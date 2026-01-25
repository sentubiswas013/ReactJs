# 6. Java Exception Handling 

## 1. What is an exception in Java?

An exception is an unexpected event that occurs during program execution and disrupts the normal flow of the program. It's Java's way of handling runtime errors gracefully.

- Represents abnormal conditions during execution
- Allows programs to handle errors without crashing
- Provides information about what went wrong
- Can be caught and handled using try-catch blocks

```java
int result = 10 / 0; // ArithmeticException occurs
String text = null;
int length = text.length(); // NullPointerException occurs
```

## 2. What is the exception hierarchy in Java?

Java's exception hierarchy starts with Throwable class, which has two main branches: Error and Exception. Exception further divides into checked and unchecked exceptions.

```
Throwable
├── Error (unchecked)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── IOException (checked)
    ├── SQLException (checked)
    └── RuntimeException (unchecked)
        ├── NullPointerException
        ├── ArithmeticException
        └── ArrayIndexOutOfBoundsException
```

- **Error:** System-level problems, usually unrecoverable
- **Exception:** Application-level problems that can be handled

## 3. What are checked and unchecked exceptions?

**Checked exceptions** must be handled at compile time, while **unchecked exceptions** occur at runtime and don't require mandatory handling.

**Checked Exceptions:**
- Must be caught or declared with throws
- Compile-time enforcement
- Examples: IOException, SQLException

**Unchecked Exceptions:**
- Runtime exceptions, optional handling
- Inherit from RuntimeException
- Examples: NullPointerException, ArithmeticException

```java
// Checked - must handle
try {
    FileReader file = new FileReader("file.txt");
} catch (IOException e) { }

// Unchecked - optional handling
int[] arr = {1, 2, 3};
int value = arr[5]; // ArrayIndexOutOfBoundsException
```

## 4. What is the difference between throw and throws?

**throw** is used to explicitly throw an exception, while **throws** is used to declare that a method might throw exceptions.

**throw:**
- Throws actual exception object
- Used inside method body
- Followed by exception instance

**throws:**
- Declares possible exceptions
- Used in method signature
- Followed by exception class names

```java
public void validateAge(int age) throws IllegalArgumentException {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
}
```

## 5. What is try-catch-finally block?

Try-catch-finally is Java's exception handling mechanism where try contains risky code, catch handles exceptions, and finally executes cleanup code regardless of exceptions.

- **try:** Contains code that might throw exceptions
- **catch:** Handles specific exceptions
- **finally:** Always executes (cleanup code)

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("This always executes");
}
```

Multiple catch blocks can handle different exception types, and finally runs even if exceptions occur.

## 6. What is try-with-resources?

Try-with-resources automatically closes resources that implement AutoCloseable interface. It ensures proper resource management without explicit finally blocks.

- Automatically closes resources
- Resources must implement AutoCloseable
- Cleaner code, no explicit close() calls
- Introduced in Java 7

```java
// Old way
FileReader file = null;
try {
    file = new FileReader("data.txt");
} finally {
    if (file != null) file.close();
}

// Try-with-resources
try (FileReader file = new FileReader("data.txt")) {
    // Use file
} // Automatically closed
```

## 7. How do you create custom exceptions?

Custom exceptions are created by extending Exception class for checked exceptions or RuntimeException for unchecked exceptions. They provide specific error information for your application.

- Extend Exception (checked) or RuntimeException (unchecked)
- Provide constructors for different scenarios
- Add custom fields/methods if needed

```java
// Checked custom exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Unchecked custom exception
class InsufficientBalanceException extends RuntimeException {
    private double balance;
    
    public InsufficientBalanceException(String message, double balance) {
        super(message);
        this.balance = balance;
    }
}
```

## 8. What is exception chaining?

Exception chaining links exceptions together, preserving the original cause when wrapping exceptions. It helps maintain the complete error trail for better debugging.

- Preserves original exception information
- Uses initCause() method or constructor parameter
- Helps in debugging complex error scenarios
- Maintains exception stack trace

```java
try {
    // Some database operation
    connectToDatabase();
} catch (SQLException e) {
    // Chain the original exception
    throw new ServiceException("Service failed", e);
}

// Or using initCause()
RuntimeException re = new RuntimeException("Wrapper exception");
re.initCause(originalException);
throw re;
```

This allows you to see both the high-level service error and the underlying database error in the stack trace.