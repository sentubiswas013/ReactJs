## Advanced Concurrency

### 116. What is java.util.concurrent package?

The **java.util.concurrent package** provides high-level concurrency utilities that are more powerful and easier to use than low-level synchronization primitives.

#### **Key Components:**

| Category | Classes/Interfaces | Purpose |
|----------|-------------------|---------|
| **Executors** | ExecutorService, ThreadPoolExecutor | Thread pool management |
| **Locks** | ReentrantLock, ReadWriteLock | Advanced locking mechanisms |
| **Atomic Variables** | AtomicInteger, AtomicReference | Lock-free thread-safe operations |
| **Synchronization Aids** | CountDownLatch, CyclicBarrier, Semaphore | Thread coordination |
| **Concurrent Collections** | ConcurrentHashMap, BlockingQueue | Thread-safe data structures |
| **Asynchronous Programming** | Future, CompletableFuture | Async task management |

#### **Benefits Over Traditional Synchronization:**
```java
// Traditional approach - manual thread management
public class TraditionalApproach {
    public void processData() {
        Thread t1 = new Thread(() -> processChunk1());
        Thread t2 = new Thread(() -> processChunk2());
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Modern approach - using java.util.concurrent
import java.util.concurrent.*;

public class ModernApproach {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void processData() {
        Future<?> future1 = executor.submit(() -> processChunk1());
        Future<?> future2 = executor.submit(() -> processChunk2());
        
        try {
            future1.get();  // Wait for completion
            future2.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

#### **Package Structure:**
```java
// Core interfaces and classes
java.util.concurrent.ExecutorService
java.util.concurrent.Future
java.util.concurrent.CompletableFuture

// Locks package
java.util.concurrent.locks.ReentrantLock
java.util.concurrent.locks.ReadWriteLock

// Atomic package
java.util.concurrent.atomic.AtomicInteger
java.util.concurrent.atomic.AtomicReference
```

### 117. What is ExecutorService?

**ExecutorService** is a high-level interface for managing and controlling thread execution, providing thread pool management and task lifecycle control.

#### **Key Features:**
- **Thread pool management** - automatic thread creation/destruction
- **Task submission** - submit Runnable and Callable tasks
- **Lifecycle control** - shutdown and termination management
- **Result handling** - Future objects for async results

#### **Basic Usage:**
```java
import java.util.concurrent.*;

public class ExecutorServiceExample {
    public void demonstrateBasicUsage() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit Runnable tasks
        executor.submit(() -> {
            System.out.println("Task 1 executed by: " + Thread.currentThread().getName());
        });
        
        // Submit Callable tasks (return results)
        Future<String> future = executor.submit(() -> {
            Thread.sleep(1000);
            return "Task completed";
        });
        
        try {
            String result = future.get();  // Blocking call
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        // Proper shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
```

#### **ExecutorService Methods:**
```java
public class ExecutorServiceMethods {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void demonstrateMethods() {
        // Submit tasks
        Future<?> future1 = executor.submit(runnableTask);
        Future<String> future2 = executor.submit(callableTask);
        
        // Submit multiple tasks
        List<Callable<String>> tasks = Arrays.asList(
            () -> "Task 1",
            () -> "Task 2",
            () -> "Task 3"
        );
        
        try {
            // Execute all tasks and wait for completion
            List<Future<String>> futures = executor.invokeAll(tasks);
            
            // Execute tasks and return first completed result
            String result = executor.invokeAny(tasks);
            
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    Runnable runnableTask = () -> System.out.println("Runnable task");
    Callable<String> callableTask = () -> "Callable result";
}
```

#### **Lifecycle Management:**
```java
public class ExecutorLifecycle {
    public void demonstrateLifecycle() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit tasks
        executor.submit(() -> doWork());
        
        // Graceful shutdown
        executor.shutdown();  // No new tasks accepted
        
        try {
            // Wait for existing tasks to complete
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                // Force shutdown if tasks don't complete in time
                executor.shutdownNow();
                
                // Wait for forced shutdown
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    private void doWork() {
        // Simulate work
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
}
```

### 118. What is ThreadPoolExecutor?

**ThreadPoolExecutor** is the main implementation of ExecutorService that manages a pool of worker threads with configurable parameters.

#### **Key Parameters:**
```java
public ThreadPoolExecutor(
    int corePoolSize,           // Minimum number of threads
    int maximumPoolSize,        // Maximum number of threads
    long keepAliveTime,         // Thread idle timeout
    TimeUnit unit,              // Time unit for keepAliveTime
    BlockingQueue<Runnable> workQueue,  // Task queue
    ThreadFactory threadFactory,         // Thread creation factory
    RejectedExecutionHandler handler     // Rejection policy
)
```

#### **Configuration Example:**
```java
import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public void createCustomThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,                              // corePoolSize
            4,                              // maximumPoolSize
            60L,                            // keepAliveTime
            TimeUnit.SECONDS,               // unit
            new LinkedBlockingQueue<>(100), // workQueue
            new ThreadFactory() {           // custom thread factory
                private int counter = 0;
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "CustomThread-" + counter++);
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()  // rejection policy
        );
        
        // Submit tasks
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by: " + 
                                 Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

#### **Thread Pool Behavior:**
```java
public class ThreadPoolBehavior {
    public void demonstrateBehavior() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2)
        );
        
        // Task submission behavior:
        // 1. If threads < corePoolSize: create new thread
        // 2. If threads = corePoolSize: add to queue
        // 3. If queue full and threads < maxPoolSize: create new thread
        // 4. If threads = maxPoolSize and queue full: reject task
        
        // Monitor pool status
        System.out.println("Core pool size: " + executor.getCorePoolSize());
        System.out.println("Max pool size: " + executor.getMaximumPoolSize());
        System.out.println("Active threads: " + executor.getActiveCount());
        System.out.println("Queue size: " + executor.getQueue().size());
    }
}
```

#### **Rejection Policies:**
```java
public class RejectionPolicies {
    public void demonstratePolicies() {
        // 1. AbortPolicy (default) - throws RejectedExecutionException
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(1, 1, 0L, 
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.AbortPolicy());
        
        // 2. CallerRunsPolicy - runs task in caller thread
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.CallerRunsPolicy());
        
        // 3. DiscardPolicy - silently discards task
        ThreadPoolExecutor executor3 = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.DiscardPolicy());
        
        // 4. DiscardOldestPolicy - discards oldest task in queue
        ThreadPoolExecutor executor4 = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
```

### 119. What are the types of thread pools?

Java provides several **pre-configured thread pool types** through the Executors factory class.

#### **1. FixedThreadPool:**
```java
public class FixedThreadPoolExample {
    public void demonstrateFixedThreadPool() {
        // Creates pool with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Equivalent to:
        // new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
        //                       new LinkedBlockingQueue<Runnable>())
        
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

**Use Case:** Known workload with predictable number of concurrent tasks.

#### **2. CachedThreadPool:**
```java
public class CachedThreadPoolExample {
    public void demonstrateCachedThreadPool() {
        // Creates threads as needed, reuses idle threads
        ExecutorService executor = Executors.newCachedThreadPool();
        
        // Equivalent to:
        // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
        //                       new SynchronousQueue<Runnable>())
        
        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

**Use Case:** Many short-lived asynchronous tasks.

#### **3. SingleThreadExecutor:**
```java
public class SingleThreadExecutorExample {
    public void demonstrateSingleThreadExecutor() {
        // Single worker thread, tasks executed sequentially
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

**Use Case:** Tasks must be executed sequentially, thread-safe execution.

#### **4. ScheduledThreadPool:**
```java
public class ScheduledThreadPoolExample {
    public void demonstrateScheduledThreadPool() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        
        // Schedule one-time task with delay
        scheduler.schedule(() -> {
            System.out.println("Delayed task executed");
        }, 5, TimeUnit.SECONDS);
        
        // Schedule recurring task with fixed rate
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task: " + System.currentTimeMillis());
        }, 0, 2, TimeUnit.SECONDS);
        
        // Schedule recurring task with fixed delay
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Fixed delay task");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }, 0, 3, TimeUnit.SECONDS);
        
        // Shutdown after 20 seconds
        scheduler.schedule(() -> scheduler.shutdown(), 20, TimeUnit.SECONDS);
    }
}
```

**Use Case:** Delayed execution, periodic tasks, scheduling.

#### **Comparison Table:**

| Pool Type | Core Threads | Max Threads | Queue | Use Case |
|-----------|--------------|-------------|-------|----------|
| **Fixed** | N | N | Unbounded | Predictable load |
| **Cached** | 0 | Unlimited | Direct handoff | Variable load |
| **Single** | 1 | 1 | Unbounded | Sequential execution |
| **Scheduled** | N | N | Delayed queue | Timed tasks |

### 120. What is Future and CompletableFuture?

#### **Future - Basic Asynchronous Result:**
```java
import java.util.concurrent.*;

public class FutureExample {
    public void demonstrateFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit Callable task
        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);  // Simulate long-running task
            return "Task completed";
        });
        
        try {
            // Check if task is done (non-blocking)
            System.out.println("Is done: " + future.isDone());
            
            // Get result (blocking)
            String result = future.get();  // Waits until completion
            System.out.println("Result: " + result);
            
            // Get result with timeout
            String result2 = future.get(3, TimeUnit.SECONDS);
            
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
    }
}
```

#### **CompletableFuture - Advanced Asynchronous Programming:**
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public void demonstrateCompletableFuture() {
        // Create CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            return "Hello";
        });
        
        // Chain operations
        CompletableFuture<String> result = future
            .thenApply(s -> s + " World")           // Transform result
            .thenApply(String::toUpperCase)         // Another transformation
            .thenCompose(s -> CompletableFuture     // Compose with another async operation
                .supplyAsync(() -> s + "!"));
        
        // Handle completion
        result.thenAccept(System.out::println);     // Consume result
        
        // Handle exceptions
        result.exceptionally(throwable -> {
            System.err.println("Error: " + throwable.getMessage());
            return "Default value";
        });
        
        // Wait for completion
        result.join();  // Similar to get() but unchecked exceptions
    }
}
```

#### **CompletableFuture Combining Operations:**
```java
public class CompletableFutureCombining {
    public void demonstrateCombining() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        
        // Combine two futures
        CompletableFuture<String> combined = future1.thenCombine(future2, 
            (s1, s2) -> s1 + " " + s2);
        
        // Wait for either future to complete
        CompletableFuture<Object> either = CompletableFuture.anyOf(future1, future2);
        
        // Wait for all futures to complete
        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2);
        
        // Execute after both complete
        all.thenRun(() -> System.out.println("Both completed"));
        
        System.out.println(combined.join());  // "Hello World"
    }
}
```

#### **Key Differences:**

| Feature | Future | CompletableFuture |
|---------|--------|-------------------|
| **Chaining** | No | Yes |
| **Combining** | No | Yes |
| **Exception Handling** | Basic | Advanced |
| **Completion Callbacks** | No | Yes |
| **Manual Completion** | No | Yes |

### 121. What is CountDownLatch?

**CountDownLatch** is a synchronization aid that allows threads to wait until a set of operations complete.

#### **Basic Usage:**
```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public void demonstrateCountDownLatch() throws InterruptedException {
        int numberOfTasks = 3;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);
        
        // Start worker threads
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            new Thread(() -> {
                try {
                    System.out.println("Task " + taskId + " starting");
                    Thread.sleep(2000);  // Simulate work
                    System.out.println("Task " + taskId + " completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();  // Decrement counter
                }
            }).start();
        }
        
        // Wait for all tasks to complete
        System.out.println("Waiting for all tasks to complete...");
        latch.await();  // Blocks until counter reaches 0
        System.out.println("All tasks completed!");
    }
}
```

#### **Real-world Example - Service Startup:**
```java
public class ServiceStartup {
    private final CountDownLatch startupLatch = new CountDownLatch(3);
    
    public void startServices() {
        // Start database service
        new Thread(() -> {
            initializeDatabase();
            startupLatch.countDown();
        }).start();
        
        // Start cache service
        new Thread(() -> {
            initializeCache();
            startupLatch.countDown();
        }).start();
        
        // Start web service
        new Thread(() -> {
            initializeWebServer();
            startupLatch.countDown();
        }).start();
        
        try {
            // Wait for all services to start
            startupLatch.await(30, TimeUnit.SECONDS);
            System.out.println("All services started successfully");
        } catch (InterruptedException e) {
            System.err.println("Service startup interrupted");
            Thread.currentThread().interrupt();
        }
    }
    
    private void initializeDatabase() { /* Database init */ }
    private void initializeCache() { /* Cache init */ }
    private void initializeWebServer() { /* Web server init */ }
}
```

#### **Key Characteristics:**
- **One-time use** - cannot be reset
- **Thread-safe** - multiple threads can call countDown()
- **Blocking** - await() blocks until counter reaches zero
- **Timeout support** - await() with timeout

### 122. What is CyclicBarrier?

**CyclicBarrier** synchronizes threads at a common barrier point where all threads must arrive before any can proceed.

#### **Basic Usage:**
```java
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public void demonstrateCyclicBarrier() {
        int numberOfThreads = 3;
        
        // Create barrier with optional barrier action
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> {
            System.out.println("All threads reached barrier - proceeding to next phase");
        });
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    // Phase 1
                    System.out.println("Thread " + threadId + " - Phase 1");
                    Thread.sleep(1000 + threadId * 500);  // Different work times
                    
                    barrier.await();  // Wait for all threads
                    
                    // Phase 2
                    System.out.println("Thread " + threadId + " - Phase 2");
                    Thread.sleep(1000);
                    
                    barrier.await();  // Wait again (barrier is reusable)
                    
                    // Phase 3
                    System.out.println("Thread " + threadId + " - Phase 3");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

#### **Parallel Algorithm Example:**
```java
public class ParallelMatrixMultiplication {
    private final CyclicBarrier barrier;
    private final int[][] matrixA, matrixB, result;
    private final int numberOfThreads;
    
    public ParallelMatrixMultiplication(int[][] a, int[][] b, int threads) {
        this.matrixA = a;
        this.matrixB = b;
        this.result = new int[a.length][b[0].length];
        this.numberOfThreads = threads;
        this.barrier = new CyclicBarrier(threads, () -> {
            System.out.println("Phase completed by all threads");
        });
    }
    
    public void multiply() {
        int rowsPerThread = matrixA.length / numberOfThreads;
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int startRow = i * rowsPerThread;
            final int endRow = (i == numberOfThreads - 1) ? 
                matrixA.length : (i + 1) * rowsPerThread;
            
            new Thread(() -> {
                try {
                    // Each thread processes its assigned rows
                    for (int row = startRow; row < endRow; row++) {
                        for (int col = 0; col < matrixB[0].length; col++) {
                            for (int k = 0; k < matrixB.length; k++) {
                                result[row][col] += matrixA[row][k] * matrixB[k][col];
                            }
                        }
                    }
                    
                    // Wait for all threads to complete their portion
                    barrier.await();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

#### **CyclicBarrier vs CountDownLatch:**

| Feature | CyclicBarrier | CountDownLatch |
|---------|---------------|----------------|
| **Reusability** | Reusable | One-time use |
| **Waiting Threads** | All parties wait | Only some threads wait |
| **Reset** | Automatic after barrier | Cannot reset |
| **Barrier Action** | Supported | Not supported |

### 123. What is Semaphore?

**Semaphore** controls access to shared resources by maintaining a set of permits.

#### **Basic Usage:**
```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private final Semaphore semaphore = new Semaphore(3);  // 3 permits
    
    public void accessResource() {
        try {
            semaphore.acquire();  // Acquire permit
            System.out.println(Thread.currentThread().getName() + " acquired permit");
            
            // Use resource
            Thread.sleep(2000);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released permit");
            semaphore.release();  // Release permit
        }
    }
    
    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();
        
        // Create 10 threads trying to access resource
        for (int i = 0; i < 10; i++) {
            new Thread(example::accessResource, "Thread-" + i).start();
        }
    }
}
```

#### **Connection Pool Example:**
```java
public class ConnectionPool {
    private final Semaphore semaphore;
    private final Queue<Connection> connections;
    
    public ConnectionPool(int maxConnections) {
        this.semaphore = new Semaphore(maxConnections);
        this.connections = new ConcurrentLinkedQueue<>();
        
        // Initialize connections
        for (int i = 0; i < maxConnections; i++) {
            connections.offer(new Connection("Connection-" + i));
        }
    }
    
    public Connection getConnection() throws InterruptedException {
        semaphore.acquire();  // Wait for available permit
        return connections.poll();
    }
    
    public void releaseConnection(Connection connection) {
        connections.offer(connection);
        semaphore.release();  // Return permit
    }
    
    static class Connection {
        private final String name;
        Connection(String name) { this.name = name; }
        @Override
        public String toString() { return name; }
    }
}
```

#### **Semaphore Features:**
```java
public class SemaphoreFeatures {
    private final Semaphore semaphore = new Semaphore(2, true);  // Fair semaphore
    
    public void demonstrateFeatures() {
        try {
            // Try to acquire without blocking
            if (semaphore.tryAcquire()) {
                System.out.println("Acquired permit immediately");
                semaphore.release();
            }
            
            // Try to acquire with timeout
            if (semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
                System.out.println("Acquired permit within timeout");
                semaphore.release();
            }
            
            // Acquire multiple permits
            semaphore.acquire(2);
            System.out.println("Acquired 2 permits");
            semaphore.release(2);
            
            // Check available permits
            System.out.println("Available permits: " + semaphore.availablePermits());
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

### 124. What is ReentrantLock?

**ReentrantLock** is an explicit lock that provides the same functionality as synchronized with additional features.

#### **Basic Usage:**
```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println("Counter: " + counter);
        } finally {
            lock.unlock();  // Always unlock in finally block
        }
    }
    
    public void demonstrateReentrant() {
        lock.lock();
        try {
            System.out.println("First lock acquired");
            
            // Same thread can acquire lock again (reentrant)
            lock.lock();
            try {
                System.out.println("Second lock acquired");
            } finally {
                lock.unlock();  // Must unlock same number of times
            }
            
        } finally {
            lock.unlock();
        }
    }
}
```

#### **Advanced Features:**
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockAdvanced {
    private final ReentrantLock lock = new ReentrantLock(true);  // Fair lock
    
    public void tryLockExample() {
        // Non-blocking lock attempt
        if (lock.tryLock()) {
            try {
                System.out.println("Lock acquired immediately");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock");
        }
    }
    
    public void tryLockWithTimeout() {
        try {
            // Try to acquire lock with timeout
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println("Lock acquired within timeout");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Timeout waiting for lock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void interruptibleLock() {
        try {
            // Lock that can be interrupted
            lock.lockInterruptibly();
            try {
                // Critical section
                Thread.sleep(5000);
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("Lock acquisition interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
```

#### **Condition Variables:**
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private final Object[] buffer = new Object[10];
    private int count = 0, putIndex = 0, takeIndex = 0;
    
    public void put(Object item) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                notFull.await();  // Wait until buffer not full
            }
            
            buffer[putIndex] = item;
            putIndex = (putIndex + 1) % buffer.length;
            count++;
            
            notEmpty.signal();  // Signal that buffer is not empty
        } finally {
            lock.unlock();
        }
    }
    
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();  // Wait until buffer not empty
            }
            
            Object item = buffer[takeIndex];
            buffer[takeIndex] = null;
            takeIndex = (takeIndex + 1) % buffer.length;
            count--;
            
            notFull.signal();  // Signal that buffer is not full
            return item;
        } finally {
            lock.unlock();
        }
    }
}
```

### 125. What is the difference between ReentrantLock and synchronized?

| Feature | ReentrantLock | synchronized |
|---------|---------------|--------------|
| **Lock Acquisition** | Explicit lock()/unlock() | Automatic |
| **Try Lock** | tryLock() with timeout | Not available |
| **Interruptible** | lockInterruptibly() | Not interruptible |
| **Fairness** | Fair/unfair options | Unfair only |
| **Condition Variables** | Multiple conditions | Single wait/notify |
| **Performance** | Slightly better in high contention | Good for low contention |
| **Error Handling** | Manual unlock in finally | Automatic unlock |

#### **Synchronized Example:**
```java
public class SynchronizedExample {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;  // Automatic lock acquisition/release
    }
    
    public void synchronizedBlock() {
        synchronized (this) {
            counter++;  // Automatic lock management
        }
    }
    
    // Wait/notify with synchronized
    private boolean condition = false;
    
    public synchronized void waitForCondition() throws InterruptedException {
        while (!condition) {
            wait();  // Single condition variable
        }
    }
    
    public synchronized void signalCondition() {
        condition = true;
        notifyAll();  // Wake all waiting threads
    }
}
```

#### **ReentrantLock Example:**
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock(true);  // Fair lock
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            counter++;  // Manual lock management
        } finally {
            lock.unlock();  // Must unlock manually
        }
    }
    
    public void tryIncrement() {
        if (lock.tryLock()) {  // Non-blocking attempt
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock");
        }
    }
    
    // Multiple condition variables
    public void waitForCondition1() throws InterruptedException {
        lock.lock();
        try {
            condition1.await();  // Wait on specific condition
        } finally {
            lock.unlock();
        }
    }
    
    public void signalCondition1() {
        lock.lock();
        try {
            condition1.signal();  // Signal specific condition
        } finally {
            lock.unlock();
        }
    }
}
```

#### **When to Use Each:**

**Use synchronized when:**
- Simple locking requirements
- Automatic lock management preferred
- Lower complexity needed
- Built-in wait/notify is sufficient

**Use ReentrantLock when:**
- Need tryLock() functionality
- Require interruptible locking
- Need fair locking
- Multiple condition variables required
- Advanced lock features needed
