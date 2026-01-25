# 8. Java Multithreading & Synchronization 

## 1. What is multithreading?

Multithreading is the ability to execute multiple threads concurrently within a single program. It allows better resource utilization and improved performance by running tasks simultaneously.

- Multiple threads share same memory space
- Enables concurrent execution of tasks
- Improves CPU utilization and responsiveness
- Threads can communicate through shared memory
- JVM manages thread scheduling

Benefits include faster execution, better resource usage, and responsive user interfaces.

## 2. How do you create threads in Java?

There are two main ways to create threads in Java: extending Thread class or implementing Runnable interface.

**Method 1: Extending Thread**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
MyThread t = new MyThread();
t.start();
```

**Method 2: Implementing Runnable**
```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}
Thread t = new Thread(new MyTask());
t.start();
```

## 3. What is the difference between extending Thread and implementing Runnable?

**Extending Thread:**
- Direct inheritance from Thread class
- Cannot extend other classes (single inheritance)
- Tightly coupled with Thread class
- Less flexible approach

**Implementing Runnable:**
- Can extend other classes
- Better separation of concerns
- More flexible and reusable
- Preferred approach for thread creation

```java
// Runnable allows extending other classes
class MyTask extends SomeClass implements Runnable {
    public void run() { }
}

// Thread extension limits inheritance
class MyThread extends Thread { // Cannot extend anything else
    public void run() { }
}
```

## 4. What are the states of a thread?

A thread goes through various states during its lifecycle: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED.

- **NEW:** Thread created but not started
- **RUNNABLE:** Thread executing or ready to execute
- **BLOCKED:** Thread blocked waiting for monitor lock
- **WAITING:** Thread waiting indefinitely for another thread
- **TIMED_WAITING:** Thread waiting for specified time period
- **TERMINATED:** Thread completed execution

```java
Thread t = new Thread(() -> {}); // NEW state
t.start(); // RUNNABLE state
// Thread may go through BLOCKED, WAITING states
// Finally reaches TERMINATED state
```

## 5. What is synchronization in Java?

Synchronization is a mechanism that ensures only one thread can access a shared resource at a time. It prevents data corruption and maintains thread safety.

- Controls access to shared resources
- Prevents race conditions
- Uses synchronized keyword or locks
- Can synchronize methods or code blocks
- Ensures thread safety but may reduce performance

```java
// Synchronized method
public synchronized void increment() {
    count++;
}

// Synchronized block
public void decrement() {
    synchronized(this) {
        count--;
    }
}
```

## 6. What is deadlock and how do you prevent it?

Deadlock occurs when two or more threads are blocked forever, each waiting for the other to release a resource. It's a circular dependency situation.

**Prevention strategies:**
- Avoid nested locks
- Use timeout for lock acquisition
- Order locks consistently
- Use concurrent collections
- Implement deadlock detection

```java
// Deadlock scenario
Thread1: lock(A) -> lock(B)
Thread2: lock(B) -> lock(A)

// Prevention - consistent ordering
Thread1: lock(A) -> lock(B)
Thread2: lock(A) -> lock(B)
```

## 7. What is volatile keyword?

Volatile keyword ensures that a variable's value is always read from and written to main memory, not from thread's local cache. It provides visibility guarantee across threads.

- Ensures visibility of changes across threads
- Prevents compiler optimizations
- No atomicity guarantee for compound operations
- Lighter alternative to synchronization for simple cases
- Used for flags and status variables

```java
class SharedData {
    private volatile boolean flag = false;
    
    public void setFlag() {
        flag = true; // Immediately visible to all threads
    }
    
    public boolean isFlag() {
        return flag; // Always reads from main memory
    }
}
```

## 8. What is the difference between synchronized and volatile?

**Synchronized:**
- Provides both visibility and atomicity
- Blocks other threads (mutual exclusion)
- Can be used with methods and blocks
- Heavier performance overhead
- Prevents race conditions completely

**Volatile:**
- Provides only visibility, not atomicity
- No blocking of threads
- Only for variables
- Lighter performance overhead
- Prevents visibility issues only

```java
// Synchronized - full protection
private int count = 0;
public synchronized void increment() {
    count++; // Atomic and visible
}

// Volatile - visibility only
private volatile boolean ready = false;
public void setReady() {
    ready = true; // Visible but not atomic for compound operations
}
```

## 9. What is race condition and atomic operation?

**Race condition** occurs when multiple threads access shared data simultaneously and the outcome depends on thread scheduling. **Atomic operation** is indivisible and completes without interruption.

**Race Condition:**
- Multiple threads modify shared data
- Unpredictable results due to timing
- Causes data corruption
- Prevented by synchronization

**Atomic Operation:**
- Indivisible operation
- Either completes fully or not at all
- Thread-safe by nature
- Examples: reading/writing primitive variables (except long/double)

```java
// Race condition example
private int counter = 0;
public void increment() {
    counter++; // Not atomic: read -> increment -> write
}

// Atomic solution
private AtomicInteger counter = new AtomicInteger(0);
public void increment() {
    counter.incrementAndGet(); // Atomic operation
}
```

Atomic operations eliminate race conditions without explicit synchronization, providing better performance for simple operations.