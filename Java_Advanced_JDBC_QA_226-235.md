## Advanced JDBC 

### 226. What is connection pooling?

**Connection pooling** is a technique that **maintains a cache of database connections** for reuse:

#### Why Connection Pooling?
- **Expensive operation**: Creating database connections is costly
- **Resource management**: Limit concurrent connections
- **Performance**: Reuse existing connections
- **Scalability**: Handle multiple concurrent users

#### How It Works:
```
Application Request → Connection Pool → Available Connection
                                   ↓
Database Operations ← Connection ← Return to Pool
```

#### Without Connection Pooling:
```java
// Inefficient - creates new connection each time
public User getUser(int id) {
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
        
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        // Process result...
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
```

#### With Connection Pooling (HikariCP):
```java
// Efficient - reuses pooled connections
public class DatabaseConfig {
    private static HikariDataSource dataSource;
    
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("user");
        config.setPassword("password");
        config.setMaximumPoolSize(20);          // Max 20 connections
        config.setMinimumIdle(5);               // Min 5 idle connections
        config.setConnectionTimeout(30000);     // 30 second timeout
        config.setIdleTimeout(600000);          // 10 minute idle timeout
        
        dataSource = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

// Usage
public User getUser(int id) {
    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
        
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        // Process result...
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
```

#### Popular Connection Pool Libraries:
```xml
<!-- HikariCP (Recommended) -->
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.0.1</version>
</dependency>

<!-- Apache DBCP2 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-dbcp2</artifactId>
    <version>2.9.0</version>
</dependency>

<!-- C3P0 -->
<dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.5</version>
</dependency>
```

### 227. What is transaction management in JDBC?

**Transaction management** ensures **data consistency** by grouping multiple operations:

#### ACID Properties:
- **Atomicity**: All operations succeed or all fail
- **Consistency**: Database remains in valid state
- **Isolation**: Concurrent transactions don't interfere
- **Durability**: Committed changes are permanent

#### Basic Transaction Management:
```java
public void transferMoney(int fromAccount, int toAccount, BigDecimal amount) {
    Connection conn = null;
    try {
        conn = DriverManager.getConnection(url, user, password);
        
        // Start transaction
        conn.setAutoCommit(false);
        
        // Debit from source account
        PreparedStatement debitStmt = conn.prepareStatement(
            "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?"
        );
        debitStmt.setBigDecimal(1, amount);
        debitStmt.setInt(2, fromAccount);
        debitStmt.setBigDecimal(3, amount);
        
        int debitRows = debitStmt.executeUpdate();
        if (debitRows == 0) {
            throw new SQLException("Insufficient funds or account not found");
        }
        
        // Credit to destination account
        PreparedStatement creditStmt = conn.prepareStatement(
            "UPDATE accounts SET balance = balance + ? WHERE id = ?"
        );
        creditStmt.setBigDecimal(1, amount);
        creditStmt.setInt(2, toAccount);
        
        int creditRows = creditStmt.executeUpdate();
        if (creditRows == 0) {
            throw new SQLException("Destination account not found");
        }
        
        // Commit transaction
        conn.commit();
        System.out.println("Transfer successful");
        
    } catch (SQLException e) {
        // Rollback on error
        if (conn != null) {
            try {
                conn.rollback();
                System.out.println("Transaction rolled back");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);  // Restore auto-commit
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### Transaction Isolation Levels:
```java
// Set isolation level
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

// Available isolation levels:
// TRANSACTION_READ_UNCOMMITTED  - Dirty reads possible
// TRANSACTION_READ_COMMITTED    - Prevents dirty reads
// TRANSACTION_REPEATABLE_READ   - Prevents dirty and non-repeatable reads
// TRANSACTION_SERIALIZABLE      - Highest isolation, prevents all phenomena
```

#### Savepoints:
```java
public void complexTransaction() {
    try {
        conn.setAutoCommit(false);
        
        // First operation
        stmt.executeUpdate("INSERT INTO orders (id, customer_id) VALUES (1, 100)");
        
        // Create savepoint
        Savepoint savepoint1 = conn.setSavepoint("SavePoint1");
        
        try {
            // Second operation (might fail)
            stmt.executeUpdate("INSERT INTO order_items (order_id, product_id) VALUES (1, 200)");
            
            // Third operation
            stmt.executeUpdate("UPDATE inventory SET quantity = quantity - 1 WHERE product_id = 200");
            
        } catch (SQLException e) {
            // Rollback to savepoint (keeps first operation)
            conn.rollback(savepoint1);
            System.out.println("Rolled back to savepoint");
        }
        
        // Commit remaining operations
        conn.commit();
        
    } catch (SQLException e) {
        conn.rollback();  // Rollback everything
    }
}
```

### 228. What is batch processing in JDBC?

**Batch processing** executes **multiple SQL statements together** for better performance:

#### Benefits:
- **Reduced network round trips**
- **Better performance** for bulk operations
- **Atomic execution** of multiple statements
- **Efficient resource usage**

#### Statement Batch Processing:
```java
public void insertMultipleUsers(List<User> users) {
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {
        
        conn.setAutoCommit(false);
        
        // Add multiple statements to batch
        for (User user : users) {
            String sql = String.format(
                "INSERT INTO users (name, email) VALUES ('%s', '%s')",
                user.getName(), user.getEmail()
            );
            stmt.addBatch(sql);
        }
        
        // Execute all statements together
        int[] results = stmt.executeBatch();
        conn.commit();
        
        System.out.println("Inserted " + results.length + " users");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

#### PreparedStatement Batch Processing (Recommended):
```java
public void insertUsersWithPreparedStatement(List<User> users) {
    String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        conn.setAutoCommit(false);
        
        for (User user : users) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getAge());
            pstmt.addBatch();  // Add to batch
            
            // Execute batch in chunks for memory efficiency
            if (users.indexOf(user) % 1000 == 0) {
                pstmt.executeBatch();
                pstmt.clearBatch();
            }
        }
        
        // Execute remaining batch
        pstmt.executeBatch();
        conn.commit();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

#### Handling Batch Failures:
```java
public void batchWithErrorHandling(List<User> users) {
    String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        conn.setAutoCommit(false);
        
        for (User user : users) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.addBatch();
        }
        
        try {
            int[] results = pstmt.executeBatch();
            
            // Check results
            for (int i = 0; i < results.length; i++) {
                if (results[i] == Statement.EXECUTE_FAILED) {
                    System.out.println("Failed to insert user at index: " + i);
                }
            }
            
            conn.commit();
            
        } catch (BatchUpdateException e) {
            // Handle batch update exception
            int[] updateCounts = e.getUpdateCounts();
            System.out.println("Batch failed. Successful updates: " + updateCounts.length);
            conn.rollback();
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

### 229. What is SQL injection and how to prevent it?

**SQL injection** is a security vulnerability where **malicious SQL code** is inserted into queries:

#### How SQL Injection Works:
```java
// VULNERABLE CODE - Never do this!
public User loginUser(String username, String password) {
    String sql = "SELECT * FROM users WHERE username = '" + username + 
                "' AND password = '" + password + "'";
    
    // Malicious input: username = "admin'; --"
    // Resulting SQL: SELECT * FROM users WHERE username = 'admin'; --' AND password = ''
    // This bypasses password check!
    
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    // Process result...
}
```

#### Prevention Methods:

##### 1. Use PreparedStatement (Primary Defense):
```java
// SAFE CODE - Use PreparedStatement
public User loginUser(String username, String password) {
    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, username);  // Safely escaped
        pstmt.setString(2, password);  // Safely escaped
        
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
```

##### 2. Input Validation:
```java
public boolean isValidUsername(String username) {
    // Allow only alphanumeric characters and underscore
    return username != null && username.matches("^[a-zA-Z0-9_]{3,20}$");
}

public User loginUser(String username, String password) {
    // Validate input first
    if (!isValidUsername(username)) {
        throw new IllegalArgumentException("Invalid username format");
    }
    
    if (password == null || password.length() < 8) {
        throw new IllegalArgumentException("Invalid password");
    }
    
    // Proceed with PreparedStatement...
}
```

##### 3. Stored Procedures:
```sql
-- SQL stored procedure
DELIMITER //
CREATE PROCEDURE GetUserByCredentials(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100)
)
BEGIN
    SELECT id, username, email 
    FROM users 
    WHERE username = p_username AND password = p_password;
END //
DELIMITER ;
```

```java
// Java code using stored procedure
public User loginUserWithStoredProcedure(String username, String password) {
    String sql = "{call GetUserByCredentials(?, ?)}";
    
    try (CallableStatement cstmt = conn.prepareCall(sql)) {
        cstmt.setString(1, username);
        cstmt.setString(2, password);
        
        ResultSet rs = cstmt.executeQuery();
        
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
```

##### 4. Whitelist Validation for Dynamic Queries:
```java
public List<User> getUsersSorted(String sortColumn) {
    // Whitelist allowed columns
    Set<String> allowedColumns = Set.of("id", "username", "email", "created_date");
    
    if (!allowedColumns.contains(sortColumn)) {
        throw new IllegalArgumentException("Invalid sort column");
    }
    
    // Safe to use in dynamic SQL since it's whitelisted
    String sql = "SELECT * FROM users ORDER BY " + sortColumn;
    
    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        // Process results...
    }
}
```

### 230. What is the difference between execute(), executeQuery(), and executeUpdate()?

#### Method Comparison:

| Method | Purpose | Return Type | Use Case |
|--------|---------|-------------|----------|
| **execute()** | Any SQL statement | boolean | Unknown SQL type |
| **executeQuery()** | SELECT statements | ResultSet | Retrieving data |
| **executeUpdate()** | INSERT/UPDATE/DELETE/DDL | int | Modifying data |

#### execute() - Generic Method:
```java
public void executeGenericSQL(String sql) {
    try (Statement stmt = conn.createStatement()) {
        boolean hasResultSet = stmt.execute(sql);
        
        if (hasResultSet) {
            // SQL returned a ResultSet (SELECT)
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                // Process results
                System.out.println(rs.getString(1));
            }
        } else {
            // SQL returned update count (INSERT/UPDATE/DELETE)
            int updateCount = stmt.getUpdateCount();
            System.out.println("Rows affected: " + updateCount);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

#### executeQuery() - For SELECT:
```java
public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    String sql = "SELECT id, username, email FROM users";
    
    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {  // Returns ResultSet
        
        while (rs.next()) {
            User user = new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email")
            );
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
}
```

#### executeUpdate() - For Modifications:
```java
public class UserDAO {
    
    // INSERT
    public int insertUser(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            
            return pstmt.executeUpdate();  // Returns number of inserted rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // UPDATE
    public int updateUserEmail(int userId, String newEmail) {
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, userId);
            
            return pstmt.executeUpdate();  // Returns number of updated rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // DELETE
    public int deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            
            return pstmt.executeUpdate();  // Returns number of deleted rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // DDL operations
    public void createTable() {
        String sql = "CREATE TABLE temp_users (id INT PRIMARY KEY, name VARCHAR(50))";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);  // Returns 0 for DDL
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 231. What is ResultSetMetaData?

**ResultSetMetaData** provides **information about the structure** of a ResultSet:

#### Basic Usage:
```java
public void analyzeResultSet(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    
    int columnCount = metaData.getColumnCount();
    System.out.println("Number of columns: " + columnCount);
    
    // Iterate through columns
    for (int i = 1; i <= columnCount; i++) {
        String columnName = metaData.getColumnName(i);
        String columnType = metaData.getColumnTypeName(i);
        int columnSize = metaData.getColumnDisplaySize(i);
        boolean isNullable = metaData.isNullable(i) == ResultSetMetaData.columnNullable;
        
        System.out.printf("Column %d: %s (%s, size: %d, nullable: %b)%n",
            i, columnName, columnType, columnSize, isNullable);
    }
}
```

#### Dynamic Query Processing:
```java
public void printResultSetDynamically(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    
    // Print column headers
    for (int i = 1; i <= columnCount; i++) {
        System.out.printf("%-15s", metaData.getColumnName(i));
    }
    System.out.println();
    
    // Print separator
    for (int i = 1; i <= columnCount; i++) {
        System.out.print("---------------");
    }
    System.out.println();
    
    // Print data rows
    while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
            Object value = rs.getObject(i);
            System.out.printf("%-15s", value != null ? value.toString() : "NULL");
        }
        System.out.println();
    }
}
```

#### Converting ResultSet to Map:
```java
public List<Map<String, Object>> resultSetToMapList(ResultSet rs) throws SQLException {
    List<Map<String, Object>> results = new ArrayList<>();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    
    while (rs.next()) {
        Map<String, Object> row = new HashMap<>();
        
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            Object value = rs.getObject(i);
            row.put(columnName, value);
        }
        
        results.add(row);
    }
    
    return results;
}
```

#### Column Information Methods:
```java
public void getDetailedColumnInfo(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    
    for (int i = 1; i <= metaData.getColumnCount(); i++) {
        System.out.println("Column " + i + ":");
        System.out.println("  Name: " + metaData.getColumnName(i));
        System.out.println("  Label: " + metaData.getColumnLabel(i));
        System.out.println("  Type: " + metaData.getColumnType(i));
        System.out.println("  Type Name: " + metaData.getColumnTypeName(i));
        System.out.println("  Class Name: " + metaData.getColumnClassName(i));
        System.out.println("  Precision: " + metaData.getPrecision(i));
        System.out.println("  Scale: " + metaData.getScale(i));
        System.out.println("  Table Name: " + metaData.getTableName(i));
        System.out.println("  Schema Name: " + metaData.getSchemaName(i));
        System.out.println("  Is Auto Increment: " + metaData.isAutoIncrement(i));
        System.out.println("  Is Read Only: " + metaData.isReadOnly(i));
        System.out.println("---");
    }
}
```

### 232. What is DatabaseMetaData?

**DatabaseMetaData** provides **information about the database** itself:

#### Basic Database Information:
```java
public void getDatabaseInfo() throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    
    System.out.println("Database Product: " + metaData.getDatabaseProductName());
    System.out.println("Database Version: " + metaData.getDatabaseProductVersion());
    System.out.println("Driver Name: " + metaData.getDriverName());
    System.out.println("Driver Version: " + metaData.getDriverVersion());
    System.out.println("JDBC Version: " + metaData.getJDBCMajorVersion() + "." + 
                      metaData.getJDBCMinorVersion());
    System.out.println("Max Connections: " + metaData.getMaxConnections());
    System.out.println("Max Statement Length: " + metaData.getMaxStatementLength());
}
```

#### Table Information:
```java
public void listTables() throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    
    // Get all tables
    ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
    
    System.out.println("Tables in database:");
    while (tables.next()) {
        String tableName = tables.getString("TABLE_NAME");
        String tableType = tables.getString("TABLE_TYPE");
        String schema = tables.getString("TABLE_SCHEM");
        
        System.out.println("  " + schema + "." + tableName + " (" + tableType + ")");
    }
}
```

#### Column Information for a Table:
```java
public void getTableColumns(String tableName) throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    
    ResultSet columns = metaData.getColumns(null, null, tableName, "%");
    
    System.out.println("Columns for table " + tableName + ":");
    while (columns.next()) {
        String columnName = columns.getString("COLUMN_NAME");
        String dataType = columns.getString("TYPE_NAME");
        int columnSize = columns.getInt("COLUMN_SIZE");
        boolean isNullable = columns.getString("IS_NULLABLE").equals("YES");
        String defaultValue = columns.getString("COLUMN_DEF");
        
        System.out.printf("  %s %s(%d) %s %s%n",
            columnName, dataType, columnSize,
            isNullable ? "NULL" : "NOT NULL",
            defaultValue != null ? "DEFAULT " + defaultValue : "");
    }
}
```

#### Primary Keys and Foreign Keys:
```java
public void getTableKeys(String tableName) throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    
    // Primary keys
    ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
    System.out.println("Primary Keys:");
    while (primaryKeys.next()) {
        String columnName = primaryKeys.getString("COLUMN_NAME");
        String pkName = primaryKeys.getString("PK_NAME");
        System.out.println("  " + columnName + " (PK: " + pkName + ")");
    }
    
    // Foreign keys
    ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
    System.out.println("Foreign Keys:");
    while (foreignKeys.next()) {
        String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
        String pkTableName = foreignKeys.getString("PKTABLE_NAME");
        String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
        
        System.out.println("  " + fkColumnName + " -> " + pkTableName + "." + pkColumnName);
    }
}
```

#### Database Capabilities:
```java
public void checkDatabaseCapabilities() throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    
    System.out.println("Database Capabilities:");
    System.out.println("  Supports Transactions: " + metaData.supportsTransactions());
    System.out.println("  Supports Batch Updates: " + metaData.supportsBatchUpdates());
    System.out.println("  Supports Savepoints: " + metaData.supportsSavepoints());
    System.out.println("  Supports Stored Procedures: " + metaData.supportsStoredProcedures());
    System.out.println("  Supports Multiple ResultSets: " + metaData.supportsMultipleResultSets());
    System.out.println("  Supports Outer Joins: " + metaData.supportsOuterJoins());
    System.out.println("  Supports ANSI92 SQL: " + metaData.supportsANSI92FullSQL());
}
```

### 233. How do you handle large objects (LOB) in JDBC?

**Large Objects (LOB)** handle **binary (BLOB)** and **character (CLOB)** data:

#### BLOB (Binary Large Object):
```java
public void insertImage(int userId, InputStream imageStream) throws SQLException {
    String sql = "INSERT INTO user_images (user_id, image_data) VALUES (?, ?)";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);
        pstmt.setBlob(2, imageStream);  // Set BLOB from InputStream
        
        pstmt.executeUpdate();
        System.out.println("Image inserted successfully");
    }
}

public void retrieveImage(int userId, String outputPath) throws SQLException, IOException {
    String sql = "SELECT image_data FROM user_images WHERE user_id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Blob blob = rs.getBlob("image_data");
                
                // Write to file
                try (InputStream inputStream = blob.getBinaryStream();
                     FileOutputStream outputStream = new FileOutputStream(outputPath)) {
                    
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                
                System.out.println("Image retrieved and saved to: " + outputPath);
            }
        }
    }
}
```

#### CLOB (Character Large Object):
```java
public void insertDocument(int docId, String content) throws SQLException {
    String sql = "INSERT INTO documents (id, content) VALUES (?, ?)";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, docId);
        
        // Create CLOB from string
        Clob clob = conn.createClob();
        clob.setString(1, content);
        pstmt.setClob(2, clob);
        
        pstmt.executeUpdate();
        System.out.println("Document inserted successfully");
    }
}

public String retrieveDocument(int docId) throws SQLException {
    String sql = "SELECT content FROM documents WHERE id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, docId);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Clob clob = rs.getClob("content");
                
                // Read CLOB content
                try (Reader reader = clob.getCharacterStream()) {
                    StringBuilder content = new StringBuilder();
                    char[] buffer = new char[4096];
                    int charsRead;
                    
                    while ((charsRead = reader.read(buffer)) != -1) {
                        content.append(buffer, 0, charsRead);
                    }
                    
                    return content.toString();
                }
            }
        }
    }
    return null;
}
```

#### Streaming Large Data:
```java
public void insertLargeFile(String filePath) throws SQLException, IOException {
    String sql = "INSERT INTO file_storage (filename, file_data) VALUES (?, ?)";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql);
         FileInputStream fileStream = new FileInputStream(filePath)) {
        
        File file = new File(filePath);
        pstmt.setString(1, file.getName());
        pstmt.setBinaryStream(2, fileStream, file.length());
        
        pstmt.executeUpdate();
        System.out.println("Large file inserted: " + filePath);
    }
}
```

### 234. What is scrollable ResultSet?

**Scrollable ResultSet** allows **bidirectional navigation** through query results:

#### Creating Scrollable ResultSet:
```java
// Create scrollable and updatable ResultSet
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,  // Scrollable type
    ResultSet.CONCUR_READ_ONLY          // Concurrency type
);

ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY id");
```

#### Navigation Methods:
```java
public void demonstrateScrolling(ResultSet rs) throws SQLException {
    // Move to different positions
    rs.first();           // Move to first row
    System.out.println("First user: " + rs.getString("name"));
    
    rs.last();            // Move to last row
    System.out.println("Last user: " + rs.getString("name"));
    
    rs.absolute(5);       // Move to 5th row
    System.out.println("5th user: " + rs.getString("name"));
    
    rs.relative(-2);      // Move 2 rows backward
    System.out.println("Current user: " + rs.getString("name"));
    
    rs.previous();        // Move to previous row
    System.out.println("Previous user: " + rs.getString("name"));
    
    // Check position
    System.out.println("Current row number: " + rs.getRow());
    System.out.println("Is first row: " + rs.isFirst());
    System.out.println("Is last row: " + rs.isLast());
    System.out.println("Is before first: " + rs.isBeforeFirst());
    System.out.println("Is after last: " + rs.isAfterLast());
}
```

#### ResultSet Types:
```java
// TYPE_FORWARD_ONLY (default)
Statement stmt1 = conn.createStatement(
    ResultSet.TYPE_FORWARD_ONLY,
    ResultSet.CONCUR_READ_ONLY
);

// TYPE_SCROLL_INSENSITIVE - scrollable, not sensitive to changes
Statement stmt2 = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY
);

// TYPE_SCROLL_SENSITIVE - scrollable, sensitive to changes
Statement stmt3 = conn.createStatement(
    ResultSet.TYPE_SCROLL_SENSITIVE,
    ResultSet.CONCUR_UPDATABLE
);
```

#### Practical Example - Pagination:
```java
public List<User> getUsersPage(int pageNumber, int pageSize) throws SQLException {
    String sql = "SELECT * FROM users ORDER BY id";
    
    try (Statement stmt = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = stmt.executeQuery(sql)) {
        
        List<User> users = new ArrayList<>();
        
        // Calculate starting position
        int startRow = (pageNumber - 1) * pageSize + 1;
        
        if (rs.absolute(startRow)) {
            int count = 0;
            do {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                users.add(user);
                count++;
            } while (rs.next() && count < pageSize);
        }
        
        return users;
    }
}
```

### 235. What is updatable ResultSet?

**Updatable ResultSet** allows **direct modification** of data through the ResultSet:

#### Creating Updatable ResultSet:
```java
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE         // Make it updatable
);

ResultSet rs = stmt.executeQuery("SELECT id, name, email, salary FROM employees");
```

#### Updating Existing Rows:
```java
public void updateEmployeeSalaries() throws SQLException {
    String sql = "SELECT id, name, salary FROM employees WHERE department = 'IT'";
    
    try (Statement stmt = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            double currentSalary = rs.getDouble("salary");
            double newSalary = currentSalary * 1.10;  // 10% raise
            
            // Update the row
            rs.updateDouble("salary", newSalary);
            rs.updateRow();  // Commit the update
            
            System.out.println("Updated salary for " + rs.getString("name") + 
                             " from " + currentSalary + " to " + newSalary);
        }
    }
}
```

#### Inserting New Rows:
```java
public void insertNewEmployee(String name, String email, double salary) throws SQLException {
    String sql = "SELECT id, name, email, salary FROM employees";
    
    try (Statement stmt = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
         ResultSet rs = stmt.executeQuery(sql)) {
        
        // Move to insert row
        rs.moveToInsertRow();
        
        // Set values for new row
        rs.updateString("name", name);
        rs.updateString("email", email);
        rs.updateDouble("salary", salary);
        
        // Insert the row
        rs.insertRow();
        
        // Move back to current row
        rs.moveToCurrentRow();
        
        System.out.println("Inserted new employee: " + name);
    }
}
```

#### Deleting Rows:
```java
public void deleteInactiveEmployees() throws SQLException {
    String sql = "SELECT id, name, last_login FROM employees";
    
    try (Statement stmt = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Date lastLogin = rs.getDate("last_login");
            
            // Delete if no login in last 365 days
            if (lastLogin != null) {
                long daysSinceLogin = (System.currentTimeMillis() - lastLogin.getTime()) 
                                    / (1000 * 60 * 60 * 24);
                
                if (daysSinceLogin > 365) {
                    String name = rs.getString("name");
                    rs.deleteRow();  // Delete current row
                    System.out.println("Deleted inactive employee: " + name);
                }
            }
        }
    }
}
```

#### Complete CRUD Example:
```java
public class UpdatableResultSetCRUD {
    
    public void performCRUDOperations() throws SQLException {
        String sql = "SELECT id, name, email, department FROM employees";
        
        try (Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // READ - Display all employees
            System.out.println("Current employees:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s, Dept: %s%n",
                    rs.getInt("id"), rs.getString("name"),
                    rs.getString("email"), rs.getString("department"));
            }
            
            // CREATE - Insert new employee
            rs.moveToInsertRow();
            rs.updateString("name", "John Doe");
            rs.updateString("email", "john.doe@company.com");
            rs.updateString("department", "Engineering");
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Inserted new employee");
            
            // UPDATE - Modify existing employee
            rs.first();
            if (rs.next()) {  // Move to second row
                rs.updateString("department", "Senior Engineering");
                rs.updateRow();
                System.out.println("Updated employee department");
            }
            
            // DELETE - Remove employee
            rs.last();
            rs.deleteRow();
            System.out.println("Deleted last employee");
        }
    }
}
```

## Best Practices

### 1. Always Use Connection Pooling:
```java
// Configure connection pool properly
HikariConfig config = new HikariConfig();
config.setMaximumPoolSize(20);
config.setMinimumIdle(5);
config.setConnectionTimeout(30000);
config.setIdleTimeout(600000);
config.setMaxLifetime(1800000);
```

### 2. Handle Transactions Properly:
```java
try {
    conn.setAutoCommit(false);
    // Multiple operations
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
    throw e;
} finally {
    conn.setAutoCommit(true);
}
```

### 3. Use PreparedStatement for Security:
```java
// Always use PreparedStatement for user input
String sql = "SELECT * FROM users WHERE email = ?";
try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, userEmail);
    // Execute query
}
```

### 4. Close Resources Properly:
```java
// Use try-with-resources
try (Connection conn = getConnection();
     PreparedStatement pstmt = conn.prepareStatement(sql);
     ResultSet rs = pstmt.executeQuery()) {
    // Use resources
} // Automatically closed
```

## Key Takeaways

1. **Connection pooling is essential** for production applications
2. **Transaction management ensures data consistency** with ACID properties
3. **Batch processing improves performance** for bulk operations
4. **PreparedStatement prevents SQL injection** - always use for user input
5. **execute(), executeQuery(), executeUpdate()** serve different purposes
6. **Metadata provides runtime information** about database structure
7. **LOBs handle large binary and character data** efficiently
8. **Scrollable and updatable ResultSets** provide advanced data manipulation
9. **Always handle exceptions and close resources** properly
10. **Follow security best practices** to prevent vulnerabilities