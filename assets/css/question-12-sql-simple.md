# SQL Interview Questions

A comprehensive collection of SQL interview questions organized by difficulty level and topic areas.

## **Basic SQL Questions**

### **SQL Fundamentals**
1. **What is SQL?**
   - Explain what SQL stands for and its purpose.

2. **What are the different types of SQL statements?**
   - DDL, DML, DCL, TCL, etc.

### **Database Design & Keys**
3. **What is a primary key?**
   - Define primary key and its purpose in database design.

4. **What is a foreign key?**
   - Explain foreign key constraints and referential integrity.

5. **What is a composite key?**
   - Define composite key and provide an example.

### **Basic Queries & Operations**
6. **What is a JOIN?**
   - Types of JOINs (INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL OUTER JOIN).

7. **What is the difference between `WHERE` and `HAVING`?**
   - Explain when to use `WHERE` vs `HAVING`.

8. **What is a subquery?**
   - Describe a subquery and its use cases.

9. **What are the different types of subqueries?**
   - Correlated vs. non-correlated subqueries.

10. **Explain the difference between `UNION` and `UNION ALL`.**
    - How do they differ in terms of duplicates?

11. **What is the difference between `GROUP BY` and `ORDER BY`?**
    - Their usage and differences.

12. **What is the `LIMIT`/`TOP` clause in SQL?**
    - How does it limit the number of rows in a query result?

### **Database Normalization**
13. **What is normalization?**
    - What are the normal forms (1NF, 2NF, 3NF, BCNF)?

14. **What is denormalization?**
    - When and why would you denormalize a database?

### **Indexing Basics**
15. **What is an index?**
    - Explain how indexing works and its impact on query performance.

## **Intermediate SQL Questions**

### **Advanced Joins & Relationships**
16. **What is the difference between `INNER JOIN` and `OUTER JOIN`?**
    - Explain the different types of OUTER JOINs.

17. **What is a self-join?**
    - Provide an example where a self-join would be useful.

18. **Explain the difference between a `LEFT JOIN` and a `RIGHT JOIN`.**
    - Detailed explanation and example use cases.

### **Advanced Query Techniques**
19. **What are window functions in SQL?**
    - Describe `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`, `NTILE()`, `LEAD()`, and `LAG()`.

20. **Explain the `CASE` statement in SQL.**
    - Use of `CASE` for conditional logic in queries.

21. **What is a CTE (Common Table Expression)?**
    - How do CTEs differ from subqueries?

22. **What is the difference between `IN` and `EXISTS`?**
    - How both are used in subqueries.

23. **What is the difference between `DISTINCT` and `GROUP BY`?**
    - How both clauses are used for eliminating duplicates.

### **Data Operations**
24. **What is the difference between `DELETE`, `TRUNCATE`, and `DROP`?**
    - Use cases and differences in terms of performance and data recovery.

25. **How would you handle NULL values in SQL?**
    - Methods for dealing with NULLs, including `IS NULL`, `COALESCE()`, `IFNULL()`.

### **Data Types & Storage**
26. **What is the difference between `CHAR` and `VARCHAR`?**
    - Differences in terms of storage and performance.

### **Database Objects**
27. **What are stored procedures?**
    - Define stored procedures and their benefits.

28. **What are triggers in SQL?**
    - Explain the types of triggers and how they are used.

29. **What is a view in SQL?**
    - Define views and when they should be used.

30. **What is a schema in SQL?**
    - Define schema and its use in organizing database objects.

### **Aggregate Functions**
31. **What is an aggregate function in SQL?**
    - Examples: `COUNT()`, `SUM()`, `AVG()`, `MIN()`, `MAX()`.

### **Transactions**
32. **What is a transaction in SQL?**
    - ACID properties (Atomicity, Consistency, Isolation, Durability).

33. **What are the `ROLLBACK`, `COMMIT`, and `SAVEPOINT` commands?**
    - Usage in managing transactions.

### **Query Analysis**
34. **What is the `EXPLAIN` command used for?**
    - How to use `EXPLAIN` to analyze and optimize query performance.

## **Advanced SQL Questions**

### **Security & Best Practices**
35. **What is SQL injection?**
    - How can SQL injection attacks be prevented?

36. **What is data masking in SQL?**
    - Define data masking and its use cases for security and compliance.

### **Performance Optimization**
37. **What are the types of indexes in SQL?**
    - Clustered vs. non-clustered indexes.

38. **Explain the concept of `Indexing` and how it impacts query performance.**
    - Discuss types of indexes (e.g., unique, composite) and their usage.

39. **How do you optimize SQL queries for better performance?**
    - Techniques like indexing, query restructuring, avoiding subqueries, etc.

40. **What are common performance bottlenecks in SQL queries?**
    - Discuss issues such as missing indexes, large result sets, inefficient joins, etc.

41. **How do you handle large datasets in SQL?**
    - Techniques for handling large datasets (partitioning, batching, indexing).

### **Advanced Database Concepts**
42. **What is a materialized view?**
    - Describe materialized views and their benefits in performance optimization.

43. **What are recursive queries in SQL?**
    - Example of recursive queries using CTEs.

44. **What is sharding in database design?**
    - Explain horizontal partitioning and its benefits.

45. **What is a database deadlock?**
    - Causes of deadlocks and how to avoid or resolve them.

46. **What is full-text search in SQL?**
    - How to implement and use full-text search features in SQL.

### **Advanced Operations**
47. **What is an `EXCEPT` clause in SQL?**
    - How `EXCEPT` works in SQL and its use cases.

48. **What are the advantages of using stored procedures over regular SQL queries?**
    - Benefits in terms of performance, security, and maintainability.

### **Database Systems & Architecture**
49. **What is the difference between SQL Server and MySQL?**
    - Compare features, performance, and use cases.

50. **What is a data warehouse, and how does it differ from OLTP?**
    - Difference between OLTP and OLAP, and the role of data warehouses.

---

*This comprehensive list covers SQL concepts from basic syntax to advanced database design and performance optimization. Questions are organized by difficulty level and topic areas for structured learning and interview preparation.*