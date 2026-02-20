## 4) Multithreading & Concurrency

### 35. Explain thread lifecycle states.

**Answer:**
A thread in Java goes through six states: NEW when created but not started, RUNNABLE when executing or ready to execute, BLOCKED when waiting for a monitor lock, WAITING when waiting indefinitely for another thread, TIMED_WAITING when waiting for a specific time, and TERMINATED when execution completes. A thread moves from NEW to RUNNABLE when start() is called. It becomes BLOCKED when trying to enter a synchronized block. It enters WAITING when calling wait(), join(), or park(). TIMED_WAITING occurs with sleep(), wait(timeout), or join(timeout). Finally, it reaches TERMINATED when run() completes.

**Example:**
```java
public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized(ThreadStates.class) {
                try {
                    Thread.sleep(1000); // TIMED_WAITING
                } catch (InterruptedException e) {}
            }
        });
        
        System.out.println(thread.getState()); // NEW
        thread.start();
        System.out.println(thread.getState()); // RUNNABLE
        Thread.sleep(100);
        System.out.println(thread.getState()); // TIMED_WAITING
        thread.join();
        System.out.println(thread.getState()); // TERMINATED
    }
}
```

---

### 36. Difference between `start()` and `run()` methods.

**Answer:**
The start() method creates a new thread and invokes the run() method in that new thread, enabling concurrent execution. Calling run() directly executes the code in the current thread without creating a new thread, making it a normal method call. start() can only be called once per thread object, throwing IllegalThreadStateException if called again. run() can be called multiple times. Always use start() to achieve multithreading. The JVM handles thread scheduling and context switching when start() is used.

**Example:**
```java
public class StartVsRun {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        
        // Correct - creates new thread
        thread.start(); // Output: Thread: Thread-0
        
        // Wrong - runs in main thread
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        thread2.run(); // Output: Thread: main (no new thread created)
    }
}
```

---

### 37. What is `synchronized` keyword and its usage?

**Answer:**
The synchronized keyword provides mutual exclusion, ensuring only one thread can execute a synchronized block or method at a time. It can be applied to methods or blocks. Synchronized methods lock the entire object (this for instance methods, Class object for static methods). Synchronized blocks allow fine-grained locking on specific objects. Every object in Java has an intrinsic lock (monitor). When a thread enters a synchronized section, it acquires the lock and releases it when exiting. Other threads trying to enter must wait. Use synchronized to protect shared mutable state from race conditions.

**Example:**
```java
public class SynchronizedExample {
    private int count = 0;
    
    // Synchronized method - locks entire object
    public synchronized void increment() {
        count++;
    }
    
    // Synchronized block - locks specific object
    public void incrementBlock() {
        synchronized(this) {
            count++;
        }
    }
    
    // Static synchronized - locks Class object
    public static synchronized void staticMethod() {
        // class-level lock
    }
    
    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample obj = new SynchronizedExample();
        
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) obj.increment();
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) obj.increment();
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(obj.count); // 2000 (thread-safe)
    }
}
```

---

### 38. Explain `volatile` keyword and happens-before relationship.

**Answer:**
The volatile keyword ensures visibility of changes across threads. When a variable is volatile, writes to it are immediately visible to other threads, and reads always get the latest value from main memory, not from thread's cache. It prevents instruction reordering around volatile variables. However, volatile doesn't provide atomicity for compound operations like increment. The happens-before relationship guarantees that actions in one thread are visible to another thread. A write to a volatile variable happens-before every subsequent read of that variable. Use volatile for flags and state variables that are written by one thread and read by others.

**Example:**
```java
public class VolatileExample {
    private volatile boolean running = true;
    
    public void stop() {
        running = false; // visible to other threads immediately
    }
    
    public void run() {
        while(running) {
            // do work
        }
        // stops when running becomes false
    }
    
    // Without volatile, thread might cache 'running' and never see the change
    
    public static void main(String[] args) throws InterruptedException {
        VolatileExample example = new VolatileExample();
        
        Thread worker = new Thread(example::run);
        worker.start();
        
        Thread.sleep(1000);
        example.stop(); // worker thread sees this change
        worker.join();
    }
}
```

---

### 39. Difference between `wait()` and `sleep()`.

**Answer:**
wait() is called on an object and releases the lock, allowing other threads to acquire it. It must be called within a synchronized context and the thread waits until notify() or notifyAll() is called. sleep() is a static method of Thread class that pauses the current thread for a specified time without releasing any locks. wait() is used for inter-thread communication, while sleep() is for introducing delays. wait() can be interrupted and must handle InterruptedException. After wait(), the thread must reacquire the lock before continuing. sleep() simply pauses execution.

**Example:**
```java
public class WaitVsSleep {
    private static final Object lock = new Object();
    
    public static void main(String[] args) {
        // wait() example - releases lock
        Thread t1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    System.out.println("T1: waiting");
                    lock.wait(); // releases lock
                    System.out.println("T1: resumed");
                } catch (InterruptedException e) {}
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized(lock) {
                System.out.println("T2: notifying");
                lock.notify(); // wakes up t1
            }
        });
        
        t1.start();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        t2.start();
        
        // sleep() example - doesn't release lock
        Thread t3 = new Thread(() -> {
            synchronized(lock) {
                try {
                    Thread.sleep(1000); // holds lock while sleeping
                } catch (InterruptedException e) {}
            }
        });
    }
}
```

---

### 40. What is deadlock? How do you prevent it?

**Answer:**
Deadlock occurs when two or more threads are blocked forever, each waiting for a lock held by another. Classic example: Thread A holds Lock1 and waits for Lock2, while Thread B holds Lock2 and waits for Lock1. Prevention strategies include: always acquire locks in the same order across all threads, use timeout with tryLock(), avoid nested locks when possible, use higher-level concurrency utilities like concurrent collections, and design to minimize lock holding time. Detection tools include thread dumps and JConsole. Use lock ordering, timeout mechanisms, and careful design to prevent deadlocks.

**Example:**
```java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // BAD - can cause deadlock
    public void method1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // work
            }
        }
    }
    
    public void method2() {
        synchronized(lock2) { // different order!
            synchronized(lock1) {
                // work
            }
        }
    }
    
    // GOOD - consistent lock ordering
    public void safeMethod1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // work
            }
        }
    }
    
    public void safeMethod2() {
        synchronized(lock1) { // same order
            synchronized(lock2) {
                // work
            }
        }
    }
}
```

---

### 41. Explain `ExecutorService` and thread pool types.

**Answer:**
ExecutorService is a high-level API for managing thread execution, providing thread pools to reuse threads efficiently. It decouples task submission from execution mechanics. Main thread pool types: FixedThreadPool creates a fixed number of threads, reusing them for tasks. CachedThreadPool creates threads as needed and reuses idle threads. SingleThreadExecutor uses one thread for sequential execution. ScheduledThreadPool schedules tasks with delays or periodic execution. Thread pools reduce overhead of thread creation, limit concurrent threads, and provide better resource management. Always shutdown ExecutorService to release resources.

**Example:**
```java
import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) {
        // Fixed thread pool - 5 threads
        ExecutorService fixed = Executors.newFixedThreadPool(5);
        
        // Cached thread pool - creates as needed
        ExecutorService cached = Executors.newCachedThreadPool();
        
        // Single thread executor
        ExecutorService single = Executors.newSingleThreadExecutor();
        
        // Scheduled executor
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        
        // Submit tasks
        fixed.submit(() -> System.out.println("Task executed"));
        
        Future<Integer> future = fixed.submit(() -> 42);
        
        // Schedule task
        scheduled.schedule(() -> System.out.println("Delayed"), 5, TimeUnit.SECONDS);
        
        // Shutdown
        fixed.shutdown();
        try {
            fixed.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {}
    }
}
```

---

### 42. What is `CountDownLatch`, `CyclicBarrier`, `Semaphore`?

**Answer:**
CountDownLatch allows threads to wait until a set of operations completes. It's initialized with a count that decrements with countDown() calls. Threads calling await() block until count reaches zero. It's one-time use. CyclicBarrier makes threads wait for each other at a barrier point. When all threads reach the barrier, they're released together. It's reusable. Semaphore controls access to a resource pool with permits. acquire() gets a permit, release() returns it. Use CountDownLatch for waiting on initialization, CyclicBarrier for coordinating phases, and Semaphore for limiting concurrent access.

**Example:**
```java
import java.util.concurrent.*;

public class SynchronizersExample {
    // CountDownLatch - wait for multiple threads to complete
    public static void countDownLatchExample() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        
        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("Task done");
                latch.countDown();
            }).start();
        }
        
        latch.await(); // waits until count = 0
        System.out.println("All tasks completed");
    }
    
    // CyclicBarrier - threads wait for each other
    public static void cyclicBarrierExample() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> 
            System.out.println("All reached barrier"));
        
        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Waiting at barrier");
                    barrier.await();
                } catch (Exception e) {}
            }).start();
        }
    }
    
    // Semaphore - limit concurrent access
    public static void semaphoreExample() {
        Semaphore semaphore = new Semaphore(2); // 2 permits
        
        for(int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Acquired");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {}
            }).start();
        }
    }
}
```

---

### 43. `ReentrantLock` vs `synchronized` - when to use which?

**Answer:**
ReentrantLock provides more flexibility than synchronized. It offers tryLock() for non-blocking lock attempts with timeout, lockInterruptibly() for interruptible locking, and fairness policy for lock acquisition order. It allows multiple condition variables via newCondition(). However, you must manually unlock in a finally block. synchronized is simpler, automatically releases locks, and has less overhead. Use synchronized for simple cases and ReentrantLock when you need tryLock, timeouts, fairness, multiple conditions, or interruptible locking. ReentrantLock is more powerful but requires careful management.

**Example:**
```java
import java.util.concurrent.locks.*;

public class LockComparison {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    
    // ReentrantLock with try-lock
    public void incrementWithTryLock() {
        if(lock.tryLock()) {
            try {
                count++;
            } finally {
                lock.unlock(); // must unlock manually
            }
        }
    }
    
    // ReentrantLock with timeout
    public void incrementWithTimeout() throws InterruptedException {
        if(lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }
    
    // Synchronized - simpler
    public synchronized void incrementSynchronized() {
        count++; // automatically unlocks
    }
    
    // Fair lock
    private final ReentrantLock fairLock = new ReentrantLock(true);
    
    // Condition variables
    private final Condition condition = lock.newCondition();
    
    public void awaitCondition() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
    }
}
```

---

### 44. Explain `ReadWriteLock` and its use cases.

**Answer:**
ReadWriteLock maintains a pair of locks: one for read-only operations and one for writing. Multiple threads can hold the read lock simultaneously if no thread holds the write lock. The write lock is exclusive - only one thread can hold it and no readers are allowed. This improves performance for read-heavy scenarios. ReentrantReadWriteLock is the common implementation. Use it when reads vastly outnumber writes, like caching scenarios. Read lock allows concurrent reads, write lock ensures exclusive writes. It prevents readers from seeing inconsistent state during writes.

**Example:**
```java
import java.util.concurrent.locks.*;

public class ReadWriteLockExample {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    private int value = 0;
    
    // Multiple threads can read simultaneously
    public int read() {
        readLock.lock();
        try {
            return value;
        } finally {
            readLock.unlock();
        }
    }
    
    // Only one thread can write, blocks all readers
    public void write(int newValue) {
        writeLock.lock();
        try {
            value = newValue;
        } finally {
            writeLock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        
        // Many readers
        for(int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(example.read())).start();
        }
        
        // Few writers
        new Thread(() -> example.write(42)).start();
    }
}
```

---

### 45. What is `AtomicInteger` and how does it work?

**Answer:**
AtomicInteger provides lock-free thread-safe operations on integers using CAS (Compare-And-Swap) operations. CAS is a CPU-level atomic instruction that compares a value with an expected value and updates it only if they match. This avoids locks, reducing contention and improving performance. Methods like incrementAndGet(), getAndIncrement(), compareAndSet() are atomic. It's ideal for counters, sequence generators, and simple shared state. AtomicInteger uses volatile internally for visibility and CAS for atomicity. It's faster than synchronized for simple operations but only works for single variables.

**Example:**
```java
import java.util.concurrent.atomic.*;

public class AtomicExample {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet(); // atomic, thread-safe
    }
    
    public int get() {
        return counter.get();
    }
    
    // CAS operation
    public boolean compareAndSet(int expected, int newValue) {
        return counter.compareAndSet(expected, newValue);
    }
    
    public static void main(String[] args) throws InterruptedException {
        AtomicExample example = new AtomicExample();
        
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    example.increment();
                }
            });
            threads[i].start();
        }
        
        for(Thread t : threads) t.join();
        System.out.println(example.get()); // 10000 (thread-safe)
    }
}
```

---

### 46. Explain `CompletableFuture` for async programming.

**Answer:**
CompletableFuture enables asynchronous, non-blocking programming with composable operations. It represents a future result that can be completed explicitly. Methods like supplyAsync() run tasks asynchronously, thenApply() transforms results, thenAccept() consumes results, and thenCompose() chains dependent futures. It supports combining multiple futures with allOf(), anyOf(), and exception handling with exceptionally() and handle(). Unlike Future, it's composable and doesn't block. Use it for async API calls, parallel processing, and building reactive pipelines. It improves responsiveness and resource utilization.

**Example:**
```java
import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // Async execution
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        
        // Chain operations
        future.thenApply(s -> s + " World")
              .thenAccept(System.out::println);
        
        // Combine futures
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);
        
        CompletableFuture<Integer> combined = f1.thenCombine(f2, (a, b) -> a + b);
        
        // Exception handling
        CompletableFuture<String> withError = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Error");
        }).exceptionally(ex -> "Default");
        
        // Wait for all
        CompletableFuture.allOf(f1, f2).join();
    }
}
```

---

### 47. What is `ThreadLocal` and its use cases?

**Answer:**
ThreadLocal provides thread-local variables where each thread has its own independent copy. Values stored in ThreadLocal are accessible only to that thread. It's useful for maintaining per-thread context like user sessions, database connections, or transaction contexts without passing them as parameters. Each thread's value is isolated from others. Common use cases include SimpleDateFormat (not thread-safe), storing user authentication context, and maintaining transaction state. Always remove ThreadLocal values using remove() to prevent memory leaks, especially in thread pool environments where threads are reused.

**Example:**
```java
public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    
    public static void increment() {
        threadLocal.set(threadLocal.get() + 1);
    }
    
    public static int get() {
        return threadLocal.get();
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            threadLocal.set(100);
            System.out.println("T1: " + threadLocal.get()); // 100
        });
        
        Thread t2 = new Thread(() -> {
            threadLocal.set(200);
            System.out.println("T2: " + threadLocal.get()); // 200
        });
        
        t1.start();
        t2.start();
        
        // Each thread has its own value
    }
    
    // Real-world example - user context
    private static ThreadLocal<String> userContext = new ThreadLocal<>();
    
    public static void setUser(String user) {
        userContext.set(user);
    }
    
    public static String getUser() {
        return userContext.get();
    }
    
    public static void cleanup() {
        userContext.remove(); // prevent memory leak
    }
}
```

---

### 48. How do you handle exceptions in multithreaded code?

**Answer:**
Exception handling in multithreaded code requires special attention because exceptions in one thread don't propagate to other threads. For threads, use try-catch within run() method or set an UncaughtExceptionHandler. For ExecutorService, Future.get() throws ExecutionException wrapping the original exception. Use submit() instead of execute() to capture exceptions. CompletableFuture provides exceptionally() and handle() for async exception handling. Always log exceptions in worker threads. Consider using a global exception handler for uncaught exceptions. Proper exception handling prevents silent failures and aids debugging.

**Example:**
```java
import java.util.concurrent.*;

public class ExceptionHandling {
    // Thread with exception handler
    public static void threadExample() {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Error in thread");
        });
        
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Caught: " + e.getMessage());
        });
        
        thread.start();
    }
    
    // ExecutorService with Future
    public static void executorExample() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<Integer> future = executor.submit(() -> {
            throw new RuntimeException("Error in task");
        });
        
        try {
            future.get(); // throws ExecutionException
        } catch (ExecutionException e) {
            System.out.println("Caught: " + e.getCause());
        } catch (InterruptedException e) {}
        
        executor.shutdown();
    }
    
    // CompletableFuture exception handling
    public static void completableFutureExample() {
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Error");
        }).exceptionally(ex -> {
            System.out.println("Handled: " + ex.getMessage());
            return null;
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        });
    }
}
```

---

### 49. Explain Fork/Join framework.

**Answer:**
Fork/Join framework is designed for parallel processing of recursive tasks using divide-and-conquer approach. It uses work-stealing algorithm where idle threads steal tasks from busy threads' queues. ForkJoinPool manages worker threads. Tasks extend RecursiveTask (returns result) or RecursiveAction (no result). The fork() method submits subtasks asynchronously, join() waits for completion. It's efficient for problems that can be broken into smaller subproblems like parallel sorting, tree traversal, or matrix operations. The framework automatically balances load across available processors. Use it for CPU-intensive recursive algorithms.

**Example:**
```java
import java.util.concurrent.*;

public class ForkJoinExample extends RecursiveTask<Long> {
    private final long[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 1000;
    
    public ForkJoinExample(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        if(end - start <= THRESHOLD) {
            // Direct computation
            long sum = 0;
            for(int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Fork into subtasks
            int mid = (start + end) / 2;
            ForkJoinExample left = new ForkJoinExample(array, start, mid);
            ForkJoinExample right = new ForkJoinExample(array, mid, end);
            
            left.fork(); // async execution
            long rightResult = right.compute();
            long leftResult = left.join(); // wait for result
            
            return leftResult + rightResult;
        }
    }
    
    public static void main(String[] args) {
        long[] array = new long[10000];
        for(int i = 0; i < array.length; i++) array[i] = i;
        
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinExample task = new ForkJoinExample(array, 0, array.length);
        long result = pool.invoke(task);
        System.out.println("Sum: " + result);
    }
}
```

---

### 50. What are best practices for thread safety?

**Answer:**
Thread safety best practices include: minimize shared mutable state, prefer immutable objects, use thread-safe collections like ConcurrentHashMap, synchronize access to shared mutable state, use higher-level concurrency utilities over low-level primitives, avoid holding locks during I/O operations, use atomic variables for simple counters, document thread-safety guarantees, use ThreadLocal for per-thread state, always release locks in finally blocks, prefer composition over inheritance for thread-safe classes, avoid calling alien methods while holding locks, and test with multiple threads under load. Design for thread safety from the start rather than adding it later.

**Example:**
```java
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ThreadSafetyBestPractices {
    // 1. Immutable objects
    public static final class ImmutablePoint {
        private final int x, y;
        public ImmutablePoint(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; }
        public int getY() { return y; }
    }
    
    // 2. Thread-safe collections
    private final Map<String, Integer> map = new ConcurrentHashMap<>();
    
    // 3. Atomic variables
    private final AtomicInteger counter = new AtomicInteger(0);
    
    // 4. Proper synchronization
    private final Object lock = new Object();
    private int value;
    
    public void updateValue(int newValue) {
        synchronized(lock) {
            value = newValue;
        }
    }
    
    // 5. ThreadLocal for per-thread state
    private static final ThreadLocal<SimpleDateFormat> dateFormat = 
        ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    
    // 6. Use ExecutorService
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    
    // 7. Minimize lock scope
    public void goodLocking() {
        // do work without lock
        synchronized(lock) {
            // minimal critical section
        }
        // more work without lock
    }
    
    // 8. Document thread-safety
    /**
     * Thread-safe counter using AtomicInteger
     */
    public void increment() {
        counter.incrementAndGet();
    }
}
```

