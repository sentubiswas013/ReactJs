# 🔵 6. Java Collections Framework Questions (104-112)

# 🔹 1. Core Collections

### 104. What is the Collections Framework in Java? (25 seconds)
• **Unified architecture** - Standard way to store and manipulate groups of objects
• **Interfaces and implementations** - List, Set, Map with ArrayList, HashSet, HashMap
• **Algorithms** - Sorting, searching, shuffling through Collections class
• **Performance benefits** - Optimized data structures for different use cases

```java
// Collections Framework example
List<String> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();

Collections.sort(list);
Collections.shuffle(list);
```

### 105. What are the main interfaces in the Collections Framework? (20 seconds)
• **Collection** - Root interface for all collections
• **List** - Ordered collection allowing duplicates (ArrayList, LinkedList)
• **Set** - Collection with no duplicates (HashSet, TreeSet)
• **Map** - Key-value pairs (HashMap, TreeMap)
• **Queue** - FIFO operations (LinkedList, PriorityQueue)

```java
Collection<String> collection = new ArrayList<>();
List<String> list = new ArrayList<>();
Set<String> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();
Queue<String> queue = new LinkedList<>();
```

### 106. What is the difference between Collection and Collections in Java? (20 seconds)
• **Collection** - Interface that represents a group of objects
• **Collections** - Utility class with static methods for collection operations
• **Collection** - Extended by List, Set, Queue interfaces
• **Collections** - Provides sort(), reverse(), shuffle(), synchronizedList()

```java
// Collection interface
Collection<String> collection = new ArrayList<>();

// Collections utility class
Collections.sort(list);
Collections.reverse(list);
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

### 107. What is the difference between List and Set interfaces? (25 seconds)
• **List allows duplicates** - Can store same element multiple times
• **Set prevents duplicates** - Each element appears only once
• **List maintains insertion order** - Elements stored in sequence
• **List provides indexed access** - get(index), set(index, element)
• **Set focuses on uniqueness** - Uses equals() and hashCode()

```java
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Java"); // Duplicate allowed
System.out.println(list.size()); // 2

Set<String> set = new HashSet<>();
set.add("Java");
set.add("Java"); // Duplicate ignored
System.out.println(set.size()); // 1
```

### 108. What is the difference between ArrayList and LinkedList? (30 seconds)
• **ArrayList** - Dynamic array, fast random access O(1)
• **LinkedList** - Doubly linked list, fast insertion/deletion O(1)
• **ArrayList** - Better for frequent reading and iteration
• **LinkedList** - Better for frequent insertion/deletion in middle
• **Memory** - ArrayList uses less memory per element

```java
// ArrayList - fast access
List<String> arrayList = new ArrayList<>();
String element = arrayList.get(5); // O(1) access

// LinkedList - fast insertion
List<String> linkedList = new LinkedList<>();
linkedList.add(2, "newElement"); // O(1) at known position
```

### 109. What is the difference between ArrayList and Vector? (25 seconds)
• **Synchronization** - Vector is synchronized, ArrayList is not
• **Performance** - ArrayList is faster due to no synchronization overhead
• **Growth** - Vector doubles size, ArrayList grows by 50%
• **Legacy** - Vector is older, ArrayList is preferred
• **Thread safety** - Use Collections.synchronizedList() for ArrayList

```java
// ArrayList (not synchronized)
List<String> arrayList = new ArrayList<>();

// Vector (synchronized)
List<String> vector = new Vector<>();

// Make ArrayList thread-safe
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

### 110. What is the difference between HashMap and Hashtable? (30 seconds)
• **Synchronization** - Hashtable is synchronized, HashMap is not
• **Null values** - HashMap allows one null key and multiple null values
• **Hashtable** - No null keys or values allowed
• **Performance** - HashMap is faster due to no synchronization
• **Inheritance** - Hashtable extends Dictionary, HashMap extends AbstractMap

```java
// HashMap (allows null, not synchronized)
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put(null, 1); // Allowed
hashMap.put("key", null); // Allowed

// Hashtable (no null, synchronized)
Map<String, Integer> hashtable = new Hashtable<>();
// hashtable.put(null, 1); // NullPointerException
```

### 111. What is the difference between HashMap and TreeMap? (25 seconds)
• **Ordering** - TreeMap maintains sorted order, HashMap doesn't
• **Performance** - HashMap O(1) access, TreeMap O(log n)
• **Null keys** - HashMap allows one null key, TreeMap doesn't
• **Implementation** - HashMap uses hash table, TreeMap uses Red-Black tree
• **Use case** - TreeMap for sorted data, HashMap for fast access

```java
// HashMap - fast access, no ordering
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("c", 3);
hashMap.put("a", 1);
hashMap.put("b", 2);

// TreeMap - sorted order
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("c", 3);
treeMap.put("a", 1);
treeMap.put("b", 2);
// Iteration order: a, b, c
```

### 112. What is the difference between HashSet and TreeSet? (25 seconds)
• **Ordering** - TreeSet maintains sorted order, HashSet doesn't
• **Performance** - HashSet O(1) operations, TreeSet O(log n)
• **Null values** - HashSet allows one null, TreeSet doesn't
• **Implementation** - HashSet uses hash table, TreeSet uses Red-Black tree
• **Interface** - TreeSet implements NavigableSet with additional methods

```java
// HashSet - fast operations, no ordering
Set<String> hashSet = new HashSet<>();
hashSet.add("c");
hashSet.add("a");
hashSet.add("b");

// TreeSet - sorted order
Set<String> treeSet = new TreeSet<>();
treeSet.add("c");
treeSet.add("a");
treeSet.add("b");
// Iteration order: a, b, c
```
