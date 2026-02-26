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
