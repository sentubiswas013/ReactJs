Here are **clear, separate bullet-point answers** for your two earlier questions **plus** a set of **strong interview questions** for the SQL/DB line you shared.

---

# ✅ **1. Arrow vs Normal Function — Split Version**

### **Arrow Functions**

* Have shorter, cleaner syntax and **do not create their own `this` or `arguments`**.
* Cannot be used as constructors and are ideal for callbacks or when you want lexical `this`.

### **Normal Functions**

* Create their own **`this`, `arguments`, and prototype**, allowing more flexibility.
* Can be used as constructors and are preferred when you need dynamic `this` or to create instances.

---

# ✅ **2. Import vs Require — Two Bullet Points**

* **import (ES6)** → Statically analyzed, hoisted, supports tree-shaking, loads at compile time, preferred in modern JS apps.
* **require (CommonJS)** → Loaded dynamically at runtime, works in Node.js historically, not tree-shakable.

---

# ✅ **3. Interview Questions for:**

**“Drive database architecture, schema design, stored procedure development, and query optimization in SQL Server.”**

Here are strong questions commonly asked for this type of responsibility:

### **Database Architecture & Schema Design**

1. How do you design a scalable relational database schema from scratch?
2. What normalization forms do you follow, and when do you denormalize?
3. How do you design relationships (1–1, 1–many, many–many) and choose between JOIN tables vs bridge tables?
4. What factors do you consider while selecting data types?
5. How do you ensure referential integrity and reduce redundant data?

### **Stored Procedure Development**

6. When should you use stored procedures compared to inline SQL?
7. How do you handle error handling and transactions within stored procedures?
8. What is the difference between a stored procedure and a function in SQL Server?
9. How do you optimize stored procedures for large datasets?
10. How do you debug or test stored procedures?

### **Query Optimization**

11. What are SQL execution plans? How do you read them?
12. What is the purpose of indexing, and when can indexes hurt performance?
13. How do you detect slow queries in SQL Server?
14. What are clustered vs non-clustered indexes? When do you use which?
15. What techniques do you use to reduce full table scans?

### **Performance & Scalability**

16. How do you handle high-volume read/write workloads?
17. What is deadlocking? How do you resolve it?
18. What’s the difference between temp tables and table variables?
19. How do you partition large tables?
20. How do you design databases for microservices and distributed systems?

---

If you want, I can also prepare:
✅ **Short ideal answers to all the above questions**
or
✅ **A 1-page cheat sheet for SQL architecture interview prep**
