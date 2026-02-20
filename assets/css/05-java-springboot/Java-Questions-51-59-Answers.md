## 5) Exception Handling & JVM Internals

## 51. Checked vs unchecked exceptions - when to use which?

**Answer:**
Checked exceptions are compile-time exceptions that must be handled or declared. Unchecked exceptions are runtime exceptions that don't require explicit handling.

**Use checked exceptions** for recoverable conditions (file not found, network issues).
**Use unchecked exceptions** for programming errors (null pointer, illegal arguments).

**Example:**
```java
// Checked Exception - must handle
public void readFile(String path) throws IOException {
    FileReader reader = new FileReader(path); // IOException is checked
}

// Unchecked Exception - optional handling
public void divide(int a, int b) {
    int result = a / b; // ArithmeticException is unchecked
}
```

---

## 52. Explain try-catch-finally execution flow.

**Answer:**
Try block executes code, catch handles exceptions, finally always executes regardless of exception occurrence.

**Execution flow:**
1. Try block executes
2. If exception occurs, matching catch executes
3. Finally block always executes (even with return statements)

**Example:**
```java
public int process() {
    try {
        System.out.println("Try");
        return 1;
    } catch (Exception e) {
        System.out.println("Catch");
        return 2;
    } finally {
        System.out.println("Finally"); // Always executes
    }
}
// Output: Try, Finally, returns 1
```

---

## 53. What is try-with-resources and why is it important?

**Answer:**
Try-with-resources automatically closes resources that implement AutoCloseable, preventing resource leaks.

**Important because:** Ensures resources are closed even if exceptions occur, eliminates boilerplate finally blocks.

**Example:**
```java
// Old way - manual close
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("file.txt"));
} finally {
    if (br != null) br.close();
}

// Try-with-resources - auto close
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    return br.readLine();
} // Automatically closed
```

---

## 54. How do you create custom exceptions?

**Answer:**
Extend Exception (checked) or RuntimeException (unchecked) and provide constructors for message and cause.

**Example:**
```java
// Custom checked exception
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
    
    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Usage
public void withdraw(double amount) throws InsufficientBalanceException {
    if (balance < amount) {
        throw new InsufficientBalanceException("Balance: " + balance);
    }
}
```

---

## 55. What is exception chaining?

**Answer:**
Exception chaining links one exception to another, preserving the original cause while throwing a new exception.

**Purpose:** Maintains complete error context and root cause information.

**Example:**
```java
public void processData() throws DataProcessingException {
    try {
        // Database operation
        connection.execute(query);
    } catch (SQLException e) {
        // Chain original exception
        throw new DataProcessingException("Failed to process", e);
    }
}

// Retrieve original cause
catch (DataProcessingException e) {
    Throwable cause = e.getCause(); // Gets SQLException
}
```

---

## 56. Explain ClassLoader hierarchy in JVM.

**Answer:**
ClassLoader hierarchy loads classes in three levels: Bootstrap (core Java), Extension (ext directory), and Application (classpath).

**Hierarchy:** Bootstrap → Extension → Application (follows parent delegation model).

**Example:**
```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Application ClassLoader
        ClassLoader appLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(appLoader); // AppClassLoader
        
        // Extension ClassLoader
        ClassLoader extLoader = appLoader.getParent();
        System.out.println(extLoader); // ExtClassLoader
        
        // Bootstrap ClassLoader (null - native)
        ClassLoader bootLoader = extLoader.getParent();
        System.out.println(bootLoader); // null
    }
}
```

---

## 57. What is JIT compilation and method inlining?

**Answer:**
JIT (Just-In-Time) compilation converts frequently executed bytecode to native machine code at runtime for performance.

**Method inlining:** JIT replaces method calls with method body to eliminate call overhead.

**Example:**
```java
// Before inlining
public int calculate(int x) {
    return add(x, 5);
}

private int add(int a, int b) {
    return a + b;
}

// After JIT inlining (conceptual)
public int calculate(int x) {
    return x + 5; // Method call eliminated
}

// JIT flags
// -XX:+PrintCompilation (see JIT compilation)
// -XX:CompileThreshold=10000 (compilation threshold)
```

---

## 58. How does JVM execute bytecode?

**Answer:**
JVM loads class files, verifies bytecode, interprets or JIT-compiles to native code, then executes on the runtime engine.

**Steps:** Load → Verify → Prepare → Resolve → Initialize → Execute

**Example:**
```java
// Source code
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}

// Compiled to bytecode (.class)
// javap -c Hello.class shows:
// 0: getstatic     #2  // Field java/lang/System.out
// 3: ldc           #3  // String Hello
// 5: invokevirtual #4  // Method println

// JVM execution:
// 1. ClassLoader loads Hello.class
// 2. Bytecode verifier checks validity
// 3. Interpreter executes or JIT compiles
// 4. Native code runs on CPU
```

---

## 59. What are JVM flags for debugging?

**Answer:**
JVM flags enable debugging features like verbose output, heap dumps, GC logs, and remote debugging.

**Common flags:** -verbose, -XX:+PrintGCDetails, -XX:+HeapDumpOnOutOfMemoryError, -agentlib:jdwp

**Example:**
```bash
# Verbose class loading
java -verbose:class MyApp

# GC logging
java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps MyApp

# Heap dump on OOM
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/dump.hprof MyApp

# Remote debugging (port 5005)
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 MyApp

# Print JVM flags
java -XX:+PrintFlagsFinal -version

# Enable assertions
java -ea MyApp
```

