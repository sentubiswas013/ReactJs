## 🟢 8. System Design (Data Engineering ⭐)

### Q1. Design a data pipeline to ingest 1 billion records per day.

**Answer:**

1 billion records per day = ~11,500 records per second. This needs a distributed, scalable, fault-tolerant architecture.

**My design:**

```
Sources (APIs, DBs, Apps)
        │
        ▼
[Kafka — ingestion buffer]        ← handles spikes, decouples producers/consumers
        │
        ▼
[Spark Streaming / Flink]         ← parallel processing across cluster
        │
        ├──→ [S3 / GCS — raw data lake]     ← land everything raw (Parquet, partitioned by date)
        │
        ▼
[Spark batch / dbt]               ← transform, clean, aggregate
        │
        ▼
[Data Warehouse — Redshift/BigQuery/Snowflake]
        │
        ▼
[BI Tools — Tableau / Looker]
```

**Key design decisions:**

**1 — Kafka as the ingestion buffer:**
- Absorbs traffic spikes — producers write at their own pace.
- Durable — messages retained for 7 days, replayable.
- Scale: 1B records/day = ~11.5K/sec — easily handled with 20–50 Kafka partitions.

**2 — Partitioned storage in S3:**
```python
# Partition by date + hour for efficient querying
df.write \
  .partitionBy("year", "month", "day", "hour") \
  .mode("append") \
  .parquet("s3://bucket/raw/events/")
```

**3 — Parallel Spark processing:**
```python
# Scale workers based on volume
spark.conf.set("spark.sql.shuffle.partitions", "500")
# Each partition ~200MB = manageable chunk size
```

**4 — Incremental loading into warehouse:**
- Don't reload all 1B records daily — load only new/changed records.
- Use watermark on `ingested_at` timestamp.

**5 — Monitoring:**
- Kafka consumer lag — alert if > 100K messages behind.
- Pipeline SLA — data must be in warehouse within 2 hours of ingestion.
- Row count checks — alert if daily count drops > 20% from 7-day average.

**Capacity estimate:**
```
1B records × 500 bytes avg = 500GB/day raw
Parquet compression ~5x = ~100GB/day stored
Monthly storage = ~3TB
```

> The key insight is: Kafka absorbs the write pressure, Spark handles the parallel processing, and S3 is the cheap durable store. Never try to write 1B records directly into a database — it won't survive the load.

---

### Q2. How would you design a real-time analytics dashboard?

**Answer:**

Real-time dashboard means data visible to users within seconds of it happening. The architecture has three layers — ingestion, processing, and serving.

**Architecture:**

```
User Actions / App Events
        │
        ▼
[Kafka — event stream]
        │
        ▼
[Spark Structured Streaming / Flink]
  - aggregate per minute/hour
  - compute running totals, counts, averages
        │
        ├──→ [Redis / DynamoDB]     ← pre-aggregated results (sub-ms reads)
        │
        ├──→ [ClickHouse / Druid]   ← real-time OLAP for ad-hoc queries
        │
        ▼
[Dashboard API — FastAPI / GraphQL]
        │
        ▼
[Frontend — WebSocket / polling every 5s]
```

**Key components:**

**1 — Pre-aggregation in streaming layer:**
```python
# Compute per-minute aggregates in Spark Streaming
result = df \
    .withWatermark("event_time", "1 minute") \
    .groupBy(
        window("event_time", "1 minute"),
        "product_id"
    ) \
    .agg(
        count("*").alias("event_count"),
        sum("revenue").alias("total_revenue")
    )
```

**2 — Redis for dashboard serving:**
```python
# Store pre-aggregated metrics in Redis — sub-millisecond reads
def write_to_redis(batch_df, batch_id):
    r = redis.Redis(host='redis-host')
    for row in batch_df.collect():
        r.hset(f"metrics:{row.product_id}",
               mapping={"count": row.event_count, "revenue": row.total_revenue})

df.writeStream.foreachBatch(write_to_redis).start()
```

**3 — WebSocket for live updates:**
```python
# FastAPI WebSocket — push updates to dashboard every 5 seconds
@app.websocket("/ws/dashboard")
async def dashboard_ws(websocket: WebSocket):
    await websocket.accept()
    while True:
        metrics = redis_client.hgetall("metrics:summary")
        await websocket.send_json(metrics)
        await asyncio.sleep(5)
```

**4 — ClickHouse for ad-hoc real-time queries:**
- Columnar OLAP database — handles billions of rows with sub-second query times.
- Great for "slice and dice" analytics on live data.

> The serving layer is the most important design decision. Redis for pre-computed KPIs (fastest), ClickHouse/Druid for flexible ad-hoc queries. Never query Kafka or raw S3 directly from a dashboard — always pre-aggregate.

---

### Q3. Design a data warehouse for an e-commerce platform.

**Answer:**

I'd use a **Star Schema** with clearly separated fact and dimension tables, built on a modern cloud warehouse.

**Core tables:**

```
Dimension Tables:
  dim_customer   (customer_id, name, email, city, country, segment)
  dim_product    (product_id, name, category, brand, price)
  dim_date       (date_id, date, day, month, quarter, year, is_weekend)
  dim_store      (store_id, name, city, region, channel: online/offline)

Fact Tables:
  fact_orders    (order_id, date_id, customer_id, product_id, store_id,
                  quantity, unit_price, discount, revenue, cost, profit)
  fact_returns   (return_id, order_id, date_id, customer_id, product_id,
                  quantity, refund_amount, reason)
  fact_sessions  (session_id, date_id, customer_id, device, pages_viewed,
                  duration_seconds, converted)
```

**Schema diagram:**
```
[dim_date] ──────────────────────────────────────────┐
[dim_customer] ──────────────────────────────────────┤
[dim_product]  ──────────── [fact_orders] ───────────┤
[dim_store]  ────────────────────────────────────────┘
```

**Layered architecture (dbt):**
```
Layer 1 — Raw:      s3://bucket/raw/         (exact copy from source)
Layer 2 — Staging:  stg_orders, stg_customers (cleaned, typed, renamed)
Layer 3 — Marts:    fact_orders, dim_customer  (business-ready)
```

**SCD Type 2 for customer dimension:**
```sql
-- Track customer address changes over time
dim_customer:
| customer_id | name  | city | start_date | end_date   | is_current |
|-------------|-------|------|------------|------------|------------|
| 101         | Alice | NYC  | 2020-01-01 | 2024-06-01 | N          |
| 101         | Alice | LA   | 2024-06-01 | NULL       | Y          |
```

**Partitioning strategy:**
```sql
-- BigQuery: partition fact tables by date, cluster by customer/product
CREATE TABLE fact_orders
PARTITION BY DATE(order_date)
CLUSTER BY customer_id, product_id;
```

**Key business queries it enables:**
```sql
-- Revenue by category last 30 days
SELECT p.category, SUM(o.revenue) AS total_revenue
FROM fact_orders o
JOIN dim_product p ON o.product_id = p.product_id
JOIN dim_date d ON o.date_id = d.date_id
WHERE d.date >= CURRENT_DATE - 30
GROUP BY p.category ORDER BY total_revenue DESC;
```

> I always start with the business questions — what does the analytics team need to answer? Then design the schema to make those queries fast and simple. Star schema with partitioned fact tables handles 99% of e-commerce analytics needs.

---

### Q4. How would you handle duplicate data in a large-scale pipeline?

**Answer:**

Duplicates are inevitable in distributed pipelines — network retries, at-least-once delivery, and reprocessing all cause them. I handle them at multiple layers.

**Layer 1 — Prevent at ingestion (idempotent writes):**
```python
# Upsert instead of insert — safe to run multiple times
df.write \
  .format("delta") \
  .mode("overwrite") \
  .option("replaceWhere", "order_date = '2024-01-15'") \
  .save("s3://bucket/orders/")
```

**Layer 2 — Deduplicate in Spark before loading:**
```python
from pyspark.sql.functions import row_number, desc
from pyspark.sql.window import Window

# Keep latest version of each order_id
w = Window.partitionBy("order_id").orderBy(desc("updated_at"))

df_deduped = df \
    .withColumn("rn", row_number().over(w)) \
    .filter("rn = 1") \
    .drop("rn")
```

**Layer 3 — Deduplicate in SQL at warehouse:**
```sql
-- CTE to find and remove duplicates
WITH ranked AS (
    SELECT *,
           ROW_NUMBER() OVER (
               PARTITION BY order_id
               ORDER BY updated_at DESC
           ) AS rn
    FROM orders_staging
)
INSERT INTO orders_clean
SELECT * EXCEPT(rn) FROM ranked WHERE rn = 1;
```

**Layer 4 — Delta Lake MERGE (upsert):**
```python
from delta.tables import DeltaTable

delta_table = DeltaTable.forPath(spark, "s3://bucket/delta/orders/")

delta_table.alias("target").merge(
    new_data.alias("source"),
    "target.order_id = source.order_id"
) \
.whenMatchedUpdateAll() \
.whenNotMatchedInsertAll() \
.execute()
```

**Layer 5 — Bloom filters for large-scale dedup:**
```python
# For 1B+ records — use probabilistic dedup with bloom filter
from pyspark.sql.functions import sha2, concat_ws

# Hash the natural key
df = df.withColumn("record_hash",
    sha2(concat_ws("|", col("order_id"), col("customer_id"), col("amount")), 256))

df_deduped = df.dropDuplicates(["record_hash"])
```

> My rule: design for idempotency first — make writes safe to repeat. Then add deduplication as a safety net. Never assume upstream systems send clean, unique data — they don't.

---

### Q5. Design a system to track user clickstream data.

**Answer:**

Clickstream is high-volume, append-only event data — millions of events per second from web/mobile. The design must handle massive write throughput and enable both real-time and historical analysis.

**Architecture:**

```
Web/Mobile Apps
        │
        ▼
[Event Collector API — FastAPI/Nginx]   ← lightweight, stateless, auto-scales
        │  (async, fire-and-forget)
        ▼
[Kafka — clickstream topic]             ← buffer, 20+ partitions
        │
        ├──→ [Spark Streaming]          ← real-time: active users, page views/min
        │           │
        │           ▼
        │    [Redis — live metrics]     ← dashboard: "1,234 users online now"
        │
        └──→ [S3 — raw events]          ← batch: historical analysis
                    │
                    ▼
             [Spark batch / dbt]        ← sessionization, funnel analysis
                    │
                    ▼
             [BigQuery / Snowflake]     ← analytics warehouse
```

**Event schema:**
```json
{
  "event_id":    "uuid-v4",
  "user_id":     "u_12345",
  "session_id":  "s_abc123",
  "event_type":  "page_view",
  "page_url":    "/products/laptop",
  "timestamp":   "2024-01-15T10:23:45.123Z",
  "device":      "mobile",
  "country":     "US",
  "referrer":    "google.com"
}
```

**Sessionization in Spark:**
```python
from pyspark.sql.functions import window, lag, unix_timestamp
from pyspark.sql.window import Window

SESSION_TIMEOUT = 30 * 60  # 30 minutes

w = Window.partitionBy("user_id").orderBy("timestamp")

df = df.withColumn("prev_ts", lag("timestamp").over(w)) \
       .withColumn("gap_seconds",
           unix_timestamp("timestamp") - unix_timestamp("prev_ts")) \
       .withColumn("new_session",
           (col("gap_seconds") > SESSION_TIMEOUT) | col("prev_ts").isNull())
```

**Funnel analysis query:**
```sql
-- Conversion funnel: view → cart → purchase
SELECT
    COUNT(DISTINCT CASE WHEN event_type = 'page_view'    THEN user_id END) AS viewers,
    COUNT(DISTINCT CASE WHEN event_type = 'add_to_cart'  THEN user_id END) AS cart_adds,
    COUNT(DISTINCT CASE WHEN event_type = 'purchase'     THEN user_id END) AS purchasers
FROM clickstream_events
WHERE event_date = CURRENT_DATE;
```

> Clickstream is one of the highest-volume data sources in any company. The key design decisions are: async ingestion (never block the user), Kafka for buffering, partition S3 by date/hour, and separate real-time (Redis) from historical (warehouse) serving layers.

---

### Q6. How would you build a scalable ETL pipeline on AWS?

**Answer:**

I'd use a fully managed, serverless-first approach on AWS to minimize ops overhead.

**Architecture:**

```
Sources (RDS, APIs, S3 uploads, Kinesis)
        │
        ▼
[AWS Glue Crawlers]              ← auto-discover schema, update Glue Catalog
        │
        ▼
[AWS Glue ETL Jobs (Spark)]      ← transform, clean, enrich
        │
        ├──→ [S3 — data lake]    ← raw + processed zones (Parquet, partitioned)
        │
        ▼
[Amazon Redshift / Athena]       ← analytics warehouse / serverless SQL
        │
        ▼
[Amazon QuickSight / Tableau]    ← BI and dashboards
```

**Orchestration with Airflow (MWAA):**
```python
from airflow import DAG
from airflow.providers.amazon.aws.operators.glue import GlueJobOperator
from airflow.providers.amazon.aws.operators.redshift import RedshiftSQLOperator

with DAG('aws_etl_pipeline', schedule_interval='@daily') as dag:

    run_glue = GlueJobOperator(
        task_id='run_glue_transform',
        job_name='orders-transform-job',
        script_location='s3://bucket/scripts/transform.py'
    )

    load_redshift = RedshiftSQLOperator(
        task_id='load_to_redshift',
        sql="COPY orders FROM 's3://bucket/processed/orders/' IAM_ROLE '...' FORMAT PARQUET;"
    )

    run_glue >> load_redshift
```

**S3 zone structure:**
```
s3://company-data-lake/
  ├── raw/           ← exact copy from source, never modified
  ├── processed/     ← cleaned, typed, Parquet format
  ├── curated/       ← business-ready, aggregated
  └── archive/       ← data older than 90 days (Glacier)
```

**Cost optimization:**
```
- S3 Intelligent-Tiering: auto-move cold data to cheaper storage
- Glue: serverless — pay per DPU-hour, no idle cluster cost
- Athena: pay per TB scanned — use Parquet + partitions to minimize scan
- Redshift: use pause/resume for dev clusters
```

**Security:**
```
- IAM roles for Glue/Redshift — no hardcoded credentials
- S3 bucket policies — least privilege access
- KMS encryption at rest for all S3 buckets
- VPC endpoints — keep traffic off public internet
- AWS Secrets Manager for DB credentials
```

> My AWS ETL stack: S3 as the data lake, Glue for serverless Spark jobs, MWAA (Airflow) for orchestration, Redshift for the warehouse, and CloudWatch for monitoring. It's fully managed — no servers to patch, auto-scales with data volume.

---

### Q7. How do you design for high availability and fault tolerance?

**Answer:**

High availability means the system keeps running even when components fail. Fault tolerance means it recovers gracefully without data loss.

**Principle 1 — Eliminate single points of failure:**
```
❌ Single Kafka broker → ✅ Kafka cluster (3+ brokers, replication factor 3)
❌ Single Airflow scheduler → ✅ Active-passive HA scheduler
❌ Single Redshift node → ✅ Multi-node cluster with automatic failover
❌ Single AZ deployment → ✅ Multi-AZ across 3 availability zones
```

**Principle 2 — Idempotent, restartable pipelines:**
```python
# Every pipeline step must be safely re-runnable
# Use partition overwrite — not append
df.write \
  .mode("overwrite") \
  .partitionBy("date") \
  .parquet("s3://bucket/orders/")
# If this fails and reruns — no duplicates, no data loss
```

**Principle 3 — Retry with exponential backoff:**
```python
from tenacity import retry, stop_after_attempt, wait_exponential

@retry(
    stop=stop_after_attempt(5),
    wait=wait_exponential(multiplier=1, min=4, max=60)
)
def load_to_warehouse(df):
    df.write.jdbc(url=jdbc_url, table="orders", mode="append")
```

**Principle 4 — Dead letter queues for failed messages:**
```python
# Kafka: route failed messages to DLQ for investigation
def process_message(msg):
    try:
        transform_and_load(msg)
    except Exception as e:
        producer.send('orders-dlq', value={
            'original_message': msg.value,
            'error': str(e),
            'failed_at': datetime.utcnow().isoformat()
        })
```

**Principle 5 — Checkpointing for streaming:**
```python
# Spark Streaming: checkpoint enables recovery from exact failure point
df.writeStream \
  .option("checkpointLocation", "s3://bucket/checkpoints/orders/") \
  .format("delta") \
  .start("s3://bucket/output/")
```

**Principle 6 — Data replication:**
```
S3: 99.999999999% durability — 3 copies across AZs automatically
Redshift: automated snapshots every 8 hours, cross-region replication
Kafka: replication factor 3 — survives 2 broker failures
```

**Monitoring and alerting:**
```python
# Alert before failure, not after
# Kafka lag > 100K → scale consumers
# Pipeline duration > 2x baseline → investigate
# Row count drop > 20% → data quality alert
# Disk usage > 80% → expand storage
```

> HA and fault tolerance is about designing for failure from day one — not adding it later. Every component I design, I ask: "What happens when this fails?" If the answer is "the whole pipeline stops", I need to fix that.

---

### Q8. How would you migrate an on-premise data warehouse to the cloud?

**Answer:**

Cloud migration is a phased process — never a big-bang cutover. I follow a 5-phase approach.

**Phase 1 — Assessment (2–4 weeks):**
```
- Inventory: catalog all tables, schemas, stored procedures, ETL jobs
- Data volume: total size, growth rate, query patterns
- Dependencies: which reports/apps depend on which tables
- Complexity: identify custom SQL, proprietary functions to rewrite
- Choose target: Redshift (AWS), BigQuery (GCP), or Snowflake (multi-cloud)
```

**Phase 2 — Schema migration:**
```sql
-- Convert Oracle-specific SQL to target dialect
-- Oracle:
SELECT * FROM orders WHERE ROWNUM <= 10;

-- BigQuery equivalent:
SELECT * FROM orders LIMIT 10;

-- Oracle date functions → BigQuery:
-- SYSDATE → CURRENT_DATE
-- ADD_MONTHS(date, 1) → DATE_ADD(date, INTERVAL 1 MONTH)
```

**Phase 3 — Data migration:**
```python
# Option A: AWS DMS (Database Migration Service) — continuous replication
# Option B: Export to S3, load to Redshift
import boto3

# Export from on-prem Oracle to S3
df = spark.read.jdbc(url=oracle_url, table="orders", properties=oracle_props)
df.write.parquet("s3://migration-bucket/orders/")

# Load into Redshift
redshift.execute("""
    COPY orders FROM 's3://migration-bucket/orders/'
    IAM_ROLE 'arn:aws:iam::...'
    FORMAT PARQUET;
""")
```

**Phase 4 — ETL pipeline migration:**
```
On-prem SSIS/Informatica → AWS Glue / dbt
On-prem Hadoop jobs      → Spark on EMR / Databricks
Cron jobs                → Apache Airflow (MWAA)
```

**Phase 5 — Cutover (zero-downtime):**
```
Week 1–4:  Run cloud warehouse in parallel — validate results match on-prem
Week 5:    Switch read traffic to cloud — on-prem still writes
Week 6:    Switch write traffic to cloud — on-prem becomes read-only backup
Week 7:    Decommission on-prem after 2-week validation period
```

**Validation query:**
```sql
-- Compare row counts between on-prem and cloud
SELECT 'on_prem' AS source, COUNT(*) AS cnt FROM on_prem.orders
UNION ALL
SELECT 'cloud'   AS source, COUNT(*) AS cnt FROM cloud.orders;

-- Compare aggregates
SELECT 'on_prem', SUM(revenue) FROM on_prem.fact_sales WHERE year = 2023
UNION ALL
SELECT 'cloud',   SUM(revenue) FROM cloud.fact_sales   WHERE year = 2023;
```

> The biggest risk in migration is data loss or discrepancy. I always run parallel for at least 2 weeks and validate row counts + key aggregates before cutting over. Never rush the cutover — a bad migration is worse than staying on-prem.

---

### Q9. Design a data lake architecture.

**Answer:**

A data lake is a centralized repository for all raw data — structured, semi-structured, and unstructured — stored cheaply in object storage.

**Architecture — 4 zones:**

```
Sources (DBs, APIs, Logs, IoT, Files)
        │
        ▼
┌─────────────────────────────────────────────────────┐
│                    DATA LAKE (S3/GCS/ADLS)          │
│                                                     │
│  Zone 1: RAW / BRONZE                               │
│  s3://lake/raw/                                     │
│  - Exact copy from source, never modified           │
│  - All formats: JSON, CSV, Avro, logs               │
│  - Partitioned by ingestion date                    │
│                                                     │
│  Zone 2: PROCESSED / SILVER                         │
│  s3://lake/processed/                               │
│  - Cleaned, typed, deduplicated                     │
│  - Parquet format, partitioned by event date        │
│  - Schema validated                                 │
│                                                     │
│  Zone 3: CURATED / GOLD                             │
│  s3://lake/curated/                                 │
│  - Business-ready, aggregated                       │
│  - Star schema fact/dimension tables                │
│  - Optimized for analytics                          │
│                                                     │
│  Zone 4: ARCHIVE                                    │
│  s3://lake/archive/                                 │
│  - Data > 90 days old                               │
│  - S3 Glacier — cheap cold storage                  │
└─────────────────────────────────────────────────────┘
        │
        ▼
[Query Layer: Athena / Spark / Presto]
        │
        ▼
[Data Warehouse: Redshift / BigQuery]   ← curated data only
        │
        ▼
[BI Tools / Data Science / ML]
```

**File organization:**
```
s3://lake/processed/orders/
  ├── year=2024/
  │   ├── month=01/
  │   │   ├── day=15/
  │   │   │   ├── part-00000.parquet
  │   │   │   └── part-00001.parquet
```

**Delta Lake on top for ACID:**
```python
# Write with Delta — adds ACID, time travel, schema enforcement
df.write \
  .format("delta") \
  .mode("append") \
  .partitionBy("year", "month", "day") \
  .save("s3://lake/delta/orders/")

# Time travel — query data as of yesterday
spark.read \
  .format("delta") \
  .option("timestampAsOf", "2024-01-14") \
  .load("s3://lake/delta/orders/")
```

**Governance:**
```
- AWS Glue Data Catalog: central metadata store — table schemas, locations
- AWS Lake Formation: fine-grained access control — column/row level security
- Data quality: Great Expectations checks at each zone transition
- Lineage: OpenLineage / DataHub — track data from source to gold
```

> The bronze/silver/gold pattern is the industry standard. Raw data is sacred — never modify it. Each zone adds more quality and structure. Delta Lake on S3 gives you warehouse-quality reliability at data lake cost.

---

### Q10. How do you handle PII (Personally Identifiable Information) in your pipeline?

**Answer:**

PII handling is critical — both legally (GDPR, CCPA) and ethically. I apply a defense-in-depth approach across the entire pipeline.

**What counts as PII:**
```
Direct PII:   name, email, phone, SSN, passport number, credit card
Indirect PII: IP address, device ID, location, user_id (if linkable)
Sensitive:    health data, financial data, biometrics
```

**Strategy 1 — Classify data at ingestion:**
```python
PII_COLUMNS = ['name', 'email', 'phone', 'ssn', 'ip_address', 'address']

def tag_pii_columns(df):
    for col_name in df.columns:
        if col_name in PII_COLUMNS:
            df = df.withColumn(col_name,
                df[col_name].cast("string"))  # tag for downstream handling
    return df
```

**Strategy 2 — Tokenization (replace with reversible token):**
```python
import hashlib, hmac

SECRET_KEY = os.environ['PII_HMAC_KEY']  # from AWS Secrets Manager

def tokenize(value: str) -> str:
    return hmac.new(
        SECRET_KEY.encode(),
        value.encode(),
        hashlib.sha256
    ).hexdigest()

# Replace email with token — analytics still works, PII is protected
df = df.withColumn("email_token", udf(tokenize)("email")) \
       .drop("email")
```

**Strategy 3 — Masking for non-production environments:**
```python
from pyspark.sql.functions import regexp_replace, lit, sha2

def mask_pii(df):
    return df \
        .withColumn("email",   regexp_replace("email", r"(?<=.).(?=.*@)", "*")) \
        .withColumn("phone",   regexp_replace("phone", r"\d(?=\d{4})", "*")) \
        .withColumn("name",    lit("REDACTED")) \
        .withColumn("user_id", sha2("user_id", 256))  # one-way hash
```

**Strategy 4 — Encryption at rest and in transit:**
```python
# S3: server-side encryption with KMS
df.write \
  .option("fs.s3a.server-side-encryption-algorithm", "aws:kms") \
  .option("fs.s3a.server-side-encryption-key", KMS_KEY_ARN) \
  .parquet("s3://bucket/sensitive/customers/")
```

**Strategy 5 — Access control (least privilege):**
```
- Raw PII data: only data engineers + security team
- Tokenized data: analysts can access
- Aggregated data: all users
- Column-level security in BigQuery/Snowflake: mask PII columns per role
```

**Strategy 6 — Right to be forgotten (GDPR Article 17):**
```python
# Delta Lake: delete specific user's data
from delta.tables import DeltaTable

delta_table = DeltaTable.forPath(spark, "s3://bucket/delta/customers/")
delta_table.delete(f"user_id = '{user_id_to_delete}'")

# VACUUM to physically remove deleted files
delta_table.vacuum(retentionHours=0)
```

**Strategy 7 — Audit logging:**
```python
# Log every access to PII data
def log_pii_access(user, table, columns_accessed, purpose):
    audit_log.info({
        "user":             user,
        "table":            table,
        "columns":          columns_accessed,
        "purpose":          purpose,
        "timestamp":        datetime.utcnow().isoformat(),
        "ip":               request.remote_addr
    })
```

> PII handling is not optional — it's a legal requirement. My rule: PII should be tokenized or masked as early as possible in the pipeline — ideally at ingestion. The further downstream PII travels in plain text, the higher the risk. I treat PII data like a hot potato — handle it minimally and pass it on in protected form.


