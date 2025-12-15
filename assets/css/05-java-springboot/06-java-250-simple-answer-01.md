# Java Interview Question Bank
*Top Questions Frequently Asked in Real-World Technical Interviews*

## 1. Basic Concepts (6 Questions)
### 1. What is Java and what are its key features?

* Java is a **high-level, object-oriented, platform-independent** programming language.
* It follows **“Write Once, Run Anywhere”** using bytecode and the JVM.
* Key features: **OOP**, **platform independence**, **robust**, **secure**, **multithreaded**, and **automatic garbage collection**.
* Widely used for **enterprise apps, web apps, Android, and backend systems**.

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

---

### 2. Explain the difference between JDK, JRE, and JVM

* **JVM** runs Java bytecode and makes Java platform-independent.
* **JRE** = JVM + core libraries → used to **run** Java programs.
* **JDK** = JRE + development tools → used to **develop** Java programs.
* In short: **JDK ⊃ JRE ⊃ JVM**.

```text
JDK → JRE → JVM
```

---

### 3. What are the main principles of Object-Oriented Programming?

* **Encapsulation** – hide data using private fields.
* **Inheritance** – reuse code using parent-child relationship.
* **Polymorphism** – one interface, many implementations.
* **Abstraction** – show what, hide how.

```java
class Car {
    void start() {
        System.out.println("Car starts");
    }
}
```

---

### 4. What is polymorphism? Explain with examples.

* Polymorphism means **many forms**.
* Achieved using **method overriding** and **method overloading**.
* Runtime polymorphism happens through **parent reference, child object**.

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

Animal a = new Dog();
a.sound(); // Dog barks
```

---

### 5. What is encapsulation and how is it implemented in Java?

* Encapsulation means **binding data and methods together**.
* Implemented using **private variables** and **public getters/setters**.
* Improves **security** and **maintainability**.

```java
class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance = amount;
    }
}
```

---

### 6. What is inheritance and what are its types?

* Inheritance allows a class to **reuse properties of another class** using `extends`.
* Promotes **code reuse** and **IS-A relationship**.
* Types in Java:

  * Single
  * Multilevel
  * Hierarchical
* Java does **not support multiple inheritance with classes** (uses interfaces).

```java
class Vehicle {
    void move() {
        System.out.println("Vehicle moves");
    }
}

class Bike extends Vehicle {
}
```

---

### 7. What is an abstract class?

* An abstract class is a class that **cannot be instantiated**.
* It can have **abstract and non-abstract methods**.
* Used when classes share common behavior but need specific implementations.

```java
abstract class Shape {
    abstract void draw();

    void display() {
        System.out.println("Displaying shape");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}
```

---

## 2. Data Types and Variables (8 Questions)
### 1. What are primitive data types in Java?

* Primitive data types store **actual values**, not objects.
* Java has **8 primitive types**:
  `byte, short, int, long, float, double, char, boolean`
* They are **faster** and use **less memory** than objects.

```java
int age = 25;
double salary = 45000.50;
boolean active = true;
```

---

### 2. What is the difference between primitive and reference types?

* **Primitive types** store values directly.
* **Reference types** store memory addresses of objects.
* Primitives cannot be `null`, references can be.

```java
int x = 10;              // primitive
String name = "Java";   // reference
```

---

### 3. What is autoboxing and unboxing?

* **Autoboxing**: converting primitive → wrapper object.
* **Unboxing**: converting wrapper → primitive.
* Happens automatically in Java.

```java
Integer a = 10;  // autoboxing
int b = a;       // unboxing
```

---

### 4. What is the difference between `==` and `equals()` method?

* `==` compares **memory references**.
* `equals()` compares **content/value**.
* Always use `equals()` for object comparison.

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2);       // false
System.out.println(s1.equals(s2));  // true
```

---

### 5. What is the difference between String, StringBuilder, and StringBuffer?

* **String** is immutable.
* **StringBuilder** is mutable and **not thread-safe** (fast).
* **StringBuffer** is mutable and **thread-safe** (slower).

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" Java");
System.out.println(sb);
```

---

### 6. Why are strings immutable in Java?

* Improves **security** (used in passwords, URLs).
* Enables **string pooling** for memory efficiency.
* Makes strings **thread-safe**.

```java
String s = "Java";
s.concat(" World"); // creates new object
System.out.println(s); // Java
```

---

### 7. What is string pooling?

* String literals are stored in a **special memory area called String Pool**.
* Same literal shares the same object to save memory.
* `new String()` always creates a new object.

```java
String a = "Java";
String b = "Java";
System.out.println(a == b); // true
```

---

### 8. What is the difference between final, finally, and finalize?

* **final** → prevents change (variable, method, class).
* **finally** → block that always executes after try-catch.
* **finalize()** → called by GC before object destruction.

```java
final int x = 10;

try {
    int y = 10 / 0;
} finally {
    System.out.println("Always executed");
}
```

---


## 3. Classes and Objects (7 Questions)
### 1. What is a constructor in Java?

* A constructor is a **special method** used to initialize objects.
* It has the **same name as the class** and **no return type**.
* Called **automatically** when an object is created.

```java
class Person {
    Person() {
        System.out.println("Object created");
    }
}

Person p = new Person();
```

---

### 2. What is constructor chaining?

* Constructor chaining means **calling one constructor from another**.
* Done using `this()` within the same class.
* Reduces **code duplication**.

```java
class Demo {
    Demo() {
        this(10);
    }

    Demo(int x) {
        System.out.println(x);
    }
}
```

---

### 3. What is the difference between `this` and `super` keywords?

* `this` refers to the **current class object**.
* `super` refers to the **parent class object**.
* Used to access variables, methods, and constructors.

```java
class A {
    int x = 10;
}

class B extends A {
    int x = 20;

    void show() {
        System.out.println(this.x);   // 20
        System.out.println(super.x);  // 10
    }
}
```

---

### 4. What is method overloading?

* Method overloading means **same method name, different parameters**.
* Happens at **compile time**.
* Improves **readability**.

```java
class Calc {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

---

### 5. What is method overriding?

* Method overriding means **child class provides its own implementation** of parent method.
* Method signature must be **same**.
* Happens at **runtime**.

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

---

### 6. What is the difference between overloading and overriding?

* **Overloading** → same class, different parameters, compile-time.
* **Overriding** → different classes, same method, runtime.
* Overloading does not require inheritance; overriding does.

```java
// Overloading
add(int a, int b)

// Overriding
void sound()
```

---

### 7. What is dynamic method dispatch?

* Dynamic method dispatch is **runtime method resolution**.
* Parent reference points to child object.
* Ensures **runtime polymorphism**.

```java
Animal a = new Dog();
a.sound(); // Dog barks
```

---

## 4. Inheritance (6 Questions)
### **1. Why doesn't Java support multiple inheritance?**

* Java avoids multiple inheritance to prevent **ambiguity and complexity**
* The main issue is **method conflict** when two parent classes have the same method
* It can make code **hard to maintain and debug**
* Java instead supports **multiple inheritance through interfaces**, which is safer

**Example (problem scenario):**

```java
class A {
    void show() { }
}

class B {
    void show() { }
}

// class C extends A, B { }  // ❌ Not allowed
```

---

### **2. What is the diamond problem?**

* It occurs when a class inherits from **two classes that share a common parent**
* The compiler gets confused about **which method to call**
* This creates **ambiguity in method resolution**

**Structure:**

```
    A
   / \
  B   C
   \ /
    D
```

**Problem:**

* If `A` has a method and both `B` and `C` override it
* Which version should `D` inherit?

---

### **3. How does Java solve the diamond problem?**

* Java **does not allow multiple inheritance with classes**
* With **interfaces**, Java forces the class to **explicitly override** the method
* This removes ambiguity and keeps behavior clear

**Example using interface:**

```java
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}

class C implements A, B {
    public void show() {
        A.super.show(); // explicitly choosing
    }
}
```

---

### **4. Can you override static methods?**

* ❌ **No**, static methods **cannot be overridden**
* Static methods belong to the **class**, not the object
* They can only be **hidden**, not overridden

**Example:**

```java
class Parent {
    static void display() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void display() {
        System.out.println("Child");
    }
}
```

**Call behavior:**

```java
Parent p = new Child();
p.display();  // Output: Parent
```

---

### **5. What is covariant return type?**

* It allows an **overridden method** to return a **subclass type**
* Introduced in **Java 5**
* Improves **readability and flexibility**

**Example:**

```java
class Animal {
    Animal getAnimal() {
        return new Animal();
    }
}

class Dog extends Animal {
    Dog getAnimal() {
        return new Dog();
    }
}
```

---

### **6. What is the difference between IS-A and HAS-A relationship?**

### **IS-A (Inheritance)**

* Represents **inheritance**
* Achieved using `extends`
* Shows **parent-child relationship**

```java
class Dog extends Animal {
}
```

### **HAS-A (Composition)**

* Represents **ownership**
* Achieved using object reference
* More flexible than inheritance

```java
class Car {
    Engine engine = new Engine();
}
```

**Key Difference:**

* IS-A → *Dog is an Animal*
* HAS-A → *Car has an Engine*

---

## 5. Interfaces and Abstract Classes (8 Questions)
### **1. What is an interface in Java?**

* An interface is a **contract** that defines *what a class must do*
* It contains **method declarations** (and from Java 8, default & static methods)
* Used to achieve **100% abstraction** and **multiple inheritance**
* Classes use `implements` keyword

**Example:**

```java
interface Vehicle {
    void start();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }
}
```

---

### **2. What is an abstract class?**

* An abstract class is a **partial abstraction**
* It can have **abstract and concrete methods**
* It can contain **constructors, instance variables, and state**
* Used when classes share **common behavior**

**Example:**

```java
abstract class Animal {
    abstract void sound();

    void sleep() {
        System.out.println("Sleeping");
    }
}
```

---

### **3. What is the difference between interface and abstract class?**

| Feature              | Interface                 | Abstract Class      |
| -------------------- | ------------------------- | ------------------- |
| Multiple inheritance | ✅ Supported               | ❌ Not supported     |
| Method types         | Abstract, default, static | Abstract + concrete |
| Variables            | `public static final`     | Any type            |
| Constructors         | ❌ No                      | ✅ Yes               |
| Keyword              | `implements`              | `extends`           |

**Interview tip:**

* Use **interface** for capability
* Use **abstract class** for shared base logic

---

### **4. What are default methods in interfaces?**

* Introduced in **Java 8**
* Allow adding new methods **without breaking existing classes**
* Can have method body using `default` keyword

**Example:**

```java
interface Vehicle {
    default void fuelType() {
        System.out.println("Petrol");
    }
}
```

---

### **5. What are static methods in interfaces?**

* Introduced in **Java 8**
* Belong to the **interface itself**
* Cannot be overridden or accessed via object

**Example:**

```java
interface Utils {
    static void log() {
        System.out.println("Logging");
    }
}

// Call
Utils.log();
```

---

### **6. What is a marker interface?**

* An interface **with no methods**
* Used to **mark a class** for special behavior
* JVM checks it using `instanceof`

**Examples:**

* `Serializable`
* `Cloneable`
* `RandomAccess`

```java
class Student implements Serializable {
}
```

---

### **7. What is a functional interface?**

* An interface with **exactly one abstract method**
* Used in **lambda expressions**
* Marked with `@FunctionalInterface` (optional but recommended)

**Example:**

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

Calculator c = (a, b) -> a + b;
```

---

### **8. Can an interface extend another interface?**

* ✅ **Yes**
* An interface can extend **one or multiple interfaces**
* Achieves **multiple inheritance safely**

**Example:**

```java
interface A {
    void methodA();
}

interface B {
    void methodB();
}

interface C extends A, B {
}
```

---

## 6. Exception Handling (8 Questions)
### **1. What is an exception in Java?**

* An exception is a **runtime problem** that **interrupts normal program flow**.
* It happens when something unexpected occurs, like dividing by zero or file not found.

**Example:**

```java
int a = 10 / 0;  // ArithmeticException
```

---

### **2. What is the exception hierarchy in Java?**

* All exceptions come from the **Throwable** class.
* It has two main branches:

  * **Error** → serious problems (OutOfMemoryError)
  * **Exception** → problems we can handle

**Hierarchy:**

```
Throwable
 ├── Error
 └── Exception
      ├── Checked Exception
      └── RuntimeException
```

---

### **3. What are checked and unchecked exceptions?**

* **Checked exceptions** are checked at compile time.
* **Unchecked exceptions** happen at runtime.

**Examples:**

```java
// Checked
FileReader fr = new FileReader("file.txt");

// Unchecked
int x = 10 / 0;
```

---

### **4. Difference between throw and throws?**

* **throw** → used to **explicitly throw** an exception.
* **throws** → used in method signature to **declare exceptions**.

**Example:**

```java
void checkAge(int age) throws Exception {
    if (age < 18) {
        throw new Exception("Not eligible");
    }
}
```

---

### **5. What is try-catch-finally block?**

* **try** → code that may cause exception
* **catch** → handles exception
* **finally** → always executes (cleanup code)

**Example:**

```java
try {
    int a = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error occurred");
} finally {
    System.out.println("Always executed");
}
```

---

### **6. What is try-with-resources?**

* Automatically **closes resources** like files and streams.
* No need for finally block.

**Example:**

```java
try (FileReader fr = new FileReader("file.txt")) {
    System.out.println("Reading file");
}
```

---

### **7. How do you create custom exceptions?**

* Extend the **Exception** or **RuntimeException** class.

**Example:**

```java
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}
```

Usage:

```java
throw new InvalidAgeException("Age is invalid");
```

---

### **8. What is exception chaining?**

* Passing one exception as the **cause of another**.
* Helps track root cause.

**Example:**

```java
try {
    int a = 10 / 0;
} catch (ArithmeticException e) {
    throw new RuntimeException("Calculation failed", e);
}
```

---

## 7. Collections Framework (8 Questions)
### **1. What is Java Collections Framework?**

* It’s a **set of classes and interfaces** used to **store, manipulate, and retrieve data**.
* It provides **ready-made data structures** like List, Set, Map, Queue.
* Benefits: **reduces coding effort**, **improves performance**, and **standardizes APIs**.
* Common interfaces: `List`, `Set`, `Map`, `Queue`.

```java
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Spring");
```

---

### **2. Difference between ArrayList and LinkedList**

* **ArrayList** uses a **dynamic array** → fast **read**, slow **insert/delete** in middle.
* **LinkedList** uses **doubly linked list** → slow **read**, fast **insert/delete**.
* ArrayList is better for **searching**, LinkedList for **frequent modifications**.

```java
List<Integer> a = new ArrayList<>();
List<Integer> l = new LinkedList<>();
```

---

### **3. Difference between HashMap and TreeMap**

* **HashMap** stores data in **no order**.
* **TreeMap** stores data in **sorted order** (natural or comparator).
* HashMap is **faster**, TreeMap is used when **ordering is required**.

```java
Map<Integer, String> map1 = new HashMap<>();
Map<Integer, String> map2 = new TreeMap<>();
```

---

### **4. Difference between HashMap and Hashtable**

* **HashMap** is **not synchronized**, faster.
* **Hashtable** is **synchronized**, thread-safe but slower.
* HashMap allows **one null key and multiple null values**.
* Hashtable allows **no null key or value**.

```java
Map<String, String> map = new HashMap<>();
Hashtable<String, String> table = new Hashtable<>();
```

---

### **5. How does HashMap work internally?**

* HashMap uses **hashing** to store key-value pairs.
* Key’s `hashCode()` determines the **bucket index**.
* If multiple keys map to same bucket → **linked list / tree (Java 8+)**.
* Uses `equals()` to identify the correct key.

```java
map.put("A", 1); // hashCode → bucket → store entry
```

---

### **6. What is hash collision and how is it handled?**

* **Collision** happens when two keys have same hash value.
* HashMap handles it using:

  * **Linked List** (before Java 8)
  * **Balanced Tree (Red-Black Tree)** (Java 8+)
* This ensures performance doesn’t degrade badly.

```java
map.put("FB", 1);
map.put("Ea", 2); // Same hashCode → collision
```

---

### **7. Difference between fail-fast and fail-safe iterators**

* **Fail-fast** throws `ConcurrentModificationException`.
* **Fail-safe** works on a **copy**, no exception.
* Fail-fast is faster, fail-safe is safer.

```java
// Fail-fast
Iterator it = list.iterator();

// Fail-safe
Iterator it2 = new CopyOnWriteArrayList<>(list).iterator();
```

---

### **8. Difference between Comparable and Comparator**

* **Comparable** is used for **natural ordering**.
* **Comparator** is used for **custom ordering**.
* Comparable has `compareTo()`, Comparator has `compare()`.

```java
class Employee implements Comparable<Employee> {
    public int compareTo(Employee e) {
        return this.id - e.id;
    }
}

// Comparator
Comparator<Employee> comp =
    (e1, e2) -> e1.name.compareTo(e2.name);
```

---

### **9. What are WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?**

* **WeakHashMap**: Keys removed when no strong reference exists.
* **IdentityHashMap**: Uses `==` instead of `equals()`.
* **LinkedHashMap**: Maintains **insertion order**.
* **PriorityQueue**: Elements processed by **priority**, not FIFO.

```java
Map<Object, String> weakMap = new WeakHashMap<>();
Map<String, String> linkedMap = new LinkedHashMap<>();

Queue<Integer> pq = new PriorityQueue<>();
pq.add(10);
pq.add(5);
```

---

## 8. Multithreading and Synchronization (8 Questions)
### 1. What is multithreading?

* Multithreading means **running multiple threads concurrently** within a single program
* Helps improve **performance and responsiveness**
* Threads share the **same memory**, unlike processes

**Example**

```java
class MyTask extends Thread {
    public void run() {
        System.out.println("Task running");
    }
}
```

---

### 2. How do you create threads in Java?

**Two common ways:**

### 1️⃣ Extend `Thread`

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
new MyThread().start();
```

### 2️⃣ Implement `Runnable` (preferred)

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Thread running");
    }
}
new Thread(new MyTask()).start();
```

---

### 3. Difference between extending Thread and implementing Runnable?

* `Thread`: you **cannot extend another class**
* `Runnable`: allows **multiple inheritance**
* `Runnable` is **more flexible and reusable**
* **Best practice → Runnable**

---

### 4. What are the states of a thread?

* **NEW** – thread created but not started
* **RUNNABLE** – ready or running
* **BLOCKED** – waiting for monitor lock
* **WAITING** – waiting indefinitely
* **TIMED_WAITING** – waiting for fixed time
* **TERMINATED** – execution finished

---

### 5. What is synchronization in Java?

* Synchronization **controls access to shared resources**
* Prevents **data inconsistency**
* Uses **monitor lock**

**Example**

```java
synchronized void increment() {
    count++;
}
```

---

### 6. What is deadlock and how do you prevent it?

* Deadlock happens when **two threads wait for each other forever**

**Example scenario**

* Thread A holds Lock1 → waits for Lock2
* Thread B holds Lock2 → waits for Lock1

**Prevention**

* Avoid nested locks
* Use **lock ordering**
* Use `tryLock()` (ReentrantLock)
* Keep synchronized blocks small

---

### 7. What is `volatile` keyword?

* Ensures **visibility of changes across threads**
* Prevents **CPU cache issues**
* Does **not provide atomicity**

**Example**

```java
volatile boolean running = true;
```

---

### 8. Difference between `synchronized` and `volatile`?

| synchronized                   | volatile                |
| ------------------------------ | ----------------------- |
| Ensures atomicity + visibility | Ensures visibility only |
| Uses locking                   | No locking              |
| Slower                         | Faster                  |
| Thread-safe                    | Not fully thread-safe   |

---

### 9. What is race condition and atomic operation?

### Race Condition

* Multiple threads **modify shared data simultaneously**
* Result depends on execution order

**Example**

```java
count++; // not thread-safe
```

### Atomic Operation

* Operation completes **in a single step**
* Cannot be interrupted

**Example**

```java
AtomicInteger count = new AtomicInteger();
count.incrementAndGet();
```

---

## 9. Advanced Concurrency (6 Questions)
### 1️⃣ What is **ExecutorService**?

* ExecutorService is a **framework to manage threads** for you.
* You submit tasks → it handles **thread creation, reuse, and shutdown**.
* Better than creating threads manually.

**Example**

```java
ExecutorService executor = Executors.newFixedThreadPool(2);

executor.submit(() -> {
    System.out.println("Task running in thread pool");
});

executor.shutdown();
```

---

### 2️⃣ What are the **types of thread pools**?

* **Fixed Thread Pool** – fixed number of threads
* **Cached Thread Pool** – creates threads as needed
* **Single Thread Executor** – one thread only
* **Scheduled Thread Pool** – runs tasks after delay or periodically

**Example**

```java
Executors.newFixedThreadPool(5);
Executors.newCachedThreadPool();
Executors.newSingleThreadExecutor();
Executors.newScheduledThreadPool(2);
```

---

### 3️⃣ What is **Future** and **CompletableFuture**?

### **Future**

* Represents result of **async task**
* You must **block** using `get()`

```java
Future<Integer> future = executor.submit(() -> 10);
System.out.println(future.get()); // waits
```

### **CompletableFuture**

* Advanced async programming
* **Non-blocking**, supports chaining

```java
CompletableFuture
    .supplyAsync(() -> 10)
    .thenApply(n -> n * 2)
    .thenAccept(System.out::println);
```

---

### 4️⃣ What is **CountDownLatch**?

* Used to **wait until multiple threads finish**
* Main thread waits until count reaches zero

**Example**

```java
CountDownLatch latch = new CountDownLatch(3);

for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        System.out.println("Task done");
        latch.countDown();
    }).start();
}

latch.await(); // waits
System.out.println("All tasks completed");
```

---

### 5️⃣ What is **ReentrantLock**?

* A **manual locking mechanism**
* Same thread can lock multiple times
* More control than `synchronized`

**Example**

```java
ReentrantLock lock = new ReentrantLock();

lock.lock();
try {
    System.out.println("Critical section");
} finally {
    lock.unlock();
}
```

---

### 6️⃣ Difference between **ReentrantLock** and **synchronized**

| Feature       | synchronized | ReentrantLock |
| ------------- | ------------ | ------------- |
| Lock release  | Automatic    | Manual        |
| Try lock      | ❌ No         | ✅ Yes         |
| Fairness      | ❌ No         | ✅ Yes         |
| Interruptible | ❌ No         | ✅ Yes         |

**TryLock Example**

```java
if (lock.tryLock()) {
    try {
        System.out.println("Lock acquired");
    } finally {
        lock.unlock();
    }
}
```

---

## 10. JVM and Memory Management (8 Questions)
### 1. **What are the different memory areas in JVM?**

* **Heap**: Stores objects and arrays (runtime data).
* **Stack**: Stores method call frames and local variables.
* **Metaspace**: Stores class metadata.
* **Program Counter (PC) Register**: Keeps track of the current instruction.
* **Native Method Stack**: Stores data for native (non-Java) method calls.

### 2. **What is the difference between heap and stack?**

* **Heap**:

  * Dynamic memory allocation.
  * Used for objects and arrays.
  * Garbage collected.
* **Stack**:

  * Stores method frames and local variables.
  * Fast memory allocation (LIFO).
  * Automatically managed.

**Example**:

```java
public class StackHeapExample {
    public static void main(String[] args) {
        int x = 10;  // Stack
        MyObject obj = new MyObject();  // Heap
    }
}
class MyObject {
    int value;
}
```

### 3. **What is the difference between permgen and metaspace?**

* **PermGen (Pre-Java 8)**:

  * Fixed-size memory space for class metadata.
  * Can cause `OutOfMemoryError` if exceeded.
* **Metaspace (Java 8 and later)**:

  * Dynamically sized memory for class metadata.
  * Grows and shrinks based on system limits.

**Example (PermGen issue)**:

```java
// Java 7 and earlier may throw OutOfMemoryError due to PermGen space overflow.
```

### 4. **What is garbage collection?**

* Automatic process of reclaiming memory by removing unused objects.
* Prevents memory leaks and improves performance.

**Example**:

```java
public class GCExample {
    public static void main(String[] args) {
        MyObject obj = new MyObject();
        obj = null;  // Eligible for GC
        System.gc();  // Suggest JVM to run GC
    }
}
class MyObject {}
```

### 5. **What are the types of garbage collectors?**

* **Serial GC**: Single-threaded, simple, and slow.
* **Parallel GC**: Multi-threaded, good for multi-core systems.
* **CMS (Concurrent Mark-Sweep) GC**: Reduces GC pause times.
* **G1 (Garbage First) GC**: Aims for low pause times and high throughput.

**Example of G1 GC (in JVM args)**:

```bash
java -XX:+UseG1GC -jar yourapp.jar
```

### 6. **What is generational garbage collection?**

* Divides heap into generations: **Young** (new objects) and **Old** (long-lived objects).
* **Minor GC**: Cleans Young Generation.
* **Major GC**: Cleans Old Generation.

**Example**:

* Young Generation contains objects from the new `new MyObject()` calls.
* Old Generation holds objects that have survived multiple GCs.

### 7. **What is the difference between minor GC and major GC?**

* **Minor GC**:

  * Focuses on cleaning the Young Generation.
  * Faster, less costly.
* **Major GC (Full GC)**:

  * Cleans both Young and Old Generations.
  * Slower and more resource-intensive.

**Example**:

```bash
// Minor GC happens when Young Generation is full.
// Major GC happens when Old Generation needs cleanup.
```

### 8. **What is metaspace?**

* **Metaspace**: A memory area (introduced in Java 8) to store class metadata.
* Unlike PermGen, Metaspace grows dynamically and is only limited by the system’s memory.

**Example**:

```java
// Class metadata stored in Metaspace, no fixed size limits.
```

### 9. **What are GC roots?**

* Objects directly referenced by active threads, static fields, or JNI (Java Native Interface) references.
* These are the starting points for the garbage collection process to trace reachable objects.

**Example**:

* **Thread-local variables**, **static references**, and **active method calls** are GC roots.

```java
public class GCRootExample {
    static MyObject staticObj; // GC Root

    public static void main(String[] args) {
        staticObj = new MyObject(); // Reachable via static reference.
    }
}
class MyObject {}
```

## 11. Input/Output (I/O) (6 Questions)
### 1. **What are the different ways to read a file in Java?**

* **FileInputStream**: Reads binary data.
* **FileReader**: Reads character data (text files).
* **BufferedReader**: Efficient for reading text line by line.
* **Scanner**: Useful for reading text, with options for parsing data types.
* **NIO Files.readAllLines**: Reads all lines of a file into a list.

**Example**:

```java
// Using BufferedReader to read line by line
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
String line;
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
reader.close();
```

### 2. **What is the difference between InputStream and Reader?**

* **InputStream**:

  * Works with **binary data** (bytes).
  * Used for reading any type of file, including non-text files (images, audio).
* **Reader**:

  * Works with **character data** (chars).
  * Used for reading text files.

**Example**:

```java
// InputStream (for binary data)
FileInputStream inputStream = new FileInputStream("file.bin");

// Reader (for text data)
FileReader reader = new FileReader("file.txt");
```

### 3. **What is BufferedReader and BufferedWriter?**

* **BufferedReader**: Reads text efficiently using a buffer. It reduces the number of I/O operations by reading larger chunks at once.
* **BufferedWriter**: Writes text efficiently, buffering the data to minimize the number of write operations.

**Example**:

```java
// BufferedReader example
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
String line;
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
reader.close();

// BufferedWriter example
BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
writer.write("Hello, World!");
writer.close();
```

### 4. **How do you handle large files efficiently?**

* **BufferedReader/Writer**: Use for reading/writing in chunks instead of line-by-line for better performance.
* **FileInputStream/FileOutputStream**: Read/write in chunks of byte arrays.
* **Memory-mapped files (NIO)**: For extremely large files, memory-mapping allows file content to be mapped directly to memory.

**Example** (BufferedReader for large files):

```java
BufferedReader reader = new BufferedReader(new FileReader("largefile.txt"));
String line;
while ((line = reader.readLine()) != null) {
    // Process each line
}
reader.close();
```

### 5. **What is NIO in Java?**

* **NIO (New I/O)**: Introduced in Java 1.4, it provides more scalable and flexible I/O operations compared to traditional I/O.
* It includes features like **buffers**, **channels**, and **selectors** for asynchronous I/O operations.

**Example**:

```java
// Using NIO to read a file
Path path = Paths.get("file.txt");
List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
lines.forEach(System.out::println);
```

### 6. **What is the difference between IO and NIO?**

* **Traditional I/O (IO)**:

  * Blocking I/O: Operations are synchronous (thread waits for data).
  * Streams and Readers/Writers.
  * Suitable for simpler applications.

* **NIO**:

  * Non-blocking I/O: Allows asynchronous operations, useful for high-performance apps.
  * Uses **buffers**, **channels**, and **selectors**.
  * Supports memory-mapped files and file operations on a large scale.

**Example** (Blocking vs Non-blocking):

* **Blocking (IO)**:

  ```java
  // FileReader (Blocking)
  FileReader reader = new FileReader("file.txt");
  int data = reader.read();  // Blocks until data is read
  ```

* **Non-blocking (NIO)**:

  ```java
  // NIO Channel (Non-blocking)
  Path path = Paths.get("file.txt");
  try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      while (channel.read(buffer) > 0) {
          buffer.flip();
          while (buffer.hasRemaining()) {
              System.out.print((char) buffer.get());
          }
          buffer.clear();
      }
  }
  ```

### 7. **When would you use NIO over traditional I/O?**

* **Use NIO** when you need:

  * **Non-blocking operations**: Allows multiple I/O operations without blocking the main thread.
  * **Large file handling**: Memory-mapped files allow direct interaction with file content.
  * **Scalability**: For server applications or high-performance apps (e.g., networking, handling many connections).
* **Use traditional I/O** for simpler, sequential file operations where performance isn't a concern.

**Example (when to use NIO)**:

* **File Handling**: If you need to process large files or perform operations asynchronously (e.g., serving large files over HTTP), NIO is preferred.
* **Example with Server**:

  ```java
  // NIO for non-blocking server socket
  ServerSocketChannel serverChannel = ServerSocketChannel.open();
  serverChannel.configureBlocking(false);  // Non-blocking mode
  ```

## 12. Generics (6 Questions)
### 1. **What are generics in Java?**

* Generics allow you to write **type-safe** and **reusable** code.
* You define the type once and use it consistently.
* Errors are caught at **compile time**, not runtime.

**Example:**

```java
List<String> names = new ArrayList<>();
names.add("Alice");   // OK
// names.add(10);     // Compile-time error
```

---

### 2. **Why were generics introduced?**

* To **avoid ClassCastException** at runtime.
* To remove unnecessary **type casting**.
* To make code **cleaner, safer, and reusable**.

**Before Generics:**

```java
List list = new ArrayList();
list.add("Java");
String s = (String) list.get(0); // Manual casting
```

**With Generics:**

```java
List<String> list = new ArrayList<>();
String s = list.get(0); // No casting needed
```

---

### 3. **What is type erasure?**

* Generics exist **only at compile time**.
* At runtime, generic type information is **removed**.
* JVM works with **raw types**.

**Example:**

```java
List<String> list = new ArrayList<>();
```

➡ At runtime, JVM sees it as:

```java
List list = new ArrayList();
```

---

### 4. **What is the difference between `<? extends T>` and `<? super T>`?**

* `<? extends T>`:

  * Used when you **only read** from a collection.
  * Upper-bounded wildcard.
* `<? super T>`:

  * Used when you **only write** to a collection.
  * Lower-bounded wildcard.

**Example:**

```java
// extends → read only
List<? extends Number> list1 = List.of(1, 2.5);
// list1.add(10); // ❌ Not allowed

// super → write allowed
List<? super Integer> list2 = new ArrayList<>();
list2.add(10);  // ✅ Allowed
```

---

### 5. **What is PECS principle?**

* **PECS = Producer Extends, Consumer Super**
* If a collection **produces data**, use `extends`.
* If a collection **consumes data**, use `super`.

**Example:**

```java
// Producer
void read(List<? extends Number> list) {
    Number n = list.get(0);
}

// Consumer
void write(List<? super Integer> list) {
    list.add(10);
}
```

---

### 6. **What are the limitations of generics?**

* Cannot use **primitive types** (`int`, `double`).
* Cannot create **generic objects directly**.
* No **runtime type checking** (due to type erasure).
* Cannot use generics with **static variables**.

**Examples:**

```java
// ❌ Not allowed
List<int> list;

// ❌ Cannot do this
T obj = new T();

// ❌ Static generic variable
static T value;
```

## 13. Annotations and Reflection (6 Questions)
### 1. **What are annotations in Java?**

* Annotations are metadata that provide additional information to the compiler or runtime.
* They don’t change the program logic but can be used for code analysis, documentation, and runtime processing.
* Example:

  ```java
  @Override
  public String toString() {
      return "Hello, World!";
  }
  ```

### 2. **What are built-in annotations?**

* Built-in annotations are predefined in Java, like:

  * `@Override`: Indicates a method is overriding a superclass method.
  * `@Deprecated`: Marks a method or class as obsolete.
  * `@SuppressWarnings`: Tells the compiler to suppress specific warnings.
  * `@FunctionalInterface`: Marks an interface as functional (has exactly one abstract method).

  Example:

  ```java
  @Deprecated
  public void oldMethod() { }
  ```

### 3. **How do you create custom annotations?**

* You create custom annotations by defining an interface with the `@interface` keyword.
* Example:

  ```java
  @interface MyCustomAnnotation {
      String value() default "default value";
  }
  ```

### 4. **What is retention policy?**

* Retention policy determines when annotations are available:

  * `SOURCE`: Only available in the source code (not compiled).
  * `CLASS`: Available in compiled class files but not at runtime.
  * `RUNTIME`: Available at runtime (can be accessed via reflection).

  Example:

  ```java
  @Retention(RetentionPolicy.RUNTIME)
  public @interface MyAnnotation { }
  ```

### 5. **What is the difference between @Override and @Overload?**

* **`@Override`**: Indicates a method is overriding a method from a superclass or interface.

  * Example:

    ```java
    @Override
    public void run() { }
    ```
* **`@Overload`**: There’s no `@Overload` annotation in Java; method overloading is determined by the method signature (name and parameters).

### 6. **What is reflection in Java?**

* Reflection allows you to inspect or modify the behavior of classes, methods, fields, etc., at runtime.
* It’s part of the `java.lang.reflect` package.
* Example:

  ```java
  Class<?> clazz = MyClass.class;
  Method method = clazz.getMethod("myMethod");
  method.invoke(new MyClass());
  ```

### 7. **When should you use reflection?**

* Use reflection when you need dynamic behavior, like:

  * Accessing private members of a class.
  * Instantiating objects or invoking methods dynamically.
  * Frameworks like Spring use reflection extensively.

  Example:

  ```java
  Method method = MyClass.class.getDeclaredMethod("somePrivateMethod");
  method.setAccessible(true);
  method.invoke(myObject);
  ```

### 8. **What are the performance implications of reflection?**

* Reflection is slower than direct method calls because it involves inspecting metadata and resolving objects at runtime.
* Avoid using reflection in performance-critical code.

  Example (slower than direct call):

  ```java
  Method method = MyClass.class.getMethod("methodName");
  method.invoke(myObject);
  ```

### 9. **What are the security implications of reflection?**

* Reflection can bypass normal access control (like private fields/methods), which could lead to security vulnerabilities.
* It's important to control access carefully, especially when accepting reflection-based input from untrusted sources.

### 10. **How do you handle exceptions in reflection?**

* Reflection can throw several exceptions, like `NoSuchMethodException`, `IllegalAccessException`, and `InvocationTargetException`.
* Handle these exceptions using `try-catch` blocks.

  Example:

  ```java
  try {
      Method method = clazz.getMethod("methodName");
      method.invoke(myObject);
  } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
  }
  ```

## 14. Lambda Expressions and Streams API (8 Questions)
### 1. **What are lambda expressions?**

* Lambda expressions allow you to pass behavior as a parameter to methods or store it in variables.
* They provide a more concise and readable way to write code that uses functional interfaces.

  **Example:**

  ```java
  // Lambda expression to implement Runnable
  Runnable r = () -> System.out.println("Hello from Lambda!");
  r.run();
  ```

### 2. **What are functional interfaces?**

* A **functional interface** is an interface with exactly one abstract method. It can have multiple default or static methods.
* They're the target types for lambda expressions.

  **Example:**

  ```java
  @FunctionalInterface
  public interface MyFunctionalInterface {
      void myMethod(); // Only one abstract method
  }

  MyFunctionalInterface mfi = () -> System.out.println("Method implemented!");
  mfi.myMethod();
  ```

### 3. **What are method references?**

* Method references provide a shorthand for calling methods using the syntax `ClassName::methodName`.
* It's a more concise way to use existing methods as lambdas.

  **Example:**

  ```java
  // Using method reference instead of lambda
  List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
  names.forEach(System.out::println); // Equivalent to (name) -> System.out.println(name)
  ```

### 4. **What is the difference between lambda and anonymous class?**

* **Lambda expressions** are more concise and easier to read. They are used primarily with functional interfaces.
* **Anonymous classes** are more verbose, requiring you to define a full class and override methods.

  **Example (Lambda vs Anonymous):**

  * **Lambda:**

    ```java
    Runnable r = () -> System.out.println("Runnable using Lambda");
    ```
  * **Anonymous Class:**

    ```java
    Runnable r = new Runnable() {
        public void run() {
            System.out.println("Runnable using Anonymous Class");
        }
    };
    ```

### 5. **What is Stream API?**

* The **Stream API** provides a powerful way to process sequences of elements (like collections) in a functional style.
* Streams allow operations like filtering, mapping, and reducing data without modifying the original collection.

  **Example:**

  ```java
  List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
  names.stream()
       .filter(name -> name.startsWith("A"))
       .forEach(System.out::println); // Outputs "Alice"
  ```

### 6. **What is the difference between Collection and Stream?**

* **Collection**: Represents a data structure like `List`, `Set`, etc., and is typically used for storing and manipulating data.
* **Stream**: Represents a sequence of elements that can be processed using functional-style operations (like `map`, `filter`, etc.). Streams don't modify the original data structure but rather work on the data in a pipeline.

  **Example:**

  * **Collection**:

    ```java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    ```
  * **Stream**:

    ```java
    numbers.stream().map(n -> n * 2).forEach(System.out::println); // Outputs 2, 4, 6, 8, 10
    ```

### 7. **What are intermediate and terminal operations?**

* **Intermediate operations**: These return a new stream and can be chained (e.g., `map`, `filter`).
* **Terminal operations**: These trigger the processing of the stream and return a result (e.g., `forEach`, `collect`, `reduce`).

  **Example:**

  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
  // Intermediate operation: filter and map
  numbers.stream()
         .filter(n -> n % 2 == 0) // Even numbers
         .map(n -> n * 2)          // Double them
         .forEach(System.out::println); // Terminal operation: forEach
  ```

### 8. **What is the difference between `map()` and `flatMap()`?**

* **`map()`**: Transforms each element of the stream into another form (e.g., mapping each string to its length).
* **`flatMap()`**: Flattens nested structures (like a `Stream<List<T>>` into a `Stream<T>`).

  **Example:**

  * **map()**:

    ```java
    List<String> words = Arrays.asList("apple", "banana", "cherry");
    words.stream().map(String::toUpperCase).forEach(System.out::println); // Outputs "APPLE", "BANANA", "CHERRY"
    ```
  * **flatMap()**:

    ```java
    List<List<String>> listOfLists = Arrays.asList(
        Arrays.asList("apple", "banana"),
        Arrays.asList("cherry", "date")
    );
    listOfLists.stream().flatMap(List::stream).forEach(System.out::println); // Outputs "apple", "banana", "cherry", "date"
    ```

### 9. **What is the Optional class?**

* The **`Optional`** class is used to represent a value that may or may not be present (like a container for a nullable value).
* It helps avoid `NullPointerExceptions` by explicitly checking for the presence or absence of a value.

  **Example:**

  ```java
  Optional<String> name = Optional.ofNullable(null);
  System.out.println(name.orElse("Unknown")); // Outputs "Unknown"
  ```

## 15. JDBC (6 Questions)
### 1. **What is JDBC?**

* JDBC stands for **Java Database Connectivity**.
* It’s an API that allows Java applications to **connect and interact with databases**.
* Used to execute SQL queries like `SELECT`, `INSERT`, `UPDATE`, `DELETE`.

**Example:**

```java
Connection con = DriverManager.getConnection(url, user, password);
```

---

### 2. **What are the steps to connect to a database using JDBC?**

* Load the JDBC driver
* Create a database connection
* Create a statement
* Execute SQL query
* Process the result
* Close resources

**Example:**

```java
Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb", "root", "pass");

Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

con.close();
```

---

### 3. **What is the difference between Statement and PreparedStatement?**

* **Statement**

  * Used for simple SQL queries
  * Vulnerable to SQL injection
* **PreparedStatement**

  * Precompiled SQL
  * Safer and faster
  * Prevents SQL injection

**Example:**

```java
PreparedStatement ps =
    con.prepareStatement("SELECT * FROM users WHERE id = ?");
ps.setInt(1, 1);
ResultSet rs = ps.executeQuery();
```

---

### 4. **What is connection pooling?**

* Connection pooling **reuses database connections** instead of creating new ones every time.
* Improves performance and scalability.
* Common tools: **HikariCP**, **Apache DBCP**, **C3P0**.

**Example (concept):**

```java
DataSource ds = dataSource;
Connection con = ds.getConnection();
```

---

### 5. **What is SQL injection and how to prevent it?**

* SQL injection is a security attack where malicious SQL is inserted via user input.
* It can expose or delete database data.
* Prevent it using **PreparedStatement** and input validation.

**Bad Example (Vulnerable):**

```java
String query = "SELECT * FROM users WHERE name = '" + name + "'";
```

**Good Example (Safe):**

```java
PreparedStatement ps =
    con.prepareStatement("SELECT * FROM users WHERE name = ?");
ps.setString(1, name);
```

---

### 6. **What is transaction management in JDBC?**

* Transaction management ensures **all operations succeed or none do**.
* Controlled using `commit()` and `rollback()`.
* Auto-commit is disabled to manage transactions manually.

**Example:**

```java
con.setAutoCommit(false);

try {
    stmt.executeUpdate("INSERT INTO users VALUES (1, 'John')");
    stmt.executeUpdate("INSERT INTO accounts VALUES (1, 1000)");
    con.commit();
} catch (Exception e) {
    con.rollback();
}
```

---

## 16. Design Patterns (8 Questions)
### 1. **What are design patterns?**

* Design patterns are **proven solutions to common software design problems**.
* They improve **code readability, reusability, and maintainability**.
* They are **templates**, not ready-made code.

**Example (concept):**

```java
// Using a pattern like Singleton or Factory to solve common problems
```

---

### 2. **What is Singleton pattern?**

* Ensures **only one instance** of a class exists.
* Provides a **global access point** to that instance.
* Common use cases: logging, configuration, database connection.

**Example:**

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

### 3. **How do you implement thread-safe Singleton?**

* Use **synchronized method**, **double-checked locking**, or **enum**.
* Best and simplest approach: **Enum Singleton**.

**Enum Example (Best):**

```java
enum Singleton {
    INSTANCE;
}
```

**Double-Checked Locking:**

```java
class Singleton {
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
```

---

### 4. **What is Factory pattern?**

* Factory pattern is used to **create objects without exposing creation logic**.
* It returns objects based on input or conditions.
* Promotes loose coupling.

**Example:**

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        if ("circle".equals(type)) return new Circle();
        return null;
    }
}
```

---

### 5. **What is Observer pattern?**

* Observer pattern defines a **one-to-many dependency**.
* When one object changes state, all observers are notified.
* Common in event handling and messaging systems.

**Example:**

```java
interface Observer {
    void update();
}

class User implements Observer {
    public void update() {
        System.out.println("Notified!");
    }
}
```

---

### 6. **What is Strategy pattern?**

* Strategy pattern allows **changing behavior at runtime**.
* Uses composition instead of inheritance.
* Each strategy is a separate class.

**Example:**

```java
interface PaymentStrategy {
    void pay();
}

class CreditCardPayment implements PaymentStrategy {
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}
```

---

### 7. **What is Adapter pattern?**

* Adapter pattern allows **incompatible interfaces to work together**.
* Acts as a bridge between old and new systems.

**Example:**

```java
interface Charger {
    void charge();
}

class OldCharger {
    void oldCharge() {
        System.out.println("Charging with old charger");
    }
}

class ChargerAdapter implements Charger {
    OldCharger oldCharger = new OldCharger();

    public void charge() {
        oldCharger.oldCharge();
    }
}
```

---

### 8. **What is Decorator pattern?**

* Decorator pattern adds **new behavior to objects dynamically**.
* Avoids subclass explosion.
* Common in UI components and I/O streams.

**Example:**

```java
interface Coffee {
    String getDescription();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;

    MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
}
```

---

## 17. Spring Framework (8 Questions)
### 1. **What is Spring Framework?**

* A comprehensive, lightweight framework for Java that simplifies development, especially for enterprise applications.
* Provides infrastructure support for developing Java applications (e.g., transaction management, security, etc.).
* Allows you to build loosely-coupled, scalable, and testable applications using concepts like IoC and DI.

### 2. **What is Inversion of Control (IoC)?**

* A design principle where the control of object creation and dependencies is shifted from the program to the framework.
* Spring manages object creation and dependencies, freeing developers from manual handling.
* Example:

  ```java
  @Component
  public class MyService {
      private final MyRepository repository;
      
      @Autowired
      public MyService(MyRepository repository) {
          this.repository = repository;
      }
  }
  ```

### 3. **What is Dependency Injection?**

* A technique for achieving IoC by passing dependencies (objects) into a class rather than creating them inside.
* Spring automatically injects dependencies into beans (via constructor, setter, or field injection).
* Example:

  ```java
  @Component
  public class MyService {
      private final MyRepository repository;

      @Autowired
      public MyService(MyRepository repository) {
          this.repository = repository;
      }
  }
  ```

### 4. **What is the difference between BeanFactory and ApplicationContext?**

* **BeanFactory**: Basic container for managing beans, less feature-rich.
* **ApplicationContext**: Extends BeanFactory with more advanced features like event propagation, AOP support, etc. It's the preferred container.
* Example:

  ```java
  ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
  ```

### 5. **What are Spring beans?**

* Objects that are managed by the Spring IoC container.
* Beans are created, configured, and managed by Spring, typically defined with `@Component` or XML configuration.
* Example:

  ```java
  @Component
  public class MyBean {
      // Bean definition
  }
  ```

### 6. **What is Spring Boot?**

* A framework built on top of the Spring Framework, designed to simplify the development of Spring applications.
* Provides production-ready defaults, auto-configuration, embedded servers (like Tomcat), and minimal configuration.
* Example:

  ```java
  @SpringBootApplication
  public class MyApp {
      public static void main(String[] args) {
          SpringApplication.run(MyApp.class, args);
      }
  }
  ```

### 7. **What is auto-configuration in Spring Boot?**

* Automatically configures application components based on the classpath and project settings.
* Developers can focus on writing business logic, as Spring Boot handles configurations for you.
* Example:

  * If you have `spring-boot-starter-web` in your dependencies, Spring Boot auto-configures a web server.

### 8. **What is @SpringBootApplication annotation?**

* A convenience annotation that combines:

  * `@Configuration`: Marks the class as a configuration class.
  * `@EnableAutoConfiguration`: Enables auto-configuration.
  * `@ComponentScan`: Scans for components, configurations, and services.
* Example:

  ```java
  @SpringBootApplication
  public class MyApp {
      public static void main(String[] args) {
          SpringApplication.run(MyApp.class, args);
      }
  }
  ```

### 9. **What is the difference between @Component, @Service, and @Repository?**

* `@Component`: A generic annotation for Spring beans.
* `@Service`: A specialization of `@Component`, used for service classes.
* `@Repository`: A specialization of `@Component`, used for data access classes.
* Example:

  ```java
  @Service
  public class MyService {
      // Business logic
  }

  @Repository
  public class MyRepository {
      // Data access logic
  }
  ```

### 10. **What is @Autowired annotation?**

* Used to automatically inject dependencies into Spring beans.
* Can be used on constructors, fields, or setter methods.
* Example:

  ```java
  @Autowired
  private MyRepository repository;
  ```

### 11. **What is @Qualifier annotation?**

* Used to specify which bean to inject when there are multiple candidates for injection.
* It helps resolve ambiguity when more than one bean of the same type exists.
* Example:

  ```java
  @Autowired
  @Qualifier("mySpecificBean")
  private MyBean myBean;
  ```

### 12. **What is ApplicationContext?**

* The central interface for accessing Spring’s IoC container.
* It provides advanced features like event handling, bean post-processing, and more.
* Example:

  ```java
  ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
  MyBean bean = context.getBean(MyBean.class);
  ```

## 18. RESTful Services (6 Questions)
### 1. **What are RESTful web services?**

* RESTful web services are APIs that follow REST (Representational State Transfer) principles.
* They allow clients to communicate with servers using standard HTTP methods.
* Data is usually exchanged in JSON format.
* Example (Spring Boot):

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return new User(id, "John");
    }
}
```

---

### 2. **What are the principles of REST?**

* **Stateless** → Each request contains all needed information.
* **Client–Server** → Clear separation between client and server.
* **Uniform Interface** → Uses standard HTTP methods.
* **Resource-Based** → Everything is treated as a resource (URI-based).
* **Cacheable** → Responses can be cached for performance.
* Example:

```
GET /users/1
```

---

### 3. **What are HTTP methods and their usage?**

* **GET** → Retrieve data
* **POST** → Create new data
* **PUT** → Update existing data (full update)
* **PATCH** → Partial update
* **DELETE** → Remove data
* Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return user;
}
```

---

### 4. **What is the difference between PUT and POST?**

* **POST**

  * Creates a new resource
  * Not idempotent
* **PUT**

  * Updates or replaces an existing resource
  * Idempotent
* Example:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) { }

@PutMapping("/users/{id}")
public User updateUser(@PathVariable int id, @RequestBody User user) { }
```

---

### 5. **What is idempotency in REST?**

* An operation is idempotent if multiple identical requests produce the same result.
* **Idempotent methods**: GET, PUT, DELETE
* **Non-idempotent method**: POST
* Example:

```java
DELETE /users/5   // Calling multiple times gives same result
```

---

### 6. **What are HTTP status codes?**

* Standard codes returned by the server to indicate request result.
* Common categories:

  * **2xx** → Success (200 OK, 201 Created)
  * **4xx** → Client errors (400 Bad Request, 404 Not Found)
  * **5xx** → Server errors (500 Internal Server Error)
* Example:

```java
@GetMapping("/users/{id}")
public ResponseEntity<User> getUser(@PathVariable int id) {
    return ResponseEntity.ok(new User(id, "John"));
}
```

## 19. Microservices (6 Questions)
### 1. **What are microservices?**

* An architectural style where an application is split into small, independent services.
* Each service focuses on one business capability.
* Services communicate using lightweight protocols like HTTP/REST.
* Example (simple service):

```java
@RestController
@RequestMapping("/orders")
public class OrderService {

    @GetMapping("/{id}")
    public String getOrder(@PathVariable int id) {
        return "Order " + id;
    }
}
```

---

### 2. **What are the advantages of microservices?**

* **Independent deployment** → Update one service without affecting others.
* **Scalability** → Scale only the services under heavy load.
* **Fault isolation** → Failure in one service doesn’t crash the whole system.
* **Technology flexibility** → Different services can use different tech stacks.
* Real-life example: Scale only the **payment service** during sales.

---

### 3. **What are the challenges of microservices?**

* **Complexity** → Managing many services is harder.
* **Network latency** → Services communicate over the network.
* **Data consistency** → Each service has its own database.
* **Monitoring & logging** → Needs centralized tools.
* Example challenge:

```
Service A → HTTP call → Service B (may fail)
```

---

### 4. **What is service discovery?**

* A mechanism where services find and communicate with each other dynamically.
* Prevents hardcoding service IPs and ports.
* Common tools: **Eureka, Consul**
* Example (Eureka client):

```java
@EnableEurekaClient
@SpringBootApplication
public class OrderServiceApp { }
```

---

### 5. **What is API Gateway?**

* A single entry point for all client requests.
* Routes requests to appropriate microservices.
* Handles cross-cutting concerns like security, logging, and rate limiting.
* Example:

```
Client → API Gateway → Order Service / User Service
```

---

### 6. **What is circuit breaker pattern?**

* A fault-tolerance pattern that prevents cascading failures.
* Stops calling a failing service temporarily.
* Common library: **Resilience4j**
* Example:

```java
@CircuitBreaker(name = "orderService", fallbackMethod = "fallback")
public String callOrderService() {
    return restTemplate.getForObject("http://ORDER-SERVICE/orders", String.class);
}

public String fallback(Exception e) {
    return "Service unavailable";
}
```

## 20. Performance Tuning (8 Questions)
### 1. **How do you identify performance bottlenecks?**

* **Profiling:** Use tools like JProfiler, VisualVM, or YourKit to track CPU usage, memory consumption, and thread activity.
* **Log Analysis:** Look for slow queries, high memory usage, or long-running methods by analyzing logs.
* **JVM Metrics:** Monitor GC pauses, heap memory usage, and thread activity using `jstat` or `GC logs`.
* **Example:** Using VisualVM to inspect method execution times and memory usage.

---

### 2. **What are common performance issues in Java applications?**

* **Memory Leaks:** Unused objects not being garbage collected, causing increased memory usage.
* **Inefficient I/O Operations:** Reading/writing large amounts of data without buffering or batching.
* **Thread Contention:** Too many threads waiting on shared resources.
* **Excessive Garbage Collection:** Frequent GC pauses affecting response times.
* **Example:** Using `System.gc()` unnecessarily can trigger full GC cycles.

---

### 3. **What is connection pooling and why is it important?**

* **Connection Pooling:** Reusing database or network connections instead of opening a new one each time.
* **Why Important:** Reduces overhead of creating and closing connections, improving performance and scalability.
* **Example:** Using Apache DBCP or HikariCP for managing DB connections.

  ```java
  HikariDataSource ds = new HikariDataSource();
  ds.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
  ds.setUsername("user");
  ds.setPassword("password");
  ```

---

### 4. **What is caching and when should you use it?**

* **Caching:** Storing frequently accessed data in memory to avoid repeated computations or database hits.
* **When to Use:** For read-heavy applications with data that doesn't change frequently (e.g., user sessions, configuration data).
* **Example:** Using `HashMap` for caching data:

  ```java
  Map<String, String> cache = new HashMap<>();
  cache.put("user1", "data1");
  ```

---

### 5. **What are important JVM parameters?**

* **-Xms and -Xmx:** Set initial and maximum heap sizes.

  ```bash
  java -Xms512m -Xmx2g MyApp
  ```
* **-XX:+UseG1GC:** Use G1 Garbage Collector, good for applications with large heaps.
* **-XX:MetaspaceSize and -XX:MaxMetaspaceSize:** Control the metaspace (for class metadata).
* **-XX:+PrintGCDetails:** Log detailed GC information to monitor performance.

  ```bash
  java -XX:+PrintGCDetails -Xms512m -Xmx2g MyApp
  ```

### 6. **How do you tune heap size?**

* **Adjusting -Xms and -Xmx:** Set the initial (-Xms) and maximum (-Xmx) heap sizes based on your application’s memory usage patterns.

  * **Xms:** Initial heap size at JVM startup.
  * **Xmx:** Maximum heap size the JVM can grow to.
* **Start small, then increase:** Begin with reasonable values based on expected memory usage and adjust based on profiling results.
* **Example:** Tuning heap size for a high-load application:

  ```bash
  java -Xms1g -Xmx4g MyApp
  ```
* **Monitor with GC logs** to fine-tune heap sizes further.

---

### 7. **What is the difference between -Xms and -Xmx?**

* **-Xms:** Initial heap size when the JVM starts. It determines how much memory the JVM allocates at the beginning.
* **-Xmx:** Maximum heap size the JVM can expand to. This is the upper limit for the heap memory.
* **Key Difference:**

  * **-Xms:** Set the starting heap size.
  * **-Xmx:** Set the maximum heap limit.
* **Example:** Set a starting heap of 512MB and max heap of 2GB:

  ```bash
  java -Xms512m -Xmx2g MyApp
  ```

---

### 8. **How do you analyze heap dumps?**

* **Heap Dumps:** A snapshot of the JVM heap at a particular point in time, useful for diagnosing memory issues.
* **Tools to Analyze:**

  * **Eclipse MAT (Memory Analyzer Tool):** Open the heap dump to analyze object retention, memory leaks, and garbage collection.
  * **JVisualVM:** Offers heap dump analysis, including object counts and memory consumption.
  * **Example Command:** Triggering a heap dump on OutOfMemoryError.

    ```bash
    java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heapdump.hprof MyApp
    ```
  * **Look for:**

    * Dominant objects in memory.
    * Retained memory (objects that aren’t being garbage collected).
    * Memory leaks.

---

### 9. **What is JIT compilation?**

* **JIT (Just-In-Time) Compilation:** A feature where the JVM compiles bytecode into native machine code *at runtime*, improving performance by optimizing frequently used code paths.
* **How it Works:** The JVM analyzes code execution patterns and compiles "hot spots" (frequently executed methods) into native code to reduce the overhead of interpreting bytecode.
* **Example:**

  * JIT happens automatically; no need for explicit commands.
  * However, you can control JIT behavior with `-XX:CompileThreshold` to adjust when methods are compiled.

  ```bash
  java -XX:CompileThreshold=1000 MyApp
  ```
* **Benefit:** Faster execution, especially for long-running applications.

---

## 21. Modern Java Features (4 Questions)
### 1. What are the new features in **Java 8**?

* Biggest shift: **functional programming**
* **Lambdas** reduce boilerplate code
* **Streams API** for clean data processing
* **Default methods** in interfaces
* New **Date & Time API**
* `Optional` helps avoid null checks

**Example (Lambda + Stream):**

```java
List<String> names = List.of("Ram", "Sam", "Amit");

names.stream()
     .filter(n -> n.startsWith("A"))
     .forEach(System.out::println);
```

---

### 2. What are the new features in **Java 11**?

* It’s an **LTS version**
* New **String utility methods**
* Standard **HTTP Client API**
* `var` support in lambda parameters
* Removed old Java EE modules from JDK

**Example (String utilities):**

```java
String s = "  Hello  ";

System.out.println(s.isBlank());  // false
System.out.println(s.strip());    // "Hello"
```

---

### 3. What are the new features in **Java 17**?

* Another **LTS version**, widely used today
* **Sealed classes** for controlled inheritance
* **Pattern matching for instanceof**
* Strong encapsulation of internal APIs
* Performance and security improvements

**Example (Pattern matching):**

```java
if (obj instanceof String str) {
    System.out.println(str.toUpperCase());
}
```

**Example (Sealed class):**

```java
sealed class Shape permits Circle, Square {}

final class Circle extends Shape {}
final class Square extends Shape {}
```

---

### 4. What is the Java release cycle and **LTS versions**?

* Java follows a **6-month release cycle**
* New versions in **March and September**
* **LTS versions** are meant for production use
* Companies usually skip non-LTS versions

**Popular LTS versions:**

* Java 8
* Java 11
* Java 17
* Java 21

👉 *“Non-LTS for features, LTS for stability.”*

---

### 5. How do you migrate an application from **Java 8 to Java 17**?

* First, **upgrade JDK and build tools**
* Upgrade **third-party libraries**
* Fix **removed Java EE APIs**
* Resolve **module and reflection issues**
* Run tests and verify performance

---

### Practical migration steps

### 1️⃣ Update compiler version

**Maven example:**

```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

---

### 2️⃣ Fix removed APIs (Java EE)

* JAXB, JAX-WS not in JDK anymore
* Add explicit dependencies

```xml
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
</dependency>
```

---

### 3️⃣ Handle module access issues

* Java 17 blocks internal APIs
* Avoid `sun.*` packages
* Temporary JVM option if needed:

```bash
--add-opens java.base/java.lang=ALL-UNNAMED
```

---

### 4️⃣ Modernize code safely

**Before (Java 8):**

```java
if (obj instanceof String) {
    String s = (String) obj;
}
```

**After (Java 17):**

```java
if (obj instanceof String s) {
    System.out.println(s);
}
```

---


## Cloud and Containerization
### 1. What is **containerization**?

* Containerization means **packing app + dependencies together**
* Runs the same way in dev, test, and production
* Lightweight compared to virtual machines
* Uses the host OS kernel

**Example (idea):**

```text
Java App + JDK + Config → One container → Runs anywhere
```

---

### 2. What is **Docker**?

* Docker is a **tool to create and run containers**
* Uses **Dockerfile** to define app setup
* Makes deployment fast and consistent
* Very common with Java microservices

**Example (Dockerfile for Java app):**

```dockerfile
FROM openjdk:17
COPY app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### 3. What is **Kubernetes**?

* Kubernetes is a **container orchestration platform**
* Manages deployment, scaling, and self-healing
* Automatically restarts failed containers
* Used when you have **many containers**

**Example (Pod idea):**

```yaml
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: app
    image: my-java-app
```

---

### 4. What is **cloud computing**?

* Cloud computing means **using servers over the internet**
* No need to manage physical hardware
* Pay only for what you use
* Examples: AWS, Azure, GCP

**Simple example:**

```text
Run Java app on AWS EC2 instead of local server
```

---

### 5. What is a **distributed system**?

* A system where **multiple services run on different machines**
* They communicate over the network
* Improves scalability and fault tolerance
* Microservices are distributed systems

**Example:**

```text
User Service → Order Service → Payment Service
```

---

### 6. What is **load balancing**?

* Load balancing **distributes traffic across servers**
* Prevents one server from getting overloaded
* Improves performance and availability
* Can be hardware or software based

**Example:**

```text
Client → Load Balancer → Server1 / Server2 / Server3
```

---

### 7. What are **caching strategies**?

* Caching stores **frequently used data in fast memory**
* Reduces database calls
* Improves response time
* Common types: in-memory, distributed, HTTP cache

**Example (simple Java cache):**

```java
Map<String, String> cache = new HashMap<>();

cache.put("user1", "Pintu");
System.out.println(cache.get("user1"));
```
---
