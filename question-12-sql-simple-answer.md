### 1. What is SQL?

SQL stands for Structured Query Language. It's the standard language for managing and manipulating relational databases. Think of it as the universal way to talk to databases - whether you want to retrieve data, update records, or create new tables. Every major database system like MySQL, PostgreSQL, and SQL Server uses SQL.

```sql
SELECT * FROM users WHERE age > 25;
```

### 2. What are the different types of SQL statements?

There are four main types: DDL creates and modifies database structure like CREATE TABLE, DML manipulates data like INSERT and SELECT, DCL controls access with GRANT and REVOKE, and TCL manages transactions with COMMIT and ROLLBACK. Most day-to-day work involves DML for querying and updating data.

```sql
-- DDL
CREATE TABLE products (id INT, name VARCHAR(50));
-- DML  
INSERT INTO products VALUES (1, 'Laptop');
-- DCL
GRANT SELECT ON products TO user1;
-- TCL
COMMIT;
```

### 3. What is a primary key?

A primary key uniquely identifies each row in a table. It's like a social security number - no two rows can have the same primary key value, and it can't be null. This ensures data integrity and helps the database quickly find specific records.

```sql
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);
```

### 4. What is a foreign key?

A foreign key creates a link between two tables by referencing the primary key of another table. It maintains referential integrity - you can't have orphaned records. For example, an order must belong to an existing customer.

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

### 5. What is a composite key?

A composite key uses multiple columns together to uniquely identify a row. This is useful when no single column can guarantee uniqueness. For instance, in a course enrollment table, student ID plus course ID together form a unique combination.

```sql
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    PRIMARY KEY (student_id, course_id)
);
```

### 6. What is a JOIN?

A JOIN combines data from multiple tables based on related columns. INNER JOIN returns matching records, LEFT JOIN returns all records from the left table plus matches, RIGHT JOIN does the opposite, and FULL OUTER JOIN returns everything from both tables.

```sql
SELECT c.name, o.order_date 
FROM customers c 
INNER JOIN orders o ON c.customer_id = o.customer_id;
```

### 7. What is the difference between WHERE and HAVING?

WHERE filters individual rows before grouping, while HAVING filters groups after GROUP BY. Use WHERE for regular conditions and HAVING when you need to filter based on aggregate functions like COUNT or SUM.

```sql
SELECT department, COUNT(*) 
FROM employees 
WHERE salary > 50000 
GROUP BY department 
HAVING COUNT(*) > 5;
```

### 8. What is a subquery?

A subquery is a query nested inside another query. It's like asking a question within a question. The inner query runs first, and its result is used by the outer query. Very useful for complex filtering and comparisons.

```sql
SELECT name FROM employees 
WHERE salary > (SELECT AVG(salary) FROM employees);
```

### 9. What are the different types of subqueries?

There are two main types: correlated and non-correlated. Non-correlated subqueries run independently and return the same result every time. Correlated subqueries reference the outer query and run once for each row, making them slower but more flexible for row-by-row comparisons.

```sql
-- Non-correlated
SELECT * FROM products WHERE price > (SELECT AVG(price) FROM products);

-- Correlated  
SELECT * FROM employees e1 WHERE salary > 
(SELECT AVG(salary) FROM employees e2 WHERE e2.department = e1.department);
```

### 10. Explain the difference between UNION and UNION ALL.

UNION combines results from multiple queries and removes duplicates, while UNION ALL keeps all rows including duplicates. UNION is slower because it has to check for duplicates, so use UNION ALL when you know there won't be duplicates or when you want to keep them.

```sql
-- UNION removes duplicates
SELECT city FROM customers UNION SELECT city FROM suppliers;

-- UNION ALL keeps duplicates  
SELECT city FROM customers UNION ALL SELECT city FROM suppliers;
```

### 11. What is the difference between GROUP BY and ORDER BY?

GROUP BY groups rows with the same values into summary rows, typically used with aggregate functions like COUNT or SUM. ORDER BY sorts the result set by specified columns. Think of GROUP BY as categorizing data, while ORDER BY arranges it. You often use both together - GROUP BY first, then ORDER BY.

```sql
SELECT department, COUNT(*) 
FROM employees 
GROUP BY department 
ORDER BY COUNT(*) DESC;
```

### 12. What is the LIMIT/TOP clause in SQL?

LIMIT restricts the number of rows returned by a query. It's perfect for pagination or getting just the top results. In MySQL you use LIMIT, in SQL Server you use TOP. Very useful for performance when you only need a few records from a large table.

```sql
-- MySQL
SELECT * FROM products ORDER BY price DESC LIMIT 5;

-- SQL Server  
SELECT TOP 5 * FROM products ORDER BY price DESC;
```

### 13. What is normalization?

Normalization organizes data to reduce redundancy and improve data integrity. First Normal Form eliminates repeating groups, Second Normal Form removes partial dependencies, and Third Normal Form eliminates transitive dependencies. It's like organizing your closet - everything has its proper place and nothing is duplicated unnecessarily.

```sql
-- Before normalization (bad)
CREATE TABLE orders (id INT, customer_name VARCHAR(50), customer_email VARCHAR(50));

-- After normalization (good)
CREATE TABLE customers (id INT, name VARCHAR(50), email VARCHAR(50));
CREATE TABLE orders (id INT, customer_id INT);
```

### 14. What is denormalization?

Denormalization intentionally introduces redundancy to improve query performance. Sometimes you sacrifice storage space and update complexity for faster reads. It's common in data warehouses where you prioritize reporting speed over storage efficiency. Think of it as trading space for speed.

```sql
-- Denormalized for faster reporting
CREATE TABLE sales_report (
    order_id INT,
    customer_name VARCHAR(50),  -- Redundant but faster
    product_name VARCHAR(50),   -- Redundant but faster
    total_amount DECIMAL(10,2)
);
```

### 15. What is an index?

An index is like a book's table of contents - it helps the database quickly find data without scanning every row. It creates a separate structure that points to the actual data. Indexes speed up SELECT queries but slow down INSERT, UPDATE, and DELETE operations because the index needs to be maintained.

```sql
CREATE INDEX idx_customer_email ON customers(email);
CREATE INDEX idx_order_date ON orders(order_date);
```

### 16. What is the difference between INNER JOIN and OUTER JOIN?

INNER JOIN returns only matching records from both tables. OUTER JOIN returns matching records plus non-matching records from one or both tables. LEFT OUTER JOIN includes all records from the left table, RIGHT OUTER JOIN from the right table, and FULL OUTER JOIN from both tables.

```sql
-- INNER JOIN - only customers with orders
SELECT c.name, o.order_date FROM customers c 
INNER JOIN orders o ON c.id = o.customer_id;

-- LEFT OUTER JOIN - all customers, even without orders
SELECT c.name, o.order_date FROM customers c 
LEFT JOIN orders o ON c.id = o.customer_id;
```

### 17. What is a self-join?

A self-join joins a table to itself using aliases. It's useful for hierarchical data like employee-manager relationships or finding related records within the same table. You treat the same table as if it were two different tables by giving them different aliases.

```sql
SELECT e.name AS employee, m.name AS manager 
FROM employees e 
LEFT JOIN employees m ON e.manager_id = m.id;
```

### 18. Explain the difference between LEFT JOIN and RIGHT JOIN.

LEFT JOIN returns all records from the left table and matching records from the right table. RIGHT JOIN does the opposite - all records from the right table and matching records from the left. Most developers prefer LEFT JOIN because it reads more naturally from left to right.

```sql
-- LEFT JOIN - all customers, matched orders
SELECT c.name, o.total FROM customers c 
LEFT JOIN orders o ON c.id = o.customer_id;

-- RIGHT JOIN - all orders, matched customers  
SELECT c.name, o.total FROM customers c 
RIGHT JOIN orders o ON c.id = o.customer_id;
```

### 19. What are window functions in SQL?

Window functions perform calculations across related rows without grouping them like GROUP BY does. ROW_NUMBER assigns sequential numbers, RANK gives tied values the same rank with gaps, DENSE_RANK has no gaps, and LAG/LEAD access previous or next row values. They're powerful for analytics and reporting.

```sql
SELECT name, salary,
    ROW_NUMBER() OVER (ORDER BY salary DESC) as row_num,
    RANK() OVER (ORDER BY salary DESC) as rank_num,
    LAG(salary) OVER (ORDER BY salary DESC) as prev_salary
FROM employees;
```

### 20. Explain the CASE statement in SQL.

CASE adds conditional logic to SQL queries, like an if-else statement. You can use it in SELECT, WHERE, or ORDER BY clauses to transform data based on conditions. It's perfect for categorizing data, handling null values, or creating calculated fields with different logic paths.

```sql
SELECT name, salary,
    CASE 
        WHEN salary > 80000 THEN 'High'
        WHEN salary > 50000 THEN 'Medium'
        ELSE 'Low'
    END as salary_category
FROM employees;
```

### 21. What is a CTE (Common Table Expression)?

A CTE is a temporary named result set that exists only during query execution. It's like creating a temporary table that you can reference multiple times in your main query. CTEs are more readable than subqueries and support recursion. They're perfect for breaking down complex queries into manageable parts.

```sql
WITH high_earners AS (
    SELECT * FROM employees WHERE salary > 80000
)
SELECT department, COUNT(*) 
FROM high_earners 
GROUP BY department;
```

### 22. What is the difference between IN and EXISTS?

IN checks if a value matches any value in a list or subquery result. EXISTS checks if a subquery returns at least one row. EXISTS is often faster because it stops at the first match, while IN has to evaluate all values. Use EXISTS for correlated subqueries and IN for simple value lists.

```sql
-- IN - checks for matching values
SELECT * FROM customers WHERE city IN ('New York', 'London');

-- EXISTS - checks if subquery returns rows
SELECT * FROM customers c WHERE EXISTS 
(SELECT 1 FROM orders o WHERE o.customer_id = c.id);
```

### 23. What is the difference between DISTINCT and GROUP BY?

DISTINCT removes duplicate rows from the result set, while GROUP BY groups rows for aggregate functions. DISTINCT is simpler for just eliminating duplicates. GROUP BY is more powerful because you can use aggregate functions like COUNT, SUM, or AVG with it. Think of DISTINCT as a simple deduplication tool.

```sql
-- DISTINCT - just removes duplicates
SELECT DISTINCT city FROM customers;

-- GROUP BY - groups for aggregation
SELECT city, COUNT(*) FROM customers GROUP BY city;
```

### 24. What is the difference between DELETE, TRUNCATE, and DROP?

DELETE removes specific rows and can use WHERE clauses, it's logged and can be rolled back. TRUNCATE removes all rows quickly, resets identity counters, but can't be rolled back in most databases. DROP removes the entire table structure. DELETE is slowest but most flexible, TRUNCATE is fast for clearing tables, DROP destroys everything.

```sql
DELETE FROM employees WHERE department = 'Sales';  -- Specific rows
TRUNCATE TABLE temp_data;                          -- All rows, fast
DROP TABLE old_table;                              -- Entire table
```

### 25. How would you handle NULL values in SQL?

Use IS NULL or IS NOT NULL to check for nulls, never use equals. COALESCE returns the first non-null value from a list. ISNULL or IFNULL replaces nulls with a default value. Always consider nulls in your WHERE clauses because null comparisons return unknown, not true or false.

```sql
SELECT name, COALESCE(phone, 'No phone') as contact
FROM customers 
WHERE email IS NOT NULL;

SELECT * FROM products WHERE price IS NULL OR price = 0;
```

### 26. What is the difference between CHAR and VARCHAR?

CHAR has fixed length and pads with spaces, while VARCHAR has variable length up to a maximum. CHAR is faster for fixed-size data like country codes, but wastes space. VARCHAR saves space but has slight overhead. Use CHAR when all values are the same length, VARCHAR when lengths vary significantly.

```sql
CREATE TABLE countries (
    code CHAR(2),        -- Always 2 characters: 'US', 'UK'
    name VARCHAR(50)     -- Variable length: 'USA', 'United Kingdom'
);
```

### 27. What are stored procedures?

Stored procedures are precompiled SQL code blocks stored in the database. They accept parameters, contain business logic, and can return results. Benefits include better performance, security, code reusability, and centralized business logic. They're like functions in programming languages but executed on the database server.

```sql
CREATE PROCEDURE GetCustomerOrders(@CustomerID INT)
AS
BEGIN
    SELECT * FROM orders WHERE customer_id = @CustomerID;
END;

EXEC GetCustomerOrders 123;
```

### 28. What are triggers in SQL?

Triggers are special stored procedures that automatically execute in response to database events like INSERT, UPDATE, or DELETE. They're invisible to applications and enforce business rules, maintain audit trails, or sync data. BEFORE triggers run before the event, AFTER triggers run after. Use them carefully as they can impact performance.

```sql
CREATE TRIGGER audit_employee_changes
AFTER UPDATE ON employees
FOR EACH ROW
BEGIN
    INSERT INTO audit_log (table_name, action, timestamp)
    VALUES ('employees', 'UPDATE', NOW());
END;
```

### 29. What is a view in SQL?

A view is a virtual table based on a SELECT query. It doesn't store data physically but shows data from underlying tables dynamically. Views simplify complex queries, provide security by hiding sensitive columns, and create a consistent interface. They're like saved queries that look and act like tables.

```sql
CREATE VIEW active_customers AS
SELECT customer_id, name, email 
FROM customers 
WHERE status = 'active';

SELECT * FROM active_customers;
```

### 30. What is a schema in SQL?

A schema is a logical container that organizes database objects like tables, views, and procedures. It's like a namespace that groups related objects together and provides security boundaries. Different users can have different schemas, and you can have multiple schemas in one database for better organization and access control.

```sql
CREATE SCHEMA sales;
CREATE TABLE sales.customers (id INT, name VARCHAR(50));
CREATE TABLE sales.orders (id INT, customer_id INT);

SELECT * FROM sales.customers;
```

### 31. What is an aggregate function in SQL?

Aggregate functions perform calculations on multiple rows and return a single result. The main ones are COUNT for counting rows, SUM for totaling numbers, AVG for averages, MIN and MAX for smallest and largest values. They're essential for reporting and analytics, and you typically use them with GROUP BY to get results per category.

```sql
SELECT department, 
    COUNT(*) as employee_count,
    AVG(salary) as avg_salary,
    MAX(salary) as highest_salary
FROM employees 
GROUP BY department;
```

### 32. What is a transaction in SQL?

A transaction is a group of SQL operations that execute as a single unit - either all succeed or all fail. It follows ACID properties: Atomicity ensures all-or-nothing execution, Consistency maintains data integrity, Isolation prevents interference between concurrent transactions, and Durability guarantees committed changes persist even after system failures.

```sql
BEGIN TRANSACTION;
UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;
COMMIT;
```

### 33. What are the ROLLBACK, COMMIT, and SAVEPOINT commands?

COMMIT permanently saves all changes made in the current transaction. ROLLBACK undoes all changes and cancels the transaction. SAVEPOINT creates a checkpoint within a transaction that you can roll back to without canceling the entire transaction. Think of savepoints as bookmarks you can return to if something goes wrong.

```sql
BEGIN TRANSACTION;
INSERT INTO orders VALUES (1, 'Product A');
SAVEPOINT sp1;
INSERT INTO orders VALUES (2, 'Product B');
ROLLBACK TO sp1;  -- Only rolls back second insert
COMMIT;           -- Saves first insert
```

### 34. What is the EXPLAIN command used for?

EXPLAIN shows the execution plan for a query - how the database will retrieve the data. It reveals which indexes are used, join methods, and estimated costs. This helps identify performance bottlenecks like missing indexes or inefficient joins. It's your primary tool for query optimization and understanding why queries are slow.

```sql
EXPLAIN SELECT c.name, COUNT(o.id) 
FROM customers c 
LEFT JOIN orders o ON c.id = o.customer_id 
GROUP BY c.name;
```

### 35. What is SQL injection?

SQL injection is a security attack where malicious SQL code is inserted into application queries through user input. It can expose, modify, or delete data. Prevent it by using parameterized queries or prepared statements instead of concatenating user input directly into SQL strings. Never trust user input in SQL queries.

```sql
-- Vulnerable (bad)
query = "SELECT * FROM users WHERE name = '" + userInput + "'";

-- Safe (good)  
PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE name = ?");
ps.setString(1, userInput);
```

### 36. What is data masking in SQL?

Data masking replaces sensitive data with realistic but fake values for security and compliance. It's used in non-production environments where developers need realistic data without exposing real customer information. You can mask credit cards, emails, or names while preserving data relationships and formats for testing.

```sql
SELECT 
    customer_id,
    CONCAT('****-****-****-', RIGHT(credit_card, 4)) as masked_card,
    CONCAT(LEFT(email, 3), '***@', SUBSTRING_INDEX(email, '@', -1)) as masked_email
FROM customers;
```

### 37. What are the types of indexes in SQL?

The main types are clustered and non-clustered indexes. A clustered index physically reorders table data and there can only be one per table - usually on the primary key. Non-clustered indexes create separate structures pointing to data rows, and you can have multiple per table. Clustered is faster for range queries, non-clustered for specific lookups.

```sql
-- Clustered index (usually automatic on primary key)
CREATE CLUSTERED INDEX idx_customer_id ON customers(customer_id);

-- Non-clustered index
CREATE NONCLUSTERED INDEX idx_customer_email ON customers(email);
```

### 38. Explain the concept of Indexing and how it impacts query performance.

Indexing creates separate data structures that point to table rows, like a book's index. It dramatically speeds up SELECT queries by avoiding full table scans, but slows down INSERT, UPDATE, and DELETE because indexes must be maintained. Choose indexes based on your query patterns - index columns used in WHERE, JOIN, and ORDER BY clauses.

```sql
-- Without index: scans entire table
SELECT * FROM employees WHERE department = 'Sales';

-- Create index for faster lookups
CREATE INDEX idx_department ON employees(department);

-- Composite index for multiple columns
CREATE INDEX idx_dept_salary ON employees(department, salary);
```

### 39. How do you optimize SQL queries for better performance?

Key techniques include adding appropriate indexes, avoiding SELECT *, using WHERE clauses to filter early, optimizing JOIN order, and replacing subqueries with JOINs when possible. Also limit result sets with LIMIT, use EXISTS instead of IN for large datasets, and avoid functions in WHERE clauses. Always analyze execution plans with EXPLAIN.

```sql
-- Optimized query
SELECT e.name, d.department_name 
FROM employees e 
INNER JOIN departments d ON e.dept_id = d.id 
WHERE e.salary > 50000 
ORDER BY e.name 
LIMIT 100;
```

### 40. What are common performance bottlenecks in SQL queries?

Major bottlenecks include missing indexes causing full table scans, inefficient JOINs on large tables, using SELECT * instead of specific columns, functions in WHERE clauses preventing index usage, and poorly written subqueries. Also watch for large result sets without LIMIT, unnecessary GROUP BY operations, and queries that don't leverage existing indexes properly.

```sql
-- Bottleneck: function in WHERE prevents index usage
SELECT * FROM orders WHERE YEAR(order_date) = 2023;

-- Better: allows index usage
SELECT * FROM orders WHERE order_date >= '2023-01-01' AND order_date < '2024-01-01';
```

### 41. How do you handle large datasets in SQL?

For large datasets, use partitioning to split tables across multiple storage locations, implement proper indexing strategies, and process data in batches rather than all at once. Use LIMIT for pagination, consider archiving old data, and leverage database-specific features like table compression. Always monitor query performance and adjust based on actual usage patterns.

```sql
-- Partitioning by date
CREATE TABLE sales_2023 PARTITION OF sales 
FOR VALUES FROM ('2023-01-01') TO ('2024-01-01');

-- Batch processing
UPDATE products SET price = price * 1.1 
WHERE category = 'electronics' LIMIT 1000;
```

### 42. What is a materialized view?

A materialized view is a physical copy of query results stored on disk, unlike regular views which are virtual. It pre-computes and stores complex query results for faster access, especially useful for expensive aggregations or joins. You need to refresh it periodically to keep data current, but it dramatically improves read performance for analytical queries.

```sql
CREATE MATERIALIZED VIEW monthly_sales AS
SELECT DATE_TRUNC('month', order_date) as month, 
       SUM(amount) as total_sales
FROM orders 
GROUP BY DATE_TRUNC('month', order_date);

REFRESH MATERIALIZED VIEW monthly_sales;
```

### 43. What are recursive queries in SQL?

Recursive queries use CTEs to process hierarchical data by repeatedly applying the same logic. They have an anchor member (base case) and a recursive member that references itself. Perfect for organizational charts, bill of materials, or any tree-like structure where you need to traverse parent-child relationships to any depth.

```sql
WITH RECURSIVE employee_hierarchy AS (
    SELECT id, name, manager_id, 1 as level FROM employees WHERE manager_id IS NULL
    UNION ALL
    SELECT e.id, e.name, e.manager_id, eh.level + 1 
    FROM employees e 
    JOIN employee_hierarchy eh ON e.manager_id = eh.id
)
SELECT * FROM employee_hierarchy;
```

### 44. What is sharding in database design?

Sharding is horizontal partitioning where you split a large table across multiple databases or servers based on a shard key. Each shard contains a subset of data, distributing load and improving performance. It's complex to implement but essential for scaling beyond single-server limits. Choose shard keys carefully to avoid hotspots and ensure even distribution.

```sql
-- Shard by customer_id ranges
-- Shard 1: customer_id 1-1000000
-- Shard 2: customer_id 1000001-2000000
SELECT * FROM orders_shard_1 WHERE customer_id BETWEEN 1 AND 1000000;
SELECT * FROM orders_shard_2 WHERE customer_id BETWEEN 1000001 AND 2000000;
```

### 45. What is a database deadlock?

A deadlock occurs when two or more transactions wait for each other to release locks, creating a circular dependency that prevents any from proceeding. The database automatically detects and resolves deadlocks by killing one transaction. Prevent them by accessing tables in consistent order, keeping transactions short, and using appropriate isolation levels.

```sql
-- Transaction 1
BEGIN; UPDATE accounts SET balance = 100 WHERE id = 1;
       UPDATE accounts SET balance = 200 WHERE id = 2; COMMIT;

-- Transaction 2 (potential deadlock)
BEGIN; UPDATE accounts SET balance = 300 WHERE id = 2;
       UPDATE accounts SET balance = 400 WHERE id = 1; COMMIT;
```

### 46. What is full-text search in SQL?

Full-text search enables searching within text content using natural language queries, ranking results by relevance. It's more powerful than LIKE patterns, supporting stemming, synonyms, and phrase matching. Most databases provide built-in full-text capabilities that create specialized indexes for fast text searching across large documents or descriptions.

```sql
-- Create full-text index
CREATE FULLTEXT INDEX ft_product_description ON products(description);

-- Search with ranking
SELECT *, MATCH(description) AGAINST('wireless bluetooth headphones') as relevance
FROM products 
WHERE MATCH(description) AGAINST('wireless bluetooth headphones')
ORDER BY relevance DESC;
```

### 47. What is an EXCEPT clause in SQL?

EXCEPT returns rows from the first query that don't exist in the second query - essentially set subtraction. It's useful for finding missing records or differences between datasets. Similar to UNION, both queries must have the same number and compatible types of columns. Some databases use MINUS instead of EXCEPT for the same functionality.

```sql
-- Find customers who haven't placed orders
SELECT customer_id FROM customers
EXCEPT
SELECT DISTINCT customer_id FROM orders;

-- Find products not in any category
SELECT product_id FROM products
EXCEPT  
SELECT product_id FROM product_categories;
```

### 48. What are the advantages of using stored procedures over regular SQL queries?

Stored procedures offer better performance through pre-compilation, enhanced security by preventing SQL injection, centralized business logic, and reduced network traffic. They enable code reusability, parameter validation, and complex logic with loops and conditions. However, they're database-specific and can make application deployment more complex.

```sql
CREATE PROCEDURE ProcessOrder(@CustomerID INT, @ProductID INT, @Quantity INT)
AS
BEGIN
    DECLARE @Price DECIMAL(10,2);
    SELECT @Price = price FROM products WHERE id = @ProductID;
    
    INSERT INTO orders (customer_id, product_id, quantity, total)
    VALUES (@CustomerID, @ProductID, @Quantity, @Price * @Quantity);
END;
```

### 49. What is the difference between SQL Server and MySQL?

SQL Server is Microsoft's enterprise database with advanced features, strong Windows integration, and comprehensive tooling, but requires licensing fees. MySQL is open-source, cross-platform, and popular for web applications with good performance and community support. SQL Server excels in enterprise environments, while MySQL is preferred for cost-effective web solutions.

```sql
-- SQL Server syntax
SELECT TOP 10 * FROM customers ORDER BY created_date DESC;

-- MySQL syntax  
SELECT * FROM customers ORDER BY created_date DESC LIMIT 10;
```

### 50. What is a data warehouse, and how does it differ from OLTP?

A data warehouse is designed for analytical queries and reporting (OLAP), optimized for read-heavy workloads with denormalized schemas and historical data. OLTP systems handle daily transactions with normalized schemas optimized for fast inserts, updates, and deletes. Data warehouses aggregate data from multiple OLTP systems for business intelligence and decision-making.

```sql
-- OLTP: Normalized transaction processing
INSERT INTO orders (customer_id, product_id, quantity) VALUES (123, 456, 2);

-- OLAP: Denormalized analytical queries
SELECT customer_name, product_category, SUM(sales_amount) as total_sales
FROM sales_fact sf
JOIN customer_dim cd ON sf.customer_key = cd.customer_key
GROUP BY customer_name, product_category;
```