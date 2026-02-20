# ✅ 1) Core Java (Basics and Advanced)

## 0. What is Java and what are its key features?

Java is a high-level, object-oriented programming language designed for platform independence and enterprise application development. It's known for its "write once, run anywhere" philosophy.

**Key Features:**
- **Platform Independent:** Runs on any system with JVM
- **Object-Oriented:** Based on objects and classes
- **Simple and Secure:** Easy syntax, built-in security features
- **Robust:** Strong memory management and exception handling
- **Multithreaded:** Supports concurrent programming
- **Automatic Memory Management:** Garbage collection handles memory

Java compiles to bytecode that runs on the Java Virtual Machine, making it portable across different operating systems.

## 0. Explain the difference between JDK, JRE, and JVM.

**JVM (Java Virtual Machine)** is the runtime environment that executes Java bytecode on any platform. It’s responsible for **memory management, garbage collection, and execution**.

**JRE (Java Runtime Environment)** includes the **JVM plus core libraries and classes** needed to run Java applications. It doesn’t have tools for development.

**JDK (Java Development Kit)** includes **JRE plus development tools** like `javac` and `jar` for compiling and building Java programs.


```
JDK = JRE + Development Tools (javac, javadoc, jar, etc.)
JRE = JVM + Core Libraries (java.lang, java.util, etc.)
JVM = Runtime execution environment
```

## 0. What are the main principles of Object-Oriented Programming?

Object-Oriented Programming is based on four fundamental principles that promote code reusability, maintainability, and modularity.

**Four OOP Principles:**

- **Encapsulation**: is the process of **wrapping data (variables) and methods (functions) together into a single unit called a class**, and **restricting direct access to the data** using access modifiers (`private`, `protected`, `public`).

- **Inheritance**: The ability of a class to inherit the properties and behaviors of another class. And  class (child/subclass) access the properties and behaviors of another class (parent/superclass)** using the `extends` keyword.

- **Polymorphism**: means **"many forms"**. It allows the same method or object to behave differently in different situations. (e.g., method overloading, method overriding).

- **Abstraction**: is the concept of **hiding internal implementation details and showing only essential features to the user**.

## 0. What are data types in Java?

**Answer:**
There is two type of data Primitive Data and Non-Primitive Data

**8 Primitive Data :**
- **byte** - 8-bit integer (-128 to 127)
- **short** - 16-bit integer (-32,768 to 32,767)
- **int** - 32-bit integer (most commonly used)
- **long** - 64-bit integer (add 'L' suffix)
- **float** - 32-bit decimal (add 'f' suffix)
- **double** - 64-bit decimal (default for decimals)
- **char** - 16-bit Unicode character
- **boolean** - true or false

**8 Non-Primitive Data :**
- **String** - Used to store text
- **Array** - Used to store multiple values
- **Class** - Blueprint for objects
- **Interface** - Defines method structure for classes

**Example:**
```java
int age = 25;
double salary = 50000.50;
char grade = 'A';
boolean isActive = true;
long population = 1000000L;
```

## 1. Explain `==` vs `equals()` vs `hashCode()` contract.

**Answer:**
* `==` compares **memory references** for objects and **actual values** for primitive types.
* `equals()` compares the **logical or content equality** of objects.
* The `hashCode()` contract states that if two objects are equal according to `equals()`, they **must have the same hash code**. However, two objects having the same hash code **are not necessarily equal**.

```java
String s1 = new String("hello");
String s2 = new String("hello");
String s3 = s1;

System.out.println(s1 == s2);        // false (different objects)
System.out.println(s1 == s3);        // true (same reference)
System.out.println(s1.equals(s2));   // true (same content)
System.out.println(s1.hashCode() == s2.hashCode()); // true
```

## 2. Why is String immutable? What are the benefits?

**Answer:**
* Because its internal character array is **private and final**, and it has **no setters** to modify the value.

### Benefits:
* **Thread-safe** (no synchronization needed).
* Uses **String Pool** → saves memory.
* More **secure** (safe for passwords, URLs, etc.).
* Safe to use as **HashMap key** (hash code does not change).


```java
String str = "Java";
str.concat(" Programming"); // Creates new String, doesn't modify original
System.out.println(str);     // Still prints "Java"

String modified = str.concat(" Programming");
System.out.println(modified); // Prints "Java Programming"
```

## 3. How does HashMap work internally in Java 8+?

**Answer:**

HashMap uses an array of buckets where each bucket can hold a linked list or tree structure. It calculates the hash code of the key, applies a hash function to determine the bucket index, and stores the key-value pair. In Java 8+, when a bucket has more than 8 entries, it converts from linked list to a balanced tree (red-black tree) for better performance, reducing lookup time from O(n) to O(log n).

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("John", 25);  // hash("John") -> bucket index -> store entry

// Internal process:
// 1. hash = key.hashCode()
// 2. index = (n-1) & hash  (n = array length)
// 3. Store at array[index]
```

## 4. What happens if two objects have the same hashCode?

**Answer:**

This is called a hash collision. **HashMap handles it by storing multiple entries in the same bucket using a linked list or tree structure. When retrieving, it uses `equals()` to find the exact match among entries with the same hash code.

```java
class Person {
    String name;
    public int hashCode() { return 1; } // All return same hash
    public boolean equals(Object o) {
        return this.name.equals(((Person)o).name);
    }
}

HashMap<Person, String> map = new HashMap<>();
map.put(new Person("Alice"), "Engineer");
map.put(new Person("Bob"), "Doctor"); // Same bucket, different entry
```

## 5. Difference between abstract class and interface (Java 8+).

**Answer:**

Abstract classes can have constructors, instance variables, and concrete methods with implementation. Interfaces (Java 8+) can have default and static methods but no constructors or instance variables. A class can implement multiple interfaces but extend only one abstract class. Use abstract classes for "is-a" relationships with shared state, interfaces for "can-do" capabilities.

```java
abstract class Animal {
    String name;
    abstract void sound();
    void sleep() { System.out.println("Sleeping"); }
}

interface Flyable {
    default void fly() { System.out.println("Flying"); }
    static void glide() { System.out.println("Gliding"); }
}

class Bird extends Animal implements Flyable {
    void sound() { System.out.println("Chirp"); }
}
```

## 6. Explain method overloading vs overriding with covariant return types.

**Answer:**
Overloading is having multiple methods with the same name but different parameters in the same class (compile-time polymorphism). Overriding is redefining a parent class method in a child class with the same signature (runtime polymorphism). Covariant return types allow an overriding method to return a subtype of the original return type.

```java
class Parent {
    Animal getAnimal() { return new Animal(); }
    void print(int x) { }
    void print(String s) { } // Overloading
}

class Child extends Parent {
    @Override
    Dog getAnimal() { return new Dog(); } // Covariant return (Dog extends Animal)
}
```

## 7. What are default and static methods in interfaces?

**Answer:**
Default methods provide a default implementation in interfaces, allowing you to add new methods without breaking existing implementations. Static methods belong to the interface itself and can be called without an instance. Both were introduced in Java 8 to enable interface evolution.

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle starting");
    }
    
    static void service() {
        System.out.println("Service all vehicles");
    }
}

class Car implements Vehicle {
    // Can use default start() or override it
}

Vehicle.service(); // Call static method
```

## 8. Explain autoboxing/unboxing and performance implications.

**Answer:**
Autoboxing automatically converts primitives to wrapper objects, unboxing does the reverse. Performance implications include memory overhead (wrapper objects consume more memory), potential NullPointerException during unboxing, and slower execution due to object creation and garbage collection.

```java
// Autoboxing
Integer obj = 10; // int -> Integer (automatic)

// Unboxing
int primitive = obj; // Integer -> int (automatic)

// Performance issue
Integer sum = 0;
for(int i = 0; i < 1000; i++) {
    sum += i; // Unbox, add, autobox repeatedly - slow!
}
```

## 9. Difference between `String`, `StringBuilder`, `StringBuffer`.

**Answer:**
String is immutable and thread-safe. StringBuilder is mutable, not thread-safe, and faster for single-threaded string manipulation. StringBuffer is mutable, thread-safe (synchronized methods), and slower than StringBuilder. Use String for fixed text, StringBuilder for single-threaded concatenation, StringBuffer for multi-threaded scenarios.

```java
String str = "Hello";
str += " World"; // Creates new String object each time

StringBuilder sb = new StringBuilder("Hello");
sb.append(" World"); // Modifies same object - faster

StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World"); // Thread-safe but slower
```

## 10. How does String pool and `intern()` method work?

**Answer:**
The String pool is a special memory area in the heap where Java stores string literals to optimize memory. When you create a string literal, Java checks the pool first and reuses existing strings. The `intern()` method manually adds a string to the pool or returns the existing pooled reference.

```java
String s1 = "hello";        // Goes to pool
String s2 = "hello";        // Reuses from pool
System.out.println(s1 == s2); // true

String s3 = new String("hello"); // Heap, not pool
System.out.println(s1 == s3);    // false

String s4 = s3.intern();    // Returns pool reference
System.out.println(s1 == s4);    // true
```

## 11. Explain `final`, `finally`, `finalize`.

**Answer:**
`final` is a keyword making variables constant, methods non-overridable, and classes non-inheritable. `finally` is a block that always executes after try-catch, used for cleanup. `finalize()` is a deprecated method called by garbage collector before object destruction, unreliable for cleanup.

```java
final int MAX = 100; // Constant

try {
    int x = 10/0;
} catch(Exception e) {
    System.out.println("Error");
} finally {
    System.out.println("Always executes"); // Cleanup code
}

class Resource {
    @Deprecated
    protected void finalize() {
        // Called before GC (unreliable, use try-with-resources instead)
    }
}
```

## 12. What is the difference between shallow copy and deep copy?

**Answer:**
Shallow copy creates a new object but copies references to nested objects, so both original and copy share the same nested objects. Deep copy creates a new object and recursively copies all nested objects, creating completely independent copies. Modifying nested objects in shallow copy affects both, while deep copy remains independent.

```java
class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;
    
    // Shallow copy
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Copies reference to address
    }
    
    // Deep copy
    public Person deepCopy() {
        Person copy = new Person();
        copy.name = this.name;
        copy.address = new Address(this.address.city); // New Address object
        return copy;
    }
}

Person p1 = new Person();
p1.address = new Address("NYC");

Person p2 = (Person) p1.clone(); // Shallow
p2.address.city = "LA"; // Affects p1 too!

Person p3 = p1.deepCopy(); // Deep
p3.address.city = "Boston"; // p1 unaffected
```

# ✅ 2) Collections Framework & Performance Tuning

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

# ✅ 3) Java Memory Model & Garbage Collection

### 25. Explain JVM memory structure (Heap, Stack, Metaspace).

**Answer:**
The JVM memory is divided into several areas. The Heap is where all objects and instance variables are stored, shared across all threads, and managed by the garbage collector. The Stack stores method frames, local variables, and partial results for each thread - each thread has its own stack. Metaspace replaced PermGen in Java 8 and stores class metadata, static variables, and constant pool. Unlike PermGen, Metaspace uses native memory and can grow dynamically. The heap is further divided into Young Generation and Old Generation for efficient garbage collection.

**Example:**
```java
public class MemoryExample {
    static int staticVar = 100;  // Metaspace
    
    public void method() {
        int localVar = 10;       // Stack
        String str = new String("Hello"); // "Hello" in Heap, str reference in Stack
        
        // Object created in Heap (Young Generation - Eden space)
        Employee emp = new Employee();
    }
}

// JVM flags to configure memory
// -Xms512m (initial heap)
// -Xmx2g (max heap)
// -XX:MetaspaceSize=128m
```

---

### 26. What are Young Generation (Eden, Survivor) and Old Generation?

**Answer:**
The Heap is divided into Young Generation and Old Generation for generational garbage collection. Young Generation consists of Eden space and two Survivor spaces (S0 and S1). New objects are allocated in Eden. When Eden fills up, Minor GC occurs, moving surviving objects to a Survivor space. Objects that survive multiple GC cycles are promoted to Old Generation. This design is based on the observation that most objects die young. Young Generation uses fast collection algorithms, while Old Generation uses more thorough but slower algorithms.

**Example:**
```java
public class GenerationExample {
    public static void main(String[] args) {
        // Objects created in Eden space
        for (int i = 0; i < 1000; i++) {
            String temp = new String("temp" + i); // dies quickly
        }
        
        // Long-lived object - eventually moves to Old Gen
        List<String> cache = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            cache.add("data" + i);
        }
        
        // After multiple GC cycles, cache moves to Old Generation
    }
}

// JVM flags
// -XX:NewRatio=2 (Old:Young = 2:1)
// -XX:SurvivorRatio=8 (Eden:Survivor = 8:1)
```

---

### 27. Explain different GC algorithms (G1, CMS, ZGC, Parallel).

**Answer:**
Parallel GC uses multiple threads for both Young and Old generation collection, maximizing throughput but causing stop-the-world pauses. CMS (Concurrent Mark Sweep) performs most Old Gen collection concurrently with application threads, reducing pause times but using more CPU. G1 (Garbage First) divides heap into regions, collecting regions with most garbage first, balancing throughput and latency with predictable pause times. ZGC is a low-latency collector with pause times under 10ms, using colored pointers and load barriers, suitable for large heaps. Choose based on your latency and throughput requirements.

**Example:**
```java
// Enable different GC algorithms via JVM flags

// Parallel GC (default in Java 8) - high throughput
// -XX:+UseParallelGC

// G1 GC (default in Java 9+) - balanced
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200

// CMS (deprecated in Java 9) - low latency
// -XX:+UseConcMarkSweepGC

// ZGC (Java 11+) - ultra-low latency
// -XX:+UseZGC
// -XX:ZCollectionInterval=5

public class GCExample {
    public static void main(String[] args) {
        // Application code - GC runs automatically
        List<byte[]> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new byte[1024 * 1024]); // 1MB each
        }
    }
}
```

---

### 28. What is Minor GC vs Major GC vs Full GC?

**Answer:**
Minor GC occurs in Young Generation when Eden space fills up. It's fast because most objects in Young Gen are short-lived and can be quickly collected. Surviving objects move to Survivor spaces or get promoted to Old Generation. Major GC cleans the Old Generation and is slower because objects there are long-lived. Full GC collects both Young and Old generations, causing longer stop-the-world pauses. Full GC is triggered when Old Gen is full or explicitly called via System.gc(). Frequent Full GCs indicate memory issues or improper heap sizing.

**Example:**
```java
public class GCTypesExample {
    public static void main(String[] args) {
        // Triggers Minor GC frequently
        for (int i = 0; i < 1000000; i++) {
            String temp = new String("temp"); // short-lived
        }
        
        // Long-lived objects may trigger Major GC
        List<byte[]> longLived = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            longLived.add(new byte[1024 * 1024]);
        }
        
        // Avoid explicit GC calls
        // System.gc(); // triggers Full GC - not recommended
    }
}

// Monitor GC with flags
// -XX:+PrintGCDetails
// -XX:+PrintGCTimeStamps
// -Xlog:gc*
```

---

### 29. How do you identify and fix memory leaks?

**Answer:**
Memory leaks occur when objects are unintentionally retained in memory. Common causes include unclosed resources, static collections holding references, listeners not removed, and ThreadLocal variables not cleared. To identify leaks, monitor heap usage over time - if it continuously grows without dropping after GC, you likely have a leak. Use heap dumps to analyze which objects consume memory. Fix by ensuring proper resource cleanup, removing listeners, clearing collections, using weak references where appropriate, and cleaning up ThreadLocal variables.

**Example:**
```java
// Memory leak examples and fixes

// BAD - Static collection grows indefinitely
public class LeakyCache {
    private static Map<String, Object> cache = new HashMap<>();
    
    public void addToCache(String key, Object value) {
        cache.put(key, value); // never removed
    }
}

// GOOD - Use bounded cache or weak references
public class FixedCache {
    private Map<String, Object> cache = new WeakHashMap<>();
    
    public void addToCache(String key, Object value) {
        cache.put(key, value); // auto-removed when key is GC'd
    }
}

// BAD - Unclosed resources
public void readFile() throws IOException {
    FileInputStream fis = new FileInputStream("file.txt");
    // forgot to close
}

// GOOD - Try-with-resources
public void readFile() throws IOException {
    try (FileInputStream fis = new FileInputStream("file.txt")) {
        // auto-closed
    }
}
```

---

### 30. What tools do you use for memory analysis?

**Answer:**
For memory analysis, I use JVisualVM for real-time monitoring of heap, threads, and CPU usage. Eclipse MAT (Memory Analyzer Tool) is excellent for analyzing heap dumps to find memory leaks and large object retention. JProfiler and YourKit are commercial profilers offering advanced features. For command-line analysis, jmap generates heap dumps, jstat monitors GC statistics, and jcmd provides diagnostic commands. In production, I use APM tools like New Relic or Dynatrace. Java Flight Recorder provides low-overhead profiling data that can be analyzed with JMC (Java Mission Control).

**Example:**
```bash
# Command-line tools

# Generate heap dump
jmap -dump:live,format=b,file=heap.bin <pid>

# View heap histogram
jmap -histo <pid>

# Monitor GC statistics
jstat -gc <pid> 1000

# Get thread dump
jstack <pid> > threads.txt

# JVM diagnostic commands
jcmd <pid> GC.heap_info
jcmd <pid> VM.native_memory summary
```

```java
// Programmatic heap dump
import com.sun.management.HotSpotDiagnosticMXBean;
import java.lang.management.ManagementFactory;

public class HeapDumper {
    public static void dumpHeap(String filePath) throws Exception {
        HotSpotDiagnosticMXBean mxBean = ManagementFactory
            .getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        mxBean.dumpHeap(filePath, true);
    }
}
```

---

### 31. Explain different types of `OutOfMemoryError`.

**Answer:**
Java throws different OutOfMemoryError types for various scenarios. "Java heap space" occurs when the heap is full and GC can't free enough memory - increase heap size or fix memory leaks. "GC overhead limit exceeded" means the JVM spends too much time in GC with little memory recovered. "Metaspace" happens when class metadata exceeds Metaspace limit - increase Metaspace or reduce class loading. "Unable to create new native thread" occurs when OS can't create more threads - reduce thread count or increase OS limits. "Direct buffer memory" relates to NIO direct buffers exceeding limit.

**Example:**
```java
// Different OOM scenarios

// 1. Heap space OOM
public class HeapOOM {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]); // OutOfMemoryError: Java heap space
        }
    }
}
// Fix: -Xmx2g

// 2. Metaspace OOM
public class MetaspaceOOM {
    public static void main(String[] args) {
        while (true) {
            // Dynamically generate classes
            // OutOfMemoryError: Metaspace
        }
    }
}
// Fix: -XX:MaxMetaspaceSize=512m

// 3. Thread creation OOM
public class ThreadOOM {
    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                try { Thread.sleep(1000000); } catch (Exception e) {}
            }).start(); // OutOfMemoryError: unable to create new native thread
        }
    }
}
// Fix: Reduce threads or increase OS limits
```

---

### 32. What are JVM tuning parameters (`-Xms`, `-Xmx`, `-XX`)?

**Answer:**
JVM tuning parameters control memory and performance. -Xms sets initial heap size, -Xmx sets maximum heap size - setting them equal avoids heap resizing overhead. -Xss sets thread stack size. -XX flags are advanced options: -XX:NewRatio controls Young to Old Gen ratio, -XX:MaxMetaspaceSize limits Metaspace, -XX:+UseG1GC selects garbage collector. For production, set -Xms equal to -Xmx for predictable performance, choose appropriate GC algorithm, enable GC logging, and tune based on application behavior. Monitor and adjust based on actual usage patterns.

**Example:**
```bash
# Common JVM tuning parameters

# Heap sizing
-Xms2g                    # Initial heap 2GB
-Xmx2g                    # Max heap 2GB (same as Xms)
-Xss512k                  # Thread stack size

# GC selection and tuning
-XX:+UseG1GC              # Use G1 collector
-XX:MaxGCPauseMillis=200  # Target pause time
-XX:NewRatio=2            # Old:Young = 2:1
-XX:SurvivorRatio=8       # Eden:Survivor = 8:1

# Metaspace
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=512m

# GC logging (Java 9+)
-Xlog:gc*:file=gc.log:time,level,tags

# Performance tuning
-XX:+UseStringDeduplication  # Reduce String memory
-XX:+AlwaysPreTouch          # Touch memory at startup
-XX:+DisableExplicitGC       # Ignore System.gc()

# Example startup command
java -Xms2g -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 \
     -Xlog:gc*:file=gc.log -jar myapp.jar
```

---

### 33. Explain strong, soft, weak, and phantom references.

**Answer:**
Strong references are normal references that prevent garbage collection - objects are never collected while strongly referenced. Soft references are collected only when memory is low, useful for memory-sensitive caches. Weak references don't prevent collection - objects are collected in the next GC cycle if only weakly referenced, used in WeakHashMap. Phantom references are enqueued after finalization but before memory reclamation, used for cleanup actions. Reference queues notify when references are cleared. Use soft references for caches, weak references for canonical mappings, and phantom references for resource cleanup.

**Example:**
```java
import java.lang.ref.*;

public class ReferenceExample {
    public static void main(String[] args) {
        // Strong reference - never GC'd while referenced
        Object strong = new Object();
        
        // Soft reference - GC'd when memory is low
        SoftReference<Object> soft = new SoftReference<>(new Object());
        Object obj1 = soft.get(); // may return null if GC'd
        
        // Weak reference - GC'd in next cycle
        WeakReference<Object> weak = new WeakReference<>(new Object());
        Object obj2 = weak.get(); // may return null after GC
        
        // Phantom reference - for cleanup actions
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(new Object(), queue);
        // phantom.get() always returns null
        
        // Cache using soft references
        Map<String, SoftReference<byte[]>> cache = new HashMap<>();
        cache.put("key", new SoftReference<>(new byte[1024]));
    }
}
```

---

### 34. How does G1 Garbage Collector work?

**Answer:**
G1 (Garbage First) divides the heap into equal-sized regions instead of contiguous Young and Old generations. Each region can be Eden, Survivor, or Old. G1 tracks live data in each region and prioritizes collecting regions with most garbage first, hence the name. It performs concurrent marking to identify live objects, then does mixed collections that collect both Young and Old regions. G1 aims to meet pause time goals by collecting only a subset of regions in each cycle. It uses remembered sets to track cross-region references, enabling parallel and incremental collection with predictable pause times.

**Example:**
```java
// G1 GC configuration
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200        # Target pause time
// -XX:G1HeapRegionSize=16m        # Region size (1-32MB)
// -XX:InitiatingHeapOccupancyPercent=45  # Start concurrent marking
// -XX:G1ReservePercent=10         # Reserve for evacuation
// -XX:ConcGCThreads=4             # Concurrent marking threads

public class G1Example {
    public static void main(String[] args) {
        // G1 works best with:
        // - Large heaps (> 6GB)
        // - Predictable pause time requirements
        // - Mixed object lifetimes
        
        List<Object> longLived = new ArrayList<>();
        
        for (int i = 0; i < 1000000; i++) {
            // Short-lived objects
            String temp = "temp" + i;
            
            // Some long-lived objects
            if (i % 1000 == 0) {
                longLived.add(new byte[1024]);
            }
        }
        
        // G1 efficiently handles this mixed workload
        // by collecting regions with most garbage first
    }
}

// Monitor G1 with:
// -Xlog:gc*,gc+heap=debug:file=gc.log
```

# ✅ 4) Multithreading & Concurrency

### 35. Explain thread lifecycle states.

**Answer:**
A thread in Java goes through six states: NEW when created but not started, RUNNABLE when executing or ready to execute, BLOCKED when waiting for a monitor lock, WAITING when waiting indefinitely for another thread, TIMED_WAITING when waiting for a specific time, and TERMINATED when execution completes. A thread moves from NEW to RUNNABLE when start() is called. It becomes BLOCKED when trying to enter a synchronized block. It enters WAITING when calling wait(), join(), or park(). TIMED_WAITING occurs with sleep(), wait(timeout), or join(timeout). Finally, it reaches TERMINATED when run() completes.

**Example:**
```java
public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized(ThreadStates.class) {
                try {
                    Thread.sleep(1000); // TIMED_WAITING
                } catch (InterruptedException e) {}
            }
        });
        
        System.out.println(thread.getState()); // NEW
        thread.start();
        System.out.println(thread.getState()); // RUNNABLE
        Thread.sleep(100);
        System.out.println(thread.getState()); // TIMED_WAITING
        thread.join();
        System.out.println(thread.getState()); // TERMINATED
    }
}
```

---

### 36. Difference between `start()` and `run()` methods.

**Answer:**
The start() method creates a new thread and invokes the run() method in that new thread, enabling concurrent execution. Calling run() directly executes the code in the current thread without creating a new thread, making it a normal method call. start() can only be called once per thread object, throwing IllegalThreadStateException if called again. run() can be called multiple times. Always use start() to achieve multithreading. The JVM handles thread scheduling and context switching when start() is used.

**Example:**
```java
public class StartVsRun {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        
        // Correct - creates new thread
        thread.start(); // Output: Thread: Thread-0
        
        // Wrong - runs in main thread
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        thread2.run(); // Output: Thread: main (no new thread created)
    }
}
```

---

### 37. What is `synchronized` keyword and its usage?

**Answer:**
The synchronized keyword provides mutual exclusion, ensuring only one thread can execute a synchronized block or method at a time. It can be applied to methods or blocks. Synchronized methods lock the entire object (this for instance methods, Class object for static methods). Synchronized blocks allow fine-grained locking on specific objects. Every object in Java has an intrinsic lock (monitor). When a thread enters a synchronized section, it acquires the lock and releases it when exiting. Other threads trying to enter must wait. Use synchronized to protect shared mutable state from race conditions.

**Example:**
```java
public class SynchronizedExample {
    private int count = 0;
    
    // Synchronized method - locks entire object
    public synchronized void increment() {
        count++;
    }
    
    // Synchronized block - locks specific object
    public void incrementBlock() {
        synchronized(this) {
            count++;
        }
    }
    
    // Static synchronized - locks Class object
    public static synchronized void staticMethod() {
        // class-level lock
    }
    
    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample obj = new SynchronizedExample();
        
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) obj.increment();
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) obj.increment();
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(obj.count); // 2000 (thread-safe)
    }
}
```

---

### 38. Explain `volatile` keyword and happens-before relationship.

**Answer:**
The volatile keyword ensures visibility of changes across threads. When a variable is volatile, writes to it are immediately visible to other threads, and reads always get the latest value from main memory, not from thread's cache. It prevents instruction reordering around volatile variables. However, volatile doesn't provide atomicity for compound operations like increment. The happens-before relationship guarantees that actions in one thread are visible to another thread. A write to a volatile variable happens-before every subsequent read of that variable. Use volatile for flags and state variables that are written by one thread and read by others.

**Example:**
```java
public class VolatileExample {
    private volatile boolean running = true;
    
    public void stop() {
        running = false; // visible to other threads immediately
    }
    
    public void run() {
        while(running) {
            // do work
        }
        // stops when running becomes false
    }
    
    // Without volatile, thread might cache 'running' and never see the change
    
    public static void main(String[] args) throws InterruptedException {
        VolatileExample example = new VolatileExample();
        
        Thread worker = new Thread(example::run);
        worker.start();
        
        Thread.sleep(1000);
        example.stop(); // worker thread sees this change
        worker.join();
    }
}
```

---

### 39. Difference between `wait()` and `sleep()`.

**Answer:**
wait() is called on an object and releases the lock, allowing other threads to acquire it. It must be called within a synchronized context and the thread waits until notify() or notifyAll() is called. sleep() is a static method of Thread class that pauses the current thread for a specified time without releasing any locks. wait() is used for inter-thread communication, while sleep() is for introducing delays. wait() can be interrupted and must handle InterruptedException. After wait(), the thread must reacquire the lock before continuing. sleep() simply pauses execution.

**Example:**
```java
public class WaitVsSleep {
    private static final Object lock = new Object();
    
    public static void main(String[] args) {
        // wait() example - releases lock
        Thread t1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    System.out.println("T1: waiting");
                    lock.wait(); // releases lock
                    System.out.println("T1: resumed");
                } catch (InterruptedException e) {}
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized(lock) {
                System.out.println("T2: notifying");
                lock.notify(); // wakes up t1
            }
        });
        
        t1.start();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        t2.start();
        
        // sleep() example - doesn't release lock
        Thread t3 = new Thread(() -> {
            synchronized(lock) {
                try {
                    Thread.sleep(1000); // holds lock while sleeping
                } catch (InterruptedException e) {}
            }
        });
    }
}
```

---

### 40. What is deadlock? How do you prevent it?

**Answer:**
Deadlock occurs when two or more threads are blocked forever, each waiting for a lock held by another. Classic example: Thread A holds Lock1 and waits for Lock2, while Thread B holds Lock2 and waits for Lock1. Prevention strategies include: always acquire locks in the same order across all threads, use timeout with tryLock(), avoid nested locks when possible, use higher-level concurrency utilities like concurrent collections, and design to minimize lock holding time. Detection tools include thread dumps and JConsole. Use lock ordering, timeout mechanisms, and careful design to prevent deadlocks.

**Example:**
```java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // BAD - can cause deadlock
    public void method1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // work
            }
        }
    }
    
    public void method2() {
        synchronized(lock2) { // different order!
            synchronized(lock1) {
                // work
            }
        }
    }
    
    // GOOD - consistent lock ordering
    public void safeMethod1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // work
            }
        }
    }
    
    public void safeMethod2() {
        synchronized(lock1) { // same order
            synchronized(lock2) {
                // work
            }
        }
    }
}
```

---

### 41. Explain `ExecutorService` and thread pool types.

**Answer:**
ExecutorService is a high-level API for managing thread execution, providing thread pools to reuse threads efficiently. It decouples task submission from execution mechanics. Main thread pool types: FixedThreadPool creates a fixed number of threads, reusing them for tasks. CachedThreadPool creates threads as needed and reuses idle threads. SingleThreadExecutor uses one thread for sequential execution. ScheduledThreadPool schedules tasks with delays or periodic execution. Thread pools reduce overhead of thread creation, limit concurrent threads, and provide better resource management. Always shutdown ExecutorService to release resources.

**Example:**
```java
import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) {
        // Fixed thread pool - 5 threads
        ExecutorService fixed = Executors.newFixedThreadPool(5);
        
        // Cached thread pool - creates as needed
        ExecutorService cached = Executors.newCachedThreadPool();
        
        // Single thread executor
        ExecutorService single = Executors.newSingleThreadExecutor();
        
        // Scheduled executor
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        
        // Submit tasks
        fixed.submit(() -> System.out.println("Task executed"));
        
        Future<Integer> future = fixed.submit(() -> 42);
        
        // Schedule task
        scheduled.schedule(() -> System.out.println("Delayed"), 5, TimeUnit.SECONDS);
        
        // Shutdown
        fixed.shutdown();
        try {
            fixed.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {}
    }
}
```

---

### 42. What is `CountDownLatch`, `CyclicBarrier`, `Semaphore`?

**Answer:**
CountDownLatch allows threads to wait until a set of operations completes. It's initialized with a count that decrements with countDown() calls. Threads calling await() block until count reaches zero. It's one-time use. CyclicBarrier makes threads wait for each other at a barrier point. When all threads reach the barrier, they're released together. It's reusable. Semaphore controls access to a resource pool with permits. acquire() gets a permit, release() returns it. Use CountDownLatch for waiting on initialization, CyclicBarrier for coordinating phases, and Semaphore for limiting concurrent access.

**Example:**
```java
import java.util.concurrent.*;

public class SynchronizersExample {
    // CountDownLatch - wait for multiple threads to complete
    public static void countDownLatchExample() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        
        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("Task done");
                latch.countDown();
            }).start();
        }
        
        latch.await(); // waits until count = 0
        System.out.println("All tasks completed");
    }
    
    // CyclicBarrier - threads wait for each other
    public static void cyclicBarrierExample() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> 
            System.out.println("All reached barrier"));
        
        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Waiting at barrier");
                    barrier.await();
                } catch (Exception e) {}
            }).start();
        }
    }
    
    // Semaphore - limit concurrent access
    public static void semaphoreExample() {
        Semaphore semaphore = new Semaphore(2); // 2 permits
        
        for(int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Acquired");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {}
            }).start();
        }
    }
}
```

---

### 43. `ReentrantLock` vs `synchronized` - when to use which?

**Answer:**
ReentrantLock provides more flexibility than synchronized. It offers tryLock() for non-blocking lock attempts with timeout, lockInterruptibly() for interruptible locking, and fairness policy for lock acquisition order. It allows multiple condition variables via newCondition(). However, you must manually unlock in a finally block. synchronized is simpler, automatically releases locks, and has less overhead. Use synchronized for simple cases and ReentrantLock when you need tryLock, timeouts, fairness, multiple conditions, or interruptible locking. ReentrantLock is more powerful but requires careful management.

**Example:**
```java
import java.util.concurrent.locks.*;

public class LockComparison {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    
    // ReentrantLock with try-lock
    public void incrementWithTryLock() {
        if(lock.tryLock()) {
            try {
                count++;
            } finally {
                lock.unlock(); // must unlock manually
            }
        }
    }
    
    // ReentrantLock with timeout
    public void incrementWithTimeout() throws InterruptedException {
        if(lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }
    
    // Synchronized - simpler
    public synchronized void incrementSynchronized() {
        count++; // automatically unlocks
    }
    
    // Fair lock
    private final ReentrantLock fairLock = new ReentrantLock(true);
    
    // Condition variables
    private final Condition condition = lock.newCondition();
    
    public void awaitCondition() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
    }
}
```

---

### 44. Explain `ReadWriteLock` and its use cases.

**Answer:**
ReadWriteLock maintains a pair of locks: one for read-only operations and one for writing. Multiple threads can hold the read lock simultaneously if no thread holds the write lock. The write lock is exclusive - only one thread can hold it and no readers are allowed. This improves performance for read-heavy scenarios. ReentrantReadWriteLock is the common implementation. Use it when reads vastly outnumber writes, like caching scenarios. Read lock allows concurrent reads, write lock ensures exclusive writes. It prevents readers from seeing inconsistent state during writes.

**Example:**
```java
import java.util.concurrent.locks.*;

public class ReadWriteLockExample {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    private int value = 0;
    
    // Multiple threads can read simultaneously
    public int read() {
        readLock.lock();
        try {
            return value;
        } finally {
            readLock.unlock();
        }
    }
    
    // Only one thread can write, blocks all readers
    public void write(int newValue) {
        writeLock.lock();
        try {
            value = newValue;
        } finally {
            writeLock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        
        // Many readers
        for(int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(example.read())).start();
        }
        
        // Few writers
        new Thread(() -> example.write(42)).start();
    }
}
```

---

### 45. What is `AtomicInteger` and how does it work?

**Answer:**
AtomicInteger provides lock-free thread-safe operations on integers using CAS (Compare-And-Swap) operations. CAS is a CPU-level atomic instruction that compares a value with an expected value and updates it only if they match. This avoids locks, reducing contention and improving performance. Methods like incrementAndGet(), getAndIncrement(), compareAndSet() are atomic. It's ideal for counters, sequence generators, and simple shared state. AtomicInteger uses volatile internally for visibility and CAS for atomicity. It's faster than synchronized for simple operations but only works for single variables.

**Example:**
```java
import java.util.concurrent.atomic.*;

public class AtomicExample {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet(); // atomic, thread-safe
    }
    
    public int get() {
        return counter.get();
    }
    
    // CAS operation
    public boolean compareAndSet(int expected, int newValue) {
        return counter.compareAndSet(expected, newValue);
    }
    
    public static void main(String[] args) throws InterruptedException {
        AtomicExample example = new AtomicExample();
        
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    example.increment();
                }
            });
            threads[i].start();
        }
        
        for(Thread t : threads) t.join();
        System.out.println(example.get()); // 10000 (thread-safe)
    }
}
```

---

### 46. Explain `CompletableFuture` for async programming.

**Answer:**
CompletableFuture enables asynchronous, non-blocking programming with composable operations. It represents a future result that can be completed explicitly. Methods like supplyAsync() run tasks asynchronously, thenApply() transforms results, thenAccept() consumes results, and thenCompose() chains dependent futures. It supports combining multiple futures with allOf(), anyOf(), and exception handling with exceptionally() and handle(). Unlike Future, it's composable and doesn't block. Use it for async API calls, parallel processing, and building reactive pipelines. It improves responsiveness and resource utilization.

**Example:**
```java
import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // Async execution
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        
        // Chain operations
        future.thenApply(s -> s + " World")
              .thenAccept(System.out::println);
        
        // Combine futures
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);
        
        CompletableFuture<Integer> combined = f1.thenCombine(f2, (a, b) -> a + b);
        
        // Exception handling
        CompletableFuture<String> withError = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Error");
        }).exceptionally(ex -> "Default");
        
        // Wait for all
        CompletableFuture.allOf(f1, f2).join();
    }
}
```

---

### 47. What is `ThreadLocal` and its use cases?

**Answer:**
ThreadLocal provides thread-local variables where each thread has its own independent copy. Values stored in ThreadLocal are accessible only to that thread. It's useful for maintaining per-thread context like user sessions, database connections, or transaction contexts without passing them as parameters. Each thread's value is isolated from others. Common use cases include SimpleDateFormat (not thread-safe), storing user authentication context, and maintaining transaction state. Always remove ThreadLocal values using remove() to prevent memory leaks, especially in thread pool environments where threads are reused.

**Example:**
```java
public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    
    public static void increment() {
        threadLocal.set(threadLocal.get() + 1);
    }
    
    public static int get() {
        return threadLocal.get();
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            threadLocal.set(100);
            System.out.println("T1: " + threadLocal.get()); // 100
        });
        
        Thread t2 = new Thread(() -> {
            threadLocal.set(200);
            System.out.println("T2: " + threadLocal.get()); // 200
        });
        
        t1.start();
        t2.start();
        
        // Each thread has its own value
    }
    
    // Real-world example - user context
    private static ThreadLocal<String> userContext = new ThreadLocal<>();
    
    public static void setUser(String user) {
        userContext.set(user);
    }
    
    public static String getUser() {
        return userContext.get();
    }
    
    public static void cleanup() {
        userContext.remove(); // prevent memory leak
    }
}
```

---

### 48. How do you handle exceptions in multithreaded code?

**Answer:**
Exception handling in multithreaded code requires special attention because exceptions in one thread don't propagate to other threads. For threads, use try-catch within run() method or set an UncaughtExceptionHandler. For ExecutorService, Future.get() throws ExecutionException wrapping the original exception. Use submit() instead of execute() to capture exceptions. CompletableFuture provides exceptionally() and handle() for async exception handling. Always log exceptions in worker threads. Consider using a global exception handler for uncaught exceptions. Proper exception handling prevents silent failures and aids debugging.

**Example:**
```java
import java.util.concurrent.*;

public class ExceptionHandling {
    // Thread with exception handler
    public static void threadExample() {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Error in thread");
        });
        
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Caught: " + e.getMessage());
        });
        
        thread.start();
    }
    
    // ExecutorService with Future
    public static void executorExample() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<Integer> future = executor.submit(() -> {
            throw new RuntimeException("Error in task");
        });
        
        try {
            future.get(); // throws ExecutionException
        } catch (ExecutionException e) {
            System.out.println("Caught: " + e.getCause());
        } catch (InterruptedException e) {}
        
        executor.shutdown();
    }
    
    // CompletableFuture exception handling
    public static void completableFutureExample() {
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Error");
        }).exceptionally(ex -> {
            System.out.println("Handled: " + ex.getMessage());
            return null;
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        });
    }
}
```

---

### 49. Explain Fork/Join framework.

**Answer:**
Fork/Join framework is designed for parallel processing of recursive tasks using divide-and-conquer approach. It uses work-stealing algorithm where idle threads steal tasks from busy threads' queues. ForkJoinPool manages worker threads. Tasks extend RecursiveTask (returns result) or RecursiveAction (no result). The fork() method submits subtasks asynchronously, join() waits for completion. It's efficient for problems that can be broken into smaller subproblems like parallel sorting, tree traversal, or matrix operations. The framework automatically balances load across available processors. Use it for CPU-intensive recursive algorithms.

**Example:**
```java
import java.util.concurrent.*;

public class ForkJoinExample extends RecursiveTask<Long> {
    private final long[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 1000;
    
    public ForkJoinExample(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        if(end - start <= THRESHOLD) {
            // Direct computation
            long sum = 0;
            for(int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Fork into subtasks
            int mid = (start + end) / 2;
            ForkJoinExample left = new ForkJoinExample(array, start, mid);
            ForkJoinExample right = new ForkJoinExample(array, mid, end);
            
            left.fork(); // async execution
            long rightResult = right.compute();
            long leftResult = left.join(); // wait for result
            
            return leftResult + rightResult;
        }
    }
    
    public static void main(String[] args) {
        long[] array = new long[10000];
        for(int i = 0; i < array.length; i++) array[i] = i;
        
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinExample task = new ForkJoinExample(array, 0, array.length);
        long result = pool.invoke(task);
        System.out.println("Sum: " + result);
    }
}
```

---

### 50. What are best practices for thread safety?

**Answer:**
Thread safety best practices include: minimize shared mutable state, prefer immutable objects, use thread-safe collections like ConcurrentHashMap, synchronize access to shared mutable state, use higher-level concurrency utilities over low-level primitives, avoid holding locks during I/O operations, use atomic variables for simple counters, document thread-safety guarantees, use ThreadLocal for per-thread state, always release locks in finally blocks, prefer composition over inheritance for thread-safe classes, avoid calling alien methods while holding locks, and test with multiple threads under load. Design for thread safety from the start rather than adding it later.

**Example:**
```java
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ThreadSafetyBestPractices {
    // 1. Immutable objects
    public static final class ImmutablePoint {
        private final int x, y;
        public ImmutablePoint(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; }
        public int getY() { return y; }
    }
    
    // 2. Thread-safe collections
    private final Map<String, Integer> map = new ConcurrentHashMap<>();
    
    // 3. Atomic variables
    private final AtomicInteger counter = new AtomicInteger(0);
    
    // 4. Proper synchronization
    private final Object lock = new Object();
    private int value;
    
    public void updateValue(int newValue) {
        synchronized(lock) {
            value = newValue;
        }
    }
    
    // 5. ThreadLocal for per-thread state
    private static final ThreadLocal<SimpleDateFormat> dateFormat = 
        ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    
    // 6. Use ExecutorService
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    
    // 7. Minimize lock scope
    public void goodLocking() {
        // do work without lock
        synchronized(lock) {
            // minimal critical section
        }
        // more work without lock
    }
    
    // 8. Document thread-safety
    /**
     * Thread-safe counter using AtomicInteger
     */
    public void increment() {
        counter.incrementAndGet();
    }
}
```

# ✅ 5) Exception Handling & JVM Internals

## 51. Checked vs unchecked exceptions - when to use which?

**Answer:**
Checked exceptions are compile-time exceptions that must be handled or declared. Unchecked exceptions are runtime exceptions that don't require explicit handling.

**Use checked exceptions** for recoverable conditions (file not found, network issues).
**Use unchecked exceptions** for programming errors (null pointer, illegal arguments).

**Example:**
```java
// Checked Exception - must handle
public void readFile(String path) throws IOException {
    FileReader reader = new FileReader(path); // IOException is checked
}

// Unchecked Exception - optional handling
public void divide(int a, int b) {
    int result = a / b; // ArithmeticException is unchecked
}
```

---

## 52. Explain try-catch-finally execution flow.

**Answer:**
Try block executes code, catch handles exceptions, finally always executes regardless of exception occurrence.

**Execution flow:**
1. Try block executes
2. If exception occurs, matching catch executes
3. Finally block always executes (even with return statements)

**Example:**
```java
public int process() {
    try {
        System.out.println("Try");
        return 1;
    } catch (Exception e) {
        System.out.println("Catch");
        return 2;
    } finally {
        System.out.println("Finally"); // Always executes
    }
}
// Output: Try, Finally, returns 1
```

---

## 53. What is try-with-resources and why is it important?

**Answer:**
Try-with-resources automatically closes resources that implement AutoCloseable, preventing resource leaks.

**Important because:** Ensures resources are closed even if exceptions occur, eliminates boilerplate finally blocks.

**Example:**
```java
// Old way - manual close
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("file.txt"));
} finally {
    if (br != null) br.close();
}

// Try-with-resources - auto close
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    return br.readLine();
} // Automatically closed
```

---

## 54. How do you create custom exceptions?

**Answer:**
Extend Exception (checked) or RuntimeException (unchecked) and provide constructors for message and cause.

**Example:**
```java
// Custom checked exception
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
    
    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Usage
public void withdraw(double amount) throws InsufficientBalanceException {
    if (balance < amount) {
        throw new InsufficientBalanceException("Balance: " + balance);
    }
}
```

---

## 55. What is exception chaining?

**Answer:**
Exception chaining links one exception to another, preserving the original cause while throwing a new exception.

**Purpose:** Maintains complete error context and root cause information.

**Example:**
```java
public void processData() throws DataProcessingException {
    try {
        // Database operation
        connection.execute(query);
    } catch (SQLException e) {
        // Chain original exception
        throw new DataProcessingException("Failed to process", e);
    }
}

// Retrieve original cause
catch (DataProcessingException e) {
    Throwable cause = e.getCause(); // Gets SQLException
}
```

---

## 56. Explain ClassLoader hierarchy in JVM.

**Answer:**
ClassLoader hierarchy loads classes in three levels: Bootstrap (core Java), Extension (ext directory), and Application (classpath).

**Hierarchy:** Bootstrap → Extension → Application (follows parent delegation model).

**Example:**
```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Application ClassLoader
        ClassLoader appLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(appLoader); // AppClassLoader
        
        // Extension ClassLoader
        ClassLoader extLoader = appLoader.getParent();
        System.out.println(extLoader); // ExtClassLoader
        
        // Bootstrap ClassLoader (null - native)
        ClassLoader bootLoader = extLoader.getParent();
        System.out.println(bootLoader); // null
    }
}
```

---

## 57. What is JIT compilation and method inlining?

**Answer:**
JIT (Just-In-Time) compilation converts frequently executed bytecode to native machine code at runtime for performance.

**Method inlining:** JIT replaces method calls with method body to eliminate call overhead.

**Example:**
```java
// Before inlining
public int calculate(int x) {
    return add(x, 5);
}

private int add(int a, int b) {
    return a + b;
}

// After JIT inlining (conceptual)
public int calculate(int x) {
    return x + 5; // Method call eliminated
}

// JIT flags
// -XX:+PrintCompilation (see JIT compilation)
// -XX:CompileThreshold=10000 (compilation threshold)
```

---

## 58. How does JVM execute bytecode?

**Answer:**
JVM loads class files, verifies bytecode, interprets or JIT-compiles to native code, then executes on the runtime engine.

**Steps:** Load → Verify → Prepare → Resolve → Initialize → Execute

**Example:**
```java
// Source code
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}

// Compiled to bytecode (.class)
// javap -c Hello.class shows:
// 0: getstatic     #2  // Field java/lang/System.out
// 3: ldc           #3  // String Hello
// 5: invokevirtual #4  // Method println

// JVM execution:
// 1. ClassLoader loads Hello.class
// 2. Bytecode verifier checks validity
// 3. Interpreter executes or JIT compiles
// 4. Native code runs on CPU
```

---

## 59. What are JVM flags for debugging?

**Answer:**
JVM flags enable debugging features like verbose output, heap dumps, GC logs, and remote debugging.

**Common flags:** -verbose, -XX:+PrintGCDetails, -XX:+HeapDumpOnOutOfMemoryError, -agentlib:jdwp

**Example:**
```bash
# Verbose class loading
java -verbose:class MyApp

# GC logging
java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps MyApp

# Heap dump on OOM
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/dump.hprof MyApp

# Remote debugging (port 5005)
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 MyApp

# Print JVM flags
java -XX:+PrintFlagsFinal -version

# Enable assertions
java -ea MyApp
```

# ✅ 6) Java 8+ Features (Streams, Lambda, Optional)

## 60. What are lambda expressions and functional interfaces?

**Answer:**
Lambda expressions are anonymous functions that implement functional interfaces. A functional interface has exactly one abstract method.

**Lambda syntax:** `(parameters) -> expression` or `(parameters) -> { statements; }`

**Example:**
```java
// Functional Interface
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Without lambda
Calculator add = new Calculator() {
    public int calculate(int a, int b) { return a + b; }
};

// With lambda
Calculator multiply = (a, b) -> a * b;
Calculator divide = (a, b) -> { return a / b; };

System.out.println(multiply.calculate(5, 3)); // 15
```

---

## 61. Explain built-in functional interfaces (Predicate, Function, Consumer, Supplier).

**Answer:**
Built-in functional interfaces in java.util.function package for common operations.

- **Predicate<T>:** Takes T, returns boolean (test condition)
- **Function<T,R>:** Takes T, returns R (transform)
- **Consumer<T>:** Takes T, returns void (consume/process)
- **Supplier<T>:** Takes nothing, returns T (supply/generate)

**Example:**
```java
// Predicate - test condition
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4)); // true

// Function - transform
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello")); // 5

// Consumer - process
Consumer<String> print = s -> System.out.println(s);
print.accept("Hi"); // Hi

// Supplier - generate
Supplier<Double> random = () -> Math.random();
System.out.println(random.get()); // 0.xyz
```

---

## 62. What is method reference and its types?

**Answer:**
Method reference is shorthand for lambda that calls an existing method using `::` operator.

**Types:** Static, Instance, Constructor, Arbitrary object

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob");

// Lambda
names.forEach(name -> System.out.println(name));

// Static method reference
names.forEach(System.out::println);

// Instance method reference
String prefix = "Hello ";
Function<String, String> greet = prefix::concat;
System.out.println(greet.apply("World")); // Hello World

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;

// Arbitrary object method reference
names.stream().map(String::toUpperCase);
```

---

## 63. Difference between intermediate and terminal operations in Stream.

**Answer:**
Intermediate operations return a stream and are lazy (not executed until terminal operation). Terminal operations produce result and close the stream.

**Intermediate:** map, filter, sorted, distinct (chainable)
**Terminal:** collect, forEach, reduce, count (triggers execution)

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Intermediate operations (lazy - not executed yet)
Stream<Integer> stream = numbers.stream()
    .filter(n -> n > 2)  // Intermediate
    .map(n -> n * 2);    // Intermediate

// Terminal operation (triggers execution)
List<Integer> result = stream.collect(Collectors.toList()); // Terminal
System.out.println(result); // [6, 8, 10]
```

## 63. What is the difference between lambda and anonymous class?

**Lambda Expression** is a concise way to represent an **implementation of a functional interface** using an expression, allowing cleaner and shorter code.

**Anonymous Class** is a **class without a name** defined and instantiated in a single statement, used to provide an implementation of an interface or subclass.

```java
// Anonymous class - verbose
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println(this.getClass()); // Anonymous class
    }
};

// Lambda - concise
Runnable r2 = () -> {
    System.out.println(this.getClass()); // Enclosing class
};
```

## 63. What is Stream API?

The **Stream API**, introduced in **Java 8**, is used to **process collections of data in a functional way**. It allows operations like filtering, mapping, and sorting without modifying the original data source.

```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// Stream pipeline
List<String> result = names.stream()
    .filter(name -> name.length() > 3)  // Intermediate
    .map(String::toUpperCase)           // Intermediate
    .sorted()                           // Intermediate
    .collect(Collectors.toList());      // Terminal
```

## 6. What is the difference between Collection and Stream?

A **Collection** is a **data structure** that stores elements in memory, like `List`, `Set`, or `Map`. It holds data and allows operations such as add, remove, or iterate, and it can be traversed multiple times.

A **Stream** is **not a data structure**; it’s a **data-processing abstraction**. It doesn’t store data but processes elements from a collection or other sources. Streams are **one-time use**, support **functional operations** like `filter` and `map`, and enable easy **parallel processing**.

```java
List<String> collection = Arrays.asList("a", "b", "c");
collection.add("d"); // Modifies collection

Stream<String> stream = collection.stream();
stream.filter(s -> s.length() > 1); // Doesn't modify collection
// stream.filter(...); // Error - stream already used
```

## 64. Explain `map()`, `flatMap()`, `filter()`, `reduce()`.

**Answer:**
Stream operations for transformation, flattening, filtering, and aggregation.

- **map():** Transform each element
- **flatMap():** Transform and flatten nested structures
- **filter():** Select elements matching condition
- **reduce():** Combine elements into single result

**Example:**
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);

// map - transform
List<Integer> squared = nums.stream()
    .map(n -> n * n)
    .collect(Collectors.toList()); // [1, 4, 9, 16]

// filter - select
List<Integer> evens = nums.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList()); // [2, 4]

// reduce - aggregate
int sum = nums.stream()
    .reduce(0, (a, b) -> a + b); // 10

// flatMap - flatten
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2), Arrays.asList(3, 4)
);
List<Integer> flat = nested.stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList()); // [1, 2, 3, 4]
```

---

## 65. What is the difference between `map()` and `flatMap()`?

**Answer:**

- `map()` transforms each element to one element (1-to-1). 
- `flatMap()` transforms each element to a stream and flattens all streams into one (1-to-many).

**map:** Stream<T> → Stream<R>
**flatMap:** Stream<T> → Stream<Stream<R>> → Stream<R>

**Example:**
```java
List<String> words = Arrays.asList("Hello", "World");

// map - 1 to 1 transformation
List<Integer> lengths = words.stream()
    .map(s -> s.length())
    .collect(Collectors.toList()); // [5, 5]

// flatMap - 1 to many, then flatten
List<String> chars = words.stream()
    .flatMap(s -> Arrays.stream(s.split("")))
    .collect(Collectors.toList()); 
// [H, e, l, l, o, W, o, r, l, d]

// map would give Stream<String[]>
// flatMap gives Stream<String>
```

---

## 66. When should you use parallel streams?

**Answer:**
Use parallel streams for large datasets with CPU-intensive operations and independent elements. Avoid for small datasets, I/O operations, or when order matters.

**Use when:** Large data (>10k elements), stateless operations, multi-core CPU
**Avoid when:** Small data, I/O bound, shared mutable state

**Example:**
```java
List<Integer> numbers = IntStream.rangeClosed(1, 1000000)
    .boxed().collect(Collectors.toList());

// Sequential - single thread
long start = System.currentTimeMillis();
long sum1 = numbers.stream()
    .mapToInt(n -> n * n)
    .sum();
System.out.println("Sequential: " + (System.currentTimeMillis() - start));

// Parallel - multiple threads
start = System.currentTimeMillis();
long sum2 = numbers.parallelStream()
    .mapToInt(n -> n * n)
    .sum();
System.out.println("Parallel: " + (System.currentTimeMillis() - start));

// Don't use parallel for small data
List<Integer> small = Arrays.asList(1, 2, 3);
small.stream().forEach(System.out::println); // Better than parallel
```

---

## 67. Explain `Optional` class and its methods.

**Answer:**
Optional is a container that may or may not contain a non-null value, avoiding NullPointerException.

**Key methods:** of(), ofNullable(), isPresent(), ifPresent(), orElse(), orElseGet(), orElseThrow(), map(), filter()

**Example:**
```java
// Creating Optional
Optional<String> name = Optional.of("John"); // NPE if null
Optional<String> nullable = Optional.ofNullable(null); // Safe
Optional<String> empty = Optional.empty();

// Checking and retrieving
if (name.isPresent()) {
    System.out.println(name.get()); // John
}

// Better approach
name.ifPresent(n -> System.out.println(n)); // John

// Default values
String result = nullable.orElse("Default"); // Default
String result2 = nullable.orElseGet(() -> "Computed"); // Computed
String result3 = nullable.orElseThrow(() -> new Exception()); // Throws

// Transform
Optional<Integer> length = name.map(String::length); // Optional[4]
```

---

## 68. Difference between `orElse()` and `orElseGet()`?

**Answer:**
orElse() always evaluates the default value. orElseGet() evaluates lazily only when Optional is empty.

**orElse:** Value always computed (use for constants)
**orElseGet:** Supplier called only if empty (use for expensive operations)

**Example:**
```java
public String getDefault() {
    System.out.println("Computing default...");
    return "Default";
}

Optional<String> name = Optional.of("John");

// orElse - always executes
String result1 = name.orElse(getDefault()); 
// Prints: "Computing default..." even though name exists
// Returns: "John"

// orElseGet - lazy execution
String result2 = name.orElseGet(() -> getDefault());
// Doesn't print anything
// Returns: "John"

Optional<String> empty = Optional.empty();

// Both execute when empty
String result3 = empty.orElse(getDefault()); // Prints, returns "Default"
String result4 = empty.orElseGet(() -> getDefault()); // Prints, returns "Default"
```

---

## 69. How do you handle exceptions in streams?

**Answer:**
Wrap checked exceptions in unchecked exceptions or use helper methods, since stream lambdas don't allow checked exceptions.

**Approaches:** Try-catch in lambda, wrapper method, custom functional interface

**Example:**
```java
List<String> numbers = Arrays.asList("1", "2", "abc", "4");

// Approach 1: Try-catch in lambda
List<Integer> result1 = numbers.stream()
    .map(s -> {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0; // Default value
        }
    })
    .collect(Collectors.toList()); // [1, 2, 0, 4]

// Approach 2: Wrapper method
public static Integer parseOrDefault(String s) {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        return 0;
    }
}

List<Integer> result2 = numbers.stream()
    .map(s -> parseOrDefault(s))
    .collect(Collectors.toList());

// Approach 3: Filter invalid
List<Integer> result3 = numbers.stream()
    .filter(s -> s.matches("\\d+"))
    .map(Integer::parseInt)
    .collect(Collectors.toList()); // [1, 2, 4]
```

---

## 70. What is the `Collectors` utility class?

**Answer:**
Collectors provides reduction operations to accumulate stream elements into collections, strings, or other results.

**Common collectors:** toList(), toSet(), toMap(), joining(), groupingBy(), partitioningBy(), counting(), summingInt()

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// toList
List<String> list = names.stream().collect(Collectors.toList());

// toSet
Set<String> set = names.stream().collect(Collectors.toSet());

// joining
String joined = names.stream().collect(Collectors.joining(", ")); 
// "John, Jane, Bob, Alice"

// counting
long count = names.stream().collect(Collectors.counting()); // 4

// toMap
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(
        name -> name,           // key
        name -> name.length()   // value
    )); // {John=4, Jane=4, Bob=3, Alice=5}

// summingInt
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
int sum = nums.stream().collect(Collectors.summingInt(n -> n)); // 10
```

---

## 71. How do you group and partition data using streams?

**Answer:**
groupingBy() groups elements by classifier function into Map. partitioningBy() splits into two groups (true/false) based on predicate.

**groupingBy:** Multiple groups by key
**partitioningBy:** Two groups (true/false)

**Example:**
```java
class Person {
    String name;
    int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

List<Person> people = Arrays.asList(
    new Person("John", 25),
    new Person("Jane", 30),
    new Person("Bob", 25),
    new Person("Alice", 30)
);

// groupingBy - group by age
Map<Integer, List<Person>> byAge = people.stream()
    .collect(Collectors.groupingBy(Person::getAge));
// {25=[John, Bob], 30=[Jane, Alice]}

// groupingBy with counting
Map<Integer, Long> ageCount = people.stream()
    .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
// {25=2, 30=2}

// partitioningBy - split by condition
Map<Boolean, List<Person>> partitioned = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
// {false=[John, Bob], true=[Jane, Alice]}
```

# ✅ 7) Spring Framework (Core + MVC)

## 72. What is IoC and Dependency Injection?

**Answer:**
IoC (Inversion of Control) means the framework controls object creation instead of the developer. Dependency Injection is a pattern where objects receive their dependencies from external sources rather than creating them.

**Example:**
```java
// Without DI
public class UserService {
    private UserRepository repo = new UserRepository(); // tight coupling
}

// With DI
@Service
public class UserService {
    private final UserRepository repo;
    
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo; // Spring injects dependency
    }
}
```

---

## 73. Explain Spring Bean lifecycle.

**Answer:**
Bean lifecycle: Instantiation → Populate Properties → setBeanName() → setBeanFactory() → setApplicationContext() → @PostConstruct → Bean Ready → @PreDestroy → Destroy.

**Example:**
```java
@Component
public class MyBean {
    
    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }
    
    @PreDestroy
    public void cleanup() {
        System.out.println("Bean destroyed");
    }
}
```

---

## 74. What are bean scopes (singleton, prototype, request, session)?

**Answer:**
- **Singleton**: One instance per Spring container (default)
- **Prototype**: New instance every time requested
- **Request**: One instance per HTTP request
- **Session**: One instance per HTTP session

**Example:**
```java
@Component
@Scope("singleton") // default
public class SingletonBean { }

@Component
@Scope("prototype")
public class PrototypeBean { }

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBean { }
```

---

## 75. Difference between `@Component`, `@Service`, `@Repository`, `@Controller`.

**Answer:**
All four are Spring stereotype annotations used to mark classes as Spring-managed beans, but they represent different layers of the application.

`@Component` is a generic annotation used to register any class as a Spring bean. It is the base annotation for other stereotypes.

`@Service` is a specialization of `@Component` and is used for the service layer. It contains business logic.

`@Repository` is also a specialization of `@Component` and is used for the data access layer (DAO). It provides automatic exception translation for database operations.

`@Controller` is another specialization of `@Component` and is used in the presentation layer. It handles incoming HTTP requests and returns responses, typically used in Spring MVC applications.


**Example:**
```java
@Component
public class GenericComponent { }

@Service
public class UserService { }

@Repository
public class UserRepository { }

@Controller
public class UserController { }
```

---

## 76. Constructor vs setter vs field injection - which is preferred?

**Answer:**
Constructor injection is preferred because it ensures immutability, makes dependencies explicit, and allows final fields. Field injection is discouraged.

**Example:**
```java
// Constructor injection (Preferred)
@Service
public class UserService {
    private final UserRepository repo;
    
    public UserService(UserRepository repo) {
        this.repo = repo;
    }
}

// Setter injection
@Service
public class OrderService {
    private OrderRepository repo;
    
    @Autowired
    public void setRepo(OrderRepository repo) {
        this.repo = repo;
    }
}

// Field injection (Not recommended)
@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
}
```

---

## 77. How do you resolve bean ambiguity (`@Primary`, `@Qualifier`)?

**Answer:**

`@Primary` is used to mark one bean as the default choice. When multiple beans exist, Spring will inject the bean marked with `@Primary` unless specified otherwise.

`@Qualifier` is used to specify exactly which bean should be injected by providing the bean name. It is more specific and overrides `@Primary` when both are used.

Use `@Primary` to mark a default bean or `@Qualifier` to specify which bean to inject when multiple candidates exist.

**Example:**
```java
@Component
@Primary
public class MySQLDatabase implements Database { }

@Component
public class PostgresDatabase implements Database { }

@Service
public class DataService {
    private final Database db;
    
    public DataService(@Qualifier("postgresDatabase") Database db) {
        this.db = db;
    }
}
```

## 78. What is `@Configuration` and `@Bean`?

**Answer:**

`@Configuration` marks a class as a source of bean definitions. `@Bean` defines a method that returns a bean to be managed by Spring container.

`@Configuration` is used to mark a class as a Spring configuration class. It tells Spring that this class contains bean definitions.

`@Bean` is used inside a `@Configuration` class to define and register a method’s return object as a Spring bean in the application context.


**Example:**
```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/db");
        return ds;
    }
    
    @Bean
    public UserService userService() {
        return new UserService(dataSource());
    }
}
```

---

## 79. Explain Spring AOP concepts (Aspect, Advice, Pointcut, JoinPoint).

**Answer:**

**Aspect** is a class that contains cross-cutting logic like logging, security, or transact
**Advice** is the actual action that is executed at a specific point, such as before, after, or around a method execution.
**Pointcut** is an expression that defines where the advice should be applied, meaning which methods will be intercepted.
**JoinPoint** represents the specific point during execution, usually a method call, where the advice is applied.


**Example:**
```java
@Aspect
@Component
public class LoggingAspect {
    
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void serviceMethods() { }
    
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature());
    }
}
```

---

## 80. What are advice types (Before, After, Around)?

**Answer:**
- **@Before**: Runs before method execution
- **@After**: Runs after method (finally)
- **@AfterReturning**: Runs after successful return
- **@AfterThrowing**: Runs after exception
- **@Around**: Wraps method execution

**Example:**
```java
@Aspect
@Component
public class PerformanceAspect {
    
    @Before("execution(* com.example.service.*.*(..))")
    public void before() {
        System.out.println("Before method");
    }
    
    @After("execution(* com.example.service.*.*(..))")
    public void after() {
        System.out.println("After method");
    }
    
    @Around("execution(* com.example.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time + "ms");
        return result;
    }
}
```

---

## 81. Explain Spring MVC request flow and DispatcherServlet.

**Answer:**
Request → DispatcherServlet → HandlerMapping → Controller → ViewResolver → View → Response. DispatcherServlet is the front controller that routes requests.

**Example:**
```java
@Controller
public class UserController {
    
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userView"; // ViewResolver resolves to JSP/Thymeleaf
    }
}
```

---

## 82. What is `@PathVariable` vs `@RequestParam`?

**Answer:**
`@PathVariable` extracts values from URI path. `@RequestParam` extracts query parameters from URL.

**Example:**
```java
@RestController
public class ProductController {
    
    // PathVariable: /products/123
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
    
    // RequestParam: /products?category=electronics&page=2
    @GetMapping("/products")
    public List<Product> getProducts(
        @RequestParam String category,
        @RequestParam(defaultValue = "0") int page) {
        return productService.findByCategory(category, page);
    }
}
```

---

## 83. How do you handle exceptions using `@ControllerAdvice`?

**Answer:**
`@ControllerAdvice` provides global exception handling across all controllers using `@ExceptionHandler` methods.

**Example:**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(500, "Internal error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

class ErrorResponse {
    private int status;
    private String message;
    
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    // getters/setters
}
```

---

## 84. Explain Spring validation with `@Valid`.

**Answer:**
`@Valid` triggers validation on request body using Bean Validation annotations. Validation errors are captured in BindingResult.

**Example:**
```java
// DTO with validation
public class UserDTO {
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email")
    private String email;
    
    @Min(value = 18, message = "Age must be 18+")
    private int age;
    
    // getters/setters
}

// Controller
@RestController
public class UserController {
    
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user, 
                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        userService.save(user);
        return ResponseEntity.ok("User created");
    }
}
```

# ✅ 8) Spring Boot and Spring Security

## 85. What is Spring Boot auto-configuration?

**Answer:**
Auto-configuration automatically configures Spring application based on dependencies in classpath. It uses `@Conditional` annotations to apply configurations only when certain conditions are met.

**Example:**
```java
// Spring Boot detects H2 in classpath and auto-configures DataSource
// pom.xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>

// No manual configuration needed - Spring Boot auto-configures
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

## 86. Explain `@SpringBootApplication` annotation.

**Answer:**
`@SpringBootApplication` is a combination of three annotations: `@Configuration` (Java config), `@EnableAutoConfiguration` (auto-config), and `@ComponentScan` (scan components).

**Example:**
```java
@SpringBootApplication
// Equivalent to:
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// Exclude specific auto-configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CustomApp { }
```

---

## 87. What are Spring Boot starters?

**Answer:**
Starters are dependency descriptors that bundle related dependencies together. They simplify dependency management by providing pre-configured sets of libraries.

**Example:**
```xml
<!-- pom.xml -->
<!-- Web starter includes Spring MVC, Tomcat, Jackson -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- JPA starter includes Hibernate, Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Security starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

## 88. How do you externalize configuration?

**Answer:**
Use `application.properties` or `application.yml` files, environment variables, command-line arguments, or `@ConfigurationProperties` to externalize configuration.

**Example:**
```properties
# application.properties
app.name=MyApp
app.version=1.0
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/db
```

```java
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private String version;
    
    // getters/setters
}

@RestController
public class ConfigController {
    @Value("${app.name}")
    private String appName;
    
    @Autowired
    private AppConfig config;
}
```

---

## 89. What are Spring profiles and how do you use them?

**Answer:**
Profiles allow different configurations for different environments (dev, test, prod). Activate using `spring.profiles.active` property.

**Example:**
```properties
# application-dev.properties
spring.datasource.url=jdbc:h2:mem:testdb

# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-server:3306/db
```

```java
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource();
    }
}

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public DataSource dataSource() {
        return new MySQLDataSource();
    }
}

// Activate: java -jar app.jar --spring.profiles.active=prod
```

---

## 90. Explain Spring Boot Actuator endpoints.

**Answer:**
Actuator provides production-ready features like health checks, metrics, and monitoring endpoints. Common endpoints: `/health`, `/metrics`, `/info`, `/env`.

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=health,metrics,info
management.endpoint.health.show-details=always
```

```java
// Custom health indicator
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean isHealthy = checkService();
        if (isHealthy) {
            return Health.up().withDetail("service", "available").build();
        }
        return Health.down().withDetail("service", "unavailable").build();
    }
}
```

---

## 91. How do you create custom auto-configuration?

**Answer:**
Create a configuration class with `@Configuration` and `@Conditional` annotations, then register it in `META-INF/spring.factories`.

**Example:**
```java
@Configuration
@ConditionalOnClass(MyService.class)
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService(MyProperties props) {
        return new MyService(props.getName());
    }
}

@ConfigurationProperties(prefix = "my.service")
public class MyProperties {
    private String name;
    // getters/setters
}
```

```properties
# META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.MyAutoConfiguration
```

---

## 92. What is Spring Boot DevTools?

**Answer:**
DevTools provides development-time features like automatic restart, live reload, and configurations for faster development cycle.

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

```properties
# application.properties
spring.devtools.restart.enabled=true
spring.devtools.livereload.enabled=true
```

---

## 93. Explain Spring Security filter chain.

**Answer:**
Security filter chain intercepts requests and applies security checks. Filters include: SecurityContextPersistenceFilter, UsernamePasswordAuthenticationFilter, FilterSecurityInterceptor.

**Example:**
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
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
```

---

## 94. What is authentication vs authorization?

**Answer:**
Authentication verifies who you are (identity). Authorization determines what you can access (permissions).

**Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Authentication - verify user identity
            .httpBasic(Customizer.withDefaults())
            // Authorization - check permissions
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

---

## 95. How do you implement JWT authentication?

**Answer:**
Create JWT token on login, validate token on each request using a filter. Token contains user info and is signed with secret key.

**Example:**
```java
@Component
public class JwtUtil {
    private String secret = "mySecretKey";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token).getBody().getSubject();
    }
}

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            String username = jwtUtil.extractUsername(jwt);
            // Set authentication in SecurityContext
        }
        chain.doFilter(request, response);
    }
}
```

---

## 96. What is OAuth2 and OpenID Connect?

**Answer:**
OAuth2 is an authorization framework for delegated access. OpenID Connect is an identity layer on top of OAuth2 for authentication.

**Example:**
```java
@Configuration
@EnableWebSecurity
public class OAuth2Config {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

```properties
# application.properties
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-secret
spring.security.oauth2.client.registration.google.scope=profile,email
```

---

## 97. Explain OAuth2 authorization flows.

**Answer:**
- **Authorization Code**: For web apps (most secure)
- **Implicit**: For SPAs (deprecated)
- **Client Credentials**: For service-to-service
- **Password**: For trusted apps (not recommended)

**Example:**
```java
// Authorization Code Flow
@RestController
public class OAuth2Controller {
    
    @GetMapping("/login")
    public String login() {
        // Redirect to authorization server
        return "redirect:/oauth2/authorization/google";
    }
    
    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        // Exchange code for access token
        return "success";
    }
}

// Client Credentials Flow
@Configuration
public class OAuth2ClientConfig {
    
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {
        
        OAuth2AuthorizedClientProvider provider = 
            OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();
        
        DefaultOAuth2AuthorizedClientManager manager = 
            new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        manager.setAuthorizedClientProvider(provider);
        return manager;
    }
}
```

---

## 98. What is access token vs refresh token?

**Answer:**
Access token is short-lived token for API access. Refresh token is long-lived token used to obtain new access tokens without re-authentication.

**Example:**
```java
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
}

@Service
public class TokenService {
    
    public TokenResponse generateTokens(String username) {
        String accessToken = generateAccessToken(username); // 15 min
        String refreshToken = generateRefreshToken(username); // 7 days
        return new TokenResponse(accessToken, refreshToken, 900);
    }
    
    public TokenResponse refreshAccessToken(String refreshToken) {
        if (validateRefreshToken(refreshToken)) {
            String username = extractUsername(refreshToken);
            String newAccessToken = generateAccessToken(username);
            return new TokenResponse(newAccessToken, refreshToken, 900);
        }
        throw new InvalidTokenException();
    }
}

@RestController
public class AuthController {
    
    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshRequest request) {
        return tokenService.refreshAccessToken(request.getRefreshToken());
    }
}
```

---

## 99. How do you implement method-level security (`@PreAuthorize`)?

**Answer:**
Enable method security with `@EnableMethodSecurity` and use `@PreAuthorize`, `@PostAuthorize` annotations to secure methods based on expressions.

**Example:**
```java
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig { }

@Service
public class UserService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    @PreAuthorize("#username == authentication.principal.username")
    public void updateProfile(String username, UserDTO dto) {
        // User can only update their own profile
    }
    
    @PostAuthorize("returnObject.owner == authentication.principal.username")
    public Document getDocument(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }
}
```

---

## 100. What is CORS and CSRF? When to disable CSRF?

**Answer:**
CORS (Cross-Origin Resource Sharing) allows requests from different domains. CSRF (Cross-Site Request Forgery) protection prevents unauthorized commands. Disable CSRF for stateless REST APIs using JWT.

**Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Disable CSRF for stateless API
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
```

---

## 101. How do you implement role-based access control?

**Answer:**
Define roles and authorities, assign them to users, and configure access rules using `hasRole()` or `hasAuthority()` in security configuration.

**Example:**
```java
@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}

@Entity
public class Role {
    @Id
    private Long id;
    private String name; // ROLE_USER, ROLE_ADMIN
}

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()))
            .build();
    }
}

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
}

@RestController
public class AdminController {
    
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
```

# ✅ 9) REST API Development & Best Practices

## 102. What are REST constraints?

**Answer:**
REST constraints are: Client-Server (separation), Stateless (no session on server), Cacheable (responses marked cacheable), Uniform Interface (standard HTTP methods), Layered System (intermediaries allowed), Code-on-Demand (optional, server can send executable code).

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // Stateless - no session stored on server
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        // Cacheable - add cache headers
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
            .body(user);
    }
    
    // Uniform Interface - standard HTTP methods
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

---

## 103. Explain HTTP methods and their idempotency.

**Answer:**
- **GET**: Retrieve data (Idempotent, Safe)
- **POST**: Create resource (Not Idempotent)
- **PUT**: Update/Replace resource (Idempotent)
- **PATCH**: Partial update (Not Idempotent)
- **DELETE**: Remove resource (Idempotent)

Idempotent means multiple identical requests have same effect as single request.

**Example:**
```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @GetMapping("/{id}") // Idempotent, Safe
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
    
    @PostMapping // Not Idempotent - creates new resource each time
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}") // Idempotent - same result on multiple calls
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }
    
    @DeleteMapping("/{id}") // Idempotent - deleting already deleted returns same state
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## 104. Difference between PUT and PATCH?

**Answer:**
PUT replaces entire resource (send all fields). PATCH partially updates resource (send only changed fields). PUT is idempotent, PATCH may not be.

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // PUT - Replace entire resource
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // All fields must be provided
        return userService.replaceUser(id, user);
    }
    
    // PATCH - Partial update
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Only changed fields provided
        return userService.partialUpdate(id, updates);
    }
}

// Example requests:
// PUT /api/users/1
// { "name": "John", "email": "john@example.com", "age": 30 } // All fields

// PATCH /api/users/1
// { "email": "newemail@example.com" } // Only changed field
```

---

## 105. Explain HTTP status codes (200, 201, 204, 400, 401, 403, 404, 500).

**Answer:**
- **200 OK**: Success
- **201 Created**: Resource created
- **204 No Content**: Success, no response body
- **400 Bad Request**: Invalid input
- **401 Unauthorized**: Authentication required
- **403 Forbidden**: No permission
- **404 Not Found**: Resource doesn't exist
- **500 Internal Server Error**: Server error

**Example:**
```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.findById(id)
            .map(order -> ResponseEntity.ok(order)) // 200
            .orElse(ResponseEntity.notFound().build()); // 404
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order created = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Invalid input")); // 400
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Auth required")); // 401
    }
    
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("No permission")); // 403
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Server error")); // 500
    }
}
```

---

## 106. How do you design RESTful URIs?

**Answer:**
Use nouns (not verbs), plural names, hierarchical structure, lowercase, hyphens for readability. Avoid file extensions and query strings for resource identification.

**Example:**
```java
@RestController
@RequestMapping("/api")
public class RestfulController {
    
    // Good: /api/users (plural noun)
    @GetMapping("/users")
    public List<User> getUsers() { }
    
    // Good: /api/users/123
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) { }
    
    // Good: Hierarchical - /api/users/123/orders
    @GetMapping("/users/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable Long userId) { }
    
    // Good: /api/users/123/orders/456
    @GetMapping("/users/{userId}/orders/{orderId}")
    public Order getUserOrder(@PathVariable Long userId, @PathVariable Long orderId) { }
    
    // Good: Query params for filtering
    @GetMapping("/products")
    public List<Product> getProducts(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Double minPrice) { }
    
    // Bad examples (avoid):
    // /api/getUsers (verb)
    // /api/user (singular)
    // /api/users.json (file extension)
}
```

---

## 107. What are API versioning strategies?

**Answer:**
- **URI versioning**: `/api/v1/users`
- **Header versioning**: `Accept: application/vnd.api.v1+json`
- **Query parameter**: `/api/users?version=1`
- **Content negotiation**: Custom media types

**Example:**
```java
// 1. URI Versioning (Most common)
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    @GetMapping
    public List<UserV1> getUsers() {
        return userService.getAllUsersV1();
    }
}

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
    @GetMapping
    public List<UserV2> getUsers() {
        return userService.getAllUsersV2();
    }
}

// 2. Header Versioning
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping(headers = "X-API-VERSION=1")
    public List<UserV1> getUsersV1() {
        return userService.getAllUsersV1();
    }
    
    @GetMapping(headers = "X-API-VERSION=2")
    public List<UserV2> getUsersV2() {
        return userService.getAllUsersV2();
    }
}

// 3. Query Parameter Versioning
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int version) {
        if (version == 1) {
            return ResponseEntity.ok(userService.getAllUsersV1());
        }
        return ResponseEntity.ok(userService.getAllUsersV2());
    }
}
```

---

## 108. How do you implement pagination, sorting, filtering?

**Answer:**
Use query parameters for pagination (page, size), sorting (sort), and filtering (field-specific params). Spring Data provides Pageable interface.

**Example:**
```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    // Pagination, Sorting, Filtering
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id,asc") String[] sort) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(
            sort[1].equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
            sort[0]
        ));
        
        Page<Product> products = productService.findProducts(category, minPrice, pageable);
        return ResponseEntity.ok(products);
    }
}

// Service
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    
    public Page<Product> findProducts(String category, Double minPrice, Pageable pageable) {
        if (category != null && minPrice != null) {
            return repository.findByCategoryAndPriceGreaterThan(category, minPrice, pageable);
        }
        return repository.findAll(pageable);
    }
}

// Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryAndPriceGreaterThan(String category, Double price, Pageable pageable);
}

// Request: GET /api/products?category=electronics&minPrice=100&page=0&size=20&sort=price,desc
```

---

## 109. What is HATEOAS?

**Answer:**
HATEOAS (Hypermedia As The Engine Of Application State) means API responses include links to related resources, making API self-descriptive and discoverable.

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        
        EntityModel<User> resource = EntityModel.of(user);
        
        // Add links
        resource.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).getUserOrders(id)).withRel("orders"));
        resource.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        
        return resource;
    }
    
    @GetMapping("/{id}/orders")
    public List<Order> getUserOrders(@PathVariable Long id) {
        return orderService.findByUserId(id);
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}

// Response:
// {
//   "id": 1,
//   "name": "John",
//   "_links": {
//     "self": { "href": "/api/users/1" },
//     "orders": { "href": "/api/users/1/orders" },
//     "all-users": { "href": "/api/users" }
//   }
// }
```

---

## 110. How do you handle error responses consistently?

**Answer:**
Use `@ControllerAdvice` with `@ExceptionHandler` to create global exception handling. Return consistent error response structure with status, message, timestamp.

**Example:**
```java
// Error Response DTO
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    
    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
    // getters/setters
}

// Global Exception Handler
@ControllerAdvice
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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message,
            request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
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

---

## 111. What is content negotiation?

**Answer:**
Content negotiation allows client to specify desired response format (JSON, XML) using Accept header. Server responds with appropriate format based on client preference.

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // Produces JSON or XML based on Accept header
    @GetMapping(value = "/{id}", 
                produces = {MediaType.APPLICATION_JSON_VALUE, 
                           MediaType.APPLICATION_XML_VALUE})
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // Consumes JSON or XML based on Content-Type header
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE, 
                           MediaType.APPLICATION_XML_VALUE})
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}

// Configuration
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
            .favorParameter(false)
            .ignoreAcceptHeader(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML);
    }
}

// Request with JSON: Accept: application/json
// Request with XML: Accept: application/xml
```

---

## 112. How do you document APIs (Swagger/OpenAPI)?

**Answer:**
Use SpringDoc OpenAPI (Swagger) to auto-generate API documentation. Add annotations for detailed descriptions. Access UI at `/swagger-ui.html`.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```

```java
// Configuration
@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("User API")
                .version("1.0")
                .description("User management API"));
    }
}

// Controller with documentation
@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {
    
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public User getUser(
        @Parameter(description = "User ID") @PathVariable Long id) {
        return userService.findById(id);
    }
    
    @Operation(summary = "Create new user")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}

// DTO with schema documentation
public class User {
    @Schema(description = "User ID", example = "1")
    private Long id;
    
    @Schema(description = "User name", example = "John Doe")
    private String name;
    
    @Schema(description = "Email address", example = "john@example.com")
    private String email;
}

// Access: http://localhost:8080/swagger-ui.html
```

---

## 113. How do you implement rate limiting?

**Answer:**
Rate limiting restricts number of requests per time window. Implement using filters, interceptors, or libraries like Bucket4j. Return 429 (Too Many Requests) when limit exceeded.

**Example:**
```java
// Using Bucket4j
@Component
public class RateLimitFilter extends OncePerRequestFilter {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String clientId = getClientId(request);
        Bucket bucket = resolveBucket(clientId);
        
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded");
        }
    }
    
    private Bucket resolveBucket(String clientId) {
        return cache.computeIfAbsent(clientId, k -> createBucket());
    }
    
    private Bucket createBucket() {
        // 10 requests per minute
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
    
    private String getClientId(HttpServletRequest request) {
        return request.getRemoteAddr(); // or use API key
    }
}

// Simple implementation without library
@Component
public class SimpleRateLimiter {
    private final Map<String, List<Long>> requestCounts = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 10;
    private static final long TIME_WINDOW = 60000; // 1 minute
    
    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        requestCounts.putIfAbsent(clientId, new ArrayList<>());
        
        List<Long> timestamps = requestCounts.get(clientId);
        timestamps.removeIf(time -> now - time > TIME_WINDOW);
        
        if (timestamps.size() < MAX_REQUESTS) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
```

---

## 114. What is API Gateway?

**Answer:**
API Gateway is a single entry point for all client requests. It handles routing, authentication, rate limiting, load balancing, and request/response transformation.

**Example:**
```java
// Using Spring Cloud Gateway
@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Route to User Service
            .route("user-service", r -> r
                .path("/api/users/**")
                .filters(f -> f
                    .addRequestHeader("X-Gateway", "true")
                    .circuitBreaker(c -> c.setName("userServiceCB"))
                )
                .uri("lb://USER-SERVICE"))
            
            // Route to Order Service
            .route("order-service", r -> r
                .path("/api/orders/**")
                .filters(f -> f
                    .rewritePath("/api/orders/(?<segment>.*)", "/orders/${segment}")
                    .addResponseHeader("X-Response-Time", String.valueOf(System.currentTimeMillis()))
                )
                .uri("lb://ORDER-SERVICE"))
            
            // Route with rate limiting
            .route("product-service", r -> r
                .path("/api/products/**")
                .filters(f -> f
                    .requestRateLimiter(c -> c
                        .setRateLimiter(redisRateLimiter())
                    )
                )
                .uri("lb://PRODUCT-SERVICE"))
            .build();
    }
    
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20); // replenishRate, burstCapacity
    }
}

// Global Filter for authentication
@Component
public class AuthenticationFilter implements GlobalFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }
    
    private boolean validateToken(String token) {
        // Validate JWT token
        return true;
    }
}

// application.yml
// spring:
//   cloud:
//     gateway:
//       routes:
//         - id: user-service
//           uri: lb://USER-SERVICE
//           predicates:
//             - Path=/api/users/**
```

# ✅ 10) Microservices Basics and Architecture

## 115. Monolithic vs microservices architecture.

**Answer:** Monolithic is a single deployable unit where all features are tightly coupled. Microservices split the application into independent services, each owning its domain and deployable separately.

**Example:**
```java
// Monolithic - All in one application
@SpringBootApplication
public class EcommerceApp {
    // Order, Payment, Inventory all in same codebase
}

// Microservices - Separate services
@SpringBootApplication
public class OrderService { }

@SpringBootApplication
public class PaymentService { }

@SpringBootApplication
public class InventoryService { }
```

---

## 116. When should you NOT use microservices?

**Answer:** Avoid microservices for small teams, simple applications, startups with unclear requirements, or when operational complexity exceeds benefits. Use monolith first.

**Example:**
```java
// DON'T use microservices for:
// - Team < 5 developers
// - Simple CRUD app
// - Tight budget/timeline
// - No DevOps expertise

// Start with modular monolith
@SpringBootApplication
public class SimpleApp {
    // Keep modules separated but in one deployment
}
```

---

## 117. How do you decompose a monolith?

**Answer:** Identify bounded contexts, extract services by business capability, use strangler pattern to gradually migrate functionality while keeping the monolith running.

**Example:**
```java
// Step 1: Identify bounded context
// Monolith has: User, Order, Payment

// Step 2: Extract one service
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping("/orders")
    public Order create(@RequestBody Order order) {
        // Call new PaymentService instead of internal method
        restTemplate.postForObject("http://payment-service/pay", payment, Response.class);
        return orderRepo.save(order);
    }
}
```

---

## 118. What is Domain-Driven Design (DDD)?

**Answer:** DDD is an approach to software design that focuses on modeling business domains. Key concepts: Bounded Context, Entities, Aggregates, Value Objects, and Ubiquitous Language.

**Example:**
```java
// Bounded Context: Order Management
@Entity
public class Order {  // Aggregate Root
    @Id
    private Long id;
    private List<OrderItem> items;  // Entity
    private Money totalAmount;  // Value Object
}

@Embeddable
public class Money {  // Value Object
    private BigDecimal amount;
    private String currency;
}
```

---

## 119. What is service discovery (Eureka, Consul)?

**Answer:** Service discovery automatically detects network locations of service instances. Services register themselves, and clients query the registry to find available instances.

**Example:**
```java
// Eureka Server
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApp { }

// Service Registration
@EnableEurekaClient
@SpringBootApplication
public class OrderService { }

// application.yml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

---

## 120. Explain API Gateway pattern.

**Answer:** API Gateway is a single entry point for all clients. It routes requests, aggregates responses, handles authentication, rate limiting, and protocol translation.

**Example:**
```java
// Spring Cloud Gateway
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("order", r -> r.path("/orders/**")
                .uri("lb://ORDER-SERVICE"))
            .route("payment", r -> r.path("/payments/**")
                .uri("lb://PAYMENT-SERVICE"))
            .build();
    }
}
```

---

## 121. What is Circuit Breaker pattern (Resilience4j)?

**Answer:** Circuit Breaker prevents cascading failures by stopping calls to failing services. States: CLOSED (normal), OPEN (failing), HALF_OPEN (testing recovery).

**Example:**
```java
@Service
public class OrderService {
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public Payment processPayment(Order order) {
        return restTemplate.postForObject("http://payment-service/pay", order, Payment.class);
    }
    
    public Payment paymentFallback(Order order, Exception e) {
        return new Payment("PENDING");
    }
}

// application.yml
resilience4j.circuitbreaker:
  instances:
    paymentService:
      failure-rate-threshold: 50
      wait-duration-in-open-state: 10000
```

---

## 122. Explain Saga pattern (choreography vs orchestration).

**Answer:** Saga manages distributed transactions across services. Choreography: services publish events, others react. Orchestration: central coordinator directs the flow.

**Example:**
```java
// Choreography - Event-driven
@Service
public class OrderService {
    public void createOrder(Order order) {
        orderRepo.save(order);
        eventPublisher.publish(new OrderCreatedEvent(order));
    }
}

@Service
public class PaymentService {
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        processPayment(event.getOrder());
    }
}

// Orchestration - Central coordinator
@Service
public class OrderSaga {
    public void execute(Order order) {
        orderService.create(order);
        paymentService.process(order);
        inventoryService.reserve(order);
    }
}
```

---

## 123. How do you handle inter-service communication?

**Answer:** Use synchronous (REST, gRPC) for immediate responses or asynchronous (messaging) for decoupling. Choose based on latency, coupling, and reliability needs.

**Example:**
```java
// Synchronous - REST
@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    
    public Order create(Order order) {
        Payment payment = restTemplate.postForObject(
            "http://payment-service/pay", order, Payment.class);
        return orderRepo.save(order);
    }
}

// Asynchronous - Kafka
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafka;
    
    public void create(Order order) {
        orderRepo.save(order);
        kafka.send("order-created", order);
    }
}
```

---

## 124. REST vs messaging (Kafka, RabbitMQ) - when to use which?

**Answer:** Use REST for synchronous, request-response scenarios. Use messaging for asynchronous, event-driven, high-throughput, or decoupled communication.

**Example:**
```java
// REST - Immediate response needed
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {
    return orderService.findById(id);
}

// Kafka - Fire and forget, high volume
@Service
public class NotificationService {
    @KafkaListener(topics = "order-created")
    public void sendEmail(Order order) {
        emailService.send(order.getCustomerEmail());
    }
}
```

---

## 125. What is event-driven architecture?

**Answer:** Event-driven architecture uses events to trigger and communicate between services. Services publish events when state changes; others subscribe and react asynchronously.

**Example:**
```java
// Event Publisher
@Service
public class OrderService {
    @Autowired
    private ApplicationEventPublisher publisher;
    
    public void placeOrder(Order order) {
        orderRepo.save(order);
        publisher.publishEvent(new OrderPlacedEvent(order));
    }
}

// Event Listener
@Service
public class InventoryService {
    @EventListener
    public void reserveStock(OrderPlacedEvent event) {
        inventoryRepo.reserve(event.getOrder().getItems());
    }
}
```

---

## 126. How do you implement distributed tracing?

**Answer:** Distributed tracing tracks requests across services using trace IDs. Tools like Zipkin or Jaeger collect and visualize traces to identify bottlenecks.

**Example:**
```java
// Add dependency: spring-cloud-starter-zipkin

// application.yml
spring:
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      probability: 1.0

// Automatic tracing
@RestController
public class OrderController {
    @GetMapping("/orders/{id}")
    public Order get(@PathVariable Long id) {
        // Trace ID automatically propagated
        return orderService.findById(id);
    }
}
```

---

## 127. What is correlation ID?

**Answer:** Correlation ID is a unique identifier passed through all services in a request chain, enabling log aggregation and request tracking across distributed systems.

**Example:**
```java
@Component
public class CorrelationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String correlationId = request.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put("correlationId", correlationId);
        return true;
    }
}

// Logging
log.info("Processing order"); // Logs: [correlationId=abc-123] Processing order
```

---

## 128. What is Spring Cloud Config Server?

**Answer:** Spring Cloud Config Server provides centralized external configuration management for distributed systems. Services fetch configuration from a central server backed by Git.

**Example:**
```java
// Config Server
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp { }

// application.yml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/myorg/config-repo

// Client Service
@SpringBootApplication
public class OrderService { }

// bootstrap.yml
spring:
  cloud:
    config:
      uri: http://localhost:8888
      name: order-service
```

---

## 129. What is database per service pattern?

**Answer:** Each microservice owns its database, ensuring loose coupling and independent scaling. Services cannot directly access other service databases; use APIs or events.

**Example:**
```java
// Order Service - owns order_db
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
}

// Payment Service - owns payment_db
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private Long id;
}

// Communication via API, not direct DB access
@Service
public class OrderService {
    public Order create(Order order) {
        orderRepo.save(order);
        // Call Payment Service API, not its DB
        restTemplate.post("http://payment-service/pay", payment);
        return order;
    }
}
```

# ✅ 11) Database Design & Optimization (SQL + JPA/Hibernate)

## 130. What is normalization (1NF, 2NF, 3NF)?

**Answer:** Normalization eliminates data redundancy. 1NF: atomic values, no repeating groups. 2NF: 1NF + no partial dependencies. 3NF: 2NF + no transitive dependencies.

**Example:**
```sql
-- Unnormalized
CREATE TABLE orders (
    id INT,
    customer_name VARCHAR(100),
    products VARCHAR(500)  -- "Laptop,Mouse,Keyboard"
);

-- 1NF: Atomic values
CREATE TABLE orders (
    id INT,
    customer_name VARCHAR(100),
    product VARCHAR(100)
);

-- 3NF: Separate entities
CREATE TABLE customers (id INT, name VARCHAR(100));
CREATE TABLE orders (id INT, customer_id INT);
CREATE TABLE order_items (order_id INT, product_id INT);
```

---

## 131. When should you denormalize?

**Answer:** Denormalize for read-heavy systems, reporting, reducing joins, or when query performance is critical. Trade consistency for speed.

**Example:**
```java
// Normalized - Multiple joins
@Entity
public class Order {
    @ManyToOne
    private Customer customer;
}

// Denormalized - Duplicate customer name for faster reads
@Entity
public class Order {
    @ManyToOne
    private Customer customer;
    private String customerName;  // Denormalized
    private String customerEmail; // Denormalized
}
```

---

## 132. Explain ACID properties.

**Answer:** ACID ensures reliable transactions. Atomicity: all or nothing. Consistency: valid state. Isolation: concurrent transactions don't interfere. Durability: committed data persists.

**Example:**
```java
@Service
public class BankService {
    @Transactional  // ACID guaranteed
    public void transfer(Account from, Account to, BigDecimal amount) {
        from.debit(amount);      // Atomicity: both succeed or both fail
        to.credit(amount);       // Consistency: balance rules maintained
        // Isolation: other transactions see consistent state
        // Durability: committed changes survive crashes
    }
}
```

---

## 133. What are database indexes and how do they work?

**Answer:** Indexes are data structures (B-Tree, Hash) that speed up data retrieval by creating pointers to table rows, trading write speed for read speed.

**Example:**
```sql
-- Without index: Full table scan O(n)
SELECT * FROM users WHERE email = 'john@example.com';

-- Create index
CREATE INDEX idx_email ON users(email);

-- With index: O(log n) lookup
SELECT * FROM users WHERE email = 'john@example.com';
```

```java
@Entity
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class User {
    @Column(unique = true)
    private String email;
}
```

---

## 134. Clustered vs non-clustered index.

**Answer:** Clustered index determines physical row order (one per table, usually primary key). Non-clustered index creates separate structure with pointers (multiple allowed).

**Example:**
```sql
-- Clustered: Data sorted by primary key
CREATE TABLE users (
    id INT PRIMARY KEY,  -- Clustered index
    name VARCHAR(100)
);

-- Non-clustered: Separate index structure
CREATE INDEX idx_name ON users(name);  -- Non-clustered

-- Query uses non-clustered, then looks up clustered
SELECT * FROM users WHERE name = 'John';
```

---

## 135. How do you optimize slow SQL queries?

**Answer:** Add indexes, avoid SELECT *, use EXPLAIN, limit results, optimize joins, use covering indexes, partition tables, and cache results.

**Example:**
```sql
-- Slow: No index, SELECT *
SELECT * FROM orders WHERE customer_id = 123;

-- Optimized
CREATE INDEX idx_customer ON orders(customer_id);
SELECT id, total, created_at FROM orders WHERE customer_id = 123 LIMIT 100;

-- Use EXPLAIN to analyze
EXPLAIN SELECT id FROM orders WHERE customer_id = 123;
```

---

## 136. What is the N+1 query problem? How to fix it?

**Answer:** N+1 occurs when fetching a list (1 query) then loading related entities individually (N queries). Fix with JOIN FETCH or entity graphs.

**Example:**
```java
// N+1 Problem: 1 + N queries
List<Order> orders = orderRepo.findAll();  // 1 query
for (Order order : orders) {
    order.getCustomer().getName();  // N queries
}

// Solution 1: JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.customer")
List<Order> findAllWithCustomer();

// Solution 2: Entity Graph
@EntityGraph(attributePaths = {"customer"})
List<Order> findAll();
```

---

## 137. Explain transaction isolation levels.

**Answer:** Isolation levels control concurrent transaction visibility. READ_UNCOMMITTED (dirty reads), READ_COMMITTED (default), REPEATABLE_READ (consistent reads), SERIALIZABLE (full isolation).

**Example:**
```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void processOrder(Long orderId) {
    // Sees only committed data
}

@Transactional(isolation = Isolation.REPEATABLE_READ)
public void generateReport() {
    // Same query returns same results within transaction
}

@Transactional(isolation = Isolation.SERIALIZABLE)
public void criticalOperation() {
    // Full isolation, prevents all anomalies
}
```

---

## 138. What is dirty read, non-repeatable read, phantom read?

**Answer:** Dirty read: reading uncommitted data. Non-repeatable read: same query returns different results. Phantom read: new rows appear in range queries.

**Example:**
```java
// Dirty Read (READ_UNCOMMITTED)
// T1: UPDATE balance = 100 (not committed)
// T2: SELECT balance -> sees 100 (dirty)

// Non-repeatable Read (READ_COMMITTED)
// T1: SELECT balance -> 100
// T2: UPDATE balance = 200, COMMIT
// T1: SELECT balance -> 200 (different)

// Phantom Read (REPEATABLE_READ)
// T1: SELECT COUNT(*) WHERE age > 20 -> 5
// T2: INSERT user age=25, COMMIT
// T1: SELECT COUNT(*) WHERE age > 20 -> 6 (phantom)
```

---

## 139. Optimistic locking vs pessimistic locking.

**Answer:** Optimistic: assumes no conflicts, checks version on update. Pessimistic: locks row immediately. Use optimistic for low contention, pessimistic for high contention.

**Example:**
```java
// Optimistic Locking
@Entity
public class Product {
    @Id
    private Long id;
    @Version
    private Long version;  // Auto-incremented on update
    private Integer stock;
}

// Pessimistic Locking
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdWithLock(@Param("id") Long id);
```

---

## 140. How do you implement optimistic locking in JPA (`@Version`)?

**Answer:** Add @Version field. JPA automatically checks version on update and throws OptimisticLockException if changed by another transaction.

**Example:**
```java
@Entity
public class Account {
    @Id
    private Long id;
    @Version
    private Long version;
    private BigDecimal balance;
}

@Service
public class AccountService {
    @Transactional
    public void withdraw(Long id, BigDecimal amount) {
        Account account = accountRepo.findById(id).get();
        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);  // Throws OptimisticLockException if version changed
    }
}
```

---

## 141. Explain JPA entity lifecycle states.

**Answer:** Transient: new object, not tracked. Managed: tracked by EntityManager. Detached: was managed, now disconnected. Removed: marked for deletion.

**Example:**
```java
// Transient
User user = new User("John");

// Managed
entityManager.persist(user);
user.setName("Jane");  // Auto-synced to DB

// Detached
entityManager.detach(user);
user.setName("Bob");  // Not synced

// Removed
entityManager.remove(user);  // Deleted on commit
```

---

## 142. Difference between `persist()`, `merge()`, `save()`.

**Answer:** persist(): makes transient entity managed. merge(): copies detached entity to managed. save(): Hibernate method, returns ID immediately.

**Example:**
```java
// persist() - Transient to Managed
User user = new User("John");
entityManager.persist(user);  // Now managed

// merge() - Detached to Managed
User detached = new User("Jane");
detached.setId(1L);
User managed = entityManager.merge(detached);  // Returns managed copy

// save() - Hibernate specific
User user = new User("Bob");
Long id = session.save(user);  // Returns generated ID
```

---

## 143. Explain JPA relationships (`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`).

**Answer:** OneToOne: single entity on both sides. OneToMany/ManyToOne: parent-child. ManyToMany: multiple on both sides via join table.

**Example:**
```java
// OneToOne
@Entity
public class User {
    @OneToOne
    private Profile profile;
}

// OneToMany / ManyToOne
@Entity
public class Order {
    @ManyToOne
    private Customer customer;
}

@Entity
public class Customer {
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}

// ManyToMany
@Entity
public class Student {
    @ManyToMany
    @JoinTable(name = "student_course")
    private List<Course> courses;
}
```

---

## 144. What is owning side vs inverse side?

**Answer:** Owning side has the foreign key and controls the relationship. Inverse side uses mappedBy. Changes on owning side are persisted.

**Example:**
```java
// Owning side: Has @JoinColumn
@Entity
public class Order {
    @ManyToOne
    @JoinColumn(name = "customer_id")  // Owning side
    private Customer customer;
}

// Inverse side: Has mappedBy
@Entity
public class Customer {
    @OneToMany(mappedBy = "customer")  // Inverse side
    private List<Order> orders;
}

// Only changes on owning side persist
order.setCustomer(customer);  // Persisted
customer.getOrders().add(order);  // Not persisted unless owning side updated
```

---

## 145. Lazy loading vs eager loading - when to use which?

**Answer:** Lazy: loads related entities on access (default for collections). Eager: loads immediately (default for single entities). Use lazy to avoid unnecessary queries.

**Example:**
```java
// Lazy Loading (default for @OneToMany, @ManyToMany)
@Entity
public class Customer {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;  // Loaded when accessed
}

// Eager Loading (default for @ManyToOne, @OneToOne)
@Entity
public class Order {
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;  // Loaded immediately
}

// Use lazy for large collections
// Use eager for frequently accessed single entities
```

---

## 146. How do you handle LazyInitializationException?

**Answer:** Occurs when accessing lazy entity outside transaction. Fix: use JOIN FETCH, @Transactional, entity graphs, or DTO projections.

**Example:**
```java
// Problem: LazyInitializationException
@Service
public class OrderService {
    public Order getOrder(Long id) {
        return orderRepo.findById(id).get();
    }  // Transaction ends
}
// controller: order.getCustomer().getName() -> Exception!

// Solution 1: JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.customer WHERE o.id = :id")
Order findByIdWithCustomer(@Param("id") Long id);

// Solution 2: @Transactional on controller method
@Transactional(readOnly = true)
public OrderDTO getOrder(Long id) {
    Order order = orderRepo.findById(id).get();
    return new OrderDTO(order, order.getCustomer());
}
```

---

## 147. Explain `@Transactional` and propagation levels.

**Answer:** @Transactional manages transactions. Propagation: REQUIRED (default, join existing), REQUIRES_NEW (new transaction), NESTED (savepoint), SUPPORTS, MANDATORY, NEVER, NOT_SUPPORTED.

**Example:**
```java
@Service
public class OrderService {
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(Order order) {
        orderRepo.save(order);
        paymentService.processPayment(order);  // Joins this transaction
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAudit(String action) {
        auditRepo.save(new Audit(action));  // New transaction, commits independently
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void updateInventory(Long productId) {
        // Creates savepoint, can rollback without affecting parent
    }
}
```

---

## 148. JPQL vs Criteria API vs native SQL.

**Answer:** JPQL: object-oriented query language. Criteria API: type-safe programmatic queries. Native SQL: database-specific SQL. Use JPQL for simple, Criteria for dynamic, native for complex.

**Example:**
```java
// JPQL
@Query("SELECT o FROM Order o WHERE o.customer.name = :name")
List<Order> findByCustomerName(@Param("name") String name);

// Criteria API
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Order> query = cb.createQuery(Order.class);
Root<Order> order = query.from(Order.class);
query.select(order).where(cb.equal(order.get("status"), "PENDING"));
List<Order> orders = em.createQuery(query).getResultList();

// Native SQL
@Query(value = "SELECT * FROM orders WHERE status = ?1", nativeQuery = true)
List<Order> findByStatusNative(String status);
```

---

## 149. How do you implement pagination in JPA?

**Answer:** Use Pageable parameter with Spring Data JPA or setFirstResult/setMaxResults with EntityManager.

**Example:**
```java
// Spring Data JPA
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByStatus(String status, Pageable pageable);
}

@Service
public class OrderService {
    public Page<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderRepo.findByStatus("PENDING", pageable);
    }
}

// EntityManager
TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
query.setFirstResult(page * size);
query.setMaxResults(size);
List<Order> orders = query.getResultList();
```

---

## 150. How do you implement batch processing in JPA?

**Answer:** Configure batch size, flush and clear EntityManager periodically to avoid memory issues when processing large datasets.

**Example:**
```java
// application.properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

@Service
public class BatchService {
    @Transactional
    public void importOrders(List<Order> orders) {
        int batchSize = 50;
        for (int i = 0; i < orders.size(); i++) {
            entityManager.persist(orders.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();  // Prevent memory issues
            }
        }
    }
}
```

# ✅ 12) Caching Strategies (Redis / Distributed Cache)

## 151. What is caching and why is it important?

**Answer:** Caching stores frequently accessed data in fast-access memory to reduce database load and improve response time.

**Example:**
```java
// Without cache: DB call every time
User user = userRepository.findById(1L); // 100ms

// With cache: DB call once, then memory
User user = cache.get("user:1"); // 1ms
```

---

## 152. Explain cache-aside, write-through, write-behind patterns.

**Answer:**
- **Cache-Aside**: App checks cache first, loads from DB on miss, then updates cache
- **Write-Through**: Write to cache and DB simultaneously
- **Write-Behind**: Write to cache immediately, DB asynchronously

**Example:**
```java
// Cache-Aside
public User getUser(Long id) {
    User user = cache.get("user:" + id);
    if (user == null) {
        user = db.findById(id);
        cache.put("user:" + id, user);
    }
    return user;
}

// Write-Through
public void saveUser(User user) {
    db.save(user);
    cache.put("user:" + user.getId(), user);
}
```

---

## 153. Local cache vs distributed cache - when to use which?

**Answer:**
- **Local Cache**: In-memory (Caffeine, Guava), single instance, fast but not shared
- **Distributed Cache**: Shared across instances (Redis), consistent but network latency

**Example:**
```java
// Local Cache - for static data
@Cacheable(cacheNames = "countries")
public List<String> getCountries() { }

// Distributed Cache - for user sessions
@Cacheable(cacheNames = "userSessions", cacheManager = "redisCacheManager")
public Session getUserSession(String token) { }
```

---

## 154. How does Redis work? What data structures does it support?

**Answer:** Redis is an in-memory key-value store. Supports: String, List, Set, Sorted Set, Hash, Bitmap, HyperLogLog, Stream.

**Example:**
```java
// String
redisTemplate.opsForValue().set("user:1", "John");

// Hash
redisTemplate.opsForHash().put("user:1", "name", "John");

// List
redisTemplate.opsForList().rightPush("queue", "task1");

// Set
redisTemplate.opsForSet().add("tags", "java", "spring");
```

---

## 155. What is cache eviction policy (LRU, LFU)?

**Answer:**
- **LRU (Least Recently Used)**: Removes least recently accessed items
- **LFU (Least Frequently Used)**: Removes least frequently accessed items

**Example:**
```java
@Bean
public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterAccess(10, TimeUnit.MINUTES)); // LRU-like
    return cacheManager;
}
```

---

## 156. How do you implement caching in Spring Boot (`@Cacheable`, `@CachePut`, `@CacheEvict`)?

**Answer:**
- **@Cacheable**: Caches method result
- **@CachePut**: Updates cache
- **@CacheEvict**: Removes from cache

**Example:**
```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @CachePut(value = "users", key = "#user.id")
    public User update(User user) {
        return userRepository.save(user);
    }
    
    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
```

---

## 157. What is cache stampede? How to prevent it?

**Answer:** Cache stampede occurs when many requests simultaneously try to regenerate an expired cache entry, overwhelming the database.

**Prevention:** Use locking, stale-while-revalidate, or probabilistic early expiration.

**Example:**
```java
// Using synchronized to prevent stampede
private final Object lock = new Object();

public User getUser(Long id) {
    User user = cache.get("user:" + id);
    if (user == null) {
        synchronized (lock) {
            user = cache.get("user:" + id); // Double-check
            if (user == null) {
                user = db.findById(id);
                cache.put("user:" + id, user);
            }
        }
    }
    return user;
}

// Or use @Cacheable with sync
@Cacheable(value = "users", key = "#id", sync = true)
public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
}
```

---

## 158. How do you handle cache invalidation?

**Answer:** Invalidate cache on data updates using TTL (Time-To-Live), event-based invalidation, or manual eviction.

**Example:**
```java
@Service
public class UserService {
    
    @Autowired
    private CacheManager cacheManager;
    
    // TTL-based
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // Event-based invalidation
    @CacheEvict(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    // Manual invalidation
    public void clearUserCache(Long userId) {
        Cache cache = cacheManager.getCache("users");
        if (cache != null) {
            cache.evict(userId);
        }
    }
    
    // Clear all
    @CacheEvict(value = "users", allEntries = true)
    public void clearAllUsers() { }
}
```

---

## 159. What is Redis Cluster vs Redis Sentinel?

**Answer:**
- **Redis Sentinel**: Provides high availability through monitoring, automatic failover, and master-slave replication
- **Redis Cluster**: Provides horizontal scaling with data sharding across multiple nodes

**Example:**
```java
// Redis Sentinel Configuration
@Configuration
public class RedisSentinelConfig {
    @Bean
    public RedisConnectionFactory sentinelConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = 
            new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
    }
}

// Redis Cluster Configuration
@Configuration
public class RedisClusterConfig {
    @Bean
    public RedisConnectionFactory clusterConnectionFactory() {
        RedisClusterConfiguration clusterConfig = 
            new RedisClusterConfiguration()
                .clusterNode("127.0.0.1", 7000)
                .clusterNode("127.0.0.1", 7001)
                .clusterNode("127.0.0.1", 7002);
        return new LettuceConnectionFactory(clusterConfig);
    }
}
```

# ✅ 13) Security (Basic – JWT, Spring Security)

## 160. What are OWASP Top 10 vulnerabilities?

**Answer:** OWASP Top 10 lists the most critical web application security risks: Broken Access Control, Cryptographic Failures, Injection, Insecure Design, Security Misconfiguration, Vulnerable Components, Authentication Failures, Data Integrity Failures, Logging Failures, and SSRF.

**Example:**
```java
// Broken Access Control - BAD
@GetMapping("/user/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id); // Any user can access any ID
}

// Fixed with proper authorization
@GetMapping("/user/{id}")
@PreAuthorize("@userSecurity.canAccessUser(#id)")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

---

## 161. What is SQL injection? How to prevent it?

**Answer:** SQL injection occurs when untrusted input is directly concatenated into SQL queries, allowing attackers to execute malicious SQL. Prevent using parameterized queries or prepared statements.

**Example:**
```java
// Vulnerable to SQL Injection - BAD
String query = "SELECT * FROM users WHERE username = '" + username + "'";

// Safe - Using PreparedStatement
String query = "SELECT * FROM users WHERE username = ?";
PreparedStatement stmt = connection.prepareStatement(query);
stmt.setString(1, username);

// Safe - Using JPA
@Query("SELECT u FROM User u WHERE u.username = :username")
User findByUsername(@Param("username") String username);
```

---

## 162. What is XSS and CSRF?

**Answer:**
- **XSS (Cross-Site Scripting)**: Injecting malicious scripts into web pages viewed by other users
- **CSRF (Cross-Site Request Forgery)**: Forcing authenticated users to execute unwanted actions

**Example:**
```java
// XSS Prevention - Escape output
@GetMapping("/profile")
public String profile(Model model, @RequestParam String name) {
    model.addAttribute("name", HtmlUtils.htmlEscape(name)); // Escape HTML
    return "profile";
}

// CSRF Prevention - Spring Security enables by default
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        return http.build();
    }
}
```

---

## 163. Explain JWT structure (header.payload.signature).

**Answer:** JWT has three parts separated by dots:
- **Header**: Algorithm and token type
- **Payload**: Claims (user data)
- **Signature**: Verification hash

**Example:**
```java
// JWT Structure: xxxxx.yyyyy.zzzzz

// Header (Base64 encoded)
{
  "alg": "HS256",
  "typ": "JWT"
}

// Payload (Base64 encoded)
{
  "sub": "user123",
  "name": "John Doe",
  "exp": 1735689600
}

// Signature
HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  secret
)

// Creating JWT
String jwt = Jwts.builder()
    .setSubject("user123")
    .claim("name", "John Doe")
    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
    .signWith(SignatureAlgorithm.HS256, "secretKey")
    .compact();
```

---

## 164. How do you validate JWT tokens?

**Answer:** Validate JWT by verifying signature, checking expiration, and validating claims.

**Example:**
```java
@Component
public class JwtTokenValidator {
    
    private final String SECRET_KEY = "mySecretKey";
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false; // Token expired
        } catch (JwtException e) {
            return false; // Invalid token
        }
    }
    
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
}
```

---

## 165. Stateless vs stateful authentication.

**Answer:**
- **Stateless**: No server-side session storage, uses tokens (JWT). Scalable but token can't be revoked easily
- **Stateful**: Server stores session data. Easy to revoke but requires session storage

**Example:**
```java
// Stateless - JWT based
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    authenticate(request.getUsername(), request.getPassword());
    String token = jwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(new JwtResponse(token));
}

// Stateful - Session based
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
    User user = authenticate(request.getUsername(), request.getPassword());
    session.setAttribute("user", user); // Stored on server
    return ResponseEntity.ok("Login successful");
}
```

---

## 166. How do you implement refresh token mechanism?

**Answer:** Use short-lived access tokens with long-lived refresh tokens. When access token expires, use refresh token to get new access token without re-authentication.

**Example:**
```java
@Service
public class TokenService {
    
    public TokenResponse generateTokens(String username) {
        String accessToken = generateAccessToken(username); // 15 min
        String refreshToken = generateRefreshToken(username); // 7 days
        
        // Store refresh token in DB
        refreshTokenRepository.save(new RefreshToken(username, refreshToken));
        
        return new TokenResponse(accessToken, refreshToken);
    }
    
    public String refreshAccessToken(String refreshToken) {
        if (!isValidRefreshToken(refreshToken)) {
            throw new InvalidTokenException();
        }
        String username = getUsernameFromToken(refreshToken);
        return generateAccessToken(username);
    }
    
    private String generateAccessToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 900000)) // 15 min
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}
```

---

## 167. How do you store passwords securely (BCrypt)?

**Answer:** Never store plain text passwords. Use BCrypt hashing algorithm which includes salt and is computationally expensive to prevent brute-force attacks.

**Example:**
```java
@Service
public class UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Registration - Hash password
    public User registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        return userRepository.save(user);
    }
    
    // Login - Verify password
    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}

// Configuration
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Strength factor
    }
}
```

---

## 168. What is the principle of least privilege?

**Answer:** Grant users only the minimum permissions necessary to perform their job. Reduces security risk if account is compromised.

**Example:**
```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        );
        return http.build();
    }
}

// Method level
@Service
public class DocumentService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
    
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public Document viewDocument(Long id, Long userId) {
        return documentRepository.findById(id).orElse(null);
    }
}
```

---

## 169. How do you handle secrets management?

**Answer:** Never hardcode secrets in code. Use environment variables, secret management services (AWS Secrets Manager, HashiCorp Vault), or encrypted configuration files.

**Example:**
```java
// BAD - Hardcoded secrets
String apiKey = "sk_live_12345abcde";

// GOOD - Environment variables
@Value("${api.key}")
private String apiKey;

// application.yml
// api:
//   key: ${API_KEY}

// BETTER - AWS Secrets Manager
@Configuration
public class SecretsConfig {
    
    @Bean
    public AWSSecretsManager secretsManager() {
        return AWSSecretsManagerClientBuilder.standard()
            .withRegion("us-east-1")
            .build();
    }
    
    public String getSecret(String secretName) {
        GetSecretValueRequest request = new GetSecretValueRequest()
            .withSecretId(secretName);
        GetSecretValueResult result = secretsManager().getSecretValue(request);
        return result.getSecretString();
    }
}

// BEST - Spring Cloud Config with encryption
// bootstrap.yml
// spring:
//   cloud:
//     config:
//       uri: http://config-server:8888
// 
// Config Server stores: {cipher}AQA3eHj8...encrypted_value
```

# ✅ 14) Design Patterns (Commonly Used)

## 170. Explain Singleton pattern and thread-safe implementation.

**Definition:** Singleton ensures only one instance of a class exists throughout the application lifecycle.

**Example:**
```java
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    
    private DatabaseConnection() {}
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}
```

---

## 171. What is double-checked locking problem?

**Definition:** Double-checked locking is a pattern to reduce synchronization overhead by checking the instance twice - once without locking and once with locking.

**Example:**
```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {  // First check (no lock)
            synchronized (Singleton.class) {
                if (instance == null) {  // Second check (with lock)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

---

## 172. Explain Factory pattern with example.

**Definition:** Factory pattern creates objects without exposing creation logic, returning objects through a common interface.

**Example:**
```java
interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    public void drive() { System.out.println("Driving car"); }
}

class Bike implements Vehicle {
    public void drive() { System.out.println("Riding bike"); }
}

class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equals("car")) return new Car();
        if (type.equals("bike")) return new Bike();
        return null;
    }
}
```

---

## 173. Explain Builder pattern and its use cases.

**Definition:** Builder pattern constructs complex objects step by step, separating construction from representation.

**Example:**
```java
public class User {
    private String name;
    private int age;
    private String email;
    
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }
    
    public static class Builder {
        private String name;
        private int age;
        private String email;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
}

// Usage: User user = new User.Builder().name("John").age(30).build();
```

---

## 174. Explain Strategy pattern with example.

**Definition:** Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

**Example:**
```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCard implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPal implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

---

## 175. Explain Observer pattern.

**Definition:** Observer pattern defines one-to-many dependency where when one object changes state, all dependents are notified automatically.

**Example:**
```java
interface Observer {
    void update(String message);
}

class User implements Observer {
    private String name;
    
    public User(String name) { this.name = name; }
    
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}
```

---

## 176. Explain Decorator pattern.

**Definition:** Decorator pattern adds new functionality to an object dynamically without altering its structure.

**Example:**
```java
interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double getCost() { return 2.0; }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

// Usage: Coffee coffee = new MilkDecorator(new SimpleCoffee());
```

---

## 177. Explain Adapter pattern.

**Definition:** Adapter pattern converts the interface of a class into another interface that clients expect, allowing incompatible interfaces to work together.

**Example:**
```java
interface MediaPlayer {
    void play(String filename);
}

class MP3Player {
    public void playMP3(String filename) {
        System.out.println("Playing MP3: " + filename);
    }
}

class MediaAdapter implements MediaPlayer {
    private MP3Player mp3Player;
    
    public MediaAdapter() {
        this.mp3Player = new MP3Player();
    }
    
    public void play(String filename) {
        mp3Player.playMP3(filename);
    }
}
```

---

## 178. Explain Facade pattern.

**Definition:** Facade pattern provides a simplified interface to a complex subsystem, hiding its complexity.

**Example:**
```java
class CPU {
    public void start() { System.out.println("CPU started"); }
}

class Memory {
    public void load() { System.out.println("Memory loaded"); }
}

class HardDrive {
    public void read() { System.out.println("HardDrive reading"); }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void startComputer() {
        cpu.start();
        memory.load();
        hardDrive.read();
    }
}
```

---

## 179. Explain Template Method pattern.

**Definition:** Template Method pattern defines the skeleton of an algorithm in a base class, letting subclasses override specific steps without changing the algorithm's structure.

**Example:**
```java
abstract class DataProcessor {
    public final void process() {
        readData();
        processData();
        saveData();
    }
    
    abstract void readData();
    abstract void processData();
    
    void saveData() {
        System.out.println("Saving data");
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

---

## 180. Explain Proxy pattern.

**Definition:** Proxy pattern provides a surrogate or placeholder to control access to another object.

**Example:**
```java
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
```

---

## 181. Strategy vs State pattern.

**Definition:** Strategy pattern selects algorithm at runtime based on client choice. State pattern changes object behavior when its internal state changes.

**Example:**
```java
// Strategy - Client chooses behavior
interface SortStrategy {
    void sort(int[] array);
}

class QuickSort implements SortStrategy {
    public void sort(int[] array) { /* quick sort */ }
}

// State - Object changes behavior based on state
interface State {
    void handle();
}

class OnState implements State {
    public void handle() { System.out.println("Turning OFF"); }
}

class OffState implements State {
    public void handle() { System.out.println("Turning ON"); }
}

class Switch {
    private State state;
    
    public void setState(State state) { this.state = state; }
    public void press() { state.handle(); }
}
```

---

## 182. When do you use Dependency Injection pattern?

**Definition:** Dependency Injection provides dependencies to a class from external sources rather than creating them internally, promoting loose coupling and testability.

**Example:**
```java
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class UserService {
    private Database database;
    
    // Constructor Injection
    public UserService(Database database) {
        this.database = database;
    }
    
    public void saveUser(String user) {
        database.save(user);
    }
}

// Usage: UserService service = new UserService(new MySQLDatabase());
```

# ✅ 15) Performance & Optimization and Troubleshooting

## 183. How do you identify performance bottlenecks?

**Definition:** Performance bottlenecks are identified by monitoring application metrics, analyzing logs, profiling code execution, and measuring response times to find slow operations.

**Example:**
```java
@RestController
public class UserController {
    
    @GetMapping("/users")
    public List<User> getUsers() {
        long startTime = System.currentTimeMillis();
        
        List<User> users = userService.findAll();
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("Query took {} ms", duration);
        
        return users;
    }
}

// Use Spring Boot Actuator metrics
@Component
public class PerformanceMonitor {
    private final MeterRegistry registry;
    
    public void trackOperation(String operation, Runnable task) {
        Timer.Sample sample = Timer.start(registry);
        task.run();
        sample.stop(Timer.builder(operation).register(registry));
    }
}
```

---

## 184. What tools do you use for profiling (JProfiler, VisualVM)?

**Definition:** Profiling tools analyze application performance by monitoring CPU usage, memory allocation, thread activity, and method execution times.

**Example:**
```java
// Enable JMX for VisualVM connection
// Add JVM arguments: -Dcom.sun.management.jmxremote

// Using Java Flight Recorder
public class ProfilerExample {
    public static void main(String[] args) {
        // Start JFR: java -XX:StartFlightRecording=duration=60s,filename=recording.jfr
        
        performHeavyOperation();
    }
    
    private static void performHeavyOperation() {
        for (int i = 0; i < 1000000; i++) {
            String s = new String("test" + i);
        }
    }
}

// VisualVM: Connect to running JVM and analyze CPU/Memory
// JProfiler: Attach to process and profile method calls
```

---

## 185. How do you optimize database queries?

**Definition:** Database query optimization involves using indexes, avoiding N+1 queries, using proper joins, limiting result sets, and analyzing query execution plans.

**Example:**
```java
// Bad - N+1 Query Problem
@Entity
public class Order {
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> items;
}

List<Order> orders = orderRepository.findAll();
for (Order order : orders) {
    order.getItems().size(); // N+1 queries!
}

// Good - Use JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.items")
List<Order> findAllWithItems();

// Use Pagination
Pageable pageable = PageRequest.of(0, 20);
Page<Order> orders = orderRepository.findAll(pageable);

// Use Indexes
@Entity
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class User {
    @Column(unique = true)
    private String email;
}
```

---

## 186. What is connection pooling (HikariCP configuration)?

**Definition:** Connection pooling maintains a pool of reusable database connections to avoid the overhead of creating new connections for each request.

**Example:**
```yaml
# application.yml
spring:
  datasource:
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
```

```java
@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("user");
        config.setPassword("pass");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        return new HikariDataSource(config);
    }
}
```

---

## 187. How do you size thread pools?

**Definition:** Thread pool sizing depends on workload type: CPU-bound tasks use cores+1, I/O-bound tasks use higher counts based on blocking time ratio.

**Example:**
```java
@Configuration
public class ThreadPoolConfig {
    
    // CPU-bound tasks
    @Bean
    public Executor cpuBoundExecutor() {
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores);
        executor.setMaxPoolSize(cores + 1);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("cpu-");
        executor.initialize();
        return executor;
    }
    
    // I/O-bound tasks
    @Bean
    public Executor ioBoundExecutor() {
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores * 2);
        executor.setMaxPoolSize(cores * 4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("io-");
        executor.initialize();
        return executor;
    }
}
```

---

## 188. Horizontal vs vertical scaling.

**Definition:** Vertical scaling adds more resources (CPU, RAM) to existing servers. Horizontal scaling adds more servers to distribute load.

**Example:**
```java
// Vertical Scaling - Single instance with more resources
// JVM args: -Xmx8g -Xms8g (increase heap)

// Horizontal Scaling - Multiple instances with load balancer
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// application.yml for multiple instances
server:
  port: ${PORT:8080}  # Instance 1: 8080, Instance 2: 8081

// Nginx Load Balancer config
/*
upstream backend {
    server localhost:8080;
    server localhost:8081;
    server localhost:8082;
}
*/

// Stateless design for horizontal scaling
@RestController
public class StatelessController {
    // No instance variables storing state
    // Use Redis/Database for shared state
}
```

---

## 189. How do you optimize JVM for production?

**Definition:** JVM optimization involves tuning heap size, garbage collector selection, and monitoring GC behavior for optimal performance.

**Example:**
```bash
# Production JVM flags
java -Xms4g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/logs/heapdump.hprof \
     -XX:+PrintGCDetails \
     -XX:+PrintGCDateStamps \
     -Xloggc:/logs/gc.log \
     -jar application.jar
```

```java
// Monitor GC in code
@Component
public class GCMonitor {
    
    @PostConstruct
    public void monitorGC() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            log.info("GC: {}, Count: {}, Time: {}ms", 
                gcBean.getName(), 
                gcBean.getCollectionCount(), 
                gcBean.getCollectionTime());
        }
    }
}
```

---

## 190. How do you troubleshoot memory leaks?

**Definition:** Memory leaks occur when objects are unintentionally retained in memory. Troubleshoot using heap dumps, analyzing object retention paths, and monitoring memory growth.

**Example:**
```java
// Common memory leak - Static collection
public class MemoryLeakExample {
    private static List<User> cache = new ArrayList<>(); // Never cleared!
    
    public void addUser(User user) {
        cache.add(user); // Memory leak
    }
}

// Fix - Use WeakHashMap or clear cache
public class FixedExample {
    private Map<String, User> cache = new WeakHashMap<>();
    
    @Scheduled(fixedRate = 3600000)
    public void clearCache() {
        cache.clear();
    }
}

// Analyze heap dump
// 1. Generate: jmap -dump:live,format=b,file=heap.bin <pid>
// 2. Analyze with Eclipse MAT or VisualVM
// 3. Look for objects with high retained heap
```

---

## 191. How do you analyze heap dumps and thread dumps?

**Definition:** Heap dumps show memory state and object allocation. Thread dumps show thread states and stack traces for deadlock detection.

**Example:**
```bash
# Generate heap dump
jmap -dump:live,format=b,file=heap.hprof <pid>

# Generate thread dump
jstack <pid> > thread_dump.txt

# Or use kill signal
kill -3 <pid>
```

```java
// Programmatic thread dump
public class ThreadDumpGenerator {
    
    public static String generateThreadDump() {
        StringBuilder dump = new StringBuilder();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        
        for (ThreadInfo threadInfo : threadMXBean.dumpAllThreads(true, true)) {
            dump.append(threadInfo.toString());
        }
        
        return dump.toString();
    }
}

// Analyze for deadlocks
ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
if (deadlockedThreads != null) {
    log.error("Deadlock detected!");
}
```

---

## 192. How do you reduce API response time?

**Definition:** Reduce API response time by optimizing queries, using caching, implementing async processing, reducing payload size, and using CDN for static content.

**Example:**
```java
// 1. Use Caching
@Cacheable("users")
public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
}

// 2. Async Processing
@Async
public CompletableFuture<List<Order>> getOrders() {
    return CompletableFuture.completedFuture(orderRepository.findAll());
}

// 3. Pagination
@GetMapping("/users")
public Page<User> getUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
}

// 4. Database Optimization
@Query("SELECT new com.example.UserDTO(u.id, u.name) FROM User u")
List<UserDTO> findAllProjected(); // Fetch only needed fields

// 5. Compression
@Configuration
public class CompressionConfig {
    @Bean
    public FilterRegistrationBean<GzipFilter> gzipFilter() {
        FilterRegistrationBean<GzipFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new GzipFilter());
        return registration;
    }
}

// 6. Connection pooling (already configured in HikariCP)
```

---

## 193. What is reactive programming and when to use it?

**Definition:** Reactive programming is a non-blocking, asynchronous programming paradigm that handles streams of data and propagates changes efficiently.

**Example:**
```java
// Traditional blocking approach
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id); // Blocks thread
}

// Reactive approach with WebFlux
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable Long id) {
    return userService.findById(id); // Non-blocking
}

// Reactive repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByAge(int age);
}

// Reactive service
@Service
public class UserService {
    
    public Mono<User> getUser(Long id) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException()));
    }
    
    public Flux<User> getAllUsers() {
        return userRepository.findAll()
            .filter(user -> user.isActive())
            .map(this::enrichUser);
    }
}

// Use reactive when:
// - High concurrency requirements
// - I/O-bound operations
// - Streaming data
// - Backpressure handling needed
```

# ✅ 16) Unit Testing (JUnit, Mockito)

## 194. What is unit test vs integration test?

**Definition:** Unit tests verify individual components in isolation with mocked dependencies. Integration tests verify multiple components working together with real dependencies.

**Example:**
```java
// Unit Test - Tests service in isolation
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldFindUserById() {
        User user = new User(1L, "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        User result = userService.findById(1L);
        
        assertEquals("John", result.getName());
    }
}

// Integration Test - Tests with real database
@SpringBootTest
@AutoConfigureTestDatabase
class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldSaveAndRetrieveUser() {
        User user = new User(null, "John");
        User saved = userService.save(user);
        
        User found = userService.findById(saved.getId());
        
        assertEquals("John", found.getName());
    }
}
```

---

## 195. Explain AAA pattern (Arrange, Act, Assert).

**Definition:** AAA pattern structures tests into three sections: Arrange (setup), Act (execute), Assert (verify). It improves test readability and maintainability.

**Example:**
```java
@Test
void shouldCalculateTotalPrice() {
    // Arrange
    Order order = new Order();
    order.addItem(new Item("Book", 10.0));
    order.addItem(new Item("Pen", 2.0));
    
    // Act
    double total = order.calculateTotal();
    
    // Assert
    assertEquals(12.0, total);
}

@Test
void shouldApplyDiscount() {
    // Arrange
    Cart cart = new Cart();
    cart.addItem(100.0);
    DiscountService service = new DiscountService();
    
    // Act
    double discounted = service.applyDiscount(cart, 10);
    
    // Assert
    assertEquals(90.0, discounted);
}
```

---

## 196. What is JUnit 5 architecture?

**Definition:** JUnit 5 consists of three modules: JUnit Platform (test execution), JUnit Jupiter (new API), and JUnit Vintage (backward compatibility with JUnit 3/4).

**Example:**
```java
// JUnit Jupiter API
import org.junit.jupiter.api.*;

@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeAll
    static void setupAll() {
        System.out.println("Before all tests");
    }
    
    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Should add two numbers")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }
    
    @AfterEach
    void tearDown() {
        calculator = null;
    }
    
    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests");
    }
}
```

---

## 197. Explain `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`.

**Definition:** These annotations control test lifecycle. @BeforeEach/@AfterEach run before/after each test. @BeforeAll/@AfterAll run once before/after all tests (must be static).

**Example:**
```java
class LifecycleTest {
    
    private Connection connection;
    private static Database database;
    
    @BeforeAll
    static void initDatabase() {
        database = new Database();
        database.start();
    }
    
    @BeforeEach
    void openConnection() {
        connection = database.getConnection();
    }
    
    @Test
    void testQuery1() {
        // connection is available
        assertNotNull(connection);
    }
    
    @Test
    void testQuery2() {
        // fresh connection for each test
        assertNotNull(connection);
    }
    
    @AfterEach
    void closeConnection() {
        connection.close();
    }
    
    @AfterAll
    static void shutdownDatabase() {
        database.stop();
    }
}
```

---

## 198. How do you write parameterized tests?

**Definition:** Parameterized tests run the same test logic with different input values, reducing code duplication and improving test coverage.

**Example:**
```java
class ParameterizedTests {
    
    @ParameterizedTest
    @ValueSource(strings = {"hello", "world", "test"})
    void testStringLength(String input) {
        assertTrue(input.length() > 0);
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "5, 5, 10",
        "10, 20, 30"
    })
    void testAddition(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
    
    @ParameterizedTest
    @MethodSource("provideUsers")
    void testUserValidation(User user) {
        assertTrue(user.isValid());
    }
    
    static Stream<User> provideUsers() {
        return Stream.of(
            new User("John", 25),
            new User("Jane", 30)
        );
    }
}
```

---

## 199. What is Mockito? Explain `@Mock`, `@InjectMocks`, `@Spy`.

**Definition:** Mockito is a mocking framework for unit tests. @Mock creates mock objects, @InjectMocks injects mocks into the tested object, @Spy creates partial mocks.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;  // Fully mocked
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;  // Mocks injected here
    
    @Spy
    private ValidationService validationService;  // Partial mock
    
    @Test
    void testCreateUser() {
        User user = new User("John");
        when(userRepository.save(any())).thenReturn(user);
        
        User created = userService.createUser(user);
        
        assertEquals("John", created.getName());
        verify(emailService).sendWelcomeEmail(user);
    }
}
```

---

## 200. Mock vs Spy - difference?

**Definition:** Mock creates a fake object with no real behavior. Spy wraps a real object, allowing real methods to be called unless stubbed.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class MockVsSpyTest {
    
    @Test
    void testMock() {
        List<String> mockList = mock(ArrayList.class);
        
        mockList.add("item");
        
        // Mock doesn't call real method
        assertEquals(0, mockList.size());  // Returns default value
        
        when(mockList.size()).thenReturn(5);
        assertEquals(5, mockList.size());
    }
    
    @Test
    void testSpy() {
        List<String> spyList = spy(new ArrayList<>());
        
        spyList.add("item");
        
        // Spy calls real method
        assertEquals(1, spyList.size());  // Real behavior
        
        when(spyList.size()).thenReturn(100);
        assertEquals(100, spyList.size());  // Stubbed behavior
    }
}
```

---

## 201. How do you stub method behavior (`when().thenReturn()`)?

**Definition:** Stubbing defines mock behavior by specifying what a method should return when called with specific arguments.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class StubbingTest {
    
    @Mock
    private UserRepository repository;
    
    @Test
    void testStubbing() {
        User user = new User(1L, "John");
        
        // Basic stubbing
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        
        // Multiple return values
        when(repository.count()).thenReturn(5L, 10L, 15L);
        
        // Throw exception
        when(repository.findById(999L))
            .thenThrow(new NotFoundException());
        
        // Argument matchers
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        
        // Verify
        assertEquals("John", repository.findById(1L).get().getName());
        assertEquals(5L, repository.count());
        assertEquals(10L, repository.count());
    }
}
```

---

## 202. How do you verify method invocations?

**Definition:** Verification confirms that specific methods were called with expected arguments and frequency during test execution.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class VerificationTest {
    
    @Mock
    private EmailService emailService;
    
    @Mock
    private UserRepository repository;
    
    @Test
    void testVerification() {
        User user = new User("John");
        
        emailService.send(user, "Welcome");
        repository.save(user);
        repository.save(user);
        
        // Verify method called once
        verify(emailService).send(user, "Welcome");
        
        // Verify method called twice
        verify(repository, times(2)).save(user);
        
        // Verify never called
        verify(emailService, never()).sendError(any());
        
        // Verify with argument matchers
        verify(emailService).send(any(User.class), eq("Welcome"));
        
        // Verify at least/at most
        verify(repository, atLeast(1)).save(user);
        verify(repository, atMost(3)).save(user);
    }
}
```

---

## 203. How do you test exceptions in JUnit 5?

**Definition:** JUnit 5 provides assertThrows to verify that code throws expected exceptions with specific messages.

**Example:**
```java
class ExceptionTest {
    
    @Test
    void shouldThrowException() {
        UserService service = new UserService();
        
        // Assert exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            service.createUser(null);
        });
    }
    
    @Test
    void shouldThrowExceptionWithMessage() {
        UserService service = new UserService();
        
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.findById(999L);
        });
        
        assertEquals("User not found", exception.getMessage());
    }
    
    @Test
    void shouldNotThrowException() {
        UserService service = new UserService();
        
        assertDoesNotThrow(() -> {
            service.findById(1L);
        });
    }
}
```

---

## 204. What is `@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest`?

**Definition:** These are Spring Boot test slices. @SpringBootTest loads full context, @WebMvcTest tests web layer only, @DataJpaTest tests JPA repositories only.

**Example:**
```java
// Full application context
@SpringBootTest
class FullIntegrationTest {
    @Autowired
    private UserService userService;
    
    @Test
    void testFullStack() {
        User user = userService.createUser(new User("John"));
        assertNotNull(user.getId());
    }
}

// Web layer only
@WebMvcTest(UserController.class)
class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void testGetUser() throws Exception {
        when(userService.findById(1L)).thenReturn(new User("John"));
        
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John"));
    }
}

// JPA layer only
@DataJpaTest
class RepositoryTest {
    @Autowired
    private UserRepository repository;
    
    @Test
    void testSaveUser() {
        User user = repository.save(new User("John"));
        assertNotNull(user.getId());
    }
}
```

---

## 205. How do you test REST controllers using MockMvc?

**Definition:** MockMvc simulates HTTP requests to test REST controllers without starting a real server, verifying responses and status codes.

**Example:**
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void testGetUser() throws Exception {
        User user = new User(1L, "John");
        when(userService.findById(1L)).thenReturn(user);
        
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("John"));
    }
    
    @Test
    void testCreateUser() throws Exception {
        User user = new User(null, "John");
        User saved = new User(1L, "John");
        when(userService.save(any())).thenReturn(saved);
        
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }
    
    @Test
    void testUpdateUser() throws Exception {
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane\"}"))
            .andExpect(status().isOk());
        
        verify(userService).update(eq(1L), any());
    }
    
    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
            .andExpect(status().isNoContent());
        
        verify(userService).delete(1L);
    }
    
    @Test
    void testNotFound() throws Exception {
        when(userService.findById(999L))
            .thenThrow(new NotFoundException());
        
        mockMvc.perform(get("/users/999"))
            .andExpect(status().isNotFound());
    }
}
```

# ✅ 17) Build Tools (Maven/Gradle)

## 206. What is Maven and POM?

**Definition:** Maven is a build automation and dependency management tool. POM (Project Object Model) is an XML file that contains project configuration, dependencies, plugins, and build settings.

**Example:**
```xml
<!-- pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <java.version>17</java.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.1.0</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 207. Explain Maven lifecycle phases.

**Definition:** Maven has three built-in lifecycles: clean, default, and site. The default lifecycle has phases like validate, compile, test, package, install, and deploy that execute in order.

**Example:**
```bash
# Clean lifecycle
mvn clean                    # Removes target directory

# Default lifecycle phases (executed in order)
mvn validate                 # Validates project structure
mvn compile                  # Compiles source code
mvn test                     # Runs unit tests
mvn package                  # Creates JAR/WAR
mvn verify                   # Runs integration tests
mvn install                  # Installs to local repository
mvn deploy                   # Deploys to remote repository

# Common commands
mvn clean install            # Clean + compile + test + package + install
mvn clean package -DskipTests  # Package without running tests
```

```xml
<!-- Custom plugin execution in specific phase -->
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0</version>
            <executions>
                <execution>
                    <phase>test</phase>
                    <goals>
                        <goal>test</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

---

## 208. What are Maven dependency scopes?

**Definition:** Dependency scopes control when dependencies are available in the classpath during compile, test, and runtime phases.

**Example:**
```xml
<dependencies>
    <!-- compile (default) - Available in all classpaths -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>6.0.0</version>
        <scope>compile</scope>
    </dependency>
    
    <!-- provided - Available at compile/test, not packaged -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- runtime - Not needed for compilation, only runtime -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
        <scope>runtime</scope>
    </dependency>
    
    <!-- test - Only available during test phase -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    
    <!-- system - Similar to provided, must specify path -->
    <dependency>
        <groupId>com.custom</groupId>
        <artifactId>custom-lib</artifactId>
        <version>1.0</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/lib/custom.jar</systemPath>
    </dependency>
    
    <!-- import - Only for dependency management in POM -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>3.1.0</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>
</dependencies>
```

---

## 209. What is transitive dependency?

**Definition:** Transitive dependencies are dependencies of your direct dependencies. Maven automatically includes them in your project's classpath.

**Example:**
```xml
<!-- Your pom.xml -->
<dependencies>
    <!-- Direct dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.1.0</version>
    </dependency>
    <!-- This brings transitive dependencies:
         - spring-web
         - spring-webmvc
         - jackson-databind
         - tomcat-embed-core
         etc. -->
</dependencies>

<!-- View dependency tree -->
<!-- Command: mvn dependency:tree -->

<!-- Exclude transitive dependency -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.1.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

---

## 210. How do you resolve dependency conflicts?

**Definition:** Dependency conflicts occur when different versions of the same library are required. Maven uses nearest definition and first declaration rules to resolve conflicts.

**Example:**
```bash
# Analyze dependency conflicts
mvn dependency:tree
mvn dependency:analyze

# Find specific dependency
mvn dependency:tree -Dincludes=commons-logging
```

```xml
<!-- Method 1: Explicit version declaration (nearest wins) -->
<dependencies>
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.2</version>  <!-- This version will be used -->
    </dependency>
</dependencies>

<!-- Method 2: Dependency Management -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- Method 3: Exclude conflicting transitive dependency -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.0</version>
    <exclusions>
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!-- Method 4: Use Maven Enforcer Plugin -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-enforcer-plugin</artifactId>
    <version>3.0.0</version>
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

---

## 211. What is Maven BOM (Bill of Materials)?

**Definition:** BOM is a special POM that provides a centralized dependency management for a set of related artifacts, ensuring version consistency across modules.

**Example:**
```xml
<!-- Parent POM with BOM -->
<project>
    <groupId>com.example</groupId>
    <artifactId>parent-bom</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>
    
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
            
            <!-- Define versions for your modules -->
            <dependency>
                <groupId>com.example</groupId>
                <artifactId>common-utils</artifactId>
                <version>1.0.0</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

<!-- Child module using BOM -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-bom</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>my-service</artifactId>
    
    <dependencies>
        <!-- No version needed - inherited from BOM -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>common-utils</artifactId>
        </dependency>
    </dependencies>
</project>
```

---

## 212. How do you create multi-module Maven project?

**Definition:** Multi-module projects organize related modules under a parent POM, enabling shared configuration and coordinated builds across modules.

**Example:**
```xml
<!-- Parent POM (root/pom.xml) -->
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>parent-project</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>
    
    <modules>
        <module>common</module>
        <module>service</module>
        <module>web</module>
    </modules>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
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

<!-- Common Module (common/pom.xml) -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>common</artifactId>
    <packaging>jar</packaging>
</project>

<!-- Service Module (service/pom.xml) -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>service</artifactId>
    <packaging>jar</packaging>
    
    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>common</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
</project>

<!-- Web Module (web/pom.xml) -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>web</artifactId>
    <packaging>jar</packaging>
    
    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>service</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
</project>

<!-- Build all modules: mvn clean install -->
```

---

## 213. Maven vs Gradle - when to use which?

**Definition:** Maven uses XML configuration with convention over configuration. Gradle uses Groovy/Kotlin DSL with more flexibility and better performance. Choose based on project needs and team preference.

**Example:**
```xml
<!-- Maven (pom.xml) -->
<project>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.1.0</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

```groovy
// Gradle (build.gradle)
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.1.0'
}

group = 'com.example'
version = '1.0.0'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

```kotlin
// Gradle Kotlin DSL (build.gradle.kts)
plugins {
    java
    id("org.springframework.boot") version "3.1.0"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
```

**When to use Maven:**
- Standard enterprise projects
- Team familiar with XML
- Need strict conventions
- Extensive plugin ecosystem
- Corporate environments with Maven standards

**When to use Gradle:**
- Need faster builds (incremental compilation)
- Complex build logic required
- Multi-module projects with many dependencies
- Android development (default)
- Prefer concise DSL over XML
- Need build caching and parallel execution

# ✅ 18) Git & CI/CD Basics

## 214. Explain Git branching strategies (GitFlow, trunk-based).

**GitFlow**: Uses multiple long-lived branches (main, develop) with feature, release, and hotfix branches for structured releases.

**Trunk-based**: Developers commit directly to main/trunk frequently with short-lived feature branches (< 1 day).

**Example:**
```bash
# GitFlow
git checkout -b feature/login develop
git checkout -b release/1.0 develop

# Trunk-based
git checkout -b feature/login main
# Merge within hours/1 day
```

---

## 215. `git merge` vs `git rebase` - when to use which?

**git merge**: Combines branches preserving history with a merge commit.

**git rebase**: Rewrites history by moving commits on top of another branch, creating linear history.

**Example:**
```bash
# Merge - preserves history
git checkout main
git merge feature/login

# Rebase - linear history
git checkout feature/login
git rebase main
```

**Use merge** for public branches, **use rebase** for cleaning local history before pushing.

---

## 216. How do you resolve merge conflicts?

Conflicts occur when same lines are modified in different branches. Manually edit files, choose changes, then commit.

**Example:**
```bash
git merge feature/login
# Conflict occurs

# Edit conflicted file
String name = "John";

# Choose one or combine
String name = "John";

git add .
git commit -m "Resolved conflict"
```

---

## 217. What is CI/CD pipeline?

**CI (Continuous Integration)**: Automatically build and test code on every commit.

**CD (Continuous Deployment)**: Automatically deploy tested code to production.

**Example:**
```yaml
# .gitlab-ci.yml
stages:
  - build
  - test
  - deploy

build:
  stage: build
  script:
    - mvn clean package

test:
  stage: test
  script:
    - mvn test

deploy:
  stage: deploy
  script:
    - kubectl apply -f deployment.yml
```

---

## 218. Explain Jenkins pipeline stages.

Stages organize pipeline into logical steps: checkout, build, test, deploy.

**Example:**
```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/repo.git'
            }
        }
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
        stage('Deploy') {
            steps {
                sh './deploy.sh'
            }
        }
    }
}
```

---

## 219. What is declarative vs scripted pipeline?

**Declarative**: Structured, simpler syntax with predefined sections (pipeline, stages, steps).

**Scripted**: Groovy-based, more flexible but complex.

**Example:**
```groovy
// Declarative
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}

// Scripted
node {
    stage('Build') {
        sh 'mvn clean package'
    }
}
```

---

## 220. What is blue-green deployment?

Two identical environments (blue=current, green=new). Deploy to green, test, then switch traffic. Instant rollback if issues.

**Example:**
```yaml
# Blue environment (current)
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    version: blue

# Deploy to green
kubectl apply -f green-deployment.yml

# Switch traffic
kubectl patch service app-service -p '{"spec":{"selector":{"version":"green"}}}'
```

---

## 221. What is canary deployment?

Gradually roll out new version to small subset of users (5-10%), monitor, then increase traffic if stable.

**Example:**
```yaml
# 90% traffic to v1, 10% to v2
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    app: myapp
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-v1
spec:
  replicas: 9
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-v2
spec:
  replicas: 1
```

---

## 222. How do you handle database migrations in CI/CD?

Use migration tools (Flyway, Liquibase) to version and apply schema changes automatically during deployment.

**Example:**
```java
// Flyway migration - V1__create_user_table.sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

// application.properties
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

```yaml
# CI/CD pipeline
deploy:
  script:
    - mvn flyway:migrate  # Run migrations
    - kubectl apply -f deployment.yml
```

**Flyway** automatically tracks applied migrations in `flyway_schema_history` table and applies only new ones.

# ✅ 19) Monitoring, Logging, Observability & Debugging

## 223. What are the three pillars of observability?

**Logs**: Detailed event records of what happened.

**Metrics**: Numerical measurements over time (CPU, memory, request count).

**Traces**: Request flow across distributed services.

**Example:**
```java
// Logs
log.info("User {} logged in", userId);

// Metrics
meterRegistry.counter("user.login.count").increment();

// Traces
@NewSpan
public void processOrder(String orderId) {
    // Distributed trace created
}
```

---

## 224. How do you implement logging in Spring Boot?

Spring Boot uses SLF4J with Logback by default. Use @Slf4j annotation or LoggerFactory.

**Example:**
```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    public void createUser(String name) {
        log.info("Creating user: {}", name);
        log.debug("User details: {}", name);
        log.error("Failed to create user", exception);
    }
}
```

```properties
# application.properties
logging.level.com.example=DEBUG
logging.file.name=app.log
```

---

## 225. Explain logging levels (DEBUG, INFO, WARN, ERROR).

**DEBUG**: Detailed diagnostic info for development.

**INFO**: General informational messages about app flow.

**WARN**: Potentially harmful situations that aren't errors.

**ERROR**: Error events that might still allow app to continue.

**Example:**
```java
@Slf4j
public class OrderService {
    public void processOrder(Order order) {
        log.debug("Processing order: {}", order.getId());
        log.info("Order {} placed successfully", order.getId());
        log.warn("Order {} has low stock", order.getId());
        log.error("Failed to process order {}", order.getId(), exception);
    }
}
```

---

## 226. What is structured logging?

Logging in structured format (JSON) instead of plain text for easier parsing and analysis.

**Example:**
```java
// Plain text log
log.info("User john logged in from IP 192.168.1.1");

// Structured log (JSON)
log.info("User login", 
    kv("userId", "john"),
    kv("ip", "192.168.1.1"),
    kv("timestamp", Instant.now())
);
```

```json
// Output
{
  "timestamp": "2024-01-15T10:30:00Z",
  "level": "INFO",
  "message": "User login",
  "userId": "john",
  "ip": "192.168.1.1"
}
```

---

## 227. What is correlation ID and how to implement it?

Unique identifier passed through all services in a request chain to trace the entire flow.

**Example:**
```java
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
```

```java
// Logs automatically include correlationId
log.info("Processing request"); // [correlationId=abc-123] Processing request
```

---

## 228. What is distributed tracing (Zipkin, Jaeger)?

Tracks requests as they flow through multiple microservices, showing latency and dependencies.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

```properties
# application.properties
spring.zipkin.base-url=http://localhost:9411
spring.sleuth.sampler.probability=1.0
```

```java
@RestController
public class OrderController {
    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable String id) {
        // Automatically traced across services
        return orderService.findById(id);
    }
}
```

**Zipkin UI shows**: Service A → Service B → Database (with timing for each hop)

---

## 229. What tools do you use for monitoring (Prometheus, Grafana)?

**Prometheus**: Collects and stores metrics as time-series data.

**Grafana**: Visualizes metrics with dashboards and alerts.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=prometheus
management.metrics.export.prometheus.enabled=true
```

```java
@RestController
public class MetricsController {
    private final Counter orderCounter;
    
    public MetricsController(MeterRegistry registry) {
        this.orderCounter = registry.counter("orders.created");
    }
    
    @PostMapping("/order")
    public void createOrder() {
        orderCounter.increment();
    }
}
```

**Prometheus scrapes**: `http://localhost:8080/actuator/prometheus`

---

## 230. What metrics should you monitor in production?

**Application**: Request rate, error rate, response time, active users.

**System**: CPU, memory, disk, network usage.

**Business**: Orders/sec, revenue, conversion rate.

**Example:**
```java
@Component
public class ApplicationMetrics {
    private final MeterRegistry registry;
    
    public ApplicationMetrics(MeterRegistry registry) {
        this.registry = registry;
        // JVM metrics
        registry.gauge("jvm.memory.used", Runtime.getRuntime().totalMemory());
        
        // Business metrics
        registry.counter("orders.total");
        registry.timer("api.response.time");
        
        // Error rate
        registry.counter("errors.count");
    }
}
```

---

## 231. How do you analyze thread dumps and heap dumps?

**Thread dump**: Shows all threads and their states to identify deadlocks or blocked threads.

**Heap dump**: Shows memory usage to identify memory leaks.

**Example:**
```bash
# Generate thread dump
jstack <pid> > thread_dump.txt

# Generate heap dump
jmap -dump:live,format=b,file=heap.bin <pid>

# Analyze with tools
# Thread dump: Look for BLOCKED, WAITING states
# Heap dump: Use Eclipse MAT, VisualVM
```

```java
// Thread dump analysis
"Thread-1" #12 prio=5 os_prio=0 waiting on condition
   java.lang.Thread.State: WAITING (parking)
   
"Thread-2" #13 prio=5 os_prio=0 waiting for monitor entry
   java.lang.Thread.State: BLOCKED (on object monitor)
```

---

## 232. What is ELK stack?

**E**lasticsearch: Stores and indexes logs.

**L**ogstash: Collects, processes, and forwards logs.

**K**ibana: Visualizes logs with dashboards.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
</dependency>
```

```xml
<!-- logback-spring.xml -->
<appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <destination>localhost:5000</destination>
    <encoder class="net.logstash.logback.encoder.LogstashEncoder"/>
</appender>
```

```java
@Slf4j
@RestController
public class UserController {
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        log.info("Fetching user: {}", id);
        return userService.findById(id);
    }
}
```

# ✅ 20) Basic System Design and Scalability

## 233. How do you approach system design problems?

**Steps**: Understand requirements → Define scope → Estimate scale → Design high-level architecture → Deep dive components → Identify bottlenecks → Scale and optimize.

**Example:**
```
Design URL Shortener:

1. Requirements: Shorten URL, redirect, analytics
2. Scale: 100M URLs/month, 10:1 read:write ratio
3. Architecture:
   - API Gateway → Load Balancer
   - App Servers (Spring Boot)
   - Database (PostgreSQL) + Cache (Redis)
   - CDN for static content

4. Components:
   - POST /shorten → Generate short code → Store mapping
   - GET /{code} → Lookup → Redirect (302)
   
5. Bottlenecks: Database reads → Add Redis cache
6. Scale: Horizontal scaling + DB sharding by hash
```

---

## 234. What is load balancing?

Distributes incoming traffic across multiple servers to ensure no single server is overwhelmed.

**Example:**
```yaml
# Nginx Load Balancer
upstream backend {
    server app1.example.com:8080;
    server app2.example.com:8080;
    server app3.example.com:8080;
}

server {
    listen 80;
    location / {
        proxy_pass http://backend;
    }
}
```

```java
// Spring Boot app runs on multiple instances
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// Deploy on app1:8080, app2:8080, app3:8080
```

**Algorithms**: Round-robin, least connections, IP hash.

---

## 235. Horizontal vs vertical scaling.

**Vertical**: Add more CPU/RAM to existing server (scale up).

**Horizontal**: Add more servers (scale out).

**Example:**
```
Vertical Scaling:
Server: 4 CPU, 8GB RAM → 16 CPU, 64GB RAM
Pros: Simple, no code changes
Cons: Hardware limits, single point of failure

Horizontal Scaling:
1 Server → 10 Servers behind load balancer
Pros: No limits, fault tolerant
Cons: Complex, requires stateless design
```

```java
// Design for horizontal scaling - stateless
@RestController
public class OrderController {
    @Autowired
    private RedisTemplate<String, Object> redis; // Shared state
    
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        // No local state - can run on any server
        redis.opsForValue().set("order:" + order.getId(), order);
        return order;
    }
}
```

---

## 236. Explain CAP theorem.

**C**onsistency: All nodes see same data at same time.

**A**vailability: Every request gets a response.

**P**artition tolerance: System works despite network failures.

**You can only choose 2 of 3.**

**Example:**
```
CA (Consistency + Availability): Traditional RDBMS
- PostgreSQL, MySQL
- No partition tolerance

CP (Consistency + Partition tolerance): 
- MongoDB, HBase
- May reject requests during partition

AP (Availability + Partition tolerance):
- Cassandra, DynamoDB
- Eventually consistent
```

```java
// AP System - Eventually consistent
@Service
public class UserService {
    @Autowired
    private CassandraTemplate cassandra;
    
    public void updateUser(User user) {
        cassandra.update(user); // Available but may be stale
    }
}
```

---

## 237. What is eventual consistency?

Data will become consistent across all nodes eventually, but not immediately. Prioritizes availability over immediate consistency.

**Example:**
```java
// Social media post - eventual consistency
@Service
public class PostService {
    @Autowired
    private KafkaTemplate<String, Post> kafka;
    
    public void createPost(Post post) {
        // Write to primary DB
        postRepository.save(post);
        
        // Async propagate to replicas
        kafka.send("post-created", post);
        
        // Users may see old data for few seconds
    }
}
```

```
Timeline:
T0: User A posts → Saved to DB1
T1: User B reads → Sees old data (DB2 not updated yet)
T2: Replication completes
T3: User B reads → Sees new post
```

---

## 238. How do you design for high availability?

Eliminate single points of failure through redundancy, failover, and geographic distribution.

**Example:**
```yaml
# High Availability Architecture
Components:
- Load Balancer: 2 instances (active-passive)
- App Servers: 3+ instances across availability zones
- Database: Master-slave replication + auto-failover
- Cache: Redis Sentinel (3 nodes)
- Message Queue: Kafka cluster (3 brokers)

Availability = 99.99% (4 nines) = 52 min downtime/year
```

```java
// Health check for failover
@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        // Load balancer checks this
        return ResponseEntity.ok("UP");
    }
}
```

```properties
# Spring Boot - Multiple DB connections
spring.datasource.url=jdbc:mysql://primary:3306,secondary:3306/db
spring.datasource.hikari.connection-timeout=5000
```

---

## 239. What is database sharding?

Splitting database horizontally across multiple servers, each holding a subset of data.

**Example:**
```java
// Shard by user ID
@Service
public class UserService {
    private Map<Integer, DataSource> shards = new HashMap<>();
    
    public User getUser(Long userId) {
        int shardId = (int) (userId % 4); // 4 shards
        DataSource ds = shards.get(shardId);
        // Query from specific shard
        return jdbcTemplate.queryForObject(ds, 
            "SELECT * FROM users WHERE id = ?", userId);
    }
}
```

```
Sharding Strategy:
- Shard 0: userId % 4 = 0 (users 0, 4, 8, 12...)
- Shard 1: userId % 4 = 1 (users 1, 5, 9, 13...)
- Shard 2: userId % 4 = 2 (users 2, 6, 10, 14...)
- Shard 3: userId % 4 = 3 (users 3, 7, 11, 15...)

Pros: Horizontal scaling, better performance
Cons: Complex queries, rebalancing difficult
```

---

## 240. What is database replication?

Copying data from primary database to one or more replica databases for redundancy and read scaling.

**Example:**
```java
// Master-Slave replication
@Configuration
public class DataSourceConfig {
    @Bean
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://master:3306/db").build();
    }
    
    @Bean
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://slave:3306/db").build();
    }
}

@Service
public class UserService {
    @Autowired
    @Qualifier("masterDataSource")
    private DataSource master;
    
    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slave;
    
    public void createUser(User user) {
        // Write to master
        masterJdbc.update("INSERT INTO users...", user);
    }
    
    public User getUser(Long id) {
        // Read from slave
        return slaveJdbc.queryForObject("SELECT * FROM users WHERE id=?", id);
    }
}
```

**Types**: Master-Slave, Master-Master, Multi-Master.

---

## 241. What is message queue (Kafka vs RabbitMQ)?

Asynchronous communication between services using queues. Decouples producers and consumers.

**Kafka**: High-throughput, distributed streaming platform for event logs.

**RabbitMQ**: Traditional message broker with complex routing.

**Example:**
```java
// Kafka - Event streaming
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafka;
    
    public void createOrder(Order order) {
        orderRepository.save(order);
        kafka.send("order-events", order); // Fire and forget
    }
}

@KafkaListener(topics = "order-events")
public void handleOrder(Order order) {
    // Process asynchronously
    emailService.sendConfirmation(order);
}
```

```java
// RabbitMQ - Task queue
@Service
public class NotificationService {
    @Autowired
    private RabbitTemplate rabbit;
    
    public void sendNotification(String message) {
        rabbit.convertAndSend("notifications", message);
    }
}

@RabbitListener(queues = "notifications")
public void processNotification(String message) {
    // Worker processes message
    smsService.send(message);
}
```
# ✅ 21) Cloud Computing (AWS, Azure)

## 242. What is IaaS vs PaaS vs SaaS?

**IaaS (Infrastructure as a Service)**: Rent virtual machines, storage, networks. You manage OS, runtime, apps.

**PaaS (Platform as a Service)**: Managed platform for deploying apps. Provider manages OS, runtime.

**SaaS (Software as a Service)**: Ready-to-use software. Provider manages everything.

**Example:**
```
IaaS: AWS EC2, Azure VMs
- You install Java, Tomcat, deploy app
- Full control, more management

PaaS: AWS Elastic Beanstalk, Azure App Service
- Upload JAR, platform handles deployment
- Less control, less management

SaaS: Gmail, Salesforce, Office 365
- Just use the software
- No control, no management
```

```java
// Deploying Spring Boot on PaaS (Elastic Beanstalk)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// Just upload JAR - platform handles scaling, load balancing
```

---

## 243. What AWS/Azure services do you use for Spring Boot apps?

**Compute**: EC2, ECS, Lambda (AWS) | VMs, Container Instances, Functions (Azure)

**Database**: RDS, DynamoDB | Azure SQL, Cosmos DB

**Cache**: ElastiCache | Azure Cache for Redis

**Storage**: S3 | Blob Storage

**Messaging**: SQS, SNS, Kinesis | Service Bus, Event Hubs

**Example:**
```java
// AWS S3 Integration
@Service
public class FileService {
    @Autowired
    private AmazonS3 s3Client;
    
    public void uploadFile(MultipartFile file) {
        s3Client.putObject("my-bucket", 
            file.getOriginalFilename(), 
            file.getInputStream(), 
            new ObjectMetadata());
    }
}
```

```properties
# application.properties
cloud.aws.credentials.access-key=${AWS_ACCESS_KEY}
cloud.aws.credentials.secret-key=${AWS_SECRET_KEY}
cloud.aws.region.static=us-east-1
```

```java
// AWS RDS (PostgreSQL)
spring.datasource.url=jdbc:postgresql://mydb.abc.rds.amazonaws.com:5432/mydb
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

---

## 244. How do you manage secrets in cloud (AWS Secrets Manager)?

Store sensitive data (passwords, API keys) securely in cloud secret management services instead of hardcoding.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.amazonaws.secretsmanager</groupId>
    <artifactId>aws-secretsmanager-jdbc</artifactId>
</dependency>
```

```java
// AWS Secrets Manager
@Configuration
public class SecretsConfig {
    @Bean
    public String getDatabasePassword() {
        AWSSecretsManager client = AWSSecretsManagerClientBuilder
            .standard()
            .withRegion("us-east-1")
            .build();
        
        GetSecretValueRequest request = new GetSecretValueRequest()
            .withSecretId("prod/db/password");
        
        GetSecretValueResult result = client.getSecretValue(request);
        return result.getSecretString();
    }
}
```

```properties
# application.properties - Reference secrets
spring.datasource.url=jdbc:postgresql://mydb.rds.amazonaws.com:5432/db
spring.datasource.username=admin
spring.datasource.password=${DB_PASSWORD} # From Secrets Manager
```

```bash
# Store secret via AWS CLI
aws secretsmanager create-secret \
    --name prod/db/password \
    --secret-string "mySecurePassword123"
```

---

## 245. What is auto-scaling in cloud?

Automatically adjusts number of running instances based on demand (CPU, memory, request count).

**Example:**
```yaml
# AWS Auto Scaling Configuration
AutoScalingGroup:
  MinSize: 2
  MaxSize: 10
  DesiredCapacity: 3
  TargetTrackingScaling:
    TargetValue: 70  # CPU percentage
    ScaleOutCooldown: 300
    ScaleInCooldown: 300
```

```java
// Spring Boot app - stateless for auto-scaling
@RestController
public class OrderController {
    @Autowired
    private RedisTemplate redis; // Shared state
    
    @GetMapping("/orders")
    public List<Order> getOrders() {
        // No local state - can scale horizontally
        return orderService.findAll();
    }
}
```

**Scaling Policies:**
```
Scale Out: CPU > 70% → Add 2 instances
Scale In: CPU < 30% → Remove 1 instance

Example:
10 AM: 3 instances (normal load)
12 PM: 8 instances (lunch rush, high CPU)
3 PM: 4 instances (load decreased)
```

---

## 246. How do you design for disaster recovery?

Plan for failures with backups, replication, and multi-region deployment.

**Example:**
```yaml
# Multi-Region Architecture
Primary Region (us-east-1):
  - Application Servers
  - RDS Primary Database
  - S3 Bucket (versioning enabled)

Secondary Region (us-west-2):
  - Standby Application Servers
  - RDS Read Replica (auto-failover)
  - S3 Cross-Region Replication

Recovery Objectives:
  RTO (Recovery Time Objective): 15 minutes
  RPO (Recovery Point Objective): 5 minutes
```

```java
// Database failover configuration
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        // Primary and failover endpoints
        config.setJdbcUrl("jdbc:postgresql://primary.rds.amazonaws.com:5432," +
                         "secondary.rds.amazonaws.com:5432/mydb");
        config.setConnectionTimeout(5000);
        return new HikariDataSource(config);
    }
}
```

```properties
# Backup strategy
spring.datasource.hikari.connection-timeout=5000
# Automated daily backups
aws.rds.backup.retention-period=7
aws.rds.backup.window=03:00-04:00
```

**DR Strategies:**
- **Backup & Restore**: Cheapest, slowest (RTO: hours)
- **Pilot Light**: Minimal resources running (RTO: 10-30 min)
- **Warm Standby**: Scaled-down version running (RTO: minutes)
- **Multi-Site Active-Active**: Full capacity both regions (RTO: seconds)

# ✅ 22) Behavioral & Team Handling

## 247. Tell me about a challenging production bug you fixed.

**Structure**: Situation → Problem → Action → Result (STAR method)

**Example Answer:**
```
Situation: E-commerce checkout failing for 20% of users during Black Friday.

Problem: 
- Intermittent 500 errors
- No clear pattern in logs
- High-pressure situation (revenue loss)

Action:
1. Analyzed logs - found database connection timeouts
2. Checked connection pool metrics - all connections exhausted
3. Identified N+1 query problem in order processing
4. Root cause: Lazy loading in loop fetching order items

Code Issue:
for (Order order : orders) {
    order.getItems().size(); // N+1 queries
}

Fix Applied:
@Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id IN :ids")
List<Order> findOrdersWithItems(@Param("ids") List<Long> ids);

Result:
- Reduced DB queries from 1000+ to 1
- Response time: 5s → 200ms
- Zero errors, processed $2M in sales
- Added monitoring alerts for connection pool
```

---

## 248. How do you handle disagreements with team members?

**Approach**: Listen → Understand → Discuss → Data-driven decision → Respect outcome

**Example Answer:**
```
Situation: Disagreed on using microservices vs monolith for new feature.

My Approach:
1. Listen: Understood teammate's concerns about complexity
2. Present Data: Showed metrics on current monolith bottlenecks
3. Discuss Trade-offs:
   - Microservices: Better scaling, more complexity
   - Monolith: Simpler, but hitting limits
4. Compromise: Started with modular monolith, plan to extract later
5. Document Decision: Created ADR (Architecture Decision Record)

Outcome:
- Team aligned on approach
- Delivered feature on time
- Maintained good relationship
- Learned from teammate's perspective
```

**Key Principles:**
- Focus on problem, not person
- Use data and facts
- Be open to being wrong
- Find win-win solutions

---

## 249. How do you prioritize tasks when everything is urgent?

**Framework**: Impact vs Effort matrix + Business value

**Example Answer:**
```
Approach:
1. Assess Impact: Customer-facing > Internal tools
2. Evaluate Urgency: Deadlines, dependencies
3. Estimate Effort: Quick wins vs long tasks
4. Communicate: Set expectations with stakeholders

Real Example:
Monday morning - 5 "urgent" tasks:

Task A: Production bug (checkout broken)
  Impact: HIGH | Urgency: CRITICAL | Effort: 2h
  Priority: 1 - Do immediately

Task B: New feature demo for client
  Impact: HIGH | Urgency: HIGH | Effort: 4h
  Priority: 2 - Do today

Task C: Code review for teammate
  Impact: MEDIUM | Urgency: MEDIUM | Effort: 30m
  Priority: 3 - Quick win, do next

Task D: Refactoring old code
  Impact: LOW | Urgency: LOW | Effort: 8h
  Priority: 4 - Schedule for next sprint

Task E: Update documentation
  Impact: MEDIUM | Urgency: LOW | Effort: 1h
  Priority: 5 - Delegate or schedule

Action:
- Fixed production bug first (A)
- Communicated delays to stakeholders (B, C)
- Delegated documentation (E)
- Moved refactoring to backlog (D)
```

---

## 250. How do you mentor junior developers?

**Approach**: Teach → Guide → Review → Encourage independence

**Example Answer:**
```
My Mentoring Strategy:

1. Pair Programming (Week 1-2):
   - Work together on tasks
   - Explain thought process
   - Show debugging techniques

Example:
"Let's debug this NullPointerException together.
First, check the stack trace - line 45.
Now, let's add breakpoint and inspect variables.
See how user object is null? Let's trace back why."

2. Code Reviews (Ongoing):
   - Provide constructive feedback
   - Explain "why" not just "what"
   - Share best practices

Example Review Comment:
"Good start! Consider using Optional here to avoid NPE.
Instead of: if (user != null) { user.getName(); }
Try: Optional.ofNullable(user).map(User::getName).orElse('Unknown');
This makes null handling explicit. Here's a resource: [link]"

3. Assign Graduated Tasks:
   - Week 1: Bug fixes
   - Week 2: Small features
   - Month 2: Own feature end-to-end
   - Month 3: Lead small project

4. Regular 1-on-1s:
   - Discuss challenges
   - Answer questions
   - Set learning goals

5. Encourage Learning:
   - Share articles, courses
   - Recommend books (Effective Java, Clean Code)
   - Encourage experimentation

Result:
- Junior dev became productive in 1 month
- Independently delivered features by month 3
- Now mentoring others
```

**Key Principles:**
- Be patient and approachable
- Teach problem-solving, not just solutions
- Celebrate small wins
- Create safe environment for questions

---