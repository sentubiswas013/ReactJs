## Synchronization

### 106. What is synchronization in Java?

**Synchronization** is a mechanism to control access to shared resources by multiple threads to prevent data corruption and race conditions.

#### **Purpose of Synchronization:**
- **Prevent race conditions** - avoid unpredictable behavior
- **Ensure data consistency** - maintain data integrity
- **Coordinate thread access** - control shared resource usage
- **Provide thread safety** - guarantee correct concurrent execution

#### **Problem Without Synchronization:**
```java
public class UnsafeCounter {
    private int count = 0;
    
    public void increment() {
        count++;  // Not atomic: read, increment, write
    }
    
    public int getCount() {
        return count;
    }
    
    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();
        
        // Create 1000 threads, each incrementing 1000 times
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();  // Race condition!
                }
            });
            threads[i].start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("Expected: 1000000");
        System.out.println("Actual: " + counter.getCount());  // Usually less than 1000000
    }
}
```

#### **Solution With Synchronization:**
```java
public class SafeCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;  // Now thread-safe
    }
    
    public synchronized int getCount() {
        return count;
    }
    
    // Now the result will always be 1000000
}
```

#### **Types of Synchronization:**
- **Method-level synchronization** - entire method is synchronized
- **Block-level synchronization** - specific code blocks are synchronized
- **Static synchronization** - class-level locking
- **Object-level synchronization** - instance-level locking

### 107. What is the synchronized keyword?

The **synchronized keyword** provides mutual exclusion by allowing only one thread to execute synchronized code at a time.

#### **Synchronized Method:**
```java
public class SynchronizedMethods {
    private int balance = 1000;
    
    // Synchronized instance method - locks on 'this'
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println("Withdrawing: " + amount);
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        }
    }
    
    // Synchronized static method - locks on Class object
    public static synchronized void staticMethod() {
        System.out.println("Static synchronized method");
    }
}
```

#### **Synchronized Block:**
```java
public class SynchronizedBlocks {
    private int balance = 1000;
    private final Object lock = new Object();
    
    public void withdraw(int amount) {
        // Synchronized block with custom lock
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }
    
    public void deposit(int amount) {
        // Synchronized block with 'this' as lock
        synchronized (this) {
            balance += amount;
        }
    }
    
    // Multiple synchronized blocks can use different locks
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            // Critical section 1
        }
    }
    
    public void method2() {
        synchronized (lock2) {
            // Critical section 2 - can run concurrently with method1
        }
    }
}
```

#### **How Synchronized Works:**
```java
public class SynchronizedMechanism {
    // Every object has an intrinsic lock (monitor)
    private final Object monitor = new Object();
    
    public void demonstrateLocking() {
        synchronized (monitor) {
            // 1. Thread acquires monitor lock
            // 2. Executes critical section
            // 3. Releases lock when exiting block
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
        // Lock is automatically released here
    }
}
```

### 108. What is the difference between synchronized method and synchronized block?

| Aspect | Synchronized Method | Synchronized Block |
|--------|-------------------|-------------------|
| **Granularity** | Entire method | Specific code section |
| **Lock Object** | `this` (instance) or `Class` (static) | Any object |
| **Performance** | Lower (locks entire method) | Higher (locks only needed section) |
| **Flexibility** | Less flexible | More flexible |
| **Syntax** | Method modifier | Block with lock object |

#### **Synchronized Method Examples:**
```java
public class SynchronizedMethodExample {
    private int value = 0;
    
    // Locks entire method on 'this'
    public synchronized void incrementAndPrint() {
        value++;                           // Critical section
        System.out.println(value);         // Critical section
        doSomeOtherWork();                 // Also locked (may not need to be)
    }
    
    // Static synchronized method - locks on Class object
    public static synchronized void staticMethod() {
        System.out.println("Static method");
    }
    
    private void doSomeOtherWork() {
        // Some work that doesn't need synchronization
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
}
```

#### **Synchronized Block Examples:**
```java
public class SynchronizedBlockExample {
    private int value = 0;
    private final Object lock = new Object();
    
    public void incrementAndPrint() {
        // Only critical section is synchronized
        synchronized (lock) {
            value++;                       // Critical section
            System.out.println(value);     // Critical section
        }
        doSomeOtherWork();                 // Not synchronized - better performance
    }
    
    // Multiple locks for different resources
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private int resource1 = 0;
    private int resource2 = 0;
    
    public void updateResource1() {
        synchronized (lock1) {
            resource1++;
        }
    }
    
    public void updateResource2() {
        synchronized (lock2) {  // Can run concurrently with updateResource1
            resource2++;
        }
    }
}
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    private int counter = 0;
    
    // Synchronized method - locks entire method
    public synchronized void synchronizedMethod() {
        counter++;                    // Needs synchronization
        expensiveOperation();         // Doesn't need synchronization but is locked
    }
    
    // Synchronized block - locks only what's needed
    public void synchronizedBlock() {
        synchronized (this) {
            counter++;                // Only this needs synchronization
        }
        expensiveOperation();         // Not synchronized - better concurrency
    }
    
    private void expensiveOperation() {
        // Expensive operation that doesn't need synchronization
        try { Thread.sleep(10); } catch (InterruptedException e) {}
    }
}
```

### 109. What is deadlock?

**Deadlock** occurs when two or more threads are blocked forever, each waiting for the other to release a resource.

#### **Deadlock Conditions (All must be true):**
1. **Mutual Exclusion** - resources cannot be shared
2. **Hold and Wait** - threads hold resources while waiting for others
3. **No Preemption** - resources cannot be forcibly taken
4. **Circular Wait** - circular chain of threads waiting for each other

#### **Classic Deadlock Example:**
```java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock1...");
            
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            
            System.out.println("Thread 1: Waiting for lock2...");
            synchronized (lock2) {
                System.out.println("Thread 1: Acquired lock2!");
            }
        }
    }
    
    public void method2() {
        synchronized (lock2) {
            System.out.println("Thread 2: Holding lock2...");
            
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            
            System.out.println("Thread 2: Waiting for lock1...");
            synchronized (lock1) {
                System.out.println("Thread 2: Acquired lock1!");
            }
        }
    }
    
    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();
        
        Thread t1 = new Thread(example::method1);
        Thread t2 = new Thread(example::method2);
        
        t1.start();
        t2.start();
        
        // Deadlock occurs:
        // Thread 1 holds lock1, waits for lock2
        // Thread 2 holds lock2, waits for lock1
        // Both threads wait forever
    }
}
```

#### **Deadlock Detection:**
```java
public class DeadlockDetection {
    public static void detectDeadlock() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadBean.findDeadlockedThreads();
        
        if (deadlockedThreads != null) {
            ThreadInfo[] threadInfos = threadBean.getThreadInfo(deadlockedThreads);
            System.out.println("Deadlock detected!");
            for (ThreadInfo threadInfo : threadInfos) {
                System.out.println("Thread: " + threadInfo.getThreadName());
                System.out.println("Blocked on: " + threadInfo.getLockName());
            }
        }
    }
}
```

### 110. How do you prevent deadlock?

#### **Prevention Strategies:**

**1. Lock Ordering - Most Effective:**
```java
public class DeadlockPrevention {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // Always acquire locks in same order
    public void method1() {
        synchronized (lock1) {      // Always acquire lock1 first
            synchronized (lock2) {  // Then lock2
                // Critical section
            }
        }
    }
    
    public void method2() {
        synchronized (lock1) {      // Same order: lock1 first
            synchronized (lock2) {  // Then lock2
                // Critical section
            }
        }
    }
}
```

**2. Timeout with tryLock:**
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class TimeoutPrevention {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();
    
    public void safeMethod() {
        boolean acquired1 = false;
        boolean acquired2 = false;
        
        try {
            acquired1 = lock1.tryLock(1, TimeUnit.SECONDS);
            if (acquired1) {
                acquired2 = lock2.tryLock(1, TimeUnit.SECONDS);
                if (acquired2) {
                    // Both locks acquired - safe to proceed
                    performOperation();
                } else {
                    System.out.println("Could not acquire lock2, avoiding deadlock");
                }
            } else {
                System.out.println("Could not acquire lock1, avoiding deadlock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (acquired2) lock2.unlock();
            if (acquired1) lock1.unlock();
        }
    }
}
```

**3. Avoid Nested Locks:**
```java
public class AvoidNestedLocks {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // Instead of nested locks
    public void badApproach() {
        synchronized (lock1) {
            synchronized (lock2) {  // Nested - potential deadlock
                // Critical section
            }
        }
    }
    
    // Use separate synchronized blocks
    public void goodApproach() {
        synchronized (lock1) {
            // Work with resource 1
        }
        
        synchronized (lock2) {
            // Work with resource 2
        }
    }
}
```

**4. Use Higher-Level Concurrency Utilities:**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HigherLevelUtilities {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void avoidDeadlockWithExecutor() {
        // Use thread pools instead of manual synchronization
        executor.submit(() -> {
            // Task 1
        });
        
        executor.submit(() -> {
            // Task 2
        });
    }
}
```

### 111. What is race condition?

**Race condition** occurs when multiple threads access shared data concurrently and the final result depends on the timing of thread execution.

#### **Race Condition Example:**
```java
public class RaceConditionExample {
    private int counter = 0;
    
    public void increment() {
        // This is NOT atomic - race condition!
        // Step 1: Read current value
        // Step 2: Increment value
        // Step 3: Write back value
        counter++;
    }
    
    public static void main(String[] args) throws InterruptedException {
        RaceConditionExample example = new RaceConditionExample();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + example.counter);  // Often less than 2000
    }
}
```

#### **Why Race Conditions Occur:**
```java
// Thread interleaving example
// Thread 1: counter = 5
// Thread 1: reads counter (5)
// Thread 2: reads counter (5)  ← Both read same value
// Thread 1: increments (5 + 1 = 6)
// Thread 2: increments (5 + 1 = 6)  ← Both calculate same result
// Thread 1: writes counter (6)
// Thread 2: writes counter (6)  ← Lost increment!
// Result: counter = 6 instead of 7
```

#### **Solutions to Race Conditions:**

**1. Synchronization:**
```java
public class SynchronizedSolution {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;  // Now atomic
    }
}
```

**2. Atomic Variables:**
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSolution {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet();  // Atomic operation
    }
}
```

**3. Locks:**
```java
import java.util.concurrent.locks.ReentrantLock;

public class LockSolution {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
```

### 112. What is thread safety?

**Thread safety** means code behaves correctly when accessed by multiple threads simultaneously.

#### **Levels of Thread Safety:**

**1. Immutable - Highest Level:**
```java
public final class ImmutableClass {
    private final int value;
    private final String name;
    
    public ImmutableClass(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public int getValue() { return value; }
    public String getName() { return name; }
    
    // No setters - object cannot be modified
    // Thread-safe by design
}
```

**2. Thread-Safe Classes:**
```java
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;

public class ThreadSafeCollections {
    // Thread-safe collections
    private final Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
    private final List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    
    public void safeOperations() {
        // These operations are thread-safe
        concurrentMap.put("key", 1);
        synchronizedList.add("item");
    }
}
```

**3. Conditionally Thread-Safe:**
```java
public class ConditionallyThreadSafe {
    private final List<String> list = Collections.synchronizedList(new ArrayList<>());
    
    public void safeOperation() {
        list.add("item");  // Thread-safe
    }
    
    public void unsafeOperation() {
        // Iteration requires external synchronization
        synchronized (list) {
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
```

**4. Not Thread-Safe:**
```java
public class NotThreadSafe {
    private List<String> list = new ArrayList<>();  // Not thread-safe
    private int counter = 0;                        // Not thread-safe
    
    public void unsafeOperations() {
        list.add("item");  // Race condition possible
        counter++;         // Race condition possible
    }
}
```

#### **Achieving Thread Safety:**

**1. Synchronization:**
```java
public class SynchronizedThreadSafety {
    private int value = 0;
    
    public synchronized void setValue(int value) {
        this.value = value;
    }
    
    public synchronized int getValue() {
        return value;
    }
}
```

**2. Volatile for Simple Cases:**
```java
public class VolatileThreadSafety {
    private volatile boolean flag = false;
    
    public void setFlag(boolean flag) {
        this.flag = flag;  // Thread-safe for simple assignment
    }
    
    public boolean getFlag() {
        return flag;       // Thread-safe for simple read
    }
}
```

**3. Atomic Classes:**
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThreadSafety {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet();  // Thread-safe
    }
    
    public int getValue() {
        return counter.get();       // Thread-safe
    }
}
```

### 113. What is volatile keyword?

The **volatile keyword** ensures variable visibility across threads and prevents certain compiler optimizations.

#### **Volatile Guarantees:**
- **Visibility** - changes are immediately visible to all threads
- **Ordering** - prevents reordering of volatile reads/writes
- **No caching** - reads/writes go directly to main memory

#### **Volatile Example:**
```java
public class VolatileExample {
    private volatile boolean running = true;  // Volatile ensures visibility
    
    public void startWorker() {
        Thread worker = new Thread(() -> {
            while (running) {  // Always reads fresh value from main memory
                doWork();
            }
            System.out.println("Worker stopped");
        });
        worker.start();
    }
    
    public void stopWorker() {
        running = false;  // Change immediately visible to worker thread
    }
    
    private void doWork() {
        // Some work
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
}
```

#### **Without Volatile - Problem:**
```java
public class WithoutVolatile {
    private boolean running = true;  // Not volatile
    
    public void startWorker() {
        Thread worker = new Thread(() -> {
            // Compiler might optimize this to:
            // if (running) { while(true) { doWork(); } }
            // Worker thread might never see running = false
            while (running) {
                doWork();
            }
        });
        worker.start();
    }
    
    public void stopWorker() {
        running = false;  // Change might not be visible to worker thread
    }
}
```

#### **Volatile Use Cases:**

**1. Status Flags:**
```java
public class StatusFlag {
    private volatile boolean shutdown = false;
    
    public void shutdown() {
        shutdown = true;
    }
    
    public void doWork() {
        while (!shutdown) {
            // Process work
        }
    }
}
```

**2. Double-Checked Locking:**
```java
public class Singleton {
    private static volatile Singleton instance;  // Volatile prevents reordering
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();  // Volatile ensures proper initialization
                }
            }
        }
        return instance;
    }
}
```

#### **Volatile Limitations:**
```java
public class VolatileLimitations {
    private volatile int counter = 0;
    
    public void increment() {
        counter++;  // NOT atomic! Still has race condition
        // This is actually: counter = counter + 1
        // Read, increment, write - not atomic even with volatile
    }
    
    // Solution: Use AtomicInteger or synchronization
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    public void safeIncrement() {
        atomicCounter.incrementAndGet();  // Atomic operation
    }
}
```

### 114. What is the difference between synchronized and volatile?

| Aspect | synchronized | volatile |
|--------|-------------|----------|
| **Purpose** | Mutual exclusion + visibility | Visibility only |
| **Atomicity** | Provides atomicity | No atomicity |
| **Blocking** | Can block threads | Non-blocking |
| **Performance** | Higher overhead | Lower overhead |
| **Scope** | Methods/blocks | Variables only |
| **Compound Operations** | Safe | Not safe |

#### **Synchronized - Mutual Exclusion:**
```java
public class SynchronizedExample {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;  // Atomic: only one thread can execute this
    }
    
    public synchronized int getCounter() {
        return counter;  // Consistent read
    }
    
    // Provides both atomicity and visibility
}
```

#### **Volatile - Visibility Only:**
```java
public class VolatileExample {
    private volatile int counter = 0;
    
    public void increment() {
        counter++;  // NOT atomic! Race condition still exists
        // Multiple threads can interfere with each other
    }
    
    public int getCounter() {
        return counter;  // Always reads fresh value
    }
    
    // Provides visibility but not atomicity
}
```

#### **When to Use Each:**

**Use synchronized for:**
```java
public class UseSynchronized {
    private int balance = 1000;
    
    // Compound operation - needs atomicity
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {  // Read
            balance -= amount;    // Write
        }
    }
    
    // Multiple operations that must be atomic together
    public synchronized void transfer(Account other, int amount) {
        this.balance -= amount;
        other.balance += amount;
    }
}
```

**Use volatile for:**
```java
public class UseVolatile {
    private volatile boolean shutdown = false;
    private volatile int status = 0;
    
    // Simple flag - only needs visibility
    public void shutdown() {
        shutdown = true;  // Simple write
    }
    
    public boolean isShutdown() {
        return shutdown;  // Simple read
    }
    
    // Status updates - simple assignments
    public void updateStatus(int newStatus) {
        status = newStatus;  // Simple write, visibility needed
    }
}
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    private volatile int volatileCounter = 0;
    private int synchronizedCounter = 0;
    
    // Faster - no blocking
    public void incrementVolatile() {
        volatileCounter++;  // Still not thread-safe for increment!
    }
    
    // Slower - thread blocking
    public synchronized void incrementSynchronized() {
        synchronizedCounter++;  // Thread-safe but slower
    }
    
    // Best of both worlds for simple operations
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    public void incrementAtomic() {
        atomicCounter.incrementAndGet();  // Fast and thread-safe
    }
}
```

### 115. What is atomic operation?

An **atomic operation** completes entirely or not at all, without interruption from other threads.

#### **Characteristics of Atomic Operations:**
- **Indivisible** - cannot be interrupted midway
- **Thread-safe** - safe for concurrent access
- **No partial states** - either complete success or complete failure
- **Lock-free** - no explicit synchronization needed

#### **Java's Atomic Classes:**
```java
import java.util.concurrent.atomic.*;

public class AtomicOperations {
    // Atomic primitives
    private final AtomicInteger atomicInt = new AtomicInteger(0);
    private final AtomicLong atomicLong = new AtomicLong(0L);
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final AtomicReference<String> atomicRef = new AtomicReference<>("initial");
    
    public void demonstrateAtomicOperations() {
        // All these operations are atomic
        atomicInt.incrementAndGet();        // Atomic increment
        atomicInt.decrementAndGet();        // Atomic decrement
        atomicInt.addAndGet(5);             // Atomic add
        atomicInt.compareAndSet(5, 10);     // Atomic compare-and-swap
        
        atomicBoolean.compareAndSet(false, true);  // Atomic flag toggle
        atomicRef.compareAndSet("old", "new");     // Atomic reference update
    }
}
```

#### **Compare-and-Swap (CAS) Operations:**
```java
public class CompareAndSwap {
    private final AtomicInteger value = new AtomicInteger(0);
    
    public void atomicIncrement() {
        int current;
        int updated;
        do {
            current = value.get();           // Read current value
            updated = current + 1;           // Calculate new value
        } while (!value.compareAndSet(current, updated));  // Atomic update
        
        // CAS ensures: if value is still 'current', set it to 'updated'
        // If another thread changed it, retry the operation
    }
    
    public void safeUpdate(int newValue) {
        // Only update if current value is less than new value
        int current;
        do {
            current = value.get();
            if (current >= newValue) {
                break;  // Don't update
            }
        } while (!value.compareAndSet(current, newValue));
    }
}
```

#### **Atomic vs Non-Atomic Operations:**
```java
public class AtomicVsNonAtomic {
    private volatile int volatileCounter = 0;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    // NOT atomic - race condition possible
    public void nonAtomicIncrement() {
        volatileCounter++;  // Read, increment, write - 3 separate operations
    }
    
    // Atomic - thread-safe
    public void atomicIncrement() {
        atomicCounter.incrementAndGet();  // Single atomic operation
    }
    
    // Demonstration of the difference
    public static void main(String[] args) throws InterruptedException {
        AtomicVsNonAtomic example = new AtomicVsNonAtomic();
        
        // Create 1000 threads, each incrementing 1000 times
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.nonAtomicIncrement();  // Race conditions
                    example.atomicIncrement();     // Thread-safe
                }
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("Non-atomic result: " + example.volatileCounter);  // Usually < 1000000
        System.out.println("Atomic result: " + example.atomicCounter.get()); // Always 1000000
    }
}
```

#### **Custom Atomic Operations:**
```java
public class CustomAtomicOperations {
    private final AtomicReference<Node> head = new AtomicReference<>();
    
    static class Node {
        final int value;
        final Node next;
        
        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    
    // Lock-free stack push
    public void push(int value) {
        Node newNode;
        Node currentHead;
        do {
            currentHead = head.get();
            newNode = new Node(value, currentHead);
        } while (!head.compareAndSet(currentHead, newNode));
    }
    
    // Lock-free stack pop
    public Integer pop() {
        Node currentHead;
        Node newHead;
        do {
            currentHead = head.get();
            if (currentHead == null) {
                return null;  // Stack is empty
            }
            newHead = currentHead.next;
        } while (!head.compareAndSet(currentHead, newHead));
        
        return currentHead.value;
    }
}
```

#### **Performance Benefits:**
```java
public class AtomicPerformance {
    private int synchronizedCounter = 0;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    // Slower - uses locks
    public synchronized void synchronizedIncrement() {
        synchronizedCounter++;
    }
    
    // Faster - lock-free
    public void atomicIncrement() {
        atomicCounter.incrementAndGet();
    }
    
    // Atomic operations are generally faster than synchronized
    // because they don't involve thread blocking/unblocking
}
```
