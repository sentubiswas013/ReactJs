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

