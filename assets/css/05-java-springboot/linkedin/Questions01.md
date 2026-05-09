# JAVA CORE – MUST-ASK INTERVIEW QUESTIONS (5+ YEARS) Part-1

---

## OOPS (Depth matters)

### 1. Abstraction vs Encapsulation (real project example)
- **Abstraction**: Hiding *what* is done (using interface/abstract class). E.g., `PaymentService` interface hides UPI/Card logic.
- **Encapsulation**: Hiding *how* it is done (private fields + getters/setters).

```java
// Abstraction
interface PaymentService { void pay(double amount); }

// Encapsulation
class BankAccount {
    private double balance;
    public double getBalance() { return balance; }
    public void deposit(double amt) { balance += amt; }
}
```

---

### 2. Interface vs Abstract class (what changed after Java 8?)
| | Interface | Abstract Class |
|---|---|---|
| State | No instance fields | Can have fields |
| Constructor | No | Yes |
| Java 8+ | `default` & `static` methods allowed | Same as before |

```java
interface Flyable {
    default void fly() { System.out.println("Flying"); } // Java 8+
}
abstract class Animal {
    String name;
    abstract void sound();
}
```

---

### 3. How is multiple inheritance achieved in Java?
Java doesn't allow multiple class inheritance but allows implementing multiple interfaces.

```java
interface A { default void hello() { System.out.println("A"); } }
interface B { default void hello() { System.out.println("B"); } }

class C implements A, B {   
    public void hello() {
        A.super.hello(); 
    } // must override to resolve conflict
}
```

---

### 4. What is composition and Why is composition preferred over inheritance?
Composition = "has-a" relationship. Preferred because it avoids tight coupling and fragile base class problem.

```java
class Engine { void start() { System.out.println("Engine started"); } }

class Car {
    private Engine engine = new Engine(); // composition
    void start() { engine.start(); }
}
```

---

### 5. Can we override a static method? Why?
No. Static methods belong to the class, not the instance. You can *hide* them, not override.

```java
class Parent { static void show() { System.out.println("Parent"); } }
class Child extends Parent { static void show() { System.out.println("Child"); } } // method hiding

Parent p = new Child();
p.show(); // prints "Parent" — not polymorphic
```

---

### 6. Can a constructor be overridden?
No. Constructors are not inherited, so they cannot be overridden. Each class defines its own constructor.

```java
class A { A() { System.out.println("A constructor"); } }
class B extends A { B() { super(); System.out.println("B constructor"); } }
```

---

### 7. What is the Diamond problem? How does Java solve it?
When two interfaces have the same default method, the implementing class must override it.

```java
interface A { default void greet() { System.out.println("A"); } }
interface B { default void greet() { System.out.println("B"); } }

class C implements A, B {
    public void greet() { A.super.greet(); } // explicit resolution
}
```

---

### 8. SOLID principles – Single Responsibility Principle in detail
A class should have only one reason to change.

```java
// BAD: one class handles both order and email
class OrderService { void placeOrder() {} void sendEmail() {} }

// GOOD: separate responsibilities
class OrderService { void placeOrder() {} }
class EmailService { void sendEmail() {} }
```

---

## OBJECT CLASS (Favorite trap area)

### 9. equals() vs ==
- `==` compares references (memory address).
- `equals()` compares logical content (if overridden).

```java
String a = new String("hello");
String b = new String("hello");
System.out.println(a == b);       // false
System.out.println(a.equals(b));  // true
```

---

### 10. Why override hashCode() when equals() is overridden?
Contract: if `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must also be true. HashMap uses hashCode to find the bucket.

```java
class Employee {
    int id;
    public boolean equals(Object o) { return ((Employee)o).id == this.id; }
    public int hashCode() { return Integer.hashCode(id); }
}
```

---

### 11. Can two objects have the same hashCode but not be equal?
Yes — this is a **hash collision**. hashCode is not unique; equals() is the final arbiter.

```java
"Aa".hashCode() == "BB".hashCode() // true, but "Aa".equals("BB") is false
```

---

### 12. What happens if hashCode changes after putting an object in HashMap?
The object becomes unretrievable — it's stored in one bucket but looked up in another.

```java
// Never use mutable fields in hashCode for Map keys
Map<List<Integer>, String> map = new HashMap<>();
List<Integer> key = new ArrayList<>(List.of(1));
map.put(key, "value");
key.add(2); // mutates key — map.get(key) returns null now
```

---

### 13. Why is String immutable?
- Security (class loading, network params)
- Thread safety
- String pool reuse
- hashCode caching

```java
String s = "hello";
s.concat(" world"); // creates new object, s is unchanged
System.out.println(s); // "hello"
```

---

### 14. How does String pool work internally?
String literals are stored in the **String Pool** (part of Heap since Java 7). `intern()` forces a string into the pool.

```java
String a = "hello";           // pool
String b = "hello";           // same pool reference
String c = new String("hello").intern(); // forced into pool
System.out.println(a == b);   // true
System.out.println(a == c);   // true
```

---

### 15. Difference between String, StringBuilder, and StringBuffer
| | String | StringBuilder | StringBuffer |
|---|---|---|---|
| Mutable | No | Yes | Yes |
| Thread-safe | Yes (immutable) | No | Yes (synchronized) |
| Performance | Slow (concat) | Fast | Slower than SB |

```java
StringBuilder sb = new StringBuilder("hello");
sb.append(" world"); // mutable, no new object
```

---

## MULTITHREADING (Interview killer)

### 16. What is thread safety?
Code is thread-safe if it behaves correctly when accessed by multiple threads simultaneously.

```java
class Counter {
    private int count = 0;
    public synchronized void increment() { count++; } // thread-safe
}
```

---

### 17. synchronized vs ReentrantLock
- `synchronized`: simpler, auto-release
- `ReentrantLock`: tryLock, fairness, interruptible

```java
ReentrantLock lock = new ReentrantLock();
lock.lock();
try { /* critical section */ }
finally { lock.unlock(); }
```

---

### 18. volatile keyword – real use case
Ensures visibility of changes across threads. Does NOT guarantee atomicity.

```java
class Worker {
    private volatile boolean running = true;
    void stop() { running = false; }
    void run() { while (running) { /* work */ } }
}
```

---

### 19. wait() vs sleep()
| | wait() | sleep() |
|---|---|---|
| Releases lock | Yes | No |
| Called on | Object | Thread |
| Woken by | notify() | timeout |

```java
synchronized (obj) { obj.wait(); }   // releases lock
Thread.sleep(1000);                   // holds lock
```

---

### 20. notify() vs notifyAll()
- `notify()`: wakes one random waiting thread.
- `notifyAll()`: wakes all waiting threads.

```java
synchronized (obj) { obj.notifyAll(); }
```

---

### 21. What is deadlock? Give an example.
Two threads wait for each other's lock forever.

```java
Object A = new Object(), B = new Object();
Thread t1 = new Thread(() -> { synchronized(A) { synchronized(B) {} } });
Thread t2 = new Thread(() -> { synchronized(B) { synchronized(A) {} } });
t1.start(); t2.start(); // deadlock
```

---

### 22. How to prevent deadlock?
- Always acquire locks in the same order.
- Use `tryLock()` with timeout.
- Avoid nested locks.

```java
// Always lock A before B in all threads
synchronized(A) { synchronized(B) { /* safe */ } }
```

---

### 23. Thread lifecycle
`NEW → RUNNABLE → RUNNING → BLOCKED/WAITING/TIMED_WAITING → TERMINATED`

```java
Thread t = new Thread(() -> System.out.println("running")); // NEW
t.start(); // RUNNABLE → RUNNING → TERMINATED
```

---

### 24. Callable vs Runnable
- `Runnable`: no return value, no checked exception.
- `Callable`: returns a value, can throw checked exception.

```java
Callable<Integer> task = () -> 42;
Future<Integer> result = Executors.newSingleThreadExecutor().submit(task);
System.out.println(result.get()); // 42
```

---

### 25. Future vs CompletableFuture
- `Future`: blocking `.get()`, no chaining.
- `CompletableFuture`: non-blocking, supports chaining, callbacks.

```java
CompletableFuture.supplyAsync(() -> "hello")
    .thenApply(String::toUpperCase)
    .thenAccept(System.out::println); // HELLO
```

---

### 26. How does thread pool work internally?
Maintains a pool of worker threads. Tasks are queued in a `BlockingQueue`. Workers pick tasks when free.

```java
ExecutorService pool = Executors.newFixedThreadPool(4);
pool.submit(() -> System.out.println("task"));
pool.shutdown();
```

---

### 27. ExecutorService vs ForkJoinPool
- `ExecutorService`: general-purpose thread pool.
- `ForkJoinPool`: work-stealing, designed for recursive divide-and-conquer tasks.

```java
ForkJoinPool pool = new ForkJoinPool();
pool.invoke(new RecursiveTask<Integer>() {
    protected Integer compute() { return 1 + 1; }
});
```

---

## COLLECTIONS (Most asked)

### 28. HashMap internal working (Java 8)
Array of buckets. Each bucket is a LinkedList; becomes a **Red-Black Tree** when bucket size > 8 (treeification).

```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1); // hash("a") → bucket index → store Node
```

---

### 29. Why is HashMap not thread-safe?
Concurrent `put()` can cause infinite loop (Java 7) or data loss (Java 8) due to unsynchronized resize.

```java
// Use ConcurrentHashMap instead
Map<String, Integer> map = new ConcurrentHashMap<>();
```

---

### 30. ConcurrentHashMap internal working
Uses **segment-level locking** (Java 7) / **CAS + synchronized on bucket head** (Java 8). Reads are lock-free.

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1);
map.computeIfAbsent("key2", k -> 2);
```

---

### 31. HashMap vs Hashtable vs ConcurrentHashMap
| | HashMap | Hashtable | ConcurrentHashMap |
|---|---|---|---|
| Thread-safe | No | Yes (full lock) | Yes (bucket lock) |
| Null key | 1 allowed | Not allowed | Not allowed |
| Performance | Fast | Slow | Fast |

---

### 32. Why does HashMap allow one null key?
`null` key is handled specially — it's always placed in bucket 0 (no `hashCode()` call).

```java
Map<String, String> map = new HashMap<>();
map.put(null, "value"); // stored at index 0
```

---

### 33. TreeMap vs HashMap
- `HashMap`: O(1) get/put, unordered.
- `TreeMap`: O(log n) get/put, sorted by key (Red-Black Tree).

```java
TreeMap<String, Integer> map = new TreeMap<>();
map.put("b", 2); map.put("a", 1);
System.out.println(map); // {a=1, b=2} — sorted
```

---

### 34. How does sorting work in TreeMap?
Uses natural ordering (`Comparable`) or a custom `Comparator` passed at construction.

```java
TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
map.put("a", 1); map.put("b", 2);
System.out.println(map); // {b=2, a=1}
```

---

### 35. Fail-fast vs Fail-safe iterators
- **Fail-fast**: throws `ConcurrentModificationException` if modified during iteration (e.g., `ArrayList`).
- **Fail-safe**: iterates over a copy, no exception (e.g., `CopyOnWriteArrayList`).

```java
List<String> list = new CopyOnWriteArrayList<>(List.of("a", "b"));
for (String s : list) { list.add("c"); } // no exception
```

---

### 36. Why is entrySet() faster than keySet()?
`keySet()` requires a second lookup (`map.get(key)`) per entry. `entrySet()` gives key+value in one shot.

```java
for (Map.Entry<String, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + "=" + e.getValue()); // single traversal
}
```

---

### 37. How to use a custom object as a Map key?
Override `equals()` and `hashCode()`. Prefer immutable objects.

```java
class EmpId {
    final int id;
    EmpId(int id) { this.id = id; }
    public boolean equals(Object o) { return ((EmpId)o).id == id; }
    public int hashCode() { return Integer.hashCode(id); }
}
Map<EmpId, String> map = new HashMap<>();
```

---

### 38. Comparable vs Comparator (real use case)
- `Comparable`: natural order, defined inside the class (`compareTo`).
- `Comparator`: external, multiple sort strategies.

```java
// Comparable
class Employee implements Comparable<Employee> {
    int salary;
    public int compareTo(Employee o) { return this.salary - o.salary; }
}

// Comparator
List<Employee> list = ...;
list.sort(Comparator.comparing(e -> e.salary));
```

---

## EXCEPTION HANDLING

### 39. Checked vs Unchecked exceptions
- **Checked**: must be declared/caught (e.g., `IOException`). Compile-time.
- **Unchecked**: `RuntimeException` subclasses (e.g., `NullPointerException`). Runtime.

```java
void read() throws IOException { } // checked
void divide() { int x = 1/0; }    // unchecked — ArithmeticException
```

---

### 40. try-catch-finally execution order
`try → catch (if exception) → finally (always)`

```java
try { return 1; }
catch (Exception e) { return 2; }
finally { System.out.println("always runs"); } // prints before return
```

---

### 41. Can finally block be skipped?
Yes — only if `System.exit()` is called or JVM crashes.

```java
try { System.exit(0); }
finally { System.out.println("never runs"); }
```

---

### 42. throw vs throws
- `throw`: actually throws an exception instance.
- `throws`: declares that a method may throw an exception.

```java
void validate(int age) throws IllegalArgumentException {
    if (age < 0) throw new IllegalArgumentException("Invalid age");
}
```

---

### 43. When and why do you create custom exceptions?
When you need domain-specific error semantics with meaningful names.

```java
class InsufficientFundsException extends RuntimeException {
    InsufficientFundsException(double amount) {
        super("Insufficient funds: " + amount);
    }
}
// Usage
throw new InsufficientFundsException(500.0);
```

---

### 44. Exception handling best practices in REST APIs
- Use `@ControllerAdvice` + `@ExceptionHandler`.
- Return structured error response.
- Never expose stack traces to clients.

```java
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientFundsException.class)
    ResponseEntity<String> handle(InsufficientFundsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
```

---

## JVM / MEMORY (Senior filter)

### 45. JVM vs JRE vs JDK
- **JDK** = JRE + compiler + tools (for developers)
- **JRE** = JVM + libraries (to run Java apps)
- **JVM** = executes bytecode (platform-specific)

---

### 46. Stack vs Heap
| | Stack | Heap |
|---|---|---|
| Stores | Method frames, local vars | Objects, instance vars |
| Thread | Per-thread | Shared |
| Size | Small, fast | Large, GC managed |

```java
void method() {
    int x = 5;           // stack
    String s = new String("hi"); // reference on stack, object on heap
}
```

---

### 47. What goes into Metaspace?
Class metadata, method bytecode, static variables (since Java 8). Replaced PermGen. Grows dynamically by default.

---

### 48. Types of Garbage Collectors
| GC | Use case |
|---|---|
| Serial GC | Single-threaded, small apps |
| Parallel GC | Throughput-focused |
| G1 GC | Default Java 9+, balanced |
| ZGC / Shenandoah | Low-latency, large heaps |

```bash
java -XX:+UseG1GC -jar app.jar
```

---

### 49. Minor GC vs Major GC
- **Minor GC**: cleans **Young Generation** (Eden + Survivor). Fast, frequent.
- **Major GC**: cleans **Old Generation**. Slower, less frequent.
- **Full GC**: cleans entire heap + Metaspace. Avoid in production.

---

### 50. How to debug memory leaks?
1. Use `-Xmx` to limit heap and reproduce OOM.
2. Take heap dump: `-XX:+HeapDumpOnOutOfMemoryError`
3. Analyze with **Eclipse MAT** or **VisualVM**.

```bash
java -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -jar app.jar
```

---

### 51. Causes of OutOfMemoryError
- Too many objects in heap (memory leak)
- Large static collections holding references
- Infinite recursion (StackOverflowError)
- Metaspace full (too many class loaders)

```java
// Classic leak: static list holding references forever
static List<byte[]> leak = new ArrayList<>();
void leakMemory() { leak.add(new byte[1024 * 1024]); }
```

---

### 52. Why finalize() is deprecated?
- Unpredictable execution time (GC decides when)
- Can cause object resurrection
- Performance overhead
- Use `try-with-resources` or `Cleaner` instead.

```java
// Preferred: AutoCloseable
class Resource implements AutoCloseable {
    public void close() { System.out.println("cleaned up"); }
}
try (Resource r = new Resource()) { /* use */ }
```

---
*36 reactions*
