# Java Interview Question Bank
*Top Questions Frequently Asked in Real-World Technical Interviews*

### 1. Basic Concepts (6 Questions)
## 1. What is Java and what are its key features?

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

## 2. Explain the difference between JDK, JRE, and JVM

* **JVM** runs Java bytecode and makes Java platform-independent.
* **JRE** = JVM + core libraries → used to **run** Java programs.
* **JDK** = JRE + development tools → used to **develop** Java programs.
* In short: **JDK ⊃ JRE ⊃ JVM**.

```text
JDK → JRE → JVM
```

---

## 3. What are the main principles of Object-Oriented Programming?

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

## 4. What is polymorphism? Explain with examples.

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

## 5. What is encapsulation and how is it implemented in Java?

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

## 6. What is inheritance and what are its types?

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

## 7. What is an abstract class?

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

### 2. Data Types and Variables (8 Questions)
## 1. What are primitive data types in Java?

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

## 2. What is the difference between primitive and reference types?

* **Primitive types** store values directly.
* **Reference types** store memory addresses of objects.
* Primitives cannot be `null`, references can be.

```java
int x = 10;              // primitive
String name = "Java";   // reference
```

---

## 3. What is autoboxing and unboxing?

* **Autoboxing**: converting primitive → wrapper object.
* **Unboxing**: converting wrapper → primitive.
* Happens automatically in Java.

```java
Integer a = 10;  // autoboxing
int b = a;       // unboxing
```

---

## 4. What is the difference between `==` and `equals()` method?

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

## 5. What is the difference between String, StringBuilder, and StringBuffer?

* **String** is immutable.
* **StringBuilder** is mutable and **not thread-safe** (fast).
* **StringBuffer** is mutable and **thread-safe** (slower).

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" Java");
System.out.println(sb);
```

---

## 6. Why are strings immutable in Java?

* Improves **security** (used in passwords, URLs).
* Enables **string pooling** for memory efficiency.
* Makes strings **thread-safe**.

```java
String s = "Java";
s.concat(" World"); // creates new object
System.out.println(s); // Java
```

---

## 7. What is string pooling?

* String literals are stored in a **special memory area called String Pool**.
* Same literal shares the same object to save memory.
* `new String()` always creates a new object.

```java
String a = "Java";
String b = "Java";
System.out.println(a == b); // true
```

---

## 8. What is the difference between final, finally, and finalize?

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


### 3. Classes and Objects (7 Questions)
## 1. What is a constructor in Java?

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

## 2. What is constructor chaining?

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

## 3. What is the difference between `this` and `super` keywords?

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

## 4. What is method overloading?

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

## 5. What is method overriding?

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

## 6. What is the difference between overloading and overriding?

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

## 7. What is dynamic method dispatch?

* Dynamic method dispatch is **runtime method resolution**.
* Parent reference points to child object.
* Ensures **runtime polymorphism**.

```java
Animal a = new Dog();
a.sound(); // Dog barks
```

---

### 4. Inheritance (6 Questions)
## **1. Why doesn't Java support multiple inheritance?**

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

## **2. What is the diamond problem?**

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

## **3. How does Java solve the diamond problem?**

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

## **4. Can you override static methods?**

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

## **5. What is covariant return type?**

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

## **6. What is the difference between IS-A and HAS-A relationship?**

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

### 5. Interfaces and Abstract Classes (8 Questions)
## **1. What is an interface in Java?**

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

## **2. What is an abstract class?**

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

## **3. What is the difference between interface and abstract class?**

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

## **4. What are default methods in interfaces?**

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

## **5. What are static methods in interfaces?**

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

## **6. What is a marker interface?**

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

## **7. What is a functional interface?**

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

## **8. Can an interface extend another interface?**

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

### 6. Exception Handling (8 Questions)
## **1. What is an exception in Java?**

* An exception is a **runtime problem** that **interrupts normal program flow**.
* It happens when something unexpected occurs, like dividing by zero or file not found.

**Example:**

```java
int a = 10 / 0;  // ArithmeticException
```

---

## **2. What is the exception hierarchy in Java?**

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

## **3. What are checked and unchecked exceptions?**

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

## **4. Difference between throw and throws?**

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

## **5. What is try-catch-finally block?**

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

## **6. What is try-with-resources?**

* Automatically **closes resources** like files and streams.
* No need for finally block.

**Example:**

```java
try (FileReader fr = new FileReader("file.txt")) {
    System.out.println("Reading file");
}
```

---

## **7. How do you create custom exceptions?**

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

## **8. What is exception chaining?**

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

### 7. Collections Framework (8 Questions)
## **1. What is Java Collections Framework?**

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

## **2. Difference between ArrayList and LinkedList**

* **ArrayList** uses a **dynamic array** → fast **read**, slow **insert/delete** in middle.
* **LinkedList** uses **doubly linked list** → slow **read**, fast **insert/delete**.
* ArrayList is better for **searching**, LinkedList for **frequent modifications**.

```java
List<Integer> a = new ArrayList<>();
List<Integer> l = new LinkedList<>();
```

---

## **3. Difference between HashMap and TreeMap**

* **HashMap** stores data in **no order**.
* **TreeMap** stores data in **sorted order** (natural or comparator).
* HashMap is **faster**, TreeMap is used when **ordering is required**.

```java
Map<Integer, String> map1 = new HashMap<>();
Map<Integer, String> map2 = new TreeMap<>();
```

---

## **4. Difference between HashMap and Hashtable**

* **HashMap** is **not synchronized**, faster.
* **Hashtable** is **synchronized**, thread-safe but slower.
* HashMap allows **one null key and multiple null values**.
* Hashtable allows **no null key or value**.

```java
Map<String, String> map = new HashMap<>();
Hashtable<String, String> table = new Hashtable<>();
```

---

## **5. How does HashMap work internally?**

* HashMap uses **hashing** to store key-value pairs.
* Key’s `hashCode()` determines the **bucket index**.
* If multiple keys map to same bucket → **linked list / tree (Java 8+)**.
* Uses `equals()` to identify the correct key.

```java
map.put("A", 1); // hashCode → bucket → store entry
```

---

## **6. What is hash collision and how is it handled?**

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

## **7. Difference between fail-fast and fail-safe iterators**

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

## **8. Difference between Comparable and Comparator**

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

## **9. What are WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?**

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

### 8. Multithreading and Synchronization (8 Questions)
1. **What is multithreading?**
2. **How do you create threads in Java?**
3. **What is the difference between extending Thread and implementing Runnable?**
4. **What are the states of a thread?**
5. **What is synchronization in Java?**
6. **What is deadlock and how do you prevent it?**
7. **What is volatile keyword?**
8. **What is the difference between synchronized and volatile?**
9. **What is race condition and atomic operation?**

### 9. Advanced Concurrency (6 Questions)
1. **What is ExecutorService?**
2. **What are the types of thread pools?**
3. **What is Future and CompletableFuture?**
4. **What is CountDownLatch?**
5. **What is ReentrantLock?**
6. **What is the difference between ReentrantLock and synchronized?**

### 10. JVM and Memory Management (8 Questions)
1. **What are the different memory areas in JVM?**
2. **What is the difference between heap and stack?**
2. **What is the difference between permgen and metaspace?**
3. **What is garbage collection?**
4. **What are the types of garbage collectors?**
5. **What is generational garbage collection?**
6. **What is the difference between minor GC and major GC?**
7. **What is metaspace?**
8. **What are GC roots?**

### 11. Input/Output (I/O) (6 Questions)
1. **What are the different ways to read a file in Java?**
2. **What is the difference between InputStream and Reader?**
3. **What is BufferedReader and BufferedWriter?**
4. **How do you handle large files efficiently?**
4. **What is NIO in Java?**
5. **What is the difference between IO and NIO?**
6. **When would you use NIO over traditional I/O?**

### 12. Generics (6 Questions)
1. **What are generics in Java?**
2. **Why were generics introduced?**
3. **What is type erasure?**
4. **What is the difference between <? extends T> and <? super T>?**
5. **What is PECS principle?**
6. **What are the limitations of generics?**

### 13. Annotations and Reflection (6 Questions)
1. **What are annotations in Java?**
1. **What are built-in annotations?**
2. **How do you create custom annotations?**
3. **What is retention policy?**
3. **What is the difference between @Override and @Overload?**
4. **What is reflection in Java?**
5. **When should you use reflection?**
6. **What are the performance implications of reflection?**
7. **What are the security implications of reflection?**
8. **How do you handle exceptions in reflection?**

### 14. Lambda Expressions and Streams API (8 Questions)
1. **What are lambda expressions?**
2. **What are functional interfaces?**
3. **What are method references?**
4. **What is the difference between lambda and anonymous class?**
5. **What is Stream API?**
5. **What is the difference between Collection and Stream?**
6. **What are intermediate and terminal operations?**
7. **What is the difference between map() and flatMap()?**
8. **What is Optional class?**

### 15. JDBC (6 Questions)
1. **What is JDBC?**
2. **What are the steps to connect to a database using JDBC?**
3. **What is the difference between Statement and PreparedStatement?**
4. **What is connection pooling?**
5. **What is SQL injection and how to prevent it?**
6. **What is transaction management in JDBC?**

### 16. Design Patterns (8 Questions)
1. **What are design patterns?**
2. **What is Singleton pattern?**
3. **How do you implement thread-safe Singleton?**
4. **What is Factory pattern?**
5. **What is Observer pattern?**
6. **What is Strategy pattern?**
7. **What is Adapter pattern?**
8. **What is Decorator pattern?**

### 17. Spring Framework (8 Questions)
1. **What is Spring Framework?**
2. **What is Inversion of Control (IoC)?**
3. **What is Dependency Injection?**
4. **What is the difference between BeanFactory and ApplicationContext?**
3. **What are Spring beans?**
5. **What is Spring Boot?**
6. **What is auto-configuration in Spring Boot?**
7. **What is @SpringBootApplication annotation?**
8. **What is the difference between @Component, @Service, and @Repository?**
9. **What is @Autowired annotation?**
10. **What is @Qualifier annotation?**
11. **What is ApplicationContext?**


### 18. RESTful Services (6 Questions)
1. **What are RESTful web services?**
2. **What are the principles of REST?**
3. **What are HTTP methods and their usage?**
4. **What is the difference between PUT and POST?**
5. **What is idempotency in REST?**
6. **What are HTTP status codes?**

### 19. Microservices (6 Questions)
1. **What are microservices?**
2. **What are the advantages of microservices?**
3. **What are the challenges of microservices?**
4. **What is service discovery?**
5. **What is API Gateway?**
6. **What is circuit breaker pattern?**

### 20. Performance Tuning (8 Questions)
1. **How do you identify performance bottlenecks?**
2. **What are common performance issues in Java applications?**
3. **What is connection pooling and why is it important?**
4. **What is caching and when should you use it?**
5. **What are important JVM parameters?**
6. **How do you tune heap size?**
7. **What is the difference between -Xms and -Xmx?**
8. **How do you analyze heap dumps?**
9. **What is JIT compilation?**

### 21. Modern Java Features (4 Questions)
1. **What are the new features in Java 8?**
2. **What are the new features in Java 11?**
3. **What are the new features in Java 17?**
4. **What is the Java release cycle and LTS versions?**


#### Cloud and Containerization
1. **What is containerization?**
2. **What is Docker?**
3. **What is Kubernetes?**
4. **What is cloud computing?**
5. **What is distributed system?**
6. **What is load balancing?**
7. **What is caching strategies?**
---

**Total Questions: 147**

*This question bank covers the most critical and frequently asked Java interview questions across all experience levels, from junior to senior positions.*