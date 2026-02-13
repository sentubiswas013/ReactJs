# 🔹 Core Java (Scenario-Based) - Solutions

## 1. Your application is facing race conditions in a multi-threaded environment. How would you fix it?

**Steps to Fix:**
1. Identify shared mutable state
2. Use synchronization mechanisms
3. Choose appropriate concurrency control
4. Test with concurrent load

**Solutions:**

**Option 1: Synchronized Block**
```java
public class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

**Option 2: ReentrantLock**
```java
public class Counter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
```

**Option 3: Atomic Variables (Best for simple operations)**
```java
public class Counter {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet();
    }
    
    public int getCount() {
        return count.get();
    }
}
```

**Option 4: ConcurrentHashMap for collections**
```java
Map<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1);
```

---

## 2. You see high CPU usage due to threads. How do you analyze and optimize?

**Analysis Steps:**

**Step 1: Identify Thread Issues**
```bash
# Get thread dump
jstack <pid> > thread_dump.txt

# Monitor CPU per thread
top -H -p <pid>
```

**Step 2: Analyze Thread Dump**
```java
// Look for:
// - Busy waiting loops
// - Excessive thread creation
// - Thread contention
```

**Step 3: Common Issues & Fixes**

**Issue 1: Busy Waiting**
```java
// ❌ Bad - Busy waiting
while (!condition) {
    // CPU spinning
}

// ✅ Good - Use wait/notify
synchronized (lock) {
    while (!condition) {
        lock.wait();
    }
}
```

**Issue 2: Too Many Threads**
```java
// ❌ Bad - Creating threads on demand
new Thread(() -> process()).start();

// ✅ Good - Use thread pool
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> process());
```

**Issue 3: Inefficient Thread Pool**
```java
// ✅ Optimize thread pool size
int cores = Runtime.getRuntime().availableProcessors();
// CPU-intensive: cores
// I/O-intensive: cores * 2
ExecutorService executor = Executors.newFixedThreadPool(cores);
```

---

## 3. Your application has memory leaks in production. How do you detect and resolve them?

**Detection Steps:**

**Step 1: Monitor Heap Usage**
```bash
# Enable GC logging
-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log

# Take heap dump
jmap -dump:live,format=b,file=heap.bin <pid>
```

**Step 2: Analyze with Tools**
```bash
# Use Eclipse MAT, VisualVM, or JProfiler
# Look for:
# - Objects with high retained size
# - Growing collections
# - Unclosed resources
```

**Common Leak Scenarios & Fixes:**

**Leak 1: Unclosed Resources**
```java
// ❌ Bad
public void readFile() throws IOException {
    FileInputStream fis = new FileInputStream("file.txt");
    // If exception occurs, stream not closed
}

// ✅ Good - Try-with-resources
public void readFile() throws IOException {
    try (FileInputStream fis = new FileInputStream("file.txt")) {
        // Auto-closed
    }
}
```

**Leak 2: Static Collections**
```java
// ❌ Bad - Never cleared
public class Cache {
    private static Map<String, Object> cache = new HashMap<>();
    
    public static void put(String key, Object value) {
        cache.put(key, value); // Grows indefinitely
    }
}

// ✅ Good - Use bounded cache
public class Cache {
    private static Map<String, Object> cache = 
        Collections.synchronizedMap(new LinkedHashMap<String, Object>(100, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 100;
            }
        });
}
```

**Leak 3: Listeners Not Removed**
```java
// ❌ Bad
button.addActionListener(listener); // Never removed

// ✅ Good
button.removeActionListener(listener);
```

**Leak 4: ThreadLocal Not Cleared**
```java
// ❌ Bad
private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

// ✅ Good
try {
    threadLocal.set(connection);
    // use connection
} finally {
    threadLocal.remove(); // Always remove
}
```

---

## 4. How would you design an immutable class used in concurrent systems?

**Design Steps:**

**Step 1: Make class final**
**Step 2: Make all fields private and final**
**Step 3: No setter methods**
**Step 4: Initialize all fields in constructor**
**Step 5: Deep copy mutable objects**

**Example:**
```java
public final class ImmutableUser {
    private final String name;
    private final int age;
    private final List<String> roles;
    private final Date createdDate;
    
    public ImmutableUser(String name, int age, List<String> roles, Date createdDate) {
        this.name = name;
        this.age = age;
        // Deep copy mutable objects
        this.roles = roles != null ? new ArrayList<>(roles) : new ArrayList<>();
        this.createdDate = createdDate != null ? new Date(createdDate.getTime()) : null;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Return defensive copy
    public List<String> getRoles() {
        return new ArrayList<>(roles);
    }
    
    public Date getCreatedDate() {
        return createdDate != null ? new Date(createdDate.getTime()) : null;
    }
}
```

**Benefits in Concurrent Systems:**
- Thread-safe by design
- No synchronization needed
- Can be safely shared
- Cacheable

---

## 5. You need a thread-safe Singleton. How would you implement it?

**Best Approach: Bill Pugh Singleton (Initialization-on-demand holder)**
```java
public class Singleton {
    private Singleton() {}
    
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

**Alternative 1: Enum Singleton (Simplest & Best)**
```java
public enum Singleton {
    INSTANCE;
    
    public void doSomething() {
        // Business logic
    }
}

// Usage
Singleton.INSTANCE.doSomething();
```

**Alternative 2: Double-Checked Locking**
```java
public class Singleton {
    private static volatile Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

**Why Thread-Safe:**
- Bill Pugh: Class loading is thread-safe by JVM
- Enum: JVM guarantees single instance
- Double-checked: volatile + synchronized

---

## 6. You must process a 20GB file without OutOfMemoryError. How?

**Solution: Stream Processing**

**Step 1: Read Line by Line**
```java
public void processLargeFile(String filePath) throws IOException {
    try (BufferedReader reader = new BufferedReader(
            new FileReader(filePath), 8192 * 4)) { // 32KB buffer
        
        String line;
        while ((line = reader.readLine()) != null) {
            processLine(line);
        }
    }
}

private void processLine(String line) {
    // Process each line individually
}
```

**Step 2: NIO for Better Performance**
```java
public void processWithNIO(String filePath) throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(
            Paths.get(filePath), StandardCharsets.UTF_8)) {
        
        reader.lines()
              .forEach(this::processLine);
    }
}
```

**Step 3: Parallel Processing with Streams**
```java
public void processParallel(String filePath) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
        lines.parallel()
             .forEach(this::processLine);
    }
}
```

**Step 4: Memory-Mapped Files for Random Access**
```java
public void processWithMemoryMap(String filePath) throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(filePath, "r");
         FileChannel channel = file.getChannel()) {
        
        long fileSize = channel.size();
        long position = 0;
        long chunkSize = 100 * 1024 * 1024; // 100MB chunks
        
        while (position < fileSize) {
            long size = Math.min(chunkSize, fileSize - position);
            MappedByteBuffer buffer = channel.map(
                FileChannel.MapMode.READ_ONLY, position, size);
            
            processBuffer(buffer);
            position += size;
        }
    }
}
```

**Key Points:**
- Never load entire file into memory
- Process in chunks/streams
- Use appropriate buffer size
- Consider parallel processing for CPU-intensive tasks

---

## 7. Your HashMap performance degraded. What could be the reason?

**Common Reasons & Solutions:**

**Reason 1: Poor hashCode() Implementation**
```java
// ❌ Bad - All objects get same hash
@Override
public int hashCode() {
    return 1; // All collisions!
}

// ✅ Good - Proper distribution
@Override
public int hashCode() {
    return Objects.hash(id, name, email);
}
```

**Reason 2: High Load Factor**
```java
// Default load factor is 0.75
// If too many elements, many collisions occur

// ✅ Solution: Initialize with proper capacity
int expectedSize = 1000;
Map<String, String> map = new HashMap<>(expectedSize * 4 / 3);
```

**Reason 3: Concurrent Modification**
```java
// ❌ Bad - ConcurrentModificationException or corruption
Map<String, String> map = new HashMap<>();
// Multiple threads modifying

// ✅ Good - Use ConcurrentHashMap
Map<String, String> map = new ConcurrentHashMap<>();
```

**Reason 4: Too Many Collisions (Java 7)**
```java
// In Java 7, collisions create linked list (O(n) lookup)
// In Java 8+, converts to tree after 8 collisions (O(log n))

// ✅ Ensure good hashCode distribution
```

**Performance Analysis:**
```java
public class HashMapAnalysis {
    public static void analyzeHashMap(Map<?, ?> map) {
        try {
            Field tableField = HashMap.class.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] table = (Object[]) tableField.get(map);
            
            int emptyBuckets = 0;
            int collisions = 0;
            
            for (Object bucket : table) {
                if (bucket == null) {
                    emptyBuckets++;
                } else {
                    // Analyze bucket chain length
                }
            }
            
            System.out.println("Empty buckets: " + emptyBuckets);
            System.out.println("Collisions: " + collisions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 8. How would you implement a custom thread pool?

**Implementation:**

```java
public class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> threads;
    private volatile boolean isShutdown = false;
    
    public CustomThreadPool(int poolSize, int queueSize) {
        taskQueue = new LinkedBlockingQueue<>(queueSize);
        threads = new ArrayList<>(poolSize);
        
        for (int i = 0; i < poolSize; i++) {
            WorkerThread worker = new WorkerThread();
            threads.add(worker);
            worker.start();
        }
    }
    
    public void submit(Runnable task) throws InterruptedException {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        taskQueue.put(task);
    }
    
    public void shutdown() {
        isShutdown = true;
        for (WorkerThread thread : threads) {
            thread.interrupt();
        }
    }
    
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    if (isShutdown) {
                        break;
                    }
                } catch (Exception e) {
                    // Log exception
                    e.printStackTrace();
                }
            }
        }
    }
}
```

**Usage:**
```java
CustomThreadPool pool = new CustomThreadPool(5, 100);

pool.submit(() -> {
    System.out.println("Task executed by: " + Thread.currentThread().getName());
});

pool.shutdown();
```

**Key Components:**
1. **BlockingQueue**: Holds pending tasks
2. **Worker Threads**: Execute tasks from queue
3. **Submit Method**: Adds tasks to queue
4. **Shutdown**: Gracefully stops pool

---

## 9. You need to design a LRU cache. How would you implement it?

**Implementation using LinkedHashMap:**

```java
public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public synchronized V get(K key) {
        return cache.get(key);
    }
    
    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }
    
    public synchronized int size() {
        return cache.size();
    }
}
```

**Manual Implementation with HashMap + Doubly Linked List:**

```java
public class LRUCache<K, V> {
    private class Node {
        K key;
        V value;
        Node prev, next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<K, Node> cache;
    private final Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }
    
    public synchronized V get(K key) {
        Node node = cache.get(key);
        if (node == null) return null;
        
        moveToHead(node);
        return node.value;
    }
    
    public synchronized void put(K key, V value) {
        Node node = cache.get(key);
        
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            
            if (cache.size() > capacity) {
                Node removed = removeTail();
                cache.remove(removed.key);
            }
        }
    }
    
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}
```

**Usage:**
```java
LRUCache<String, String> cache = new LRUCache<>(3);
cache.put("1", "one");
cache.put("2", "two");
cache.put("3", "three");
cache.put("4", "four"); // "1" is evicted

String value = cache.get("2"); // "two", moves to front
```

**Time Complexity:**
- get(): O(1)
- put(): O(1)

---

## 10. How do you handle deadlock situations in Java?

**Understanding Deadlock:**
```java
// ❌ Deadlock Example
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            System.out.println("Lock1 acquired");
            synchronized (lock2) {
                System.out.println("Lock2 acquired");
            }
        }
    }
    
    public void method2() {
        synchronized (lock2) {
            System.out.println("Lock2 acquired");
            synchronized (lock1) {
                System.out.println("Lock1 acquired");
            }
        }
    }
}
```

**Prevention Strategies:**

**Strategy 1: Lock Ordering**
```java
// ✅ Always acquire locks in same order
public class NoDeadlock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                // Safe
            }
        }
    }
    
    public void method2() {
        synchronized (lock1) { // Same order
            synchronized (lock2) {
                // Safe
            }
        }
    }
}
```

**Strategy 2: Lock Timeout with tryLock()**
```java
public class TimeoutLock {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    
    public void method1() throws InterruptedException {
        while (true) {
            boolean lock1Acquired = lock1.tryLock(100, TimeUnit.MILLISECONDS);
            if (!lock1Acquired) continue;
            
            try {
                boolean lock2Acquired = lock2.tryLock(100, TimeUnit.MILLISECONDS);
                if (!lock2Acquired) continue;
                
                try {
                    // Critical section
                    return;
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }
        }
    }
}
```

**Strategy 3: Single Lock**
```java
// ✅ Use single lock for related operations
public class SingleLock {
    private final Object lock = new Object();
    
    public void method1() {
        synchronized (lock) {
            // Operation 1
        }
    }
    
    public void method2() {
        synchronized (lock) {
            // Operation 2
        }
    }
}
```

**Strategy 4: Avoid Nested Locks**
```java
// ✅ Refactor to avoid nested synchronization
public class NoNestedLocks {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            // Operation on resource 1
        }
        // Release lock1 before acquiring lock2
        synchronized (lock2) {
            // Operation on resource 2
        }
    }
}
```

**Detection:**
```java
// Use ThreadMXBean to detect deadlocks
public class DeadlockDetector {
    public static void detectDeadlock() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadBean.findDeadlockedThreads();
        
        if (deadlockedThreads != null) {
            ThreadInfo[] threadInfos = threadBean.getThreadInfo(deadlockedThreads);
            System.out.println("Deadlock detected!");
            for (ThreadInfo info : threadInfos) {
                System.out.println(info.getThreadName() + " - " + info.getLockName());
            }
        }
    }
}
```

**Best Practices:**
1. Always acquire locks in the same order
2. Use timeout with tryLock()
3. Minimize lock scope
4. Avoid nested locks when possible
5. Use higher-level concurrency utilities (ConcurrentHashMap, BlockingQueue)
6. Monitor for deadlocks in production

---

# 🔹 Collections & Streams - Solutions

## 11. You need unique elements in insertion order. Which collection and why?

**Answer: LinkedHashSet**

**Why LinkedHashSet:**
- Maintains insertion order (unlike HashSet)
- Guarantees uniqueness (unlike ArrayList)
- O(1) add, remove, contains operations
- Uses doubly-linked list + hash table

**Example:**
```java
Set<String> uniqueOrdered = new LinkedHashSet<>();
uniqueOrdered.add("Apple");
uniqueOrdered.add("Banana");
uniqueOrdered.add("Apple"); // Duplicate ignored
uniqueOrdered.add("Cherry");

System.out.println(uniqueOrdered); // [Apple, Banana, Cherry]
```

**Comparison with Alternatives:**

```java
// HashSet - No order guarantee
Set<String> hashSet = new HashSet<>(); // Fast but unordered

// TreeSet - Sorted order (not insertion order)
Set<String> treeSet = new TreeSet<>(); // Sorted but O(log n)

// ArrayList - Order but allows duplicates
List<String> list = new ArrayList<>(); // Duplicates allowed
```

**Real-World Use Case:**
```java
public class UserActivityTracker {
    private Set<String> visitedPages = new LinkedHashSet<>();
    
    public void trackPage(String page) {
        visitedPages.add(page); // Maintains visit order, no duplicates
    }
    
    public List<String> getBrowsingHistory() {
        return new ArrayList<>(visitedPages);
    }
}
```

---

## 12. Your system requires high read, low write concurrency. Which collection would you use?

**Answer: CopyOnWriteArrayList or CopyOnWriteArraySet**

**Why CopyOnWrite Collections:**
- Reads don't require locking (thread-safe)
- Writes create a new copy (expensive but rare)
- Perfect for high read, low write scenarios
- No ConcurrentModificationException

**Example:**
```java
// For List
List<String> list = new CopyOnWriteArrayList<>();
list.add("Item1");
list.add("Item2");

// Multiple threads can read without blocking
list.forEach(System.out::println);

// For Set
Set<String> set = new CopyOnWriteArraySet<>();
```

**Performance Comparison:**
```java
// ❌ Bad for high read - requires synchronization
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// ✅ Good for high read, low write
List<String> cowList = new CopyOnWriteArrayList<>();

// ✅ Good for high read/write with key-based access
Map<String, String> concurrentMap = new ConcurrentHashMap<>();
```

**Real-World Example:**
```java
public class ConfigurationManager {
    // Config rarely changes but frequently read
    private final List<String> allowedIPs = new CopyOnWriteArrayList<>();
    
    public void addAllowedIP(String ip) {
        allowedIPs.add(ip); // Rare write
    }
    
    public boolean isAllowed(String ip) {
        return allowedIPs.contains(ip); // Frequent read, no lock
    }
}
```

**When to Use:**
- Event listeners
- Configuration data
- Cache of rarely changing data
- Observer patterns

---

## 13. How would you group objects by multiple fields using Streams?

**Solution: Nested groupingBy or Composite Key**

**Approach 1: Nested groupingBy**
```java
class Employee {
    String department;
    String city;
    int salary;
    
    // Constructor, getters
}

// Group by department, then by city
Map<String, Map<String, List<Employee>>> grouped = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.groupingBy(Employee::getCity)
    ));

// Access: grouped.get("IT").get("NYC")
```

**Approach 2: Composite Key**
```java
class CompositeKey {
    String department;
    String city;
    
    public CompositeKey(String department, String city) {
        this.department = department;
        this.city = city;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return Objects.equals(department, that.department) &&
               Objects.equals(city, that.city);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(department, city);
    }
}

// Group by composite key
Map<CompositeKey, List<Employee>> grouped = employees.stream()
    .collect(Collectors.groupingBy(
        e -> new CompositeKey(e.getDepartment(), e.getCity())
    ));
```

**Approach 3: String Concatenation (Simple)**
```java
Map<String, List<Employee>> grouped = employees.stream()
    .collect(Collectors.groupingBy(
        e -> e.getDepartment() + "|" + e.getCity()
    ));
```

**Three-Level Grouping:**
```java
// Group by department -> city -> salary range
Map<String, Map<String, Map<String, List<Employee>>>> grouped = 
    employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.groupingBy(
                Employee::getCity,
                Collectors.groupingBy(e -> 
                    e.getSalary() > 50000 ? "High" : "Low"
                )
            )
        ));
```

---

## 14. You need to sort a list by 3 properties dynamically. How?

**Solution: Comparator.comparing with thenComparing**

**Static Sorting:**
```java
class Employee {
    String department;
    String name;
    int salary;
    // Constructor, getters
}

List<Employee> sorted = employees.stream()
    .sorted(Comparator.comparing(Employee::getDepartment)
                      .thenComparing(Employee::getName)
                      .thenComparing(Employee::getSalary))
    .collect(Collectors.toList());
```

**Dynamic Sorting:**
```java
public class DynamicSorter {
    
    public static List<Employee> sortDynamically(
            List<Employee> employees, 
            List<String> sortFields) {
        
        Comparator<Employee> comparator = null;
        
        for (String field : sortFields) {
            Comparator<Employee> fieldComparator = getComparator(field);
            comparator = (comparator == null) 
                ? fieldComparator 
                : comparator.thenComparing(fieldComparator);
        }
        
        return employees.stream()
            .sorted(comparator)
            .collect(Collectors.toList());
    }
    
    private static Comparator<Employee> getComparator(String field) {
        switch (field) {
            case "department": return Comparator.comparing(Employee::getDepartment);
            case "name": return Comparator.comparing(Employee::getName);
            case "salary": return Comparator.comparing(Employee::getSalary);
            default: throw new IllegalArgumentException("Unknown field: " + field);
        }
    }
}

// Usage
List<String> sortOrder = Arrays.asList("department", "salary", "name");
List<Employee> sorted = DynamicSorter.sortDynamically(employees, sortOrder);
```

**With Ascending/Descending:**
```java
public class SortConfig {
    String field;
    boolean ascending;
    
    public SortConfig(String field, boolean ascending) {
        this.field = field;
        this.ascending = ascending;
    }
}

public static List<Employee> sortWithDirection(
        List<Employee> employees, 
        List<SortConfig> configs) {
    
    Comparator<Employee> comparator = null;
    
    for (SortConfig config : configs) {
        Comparator<Employee> fieldComparator = getComparator(config.field);
        if (!config.ascending) {
            fieldComparator = fieldComparator.reversed();
        }
        comparator = (comparator == null) 
            ? fieldComparator 
            : comparator.thenComparing(fieldComparator);
    }
    
    return employees.stream()
        .sorted(comparator)
        .collect(Collectors.toList());
}
```

---

## 15. You must find duplicates in a 10M record list efficiently. Approach?

**Solution: Use HashSet for O(n) complexity**

**Approach 1: Find All Duplicates**
```java
public static <T> Set<T> findDuplicates(List<T> list) {
    Set<T> seen = new HashSet<>();
    Set<T> duplicates = new HashSet<>();
    
    for (T item : list) {
        if (!seen.add(item)) {
            duplicates.add(item);
        }
    }
    
    return duplicates;
}
```

**Approach 2: Using Streams**
```java
public static <T> Set<T> findDuplicatesStream(List<T> list) {
    Set<T> seen = new HashSet<>();
    return list.stream()
        .filter(e -> !seen.add(e))
        .collect(Collectors.toSet());
}
```

**Approach 3: Find Duplicates with Count**
```java
public static <T> Map<T, Long> findDuplicatesWithCount(List<T> list) {
    return list.stream()
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .filter(e -> e.getValue() > 1)
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue
        ));
}
```

**Approach 4: Memory-Efficient for Large Data**
```java
public static <T> Set<T> findDuplicatesMemoryEfficient(List<T> list) {
    // Use BitSet for primitive types or bloom filter for objects
    Set<T> duplicates = new HashSet<>();
    Set<T> seen = new HashSet<>(list.size() / 2); // Initial capacity
    
    for (T item : list) {
        if (seen.contains(item)) {
            duplicates.add(item);
        } else {
            seen.add(item);
        }
    }
    
    return duplicates;
}
```

**Performance Comparison:**
```java
// Time Complexity: O(n)
// Space Complexity: O(n)

// For 10M records:
List<Integer> largeList = new ArrayList<>(10_000_000);
// ... populate list

long start = System.currentTimeMillis();
Set<Integer> duplicates = findDuplicates(largeList);
long end = System.currentTimeMillis();
System.out.println("Time: " + (end - start) + "ms");
```

---

## 16. When would you use parallel streams in production?

**Use Parallel Streams When:**

1. **Large datasets** (typically > 10,000 elements)
2. **CPU-intensive operations** (not I/O bound)
3. **Independent operations** (no shared state)
4. **Stateless operations**

**Good Use Cases:**
```java
// ✅ CPU-intensive computation on large dataset
List<Integer> numbers = IntStream.range(0, 1_000_000)
    .boxed()
    .collect(Collectors.toList());

List<Integer> results = numbers.parallelStream()
    .map(n -> expensiveComputation(n))
    .collect(Collectors.toList());

// ✅ Filtering large collections
List<Employee> highEarners = employees.parallelStream()
    .filter(e -> e.getSalary() > 100000)
    .collect(Collectors.toList());
```

**Avoid Parallel Streams When:**
```java
// ❌ Small datasets
List<Integer> small = Arrays.asList(1, 2, 3, 4, 5);
small.parallelStream(); // Overhead > benefit

// ❌ I/O operations
files.parallelStream()
    .forEach(file -> readFromDatabase(file)); // Thread contention

// ❌ Shared mutable state
List<Integer> result = new ArrayList<>();
numbers.parallelStream()
    .forEach(n -> result.add(n)); // Race condition!

// ❌ Order-dependent operations
list.parallelStream()
    .forEachOrdered(System.out::println); // Defeats parallelism
```

**Correct Parallel Stream Usage:**
```java
// ✅ Use thread-safe collectors
List<Integer> result = numbers.parallelStream()
    .map(n -> n * 2)
    .collect(Collectors.toList()); // Thread-safe

// ✅ Use reduction operations
int sum = numbers.parallelStream()
    .mapToInt(Integer::intValue)
    .sum();

// ✅ Control parallelism
ForkJoinPool customPool = new ForkJoinPool(4);
customPool.submit(() ->
    numbers.parallelStream()
        .map(this::process)
        .collect(Collectors.toList())
).get();
```

**Performance Testing:**
```java
// Always benchmark before using parallel streams
long start = System.currentTimeMillis();
// sequential
long seqTime = System.currentTimeMillis() - start;

start = System.currentTimeMillis();
// parallel
long parTime = System.currentTimeMillis() - start;

System.out.println("Sequential: " + seqTime + "ms");
System.out.println("Parallel: " + parTime + "ms");
```

---

## 17. You need to convert List to Map with duplicate keys. How do you handle collisions?

**Solution: Use Collectors.toMap with merge function**

**Approach 1: Keep First Value**
```java
List<Employee> employees = Arrays.asList(
    new Employee("1", "John"),
    new Employee("1", "Jane"), // Duplicate key
    new Employee("2", "Bob")
);

Map<String, Employee> map = employees.stream()
    .collect(Collectors.toMap(
        Employee::getId,
        Function.identity(),
        (existing, replacement) -> existing // Keep first
    ));
```

**Approach 2: Keep Last Value**
```java
Map<String, Employee> map = employees.stream()
    .collect(Collectors.toMap(
        Employee::getId,
        Function.identity(),
        (existing, replacement) -> replacement // Keep last
    ));
```

**Approach 3: Merge Values**
```java
Map<String, Integer> salaryMap = employees.stream()
    .collect(Collectors.toMap(
        Employee::getDepartment,
        Employee::getSalary,
        (sal1, sal2) -> sal1 + sal2 // Sum salaries
    ));
```

**Approach 4: Collect to List (Group)**
```java
Map<String, List<Employee>> grouped = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

**Approach 5: Custom Merge Logic**
```java
Map<String, Employee> map = employees.stream()
    .collect(Collectors.toMap(
        Employee::getId,
        Function.identity(),
        (e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2 // Keep higher salary
    ));
```

**Approach 6: Throw Exception on Duplicate**
```java
Map<String, Employee> map = employees.stream()
    .collect(Collectors.toMap(
        Employee::getId,
        Function.identity(),
        (e1, e2) -> {
            throw new IllegalStateException("Duplicate key: " + e1.getId());
        }
    ));
```

**Real-World Example:**
```java
public class EmployeeService {
    
    // Latest employee record wins
    public Map<String, Employee> getLatestEmployeeRecords(List<Employee> records) {
        return records.stream()
            .collect(Collectors.toMap(
                Employee::getId,
                Function.identity(),
                (old, latest) -> latest.getTimestamp() > old.getTimestamp() 
                    ? latest : old,
                LinkedHashMap::new // Maintain order
            ));
    }
}
```

---

## 18. How do you make a collection immutable?

**Multiple Approaches:**

**Approach 1: Collections.unmodifiableXXX (Java 5+)**
```java
List<String> mutableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
List<String> immutableList = Collections.unmodifiableList(mutableList);

// immutableList.add("D"); // Throws UnsupportedOperationException

// ⚠️ Warning: Original list can still be modified
mutableList.add("D"); // Affects immutableList!
```

**Approach 2: List.of, Set.of, Map.of (Java 9+)**
```java
// ✅ Truly immutable
List<String> list = List.of("A", "B", "C");
Set<String> set = Set.of("A", "B", "C");
Map<String, Integer> map = Map.of("A", 1, "B", 2);

// list.add("D"); // Throws UnsupportedOperationException
// No null values allowed
```

**Approach 3: Collectors.toUnmodifiableXXX (Java 10+)**
```java
List<String> immutableList = list.stream()
    .filter(s -> s.startsWith("A"))
    .collect(Collectors.toUnmodifiableList());

Set<String> immutableSet = set.stream()
    .collect(Collectors.toUnmodifiableSet());

Map<String, Integer> immutableMap = list.stream()
    .collect(Collectors.toUnmodifiableMap(
        Function.identity(),
        String::length
    ));
```

**Approach 4: Guava ImmutableXXX**
```java
// Requires Guava library
ImmutableList<String> list = ImmutableList.of("A", "B", "C");
ImmutableSet<String> set = ImmutableSet.of("A", "B", "C");
ImmutableMap<String, Integer> map = ImmutableMap.of("A", 1, "B", 2);

// From existing collection
ImmutableList<String> copy = ImmutableList.copyOf(existingList);
```

**Comparison:**
```java
// Collections.unmodifiableList - View, not copy
List<String> original = new ArrayList<>(Arrays.asList("A", "B"));
List<String> unmod = Collections.unmodifiableList(original);
original.add("C"); // Affects unmod

// List.of - True immutable copy
List<String> immutable = List.of("A", "B");
// No way to modify

// Performance: List.of is more memory efficient
```

**Deep Immutability:**
```java
class Person {
    private final String name;
    private final List<String> hobbies;
    
    public Person(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = List.copyOf(hobbies); // Deep copy
    }
    
    public List<String> getHobbies() {
        return hobbies; // Already immutable
    }
}
```

---

## 19. What are performance trade-offs between ArrayList and LinkedList in real apps?

**ArrayList vs LinkedList:**

**ArrayList:**
```java
// ✅ Fast random access: O(1)
List<String> arrayList = new ArrayList<>();
String item = arrayList.get(1000); // Fast

// ✅ Fast iteration: O(n)
for (String s : arrayList) { } // Cache-friendly

// ❌ Slow insertion/deletion in middle: O(n)
arrayList.add(500, "new"); // Shifts elements

// ✅ Memory efficient: Contiguous memory
```

**LinkedList:**
```java
// ❌ Slow random access: O(n)
List<String> linkedList = new LinkedList<>();
String item = linkedList.get(1000); // Traverses nodes

// ❌ Slower iteration: O(n) but cache-unfriendly
for (String s : linkedList) { } // Pointer chasing

// ✅ Fast insertion/deletion at ends: O(1)
linkedList.addFirst("new"); // Fast
linkedList.addLast("new"); // Fast

// ❌ More memory: Node overhead (prev, next pointers)
```

**Performance Comparison:**
```java
// Random Access
arrayList.get(index);     // O(1) - ✅ Winner
linkedList.get(index);    // O(n) - ❌ Slow

// Add at End
arrayList.add(item);      // O(1) amortized - ✅
linkedList.add(item);     // O(1) - ✅

// Add at Beginning
arrayList.add(0, item);   // O(n) - ❌ Shifts all
linkedList.addFirst(item);// O(1) - ✅ Winner

// Add in Middle
arrayList.add(n/2, item); // O(n) - ❌
linkedList.add(n/2, item);// O(n) - ❌ (traverse + insert)

// Remove from End
arrayList.remove(size-1); // O(1) - ✅
linkedList.removeLast();  // O(1) - ✅

// Iteration
arrayList.forEach();      // Fast - ✅ Cache-friendly
linkedList.forEach();     // Slower - ❌ Cache misses

// Memory
arrayList: 4 bytes/element + array overhead
linkedList: 24 bytes/element (object + 2 pointers)
```

**Real-World Recommendations:**

```java
// ✅ Use ArrayList (95% of cases)
// - Random access needed
// - Iteration is common
// - Memory matters
// - Default choice

// ✅ Use LinkedList (rare cases)
// - Frequent insertions/deletions at beginning
// - Implementing Queue/Deque
// - No random access needed

// Better alternatives to LinkedList:
ArrayDeque<String> deque = new ArrayDeque<>(); // Better than LinkedList for queue
```

**Benchmark Example:**
```java
int size = 100_000;

// ArrayList - Fast
List<Integer> arrayList = new ArrayList<>();
long start = System.nanoTime();
for (int i = 0; i < size; i++) {
    arrayList.add(i);
}
long arrayTime = System.nanoTime() - start;

// LinkedList - Comparable for add at end
List<Integer> linkedList = new LinkedList<>();
start = System.nanoTime();
for (int i = 0; i < size; i++) {
    linkedList.add(i);
}
long linkedTime = System.nanoTime() - start;

// Random access - ArrayList wins
start = System.nanoTime();
for (int i = 0; i < 1000; i++) {
    arrayList.get(50000);
}
long arrayAccessTime = System.nanoTime() - start;

start = System.nanoTime();
for (int i = 0; i < 1000; i++) {
    linkedList.get(50000);
}
long linkedAccessTime = System.nanoTime() - start;
// linkedAccessTime >> arrayAccessTime
```

---

## 20. You need custom sorting logic reusable across services. How design it?

**Solution: Create Reusable Comparator Classes**

**Approach 1: Separate Comparator Classes**
```java
public class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Integer.compare(e1.getSalary(), e2.getSalary());
    }
}

public class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}

// Usage
employees.sort(new EmployeeSalaryComparator());
```

**Approach 2: Enum-Based Comparators**
```java
public enum EmployeeComparator implements Comparator<Employee> {
    BY_NAME {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.getName().compareTo(e2.getName());
        }
    },
    BY_SALARY {
        @Override
        public int compare(Employee e1, Employee e2) {
            return Integer.compare(e1.getSalary(), e2.getSalary());
        }
    },
    BY_DEPARTMENT {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.getDepartment().compareTo(e2.getDepartment());
        }
    };
}

// Usage
employees.sort(EmployeeComparator.BY_SALARY);
employees.sort(EmployeeComparator.BY_NAME.reversed());
```

**Approach 3: Builder Pattern for Complex Sorting**
```java
public class ComparatorBuilder<T> {
    private Comparator<T> comparator;
    
    public static <T> ComparatorBuilder<T> create() {
        return new ComparatorBuilder<>();
    }
    
    public <U extends Comparable<U>> ComparatorBuilder<T> thenComparing(
            Function<T, U> keyExtractor) {
        Comparator<T> newComparator = Comparator.comparing(keyExtractor);
        comparator = (comparator == null) 
            ? newComparator 
            : comparator.thenComparing(newComparator);
        return this;
    }
    
    public <U extends Comparable<U>> ComparatorBuilder<T> thenComparingDesc(
            Function<T, U> keyExtractor) {
        Comparator<T> newComparator = Comparator.comparing(keyExtractor).reversed();
        comparator = (comparator == null) 
            ? newComparator 
            : comparator.thenComparing(newComparator);
        return this;
    }
    
    public Comparator<T> build() {
        return comparator;
    }
}

// Usage
Comparator<Employee> comparator = ComparatorBuilder.<Employee>create()
    .thenComparing(Employee::getDepartment)
    .thenComparingDesc(Employee::getSalary)
    .thenComparing(Employee::getName)
    .build();

employees.sort(comparator);
```

**Approach 4: Utility Class with Static Methods**
```java
public class EmployeeComparators {
    
    public static Comparator<Employee> byName() {
        return Comparator.comparing(Employee::getName);
    }
    
    public static Comparator<Employee> bySalary() {
        return Comparator.comparing(Employee::getSalary);
    }
    
    public static Comparator<Employee> byDepartmentAndSalary() {
        return Comparator.comparing(Employee::getDepartment)
                         .thenComparing(Employee::getSalary);
    }
    
    public static Comparator<Employee> byMultipleFields(
            List<Function<Employee, Comparable>> extractors) {
        Comparator<Employee> comparator = null;
        for (Function<Employee, Comparable> extractor : extractors) {
            Comparator<Employee> current = Comparator.comparing(extractor);
            comparator = (comparator == null) 
                ? current 
                : comparator.thenComparing(current);
        }
        return comparator;
    }
}

// Usage
employees.sort(EmployeeComparators.byName());
employees.sort(EmployeeComparators.bySalary().reversed());
```

**Approach 5: Strategy Pattern with Configuration**
```java
public interface SortStrategy<T> {
    Comparator<T> getComparator();
}

public class EmployeeSortStrategy implements SortStrategy<Employee> {
    private final String sortField;
    private final boolean ascending;
    
    public EmployeeSortStrategy(String sortField, boolean ascending) {
        this.sortField = sortField;
        this.ascending = ascending;
    }
    
    @Override
    public Comparator<Employee> getComparator() {
        Comparator<Employee> comparator;
        
        switch (sortField) {
            case "name":
                comparator = Comparator.comparing(Employee::getName);
                break;
            case "salary":
                comparator = Comparator.comparing(Employee::getSalary);
                break;
            case "department":
                comparator = Comparator.comparing(Employee::getDepartment);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + sortField);
        }
        
        return ascending ? comparator : comparator.reversed();
    }
}

// Usage
SortStrategy<Employee> strategy = new EmployeeSortStrategy("salary", false);
employees.sort(strategy.getComparator());
```

**Reusable Across Microservices:**
```java
// Create shared library: common-utils.jar

// In common-utils module
public class GenericComparators {
    
    public static <T, U extends Comparable<U>> Comparator<T> comparing(
            Function<T, U> keyExtractor, boolean ascending) {
        Comparator<T> comparator = Comparator.comparing(keyExtractor);
        return ascending ? comparator : comparator.reversed();
    }
    
    public static <T> Comparator<T> multiField(
            List<ComparatorConfig<T>> configs) {
        Comparator<T> result = null;
        for (ComparatorConfig<T> config : configs) {
            Comparator<T> current = config.getComparator();
            result = (result == null) ? current : result.thenComparing(current);
        }
        return result;
    }
}

// Use in Service A, Service B, Service C
employees.sort(GenericComparators.comparing(Employee::getSalary, false));
```

# 🔹 Spring Boot Core - Solutions

## 21. Your REST API must return proper HTTP status and structured error response. How design?

**Solution: Create Custom Error Response Structure with @ControllerAdvice**

**Step 1: Define Error Response Structure**
```java
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
    private String path;
    
    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }
    
    // Getters and setters
}
```

**Step 2: Create Global Exception Handler**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex, 
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            IllegalArgumentException ex,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

**Step 3: Custom Exceptions**
```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

**Step 4: Controller Usage**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        return ResponseEntity.ok(user);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

---

## 22. You need global exception handling across microservices. How implement?

**Solution: Shared Exception Handling Library**

**Step 1: Create Common Library (common-exception-handler)**
```java
// In common-exception-handler module
@RestControllerAdvice
public class CommonExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request) {
        log.error("Business exception: {}", ex.getMessage());
        ApiError error = new ApiError(
            ex.getErrorCode(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiError error = new ApiError(
            "VALIDATION_ERROR",
            "Validation failed",
            request.getRequestURI(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
```

**Step 2: Common Error Models**
```java
public class ApiError {
    private String errorCode;
    private String message;
    private String path;
    private long timestamp;
    private Map<String, String> details;
    
    public ApiError(String errorCode, String message, String path) {
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
    }
    
    public ApiError(String errorCode, String message, String path, Map<String, String> details) {
        this(errorCode, message, path);
        this.details = details;
    }
    
    // Getters and setters
}

public class BusinessException extends RuntimeException {
    private String errorCode;
    private HttpStatus httpStatus;
    
    public BusinessException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    
    // Getters
}
```

**Step 3: Enable in Each Microservice**
```xml
<!-- pom.xml in each microservice -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>common-exception-handler</artifactId>
    <version>1.0.0</version>
</dependency>
```

```java
// Auto-configuration
@Configuration
@ComponentScan(basePackages = "com.company.common.exception")
public class ExceptionHandlerConfig {
}
```

**Step 4: Usage in Microservices**
```java
@Service
public class OrderService {
    
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException(
                "ORDER_NOT_FOUND",
                "Order not found: " + id,
                HttpStatus.NOT_FOUND
            ));
    }
}
```

---

## 23. Your application startup is slow. How diagnose?

**Diagnosis Steps:**

**Step 1: Enable Startup Logging**
```properties
# application.properties
logging.level.org.springframework=DEBUG
spring.main.lazy-initialization=false
debug=true
```

**Step 2: Use Spring Boot Actuator**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
management.endpoints.web.exposure.include=startup
management.endpoint.startup.enabled=true
```

**Step 3: Add Startup Listener**
```java
@Component
public class StartupTimeLogger implements ApplicationListener<ApplicationReadyEvent> {
    
    private static final Logger log = LoggerFactory.getLogger(StartupTimeLogger.class);
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        long startupTime = System.currentTimeMillis() - event.getTimestamp();
        log.info("Application started in {} ms", startupTime);
    }
}
```

**Step 4: Profile Bean Creation**
```java
@Component
public class BeanInitializationLogger implements BeanPostProcessor {
    
    private Map<String, Long> beanStartTimes = new ConcurrentHashMap<>();
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        beanStartTimes.put(beanName, System.currentTimeMillis());
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        long startTime = beanStartTimes.get(beanName);
        long duration = System.currentTimeMillis() - startTime;
        if (duration > 100) { // Log beans taking > 100ms
            System.out.println("Bean " + beanName + " took " + duration + "ms");
        }
        return bean;
    }
}
```

**Common Solutions:**

**Solution 1: Enable Lazy Initialization**
```properties
spring.main.lazy-initialization=true
```

**Solution 2: Exclude Unnecessary Auto-configurations**
```java
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class Application {
}
```

**Solution 3: Optimize Component Scanning**
```java
@SpringBootApplication
@ComponentScan(basePackages = "com.company.specific") // Narrow scope
public class Application {
}
```

**Solution 4: Use Conditional Beans**
```java
@Configuration
public class DatabaseConfig {
    
    @Bean
    @ConditionalOnProperty(name = "feature.database.enabled", havingValue = "true")
    public DataSource dataSource() {
        // Only create if needed
    }
}
```

---

## 24. You must externalize configuration per environment (dev, qa, prod). How?

**Solution: Profile-Specific Configuration Files**

**Approach 1: Profile-Specific Files**
```
src/main/resources/
├── application.properties          # Common config
├── application-dev.properties      # Dev config
├── application-qa.properties       # QA config
└── application-prod.properties     # Prod config
```

```properties
# application.properties (common)
spring.application.name=myapp
server.port=8080

# application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/devdb
spring.datasource.username=devuser
logging.level.root=DEBUG

# application-qa.properties
spring.datasource.url=jdbc:mysql://qa-server:3306/qadb
spring.datasource.username=qauser
logging.level.root=INFO

# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-server:3306/proddb
spring.datasource.username=produser
logging.level.root=WARN
```

**Activate Profile:**
```bash
# Command line
java -jar app.jar --spring.profiles.active=prod

# Environment variable
export SPRING_PROFILES_ACTIVE=prod

# application.properties
spring.profiles.active=dev
```

**Approach 2: YAML Configuration**
```yaml
# application.yml
spring:
  application:
    name: myapp

---
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:mysql://localhost:3306/devdb
    username: devuser

---
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    url: jdbc:mysql://prod-server:3306/proddb
    username: produser
```

**Approach 3: External Configuration**
```bash
# Load from external location
java -jar app.jar --spring.config.location=file:/etc/config/application.properties

# Multiple locations
java -jar app.jar --spring.config.location=classpath:/,file:/etc/config/
```

**Approach 4: Environment Variables**
```properties
# application.properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

```bash
# Set environment variables
export DB_URL=jdbc:mysql://prod-server:3306/proddb
export DB_USERNAME=produser
export DB_PASSWORD=secret
```

**Approach 5: Config Server (Microservices)**
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
}
```

```properties
# Config server
spring.cloud.config.server.git.uri=https://github.com/company/config-repo
```

---

## 25. How would you secure sensitive properties like DB passwords?

**Solution: Multiple Security Approaches**

**Approach 1: Jasypt Encryption**
```xml
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```

```properties
# application.properties
jasypt.encryptor.password=${JASYPT_PASSWORD}
spring.datasource.password=ENC(encrypted_password_here)
```

```java
// Encrypt password
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("master-key");
        String encrypted = encryptor.encrypt("myPassword");
        System.out.println("Encrypted: " + encrypted);
    }
}
```

**Approach 2: Environment Variables**
```properties
spring.datasource.password=${DB_PASSWORD}
```

```bash
export DB_PASSWORD=secret_password
```

**Approach 3: AWS Secrets Manager**
```xml
<dependency>
    <groupId>com.amazonaws.secretsmanager</groupId>
    <artifactId>aws-secretsmanager-jdbc</artifactId>
</dependency>
```

```properties
spring.datasource.url=jdbc-secretsmanager:mysql://prod-db:3306/mydb
spring.datasource.username=admin
spring.datasource.driver-class-name=com.amazonaws.secretsmanager.sql.AWSSecretsManagerMySQLDriver
```

**Approach 4: HashiCorp Vault**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-vault-config</artifactId>
</dependency>
```

```properties
spring.cloud.vault.uri=https://vault-server:8200
spring.cloud.vault.token=${VAULT_TOKEN}
spring.cloud.vault.kv.enabled=true
```

**Approach 5: Spring Cloud Config with Encryption**
```properties
# In config server
encrypt.key=my-secret-key

# In application properties
spring.datasource.password={cipher}encrypted_value
```

**Best Practices:**
```java
@Configuration
public class SecurityConfig {
    
    // Never log sensitive data
    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        
        // Don't log password
        log.info("Connecting to database: {}", url);
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
```

---

## 26. You need custom auto-configuration. How create it?

**Solution: Create Custom Starter**

**Step 1: Create Auto-Configuration Class**
```java
@Configuration
@ConditionalOnClass(MyService.class)
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService(MyProperties properties) {
        return new MyService(properties.getApiKey(), properties.getTimeout());
    }
}
```

**Step 2: Create Configuration Properties**
```java
@ConfigurationProperties(prefix = "myservice")
public class MyProperties {
    private String apiKey;
    private int timeout = 5000;
    
    // Getters and setters
}
```

**Step 3: Register Auto-Configuration**
```
# src/main/resources/META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.company.autoconfigure.MyAutoConfiguration
```

**Step 4: Create Service**
```java
public class MyService {
    private final String apiKey;
    private final int timeout;
    
    public MyService(String apiKey, int timeout) {
        this.apiKey = apiKey;
        this.timeout = timeout;
    }
    
    public void doSomething() {
        // Implementation
    }
}
```

**Step 5: Usage in Application**
```xml
<!-- Add dependency -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>myservice-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

```properties
# application.properties
myservice.api-key=your-key
myservice.timeout=10000
```

```java
@RestController
public class MyController {
    
    @Autowired
    private MyService myService; // Auto-configured
    
    @GetMapping("/test")
    public String test() {
        myService.doSomething();
        return "Success";
    }
}
```

**Advanced: Conditional Configuration**
```java
@Configuration
@ConditionalOnProperty(name = "myservice.enabled", havingValue = "true", matchIfMissing = true)
public class MyAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(DataSource.class)
    public MyService myService(MyProperties properties, DataSource dataSource) {
        return new MyService(properties, dataSource);
    }
}
```

---

## 27. How do you reduce memory footprint in Spring Boot apps?

**Solutions:**

**Solution 1: Enable Lazy Initialization**
```properties
spring.main.lazy-initialization=true
```

**Solution 2: Exclude Unused Auto-Configurations**
```java
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    JmxAutoConfiguration.class,
    WebMvcAutoConfiguration.class
})
public class Application {
}
```

**Solution 3: Optimize JVM Settings**
```bash
java -Xms256m -Xmx512m \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+UseStringDeduplication \
     -jar app.jar
```

**Solution 4: Use Lightweight Embedded Server**
```xml
<!-- Replace Tomcat with Undertow -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-undertow</artifactId>
</dependency>
```

**Solution 5: Optimize Connection Pools**
```properties
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=2
```

**Solution 6: Disable Unnecessary Features**
```properties
spring.jmx.enabled=false
spring.devtools.restart.enabled=false
management.endpoints.enabled-by-default=false
```

**Solution 7: Use Prototype Scope for Heavy Objects**
```java
@Bean
@Scope("prototype")
public HeavyObject heavyObject() {
    return new HeavyObject();
}
```

**Solution 8: Optimize Logging**
```properties
logging.level.root=WARN
logging.pattern.console=%d{HH:mm:ss} %-5level %logger{36} - %msg%n
```

---

## 28. Your bean is getting created twice. How troubleshoot?

**Troubleshooting Steps:**

**Step 1: Enable Debug Logging**
```properties
logging.level.org.springframework.beans=DEBUG
logging.level.org.springframework.context=DEBUG
```

**Step 2: Check for Multiple Definitions**
```java
// ❌ Problem: Bean defined in multiple places
@Configuration
public class Config1 {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}

@Configuration
public class Config2 {
    @Bean
    public MyService myService() { // Duplicate!
        return new MyService();
    }
}
```

**Step 3: Check Component Scanning Overlap**
```java
// ❌ Problem: Overlapping component scans
@SpringBootApplication
@ComponentScan(basePackages = "com.company")
public class Application {
}

@Configuration
@ComponentScan(basePackages = "com.company.service") // Overlaps!
public class ServiceConfig {
}
```

**Step 4: Check for @Component + @Bean**
```java
// ❌ Problem: Both annotations
@Component
public class MyService {
}

@Configuration
public class Config {
    @Bean
    public MyService myService() { // Creates second instance
        return new MyService();
    }
}
```

**Solutions:**

**Solution 1: Use @ConditionalOnMissingBean**
```java
@Configuration
public class Config {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService() {
        return new MyService();
    }
}
```

**Solution 2: Use @Primary**
```java
@Configuration
public class Config {
    
    @Bean
    @Primary
    public MyService primaryService() {
        return new MyService();
    }
    
    @Bean
    public MyService secondaryService() {
        return new MyService();
    }
}
```

**Solution 3: Add Bean Creation Logger**
```java
@Component
public class BeanCreationLogger implements BeanPostProcessor {
    
    private Map<String, Integer> beanCounts = new ConcurrentHashMap<>();
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        beanCounts.merge(beanName, 1, Integer::sum);
        if (beanCounts.get(beanName) > 1) {
            System.err.println("Bean created multiple times: " + beanName);
        }
        return bean;
    }
}
```

---

## 29. What happens internally when Spring Boot starts?

**Spring Boot Startup Process:**

**Phase 1: SpringApplication Creation**
```java
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
}

// Internally:
// 1. Create SpringApplication instance
// 2. Determine web application type (SERVLET, REACTIVE, NONE)
// 3. Load ApplicationContextInitializers
// 4. Load ApplicationListeners
```

**Phase 2: Environment Preparation**
```
1. Create Environment (StandardEnvironment)
2. Configure Environment
   - Load application.properties
   - Load application-{profile}.properties
   - Process command line arguments
   - Process environment variables
3. Print banner
```

**Phase 3: ApplicationContext Creation**
```
1. Create ApplicationContext based on type
   - AnnotationConfigServletWebServerApplicationContext (Web)
   - AnnotationConfigApplicationContext (Non-web)
2. Prepare context
   - Set environment
   - Apply initializers
   - Post-process bean factory
```

**Phase 4: Bean Definition Loading**
```
1. Scan @ComponentScan packages
2. Process @Configuration classes
3. Load auto-configurations from spring.factories
4. Register bean definitions
```

**Phase 5: Bean Creation & Dependency Injection**
```
1. Instantiate beans
2. Populate properties
3. Call BeanPostProcessors
4. Initialize beans (@PostConstruct)
5. Resolve dependencies
```

**Phase 6: Auto-Configuration**
```
1. Load EnableAutoConfiguration from spring.factories
2. Apply @Conditional checks
3. Create auto-configured beans
```

**Phase 7: Embedded Server Start**
```
1. Create embedded server (Tomcat/Jetty/Undertow)
2. Configure server
3. Start server
4. Deploy application
```

**Phase 8: Application Ready**
```
1. Publish ApplicationReadyEvent
2. Run CommandLineRunner beans
3. Run ApplicationRunner beans
```

**Visualization:**
```java
@Component
public class StartupLogger implements ApplicationListener<ApplicationEvent> {
    
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            System.out.println("1. Application Starting");
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.out.println("2. Environment Prepared");
        } else if (event instanceof ApplicationContextInitializedEvent) {
            System.out.println("3. Context Initialized");
        } else if (event instanceof ApplicationPreparedEvent) {
            System.out.println("4. Application Prepared");
        } else if (event instanceof ApplicationStartedEvent) {
            System.out.println("5. Application Started");
        } else if (event instanceof ApplicationReadyEvent) {
            System.out.println("6. Application Ready");
        }
    }
}
```

---

## 30. How do you implement graceful shutdown?

**Solution: Configure Graceful Shutdown**

**Step 1: Enable Graceful Shutdown (Spring Boot 2.3+)**
```properties
server.shutdown=graceful
spring.lifecycle.timeout-per-shutdown-phase=30s
```

**Step 2: Implement PreDestroy Hooks**
```java
@Service
public class MyService {
    
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    
    @PreDestroy
    public void cleanup() {
        System.out.println("Shutting down executor...");
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
```

**Step 3: Implement DisposableBean**
```java
@Component
public class ConnectionManager implements DisposableBean {
    
    private Connection connection;
    
    @Override
    public void destroy() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection closed");
        }
    }
}
```

**Step 4: Handle In-Flight Requests**
```java
@Component
public class RequestTracker {
    
    private final AtomicInteger activeRequests = new AtomicInteger(0);
    
    public void incrementRequests() {
        activeRequests.incrementAndGet();
    }
    
    public void decrementRequests() {
        activeRequests.decrementAndGet();
    }
    
    @PreDestroy
    public void waitForCompletion() {
        System.out.println("Waiting for active requests to complete...");
        while (activeRequests.get() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("All requests completed");
    }
}
```

**Step 5: Shutdown Hook**
```java
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = 
            SpringApplication.run(Application.class, args);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook triggered");
            context.close();
        }));
    }
}
```

**Step 6: Health Check for Load Balancer**
```java
@Component
public class ShutdownHealthIndicator implements HealthIndicator {
    
    private volatile boolean shuttingDown = false;
    
    @Override
    public Health health() {
        if (shuttingDown) {
            return Health.down().withDetail("reason", "Shutting down").build();
        }
        return Health.up().build();
    }
    
    @PreDestroy
    public void markShuttingDown() {
        shuttingDown = true;
        // Wait for load balancer to detect
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

**Complete Example:**
```java
@Service
public class GracefulShutdownService {
    
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final AtomicInteger activeJobs = new AtomicInteger(0);
    
    public void submitJob(Runnable job) {
        activeJobs.incrementAndGet();
        executor.submit(() -> {
            try {
                job.run();
            } finally {
                activeJobs.decrementAndGet();
            }
        });
    }
    
    @PreDestroy
    public void shutdown() {
        System.out.println("Starting graceful shutdown...");
        
        // Stop accepting new jobs
        executor.shutdown();
        
        // Wait for active jobs
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Shutdown complete");
    }
}
```

# 🔹 REST API & Web - Solutions

## 31. You must implement API versioning. What are options?

**Option 1: URI Versioning (Most Common)**
```java
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    
    @GetMapping("/{id}")
    public UserV1 getUser(@PathVariable Long id) {
        return userService.getUserV1(id);
    }
}

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
    
    @GetMapping("/{id}")
    public UserV2 getUser(@PathVariable Long id) {
        return userService.getUserV2(id);
    }
}
```

**Option 2: Request Parameter Versioning**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping(params = "version=1")
    public UserV1 getUserV1(@RequestParam Long id) {
        return userService.getUserV1(id);
    }
    
    @GetMapping(params = "version=2")
    public UserV2 getUserV2(@RequestParam Long id) {
        return userService.getUserV2(id);
    }
}
// Usage: /api/users?id=1&version=2
```

**Option 3: Header Versioning**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping(value = "/{id}", headers = "X-API-VERSION=1")
    public UserV1 getUserV1(@PathVariable Long id) {
        return userService.getUserV1(id);
    }
    
    @GetMapping(value = "/{id}", headers = "X-API-VERSION=2")
    public UserV2 getUserV2(@PathVariable Long id) {
        return userService.getUserV2(id);
    }
}
// Header: X-API-VERSION: 2
```

**Option 4: Content Negotiation (Accept Header)**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping(value = "/{id}", produces = "application/vnd.company.v1+json")
    public UserV1 getUserV1(@PathVariable Long id) {
        return userService.getUserV1(id);
    }
    
    @GetMapping(value = "/{id}", produces = "application/vnd.company.v2+json")
    public UserV2 getUserV2(@PathVariable Long id) {
        return userService.getUserV2(id);
    }
}
// Header: Accept: application/vnd.company.v2+json
```

**Recommendation: URI Versioning**
- Most visible and explicit
- Easy to test and document
- Cache-friendly
- Simple for clients

---

## 32. Your API must support pagination and sorting for 10M+ records. How?

**Solution: Use Spring Data Pageable**

**Step 1: Controller with Pageable**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping
    public Page<User> getUsers(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) 
            Pageable pageable) {
        return userService.findAll(pageable);
    }
}
```

**Step 2: Service Layer**
```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
```

**Step 3: Repository**
```java
public interface UserRepository extends JpaRepository<User, Long> {
    // Inherits findAll(Pageable pageable)
}
```

**Step 4: Custom Response DTO**
```java
public class PageResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    
    public static <T> PageResponse<T> of(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.content = page.getContent();
        response.pageNumber = page.getNumber();
        response.pageSize = page.getSize();
        response.totalElements = page.getTotalElements();
        response.totalPages = page.getTotalPages();
        response.last = page.isLast();
        return response;
    }
}

@GetMapping
public PageResponse<User> getUsers(Pageable pageable) {
    Page<User> page = userService.findAll(pageable);
    return PageResponse.of(page);
}
```

**Usage Examples:**
```bash
# Basic pagination
GET /api/users?page=0&size=20

# With sorting (single field)
GET /api/users?page=0&size=20&sort=name,asc

# Multiple sort fields
GET /api/users?page=0&size=20&sort=name,asc&sort=createdDate,desc

# Custom parameters
GET /api/users?page=0&size=20&sort=salary,desc&department=IT
```

**Performance Optimization for Large Datasets:**
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Use index on frequently sorted columns
    @Query("SELECT u FROM User u WHERE u.department = :dept")
    Page<User> findByDepartment(@Param("dept") String department, Pageable pageable);
    
    // Avoid COUNT query for better performance
    Slice<User> findByStatus(String status, Pageable pageable);
}

// Using Slice instead of Page (no total count)
@GetMapping("/slice")
public Slice<User> getUsersSlice(Pageable pageable) {
    return userRepository.findByStatus("ACTIVE", pageable);
}
```

**Database Indexing:**
```sql
-- Create indexes on sorted columns
CREATE INDEX idx_user_name ON users(name);
CREATE INDEX idx_user_created_date ON users(created_date);
CREATE INDEX idx_user_dept_salary ON users(department, salary);
```

---

## 33. You need request validation globally. How implement?

**Solution: Use @Valid with Global Exception Handler**

**Step 1: Add Validation Dependency**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

**Step 2: Create DTO with Validation**
```java
public class UserRequest {
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phone;
    
    // Getters and setters
}
```

**Step 3: Controller with @Valid**
```java
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody UserRequest request) {
        User user = userService.update(id, request);
        return ResponseEntity.ok(user);
    }
}
```

**Step 4: Global Exception Handler**
```java
@RestControllerAdvice
public class ValidationExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ValidationErrorResponse response = new ValidationErrorResponse(
            "VALIDATION_FAILED",
            "Validation failed",
            errors
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        
        ValidationErrorResponse response = new ValidationErrorResponse(
            "VALIDATION_FAILED",
            "Validation failed",
            errors
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

public class ValidationErrorResponse {
    private String errorCode;
    private String message;
    private Map<String, String> fieldErrors;
    private long timestamp;
    
    public ValidationErrorResponse(String errorCode, String message, Map<String, String> fieldErrors) {
        this.errorCode = errorCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.timestamp = System.currentTimeMillis();
    }
    
    // Getters and setters
}
```

**Custom Validator:**
```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return true;
        return !userRepository.existsByEmail(email);
    }
}

// Usage
public class UserRequest {
    @UniqueEmail
    private String email;
}
```

---

## 34. How do you implement rate limiting?

**Solution: Multiple Approaches**

**Approach 1: Bucket4j (Token Bucket Algorithm)**
```xml
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>7.6.0</version>
</dependency>
```

```java
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        String key = getClientKey(request);
        Bucket bucket = resolveBucket(key);
        
        if (bucket.tryConsume(1)) {
            return true;
        }
        
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.getWriter().write("Rate limit exceeded");
        return false;
    }
    
    private Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, k -> createBucket());
    }
    
    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1)));
        return Bucket.builder()
            .addLimit(limit)
            .build();
    }
    
    private String getClientKey(HttpServletRequest request) {
        return request.getRemoteAddr(); // Or use API key, user ID, etc.
    }
}

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor);
    }
}
```

**Approach 2: Custom Annotation with AOP**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    int limit() default 100;
    int duration() default 60; // seconds
}

@Aspect
@Component
public class RateLimitAspect {
    
    private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> resetTimes = new ConcurrentHashMap<>();
    
    @Around("@annotation(rateLimit)")
    public Object checkRateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) 
            throws Throwable {
        
        HttpServletRequest request = 
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        
        String key = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();
        
        resetTimes.putIfAbsent(key, currentTime + rateLimit.duration() * 1000);
        
        if (currentTime > resetTimes.get(key)) {
            requestCounts.put(key, new AtomicInteger(0));
            resetTimes.put(key, currentTime + rateLimit.duration() * 1000);
        }
        
        AtomicInteger count = requestCounts.computeIfAbsent(key, k -> new AtomicInteger(0));
        
        if (count.incrementAndGet() > rateLimit.limit()) {
            throw new RateLimitExceededException("Rate limit exceeded");
        }
        
        return joinPoint.proceed();
    }
}

// Usage
@RestController
public class ApiController {
    
    @GetMapping("/api/data")
    @RateLimit(limit = 10, duration = 60)
    public String getData() {
        return "Data";
    }
}
```

**Approach 3: Redis-Based (Distributed)**
```java
@Component
public class RedisRateLimiter {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public boolean allowRequest(String key, int limit, int windowSeconds) {
        String redisKey = "rate_limit:" + key;
        Long currentCount = redisTemplate.opsForValue().increment(redisKey);
        
        if (currentCount == 1) {
            redisTemplate.expire(redisKey, windowSeconds, TimeUnit.SECONDS);
        }
        
        return currentCount <= limit;
    }
}

@Component
public class RedisRateLimitInterceptor implements HandlerInterceptor {
    
    @Autowired
    private RedisRateLimiter rateLimiter;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        String key = request.getRemoteAddr();
        
        if (!rateLimiter.allowRequest(key, 100, 60)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded");
            return false;
        }
        
        return true;
    }
}
```

---

## 35. Your API must support file upload & download. How?

**File Upload:**

```java
@RestController
@RequestMapping("/api/files")
public class FileController {
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file) {
        
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/files/download/")
            .path(fileName)
            .toUriString();
        
        FileUploadResponse response = new FileUploadResponse(
            fileName,
            fileDownloadUri,
            file.getContentType(),
            file.getSize()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/upload-multiple")
    public ResponseEntity<List<FileUploadResponse>> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files) {
        
        List<FileUploadResponse> responses = Arrays.stream(files)
            .map(file -> {
                String fileName = fileStorageService.storeFile(file);
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/download/")
                    .path(fileName)
                    .toUriString();
                return new FileUploadResponse(fileName, fileDownloadUri, 
                    file.getContentType(), file.getSize());
            })
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        
        String contentType = "application/octet-stream";
        
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }
}
```

**File Storage Service:**
```java
@Service
public class FileStorageService {
    
    private final Path fileStorageLocation;
    
    @Autowired
    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }
    
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid file path: " + fileName);
            }
            
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName, ex);
        }
    }
    
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found: " + fileName, ex);
        }
    }
}
```

**Configuration:**
```properties
# application.properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
file.upload-dir=./uploads
```

**Response DTO:**
```java
public class FileUploadResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    
    public FileUploadResponse(String fileName, String fileDownloadUri, 
                            String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
    
    // Getters and setters
}
```

---

## Summary (Questions 31-35)

31. **API Versioning**: URI versioning (recommended), header, parameter, content negotiation
32. **Pagination & Sorting**: Spring Data Pageable, use Slice for large datasets, database indexing
33. **Request Validation**: @Valid with @NotBlank, @Email, global exception handler
34. **Rate Limiting**: Bucket4j, custom AOP, Redis-based for distributed systems
35. **File Upload/Download**: MultipartFile, FileStorageService, proper security checks

All solutions are production-ready and follow Spring Boot best practices.

## 36. How do you handle CORS securely?

**Solution: Configure CORS Properly**

**Approach 1: Global CORS Configuration**
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("https://trusted-domain.com", "https://app.company.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Authorization", "Content-Type")
            .exposedHeaders("X-Total-Count")
            .allowCredentials(true)
            .maxAge(3600);
    }
}
```

**Approach 2: Controller-Level CORS**
```java
@RestController
@RequestMapping("/api/users")
@CrossOrigin(
    origins = "https://trusted-domain.com",
    methods = {RequestMethod.GET, RequestMethod.POST},
    allowedHeaders = {"Authorization", "Content-Type"},
    maxAge = 3600
)
public class UserController {
    
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }
}
```

**Approach 3: Method-Level CORS**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @CrossOrigin(origins = "https://trusted-domain.com")
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }
}
```

**Approach 4: Security Configuration with CORS**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://trusted-domain.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
```

**Security Best Practices:**
```java
@Configuration
public class SecureCorsConfig implements WebMvcConfigurer {
    
    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            // ❌ Never use "*" in production
            // .allowedOrigins("*")
            
            // ✅ Specify exact origins
            .allowedOrigins(allowedOrigins)
            
            // ✅ Limit methods
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            
            // ✅ Don't allow all headers
            .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
            
            // ✅ Set credentials carefully
            .allowCredentials(true)
            
            // ✅ Set reasonable max age
            .maxAge(3600);
    }
}
```

```properties
# application.properties
cors.allowed-origins=https://app.company.com,https://admin.company.com
```

---

## 37. How do you design idempotent APIs?

**Solution: Multiple Strategies**

**Strategy 1: Idempotency Key (Recommended for POST)**
```java
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    @Autowired
    private IdempotencyService idempotencyService;
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody PaymentRequest request) {
        
        // Check if request already processed
        Optional<Payment> existing = idempotencyService.getResult(idempotencyKey);
        if (existing.isPresent()) {
            return ResponseEntity.ok(existing.get());
        }
        
        // Process new request
        Payment payment = paymentService.processPayment(request);
        
        // Store result with idempotency key
        idempotencyService.storeResult(idempotencyKey, payment);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}
```

**Idempotency Service:**
```java
@Service
public class IdempotencyService {
    
    @Autowired
    private IdempotencyRepository repository;
    
    public Optional<Payment> getResult(String key) {
        return repository.findByKey(key)
            .map(record -> deserialize(record.getResult()));
    }
    
    public void storeResult(String key, Payment payment) {
        IdempotencyRecord record = new IdempotencyRecord();
        record.setKey(key);
        record.setResult(serialize(payment));
        record.setCreatedAt(LocalDateTime.now());
        repository.save(record);
    }
    
    private String serialize(Payment payment) {
        // Convert to JSON
        return new ObjectMapper().writeValueAsString(payment);
    }
    
    private Payment deserialize(String json) {
        // Convert from JSON
        return new ObjectMapper().readValue(json, Payment.class);
    }
}
```

**Strategy 2: Natural Idempotency (PUT/DELETE)**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // PUT is naturally idempotent
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request) {
        User user = userService.update(id, request);
        return ResponseEntity.ok(user);
    }
    
    // DELETE is naturally idempotent
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

**Strategy 3: Database Constraints**
```java
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"orderId", "userId"})
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String orderId;
    
    private Long userId;
    
    // Other fields
}

@Service
public class OrderService {
    
    @Transactional
    public Order createOrder(OrderRequest request) {
        try {
            Order order = new Order();
            order.setOrderId(request.getOrderId());
            order.setUserId(request.getUserId());
            return orderRepository.save(order);
        } catch (DataIntegrityViolationException e) {
            // Duplicate order, return existing
            return orderRepository.findByOrderId(request.getOrderId())
                .orElseThrow();
        }
    }
}
```

**Strategy 4: Optimistic Locking**
```java
@Entity
public class Account {
    @Id
    private Long id;
    
    private BigDecimal balance;
    
    @Version
    private Long version;
    
    // Getters and setters
}

@Service
public class AccountService {
    
    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow();
        
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account); // Version check ensures idempotency
    }
}
```

---

## 38. How would you log request/response without affecting performance?

**Solution: Async Logging with Filters**

**Approach 1: Custom Logging Filter**
```java
@Component
@Order(1)
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    
    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        
        long startTime = System.currentTimeMillis();
        
        filterChain.doFilter(requestWrapper, responseWrapper);
        
        long duration = System.currentTimeMillis() - startTime;
        
        // Log asynchronously
        logRequestResponse(requestWrapper, responseWrapper, duration);
        
        responseWrapper.copyBodyToResponse();
    }
    
    @Async
    private void logRequestResponse(ContentCachingRequestWrapper request, 
                                    ContentCachingResponseWrapper response, 
                                    long duration) {
        
        String requestBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
        String responseBody = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
        
        log.info("Request: {} {} | Duration: {}ms | Status: {} | Request Body: {} | Response Body: {}",
            request.getMethod(),
            request.getRequestURI(),
            duration,
            response.getStatus(),
            requestBody.length() > 1000 ? requestBody.substring(0, 1000) + "..." : requestBody,
            responseBody.length() > 1000 ? responseBody.substring(0, 1000) + "..." : responseBody
        );
    }
}
```

**Approach 2: Interceptor with Selective Logging**
```java
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                               Object handler, Exception ex) throws Exception {
        
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;
        
        // Only log slow requests
        if (duration > 1000) {
            log.warn("Slow request: {} {} took {}ms", 
                request.getMethod(), request.getRequestURI(), duration);
        }
    }
}
```

**Approach 3: AOP-Based Logging**
```java
@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        
        long startTime = System.currentTimeMillis();
        
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            
            // Async logging
            logAsync(methodName, args, result, duration, null);
            
            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            logAsync(methodName, args, null, duration, e);
            throw e;
        }
    }
    
    @Async
    private void logAsync(String methodName, Object[] args, Object result, 
                         long duration, Exception error) {
        if (error != null) {
            log.error("Method: {} | Duration: {}ms | Error: {}", 
                methodName, duration, error.getMessage());
        } else {
            log.info("Method: {} | Duration: {}ms", methodName, duration);
        }
    }
}
```

**Approach 4: Structured Logging with MDC**
```java
@Component
public class MDCLoggingFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        try {
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("method", request.getMethod());
            MDC.put("uri", request.getRequestURI());
            MDC.put("clientIp", request.getRemoteAddr());
            
            filterChain.doFilter(request, response);
            
        } finally {
            MDC.clear();
        }
    }
}
```

**Configuration for Async:**
```java
@Configuration
@EnableAsync
public class AsyncConfig {
    
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-log-");
        executor.initialize();
        return executor;
    }
}
```

---

## 39. How do you implement API documentation?

**Solution: SpringDoc OpenAPI (Swagger)**

**Step 1: Add Dependency**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

**Step 2: Configuration**
```java
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("User Management API")
                .version("1.0")
                .description("API for managing users")
                .contact(new Contact()
                    .name("API Support")
                    .email("support@company.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")))
            .servers(Arrays.asList(
                new Server().url("https://api.company.com").description("Production"),
                new Server().url("https://api-dev.company.com").description("Development")
            ));
    }
}
```

**Step 3: Document Controllers**
```java
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    
    @Operation(
        summary = "Get user by ID",
        description = "Returns a single user by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id) {
        User user = userService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(user);
    }
    
    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @PostMapping
    public ResponseEntity<User> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "User details",
                required = true,
                content = @Content(schema = @Schema(implementation = UserRequest.class))
            )
            @RequestBody @Valid UserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
```

**Step 4: Document Models**
```java
@Schema(description = "User details")
public class User {
    
    @Schema(description = "User ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "User name", example = "John Doe", required = true)
    private String name;
    
    @Schema(description = "Email address", example = "john@example.com", required = true)
    private String email;
    
    @Schema(description = "User age", example = "25", minimum = "18", maximum = "100")
    private Integer age;
    
    // Getters and setters
}
```

**Step 5: Security Documentation**
```java
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("API").version("1.0"))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
}
```

**Access Documentation:**
```
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
```

**Configuration Properties:**
```properties
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
```

---

## 40. How do you prevent API abuse in public systems?

**Solution: Multi-Layer Protection**

**Layer 1: Rate Limiting (Already covered in Q34)**
```java
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        String key = getClientKey(request);
        Bucket bucket = resolveBucket(key);
        
        if (!bucket.tryConsume(1)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            return false;
        }
        return true;
    }
    
    private Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, k -> {
            Bandwidth limit = Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1)));
            return Bucket.builder().addLimit(limit).build();
        });
    }
    
    private String getClientKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        return apiKey != null ? apiKey : request.getRemoteAddr();
    }
}
```

**Layer 2: API Key Authentication**
```java
@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {
    
    @Autowired
    private ApiKeyService apiKeyService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        String apiKey = request.getHeader("X-API-Key");
        
        if (apiKey == null || !apiKeyService.isValid(apiKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid API key");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}

@Service
public class ApiKeyService {
    
    @Autowired
    private ApiKeyRepository repository;
    
    public boolean isValid(String apiKey) {
        return repository.findByKey(apiKey)
            .map(key -> key.isActive() && !key.isExpired())
            .orElse(false);
    }
}
```

**Layer 3: Request Size Limits**
```properties
server.tomcat.max-http-post-size=1MB
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

**Layer 4: IP Whitelisting/Blacklisting**
```java
@Component
public class IpFilterInterceptor implements HandlerInterceptor {
    
    @Value("${security.ip.blacklist}")
    private Set<String> blacklistedIps;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler) throws Exception {
        
        String clientIp = getClientIp(request);
        
        if (blacklistedIps.contains(clientIp)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        
        return true;
    }
    
    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null) {
            return xForwardedFor.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
```

**Layer 5: Request Throttling by User**
```java
@Aspect
@Component
public class UserThrottlingAspect {
    
    private final Map<String, Queue<Long>> userRequests = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 1000;
    private static final long TIME_WINDOW = 3600000; // 1 hour
    
    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void checkThrottle() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Queue<Long> requests = userRequests.computeIfAbsent(userId, 
            k -> new ConcurrentLinkedQueue<>());
        
        long now = System.currentTimeMillis();
        
        // Remove old requests
        requests.removeIf(timestamp -> now - timestamp > TIME_WINDOW);
        
        if (requests.size() >= MAX_REQUESTS) {
            throw new TooManyRequestsException("User request limit exceeded");
        }
        
        requests.add(now);
    }
}
```

**Layer 6: CAPTCHA for Suspicious Activity**
```java
@Service
public class CaptchaService {
    
    @Value("${recaptcha.secret}")
    private String recaptchaSecret;
    
    public boolean verifyCaptcha(String captchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "https://www.google.com/recaptcha/api/siteverify";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", recaptchaSecret);
        params.add("response", captchaResponse);
        
        Map<String, Object> response = restTemplate.postForObject(url, params, Map.class);
        return Boolean.TRUE.equals(response.get("success"));
    }
}

@PostMapping("/api/public/register")
public ResponseEntity<User> register(
        @RequestBody UserRequest request,
        @RequestParam("captcha") String captchaResponse) {
    
    if (!captchaService.verifyCaptcha(captchaResponse)) {
        throw new BadRequestException("Invalid captcha");
    }
    
    User user = userService.register(request);
    return ResponseEntity.ok(user);
}
```

**Layer 7: Monitoring and Alerting**
```java
@Component
public class AbuseDetectionService {
    
    private static final Logger log = LoggerFactory.getLogger(AbuseDetectionService.class);
    
    @Scheduled(fixedRate = 60000) // Every minute
    public void detectAnomalies() {
        // Check for suspicious patterns
        List<String> suspiciousIps = findSuspiciousIps();
        
        if (!suspiciousIps.isEmpty()) {
            log.warn("Suspicious activity detected from IPs: {}", suspiciousIps);
            // Send alert, block IPs, etc.
        }
    }
    
    private List<String> findSuspiciousIps() {
        // Analyze request patterns
        // Return IPs with unusual activity
        return new ArrayList<>();
    }
}
```

# 🔹 Spring Data & Database - Solutions

## 41. You face N+1 query problem in Hibernate. How solve?

**Problem: N+1 queries occur when fetching a collection triggers additional queries for each element**

**Solution 1: Use @EntityGraph**
```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    @EntityGraph(attributePaths = {"employees"})
    List<Department> findAll();
    
    @EntityGraph(attributePaths = {"employees"})
    Optional<Department> findById(Long id);
}
```

**Solution 2: Use JOIN FETCH in JPQL**
```java
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees")
    List<Department> findAllWithEmployees();
    
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.id = :id")
    Optional<Department> findByIdWithEmployees(@Param("id") Long id);
}
```

**Solution 3: Use @Fetch(FetchMode.SUBSELECT)**
```java
@Entity
public class Department {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "department")
    @Fetch(FetchMode.SUBSELECT)
    private List<Employee> employees;
}
```

**Solution 4: Use @BatchSize**
```java
@Entity
public class Department {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "department")
    @BatchSize(size = 10)
    private List<Employee> employees;
}
```

**Solution 5: DTO Projection**
```java
public interface DepartmentWithEmployees {
    Long getId();
    String getName();
    List<EmployeeDto> getEmployees();
}

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    @Query("SELECT d.id as id, d.name as name, e as employees " +
           "FROM Department d LEFT JOIN d.employees e")
    List<DepartmentWithEmployees> findAllWithEmployeesProjection();
}
```

**Enable Query Logging to Detect N+1:**
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## 42. How do you implement soft delete?

**Solution: Use @SQLDelete and @Where annotations**

**Approach 1: Using Hibernate Annotations**
```java
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    
    @Column(name = "deleted")
    private boolean deleted = false;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    // Getters and setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Automatically filters deleted = false
    List<User> findAll();
    
    // Custom query to include deleted
    @Query("SELECT u FROM User u WHERE u.deleted = true")
    List<User> findDeleted();
    
    // Find including deleted
    @Query("SELECT u FROM User u")
    List<User> findAllIncludingDeleted();
}
```

**Approach 2: Custom Repository Implementation**
```java
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {
    void softDelete(ID id);
    void softDeleteAll(Iterable<? extends T> entities);
    List<T> findAllIncludingDeleted();
}

@NoRepositoryBean
public class SoftDeleteRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
        implements SoftDeleteRepository<T, ID> {
    
    private final EntityManager entityManager;
    
    public SoftDeleteRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public void softDelete(ID id) {
        T entity = findById(id).orElseThrow();
        if (entity instanceof SoftDeletable) {
            ((SoftDeletable) entity).setDeleted(true);
            ((SoftDeletable) entity).setDeletedAt(LocalDateTime.now());
            entityManager.merge(entity);
        }
    }
    
    @Override
    @Transactional
    public void softDeleteAll(Iterable<? extends T> entities) {
        entities.forEach(entity -> {
            if (entity instanceof SoftDeletable) {
                ((SoftDeletable) entity).setDeleted(true);
                ((SoftDeletable) entity).setDeletedAt(LocalDateTime.now());
                entityManager.merge(entity);
            }
        });
    }
    
    @Override
    public List<T> findAllIncludingDeleted() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        return entityManager.createQuery(query).getResultList();
    }
}

public interface SoftDeletable {
    void setDeleted(boolean deleted);
    void setDeletedAt(LocalDateTime deletedAt);
    boolean isDeleted();
}

@MappedSuperclass
public abstract class SoftDeletableEntity implements SoftDeletable {
    
    @Column(name = "deleted")
    private boolean deleted = false;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    @Override
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
    
    @Override
    public boolean isDeleted() {
        return deleted;
    }
    
    // Getters
}
```

**Approach 3: Service Layer Implementation**
```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void softDelete(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
    
    @Transactional
    public void restore(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setDeleted(false);
        user.setDeletedAt(null);
        userRepository.save(user);
    }
}
```

---

## 43. Your query is slow. How optimize?

**Optimization Steps:**

**Step 1: Enable Query Logging and Analysis**
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Enable statistics
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=DEBUG
```

**Step 2: Add Database Indexes**
```java
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_email", columnList = "email"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_created_date", columnList = "created_date"),
    @Index(name = "idx_dept_salary", columnList = "department_id, salary")
})
public class User {
    @Id
    private Long id;
    
    @Column(unique = true)
    private String email;
    
    private String status;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "department_id")
    private Long departmentId;
    
    private BigDecimal salary;
}
```

**Step 3: Use Projections Instead of Full Entities**
```java
// ❌ Slow - Fetches all columns
@Query("SELECT u FROM User u WHERE u.status = :status")
List<User> findByStatus(@Param("status") String status);

// ✅ Fast - Fetches only needed columns
public interface UserProjection {
    Long getId();
    String getName();
    String getEmail();
}

@Query("SELECT u.id as id, u.name as name, u.email as email " +
       "FROM User u WHERE u.status = :status")
List<UserProjection> findByStatusProjection(@Param("status") String status);
```

**Step 4: Use Pagination for Large Results**
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Page<User> findByStatus(String status, Pageable pageable);
    
    // Or use Slice to avoid count query
    Slice<User> findByDepartment(String department, Pageable pageable);
}
```

**Step 5: Optimize JOIN Queries**
```java
// ❌ Slow - Multiple queries
@Query("SELECT u FROM User u WHERE u.department.name = :deptName")
List<User> findByDepartmentName(@Param("deptName") String deptName);

// ✅ Fast - Single query with JOIN
@Query("SELECT u FROM User u JOIN u.department d WHERE d.name = :deptName")
List<User> findByDepartmentNameOptimized(@Param("deptName") String deptName);
```

**Step 6: Use Native Queries for Complex Operations**
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(value = "SELECT * FROM users u " +
                   "WHERE u.status = :status " +
                   "AND u.created_date > :date " +
                   "ORDER BY u.salary DESC " +
                   "LIMIT :limit",
           nativeQuery = true)
    List<User> findTopUsersByStatusNative(
        @Param("status") String status,
        @Param("date") LocalDateTime date,
        @Param("limit") int limit
    );
}
```

**Step 7: Enable Query Cache**
```properties
spring.jpa.properties.hibernate.cache.use_query_cache=true
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
```

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findByStatusCached(@Param("status") String status);
}
```

**Step 8: Batch Fetching**
```java
@Entity
public class Department {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "department")
    @BatchSize(size = 25)
    private List<User> users;
}
```

---

## 44. You need transaction management across multiple services. How?

**Solution: Multiple Approaches**

**Approach 1: Programmatic Transaction Management**
```java
@Service
public class OrderService {
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private InventoryService inventoryService;
    
    public void createOrder(OrderRequest request) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
            // Create order
            Order order = orderRepository.save(new Order(request));
            
            // Process payment
            paymentService.processPayment(order.getId(), request.getAmount());
            
            // Update inventory
            inventoryService.reduceStock(request.getProductId(), request.getQuantity());
            
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
```

**Approach 2: Declarative with @Transactional**
```java
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        paymentService.processPayment(order.getId(), request.getAmount());
        inventoryService.reduceStock(request.getProductId(), request.getQuantity());
    }
}

@Service
public class PaymentService {
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void processPayment(Long orderId, BigDecimal amount) {
        // Payment logic
    }
}

@Service
public class InventoryService {
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void reduceStock(Long productId, int quantity) {
        // Inventory logic
    }
}
```

**Approach 3: ChainedTransactionManager (Multiple Databases)**
```java
@Configuration
public class TransactionConfig {
    
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("orderTransactionManager") PlatformTransactionManager orderTxManager,
            @Qualifier("paymentTransactionManager") PlatformTransactionManager paymentTxManager) {
        
        return new ChainedTransactionManager(orderTxManager, paymentTxManager);
    }
    
    @Bean
    @Primary
    public DataSource orderDataSource() {
        // Order database configuration
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    public DataSource paymentDataSource() {
        // Payment database configuration
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    public PlatformTransactionManager orderTransactionManager(
            @Qualifier("orderDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
    public PlatformTransactionManager paymentTransactionManager(
            @Qualifier("paymentDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
```

**Approach 4: Saga Pattern (Microservices)**
```java
@Service
public class OrderSagaOrchestrator {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private InventoryService inventoryService;
    
    public void executeOrderSaga(OrderRequest request) {
        Long orderId = null;
        Long paymentId = null;
        
        try {
            // Step 1: Create order
            orderId = orderService.createOrder(request);
            
            // Step 2: Process payment
            paymentId = paymentService.processPayment(orderId, request.getAmount());
            
            // Step 3: Reserve inventory
            inventoryService.reserveStock(request.getProductId(), request.getQuantity());
            
        } catch (Exception e) {
            // Compensating transactions
            if (paymentId != null) {
                paymentService.refundPayment(paymentId);
            }
            if (orderId != null) {
                orderService.cancelOrder(orderId);
            }
            throw new SagaExecutionException("Order saga failed", e);
        }
    }
}
```

**Transaction Propagation Types:**
```java
@Service
public class TransactionExamples {
    
    // Joins existing transaction or creates new one
    @Transactional(propagation = Propagation.REQUIRED)
    public void required() { }
    
    // Always creates new transaction, suspends existing
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew() { }
    
    // Executes within existing transaction, non-transactional otherwise
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports() { }
    
    // Must execute within existing transaction
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory() { }
    
    // Executes non-transactionally, suspends existing transaction
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() { }
    
    // Executes non-transactionally, throws exception if transaction exists
    @Transactional(propagation = Propagation.NEVER)
    public void never() { }
    
    // Creates new transaction if one exists, otherwise executes non-transactionally
    @Transactional(propagation = Propagation.NESTED)
    public void nested() { }
}
```

---

## 45. How do you implement optimistic vs pessimistic locking?

**Optimistic Locking: Uses version field**

```java
@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String accountNumber;
    private BigDecimal balance;
    
    @Version
    private Long version;
    
    // Getters and setters
}

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }
        
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account); // Version check happens here
    }
    
    // Handle optimistic lock exception
    @Transactional
    public void withdrawWithRetry(Long accountId, BigDecimal amount) {
        int maxRetries = 3;
        int attempt = 0;
        
        while (attempt < maxRetries) {
            try {
                withdraw(accountId, amount);
                return;
            } catch (OptimisticLockException e) {
                attempt++;
                if (attempt >= maxRetries) {
                    throw new RuntimeException("Failed after " + maxRetries + " attempts", e);
                }
                // Wait before retry
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
```

**Pessimistic Locking: Uses database locks**

```java
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    // Pessimistic Write Lock (exclusive lock)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdWithWriteLock(@Param("id") Long id);
    
    // Pessimistic Read Lock (shared lock)
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdWithReadLock(@Param("id") Long id);
    
    // Pessimistic Force Increment (lock and increment version)
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdWithForceIncrementLock(@Param("id") Long id);
}

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Transactional
    public void withdrawWithPessimisticLock(Long accountId, BigDecimal amount) {
        // Acquires exclusive lock on the row
        Account account = accountRepository.findByIdWithWriteLock(accountId)
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }
        
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        // Lock released when transaction commits
    }
}
```

**Comparison:**

```java
@Service
public class LockingComparisonService {
    
    // ✅ Optimistic Locking
    // - Better for low contention
    // - Better performance (no database locks)
    // - Fails at commit time
    // - Requires retry logic
    @Transactional
    public void optimisticExample(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        // Do work...
        accountRepository.save(account); // May throw OptimisticLockException
    }
    
    // ✅ Pessimistic Locking
    // - Better for high contention
    // - Prevents conflicts upfront
    // - May cause deadlocks
    // - Lower throughput
    @Transactional
    public void pessimisticExample(Long id) {
        Account account = accountRepository.findByIdWithWriteLock(id).orElseThrow();
        // Do work... (row is locked)
        accountRepository.save(account); // No version conflict possible
    }
}
```

**Custom Lock Timeout:**
```java
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdWithLockTimeout(@Param("id") Long id);
}
```

---

## Summary (Questions 41-45)

41. **N+1 Query Problem**: @EntityGraph, JOIN FETCH, @BatchSize, DTO projections
42. **Soft Delete**: @SQLDelete + @Where, custom repository, service layer implementation
43. **Query Optimization**: Indexes, projections, pagination, JOIN optimization, query cache
44. **Transaction Management**: @Transactional, programmatic, ChainedTransactionManager, Saga pattern
45. **Locking**: Optimistic (@Version), Pessimistic (@Lock), retry logic, lock timeouts

All solutions are production-ready and follow Spring Data best practices.

## 46. How do you handle large batch inserts efficiently?

**Solution: Multiple Optimization Techniques**

**Approach 1: JDBC Batch Insert**
```java
@Repository
public class UserBatchRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void batchInsert(List<User> users) {
        String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
        
        jdbcTemplate.batchUpdate(sql, users, users.size(),
            (PreparedStatement ps, User user) -> {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setInt(3, user.getAge());
            });
    }
}
```

**Approach 2: JPA with Batch Configuration**
```properties
# application.properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
spring.jpa.properties.hibernate.batch_versioned_data=true
```

```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Transactional
    public void batchInsert(List<User> users) {
        int batchSize = 50;
        
        for (int i = 0; i < users.size(); i++) {
            entityManager.persist(users.get(i));
            
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        
        entityManager.flush();
        entityManager.clear();
    }
}
```

**Approach 3: saveAll with Optimization**
```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void bulkInsert(List<User> users) {
        int batchSize = 1000;
        
        for (int i = 0; i < users.size(); i += batchSize) {
            int end = Math.min(i + batchSize, users.size());
            List<User> batch = users.subList(i, end);
            userRepository.saveAll(batch);
            userRepository.flush();
        }
    }
}
```

**Approach 4: Native Batch Insert**
```java
@Repository
public class UserBatchRepository {
    
    @Autowired
    private EntityManager entityManager;
    
    @Transactional
    public void nativeBatchInsert(List<User> users) {
        StringBuilder sql = new StringBuilder(
            "INSERT INTO users (name, email, age) VALUES ");
        
        for (int i = 0; i < users.size(); i++) {
            sql.append("(?, ?, ?)");
            if (i < users.size() - 1) {
                sql.append(", ");
            }
        }
        
        Query query = entityManager.createNativeQuery(sql.toString());
        
        int paramIndex = 1;
        for (User user : users) {
            query.setParameter(paramIndex++, user.getName());
            query.setParameter(paramIndex++, user.getEmail());
            query.setParameter(paramIndex++, user.getAge());
        }
        
        query.executeUpdate();
    }
}
```

**Approach 5: Disable ID Generation for Batch**
```java
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", 
                       allocationSize = 50)
    private Long id;
    
    private String name;
    private String email;
}
```

**Performance Comparison:**
```java
@Service
public class BatchInsertPerformanceTest {
    
    public void compareApproaches(List<User> users) {
        // Approach 1: One by one (Slowest)
        long start = System.currentTimeMillis();
        users.forEach(user -> userRepository.save(user));
        System.out.println("One by one: " + (System.currentTimeMillis() - start) + "ms");
        
        // Approach 2: saveAll without flush (Medium)
        start = System.currentTimeMillis();
        userRepository.saveAll(users);
        System.out.println("saveAll: " + (System.currentTimeMillis() - start) + "ms");
        
        // Approach 3: JDBC batch (Fastest)
        start = System.currentTimeMillis();
        jdbcBatchInsert(users);
        System.out.println("JDBC batch: " + (System.currentTimeMillis() - start) + "ms");
    }
}
```

---

## 47. You need audit fields (createdBy, updatedAt). How implement?

**Solution: Use JPA Auditing**

**Step 1: Enable JPA Auditing**
```java
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
```

**Step 2: Implement AuditorAware**
```java
public class AuditorAwareImpl implements AuditorAware<String> {
    
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        
        return Optional.of(authentication.getName());
    }
}
```

**Step 3: Create Base Auditable Entity**
```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
    
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    // Getters and setters
}
```

**Step 4: Extend Auditable in Entities**
```java
@Entity
@Table(name = "users")
public class User extends Auditable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    
    // Getters and setters
}
```

**Alternative: Custom Implementation**
```java
@Aspect
@Component
public class AuditAspect {
    
    @Before("execution(* com.company.repository.*.save(..))")
    public void beforeSave(JoinPoint joinPoint) {
        Object entity = joinPoint.getArgs()[0];
        
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            String currentUser = getCurrentUser();
            LocalDateTime now = LocalDateTime.now();
            
            if (auditable.getCreatedBy() == null) {
                auditable.setCreatedBy(currentUser);
                auditable.setCreatedDate(now);
            }
            
            auditable.setUpdatedBy(currentUser);
            auditable.setUpdatedDate(now);
        }
    }
    
    private String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : "system";
    }
}
```

**With Additional Audit Fields:**
```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class FullAuditable {
    
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
    
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    
    @Column(name = "created_ip", updatable = false)
    private String createdIp;
    
    @Column(name = "updated_ip")
    private String updatedIp;
    
    @Version
    private Long version;
    
    // Getters and setters
}
```

---

## 48. How would you design database migration strategy?

**Solution: Use Flyway or Liquibase**

**Approach 1: Flyway (Recommended)**

**Step 1: Add Dependency**
```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

**Step 2: Configuration**
```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration
spring.flyway.sql-migration-prefix=V
spring.flyway.sql-migration-separator=__
spring.flyway.sql-migration-suffixes=.sql
```

**Step 3: Create Migration Files**
```
src/main/resources/db/migration/
├── V1__create_users_table.sql
├── V2__add_email_to_users.sql
├── V3__create_orders_table.sql
└── V4__add_index_to_users_email.sql
```

**V1__create_users_table.sql:**
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**V2__add_email_to_users.sql:**
```sql
ALTER TABLE users ADD COLUMN email VARCHAR(255);
CREATE UNIQUE INDEX idx_users_email ON users(email);
```

**Step 4: Java-Based Migrations**
```java
@Component
public class V5__MigrateUserData implements JavaMigration {
    
    @Override
    public void migrate(Context context) throws Exception {
        try (Statement statement = context.getConnection().createStatement()) {
            statement.execute(
                "UPDATE users SET email = CONCAT(LOWER(name), '@example.com') " +
                "WHERE email IS NULL"
            );
        }
    }
}
```

**Approach 2: Liquibase**

**Step 1: Add Dependency**
```xml
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>
```

**Step 2: Configuration**
```properties
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
```

**Step 3: Create Changelog**
```yaml
# db/changelog/db.changelog-master.yaml
databaseChangeLog:
  - include:
      file: db/changelog/changes/v1-create-users-table.yaml
  - include:
      file: db/changelog/changes/v2-add-email-column.yaml
```

**v1-create-users-table.yaml:**
```yaml
databaseChangeLog:
  - changeSet:
      id: 1
      author: developer
      changes:
        - createTable:
            tableName: users
            columns:
              - column:
                  name: id
                  type: BIGINT
                  autoIncrement: true
                  constraints:
                    primaryKey: true
              - column:
                  name: name
                  type: VARCHAR(100)
                  constraints:
                    nullable: false
              - column:
                  name: age
                  type: INT
```

**Best Practices:**

```java
@Configuration
public class MigrationConfig {
    
    // Run migrations on startup
    @Bean
    public FlywayMigrationStrategy cleanMigrateStrategy() {
        return flyway -> {
            // Only in dev environment
            if (isDevEnvironment()) {
                flyway.clean();
            }
            flyway.migrate();
        };
    }
    
    private boolean isDevEnvironment() {
        return Arrays.asList(environment.getActiveProfiles()).contains("dev");
    }
}
```

**Migration Naming Convention:**
```
V{version}__{description}.sql

Examples:
V1__initial_schema.sql
V2__add_user_email.sql
V2.1__add_user_phone.sql
V3__create_orders_table.sql
```

**Rollback Strategy:**
```sql
-- V5__add_status_column.sql
ALTER TABLE users ADD COLUMN status VARCHAR(20) DEFAULT 'ACTIVE';

-- U5__rollback_status_column.sql (for rollback)
ALTER TABLE users DROP COLUMN status;
```

---

## 49. How do you implement read/write DB splitting?

**Solution: Multiple DataSource Configuration**

**Approach 1: Spring AbstractRoutingDataSource**

**Step 1: Create Routing DataSource**
```java
public class RoutingDataSource extends AbstractRoutingDataSource {
    
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}

public class DataSourceContextHolder {
    
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();
    
    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }
    
    public static DataSourceType getDataSourceType() {
        return contextHolder.get();
    }
    
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}

public enum DataSourceType {
    WRITE, READ
}
```

**Step 2: Configure Multiple DataSources**
```java
@Configuration
public class DataSourceConfig {
    
    @Bean
    @ConfigurationProperties("spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties("spring.datasource.read")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @Primary
    public DataSource routingDataSource(
            @Qualifier("writeDataSource") DataSource writeDataSource,
            @Qualifier("readDataSource") DataSource readDataSource) {
        
        RoutingDataSource routingDataSource = new RoutingDataSource();
        
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ, readDataSource);
        
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        
        return routingDataSource;
    }
}
```

**Step 3: Configuration Properties**
```properties
# Write DataSource (Master)
spring.datasource.write.jdbc-url=jdbc:mysql://master-db:3306/mydb
spring.datasource.write.username=write_user
spring.datasource.write.password=write_pass
spring.datasource.write.driver-class-name=com.mysql.cj.jdbc.Driver

# Read DataSource (Replica)
spring.datasource.read.jdbc-url=jdbc:mysql://replica-db:3306/mydb
spring.datasource.read.username=read_user
spring.datasource.read.password=read_pass
spring.datasource.read.driver-class-name=com.mysql.cj.jdbc.Driver
```

**Step 4: Create Routing Aspect**
```java
@Aspect
@Component
@Order(1)
public class DataSourceRoutingAspect {
    
    @Before("@annotation(readOnly)")
    public void setReadDataSource(ReadOnly readOnly) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.READ);
    }
    
    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void setWriteDataSource() {
        if (DataSourceContextHolder.getDataSourceType() == null) {
            DataSourceContextHolder.setDataSourceType(DataSourceType.WRITE);
        }
    }
    
    @After("@annotation(readOnly) || @annotation(org.springframework.transaction.annotation.Transactional)")
    public void clearDataSource() {
        DataSourceContextHolder.clearDataSourceType();
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {
}
```

**Step 5: Usage in Service**
```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User createUser(User user) {
        // Uses WRITE datasource
        return userRepository.save(user);
    }
    
    @ReadOnly
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        // Uses READ datasource
        return userRepository.findById(id).orElseThrow();
    }
    
    @ReadOnly
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        // Uses READ datasource
        return userRepository.findAll();
    }
}
```

**Approach 2: Load Balancing Multiple Read Replicas**
```java
public class LoadBalancedRoutingDataSource extends AbstractRoutingDataSource {
    
    private final List<DataSourceType> readDataSources = Arrays.asList(
        DataSourceType.READ_1,
        DataSourceType.READ_2,
        DataSourceType.READ_3
    );
    
    private final AtomicInteger counter = new AtomicInteger(0);
    
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType type = DataSourceContextHolder.getDataSourceType();
        
        if (type == DataSourceType.READ) {
            // Round-robin load balancing
            int index = counter.getAndIncrement() % readDataSources.size();
            return readDataSources.get(index);
        }
        
        return type;
    }
}
```

---

## 50. How do you handle distributed transactions in microservices?

**Solution: Multiple Patterns**

**Pattern 1: Saga Pattern (Choreography)**
```java
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = new Order(request);
        order.setStatus(OrderStatus.PENDING);
        order = orderRepository.save(order);
        
        // Publish event
        eventPublisher.publishEvent(new OrderCreatedEvent(order.getId()));
        
        return order;
    }
    
    @TransactionalEventListener
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        
        // Publish next event
        eventPublisher.publishEvent(new OrderPaidEvent(order.getId()));
    }
    
    @TransactionalEventListener
    public void handlePaymentFailed(PaymentFailedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @EventListener
    @Transactional
    public void handleOrderCreated(OrderCreatedEvent event) {
        try {
            Payment payment = processPayment(event.getOrderId());
            paymentRepository.save(payment);
            
            eventPublisher.publishEvent(new PaymentCompletedEvent(event.getOrderId()));
        } catch (Exception e) {
            eventPublisher.publishEvent(new PaymentFailedEvent(event.getOrderId()));
        }
    }
}
```

**Pattern 2: Saga Pattern (Orchestration)**
```java
@Service
public class OrderSagaOrchestrator {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private ShippingService shippingService;
    
    public void executeOrderSaga(OrderRequest request) {
        SagaState state = new SagaState();
        
        try {
            // Step 1: Create Order
            Long orderId = orderService.createOrder(request);
            state.setOrderId(orderId);
            
            // Step 2: Process Payment
            Long paymentId = paymentService.processPayment(orderId, request.getAmount());
            state.setPaymentId(paymentId);
            
            // Step 3: Reserve Inventory
            Long reservationId = inventoryService.reserveStock(
                request.getProductId(), request.getQuantity());
            state.setReservationId(reservationId);
            
            // Step 4: Create Shipment
            Long shipmentId = shippingService.createShipment(orderId);
            state.setShipmentId(shipmentId);
            
            // Success - commit all
            orderService.confirmOrder(orderId);
            
        } catch (Exception e) {
            // Compensate in reverse order
            compensate(state);
            throw new SagaExecutionException("Order saga failed", e);
        }
    }
    
    private void compensate(SagaState state) {
        if (state.getShipmentId() != null) {
            shippingService.cancelShipment(state.getShipmentId());
        }
        if (state.getReservationId() != null) {
            inventoryService.releaseStock(state.getReservationId());
        }
        if (state.getPaymentId() != null) {
            paymentService.refundPayment(state.getPaymentId());
        }
        if (state.getOrderId() != null) {
            orderService.cancelOrder(state.getOrderId());
        }
    }
}
```

**Pattern 3: Outbox Pattern**
```java
@Entity
@Table(name = "outbox_events")
public class OutboxEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String aggregateType;
    private String aggregateId;
    private String eventType;
    
    @Column(columnDefinition = "TEXT")
    private String payload;
    
    private LocalDateTime createdAt;
    private boolean processed;
    
    // Getters and setters
}

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OutboxEventRepository outboxRepository;
    
    @Transactional
    public Order createOrder(OrderRequest request) {
        // Save order
        Order order = orderRepository.save(new Order(request));
        
        // Save event to outbox (same transaction)
        OutboxEvent event = new OutboxEvent();
        event.setAggregateType("Order");
        event.setAggregateId(order.getId().toString());
        event.setEventType("OrderCreated");
        event.setPayload(toJson(order));
        event.setCreatedAt(LocalDateTime.now());
        event.setProcessed(false);
        
        outboxRepository.save(event);
        
        return order;
    }
}

@Component
public class OutboxEventPublisher {
    
    @Autowired
    private OutboxEventRepository outboxRepository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishEvents() {
        List<OutboxEvent> events = outboxRepository.findByProcessedFalse();
        
        for (OutboxEvent event : events) {
            try {
                kafkaTemplate.send(event.getEventType(), event.getPayload());
                event.setProcessed(true);
                outboxRepository.save(event);
            } catch (Exception e) {
                // Log and retry later
            }
        }
    }
}
```

**Pattern 4: Two-Phase Commit (2PC) - Avoid in Microservices**
```java
// ❌ Not recommended for microservices
// - Blocking protocol
// - Single point of failure
// - Poor performance
// - Doesn't scale

@Configuration
public class JtaConfig {
    
    @Bean
    public JtaTransactionManager transactionManager() {
        // Atomikos or Bitronix
        return new JtaTransactionManager();
    }
}
```

**Best Practice: Event-Driven with Idempotency**
```java
@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Transactional
    public void processPayment(OrderCreatedEvent event) {
        // Check if already processed (idempotency)
        if (paymentRepository.existsByOrderId(event.getOrderId())) {
            return; // Already processed
        }
        
        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setAmount(event.getAmount());
        payment.setStatus(PaymentStatus.COMPLETED);
        
        paymentRepository.save(payment);
        
        // Publish event
        publishEvent(new PaymentCompletedEvent(event.getOrderId()));
    }
}
```