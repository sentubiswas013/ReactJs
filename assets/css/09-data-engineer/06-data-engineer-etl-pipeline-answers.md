## 🟢 5. ETL & Data Pipelines

### Q1. What is ETL? What is ELT? When would you use each?

**Answer:**

**ETL — Extract, Transform, Load:**
- Extract data from source systems.
- Transform it (clean, enrich, aggregate) in a separate processing layer.
- Load the transformed data into the destination (warehouse).

**ELT — Extract, Load, Transform:**
- Extract data from sources.
- Load raw data directly into the destination first.
- Transform it inside the warehouse using SQL.

```
ETL flow:
Source → [Extract] → [Transform: Spark/Python] → [Load] → Warehouse

ELT flow:
Source → [Extract] → [Load raw] → Warehouse → [Transform: dbt/SQL]
```

**When to use ETL:**
- Sensitive data that must be masked/filtered before entering the warehouse.
- Complex transformations that SQL can't handle well.
- Legacy systems with limited warehouse compute.
- When raw data should never land in the destination.

**When to use ELT:**
- Modern cloud warehouses (BigQuery, Snowflake, Redshift) — they have massive compute.
- When you want raw data preserved for reprocessing.
- When transformations are SQL-based (dbt is built for this).
- Faster ingestion — load first, transform later.

| | ETL | ELT |
|---|---|---|
| Transform location | Outside warehouse | Inside warehouse |
| Raw data stored | ❌ | ✅ |
| Best tools | Spark, Glue, Informatica | dbt, BigQuery, Snowflake |
| Modern preference | Legacy systems | Cloud-native pipelines |

> In modern data engineering, ELT is the dominant pattern — load raw data into S3 or the warehouse, then use dbt to transform. It's faster to build, easier to debug, and raw data is always available for reprocessing.

---

### Q2. How do you handle data quality issues in a pipeline?

**Answer:**

Data quality is one of the most critical parts of a pipeline. I handle it in layers — prevent, detect, and alert.

**Step 1 — Define quality rules upfront:**
- Completeness: required fields must not be null.
- Uniqueness: no duplicate primary keys.
- Validity: values within expected ranges or formats.
- Consistency: referential integrity across tables.
- Timeliness: data arrives within expected SLA windows.

**Step 2 — Validate at ingestion:**

```python
import pandas as pd

def validate(df):
    assert df['order_id'].notnull().all(),     "Null order_ids found"
    assert df['order_id'].is_unique,           "Duplicate order_ids found"
    assert (df['amount'] > 0).all(),           "Negative amounts found"
    assert df['status'].isin(
        ['pending','shipped','delivered']).all(), "Invalid status values"
```

**Step 3 — Use a data quality framework:**

```python
# Great Expectations example
import great_expectations as ge

df = ge.from_pandas(raw_df)
df.expect_column_values_to_not_be_null('order_id')
df.expect_column_values_to_be_unique('order_id')
df.expect_column_values_to_be_between('amount', 0, 100000)
```

**Step 4 — Quarantine bad records:**

```python
good_records = df[df['order_id'].notnull()]
bad_records  = df[df['order_id'].isnull()]

good_records.to_parquet('s3://bucket/processed/')
bad_records.to_parquet('s3://bucket/quarantine/')  # investigate later
```

**Step 5 — Alert and log:**
- Log quality metrics (null counts, duplicate counts) per pipeline run.
- Alert on Slack/PagerDuty if quality drops below threshold.

> I never silently drop bad records. I always quarantine them, log the count, and alert the team. Silent data loss is the worst kind of bug in a data pipeline.

---

### Q3. What is idempotency in a data pipeline?

**Answer:**

Idempotency means **running the same pipeline multiple times produces the same result** — no duplicates, no data loss, no side effects.

If a pipeline fails halfway and you re-run it, the output should be identical to a single successful run.

**Why it matters:**
- Pipelines fail. Networks drop. Jobs time out. You need to safely re-run without corrupting data.

**Non-idempotent (bad):**

```python
# Every run appends — re-running creates duplicates
df.to_sql('orders', engine, if_exists='append')
```

**Idempotent (good) — delete and reload:**

```python
# Delete the partition first, then insert fresh
engine.execute("DELETE FROM orders WHERE order_date = '2024-01-15'")
df.to_sql('orders', engine, if_exists='append')
```

**Idempotent (good) — upsert/merge:**

```sql
-- MERGE handles both insert and update safely
MERGE INTO orders AS target
USING new_orders AS source
ON target.order_id = source.order_id
WHEN MATCHED THEN UPDATE SET ...
WHEN NOT MATCHED THEN INSERT ...;
```

**Idempotent (good) — overwrite partition:**

```python
# Spark: overwrite only the specific partition
df.write \
  .mode("overwrite") \
  .partitionBy("order_date") \
  .parquet("s3://bucket/orders/")
```

> I design every pipeline to be idempotent from day one. The rule is simple — if I can safely re-run it without calling anyone at 2am to fix duplicates, it's idempotent.

---

### Q4. How do you handle late-arriving data?

**Answer:**

Late-arriving data is when records arrive after the expected processing window — for example, a mobile app event from yesterday arrives today due to network delays.

**Strategies:**

**1 — Reprocess the affected partition:**
If data is partitioned by date, re-run the pipeline for the affected date when late data arrives.

```python
# Airflow: trigger backfill for a specific date
airflow dags backfill my_pipeline --start-date 2024-01-14 --end-date 2024-01-14
```

**2 — Use a grace period / watermark (streaming):**
In stream processing, define a watermark — how long to wait for late events before closing a window.

```python
# Spark Structured Streaming: 2-hour watermark
df.withWatermark("event_time", "2 hours") \
  .groupBy(window("event_time", "1 hour")) \
  .count()
```

**3 — Use an arrival timestamp + event timestamp:**
Store both `event_time` (when it happened) and `ingestion_time` (when it arrived). Process based on event_time but partition by ingestion_time for pipeline efficiency.

```sql
-- Separate event time from ingestion time
SELECT
    event_id,
    event_timestamp,           -- when it actually happened
    ingested_at,               -- when we received it
    DATEDIFF(ingested_at, event_timestamp) AS latency_hours
FROM events;
```

**4 — Upsert / merge on re-arrival:**
Design the pipeline to upsert — if a late record arrives for an already-processed window, merge it in rather than duplicate it.

> My default approach is: partition by event date, use upserts, and run a daily reconciliation job that catches any late records from the previous 3 days. For streaming, I set a watermark based on the SLA agreed with the upstream team.

---

### Q5. What is incremental loading vs full load?

**Answer:**

**Full Load:**
Truncate the destination table and reload all data from scratch on every run.

```python
# Full load: wipe and reload
engine.execute("TRUNCATE TABLE orders")
df.to_sql('orders', engine, if_exists='append')
```

✅ Simple, always consistent
❌ Slow and expensive for large tables — reads everything every time

**Incremental Load:**
Only load new or changed records since the last run — using a watermark like `updated_at` or `created_at`.

```python
# Incremental: only load records updated since last run
last_run = get_last_watermark()   # e.g., '2024-01-14 23:59:59'

query = f"""
    SELECT * FROM orders
    WHERE updated_at > '{last_run}'
"""
new_records = pd.read_sql(query, source_engine)
new_records.to_sql('orders', dest_engine, if_exists='append')

save_watermark(new_records['updated_at'].max())
```

✅ Fast, efficient, scales to large tables
❌ More complex — need reliable watermark column, handle deletes separately

| | Full Load | Incremental Load |
|---|---|---|
| Speed | Slow | Fast |
| Complexity | Simple | More complex |
| Best for | Small tables, reference data | Large transactional tables |
| Handles deletes | ✅ (wipe and reload) | ❌ (need CDC or soft deletes) |

**Handling deletes in incremental loads:**
- Use soft deletes (`is_deleted = true`) in the source.
- Or use CDC (Change Data Capture) tools like Debezium to capture DELETE events.

> I use full load for small dimension tables (< 1M rows) and incremental for large fact tables. The key is having a reliable `updated_at` column on the source — if the source doesn't have one, I push back and ask the team to add it.

---

### Q6. How do you monitor and alert on pipeline failures?

**Answer:**

Monitoring is non-negotiable in production pipelines. I monitor at three levels — infrastructure, pipeline, and data quality.

**Level 1 — Pipeline orchestration monitoring (Airflow):**

```python
# Airflow: email + Slack alert on failure
from airflow import DAG
from airflow.operators.python import PythonOperator
from airflow.utils.email import send_email

def alert_on_failure(context):
    send_email(
        to='data-team@company.com',
        subject=f"Pipeline Failed: {context['task_instance'].dag_id}",
        html_content=f"Task {context['task_instance'].task_id} failed."
    )

dag = DAG(
    'my_pipeline',
    default_args={
        'on_failure_callback': alert_on_failure,
        'retries': 3,
        'retry_delay': timedelta(minutes=5)
    }
)
```

**Level 2 — Custom metrics logging:**

```python
import logging
import time

def run_pipeline():
    start = time.time()
    try:
        records = extract()
        load(records)
        duration = time.time() - start
        log_metric('pipeline.success', 1)
        log_metric('pipeline.duration_seconds', duration)
        log_metric('pipeline.records_loaded', len(records))
    except Exception as e:
        log_metric('pipeline.failure', 1)
        raise
```

**Level 3 — Data quality alerts:**

```python
# Alert if row count drops significantly
expected_min_rows = 10_000
actual_rows = get_row_count('fact_orders')

if actual_rows < expected_min_rows:
    send_slack_alert(f"⚠️ fact_orders has only {actual_rows} rows — expected {expected_min_rows}+")
```

**Tools I use:**
- **Airflow** — task-level success/failure tracking, SLA miss alerts.
- **CloudWatch / Datadog** — infrastructure and custom metrics.
- **Great Expectations / dbt tests** — data quality checks post-load.
- **PagerDuty / Slack** — alerting channels.

> My rule: every production pipeline must have at minimum — retry logic, failure alerts, and a row count check. If any of those three are missing, the pipeline isn't production-ready.

---

### Q7. What is data lineage?

**Answer:**

Data lineage is the ability to **track where data comes from, how it moves, and how it transforms** across a pipeline — from source to destination.

It answers questions like:
- Where did this column come from?
- Which pipelines touch this table?
- If I change this source table, what downstream reports break?
- Why does this number look wrong — where did it get modified?

**Visual example:**

```
[CRM Database]          [Payment System]
      │                        │
      ▼                        ▼
[raw_customers (S3)]   [raw_payments (S3)]
      │                        │
      └──────────┬─────────────┘
                 ▼
        [dbt: stg_customers + stg_payments]
                 │
                 ▼
        [dbt: fact_revenue]
                 │
                 ▼
        [Tableau: Revenue Dashboard]
```

**Tools for data lineage:**
- **dbt** — automatically generates lineage graphs from model dependencies.
- **Apache Atlas** — enterprise metadata and lineage tracking.
- **OpenLineage** — open standard for lineage events (used by Airflow, Spark).
- **DataHub / Amundsen** — data catalog with lineage visualization.
- **AWS Glue Data Catalog** — tracks lineage within the AWS ecosystem.

```yaml
# dbt: lineage is automatic from ref() calls
-- models/mart/fact_revenue.sql
SELECT *
FROM {{ ref('stg_payments') }}   -- dbt knows this depends on stg_payments
JOIN {{ ref('stg_customers') }}  -- and stg_customers
```

> Data lineage is critical for debugging and impact analysis. When an analyst says "this revenue number changed", lineage lets me trace it back to the exact source table and transformation in minutes instead of hours.

---

### Q8. How do you handle schema changes in a pipeline?

**Answer:**

Schema changes are one of the most common causes of pipeline failures. I handle them with a combination of defensive coding, schema evolution, and alerting.

**Types of schema changes:**
- **Additive** — new column added (usually safe).
- **Breaking** — column renamed, removed, or type changed (dangerous).

**Strategy 1 — Schema-on-read with flexible formats:**

```python
# Parquet + Spark: schema evolution handles new columns automatically
df = spark.read \
    .option("mergeSchema", "true") \
    .parquet("s3://bucket/orders/")
```

**Strategy 2 — Detect schema changes before processing:**

```python
import pandas as pd

expected_columns = {'order_id', 'customer_id', 'amount', 'status'}

def validate_schema(df):
    actual_columns = set(df.columns)
    missing = expected_columns - actual_columns
    extra   = actual_columns - expected_columns

    if missing:
        raise ValueError(f"Missing columns: {missing}")
    if extra:
        print(f"Warning: New columns detected: {extra}")  # log but don't fail
```

**Strategy 3 — dbt schema tests:**

```yaml
# dbt: schema.yml — test column existence and types
models:
  - name: stg_orders
    columns:
      - name: order_id
        tests:
          - not_null
          - unique
      - name: amount
        tests:
          - not_null
```

**Strategy 4 — Versioned schemas (Avro + Schema Registry):**

```python
# Kafka + Confluent Schema Registry: enforce schema compatibility
# BACKWARD compatible: new schema can read old data
# FORWARD compatible: old schema can read new data
# FULL compatible: both directions
```

**Strategy 5 — Soft migration for breaking changes:**

```sql
-- Add new column first, keep old one
ALTER TABLE orders ADD COLUMN customer_name VARCHAR(100);
UPDATE orders SET customer_name = (SELECT name FROM customers WHERE id = customer_id);
-- Only drop old column after all pipelines are updated
```

> My rule: additive changes are fine — I log them and move on. Breaking changes must go through a migration plan with backward compatibility. I never let a schema change silently corrupt downstream data.

---

### Q9. What tools have you used for ETL? (Airflow, Glue, dbt, Spark)

**Answer:**

I've worked with the main ETL tools across the stack — here's my honest take on each:

---

**Apache Airflow — Orchestration:**
- Defines pipelines as DAGs (Directed Acyclic Graphs) in Python.
- Schedules, monitors, and retries tasks.
- Not a processing engine — it orchestrates other tools.

```python
from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime

with DAG('daily_orders', schedule_interval='@daily', start_date=datetime(2024,1,1)) as dag:
    extract = PythonOperator(task_id='extract', python_callable=extract_orders)
    transform = PythonOperator(task_id='transform', python_callable=transform_orders)
    load = PythonOperator(task_id='load', python_callable=load_orders)

    extract >> transform >> load   # define dependency order
```

---

**AWS Glue — Serverless ETL on AWS:**
- Managed Spark environment — no cluster to manage.
- Auto-discovers schemas with Glue Crawlers.
- Integrates natively with S3, Redshift, RDS.

```python
# Glue job: read from S3, transform, write to Redshift
from awsglue.context import GlueContext
glueContext = GlueContext(SparkContext.getOrCreate())

df = glueContext.create_dynamic_frame.from_catalog(
    database="raw_db", table_name="orders"
)
df = df.filter(lambda x: x['status'] == 'completed')
glueContext.write_dynamic_frame.from_jdbc_conf(df, ...)
```

---

**dbt — SQL Transformations (ELT):**
- Transforms data inside the warehouse using SQL SELECT statements.
- Handles dependencies, testing, documentation, and lineage automatically.
- My go-to for the T in ELT.

```sql
-- dbt model: models/mart/fact_orders.sql
SELECT
    o.order_id,
    c.customer_name,
    o.amount,
    o.order_date
FROM {{ ref('stg_orders') }} o
JOIN {{ ref('stg_customers') }} c ON o.customer_id = c.customer_id
```

---

**Apache Spark — Large-scale Processing:**
- Distributed processing engine for massive datasets.
- Runs on EMR, Databricks, Glue, or standalone clusters.

```python
from pyspark.sql import SparkSession
from pyspark.sql.functions import col, sum

spark = SparkSession.builder.appName("ETL").getOrCreate()

df = spark.read.parquet("s3://bucket/raw/orders/")
result = df.filter(col("status") == "completed") \
           .groupBy("customer_id") \
           .agg(sum("amount").alias("total_spent"))

result.write.mode("overwrite").parquet("s3://bucket/processed/customer_spend/")
```

---

**Quick comparison:**

| Tool | Role | Best for |
|---|---|---|
| Airflow | Orchestration | Scheduling, monitoring, dependencies |
| AWS Glue | Serverless ETL | AWS-native, no cluster management |
| dbt | SQL transformation | ELT inside warehouse, analytics engineering |
| Spark | Processing engine | Large-scale batch and streaming |

> In a typical modern stack I'd use: Airflow to orchestrate, Spark or Glue to process raw data, dbt to transform inside the warehouse, and Great Expectations for quality checks. Each tool does one thing well.

---

### Q10. How do you ensure exactly-once processing in a pipeline?

**Answer:**

Exactly-once means **each record is processed and written exactly one time** — no duplicates, no missed records. It's the hardest guarantee to achieve in distributed systems.

There are three delivery guarantees:
- **At-most-once** — may lose records, never duplicates. (fire and forget)
- **At-least-once** — never loses records, may duplicate. (retry on failure)
- **Exactly-once** — never loses, never duplicates. (hardest to achieve)

**Strategy 1 — Idempotent writes + at-least-once delivery:**
The most practical approach — retry freely, but make writes idempotent so duplicates are harmless.

```sql
-- Upsert: safe to run multiple times
INSERT INTO orders (order_id, amount, status)
VALUES (1001, 99.99, 'completed')
ON CONFLICT (order_id) DO UPDATE
SET amount = EXCLUDED.amount, status = EXCLUDED.status;
```

**Strategy 2 — Deduplication on read:**

```python
# Deduplicate after loading
df = spark.read.parquet("s3://bucket/orders/")
df_deduped = df.dropDuplicates(['order_id'])
df_deduped.write.mode("overwrite").parquet("s3://bucket/clean/orders/")
```

**Strategy 3 — Transactional writes (Delta Lake):**

```python
# Delta Lake: ACID transactions prevent partial writes
df.write \
  .format("delta") \
  .mode("overwrite") \
  .option("overwriteSchema", "true") \
  .save("s3://bucket/delta/orders/")
```

**Strategy 4 — Kafka exactly-once (streaming):**

```python
# Spark Structured Streaming: exactly-once with checkpointing
df.writeStream \
  .format("delta") \
  .outputMode("append") \
  .option("checkpointLocation", "s3://bucket/checkpoints/orders/") \
  .start("s3://bucket/delta/orders/")
```

**Strategy 5 — Two-phase commit pattern:**
1. Write to a staging table.
2. Validate the staging data.
3. Atomically swap staging → production in a single transaction.

```sql
BEGIN;
  TRUNCATE TABLE orders_staging;
  -- load new data into staging
  INSERT INTO orders SELECT * FROM orders_staging;  -- atomic swap
COMMIT;
```

> In practice, true exactly-once is very hard to guarantee end-to-end. My approach is at-least-once delivery + idempotent writes — it's simpler, more reliable, and achieves the same outcome. Delta Lake and Kafka with checkpointing are my go-to tools when exactly-once is a hard requirement.


