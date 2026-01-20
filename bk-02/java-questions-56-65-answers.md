# 🔹 Exception Handling

## Exception Handling - Exception Basics

### 56. What is an exception in Java?

An **exception** is an abnormal event that occurs during program execution and disrupts the normal flow of instructions.

#### **Key Characteristics:**
- **Runtime event** - occurs during program execution
- **Object representation** - exceptions are objects
- **Disrupts flow** - interrupts normal program execution
- **Recoverable** - can be caught and handled
- **Graceful handling** - prevents program crashes

#### **Basic Example:**
```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;  // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero: " + e.getMessage());
        }
    }
}
```

#### **Exception vs Normal Flow:**
```java
// Normal flow
public int divide(int a, int b) {
    return a / b;  // Works fine when b != 0
}

// Exception scenario
public int safeDivide(int a, int b) {
    if (b == 0) {
        throw new ArithmeticException("Division by zero");
    }
    return a / b;
}
```

#### **Benefits of Exception Handling:**
- **Program stability** - prevents crashes
- **Error information** - provides details about what went wrong
- **Recovery mechanism** - allows alternative actions
- **Clean code** - separates error handling from business logic

### 57. What is the difference between error and exception?

| Aspect | Error | Exception |
|--------|-------|-----------|
| **Severity** | Serious, unrecoverable | Recoverable conditions |
| **Handling** | Should not be caught | Should be caught and handled |
| **Cause** | System-level problems | Application-level problems |
| **Recovery** | Not possible | Possible with proper handling |
| **Examples** | OutOfMemoryError, StackOverflowError | IOException, SQLException |

#### **Error Examples:**
```java
// These should NOT be caught
public class ErrorExamples {
    public void causeOutOfMemoryError() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]);  // OutOfMemoryError
        }
    }
    
    public void causeStackOverflowError() {
        causeStackOverflowError();  // Infinite recursion -> StackOverflowError
    }
}
```

#### **Exception Examples:**
```java
// These should be caught and handled
public class ExceptionExamples {
    public void readFile(String filename) {
        try {
            FileReader file = new FileReader(filename);  // May throw FileNotFoundException
            // Process file
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
    
    public void parseNumber(String str) {
        try {
            int number = Integer.parseInt(str);  // May throw NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + str);
        }
    }
}
```

#### **When to Handle vs When to Let Fail:**
```java
// Handle exceptions - recoverable
try {
    processUserInput(input);
} catch (InvalidInputException e) {
    showErrorMessage("Please enter valid input");
}

// Don't catch errors - let them fail fast
// try {
//     someMethod();
// } catch (OutOfMemoryError e) {  // ✗ Don't do this
//     // Cannot recover from this
// }
```

### 58. What is the exception hierarchy in Java?

```
                    Throwable
                   /         \
               Error         Exception
              /     \       /         \
    OutOfMemory  StackOverflow    IOException  RuntimeException
    Error        Error            SQLException      /        \
                                                NullPointer  ArrayIndexOutOf
                                                Exception    BoundsException
```

#### **Throwable Class:**
```java
// Root of all exceptions and errors
public class Throwable {
    private String message;
    private Throwable cause;
    
    public String getMessage() { return message; }
    public Throwable getCause() { return cause; }
    public void printStackTrace() { /* prints stack trace */ }
}
```

#### **Error Branch:**
```java
// System-level problems - don't catch these
class SystemErrors {
    // OutOfMemoryError - JVM runs out of memory
    // StackOverflowError - call stack exceeds limit
    // NoClassDefFoundError - class not found at runtime
    // VirtualMachineError - JVM problems
}
```

#### **Exception Branch:**
```java
// Application-level problems - should be handled

// Checked Exceptions (compile-time checking)
class CheckedExceptions {
    public void readFile() throws IOException {
        // IOException, FileNotFoundException
        // SQLException, ClassNotFoundException
    }
}

// Unchecked Exceptions (runtime checking)
class UncheckedExceptions {
    public void accessArray() {
        // RuntimeException and its subclasses
        // NullPointerException, ArrayIndexOutOfBoundsException
        // IllegalArgumentException, NumberFormatException
    }
}
```

#### **Practical Hierarchy Usage:**
```java
public class ExceptionHandling {
    public void handleSpecificExceptions() {
        try {
            riskyOperation();
        } catch (FileNotFoundException e) {
            // Most specific first
            System.out.println("File not found");
        } catch (IOException e) {
            // More general
            System.out.println("IO problem");
        } catch (Exception e) {
            // Most general last
            System.out.println("General exception");
        }
    }
}
```

### 59. What are checked and unchecked exceptions?

| Aspect | Checked Exceptions | Unchecked Exceptions |
|--------|-------------------|---------------------|
| **Compile-time** | Must be handled | No handling required |
| **Inheritance** | Extend Exception (not RuntimeException) | Extend RuntimeException |
| **When occurs** | Predictable conditions | Programming errors |
| **Examples** | IOException, SQLException | NullPointerException, ArrayIndexOutOfBoundsException |

#### **Checked Exceptions:**
```java
// Must be handled at compile time
public class CheckedExceptionExample {
    // Method declares it throws checked exception
    public void readFile(String filename) throws IOException {
        FileReader file = new FileReader(filename);  // Checked exception
        // Compiler forces handling
    }
    
    // Caller must handle or declare
    public void caller() {
        try {
            readFile("data.txt");  // Must catch
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
    
    // Or declare in method signature
    public void anotherCaller() throws IOException {
        readFile("data.txt");  // Pass exception up
    }
}
```

#### **Unchecked Exceptions:**
```java
// No compile-time handling required
public class UncheckedExceptionExample {
    public void accessArray() {
        int[] arr = {1, 2, 3};
        // This may throw ArrayIndexOutOfBoundsException
        // But compiler doesn't force handling
        System.out.println(arr[5]);  // Runtime exception
    }
    
    public void processString(String str) {
        // May throw NullPointerException
        // No compile-time checking required
        System.out.println(str.length());
    }
    
    // Optional handling
    public void safeProcessing(String str) {
        try {
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("String is null");
        }
    }
}
```

#### **Common Checked Exceptions:**
```java
// File operations
try {
    FileInputStream fis = new FileInputStream("file.txt");
} catch (FileNotFoundException e) { }

// Database operations
try {
    Connection conn = DriverManager.getConnection(url);
} catch (SQLException e) { }

// Network operations
try {
    URL url = new URL("http://example.com");
} catch (MalformedURLException e) { }

// Class loading
try {
    Class.forName("com.example.MyClass");
} catch (ClassNotFoundException e) { }
```

#### **Common Unchecked Exceptions:**
```java
// Null pointer access
String str = null;
str.length();  // NullPointerException

// Array bounds
int[] arr = new int[3];
arr[5] = 10;   // ArrayIndexOutOfBoundsException

// Number parsing
Integer.parseInt("abc");  // NumberFormatException

// Illegal arguments
Thread.sleep(-1);  // IllegalArgumentException
```

### 60. What is the difference between throw and throws?

| Aspect | throw | throws |
|--------|-------|--------|
| **Purpose** | Explicitly throw exception | Declare possible exceptions |
| **Location** | Inside method body | Method signature |
| **Syntax** | `throw new Exception()` | `throws Exception` |
| **Usage** | Execute exception throwing | Declare exception possibility |
| **Object** | Followed by exception object | Followed by exception class |

#### **throw - Explicit Exception Throwing:**
```java
public class ThrowExample {
    public void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age cannot exceed 150");
        }
        System.out.println("Valid age: " + age);
    }
    
    public void withdraw(double amount, double balance) {
        if (amount > balance) {
            throw new RuntimeException("Insufficient funds");
        }
        // Process withdrawal
    }
}
```

#### **throws - Exception Declaration:**
```java
public class ThrowsExample {
    // Declares that method might throw IOException
    public void readFile(String filename) throws IOException {
        FileReader file = new FileReader(filename);  // May throw IOException
        // Method doesn't handle it, passes to caller
    }
    
    // Multiple exception declaration
    public void databaseOperation() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");  // ClassNotFoundException
        Connection conn = DriverManager.getConnection(url);  // SQLException
    }
    
    // Caller must handle declared exceptions
    public void caller() {
        try {
            readFile("data.txt");
            databaseOperation();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

#### **Combined Usage:**
```java
public class CombinedExample {
    // Method declares exception and also throws it
    public void processFile(String filename) throws IOException {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }
        
        FileReader file = new FileReader(filename);  // May throw IOException
        // IOException is declared, IllegalArgumentException is unchecked
    }
}
```

### 61. What is try-catch-finally block?

The **try-catch-finally** construct provides structured exception handling in Java.

#### **Basic Structure:**
```java
try {
    // Risky code that might throw exceptions
} catch (SpecificException e) {
    // Handle specific exception
} catch (GeneralException e) {
    // Handle more general exception
} finally {
    // Cleanup code - always executes
}
```

#### **Complete Example:**
```java
public class TryCatchFinallyExample {
    public void processFile(String filename) {
        FileReader file = null;
        BufferedReader reader = null;
        
        try {
            file = new FileReader(filename);
            reader = new BufferedReader(file);
            String line = reader.readLine();
            System.out.println("First line: " + line);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            // Cleanup resources
            try {
                if (reader != null) reader.close();
                if (file != null) file.close();
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
```

#### **Execution Flow:**
```java
public class ExecutionFlow {
    public void demonstrateFlow() {
        System.out.println("1. Before try");
        
        try {
            System.out.println("2. In try block");
            int result = 10 / 0;  // Exception here
            System.out.println("3. This won't execute");
        } catch (ArithmeticException e) {
            System.out.println("4. In catch block");
        } finally {
            System.out.println("5. In finally block");
        }
        
        System.out.println("6. After try-catch-finally");
    }
    
    // Output:
    // 1. Before try
    // 2. In try block
    // 4. In catch block
    // 5. In finally block
    // 6. After try-catch-finally
}
```

#### **Finally Block Guarantees:**
```java
public class FinallyGuarantees {
    public String testFinally() {
        try {
            return "try";
        } catch (Exception e) {
            return "catch";
        } finally {
            System.out.println("Finally always executes");
            // Even when return statements exist
        }
    }
    
    public void testFinallyWithException() {
        try {
            throw new RuntimeException("Exception");
        } finally {
            System.out.println("Finally executes even with unhandled exception");
        }
    }
}
```

### 62. Can you have multiple catch blocks?

**Yes**, you can have multiple catch blocks to handle different types of exceptions.

#### **Multiple Catch Blocks:**
```java
public class MultipleCatchExample {
    public void processData(String data) {
        try {
            // Multiple operations that can throw different exceptions
            int number = Integer.parseInt(data);  // NumberFormatException
            int[] array = new int[number];        // NegativeArraySizeException
            array[0] = 100 / number;              // ArithmeticException
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + data);
        } catch (NegativeArraySizeException e) {
            System.out.println("Array size cannot be negative");
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
```

#### **Order Matters - Specific to General:**
```java
public class CatchOrder {
    public void correctOrder() {
        try {
            riskyOperation();
        } catch (FileNotFoundException e) {      // Most specific
            System.out.println("File not found");
        } catch (IOException e) {               // More general
            System.out.println("IO error");
        } catch (Exception e) {                 // Most general
            System.out.println("General error");
        }
    }
    
    // This won't compile - unreachable catch
    public void incorrectOrder() {
        try {
            riskyOperation();
        } catch (Exception e) {                 // Too general first
            System.out.println("General error");
        // } catch (IOException e) {            // ✗ Unreachable code
        //     System.out.println("IO error");
        // }
    }
}
```

#### **Multi-catch (Java 7+):**
```java
public class MultiCatch {
    public void handleMultipleExceptions() {
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {
            // Handle both exceptions the same way
            System.out.println("Database or IO error: " + e.getMessage());
            // Note: 'e' is effectively final
        } catch (Exception e) {
            System.out.println("Other error: " + e.getMessage());
        }
    }
}
```

### 63. What is try-with-resources?

**Try-with-resources** automatically manages resources that implement the `AutoCloseable` interface.

#### **Syntax:**
```java
try (ResourceType resource = new ResourceType()) {
    // Use resource
} catch (Exception e) {
    // Handle exceptions
}
// Resource automatically closed here
```

#### **Basic Example:**
```java
public class TryWithResourcesExample {
    // Old way - manual resource management
    public void readFileOldWay(String filename) {
        FileReader file = null;
        BufferedReader reader = null;
        
        try {
            file = new FileReader(filename);
            reader = new BufferedReader(file);
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (file != null) file.close();
            } catch (IOException e) {
                System.out.println("Error closing resources");
            }
        }
    }
    
    // New way - automatic resource management
    public void readFileNewWay(String filename) {
        try (FileReader file = new FileReader(filename);
             BufferedReader reader = new BufferedReader(file)) {
            
            System.out.println(reader.readLine());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Resources automatically closed
    }
}
```

#### **Multiple Resources:**
```java
public class MultipleResources {
    public void copyFile(String source, String destination) {
        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(destination);
             BufferedInputStream bufferedInput = new BufferedInputStream(input);
             BufferedOutputStream bufferedOutput = new BufferedOutputStream(output)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                bufferedOutput.write(buffer, 0, bytesRead);
            }
            
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        // All resources automatically closed in reverse order
    }
}
```

#### **Custom AutoCloseable Resource:**
```java
public class CustomResource implements AutoCloseable {
    private String name;
    
    public CustomResource(String name) {
        this.name = name;
        System.out.println("Opening resource: " + name);
    }
    
    public void doSomething() {
        System.out.println("Using resource: " + name);
    }
    
    @Override
    public void close() {
        System.out.println("Closing resource: " + name);
    }
}

// Usage
public void useCustomResource() {
    try (CustomResource resource = new CustomResource("MyResource")) {
        resource.doSomething();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    // Output:
    // Opening resource: MyResource
    // Using resource: MyResource
    // Closing resource: MyResource
}
```

### 64. What happens if an exception occurs in finally block?

When an exception occurs in the finally block, it **suppresses** any exception from the try or catch block.

#### **Exception Suppression:**
```java
public class FinallyException {
    public void demonstrateSuppression() {
        try {
            throw new RuntimeException("Exception from try block");
        } finally {
            throw new RuntimeException("Exception from finally block");
        }
        // Only "Exception from finally block" is thrown
        // "Exception from try block" is suppressed
    }
    
    public void handleSuppressedExceptions() {
        try {
            demonstrateSuppression();
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            
            // Access suppressed exceptions
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable t : suppressed) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }
}
```

#### **Try-with-resources and Suppressed Exceptions:**
```java
public class SuppressedExceptionExample {
    static class ProblematicResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new Exception("Error closing resource");
        }
        
        public void doWork() throws Exception {
            throw new Exception("Error during work");
        }
    }
    
    public void demonstrateSuppressionInTryWithResources() {
        try (ProblematicResource resource = new ProblematicResource()) {
            resource.doWork();  // Throws exception
        } catch (Exception e) {
            System.out.println("Primary: " + e.getMessage());  // "Error during work"
            
            // The close() exception is suppressed
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());  // "Error closing resource"
            }
        }
    }
}
```

#### **Best Practices:**
```java
public class FinallyBestPractices {
    // ✗ Bad - finally exception masks original
    public void badPractice() {
        try {
            throw new RuntimeException("Important exception");
        } finally {
            throw new RuntimeException("Masks important exception");
        }
    }
    
    // ✓ Good - handle finally exceptions carefully
    public void goodPractice() {
        Exception originalException = null;
        
        try {
            throw new RuntimeException("Important exception");
        } catch (Exception e) {
            originalException = e;
            throw e;
        } finally {
            try {
                // Cleanup code that might throw
                riskyCleanup();
            } catch (Exception e) {
                if (originalException != null) {
                    e.addSuppressed(originalException);
                }
                // Log cleanup exception but don't throw
                System.err.println("Cleanup failed: " + e.getMessage());
            }
        }
    }
    
    private void riskyCleanup() throws Exception {
        // Cleanup code
    }
}
```

### 65. Can you throw an exception from finally block?

**Yes**, you can throw exceptions from finally block, but it's generally **not recommended**.

#### **Throwing from Finally:**
```java
public class ThrowFromFinally {
    public void throwFromFinally() {
        try {
            System.out.println("In try block");
        } finally {
            throw new RuntimeException("Exception from finally");
        }
        // This method will throw "Exception from finally"
    }
    
    public void suppressOriginalException() {
        try {
            throw new RuntimeException("Original exception");
        } finally {
            throw new RuntimeException("Finally exception");
        }
        // "Original exception" is suppressed
        // "Finally exception" is thrown
    }
}
```

#### **Problems with Throwing from Finally:**
```java
public class ProblemsWithFinallyThrow {
    public void maskingImportantException() {
        try {
            // Important business logic exception
            throw new IllegalStateException("Critical business rule violation");
        } finally {
            // Cleanup code throws exception
            throw new RuntimeException("Cleanup failed");
        }
        // The critical business exception is lost!
    }
    
    public void debuggingNightmare() {
        try {
            processImportantData();
        } catch (DataProcessingException e) {
            // Log and handle the data processing error
            logError(e);
            throw e;  // Re-throw for caller
        } finally {
            // This masks the real problem
            throw new RuntimeException("Logging system failed");
        }
        // Developer will see logging failure, not data processing failure
    }
}
```

#### **Better Alternatives:**
```java
public class BetterAlternatives {
    // ✓ Good - Log exceptions in finally, don't throw
    public void logDontThrow() {
        try {
            riskyOperation();
        } finally {
            try {
                cleanup();
            } catch (Exception e) {
                // Log the cleanup failure but don't throw
                logger.error("Cleanup failed", e);
            }
        }
    }
    
    // ✓ Good - Use try-with-resources for automatic cleanup
    public void useAutoCloseable() {
        try (MyResource resource = new MyResource()) {
            resource.doWork();
        } catch (Exception e) {
            // Handle business logic exceptions
            // Resource cleanup exceptions are suppressed, not lost
        }
    }
    
    // ✓ Good - Explicit exception handling in finally
    public void explicitHandling() {
        Exception primaryException = null;
        
        try {
            riskyOperation();
        } catch (Exception e) {
            primaryException = e;
        } finally {
            try {
                cleanup();
            } catch (Exception cleanupException) {
                if (primaryException != null) {
                    // Add cleanup exception as suppressed
                    primaryException.addSuppressed(cleanupException);
                } else {
                    // No primary exception, cleanup exception is the problem
                    throw cleanupException;
                }
            }
        }
        
        if (primaryException != null) {
            throw primaryException;
        }
    }
}
```

#### **When It Might Be Acceptable:**
```java
public class AcceptableFinallyThrow {
    // Validation in finally - might be acceptable
    public void validateState() {
        try {
            performOperation();
        } finally {
            // Validate system state after operation
            if (!isSystemStateValid()) {
                throw new IllegalStateException("System in invalid state");
            }
        }
    }
    
    // Resource cleanup with critical failure
    public void criticalCleanup() {
        try {
            performCriticalOperation();
        } finally {
            try {
                criticalCleanup();
            } catch (CriticalCleanupException e) {
                // If cleanup fails, system might be corrupted
                // Throwing might be appropriate to prevent further damage
                throw new SystemCorruptedException("Critical cleanup failed", e);
            }
        }
    }
}
```
