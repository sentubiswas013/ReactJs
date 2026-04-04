## 🟢 4. Data Warehousing

### Q1. What is a data warehouse? How is it different from a database?

**Answer:**

A data warehouse is a **centralized repository designed for analytics and reporting** — it stores large volumes of historical, structured data from multiple sources, optimized for read-heavy analytical queries.

A regular database (OLTP) is designed for **day-to-day transactional operations** — fast inserts, updates, and deletes.

| | Database (OLTP) | Data Warehouse (OLAP) |
|---|---|---|
| Purpose | Run operations | Analyze history |
| Data | Current, live | Historical, aggregated |
| Schema | Normalized | Denormalized (Star/Snowflake) |
| Query type | Simple, fast reads/writes | Complex, large aggregations |
| Users | Applications, developers | Analysts, BI tools |
| Examples | PostgreSQL, MySQL | Redshift, BigQuery, Snowflake |

**Real-world example:**
> Your e-commerce app writes orders to a PostgreSQL database. Every night, an ETL pipeline loads that data into Redshift. Analysts then query Redshift to answer "What were total sales by region last quarter?" — that's the data warehouse doing its job.

> As a data engineer, I build and maintain the pipelines that feed the data warehouse. The warehouse is the destination, not the source.

---

### Q2. Explain Star Schema vs Snowflake Schema.

**Answer:**

Both are data warehouse schema designs — the difference is how dimension tables are structured.

**Star Schema:**
- One central **fact table** surrounded by **denormalized dimension tables**.
- Simple, flat structure — looks like a star.
- Fewer JOINs, faster queries.
- More storage due to redundancy in dimensions.

```
         [dim_date]
              |
[dim_customer] — [fact_sales] — [dim_product]
              |
         [dim_store]
```

**Snowflake Schema:**
- Dimension tables are **normalized** — split into sub-dimensions.
- Looks like a snowflake — more complex structure.
- More JOINs needed, slightly slower queries.
- Less storage, more data integrity.

```
[dim_city] → [dim_customer] → [fact_sales] → [dim_product] → [dim_category]
```

| | Star Schema | Snowflake Schema |
|---|---|---|
| Dimension tables | Denormalized | Normalized |
| Query speed | Faster | Slower (more JOINs) |
| Storage | More | Less |
| Complexity | Simple | Complex |
| Best for | BI tools, dashboards | Storage-sensitive warehouses |

> In practice, I prefer Star Schema for most data warehouse projects — BI tools like Tableau and Power BI perform better with fewer JOINs. Snowflake schema makes sense when storage cost is a concern or dimensions are very large.

---

### Q3. What is a fact table vs a dimension table?

**Answer:**

These are the two building blocks of a data warehouse schema.

- **Fact Table** — stores **measurable, quantitative data** — the metrics you want to analyze. Contains foreign keys to dimension tables and numeric measures.
- **Dimension Table** — stores **descriptive, contextual data** — the "who, what, where, when" around the facts.

**Example — Sales data warehouse:**

```
Fact Table: fact_sales
| sale_id | date_id | customer_id | product_id | store_id | quantity | revenue |
|---------|---------|-------------|------------|----------|----------|---------|
| 1       | 20240101| 101         | 501        | 10       | 2        | 199.98  |

Dimension Table: dim_customer
| customer_id | name    | city    | country |
|-------------|---------|---------|---------|
| 101         | Alice   | NYC     | USA     |

Dimension Table: dim_product
| product_id | product_name | category    | price  |
|------------|--------------|-------------|--------|
| 501        | Laptop       | Electronics | 999.99 |
```

**Key characteristics:**

| | Fact Table | Dimension Table |
|---|---|---|
| Contains | Metrics, measures | Descriptive attributes |
| Size | Very large (millions of rows) | Smaller (thousands of rows) |
| Keys | Foreign keys + degenerate keys | Primary key |
| Examples | Sales, transactions, events | Customer, Product, Date, Location |

> A good rule of thumb — if you're asking "how much" or "how many", that's a fact. If you're asking "who", "what", "where", or "when", that's a dimension.

---

### Q4. What is SCD (Slowly Changing Dimension)? Explain Type 1, Type 2, Type 3.

**Answer:**

SCD stands for **Slowly Changing Dimension** — it describes how we handle changes to dimension data over time. For example, a customer moves to a new city — how do we update the `dim_customer` table?

There are three main types:

---

**Type 1 — Overwrite (No History):**
Simply update the existing record. Old value is lost. No history kept.

```sql
-- Customer moved from NYC to LA
UPDATE dim_customer SET city = 'LA' WHERE customer_id = 101;
-- NYC is gone forever
```
✅ Simple  ❌ No historical tracking

---

**Type 2 — Add New Row (Full History):**
Insert a new row with the new value. Keep the old row. Use `start_date`, `end_date`, and `is_current` flag to track versions.

```
| customer_id | name  | city | start_date | end_date   | is_current |
|-------------|-------|------|------------|------------|------------|
| 101         | Alice | NYC  | 2020-01-01 | 2024-06-01 | N          |
| 101         | Alice | LA   | 2024-06-01 | NULL       | Y          |
```
✅ Full history preserved  ❌ Table grows over time

---

**Type 3 — Add New Column (Limited History):**
Add a new column to store the previous value. Only tracks one level of change.

```
| customer_id | name  | current_city | previous_city |
|-------------|-------|--------------|---------------|
| 101         | Alice | LA           | NYC           |
```
✅ Simple, no extra rows  ❌ Only one previous value stored

---

**Which type to use:**

| Type | Use When |
|---|---|
| Type 1 | History doesn't matter (e.g., fix a typo) |
| Type 2 | Full history needed (e.g., customer address, employee department) |
| Type 3 | Only current and previous value needed |

> In data engineering, Type 2 is the most commonly used in production warehouses. I've implemented it using dbt snapshots and Spark merge operations — it's the standard for tracking customer, product, and employee changes over time.

---

### Q5. What is a data mart?

**Answer:**

A data mart is a **subset of a data warehouse** focused on a specific business domain or department — like Sales, Finance, or Marketing.

Think of a data warehouse as a large central library, and a data mart as a smaller specialized section within it — just the books relevant to one team.

```
                    ┌─────────────────────┐
                    │    Data Warehouse   │
                    │  (All company data) │
                    └──────────┬──────────┘
                               │
          ┌────────────────────┼────────────────────┐
          ▼                    ▼                    ▼
   [Sales Data Mart]   [Finance Data Mart]  [Marketing Data Mart]
   (sales, revenue,    (budgets, P&L,       (campaigns, leads,
    customers)          invoices)            conversions)
```

**Two approaches to building data marts:**

- **Top-down (Inmon approach)** — build the enterprise warehouse first, then create data marts from it.
- **Bottom-up (Kimball approach)** — build individual data marts first, then integrate them into a warehouse.

**Benefits of data marts:**
- Faster queries — smaller, focused datasets.
- Easier access control — teams only see their data.
- Simpler for analysts — no need to understand the full warehouse schema.

> In practice, I've built data marts as separate schemas within Redshift or BigQuery — the Sales mart has its own set of tables optimized for the sales team's reporting needs. It reduces query complexity and improves performance for end users.

---

### Q6. What is the difference between a data warehouse and a data lake?

**Answer:**

This is a very common question. The key difference is **what kind of data they store and how it's structured**.

- **Data Warehouse** — stores **structured, processed data**. Schema is defined before loading (schema-on-write). Optimized for SQL analytics.
- **Data Lake** — stores **raw data in any format** — structured, semi-structured, unstructured. Schema is applied when reading (schema-on-read). Cheap storage, flexible.

| | Data Warehouse | Data Lake |
|---|---|---|
| Data type | Structured only | Any (JSON, CSV, images, logs) |
| Schema | Schema-on-write | Schema-on-read |
| Storage cost | Expensive | Cheap (S3, GCS, ADLS) |
| Query speed | Fast | Slower (without optimization) |
| Users | Analysts, BI tools | Data engineers, data scientists |
| Examples | Redshift, BigQuery, Snowflake | S3 + Athena, Azure Data Lake, GCS |

**Real-world flow:**

```
Raw Sources → [Data Lake: S3/GCS] → ETL/ELT → [Data Warehouse: Redshift/BigQuery] → BI Tools
```

> In modern architectures, the data lake is the landing zone — everything raw goes there first. Then we clean, transform, and load the relevant data into the warehouse for analytics. The lake is cheap and flexible; the warehouse is fast and structured.

---

### Q7. What is a data lakehouse?

**Answer:**

A data lakehouse is a **modern architecture that combines the best of both data lakes and data warehouses** — cheap, flexible storage of a data lake with the ACID transactions, schema enforcement, and query performance of a data warehouse.

It eliminates the need to maintain two separate systems.

```
Data Lake (raw storage: S3/GCS/ADLS)
        +
Warehouse features (ACID, schema, indexing, SQL)
        =
Data Lakehouse
```

**Key technologies that enable lakehouses:**
- **Delta Lake** (Databricks) — adds ACID transactions and versioning on top of Parquet files in S3.
- **Apache Iceberg** — open table format with schema evolution and time travel.
- **Apache Hudi** — supports upserts and incremental processing on data lakes.

**Benefits:**
- One storage layer for all workloads — BI, ML, streaming.
- ACID transactions on cheap object storage.
- Schema enforcement + schema evolution.
- Time travel — query data as it was at a point in time.
- No data duplication between lake and warehouse.

```python
# Delta Lake example — ACID upsert on S3
from delta.tables import DeltaTable

delta_table = DeltaTable.forPath(spark, "s3://my-bucket/orders")
delta_table.alias("target").merge(
    new_data.alias("source"),
    "target.order_id = source.order_id"
).whenMatchedUpdateAll().whenNotMatchedInsertAll().execute()
```

> Lakehouses are where the industry is heading. Platforms like Databricks and Snowflake are essentially lakehouse architectures. I've worked with Delta Lake on top of S3 — it gives you warehouse-quality reliability without the cost of a separate warehouse system.

---

### Q8. What is partitioning and bucketing in a data warehouse?

**Answer:**

Both are techniques to **physically organize data** for faster query performance — but they work differently.

**Partitioning:**
Divides a table into **separate physical segments** based on column values. When you query with a filter on the partition column, the engine skips irrelevant partitions entirely — called **partition pruning**.

```sql
-- BigQuery: partition by date
CREATE TABLE orders
PARTITION BY DATE(order_date)
AS SELECT * FROM raw_orders;

-- Query only scans the relevant partition
SELECT * FROM orders
WHERE order_date = '2024-01-15';  -- only reads Jan 15 partition
```

**Bucketing (Clustering):**
Divides data into a **fixed number of buckets** based on a hash of a column. Rows with the same hash go into the same bucket. Speeds up JOINs and GROUP BY on the bucketed column.

```sql
-- Hive/Spark: bucket by customer_id into 64 buckets
CREATE TABLE orders
CLUSTERED BY (customer_id) INTO 64 BUCKETS;

-- BigQuery: cluster by customer_id
CREATE TABLE orders
PARTITION BY DATE(order_date)
CLUSTER BY customer_id;
```

| | Partitioning | Bucketing/Clustering |
|---|---|---|
| Based on | Column value ranges | Hash of column values |
| Best for | Date/time range filters | JOIN and GROUP BY columns |
| Partition count | Dynamic (one per value) | Fixed number of buckets |
| Skips data | Entire partitions | Specific buckets |

> In practice, I always partition large tables by date — it's the most common filter in analytics queries. Then I add clustering on high-cardinality columns like customer_id or product_id that are frequently used in JOINs.

---

### Q9. How does columnar storage improve query performance?

**Answer:**

Traditional row-based storage stores all columns of a row together. Columnar storage stores each column separately on disk.

**Row storage:**
```
Row 1: [id=1, name=Alice, city=NYC, salary=90000]
Row 2: [id=2, name=Bob,   city=LA,  salary=85000]
```

**Columnar storage:**
```
id column:     [1, 2, 3, 4, ...]
name column:   [Alice, Bob, Carol, ...]
city column:   [NYC, LA, NYC, ...]
salary column: [90000, 85000, 95000, ...]
```

**Why it's faster for analytics:**

1. **Column pruning** — if your query only needs `salary` and `city`, the engine reads only those two columns. Row storage would read all columns for every row.

2. **Better compression** — columns contain the same data type and often similar values, so they compress much better (e.g., run-length encoding on repeated city names).

3. **Vectorized execution** — modern CPUs can process arrays of the same type much faster using SIMD instructions.

```sql
-- This query only needs to read 2 columns out of 50
SELECT AVG(salary) FROM employees WHERE city = 'NYC';

-- Row storage: reads all 50 columns for every row
-- Columnar storage: reads only salary + city columns → 40x less I/O
```

**Columnar formats used in data engineering:**
- **Parquet** — most common, used in Spark, Hive, BigQuery, Redshift Spectrum.
- **ORC** — optimized for Hive workloads.
- **Arrow** — in-memory columnar format used by Pandas and Spark.

> I always store data in Parquet format in S3 for this reason — it's columnar, compressed, and dramatically reduces both query time and cost in tools like Athena and BigQuery.

---

### Q10. What tools have you used for data warehousing? (Redshift, BigQuery, Snowflake)

**Answer:**

I've worked with the major cloud data warehouses — here's my honest take on each:

---

**Amazon Redshift:**
- Columnar, MPP (Massively Parallel Processing) warehouse on AWS.
- Great for large-scale SQL analytics, integrates natively with S3, Glue, and the AWS ecosystem.
- Key features: distribution keys, sort keys, Redshift Spectrum (query S3 directly).
- Best for: AWS-heavy shops with large structured datasets.

```sql
-- Redshift: define distribution and sort keys for performance
CREATE TABLE fact_sales (
    sale_id     BIGINT,
    customer_id INT,
    sale_date   DATE,
    revenue     DECIMAL(10,2)
)
DISTKEY(customer_id)
SORTKEY(sale_date);
```

---

**Google BigQuery:**
- Serverless, fully managed warehouse on GCP.
- No infrastructure to manage — just run SQL and pay per query.
- Extremely fast on large datasets due to Dremel engine and columnar storage.
- Key features: partitioning, clustering, streaming inserts, ML inside SQL.
- Best for: teams that want zero ops overhead and fast ad-hoc analytics.

```sql
-- BigQuery: partition + cluster for performance
CREATE TABLE `project.dataset.orders`
PARTITION BY DATE(order_date)
CLUSTER BY customer_id, product_id
AS SELECT * FROM `project.dataset.raw_orders`;
```

---

**Snowflake:**
- Cloud-agnostic (runs on AWS, GCP, Azure).
- Separates compute and storage — scale them independently.
- Virtual warehouses = compute clusters you can spin up/down instantly.
- Key features: zero-copy cloning, time travel, data sharing across accounts.
- Best for: multi-cloud environments, teams needing flexible compute scaling.

```sql
-- Snowflake: scale compute independently
ALTER WAREHOUSE my_warehouse SET WAREHOUSE_SIZE = 'X-LARGE';
-- Run heavy query
ALTER WAREHOUSE my_warehouse SET WAREHOUSE_SIZE = 'X-SMALL';
-- Scale back down to save cost
```

---

**Quick comparison:**

| | Redshift | BigQuery | Snowflake |
|---|---|---|---|
| Cloud | AWS only | GCP only | Multi-cloud |
| Pricing | Per cluster/hour | Per query (TB scanned) | Per compute second |
| Ops overhead | Medium | None (serverless) | Low |
| Best feature | AWS integration | Serverless speed | Compute/storage separation |

> My go-to is BigQuery for GCP projects and Snowflake for multi-cloud or when teams need flexible scaling. For AWS-native stacks, Redshift is the natural choice. The SQL is mostly standard across all three — the differences are in performance tuning and cost optimization.


