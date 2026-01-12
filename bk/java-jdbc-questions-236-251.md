# 🔵 15. Database Connectivity (JDBC)
---
# 🔹 JDBC Basics
### Question 236: What is JDBC?

**Answer (25 seconds):**
* JDBC stands for Java Database Connectivity
* API that connects Java applications to databases
* Provides standard interface for database operations
* Database-independent - works with any JDBC-compliant database

```java
import java.sql.*;

Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");
```

---

### Question 237: What are the steps to connect to a database using JDBC?

**Answer (35 seconds):**
* **Load Driver**: Register JDBC driver (auto in modern Java)
* **Create Connection**: Use DriverManager.getConnection()
* **Create Statement**: PreparedStatement or Statement
* **Execute Query**: executeQuery() or executeUpdate()
* **Process Results**: Handle ResultSet
* **Close Resources**: Close connections, statements, resultsets

```java
Connection conn = DriverManager.getConnection(url, user, pass);
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
stmt.setInt(1, userId);
ResultSet rs = stmt.executeQuery();
```

---

### Question 238: What are the different types of JDBC drivers?

**Answer (30 seconds):**
* **Type 1**: JDBC-ODBC Bridge (deprecated)
* **Type 2**: Native API driver (platform-specific)
* **Type 3**: Network Protocol driver (middleware)
* **Type 4**: Pure Java driver (most common, database-specific)
* Type 4 is preferred - pure Java, best performance, platform-independent

```java
// Type 4 driver examples
"jdbc:mysql://localhost:3306/db"     // MySQL
"jdbc:postgresql://localhost/db"      // PostgreSQL
"jdbc:oracle:thin:@localhost:1521:xe" // Oracle
```

---

### Question 239: What is the difference between Statement and PreparedStatement?

**Answer (35 seconds):**
* **Statement**: Executes static SQL, compiled each time
* **PreparedStatement**: Pre-compiled SQL with parameters
* PreparedStatement prevents SQL injection attacks
* Better performance for repeated queries
* Supports parameter binding with setters

```java
// Statement (avoid for user input)
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// PreparedStatement (preferred)
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId);
```

---

### Question 240: What is CallableStatement?

**Answer (25 seconds):**
* Used to call stored procedures and functions in database
* Extends PreparedStatement interface
* Supports IN, OUT, and INOUT parameters
* Can return multiple result sets

```java
CallableStatement cstmt = conn.prepareCall("{call getUserById(?)}");
cstmt.setInt(1, userId);
ResultSet rs = cstmt.executeQuery();

// For procedures with OUT parameters
CallableStatement cstmt2 = conn.prepareCall("{call getCount(?, ?)}");
cstmt2.setString(1, "active");
cstmt2.registerOutParameter(2, Types.INTEGER);
```

---

### Question 241: What is connection pooling?

**Answer (30 seconds):**
* Technique to reuse database connections instead of creating new ones
* Improves performance by avoiding connection overhead
* Manages pool of pre-created connections
* Popular implementations: HikariCP, Apache DBCP, C3P0

```java
// HikariCP example
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setMaximumPoolSize(20);
HikariDataSource dataSource = new HikariDataSource(config);

Connection conn = dataSource.getConnection();
```

---

### Question 242: What is the difference between execute(), executeQuery(), and executeUpdate()?

**Answer (35 seconds):**
* **executeQuery()**: For SELECT statements, returns ResultSet
* **executeUpdate()**: For INSERT/UPDATE/DELETE, returns int (affected rows)
* **execute()**: For any SQL, returns boolean (true if ResultSet available)
* Use specific methods for better type safety and performance

```java
// SELECT - use executeQuery()
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// INSERT/UPDATE/DELETE - use executeUpdate()
int rows = stmt.executeUpdate("DELETE FROM users WHERE id = 1");

// Unknown SQL type - use execute()
boolean hasResultSet = stmt.execute(dynamicSQL);
```

---

### Question 243: What is ResultSet in JDBC?

**Answer (30 seconds):**
* Object that holds data retrieved from database after executing query
* Maintains cursor pointing to current row
* Provides getter methods to retrieve column values
* Initially positioned before first row - use next() to navigate

```java
ResultSet rs = stmt.executeQuery("SELECT id, name FROM users");
while(rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    System.out.println(id + ": " + name);
}
```

---

### Question 244: What are the different types of ResultSet?

**Answer (35 seconds):**
* **TYPE_FORWARD_ONLY**: Default, cursor moves forward only
* **TYPE_SCROLL_INSENSITIVE**: Scrollable, doesn't reflect DB changes
* **TYPE_SCROLL_SENSITIVE**: Scrollable, reflects DB changes
* **CONCUR_READ_ONLY**: Cannot update through ResultSet
* **CONCUR_UPDATABLE**: Can update database through ResultSet

```java
PreparedStatement stmt = conn.prepareStatement(
    "SELECT * FROM users", 
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE
);
ResultSet rs = stmt.executeQuery();
rs.absolute(5); // Jump to 5th row
```

---

### Question 245: What is transaction management in JDBC?

**Answer (35 seconds):**
* Group of SQL operations treated as single unit
* Either all operations succeed (commit) or all fail (rollback)
* Use setAutoCommit(false) to start manual transaction
* Call commit() to save changes or rollback() to undo

```java
conn.setAutoCommit(false);
try {
    stmt1.executeUpdate("INSERT INTO accounts...");
    stmt2.executeUpdate("UPDATE balance...");
    conn.commit(); // Success
} catch(Exception e) {
    conn.rollback(); // Failure
}
```

---

### Question 246: What is database transaction?

**Answer (25 seconds):**
* Logical unit of work containing one or more SQL operations
* Ensures data consistency and integrity
* Follows ACID properties
* All operations succeed together or fail together

```java
// Bank transfer example
BEGIN TRANSACTION
    UPDATE accounts SET balance = balance - 100 WHERE id = 1;
    UPDATE accounts SET balance = balance + 100 WHERE id = 2;
COMMIT;
```

---

### Question 247: What is ACID properties?

**Answer (35 seconds):**
* **Atomicity**: All operations succeed or all fail
* **Consistency**: Database remains in valid state
* **Isolation**: Concurrent transactions don't interfere
* **Durability**: Committed changes persist even after system failure
* These properties ensure reliable database transactions

```java
// ACID example in JDBC
conn.setAutoCommit(false); // Atomicity
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); // Isolation
// Consistency and Durability handled by database
```

---

### Question 248: What is isolation levels in database?

**Answer (40 seconds):**
* Controls how transaction changes are visible to other transactions
* **READ_UNCOMMITTED**: Lowest isolation, dirty reads possible
* **READ_COMMITTED**: Prevents dirty reads
* **REPEATABLE_READ**: Prevents dirty and non-repeatable reads
* **SERIALIZABLE**: Highest isolation, prevents all phenomena

```java
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
```

---

### Question 249: What is connection leakage?

**Answer (30 seconds):**
* When database connections are not properly closed after use
* Leads to connection pool exhaustion
* Application becomes unable to get new connections
* Always close connections in finally block or use try-with-resources

```java
// Proper connection handling
try (Connection conn = dataSource.getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
    // Use connection
} // Auto-closed here
```

---

### Question 250: What is batch processing in JDBC?

**Answer (30 seconds):**
* Technique to execute multiple SQL statements together
* Reduces network round trips to database
* Improves performance for bulk operations
* Use addBatch() and executeBatch() methods

```java
PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (?, ?)");
for(User user : users) {
    stmt.setInt(1, user.getId());
    stmt.setString(2, user.getName());
    stmt.addBatch();
}
int[] results = stmt.executeBatch();
```

---

### Question 251: What is SQL injection and how to prevent it?

**Answer (35 seconds):**
* Security vulnerability where malicious SQL code is inserted into queries
* Can lead to data theft, corruption, or unauthorized access
* **Prevention**: Use PreparedStatement with parameters
* Never concatenate user input directly into SQL strings
* Validate and sanitize all user inputs

```java
// Vulnerable (DON'T DO THIS)
String sql = "SELECT * FROM users WHERE name = '" + userName + "'";

// Safe (DO THIS)
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
stmt.setString(1, userName);
```
