# 🔹 Database Connectivity (JDBC)

## JDBC Basics

### 216. What is JDBC?

**JDBC (Java Database Connectivity)** is a **Java API** for connecting and executing queries on databases:

#### Key Features:
- **Database independence**: Write once, run with any database
- **Standard API**: Consistent interface across different databases
- **SQL support**: Execute any SQL statement
- **Transaction management**: Handle database transactions
- **Metadata access**: Get information about database structure

#### Architecture:
```
Java Application
       ↓
   JDBC API
       ↓
  JDBC Driver
       ↓
   Database
```

#### Basic Example:
```java
// Load driver (optional in modern JDBC)
Class.forName("com.mysql.cj.jdbc.Driver");

// Establish connection
String url = "jdbc:mysql://localhost:3306/mydb";
Connection conn = DriverManager.getConnection(url, "user", "password");

// Execute query
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// Process results
while (rs.next()) {
    System.out.println(rs.getString("name"));
}

// Close resources
rs.close();
stmt.close();
conn.close();
```

### 217. What are the steps to connect to a database using JDBC?

#### Step-by-Step Process:

##### 1. Load JDBC Driver:
```java
// Explicit loading (optional in JDBC 4.0+)
Class.forName("com.mysql.cj.jdbc.Driver");

// Modern approach - automatic loading
// Driver loaded automatically from classpath
```

##### 2. Establish Connection:
```java
String url = "jdbc:mysql://localhost:3306/database_name";
String username = "your_username";
String password = "your_password";

Connection connection = DriverManager.getConnection(url, username, password);
```

##### 3. Create Statement:
```java
// Simple statement
Statement statement = connection.createStatement();

// Or prepared statement
PreparedStatement preparedStatement = connection.prepareStatement(
    "SELECT * FROM users WHERE age > ?"
);
```

##### 4. Execute SQL:
```java
// For SELECT queries
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

// For INSERT/UPDATE/DELETE
int rowsAffected = statement.executeUpdate("INSERT INTO users VALUES (...)");
```

##### 5. Process Results:
```java
while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    System.out.println("ID: " + id + ", Name: " + name);
}
```

##### 6. Close Resources:
```java
// Close in reverse order
if (resultSet != null) resultSet.close();
if (statement != null) statement.close();
if (connection != null) connection.close();
```

#### Complete Example:
```java
public class JDBCExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";
        
        try {
            // Step 1 & 2: Load driver and establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Step 3: Create statement
            Statement stmt = conn.createStatement();
            
            // Step 4: Execute query
            ResultSet rs = stmt.executeQuery("SELECT id, name, email FROM users");
            
            // Step 5: Process results
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---");
            }
            
            // Step 6: Close resources
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 218. What are JDBC drivers?

**JDBC drivers** are **software components** that enable Java applications to communicate with databases:

#### Purpose:
- **Translate JDBC calls** into database-specific protocol
- **Handle network communication** with database server
- **Manage connection lifecycle** and resource allocation
- **Convert data types** between Java and database formats

#### Driver Components:
```java
// Driver registration (automatic in JDBC 4.0+)
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

// Connection creation
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "pass"
);
```

#### Common JDBC Drivers:

##### MySQL:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

##### PostgreSQL:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>
```

##### Oracle:
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>21.9.0.0</version>
</dependency>
```

#### Driver Loading:
```java
// Old way (explicit loading)
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}

// Modern way (automatic loading)
// Just include driver JAR in classpath
Connection conn = DriverManager.getConnection(url, user, password);
```

### 219. What are the types of JDBC drivers?

#### Four Types of JDBC Drivers:

##### Type 1: JDBC-ODBC Bridge Driver
```java
// Example (deprecated)
String url = "jdbc:odbc:myDataSource";
Connection conn = DriverManager.getConnection(url);
```

**Characteristics:**
- Uses ODBC driver to connect to database
- **Deprecated** since Java 8
- Platform-dependent (requires ODBC)
- Poor performance due to multiple layers

##### Type 2: Native API Driver (Partly Java)
```java
// Example with Oracle OCI driver
String url = "jdbc:oracle:oci:@mydb";
Connection conn = DriverManager.getConnection(url, "user", "pass");
```

**Characteristics:**
- Uses database-specific native libraries
- Better performance than Type 1
- Platform-dependent
- Requires native library installation

##### Type 3: Network Protocol Driver (Pure Java)
```java
// Example with middleware server
String url = "jdbc:idb:server=myserver:port=1433";
Connection conn = DriverManager.getConnection(url, "user", "pass");
```

**Characteristics:**
- Pure Java implementation
- Uses middleware server
- Platform-independent
- Network overhead

##### Type 4: Pure Java Driver (Thin Driver)
```java
// MySQL example
String url = "jdbc:mysql://localhost:3306/mydb";
Connection conn = DriverManager.getConnection(url, "user", "pass");

// PostgreSQL example
String url = "jdbc:postgresql://localhost:5432/mydb";
Connection conn = DriverManager.getConnection(url, "user", "pass");
```

**Characteristics:**
- **Most commonly used**
- Pure Java implementation
- Platform-independent
- Best performance
- Direct database communication

#### Comparison Table:

| Type | Name | Platform Independent | Performance | Usage |
|------|------|---------------------|-------------|-------|
| **Type 1** | JDBC-ODBC Bridge | ❌ | Poor | Deprecated |
| **Type 2** | Native API | ❌ | Good | Legacy systems |
| **Type 3** | Network Protocol | ✅ | Good | Middleware |
| **Type 4** | Pure Java | ✅ | **Best** | **Recommended** |

### 220. What is Connection interface?

**Connection interface** represents a **session with a database**:

#### Key Responsibilities:
- **Database session management**
- **Transaction control**
- **Statement creation**
- **Metadata access**

#### Core Methods:

##### Creating Statements:
```java
Connection conn = DriverManager.getConnection(url, user, password);

// Create different types of statements
Statement stmt = conn.createStatement();
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
CallableStatement cstmt = conn.prepareCall("{call getUserById(?)}");
```

##### Transaction Management:
```java
try {
    // Disable auto-commit for transaction
    conn.setAutoCommit(false);
    
    // Execute multiple statements
    stmt1.executeUpdate("INSERT INTO accounts VALUES (1, 1000)");
    stmt2.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE id = 1");
    
    // Commit transaction
    conn.commit();
    
} catch (SQLException e) {
    // Rollback on error
    conn.rollback();
} finally {
    // Restore auto-commit
    conn.setAutoCommit(true);
}
```

##### Connection Properties:
```java
// Check connection status
boolean isValid = conn.isValid(5); // 5 second timeout

// Get database metadata
DatabaseMetaData metaData = conn.getMetaData();
String dbName = metaData.getDatabaseProductName();
String dbVersion = metaData.getDatabaseProductVersion();

// Set connection properties
conn.setReadOnly(true);  // Read-only connection
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
```

#### Connection URL Formats:
```java
// MySQL
String mysqlUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";

// PostgreSQL
String postgresUrl = "jdbc:postgresql://localhost:5432/mydb";

// Oracle
String oracleUrl = "jdbc:oracle:thin:@localhost:1521:xe";

// SQL Server
String sqlServerUrl = "jdbc:sqlserver://localhost:1433;databaseName=mydb";
```

### 221. What is Statement interface?

**Statement interface** is used to **execute static SQL statements**:

#### Basic Usage:
```java
Connection conn = DriverManager.getConnection(url, user, password);
Statement stmt = conn.createStatement();

// Execute different types of SQL
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
int rows = stmt.executeUpdate("INSERT INTO users VALUES (1, 'John', 'john@email.com')");
boolean hasResults = stmt.execute("SELECT COUNT(*) FROM users");
```

#### Key Methods:

##### executeQuery() - For SELECT:
```java
ResultSet rs = stmt.executeQuery("SELECT id, name, email FROM users");
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    String email = rs.getString("email");
    System.out.println(id + ": " + name + " - " + email);
}
```

##### executeUpdate() - For INSERT/UPDATE/DELETE:
```java
// INSERT
int insertedRows = stmt.executeUpdate(
    "INSERT INTO users (name, email) VALUES ('Alice', 'alice@email.com')"
);

// UPDATE
int updatedRows = stmt.executeUpdate(
    "UPDATE users SET email = 'newemail@domain.com' WHERE id = 1"
);

// DELETE
int deletedRows = stmt.executeUpdate("DELETE FROM users WHERE id = 1");

System.out.println("Rows affected: " + updatedRows);
```

##### execute() - For Any SQL:
```java
boolean hasResultSet = stmt.execute("SELECT * FROM users");

if (hasResultSet) {
    ResultSet rs = stmt.getResultSet();
    // Process result set
} else {
    int updateCount = stmt.getUpdateCount();
    System.out.println("Update count: " + updateCount);
}
```

#### Statement Configuration:
```java
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,  // Scrollable
    ResultSet.CONCUR_READ_ONLY          // Read-only
);

// Set query timeout
stmt.setQueryTimeout(30); // 30 seconds

// Set max rows
stmt.setMaxRows(100);
```

### 222. What is PreparedStatement?

**PreparedStatement** is a **precompiled SQL statement** with parameters:

#### Key Advantages:
- **Performance**: Precompiled and cached
- **Security**: Prevents SQL injection
- **Convenience**: Parameter binding
- **Reusability**: Execute multiple times with different parameters

#### Basic Usage:
```java
String sql = "SELECT * FROM users WHERE age > ? AND city = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);

// Set parameters (1-based indexing)
pstmt.setInt(1, 25);        // age > 25
pstmt.setString(2, "NYC");  // city = 'NYC'

ResultSet rs = pstmt.executeQuery();
```

#### Parameter Setting:
```java
PreparedStatement pstmt = conn.prepareStatement(
    "INSERT INTO users (name, age, email, created_date) VALUES (?, ?, ?, ?)"
);

// Set different parameter types
pstmt.setString(1, "John Doe");
pstmt.setInt(2, 30);
pstmt.setString(3, "john@email.com");
pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

int rowsInserted = pstmt.executeUpdate();
```

#### Batch Processing:
```java
PreparedStatement pstmt = conn.prepareStatement(
    "INSERT INTO users (name, email) VALUES (?, ?)"
);

// Add multiple batches
pstmt.setString(1, "User1");
pstmt.setString(2, "user1@email.com");
pstmt.addBatch();

pstmt.setString(1, "User2");
pstmt.setString(2, "user2@email.com");
pstmt.addBatch();

// Execute all batches
int[] results = pstmt.executeBatch();
```

#### Handling NULL Values:
```java
PreparedStatement pstmt = conn.prepareStatement(
    "UPDATE users SET phone = ? WHERE id = ?"
);

// Set NULL value
pstmt.setNull(1, Types.VARCHAR);  // phone = NULL
pstmt.setInt(2, 123);             // id = 123

pstmt.executeUpdate();
```

### 223. What is CallableStatement?

**CallableStatement** is used to **execute stored procedures and functions**:

#### Basic Usage:
```java
// Call stored procedure
CallableStatement cstmt = conn.prepareCall("{call getUserById(?)}");
cstmt.setInt(1, 123);
ResultSet rs = cstmt.executeQuery();

// Call function
CallableStatement cstmt2 = conn.prepareCall("{? = call calculateTotal(?)}");
cstmt2.registerOutParameter(1, Types.DECIMAL);
cstmt2.setInt(2, 456);
cstmt2.execute();
BigDecimal total = cstmt2.getBigDecimal(1);
```

#### Stored Procedure with OUT Parameters:
```sql
-- SQL stored procedure
CREATE PROCEDURE getUserStats(
    IN userId INT,
    OUT userName VARCHAR(100),
    OUT userCount INT
)
BEGIN
    SELECT name INTO userName FROM users WHERE id = userId;
    SELECT COUNT(*) INTO userCount FROM user_activities WHERE user_id = userId;
END
```

```java
// Java code to call procedure
CallableStatement cstmt = conn.prepareCall("{call getUserStats(?, ?, ?)}");

// Set IN parameter
cstmt.setInt(1, 123);

// Register OUT parameters
cstmt.registerOutParameter(2, Types.VARCHAR);
cstmt.registerOutParameter(3, Types.INTEGER);

// Execute procedure
cstmt.execute();

// Get OUT parameter values
String userName = cstmt.getString(2);
int activityCount = cstmt.getInt(3);

System.out.println("User: " + userName + ", Activities: " + activityCount);
```

#### Function Call:
```java
// Call database function
CallableStatement cstmt = conn.prepareCall("{? = call calculateDiscount(?, ?)}");

// Register return value
cstmt.registerOutParameter(1, Types.DECIMAL);

// Set input parameters
cstmt.setDecimal(2, new BigDecimal("100.00"));  // amount
cstmt.setString(3, "PREMIUM");                  // customer type

cstmt.execute();

BigDecimal discount = cstmt.getBigDecimal(1);
System.out.println("Discount: " + discount);
```

### 224. What is the difference between Statement and PreparedStatement?

#### Comparison Table:

| Aspect | Statement | PreparedStatement |
|--------|-----------|-------------------|
| **SQL Type** | Static SQL | Parameterized SQL |
| **Compilation** | Every execution | Once (precompiled) |
| **Performance** | Slower for repeated queries | Faster for repeated queries |
| **SQL Injection** | Vulnerable | Protected |
| **Parameters** | No parameter support | Parameter placeholders |
| **Caching** | Not cached | Cached by database |

#### Performance Comparison:
```java
// Statement - compiled every time
Statement stmt = conn.createStatement();
for (int i = 0; i < 1000; i++) {
    stmt.executeUpdate("INSERT INTO users VALUES (" + i + ", 'User" + i + "')");
}

// PreparedStatement - compiled once
PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES (?, ?)");
for (int i = 0; i < 1000; i++) {
    pstmt.setInt(1, i);
    pstmt.setString(2, "User" + i);
    pstmt.executeUpdate();
}
```

#### SQL Injection Vulnerability:
```java
// Statement - VULNERABLE to SQL injection
String userInput = "'; DROP TABLE users; --";
Statement stmt = conn.createStatement();
// This is dangerous!
stmt.executeQuery("SELECT * FROM users WHERE name = '" + userInput + "'");

// PreparedStatement - SAFE from SQL injection
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
pstmt.setString(1, userInput);  // Safely escaped
pstmt.executeQuery();
```

#### When to Use Each:

##### Use Statement for:
- **One-time queries**
- **Dynamic SQL structure**
- **DDL operations** (CREATE, ALTER, DROP)

```java
// DDL operations
Statement stmt = conn.createStatement();
stmt.execute("CREATE TABLE temp_table (id INT, name VARCHAR(50))");
stmt.execute("DROP TABLE old_table");
```

##### Use PreparedStatement for:
- **Repeated queries**
- **User input handling**
- **Better performance**
- **Security requirements**

```java
// Repeated queries with different parameters
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE age = ?");

pstmt.setInt(1, 25);
ResultSet rs1 = pstmt.executeQuery();

pstmt.setInt(1, 30);
ResultSet rs2 = pstmt.executeQuery();
```

### 225. What is ResultSet?

**ResultSet** represents the **result of a database query** as a table of data:

#### Basic Navigation:
```java
ResultSet rs = stmt.executeQuery("SELECT id, name, email, age FROM users");

// Navigate through rows
while (rs.next()) {
    // Get data by column index (1-based)
    int id = rs.getInt(1);
    String name = rs.getString(2);
    
    // Get data by column name (preferred)
    String email = rs.getString("email");
    int age = rs.getInt("age");
    
    System.out.println(id + ": " + name + " (" + email + ") - Age: " + age);
}
```

#### Data Type Retrieval:
```java
while (rs.next()) {
    // Numeric types
    int intValue = rs.getInt("int_column");
    long longValue = rs.getLong("long_column");
    double doubleValue = rs.getDouble("double_column");
    BigDecimal decimal = rs.getBigDecimal("decimal_column");
    
    // String types
    String stringValue = rs.getString("varchar_column");
    
    // Date/Time types
    Date date = rs.getDate("date_column");
    Time time = rs.getTime("time_column");
    Timestamp timestamp = rs.getTimestamp("timestamp_column");
    
    // Boolean
    boolean boolValue = rs.getBoolean("boolean_column");
    
    // Handle NULL values
    String nullableValue = rs.getString("nullable_column");
    if (rs.wasNull()) {
        System.out.println("Value was NULL");
    }
}
```

#### ResultSet Types:
```java
// Different ResultSet types
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,  // Can scroll forward/backward
    ResultSet.CONCUR_READ_ONLY          // Read-only
);

ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY id");

// Navigate in different directions
rs.next();        // Move to next row
rs.previous();    // Move to previous row
rs.first();       // Move to first row
rs.last();        // Move to last row
rs.absolute(5);   // Move to 5th row
rs.relative(2);   // Move 2 rows forward from current position

// Get position information
int currentRow = rs.getRow();
boolean isFirst = rs.isFirst();
boolean isLast = rs.isLast();
```

#### ResultSet Metadata:
```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
ResultSetMetaData metaData = rs.getMetaData();

int columnCount = metaData.getColumnCount();
System.out.println("Number of columns: " + columnCount);

for (int i = 1; i <= columnCount; i++) {
    String columnName = metaData.getColumnName(i);
    String columnType = metaData.getColumnTypeName(i);
    int columnSize = metaData.getColumnDisplaySize(i);
    
    System.out.println("Column " + i + ": " + columnName + 
                      " (" + columnType + ", size: " + columnSize + ")");
}
```

#### Processing Large ResultSets:
```java
// Set fetch size for better performance
Statement stmt = conn.createStatement();
stmt.setFetchSize(1000);  // Fetch 1000 rows at a time

ResultSet rs = stmt.executeQuery("SELECT * FROM large_table");

int count = 0;
while (rs.next()) {
    // Process row
    processRow(rs);
    
    count++;
    if (count % 10000 == 0) {
        System.out.println("Processed " + count + " rows");
    }
}
```

## Best Practices

### 1. Use Try-with-Resources:
```java
String sql = "SELECT * FROM users WHERE age > ?";
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
    pstmt.setInt(1, 25);
    try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

### 2. Use PreparedStatement for User Input:
```java
// Always use PreparedStatement for user input
public User findUserByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
```

### 3. Handle Transactions Properly:
```java
public void transferMoney(int fromAccount, int toAccount, BigDecimal amount) {
    try {
        conn.setAutoCommit(false);
        
        // Debit from account
        PreparedStatement debit = conn.prepareStatement(
            "UPDATE accounts SET balance = balance - ? WHERE id = ?"
        );
        debit.setBigDecimal(1, amount);
        debit.setInt(2, fromAccount);
        debit.executeUpdate();
        
        // Credit to account
        PreparedStatement credit = conn.prepareStatement(
            "UPDATE accounts SET balance = balance + ? WHERE id = ?"
        );
        credit.setBigDecimal(1, amount);
        credit.setInt(2, toAccount);
        credit.executeUpdate();
        
        conn.commit();
        
    } catch (SQLException e) {
        try {
            conn.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 4. Use Connection Pooling:
```java
// Use connection pooling libraries like HikariCP
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);

HikariDataSource dataSource = new HikariDataSource(config);

// Get connection from pool
try (Connection conn = dataSource.getConnection()) {
    // Use connection
}
```

## Key Takeaways

1. **JDBC provides database independence** through standard API
2. **Type 4 drivers are preferred** - pure Java, best performance
3. **Always use PreparedStatement** for user input to prevent SQL injection
4. **Close resources properly** - use try-with-resources
5. **Handle transactions explicitly** when needed
6. **Use connection pooling** in production applications
7. **ResultSet provides flexible data access** with metadata support
8. **Follow the standard connection steps** for reliable database access