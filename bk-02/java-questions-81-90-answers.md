## Advanced Collections

### 81. How does HashMap work internally?

HashMap uses a **hash table** with **separate chaining** for collision resolution.

#### **Internal Structure:**
```java
// Simplified HashMap structure
class HashMap<K,V> {
    Node<K,V>[] table;        // Array of buckets
    int size;                 // Number of entries
    int threshold;            // Resize threshold
    float loadFactor;         // Load factor (default 0.75)
    
    static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;       // For chaining
    }
}
```

#### **How put() Works:**
```java
public V put(K key, V value) {
    // 1. Calculate hash
    int hash = hash(key);
    
    // 2. Find bucket index
    int index = hash & (table.length - 1);
    
    // 3. Handle collision or insert
    Node<K,V> node = table[index];
    if (node == null) {
        // Empty bucket - direct insertion
        table[index] = new Node<>(hash, key, value, null);
    } else {
        // Collision - traverse chain
        while (node != null) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                // Key exists - update value
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            if (node.next == null) break;
            node = node.next;
        }
        // Add new node to end of chain
        node.next = new Node<>(hash, key, value, null);
    }
    
    // 4. Check if resize needed
    if (++size > threshold) {
        resize();
    }
    return null;
}
```

#### **Hash Function:**
```java
static final int hash(Object key) {
    int h;
    // XOR higher bits with lower bits for better distribution
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

#### **Tree Conversion (Java 8+):**
```java
// When chain length > TREEIFY_THRESHOLD (8), convert to balanced tree
// When tree size < UNTREEIFY_THRESHOLD (6), convert back to list
// Improves worst-case performance from O(n) to O(log n)
```

### 82. What is hash collision and how is it handled?

**Hash collision** occurs when different keys produce the same hash code and map to the same bucket.

#### **Types of Collisions:**

**1. Hash Code Collision:**
```java
// Different objects, same hash code
String s1 = "Aa";
String s2 = "BB";
System.out.println(s1.hashCode());  // 2112
System.out.println(s2.hashCode());  // 2112 - same hash code!
```

**2. Bucket Index Collision:**
```java
// Different hash codes, same bucket index
int hash1 = 100;
int hash2 = 116;
int tableSize = 16;
int index1 = hash1 & (tableSize - 1);  // 4
int index2 = hash2 & (tableSize - 1);  // 4 - same bucket!
```

#### **Collision Resolution - Separate Chaining:**
```java
// HashMap uses separate chaining
// Each bucket contains a linked list (or tree) of entries

Bucket 0: null
Bucket 1: [key1,val1] -> [key5,val5] -> null
Bucket 2: [key2,val2] -> null
Bucket 3: null
Bucket 4: [key3,val3] -> [key7,val7] -> [key11,val11] -> null
```

#### **Tree Conversion for Performance:**
```java
public class CollisionHandling {
    // When chain length exceeds TREEIFY_THRESHOLD (8)
    // Convert linked list to balanced red-black tree
    
    public void demonstrateTreeConversion() {
        Map<BadHashKey, String> map = new HashMap<>();
        
        // Add many keys that hash to same bucket
        for (int i = 0; i < 10; i++) {
            map.put(new BadHashKey(i), "value" + i);
        }
        // After 8 entries in same bucket, converts to tree
        // Search time improves from O(n) to O(log n)
    }
    
    static class BadHashKey {
        int value;
        BadHashKey(int value) { this.value = value; }
        
        @Override
        public int hashCode() { return 1; }  // All keys hash to same bucket
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof BadHashKey && ((BadHashKey) obj).value == this.value;
        }
    }
}
```

### 83. What is the load factor in HashMap?

**Load factor** is the ratio of number of entries to the number of buckets.

#### **Load Factor Formula:**
```java
Load Factor = Number of Entries / Number of Buckets
Default Load Factor = 0.75
Threshold = Capacity × Load Factor
```

#### **Load Factor Impact:**
```java
public class LoadFactorDemo {
    public void demonstrateLoadFactor() {
        // Default: capacity=16, loadFactor=0.75, threshold=12
        Map<String, Integer> map = new HashMap<>();
        
        // Add 12 entries - no resize
        for (int i = 0; i < 12; i++) {
            map.put("key" + i, i);
        }
        
        // Add 13th entry - triggers resize
        map.put("key12", 12);  // Capacity doubles to 32, threshold becomes 24
    }
    
    // Custom load factor
    public void customLoadFactor() {
        // Lower load factor - more memory, fewer collisions
        Map<String, Integer> lowLoad = new HashMap<>(16, 0.5f);
        
        // Higher load factor - less memory, more collisions
        Map<String, Integer> highLoad = new HashMap<>(16, 0.9f);
    }
}
```

#### **Load Factor Trade-offs:**

| Load Factor | Memory Usage | Collision Probability | Performance |
|-------------|--------------|----------------------|-------------|
| **Low (0.5)** | High | Low | Fast access |
| **Medium (0.75)** | Balanced | Balanced | Good balance |
| **High (0.9)** | Low | High | Slower access |

#### **Choosing Load Factor:**
```java
// ✓ Use default 0.75 for most cases
Map<String, Integer> defaultMap = new HashMap<>();

// ✓ Use lower load factor when performance is critical
Map<String, Integer> fastMap = new HashMap<>(1000, 0.5f);

// ✓ Use higher load factor when memory is limited
Map<String, Integer> compactMap = new HashMap<>(100, 0.9f);
```

### 84. What happens when HashMap reaches its capacity?

When HashMap reaches its **threshold** (capacity × load factor), it triggers **rehashing**.

#### **Resize Process:**
```java
public class HashMapResize {
    public void demonstrateResize() {
        Map<String, Integer> map = new HashMap<>(4, 0.75f);
        // Initial: capacity=4, threshold=3
        
        map.put("A", 1);  // size=1, no resize
        map.put("B", 2);  // size=2, no resize
        map.put("C", 3);  // size=3, no resize
        map.put("D", 4);  // size=4 > threshold=3, RESIZE!
        
        // After resize: capacity=8, threshold=6
    }
}
```

#### **Rehashing Steps:**

**1. Create New Array:**
```java
// Double the capacity
Node<K,V>[] oldTable = table;
int oldCapacity = oldTable.length;
int newCapacity = oldCapacity << 1;  // Double the size
Node<K,V>[] newTable = new Node[newCapacity];
```

**2. Rehash All Entries:**
```java
// Recalculate bucket index for each entry
for (int i = 0; i < oldCapacity; i++) {
    Node<K,V> node = oldTable[i];
    while (node != null) {
        Node<K,V> next = node.next;
        
        // Recalculate index in new table
        int newIndex = node.hash & (newCapacity - 1);
        
        // Move to new table
        node.next = newTable[newIndex];
        newTable[newIndex] = node;
        
        node = next;
    }
}
```

#### **Performance Impact:**
```java
public class ResizePerformance {
    public void measureResizeImpact() {
        Map<Integer, String> map = new HashMap<>(2);  // Small initial capacity
        
        long start = System.nanoTime();
        
        // This will trigger multiple resizes
        for (int i = 0; i < 1000; i++) {
            map.put(i, "value" + i);
            // Resizes at: 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024
        }
        
        long end = System.nanoTime();
        System.out.println("Time with resizes: " + (end - start) + " ns");
        
        // Better: pre-size the map
        Map<Integer, String> presized = new HashMap<>(1024);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            presized.put(i, "value" + i);  // No resizes needed
        }
        end = System.nanoTime();
        System.out.println("Time without resizes: " + (end - start) + " ns");
    }
}
```

### 85. What is the difference between fail-fast and fail-safe iterators?

| Aspect | Fail-Fast | Fail-Safe |
|--------|-----------|-----------|
| **Behavior** | Throws ConcurrentModificationException | No exception thrown |
| **Detection** | Detects concurrent modification | Allows concurrent modification |
| **Memory** | Works on original collection | Works on copy of collection |
| **Performance** | Faster | Slower (due to copying) |
| **Examples** | ArrayList, HashMap | ConcurrentHashMap, CopyOnWriteArrayList |

#### **Fail-Fast Iterator:**
```java
public class FailFastExample {
    public void demonstrateFailFast() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        
        try {
            for (String item : list) {
                System.out.println(item);
                if ("B".equals(item)) {
                    list.remove(item);  // Modifies collection during iteration
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Fail-fast: " + e.getMessage());
        }
    }
    
    public void safeModification() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        
        // ✓ Safe way - use iterator's remove method
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("B".equals(item)) {
                iterator.remove();  // Safe removal
            }
        }
    }
}
```

#### **Fail-Safe Iterator:**
```java
public class FailSafeExample {
    public void demonstrateFailSafe() {
        // ConcurrentHashMap uses fail-safe iterator
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        // Safe to modify during iteration
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
            if ("B".equals(entry.getKey())) {
                map.put("D", 4);  // Safe modification
            }
        }
        // No exception thrown
    }
    
    public void copyOnWriteExample() {
        // CopyOnWriteArrayList creates copy for each modification
        List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
        
        for (String item : list) {
            System.out.println(item);
            if ("B".equals(item)) {
                list.add("D");  // Safe - works on copy
            }
        }
        // Iterator sees original snapshot, modifications don't affect it
    }
}
```

### 86. What is WeakHashMap?

**WeakHashMap** uses **weak references** for keys, allowing garbage collection when no strong references exist.

#### **Weak Reference Behavior:**
```java
public class WeakHashMapExample {
    public void demonstrateWeakReferences() {
        Map<String, String> weakMap = new WeakHashMap<>();
        Map<String, String> normalMap = new HashMap<>();
        
        // Create keys
        String key1 = new String("key1");  // Strong reference
        String key2 = new String("key2");
        
        // Add to both maps
        weakMap.put(key1, "value1");
        weakMap.put(key2, "value2");
        normalMap.put(key1, "value1");
        normalMap.put(key2, "value2");
        
        System.out.println("Before GC - WeakMap size: " + weakMap.size());     // 2
        System.out.println("Before GC - NormalMap size: " + normalMap.size()); // 2
        
        // Remove strong references
        key1 = null;
        key2 = null;
        
        // Force garbage collection
        System.gc();
        Thread.sleep(1000);  // Give GC time to run
        
        System.out.println("After GC - WeakMap size: " + weakMap.size());      // 0 (keys collected)
        System.out.println("After GC - NormalMap size: " + normalMap.size());  // 2 (keys retained)
    }
}
```

#### **Use Cases for WeakHashMap:**

**1. Cache Implementation:**
```java
public class CacheExample {
    private final Map<Object, ExpensiveObject> cache = new WeakHashMap<>();
    
    public ExpensiveObject getExpensiveObject(Object key) {
        ExpensiveObject cached = cache.get(key);
        if (cached == null) {
            cached = createExpensiveObject(key);
            cache.put(key, cached);
        }
        return cached;
    }
    
    // When 'key' is no longer referenced elsewhere,
    // it will be garbage collected along with its cache entry
}
```

**2. Observer Pattern:**
```java
public class ObserverRegistry {
    private final Map<Observer, String> observers = new WeakHashMap<>();
    
    public void addObserver(Observer observer) {
        observers.put(observer, "registered");
    }
    
    public void notifyObservers() {
        for (Observer observer : observers.keySet()) {
            observer.update();
        }
    }
    
    // Observers are automatically removed when they're no longer referenced
}
```

### 87. What is IdentityHashMap?

**IdentityHashMap** uses **reference equality** (==) instead of **object equality** (equals()) for key comparison.

#### **Reference vs Object Equality:**
```java
public class IdentityHashMapExample {
    public void demonstrateDifference() {
        Map<String, Integer> normalMap = new HashMap<>();
        Map<String, Integer> identityMap = new IdentityHashMap<>();
        
        String key1 = new String("hello");
        String key2 = new String("hello");  // Same content, different object
        String key3 = "hello";              // String literal (interned)
        
        // HashMap uses equals() - treats key1 and key2 as same
        normalMap.put(key1, 1);
        normalMap.put(key2, 2);  // Overwrites previous value
        System.out.println("HashMap size: " + normalMap.size());  // 1
        
        // IdentityHashMap uses == - treats key1 and key2 as different
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);  // Different objects, both stored
        identityMap.put(key3, 3);  // key3 might be same reference as key1 (interning)
        System.out.println("IdentityHashMap size: " + identityMap.size());  // 2 or 3
    }
}
```

#### **Use Cases:**

**1. Object Tracking:**
```java
public class ObjectTracker {
    private final Map<Object, String> objectStates = new IdentityHashMap<>();
    
    public void trackObject(Object obj, String state) {
        objectStates.put(obj, state);  // Track specific object instance
    }
    
    public String getObjectState(Object obj) {
        return objectStates.get(obj);  // Only matches exact same object
    }
}
```

**2. Serialization/Deserialization:**
```java
public class SerializationHelper {
    private final Map<Object, Integer> objectIds = new IdentityHashMap<>();
    private int nextId = 1;
    
    public int getObjectId(Object obj) {
        return objectIds.computeIfAbsent(obj, k -> nextId++);
    }
    
    // Ensures each object instance gets unique ID, even if equals() returns true
}
```

### 88. What is LinkedHashMap?

**LinkedHashMap** extends HashMap and maintains **insertion order** or **access order** using a doubly-linked list.

#### **Insertion Order (Default):**
```java
public class LinkedHashMapExample {
    public void demonstrateInsertionOrder() {
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        Map<String, Integer> hashMap = new HashMap<>();
        
        // Add in specific order
        linkedMap.put("third", 3);
        linkedMap.put("first", 1);
        linkedMap.put("second", 2);
        
        hashMap.put("third", 3);
        hashMap.put("first", 1);
        hashMap.put("second", 2);
        
        System.out.println("LinkedHashMap: " + linkedMap);  // {third=3, first=1, second=2}
        System.out.println("HashMap: " + hashMap);          // Random order
    }
}
```

#### **Access Order Mode:**
```java
public class AccessOrderExample {
    public void demonstrateAccessOrder() {
        // accessOrder = true enables LRU behavior
        Map<String, Integer> lruMap = new LinkedHashMap<>(16, 0.75f, true);
        
        lruMap.put("A", 1);
        lruMap.put("B", 2);
        lruMap.put("C", 3);
        
        System.out.println("Initial: " + lruMap);  // {A=1, B=2, C=3}
        
        // Access A - moves to end
        lruMap.get("A");
        System.out.println("After accessing A: " + lruMap);  // {B=2, C=3, A=1}
        
        // Access B - moves to end
        lruMap.get("B");
        System.out.println("After accessing B: " + lruMap);  // {C=3, A=1, B=2}
    }
}
```

#### **LRU Cache Implementation:**
```java
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;
    
    public LRUCache(int maxSize) {
        super(16, 0.75f, true);  // Enable access order
        this.maxSize = maxSize;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;  // Remove oldest when size exceeds limit
    }
}

// Usage
LRUCache<String, String> cache = new LRUCache<>(3);
cache.put("1", "one");
cache.put("2", "two");
cache.put("3", "three");
cache.put("4", "four");    // "1" is automatically removed
System.out.println(cache); // {2=two, 3=three, 4=four}
```

### 89. What is PriorityQueue?

**PriorityQueue** is a **heap-based** priority queue that orders elements by priority, not insertion order.

#### **Natural Ordering:**
```java
public class PriorityQueueExample {
    public void demonstrateNaturalOrdering() {
        Queue<Integer> pq = new PriorityQueue<>();
        
        // Add elements in random order
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        pq.offer(5);
        
        // Elements come out in priority order (min-heap by default)
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());  // 5, 10, 20, 30
        }
    }
}
```

#### **Custom Comparator:**
```java
public class CustomPriorityExample {
    public void demonstrateCustomPriority() {
        // Max-heap using reverse order
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(Arrays.asList(30, 10, 20, 5));
        
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());  // 30, 20, 10, 5
        }
        
        // Custom object priority
        Queue<Task> taskQueue = new PriorityQueue<>(
            Comparator.comparing(Task::getPriority).reversed()
        );
        
        taskQueue.offer(new Task("Low priority", 1));
        taskQueue.offer(new Task("High priority", 10));
        taskQueue.offer(new Task("Medium priority", 5));
        
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());  // High, Medium, Low
        }
    }
    
    static class Task {
        String name;
        int priority;
        
        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        int getPriority() { return priority; }
        
        @Override
        public String toString() { return name + " (" + priority + ")"; }
    }
}
```

#### **Heap Properties:**
```java
public class HeapProperties {
    public void demonstrateHeapBehavior() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(4, 2, 8, 1, 7, 3));
        
        // peek() returns minimum without removing
        System.out.println("Min element: " + pq.peek());  // 1
        
        // poll() removes and returns minimum
        System.out.println("Removed: " + pq.poll());      // 1
        System.out.println("New min: " + pq.peek());      // 2
        
        // Note: iteration order is NOT sorted
        System.out.println("Iteration order: " + pq);     // Not necessarily sorted
    }
}
```

### 90. What is the difference between Comparable and Comparator?

| Aspect | Comparable | Comparator |
|--------|------------|------------|
| **Location** | Inside the class | External class/lambda |
| **Method** | compareTo(T other) | compare(T o1, T o2) |
| **Sorting** | Natural ordering | Custom ordering |
| **Flexibility** | Single sorting logic | Multiple sorting strategies |
| **Modification** | Requires class modification | No class modification needed |

#### **Comparable - Natural Ordering:**
```java
public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double gpa;
    
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    @Override
    public int compareTo(Student other) {
        // Natural ordering by GPA (descending)
        return Double.compare(other.gpa, this.gpa);
    }
    
    // Usage
    public static void demonstrateComparable() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8),
            new Student("Bob", 22, 3.5),
            new Student("Charlie", 21, 3.9)
        );
        
        Collections.sort(students);  // Uses compareTo()
        // Sorted by GPA: Charlie (3.9), Alice (3.8), Bob (3.5)
    }
}
```

#### **Comparator - Custom Ordering:**
```java
public class ComparatorExample {
    public void demonstrateComparator() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8),
            new Student("Bob", 22, 3.5),
            new Student("Charlie", 21, 3.9)
        );
        
        // Sort by name
        Collections.sort(students, Comparator.comparing(Student::getName));
        
        // Sort by age (descending)
        Collections.sort(students, Comparator.comparing(Student::getAge).reversed());
        
        // Multiple criteria - age first, then GPA
        Collections.sort(students, 
            Comparator.comparing(Student::getAge)
                     .thenComparing(Student::getGpa));
        
        // Lambda comparator
        Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));
    }
}
```

#### **Advanced Comparator Usage:**
```java
public class AdvancedComparator {
    public void demonstrateAdvancedUsage() {
        List<Student> students = getStudents();
        
        // Null-safe comparison
        Comparator<Student> nameComparator = 
            Comparator.comparing(Student::getName, Comparator.nullsLast(String::compareTo));
        
        // Custom comparison logic
        Comparator<Student> gradeComparator = (s1, s2) -> {
            if (s1.getGpa() >= 3.5 && s2.getGpa() >= 3.5) return 0;  // Both honor students
            return Double.compare(s2.getGpa(), s1.getGpa());
        };
        
        // Chaining comparators
        Comparator<Student> complexComparator = 
            Comparator.comparing(Student::getGpa).reversed()           // GPA descending
                     .thenComparing(Student::getAge)                   // Then age ascending
                     .thenComparing(Student::getName);                 // Then name ascending
        
        students.sort(complexComparator);
    }
    
    // TreeSet with custom comparator
    public void treeSetWithComparator() {
        // Sort students by name length, then alphabetically
        Set<Student> studentSet = new TreeSet<>(
            Comparator.comparing((Student s) -> s.getName().length())
                     .thenComparing(Student::getName)
        );
        
        studentSet.addAll(getStudents());
    }
}
```

#### **When to Use Each:**
```java
// ✓ Use Comparable when:
// - There's a single, natural way to order objects
// - You control the class definition
// - The ordering is fundamental to the class

public class Price implements Comparable<Price> {
    private BigDecimal amount;
    
    @Override
    public int compareTo(Price other) {
        return this.amount.compareTo(other.amount);  // Natural ordering by amount
    }
}

// ✓ Use Comparator when:
// - You need multiple sorting strategies
// - You don't control the class definition
// - The ordering is context-dependent

public class ProductSorter {
    public static final Comparator<Product> BY_PRICE = 
        Comparator.comparing(Product::getPrice);
    
    public static final Comparator<Product> BY_NAME = 
        Comparator.comparing(Product::getName);
    
    public static final Comparator<Product> BY_POPULARITY = 
        Comparator.comparing(Product::getPopularityScore).reversed();
}
```
