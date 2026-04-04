## 🟢 10. Data Modeling

## 1. What is data modeling? Why is it important?

Data modeling is the process of defining how data is structured, stored, and related within a system. You're essentially creating a blueprint of your data before you build anything.

It's important because:
- It ensures everyone — engineers, analysts, business stakeholders — has a shared understanding of the data.
- It prevents costly redesigns later. A bad model early means painful migrations at scale.
- It directly impacts query performance, data quality, and maintainability.

Think of it like architecture blueprints before construction. You don't start building without a plan.

---

## 2. What is the difference between conceptual, logical, and physical data models?

Three levels of abstraction, each for a different audience:

| Level | Focus | Audience | Example |
|-------|-------|----------|---------|
| **Conceptual** | What entities exist and how they relate | Business stakeholders | Customer places Order |
| **Logical** | Attributes, data types, relationships — no DB-specific details | Architects / Analysts | Customer(id, name, email) → Order(id, customer_id, date) |
| **Physical** | Actual table definitions, indexes, partitions, storage | DBAs / Engineers | `CREATE TABLE orders (id BIGINT PRIMARY KEY, ...)` |

You start conceptual, refine to logical, then implement as physical.

---

## 3. When would you use a relational model vs a document model?

**Relational model** (SQL — PostgreSQL, MySQL, Redshift):
- Data is structured and consistent.
- You need joins across multiple entities.
- Strong consistency and ACID transactions matter.
- Example: banking, ERP, order management.

**Document model** (NoSQL — MongoDB, DynamoDB, Firestore):
- Data is semi-structured or schema varies per record.
- You read/write a whole document at once — no joins needed.
- You need horizontal scale and flexible schema.
- Example: user profiles, product catalogs, event logs.

Rule of thumb: if your data fits neatly into tables with clear relationships, go relational. If it's nested, variable, or document-like, go document model.

---

## 4. What is an entity-relationship (ER) diagram?

An ER diagram is a visual representation of the entities in a system and how they relate to each other.

Key components:
- **Entity** — a real-world object, like `Customer`, `Order`, `Product`. Represented as a rectangle.
- **Attribute** — a property of an entity, like `customer_name` or `order_date`. Represented as an oval.
- **Relationship** — how entities connect, like "Customer *places* Order". Represented as a diamond.
- **Cardinality** — defines the nature of the relationship: one-to-one, one-to-many, many-to-many.

ER diagrams are the starting point for designing a relational database schema.

---

## 5. How do you model many-to-many relationships?

You can't directly represent many-to-many in a relational database — you need a **junction table** (also called a bridge or associative table).

Example: A `Student` can enroll in many `Courses`, and a `Course` can have many `Students`.

```sql
-- Junction table
CREATE TABLE student_course (
    student_id INT REFERENCES students(id),
    course_id  INT REFERENCES courses(id),
    enrolled_at DATE,
    PRIMARY KEY (student_id, course_id)
);
```

The junction table holds the foreign keys of both entities plus any relationship-specific attributes like `enrolled_at`.

---

## 6. What is a surrogate key vs a natural key?

**Natural key** — a key that comes from the real-world data itself. It has business meaning.
- Example: `email`, `SSN`, `passport_number`, `product_SKU`.
- Problem: natural keys can change, be duplicated across systems, or be too long for joins.

**Surrogate key** — a system-generated key with no business meaning. Usually an auto-increment integer or UUID.
- Example: `id BIGINT AUTO_INCREMENT` or `id UUID DEFAULT gen_random_uuid()`.
- Preferred in most data warehouses because it's stable, compact, and system-controlled.

In data warehousing, surrogate keys are standard — especially for SCD Type 2 where the same business entity can have multiple rows over time.

---

## 7. How do you model time-series data?

Time-series data is a sequence of values recorded at regular or irregular time intervals — metrics, sensor readings, stock prices, logs.

Best practices:
- **Partition by time** — partition tables by day, month, or hour so queries only scan relevant time ranges.
- **Use a time-series database** — InfluxDB, TimescaleDB (Postgres extension), or Amazon Timestream are purpose-built for this.
- **Columnar storage** — store in Parquet/ORC on S3 partitioned by date for analytical workloads.
- **Downsampling** — aggregate old data (e.g., minute-level → hourly → daily) to reduce storage over time.

Example schema:
```sql
CREATE TABLE sensor_readings (
    sensor_id   INT,
    recorded_at TIMESTAMP,
    value       FLOAT,
    PRIMARY KEY (sensor_id, recorded_at)
) PARTITION BY RANGE (recorded_at);
```

---

## 8. What is a graph data model? When would you use it?

A graph data model represents data as **nodes** (entities) and **edges** (relationships), where relationships are first-class citizens — not just foreign keys.

When to use it:
- **Social networks** — users follow users, users like posts.
- **Fraud detection** — finding connected accounts or suspicious transaction chains.
- **Recommendation engines** — "people who bought X also bought Y".
- **Knowledge graphs** — complex interconnected concepts.
- **Network/IT topology** — servers connected to routers connected to switches.

Graph databases: **Neo4j**, **Amazon Neptune**, **TigerGraph**.

The key advantage: traversing deep relationships (e.g., friends of friends of friends) is extremely fast in a graph DB, but would require expensive recursive joins in SQL.

---

## 9. How do you handle hierarchical data in SQL?

Hierarchical data has parent-child relationships — org charts, category trees, file systems, bill of materials.

Three common approaches:

**1. Adjacency List** — each row stores its parent_id. Simple but requires recursive queries.
```sql
SELECT * FROM categories
WHERE parent_id = 5;
```

**2. Recursive CTE** — traverse the hierarchy using `WITH RECURSIVE`.
```sql
WITH RECURSIVE org_tree AS (
    SELECT id, name, manager_id, 1 AS level
    FROM employees WHERE manager_id IS NULL
    UNION ALL
    SELECT e.id, e.name, e.manager_id, ot.level + 1
    FROM employees e
    JOIN org_tree ot ON e.manager_id = ot.id
)
SELECT * FROM org_tree;
```

**3. Nested Sets / Materialized Path** — store left/right bounds or full path string (`/root/parent/child`). Faster reads, more complex writes.

For most use cases, recursive CTEs with adjacency list is the cleanest approach.

---

## 10. What is data vault modeling?

Data Vault is a modeling methodology designed for enterprise data warehouses that need to handle change, scale, and auditability over time.

It has three core table types:

| Type | Purpose | Example |
|------|---------|---------|
| **Hub** | Stores unique business keys | `hub_customer` — just customer_id + load date |
| **Link** | Stores relationships between hubs | `link_order_customer` — connects order and customer hubs |
| **Satellite** | Stores descriptive attributes and history | `sat_customer_details` — name, email, address with timestamps |

Key advantages:
- **Fully auditable** — every record has a load timestamp and source system.
- **Handles change well** — adding a new source or attribute doesn't break existing tables.
- **Parallel loading** — hubs, links, and satellites can be loaded independently.

It's more complex than Star Schema but much better for large enterprises with multiple source systems and strict audit requirements. Tools like **dbt** and **dbtvault** make it more practical to implement.

---
