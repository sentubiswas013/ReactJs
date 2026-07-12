

# ✅ 27. Java SQL

## 1. What is SQL?

**SQL (Structured Query Language)** is the standard language used to communicate with a **Relational Database**. It is used to **store**, **retrieve**, **update**, and **delete** data from database tables.

Databases such as MySQL, Oracle Database, PostgreSQL, and Microsoft SQL Server use SQL.


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


## 4. What is Database Indexing and When to Use It?


A **Database Index** is a **data structure** that improves the **speed of data retrieval** from a table. It works like the **index of a book**, allowing the database to find rows quickly without scanning the entire table.

**Key Features**

* Improves **SELECT** query performance.
* Reduces the need for a **Full Table Scan**.
* Created on one or more **columns**.
* Uses additional **storage space**.
* Slightly slows down **INSERT, UPDATE, and DELETE** operations because the index must also be updated.

**How It Works**

1. Create an **index** on one or more columns.
2. The database stores the indexed column values in a **sorted data structure** (commonly a **B-Tree**).
3. When a query searches on the indexed column, the database uses the index to locate matching rows quickly.
4. The database retrieves only the required rows instead of scanning the entire table.

**Syntax**

**Create an Index**

```sql
CREATE INDEX idx_employee_name
ON Employee(Name);
```

**Create a Unique Index**

```sql
CREATE UNIQUE INDEX idx_employee_email
ON Employee(Email);
```

**Example**

Without an index:

```sql
SELECT *
FROM Employee
WHERE Email = 'john@example.com';
```

* The database may perform a **Full Table Scan**.

After creating an index:

```sql
CREATE INDEX idx_email
ON Employee(Email);
```

Now the same query:

```sql
SELECT *
FROM Employee
WHERE Email = 'john@example.com';
```

* The database uses the **index** to find the row much faster.

**When to Use**

* Columns frequently used in the **WHERE** clause.
* Columns used in **JOIN** conditions.
* Columns used in **ORDER BY**.
* Columns used in **GROUP BY**.
* Columns frequently searched for specific values.
* **Primary Key** and **Unique** columns (most databases create indexes automatically).

**When Not to Use**

* Small tables where a **Full Table Scan** is faster.
* Columns with frequent **INSERT, UPDATE, DELETE** operations.
* Columns with very few unique values (for example, **Gender**, **Status**, **Yes/No**).
* Columns that are rarely used in queries.

**Advantages**

* Faster **SELECT** queries.
* Improves **JOIN**, **ORDER BY**, and **GROUP BY** performance.
* Reduces query execution time.
* Helps optimize large tables.

**Disadvantages**

* Uses additional **disk space**.
* Slows down **INSERT**, **UPDATE**, and **DELETE** operations.
* Too many indexes can reduce overall database performance.

**Common Interview Follow-up Questions**

**1. Why does an index improve performance?**

Because the database searches the **index** instead of scanning every row in the table, reducing the amount of data it needs to examine.

**2. Why do indexes slow down INSERT, UPDATE, and DELETE?**

Whenever data changes, the database must also **update the index**, which adds extra work.

**3. Can a table have multiple indexes?**

**Yes.** A table can have **multiple indexes** on different columns or combinations of columns.

**4. What is the difference between a Primary Key and an Index?**

| **Primary Key**                         | **Index**                           |
| --------------------------------------- | ----------------------------------- |
| Ensures **uniqueness** and **NOT NULL** | Improves **query performance**      |
| Only **one** per table                  | Multiple indexes allowed            |
| Automatically creates an index          | Can be **unique** or **non-unique** |

**5. What are the common types of indexes?**

* **Clustered Index** – Stores the table data in the same order as the index (usually only one per table).
* **Non-Clustered Index** – Stores the index separately and points to the actual rows (multiple allowed).
* **Unique Index** – Ensures all indexed values are unique.
* **Composite Index** – Created on **multiple columns**.


## 5. What is the Difference Between Stored Procedure and Aggregate Function?

**Stored Procedure** is a **precompiled SQL program** stored in the database. It can accept **parameters**, contain **business logic (IF, loops)**, and return results.

**Aggregate Functions** perform calculations on multiple rows and return a **single value**.
Common examples: `COUNT`, `SUM`, `AVG`, `MAX`, `MIN`.

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


## 6. What is Normalization? Types (1NF, 2NF, 3NF)?

**Normalization** is the process of organizing data in a database to **reduce redundancy (duplicate data)** and **improve data integrity** by dividing data into related tables.

**Benefits**

* **Eliminates duplicate data**
* **Maintains data consistency**
* **Reduces update, insert, and delete anomalies**
* **Improves data integrity**

**1NF (First Normal Form)**

**Rule**

* Each column should contain **atomic (single) values**.
* No **repeating groups** or multiple values in a single column.

**Example (Not in 1NF)**

| StudentID | Name | Subjects  |
| --------- | ---- | --------- |
| 101       | John | Java, SQL |

**After 1NF**

| StudentID | Name | Subject |
| --------- | ---- | ------- |
| 101       | John | Java    |
| 101       | John | SQL     |

**2NF (Second Normal Form)**

**Rule**

* Must satisfy **1NF**.
* Remove **partial dependency**, where a non-key column depends on only part of a composite primary key.

**Example**

**Before 2NF**

| StudentID | CourseID | StudentName | CourseName |
| --------- | -------- | ----------- | ---------- |

Primary Key: **(StudentID, CourseID)**

Here:

* **StudentName** depends only on **StudentID**.
* **CourseName** depends only on **CourseID**.

**After 2NF**

**Student Table**

| StudentID | StudentName |
| --------- | ----------- |

**Course Table**

| CourseID | CourseName |
| -------- | ---------- |

**Enrollment Table**

| StudentID | CourseID |
| --------- | -------- |

**3NF (Third Normal Form)**

**Rule**

* Must satisfy **2NF**.
* Remove **transitive dependency**, where a non-key column depends on another non-key column.

**Example**

**Before 3NF**

| EmployeeID | EmployeeName | DepartmentID | DepartmentName |
| ---------- | ------------ | ------------ | -------------- |

Here:

* **DepartmentName** depends on **DepartmentID**, not directly on **EmployeeID**.

**After 3NF**

**Employee Table**

| EmployeeID | EmployeeName | DepartmentID |
| ---------- | ------------ | ------------ |

**Department Table**

| DepartmentID | DepartmentName |
| ------------ | -------------- |



## 7. What is the Between DELETE, TRUNCATE, and DROP?


These are SQL commands used to remove data or database objects, but they work differently.

| **Feature**      | **DELETE**                                | **TRUNCATE**                       | **DROP**                           |
| ---------------- | ----------------------------------------- | ---------------------------------- | ---------------------------------- |
| Removes          | **Selected rows**                         | **All rows**                       | **Entire table/object**            |
| **WHERE** clause | **Yes**                                   | **No**                             | **No**                             |
| Table structure  | **Remains**                               | **Remains**                        | **Deleted**                        |
| Rollback         | **Yes** (if transaction is not committed) | **Depends on the database**        | **Depends on the database**        |
| Speed            | **Slower**                                | **Faster**                         | **Fastest**                        |
| Type             | **DML (Data Manipulation Language)**      | **DDL (Data Definition Language)** | **DDL (Data Definition Language)** |

**1. DELETE**

**DELETE** removes **specific rows** or **all rows** from a table while keeping the **table structure** intact.

**How It Works**

* Deletes rows **one by one**.
* Can use a **WHERE** clause to delete selected records.
* Can be **rolled back** before the transaction is committed (in transactional databases).

**Syntax**

```sql
DELETE FROM Employee
WHERE Id = 101;
```

**Delete All Rows**

```sql
DELETE FROM Employee;
```

**When to Use**

* Delete **specific records**.
* When you need **rollback support**.
* When deleting based on a **condition**.

**2. TRUNCATE**

**TRUNCATE** removes **all rows** from a table but keeps the **table structure**.

**How It Works**

* Removes **all records at once**.
* Cannot use a **WHERE** clause.
* Resets the **identity/auto-increment** value in many databases.
* Much **faster** than **DELETE** because it deallocates data pages instead of deleting rows one by one.

**Syntax**

```sql
TRUNCATE TABLE Employee;
```

**When to Use**

* Remove **all records** quickly.
* Reset a table before loading fresh data.
* When individual row deletion is not required.

**3. DROP**

**DROP** permanently removes the **entire database object**, including its **data**, **structure**, indexes, constraints, and permissions.

**How It Works**

* Deletes the **table definition** from the database.
* The table no longer exists after execution.

**Syntax**

```sql
DROP TABLE Employee;
```

**When to Use**

* Remove a table that is **no longer needed**.
* Delete database objects permanently.

**Example**

Suppose the **Employee** table contains:

```text
Id   Name
101  John
102  Alice
103  Bob
```

**DELETE**

```sql
DELETE FROM Employee
WHERE Id = 101;
```

**Result**

```text
102  Alice
103  Bob
```

**TRUNCATE**

```sql
TRUNCATE TABLE Employee;
```

**Result**

```text
Table exists, but contains 0 rows.
```

**DROP**

```sql
DROP TABLE Employee;
```

**Result**

```text
Employee table no longer exists.
```

**When to Use Which?**

* Use **DELETE** when removing **specific rows**.
* Use **TRUNCATE** when removing **all rows** but keeping the **table**.
* Use **DROP** when removing the **entire table** permanently.

**Common Interview Follow-up Questions**

**1. Which command is the fastest?**

**DROP** is generally the **fastest**, followed by **TRUNCATE**, then **DELETE**.

**2. Which command supports the WHERE clause?**

Only **DELETE** supports the **WHERE** clause.

**3. Which command resets the identity (auto-increment) value?**

**TRUNCATE** resets the **identity/auto-increment** value in many databases.

**4. Which command removes the table structure?**

Only **DROP** removes the **table structure**.


```sql
DELETE FROM employee WHERE id = 5;     -- removes one row, can rollback

TRUNCATE TABLE employee;               -- removes all rows, fast, no rollback

DROP TABLE employee;                   -- removes table entirely
```

Use `DELETE` for selective removal. `TRUNCATE` to clear a table fast. `DROP` only when you want to remove the table completely.


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


## 9. What is a View in SQL?

A **View** is a **virtual table** created from the result of a **SQL query**. It **does not store data** itself; instead, it displays data from one or more underlying tables whenever it is queried.

**Key Features**

* A **virtual table** based on a **SELECT** query.
* **Does not store data** (except **Materialized Views** in some databases).
* Can combine data from **multiple tables**.
* Improves **security** by exposing only required columns or rows.
* Simplifies **complex SQL queries**.

**How It Works**

1. Create a **View** using a **SELECT** statement.
2. The database stores only the **query definition**.
3. When the view is queried, the database executes the stored query.
4. The latest data from the underlying table(s) is returned.

**Syntax**

```sql
CREATE VIEW view_name AS
SELECT column1, column2
FROM table_name
WHERE condition;
```

**Example**

**Create a View**

```sql
CREATE VIEW EmployeeView AS
SELECT EmployeeId, EmployeeName, Department
FROM Employee
WHERE Department = 'IT';
```

**Query the View**

```sql
SELECT *
FROM EmployeeView;
```

**Output**

```text
EmployeeId  EmployeeName  Department
101         John          IT
102         Alice         IT
```

**When to Use**

* Simplify **complex JOIN** queries.
* Restrict access to sensitive columns (for example, **Salary**).
* Reuse frequently executed queries.
* Present customized data to different users.

**Advantages**

* Improves **security** by hiding sensitive data.
* Simplifies complex queries.
* Promotes **code reusability**.
* Always shows the **latest data** from the underlying tables.
* Makes SQL easier to maintain.

**Limitations**

* A standard **View** does **not store data**.
* Complex views may have slower performance.
* Some views are **not updatable**, especially those using **JOIN**, **GROUP BY**, or aggregate functions.



**Common Interview Follow-up Questions**

**1. Does a View store data?**

**No.** A standard **View** stores only the **SQL query**, not the actual data. Every time you query the view, it retrieves the latest data from the base table(s).

**2. Can we perform INSERT, UPDATE, or DELETE on a View?**

**Yes**, but only if the view is **updatable**. Views with **JOIN**, **GROUP BY**, **DISTINCT**, or aggregate functions are generally **not updatable**.

**3. What is the difference between a View and a Table?**

| **View**                        | **Table**                 |
| ------------------------------- | ------------------------- |
| **Virtual table**               | **Physical table**        |
| Usually **does not store data** | Stores actual data        |
| Created from a **SELECT** query | Stores records directly   |
| Always shows the latest data    | Data is physically stored |

**4. What is a Materialized View?**

A **Materialized View** **stores the query result physically**. It provides **faster read performance** but must be **refreshed** to reflect changes in the underlying tables.


## 11. What are different types of **JOINs**?

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

-- RIGHT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
RIGHT JOIN department d ON e.dept_id = d.id;

-- FULL OUTER JOIN
SELECT e.name, d.name FROM employee e
FULL OUTER JOIN department d ON e.dept_id = d.id;
```

Think of it as a Venn diagram — INNER is the overlap, LEFT keeps the left circle, FULL keeps both circles.


## 12. What is a **Primary Key** and **Foreign Key** ?

A **Primary Key** is a column (or combination of columns) that **uniquely identifies** each record in a table.

A **Foreign Key** is a column that **references the Primary Key** of another table to establish a relationship.

| **Feature**      | **Primary Key**                             | **Foreign Key**                                      |
| ---------------- | ------------------------------------------- | ---------------------------------------------------- |
| Purpose          | **Uniquely identifies** each row            | **Creates a relationship** between tables            |
| Duplicate Values | **Not Allowed**                             | **Allowed**                                          |
| NULL Values      | **Not Allowed**                             | **Allowed** (unless restricted)                      |
| Number per Table | Only **one Primary Key** (can be composite) | Multiple **Foreign Keys** allowed                    |
| References       | Own table                                   | **Primary Key** (or **Unique Key**) of another table |


## 12. What is the Difference Between UNION and UNION ALL?

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



## 12. What is SQL injection and how to prevent it?

**SQL Injection** is a **security vulnerability** where an attacker injects malicious SQL code into application queries, to **manipulate or access the database illegally**.

It can be prevented by using **Prepared Statements (Parameterized Queries)**, **input validation**, **ORM frameworks (like JPA/Hibernate)**, **stored procedures**, and **proper access control**.


```java
// Vulnerable code - SQL injection possible
String userId = request.getParameter("id");
String sql = "SELECT * FROM users WHERE id = " + userId;
// If userId = "1 OR 1=1", returns all users!

// Safe code - using PreparedStatement
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```


## 13. What is a Cursor in SQL and when should it be used ?

A **Cursor** is used to **process database rows one by one** instead of all at once.
It is useful when we need **row-by-row processing**, but it should be used carefully because it can be **slower than set-based queries**

**Why use it?**

* Prevents **OutOfMemoryError**
* Good for very large datasets
* Reduces heap usage


```java
DECLARE @name VARCHAR(50)

-- 1. Declare Cursor
DECLARE emp_cursor CURSOR FOR
SELECT name FROM employees

-- 2. Open Cursor
OPEN emp_cursor

-- 3. Fetch first row
FETCH NEXT FROM emp_cursor INTO @name

-- Loop through all rows
WHILE @@FETCH_STATUS = 0
BEGIN
    PRINT @name

    -- Fetch next row
    FETCH NEXT FROM emp_cursor INTO @name
END

-- 5. Close Cursor
CLOSE emp_cursor

-- 6. Deallocate Cursor
DEALLOCATE emp_cursor
```

```java
@Transactional
public void processProducts() {
    try (Stream<Product> stream = repo.findAllByStream()) {
        stream.forEach(product -> {
            // process record
        });
    }
}
```

## 14. What is Batch Processing?

**Batch Processing** is a technique where a **large number of records** are **processed together as a single batch** instead of processing each record individually. It is commonly used for **scheduled**, **repetitive**, and **high-volume** data processing.

**Key Features**

* **Processes Multiple Records** together
* **Scheduled Execution**
* **High Performance**
* **Automatic Retry** and **Restart**
* **Transaction Management**
* **Scalable** for large datasets

**How It Works**

1. A **Batch Job** is triggered manually or by a scheduler.
2. Data is **read** from a source (Database, CSV, File, API).
3. Data is **processed** (validate, transform, calculate).
4. Processed data is **written** to the destination.
5. The job completes and generates a **status/report**.

**Architecture Flow**

```text
Scheduler
    │
    ▼
Batch Job
    │
    ▼
Item Reader
    │
    ▼
Item Processor
    │
    ▼
Item Writer
    │
    ▼
Database / File
```

**Main Components (Spring Batch)**

| **Component**     | **Purpose**                           |
| ----------------- | ------------------------------------- |
| **Job**           | Represents the complete batch process |
| **Step**          | A single phase within the job         |
| **ItemReader**    | Reads data from the source            |
| **ItemProcessor** | Processes or transforms the data      |
| **ItemWriter**    | Writes data to the destination        |

**When to Use**

* **Payroll Processing**
* **Bank Transactions**
* **Invoice Generation**
* **Report Generation**
* **Data Migration**
* **ETL (Extract, Transform, Load)**

**Code Example**

```java
@Component
public class EmployeeProcessor
        implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) {
        employee.setSalary(employee.getSalary() * 1.10);
        return employee;
    }
}
```

**Advantages**

* **Efficient** for large datasets
* **Improves Performance**
* **Reduces Resource Usage**
* **Supports Restart** after failure
* **Easy to Automate**

**Disadvantages**

* **Not Suitable** for real-time processing
* Large jobs may take **more time** to complete
* Error handling can be **complex**


**Common Interview Follow-up**

**Q: What is the difference between Batch Processing and Stream Processing?**

| **Batch Processing**                                   | **Stream Processing**                                                    |
| ------------------------------------------------------ | ------------------------------------------------------------------------ |
| Processes **data in batches**.                         | Processes **data continuously** as it arrives.                           |
| Used for **scheduled jobs**.                           | Used for **real-time applications**.                                     |
| Higher **throughput**.                                 | Lower **latency**.                                                       |
| Example: **Payroll**, **ETL**, **Invoice Generation**. | Example: **Fraud Detection**, **Live Notifications**, **Stock Trading**. |


## 15. What is sharding in databases?

**Sharding** is a **database scaling technique** where a large database is **split into smaller, independent databases (called shards)**. Each shard stores a **portion of the data**, allowing the system to handle more users and data efficiently.

**Key Features**

* **Horizontal Scaling**
* **Distributes Data** across multiple databases
* **Improves Performance**
* **Reduces Database Load**
* **Supports High Availability**
* Each **Shard** is an independent database

**How It Works**

1. The application receives a request.
2. A **Sharding Key** (such as **User ID** or **Customer ID**) is used.
3. The application determines which **Shard** contains the data.
4. The request is sent only to that specific **Shard**.
5. The shard processes the request and returns the result.

**Architecture Flow**

```text
           Client
              │
              ▼
        Application
              │
      Sharding Logic
              │
    ┌─────────┼─────────┐
    ▼         ▼         ▼
 Shard 1   Shard 2   Shard 3
(User 1-1000) (1001-2000) (2001-3000)
```

**Types of Sharding**

| **Type**               | **Description**                                           |
| ---------------------- | --------------------------------------------------------- |
| **Range Sharding**     | Data is divided by a range (e.g., IDs 1–1000, 1001–2000). |
| **Hash Sharding**      | A **hash function** decides which shard stores the data.  |
| **Directory Sharding** | A lookup table maps data to the correct shard.            |
| **Geo Sharding**       | Data is split based on **region** or **location**.        |

**When to Use**

* **Large Databases**
* **High Traffic Applications**
* **Microservices**
* **E-commerce Platforms**
* **Banking Systems**
* **Social Media Applications**

**Code Example**

```java
int shardId = userId % 3;

switch (shardId) {
    case 0:
        // Query Shard 1
        break;
    case 1:
        // Query Shard 2
        break;
    case 2:
        // Query Shard 3
        break;
}
```

**Advantages**

* **Better Performance**
* **Horizontal Scalability**
* **Lower Load** on each database
* **Faster Queries**
* **Supports Large Datasets**

**Disadvantages**

* **More Complex Architecture**
* **Cross-Shard Queries** are difficult
* **Data Rebalancing** is required when adding new shards
* **Joins Across Shards** are expensive



**Common Interview Follow-up**

**Q: What is the difference between Sharding and Partitioning?**

| **Sharding**                                              | **Partitioning**                                                |
| --------------------------------------------------------- | --------------------------------------------------------------- |
| **Data is distributed across multiple database servers.** | **Data is divided within the same database server.**            |
| Supports **Horizontal Scaling**.                          | Supports **Logical Organization** and performance optimization. |
| Each shard has its own **database instance**.             | All partitions belong to the **same database**.                 |
| Used for **very large, distributed systems**.             | Used for **managing large tables** within one database.         |


## 15. What is stored procedure in sql?


A **Stored Procedure** is a **precompiled collection of SQL statements** stored in the database that performs a **specific task**. It can accept **input parameters**, execute **multiple SQL operations**, and optionally return **output values**.

**Key Features**

* **Precompiled** for better performance.
* Stored and managed inside the **database**.
* Supports **input** and **output parameters**.
* Can contain **SELECT, INSERT, UPDATE, DELETE**, loops, conditions, and transactions.
* Improves **code reusability**, **security**, and **maintainability**.

**How It Works**

1. Create the stored procedure once in the database.
2. Pass required **input parameters** while calling it.
3. The database executes the stored SQL statements.
4. Returns **result sets**, **output parameters**, or a **status**.

**Syntax**

```sql
CREATE PROCEDURE procedure_name
    @parameter datatype
AS
BEGIN
    -- SQL Statements
END;
```

**Example**

**Create Procedure**

```sql
CREATE PROCEDURE GetEmployeeById
    @EmpId INT
AS
BEGIN
    SELECT *
    FROM Employee
    WHERE Id = @EmpId;
END;
```

**Execute Procedure**

```sql
EXEC GetEmployeeById @EmpId = 101;
```

**Output**

```text
Id   Name     Department
101  John     IT
```

**When to Use**

* When the **same SQL logic** is executed repeatedly.
* To improve **performance** by using precompiled execution plans.
* To **encapsulate business logic** inside the database.
* To restrict direct table access and improve **security**.
* To perform **complex database operations** involving multiple SQL statements.

**Advantages**

* **Faster execution** due to precompilation.
* **Reusable** and reduces duplicate SQL code.
* Better **security** by granting execute permission instead of table access.
* Easier **maintenance** since logic is stored in one place.
* Reduces **network traffic** because a single procedure call can execute multiple statements.

**Limitations**

* Can become difficult to maintain if business logic grows too large.
* Database-specific syntax may reduce portability between different databases.
* Debugging complex stored procedures can be challenging.

**Common Interview Follow-up Questions**

**1. What is the difference between a Stored Procedure and a Function?**

| **Stored Procedure**                   | **Function**                                      |
| -------------------------------------- | ------------------------------------------------- |
| May or may not return a value          | Must return a value                               |
| Can perform **INSERT, UPDATE, DELETE** | Mainly used to compute and return a value         |
| Called using **EXEC**                  | Can be used inside **SELECT** statements          |
| Can return multiple result sets        | Returns a single value or table (depending on DB) |

**2. Why are Stored Procedures faster?**

Because they are **precompiled**, the database can **reuse the execution plan**, reducing query parsing and optimization overhead.

**3. Can a Stored Procedure return multiple values?**

Yes. It can return:

* **Result sets**
* **Output parameters**
* **Return status code**


# ✅ 29. Java Testing

##  1. What is unit testing in Java?
Unit testing is a software testing technique where individual components or modules of a software application are tested in isolation
It helps ensure that each unit of code performs as expected and catches bugs early in developmentjava

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

@Test
public void testAdd() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.add(2, 3));
}
```

## 2. What is JUnit?
JUnit is a popular open-source testing framework for Java that provides annotations and assertions for writing and running unit testsjava

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("hello"));
    }
}
```

## 3. What are the annotations used in JUnit?
Common JUnit annotations include @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @DisplayName, 

```java
@Disabledjava
public class LifecycleTest {
    @BeforeAll
    static void setupAll() { /* runs once before all tests */ }
    
    @BeforeEach
    void setup() { /* runs before each test */ }
    
    @Test
    @DisplayName("Test addition operation")
    void testAddition() { /* test method */ }
    
    @AfterEach
    void tearDown() { /* runs after each test */ }
    
    @AfterAll
    static void tearDownAll() { /* runs once after all tests */ }
}
```

## 4. What is TestNG?

TestNG is a testing framework inspired by JUnit but with more powerful features like data providers, parallel execution, and flexible test configurationjava

```java
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestNGExample {
    @DataProvider
    public Object[][] testData() {
        return new Object[][]{{1, 2, 3}, {4, 5, 9}};
    }
    
    @Test(dataProvider = "testData")
    public void testAdd(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }
}
```

## 5. What is the difference between JUnit and TestNG?
JUnit is simpler and widely adopted, while TestNG offers more advanced features like data providers, parallel execution, and better test configurationjava

```java
// JUnit 5
@ParameterizedTest
@ValueSource(strings = {"hello", "world"})
void testWithJUnit(String word) {
    assertNotNull(word);
}

// TestNG
@Test(dataProvider = "words")
void testWithTestNG(String word) {
    assertNotNull(word);
}
```

## 6. What is mocking in Java testing?
Mocking is creating fake objects that simulate the behavior of real objects to isolate the unit being tested from its dependenciesjava

```java
// Using Mockito
@Mock
private UserRepository userRepository;

@Test
void testGetUser() {
    User mockUser = new User("John", "john@email.com");
    when(userRepository.findById(1L)).thenReturn(mockUser);
    
    User result = userService.getUser(1L);
    assertEquals("John", result.getName());
}
```

## 7. What is Mockito?
Mockito is a popular Java mocking framework that allows you to create mock objects and define their behavior for testingjava
import static org.mockito.Mockito.*

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void testSendWelcomeEmail() {
        userService.registerUser("john@email.com");
        verify(emailService).sendEmail("john@email.com", "Welcome!");
    }
}
```

## 8. What is integration testing?
Integration testing verifies that different modules or services work correctly when integrated togetherjava

```java
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCreateUser() {
        User user = new User("John", "john@email.com");
        ResponseEntity<User> response = restTemplate.postForEntity("/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
```

## 9. What is test-driven development (TDD)?
TDD is a development approach where you write tests first, then write the minimum code to make tests pass, then refactorjava

```java
// Step 1: Write failing test
@Test
void testCalculateArea() {
    Circle circle = new Circle(5);
    assertEquals(78.54, circle.calculateArea(), 0.01);
}

// Step 2: Write minimum code to pass
public class Circle {
    private double radius;
    
    public Circle(double radius) { this.radius = radius; }
    
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

## 10. What is behavior-driven development (BDD)?
BDD extends TDD by writing tests in natural language that describes the behavior of the application from user's perspectivejava

```java
// Using Cucumber with Java
@Given("a user with email {string}")
public void aUserWithEmail(String email) {
    user = new User(email);
}

@When("the user logs in")
public void theUserLogsIn() {
    loginResult = authService.login(user);
}

@Then("the login should be successful")
public void theLoginShouldBeSuccessful() {
    assertTrue(loginResult.isSuccess());
}
```