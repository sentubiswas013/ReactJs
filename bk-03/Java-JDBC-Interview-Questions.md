# 15. Java JDBC 

## 1. What is JDBC?

JDBC (Java Database Connectivity) is a Java API that provides a standard way to connect and interact with relational databases. It acts as a bridge between Java applications and databases.

- Standard API for database connectivity
- Database-independent interface
- Supports SQL operations (CRUD)
- Works with any JDBC-compliant database
- Part of Java SE platform

JDBC allows Java applications to execute SQL statements, retrieve results, and manage database connections in a portable way across different database vendors.

## 2. What are the steps to connect to a database using JDBC?

There are 5 main steps to establish database connectivity using JDBC.

**Steps:**
1. **Load Driver:** Register database driver
2. **Create Connection:** Establish database connection
3. **Create Statement:** Prepare SQL statement
4. **Execute Query:** Run SQL and get results
5. **Close Resources:** Clean up connections

```java
// Step 1: Load driver (optional in modern JDBC)
Class.forName("com.mysql.cj.jdbc.Driver");

// Step 2: Create connection
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");

// Step 3: Create statement
Statement stmt = conn.createStatement();

// Step 4: Execute query
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// Step 5: Close resources
rs.close(); stmt.close(); conn.close();
```

## 3. What is the difference between Statement and PreparedStatement?

**Statement:**
- Executes static SQL queries
- SQL compiled every time
- Vulnerable to SQL injection
- Less efficient for repeated queries

**PreparedStatement:**
- Executes parameterized SQL queries
- Pre-compiled SQL (better performance)
- Prevents SQL injection
- Efficient for repeated queries with different parameters

```java
// Statement - vulnerable to SQL injection
Statement stmt = conn.createStatement();
String sql = "SELECT * FROM users WHERE id = " + userId; // Dangerous!
ResultSet rs = stmt.executeQuery(sql);

// PreparedStatement - safe and efficient
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```

## 4. What is connection pooling?

Connection pooling is a technique that maintains a cache of database connections that can be reused across multiple requests, improving application performance and resource utilization.

**Benefits:**
- Reduces connection creation overhead
- Limits number of concurrent connections
- Improves application performance
- Better resource management
- Handles connection lifecycle automatically

**Popular Connection Pools:**
- HikariCP (fastest)
- Apache DBCP
- C3P0
- Tomcat JDBC Pool

```java
// HikariCP example
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);

HikariDataSource dataSource = new HikariDataSource(config);
Connection conn = dataSource.getConnection(); // From pool
```

## 5. What is SQL injection and how to prevent it?

SQL injection is a security vulnerability where malicious SQL code is inserted into application queries, potentially allowing unauthorized database access or data manipulation.

**How it happens:**
- User input directly concatenated into SQL
- Malicious input modifies query logic
- Can lead to data theft, deletion, or unauthorized access

**Prevention methods:**
- Use PreparedStatement with parameters
- Input validation and sanitization
- Stored procedures
- Least privilege database access

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

## 6. What is transaction management in JDBC?

Transaction management ensures that a group of database operations either all succeed or all fail together, maintaining data consistency and integrity.

**ACID Properties:**
- **Atomicity:** All or nothing execution
- **Consistency:** Data remains valid
- **Isolation:** Concurrent transactions don't interfere
- **Durability:** Committed changes are permanent

**Transaction Control:**
- `setAutoCommit(false)` - Start transaction
- `commit()` - Save changes
- `rollback()` - Undo changes
- `setSavepoint()` - Create checkpoint

```java
Connection conn = null;
try {
    conn = DriverManager.getConnection(url, user, password);
    conn.setAutoCommit(false); // Start transaction
    
    // Multiple database operations
    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE id = ?");
    pstmt1.setDouble(1, 100.0);
    pstmt1.setInt(2, 1);
    pstmt1.executeUpdate();
    
    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?");
    pstmt2.setDouble(1, 100.0);
    pstmt2.setInt(2, 2);
    pstmt2.executeUpdate();
    
    conn.commit(); // All operations successful
    
} catch (SQLException e) {
    if (conn != null) {
        conn.rollback(); // Undo all changes
    }
} finally {
    if (conn != null) {
        conn.setAutoCommit(true); // Reset to default
        conn.close();
    }
}
```

Transaction management is crucial for maintaining data integrity, especially in financial applications where partial updates could lead to inconsistent data states.