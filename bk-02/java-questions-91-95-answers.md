## Collection Performance

### 91. What is the time complexity of ArrayList operations?

ArrayList is backed by a **dynamic array**, which determines its performance characteristics.

#### **Time Complexity Summary:**

| Operation | Best Case | Average Case | Worst Case | Notes |
|-----------|-----------|--------------|------------|-------|
| **get(index)** | O(1) | O(1) | O(1) | Direct array access |
| **set(index, element)** | O(1) | O(1) | O(1) | Direct array access |
| **add(element)** | O(1) | O(1) | O(n) | Amortized O(1), O(n) when resize |
| **add(index, element)** | O(1) | O(n) | O(n) | O(1) at end, O(n) in middle |
| **remove(index)** | O(1) | O(n) | O(n) | O(1) at end, O(n) in middle |
| **contains(element)** | O(1) | O(n) | O(n) | Linear search |
| **indexOf(element)** | O(1) | O(n) | O(n) | Linear search |

#### **Detailed Analysis:**

**Fast Operations - O(1):**
```java
List<String> list = new ArrayList<>();
list.add("A");  // O(1) - add at end
list.add("B");
list.add("C");

// Random access - O(1)
String element = list.get(1);     // O(1) - direct array access
list.set(2, "D");                 // O(1) - direct array assignment

// Size operations - O(1)
int size = list.size();           // O(1)
boolean empty = list.isEmpty();   // O(1)
```

**Slow Operations - O(n):**
```java
// Insertion in middle - O(n)
list.add(1, "X");  // Shifts elements: [A, X, B, D]
// Internally: System.arraycopy() shifts elements right

// Removal from middle - O(n)
list.remove(1);    // Shifts elements: [A, B, D]
// Internally: System.arraycopy() shifts elements left

// Search operations - O(n)
boolean found = list.contains("B");  // Linear search through array
int index = list.indexOf("D");       // Linear search until found
```

#### **Resize Operation - Amortized O(1):**
```java
public class ArrayListResize {
    public void demonstrateResize() {
        List<Integer> list = new ArrayList<>(2);  // Initial capacity = 2
        
        list.add(1);  // O(1) - space available
        list.add(2);  // O(1) - space available
        list.add(3);  // O(n) - triggers resize, copies all elements
        
        // Resize process:
        // 1. Create new array with 1.5x capacity (capacity = 3)
        // 2. Copy all existing elements: O(n)
        // 3. Add new element: O(1)
        
        // Subsequent adds are O(1) until next resize
        list.add(4);  // O(1)
    }
}
```

#### **Performance Optimization:**
```java
// ✓ Good - pre-size when you know approximate size
List<String> optimized = new ArrayList<>(1000);  // Avoids multiple resizes

// ✗ Bad - frequent insertions in middle
for (int i = 0; i < 1000; i++) {
    list.add(0, "item" + i);  // O(n) each time - total O(n²)
}

// ✓ Better - add at end, then reverse if needed
for (int i = 0; i < 1000; i++) {
    list.add("item" + i);     // O(1) each time - total O(n)
}
Collections.reverse(list);    // O(n) - much better than O(n²)
```

### 92. What is the time complexity of HashMap operations?

HashMap uses a **hash table** with **separate chaining**, providing excellent average-case performance.

#### **Time Complexity Summary:**

| Operation | Average Case | Worst Case (Java 7) | Worst Case (Java 8+) | Notes |
|-----------|--------------|---------------------|----------------------|-------|
| **get(key)** | O(1) | O(n) | O(log n) | Tree conversion in Java 8+ |
| **put(key, value)** | O(1) | O(n) | O(log n) | Tree conversion in Java 8+ |
| **remove(key)** | O(1) | O(n) | O(log n) | Tree conversion in Java 8+ |
| **containsKey(key)** | O(1) | O(n) | O(log n) | Same as get() |
| **size()** | O(1) | O(1) | O(1) | Cached value |

#### **Average Case - O(1):**
```java
Map<String, Integer> map = new HashMap<>();

// All O(1) average case
map.put("apple", 5);           // O(1) - good hash distribution
map.put("banana", 3);          // O(1)
map.put("cherry", 8);          // O(1)

Integer apples = map.get("apple");      // O(1) - direct bucket access
boolean hasKey = map.containsKey("banana"); // O(1)
Integer removed = map.remove("cherry");     // O(1)
```

#### **Worst Case Scenario:**
```java
public class HashMapWorstCase {
    // Bad hash function - all keys map to same bucket
    static class BadHashKey {
        String value;
        
        BadHashKey(String value) { this.value = value; }
        
        @Override
        public int hashCode() { return 1; }  // All keys hash to bucket 1
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof BadHashKey && 
                   Objects.equals(this.value, ((BadHashKey) obj).value);
        }
    }
    
    public void demonstrateWorstCase() {
        Map<BadHashKey, String> map = new HashMap<>();
        
        // All keys go to same bucket - creates long chain
        for (int i = 0; i < 1000; i++) {
            map.put(new BadHashKey("key" + i), "value" + i);
        }
        
        // Java 7: O(n) - linear search through chain
        // Java 8+: O(log n) - chain converts to balanced tree after 8 elements
        String value = map.get(new BadHashKey("key500"));
    }
}
```

#### **Java 8+ Tree Conversion:**
```java
// When bucket chain length > TREEIFY_THRESHOLD (8):
// Linked list converts to balanced red-black tree
// Improves worst-case from O(n) to O(log n)

// Tree conversion conditions:
// 1. Chain length > 8
// 2. Total map size > 64 (MIN_TREEIFY_CAPACITY)
// 3. Keys implement Comparable or use System.identityHashCode()
```

#### **Load Factor Impact:**
```java
public class LoadFactorImpact {
    public void demonstrateLoadFactor() {
        // Default load factor = 0.75
        Map<Integer, String> defaultMap = new HashMap<>();
        
        // Lower load factor - fewer collisions, more memory
        Map<Integer, String> lowLoadMap = new HashMap<>(16, 0.5f);
        
        // Higher load factor - more collisions, less memory
        Map<Integer, String> highLoadMap = new HashMap<>(16, 0.9f);
        
        // Performance comparison:
        // Low load factor: Faster access, more memory usage
        // High load factor: Slower access, less memory usage
    }
}
```

### 93. What is the time complexity of TreeMap operations?

TreeMap uses a **Red-Black Tree** (self-balancing binary search tree), providing guaranteed logarithmic performance.

#### **Time Complexity Summary:**

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| **get(key)** | O(log n) | Tree traversal |
| **put(key, value)** | O(log n) | Insert + rebalance |
| **remove(key)** | O(log n) | Delete + rebalance |
| **containsKey(key)** | O(log n) | Same as get() |
| **firstKey() / lastKey()** | O(log n) | Navigate to min/max |
| **higherKey() / lowerKey()** | O(log n) | Tree navigation |
| **subMap()** | O(log n) | Create view |

#### **Basic Operations:**
```java
TreeMap<String, Integer> treeMap = new TreeMap<>();

// All operations are O(log n)
treeMap.put("banana", 2);     // O(log n) - insert and rebalance
treeMap.put("apple", 5);      // O(log n)
treeMap.put("cherry", 3);     // O(log n)

Integer apples = treeMap.get("apple");        // O(log n) - tree traversal
boolean hasKey = treeMap.containsKey("banana"); // O(log n)
Integer removed = treeMap.remove("cherry");     // O(log n) - delete and rebalance
```

#### **Navigation Operations:**
```java
TreeMap<Integer, String> numbers = new TreeMap<>();
numbers.put(5, "five");
numbers.put(2, "two");
numbers.put(8, "eight");
numbers.put(1, "one");
numbers.put(7, "seven");

// All navigation operations are O(log n)
Integer first = numbers.firstKey();        // O(log n) - leftmost node
Integer last = numbers.lastKey();          // O(log n) - rightmost node
Integer lower = numbers.lowerKey(5);       // O(log n) - largest < 5
Integer higher = numbers.higherKey(5);     // O(log n) - smallest > 5
Integer floor = numbers.floorKey(4);       // O(log n) - largest <= 4
Integer ceiling = numbers.ceilingKey(4);   // O(log n) - smallest >= 4
```

#### **Range Operations:**
```java
// Range views are O(log n) to create, then depend on range size
NavigableMap<Integer, String> subMap = numbers.subMap(2, true, 7, true);
// Creates view from 2 to 7 (inclusive) - O(log n)

NavigableMap<Integer, String> headMap = numbers.headMap(5, false);
// Creates view of elements < 5 - O(log n)

NavigableMap<Integer, String> tailMap = numbers.tailMap(5, true);
// Creates view of elements >= 5 - O(log n)
```

#### **Performance Comparison:**
```java
public class TreeMapPerformance {
    public void compareWithHashMap() {
        Map<Integer, String> hashMap = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        
        // Insertion performance
        for (int i = 0; i < 100000; i++) {
            hashMap.put(i, "value" + i);   // O(1) average - faster
            treeMap.put(i, "value" + i);   // O(log n) - slower but sorted
        }
        
        // Access performance
        String value1 = hashMap.get(50000);   // O(1) average - faster
        String value2 = treeMap.get(50000);   // O(log n) - slower
        
        // TreeMap advantages: sorted order, range operations
        // HashMap advantages: faster access, lower memory overhead
    }
}
```

### 94. How do you choose the right collection for your use case?

Collection choice depends on **access patterns**, **performance requirements**, and **functional needs**.

#### **Decision Matrix:**

| Use Case | Recommended Collection | Reason |
|----------|----------------------|---------|
| **Indexed access** | ArrayList | O(1) random access |
| **Frequent insertions** | LinkedList | O(1) at known positions |
| **Key-value lookup** | HashMap | O(1) average access |
| **Sorted key-value** | TreeMap | O(log n) with ordering |
| **Unique elements** | HashSet | O(1) uniqueness check |
| **Sorted unique elements** | TreeSet | O(log n) with ordering |
| **FIFO queue** | LinkedList, ArrayDeque | O(1) queue operations |
| **Priority queue** | PriorityQueue | O(log n) priority ordering |
| **Thread-safe** | ConcurrentHashMap, Vector | Built-in synchronization |

#### **Access Pattern Analysis:**

**1. Random Access vs Sequential Access:**
```java
// ✓ ArrayList - frequent random access
public void processDataByIndex(List<String> data) {
    for (int i = 0; i < data.size(); i++) {
        if (shouldProcess(i)) {
            String item = data.get(i);  // O(1) with ArrayList
            process(item);
        }
    }
}

// ✓ LinkedList - sequential access with modifications
public void processAndModify(LinkedList<String> data) {
    Iterator<String> it = data.iterator();
    while (it.hasNext()) {
        String item = it.next();
        if (shouldRemove(item)) {
            it.remove();  // O(1) with iterator
        }
    }
}
```

**2. Lookup Requirements:**
```java
// ✓ HashMap - fast key-based lookup
Map<String, User> userCache = new HashMap<>();
User user = userCache.get(userId);  // O(1) average

// ✓ TreeMap - sorted keys with range queries
TreeMap<Date, Event> eventLog = new TreeMap<>();
Map<Date, Event> todayEvents = eventLog.subMap(startOfDay, endOfDay);

// ✓ HashSet - fast membership testing
Set<String> validCodes = new HashSet<>(Arrays.asList("A", "B", "C"));
boolean isValid = validCodes.contains(inputCode);  // O(1) average
```

**3. Ordering Requirements:**
```java
// ✓ LinkedHashMap - insertion order preservation
Map<String, String> orderedConfig = new LinkedHashMap<>();
// Maintains order for iteration

// ✓ TreeSet - natural ordering
Set<String> sortedNames = new TreeSet<>();
// Always sorted for iteration

// ✓ PriorityQueue - priority-based ordering
Queue<Task> taskQueue = new PriorityQueue<>(Comparator.comparing(Task::getPriority));
```

#### **Performance vs Features Trade-off:**
```java
public class CollectionTradeoffs {
    // Fast access, no ordering
    Map<String, Object> fastMap = new HashMap<>();
    
    // Slower access, maintains order
    Map<String, Object> orderedMap = new TreeMap<>();
    
    // Fast access, insertion order
    Map<String, Object> insertionOrderMap = new LinkedHashMap<>();
    
    // Thread-safe, good performance
    Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
    
    // Thread-safe, poor performance
    Map<String, Object> syncMap = Collections.synchronizedMap(new HashMap<>());
}
```

#### **Size and Memory Considerations:**
```java
// ✓ ArrayList - known size, frequent access
List<String> knownSizeList = new ArrayList<>(1000);  // Pre-size to avoid resizing

// ✓ LinkedList - unknown size, frequent modifications
List<String> dynamicList = new LinkedList<>();  // No wasted space

// ✓ HashMap - load factor tuning
Map<String, Object> memoryOptimized = new HashMap<>(100, 0.9f);  // Higher load factor
Map<String, Object> speedOptimized = new HashMap<>(100, 0.5f);   // Lower load factor
```

### 95. What are the best practices for using collections?

#### **1. Use Generics for Type Safety:**
```java
// ✓ Good - type safe
List<String> names = new ArrayList<>();
names.add("Alice");
String name = names.get(0);  // No casting needed

// ✗ Bad - not type safe
List rawList = new ArrayList();
rawList.add("Alice");
rawList.add(123);  // Runtime error waiting to happen
String name = (String) rawList.get(0);  // Explicit casting required
```

#### **2. Program to Interfaces:**
```java
// ✓ Good - flexible, can change implementation
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
Set<String> set = new HashSet<>();

// ✗ Bad - tied to specific implementation
ArrayList<String> arrayList = new ArrayList<>();
HashMap<String, Integer> hashMap = new HashMap<>();
```

#### **3. Initialize with Appropriate Capacity:**
```java
// ✓ Good - avoid unnecessary resizing
List<String> list = new ArrayList<>(1000);  // Known size
Map<String, Object> map = new HashMap<>(100, 0.75f);  // Expected size

// ✗ Bad - multiple resizes
List<String> smallList = new ArrayList<>();  // Default size 10
for (int i = 0; i < 1000; i++) {
    smallList.add("item" + i);  // Multiple resizes
}
```

#### **4. Use Enhanced For Loops:**
```java
List<String> items = Arrays.asList("a", "b", "c");

// ✓ Good - clean and efficient
for (String item : items) {
    process(item);
}

// ✗ Bad - verbose and error-prone
for (int i = 0; i < items.size(); i++) {
    String item = items.get(i);
    process(item);
}
```

#### **5. Safe Iteration and Modification:**
```java
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));

// ✓ Good - use iterator for safe removal
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (shouldRemove(item)) {
        it.remove();  // Safe removal
    }
}

// ✓ Good - collect items to remove, then remove
List<String> toRemove = new ArrayList<>();
for (String item : list) {
    if (shouldRemove(item)) {
        toRemove.add(item);
    }
}
list.removeAll(toRemove);

// ✗ Bad - ConcurrentModificationException
for (String item : list) {
    if (shouldRemove(item)) {
        list.remove(item);  // Throws exception
    }
}
```

#### **6. Choose Thread-Safe Collections Appropriately:**
```java
// ✓ Good - modern concurrent collections
Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
List<String> copyOnWriteList = new CopyOnWriteArrayList<>();

// ✓ Good - synchronized wrappers when needed
Map<String, Object> syncMap = Collections.synchronizedMap(new HashMap<>());
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// ✗ Bad - using legacy thread-safe collections
Hashtable<String, Object> hashtable = new Hashtable<>();  // Use ConcurrentHashMap instead
Vector<String> vector = new Vector<>();  // Use ArrayList with synchronization if needed
```

#### **7. Use Immutable Collections When Possible:**
```java
// ✓ Good - immutable collections (Java 9+)
List<String> immutableList = List.of("a", "b", "c");
Set<String> immutableSet = Set.of("x", "y", "z");
Map<String, Integer> immutableMap = Map.of("key1", 1, "key2", 2);

// ✓ Good - defensive copying
public List<String> getItems() {
    return new ArrayList<>(items);  // Return copy, not original
}

// ✓ Good - unmodifiable views
List<String> readOnlyView = Collections.unmodifiableList(originalList);
```

#### **8. Null Handling:**
```java
// ✓ Good - null-safe operations
List<String> list = new ArrayList<>();
if (list != null && !list.isEmpty()) {
    process(list);
}

// ✓ Good - use Optional for single values
Optional<String> optionalValue = Optional.ofNullable(getValue());
optionalValue.ifPresent(this::process);

// ✗ Bad - not handling nulls
list.add(null);  // Some collections don't allow nulls (TreeSet, ConcurrentHashMap)
```

#### **9. Performance Monitoring:**
```java
public class CollectionPerformanceMonitoring {
    public void monitorPerformance() {
        // Monitor collection sizes
        if (cache.size() > MAX_CACHE_SIZE) {
            cache.clear();  // Prevent memory leaks
        }
        
        // Use appropriate collection for use case
        if (frequentRandomAccess) {
            useArrayList();
        } else if (frequentInsertions) {
            useLinkedList();
        }
        
        // Profile and measure actual performance
        long start = System.nanoTime();
        performOperation();
        long duration = System.nanoTime() - start;
        logPerformance(duration);
    }
}
```

#### **10. Documentation and Contracts:**
```java
/**
 * Returns an unmodifiable view of user preferences.
 * Changes to the underlying map will be reflected in the returned map.
 * 
 * @return unmodifiable map of preferences, never null
 */
public Map<String, String> getPreferences() {
    return Collections.unmodifiableMap(preferences);
}

/**
 * Adds items to the processing queue.
 * This method is thread-safe.
 * 
 * @param items items to add, must not be null
 * @throws IllegalArgumentException if items is null or contains null elements
 */
public void addItems(List<Item> items) {
    Objects.requireNonNull(items, "Items cannot be null");
    // Implementation
}
```
