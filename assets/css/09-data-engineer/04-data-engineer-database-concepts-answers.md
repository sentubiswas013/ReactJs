## 🟢 3. Database Concepts

### Q1. What is the difference between SQL and NoSQL databases?

**Answer:**

The core difference is **structure and scalability**.

- **SQL (Relational)** — data is stored in tables with rows and columns. Schema is fixed upfront. Uses SQL to query. Strong consistency. Examples: PostgreSQL, MySQL, Oracle.
- **NoSQL (Non-Relational)** — data is stored in flexible formats like documents, key-value, wide-column, or graphs. Schema is dynamic. Scales horizontally. Examples: MongoDB, Cassandra, DynamoDB, Redis.

| | SQL | NoSQL |
|---|---|---|
| Schema | Fixed | Flexible |
| Scaling | Vertical | Horizontal |
| Consistency | Strong (ACID) | Eventual (BASE) |
| Best for | Structured, relational data | Unstructured, high-volume data |
| Query language | SQL | Varies by DB |

**When to use which:**
- SQL → financial systems, ERP, anything needing joins and transactions.
- NoSQL → user activity logs, product catalogs, real-time feeds, IoT data.

> In data engineering, I often use both — SQL databases as the source of truth and NoSQL for high-throughput ingestion or caching layers.

---

### Q2. Explain ACID properties.

**Answer:**

ACID is a set of four properties that guarantee database transactions are processed reliably.

- **A — Atomicity** — a transaction is all or nothing. If one step fails, the entire transaction is rolled back. No partial updates.
- **C — Consistency** — a transaction brings the database from one valid state to another. All rules, constraints, and cascades are respected.
- **I — Isolation** — concurrent transactions don't interfere with each other. Each transaction runs as if it's the only one.
- **D — Durability** — once a transaction is committed, it stays committed — even if the system crashes. Data is written to disk.

**Real-world example:**
> Bank transfer — debit $100 from Account A, credit $100 to Account B. If the credit fails, the debit must be rolled back. That's Atomicity. Both accounts must remain in a valid state — that's Consistency.

```sql
BEGIN;
  UPDATE accounts SET balance = balance - 100 WHERE id = 1;
  UPDATE accounts SET balance = balance + 100 WHERE id = 2;
COMMIT;  -- both succeed, or neither happens
```

> ACID is why relational databases are still the go-to for financial and transactional systems. NoSQL databases often trade some ACID guarantees for speed and scale.

---

### Q3. What is normalization? Explain 1NF, 2NF, 3NF.

**Answer:**

Normalization is the process of **organizing a database to reduce redundancy and improve data integrity**. We do it by splitting data into related tables.

**1NF — First Normal Form:**
- Each column must have **atomic (indivisible) values** — no arrays or comma-separated lists in a cell.
- Each row must be unique.

```
❌ Bad:  | customer | phones          |
         | Alice    | 111, 222, 333   |

✅ Good: | customer | phone |
         | Alice    | 111   |
         | Alice    | 222   |
```

**2NF — Second Normal Form:**
- Must be in 1NF.
- Every non-key column must depend on the **entire primary key** — no partial dependencies.
- Applies when you have a composite primary key.

```
❌ Bad:  | order_id | product_id | product_name | qty |
         product_name depends only on product_id, not the full key

✅ Good: Split into Orders table and Products table
```

**3NF — Third Normal Form:**
- Must be in 2NF.
- No **transitive dependencies** — non-key columns should not depend on other non-key columns.

```
❌ Bad:  | emp_id | dept_id | dept_name |
         dept_name depends on dept_id, not emp_id

✅ Good: Split into Employees table and Departments table
```

> In data engineering, source OLTP systems are usually normalized. When we load into a data warehouse, we often denormalize for query performance.

---

### Q4. What is denormalization and when would you use it?

**Answer:**

Denormalization is the **intentional introduction of redundancy** into a database by combining tables or duplicating data — the opposite of normalization.

Instead of joining multiple tables at query time, you pre-join and store the result.

```sql
-- Normalized: need a JOIN every time
SELECT o.order_id, c.name, p.product_name
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id;

-- Denormalized: everything in one table, no JOIN needed
SELECT order_id, customer_name, product_name
FROM orders_flat;
```

**When to use denormalization:**
- **Data warehouses and analytics** — read-heavy workloads where JOIN performance matters.
- **Reporting tables** — pre-aggregated or pre-joined tables for dashboards.
- **NoSQL databases** — document stores like MongoDB encourage denormalization.
- **High-read, low-write** scenarios.

**Trade-offs:**
- Faster reads ✅
- More storage used ❌
- Data updates are harder — must update in multiple places ❌

> In data engineering, I denormalize when building the serving layer of a data warehouse — fact tables with dimension data already embedded. It makes BI tools and analysts much faster.

---

### Q5. What is a primary key vs foreign key?

**Answer:**

- **Primary Key** — uniquely identifies each row in a table. Cannot be NULL. Only one per table.
- **Foreign Key** — a column in one table that references the primary key of another table. It enforces referential integrity — you can't have an order for a customer that doesn't exist.

```sql
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,   -- primary key
    name        VARCHAR(100)
);

CREATE TABLE orders (
    order_id    INT PRIMARY KEY,
    customer_id INT,
    amount      DECIMAL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)  -- foreign key
);
```

**Key differences:**

| | Primary Key | Foreign Key |
|---|---|---|
| Purpose | Uniquely identify a row | Link to another table |
| NULL allowed | ❌ | ✅ (optional relationship) |
| Count per table | Only one | Can have many |
| Uniqueness | Always unique | Not required to be unique |

> Foreign keys enforce data integrity at the database level. In high-throughput data pipelines, some teams disable FK constraints for performance and enforce integrity in the application layer instead — but that's a trade-off you need to be aware of.

---

### Q6. What is a transaction? How do you handle rollback?

**Answer:**

A transaction is a **unit of work** that groups one or more SQL operations together. Either all operations succeed and are committed, or if anything fails, everything is rolled back to the previous state.

**Basic transaction flow:**

```sql
BEGIN;                          -- start transaction

UPDATE inventory SET qty = qty - 1 WHERE product_id = 101;
INSERT INTO orders (product_id, qty) VALUES (101, 1);

COMMIT;                         -- save all changes permanently
```

**Handling rollback on failure:**

```sql
BEGIN;

UPDATE inventory SET qty = qty - 1 WHERE product_id = 101;

-- Something goes wrong
ROLLBACK;                       -- undo everything since BEGIN
```

**In Python with psycopg2:**

```python
import psycopg2

conn = psycopg2.connect(...)
try:
    cur = conn.cursor()
    cur.execute("UPDATE inventory SET qty = qty - 1 WHERE product_id = 101")
    cur.execute("INSERT INTO orders (product_id, qty) VALUES (101, 1)")
    conn.commit()               # all good, commit
except Exception as e:
    conn.rollback()             # something failed, rollback
    print(f"Transaction failed: {e}")
finally:
    conn.close()
```

**Savepoints — partial rollback:**

```sql
BEGIN;
  INSERT INTO logs VALUES ('step1');
  SAVEPOINT sp1;
  INSERT INTO logs VALUES ('step2');
  ROLLBACK TO sp1;              -- undo only step2, keep step1
COMMIT;
```

> In data pipelines, I always wrap multi-step DB operations in transactions. If I'm loading data in batches, I commit per batch so a failure doesn't wipe out all progress.

---

### Q7. What is the difference between OLTP and OLAP?

**Answer:**

OLTP and OLAP are two completely different types of database workloads.

- **OLTP (Online Transaction Processing)** — handles day-to-day operations. Many small, fast read/write transactions. Optimized for writes. Highly normalized.
- **OLAP (Online Analytical Processing)** — handles analytics and reporting. Few large, complex queries over historical data. Optimized for reads. Denormalized.

| | OLTP | OLAP |
|---|---|---|
| Purpose | Run the business | Analyze the business |
| Operations | INSERT, UPDATE, DELETE | SELECT with aggregations |
| Data volume | Current data, GBs | Historical data, TBs–PBs |
| Schema | Normalized (3NF) | Denormalized (Star/Snowflake) |
| Response time | Milliseconds | Seconds to minutes |
| Users | App users, developers | Analysts, data scientists |
| Examples | MySQL, PostgreSQL | Redshift, BigQuery, Snowflake |

**Real-world example:**
> When you place an order on Amazon, that hits an OLTP database. When Amazon's analytics team runs a report on "total sales by region last quarter", that hits an OLAP system.

> As a data engineer, my job is often to **move data from OLTP to OLAP** — that's the ETL pipeline. OLTP is the source, OLAP is the destination.

---

### Q8. What are stored procedures and when should you use them?

**Answer:**

A stored procedure is a **precompiled block of SQL code** stored in the database that can be executed by name. It can accept parameters, contain logic, and return results.

```sql
-- Create a stored procedure
CREATE OR REPLACE PROCEDURE update_order_status(
    p_order_id INT,
    p_status   VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE orders
    SET status = p_status, updated_at = NOW()
    WHERE order_id = p_order_id;

    INSERT INTO audit_log (order_id, action, ts)
    VALUES (p_order_id, 'STATUS_UPDATED', NOW());
END;
$$;

-- Execute it
CALL update_order_status(1001, 'shipped');
```

**When to use stored procedures:**
- Encapsulating complex, multi-step business logic in the DB.
- Batch operations that run on a schedule.
- When you want to restrict direct table access and expose only procedures.
- Reducing network round-trips for multi-step operations.

**When NOT to use them:**
- When logic needs to be version-controlled and tested like application code.
- When you need portability across different databases.
- In modern data engineering, most teams prefer dbt or Python-based transformations over stored procedures.

> Personally, I use stored procedures sparingly in data engineering. They're hard to test, version control, and debug. I prefer dbt models or Python for transformations — they live in Git and are much easier to maintain.

---

### Q9. What is a materialized view vs a regular view?

**Answer:**

Both are virtual tables based on a query — the difference is **whether the result is stored or computed on the fly**.

- **Regular View** — just a saved SQL query. Every time you query it, it runs the underlying query fresh. No data is stored.
- **Materialized View** — the query result is **physically stored** on disk. It's like a cached snapshot. Must be refreshed to get updated data.

```sql
-- Regular view: no storage, always fresh
CREATE VIEW active_customers AS
SELECT * FROM customers WHERE status = 'active';

-- Materialized view: stored result, faster reads
CREATE MATERIALIZED VIEW monthly_sales AS
SELECT DATE_TRUNC('month', order_date) AS month,
       SUM(amount) AS total_sales
FROM orders
GROUP BY 1;

-- Refresh when needed
REFRESH MATERIALIZED VIEW monthly_sales;
```

| | Regular View | Materialized View |
|---|---|---|
| Data stored | ❌ | ✅ |
| Query speed | Slower (re-runs query) | Faster (pre-computed) |
| Always fresh | ✅ | ❌ (needs refresh) |
| Storage cost | None | Yes |

**When to use materialized views:**
- Expensive aggregation queries that run frequently.
- Dashboard queries that don't need real-time data.
- Pre-computing joins across large tables.

> In data warehouses like Redshift and BigQuery, materialized views are a great way to speed up reporting without duplicating full pipeline logic. I use them for summary tables that analysts query constantly.

---

### Q10. What is database sharding?

**Answer:**

Sharding is a **horizontal scaling technique** where a large database is split into smaller pieces called **shards**, each stored on a separate server. Each shard holds a subset of the data.

Instead of one giant table with 1 billion rows on one server, you split it across 10 servers — each holding 100 million rows.

**How sharding works:**

```
Total Data: 1 billion users

Shard 1: users where user_id % 4 = 0   → Server 1
Shard 2: users where user_id % 4 = 1   → Server 2
Shard 3: users where user_id % 4 = 2   → Server 3
Shard 4: users where user_id % 4 = 3   → Server 4
```

**Common sharding strategies:**
- **Range-based** — shard by value range (e.g., A–F on shard 1, G–M on shard 2).
- **Hash-based** — apply a hash function to the key to distribute evenly.
- **Geography-based** — US users on US servers, EU users on EU servers.

**Benefits:**
- Handles massive scale — more data, more servers.
- Improved read/write throughput per shard.

**Challenges:**
- **Cross-shard queries** are expensive — joining data across shards is hard.
- **Rebalancing** — if one shard gets too large, redistributing is complex.
- Application complexity increases — the app must know which shard to query.

> In data engineering, I've worked with sharded MySQL and Cassandra setups. The key lesson is — choose your shard key carefully. A bad shard key causes **hotspots** where one shard gets all the traffic while others sit idle.


