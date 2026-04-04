## 🟢 1. SQL (Most Important ⭐)

### Q1. What is the difference between `INNER JOIN`, `LEFT JOIN`, `RIGHT JOIN`, and `FULL OUTER JOIN`?

**Answer:**

So basically, all JOINs are about combining rows from two tables — the difference is just *which rows* you keep.

- **INNER JOIN** — gives you only the rows that have a match in **both** tables. If there's no match, the row is dropped.
- **LEFT JOIN** — gives you **all rows from the left table**, and matching rows from the right. If no match, right side is NULL.
- **RIGHT JOIN** — opposite of LEFT JOIN. All rows from the **right table**, matching from left. If no match, left side is NULL.
- **FULL OUTER JOIN** — gives you **all rows from both tables**. Where there's no match on either side, you get NULLs.

**Real-world example:**
> If I have a `customers` table and an `orders` table, and I want all customers even if they haven't placed an order — I use a LEFT JOIN. If I use INNER JOIN, customers with no orders won't show up.

```sql
-- INNER JOIN: only matched rows
SELECT c.name, o.order_id
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id;

-- LEFT JOIN: all customers, even without orders
SELECT c.name, o.order_id
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id;
```

---

### Q2. What is a window function? Explain `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`.

**Answer:**

A window function performs a calculation **across a set of rows related to the current row** — without collapsing them like GROUP BY does. You still see every row, but you get an extra computed column.

The three ranking functions:

- **ROW_NUMBER()** — assigns a unique sequential number to each row. No ties — even if two rows are equal, they get different numbers.
- **RANK()** — assigns the same rank to ties, but **skips the next rank**. So if two rows are rank 1, the next is rank 3.
- **DENSE_RANK()** — same as RANK for ties, but **does NOT skip**. Two rows rank 1, next is rank 2.

**Example:**

```sql
SELECT
  name,
  salary,
  ROW_NUMBER() OVER (ORDER BY salary DESC) AS row_num,
  RANK()       OVER (ORDER BY salary DESC) AS rnk,
  DENSE_RANK() OVER (ORDER BY salary DESC) AS dense_rnk
FROM employees;
```

| name  | salary | row_num | rnk | dense_rnk |
|-------|--------|---------|-----|-----------|
| Alice | 9000   | 1       | 1   | 1         |
| Bob   | 9000   | 2       | 1   | 1         |
| Carol | 8000   | 3       | 3   | 2         |

> I use DENSE_RANK most often in data engineering when I need to find top-N records per group without gaps.

---

### Q3. What is the difference between `WHERE` and `HAVING`?

**Answer:**

Simple rule: **WHERE filters rows before aggregation, HAVING filters after aggregation.**

- `WHERE` works on individual rows — it runs before `GROUP BY`.
- `HAVING` works on grouped results — it runs after `GROUP BY`.

You **cannot** use aggregate functions like `SUM()`, `COUNT()` in a WHERE clause — that's what HAVING is for.

**Example:**

```sql
-- WHERE: filter before grouping
SELECT department, COUNT(*) AS emp_count
FROM employees
WHERE status = 'active'         -- filters rows first
GROUP BY department
HAVING COUNT(*) > 5;            -- then filters groups
```

> Think of it this way: WHERE is a row-level filter, HAVING is a group-level filter.

---

### Q4. How do you find duplicate records in a table?

**Answer:**

I use `GROUP BY` with `HAVING COUNT(*) > 1` to find duplicates.

```sql
-- Find duplicate emails
SELECT email, COUNT(*) AS cnt
FROM users
GROUP BY email
HAVING COUNT(*) > 1;
```

If I want to see the full duplicate rows, I use a CTE or subquery:

```sql
WITH dupes AS (
  SELECT id, email,
         ROW_NUMBER() OVER (PARTITION BY email ORDER BY id) AS rn
  FROM users
)
SELECT * FROM dupes WHERE rn > 1;
```

> The second approach is also useful when you want to **delete** duplicates — you just delete where `rn > 1`.

---

### Q5. What is a CTE (Common Table Expression)? How is it different from a subquery?

**Answer:**

A CTE is a **named temporary result set** defined using the `WITH` keyword. It exists only for the duration of the query.

**Key differences from a subquery:**

| | CTE | Subquery |
|---|---|---|
| Readability | Much cleaner, named | Can get deeply nested |
| Reusability | Can reference the same CTE multiple times | Must repeat the subquery |
| Recursion | Supports recursive queries | Does not |
| Performance | Similar in most databases | Similar |

```sql
-- Subquery approach
SELECT * FROM (
  SELECT name, salary, RANK() OVER (ORDER BY salary DESC) AS rnk
  FROM employees
) ranked
WHERE rnk <= 3;

-- CTE approach (cleaner)
WITH ranked AS (
  SELECT name, salary, RANK() OVER (ORDER BY salary DESC) AS rnk
  FROM employees
)
SELECT * FROM ranked WHERE rnk <= 3;
```

> I prefer CTEs in data engineering because pipelines and transformations get complex — named blocks make the logic much easier to follow and debug.

---

### Q6. Explain `GROUP BY` vs `PARTITION BY`.

**Answer:**

Both are used to group data, but they work very differently:

- **GROUP BY** — **collapses** rows into one row per group. You lose the individual rows.
- **PARTITION BY** — used inside window functions. It **divides** rows into groups but **keeps all rows**. Each row gets a computed value based on its partition.

```sql
-- GROUP BY: one row per department
SELECT department, AVG(salary) AS avg_sal
FROM employees
GROUP BY department;

-- PARTITION BY: all rows kept, avg shown per department
SELECT name, department, salary,
       AVG(salary) OVER (PARTITION BY department) AS dept_avg
FROM employees;
```

> Use GROUP BY when you want summary/aggregated output. Use PARTITION BY when you want to keep all rows but add a calculated column based on a group.

---

### Q7. How do you find the second highest salary?

**Answer:**

There are a few ways. My preferred approach uses `DENSE_RANK()` because it handles ties correctly:

```sql
WITH ranked AS (
  SELECT salary,
         DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
  FROM employees
)
SELECT salary FROM ranked WHERE rnk = 2;
```

Another classic approach without window functions:

```sql
SELECT MAX(salary)
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```

> I prefer the window function approach because it's easy to extend — if they ask for the 3rd or Nth highest, I just change the rank number.

---

### Q8. What is indexing? How does it improve query performance?

**Answer:**

An index is a **data structure** (usually a B-tree) that the database maintains separately to allow fast lookups — similar to an index at the back of a book.

Without an index, the database does a **full table scan** — reads every row. With an index on a column, it can jump directly to the relevant rows.

**How it helps:**
- Speeds up `WHERE`, `JOIN`, `ORDER BY`, and `GROUP BY` on indexed columns.
- Dramatically reduces query time on large tables.

**Trade-offs:**
- Indexes take up **extra storage**.
- They **slow down writes** (INSERT/UPDATE/DELETE) because the index must be updated too.

```sql
-- Create an index
CREATE INDEX idx_orders_customer_id ON orders(customer_id);

-- Now this query is fast
SELECT * FROM orders WHERE customer_id = 101;
```

> In data engineering, I'm careful about over-indexing on tables that have heavy write loads — like staging tables in ETL pipelines.

---

### Q9. What is the difference between `UNION` and `UNION ALL`?

**Answer:**

Both combine results from two SELECT queries — the difference is about **duplicates**:

- **UNION** — removes duplicate rows from the combined result. It runs a DISTINCT internally, which costs extra processing.
- **UNION ALL** — keeps **all rows including duplicates**. It's faster because no deduplication step.

```sql
-- UNION: removes duplicates
SELECT city FROM customers
UNION
SELECT city FROM suppliers;

-- UNION ALL: keeps duplicates, faster
SELECT city FROM customers
UNION ALL
SELECT city FROM suppliers;
```

> In data pipelines, I almost always use `UNION ALL` unless I specifically need deduplication — it's faster and more predictable. If I need distinct results, I handle it explicitly with a `SELECT DISTINCT` wrapper.

**Rules for both:**
- Same number of columns in each SELECT.
- Columns must have compatible data types.

---

### Q10. Write a query to find customers who placed orders in the last 30 days.

**Answer:**

```sql
SELECT DISTINCT c.customer_id, c.name, c.email
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '30 days';
```

Or using a subquery approach:

```sql
SELECT customer_id, name, email
FROM customers
WHERE customer_id IN (
  SELECT DISTINCT customer_id
  FROM orders
  WHERE order_date >= CURRENT_DATE - INTERVAL '30 days'
);
```

> I use `CURRENT_DATE - INTERVAL '30 days'` for portability. In SQL Server it would be `DATEADD(DAY, -30, GETDATE())`, in MySQL `DATE_SUB(NOW(), INTERVAL 30 DAY)`. I always clarify the database dialect in interviews.

**If they ask for count of orders too:**

```sql
SELECT c.customer_id, c.name, COUNT(o.order_id) AS order_count
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY c.customer_id, c.name;
```

