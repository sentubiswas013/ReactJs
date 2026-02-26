# 🔹 Performance & Practical

### 21. What is query optimization?

**Answer:**
Query optimization is the process of improving SQL query performance to execute faster and use fewer resources. It's like finding the shortest route to your destination instead of taking the long way. The database query optimizer analyzes different ways to execute your query and chooses the most efficient execution plan. This involves selecting the right indexes, choosing optimal join methods, and minimizing data scans. Good optimization reduces response time, lowers CPU and memory usage, and improves overall database performance.

**Example:**
```sql
-- Unoptimized: Using functions on indexed columns
SELECT * FROM employees WHERE YEAR(hire_date) = 2023;

-- Optimized: Direct comparison allows index usage
SELECT * FROM employees WHERE hire_date >= '2023-01-01' AND hire_date < '2024-01-01';

-- Unoptimized: SELECT *
SELECT * FROM employees WHERE department = 'IT';

-- Optimized: Select only needed columns
SELECT emp_id, name, salary FROM employees WHERE department = 'IT';

-- Using EXPLAIN to see execution plan
EXPLAIN SELECT * FROM employees WHERE department = 'IT';
```

---

### 22. How do you improve slow queries?

**Answer:**
To improve slow queries, start by analyzing the execution plan with EXPLAIN to identify bottlenecks. Add indexes on columns used in WHERE, JOIN, and ORDER BY clauses. Avoid SELECT * and fetch only needed columns. Limit result sets with WHERE conditions early. Replace subqueries with JOINs when possible. Avoid functions on indexed columns in WHERE clauses. Use LIMIT for pagination. Partition large tables. Update statistics regularly. Consider denormalization for read-heavy workloads. It's like tuning a car - you identify what's slowing it down and fix those specific parts.

**Example:**
```sql
-- 1. Add indexes on frequently queried columns
CREATE INDEX idx_dept_salary ON employees(department, salary);

-- 2. Avoid SELECT *, specify columns
SELECT name, salary FROM employees WHERE department = 'IT';

-- 3. Use LIMIT for pagination
SELECT * FROM orders ORDER BY order_date DESC LIMIT 100;

-- 4. Replace subquery with JOIN
-- Slow subquery
SELECT * FROM employees WHERE dept_id IN (SELECT id FROM departments WHERE location = 'NYC');

-- Faster JOIN
SELECT e.* FROM employees e JOIN departments d ON e.dept_id = d.id WHERE d.location = 'NYC';

-- 5. Avoid functions on indexed columns
-- Slow
SELECT * FROM employees WHERE UPPER(name) = 'JOHN';

-- Fast
SELECT * FROM employees WHERE name = 'John';

-- 6. Use EXISTS instead of IN for large datasets
SELECT * FROM customers c WHERE EXISTS (SELECT 1 FROM orders o WHERE o.customer_id = c.id);
```

---

### 23. What is a transaction?

**Answer:**
A transaction is a sequence of one or more SQL operations treated as a single unit of work - either all operations succeed together or all fail together. It's like a bank transfer where money must leave one account and enter another; if either step fails, both are cancelled. Transactions ensure data consistency and integrity. They start with BEGIN, execute operations, and end with COMMIT (to save changes) or ROLLBACK (to undo changes). This prevents partial updates that could leave your database in an inconsistent state.

**Example:**
```sql
-- Basic transaction
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;

COMMIT;  -- Save changes

-- Transaction with error handling
BEGIN TRANSACTION;

UPDATE inventory SET quantity = quantity - 5 WHERE product_id = 101;
INSERT INTO orders (product_id, quantity) VALUES (101, 5);

-- If everything is okay
COMMIT;

-- If there's an error
ROLLBACK;  -- Undo all changes

-- Practical example with savepoint
BEGIN TRANSACTION;

INSERT INTO customers (name) VALUES ('John');
SAVEPOINT sp1;

INSERT INTO orders (customer_id) VALUES (LAST_INSERT_ID());
-- If order insert fails, rollback to savepoint
ROLLBACK TO sp1;

COMMIT;
```

---

### 24. What are ACID properties?

**Answer:**
ACID stands for Atomicity, Consistency, Isolation, and Durability - the four properties that guarantee reliable database transactions. Atomicity means all-or-nothing: either the entire transaction succeeds or none of it does. Consistency ensures the database moves from one valid state to another, maintaining all rules and constraints. Isolation means concurrent transactions don't interfere with each other - they execute as if they're alone. Durability guarantees that once a transaction is committed, it stays committed even if the system crashes. Think of it as a contract with four guarantees.

**Example:**
```sql
-- ATOMICITY: All or nothing
BEGIN TRANSACTION;
UPDATE accounts SET balance = balance - 500 WHERE id = 1;
UPDATE accounts SET balance = balance + 500 WHERE id = 2;
COMMIT;  -- Both updates happen or neither does

-- CONSISTENCY: Maintains constraints
BEGIN TRANSACTION;
INSERT INTO orders (customer_id, amount) VALUES (999, 100);
-- Fails if customer_id 999 doesn't exist (foreign key constraint)
COMMIT;

-- ISOLATION: Transactions don't interfere
-- Transaction 1
BEGIN TRANSACTION;
SELECT balance FROM accounts WHERE id = 1;  -- Reads 1000
UPDATE accounts SET balance = balance - 100 WHERE id = 1;
COMMIT;

-- Transaction 2 (running concurrently)
BEGIN TRANSACTION;
SELECT balance FROM accounts WHERE id = 1;  -- Still reads 1000 (isolated)
COMMIT;

-- DURABILITY: Changes persist after commit
BEGIN TRANSACTION;
INSERT INTO logs (message) VALUES ('System started');
COMMIT;  -- Even if power fails now, this record is saved
```

---

### 25. What is a CTE (Common Table Expression)?

**Answer:**
A CTE (Common Table Expression) is a temporary named result set that exists only during query execution - it's like creating a temporary variable to hold intermediate results. You define it using WITH clause before your main query. CTEs make complex queries more readable by breaking them into logical steps, like solving a problem step-by-step. They're especially useful for recursive queries (like organizational hierarchies) and when you need to reference the same subquery multiple times. Unlike views, CTEs are not stored and disappear after the query runs.

**Example:**
```sql
-- Basic CTE
WITH high_earners AS (
    SELECT name, salary, department
    FROM employees
    WHERE salary > 100000
)
SELECT * FROM high_earners WHERE department = 'IT';

-- Multiple CTEs
WITH 
dept_avg AS (
    SELECT department, AVG(salary) as avg_salary
    FROM employees
    GROUP BY department
),
high_performers AS (
    SELECT e.name, e.salary, e.department
    FROM employees e
    JOIN dept_avg d ON e.department = d.department
    WHERE e.salary > d.avg_salary
)
SELECT * FROM high_performers;

-- Recursive CTE (employee hierarchy)
WITH RECURSIVE employee_hierarchy AS (
    -- Base case: top-level managers
    SELECT emp_id, name, manager_id, 1 as level
    FROM employees
    WHERE manager_id IS NULL
    
    UNION ALL
    
    -- Recursive case: employees reporting to previous level
    SELECT e.emp_id, e.name, e.manager_id, eh.level + 1
    FROM employees e
    JOIN employee_hierarchy eh ON e.manager_id = eh.emp_id
)
SELECT * FROM employee_hierarchy;

-- CTE for data transformation
WITH monthly_sales AS (
    SELECT 
        DATE_FORMAT(order_date, '%Y-%m') as month,
        SUM(amount) as total_sales
    FROM orders
    GROUP BY DATE_FORMAT(order_date, '%Y-%m')
)
SELECT month, total_sales,
       LAG(total_sales) OVER (ORDER BY month) as prev_month_sales
FROM monthly_sales;
```

---

## Summary

These 5 performance and practical SQL concepts are crucial for production environments:
- **Query optimization** - improving query performance through better execution plans
- **Improve slow queries** - techniques like indexing, avoiding SELECT *, using JOINs over subqueries
- **Transaction** - unit of work with BEGIN, COMMIT, ROLLBACK
- **ACID properties** - Atomicity, Consistency, Isolation, Durability guarantees
- **CTE** - temporary named result sets using WITH clause, great for readability and recursion

Understanding these concepts is essential for building robust, high-performance database applications and handling real-world production scenarios effectively.

