# ✅ 1) Core Java (Basics and Advanced) - Questions & Answers

### 1. Explain `==` vs `equals()` vs `hashCode()` contract with HashMap example.

**Answer:**
`==` compares memory addresses, checking if two references point to the same object. `equals()` compares the actual content or values of objects. `hashCode()` returns an integer hash value used in hash-based collections like HashMap.

The contract says: if two objects are equal using `equals()`, they must have the same `hashCode()`. But same `hashCode()` doesn't mean objects are equal.

In HashMap, `hashCode()` determines the bucket location, then `equals()` checks if the key already exists in that bucket.

```java
String s1 = new String("hello");
String s2 = new String("hello");
s1 == s2;        // false (different objects)
s1.equals(s2);   // true (same content)
s1.hashCode() == s2.hashCode(); // true (must be same)
```

---

### 2. Why is String immutable? What are security, thread-safety, and performance benefits?

**Answer:**
String is immutable means once created, its value cannot be changed.

**Security:** Strings are used for sensitive data like passwords, database URLs. If mutable, anyone could change them.

**Thread-safety:** Multiple threads can share the same String without synchronization since it can't be modified.

**Performance:** String pool reuses String objects, saving memory. hashCode is cached, making HashMap operations faster.

---

### 3. How does HashMap work internally? Explain hashing, collision, and treeification in Java 8.

**Answer:**
HashMap stores key-value pairs in an array of buckets. When you put a key, it calculates `hashCode()`, then uses it to find the bucket index.

**Hashing:** Converts key to an integer hash, then applies bitwise operations to get bucket index.

**Collision:** When two keys land in the same bucket, they're stored as a linked list.

**Treeification (Java 8):** If a bucket has more than 8 entries, the linked list converts to a balanced tree (Red-Black tree) for faster lookup, changing O(n) to O(log n).

---

### 4. What happens when two objects have the same hashCode but are not equal?

**Answer:**
This is called a hash collision. Both objects will be stored in the same bucket. HashMap uses a linked list or tree structure to store multiple entries in one bucket.

When retrieving, HashMap first finds the bucket using `hashCode()`, then iterates through entries in that bucket using `equals()` to find the exact match.

This is why both `hashCode()` and `equals()` must be properly implemented together.

---

### 5. Difference between abstract class and interface in Java 8+.

**Answer:**
**Abstract class:**
- Can have constructors
- Can have instance variables (state)
- Can have concrete methods and abstract methods
- Supports single inheritance only
- Use when classes share common behavior and state

**Interface:**
- No constructors
- Only constants (public static final)
- Can have default and static methods (Java 8+)
- Supports multiple inheritance
- Use for defining contracts or capabilities

```java
abstract class Animal {
    String name; // state
    abstract void sound();
}

interface Flyable {
    default void fly() { } // Java 8+
}
```

---

### 6. What are default and static methods in interfaces? Why were they introduced?

**Answer:**
**Default methods:** Methods with implementation in interfaces, using `default` keyword. Classes implementing the interface can use or override them.

**Static methods:** Utility methods that belong to the interface, called using interface name.

**Why introduced:** To add new methods to interfaces without breaking existing implementations. For example, Java 8 added `forEach()` to Collection interface without breaking millions of existing classes.

```java
interface MyInterface {
    default void print() { 
        System.out.println("Default"); 
    }
    static void show() { 
        System.out.println("Static"); 
    }
}
```

---

### 7. Explain method overloading vs overriding with covariant return types.

**Answer:**
**Overloading:** Same method name, different parameters in the same class. Compile-time polymorphism.

**Overriding:** Same method signature in parent and child class. Runtime polymorphism.

**Covariant return type:** When overriding, the return type can be a subclass of the parent's return type.

```java
// Overloading
void print(int a) { }
void print(String s) { }

// Overriding with covariant return type
class Animal {
    Animal getAnimal() { return new Animal(); }
}
class Dog extends Animal {
    Dog getAnimal() { return new Dog(); } // Dog is subclass of Animal
}
```

---

### 8. What is autoboxing/unboxing? What are the performance implications?

**Answer:**
**Autoboxing:** Automatic conversion of primitive to wrapper object (int to Integer).

**Unboxing:** Automatic conversion of wrapper object to primitive (Integer to int).

**Performance implications:** 
- Creates unnecessary objects, increasing memory usage
- Slower than primitives due to object creation overhead
- Can cause NullPointerException if wrapper is null
- In loops, autoboxing can significantly impact performance

```java
Integer a = 10;        // autoboxing: int to Integer
int b = a;             // unboxing: Integer to int

// Performance issue in loop
Integer sum = 0;
for(int i = 0; i < 1000; i++) {
    sum += i;  // unboxing + boxing in each iteration
}
```

---

### 9. Difference between `String`, `StringBuilder`, `StringBuffer` with thread safety.

**Answer:**
**String:** Immutable, thread-safe by nature. Every modification creates a new object.

**StringBuilder:** Mutable, NOT thread-safe, faster. Use in single-threaded environments.

**StringBuffer:** Mutable, thread-safe (synchronized methods), slower than StringBuilder.

**When to use:**
- String: When value doesn't change
- StringBuilder: String concatenation in single thread
- StringBuffer: String concatenation in multi-threaded environment

```java
String s = "Hello";
s = s + " World";  // Creates new object

StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");  // Modifies same object, fast

StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World");  // Thread-safe but slower
```

---

### 10. How does String pool work? Explain `intern()` method and memory optimization.

**Answer:**
String pool is a special memory area in heap where Java stores String literals to save memory by reusing them.

When you create a String literal, JVM checks the pool. If it exists, returns the reference; otherwise, creates new and adds to pool.

**intern() method:** Forces a String to be added to the pool. If already exists, returns the pooled reference.

**Memory optimization:** Multiple variables can reference the same String object in the pool, saving memory.

```java
String s1 = "hello";           // goes to pool
String s2 = "hello";           // reuses from pool
s1 == s2;                      // true (same reference)

String s3 = new String("hello"); // creates new object in heap
s3 == s1;                      // false (different objects)

String s4 = s3.intern();       // returns pooled reference
s4 == s1;                      // true (same pooled reference)
```

---

### 11. Explain `final`, `finally`, `finalize` keywords with examples.

**Answer:**
**final:** 
- Variable: Value cannot be changed (constant)
- Method: Cannot be overridden
- Class: Cannot be extended

**finally:** Block that always executes after try-catch, used for cleanup (closing resources).

**finalize:** Deprecated method called by garbage collector before destroying an object. Don't use it; use try-with-resources instead.

```java
// final
final int MAX = 100;           // constant
final void display() { }       // can't override
final class Utility { }        // can't extend

// finally
try {
    // code
} catch (Exception e) {
    // handle
} finally {
    // always executes - cleanup code
}

// finalize (deprecated)
protected void finalize() {
    // cleanup before GC
}
```

---

### 12. What is shallow copy vs deep copy? How do you implement deep copy?

**Answer:**
**Shallow copy:** Copies object's primitive fields, but references still point to the same objects. Changes in nested objects affect both copies.

**Deep copy:** Creates completely independent copy, including all nested objects. Changes don't affect the original.

**Implementation:**
- Clone method with manual copying
- Copy constructor
- Serialization-deserialization
- Using libraries like Apache Commons or Jackson

```java
class Address {
    String city;
}

class Person implements Cloneable {
    String name;
    Address address;
    
    // Shallow copy
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // address reference copied
    }
    
    // Deep copy
    public Person deepCopy() {
        Person copy = new Person();
        copy.name = this.name;
        copy.address = new Address(); // new object created
        copy.address.city = this.address.city;
        return copy;
    }
}
```

---

### 13. What is immutability? How do you create an immutable class?

**Answer:**
Immutability means an object's state cannot be changed after creation. Like String, once created, you can't modify it.

**How to create immutable class:**
1. Make class `final` (can't be extended)
2. Make all fields `private final`
3. No setter methods
4. Initialize all fields in constructor
5. Return deep copies of mutable objects in getters

```java
public final class Employee {
    private final String name;
    private final int id;
    private final Date joinDate;
    
    public Employee(String name, int id, Date joinDate) {
        this.name = name;
        this.id = id;
        this.joinDate = new Date(joinDate.getTime()); // deep copy
    }
    
    public String getName() { return name; }
    public int getId() { return id; }
    public Date getJoinDate() { 
        return new Date(joinDate.getTime()); // return copy
    }
}
```

---

### 14. Explain pass-by-value vs pass-by-reference in Java.

**Answer:**
Java is ALWAYS pass-by-value, not pass-by-reference.

**For primitives:** The actual value is copied. Changes inside method don't affect original.

**For objects:** The reference value (memory address) is copied, not the object itself. Both references point to the same object, so changes to object's state are visible, but reassigning the reference doesn't affect the original reference.

```java
void modify(int x) {
    x = 100;  // doesn't affect original
}

void modify(Person p) {
    p.name = "John";  // affects original object
    p = new Person(); // doesn't affect original reference
}

int a = 10;
modify(a);
// a is still 10

Person person = new Person();
modify(person);
// person.name is "John" (object modified)
// person still points to original object (reference not changed)
```

---

### 15. What is the difference between `Comparable` and `Comparator`?

**Answer:**
Both are used for sorting objects, but they work differently.

**Comparable:**
- Interface with `compareTo()` method
- Defined inside the class being sorted
- Provides single, natural ordering
- Used with `Collections.sort(list)`

**Comparator:**
- Interface with `compare()` method
- Defined outside the class
- Provides multiple custom orderings
- Used with `Collections.sort(list, comparator)`

```java
// Comparable - natural ordering
class Employee implements Comparable<Employee> {
    int id;
    public int compareTo(Employee e) {
        return this.id - e.id; // sort by id
    }
}

// Comparator - custom ordering
class NameComparator implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.name.compareTo(e2.name); // sort by name
    }
}

Collections.sort(employees); // uses Comparable
Collections.sort(employees, new NameComparator()); // uses Comparator
```

# ✅ 2) Collections Framework & Performance Tuning - Questions & Answers

### 16. Explain the complete Collections hierarchy (List, Set, Queue, Map).

**Answer:**
The Collections framework has two main branches: Collection interface and Map interface.

**Collection Interface:**
- **List:** Ordered, allows duplicates (ArrayList, LinkedList, Vector)
- **Set:** No duplicates (HashSet, LinkedHashSet, TreeSet)
- **Queue:** FIFO order (PriorityQueue, LinkedList, ArrayDeque)

**Map Interface:** Key-value pairs, keys are unique
- HashMap, LinkedHashMap, TreeMap, Hashtable

```
Collection (interface)
├── List (ArrayList, LinkedList, Vector)
├── Set (HashSet, LinkedHashSet, TreeSet)
└── Queue (PriorityQueue, ArrayDeque)

Map (interface)
└── HashMap, LinkedHashMap, TreeMap, Hashtable
```

---

### 17. `ArrayList` vs `LinkedList` - time complexity and real-world use cases.

**Answer:**
**ArrayList:** Dynamic array, stores elements in contiguous memory.
- Get by index: O(1) - very fast
- Add/Remove at end: O(1)
- Add/Remove at beginning/middle: O(n) - slow, shifts elements
- **Use when:** Frequent random access, reading data

**LinkedList:** Doubly linked list, elements scattered in memory.
- Get by index: O(n) - slow, must traverse
- Add/Remove at beginning/end: O(1) - fast
- Add/Remove in middle: O(1) if you have the node
- **Use when:** Frequent insertions/deletions, implementing Queue/Deque

```java
// ArrayList - fast access
List<String> list = new ArrayList<>();
list.get(100); // O(1) - direct access

// LinkedList - fast insertion
List<String> list = new LinkedList<>();
list.addFirst("item"); // O(1) - no shifting
```

**Real-world:** ArrayList for displaying data, LinkedList for implementing undo/redo functionality.

---

### 18. How does HashMap handle collisions in Java 7 vs Java 8?

**Answer:**
**Java 7:** Uses linked list to handle collisions. All colliding entries are stored in a linked list at the same bucket. Lookup is O(n) in worst case.

**Java 8:** Uses linked list initially, but when bucket size exceeds 8 entries, it converts to a balanced tree (Red-Black tree). This improves worst-case lookup from O(n) to O(log n).

When bucket size drops below 6, it converts back to linked list.

```java
// Java 7: Bucket with collision
Bucket[5] -> Entry1 -> Entry2 -> Entry3 -> ... (linked list)

// Java 8: Bucket with many collisions
Bucket[5] -> TreeNode (Red-Black tree structure)
```

This optimization significantly improves performance when there are many hash collisions.

---

### 19. Explain `ConcurrentHashMap` internal working and lock striping.

**Answer:**
ConcurrentHashMap is thread-safe without locking the entire map. It uses lock striping for better concurrency.

**Lock Striping (Java 7):** Map is divided into segments (default 16). Each segment has its own lock. Multiple threads can access different segments simultaneously.

**Java 8 improvement:** Uses CAS (Compare-And-Swap) operations and synchronized blocks on individual nodes instead of segments. Even better concurrency.

**Key features:**
- Read operations don't require locks
- Write operations lock only the affected bucket
- Multiple threads can read/write different buckets simultaneously

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
// Thread 1 can write to bucket 5
// Thread 2 can write to bucket 10 simultaneously
// No full map locking
```

---

### 20. Difference between `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`.

**Answer:**
**HashMap:**
- No order maintained
- Allows one null key, multiple null values
- Not thread-safe
- Best performance: O(1)

**LinkedHashMap:**
- Maintains insertion order or access order
- Allows one null key, multiple null values
- Not thread-safe
- Slightly slower than HashMap

**TreeMap:**
- Sorted order (natural or custom comparator)
- No null keys (throws NPE)
- Not thread-safe
- Slower: O(log n)

**Hashtable:**
- Legacy class, synchronized (thread-safe)
- No null keys or values
- Slower due to synchronization
- Use ConcurrentHashMap instead

```java
Map<String, Integer> hashMap = new HashMap<>();        // fast, no order
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // insertion order
Map<String, Integer> treeMap = new TreeMap<>();        // sorted
Map<String, Integer> hashtable = new Hashtable<>();    // thread-safe, legacy
```

---

### 21. What is fail-fast vs fail-safe iterator? Give examples.

**Answer:**
**Fail-fast iterator:**
- Throws ConcurrentModificationException if collection is modified during iteration
- Works on original collection
- Examples: ArrayList, HashMap, HashSet

**Fail-safe iterator:**
- Works on a copy of the collection
- Doesn't throw exception if modified
- May not reflect latest changes
- Examples: ConcurrentHashMap, CopyOnWriteArrayList

```java
// Fail-fast
List<String> list = new ArrayList<>();
list.add("A");
for(String s : list) {
    list.add("B"); // throws ConcurrentModificationException
}

// Fail-safe
List<String> list = new CopyOnWriteArrayList<>();
list.add("A");
for(String s : list) {
    list.add("B"); // No exception, works on copy
}
```

---

### 22. How do you make a collection thread-safe?

**Answer:**
There are several ways to make collections thread-safe:

**1. Collections.synchronizedXXX():**
```java
List<String> list = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
```

**2. Concurrent collections (better performance):**
```java
List<String> list = new CopyOnWriteArrayList<>();
Map<String, Integer> map = new ConcurrentHashMap<>();
Queue<String> queue = new ConcurrentLinkedQueue<>();
```

**3. Legacy synchronized collections:**
```java
Vector<String> vector = new Vector<>(); // synchronized ArrayList
Hashtable<String, Integer> table = new Hashtable<>(); // synchronized HashMap
```

**Best practice:** Use concurrent collections (ConcurrentHashMap, CopyOnWriteArrayList) for better performance.

---

### 23. Explain `Collections.synchronizedMap()` vs `ConcurrentHashMap`.

**Answer:**
**Collections.synchronizedMap():**
- Wraps any Map and synchronizes all methods
- Locks the entire map for every operation
- Only one thread can access at a time
- Poor performance in multi-threaded environment
- Iterator needs external synchronization

**ConcurrentHashMap:**
- Designed for concurrency from ground up
- Uses lock striping or CAS operations
- Multiple threads can read/write simultaneously
- Much better performance
- Iterator is fail-safe

```java
// synchronizedMap - locks entire map
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
// Only one thread can access at a time

// ConcurrentHashMap - fine-grained locking
Map<String, Integer> concMap = new ConcurrentHashMap<>();
// Multiple threads can access different buckets simultaneously
```

**Use ConcurrentHashMap** for better concurrency and performance.

---

### 24. What is the load factor and initial capacity in HashMap? How to tune them?

**Answer:**
**Initial Capacity:** Number of buckets when HashMap is created (default: 16).

**Load Factor:** Threshold to trigger resizing (default: 0.75 or 75%).

When size exceeds capacity × load factor, HashMap doubles its capacity and rehashes all entries.

**Formula:** Resize when: size > capacity × 0.75

**Tuning guidelines:**
- **High load factor (0.9):** Less memory, more collisions, slower lookup
- **Low load factor (0.5):** More memory, fewer collisions, faster lookup
- **Default (0.75):** Good balance between time and space

```java
// Default: capacity=16, loadFactor=0.75
Map<String, Integer> map = new HashMap<>();

// Custom: capacity=32, loadFactor=0.8
Map<String, Integer> map = new HashMap<>(32, 0.8f);

// If you know size in advance
Map<String, Integer> map = new HashMap<>(1000); // avoids resizing
```

**Best practice:** If you know the size, set initial capacity to avoid resizing.

---

### 25. When would you use `WeakHashMap`? Explain with use case.

**Answer:**
WeakHashMap uses weak references for keys. When a key has no strong references outside the map, it can be garbage collected automatically.

**Use case:** Caching where you want automatic memory cleanup.

**Regular HashMap:** Keys are never garbage collected even if not used elsewhere, causing memory leaks.

**WeakHashMap:** Keys are garbage collected when not referenced, preventing memory leaks.

```java
// Regular HashMap - memory leak
Map<User, UserData> cache = new HashMap<>();
User user = new User("John");
cache.put(user, new UserData());
user = null; // Key still in map, never garbage collected

// WeakHashMap - automatic cleanup
Map<User, UserData> cache = new WeakHashMap<>();
User user = new User("John");
cache.put(user, new UserData());
user = null; // Key can be garbage collected
System.gc(); // After GC, entry is removed from map
```

**Real-world:** Metadata caches, listener registries, canonicalizing mappings.

---

### 26. What is `PriorityQueue` and how does it work internally?

**Answer:**
PriorityQueue is a queue where elements are ordered by priority, not insertion order. Highest priority element is always at the front.

**Internal structure:** Uses a binary heap (min-heap by default).

**Key features:**
- Elements are ordered by natural ordering or custom Comparator
- Head is always the smallest element (min-heap)
- Not thread-safe
- Doesn't allow null elements

**Time complexity:**
- Add: O(log n)
- Poll/Remove: O(log n)
- Peek: O(1)

```java
// Natural ordering (min-heap)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.add(5);
pq.add(1);
pq.add(3);
pq.poll(); // returns 1 (smallest)

// Custom ordering (max-heap)
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
pq.add(5);
pq.add(1);
pq.add(3);
pq.poll(); // returns 5 (largest)
```

**Use case:** Task scheduling, Dijkstra's algorithm, finding top K elements.

---

### 27. Explain `EnumSet` and `EnumMap` performance benefits.

**Answer:**
EnumSet and EnumMap are specialized collections for enum types, offering superior performance.

**EnumSet:**
- Internally uses bit vector (long or long array)
- All operations are extremely fast: O(1)
- Very memory efficient
- Much faster than HashSet for enums

**EnumMap:**
- Internally uses array indexed by enum ordinal
- All operations are O(1)
- Faster and more memory efficient than HashMap
- Maintains natural enum order

```java
// EnumSet - fast and memory efficient
enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

Set<Day> weekend = EnumSet.of(Day.SAT, Day.SUN);
Set<Day> weekdays = EnumSet.range(Day.MON, Day.FRI);
Set<Day> allDays = EnumSet.allOf(Day.class);

// EnumMap - fast key-value storage
Map<Day, String> schedule = new EnumMap<>(Day.class);
schedule.put(Day.MON, "Meeting");
```

**Why faster:** Bit operations and array access are faster than hashing and tree operations.

---

### 28. How do you choose the right collection for performance optimization?

**Answer:**
Choose based on your access patterns and requirements:

**For List:**
- **ArrayList:** Random access, read-heavy operations
- **LinkedList:** Frequent insertions/deletions at beginning/end
- **CopyOnWriteArrayList:** Read-heavy, thread-safe

**For Set:**
- **HashSet:** Fast operations, no order needed
- **LinkedHashSet:** Need insertion order
- **TreeSet:** Need sorted order
- **EnumSet:** Working with enums

**For Map:**
- **HashMap:** Fast operations, no order needed
- **LinkedHashMap:** Need insertion/access order
- **TreeMap:** Need sorted order
- **ConcurrentHashMap:** Thread-safe, high concurrency
- **EnumMap:** Working with enum keys

**For Queue:**
- **ArrayDeque:** General purpose queue/stack
- **PriorityQueue:** Priority-based processing
- **LinkedList:** Simple FIFO queue

```java
// Decision tree
// Need order? -> LinkedHashMap/LinkedHashSet
// Need sorting? -> TreeMap/TreeSet
// Thread-safe? -> ConcurrentHashMap/CopyOnWriteArrayList
// Frequent random access? -> ArrayList
// Frequent insertions? -> LinkedList
// Working with enums? -> EnumSet/EnumMap
```

---

### 29. What is the time complexity of operations in ArrayList, LinkedList, HashMap, TreeMap?

**Answer:**

**ArrayList:**
- Get by index: O(1)
- Add at end: O(1) amortized
- Add/Remove at index: O(n)
- Search: O(n)

**LinkedList:**
- Get by index: O(n)
- Add/Remove at beginning/end: O(1)
- Add/Remove at index: O(n)
- Search: O(n)

**HashMap:**
- Get: O(1) average, O(n) worst case
- Put: O(1) average, O(n) worst case
- Remove: O(1) average
- ContainsKey: O(1) average

**TreeMap:**
- Get: O(log n)
- Put: O(log n)
- Remove: O(log n)
- ContainsKey: O(log n)

```java
// Quick reference
ArrayList:  get=O(1), add=O(1), remove=O(n)
LinkedList: get=O(n), add=O(1), remove=O(1)
HashMap:    get=O(1), put=O(1), remove=O(1)
TreeMap:    get=O(log n), put=O(log n), remove=O(log n)
```

**Memory tip:** 
- Array-based (ArrayList, HashMap) = O(1) access
- Tree-based (TreeMap, TreeSet) = O(log n) operations
- List-based (LinkedList) = O(n) access, O(1) add/remove at ends

# ✅ 3) Java Memory Model & Garbage Collection - 13 Questions

## 30. Explain JVM memory structure (Heap, Stack, Metaspace, Code Cache, Native Memory).

**Simple Answer:**

JVM memory is divided into five main areas:

- **Heap**: Where all objects live. Shared by all threads. Divided into Young and Old Generation. This is where garbage collection happens.

- **Stack**: Each thread gets its own stack. Stores method calls, local variables, and references. Very fast, automatically cleaned when method exits.

- **Metaspace**: Stores class metadata, method information, and static variables. Replaced PermGen in Java 8. Grows automatically, limited by system memory.

- **Code Cache**: Stores compiled native code from JIT compiler. Hot code paths get compiled to machine code for faster execution.

- **Native Memory**: Used by JVM internals, thread stacks, and native libraries. Not managed by garbage collector.

**Remember**: "HSMCN" - Heap (objects), Stack (methods), Metaspace (classes), Code (compiled), Native (JVM internals)

---

## 31. What are Young Generation (Eden, S0, S1) and Old Generation?

**Simple Answer:**

The Heap is divided into generations based on object age:

**Young Generation** (where new objects are born):
- **Eden Space**: All new objects start here. When full, Minor GC happens.
- **Survivor 0 (S0)**: Objects that survive first GC move here.
- **Survivor 1 (S1)**: Objects alternate between S0 and S1 with each GC.

**Old Generation** (Tenured):
- Objects that survive multiple Minor GCs (typically 15) get promoted here.
- Long-lived objects stay here.
- When full, Major GC happens (slower and more expensive).

**Remember**: "Eden → S0 ↔ S1 → Old" - Objects graduate through generations like school grades.

---

## 32. Explain different GC algorithms (Serial, Parallel, CMS, G1, ZGC, Shenandoah).

**Simple Answer:**

**Serial GC**: Single thread, stops everything. Good for small apps, single CPU. Simple but slow.

**Parallel GC**: Multiple threads for GC. Stops everything but faster. Good for batch jobs where throughput matters more than pauses.

**CMS (Concurrent Mark Sweep)**: Runs concurrently with application. Low pause times but uses more CPU. Deprecated in Java 14.

**G1 GC (Garbage First)**: Default since Java 9. Divides heap into regions. Predictable pause times. Balances throughput and latency. Best for most applications.

**ZGC**: Ultra-low pause times (< 10ms). Handles huge heaps (terabytes). Good for latency-sensitive apps. Available from Java 15.

**Shenandoah**: Similar to ZGC, low pause times. Works differently internally. Good alternative to ZGC.

**Remember**: Serial (1 thread) → Parallel (many threads) → CMS (concurrent) → G1 (regional) → ZGC/Shenandoah (ultra-low pause)

---

## 33. What is Minor GC vs Major GC vs Full GC?

**Simple Answer:**

**Minor GC**:
- Cleans Young Generation (Eden + Survivors).
- Happens frequently, very fast (milliseconds).
- Stops the world briefly.
- Most objects die young, so very efficient.

**Major GC**:
- Cleans Old Generation only.
- Less frequent, slower than Minor GC.
- Longer pause times.

**Full GC**:
- Cleans entire heap (Young + Old + Metaspace).
- Rare, very slow (can take seconds).
- Stops the world completely.
- Should be avoided in production.

**Remember**: Minor (Young, fast, frequent) → Major (Old, slow, occasional) → Full (Everything, very slow, rare)

---

## 34. How does G1 Garbage Collector work? What are regions and humongous objects?

**Simple Answer:**

**G1 GC Basics**:
- Divides heap into equal-sized regions (typically 1-32 MB).
- Each region can be Eden, Survivor, or Old.
- Collects regions with most garbage first (hence "Garbage First").
- Predictable pause times using target pause time goal.

**Regions**:
- Heap is divided into ~2000 regions.
- Flexible assignment - any region can become any generation.
- Allows incremental collection of heap.

**Humongous Objects**:
- Objects larger than 50% of region size.
- Stored in contiguous Old Generation regions.
- Collected only during Full GC.
- Can cause performance issues if too many.

**Remember**: G1 = "Regional cleaning, biggest mess first, predictable pauses"

---

## 35. What is ZGC and when should you use it?

**Simple Answer:**

**ZGC (Z Garbage Collector)**:
- Ultra-low latency GC with pause times < 10ms.
- Handles heaps from 8MB to 16TB.
- Concurrent - does most work while app runs.
- Uses colored pointers and load barriers.

**When to use ZGC**:
- Latency-sensitive applications (trading systems, real-time apps).
- Large heap sizes (> 100GB).
- Need consistent response times.
- Can't tolerate GC pauses > 10ms.

**When NOT to use**:
- Small heaps (< 4GB) - overhead not worth it.
- Throughput is more important than latency.
- CPU resources are limited (ZGC uses more CPU).

**Remember**: ZGC = "Zero pause (almost), Zettabyte scale, Zippy response"

---

## 36. How do you identify and fix memory leaks in production?

**Simple Answer:**

**Identify Memory Leaks**:
1. Monitor heap usage over time - if it keeps growing, suspect leak.
2. Watch for frequent Full GCs with no memory recovery.
3. Check OutOfMemoryError in logs.
4. Use monitoring tools (Prometheus, Grafana, New Relic).

**Fix Memory Leaks**:
1. **Take heap dump**: Use `jmap -dump:live,format=b,file=heap.bin <pid>`
2. **Analyze with MAT** (Memory Analyzer Tool): Find objects consuming most memory.
3. **Look for common causes**:
   - Unclosed resources (connections, streams, files)
   - Static collections growing unbounded
   - ThreadLocal not cleaned
   - Event listeners not removed
   - Cache without eviction policy
4. **Fix the code**: Close resources, clear collections, remove listeners.
5. **Verify**: Monitor after deployment.

**Remember**: "Monitor → Dump → Analyze → Fix → Verify"

---

## 37. What tools do you use for memory analysis (MAT, JProfiler, VisualVM, JFR)?

**Simple Answer:**

**MAT (Memory Analyzer Tool)**:
- Free, powerful heap dump analyzer.
- Shows memory leaks, dominator tree, retained heap.
- Best for post-mortem analysis.

**JProfiler**:
- Commercial, comprehensive profiler.
- Real-time monitoring, CPU and memory profiling.
- Great UI, easy to use.
- Good for development and testing.

**VisualVM**:
- Free, comes with JDK.
- Real-time monitoring, heap dumps, thread dumps.
- Good for quick analysis.
- Lightweight, easy to start.

**JFR (Java Flight Recorder)**:
- Built into JVM, very low overhead (< 1%).
- Records events continuously.
- Production-safe, can run always-on.
- Analyze with JMC (Java Mission Control).

**Remember**: MAT (heap dumps), JProfiler (paid, powerful), VisualVM (free, quick), JFR (production-safe)

---

## 38. Explain different types of `OutOfMemoryError` and their root causes.

**Simple Answer:**

**1. Java heap space**:
- Heap is full, can't create new objects.
- Cause: Memory leak, heap too small, or too many objects.
- Fix: Increase heap (-Xmx), fix memory leaks.

**2. GC Overhead Limit Exceeded**:
- JVM spending > 98% time in GC, recovering < 2% memory.
- Cause: Heap too small or severe memory leak.
- Fix: Increase heap, fix leaks, optimize code.

**3. Metaspace**:
- Class metadata space exhausted.
- Cause: Too many classes loaded, classloader leak.
- Fix: Increase Metaspace (-XX:MaxMetaspaceSize), fix classloader leaks.

**4. Unable to create new native thread**:
- Can't create more OS threads.
- Cause: Too many threads, OS limit reached.
- Fix: Reduce threads, increase OS limits, use thread pools.

**5. Direct buffer memory**:
- NIO direct buffers exhausted.
- Cause: Too many direct ByteBuffers not released.
- Fix: Increase -XX:MaxDirectMemorySize, release buffers properly.

**Remember**: Heap (objects), Metaspace (classes), Threads (too many), Direct (NIO buffers)

---

## 39. What are JVM tuning parameters (`-Xms`, `-Xmx`, `-XX:MaxMetaspaceSize`, `-XX:+UseG1GC`)?

**Simple Answer:**

**Basic Memory Parameters**:
- **-Xms**: Initial heap size (e.g., -Xms2g = start with 2GB)
- **-Xmx**: Maximum heap size (e.g., -Xmx4g = max 4GB)
- **Tip**: Set Xms = Xmx to avoid heap resizing overhead

**Metaspace**:
- **-XX:MaxMetaspaceSize**: Max class metadata space (e.g., -XX:MaxMetaspaceSize=512m)

**GC Selection**:
- **-XX:+UseG1GC**: Use G1 Garbage Collector (default Java 9+)
- **-XX:+UseZGC**: Use ZGC (low latency)
- **-XX:+UseParallelGC**: Use Parallel GC (throughput)

**G1 Tuning**:
- **-XX:MaxGCPauseMillis=200**: Target max pause time (default 200ms)
- **-XX:G1HeapRegionSize=16m**: Region size

**GC Logging**:
- **-Xlog:gc*:file=gc.log**: Enable GC logging (Java 9+)

**Remember**: Xms/Xmx (heap), MaxMetaspaceSize (classes), UseG1GC (collector choice)

---

## 40. Explain strong, soft, weak, and phantom references with use cases.

**Simple Answer:**

**Strong Reference** (Normal):
```java
Object obj = new Object(); // Strong reference
```
- Default reference type.
- Never garbage collected while reference exists.
- Use: Normal object usage.

**Soft Reference**:
```java
SoftReference<Object> soft = new SoftReference<>(obj);
```
- Collected only when memory is low.
- JVM tries to keep them as long as possible.
- Use: Memory-sensitive caches (image cache, parsed data).

**Weak Reference**:
```java
WeakReference<Object> weak = new WeakReference<>(obj);
```
- Collected in next GC cycle if no strong reference.
- Use: WeakHashMap, canonicalizing mappings, listeners.

**Phantom Reference**:
```java
PhantomReference<Object> phantom = new PhantomReference<>(obj, queue);
```
- Can't get object back, only know when it's collected.
- Collected after finalization.
- Use: Post-mortem cleanup, better than finalize().

**Remember**: Strong (never GC) → Soft (low memory) → Weak (next GC) → Phantom (after death)

---

## 41. What is the difference between stack overflow and heap overflow?

**Simple Answer:**

**Stack Overflow**:
- **Error**: StackOverflowError
- **Cause**: Stack memory exhausted, usually from infinite recursion or very deep recursion.
- **Location**: Thread stack (each thread has its own stack).
- **Example**: 
  ```java
  void recursive() { recursive(); } // Infinite recursion
  ```
- **Fix**: Fix recursion logic, increase stack size (-Xss), use iteration instead.

**Heap Overflow**:
- **Error**: OutOfMemoryError: Java heap space
- **Cause**: Heap memory exhausted, too many objects created.
- **Location**: Heap (shared by all threads).
- **Example**:
  ```java
  List<byte[]> list = new ArrayList<>();
  while(true) { list.add(new byte[1024*1024]); } // Keep adding
  ```
- **Fix**: Increase heap (-Xmx), fix memory leaks, optimize object creation.

**Remember**: Stack (recursion, per-thread, small) vs Heap (objects, shared, large)

---

## 42. How do you analyze GC logs for performance tuning?

**Simple Answer:**

**Enable GC Logging**:
```bash
# Java 9+
-Xlog:gc*:file=gc.log:time,uptime,level,tags

# Java 8
-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log
```

**Key Metrics to Analyze**:

1. **GC Frequency**: How often GC runs
   - Too frequent Minor GC → Young Gen too small
   - Frequent Full GC → Heap too small or memory leak

2. **Pause Times**: How long app stops
   - Target: Minor GC < 50ms, Major GC < 200ms
   - Long pauses → Need different GC or tuning

3. **Memory Recovery**: How much memory freed
   - Low recovery → Memory leak
   - Good recovery → Normal behavior

4. **Promotion Rate**: Objects moving to Old Gen
   - High promotion → Young Gen too small or objects live too long

5. **Allocation Rate**: How fast objects created
   - High rate → Optimize object creation

**Tools**:
- GCViewer, GCEasy.io (online), JClarity Censum

**Remember**: "Frequency, Pause, Recovery, Promotion, Allocation" - FPRPA

---

## Summary Cheat Sheet

| Question | Key Concept | Remember As |
|----------|-------------|-------------|
| 30 | JVM Memory | HSMCN: Heap, Stack, Metaspace, Code, Native |
| 31 | Generations | Eden → S0 ↔ S1 → Old (like school grades) |
| 32 | GC Algorithms | Serial → Parallel → CMS → G1 → ZGC (evolution) |
| 33 | GC Types | Minor (fast) → Major (slow) → Full (very slow) |
| 34 | G1 GC | Regional cleaning, biggest mess first |
| 35 | ZGC | Ultra-low pause, huge heaps, latency-critical |
| 36 | Memory Leaks | Monitor → Dump → Analyze → Fix → Verify |
| 37 | Tools | MAT (dumps), JProfiler (paid), VisualVM (free), JFR (prod) |
| 38 | OOM Types | Heap, Metaspace, Threads, Direct buffers |
| 39 | JVM Params | Xms/Xmx (heap), MaxMetaspaceSize, UseG1GC |
| 40 | References | Strong → Soft → Weak → Phantom (GC eagerness) |
| 41 | Overflow Types | Stack (recursion) vs Heap (objects) |
| 42 | GC Analysis | FPRPA: Frequency, Pause, Recovery, Promotion, Allocation |

# ✅ 4) Multithreading & Concurrency - 18 Questions

## 43. Explain thread lifecycle states (New, Runnable, Blocked, Waiting, Timed Waiting, Terminated).

**Simple Answer:**

A thread goes through 6 states in its lifetime:

**1. NEW**: Thread created but not started yet.
```java
Thread t = new Thread(); // NEW state
```

**2. RUNNABLE**: Thread is ready to run or currently running. Waiting for CPU time.
```java
t.start(); // Now RUNNABLE
```

**3. BLOCKED**: Thread waiting to acquire a lock/monitor.
```java
synchronized(obj) { } // Waiting for lock = BLOCKED
```

**4. WAITING**: Thread waiting indefinitely for another thread's action.
```java
obj.wait(); // WAITING
thread.join(); // WAITING
```

**5. TIMED_WAITING**: Thread waiting for a specific time period.
```java
Thread.sleep(1000); // TIMED_WAITING
obj.wait(1000); // TIMED_WAITING
```

**6. TERMINATED**: Thread completed execution or stopped.

**Remember**: "NEW → RUNNABLE → (BLOCKED/WAITING/TIMED_WAITING) → TERMINATED" - Like a person's day: Wake up → Ready → (Stuck/Waiting) → Sleep

---

## 44. Difference between `start()` and `run()` methods.

**Simple Answer:**

**start() method**:
- Creates a new thread and calls run() in that new thread.
- Can only be called once per thread object.
- Throws IllegalThreadStateException if called twice.
- Actual multithreading happens.

```java
Thread t = new Thread(() -> System.out.println("New thread"));
t.start(); // Creates new thread, runs in parallel
```

**run() method**:
- Just a normal method call in the same thread.
- No new thread is created.
- Can be called multiple times.
- No multithreading - runs sequentially.

```java
Thread t = new Thread(() -> System.out.println("Same thread"));
t.run(); // Just method call, no new thread
```

**Remember**: start() = "Start a new thread" | run() = "Run in current thread (no multithreading)"

---

## 45. What is `synchronized` keyword? Method vs block synchronization.

**Simple Answer:**

**synchronized** ensures only one thread can execute a code section at a time. Prevents race conditions.

**Method Synchronization**:
```java
// Locks entire object
public synchronized void method() {
    // Only one thread can execute this at a time
}

// For static methods, locks the Class object
public static synchronized void staticMethod() {
    // Only one thread across all instances
}
```

**Block Synchronization** (Better - more granular):
```java
public void method() {
    // Non-critical code here (multiple threads can execute)
    
    synchronized(this) { // Lock specific object
        // Critical section - only one thread
    }
    
    // More non-critical code
}
```

**When to use which**:
- Method sync: When entire method needs protection (simpler).
- Block sync: When only part of method needs protection (better performance).

**Remember**: Method = "Lock entire room" | Block = "Lock only the safe"

---

## 46. Explain `volatile` keyword and happens-before relationship in JMM.

**Simple Answer:**

**volatile** ensures visibility of variable changes across threads.

**Without volatile**:
```java
private boolean flag = false; // Thread may cache this

// Thread 1
flag = true; // May not be visible to Thread 2 immediately

// Thread 2
while(!flag) { } // May loop forever due to caching
```

**With volatile**:
```java
private volatile boolean flag = false; // Always read from main memory

// Thread 1
flag = true; // Immediately visible to all threads

// Thread 2
while(!flag) { } // Will see the change
```

**Happens-Before Relationship**:
- Guarantees that writes before volatile write are visible after volatile read.
- Write to volatile → happens-before → Read from volatile.

**When to use**:
- Simple flags/status variables.
- Double-checked locking.
- NOT for compound operations (use AtomicInteger instead).

**Remember**: volatile = "Always fresh from main memory, no caching"

---

## 47. Difference between `wait()`, `notify()`, `notifyAll()` and `sleep()`.

**Simple Answer:**

**wait()**:
- Releases the lock and waits.
- Must be called inside synchronized block.
- Thread goes to WAITING state.
- Wakes up when notify() is called.

```java
synchronized(obj) {
    obj.wait(); // Release lock, wait for notification
}
```

**notify()**:
- Wakes up ONE waiting thread (random).
- Must be called inside synchronized block.
- Doesn't release lock immediately.

```java
synchronized(obj) {
    obj.notify(); // Wake up one thread
}
```

**notifyAll()**:
- Wakes up ALL waiting threads.
- All threads compete for lock.
- Safer than notify().

```java
synchronized(obj) {
    obj.notifyAll(); // Wake up all threads
}
```

**sleep()**:
- Does NOT release lock.
- Thread goes to TIMED_WAITING.
- No synchronized block needed.
- Wakes up after time expires.

```java
Thread.sleep(1000); // Sleep 1 second, keep lock
```

**Remember**: wait/notify = "Release lock, coordinate" | sleep = "Keep lock, just pause"

---

## 48. What is deadlock? How do you detect and prevent it?

**Simple Answer:**

**Deadlock**: Two or more threads waiting for each other forever.

**Classic Example**:
```java
// Thread 1
synchronized(lock1) {
    synchronized(lock2) { } // Waiting for lock2
}

// Thread 2
synchronized(lock2) {
    synchronized(lock1) { } // Waiting for lock1
}
// Both threads stuck forever!
```

**Detect Deadlock**:
1. Use jstack: `jstack <pid>` - shows "Found 1 deadlock"
2. Use VisualVM, JConsole - Threads tab shows deadlocks
3. ThreadMXBean in code:
```java
ThreadMXBean bean = ManagementFactory.getThreadMXBean();
long[] deadlocks = bean.findDeadlockedThreads();
```

**Prevent Deadlock**:
1. **Lock Ordering**: Always acquire locks in same order
```java
// Both threads acquire lock1 first, then lock2
synchronized(lock1) {
    synchronized(lock2) { }
}
```

2. **Lock Timeout**: Use tryLock() with timeout
```java
if(lock.tryLock(1, TimeUnit.SECONDS)) {
    try { } finally { lock.unlock(); }
}
```

3. **Avoid Nested Locks**: Use single lock when possible
4. **Use concurrent utilities**: ReentrantLock, Semaphore

**Remember**: "Same order, timeout, avoid nesting" - SOT

---

## 49. Explain livelock and starvation with real-world examples.

**Simple Answer:**

**Livelock**: Threads keep changing state in response to each other but make no progress.

**Real-world example**: Two people in a hallway, both step aside in the same direction repeatedly, blocking each other.

**Code example**:
```java
// Both threads keep yielding to each other
while(otherThreadBusy) {
    Thread.yield(); // Keep giving up, no progress
}
```

**Solution**: Add randomness or priority to break the cycle.

---

**Starvation**: Thread never gets CPU time because other threads keep taking it.

**Real-world example**: In a restaurant, VIP customers keep coming, regular customer never gets served.

**Code example**:
```java
// High priority threads keep running
Thread highPriority = new Thread();
highPriority.setPriority(Thread.MAX_PRIORITY);
// Low priority thread starves
```

**Solution**: 
- Fair locks: `new ReentrantLock(true)`
- Proper thread pool sizing
- Avoid priority manipulation

**Remember**: Livelock = "Active but stuck" | Starvation = "Never gets a turn"

---

## 50. What is `ExecutorService`? Explain different thread pool types.

**Simple Answer:**

**ExecutorService**: Framework to manage thread pools. Better than creating threads manually.

**Thread Pool Types**:

**1. FixedThreadPool**: Fixed number of threads
```java
ExecutorService executor = Executors.newFixedThreadPool(5);
// Always 5 threads, queue tasks if all busy
// Use: Known workload, controlled resource usage
```

**2. CachedThreadPool**: Creates threads as needed, reuses idle threads
```java
ExecutorService executor = Executors.newCachedThreadPool();
// Grows/shrinks dynamically, threads die after 60s idle
// Use: Many short-lived tasks
```

**3. SingleThreadExecutor**: Only one thread
```java
ExecutorService executor = Executors.newSingleThreadExecutor();
// Sequential execution, tasks queued
// Use: Order matters, background processing
```

**4. ScheduledThreadPool**: Schedule tasks with delay/periodic execution
```java
ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
executor.schedule(task, 5, TimeUnit.SECONDS); // Run after 5s
executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS); // Every 1s
// Use: Periodic tasks, delayed execution
```

**Best Practice**: Use ThreadPoolExecutor with custom parameters
```java
new ThreadPoolExecutor(
    5,      // core pool size
    10,     // max pool size
    60L,    // keep alive time
    TimeUnit.SECONDS,
    new LinkedBlockingQueue<>(100) // queue
);
```

**Remember**: Fixed (stable), Cached (flexible), Single (sequential), Scheduled (timed)

---

## 51. What is `CountDownLatch`, `CyclicBarrier`, `Semaphore`, `Phaser`?

**Simple Answer:**

**CountDownLatch**: Wait for N operations to complete (one-time use)
```java
CountDownLatch latch = new CountDownLatch(3); // Wait for 3 tasks

// Worker threads
latch.countDown(); // Decrease count

// Main thread
latch.await(); // Wait until count reaches 0
// Use: Wait for multiple services to start
```

**CyclicBarrier**: All threads wait for each other (reusable)
```java
CyclicBarrier barrier = new CyclicBarrier(3); // 3 threads must wait

// Each thread
barrier.await(); // Wait for all 3 threads
// All proceed together after all arrive
// Use: Parallel computation phases
```

**Semaphore**: Control access to N resources
```java
Semaphore semaphore = new Semaphore(3); // 3 permits

semaphore.acquire(); // Get permit (blocks if none available)
try {
    // Access resource
} finally {
    semaphore.release(); // Return permit
}
// Use: Connection pool, rate limiting
```

**Phaser**: Advanced barrier with dynamic parties
```java
Phaser phaser = new Phaser(3); // 3 parties

phaser.arriveAndAwaitAdvance(); // Wait for all
// Can add/remove parties dynamically
// Use: Complex multi-phase algorithms
```

**Remember**: Latch (countdown), Barrier (meet up), Semaphore (permits), Phaser (phases)

---

## 52. `ReentrantLock` vs `synchronized` - when to use which?

**Simple Answer:**

**synchronized** (Simple, built-in):
```java
synchronized(obj) {
    // Critical section
}
```
**Pros**: Simple, automatic lock release, JVM optimized
**Cons**: No timeout, no try-lock, no fairness control

---

**ReentrantLock** (Advanced, flexible):
```java
ReentrantLock lock = new ReentrantLock(true); // fair lock
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock(); // Must manually unlock
}
```

**Advanced Features**:
```java
// Try lock with timeout
if(lock.tryLock(1, TimeUnit.SECONDS)) {
    try { } finally { lock.unlock(); }
}

// Check if locked
if(lock.isLocked()) { }

// Fair lock (FIFO order)
ReentrantLock fairLock = new ReentrantLock(true);
```

**When to use synchronized**:
- Simple locking needs
- Short critical sections
- Don't need advanced features

**When to use ReentrantLock**:
- Need timeout (avoid deadlock)
- Need try-lock (non-blocking attempt)
- Need fairness (prevent starvation)
- Need lock interruptibility
- Complex locking logic

**Remember**: synchronized = "Simple & safe" | ReentrantLock = "Powerful & flexible"

---

## 53. Explain `ReadWriteLock` and `StampedLock` with use cases.

**Simple Answer:**

**ReadWriteLock**: Separate locks for reading and writing
```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();

// Multiple readers can read simultaneously
rwLock.readLock().lock();
try {
    // Read data (many threads can do this together)
} finally {
    rwLock.readLock().unlock();
}

// Only one writer, blocks all readers
rwLock.writeLock().lock();
try {
    // Write data (exclusive access)
} finally {
    rwLock.writeLock().unlock();
}
```

**Use case**: Cache, configuration, read-heavy data structures
**Benefit**: Better performance when reads >> writes

---

**StampedLock**: Optimistic reading (Java 8+, faster)
```java
StampedLock lock = new StampedLock();

// Optimistic read (no locking!)
long stamp = lock.tryOptimisticRead();
// Read data
if(!lock.validate(stamp)) { // Check if data changed
    // Data changed, upgrade to read lock
    stamp = lock.readLock();
    try {
        // Re-read data
    } finally {
        lock.unlockRead(stamp);
    }
}

// Write lock
long stamp = lock.writeLock();
try {
    // Write data
} finally {
    lock.unlockWrite(stamp);
}
```

**Use case**: High-performance scenarios, mostly reads
**Benefit**: Faster than ReadWriteLock for read-heavy workloads

**Remember**: ReadWriteLock = "Readers share, writers exclusive" | StampedLock = "Optimistic reading"

---

## 54. What is `AtomicInteger`? How does CAS (Compare-And-Swap) work?

**Simple Answer:**

**AtomicInteger**: Thread-safe integer without locks (lock-free).

**Without Atomic** (Not thread-safe):
```java
private int count = 0;
count++; // Not atomic! Read-Modify-Write = 3 operations
// Race condition possible
```

**With AtomicInteger** (Thread-safe):
```java
private AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet(); // Atomic operation
count.addAndGet(5); // Add 5 atomically
count.compareAndSet(10, 20); // CAS operation
```

**CAS (Compare-And-Swap)**: Hardware-level atomic operation
```java
// Pseudocode of CAS
boolean compareAndSet(int expected, int newValue) {
    if(currentValue == expected) {
        currentValue = newValue;
        return true; // Success
    }
    return false; // Someone else changed it
}
```

**How it works**:
1. Read current value
2. Calculate new value
3. CAS: If current value unchanged, update it
4. If CAS fails (someone else changed it), retry

**Example**:
```java
AtomicInteger count = new AtomicInteger(10);

// Thread 1 & 2 both try to increment
count.compareAndSet(10, 11); // One succeeds
count.compareAndSet(10, 11); // Other fails, retries with 11
```

**Benefits**:
- No locks = better performance
- No deadlock possible
- Lock-free algorithms

**Use cases**: Counters, IDs, flags, statistics

**Remember**: CAS = "Check before you change, retry if someone beat you"

---

## 55. Explain `CompletableFuture` for async programming with chaining examples.

**Simple Answer:**

**CompletableFuture**: Async programming with callbacks and chaining (Java 8+).

**Basic Usage**:
```java
// Run async task
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return "Hello"; // Runs in separate thread
});

// Get result (blocking)
String result = future.get();
```

**Chaining Operations**:
```java
CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")           // Transform
    .thenApply(String::toUpperCase)         // Transform again
    .thenAccept(System.out::println)        // Consume
    .thenRun(() -> System.out.println("Done")); // Run action
```

**Combining Futures**:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

// Combine both
future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2)
       .thenAccept(System.out::println); // "Hello World"
```

**Error Handling**:
```java
CompletableFuture.supplyAsync(() -> {
    if(true) throw new RuntimeException("Error");
    return "Success";
})
.exceptionally(ex -> "Default Value")  // Handle error
.thenAccept(System.out::println);      // Prints "Default Value"
```

**Multiple Futures**:
```java
// Wait for all
CompletableFuture.allOf(future1, future2, future3).join();

// Wait for any
CompletableFuture.anyOf(future1, future2, future3).join();
```

**Remember**: "Supply → Apply → Accept → Run" - Async pipeline

---

## 56. What is `ThreadLocal` and its use cases? What are memory leak risks?

**Simple Answer:**

**ThreadLocal**: Each thread gets its own copy of a variable.

**Basic Usage**:
```java
private static ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> 0);

// Thread 1
threadId.set(1); // Only visible to Thread 1

// Thread 2
threadId.set(2); // Only visible to Thread 2

// Each thread sees its own value
int id = threadId.get();
```

**Real Use Cases**:

1. **User Context** (Spring Security):
```java
ThreadLocal<User> userContext = new ThreadLocal<>();
// Store logged-in user per request thread
```

2. **Database Connection** (per thread):
```java
ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
```

3. **SimpleDateFormat** (not thread-safe):
```java
ThreadLocal<SimpleDateFormat> dateFormat = 
    ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
```

**Memory Leak Risk**:
```java
// In thread pool, threads are reused
ThreadLocal<byte[]> data = new ThreadLocal<>();
data.set(new byte[1024*1024]); // 1MB

// Thread finishes but goes back to pool
// ThreadLocal data still in memory!
// Multiply by thread pool size = BIG LEAK
```

**Prevent Memory Leaks**:
```java
try {
    threadLocal.set(value);
    // Use value
} finally {
    threadLocal.remove(); // ALWAYS remove!
}
```

**Remember**: ThreadLocal = "Private variable per thread" | Always remove() in finally!

---

## 57. How do you handle exceptions in multithreaded code?

**Simple Answer:**

**Problem**: Exceptions in threads don't propagate to caller.

**Wrong Way**:
```java
new Thread(() -> {
    throw new RuntimeException("Error"); // Lost! No one catches it
}).start();
```

**Solution 1: Try-Catch in Thread**:
```java
new Thread(() -> {
    try {
        // Risky code
    } catch(Exception e) {
        logger.error("Error in thread", e);
    }
}).start();
```

**Solution 2: UncaughtExceptionHandler**:
```java
Thread thread = new Thread(() -> {
    throw new RuntimeException("Error");
});

thread.setUncaughtExceptionHandler((t, e) -> {
    logger.error("Uncaught exception in " + t.getName(), e);
});

thread.start();
```

**Solution 3: ExecutorService with Future**:
```java
ExecutorService executor = Executors.newFixedThreadPool(5);

Future<String> future = executor.submit(() -> {
    throw new RuntimeException("Error");
    return "Success";
});

try {
    future.get(); // Exception thrown here!
} catch(ExecutionException e) {
    Throwable cause = e.getCause(); // Original exception
    logger.error("Task failed", cause);
}
```

**Solution 4: CompletableFuture**:
```java
CompletableFuture.supplyAsync(() -> {
    throw new RuntimeException("Error");
})
.exceptionally(ex -> {
    logger.error("Async error", ex);
    return "Default";
})
.thenAccept(System.out::println);
```

**Best Practices**:
- Always handle exceptions in thread code
- Use ExecutorService over raw threads
- Log exceptions properly
- Set UncaughtExceptionHandler as safety net

**Remember**: "Catch in thread, or use Future.get(), or exceptionally()"

---

## 58. Explain Fork/Join framework and work-stealing algorithm.

**Simple Answer:**

**Fork/Join**: Framework for parallel processing by dividing tasks (Java 7+).

**Concept**: 
- Fork: Split big task into smaller subtasks
- Join: Combine results

**Basic Example**:
```java
class SumTask extends RecursiveTask<Long> {
    private long[] array;
    private int start, end;
    private static final int THRESHOLD = 1000;
    
    protected Long compute() {
        if(end - start <= THRESHOLD) {
            // Small enough, compute directly
            long sum = 0;
            for(int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Split into two subtasks
            int mid = (start + end) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);
            
            left.fork();  // Fork left task
            long rightResult = right.compute(); // Compute right
            long leftResult = left.join(); // Join left result
            
            return leftResult + rightResult;
        }
    }
}

// Usage
ForkJoinPool pool = new ForkJoinPool();
long result = pool.invoke(new SumTask(array, 0, array.length));
```

**Work-Stealing Algorithm**:
- Each thread has its own deque (double-ended queue) of tasks
- Thread takes tasks from its own queue (from head)
- When queue is empty, "steals" tasks from other threads' queues (from tail)
- Keeps all threads busy, balances load automatically

**Visual**:
```
Thread 1 Queue: [T1, T2, T3] → Takes T1
Thread 2 Queue: [T4, T5]     → Takes T4
Thread 3 Queue: []           → Steals T3 from Thread 1
```

**Use Cases**:
- Large array processing
- Recursive algorithms
- Parallel sorting
- Image processing

**Remember**: Fork (divide) → Compute (conquer) → Join (combine) + Work-stealing (balance)

---

## 59. What is the difference between `Callable` and `Runnable`?

**Simple Answer:**

**Runnable**: No return value, no checked exceptions
```java
Runnable task = () -> {
    System.out.println("Running");
    // Can't return anything
    // Can't throw checked exceptions
};

new Thread(task).start();
```

**Callable**: Returns value, can throw checked exceptions
```java
Callable<String> task = () -> {
    if(someCondition) {
        throw new Exception("Error"); // Checked exception OK
    }
    return "Result"; // Returns value
};

ExecutorService executor = Executors.newFixedThreadPool(5);
Future<String> future = executor.submit(task);
String result = future.get(); // Get returned value
```

**Comparison Table**:

| Feature | Runnable | Callable |
|---------|----------|----------|
| Return value | No (void) | Yes (generic type) |
| Checked exceptions | No | Yes |
| Method | run() | call() |
| Usage | Thread, ExecutorService | ExecutorService only |
| Get result | Not possible | future.get() |

**When to use**:
- **Runnable**: Fire and forget, no result needed
- **Callable**: Need result or exception handling

**Remember**: Runnable = "Just run" | Callable = "Call and get result"

---

## 60. What are best practices for writing thread-safe code?

**Simple Answer:**

**1. Immutability** (Best approach):
```java
public final class ImmutableUser {
    private final String name;
    private final int age;
    
    public ImmutableUser(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // Only getters, no setters
}
// Immutable = Thread-safe automatically
```

**2. Minimize Shared State**:
```java
// Bad: Shared mutable state
private int counter = 0;

// Good: ThreadLocal or method-local variables
ThreadLocal<Integer> counter = new ThreadLocal<>();
```

**3. Use Concurrent Collections**:
```java
// Bad
Map<String, String> map = new HashMap<>(); // Not thread-safe

// Good
Map<String, String> map = new ConcurrentHashMap<>();
List<String> list = new CopyOnWriteArrayList<>();
```

**4. Proper Synchronization**:
```java
// Synchronize smallest possible scope
public void method() {
    // Non-critical code
    
    synchronized(this) {
        // Only critical section
    }
    
    // More non-critical code
}
```

**5. Use Atomic Classes**:
```java
// Bad
private int count = 0;
public synchronized void increment() { count++; }

// Good
private AtomicInteger count = new AtomicInteger(0);
public void increment() { count.incrementAndGet(); }
```

**6. Avoid Nested Locks** (Prevent deadlock):
```java
// Bad
synchronized(lock1) {
    synchronized(lock2) { } // Deadlock risk
}

// Good: Single lock or lock ordering
```

**7. Use Higher-Level Utilities**:
```java
// Use ExecutorService instead of raw threads
// Use CountDownLatch instead of wait/notify
// Use CompletableFuture for async
```

**8. Document Thread Safety**:
```java
/**
 * Thread-safe. Uses ConcurrentHashMap internally.
 */
public class Cache { }
```

**9. Prefer Final Fields**:
```java
private final Object lock = new Object(); // Can't be reassigned
```

**10. Test with Concurrency**:
```java
// Use tools: ThreadSanitizer, FindBugs, JCStress
// Load testing with multiple threads
```

**Remember**: "Immutable, Minimize, Concurrent, Atomic, Document" - IMCAD

---

## Summary Cheat Sheet

| Question | Key Concept | Remember As |
|----------|-------------|-------------|
| 43 | Thread States | NEW → RUNNABLE → (BLOCKED/WAITING) → TERMINATED |
| 44 | start() vs run() | start = new thread, run = same thread |
| 45 | synchronized | Method = lock room, Block = lock safe |
| 46 | volatile | Always fresh from main memory |
| 47 | wait/notify/sleep | wait = release lock, sleep = keep lock |
| 48 | Deadlock | Same order, timeout, avoid nesting (SOT) |
| 49 | Livelock/Starvation | Active but stuck / Never gets turn |
| 50 | ExecutorService | Fixed, Cached, Single, Scheduled |
| 51 | Sync Utilities | Latch, Barrier, Semaphore, Phaser |
| 52 | Lock Types | synchronized = simple, ReentrantLock = flexible |
| 53 | Read/Write Locks | Readers share, writers exclusive |
| 54 | AtomicInteger/CAS | Check before change, retry if beaten |
| 55 | CompletableFuture | Supply → Apply → Accept → Run |
| 56 | ThreadLocal | Private per thread, always remove() |
| 57 | Exception Handling | Catch in thread or use Future.get() |
| 58 | Fork/Join | Divide → Compute → Combine + Work-stealing |
| 59 | Callable vs Runnable | Callable returns value, Runnable doesn't |
| 60 | Thread-Safe Code | IMCAD: Immutable, Minimize, Concurrent, Atomic, Document |

# ✅ 5) Exception Handling & JVM Internals - 11 Questions

## 61. Checked vs unchecked exceptions - when to use which?

**Simple Answer:**

**Checked Exceptions** (Must handle or declare):
```java
// Compiler forces you to handle
public void readFile() throws IOException {
    FileReader file = new FileReader("file.txt"); // Checked exception
}

// Must catch or declare
try {
    readFile();
} catch(IOException e) {
    // Handle it
}
```

**Examples**: IOException, SQLException, ClassNotFoundException
**When to use**: Recoverable conditions you expect caller to handle

---

**Unchecked Exceptions** (Runtime exceptions):
```java
// No need to declare or catch
public void divide(int a, int b) {
    return a / b; // Can throw ArithmeticException
}

int[] arr = new int[5];
arr[10] = 5; // ArrayIndexOutOfBoundsException
```

**Examples**: NullPointerException, IllegalArgumentException, ArrayIndexOutOfBoundsException
**When to use**: Programming errors, shouldn't happen if code is correct

---

**Decision Guide**:

**Use Checked Exception when**:
- External resource failure (file, network, database)
- Caller can reasonably recover
- Business logic validation

**Use Unchecked Exception when**:
- Programming bug (null pointer, illegal argument)
- Caller can't do anything meaningful
- Validation failures

**Modern Trend**: Prefer unchecked exceptions (Spring, Hibernate use them)

**Remember**: Checked = "Expected problems, must handle" | Unchecked = "Programming bugs, optional handling"

---

## 62. Explain try-catch-finally execution flow with return statements.

**Simple Answer:**

**Basic Flow**:
```java
try {
    // 1. Execute this first
    return "try";
} catch(Exception e) {
    // 2. Execute if exception occurs
    return "catch";
} finally {
    // 3. ALWAYS executes (even with return)
    System.out.println("Finally");
}
```

**Tricky Case 1: Return in finally overrides**:
```java
public String test() {
    try {
        return "try"; // This value is discarded!
    } finally {
        return "finally"; // This wins!
    }
}
// Returns "finally" - BAD PRACTICE!
```

**Tricky Case 2: Finally modifies value**:
```java
public int test() {
    int x = 1;
    try {
        return x; // Returns 1 (value copied before finally)
    } finally {
        x = 2; // Doesn't affect return value
    }
}
// Returns 1, not 2
```

**Tricky Case 3: Exception in finally**:
```java
try {
    throw new Exception("try");
} finally {
    throw new Exception("finally"); // This exception is thrown
}
// Original exception is lost!
```

**Execution Order**:
1. Try block executes
2. If exception → catch block
3. Finally ALWAYS executes (even with return/exception)
4. Return value from try/catch is returned (unless finally has return)

**Best Practices**:
- Never return from finally block
- Never throw exception from finally block
- Use finally only for cleanup (close resources)

**Remember**: "Try → Catch → Finally (always) → Return" | Never return in finally!

---

## 63. What is try-with-resources? How does it work with AutoCloseable?

**Simple Answer:**

**Old Way** (Manual cleanup):
```java
FileReader file = null;
try {
    file = new FileReader("file.txt");
    // Use file
} catch(IOException e) {
    // Handle
} finally {
    if(file != null) {
        try {
            file.close(); // Must close manually
        } catch(IOException e) {
            // Handle close exception
        }
    }
}
// Verbose and error-prone!
```

**New Way** (Try-with-resources - Java 7+):
```java
try(FileReader file = new FileReader("file.txt")) {
    // Use file
} catch(IOException e) {
    // Handle
}
// Automatically closes file, even if exception occurs!
```

**Multiple Resources**:
```java
try(FileReader file = new FileReader("in.txt");
    FileWriter writer = new FileWriter("out.txt")) {
    // Use both
}
// Both closed automatically in reverse order
```

**How it works**:
- Resource must implement AutoCloseable interface
- close() method called automatically
- Closes in reverse order of declaration
- Handles exceptions properly (suppressed exceptions)

**Custom AutoCloseable**:
```java
class MyResource implements AutoCloseable {
    public void doWork() {
        System.out.println("Working");
    }
    
    @Override
    public void close() {
        System.out.println("Closing");
    }
}

try(MyResource resource = new MyResource()) {
    resource.doWork();
}
// close() called automatically
```

**Benefits**:
- No need for finally block
- Cleaner code
- No resource leaks
- Proper exception handling

**Remember**: "Try-with-resources = Auto-close, no finally needed"

---

## 64. How do you create custom exceptions? Best practices.

**Simple Answer:**

**Basic Custom Exception**:
```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

// Usage
throw new UserNotFoundException("User with id 123 not found");
```

**With Cause** (Exception chaining):
```java
public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Usage
try {
    // Database operation
} catch(SQLException e) {
    throw new DataProcessingException("Failed to process data", e);
}
```

**Complete Custom Exception**:
```java
public class InsufficientBalanceException extends RuntimeException {
    private final String accountId;
    private final double balance;
    private final double amount;
    
    public InsufficientBalanceException(String accountId, double balance, double amount) {
        super(String.format("Account %s has balance %.2f, cannot withdraw %.2f", 
                           accountId, balance, amount));
        this.accountId = accountId;
        this.balance = balance;
        this.amount = amount;
    }
    
    // Getters for additional context
    public String getAccountId() { return accountId; }
    public double getBalance() { return balance; }
    public double getAmount() { return amount; }
}
```

**Best Practices**:

1. **Extend RuntimeException** (unchecked) for most cases
2. **Meaningful names**: End with "Exception"
3. **Provide constructors**:
   - Message only
   - Message + cause
   - No-arg (optional)
4. **Add context**: Include relevant data as fields
5. **Clear messages**: Explain what went wrong and why
6. **Don't overuse**: Only when standard exceptions don't fit
7. **Document**: Use Javadoc to explain when thrown

**When to create custom exception**:
- Domain-specific errors (UserNotFoundException)
- Need additional context (account balance)
- Specific handling required

**When NOT to create**:
- Standard exception fits (IllegalArgumentException)
- No additional value over standard exceptions

**Remember**: "Extend RuntimeException, meaningful name, add context, clear message"

---

## 65. What is exception chaining and suppressed exceptions?

**Simple Answer:**

**Exception Chaining**: Wrap one exception in another to preserve cause.

```java
try {
    // Low-level operation
    connection.execute(sql);
} catch(SQLException e) {
    // Wrap in higher-level exception
    throw new DataAccessException("Failed to fetch user", e);
    //                                                      ↑
    //                                                   Original cause
}

// Later, can get root cause
catch(DataAccessException e) {
    Throwable cause = e.getCause(); // Gets SQLException
    cause.printStackTrace(); // See original error
}
```

**Why chain**:
- Preserve original error information
- Provide context at each layer
- Easier debugging

---

**Suppressed Exceptions**: Exceptions that occur during cleanup (Java 7+).

**Problem without try-with-resources**:
```java
try {
    throw new Exception("Main exception");
} finally {
    throw new Exception("Finally exception"); // This hides main exception!
}
// Main exception is lost!
```

**Solution with try-with-resources**:
```java
class MyResource implements AutoCloseable {
    public void doWork() throws Exception {
        throw new Exception("Work exception");
    }
    
    @Override
    public void close() throws Exception {
        throw new Exception("Close exception");
    }
}

try(MyResource r = new MyResource()) {
    r.doWork(); // Throws "Work exception"
}
// close() throws "Close exception" but it's suppressed
```

**Accessing suppressed exceptions**:
```java
try {
    // Code with try-with-resources
} catch(Exception e) {
    System.out.println("Main: " + e.getMessage());
    
    Throwable[] suppressed = e.getSuppressed();
    for(Throwable t : suppressed) {
        System.out.println("Suppressed: " + t.getMessage());
    }
}
```

**Manual suppression** (rare):
```java
Exception main = new Exception("Main");
Exception suppressed = new Exception("Suppressed");
main.addSuppressed(suppressed);
throw main;
```

**Remember**: Chaining = "Wrap exceptions, preserve cause" | Suppressed = "Cleanup exceptions attached to main"

---

## 66. Explain ClassLoader hierarchy (Bootstrap, Platform/Extension, Application).

**Simple Answer:**

**ClassLoader Hierarchy** (Parent-child relationship):

```
Bootstrap ClassLoader (null)
    ↓ (parent)
Platform/Extension ClassLoader
    ↓ (parent)
Application ClassLoader
    ↓ (parent)
Custom ClassLoader (optional)
```

**1. Bootstrap ClassLoader**:
- Written in native code (C/C++)
- Loads core Java classes (java.lang.*, java.util.*)
- Located in: `$JAVA_HOME/jre/lib/rt.jar`
- Parent: null (top of hierarchy)
- Cannot be accessed directly from Java

```java
String.class.getClassLoader(); // Returns null (Bootstrap)
```

**2. Platform/Extension ClassLoader** (Java 9+):
- Loads extension classes
- Located in: `$JAVA_HOME/jre/lib/ext/` or `-Djava.ext.dirs`
- Parent: Bootstrap ClassLoader
- Java 8: Called "Extension ClassLoader"

```java
// Extension classes
javax.crypto.Cipher.class.getClassLoader(); // Platform/Extension
```

**3. Application ClassLoader** (System ClassLoader):
- Loads application classes from classpath
- Loads classes from `-cp` or `CLASSPATH`
- Parent: Platform/Extension ClassLoader
- Most of your application classes loaded here

```java
MyClass.class.getClassLoader(); // Application ClassLoader
```

**4. Custom ClassLoader** (Optional):
- For special loading needs (plugins, hot reload)
- Parent: Application ClassLoader (usually)

**Check ClassLoader**:
```java
ClassLoader cl = MyClass.class.getClassLoader();
System.out.println(cl); // Application ClassLoader
System.out.println(cl.getParent()); // Platform ClassLoader
System.out.println(cl.getParent().getParent()); // null (Bootstrap)
```

**Remember**: "Bootstrap (core) → Platform (extensions) → Application (your code)"

---

## 67. What is parent delegation model in ClassLoaders?

**Simple Answer:**

**Parent Delegation Model**: Before loading a class, ask parent first.

**How it works**:
1. Application ClassLoader receives request to load class
2. Delegates to parent (Platform ClassLoader)
3. Platform delegates to parent (Bootstrap ClassLoader)
4. Bootstrap tries to load (if core Java class)
5. If Bootstrap can't find, Platform tries
6. If Platform can't find, Application tries
7. If no one can load, throws ClassNotFoundException

**Visual Flow**:
```
Request to load "com.example.MyClass"
    ↓
Application CL: "Let me ask parent first"
    ↓
Platform CL: "Let me ask parent first"
    ↓
Bootstrap CL: "Not a core class, I can't load"
    ↓
Platform CL: "Not an extension, I can't load"
    ↓
Application CL: "Found it in classpath! Loading..."
```

**Why this model**:

1. **Security**: Prevents malicious code from replacing core classes
```java
// Can't create fake java.lang.String
// Bootstrap loads it first, your version ignored
```

2. **Avoid duplicates**: Class loaded once by parent, shared by all children

3. **Consistency**: Same class definition across application

**Example**:
```java
// Try to load String
Class.forName("java.lang.String");
// Bootstrap loads it (not your custom String)

// Try to load your class
Class.forName("com.example.MyClass");
// Goes through delegation, Application CL loads it
```

**Breaking delegation** (Custom ClassLoader):
```java
public class CustomClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // Load yourself first (break delegation)
        if(name.startsWith("com.example.plugin")) {
            // Load without asking parent
            return findClass(name);
        }
        // For others, use delegation
        return super.loadClass(name);
    }
}
```

**Remember**: "Ask parent first, load yourself last" - Delegation chain

---

## 68. What is JIT compilation? Explain C1 and C2 compilers.

**Simple Answer:**

**JIT (Just-In-Time) Compilation**: Converts bytecode to native machine code at runtime for better performance.

**How Java executes code**:
```
Java Source (.java)
    ↓ javac
Bytecode (.class)
    ↓ JVM
Interpreter (slow) → JIT Compiler → Native Code (fast)
```

**Why JIT**:
- Bytecode is platform-independent but slow
- Native code is fast but platform-specific
- JIT gives best of both: portability + performance

---

**Two JIT Compilers**:

**C1 Compiler (Client Compiler)**:
- Fast compilation, less optimization
- Low overhead
- Good for startup time
- Used for code that runs occasionally

**C2 Compiler (Server Compiler)**:
- Slow compilation, heavy optimization
- High overhead
- Best for long-running code
- Used for "hot" code (frequently executed)

---

**Tiered Compilation** (Default in Java 8+):
```
Level 0: Interpreter (no compilation)
    ↓
Level 1: C1 with minimal profiling
    ↓
Level 2: C1 with full profiling
    ↓
Level 3: C1 with full optimization
    ↓
Level 4: C2 with aggressive optimization
```

**How it works**:
1. Code starts in interpreter
2. JVM profiles code (counts executions)
3. "Warm" code → C1 compiles (quick optimization)
4. "Hot" code → C2 compiles (aggressive optimization)
5. Optimized native code runs directly

**Example**:
```java
public int sum(int a, int b) {
    return a + b;
}

// First 100 calls: Interpreted (slow)
// Next 1000 calls: C1 compiled (faster)
// After 10000 calls: C2 compiled (fastest)
```

**JIT Optimizations**:
- Method inlining
- Dead code elimination
- Loop unrolling
- Escape analysis

**Control JIT**:
```bash
-XX:+TieredCompilation  # Enable tiered (default)
-XX:-TieredCompilation  # Disable tiered
-XX:CompileThreshold=10000  # Compilation threshold
```

**Remember**: "Interpreter → C1 (quick) → C2 (aggressive)" - Tiered compilation

---

## 69. How does JVM execute bytecode?

**Simple Answer:**

**JVM Execution Process**:

**Step 1: Loading**
```
.class file → ClassLoader → Method Area (Metaspace)
```
- ClassLoader reads .class file
- Verifies bytecode (security check)
- Stores class metadata in Method Area

**Step 2: Linking**
- **Verification**: Ensures bytecode is valid and safe
- **Preparation**: Allocates memory for static variables
- **Resolution**: Converts symbolic references to direct references

**Step 3: Initialization**
- Executes static initializers
- Initializes static variables

**Step 4: Execution**
```
Bytecode → Interpreter/JIT → Native Code → CPU
```

---

**Execution Methods**:

**1. Interpretation** (Slow):
```java
// Java code
int sum = a + b;

// Bytecode
iload_1    // Load 'a' onto stack
iload_2    // Load 'b' onto stack
iadd       // Add top two values
istore_3   // Store result in 'sum'

// Interpreter executes each instruction one by one
```

**2. JIT Compilation** (Fast):
- Hot code compiled to native machine code
- Runs directly on CPU
- Much faster than interpretation

---

**JVM Components in Execution**:

**Stack** (per thread):
- Stores method calls and local variables
- Each method call creates a stack frame

**Heap**:
- Stores objects
- Shared by all threads

**Program Counter (PC)**:
- Points to current instruction
- Each thread has its own PC

**Native Method Stack**:
- For native (C/C++) method calls

---

**Example Execution**:
```java
public class Example {
    public static void main(String[] args) {
        int x = 5;
        int y = add(x, 3);
    }
    
    static int add(int a, int b) {
        return a + b;
    }
}
```

**Execution Flow**:
1. ClassLoader loads Example.class
2. Verifies bytecode
3. Initializes class (if needed)
4. Creates stack frame for main()
5. Executes main() bytecode (interpreted initially)
6. Calls add() - new stack frame
7. Returns result, pops stack frame
8. If add() called frequently, JIT compiles it
9. Next calls use compiled native code

**Remember**: "Load → Link → Initialize → Execute (Interpret/JIT)"

---

## 70. What is method inlining and escape analysis?

**Simple Answer:**

**Method Inlining**: Replace method call with method body (JIT optimization).

**Without Inlining**:
```java
public int calculate() {
    return add(5, 3); // Method call overhead
}

int add(int a, int b) {
    return a + b;
}

// Execution: Jump to add(), execute, return (slow)
```

**With Inlining** (JIT does this):
```java
public int calculate() {
    return 5 + 3; // No method call, direct calculation
}

// Execution: Direct calculation (fast)
```

**Benefits**:
- Eliminates method call overhead
- Enables further optimizations
- Faster execution

**When JIT inlines**:
- Small methods (< 35 bytecodes by default)
- Frequently called methods
- Final, private, or static methods (easier to inline)

**Control inlining**:
```bash
-XX:MaxInlineSize=35  # Max method size to inline
-XX:FreqInlineSize=325  # Max size for frequent methods
```

---

**Escape Analysis**: Determines if object escapes method scope (JIT optimization).

**Object Escapes** (used outside method):
```java
public User createUser() {
    User user = new User();
    return user; // Escapes! Used by caller
}
// Must allocate on heap
```

**Object Doesn't Escape** (local only):
```java
public int calculate() {
    Point p = new Point(5, 3); // Only used here
    return p.x + p.y;
}
// Can optimize!
```

**Optimizations when object doesn't escape**:

**1. Stack Allocation** (instead of heap):
```java
// Normal: Allocate on heap (slow, needs GC)
Point p = new Point(5, 3);

// Optimized: Allocate on stack (fast, auto-cleanup)
// JIT does this automatically
```

**2. Scalar Replacement** (eliminate object):
```java
// Original
Point p = new Point(5, 3);
int sum = p.x + p.y;

// Optimized by JIT (no object created!)
int x = 5;
int y = 3;
int sum = x + y;
```

**3. Lock Elision** (remove unnecessary locks):
```java
public void method() {
    StringBuffer sb = new StringBuffer(); // Local, doesn't escape
    sb.append("Hello"); // Synchronized method
}

// JIT removes synchronization (no other thread can access)
```

**Benefits**:
- Reduced heap allocation
- Less GC pressure
- Better performance
- Removed unnecessary locks

**Enable/Disable**:
```bash
-XX:+DoEscapeAnalysis  # Enable (default)
-XX:-DoEscapeAnalysis  # Disable
```

**Remember**: Inlining = "Replace call with body" | Escape Analysis = "Keep local objects on stack"

---

## 71. What are JVM flags for debugging and performance tuning?

**Simple Answer:**

**Memory Flags**:
```bash
# Heap size
-Xms2g              # Initial heap size (2GB)
-Xmx4g              # Maximum heap size (4GB)
-Xmn1g              # Young generation size (1GB)

# Metaspace
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=512m

# Stack size per thread
-Xss1m              # 1MB per thread stack
```

---

**GC Flags**:
```bash
# Choose GC
-XX:+UseG1GC        # G1 Garbage Collector (default Java 9+)
-XX:+UseZGC         # ZGC (low latency)
-XX:+UseParallelGC  # Parallel GC (throughput)

# G1 tuning
-XX:MaxGCPauseMillis=200    # Target max pause time
-XX:G1HeapRegionSize=16m    # Region size

# GC logging (Java 9+)
-Xlog:gc*:file=gc.log:time,uptime,level,tags
```

---

**JIT Compiler Flags**:
```bash
# Tiered compilation
-XX:+TieredCompilation      # Enable (default)
-XX:TieredStopAtLevel=1     # Stop at C1 (faster startup)

# Compilation threshold
-XX:CompileThreshold=10000  # Compile after 10k invocations

# Print compilation
-XX:+PrintCompilation       # Show what's being compiled
```

---

**Debugging Flags**:
```bash
# Print JVM flags
-XX:+PrintFlagsFinal        # All JVM flags and values
-XX:+PrintCommandLineFlags  # Flags explicitly set

# Class loading
-verbose:class              # Print loaded classes
-XX:+TraceClassLoading      # Detailed class loading

# GC details
-verbose:gc                 # Basic GC info
-XX:+PrintGCDetails         # Detailed GC info (Java 8)

# Thread dumps on OutOfMemoryError
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/tmp/heapdump.hprof

# Native memory tracking
-XX:NativeMemoryTracking=summary
```

---

**Performance Tuning Flags**:
```bash
# Escape analysis
-XX:+DoEscapeAnalysis       # Enable (default)

# Aggressive optimizations
-XX:+AggressiveOpts         # Enable experimental optimizations

# Large pages (better performance)
-XX:+UseLargePages

# String deduplication (save memory)
-XX:+UseStringDeduplication

# Disable explicit GC calls
-XX:+DisableExplicitGC      # Ignore System.gc()
```

---

**Monitoring Flags**:
```bash
# JMX monitoring
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=9010
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false

# Flight Recorder (production profiling)
-XX:+FlightRecorder
-XX:StartFlightRecording=duration=60s,filename=recording.jfr
```

---

**Common Combinations**:

**Development** (fast startup):
```bash
java -Xms512m -Xmx512m -XX:TieredStopAtLevel=1 -jar app.jar
```

**Production** (optimized):
```bash
java -Xms4g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/logs/heapdump.hprof \
     -Xlog:gc*:file=/logs/gc.log:time,uptime \
     -jar app.jar
```

**Debugging** (troubleshooting):
```bash
java -Xms2g -Xmx2g \
     -verbose:gc \
     -XX:+PrintGCDetails \
     -XX:+PrintCommandLineFlags \
     -XX:+HeapDumpOnOutOfMemoryError \
     -jar app.jar
```

---

**How to check current flags**:
```bash
# Running JVM
jinfo -flags <pid>

# Or use JVM flag
java -XX:+PrintFlagsFinal -version | grep <flag-name>
```

**Remember**: "Memory (Xms/Xmx), GC (UseG1GC), Debug (HeapDump), Monitor (JMX)"

---

## Summary Cheat Sheet

| Question | Key Concept | Remember As |
|----------|-------------|-------------|
| 61 | Exception Types | Checked = expected, Unchecked = bugs |
| 62 | Try-Catch-Finally | Try → Catch → Finally (always) → Return |
| 63 | Try-with-resources | Auto-close, implements AutoCloseable |
| 64 | Custom Exceptions | Extend RuntimeException, add context |
| 65 | Exception Chaining | Wrap exceptions, preserve cause |
| 66 | ClassLoader Hierarchy | Bootstrap → Platform → Application |
| 67 | Parent Delegation | Ask parent first, load yourself last |
| 68 | JIT Compilation | Interpreter → C1 (quick) → C2 (aggressive) |
| 69 | Bytecode Execution | Load → Link → Initialize → Execute |
| 70 | JIT Optimizations | Inlining (replace call), Escape (stack alloc) |
| 71 | JVM Flags | Xms/Xmx (heap), UseG1GC, HeapDump |

---

## Quick Interview Tips

1. **Exception handling**: Always mention try-with-resources for resource management
2. **ClassLoaders**: Explain parent delegation for security
3. **JIT**: Mention tiered compilation (C1 + C2)
4. **Performance**: Know common JVM flags (Xms, Xmx, UseG1GC)
5. **Real experience**: "In production, we use -Xms4g -Xmx4g with G1GC..."
6. **Monitoring**: Mention heap dumps, GC logs, JFR
7. **Optimization**: Explain method inlining and escape analysis benefits

---

## Common Interview Scenarios

**Scenario 1**: "Application throwing OutOfMemoryError, how to debug?"
**Answer**: "Enable heap dump with -XX:+HeapDumpOnOutOfMemoryError, analyze with MAT, check for memory leaks"

**Scenario 2**: "How to improve application startup time?"
**Answer**: "Use -XX:TieredStopAtLevel=1 to stop at C1 compiler, reduce classpath scanning, use lazy initialization"

**Scenario 3**: "Explain how JVM optimizes your code"
**Answer**: "JIT compiler uses method inlining for small methods, escape analysis for local objects, and tiered compilation for hot code"

---

## Real-World Production Settings

**Microservice** (Spring Boot):
```bash
java -Xms1g -Xmx1g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/logs/heap.hprof \
     -Xlog:gc*:file=/logs/gc.log \
     -jar service.jar
```

**Batch Processing** (High throughput):
```bash
java -Xms8g -Xmx8g \
     -XX:+UseParallelGC \
     -XX:ParallelGCThreads=8 \
     -jar batch-job.jar
```

**Low Latency** (Trading system):
```bash
java -Xms16g -Xmx16g \
     -XX:+UseZGC \
     -XX:+UnlockExperimentalVMOptions \
     -jar trading-app.jar
```

# ✅ 6) Java 8, 11 and 17 Features - 17 Questions

## 72. What are lambda expressions and functional interfaces?

**Simple Answer:**

**Lambda Expression**: Anonymous function - a short way to write methods without a name.

**Old Way** (Anonymous class):
```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};
```

**Lambda Way**:
```java
Runnable r = () -> System.out.println("Hello");
```

**Lambda Syntax**:
```java
// No parameters
() -> System.out.println("Hello")

// One parameter (parentheses optional)
x -> x * 2
(x) -> x * 2

// Multiple parameters
(x, y) -> x + y

// Multiple statements (need braces)
(x, y) -> {
    int sum = x + y;
    return sum;
}
```

**Functional Interface**: Interface with exactly ONE abstract method.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // Only one abstract method
}

// Use with lambda
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3)); // 8
```

**Why Functional Interface**:
- Lambda needs a target type
- Interface must have exactly one abstract method
- Can have default and static methods

**Remember**: Lambda = "Short anonymous function" | Functional Interface = "One abstract method"

---

## 73. Explain built-in functional interfaces (Predicate, Function, Consumer, Supplier, BiFunction).

**Simple Answer:**

**1. Predicate<T>**: Takes input, returns boolean (test/filter)
```java
Predicate<Integer> isEven = x -> x % 2 == 0;
System.out.println(isEven.test(4)); // true

// Use case: filtering
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream().filter(x -> x > 2).collect(Collectors.toList());
```

**2. Function<T, R>**: Takes input T, returns output R (transform)
```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello")); // 5

// Use case: mapping
List<String> names = Arrays.asList("John", "Jane");
names.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
```

**3. Consumer<T>**: Takes input, returns nothing (consume/process)
```java
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello"); // Prints: Hello

// Use case: forEach
List<String> names = Arrays.asList("John", "Jane");
names.forEach(name -> System.out.println(name));
```

**4. Supplier<T>**: Takes nothing, returns output (supply/generate)
```java
Supplier<Double> random = () -> Math.random();
System.out.println(random.get()); // Random number

// Use case: lazy initialization
Supplier<List<String>> listSupplier = () -> new ArrayList<>();
```

**5. BiFunction<T, U, R>**: Takes two inputs, returns output
```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(5, 3)); // 8

// Use case: reduce operations
```

**Quick Reference**:
| Interface | Input | Output | Method | Use |
|-----------|-------|--------|--------|-----|
| Predicate<T> | T | boolean | test() | Filter |
| Function<T,R> | T | R | apply() | Transform |
| Consumer<T> | T | void | accept() | Process |
| Supplier<T> | none | T | get() | Generate |
| BiFunction<T,U,R> | T, U | R | apply() | Combine |

**Remember**: "Predicate tests, Function transforms, Consumer processes, Supplier generates"

---

## 74. What is method reference? Explain all four types.

**Simple Answer:**

**Method Reference**: Shorthand for lambda that calls a method.

**Syntax**: `ClassName::methodName`

---

**Type 1: Static Method Reference**
```java
// Lambda
Function<String, Integer> lambda = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> methodRef = Integer::parseInt;

// Usage
methodRef.apply("123"); // 123
```

---

**Type 2: Instance Method on Particular Object**
```java
String str = "Hello";

// Lambda
Supplier<Integer> lambda = () -> str.length();

// Method reference
Supplier<Integer> methodRef = str::length;

// Usage
methodRef.get(); // 5
```

---

**Type 3: Instance Method on Arbitrary Object**
```java
// Lambda
Function<String, String> lambda = s -> s.toUpperCase();

// Method reference
Function<String, String> methodRef = String::toUpperCase;

// Usage
methodRef.apply("hello"); // HELLO

// Common in streams
List<String> names = Arrays.asList("john", "jane");
names.stream().map(String::toUpperCase).collect(Collectors.toList());
```

---

**Type 4: Constructor Reference**
```java
// Lambda
Supplier<List<String>> lambda = () -> new ArrayList<>();

// Method reference
Supplier<List<String>> methodRef = ArrayList::new;

// Usage
List<String> list = methodRef.get();

// With parameters
Function<Integer, List<String>> listCreator = ArrayList::new;
List<String> list = listCreator.apply(10); // Initial capacity 10
```

**Summary**:
1. Static: `ClassName::staticMethod`
2. Instance (specific): `object::instanceMethod`
3. Instance (arbitrary): `ClassName::instanceMethod`
4. Constructor: `ClassName::new`

**Remember**: "Static, Specific Instance, Arbitrary Instance, Constructor" - SSAC

---

## 75. Difference between intermediate and terminal operations in Stream API.

**Simple Answer:**

**Intermediate Operations**: Return a stream, lazy (don't execute until terminal operation)

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

Stream<Integer> stream = numbers.stream()
    .filter(x -> x > 2)    // Intermediate
    .map(x -> x * 2);      // Intermediate
// Nothing executed yet!
```

**Common Intermediate Operations**:
- `filter()` - filter elements
- `map()` - transform elements
- `flatMap()` - flatten nested structures
- `distinct()` - remove duplicates
- `sorted()` - sort elements
- `limit()` - limit size
- `skip()` - skip elements

---

**Terminal Operations**: Trigger execution, return result (not a stream)

```java
List<Integer> result = numbers.stream()
    .filter(x -> x > 2)
    .map(x -> x * 2)
    .collect(Collectors.toList()); // Terminal - NOW it executes!
```

**Common Terminal Operations**:
- `collect()` - collect to collection
- `forEach()` - iterate
- `count()` - count elements
- `reduce()` - reduce to single value
- `findFirst()` - find first element
- `findAny()` - find any element
- `anyMatch()` - check if any match
- `allMatch()` - check if all match
- `noneMatch()` - check if none match

---

**Key Differences**:

| Aspect | Intermediate | Terminal |
|--------|--------------|----------|
| Returns | Stream | Result (not stream) |
| Execution | Lazy (deferred) | Eager (immediate) |
| Chaining | Can chain multiple | Ends the chain |
| Reusable | Yes (stream not consumed) | No (stream consumed) |

**Example showing lazy execution**:
```java
Stream<Integer> stream = numbers.stream()
    .filter(x -> {
        System.out.println("Filter: " + x);
        return x > 2;
    })
    .map(x -> {
        System.out.println("Map: " + x);
        return x * 2;
    });
// No output yet!

stream.collect(Collectors.toList()); // NOW prints output
```

**Remember**: Intermediate = "Lazy, returns stream" | Terminal = "Eager, returns result"

---

## 76. Explain `map()`, `flatMap()`, `filter()`, `reduce()`, `collect()`.

**Simple Answer:**

**1. filter()**: Keep elements that match condition
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> evens = numbers.stream()
    .filter(x -> x % 2 == 0)
    .collect(Collectors.toList());
// Result: [2, 4]
```

**2. map()**: Transform each element
```java
List<String> names = Arrays.asList("john", "jane");
List<String> upper = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
// Result: ["JOHN", "JANE"]
```

**3. flatMap()**: Flatten nested structures
```java
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);
List<Integer> flat = nested.stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4]
```

**4. reduce()**: Combine elements into single result
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

// Sum
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);
// Result: 10

// Max
Optional<Integer> max = numbers.stream()
    .reduce((a, b) -> a > b ? a : b);
// Result: Optional[4]
```

**5. collect()**: Collect stream into collection
```java
// To List
List<String> list = stream.collect(Collectors.toList());

// To Set
Set<String> set = stream.collect(Collectors.toSet());

// To Map
Map<Integer, String> map = users.stream()
    .collect(Collectors.toMap(User::getId, User::getName));

// Joining strings
String joined = names.stream()
    .collect(Collectors.joining(", "));
```

**Remember**: "Filter keeps, Map transforms, FlatMap flattens, Reduce combines, Collect gathers"

---

## 77. What is the difference between `map()` and `flatMap()`?

**Simple Answer:**

**map()**: One-to-one transformation (1 input → 1 output)
```java
List<String> names = Arrays.asList("John", "Jane");
List<Integer> lengths = names.stream()
    .map(s -> s.length())
    .collect(Collectors.toList());
// ["John", "Jane"] → [4, 4]
```

**flatMap()**: One-to-many transformation, then flatten (1 input → multiple outputs → flatten)
```java
List<String> sentences = Arrays.asList("Hello World", "Java Stream");
List<String> words = sentences.stream()
    .flatMap(s -> Arrays.stream(s.split(" ")))
    .collect(Collectors.toList());
// ["Hello World", "Java Stream"] → ["Hello", "World", "Java", "Stream"]
```

**Visual Difference**:
```java
// map() - keeps structure
[[1,2], [3,4]].map(list -> list) → [[1,2], [3,4]]

// flatMap() - flattens structure
[[1,2], [3,4]].flatMap(list -> list.stream()) → [1, 2, 3, 4]
```

**Real Example**:
```java
class User {
    List<String> emails;
}

List<User> users = getUsers();

// map() - returns List<List<String>>
List<List<String>> nested = users.stream()
    .map(user -> user.emails)
    .collect(Collectors.toList());

// flatMap() - returns List<String>
List<String> flat = users.stream()
    .flatMap(user -> user.emails.stream())
    .collect(Collectors.toList());
```

**Remember**: map = "Transform" | flatMap = "Transform + Flatten"

---

## 78. When should you use parallel streams? What are the pitfalls?

**Simple Answer:**

**Parallel Stream**: Process elements in parallel using multiple threads.

```java
// Sequential
list.stream().map(...).collect(...);

// Parallel
list.parallelStream().map(...).collect(...);
```

**When to Use**:
1. **Large datasets** (thousands+ elements)
2. **CPU-intensive operations** (complex calculations)
3. **Independent operations** (no shared state)
4. **Stateless operations**

```java
// Good use case
List<Integer> numbers = IntStream.range(1, 1000000).boxed().collect(Collectors.toList());
long sum = numbers.parallelStream()
    .mapToInt(x -> x * x)
    .sum();
```

**When NOT to Use**:
1. **Small datasets** (overhead > benefit)
2. **I/O operations** (database, file, network)
3. **Shared mutable state**
4. **Order matters**

---

**Pitfalls**:

**1. Shared Mutable State** (Race condition):
```java
// BAD - not thread-safe
List<Integer> result = new ArrayList<>();
numbers.parallelStream()
    .forEach(x -> result.add(x)); // Race condition!

// GOOD - use collect
List<Integer> result = numbers.parallelStream()
    .collect(Collectors.toList());
```

**2. Order Issues**:
```java
// Sequential - maintains order
list.stream().forEach(System.out::println);

// Parallel - random order
list.parallelStream().forEach(System.out::println);

// Parallel but ordered
list.parallelStream().forEachOrdered(System.out::println);
```

**3. Overhead for Small Data**:
```java
// BAD - overhead > benefit
List<Integer> small = Arrays.asList(1, 2, 3);
small.parallelStream().map(x -> x * 2).collect(Collectors.toList());
```

**4. Blocking Operations**:
```java
// BAD - blocks threads
users.parallelStream()
    .map(user -> database.findById(user.id)) // Blocking I/O
    .collect(Collectors.toList());
```

**Remember**: "Large data + CPU-intensive + Independent = Good for parallel"

---

## 79. Explain `Optional` class and its methods (`of`, `ofNullable`, `orElse`, `orElseGet`, `orElseThrow`).

**Simple Answer:**

**Optional**: Container that may or may not contain a value. Avoids NullPointerException.

**Creating Optional**:

**1. Optional.of()** - Value must not be null
```java
Optional<String> opt = Optional.of("Hello");
// Optional.of(null); // Throws NullPointerException
```

**2. Optional.ofNullable()** - Value can be null
```java
Optional<String> opt = Optional.ofNullable(getValue()); // Safe
Optional<String> empty = Optional.ofNullable(null); // Empty optional
```

**3. Optional.empty()** - Empty optional
```java
Optional<String> empty = Optional.empty();
```

---

**Using Optional**:

**1. orElse()** - Return default if empty
```java
String value = optional.orElse("default");
// Always evaluates default value
```

**2. orElseGet()** - Return from supplier if empty
```java
String value = optional.orElseGet(() -> "default");
// Only evaluates if empty (lazy)
```

**3. orElseThrow()** - Throw exception if empty
```java
String value = optional.orElseThrow();
// Throws NoSuchElementException

String value = optional.orElseThrow(() -> new CustomException());
// Throws custom exception
```

**4. isPresent()** - Check if value exists
```java
if(optional.isPresent()) {
    String value = optional.get();
}
```

**5. ifPresent()** - Execute if value exists
```java
optional.ifPresent(value -> System.out.println(value));
```

**6. map()** - Transform value if present
```java
Optional<Integer> length = optional.map(String::length);
```

**7. filter()** - Filter value
```java
Optional<String> filtered = optional.filter(s -> s.length() > 5);
```

**Real Example**:
```java
public Optional<User> findUser(int id) {
    User user = database.find(id);
    return Optional.ofNullable(user);
}

// Usage
User user = findUser(123)
    .filter(u -> u.isActive())
    .orElseThrow(() -> new UserNotFoundException());
```

**Remember**: "of (not null), ofNullable (can be null), orElse (default), orElseThrow (exception)"

---

## 80. Difference between `orElse()` and `orElseGet()`?

**Simple Answer:**

**Key Difference**: When the default value is evaluated.

**orElse()**: Always evaluates default value (eager)
```java
String value = optional.orElse(getDefaultValue());
// getDefaultValue() ALWAYS called, even if optional has value
```

**orElseGet()**: Evaluates only if empty (lazy)
```java
String value = optional.orElseGet(() -> getDefaultValue());
// getDefaultValue() called ONLY if optional is empty
```

---

**Performance Impact**:

```java
// Expensive operation
public String getDefault() {
    System.out.println("Computing default...");
    return "default";
}

Optional<String> opt = Optional.of("Hello");

// orElse - ALWAYS computes
String v1 = opt.orElse(getDefault());
// Prints: "Computing default..." even though opt has value!

// orElseGet - computes ONLY if needed
String v2 = opt.orElseGet(() -> getDefault());
// Doesn't print anything (opt has value)
```

---

**When to Use**:

**Use orElse()** when:
- Default value is simple constant
- No computation needed
```java
String name = optional.orElse("Unknown");
int count = optional.orElse(0);
```

**Use orElseGet()** when:
- Default value requires computation
- Method call needed
- Creating new object
```java
User user = optional.orElseGet(() -> new User());
String value = optional.orElseGet(() -> database.getDefault());
List<String> list = optional.orElseGet(() -> new ArrayList<>());
```

**Remember**: orElse = "Always evaluate" | orElseGet = "Lazy evaluate"

---

## 81. How do you handle exceptions in streams?

**Simple Answer:**

**Problem**: Streams don't handle checked exceptions well.

```java
// Doesn't compile - checked exception
list.stream()
    .map(s -> new URL(s)) // MalformedURLException
    .collect(Collectors.toList());
```

**Solution 1: Wrap in try-catch**
```java
list.stream()
    .map(s -> {
        try {
            return new URL(s);
        } catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }
    })
    .collect(Collectors.toList());
```

**Solution 2: Extract to method**
```java
private URL createURL(String s) {
    try {
        return new URL(s);
    } catch(MalformedURLException e) {
        throw new RuntimeException(e);
    }
}

list.stream()
    .map(this::createURL)
    .collect(Collectors.toList());
```

**Solution 3: Wrapper function**
```java
@FunctionalInterface
interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}

static <T, R> Function<T, R> wrap(CheckedFunction<T, R> func) {
    return t -> {
        try {
            return func.apply(t);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    };
}

// Usage
list.stream()
    .map(wrap(s -> new URL(s)))
    .collect(Collectors.toList());
```

**Solution 4: Filter out errors**
```java
list.stream()
    .map(s -> {
        try {
            return Optional.of(new URL(s));
        } catch(MalformedURLException e) {
            return Optional.empty();
        }
    })
    .filter(Optional::isPresent)
    .map(Optional::get)
    .collect(Collectors.toList());
```

**Remember**: "Wrap in RuntimeException or use Optional to filter errors"

---

## 82. What is the `Collectors` utility class? Explain grouping and partitioning.

**Simple Answer:**

**Collectors**: Utility class with common collection operations.

**Basic Collectors**:
```java
// To List
List<String> list = stream.collect(Collectors.toList());

// To Set
Set<String> set = stream.collect(Collectors.toSet());

// To Map
Map<Integer, String> map = users.stream()
    .collect(Collectors.toMap(User::getId, User::getName));

// Joining
String joined = stream.collect(Collectors.joining(", "));
// Result: "a, b, c"

// Counting
long count = stream.collect(Collectors.counting());

// Sum
int sum = stream.collect(Collectors.summingInt(User::getAge));
```

---

**Grouping**: Group elements by key

```java
class Employee {
    String name;
    String department;
    int salary;
}

List<Employee> employees = getEmployees();

// Group by department
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
// Result: {"IT": [emp1, emp2], "HR": [emp3]}

// Group and count
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
    ));
// Result: {"IT": 2, "HR": 1}

// Group and sum
Map<String, Integer> salaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.summingInt(Employee::getSalary)
    ));
```

---

**Partitioning**: Split into two groups (true/false)

```java
// Partition by condition
Map<Boolean, List<Employee>> partitioned = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));
// Result: {true: [highPaid], false: [lowPaid]}

// Partition and count
Map<Boolean, Long> count = employees.stream()
    .collect(Collectors.partitioningBy(
        e -> e.getSalary() > 50000,
        Collectors.counting()
    ));
// Result: {true: 5, false: 10}
```

**Remember**: groupingBy = "Multiple groups by key" | partitioningBy = "Two groups (true/false)"


## 83. What are new features in Java 11 (var, String methods, HTTP Client)?

**Simple Answer:**

**1. Local Variable Type Inference (var)**:
```java
// Instead of
String name = "John";
List<String> list = new ArrayList<>();

// Use var
var name = "John"; // Compiler infers String
var list = new ArrayList<String>(); // Compiler infers type
```

**Rules**:
- Only for local variables
- Must initialize immediately
- Cannot be null
- Not for fields, parameters, or return types

---

**2. New String Methods**:

```java
// isBlank() - checks if empty or whitespace
"   ".isBlank(); // true
"".isBlank(); // true
"Hello".isBlank(); // false

// lines() - split by line breaks
String multiline = "Line1\nLine2\nLine3";
multiline.lines().forEach(System.out::println);

// strip() - removes leading/trailing whitespace (Unicode-aware)
"  Hello  ".strip(); // "Hello"

// stripLeading() / stripTrailing()
"  Hello  ".stripLeading(); // "Hello  "

// repeat() - repeat string
"Ha".repeat(3); // "HaHaHa"
```

---

**3. HTTP Client API** (Standard):
```java
// Synchronous request
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .GET()
    .build();

HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
System.out.println(response.body());

// Asynchronous request
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);
```

---

**4. Other Features**:
- **Files methods**: `Files.readString()`, `Files.writeString()`
- **Collection.toArray()**: `list.toArray(String[]::new)`
- **Predicate.not()**: `list.stream().filter(Predicate.not(String::isBlank))`
- **Running Java files directly**: `java HelloWorld.java` (no compilation needed)

**Remember**: "var (type inference), String methods (isBlank, lines, strip, repeat), HTTP Client"

---

## 84. What are new features in Java 17 (Sealed classes, Pattern matching, Records)?

**Simple Answer:**

**1. Sealed Classes** (Control inheritance):
```java
// Only specified classes can extend
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
}

final class Circle extends Shape { }
final class Rectangle extends Shape { }
final class Triangle extends Shape { }

// class Square extends Shape { } // Compilation error!
```

---

**2. Pattern Matching for instanceof**:
```java
// Old way
if(obj instanceof String) {
    String s = (String) obj; // Cast needed
    System.out.println(s.length());
}

// New way (Java 16+)
if(obj instanceof String s) { // Pattern variable
    System.out.println(s.length()); // No cast needed
}
```

---

**3. Records** (Immutable data classes):
```java
// Old way
public class User {
    private final String name;
    private final int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    // equals, hashCode, toString...
}

// New way with Record
public record User(String name, int age) { }

// Usage
User user = new User("John", 30);
System.out.println(user.name()); // Accessor method
System.out.println(user); // Auto toString
```

---

**4. Text Blocks** (Java 15+):
```java
// Old way
String json = "{\n" +
              "  \"name\": \"John\",\n" +
              "  \"age\": 30\n" +
              "}";

// New way
String json = """
    {
      "name": "John",
      "age": 30
    }
    """;
```

---

**5. Switch Expressions** (Java 14+):
```java
// Old switch
String result;
switch(day) {
    case MONDAY:
    case FRIDAY:
        result = "Work";
        break;
    case SATURDAY:
        result = "Fun";
        break;
    default:
        result = "Other";
}

// New switch expression
String result = switch(day) {
    case MONDAY, FRIDAY -> "Work";
    case SATURDAY -> "Fun";
    default -> "Other";
};
```

**Remember**: "Sealed (control inheritance), Pattern matching (no cast), Records (data classes)"

---

## 85. What are Records in Java? When should you use them?

**Simple Answer:**

**Record**: Immutable data carrier class (Java 14+).

**Basic Record**:
```java
public record Point(int x, int y) { }

// Automatically generates:
// - Constructor: Point(int x, int y)
// - Getters: x(), y()
// - equals(), hashCode(), toString()
// - All fields are final
```

**Usage**:
```java
Point p = new Point(10, 20);
System.out.println(p.x()); // 10
System.out.println(p.y()); // 20
System.out.println(p); // Point[x=10, y=20]
```

---

**Custom Constructor**:
```java
public record User(String name, int age) {
    // Compact constructor (validation)
    public User {
        if(age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
```

**Custom Methods**:
```java
public record Rectangle(int width, int height) {
    public int area() {
        return width * height;
    }
}
```

**Static Members**:
```java
public record User(String name, int age) {
    public static User of(String name) {
        return new User(name, 0);
    }
}
```

---

**When to Use Records**:

**Use Records for**:
- DTOs (Data Transfer Objects)
- API request/response models
- Configuration objects
- Value objects
- Immutable data holders

```java
// Perfect for DTOs
public record UserDTO(Long id, String name, String email) { }

// API response
public record ApiResponse(int status, String message, Object data) { }
```

**Don't Use Records for**:
- Mutable objects (records are immutable)
- Entities with behavior (use classes)
- JPA entities (need no-arg constructor)
- Objects needing inheritance (records are final)

**Remember**: "Records = Immutable data carriers, perfect for DTOs"

---

## 86. What are sealed classes and interfaces? Use cases.

**Simple Answer:**

**Sealed Class**: Restricts which classes can extend/implement it.

**Basic Sealed Class**:
```java
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
}

final class Circle extends Shape { }
final class Rectangle extends Shape { }
final class Triangle extends Shape { }

// Any other class cannot extend Shape
```

---

**Sealed Interface**:
```java
public sealed interface Payment 
    permits CreditCard, DebitCard, Cash {
}

final class CreditCard implements Payment { }
final class DebitCard implements Payment { }
final class Cash implements Payment { }
```

---

**Subclass Modifiers**:

Permitted subclasses must be one of:
1. **final** - cannot be extended further
2. **sealed** - can be extended by specific classes
3. **non-sealed** - can be extended by anyone

```java
public sealed class Vehicle 
    permits Car, Bike, Truck {
}

final class Car extends Vehicle { } // Cannot extend further

sealed class Bike extends Vehicle 
    permits SportBike, CruiserBike { } // Controlled extension

non-sealed class Truck extends Vehicle { } // Anyone can extend

class PickupTruck extends Truck { } // OK
```

---

**Use Cases**:

**1. Domain Modeling** (Exhaustive types):
```java
public sealed interface Result<T> 
    permits Success, Failure {
}

record Success<T>(T value) implements Result<T> { }
record Failure<T>(String error) implements Result<T> { }

// Pattern matching
String message = switch(result) {
    case Success(var value) -> "Got: " + value;
    case Failure(var error) -> "Error: " + error;
}; // Compiler knows all cases covered
```

**2. State Machine**:
```java
public sealed interface OrderState 
    permits Pending, Confirmed, Shipped, Delivered {
}

final class Pending implements OrderState { }
final class Confirmed implements OrderState { }
final class Shipped implements OrderState { }
final class Delivered implements OrderState { }
```

**3. API Design** (Prevent misuse):
```java
public sealed interface DatabaseOperation 
    permits Read, Write {
}

final class Read implements DatabaseOperation { }
final class Write implements DatabaseOperation { }
// Users cannot create custom operations
```

**Benefits**:
- Exhaustive pattern matching
- Better domain modeling
- Controlled inheritance
- Compiler helps catch missing cases

**Remember**: "Sealed = Control who can extend, perfect for fixed set of types"

---

## 87. Explain text blocks in Java 15+.

**Simple Answer:**

**Text Block**: Multi-line string literal with better formatting (Java 15+).

**Syntax**: Use triple quotes `"""`

**Old Way**:
```java
String html = "<html>\n" +
              "  <body>\n" +
              "    <h1>Hello</h1>\n" +
              "  </body>\n" +
              "</html>";
```

**Text Block Way**:
```java
String html = """
    <html>
      <body>
        <h1>Hello</h1>
      </body>
    </html>
    """;
```

---

**Features**:

**1. Automatic Indentation**:
```java
String text = """
    Line 1
    Line 2
      Indented
    """;
// Indentation relative to closing quotes
```

**2. No Need for Escape Sequences**:
```java
// Old way
String json = "{\"name\":\"John\",\"age\":30}";

// Text block
String json = """
    {
      "name": "John",
      "age": 30
    }
    """;
```

**3. String Interpolation** (with formatted):
```java
String name = "John";
int age = 30;

String text = """
    Name: %s
    Age: %d
    """.formatted(name, age);
```

---

**Common Use Cases**:

**SQL Queries**:
```java
String sql = """
    SELECT u.id, u.name, u.email
    FROM users u
    WHERE u.age > 18
    ORDER BY u.name
    """;
```

**JSON**:
```java
String json = """
    {
      "users": [
        {"id": 1, "name": "John"},
        {"id": 2, "name": "Jane"}
      ]
    }
    """;
```

**HTML**:
```java
String html = """
    <!DOCTYPE html>
    <html>
      <head><title>Page</title></head>
      <body>
        <h1>Welcome</h1>
      </body>
    </html>
    """;
```

**Benefits**:
- More readable
- No escape sequences needed
- Preserves formatting
- Less error-prone

**Remember**: "Text blocks = Multi-line strings with triple quotes, no escaping needed"

---

## 88. What is pattern matching for instanceof (Java 16+)?

**Simple Answer:**

**Pattern Matching**: Combine type check and cast in one step.

**Old Way** (Before Java 16):
```java
if(obj instanceof String) {
    String s = (String) obj; // Explicit cast
    System.out.println(s.length());
}
```

**New Way** (Java 16+):
```java
if(obj instanceof String s) { // Pattern variable
    System.out.println(s.length()); // No cast needed
}
```

---

**Benefits**:

**1. Less Boilerplate**:
```java
// Old
if(shape instanceof Circle) {
    Circle circle = (Circle) shape;
    System.out.println(circle.radius());
}

// New
if(shape instanceof Circle circle) {
    System.out.println(circle.radius());
}
```

**2. Scope of Pattern Variable**:
```java
if(obj instanceof String s) {
    System.out.println(s.length()); // s in scope
}
// s not in scope here

// Works with logical operators
if(obj instanceof String s && s.length() > 5) {
    System.out.println(s); // s in scope
}
```

**3. Negation**:
```java
if(!(obj instanceof String s)) {
    // s NOT in scope here
} else {
    System.out.println(s); // s in scope in else
}
```

---

**Real-World Examples**:

**Processing Different Types**:
```java
public void process(Object obj) {
    if(obj instanceof String s) {
        System.out.println("String: " + s.toUpperCase());
    } else if(obj instanceof Integer i) {
        System.out.println("Integer: " + (i * 2));
    } else if(obj instanceof List<?> list) {
        System.out.println("List size: " + list.size());
    }
}
```

**With Switch** (Java 17+ Preview):
```java
String result = switch(obj) {
    case String s -> "String: " + s;
    case Integer i -> "Integer: " + i;
    case null -> "Null value";
    default -> "Unknown type";
};
```

**Validation**:
```java
public double calculateArea(Shape shape) {
    if(shape instanceof Circle c) {
        return Math.PI * c.radius() * c.radius();
    } else if(shape instanceof Rectangle r) {
        return r.width() * r.height();
    }
    throw new IllegalArgumentException("Unknown shape");
}
```

**Remember**: "Pattern matching = Type check + cast in one step, less boilerplate"

---

## Summary Cheat Sheet

| Question | Key Concept | Remember As |
|----------|-------------|-------------|
| 72 | Lambda & Functional Interface | Short anonymous function, one abstract method |
| 73 | Functional Interfaces | Predicate tests, Function transforms, Consumer processes |
| 74 | Method Reference | Static, Instance, Arbitrary, Constructor (SSAC) |
| 75 | Stream Operations | Intermediate (lazy), Terminal (eager) |
| 76 | Stream Methods | Filter keeps, Map transforms, FlatMap flattens |
| 77 | map vs flatMap | map = transform, flatMap = transform + flatten |
| 78 | Parallel Streams | Large data + CPU-intensive + Independent |
| 79 | Optional | of (not null), ofNullable (can be null) |
| 80 | orElse vs orElseGet | orElse = eager, orElseGet = lazy |
| 81 | Stream Exceptions | Wrap in RuntimeException or use Optional |
| 82 | Collectors | groupingBy (multiple groups), partitioningBy (two groups) |
| 83 | Java 11 | var, String methods, HTTP Client |
| 84 | Java 17 | Sealed classes, Pattern matching, Records |
| 85 | Records | Immutable data carriers, perfect for DTOs |
| 86 | Sealed Classes | Control inheritance, fixed set of types |
| 87 | Text Blocks | Multi-line strings with triple quotes |
| 88 | Pattern Matching | Type check + cast in one step |

---

## Quick Interview Tips

1. **Lambda**: Always mention functional interface requirement
2. **Streams**: Explain lazy evaluation of intermediate operations
3. **Optional**: Prefer orElseGet over orElse for expensive operations
4. **Parallel Streams**: Mention pitfalls (shared state, small data)
5. **Records**: Perfect for DTOs, immutable by design
6. **Sealed Classes**: Great for domain modeling with fixed types
7. **Real Experience**: "In our project, we use Records for API responses..."

---

## Common Interview Scenarios

**Scenario 1**: "When would you use parallel streams?"
**Answer**: "For large datasets with CPU-intensive operations and no shared state. For example, processing millions of records with complex calculations. Avoid for I/O operations or small datasets."

**Scenario 2**: "Why use Optional instead of null?"
**Answer**: "Optional makes null handling explicit, prevents NullPointerException, and provides functional methods like map, filter, orElse. It's self-documenting that a value might be absent."

**Scenario 3**: "Difference between map and flatMap?"
**Answer**: "map does one-to-one transformation, flatMap does one-to-many and flattens. Use flatMap when each element produces a stream or collection that needs flattening."

---

## Real-World Code Examples

**API Response with Records**:
```java
public record ApiResponse<T>(
    int status,
    String message,
    T data,
    LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data, LocalDateTime.now());
    }
}
```

**Domain Model with Sealed Classes**:
```java
public sealed interface PaymentMethod permits CreditCard, DebitCard, UPI {
}

record CreditCard(String number, String cvv) implements PaymentMethod { }
record DebitCard(String number, String pin) implements PaymentMethod { }
record UPI(String id) implements PaymentMethod { }

// Exhaustive pattern matching
double processFee(PaymentMethod payment) {
    return switch(payment) {
        case CreditCard c -> 2.5;
        case DebitCard d -> 1.5;
        case UPI u -> 0.0;
    };
}
```

**Stream Processing**:
```java
List<User> activeUsers = users.stream()
    .filter(u -> u.isActive())
    .filter(u -> u.getAge() > 18)
    .sorted(Comparator.comparing(User::getName))
    .limit(10)
    .collect(Collectors.toList());
```

# ✅ 7) Spring Framework (Core + MVC) - 14 Questions

## 89. What is IoC (Inversion of Control) and Dependency Injection?

**Simple Answer:**

IoC means "don't call us, we'll call you." Instead of your code creating objects, Spring creates and manages them for you.

Dependency Injection is HOW Spring implements IoC. Spring injects dependencies into your class instead of you creating them with `new`.

**Example:**
```java
// Without DI - You control object creation
public class UserService {
    private UserRepository repo = new UserRepository(); // You create it
}

// With DI - Spring controls it
@Service
public class UserService {
    @Autowired
    private UserRepository repo; // Spring injects it
}
```

**Remember:** IoC = What (concept), DI = How (implementation)

---

## 90. Explain Spring Bean lifecycle in detail.

**Simple Answer:**

Think of it as a baby growing up:

1. **Instantiate** - Bean is born (constructor called)
2. **Populate Properties** - Bean gets its dependencies (DI happens)
3. **setBeanName()** - Bean learns its name (if implements BeanNameAware)
4. **setBeanFactory()** - Bean knows its factory (if implements BeanFactoryAware)
5. **setApplicationContext()** - Bean knows the context (if implements ApplicationContextAware)
6. **@PostConstruct / afterPropertiesSet()** - Bean initializes (custom init logic)
7. **Bean Ready** - Bean is fully grown and ready to use
8. **@PreDestroy / destroy()** - Bean cleanup before death (shutdown)

**Remember:** Born → Fed → Named → Aware → Init → Ready → Destroy

---

## 91. What are bean scopes (singleton, prototype, request, session, application)?

**Simple Answer:**

Bean scope = How many instances Spring creates

- **singleton** (default) - One instance per Spring container. Like a single CEO in a company.
- **prototype** - New instance every time you ask. Like getting a new coffee cup each time.
- **request** - One instance per HTTP request. Dies when request ends.
- **session** - One instance per HTTP session. Dies when session expires.
- **application** - One instance per ServletContext. Shared across all sessions.

```java
@Scope("singleton") // Default
@Scope("prototype") // New every time
@Scope("request")   // Web only
@Scope("session")   // Web only
```

**Remember:** Singleton = 1, Prototype = Many, Request/Session/Application = Web scopes

---

## 92. Difference between `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController`.

**Simple Answer:**

All are `@Component` with different names for clarity:

- **@Component** - Generic Spring bean. Use when nothing else fits.
- **@Service** - Business logic layer. Use for service classes.
- **@Repository** - Data access layer. Use for DAO classes. Adds exception translation.
- **@Controller** - Web MVC controller. Returns views (HTML pages).
- **@RestController** - REST API controller. Returns JSON/XML data. = @Controller + @ResponseBody

```java
@Component   // Generic
@Service     // Business logic
@Repository  // Database access
@Controller  // Returns views
@RestController // Returns JSON
```

**Remember:** Component is parent, others are specialized children with specific roles.

---

## 93. Constructor vs setter vs field injection - which is preferred and why?

**Simple Answer:**

**Constructor Injection** (BEST) - Preferred
```java
@Service
public class UserService {
    private final UserRepository repo;
    
    public UserService(UserRepository repo) { // Constructor
        this.repo = repo;
    }
}
```

**Why Constructor is Best:**
- Makes dependencies required (can't create object without them)
- Supports `final` fields (immutability)
- Easy to test (just pass mocks in constructor)
- No reflection needed

**Setter Injection** (OPTIONAL dependencies)
```java
@Autowired
public void setRepo(UserRepository repo) { }
```

**Field Injection** (AVOID)
```java
@Autowired
private UserRepository repo; // Hard to test, uses reflection
```

**Remember:** Constructor > Setter > Field. Constructor wins!

---

## 94. How do you resolve bean ambiguity using `@Primary` and `@Qualifier`?

**Simple Answer:**

When Spring finds multiple beans of same type, it gets confused. Two solutions:

**@Primary** - "Use this one by default"
```java
@Service
@Primary // This one wins by default
public class EmailService implements NotificationService { }

@Service
public class SmsService implements NotificationService { }
```

**@Qualifier** - "I want this specific one"
```java
@Service
@Qualifier("email")
public class EmailService implements NotificationService { }

@Autowired
@Qualifier("email") // Specify which one
private NotificationService service;
```

**Remember:** @Primary = default choice, @Qualifier = specific choice

---

## 95. What is `@Configuration` and `@Bean` annotation?

**Simple Answer:**

**@Configuration** - Marks a class as a source of bean definitions. Like a factory that produces beans.

**@Bean** - Marks a method that returns a bean. Spring will manage the returned object.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(); // Spring manages this
    }
    
    @Bean
    public UserService userService() {
        return new UserService(dataSource()); // Manual wiring
    }
}
```

**When to use:**
- Third-party classes you can't annotate with @Component
- Complex bean creation logic
- Conditional bean creation

**Remember:** @Configuration = Factory, @Bean = Product

---

## 96. Explain Spring AOP concepts (Aspect, Advice, Pointcut, JoinPoint, Weaving).

**Simple Answer:**

AOP = Add behavior to existing code without modifying it. Like adding security or logging.

**Key Concepts:**

- **Aspect** - The feature you want to add (e.g., logging, security)
- **Advice** - The actual code that runs (what to do)
- **Pointcut** - Where to apply it (which methods)
- **JoinPoint** - The exact execution point (method being called)
- **Weaving** - The process of applying aspects to code

```java
@Aspect
@Component
public class LoggingAspect { // Aspect
    
    @Before("execution(* com.example.service.*.*(..))") // Pointcut
    public void logBefore(JoinPoint joinPoint) { // Advice + JoinPoint
        System.out.println("Calling: " + joinPoint.getSignature());
    }
}
```

**Remember:** Aspect = What, Pointcut = Where, Advice = When, JoinPoint = Exact spot

---

## 97. What are advice types (Before, After, AfterReturning, AfterThrowing, Around)?

**Simple Answer:**

Advice = When your code runs relative to the target method.

- **@Before** - Runs BEFORE the method. Like checking security before entering.
- **@After** - Runs AFTER the method (always, even if exception). Like closing door.
- **@AfterReturning** - Runs AFTER successful return. Like celebrating success.
- **@AfterThrowing** - Runs AFTER exception. Like handling errors.
- **@Around** - Wraps the method. Most powerful. You control everything.

```java
@Before("execution(* save*(..))")
public void before() { } // Runs first

@AfterReturning("execution(* save*(..))")
public void afterSuccess() { } // Only if success

@AfterThrowing("execution(* save*(..))")
public void afterError() { } // Only if error

@After("execution(* save*(..))")
public void after() { } // Always runs

@Around("execution(* save*(..))")
public Object around(ProceedingJoinPoint pjp) throws Throwable {
    // Before
    Object result = pjp.proceed(); // Call actual method
    // After
    return result;
}
```

**Remember:** Before → Method → AfterReturning/AfterThrowing → After. Around wraps all.

---

## 98. Explain Spring MVC request flow and DispatcherServlet role.

**Simple Answer:**

**DispatcherServlet** = Front door of your application. All requests come here first.

**Request Flow (7 Steps):**

1. **Request arrives** → DispatcherServlet receives it
2. **Handler Mapping** → Finds which controller method to call
3. **Controller** → Your @Controller method executes
4. **Model & View** → Controller returns data and view name
5. **ViewResolver** → Finds the actual view (JSP/Thymeleaf)
6. **View Rendering** → View generates HTML
7. **Response** → HTML sent back to browser

```
Browser → DispatcherServlet → HandlerMapping → Controller 
→ ViewResolver → View → Response → Browser
```

**Remember:** DispatcherServlet is the traffic cop directing requests to right controllers.

---

## 99. What is `@PathVariable` vs `@RequestParam` vs `@RequestBody`?

**Simple Answer:**

All three extract data from HTTP request, but from different places:

**@PathVariable** - From URL path
```java
// URL: /users/123
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { } // id = 123
```

**@RequestParam** - From query string
```java
// URL: /users?name=John&age=25
@GetMapping("/users")
public List<User> search(@RequestParam String name, 
                         @RequestParam int age) { }
```

**@RequestBody** - From request body (JSON/XML)
```java
// Body: {"name":"John","age":25}
@PostMapping("/users")
public User create(@RequestBody User user) { } // JSON → User object
```

**Remember:** PathVariable = URL path, RequestParam = Query string, RequestBody = JSON body

---

## 100. How do you handle exceptions using `@ControllerAdvice` and `@ExceptionHandler`?

**Simple Answer:**

**@ControllerAdvice** = Global exception handler for all controllers. Catches exceptions from anywhere.

**@ExceptionHandler** = Handles specific exception types.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("User not found", 404);
        return ResponseEntity.status(404).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse("Internal error", 500);
        return ResponseEntity.status(500).body(error);
    }
}
```

**Benefits:**
- Centralized error handling
- Clean controllers (no try-catch everywhere)
- Consistent error responses

**Remember:** @ControllerAdvice = Global catcher, @ExceptionHandler = Specific exception handler

---

## 101. Explain Spring validation with `@Valid` and custom validators.

**Simple Answer:**

**@Valid** - Triggers validation on an object using annotations.

**Built-in Validations:**
```java
public class User {
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
    
    @Email(message = "Invalid email")
    private String email;
    
    @Min(18)
    @Max(100)
    private int age;
}

@PostMapping("/users")
public ResponseEntity<User> create(@Valid @RequestBody User user) {
    // If validation fails, Spring returns 400 Bad Request
}
```

**Custom Validator:**
```java
// 1. Create annotation
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface ValidPhone {
    String message() default "Invalid phone";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// 2. Create validator
public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && phone.matches("\\d{10}");
    }
}

// 3. Use it
public class User {
    @ValidPhone
    private String phone;
}
```

**Remember:** @Valid triggers validation, annotations define rules, custom validators for complex logic.

---

## 102. What is `@Transactional` annotation and its attributes?

**Simple Answer:**

**@Transactional** - Wraps method in a database transaction. If method fails, all database changes rollback.

**Key Attributes:**

```java
@Transactional(
    propagation = Propagation.REQUIRED,  // Join existing or create new
    isolation = Isolation.READ_COMMITTED, // Isolation level
    timeout = 30,                         // Timeout in seconds
    readOnly = false,                     // Read-only optimization
    rollbackFor = Exception.class,        // Rollback on these exceptions
    noRollbackFor = IllegalArgumentException.class // Don't rollback on these
)
public void transferMoney(Long from, Long to, BigDecimal amount) {
    // All DB operations in one transaction
    accountRepo.debit(from, amount);
    accountRepo.credit(to, amount);
    // If any fails, both rollback
}
```

**Common Propagation Types:**
- **REQUIRED** (default) - Use existing transaction or create new
- **REQUIRES_NEW** - Always create new transaction
- **SUPPORTS** - Use transaction if exists, otherwise non-transactional
- **MANDATORY** - Must have existing transaction, else throw exception
- **NEVER** - Must NOT have transaction, else throw exception

**Common Isolation Levels:**
- **READ_UNCOMMITTED** - Dirty reads possible
- **READ_COMMITTED** (default) - No dirty reads
- **REPEATABLE_READ** - No dirty, non-repeatable reads
- **SERIALIZABLE** - Full isolation, slowest

**Remember:** @Transactional = All or nothing. Success = commit, Failure = rollback.

---

## Summary Table for Quick Revision

| Question | Key Concept | Remember This |
|----------|-------------|---------------|
| 89 | IoC & DI | IoC = What, DI = How |
| 90 | Bean Lifecycle | Born → Fed → Named → Aware → Init → Ready → Destroy |
| 91 | Bean Scopes | Singleton=1, Prototype=Many, Request/Session=Web |
| 92 | Stereotype Annotations | Component is parent, others are specialized |
| 93 | Injection Types | Constructor > Setter > Field |
| 94 | Bean Ambiguity | @Primary = default, @Qualifier = specific |
| 95 | Configuration | @Configuration = Factory, @Bean = Product |
| 96 | AOP Concepts | Aspect=What, Pointcut=Where, Advice=When |
| 97 | Advice Types | Before → Method → AfterReturning/Throwing → After |
| 98 | MVC Flow | DispatcherServlet is the traffic cop |
| 99 | Request Data | PathVariable=URL, RequestParam=Query, RequestBody=JSON |
| 100 | Exception Handling | @ControllerAdvice = Global, @ExceptionHandler = Specific |
| 101 | Validation | @Valid triggers, annotations define rules |
| 102 | Transactions | @Transactional = All or nothing |

# ✅ 8) Spring Boot and Spring Security - 20 Questions

## 103. What is Spring Boot? What problems does it solve?

**Simple Answer:**

Spring Boot = Spring Framework made easy. It's Spring with batteries included.

**Problems it solves:**

1. **No XML configuration** - Everything is annotation-based or auto-configured
2. **No dependency hell** - Starters bundle compatible dependencies
3. **Embedded server** - No need to deploy WAR files to Tomcat
4. **Production-ready** - Built-in health checks, metrics, monitoring
5. **Fast setup** - Create app in minutes, not days

```java
@SpringBootApplication // Just this one annotation!
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args); // That's it!
    }
}
```

**Remember:** Spring Boot = Spring without the pain. Convention over configuration.

---

## 104. Explain Spring Boot auto-configuration mechanism.

**Simple Answer:**

Auto-configuration = Spring Boot looks at your classpath and automatically configures beans for you.

**How it works:**

1. Spring Boot scans classpath for libraries
2. Finds auto-configuration classes (in spring.factories)
3. Uses @Conditional annotations to decide what to configure
4. Creates beans automatically if conditions match

```java
// Example: If H2 is on classpath, auto-configure DataSource
@Configuration
@ConditionalOnClass(DataSource.class) // Only if DataSource exists
@ConditionalOnMissingBean(DataSource.class) // Only if you haven't created one
public class DataSourceAutoConfiguration {
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(); // Auto-configured!
    }
}
```

**You can override:**
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost/mydb
```

**Remember:** Auto-configuration = Smart defaults. Spring Boot guesses what you need.

---

## 105. What does `@SpringBootApplication` annotation include?

**Simple Answer:**

@SpringBootApplication is a combo annotation. It's 3 annotations in 1:

```java
@SpringBootApplication
// Equals:
@SpringBootConfiguration  // = @Configuration (marks as config class)
@EnableAutoConfiguration  // Enables auto-configuration magic
@ComponentScan           // Scans for @Component, @Service, etc.
```

**What each does:**

- **@SpringBootConfiguration** - Marks class as configuration source
- **@EnableAutoConfiguration** - Turns on auto-configuration
- **@ComponentScan** - Scans current package and sub-packages for beans

```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

**Remember:** @SpringBootApplication = 3-in-1 combo (Configuration + AutoConfig + ComponentScan)

---

## 106. What are Spring Boot starters? How do they work?

**Simple Answer:**

Starters = Pre-packaged dependency bundles. One dependency brings everything you need.

**Common Starters:**

```xml
<!-- Web applications -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- Includes: Spring MVC, Tomcat, Jackson, Validation -->
</dependency>

<!-- Database -->
<dependency>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <!-- Includes: Hibernate, JPA, JDBC, Transaction -->
</dependency>

<!-- Security -->
<dependency>
    <artifactId>spring-boot-starter-security</artifactId>
    <!-- Includes: Spring Security, all security modules -->
</dependency>
```

**How they work:**
- Each starter is a POM file with curated dependencies
- All versions are compatible (tested together)
- Triggers auto-configuration

**Remember:** Starters = Dependency bundles. One import, everything works.

---

## 107. How do you externalize configuration in Spring Boot?

**Simple Answer:**

Externalize = Keep configuration outside code. Change settings without recompiling.

**Priority Order (highest to lowest):**

1. **Command line arguments** - `java -jar app.jar --server.port=9090`
2. **Environment variables** - `SERVER_PORT=9090`
3. **application.properties/yml** - In project
4. **@PropertySource** - Custom property files
5. **Default values** - In code

```java
// application.properties
server.port=8080
app.name=MyApp
app.timeout=30

// Access in code
@Value("${app.name}")
private String appName;

// Or use @ConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private int timeout;
    // getters/setters
}
```

**Different environments:**
```
application.properties          # Default
application-dev.properties      # Dev profile
application-prod.properties     # Prod profile
```

**Remember:** Externalize = Configuration outside code. Priority: CLI > Env > Properties > Defaults

---

## 108. What are Spring profiles? How do you activate them?

**Simple Answer:**

Profiles = Different configurations for different environments (dev, test, prod).

**Setup:**

```properties
# application.properties (common)
app.name=MyApp

# application-dev.properties
spring.datasource.url=jdbc:h2:mem:testdb
logging.level.root=DEBUG

# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-server/db
logging.level.root=ERROR
```

**Activate profiles (4 ways):**

```bash
# 1. Command line
java -jar app.jar --spring.profiles.active=prod

# 2. Environment variable
export SPRING_PROFILES_ACTIVE=prod

# 3. application.properties
spring.profiles.active=dev

# 4. In code (testing)
@ActiveProfiles("test")
```

**Profile-specific beans:**
```java
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource(); // Only in dev
    }
}
```

**Remember:** Profiles = Environment-specific config. Activate via CLI, env, or properties.

---

## 109. Explain Spring Boot Actuator endpoints and their uses.

**Simple Answer:**

Actuator = Built-in monitoring and management endpoints. Health checks, metrics, info.

**Common Endpoints:**

```
/actuator/health      - App health status (UP/DOWN)
/actuator/info        - App information
/actuator/metrics     - Performance metrics
/actuator/env         - Environment properties
/actuator/loggers     - View/change log levels
/actuator/threaddump  - Thread dump
/actuator/heapdump    - Heap dump
/actuator/beans       - All Spring beans
```

**Setup:**

```xml
<dependency>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
# Expose endpoints
management.endpoints.web.exposure.include=health,info,metrics
# Or expose all (not recommended in prod)
management.endpoints.web.exposure.include=*

# Custom health info
management.endpoint.health.show-details=always
```

**Custom health check:**
```java
@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        if (isDatabaseUp()) {
            return Health.up().withDetail("database", "Available").build();
        }
        return Health.down().withDetail("database", "Unavailable").build();
    }
}
```

**Remember:** Actuator = Built-in monitoring. /actuator/health is most important.

---

## 110. How do you secure actuator endpoints?

**Simple Answer:**

Actuator endpoints expose sensitive info. Must secure them!

**3 Ways to secure:**

**1. Restrict which endpoints are exposed:**
```properties
# Only expose health and info
management.endpoints.web.exposure.include=health,info
```

**2. Use different port:**
```properties
# Actuator on different port (internal only)
management.server.port=9090
management.server.address=127.0.0.1
```

**3. Add Spring Security:**
```java
@Configuration
public class ActuatorSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health").permitAll() // Public
                .requestMatchers("/actuator/**").hasRole("ADMIN") // Admin only
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
}
```

**Remember:** Secure actuator = Limit exposure + Different port + Spring Security

---

## 111. How do you create custom auto-configuration?

**Simple Answer:**

Custom auto-configuration = Create your own "magic" configuration that activates conditionally.

**Steps:**

**1. Create configuration class:**
```java
@Configuration
@ConditionalOnClass(MyService.class) // Only if MyService exists
@ConditionalOnProperty(name = "myservice.enabled", havingValue = "true")
public class MyServiceAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean // Only if user hasn't created one
    public MyService myService() {
        return new MyService();
    }
}
```

**2. Create spring.factories file:**
```
# src/main/resources/META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.MyServiceAutoConfiguration
```

**3. Add properties:**
```java
@ConfigurationProperties(prefix = "myservice")
public class MyServiceProperties {
    private boolean enabled = true;
    private String url;
    // getters/setters
}
```

**Remember:** Custom auto-config = @Configuration + @Conditional + spring.factories

---

## 112. What is `@Conditional` annotation family?

**Simple Answer:**

@Conditional = "Only create this bean IF condition is true."

**Common Conditionals:**

```java
// Only if class exists on classpath
@ConditionalOnClass(DataSource.class)

// Only if class NOT on classpath
@ConditionalOnMissingClass("com.example.SomeClass")

// Only if bean exists
@ConditionalOnBean(DataSource.class)

// Only if bean does NOT exist
@ConditionalOnMissingBean(DataSource.class)

// Only if property matches
@ConditionalOnProperty(name = "feature.enabled", havingValue = "true")

// Only if web application
@ConditionalOnWebApplication

// Only if specific profile active
@ConditionalOnProfile("dev")
```

**Example:**
```java
@Configuration
public class MyConfig {
    
    @Bean
    @ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(); // Only if cache.enabled=true
    }
}
```

**Remember:** @Conditional = IF statements for bean creation

---

## 113. Explain Spring Security architecture and filter chain.

**Simple Answer:**

Spring Security = Chain of filters that check every request before it reaches your controller.

**Filter Chain Flow:**

```
Request → Filter1 → Filter2 → Filter3 → ... → Controller
          ↓         ↓         ↓
       Security  Authentication Authorization
       Context   Filter        Filter
```

**Key Filters (in order):**

1. **SecurityContextPersistenceFilter** - Loads security context from session
2. **UsernamePasswordAuthenticationFilter** - Handles login (username/password)
3. **BasicAuthenticationFilter** - Handles HTTP Basic auth
4. **JwtAuthenticationFilter** - Handles JWT tokens (custom)
5. **ExceptionTranslationFilter** - Handles security exceptions
6. **FilterSecurityInterceptor** - Final check, makes authorization decision

**Configuration:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin()
            .httpBasic();
        return http.build();
    }
}
```

**Remember:** Security = Filter chain. Each filter checks one thing.

---

## 114. What is authentication vs authorization?

**Simple Answer:**

**Authentication** = Who are you? (Identity verification)
**Authorization** = What can you do? (Permission check)

**Think of it like airport:**
- **Authentication** = Show passport (prove who you are)
- **Authorization** = Check boarding pass (check if you can board this flight)

```java
// Authentication - Login
POST /login
{
    "username": "john",
    "password": "secret123"
}
Response: JWT token (you are John)

// Authorization - Access resource
GET /admin/users
Header: Authorization: Bearer <token>
Response: 403 Forbidden (John is not admin)
```

**In Spring Security:**
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN") // Authorization
            .anyRequest().authenticated() // Authentication required
        )
        .formLogin(); // Authentication method
    return http.build();
}
```

**Remember:** Authentication = WHO you are, Authorization = WHAT you can do

---

## 115. How do you configure `SecurityFilterChain` in Spring Boot 3?

**Simple Answer:**

Spring Boot 3 uses SecurityFilterChain bean (no more WebSecurityConfigurerAdapter).

**Basic Configuration:**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**", "/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // For JWT
            )
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Key Changes in Spring Boot 3:**
- Use lambda DSL (more readable)
- Return SecurityFilterChain bean
- No extends WebSecurityConfigurerAdapter

**Remember:** Spring Boot 3 = SecurityFilterChain bean + Lambda DSL

---

## 116. How do you implement JWT authentication in Spring Boot?

**Simple Answer:**

JWT = Token-based authentication. User logs in once, gets token, uses token for all requests.

**Flow:**
1. User logs in → Server creates JWT → Returns token
2. User sends token in header → Server validates → Allows access

**Implementation:**

**1. Add dependency:**
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
</dependency>
```

**2. JWT Utility:**
```java
@Component
public class JwtUtil {
    private String secret = "mySecretKey";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```

**3. JWT Filter:**
```java
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                // Set authentication in context
            }
        }
        chain.doFilter(request, response);
    }
}
```

**4. Login endpoint:**
```java
@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    // Authenticate user
    String token = jwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(token);
}
```

**Remember:** JWT = Login once, get token, use token everywhere

---

## 117. What is OAuth2 and OpenID Connect (OIDC)?

**Simple Answer:**

**OAuth2** = Authorization framework. "Let app X access my data on app Y without giving password."

**OIDC** = OAuth2 + Identity layer. "Who is the user?"

**Real-world example:**
- "Login with Google" = OIDC (identity)
- "Let app access your Google Drive" = OAuth2 (authorization)

**OAuth2 Key Terms:**

- **Resource Owner** - You (the user)
- **Client** - The app requesting access
- **Authorization Server** - Google, Facebook, etc.
- **Resource Server** - API with your data

**OIDC adds:**
- **ID Token** - JWT with user info (name, email)
- **UserInfo endpoint** - Get user details

```java
// Spring Boot OAuth2 Login
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .oauth2Login() // Enable OAuth2 login
            .and()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

```yaml
# application.yml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: your-client-id
            client-secret: your-secret
            scope: openid, profile, email
```

**Remember:** OAuth2 = Authorization (what you can do), OIDC = Authentication (who you are)

---

## 118. Explain OAuth2 authorization flows (Authorization Code, Client Credentials).

**Simple Answer:**

OAuth2 has different flows for different scenarios.

**1. Authorization Code Flow** (Most secure, for web apps)

```
User → Client App → "Login with Google" 
→ Google Login → User approves 
→ Google sends CODE to Client 
→ Client exchanges CODE for TOKEN 
→ Client uses TOKEN to access API
```

**Use when:** User is involved (web/mobile apps)

**2. Client Credentials Flow** (For server-to-server)

```
Service A → Sends client_id + client_secret 
→ Auth Server → Returns TOKEN 
→ Service A uses TOKEN to call Service B
```

**Use when:** No user involved (microservices, background jobs)

**Code Examples:**

**Authorization Code:**
```java
// Spring handles this automatically with oauth2Login()
@GetMapping("/user")
public String user(@AuthenticationPrincipal OAuth2User principal) {
    return "Hello " + principal.getAttribute("name");
}
```

**Client Credentials:**
```java
@Configuration
public class OAuth2ClientConfig {
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {
        
        OAuth2AuthorizedClientProvider authorizedClientProvider =
            OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();
        
        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
            new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        
        return authorizedClientManager;
    }
}
```

**Remember:** Authorization Code = User involved, Client Credentials = Server-to-server

---

## 119. What is access token vs refresh token? How do you implement refresh token rotation?

**Simple Answer:**

**Access Token** = Short-lived key (15 min). Used to access APIs.
**Refresh Token** = Long-lived key (30 days). Used to get new access token.

**Why both?**
- Access token expires quickly (more secure)
- Refresh token gets new access token without re-login

**Flow:**

```
1. Login → Get access token (15 min) + refresh token (30 days)
2. Use access token for API calls
3. Access token expires → Use refresh token to get new access token
4. Continue using new access token
5. Refresh token expires → User must login again
```

**Refresh Token Rotation** = Each time you use refresh token, you get NEW refresh token (old one becomes invalid).

**Implementation:**

```java
@PostMapping("/refresh")
public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest request) {
    String oldRefreshToken = request.getRefreshToken();
    
    // Validate old refresh token
    if (!jwtUtil.validateRefreshToken(oldRefreshToken)) {
        return ResponseEntity.status(401).build();
    }
    
    String username = jwtUtil.extractUsername(oldRefreshToken);
    
    // Generate NEW tokens
    String newAccessToken = jwtUtil.generateAccessToken(username);
    String newRefreshToken = jwtUtil.generateRefreshToken(username);
    
    // Invalidate old refresh token (rotation)
    refreshTokenRepository.deleteByToken(oldRefreshToken);
    refreshTokenRepository.save(new RefreshToken(newRefreshToken, username));
    
    return ResponseEntity.ok(new TokenResponse(newAccessToken, newRefreshToken));
}
```

**Security benefit of rotation:**
- If refresh token is stolen, it only works once
- Next legitimate refresh fails, alerts you of breach

**Remember:** Access = Short-lived, Refresh = Long-lived. Rotation = New refresh token each time.

---

## 120. How do you implement method-level security using `@PreAuthorize`, `@PostAuthorize`?

**Simple Answer:**

Method-level security = Secure individual methods, not just URLs.

**Enable it:**
```java
@Configuration
@EnableMethodSecurity // Spring Boot 3
public class SecurityConfig {
    // ...
}
```

**Annotations:**

**@PreAuthorize** - Check BEFORE method runs
```java
@Service
public class UserService {
    
    @PreAuthorize("hasRole('ADMIN')") // Only admins
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // User or Admin
    public User getUser(Long id) {
        return userRepository.findById(id);
    }
    
    @PreAuthorize("#username == authentication.principal.username") // Own data only
    public User updateProfile(String username, User user) {
        return userRepository.save(user);
    }
}
```

**@PostAuthorize** - Check AFTER method runs (filter result)
```java
@PostAuthorize("returnObject.owner == authentication.principal.username")
public Document getDocument(Long id) {
    return documentRepository.findById(id); // Only if you own it
}
```

**@PreFilter** - Filter input collection
```java
@PreFilter("filterObject.owner == authentication.principal.username")
public void deleteDocuments(List<Document> documents) {
    // Only deletes documents you own
}
```

**@PostFilter** - Filter output collection
```java
@PostFilter("filterObject.owner == authentication.principal.username")
public List<Document> getAllDocuments() {
    return documentRepository.findAll(); // Returns only your documents
}
```

**SpEL Expressions:**
```java
hasRole('ADMIN')
hasAnyRole('USER', 'ADMIN')
hasAuthority('READ_PRIVILEGE')
#username == authentication.principal.username
returnObject.owner == authentication.name
```

**Remember:** @PreAuthorize = Check before, @PostAuthorize = Check after

---

## 121. What is CORS? How do you configure it in Spring Boot?

**Simple Answer:**

**CORS** = Cross-Origin Resource Sharing. Browser security that blocks requests from different domains.

**Problem:**
```
Frontend: http://localhost:3000 (React)
Backend:  http://localhost:8080 (Spring Boot)
Browser blocks this! (different ports = different origins)
```

**Solution - Enable CORS:**

**1. Global Configuration:**
```java
@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // All endpoints
                    .allowedOrigins("http://localhost:3000") // Frontend URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }
}
```

**2. Controller-level:**
```java
@RestController
@CrossOrigin(origins = "http://localhost:3000") // This controller only
public class UserController {
    // ...
}
```

**3. Method-level:**
```java
@GetMapping("/users")
@CrossOrigin(origins = "http://localhost:3000") // This method only
public List<User> getUsers() {
    return userService.getAllUsers();
}
```

**4. With Spring Security:**
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(Customizer.withDefaults()) // Enable CORS
        .csrf(csrf -> csrf.disable());
    return http.build();
}
```

**Remember:** CORS = Allow frontend on different domain to call your API

---

## 122. What is CSRF protection? When should you disable it?

**Simple Answer:**

**CSRF** = Cross-Site Request Forgery. Attack where malicious site tricks you into making unwanted requests.

**How attack works:**
```
1. You login to bank.com (get session cookie)
2. You visit evil.com (while still logged in)
3. evil.com has: <form action="bank.com/transfer" method="POST">
4. Form auto-submits, transfers your money!
5. Bank thinks it's you (has your cookie)
```

**CSRF Protection:**
Spring generates unique token for each session. Every form must include this token.

```html
<!-- Spring adds CSRF token automatically -->
<form action="/transfer" method="POST">
    <input type="hidden" name="_csrf" value="unique-token-here">
    <input type="text" name="amount">
    <button>Transfer</button>
</form>
```

**When to DISABLE CSRF:**

✅ **Disable for REST APIs with JWT/OAuth2**
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Disable for stateless APIs
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
    return http.build();
}
```

**Why disable for APIs?**
- APIs use tokens (not cookies)
- APIs are stateless (no sessions)
- CSRF only affects cookie-based auth

❌ **Keep ENABLED for:**
- Traditional web apps with sessions
- Cookie-based authentication
- Form-based login

**Selective disable:**
```java
http
    .csrf(csrf -> csrf
        .ignoringRequestMatchers("/api/**") // Disable for /api/*
    );
```

**Remember:** CSRF = Protects cookie-based auth. Disable for stateless JWT APIs.

---

## Summary Table for Quick Revision

| Question | Key Concept | Remember This |
|----------|-------------|---------------|
| 103 | Spring Boot | Spring without the pain |
| 104 | Auto-configuration | Smart defaults based on classpath |
| 105 | @SpringBootApplication | 3-in-1: Configuration + AutoConfig + ComponentScan |
| 106 | Starters | Dependency bundles |
| 107 | Externalize Config | CLI > Env > Properties > Defaults |
| 108 | Profiles | Environment-specific config |
| 109 | Actuator | Built-in monitoring endpoints |
| 110 | Secure Actuator | Limit exposure + Different port + Security |
| 111 | Custom Auto-config | @Configuration + @Conditional + spring.factories |
| 112 | @Conditional | IF statements for beans |
| 113 | Security Architecture | Chain of filters |
| 114 | Auth vs Authz | WHO you are vs WHAT you can do |
| 115 | SecurityFilterChain | Spring Boot 3 = Bean + Lambda DSL |
| 116 | JWT | Login once, token everywhere |
| 117 | OAuth2 vs OIDC | OAuth2 = Authorization, OIDC = Identity |
| 118 | OAuth2 Flows | Authorization Code = User, Client Credentials = Server |
| 119 | Tokens | Access = Short, Refresh = Long, Rotation = Security |
| 120 | Method Security | @PreAuthorize = Before, @PostAuthorize = After |
| 121 | CORS | Allow cross-domain API calls |
| 122 | CSRF | Disable for stateless APIs, keep for sessions |

# ✅ 9) REST API Development & Best Practices - 13 Questions

## 123. What are REST constraints (Client-Server, Stateless, Cacheable, Uniform Interface)?

**Simple Answer:**

REST has 6 main constraints that make APIs scalable and maintainable:

1. **Client-Server**: Separation of concerns - client handles UI, server handles data. They evolve independently.

2. **Stateless**: Each request contains all information needed. Server doesn't store client state between requests. Every request is independent.

3. **Cacheable**: Responses must define if they can be cached or not. This improves performance by reducing server load.

4. **Uniform Interface**: Consistent way to interact with resources using standard HTTP methods (GET, POST, PUT, DELETE) and URIs.

5. **Layered System**: Client doesn't know if connected directly to server or through intermediaries like load balancers or proxies.

6. **Code on Demand (Optional)**: Server can send executable code to client (like JavaScript).

**Remember**: "CSS-ULC" - Client-Server, Stateless, Cacheable, Uniform Interface, Layered, Code-on-demand.

---

## 124. Explain HTTP methods and their idempotency (GET, POST, PUT, PATCH, DELETE).

**Simple Answer:**

**Idempotent** means calling the same operation multiple times produces the same result.

- **GET**: Retrieve data. **Idempotent** ✓ Safe ✓ - Reading 10 times gives same result.
  
- **POST**: Create new resource. **NOT Idempotent** ✗ - Calling 5 times creates 5 resources.

- **PUT**: Replace entire resource. **Idempotent** ✓ - Updating 10 times gives same final state.

- **PATCH**: Partial update. **NOT Idempotent** ✗ (usually) - Depends on implementation.

- **DELETE**: Remove resource. **Idempotent** ✓ - Deleting 10 times, resource still deleted.

**Remember**: "GET, PUT, DELETE are idempotent. POST is not."

---

## 125. What is the difference between PUT and PATCH?

**Simple Answer:**

**PUT**: Replace the **entire** resource. You send the complete object.
```
PUT /users/123
{ "name": "John", "email": "john@example.com", "age": 30 }
```
If you omit a field, it gets removed or set to null.

**PATCH**: **Partial** update. You send only the fields you want to change.
```
PATCH /users/123
{ "age": 31 }
```
Only age changes, name and email remain unchanged.

**Remember**: "PUT = Replace all, PATCH = Update parts"

---

## 126. Explain HTTP status codes (200, 201, 204, 400, 401, 403, 404, 409, 500, 503).

**Simple Answer:**

**2xx - Success:**
- **200 OK**: Request succeeded, data returned (GET, PUT, PATCH)
- **201 Created**: New resource created (POST)
- **204 No Content**: Success but no data to return (DELETE)

**4xx - Client Errors:**
- **400 Bad Request**: Invalid input/validation failed
- **401 Unauthorized**: Authentication required or failed
- **403 Forbidden**: Authenticated but no permission
- **404 Not Found**: Resource doesn't exist
- **409 Conflict**: Resource conflict (duplicate email)

**5xx - Server Errors:**
- **500 Internal Server Error**: Server crashed/unexpected error
- **503 Service Unavailable**: Server overloaded or down

**Remember**: "2xx = Success, 4xx = Your fault, 5xx = Server's fault"

---

## 127. How do you design RESTful URIs? Best practices.

**Simple Answer:**

**Best Practices:**

1. **Use nouns, not verbs**: `/users` not `/getUsers`

2. **Use plural names**: `/users` not `/user`

3. **Use hierarchy for relationships**: `/users/123/orders`

4. **Use hyphens for readability**: `/order-items` not `/orderItems`

5. **Lowercase letters**: `/users` not `/Users`

6. **No trailing slash**: `/users` not `/users/`

7. **Use query params for filtering**: `/users?status=active&role=admin`

8. **Version your API**: `/api/v1/users`

**Examples:**
```
GET    /api/v1/users              - Get all users
GET    /api/v1/users/123          - Get user by ID
POST   /api/v1/users              - Create user
PUT    /api/v1/users/123          - Update user
DELETE /api/v1/users/123          - Delete user
GET    /api/v1/users/123/orders   - Get orders of user 123
```

**Remember**: "Nouns, Plural, Hierarchy, Lowercase, No verbs"

---

## 128. What are API versioning strategies (URI, Header, Query param)?

**Simple Answer:**

**1. URI Versioning** (Most Common):
```
/api/v1/users
/api/v2/users
```
**Pros**: Clear, easy to test, browser-friendly
**Cons**: Multiple URIs for same resource

**2. Header Versioning**:
```
GET /api/users
Header: Accept: application/vnd.myapi.v1+json
```
**Pros**: Clean URIs
**Cons**: Harder to test, not browser-friendly

**3. Query Parameter**:
```
/api/users?version=1
```
**Pros**: Simple
**Cons**: Pollutes query params, easy to forget

**4. Content Negotiation**:
```
Accept: application/vnd.myapi+json;version=1
```

**Recommendation**: Use URI versioning for simplicity.

**Remember**: "URI versioning is most popular and practical"

---

## 129. How do you implement pagination, sorting, and filtering in REST APIs?

**Simple Answer:**

**Pagination** (Limit + Offset or Page + Size):
```
GET /api/users?page=2&size=20
GET /api/users?limit=20&offset=40
```

**Sorting**:
```
GET /api/users?sort=name,asc
GET /api/users?sort=createdDate,desc
```

**Filtering**:
```
GET /api/users?status=active&role=admin
GET /api/users?age_gt=25&city=NYC
```

**Combined Example**:
```
GET /api/users?status=active&sort=name,asc&page=1&size=20
```

**Response Format**:
```json
{
  "content": [...],
  "page": 1,
  "size": 20,
  "totalElements": 150,
  "totalPages": 8
}
```

**Spring Boot Example**:
```java
@GetMapping("/users")
public Page<User> getUsers(
    @RequestParam(required = false) String status,
    Pageable pageable) {
    return userService.findByStatus(status, pageable);
}
```

**Remember**: "page, size, sort, filter - all via query params"

---

## 130. What is HATEOAS? When is it useful?

**Simple Answer:**

**HATEOAS** = Hypermedia As The Engine Of Application State

It means API responses include **links** to related actions, so clients discover what they can do next.

**Example Without HATEOAS**:
```json
{
  "id": 123,
  "name": "John",
  "status": "active"
}
```

**Example With HATEOAS**:
```json
{
  "id": 123,
  "name": "John",
  "status": "active",
  "_links": {
    "self": { "href": "/users/123" },
    "orders": { "href": "/users/123/orders" },
    "deactivate": { "href": "/users/123/deactivate" }
  }
}
```

**When Useful:**
- Public APIs where clients need discoverability
- Complex workflows with multiple steps
- When you want loose coupling

**When NOT Useful:**
- Internal microservices (overhead)
- Mobile apps (extra data)
- Simple CRUD APIs

**Remember**: "HATEOAS = Self-documenting API with links, rarely used in practice"

---

## 131. How do you design consistent error response payloads?

**Simple Answer:**

**Standard Error Response Format**:
```json
{
  "timestamp": "2024-01-15T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/users",
  "errors": [
    {
      "field": "email",
      "message": "Email is required"
    },
    {
      "field": "age",
      "message": "Age must be greater than 0"
    }
  ]
}
```

**Key Fields:**
- **timestamp**: When error occurred
- **status**: HTTP status code
- **error**: Error type
- **message**: Human-readable message
- **path**: API endpoint
- **errors**: List of validation errors (for 400)

**Spring Boot Implementation**:
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
        MethodArgumentNotValidException ex) {
        
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(400);
        error.setMessage("Validation failed");
        
        List<FieldError> fieldErrors = ex.getBindingResult()
            .getFieldErrors().stream()
            .map(e -> new FieldError(e.getField(), e.getDefaultMessage()))
            .collect(Collectors.toList());
        
        error.setErrors(fieldErrors);
        return ResponseEntity.badRequest().body(error);
    }
}
```

**Remember**: "Consistent structure: timestamp, status, message, path, errors"

---

## 132. What is content negotiation (Accept header)?

**Simple Answer:**

**Content Negotiation** = Client tells server what format it wants (JSON, XML, etc.)

**Using Accept Header**:
```
GET /api/users/123
Accept: application/json    → Returns JSON
Accept: application/xml     → Returns XML
Accept: text/csv            → Returns CSV
```

**Spring Boot Example**:
```java
@GetMapping(value = "/users/{id}", 
    produces = {MediaType.APPLICATION_JSON_VALUE, 
                MediaType.APPLICATION_XML_VALUE})
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

**Response Based on Accept Header**:

**JSON Response** (Accept: application/json):
```json
{
  "id": 123,
  "name": "John"
}
```

**XML Response** (Accept: application/xml):
```xml
<user>
  <id>123</id>
  <name>John</name>
</user>
```

**Default Behavior**: If no Accept header, return JSON (most common).

**Remember**: "Accept header tells server what format client wants"

---

## 133. How do you document REST APIs using Swagger/OpenAPI?

**Simple Answer:**

**Swagger/OpenAPI** = Interactive API documentation

**Step 1: Add Dependency** (Spring Boot):
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```

**Step 2: Add Annotations**:
```java
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    
    @Operation(summary = "Get user by ID", 
               description = "Returns a single user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public User getUser(
        @Parameter(description = "User ID") 
        @PathVariable Long id) {
        return userService.findById(id);
    }
    
    @Operation(summary = "Create new user")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}
```

**Step 3: Access Swagger UI**:
```
http://localhost:8080/swagger-ui.html
```

**Benefits:**
- Interactive testing
- Auto-generated documentation
- Client code generation
- API versioning support

**Remember**: "Add springdoc dependency, use @Operation and @Tag, access /swagger-ui.html"

---

## 134. How do you implement rate limiting and throttling?

**Simple Answer:**

**Rate Limiting** = Limit number of requests per time window (e.g., 100 requests/minute)

**Approaches:**

**1. Using Spring Boot + Bucket4j**:
```xml
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
    <artifactId>bucket4j-core</artifactId>
</dependency>
```

```java
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, 
                            Object handler) {
        String key = request.getRemoteAddr(); // IP-based
        Bucket bucket = cache.computeIfAbsent(key, k -> createBucket());
        
        if (bucket.tryConsume(1)) {
            return true; // Allow request
        } else {
            response.setStatus(429); // Too Many Requests
            return false;
        }
    }
    
    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(100, 
            Refill.intervally(100, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
```

**2. Using API Gateway** (AWS, Kong, Nginx):
- Configure rate limits at gateway level
- No code changes needed

**3. Using Redis**:
```java
public boolean isAllowed(String userId) {
    String key = "rate_limit:" + userId;
    Long count = redisTemplate.opsForValue().increment(key);
    
    if (count == 1) {
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }
    
    return count <= 100; // 100 requests per minute
}
```

**Response Headers**:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 45
X-RateLimit-Reset: 1642345678
```

**Remember**: "Use Bucket4j or Redis, return 429 when limit exceeded"

---

## 135. How do you ensure backward compatibility in APIs?

**Simple Answer:**

**Strategies to Maintain Backward Compatibility:**

**1. Never Remove Fields** - Only add new fields:
```json
// Version 1
{ "name": "John" }

// Version 2 - Add field (OK)
{ "name": "John", "email": "john@example.com" }

// Version 2 - Remove field (BREAKS compatibility)
{ "email": "john@example.com" }  ❌
```

**2. Use Optional Fields**:
```java
public class UserDTO {
    private String name;        // Required
    private String email;       // Optional (new field)
    private String phone;       // Optional (new field)
}
```

**3. Deprecate, Don't Delete**:
```java
@Deprecated
@GetMapping("/old-endpoint")
public Response oldMethod() {
    // Keep working, but mark deprecated
}

@GetMapping("/new-endpoint")
public Response newMethod() {
    // New implementation
}
```

**4. Version Your API**:
```
/api/v1/users  - Old clients
/api/v2/users  - New clients
```

**5. Use Default Values**:
```java
public class User {
    private String status = "active"; // Default value
}
```

**6. Support Multiple Formats**:
```java
// Accept both old and new request formats
public User createUser(@RequestBody UserRequest request) {
    if (request.getNewField() == null) {
        // Handle old format
    }
}
```

**7. Communicate Changes**:
- Changelog
- Deprecation notices
- Migration guides
- Sunset headers

**Testing**:
```java
@Test
public void testBackwardCompatibility() {
    // Test with old request format
    String oldRequest = "{\"name\":\"John\"}";
    // Should still work
}
```

**Remember**: "Add, don't remove. Deprecate, don't delete. Version when breaking changes needed."

---

## Summary - Quick Reference

| Question | Key Concept | Remember This |
|----------|-------------|---------------|
| 123 | REST Constraints | CSS-ULC: Client-Server, Stateless, Cacheable, Uniform, Layered, Code-on-demand |
| 124 | HTTP Methods & Idempotency | GET, PUT, DELETE = Idempotent; POST = Not |
| 125 | PUT vs PATCH | PUT = Replace all, PATCH = Update parts |
| 126 | Status Codes | 2xx = Success, 4xx = Client error, 5xx = Server error |
| 127 | RESTful URIs | Nouns, Plural, Hierarchy, Lowercase, No verbs |
| 128 | API Versioning | URI versioning most popular: /api/v1/users |
| 129 | Pagination/Sorting | Query params: page, size, sort, filter |
| 130 | HATEOAS | Self-documenting with links, rarely used |
| 131 | Error Responses | timestamp, status, message, path, errors |
| 132 | Content Negotiation | Accept header tells format (JSON/XML) |
| 133 | API Documentation | Swagger: springdoc + @Operation + /swagger-ui |
| 134 | Rate Limiting | Bucket4j or Redis, return 429 when exceeded |
| 135 | Backward Compatibility | Add don't remove, deprecate don't delete |

# ✅ 10) Microservices Basics and Architecture - 17 Questions

## 136. Monolithic vs microservices architecture - pros and cons.

**Answer:**

**Monolithic Architecture:**
- **Pros**: Simple to develop, test, and deploy initially. Single codebase, easier debugging, no network latency between components, strong consistency with single database.
- **Cons**: Tight coupling, difficult to scale specific features, long deployment times, technology lock-in, single point of failure, hard to maintain as it grows.

**Microservices Architecture:**
- **Pros**: Independent deployment, technology flexibility, better scalability, fault isolation, team autonomy, easier to understand small services.
- **Cons**: Distributed system complexity, network latency, data consistency challenges, harder to debug, operational overhead, requires DevOps maturity.

**Remember**: Start monolithic, move to microservices when you have clear bounded contexts and scaling needs.

---

## 137. When should you NOT use microservices?

**Answer:**

Don't use microservices when:
1. **Small team** (less than 10 developers) - overhead outweighs benefits
2. **Simple application** - CRUD apps don't need distributed complexity
3. **Startup/MVP phase** - need speed, not scalability
4. **Lack of DevOps maturity** - no CI/CD, monitoring, or container orchestration
5. **Unclear domain boundaries** - don't know how to split services properly
6. **Strong consistency required** - distributed transactions are complex
7. **Limited resources** - infrastructure costs multiply with services

**Remember**: Microservices solve organizational and scaling problems, not technical ones. If you don't have those problems, don't use microservices.

---

## 138. How do you decompose a monolith into microservices?

**Answer:**

Follow this step-by-step approach:

1. **Identify Bounded Contexts** - Use Domain-Driven Design to find natural business boundaries (e.g., Order, Payment, Inventory, User)
2. **Start with Strangler Pattern** - Don't rewrite everything. Extract one service at a time, route traffic gradually
3. **Extract by Business Capability** - Not by technical layers. Each service should represent a business function
4. **Database Decomposition** - Separate databases per service, use APIs for data access
5. **Handle Dependencies** - Identify and minimize inter-service dependencies
6. **API Gateway** - Create a single entry point for clients
7. **Shared Libraries** - Extract common code to libraries, but avoid tight coupling

**Order of Extraction**: Start with least dependent, most isolated modules first.

**Remember**: It's a journey, not a big-bang migration. Take 6-12 months for large systems.

---

## 139. What is Domain-Driven Design (DDD) and bounded context?

**Answer:**

**Domain-Driven Design (DDD)**: An approach to software design that focuses on modeling software based on the business domain.

**Key Concepts:**
- **Domain**: The business problem you're solving (e.g., e-commerce)
- **Bounded Context**: A logical boundary where a specific domain model applies. Each microservice should represent one bounded context
- **Ubiquitous Language**: Common vocabulary between developers and business experts
- **Entities**: Objects with unique identity (e.g., User, Order)
- **Value Objects**: Objects without identity (e.g., Address, Money)
- **Aggregates**: Cluster of entities treated as a single unit (e.g., Order with OrderItems)

**Example**: In e-commerce:
- Order Context: Order, OrderItem, OrderStatus
- Payment Context: Payment, Transaction, Invoice
- Inventory Context: Product, Stock, Warehouse

**Remember**: Bounded contexts define microservice boundaries. One bounded context = one microservice.

---

## 140. What is service discovery? Explain Eureka, Consul.

**Answer:**

**Service Discovery**: Automatic detection of service instances in a dynamic environment where IPs and ports change frequently.

**Two Types:**
1. **Client-Side Discovery**: Client queries registry and chooses instance (Eureka)
2. **Server-Side Discovery**: Load balancer queries registry (Consul with load balancer)

**Eureka (Netflix OSS):**
- Client-side discovery
- Services register themselves on startup
- Clients fetch registry and cache it locally
- Heartbeat mechanism (30 seconds default)
- Self-preservation mode prevents mass deregistration
- Works well with Spring Cloud

**Consul (HashiCorp):**
- Both client and server-side discovery
- Built-in health checks
- Key-value store for configuration
- Multi-datacenter support
- DNS and HTTP interfaces

**Remember**: Eureka = Spring ecosystem, Consul = broader ecosystem with more features.

---

## 141. Explain API Gateway pattern (Spring Cloud Gateway, Zuul).

**Answer:**

**API Gateway**: Single entry point for all client requests. Routes requests to appropriate microservices.

**Key Responsibilities:**
1. **Routing** - Direct requests to correct service
2. **Authentication/Authorization** - Centralized security
3. **Rate Limiting** - Prevent abuse
4. **Load Balancing** - Distribute traffic
5. **Request/Response Transformation** - Modify headers, body
6. **Caching** - Reduce backend calls
7. **Logging/Monitoring** - Centralized observability

**Spring Cloud Gateway (Modern):**
- Built on Spring WebFlux (reactive, non-blocking)
- Better performance and scalability
- Predicates and Filters for routing logic
- Supports WebSocket

**Zuul (Legacy):**
- Built on Servlet API (blocking)
- Zuul 1 is synchronous, Zuul 2 is async
- Netflix stopped active development

**Remember**: Use Spring Cloud Gateway for new projects. It's reactive and actively maintained.

---

## 142. What is Circuit Breaker pattern? How does Resilience4j work?

**Answer:**

**Circuit Breaker Pattern**: Prevents cascading failures by stopping calls to failing services, giving them time to recover.

**How It Works:**
When a service fails repeatedly, the circuit breaker "opens" and immediately returns an error without calling the service. After a timeout, it tries again (half-open state).

**Resilience4j:**
A lightweight fault tolerance library for Java 8+.

**Key Features:**
1. **Circuit Breaker** - Prevents cascading failures
2. **Rate Limiter** - Limits requests per second
3. **Retry** - Automatic retry with exponential backoff
4. **Bulkhead** - Limits concurrent calls
5. **Time Limiter** - Timeout for operations

**Configuration Example:**
```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
public Payment processPayment(Order order) {
    return paymentClient.pay(order);
}

public Payment paymentFallback(Order order, Exception e) {
    return Payment.failed("Service unavailable");
}
```

**Remember**: Circuit breaker protects your system from cascading failures. It's like an electrical circuit breaker for software.

---

## 143. Explain circuit breaker states (Closed, Open, Half-Open).

**Answer:**

**Three States:**

**1. CLOSED (Normal Operation):**
- All requests pass through to the service
- Monitors failure rate
- If failures exceed threshold → transitions to OPEN
- Example: 50% failure rate in last 10 calls

**2. OPEN (Failure State):**
- All requests immediately fail without calling service
- Returns fallback response
- After wait duration (e.g., 60 seconds) → transitions to HALF-OPEN
- Gives failing service time to recover

**3. HALF-OPEN (Testing State):**
- Allows limited number of test requests (e.g., 3 requests)
- If successful → transitions back to CLOSED
- If failed → transitions back to OPEN
- Determines if service has recovered

**State Transition Flow:**
```
CLOSED → (failures exceed threshold) → OPEN
OPEN → (wait duration expires) → HALF-OPEN
HALF-OPEN → (test calls succeed) → CLOSED
HALF-OPEN → (test calls fail) → OPEN
```

**Remember**: Closed = working, Open = broken, Half-Open = testing recovery.

---

## 144. What is Saga pattern? Choreography vs Orchestration.

**Answer:**

**Saga Pattern**: Manages distributed transactions across microservices using a sequence of local transactions. Each transaction updates one service and publishes an event.

**Two Implementation Styles:**

**1. Choreography (Event-Driven):**
- No central coordinator
- Each service listens to events and decides what to do
- Services publish events after completing their transaction

**Example - Order Processing:**
```
Order Service → creates order → publishes OrderCreated event
Payment Service → listens → processes payment → publishes PaymentCompleted
Inventory Service → listens → reserves stock → publishes StockReserved
Shipping Service → listens → ships order → publishes OrderShipped
```

**Pros**: Loose coupling, no single point of failure
**Cons**: Hard to track, complex to debug, cyclic dependencies risk

**2. Orchestration (Centralized):**
- Central orchestrator controls the workflow
- Orchestrator tells each service what to do

**Example:**
```
Order Orchestrator:
1. Call Payment Service
2. If success → Call Inventory Service
3. If success → Call Shipping Service
4. If any fails → Trigger compensating transactions
```

**Pros**: Easy to understand, centralized monitoring, clear workflow
**Cons**: Single point of failure, orchestrator can become complex

**Remember**: Choreography = distributed control, Orchestration = centralized control. Use orchestration for complex workflows.

---

## 145. How do you handle distributed transactions?

**Answer:**

Distributed transactions are challenging because you can't use traditional ACID transactions across services.

**Solutions:**

**1. Saga Pattern (Recommended):**
- Break transaction into local transactions
- Use compensating transactions for rollback
- Example: If payment succeeds but shipping fails, refund payment

**2. Two-Phase Commit (2PC) - Avoid:**
- Coordinator asks all services to prepare
- If all agree, coordinator commits
- Problem: Blocking, single point of failure, not suitable for microservices

**3. Event Sourcing:**
- Store events instead of current state
- Replay events to rebuild state
- Natural audit trail

**4. Try-Confirm/Cancel (TCC):**
- Try: Reserve resources
- Confirm: Commit the transaction
- Cancel: Release resources if failed

**5. Eventual Consistency:**
- Accept that data won't be immediately consistent
- Use asynchronous messaging
- Design for idempotency

**Best Practices:**
- Design for failure - assume services will fail
- Implement idempotency - same request multiple times = same result
- Use correlation IDs for tracking
- Monitor and alert on saga failures

**Remember**: Avoid distributed transactions if possible. If needed, use Saga pattern with compensating transactions.

---

## 146. How do you handle inter-service communication (REST vs messaging)?

**Answer:**

**Two Main Approaches:**

**1. Synchronous (REST/HTTP):**
- Direct request-response
- Client waits for response
- Simple to implement and debug

**When to Use:**
- Real-time data needed
- Simple request-response flows
- Low latency requirements
- Example: Get user profile, validate payment

**Pros**: Simple, immediate response, easy debugging
**Cons**: Tight coupling, cascading failures, service must be available

**2. Asynchronous (Messaging - Kafka/RabbitMQ):**
- Fire and forget
- Client doesn't wait
- Message broker in between

**When to Use:**
- Long-running operations
- Event-driven workflows
- High throughput needed
- Decoupling services
- Example: Order processing, email notifications

**Pros**: Loose coupling, better scalability, fault tolerance
**Cons**: Complexity, eventual consistency, harder debugging

**Hybrid Approach (Best Practice):**
- Use REST for queries (GET operations)
- Use messaging for commands (POST/PUT operations)
- Use REST for synchronous workflows
- Use messaging for asynchronous workflows

**Remember**: REST = synchronous & simple, Messaging = asynchronous & scalable. Choose based on use case.

---

## 147. When should you use Kafka vs RabbitMQ?

**Answer:**

**Apache Kafka:**
**Best For**: High-throughput event streaming, log aggregation, real-time analytics

**Characteristics:**
- Distributed commit log
- Messages persist on disk (retention period)
- Pull-based consumption
- Horizontal scalability
- Ordered within partition
- Multiple consumers can read same message

**Use Cases:**
- Event sourcing
- Log aggregation
- Real-time analytics
- Activity tracking
- Metrics collection
- Stream processing

**RabbitMQ:**
**Best For**: Traditional message queuing, task distribution, request-reply patterns

**Characteristics:**
- Message broker with queues
- Push-based delivery
- Messages deleted after consumption
- Complex routing (exchanges, bindings)
- Priority queues
- Message acknowledgment

**Use Cases:**
- Task queues (background jobs)
- Request-reply patterns
- RPC communication
- Complex routing logic
- Guaranteed delivery needed

**Quick Decision Matrix:**
- **High throughput (millions/sec)** → Kafka
- **Message replay needed** → Kafka
- **Event streaming** → Kafka
- **Complex routing** → RabbitMQ
- **Priority queues** → RabbitMQ
- **Traditional queuing** → RabbitMQ

**Remember**: Kafka = event streaming & high throughput, RabbitMQ = traditional messaging & complex routing.

---

## 148. What is event-driven architecture and event sourcing?

**Answer:**

**Event-Driven Architecture (EDA):**
Architecture where services communicate through events. Services publish events when state changes, other services react to those events.

**Key Concepts:**
- **Event**: Something that happened (OrderCreated, PaymentProcessed)
- **Event Producer**: Service that publishes events
- **Event Consumer**: Service that listens and reacts to events
- **Event Bus**: Infrastructure for event distribution (Kafka, RabbitMQ)

**Benefits:**
- Loose coupling
- Scalability
- Real-time processing
- Flexibility to add new consumers

**Event Sourcing:**
A pattern where you store all changes as a sequence of events instead of storing current state.

**Traditional Approach:**
```
User table: id=1, name="John", balance=100
(Update overwrites previous state)
```

**Event Sourcing Approach:**
```
Event 1: AccountCreated(id=1, name="John", balance=0)
Event 2: MoneyDeposited(id=1, amount=100)
Event 3: MoneyWithdrawn(id=1, amount=20)
Current state = replay all events = balance=80
```

**Benefits:**
- Complete audit trail
- Time travel (rebuild state at any point)
- Event replay for debugging
- Natural fit for event-driven systems

**Challenges:**
- Storage grows over time
- Query complexity (need to replay events)
- Schema evolution
- Eventual consistency

**Remember**: EDA = services communicate via events, Event Sourcing = store events instead of state.

---

## 149. How do you implement distributed tracing (Zipkin, Jaeger)?

**Answer:**

**Distributed Tracing**: Tracks requests as they flow through multiple microservices, helping debug latency and failures.

**Key Concepts:**
- **Trace**: End-to-end journey of a request across services
- **Span**: Single operation within a trace (one service call)
- **Trace ID**: Unique ID for entire request journey
- **Span ID**: Unique ID for each operation
- **Parent Span ID**: Links spans together

**How It Works:**
1. API Gateway receives request → generates Trace ID
2. Each service creates a Span with Span ID
3. Trace ID propagated through HTTP headers
4. All spans sent to tracing system
5. Tracing system visualizes the flow

**Zipkin (Twitter):**
- Lightweight, easy to set up
- Good for Spring Boot (Spring Cloud Sleuth integration)
- Web UI for visualization
- Supports multiple storage backends

**Jaeger (Uber):**
- More features than Zipkin
- Better for large-scale systems
- OpenTracing compatible
- Advanced querying capabilities
- Better performance at scale

**Spring Boot Implementation:**
```java
// Add dependencies
spring-cloud-starter-sleuth
spring-cloud-sleuth-zipkin

// Configuration
spring.zipkin.base-url=http://localhost:9411
spring.sleuth.sampler.probability=1.0  // 100% sampling
```

**Remember**: Distributed tracing shows request flow across services. Zipkin = simple, Jaeger = enterprise-grade.

---

## 150. What is correlation ID? How do you propagate it?

**Answer:**

**Correlation ID**: A unique identifier that tracks a request across multiple services and log entries. Essential for debugging distributed systems.

**Why It's Important:**
- Links all logs for a single user request
- Helps debug issues across services
- Tracks request flow
- Measures end-to-end latency

**How to Propagate:**

**1. HTTP Headers:**
```java
// Generate at API Gateway
String correlationId = UUID.randomUUID().toString();
request.addHeader("X-Correlation-ID", correlationId);

// Extract in each service
String correlationId = request.getHeader("X-Correlation-ID");
MDC.put("correlationId", correlationId);
```

**2. Spring Boot Implementation:**
```java
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) {
        String correlationId = request.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put("correlationId", correlationId);
        response.addHeader("X-Correlation-ID", correlationId);
        filterChain.doFilter(request, response);
        MDC.clear();
    }
}
```

**3. Logging Configuration:**
```xml
<pattern>%d{yyyy-MM-dd HH:mm:ss} [%X{correlationId}] %-5level %logger{36} - %msg%n</pattern>
```

**4. RestTemplate/WebClient:**
```java
restTemplate.getInterceptors().add((request, body, execution) -> {
    request.getHeaders().add("X-Correlation-ID", MDC.get("correlationId"));
    return execution.execute(request, body);
});
```

**Remember**: Correlation ID = request tracking across services. Always propagate it through HTTP headers and log it.

---

## 151. What is Spring Cloud Config Server for externalized configuration?

**Answer:**

**Spring Cloud Config Server**: Centralized configuration management for distributed systems. Stores configuration in Git repository and serves it to microservices.

**Why Use It:**
- **Centralized**: All configs in one place
- **Environment-specific**: Dev, QA, Prod configs
- **Version controlled**: Git tracks changes
- **Dynamic refresh**: Update config without restart
- **Security**: Encrypt sensitive data

**Architecture:**
```
Git Repository (configs) → Config Server → Microservices
```

**Setup:**

**1. Config Server:**
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**application.yml:**
```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/myorg/config-repo
          default-label: main
```

**2. Git Repository Structure:**
```
config-repo/
  application.yml          # Common to all services
  order-service.yml        # Order service specific
  order-service-dev.yml    # Order service dev environment
  order-service-prod.yml   # Order service prod environment
```

**3. Client Configuration:**
```yaml
spring:
  application:
    name: order-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: true
  profiles:
    active: dev
```

**4. Dynamic Refresh:**
```java
@RefreshScope
@RestController
public class OrderController {
    @Value("${order.max-items}")
    private int maxItems;
}
```

**Refresh endpoint:** POST `/actuator/refresh`

**Remember**: Config Server = centralized configuration from Git. Use @RefreshScope for dynamic updates.

---

## 152. What is database per service pattern? Challenges?

**Answer:**

**Database Per Service Pattern**: Each microservice has its own private database. No direct database access between services.

**Why Use It:**
- **Loose coupling**: Services independent
- **Technology flexibility**: Each service can use different DB (SQL, NoSQL)
- **Scalability**: Scale databases independently
- **Fault isolation**: One DB failure doesn't affect others
- **Team autonomy**: Teams own their data

**Challenges:**

**1. Data Consistency:**
- No ACID transactions across services
- Solution: Saga pattern, eventual consistency

**2. Joins Across Services:**
- Can't do SQL joins across databases
- Solution: API composition, CQRS, data replication

**3. Data Duplication:**
- Same data in multiple databases
- Solution: Event-driven synchronization, accept duplication

**4. Distributed Queries:**
- Complex queries spanning multiple services
- Solution: API Gateway aggregation, CQRS with read models

**5. Testing Complexity:**
- Need to set up multiple databases
- Solution: Testcontainers, in-memory databases

**6. Migration Complexity:**
- Schema changes affect only one service
- But data migration is harder
- Solution: Backward compatible changes, versioning

**Patterns to Handle Challenges:**

**API Composition:**
```java
// Order Service calls multiple services
OrderDetails getOrderDetails(orderId) {
    Order order = orderService.getOrder(orderId);
    User user = userService.getUser(order.getUserId());
    Product product = productService.getProduct(order.getProductId());
    return new OrderDetails(order, user, product);
}
```

**CQRS (Command Query Responsibility Segregation):**
- Separate read and write models
- Maintain denormalized read database
- Sync via events

**Event-Driven Data Sync:**
```
Order Service → publishes OrderCreated event
Reporting Service → listens → updates its read database
```

**Remember**: Database per service = independence but complexity. Accept eventual consistency and use Saga pattern for transactions.

# ✅ 11) JDBC, Database Design & Optimization (SQL + JPA/Hibernate) - 25 Questions

## 153. What is JDBC? Explain JDBC architecture and driver types (Type 1-4).

**Answer:**

JDBC (Java Database Connectivity) is a Java API that enables Java applications to interact with databases.

**JDBC Architecture:**
- Application Layer → JDBC API → JDBC Driver Manager → JDBC Driver → Database

**Driver Types:**

1. **Type 1 - JDBC-ODBC Bridge Driver**
   - Converts JDBC calls to ODBC calls
   - Requires ODBC driver installation
   - Slow performance, platform-dependent
   - Deprecated in Java 8

2. **Type 2 - Native-API Driver**
   - Converts JDBC calls to database-specific native calls
   - Requires native database libraries
   - Better performance than Type 1
   - Platform-dependent

3. **Type 3 - Network Protocol Driver**
   - Pure Java driver
   - Uses middleware server to convert JDBC calls
   - Database-independent
   - Good for web applications

4. **Type 4 - Thin Driver (Pure Java)**
   - Direct communication with database
   - Pure Java, platform-independent
   - Best performance
   - Most commonly used (e.g., MySQL Connector, PostgreSQL JDBC)

**Remember:** Type 4 is the industry standard - pure Java, best performance.

---

## 154. What is the difference between Statement, PreparedStatement, CallableStatement?

**Answer:**

| Feature | Statement | PreparedStatement | CallableStatement |
|---------|-----------|-------------------|-------------------|
| **Purpose** | Execute simple SQL queries | Execute parameterized queries | Execute stored procedures |
| **SQL Compilation** | Compiled every time | Pre-compiled once | Pre-compiled |
| **Performance** | Slow | Fast (reusable) | Fast |
| **SQL Injection** | Vulnerable | Protected | Protected |
| **Parameters** | No parameters | Input parameters (?) | IN/OUT parameters |
| **Use Case** | Static queries | Dynamic queries | Stored procedures |

**Code Example:**
```java
// Statement - Avoid for dynamic data
Statement stmt = conn.createStatement();
stmt.executeQuery("SELECT * FROM users WHERE id = " + userId); // SQL Injection risk!

// PreparedStatement - Preferred
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId);
pstmt.executeQuery();

// CallableStatement - For stored procedures
CallableStatement cstmt = conn.prepareCall("{call getUserById(?)}");
cstmt.setInt(1, userId);
cstmt.execute();
```

**Remember:** Always use PreparedStatement for queries with user input to prevent SQL injection.

---

## 155. How do you prevent SQL injection in JDBC?

**Answer:**

**SQL Injection** occurs when malicious SQL code is inserted into application queries.

**Prevention Techniques:**

1. **Use PreparedStatement (Primary Defense)**
```java
// BAD - SQL Injection vulnerable
String query = "SELECT * FROM users WHERE username = '" + username + "'";
// If username = "admin' OR '1'='1", it bypasses authentication!

// GOOD - Safe from SQL Injection
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
pstmt.setString(1, username); // Automatically escapes special characters
```

2. **Input Validation**
```java
// Validate and sanitize input
if (!username.matches("[a-zA-Z0-9]+")) {
    throw new IllegalArgumentException("Invalid username");
}
```

3. **Use Stored Procedures**
```java
CallableStatement cstmt = conn.prepareCall("{call authenticateUser(?, ?)}");
cstmt.setString(1, username);
cstmt.setString(2, password);
```

4. **Least Privilege Principle**
   - Database user should have minimal permissions
   - Don't use admin accounts for application connections

5. **Use ORM Frameworks**
   - JPA/Hibernate automatically use parameterized queries

**Remember:** PreparedStatement is your first line of defense against SQL injection.

---

## 156. What is connection pooling? Why is it important?

**Answer:**

**Connection Pooling** is a technique where a pool of database connections is created and reused instead of creating new connections for each request.

**Why It's Important:**

1. **Performance:** Creating a new connection is expensive (100-1000ms)
2. **Resource Management:** Limits database connections
3. **Scalability:** Handles high concurrent requests efficiently
4. **Reduced Latency:** Reuses existing connections

**Without Connection Pool:**
```java
// Every request creates new connection - SLOW!
Connection conn = DriverManager.getConnection(url, user, password); // 500ms
// Execute query
conn.close(); // Connection destroyed
```

**With Connection Pool:**
```java
// Get connection from pool - FAST!
Connection conn = dataSource.getConnection(); // 1-5ms (reused)
// Execute query
conn.close(); // Returns to pool, not destroyed
```

**Popular Connection Pools:**
- **HikariCP** (fastest, Spring Boot default)
- Apache DBCP
- C3P0
- Tomcat JDBC Pool

**HikariCP Configuration:**
```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
```

**Remember:** Connection pooling reduces connection creation overhead from 500ms to 1-5ms.

---

## 157. What is normalization? Explain 1NF, 2NF, 3NF, BCNF.

**Answer:**

**Normalization** is the process of organizing database tables to reduce redundancy and improve data integrity.

**1NF (First Normal Form):**
- Each column contains atomic (indivisible) values
- No repeating groups
- Each row is unique (has primary key)

```sql
-- NOT 1NF (phone numbers in one column)
| ID | Name  | Phones           |
|----|-------|------------------|
| 1  | John  | 123-456, 789-012 |

-- 1NF (atomic values)
| ID | Name  | Phone    |
|----|-------|----------|
| 1  | John  | 123-456  |
| 1  | John  | 789-012  |
```

**2NF (Second Normal Form):**
- Must be in 1NF
- No partial dependency (all non-key columns depend on entire primary key)

```sql
-- NOT 2NF (StudentName depends only on StudentID, not on both)
| StudentID | CourseID | StudentName | CourseName |
|-----------|----------|-------------|------------|
| 1         | 101      | John        | Math       |

-- 2NF (split into two tables)
Students: | StudentID | StudentName |
Courses:  | CourseID  | CourseName  |
Enrollment: | StudentID | CourseID |
```

**3NF (Third Normal Form):**
- Must be in 2NF
- No transitive dependency (non-key columns don't depend on other non-key columns)

```sql
-- NOT 3NF (City depends on ZipCode, not on StudentID)
| StudentID | ZipCode | City      |
|-----------|---------|-----------|
| 1         | 12345   | New York  |

-- 3NF (split tables)
Students: | StudentID | ZipCode |
ZipCodes: | ZipCode | City |
```

**BCNF (Boyce-Codd Normal Form):**
- Stricter version of 3NF
- Every determinant must be a candidate key
- Handles edge cases where 3NF isn't enough

**Remember:** 1NF = Atomic values, 2NF = No partial dependency, 3NF = No transitive dependency.

---

## 158. When should you denormalize a database?

**Answer:**

**Denormalization** is intentionally introducing redundancy to improve read performance.

**When to Denormalize:**

1. **Read-Heavy Applications**
   - More reads than writes (90% reads, 10% writes)
   - Example: Reporting systems, analytics dashboards

2. **Complex Joins Are Slow**
   - Queries joining 5+ tables
   - Performance bottleneck identified

3. **Aggregated Data Needed Frequently**
   - Calculating totals, counts repeatedly
   - Example: Order total, product rating average

4. **Historical Data/Snapshots**
   - Need to preserve data as it was at a point in time
   - Example: Invoice with product price at purchase time

**Example:**
```sql
-- Normalized (requires JOIN)
Orders: | OrderID | CustomerID | OrderDate |
OrderItems: | OrderID | ProductID | Quantity | Price |

-- Query requires JOIN
SELECT o.OrderID, SUM(oi.Quantity * oi.Price) as Total
FROM Orders o JOIN OrderItems oi ON o.OrderID = oi.OrderID
GROUP BY o.OrderID;

-- Denormalized (add TotalAmount column)
Orders: | OrderID | CustomerID | OrderDate | TotalAmount |
-- No JOIN needed, faster reads, but must update TotalAmount on every order change
```

**Trade-offs:**
- ✅ Faster reads
- ❌ Slower writes (must update multiple places)
- ❌ Data inconsistency risk
- ❌ More storage space

**Remember:** Denormalize only after identifying performance bottlenecks through profiling.

---

## 159. Explain ACID properties in detail.

**Answer:**

**ACID** ensures reliable database transactions.

**A - Atomicity (All or Nothing)**
- Transaction is treated as a single unit
- Either all operations succeed or all fail
- No partial updates

```java
// Example: Bank transfer
BEGIN TRANSACTION
  UPDATE accounts SET balance = balance - 100 WHERE id = 1; // Debit
  UPDATE accounts SET balance = balance + 100 WHERE id = 2; // Credit
COMMIT; // Both succeed or both rollback
```

**C - Consistency**
- Database moves from one valid state to another
- All constraints, triggers, cascades are enforced
- Data integrity is maintained

```sql
-- Example: Balance cannot be negative (constraint)
ALTER TABLE accounts ADD CONSTRAINT check_balance CHECK (balance >= 0);
-- Transaction will fail if it violates this constraint
```

**I - Isolation**
- Concurrent transactions don't interfere with each other
- Each transaction executes as if it's the only one
- Controlled by isolation levels

```java
// Transaction 1 and Transaction 2 run concurrently
// Isolation ensures they don't see each other's uncommitted changes
```

**D - Durability**
- Once committed, changes are permanent
- Survives system crashes, power failures
- Data is written to non-volatile storage

```java
COMMIT; // After this, data is guaranteed to be saved even if system crashes
```

**Real-World Example:**
```java
@Transactional // Spring ensures ACID
public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
    Account from = accountRepo.findById(fromId);
    Account to = accountRepo.findById(toId);
    
    from.setBalance(from.getBalance().subtract(amount)); // Atomicity
    to.setBalance(to.getBalance().add(amount));          // Atomicity
    
    accountRepo.save(from); // Consistency (constraints checked)
    accountRepo.save(to);   // Isolation (other transactions wait)
    // Durability (committed to disk)
}
```

**Remember:** ACID = Atomicity (all or nothing), Consistency (valid state), Isolation (no interference), Durability (permanent).

---

## 160. What are database indexes? How do they improve performance?

**Answer:**

**Index** is a data structure that improves the speed of data retrieval operations on a database table.

**How Indexes Work:**
- Like a book's index - helps find information quickly without scanning every page
- Creates a separate structure with pointers to actual data
- Typically uses B-Tree or Hash data structures

**Performance Improvement:**

**Without Index:**
```sql
SELECT * FROM users WHERE email = 'john@example.com';
-- Full table scan: O(n) - checks every row
-- 1 million rows = 1 million comparisons
```

**With Index:**
```sql
CREATE INDEX idx_email ON users(email);
SELECT * FROM users WHERE email = 'john@example.com';
-- Index lookup: O(log n) - uses B-Tree
-- 1 million rows = ~20 comparisons
```

**Types of Indexes:**

1. **Single Column Index**
```sql
CREATE INDEX idx_lastname ON employees(last_name);
```

2. **Composite Index (Multiple Columns)**
```sql
CREATE INDEX idx_name ON employees(last_name, first_name);
-- Useful for queries filtering by both columns
```

3. **Unique Index**
```sql
CREATE UNIQUE INDEX idx_email ON users(email);
-- Ensures no duplicate emails
```

**When to Use Indexes:**
- ✅ Columns used in WHERE clauses
- ✅ Columns used in JOIN conditions
- ✅ Columns used in ORDER BY
- ✅ Foreign key columns

**When NOT to Use Indexes:**
- ❌ Small tables (< 1000 rows)
- ❌ Columns with frequent INSERT/UPDATE/DELETE
- ❌ Columns with low cardinality (few unique values, e.g., gender)

**Trade-offs:**
- ✅ Faster SELECT queries
- ❌ Slower INSERT/UPDATE/DELETE (index must be updated)
- ❌ Additional storage space

**Remember:** Indexes speed up reads but slow down writes. Use on frequently queried columns.

---

## 161. Clustered vs non-clustered index - difference and use cases.

**Answer:**

| Feature | Clustered Index | Non-Clustered Index |
|---------|----------------|---------------------|
| **Storage** | Data rows stored in index order | Separate structure with pointers |
| **Count per Table** | Only ONE per table | Multiple allowed |
| **Speed** | Faster (direct data access) | Slightly slower (extra lookup) |
| **Primary Key** | Usually the clustered index | Can be on any column |
| **Data Organization** | Physical order of data | Logical order only |

**Clustered Index:**
- Data is physically sorted by index key
- Like a phone book sorted by last name
- Typically created on primary key

```sql
-- Clustered index (usually automatic on primary key)
CREATE TABLE employees (
    id INT PRIMARY KEY, -- Clustered index
    name VARCHAR(100),
    salary DECIMAL
);
-- Data rows are physically stored in order of 'id'
```

**Non-Clustered Index:**
- Separate structure pointing to data rows
- Like a book's index pointing to page numbers
- Can have multiple per table

```sql
-- Non-clustered indexes
CREATE INDEX idx_name ON employees(name);
CREATE INDEX idx_salary ON employees(salary);
-- Separate structures with pointers to actual data
```

**Visual Representation:**

**Clustered Index (Phone Book):**
```
| ID (Clustered) | Name    | Salary |
|----------------|---------|--------|
| 1              | Alice   | 50000  | ← Data stored here
| 2              | Bob     | 60000  |
| 3              | Charlie | 55000  |
```

**Non-Clustered Index (Book Index):**
```
Index on Name:
Alice   → Points to Row 1
Bob     → Points to Row 2
Charlie → Points to Row 3

Actual Data (stored by ID):
Row 1: 1, Alice, 50000
Row 2: 2, Bob, 60000
Row 3: 3, Charlie, 55000
```

**Use Cases:**

**Clustered Index:**
- Primary key lookups
- Range queries (BETWEEN, >, <)
- Columns used in ORDER BY frequently

**Non-Clustered Index:**
- Columns used in WHERE clauses
- Columns used in JOIN conditions
- Covering indexes (include all columns needed)

**Remember:** One clustered index (physical order), many non-clustered indexes (logical order with pointers).

---

## 162. How do you optimize slow SQL queries?

**Answer:**

**Step-by-Step Optimization Process:**

**1. Identify Slow Queries**
```sql
-- Enable slow query log
SET GLOBAL slow_query_log = 'ON';
SET GLOBAL long_query_time = 2; -- Queries taking > 2 seconds
```

**2. Analyze Query Execution Plan**
```sql
EXPLAIN SELECT * FROM orders WHERE customer_id = 123;
-- Look for: Full table scans, missing indexes, high row counts
```

**3. Add Appropriate Indexes**
```sql
-- Before: Full table scan
SELECT * FROM orders WHERE customer_id = 123; -- Slow

-- After: Add index
CREATE INDEX idx_customer_id ON orders(customer_id);
SELECT * FROM orders WHERE customer_id = 123; -- Fast
```

**4. Avoid SELECT ***
```sql
-- Bad: Retrieves unnecessary data
SELECT * FROM orders WHERE id = 1;

-- Good: Select only needed columns
SELECT id, order_date, total FROM orders WHERE id = 1;
```

**5. Use Proper JOIN Types**
```sql
-- Bad: Cartesian product
SELECT * FROM orders, customers WHERE orders.customer_id = customers.id;

-- Good: Explicit JOIN
SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.id;
```

**6. Limit Result Sets**
```sql
-- Add LIMIT for pagination
SELECT * FROM orders ORDER BY order_date DESC LIMIT 10;
```

**7. Avoid Functions on Indexed Columns**
```sql
-- Bad: Index not used
SELECT * FROM users WHERE UPPER(email) = 'JOHN@EXAMPLE.COM';

-- Good: Index used
SELECT * FROM users WHERE email = 'john@example.com';
```

**8. Use Query Caching**
```java
@Cacheable("orders")
public Order findById(Long id) {
    return orderRepository.findById(id);
}
```

**9. Optimize Subqueries**
```sql
-- Bad: Correlated subquery (runs for each row)
SELECT * FROM orders o 
WHERE total > (SELECT AVG(total) FROM orders WHERE customer_id = o.customer_id);

-- Good: JOIN with derived table
SELECT o.* FROM orders o
JOIN (SELECT customer_id, AVG(total) as avg_total FROM orders GROUP BY customer_id) avg_orders
ON o.customer_id = avg_orders.customer_id AND o.total > avg_orders.avg_total;
```

**10. Partition Large Tables**
```sql
-- Partition by date range
CREATE TABLE orders (
    id INT,
    order_date DATE,
    total DECIMAL
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024)
);
```

**Remember:** EXPLAIN → Add Indexes → Avoid SELECT * → Limit Results → Cache frequently accessed data.

---

## 163. What is query execution plan? How do you analyze it?

**Answer:**

**Query Execution Plan** shows how the database executes a SQL query - the steps, order, and methods used.

**How to Get Execution Plan:**

**MySQL:**
```sql
EXPLAIN SELECT * FROM orders WHERE customer_id = 123;
```

**PostgreSQL:**
```sql
EXPLAIN ANALYZE SELECT * FROM orders WHERE customer_id = 123;
```

**SQL Server:**
```sql
SET SHOWPLAN_TEXT ON;
SELECT * FROM orders WHERE customer_id = 123;
```

**Key Columns to Analyze:**

| Column | Meaning | Good | Bad |
|--------|---------|------|-----|
| **type** | Join type | const, eq_ref, ref | ALL (full scan) |
| **possible_keys** | Indexes that could be used | Multiple options | NULL |
| **key** | Index actually used | Index name | NULL |
| **rows** | Estimated rows scanned | Low number | High number |
| **Extra** | Additional info | Using index | Using filesort, Using temporary |

**Example Analysis:**

```sql
EXPLAIN SELECT * FROM orders WHERE customer_id = 123;
```

**Bad Execution Plan:**
```
+----+-------------+--------+------+---------------+------+---------+------+--------+-------------+
| id | select_type | table  | type | possible_keys | key  | key_len | ref  | rows   | Extra       |
+----+-------------+--------+------+---------------+------+---------+------+--------+-------------+
|  1 | SIMPLE      | orders | ALL  | NULL          | NULL | NULL    | NULL | 100000 | Using where |
+----+-------------+--------+------+---------------+------+---------+------+--------+-------------+
```
- **type = ALL:** Full table scan (BAD!)
- **key = NULL:** No index used
- **rows = 100000:** Scanning all rows

**Good Execution Plan (After Adding Index):**
```sql
CREATE INDEX idx_customer_id ON orders(customer_id);
EXPLAIN SELECT * FROM orders WHERE customer_id = 123;
```

```
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-------+
|  1 | SIMPLE      | orders | ref  | idx_customer_id  | idx_customer_id  | 4       | const | 10   | NULL  |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-------+
```
- **type = ref:** Index lookup (GOOD!)
- **key = idx_customer_id:** Using index
- **rows = 10:** Only 10 rows scanned

**Red Flags to Look For:**

1. **type = ALL** → Full table scan, add index
2. **Extra = Using filesort** → Sorting without index, add index on ORDER BY column
3. **Extra = Using temporary** → Creating temp table, optimize query
4. **High rows count** → Too many rows scanned, add filters or indexes

**Remember:** EXPLAIN shows query execution plan. Look for type=ALL (bad), key=NULL (no index), high rows (inefficient).

---

## 164. What is the N+1 query problem? How to fix it in JPA?

**Answer:**

**N+1 Query Problem** occurs when you fetch a list of N entities, and then for each entity, an additional query is executed to fetch related data, resulting in N+1 total queries.

**Example Problem:**

```java
// Entity
@Entity
public class Order {
    @Id
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading
    private Customer customer;
}

// Code that causes N+1
List<Order> orders = orderRepository.findAll(); // 1 query
for (Order order : orders) {
    System.out.println(order.getCustomer().getName()); // N queries (one per order)
}
```

**SQL Executed:**
```sql
-- Query 1: Fetch all orders
SELECT * FROM orders; -- Returns 100 orders

-- Query 2-101: Fetch customer for each order (N queries)
SELECT * FROM customers WHERE id = 1;
SELECT * FROM customers WHERE id = 2;
SELECT * FROM customers WHERE id = 3;
...
SELECT * FROM customers WHERE id = 100;
-- Total: 101 queries!
```

**Solutions:**

**1. Use JOIN FETCH (Best Solution)**
```java
@Query("SELECT o FROM Order o JOIN FETCH o.customer")
List<Order> findAllWithCustomer();

// SQL: Single query with JOIN
SELECT o.*, c.* FROM orders o INNER JOIN customers c ON o.customer_id = c.id;
// Total: 1 query!
```

**2. Use @EntityGraph**
```java
@EntityGraph(attributePaths = {"customer"})
@Query("SELECT o FROM Order o")
List<Order> findAllWithCustomer();
```

**3. Use Batch Fetching**
```java
@Entity
public class Order {
    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 10) // Fetch 10 customers at a time
    private Customer customer;
}

// SQL: Batched queries
SELECT * FROM orders; -- 1 query
SELECT * FROM customers WHERE id IN (1,2,3,4,5,6,7,8,9,10); -- Batch 1
SELECT * FROM customers WHERE id IN (11,12,13,14,15,16,17,18,19,20); -- Batch 2
// Total: 11 queries instead of 101
```

**4. Use DTO Projection**
```java
@Query("SELECT new com.example.OrderDTO(o.id, o.total, c.name) " +
       "FROM Order o JOIN o.customer c")
List<OrderDTO> findAllOrderDTOs();
```

**How to Detect N+1:**
```properties
# Enable SQL logging
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Show bind parameters
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Detect N+1 queries
spring.jpa.properties.hibernate.generate_statistics=true
```

**Remember:** N+1 = 1 query for list + N queries for related data. Fix with JOIN FETCH or @EntityGraph.

---

## 165. Explain transaction isolation levels (Read Uncommitted, Read Committed, Repeatable Read, Serializable).

**Answer:**

**Transaction Isolation Levels** control how concurrent transactions interact and what data they can see.

**Isolation Levels (Least to Most Strict):**

**1. READ UNCOMMITTED (Level 0)**
- Transactions can read uncommitted changes from other transactions
- **Allows:** Dirty reads, non-repeatable reads, phantom reads
- **Use Case:** Rarely used, only for approximate data (e.g., dashboard counts)

```java
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public void readData() {
    // Can see uncommitted changes from other transactions
}
```

**Problem - Dirty Read:**
```
Transaction 1: UPDATE accounts SET balance = 1000 WHERE id = 1; (not committed)
Transaction 2: SELECT balance FROM accounts WHERE id = 1; -- Reads 1000 (dirty read)
Transaction 1: ROLLBACK; -- Balance is actually 500!
Transaction 2 read incorrect data!
```

---

**2. READ COMMITTED (Level 1) - Default in PostgreSQL, Oracle**
- Transactions can only read committed data
- **Prevents:** Dirty reads
- **Allows:** Non-repeatable reads, phantom reads

```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void readData() {
    // Only sees committed data
}
```

**Problem - Non-Repeatable Read:**
```
Transaction 1: SELECT balance FROM accounts WHERE id = 1; -- Reads 500
Transaction 2: UPDATE accounts SET balance = 1000 WHERE id = 1; COMMIT;
Transaction 1: SELECT balance FROM accounts WHERE id = 1; -- Reads 1000 (different!)
Same query, different results within same transaction!
```

---

**3. REPEATABLE READ (Level 2) - Default in MySQL**
- Ensures same query returns same results within a transaction
- **Prevents:** Dirty reads, non-repeatable reads
- **Allows:** Phantom reads

```java
@Transactional(isolation = Isolation.REPEATABLE_READ)
public void readData() {
    // Same query returns same results
}
```

**Problem - Phantom Read:**
```
Transaction 1: SELECT COUNT(*) FROM orders WHERE status = 'PENDING'; -- Returns 10
Transaction 2: INSERT INTO orders (status) VALUES ('PENDING'); COMMIT;
Transaction 1: SELECT COUNT(*) FROM orders WHERE status = 'PENDING'; -- Returns 11 (phantom!)
New rows appeared!
```

---

**4. SERIALIZABLE (Level 3) - Strictest**
- Transactions execute as if they are serial (one after another)
- **Prevents:** Dirty reads, non-repeatable reads, phantom reads
- **Use Case:** Financial transactions, critical data

```java
@Transactional(isolation = Isolation.SERIALIZABLE)
public void transferMoney() {
    // Complete isolation, but slowest
}
```

**No Problems, but Slowest Performance**

---

**Comparison Table:**

| Isolation Level | Dirty Read | Non-Repeatable Read | Phantom Read | Performance |
|----------------|------------|---------------------|--------------|-------------|
| READ UNCOMMITTED | ✗ Allowed | ✗ Allowed | ✗ Allowed | Fastest |
| READ COMMITTED | ✓ Prevented | ✗ Allowed | ✗ Allowed | Fast |
| REPEATABLE READ | ✓ Prevented | ✓ Prevented | ✗ Allowed | Moderate |
| SERIALIZABLE | ✓ Prevented | ✓ Prevented | ✓ Prevented | Slowest |

**Spring Boot Configuration:**
```java
@Transactional(isolation = Isolation.READ_COMMITTED) // Most common
public void processOrder() {
    // Business logic
}
```

**Remember:** 
- **READ COMMITTED** = Most common (prevents dirty reads)
- **REPEATABLE READ** = MySQL default (prevents dirty + non-repeatable reads)
- **SERIALIZABLE** = Strictest (prevents all anomalies, slowest)

## 165. Explain transaction isolation levels (Read Uncommitted, Read Committed, Repeatable Read, Serializable).

**Simple Answer:**

Transaction isolation levels control how concurrent transactions interact with each other:

1. **Read Uncommitted (Level 0)** - Lowest isolation
   - Can read uncommitted changes from other transactions
   - Allows: Dirty reads, non-repeatable reads, phantom reads
   - Use case: Rarely used, only for non-critical data

2. **Read Committed (Level 1)** - Default in most databases
   - Can only read committed data
   - Prevents: Dirty reads
   - Allows: Non-repeatable reads, phantom reads
   - Use case: Most common, good balance

3. **Repeatable Read (Level 2)** - MySQL default
   - Same query returns same results within transaction
   - Prevents: Dirty reads, non-repeatable reads
   - Allows: Phantom reads
   - Use case: When you need consistent reads

4. **Serializable (Level 3)** - Highest isolation
   - Complete isolation, transactions run as if serial
   - Prevents: All anomalies (dirty, non-repeatable, phantom)
   - Use case: Critical financial transactions
   - Trade-off: Slowest performance, highest locking

**Remember:** Higher isolation = More consistency but Less performance

---

## 166. What is dirty read, non-repeatable read, phantom read?

**Simple Answer:**

These are concurrency problems in databases:

**1. Dirty Read**
- Reading uncommitted data from another transaction
- Example: Transaction A updates balance to $500 but hasn't committed. Transaction B reads $500. Transaction A rolls back. B read wrong data!
- Prevented by: Read Committed and above

**2. Non-Repeatable Read**
- Same query returns different results within same transaction
- Example: Transaction A reads balance = $100. Transaction B updates it to $200 and commits. Transaction A reads again = $200. Different result!
- Prevented by: Repeatable Read and above

**3. Phantom Read**
- New rows appear in query results within same transaction
- Example: Transaction A counts 10 employees. Transaction B inserts 1 employee and commits. Transaction A counts again = 11. New row appeared!
- Prevented by: Serializable only

**Remember:** Dirty = uncommitted data, Non-repeatable = changed data, Phantom = new/deleted rows

---

## 167. Optimistic locking vs pessimistic locking - when to use which?

**Simple Answer:**

**Optimistic Locking**
- Assumes conflicts are rare
- No locks during read, checks version before update
- Uses version field or timestamp
- If version changed, throws exception
- **When to use:** Low contention, read-heavy applications, better performance
- Example: E-commerce product catalog

**Pessimistic Locking**
- Assumes conflicts are common
- Locks data immediately when reading
- Other transactions must wait
- Guaranteed no conflicts
- **When to use:** High contention, write-heavy applications, critical updates
- Example: Banking transactions, inventory management

**Code Example:**
```java
// Optimistic - uses @Version
@Entity
public class Product {
    @Version
    private Long version;
}

// Pessimistic - explicit lock
entityManager.find(Product.class, id, LockModeType.PESSIMISTIC_WRITE);
```

**Remember:** Optimistic = "Hope for the best", Pessimistic = "Expect the worst"

---

## 168. How do you implement optimistic locking in JPA using `@Version`?

**Simple Answer:**

Add a `@Version` field to your entity. JPA automatically handles version checking:

```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double price;
    
    @Version
    private Long version;  // JPA manages this automatically
}
```

**How it works:**
1. When you read entity, JPA remembers the version
2. When you update, JPA adds: `WHERE id = ? AND version = ?`
3. If version doesn't match, throws `OptimisticLockException`
4. JPA automatically increments version on successful update

**Handling conflicts:**
```java
try {
    productRepository.save(product);
} catch (OptimisticLockException e) {
    // Reload entity and retry
    Product fresh = productRepository.findById(id);
    // Merge changes and retry
}
```

**Remember:** @Version = Automatic optimistic locking, no code needed!

---

## 169. Explain JPA entity lifecycle states (New, Managed, Detached, Removed).

**Simple Answer:**

**1. New (Transient)**
- Just created with `new` keyword
- Not associated with EntityManager
- Not in database
```java
Product p = new Product(); // New state
```

**2. Managed (Persistent)**
- Tracked by EntityManager
- Changes automatically synced to database
- In persistence context
```java
entityManager.persist(p);  // Now Managed
p.setPrice(100);  // Auto-saved at transaction commit
```

**3. Detached**
- Was managed, but EntityManager closed
- Changes NOT tracked
- Still has database ID
```java
entityManager.close();  // p becomes Detached
p.setPrice(200);  // NOT saved automatically
```

**4. Removed**
- Marked for deletion
- Will be deleted at transaction commit
```java
entityManager.remove(p);  // Marked as Removed
// Deleted from DB at commit
```

**Remember:** New → Managed → Detached → Removed (lifecycle flow)

---

## 170. Difference between `persist()`, `merge()`, `save()`, `saveAndFlush()`.

**Simple Answer:**

**JPA EntityManager methods:**

**1. persist()**
- Only for NEW entities
- Makes entity Managed
- Throws exception if entity already exists
```java
entityManager.persist(newProduct);  // Must be new
```

**2. merge()**
- For DETACHED entities
- Creates new Managed copy
- Returns the managed entity
- Can insert or update
```java
Product managed = entityManager.merge(detachedProduct);
// Use 'managed', not 'detachedProduct'
```

**Spring Data JPA methods:**

**3. save()**
- Calls persist() for new, merge() for existing
- Smart method, works for both
- Returns saved entity
```java
Product saved = repository.save(product);  // Works for new or existing
```

**4. saveAndFlush()**
- Same as save() + immediate flush to database
- Doesn't wait for transaction commit
- Use when you need immediate DB sync
```java
repository.saveAndFlush(product);  // Immediate SQL execution
```

**Remember:** persist = new only, merge = detached, save = smart choice, saveAndFlush = immediate

---

## 171. Explain JPA relationships (`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`).

**Simple Answer:**

**1. @OneToOne**
- One entity relates to exactly one other
- Example: User ↔ Profile
```java
@Entity
public class User {
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
```

**2. @OneToMany**
- One entity has many related entities
- Example: Department → Employees
```java
@Entity
public class Department {
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
```

**3. @ManyToOne**
- Many entities relate to one entity
- Example: Employees → Department
```java
@Entity
public class Employee {
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
```

**4. @ManyToMany**
- Many entities relate to many others
- Example: Students ↔ Courses (needs join table)
```java
@Entity
public class Student {
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```

**Remember:** OneToOne = 1:1, OneToMany = 1:N, ManyToOne = N:1, ManyToMany = N:M

---

## 172. What is owning side vs inverse side in bidirectional relationships?

**Simple Answer:**

In bidirectional relationships, one side "owns" the relationship:

**Owning Side:**
- Has the foreign key in database
- Controls the relationship
- Has `@JoinColumn`
- Changes here update the database

**Inverse Side:**
- References the owning side
- Has `mappedBy` attribute
- Read-only for relationship
- Changes here DON'T update database

**Example:**
```java
// Employee is OWNING side (has foreign key)
@Entity
public class Employee {
    @ManyToOne
    @JoinColumn(name = "department_id")  // Foreign key here
    private Department department;
}

// Department is INVERSE side
@Entity
public class Department {
    @OneToMany(mappedBy = "department")  // mappedBy = inverse
    private List<Employee> employees;
}
```

**Important:**
- Always update BOTH sides in code for consistency
- But only owning side updates database

```java
// Correct way
employee.setDepartment(dept);  // Owning side
dept.getEmployees().add(employee);  // Inverse side (for consistency)
```

**Remember:** Owning = has @JoinColumn, Inverse = has mappedBy

---

## 173. Lazy loading vs eager loading - when to use which?

**Simple Answer:**

**Lazy Loading (Default for collections)**
- Loads related entities only when accessed
- Better performance initially
- Can cause LazyInitializationException
```java
@OneToMany(fetch = FetchType.LAZY)  // Default
private List<Order> orders;
```

**Eager Loading**
- Loads related entities immediately
- More memory usage
- No lazy exceptions
```java
@ManyToOne(fetch = FetchType.EAGER)  // Default for @ManyToOne
private Customer customer;
```

**When to use:**

**Use LAZY when:**
- Related data not always needed
- Large collections
- Performance is critical
- Example: User's 1000 orders

**Use EAGER when:**
- Related data always needed
- Small collections
- Avoiding N+1 problem
- Example: Order's customer info

**Default behavior:**
- @OneToMany, @ManyToMany → LAZY
- @OneToOne, @ManyToOne → EAGER

**Remember:** Lazy = load later, Eager = load now. Default: collections are lazy, single entities are eager.

---

## 174. How do you handle LazyInitializationException?

**Simple Answer:**

LazyInitializationException occurs when accessing lazy-loaded data outside transaction/session.

**Solutions:**

**1. Use @Transactional (Best)**
```java
@Transactional
public OrderDTO getOrder(Long id) {
    Order order = orderRepository.findById(id);
    order.getItems().size();  // Access within transaction
    return toDTO(order);
}
```

**2. Fetch Join in Query**
```java
@Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :id")
Order findByIdWithItems(@Param("id") Long id);
```

**3. Entity Graph**
```java
@EntityGraph(attributePaths = {"items", "customer"})
Order findById(Long id);
```

**4. Open Session in View (Not recommended)**
```properties
# application.properties
spring.jpa.open-in-view=true  # Keep session open for view layer
```

**5. Initialize in Transaction**
```java
@Transactional
public Order getOrder(Long id) {
    Order order = orderRepository.findById(id);
    Hibernate.initialize(order.getItems());  // Force load
    return order;
}
```

**Remember:** Best solution = Keep access within @Transactional or use JOIN FETCH

---

## 175. Explain `@Transactional` and transaction propagation levels.

**Simple Answer:**

**@Transactional** manages database transactions automatically.

**Basic usage:**
```java
@Transactional
public void transferMoney(Long from, Long to, Double amount) {
    // All operations in one transaction
    // Auto-commit on success, rollback on exception
}
```

**Propagation Levels:**

**1. REQUIRED (Default)**
- Use existing transaction or create new
- Most common
```java
@Transactional(propagation = Propagation.REQUIRED)
```

**2. REQUIRES_NEW**
- Always create new transaction
- Suspend existing transaction
- Use for: Independent operations (logging, audit)
```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
```

**3. MANDATORY**
- Must have existing transaction
- Throws exception if no transaction
- Use for: Methods that must run in transaction

**4. SUPPORTS**
- Use transaction if exists, otherwise non-transactional
- Use for: Read-only operations

**5. NOT_SUPPORTED**
- Always run without transaction
- Suspend existing transaction

**6. NEVER**
- Must NOT have transaction
- Throws exception if transaction exists

**7. NESTED**
- Create nested transaction (savepoint)
- Can rollback to savepoint

**Common attributes:**
```java
@Transactional(
    propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    timeout = 30,
    readOnly = false,
    rollbackFor = Exception.class
)
```

**Remember:** REQUIRED = default (join or create), REQUIRES_NEW = always new, MANDATORY = must exist

---

## 176. JPQL vs Criteria API vs native SQL - when to use which?

**Simple Answer:**

**1. JPQL (Java Persistence Query Language)**
- Object-oriented SQL
- Type-safe at compile time
- Database independent
- **When to use:** Simple to moderate queries, most common

```java
@Query("SELECT u FROM User u WHERE u.email = :email")
User findByEmail(@Param("email") String email);

@Query("SELECT u FROM User u JOIN FETCH u.orders WHERE u.id = :id")
User findUserWithOrders(@Param("id") Long id);
```

**2. Criteria API**
- Programmatic query building
- Fully type-safe
- Dynamic queries
- **When to use:** Complex dynamic queries, search filters

```java
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<User> query = cb.createQuery(User.class);
Root<User> user = query.from(User.class);

List<Predicate> predicates = new ArrayList<>();
if (name != null) {
    predicates.add(cb.equal(user.get("name"), name));
}
if (email != null) {
    predicates.add(cb.like(user.get("email"), "%" + email + "%"));
}
query.where(predicates.toArray(new Predicate[0]));
```

**3. Native SQL**
- Raw SQL queries
- Database-specific features
- Best performance for complex queries
- **When to use:** Complex joins, database-specific functions, performance optimization

```java
@Query(value = "SELECT * FROM users u WHERE u.created_date > NOW() - INTERVAL 30 DAY", 
       nativeQuery = true)
List<User> findRecentUsers();

@Query(value = "SELECT u.*, COUNT(o.id) as order_count " +
               "FROM users u LEFT JOIN orders o ON u.id = o.user_id " +
               "GROUP BY u.id HAVING order_count > 5", 
       nativeQuery = true)
List<Object[]> findActiveUsers();
```

**Decision Matrix:**
- Simple queries → JPQL
- Dynamic filters → Criteria API
- Complex/optimized queries → Native SQL
- Database portability needed → JPQL or Criteria API
- Database-specific features → Native SQL

**Remember:** JPQL = most common, Criteria = dynamic, Native = performance/complex

---

## 177. How do you implement pagination in JPA using `Pageable`?

**Simple Answer:**

Spring Data JPA provides built-in pagination with `Pageable`:

**1. Repository Method:**
```java
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    
    Page<User> findByStatus(String status, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.age > :age")
    Page<User> findByAgeGreaterThan(@Param("age") int age, Pageable pageable);
}
```

**2. Service Layer:**
```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return userRepository.findAll(pageable);
    }
}
```

**3. Controller:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        
        Page<User> users = userService.getUsers(page, size, sortBy);
        return ResponseEntity.ok(users);
    }
}
```

**4. Response Structure:**
```json
{
  "content": [...],           // Actual data
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 100,       // Total records
  "totalPages": 10,           // Total pages
  "last": false,              // Is last page?
  "first": true,              // Is first page?
  "numberOfElements": 10      // Elements in current page
}
```

**Advanced Pagination:**
```java
// Multiple sort fields
Pageable pageable = PageRequest.of(0, 10, 
    Sort.by("lastName").ascending()
        .and(Sort.by("firstName").ascending()));

// Custom page implementation
Page<UserDTO> dtoPage = userPage.map(user -> new UserDTO(user));
```

**Remember:** PageRequest.of(page, size, sort) → Pass to repository → Get Page<T> with metadata

---

## Summary - Quick Reference

| Question | Key Concept | Remember This |
|----------|-------------|---------------|
| 165 | Isolation Levels | Read Uncommitted < Read Committed < Repeatable Read < Serializable |
| 166 | Read Anomalies | Dirty = uncommitted, Non-repeatable = changed, Phantom = new rows |
| 167 | Locking Types | Optimistic = hope, Pessimistic = lock |
| 168 | @Version | Automatic optimistic locking |
| 169 | Entity States | New → Managed → Detached → Removed |
| 170 | Persist vs Merge | persist = new, merge = detached, save = both |
| 171 | Relationships | OneToOne, OneToMany, ManyToOne, ManyToMany |
| 172 | Owning Side | Has @JoinColumn, controls relationship |
| 173 | Loading Types | Lazy = later, Eager = now |
| 174 | Lazy Exception | Use @Transactional or JOIN FETCH |
| 175 | @Transactional | REQUIRED (default), REQUIRES_NEW (new), MANDATORY (must exist) |
| 176 | Query Types | JPQL = common, Criteria = dynamic, Native = performance |
| 177 | Pagination | PageRequest.of(page, size, sort) |

# ✅ 12) Caching Strategies (Redis / Distributed Cache) - 11 Questions

## 178. What is caching? Why is it important for performance?

**Simple Answer:**

Caching is storing frequently accessed data in fast memory to avoid repeated expensive operations.

**Why Important:**

1. **Reduces Latency** - Data retrieved from memory (microseconds) vs database (milliseconds)
2. **Reduces Database Load** - Fewer queries to database
3. **Improves Scalability** - Handle more users with same resources
4. **Saves Cost** - Less database/API calls = lower costs
5. **Better User Experience** - Faster response times

**Common Use Cases:**
- Database query results
- API responses
- Session data
- Computed values
- Static content

**Example Impact:**
- Without cache: 100ms database query
- With cache: 1ms memory lookup
- **100x faster!**

**Remember:** Cache = Speed. Store expensive operations in fast memory.

---

## 179. Explain cache-aside (lazy loading) pattern.

**Simple Answer:**

Cache-aside is the most common caching pattern where application manages the cache.

**How it works:**

1. **Read Flow:**
   - Check cache first
   - If found (cache hit) → return data
   - If not found (cache miss) → fetch from database
   - Store in cache for next time
   - Return data

2. **Write Flow:**
   - Write to database
   - Invalidate/delete cache entry
   - Next read will reload fresh data

**Code Example:**
```java
public User getUser(Long id) {
    // 1. Check cache
    User user = cache.get("user:" + id);
    
    if (user != null) {
        return user;  // Cache hit
    }
    
    // 2. Cache miss - fetch from DB
    user = userRepository.findById(id);
    
    // 3. Store in cache
    cache.put("user:" + id, user);
    
    return user;
}

public void updateUser(User user) {
    // 1. Update database
    userRepository.save(user);
    
    // 2. Invalidate cache
    cache.evict("user:" + user.getId());
}
```

**Pros:**
- Simple to implement
- Cache only what's needed
- Resilient (cache failure doesn't break app)

**Cons:**
- Cache miss penalty (extra latency)
- Possible stale data

**Remember:** Read: Check cache → Miss → Load from DB → Store in cache. Write: Update DB → Invalidate cache.

---

## 180. What is write-through and write-behind caching?

**Simple Answer:**

**Write-Through Caching:**
- Write to cache AND database simultaneously
- Cache always in sync with database
- Slower writes, but consistent

```java
public void saveUser(User user) {
    // 1. Write to cache
    cache.put("user:" + user.getId(), user);
    
    // 2. Write to database (synchronous)
    userRepository.save(user);
}
```

**Pros:** Data consistency, cache always fresh
**Cons:** Slower writes (wait for both operations)
**Use when:** Consistency is critical

**Write-Behind (Write-Back) Caching:**
- Write to cache immediately
- Write to database asynchronously (later)
- Faster writes, but risk of data loss

```java
public void saveUser(User user) {
    // 1. Write to cache immediately
    cache.put("user:" + user.getId(), user);
    
    // 2. Queue for async DB write
    writeQueue.add(user);
    
    // Background process writes to DB later
}
```

**Pros:** Very fast writes, better performance
**Cons:** Risk of data loss if cache fails, eventual consistency
**Use when:** Performance critical, can tolerate brief inconsistency

**Comparison:**

| Pattern | Write Speed | Consistency | Data Loss Risk |
|---------|-------------|-------------|----------------|
| Write-Through | Slow | Strong | Low |
| Write-Behind | Fast | Eventual | Higher |
| Cache-Aside | Medium | Eventual | Low |

**Remember:** Write-Through = sync (slow, safe), Write-Behind = async (fast, risky)

---

## 181. Local cache vs distributed cache - when to use which?

**Simple Answer:**

**Local Cache:**
- Stored in application memory (same JVM)
- Examples: Caffeine, Guava Cache, EhCache (local mode)

**Distributed Cache:**
- Stored in separate cache server
- Shared across multiple application instances
- Examples: Redis, Memcached, Hazelcast

**Comparison:**

| Feature | Local Cache | Distributed Cache |
|---------|-------------|-------------------|
| Speed | Fastest (in-memory) | Fast (network call) |
| Scalability | Per instance | Shared across instances |
| Consistency | No sync issues | Needs sync strategy |
| Complexity | Simple | More complex |
| Cost | Free (uses app memory) | Separate infrastructure |

**When to use Local Cache:**
- Single application instance
- Small data size
- Read-heavy, rarely changes
- Ultra-low latency needed
- Example: Configuration, reference data

**When to use Distributed Cache:**
- Multiple application instances
- Need shared cache across servers
- Session management
- Large data size
- Example: User sessions, shopping carts

**Hybrid Approach (Best):**
```java
// L1 (Local) + L2 (Distributed)
// Check local cache first, then distributed
public User getUser(Long id) {
    // L1 - Local cache (fastest)
    User user = localCache.get(id);
    if (user != null) return user;
    
    // L2 - Distributed cache
    user = redisCache.get(id);
    if (user != null) {
        localCache.put(id, user);
        return user;
    }
    
    // Database
    user = database.findById(id);
    localCache.put(id, user);
    redisCache.put(id, user);
    return user;
}
```

**Remember:** Local = fast but isolated, Distributed = shared but slower. Use both for best performance.

---

## 182. How does Redis work? What data structures does it support?

**Simple Answer:**

**Redis (Remote Dictionary Server):**
- In-memory key-value data store
- Extremely fast (sub-millisecond latency)
- Supports persistence
- Single-threaded (no race conditions)

**Core Data Structures:**

**1. String** - Simple key-value
```java
SET user:1:name "John"
GET user:1:name  // Returns "John"
INCR page:views  // Atomic increment
```

**2. Hash** - Object with fields
```java
HSET user:1 name "John" age 30 email "john@example.com"
HGET user:1 name  // Returns "John"
HGETALL user:1    // Returns all fields
```

**3. List** - Ordered collection
```java
LPUSH queue:tasks "task1" "task2"
RPOP queue:tasks  // FIFO queue
LRANGE queue:tasks 0 10  // Get first 10
```

**4. Set** - Unique unordered collection
```java
SADD tags:post:1 "java" "redis" "cache"
SMEMBERS tags:post:1  // Get all tags
SISMEMBER tags:post:1 "java"  // Check membership
```

**5. Sorted Set** - Set with scores (leaderboard)
```java
ZADD leaderboard 100 "player1" 200 "player2"
ZRANGE leaderboard 0 10 WITHSCORES  // Top 10
ZRANK leaderboard "player1"  // Get rank
```

**6. Bitmap** - Bit operations
```java
SETBIT user:active:2024-01-01 123 1  // User 123 active
BITCOUNT user:active:2024-01-01  // Count active users
```

**7. HyperLogLog** - Cardinality estimation
```java
PFADD unique:visitors "user1" "user2"
PFCOUNT unique:visitors  // Approximate count
```

**8. Geospatial** - Location data
```java
GEOADD locations 13.361389 38.115556 "Palermo"
GEORADIUS locations 15 37 200 km  // Find nearby
```

**Common Use Cases:**
- Caching (String, Hash)
- Session storage (Hash)
- Real-time analytics (Sorted Set)
- Message queues (List)
- Rate limiting (String with INCR)
- Leaderboards (Sorted Set)

**Remember:** Redis = In-memory, super fast, 8+ data structures, single-threaded

---

## 183. What is cache eviction policy (LRU, LFU, FIFO, TTL)?

**Simple Answer:**

Cache eviction policies determine which data to remove when cache is full.

**1. LRU (Least Recently Used)**
- Removes least recently accessed items
- Most popular policy
- Good for general use

```
Access pattern: A, B, C, D, A, E (cache size = 3)
Cache: [A, D, E]  // B, C evicted (not used recently)
```

**2. LFU (Least Frequently Used)**
- Removes least frequently accessed items
- Tracks access count
- Good for hot data

```
Access counts: A=5, B=2, C=8, D=1
Evict D (lowest count)
```

**3. FIFO (First In First Out)**
- Removes oldest items first
- Simple but not optimal
- Ignores access patterns

```
Insert order: A, B, C, D (cache size = 3)
Cache: [B, C, D]  // A evicted (oldest)
```

**4. TTL (Time To Live)**
- Items expire after set time
- Not based on cache size
- Good for time-sensitive data

```java
cache.put("session:123", data, 30, TimeUnit.MINUTES);
// Auto-removed after 30 minutes
```

**5. Random**
- Randomly removes items
- Fastest, but unpredictable

**Comparison:**

| Policy | Best For | Complexity | Hit Rate |
|--------|----------|------------|----------|
| LRU | General purpose | Medium | High |
| LFU | Hot data | High | Very High |
| FIFO | Simple needs | Low | Low |
| TTL | Time-sensitive | Low | Varies |

**Spring Boot Configuration:**
```java
@Bean
public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)  // TTL
        .recordStats());
    return cacheManager;
}
```

**Redis Configuration:**
```properties
# Redis LRU
maxmemory 256mb
maxmemory-policy allkeys-lru
```

**Remember:** LRU = recently used stays, LFU = frequently used stays, FIFO = oldest goes, TTL = time-based

---

## 184. How do you implement caching in Spring Boot using `@Cacheable`, `@CachePut`, `@CacheEvict`?

**Simple Answer:**

**1. Enable Caching:**
```java
@SpringBootApplication
@EnableCaching
public class Application {
}
```

**2. Add Dependency:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

**3. @Cacheable - Cache method result:**
```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        // Expensive DB call
        return userRepository.findById(id);
    }
    
    @Cacheable(value = "users", key = "#email", unless = "#result == null")
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
```

**4. @CachePut - Update cache:**
```java
@CachePut(value = "users", key = "#user.id")
public User updateUser(User user) {
    return userRepository.save(user);
    // Cache updated with new value
}
```

**5. @CacheEvict - Remove from cache:**
```java
@CacheEvict(value = "users", key = "#id")
public void deleteUser(Long id) {
    userRepository.deleteById(id);
}

@CacheEvict(value = "users", allEntries = true)
public void clearAllUsers() {
    // Clear entire cache
}
```

**6. Multiple Operations:**
```java
@Caching(
    evict = {
        @CacheEvict(value = "users", key = "#user.id"),
        @CacheEvict(value = "userStats", key = "#user.id")
    },
    put = @CachePut(value = "users", key = "#user.id")
)
public User updateUserProfile(User user) {
    return userRepository.save(user);
}
```

**7. Conditional Caching:**
```java
@Cacheable(value = "products", 
           key = "#id", 
           condition = "#id > 100",
           unless = "#result.price < 10")
public Product getProduct(Long id) {
    return productRepository.findById(id);
}
```

**Configuration:**
```java
@Configuration
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
            new ConcurrentMapCache("users"),
            new ConcurrentMapCache("products")
        ));
        return cacheManager;
    }
}
```

**Remember:** @Cacheable = read from cache, @CachePut = update cache, @CacheEvict = remove from cache

---

## 185. What is cache stampede (thundering herd)? How to prevent it?

**Simple Answer:**

**Cache Stampede (Thundering Herd):**
When cache expires and multiple requests simultaneously try to reload the same data, causing database overload.

**Scenario:**
```
1. Popular item in cache expires
2. 1000 requests arrive at same time
3. All 1000 requests see cache miss
4. All 1000 query database simultaneously
5. Database crashes!
```

**Prevention Strategies:**

**1. Lock-Based Approach (Best):**
```java
private final ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();

public User getUser(Long id) {
    String key = "user:" + id;
    User user = cache.get(key);
    
    if (user == null) {
        Lock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
        lock.lock();
        try {
            // Double-check after acquiring lock
            user = cache.get(key);
            if (user == null) {
                user = database.findById(id);  // Only one thread loads
                cache.put(key, user);
            }
        } finally {
            lock.unlock();
        }
    }
    return user;
}
```

**2. Probabilistic Early Expiration:**
```java
// Refresh cache before actual expiration
public User getUser(Long id) {
    CacheEntry entry = cache.getWithMetadata("user:" + id);
    
    if (entry == null || shouldRefreshEarly(entry)) {
        User user = database.findById(id);
        cache.put("user:" + id, user, 60, TimeUnit.SECONDS);
        return user;
    }
    return entry.getValue();
}

private boolean shouldRefreshEarly(CacheEntry entry) {
    long timeLeft = entry.getExpirationTime() - System.currentTimeMillis();
    long ttl = entry.getTTL();
    double beta = 1.0;
    return timeLeft < beta * Math.log(Math.random()) * ttl;
}
```

**3. Never Expire + Background Refresh:**
```java
@Scheduled(fixedRate = 50000)
public void refreshPopularItems() {
    List<Long> popularIds = getPopularItemIds();
    for (Long id : popularIds) {
        User user = database.findById(id);
        cache.put("user:" + id, user);
    }
}
```

**4. Stale-While-Revalidate:**
```java
public User getUser(Long id) {
    User user = cache.get("user:" + id);
    
    if (user == null) {
        user = database.findById(id);
        cache.put("user:" + id, user);
    } else if (isStale(user)) {
        // Return stale data immediately
        // Refresh asynchronously
        CompletableFuture.runAsync(() -> {
            User fresh = database.findById(id);
            cache.put("user:" + id, fresh);
        });
    }
    return user;
}
```

**5. Redis Distributed Lock:**
```java
public User getUser(Long id) {
    String key = "user:" + id;
    User user = redisCache.get(key);
    
    if (user == null) {
        String lockKey = "lock:" + key;
        boolean acquired = redisCache.setNX(lockKey, "1", 10, TimeUnit.SECONDS);
        
        if (acquired) {
            try {
                user = database.findById(id);
                redisCache.put(key, user);
            } finally {
                redisCache.delete(lockKey);
            }
        } else {
            Thread.sleep(100);  // Wait and retry
            return getUser(id);
        }
    }
    return user;
}
```

**Remember:** Stampede = many requests reload same expired data. Solution = Lock so only one thread loads.

---

## 186. How do you handle cache invalidation in distributed systems?

**Simple Answer:**

Cache invalidation is hard in distributed systems because multiple servers have their own caches.

**Strategies:**

**1. TTL-Based (Simplest):**
```java
// Set expiration time
cache.put("user:1", user, 5, TimeUnit.MINUTES);
// Auto-expires, no manual invalidation needed
```
**Pros:** Simple, no coordination
**Cons:** Stale data until expiration

**2. Event-Based Invalidation:**
```java
// Service A updates data
public void updateUser(User user) {
    userRepository.save(user);
    eventPublisher.publish(new UserUpdatedEvent(user.getId()));
}

// All services listen and invalidate
@EventListener
public void onUserUpdated(UserUpdatedEvent event) {
    cache.evict("user:" + event.getUserId());
}
```

**3. Pub/Sub Pattern (Redis):**
```java
// Publisher (after update)
public void updateUser(User user) {
    userRepository.save(user);
    redisTemplate.convertAndSend("cache:invalidate", "user:" + user.getId());
}

// Subscribers (all instances)
@RedisMessageListener(topics = "cache:invalidate")
public void handleInvalidation(String key) {
    localCache.evict(key);
}
```

**4. Cache-Aside with Distributed Cache:**
```java
// All instances share Redis cache
public User getUser(Long id) {
    User user = redisCache.get("user:" + id);
    if (user == null) {
        user = database.findById(id);
        redisCache.put("user:" + id, user);
    }
    return user;
}

public void updateUser(User user) {
    database.save(user);
    redisCache.evict("user:" + user.getId());  // All instances see this
}
```

**5. Write-Through Pattern:**
```java
public void updateUser(User user) {
    // Update both atomically
    database.save(user);
    distributedCache.put("user:" + user.getId(), user);
}
```

**6. Version-Based Invalidation:**
```java
@Entity
public class User {
    @Version
    private Long version;
}

// Cache with version
cache.put("user:1:v5", user);

// On update, version changes, old cache ignored
```

**7. Kafka for Invalidation Events:**
```java
// Producer
public void updateUser(User user) {
    userRepository.save(user);
    kafkaTemplate.send("cache-invalidation", 
        new InvalidationEvent("user", user.getId()));
}

// Consumer (all instances)
@KafkaListener(topics = "cache-invalidation")
public void handleInvalidation(InvalidationEvent event) {
    cache.evict(event.getKey());
}
```

**Best Practices:**
- Use distributed cache (Redis) instead of local cache
- Combine TTL with event-based invalidation
- Invalidate on write, not on read
- Use message queue for reliable delivery
- Monitor cache hit rates

**Remember:** Distributed invalidation = notify all instances. Use Redis Pub/Sub or message queue.

---

## 187. What is Redis Cluster vs Redis Sentinel?

**Simple Answer:**

Both provide high availability for Redis, but serve different purposes.

**Redis Sentinel:**
- **Purpose:** High availability and monitoring
- **Focus:** Automatic failover for master-slave setup
- **Data:** Single master, multiple slaves (replicas)
- **Scalability:** No horizontal scaling

**How it works:**
```
Master (writes) ← Sentinel monitors
  ↓ replicates
Slave 1 (reads)
Slave 2 (reads)

If master fails:
Sentinel promotes Slave 1 to master
```

**Features:**
- Monitoring (health checks)
- Automatic failover
- Configuration provider
- Notification

**Use when:** Need high availability, single dataset fits in memory

---

**Redis Cluster:**
- **Purpose:** Horizontal scaling and partitioning
- **Focus:** Distribute data across multiple nodes
- **Data:** Sharded across 16,384 slots
- **Scalability:** Yes, add more nodes

**How it works:**
```
Node 1: Slots 0-5460     (Master + Slave)
Node 2: Slots 5461-10922 (Master + Slave)
Node 3: Slots 10923-16383 (Master + Slave)

Data automatically distributed by key hash
```

**Features:**
- Automatic sharding
- Built-in replication
- Automatic failover
- Linear scalability

**Use when:** Dataset too large for single server, need horizontal scaling

---

**Comparison:**

| Feature | Sentinel | Cluster |
|---------|----------|---------|
| Purpose | HA for single master | Horizontal scaling |
| Data Distribution | No (single master) | Yes (sharded) |
| Scalability | Vertical only | Horizontal |
| Complexity | Simple | Complex |
| Failover | Yes | Yes |
| Max Data Size | Single server memory | Sum of all nodes |
| Client Support | Simple | Cluster-aware client needed |

**Configuration Examples:**

**Sentinel:**
```properties
# sentinel.conf
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 5000
sentinel failover-timeout mymaster 10000
```

**Cluster:**
```bash
# Create cluster
redis-cli --cluster create \
  127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 \
  --cluster-replicas 1
```

**Spring Boot with Sentinel:**
```yaml
spring:
  redis:
    sentinel:
      master: mymaster
      nodes: localhost:26379,localhost:26380
```

**Spring Boot with Cluster:**
```yaml
spring:
  redis:
    cluster:
      nodes: localhost:7000,localhost:7001,localhost:7002
```

**Remember:** Sentinel = HA for single master, Cluster = sharding for scale

---

## 188. How do you ensure cache consistency?

**Simple Answer:**

Cache consistency means cache and database have the same data. This is challenging!

**Consistency Patterns:**

**1. Strong Consistency (Synchronous):**
```java
@Transactional
public void updateUser(User user) {
    // Update DB and cache in same transaction
    userRepository.save(user);
    cache.put("user:" + user.getId(), user);
    // Both succeed or both fail
}
```
**Pros:** Always consistent
**Cons:** Slower, cache failure breaks writes

**2. Eventual Consistency (Asynchronous):**
```java
public void updateUser(User user) {
    userRepository.save(user);
    // Async cache update
    CompletableFuture.runAsync(() -> 
        cache.put("user:" + user.getId(), user)
    );
}
```
**Pros:** Faster, resilient
**Cons:** Brief inconsistency window

**3. Cache Invalidation (Recommended):**
```java
public void updateUser(User user) {
    userRepository.save(user);
    cache.evict("user:" + user.getId());
    // Next read will reload fresh data
}
```
**Pros:** Simple, safe
**Cons:** Cache miss on next read

**4. Read-Through + Write-Through:**
```java
// All access through cache layer
public User getUser(Long id) {
    return cacheManager.get("user:" + id, () -> 
        userRepository.findById(id)
    );
}

public void updateUser(User user) {
    cacheManager.put("user:" + user.getId(), user);
    userRepository.save(user);
}
```

**5. Versioning:**
```java
@Entity
public class User {
    @Version
    private Long version;
}

// Cache key includes version
String key = "user:" + id + ":v" + version;
cache.put(key, user);
```

**6. TTL + Invalidation (Best Practice):**
```java
public void updateUser(User user) {
    userRepository.save(user);
    cache.evict("user:" + user.getId());
}

@Cacheable(value = "users", key = "#id")
@CacheConfig(cacheNames = "users", 
             cacheManager = "cacheManagerWithTTL")
public User getUser(Long id) {
    return userRepository.findById(id);
}

// TTL as safety net
@Bean
public CacheManager cacheManagerWithTTL() {
    return RedisCacheManager.builder(connectionFactory)
        .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10)))
        .build();
}
```

**Common Pitfalls:**

**Problem 1: Race Condition**
```java
// Thread 1: Read from DB (old value)
// Thread 2: Update DB (new value)
// Thread 2: Update cache (new value)
// Thread 1: Update cache (old value) ← STALE!
```

**Solution: Delete instead of update**
```java
public void updateUser(User user) {
    userRepository.save(user);
    cache.evict("user:" + user.getId());  // Delete, don't update
}
```

**Problem 2: Cache and DB out of sync**
```java
// Update DB succeeds
userRepository.save(user);
// Cache update fails (network issue)
cache.put("user:" + user.getId(), user);  // FAILS!
// Now inconsistent!
```

**Solution: Invalidate in finally block**
```java
public void updateUser(User user) {
    try {
        userRepository.save(user);
    } finally {
        cache.evict("user:" + user.getId());  // Always runs
    }
}
```

**Best Practices:**
1. **Invalidate, don't update** - Safer against race conditions
2. **Use TTL** - Safety net for missed invalidations
3. **Monitor cache hit rate** - Detect consistency issues
4. **Idempotent operations** - Safe to retry
5. **Eventual consistency** - Accept brief staleness
6. **Circuit breaker** - Don't let cache failures break app

**Remember:** Perfect consistency is hard. Use invalidation + TTL for safety.

---

## Summary - Quick Reference

| Question | Key Concept | Remember This |
|----------|-------------|---------------|
| 178 | What is Caching | Store expensive operations in fast memory |
| 179 | Cache-Aside | Check cache → Miss → Load DB → Store cache |
| 180 | Write Patterns | Write-Through = sync, Write-Behind = async |
| 181 | Local vs Distributed | Local = fast/isolated, Distributed = shared/slower |
| 182 | Redis | In-memory, 8+ data structures, single-threaded |
| 183 | Eviction Policies | LRU = recent, LFU = frequent, TTL = time-based |
| 184 | Spring Cache | @Cacheable = read, @CachePut = update, @CacheEvict = delete |
| 185 | Cache Stampede | Many reload same data. Use locks! |
| 186 | Distributed Invalidation | Use Redis Pub/Sub or message queue |
| 187 | Sentinel vs Cluster | Sentinel = HA, Cluster = scaling |
| 188 | Cache Consistency | Invalidate + TTL = safe approach |

# ✅ 13) Java I/O Operations, Annotations & Reflection - 12 Questions

## 189. What is the difference between InputStream and OutputStream?

**Answer:**

InputStream and OutputStream are abstract base classes for reading and writing bytes respectively.

**InputStream** is used for reading data from a source like files, network, or memory. It reads bytes one at a time or in chunks. Common methods include read(), available(), and close(). Examples are FileInputStream, ByteArrayInputStream.

**OutputStream** is used for writing data to a destination. It writes bytes to files, network, or memory. Common methods include write(), flush(), and close(). Examples are FileOutputStream, ByteArrayOutputStream.

Key difference: InputStream reads data IN from a source, OutputStream writes data OUT to a destination. Both work with bytes, not characters. For character data, use Reader and Writer classes instead.

---

## 190. Explain BufferedReader vs Scanner for reading input.

**Answer:**

Both read input but have different purposes and performance characteristics.

**BufferedReader** is faster and more efficient. It reads text line by line using readLine() method. It's synchronized and thread-safe. Best for reading large files or when performance matters. It only reads Strings, so you need to parse manually.

**Scanner** is more convenient but slower. It can parse different data types directly like nextInt(), nextDouble(), nextLine(). It uses regular expressions for parsing which adds overhead. Not thread-safe. Best for reading formatted input or when you need automatic type conversion.

For production applications reading large files, use BufferedReader. For simple console input or small data with mixed types, Scanner is fine.

---

## 191. What is serialization and deserialization? What is `serialVersionUID`?

**Answer:**

**Serialization** is converting a Java object into a byte stream so it can be saved to a file, sent over network, or stored in database. Use ObjectOutputStream.

**Deserialization** is the reverse - converting byte stream back into a Java object. Use ObjectInputStream.

The class must implement Serializable interface. Fields marked as transient are not serialized.

**serialVersionUID** is a unique identifier for each Serializable class. It's used during deserialization to verify that sender and receiver have compatible class versions. If the UID doesn't match, you get InvalidClassException.

If you don't declare it explicitly, JVM generates one automatically, but this can cause issues when class structure changes. Best practice is to always declare it explicitly as: `private static final long serialVersionUID = 1L;`

---

## 192. What is the difference between Serializable and Externalizable?

**Answer:**

Both are used for serialization but give different levels of control.

**Serializable** is a marker interface with no methods. JVM handles serialization automatically. It serializes all non-transient fields. Easy to use but less control and can be slower.

**Externalizable** extends Serializable and requires implementing two methods: writeExternal() and readExternal(). You have complete control over what gets serialized and how. More efficient because you decide exactly what to serialize. Useful when you want custom serialization logic or need to serialize only specific fields.

Use Serializable for simple cases. Use Externalizable when you need performance optimization or custom serialization logic, like encrypting sensitive data before serialization.

---

## 193. What is NIO (New I/O)? Difference between IO and NIO.

**Answer:**

NIO was introduced in Java 1.4 to provide better performance for I/O operations.

**Traditional IO** is stream-oriented, blocking, and synchronous. One thread per connection. Reads data byte by byte or line by line. Simple but not scalable for many connections.

**NIO** is buffer-oriented, non-blocking, and can be asynchronous. Uses channels and buffers. One thread can handle multiple connections using Selectors. Reads data into buffers for processing. More complex but highly scalable.

Key differences:
- IO is blocking, NIO is non-blocking
- IO uses streams, NIO uses channels and buffers
- IO is one-way, NIO channels are bidirectional
- NIO has Selectors for multiplexing multiple channels

Use NIO for high-performance applications like web servers handling thousands of connections. Use traditional IO for simple file operations.

---

## 194. Explain Channels and Buffers in NIO.

**Answer:**

**Channels** are like streams but bidirectional. They represent connections to entities like files, sockets, or hardware devices. You can read from and write to the same channel. Main types are FileChannel, SocketChannel, ServerSocketChannel, and DatagramChannel.

**Buffers** are containers for data. Instead of reading byte by byte, you read chunks of data into a buffer, then process it. Buffers have capacity (total size), position (current read/write location), and limit (end of data).

The workflow is: Create a buffer, read data from channel into buffer using channel.read(buffer), flip the buffer to switch from writing to reading mode, process data from buffer, clear or compact buffer for reuse.

This approach is more efficient because it reduces system calls and allows better memory management. Buffers can be direct (allocated outside JVM heap) for even better performance with native I/O operations.

---

## 195. What are annotations in Java? How do they work?

**Answer:**

Annotations are metadata that provide information about code to the compiler, runtime, or tools. They don't directly affect program logic but influence how code is processed.

Annotations start with @ symbol like @Override, @Deprecated, @SuppressWarnings. They can be applied to classes, methods, fields, parameters, and other elements.

They work at three levels:
1. **Source level** - processed by compiler and discarded (like @Override)
2. **Class level** - stored in .class file but not loaded at runtime
3. **Runtime level** - available at runtime via reflection (like @Autowired)

Annotations can have elements (parameters) like @RequestMapping(value="/api", method=GET). They're processed by annotation processors during compilation or by frameworks at runtime using reflection.

Common uses: dependency injection (@Autowired), ORM mapping (@Entity, @Table), REST APIs (@RestController, @GetMapping), validation (@NotNull, @Size).

---

## 196. What is the difference between built-in and custom annotations?

**Answer:**

**Built-in annotations** are provided by Java or frameworks. Java provides @Override, @Deprecated, @SuppressWarnings, @FunctionalInterface. Spring provides @Component, @Autowired, @Transactional. JPA provides @Entity, @Id, @Column. You just use them directly.

**Custom annotations** are created by developers for specific needs. You define them using @interface keyword. You must specify meta-annotations like @Retention (how long annotation is kept) and @Target (where it can be applied).

Example of custom annotation:
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {
    String value() default "";
}
```

Then you need to process it, usually with reflection or AOP. Custom annotations are useful for cross-cutting concerns like logging, security, validation, or creating your own framework features.

---

## 197. Explain meta-annotations (`@Retention`, `@Target`, `@Inherited`, `@Documented`).

**Answer:**

Meta-annotations are annotations that apply to other annotations. They define how your custom annotation behaves.

**@Retention** specifies how long the annotation is retained:
- SOURCE - discarded by compiler
- CLASS - stored in .class file but not available at runtime
- RUNTIME - available at runtime via reflection (most common)

**@Target** specifies where the annotation can be used:
- TYPE (class, interface), METHOD, FIELD, PARAMETER, CONSTRUCTOR, etc.
- You can specify multiple targets

**@Inherited** means if you annotate a parent class, child classes automatically inherit that annotation. Only works for class-level annotations.

**@Documented** means the annotation will appear in JavaDoc documentation. Useful for public APIs.

Example: `@Retention(RUNTIME) @Target({METHOD, TYPE})` means annotation is available at runtime and can be applied to methods and classes.

---

## 198. What is reflection API? When should you use it?

**Answer:**

Reflection API allows you to inspect and manipulate classes, methods, fields, and constructors at runtime, even private ones. It's in java.lang.reflect package.

You can:
- Get class information (Class.forName())
- Create objects dynamically (Constructor.newInstance())
- Access and modify fields (Field.get(), Field.set())
- Invoke methods (Method.invoke())
- Discover annotations

**When to use:**
- Frameworks like Spring for dependency injection
- ORM tools like Hibernate for entity mapping
- Testing frameworks for accessing private methods
- Serialization/deserialization
- Building generic libraries or tools

**When NOT to use:**
- Regular application code - it's slow and breaks encapsulation
- When you know types at compile time
- Performance-critical sections

Reflection has overhead and can cause security issues. Use it only when you truly need runtime flexibility.

---

## 199. How do you access private fields and methods using reflection?

**Answer:**

Reflection can bypass access modifiers to access private members.

**For private fields:**
```java
Class<?> clazz = MyClass.class;
Field field = clazz.getDeclaredField("privateField");
field.setAccessible(true); // Bypass access control
Object value = field.get(objectInstance);
field.set(objectInstance, newValue);
```

**For private methods:**
```java
Method method = clazz.getDeclaredMethod("privateMethod", paramTypes);
method.setAccessible(true); // Bypass access control
Object result = method.invoke(objectInstance, args);
```

The key is setAccessible(true) which disables access checks. This works even for private, protected, or package-private members.

**Important:** This breaks encapsulation and can cause security issues. Modern Java has SecurityManager and module system restrictions. Use only for testing or framework development, never in production application code.

---

## 200. What are the performance implications of using reflection?

**Answer:**

Reflection is significantly slower than direct code access, typically 2-10 times slower or more.

**Performance issues:**
1. **Method invocation overhead** - Method.invoke() is much slower than direct method calls
2. **Type checking** - Runtime type checking and validation adds overhead
3. **Security checks** - Access control verification takes time
4. **No compiler optimization** - JIT compiler can't optimize reflective calls as well
5. **Object creation** - Creating Method, Field, Constructor objects has overhead

**Impact on JVM:**
- Prevents inlining and other optimizations
- Can cause more garbage collection
- Increases CPU usage

**Mitigation strategies:**
- Cache reflected objects (Method, Field, Constructor) instead of looking them up repeatedly
- Use MethodHandles (Java 7+) which are faster than reflection
- Consider code generation or bytecode manipulation as alternatives
- Use reflection only during initialization, not in hot paths

For frameworks, reflection overhead during startup is acceptable. For application logic, avoid it in performance-critical code.

# ✅ 14) Design Patterns & Architecture - 16 Questions

## 201. Explain Singleton pattern and thread-safe implementations.

**Answer:**

Singleton pattern ensures only one instance of a class exists throughout the application. It provides a global access point to that instance.

**Basic implementation problems:** Simple lazy initialization is not thread-safe. Multiple threads can create multiple instances.

**Thread-safe implementations:**

1. **Eager initialization** - Instance created at class loading time. Thread-safe but wastes memory if never used.

2. **Synchronized method** - Add synchronized to getInstance(). Thread-safe but slow due to synchronization overhead on every call.

3. **Double-checked locking** - Check if instance is null, then synchronize only for creation. Use volatile keyword for instance variable.

4. **Bill Pugh Singleton** - Use static inner class. Best approach. Thread-safe, lazy, and no synchronization overhead. JVM handles thread safety during class loading.

5. **Enum Singleton** - Simplest and safest. Automatically thread-safe and prevents serialization issues.

Best practice: Use enum for simple cases or Bill Pugh for complex scenarios. Avoid synchronized method due to performance.

---

## 202. What is double-checked locking problem and its solution?

**Answer:**

Double-checked locking is an optimization to reduce synchronization overhead in Singleton pattern. You check if instance is null twice - once without lock, once with lock.

**The problem:** Without volatile keyword, it can fail due to instruction reordering. Thread A might see partially constructed object. Thread B might get reference to incomplete object before constructor finishes.

**The solution:** Declare instance variable as volatile. This prevents instruction reordering and ensures visibility across threads.

```java
private static volatile Singleton instance;

public static Singleton getInstance() {
    if (instance == null) {  // First check
        synchronized (Singleton.class) {
            if (instance == null) {  // Second check
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```

The volatile keyword ensures that writes to instance are visible to all threads immediately. This pattern works correctly in Java 5 and above. For older Java versions, use Bill Pugh Singleton instead.

---

## 203. Explain Factory pattern with real-world example.

**Answer:**

Factory pattern creates objects without exposing creation logic to the client. It uses a factory method to decide which class to instantiate.

**Real-world example:** Payment processing system.

You have different payment methods - CreditCard, PayPal, GooglePay. Instead of client code using new CreditCardPayment(), you use PaymentFactory.

```java
interface Payment {
    void processPayment(double amount);
}

class PaymentFactory {
    public static Payment getPayment(String type) {
        if (type.equals("CREDIT")) return new CreditCardPayment();
        if (type.equals("PAYPAL")) return new PayPalPayment();
        if (type.equals("GOOGLE")) return new GooglePayPayment();
        return null;
    }
}
```

**Benefits:** Client code doesn't depend on concrete classes. Easy to add new payment methods without changing client code. Follows Open-Closed Principle.

**Use cases:** Database connection creation, document generation (PDF, Word, Excel), notification systems (Email, SMS, Push).

---

## 204. Explain Abstract Factory pattern.

**Answer:**

Abstract Factory is a factory of factories. It creates families of related objects without specifying their concrete classes.

**Example:** UI components for different operating systems.

You need Button and Checkbox for Windows and Mac. Each OS has its own style. Abstract Factory creates matching sets.

```java
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}
```

**Key difference from Factory:** Factory creates one product. Abstract Factory creates families of related products that work together.

**Use cases:** Cross-platform UI frameworks, database access layers supporting multiple databases, theme systems where components must match.

---

## 205. Explain Builder pattern and its advantages.

**Answer:**

Builder pattern constructs complex objects step by step. It separates object construction from representation, allowing same construction process to create different representations.

**Problem it solves:** Classes with many constructor parameters become unreadable. Telescoping constructors are confusing. You can't create immutable objects easily with setters.

```java
class User {
    private String name;      // required
    private String email;     // required
    private int age;          // optional
    private String phone;     // optional
    
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
    }
    
    static class Builder {
        private String name;
        private String email;
        private int age;
        private String phone;
        
        Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        Builder age(int age) { this.age = age; return this; }
        Builder phone(String phone) { this.phone = phone; return this; }
        
        User build() { return new User(this); }
    }
}

// Usage: new User.Builder("John", "john@email.com").age(30).build();
```

**Advantages:** Readable code, immutable objects, optional parameters, validation before object creation.

---

## 206. Explain Strategy pattern with example.

**Answer:**

Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Algorithm varies independently from clients that use it.

**Example:** Sorting strategies or payment processing.

```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalStrategy implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

**Benefits:** Eliminates conditional statements. Easy to add new strategies. Follows Open-Closed Principle. Client can choose algorithm at runtime.

**Use cases:** Sorting algorithms, compression algorithms, validation rules, pricing strategies, routing algorithms.

---

## 207. Explain Observer pattern and its use in event-driven systems.

**Answer:**

Observer pattern defines one-to-many dependency. When one object (subject) changes state, all dependents (observers) are notified automatically.

**Example:** Newsletter subscription system.

```java
interface Observer {
    void update(String message);
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    void attach(Observer observer) {
        observers.add(observer);
    }
    
    void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

class EmailSubscriber implements Observer {
    public void update(String message) {
        System.out.println("Email sent: " + message);
    }
}
```

**In event-driven systems:** Used extensively in UI frameworks (button clicks), messaging systems (Kafka consumers), reactive programming (RxJava), Spring ApplicationEvent.

**Benefits:** Loose coupling between subject and observers. Dynamic subscription. Supports broadcast communication.

**Drawbacks:** Can cause memory leaks if observers aren't unsubscribed. Order of notification is not guaranteed.

---

## 208. Explain Decorator pattern.

**Answer:**

Decorator pattern adds new functionality to objects dynamically without altering their structure. It wraps the original object and provides additional behavior.

**Example:** Coffee shop with add-ons.

```java
interface Coffee {
    double cost();
    String description();
}

class SimpleCoffee implements Coffee {
    public double cost() { return 5.0; }
    public String description() { return "Simple Coffee"; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
}

class MilkDecorator extends CoffeeDecorator {
    MilkDecorator(Coffee coffee) { super(coffee); }
    public double cost() { return coffee.cost() + 1.5; }
    public String description() { return coffee.description() + ", Milk"; }
}

class SugarDecorator extends CoffeeDecorator {
    SugarDecorator(Coffee coffee) { super(coffee); }
    public double cost() { return coffee.cost() + 0.5; }
    public String description() { return coffee.description() + ", Sugar"; }
}

// Usage: new MilkDecorator(new SugarDecorator(new SimpleCoffee()))
```

**Benefits:** More flexible than inheritance. Add responsibilities at runtime. Follows Single Responsibility Principle.

**Real use:** Java I/O streams (BufferedReader wraps FileReader), Spring's BeanWrapper.

---

## 209. Explain Adapter pattern.

**Answer:**

Adapter pattern converts interface of a class into another interface that clients expect. It allows incompatible interfaces to work together.

**Example:** Integrating third-party payment gateway.

```java
// Your existing interface
interface PaymentProcessor {
    void processPayment(String amount);
}

// Third-party class with different interface
class StripePayment {
    void makePayment(double dollars) {
        System.out.println("Stripe payment: $" + dollars);
    }
}

// Adapter to make Stripe compatible
class StripeAdapter implements PaymentProcessor {
    private StripePayment stripePayment;
    
    StripeAdapter(StripePayment stripePayment) {
        this.stripePayment = stripePayment;
    }
    
    public void processPayment(String amount) {
        double dollars = Double.parseDouble(amount);
        stripePayment.makePayment(dollars);
    }
}
```

**Benefits:** Reuse existing classes with incompatible interfaces. Integrate legacy code with new systems. Follows Open-Closed Principle.

**Real use:** JDBC drivers adapt different databases to common interface. Spring's HandlerAdapter for different controller types.

---

## 210. Explain Facade pattern.

**Answer:**

Facade pattern provides a simplified interface to a complex subsystem. It hides complexity and provides a single entry point.

**Example:** Home theater system.

```java
// Complex subsystem classes
class DVDPlayer {
    void on() { System.out.println("DVD on"); }
    void play() { System.out.println("Playing movie"); }
}

class Projector {
    void on() { System.out.println("Projector on"); }
    void wideScreen() { System.out.println("Wide screen mode"); }
}

class SoundSystem {
    void on() { System.out.println("Sound on"); }
    void setVolume(int level) { System.out.println("Volume: " + level); }
}

// Facade - simple interface
class HomeTheaterFacade {
    private DVDPlayer dvd;
    private Projector projector;
    private SoundSystem sound;
    
    void watchMovie() {
        dvd.on();
        projector.on();
        projector.wideScreen();
        sound.on();
        sound.setVolume(10);
        dvd.play();
    }
}
```

**Benefits:** Simplifies complex systems. Reduces dependencies. Easier to use and test.

**Real use:** SLF4J facade for logging frameworks, Spring's JdbcTemplate simplifies JDBC, REST API facades for microservices.

---

## 211. Explain Template Method pattern.

**Answer:**

Template Method pattern defines skeleton of algorithm in base class, letting subclasses override specific steps without changing algorithm structure.

**Example:** Data processing pipeline.

```java
abstract class DataProcessor {
    // Template method - defines algorithm structure
    public final void process() {
        readData();
        processData();
        writeData();
    }
    
    abstract void readData();
    abstract void processData();
    
    void writeData() {  // Default implementation
        System.out.println("Writing data to file");
    }
}

class CSVProcessor extends DataProcessor {
    void readData() { System.out.println("Reading CSV"); }
    void processData() { System.out.println("Processing CSV"); }
}

class XMLProcessor extends DataProcessor {
    void readData() { System.out.println("Reading XML"); }
    void processData() { System.out.println("Processing XML"); }
}
```

**Benefits:** Code reuse. Control over algorithm structure. Subclasses only override what they need.

**Real use:** Spring's JdbcTemplate, Servlet's service() method, JUnit's setUp() and tearDown().

**Key point:** Template method should be final to prevent subclasses from changing algorithm structure.

---

## 212. Explain Proxy pattern (Static vs Dynamic proxy).

**Answer:**

Proxy pattern provides a surrogate or placeholder to control access to another object. Proxy has same interface as real object.

**Static Proxy:** You manually create proxy class at compile time.

```java
interface Service {
    void execute();
}

class RealService implements Service {
    public void execute() { System.out.println("Executing"); }
}

class ServiceProxy implements Service {
    private RealService realService;
    
    public void execute() {
        System.out.println("Before execution");
        if (realService == null) realService = new RealService();
        realService.execute();
        System.out.println("After execution");
    }
}
```

**Dynamic Proxy:** Created at runtime using reflection. Java provides Proxy class and InvocationHandler.

```java
Service proxy = (Service) Proxy.newProxyInstance(
    Service.class.getClassLoader(),
    new Class[]{Service.class},
    (proxy, method, args) -> {
        System.out.println("Before " + method.getName());
        Object result = method.invoke(new RealService(), args);
        System.out.println("After " + method.getName());
        return result;
    }
);
```

**Use cases:** Lazy loading, access control, logging, caching, remote proxy (RMI).

**Real use:** Spring AOP uses dynamic proxies, Hibernate lazy loading, security proxies.

---

## 213. Strategy vs State pattern - difference.

**Answer:**

Both patterns look similar but have different intents and usage.

**Strategy Pattern:**
- Purpose: Choose algorithm at runtime
- Client knows about different strategies and selects one
- Strategies are independent and interchangeable
- Focus: Behavior variation
- Example: Payment methods, sorting algorithms

**State Pattern:**
- Purpose: Object behavior changes based on internal state
- Client doesn't know about states, object manages state transitions
- States are related and represent object lifecycle
- Focus: State transitions
- Example: Order states (Pending → Confirmed → Shipped → Delivered)

```java
// State pattern example
class Order {
    private OrderState state;
    
    void nextState() {
        state.next(this);  // State manages transition
    }
}

interface OrderState {
    void next(Order order);
}
```

**Key difference:** Strategy is about choosing behavior. State is about changing behavior based on internal state. In State pattern, object appears to change its class.

---

## 214. What is Dependency Injection pattern?

**Answer:**

Dependency Injection is a design pattern where objects receive their dependencies from external source rather than creating them. It's a form of Inversion of Control.

**Three types:**

1. **Constructor Injection** - Dependencies passed through constructor. Best practice. Ensures required dependencies are provided. Supports immutability.

2. **Setter Injection** - Dependencies set through setter methods. Allows optional dependencies. Can change dependencies after object creation.

3. **Field Injection** - Dependencies injected directly into fields using annotations. Simple but hard to test and breaks encapsulation.

```java
// Constructor injection - preferred
class UserService {
    private final UserRepository repository;
    
    UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

**Benefits:** Loose coupling, easier testing (can inject mocks), better code organization, follows Dependency Inversion Principle.

**Frameworks:** Spring uses DI extensively with @Autowired, @Inject. Google Guice, CDI also provide DI.

**Best practice:** Prefer constructor injection for required dependencies, setter injection for optional ones. Avoid field injection.

---

## 215. Explain SOLID principles with Java examples.

**Answer:**

SOLID is five design principles for maintainable object-oriented code.

**S - Single Responsibility Principle:** Class should have one reason to change. One responsibility only.
- Bad: UserService handles business logic AND email sending
- Good: UserService for business logic, EmailService for emails

**O - Open/Closed Principle:** Open for extension, closed for modification.
- Use interfaces and abstract classes
- Add new features by extending, not modifying existing code
- Example: Strategy pattern, payment methods

**L - Liskov Substitution Principle:** Subclass should be substitutable for parent class without breaking functionality.
- If Bird has fly() method, Penguin shouldn't extend Bird
- Subclass must honor parent's contract

**I - Interface Segregation Principle:** Many specific interfaces better than one general interface.
- Don't force classes to implement methods they don't use
- Bad: Worker interface with work() and eat() - Robot doesn't eat
- Good: Workable and Eatable separate interfaces

**D - Dependency Inversion Principle:** Depend on abstractions, not concrete classes.
- High-level modules shouldn't depend on low-level modules
- Both should depend on abstractions (interfaces)
- Example: Service depends on Repository interface, not concrete implementation

These principles lead to flexible, maintainable, testable code.

---

## 216. What is hexagonal (ports and adapters) architecture?

**Answer:**

Hexagonal architecture, also called Ports and Adapters, separates core business logic from external concerns. Application core is independent of frameworks, databases, and UI.

**Key concepts:**

**Ports:** Interfaces that define how application interacts with outside world. Input ports (use cases) and output ports (repositories, external services).

**Adapters:** Implementations that connect ports to external systems. Input adapters (REST controllers, message listeners) and output adapters (database repositories, external API clients).

**Core/Domain:** Business logic. No dependencies on frameworks or infrastructure. Pure Java code.

**Structure:**
```
Core Domain (Business Logic)
    ↕ Ports (Interfaces)
Adapters (Implementations)
    ↕
External Systems (DB, REST, Message Queue)
```

**Benefits:**
- Business logic independent of frameworks
- Easy to test - mock adapters
- Easy to swap implementations (change database, switch from REST to messaging)
- Follows Dependency Inversion Principle

**Example:** UserService (core) depends on UserRepository port (interface). JpaUserRepository adapter implements the port and handles database.

**Use case:** Microservices, domain-driven design, applications requiring high testability and flexibility.

# ✅ 15) Performance & Optimization and Troubleshooting - 12 Questions

## 217. How do you identify performance bottlenecks in Java applications?

**Answer:**

Performance bottlenecks can be in CPU, memory, I/O, or network. Here's how to identify them:

**1. Monitor key metrics:** CPU usage, memory consumption, response time, throughput, GC frequency and duration.

**2. Use profiling tools:** JProfiler, YourKit, VisualVM, Java Flight Recorder. These show which methods consume most CPU time and memory.

**3. Analyze application logs:** Look for slow queries, timeouts, exceptions. Enable slow query logging in databases.

**4. Check thread dumps:** Identify blocked threads, deadlocks, or threads waiting on locks. Take multiple dumps to see patterns.

**5. Analyze heap dumps:** Find memory leaks, large objects, and what's consuming heap space.

**6. Use APM tools:** New Relic, Datadog, Dynatrace provide real-time monitoring and transaction tracing.

**7. Database profiling:** Check query execution plans, missing indexes, N+1 queries.

**Common bottlenecks:** Inefficient database queries, excessive GC, thread contention, blocking I/O, memory leaks, inefficient algorithms.

**Best practice:** Start with monitoring, narrow down to specific component, then use profiling tools to find exact issue.

---

## 218. What tools do you use for profiling (JProfiler, YourKit, VisualVM, Async-profiler)?

**Answer:**

**VisualVM:** Free, bundled with JDK. Good for basic profiling, monitoring, heap dumps, thread dumps. Visual GC plugin shows garbage collection in real-time. Best for development and quick analysis.

**JProfiler:** Commercial, powerful. CPU profiling shows hot spots, call trees. Memory profiling tracks allocations and leaks. Thread profiling shows contention. Database profiling tracks JDBC calls. Best for deep analysis.

**YourKit:** Commercial, similar to JProfiler. Excellent CPU and memory profiling. Low overhead. Good integration with IDEs. Exception profiling feature is unique.

**Async-profiler:** Free, low overhead. Uses native sampling, doesn't require instrumentation. Great for production profiling. Generates flame graphs for visualization. Best for CPU profiling in production.

**Java Flight Recorder (JFR):** Built into JVM, very low overhead. Records events continuously. Analyze with Java Mission Control. Best for production monitoring.

**When to use:**
- Development: VisualVM, IDE profilers
- Deep analysis: JProfiler, YourKit
- Production: Async-profiler, JFR (low overhead)

**Key metrics to track:** CPU time per method, memory allocations, GC activity, thread states, lock contention.

---

## 219. How do you optimize database queries for performance?

**Answer:**

**1. Add proper indexes:** Index columns used in WHERE, JOIN, ORDER BY clauses. But don't over-index as it slows writes.

**2. Analyze query execution plan:** Use EXPLAIN or EXPLAIN ANALYZE. Look for full table scans, missing indexes, inefficient joins.

**3. Avoid N+1 queries:** Use JOIN or fetch strategies. In JPA, use @EntityGraph or JOIN FETCH in JPQL.

**4. Use pagination:** Don't fetch all records. Use LIMIT/OFFSET or cursor-based pagination.

**5. Select only needed columns:** Avoid SELECT *. Fetch only required fields to reduce data transfer.

**6. Use appropriate fetch types:** Lazy loading for collections, eager for frequently accessed data.

**7. Optimize JOINs:** Ensure join columns are indexed. Consider denormalization for complex joins.

**8. Use query caching:** Cache frequently executed queries. Use second-level cache in Hibernate.

**9. Batch operations:** Use batch inserts/updates instead of individual operations.

**10. Connection pooling:** Reuse connections. Configure pool size based on load.

**11. Avoid functions in WHERE clause:** WHERE YEAR(date) = 2024 prevents index usage. Use date >= '2024-01-01' instead.

**12. Monitor slow queries:** Enable slow query log. Set threshold and analyze regularly.

---

## 220. What is connection pooling? How do you configure HikariCP?

**Answer:**

**Connection pooling** reuses database connections instead of creating new ones for each request. Creating connections is expensive (network handshake, authentication). Pooling improves performance and resource utilization.

**How it works:** Pool maintains a set of open connections. When application needs connection, it borrows from pool. After use, connection returns to pool instead of closing.

**HikariCP** is the fastest and most reliable connection pool. It's default in Spring Boot 2+.

**Configuration:**
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 10        # Max connections
      minimum-idle: 5              # Min idle connections
      connection-timeout: 30000    # Wait time for connection (ms)
      idle-timeout: 600000         # Max idle time (10 min)
      max-lifetime: 1800000        # Max connection lifetime (30 min)
      pool-name: MyHikariPool
```

**Key parameters:**
- **maximum-pool-size:** Total connections. Formula: (core_count * 2) + effective_spindle_count. Usually 10-20 for web apps.
- **minimum-idle:** Keep connections ready. Set equal to maximum-pool-size for fixed size.
- **connection-timeout:** How long to wait for connection before throwing exception.
- **max-lifetime:** Prevent stale connections. Should be less than database timeout.

**Best practices:** Don't set pool size too high. More connections don't mean better performance. Monitor pool usage and adjust.

---

## 221. How do you size thread pools correctly?

**Answer:**

Thread pool sizing depends on workload type: CPU-bound or I/O-bound.

**For CPU-bound tasks:** Number of threads = Number of CPU cores (or cores + 1). More threads cause context switching overhead without benefit.

**For I/O-bound tasks:** Number of threads = Number of cores / (1 - blocking factor). Blocking factor is percentage of time spent waiting (0 to 1). If 50% waiting, blocking factor = 0.5.

**Example:** 8 cores, 70% I/O wait (blocking factor = 0.7)
Threads = 8 / (1 - 0.7) = 8 / 0.3 = ~27 threads

**In Spring Boot:**
```yaml
server:
  tomcat:
    threads:
      max: 200          # Maximum threads
      min-spare: 10     # Minimum threads
```

**For ExecutorService:**
```java
int cores = Runtime.getRuntime().availableProcessors();
// CPU-bound
ExecutorService cpuPool = Executors.newFixedThreadPool(cores);
// I/O-bound
ExecutorService ioPool = Executors.newFixedThreadPool(cores * 2);
```

**Best practices:**
- Start with formula, then tune based on monitoring
- Monitor thread pool metrics: active threads, queue size, rejected tasks
- Use bounded queues to prevent memory issues
- Set rejection policy (abort, caller-runs, discard)
- Don't create unlimited threads

**Warning:** Too few threads = underutilization. Too many threads = context switching overhead and memory waste.

---

## 222. Horizontal vs vertical scaling - when to use which?

**Answer:**

**Vertical Scaling (Scale Up):** Add more resources to existing server - more CPU, RAM, disk. Single machine becomes more powerful.

**Pros:** Simple, no code changes, no distributed system complexity, better for databases.
**Cons:** Hardware limits, single point of failure, downtime during upgrade, expensive at high end.
**When to use:** Databases, legacy applications, when you need strong consistency, early stage with low traffic.

**Horizontal Scaling (Scale Out):** Add more servers. Distribute load across multiple machines.

**Pros:** No hardware limits, high availability, fault tolerance, cost-effective (use commodity hardware), better for stateless applications.
**Cons:** Complex architecture, need load balancer, data consistency challenges, network overhead.
**When to use:** Web applications, microservices, high traffic, need high availability, stateless services.

**Real-world approach:**
- **Web/API servers:** Horizontal scaling (stateless, easy to replicate)
- **Databases:** Vertical scaling first, then read replicas (horizontal for reads)
- **Cache (Redis):** Horizontal with clustering
- **Message queues:** Horizontal with partitioning

**Modern trend:** Horizontal scaling with containers (Docker) and orchestration (Kubernetes). Cloud auto-scaling makes horizontal scaling easier.

**Best practice:** Design for horizontal scaling from start. Keep services stateless. Use external session storage.

---

## 223. How do you optimize JVM for production workloads?

**Answer:**

**1. Choose right GC algorithm:**
- G1GC: Default, good for most cases. Balanced throughput and latency.
- ZGC/Shenandoah: Ultra-low latency (< 10ms pauses). For latency-sensitive apps.
- Parallel GC: Maximum throughput. For batch processing.

**2. Set heap size appropriately:**
```bash
-Xms4g -Xmx4g  # Set min and max same to avoid resizing
```
Rule: 25-50% of system RAM. Leave memory for OS and off-heap.

**3. Tune GC parameters:**
```bash
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200      # Target pause time
-XX:G1HeapRegionSize=16m      # Region size
-XX:InitiatingHeapOccupancyPercent=45  # When to start marking
```

**4. Enable GC logging:**
```bash
-Xlog:gc*:file=/var/log/gc.log:time,uptime:filecount=5,filesize=100m
```

**5. Set metaspace size:**
```bash
-XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m
```

**6. Use large pages for better performance:**
```bash
-XX:+UseLargePages
```

**7. Enable JIT optimizations:**
```bash
-XX:+TieredCompilation  # Use both C1 and C2 compilers
```

**8. For containers, set CPU/memory limits:**
```bash
-XX:+UseContainerSupport
-XX:MaxRAMPercentage=75.0
```

**9. Enable Flight Recorder for monitoring:**
```bash
-XX:+FlightRecorder
```

**Best practices:** Start with defaults, monitor, then tune. Don't over-optimize prematurely. Test changes under load.

---

## 224. How do you troubleshoot memory leaks in production?

**Answer:**

**Symptoms:** Increasing memory usage over time, frequent GC, OutOfMemoryError, application slowdown.

**Step-by-step approach:**

**1. Monitor heap usage:** Use monitoring tools (Prometheus, Grafana, New Relic). Look for sawtooth pattern that keeps rising.

**2. Enable GC logging:** Analyze GC logs to see if Old Generation keeps growing.

**3. Take heap dumps:** Capture heap dump when memory is high.
```bash
jmap -dump:live,format=b,file=heap.bin <pid>
# Or automatically on OOM
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/dumps
```

**4. Analyze heap dump:** Use Eclipse MAT (Memory Analyzer Tool) or VisualVM.
- Look for objects with large retained size
- Find dominator tree (what's holding most memory)
- Check for duplicate strings, large collections
- Look for suspicious object counts

**5. Find leak suspects:** MAT provides leak suspects report automatically. Shows likely culprits.

**6. Check common causes:**
- Static collections that keep growing
- Unclosed resources (connections, streams, files)
- ThreadLocal not cleaned up
- Event listeners not unregistered
- Cache without eviction policy
- Classloader leaks in application servers

**7. Review code:** Look at recent changes. Check for collections that grow unbounded.

**8. Use profilers in staging:** Reproduce issue with profiler attached to see allocations in real-time.

**Prevention:** Code reviews, use try-with-resources, set cache limits, monitor in staging, load testing.

---

## 225. How do you analyze heap dumps using MAT?

**Answer:**

**MAT (Memory Analyzer Tool)** is Eclipse tool for analyzing heap dumps to find memory leaks and optimize memory usage.

**Steps to analyze:**

**1. Open heap dump:** File → Open Heap Dump. MAT parses and creates indexes.

**2. Leak Suspects Report:** MAT automatically generates this. Shows top suspects consuming memory. Start here.

**3. Dominator Tree:** Shows objects by retained heap size (memory freed if object is GC'd). Sort by retained heap to find biggest consumers.

**4. Histogram:** Shows all classes and instance counts. Look for:
- Unexpected high instance counts
- Large arrays or collections
- Duplicate strings

**5. OQL (Object Query Language):** SQL-like queries for heap.
```sql
SELECT * FROM java.lang.String s WHERE s.value.length > 1000
SELECT * FROM java.util.HashMap WHERE size() > 10000
```

**6. Path to GC Roots:** Right-click object → Path to GC Roots → exclude weak references. Shows what's preventing object from being garbage collected.

**7. Compare heap dumps:** Take multiple dumps over time. Compare to see what's growing.

**Key concepts:**
- **Shallow heap:** Memory occupied by object itself
- **Retained heap:** Memory freed if object is GC'd (includes referenced objects)
- **GC Root:** Objects that are always reachable (static fields, active threads, JNI references)

**Common findings:** Large HashMap, char arrays (Strings), ThreadLocal leaks, unclosed connections.

---

## 226. How do you analyze thread dumps for deadlocks?

**Answer:**

**Thread dump** is snapshot of all threads and their states. Used to diagnose deadlocks, thread contention, and performance issues.

**How to take thread dump:**
```bash
jstack <pid> > threaddump.txt
# Or send SIGQUIT signal
kill -3 <pid>
# Or use jcmd
jcmd <pid> Thread.print
```

**Analyzing for deadlocks:**

**1. Look for "Found one Java-level deadlock" message:** JVM automatically detects and reports deadlocks in thread dump.

**2. Check thread states:**
- RUNNABLE: Executing
- BLOCKED: Waiting for monitor lock
- WAITING: Waiting indefinitely (wait(), join())
- TIMED_WAITING: Waiting with timeout (sleep(), wait(timeout))

**3. Identify blocked threads:** Look for threads in BLOCKED state. Check what lock they're waiting for.

**4. Find lock ownership:** See which thread holds the lock. Look for "locked <0x...>" and "waiting to lock <0x...>".

**5. Trace circular dependency:** Thread A waits for lock held by Thread B, Thread B waits for lock held by Thread A.

**Example deadlock pattern:**
```
Thread-1: locked <0x123>, waiting to lock <0x456>
Thread-2: locked <0x456>, waiting to lock <0x123>
```

**Other issues to find:**
- **Thread contention:** Many threads BLOCKED on same lock
- **Thread starvation:** Thread never gets CPU time
- **Busy threads:** RUNNABLE threads consuming CPU

**Tools:** VisualVM, FastThread.io (online analyzer), IBM Thread and Monitor Dump Analyzer.

**Best practice:** Take 3-4 thread dumps with 10-second intervals. Compare to see patterns.

---

## 227. How do you reduce API response time?

**Answer:**

**1. Database optimization:**
- Add indexes on frequently queried columns
- Fix N+1 queries using JOIN FETCH
- Use pagination, don't fetch all records
- Enable query caching
- Use read replicas for read-heavy operations

**2. Implement caching:**
- Cache frequently accessed data (Redis, Caffeine)
- Use @Cacheable for method results
- Cache at multiple levels: application, database, CDN
- Set appropriate TTL

**3. Optimize code:**
- Use efficient algorithms and data structures
- Avoid unnecessary loops and computations
- Use lazy loading where appropriate
- Profile to find hot spots

**4. Async processing:**
- Use @Async for non-critical operations
- Return response immediately, process in background
- Use CompletableFuture for parallel operations
- Offload heavy tasks to message queues

**5. Connection pooling:**
- Configure HikariCP properly
- Reuse HTTP connections (connection pooling for external APIs)

**6. Reduce payload size:**
- Return only required fields (DTO projections)
- Use compression (GZIP)
- Paginate large responses

**7. Use CDN:**
- Serve static content from CDN
- Cache API responses at edge locations

**8. Optimize serialization:**
- Use efficient formats (Protocol Buffers vs JSON)
- Configure Jackson properly

**9. Scale horizontally:**
- Add more instances behind load balancer
- Use auto-scaling

**10. Monitor and measure:**
- Use APM tools to identify slow endpoints
- Set up alerts for high response times
- Continuously monitor and optimize

**Quick wins:** Add caching, fix N+1 queries, add database indexes, enable compression.

---

## 228. What is reactive programming? When should you use it?

**Answer:**

**Reactive programming** is programming paradigm focused on asynchronous data streams and propagation of change. It's non-blocking and event-driven.

**Key concepts:**
- **Non-blocking:** Threads don't wait for I/O operations
- **Backpressure:** Consumer controls data flow rate
- **Event-driven:** React to events/data as they arrive
- **Functional style:** Use operators like map, filter, flatMap

**In Java:** Project Reactor (Spring WebFlux), RxJava, Akka Streams.

**Example with WebFlux:**
```java
@GetMapping("/users")
public Flux<User> getUsers() {
    return userRepository.findAll();  // Returns stream of users
}

@GetMapping("/user/{id}")
public Mono<User> getUser(@PathVariable String id) {
    return userRepository.findById(id);  // Returns single user
}
```

**When to use:**
- **High concurrency:** Thousands of concurrent connections (chat apps, streaming)
- **I/O-bound operations:** Lots of external API calls, database queries
- **Real-time data:** Live feeds, notifications, stock prices
- **Microservices:** Non-blocking inter-service communication
- **Limited resources:** Need to handle many requests with few threads

**When NOT to use:**
- **CPU-bound operations:** Heavy computations (reactive doesn't help)
- **Simple CRUD apps:** Traditional blocking approach is simpler
- **Team unfamiliar:** Steep learning curve, harder to debug
- **Blocking dependencies:** If you must use blocking libraries, reactive loses benefits

**Benefits:** Better resource utilization, handles more concurrent users with fewer threads, better scalability.

**Challenges:** Harder to learn and debug, different mindset, stack traces are complex, not all libraries support reactive.

**Best practice:** Use traditional Spring MVC for most apps. Use WebFlux only when you have specific need for high concurrency or non-blocking I/O.

# ✅ 16) Build Tools (Maven/Gradle) - 9 Questions

## 229. What is Maven? Explain POM structure.

**Answer:**

**Maven** is a build automation and project management tool for Java. It handles compilation, testing, packaging, dependency management, and deployment using convention over configuration.

**POM (Project Object Model)** is the fundamental unit of Maven. It's an XML file (pom.xml) that contains project information and configuration.

**Key POM elements:**

```xml
<project>
    <!-- Project coordinates - uniquely identify project -->
    <groupId>com.company</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>  <!-- jar, war, pom -->
    
    <!-- Project metadata -->
    <name>My Application</name>
    <description>Project description</description>
    
    <!-- Properties - define versions -->
    <properties>
        <java.version>17</java.version>
        <spring.version>3.1.0</spring.version>
    </properties>
    
    <!-- Dependencies -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
    
    <!-- Build configuration -->
    <build>
        <plugins>
            <!-- Maven plugins -->
        </plugins>
    </build>
</project>
```

**Benefits:** Standardized project structure, automatic dependency management, consistent build process, extensive plugin ecosystem.

**Convention:** src/main/java for code, src/test/java for tests, target/ for build output.

---

## 230. Explain Maven lifecycle phases (validate, compile, test, package, install, deploy).

**Answer:**

Maven has three built-in lifecycles: **default** (build), **clean** (cleanup), and **site** (documentation). Most important is the default lifecycle.

**Default lifecycle phases (in order):**

**1. validate:** Validates project structure and POM correctness. Checks if all necessary information is available.

**2. compile:** Compiles source code from src/main/java to target/classes. Uses maven-compiler-plugin.

**3. test:** Runs unit tests using test framework (JUnit, TestNG). Tests are in src/test/java. Uses maven-surefire-plugin. Build fails if tests fail.

**4. package:** Packages compiled code into distributable format (JAR, WAR, EAR). Creates artifact in target/ directory.

**5. verify:** Runs integration tests and quality checks. Validates package is valid and meets quality criteria.

**6. install:** Installs package to local Maven repository (~/.m2/repository). Makes it available for other local projects.

**7. deploy:** Copies package to remote repository (Nexus, Artifactory). Shares with other developers and projects.

**How it works:** When you run a phase, all previous phases execute automatically. Example: `mvn package` runs validate → compile → test → package.

**Common commands:**
- `mvn clean` - Delete target directory
- `mvn compile` - Compile code only
- `mvn test` - Compile and run tests
- `mvn package` - Create JAR/WAR
- `mvn install` - Install to local repo
- `mvn clean install` - Clean, then build and install

---

## 231. What are Maven dependency scopes (compile, provided, runtime, test, system)?

**Answer:**

Dependency scope controls when dependency is available and whether it's included in final package.

**1. compile (default):** Available in all classpaths - compile, test, and runtime. Included in final package. Most common scope.
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <scope>compile</scope>  <!-- or omit, it's default -->
</dependency>
```

**2. provided:** Available for compile and test, but NOT included in package. Container/JDK provides it at runtime. Example: Servlet API (Tomcat provides it).
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <scope>provided</scope>
</dependency>
```

**3. runtime:** Not needed for compilation, only for execution. Available at test and runtime. Example: JDBC drivers.
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

**4. test:** Only available during test compilation and execution. Not included in package. Example: JUnit, Mockito.
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
</dependency>
```

**5. system:** Similar to provided but you specify JAR location explicitly. Avoid using - not portable.

**6. import:** Only for POM dependencies in dependencyManagement. Imports dependency management from another POM.

**Best practice:** Use appropriate scope to keep package size small and avoid conflicts.

---

## 232. What is transitive dependency?

**Answer:**

**Transitive dependency** is a dependency of your dependency. Maven automatically includes them in your project.

**Example:** Your project depends on Spring Web. Spring Web depends on Spring Core. Spring Core is transitive dependency - you get it automatically without declaring it.

```
Your Project
    └── spring-boot-starter-web (direct dependency)
        ├── spring-web (transitive)
        ├── spring-webmvc (transitive)
        └── jackson-databind (transitive)
            └── jackson-core (transitive of transitive)
```

**Benefits:** Don't need to manually find and add all dependencies. Maven resolves entire dependency tree automatically.

**Dependency Mediation:** If multiple versions of same dependency exist, Maven chooses one:
- **Nearest definition wins:** Dependency closer in tree is used
- **First declaration wins:** If same depth, first declared version is used

**View dependency tree:**
```bash
mvn dependency:tree
```

**Problems:**
- **Version conflicts:** Different libraries need different versions
- **Bloated dependencies:** Get libraries you don't need
- **Security vulnerabilities:** Transitive dependencies may have issues

**Solutions:**
- **Exclusions:** Exclude specific transitive dependencies
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

- **Dependency Management:** Control versions centrally in parent POM

---

## 233. How do you resolve dependency conflicts in Maven?

**Answer:**

Dependency conflicts occur when different libraries require different versions of same dependency.

**Maven's default resolution:** Uses dependency mediation - nearest definition wins, or first declaration wins if same depth.

**Strategies to resolve conflicts:**

**1. Check dependency tree:**
```bash
mvn dependency:tree
# Or find specific dependency
mvn dependency:tree -Dincludes=groupId:artifactId
```

**2. Explicit version declaration:** Declare the version you want directly in your POM. Direct dependencies override transitive ones.
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>  <!-- Override transitive version -->
</dependency>
```

**3. Dependency Management:** Define versions in dependencyManagement section. Applies to all modules in multi-module project.
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.0</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**4. Exclusions:** Exclude problematic transitive dependency, then add correct version explicitly.
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**5. Use Maven Enforcer Plugin:** Fail build if conflicts exist.
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-enforcer-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>enforce</goal>
            </goals>
            <configuration>
                <rules>
                    <dependencyConvergence/>
                </rules>
            </configuration>
        </execution>
    </executions>
</plugin>
```

**Best practice:** Use BOM (Bill of Materials) for consistent versions across related dependencies.

---

## 234. What is Maven BOM (Bill of Materials)?

**Answer:**

**BOM (Bill of Materials)** is a special POM that provides centralized dependency version management. It's a list of dependencies with their versions that work well together.

**Purpose:** Ensures consistent versions across multiple related dependencies without specifying version for each one.

**How it works:** Import BOM in dependencyManagement with scope import. Then declare dependencies without versions - they come from BOM.

**Example - Using Spring Boot BOM:**
```xml
<dependencyManagement>
    <dependencies>
        <!-- Import Spring Boot BOM -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>3.1.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <!-- No version needed - comes from BOM -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

**Benefits:**
- Consistent versions across all dependencies
- Tested combinations that work together
- Easy version upgrades - change BOM version only
- Reduces version conflicts
- Cleaner POM - no version numbers everywhere

**Common BOMs:**
- Spring Boot: spring-boot-dependencies
- Spring Cloud: spring-cloud-dependencies
- Jackson: jackson-bom
- JUnit: junit-bom

**Creating your own BOM:** Useful for multi-module projects or company-wide standards.

---

## 235. How do you create multi-module Maven project?

**Answer:**

**Multi-module project** has parent POM and multiple child modules. Useful for organizing large applications into smaller, manageable pieces.

**Structure:**
```
my-app/
├── pom.xml (parent)
├── common/
│   └── pom.xml
├── service/
│   └── pom.xml
└── web/
    └── pom.xml
```

**Parent POM (my-app/pom.xml):**
```xml
<project>
    <groupId>com.company</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>  <!-- Important: pom packaging -->
    
    <!-- List all modules -->
    <modules>
        <module>common</module>
        <module>service</module>
        <module>web</module>
    </modules>
    
    <!-- Shared dependency versions -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>3.1.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

**Child POM (common/pom.xml):**
```xml
<project>
    <!-- Reference parent -->
    <parent>
        <groupId>com.company</groupId>
        <artifactId>my-app</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>common</artifactId>
    
    <dependencies>
        <!-- Dependencies specific to this module -->
    </dependencies>
</project>
```

**Module dependencies (service depends on common):**
```xml
<dependency>
    <groupId>com.company</groupId>
    <artifactId>common</artifactId>
    <version>${project.version}</version>
</dependency>
```

**Build all modules:**
```bash
mvn clean install  # From parent directory
```

**Build specific module:**
```bash
mvn clean install -pl service  # Only service module
mvn clean install -pl service -am  # service and its dependencies
```

**Benefits:** Code reuse, better organization, independent versioning, parallel builds, shared configuration.

---

## 236. What are Maven plugins? Name commonly used plugins.

**Answer:**

**Maven plugins** extend Maven's functionality. They execute specific tasks during build lifecycle. Each plugin has goals (tasks it can perform).

**Plugin syntax:**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

**Commonly used plugins:**

**1. maven-compiler-plugin:** Compiles Java source code. Configure Java version.

**2. maven-surefire-plugin:** Runs unit tests during test phase. Works with JUnit, TestNG.

**3. maven-failsafe-plugin:** Runs integration tests during verify phase. Tests named *IT.java.

**4. maven-jar-plugin:** Creates JAR file. Configure manifest, includes/excludes.

**5. maven-war-plugin:** Creates WAR file for web applications.

**6. maven-shade-plugin:** Creates uber/fat JAR with all dependencies included.

**7. maven-assembly-plugin:** Creates distribution packages (zip, tar.gz) with dependencies and resources.

**8. maven-dependency-plugin:** Analyzes, copies, or unpacks dependencies.

**9. maven-clean-plugin:** Cleans build directory (target/).

**10. maven-resources-plugin:** Copies resources to output directory.

**11. maven-install-plugin:** Installs artifact to local repository.

**12. maven-deploy-plugin:** Deploys artifact to remote repository.

**13. maven-enforcer-plugin:** Enforces rules (Java version, Maven version, dependency convergence).

**14. maven-checkstyle-plugin:** Code style checking.

**15. jacoco-maven-plugin:** Code coverage reporting.

**Spring Boot specific:**
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
Creates executable JAR with embedded server.

**Run plugin goal directly:**
```bash
mvn dependency:tree
mvn clean:clean
```

---

## 237. Maven vs Gradle - when to use which?

**Answer:**

Both are build tools but have different approaches and strengths.

**Maven:**

**Pros:**
- XML-based, declarative configuration
- Standardized structure and lifecycle
- Huge plugin ecosystem
- Better IDE support (historically)
- Easier for beginners - convention over configuration
- Better for traditional enterprise projects
- Stable and mature

**Cons:**
- Verbose XML configuration
- Less flexible
- Slower build times
- Harder to customize

**When to use Maven:**
- Standard Java projects
- Team prefers XML
- Need stability and maturity
- Enterprise environments with established Maven infrastructure
- Simple projects with standard structure

**Gradle:**

**Pros:**
- Groovy/Kotlin DSL - more concise and readable
- Much faster builds (incremental builds, build cache)
- More flexible and customizable
- Better for complex builds
- Native Android support
- Powerful dependency management
- Modern and actively developed

**Cons:**
- Steeper learning curve
- More complex for simple projects
- Can be harder to debug
- Less standardized - more ways to do things

**When to use Gradle:**
- Android projects (standard)
- Large projects needing fast builds
- Complex build requirements
- Multi-language projects
- Microservices with many modules
- When build performance matters

**Performance comparison:** Gradle is typically 2-10x faster than Maven due to incremental builds and build caching.

**Migration:** Many projects are migrating from Maven to Gradle for performance. Gradle can use Maven repositories.

**Modern trend:** New projects often choose Gradle. Spring Boot supports both equally well.

**Best practice:** Choose based on team expertise and project needs. Both are excellent tools.

# ✅ 17) Java Hibernate, Servlet, JSP, JPA and Web Development - 12 Questions

### 238. What is Servlet lifecycle?

A servlet goes through three main phases: initialization, service, and destruction.

**Lifecycle phases:**
1. **Loading and Instantiation** - Container loads servlet class and creates instance
2. **Initialization** - `init()` method called once when servlet is first loaded
3. **Request Handling** - `service()` method called for each request, which delegates to `doGet()`, `doPost()`, etc.
4. **Destruction** - `destroy()` method called once before servlet is removed from service

**Key points:**
- `init()` is called only once during servlet lifetime
- `service()` is called for every request (thread-safe handling needed)
- `destroy()` is called once when container shuts down or servlet is unloaded
- Container manages the entire lifecycle

---

### 239. What is the difference between `doGet()` and `doPost()`?

Both handle HTTP requests but differ in how data is sent and their intended use.

**doGet():**
- Handles GET requests
- Parameters visible in URL query string
- Limited data size (URL length restrictions)
- Bookmarkable and cacheable
- Used for retrieving data (idempotent)
- Example: `/search?query=java`

**doPost():**
- Handles POST requests
- Parameters sent in request body
- No size limitations
- Not bookmarkable or cacheable
- Used for submitting data, creating resources
- More secure for sensitive data

**Remember:** GET for reading, POST for writing/modifying.

---

### 240. What is Servlet filter and its use cases?

A filter intercepts requests and responses before they reach the servlet or client.

**How it works:**
- Implements `Filter` interface
- Has `init()`, `doFilter()`, and `destroy()` methods
- Uses `FilterChain` to pass request to next filter or servlet

**Common use cases:**
- Authentication and authorization
- Logging and auditing
- Request/response compression
- CORS handling
- Character encoding
- Input validation

**Example flow:**
```
Client → Filter1 → Filter2 → Servlet → Filter2 → Filter1 → Client
```

**Key point:** Filters are reusable and can be applied to multiple servlets via web.xml or annotations.

---

### 241. What is JSP? How is it different from Servlet?

JSP (JavaServer Pages) is a technology for creating dynamic web pages using HTML with embedded Java code.

**JSP characteristics:**
- HTML-centric with Java code embedded
- Automatically compiled to servlet by container
- Easier for UI development
- Uses tags like `<% %>`, `<%= %>`, `${ }`

**Servlet characteristics:**
- Java-centric with HTML embedded in code
- Manually written Java class
- Better for business logic
- More control over request/response

**Key differences:**
- JSP is view layer, Servlet is controller layer
- JSP converted to servlet at runtime
- JSP better for presentation, Servlet better for processing
- JSP uses implicit objects, Servlet uses explicit objects

**Remember:** JSP = HTML + Java, Servlet = Java + HTML

---

### 242. What are JSP implicit objects?

JSP provides 9 built-in objects available without declaration.

**The 9 implicit objects:**

1. **request** - HttpServletRequest object
2. **response** - HttpServletResponse object
3. **out** - JspWriter for output
4. **session** - HttpSession object
5. **application** - ServletContext object
6. **config** - ServletConfig object
7. **pageContext** - PageContext object (access to all scopes)
8. **page** - Current servlet instance (like 'this')
9. **exception** - Exception object (only in error pages)

**Scope hierarchy:**
- page < request < session < application

**Remember:** These objects are automatically available in JSP without creating them.

---

### 243. What is session management in web applications?

Session management tracks user state across multiple HTTP requests since HTTP is stateless.

**Why needed:**
- HTTP is stateless (each request is independent)
- Need to maintain user identity and data across requests
- Essential for login, shopping carts, user preferences

**Session management techniques:**

1. **Cookies** - Small data stored on client browser
2. **URL Rewriting** - Session ID appended to URL
3. **Hidden Form Fields** - Session data in hidden inputs
4. **HttpSession** - Server-side session object (most common)

**HttpSession example:**
- Container creates unique session ID
- Stored as cookie (JSESSIONID) on client
- Server maintains session data in memory
- Default timeout: 30 minutes

**Remember:** Session = server-side storage, Cookie = client-side storage.

---

### 244. What is the difference between cookies and sessions?

Both store user data but differ in storage location and security.

**Cookies:**
- Stored on client browser
- Limited size (4KB per cookie)
- Can persist across browser sessions
- Less secure (visible to user)
- Sent with every request
- Example: Remember me, preferences

**Sessions:**
- Stored on server
- No size limitation
- Destroyed when browser closes (by default)
- More secure (data on server)
- Only session ID sent to client
- Example: Login state, shopping cart

**Key differences:**
- Location: Client vs Server
- Security: Less secure vs More secure
- Size: Limited vs Unlimited
- Lifetime: Configurable vs Session-based

**Remember:** Cookie = client storage, Session = server storage with cookie ID.

---

### 245. How does Hibernate differ from JPA?

JPA is a specification, Hibernate is an implementation of that specification.

**JPA (Java Persistence API):**
- Standard specification for ORM
- Defines interfaces and annotations
- Vendor-neutral
- Part of Jakarta EE
- Examples: `@Entity`, `@Id`, `EntityManager`

**Hibernate:**
- ORM framework implementing JPA
- Provides additional features beyond JPA
- Can be used standalone or as JPA provider
- Has proprietary features and APIs
- Examples: `Session`, `Criteria API`, caching

**Key differences:**
- JPA = specification, Hibernate = implementation
- JPA is portable, Hibernate has vendor-specific features
- Hibernate existed before JPA
- Can switch from Hibernate to EclipseLink (both implement JPA)

**Remember:** JPA is the standard, Hibernate is one implementation of it.

---

### 246. What is Hibernate SessionFactory and Session?

SessionFactory and Session are core Hibernate objects for database operations.

**SessionFactory:**
- Heavy-weight object created once per application
- Thread-safe and immutable
- Factory for creating Session objects
- Holds second-level cache
- Built from Configuration
- Expensive to create

**Session:**
- Light-weight object created per request/transaction
- NOT thread-safe
- Represents single unit of work with database
- Holds first-level cache
- Used for CRUD operations
- Should be short-lived

**Relationship:**
```
SessionFactory (one per app) → Session (one per transaction)
```

**Remember:** SessionFactory = factory (singleton), Session = worker (per request).

---

### 247. What is the difference between `get()` and `load()` in Hibernate?

Both retrieve entities but differ in loading strategy and null handling.

**get() method:**
- Hits database immediately (eager loading)
- Returns null if object not found
- Returns actual object
- Use when you're unsure if object exists

**load() method:**
- Returns proxy without hitting database (lazy loading)
- Throws ObjectNotFoundException if object not found
- Returns proxy object
- Database hit only when you access properties
- Use when you're sure object exists

**Example:**
```java
// get() - immediate DB hit
User user = session.get(User.class, 1); // null if not found

// load() - lazy loading
User user = session.load(User.class, 1); // proxy returned
user.getName(); // DB hit happens here
```

**Remember:** get() = immediate + null, load() = lazy + exception.

---

### 248. What is Hibernate first-level and second-level cache?

Hibernate uses two levels of caching to improve performance.

**First-level cache:**
- Session-level cache
- Enabled by default (cannot disable)
- Scope: Single session
- Stores entities within one transaction
- Cleared when session closes
- Automatic and mandatory

**Second-level cache:**
- SessionFactory-level cache
- Disabled by default (must configure)
- Scope: Across sessions (application-wide)
- Shared among all sessions
- Requires cache provider (EhCache, Redis, Hazelcast)
- Optional and configurable

**Cache hierarchy:**
```
Request → First-level cache → Second-level cache → Database
```

**Remember:** First-level = session scope (default), Second-level = application scope (optional).

---

### 249. How do you configure Hibernate second-level cache?

Second-level cache requires configuration and a cache provider.

**Steps to configure:**

1. **Add cache provider dependency** (e.g., EhCache)
2. **Enable second-level cache in hibernate.cfg.xml:**
```xml
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">
    org.hibernate.cache.ehcache.EhCacheRegionFactory
</property>
```

3. **Mark entities as cacheable:**
```java
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User { }
```

**Cache strategies:**
- **READ_ONLY** - For data that never changes
- **READ_WRITE** - For data that can be updated
- **NONSTRICT_READ_WRITE** - For data rarely updated
- **TRANSACTIONAL** - For JTA transactions

**Common providers:**
- EhCache (most popular)
- Redis
- Hazelcast
- Infinispan

**Remember:** Enable in config + annotate entity + choose strategy + add provider.

# ✅ 18) Monitoring, Logging, Observability & Debugging - 12 Questions

### 250. What are the three pillars of observability (Logs, Metrics, Traces)?

Observability helps you understand what's happening inside your system by examining external outputs.

**The Three Pillars:**

1. **Logs** - Timestamped records of discrete events
   - What: Text records of application events
   - Example: "User login failed", "Payment processed"
   - Tools: ELK, Splunk, CloudWatch Logs
   - Use: Debugging specific issues, audit trails

2. **Metrics** - Numerical measurements over time
   - What: Aggregated data points (counters, gauges, histograms)
   - Example: CPU usage, request count, response time
   - Tools: Prometheus, Grafana, Datadog
   - Use: Performance monitoring, alerting, trends

3. **Traces** - Request journey across services
   - What: End-to-end path of a request through distributed system
   - Example: API call → Service A → Service B → Database
   - Tools: Zipkin, Jaeger, OpenTelemetry
   - Use: Understanding latency, bottleneck identification

**Remember:** Logs = what happened, Metrics = how much, Traces = where it went.

---

### 251. How do you implement logging in Spring Boot (SLF4J, Logback)?

Spring Boot uses SLF4J as the logging facade with Logback as the default implementation.

**Basic implementation:**

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    public void createUser(User user) {
        log.info("Creating user: {}", user.getEmail());
        log.debug("User details: {}", user);
    }
}
```

**Configuration (application.properties):**
```properties
logging.level.root=INFO
logging.level.com.myapp=DEBUG
logging.file.name=app.log
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

**Why SLF4J?**
- Abstraction layer (can switch implementations)
- Parameterized logging (better performance)
- No need to check log level manually

**Remember:** SLF4J = interface, Logback = implementation (default in Spring Boot).

---

### 252. Explain logging levels (TRACE, DEBUG, INFO, WARN, ERROR).

Logging levels indicate severity and help filter logs based on importance.

**Five standard levels (least to most severe):**

1. **TRACE** - Very detailed diagnostic information
   - Use: Deep debugging, method entry/exit
   - Example: "Entering method calculateTax()"
   - Production: Usually OFF

2. **DEBUG** - Detailed information for debugging
   - Use: Development troubleshooting
   - Example: "Query executed: SELECT * FROM users"
   - Production: Usually OFF

3. **INFO** - General informational messages
   - Use: Application flow, important events
   - Example: "Application started", "User logged in"
   - Production: ON

4. **WARN** - Potentially harmful situations
   - Use: Recoverable issues, deprecated usage
   - Example: "Connection pool 80% full"
   - Production: ON

5. **ERROR** - Error events that might still allow app to continue
   - Use: Exceptions, failures
   - Example: "Failed to process payment"
   - Production: ON

**Hierarchy:** TRACE < DEBUG < INFO < WARN < ERROR

**Remember:** Development = DEBUG, Production = INFO/WARN/ERROR only.

---

### 253. What is structured logging and why is it important?

Structured logging formats logs as structured data (JSON) instead of plain text.

**Traditional logging:**
```
2024-01-15 10:30:45 User john@example.com logged in from IP 192.168.1.1
```

**Structured logging:**
```json
{
  "timestamp": "2024-01-15T10:30:45Z",
  "level": "INFO",
  "message": "User logged in",
  "userId": "12345",
  "email": "john@example.com",
  "ip": "192.168.1.1",
  "service": "auth-service"
}
```

**Why important:**

1. **Searchable** - Query by specific fields
2. **Parsable** - Easy for log aggregation tools
3. **Consistent** - Standard format across services
4. **Analyzable** - Better for metrics and dashboards
5. **Filterable** - Quick filtering by attributes

**Implementation in Spring Boot:**
```java
log.info("User logged in", 
    kv("userId", user.getId()),
    kv("email", user.getEmail()),
    kv("ip", request.getRemoteAddr())
);
```

**Remember:** Structured = JSON format = easier to search and analyze.

---

### 254. What is correlation ID? How do you implement it across microservices?

Correlation ID is a unique identifier that tracks a request across multiple services.

**Why needed:**
- Trace single request through distributed system
- Link logs from different services
- Debug issues in microservices architecture
- Understand request flow

**How it works:**
1. Generate unique ID at API Gateway (UUID)
2. Pass ID in HTTP header (X-Correlation-ID)
3. Each service logs with this ID
4. Propagate to downstream services

**Implementation:**

```java
// Filter to extract/generate correlation ID
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) {
        String correlationId = request.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put("correlationId", correlationId);
        response.setHeader("X-Correlation-ID", correlationId);
        chain.doFilter(request, response);
        MDC.clear();
    }
}

// RestTemplate interceptor for propagation
restTemplate.getInterceptors().add((request, body, execution) -> {
    String correlationId = MDC.get("correlationId");
    if (correlationId != null) {
        request.getHeaders().add("X-Correlation-ID", correlationId);
    }
    return execution.execute(request, body);
});
```

**Remember:** One ID per request, passed through all services via headers.

---

### 255. What is distributed tracing? Explain Zipkin, Jaeger, OpenTelemetry.

Distributed tracing tracks requests as they flow through multiple services in a microservices architecture.

**What it provides:**
- Visual representation of request flow
- Latency breakdown per service
- Identify bottlenecks
- Understand service dependencies

**Key concepts:**
- **Trace** - Complete journey of a request
- **Span** - Single operation within a trace
- **Trace ID** - Unique identifier for entire trace
- **Span ID** - Unique identifier for each span

**Tools:**

1. **Zipkin**
   - Twitter's open-source tracing system
   - Simple UI for viewing traces
   - Lightweight and easy to set up
   - Good for small to medium systems

2. **Jaeger**
   - Uber's distributed tracing platform
   - More features than Zipkin
   - Better for complex microservices
   - Supports OpenTracing standard

3. **OpenTelemetry**
   - Unified standard (merged OpenTracing + OpenCensus)
   - Vendor-neutral
   - Supports logs, metrics, and traces
   - Future-proof choice

**Spring Boot integration:**
```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

**Remember:** Zipkin = simple, Jaeger = advanced, OpenTelemetry = standard.

---

### 256. What tools do you use for monitoring (Prometheus, Grafana, New Relic, Datadog)?

Monitoring tools collect, store, and visualize application metrics.

**Popular tools:**

1. **Prometheus**
   - Open-source metrics collection
   - Pull-based model (scrapes metrics)
   - Time-series database
   - Powerful query language (PromQL)
   - Best for: Kubernetes, cloud-native apps

2. **Grafana**
   - Visualization and dashboarding
   - Works with Prometheus, InfluxDB, etc.
   - Beautiful, customizable dashboards
   - Alerting capabilities
   - Best for: Visualizing metrics from multiple sources

3. **New Relic**
   - Commercial APM (Application Performance Monitoring)
   - Full-stack observability
   - Auto-instrumentation
   - AI-powered insights
   - Best for: Enterprise, all-in-one solution

4. **Datadog**
   - Commercial monitoring platform
   - Infrastructure + application monitoring
   - Log management + APM + tracing
   - Cloud-native
   - Best for: Cloud environments, comprehensive monitoring

**Typical stack:**
- **Open-source:** Prometheus + Grafana + ELK
- **Commercial:** Datadog or New Relic (all-in-one)

**Remember:** Prometheus collects, Grafana visualizes, New Relic/Datadog do both.

---

### 257. What metrics should you monitor in production (CPU, memory, GC, latency, throughput)?

Production monitoring requires tracking both infrastructure and application metrics.

**Infrastructure metrics:**

1. **CPU Usage**
   - Target: < 70% average
   - Alert: > 80% sustained

2. **Memory Usage**
   - Heap memory utilization
   - Alert: > 85% heap usage

3. **Disk I/O**
   - Read/write operations
   - Disk space utilization

**JVM metrics:**

4. **Garbage Collection**
   - GC frequency and duration
   - Alert: GC pause > 1 second
   - Young/Old generation usage

5. **Thread Count**
   - Active threads
   - Thread pool utilization

**Application metrics:**

6. **Latency (Response Time)**
   - P50, P95, P99 percentiles
   - Target: P95 < 200ms for APIs

7. **Throughput (Requests per second)**
   - Request rate
   - Success vs failure rate

8. **Error Rate**
   - 4xx and 5xx errors
   - Alert: Error rate > 1%

9. **Database Metrics**
   - Connection pool usage
   - Query execution time
   - Slow queries

10. **Business Metrics**
    - Orders processed
    - User registrations
    - Payment success rate

**Remember:** Monitor the 4 Golden Signals: Latency, Traffic, Errors, Saturation.

---

### 258. How do you set up alerts for critical issues?

Alerting notifies teams when metrics exceed thresholds or anomalies occur.

**Alert setup process:**

1. **Define what to alert on:**
   - High error rate (> 1%)
   - High latency (P95 > 500ms)
   - High CPU/memory (> 80%)
   - Service down (health check fails)
   - Low disk space (< 10%)

2. **Set appropriate thresholds:**
   - Based on baseline metrics
   - Consider normal variations
   - Avoid alert fatigue

3. **Configure alert rules:**

**Prometheus example:**
```yaml
groups:
  - name: api_alerts
    rules:
      - alert: HighErrorRate
        expr: rate(http_requests_total{status=~"5.."}[5m]) > 0.01
        for: 5m
        labels:
          severity: critical
        annotations:
          summary: "High error rate detected"
```

4. **Set up notification channels:**
   - PagerDuty (on-call rotation)
   - Slack (team notifications)
   - Email (non-critical)
   - SMS (critical only)

5. **Define severity levels:**
   - **Critical** - Immediate action (page on-call)
   - **Warning** - Investigate soon (Slack)
   - **Info** - FYI only (email)

**Best practices:**
- Alert on symptoms, not causes
- Reduce noise (avoid alert fatigue)
- Make alerts actionable
- Include runbook links

**Remember:** Alert on what matters, not everything. Quality over quantity.

---

### 259. How do you analyze thread dumps and heap dumps?

Thread dumps and heap dumps are essential for troubleshooting production issues.

**Thread Dump:**

**What it is:**
- Snapshot of all threads at a moment in time
- Shows thread states and stack traces

**When to use:**
- Application hanging
- High CPU usage
- Deadlock detection
- Performance issues

**How to generate:**
```bash
jstack <pid> > thread_dump.txt
# or
kill -3 <pid>  # Sends SIGQUIT
```

**What to look for:**
- **BLOCKED threads** - Waiting for locks
- **WAITING threads** - Waiting for notification
- **RUNNABLE threads** - Actively executing
- **Deadlocks** - Circular lock dependencies

**Heap Dump:**

**What it is:**
- Snapshot of memory at a moment in time
- Shows all objects and their references

**When to use:**
- OutOfMemoryError
- Memory leaks
- High memory usage

**How to generate:**
```bash
jmap -dump:live,format=b,file=heap.bin <pid>
# or configure JVM flag
-XX:+HeapDumpOnOutOfMemoryError
```

**Analysis tools:**
- **Eclipse MAT** (Memory Analyzer Tool)
- **VisualVM**
- **JProfiler**

**What to look for:**
- Objects with high retained size
- Duplicate strings
- Collection sizes
- Leak suspects

**Remember:** Thread dump = CPU/hanging issues, Heap dump = memory issues.

---

### 260. What is ELK stack (Elasticsearch, Logstash, Kibana)?

ELK is a popular open-source stack for centralized logging and log analysis.

**Components:**

1. **Elasticsearch**
   - Distributed search and analytics engine
   - Stores and indexes logs
   - Fast full-text search
   - Scalable and distributed

2. **Logstash**
   - Log collection and processing pipeline
   - Ingests logs from multiple sources
   - Transforms and enriches data
   - Sends to Elasticsearch

3. **Kibana**
   - Visualization and exploration tool
   - Web UI for Elasticsearch
   - Create dashboards and charts
   - Search and filter logs

**How it works:**
```
Application → Logstash → Elasticsearch → Kibana
    (logs)     (collect)    (store)      (visualize)
```

**Modern variation (EFK):**
- Replace Logstash with **Filebeat** (lightweight shipper)
- More efficient for simple log forwarding

**Use cases:**
- Centralized logging for microservices
- Log aggregation from multiple servers
- Real-time log analysis
- Security monitoring (SIEM)
- Application debugging

**Alternative:** Splunk (commercial), Loki (Grafana's solution)

**Remember:** E = store, L = collect, K = visualize.

---

### 261. How do you implement centralized logging?

Centralized logging aggregates logs from all services into one location for easy searching and analysis.

**Why needed:**
- Microservices generate logs on different servers
- Hard to debug issues across services
- Need single place to search all logs
- Correlation of events across services

**Implementation approaches:**

**1. ELK/EFK Stack approach:**

```
Services → Filebeat → Logstash → Elasticsearch → Kibana
```

**Steps:**
1. Configure structured logging in each service (JSON format)
2. Install Filebeat on each server to ship logs
3. Logstash processes and enriches logs
4. Elasticsearch stores and indexes
5. Kibana for visualization and search

**2. Cloud-native approach:**

**AWS:**
- CloudWatch Logs (collection)
- CloudWatch Insights (search)

**GCP:**
- Cloud Logging (formerly Stackdriver)

**Azure:**
- Azure Monitor Logs

**3. Spring Boot configuration:**

```yaml
# application.yml
logging:
  pattern:
    console: '{"timestamp":"%d{ISO8601}","level":"%level","service":"${spring.application.name}","trace":"%X{traceId}","message":"%message"}%n'
```

**Best practices:**
1. Use structured logging (JSON)
2. Include correlation IDs
3. Add service name and environment
4. Set appropriate retention policies
5. Implement log sampling for high-volume services
6. Secure sensitive data (mask PII)

**Remember:** All logs → One place → Easy search → Better debugging.


# ✅ 19) Basic System Design and Scalability - 10 Questions

### 262. How do you approach system design problems?

System design requires a structured approach to build scalable, reliable systems.

**Step-by-step approach:**

**1. Clarify Requirements (5 minutes)**
- Functional requirements: What features are needed?
- Non-functional requirements: Scale, performance, availability
- Ask questions: Users? Traffic? Data size? Latency requirements?

**2. Estimate Scale (5 minutes)**
- Daily Active Users (DAU)
- Requests per second (QPS)
- Storage requirements
- Bandwidth needs

**3. Define APIs (5 minutes)**
- Core endpoints
- Request/response formats
- Example: `POST /api/orders`, `GET /api/orders/{id}`

**4. High-Level Design (10 minutes)**
- Draw major components (client, load balancer, servers, database, cache)
- Show data flow
- Identify bottlenecks

**5. Deep Dive (15 minutes)**
- Database schema
- Caching strategy
- Scaling approach
- Handle edge cases

**6. Address Trade-offs (5 minutes)**
- Consistency vs Availability
- SQL vs NoSQL
- Synchronous vs Asynchronous

**Key principles:**
- Start simple, then scale
- Think about bottlenecks
- Consider failure scenarios
- Discuss trade-offs

**Remember:** Clarify → Estimate → Design → Deep Dive → Trade-offs.

---

### 263. What is load balancing? Explain load balancing algorithms.

Load balancing distributes incoming traffic across multiple servers to improve performance and availability.

**Why needed:**
- Prevent server overload
- Improve response time
- Provide redundancy
- Enable horizontal scaling

**Load balancing algorithms:**

**1. Round Robin**
- Distributes requests sequentially
- Simple and fair
- Doesn't consider server load
- Best for: Servers with equal capacity

**2. Weighted Round Robin**
- Assigns weight based on server capacity
- More requests to powerful servers
- Best for: Servers with different capacities

**3. Least Connections**
- Routes to server with fewest active connections
- Good for long-lived connections
- Best for: Variable request processing time

**4. Least Response Time**
- Routes to server with fastest response
- Considers both connections and response time
- Best for: Optimal performance

**5. IP Hash**
- Uses client IP to determine server
- Same client always goes to same server
- Best for: Session persistence

**6. Random**
- Randomly selects server
- Simple but effective
- Best for: Stateless applications

**Types of load balancers:**
- **Layer 4 (Transport)** - Based on IP/Port (faster)
- **Layer 7 (Application)** - Based on HTTP content (smarter)

**Remember:** Round Robin = simple, Least Connections = smart, IP Hash = sticky sessions.

---

### 264. Horizontal vs vertical scaling.

Two approaches to handle increased load: add more machines or make existing machines more powerful.

**Vertical Scaling (Scale Up):**

**What:** Increase resources of existing server (CPU, RAM, disk)

**Pros:**
- Simple to implement
- No code changes needed
- No distributed system complexity
- Consistent data (single database)

**Cons:**
- Hardware limits (can't scale infinitely)
- Single point of failure
- Downtime during upgrades
- Expensive at high end

**Example:** Upgrade from 8GB to 32GB RAM

**Horizontal Scaling (Scale Out):**

**What:** Add more servers to distribute load

**Pros:**
- Nearly unlimited scaling
- Better fault tolerance
- Cost-effective (commodity hardware)
- No downtime during scaling

**Cons:**
- Complex architecture
- Data consistency challenges
- Load balancing needed
- More operational overhead

**Example:** Add 5 more application servers

**When to use:**

**Vertical:**
- Small to medium applications
- Monolithic architecture
- Budget constraints for complexity
- Quick solution needed

**Horizontal:**
- Large-scale applications
- Microservices architecture
- High availability required
- Long-term growth expected

**Remember:** Vertical = bigger machine, Horizontal = more machines.

---

### 265. Explain CAP theorem with examples.

CAP theorem states that a distributed system can only guarantee 2 out of 3 properties simultaneously.

**The three properties:**

**1. Consistency (C)**
- All nodes see the same data at the same time
- Every read gets the most recent write
- Example: Bank account balance must be accurate

**2. Availability (A)**
- Every request gets a response (success or failure)
- System remains operational even if nodes fail
- Example: Website always responds, even during issues

**3. Partition Tolerance (P)**
- System continues to work despite network failures
- Handles communication breakdowns between nodes
- Required in distributed systems (network failures happen)

**CAP combinations:**

**CA (Consistency + Availability) - No Partition Tolerance**
- Traditional relational databases (single node)
- Example: PostgreSQL, MySQL (single instance)
- Problem: Not truly distributed, single point of failure

**CP (Consistency + Partition Tolerance) - Sacrifice Availability**
- System may refuse requests to maintain consistency
- Example: MongoDB, HBase, Redis
- Use case: Banking, financial transactions

**AP (Availability + Partition Tolerance) - Sacrifice Consistency**
- System always responds but data may be stale
- Example: Cassandra, DynamoDB, Couchbase
- Use case: Social media feeds, product catalogs

**Real-world example:**

**Banking (CP):**
- During network partition, reject transactions
- Better to be unavailable than show wrong balance

**Social Media (AP):**
- During network partition, show cached data
- Better to show slightly old posts than be down

**Remember:** In distributed systems, P is mandatory, so choose between C and A.

---

### 266. What is eventual consistency vs strong consistency?

Consistency models define how and when data changes become visible across distributed systems.

**Strong Consistency:**

**What:** All nodes see the same data immediately after a write

**Characteristics:**
- Read always returns latest write
- Synchronous replication
- Higher latency
- Lower availability

**Example:**
```
Write: balance = $100
Read immediately: balance = $100 (guaranteed)
```

**Use cases:**
- Banking transactions
- Inventory management
- Booking systems
- Financial records

**Technologies:** Traditional RDBMS, MongoDB (with proper settings), Zookeeper

**Eventual Consistency:**

**What:** All nodes will eventually see the same data, but not immediately

**Characteristics:**
- Reads may return stale data temporarily
- Asynchronous replication
- Lower latency
- Higher availability

**Example:**
```
Write: balance = $100
Read immediately: balance = $90 (old value)
Read after delay: balance = $100 (updated)
```

**Use cases:**
- Social media posts
- Product reviews
- DNS updates
- Shopping cart

**Technologies:** Cassandra, DynamoDB, Riak

**Consistency spectrum:**
```
Strong → Bounded Staleness → Session → Eventual
(Slow, Consistent) ← → (Fast, May be stale)
```

**Remember:** Strong = always correct, Eventual = eventually correct.

---

### 267. How do you design for high availability and fault tolerance?

High availability ensures systems remain operational even when components fail.

**Key strategies:**

**1. Eliminate Single Points of Failure**
- Multiple instances of every component
- Load balancers, app servers, databases
- Example: 3+ application servers behind load balancer

**2. Redundancy**
- Active-Active: All instances handle traffic
- Active-Passive: Standby instances ready to take over
- Example: Primary and replica databases

**3. Health Checks and Auto-Recovery**
- Monitor component health
- Automatic failover to healthy instances
- Example: Load balancer removes unhealthy servers

**4. Database Replication**
- Master-Slave replication
- Multi-master replication
- Example: PostgreSQL with read replicas

**5. Geographic Distribution**
- Deploy across multiple data centers/regions
- Survive regional outages
- Example: AWS multi-region deployment

**6. Circuit Breaker Pattern**
- Prevent cascading failures
- Fail fast when dependencies are down
- Example: Resilience4j circuit breaker

**7. Graceful Degradation**
- Reduce functionality instead of complete failure
- Serve cached data when database is down
- Example: Show cached product list

**8. Backup and Disaster Recovery**
- Regular automated backups
- Test recovery procedures
- Example: Daily database snapshots

**Availability calculation:**
```
99.9% (3 nines) = 8.76 hours downtime/year
99.99% (4 nines) = 52.56 minutes downtime/year
99.999% (5 nines) = 5.26 minutes downtime/year
```

**Remember:** Redundancy + Monitoring + Auto-recovery = High Availability.

---

### 268. What is database sharding? Types of sharding.

Sharding is horizontal partitioning of data across multiple databases to improve scalability.

**Why shard:**
- Single database can't handle load
- Improve query performance
- Distribute data geographically
- Scale beyond single machine limits

**How it works:**
- Split data across multiple database instances (shards)
- Each shard contains subset of data
- Application routes queries to correct shard

**Types of sharding:**

**1. Range-Based Sharding**
- Partition by value ranges
- Example: Users A-M → Shard1, N-Z → Shard2
- Pros: Simple, range queries easy
- Cons: Uneven distribution (hotspots)

**2. Hash-Based Sharding**
- Use hash function on shard key
- Example: `hash(userId) % num_shards`
- Pros: Even distribution
- Cons: Range queries difficult, resharding hard

**3. Geographic Sharding**
- Partition by location
- Example: US users → US shard, EU users → EU shard
- Pros: Low latency, data locality
- Cons: Uneven load

**4. Directory-Based Sharding**
- Lookup table maps keys to shards
- Example: Lookup service tells which shard has user
- Pros: Flexible, easy to rebalance
- Cons: Lookup service is bottleneck

**Challenges:**

1. **Cross-shard queries** - Joining data across shards is expensive
2. **Resharding** - Adding/removing shards requires data migration
3. **Hotspots** - Uneven data distribution
4. **Complexity** - Application must be shard-aware

**Example:**
```java
// Hash-based sharding
int shardId = Math.abs(userId.hashCode()) % NUM_SHARDS;
Database shard = shards.get(shardId);
```

**Remember:** Sharding = split data horizontally across multiple databases.

---

### 269. What is database replication (Master-Slave, Master-Master)?

Database replication copies data across multiple database servers for availability and performance.

**Master-Slave Replication:**

**How it works:**
- One Master (primary) handles writes
- Multiple Slaves (replicas) handle reads
- Data copied from Master to Slaves asynchronously

**Pros:**
- Read scalability (distribute reads across slaves)
- Backup and disaster recovery
- No write conflicts
- Simple to implement

**Cons:**
- Write bottleneck (single master)
- Replication lag (slaves may be behind)
- Manual failover needed if master fails

**Use cases:**
- Read-heavy applications
- Reporting and analytics
- Geographic distribution of reads

**Example flow:**
```
Write → Master → Replicate → Slave1, Slave2, Slave3
Read ← Slave1, Slave2, Slave3
```

**Master-Master Replication:**

**How it works:**
- Multiple masters accept writes
- Each master replicates to others
- Bidirectional replication

**Pros:**
- Write scalability (distribute writes)
- High availability (no single point of failure)
- Automatic failover
- Better performance

**Cons:**
- Write conflicts possible
- Complex conflict resolution
- Harder to maintain consistency
- More complex setup

**Use cases:**
- Write-heavy applications
- Multi-region deployments
- High availability requirements

**Conflict resolution strategies:**
- Last Write Wins (LWW)
- Version vectors
- Application-level resolution

**Replication types:**

**Synchronous:**
- Wait for replicas to confirm
- Strong consistency
- Higher latency

**Asynchronous:**
- Don't wait for replicas
- Eventual consistency
- Lower latency

**Remember:** Master-Slave = one writer, Master-Master = multiple writers.

---

### 270. What is message queue? Kafka vs RabbitMQ comparison.

Message queues enable asynchronous communication between services by storing messages until they're processed.

**Why use message queues:**
- Decouple services
- Handle traffic spikes (buffering)
- Ensure message delivery
- Enable async processing
- Improve scalability

**Common patterns:**
- Point-to-Point (Queue)
- Publish-Subscribe (Topic)

**Kafka vs RabbitMQ:**

**Apache Kafka:**

**Architecture:**
- Distributed streaming platform
- Log-based storage
- Partitioned topics

**Strengths:**
- High throughput (millions of messages/sec)
- Horizontal scalability
- Message replay (retain messages)
- Event sourcing
- Real-time streaming

**Use cases:**
- Event streaming
- Log aggregation
- Real-time analytics
- Activity tracking
- IoT data ingestion

**Characteristics:**
- Pull-based (consumers pull messages)
- Messages retained for configured time
- Ordered within partition
- Complex setup

**RabbitMQ:**

**Architecture:**
- Traditional message broker
- Queue-based
- AMQP protocol

**Strengths:**
- Flexible routing (exchanges)
- Message acknowledgment
- Priority queues
- Easy to set up
- Rich features (TTL, dead letter queues)

**Use cases:**
- Task queues
- Request-reply patterns
- Complex routing
- Microservices communication
- Background jobs

**Characteristics:**
- Push-based (broker pushes to consumers)
- Messages deleted after consumption
- No ordering guarantee across queues
- Simpler setup

**Quick comparison:**

| Feature | Kafka | RabbitMQ |
|---------|-------|----------|
| Throughput | Very High | Moderate |
| Latency | Low | Very Low |
| Message Retention | Yes (configurable) | No (deleted after consumption) |
| Ordering | Yes (per partition) | Limited |
| Replay | Yes | No |
| Complexity | High | Low |
| Best for | Streaming, Analytics | Task queues, RPC |

**Remember:** Kafka = high throughput streaming, RabbitMQ = flexible messaging.

---

### 271. How do you ensure idempotency in distributed systems?

Idempotency means performing the same operation multiple times produces the same result as doing it once.

**Why important:**
- Network failures cause retries
- Duplicate messages in queues
- Prevent duplicate charges, orders, etc.
- Essential for reliability

**Strategies to ensure idempotency:**

**1. Idempotent Operations (Natural)**
- Some operations are naturally idempotent
- Examples:
  - `SET user.name = "John"` (always same result)
  - `DELETE user WHERE id = 123` (deleting again does nothing)
  - GET requests (read-only)

**2. Idempotency Keys**
- Client generates unique key per request
- Server checks if key was already processed
- Return cached result if duplicate

```java
@PostMapping("/orders")
public Order createOrder(@RequestHeader("Idempotency-Key") String key,
                         @RequestBody OrderRequest request) {
    Order existing = cache.get(key);
    if (existing != null) {
        return existing; // Already processed
    }
    
    Order order = orderService.create(request);
    cache.put(key, order);
    return order;
}
```

**3. Database Constraints**
- Unique constraints prevent duplicates
- Example: Unique constraint on (userId, orderId)

```sql
CREATE UNIQUE INDEX idx_user_order ON orders(user_id, order_id);
```

**4. Versioning/Timestamps**
- Include version number or timestamp
- Only process if version is newer

```java
UPDATE account 
SET balance = 1000, version = version + 1
WHERE id = 123 AND version = 5;
```

**5. State Machines**
- Track operation state
- Only allow valid state transitions
- Example: Order states: PENDING → PROCESSING → COMPLETED

**6. Distributed Locks**
- Acquire lock before processing
- Prevent concurrent duplicate processing
- Example: Redis distributed lock

**7. Message Deduplication**
- Message queue tracks processed message IDs
- Example: Kafka exactly-once semantics

**Real-world example:**

**Payment processing:**
```java
@Transactional
public Payment processPayment(String idempotencyKey, PaymentRequest request) {
    // Check if already processed
    Payment existing = paymentRepo.findByIdempotencyKey(idempotencyKey);
    if (existing != null) {
        return existing;
    }
    
    // Process payment
    Payment payment = new Payment();
    payment.setIdempotencyKey(idempotencyKey);
    payment.setAmount(request.getAmount());
    payment.setStatus("COMPLETED");
    
    return paymentRepo.save(payment);
}
```

**Best practices:**
- Use idempotency keys for critical operations
- Store keys with TTL (e.g., 24 hours)
- Return same response for duplicate requests
- Log duplicate attempts for monitoring

**Remember:** Idempotency = same operation multiple times = same result once.

# ✅ 20) DevOps, Cloud & Deployment (AWS, Azure) - 10 Questions

### 272. What is IaaS vs PaaS vs SaaS?

These are three cloud service models that differ in the level of control and management responsibility.

**IaaS (Infrastructure as a Service):**

**What you get:**
- Virtual machines, storage, networks
- Raw computing resources
- Maximum control and flexibility

**You manage:**
- Operating system, runtime, applications, data
- Scaling, patching, security

**Provider manages:**
- Physical hardware, virtualization, networking

**Examples:**
- AWS EC2, Azure VMs, Google Compute Engine
- AWS S3 (storage)

**Use case:** Need full control over infrastructure, custom configurations

**PaaS (Platform as a Service):**

**What you get:**
- Platform to deploy applications
- Runtime environment included
- Less infrastructure management

**You manage:**
- Applications and data only

**Provider manages:**
- OS, runtime, middleware, scaling

**Examples:**
- AWS Elastic Beanstalk, Azure App Service
- Heroku, Google App Engine

**Use case:** Focus on code, not infrastructure

**SaaS (Software as a Service):**

**What you get:**
- Ready-to-use software applications
- Access via browser/API
- Zero infrastructure management

**You manage:**
- Just use the application

**Provider manages:**
- Everything (infrastructure, platform, application)

**Examples:**
- Gmail, Salesforce, Office 365, Slack

**Use case:** Use software without any technical management

**Comparison:**

| Aspect | IaaS | PaaS | SaaS |
|--------|------|------|------|
| Control | High | Medium | Low |
| Flexibility | High | Medium | Low |
| Management | You manage most | Shared | Provider manages all |
| Example | EC2 | Elastic Beanstalk | Gmail |

**Remember:** IaaS = infrastructure, PaaS = platform, SaaS = software.

---

### 273. What AWS services do you use for Spring Boot apps (EC2, ECS, Lambda, RDS, S3)?

AWS provides various services for deploying and running Spring Boot applications.

**Compute services:**

**1. EC2 (Elastic Compute Cloud)**
- Virtual servers in the cloud
- Full control over OS and configuration
- Deploy Spring Boot as JAR/WAR
- Use case: Traditional deployment, full control needed

**2. ECS (Elastic Container Service)**
- Container orchestration service
- Run Docker containers
- Managed scaling and load balancing
- Use case: Containerized Spring Boot apps

**3. Lambda**
- Serverless compute
- Run code without managing servers
- Pay per execution
- Use case: Event-driven, microservices, APIs with low traffic

**Database services:**

**4. RDS (Relational Database Service)**
- Managed relational databases
- Supports PostgreSQL, MySQL, Oracle, SQL Server
- Automated backups, patching, scaling
- Use case: Primary database for Spring Boot apps

**Storage services:**

**5. S3 (Simple Storage Service)**
- Object storage
- Store files, images, documents
- Highly durable and scalable
- Use case: File uploads, static content, backups

**Additional services commonly used:**

**6. ElastiCache (Redis/Memcached)**
- Managed caching service
- Use case: Application caching

**7. API Gateway**
- Managed API gateway
- Use case: Expose Lambda functions as REST APIs

**8. CloudWatch**
- Monitoring and logging
- Use case: Application metrics and logs

**9. Secrets Manager**
- Store and manage secrets
- Use case: Database credentials, API keys

**10. Load Balancer (ALB/NLB)**
- Distribute traffic across instances
- Use case: High availability

**Typical Spring Boot architecture on AWS:**
```
Users → Route 53 (DNS) → ALB → EC2/ECS (Spring Boot) → RDS (Database)
                                    ↓
                                   S3 (Files)
                                    ↓
                              ElastiCache (Redis)
```

**Remember:** EC2 = VMs, ECS = containers, Lambda = serverless, RDS = database, S3 = storage.

---

### 274. What is Docker? How do you containerize Spring Boot application?

Docker is a platform for packaging applications and their dependencies into containers.

**What is Docker:**
- Containerization platform
- Packages app + dependencies + runtime
- Runs consistently across environments
- Lightweight compared to VMs

**Key concepts:**
- **Image** - Blueprint for container (read-only)
- **Container** - Running instance of image
- **Dockerfile** - Instructions to build image
- **Registry** - Store for images (Docker Hub, ECR)

**Benefits:**
- Consistent environments (dev = prod)
- Easy deployment
- Isolation
- Scalability

**Containerizing Spring Boot:**

**1. Create Dockerfile:**
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**2. Build image:**
```bash
docker build -t myapp:1.0 .
```

**3. Run container:**
```bash
docker run -p 8080:8080 myapp:1.0
```

**With environment variables:**
```bash
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=jdbc:postgresql://db:5432/mydb \
  myapp:1.0
```

**Docker Compose (multi-container):**
```yaml
version: '3'
services:
  app:
    image: myapp:1.0
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
    depends_on:
      - db
  db:
    image: postgres:15
    environment:
      - POSTGRES_PASSWORD=secret
```

**Remember:** Docker = containerization, Dockerfile = build instructions, Image = template, Container = running instance.

---

### 275. What is multi-stage Docker build?

Multi-stage builds optimize Docker images by separating build and runtime environments.

**Problem with single-stage:**
- Build tools (Maven, Gradle) included in final image
- Large image size
- Security risks (unnecessary tools in production)

**Multi-stage solution:**
- Use multiple FROM statements
- Copy only necessary artifacts
- Smaller, more secure images

**Example - Spring Boot multi-stage build:**

```dockerfile
# Stage 1: Build
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**How it works:**
1. **Build stage** - Uses Maven image, compiles code
2. **Runtime stage** - Uses slim JDK, copies only JAR
3. Final image contains only runtime dependencies

**Benefits:**

1. **Smaller image size**
   - Single-stage: 800MB (includes Maven)
   - Multi-stage: 250MB (only JDK + JAR)

2. **Better security**
   - No build tools in production image
   - Reduced attack surface

3. **Faster deployments**
   - Smaller images = faster push/pull

4. **Cleaner images**
   - No source code or build artifacts

**With Gradle:**
```dockerfile
# Build stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build -x test

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/myapp.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Remember:** Multi-stage = build stage + runtime stage = smaller, secure images.

---

### 276. What is Kubernetes? Explain Pods, Services, Deployments.

Kubernetes (K8s) is a container orchestration platform for automating deployment, scaling, and management of containerized applications.

**What Kubernetes does:**
- Automates container deployment
- Scales applications up/down
- Self-healing (restarts failed containers)
- Load balancing
- Rolling updates

**Core concepts:**

**1. Pod:**

**What:** Smallest deployable unit in Kubernetes

**Characteristics:**
- Contains one or more containers
- Shares network and storage
- Ephemeral (can be replaced)
- Has unique IP address

**Example:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: myapp-pod
spec:
  containers:
  - name: myapp
    image: myapp:1.0
    ports:
    - containerPort: 8080
```

**Use case:** Usually don't create Pods directly, use Deployments

**2. Service:**

**What:** Stable network endpoint to access Pods

**Why needed:**
- Pods are ephemeral (IPs change)
- Service provides stable DNS name
- Load balances across Pods

**Types:**
- **ClusterIP** - Internal only (default)
- **NodePort** - Exposes on node port
- **LoadBalancer** - Cloud load balancer

**Example:**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: myapp-service
spec:
  selector:
    app: myapp
  ports:
  - port: 80
    targetPort: 8080
  type: LoadBalancer
```

**3. Deployment:**

**What:** Manages desired state of Pods

**Features:**
- Declares desired number of replicas
- Rolling updates
- Rollback capability
- Self-healing

**Example:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: myapp
        image: myapp:1.0
        ports:
        - containerPort: 8080
```

**How they work together:**
```
Deployment → Creates/Manages → Pods
Service → Routes traffic to → Pods
```

**Typical flow:**
1. Create Deployment (defines 3 replicas)
2. K8s creates 3 Pods
3. Create Service to expose Pods
4. Users access via Service DNS name

**Remember:** Pod = container wrapper, Service = stable endpoint, Deployment = manages Pods.

---

### 277. What is blue-green deployment vs canary deployment?

Both are deployment strategies to minimize risk and downtime during releases.

**Blue-Green Deployment:**

**How it works:**
1. **Blue** = Current production version (v1)
2. **Green** = New version (v2) deployed in parallel
3. Test Green environment
4. Switch traffic from Blue to Green instantly
5. Keep Blue as backup for quick rollback

**Characteristics:**
- Instant cutover
- 100% traffic switches at once
- Requires double infrastructure
- Easy rollback (switch back to Blue)

**Example flow:**
```
Step 1: Blue (v1) ← 100% traffic
Step 2: Blue (v1) + Green (v2) deployed
Step 3: Test Green
Step 4: Green (v2) ← 100% traffic (switch)
Step 5: Blue (v1) kept for rollback
```

**Pros:**
- Zero downtime
- Instant rollback
- Full testing before switch

**Cons:**
- Requires 2x infrastructure
- Database migrations tricky
- All-or-nothing switch

**Use case:** Critical applications, need instant rollback

**Canary Deployment:**

**How it works:**
1. Deploy new version to small subset of servers
2. Route small % of traffic to new version (e.g., 5%)
3. Monitor metrics (errors, latency)
4. Gradually increase traffic (10%, 25%, 50%, 100%)
5. Rollback if issues detected

**Characteristics:**
- Gradual rollout
- Limited blast radius
- Real user testing
- Can rollback at any stage

**Example flow:**
```
Step 1: v1 ← 100% traffic
Step 2: v1 ← 95%, v2 ← 5% (canary)
Step 3: Monitor metrics
Step 4: v1 ← 75%, v2 ← 25%
Step 5: v1 ← 50%, v2 ← 50%
Step 6: v2 ← 100% (complete)
```

**Pros:**
- Lower risk (limited exposure)
- Real user feedback
- Gradual rollout
- Less infrastructure needed

**Cons:**
- Slower rollout
- More complex routing
- Need good monitoring

**Use case:** High-risk changes, want gradual validation

**Comparison:**

| Aspect | Blue-Green | Canary |
|--------|-----------|--------|
| Traffic switch | Instant (100%) | Gradual (5%→100%) |
| Risk | Medium | Low |
| Infrastructure | 2x | 1x + small % |
| Rollback | Instant | Gradual |
| Complexity | Simple | Complex |

**Remember:** Blue-Green = instant switch, Canary = gradual rollout.

---

### 278. How do you manage secrets in cloud (AWS Secrets Manager, Parameter Store)?

Secrets (passwords, API keys, tokens) should never be hardcoded or stored in version control.

**Why manage secrets properly:**
- Security (prevent exposure)
- Rotation (change credentials regularly)
- Audit (track access)
- Centralization (single source of truth)

**AWS solutions:**

**1. AWS Secrets Manager:**

**What it does:**
- Store and manage secrets
- Automatic rotation
- Fine-grained access control
- Encryption at rest

**Features:**
- Automatic secret rotation (RDS, etc.)
- Version history
- Cross-region replication
- Integration with RDS, Redshift

**Cost:** Pay per secret + API calls

**Use case:** Database credentials, API keys needing rotation

**Example - Store secret:**
```bash
aws secretsmanager create-secret \
  --name prod/db/password \
  --secret-string "mySecretPassword123"
```

**Example - Retrieve in Spring Boot:**
```java
@Configuration
public class SecretsConfig {
    
    @Bean
    public DataSource dataSource() {
        String secret = getSecret("prod/db/password");
        // Use secret to configure DataSource
    }
    
    private String getSecret(String secretName) {
        SecretsManagerClient client = SecretsManagerClient.create();
        GetSecretValueRequest request = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build();
        return client.getSecretValue(request).secretString();
    }
}
```

**2. AWS Systems Manager Parameter Store:**

**What it does:**
- Store configuration and secrets
- Hierarchical storage
- Free tier available
- Integration with CloudFormation

**Types:**
- **String** - Plain text
- **StringList** - Comma-separated
- **SecureString** - Encrypted with KMS

**Cost:** Free for standard parameters, paid for advanced

**Use case:** Configuration values, less sensitive secrets

**Example - Store parameter:**
```bash
aws ssm put-parameter \
  --name /prod/db/url \
  --value "jdbc:postgresql://db:5432/mydb" \
  --type SecureString
```

**Example - Spring Boot integration:**
```yaml
# application.yml
spring:
  config:
    import: aws-parameterstore:/prod/
```

**Secrets Manager vs Parameter Store:**

| Feature | Secrets Manager | Parameter Store |
|---------|----------------|-----------------|
| Automatic rotation | Yes | No |
| Cost | Higher | Lower (free tier) |
| Use case | Secrets needing rotation | Config + simple secrets |
| Max size | 64 KB | 8 KB (standard) |

**Best practices:**

1. **Never hardcode secrets**
2. **Use IAM roles** for access control
3. **Enable encryption** at rest
4. **Rotate regularly** (especially for databases)
5. **Audit access** using CloudTrail
6. **Use different secrets** per environment

**Alternative - Environment variables:**
```yaml
# docker-compose.yml
services:
  app:
    environment:
      - DB_PASSWORD=${DB_PASSWORD}  # From secrets
```

**Remember:** Secrets Manager = rotation + advanced, Parameter Store = config + simple secrets.

---

### 279. What is auto-scaling? How do you configure it?

Auto-scaling automatically adjusts the number of compute resources based on demand.

**Why auto-scaling:**
- Handle traffic spikes automatically
- Reduce costs (scale down during low traffic)
- Improve availability
- No manual intervention

**Types of scaling:**

**1. Horizontal scaling (scale out/in)**
- Add/remove instances
- Most common for stateless apps

**2. Vertical scaling (scale up/down)**
- Increase/decrease instance size
- Requires restart

**AWS Auto Scaling components:**

**1. Launch Template/Configuration**
- Defines instance type, AMI, security groups
- Blueprint for new instances

**2. Auto Scaling Group (ASG)**
- Manages group of EC2 instances
- Defines min, max, desired capacity

**3. Scaling Policies**
- Rules for when to scale
- Based on metrics (CPU, memory, requests)

**Scaling policies:**

**Target Tracking:**
- Maintain target metric value
- Example: Keep CPU at 70%

**Step Scaling:**
- Scale based on metric thresholds
- Example: Add 2 instances if CPU > 80%

**Scheduled Scaling:**
- Scale at specific times
- Example: Scale up at 9 AM, down at 6 PM

**Configuration example:**

```yaml
# Auto Scaling Group
AutoScalingGroup:
  MinSize: 2
  MaxSize: 10
  DesiredCapacity: 3
  HealthCheckType: ELB
  TargetGroupARNs:
    - !Ref TargetGroup

# Scaling Policy
ScalingPolicy:
  PolicyType: TargetTrackingScaling
  TargetTrackingConfiguration:
    PredefinedMetricType: ASGAverageCPUUtilization
    TargetValue: 70.0
```

**How it works:**

1. **Scale Out (add instances):**
   - CPU > 70% for 5 minutes
   - ASG launches new instances
   - Load balancer adds them to pool

2. **Scale In (remove instances):**
   - CPU < 30% for 15 minutes
   - ASG terminates instances
   - Respects min capacity

**Kubernetes auto-scaling:**

**Horizontal Pod Autoscaler (HPA):**
```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: myapp-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: myapp
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
```

**Best practices:**

1. **Set appropriate thresholds** (not too aggressive)
2. **Use cooldown periods** (prevent flapping)
3. **Monitor costs** (scaling can increase bills)
4. **Test scaling policies** before production
5. **Use multiple metrics** (CPU + memory + requests)
6. **Set reasonable min/max** limits

**Remember:** Auto-scaling = automatic resource adjustment based on demand.

---

### 280. How do you design for disaster recovery?

Disaster recovery (DR) ensures business continuity when catastrophic failures occur.

**Key concepts:**

**RTO (Recovery Time Objective):**
- How long can you be down?
- Example: 4 hours

**RPO (Recovery Point Objective):**
- How much data loss is acceptable?
- Example: 1 hour (lose max 1 hour of data)

**DR strategies (cost vs speed):**

**1. Backup and Restore (Cheapest, Slowest)**
- Regular backups to S3/Glacier
- Restore when disaster occurs
- RTO: Hours to days
- RPO: Hours
- Use case: Non-critical systems

**2. Pilot Light (Low cost, Medium speed)**
- Minimal version always running
- Core services ready, scaled down
- Scale up during disaster
- RTO: Minutes to hours
- RPO: Minutes
- Use case: Important but not critical

**3. Warm Standby (Medium cost, Fast)**
- Scaled-down version running
- Can handle some traffic
- Scale up during disaster
- RTO: Minutes
- RPO: Seconds to minutes
- Use case: Business-critical systems

**4. Multi-Site Active-Active (Expensive, Instant)**
- Full production in multiple regions
- Both sites handle traffic
- Instant failover
- RTO: Seconds
- RPO: Near zero
- Use case: Mission-critical systems

**DR implementation checklist:**

**1. Data backup:**
- Automated daily backups
- Store in different region
- Test restore regularly

**2. Multi-region deployment:**
- Deploy to 2+ AWS regions
- Use Route 53 for failover

**3. Database replication:**
- Cross-region read replicas
- Automated failover

**4. Infrastructure as Code:**
- Use Terraform/CloudFormation
- Quick environment recreation

**5. Monitoring and alerts:**
- Health checks
- Automated failover triggers

**6. Documentation:**
- DR runbook
- Contact information
- Step-by-step procedures

**7. Regular testing:**
- DR drills quarterly
- Chaos engineering
- Validate RTO/RPO

**AWS DR example:**

```
Primary Region (us-east-1):
  - EC2 instances (active)
  - RDS primary
  - S3 bucket

DR Region (us-west-2):
  - EC2 instances (standby/scaled down)
  - RDS read replica (can promote)
  - S3 cross-region replication

Route 53:
  - Health checks on primary
  - Automatic failover to DR region
```

**Spring Boot considerations:**

```yaml
# application.yml - Multi-region config
spring:
  datasource:
    url: ${DB_URL}  # Switch via environment
  cloud:
    aws:
      region: ${AWS_REGION}
```

**Cost optimization:**
- Use cheaper storage for backups (S3 Glacier)
- Scale down DR environment
- Use spot instances for non-critical

**Remember:** DR = backup + replication + failover + testing. Balance cost vs RTO/RPO.

---

### 281. What is CI/CD pipeline? Explain stages.

CI/CD automates the software delivery process from code commit to production deployment.

**CI (Continuous Integration):**
- Automatically build and test code changes
- Merge code frequently (multiple times per day)
- Catch bugs early

**CD (Continuous Delivery/Deployment):**
- **Continuous Delivery** - Ready to deploy (manual approval)
- **Continuous Deployment** - Automatically deploy to production

**CI/CD Pipeline stages:**

**1. Source/Trigger:**
- Developer commits code to Git
- Triggers pipeline automatically
- Tools: GitHub, GitLab, Bitbucket

**2. Build:**
- Compile source code
- Resolve dependencies
- Create artifacts (JAR, Docker image)
- Tools: Maven, Gradle

```bash
mvn clean package
```

**3. Test:**
- Run automated tests
- Unit tests, integration tests
- Code coverage analysis
- Fail pipeline if tests fail

```bash
mvn test
```

**4. Code Quality:**
- Static code analysis
- Security scanning
- Code coverage check
- Tools: SonarQube, Checkstyle

**5. Build Docker Image:**
- Create container image
- Tag with version
- Push to registry

```bash
docker build -t myapp:${VERSION} .
docker push myapp:${VERSION}
```

**6. Deploy to Staging:**
- Deploy to staging environment
- Run smoke tests
- Integration testing

**7. Approval (Optional):**
- Manual approval gate
- Review deployment
- Required for production

**8. Deploy to Production:**
- Deploy to production
- Blue-green or canary deployment
- Monitor metrics

**9. Post-Deployment:**
- Run smoke tests
- Monitor logs and metrics
- Send notifications

**Example - Jenkins pipeline:**

```groovy
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Code Quality') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        
        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp:${BUILD_NUMBER} .'
                sh 'docker push myapp:${BUILD_NUMBER}'
            }
        }
        
        stage('Deploy to Staging') {
            steps {
                sh 'kubectl apply -f k8s/staging/'
            }
        }
        
        stage('Approval') {
            steps {
                input 'Deploy to production?'
            }
        }
        
        stage('Deploy to Production') {
            steps {
                sh 'kubectl apply -f k8s/production/'
            }
        }
    }
    
    post {
        success {
            slackSend message: "Deployment successful!"
        }
        failure {
            slackSend message: "Deployment failed!"
        }
    }
}
```

**Popular CI/CD tools:**

- **Jenkins** - Open source, highly customizable
- **GitLab CI** - Integrated with GitLab
- **GitHub Actions** - Integrated with GitHub
- **CircleCI** - Cloud-based
- **AWS CodePipeline** - AWS native
- **Azure DevOps** - Microsoft ecosystem

**Best practices:**

1. **Keep pipeline fast** (< 10 minutes)
2. **Fail fast** (run quick tests first)
3. **Automate everything** (no manual steps)
4. **Use version control** for pipeline config
5. **Monitor pipeline metrics** (success rate, duration)
6. **Secure secrets** (use vault, not hardcode)
7. **Parallel execution** where possible

**Benefits:**

- Faster releases
- Fewer bugs in production
- Consistent deployments
- Quick rollback
- Developer productivity

**Remember:** CI/CD = automate build → test → deploy. Catch issues early, deploy frequently.

# ✅ 21) Leadership, Code Review & Architecture Decisions - 10 Questions

## 282. How do you conduct effective code reviews?

**Answer:**

I follow a structured approach to code reviews:

- **Review within 24 hours** - Quick feedback keeps momentum
- **Use a checklist** - Functionality, readability, tests, security, performance
- **Focus on the code, not the person** - Be constructive and respectful
- **Ask questions instead of commanding** - "Have you considered...?" vs "Change this"
- **Praise good work** - Acknowledge clever solutions and improvements
- **Automate what you can** - Use linters, formatters, and static analysis tools
- **Keep reviews small** - Max 400 lines, easier to review thoroughly
- **Test the code if needed** - Pull and run locally for complex changes

**Key principle:** Code reviews are for learning, not just finding bugs.

---

## 283. What do you look for in code reviews?

**Answer:**

I check these key areas in order:

1. **Functionality** - Does it solve the problem correctly?
2. **Tests** - Are there unit tests? Do they cover edge cases?
3. **Security** - SQL injection, XSS, authentication, authorization issues
4. **Code readability** - Clear naming, proper structure, not too complex
5. **Design patterns** - SOLID principles, appropriate patterns used
6. **Performance** - N+1 queries, unnecessary loops, memory leaks
7. **Error handling** - Proper exception handling and logging
8. **Documentation** - Complex logic explained, API docs updated
9. **Code duplication** - DRY principle followed
10. **Standards compliance** - Follows team coding standards

**Red flags:** Hardcoded credentials, missing error handling, no tests, overly complex methods.

---

## 284. How do you make architectural decisions?

**Answer:**

I use a systematic approach:

1. **Understand the problem** - Gather requirements, constraints, and scale expectations
2. **Consider multiple options** - List 2-3 viable solutions with pros/cons
3. **Evaluate trade-offs** - Performance vs complexity, cost vs scalability
4. **Use ADR (Architecture Decision Records)** - Document the decision, context, and rationale
5. **Involve the team** - Get input from senior developers and stakeholders
6. **Prototype if needed** - Build POC for critical or risky decisions
7. **Consider future impact** - Scalability, maintainability, team expertise
8. **Review past decisions** - Learn from what worked and what didn't

**Key factors:** Business needs, technical constraints, team skills, time to market, total cost of ownership.

---

## 285. What factors do you consider when choosing a technology stack?

**Answer:**

I evaluate these critical factors:

1. **Business requirements** - Does it meet functional and non-functional needs?
2. **Team expertise** - Can the team learn it quickly? Is support available?
3. **Community and ecosystem** - Active community, good documentation, libraries
4. **Performance and scalability** - Can it handle expected load and growth?
5. **Cost** - Licensing, infrastructure, training, maintenance costs
6. **Maturity and stability** - Production-ready? Long-term support?
7. **Integration capabilities** - Works with existing systems?
8. **Hiring availability** - Can we find developers with these skills?
9. **Vendor lock-in** - Can we migrate if needed?
10. **Security and compliance** - Meets security standards and regulations?

**Example:** Choosing Spring Boot over Node.js because team has Java expertise, enterprise support needed, and strong integration with existing Java systems.

---

## 286. How do you balance technical debt vs new features?

**Answer:**

I use the **70-20-10 rule**:

- **70%** - New features and business requirements
- **20%** - Technical debt and refactoring
- **10%** - Innovation and learning

**My approach:**

1. **Track technical debt** - Maintain a backlog with priority and impact
2. **Classify by severity** - Critical (blocks development), High (slows team), Medium (minor impact)
3. **Address critical debt immediately** - Security issues, production bugs
4. **Bundle with features** - Refactor related code when adding new features
5. **Dedicated sprints** - Every 4-5 sprints, allocate one for debt reduction
6. **Communicate impact** - Show stakeholders how debt affects velocity and quality
7. **Prevent new debt** - Code reviews, standards, automated checks

**Key principle:** Technical debt is like financial debt - small regular payments prevent bankruptcy.

---

## 287. How do you handle legacy code refactoring?

**Answer:**

I follow the **Strangler Fig Pattern** approach:

1. **Understand before changing** - Read code, add comments, create diagrams
2. **Add tests first** - Write characterization tests to preserve behavior
3. **Refactor incrementally** - Small, safe changes, not big rewrites
4. **Use feature flags** - Deploy new code alongside old, switch gradually
5. **Extract and isolate** - Move logic to new classes/services
6. **Document as you go** - Explain complex parts for the team
7. **Monitor after changes** - Watch metrics, logs, errors in production
8. **Get team buy-in** - Explain benefits, share progress

**Strategy:**
- **Boy Scout Rule** - Leave code better than you found it
- **Refactor during feature work** - Don't create separate refactoring projects
- **Focus on high-value areas** - Refactor code that changes frequently

**Avoid:** Big bang rewrites - they usually fail.

---

## 288. How do you ensure coding standards in the team?

**Answer:**

I implement multiple layers of enforcement:

**1. Automated Tools (First Line of Defense):**
- **Linters** - Checkstyle, PMD, SonarQube
- **Formatters** - Google Java Format, Prettier
- **Pre-commit hooks** - Block commits that violate standards
- **CI/CD checks** - Fail builds on violations

**2. Documentation:**
- **Coding guidelines document** - Clear, concise, with examples
- **README templates** - Standardized project documentation
- **Code examples** - Reference implementations

**3. Team Practices:**
- **Code reviews** - Enforce standards during reviews
- **Pair programming** - Share knowledge and standards
- **Onboarding checklist** - Ensure new members learn standards
- **Regular discussions** - Review and update standards quarterly

**4. IDE Configuration:**
- **Shared IDE settings** - Import formatting, inspection rules
- **EditorConfig** - Consistent formatting across IDEs

**Key principle:** Make it easy to do the right thing, hard to do the wrong thing.

---

## 289. How do you mentor junior developers?

**Answer:**

I use a structured mentoring approach:

**1. Build Foundation:**
- **Pair programming** - Work together on real tasks
- **Code reviews with explanations** - Teach through feedback
- **Assign graduated tasks** - Start simple, increase complexity
- **Encourage questions** - Create safe environment for learning

**2. Teach Problem-Solving:**
- **Don't give answers immediately** - Guide them to find solutions
- **Explain the "why"** - Not just "how", but reasoning behind decisions
- **Share debugging techniques** - Teach how to troubleshoot
- **Review their approach** - Discuss alternative solutions

**3. Provide Resources:**
- **Curated learning materials** - Books, courses, articles
- **Internal documentation** - Architecture docs, best practices
- **Regular 1-on-1s** - Weekly check-ins for guidance
- **Set clear goals** - Define learning objectives and milestones

**4. Build Confidence:**
- **Celebrate wins** - Acknowledge progress and achievements
- **Normalize mistakes** - Share your own learning experiences
- **Give ownership** - Let them lead small features
- **Provide constructive feedback** - Focus on growth, not criticism

**Key principle:** A good mentor creates more mentors, not followers.

---

## 290. How do you handle technical disagreements in the team?

**Answer:**

I follow a collaborative resolution process:

**1. Listen First:**
- **Understand all perspectives** - Let everyone explain their viewpoint
- **Ask clarifying questions** - Ensure you understand the reasoning
- **Stay objective** - Focus on technical merits, not personalities

**2. Evaluate Options:**
- **List pros and cons** - Create comparison matrix
- **Consider data** - Use metrics, benchmarks, past experiences
- **Identify trade-offs** - Performance vs complexity, cost vs benefit
- **Check alignment with goals** - Which option serves business needs better?

**3. Decide and Move Forward:**
- **Build consensus** - Try to find common ground
- **Use ADR (Architecture Decision Record)** - Document the decision
- **Disagree and commit** - If no consensus, senior/architect decides, team commits
- **Time-box discussions** - Don't let debates drag on indefinitely
- **Prototype if needed** - Build POC to test assumptions

**4. Learn and Adapt:**
- **Review decisions later** - Was it the right choice?
- **No blame culture** - Focus on learning, not who was right

**Key principle:** Strong opinions, loosely held. Be willing to change your mind with new information.

---

## 291. What is your approach to technical documentation?

**Answer:**

I follow the **Documentation as Code** philosophy:

**1. Types of Documentation:**

**Code-Level:**
- **Self-documenting code** - Clear naming, simple logic
- **Javadoc for public APIs** - Document what, not how
- **README per module** - Setup, usage, examples
- **Inline comments** - Only for complex/non-obvious logic

**Architecture-Level:**
- **Architecture Decision Records (ADR)** - Why decisions were made
- **System diagrams** - C4 model (Context, Container, Component, Code)
- **API documentation** - Swagger/OpenAPI specs
- **Runbooks** - Deployment, troubleshooting, incident response

**2. Documentation Principles:**
- **Keep it close to code** - Store in Git, version controlled
- **Keep it updated** - Review during code reviews
- **Keep it simple** - Short, scannable, with examples
- **Keep it relevant** - Delete outdated docs

**3. Tools I Use:**
- **Markdown** - For README, ADRs, guides
- **Swagger/OpenAPI** - For REST API documentation
- **Confluence/Wiki** - For architecture and process docs
- **Mermaid/PlantUML** - For diagrams as code
- **Javadoc** - For code-level documentation

**4. When to Document:**
- **Complex algorithms** - Explain the approach
- **Architectural decisions** - Why this pattern/technology
- **Setup and deployment** - How to run locally and deploy
- **Troubleshooting guides** - Common issues and solutions
- **API contracts** - Request/response formats, error codes

**Key principle:** Good documentation is like good code - clear, concise, and maintainable. If you can't maintain it, don't write it.

# ✅ 22) Behavioral & Team Handling - 10 Questions

## 292. Tell me about a challenging production bug you fixed.

**Answer:**
We had a memory leak in production causing OutOfMemoryError every 3 days. I analyzed heap dumps using MAT (Memory Analyzer Tool) and found ThreadLocal variables weren't being cleaned up in our thread pool. The issue was we stored user session data in ThreadLocal but never called remove(). I fixed it by adding a finally block to clear ThreadLocal after each request, and implemented a servlet filter to ensure cleanup. After deployment, memory usage stabilized and the issue never recurred.

**Key Points to Remember:**
- Describe the problem clearly
- Explain your investigation approach
- Show technical depth (tools used)
- Highlight the solution and results

---

## 293. How do you handle disagreements with team members?

**Answer:**
I believe in data-driven discussions. When disagreements arise, I focus on the technical merits rather than personal opinions. I listen to understand their perspective first, then present my viewpoint with concrete examples, performance metrics, or proof of concepts. If we still disagree, I suggest creating a small spike or prototype to test both approaches. Ultimately, I'm open to being wrong - the best solution should win, not egos. If needed, we escalate to the tech lead with both options documented.

**Key Points to Remember:**
- Listen first, speak second
- Use data and facts, not emotions
- Be willing to compromise
- Focus on team goals, not personal wins

---

## 294. How do you prioritize tasks when everything is urgent?

**Answer:**
I use a three-step approach: First, I assess business impact - what affects customers or revenue most? Second, I evaluate technical dependencies - what blocks other work? Third, I communicate with stakeholders to align on priorities. I categorize tasks using the Eisenhower Matrix: urgent-important gets done first, important-not-urgent gets scheduled, urgent-not-important gets delegated, and neither gets dropped. I also timebox my day - 70% planned work, 30% buffer for urgent issues. Regular standup communication keeps everyone aligned.

**Key Points to Remember:**
- Business impact first
- Communicate with stakeholders
- Use prioritization frameworks
- Leave buffer time for emergencies

---

## 295. How do you mentor junior developers effectively?

**Answer:**
I follow a structured approach: First, I pair program with them on real tasks, explaining my thought process. Second, I assign them progressively challenging tasks with clear acceptance criteria. Third, I conduct thorough code reviews with explanations, not just corrections - I ask "why did you choose this approach?" to encourage critical thinking. Fourth, I share resources like books, articles, and courses. Fifth, I have weekly one-on-ones to discuss their growth and challenges. Most importantly, I create a safe environment where asking questions is encouraged, not judged.

**Key Points to Remember:**
- Pair programming for hands-on learning
- Progressive task complexity
- Educational code reviews
- Regular feedback sessions
- Safe learning environment

---

## 296. Describe a time when you had to meet a tight deadline.

**Answer:**
We had to deliver a payment integration feature in 2 weeks instead of planned 4 weeks due to a business commitment. I immediately broke down the work into must-haves and nice-to-haves. We focused on core payment flow first, deferring features like payment history and refunds to phase 2. I coordinated daily standups to identify blockers early. We used feature flags to deploy incrementally without waiting for everything to be perfect. I also negotiated with QA to do parallel testing instead of sequential. We delivered on time with 95% test coverage on critical paths. The deferred features were added in the next sprint.

**Key Points to Remember:**
- Break down scope (MVP approach)
- Daily communication
- Incremental delivery
- Negotiate and collaborate
- Deliver quality, not just speed

---

## 297. How do you handle production incidents and post-mortems?

**Answer:**
During incidents, I follow the incident response protocol: First, acknowledge and assess severity. Second, form a war room with relevant team members. Third, focus on mitigation first, root cause later - get the system stable. Fourth, communicate status updates every 30 minutes to stakeholders. After resolution, I conduct a blameless post-mortem within 48 hours. We document what happened, why it happened, what we did, and action items to prevent recurrence. I focus on process improvements, not blaming individuals. We track action items in JIRA and review them in retrospectives.

**Key Points to Remember:**
- Mitigation first, investigation later
- Regular stakeholder communication
- Blameless post-mortems
- Document and learn
- Track preventive actions

---

## 298. How do you stay updated with new technologies?

**Answer:**
I use a multi-channel approach: First, I follow tech blogs like Baeldung, DZone, and Spring.io. Second, I'm active on GitHub and contribute to open-source projects. Third, I attend local Java User Group meetups and conferences like SpringOne. Fourth, I dedicate 2-3 hours weekly for hands-on learning - I build small projects with new technologies. Fifth, I follow thought leaders on Twitter and LinkedIn. Sixth, I take online courses on Udemy or Pluralsight for deep dives. Most importantly, I apply new learnings in my current projects through spikes and proof of concepts.

**Key Points to Remember:**
- Multiple learning sources
- Hands-on practice, not just reading
- Community engagement
- Dedicated learning time
- Apply knowledge in real projects

---

## 299. Tell me about a project you're most proud of.

**Answer:**
I led the migration of a monolithic e-commerce application to microservices architecture. The monolith had 500K lines of code, 20-minute build times, and frequent production issues. Over 6 months, I designed the microservices architecture using Domain-Driven Design, identifying 8 bounded contexts. We used Spring Boot, Docker, Kubernetes, and Kafka. I implemented circuit breakers with Resilience4j, distributed tracing with Zipkin, and centralized logging with ELK stack. The result: deployment time reduced from 2 hours to 15 minutes, system availability improved from 95% to 99.5%, and team velocity increased by 40%. Most importantly, we could now scale services independently based on load.

**Key Points to Remember:**
- Describe the challenge/context
- Your role and decisions
- Technologies used
- Measurable results
- Business impact

---

## 300. How do you handle technical debt in large codebases?

**Answer:**
I treat technical debt like financial debt - it needs active management. First, I maintain a technical debt backlog with clear documentation of each debt item, its impact, and effort to fix. Second, I use the Boy Scout Rule - leave code better than you found it. Every sprint, we allocate 20% capacity for technical debt - this is non-negotiable. Third, I prioritize debt that impacts velocity or causes frequent bugs. Fourth, I make technical debt visible to stakeholders using metrics like code coverage, cyclomatic complexity, and bug frequency. Fifth, during planning, I advocate for refactoring when adding new features to debt-heavy areas. The key is balance - not all debt needs immediate fixing.

**Key Points to Remember:**
- Track and document debt
- Allocate regular time (20% rule)
- Prioritize by impact
- Make it visible to stakeholders
- Balance with feature delivery

---

## 301. How do you ensure knowledge sharing in the team?

**Answer:**
I use multiple mechanisms: First, we conduct weekly tech talks where team members present on topics they learned or implemented. Second, we maintain comprehensive documentation in Confluence - architecture decisions, runbooks, and troubleshooting guides. Third, we do regular code reviews with knowledge transfer as a goal, not just bug finding. Fourth, we practice pair programming and mob programming for complex features. Fifth, we maintain a team wiki with FAQs and common issues. Sixth, we record important technical discussions and decisions in ADRs (Architecture Decision Records). Finally, we have a Slack channel for quick knowledge sharing and questions. The goal is to avoid knowledge silos.

**Key Points to Remember:**
- Regular tech talks
- Comprehensive documentation
- Educational code reviews
- Pair/mob programming
- Architecture Decision Records (ADRs)
- Multiple communication channels
