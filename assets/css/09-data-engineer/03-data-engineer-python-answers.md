## 🟢 2. Python / Programming

### Q1. How do you read a large CSV file without loading it into memory?

**Answer:**

When a CSV file is too large to fit in memory, I use **chunking** in Pandas or Python's built-in file iterator.

**Option 1 — Pandas `chunksize`:**

```python
import pandas as pd

for chunk in pd.read_csv('large_file.csv', chunksize=100_000):
    process(chunk)  # process each chunk independently
```

**Option 2 — Python built-in (line by line):**

```python
with open('large_file.csv', 'r') as f:
    for line in f:
        process(line)
```

**Option 3 — Use Dask for distributed processing:**

```python
import dask.dataframe as dd

df = dd.read_csv('large_file.csv')  # lazy, doesn't load into memory
result = df.groupby('category').amount.sum().compute()
```

> In data engineering, I prefer Dask or Spark for truly large files. For moderate sizes, Pandas chunking works well. The key idea is — **never load what you don't need to**.

---

### Q2. What is the difference between a list, tuple, set, and dictionary?

**Answer:**

These are the four core Python data structures — each has a specific use case:

| | List | Tuple | Set | Dictionary |
|---|---|---|---|---|
| Ordered | ✅ | ✅ | ❌ | ✅ (Python 3.7+) |
| Mutable | ✅ | ❌ | ✅ | ✅ |
| Duplicates | ✅ | ✅ | ❌ | Keys: ❌ |
| Syntax | `[1,2]` | `(1,2)` | `{1,2}` | `{"k":"v"}` |

- **List** — ordered, mutable, allows duplicates. Use for sequences you'll modify.
- **Tuple** — ordered, immutable. Use for fixed data like coordinates or DB rows.
- **Set** — unordered, no duplicates. Use for membership checks and deduplication.
- **Dictionary** — key-value pairs. Use for lookups and structured data.

```python
my_list = [1, 2, 2, 3]       # allows duplicates
my_tuple = (1, 2, 3)          # immutable
my_set = {1, 2, 2, 3}         # {1, 2, 3} — deduped
my_dict = {"name": "Alice"}   # key-value
```

> In data engineering, I use dicts heavily for config and lookups, sets for deduplication, and tuples for returning multiple values from functions.

---

### Q3. How do you handle missing values in a Pandas DataFrame?

**Answer:**

First I **detect** them, then decide whether to **drop**, **fill**, or **flag** them.

```python
import pandas as pd

df = pd.read_csv('data.csv')

# Detect
df.isnull().sum()           # count nulls per column
df.isnull().any(axis=1)     # rows with any null

# Drop
df.dropna()                          # drop rows with any null
df.dropna(subset=['email'])          # drop only if 'email' is null

# Fill
df.fillna(0)                         # fill with a constant
df.fillna(df.mean(numeric_only=True))  # fill with column mean
df['city'].fillna('Unknown')         # fill specific column

# Forward/backward fill (useful for time-series)
df.fillna(method='ffill')            # forward fill
df.fillna(method='bfill')            # backward fill
```

> In production pipelines, I never blindly drop nulls. I always check — is this null expected? Is it a data quality issue upstream? I usually flag them and log the count so the team can investigate.

---

### Q4. What are Python generators? Why are they useful in data engineering?

**Answer:**

A generator is a function that **yields values one at a time** instead of returning them all at once. It uses the `yield` keyword and is **lazy** — it only computes the next value when asked.

```python
# Regular function — loads everything into memory
def get_numbers(n):
    return [i for i in range(n)]   # creates full list

# Generator — produces one value at a time
def get_numbers_gen(n):
    for i in range(n):
        yield i                    # yields one at a time
```

**Why useful in data engineering:**

- **Memory efficient** — processes one record at a time, no matter how large the dataset.
- **Streaming** — perfect for reading files, API responses, or Kafka messages line by line.
- **Pipeline chaining** — generators can be chained together like a pipeline.

```python
def read_file(path):
    with open(path) as f:
        for line in f:
            yield line.strip()

def filter_empty(lines):
    for line in lines:
        if line:
            yield line

# Chain them — no memory overhead
for line in filter_empty(read_file('data.csv')):
    process(line)
```

> I use generators a lot when building lightweight ETL scripts that process large files row by row without spinning up Spark.

---

### Q5. How do you merge two DataFrames in Pandas?

**Answer:**

Pandas `merge()` works just like SQL JOINs. I use it all the time.

```python
import pandas as pd

customers = pd.DataFrame({'id': [1,2,3], 'name': ['Alice','Bob','Carol']})
orders    = pd.DataFrame({'customer_id': [1,1,2], 'amount': [100, 200, 150]})

# INNER JOIN (default)
pd.merge(customers, orders, left_on='id', right_on='customer_id')

# LEFT JOIN
pd.merge(customers, orders, left_on='id', right_on='customer_id', how='left')

# RIGHT JOIN
pd.merge(customers, orders, left_on='id', right_on='customer_id', how='right')

# FULL OUTER JOIN
pd.merge(customers, orders, left_on='id', right_on='customer_id', how='outer')
```

**If columns have the same name:**

```python
pd.merge(df1, df2, on='customer_id')   # shorthand when key name matches
```

**For index-based merge:**

```python
df1.join(df2, how='left')   # joins on index
```

> I always check for duplicate rows after a merge — if the key isn't unique on both sides, you can get a cartesian explosion. I validate with `df.shape` before and after.

---

### Q6. What is the difference between `map()`, `filter()`, and `reduce()`?

**Answer:**

All three are functional programming tools that operate on iterables:

- **`map()`** — applies a function to **every element**, returns transformed values.
- **`filter()`** — keeps only elements where the function returns **True**.
- **`reduce()`** — applies a function **cumulatively** to reduce to a single value (needs `functools`).

```python
from functools import reduce

numbers = [1, 2, 3, 4, 5]

# map: square every number
squared = list(map(lambda x: x**2, numbers))
# [1, 4, 9, 16, 25]

# filter: keep only even numbers
evens = list(filter(lambda x: x % 2 == 0, numbers))
# [2, 4]

# reduce: sum all numbers
total = reduce(lambda x, y: x + y, numbers)
# 15
```

> Honestly, in modern Python I mostly use list comprehensions instead of map/filter — they're more readable. But reduce is still useful for things like building cumulative totals or merging dicts. In Spark, map/filter/reduce are core operations on RDDs.

---

### Q7. How do you write a Python function to flatten a nested JSON?

**Answer:**

Nested JSONs are very common in data engineering — API responses, event logs, etc. I flatten them so they can be loaded into a table.

```python
def flatten_json(data, parent_key='', sep='_'):
    items = {}
    for key, value in data.items():
        new_key = f"{parent_key}{sep}{key}" if parent_key else key
        if isinstance(value, dict):
            items.update(flatten_json(value, new_key, sep))
        elif isinstance(value, list):
            for i, item in enumerate(value):
                if isinstance(item, dict):
                    items.update(flatten_json(item, f"{new_key}_{i}", sep))
                else:
                    items[f"{new_key}_{i}"] = item
        else:
            items[new_key] = value
    return items
```

**Example:**

```python
nested = {
    "user": {
        "name": "Alice",
        "address": {
            "city": "NYC",
            "zip": "10001"
        }
    },
    "score": 95
}

flatten_json(nested)
# {'user_name': 'Alice', 'user_address_city': 'NYC', 'user_address_zip': '10001', 'score': 95}
```

> In production I also use `pandas.json_normalize()` for simpler cases — it handles one level of nesting cleanly. For deeply nested or inconsistent schemas, I write a custom recursive function like this.

---

### Q8. What is list comprehension? Give an example.

**Answer:**

List comprehension is a **concise, readable way** to create a list in a single line. It replaces a for loop + append pattern.

```python
# Traditional loop
squares = []
for x in range(10):
    squares.append(x**2)

# List comprehension — same result, one line
squares = [x**2 for x in range(10)]
```

**With a condition (filter):**

```python
# Only even squares
even_squares = [x**2 for x in range(10) if x % 2 == 0]
# [0, 4, 16, 36, 64]
```

**Nested list comprehension:**

```python
# Flatten a 2D list
matrix = [[1,2],[3,4],[5,6]]
flat = [num for row in matrix for num in row]
# [1, 2, 3, 4, 5, 6]
```

**Dict comprehension (same idea):**

```python
word_lengths = {word: len(word) for word in ['apple', 'banana', 'cherry']}
# {'apple': 5, 'banana': 6, 'cherry': 6}
```

> I use list comprehensions constantly in data engineering for quick transformations — cleaning column names, parsing values, building lookup dicts. They're faster than loops and much more Pythonic.

---

### Q9. How do you connect to a database using Python (e.g., SQLAlchemy, psycopg2)?

**Answer:**

There are two common approaches — raw driver or ORM/abstraction layer.

**Option 1 — psycopg2 (raw PostgreSQL driver):**

```python
import psycopg2

conn = psycopg2.connect(
    host="localhost",
    database="mydb",
    user="<db_user>",
    password="<db_password>"
)

cursor = conn.cursor()
cursor.execute("SELECT * FROM orders WHERE status = %s", ('pending',))
rows = cursor.fetchall()

cursor.close()
conn.close()
```

**Option 2 — SQLAlchemy (works with any DB):**

```python
from sqlalchemy import create_engine
import pandas as pd

engine = create_engine("postgresql://<user>:<password>@localhost:5432/mydb")

# Read into DataFrame
df = pd.read_sql("SELECT * FROM orders", engine)

# Write DataFrame to DB
df.to_sql('orders_backup', engine, if_exists='replace', index=False)
```

**Option 3 — Using context manager (best practice):**

```python
with psycopg2.connect(host="localhost", database="mydb",
                      user="<user>", password="<password>") as conn:
    with conn.cursor() as cur:
        cur.execute("SELECT COUNT(*) FROM orders")
        print(cur.fetchone())
```

> In data engineering, I prefer SQLAlchemy with Pandas because `read_sql` and `to_sql` make it very easy to move data between databases and DataFrames. I always store credentials in environment variables or AWS Secrets Manager — never hardcoded.

---

### Q10. What are decorators in Python?

**Answer:**

A decorator is a function that **wraps another function** to add extra behavior — without modifying the original function's code. It uses the `@` syntax.

**Simple example — logging decorator:**

```python
import time

def timer(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        end = time.time()
        print(f"{func.__name__} took {end - start:.2f}s")
        return result
    return wrapper

@timer
def process_data():
    time.sleep(1)
    print("Processing done")

process_data()
# Processing done
# process_data took 1.00s
```

**Common built-in decorators:**

```python
@staticmethod    # method doesn't need self
@classmethod     # method gets class as first arg
@property        # access method like an attribute
```

**Real-world data engineering use cases:**

```python
# Retry decorator for flaky API calls
def retry(times=3):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for attempt in range(times):
                try:
                    return func(*args, **kwargs)
                except Exception as e:
                    print(f"Attempt {attempt+1} failed: {e}")
            raise Exception("All retries failed")
        return wrapper
    return decorator

@retry(times=3)
def fetch_from_api(url):
    # ... API call
    pass
```

> I use decorators in data pipelines for logging, timing, retry logic, and authentication checks. They keep the core business logic clean and separate from cross-cutting concerns.


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


