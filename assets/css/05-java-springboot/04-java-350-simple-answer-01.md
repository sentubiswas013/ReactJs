# Core Java Fundamentals 

---

## 1. What is Java and what are its key features?

* Java is a **high-level, object-oriented programming language** used to build secure, portable, and scalable applications.
* It is widely used in **web apps, mobile apps (Android), enterprise systems, and backend services**.
* Key features include **platform independence, object-oriented design, security, robustness, multithreading, and automatic memory management**.

**Example:**

```java
public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

---

## 2. Difference between JDK, JRE, and JVM

* **JVM (Java Virtual Machine)** runs Java bytecode and makes Java platform-independent.
* **JRE (Java Runtime Environment)** includes JVM + core libraries to run Java programs.
* **JDK (Java Development Kit)** includes JRE + development tools like compiler (`javac`).

**Flow:**

```
.java → javac → .class → JVM → Output
```

---

## 3. What is platform independence in Java?

* Platform independence means **Java programs can run on any OS** without modification.
* Java code is compiled into **bytecode**, not machine code.
* The JVM converts bytecode into OS-specific instructions.

**Example:**

```java
System.out.println("Runs on Windows, Linux, Mac");
```

---

## 4. How does Java achieve "Write Once, Run Anywhere"?

* Java compiler converts code into **bytecode (.class file)**.
* Bytecode is **not tied to any operating system**.
* Any system with a JVM can execute it.

**Diagram:**

```
Source Code → Bytecode → JVM → Any OS
```

---

## 5. Main principles of Object-Oriented Programming

* **Encapsulation** – binding data and methods together
* **Inheritance** – reusing existing class features
* **Polymorphism** – many forms of the same method
* **Abstraction** – hiding implementation details

**Example:**

```java
class Car {
    void drive() {
        System.out.println("Car is driving");
    }
}
```

---

## 6. Difference between class and object

* A **class** is a blueprint or template.
* An **object** is a real-world instance of a class.
* Multiple objects can be created from one class.

**Example:**

```java
class Student {
    String name;
}

Student s1 = new Student();
s1.name = "Alex";
```

---

## 7. What is encapsulation and how is it implemented?

* Encapsulation means **hiding data** and allowing access through methods.
* It is implemented using **private variables** and **public getters/setters**.
* Improves security and maintainability.

**Example:**

```java
class Account {
    private double balance;

    public void setBalance(double b) {
        balance = b;
    }

    public double getBalance() {
        return balance;
    }
}
```

---

## 8. What is inheritance and its types?

* Inheritance allows a class to **acquire properties of another class**.
* It promotes code reuse.
* Java supports **single, multilevel, and hierarchical inheritance**.

**Example:**

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

---

## 9. What is polymorphism? Explain with example

* Polymorphism means **one method, many behaviors**.
* Achieved using **method overloading** and **method overriding**.

**Example (Overriding):**

```java
class Shape {
    void draw() {
        System.out.println("Drawing shape");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing circle");
    }
}
```

---

## 10. What is abstraction and how is it achieved?

* Abstraction hides **implementation details** and shows only essential features.
* Achieved using **abstract classes and interfaces**.
* Helps reduce complexity.

**Example:**

```java
abstract class Vehicle {
    abstract void start();
}

class Bike extends Vehicle {
    void start() {
        System.out.println("Bike starts with kick");
    }
}
```

---

## Data Types and Variables 

---

## 11. What are primitive data types in Java?

* Primitive data types are **basic data types** that store simple values.
* Java has **8 primitives**: byte, short, int, long, float, double, char, boolean.
* They are **faster** and store actual values, not objects.

**Example:**

```java
int age = 25;
boolean isActive = true;
```

---

## 12. Difference between primitive and reference types

* **Primitive types** store actual values and use less memory.
* **Reference types** store memory addresses of objects.
* Reference types can be `null`, primitives cannot.

**Example:**

```java
int x = 10;          // primitive
String name = "Tom"; // reference
```

---

## 13. What is autoboxing and unboxing?

* **Autoboxing** converts primitive to wrapper object automatically.
* **Unboxing** converts wrapper object back to primitive.
* Introduced to make Java collections easier to use.

**Example:**

```java
Integer a = 10; // autoboxing
int b = a;      // unboxing
```

---

## 14. Explain wrapper classes

* Wrapper classes convert primitives into objects.
* Each primitive has a wrapper like `Integer`, `Double`, `Character`.
* Required for collections like ArrayList.

**Example:**

```java
Integer num = Integer.valueOf(5);
```

---

## 15. Difference between == and equals()

* `==` compares **memory references**.
* `equals()` compares **actual content** of objects.
* For primitives, `==` compares values.

**Example:**

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2);      // false
System.out.println(s1.equals(s2)); // true
```

---

## 16. Difference between String, StringBuilder, and StringBuffer

* **String** is immutable.
* **StringBuilder** is mutable and fast (not thread-safe).
* **StringBuffer** is mutable and thread-safe.

**Example:**

```java
StringBuilder sb = new StringBuilder("Hi");
sb.append(" Java");
```

---

## 17. Why are strings immutable in Java?

* Immutability improves **security, caching, and thread safety**.
* Used heavily in string pool and as keys in collections.
* Any change creates a new object.

**Example:**

```java
String s = "Hello";
s.concat(" World"); // creates new object
```

---

## 18. What is string pooling?

* String pool stores **unique string literals**.
* Saves memory by reusing same string objects.
* Strings created with literals go to the pool.

**Example:**

```java
String a = "Java";
String b = "Java";
System.out.println(a == b); // true
```

---

## 19. How do you compare strings in Java?

* Use `equals()` to compare content.
* Use `==` only to compare references.
* Use `compareTo()` for lexicographical comparison.

**Example:**

```java
String a = "ABC";
String b = "ABC";
System.out.println(a.equals(b));
```

---

## 20. Difference between final, finally, and finalize

* `final` prevents modification (variables, methods, classes).
* `finally` executes cleanup code after try-catch.
* `finalize()` is called by garbage collector.

**Example:**

```java
final int x = 10;

try {
    int a = 5 / 0;
} finally {
    System.out.println("Cleanup");
}
```

---

## Control Structures 

---

## 21. What are the different types of loops in Java?

* Java provides **three main loops**: `for`, `while`, and `do-while`.
* `for` loop is used when the number of iterations is known.
* `while` and `do-while` are used when iterations depend on a condition.
* `do-while` runs at least once.

**Example:**

```java
for (int i = 1; i <= 3; i++) {
    System.out.println(i);
}
```

---

## 22. Difference between break and continue

* `break` **terminates the loop completely**.
* `continue` **skips the current iteration** and moves to the next one.
* Both are used to control loop execution.

**Example:**

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) continue;
    if (i == 5) break;
    System.out.println(i);
}
```

---

## 23. What is enhanced for loop (for-each)?

* Enhanced for loop is used to **iterate over arrays and collections**.
* It is simpler and avoids index handling.
* Best when you only need values, not index.

**Example:**

```java
int[] nums = {1, 2, 3};
for (int n : nums) {
    System.out.println(n);
}
```

---

## 24. When would you use switch vs if-else?

* Use `if-else` for **complex conditions and ranges**.
* Use `switch` for **fixed values** like menu options.
* `switch` improves readability for multiple constant cases.

**Example:**

```java
int day = 2;
switch (day) {
    case 1: System.out.println("Mon"); break;
    case 2: System.out.println("Tue"); break;
}
```

---

## 25. Rules for switch statement in Java

* `switch` works with **byte, short, int, char, String, enum**.
* Case values must be **constant and unique**.
* `break` prevents fall-through.
* `default` is optional.

**Example:**

```java
char grade = 'A';
switch (grade) {
    case 'A': System.out.println("Excellent"); break;
    default: System.out.println("Other");
}
```

---

## Object-Oriented Programming – Classes and Objects

---

## 26. What is a constructor in Java?

* A constructor is a **special method** used to initialize objects.
* It has the **same name as the class** and no return type.
* It is called **automatically** when an object is created.

**Example:**

```java
class Car {
    Car() {
        System.out.println("Car created");
    }
}
```

---

## 27. Types of constructors

* **Default constructor**: no parameters, provided by compiler.
* **Parameterized constructor**: accepts arguments.
* Used to initialize objects with different values.

**Example:**

```java
class Student {
    Student(String name) {
        System.out.println(name);
    }
}
```

---

## 28. What is constructor chaining?

* Constructor chaining means **calling one constructor from another**.
* Done using `this()` or `super()`.
* Helps avoid code duplication.

**Example:**

```java
class Test {
    Test() {
        this(10);
    }
    Test(int x) {
        System.out.println(x);
    }
}
```

---

## 29. Can you call a constructor from another constructor?

* Yes, using the `this()` keyword.
* It must be the **first statement** in the constructor.
* Used for constructor chaining.

**Example:**

```java
class Demo {
    Demo() {
        this(5);
    }
    Demo(int a) {
        System.out.println(a);
    }
}
```

---

## 30. Difference between this and super keywords

* `this` refers to **current class object**.
* `super` refers to **parent class object**.
* Used to access variables, methods, and constructors.

**Example:**

```java
class A {
    int x = 10;
}
class B extends A {
    int x = 20;
    void show() {
        System.out.println(this.x);
        System.out.println(super.x);
    }
}
```

---

## 31. What is method overloading?

* Method overloading means **same method name, different parameters**.
* Happens in the **same class**.
* Improves readability and flexibility.

**Example:**

```java
class Calc {
    int add(int a, int b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
```

---

## 32. What is method overriding?

* Method overriding means **child class provides its own implementation**.
* Happens in **inheritance**.
* Method signature must be same.

**Example:**

```java
class Parent {
    void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    void show() { System.out.println("Child"); }
}
```

---

## 33. Rules for method overriding

* Method name and parameters must be same.
* IS-A relationship is required.
* Cannot reduce access level.
* `final` and `static` methods cannot be overridden.

**Example:**

```java
@Override
void show() { }
```

---

## 34. Difference between overloading and overriding

* Overloading is **compile-time polymorphism**.
* Overriding is **runtime polymorphism**.
* Overloading: same class, overriding: parent-child classes.

**Example:**

```java
// Overloading
add(1,2);
add(1,2,3);
```

---

## 35. What is dynamic method dispatch?

* Dynamic method dispatch decides **which overridden method to call at runtime**.
* Based on **object type**, not reference type.
* Achieved using method overriding.

**Example:**

```java
Parent p = new Child();
p.show(); // calls Child's method
```

---

## Object-Oriented Programming – Inheritance

---

## 36. What is single inheritance in Java?

* Single inheritance means **one child class extends one parent class**.
* It is the simplest form of inheritance.
* Helps reuse code and maintain clarity.

**Example:**

```java
class Parent {
    void show() {
        System.out.println("Parent class");
    }
}

class Child extends Parent {
}
```

---

## 37. Why doesn't Java support multiple inheritance?

* Multiple inheritance can cause **ambiguity and complexity**.
* Leads to problems like the **diamond problem**.
* Java avoids this to keep code simple and maintainable.

**Note:** Java supports multiple inheritance through **interfaces**.

---

## 38. What is the diamond problem?

* Occurs when a class inherits from **two classes that have the same method**.
* JVM gets confused about which method to call.
* Common in languages that support multiple inheritance.

**Diagram:**

```
   A
  / \
 B   C
  \ /
   D
```

---

## 39. How does Java solve the diamond problem?

* Java does **not allow multiple inheritance with classes**.
* Interfaces resolve it using **default methods** with explicit override.
* Method must be overridden to remove ambiguity.

**Example:**

```java
interface A {
    default void show() {}
}

class B implements A {
    public void show() {}
}
```

---

## 40. What is the use of super keyword?

* `super` refers to the **parent class object**.
* Used to access parent class variables, methods, and constructors.
* Commonly used in method overriding.

**Example:**

```java
super.show();
```

---

## 41. What is method hiding?

* Method hiding occurs when a **static method** in child class
  has the same signature as parent class static method.
* It is resolved at **compile time**.

**Example:**

```java
class A {
    static void display() {}
}
class B extends A {
    static void display() {}
}
```

---

## 42. Can you override static methods?

* No, static methods **cannot be overridden**.
* They belong to the class, not the object.
* They can only be **hidden**, not overridden.

---

## 43. Can you override private methods?

* No, private methods are **not visible** to child classes.
* They are not inherited.
* So overriding is not possible.

---

## 44. What is covariant return type?

* Covariant return type allows a child class method
  to return a **subclass of the parent method's return type**.
* Introduced in Java 5.

**Example:**

```java
class A {
    A get() { return this; }
}
class B extends A {
    B get() { return this; }
}
```

---

## 45. Difference between IS-A and HAS-A relationship

* **IS-A** represents inheritance.
* **HAS-A** represents composition or aggregation.
* HAS-A is more flexible than IS-A.

**Example:**

```java
class Engine {}
class Car {
    Engine e = new Engine(); // HAS-A
}
```

---

## Object-Oriented Programming – Interfaces and Abstract Classes

---

## 46. What is an interface in Java?

* An interface is a **blueprint of a class**.
* It contains **abstract methods, default methods, static methods, and constants**.
* Used to achieve **100% abstraction and multiple inheritance**.

**Example:**

```java
interface Vehicle {
    void start();
}
```

---

## 47. What is an abstract class?

* An abstract class is a class that **cannot be instantiated**.
* It can have **abstract and non-abstract methods**.
* Used when classes share common behavior.

**Example:**

```java
abstract class Animal {
    abstract void sound();
}
```

---

## 48. Difference between interface and abstract class

* Interface supports **multiple inheritance**, abstract class does not.
* Interface methods are abstract by default.
* Abstract class can have constructors and instance variables.

**Example:**

```java
interface A {}
abstract class B {}
```

---

## 49. Can an interface have constructors?

* No, interfaces **cannot have constructors**.
* They cannot be instantiated.
* Constructors are only for classes.

---

## 50. What are default methods in interfaces?

* Default methods allow **method implementation inside interfaces**.
* Introduced in Java 8.
* Helps add new methods without breaking implementations.

**Example:**

```java
interface Test {
    default void show() {
        System.out.println("Default method");
    }
}
```

---

## 51. What are static methods in interfaces?

* Static methods belong to the **interface itself**.
* They cannot be overridden by implementing classes.
* Used for utility methods.

**Example:**

```java
interface Utils {
    static void help() {}
}
```

---

## 52. Can you have private methods in interfaces?

* Yes, from Java 9 onwards.
* Private methods are used to **support default methods**.
* They improve code reuse inside interface.

**Example:**

```java
interface Sample {
    private void log() {}
}
```

---

## 53. What is marker interface?

* Marker interface has **no methods**.
* Used to provide metadata to JVM.
* Example: `Serializable`, `Cloneable`.

---

## 54. What is functional interface?

* Functional interface has **only one abstract method**.
* Used with **lambda expressions**.
* Annotated with `@FunctionalInterface`.

**Example:**

```java
@FunctionalInterface
interface Calc {
    int add(int a, int b);
}
```

---

## 55. Can an interface extend another interface?

* Yes, an interface can **extend one or more interfaces**.
* Supports multiple inheritance.

**Example:**

```java
interface A {}
interface B extends A {}
```

---

## Exception Handling – Basics

---

## 56. What is an exception in Java?

* An exception is an **unexpected event** that occurs during program execution.
* It disrupts the normal flow of the program.
* Java provides a powerful mechanism to **handle runtime errors**.

**Example:**

```java
int a = 5 / 0; // ArithmeticException
```

---

## 57. Difference between error and exception

* **Errors** are serious problems that applications should not try to handle.
* **Exceptions** are recoverable conditions.
* Errors occur due to system issues, exceptions due to code issues.

**Example:**

```java
// Error: OutOfMemoryError
// Exception: NullPointerException
```

---

## 58. What is the exception hierarchy in Java?

* All exceptions inherit from the **Throwable** class.
* Throwable has two children: **Error** and **Exception**.
* RuntimeException is a subclass of Exception.

**Hierarchy:**

```
Throwable
 ├─ Error
 └─ Exception
     └─ RuntimeException
```

---

## 59. What are checked and unchecked exceptions?

* **Checked exceptions** are checked at compile time.
* **Unchecked exceptions** occur at runtime.
* Checked must be handled, unchecked are optional.

**Example:**

```java
// Checked
FileReader fr = new FileReader("a.txt");

// Unchecked
int x = 10 / 0;
```

---

## 60. Difference between throw and throws

* `throw` is used to **explicitly throw an exception**.
* `throws` declares exceptions in method signature.
* `throw` is inside method, `throws` is with method definition.

**Example:**

```java
throw new ArithmeticException();

void read() throws IOException {}
```

---

## 61. What is try-catch-finally block?

* `try` contains risky code.
* `catch` handles the exception.
* `finally` executes whether exception occurs or not.

**Example:**

```java
try {
    int a = 10 / 0;
} catch (Exception e) {
    System.out.println(e);
} finally {
    System.out.println("Cleanup");
}
```

---

## 62. Can you have multiple catch blocks?

* Yes, multiple catch blocks are allowed.
* They handle **different exception types**.
* Order matters: child exception first.

**Example:**

```java
catch (ArithmeticException e) {}
catch (Exception e) {}
```

---

## 63. What is try-with-resources?

* Introduced in Java 7.
* Automatically closes resources.
* Reduces boilerplate code.

**Example:**

```java
try (FileReader fr = new FileReader("a.txt")) {
}
```

---

## 64. What happens if exception occurs in finally block?

* The exception in `finally` **overrides previous exceptions**.
* Original exception may be lost.
* This is why throwing exceptions in finally is discouraged.

---

## 65. Can you throw an exception from finally block?

* Yes, but it is **not recommended**.
* It hides the original exception.
* Makes debugging difficult.

**Example:**

```java
finally {
    throw new RuntimeException();
}
```

---

## Exception Handling – Custom Exceptions

---

## 66. How do you create custom exceptions?

* Custom exceptions are created by **extending Exception or RuntimeException**.
* Used to represent application-specific errors.
* You can add custom messages or logic.

**Example:**

```java
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}
```

---

## 67. When should you create custom exceptions?

* When built-in exceptions are **not meaningful enough**.
* To clearly represent **business logic errors**.
* Improves readability and error handling.

**Example:**

```java
if (age < 18) {
    throw new InvalidAgeException("Not eligible");
}
```

---

## 68. What is exception chaining?

* Exception chaining means **linking one exception to another**.
* Helps preserve the **original cause** of error.
* Done using constructor or `initCause()`.

**Example:**

```java
catch (Exception e) {
    throw new RuntimeException("Failed", e);
}
```

---

## 69. What is suppressed exception?

* Suppressed exceptions occur in **try-with-resources**.
* Happens when an exception is thrown while closing resources.
* Primary exception is thrown, others are suppressed.

**Example:**

```java
try (Resource r = new Resource()) {
    throw new Exception("Main");
}
```

---

## 70. How do you handle multiple exceptions in a single catch?

* Java allows **multi-catch block** using `|`.
* Reduces duplicate code.
* Introduced in Java 7.

**Example:**

```java
try {
    int a = 10 / 0;
} catch (ArithmeticException | NullPointerException e) {
    System.out.println(e);
}
```

---

## Java Collections Framework – Short Spoken Answers with Examples

---

### 71. What is Java Collections Framework?

* Java Collections Framework (JCF) is a set of classes and interfaces used to store, manipulate, and process groups of objects.
* It provides ready-made data structures like List, Set, and Map with common operations.
* It helps reduce coding effort, improves performance, and ensures consistency.

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
```

---

### 72. Difference between Collection and Collections?

* `Collection` is an interface that represents a group of objects.
* `Collections` is a utility class that provides static helper methods.
* One defines behavior, the other provides operations.

```java
Collection<Integer> list = new ArrayList<>();
Collections.sort((List<Integer>) list);
```

---

### 73. Main interfaces in Collections Framework?

* `Collection` is the root interface.
* `List`, `Set`, and `Queue` extend Collection.
* `Map` is separate and represents key-value pairs.

```java
List<String> list = new ArrayList<>();
Set<String> set = new HashSet<>();
Map<Integer, String> map = new HashMap<>();
```

---

### 74. Difference between List, Set, and Map?

* `List` allows duplicates and maintains order.
* `Set` does not allow duplicates.
* `Map` stores data as key-value pairs.

```java
List<String> l = Arrays.asList("A", "A");
Set<String> s = new HashSet<>(l);
Map<Integer, String> m = Map.of(1, "A");
```

---

### 75. Difference between ArrayList and LinkedList?

* `ArrayList` uses a dynamic array.
* `LinkedList` uses a doubly linked list.
* ArrayList is faster for access, LinkedList is faster for insert/delete.

```java
List<Integer> a = new ArrayList<>();
List<Integer> l = new LinkedList<>();
```

---

### 76. When to use ArrayList vs LinkedList?

* Use `ArrayList` when frequent reads are required.
* Use `LinkedList` when frequent insertions or deletions are needed.
* Most applications prefer ArrayList.

```java
ArrayList<String> readHeavy = new ArrayList<>();
LinkedList<String> modifyHeavy = new LinkedList<>();
```

---

### 77. Difference between HashSet and TreeSet?

* `HashSet` does not maintain order.
* `TreeSet` stores elements in sorted order.
* TreeSet is slower due to sorting.

```java
Set<Integer> hs = new HashSet<>();
Set<Integer> ts = new TreeSet<>();
```

---

### 78. Difference between HashMap and TreeMap?

* `HashMap` does not maintain order.
* `TreeMap` stores keys in sorted order.
* TreeMap uses Red-Black Tree internally.

```java
Map<Integer, String> hm = new HashMap<>();
Map<Integer, String> tm = new TreeMap<>();
```

---

### 79. Difference between HashMap and Hashtable?

* `HashMap` is not synchronized and allows one null key.
* `Hashtable` is synchronized and does not allow null.
* HashMap is preferred in modern Java.

```java
HashMap<Integer, String> hm = new HashMap<>();
Hashtable<Integer, String> ht = new Hashtable<>();
```

---

### 80. Difference between HashMap and ConcurrentHashMap?

* `HashMap` is not thread-safe.
* `ConcurrentHashMap` is thread-safe and supports concurrent access.
* It uses internal locking for better performance.

```java
Map<Integer, String> chm = new ConcurrentHashMap<>();
chm.put(1, "Java");
```

---

## Advanced Collections – Short Spoken Answers with Examples

---

### 81. How does HashMap work internally?

* HashMap stores data as key-value pairs using hashing.
* Key’s `hashCode()` is used to find a bucket index.
* Inside each bucket, entries are stored as a linked list or tree.

```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
```

---

### 82. What is hash collision and how is it handled?

* A collision occurs when multiple keys map to the same bucket.
* Java first uses a linked list, then converts it to a tree if collisions increase.
* This improves performance.

```java
map.put("FB", 1);
map.put("Ea", 2); // Same hashCode
```

---

### 83. What is the load factor in HashMap?

* Load factor defines when HashMap should resize.
* Default value is 0.75.
* It balances memory usage and performance.

```java
Map<Integer, String> map = new HashMap<>(16, 0.75f);
```

---

### 84. What happens when HashMap reaches its capacity?

* HashMap resizes when size exceeds capacity × load factor.
* Capacity is doubled.
* All entries are rehashed.

```java
map.put(100, "Resize happens internally");
```

---

### 85. Difference between fail-fast and fail-safe iterators?

* Fail-fast throws exception on modification during iteration.
* Fail-safe works on a copy and does not throw exception.
* Fail-fast is faster.

```java
Iterator<Integer> it = list.iterator(); // fail-fast
```

---

### 86. What is WeakHashMap?

* WeakHashMap stores keys using weak references.
* Entries are removed when key is no longer used.
* Useful for caching.

```java
Map<Object, String> map = new WeakHashMap<>();
```

---

### 87. What is IdentityHashMap?

* IdentityHashMap compares keys using `==` instead of `equals()`.
* Used when reference equality is required.

```java
Map<String, String> map = new IdentityHashMap<>();
```

---

### 88. What is LinkedHashMap?

* LinkedHashMap maintains insertion order.
* It uses a doubly linked list.
* Often used for LRU cache.

```java
Map<Integer, String> map = new LinkedHashMap<>();
```

---

### 89. What is PriorityQueue?

* PriorityQueue orders elements based on priority.
* Default behavior is min-heap.
* Does not allow null elements.

```java
Queue<Integer> pq = new PriorityQueue<>();
pq.add(5);
pq.add(1);
```

---

### 90. Difference between Comparable and Comparator?

* Comparable defines natural ordering.
* Comparator defines custom ordering.
* Comparator is more flexible.

```java
Collections.sort(list, (a, b) -> a - b);
```

---

## Collection Performance – Short Spoken Answers with Examples

---

### 91. What is the time complexity of ArrayList operations?

* Access by index is O(1) because it uses an array.
* Adding at the end is O(1) amortized.
* Inserting or deleting in the middle is O(n) due to shifting.

```java
List<Integer> list = new ArrayList<>();
list.add(10);      // O(1)
list.get(0);       // O(1)
list.remove(0);    // O(n)
```

---

### 92. What is the time complexity of HashMap operations?

* `put()` and `get()` are O(1) on average.
* Worst case is O(n), but Java 8+ reduces it to O(log n) using trees.
* Performance depends on good hashCode implementation.

```java
Map<Integer, String> map = new HashMap<>();
map.put(1, "A");
map.get(1);
```

---

### 93. What is the time complexity of TreeMap operations?

* TreeMap operations take O(log n) time.
* It uses a Red-Black Tree internally.
* Slower than HashMap but keeps sorted order.

```java
Map<Integer, String> map = new TreeMap<>();
map.put(3, "C");
```

---

### 94. How do you choose the right collection for your use case?

* Use List when order matters and duplicates are allowed.
* Use Set when uniqueness is required.
* Use Map for key-value lookups.
* Consider performance, ordering, and thread-safety.

```java
List<String> list = new ArrayList<>();
Set<String> set = new HashSet<>();
```

---

### 95. What are the best practices for using collections?

* Choose the right collection based on usage pattern.
* Use generics to avoid casting issues.
* Prefer interfaces over implementations.
* Avoid unnecessary synchronization.

```java
List<String> list = new ArrayList<>(); // use interface type
```

---

## Multithreading and Concurrency – Thread Basics

---

### 96. What is multithreading?

* Multithreading allows multiple threads to run concurrently within a program.
* It improves performance and resource utilization.
* Commonly used in background tasks and parallel processing.

```java
Thread t = new Thread(() -> System.out.println("Task running"));
t.start();
```

---

### 97. Difference between process and thread?

* A process is an independent program with its own memory.
* A thread is a lightweight unit inside a process.
* Threads share memory, processes do not.

```java
// Multiple threads inside one process
```

---

### 98. How do you create threads in Java?

* By extending the Thread class.
* By implementing the Runnable interface.
* Runnable is preferred.

```java
new Thread(() -> System.out.println("Thread running")).start();
```

---

### 99. Difference between extending Thread and implementing Runnable?

* Extending Thread limits inheritance.
* Runnable allows extending another class.
* Runnable separates task from thread.

```java
class Task implements Runnable {
  public void run() {}
}
```

---

### 100. What are the states of a thread?

* New, Runnable, Running, Blocked/Waiting, Terminated.
* Managed by JVM scheduler.

```java
Thread.State state = Thread.currentThread().getState();
```

---

### 101. Difference between start() and run()?

* start() creates a new thread.
* run() executes like a normal method.
* Always call start().

```java
thread.start(); // correct
```

---

### 102. What is thread priority?

* Priority decides execution preference.
* Range is 1 to 10.
* JVM may ignore priorities.

```java
thread.setPriority(Thread.MAX_PRIORITY);
```

---

### 103. What is daemon thread?

* Daemon threads run in background.
* JVM exits when only daemon threads remain.
* Used for cleanup tasks.

```java
thread.setDaemon(true);
```

---

### 104. Difference between user thread and daemon thread?

* User threads keep JVM alive.
* Daemon threads do not.
* Main thread is a user thread.

```java
// GC thread is daemon
```

---

### 105. How do you stop a thread?

* Use a flag or interruption.
* stop() is deprecated and unsafe.

```java
thread.interrupt();
```

---
### Synchronization
## 106. What is synchronization in Java?
- Synchronization is a way to **control access to shared resources** in a multithreaded program.
- It ensures that **only one thread executes a critical section at a time**.
- This prevents **data inconsistency** when multiple threads modify the same object.
- Java achieves this using **intrinsic locks (monitors)**.

**Example:**
```java
class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}
````

---

## 107. What is the `synchronized` keyword?

* `synchronized` is used to **lock an object or method** so only one thread can access it.
* The lock is automatically **acquired before execution** and **released after**.
* It helps prevent **race conditions**.
* It can be applied to **methods or blocks**.

**Example:**

```java
synchronized void print() {
    System.out.println("Thread-safe");
}
```

---

## 108. Difference between synchronized method and synchronized block?

* A **synchronized method** locks the **entire method**.
* A **synchronized block** locks **only a specific section of code**.
* Blocks are more **efficient and flexible**.
* Blocks allow locking on **specific objects**, not just `this`.

**Example:**

```java
void process() {
    synchronized (this) {
        // critical section
    }
}
```

---

## 109. What is deadlock?

* Deadlock occurs when **two or more threads wait forever** for each other’s locks.
* Each thread holds one lock and waits for another.
* The program **freezes without crashing**.
* It’s a serious concurrency bug.

**Example:**

```java
Thread A locks obj1 → waits for obj2
Thread B locks obj2 → waits for obj1
```

---

## 110. How do you prevent deadlock?

* Always **acquire locks in the same order**.
* Avoid **nested locks** when possible.
* Use **timeouts** or `tryLock()` from `ReentrantLock`.
* Keep synchronized blocks **small and simple**.

**Example:**

```java
lock1.lock();
lock2.lock();
// safe execution
```

---

## 111. What is a race condition?

* A race condition happens when **multiple threads modify shared data simultaneously**.
* The final result depends on **thread execution timing**.
* It leads to **unpredictable behavior**.
* Synchronization prevents this.

**Example:**

```java
count++; // not thread-safe
```

---

## 112. What is thread safety?

* Thread safety means code **works correctly even with multiple threads**.
* No data corruption or inconsistent state occurs.
* Achieved using **synchronization, immutability, or atomic classes**.
* Thread-safe code is **reliable and predictable**.

**Example:**

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

---

## 113. What is the `volatile` keyword?

* `volatile` ensures **visibility of changes** across threads.
* A write to a volatile variable is **immediately visible** to others.
* It does **not provide mutual exclusion**.
* Best for flags and status variables.

**Example:**

```java
volatile boolean running = true;
```

---

## 114. Difference between synchronized and volatile?

* `synchronized` provides **locking + visibility**.
* `volatile` provides **visibility only**, no locking.
* `synchronized` prevents race conditions.
* `volatile` is lighter and faster but limited.

**Example:**

```java
synchronized void update() { }
volatile int value;
```

---

## 115. What is an atomic operation?

* An atomic operation is **completed in a single step**.
* It cannot be interrupted by other threads.
* Java provides atomic classes in `java.util.concurrent.atomic`.
* They are **lock-free and thread-safe**.

**Example:**

```java
AtomicInteger num = new AtomicInteger(10);
num.incrementAndGet();
```

---

## Advanced Concurrency
## 116. What is `java.util.concurrent` package?
- It’s a **high-level concurrency framework** introduced in Java 5.
- It simplifies multithreading compared to low-level `Thread` and `synchronized`.
- Provides **thread pools, locks, atomic classes, and synchronization utilities**.
- Helps write **scalable, maintainable, and thread-safe code**.

**Example:**
```java
ExecutorService executor = Executors.newFixedThreadPool(2);
````

---

## 117. What is `ExecutorService`?

* `ExecutorService` manages **thread creation and execution** for you.
* You submit tasks, not threads.
* It improves **performance and resource management**.
* Supports task lifecycle methods like `shutdown()`.

**Example:**

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
executor.submit(() -> System.out.println("Task running"));
executor.shutdown();
```

---

## 118. What is `ThreadPoolExecutor`?

* `ThreadPoolExecutor` is the **core implementation** behind thread pools.
* It gives **full control** over pool size, queue, and rejection policy.
* Used when you need **custom thread pool behavior**.
* Most `Executors` methods use it internally.

**Example:**

```java
ThreadPoolExecutor executor =
    (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
```

---

## 119. What are the types of thread pools?

* **Fixed Thread Pool** – fixed number of threads.
* **Cached Thread Pool** – creates threads as needed.
* **Single Thread Executor** – one background thread.
* **Scheduled Thread Pool** – runs tasks after delay or periodically.

**Example:**

```java
ExecutorService fixedPool = Executors.newFixedThreadPool(5);
```

---

## 120. What is `Future` and `CompletableFuture`?

* `Future` represents the **result of an async task**.
* You can check completion or block using `get()`.
* `CompletableFuture` is **non-blocking and more powerful**.
* It supports **chaining, callbacks, and async pipelines**.

**Example:**

```java
CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenAccept(System.out::println);
```

---

## 121. What is `CountDownLatch`?

* `CountDownLatch` allows threads to **wait until a count reaches zero**.
* Used when one or more threads must wait for others to finish.
* It is **one-time use only**.
* Common in startup or testing scenarios.

**Example:**

```java
CountDownLatch latch = new CountDownLatch(2);
latch.countDown();
latch.await();
```

---

## 122. What is `CyclicBarrier`?

* `CyclicBarrier` lets multiple threads **wait for each other**.
* Once all reach the barrier, they **proceed together**.
* It is **reusable**, unlike `CountDownLatch`.
* Useful in parallel computation steps.

**Example:**

```java
CyclicBarrier barrier = new CyclicBarrier(3);
barrier.await();
```

---

## 123. What is `Semaphore`?

* `Semaphore` controls **access to a limited number of resources**.
* It uses permits instead of locks.
* Threads acquire a permit before proceeding.
* Useful for **connection pools or rate limiting**.

**Example:**

```java
Semaphore semaphore = new Semaphore(2);
semaphore.acquire();
semaphore.release();
```

---

## 124. What is `ReentrantLock`?

* `ReentrantLock` is an **explicit locking mechanism**.
* A thread can **re-acquire the same lock** it already holds.
* Offers advanced features like **tryLock and fairness**.
* More flexible than `synchronized`.

**Example:**

```java
ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

---

## 125. Difference between `ReentrantLock` and `synchronized`?

* `synchronized` is **simpler and built-in**.
* `ReentrantLock` offers **more control and flexibility**.
* `ReentrantLock` supports **tryLock, timeout, fairness**.
* `synchronized` automatically releases lock; `ReentrantLock` must be unlocked manually.

**Example:**

```java
// synchronized
synchronized void method() { }

// ReentrantLock
lock.lock();
lock.unlock();
```

---

## Memory Areas
### **126. What are the different memory areas in JVM?**

* JVM divides memory to manage execution efficiently
* **Heap** → stores objects
* **Stack** → stores method calls & local variables
* **Method Area / Metaspace** → class metadata
* **PC Register** → current instruction
* **Native Method Stack** → native (C/C++) calls

```java
public class Demo {
    int x = 10;   // Heap
    void test() { // Stack frame
        int y = 20;
    }
}
```

---

### **127. What is heap memory?**

* Heap stores **objects and instance variables**
* Shared across all threads
* Managed by **Garbage Collector**
* Larger but slower than stack

```java
class User {
    String name;
}

User u = new User(); // object stored in heap
```

---

### **128. What is stack memory?**

* Stack stores **method calls, local variables, references**
* Each thread has its **own stack**
* Faster access, automatically freed
* LIFO (Last In, First Out)

```java
void calculate() {
    int a = 10;  // stored in stack
}
```

---

### **129. What is method area?**

* Stores **class-level data**
* Includes class metadata, static variables, method bytecode
* Shared across all threads
* In Java 8+, implemented as **Metaspace**

```java
class Test {
    static int count = 0; // stored in method area
}
```

---

### **130. Difference between heap and stack?**

* Heap stores **objects**, stack stores **method execution**
* Heap is shared, stack is thread-specific
* Heap is slower, stack is faster
* Heap memory is GC-managed

```java
int x = 5;           // stack
Person p = new Person(); // p in stack, object in heap
```

---

### **131. What is permgen space?**

* PermGen stores **class metadata** (Java 7 and earlier)
* Fixed size → caused `OutOfMemoryError`
* Removed in Java 8

```java
// Too many classes loaded → PermGen error (Java 7)
```

---

### **132. What is metaspace?**

* Replaced PermGen in **Java 8+**
* Stores class metadata
* Uses **native memory**, not heap
* Grows dynamically

```bash
-XX:MaxMetaspaceSize=256m
```

---

### **133. Difference between permgen and metaspace?**

* PermGen is fixed-size, Metaspace is dynamic
* PermGen is inside JVM memory, Metaspace uses OS memory
* PermGen removed in Java 8

```text
Java 7 → PermGen
Java 8+ → Metaspace
```

---

### **134. What is direct memory?**

* Memory allocated **outside heap**
* Used by **NIO (ByteBuffer)**
* Faster I/O operations
* Not managed by GC directly

```java
ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
```

---

### **135. What is off-heap memory?**

* Memory allocated **outside JVM heap**
* Includes direct memory & native allocations
* Reduces GC pressure
* Used in high-performance systems

```java
sun.misc.Unsafe unsafe = getUnsafe();
long address = unsafe.allocateMemory(1024);
```

---
### Garbage Collection
### **136. What is garbage collection?**

* Garbage Collection (GC) is Java’s **automatic memory management** process.
* It **removes unused objects** from heap memory to prevent memory leaks.
* The developer doesn’t manually free memory like in C/C++.
* GC improves **application stability and safety**.

```java
String s = new String("Java");
s = null; // Object eligible for GC
```

---

### **137. How does garbage collection work?**

* GC works by **finding unreachable objects** in heap memory.
* It starts from **GC Roots** and checks which objects are still referenced.
* Unreachable objects are **marked and deleted**.
* This process runs automatically in the background.

```java
Object a = new Object();
Object b = a;
a = null;
b = null; // Object becomes unreachable
```

---

### **138. What are the types of garbage collectors?**

* **Serial GC** – single-threaded, small apps.
* **Parallel GC** – multi-threaded, high throughput.
* **CMS (Concurrent Mark Sweep)** – low pause time (deprecated).
* **G1 GC** – balanced performance (default in modern Java).
* **ZGC / Shenandoah** – ultra-low latency.

```bash
-XX:+UseG1GC
```

---

### **139. What is generational garbage collection?**

* Heap memory is divided based on **object age**.
* Most objects die young, so GC focuses there first.
* This improves **performance and speed**.
* Java heap has **Young, Old, and Metaspace**.

```java
// Short-lived objects go to Young Generation
for(int i=0;i<1000;i++) {
    new Object();
}
```

---

### **140. What are young generation and old generation?**

* **Young Generation** stores new objects.

  * Eden, Survivor S0, Survivor S1
* **Old Generation** stores long-lived objects.
* Objects promoted after surviving multiple GCs.

```java
List<String> list = new ArrayList<>(); // May move to Old Gen if long-lived
```

---

### **141. Difference between Minor GC and Major GC?**

* **Minor GC**

  * Cleans Young Generation
  * Fast and frequent
* **Major GC**

  * Cleans Old Generation
  * Slower and less frequent
* Major GC impacts application performance more.

```bash
Minor GC → Young Gen  
Major GC → Old Gen
```

---

### **142. What is Full GC?**

* Full GC cleans **entire heap memory**.
* Includes Young, Old, and Metaspace.
* Causes **long pause times (Stop-The-World)**.
* Should be avoided in production systems.

```bash
jstat -gc <pid>
```

---

### **143. What are GC Roots?**

* GC Roots are **starting points** for object reachability.
* Common GC Roots:

  * Local variables
  * Static variables
  * Active threads
  * JNI references
* Objects reachable from GC Roots are **not collected**.

```java
static Object root = new Object(); // GC Root
```

---

### **144. How do you tune garbage collection?**

* Choose the **right GC algorithm**.
* Adjust heap size and generations.
* Monitor GC logs.
* Reduce object creation.

```bash
-Xms512m -Xmx1024m -XX:+UseG1GC
```

---

### **145. What are the common GC algorithms?**

* **Mark & Sweep** – mark live objects, sweep dead ones.
* **Mark & Compact** – removes fragmentation.
* **Copying Algorithm** – used in Young Gen.
* **Generational Algorithm** – combines multiple strategies.

```java
// JVM internally applies these algorithms
System.gc(); // Request GC (not guaranteed)
```

---

## Input/Output (I/O)
### **146. What are the different ways to read a file in Java?**

* Java provides **multiple APIs** to read files depending on need.
* Using **FileInputStream** for binary data.
* Using **FileReader / BufferedReader** for text files.
* Using **Files class (NIO)** for modern, simple code.
* Using **Scanner** for small files and quick parsing.

```java
Files.readAllLines(Path.of("data.txt"));
```

---

### **147. What is the difference between InputStream and Reader?**

* **InputStream** handles **binary data** (bytes).
* **Reader** handles **character data** (text).
* Reader supports **character encoding** like UTF-8.
* Use InputStream for images, videos; Reader for text.

```java
InputStream is = new FileInputStream("img.png");
Reader r = new FileReader("text.txt");
```

---

### **148. What is the difference between OutputStream and Writer?**

* **OutputStream** writes **bytes**.
* **Writer** writes **characters**.
* Writer handles encoding automatically.
* Use Writer for text output, OutputStream for binary.

```java
Writer w = new FileWriter("out.txt");
OutputStream os = new FileOutputStream("out.bin");
```

---

### **149. What is BufferedReader and BufferedWriter?**

* They **improve performance** by reducing I/O operations.
* Data is read/written in **chunks instead of one character**.
* BufferedReader provides `readLine()` method.
* Recommended for large text files.

```java
BufferedReader br = new BufferedReader(new FileReader("data.txt"));
```

---

### **150. Difference between FileInputStream and FileReader?**

* **FileInputStream** reads **raw bytes**.
* **FileReader** reads **characters** using default encoding.
* FileReader is easier for text files.
* FileInputStream is better for binary data.

```java
FileInputStream fis = new FileInputStream("img.jpg");
FileReader fr = new FileReader("text.txt");
```

---

### **151. What is RandomAccessFile?**

* Allows **read and write at any position** in a file.
* Supports `seek()` method.
* Useful for **large files and partial updates**.
* Works in both read and write modes.

```java
RandomAccessFile raf = new RandomAccessFile("data.txt", "rw");
raf.seek(10);
raf.writeBytes("Java");
```

---

### **152. What is File class?**

* Represents **file or directory path**.
* Used to create, delete, check file properties.
* Does NOT read or write file content.
* Legacy API from `java.io`.

```java
File file = new File("test.txt");
System.out.println(file.exists());
```

---

### **153. What is Path and Files class?**

* Part of **Java NIO** (`java.nio.file`).
* **Path** represents file location.
* **Files** provides utility methods for file operations.
* Cleaner and more powerful than File class.

```java
Path path = Path.of("data.txt");
Files.readString(path);
```

---

### **154. Difference between File and Path?**

* **File** is older and less flexible.
* **Path** supports symbolic links, better error handling.
* Path works well with modern APIs.
* File is now mostly replaced by Path.

```java
File f = new File("a.txt");
Path p = Path.of("a.txt");
```

---

### **155. How do you handle large files efficiently?**

* Use **Buffered streams**.
* Read files **line by line**, not all at once.
* Use **streams or NIO channels**.
* Avoid loading entire file into memory.

```java
try (BufferedReader br = Files.newBufferedReader(Path.of("big.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        // process line
    }
}
```

---

### **156. What is NIO in Java?**

* NIO stands for **New I/O**, introduced in Java 1.4
* Designed for **high-performance I/O**
* Supports **non-blocking**, **buffer-based**, and **channel-based** operations
* Commonly used in servers and networking

```java
FileChannel channel = FileChannel.open(Path.of("data.txt"));
```

---

### **157. Difference between IO and NIO?**

* IO is **stream-based**, NIO is **buffer-based**
* IO is **blocking**, NIO supports **non-blocking**
* IO works with one thread per connection
* NIO can handle many connections with fewer threads

```java
// IO
InputStream in = new FileInputStream("a.txt");

// NIO
FileChannel channel = FileChannel.open(Path.of("a.txt"));
```

---

### **158. What is a Channel in NIO?**

* Channel is a **two-way data path**
* Can read and write
* Works with **buffers**
* Common channels: FileChannel, SocketChannel

```java
SocketChannel channel = SocketChannel.open();
channel.configureBlocking(false);
```

---

### **159. What is a Buffer in NIO?**

* Buffer temporarily holds data during I/O
* Data flows **channel → buffer → application**
* Key buffers: ByteBuffer, CharBuffer
* Uses position, limit, and capacity

```java
ByteBuffer buffer = ByteBuffer.allocate(1024);
```

---

### **160. What is a Selector in NIO?**

* Selector manages **multiple channels** using one thread
* Monitors events like read, write, connect
* Improves scalability

```java
Selector selector = Selector.open();
channel.register(selector, SelectionKey.OP_READ);
```

---

### **161. What is non-blocking I/O?**

* Thread does **not wait** for I/O completion
* Returns immediately
* Enables handling many clients efficiently

```java
channel.configureBlocking(false);
```

---

### **162. What is a memory-mapped file?**

* Maps file directly into **memory**
* OS handles reading/writing
* Very fast for large files

```java
MappedByteBuffer buffer =
    channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
```

---

### **163. When would you use NIO over traditional I/O?**

* High-traffic servers
* Real-time networking
* Large file transfers
* When scalability and performance matter

```java
ServerSocketChannel server = ServerSocketChannel.open();
server.configureBlocking(false);
```

---

### **164. What is NIO.2?**

* Introduced in **Java 7**
* Located in `java.nio.file`
* Better file system support
* Supports asynchronous I/O

```java
Path path = Paths.get("file.txt");
Files.readAllLines(path);
```

---

### **165. What is asynchronous I/O?**

* I/O runs in background
* Application continues execution
* Uses callbacks or futures

```java
AsynchronousFileChannel channel =
    AsynchronousFileChannel.open(Path.of("data.txt"));
```

---

## Generics
### **166. What are generics in Java?**

* Generics allow **type-safe code**
* They let you write **reusable classes and methods**
* Errors are caught at **compile time**, not runtime

```java
List<String> names = new ArrayList<>();
names.add("John"); // type-safe
```

---

### **167. Why were generics introduced?**

* To remove **ClassCastException** at runtime
* To eliminate explicit casting
* To improve code readability and safety

```java
// Before generics
List list = new ArrayList();
String s = (String) list.get(0);

// With generics
List<String> list = new ArrayList<>();
String s = list.get(0);
```

---

### **168. What is type erasure?**

* Generic type info is **removed at runtime**
* JVM works with **raw types**
* Ensures backward compatibility

```java
List<String> list = new ArrayList<>();
// At runtime → List
```

---

### **169. What are bounded type parameters?**

* Restrict generic types using `extends`
* Ensures type has specific behavior

```java
class Box<T extends Number> {
    T value;
}
```

---

### **170. Difference between `<?>` and `<? extends Object>`?**

* They are **effectively the same**
* All classes extend `Object`
* Both are read-only

```java
List<?> list1;
List<? extends Object> list2;
```

---

### **171. Difference between `<? extends T>` and `<? super T>`?**

* `extends` → **read-only (producer)**
* `super` → **write-only (consumer)**

```java
List<? extends Number> readOnly;
List<? super Integer> writeOnly;
```

---

### **172. What is PECS principle?**

* **Producer → Extends**
* **Consumer → Super**
* Helps choose correct wildcard

```java
void read(List<? extends Number> list) {}
void write(List<? super Integer> list) {}
```

---

### **173. Can you create generic arrays?**

* **No**, directly not allowed
* Because of type erasure
* Use collections instead

```java
// Not allowed
// T[] arr = new T[10];

List<T> list = new ArrayList<>();
```

---

### **174. What are generic methods?**

* Methods with their own type parameters
* Independent of class generics

```java
static <T> void print(T value) {
    System.out.println(value);
}
```

---

### **175. What are the limitations of generics?**

* No primitive types (`int`, `double`)
* No generic arrays
* No runtime type checks
* Static members can’t use type parameters

```java
// Not allowed
// if(obj instanceof List<String>)
```

---

## Annotations and Reflection

### **176. What are annotations in Java?**

* Annotations are **metadata** added to Java code.
* They provide information to the **compiler, JVM, or frameworks**.
* They don’t change program logic directly.
* Commonly used in **Spring, JPA, Lombok**, etc.

```java
@Override
public String toString() {
    return "Java";
}
```

---

### **177. What are built-in annotations?**

* Built-in annotations come with Java.
* Common ones include:

  * `@Override`
  * `@Deprecated`
  * `@SuppressWarnings`
  * `@FunctionalInterface`
* They help with **compile-time checks and documentation**.

```java
@Deprecated
void oldMethod() {}
```

---

### **178. How do you create custom annotations?**

* Use `@interface` keyword.
* Can define elements like methods.
* Often combined with retention and target.

```java
@interface MyAnnotation {
    String value();
}
```

---

### **179. What is retention policy?**

* Retention policy defines **how long annotation is available**.
* Three types:

  * `SOURCE` – compile time only
  * `CLASS` – in bytecode
  * `RUNTIME` – accessible via reflection
* Runtime is used by frameworks.

```java
@Retention(RetentionPolicy.RUNTIME)
@interface Info {}
```

---

### **180. Difference between @Override and @Overload?**

* `@Override` checks method **overriding correctness**.
* `@Overload` does NOT exist in Java.
* Method overloading is handled by **compiler automatically**.
* Using `@Override` avoids runtime bugs.

```java
@Override
public String toString() { return "Test"; }
```

---

### **181. What is @SuppressWarnings?**

* Used to **suppress compiler warnings**.
* Helps clean code when warnings are known and safe.
* Common warnings: `unchecked`, `deprecation`.

```java
@SuppressWarnings("unchecked")
List list = new ArrayList();
```

---

### **182. What is @Deprecated?**

* Marks code as **outdated**.
* Compiler shows warning when used.
* Helps maintain backward compatibility.
* Often replaced with newer APIs.

```java
@Deprecated
void oldApi() {}
```

---

### **183. What is @FunctionalInterface?**

* Ensures the interface has **only one abstract method**.
* Used for **lambda expressions**.
* Compile-time safety annotation.

```java
@FunctionalInterface
interface MyFunc {
    void run();
}
```

---

### **184. How do you process annotations at runtime?**

* Use **Reflection API**.
* Works only with `RUNTIME` retention.
* Frameworks like Spring use this heavily.

```java
Class<?> c = MyClass.class;
if (c.isAnnotationPresent(Info.class)) {
    System.out.println("Annotation found");
}
```

---

### **185. What is annotation processing?**

* Processing annotations at **compile time**.
* Uses **Annotation Processor API**.
* Generates code or validates rules.
* Common in Lombok, MapStruct.

```java
@SupportedAnnotationTypes("MyAnnotation")
public class MyProcessor extends AbstractProcessor {}
```

---



### **186. What is reflection in Java?**

* Reflection is a Java feature that lets you **inspect and use classes, methods, fields, and constructors at runtime**
* You can work with classes **even if you don’t know them at compile time**
* Commonly used in **frameworks like Spring, Hibernate, JUnit**

```java
Class<?> clazz = Class.forName("java.lang.String");
System.out.println(clazz.getMethods().length);
```

---

### **187. How do you get a Class object?**

* There are **three main ways** to get a `Class` object
* Use `.class`, `getClass()`, or `Class.forName()`
* `Class.forName()` also **loads the class**

```java
Class<String> c1 = String.class;
Class<?> c2 = "hello".getClass();
Class<?> c3 = Class.forName("java.lang.String");
```

---

### **188. How do you create objects using reflection?**

* Use a **Constructor object**
* First get the class, then the constructor, then call `newInstance()`
* Useful when class name is dynamic

```java
Class<?> clazz = Class.forName("java.lang.String");
Object obj = clazz.getConstructor(String.class).newInstance("Hello");
```

---

### **189. How do you invoke methods using reflection?**

* Get the `Method` object using `getMethod()` or `getDeclaredMethod()`
* Call `invoke()` on it
* You can pass arguments dynamically

```java
Method m = String.class.getMethod("toUpperCase");
Object result = m.invoke("hello");
System.out.println(result);
```

---

### **190. How do you access private fields using reflection?**

* Use `getDeclaredField()`
* Call `setAccessible(true)` to bypass access checks
* This should be used carefully

```java
Field field = MyClass.class.getDeclaredField("name");
field.setAccessible(true);
String value = (String) field.get(obj);
```

---

### **191. What are the performance implications of reflection?**

* Reflection is **slower than normal method calls**
* JVM optimizations like inlining don’t work well
* Should **not be used in performance-critical code**

```java
// Slower than direct call
method.invoke(obj);
```

---

### **192. When should you use reflection?**

* When behavior needs to be **dynamic**
* In **frameworks, plugins, dependency injection**
* Avoid using it in regular business logic

```java
// Example: loading class dynamically
Class<?> plugin = Class.forName(pluginName);
```

---

### **193. What are the security implications of reflection?**

* Reflection can **break encapsulation**
* It can access private data and methods
* SecurityManager (older Java) could restrict this

```java
field.setAccessible(true); // Can expose sensitive data
```

---

### **194. Difference between Class.forName() and ClassLoader.loadClass()?**

* `Class.forName()` **loads and initializes** the class
* `loadClass()` **only loads**, does not initialize
* Initialization means static blocks run

```java
Class.forName("MyClass");           // Loads + initializes
classLoader.loadClass("MyClass");   // Loads only
```

---

### **195. How do you handle exceptions in reflection?**

* Reflection throws **checked exceptions**
* Always use **try-catch**
* Common ones: `ClassNotFoundException`, `NoSuchMethodException`

```java
try {
    Class<?> c = Class.forName("Test");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
```

---

## Lambda Expressions and Streams
## **196. What are lambda expressions?**

* Lambda expressions are a **shorter way to write implementations of functional interfaces**.
* They remove **boilerplate code** like anonymous classes.
* Introduced in **Java 8** to support **functional programming**.
* Mainly used with **streams, collections, and callbacks**.

```java
// Without lambda
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// With lambda
Runnable r2 = () -> System.out.println("Hello");
```

---

## **197. What is the syntax of lambda expressions?**

* Syntax has **three parts**:

  * Parameters
  * Arrow operator `->`
  * Body
* Parentheses are optional for **single parameter**.
* Curly braces optional for **single-line body**.

```java
// No parameter
() -> System.out.println("Hi");

// One parameter
x -> x * 2

// Multiple parameters
(a, b) -> a + b
```

---

## **198. What are functional interfaces?**

* A functional interface has **exactly one abstract method**.
* It can have **default and static methods**.
* Required for **lambda expressions**.
* Marked with `@FunctionalInterface` (optional but recommended).

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

Calculator c = (a, b) -> a + b;
```

---

## **199. What are method references?**

* Method references are a **shorter form of lambda expressions**.
* Used when lambda **just calls an existing method**.
* Improves **readability**.

Types:

* Static method
* Instance method
* Constructor

```java
// Lambda
list.forEach(name -> System.out.println(name));

// Method reference
list.forEach(System.out::println);
```

---

## **200. What are constructor references?**

* Constructor references are used to **create objects using lambda style**.
* Represented using `ClassName::new`.
* Used with functional interfaces like `Supplier`.

```java
Supplier<List<String>> s1 = () -> new ArrayList<>();

// Constructor reference
Supplier<List<String>> s2 = ArrayList::new;
```

---

## **201. Difference between lambda and anonymous class?**

* Lambda is **shorter and cleaner**.
* Lambda works only with **functional interfaces**.
* Anonymous class can have **multiple methods**.
* Lambda does **not create a new scope** for `this`.

```java
// Anonymous class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Run");
    }
};

// Lambda
Runnable r2 = () -> System.out.println("Run");
```

---

## **202. What are the built-in functional interfaces?**

* Java provides functional interfaces in `java.util.function`.
* Commonly used ones:

  * `Predicate`
  * `Function`
  * `Consumer`
  * `Supplier`
* Avoids creating **custom interfaces**.

```java
Predicate<Integer> p = x -> x > 10;
Function<Integer, String> f = x -> "Value: " + x;
Consumer<String> c = s -> System.out.println(s);
```

---

## **203. What is Predicate interface?**

* `Predicate<T>` is used to **test a condition**.
* Returns **boolean**.
* Commonly used in **filtering streams**.
* Method: `test()`.

```java
Predicate<Integer> isEven = n -> n % 2 == 0;

System.out.println(isEven.test(10)); // true
```

---

## **204. What is Function interface?**

* `Function<T, R>` takes **one input** and returns **one output**.
* Used for **transforming data**.
* Method: `apply()`.

```java
Function<Integer, String> convert = n -> "Number: " + n;

System.out.println(convert.apply(5));
```

---

## **205. What is Consumer interface?**

* `Consumer<T>` takes input but **returns nothing**.
* Used for **printing, logging, saving data**.
* Method: `accept()`.

```java
Consumer<String> print = s -> System.out.println(s);

print.accept("Hello Java");
```

---

### Streams API
### 206. What is Stream API?

* Stream API, introduced in Java 8, is a way to process collections of data **declaratively**.
* It lets you perform operations like **filter, map, reduce, collect** without writing loops.
* Example:

```java
List<Integer> nums = List.of(1,2,3,4,5);
nums.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
```

* Prints: `2 4`

---

### 207. What is the difference between Collection and Stream?

* **Collection** stores data, **Stream** processes data.
* Collection: **eager**, Stream: **lazy** evaluation.
* Collection can be reused, Stream **cannot**; once consumed, it’s done.

```java
List<String> list = List.of("a","b");
Stream<String> stream = list.stream(); // can’t reuse stream after terminal operation
```

---

### 208. What are intermediate and terminal operations?

* **Intermediate operations**: Transform streams, return another stream, **lazy** (e.g., `map()`, `filter()`).
* **Terminal operations**: Produce result or side-effect, trigger processing (e.g., `collect()`, `forEach()`).

```java
List<Integer> nums = List.of(1,2,3);
nums.stream().filter(n -> n>1).forEach(System.out::println);
```

* `filter()` → intermediate, `forEach()` → terminal

---

### 209. What is the difference between map() and flatMap()?

* `map()` → **1:1 mapping**, transforms each element to another object.
* `flatMap()` → **1:many mapping**, flattens nested structures into a single stream.

```java
List<List<Integer>> list = List.of(List.of(1,2), List.of(3,4));
list.stream().flatMap(List::stream).forEach(System.out::println); // 1 2 3 4
```

---

### 210. What is the difference between filter() and map()?

* `filter()` → **selects elements**, returns only those matching a condition.
* `map()` → **transforms elements**, changes each element into something else.

```java
List<Integer> nums = List.of(1,2,3);
nums.stream().filter(n -> n>1).map(n -> n*10).forEach(System.out::println); // 20 30
```

---

### 211. What is parallel stream?

* A **parallel stream** splits the data into multiple chunks and processes them in **different threads**.
* Useful for speeding up CPU-intensive operations.

```java
List<Integer> nums = List.of(1,2,3,4,5);
nums.parallelStream().forEach(System.out::println);
```

---

### 212. When should you use parallel streams?

* Use parallel streams for **large collections** and **CPU-bound tasks**.
* Avoid for **small collections** or operations with side-effects or order-dependency.

---

### 213. What is the difference between findFirst() and findAny()?

* `findFirst()` → returns the **first element** of the stream (useful in ordered streams).
* `findAny()` → returns **any element**, faster in parallel streams since it can pick whichever is ready.

```java
Optional<Integer> first = nums.stream().findFirst();
Optional<Integer> any = nums.parallelStream().findAny();
```

---

### 214. What is Optional class?

* `Optional` is a container that may or may not contain a value, helps **avoid null pointer exceptions**.

```java
Optional<String> opt = Optional.ofNullable(null);
System.out.println(opt.isPresent()); // false
```

---

### 215. How do you handle null values with Optional?

* Use `ofNullable()` to wrap potentially null values.
* Methods: `isPresent()`, `orElse()`, `orElseGet()`, `ifPresent()`.

```java
String name = null;
String result = Optional.ofNullable(name).orElse("Unknown");
System.out.println(result); // Unknown
```

---


### Advanced JDBC
### **216. What is JDBC?**

* JDBC stands for **Java Database Connectivity**
* It is an API that allows Java applications to **connect and interact with databases**
* It lets us execute SQL queries, retrieve results, and update data
* JDBC is database-independent; only the driver changes

```java
Connection con = DriverManager.getConnection(url, user, pass);
```

---

### **217. Steps to connect to a database using JDBC**

* Load the JDBC driver
* Create a database connection
* Create a statement
* Execute SQL query
* Process the result
* Close the connection

```java
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection(url, user, pass);
```

---

### **218. What are JDBC drivers?**

* JDBC drivers are **software components** that enable Java to communicate with a database
* They translate Java JDBC calls into database-specific calls
* Each database vendor provides its own driver

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

---

### **219. Types of JDBC drivers**

* **Type 1:** JDBC-ODBC Bridge (deprecated)
* **Type 2:** Native API driver
* **Type 3:** Network Protocol driver
* **Type 4:** Thin driver (most commonly used)

```text
Type 4 → Pure Java → Best performance
```

---

### **220. What is Connection interface?**

* `Connection` represents a **session between Java and the database**
* It is used to create statements and manage transactions
* Comes from `java.sql` package

```java
Connection con = DriverManager.getConnection(url, user, pass);
```

---

### **221. What is Statement interface?**

* `Statement` is used to **execute simple SQL queries**
* It is suitable when query does not change
* Not safe from SQL injection

```java
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

---

### **222. What is PreparedStatement?**

* `PreparedStatement` is used for **parameterized queries**
* It is faster and more secure than `Statement`
* Prevents SQL injection

```java
PreparedStatement ps = con.prepareStatement(
    "SELECT * FROM users WHERE id = ?");
ps.setInt(1, 1);
```

---

### **223. What is CallableStatement?**

* `CallableStatement` is used to **call stored procedures**
* It supports IN, OUT, and INOUT parameters
* Used for complex database logic

```java
CallableStatement cs = con.prepareCall("{call getUser(?)}");
cs.setInt(1, 1);
```

---

### **224. Difference between Statement and PreparedStatement**

* `Statement` uses **static SQL queries**
* `PreparedStatement` uses **dynamic and parameterized queries**
* PreparedStatement is **faster, safer, and reusable**

```java
// Statement
stmt.executeQuery("SELECT * FROM users");

// PreparedStatement
ps.setInt(1, 1);
ps.executeQuery();
```

---

### **225. What is ResultSet?**

* `ResultSet` stores the **data returned from a SQL query**
* It acts like a cursor pointing to rows
* Used to read data row by row

```java
while (rs.next()) {
    System.out.println(rs.getString("name"));
}
```

---


## Design Patterns

### Creational Patterns
## **226. What is connection pooling?**

* Connection pooling means **reusing database connections** instead of creating a new one every time.
* Creating a DB connection is **expensive**, so pooling improves **performance**.
* Connections are **created once**, stored in a pool, and reused.
* Common tools: **HikariCP, Apache DBCP, C3P0**.

```java
DataSource ds = new HikariDataSource();
Connection con = ds.getConnection();
```

---

## **227. What is transaction management in JDBC?**

* Transaction management ensures **all operations succeed or none do**.
* By default, JDBC uses **auto-commit mode**.
* We disable auto-commit to **control commit and rollback** manually.
* Used for **data consistency**.

```java
con.setAutoCommit(false);

try {
    stmt.executeUpdate(sql1);
    stmt.executeUpdate(sql2);
    con.commit();
} catch (Exception e) {
    con.rollback();
}
```

---

## **228. What is batch processing in JDBC?**

* Batch processing allows **multiple SQL statements to be executed together**.
* Reduces **database round trips**, improving performance.
* Mainly used for **bulk insert/update**.

```java
PreparedStatement ps =
    con.prepareStatement("INSERT INTO emp VALUES (?, ?)");

ps.setInt(1, 1);
ps.setString(2, "John");
ps.addBatch();

ps.setInt(1, 2);
ps.setString(2, "Alex");
ps.addBatch();

ps.executeBatch();
```

---

## **229. What is SQL injection and how to prevent it?**

* SQL injection is a **security attack** where malicious SQL is injected via input.
* Happens when queries are built using **string concatenation**.
* Prevented by using **PreparedStatement**.
* Also validate inputs and avoid dynamic SQL.

```java
PreparedStatement ps =
    con.prepareStatement("SELECT * FROM users WHERE id = ?");
ps.setInt(1, userId);
```

---

## **230. Difference between execute(), executeQuery(), and executeUpdate()?**

* `executeQuery()` → Used for **SELECT**, returns `ResultSet`.
* `executeUpdate()` → Used for **INSERT, UPDATE, DELETE**, returns row count.
* `execute()` → Used when SQL type is **unknown**, returns boolean.

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
int count = stmt.executeUpdate("DELETE FROM emp WHERE id=1");
stmt.execute("CREATE TABLE test(id INT)");
```

---

## **231. What is ResultSetMetaData?**

* `ResultSetMetaData` gives **information about columns** in a ResultSet.
* Used when **column details are not known at compile time**.
* Helpful for **dynamic reports**.

```java
ResultSetMetaData rsmd = rs.getMetaData();
int cols = rsmd.getColumnCount();
String name = rsmd.getColumnName(1);
```

---

## **232. What is DatabaseMetaData?**

* `DatabaseMetaData` provides **information about the database itself**.
* Includes DB name, version, tables, drivers, supported features.
* Useful for **database-independent applications**.

```java
DatabaseMetaData dbmd = con.getMetaData();
System.out.println(dbmd.getDatabaseProductName());
```

---

## **233. How do you handle large objects (LOB) in JDBC?**

* LOBs are **large data types** like `BLOB` (binary) and `CLOB` (text).
* Used for **images, videos, documents**.
* Handled using streams.

```java
// BLOB
PreparedStatement ps =
  con.prepareStatement("INSERT INTO files VALUES (?, ?)");

ps.setInt(1, 1);
ps.setBinaryStream(2, new FileInputStream("img.jpg"));
ps.executeUpdate();
```

---

## **234. What is scrollable ResultSet?**

* A scrollable ResultSet allows **moving forward and backward**.
* Default ResultSet is **forward-only**.
* Useful for **pagination and reports**.

```java
Statement stmt = con.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY
);

ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
rs.last();
rs.previous();
```

---

## **235. What is updatable ResultSet?**

* An updatable ResultSet allows **updating data directly** in the ResultSet.
* Changes are **reflected in the database**.
* Avoids writing separate UPDATE queries.

```java
Statement stmt = con.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE
);

ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
rs.next();
rs.updateString("name", "Rahul");
rs.updateRow();
```

---



### Behavioral Patterns
### 236. What are design patterns?

* Design patterns are **reusable solutions** to common software problems.
* They’re like templates for **writing clean, maintainable code**.
* Example: Singleton, Factory, Observer, etc.
* Think of them as **best practices** rather than exact code.

---

### 237. What is Singleton pattern?

* Ensures a **class has only one instance** and provides a **global access point**.

```java
class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if(instance == null) instance = new Singleton();
        return instance;
    }
}
```

---

### 238. How do you implement thread-safe Singleton?

* Use **synchronized**, **eager initialization**, or **double-checked locking**.

```java
class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if(instance == null) {
            synchronized(Singleton.class) {
                if(instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
```

---

### 239. What is Factory pattern?

* Factory pattern **creates objects without exposing the creation logic**.
* Lets you **instantiate classes through a common interface**.

```java
interface Shape { void draw(); }
class Circle implements Shape { public void draw() { System.out.println("Circle"); } }
class ShapeFactory {
    public Shape getShape(String type) {
        if(type.equals("circle")) return new Circle();
        return null;
    }
}
```

---

### 240. What is Abstract Factory pattern?

* Abstract Factory **creates families of related objects** without specifying concrete classes.
* Good for **cross-platform or UI components**.

```java
interface GUIFactory { Button createButton(); }
class MacFactory implements GUIFactory { public Button createButton(){ return new MacButton(); } }
```

---

### 241. What is Builder pattern?

* Builder pattern **constructs complex objects step by step**, great for objects with many optional fields.

```java
class Car {
    private String color; private int wheels;
    public static class Builder {
        private String color; private int wheels;
        public Builder setColor(String c){ color=c; return this; }
        public Builder setWheels(int w){ wheels=w; return this; }
        public Car build(){ return new Car(this); }
    }
    private Car(Builder b){ color=b.color; wheels=b.wheels; }
}
Car car = new Car.Builder().setColor("Red").setWheels(4).build();
```

---

### 242. What is Prototype pattern?

* Prototype pattern **clones existing objects** instead of creating new ones.
* Useful when object creation is **expensive**.

```java
class Person implements Cloneable {
    String name;
    public Person clone() throws CloneNotSupportedException { return (Person) super.clone(); }
}
```

---

### 243. When would you use each creational pattern?

* **Singleton:** Single instance needed (config, logger).
* **Factory:** Simple object creation with interface.
* **Abstract Factory:** Families of objects, cross-platform UI.
* **Builder:** Complex object construction with optional fields.
* **Prototype:** Copying objects efficiently.

---

### 244. What are the pros and cons of Singleton pattern?

* **Pros:** Controlled instance, global access, lazy initialization possible.
* **Cons:** Hard to test, can hide dependencies, multi-thread issues if not implemented carefully.

---

### 245. How do you break Singleton pattern?

* **Reflection:** Can call private constructor.
* **Serialization:** Can create a new instance during deserialization.
* **Cloning:** If `clone()` is not handled, you can copy the instance.

---



