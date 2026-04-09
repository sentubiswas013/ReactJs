# SQL — Interview Answers

---

## 1. What are the Types of SQL JOINs?

JOINs combine rows from two or more tables based on a related column.

- **INNER JOIN** — returns only matching rows from both tables
- **LEFT JOIN** — returns all rows from the left table + matching rows from right (nulls if no match)
- **RIGHT JOIN** — returns all rows from the right table + matching from left
- **FULL OUTER JOIN** — returns all rows from both tables (nulls where no match)

```sql
-- INNER JOIN
SELECT e.name, d.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id;

-- LEFT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
LEFT JOIN department d ON e.dept_id = d.id;

-- FULL OUTER JOIN
SELECT e.name, d.name FROM employee e
FULL OUTER JOIN department d ON e.dept_id = d.id;
```

Think of it as a Venn diagram — INNER is the overlap, LEFT keeps the left circle, FULL keeps both circles.

---

## 2. What is the Difference Between WHERE and HAVING?

Both filter rows — but at different stages.

- **WHERE** — filters rows **before** grouping (works on raw rows)
- **HAVING** — filters groups **after** `GROUP BY` (works on aggregated results)

```sql
-- WHERE: filter before grouping
SELECT dept_id, COUNT(*) FROM employee
WHERE salary > 50000
GROUP BY dept_id;

-- HAVING: filter after grouping
SELECT dept_id, COUNT(*) as total FROM employee
GROUP BY dept_id
HAVING COUNT(*) > 5;
```

Simple rule: if you're filtering on an aggregate function like `COUNT`, `SUM`, `AVG` — use `HAVING`. Otherwise use `WHERE`.

---

## 3. What is GROUP BY and ORDER BY?

- **GROUP BY** — groups rows with the same value into summary rows. Used with aggregate functions like `COUNT`, `SUM`, `AVG`.
- **ORDER BY** — sorts the result set by one or more columns (ASC by default, or DESC).

```sql
-- GROUP BY: count employees per department
SELECT dept_id, COUNT(*) as total
FROM employee
GROUP BY dept_id;

-- ORDER BY: sort by salary descending
SELECT name, salary FROM employee
ORDER BY salary DESC;

-- Combined
SELECT dept_id, AVG(salary) as avg_sal
FROM employee
GROUP BY dept_id
ORDER BY avg_sal DESC;
```

`GROUP BY` collapses rows. `ORDER BY` just sorts them.

---

## 4. What is Database Indexing and When to Use It?

An index is like a book's table of contents — it lets the database find rows fast without scanning the whole table.

**When to use:**
- Columns in `WHERE`, `JOIN`, `ORDER BY`, `GROUP BY`
- Foreign key columns
- High-cardinality columns (many unique values like email, ID)

**When NOT to use:**
- Small tables
- Columns updated very frequently
- Low-cardinality columns (like a boolean `is_active`)

```sql
CREATE INDEX idx_employee_email ON employee(email);

-- Composite index
CREATE INDEX idx_dept_salary ON employee(dept_id, salary);
```

Indexes speed up reads but slow down writes (INSERT/UPDATE/DELETE). Use them wisely — don't over-index.

---

## 5. What is the Difference Between Stored Procedure and Function?

Both are reusable SQL blocks — but with key differences.

| | Stored Procedure | Function |
|---|---|---|
| Returns | Optional (0 or more values via OUT params) | Must return a single value |
| Can use in SELECT | No | Yes |
| Can have DML (INSERT/UPDATE) | Yes | Limited (depends on DB) |
| Called with | `CALL` / `EXEC` | Used in expressions |
| Transaction control | Yes | No |

```sql
-- Stored Procedure
CREATE PROCEDURE get_employee(IN emp_id INT)
BEGIN
  SELECT * FROM employee WHERE id = emp_id;
END;

CALL get_employee(1);

-- Function
CREATE FUNCTION get_salary(emp_id INT) RETURNS DECIMAL
BEGIN
  DECLARE sal DECIMAL;
  SELECT salary INTO sal FROM employee WHERE id = emp_id;
  RETURN sal;
END;

SELECT get_salary(1);  -- used inline
```

Use a **function** when you need a return value in a query. Use a **procedure** for business logic, batch operations, or multiple operations.

---

## 6. What is Normalization? Types (1NF, 2NF, 3NF)?

Normalization is the process of organizing a database to **reduce redundancy** and **improve data integrity**.

**1NF (First Normal Form):**
- Each column has atomic (indivisible) values
- No repeating groups or arrays in a column

```
❌ Bad:  | id | name  | phones          |
         | 1  | Alice | 111, 222, 333   |

✅ Good: | id | name  | phone |
         | 1  | Alice | 111   |
         | 1  | Alice | 222   |
```

**2NF (Second Normal Form):**
- Must be in 1NF
- No partial dependency — every non-key column must depend on the **whole** primary key (applies to composite keys)

**3NF (Third Normal Form):**
- Must be in 2NF
- No transitive dependency — non-key columns must not depend on other non-key columns

```
❌ Bad:  | emp_id | dept_id | dept_name |
         dept_name depends on dept_id, not emp_id

✅ Fix: Split into employee(emp_id, dept_id) and department(dept_id, dept_name)
```

In practice, aim for 3NF. Sometimes you intentionally denormalize for performance.

---

## 7. What is the Difference Between DELETE, TRUNCATE, and DROP?

All three remove data — but very differently.

| | DELETE | TRUNCATE | DROP |
|---|---|---|---|
| Removes | Specific rows | All rows | Entire table + structure |
| WHERE clause | Yes | No | No |
| Rollback | Yes (logged) | No (or limited) | No |
| Triggers fired | Yes | No | No |
| Resets auto-increment | No | Yes | Yes |

```sql
DELETE FROM employee WHERE id = 5;     -- removes one row, can rollback

TRUNCATE TABLE employee;               -- removes all rows, fast, no rollback

DROP TABLE employee;                   -- removes table entirely
```

Use `DELETE` for selective removal. `TRUNCATE` to clear a table fast. `DROP` only when you want to remove the table completely.

---

## 8. What is a Subquery vs a JOIN?

Both retrieve related data — but differently.

**Subquery** — a query nested inside another query. Runs separately, result is used by the outer query.

```sql
-- Subquery: find employees earning more than average
SELECT name FROM employee
WHERE salary > (SELECT AVG(salary) FROM employee);
```

**JOIN** — combines rows from two tables in a single query execution.

```sql
-- JOIN: same result, often more efficient
SELECT e.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id
WHERE d.name = 'Engineering';
```

**When to use which:**
- Use **JOIN** when you need columns from multiple tables — it's generally faster
- Use **subquery** when the inner result is a single value or a filtered set that's hard to express as a JOIN
- Correlated subqueries (referencing outer query) can be slow — prefer JOIN or CTEs

---

## 9. What is a View in SQL?

A view is a **virtual table** — it's a saved SQL query that you can query like a table. It doesn't store data itself.

```sql
-- Create a view
CREATE VIEW high_salary_employees AS
SELECT name, salary, dept_id FROM employee
WHERE salary > 80000;

-- Query the view like a table
SELECT * FROM high_salary_employees;
```

**Benefits:**
- Simplifies complex queries — write once, reuse everywhere
- Security — expose only specific columns/rows to users
- Abstraction — hide underlying table structure

**Limitation:** A regular view doesn't store data. For performance, use a **Materialized View** (stores the result physically, needs refresh).

---

## 10. What is a Transaction in SQL?

A transaction is a **group of SQL operations** that execute as a single unit. Either all succeed or all fail — no partial updates.

Transactions follow **ACID** properties:
- **Atomicity** — all or nothing
- **Consistency** — data stays valid before and after
- **Isolation** — transactions don't interfere with each other
- **Durability** — committed changes are permanent

```sql
BEGIN TRANSACTION;

UPDATE account SET balance = balance - 500 WHERE id = 1;  -- debit
UPDATE account SET balance = balance + 500 WHERE id = 2;  -- credit

-- If both succeed
COMMIT;

-- If anything fails
ROLLBACK;
```

Without transactions, if the debit succeeds but the credit fails, you lose money. Transactions prevent that.

---

## 11. What is the Difference Between UNION and UNION ALL?

Both combine results of two SELECT queries — but handle duplicates differently.

- **UNION** — combines results and **removes duplicates** (slower, does a distinct sort)
- **UNION ALL** — combines results and **keeps all rows including duplicates** (faster)

```sql
-- UNION: removes duplicates
SELECT name FROM employee_india
UNION
SELECT name FROM employee_us;

-- UNION ALL: keeps duplicates
SELECT name FROM employee_india
UNION ALL
SELECT name FROM employee_us;
```

**Rules for both:**
- Same number of columns in both SELECT statements
- Columns must have compatible data types

Use `UNION ALL` when you know there are no duplicates or you want all rows — it's faster because it skips the deduplication step.

---
