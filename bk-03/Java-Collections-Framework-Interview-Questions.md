# 7. Java Collections Framework

## 1. What is Java Collections Framework?

Java Collections Framework is a unified architecture for storing and manipulating groups of objects. It provides interfaces, implementations, and algorithms to work with collections efficiently.

- Provides common interfaces like List, Set, Map
- Ready-to-use implementations like ArrayList, HashMap
- Algorithms for sorting, searching, shuffling
- Reduces programming effort and increases performance

```java
List<String> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();
```

## 2. What is the difference between ArrayList and LinkedList?

ArrayList uses dynamic arrays for storage, while LinkedList uses doubly-linked nodes. This affects their performance characteristics for different operations.

**ArrayList:**
- Random access O(1)
- Insertion/deletion at middle O(n)
- Better for frequent access
- Contiguous memory storage

**LinkedList:**
- Sequential access O(n)
- Insertion/deletion O(1) if node known
- Better for frequent modifications
- Non-contiguous memory storage

```java
List<String> arrayList = new ArrayList<>(); // Fast access
List<String> linkedList = new LinkedList<>(); // Fast insertion/deletion
```

## 3. What is the difference between HashMap and TreeMap?

HashMap provides O(1) average access time with no ordering, while TreeMap maintains sorted order with O(log n) access time.

**HashMap:**
- Hash table implementation
- O(1) average time complexity
- No ordering of keys
- Allows one null key

**TreeMap:**
- Red-black tree implementation
- O(log n) time complexity
- Sorted order of keys
- No null keys allowed

```java
Map<String, Integer> hashMap = new HashMap<>(); // Fast, unordered
Map<String, Integer> treeMap = new TreeMap<>(); // Slower, sorted
```

## 4. What is the difference between HashMap and Hashtable?

HashMap is not synchronized and allows null values, while Hashtable is synchronized and doesn't allow nulls. HashMap is preferred for single-threaded applications.

**HashMap:**
- Not synchronized (not thread-safe)
- Allows one null key and multiple null values
- Introduced in Java 1.2
- Better performance

**Hashtable:**
- Synchronized (thread-safe)
- No null keys or values allowed
- Legacy class from Java 1.0
- Slower due to synchronization

```java
Map<String, Integer> hashMap = new HashMap<>(); // Modern, faster
Map<String, Integer> hashtable = new Hashtable<>(); // Legacy, thread-safe
```

## 5. How does HashMap work internally?

HashMap uses an array of buckets where each bucket can hold multiple key-value pairs. It uses hashing to determine which bucket to use for storing entries.

- Uses array of Node objects (buckets)
- Hash function determines bucket index
- Handles collisions with chaining (linked list/tree)
- Rehashing occurs when load factor exceeds threshold

```java
// Simplified internal process:
// 1. hash(key) -> bucket index
// 2. Store/retrieve from that bucket
// 3. Handle collisions in same bucket

Map<String, Integer> map = new HashMap<>();
map.put("key", 100); // hash("key") -> bucket index -> store
```

## 6. What is hash collision and how is it handled?

Hash collision occurs when two different keys produce the same hash code, mapping to the same bucket. HashMap handles this using chaining and tree conversion.

- **Chaining:** Multiple entries in same bucket form linked list
- **Tree conversion:** When chain length > 8, converts to balanced tree
- **Load factor:** Rehashing when buckets become too full
- **Open addressing:** Alternative approach (not used in HashMap)

```java
// Collision example:
// hash("Aa") and hash("BB") might produce same value
map.put("Aa", 1);
map.put("BB", 2); // Collision - stored in same bucket as linked list
```

## 7. What is the difference between fail-fast and fail-safe iterators?

Fail-fast iterators throw ConcurrentModificationException when collection is modified during iteration, while fail-safe iterators work on a copy and don't throw exceptions.

**Fail-fast:**
- Detects concurrent modifications
- Throws ConcurrentModificationException
- Examples: ArrayList, HashMap iterators
- Works on original collection

**Fail-safe:**
- Allows concurrent modifications
- Works on cloned copy
- Examples: ConcurrentHashMap, CopyOnWriteArrayList
- May not reflect latest changes

```java
List<String> list = new ArrayList<>();
Iterator<String> failFast = list.iterator(); // Throws exception if modified

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
Iterator<String> failSafe = map.keySet().iterator(); // Safe for modifications
```

## 8. What is the difference between Comparable and Comparator?

Comparable provides natural ordering by implementing compareTo() method in the class itself, while Comparator provides custom ordering through external comparison logic.

**Comparable:**
- Single sorting sequence
- compareTo() method in class
- Natural ordering
- Part of java.lang package

**Comparator:**
- Multiple sorting sequences
- External compare() method
- Custom ordering
- Part of java.util package

```java
// Comparable - natural ordering
class Student implements Comparable<Student> {
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}

// Comparator - custom ordering
Comparator<Student> ageComparator = (s1, s2) -> s1.age - s2.age;
Collections.sort(students, ageComparator);
```

## 9. What is WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?

These are specialized collection implementations with unique characteristics for specific use cases.

**WeakHashMap:**
- Keys are weak references
- Entries removed when key is garbage collected
- Useful for caches and memory-sensitive applications

**IdentityHashMap:**
- Uses == instead of equals() for key comparison
- Allows duplicate "equal" keys
- Useful when object identity matters

**LinkedHashMap:**
- Maintains insertion or access order
- Combines HashMap performance with predictable iteration
- Useful for LRU caches

**PriorityQueue:**
- Heap-based priority queue
- Elements ordered by natural ordering or Comparator
- Useful for scheduling and algorithms

```java
Map<String, Integer> weakMap = new WeakHashMap<>(); // GC-friendly
Map<String, Integer> identityMap = new IdentityHashMap<>(); // Identity-based
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // Ordered
Queue<Integer> priorityQueue = new PriorityQueue<>(); // Heap-based
```