# Top 1000 Java Interview Question & Answers

## Exception Handling
### 132. **What is Exception Handling in Java?**

**Exception Handling** in Java is a mechanism that allows a program to deal with runtime errors, ensuring that the program continues to run smoothly even when unexpected conditions (exceptions) occur. Java provides a powerful set of tools for handling errors, allowing developers to handle exceptions explicitly through `try`, `catch`, and `finally` blocks, improving the program's reliability.

The basic flow of exception handling in Java is as follows:
- **`try` block**: This block contains code that might throw an exception.
- **`catch` block**: This block catches and handles exceptions thrown by the `try` block.
- **`finally` block**: This block contains code that will be executed no matter whether an exception occurs or not, often used for cleanup actions.
- **`throw` keyword**: Used to explicitly throw an exception.
- **`throws` keyword**: Used in a method signature to declare that a method may throw an exception.

#### Example:
```java
public class Example {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;  // This will cause an ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero");
        } finally {
            System.out.println("This will always be executed.");
        }
    }
}
```
In this example, the `catch` block handles the exception, and the `finally` block is executed regardless of the exception occurrence.

### 133. **In Java, what are the differences between Checked and Unchecked Exceptions?**

Java exceptions are classified into two main categories:

1. **Checked Exceptions**:
   - Checked exceptions are exceptions that are **checked at compile-time**. If a method can throw a checked exception, it must either handle the exception using a `try-catch` block or declare it using the `throws` keyword.
   - These exceptions represent **recoverable conditions**, and the programmer is expected to handle them.
   - Example: `IOException`, `SQLException`, `ClassNotFoundException`.
   
   ```java
   public void readFile() throws IOException {
       // code that may throw an IOException
   }
   ```

2. **Unchecked Exceptions**:
   - Unchecked exceptions are exceptions that are **not checked at compile-time**. These are usually caused by programming errors, such as accessing an invalid array index or dividing by zero.
   - These exceptions are subclasses of `RuntimeException` and do not need to be explicitly handled or declared.
   - Example: `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`.
   
   ```java
   public void divide() {
       int result = 10 / 0;  // This will cause an ArithmeticException (unchecked)
   }
   ```

#### Key Differences:
- **Checked Exceptions**: Must be handled or declared (`IOException`, `SQLException`).
- **Unchecked Exceptions**: Do not need to be explicitly handled or declared (`NullPointerException`, `ArithmeticException`).

### 134. **What is the base class for `Error` and `Exception` classes in Java?**

The **base class** for both `Error` and `Exception` classes in Java is **`Throwable`**.

- **`Throwable`** is the root class of the exception hierarchy in Java. It has two main subclasses:
  - **`Error`**: Represents serious problems that a program should not attempt to catch (e.g., `OutOfMemoryError`, `StackOverflowError`).
  - **`Exception`**: Represents exceptions that a program can catch and handle (e.g., `IOException`, `RuntimeException`).

#### In summary:
- `Throwable` → `Error` / `Exception`
- `Error` represents unrecoverable conditions, and `Exception` represents conditions that a program can handle.

### 135. **What is a `finally` block in Java?**

The **`finally` block** in Java is a block of code that is always executed after the `try` block completes, regardless of whether an exception was thrown or not. It is typically used to **clean up resources** (e.g., closing file streams, database connections) or perform some other important actions that must always happen after the `try-catch` sequence.

The syntax of a `finally` block is as follows:

```java
try {
    // Code that may throw an exception
} catch (Exception e) {
    // Exception handling code
} finally {
    // Cleanup code, executed whether an exception occurs or not
}
```

#### Example:
```java
public class Example {
    public static void main(String[] args) {
        try {
            System.out.println("Try block executed");
            int result = 10 / 0;  // Will cause ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Catch block executed");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Output:**
```
Try block executed
Catch block executed
Finally block executed
```

In this example, even though an exception is thrown in the `try` block, the `finally` block is still executed.

### 136. **What is the use of the `finally` block in Java?**

The **use of the `finally` block** in Java is to guarantee that certain actions are performed, regardless of whether an exception was thrown or not. The `finally` block is typically used for **resource cleanup** (e.g., closing files, releasing database connections) or other important actions that need to happen even when exceptions occur.

#### Key Uses:
- **Cleanup resources**: It ensures that critical resource deallocation (like closing file streams, network connections, etc.) happens even if an exception occurs.
- **Guarantee execution**: It guarantees that the code inside the `finally` block will always be executed, no matter what happens in the `try` and `catch` blocks (unless the JVM exits abruptly).
- **Exception handling assurance**: Even if an exception is thrown and caught, the `finally` block will still run, ensuring that cleanup tasks are completed.

#### Example:
```java
public class ResourceExample {
    public static void main(String[] args) {
        FileReader reader = null;
        try {
            reader = new FileReader("file.txt");
            // Reading from the file
        } catch (IOException e) {
            System.out.println("Error reading file");
        } finally {
            // Always close the resource, even if an exception occurs
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("File closed successfully.");
                } catch (IOException e) {
                    System.out.println("Error closing file.");
                }
            }
        }
    }
}
```

In this example, the file will be closed in the `finally` block, ensuring that the resource is cleaned up regardless of whether an exception occurs during reading the file.

### Summary:
- **`Exception Handling`** helps to manage runtime errors and improve the program's stability.
- **Checked vs Unchecked Exceptions**: Checked exceptions must be handled, while unchecked exceptions are not required to be explicitly handled.
- **`Throwable`** is the base class for both `Error` and `Exception`.
- The **`finally` block** ensures that important cleanup code is executed, whether an exception occurs or not.

### 137. **Can we create a `finally` block without creating a `catch` block?**

Yes, it is possible to create a **`finally` block** without a **`catch` block** in Java. The `finally` block will always execute, whether an exception is thrown or not in the `try` block, and it doesn't require a `catch` block to be present. A `catch` block is optional, but the `finally` block is often used for resource cleanup or guaranteed execution of certain code.

#### Example without `catch` block:
```java
public class Example {
    public static void main(String[] args) {
        try {
            int result = 10 / 2;
            System.out.println("Try block executed");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Output:**
```
Try block executed
Finally block executed
```

In this example, no exception is thrown, but the `finally` block is still executed.

### 138. **Do we have to always put a `catch` block after a `try` block?**

No, you **do not** always have to put a **`catch` block** after a **`try` block**. The `catch` block is optional and only required if you want to handle exceptions thrown in the `try` block. 

You can use a `try` block with just a `finally` block (without a `catch` block) to guarantee execution of certain code, such as resource cleanup, regardless of whether an exception occurs.

#### Example:
```java
public class Example {
    public static void main(String[] args) {
        try {
            // Code that might throw an exception
            int result = 10 / 0;  // ArithmeticException
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Output:**
```
Finally block executed
```

Here, the `catch` block is not needed, and the exception is not handled explicitly. The `finally` block will still be executed.

### 139. **In what scenarios will a `finally` block not be executed?**

There are a few scenarios where the `finally` block may **not be executed**:

1. **If the JVM exits before the `finally` block is reached**:
   - If the program calls `System.exit()` or the JVM crashes before reaching the `finally` block, it will not be executed.

   Example:
   ```java
   public class Example {
       public static void main(String[] args) {
           try {
               System.exit(0);  // This will terminate the JVM
           } finally {
               System.out.println("Finally block will not be executed");
           }
       }
   }
   ```

   In this case, the `finally` block is never executed because `System.exit(0)` terminates the JVM.

2. **If the thread executing the `try` block is interrupted**:
   - If a thread is killed or interrupted (e.g., by calling `Thread.stop()`), the `finally` block may not execute.

3. **If the JVM crashes**:
   - If the JVM encounters an unexpected shutdown or crash (e.g., due to a fatal error), the `finally` block may not execute.

4. **If there is an infinite loop or blocking operation in the `try` block**:
   - If the `try` block contains an infinite loop or a blocking operation (e.g., reading from a file or waiting indefinitely), the `finally` block will not be executed until the `try` block finishes.

### 140. **Can we re-throw an Exception in Java?**

Yes, you can **re-throw** an exception in Java. This is useful when you catch an exception but want to pass it up the call stack to be handled by a higher-level method or log it.

There are two common ways to re-throw an exception in Java:

1. **Re-throwing the same exception**: You can catch an exception and simply re-throw it, either directly or by wrapping it in another exception.

   Example:
   ```java
   public class Example {
       public static void main(String[] args) {
           try {
               throw new ArithmeticException("Example exception");
           } catch (ArithmeticException e) {
               System.out.println("Caught exception: " + e.getMessage());
               // Re-throwing the exception
               throw e;
           }
       }
   }
   ```

   In this example, the `ArithmeticException` is caught and re-thrown.

2. **Wrapping and re-throwing as a different exception**: You can catch an exception and wrap it inside another exception type before re-throwing it.

   Example:
   ```java
   public class Example {
       public static void main(String[] args) {
           try {
               throw new ArithmeticException("Example exception");
           } catch (ArithmeticException e) {
               System.out.println("Caught exception: " + e.getMessage());
               // Wrapping and re-throwing as a different exception
               throw new RuntimeException("Wrapped exception", e);
           }
       }
   }
   ```

   In this example, the `ArithmeticException` is caught and re-thrown as a `RuntimeException`.

### Summary:
- A **`finally` block** can be used without a `catch` block, and it will always execute unless the JVM terminates prematurely (via `System.exit()`, a JVM crash, or thread interruption).
- You do **not** always have to have a `catch` block after a `try` block; you can just use a `finally` block if necessary.
- A `finally` block may not be executed in cases like JVM shutdown, thread interruption, or infinite loops.
- **Re-throwing exceptions** is possible and often used to allow higher-level methods to handle or log the exception.

### 141. **What is the difference between `throw` and `throws` in Java?**

In Java, **`throw`** and **`throws`** are both used in the context of exceptions, but they have different purposes:

- **`throw`**:
  - The `throw` keyword is used to **explicitly throw an exception** within a method or block of code.
  - It is followed by an instance of an exception (like `new ArithmeticException("Error message")`).
  - When an exception is thrown using `throw`, the control immediately transfers to the nearest `catch` block, or if not caught, it propagates up the call stack.

  **Example:**
  ```java
  public class Example {
      public static void main(String[] args) {
          throw new ArithmeticException("This is an error");
      }
  }
  ```

- **`throws`**:
  - The `throws` keyword is used in a method declaration to indicate that the method **may throw one or more exceptions**.
  - It is followed by a list of exceptions separated by commas (e.g., `throws IOException, SQLException`).
  - It informs the caller of the method that they need to handle or declare the exceptions.

  **Example:**
  ```java
  public void readFile() throws IOException {
      // code that may throw IOException
  }
  ```

  **Key Difference:**
  - `throw` is used to **actually throw** an exception within a method.
  - `throws` is used in the **method signature** to declare that the method may throw an exception.

---

### 142. **What is the concept of Exception Propagation?**

**Exception Propagation** refers to the process by which an exception is passed from one method to another when it is not caught in the current method. If a method throws an exception and it is not handled within that method (using `try-catch`), the exception is propagated to the calling method. This process continues until the exception is caught or the program terminates.

**How it works**:
1. If a method encounters an exception, it can either catch it or propagate it.
2. If it does not handle the exception (i.e., no `catch` block), the exception is propagated to the method that called it.
3. If the calling method doesn't handle the exception, it is propagated further up to the caller of that method, and so on, until the exception is either caught or the program exits (e.g., if it's uncaught at the top-level method `main()`).

**Example of Exception Propagation:**
```java
public class ExceptionPropagationExample {

    public static void main(String[] args) {
        try {
            methodA();  // Calls methodA which throws an exception
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e);
        }
    }

    public static void methodA() throws ArithmeticException {
        methodB();  // Calls methodB which throws an exception
    }

    public static void methodB() throws ArithmeticException {
        throw new ArithmeticException("Exception from methodB");
    }
}
```

**Explanation:**
- `methodB` throws an `ArithmeticException`, which is not handled within `methodB`.
- This exception is propagated back to `methodA` (the caller of `methodB`).
- `methodA` does not handle the exception, so it propagates further to `main()`, where it is caught by the `catch` block.

---

### 143. **When we override a method in a Child class, can we throw an additional Exception that is not thrown by the Parent class method?**

Yes, **you can throw an additional exception** in a method that overrides a method from a parent class, but there are some **restrictions**:

- The child class **cannot throw more exceptions** than the parent class method in terms of checked exceptions (those that are **explicitly declared** using `throws`). 
- The child class **can throw additional exceptions** as long as they are **unchecked exceptions** (subclasses of `RuntimeException` or `Error`).
- However, if the parent class method throws a checked exception, the overriding method in the child class can either:
  1. **Throw the same exception** (or a subclass of it).
  2. **Throw no exception** (i.e., not declaring it in the `throws` clause).
  3. **Throw a different checked exception** but only if it is a subclass of the exceptions declared in the parent method’s `throws` clause.

#### Example:
```java
class Parent {
    public void doSomething() throws IOException {
        System.out.println("Parent doing something");
    }
}

class Child extends Parent {
    // Overriding method can throw the same or a subclass of the exception (IOException)
    @Override
    public void doSomething() throws FileNotFoundException {
        System.out.println("Child doing something");
        throw new FileNotFoundException("File not found");
    }
}
```

**Explanation:**
- The parent class method `doSomething` throws an `IOException`.
- The child class overrides this method and throws `FileNotFoundException`, which is a subclass of `IOException`, so this is allowed.
  
However, if the child class were to throw an unrelated checked exception (e.g., `SQLException`), it would result in a compilation error because it is not a subclass of `IOException`.

---

### Summary:
- **`throw`** is used to explicitly throw an exception in the code, while **`throws`** is used in the method signature to declare that the method might throw one or more exceptions.
- **Exception propagation** is the process by which an exception is passed up the call stack to the method that called it, until it is caught or the program terminates.
- When overriding methods, the child class can throw an additional exception **if it is a subclass of the exceptions thrown by the parent** method. Unchecked exceptions (like `RuntimeException`) can be freely added, but checked exceptions must follow certain rules based on the parent method's `throws` clause.
