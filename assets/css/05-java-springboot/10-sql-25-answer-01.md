# 🔹 Basic SQL

### 1. What is SQL?

**Answer:**
SQL stands for Structured Query Language. It's a standard programming language specifically designed for managing and manipulating relational databases. Think of it as the universal language that lets you talk to databases - you use it to create tables, insert data, retrieve information, update records, and delete data. It's declarative, meaning you tell the database what you want, not how to get it.

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
WHERE filters individual rows before grouping happens, while HAVING filters groups after they've been created. Think of WHERE as a bouncer at the door checking each person individually, and HAVING as someone checking entire groups after they've formed inside. WHERE works with regular columns, but HAVING works with aggregate functions like COUNT, SUM, AVG.

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
DELETE removes specific rows and can be rolled back - it's like erasing individual entries from a notebook. TRUNCATE removes all rows quickly but keeps the table structure - it's like tearing out all pages but keeping the notebook cover. DROP removes the entire table including its structure - it's like throwing away the entire notebook. DELETE is slowest but most flexible, TRUNCATE is faster, and DROP is permanent removal.

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
JOINs combine rows from two or more tables based on related columns. The main types are: INNER JOIN (returns only matching rows from both tables), LEFT JOIN (returns all rows from left table and matching rows from right), RIGHT JOIN (opposite of LEFT), FULL OUTER JOIN (returns all rows from both tables), and CROSS JOIN (returns cartesian product of both tables). Think of them as different ways to merge two lists based on common elements.

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
INNER JOIN returns only rows where there's a match in both tables - it's like finding people who are members of both clubs. LEFT JOIN returns all rows from the left table and matching rows from the right table; if there's no match, it fills with NULL - it's like listing all members of the first club and showing which ones are also in the second club, marking "none" for those who aren't.

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
A Primary Key is a unique identifier for each row in a table. It's like a social security number - every person has one, no two people share the same one, and you can't exist without one. It must be unique, cannot be NULL, and each table can have only one primary key (though it can consist of multiple columns). It ensures data integrity and is used to establish relationships between tables.

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
A Foreign Key is a column that creates a link between two tables by referencing the Primary Key of another table. It's like a reference letter - it points to someone else's identity to establish a relationship. It ensures referential integrity, meaning you can't have orphaned records. For example, you can't have an order for a customer that doesn't exist in the customers table.

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
Both ensure uniqueness, but PRIMARY KEY cannot be NULL and you can have only one per table, while UNIQUE can accept one NULL value and you can have multiple UNIQUE constraints per table. Think of PRIMARY KEY as your main ID card (mandatory, unique, one per person), and UNIQUE as other unique identifiers like email or phone number (unique but optional, can have several).

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
Normalization is the process of organizing database tables to reduce redundancy and improve data integrity. It's like organizing a messy closet into labeled boxes - everything has its proper place, nothing is duplicated unnecessarily. You break down large tables into smaller, related tables and define relationships between them. The main forms are 1NF (atomic values), 2NF (no partial dependencies), and 3NF (no transitive dependencies).

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
An Index is a database structure that improves the speed of data retrieval operations. It's like a book's index - instead of reading every page to find a topic, you look it up in the index and jump directly to the right page. Indexes make SELECT queries faster but slow down INSERT, UPDATE, and DELETE operations because the index needs to be updated. They're created on columns frequently used in WHERE clauses or JOIN conditions.

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
GROUP BY combines rows with the same values into summary rows - it's like sorting students into classes based on their grade level. ORDER BY simply sorts the result set in ascending or descending order - it's like arranging students by height. GROUP BY is used with aggregate functions to perform calculations on each group, while ORDER BY just changes the display order without combining anything. You can use both together: GROUP BY to create groups, then ORDER BY to sort those groups.

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
A Subquery is a query nested inside another query. It's like solving a math problem where you need to solve the inner parentheses first before solving the outer equation. The subquery executes first, and its result is used by the outer query. You can use subqueries in SELECT, FROM, WHERE, or HAVING clauses. They make complex queries more readable by breaking them into logical steps.

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
A Correlated Subquery is a subquery that references columns from the outer query, so it executes once for each row processed by the outer query. It's like checking each student's grade against their own class average, not the school average. Unlike regular subqueries that run once, correlated subqueries run repeatedly. They're powerful but can be slower because they execute multiple times.

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
IN checks if a value matches any value in a list or subquery result - it's like checking if your name is on a guest list. EXISTS checks if a subquery returns any rows at all - it's like checking if the guest list has any names, not if your specific name is there. EXISTS is often faster for large datasets because it stops as soon as it finds one match, while IN compares against all values. EXISTS works with correlated subqueries better than IN.

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
A View is a virtual table based on a SQL query - it's like a saved filter or a window showing specific data from one or more tables. It doesn't store data itself; it just stores the query definition. When you query a view, it runs the underlying query in real-time. Views are great for simplifying complex queries, hiding sensitive columns, and providing a consistent interface to users without giving them direct table access.

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
A Stored Procedure is a prepared SQL code that you save and reuse - it's like a recipe you write once and follow many times. It can accept parameters, contain logic with IF statements and loops, and return results. Stored procedures improve performance because they're precompiled, reduce network traffic by executing multiple statements in one call, and enhance security by controlling data access. They're stored in the database itself.

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
Aggregate Functions perform calculations on multiple rows and return a single value - they're like taking a bunch of numbers and summarizing them into one meaningful result. Common ones are COUNT (how many), SUM (total), AVG (average), MAX (highest), and MIN (lowest). They're typically used with GROUP BY to calculate summaries for each group. You can't mix aggregate functions with regular columns unless you use GROUP BY.

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
To find duplicates, you group by the columns you want to check for duplicates and use HAVING with COUNT to filter groups that appear more than once. It's like sorting items into piles and then identifying which piles have more than one item. You can find just the duplicate values or all rows that are duplicates. This is useful for data cleaning and identifying data quality issues.

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
There are multiple approaches: use LIMIT with OFFSET to skip the first and get the second, use a subquery to find the max salary less than the overall max, or use DENSE_RANK to assign rankings and filter for rank 2. Each method has trade-offs - LIMIT is simple but database-specific, subquery works everywhere but can be slower, and DENSE_RANK handles ties better. The key is excluding the highest to get the next one.

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
A Clustered Index determines the physical order of data in the table - it's like a dictionary where words are physically arranged alphabetically. There can be only one clustered index per table because data can be sorted in only one way. A Non-Clustered Index creates a separate structure with pointers to the actual data - it's like a book's index at the back that points to page numbers. You can have multiple non-clustered indexes. Clustered is faster for range queries, non-clustered is better for specific lookups.

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

