# 🔹 Collections Framework

## Collection Basics

### 71. What is Java Collections Framework?

The **Java Collections Framework** is a unified architecture for representing and manipulating collections of objects.

#### **Key Components:**
- **Interfaces** - Abstract data types (List, Set, Map)
- **Implementations** - Concrete classes (ArrayList, HashMap)
- **Algorithms** - Static methods for operations (sort, search)
- **Utilities** - Helper classes and methods

#### **Framework Structure:**
```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList (Class)
│   ├── LinkedList (Class)
│   └── Vector (Class)
├── Set (Interface)
│   ├── HashSet (Class)
│   ├── TreeSet (Class)
│   └── LinkedHashSet (Class)
└── Queue (Interface)
    ├── PriorityQueue (Class)
    └── ArrayDeque (Class)

Map (Interface) - separate hierarchy
├── HashMap (Class)
├── TreeMap (Class)
├── LinkedHashMap (Class)
└── Hashtable (Class)
```

#### **Basic Usage:**
```java
// List - ordered, allows duplicates
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Alice");  // Duplicate allowed

// Set - no duplicates
Set<String> uniqueNames = new HashSet<>();
uniqueNames.add("Alice");
uniqueNames.add("Bob");
uniqueNames.add("Alice");  // Duplicate ignored

// Map - key-value pairs
Map<String, Integer> ages = new HashMap<>();
ages.put("Alice", 25);
ages.put("Bob", 30);
```

#### **Benefits:**
- **Consistent API** - same methods across implementations
- **Reduced programming effort** - no need to write custom data structures
- **Increased performance** - optimized implementations
- **Interoperability** - collections work together seamlessly

### 72. What is the difference between Collection and Collections?

| Aspect | Collection | Collections |
|--------|------------|-------------|
| **Type** | Interface | Utility Class |
| **Purpose** | Defines contract for collections | Provides utility methods |
| **Usage** | Implemented by collection classes | Static methods for operations |
| **Package** | java.util.Collection | java.util.Collections |

#### **Collection Interface:**
```java
// Collection is the root interface
public interface Collection<E> extends Iterable<E> {
    boolean add(E e);
    boolean remove(Object o);
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Iterator<E> iterator();
    // ... other methods
}

// Usage - implement or use implementations
List<String> list = new ArrayList<>();  // ArrayList implements Collection
Set<String> set = new HashSet<>();      // HashSet implements Collection
```

#### **Collections Utility Class:**
```java
// Collections provides static utility methods
public class Collections {
    // Sorting
    public static <T extends Comparable<? super T>> void sort(List<T> list);
    
    // Searching
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key);
    
    // Synchronization
    public static <T> Collection<T> synchronizedCollection(Collection<T> c);
    
    // Immutable collections
    public static <T> List<T> unmodifiableList(List<? extends T> list);
}

// Usage examples
List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5);
Collections.sort(numbers);                    // [1, 1, 3, 4, 5]
Collections.reverse(numbers);                 // [5, 4, 3, 1, 1]
int index = Collections.binarySearch(numbers, 3);  // Find index of 3

// Create synchronized version
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Create unmodifiable version
List<String> readOnlyList = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
```

### 73. What are the main interfaces in Collections Framework?

#### **Core Interfaces Hierarchy:**

**1. Collection Interface (Root):**
```java
Collection<E>
├── List<E>     // Ordered, allows duplicates
├── Set<E>      // No duplicates
└── Queue<E>    // FIFO operations
    └── Deque<E> // Double-ended queue
```

**2. Map Interface (Separate):**
```java
Map<K,V>        // Key-value pairs
```

#### **Interface Characteristics:**

| Interface | Duplicates | Ordered | Indexed | Key-Value |
|-----------|------------|---------|---------|-----------|
| **List** | Yes | Yes | Yes | No |
| **Set** | No | Depends | No | No |
| **Queue** | Yes | Yes | No | No |
| **Deque** | Yes | Yes | No | No |
| **Map** | Values: Yes, Keys: No | Depends | No | Yes |

#### **Interface Examples:**

**List Interface:**
```java
List<String> list = new ArrayList<>();
list.add("first");
list.add("second");
list.add("first");     // Duplicate allowed
list.get(0);           // Access by index
list.set(1, "new");    // Modify by index
```

**Set Interface:**
```java
Set<String> set = new HashSet<>();
set.add("unique");
set.add("values");
set.add("unique");     // Duplicate ignored
// No index-based access
```

**Queue Interface:**
```java
Queue<String> queue = new LinkedList<>();
queue.offer("first");   // Add to rear
queue.offer("second");
String head = queue.poll();  // Remove from front
```

**Map Interface:**
```java
Map<String, Integer> map = new HashMap<>();
map.put("key1", 100);
map.put("key2", 200);
Integer value = map.get("key1");  // Access by key
```

### 74. What is the difference between List, Set, and Map?

| Feature | List | Set | Map |
|---------|------|-----|-----|
| **Duplicates** | Allows duplicates | No duplicates | Keys: No, Values: Yes |
| **Ordering** | Maintains insertion order | Depends on implementation | Depends on implementation |
| **Indexing** | Index-based access | No indexing | Key-based access |
| **Null Values** | Multiple nulls allowed | At most one null | Depends on implementation |

#### **List - Ordered Collection with Duplicates:**
```java
List<String> fruits = new ArrayList<>();
fruits.add("apple");
fruits.add("banana");
fruits.add("apple");      // Duplicate allowed
fruits.add(null);         // Null allowed
fruits.add(null);         // Multiple nulls allowed

// Index-based operations
fruits.get(0);            // "apple"
fruits.set(1, "orange");  // Replace at index 1
fruits.remove(2);         // Remove at index 2

// Common implementations: ArrayList, LinkedList, Vector
```

#### **Set - Unique Elements:**
```java
Set<String> uniqueFruits = new HashSet<>();
uniqueFruits.add("apple");
uniqueFruits.add("banana");
uniqueFruits.add("apple");    // Ignored - duplicate
uniqueFruits.add(null);       // One null allowed (in HashSet)

// No index-based access
// uniqueFruits.get(0);      // ✗ Not available

// Check membership
boolean hasApple = uniqueFruits.contains("apple");

// Common implementations: HashSet, TreeSet, LinkedHashSet
```

#### **Map - Key-Value Pairs:**
```java
Map<String, Integer> inventory = new HashMap<>();
inventory.put("apples", 50);
inventory.put("bananas", 30);
inventory.put("apples", 60);     // Overwrites previous value

// Key-based access
Integer appleCount = inventory.get("apples");  // 60
inventory.remove("bananas");

// Keys are unique, values can duplicate
inventory.put("oranges", 50);    // Same value as apples, but different key

// Common implementations: HashMap, TreeMap, LinkedHashMap
```

### 75. What is the difference between ArrayList and LinkedList?

| Aspect | ArrayList | LinkedList |
|--------|-----------|------------|
| **Internal Structure** | Dynamic array | Doubly-linked list |
| **Random Access** | O(1) - fast | O(n) - slow |
| **Insertion/Deletion** | O(n) in middle | O(1) at known position |
| **Memory Overhead** | Lower | Higher (node objects) |
| **Cache Performance** | Better | Worse |

#### **ArrayList - Dynamic Array:**
```java
List<String> arrayList = new ArrayList<>();

// Fast random access
arrayList.add("A");
arrayList.add("B");
arrayList.add("C");
String element = arrayList.get(1);  // O(1) - direct array access

// Slow insertion in middle
arrayList.add(1, "X");  // O(n) - shift elements right

// Memory efficient - contiguous storage
// Good cache locality
```

#### **LinkedList - Doubly-Linked List:**
```java
List<String> linkedList = new LinkedList<>();

// Slow random access
linkedList.add("A");
linkedList.add("B");
linkedList.add("C");
String element = linkedList.get(1);  // O(n) - traverse from head/tail

// Fast insertion anywhere if you have reference
LinkedList<String> ll = (LinkedList<String>) linkedList;
ll.addFirst("X");   // O(1)
ll.addLast("Y");    // O(1)

// Higher memory overhead - node objects with pointers
// Poor cache locality
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    public void demonstratePerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // Adding elements - both O(1) at end
        for (int i = 0; i < 1000; i++) {
            arrayList.add(i);      // Fast
            linkedList.add(i);     // Fast
        }
        
        // Random access - ArrayList wins
        int sum1 = 0;
        for (int i = 0; i < 1000; i++) {
            sum1 += arrayList.get(i);   // O(1) each - total O(n)
        }
        
        int sum2 = 0;
        for (int i = 0; i < 1000; i++) {
            sum2 += linkedList.get(i);  // O(n) each - total O(n²)
        }
        
        // Insertion in middle - LinkedList can be better
        arrayList.add(500, -1);     // O(n) - shift 500 elements
        linkedList.add(500, -1);    // O(n) - traverse to position, then O(1) insert
    }
}
```

### 76. When would you use ArrayList vs LinkedList?

#### **Use ArrayList When:**

**1. Frequent Random Access:**
```java
// Reading data by index frequently
List<String> records = new ArrayList<>();
// ... populate records
for (int i = 0; i < records.size(); i++) {
    processRecord(records.get(i));  // O(1) access
}
```

**2. Memory is a Concern:**
```java
// ArrayList uses less memory per element
List<Integer> numbers = new ArrayList<>(1000000);  // More memory efficient
```

**3. Iteration Performance:**
```java
// ArrayList has better cache locality
for (String item : arrayList) {  // Faster iteration
    process(item);
}
```

**4. Mostly Appending Data:**
```java
// Adding to end is O(1) amortized
List<LogEntry> logs = new ArrayList<>();
logs.add(new LogEntry());  // Fast append
```

#### **Use LinkedList When:**

**1. Frequent Insertions/Deletions in Middle:**
```java
// Implementing a queue or deque
Queue<Task> taskQueue = new LinkedList<>();
taskQueue.offer(task);     // O(1) at end
Task next = taskQueue.poll();  // O(1) at beginning

// Frequent insertions at specific positions
LinkedList<String> editableText = new LinkedList<>();
editableText.add(position, newText);  // Better than ArrayList for middle insertions
```

**2. Unknown Size with Frequent Modifications:**
```java
// When you don't know final size and modify frequently
List<String> dynamicList = new LinkedList<>();
// No need to worry about array resizing overhead
```

**3. Implementing Stack/Queue Operations:**
```java
// LinkedList implements Deque interface
Deque<String> stack = new LinkedList<>();
stack.push("item");     // O(1)
String top = stack.pop();   // O(1)

Queue<String> queue = new LinkedList<>();
queue.offer("item");    // O(1)
String first = queue.poll();  // O(1)
```

#### **Decision Matrix:**
```java
public class CollectionChoice {
    // ✓ ArrayList - frequent random access
    public void processDataByIndex(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            if (shouldProcess(i)) {
                process(data.get(i));  // O(1) with ArrayList
            }
        }
    }
    
    // ✓ LinkedList - frequent insertions at beginning
    public void maintainRecentItems(LinkedList<String> recent, String newItem) {
        recent.addFirst(newItem);  // O(1)
        if (recent.size() > 100) {
            recent.removeLast();   // O(1)
        }
    }
    
    // ✓ ArrayList - memory-sensitive application
    public List<Integer> createLargeDataset() {
        return new ArrayList<>(1000000);  // Less memory per element
    }
}
```

### 77. What is the difference between HashSet and TreeSet?

| Aspect | HashSet | TreeSet |
|--------|---------|---------|
| **Internal Structure** | Hash table | Red-black tree (balanced BST) |
| **Time Complexity** | O(1) average | O(log n) |
| **Ordering** | No ordering | Sorted order |
| **Null Values** | One null allowed | No null allowed |
| **Comparison** | equals() and hashCode() | Comparable or Comparator |

#### **HashSet - Hash Table Based:**
```java
Set<String> hashSet = new HashSet<>();
hashSet.add("banana");
hashSet.add("apple");
hashSet.add("cherry");
hashSet.add(null);        // Null allowed

// No guaranteed order
System.out.println(hashSet);  // Could be: [null, cherry, apple, banana]

// Fast operations - O(1) average
boolean hasApple = hashSet.contains("apple");  // O(1)
hashSet.remove("banana");                      // O(1)
```

#### **TreeSet - Balanced Tree Based:**
```java
Set<String> treeSet = new TreeSet<>();
treeSet.add("banana");
treeSet.add("apple");
treeSet.add("cherry");
// treeSet.add(null);     // ✗ NullPointerException

// Maintains sorted order
System.out.println(treeSet);  // [apple, banana, cherry]

// Slower operations - O(log n)
boolean hasApple = treeSet.contains("apple");  // O(log n)
treeSet.remove("banana");                      // O(log n)
```

#### **TreeSet Additional Operations:**
```java
TreeSet<Integer> numbers = new TreeSet<>();
numbers.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));

// Sorted: [1, 2, 3, 5, 8, 9]
Integer first = numbers.first();        // 1
Integer last = numbers.last();          // 9
Integer lower = numbers.lower(5);       // 3 (largest < 5)
Integer higher = numbers.higher(5);     // 8 (smallest > 5)
Integer floor = numbers.floor(4);       // 3 (largest <= 4)
Integer ceiling = numbers.ceiling(4);   // 5 (smallest >= 4)

// Range operations
Set<Integer> subset = numbers.subSet(3, 8);  // [3, 5] (3 inclusive, 8 exclusive)
```

#### **Custom Sorting with TreeSet:**
```java
// Custom comparator for reverse order
TreeSet<String> reverseSet = new TreeSet<>(Collections.reverseOrder());
reverseSet.addAll(Arrays.asList("apple", "banana", "cherry"));
System.out.println(reverseSet);  // [cherry, banana, apple]

// Custom object with Comparable
class Person implements Comparable<Person> {
    String name;
    int age;
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);  // Sort by age
    }
}

TreeSet<Person> people = new TreeSet<>();
// Automatically sorted by age
```

### 78. What is the difference between HashMap and TreeMap?

| Aspect | HashMap | TreeMap |
|--------|---------|---------|
| **Internal Structure** | Hash table | Red-black tree |
| **Time Complexity** | O(1) average | O(log n) |
| **Ordering** | No ordering | Sorted by keys |
| **Null Keys** | One null key allowed | No null keys |
| **Null Values** | Multiple null values | Multiple null values |

#### **HashMap - Hash Table Based:**
```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("banana", 2);
hashMap.put("apple", 5);
hashMap.put("cherry", 3);
hashMap.put(null, 0);     // Null key allowed

// No guaranteed order
System.out.println(hashMap);  // Could be: {null=0, cherry=3, apple=5, banana=2}

// Fast operations - O(1) average
Integer apples = hashMap.get("apple");  // O(1)
hashMap.remove("banana");               // O(1)
```

#### **TreeMap - Balanced Tree Based:**
```java
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("banana", 2);
treeMap.put("apple", 5);
treeMap.put("cherry", 3);
// treeMap.put(null, 0);  // ✗ NullPointerException

// Maintains sorted order by keys
System.out.println(treeMap);  // {apple=5, banana=2, cherry=3}

// Slower operations - O(log n)
Integer apples = treeMap.get("apple");  // O(log n)
treeMap.remove("banana");               // O(log n)
```

#### **TreeMap Additional Operations:**
```java
TreeMap<String, Integer> inventory = new TreeMap<>();
inventory.put("apples", 50);
inventory.put("bananas", 30);
inventory.put("cherries", 20);
inventory.put("dates", 10);

// Navigation methods
String firstKey = inventory.firstKey();        // "apples"
String lastKey = inventory.lastKey();          // "dates"
String lowerKey = inventory.lowerKey("cherries");  // "bananas"
String higherKey = inventory.higherKey("bananas"); // "cherries"

// Range operations
Map<String, Integer> subMap = inventory.subMap("bananas", "dates");
// {bananas=30, cherries=20} (inclusive start, exclusive end)

// Reverse navigation
NavigableMap<String, Integer> descendingMap = inventory.descendingMap();
System.out.println(descendingMap);  // {dates=10, cherries=20, bananas=30, apples=50}
```

### 79. What is the difference between HashMap and Hashtable?

| Aspect | HashMap | Hashtable |
|--------|---------|-----------|
| **Synchronization** | Not synchronized | Synchronized |
| **Thread Safety** | Not thread-safe | Thread-safe |
| **Null Values** | Allows null key/values | No null key/values |
| **Performance** | Faster | Slower (due to synchronization) |
| **Inheritance** | Extends AbstractMap | Extends Dictionary (legacy) |
| **Introduction** | Java 1.2 | Java 1.0 |

#### **HashMap - Modern, Fast, Not Thread-Safe:**
```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("key1", 100);
hashMap.put("key2", 200);
hashMap.put(null, 300);      // Null key allowed
hashMap.put("key3", null);   // Null value allowed

// Fast but not thread-safe
// Multiple threads accessing simultaneously can cause issues
```

#### **Hashtable - Legacy, Synchronized, Thread-Safe:**
```java
Map<String, Integer> hashtable = new Hashtable<>();
hashtable.put("key1", 100);
hashtable.put("key2", 200);
// hashtable.put(null, 300);    // ✗ NullPointerException
// hashtable.put("key3", null); // ✗ NullPointerException

// Thread-safe but slower
// All methods are synchronized
```

#### **Thread Safety Demonstration:**
```java
public class ThreadSafetyDemo {
    public void demonstrateHashMap() {
        Map<String, Integer> map = new HashMap<>();
        
        // Unsafe in multi-threaded environment
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);  // Can cause data corruption
            }
        };
        
        new Thread(task).start();
        new Thread(task).start();
        // Possible issues: infinite loops, data corruption
    }
    
    public void demonstrateHashtable() {
        Map<String, Integer> map = new Hashtable<>();
        
        // Safe in multi-threaded environment
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);  // Thread-safe operations
            }
        };
        
        new Thread(task).start();
        new Thread(task).start();
        // No data corruption, but slower performance
    }
}
```

#### **Modern Alternatives:**
```java
// Instead of Hashtable, use:

// 1. ConcurrentHashMap - better performance
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// 2. Synchronized wrapper
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

// 3. External synchronization
Map<String, Integer> map = new HashMap<>();
synchronized(map) {
    map.put("key", value);
}
```

### 80. What is the difference between HashMap and ConcurrentHashMap?

| Aspect | HashMap | ConcurrentHashMap |
|--------|---------|-------------------|
| **Thread Safety** | Not thread-safe | Thread-safe |
| **Synchronization** | No synchronization | Segment-based locking |
| **Performance** | Fast (single-thread) | Good (multi-thread) |
| **Null Values** | Allows null key/values | No null key/values |
| **Fail-Fast** | Yes | No (fail-safe) |
| **Concurrent Reads** | Not safe | Safe |

#### **HashMap - Single-Threaded Performance:**
```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("key1", 100);
hashMap.put(null, 200);      // Null allowed
hashMap.put("key2", null);   // Null value allowed

// Fast operations but not thread-safe
// Can cause infinite loops or data corruption in multi-threaded environment
```

#### **ConcurrentHashMap - Multi-Threaded Safety:**
```java
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("key1", 100);
// concurrentMap.put(null, 200);    // ✗ NullPointerException
// concurrentMap.put("key2", null); // ✗ NullPointerException

// Thread-safe with better performance than Hashtable
// Uses segment-based locking for concurrent access
```

#### **Concurrency Features:**
```java
public class ConcurrencyDemo {
    private final Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
    
    public void demonstrateConcurrentOperations() {
        // Multiple threads can safely access
        Runnable reader = () -> {
            for (int i = 0; i < 1000; i++) {
                Integer value = concurrentMap.get("key" + (i % 100));
                // Safe concurrent reads
            }
        };
        
        Runnable writer = () -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("key" + i, i);
                // Safe concurrent writes (with some locking)
            }
        };
        
        // Multiple readers and writers can work simultaneously
        new Thread(reader).start();
        new Thread(reader).start();
        new Thread(writer).start();
    }
}
```

#### **Atomic Operations in ConcurrentHashMap:**
```java
ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();

// Atomic increment
counter.compute("visits", (key, val) -> (val == null) ? 1 : val + 1);

// Atomic put-if-absent
Integer previous = counter.putIfAbsent("users", 0);

// Atomic replace
counter.replace("sessions", 10, 11);  // Replace only if current value is 10

// Atomic compute operations
counter.computeIfAbsent("newKey", key -> calculateInitialValue(key));
counter.computeIfPresent("existingKey", (key, val) -> val * 2);
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    public void comparePerformance() {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> hashtable = new Hashtable<>();
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        
        // Single-threaded: HashMap > ConcurrentHashMap > SynchronizedMap > Hashtable
        // Multi-threaded: ConcurrentHashMap > SynchronizedMap ≈ Hashtable >> HashMap (unsafe)
    }
}
```

#### **When to Use Each:**
```java
// ✓ HashMap - single-threaded applications
Map<String, String> config = new HashMap<>();

// ✓ ConcurrentHashMap - multi-threaded applications
Map<String, UserSession> sessions = new ConcurrentHashMap<>();

// ✗ Hashtable - legacy, avoid in new code
// Map<String, Object> legacy = new Hashtable<>();

// ✓ Synchronized wrapper - when you need null values in multi-threaded environment
Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
```
