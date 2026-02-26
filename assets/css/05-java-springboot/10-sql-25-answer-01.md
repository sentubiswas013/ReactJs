# 🔹 Basic SQL

### 1. What is SQL?

**Answer:**

**SQL (Structured Query Language)** is a standard language used to manage and work with relational databases. It is used to create tables, insert, update, delete, and retrieve data. It is declarative, meaning you specify what data you want, and the database handles how to get it.


**Example:**
```sql
-- Creating a table
CREATE TABLE users (id INT, name VARCHAR(50));

-- Inserting data
INSERT INTO users VALUES (1, 'John');

-- Retrieving data
SELECT * FROM users;
```

---

### 2. What is the difference between **WHERE** and **HAVING**?

**Answer:**

**WHERE** filters individual rows **before** grouping.
**HAVING** filters grouped data **after** `GROUP BY`.

**WHERE** is used with normal columns.
**HAVING** is used with aggregate functions like `COUNT`, `SUM`, `AVG`.


**Example:**
```sql
-- WHERE: filters rows before grouping
SELECT department, COUNT(*) 
FROM employees 
WHERE salary > 50000 
GROUP BY department;

-- HAVING: filters groups after aggregation
SELECT department, COUNT(*) 
FROM employees 
GROUP BY department 
HAVING COUNT(*) > 5;
```

---

### 3. What is the difference between **DELETE**, **TRUNCATE**, and **DROP**?

**Answer:**

**DELETE** – Removes selected rows, can use `WHERE`, and can be rolled back.

**TRUNCATE** – Removes all rows quickly, cannot use `WHERE`, keeps table structure.

**DROP** – Deletes the entire table including its structure permanently.


**Example:**
```sql
-- DELETE: removes specific rows, can use WHERE
DELETE FROM users WHERE id = 1;

-- TRUNCATE: removes all rows, faster, resets identity
TRUNCATE TABLE users;

-- DROP: removes entire table structure
DROP TABLE users;
```

---

### 4. What are different types of **JOINs**?

**Answer:**

**JOIN** is used to combine rows from two or more tables based on a related column.

Main types:

* **INNER JOIN** – Returns only matching rows from both tables.
* **LEFT JOIN** – Returns all rows from left table and matching rows from right table.
* **RIGHT JOIN** – Returns all rows from right table and matching rows from left table.
* **FULL OUTER JOIN** – Returns all rows from both tables.
* **CROSS JOIN** – Returns Cartesian product (all possible combinations).


**Example:**
```sql
-- INNER JOIN: only matching records
SELECT * FROM orders o INNER JOIN customers c ON o.customer_id = c.id;

-- LEFT JOIN: all from left, matching from right
SELECT * FROM customers c LEFT JOIN orders o ON c.id = o.customer_id;

-- CROSS JOIN: all combinations
SELECT * FROM colors CROSS JOIN sizes;
```

---

### 5. What is the difference between **INNER JOIN** and **LEFT JOIN**?

**Answer:**

**INNER JOIN** – Returns only the rows that have matching records in both tables.

**LEFT JOIN** – Returns all rows from the left table and matching rows from the right table. If no match exists, it returns `NULL` for right table columns.


**Example:**
```sql
-- INNER JOIN: only customers who placed orders
SELECT c.name, o.order_id 
FROM customers c 
INNER JOIN orders o ON c.id = o.customer_id;

-- LEFT JOIN: all customers, even without orders
SELECT c.name, o.order_id 
FROM customers c 
LEFT JOIN orders o ON c.id = o.customer_id;
```

---

### 6. What is a **Primary Key**?

**Answer:**

**Primary Key** is a column (or combination of columns) that **uniquely identifies each row** in a table.

It must be **unique** and **cannot be NULL**.
Each table can have **only one primary key**, but it can contain multiple columns (composite key).


**Example:**
```sql
-- Single column primary key
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50)
);

-- Composite primary key
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    PRIMARY KEY (student_id, course_id)
);
```

---

### 7. What is a **Foreign Key**?

**Answer:**

**Foreign Key** is a column that **references the Primary Key of another table**.

It is used to **create relationships between tables** and ensures **referential integrity** (no orphan records).


**Example:**
```sql
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

---

### 8. What is the difference between **UNIQUE** and **PRIMARY KEY**?

**Answer:**

**PRIMARY KEY** – Ensures uniqueness and **cannot be NULL**. A table can have **only one** primary key.

**UNIQUE** – Also ensures uniqueness, but **can allow one NULL value** and a table can have **multiple UNIQUE constraints**.


**Example:**
```sql
CREATE TABLE users (
    user_id INT PRIMARY KEY,           -- Only one, cannot be NULL
    email VARCHAR(100) UNIQUE,         -- Can have multiple UNIQUE
    phone VARCHAR(15) UNIQUE,          -- Another UNIQUE constraint
    ssn VARCHAR(11) UNIQUE             -- UNIQUE can be NULL
);
```

---

### 9. What is **Normalization**?

**Answer:**

**Normalization** is the process of organizing database tables to **reduce redundancy** and **improve data integrity**.

It divides large tables into smaller related tables and defines proper relationships.

Common normal forms:

* **1NF** – Atomic values (no repeating groups)
* **2NF** – No partial dependency
* **3NF** – No transitive dependency


**Example:**
```sql
-- Before Normalization (redundant data)
CREATE TABLE orders_bad (
    order_id INT,
    customer_name VARCHAR(50),
    customer_email VARCHAR(50),
    product_name VARCHAR(50)
);

-- After Normalization (1NF, 2NF, 3NF)
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE products (
    product_id INT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    product_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
```

---

### 10. What is an **Index**?

**Answer:**

**Index** is a database object that **improves data retrieval speed**.

It makes `SELECT` queries faster but can slow down `INSERT`, `UPDATE`, and `DELETE` because the index must also be updated.

Indexes are usually created on columns used in `WHERE`, `JOIN`, or `ORDER BY` clauses.


**Example:**
```sql
-- Creating an index on a single column
CREATE INDEX idx_email ON users(email);

-- Creating a composite index
CREATE INDEX idx_name_dept ON employees(last_name, department);

-- Using the index in a query
SELECT * FROM users WHERE email = 'john@example.com';

-- Dropping an index
DROP INDEX idx_email;
```

# 🔹 Intermediate SQL

### 11. What is the difference between **GROUP BY** and **ORDER BY**?

**Answer:**

**GROUP BY** – Groups rows with the same values and is used with aggregate functions like `COUNT`, `SUM`, `AVG`.

**ORDER BY** – Sorts the result set in ascending or descending order.

**GROUP BY** combines data.
**ORDER BY** only sorts data.


**Example:**
```sql
-- GROUP BY: combines rows and aggregates
SELECT department, COUNT(*) as emp_count
FROM employees
GROUP BY department;

-- ORDER BY: just sorts results
SELECT name, salary
FROM employees
ORDER BY salary DESC;

-- Both together
SELECT department, AVG(salary) as avg_sal
FROM employees
GROUP BY department
ORDER BY avg_sal DESC;
```

---

### 12. What is a **Subquery**?

**Answer:**

**Subquery** is a query written **inside another query**.

It executes first, and its result is used by the outer query.

It can be used in `SELECT`, `FROM`, `WHERE`, or `HAVING` clauses.


**Example:**
```sql
-- Subquery in WHERE clause
SELECT name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

-- Subquery in FROM clause
SELECT dept, avg_salary
FROM (SELECT department as dept, AVG(salary) as avg_salary
      FROM employees
      GROUP BY department) as dept_avg;

-- Subquery in SELECT clause
SELECT name, 
       salary,
       (SELECT AVG(salary) FROM employees) as company_avg
FROM employees;
```

---

### 13. What is a **Correlated Subquery**?

**Answer:**

**Correlated Subquery** is a subquery that **depends on the outer query** and uses its column values.

It executes **once for each row** of the outer query, so it can be slower than a normal subquery.


**Example:**
```sql
-- Find employees earning more than their department average
SELECT e1.name, e1.salary, e1.department
FROM employees e1
WHERE salary > (SELECT AVG(salary)
                FROM employees e2
                WHERE e2.department = e1.department);

-- Find employees with above-average salary in their department
SELECT name, department, salary
FROM employees e1
WHERE salary > (SELECT AVG(e2.salary)
                FROM employees e2
                WHERE e1.department = e2.department);
```

---

### 14. What is the difference between **IN** and **EXISTS**?

**Answer:**
**IN** – Checks if a value matches **any value** in a list or subquery result.

**EXISTS** – Checks if a subquery returns **at least one row**.

**IN** compares values.
**EXISTS** checks for existence and is often faster for large datasets.


**Example:**
```sql
-- IN: checks for value match
SELECT name
FROM employees
WHERE department_id IN (SELECT id FROM departments WHERE location = 'NYC');

-- EXISTS: checks if subquery returns any rows
SELECT name
FROM employees e
WHERE EXISTS (SELECT 1 
              FROM orders o 
              WHERE o.employee_id = e.id);

-- NOT EXISTS: find employees with no orders
SELECT name
FROM employees e
WHERE NOT EXISTS (SELECT 1 
                  FROM orders o 
                  WHERE o.employee_id = e.id);
```

---

### 15. What is a **View**?

**Answer:**

**View** is a **virtual table** based on a SQL query.

It does not store data itself; it stores only the query definition.

Views are used to **simplify complex queries**, **hide sensitive data**, and provide a consistent interface to users.


**Example:**
```sql
-- Creating a view
CREATE VIEW high_earners AS
SELECT name, salary, department
FROM employees
WHERE salary > 100000;

-- Using the view like a table
SELECT * FROM high_earners WHERE department = 'IT';

-- Creating a view with joins
CREATE VIEW employee_details AS
SELECT e.name, e.salary, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.id;

-- Dropping a view
DROP VIEW high_earners;
```

---

### 16. What is a **Stored Procedure**?

**Answer:**

**Stored Procedure** is a **precompiled SQL program** stored in the database.

It can accept **parameters**, contain **business logic (IF, loops)**, and return results.

It improves **performance**, reduces **network calls**, and enhances **security**.


**Example:**
```sql
-- Creating a stored procedure
CREATE PROCEDURE GetEmployeesByDept(IN dept_name VARCHAR(50))
BEGIN
    SELECT name, salary
    FROM employees
    WHERE department = dept_name;
END;

-- Calling the stored procedure
CALL GetEmployeesByDept('IT');

-- Procedure with output parameter
CREATE PROCEDURE GetAvgSalary(IN dept VARCHAR(50), OUT avg_sal DECIMAL(10,2))
BEGIN
    SELECT AVG(salary) INTO avg_sal
    FROM employees
    WHERE department = dept;
END;
```

---

### 17. What are **Aggregate Functions**?

**Answer:**

**Aggregate Functions** perform calculations on multiple rows and return a **single value**.

Common examples: `COUNT`, `SUM`, `AVG`, `MAX`, `MIN`.

They are usually used with `GROUP BY` to calculate summary results.


**Example:**
```sql
-- Basic aggregate functions
SELECT COUNT(*) as total_employees FROM employees;
SELECT SUM(salary) as total_payroll FROM employees;
SELECT AVG(salary) as average_salary FROM employees;
SELECT MAX(salary) as highest_salary FROM employees;
SELECT MIN(salary) as lowest_salary FROM employees;

-- With GROUP BY
SELECT department, 
       COUNT(*) as emp_count,
       AVG(salary) as avg_sal,
       MAX(salary) as max_sal
FROM employees
GROUP BY department;
```

---

### 18. How to fetch duplicate records from a table?

**Answer:**

To fetch duplicate records, use `GROUP BY` on the column(s) and filter with `HAVING COUNT(*) > 1`.

**Example:**
```sql
-- Find duplicate emails
SELECT email, COUNT(*) as count
FROM users
GROUP BY email
HAVING COUNT(*) > 1;

-- Find all rows with duplicate emails
SELECT *
FROM users
WHERE email IN (SELECT email
                FROM users
                GROUP BY email
                HAVING COUNT(*) > 1);

-- Find duplicates based on multiple columns
SELECT name, department, COUNT(*) as count
FROM employees
GROUP BY name, department
HAVING COUNT(*) > 1;
```

---

### 19. How to fetch the second highest salary?

**Answer:**

You can fetch the **second highest salary** using a subquery:

**Example:**
```sql
-- Method 1: Using LIMIT and OFFSET
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 1;

-- Method 2: Using subquery
SELECT MAX(salary) as second_highest
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);

-- Method 3: Using DENSE_RANK (handles ties)
SELECT salary
FROM (SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) as rank
      FROM employees) as ranked
WHERE rank = 2;

-- Get Nth highest salary (e.g., 3rd highest)
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 2;
```

---

### 20. What is the difference between **Clustered** and **Non-Clustered Index**?

**Answer:**

**Clustered Index** – Defines the **physical order of data** in the table.
Only **one** clustered index is allowed per table.

**Non-Clustered Index** – Creates a **separate index structure** with pointers to the actual data.
You can have **multiple** non-clustered indexes per table.


**Example:**
```sql
-- Clustered Index (usually the primary key)
CREATE TABLE employees (
    emp_id INT PRIMARY KEY CLUSTERED,  -- Clustered index
    name VARCHAR(50),
    salary DECIMAL(10,2)
);

-- Non-Clustered Index
CREATE NONCLUSTERED INDEX idx_name ON employees(name);
CREATE NONCLUSTERED INDEX idx_salary ON employees(salary);

-- In most databases, PRIMARY KEY creates clustered index automatically
CREATE TABLE products (
    product_id INT PRIMARY KEY,  -- Automatically clustered
    product_name VARCHAR(100),
    price DECIMAL(10,2)
);

-- Additional non-clustered indexes
CREATE INDEX idx_product_name ON products(product_name);
CREATE INDEX idx_price ON products(price);
```

# 🔹 Performance & Practical

### 21. What is query optimization?

**Answer:**

**Query Optimization** is the process of improving a SQL query to **run faster and use fewer resources**.

The database optimizer chooses the most efficient **execution plan** by selecting proper indexes, join methods, and minimizing full table scans.

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

To improve slow queries:

* Use **EXPLAIN** to check the execution plan.
* Add **indexes** on columns used in `WHERE`, `JOIN`, and `ORDER BY`.
* Avoid `SELECT *` and fetch only required columns.
* Optimize joins and reduce unnecessary data scans.
* Use proper filtering and pagination (`LIMIT`).

These steps help reduce execution time and improve performance.


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

**Transaction** is a group of SQL operations executed as a **single unit of work**.

Either **all operations succeed (COMMIT)** or **all fail (ROLLBACK)**.

It ensures **data consistency and integrity** in the database.


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

**ACID** properties ensure reliable transactions in a database:

* **Atomicity** – All operations succeed or none (all-or-nothing).
* **Consistency** – Data remains valid and follows all rules.
* **Isolation** – Transactions do not interfere with each other.
* **Durability** – Once committed, data is permanently saved even after a crash.


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

**CTE (Common Table Expression)** is a temporary named result set defined using the `WITH` clause.

It exists only during query execution and helps make complex queries more readable.

It is also useful for writing **recursive queries**.


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

