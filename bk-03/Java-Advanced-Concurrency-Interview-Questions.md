# 9. Java Advanced Concurrency 

## 1. What is ExecutorService?

ExecutorService is a high-level framework for managing and controlling thread execution. It provides a way to submit tasks and manage their lifecycle without directly creating threads.

- Manages thread pools automatically
- Provides methods to submit tasks (submit, execute)
- Handles thread creation, reuse, and termination
- Returns Future objects for tracking task progress
- Better alternative to manual thread management

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

// Submit tasks
executor.submit(() -> System.out.println("Task executed"));
executor.execute(() -> System.out.println("Another task"));

executor.shutdown(); // Graceful shutdown
```

## 2. What are the types of thread pools?

Java provides several predefined thread pool types through Executors class, each optimized for different use cases.

**Fixed Thread Pool:**
- Fixed number of threads
- Good for known workload

**Cached Thread Pool:**
- Creates threads as needed
- Reuses idle threads

**Single Thread Executor:**
- Single worker thread
- Sequential task execution

**Scheduled Thread Pool:**
- Supports delayed and periodic tasks

```java
ExecutorService fixed = Executors.newFixedThreadPool(10);
ExecutorService cached = Executors.newCachedThreadPool();
ExecutorService single = Executors.newSingleThreadExecutor();
ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(5);
```

## 3. What is Future and CompletableFuture?

**Future** represents the result of an asynchronous computation, while **CompletableFuture** is an enhanced version that supports functional programming and chaining operations.

**Future:**
- Represents pending result
- Blocking get() method
- Limited functionality
- Cannot be completed manually

**CompletableFuture:**
- Non-blocking operations
- Supports chaining and composition
- Can be completed manually
- Functional programming support

```java
// Future - basic async result
Future<String> future = executor.submit(() -> "Hello");
String result = future.get(); // Blocking call

// CompletableFuture - enhanced async programming
CompletableFuture<String> cf = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenCompose(s -> CompletableFuture.completedFuture(s.toUpperCase()));
```

## 4. What is CountDownLatch?

CountDownLatch is a synchronization utility that allows one or more threads to wait until a set of operations being performed by other threads completes.

- Initialized with a count
- Threads wait until count reaches zero
- countDown() decreases the count
- await() blocks until count is zero
- One-time use only (cannot be reset)

```java
CountDownLatch latch = new CountDownLatch(3);

// Worker threads
for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        // Do work
        System.out.println("Task completed");
        latch.countDown(); // Decrease count
    }).start();
}

latch.await(); // Wait for all tasks to complete
System.out.println("All tasks finished");
```

## 5. What is ReentrantLock?

ReentrantLock is an explicit lock implementation that provides more flexibility than synchronized blocks. It allows the same thread to acquire the lock multiple times.

- Explicit lock/unlock operations
- Supports fairness policy
- Interruptible lock acquisition
- Try-lock with timeout
- Same thread can acquire multiple times (reentrant)

```java
ReentrantLock lock = new ReentrantLock();

public void method1() {
    lock.lock();
    try {
        // Critical section
        method2(); // Same thread can acquire lock again
    } finally {
        lock.unlock();
    }
}

public void method2() {
    lock.lock();
    try {
        // Another critical section
    } finally {
        lock.unlock();
    }
}
```

## 6. What is the difference between ReentrantLock and synchronized?

**ReentrantLock:**
- Explicit lock/unlock
- Supports fairness, timeout, interruption
- More flexible but requires manual management
- Can check if lock is held
- Better performance under high contention

**Synchronized:**
- Implicit lock/unlock
- Simpler syntax
- Automatic lock release
- JVM optimized
- Cannot be interrupted

```java
// ReentrantLock - explicit control
ReentrantLock lock = new ReentrantLock();
public void method() {
    if (lock.tryLock(1, TimeUnit.SECONDS)) {
        try {
            // Critical section
        } finally {
            lock.unlock();
        }
    }
}

// Synchronized - implicit control
public synchronized void method() {
    // Critical section - automatic lock management
}
```

**Use ReentrantLock when you need:**
- Timeout for lock acquisition
- Interruptible locking
- Fair locking
- Try-lock functionality

**Use synchronized when you need:**
- Simple locking requirements
- Automatic lock management
- Better readability