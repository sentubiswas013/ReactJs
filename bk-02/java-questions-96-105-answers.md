# 🔹 Multithreading and Concurrency

## Multithreading and Concurrency - Thread Basics

### 96. What is multithreading?

**Multithreading** is the ability to execute multiple threads concurrently within a single process.

#### **Key Concepts:**
- **Concurrent execution** - multiple threads run simultaneously
- **Shared resources** - threads share memory and process resources
- **Independent execution** - each thread has its own execution path
- **Improved performance** - better CPU utilization and responsiveness

#### **Basic Multithreading Example:**
```java
public class MultithreadingExample {
    public static void main(String[] args) {
        // Create multiple threads
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2: " + i);
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
            }
        });
        
        // Start threads - they run concurrently
        thread1.start();
        thread2.start();
        
        System.out.println("Main thread continues...");
    }
}
```

#### **Benefits of Multithreading:**
- **Improved responsiveness** - UI remains responsive during background tasks
- **Better resource utilization** - CPU cores work in parallel
- **Concurrent processing** - multiple tasks execute simultaneously
- **Asynchronous operations** - non-blocking I/O and network operations

#### **Use Cases:**
```java
// Web server handling multiple requests
public class WebServer {
    public void handleRequest(Request request) {
        new Thread(() -> processRequest(request)).start();
    }
}

// Background data processing
public class DataProcessor {
    public void processLargeDataset() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (DataChunk chunk : dataset) {
            executor.submit(() -> processChunk(chunk));
        }
    }
}
```

### 97. What is the difference between process and thread?

| Aspect | Process | Thread |
|--------|---------|--------|
| **Definition** | Independent program execution | Lightweight unit within process |
| **Memory** | Separate memory space | Shared memory space |
| **Communication** | IPC (Inter-Process Communication) | Direct memory sharing |
| **Creation Cost** | Expensive | Lightweight |
| **Context Switching** | Slower | Faster |
| **Isolation** | Fully isolated | Share process resources |
| **Failure Impact** | Isolated failure | Can affect entire process |

#### **Process Characteristics:**
```java
// Each JVM instance is a separate process
// Process 1: java App1
public class App1 {
    public static void main(String[] args) {
        System.out.println("Process 1 - PID: " + ProcessHandle.current().pid());
        // Separate memory space, cannot directly access App2's variables
    }
}

// Process 2: java App2  
public class App2 {
    public static void main(String[] args) {
        System.out.println("Process 2 - PID: " + ProcessHandle.current().pid());
        // Separate memory space, isolated from App1
    }
}
```

#### **Thread Characteristics:**
```java
public class ThreadExample {
    private static int sharedCounter = 0;  // Shared among all threads
    
    public static void main(String[] args) {
        // Multiple threads within same process
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter++;  // Direct access to shared memory
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter++;  // Same shared variable
            }
        });
        
        t1.start();
        t2.start();
    }
}
```

#### **Communication Comparison:**
```java
// Process communication - requires IPC
ProcessBuilder pb = new ProcessBuilder("java", "OtherProcess");
Process process = pb.start();
// Communicate via streams, files, sockets, etc.

// Thread communication - direct memory sharing
class ThreadCommunication {
    private volatile boolean flag = false;
    
    public void thread1() {
        flag = true;  // Direct memory access
    }
    
    public void thread2() {
        while (!flag) {  // Direct memory access
            // Wait for flag
        }
    }
}
```

### 98. How do you create threads in Java?

There are **several ways** to create threads in Java:

#### **1. Extending Thread Class:**
```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread: " + i);
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }
}

// Usage
MyThread thread = new MyThread();
thread.start();  // Creates new thread and calls run()
```

#### **2. Implementing Runnable Interface:**
```java
class MyTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Task: " + i);
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }
}

// Usage
Thread thread = new Thread(new MyTask());
thread.start();
```

#### **3. Lambda Expression (Java 8+):**
```java
// Most concise approach
Thread thread = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("Lambda: " + i);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
});
thread.start();
```

#### **4. Using ExecutorService (Recommended):**
```java
ExecutorService executor = Executors.newFixedThreadPool(2);

// Submit Runnable
executor.submit(() -> {
    System.out.println("Task executed by thread pool");
});

// Submit Callable (returns result)
Future<String> future = executor.submit(() -> {
    return "Task completed";
});

executor.shutdown();
```

#### **5. Thread with Name and Priority:**
```java
Thread thread = new Thread(() -> {
    System.out.println("Running in: " + Thread.currentThread().getName());
}, "MyCustomThread");

thread.setPriority(Thread.MAX_PRIORITY);
thread.start();
```

### 99. What is the difference between extending Thread and implementing Runnable?

| Aspect | Extending Thread | Implementing Runnable |
|--------|------------------|----------------------|
| **Inheritance** | Uses single inheritance slot | Allows multiple inheritance |
| **Design** | IS-A relationship | HAS-A relationship |
| **Reusability** | Less flexible | More reusable |
| **Thread Pool** | Cannot use with executors easily | Works well with thread pools |
| **Separation** | Task and thread coupled | Task and thread separated |

#### **Extending Thread - Less Flexible:**
```java
class PrinterThread extends Thread {
    private String message;
    
    public PrinterThread(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        System.out.println(message);
    }
}

// Problems:
// 1. Cannot extend another class
// 2. Tightly couples task with thread
// 3. Hard to reuse with different thread management strategies

// Usage
PrinterThread thread = new PrinterThread("Hello");
thread.start();
```

#### **Implementing Runnable - More Flexible:**
```java
class PrinterTask implements Runnable {
    private String message;
    
    public PrinterTask(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        System.out.println(message);
    }
}

// Advantages:
// 1. Can extend other classes
// 2. Separates task from thread management
// 3. Reusable with different execution strategies

// Multiple usage options
PrinterTask task = new PrinterTask("Hello");

// Option 1: Traditional thread
new Thread(task).start();

// Option 2: Thread pool
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(task);

// Option 3: Scheduled execution
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.schedule(task, 5, TimeUnit.SECONDS);
```

#### **Inheritance Flexibility:**
```java
// With Runnable - can extend other classes
class DatabaseTask extends DatabaseConnection implements Runnable {
    @Override
    public void run() {
        // Can use inherited database methods
        connect();
        executeQuery();
        disconnect();
    }
}

// With Thread - cannot extend other classes
// class DatabaseThread extends Thread, DatabaseConnection { // ✗ Not allowed
```

### 100. What are the states of a thread?

Java threads have **6 states** defined in `Thread.State` enum:

#### **Thread State Diagram:**
```
NEW → RUNNABLE ⇄ BLOCKED
         ↕         ↕
    TIMED_WAITING ← WAITING
         ↓         ↓
      TERMINATED ←──┘
```

#### **State Descriptions:**

| State | Description | How to Enter | How to Exit |
|-------|-------------|--------------|-------------|
| **NEW** | Created but not started | `new Thread()` | `thread.start()` |
| **RUNNABLE** | Executing or ready to execute | `start()` called | Various transitions |
| **BLOCKED** | Waiting for monitor lock | Trying to enter synchronized block | Acquiring the lock |
| **WAITING** | Waiting indefinitely | `wait()`, `join()` | `notify()`, `notifyAll()` |
| **TIMED_WAITING** | Waiting for specified time | `sleep()`, `wait(timeout)` | Timeout or notification |
| **TERMINATED** | Execution completed | `run()` method ends | Final state |

#### **State Transition Examples:**
```java
public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread running");
                Thread.sleep(2000);  // TIMED_WAITING
                System.out.println("Thread finishing");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        System.out.println("State: " + thread.getState());  // NEW
        
        thread.start();
        System.out.println("State: " + thread.getState());  // RUNNABLE
        
        Thread.sleep(100);
        System.out.println("State: " + thread.getState());  // TIMED_WAITING
        
        thread.join();  // Wait for completion
        System.out.println("State: " + thread.getState());  // TERMINATED
    }
}
```

#### **Monitoring Thread States:**
```java
public class ThreadStateMonitor {
    public void monitorThreadStates() {
        Thread worker = new Thread(() -> {
            synchronized (this) {
                try {
                    wait(1000);  // TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Monitor state changes
        new Thread(() -> {
            while (worker.getState() != Thread.State.TERMINATED) {
                System.out.println("Worker state: " + worker.getState());
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        }).start();
        
        worker.start();
    }
}
```

### 101. What is the difference between start() and run() methods?

| Method | Behavior | Thread Creation | Use Case |
|--------|----------|----------------|----------|
| **start()** | Creates new thread, then calls run() | Yes | Multithreading |
| **run()** | Executes in current thread | No | Sequential execution |

#### **start() Method - Creates New Thread:**
```java
public class StartVsRun {
    public static void demonstrateStart() {
        Thread thread = new Thread(() -> {
            System.out.println("Running in: " + Thread.currentThread().getName());
            for (int i = 0; i < 3; i++) {
                System.out.println("Count: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        System.out.println("Main thread: " + Thread.currentThread().getName());
        thread.start();  // Creates new thread
        System.out.println("Main continues immediately");
        
        // Output:
        // Main thread: main
        // Main continues immediately
        // Running in: Thread-0
        // Count: 0
        // Count: 1
        // Count: 2
    }
}
```

#### **run() Method - Executes in Current Thread:**
```java
public class RunMethodExample {
    public static void demonstrateRun() {
        Thread thread = new Thread(() -> {
            System.out.println("Running in: " + Thread.currentThread().getName());
            for (int i = 0; i < 3; i++) {
                System.out.println("Count: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        System.out.println("Main thread: " + Thread.currentThread().getName());
        thread.run();  // Executes in main thread
        System.out.println("Main continues after run() completes");
        
        // Output:
        // Main thread: main
        // Running in: main
        // Count: 0
        // Count: 1
        // Count: 2
        // Main continues after run() completes
    }
}
```

#### **Key Differences Demonstration:**
```java
public class StartRunComparison {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        };
        
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        
        System.out.println("=== Using start() ===");
        t1.start();  // Runs concurrently
        t2.start();  // Runs concurrently
        
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        System.out.println("\n=== Using run() ===");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");
        
        t3.run();  // Runs sequentially in main thread
        t4.run();  // Runs sequentially in main thread
    }
}
```

### 102. What is thread priority?

**Thread priority** is a hint to the thread scheduler about the relative importance of threads.

#### **Priority Levels:**
```java
public class ThreadPriority {
    public static void demonstratePriority() {
        Thread lowPriority = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Low priority: " + i);
            }
        });
        
        Thread highPriority = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("High priority: " + i);
            }
        });
        
        // Set priorities
        lowPriority.setPriority(Thread.MIN_PRIORITY);    // 1
        highPriority.setPriority(Thread.MAX_PRIORITY);   // 10
        
        // Default priority is NORM_PRIORITY (5)
        System.out.println("Default priority: " + Thread.NORM_PRIORITY);
        
        lowPriority.start();
        highPriority.start();
    }
}
```

#### **Priority Constants:**
```java
// Thread priority constants
Thread.MIN_PRIORITY  = 1
Thread.NORM_PRIORITY = 5  (default)
Thread.MAX_PRIORITY  = 10

// Setting priority
thread.setPriority(8);  // Custom priority
int priority = thread.getPriority();  // Get current priority
```

#### **Important Notes:**
```java
public class PriorityLimitations {
    public void demonstrateLimitations() {
        Thread thread = new Thread(() -> {
            System.out.println("Thread priority: " + Thread.currentThread().getPriority());
        });
        
        // Priority is just a hint, not a guarantee
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        
        // Platform-dependent behavior
        // Some operating systems ignore Java thread priorities
        // Modern JVMs may not strictly follow priority ordering
    }
    
    // Better approach: Use ExecutorService with proper task management
    public void betterApproach() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit high-priority tasks first
        executor.submit(highPriorityTask);
        executor.submit(lowPriorityTask);
        
        executor.shutdown();
    }
}
```

### 103. What is daemon thread?

**Daemon threads** are background service threads that don't prevent JVM from exiting.

#### **Characteristics:**
- **Background service** - provide services to user threads
- **JVM exit** - don't prevent JVM shutdown
- **Automatic termination** - killed when all user threads finish
- **Examples** - Garbage Collector, JIT Compiler

#### **Creating Daemon Threads:**
```java
public class DaemonThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("User thread: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
            System.out.println("User thread finished");
        });
        
        Thread daemonThread = new Thread(() -> {
            while (true) {  // Infinite loop
                System.out.println("Daemon thread running...");
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });
        
        // Set as daemon BEFORE starting
        daemonThread.setDaemon(true);
        
        userThread.start();
        daemonThread.start();
        
        // JVM will exit when userThread finishes
        // daemonThread will be terminated automatically
    }
}
```

#### **Daemon Thread Rules:**
```java
public class DaemonRules {
    public void demonstrateRules() {
        Thread thread = new Thread(() -> {
            System.out.println("Is daemon: " + Thread.currentThread().isDaemon());
        });
        
        // Must set daemon status before starting
        thread.setDaemon(true);  // ✓ Valid
        thread.start();
        
        // thread.setDaemon(true);  // ✗ IllegalThreadStateException
    }
    
    public void daemonInheritance() {
        Thread parentThread = new Thread(() -> {
            // Child threads inherit daemon status from parent
            Thread childThread = new Thread(() -> {
                System.out.println("Child is daemon: " + Thread.currentThread().isDaemon());
            });
            childThread.start();
        });
        
        parentThread.setDaemon(true);
        parentThread.start();
        // Child thread will also be daemon
    }
}
```

#### **Common Use Cases:**
```java
// Background monitoring
Thread monitorThread = new Thread(() -> {
    while (true) {
        checkSystemHealth();
        try { Thread.sleep(5000); } catch (InterruptedException e) { break; }
    }
});
monitorThread.setDaemon(true);
monitorThread.start();

// Periodic cleanup
Thread cleanupThread = new Thread(() -> {
    while (true) {
        cleanupTempFiles();
        try { Thread.sleep(60000); } catch (InterruptedException e) { break; }
    }
});
cleanupThread.setDaemon(true);
cleanupThread.start();
```

### 104. What is the difference between user thread and daemon thread?

| Aspect | User Thread | Daemon Thread |
|--------|-------------|---------------|
| **JVM Exit** | Prevents JVM exit | Doesn't prevent JVM exit |
| **Priority** | High priority for JVM | Low priority for JVM |
| **Purpose** | Main application logic | Background services |
| **Lifecycle** | Must complete before JVM exit | Terminated when JVM exits |
| **Default** | All threads are user threads by default | Must be explicitly set |

#### **JVM Exit Behavior:**
```java
public class UserVsDaemonThreads {
    public static void demonstrateJVMExit() {
        // User thread - keeps JVM alive
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("User thread: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        // Daemon thread - doesn't keep JVM alive
        Thread daemonThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {  // Long running
                System.out.println("Daemon thread: " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });
        
        daemonThread.setDaemon(true);
        
        userThread.start();
        daemonThread.start();
        
        // JVM will exit after userThread completes
        // daemonThread will be forcibly terminated
    }
}
```

#### **Thread Type Checking:**
```java
public class ThreadTypeChecker {
    public void checkThreadTypes() {
        Thread currentThread = Thread.currentThread();
        System.out.println("Main thread is daemon: " + currentThread.isDaemon());  // false
        
        Thread userThread = new Thread(() -> {});
        System.out.println("User thread is daemon: " + userThread.isDaemon());  // false
        
        Thread daemonThread = new Thread(() -> {});
        daemonThread.setDaemon(true);
        System.out.println("Daemon thread is daemon: " + daemonThread.isDaemon());  // true
    }
}
```

#### **Practical Example:**
```java
public class WebServer {
    public static void main(String[] args) {
        // Main server thread (user thread)
        Thread serverThread = new Thread(() -> {
            System.out.println("Server starting...");
            // Server logic here
            try { Thread.sleep(10000); } catch (InterruptedException e) {}
            System.out.println("Server shutting down...");
        });
        
        // Background monitoring (daemon thread)
        Thread monitorThread = new Thread(() -> {
            while (true) {
                System.out.println("Monitoring system...");
                try { Thread.sleep(2000); } catch (InterruptedException e) { break; }
            }
        });
        monitorThread.setDaemon(true);
        
        serverThread.start();
        monitorThread.start();
        
        // When serverThread finishes, JVM exits
        // monitorThread is automatically terminated
    }
}
```

### 105. How do you stop a thread?

**Never use deprecated `stop()` method**. Use **cooperative cancellation** with interruption mechanism.

#### **❌ Deprecated Approach (Don't Use):**
```java
// DON'T DO THIS - deprecated and unsafe
Thread thread = new Thread(() -> {
    while (true) {
        // Some work
    }
});
thread.start();
// thread.stop();  // ✗ Deprecated - can cause data corruption
```

#### **✅ Correct Approach 1 - Interruption:**
```java
public class ThreadInterruption {
    public static void demonstrateInterruption() throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // Simulate work
                    System.out.println("Working...");
                    Thread.sleep(1000);  // InterruptedException if interrupted
                } catch (InterruptedException e) {
                    // Restore interrupt status and exit
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted, exiting...");
                    break;
                }
            }
        });
        
        worker.start();
        Thread.sleep(3000);  // Let it work for 3 seconds
        worker.interrupt();  // Request interruption
        worker.join();       // Wait for completion
    }
}
```

#### **✅ Correct Approach 2 - Volatile Flag:**
```java
public class VolatileFlagCancellation {
    private volatile boolean running = true;
    
    public void startWorker() {
        Thread worker = new Thread(() -> {
            while (running) {
                // Do work
                System.out.println("Working...");
                try { Thread.sleep(1000); } catch (InterruptedException e) {
                    // Handle interruption but continue checking flag
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Worker stopped");
        });
        
        worker.start();
        
        // Stop after 5 seconds
        new Thread(() -> {
            try { Thread.sleep(5000); } catch (InterruptedException e) {}
            running = false;  // Signal to stop
        }).start();
    }
}
```

#### **✅ Correct Approach 3 - ExecutorService:**
```java
public class ExecutorServiceCancellation {
    public void demonstrateExecutorCancellation() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<?> future = executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("Task running...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        Thread.sleep(3000);
        future.cancel(true);  // Interrupt the task
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
```

#### **Handling InterruptedException Properly:**
```java
public class ProperInterruptionHandling {
    public void handleInterruption() {
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    doWork();
                    Thread.sleep(1000);  // Blocking operation
                }
            } catch (InterruptedException e) {
                // Option 1: Restore interrupt status and exit
                Thread.currentThread().interrupt();
                return;
                
                // Option 2: Handle and continue (if appropriate)
                // handleInterruption();
            }
        });
        
        worker.start();
    }
    
    private void doWork() {
        // Non-blocking work
        System.out.println("Doing work...");
    }
}
```

#### **Best Practices for Thread Stopping:**
```java
public class ThreadStoppingBestPractices {
    // 1. Design threads to be interruptible
    public void interruptibleTask() {
        while (!Thread.currentThread().isInterrupted()) {
            // Check interruption status regularly
            if (Thread.interrupted()) {
                break;  // Exit gracefully
            }
            
            // Do work in small chunks
            doSmallAmountOfWork();
        }
    }
    
    // 2. Use timeout for blocking operations
    public void timeoutExample() {
        try {
            // Use timeout instead of indefinite blocking
            boolean acquired = semaphore.tryAcquire(5, TimeUnit.SECONDS);
            if (!acquired) {
                return;  // Timeout, exit gracefully
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
    
    // 3. Cleanup resources properly
    public void resourceCleanup() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("file.txt");
            while (!Thread.currentThread().isInterrupted()) {
                // Process file
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (fis != null) {
                try { fis.close(); } catch (IOException e) {}
            }
        }
    }
}
```
