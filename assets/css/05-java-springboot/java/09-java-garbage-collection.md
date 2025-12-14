# Top 1000 Java Interview Question & Answers

## Garbage Collection

### 100. **What is Garbage Collection in Java?**

**Garbage Collection (GC)** in Java is an automatic memory management process. It involves the **reclaiming of memory** used by objects that are no longer reachable or in use by the program. The **Garbage Collector (GC)** is responsible for identifying and deleting objects that are no longer needed, thus freeing up memory for new objects and helping to prevent memory leaks.

In Java, the memory management is largely automatic, and developers don't need to explicitly release memory (like in languages such as C or C++). This process improves efficiency and helps manage memory in a way that prevents crashes and performance degradation.

### 101. **Why Java Provides Garbage Collector?**

Java provides a **Garbage Collector** for several reasons:

1. **Automatic Memory Management**: GC automates memory management, so developers don't need to manually allocate and deallocate memory, reducing the chances of errors like memory leaks or dangling pointers.
   
2. **Prevents Memory Leaks**: The garbage collector helps in cleaning up unused objects, thus preventing memory leaks in applications. It ensures that memory is freed up as soon as an object is no longer in use.

3. **Improved Performance**: GC helps in maintaining the application's performance by removing objects that are no longer needed, which could otherwise consume resources and slow down the system.

4. **Simplicity**: With automatic garbage collection, developers can focus more on the logic of their applications without worrying about memory management issues.

5. **Memory Safety**: The garbage collector minimizes the risks associated with manual memory management, such as double freeing memory or forgetting to free memory, which can lead to crashes.

### 102. **What is the Purpose of `gc()` in Java?**

The `gc()` method is a static method in the `System` class or `Runtime` class. Its purpose is to **suggest to the JVM** that it might be a good time to perform garbage collection. This method does not guarantee that garbage collection will occur, but it provides a hint to the JVM to perform GC.

#### Example:
```java
System.gc();  // Suggests to the JVM to perform garbage collection
```

However, calling `System.gc()` does not directly invoke garbage collection. The JVM has its own algorithm to decide when to run garbage collection, and invoking `gc()` is merely a request, not a command.

### 103. **How Does Garbage Collection Work in Java?**

Garbage Collection in Java works in the following steps:

1. **Marking**:
   The garbage collector first identifies which objects are still in use by marking them. The objects that are reachable from the root (starting from references in local variables, static variables, etc.) are marked as "alive."

2. **Sweeping**:
   After marking, the garbage collector identifies objects that are not reachable (i.e., objects that are no longer in use) and considers them for removal. These objects are cleared, freeing up memory.

3. **Compacting (Optional)**:
   In some cases, the memory may become fragmented. To optimize the memory allocation and improve the performance, the garbage collector may **compact** the heap by moving objects together, leaving large contiguous free spaces for new objects.

4. **Generational Garbage Collection**:
   Most JVMs implement **generational garbage collection**, where the heap is divided into generations:
   - **Young Generation**: Where new objects are allocated. It is frequently collected.
   - **Old (Tenured) Generation**: Where long-lived objects are stored.
   - **Permanent Generation**: Used for storing metadata about the classes and methods (though it has been replaced by Metaspace in Java 8).
   
   Objects that survive multiple collections in the young generation eventually move to the old generation.

5. **Finalization**:
   Before an object is garbage collected, its `finalize()` method is invoked (if defined). However, it's worth noting that relying on `finalize()` is discouraged because it is not guaranteed to be called in a timely manner.

### 104. **When Does an Object Become Eligible for Garbage Collection in Java?**

An object becomes eligible for garbage collection when it is no longer **reachable** by any live thread in the program. This generally occurs in the following scenarios:

1. **No References**: The object has no active references pointing to it. If no variables or objects refer to it, it becomes unreachable.
   
2. **Nullifying References**: If an object reference is explicitly set to `null`, making the object unreachable, it becomes eligible for garbage collection.

3. **Out of Scope**: If an object was created within a method or block and goes out of scope (e.g., when the method finishes execution), it becomes unreachable.

4. **Circular References**: If two or more objects reference each other in a cycle, but they are no longer reachable from any active thread or root object, they will still be eligible for GC.

#### Example:
```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        Object obj = new Object();  // obj references an object
        obj = null;  // Now the object becomes eligible for GC because no references point to it
    }
}
```

In the above example, after the line `obj = null;`, the object created earlier becomes eligible for garbage collection because no references are pointing to it.

### Summary of Garbage Collection:

- **Garbage Collection** in Java is the process of automatically reclaiming memory used by objects that are no longer reachable.
- **`gc()`** is a hint to the JVM to perform garbage collection, but it does not guarantee immediate collection.
- **Marking, sweeping, and compacting** are the core steps of garbage collection in Java.
- An **object becomes eligible for garbage collection** when there are no references to it from any part of the program.

### 105. **Why Do We Use `finalize()` Method in Java?**

The **`finalize()`** method in Java is used to perform cleanup operations before an object is garbage collected. This method is called by the garbage collector just before an object is destroyed and its memory is reclaimed. It is typically used to release resources like file handles, network connections, or database connections that are not automatically managed by Java's garbage collector.

- **Usage**: `finalize()` allows developers to specify how resources should be released when the object is no longer in use. It's part of the **`java.lang.Object`** class, so every Java object can override it.

However, the use of `finalize()` is not recommended in modern Java because:
1. **Unpredictability**: The timing of the call to `finalize()` is not guaranteed, and it might not be called at all if the garbage collector does not run.
2. **Performance Overhead**: The JVM has to track and call `finalize()`, which can have performance costs.
3. **Alternative Approaches**: It's often better to use other mechanisms, like **`try-with-resources`** or **`AutoCloseable`** for resource management.

#### Example:
```java
class MyResource {
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Cleaning up resources...");
            // Release resources here, like closing files or connections
        } finally {
            super.finalize();  // Always call super.finalize() at the end
        }
    }
}

public class FinalizeExample {
    public static void main(String[] args) {
        MyResource resource = new MyResource();
        resource = null;  // Object becomes eligible for GC
        System.gc();  // Request for garbage collection (not guaranteed)
    }
}
```

### 106. **What Are the Different Types of References in Java?**

In Java, references to objects are categorized into **four types** based on their behavior with respect to garbage collection:

1. **Strong Reference**:
   - The default type of reference. Any object that has a strong reference is not eligible for garbage collection.
   - Example:
     ```java
     Object obj = new Object();  // Strong reference
     ```
   - The object will not be garbage collected as long as the reference is pointing to it.

2. **Soft Reference**:
   - Soft references are used to implement memory-sensitive caches. If the JVM needs memory, it will collect objects with soft references.
   - Example:
     ```java
     SoftReference<MyClass> softRef = new SoftReference<>(new MyClass());
     ```
   - The object is eligible for GC when the JVM is low on memory.

3. **Weak Reference**:
   - Weak references are used for objects that can be collected as soon as they are no longer strongly referenced. These references are commonly used in situations where an object is needed, but it should not prevent the object from being garbage collected.
   - Example:
     ```java
     WeakReference<MyClass> weakRef = new WeakReference<>(new MyClass());
     ```
   - The object is eligible for garbage collection as soon as it is weakly referenced and no strong references are pointing to it.

4. **Phantom Reference**:
   - Phantom references are used when you want to be notified when an object is about to be garbage collected, but you do not have access to the object itself.
   - Example:
     ```java
     PhantomReference<MyClass> phantomRef = new PhantomReference<>(new MyClass(), referenceQueue);
     ```
   - Phantom references do not provide direct access to the object, only its notification when it is ready for GC.

### 107. **How Can We Reference an Unreferenced Object Again?**

Once an object becomes eligible for garbage collection and is reclaimed, it is generally **no longer accessible**. However, if the object is still in memory (before the GC reclaims it), you can re-reference it using a **strong reference** or one of the other reference types, depending on how you want to manage the object.

If an object has already been **garbage collected**, you cannot access or reference it again. The reference to the object is essentially removed from the memory, and no further operations can be performed on it.

To "refer back" to an object before it is garbage collected, you need to **keep a reference** to it (strong, weak, or soft reference) while it is in use. Once the object is eligible for garbage collection, you cannot revive it.

Example (using a **weak reference**):
```java
WeakReference<MyClass> weakRef = new WeakReference<>(new MyClass());
MyClass obj = weakRef.get();  // Returns the object if it is still alive

// If the object was collected, weakRef.get() returns null
```

In the case of a **phantom reference**, the object can't be accessed directly, but you can use a **reference queue** to monitor when the object is ready for garbage collection.

### 108. **What Kind of Process is the Garbage Collector Thread?**

The **Garbage Collector (GC) thread** is a **background process** in Java that runs automatically in the JVM. The garbage collector runs in a separate thread from the main application threads and performs memory management tasks, specifically identifying and reclaiming unused memory.

Key characteristics of the GC thread:
1. **Background Process**: The GC runs in the background and does not require direct involvement from the application. However, the application can trigger GC using `System.gc()`, although this is generally not recommended.
   
2. **Non-Deterministic**: The GC process is non-deterministic, meaning it doesn't run at a predictable time. The JVM decides when to invoke the garbage collector based on factors like memory usage, allocation rate, and available system resources.

3. **Automatic Memory Management**: The GC process handles the deallocation of memory for objects that are no longer in use, making memory management in Java largely automatic.

4. **Stop-The-World Events**: During certain types of garbage collection (such as **Full GC**), the GC thread may halt the application's threads, causing a "Stop-The-World" event. This is where all application threads are paused while garbage collection occurs.

In summary:
- **Garbage Collection** is a background process in Java that runs in its own thread.
- It performs automatic memory management and frees up memory by collecting objects that are no longer needed.
- While it improves memory safety, it is non-deterministic and can occasionally pause application threads.

### 109. **What is the Purpose of the `Runtime` Class?**

The **`Runtime`** class in Java is a part of the **`java.lang`** package and provides an interface to interact with the Java runtime environment. It allows developers to interact with the **JVM** during the execution of a Java application. Through the `Runtime` class, you can:

1. **Access system resources**: It provides methods to access and manage system-level resources such as memory, processors, and the operating system environment.
2. **Execute external processes**: It provides a way to invoke external system processes or execute commands on the underlying operating system.
3. **Manage memory**: You can query and manage memory usage and garbage collection.
4. **Shut down the JVM**: It allows you to stop the Java application by invoking the `exit()` method.

The `Runtime` class is designed as a singleton, meaning there is only one instance of it, and it can be accessed via the **`getRuntime()`** method.

#### Example:
```java
Runtime runtime = Runtime.getRuntime();
System.out.println("Available processors: " + runtime.availableProcessors());
System.out.println("Total memory: " + runtime.totalMemory());
```

### 110. **How Can We Invoke an External Process in Java?**

In Java, you can invoke an external process using the **`Runtime`** class or the **`ProcessBuilder`** class. Both of these provide ways to run external commands or programs from within a Java program.

#### 1. **Using `Runtime.getRuntime().exec()`**:
The `exec()` method of the `Runtime` class can be used to run a command in the underlying operating system's shell.

Example:
```java
public class ExternalProcessExample {
    public static void main(String[] args) {
        try {
            // Running an external command (for example, "notepad" on Windows)
            Process process = Runtime.getRuntime().exec("notepad");
            
            // Wait for the process to exit
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 2. **Using `ProcessBuilder`**:
`ProcessBuilder` provides a more flexible and powerful approach to handling external processes. You can use it to set environment variables, redirect input/output streams, and control the process execution.

Example:
```java
import java.io.IOException;

public class ProcessBuilderExample {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("notepad");
        try {
            Process process = processBuilder.start();
            process.waitFor();  // Wait until the process exits
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### 111. **What Are the Uses of `Runtime` Class?**

The **`Runtime`** class in Java provides several useful methods for interacting with the Java environment. The common uses of the `Runtime` class include:

1. **Accessing Available System Resources**:
   - `availableProcessors()`: Returns the number of processors available to the JVM.
   - `freeMemory()`: Returns the amount of free memory in the JVM.
   - `totalMemory()`: Returns the total amount of memory available to the JVM.
   - `maxMemory()`: Returns the maximum amount of memory that the JVM can use.

   Example:
   ```java
   Runtime runtime = Runtime.getRuntime();
   System.out.println("Free memory: " + runtime.freeMemory());
   System.out.println("Total memory: " + runtime.totalMemory());
   System.out.println("Max memory: " + runtime.maxMemory());
   ```

2. **Garbage Collection**:
   - The `Runtime` class can be used to **invoke garbage collection** manually, although it’s generally not recommended. The method `gc()` suggests that the JVM performs garbage collection.

   Example:
   ```java
   Runtime.getRuntime().gc();  // Suggests the JVM to run garbage collection
   ```

3. **Exiting the JVM**:
   - The `exit(int status)` method allows you to exit the JVM with a specified exit status code. A status of `0` usually indicates normal termination, while non-zero values indicate abnormal termination.
   
   Example:
   ```java
   Runtime.getRuntime().exit(0);  // Exit the JVM with a status code of 0 (normal)
   ```

4. **Executing External Processes**:
   - As discussed in the previous answer, the `exec()` method allows you to run external processes, such as operating system commands or launching other programs.

5. **Managing Memory**:
   - Methods like `gc()`, `freeMemory()`, and `totalMemory()` can be used to monitor and manage memory usage in the Java application.

#### Example of combining multiple features of `Runtime`:
```java
public class RuntimeExample {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        
        // Display system information
        System.out.println("Available processors: " + runtime.availableProcessors());
        System.out.println("Free memory: " + runtime.freeMemory());
        System.out.println("Total memory: " + runtime.totalMemory());
        
        // Suggest garbage collection
        runtime.gc();
        
        // Run an external process
        try {
            Process process = runtime.exec("notepad");
            process.waitFor();  // Wait until the process terminates
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Summary:

1. **`Runtime` Class**: Provides methods for interacting with the Java runtime, managing system resources, executing external processes, and managing memory.
2. **Invoking External Process**: You can invoke external processes using `Runtime.exec()` or `ProcessBuilder`.
3. **Uses of `Runtime`**: It can be used for accessing system resources, triggering garbage collection, exiting the JVM, and executing external processes, among other tasks.