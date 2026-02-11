# Java & Spring Boot Interview Questions - Answers (Questions 1-10)

## Core Java Fundamentals

### 1. You have a multi-threaded application where multiple threads access shared data. How do you ensure thread safety?

Use synchronized blocks or methods to lock critical sections. For simple cases, use atomic classes like AtomicInteger. For collections, use ConcurrentHashMap or Collections.synchronizedList(). Better approach is using locks from java.util.concurrent like ReentrantLock for more control. Or avoid shared state altogether - make objects immutable or use ThreadLocal for thread-specific data.

```java
// Synchronized method
public synchronized void updateCounter() {
    counter++;
}

// Using AtomicInteger
private AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();

// Using ReentrantLock
private final ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

### 2. Your application is experiencing memory leaks. How do you identify and fix them?

Use profiling tools like VisualVM or JProfiler to monitor heap usage. Look for objects that keep growing and never get garbage collected. Common causes: static collections holding references, unclosed resources like streams or connections, listeners not removed, ThreadLocal not cleaned up. Fix by removing references when done, using try-with-resources for auto-closing, clearing collections, and calling remove() on ThreadLocal.

```java
// Bad - memory leak
static List<Object> cache = new ArrayList<>();

// Good - use WeakHashMap or clear when done
Map<Key, Value> cache = new WeakHashMap<>();

// Always close resources
try (FileInputStream fis = new FileInputStream(file)) {
    // use stream
}
```

### 3. You need to process a large file (10GB+) without loading it entirely into memory. How do you approach this?

Read the file line by line or in chunks using BufferedReader or FileChannel. Process each line immediately and discard it. For structured data, use streaming parsers like Jackson's streaming API for JSON. Never use Files.readAllLines() for large files.

```java
try (BufferedReader reader = new BufferedReader(new FileReader("large.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        processLine(line);
    }
}

// For binary files, read in chunks
try (FileInputStream fis = new FileInputStream("large.bin")) {
    byte[] buffer = new byte[8192];
    int bytesRead;
    while ((bytesRead = fis.read(buffer)) != -1) {
        processChunk(buffer, bytesRead);
    }
}
```

### 4. How would you implement a custom exception hierarchy for your application?

Create a base exception class extending RuntimeException for unchecked exceptions or Exception for checked. Then create specific exceptions for different scenarios - validation errors, business logic errors, external service failures. Include error codes and meaningful messages.

```java
public class ApplicationException extends RuntimeException {
    private final String errorCode;
    
    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super("VALIDATION_ERROR", message);
    }
}

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String resource) {
        super("NOT_FOUND", resource + " not found");
    }
}
```

### 5. You need to compare two objects for equality. When would you override equals() and hashCode()?

Override both when you need to compare objects by their content, not reference. Always override both together - if two objects are equal, they must have same hashCode. Needed when using objects as HashMap keys or in HashSet. Use all significant fields in equals comparison and hashCode calculation.

```java
public class Person {
    private String name;
    private int age;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```

### 6. Your application needs to handle millions of objects. How do you optimize memory usage?

Use primitive types instead of wrapper classes where possible. Use lazy initialization - create objects only when needed. Use object pooling for frequently created objects. Consider using flyweight pattern for shared data. Use weak references for caches. Process data in batches instead of loading everything. Use efficient data structures - ArrayList over LinkedList for most cases.

```java
// Bad - uses more memory
List<Integer> numbers = new ArrayList<>();

// Better - use primitive array if possible
int[] numbers = new int[size];

// Lazy initialization
private ExpensiveObject obj;
public ExpensiveObject getObj() {
    if (obj == null) {
        obj = new ExpensiveObject();
    }
    return obj;
}

// Use WeakHashMap for caches
Map<Key, Value> cache = new WeakHashMap<>();
```

### 7. You need to implement a caching mechanism without using external libraries. How do you do it?

Use a ConcurrentHashMap to store cached data with keys. Add expiration by storing timestamp with each entry and checking on retrieval. Implement LRU eviction using LinkedHashMap with access order. For thread safety, use ConcurrentHashMap or synchronize access.

```java
public class SimpleCache<K, V> {
    private final Map<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
    private final long ttlMillis;
    
    public SimpleCache(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }
    
    public void put(K key, V value) {
        cache.put(key, new CacheEntry<>(value, System.currentTimeMillis()));
    }
    
    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null) return null;
        
        if (System.currentTimeMillis() - entry.timestamp > ttlMillis) {
            cache.remove(key);
            return null;
        }
        return entry.value;
    }
    
    private static class CacheEntry<V> {
        final V value;
        final long timestamp;
        
        CacheEntry(V value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
```

### 8. How would you handle circular references in object serialization?

Use transient keyword to exclude circular reference fields from serialization. Or implement custom serialization with writeObject/readObject methods. Use object identity tracking to detect cycles. Better approach is to redesign to avoid circular references - use IDs instead of direct references.

```java
public class Employee implements Serializable {
    private String name;
    private transient Department department; // Avoid circular reference
    private Long departmentId; // Use ID instead
}

// Custom serialization
private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    // Custom logic to handle circular references
}

// Or use JSON libraries with cycle detection
ObjectMapper mapper = new ObjectMapper();
mapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
```

### 9. You need to execute tasks asynchronously and get results. How do you implement this?

Use CompletableFuture for async execution with results. Or use ExecutorService with Callable and Future. CompletableFuture is better as it supports chaining, combining multiple futures, and exception handling.

```java
// Using CompletableFuture
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return performLongTask();
});

future.thenAccept(result -> System.out.println(result));

// Get result with timeout
String result = future.get(5, TimeUnit.SECONDS);

// Using ExecutorService
ExecutorService executor = Executors.newFixedThreadPool(10);
Future<String> future = executor.submit(() -> {
    return performLongTask();
});
String result = future.get();
executor.shutdown();

// Combine multiple futures
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Task1");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Task2");
CompletableFuture<Void> combined = CompletableFuture.allOf(f1, f2);
```

### 10. Your application has performance issues with String concatenation in loops. How do you fix it?

Use StringBuilder instead of String concatenation with + operator. String is immutable, so each concatenation creates a new object. StringBuilder is mutable and efficient for building strings in loops.

```java
// Bad - creates many String objects
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i + ",";
}

// Good - uses StringBuilder
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i).append(",");
}
String result = sb.toString();

// For simple cases, String.join() is cleaner
List<String> items = Arrays.asList("a", "b", "c");
String result = String.join(",", items);
```

### 11. How would you implement a thread-safe singleton? What approaches would you use?

Use enum - simplest and safest way. Or use double-checked locking with volatile. Or use static inner class for lazy initialization. Enum is best because it's thread-safe by default and prevents multiple instances even with reflection or serialization.

```java
// Best approach - Enum singleton
public enum Singleton {
    INSTANCE;
    
    public void doSomething() {
        // business logic
    }
}

// Double-checked locking
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

// Static inner class - lazy and thread-safe
public class Singleton {
    private Singleton() {}
    
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

### 12. How would you use CompletableFuture for async operations?

Use supplyAsync() to run tasks asynchronously and return results. Chain operations with thenApply(), thenAccept(), or thenCompose(). Combine multiple futures with allOf() or anyOf(). Handle exceptions with exceptionally() or handle(). It's non-blocking and supports functional composition.

```java
// Basic async execution
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return fetchDataFromAPI();
});

// Chain operations
future.thenApply(data -> processData(data))
      .thenAccept(result -> saveToDatabase(result))
      .exceptionally(ex -> {
          log.error("Error", ex);
          return null;
      });

// Combine multiple futures
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Data1");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Data2");

CompletableFuture.allOf(f1, f2).thenRun(() -> {
    String result1 = f1.join();
    String result2 = f2.join();
    System.out.println(result1 + result2);
});

// With timeout
String result = future.get(5, TimeUnit.SECONDS);

// Compose dependent futures
CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> getUser(id));
CompletableFuture<Order> orderFuture = userFuture.thenCompose(user -> 
    CompletableFuture.supplyAsync(() -> getOrders(user.getId()))
);
```

### 13. You need to handle race conditions in your application. What synchronization mechanisms would you use?

Use synchronized blocks for simple cases. Use ReentrantLock for more control like tryLock() with timeout. Use atomic classes like AtomicInteger for counters. Use concurrent collections like ConcurrentHashMap. For read-heavy scenarios, use ReadWriteLock. Best is to design without shared mutable state.

```java
// Synchronized block
private final Object lock = new Object();
synchronized(lock) {
    // critical section
    balance += amount;
}

// ReentrantLock with timeout
private final ReentrantLock lock = new ReentrantLock();
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try {
        // critical section
    } finally {
        lock.unlock();
    }
}

// Atomic operations
private AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
counter.compareAndSet(expected, newValue);

// ReadWriteLock for read-heavy operations
private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

public String read() {
    rwLock.readLock().lock();
    try {
        return data;
    } finally {
        rwLock.readLock().unlock();
    }
}

public void write(String value) {
    rwLock.writeLock().lock();
    try {
        data = value;
    } finally {
        rwLock.writeLock().unlock();
    }
}
```

### 14. How would you implement producer-consumer pattern?

Use BlockingQueue - it handles all synchronization automatically. Producer puts items, consumer takes items. Queue blocks when full or empty. Use LinkedBlockingQueue for unbounded queue or ArrayBlockingQueue for bounded. No need for manual wait/notify.

```java
// Using BlockingQueue
public class ProducerConsumer {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
    
    // Producer
    public void produce() {
        try {
            queue.put("item"); // blocks if queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Consumer
    public void consume() {
        try {
            String item = queue.take(); // blocks if queue is empty
            process(item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Complete example with threads
public class Example {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        
        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Integer item = queue.take();
                    System.out.println("Consumed: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        producer.start();
        consumer.start();
    }
}
```

### 15. How would you handle deadlock situations?

Prevent deadlocks by acquiring locks in consistent order. Use tryLock() with timeout instead of blocking locks. Avoid nested locks when possible. Use lock timeout to detect deadlocks. Monitor with thread dumps to identify deadlocked threads. Design to minimize lock holding time.

```java
// Bad - can cause deadlock
synchronized(lock1) {
    synchronized(lock2) {
        // work
    }
}

// Good - consistent lock ordering
private void transfer(Account from, Account to, int amount) {
    Account first = from.getId() < to.getId() ? from : to;
    Account second = from.getId() < to.getId() ? to : from;
    
    synchronized(first) {
        synchronized(second) {
            from.debit(amount);
            to.credit(amount);
        }
    }
}

// Using tryLock with timeout
ReentrantLock lock1 = new ReentrantLock();
ReentrantLock lock2 = new ReentrantLock();

public void safeOperation() {
    boolean lock1Acquired = false;
    boolean lock2Acquired = false;
    
    try {
        lock1Acquired = lock1.tryLock(1, TimeUnit.SECONDS);
        if (!lock1Acquired) {
            throw new RuntimeException("Could not acquire lock1");
        }
        
        lock2Acquired = lock2.tryLock(1, TimeUnit.SECONDS);
        if (!lock2Acquired) {
            throw new RuntimeException("Could not acquire lock2");
        }
        
        // perform operation
        
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    } finally {
        if (lock2Acquired) lock2.unlock();
        if (lock1Acquired) lock1.unlock();
    }
}

// Detect deadlock programmatically
ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
long[] deadlockedThreads = threadBean.findDeadlockedThreads();
if (deadlockedThreads != null) {
    ThreadInfo[] threadInfos = threadBean.getThreadInfo(deadlockedThreads);
    // Log or handle deadlock
}
```

## Collections & Data Structures

### 16. You need to store unique elements in insertion order. Which collection would you use and why?

Use LinkedHashSet. It maintains insertion order like a list but ensures uniqueness like a set. Internally uses a hash table with a linked list running through it. Perfect when you need both uniqueness and predictable iteration order.

```java
Set<String> items = new LinkedHashSet<>();
items.add("apple");
items.add("banana");
items.add("apple"); // duplicate, won't be added
items.add("cherry");

// Iterates in insertion order: apple, banana, cherry
for (String item : items) {
    System.out.println(item);
}
```

### 17. Your application requires thread-safe collections. What are your options and trade-offs?

Use ConcurrentHashMap for maps - best performance with fine-grained locking. Use CopyOnWriteArrayList for lists with mostly reads - copies entire array on write. Use ConcurrentLinkedQueue for queues. Use Collections.synchronizedList() for simple cases but slower. BlockingQueue for producer-consumer. Avoid Hashtable and Vector - they're legacy and slow.

```java
// Best for maps - fine-grained locking
Map<String, String> map = new ConcurrentHashMap<>();

// Good for read-heavy lists
List<String> list = new CopyOnWriteArrayList<>();

// For queues
Queue<String> queue = new ConcurrentLinkedQueue<>();

// Simple but slower - locks entire collection
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// For producer-consumer
BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
```

### 18. You need to implement a LRU (Least Recently Used) cache. How would you design it?

Use LinkedHashMap with accessOrder=true. Override removeEldestEntry() to limit size. When capacity is reached, oldest entry is automatically removed. LinkedHashMap maintains access order and provides O(1) operations.

```java
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // true = access order
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// Usage
LRUCache<String, String> cache = new LRUCache<>(3);
cache.put("1", "one");
cache.put("2", "two");
cache.put("3", "three");
cache.get("1"); // access "1"
cache.put("4", "four"); // "2" is removed (least recently used)
```

### 19. How would you efficiently find duplicates in a large list?

Use HashSet to track seen elements. Add each element to set - if add() returns false, it's a duplicate. Time complexity O(n), space O(n). For finding all duplicates, use frequency map with HashMap.

```java
// Find if duplicates exist
public boolean hasDuplicates(List<String> list) {
    Set<String> seen = new HashSet<>();
    for (String item : list) {
        if (!seen.add(item)) {
            return true;
        }
    }
    return false;
}

// Find all duplicates
public Set<String> findDuplicates(List<String> list) {
    Set<String> seen = new HashSet<>();
    Set<String> duplicates = new HashSet<>();
    
    for (String item : list) {
        if (!seen.add(item)) {
            duplicates.add(item);
        }
    }
    return duplicates;
}

// Using streams
Set<String> duplicates = list.stream()
    .filter(item -> Collections.frequency(list, item) > 1)
    .collect(Collectors.toSet());
```

### 20. You need to sort a collection of custom objects by multiple fields. How do you implement this?

Use Comparator.comparing() and chain with thenComparing() for multiple fields. Or implement Comparable in the class. Comparator is more flexible as you can define multiple sorting strategies.

```java
class Employee {
    String name;
    int age;
    double salary;
}

// Sort by age, then by name
List<Employee> employees = new ArrayList<>();
employees.sort(Comparator.comparing(Employee::getAge)
                         .thenComparing(Employee::getName));

// Sort by salary descending, then name ascending
employees.sort(Comparator.comparing(Employee::getSalary).reversed()
                         .thenComparing(Employee::getName));

// Using streams
List<Employee> sorted = employees.stream()
    .sorted(Comparator.comparing(Employee::getAge)
                      .thenComparing(Employee::getName))
    .collect(Collectors.toList());
```

### 21. Your application needs to process data in FIFO order across multiple threads. What data structure would you use?

Use BlockingQueue implementations like LinkedBlockingQueue or ArrayBlockingQueue. They're thread-safe and handle FIFO ordering automatically. Threads block when queue is empty or full. Perfect for producer-consumer scenarios.

```java
// Unbounded queue
BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

// Bounded queue with capacity
BlockingQueue<Task> boundedQueue = new ArrayBlockingQueue<>(100);

// Producer thread
queue.put(task); // blocks if full (for bounded queue)

// Consumer thread
Task task = queue.take(); // blocks if empty

// Non-blocking alternatives
queue.offer(task); // returns false if full
Task task = queue.poll(); // returns null if empty
```

### 22. You need to find the intersection of two large lists efficiently. What's your approach?

Convert one list to HashSet for O(1) lookup, then iterate through second list checking contains(). Time complexity O(n+m), space O(n). Use streams for cleaner code. For very large lists, consider using retainAll().

```java
// Using HashSet
public List<String> findIntersection(List<String> list1, List<String> list2) {
    Set<String> set1 = new HashSet<>(list1);
    List<String> intersection = new ArrayList<>();
    
    for (String item : list2) {
        if (set1.contains(item)) {
            intersection.add(item);
        }
    }
    return intersection;
}

// Using streams
List<String> intersection = list1.stream()
    .filter(new HashSet<>(list2)::contains)
    .collect(Collectors.toList());

// Using retainAll
Set<String> set1 = new HashSet<>(list1);
set1.retainAll(list2);
List<String> intersection = new ArrayList<>(set1);
```

### 23. How would you implement a custom HashMap from scratch?

Use array of buckets (linked lists or arrays). Hash the key to find bucket index. Handle collisions with chaining. Implement put(), get(), and resize when load factor exceeds threshold. Use modulo for array indexing.

```java
public class SimpleHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Entry<K, V>[] buckets;
    private int size = 0;
    
    @SuppressWarnings("unchecked")
    public SimpleHashMap(int capacity) {
        buckets = new Entry[capacity];
    }
    
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % buckets.length);
        Entry<K, V> entry = buckets[index];
        
        // Check if key exists
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        
        // Add new entry at beginning
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }
    
    public V get(K key) {
        int index = Math.abs(key.hashCode() % buckets.length);
        Entry<K, V> entry = buckets[index];
        
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }
}
```

### 24. You need to maintain a sorted collection with fast insertion and retrieval. What would you use?

Use TreeSet or TreeMap - they maintain sorted order using Red-Black tree. O(log n) for add, remove, and contains. Use PriorityQueue if you only need to access minimum/maximum element. For concurrent access, use ConcurrentSkipListSet.

```java
// TreeSet - sorted unique elements
Set<Integer> sortedSet = new TreeSet<>();
sortedSet.add(5);
sortedSet.add(1);
sortedSet.add(3);
// Iterates in order: 1, 3, 5

// TreeMap - sorted key-value pairs
Map<String, Integer> sortedMap = new TreeMap<>();

// Custom comparator
Set<String> set = new TreeSet<>(Comparator.reverseOrder());

// Thread-safe sorted set
Set<Integer> concurrentSet = new ConcurrentSkipListSet<>();

// PriorityQueue - only need min/max
Queue<Integer> pq = new PriorityQueue<>();
pq.offer(5);
pq.offer(1);
Integer min = pq.poll(); // returns 1
```

### 25. Your application needs to store key-value pairs where keys can be garbage collected. What collection would you use?

Use WeakHashMap. Keys are stored as weak references, so they can be garbage collected when no strong references exist. Perfect for caches where you don't want to prevent garbage collection. Values are strongly referenced.

```java
// Keys can be garbage collected
Map<Key, Value> cache = new WeakHashMap<>();

Key key = new Key("data");
cache.put(key, new Value());

// When key is no longer referenced elsewhere
key = null;
System.gc(); // Key-value pair will be removed from map

// Example use case - metadata cache
Map<Object, Metadata> metadata = new WeakHashMap<>();
metadata.put(someObject, new Metadata());
// When someObject is GC'd, metadata is automatically removed
```

### 26. You need to execute multiple tasks in parallel and wait for all to complete. How do you implement this?

Use ExecutorService with invokeAll() or CompletableFuture.allOf(). invokeAll() blocks until all tasks complete. CompletableFuture is non-blocking and more flexible. Can also use CountDownLatch or CyclicBarrier for coordination.

```java
// Using ExecutorService
ExecutorService executor = Executors.newFixedThreadPool(10);
List<Callable<String>> tasks = Arrays.asList(
    () -> performTask1(),
    () -> performTask2(),
    () -> performTask3()
);

List<Future<String>> results = executor.invokeAll(tasks);
for (Future<String> result : results) {
    String value = result.get();
}
executor.shutdown();

// Using CompletableFuture
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> performTask1());
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> performTask2());
CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> performTask3());

CompletableFuture<Void> allOf = CompletableFuture.allOf(f1, f2, f3);
allOf.join(); // wait for all

// Get results
String result1 = f1.join();
String result2 = f2.join();
String result3 = f3.join();

// Using CountDownLatch
CountDownLatch latch = new CountDownLatch(3);
executor.submit(() -> { performTask1(); latch.countDown(); });
executor.submit(() -> { performTask2(); latch.countDown(); });
executor.submit(() -> { performTask3(); latch.countDown(); });
latch.await(); // wait for all tasks
```

### 27. How would you implement a thread pool for task execution?

Use ExecutorService from java.util.concurrent. Use Executors.newFixedThreadPool() for fixed size, newCachedThreadPool() for dynamic sizing, or ThreadPoolExecutor for custom configuration. Submit tasks with execute() or submit(). Always shutdown when done.

```java
// Fixed thread pool
ExecutorService executor = Executors.newFixedThreadPool(10);

// Submit tasks
executor.execute(() -> performTask());
Future<String> future = executor.submit(() -> performTaskWithResult());

// Shutdown
executor.shutdown();
executor.awaitTermination(1, TimeUnit.MINUTES);

// Custom thread pool with more control
ThreadPoolExecutor customExecutor = new ThreadPoolExecutor(
    5,                      // core pool size
    10,                     // max pool size
    60L,                    // keep alive time
    TimeUnit.SECONDS,
    new LinkedBlockingQueue<>(100), // work queue
    new ThreadPoolExecutor.CallerRunsPolicy() // rejection policy
);

// Scheduled thread pool for periodic tasks
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
scheduler.scheduleAtFixedRate(() -> performTask(), 0, 1, TimeUnit.MINUTES);

// Single thread executor
ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
```

## Streams & Functional Programming

### 28. You need to process a large dataset and filter, transform, and aggregate it. How do you use Streams efficiently?

Chain operations - filter first to reduce data early, then map to transform, finally collect or reduce to aggregate. Streams are lazy - intermediate operations don't execute until terminal operation is called. Avoid multiple passes over data. Use method references for cleaner code.

```java
List<Employee> employees = getEmployees();

// Filter, transform, aggregate
double avgSalary = employees.stream()
    .filter(e -> e.getAge() > 30)           // Filter early
    .filter(e -> e.getDepartment().equals("IT"))
    .map(Employee::getSalary)                // Transform
    .mapToDouble(Double::doubleValue)
    .average()                               // Aggregate
    .orElse(0.0);

// Complex aggregation
Map<String, Double> deptAvgSalary = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

// Multiple transformations
List<String> names = employees.stream()
    .filter(e -> e.isActive())
    .map(Employee::getName)
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());
```

### 29. How would you implement parallel processing of a collection while maintaining thread safety?

Use parallelStream() instead of stream(). Java handles thread management automatically. Ensure operations are stateless and don't modify shared state. Use thread-safe collectors. Parallel streams use ForkJoinPool. Only use for large datasets and CPU-intensive operations.

```java
// Parallel processing
List<Result> results = largeList.parallelStream()
    .filter(item -> expensiveCheck(item))
    .map(item -> expensiveTransform(item))
    .collect(Collectors.toList());

// Thread-safe aggregation
long count = list.parallelStream()
    .filter(predicate)
    .count();

// Use concurrent collectors for mutable reduction
Map<String, List<Employee>> grouped = employees.parallelStream()
    .collect(Collectors.groupingByConcurrent(Employee::getDepartment));

// Custom parallel processing with control
ForkJoinPool customPool = new ForkJoinPool(4);
List<Result> results = customPool.submit(() ->
    list.parallelStream()
        .map(this::process)
        .collect(Collectors.toList())
).join();
```

### 30. You need to group data by multiple criteria. How do you use Collectors effectively?

Use Collectors.groupingBy() for single level grouping. Chain groupingBy() for multi-level grouping. Use downstream collectors like counting(), summingDouble(), or mapping() for aggregations within groups.

```java
// Single level grouping
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));

// Multi-level grouping
Map<String, Map<String, List<Employee>>> byDeptAndCity = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.groupingBy(Employee::getCity)
    ));

// Grouping with aggregation
Map<String, Long> deptCount = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
    ));

// Grouping with sum
Map<String, Double> deptTotalSalary = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.summingDouble(Employee::getSalary)
    ));

// Grouping with custom aggregation
Map<String, Set<String>> deptCities = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.mapping(Employee::getCity, Collectors.toSet())
    ));

// Partition by condition (boolean grouping)
Map<Boolean, List<Employee>> seniorJunior = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getAge() > 40));
```

### 31. Your Stream operations are causing performance issues. How do you optimize them?

Filter early to reduce data volume. Avoid unnecessary boxing/unboxing - use primitive streams (IntStream, LongStream). Don't use parallel streams for small datasets or I/O operations. Avoid stateful operations like sorted() if not needed. Use findFirst() instead of collecting all results when you need just one.

```java
// Bad - processes all then filters
long count = employees.stream()
    .map(Employee::getSalary)
    .filter(salary -> salary > 50000)
    .count();

// Good - filter first
long count = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .count();

// Use primitive streams to avoid boxing
int sum = employees.stream()
    .mapToInt(Employee::getAge)  // IntStream, not Stream<Integer>
    .sum();

// Use short-circuit operations
Optional<Employee> first = employees.stream()
    .filter(e -> e.getSalary() > 100000)
    .findFirst();  // Stops after finding first match

// Avoid multiple passes
// Bad
List<Employee> filtered = list.stream().filter(predicate).collect(Collectors.toList());
long count = filtered.stream().count();

// Good
long count = list.stream().filter(predicate).count();

// Don't use parallel for small lists or I/O
// Bad for I/O operations
list.parallelStream().forEach(item -> saveToDatabase(item));

// Good
list.stream().forEach(item -> saveToDatabase(item));
```

### 32. You need to handle exceptions within Stream operations. What's your approach?

Wrap checked exceptions in unchecked exceptions. Create wrapper methods that handle exceptions. Use try-catch inside lambda. Or use libraries like Vavr for functional exception handling. Extract exception-prone logic to separate methods.

```java
// Wrap checked exception
public static <T, R> Function<T, R> wrap(CheckedFunction<T, R> function) {
    return t -> {
        try {
            return function.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}

// Usage
List<String> results = list.stream()
    .map(wrap(item -> methodThatThrowsException(item)))
    .collect(Collectors.toList());

// Try-catch inside lambda
List<Result> results = list.stream()
    .map(item -> {
        try {
            return processItem(item);
        } catch (Exception e) {
            log.error("Error processing item", e);
            return null;
        }
    })
    .filter(Objects::nonNull)
    .collect(Collectors.toList());

// Return Optional for error handling
List<Result> results = list.stream()
    .map(this::safeProcess)
    .filter(Optional::isPresent)
    .map(Optional::get)
    .collect(Collectors.toList());

private Optional<Result> safeProcess(Item item) {
    try {
        return Optional.of(process(item));
    } catch (Exception e) {
        log.error("Error", e);
        return Optional.empty();
    }
}
```

### 33. How would you implement custom Collectors for complex aggregations?

Use Collector.of() to create custom collectors. Define supplier (creates container), accumulator (adds element), combiner (merges containers), and finisher (final transformation). Use for complex aggregations not covered by built-in collectors.

```java
// Custom collector to join strings with custom logic
Collector<String, StringBuilder, String> customJoiner = Collector.of(
    StringBuilder::new,                    // supplier
    (sb, s) -> sb.append(s).append(", "), // accumulator
    StringBuilder::append,                 // combiner
    StringBuilder::toString               // finisher
);

List<String> names = Arrays.asList("John", "Jane", "Bob");
String result = names.stream().collect(customJoiner);

// Custom collector for statistics
class Stats {
    int count;
    double sum;
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;
}

Collector<Employee, Stats, Stats> statsCollector = Collector.of(
    Stats::new,
    (stats, emp) -> {
        stats.count++;
        stats.sum += emp.getSalary();
        stats.min = Math.min(stats.min, emp.getSalary());
        stats.max = Math.max(stats.max, emp.getSalary());
    },
    (s1, s2) -> {
        s1.count += s2.count;
        s1.sum += s2.sum;
        s1.min = Math.min(s1.min, s2.min);
        s1.max = Math.max(s1.max, s2.max);
        return s1;
    }
);

Stats stats = employees.stream().collect(statsCollector);

// Immutable custom collector
Collector<String, ?, ImmutableList<String>> toImmutableList = Collector.of(
    ImmutableList.Builder<String>::new,
    ImmutableList.Builder::add,
    (b1, b2) -> b1.addAll(b2.build()),
    ImmutableList.Builder::build
);
```

### 34. You need to process data lazily to save memory. How do you leverage Streams?

Streams are lazy by default - intermediate operations don't execute until terminal operation. Use Stream.iterate() or Stream.generate() for infinite streams. Process one element at a time without loading all data. Use limit() to process only needed elements.

```java
// Lazy evaluation - only processes until condition met
Optional<Integer> result = Stream.iterate(1, n -> n + 1)
    .filter(n -> n % 2 == 0)
    .filter(n -> n > 100)
    .findFirst();  // Stops after finding first match

// Process large file lazily
try (Stream<String> lines = Files.lines(Paths.get("large.txt"))) {
    long count = lines
        .filter(line -> line.contains("error"))
        .count();
}

// Generate infinite stream, process limited
List<String> randomIds = Stream.generate(() -> UUID.randomUUID().toString())
    .limit(100)
    .collect(Collectors.toList());

// Lazy database query processing
Stream<Employee> employeeStream = repository.findAllStream();
employeeStream
    .filter(e -> e.getSalary() > 50000)
    .limit(10)
    .forEach(this::process);

// Process in chunks without loading all
IntStream.range(0, 1000000)
    .filter(n -> isPrime(n))
    .limit(100)  // Only finds first 100 primes
    .forEach(System.out::println);
```

### 35. You need to implement flatMap for nested collections. How do you approach this?

Use flatMap() to flatten nested structures. It maps each element to a stream and flattens all streams into one. Perfect for processing collections within collections. Converts Stream<Stream<T>> to Stream<T>.

```java
// Flatten list of lists
List<List<String>> listOfLists = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d"),
    Arrays.asList("e", "f")
);

List<String> flattened = listOfLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
// Result: [a, b, c, d, e, f]

// Process nested objects
class Department {
    List<Employee> employees;
}

List<Department> departments = getDepartments();

// Get all employees from all departments
List<Employee> allEmployees = departments.stream()
    .flatMap(dept -> dept.getEmployees().stream())
    .collect(Collectors.toList());

// Get all employee names
List<String> names = departments.stream()
    .flatMap(dept -> dept.getEmployees().stream())
    .map(Employee::getName)
    .collect(Collectors.toList());

// Flatten Optional
List<Optional<String>> optionals = Arrays.asList(
    Optional.of("a"),
    Optional.empty(),
    Optional.of("b")
);

List<String> values = optionals.stream()
    .flatMap(Optional::stream)  // Java 9+
    .collect(Collectors.toList());

// Complex nested structure
class Order {
    List<OrderItem> items;
}
class OrderItem {
    List<String> tags;
}

List<Order> orders = getOrders();

// Get all tags from all items in all orders
Set<String> allTags = orders.stream()
    .flatMap(order -> order.getItems().stream())
    .flatMap(item -> item.getTags().stream())
    .collect(Collectors.toSet());

// Split and flatten strings
List<String> sentences = Arrays.asList("hello world", "java streams");
List<String> words = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .collect(Collectors.toList());
```

## Spring Boot REST APIs

### 36. You need to build a RESTful API with proper HTTP status codes and error handling. How do you structure it?

Use @RestController for controllers. Return ResponseEntity with proper status codes - 200 for success, 201 for created, 404 for not found, 400 for bad request, 500 for server errors. Use @RestControllerAdvice for global exception handling. Create standard error response structure with timestamp, message, and status.

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

### 37. How would you implement versioning for your REST APIs?

Use URI versioning - most common and explicit. Add version in path like /api/v1/users. Or use request parameter versioning. Or header versioning with custom header. Or media type versioning in Accept header. URI versioning is simplest and most visible.

```java
// URI versioning - most common
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    @GetMapping
    public List<UserV1> getUsers() {
        return userService.getAllV1();
    }
}

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
    @GetMapping
    public List<UserV2> getUsers() {
        return userService.getAllV2();
    }
}

// Request parameter versioning
@GetMapping(value = "/users", params = "version=1")
public List<UserV1> getUsersV1() {
    return userService.getAllV1();
}

// Header versioning
@GetMapping(value = "/users", headers = "X-API-VERSION=1")
public List<UserV1> getUsersV1() {
    return userService.getAllV1();
}

// Media type versioning
@GetMapping(value = "/users", produces = "application/vnd.api.v1+json")
public List<UserV1> getUsersV1() {
    return userService.getAllV1();
}
```

### 38. You need to handle file uploads in a REST API. How do you implement this efficiently?

Use MultipartFile parameter. Configure max file size in application.properties. Save to file system or cloud storage like S3. Validate file type and size. Return file metadata. Use streaming for large files.

```java
@RestController
@RequestMapping("/api/files")
public class FileController {
    
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        // Validate file type
        String contentType = file.getContentType();
        if (!Arrays.asList("image/jpeg", "image/png").contains(contentType)) {
            return ResponseEntity.badRequest().build();
        }
        
        // Save file
        String filename = fileService.save(file);
        
        FileResponse response = new FileResponse(filename, file.getSize());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = fileService.load(filename);
        
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
            .body(resource);
    }
}

// application.properties
// spring.servlet.multipart.max-file-size=10MB
// spring.servlet.multipart.max-request-size=10MB
```

### 39. How would you implement pagination and sorting for large datasets?

Use Pageable parameter from Spring Data. Accept page, size, and sort parameters. Return Page object with content and metadata. Use @PageableDefault for defaults. Can wrap in custom response for consistent structure.

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }
    
    // Custom response wrapper
    @GetMapping("/custom")
    public ResponseEntity<PageResponse<User>> getUsersCustom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<User> userPage = userService.findAll(pageable);
        
        PageResponse<User> response = new PageResponse<>(
            userPage.getContent(),
            userPage.getTotalElements(),
            userPage.getTotalPages(),
            userPage.getNumber()
        );
        return ResponseEntity.ok(response);
    }
}

// Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByStatus(String status, Pageable pageable);
}

// Usage: GET /api/users?page=0&size=10&sort=name,asc
```

### 40. You need to implement rate limiting for your API endpoints. How do you approach this?

Use Bucket4j library with Spring Boot. Create filter to check rate limits. Store rate limit data in cache or Redis. Return 429 Too Many Requests when limit exceeded. Implement per-user or per-IP limits.

```java
@Component
public class RateLimitFilter extends OncePerRequestFilter {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        String key = getClientKey(request);
        Bucket bucket = resolveBucket(key);
        
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded");
        }
    }
    
    private Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, k -> createBucket());
    }
    
    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
    
    private String getClientKey(HttpServletRequest request) {
        return request.getRemoteAddr(); // or use user ID
    }
}
```

### 41. How would you handle CORS in a Spring Boot application?

Use @CrossOrigin annotation on controllers or methods. Or configure globally with WebMvcConfigurer. Specify allowed origins, methods, and headers. For production, restrict to specific domains.

```java
// Method level
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }
}

// Controller level
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    // All methods allow CORS
}

// Global configuration
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:3000", "https://example.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }
}

// Or using SecurityConfig
@Configuration
public class SecurityConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
```

### 42. You need to implement content negotiation (JSON/XML). How do you configure this?

Add Jackson XML dependency. Use produces attribute in @GetMapping. Spring Boot handles conversion automatically based on Accept header. Client sends Accept: application/json or Accept: application/xml.

```java
// Add dependency: jackson-dataformat-xml

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // Supports both JSON and XML
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<User> getUsers() {
        return userService.findAll();
    }
    
    // JSON only
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsersJson() {
        return userService.findAll();
    }
    
    // XML only
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<User> getUsersXml() {
        return userService.findAll();
    }
}

// Entity needs @XmlRootElement for XML
@XmlRootElement
public class User {
    private Long id;
    private String name;
    // getters/setters
}

// Client request:
// Accept: application/json -> returns JSON
// Accept: application/xml -> returns XML
```

### 43. How would you implement HATEOAS in your REST APIs?

Use Spring HATEOAS library. Add links to resources using EntityModel and WebMvcLinkBuilder. Include self link and related resource links. Makes API discoverable and self-documenting.

```java
// Add dependency: spring-boot-starter-hateoas

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        
        EntityModel<User> resource = EntityModel.of(user);
        resource.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).getUsers()).withRel("all-users"));
        resource.add(linkTo(methodOn(OrderController.class).getUserOrders(id)).withRel("orders"));
        
        return resource;
    }
    
    @GetMapping
    public CollectionModel<EntityModel<User>> getUsers() {
        List<EntityModel<User>> users = userService.findAll().stream()
            .map(user -> EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel()))
            .collect(Collectors.toList());
        
        return CollectionModel.of(users,
            linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
    }
}

// Response includes _links:
// {
//   "id": 1,
//   "name": "John",
//   "_links": {
//     "self": {"href": "http://localhost:8080/api/users/1"},
//     "all-users": {"href": "http://localhost:8080/api/users"},
//     "orders": {"href": "http://localhost:8080/api/users/1/orders"}
//   }
// }
```

### 44. You need to validate request payloads. How do you implement comprehensive validation?

Use Bean Validation annotations like @NotNull, @NotBlank, @Size, @Email. Add @Valid on request body. Handle MethodArgumentNotValidException in exception handler. Create custom validators for complex rules.

```java
public class UserRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    
    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits")
    private String phone;
}

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

@RestControllerAdvice
public class ValidationExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
```

### 45. How would you implement API documentation using Swagger/OpenAPI?

Add springdoc-openapi dependency. Access docs at /swagger-ui.html. Use @Operation, @ApiResponse annotations for details. Configure API info in application.properties. Swagger UI provides interactive documentation.

```java
// Add dependency: springdoc-openapi-ui

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Create new user")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

// Configuration
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("User API")
                .version("1.0")
                .description("User management API documentation"));
    }
}

// Access at: http://localhost:8080/swagger-ui.html
```

### 46. You need to implement API rate limiting and throttling. What's your approach?

Use Bucket4j for token bucket algorithm. Implement as filter or interceptor. Store buckets in Redis for distributed systems. Configure different limits per endpoint or user role. Return rate limit headers in response.

```java
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) throws Exception {
        
        String key = getUserKey(request);
        Bucket bucket = resolveBucket(key);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            response.setStatus(429);
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", 
                String.valueOf(probe.getNanosToWaitForRefill() / 1_000_000_000));
            return false;
        }
    }
    
    private Bucket resolveBucket(String key) {
        return buckets.computeIfAbsent(key, k -> createBucket());
    }
    
    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}

// Different limits per endpoint
@Component
public class EndpointRateLimiter {
    
    public Bucket getBucket(String endpoint) {
        if (endpoint.contains("/api/public")) {
            return createBucket(10, Duration.ofMinutes(1));
        } else if (endpoint.contains("/api/premium")) {
            return createBucket(1000, Duration.ofMinutes(1));
        }
        return createBucket(100, Duration.ofMinutes(1));
    }
}
```

### 47. How would you handle timeout and connection issues with external APIs?

Use RestTemplate or WebClient with timeout configuration. Implement retry logic with exponential backoff. Use circuit breaker pattern with Resilience4j. Handle exceptions and provide fallback responses. Log failures for monitoring.

```java
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return new RestTemplate(factory);
    }
}

@Service
public class ExternalApiService {
    
    private final RestTemplate restTemplate;
    
    // With timeout handling
    public String callExternalApi(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (ResourceAccessException e) {
            log.error("Timeout calling external API", e);
            throw new ServiceUnavailableException("External service timeout");
        }
    }
    
    // With retry
    @Retryable(
        value = {ResourceAccessException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public String callWithRetry(String url) {
        return restTemplate.getForObject(url, String.class);
    }
    
    // With circuit breaker
    @CircuitBreaker(name = "externalApi", fallbackMethod = "fallback")
    public String callWithCircuitBreaker(String url) {
        return restTemplate.getForObject(url, String.class);
    }
    
    private String fallback(String url, Exception e) {
        log.error("Circuit breaker fallback", e);
        return "Service temporarily unavailable";
    }
}

// WebClient with timeout
@Bean
public WebClient webClient() {
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5))
        ))
        .build();
}

// Usage
webClient.get()
    .uri(url)
    .retrieve()
    .bodyToMono(String.class)
    .timeout(Duration.ofSeconds(5))
    .onErrorResume(e -> Mono.just("Fallback response"));
```

## Spring Boot Data & JPA

### 48. You need to fetch data from database with complex joins. How do you optimize query performance?

Use @EntityGraph to fetch associations in single query. Or use JOIN FETCH in JPQL. Use DTO projections to fetch only needed fields. Add proper indexes on join columns. Use native queries for very complex joins. Monitor with query logging.

```java
// Using @EntityGraph
@EntityGraph(attributePaths = {"department", "address"})
@Query("SELECT e FROM Employee e WHERE e.status = :status")
List<Employee> findByStatusWithDept(@Param("status") String status);

// Using JOIN FETCH in JPQL
@Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
Optional<Employee> findByIdWithDept(@Param("id") Long id);

// DTO Projection - fetch only needed fields
@Query("SELECT new com.example.dto.EmployeeDTO(e.id, e.name, d.name) " +
       "FROM Employee e JOIN e.department d WHERE e.status = :status")
List<EmployeeDTO> findEmployeeDTOs(@Param("status") String status);

// Native query for complex joins
@Query(value = "SELECT e.*, d.name as dept_name FROM employee e " +
               "INNER JOIN department d ON e.dept_id = d.id " +
               "WHERE e.salary > :salary", nativeQuery = true)
List<Object[]> findWithComplexJoin(@Param("salary") double salary);

// Interface projection
public interface EmployeeProjection {
    String getName();
    String getDepartmentName();
}

@Query("SELECT e.name as name, d.name as departmentName " +
       "FROM Employee e JOIN e.department d")
List<EmployeeProjection> findAllProjections();
```

### 49. Your application has N+1 query problems. How do you identify and fix them?

Enable query logging to see all SQL queries. Look for repeated queries in loops. Fix with @EntityGraph, JOIN FETCH, or batch fetching. Use @BatchSize for lazy loading. Set fetch type to LAZY by default.

```java
// Enable query logging in application.properties
// spring.jpa.show-sql=true
// logging.level.org.hibernate.SQL=DEBUG

// Problem - N+1 queries
List<Employee> employees = employeeRepository.findAll();
for (Employee emp : employees) {
    emp.getDepartment().getName(); // Triggers separate query for each
}

// Solution 1: @EntityGraph
@EntityGraph(attributePaths = {"department"})
List<Employee> findAll();

// Solution 2: JOIN FETCH
@Query("SELECT e FROM Employee e JOIN FETCH e.department")
List<Employee> findAllWithDept();

// Solution 3: @BatchSize for lazy loading
@Entity
public class Employee {
    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private Department department;
}

// Solution 4: Multiple entity graphs
@NamedEntityGraph(
    name = "Employee.full",
    attributeNodes = {
        @NamedAttributeNode("department"),
        @NamedAttributeNode("address")
    }
)
@Entity
public class Employee {
    // fields
}

@EntityGraph("Employee.full")
List<Employee> findAll();
```

### 50. You need to implement soft delete functionality. How do you approach this?

Add deleted flag or deletedAt timestamp to entity. Use @Where annotation to filter deleted records. Override delete methods to set flag instead of removing. Create custom queries for finding deleted records.

```java
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id = ?")
public class Employee {
    @Id
    private Long id;
    private String name;
    
    @Column(name = "deleted")
    private boolean deleted = false;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}

// Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Finds only non-deleted (default behavior due to @Where)
    List<Employee> findByName(String name);
    
    // Find including deleted
    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findByNameIncludingDeleted(@Param("name") String name);
    
    // Find only deleted
    @Query("SELECT e FROM Employee e WHERE e.deleted = true")
    List<Employee> findDeleted();
}

// Service
@Service
public class EmployeeService {
    
    public void softDelete(Long id) {
        Employee employee = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Employee not found"));
        employee.setDeleted(true);
        employee.setDeletedAt(LocalDateTime.now());
        repository.save(employee);
    }
    
    public void restore(Long id) {
        Employee employee = repository.findByIdIncludingDeleted(id)
            .orElseThrow(() -> new NotFoundException("Employee not found"));
        employee.setDeleted(false);
        employee.setDeletedAt(null);
        repository.save(employee);
    }
}
```

### 51. How would you handle database transactions across multiple operations?

Use @Transactional annotation on service methods. Spring manages transaction automatically. All operations succeed or all rollback. Use propagation and isolation levels for complex scenarios. Handle exceptions properly for rollback.

```java
@Service
public class OrderService {
    
    @Transactional
    public void createOrder(OrderRequest request) {
        // All operations in single transaction
        Order order = orderRepository.save(new Order(request));
        inventoryService.reduceStock(request.getItems());
        paymentService.processPayment(request.getPayment());
        // If any fails, all rollback
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void processWithRollback() {
        // Rollback on any exception
    }
    
    @Transactional(noRollbackFor = CustomException.class)
    public void processWithoutRollback() {
        // Don't rollback for CustomException
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void independentTransaction() {
        // Creates new transaction, independent of caller
    }
    
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void highIsolation() {
        // Highest isolation level
    }
    
    @Transactional(readOnly = true)
    public List<Order> getOrders() {
        // Read-only transaction, optimization
        return orderRepository.findAll();
    }
}

// Programmatic transaction
@Service
public class ManualTransactionService {
    
    private final TransactionTemplate transactionTemplate;
    
    public void executeInTransaction() {
        transactionTemplate.execute(status -> {
            try {
                // operations
                return result;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
        });
    }
}
```

### 52. You need to implement auditing (created_by, updated_by, timestamps). How do you do this?

Use @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy annotations. Enable JPA auditing with @EnableJpaAuditing. Implement AuditorAware to provide current user. Extend base entity for reusability.

```java
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            // Get current user from security context
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return Optional.ofNullable(auth)
                .map(Authentication::getName)
                .or(() -> Optional.of("system"));
        };
    }
}

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}

@Entity
public class Employee extends Auditable {
    @Id
    private Long id;
    private String name;
    // Automatically gets audit fields
}
```

### 53. How would you implement custom queries that JPA doesn't support well?

Use @Query with native SQL for complex queries. Use JPQL for entity-based queries. Use Criteria API for dynamic queries. Use Spring Data Specifications for complex filtering. Use QueryDSL for type-safe queries.

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Native query
    @Query(value = "SELECT * FROM employee WHERE salary > :salary " +
                   "AND YEAR(hire_date) = :year", nativeQuery = true)
    List<Employee> findBySalaryAndYear(@Param("salary") double salary, 
                                       @Param("year") int year);
    
    // JPQL with complex conditions
    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary " +
           "AND e.department.name IN :depts ORDER BY e.salary DESC")
    List<Employee> findByComplexCriteria(@Param("minSalary") double minSalary,
                                         @Param("depts") List<String> depts);
    
    // Modifying query
    @Modifying
    @Query("UPDATE Employee e SET e.salary = e.salary * 1.1 WHERE e.department.id = :deptId")
    int increaseSalary(@Param("deptId") Long deptId);
}

// Specifications for dynamic queries
public class EmployeeSpecifications {
    
    public static Specification<Employee> hasSalaryGreaterThan(double salary) {
        return (root, query, cb) -> cb.greaterThan(root.get("salary"), salary);
    }
    
    public static Specification<Employee> hasName(String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }
}

// Usage
Specification<Employee> spec = Specification
    .where(hasSalaryGreaterThan(50000))
    .and(hasName("John"));
List<Employee> employees = repository.findAll(spec);

// Criteria API
@Service
public class EmployeeSearchService {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Employee> search(SearchCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getName() != null) {
            predicates.add(cb.like(root.get("name"), "%" + criteria.getName() + "%"));
        }
        if (criteria.getMinSalary() != null) {
            predicates.add(cb.greaterThan(root.get("salary"), criteria.getMinSalary()));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }
}
```

### 54. How would you implement optimistic vs pessimistic locking?

Optimistic locking uses @Version annotation - checks version on update. Pessimistic locking uses @Lock annotation - locks row during read. Use optimistic for low contention, pessimistic for high contention. Handle OptimisticLockException.

```java
// Optimistic locking
@Entity
public class Account {
    @Id
    private Long id;
    
    private Double balance;
    
    @Version
    private Long version;
}

@Service
public class AccountService {
    
    @Transactional
    public void transfer(Long fromId, Long toId, Double amount) {
        try {
            Account from = accountRepository.findById(fromId).orElseThrow();
            Account to = accountRepository.findById(toId).orElseThrow();
            
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            
            accountRepository.save(from);
            accountRepository.save(to);
        } catch (OptimisticLockException e) {
            // Handle concurrent modification
            throw new ConcurrentUpdateException("Account was modified by another transaction");
        }
    }
}

// Pessimistic locking
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdWithLock(@Param("id") Long id);
    
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Account> findById(Long id);
}

@Service
public class AccountServiceWithPessimisticLock {
    
    @Transactional
    public void transfer(Long fromId, Long toId, Double amount) {
        // Locks rows until transaction completes
        Account from = accountRepository.findByIdWithLock(fromId).orElseThrow();
        Account to = accountRepository.findByIdWithLock(toId).orElseThrow();
        
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        
        accountRepository.save(from);
        accountRepository.save(to);
    }
}
```

### 55. You need to batch insert thousands of records efficiently. What's your approach?

Configure batch size in properties. Use saveAll() instead of multiple save() calls. Flush and clear EntityManager periodically. Use JDBC batch for very large datasets. Disable auto-increment for better performance.

```java
// application.properties
// spring.jpa.properties.hibernate.jdbc.batch_size=50
// spring.jpa.properties.hibernate.order_inserts=true
// spring.jpa.properties.hibernate.order_updates=true

@Service
public class BatchInsertService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void batchInsert(List<Employee> employees) {
        int batchSize = 50;
        for (int i = 0; i < employees.size(); i++) {
            em.persist(employees.get(i));
            
            if (i > 0 && i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        em.flush();
        em.clear();
    }
    
    // Using repository
    @Transactional
    public void batchInsertWithRepo(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }
}

// JDBC batch for very large datasets
@Service
public class JdbcBatchService {
    
    private final JdbcTemplate jdbcTemplate;
    
    public void batchInsert(List<Employee> employees) {
        String sql = "INSERT INTO employee (name, salary, dept_id) VALUES (?, ?, ?)";
        
        jdbcTemplate.batchUpdate(sql, employees, 100, (ps, employee) -> {
            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());
        });
    }
}

// Using sequence for better batch performance
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "employee_seq", allocationSize = 50)
    private Long id;
}
```

### 56. How would you implement database migration and versioning?

Use Flyway or Liquibase for database migrations. Create versioned SQL scripts. Migrations run automatically on startup. Use naming convention like V1__Initial_schema.sql. Keep migrations in version control.

```java
// Add dependency: flyway-core

// application.properties
// spring.flyway.enabled=true
// spring.flyway.locations=classpath:db/migration
// spring.flyway.baseline-on-migrate=true

// File: src/main/resources/db/migration/V1__Initial_schema.sql
/*
CREATE TABLE employee (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    salary DECIMAL(10,2),
    created_at TIMESTAMP
);
*/

// File: V2__Add_department.sql
/*
CREATE TABLE department (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

ALTER TABLE employee ADD COLUMN dept_id BIGINT;
ALTER TABLE employee ADD FOREIGN KEY (dept_id) REFERENCES department(id);
*/

// File: V3__Add_indexes.sql
/*
CREATE INDEX idx_employee_name ON employee(name);
CREATE INDEX idx_employee_dept ON employee(dept_id);
*/

// Programmatic configuration
@Configuration
public class FlywayConfig {
    
    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .baselineOnMigrate(true)
            .load();
        flyway.migrate();
        return flyway;
    }
}

// Using Liquibase
// File: db/changelog/db.changelog-master.yaml
/*
databaseChangeLog:
  - changeSet:
      id: 1
      author: developer
      changes:
        - createTable:
            tableName: employee
            columns:
              - column:
                  name: id
                  type: bigint
                  constraints:
                    primaryKey: true
              - column:
                  name: name
                  type: varchar(100)
*/
```

### 57. How would you implement database connection pooling?

Spring Boot uses HikariCP by default - best performance. Configure pool size, timeout, and connection properties. Monitor pool metrics. Use appropriate pool size based on load. Configure connection validation.

```java
// application.properties - HikariCP configuration
// spring.datasource.hikari.maximum-pool-size=10
// spring.datasource.hikari.minimum-idle=5
// spring.datasource.hikari.connection-timeout=20000
// spring.datasource.hikari.idle-timeout=300000
// spring.datasource.hikari.max-lifetime=1200000
// spring.datasource.hikari.connection-test-query=SELECT 1

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("user");
        config.setPassword("password");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(20000);
        config.setIdleTimeout(300000);
        config.setMaxLifetime(1200000);
        config.setConnectionTestQuery("SELECT 1");
        
        return new HikariDataSource(config);
    }
}

// Monitor pool metrics
@Component
public class DataSourceHealthIndicator implements HealthIndicator {
    
    private final HikariDataSource dataSource;
    
    @Override
    public Health health() {
        HikariPoolMXBean pool = dataSource.getHikariPoolMXBean();
        
        return Health.up()
            .withDetail("active", pool.getActiveConnections())
            .withDetail("idle", pool.getIdleConnections())
            .withDetail("total", pool.getTotalConnections())
            .withDetail("waiting", pool.getThreadsAwaitingConnection())
            .build();
    }
}

// Multiple datasources
@Configuration
public class MultiDataSourceConfig {
    
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
```

## Exception Handling & Validation

### 58. You need to implement global exception handling for your Spring Boot application. How do you structure it?

Use @RestControllerAdvice to handle exceptions globally. Create @ExceptionHandler methods for different exception types. Return consistent error response with status code, message, and timestamp. Handle validation errors, business exceptions, and unexpected errors separately.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            "RESOURCE_NOT_FOUND"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ErrorResponse response = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            "VALIDATION_ERROR",
            errors
        );
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            "INTERNAL_ERROR"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String errorCode;
    private Map<String, String> details;
}
```

### 59. How would you create custom exceptions with meaningful error messages?

Create exception classes extending RuntimeException. Add error code field. Include constructors for message and cause. Create specific exceptions for different scenarios - validation, not found, business logic errors.

```java
public class ApplicationException extends RuntimeException {
    private final String errorCode;
    
    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public ApplicationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String resource, Long id) {
        super("NOT_FOUND", String.format("%s with id %d not found", resource, id));
    }
}

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super("VALIDATION_ERROR", message);
    }
}

public class BusinessException extends ApplicationException {
    public BusinessException(String message) {
        super("BUSINESS_ERROR", message);
    }
}

public class InsufficientBalanceException extends BusinessException {
    public InsufficientBalanceException(double balance, double amount) {
        super(String.format("Insufficient balance. Available: %.2f, Required: %.2f", balance, amount));
    }
}

// Usage
@Service
public class UserService {
    
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
    
    public void transfer(Long fromId, Long toId, double amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        if (from.getBalance() < amount) {
            throw new InsufficientBalanceException(from.getBalance(), amount);
        }
        // process transfer
    }
}
```

### 60. You need to validate nested objects and collections. How do you implement this?

Use @Valid annotation on nested objects and collection elements. Add validation annotations on nested class fields. Use @Valid on collection with element validation. Spring validates entire object graph.

```java
public class OrderRequest {
    @NotBlank(message = "Customer name required")
    private String customerName;
    
    @Valid
    @NotNull(message = "Address required")
    private Address address;
    
    @Valid
    @NotEmpty(message = "At least one item required")
    private List<OrderItem> items;
}

public class Address {
    @NotBlank(message = "Street required")
    private String street;
    
    @NotBlank(message = "City required")
    private String city;
    
    @Pattern(regexp = "\\d{5}", message = "Invalid zip code")
    private String zipCode;
}

public class OrderItem {
    @NotNull(message = "Product ID required")
    private Long productId;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @Positive(message = "Price must be positive")
    private Double price;
}

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest request) {
        Order order = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}

// Custom collection validation
public class OrderRequest {
    @Valid
    @Size(min = 1, max = 10, message = "Order must have 1-10 items")
    private List<@Valid OrderItem> items;
}
```

### 61. How would you handle validation errors and return user-friendly messages?

Handle MethodArgumentNotValidException in @RestControllerAdvice. Extract field errors and create readable messages. Return map of field names to error messages. Use custom error response structure.

```java
@RestControllerAdvice
public class ValidationExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ValidationErrorResponse response = new ValidationErrorResponse(
            LocalDateTime.now(),
            "Validation failed",
            errors
        );
        
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        });
        
        ValidationErrorResponse response = new ValidationErrorResponse(
            LocalDateTime.now(),
            "Validation failed",
            errors
        );
        
        return ResponseEntity.badRequest().body(response);
    }
}

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> errors;
}

// Response example:
// {
//   "timestamp": "2024-01-15T10:30:00",
//   "message": "Validation failed",
//   "errors": {
//     "email": "Invalid email format",
//     "age": "Age must be at least 18",
//     "name": "Name is required"
//   }
// }
```

### 62. You need to implement custom validators for complex business rules. How do you do this?

Create custom annotation with @Constraint. Implement ConstraintValidator interface. Add validation logic in isValid() method. Can access multiple fields for cross-field validation.

```java
// Custom annotation
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface ValidDateRange {
    String message() default "End date must be after start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// Validator implementation
public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRange> {
    
    @Override
    public boolean isValid(DateRange dateRange, ConstraintValidatorContext context) {
        if (dateRange == null) {
            return true;
        }
        
        LocalDate start = dateRange.getStartDate();
        LocalDate end = dateRange.getEndDate();
        
        if (start == null || end == null) {
            return true;
        }
        
        return end.isAfter(start);
    }
}

// Usage on class
@ValidDateRange
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
}

// Custom field validator
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhone {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class PhoneNumberValidator implements ConstraintValidator<ValidPhone, String> {
    
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isEmpty()) {
            return true;
        }
        return phone.matches("^\\d{10}$");
    }
}

// Cross-field validation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "Passwords do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegistration> {
    
    @Override
    public boolean isValid(UserRegistration user, ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getConfirmPassword());
    }
}

@PasswordMatch
public class UserRegistration {
    private String password;
    private String confirmPassword;
}
```

### 63. How would you handle exceptions in async methods?

Use try-catch inside async method. Return CompletableFuture with exceptionally() or handle(). Configure AsyncUncaughtExceptionHandler for unhandled exceptions. Log errors for monitoring.

```java
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("Async exception in method: " + method.getName(), ex);
            // Send alert or notification
        };
    }
}

@Service
public class AsyncService {
    
    // Return CompletableFuture with exception handling
    @Async
    public CompletableFuture<String> processAsync(String data) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return performOperation(data);
            } catch (Exception e) {
                log.error("Error processing async", e);
                throw new CompletionException(e);
            }
        }).exceptionally(ex -> {
            log.error("Async operation failed", ex);
            return "Fallback result";
        });
    }
    
    // Using handle for both success and error
    @Async
    public CompletableFuture<String> processWithHandle(String data) {
        return CompletableFuture.supplyAsync(() -> performOperation(data))
            .handle((result, ex) -> {
                if (ex != null) {
                    log.error("Error occurred", ex);
                    return "Error: " + ex.getMessage();
                }
                return result;
            });
    }
    
    // Void async with try-catch
    @Async
    public void processVoidAsync(String data) {
        try {
            performOperation(data);
        } catch (Exception e) {
            log.error("Async void operation failed", e);
            // Handle error - send notification, retry, etc.
        }
    }
}

// Usage
@Service
public class OrderService {
    
    public void processOrder(Order order) {
        asyncService.processAsync(order.getData())
            .thenAccept(result -> log.info("Processed: " + result))
            .exceptionally(ex -> {
                log.error("Failed to process order", ex);
                return null;
            });
    }
}
```

### 64. You need to implement retry logic for transient failures?

Use Spring Retry with @Retryable annotation. Configure max attempts and backoff policy. Use @Recover for fallback after all retries fail. Handle specific exceptions only. Use exponential backoff for better resilience.

```java
@Configuration
@EnableRetry
public class RetryConfig {
}

@Service
public class ExternalApiService {
    
    @Retryable(
        value = {RestClientException.class, TimeoutException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public String callExternalApi(String url) {
        log.info("Calling external API: " + url);
        return restTemplate.getForObject(url, String.class);
    }
    
    @Recover
    public String recover(RestClientException e, String url) {
        log.error("All retry attempts failed for: " + url, e);
        return "Fallback response";
    }
    
    // Custom retry with different backoff
    @Retryable(
        value = DatabaseException.class,
        maxAttempts = 5,
        backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2)
    )
    public void saveToDatabase(Data data) {
        repository.save(data);
    }
}

// Programmatic retry with RetryTemplate
@Configuration
public class RetryTemplateConfig {
    
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);
        
        return retryTemplate;
    }
}

@Service
public class RetryService {
    
    private final RetryTemplate retryTemplate;
    
    public String executeWithRetry(String data) {
        return retryTemplate.execute(context -> {
            log.info("Attempt: " + context.getRetryCount());
            return performOperation(data);
        }, context -> {
            log.error("All retries failed");
            return "Fallback";
        });
    }
}

// Using Resilience4j for more control
@Service
public class ResilientService {
    
    @Retry(name = "externalApi", fallbackMethod = "fallback")
    public String callApi(String url) {
        return restTemplate.getForObject(url, String.class);
    }
    
    private String fallback(String url, Exception e) {
        log.error("Fallback for: " + url, e);
        return "Service temporarily unavailable";
    }
}

// application.yml
// resilience4j.retry:
//   instances:
//     externalApi:
//       maxAttempts: 3
//       waitDuration: 1000
//       exponentialBackoffMultiplier: 2
```

### 65. You need to handle different exception types differently. How do you structure your exception hierarchy?

Create base exception class. Extend for specific categories - validation, business, technical. Use specific exceptions for different scenarios. Handle each type differently in exception handler.

```java
// Base exception
public abstract class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;
    
    protected ApplicationException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

// Validation exceptions
public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super("VALIDATION_ERROR", message, HttpStatus.BAD_REQUEST);
    }
}

// Business exceptions
public class BusinessException extends ApplicationException {
    protected BusinessException(String errorCode, String message) {
        super(errorCode, message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

public class InsufficientBalanceException extends BusinessException {
    public InsufficientBalanceException(double balance, double required) {
        super("INSUFFICIENT_BALANCE", 
              String.format("Balance %.2f is less than required %.2f", balance, required));
    }
}

public class DuplicateResourceException extends BusinessException {
    public DuplicateResourceException(String resource) {
        super("DUPLICATE_RESOURCE", resource + " already exists");
    }
}

// Resource exceptions
public class ResourceException extends ApplicationException {
    protected ResourceException(String errorCode, String message, HttpStatus status) {
        super(errorCode, message, status);
    }
}

public class ResourceNotFoundException extends ResourceException {
    public ResourceNotFoundException(String resource, Object id) {
        super("NOT_FOUND", 
              String.format("%s with id %s not found", resource, id),
              HttpStatus.NOT_FOUND);
    }
}

// Technical exceptions
public class TechnicalException extends ApplicationException {
    public TechnicalException(String message) {
        super("TECHNICAL_ERROR", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

public class ExternalServiceException extends TechnicalException {
    public ExternalServiceException(String service, Throwable cause) {
        super("Failed to communicate with " + service);
        initCause(cause);
    }
}

// Global handler
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            ex.getHttpStatus().value(),
            ex.getMessage(),
            ex.getErrorCode()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
        // Special handling for validation
        log.warn("Validation error: " + ex.getMessage());
        return handleApplicationException(ex);
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        // Log business exceptions
        log.info("Business exception: " + ex.getMessage());
        return handleApplicationException(ex);
    }
    
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorResponse> handleTechnical(TechnicalException ex) {
        // Alert for technical issues
        log.error("Technical exception", ex);
        alertService.sendAlert(ex);
        return handleApplicationException(ex);
    }
}
```

## Security & Authentication

### 66. You need to implement JWT-based authentication. How do you structure it?

Create authentication endpoint that validates credentials and generates JWT token. Add filter to validate token on each request. Store user details in SecurityContext. Use secret key to sign tokens. Set expiration time. Include user roles in token claims.

```java
// JWT utility class
@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
        return expiration.before(new Date());
    }
}

// JWT filter
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain chain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        
        chain.doFilter(request, response);
    }
}

// Authentication controller
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

// Security configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
```

### 67. How would you implement role-based access control (RBAC)?

Create User and Role entities with many-to-many relationship. Implement UserDetailsService to load user with authorities. Use @PreAuthorize or @Secured on methods. Configure HttpSecurity to restrict endpoints by role.

```java
@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}

@Entity
public class Role {
    @Id
    private Long id;
    private String name; // ROLE_USER, ROLE_ADMIN
}

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()))
            .build();
    }
}

// Method-level security
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {
}

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}

// URL-based security
@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/public/**").permitAll()
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
```

### 68. You need to secure REST APIs with OAuth2. How do you implement this?

Use Spring Security OAuth2 Resource Server. Configure authorization server or use external provider like Google, GitHub. Validate access tokens on each request. Extract user info from token. Support multiple OAuth2 providers.

```java
// Add dependency: spring-boot-starter-oauth2-resource-server

// application.yml
// spring:
//   security:
//     oauth2:
//       resourceserver:
//         jwt:
//           issuer-uri: https://accounts.google.com

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        
        return http.build();
    }
}

// Extract user info from JWT
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
            "username", jwt.getSubject(),
            "email", jwt.getClaim("email"),
            "roles", jwt.getClaim("roles")
        );
    }
}

// OAuth2 client configuration for login
@Configuration
public class OAuth2ClientConfig {
    
    @Bean
    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth2 -> oauth2
            .loginPage("/login")
            .defaultSuccessUrl("/home")
        );
        return http.build();
    }
}

// Custom JWT converter
@Bean
public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
}
```

### 69. How would you implement password encryption and validation?

Use BCryptPasswordEncoder from Spring Security. Encode password before saving. Spring Security automatically validates during authentication. Never store plain text passwords. Use strong work factor for BCrypt.

```java
@Configuration
public class PasswordConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Work factor 12
    }
}

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User registerUser(UserRegistrationRequest request) {
        // Encode password before saving
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }
    
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow();
        
        // Validate old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid old password");
        }
        
        // Encode and save new password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

// Password validation rules
public class PasswordValidator {
    
    public static boolean isValid(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(ch -> "!@#$%^&*".indexOf(ch) >= 0);
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}

// Custom validator annotation
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StrongPasswordValidator.class)
public @interface StrongPassword {
    String message() default "Password must be at least 8 characters with uppercase, lowercase, digit, and special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return PasswordValidator.isValid(password);
    }
}
```

### 70. You need to prevent SQL injection and XSS attacks. What measures do you take?

Use parameterized queries or JPA - never concatenate SQL. Use Bean Validation to sanitize inputs. Encode output in views. Use Content Security Policy headers. Enable Spring Security's XSS protection. Validate and sanitize all user inputs.

```java
// SQL Injection Prevention - Use JPA or parameterized queries
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Safe - uses parameterized query
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
    
    // Also safe - Spring Data generates parameterized query
    Optional<User> findByEmail(String email);
}

// Never do this - SQL injection vulnerable
// String sql = "SELECT * FROM users WHERE username = '" + username + "'";

// Input validation
public class UserRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$", message = "Invalid username format")
    private String username;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}

// XSS Prevention - Security headers
@Configuration
public class SecurityHeadersConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
            .contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline'")
            )
            .xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
            .frameOptions(frame -> frame.deny())
        );
        return http.build();
    }
}

// Input sanitization
@Component
public class InputSanitizer {
    
    public String sanitize(String input) {
        if (input == null) return null;
        
        // Remove HTML tags
        return input.replaceAll("<[^>]*>", "")
                   .replaceAll("&", "&amp;")
                   .replaceAll("<", "&lt;")
                   .replaceAll(">", "&gt;")
                   .replaceAll("\"", "&quot;")
                   .replaceAll("'", "&#x27;");
    }
}

// Use OWASP Java Encoder for output encoding
// Add dependency: owasp-java-encoder
@RestController
public class SafeController {
    
    @GetMapping("/display")
    public String displayData(@RequestParam String data) {
        // Encode for HTML context
        return Encode.forHtml(data);
    }
}

// CSRF Protection - enabled by default in Spring Security
@Configuration
public class CsrfConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        );
        return http.build();
    }
}
```

### 71. How would you implement refresh token mechanism?

Generate access token (short-lived) and refresh token (long-lived) on login. Store refresh token in database. When access token expires, client sends refresh token to get new access token. Validate refresh token and issue new access token. Implement token rotation for security.

```java
@Entity
public class RefreshToken {
    @Id
    private String token;
    
    @ManyToOne
    private User user;
    
    private LocalDateTime expiryDate;
    private boolean revoked;
}

@Service
public class RefreshTokenService {
    
    private final RefreshTokenRepository refreshTokenRepository;
    
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(30));
        refreshToken.setRevoked(false);
        
        return refreshTokenRepository.save(refreshToken);
    }
    
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token expired");
        }
        return token;
    }
    
    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token)
            .ifPresent(rt -> {
                rt.setRevoked(true);
                refreshTokenRepository.save(rt);
            });
    }
}

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        User user = userService.findByUsername(request.getUsername());
        String accessToken = jwtUtil.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        
        return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
                String newAccessToken = jwtUtil.generateToken(user);
                
                // Optional: Rotate refresh token
                refreshTokenService.revokeToken(requestRefreshToken);
                RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user);
                
                return ResponseEntity.ok(new TokenRefreshResponse(
                    newAccessToken, 
                    newRefreshToken.getToken()
                ));
            })
            .orElseThrow(() -> new TokenRefreshException("Invalid refresh token"));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        refreshTokenService.revokeToken(request.getRefreshToken());
        return ResponseEntity.ok().build();
    }
}
```

### 72. You need to implement method-level security. How do you approach this?

Use @PreAuthorize, @PostAuthorize, or @Secured annotations on methods. Enable method security with @EnableMethodSecurity. Use SpEL expressions for complex conditions. Can check roles, permissions, or custom logic.

```java
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig {
}

@Service
public class OrderService {
    
    // Check role
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    // Check multiple roles
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void approveOrder(Long orderId) {
        orderRepository.updateStatus(orderId, "APPROVED");
    }
    
    // Check if user owns the resource
    @PreAuthorize("#userId == authentication.principal.id")
    public Order getUserOrder(Long userId, Long orderId) {
        return orderRepository.findByUserIdAndOrderId(userId, orderId);
    }
    
    // Custom permission check
    @PreAuthorize("@orderSecurityService.canAccessOrder(#orderId, authentication)")
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
    
    // Post-authorization - check after method execution
    @PostAuthorize("returnObject.userId == authentication.principal.id")
    public Order getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
    
    // Using @Secured
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}

// Custom security service
@Service
public class OrderSecurityService {
    
    public boolean canAccessOrder(Long orderId, Authentication authentication) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return false;
        
        String username = authentication.getName();
        return order.getUser().getUsername().equals(username) ||
               authentication.getAuthorities().stream()
                   .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}

// Complex SpEL expressions
@Service
public class DocumentService {
    
    @PreAuthorize("hasRole('ADMIN') or " +
                  "(hasRole('USER') and #document.owner == authentication.name)")
    public void updateDocument(Document document) {
        documentRepository.save(document);
    }
    
    @PreAuthorize("@documentSecurityService.hasPermission(#docId, 'READ')")
    public Document readDocument(Long docId) {
        return documentRepository.findById(docId).orElseThrow();
    }
}
```

### 73. How would you implement API key-based authentication?

Create API key entity and repository. Generate unique keys for users. Add filter to validate API key from header. Store keys securely hashed. Support key rotation and expiration.

```java
@Entity
public class ApiKey {
    @Id
    private String keyHash;
    
    @ManyToOne
    private User user;
    
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean active;
}

@Service
public class ApiKeyService {
    
    private final ApiKeyRepository apiKeyRepository;
    private final PasswordEncoder passwordEncoder;
    
    public String generateApiKey(User user, String keyName) {
        String rawKey = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        String keyHash = passwordEncoder.encode(rawKey);
        
        ApiKey apiKey = new ApiKey();
        apiKey.setKeyHash(keyHash);
        apiKey.setUser(user);
        apiKey.setName(keyName);
        apiKey.setCreatedAt(LocalDateTime.now());
        apiKey.setExpiresAt(LocalDateTime.now().plusYears(1));
        apiKey.setActive(true);
        
        apiKeyRepository.save(apiKey);
        
        return rawKey; // Return only once, never stored
    }
    
    public Optional<User> validateApiKey(String rawKey) {
        List<ApiKey> apiKeys = apiKeyRepository.findAllActive();
        
        for (ApiKey apiKey : apiKeys) {
            if (passwordEncoder.matches(rawKey, apiKey.getKeyHash())) {
                if (apiKey.getExpiresAt().isAfter(LocalDateTime.now())) {
                    return Optional.of(apiKey.getUser());
                }
            }
        }
        return Optional.empty();
    }
}

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    
    private final ApiKeyService apiKeyService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain chain) throws ServletException, IOException {
        
        String apiKey = request.getHeader("X-API-Key");
        
        if (apiKey != null) {
            Optional<User> user = apiKeyService.validateApiKey(apiKey);
            
            if (user.isPresent()) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    user.get(), null, user.get().getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
}

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ApiKeyAuthenticationFilter apiKeyFilter) throws Exception {
        http.addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}

// API Key management controller
@RestController
@RequestMapping("/api/keys")
public class ApiKeyController {
    
    private final ApiKeyService apiKeyService;
    
    @PostMapping
    public ResponseEntity<ApiKeyResponse> createApiKey(@RequestBody ApiKeyRequest request) {
        User user = getCurrentUser();
        String apiKey = apiKeyService.generateApiKey(user, request.getName());
        
        return ResponseEntity.ok(new ApiKeyResponse(apiKey, "Store this key securely. It won't be shown again."));
    }
    
    @DeleteMapping("/{keyId}")
    public ResponseEntity<Void> revokeApiKey(@PathVariable Long keyId) {
        apiKeyService.revokeKey(keyId);
        return ResponseEntity.ok().build();
    }
}
```

## Performance Optimization

### 74. Your application has slow response times. How do you identify bottlenecks?

Use Spring Boot Actuator for metrics. Enable logging for slow queries. Use profiling tools like JProfiler or VisualVM. Monitor with APM tools like New Relic or Dynatrace. Check database query execution times. Look at thread dumps for blocking operations. Use @Timed annotation to measure method execution.

```java
// Enable Actuator
// Add dependency: spring-boot-starter-actuator

// application.properties
// management.endpoints.web.exposure.include=*
// management.endpoint.health.show-details=always
// spring.jpa.show-sql=true
// logging.level.org.hibernate.SQL=DEBUG
// logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

// Custom metrics with Micrometer
@Service
public class OrderService {
    
    private final MeterRegistry meterRegistry;
    
    @Timed(value = "order.processing.time", description = "Time to process order")
    public Order processOrder(OrderRequest request) {
        Timer.Sample sample = Timer.start(meterRegistry);
        
        try {
            Order order = createOrder(request);
            return order;
        } finally {
            sample.stop(meterRegistry.timer("order.processing"));
        }
    }
    
    public void trackOrderCount() {
        meterRegistry.counter("orders.created").increment();
    }
}

// Logging slow queries
@Component
public class QueryPerformanceInterceptor extends EmptyInterceptor {
    
    private static final long SLOW_QUERY_THRESHOLD = 1000; // 1 second
    
    @Override
    public String onPrepareStatement(String sql) {
        long start = System.currentTimeMillis();
        return sql;
    }
}

// Monitor with custom health indicator
@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    
    private final DataSource dataSource;
    
    @Override
    public Health health() {
        try (Connection conn = dataSource.getConnection()) {
            long start = System.currentTimeMillis();
            conn.createStatement().execute("SELECT 1");
            long duration = System.currentTimeMillis() - start;
            
            if (duration > 1000) {
                return Health.down()
                    .withDetail("responseTime", duration + "ms")
                    .build();
            }
            
            return Health.up()
                .withDetail("responseTime", duration + "ms")
                .build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}

// Profile with @Async monitoring
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }
}
```

### 75. You need to implement caching to improve performance. What strategies would you use?

Use Spring Cache with @Cacheable annotation. Configure cache provider like Redis or Caffeine. Set appropriate TTL for cache entries. Use @CacheEvict to clear cache on updates. Implement cache warming for frequently accessed data. Use different cache strategies based on data access patterns.

```java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("users", "products");
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(1000));
        return cacheManager;
    }
}

@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    @Cacheable(value = "users", key = "#username")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
    
    @CachePut(value = "users", key = "#user.id")
    public User update(User user) {
        return userRepository.save(user);
    }
    
    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    @CacheEvict(value = "users", allEntries = true)
    public void clearCache() {
        // Clears all user cache
    }
}

// Redis cache configuration
@Configuration
public class RedisCacheConfig {
    
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeKeysWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .build();
    }
}

// Cache warming on startup
@Component
public class CacheWarmer implements ApplicationRunner {
    
    private final UserService userService;
    
    @Override
    public void run(ApplicationArguments args) {
        // Load frequently accessed data into cache
        List<User> activeUsers = userRepository.findActiveUsers();
        activeUsers.forEach(user -> userService.findById(user.getId()));
    }
}

// Conditional caching
@Service
public class ProductService {
    
    @Cacheable(value = "products", key = "#id", unless = "#result == null")
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    @Cacheable(value = "products", condition = "#price > 100")
    public List<Product> findByPrice(double price) {
        return productRepository.findByPrice(price);
    }
}
```

### 76. How would you optimize database queries for better performance?

Add indexes on frequently queried columns. Use DTO projections to fetch only needed fields. Avoid N+1 queries with JOIN FETCH or @EntityGraph. Use pagination for large result sets. Optimize with query hints. Use database query plan analyzer. Batch operations when possible.

```java
// Add indexes
@Entity
@Table(indexes = {
    @Index(name = "idx_email", columnList = "email"),
    @Index(name = "idx_status_created", columnList = "status, created_at")
})
public class User {
    @Id
    private Long id;
    
    @Column(unique = true)
    private String email;
    
    private String status;
    private LocalDateTime createdAt;
}

// DTO projection - fetch only needed fields
@Query("SELECT new com.example.dto.UserDTO(u.id, u.name, u.email) FROM User u WHERE u.status = :status")
List<UserDTO> findActiveUsers(@Param("status") String status);

// Interface projection
public interface UserSummary {
    Long getId();
    String getName();
    String getEmail();
}

@Query("SELECT u.id as id, u.name as name, u.email as email FROM User u")
List<UserSummary> findAllSummaries();

// Avoid N+1 with JOIN FETCH
@Query("SELECT u FROM User u JOIN FETCH u.orders WHERE u.id = :id")
Optional<User> findByIdWithOrders(@Param("id") Long id);

// Use pagination
@Query("SELECT u FROM User u WHERE u.status = :status")
Page<User> findByStatus(@Param("status") String status, Pageable pageable);

// Query hints for optimization
@QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
@Query("SELECT u FROM User u")
List<User> findAllOptimized();

// Batch fetching
@Entity
public class Order {
    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private User user;
}

// Native query for complex operations
@Query(value = "SELECT * FROM users u WHERE u.created_at > :date " +
               "AND EXISTS (SELECT 1 FROM orders o WHERE o.user_id = u.id) " +
               "LIMIT :limit", nativeQuery = true)
List<User> findActiveUsersWithOrders(@Param("date") LocalDate date, @Param("limit") int limit);
```

### 77. Your application uses too much memory. How do you optimize it?

Use primitive types instead of wrappers. Implement lazy loading for associations. Use weak references for caches. Process large datasets in batches. Avoid keeping large objects in memory. Use streaming for file processing. Monitor heap usage with profilers. Tune JVM parameters.

```java
// Use primitives instead of wrappers
public class Statistics {
    private int count;        // Instead of Integer
    private long total;       // Instead of Long
    private double average;   // Instead of Double
}

// Lazy loading
@Entity
public class User {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profileImage;
}

// Process in batches
@Service
public class BatchProcessor {
    
    @Transactional
    public void processLargeDataset() {
        int pageSize = 100;
        int page = 0;
        
        Page<User> userPage;
        do {
            userPage = userRepository.findAll(PageRequest.of(page++, pageSize));
            userPage.getContent().forEach(this::processUser);
            
            // Clear persistence context to free memory
            entityManager.flush();
            entityManager.clear();
        } while (userPage.hasNext());
    }
}

// Use WeakHashMap for caches
public class CacheService {
    private final Map<String, Object> cache = new WeakHashMap<>();
    
    public void put(String key, Object value) {
        cache.put(key, value);
    }
}

// Stream large files
public void processLargeFile(String filename) {
    try (Stream<String> lines = Files.lines(Paths.get(filename))) {
        lines.filter(line -> line.contains("ERROR"))
             .forEach(this::processLine);
    }
}

// Limit collection sizes
@Entity
public class User {
    @OneToMany
    @Size(max = 100)
    private List<Order> recentOrders;
}

// JVM tuning in application.properties or startup
// -Xms512m -Xmx2g
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200
```

### 78. How would you optimize Spring Boot startup time?

Use lazy initialization. Exclude unnecessary auto-configurations. Use spring-context-indexer for faster component scanning. Reduce number of beans. Use @Lazy on heavy beans. Profile startup with spring.jmx.enabled. Use native image with GraalVM for faster startup.

```java
// Enable lazy initialization
// application.properties
// spring.main.lazy-initialization=true

// Or programmatically
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setLazyInitialization(true);
        app.run(args);
    }
}

// Exclude unnecessary auto-configurations
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
public class Application {
}

// Use @Lazy on heavy beans
@Configuration
public class AppConfig {
    
    @Bean
    @Lazy
    public ExpensiveService expensiveService() {
        return new ExpensiveService();
    }
}

// Add spring-context-indexer dependency for faster scanning
// <dependency>
//   <groupId>org.springframework</groupId>
//   <artifactId>spring-context-indexer</artifactId>
// </dependency>

// Reduce component scanning scope
@SpringBootApplication(scanBasePackages = "com.example.core")
public class Application {
}

// Use @ComponentScan with specific packages
@Configuration
@ComponentScan(basePackages = {"com.example.service", "com.example.repository"})
public class AppConfig {
}

// Conditional bean creation
@Configuration
public class ConditionalConfig {
    
    @Bean
    @ConditionalOnProperty(name = "feature.enabled", havingValue = "true")
    public FeatureService featureService() {
        return new FeatureService();
    }
}

// Profile startup time
// application.properties
// spring.jmx.enabled=true
// logging.level.org.springframework.boot.autoconfigure=DEBUG
```

### 79. You need to handle high concurrent requests. What optimizations would you implement?

Use async processing with @Async. Configure thread pool size appropriately. Use connection pooling for database. Implement caching. Use non-blocking I/O with WebFlux. Add load balancing. Use CDN for static content. Implement rate limiting.

```java
// Configure thread pool
@Configuration
@EnableAsync
public class AsyncConfig {
    
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("async-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}

// Async processing
@Service
public class OrderService {
    
    @Async
    public CompletableFuture<Order> processOrder(OrderRequest request) {
        Order order = createOrder(request);
        return CompletableFuture.completedFuture(order);
    }
}

// Connection pooling
// application.properties
// spring.datasource.hikari.maximum-pool-size=20
// spring.datasource.hikari.minimum-idle=10
// spring.datasource.hikari.connection-timeout=30000

// Use WebFlux for reactive non-blocking
@RestController
@RequestMapping("/api/reactive")
public class ReactiveController {
    
    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
}

// Implement caching
@Cacheable("users")
public User findById(Long id) {
    return userRepository.findById(id).orElseThrow();
}

// Rate limiting
@Component
public class RateLimitFilter extends OncePerRequestFilter {
    
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain chain) throws ServletException, IOException {
        
        String key = request.getRemoteAddr();
        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket());
        
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(429);
        }
    }
    
    private Bucket createBucket() {
        return Bucket.builder()
            .addLimit(Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1))))
            .build();
    }
}

// Optimize Tomcat for high concurrency
// application.properties
// server.tomcat.threads.max=200
// server.tomcat.threads.min-spare=20
// server.tomcat.accept-count=100
// server.tomcat.max-connections=10000
```

### 80. How would you implement lazy loading to improve performance?

Use FetchType.LAZY for associations. Use @Basic(fetch = LAZY) for large fields. Load data only when accessed. Use DTO projections to avoid loading unnecessary data. Implement pagination for large collections.

```java
// Lazy loading associations
@Entity
public class User {
    @Id
    private Long id;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}

// Lazy loading large fields
@Entity
public class Document {
    @Id
    private Long id;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String description;
}

// Load only when needed
@Service
public class UserService {
    
    @Transactional
    public void processUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        
        // Orders loaded only when accessed
        if (user.getOrders().size() > 0) {
            processOrders(user.getOrders());
        }
    }
}

// Use DTO to avoid lazy loading
@Query("SELECT new com.example.dto.UserDTO(u.id, u.name) FROM User u")
List<UserDTO> findAllUsers();

// Lazy initialization in Spring
@Component
@Lazy
public class HeavyService {
    
    public HeavyService() {
        // Expensive initialization
    }
}

// Lazy bean injection
@Service
public class MyService {
    
    @Lazy
    @Autowired
    private HeavyService heavyService;
}

// Pagination for large collections
@GetMapping("/users")
public Page<User> getUsers(@PageableDefault(size = 20) Pageable pageable) {
    return userRepository.findAll(pageable);
}
```

### 81. How would you implement asynchronous processing for long-running tasks?

Use @Async annotation for async methods. Return CompletableFuture for results. Configure thread pool executor. Use message queues for decoupled processing. Implement job status tracking. Handle exceptions properly in async methods.

```java
@Configuration
@EnableAsync
public class AsyncConfig {
    
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-task-");
        executor.initialize();
        return executor;
    }
}

@Service
public class ReportService {
    
    @Async("taskExecutor")
    public CompletableFuture<Report> generateReport(ReportRequest request) {
        try {
            Report report = processReport(request);
            return CompletableFuture.completedFuture(report);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
    
    @Async
    public void sendEmail(String to, String subject, String body) {
        emailService.send(to, subject, body);
    }
}

// Job status tracking
@Entity
public class Job {
    @Id
    private String id;
    private String status; // PENDING, PROCESSING, COMPLETED, FAILED
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private String result;
}

@Service
public class JobService {
    
    public String submitJob(JobRequest request) {
        String jobId = UUID.randomUUID().toString();
        
        Job job = new Job();
        job.setId(jobId);
        job.setStatus("PENDING");
        jobRepository.save(job);
        
        processJobAsync(jobId, request);
        
        return jobId;
    }
    
    @Async
    public void processJobAsync(String jobId, JobRequest request) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        job.setStatus("PROCESSING");
        job.setStartedAt(LocalDateTime.now());
        jobRepository.save(job);
        
        try {
            String result = performLongRunningTask(request);
            
            job.setStatus("COMPLETED");
            job.setResult(result);
        } catch (Exception e) {
            job.setStatus("FAILED");
            job.setResult(e.getMessage());
        } finally {
            job.setCompletedAt(LocalDateTime.now());
            jobRepository.save(job);
        }
    }
    
    public Job getJobStatus(String jobId) {
        return jobRepository.findById(jobId).orElseThrow();
    }
}

// Using message queue for async processing
@Service
public class OrderProcessingService {
    
    private final RabbitTemplate rabbitTemplate;
    
    public void submitOrder(Order order) {
        rabbitTemplate.convertAndSend("order-queue", order);
    }
    
    @RabbitListener(queues = "order-queue")
    public void processOrder(Order order) {
        // Long-running processing
        processOrderLogic(order);
    }
}

// Controller for async operations
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    
    @PostMapping
    public ResponseEntity<JobResponse> submitJob(@RequestBody JobRequest request) {
        String jobId = jobService.submitJob(request);
        return ResponseEntity.accepted()
            .body(new JobResponse(jobId, "Job submitted"));
    }
    
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobStatus(@PathVariable String jobId) {
        Job job = jobService.getJobStatus(jobId);
        return ResponseEntity.ok(job);
    }
}
```

## Testing

### 82. You need to write unit tests for a service with multiple dependencies. How do you structure your tests?

Use JUnit 5 and Mockito. Mock dependencies with @Mock. Inject mocks with @InjectMocks. Test business logic in isolation. Use when-then for mock behavior. Verify method calls. Test both success and failure scenarios. Follow AAA pattern - Arrange, Act, Assert.

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldCreateUser() {
        // Arrange
        User user = new User("john@example.com", "John");
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        // Act
        User result = userService.createUser(user);
        
        // Assert
        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(userRepository).save(user);
        verify(emailService).sendWelcomeEmail(user.getEmail());
    }
    
    @Test
    void shouldThrowExceptionWhenUserExists() {
        // Arrange
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);
        
        // Act & Assert
        assertThrows(DuplicateUserException.class, () -> 
            userService.createUser(new User("john@example.com", "John"))
        );
        
        verify(userRepository, never()).save(any());
    }
    
    @Test
    void shouldReturnUserById() {
        // Arrange
        User user = new User(1L, "john@example.com", "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        // Act
        User result = userService.findById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
    
    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> 
            userService.findById(999L)
        );
    }
}

// Test with ArgumentCaptor
@Test
void shouldSaveUserWithCorrectData() {
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    
    userService.createUser(new User("john@example.com", "John"));
    
    verify(userRepository).save(userCaptor.capture());
    User savedUser = userCaptor.getValue();
    assertEquals("john@example.com", savedUser.getEmail());
    assertNotNull(savedUser.getCreatedAt());
}
```

### 83. How would you test REST API endpoints comprehensively?

Use @SpringBootTest with MockMvc or TestRestTemplate. Test all HTTP methods. Verify status codes, response body, and headers. Test validation errors. Test authentication and authorization. Use @AutoConfigureMockMvc for MockMvc setup.

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void shouldGetUserById() throws Exception {
        User user = new User(1L, "john@example.com", "John");
        when(userService.findById(1L)).thenReturn(user);
        
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.name").value("John"));
    }
    
    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        when(userService.findById(999L)).thenThrow(new UserNotFoundException("User not found"));
        
        mockMvc.perform(get("/api/users/999"))
            .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldCreateUser() throws Exception {
        UserRequest request = new UserRequest("john@example.com", "John");
        User user = new User(1L, "john@example.com", "John");
        when(userService.createUser(any())).thenReturn(user);
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }
    
    @Test
    void shouldReturnValidationErrors() throws Exception {
        UserRequest request = new UserRequest("", ""); // Invalid
        
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.email").exists())
            .andExpect(jsonPath("$.errors.name").exists());
    }
    
    @Test
    void shouldUpdateUser() throws Exception {
        UserRequest request = new UserRequest("john@example.com", "John Updated");
        User user = new User(1L, "john@example.com", "John Updated");
        when(userService.update(eq(1L), any())).thenReturn(user);
        
        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Updated"));
    }
    
    @Test
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
            .andExpect(status().isNoContent());
        
        verify(userService).delete(1L);
    }
}

// Using TestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldGetUser() {
        ResponseEntity<User> response = restTemplate.getForEntity("/api/users/1", User.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }
    
    @Test
    void shouldCreateUser() {
        UserRequest request = new UserRequest("john@example.com", "John");
        
        ResponseEntity<User> response = restTemplate.postForEntity(
            "/api/users", request, User.class
        );
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
```

### 84. You need to test database operations. How do you set up test data?

Use @DataJpaTest for repository tests. Use in-memory H2 database. Create test data in @BeforeEach. Use @Sql to load SQL scripts. Use TestEntityManager for JPA operations. Clean up after tests.

```java
@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TestEntityManager entityManager;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        testUser = new User("john@example.com", "John");
        entityManager.persist(testUser);
        entityManager.flush();
    }
    
    @Test
    void shouldFindUserByEmail() {
        Optional<User> found = userRepository.findByEmail("john@example.com");
        
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getName());
    }
    
    @Test
    void shouldSaveUser() {
        User newUser = new User("jane@example.com", "Jane");
        
        User saved = userRepository.save(newUser);
        
        assertNotNull(saved.getId());
        assertEquals("jane@example.com", saved.getEmail());
    }
    
    @Test
    void shouldDeleteUser() {
        userRepository.deleteById(testUser.getId());
        
        Optional<User> found = userRepository.findById(testUser.getId());
        assertFalse(found.isPresent());
    }
    
    @Test
    void shouldFindUsersByStatus() {
        User activeUser = new User("active@example.com", "Active");
        activeUser.setStatus("ACTIVE");
        entityManager.persist(activeUser);
        
        List<User> activeUsers = userRepository.findByStatus("ACTIVE");
        
        assertEquals(1, activeUsers.size());
        assertEquals("ACTIVE", activeUsers.get(0).getStatus());
    }
}

// Using @Sql to load test data
@DataJpaTest
class UserRepositoryWithSqlTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @Sql("/test-data.sql")
    void shouldFindUsersFromSqlScript() {
        List<User> users = userRepository.findAll();
        
        assertEquals(3, users.size());
    }
    
    @Test
    @Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldCleanupAfterTest() {
        List<User> users = userRepository.findAll();
        assertEquals(3, users.size());
    }
}

// test-data.sql
// INSERT INTO users (email, name, status) VALUES ('user1@example.com', 'User 1', 'ACTIVE');
// INSERT INTO users (email, name, status) VALUES ('user2@example.com', 'User 2', 'ACTIVE');
// INSERT INTO users (email, name, status) VALUES ('user3@example.com', 'User 3', 'INACTIVE');

// application-test.properties
// spring.datasource.url=jdbc:h2:mem:testdb
// spring.jpa.hibernate.ddl-auto=create-drop
```

### 85. How would you implement integration tests for Spring Boot applications?

Use @SpringBootTest to load full application context. Use @TestConfiguration for test-specific beans. Use Testcontainers for real database. Test complete flows. Use profiles for test configuration. Mock external services.

```java
@SpringBootTest
@ActiveProfiles("test")
class OrderServiceIntegrationTest {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @MockBean
    private PaymentService paymentService;
    
    @Test
    void shouldCreateOrderSuccessfully() {
        // Arrange
        OrderRequest request = new OrderRequest("user@example.com", 100.0);
        when(paymentService.processPayment(any())).thenReturn(true);
        
        // Act
        Order order = orderService.createOrder(request);
        
        // Assert
        assertNotNull(order.getId());
        assertEquals("COMPLETED", order.getStatus());
        
        // Verify database
        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow();
        assertEquals(100.0, savedOrder.getAmount());
    }
}

// Using Testcontainers
@SpringBootTest
@Testcontainers
class OrderServiceWithTestcontainersTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    
    @Autowired
    private OrderService orderService;
    
    @Test
    void shouldProcessOrderWithRealDatabase() {
        OrderRequest request = new OrderRequest("user@example.com", 100.0);
        
        Order order = orderService.createOrder(request);
        
        assertNotNull(order.getId());
    }
}

// Test configuration
@TestConfiguration
public class TestConfig {
    
    @Bean
    @Primary
    public EmailService emailService() {
        return mock(EmailService.class);
    }
}

// Integration test with multiple layers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FullIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;
    
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }
    
    @Test
    void shouldCreateAndRetrieveUser() throws Exception {
        // Create user
        UserRequest request = new UserRequest("john@example.com", "John");
        
        MvcResult createResult = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andReturn();
        
        String response = createResult.getResponse().getContentAsString();
        Long userId = JsonPath.read(response, "$.id");
        
        // Retrieve user
        mockMvc.perform(get("/api/users/" + userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("john@example.com"));
        
        // Verify in database
        User user = userRepository.findById(userId).orElseThrow();
        assertEquals("John", user.getName());
    }
}
```

### 86. You need to test external API integrations. How do you mock them?

Use WireMock or MockServer for HTTP mocking. Use @MockBean for service mocking. Create stub responses. Test error scenarios. Use RestTemplate or WebClient test utilities.

```java
@SpringBootTest
@AutoConfigureWireMock(port = 0)
class ExternalApiServiceTest {
    
    @Autowired
    private ExternalApiService externalApiService;
    
    @Value("${wiremock.server.port}")
    private int wireMockPort;
    
    @Test
    void shouldCallExternalApiSuccessfully() {
        // Setup WireMock stub
        stubFor(get(urlEqualTo("/api/users/1"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\":1,\"name\":\"John\"}")));
        
        // Call service
        ExternalUser user = externalApiService.getUser(1L);
        
        // Verify
        assertNotNull(user);
        assertEquals("John", user.getName());
        
        // Verify WireMock was called
        verify(getRequestedFor(urlEqualTo("/api/users/1")));
    }
    
    @Test
    void shouldHandleExternalApiError() {
        // Setup error response
        stubFor(get(urlEqualTo("/api/users/999"))
            .willReturn(aResponse()
                .withStatus(404)));
        
        // Verify exception handling
        assertThrows(ExternalApiException.class, () -> 
            externalApiService.getUser(999L)
        );
    }
    
    @Test
    void shouldHandleTimeout() {
        // Setup delayed response
        stubFor(get(urlEqualTo("/api/users/1"))
            .willReturn(aResponse()
                .withStatus(200)
                .withFixedDelay(5000)));
        
        assertThrows(TimeoutException.class, () -> 
            externalApiService.getUser(1L)
        );
    }
}

// Using MockBean
@SpringBootTest
class OrderServiceWithMockedExternalApiTest {
    
    @Autowired
    private OrderService orderService;
    
    @MockBean
    private PaymentGatewayClient paymentGatewayClient;
    
    @Test
    void shouldProcessPaymentSuccessfully() {
        // Mock external API response
        PaymentResponse mockResponse = new PaymentResponse("SUCCESS", "txn123");
        when(paymentGatewayClient.processPayment(any())).thenReturn(mockResponse);
        
        // Test
        Order order = orderService.createOrder(new OrderRequest(100.0));
        
        assertEquals("PAID", order.getStatus());
        verify(paymentGatewayClient).processPayment(any());
    }
    
    @Test
    void shouldHandlePaymentFailure() {
        // Mock failure
        when(paymentGatewayClient.processPayment(any()))
            .thenThrow(new PaymentException("Payment failed"));
        
        assertThrows(OrderProcessingException.class, () -> 
            orderService.createOrder(new OrderRequest(100.0))
        );
    }
}

// RestTemplate test with MockRestServiceServer
@SpringBootTest
class RestTemplateServiceTest {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ExternalApiService externalApiService;
    
    private MockRestServiceServer mockServer;
    
    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }
    
    @Test
    void shouldCallExternalApi() {
        mockServer.expect(requestTo("http://api.example.com/users/1"))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("{\"id\":1,\"name\":\"John\"}", MediaType.APPLICATION_JSON));
        
        ExternalUser user = externalApiService.getUser(1L);
        
        assertEquals("John", user.getName());
        mockServer.verify();
    }
}
```

### 87. How would you test async methods and scheduled tasks?

Use @Async with CompletableFuture and test with get() or join(). Use Awaitility library for waiting. Test scheduled tasks by triggering manually. Mock time with Clock. Verify async execution.

```java
@SpringBootTest
class AsyncServiceTest {
    
    @Autowired
    private AsyncService asyncService;
    
    @Test
    void shouldExecuteAsyncMethod() throws Exception {
        CompletableFuture<String> future = asyncService.processAsync("test");
        
        // Wait for completion
        String result = future.get(5, TimeUnit.SECONDS);
        
        assertEquals("Processed: test", result);
    }
    
    @Test
    void shouldHandleAsyncException() {
        CompletableFuture<String> future = asyncService.processAsync("error");
        
        assertThrows(ExecutionException.class, () -> 
            future.get(5, TimeUnit.SECONDS)
        );
    }
}

// Using Awaitility
@SpringBootTest
class AsyncServiceWithAwaitilityTest {
    
    @Autowired
    private AsyncService asyncService;
    
    @Autowired
    private ResultRepository resultRepository;
    
    @Test
    void shouldProcessAsyncAndSaveResult() {
        asyncService.processAndSave("test");
        
        // Wait for async processing
        await().atMost(5, TimeUnit.SECONDS)
            .until(() -> resultRepository.findByKey("test").isPresent());
        
        Result result = resultRepository.findByKey("test").orElseThrow();
        assertEquals("Processed: test", result.getValue());
    }
}

// Testing scheduled tasks
@SpringBootTest
class ScheduledTaskTest {
    
    @Autowired
    private ScheduledTaskService scheduledTaskService;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Test
    void shouldExecuteScheduledTask() {
        // Manually trigger scheduled method
        scheduledTaskService.performScheduledTask();
        
        // Verify execution
        List<Task> tasks = taskRepository.findAll();
        assertFalse(tasks.isEmpty());
    }
}

// Testing with mocked time
@SpringBootTest
class TimeBasedServiceTest {
    
    @MockBean
    private Clock clock;
    
    @Autowired
    private TimeBasedService timeBasedService;
    
    @Test
    void shouldProcessBasedOnTime() {
        // Mock specific time
        LocalDateTime fixedTime = LocalDateTime.of(2024, 1, 15, 10, 0);
        when(clock.instant()).thenReturn(fixedTime.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        
        boolean result = timeBasedService.isBusinessHours();
        
        assertTrue(result);
    }
}

// Testing async with verification
@SpringBootTest
class AsyncEmailServiceTest {
    
    @Autowired
    private AsyncEmailService emailService;
    
    @MockBean
    private EmailClient emailClient;
    
    @Test
    void shouldSendEmailAsynchronously() {
        emailService.sendEmailAsync("test@example.com", "Subject", "Body");
        
        // Wait and verify
        await().atMost(3, TimeUnit.SECONDS)
            .untilAsserted(() -> 
                verify(emailClient).send(eq("test@example.com"), anyString(), anyString())
            );
    }
}

// Service with async method
@Service
public class AsyncService {
    
    @Async
    public CompletableFuture<String> processAsync(String data) {
        if ("error".equals(data)) {
            throw new RuntimeException("Processing error");
        }
        return CompletableFuture.completedFuture("Processed: " + data);
    }
    
    @Async
    public void processAndSave(String data) {
        String result = "Processed: " + data;
        resultRepository.save(new Result(data, result));
    }
}

// Scheduled service
@Service
public class ScheduledTaskService {
    
    @Scheduled(fixedRate = 60000)
    public void performScheduledTask() {
        taskRepository.save(new Task("Scheduled task executed"));
    }
}
```

## Microservices Architecture

### 88. You need to implement service-to-service communication. What are your options?

Use REST APIs with RestTemplate or WebClient for synchronous communication. Use message queues like RabbitMQ or Kafka for asynchronous communication. Use gRPC for high-performance communication. Use Feign client for declarative REST calls. Choose based on latency requirements and coupling needs.

```java
// REST with RestTemplate
@Service
public class OrderService {
    
    private final RestTemplate restTemplate;
    
    public User getUserDetails(Long userId) {
        String url = "http://user-service/api/users/" + userId;
        return restTemplate.getForObject(url, User.class);
    }
}

// REST with WebClient (reactive)
@Service
public class OrderService {
    
    private final WebClient webClient;
    
    public Mono<User> getUserDetails(Long userId) {
        return webClient.get()
            .uri("http://user-service/api/users/{id}", userId)
            .retrieve()
            .bodyToMono(User.class);
    }
}

// Feign Client - declarative REST
@FeignClient(name = "user-service")
public interface UserClient {
    
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
    
    @PostMapping("/api/users")
    User createUser(@RequestBody UserRequest request);
}

@Service
public class OrderService {
    
    private final UserClient userClient;
    
    public void processOrder(OrderRequest request) {
        User user = userClient.getUserById(request.getUserId());
        // process order
    }
}

// Async with RabbitMQ
@Service
public class OrderService {
    
    private final RabbitTemplate rabbitTemplate;
    
    public void createOrder(Order order) {
        rabbitTemplate.convertAndSend("order-exchange", "order.created", order);
    }
}

@Service
public class NotificationService {
    
    @RabbitListener(queues = "notification-queue")
    public void handleOrderCreated(Order order) {
        sendNotification(order);
    }
}

// Async with Kafka
@Service
public class OrderService {
    
    private final KafkaTemplate<String, Order> kafkaTemplate;
    
    public void createOrder(Order order) {
        kafkaTemplate.send("order-topic", order);
    }
}

@Service
public class InventoryService {
    
    @KafkaListener(topics = "order-topic", groupId = "inventory-group")
    public void handleOrderCreated(Order order) {
        updateInventory(order);
    }
}
```

### 89. How would you implement service discovery in a microservices architecture?

Use Netflix Eureka for service registry. Services register themselves on startup. Clients discover services through Eureka. Use Spring Cloud LoadBalancer for client-side load balancing. Configure Eureka server and clients. Services communicate using service names instead of hardcoded URLs.

```java
// Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

// application.yml for Eureka Server
// server:
//   port: 8761
// eureka:
//   client:
//     register-with-eureka: false
//     fetch-registry: false

// Eureka Client - User Service
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

// application.yml for User Service
// spring:
//   application:
//     name: user-service
// eureka:
//   client:
//     service-url:
//       defaultZone: http://localhost:8761/eureka/

// Service communication with discovery
@Configuration
public class RestTemplateConfig {
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@Service
public class OrderService {
    
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    
    public User getUserDetails(Long userId) {
        // Uses service name instead of URL
        String url = "http://user-service/api/users/" + userId;
        return restTemplate.getForObject(url, User.class);
    }
}

// Feign with Eureka
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
}

// Health check for Eureka
@RestController
public class HealthController {
    
    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
```

### 90. You need to handle distributed transactions. What patterns would you use?

Use Saga pattern - either choreography (event-based) or orchestration (coordinator-based). Avoid two-phase commit as it's complex and slow. Each service manages its own transaction. Use compensating transactions for rollback. Implement idempotency. Use eventual consistency instead of strong consistency.

```java
// Saga Orchestration Pattern
@Service
public class OrderSagaOrchestrator {
    
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    
    public void createOrder(OrderRequest request) {
        String sagaId = UUID.randomUUID().toString();
        
        try {
            // Step 1: Create order
            Order order = orderService.createOrder(request);
            
            // Step 2: Reserve inventory
            inventoryService.reserveItems(order.getItems());
            
            // Step 3: Process payment
            paymentService.processPayment(order.getAmount());
            
            // Step 4: Confirm order
            orderService.confirmOrder(order.getId());
            
        } catch (Exception e) {
            // Compensating transactions
            compensate(sagaId);
            throw new SagaException("Order creation failed", e);
        }
    }
    
    private void compensate(String sagaId) {
        // Rollback in reverse order
        paymentService.refund(sagaId);
        inventoryService.releaseItems(sagaId);
        orderService.cancelOrder(sagaId);
    }
}

// Saga Choreography Pattern - Event-based
@Service
public class OrderService {
    
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public void createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        
        // Publish event
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getItems());
        kafkaTemplate.send("order-events", event);
    }
    
    @KafkaListener(topics = "payment-events")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus("COMPLETED");
        orderRepository.save(order);
    }
    
    @KafkaListener(topics = "payment-events")
    public void handlePaymentFailed(PaymentFailedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus("CANCELLED");
        orderRepository.save(order);
        
        // Publish compensation event
        kafkaTemplate.send("order-events", new OrderCancelledEvent(order.getId()));
    }
}

@Service
public class InventoryService {
    
    @KafkaListener(topics = "order-events")
    public void handleOrderCreated(OrderCreatedEvent event) {
        try {
            reserveItems(event.getItems());
            kafkaTemplate.send("inventory-events", new InventoryReservedEvent(event.getOrderId()));
        } catch (Exception e) {
            kafkaTemplate.send("inventory-events", new InventoryReservationFailedEvent(event.getOrderId()));
        }
    }
    
    @KafkaListener(topics = "order-events")
    public void handleOrderCancelled(OrderCancelledEvent event) {
        releaseItems(event.getOrderId());
    }
}

// Idempotency for saga operations
@Service
public class PaymentService {
    
    private final Set<String> processedTransactions = new ConcurrentHashMap<>().keySet("");
    
    public void processPayment(String transactionId, double amount) {
        if (processedTransactions.contains(transactionId)) {
            return; // Already processed
        }
        
        // Process payment
        doPayment(amount);
        processedTransactions.add(transactionId);
    }
}
```

### 91. How would you implement API Gateway pattern?

Use Spring Cloud Gateway or Netflix Zuul. Gateway routes requests to appropriate services. Implement authentication, rate limiting, and logging at gateway. Use filters for cross-cutting concerns. Configure routes dynamically or statically.

```java
// Spring Cloud Gateway
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

// application.yml configuration
// spring:
//   cloud:
//     gateway:
//       routes:
//         - id: user-service
//           uri: lb://user-service
//           predicates:
//             - Path=/api/users/**
//         - id: order-service
//           uri: lb://order-service
//           predicates:
//             - Path=/api/orders/**

// Programmatic route configuration
@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r
                .path("/api/users/**")
                .filters(f -> f
                    .addRequestHeader("X-Gateway", "API-Gateway")
                    .circuitBreaker(c -> c.setName("userServiceCB")))
                .uri("lb://user-service"))
            .route("order-service", r -> r
                .path("/api/orders/**")
                .filters(f -> f.rewritePath("/api/orders/(?<segment>.*)", "/${segment}"))
                .uri("lb://order-service"))
            .build();
    }
}

// Custom filter for authentication
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return -1; // High priority
    }
    
    private boolean validateToken(String token) {
        // Validate JWT token
        return true;
    }
}

// Rate limiting filter
@Component
public class RateLimitingFilter implements GlobalFilter {
    
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String clientId = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = buckets.computeIfAbsent(clientId, k -> createBucket());
        
        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }
    
    private Bucket createBucket() {
        return Bucket.builder()
            .addLimit(Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1))))
            .build();
    }
}

// Logging filter
@Component
public class LoggingFilter implements GlobalFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Request: {} {}", 
            exchange.getRequest().getMethod(), 
            exchange.getRequest().getURI());
        
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Response: {}", exchange.getResponse().getStatusCode());
        }));
    }
}
```

### 92. You need to implement circuit breaker for resilient microservices. How do you do this?

Use Resilience4j library. Add @CircuitBreaker annotation on methods calling external services. Configure failure threshold and wait duration. Implement fallback method. Monitor circuit breaker state. Combine with retry and timeout patterns.

```java
// Add dependency: resilience4j-spring-boot2

// application.yml configuration
// resilience4j:
//   circuitbreaker:
//     instances:
//       userService:
//         failure-rate-threshold: 50
//         wait-duration-in-open-state: 10000
//         sliding-window-size: 10
//         minimum-number-of-calls: 5

@Service
public class OrderService {
    
    private final UserClient userClient;
    
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
    public User getUser(Long userId) {
        return userClient.getUserById(userId);
    }
    
    private User getUserFallback(Long userId, Exception e) {
        log.error("Circuit breaker fallback for user: {}", userId, e);
        return new User(userId, "Unknown", "unknown@example.com");
    }
    
    @CircuitBreaker(name = "paymentService", fallbackMethod = "processPaymentFallback")
    @Retry(name = "paymentService")
    public PaymentResponse processPayment(PaymentRequest request) {
        return paymentClient.processPayment(request);
    }
    
    private PaymentResponse processPaymentFallback(PaymentRequest request, Exception e) {
        log.error("Payment processing failed", e);
        return new PaymentResponse("FAILED", "Service temporarily unavailable");
    }
}

// Programmatic circuit breaker
@Service
public class ExternalApiService {
    
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RestTemplate restTemplate;
    
    public String callExternalApi(String url) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("externalApi");
        
        return circuitBreaker.executeSupplier(() -> 
            restTemplate.getForObject(url, String.class)
        );
    }
}

// Circuit breaker with custom configuration
@Configuration
public class CircuitBreakerConfig {
    
    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(10))
            .slidingWindowSize(10)
            .minimumNumberOfCalls(5)
            .build();
        
        return CircuitBreakerRegistry.of(config);
    }
}

// Monitor circuit breaker events
@Component
public class CircuitBreakerEventListener {
    
    @EventListener
    public void onCircuitBreakerEvent(CircuitBreakerOnStateTransitionEvent event) {
        log.info("Circuit breaker {} changed from {} to {}", 
            event.getCircuitBreakerName(),
            event.getStateTransition().getFromState(),
            event.getStateTransition().getToState());
        
        // Send alert if circuit opens
        if (event.getStateTransition().getToState() == CircuitBreaker.State.OPEN) {
            alertService.sendAlert("Circuit breaker opened: " + event.getCircuitBreakerName());
        }
    }
}

// Combine circuit breaker with bulkhead
@Service
public class ResilientService {
    
    @CircuitBreaker(name = "externalService", fallbackMethod = "fallback")
    @Bulkhead(name = "externalService", type = Bulkhead.Type.THREADPOOL)
    @Retry(name = "externalService")
    public String callService(String request) {
        return externalClient.call(request);
    }
    
    private String fallback(String request, Exception e) {
        return "Fallback response";
    }
}
```

### 93. How would you implement event-driven communication between services?

Use message brokers like Kafka or RabbitMQ. Services publish events when state changes. Other services subscribe to relevant events. Use event sourcing for audit trail. Implement idempotency in event handlers. Use dead letter queues for failed messages.

```java
// Kafka event publisher
@Service
public class OrderService {
    
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        
        // Publish event
        OrderCreatedEvent event = new OrderCreatedEvent(
            order.getId(),
            order.getUserId(),
            order.getItems(),
            order.getAmount()
        );
        
        kafkaTemplate.send("order-events", order.getId().toString(), event);
        
        return order;
    }
    
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
        
        // Publish status change event
        OrderStatusChangedEvent event = new OrderStatusChangedEvent(orderId, status);
        kafkaTemplate.send("order-events", orderId.toString(), event);
    }
}

// Kafka event consumer
@Service
public class NotificationService {
    
    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleOrderEvent(OrderCreatedEvent event) {
        sendOrderConfirmationEmail(event.getUserId(), event.getOrderId());
    }
    
    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleStatusChange(OrderStatusChangedEvent event) {
        sendStatusUpdateEmail(event.getOrderId(), event.getStatus());
    }
}

// Inventory service listening to order events
@Service
public class InventoryService {
    
    private final Set<String> processedEvents = ConcurrentHashMap.newKeySet();
    
    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Idempotency check
        String eventId = event.getOrderId() + "-" + event.getTimestamp();
        if (processedEvents.contains(eventId)) {
            log.info("Event already processed: {}", eventId);
            return;
        }
        
        try {
            reserveInventory(event.getItems());
            processedEvents.add(eventId);
            
            // Publish inventory reserved event
            kafkaTemplate.send("inventory-events", 
                new InventoryReservedEvent(event.getOrderId()));
        } catch (Exception e) {
            log.error("Failed to reserve inventory", e);
            kafkaTemplate.send("inventory-events", 
                new InventoryReservationFailedEvent(event.getOrderId()));
        }
    }
}

// RabbitMQ event-driven communication
@Configuration
public class RabbitMQConfig {
    
    @Bean
    public Queue orderQueue() {
        return new Queue("order-queue", true);
    }
    
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("order-exchange");
    }
    
    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueue)
            .to(orderExchange)
            .with("order.*");
    }
}

@Service
public class OrderEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    
    public void publishOrderCreated(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(order);
        rabbitTemplate.convertAndSend("order-exchange", "order.created", event);
    }
}

@Service
public class OrderEventConsumer {
    
    @RabbitListener(queues = "order-queue")
    public void handleOrderEvent(OrderCreatedEvent event) {
        processOrder(event);
    }
}

// Event sourcing pattern
@Service
public class EventStore {
    
    private final EventRepository eventRepository;
    
    public void saveEvent(DomainEvent event) {
        EventEntity entity = new EventEntity(
            event.getAggregateId(),
            event.getEventType(),
            event.getPayload(),
            LocalDateTime.now()
        );
        eventRepository.save(entity);
    }
    
    public List<DomainEvent> getEvents(String aggregateId) {
        return eventRepository.findByAggregateId(aggregateId).stream()
            .map(this::toEvent)
            .collect(Collectors.toList());
    }
}

// Dead letter queue handling
@Service
public class DeadLetterQueueHandler {
    
    @RabbitListener(queues = "order-dlq")
    public void handleFailedMessage(Message message) {
        log.error("Message failed after retries: {}", message);
        // Store for manual review or retry later
        failedMessageRepository.save(new FailedMessage(message));
    }
}
```

## Spring Boot Configuration & Design Patterns

### 94. You need to manage different configurations for different environments. How do you structure this?

Use Spring Profiles with separate property files for each environment. Create application-dev.properties, application-test.properties, application-prod.properties. Set active profile via spring.profiles.active. Use @Profile annotation on beans. Can also use YAML files for better organization.

```java
// application.properties (default)
// spring.application.name=myapp

// application-dev.properties
// spring.datasource.url=jdbc:mysql://localhost:3306/devdb
// spring.datasource.username=devuser
// logging.level.root=DEBUG

// application-test.properties
// spring.datasource.url=jdbc:mysql://testserver:3306/testdb
// spring.datasource.username=testuser
// logging.level.root=INFO

// application-prod.properties
// spring.datasource.url=jdbc:mysql://prodserver:3306/proddb
// spring.datasource.username=produser
// logging.level.root=WARN

// Set active profile
// java -jar app.jar --spring.profiles.active=prod
// Or in application.properties: spring.profiles.active=dev

// Profile-specific beans
@Configuration
@Profile("dev")
public class DevConfig {
    
    @Bean
    public DataSource dataSource() {
        return new H2DataSource(); // In-memory for dev
    }
}

@Configuration
@Profile("prod")
public class ProdConfig {
    
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(); // Production pool
    }
}

// Using YAML for better organization
// application.yml
// spring:
//   profiles:
//     active: dev
//   application:
//     name: myapp
// ---
// spring:
//   config:
//     activate:
//       on-profile: dev
//   datasource:
//     url: jdbc:mysql://localhost:3306/devdb
// ---
// spring:
//   config:
//     activate:
//       on-profile: prod
//   datasource:
//     url: jdbc:mysql://prodserver:3306/proddb

// Multiple profiles
@Component
@Profile({"dev", "test"})
public class NonProdService {
    // Only loaded in dev or test
}
```

### 95. How would you externalize configuration properties?

Use @ConfigurationProperties to bind properties to Java objects. Use @Value for simple properties. Store in application.properties or YAML. Use environment variables for sensitive data. Can use Spring Cloud Config Server for centralized configuration.

```java
// application.properties
// app.name=MyApp
// app.version=1.0
// app.features.email-enabled=true
// app.features.sms-enabled=false
// app.api.timeout=5000
// app.api.retry-count=3

// Using @ConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    
    private String name;
    private String version;
    private Features features;
    private Api api;
    
    // Getters and setters
    
    public static class Features {
        private boolean emailEnabled;
        private boolean smsEnabled;
        // Getters and setters
    }
    
    public static class Api {
        private int timeout;
        private int retryCount;
        // Getters and setters
    }
}

// Using the properties
@Service
public class AppService {
    
    private final AppProperties appProperties;
    
    public void doSomething() {
        if (appProperties.getFeatures().isEmailEnabled()) {
            sendEmail();
        }
    }
}

// Using @Value for simple properties
@Component
public class SimpleConfig {
    
    @Value("${app.name}")
    private String appName;
    
    @Value("${app.api.timeout:3000}") // Default value
    private int timeout;
    
    @Value("${app.features.email-enabled}")
    private boolean emailEnabled;
}

// Environment variables
@Component
public class DatabaseConfig {
    
    @Value("${DB_HOST:localhost}")
    private String dbHost;
    
    @Value("${DB_PORT:3306}")
    private int dbPort;
}

// Validation on configuration properties
@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
public class ValidatedAppProperties {
    
    @NotBlank
    private String name;
    
    @Min(1000)
    @Max(30000)
    private int timeout;
    
    @Email
    private String adminEmail;
}
```

### 96. How would you handle sensitive configuration data (passwords, API keys)?

Use environment variables instead of property files. Use Spring Cloud Config with encryption. Use secrets management tools like AWS Secrets Manager or HashiCorp Vault. Never commit secrets to version control. Use Jasypt for property encryption.

```java
// Using environment variables
@Configuration
public class SecurityConfig {
    
    @Value("${DB_PASSWORD}")
    private String dbPassword;
    
    @Value("${API_KEY}")
    private String apiKey;
    
    @Value("${JWT_SECRET}")
    private String jwtSecret;
}

// Set via environment
// export DB_PASSWORD=secretpass
// export API_KEY=abc123xyz

// Using Jasypt for encryption
// Add dependency: jasypt-spring-boot-starter

// application.properties
// spring.datasource.password=ENC(encrypted_password_here)
// api.key=ENC(encrypted_api_key_here)

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {
    
    @Bean
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getenv("JASYPT_PASSWORD"));
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        return encryptor;
    }
}

// AWS Secrets Manager
@Configuration
public class AwsSecretsConfig {
    
    @Bean
    public AWSSecretsManager secretsManager() {
        return AWSSecretsManagerClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .build();
    }
    
    @Bean
    public String databasePassword(AWSSecretsManager secretsManager) {
        GetSecretValueRequest request = new GetSecretValueRequest()
            .withSecretId("prod/db/password");
        GetSecretValueResult result = secretsManager.getSecretValue(request);
        return result.getSecretString();
    }
}

// HashiCorp Vault
@Configuration
public class VaultConfig {
    
    @Value("${spring.cloud.vault.token}")
    private String vaultToken;
    
    @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint endpoint = VaultEndpoint.create("vault.example.com", 8200);
        VaultTemplate template = new VaultTemplate(endpoint, 
            new TokenAuthentication(vaultToken));
        return template;
    }
    
    @Bean
    public String apiKey(VaultTemplate vaultTemplate) {
        VaultResponse response = vaultTemplate.read("secret/data/api-keys");
        return (String) response.getData().get("api-key");
    }
}

// .gitignore - never commit these
// application-prod.properties
// .env
// secrets.properties
```

### 97. You need to inject configuration values into your code. What are the different approaches?

Use @Value for simple properties. Use @ConfigurationProperties for grouped properties. Use Environment object for programmatic access. Use @PropertySource for custom property files. Constructor injection is preferred over field injection.

```java
// 1. @Value annotation
@Component
public class ServiceA {
    
    @Value("${app.name}")
    private String appName;
    
    @Value("${app.timeout:5000}")
    private int timeout;
    
    @Value("#{${app.features}}")
    private Map<String, Boolean> features;
}

// 2. @ConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private int timeout;
    // Getters and setters
}

@Service
public class ServiceB {
    private final AppConfig appConfig;
    
    public ServiceB(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}

// 3. Environment object
@Component
public class ServiceC {
    
    private final Environment env;
    
    public ServiceC(Environment env) {
        this.env = env;
    }
    
    public void doSomething() {
        String appName = env.getProperty("app.name");
        int timeout = env.getProperty("app.timeout", Integer.class, 5000);
    }
}

// 4. @PropertySource for custom files
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomConfig {
    
    @Value("${custom.property}")
    private String customProperty;
}

// 5. Constructor injection (preferred)
@Service
public class ServiceD {
    
    private final String appName;
    private final int timeout;
    
    public ServiceD(@Value("${app.name}") String appName,
                    @Value("${app.timeout}") int timeout) {
        this.appName = appName;
        this.timeout = timeout;
    }
}

// 6. SpEL expressions
@Component
public class ServiceE {
    
    @Value("#{systemProperties['user.home']}")
    private String userHome;
    
    @Value("#{T(java.lang.Math).random() * 100}")
    private double randomNumber;
    
    @Value("#{appConfig.timeout * 2}")
    private int doubleTimeout;
}
```

## Design Patterns (3 questions)

### 98. You need to create objects with complex initialization. Which pattern would you use?

Use Builder pattern for objects with many optional parameters. Use Factory pattern for creating different types of objects. Use Abstract Factory for families of related objects. Builder is most common for complex initialization.

```java
// Builder Pattern
public class User {
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;
    
    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    
    public static class Builder {
        private final String username;
        private final String email;
        private String firstName;
        private String lastName;
        private String phone;
        private String address;
        
        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }
        
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
}

// Usage
User user = new User.Builder("john", "john@example.com")
    .firstName("John")
    .lastName("Doe")
    .phone("1234567890")
    .build();

// Factory Pattern
public interface Payment {
    void processPayment(double amount);
}

public class CreditCardPayment implements Payment {
    public void processPayment(double amount) {
        // Process credit card
    }
}

public class PayPalPayment implements Payment {
    public void processPayment(double amount) {
        // Process PayPal
    }
}

public class PaymentFactory {
    
    public static Payment createPayment(String type) {
        switch (type) {
            case "CREDIT_CARD":
                return new CreditCardPayment();
            case "PAYPAL":
                return new PayPalPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type");
        }
    }
}

// Usage
Payment payment = PaymentFactory.createPayment("CREDIT_CARD");
payment.processPayment(100.0);

// Using Lombok for Builder
@Builder
public class Product {
    private String name;
    private double price;
    private String category;
    private String description;
}

// Usage with Lombok
Product product = Product.builder()
    .name("Laptop")
    .price(999.99)
    .category("Electronics")
    .build();
```

### 99. How would you implement observer pattern for event handling?

Use Spring's ApplicationEvent and @EventListener. Create custom event classes. Publish events using ApplicationEventPublisher. Listeners automatically receive events. Can make async with @Async. Use for decoupling components.

```java
// Custom event
public class OrderCreatedEvent extends ApplicationEvent {
    private final Order order;
    
    public OrderCreatedEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
    
    public Order getOrder() {
        return order;
    }
}

// Event publisher
@Service
public class OrderService {
    
    private final ApplicationEventPublisher eventPublisher;
    
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        
        // Publish event
        eventPublisher.publishEvent(new OrderCreatedEvent(this, order));
        
        return order;
    }
}

// Event listeners
@Component
public class EmailNotificationListener {
    
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        Order order = event.getOrder();
        sendOrderConfirmationEmail(order);
    }
}

@Component
public class InventoryListener {
    
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        Order order = event.getOrder();
        updateInventory(order.getItems());
    }
}

@Component
public class AnalyticsListener {
    
    @EventListener
    @Async
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Async processing
        trackOrderMetrics(event.getOrder());
    }
}

// Conditional event listening
@Component
public class ConditionalListener {
    
    @EventListener(condition = "#event.order.amount > 1000")
    public void handleLargeOrder(OrderCreatedEvent event) {
        notifyManager(event.getOrder());
    }
}

// Transaction-aware events
@Component
public class TransactionalListener {
    
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Only executed after transaction commits
        sendNotification(event.getOrder());
    }
}

// Generic event listener
@Component
public class GenericEventListener {
    
    @EventListener
    public void handleAnyEvent(ApplicationEvent event) {
        log.info("Event received: {}", event.getClass().getSimpleName());
    }
}
```

### 100. How would you implement strategy pattern for different algorithms?

Create strategy interface with method signature. Implement different strategies as separate classes. Inject appropriate strategy based on context. Use @Qualifier or factory to select strategy. Common for payment processing, pricing, sorting algorithms.

```java
// Strategy interface
public interface PaymentStrategy {
    PaymentResult processPayment(double amount);
}

// Concrete strategies
@Component("creditCard")
public class CreditCardStrategy implements PaymentStrategy {
    
    @Override
    public PaymentResult processPayment(double amount) {
        // Credit card processing logic
        return new PaymentResult("SUCCESS", "CC-" + UUID.randomUUID());
    }
}

@Component("paypal")
public class PayPalStrategy implements PaymentStrategy {
    
    @Override
    public PaymentResult processPayment(double amount) {
        // PayPal processing logic
        return new PaymentResult("SUCCESS", "PP-" + UUID.randomUUID());
    }
}

@Component("crypto")
public class CryptoStrategy implements PaymentStrategy {
    
    @Override
    public PaymentResult processPayment(double amount) {
        // Cryptocurrency processing logic
        return new PaymentResult("SUCCESS", "BTC-" + UUID.randomUUID());
    }
}

// Context using strategy
@Service
public class PaymentService {
    
    private final Map<String, PaymentStrategy> strategies;
    
    public PaymentService(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
    }
    
    public PaymentResult processPayment(String paymentType, double amount) {
        PaymentStrategy strategy = strategies.get(paymentType);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown payment type: " + paymentType);
        }
        return strategy.processPayment(amount);
    }
}

// Using @Qualifier
@Service
public class OrderService {
    
    private final PaymentStrategy paymentStrategy;
    
    public OrderService(@Qualifier("creditCard") PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void processOrder(Order order) {
        PaymentResult result = paymentStrategy.processPayment(order.getAmount());
    }
}

// Factory for strategy selection
@Component
public class PaymentStrategyFactory {
    
    private final Map<String, PaymentStrategy> strategies;
    
    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        this.strategies = strategyList.stream()
            .collect(Collectors.toMap(
                s -> s.getClass().getSimpleName().replace("Strategy", "").toLowerCase(),
                s -> s
            ));
    }
    
    public PaymentStrategy getStrategy(String type) {
        return strategies.get(type.toLowerCase());
    }
}

// Pricing strategy example
public interface PricingStrategy {
    double calculatePrice(Product product, int quantity);
}

@Component
public class RegularPricingStrategy implements PricingStrategy {
    
    @Override
    public double calculatePrice(Product product, int quantity) {
        return product.getPrice() * quantity;
    }
}

@Component
public class BulkDiscountStrategy implements PricingStrategy {
    
    @Override
    public double calculatePrice(Product product, int quantity) {
        double total = product.getPrice() * quantity;
        if (quantity > 10) {
            total *= 0.9; // 10% discount
        }
        return total;
    }
}

@Component
public class SeasonalDiscountStrategy implements PricingStrategy {
    
    @Override
    public double calculatePrice(Product product, int quantity) {
        double total = product.getPrice() * quantity;
        if (isHolidaySeason()) {
            total *= 0.85; // 15% discount
        }
        return total;
    }
}
```
