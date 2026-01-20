## Custom Exceptions

### 66. How do you create custom exceptions?

Custom exceptions are created by **extending existing exception classes** to provide domain-specific error handling.

#### **Basic Custom Exception Structure:**
```java
// Checked custom exception
public class CustomCheckedException extends Exception {
    public CustomCheckedException() {
        super();
    }
    
    public CustomCheckedException(String message) {
        super(message);
    }
    
    public CustomCheckedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CustomCheckedException(Throwable cause) {
        super(cause);
    }
}

// Unchecked custom exception
public class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException() {
        super();
    }
    
    public CustomUncheckedException(String message) {
        super(message);
    }
    
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CustomUncheckedException(Throwable cause) {
        super(cause);
    }
}
```

#### **Domain-Specific Custom Exceptions:**
```java
// Banking domain exceptions
public class InsufficientFundsException extends Exception {
    private final double requestedAmount;
    private final double availableBalance;
    
    public InsufficientFundsException(double requestedAmount, double availableBalance) {
        super(String.format("Insufficient funds: requested %.2f, available %.2f", 
              requestedAmount, availableBalance));
        this.requestedAmount = requestedAmount;
        this.availableBalance = availableBalance;
    }
    
    public double getRequestedAmount() { return requestedAmount; }
    public double getAvailableBalance() { return availableBalance; }
}

// User management exceptions
public class UserNotFoundException extends RuntimeException {
    private final String userId;
    
    public UserNotFoundException(String userId) {
        super("User not found: " + userId);
        this.userId = userId;
    }
    
    public String getUserId() { return userId; }
}

// Validation exceptions
public class ValidationException extends Exception {
    private final List<String> errors;
    
    public ValidationException(List<String> errors) {
        super("Validation failed: " + String.join(", ", errors));
        this.errors = new ArrayList<>(errors);
    }
    
    public List<String> getErrors() { return new ArrayList<>(errors); }
}
```

#### **Usage Examples:**
```java
public class BankAccount {
    private double balance;
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }
}

public class UserService {
    public User findUser(String userId) {
        User user = database.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
    }
}
```

### 67. When should you create custom exceptions?

Create custom exceptions when **standard exceptions don't adequately describe your specific error conditions**.

#### **Good Reasons to Create Custom Exceptions:**

**1. Domain-Specific Error Conditions:**
```java
// E-commerce domain
public class ProductOutOfStockException extends Exception {
    private final String productId;
    private final int requestedQuantity;
    private final int availableQuantity;
    
    public ProductOutOfStockException(String productId, int requested, int available) {
        super(String.format("Product %s: requested %d, available %d", 
              productId, requested, available));
        this.productId = productId;
        this.requestedQuantity = requested;
        this.availableQuantity = available;
    }
    
    // Getters for recovery logic
    public String getProductId() { return productId; }
    public int getRequestedQuantity() { return requestedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
}
```

**2. API Consistency:**
```java
// Payment processing API
public abstract class PaymentException extends Exception {
    protected PaymentException(String message) { super(message); }
    protected PaymentException(String message, Throwable cause) { super(message, cause); }
}

public class PaymentDeclinedException extends PaymentException {
    private final String declineReason;
    
    public PaymentDeclinedException(String reason) {
        super("Payment declined: " + reason);
        this.declineReason = reason;
    }
    
    public String getDeclineReason() { return declineReason; }
}

public class PaymentTimeoutException extends PaymentException {
    public PaymentTimeoutException() {
        super("Payment processing timed out");
    }
}
```

**3. Additional Context and Recovery Information:**
```java
public class DatabaseConnectionException extends Exception {
    private final String databaseUrl;
    private final int retryCount;
    private final long lastAttemptTime;
    
    public DatabaseConnectionException(String url, int retryCount) {
        super(String.format("Failed to connect to database %s after %d attempts", url, retryCount));
        this.databaseUrl = url;
        this.retryCount = retryCount;
        this.lastAttemptTime = System.currentTimeMillis();
    }
    
    // Recovery information
    public boolean shouldRetry() { return retryCount < 3; }
    public long getNextRetryTime() { return lastAttemptTime + (retryCount * 1000); }
}
```

#### **When NOT to Create Custom Exceptions:**

**❌ Don't create custom exceptions for:**
```java
// Bad - standard exceptions work fine
public class MyNullPointerException extends RuntimeException { }  // Use NullPointerException
public class MyIllegalArgumentException extends RuntimeException { }  // Use IllegalArgumentException
public class MyIOException extends IOException { }  // Use IOException or its subclasses

// Bad - too generic
public class ApplicationException extends Exception { }  // Too broad, not helpful

// Bad - just wrapping without adding value
public class ServiceException extends Exception {
    public ServiceException(Exception e) { super(e); }  // No additional value
}
```

**✅ Good custom exception criteria:**
- **Specific error condition** that needs special handling
- **Additional context** beyond standard exceptions
- **Recovery information** for callers
- **Domain-specific** error that makes code more readable
- **API consistency** across your application layers

### 68. What is exception chaining?

**Exception chaining** links exceptions together to preserve the original cause while throwing a new exception.

#### **Why Use Exception Chaining:**
- **Preserve root cause** - don't lose original error information
- **Add context** - provide higher-level meaning to low-level errors
- **Maintain stack trace** - complete error trail for debugging
- **Layer abstraction** - convert implementation exceptions to domain exceptions

#### **Basic Exception Chaining:**
```java
public class ServiceLayer {
    public void processData(String data) throws ServiceException {
        try {
            // Low-level database operation
            database.save(data);
        } catch (SQLException e) {
            // Chain the SQLException as cause of ServiceException
            throw new ServiceException("Failed to process data", e);
        }
    }
}

public class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

#### **Accessing Chained Exceptions:**
```java
public class ExceptionChainExample {
    public void demonstrateChaining() {
        try {
            serviceLayer.processData("test data");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
            
            // Access the root cause
            Throwable cause = e.getCause();
            if (cause instanceof SQLException) {
                SQLException sqlEx = (SQLException) cause;
                System.out.println("Database error code: " + sqlEx.getErrorCode());
            }
            
            // Print complete stack trace (shows chain)
            e.printStackTrace();
        }
    }
}
```

#### **Multi-Level Chaining:**
```java
public class MultiLevelChaining {
    // Layer 1: Database layer
    public void databaseOperation() throws SQLException {
        throw new SQLException("Connection timeout", "08001");
    }
    
    // Layer 2: DAO layer
    public void dataAccess() throws DataAccessException {
        try {
            databaseOperation();
        } catch (SQLException e) {
            throw new DataAccessException("Data access failed", e);
        }
    }
    
    // Layer 3: Service layer
    public void businessLogic() throws BusinessException {
        try {
            dataAccess();
        } catch (DataAccessException e) {
            throw new BusinessException("Business operation failed", e);
        }
    }
    
    // Usage
    public void handleChainedExceptions() {
        try {
            businessLogic();
        } catch (BusinessException e) {
            // Walk the exception chain
            Throwable current = e;
            while (current != null) {
                System.out.println("Exception: " + current.getClass().getSimpleName() + 
                                 " - " + current.getMessage());
                current = current.getCause();
            }
        }
    }
}
```

#### **Using initCause() Method:**
```java
public class InitCauseExample {
    public void alternativeChaining() throws CustomException {
        try {
            riskyOperation();
        } catch (IOException e) {
            CustomException customEx = new CustomException("Operation failed");
            customEx.initCause(e);  // Alternative to constructor chaining
            throw customEx;
        }
    }
}
```

### 69. What is suppressed exception?

**Suppressed exceptions** occur when multiple exceptions happen and one exception suppresses others to prevent them from being lost.

#### **When Suppressed Exceptions Occur:**

**1. Try-with-resources:**
```java
public class SuppressedExceptionExample {
    static class ProblematicResource implements AutoCloseable {
        private final String name;
        
        public ProblematicResource(String name) {
            this.name = name;
        }
        
        public void doWork() throws Exception {
            throw new RuntimeException("Error during work in " + name);
        }
        
        @Override
        public void close() throws Exception {
            throw new RuntimeException("Error closing " + name);
        }
    }
    
    public void demonstrateSuppression() {
        try (ProblematicResource resource = new ProblematicResource("MyResource")) {
            resource.doWork();  // Throws exception
        } catch (Exception e) {
            System.out.println("Primary exception: " + e.getMessage());
            // Output: "Error during work in MyResource"
            
            // Access suppressed exceptions
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable s : suppressed) {
                System.out.println("Suppressed: " + s.getMessage());
                // Output: "Error closing MyResource"
            }
        }
    }
}
```

**2. Multiple Resources:**
```java
public class MultipleSuppressedExceptions {
    public void multipleResources() {
        try (ProblematicResource r1 = new ProblematicResource("Resource1");
             ProblematicResource r2 = new ProblematicResource("Resource2")) {
            
            throw new RuntimeException("Main operation failed");
            
        } catch (Exception e) {
            System.out.println("Primary: " + e.getMessage());
            // Output: "Main operation failed"
            
            // Both close() exceptions are suppressed
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());
                // Output: "Error closing Resource2"
                // Output: "Error closing Resource1"
            }
        }
    }
}
```

#### **Manual Suppression:**
```java
public class ManualSuppression {
    public void manualSuppressionExample() throws Exception {
        Exception primaryException = null;
        
        try {
            throw new RuntimeException("Primary exception");
        } catch (Exception e) {
            primaryException = e;
        }
        
        try {
            // Cleanup code that might fail
            cleanup();
        } catch (Exception cleanupException) {
            if (primaryException != null) {
                // Manually add suppressed exception
                primaryException.addSuppressed(cleanupException);
                throw primaryException;
            } else {
                throw cleanupException;
            }
        }
        
        if (primaryException != null) {
            throw primaryException;
        }
    }
    
    private void cleanup() throws Exception {
        throw new RuntimeException("Cleanup failed");
    }
}
```

#### **Accessing Suppressed Exceptions:**
```java
public class AccessingSuppressed {
    public void analyzeSuppressedExceptions(Exception e) {
        // Check if there are suppressed exceptions
        Throwable[] suppressed = e.getSuppressed();
        
        if (suppressed.length > 0) {
            System.out.println("Found " + suppressed.length + " suppressed exceptions:");
            
            for (int i = 0; i < suppressed.length; i++) {
                System.out.println((i + 1) + ". " + suppressed[i].getClass().getSimpleName() + 
                                 ": " + suppressed[i].getMessage());
                
                // Suppressed exceptions can also have their own suppressed exceptions
                Throwable[] nestedSuppressed = suppressed[i].getSuppressed();
                if (nestedSuppressed.length > 0) {
                    System.out.println("   Nested suppressed exceptions: " + nestedSuppressed.length);
                }
            }
        }
    }
}
```

### 70. How do you handle multiple exceptions in a single catch?

Use **multi-catch syntax** (Java 7+) to handle multiple exception types in one catch block.

#### **Multi-catch Syntax:**
```java
public class MultiCatchExample {
    public void processData(String input) {
        try {
            // Operations that can throw different exceptions
            int number = Integer.parseInt(input);        // NumberFormatException
            int[] array = new int[number];               // NegativeArraySizeException
            array[0] = 100 / number;                     // ArithmeticException
            
        } catch (NumberFormatException | NegativeArraySizeException | ArithmeticException e) {
            // Handle all three exceptions the same way
            System.out.println("Input processing error: " + e.getMessage());
            logError(e);
        }
    }
}
```

#### **Rules for Multi-catch:**

**1. No Inheritance Relationship:**
```java
public class MultiCatchRules {
    public void correctMultiCatch() {
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {  // ✓ Valid - no inheritance relationship
            handleException(e);
        }
    }
    
    public void incorrectMultiCatch() {
        try {
            riskyOperation();
        // } catch (Exception | RuntimeException e) {  // ✗ Invalid - RuntimeException extends Exception
        //     handleException(e);
        // }
    }
}
```

**2. Exception Variable is Final:**
```java
public class FinalExceptionVariable {
    public void demonstrateFinalVariable() {
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {
            // e is implicitly final
            System.out.println("Exception type: " + e.getClass().getSimpleName());
            
            // e = new IOException();  // ✗ Compilation error - cannot reassign
            
            // But you can call methods on e
            e.printStackTrace();
        }
    }
}
```

#### **Combining Multi-catch with Regular Catch:**
```java
public class CombinedCatchBlocks {
    public void combinedHandling() {
        try {
            complexOperation();
        } catch (FileNotFoundException e) {
            // Specific handling for file not found
            System.out.println("File not found: " + e.getMessage());
            createDefaultFile();
        } catch (IOException | SQLException e) {
            // Common handling for IO and SQL exceptions
            System.out.println("Data access error: " + e.getMessage());
            notifyAdministrator(e);
        } catch (Exception e) {
            // Catch-all for any other exceptions
            System.out.println("Unexpected error: " + e.getMessage());
            logUnexpectedError(e);
        }
    }
}
```

#### **Practical Examples:**

**Network Operations:**
```java
public class NetworkOperations {
    public void downloadFile(String url) {
        try {
            URL fileUrl = new URL(url);
            URLConnection connection = fileUrl.openConnection();
            // Download logic
        } catch (MalformedURLException | UnknownHostException | SocketTimeoutException e) {
            // All network-related exceptions handled similarly
            System.out.println("Network error: " + e.getMessage());
            scheduleRetry();
        } catch (IOException e) {
            // Other IO exceptions
            System.out.println("IO error: " + e.getMessage());
        }
    }
}
```

**Data Processing:**
```java
public class DataProcessor {
    public void processUserInput(String input) {
        try {
            validateAndProcess(input);
        } catch (NumberFormatException | IllegalArgumentException | DateTimeParseException e) {
            // All input validation errors
            showUserFriendlyError("Invalid input format: " + e.getMessage());
        } catch (SecurityException | AccessDeniedException e) {
            // Security-related exceptions
            logSecurityViolation(e);
            showAccessDeniedMessage();
        }
    }
}
```

#### **Benefits of Multi-catch:**
- **Reduced code duplication** - same handling logic for multiple exceptions
- **Cleaner code** - fewer catch blocks
- **Easier maintenance** - single place to update common exception handling
- **Better readability** - groups related exceptions together

#### **When to Use Multi-catch:**
```java
// ✓ Good use cases
catch (IOException | SQLException e) {
    // Both are data access related, handle similarly
    logDataAccessError(e);
    notifyUser("Data temporarily unavailable");
}

catch (NumberFormatException | IllegalArgumentException e) {
    // Both are input validation errors
    showInputValidationError(e.getMessage());
}

// ✗ Avoid when exceptions need different handling
catch (FileNotFoundException | OutOfMemoryError e) {
    // These need very different handling approaches
    // Better to handle separately
}
```
