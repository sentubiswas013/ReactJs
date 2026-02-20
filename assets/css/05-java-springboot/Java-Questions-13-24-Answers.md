## 2) Collections Framework & Performance Tuning

### 13. Explain the Collections hierarchy.

**Answer:**
The Collections hierarchy in Java is organized under the Collection interface and Map interface. At the top, we have the Iterable interface, which Collection extends. Collection is the root interface for List, Set, and Queue. List implementations include ArrayList, LinkedList, and Vector. Set implementations include HashSet, LinkedHashSet, and TreeSet. Queue implementations include PriorityQueue and Deque. Separately, Map interface has HashMap, LinkedHashMap, TreeMap, and Hashtable implementations.

**Example:**
```java
// List - ordered, allows duplicates
List<String> list = new ArrayList<>();
list.add("A");

// Set - no duplicates
Set<String> set = new HashSet<>();
set.add("A");

// Map - key-value pairs
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);

// Queue - FIFO
Queue<String> queue = new LinkedList<>();
queue.offer("A");
```

---

### 14. `ArrayList` vs `LinkedList` - when to use which?

**Answer:**
ArrayList uses a dynamic array internally, providing fast random access with O(1) time complexity for get operations. It's best when you need frequent read operations and random access. LinkedList uses a doubly-linked list structure, providing O(1) insertion and deletion at both ends but O(n) for random access. Use ArrayList for read-heavy operations and LinkedList when you have frequent insertions and deletions at the beginning or middle of the list.

**Example:**
```java
// ArrayList - fast random access
List<String> arrayList = new ArrayList<>();
arrayList.add("A");
String item = arrayList.get(0); // O(1)

// LinkedList - fast insertion/deletion
List<String> linkedList = new LinkedList<>();
linkedList.add(0, "A"); // O(1) at head
linkedList.remove(0); // O(1) at head
```

---

### 15. How does HashMap handle collisions (Java 7 vs Java 8)?

**Answer:**
In Java 7, HashMap handles collisions using a linked list. When multiple keys hash to the same bucket, they're stored in a linked list, making worst-case lookup O(n). In Java 8, when a bucket's linked list exceeds a threshold of 8 elements, it's converted to a balanced tree (Red-Black tree), improving worst-case performance from O(n) to O(log n). When the tree size drops below 6, it converts back to a linked list.

**Example:**
```java
Map<String, Integer> map = new HashMap<>();

// Both keys might hash to same bucket
map.put("key1", 1);
map.put("key2", 2);

// Java 7: Linked list for collisions
// Java 8: Tree structure if > 8 collisions
```

---

### 16. Explain `ConcurrentHashMap` internal working.

**Answer:**
ConcurrentHashMap provides thread-safe operations without locking the entire map. In Java 8+, it uses CAS (Compare-And-Swap) operations and synchronized blocks on individual buckets. Instead of locking the whole map, it locks only the specific bucket being modified. Read operations are generally lock-free. It divides the map into segments, allowing multiple threads to read and write to different segments concurrently, achieving better performance than synchronized HashMap or Hashtable.

**Example:**
```java
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// Thread-safe operations without external synchronization
concurrentMap.put("key1", 1);
concurrentMap.putIfAbsent("key2", 2);
concurrentMap.computeIfAbsent("key3", k -> 3);

// Multiple threads can safely access different buckets
```

---

### 17. Difference between `HashMap`, `LinkedHashMap`, `TreeMap`.

**Answer:**
HashMap provides O(1) average time complexity for get and put operations but doesn't maintain any order. LinkedHashMap extends HashMap and maintains insertion order using a doubly-linked list, with slightly more memory overhead. TreeMap implements NavigableMap and stores entries in sorted order based on natural ordering or a custom Comparator, with O(log n) time complexity for operations. Use HashMap for performance, LinkedHashMap for predictable iteration order, and TreeMap for sorted keys.

**Example:**
```java
// HashMap - no order
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("C", 3);
hashMap.put("A", 1);
hashMap.put("B", 2);

// LinkedHashMap - insertion order
Map<String, Integer> linkedMap = new LinkedHashMap<>();
linkedMap.put("C", 3);
linkedMap.put("A", 1); // maintains C, A, B order

// TreeMap - sorted order
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("C", 3);
treeMap.put("A", 1); // stored as A, B, C
```

---

### 18. What is fail-fast vs fail-safe iterator?

**Answer:**
Fail-fast iterators throw ConcurrentModificationException if the collection is modified during iteration, except through the iterator's own remove method. They work directly on the original collection. Examples include ArrayList, HashMap iterators. Fail-safe iterators work on a clone or snapshot of the collection, so they don't throw exceptions when the original collection is modified. They're used in concurrent collections like CopyOnWriteArrayList and ConcurrentHashMap. The tradeoff is that fail-safe iterators may not reflect the latest state.

**Example:**
```java
// Fail-fast - throws ConcurrentModificationException
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String s : list) {
    list.remove(s); // throws exception
}

// Fail-safe - no exception
List<String> safeList = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
for (String s : safeList) {
    safeList.remove(s); // works fine
}
```

---

### 19. How do you make a collection thread-safe?

**Answer:**
You can make a collection thread-safe using Collections.synchronizedList/Set/Map wrappers, which synchronize all methods. However, this locks the entire collection. Better alternatives include using concurrent collections like ConcurrentHashMap, CopyOnWriteArrayList, or ConcurrentLinkedQueue, which provide better concurrency through fine-grained locking or lock-free algorithms. For read-heavy scenarios, CopyOnWriteArrayList is ideal. For high-concurrency maps, use ConcurrentHashMap.

**Example:**
```java
// Synchronized wrapper - locks entire collection
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Concurrent collections - better performance
List<String> cowList = new CopyOnWriteArrayList<>();
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();

// Manual synchronization
synchronized(syncList) {
    syncList.add("A");
}
```

---

### 20. Explain time complexity of ArrayList, LinkedList, HashMap operations.

**Answer:**
ArrayList provides O(1) for get and set by index, O(1) amortized for add at end, but O(n) for add/remove at specific positions due to shifting. LinkedList provides O(1) for add/remove at head or tail, but O(n) for get by index and O(n) for add/remove at specific positions. HashMap provides O(1) average case for get, put, and remove operations, but O(n) worst case if all keys hash to the same bucket, though Java 8+ improves this to O(log n) with tree structure.

**Example:**
```java
// ArrayList
List<String> arrayList = new ArrayList<>();
arrayList.add("A");           // O(1) amortized
arrayList.get(0);             // O(1)
arrayList.add(0, "B");        // O(n) - shifts elements

// LinkedList
List<String> linkedList = new LinkedList<>();
linkedList.add("A");          // O(1)
linkedList.get(0);            // O(n)
((LinkedList<String>)linkedList).addFirst("B"); // O(1)

// HashMap
Map<String, Integer> map = new HashMap<>();
map.put("key", 1);            // O(1) average
map.get("key");               // O(1) average
```

---

### 21. What is the load factor and initial capacity in HashMap?

**Answer:**
Initial capacity is the number of buckets in the HashMap when it's created, defaulting to 16. Load factor is a threshold that determines when the HashMap should be resized, defaulting to 0.75. When the number of entries exceeds capacity times load factor, the HashMap doubles its capacity and rehashes all entries. A load factor of 0.75 provides a good balance between time and space complexity. Lower load factor means more memory but fewer collisions, while higher load factor saves memory but increases collision probability.

**Example:**
```java
// Default: capacity=16, loadFactor=0.75
Map<String, Integer> map1 = new HashMap<>();

// Custom capacity and load factor
Map<String, Integer> map2 = new HashMap<>(32, 0.75f);

// Resize happens when size > capacity * loadFactor
// With default: resize at 16 * 0.75 = 12 entries
Map<String, Integer> map3 = new HashMap<>(100); // resize at 75 entries
```

---

### 22. Difference between `Comparable` and `Comparator`.

**Answer:**
Comparable is an interface that a class implements to define its natural ordering using the compareTo method. It modifies the class itself and provides a single sorting sequence. Comparator is a separate interface that defines custom ordering logic in a compare method without modifying the original class. You can have multiple Comparator implementations for different sorting strategies. Use Comparable for default natural ordering and Comparator for custom or multiple sorting options.

**Example:**
```java
// Comparable - natural ordering
class Employee implements Comparable<Employee> {
    String name;
    int age;
    
    public int compareTo(Employee e) {
        return this.age - e.age; // sort by age
    }
}

// Comparator - custom ordering
Comparator<Employee> nameComparator = (e1, e2) -> e1.name.compareTo(e2.name);
Comparator<Employee> ageComparator = Comparator.comparingInt(e -> e.age);

List<Employee> list = new ArrayList<>();
Collections.sort(list); // uses Comparable
Collections.sort(list, nameComparator); // uses Comparator
```

---

### 23. When would you use `WeakHashMap`?

**Answer:**
WeakHashMap is used when you want entries to be automatically removed when the key is no longer in use elsewhere in the application. It uses weak references for keys, allowing the garbage collector to reclaim keys that have no strong references. This is useful for implementing caches where you don't want to prevent garbage collection of keys. When a key is garbage collected, its entry is automatically removed from the map. It's ideal for memory-sensitive caching scenarios.

**Example:**
```java
Map<Object, String> weakMap = new WeakHashMap<>();

Object key = new Object();
weakMap.put(key, "value");

// Entry exists
System.out.println(weakMap.size()); // 1

// Remove strong reference
key = null;
System.gc(); // suggest garbage collection

// Entry may be removed after GC
System.out.println(weakMap.size()); // possibly 0
```

---

### 24. How do you choose the right collection for performance?

**Answer:**
Choose based on your access patterns and requirements. Use ArrayList for random access and read-heavy operations. Use LinkedList for frequent insertions/deletions at ends. Use HashSet for unique elements with fast lookup. Use TreeSet for sorted unique elements. Use HashMap for key-value pairs with fast access. Use LinkedHashMap to maintain insertion order. Use TreeMap for sorted keys. Use ConcurrentHashMap for thread-safe maps. Use PriorityQueue for priority-based processing. Consider memory overhead, thread-safety needs, ordering requirements, and operation frequencies.

**Example:**
```java
// Fast random access, read-heavy
List<String> arrayList = new ArrayList<>();

// Frequent insertions at head/tail
Deque<String> linkedList = new LinkedList<>();

// Fast lookup, no duplicates
Set<String> hashSet = new HashSet<>();

// Sorted elements
Set<String> treeSet = new TreeSet<>();

// Key-value, fast access
Map<String, Integer> hashMap = new HashMap<>();

// Thread-safe map
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// Priority-based processing
Queue<Integer> priorityQueue = new PriorityQueue<>();
```

