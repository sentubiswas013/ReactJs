# ✅ 01. Java Basic Concepts 

## 1. What is Java and what are its key features?

Java is a high-level, object-oriented programming language designed for platform independence and enterprise application development. It's known for its "write once, run anywhere" philosophy.

**Key Features:**
- **Platform Independent:** Runs on any system with JVM
- **Object-Oriented:** Based on objects and classes
- **Simple and Secure:** Easy syntax, built-in security features
- **Robust:** Strong memory management and exception handling
- **Multithreaded:** Supports concurrent programming
- **Automatic Memory Management:** Garbage collection handles memory

Java compiles to bytecode that runs on the Java Virtual Machine, making it portable across different operating systems.

## 2. Explain the difference between JDK, JRE, and JVM.

These are the three main components of the Java platform:

* **JVM (Java Virtual Machine)**: Executes Java **bytecode** and provides platform independence.
* **JRE (Java Runtime Environment)**: Contains the **JVM + libraries** required to **run** Java applications.
* **JDK (Java Development Kit)**: Contains the **JRE + development tools** required to **develop, compile, and run** Java applications.

**Simple Interview Answer**

> **JDK is used to develop Java applications, JRE is used to run them, and JVM is the engine that actually executes the Java bytecode. In short, JDK = JRE + development tools, and JRE = JVM + libraries.**

**Relationship**

```text
JDK
 ├── JRE
 │    ├── JVM
 │    └── Java Class Libraries
 └── Development Tools (javac, java, javadoc, jar, etc.)
```

**Key Features**

| **Component** | **Purpose**                      | **Contains**                                                     | **Used By**           |
| ------------- | -------------------------------- | ---------------------------------------------------------------- | --------------------- |
| **JVM**       | Executes bytecode                | Class Loader, Memory Management, Garbage Collector, JIT Compiler | Running Java programs |
| **JRE**       | Provides runtime environment     | JVM + Java libraries                                             | End users             |
| **JDK**       | Provides development environment | JRE + Compiler + Development tools                               | Java developers       |

**How It Works**

1. Developer writes Java code (`.java` file).
2. **JDK** uses the **`javac` compiler** to convert it into **bytecode** (`.class` file).
3. **JRE** provides the runtime environment to start the application.
4. **JVM** loads the bytecode, converts it into **machine code** using the **JIT (Just-In-Time) Compiler**, and executes it.

**Why to Use**

* **JVM**: Enables **"Write Once, Run Anywhere (WORA)"** by running the same bytecode on any operating system.
* **JRE**: Allows users to execute Java applications without needing development tools.
* **JDK**: Provides everything needed for Java application development.

**When to Use**

* Use **JDK** when you are **developing or compiling** Java applications.
* Use **JRE** when you only need to **run** Java applications.
* **JVM** works internally whenever a Java program is executed.

**Common JDK Tools**

| **Tool**      | **Purpose**                                |
| ------------- | ------------------------------------------ |
| **`javac`**   | Compiles `.java` files into `.class` files |
| **`java`**    | Launches and runs Java applications        |
| **`jar`**     | Creates and manages JAR files              |
| **`javadoc`** | Generates API documentation                |
| **`jdb`**     | Java debugger                              |

**Code Example**

```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

**Compilation and Execution**

```bash
javac HelloWorld.java   // JDK compiler creates HelloWorld.class
java HelloWorld         // JRE starts JVM, JVM executes bytecode
```

**Easy Way to Remember**

* **JDK = Develop + Run**
* **JRE = Run Only**
* **JVM = Execute Bytecode**

```
JDK = JRE + Development Tools (javac, javadoc, jar, etc.)
JRE = JVM + Core Libraries (java.lang, java.util, etc.)
JVM = Runtime execution environment
```

**JVM Architecture  Diagram** 

```text
        Class Loader
             ↓
     Runtime Data Areas
   ------------------------
   Heap | Stack | Method Area
   ------------------------
             ↓
     Execution Engine
   (Interpreter + JIT)
             ↓
      Garbage Collector
```

## 3. What is the difference between Structured Programming and OOP?

**Structured Programming**

Structured Programming is a programming approach where the program is divided into small, manageable functions or procedures.
It mainly focuses on **step-by-step execution of logic**.

**Main Features**

* Uses:

  * sequence
  * selection (`if`, `switch`)
  * iteration (`for`, `while`)
* Program is divided into methods/functions
* Top-down approach
* Data and functions are separate


```java
public class Calculator {

    static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int result = add(10, 20);
        System.out.println(result);
    }
}
```

Here:

* Logic is divided into functions
* Execution happens step by step

**Advantages**

* Easy to understand
* Easy debugging
* Reduces duplicate code
* Better readability

**Disadvantages**

* Difficult to manage large applications
* Data security is weak
* Code reusability is limited


**OOP (Object-Oriented Programming)**

OOP is a programming paradigm based on **objects and classes**.
It combines **data + behavior** into a single unit called an object.

**Main Concepts of OOP**

1. **Class**

   * Blueprint of object

2. **Object**

   * Instance of class

3. **Encapsulation**

   * Wrapping data and methods together

4. **Inheritance**

   * One class acquires properties of another class

5. **Polymorphism**

   * One method behaves differently

6. **Abstraction**

   * Hiding implementation details


```java
class Car {

    String brand;

    void start() {
        System.out.println(brand + " Car Started");
    }
}

public class Main {
    public static void main(String[] args) {

        Car c1 = new Car();
        c1.brand = "BMW";

        c1.start();
    }
}
```

Here:

* `Car` → class
* `c1` → object
* Data + methods together → OOP


**Structured Programming vs OOP**

| Feature           | Structured Programming | OOP                |
| ----------------- | ---------------------- | ------------------ |
| Focus             | Functions              | Objects            |
| Approach          | Top-down               | Bottom-up          |
| Data Security     | Less                   | High               |
| Reusability       | Limited                | High               |
| Best For          | Small applications     | Large applications |
| Example Languages | C, Pascal              | Java, C++, Python  |


## 3. What are the main principles of Object-Oriented Programming?

**Object-Oriented Programming** is based on four fundamental principles that promote code reusability, maintainability, and modularity.

**Four OOP Principles:**

* **Encapsulation:** Encapsulation means binding data and methods together and protecting data using access modifiers (`private`, `protected`, `public`) and access using getters and setters.
* **Inheritance:** Inheritance means a child class can use properties and methods of a parent class using `extends`.
* **Polymorphism:** Polymorphism means one method can perform different actions using overloading or overriding.
* **Abstraction:** Abstraction means hiding internal implementation and showing only necessary details using abstract class or interface.


- **1. Encapsulation:** Data is `private` and accessed using `getter and setter methods`.
👉 Hide data and control access. Keep data safe and access it through methods

```java
class Person {
    private String name;   // private variable

    public void setName(String name) {   // setter
        this.name = name;
    }

    public String getName() {   // getter
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("John");
        System.out.println(p.getName());
    }
}
```

- **2. Inheritance:** Child class `inherits properties from parent class` using `extends`.
👉 Reuse parent properties in child. Child gets features from parent

```java
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // inherited method
        d.bark();
    }
}
```

- **3. Polymorphism:** Method Overloading
👉 Same method, different behavior. One thing, many forms
```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.add(5, 10));
        System.out.println(c.add(5, 10, 15));
    }
}
```

- **4. Abstraction** abstract class or interface
👉 Hide complexity, show only needed parts. Show what is needed, hide the rest.
```java
abstract class Vehicle {
    abstract void start();   // abstract method
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car starts with key");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();
    }
}
```

## 4. What is a package in Java? 
In Java, a **package** is a **namespace that groups related classes, interfaces, and sub-packages together**. It helps organize code, avoid naming conflicts, and control access to classes.

```java
package com.example.utils;

public class Helper {
    public static void printHello() {
        System.out.println("Hello!");
    }
}
```

## 5. Normal, final, static, static final, volatile, abstract, transient, Serializable?

**Normal Variable** Declared inside a class but outside methods.
```java
class Student {
    int age = 20;   // normal variable
}
```

**final** Once assigned, value cannot be changed.
```java
class Student {
    final int age = 20;
    age = 10;  // allowed only once
}
```

**static** Belongs to class, not object. Only one copy in memory.
```java
class Student {
    static String college = "IIT";
}
```

**static final** Variable (Constant) in Class-level
```java
class Student {
    static final double PI = 3.14;
}
```

**volatile** is a keyword used in multithreading. it ensures variable changes are immediately visible to all threads (prevents caching issues)
```java
class Test {
    volatile boolean flag = true;
}
```

**Abstract** Abstract is only for: Classes and  Methods
```java
abstract class Animal {
    abstract void sound();
}
```

**transient** Used for serialization and but not saved. Used for sensitive data.
```java
public class Student implements Serializable { 
    private String username;
    private transient String password; // If you do NOT want a field to serialize:
    // `transient` is a serialization keyword in Java. password will NOT be saved.
}
```

**Serializable** is a marker interface in Java used to convert an object into a byte stream so that it can be stored, transferred, or reconstructed later.

**Serialize Object**
```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee(101, "John");
        FileOutputStream file = new FileOutputStream("employee.txt");

        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(emp);

        out.close();
        file.close();

        System.out.println("Object Serialized");
    }
}
```

**Deserialize** is the reverse process of converting the Byte Stream back into a Java Object.

```java
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadData {
    public static void main(String[] args) throws Exception {

        FileInputStream file = new FileInputStream("employee.txt");

        ObjectInputStream in = new ObjectInputStream(file);
        Employee emp = (Employee) in.readObject();

        System.out.println(emp.id);
        System.out.println(emp.name);

        in.close();
        file.close();
    }
}
```



**Difference between `transient` and `volatile`**

| Feature       | `transient`                                      | `volatile`                                          |
|---------------|--------------------------------------------------|-----------------------------------------------------|
| Purpose       | Excludes field from **serialization**            | Ensures **visibility** of field across threads      |
| Context       | Used with Java Serialization (`Serializable`)    | Used in multi-threaded programming                  |
| Effect        | Field is skipped when object is serialized       | Field is always read/written from main memory       |
| Thread safety | No effect on threads                             | Provides visibility guarantee (not atomicity)       |


## 6. What is Instance, Static, Abstract, and Final Methods?

**Instance Method:** A method that belongs to an object and is called using an instance of the class.
```java
class Student {
    void study() {   // Instance method
        System.out.println("Studying...");
    }
}

Student s = new Student();
s.study();   // Called using object
```

**Static Method:** A method that belongs to the class and is called using the class name.
```java
class Student {
    static void schoolName() {   // Static method
        System.out.println("ABC School");
    }
}

Student.schoolName();   // Called using class name
```

**Abstract Method:** A method declared without a body that must be implemented by a subclass.
```java
abstract class Animal {
    abstract void sound();   // Abstract method
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}
```

**Final Method:** A method that cannot be overridden by a subclass.
```java
class Animal {
    final void breathe() {   // Final method
        System.out.println("Breathing...");
    }
}

class Dog extends Animal {
    // void breathe() {}  ❌ Not allowed (Compile-time error)
}
```

## 7. How do you read user input from the console in Java?
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("You entered: " + input);

        scanner.close();
    }
}
```

## 8. How do you iterate through a collection in Java?
```java
// Example using for-each loop:
List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
for (String fruit : fruits) {
   System.out.println(fruit);
}
// Example using regular for loop:
for (int i = 0; i < fruits.size(); i++) {
   System.out.println(fruits.get(i));
}
```



# ✅ 02. Java Data Types and Variables

## 1. What are data types in Java?

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

## 2. What is the difference between primitive and non primitive(reference types)?
**Primitive types** in Java, like int, double, and boolean, store actual values in memory and are stored on the stack. They are fast and have a fixed size.

**Reference types**, like objects, arrays, and strings, store a reference or memory address pointing to the actual data in the heap. They can have methods, support polymorphism, and are generally more flexible but slightly slower.

```java
int x = 10;        // primitive - stores value 10
String name = "John"; // reference - stores address to "John" object
```
## 3. What is autoboxing and unboxing and Casting?

**Autoboxing** is the automatic conversion of a **primitive type** into its corresponding **wrapper class**.
  ```java
  int num = 10;
  Integer numObj = num;  // Autoboxing: int → Integer
  ```

**Unboxing** is the automatic conversion of a **wrapper class** back to its **primitive type**.
  ```java
  Integer numObj = 20;
  int num = numObj;  // Unboxing: Integer → int
  ```

**Casting** in Java means **converting one data type into another data type**.
```java
double a = 10.5;
int b = (int) a;  // Manual casting
System.out.println(b); // 10
```

## 4. What is the difference between == and equals() method?

**`==` operator:**
The `==` operator is used to compare **references** of two objects. It checks whether both variables point to the **same memory location**. For primitive data types, it compares the **actual values**.

**`equals()` method:**
The `equals()` method is used to compare the **content or logical value** of two objects. It is defined in the `Object` class and can be **overridden** by classes (like `String`) to provide meaningful value-based comparison.


**Examples:**

```java
// Using ==
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2); // false, different objects

// Using equals()
System.out.println(s1.equals(s2)); // true, content is the same

// With primitives
int a = 5, b = 5;
System.out.println(a == b); // true, values are equal
```

## 5. What is the difference between String, StringBuilder, and StringBuffer?

All three are used to handle **text (sequence of characters)** in Java, but they differ in **mutability**, **performance**, and **thread safety**.

* **String**: **Immutable** (cannot be changed after creation).
* **StringBuilder**: **Mutable** and **not thread-safe**.
* **StringBuffer**: **Mutable** and **thread-safe** because its methods are **synchronized**.

**Simple Interview Answer**

> **`String` is immutable, so every modification creates a new object. `StringBuilder` and `StringBuffer` are mutable, so they modify the same object. `StringBuilder` is faster but not thread-safe, while `StringBuffer` is thread-safe but slightly slower due to synchronization.**

**Key Differences**

| **Feature**       | **String**                         | **StringBuilder**                   | **StringBuffer**                   |
| ----------------- | ---------------------------------- | ----------------------------------- | ---------------------------------- |
| **Mutability**    | Immutable                          | Mutable                             | Mutable                            |
| **Thread Safety** | Yes (because immutable)            | No                                  | Yes (synchronized)                 |
| **Performance**   | Slow for frequent modifications    | Fastest                             | Slower than `StringBuilder`        |
| **Memory Usage**  | Creates new object on every change | Reuses same object                  | Reuses same object                 |
| **Use Case**      | Fixed text                         | Single-threaded string manipulation | Multi-threaded string manipulation |

**How It Works**

* **String**: Every operation like `+` or `concat()` creates a **new object** in memory.
* **StringBuilder**: Methods like `append()` and `insert()` modify the **same object**, avoiding unnecessary object creation.
* **StringBuffer**: Works like `StringBuilder`, but methods are **synchronized**, making it safe for multiple threads.

**Why to Use**

* Use **`String`** when the value should not change and for **constant or fixed data**.
* Use **`StringBuilder`** for **frequent string modifications** in a **single-threaded** environment.
* Use **`StringBuffer`** for **frequent string modifications** in a **multi-threaded** environment where thread safety is required.

**When to Use**

| **Scenario**                                             | **Best Choice**   |
| -------------------------------------------------------- | ----------------- |
| Fixed message or constant value                          | **String**        |
| Building SQL queries, loops, or large text in one thread | **StringBuilder** |
| Shared string object modified by multiple threads        | **StringBuffer**  |

**Code Example**

```java
// String (Immutable)
String str = "Hello";
str = str + " World";   // Creates a new object
System.out.println(str);

// StringBuilder (Mutable, Fast)
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");    // Modifies same object
System.out.println(sb);

// StringBuffer (Mutable, Thread-safe)
StringBuffer sf = new StringBuffer("Hello");
sf.append(" World");    // Modifies same object with synchronization
System.out.println(sf);
```

**Performance Example**

```java
// Not efficient for repeated updates
String result = "";
for (int i = 0; i < 5; i++) {
    result += i;
}

// Efficient for repeated updates
StringBuilder builder = new StringBuilder();
for (int i = 0; i < 5; i++) {
    builder.append(i);
}
System.out.println(builder);
```

**Easy Way to Remember**

* **String = Immutable + Safe + Slow for updates**
* **StringBuilder = Mutable + Fast + Single Thread**
* **StringBuffer = Mutable + Thread-Safe + Multi Thread**


## 6. What strings is immutable in Java?

**Definition**

A **`String`** in Java is **immutable**, which means **once a `String` object is created, its value cannot be changed**. If you modify a string, Java creates a **new object** instead of changing the existing one.

**How It Works**

When you modify a string, Java does not update the existing object. Instead, it creates a **new object** with the updated value.

```java
String str = "Hello";
str = str + " World";
```

**Internally:**

1. `"Hello"` object is created.
2. `str + " World"` creates a **new object** `"Hello World"`.
3. The variable `str` now points to the new object.

**Key Features**

* **Immutable**: Value cannot be changed after creation.
* **Thread-Safe**: Multiple threads can safely share the same string object.
* **Memory Efficient**: Supports the **String Pool**, where identical string literals share the same object.
* **Secure**: Used for sensitive data like **class names, file paths, and URLs** because values cannot be altered.

**Why is String Immutable?**

1. **Security**

   * Prevents accidental or malicious modification of important values such as file paths, network connections, and class loading information.

2. **Thread Safety**

   * Since the object never changes, multiple threads can use the same string without synchronization.

3. **String Pool Optimization**

   * Java stores string literals in the **String Pool** and reuses them, saving memory.

4. **Caching**

   * Methods like `hashCode()` can cache their result because the string value never changes.

**When to Use**

* Use **`String`** for **fixed or constant text**.
* Use **`StringBuilder`** or **`StringBuffer`** when frequent modifications are required.

**Code Example**

```java
String s1 = "Java";
String s2 = s1.concat(" Programming");

System.out.println(s1); // Java
System.out.println(s2); // Java Programming
```

In the above example, `concat()` does **not** change `s1`. It creates a **new object** and stores it in `s2`.

**String Pool Example**

```java
String a = "Hello";
String b = "Hello";

System.out.println(a == b); // true
```

Both `a` and `b` point to the **same object** in the **String Pool**, which is possible because strings are immutable.

**Easy Way to Remember**

* **Immutable = Cannot Change**
* **Any modification = New Object Created**
* **Original String Always Remains Unchanged**


## 7. What is the difference between final, finally, and finalize?

**Definition**

Although they sound similar, **`final`**, **`finally`**, and **`finalize()`** are completely different concepts in Java.

* **`final`**: A **keyword** used to restrict modification.
* **`finally`**: A **block** used with exception handling to execute code regardless of whether an exception occurs.
* **`finalize()`**: A **method** that was called by the **Garbage Collector** before object destruction. It is **deprecated** and should not be used in modern Java.


**Key Differences**

| **Feature**       | **`final`**                 | **`finally`**                   | **`finalize()`**                          |
| ----------------- | --------------------------- | ------------------------------- | ----------------------------------------- |
| **Type**          | Keyword                     | Block                           | Method                                    |
| **Purpose**       | Prevent modification        | Execute cleanup code            | Perform cleanup before object destruction |
| **Used With**     | Variables, methods, classes | `try-catch` blocks              | Objects and Garbage Collector             |
| **Execution**     | At compile time             | Always runs after `try`/`catch` | Called by GC (not guaranteed)             |
| **Current Usage** | Commonly used               | Commonly used                   | **Deprecated** (avoid using)              |

**How It Works**

**1. `final`**

* A **`final` variable** cannot be reassigned.
* A **`final` method** cannot be overridden.
* A **`final` class** cannot be inherited.

```java id="rzm81o"
final int age = 25;
// age = 30; // Compilation Error

final class Animal {
}

// class Dog extends Animal {} // Compilation Error
```

**2. `finally`**

* The `finally` block executes whether an exception occurs or not.
* Commonly used to close files, database connections, or release resources.

```java id="0oxh3i"
try {
    int result = 10 / 2;
} catch (Exception e) {
    System.out.println("Exception occurred");
} finally {
    System.out.println("Cleanup code always executes");
}
```

**3. `finalize()`**

* `finalize()` was invoked by the **Garbage Collector** before reclaiming an object's memory.
* Its execution was **not guaranteed**, and it caused performance and reliability issues.
* It is **deprecated from Java 9** and should be replaced with **`try-with-resources`** or explicit cleanup methods.

```java id="jlwmau"
class Test {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize called");
    }
}
```

**Why to Use**

* Use **`final`** to create **constants**, prevent method overriding, or stop class inheritance.
* Use **`finally`** to ensure important cleanup code always executes.
* Do **not** use **`finalize()`** in modern applications because it is deprecated.

**When to Use**

| **Scenario**                                  | **Use**                                                                           |
| --------------------------------------------- | --------------------------------------------------------------------------------- |
| Constant value or read-only variable          | **`final`**                                                                       |
| Prevent overriding or inheritance             | **`final`**                                                                       |
| Close files, sockets, or database connections | **`finally`**                                                                     |
| Object cleanup before GC                      | **Do not use `finalize()`; use `try-with-resources` or explicit cleanup instead** |

**Code Example**

```java id="1c9p5g"
public class Demo {

    final int MAX = 100;

    public static void main(String[] args) {
        try {
            System.out.println("Inside try block");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Easy Way to Remember**

* **`final` = Cannot Change**
* **`finally` = Always Executes**
* **`finalize()` = Garbage Collector Cleanup (Deprecated)**



## 8. What is string pooling in java?

**Definition**

**String Pooling** is a Java memory optimization technique where **string literals are stored in a special memory area called the String Pool**. If the same string value already exists in the pool, Java reuses the existing object instead of creating a new one.


**How It Works**

* When a string literal is created using double quotes (`""`), Java first checks the **String Pool**.
* If the value already exists, Java returns a reference to the existing object.
* If it does not exist, Java creates a new object and stores it in the pool.

```java id="4skp0r"
String s1 = "Java";
String s2 = "Java";
```

Here, only **one object** `"Java"` is created in the **String Pool**, and both `s1` and `s2` point to the same object.

**Key Features**

* **Stores only one copy** of identical string literals.
* **Reduces memory usage** by avoiding duplicate objects.
* **Improves performance** because object creation is minimized.
* Works because **`String` is immutable**.

**Why to Use**

* Saves **heap memory** by reusing existing string objects.
* Improves application **performance** by reducing object creation.
* Makes string comparison using `==` possible for pooled string literals.

**When to Use**

* Whenever you create strings using **string literals** (`"Hello"`), Java automatically uses the String Pool.
* For dynamically created strings using `new String()`, use **`intern()`** if you want to place them into the String Pool.

**Code Example**

```java id="cwlfzr"
String s1 = "Hello";
String s2 = "Hello";

System.out.println(s1 == s2); // true
```

Both variables point to the **same pooled object**, so `==` returns `true`.

**Using `new String()`**

```java id="b5q7to"
String s1 = "Hello";
String s2 = new String("Hello");

System.out.println(s1 == s2); // false
System.out.println(s1.equals(s2)); // true
```

Here:

* `s1` points to the object in the **String Pool**.
* `s2` points to a **new object** created in the heap.
* `==` compares **references**, while `equals()` compares **content**.

**Using `intern()`**

```java id="t0odqg"
String s1 = new String("Java");
String s2 = s1.intern();

System.out.println(s2 == "Java"); // true
```

The **`intern()`** method returns the reference from the **String Pool** if it exists, or adds the string to the pool if it does not.

**Easy Way to Remember**

* **String Literal (`"Java"`) = Uses String Pool**
* **`new String("Java")` = Creates New Object**
* **`intern()` = Moves or Returns String from the Pool**


## 9. What is coercion in Java?

**Coercion in Java** is the process of converting a value from one data type to another, either automatically by the compiler or manually by the programmer, to make data types compatible in expressions or assignments.


```java
int num = 10;
double result = num; // Automatic coercion - int to double

double d = 10.5;
int i = (int) d; // Explicit casting required - double to int
```

## 10. What is instance of in java

**`instanceof`** in Java is an **operator** used to check whether an object is an **instance of a specific class or interface**. it is introduce in Java 16+


```java
class Animal {}
class Dog extends Animal {}

Animal a = new Dog();

System.out.println(a instanceof Dog);    // true
System.out.println(a instanceof Animal); // true
```

## 11. What is Predicate in java?

**Definition**

A **`Predicate<T>`** is a **functional interface** introduced in **Java 8** in the `java.util.function` package. It takes **one input argument** and returns a **boolean (`true` or `false`)** result.

**Method Signature**

```java id="of0zyj"
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

**Key Features**

* **Functional Interface** with a single abstract method: **`test()`**.
* Takes **one input** and returns **`true` or `false`**.
* Works seamlessly with **Lambda Expressions** and the **Stream API**.
* Supports combining multiple conditions using **`and()`**, **`or()`**, and **`negate()`**.

**How It Works**

* You define a condition using a lambda expression.
* The **`test()`** method checks whether the input satisfies that condition.
* It returns **`true`** if the condition matches, otherwise **`false`**.

```java id="iq2it6"
Predicate<Integer> isEven = n -> n % 2 == 0;

System.out.println(isEven.test(4)); // true
System.out.println(isEven.test(5)); // false
```

**Why to Use**

* Makes code **cleaner and more readable**.
* Reduces the need for separate methods for simple conditions.
* Commonly used for **filtering collections**, **validating data**, and **conditional processing**.

**When to Use**

| **Scenario**                            | **Use `Predicate`?**           |
| --------------------------------------- | ------------------------------ |
| Filter a list using Stream API          | **Yes**                        |
| Validate user input                     | **Yes**                        |
| Check if an object matches a condition  | **Yes**                        |
| Perform calculations and return a value | **No, use `Function` instead** |

**Code Example**

```java id="f4xrfb"
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25);

        Predicate<Integer> isEven = n -> n % 2 == 0;

        numbers.stream()
               .filter(isEven)
               .forEach(System.out::println);
    }
}
```

**Output:**

```text id="0lfew3"
10
20
```

**Combining Predicates**

```java id="p07m4e"
Predicate<Integer> greaterThan10 = n -> n > 10;
Predicate<Integer> isEven = n -> n % 2 == 0;

Predicate<Integer> condition =
        greaterThan10.and(isEven);

System.out.println(condition.test(12)); // true
System.out.println(condition.test(9));  // false
```

**Common Methods**

| **Method**      | **Purpose**                          |
| --------------- | ------------------------------------ |
| **`test(T t)`** | Evaluates the condition              |
| **`and()`**     | Combines two predicates with **AND** |
| **`or()`**      | Combines two predicates with **OR**  |
| **`negate()`**  | Reverses the result                  |
| **`isEqual()`** | Checks object equality               |

**Easy Way to Remember**

* **`Predicate` = One Input + Boolean Output**
* **Used for Filtering and Validation**
* **Main Method = `test()`**


## 12. What are `equals()` and `hashCode()` in Java??

**Definition**

* **`equals()`** is a method from the `Object` class used to compare the **content or logical equality** of two objects.

* **`hashCode()`** is a method from the `Object` class that returns an **integer hash value** representing the object, mainly used in **hash-based collections** like `HashMap`, `HashSet`, and `Hashtable`.


**Key Features**

| **Feature**            | **`equals()`**          | **`hashCode()`**       |
| ---------------------- | ----------------------- | ---------------------- |
| **Purpose**            | Compares object content | Generates hash value   |
| **Return Type**        | `boolean`               | `int`                  |
| **Defined In**         | `Object` class          | `Object` class         |
| **Used By**            | Equality checking       | Hash-based collections |
| **Can Be Overridden?** | Yes                     | Yes                    |

**How It Works**

1. When an object is added to a **`HashMap`** or **`HashSet`**, Java first calls **`hashCode()`** to find the correct bucket.
2. If multiple objects have the same hash code, Java then calls **`equals()`** to compare their actual contents.
3. This combination provides **fast lookup** and **correct equality checking**.

**Contract Between `equals()` and `hashCode()`**

* If **two objects are equal (`equals()` returns `true`)**, they **must have the same `hashCode()`**.
* If two objects have the same `hashCode()`, they **may or may not be equal**.
* If you **override `equals()`**, you should also **override `hashCode()`**.

**Why to Use**

* To define **logical equality** instead of reference equality.
* To ensure objects work correctly in **`HashMap`**, **`HashSet`**, and other hash-based collections.
* To improve lookup performance using hash codes.

**When to Use**

| **Scenario**                        | **Use `equals()` and `hashCode()`?** |
| ----------------------------------- | ------------------------------------ |
| Comparing object data               | **Yes**                              |
| Using objects as `HashMap` keys     | **Yes**                              |
| Storing custom objects in `HashSet` | **Yes**                              |
| Comparing primitive values          | **No, use `==`**                     |

**Code Example**

```java id="n7z3ka"
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee emp = (Employee) obj;
        return id == emp.id && name.equals(emp.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name);
    }
}
```

**Usage Example**

```java id="jj71he"
Employee e1 = new Employee(1, "John");
Employee e2 = new Employee(1, "John");

System.out.println(e1.equals(e2));      // true
System.out.println(e1.hashCode());      // Same value
System.out.println(e2.hashCode());      // Same value
```

**What Happens in `HashMap`?**

```java id="3frbch"
Map<Employee, String> map = new HashMap<>();

map.put(new Employee(1, "John"), "Developer");

System.out.println(
    map.get(new Employee(1, "John"))
); // Developer
```

This works correctly because both **`equals()`** and **`hashCode()`** are overridden.

**Easy Way to Remember**

* **`equals()` = Compare Object Content**
* **`hashCode()` = Generate Hash Value**
* **Equal Objects = Same Hash Code**
* **Always override both together**


## 13. How to Override the hashCode Method Properly in Java?

**Definition**

The **`hashCode()`** method returns an **integer hash value** for an object. It is mainly used by **hash-based collections** like **`HashMap`**, **`HashSet`**, and **`Hashtable`** for fast storage and retrieval.


**Key Rules (hashCode Contract)**

1. If **`obj1.equals(obj2)` is `true`**, then **`obj1.hashCode() == obj2.hashCode()`**.
2. If two objects have the **same hash code**, they **may or may not be equal**.
3. If you **override `equals()`**, you **must also override `hashCode()`**.
4. Use only the **fields involved in `equals()`** when calculating the hash code.

**How It Works**

* When an object is inserted into a **`HashMap`** or **`HashSet`**, Java first calls **`hashCode()`** to find the bucket.
* If multiple objects have the same hash code, Java uses **`equals()`** to identify the correct object.

**Why to Use**

* Ensures correct behavior of **`HashMap`**, **`HashSet`**, and other hash-based collections.
* Improves lookup performance.
* Maintains consistency between **logical equality** and **hash values**.

**When to Use**

* Whenever you override **`equals()`**.
* When custom objects are used as **keys in a `HashMap`** or **elements in a `HashSet`**.

**Recommended Way (Using `Objects.hash()`)**

```java id="qv6svj"
import java.util.Objects;

class Employee {
    private int id;
    private String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;
        return id == other.id &&
               Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

**Manual Way (How `hashCode()` is Traditionally Written)**

```java id="tq95bw"
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + id;
    result = 31 * result + (name == null ? 0 : name.hashCode());
    return result;
}
```

**Why use `31`?**

* **31** is a prime number that helps generate a better distribution of hash values and reduces collisions.

**Example**

```java id="f2xepm"
Employee e1 = new Employee(101, "Alice");
Employee e2 = new Employee(101, "Alice");

System.out.println(e1.equals(e2));    // true
System.out.println(e1.hashCode());    // Same hash code
System.out.println(e2.hashCode());    // Same hash code
```

**Common Mistake**

```java id="7bmsx6"
class Employee {
    int id;

    @Override
    public boolean equals(Object obj) {
        return true;
    }
    // hashCode() not overridden
}
```

This breaks the **`equals()`-`hashCode()` contract** and causes incorrect behavior in **`HashMap`** and **`HashSet`**.

**Best Practices**

* **Always override `hashCode()` when overriding `equals()`**.
* Use **`Objects.hash()`** for simplicity and readability.
* Use only **immutable or stable fields** in hash code calculations.
* Do not use fields that frequently change after the object is added to a hash-based collection.

**Easy Way to Remember**

* **`equals()` = Compare Object Content**
* **`hashCode()` = Find Object Bucket**
* **Same Content = Same Hash Code**
* **Override Both Together**


## 14. What is `var` keyword in Java(10)?

**var** is a feature introduced in **Java 10** that allows **Local Variable Type Inference**, meaning the compiler automatically determines the variable's type from the assigned value.

**Key Features**

* **Reduces boilerplate code**.
* Type is inferred at **compile time**.
* Works only with **local variables**.
* Improves code readability when the type is obvious.
* Still maintains **strong typing**.

**How It Works**

The compiler looks at the value assigned to the variable and automatically determines its type.

```java
var name = "John";    // String
var age = 25;         // int
var salary = 5000.50; // double
```

The compiler internally treats them as:

```java
String name = "John";
int age = 25;
double salary = 5000.50;
```

**Why Use**

* Makes code shorter and cleaner.
* Useful when the type is long or obvious.
* Improves readability in loops and collections.

**When to Use**

* Local variables inside methods.
* Loop variables.
* Collection and Stream operations.
* When the variable type is clear from the assignment.

**Code Example**

```java
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        var name = "Java";
        var version = 21;

        List<String> languages =
                List.of("Java", "Python");

        for (var language : languages) {
            System.out.println(language);
        }
    }
}
```

**Limitations**

**var** cannot be used for:

* Instance variables.
* Static variables.
* Method parameters.
* Method return types.
* Variables without initialization.

```java
var x;        // Compilation Error

class Test {
    var name = "John"; // Compilation Error
}
```

## 15. Summary Data Types and Variables

**🔹 Data Types**

* **Data Type:** Specifies what type of value a variable can store.
* **Primitive Data Types:** Basic types that store actual values (int, float, char, boolean).
* **Non-Primitive Data Types:** Store references to objects (String, Arrays, Classes).
* **Type Casting:** Converting one data type to another (implicit/explicit).


**🔹 Variables**

* **Variable:** A named memory location used to store data.
* **Local Variable:** Declared inside a method and accessible only within it.
* **Instance Variable:** Declared inside a class, unique for each object.
* **Static Variable:** Shared among all objects of a class.
* **Final Variable:** Value cannot be changed once assigned (constant).


**🔹 Memory Concept**

* **Primitive Variables:** Stored directly in stack memory.
* **Reference Variables:** Stored in stack but point to objects in heap memory.


# ✅ 03. Java Classes and Objects


## 4. What is a class in Java?

A **class** in Java is a **blueprint or template** used to create **objects**, defining their **properties (fields)** and **behaviors (methods)**.

**Key Features**

* Acts as a **logical blueprint**
* Contains **variables (state)** and **methods (behavior)**
* Does not occupy memory until an **object is created**
* Supports **encapsulation**
* Can have **constructors, methods, and blocks**

**How it works**
A **class defines structure**, and when we create an object using **new keyword**, Java allocates memory and creates an **instance of that class**.

**Why to use**

* To model **real-world entities**
* To organize code into **reusable components**
* To support **OOP principles**

**When to use**

* When defining any **entity like Student, Car, Employee**
* When we need to create **multiple objects with same structure**

**Code Example**

```java id="1i6tx1"
class Student {   // Class
    String name;
    int age;

    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // Object of class
        s1.name = "Rahul";
        s1.age = 22;
        s1.display();
    }
}
```

## 2. What is an object?

An **Object** is an **instance of a Class**. A class is a blueprint, and an object is the actual entity created from that blueprint.

**Key Features**

* **State** – Data stored in fields.
* **Behavior** – Actions performed through methods.
* **Identity** – Each object is unique in memory.

**How It Works**

A **Class** defines the structure and behavior. An **Object** is created using that class and gets its own data and methods.

Example:

```java
Car car = new Car();
```

**Why to Use**

* To represent real-world entities such as **User**, **Car**, or **Employee**.
* To organize data and behavior together.
* To support **Object-Oriented Programming (OOP)**.
* To improve code reusability and maintainability.

**When to Use**

Use objects whenever you need to model an entity that has its own data and behavior.


## 3. Difference between class and object?

A **class** is a **blueprint or template** used to create objects.
An **object** is a **real instance of a class** created in memory.

**Key Features**

**Class**

* Acts as a **blueprint/template**
* Does **not occupy memory until objects are created**
* Defines **properties (variables) and behavior (methods)**

**Object**

* Is a **real runtime entity**
* Occupies **memory in heap**
* Represents a **specific instance of a class**

**How it works**
A **class defines structure**, and when we use `new`, Java creates an **object in memory** based on that class structure.

**Why to use**

* **Class** helps in designing structure
* **Object** allows real-world usage of that structure in programs

**When to use**

* Use **class** when defining a model (e.g., Student, Car)
* Use **object** when you need to **use or store actual data**

**Difference Table**

| **Class**                            | **Object**                  |
| ------------------------------------ | --------------------------- |
| Blueprint or template                | Real instance of a class    |
| Does not occupy memory directly      | Occupies memory in heap     |
| Declared using `class` keyword       | Created using `new` keyword |
| Defines structure (fields + methods) | Represents actual data      |
| Logical entity                       | Physical/runtime entity     |
| Used for designing                   | Used for execution          |

**Code Example**

```java id="1i6tx1"
class Student {   // Class
    String name;
    int age;

    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // Object
        s1.name = "Rahul";
        s1.age = 22;
        s1.display();
    }
}
```

## 4. What is method overloading?

**Method overloading** means having **multiple methods in the same class with the same name but different parameters**.

**Key Features**

* Same **method name**
* Different **parameter list (type, number, or order)**
* Happens in the **same class**
* Supports **compile-time polymorphism**
* Return type alone is **not enough to overload a method**

**How it works**

Java decides which method to call based on the **method signature (parameters)** during **compile time**.

**Why to use**

* Improves **code readability**
* Provides **flexibility** to handle different inputs with same operation name
* Reduces method naming complexity

**When to use**

* When the same operation needs to work with **different types or number of inputs**
* Example: adding numbers, printing data in different formats

**Code Example**

```java id="1i6tx1"
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println(c.add(2, 3));
        System.out.println(c.add(2, 3, 4));
        System.out.println(c.add(2.5, 3.5));
    }
}
```


## 5. What is method overriding?

**Method overriding** means redefining a **parent class method in a child class with the same method signature** to provide a **specific implementation**.

**Key Features**

* Requires **inheritance (IS-A relationship)**
* Same **method name, parameters, and return type**
* Happens in **different classes (parent-child)**
* Supports **runtime polymorphism**
* Uses **@Override annotation (recommended)**

**How it works**
When a method is called using a **parent reference pointing to a child object**, Java executes the **child class overridden method at runtime**.

**Why to use**

* To provide **specific implementation** in child class
* To achieve **runtime polymorphism**
* To extend or modify **parent behavior**

**When to use**

* When child class needs **different behavior from parent class method**
* When same method should behave differently in **different subclasses**

**Code Example**

```java id="1i6tx1"
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // Parent reference, child object
        a.sound(); // Dog barks
    }
}
```


## 6. What is the difference between this and super keywords?

**this** refers to the current object instance,  
**super** refers to the immediate parent class object.

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";
    
    void display() {
        System.out.println(this.name);  // Child
        System.out.println(super.name); // Parent
    }
}
```


## 7. What is class loader and how do they work?

A **Class Loader** is a part of the **JVM (Java Virtual Machine)** that is responsible for **loading .class files into memory at runtime**.

**Key Features**

* Part of the **JVM architecture**
* Loads classes **on demand (lazy loading)**
* Follows a **delegation hierarchy model**
* Types: **Bootstrap, Extension (Platform), Application ClassLoader**
* Enables **runtime class loading**

**How it works**

When a class is needed, the **ClassLoader loads it into JVM memory** in three steps:

1. **Loading** – Finds and loads `.class` file
2. **Linking** – Verifies and prepares class
3. **Initialization** – Assigns default/static values and executes static blocks

It follows a **parent delegation model**, meaning request is first sent to **parent ClassLoader**, then to child if not found.

**Why to use**

* To support **dynamic loading of classes**
* To improve **modularity and flexibility**
* To separate **system, framework, and user classes**

**When to use**

* When classes are loaded **at runtime instead of compile time**
* In frameworks like **Spring, Hibernate, JDBC drivers**

**Types of Class Loaders**

* **Bootstrap ClassLoader** → Loads core Java classes (`java.lang.*`)
* **Platform ClassLoader** → Loads platform libraries
* **Application ClassLoader** → Loads application classes from classpath

**Code Example (Conceptual)**

```java id="1i6tx1"
public class Main {
    public static void main(String[] args) {
        ClassLoader loader = Main.class.getClassLoader();

        System.out.println(loader); // Application ClassLoader
        System.out.println(loader.getParent()); // Platform ClassLoader
        System.out.println(loader.getParent().getParent()); // null (Bootstrap)
    }
}
```


## 8. Difference between runtime vs compile-time class loading? 

**Compile-time class loading** happens when classes are loaded during **program compilation and startup preparation**

**Runtime class loading** happens when classes are loaded **dynamically during program execution**.


**Compile-time Class Loading**

**Key Features**

* Classes are loaded at **startup or early JVM phase**
* Done using **static linking by JVM**
* All required classes must be **available before execution**
* No dynamic behavior

**How it works**
JVM loads required classes during **program start**, before execution begins, using **ClassLoader chain automatically**.

**Why / When to use**

* When all dependencies are **known in advance**
* For **simple applications** with static structure
* Faster and predictable loading


**Runtime Class Loading**

**Key Features**

* Classes are loaded **during execution**
* Uses methods like **Class.forName() or custom ClassLoader**
* Supports **dynamic behavior and plugins**
* More flexible but slightly slower

**How it works**
JVM loads class **only when it is first referenced or explicitly requested** during execution.

**Why / When to use**

* When classes are **not known at compile time**
* In frameworks like **Spring, JDBC drivers, plugin systems**
* For **dynamic module loading**


**Difference Table**

| **Compile-time Loading**        | **Runtime Loading**                      |
| ------------------------------- | ---------------------------------------- |
| Happens before execution starts | Happens during execution                 |
| Static and fixed                | Dynamic and flexible                     |
| All classes must be known       | Classes can be unknown initially         |
| Faster startup                  | Slightly slower due to on-demand loading |
| No late binding                 | Supports late binding                    |


**Code Example**

```java id="1i6tx1"
class Demo {
    static {
        System.out.println("Class Loaded");
    }
}

public class Main {
    public static void main(String[] args) throws Exception {

        // Runtime class loading
        Class.forName("Demo");

        System.out.println("Main executed");
    }
}
```


## 9. How do you load a class dynamically in Java? 

In Java, we can load a **class dynamically** at runtime using `Class.forName()`. This is useful when the class name is not known at compile time. Once the class is loaded, we can create objects and invoke methods using Reflection.

A common real-world example is loading a JDBC driver.

```java
Class<?clazz = Class.forName("com.company.Employee");
```

**Creating an Object Dynamically**

```java
Class<?clazz = Class.forName("com.company.Employee");

Object obj = clazz.getDeclaredConstructor()
                  .newInstance();
```

**JDBC Example**

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
This loads the MySQL driver at runtime.

## 10. What happens internally when you create an object using new? 


When we create an object using the `new` keyword, JVM first checks whether the class is loaded. If not, it loads the class into memory. Then it allocates memory in the Heap, initializes instance variables with default values, executes the constructor, and finally returns the object reference.

```java id="4v0xfi"
Employee emp = new Employee();
```

**Internal Steps**

```text id="f67d4p"
1. Load Employee class
2. Allocate memory in Heap
3. Initialize default values
4. Call constructor
5. Return object reference to emp
```

```java id="w5c7em"
class Employee {

    Employee() {
        System.out.println("Constructor Called");
    }
}
```


## 6. How do you create an immutable class in Java?

An **immutable class** in Java is a class whose **state (data)** cannot be changed after they are created. To create an immutable class:


**Key Features**

* Object state is **final and unchangeable** after creation
* Class is usually marked as **final**
* Fields are **private and final**
* No **setter methods**
* Only **getter methods** are provided
* Ensures **thread-safety by default**

**How it works**
All values are set using a **constructor**, and after object creation, there is **no way to modify the internal state**.

**Why to use**

* Provides **thread safety without synchronization**
* Ensures **data security and consistency**
* Useful in **caching, multi-threading, and security-sensitive applications**

**When to use**

* When object data should remain **constant after creation**
* In classes like **String, Wrapper classes, Date-Time APIs**

**Code Example**

```java id="1i6tx1"
final class Student {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rahul", 22);

        System.out.println(s1.getName());
        System.out.println(s1.getAge());
    }
}
```

## 9. How to create a class and object?

We create a class using `class` keyword and object using `new` keyword.

```java
class Student {
    String name;
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student(); // object created
        s.name = "John";
        System.out.println(s.name);
    }
}
```


## 10. What are instance variables and methods?

- **Instance variables** — fields declared inside a class but outside methods. Each object gets its own copy.
- **Instance methods** — methods that operate on instance variables. Require an object to be called.

```java
class Employee {
    String name;    // instance variable
    int salary;     // instance variable

    void work() {   // instance method
        System.out.println(name + " is working");
    }
}

Employee e = new Employee();
e.name = "Bob";
e.work();   // Bob is working
```

## 11. What is a constructor? Types?


A **constructor** in Java is a special method used to **initialize objects** when a class is instantiated.

**constructor two Types:**

* Default
* Parameterized

**Key Features**

* Has the **same name as the class**
* Has **no return type (not even void)**
* Called **automatically** when an object is created
* Can be **parameterized or non-parameterized**
* Can be **overloaded**

**How it works**
When we create an object using `new`, Java automatically calls the **constructor** to set initial values for the object’s variables.

**Why to use**

* To ensure **proper initialization** of objects
* To reduce need for manual setup after object creation
* To enforce **consistent object state**

**When to use**

* When an object needs **initial values at creation time**
* When we want to **prepare object state automatically**

**Code Example**

```java
class Student {
    String name;
    int age;

    // Constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rahul", 22);
        s1.display();
    }
}
```

## 12. What is default constructor?

A **default constructor** is a constructor that is provided by the **Java compiler automatically** if no constructor is defined in the class. It initializes the object with **default values**.

**Key Features**

* Created **automatically by compiler** if no constructor exists
* Has **no parameters**
* Initializes variables with **default values (0, null, false)**
* Can be **explicitly defined by developer**
* Always has the **same name as the class**

**How it works**
When an object is created and no constructor is written, Java inserts a **default constructor internally** and assigns **default values** to instance variables.

**Why to use**

* To allow object creation even when no initialization logic is written
* Provides **basic safe object state**

**When to use**

* When no custom initialization is needed
* When we want objects with **default values only**

**Code Example**

```java id="1i6tx1"
class Student {
    String name;
    int age;

    // No constructor defined → compiler adds default constructor
    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // default constructor called
        s1.display(); // null 0
    }
}
```


## 5. What is the static keyword?

The **static keyword** in Java is used to define **class-level members** that belong to the **class itself rather than any object**.

**Key Features**

* Belongs to the **class, not object**
* Shared across all **instances of the class**
* Can be applied to **variables, methods, blocks, and nested classes**
* Loaded into memory at **class loading time**
* Accessed using **class name**

**How it works**
When a class is loaded, all **static members are stored in method area memory**, and all objects share the same **static data**.

**Why to use**

* To share common data across all objects
* To save memory using **single copy of variable/method**
* To provide **utility methods (e.g., Math class methods)**

**When to use**

* When data should be **common for all objects (e.g., company name, counter)**
* When creating **utility/helper methods**

**Code Example**

```java id="1i6tx1"
class Student {
    static String college = "ABC College"; // static variable
    String name;

    Student(String name) {
        this.name = name;
    }

    static void displayCollege() { // static method
        System.out.println(college);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rahul");
        Student s2 = new Student("Amit");

        Student.displayCollege(); // called using class name

        System.out.println(s1.name);
        System.out.println(s2.name);
    }
}
```



## 14. Difference: static vs non-static?

Static belongs to class, non-static belongs to object.

| Static | Non-Static |
|--------|-----------|
| Belongs to the class | Belongs to the object |
| Shared across all instances | Separate copy per object |
| Accessed via class name | Accessed via object reference |
| Cannot access `this` | Can access `this` |
| Loaded at class loading time | Created when object is created |

```java
class Demo {
    static int x = 10;   // static
    int y = 20;          // non-static

    static void show() { System.out.println(x); }   // static method
    void display()     { System.out.println(y); }   // non-static method
}

Demo.show();              // no object needed
new Demo().display();     // object needed
```


## 15. Can a class have multiple constructors?

Yes. This is called **constructor overloading** — multiple constructors with different parameter lists.

```java
class Student {
    Student() {}
    Student(String name) {}
    Student(int age) {}
}
```


## 16. Can a constructor be final, static, or abstract in Java?

No, a constructor cannot be **final, static, or abstract**.

**Why:**

* **final** → Constructor is not inherited, so it cannot be overridden.
* **static** → Static belongs to class, but constructor is used to create object.
* **abstract** → Abstract methods have no body, but constructor must have a body.



## 17. Can we make a class `final`? Why?

Yes. A `final` class **cannot be extended (subclassed)**. Used to:
- Prevent inheritance for security (e.g., `String`, `Integer`)
- Ensure immutability
- Prevent unintended behavior changes

```java
final class Constants {
    static final double PI = 3.14159;
}

// class MyConstants extends Constants { }  // ❌ Compile error
```


## 18. Can we make a class `abstract`?

Yes, abstract class cannot be instantiated and can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();            // must be implemented by subclass
    void display() {                 // concrete method
        System.out.println("Area: " + area());
    }
}
```


## 19. XYZ

## 20. Can a class be both abstract and final? 

**No.** `abstract` requires the class to be subclassed (to implement abstract methods), while `final` prevents subclassing. They directly contradict each other — Java gives a compile error.


## 21. What is inner class? Types?

Inner class is a class inside another class, used for better grouping.

**Types:**

* Member inner class
* Static nested class
* Local inner class
* Anonymous class

```java
class Outer {
    class Inner {
        void show() {
            System.out.println("Inner class");
        }
    }
}
```


## 22. What is singleton class?

**Singleton** is a **Design Pattern** that ensures only **one instance** of a class is created and provides a global access point to that instance.

A **singleton** class allows **only one instance** to be created throughout the application. Achieved by making the constructor private and providing a static method to return the single instance.

**Key Features**

* Only **one object** of the class exists
* Provides **global access** to the object
* Saves memory and resources
* Commonly used in Spring beans and configuration classes

**How It Works**

The constructor is made **private** so that objects cannot be created from outside the class. A static method returns the single instance.

**Code Example**

```java
class Singleton {
    private static Singleton instance;

    private Singleton() { }   // private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// How t use
public class Test {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
    }
}
```

**Why to Use**

* To control object creation
* To share a single resource across the application
* To improve resource management
* To avoid creating unnecessary objects

**When to Use**

Use **Singleton** when exactly one object should exist in the application.

Examples:

* **Logger**
* **Configuration Manager**
* **Cache Manager**
* **Database Connection Manager**


## 23. Is it allowed to overload main() method in Java?

**Yes, the `main()` method can be overloaded** in Java.

The JVM only calls **`public static void main(String[] args)`**, while other overloaded versions behave as **regular methods**.

```java
public class Test {
    public static void main(String[] args) { } // JVM entry point
    public static void main(int x) { }         // Overloaded
    public static void main() { }              // Overloaded
}
```


## 24. Is it allowed to override main() method in Java?

**No, we cannot override the `main()` method in Java** because it is **static**, and static methods cannot be overridden (they are hidden).

* The `main()` method is:

  ```java
  public static void main(String[] args)
  ```
* Since it is **static**, it belongs to the class, not the object.
* Static methods are resolved at **compile time**, so they cannot participate in runtime polymorphism.



## 25. Can you override static methods?

No, you cannot override static methods in Java. **Static methods belong to the class, not instances,** so they're resolved at compile time based on the reference type.

```java
class Parent {
    static void display() { System.out.println("Parent"); }
}

class Child extends Parent {
    static void display() { System.out.println("Child"); } // Hiding, not overriding
}

Parent p = new Child();
p.display(); // Prints "Parent" - based on reference type
```


## 26. Is it possible to execute a program without defining a main() method?

It is **technically possible** to run code in a **static block** without a `main()` method, but **modern Java requires `main()`** as the entry point.

Using static blocks alone is **not recommended** and violates standard practices.

```java
class NoMain {
    static {
        System.out.println("Executing without main");
        System.exit(0); // Required to prevent error
    }
}
// Works in older Java versions, not recommended
```


## 27. Can we create object without `new` keyword?

Yes, using methods like clone(), factory methods, reflection, or deserialization.

```java
// Example using clone
class A implements Cloneable {
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```


## 28. What happens if a class has no constructor?

Java automatically provides a **default no-argument constructor** that calls `super()` implicitly. All fields are initialized to their default values (`0`, `null`, `false`).

```java
class Box { int width; }       // Java adds: Box() { super(); }

Box b = new Box();             // works fine
System.out.println(b.width);  // 0
```


## 29. Can a class be private or protected?

Top-level class cannot be private or protected, only public or default. Inner classes can be private/protected.

```java
class Outer {
    private class Inner { }      // ✅ allowed
    protected class Helper { }   // ✅ allowed
}

// private class TopLevel { }   // ❌ compile error
```


## 30. What happens if a static block throws an unchecked exception?

If a static block throws an unchecked exception:

* Class will **fails to load**
* JVM will throws **ExceptionInInitializerError**
* Program stops and class cannot be used


```java
public class Main {
    public static void main(String[] args) {
        Test t = new Test();
    }
}

class Test {
    static {
        System.out.println("Static block running");
        int x = 10 / 0; // ArithmeticException
    }
}

// Output:
Exception in thread "main" java.lang.ExceptionInInitializerError
```

## 31. Summary Classes and Objects

**🔹 Class**

* **Class:** A blueprint/template used to create objects.
* **Fields (Variables):** Represent the state of the object.
* **Methods:** Define the behavior of the object.
* **Constructor:** Special method used to initialize object values.


**🔹 Object**

* **Object:** Instance of a class with state and behavior.
* **State:** Represented by variables (data).
* **Behavior:** Represented by methods (functions).
* **Memory:** Objects are created in heap memory.


**🔹 Key Concepts**

* **Encapsulation:** Wrapping data and methods together and restricting access using private/public.
* **this keyword:** Refers to the current object.
* **new keyword:** Used to create an object.



# ✅ 04. Java Inheritance 

## 1. What is Inheritance in Java?

**Inheritance** is an **OOP (Object-Oriented Programming)** concept that allows one class to acquire the properties and methods of another class.

The class being inherited from is called the **Parent Class (Superclass)**, and the class that inherits is called the **Child Class (Subclass)**.

**Key Features**

* Promotes **Code Reusability**
* Establishes an **IS-A Relationship**
* Supports **Method Overriding**
* Reduces code duplication
* Improves maintainability

**How It Works**

A child class uses the **extends** keyword to inherit fields and methods from the parent class.

**Code Example**

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

Usage:

```java
Dog dog = new Dog();

dog.eat();   // Inherited method
dog.bark();  // Own method
```

**Why to Use**

* To reuse existing code
* To create a hierarchy between classes
* To avoid duplicate code
* To support polymorphism

**When to Use**

Use **Inheritance** when there is an **IS-A Relationship** between classes.

Examples:

* **Dog IS-A Animal**
* **Car IS-A Vehicle**
* **Manager IS-An Employee**



## 2. Why doesn't Java support multiple inheritance (with classes)?

Java does not support **multiple inheritance (using classes)** to avoid **ambiguity and complexity** in the program.

**Key Features / Reason**

* Prevents **Diamond Problem**
* Avoids **method ambiguity (confusion which parent method to call)**
* Keeps Java **simple and clean OOP language**
* Supports multiple inheritance only through **interfaces**

**How it works (Problem scenario)**
If Java allowed multiple inheritance, a child class could inherit the **same method from two parent classes**, creating confusion about **which method to execute**.

This is called the **Diamond Problem**.

**Why not supported**

* To avoid **ambiguity in method resolution**
* To prevent **complexity in inheritance hierarchy**
* To ensure **better maintainability and readability**

**When to use alternative (Interfaces)**

* When we need multiple behavior inheritance
* When we want **loose coupling and abstraction**
* Use **interfaces instead of classes**

**Code Example (Problem Illustration in Theory)**

```java id="1i6tx1"
// Not allowed in Java (conceptual example)

class A {
    void show() {
        System.out.println("Class A");
    }
}

class B {
    void show() {
        System.out.println("Class B");
    }
}

// class C extends A, B  ❌ Not allowed in Java
```

**Correct Approach using Interface**

```java id="1i6tx1"
interface A {
    void show();
}

interface B {
    void show();
}

class C implements A, B {
    public void show() {
        System.out.println("Resolved single implementation");
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
    }
}
```


## 3. What is the diamond problem?

**Definition**
The **Diamond Problem** is an ambiguity issue in **multiple inheritance** where a class inherits the same method from **two parent classes**, causing confusion about **which method to execute**.

**Key Features**

* Occurs in **multiple inheritance scenarios**
* Creates **method ambiguity**
* Leads to **conflict in method resolution**
* Problem of **duplicate inheritance path (diamond shape structure)**
* Avoided in Java by **not supporting multiple inheritance with classes**

**How it works (Problem scenario)**
If a class **C inherits from two classes A and B**, and both A and B have the same method, then Java cannot decide **which parent method C should use**.

This forms a **diamond-like inheritance structure**, hence the name.

**Why it is a problem**

* Causes **confusion in method execution**
* Leads to **inconsistent behavior**
* Makes code **complex and error-prone**

**When it occurs**

* In languages that support **multiple inheritance (like C++)**
* When a child class inherits from **two parent classes having same method**

**How Java solves it**

* Java avoids this issue by **not allowing multiple inheritance with classes**
* Instead, it allows multiple inheritance using **interfaces**

**Code Example (Problem Illustration Concept)**

```
    A
   / \
  B   C
   \ /
    D
```

```java id="1i6tx1"
// Conceptual example (not allowed in Java)

class A {
    void show() {
        System.out.println("Class A");
    }
}

class B {
    void show() {
        System.out.println("Class B");
    }
}

// class C extends A, B  ❌ Not allowed due to Diamond Problem
```

**Solution using Interface**

```java id="1i6tx1"
interface A {
    void show();
}

interface B {
    void show();
}

class C implements A, B {
    public void show() {
        System.out.println("Resolved implementation in C");
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
    }
}
```

If classes B and C both override a method from A, and D inherits from both B and C, which version should D use? This creates confusion and compilation errors.

## 3. How does Java solve the diamond problem?

Java solves the **diamond problem** by not allowing multiple class inheritance but supporting multiple interface inheritance with **default methods**.


```java
interface A { default void method() { } }
interface B { default void method() { } }

class C implements A, B {
    @Override
    public void method() {
        A.super.method(); // Explicitly choose which to call
    }
}
```


## 4. Why do we use Inheritance?

**Inheritance** is an OOP concept where a **child class acquires properties and behaviors of a parent class** using the **extends keyword**.

**Key Features**

* Promotes **code reusability**
* Supports **method overriding (runtime polymorphism)**
* Establishes an **IS-A relationship**
* Reduces **code duplication**
* Improves **maintainability and scalability**

**How it works**
A **child class inherits fields and methods of a parent class**, and can also **extend or override behavior**.

**Why to use**

* To avoid **repeated code writing**
* To achieve **reusability and cleaner design**
* To support **OOP principles like polymorphism**

**When to use**

* When there is a clear **IS-A relationship (Dog IS-A Animal)**
* When multiple classes share **common behavior or properties**

**Code Example**

```java id="1i6tx1"
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();  // inherited method
        d.bark(); // child method
    }
}
```


## 5. What is `extends` keyword?

**Definition**
The **extends keyword** in Java is used to create **inheritance**, where a **child class inherits properties and behaviors of a parent class**.

**Key Features**

* Used for **class-to-class inheritance**
* Supports **IS-A relationship**
* Allows **code reuse from parent class**
* Enables **method overriding**
* A class can extend **only one class (single inheritance)**

**How it works**
When a class uses **extends**, it automatically gets access to the **non-private members (fields and methods)** of the parent class.

**Why to use**

* To achieve **code reusability**
* To reduce **duplication of code**
* To implement **inheritance and polymorphism**

**When to use**

* When a class is a **specialized version of another class**
* When there is a clear **IS-A relationship**

**Code Example**

```java id="1i6tx1"
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal { // extends keyword used
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();  // inherited method
        d.bark(); // child method
    }
}
```




## 14. What is polymorphism?



**Definition**
**Polymorphism** means **one entity having many forms**, where a single method or object behaves differently in different situations.

- Poly - many
- Morphism - Behaviour

**Key Features**

* Supports **method overloading and method overriding**
* Enables **code flexibility and reusability**
* Works through **compile-time and runtime polymorphism**
* Improves **maintainability and scalability**
* Core concept of **OOP (Object-Oriented Programming)**


**How it works**

* In **compile-time polymorphism**, method selection happens using **method overloading**
* In **runtime polymorphism**, method selection happens using **method overriding via dynamic method dispatch**


**Why to use**

* To achieve **flexible and reusable code**
* To reduce **code duplication**
* To support **dynamic behavior at runtime**


**When to use**

* When same method name should work with **different inputs (overloading)**
* When child class needs **different implementation (overriding)**
* When designing **scalable systems**


**Code Example**

**Compile-time Polymorphism (Method Overloading)**
The method name is the same, but parameters are different.
```java id="1i6tx1"
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

**Runtime Polymorphism (Method Overriding)**
A child class provides its own implementation of a method defined in the parent class.
```java id="1i6tx1"
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // Runtime polymorphism
        a.sound();
    }
}
```


## 14. What is Dynamic Method Dispatch?


**Dynamic Method Dispatch** is a mechanism in Java where the **method to be executed is decided at runtime**, not at compile time. It happens when a **parent class reference** points to a **child class object** and the child class **overrides** a method of the parent class. It is the foundation of **runtime polymorphism**.

**Key Features**

* **Runtime Polymorphism** – Method call is resolved during execution.
* **Method Overriding** – Child class provides its own implementation.
* **Parent Reference, Child Object** – A superclass reference can refer to a subclass object.
* **Flexibility** – Same method call can produce different behaviors.

**How it works**

1. Create a **parent class** with a method.
2. **Override** that method in the child class.
3. Create a **parent class reference** and assign it a **child class object**.
4. When the method is called, Java executes the **child class version** based on the actual object type.

**Why do we use it?**

* To achieve **runtime polymorphism**.
* To write **flexible and extensible code**.
* To allow a single interface to work with multiple implementations.
* To reduce tight coupling between classes.

**When to use it?**

* When multiple child classes have different implementations of the same method.
* When using **Inheritance** and **Method Overriding**.
* In **frameworks**, **Spring applications**, and **design patterns** where behavior changes dynamically.

**Simple Interview Answer**

> **Dynamic Method Dispatch is a feature of Java where a parent class reference calls the overridden method of a child class object at runtime. It is used to achieve runtime polymorphism and make the code more flexible and reusable.**

**Example Code**

```java
// Parent class
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Child class
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal obj = new Dog(); // Parent reference, child object
        obj.sound();            // Calls Dog's sound() at runtime
    }
}
```

**Output**

```text
Dog barks
```


## 6. What is IS-A Relationship?


**Definition**
**IS-A** is an inheritance relationship — it means **one object is a type of another**. Implemented using `extends` or `implements`. Used to check with `instanceof`.

**Key Features**

* Represents **inheritance (parent-child relationship)**
* Uses **extends (class inheritance)** or **implements (interface relationship)**
* Promotes **code reusability**
* Supports **polymorphism**
* Defines a **hierarchical relationship**

**How it works**
If class **Dog extends Animal**, then **Dog IS-A Animal**, meaning Dog inherits all **non-private properties and behaviors** of Animal.

**Why to use**

* To achieve **inheritance and reusability**
* To build **logical hierarchy of classes**
* To enable **polymorphism**

**When to use**

* When one class is a **type of another class (Car IS-A Vehicle)**
* When common behavior should be **shared through inheritance**

**Code Example**

```java id="1i6tx1"
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal { // Dog IS-A Animal
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // inherited method
        d.bark();  // own method
    }
}
```

## 6. What is HAS-A (Composition) Relationship?

**Definition**
The **HAS-A relationship** represents **composition or aggregation**, where one class **contains or uses another class as a member variable**.

**Key Features**

* Represents **object composition** (uses-a relationship)
* Achieved using **instance variables of another class**
* Promotes **code reusability through composition**
* Provides **loose coupling compared to inheritance**
* Can model real-world relationships effectively

**How it works**
One class **creates or holds an object of another class** and uses its **methods or properties** to perform operations.

**Why to use**

* To achieve **flexibility over inheritance**
* To reduce **tight coupling between classes**
* To model **real-world relationships more accurately**

**When to use**

* When one object **contains another object (Car HAS-A Engine)**
* When behavior should be **shared via composition instead of inheritance**

**Code Example**

```java id="1i6tx1"
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    Engine engine = new Engine(); // HAS-A relationship

    void drive() {
        engine.start();
        System.out.println("Car is driving");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}
```


## 7. Composition and Aggregation?

**Definition**
**Composition** and **Aggregation** are two types of **HAS-A relationships** in Java used to build objects using other objects.

* **Composition** → Strong relationship where the **child object cannot exist without the parent**
* **Aggregation** → Weak relationship where the **child object can exist independently**


**Key Features**

**Composition**

* Strong **ownership relationship**
* Child object's lifecycle depends on **parent object**
* If parent is destroyed, child is also **destroyed**
* Used for **tight coupling scenarios**

**Aggregation**

* Weak **association relationship**
* Child object can **exist independently**
* No strict lifecycle dependency
* Used for **loose coupling**


**How it works**

* In **Composition**, parent class creates and controls the **dependent objects**
* In **Aggregation**, parent class uses **external objects passed from outside**


**Why to use**

* To represent **real-world relationships clearly**
* To improve **code reusability and modularity**
* To choose correct **dependency strength (strong vs weak)**


**When to use**

**Composition**

* When child object is **fully dependent (Car HAS-A Engine created inside Car)**
* When lifecycle is **controlled by parent**

**Aggregation**

* When object is **shared or independent (Student HAS-A Address, but Address exists separately)**


**Code Example**

**Composition Example**

```java id="1i6tx1"
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine = new Engine(); // Composition

    void drive() {
        engine.start();
        System.out.println("Car is moving");
    }
}
```

**Aggregation Example**

```java id="1i6tx1"
class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class Student {
    private Address address; // Aggregation

    Student(Address address) {
        this.address = address;
    }

    void show() {
        System.out.println(address.city);
    }
}
```


## 8. Types of Inheritance in Java

| Type | Description |
|------|-------------|
| Single | One parent → one child |
| Multilevel | A → B → C (chain) |
| Hierarchical | One parent → multiple children |
| Multiple | Not supported with classes; supported via interfaces |
| Hybrid | Combination — only via interfaces |

```java
// Single
class A { }  
class B extends A { }

// Multilevel
class C extends B { }

// Hierarchical
class D extends A { }  
class E extends A { }
```


## 9. Does Java support Multiple Inheritance? Why?

❌ No, Java does not support multiple inheritance with classes.

👉 *Reason:* To avoid **ambiguity problem (Diamond problem)**.

✅ But it supports it using **interfaces**.


## 10. What is Multilevel Inheritance?

When a class inherits from a class, which already inherits another class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {
    void bark() {}
}

class Puppy extends Dog {
    void weep() {}
}
```


## 11. What is Hierarchical Inheritance?

Multiple child classes inherit from one parent class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {}
class Cat extends Animal {}
```

## 12. Can we override static methods?

❌ No, Static methods are **not overridden**, they are **hidden**.

```java
class Parent {
    static void show() { 
        System.out.println("Parent"); 
    }
}

class Child extends Parent {
    static void show() { 
        System.out.println("Child"); 
    }  // hiding
}

Parent p = new Child();
p.show();   // Parent — reference type decides, not object type
```


## 13. Can we override final methods?

❌ No, Final methods cannot be changed in child class.

```java
class Animal {
    final void run() {}
}

class Dog extends Animal {
    // void run() { }  ❌ Error: cannot override final method
}
```

## 15. How does method overriding work internally?

**Method Overriding** works using **Dynamic Method Dispatch (Runtime Polymorphism)**. When a **parent class reference** points to a **child class object**, Java decides which overridden method to execute **at runtime** based on the **actual object type**, not the reference type.

**Key Features**

* **Runtime Polymorphism**.
* Uses **Inheritance** and **Method Overriding**.
* Decision is made based on the **actual object**, not the reference.
* Improves **flexibility** and **extensibility**.

**How it works internally**

1. Java loads the **parent** and **child** classes into memory.
2. The child class **overrides** a method from the parent class.
3. A **parent reference** is assigned to a **child object**.
4. At runtime, the **JVM** checks the actual object type.
5. The JVM calls the **overridden method** from the child class using **Dynamic Method Dispatch**.

**Why do we use it?**

* To achieve **runtime polymorphism**.
* To allow different classes to provide their own implementation of the same method.
* To write **loosely coupled** and **maintainable** code.

**When to use it?**

* When a child class needs to **customize or extend** the behavior of a parent class.
* When implementing **frameworks**, **interfaces**, and **design patterns**.
* When different objects should respond differently to the same method call.

**Example Code**

```java
// Parent class
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Child class
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal obj = new Dog(); // Parent reference, child object
        obj.sound();            // JVM calls Dog's sound() at runtime
    }
}
```

**Output**

```text
Dog barks
```

**Internal Flow**

```text
Animal obj = new Dog();
        │
        ▼
Reference Type = Animal
Actual Object Type = Dog
        │
        ▼
obj.sound()
        │
        ▼
JVM checks actual object type at runtime
        │
        ▼
Calls Dog's overridden sound() method
```

## 16. What are rules for method overriding?


**Definition:**
**Method Overriding** happens when a **child class provides a specific implementation** of a method that is already defined in its **parent class**, keeping the **same method signature**.


| Rule | Detail |
|------|--------|
| Same method name | Must match exactly |
| Same parameters | Same type, number, order |
| Return type | Same or covariant (subtype) |
| Access modifier | Same or more accessible (not more restrictive) |
| Exceptions | Can throw fewer/narrower checked exceptions |
| Cannot override | `static`, `final`, `private` methods |


**Key Rules:**

* **Method name must be same** in both parent and child class
* **Parameters must be same** (same type and order)
* Must have an **IS-A relationship (inheritance)**
* **Return type must be same or covariant** (child type allowed)
* **Access modifier cannot be more restrictive** (can be same or more visible)
* **Private, static, and final methods cannot be overridden**
* **Checked exceptions must not be broader** than parent method
* Overriding is based on **runtime polymorphism**


**How it works:**

* Parent class defines a method
* Child class **redefines the same method**
* At runtime, Java calls the method of the **actual object type**


**Why to use:**

* To achieve **runtime polymorphism**
* To provide **specific behavior in child classes**
* To improve **flexibility and reusability**


**When to use:**

* When a **general behavior exists in parent class**
* But each child class needs a **different implementation**
* In frameworks like **Spring, Hibernate, and design patterns**


```java
class Parent {
    protected Number getValue() throws Exception { return 1; }
}

class Child extends Parent {
    @Override
    public Integer getValue() { return 42; }  // ✅ covariant return, wider access, fewer exceptions
}
```


## 17. What is covariant return type?

Child class can return a more specific type than parent method.

```java
class A {}
class B extends A {}

class Parent {
    A get() { return new A(); }
}

class Child extends Parent {
    B get() { return new B(); } // allowed
}
```


## 18. Can constructors be inherited?

**No.** Constructors are not inherited because they are tied to the class they belong to. 
However, a child class can **call the parent constructor** using `super()`.

```java
class Animal {
    Animal(String name) { System.out.println("Animal: " + name); }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);   // explicitly calling parent constructor
        System.out.println("Dog: " + name);
    }
}
```


## 19. What is the order of constructor execution?

Parent constructor executes first, then child constructor.

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() { System.out.println("Child"); }
}

//Output:
Parent
Child
```


## 20. What happens if parent constructor is not called?

Java automatically calls default parent constructor using super().

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() {
        // super() is added automatically
        System.out.println("Child");
    }
}
```


## 21. Can we extend multiple classes in Java?

**No.** Java does not support extending multiple classes. A class can only extend **one** class. For multiple inheritance of behavior, use **interfaces**.

```java
// class C extends A, B { }  // ❌ Not allowed

interface A { void methodA(); }
interface B { void methodB(); }

class C implements A, B {    // ✅ multiple interfaces allowed
    public void methodA() { }
    public void methodB() { }
}
```


## 22. Can we inherit private members?

**Yes, they are inherited** but **not directly accessible** in the child class. They can be accessed indirectly through `public` or `protected` getter/setter methods.

```java
class Parent {
    private int secret = 42;
    int getSecret() { return secret; }   // public accessor
}

class Child extends Parent {
    void show() {
        // System.out.println(secret);      // ❌ not accessible
        System.out.println(getSecret());    // ✅ accessed via method
    }
}
```


## 23. Can we override private methods?

“No, private methods are not visible, so they cannot be overridden.

```java 
class A {
    private void show() {}
}

class B extends A {
    // void show() {} ❌ not overriding, new method
}
```


## 24. Can we change access modifier while overriding?

**Yes, but only to increase visibility** (make it more accessible). You cannot reduce the access level.

| Parent Modifier | Allowed in Child |
|----------------|-----------------|
| `private` | Cannot override |
| `default` | `default`, `protected`, `public` |
| `protected` | `protected`, `public` |
| `public` | `public` only |

```java
class Parent {
    protected void show() { }
}

class Child extends Parent {
    @Override
    public void show() { }    // ✅ widened from protected to public

    // private void show() { }  // ❌ narrowed — compile error
}
```

## 25. What happens if parent and child have same variable name?

This is called **variable hiding**. The variable accessed depends on the **reference type**, not the object type. Unlike methods, variables do not follow runtime polymorphism.

```java
class Parent { int x = 10; }
class Child extends Parent  { int x = 20; }

Parent p = new Child();
System.out.println(p.x);   // 10 — reference type (Parent) decides

Child c = new Child();
System.out.println(c.x);   // 20 — reference type (Child) decides
```


## 26. Can abstract class have constructor?


**Answer:**
Yes, an **abstract class can have a constructor** in Java. Even though you **cannot instantiate an abstract class directly**, its constructor is called when a **child class object is created**.


**Key Features:**

* **Abstract class can have constructors**
* Constructor is called during **child object creation**
* Used to initialize **common fields**
* Supports **code reuse in inheritance**
* Executes before child class constructor


**How it works:**

* You create an object of a **concrete child class**
* Java automatically calls the **parent (abstract class) constructor first**
* Then the **child class constructor** executes
* Used to initialize shared state in parent class


**Why to use:**

* To initialize **common properties** for all child classes
* To reduce **code duplication**
* To ensure proper **object initialization in inheritance hierarchy**


**When to use:**

* When multiple classes share **common initialization logic**
* When you want to enforce **base setup before child execution**
* In frameworks using **inheritance-based design**


**Code Example:**

```java
abstract class Vehicle {

    String type;

    Vehicle(String type) {
        this.type = type;
        System.out.println("Vehicle constructor called");
    }

    abstract void run();
}

class Bike extends Vehicle {

    Bike(String type) {
        super(type);
        System.out.println("Bike constructor called");
    }

    void run() {
        System.out.println(type + " is running");
    }
}

public class Main {
    public static void main(String[] args) {

        Bike b = new Bike("Two Wheeler");
        b.run();
    }
}
```



## 27. Can interface extend class?

**No.** An interface cannot extend a class. An interface can only **extend another interface**.

```java
// interface A extends B {} (only if B is interface)
```


## 27. Can an interface extend another interface?

Yes, in **Java**, an **interface can extend another interface** using the `extends` keyword.
A child interface inherits all methods from the parent interface.

```java
interface A {
    void methodA();
}

interface B extends A {
    void methodB();
}
```


## 28. Can class extend interface?

**No.** A class cannot `extend` an interface. A class must use `implements` to use an interface.

```java
interface Printable { void print(); }

// class Doc extends Printable { }     // ❌ Not allowed

class Doc implements Printable {       // ✅ correct
    public void print() { System.out.println("Printing..."); }
}
```

## 29. Which type of polymorphism does method overloading represent?

**Method overloading** represents **compile-time polymorphism** (also called static polymorphism or early binding).

The compiler resolves which overloaded method to call **at compile time** based on the method signature (number and types of parameters).

```java
class Calculator {
    int add(int a, int b)          { return a + b; }
    double add(double a, double b) { return a + b; }
}

// Resolved at compile time — no runtime decision needed
Calculator c = new Calculator();
c.add(1, 2);       // calls int version
c.add(1.0, 2.0);   // calls double version
```


## 30. If a class implements two interfaces with the same default method, what happens and how do you resolve it?

Java gives a **compile-time error** — the class must **override** the conflicting default method to resolve the ambiguity.

```java
interface A {
    default void greet() { System.out.println("Hello from A"); }
}

interface B {
    default void greet() { System.out.println("Hello from B"); }
}

// ❌ Compile error without override
class C implements A, B {

    // ✅ Must override to resolve conflict
    @Override
    public void greet() {
        A.super.greet();    // explicitly call A's version, OR
        // B.super.greet(); // explicitly call B's version, OR
        // write your own implementation
        System.out.println("Hello from C");
    }
}
```

## 31. Can an interface have a constructor in Java, and why or why not?

**No.** Interfaces cannot have constructors.

**Why:**
- A constructor's purpose is to **initialize an object's instance state**
- Interfaces cannot be instantiated directly — you never write `new Flyable()`
- Interfaces have **no instance variables** to initialize
- Only the implementing class is instantiated, and its constructor handles initialization

```java
interface Animal {
    // Animal() { }  // ❌ Compile error: interfaces cannot have constructors
    void sound();
}

class Dog implements Animal {
    String name;
    Dog(String name) { this.name = name; }  // ✅ constructor in the implementing class
    public void sound() { System.out.println("Woof"); }
}
```


## 31. Can an interface have instance variables in Java, and if not, what type of variables can it have?

**No.** Interfaces cannot have instance variables.

All variables in an interface are implicitly **`public static final`** (constants). They belong to the interface itself, not to any instance.

```java
interface MathConstants {
    double PI = 3.14159;        // public static final double PI = 3.14159
    int MAX_VALUE = 100;        // public static final int MAX_VALUE = 100

    // int counter;             // ❌ not allowed — would be an instance variable
    // private int x = 5;       // ❌ private instance variable not allowed
}

class Circle implements MathConstants {
    double area(double r) {
        return PI * r * r;      // access constant directly
    }
}

// Access without an instance
System.out.println(MathConstants.PI);       // 3.14159
System.out.println(MathConstants.MAX_VALUE); // 100
```

## 32. Summary Java Inheritance

**🔹 Basics**

* **Inheritance:** Mechanism where one class acquires properties and behavior of another class.
* **Parent Class (Super Class):** Class whose properties are inherited.
* **Child Class (Sub Class):** Class that inherits from parent class.
* **extends keyword:** Used to inherit a class.


**🔹 Key Concepts**

* **Code Reusability:** Avoids duplication by reusing existing code.
* **Method Overriding:** Child class provides its own implementation of parent method.
* **super keyword:** Used to access parent class methods/constructors.
* **IS-A Relationship:** Represents inheritance relationship (e.g., Dog is an Animal).


**🔹 Types of Inheritance**

* **Single Inheritance:** One parent → one child.
* **Multilevel Inheritance:** Chain of inheritance (A → B → C).
* **Hierarchical Inheritance:** One parent → multiple children.
* **Multiple Inheritance:** Not supported with classes (supported via interfaces).


**⚠️ Important Points**

* Constructors are **not inherited**, but can be called using `super()`.
* Private members are **not directly accessible** in child class.



# ✅ 05. Java Interface & Abstract Class 


## 1. What is an Interface?

An **Interface** is a blueprint that defines a set of methods that a class must implement. It specifies **what** a class should do, but not **how** it should do it.

**Key Features**

* Cannot be instantiated
* Contains **abstract methods** by default
* Supports **multiple inheritance**
* Promotes **Abstraction** and **Loose Coupling**
* Can contain **default** and **static methods** (Java 8+)

**How It Works**

A class uses the **implements** keyword to implement an interface and provide the implementation of its methods.

**Why to Use**

* To define a common contract for multiple classes
* To achieve **Abstraction**
* To reduce dependencies between components
* To support **Multiple Inheritance**

**When to Use**

Use an **Interface** when different classes should follow the same contract but may have different implementations.

Examples:

* **Payment** interface implemented by CreditCardPayment and PayPalPayment
* **Notification** interface implemented by EmailNotification and SMSNotification

**Code Example**

```java
interface Animal {

    void makeSound();
}

class Dog implements Animal {

    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}
```

Usage:

```java
Animal dog = new Dog();
dog.makeSound();
```

## 2. Interface types in Java?

In Java, interfaces are mainly of four types:

**Normal Interface :**
A normal interface defines a contract with multiple abstract methods that a class must implement.

```java
interface Payment {
    void pay();
}
```

**Functional Interface :**
A functional interface contains exactly one abstract method and is used for lambda expressions.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // single abstract method
    
    default void print() { } // default methods allowed
}

// 1. Usage with lambda
Calculator add = (a, b) -> a + b;
int result = add.calculate(5, 3);

// 2. Using Separate Class
class AddCalculator implements Calculator {
    public int calculate(int a, int b) {
        return a + b;
    }

    public void print() {
        System.out.println("Print here");
    }
}

Calculator add = new AddCalculator();
int result = add.calculate(5, 3);
System.out.println(result);

add.print();
```

**Marker Interface :**
A marker interface is an empty interface used to tag a class for special behavior.

```java
import java.io.Serializable;

// Marker interface
interface Serializable {
    // Empty - just marks the class
}

class Student implements Serializable {
    String name;
    // This class can now be serialized
}
```

**Nested Interface :**
A nested interface is declared inside a class or another interface for logical grouping.

```java
class Bank {
    interface RBI {
        void rule();
    }
}
```


## 4. What is an Abstract Class?

An **Abstract Class** is a class that cannot be instantiated directly. It is used as a base class for other classes and can contain both **abstract methods** (without implementation) and **concrete methods** (with implementation).

**Abstract Class cannot be instantiated directly**
```java
abstract class Vehicle {
    abstract void start();
}

// ❌ Compilation Error
Vehicle v = new Vehicle();
```

**Key Features**

* Cannot create an object of an **Abstract Class**
* Can contain **abstract** and **non-abstract methods**
* Can have **fields**, **constructors**, and **methods**
* Supports **Inheritance**
* Child classes must implement abstract methods

**How It Works**

An **Abstract Class** defines common behavior that can be shared by multiple subclasses. The subclasses inherit from it and provide implementations for the abstract methods.

**Why to Use**

* To provide a common base for related classes
* To avoid code duplication
* To enforce certain methods in child classes
* To support code reusability

**When to Use**

Use an **Abstract Class** when multiple classes share common fields or methods, but some behavior must be implemented differently by each subclass.

**Code Example**

```java
abstract class Animal {
    abstract void makeSound();

    void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark");
    }
}
```

Usage:

```java
Animal dog = new Dog();
dog.makeSound();
dog.sleep();
```

Abstract class can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();            // must be implemented by subclass
    void display() {                 // concrete method
        System.out.println("Area: " + area());
    }
}
```

## 4. When should you use an interface instead of an abstract class?


Both **Interface** and **Abstract Class** are used to achieve **Abstraction**, but they serve different purposes.

| **Interface**                                                       | **Abstract Class**                          |
| ------------------------------------------------------------------- | ------------------------------------------- |
| Defines a **contract**                                              | Defines a **base class**                    |
| Supports **multiple inheritance**                                   | Supports only **single inheritance**        |
| Focuses on **what to do**                                           | Focuses on **what to do and how to do it**  |
| Cannot have instance fields                                         | Can have instance fields                    |
| All methods are abstract by default (except default/static methods) | Can have both abstract and concrete methods |
| Implemented using **implements**                                    | Extended using **extends**                  |

**How It Works**

* Use an **Interface** when unrelated classes need to follow the same contract.
* Use an **Abstract Class** when classes share common state and behavior.

**Why to Use Interface**

* To achieve **Loose Coupling**
* To support **Multiple Inheritance**
* To define a common contract

**Why to Use Abstract Class**

* To share common code among subclasses
* To avoid code duplication
* To provide a common base implementation

**When to Use Interface**

When different classes need the same behavior but may implement it differently.

Example:

```java
interface Animal {
    void sound();   // abstract method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Dog barks");
    }
}
```

**When to Use Abstract Class**

When classes have common fields and methods.

Example:

```java
abstract class Animal {
    abstract void sound();   // abstract method

    void eat() {             // concrete method
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```


## 5. What are static methods in interfaces?

**Static methods in interfaces** are methods declared with the **`static`** keyword inside an interface in **Java**.
They **belong to the interface itself and are called using the interface name**, not by implementing classes.

```java
interface MyInterface {

    static void show() {
        System.out.println("Static method in interface");
    }
}

public class Test {
    public static void main(String[] args) {
        MyInterface.show();  // called using interface name
    }
}
```



## 6. Can we create object of interface or abstract class?

No, we cannot create objects of interface or abstract class directly. But we can create objects using their implementing or child classes.

```java
Animal a = new Dog(); // valid
```

## 7. Can an interface have methods with implementation? (Java 8+)
Yes, from Java 8 onwards, interfaces can have methods with implementation using default and static keywords.

```java
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }
}
```

## 8. What are default and static methods in interface?

Default methods are instance methods with implementation that can be overridden. Static methods belong to the interface and cannot be overridden.

```java
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }

    static void info() {
        System.out.println("Animal Info");
    }
}

class Dog implements Animal {}

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();           // default method
        Animal.info();     // static method
    }
}
```

## 9. Can abstract class have constructor? Why?

Yes, abstract class can have a constructor because it is used to initialize common properties when a subclass object is created.

```java
abstract class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }
}

class Dog extends Animal {
    Dog() {
        System.out.println("Dog constructor");
    }
}
```

## 10. Can abstract class have both abstract and concrete methods?

Yes, abstract class can have both abstract methods and concrete methods, which is why it provides partial abstraction.

```java
abstract class Animal {
    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}
```

## 11. Can interface have variables? What type?

Yes, interface can have variables, but they are by default public, static, and final constants.

```java
interface Animal {
    int AGE = 5; // public static final by default
}
```

## 12. When should we use interface vs abstract class?

Use interface when we need 100% abstraction and multiple inheritance. Use abstract class when we need partial abstraction with shared code.

## 13. Can a class implement multiple interfaces?

Yes, a class can implement multiple interfaces to achieve multiple inheritance.

```java id="l3p9sx"
interface A { void show(); }
interface B { void display(); }

class C implements A, B {
    public void show() {}
    public void display() {}
}
```

## 14. Can an abstract class implement an interface?

Yes, abstract class can implement interface and may or may not provide implementation.

```java id="9v2tqg"
interface A {
    void show();
}

abstract class B implements A {
    // no implementation (allowed)
}
```

## 15. Can an interface extend another interface?

Yes, interface can extend one or more interfaces.

```java id="y6m2an"
interface A {
    void show();
}

interface B extends A {
    void display();
}
```


## 16. Can an interface extend a class? (Tricky)

**No.** An interface cannot extend a class. An interface can only extend another **interface**. A class uses `implements` for interfaces and `extends` for classes.

```java
class MyClass { }
// interface MyInterface extends MyClass { }   // ❌ compile error

interface A { void methodA(); }
interface B extends A { void methodB(); }       // ✅ interface extends interface
```


## 17. Can a class extend multiple abstract classes?

**No.** Java does not support multiple class inheritance — a class can only `extend` **one** class (abstract or concrete). Use interfaces for multiple inheritance of behavior.

```java
abstract class A { abstract void methodA(); }
abstract class B { abstract void methodB(); }

// class C extends A, B { }   // ❌ Not allowed

interface IA { void methodA(); }
interface IB { void methodB(); }
class C implements IA, IB {   // ✅ multiple interfaces allowed
    public void methodA() { }
    public void methodB() { }
}
```


## 18. Can interface have constructor?

**No.** Interfaces cannot have constructors because they cannot be instantiated. There is no object state to initialize. Only the implementing class is instantiated.

```java
interface Vehicle {
    // Vehicle() { }   // ❌ compile error
    void start();
}
```


## 19. Can abstract class have constructor?

**Yes.** Abstract classes can have constructors. They are invoked when a subclass object is created via `super()`. Used to initialize common fields.

```java
abstract class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    abstract void work();
}

class Developer extends Employee {
    Developer(String name, double salary) {
        super(name, salary);
    }
    void work() { System.out.println(name + " writes code"); }
}
```


## 20. Can interface have private methods?

**Yes**, from **Java 9** onwards. Private methods in interfaces are used as **helper methods** for `default` or `static` methods to avoid code duplication. They cannot be accessed by implementing classes.

```java
interface Logger {
    default void logInfo(String msg)  { log("INFO", msg); }
    default void logError(String msg) { log("ERROR", msg); }

    private void log(String level, String msg) {   // Java 9+
        System.out.println("[" + level + "] " + msg);
    }
}
```


## 21. Can we override static methods in interface?

**No.** Static methods in interfaces belong to the interface itself and **cannot be overridden** by implementing classes. If a class defines a method with the same name, it is a completely separate method — not an override.

```java
interface Printer {
    static void print() { 
        System.out.println("Interface print"); 
    }
}

class MyPrinter implements Printer {
    static void print() { 
        System.out.println("Class print"); 
    }  // new method, not override
}

Printer.print();        // Interface print
MyPrinter.print();      // Class print
```


## 22. What if two interfaces have same default method?

Java gives a **compile-time error**. The implementing class **must override** the conflicting method. It can then choose which interface's default to call using `InterfaceName.super.method()`.

```java
interface A { default void greet() { System.out.println("Hello from A"); } }
interface B { default void greet() { System.out.println("Hello from B"); } }

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet();   // explicitly choose A's version
    }
}

new C().greet();   // Hello from A
```


## 23. Can abstract class be final?

**No.** `abstract` requires the class to be subclassed (to implement abstract methods), while `final` prevents subclassing. They directly contradict each other — Java gives a compile error.

```java
// abstract final class Shape { }   // ❌ compile error: illegal combination
```


## 24. Can interface have main method?

**Yes**, from **Java 8** onwards. Since interfaces can have `static` methods, they can have a `main` method. The JVM can execute it directly.

```java
interface App {
    static void main(String[] args) {
        System.out.println("Main method inside interface");
    }
}
// Run: java App  → Main method inside interface
```


## 25. Can we use both interface and abstract class together?

**Yes.** This is a very common pattern. An abstract class implements an interface partially, and concrete subclasses complete the implementation.

```java
interface Reportable {
    void generate();
    void export();
}

abstract class BaseReport implements Reportable {
    @Override
    public void export() {                          // common implementation
        System.out.println("Exporting to PDF...");
    }
    // generate() left for subclasses
}

class SalesReport extends BaseReport {
    @Override
    public void generate() {
        System.out.println("Generating Sales Report");
    }
}

SalesReport r = new SalesReport();
r.generate();   // Generating Sales Report
r.export();     // Exporting to PDF...
```


## 26. What access modifiers are allowed in interface?

| Member | Default Modifier | Allowed Modifiers |
|--------|-----------------|-------------------|
| Abstract methods | `public abstract` | `public` only |
| Default methods | `public` | `public` only |
| Static methods | `public` | `public` only |
| Private methods | `private` | `private` only (Java 9+) |
| Variables | `public static final` | `public static final` only |

```java
interface Demo {
    int VALUE = 10;                        // public static final
    void abstractMethod();                 // public abstract
    default void defaultMethod() { }       // public
    static void staticMethod() { }         // public
    private void helperMethod() { }        // private (Java 9+)
}
```


## 27. Can we instantiate interface using lambda?

**Yes**, but only for **functional interfaces** (interfaces with exactly one abstract method). A lambda expression provides the implementation of that single abstract method inline.

```java
@FunctionalInterface
interface Greeting {
    void greet(String name);
}

// Lambda instantiates the functional interface
Greeting g = name -> System.out.println("Hello, " + name + "!");
g.greet("Alice");   // Hello, Alice!

// Another example
Runnable r = () -> System.out.println("Running...");
r.run();
```



## 9. What is `.class` and When do we use in Java?

We use .class when we need to pass class metadata, for example in Spring configuration, exception handling, reflection, and getting beans from the Spring container.


**Common Places Where We Use `.class`**

**1. Spring Configuration**
```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);
```
Spring uses it to read configuration from the class.


**2. Exception Handling**
```java
@ExceptionHandler(Exception.class)
```
Tells Spring: handle this type of exception.


**3. Getting Bean from Spring**
```java
Student s = context.getBean(Student.class);
```
Spring will find bean of type `Student`.


**4. Reflection**
```java
Class<?> c = Student.class;
```
Used in reflection to get class information.

**5. Annotations**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
```
These also use `.class` internally to store metadata.


**`.class` vs `new`**

| `.class` | `new` |
|----------|------|
| Class metadata | Creates object |
| No memory for object | Object created |
| Used by frameworks | Used by developers |

Example:
```java
Student.class   // Class info
new Student()   // Object
```

## 10.  Summary Java Interface & Abstract Class

**🔹 Interface**

* **Interface:** A contract that defines methods a class must implement.
* **Methods:** By default abstract (Java 8+ supports default & static methods).
* **implements keyword:** Used to implement an interface.
* **Multiple Inheritance:** Supported via interfaces.
* **No Constructors:** Interfaces cannot have constructors.
* **Variables:** By default `public static final` (constants).

**🔹 Abstract Class**

* **Abstract Class:** A class that can have both abstract and concrete methods.
* **abstract keyword:** Used to declare abstract class/method.
* **Constructor:** Allowed and used for initialization.
* **Single Inheritance:** A class can extend only one abstract class.
* **Partial Abstraction:** Supports both implemented and unimplemented methods.


# ✅ 06. Java Exception Handling 

## 1. What is an exception in Java?

An **Exception** is an event that occurs during program execution and disrupts the normal flow of the application.

**Key Features**

* Represents runtime errors
* Can be **handled** using `try-catch`
* Helps prevent application crashes
* Provides information about the error

**How It Works**

When an error occurs, Java creates an **Exception Object** and throws it. The exception can be caught and handled using a **try-catch** block.

**Code Example**

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

**Why to Use**

* To handle errors gracefully
* To prevent application termination
* To improve application reliability
* To provide meaningful error messages

**When to Use**

Use **Exception Handling** whenever an operation may fail.

Examples:

* Reading a file
* Connecting to a database
* Calling an external API
* Performing mathematical operations

**Types of Exceptions**

* **Checked Exceptions** – Checked at compile time, e.g., `IOException`, `SQLException`
* **Unchecked Exceptions** – Occur at runtime, e.g., `NullPointerException`, `ArithmeticException`


## 2. What is the exception hierarchy in Java?

Java's **exception hierarchy** starts with Throwable class, which has two main branches: Error and Exception. Exception further divides into checked and unchecked exceptions.

```
Throwable
├── Error (unchecked)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── IOException (checked)
    ├── SQLException (checked)
    └── RuntimeException (unchecked)
        ├── NullPointerException
        ├── ArithmeticException
        └── ArrayIndexOutOfBoundsException
```

- **Error:** System-level problems, usually unrecoverable
- **Exception:** Application-level problems that can be handled

## 3. What are checked and unchecked exceptions?

**Checked exceptions** must be handled at compile time(IOException, SQLExcepti), while **unchecked exceptions** occur at runtime(NullPointerException, ArithmeticException) and don't require mandatory handling.

```java
// Checked - must handle
try {
    FileReader file = new FileReader("file.txt");
} catch (IOException e) { }

// Unchecked - optional handling
int[] arr = {1, 2, 3};
int value = arr[5]; // ArrayIndexOutOfBoundsException
```

## 4. What is the difference between throw and throws?

Both **`throw`** and **`throws`** are used in **Exception Handling**, but they serve different purposes.

**Key Difference**

* **`throw`** is used to explicitly throw an exception.
* **`throws`** is used to declare that a method may throw an exception.

**How It Works**

**`throw`**

Used inside a method body to create and throw an exception object.

```java
public void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or above");
    }
}
```

**`throws`**

Used in the method signature to indicate that the method may throw an exception.

```java
public void readFile() throws IOException {
    FileReader file = new FileReader("data.txt");
}
```

**Key Features**

| **throw**                             | **throws**                        |
| ------------------------------------- | --------------------------------- |
| Used to throw an exception            | Used to declare exceptions        |
| Used inside a method                  | Used in method signature          |
| Throws one exception object at a time | Can declare multiple exceptions   |
| Followed by an exception object       | Followed by exception class names |

**Why to Use**

**`throw`**

* To manually generate an exception
* To validate business rules
* To stop execution when an error occurs

**`throws`**

* To inform the caller about possible exceptions
* To delegate exception handling to another method

**When to Use**

Use **`throw`** when you want to explicitly raise an exception.

Use **`throws`** when a method may produce an exception and you want the caller to handle it.


## 5. What is try-catch-finally block?

**Try-catch-finally** is Java's **exception handling** mechanism where try contains risky code, catch handles exceptions, and finally executes cleanup code regardless of exceptions.

- **try:** Contains code that might throw exceptions
- **catch:** Handles specific exceptions
- **finally:** Always executes (cleanup code)

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("This always executes");
}
```


## 6. How do you create custom exceptions?

**Custom exceptions** are created by extending Exception class for checked exceptions or RuntimeException for unchecked exceptions. They provide specific error information for your application.

- Extend Exception (checked) or RuntimeException (unchecked)
- Provide constructors for different scenarios
- Add custom fields/methods if needed

```java
// Checked custom exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Unchecked custom exception
class InsufficientBalanceException extends RuntimeException {
    private double balance;
    
    public InsufficientBalanceException(String message, double balance) {
        super(message);
        this.balance = balance;
    }
}
```

## 7. What is exception chaining?

**Exception chaining** links exceptions together, preserving the original cause when wrapping exceptions. It helps maintain the complete error trail for better debugging.

- Preserves original exception information
- Uses initCause() method or constructor parameter
- Helps in debugging complex error scenarios
- Maintains exception stack trace

```java
try {
    // Some database operation
    connectToDatabase();
} catch (SQLException e) {
    // Chain the original exception
    throw new ServiceException("Service failed", e);
}

// Or using initCause()
RuntimeException re = new RuntimeException("Wrapper exception");
re.initCause(originalException);
throw re;
```

## 8. How do you Handle Global Exception?

In Spring Boot, we handle exceptions using `@RestControllerAdvice` for global exception handling and `@ExceptionHandler` to catch specific exceptions. When an exception occurs in the controller, it is handled in one central place instead of writing try-catch everywhere.


**Global exception handling is implemented using @ControllerAdvice in Spring Boot to handle exceptions across the entire application in a centralized way.**

Other than this There are **two ways** depending on the application type:

| Application Type | Global Exception Handling                   |
| ---------------- | ------------------------------------------- |
| Core Java        | try-catch / Thread.UncaughtExceptionHandler |
| Spring Boot      | @ControllerAdvice                           |


**1. Global Exception Handling in Core Java**

Use **UncaughtExceptionHandler** to handle exceptions globally.

```java
class TestApp {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptioHandler());
        Thread tr = new Thread(() ->{
            throw new RuntimeException("There is erro");
        });
        tr.start();
    }
}
class ExceptioHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException (Thread t, Throwable e) {
        System.out.println("Global Exception caught " + e.getMessage());
    }
}
```

**2. Global Exception Handling in Spring Boot (@ControllerAdvice, @RestControllerAdvice
)**

This handles exceptions for the **entire application**

```java
import org.springframework.web.bind.annotation.ControllerAdvice;

// GlobalExceptionHandler.java
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}

// VotingController.java
@RestController
@RequestMapping("/api")
class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping("/vote")
    public ResponseEntity<String> vote(@RequestParam int age) {
        votingService.vote(age);
        return ResponseEntity.ok("Vote successful");
    }
}
```

**REST API Global Exception**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(),
                                     HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## 9. Difference between ClassNotFoundException vs NoClassDefFoundError?


`ClassNotFoundException` occurs when JVM tries to load a class dynamically using `Class.forName()`, but the class is not found in the classpath.

`NoClassDefFoundError` occurs when the class was available during compilation, but it is missing at runtime during deployment.

In short, **ClassNotFoundException happens during dynamic loading, whereas NoClassDefFoundError happens when a required class is missing at runtime.**

**Example: ClassNotFoundException**

```java id="95q2oc"
Class.forName("com.company.Employee");
```

If the class does not exist:

```text id="kj1icn"
ClassNotFoundException
```

**Example: NoClassDefFoundError**

```java id="72tqok"
Employee emp = new Employee();
```

The code compiles successfully, but if `Employee.class` is missing from the deployed JAR:

```text id="xhk56m"
NoClassDefFoundError
```


## 10. What happens if a class is missing in a JAR during deployment?


If a class is available during compilation but missing in the deployed JAR, the application will fail at runtime with a `NoClassDefFoundError`.

This usually happens because a required dependency was not packaged, the wrong JAR version was deployed, or a JAR is missing from the classpath.

```java id="7uokki"
Employee emp = new Employee();
```

The code compiles successfully because `Employee.class` was available during compilation.

But if `Employee.class` is missing in production:

```text id="gdv5qi"
java.lang.NoClassDefFoundError:
com/company/Employee
```

**Common Reasons**

* Missing dependency JAR
* Incorrect JAR version
* Dependency not included in the build
* Classpath configuration issue


## 11. What is try-with-resources?

**Try-with-resources** in **Java** is a feature used to **automatically close resources** (like files or database connections) after the program finishes using them.


```java
// Old way
FileReader file = null;
try {
    file = new FileReader("data.txt");
} finally {
    if (file != null) file.close();
}

// Try-with-resources
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
} // Automatically closed
```

# ✅ 07. Java Collections Framework

## 0. What is Java Collections?

**Java Collections Framework (JCF)** is a set of **classes** and **interfaces** used to store, manage, and manipulate groups of objects dynamically.

Instead of using arrays with fixed size, Collections provide flexible data structures such as **List**, **Set**, **Queue**, and **Map**.

**Key Features**

* Dynamic size
* Ready-made data structures
* Supports searching, sorting, and filtering
* Improves code reusability
* Provides high-performance implementations

**How it Works**

The Collection Framework provides **interfaces** and their **implementations**:

| Interface | Implementation Examples   |
| --------- | ------------------------- |
| **List**  | ArrayList, LinkedList     |
| **Set**   | HashSet, TreeSet          |
| **Queue** | PriorityQueue, LinkedList |
| **Map**   | HashMap, TreeMap          |

You program against the interface and use a specific implementation based on your requirement.

**Why Use Collections?**

* To store multiple objects efficiently
* To avoid fixed-size limitations of arrays
* To perform common operations easily
* To improve readability and maintainability

**When to Use Which Collection?**

* **ArrayList** → Frequent reading/access
* **LinkedList** → Frequent insertions/deletions
* **HashSet** → Unique elements only
* **HashMap** → Key-value storage
* **TreeSet/TreeMap** → Sorted data
* **Queue** → FIFO processing

**Example**

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("John");
        names.add("Alice");
        names.add("Bob");

        System.out.println(names);
    }
}
```

**Output:**

```java
[John, Alice, Bob]
```

**Important Interfaces**

```java
Collection
   ├── List
   ├── Set
   └── Queue

Map (separate hierarchy)
```

Here’s a cleaner and corrected version of your table:

```
Interface    | Common Classes(implementations)        | Description----------|----------------------------------------|-----------------------------------------
List         | ArrayList, LinkedList                  | Ordered collection that allows duplicates
Set          | HashSet, TreeSet, LinkedHashSet        | Collection of unique elements (no duplicates)
Map          | HashMap, TreeMap, LinkedHashMap        | Stores key-value pairs with unique keys
```

ArrayList : add(), get(), set(), and remove() to manage your list of elements.
LinkedList

## 1. Java Collections and Their Best Use Cases?

| Requirement                         | Best Collection     | Why                               | Mostly Used In                       |
| ----------------------------------- | ------------------- | --------------------------------- | ------------------------------------ |
| Fast search/access by key           | `HashMap`           | Average O(1) lookup using hashing | Caching, APIs, lookup tables         |
| Fast unique element lookup          | `HashSet`           | Fast contains/search operations   | Removing duplicates, validations     |
| Fast indexed access                 | `ArrayList`         | O(1) random access using index    | UI lists, data retrieval             |
| Thread-safe fast access             | `ConcurrentHashMap` | Better concurrent performance     | Multithreaded applications           |
| Sorted key-value storage            | `TreeMap`           | Stores keys in sorted order       | Ranking systems, sorted reports      |
| Maintain insertion order            | `LinkedHashMap`     | Keeps insertion order             | LRU cache, ordered APIs              |
| Fast insert/delete at beginning/end | `LinkedList`        | Efficient node insertion/removal  | Queue, stack implementations         |
| Priority-based processing           | `PriorityQueue`     | Processes elements by priority    | Scheduling systems, task processing  |
| Auto-remove unused keys             | `WeakHashMap`       | Keys removed by Garbage Collector | Memory-sensitive cache               |
| Reference equality comparison       | `IdentityHashMap`   | Uses `==` instead of `equals()`   | Framework internals, object tracking |


**Quick Complexity Overview**

| Collection      | Search     | Insert         | Ordered               |
| --------------- | ---------- | -------------- | --------------------- |
| `HashMap`       | O(1)       | O(1)           | No                    |
| `TreeMap`       | O(log n)   | O(log n)       | Yes (sorted)          |
| `LinkedHashMap` | O(1)       | O(1)           | Yes (insertion order) |
| `ArrayList`     | O(1) index | O(1) append    | Yes                   |
| `LinkedList`    | O(n)       | O(1) insertion | Yes                   |
| `HashSet`       | O(1)       | O(1)           | No                    |
| `PriorityQueue` | O(log n)   | O(log n)       | Priority order        |



## 2. What is the difference between ArrayList and LinkedList?


**Definition**

Both **`ArrayList`** and **`LinkedList`** are implementations of the **`List`** interface in the Java Collections Framework, but they use different internal data structures.

* **`ArrayList`** uses a **dynamic array**.
* **`LinkedList`** uses a **doubly linked list**.


**Key Differences**

| **Feature**                      | **ArrayList**           | **LinkedList**                        |
| -------------------------------- | ----------------------- | ------------------------------------- |
| **Internal Structure**           | Dynamic Array           | Doubly Linked List                    |
| **Access by Index (`get`)**      | **Fast - O(1)**         | **Slow - O(n)**                       |
| **Insertion/Deletion at End**    | Fast - O(1) (amortized) | Fast - O(1)                           |
| **Insertion/Deletion in Middle** | Slow - O(n)             | Faster - O(1) after reaching the node |
| **Memory Usage**                 | Less memory             | More memory (extra pointers)          |
| **Implements**                   | `List`                  | `List` and `Deque`                    |
| **Best For**                     | Frequent reading        | Frequent insertion/deletion           |

**How It Works**

* **`ArrayList`**

  * Stores elements in a **contiguous array**.
  * If the array becomes full, it automatically creates a larger array and copies existing elements.

* **`LinkedList`**

  * Stores each element in a **node**.
  * Each node contains the **data**, a **reference to the next node**, and a **reference to the previous node**.

**Why to Use**

* Use **`ArrayList`** when your application performs **frequent reads or searches**.
* Use **`LinkedList`** when your application performs **frequent insertions or deletions**, especially at the beginning or middle of the list.

**When to Use**

| **Scenario**                     | **Best Choice** |
| -------------------------------- | --------------- |
| Frequent element access by index | **ArrayList**   |
| Frequent insertion/deletion      | **LinkedList**  |
| Large number of read operations  | **ArrayList**   |
| Queue or Deque implementation    | **LinkedList**  |

**Code Example**

```java id="n5r7uk"
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Spring");

        List<String> linkedList = new LinkedList<>();
        linkedList.add("Java");
        linkedList.add("Spring");

        System.out.println(arrayList.get(1));   // Fast access
        System.out.println(linkedList.get(1));  // Slower access
    }
}
```

**Performance Comparison**

| **Operation**                         | **ArrayList**    | **LinkedList**       |
| ------------------------------------- | ---------------- | -------------------- |
| **Add at End**                        | O(1) (amortized) | O(1)                 |
| **Add/Delete at Beginning or Middle** | O(n)             | O(1) after traversal |
| **Get by Index**                      | O(1)             | O(n)                 |
| **Search**                            | O(n)             | O(n)                 |

**Key Features**

* **`ArrayList`**

  * Fast random access.
  * Better cache locality.
  * Lower memory overhead.

* **`LinkedList`**

  * Efficient insertion and deletion.
  * Can act as both a **List** and a **Queue/Deque**.
  * No need to resize an internal array.


## 3. What is the difference between HashMap and TreeMap?

**Definition**

Both **`HashMap`** and **`TreeMap`** are implementations of the **`Map`** interface used to store **key-value pairs**, but they differ in how they store and organize data.

* **`HashMap`** stores entries using a **hash table**.
* **`TreeMap`** stores entries using a **Red-Black Tree** (a self-balancing binary search tree).


**Key Differences**

| **Feature**                                  | **HashMap**                   | **TreeMap**                    |
| -------------------------------------------- | ----------------------------- | ------------------------------ |
| **Internal Structure**                       | Hash Table                    | Red-Black Tree                 |
| **Order of Keys**                            | No ordering                   | Keys are automatically sorted  |
| **Time Complexity (`put`, `get`, `remove`)** | **O(1)** average              | **O(log n)**                   |
| **Null Keys**                                | Allows **one** `null` key     | Does **not** allow `null` keys |
| **Null Values**                              | Allows multiple `null` values | Allows multiple `null` values  |
| **Implements**                               | `Map`                         | `NavigableMap`, `SortedMap`    |
| **Best For**                                 | Fast lookup and insertion     | Sorted data and range queries  |

**How It Works**

* **`HashMap`**

  * Calculates the **`hashCode()`** of the key.
  * Uses the hash value to find the appropriate bucket.
  * If multiple keys map to the same bucket, it uses **`equals()`** to find the correct entry.

* **`TreeMap`**

  * Stores keys in a **Red-Black Tree**.
  * Automatically keeps keys in **ascending order** (or custom order using a `Comparator`).

**Why to Use**

* Use **`HashMap`** when you need **fast access** and do not care about the order of keys.
* Use **`TreeMap`** when you need **sorted keys** or operations like finding the smallest, largest, or a range of keys.

**When to Use**

| **Scenario**                         | **Best Choice** |
| ------------------------------------ | --------------- |
| Fast insert and lookup               | **HashMap**     |
| Maintain sorted order of keys        | **TreeMap**     |
| Range queries (`headMap`, `tailMap`) | **TreeMap**     |
| General-purpose key-value storage    | **HashMap**     |

**Code Example**

```java id="9ifxrf"
import java.util.HashMap;
import java.util.TreeMap;

public class Demo {
    public static void main(String[] args) {

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "C");
        hashMap.put(1, "A");
        hashMap.put(2, "B");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "C");
        treeMap.put(1, "A");
        treeMap.put(2, "B");

        System.out.println(hashMap); // Order not guaranteed
        System.out.println(treeMap); // {1=A, 2=B, 3=C}
    }
}
```

**Performance Comparison**

| **Operation**    | **HashMap**  | **TreeMap** |
| ---------------- | ------------ | ----------- |
| **`put()`**      | O(1) average | O(log n)    |
| **`get()`**      | O(1) average | O(log n)    |
| **`remove()`**   | O(1) average | O(log n)    |
| **Key Ordering** | No           | Yes         |

**Key Features**

* **`HashMap`**

  * Very fast access.
  * Unordered collection.
  * Uses **`hashCode()`** and **`equals()`** internally.

* **`TreeMap`**

  * Maintains keys in **sorted order**.
  * Supports **`firstKey()`**, **`lastKey()`**, **`headMap()`**, and **`tailMap()`**.
  * Uses **natural ordering** or a custom **`Comparator`**.


## 4. What is the difference between HashMap Hashtable?

**Definition**

Both **`HashMap`** and **`Hashtable`** are implementations of the **`Map`** interface used to store **key-value pairs**, but they differ mainly in **thread safety**, **performance**, and **handling of `null` values**.


**Key Differences**

| **Feature**                  | **HashMap**                   | **Hashtable**                                     |
| ---------------------------- | ----------------------------- | ------------------------------------------------- |
| **Thread Safety**            | Not synchronized              | Synchronized                                      |
| **Performance**              | Faster                        | Slower due to synchronization                     |
| **Null Key**                 | Allows **one** `null` key     | Not allowed                                       |
| **Null Values**              | Allows multiple `null` values | Not allowed                                       |
| **Introduced In**            | Java 1.2                      | Java 1.0                                          |
| **Inheritance**              | Extends `AbstractMap`         | Extends `Dictionary`                              |
| **Iterator Support**         | Uses `Iterator`               | Uses `Enumeration` and `Iterator`                 |
| **Recommended for New Code** | Yes                           | No (prefer `ConcurrentHashMap` for thread safety) |

**How It Works**

* **`HashMap`**

  * Uses a **hash table** and stores data based on the **`hashCode()`** of the key.
  * Not thread-safe, so multiple threads can cause inconsistent results without external synchronization.

* **`Hashtable`**

  * Also uses a **hash table**, but all its methods are **synchronized**.
  * Only one thread can access it at a time, making it thread-safe but reducing performance.

**Why to Use**

* Use **`HashMap`** for **single-threaded** applications or when synchronization is handled externally.
* Use **`Hashtable`** only for maintaining compatibility with legacy code. For modern multi-threaded applications, **`ConcurrentHashMap`** is preferred.

**When to Use**

| **Scenario**                          | **Best Choice**       |
| ------------------------------------- | --------------------- |
| Single-threaded application           | **HashMap**           |
| High-performance map operations       | **HashMap**           |
| Legacy code requiring synchronization | **Hashtable**         |
| Modern multi-threaded application     | **ConcurrentHashMap** |

**Code Example**

```java id="bzhv9w"
import java.util.HashMap;
import java.util.Hashtable;

public class Demo {
    public static void main(String[] args) {

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Java");
        hashMap.put(null, "Spring"); // Allowed

        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(1, "Java");
        // hashtable.put(null, "Spring"); // Throws NullPointerException

        System.out.println(hashMap);
        System.out.println(hashtable);
    }
}
```

**Performance Comparison**

| **Operation**     | **HashMap**  | **Hashtable**                                |
| ----------------- | ------------ | -------------------------------------------- |
| **`put()`**       | O(1) average | O(1) average (slower due to synchronization) |
| **`get()`**       | O(1) average | O(1) average (slower due to synchronization) |
| **Thread Safety** | No           | Yes                                          |

**Key Features**

* **`HashMap`**

  * Fast and efficient.
  * Allows one `null` key and multiple `null` values.
  * Not synchronized.

* **`Hashtable`**

  * Thread-safe because methods are synchronized.
  * Does not allow `null` keys or values.
  * Considered a **legacy class**.


## 5. How does HashMap work internally?

**Definition**

A **`HashMap`** is a data structure in Java that stores data as **key-value pairs**. Internally, it uses a **hash table** to provide very fast **insertion**, **deletion**, and **lookup** operations.


**How It Works**

**1. Calculate `hashCode()`**

* When you call `put(key, value)`, Java calls the key's **`hashCode()`** method.

```java id="drgqhj"
int hash = key.hashCode();
```

**2. Find the Bucket Index**

* The hash value is converted into an array index (bucket index).

```java id="v7g0w7"
index = hash % arraySize;
```

(Java internally uses a more optimized calculation.)

**3. Store the Entry**

* If the bucket is empty, the key-value pair is stored there.
* If the bucket already contains data (**hash collision**), Java stores multiple entries in the same bucket.

**4. Handle Hash Collisions**

* In **Java 7 and earlier**, collisions are handled using a **Linked List**.
* In **Java 8 and later**, if the number of entries in a bucket exceeds a threshold (default **8**), the linked list is converted into a **Red-Black Tree** for faster searching.

**5. Retrieve the Value**

* When `get(key)` is called:

  1. Java calculates the key's **`hashCode()`**.
  2. Finds the correct bucket.
  3. Uses **`equals()`** to compare keys if multiple entries exist in the bucket.
  4. Returns the matching value.

**Internal Flow**

```text id="x6f7fh"
put(key, value)
      │
      ▼
Call key.hashCode()
      │
      ▼
Calculate Bucket Index
      │
      ▼
Bucket Empty?
   Yes      No
    │        │
 Store    Check equals()
 Object      │
             ▼
      Add to Linked List
      or Red-Black Tree
```

**Key Features**

* Stores data as **Key-Value Pairs**.
* Uses **`hashCode()`** and **`equals()`** internally.
* Average **O(1)** time complexity for `put()` and `get()`.
* Handles collisions using **Linked List** or **Red-Black Tree**.
* Allows **one `null` key** and multiple `null` values.
* Not thread-safe.

**Why to Use**

* Very fast data retrieval and insertion.
* Efficient for storing and searching large amounts of data.
* Widely used for caching, indexing, and lookup operations.

**When to Use**

| **Scenario**                          | **Use `HashMap`?**          |
| ------------------------------------- | --------------------------- |
| Fast key-value lookup                 | **Yes**                     |
| Data order is not important           | **Yes**                     |
| Frequent insert and search operations | **Yes**                     |
| Need sorted keys                      | **No, use `TreeMap`**       |
| Multi-threaded access                 | **Use `ConcurrentHashMap`** |

**Role of `equals()` and `hashCode()`**

* **`hashCode()`** determines the **bucket**.
* **`equals()`** identifies the **exact key** inside the bucket.

This is why **if you override `equals()`, you must also override `hashCode()`**.

**Code Example**

```java id="xk9yau"
import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(101, "Alice");
        map.put(102, "Bob");

        System.out.println(map.get(101)); // Alice
    }
}
```

**Time Complexity**

| **Operation**  | **Average Case** | **Worst Case**                 |
| -------------- | ---------------- | ------------------------------ |
| **`put()`**    | O(1)             | O(log n) (after treeification) |
| **`get()`**    | O(1)             | O(log n) (after treeification) |
| **`remove()`** | O(1)             | O(log n) (after treeification) |


## 6. What is hash collision and how is it handled?

**Hash collision** occurs when **two different keys produce the same hash code**, mapping to the same bucket. HashMap handles this using chaining and tree conversion.

- **Chaining:** Multiple entries in same bucket form linked list
- **Tree conversion:** When chain length > 8, converts to balanced tree
- **Load factor:** Rehashing when buckets become too full
- **Open addressing:** Alternative approach (not used in HashMap)

**In simple words:** Collision means two keys go to the same bucket, and HashMap handles it using linked list or tree structure.

```java
// Collision example:
// hash("Aa") and hash("BB") might produce same value
map.put("Aa", 1);
map.put("BB", 2); // Collision - stored in same bucket as linked list
```

## 7. What is the difference between Comparable and Comparator?

**Comparable** is used for **natural sorting** and defines the `compareTo()` method inside the same class. It allows **only one sorting logic**.

**Comparator** is used for **custom sorting** and defines the `compare()` method in a separate class. It allows **multiple sorting logics**.

**In simple words:** Comparable = default sorting, Comparator = custom sorting.x


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

**Comparable vs Comparator**
| Feature               | Comparable    | Comparator    |
| --------------------- | ------------- | ------------- |
| Package               | `java.lang`   | `java.util`   |
| Method                | `compareTo()` | `compare()`   |
| Sorting Logic         | Inside class  | Outside class |
| Number of Sort Orders | One           | Multiple      |
| Modification Needed   | Yes           | No            |


## 8. What is WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?

* **WeakHashMap** – A map where keys are stored with **weak references**, so entries can be removed automatically by the **Java Garbage Collector** when keys are no longer used.
* **IdentityHashMap** – A map that compares keys using **reference equality (`==`) instead of `equals()`**.
* **LinkedHashMap** – A map that **maintains insertion order** using a linked list along with a hash table.
* **PriorityQueue** – A queue that **orders elements based on priority (natural order or comparator)** instead of insertion order.

```java
Map<String, Integer> weakMap = new WeakHashMap<>(); // GC-friendly, Used for: caching
Map<String, Integer> identityMap = new IdentityHashMap<>(); // for key comparison
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // Ordered, LRU cache implementations
Queue<Integer> priorityQueue = new PriorityQueue<>(); // Heap-based, processed based on priority
```

**1. WeakHashMap Example :** A map where keys are stored with **weak references**, so entries can be removed automatically by the **Java Garbage Collector** when keys are no longer used.

**Common use :** -Caching, -Memory-sensitive applications

```java 
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapExample {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> weakMap = new WeakHashMap<>();

        String key1 = new String("Java");
        weakMap.put(key1, 100);

        System.out.println("Before GC: " + weakMap);

        // Remove strong reference
        key1 = null;

        // Request garbage collection
        System.gc();

        // Wait for GC
        Thread.sleep(2000);

        System.out.println("After GC: " + weakMap);
    }
}
// Output: 
Before GC: {Java=100}
After GC: {}
```


**2. IdentityHashMap Example :** A map that compares keys using **reference equality (`==`) instead of `equals()`**.

Compares keys using `==`  instead of: `equals()`;


```java id="p8d0mc"
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapExample {

    public static void main(String[] args) {

        Map<String, Integer> identityMap = new IdentityHashMap<>();

        String s1 = new String("Java");
        String s2 = new String("Java");

        identityMap.put(s1, 1);
        identityMap.put(s2, 2);

        System.out.println(identityMap);
    }
}
// Output: {Java=1, Java=2}
// Why : s1.equals(s2) --> true
// But: s1 == s2 --> false

So both keys are treated separately.
```


**3. LinkedHashMap Example :** A map that **maintains insertion order** using a linked list along with a hash table. Maintains insertion order.


Useful for: * Ordered maps, * LRU Cache


```java id="fjlwm5"
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        Map<Integer, String> linkedMap = new LinkedHashMap<>();

        linkedMap.put(3, "Java");
        linkedMap.put(1, "Spring");
        linkedMap.put(2, "Hibernate");

        System.out.println(linkedMap);
    }
}
// Output: {3=Java, 1=Spring, 2=Hibernate}
```


**LinkedHashMap as LRU Cache**

```java id="r6xkm0"
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheExample {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> cache = new LinkedHashMap<>(3, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 3;
            }
        };

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1); // Access key 1

        cache.put(4, "D");

        System.out.println(cache);
    }
}
// Output: {3=C, 1=A, 4=D}
// Output: Key `2` removed because it was least recently used.
```


**4. PriorityQueue Example :** A queue that **orders elements based on priority (natural order or comparator)** instead of insertion order.


```java id="gqws4s"
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(50);
        priorityQueue.offer(20);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
}
// Output: 
// 10
// 20
// 30
// 50
```


**Max Heap Example**

```java id="xj5pc5"
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeapExample {

    public static void main(String[] args) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}
// Output: 
// 50
// 20
// 10
```


**Summary**

| Collection        | Special Feature                  | Common Use                  |
| ----------------- | -------------------------------- | --------------------------- |
| `WeakHashMap`     | Removes entries after GC         | Cache                       |
| `IdentityHashMap` | Uses `==` for key comparison     | Object identity tracking    |
| `LinkedHashMap`   | Maintains insertion/access order | LRU Cache                   |
| `PriorityQueue`   | Heap-based priority processing   | Scheduling, task processing |


## 9. How to Implement LRU, LFU and TTL Cache

**# 1. LRU Cache (Least Recently Used) :** Removes the least recently accessed item.

```java id="m5nz5d"
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {

        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);

        cache.put(4, "D");

        System.out.println(cache);
    }
}
// Output: {3=C, 1=A, 4=D}
// `2=B` removed because it was least recently used.
```


**2. LFU Cache (Least Frequently Used):** Removes least frequently accessed item.

```java id="gh5c2v"
import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private Map<Integer, String> cache = new HashMap<>();
    private Map<Integer, Integer> frequency = new HashMap<>();

    public void put(int key, String value) {
        cache.put(key, value);
        frequency.put(key, 1);
    }

    public String get(int key) {

        if (!cache.containsKey(key)) {
            return null;
        }

        frequency.put(key, frequency.get(key) + 1);

        return cache.get(key);
    }

    public void removeLFU() {

        int minFreq = Integer.MAX_VALUE;
        int lfuKey = -1;

        for (int key : frequency.keySet()) {

            if (frequency.get(key) < minFreq) {
                minFreq = frequency.get(key);
                lfuKey = key;
            }
        }

        cache.remove(lfuKey);
        frequency.remove(lfuKey);
    }

    public void print() {
        System.out.println(cache);
    }

    public static void main(String[] args) {

        LFUCache cache = new LFUCache();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);
        cache.get(1);

        cache.get(2);

        cache.removeLFU();

        cache.print();
    }
}
// Output: {1=A, 2=B}
// `3=C` removed because it was least frequently used.
```


**3. TTL Cache (Time To Live) :** Expires data after fixed duration.

```java id="9m7nlj"
import java.util.HashMap;
import java.util.Map;

class TTLCache {

    static class CacheObject {
        String value;
        long expiryTime;

        CacheObject(String value, long ttlMillis) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }
    }

    private Map<Integer, CacheObject> cache = new HashMap<>();

    public void put(int key, String value, long ttlMillis) {
        cache.put(key, new CacheObject(value, ttlMillis));
    }

    public String get(int key) {

        CacheObject obj = cache.get(key);

        if (obj == null) {
            return null;
        }

        if (System.currentTimeMillis() > obj.expiryTime) {
            cache.remove(key);
            return null;
        }

        return obj.value;
    }

    public static void main(String[] args) throws Exception {

        TTLCache cache = new TTLCache();

        cache.put(1, "Java", 3000);

        System.out.println(cache.get(1));

        Thread.sleep(4000);

        System.out.println(cache.get(1));
    }
}
// Output: 
// Java
// null
// After 3 seconds, value expires.
```


**Quick Interview Summary**

| Cache Type | Removes Based On      | Mostly Used In                    |
| ---------- | --------------------- | --------------------------------- |
| LRU        | Least recently used   | Browser cache, Redis              |
| LFU        | Least frequently used | Analytics, recommendation systems |
| TTL        | Time expiration       | Session cache, tokens, OTPs       |



## 10. Difference between ConcurrentHashMap and HashMap, and when to use what?

| Feature              | HashMap                        | ConcurrentHashMap                        |
|----------------------|--------------------------------|------------------------------------------|
| Thread Safety        | Not thread-safe                | Thread-safe                              |
| Null keys/values     | Allows one null key, null values | Does NOT allow null key or null value  |
| Performance          | Faster in single-threaded      | Slightly slower due to locking           |
| Locking mechanism    | None                           | Segment-level / bucket-level locking     |
| Fail behavior        | Throws ConcurrentModificationException | Does not throw it               |

**When to use:**
- Use `HashMap` in single-threaded or read-only scenarios.
- Use `ConcurrentHashMap` in multi-threaded environments where multiple threads read/write simultaneously (e.g., caches, shared state).


## 12. Difference between List and Set? 


The main difference is that a **List allows duplicate elements and maintains insertion order**, whereas a **Set does not allow duplicates and typically stores only unique elements**.

We use a List when duplicates are allowed and order matters. We use a Set when uniqueness is required.


**List Example**

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Java");

System.out.println(list);
```

**Output:**

```text
[Java, Spring, Java]
```

Duplicates are allowed.


**Set Example**

```java
Set<String> set = new HashSet<>();

set.add("Java");
set.add("Spring");
set.add("Java");

System.out.println(set);
```

**Output:**

```text
[Java, Spring]
```

Duplicate value is ignored.

| List                           | Set                                      |
| ------------------------------ | ---------------------------------------- |
| Allows duplicates              | No duplicates                            |
| Maintains insertion order      | Unique elements only                     |
| Supports index-based access    | No index-based access                    |
| Example: ArrayList, LinkedList | Example: HashSet, LinkedHashSet, TreeSet |


## 13. Difference between List and Array? 

The main difference is that an **Array has a fixed size**, while a **List is dynamic and can grow or shrink at runtime**.

Arrays can store both primitive types and objects, whereas a List stores only objects.

In modern Java applications, we usually prefer a List because it is more flexible and provides many built-in methods.


**Array Example**

```java id="h4vmyv"
int[] numbers = new int[3];

numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
```

Size is fixed to 3 and cannot be increased.


**List Example**

```java id="1jrr4h"
List<Integer> numbers =
        new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
```

The List grows dynamically as elements are added.

| Array                         | List                         |
| ----------------------------- | ---------------------------- |
| Fixed size                    | Dynamic size                 |
| Stores primitives and objects | Stores objects only          |
| Faster                        | More flexible                |
| Fewer built-in methods        | Rich Collection APIs         |
| Part of Java language         | Part of Collection Framework |


## 14. How do you convert a List to an Array? 

```java
List<String> names = List.of("John", "David", "Mike");

String[] arr = names.toArray(new String[0]);
// [John, David, Mike]
```
**Why new String[0]?**

The JVM creates an array of the correct size automatically.

## 15. How does map.get(key) work internally?

`map.get(key)` first calculates the key's `hashCode()` to find the bucket. Then it uses `equals()` to locate the exact key in that bucket and returns the associated value. This is why HashMap retrieval is usually O(1).

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "John");

String name = map.get(101);
```

## 16. What is O(1) and  O(n)?


**Big O Notation** is used to measure how the **execution time** or **memory usage** of an algorithm grows as the input size increases.

**- O(1) – Constant Time**

**Definition:**
The operation takes the **same amount of time**, regardless of the size of the input.

**How it works:**
The algorithm directly accesses the required element without looping through data.

**Key Features:**

* **Fastest** time complexity
* Performance remains **constant**
* Input size does not affect execution time

**Why use it?**

* Provides the best performance
* Suitable for direct lookups and access operations

**When to use it?**

* Accessing an array element by index
* Getting a value from a hash-based collection like `HashMap`

**Example:**

```java
int[] arr = {10, 20, 30, 40};

System.out.println(arr[2]); // 30
```

Here, accessing `arr[2]` always takes the same time, so the complexity is **O(1)**.


**- O(n) – Linear Time**

**Definition:**
The execution time grows **proportionally** to the input size.

**How it works:**
The algorithm processes each element one by one.

**Key Features:**

* Time increases as data size increases
* Requires traversing the collection
* Common in searching and iteration operations

**Why use it?**

* Necessary when every element must be checked
* Simple and easy to implement

**When to use it?**

* Searching in an unsorted array or list
* Iterating through all elements

**Example:**

```java
int[] arr = {10, 20, 30, 40, 50};

for (int num : arr) {
    System.out.println(num);
}
```

The loop visits every element once, so the complexity is **O(n)**.



## 12. Java Collections Framework

**🔹 Basics**

* **Java Collections Framework (JCF):** A set of classes and interfaces used to store and manipulate groups of data.
* **Collection Interface:** Root interface for most collection types (List, Set, Queue).
* **Map Interface:** Stores key-value pairs (not part of Collection interface).
fWhat is JDBC

**🔹 List (Ordered, Allows Duplicates)**

* **List:** Maintains insertion order and allows duplicate elements.
* **ArrayList:** Dynamic array, fast for read operations.
* **LinkedList:** Doubly linked list, fast for insert/delete operations.
* **Vector:** Thread-safe but slower (legacy).


**🔹 Set (No Duplicates)**

* **Set:** Does not allow duplicate elements.
* **HashSet:** Unordered, fast performance.
* **LinkedHashSet:** Maintains insertion order.
* **TreeSet:** Sorted order (uses Red-Black Tree).


**🔹 Map (Key-Value Pair)**

* **Map:** Stores data as key-value pairs.
* **HashMap:** Unordered, allows one null key.
* **LinkedHashMap:** Maintains insertion order.
* **TreeMap:** Sorted by keys.
* **Hashtable:** Thread-safe but legacy.


**🔹 Queue**

* **Queue:** Follows FIFO (First In First Out).
* **PriorityQueue:** Elements processed based on priority.
* **Deque:** Double-ended queue (add/remove from both ends).


**🔹 Important Concepts**

* **Iterator:** Used to traverse collection elements.
* **Comparable:** Used for natural sorting.
* **Comparator:** Used for custom sorting.
* **Collections Class:** Utility methods (sort, reverse, shuffle).


**⚠️ Key Differences**

* **List vs Set:** List allows duplicates, Set does not.
* **ArrayList vs LinkedList:** ArrayList fast read, LinkedList fast insert/delete.
* **HashMap vs TreeMap:** HashMap unordered, TreeMap sorted.


# ✅ 08. Java Multithreading & Synchronization 

## 1. What is thread and what are life cycle?

A **Thread** is a lightweight sub-process in Java that allows **concurrent execution** of tasks within a program. Multiple threads can run inside a single process sharing the same memory.

**Key Features of Thread**

* Enables **parallel execution**
* Shares **common memory space**
* Lightweight compared to processes
* Managed by **JVM and OS**
* Supports multitasking

**Thread Life Cycle**

A Java thread goes through different **states** during execution:

1. **New :** Thread is created but not started
2. **Runnable :** Thread is ready to run and waiting for CPU
3. **Running :** Thread is actively executing
4. **Blocked / Waiting :** Thread is waiting for resource or signal
5. **Terminated (Dead) :**  Thread has completed execution

**How It Works**

* A thread is created using **Thread class** or **Runnable interface**
* It enters **New state**
* After calling `start()`, it moves to **Runnable**
* CPU scheduler moves it to **Running**
* It may move between **Waiting/Blocked**
* After completion, it enters **Terminated state**


**Why to Use Threads**

* Improve **performance**
* Enable **concurrent execution**
* Better CPU utilization
* Faster task processing
* Responsive applications


**When to Use Threads**

* Web servers handling multiple requests
* Background tasks
* File processing
* Real-time systems
* Parallel computations


**Example**

```java id="th1a2b"
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Moves thread to Runnable state
    }
}
```

**Thread Life Cycle Diagram (Text View)**
```
New → Runnable → Running → Waiting/Blocked → Running → Terminated
```

## 2. How do you create threads in Java?

There are two main ways to create **threads** in Java: **extending Thread** class or implementing **Runnable interfac**e.

**Method 1: Extending Thread** means creating a new class that inherits from the `Thread` class to define the task in its `run()` method.

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
MyThread t = new MyThread();
t.start();
```

**Method 2: Implementing Runnable** means creating a class that implements the `Runnable` interface and defines the task in its `run()` method, which can then be executed by a `Thread` object.

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}
Thread t = new Thread(new MyTask());
t.start();
```

**Method3: Implementing Callable** is an interface and similar to Runnable but returns a result and can throw checked exceptions.

```java
// import java.util.concurrent.*;

// Callable works with:: ExecutorService + Future

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Callable Task Completed";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new MyCallable());

        String result = future.get();
        System.out.println(result);
        service.shutdown();
    }
}
```


## 4. What are `sleep()` vs `wait()` in Multithreading?


* **sleep()** is a method of the **Thread class** used to pause execution of a thread for a fixed time.
* **wait()** is a method of the **Object class** used for inter-thread communication, where a thread waits until it is notified.


**sleep() – Key Features**

* Belongs to **Thread class**
* Pauses thread for a **fixed time**
* Does not release **locks**
* Does not require synchronization
* Throws **InterruptedException**

**How it Works**

* Thread is paused for given milliseconds
* After time expires, it resumes automatically


**wait() – Key Features**

* Belongs to **Object class**
* Used for **inter-thread communication**
* Releases **lock (monitor)**
* Requires **synchronized block**
* Needs `notify()` or `notifyAll()` to resume

**How it Works**

* Thread releases lock and enters **waiting state**
* Another thread calls `notify()` / `notifyAll()`
* Waiting thread resumes execution


**Why to Use**

* **sleep()** → Delay execution
* **wait()** → Coordinate between threads


**When to Use**

* **sleep()**

  * Adding delay
  * Retry mechanisms
  * Timed operations

* **wait()**

  * Producer-consumer problem
  * Thread communication
  * Shared resource coordination


**Example: sleep()**

```java id="sl1a2b"
public class Main {
    public static void main(String[] args) {

        System.out.println("Start");

        try {
            Thread.sleep(2000); // pauses thread for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }
}
```


**Example: wait() / notify()**

```java id="wt3c4d"
class Shared {

    synchronized void waitMethod() {
        try {
            System.out.println("Waiting...");
            wait(); // releases lock
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resumed");
    }

    synchronized void notifyMethod() {
        System.out.println("Notifying...");
        notify(); // wakes waiting thread
    }
}

public class Main {
    public static void main(String[] args) {

        Shared obj = new Shared();

        new Thread(obj::waitMethod).start();

        new Thread(() -> {
            try { Thread.sleep(1000); } catch (Exception e) {}
            obj.notifyMethod();
        }).start();
    }
}
```


**sleep() vs wait()**

| **sleep()**               | **wait()**                  |
| ------------------------- | --------------------------- |
| Thread class method       | Object class method         |
| Does NOT release lock     | Releases lock               |
| Fixed time delay          | Waits until notified        |
| No synchronization needed | Requires synchronized block |
| Used for pause            | Used for communication      |


## 3. Execute 10 Thread. 

**Execute Thread Without any scequence**
```java
class ThreadExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int id = i;

            Thread t = new Thread(() -> {
                System.out.println("Task " + id);
            });

            t.start();
        }
    }
}
```

**Execute Thread in scequence**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++) {
            int index = i;

            executor.submit(() -> {
                System.out.println("Task-" + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (Exception e) {}
                System.out.println("End Task-" + index);
            });
        }

        executor.shutdown();
    }
}
```


```java
for(int i = 0; i < 5; i++) {
    int threadId = i;
    Object lock = new Object();
    Thread tr = new Thread(() -> {
        synchronized(lock) {
            System.out.println(threadId +"---"+ Thread.currentThread().getName());
        }
    });
    tr.start();
    tr.join();      
}
// Output::
// 0---Thread-1
// 1---Thread-2
// 2---Thread-3
// 3---Thread-4
// 4---Thread-5
```


## 4. When we have 5 threads, in which sequence will they execute? Will I get the same result every time?


* Threads run **concurrently**, not strictly one after another
* The CPU decides which thread runs first
* Execution order can change every time you run the program

```java
for (int i = 1; i <= 5; i++) {
    new Thread(() -> {
        System.out.println(Thread.currentThread().getName());
    }).start();
}

// Possible Outputs (different each time):
Thread-2
Thread-1
Thread-4
Thread-0
Thread-3

// OR ===

Thread-0
Thread-3
Thread-1
Thread-2
Thread-4
```

**When We want same result every time?**

✔️ Only if you control execution using:

* `synchronized`
* `Lock`
* `join()`
* `ExecutorService` (single thread)



**Using `synchronized` (Avoid race condition)**

👉 Ensures only one thread modifies shared data at a time

```java
class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class SyncExample {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.count);
    }
}
// Output will ALWAYS be:
2000
```


**Using Single Thread Executor (Best practice)**

👉 Guarantees order automatically

```java
import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() -> System.out.println("Task 2"));
        executor.submit(() -> System.out.println("Task 3"));

        executor.shutdown();
    }
}
// Output ALWAYS:

Task 1
Task 2
Task 3
```

## 5. What is synchronization in Java?

**Synchronization** in Java is a mechanism used to control **access of multiple threads** to a shared resource to prevent **data inconsistency**.

**Key Features**

* Ensures **thread safety**
* Allows only **one thread at a time** in critical section
* Prevents **race conditions**
* Can be applied using **synchronized method or block**
* Uses **object-level or class-level lock**

**How It Works**

* When a thread enters a **synchronized block/method**, it acquires a **lock (monitor)**.
* Other threads must wait until the lock is released.
* Once execution is complete, the lock is released automatically.

**Why to Use**

* Prevent **data corruption**
* Ensure **consistency in shared resources**
* Avoid **race conditions**
* Maintain correct program behavior in multi-threading

**When to Use**

* Shared variables in multi-threaded environment
* Banking transactions
* Inventory management
* Ticket booking systems
* Any critical section handling shared data

**Types of Synchronization**

* **Method Synchronization**
* **Block Synchronization**
* **Static Synchronization**

**Example: Synchronized Method**

```java id="sy1a2b"
class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

**Example: Synchronized Block**

```java id="sy3c4d"
class Counter {
    private int count = 0;
    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
```

**How It Solves Problem**

**Without synchronization:** - Multiple threads update shared data → **inconsistent results**
**With synchronization:** - Only one thread updates data at a time → **correct output**

**Realtime Problem**
```java
// Multiple users try to withdraw money from the same account at the same time.

// Without Synchronization (Wrong Behavior)
class BankAccount {
    int balance = 1000;

    void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing...");
            balance = balance - amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

// With Synchronization (Correct Behavior)
class BankAccount {
    int balance = 1000;

    synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing...");
            balance = balance - amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

// Main Class (Simulation)
public class Test {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread user1 = new Thread(() -> account.withdraw(700), "User1");
        Thread user2 = new Thread(() -> account.withdraw(700), "User2");

        user1.start();
        user2.start();
    }
}
```

## 6. What is volatile keyword?

**volatile** is a Java keyword used to make a variable always read from and written to **main memory**, ensuring **visibility of changes across threads**.


**Key Features**

* Ensures **visibility** between threads
* Prevents **thread caching issues**
* Reads/writes directly from **main memory**
* Does NOT provide **atomicity**
* Lightweight alternative to **synchronization** (for visibility only)


**How It Works**

* Normally, each thread stores a variable in its **local cache**
* With **volatile**, every read/write happens directly in **main memory**
* So all threads always see the **latest value**


**Why to Use**

* Ensure **data visibility** in multi-threading
* Avoid stale or cached values
* Lightweight alternative to locks for simple cases


**When to Use**

* Status flags (e.g., stop thread)
* Configuration updates
* Simple coordination between threads
* When only **visibility**, not atomicity, is needed


**How It Solves Problem**

Without **volatile**:

* Thread may keep using **cached value**
* Changes may not be visible

```java
class Task implements Runnable {
    boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Task is running...");
        }
        System.out.println("Task stopped");
    }
}
```

With **volatile**:

* Every thread sees **latest updated value immediately**

```java
class Task implements Runnable {
    volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Task is running...");
        }
        System.out.println("Task stopped");
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Thread t1 = new Thread(task);
        t1.start();

        Thread.sleep(3000);

        task.running = false; // stop thread
        System.out.println("Stopped by main thread");
    }
}
```

**volatile vs synchronized**

| **volatile**                            | **synchronized**               |
| --------------------------------------- | ------------------------------ |
| Ensures visibility only                 | Ensures visibility + atomicity |
| No locking                              | Uses locks                     |
| Faster                                  | Slower                         |
| Not thread-safe for compound operations | Fully thread-safe              |


## 7. What is Semaphore and how it works?

**A semaphore** is a synchronization mechanism used to control how many threads can access a shared resource at the same time using **permits**.

**Key Features**

* Uses **permits** to control access
* Part of **java.util.concurrent**
* Supports **binary semaphore (1 permit)** and **counting semaphore (multiple permits)**
* Prevents **resource overuse**
* Works with **acquire()** and **release()**


**How it Works**

* A Semaphore is initialized with a fixed number of **permits**
* A thread calls **acquire()** to take a permit
* If no permit is available, the thread waits (**blocked**)
* After execution, the thread calls **release()** to return the permit
* This ensures controlled **concurrent access**


**Why to Use**

* To manage **limited resources safely**
* To avoid **system overload**
* To ensure **controlled concurrency**
* To improve **resource utilization efficiency**


**When to Use**

* Limiting **database connections**
* Controlling **API request rate**
* Managing **thread pool-like access control**
* Restricting access to **shared services or hardware**


**Code Example (Java)**

```java
import java.util.concurrent.Semaphore;
public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(2); // 2 permits

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                semaphore.acquire(); // acquire permit

                System.out.println(Thread.currentThread().getName() + " working...");
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing...");
                semaphore.release(); // release permit
            }
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}
```


## 8. What is the difference between synchronized and concurrent collections?


**Synchronized Collections** are thread-safe wrappers that ensure **one thread access at a time using full object locking**.
**Concurrent Collections** are advanced thread-safe collections designed for **high performance with fine-grained locking or lock-free operations**.


**Key Features**

**Synchronized Collections**

* Provide **thread safety using full synchronization**
* Example: **Collections.synchronizedList(), synchronizedMap()**
* Use **single lock mechanism**
* Simple but **slow under high concurrency**

**Concurrent Collections**

* Provide **high scalability and performance**
* Example: **ConcurrentHashMap, CopyOnWriteArrayList, ConcurrentLinkedQueue**
* Use **segment-level locking or CAS (Compare-And-Swap)**
* Allow **multiple threads to work simultaneously**


**How it Works**

**Synchronized Collections**

* Entire collection is locked using **synchronized keyword**
* Only **one thread can access at a time**
* Other threads are **blocked until lock is released**

**Concurrent Collections**

* Collection is divided into **smaller parts (segments/buckets)** or uses **lock-free algorithms**
* Multiple threads can access **different parts concurrently**
* Uses **atomic operations (CAS)** for safe updates


**Why to Use**

**Synchronized Collections**

* To quickly make **legacy collections thread-safe**
* For **simple, low-concurrency applications**

**Concurrent Collections**

* To handle **high-performance multi-threaded systems**
* To reduce **thread contention and blocking**


**When to Use**

**Synchronized Collections**

* Low traffic applications
* Simple multi-threading scenarios
* When performance is not critical

**Concurrent Collections**

* High concurrency systems (web apps, microservices)
* Caching systems
* Real-time processing systems


**Code Example (Java)**

**Synchronized Collection Example**

```java id="7zv5g2"
import java.util.*;

public class SyncDemo {
    public static void main(String[] args) {

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        list.add("Java");
        list.add("Spring");

        synchronized (list) {
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
```


**Concurrent Collection Example**

```java id="q9m2xa"
import java.util.concurrent.*;

public class ConcurrentDemo {
    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "Spring Boot");
        map.put(2, "Microservices");

        map.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });
    }
}
```

| Synchronized Collection       | Concurrent Collection                   |
| ----------------------------- | --------------------------------------- |
| Locks entire collection       | Uses segment-level/fine-grained locking |
| Slower under high concurrency | Better performance                      |
| Introduced before Java 5      | Introduced in `java.util.concurrent`    |
| Example: synchronizedList     | Example: ConcurrentHashMap              |



## 9. What is ConcurrentHashMap and how is it different from HashMap?


**HashMap** is a **non-synchronized** collection in Java used to store **key-value pairs**. It allows **one null key** and multiple null values.

**ConcurrentHashMap** is a **thread-safe** version of HashMap designed for **high concurrency** without locking the entire map.


**Key Features**

**HashMap**

* **Not thread-safe**
* Allows **1 null key** and multiple null values
* Faster in **single-threaded** environment
* Uses **array + linked list / tree (Java 8+)**

**ConcurrentHashMap**

* **Thread-safe**
* Does NOT allow **null keys or null values**
* High performance in **multi-threaded** environment
* Uses **segment-level / bucket-level locking (fine-grained locking)**


**How it works**

**HashMap**

* Uses **hashCode()** to find bucket index
* Stores entries in **buckets**
* No synchronization → multiple threads may corrupt data

**ConcurrentHashMap**

* Divides map into **segments or buckets**
* Locks only a **small part of map during write**
* Multiple threads can read/write **simultaneously safely**


**Why to use**

**HashMap**

* When you need **fast performance**
* When working in **single-threaded applications**

**ConcurrentHashMap**

* When working in **multi-threaded applications**
* When you need **thread safety without full locking**


**When to use**

**HashMap**

* Local caching
* Single-user data processing
* No shared access across threads

**ConcurrentHashMap**

* Web applications
* Shared cache
* High-concurrency systems (microservices, servers)


**Code Example**

**HashMap Example**

```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map);
    }
}
```

**ConcurrentHashMap Example**

```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map);
    }
}
```

| Feature     | HashMap                | ConcurrentHashMap            |
| ----------- | ---------------------- | ---------------------------- |
| Thread Safe | No                     | Yes                          |
| Performance | Faster (single thread) | Optimized for multithreading |
| Null Key    | One allowed            | Not allowed                  |
| Null Value  | Allowed                | Not allowed                  |
| Iterator    | Fail-Fast              | Weakly Consistent            |


// Real-Time Use Case (Important for Interviews)
// 🧠 Scenario: Caching User Sessions
// 👉 In real applications (like backend APIs), multiple users access/update data simultaneously.

```Java
import java.util.concurrent.ConcurrentHashMap;

class UserSession {
    String userId;
    long loginTime;

    UserSession(String userId) {
        this.userId = userId;
        this.loginTime = System.currentTimeMillis();
    }
}

public class SessionManager {
    private static ConcurrentHashMap<String, UserSession> sessionCache = new ConcurrentHashMap<>();

    // Add session
    public static void login(String userId) {
        sessionCache.put(userId, new UserSession(userId));
    }

    // Get session
    public static UserSession getSession(String userId) {
        return sessionCache.get(userId);
    }

    // Remove session
    public static void logout(String userId) {
        sessionCache.remove(userId);
    }

    public static void main(String[] args) {
        login("user1");
        login("user2");

        System.out.println(getSession("user1").userId);

        logout("user1");
    }
}
// Why use ConcurrentHashMap here?
// Many users logging in/out at the same time
// No need to lock entire structure
// Faster than Collections.synchronizedMap()
```

## 10. What is deadlock and how do you prevent it?

**Deadlock** is a situation in **multithreading** where two or more threads are **waiting forever** for each other to release **locks**, causing the application to **freeze**.

**Key Features**

* Occurs in **multi-threaded environment**
* Involves **two or more threads**
* Requires **circular waiting condition**
* Threads are stuck in **blocked state permanently**
* No **automatic recovery**

**How it works**

Deadlock happens when these four conditions occur together:

* **Mutual Exclusion** → One resource can be used by only one thread
* **Hold and Wait** → Thread holds one lock and waits for another
* **No Preemption** → Locks cannot be forcibly taken away
* **Circular Wait** → Thread A waits for B, B waits for A

Example flow:

* Thread 1 locks **Resource A** and waits for **Resource B**
* Thread 2 locks **Resource B** and waits for **Resource A**
* Both threads wait forever → **Deadlock**


**Why to use (understanding purpose)**
We do NOT use deadlock intentionally, but understanding it helps to:

* Design **safe multithreaded applications**
* Avoid **performance issues and system freeze**
* Improve **concurrency control**

**When to use (real context awareness)**
Deadlock is not used directly, but you must consider it when:

* Working with **multiple locks**
* Designing **shared resource systems**
* Building **high concurrency applications**

**Code Example (Deadlock Scenario)**

```java id="deadlock1"
public class DeadlockExample {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked lock1");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (lock2) {
                    System.out.println("Thread 1 locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked lock2");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (lock1) {
                    System.out.println("Thread 2 locked lock1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

**How to avoid Deadlock (Interview point)**

* Always acquire locks in **same order**
* Use **tryLock() (Lock interface)**
* Avoid **nested locks**
* Keep **lock scope minimal**


## 11. What is race condition and how to resolve it?

A **race condition** happens when **multiple threads access and modify the same shared data at the same time**, and the final result depends on which thread runs first.

Because threads run simultaneously, the output becomes **unpredictable**.


**Simple Example (Race Condition)**

```java
class Counter {
    int count = 0;

    public void increment() {
        count++;   // Not thread-safe
    }
}
```

```java
public class Test {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.count);
    }
}
```

**Expected Output:**

```
2000
```

**Actual Output (Race Condition):**

```
1453
1789
1932
```

Because both threads update `count` at the same time.


**How to Resolve Race Condition**

**1. Using synchronized**

```java
class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

Only one thread can execute at a time.


**2. Using Atomic Variable (Best Practice)**

```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }
}
```


**3. Using Lock**

```java
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    int count = 0;
    ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
```

## 12. Multithreading and Concurrency Summary?

**Thread** is the smallest unit of execution in a program that allows multiple tasks to run simultaneously.

**ThreadPool** is a group of pre-created threads used to execute tasks, which improves performance and avoids creating threads again and again.
 
**Multithreading** allows a program to run multiple threads concurrently within the same memory space, improving CPU utilization, performance, and responsiveness.

**Concurrency** is the ability of a program to execute multiple tasks at the same time by using multiple threads. These tasks can run in parallel on multiple CPU cores

**ConcurrentHashMap**  is a thread-safe implementation of Map that allows multiple threads to read and write data concurrently without locking the entire map.

**Runnable** is an interface used to create a thread in Java. It contains only one method: `run()`.

**Callable** is an interface and similar to Runnable but **returns a result** and can **throw exceptions**.

**ExecutorService** is a framework that manages threads and thread pools and execute tasks asynchronously, in the background.

**Volatile** is a keyword used in multithreading. Volatile ensures variable changes are immediately visible to all threads (prevents caching issues)

**Semaphore** is a synchronization mechanism used to control how many threads can access a shared resource at the same time.

**synchronized** is a keyword to ensures that only one thread at a time can access a shared resource, preventing race conditions to achieve thread safety.

**Lock** (ReentrantLock) is similar to synchronized but gives more control like lock, unlock, tryLock.

**CountDownLatch** is a synchronization utility that blocks threads until a set count reaches zero, using countDown() to decrement and await() to wait.

**Deadlock** is a situation where two or more threads are blocked forever waiting for each other’s resources.

**CompletableFuture** is used to run asynchronous tasks and combine multiple async operations.

**@Async**  is used in Spring Boot to execute a method asynchronously and used to run a method in **background thread** 



# ✅ 09. Java Concurrency 

## 0. What is multithreading?

**Multithreading** is a process where **multiple threads** run concurrently within the same application. It helps perform multiple tasks at the same time, improving **performance** and **resource utilization**.

**Key Features**

* **Concurrent execution** of tasks
* Threads share the same **memory space**
* Improves **CPU utilization**
* Faster execution for independent tasks
* Supports **parallel processing** on multi-core systems

**How It Works**

* A **Thread** is a lightweight sub-process.
* Multiple threads are created inside a single process.
* The **JVM Thread Scheduler** decides when each thread runs.
* Threads execute independently but share application resources.

**Why to Use**

* Improve application **performance**
* Handle multiple tasks simultaneously
* Reduce user waiting time
* Better responsiveness in web and desktop applications

**When to Use**

* Processing large amounts of data
* File uploads/downloads
* Background tasks
* Web servers handling multiple requests
* Real-time applications

**Example**

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Creates a new thread
    }
}
```

**Using Runnable (Recommended)**

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task is running");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask());
        t1.start();
    }
}
```


## 1. What is **Concurrency in Java**?


**Concurrency**

**Definition**

**Concurrency** is the ability of a system to handle **multiple tasks at the same time** by making progress on each task. The tasks may not run simultaneously, but they are managed so that they appear to run together.

**Key Features**

* Multiple tasks are in progress at the same time
* Improves **responsiveness**
* Efficient use of **system resources**
* Supports **task switching**
* Can be achieved using **threads**, **thread pools**, or **asynchronous programming**

**How It Works**

* The CPU switches between multiple tasks.
* Each task gets a small amount of execution time.
* While one task waits (e.g., for I/O), another task can execute.
* This creates the illusion that tasks are running together.

**Why to Use**

* Improve application responsiveness
* Handle many user requests efficiently
* Better resource utilization
* Increase overall throughput

**When to Use**

* Web applications handling multiple requests
* Database operations
* File processing
* Network communication
* Asynchronous and background tasks

**Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() ->
            System.out.println("Task 1 executing")
        );

        executor.submit(() ->
            System.out.println("Task 2 executing")
        );

        executor.shutdown();
    }
}
```

* **How concurrency is achieved in Java**

* **Thread & Runnable** – basic units of execution.
* **ExecutorService** – manages thread pools and asynchronous tasks.
* **Synchronization** – ensures **thread-safe access** to shared resources.
* **Locks (ReentrantLock)** – flexible locking with reentrant and fairness options.
* **Atomic variables** – **lock-free thread-safe operations** on single variables.
* **Concurrent collections** – thread-safe collections like `ConcurrentHashMap`.
* **CompletableFuture (Java 8+)** – **asynchronous computation** with callbacks and chaining.

**Concurrency vs Parallelism**

| **Concurrency**                       | **Parallelism**                           |
| ------------------------------------- | ----------------------------------------- |
| Multiple tasks make progress together | Multiple tasks run at the same time       |
| May use a single CPU core             | Requires multiple CPU cores               |
| Focuses on managing tasks             | Focuses on executing tasks simultaneously |


## 2.  What is ConcurrentHashMap and when to use?


**ConcurrentHashMap**

**Definition**

**ConcurrentHashMap** is a **thread-safe** implementation of **Map** in Java that allows multiple threads to read and write data concurrently without causing data inconsistency.

**Key Features**

* **Thread-safe**
* High **performance** in multi-threaded applications
* Allows multiple threads to read and write simultaneously
* Does not lock the entire map for every operation
* Part of the **java.util.concurrent** package
* Does **not allow null keys or null values**

**How It Works**

* The map divides data internally into buckets.
* Multiple threads can access different buckets concurrently.
* Reads are usually performed without locking.
* Only the affected portion of the map is locked during updates, improving performance.

**Why to Use**

* Prevent data corruption in multi-threaded environments
* Better performance than **Hashtable** or synchronized collections
* Safe concurrent access to shared data

**When to Use**

* Caching
* Session management
* Shared application data
* High-concurrency applications
* Multi-threaded web applications

**Example**

```java
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map =
                new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map.get(1));
    }
}
```

**ConcurrentHashMap vs HashMap**

| **HashMap**                                  | **ConcurrentHashMap**                    |
| -------------------------------------------- | ---------------------------------------- |
| Not thread-safe                              | Thread-safe                              |
| Suitable for single-threaded applications    | Suitable for multi-threaded applications |
| Faster in single-threaded use                | Optimized for concurrent access          |
| Allows one null key and multiple null values | Does not allow null keys or values       |


## 3.  What is a Thread Pool and What are the types of thread pools?


A **Thread Pool** is a collection of **pre-created worker threads** that are reused to execute multiple tasks. Instead of creating a new thread for every task, tasks are assigned to existing threads in the pool.

**Key Features**

* **Reuses threads**
* Improves **performance**
* Reduces thread creation overhead
* Better **resource management**
* Controls the number of concurrent threads
* Provided by **Executor Framework**

**How It Works**

1. Create a **Thread Pool** with a fixed or dynamic number of threads.
2. Submit tasks to the pool.
3. An available worker thread picks up and executes the task.
4. After completion, the thread returns to the pool and waits for the next task.
5. Threads are reused instead of being created repeatedly.

**Why to Use**

* Improves application performance
* Reduces memory and CPU overhead
* Prevents creating too many threads
* Simplifies concurrent programming

**When to Use**

* Web applications handling multiple requests
* Background jobs
* Batch processing
* Asynchronous tasks
* High-concurrency applications

**Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            executor.submit(() ->
                System.out.println(
                    "Executing Task " + taskId +
                    " by " + Thread.currentThread().getName()
                )
            );
        }

        executor.shutdown();
    }
}
```

**Common Types of Thread Pools**

* **FixedThreadPool** – Fixed number of threads
* **CachedThreadPool** – Creates threads as needed
* **SingleThreadExecutor** – Uses only one thread
* **ScheduledThreadPool** – Executes tasks after a delay or periodically

## 4. What is ExecutorService?

**ExecutorService** is a Java interface from the **Executor Framework** used to manage and execute asynchronous tasks using a **Thread Pool**. It simplifies thread management by handling thread creation, reuse, and shutdown automatically.

**Key Features**

* Manages a **Thread Pool**
* Executes tasks asynchronously
* Reuses existing threads
* Improves application performance
* Supports **Runnable** and **Callable** tasks
* Provides graceful thread shutdown

**How It Works**

1. Create an **ExecutorService**.
2. Submit tasks using `submit()` or `execute()`.
3. A thread from the pool executes the task.
4. After completion, the thread returns to the pool.
5. Call `shutdown()` when all tasks are finished.

**Why to Use**

* Avoid manual thread creation
* Better resource management
* Improves performance through thread reuse
* Simplifies concurrent programming

**When to Use**

* Web applications
* Background processing
* Batch jobs
* Asynchronous operations
* High-concurrency systems

**Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() ->System.out.println("Task 2"));
        executor.shutdown();
    }
}
```

**Important Methods**

| **Method**      | **Purpose**                          |
| --------------- | ------------------------------------ |
| `execute()`     | Executes a Runnable task             |
| `submit()`      | Executes a task and returns a Future |
| `shutdown()`    | Stops accepting new tasks            |
| `shutdownNow()` | Attempts to stop all running tasks   |
| `invokeAll()`   | Executes multiple tasks              |

**ExecutorService vs Thread**

| **Thread**                   | **ExecutorService**         |
| ---------------------------- | --------------------------- |
| Manual thread management     | Automatic thread management |
| New thread per task          | Reuses threads              |
| Less efficient               | More efficient              |
| Difficult to manage at scale | Easy to manage at scale     |


## 5. What is CountDownLatch and CyclicBarrier?
               |

**CountDownLatch** is a synchronization utility that allows one or more threads to wait until a set of operations performed by other threads is completed.

**Key Features**

* **Thread synchronization**
* Uses a **counter**
* One-time use only
* Threads wait using `await()`
* Counter decreases using `countDown()`

**How It Works**

1. Create a latch with a count value.
2. Worker threads complete their tasks and call `countDown()`.
3. The waiting thread calls `await()`.
4. When the count reaches **0**, all waiting threads continue execution.

**Why to Use**

* Wait for multiple tasks to finish
* Coordinate thread execution
* Simplify multi-threaded workflows

**When to Use**

* Application startup tasks
* Parallel data processing
* Waiting for multiple services to respond

**Example**

```java id="e4uyh6"
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("Task 1 done");
            latch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("Task 2 done");
            latch.countDown();
        }).start();

        latch.await();

        System.out.println("All tasks completed");
    }
}
```

**Interview Answer**

"**CountDownLatch** is a synchronization utility that allows a thread to wait until other threads complete their work. It uses a counter that decreases with `countDown()`, and when the count reaches zero, waiting threads are released. It is a **one-time-use** synchronization mechanism."


**CyclicBarrier** is a synchronization utility that allows a group of threads to wait for each other at a common barrier point before continuing execution.

**Key Features**

* **Thread synchronization**
* Reusable (**cyclic**)
* All threads wait at a barrier
* Releases all threads together
* Can execute a barrier action

**How It Works**

1. Create a barrier with the number of participating threads.
2. Each thread calls `await()`.
3. Threads wait until all participants reach the barrier.
4. Once all arrive, the barrier opens and all threads continue.
5. The barrier automatically resets for reuse.

**Why to Use**

* Coordinate multiple threads
* Ensure threads reach the same execution point
* Useful for phased processing

**When to Use**

* Parallel computations
* Multi-step processing
* Simulation systems
* Data aggregation tasks

**Example**

```java id="m6h4uz"
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2);

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " reached barrier");

                barrier.await();

                System.out.println(Thread.currentThread().getName()
                        + " continues");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
    }
}
```

**Interview Answer**

"**CyclicBarrier** is a synchronization utility that allows multiple threads to wait for each other at a common point. When all participating threads reach the barrier using `await()`, they are released together. Unlike **CountDownLatch**, it is **reusable** and can be used across multiple processing phases."


**CountDownLatch vs CyclicBarrier**

| **CountDownLatch**                   | **CyclicBarrier**                  |
| ------------------------------------ | ---------------------------------- |
| One-time use                         | Reusable                           |
| Counter decreases to zero            | Threads wait for each other        |
| One thread typically waits           | All threads wait                   |
| Uses `countDown()` and `await()`     | Uses `await()`                     |
| Suitable for task completion waiting | Suitable for phase synchronization |


## 6. What is ReentrantLock?


**ReentrantLock** is a **thread synchronization** mechanism from the `java.util.concurrent.locks` package that provides explicit locking and unlocking of shared resources. It is an advanced alternative to the **synchronized** keyword.

**Key Features**

* **Thread-safe**
* Supports **explicit lock** and unlock
* A thread can acquire the same lock multiple times (**reentrant**)
* Supports **fair locking**
* Supports **tryLock()**
* Supports **interruptible locking**

**How It Works**

1. A thread acquires the lock using `lock()`.
2. Other threads trying to acquire the same lock must wait.
3. The thread executes the critical section.
4. The lock is released using `unlock()`.
5. Waiting threads can then acquire the lock.

**Why to Use**

* More flexible than **synchronized**
* Better control over locking behavior
* Supports timeout and interrupt handling
* Useful in complex concurrent applications

**When to Use**

* Multi-threaded applications
* Shared resource access
* Complex synchronization requirements
* When `tryLock()` or fair locking is needed

**Example**

```java id="x9w7k2"
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();

        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
```

**Using tryLock()**

```java id="y4n8m1"
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        if (lock.tryLock()) {
            try {
                System.out.println("Lock acquired");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock");
        }
    }
}
```

**ReentrantLock vs synchronized**

| **ReentrantLock**                | **synchronized**    |
| -------------------------------- | ------------------- |
| Explicit `lock()` and `unlock()` | Automatic locking   |
| Supports `tryLock()`             | No `tryLock()`      |
| Supports fairness policy         | No fairness control |
| Supports interruptible locking   | Limited control     |
| More flexible                    | Simpler to use      |


## 7. How to call three APIs concurrently?

To call **three APIs concurrently**, we execute all API requests at the same time instead of waiting for one request to finish before starting the next. In Java, this is commonly done using **CompletableFuture**.

| **Method**      | **Purpose**                                         |
| --------------- | --------------------------------------------------- |
| `supplyAsync()` | Executes a task asynchronously and returns a result |
| `allOf()`       | Waits for all tasks to complete                     |
| `join()`        | Gets the result without checked exceptions          |
| `thenCombine()` | Combines results from multiple tasks                |

**Way One**

To call three APIs concurrently in Spring Boot, we can use `CompletableFuture` with `@Async` so that all services like User, Payment, and Inventory are called in parallel, improving performance.”

User Service
Payment Service
Inventory Service

**Step 1: Enable Async**

```java id="8k6y8x"
@SpringBootApplication
@EnableAsync
public class Application {
}
```

**Step 2: Create Async Service Methods**

```java id="c8s8l2"
@Service
public class ApiService {

    @Async
    public CompletableFuture<String> getUser() {
        // call User Service API
        return CompletableFuture.completedFuture("User Data");
    }

    @Async
    public CompletableFuture<String> getPayment() {
        // call Payment Service API
        return CompletableFuture.completedFuture("Payment Data");
    }

    @Async
    public CompletableFuture<String> getInventory() {
        // call Inventory Service API
        return CompletableFuture.completedFuture("Inventory Data");
    }
}
```

**Step 3: Call All APIs Concurrently**

```java id="fqhmr2"
@RestController
public class MyController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/getAllData")
    public String getAllData() throws Exception {

        CompletableFuture<String> user = apiService.getUser();
        CompletableFuture<String> payment = apiService.getPayment();
        CompletableFuture<String> inventory = apiService.getInventory();

        // Wait for all to complete
        CompletableFuture.allOf(user, payment, inventory).join();

        return user.get() + " | " + payment.get() + " | " + inventory.get();
    }
}
```

**Way two:: WebClient (Reactive - Non-Blocking)**

We can use WebClient from Spring WebFlux to make non-blocking concurrent API calls using Mono or Flux, which is more scalable than threads.


```java
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private WebClient webClient = WebClient.create();

    public Mono<String> getUser() {
        return webClient.get()
                .uri("http://user-service/api")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPayment() {
        return webClient.get()
                .uri("http://payment-service/api")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getInventory() {
        return webClient.get()
                .uri("http://inventory-service/api")
                .retrieve()
                .bodyToMono(String.class);
    }
}
```

```java
@GetMapping("/getAll")
public Mono<String> getAll() {

    Mono<String> user = apiService.getUser();
    Mono<String> payment = apiService.getPayment();
    Mono<String> inventory = apiService.getInventory();

    return Mono.zip(user, payment, inventory)
            .map(data -> data.getT1() + " | " +
                         data.getT2() + " | " +
                         data.getT3());
}
```


**Way Three::  ExecutorService (Manual Thread Pool)**

We can use ExecutorService to manually create threads and submit tasks to call APIs in parallel.

```java
import java.util.concurrent.*;

@Service
public class ApiService {

    ExecutorService executor = Executors.newFixedThreadPool(3);

    public String getAllData() throws Exception {

        Callable<String> userTask = () -> "User Data";
        Callable<String> paymentTask = () -> "Payment Data";
        Callable<String> inventoryTask = () -> "Inventory Data";

        Future<String> user = executor.submit(userTask);
        Future<String> payment = executor.submit(paymentTask);
        Future<String> inventory = executor.submit(inventoryTask);

        return user.get() + " | " + payment.get() + " | " + inventory.get();
    }
}
```



| Feature     | WebClient                | ExecutorService |
| ----------- | ------------------------ | --------------- |
| Type        | Non-blocking             | Blocking        |
| Threads     | Less usage               | More threads    |
| Performance | High scalability         | Moderate        |
| Complexity  | Reactive learning needed | Easy            |



## 8. What is immutability in Java? 


**Immutability**

**Definition**

**Immutability** means an object's state **cannot be changed after it is created**. If a value needs to change, a **new object** is created instead of modifying the existing one.

**Key Features**

* Object state cannot be modified after creation
* Class is usually marked as **final**
* Fields are **private** and **final**
* No setter methods
* Thread-safe by nature

**How It Works**

1. Object is created with initial values.
2. Fields are assigned only once.
3. No methods allow modification of the object's state.
4. Any change requires creating a new object.

**Why to Use**

* Improves **thread safety**
* Prevents accidental data modification
* Makes code easier to understand and maintain
* Reduces synchronization issues

**When to Use**

* Value objects
* Configuration objects
* DTOs
* Multi-threaded applications
* Cache keys and shared data

**Example**

```java id="d7k4m1"
public final class Employee {

    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

**Usage**

```java id="x8n2p5"
Employee emp = new Employee("John", 25);

// Cannot modify values
// emp.setName("Mike"); // Not possible
```

**How to Create an Immutable Class**

* Make the class **final**
* Make all fields **private** and **final**
* Initialize fields through constructor
* Do not provide setter methods
* Return defensive copies for mutable objects

**Example: String**

```java id="a3v9q7"
String s1 = "Java";
String s2 = s1.concat(" Spring");

System.out.println(s1); // Java
System.out.println(s2); // Java Spring
```

Here, **String** is immutable. The original object is not changed; a new object is created.

## 9. What is CompletableFuture and how does it work?


**CompletableFuture** is a class in Java used for **asynchronous** and **non-blocking** programming. It allows tasks to run in the background and provides a way to process results when they become available.

**Key Features**

* **Asynchronous execution**
* **Non-blocking** operations
* Supports task chaining
* Combines multiple asynchronous tasks
* Built on the **Executor Framework**
* Better alternative to traditional **Future**

**How It Works**

1. Start a task asynchronously using `supplyAsync()` or `runAsync()`.
2. The task executes in a separate thread.
3. Continue other work without waiting.
4. Process the result when the task completes.
5. Combine multiple tasks if needed.

**Why to Use**

* Improve application performance
* Avoid blocking the main thread
* Execute multiple tasks concurrently
* Simplify asynchronous programming

**When to Use**

* Calling multiple APIs
* Background processing
* Microservices communication
* Data aggregation
* Long-running operations

**Example**

```java id="cf7m2x"
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    return "Hello Java";
                });

        String result = future.join();

        System.out.println(result);
    }
}
```

**Task Chaining Example**

```java id="kp9v4n"
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Java")
                        .thenApply(str -> str + " Spring");

        System.out.println(future.join());
    }
}
```

**Important Methods**

| **Method**      | **Purpose**                                |
| --------------- | ------------------------------------------ |
| `runAsync()`    | Executes a task without returning a result |
| `supplyAsync()` | Executes a task and returns a result       |
| `thenApply()`   | Transforms the result                      |
| `thenAccept()`  | Consumes the result                        |
| `thenCombine()` | Combines two tasks                         |
| `allOf()`       | Waits for multiple tasks                   |
| `join()`        | Retrieves the result                       |


## 10. Difference between Future and CompletableFuture

**`Future`** is used to get the result of an asynchronous task, but it supports only basic operations like `get()`. 

**`CompletableFuture`** is an enhanced version introduced in Java 8 that supports chaining, combining multiple async tasks, exception handling, and non-blocking programming.

**Future vs CompletableFuture**

| **Future**                 | **CompletableFuture**           |
| -------------------------- | ------------------------------- |
| Basic asynchronous support | Advanced asynchronous support   |
| Cannot easily chain tasks  | Supports task chaining          |
| Limited result processing  | Rich API for result handling    |
| More blocking operations   | Supports non-blocking workflows |

**Future**

```java
ExecutorService executor = Executors.newFixedThreadPool(1);

Future<String> future = executor.submit(() -> {
            Thread.sleep(1000);
            return "Hello";
        });

System.out.println(future.get());
// Output: Hello
// Problem: get() blocks the current thread, no chaining support
```

**CompletableFuture Example**

```java id="gw9h1k"
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(s -> s + " Java");

System.out.println(future.get());
//Output: Hello Java
```


**Exception Handling**

```java id="sqh7g4"
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
        throw new RuntimeException();
    }).exceptionally(ex -> "Default Value");

System.out.println(future.get());
//Output: Default Value
```

## 11. What is fail-fast and fail-safe iterators?


**Fail-Fast** and **Fail-Safe** describe how Java collections behave when they are modified while being iterated.

* **Fail-Fast**: Immediately throws an exception if the collection is modified during iteration.
* **Fail-Safe**: Works on a copy of the collection, so modifications do not cause exceptions.


**Fail-Fast**

**Key Features**

* Detects concurrent modifications
* Throws **ConcurrentModificationException**
* Uses the original collection
* Faster and memory efficient

**How It Works**

* Iterator keeps track of collection modifications.
* If the collection is modified outside the iterator during iteration, it throws **ConcurrentModificationException**.

**Why to Use**

* Detect programming errors early
* Prevent inconsistent data access

**When to Use**

* Single-threaded applications
* When collection modifications during iteration are not expected

**Example**

```java id="ff1a2b"
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Spring");

        for (String item : list) {
            list.add("AWS"); // Exception
        }
    }
}
```


**Fail-Safe**

**Key Features**

* Does not throw ConcurrentModificationException
* Iterates over a snapshot or copy
* Safe for concurrent modifications
* Extra memory usage

**How It Works**

* Iterator works on a separate copy of the collection.
* Changes to the original collection do not affect the iteration.

**Why to Use**

* Safe iteration in concurrent environments
* Allows modification during iteration

**When to Use**

* Multi-threaded applications
* Concurrent data access

**Example**

```java id="fs3c4d"
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list =
                new CopyOnWriteArrayList<>();

        list.add("Java");
        list.add("Spring");

        for (String item : list) {
            list.add("AWS"); // No exception
        }

        System.out.println(list);
    }
}
```

**Common Examples**

| **Fail-Fast** | **Fail-Safe**        |
| ------------- | -------------------- |
| ArrayList     | CopyOnWriteArrayList |
| HashMap       | ConcurrentHashMap    |
| HashSet       | CopyOnWriteArraySet  |

**Fail-Fast vs Fail-Safe**

| **Fail-Fast**                            | **Fail-Safe**                        |
| ---------------------------------------- | ------------------------------------ |
| Uses original collection                 | Uses copy/snapshot                   |
| Throws ConcurrentModificationException   | No exception                         |
| Less memory usage                        | More memory usage                    |
| Faster                                   | Slightly slower                      |
| Not suitable for concurrent modification | Suitable for concurrent modification |


## 12. What happens if the thread pool is exhausted?


A **Thread Pool** is considered **exhausted** when all worker threads are busy and the task queue is full, leaving no capacity to accept new tasks.

**Key Features**

* All threads are occupied
* Task queue has reached its limit
* New tasks cannot be processed immediately
* A **Rejection Policy** is triggered

**How It Works**

1. Tasks are submitted to the thread pool.
2. Available threads execute the tasks.
3. If all threads are busy, tasks are placed in the queue.
4. If both the threads and queue are full, the pool becomes exhausted.
5. The configured **RejectedExecutionHandler** decides what happens next.

**Why to Know This**

* Prevents application slowdowns
* Helps avoid task loss
* Important for system scalability
* Useful when tuning thread pools in production systems

**When It Happens**

* High traffic web applications
* Long-running tasks
* Small thread pool size
* Small queue capacity
* Sudden spikes in workload

**Common Rejection Policies**

| **Policy**              | **Behavior**                                        |
| ----------------------- | --------------------------------------------------- |
| **AbortPolicy**         | Throws **RejectedExecutionException** (Default)     |
| **CallerRunsPolicy**    | Calling thread executes the task                    |
| **DiscardPolicy**       | Silently discards the task                          |
| **DiscardOldestPolicy** | Removes the oldest queued task and adds the new one |

**Example**

```java id="u7n3qk"
ThreadPoolExecutor executor =
    new ThreadPoolExecutor(
        2, // Core threads
        2, // Max threads
        0L,
        TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<>(2)
    );

// 2 tasks run
// 2 tasks wait in queue
// 5th task gets rejected
```

**How to Avoid Thread Pool Exhaustion**

* Increase **pool size**
* Increase **queue capacity**
* Optimize slow tasks
* Use **asynchronous processing**
* Configure an appropriate **Rejection Policy**

## 13. Fork/Join Framework


The **Fork/Join Framework** is a Java framework used for **parallel processing** by breaking a large task into smaller subtasks, executing them in parallel, and then combining the results. It is part of the **java.util.concurrent** package.

**Key Features**

* Based on **divide and conquer** approach
* Uses **ForkJoinPool**
* Supports **parallel execution**
* Uses **work-stealing algorithm**
* Improves CPU utilization
* Designed for **recursive tasks**

**How It Works**

1. A large task is split into smaller **subtasks (fork)**.
2. Subtasks are executed in parallel by worker threads.
3. Each thread processes its assigned task.
4. Results are combined (**join**) to produce final output.
5. Idle threads “steal” work from busy threads using **work-stealing**.

**Why to Use**

* Improve performance for large datasets
* Efficient CPU utilization
* Reduce execution time using parallelism
* Ideal for recursive computations

**When to Use**

* Large data processing
* Sorting algorithms (like merge sort)
* Big computational tasks
* Parallel recursion problems
* CPU-intensive operations

**Example**

```java id="fj1a2b"
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {

    private int start, end;
    private int[] arr;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if (end - start <= 2) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }

        int mid = (start + end) / 2;

        SumTask left = new SumTask(arr, start, mid);
        SumTask right = new SumTask(arr, mid, end);

        left.fork();
        int rightResult = right.compute();
        int leftResult = left.join();

        return leftResult + rightResult;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(arr, 0, arr.length);

        int result = pool.invoke(task);
        System.out.println(result);
    }
}
```

**Important Classes**

| **Class**           | **Purpose**            |
| ------------------- | ---------------------- |
| **ForkJoinPool**    | Manages worker threads |
| **RecursiveTask**   | Returns a result       |
| **RecursiveAction** | No result (void tasks) |

**Fork/Join vs Thread Pool**

| **Fork/Join**              | **Thread Pool**        |
| -------------------------- | ---------------------- |
| Designed for recursion     | General task execution |
| Uses work-stealing         | Fixed task assignment  |
| Best for CPU-heavy tasks   | Good for general tasks |
| Automatically splits tasks | Manual task management |



# ✅ 10. Java JVM & Memory Management 

## 0. What is Java Memory Model (JMM)?

**Java Memory Model (JMM)** defines how threads interact with memory and ensures that changes made by one thread become visible to other threads in a predictable and safe way.

**Why is JMM Needed?**

In a multithreaded application:

* Each thread can have its own CPU cache.
* One thread may update a variable, but another thread may not see the latest value immediately.
* JMM provides rules for **visibility, ordering, and atomicity** to avoid inconsistent behavior.

**Key Concepts**

1. **Visibility** → Changes made by one thread are visible to other threads.
2. **Atomicity** → Operations happen completely or not at all.
3. **Ordering** → Instructions execute in the correct order.

`volatile` ensures that when one thread changes `running`, other threads immediately see the updated value.


```java id="kuyjci"
class SharedData {

    private volatile boolean flag = false;

    public void writer() {
        flag = true;
    }

    public void reader() {
        while (!flag) {
            // waiting
        }
        System.out.println("Flag changed");
    }
}
```

**Atomicity :** An operation completes entirely or not at all.

```java id="w6e1oi"
synchronized void increment() {
    count++;
}
```

**Ordering :** Ensures instructions execute in a predictable order using the "happens-before" relationship.

## 1. What are the different memory areas in JVM?

Java has only one memory that is JVM. and JVM memory is divided into different areas to store objects, class metadata, method execution data, and thread-specific information.

**JVM Memory Areas** manage program execution and memory:

**Easy Way to Remember**

* **Heap** → Objects
* **Stack** → Method Execution
* **Metaspace** → Class Metadata
* **PC Register** → Current Instruction
* **Native Stack** → Native Code Execution

| Memory Area             | Purpose                                                                              |
| ----------------------- | ------------------------------------------------------------------------------------ |
| **Heap**                | Stores objects and instance variables. Shared by all threads.                        |
| **Stack**               | Stores method calls, local variables, and references. Each thread has its own stack. |
| **Metaspace**           | Stores class metadata, method information, and static structures.                    |
| **PC Register**         | Stores the address of the current instruction being executed by a thread.            |
| **Native Method Stack** | Stores information for native methods written in C/C++ and called through JNI.       |


## 2. What is the difference between heap and stack?

**Heap** and **Stack** are two different memory areas in the **JVM**, and each serves a different purpose.

| Feature               | **Stack Memory**                                                   | **Heap Memory**                             |
| --------------------- | ------------------------------------------------------------------ | ------------------------------------------- |
| **Stores**            | Local variables, method calls, primitive values, object references | Objects and instance variables              |
| **Memory Allocation** | Automatic when a method is called                                  | Dynamic using **`new`** keyword             |
| **Access Speed**      | Very **fast**                                                      | Comparatively **slower**                    |
| **Thread Scope**      | **Thread-specific** (each thread has its own stack)                | **Shared** among all threads                |
| **Memory Management** | Automatically removed when method finishes                         | Managed by the **Garbage Collector (GC)**   |
| **Lifetime**          | Exists only during method execution                                | Exists until object is no longer referenced |
| **Error**             | **StackOverflowError** (deep recursion)                            | **OutOfMemoryError** (heap full)            |

**How it Works**

* When a method is called, a new **stack frame** is created in the **Stack Memory**.
* Local variables and references are stored in that stack frame.
* When an object is created using **`new`**, the actual object is stored in the **Heap Memory**, while its reference is stored in the **Stack**.
* After the method completes, the stack frame is removed automatically.
* Objects in the heap remain until the **Garbage Collector** frees the unused memory.

**Why to Use**

* **Stack** provides **fast and efficient** memory management for method execution.
* **Heap** allows **dynamic object creation** and sharing of objects across methods and threads.

**When to Use**

* Use **Stack** for temporary data like **local variables** and **method execution**.
* Use **Heap** whenever you create **objects**, **arrays**, or any data that needs to live beyond a single method call.

**Example**

```java
public class MemoryExample {
    public static void main(String[] args) {
        int age = 25;                  // Stored in Stack
        Person p = new Person();       // Reference 'p' in Stack
                                      // Object 'Person' in Heap
        p.name = "John";
    }
}

class Person {
    String name;
}
```

**In the above example:**

* **`age`** is stored in the **Stack Memory**.
* Reference variable **`p`** is stored in the **Stack Memory**.
* The actual **`Person` object** and its field **`name`** are stored in the **Heap Memory**.



## 3. What is the difference between PermGen and Metaspace?

**PermGen** (Permanent Generation) is a fixed memory area in Java (up to Java 7) used to store class metadata, method information, and interned strings.

**Characteristics:**

* Fixed size (`-XX:PermSize`, `-XX:MaxPermSize`)
* Part of JVM memory
* Can cause `OutOfMemoryError: PermGen space` if full

**Metaspace** (Java 8 onwards) replaces PermGen, storing class metadata in native memory with dynamic sizing, improving memory management.
* Fixed size (`-XX:PermSize`, `-XX:MaxMetaspaceSize`)


| Feature             | **PermGen**                                          | **Metaspace**                               |
| ------------------- | ---------------------------------------------------- | ------------------------------------------- |
| **Available In**    | Java 7 and earlier                                   | Java 8 and later                            |
| **Memory Location** | Part of the **JVM Heap**                             | Uses **Native (OS) Memory**                 |
| **Stores**          | Class metadata, static variables, method information | Class metadata and method information       |
| **Size**            | Fixed, manually configured                           | Dynamically grows by default                |
| **Configuration**   | `-XX:MaxPermSize`                                    | `-XX:MaxMetaspaceSize`                      |
| **Common Error**    | **`java.lang.OutOfMemoryError: PermGen space`**      | **`java.lang.OutOfMemoryError: Metaspace`** |
| **Performance**     | More frequent tuning required                        | Better memory management and less tuning    |

**Key Features**

* **PermGen** had a **fixed size**, so applications with many dynamically loaded classes could run out of memory.
* **Metaspace** uses **native memory**, allowing it to grow automatically as needed.
* **Metaspace** reduces memory tuning and improves JVM flexibility.

**How it Works**

* When the JVM loads a class, it stores the class metadata (class name, methods, fields, etc.).
* In **Java 7**, this metadata was stored in **PermGen**.
* In **Java 8+**, it is stored in **Metaspace**, which allocates memory from the operating system instead of the heap.


## 4. What is garbage collection?

**Garbage Collection** is an automatic memory management process in **Java** that removes objects that are no longer being used by the application and frees their memory.

**Key Features**

* **Automatic Memory Management**
* Managed by the **JVM**
* Removes unused objects
* Helps prevent **Memory Leaks**
* Improves application performance and stability

**How It Works**

The **Garbage Collector** periodically checks objects in memory.

* If an object is still referenced, it remains in memory.
* If an object is no longer referenced, it becomes eligible for **Garbage Collection**.
* The JVM reclaims the memory used by that object.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        String str = new String("Hello");

        str = null; // Object becomes eligible for GC
        System.gc(); // Request JVM to run GC
    }
}
```

**Why to Use**

* Eliminates manual memory management
* Reduces memory-related bugs
* Prevents memory leaks
* Improves application reliability

**When to Use**

**Garbage Collection** runs automatically when the JVM determines that memory needs to be reclaimed. Developers do not usually manage memory manually in Java.


## 5. What are the types of garbage collectors?

In Java, **Garbage Collectors (GC)** are responsible for **automatically reclaiming memory** used by objects that are no longer needed. Java provides several types of garbage collectors.


Java provides different **Garbage Collectors (GCs)**, each optimized for different use cases.

**1. Serial Garbage Collector**

* Uses a **single thread** for garbage collection.
* Suitable for small applications with low memory usage.
* Simple and low overhead.

JVM Option:

```bash
-XX:+UseSerialGC
```

**2. Parallel Garbage Collector**

* Uses **multiple threads** for garbage collection.
* Focuses on maximizing **throughput**.
* Default GC in older Java versions.

JVM Option:

```bash
-XX:+UseParallelGC
```

**3. G1 Garbage Collector (Garbage First)**

* Divides the heap into regions.
* Prioritizes regions with the most garbage.
* Balances throughput and low pause times.
* Default GC since **Java 9**.

JVM Option:

```bash
-XX:+UseG1GC
```

**4. Z Garbage Collector (ZGC)**

* Designed for very large heaps.
* Provides extremely low pause times.
* Suitable for large-scale applications.

JVM Option:

```bash
-XX:+UseZGC
```

**5. Shenandoah Garbage Collector**

* Focuses on low pause times.
* Performs most GC work concurrently with application threads.
* Suitable for applications requiring high responsiveness.

JVM Option:

```bash
-XX:+UseShenandoahGC
```

**Summary Table**

| Garbage Collector | Threads    | Pause Time     | Use Case                   |
| ----------------- | ---------- | -------------- | -------------------------- |
| Serial GC         | Single     | Stop-the-world | Small apps, single CPU     |
| Parallel GC       | Multiple   | Stop-the-world | High throughput apps       |
| CMS GC            | Concurrent | Low-latency    | Responsive apps            |
| G1 GC             | Concurrent | Low-medium     | Large heaps, low pause     |
| ZGC               | Concurrent | Very low       | Very large heaps, scalable |
| Shenandoah GC     | Concurrent | Very low       | Low-latency applications   |


## 6. What is generational garbage collection?


**Generational Garbage Collection (GC)** is a JVM memory management technique that divides the **Heap Memory** into different generations because **most objects die young**.

**Key Features**

* **Heap** is divided into:

  * **Young Generation** (Eden + Survivor S0 + Survivor S1)
  * **Old (Tenured) Generation**
* Objects are first created in the **Eden Space**.
* Short-lived objects are removed quickly using **Minor GC**.
* Long-lived objects are moved (promoted) to the **Old Generation**.
* **Major/Full GC** cleans the Old Generation.

**How it Works**

1. A new object is created and stored in the **Eden Space**.
2. When Eden becomes full, a **Minor GC** runs.
3. Objects that are still in use survive and move to the **Survivor Space**.
4. After surviving several GC cycles, they are promoted to the **Old Generation**.
5. When the Old Generation becomes full, a **Major GC** or **Full GC** is triggered to reclaim memory.

**Memory Flow**

```text
Eden  →  Survivor (S0/S1)  →  Old Generation
   Minor GC         Minor GC        Major/Full GC
```

**Why to Use**

* Improves **GC performance** by collecting short-lived objects quickly.
* Reduces the time spent scanning the entire heap.
* Minimizes application pause times and improves memory efficiency.

**When to Use**

* Used automatically by the **JVM** in almost all Java applications.
* Especially beneficial for applications that create many temporary objects, such as **web applications**, **microservices**, and **high-throughput systems**.

**Code Example**

```java
public class GCExample {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String str = new String("Java GC"); // Short-lived object
        }
        System.out.println("Objects created");
    }
}
```



## 7. What is the difference between minor GC and major GC?

A **Minor GC** occurs in the **Young Generation** of the heap and cleans up short-lived objects like temporary variables. It happens frequently and is usually very fast, causing minimal pause.

A **Major GC** (also called **Full GC**) runs on the **Old Generation** and removes long-lived objects that are no longer needed. It happens less often but takes more time and can significantly impact application performance.


```java
// Objects that survive multiple minor GCs get promoted to Old Generation
List<String> longLived = new ArrayList<>(); // Eventually moves to Old Gen
String temp = "temporary"; // Likely collected in minor GC
```

## 8. What are GC roots?

**GC Roots** are special references from which the **Garbage Collector (GC)** starts checking which objects are still **reachable** in memory. Any object that can be reached from a GC Root is **not eligible for garbage collection**.

**Key Features**

* **Starting point** for GC traversal.
* Objects reachable from GC Roots are **alive**.
* Unreachable objects become **eligible for garbage collection**.
* Helps JVM identify **unused memory**.

**Common GC Roots in Java**

* **Local variables** in active methods (stack references).
* **Static variables**.
* **Active threads**.
* **JNI (Native) references**.

**How It Works**

1. GC starts from all **GC Roots**.
2. It traverses all referenced objects.
3. Reachable objects are marked as **live**.
4. Unreachable objects are considered **garbage** and can be removed.

**Why to Use**

* Prevents accidental deletion of objects still in use.
* Enables efficient **memory management**.
* Helps avoid **memory leaks** and **OutOfMemoryError**.

**When to Use**

* Understanding **Garbage Collection** internals.
* Analyzing **memory leaks**.
* Debugging JVM memory issues.

**Code Example**

```java
public class GCRootExample {
    static Object staticObj = new Object(); // GC Root
    public static void main(String[] args) {
        Object localObj = new Object(); // GC Root (local variable)

        localObj = null; // No longer referenced
    }
}
```


## 9. Java Memory Management Summary?


**🔹 JVM Basics**

* **JVM:** Runtime engine that executes Java bytecode and manages memory.
* **JDK vs JRE vs JVM:** JDK = development tools, JRE = runtime, JVM = execution engine.
* **ClassLoader:** Loads class files into memory dynamically at runtime.


**📦 Memory Areas**

* **Heap:** Shared memory where objects are stored and managed by GC.
* **Stack:** Thread-specific memory for method calls and local variables.
* **Metaspace:** Stores class metadata in native memory (Java 8+).
* **PC Register:** Holds current executing instruction per thread.
* **Native Method Stack:** Executes native (C/C++) methods.


**🧠 PermGen vs Metaspace**

* **PermGen:** Fixed-size memory (pre-Java 8) for class metadata, prone to memory issues.
* **Metaspace:** Replaces PermGen, uses native memory and grows dynamically.


**⚖️ Heap vs Stack**

* **Heap:** Shared, object storage, GC-managed, slower access.
* **Stack:** Thread-local, method execution, fast and auto-managed.


**🧩 Heap Structure**

* **Young Generation:** Where new objects are created.
* **Eden Space:** Initial allocation area for new objects.
* **Survivor Space:** Stores objects that survive minor GC.
* **Old Generation:** Stores long-lived objects.


**♻️ Garbage Collection**

* **GC:** Removes unreachable objects to free memory.
* **Minor GC:** Cleans Young Generation.
* **Major GC:** Cleans Old Generation.
* **Full GC:** Cleans entire heap.
* **Stop-The-World:** Application pauses during GC execution.


**⚙️ GC Algorithms**

* **Serial GC:** Single-threaded, simple but slow.
* **Parallel GC:** Multi-threaded, high throughput.
* **G1 GC:** Region-based, low pause time (modern default).
* **CMS GC:** Low pause but deprecated.


**⚙️ JVM Internals**

* **JIT Compiler:** Converts bytecode to native code for faster execution.
* **Bytecode:** Platform-independent intermediate code.
* **Interpreter vs JIT:** Interpreter executes line-by-line, JIT optimizes frequently used code.


**⚠️ Errors**

* **OutOfMemoryError:** Heap or Metaspace memory exhausted.
* **StackOverflowError:** Stack memory exceeded (deep recursion).


**🔍 Advanced Concepts**

* **Memory Leak:** Objects not GC’d due to active references.
* **Heap Dump:** Memory snapshot used for debugging.
* **GC Tuning:** Adjusting JVM parameters for performance.


**⚙️ JVM Parameters**

* **-Xms:** Initial heap size.
* **-Xmx:** Maximum heap size.
* **-XX:+UseG1GC:** Enables G1 garbage collector.
* **-XX:MaxMetaspaceSize:** Limits Metaspace size.



# ✅ 11. Java Input/Output (I/O) 

## 1. What are the different ways to read a file in Java?

Java provides multiple approaches to read files, each suitable for different scenarios and file sizes.

**Common Methods:**
- **FileInputStream:** Byte-based reading
- **FileReader:** Character-based reading
- **BufferedReader:** Efficient line-by-line reading
- **Scanner:** Convenient parsing with delimiters
- **Files.readAllLines():** Read entire file into List
- **Files.lines():** Stream-based reading (Java 8+)

```java
// BufferedReader - most common
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
}

// Files utility - modern approach
List<String> lines = Files.readAllLines(Paths.get("file.txt"));
```

## 2. What is the difference between InputStream and Reader?

**InputStream** is a byte-oriented class used to read raw **binary data** (like images, audio, or files) from a source.

**Reader** is a character-oriented class used to read **text data** (characters, strings) from a source, handling character encoding automatically.

```java
// InputStream - for binary data
InputStream is = new FileInputStream("image.jpg");
int byteData = is.read(); // Returns byte as int

// Reader - for text data
Reader reader = new FileReader("text.txt");
int charData = reader.read(); // Returns character as int
```

## 3. What is BufferedReader and BufferedWriter?

**BufferedReader** is a Java class used to read text from an input stream efficiently by **buffering characters**, reducing the number of I/O operations.

**BufferedWriter** is a Java class used to write text to an output stream efficiently by **buffering characters**, reducing the number of I/O operations.


```java
// BufferedReader - efficient reading
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// BufferedWriter - efficient writing
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello World");
    bw.newLine();
}
```

## 4. Create API to handle large files efficiently?

When handling **Large Files**, we should avoid loading the entire file into memory. Instead, use **Streaming** with **InputStream** and **OutputStream** to process data in chunks. This reduces memory consumption and improves performance.

**Controller for File Upload**

```java
@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        Path path = Paths.get("uploads/" + file.getOriginalFilename());

        try (InputStream in = file.getInputStream();
             OutputStream out = Files.newOutputStream(path)) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return ResponseEntity.ok("File uploaded successfully");
    }
}
```

**Controller for File Download**

```java
@GetMapping("/download/{fileName}")
public ResponseEntity<Resource> downloadFile(
        @PathVariable String fileName) throws IOException {

    Path path = Paths.get("uploads/" + fileName);

    Resource resource =
            new InputStreamResource(Files.newInputStream(path));

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + fileName)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
}
```

**Efficient Strategies**

* Use **Streaming** to process files chunk by chunk.
* Use **BufferedReader** and **BufferedWriter** for large text files.
* Process files **line by line** instead of loading the entire file into memory.
* Use **Java NIO** APIs for better I/O performance.
* Use **Memory-Mapped Files** for extremely large files.
* Use **Parallel Processing** when file operations can be executed independently.

**Best Practices**

* Use **Streaming** instead of reading the entire file into memory.
* Process data in **Chunks** using a buffer (e.g., 8 KB or larger).
* Store very large files in **Object Storage** such as **Amazon S3** or a file system, and store only metadata in the database.
* Enable **Multipart Uploads** for large file transfers.
* Support **Range Requests** for resumable downloads and video streaming.
* Configure appropriate **File Size Limits** and **Timeouts**.
* Validate file type and size before processing.
* Monitor memory usage and I/O performance.



## 5. How do you process images into Oracle DB using Java APIs?

In a **Spring Boot REST API**, images are usually uploaded as **MultipartFile** and stored in an Oracle **BLOB** column. We use **JPA** or **JDBC** to save the image bytes into the database.

**Entity**

```java id="zx5b7g"
import jakarta.persistence.*;

@Entity
@Table(name = "employee_images")
public class EmployeeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    // getters and setters
}
```

**Repository**

```java id="5zj6bi"
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeImageRepository
        extends JpaRepository<EmployeeImage, Long> {
}
```

**Service**

```java id="t4p4u4"
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeImageService {

    private final EmployeeImageRepository repository;

    public EmployeeImageService(EmployeeImageRepository repository) {
        this.repository = repository;
    }

    public Long uploadImage(MultipartFile file) throws Exception {

        EmployeeImage image = new EmployeeImage();
        image.setFileName(file.getOriginalFilename());
        image.setImageData(file.getBytes());

        return repository.save(image).getId();
    }

    public EmployeeImage getImage(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
```

**Controller**

```java id="j72oym"
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class EmployeeImageController {

    private final EmployeeImageService service;

    public EmployeeImageController(EmployeeImageService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file) throws Exception {

        Long id = service.uploadImage(file);

        return ResponseEntity.ok("Image uploaded. ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable Long id) {

        EmployeeImage image = service.getImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getImageData());
    }
}
```

**Oracle Table**

```sql id="uvcp58"
CREATE TABLE employee_images (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    file_name VARCHAR2(255),
    image_data BLOB,
    PRIMARY KEY(id)
);
```

## 6. What is NIO in Java?

**NIO (New I/O)** in Java is a **high-performance I/O API** introduced in Java 1.4 that provides **non-blocking, buffer-based, and scalable input/output operations**.

It uses **channels, buffers, and selectors** for efficient **file and network communication**.

**Benefits:**
- Better scalability for server applications
- Non-blocking I/O operations
- Memory-mapped file access
- More efficient for handling many connections

```java
// NIO file reading
try (FileChannel channel = FileChannel.open(Paths.get("file.txt"))) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    channel.read(buffer);
}
```

## 7. What is the difference between IO and NIO?

**IO (java.io)** is **blocking and stream-based**, meaning a thread waits until data is read or written. It’s easy to use but not efficient for handling many connections because each request usually needs a separate thread.

**NIO (java.nio)** is **non-blocking and buffer-based**. It uses **channels, buffers, and selectors**, allowing one thread to handle multiple connections. This makes it more scalable and better suited for high-performance applications.

```java
// Traditional I/O - blocking
InputStream is = new FileInputStream("file.txt");
int data = is.read(); // Blocks until data available

// NIO - non-blocking potential
FileChannel channel = FileChannel.open(Paths.get("file.txt"));
ByteBuffer buffer = ByteBuffer.allocate(1024);
channel.read(buffer); // Can be non-blocking with proper setup
```

## 8. When would you use NIO over traditional I/O?

Use **NIO** when you need **better performance and scalability**, especially for server applications handling many concurrent connections.

**Use NIO when:**
- Building high-performance servers
- Handling thousands of concurrent connections
- Need non-blocking I/O operations
- Working with large files (memory-mapped files)
- Building chat servers, web servers

**Use Traditional I/O when:**
- Simple file operations
- Few concurrent connections
- Straightforward read/write operations
- Rapid development needed
- Working with small to medium files

# ✅ 12. Java Generics 

## 1. What are generics in Java?

**Generics** allow you to write **type-safe** classes, interfaces, and methods by specifying the **data type** at compile time. They help prevent type-casting errors and improve code reusability.

**Key Features**

* Provides **type safety**.
* Eliminates unnecessary **type casting**.
* Detects type errors at **compile time**.
* Improves **code reusability** and readability.

**How It Works**

* Use a **type parameter** (e.g., `<T>`) as a placeholder for a data type.
* The actual type is specified when creating an object or calling a method.
* The compiler ensures only the specified type is used.

**Why to Use**

* Prevents **ClassCastException**.
* Reduces runtime errors.
* Makes code more reusable and maintainable.
* Improves compile-time checking.

**When to Use**

* Working with **Collections** like `List`, `Set`, and `Map`.
* Creating reusable classes and methods.
* When the same logic should work with different data types.

**Code Example**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("John");
        names.add("Alice");

        String name = names.get(0); // No casting required
        System.out.println(name);
    }
}
```

**Generic Class Example**

```java
class Box<T> {
    private T value;
    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.set(100);

        System.out.println(box.get());
    }
}
```


## 2. Why were generics introduced?

Generics were introduced to provide compile-time type safety, eliminate casting, and enable programmers to write more robust and readable code.

**Main Benefits:**
- **Type Safety:** Catch type errors at compile time
- **Eliminate Casting:** No need for explicit type casting
- **Code Reusability:** Same code works with different types
- **Better Performance:** No boxing/unboxing overhead
- **Clearer APIs:** Self-documenting code

```java
// Before generics - runtime error possible
List list = new ArrayList();
list.add("String");
list.add(123);
String str = (String) list.get(1); // ClassCastException at runtime

// With generics - compile-time error
List<String> list = new ArrayList<>();
list.add("String");
list.add(123); // Compile error - type mismatch
```

## 3. What is type erasure?

Type **erasure** is the process where generic type information is removed during compilation. The compiler replaces generic types with their bounds or Object, maintaining backward compatibility.

- Generic type information removed at runtime
- Replaced with raw types or bounds
- Maintains backward compatibility with pre-Java 5 code
- Bridge methods created for polymorphism
- Cannot access generic type at runtime

```java
// Source code
List<String> stringList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();

// After type erasure (bytecode equivalent)
List stringList = new ArrayList();
List intList = new ArrayList();

// Both have same runtime type
System.out.println(stringList.getClass() == intList.getClass()); // true
```

## 4. What is the difference between &lt;? extends T&gt; and &lt;? super T&gt;?

**&lt;? extends T&gt; (Upper Bounded Wildcard):**
- Accepts T and its subtypes
- Read-only operations (Producer)
- Cannot add elements (except null)

**&lt;? super T&gt; (Lower Bounded Wildcard):**
- Accepts T and its supertypes
- Write operations allowed (Consumer)
- Can add T and its subtypes

```java
// ? extends T - can read, cannot write
List<? extends Number> numbers = new ArrayList<Integer>();
Number num = numbers.get(0); // OK - reading
// numbers.add(10); // Compile error - cannot write

// ? super T - can write, limited reading
List<? super Integer> integers = new ArrayList<Number>();
integers.add(10); // OK - writing Integer
// Integer val = integers.get(0); // Compile error - returns Object
Object val = integers.get(0); // OK - can read as Object
```

## 5. What is PECS principle?

**PECS** stands for **"Producer Extends, Consumer Super"** - a guideline for choosing between extends and super wildcards based on how you use the collection.

**Producer Extends:**
- Use `<? extends T>` when you only read from collection
- Collection produces/provides elements
- You're consuming what the collection produces

**Consumer Super:**
- Use `<? super T>` when you only write to collection
- Collection consumes/accepts elements
- You're producing elements for the collection

```java
// Producer - use extends
public void processNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) { // Reading - OK
        System.out.println(num);
    }
}

// Consumer - use super
public void addNumbers(List<? super Integer> numbers) {
    numbers.add(10); // Writing - OK
    numbers.add(20);
}
```

## 6. What are the limitations of generics?

**Generics** have several limitations due to type erasure and backward compatibility requirements.

**Key Limitations:**
- Cannot instantiate generic types: `new T()`
- Cannot create arrays of parameterized types: `new List<String>[10]`
- Cannot use primitives as type parameters: `List<int>` (use `List<Integer>`)
- Cannot catch parameterized exceptions
- Static context cannot access type parameters
- Cannot overload methods that differ only in generic parameters

```java
public class GenericClass<T> {
    // Cannot do these:
    // T instance = new T(); // Error - cannot instantiate
    // T[] array = new T[10]; // Error - cannot create array
    // static T staticField; // Error - static context
    
    // Cannot catch generic exception
    // try { } catch (T e) { } // Error
    
    // Cannot overload based on generics only
    // void method(List<String> list) { }
    // void method(List<Integer> list) { } // Error - same erasure
}

// Workarounds
public class GenericClass<T> {
    private Class<T> type;
    
    public GenericClass(Class<T> type) {
        this.type = type;
    }
    
    public T createInstance() throws Exception {
        return type.newInstance(); // Reflection workaround
    }
}
```

# ✅ 13. Java Annotations & Reflection 


## 1. What are annotations in Java?

**Annotations** are **metadata** that provide additional information about classes, methods, fields, or parameters. They do **not directly change the program logic**, but they help the **compiler**, **JVM**, or **frameworks** process the code.

**Key Features**

* Provide **metadata** about the code.
* Improve **readability** and reduce XML/configuration files.
* Can be processed at **compile time** or **runtime**.
* Widely used in frameworks like **Spring**, **Hibernate**, and **JUnit**.
* Can create **custom annotations**.

**How it Works**

* An annotation is added using the **`@`** symbol.
* The **compiler**, **JVM**, or a **framework** reads the annotation.
* Based on the annotation, it performs specific actions automatically.

**Why to Use**

* Reduces boilerplate code and manual configuration.
* Makes code cleaner and easier to maintain.
* Enables features like **dependency injection**, **ORM mapping**, and **unit testing**.

**When to Use**

* Use **`@Override`** to indicate method overriding.
* Use **`@Deprecated`** to mark old methods.
* Use **`@SuppressWarnings`** to suppress compiler warnings.
* In **Spring**, use annotations like **`@Component`**, **`@Service`**, and **`@Autowired`**.
* In **Hibernate**, use **`@Entity`**, **`@Table`**, and **`@Column`**.

**Common Built-in Annotations**

| **Annotation**             | **Purpose**                                       |
| -------------------------- | ------------------------------------------------- |
| **`@Override`**            | Indicates a method overrides a parent method      |
| **`@Deprecated`**          | Marks a method or class as outdated               |
| **`@SuppressWarnings`**    | Suppresses compiler warnings                      |
| **`@FunctionalInterface`** | Ensures an interface has only one abstract method |

**Code Example**

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

In this example:

* **`@Override`** tells the compiler that `sound()` must override the parent class method.
* If the method signature is incorrect, the compiler will generate an error.

**Custom Annotation Example**

```java
@interface MyAnnotation {
    String value();
}

@MyAnnotation(value = "Java")
class Test {
}
```

## 2. What are built-in annotations?

Java provides several built-in annotations for common use cases like method overriding, deprecation warnings, and compiler instructions.

**Common Built-in Annotations:**
- **@Override:** Indicates method overrides parent method
- **@Deprecated:** Marks code as outdated
- **@SuppressWarnings:** Suppresses compiler warnings
- **@FunctionalInterface:** Marks functional interfaces
- **@SafeVarargs:** Suppresses varargs warnings

```java
@Override
public void method() { } // Ensures proper overriding

@Deprecated
public void legacyMethod() { } // Warns users about deprecation

@SuppressWarnings("unchecked")
List list = new ArrayList(); // Suppresses unchecked warning
```

## 3. What is @Target, @Documented, @Inherited?


These are **meta-annotations**, which means they are **annotations used to define the behavior of other annotations**.

| **Meta-Annotation** | **Purpose**                                                                |
| ------------------- | -------------------------------------------------------------------------- |
| **`@Target`**       | Specifies where an annotation can be applied (class, method, field, etc.). |
| **`@Documented`**   | Includes the annotation information in the generated **JavaDoc**.          |
| **`@Inherited`**    | Allows a custom annotation to be inherited by child classes.               |

**Key Features**

* **`@Target`** restricts the valid locations for an annotation.
* **`@Documented`** makes the annotation visible in API documentation.
* **`@Inherited`** allows subclasses to automatically inherit a class-level annotation.
* These are mainly used while creating **custom annotations**.

**How it Works**

* **`@Target`** defines where the annotation is allowed.
* **`@Documented`** tells the JavaDoc tool to include the annotation in generated documentation.
* **`@Inherited`** allows a subclass to access the annotation present on its parent class.

**Why to Use**

* Prevents incorrect annotation usage with **`@Target`**.
* Improves API documentation with **`@Documented`**.
* Reduces duplicate annotations in inheritance hierarchies with **`@Inherited`**.

**When to Use**

* Use **`@Target`** when creating a custom annotation and you want to limit where it can be applied.
* Use **`@Documented`** when the annotation should appear in generated documentation.
* Use **`@Inherited`** when child classes should automatically inherit the parent class annotation.

**Code Example**

```java id="p8rj2z"
import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@interface MyAnnotation {
}

@MyAnnotation
class Parent {
}

class Child extends Parent {
}

public class Test {
    public static void main(String[] args) {
        System.out.println(
            Child.class.isAnnotationPresent(MyAnnotation.class)
        ); // true
    }
}
```

**In this example:**

* **`@Target(ElementType.TYPE)`** allows `@MyAnnotation` to be used only on **classes, interfaces, or enums**.
* **`@Documented`** ensures `@MyAnnotation` appears in the generated **JavaDoc**.
* **`@Inherited`** allows the `Child` class to inherit the annotation from `Parent`.

**Common `ElementType` Values for `@Target`**

| **ElementType**       | **Used On**            |
| --------------------- | ---------------------- |
| **`TYPE`**            | Class, interface, enum |
| **`METHOD`**          | Method                 |
| **`FIELD`**           | Field or variable      |
| **`PARAMETER`**       | Method parameter       |
| **`CONSTRUCTOR`**     | Constructor            |
| **`ANNOTATION_TYPE`** | Another annotation     |


## 4. Can you create custom annotations in java?

**Yes, Java allows you to create custom annotations** using the **@interface** keyword. Custom annotations are used to add **metadata** to classes, methods, fields, or parameters.

**Creating a Custom Annotation**

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value();
}
```

**Explanation**

* **@interface** – used to create a custom annotation.
* **@Retention(RetentionPolicy.RUNTIME)** – makes the annotation available at runtime.
* **@Target(ElementType.METHOD)** – specifies that the annotation can be applied only to methods.
* **String value()** – defines an attribute for the annotation.

**Using the Annotation**

```java
public class EmployeeService {

    @MyAnnotation("Employee Validation")
    public void validate() {
        System.out.println("Validating employee...");
    }
}
```

**Reading the Annotation with Reflection**

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {

        Method method = EmployeeService.class.getMethod("validate");

        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation =
                    method.getAnnotation(MyAnnotation.class);

            System.out.println(annotation.value());
        }
    }
}
```


## 5. What is retention policy?

**Retention Policy** defines **how long an annotation is available** in the Java program. It is specified using the **`@Retention`** meta-annotation and the **`RetentionPolicy`** enum.

**Key Features**

* Controls the **lifecycle** of an annotation.
* Applied using the **`@Retention`** annotation.
* Has **three types**: **`SOURCE`**, **`CLASS`**, and **`RUNTIME`**.
* Important when creating **custom annotations**.

**Retention Policy Types**

| **Retention Policy** | **Available Until**                                  | **Use Case**                               |
| -------------------- | ---------------------------------------------------- | ------------------------------------------ |
| **`SOURCE`**         | Source code only                                     | Compiler checks, removed after compilation |
| **`CLASS`**          | Stored in `.class` file but not available at runtime | Default behavior                           |
| **`RUNTIME`**        | Available during runtime through **Reflection**      | Spring, Hibernate, JUnit                   |

**How it Works**

* The **compiler** reads the `@Retention` annotation.
* Based on the selected `RetentionPolicy`, it decides how long the annotation should be kept.
* If `RUNTIME` is used, the annotation can be accessed using **Reflection API**.

**Why to Use**

* Controls whether an annotation is needed only during compilation or also at runtime.
* Improves performance by keeping annotations only as long as required.
* Enables frameworks like **Spring** and **Hibernate** to read annotations dynamically.

**When to Use**

* Use **`SOURCE`** for compile-time tools and checks.
* Use **`CLASS`** when the annotation should exist in the bytecode but is not needed at runtime.
* Use **`RUNTIME`** when frameworks or reflection need to access the annotation while the application is running.

**Code Example**

```java id="3r7n2f"
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
}

@MyAnnotation
class Test {
}

public class Main {
    public static void main(String[] args) {
        boolean present =
            Test.class.isAnnotationPresent(MyAnnotation.class);

        System.out.println(present); // true
    }
}
```


## 6. What is reflection in Java?

**Reflection in Java** is a feature that allows a program to **inspect and manipulate classes, methods, fields, and constructors at runtime**, even if they are **private**, enabling **dynamic behavior** and **runtime flexibility**.


```java
// Get class information
Class<?> clazz = String.class;
Method[] methods = clazz.getMethods();

// Create instance dynamically
Object obj = clazz.newInstance();

// Invoke method dynamically
Method method = clazz.getMethod("length");
int length = (int) method.invoke("Hello");
```

## 7. When should you use reflection?

Use **reflection** when you need dynamic behavior that cannot be achieved with normal Java code, typically in frameworks and libraries.

**Appropriate Use Cases:**
- Building frameworks (Spring, Hibernate)
- Serialization/deserialization libraries
- Testing frameworks (JUnit)
- Dependency injection containers
- Code analysis tools
- Plugin architectures

**Avoid reflection for:**
- Normal application logic
- Performance-critical code
- When compile-time solutions exist


# ✅ 14. Java Web Development - Servlets and JSP

## 1. What is servlet in Java?

A **servlet** is a Java class that **handles HTTP requests and responses** on a web server. It's like a **controller that processes incoming requests**, performs business logic, and sends back responses. Servlets run inside containers like Tomcat and are the foundation of Java web applications.

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World!</h1>");
    }
}
```

## 2. What is the servlet lifecycle?

The **Servlet Lifecycle** defines the stages a servlet goes through from **creation to destruction** in a servlet container like **Apache Tomcat**.

**Lifecycle Phases**

1. **Initialization (`init()`) :** The servlet is loaded and initialized by the container.
2. **Request Processing (`service()`) :** The servlet handles client requests (`doGet()`, `doPost()`).
3. **Destruction (`destroy()`) :** The servlet is removed from memory when the server shuts down.


```java
public class LifecycleServlet extends HttpServlet {
    
    public void init() throws ServletException {
        // Called once when servlet loads
        System.out.println("Servlet initialized");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Called for each request
        response.getWriter().println("Processing request");
    }
    
    public void destroy() {
        // Called once when servlet unloads
        System.out.println("Servlet destroyed");
    }
}
```

## 3. What is JSP (JavaServer Pages)?

**JavaServer Pages (JSP)** is a **server-side technology** used to create **dynamic web pages** using **Java code inside HTML**. It runs on a web server like **Apache Tomcat**.

JSP is internally **converted into a servlet** by the server and then executed.

* Used to build **dynamic web applications**
* Combines **HTML + Java code**
* Runs on the **server side**
* Automatically converted to **Servlet**


```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body>
    <h1>Welcome, <%= request.getParameter("username") %>!</h1>
    
    <%
        String currentTime = new java.util.Date().toString();
    %>
    
    <p>Current time: <%= currentTime %></p>
</body>
</html>
```

## 4. What is the difference between servlet and JSP?

**Servlets** are **Java classes** that handle **HTTP requests and responses**. They contain Java code and are mainly used for **processing business logic**, not for generating HTML.

**JSP (JavaServer Pages)** is a **view technology** that allows embedding **Java code inside HTML** to create dynamic web pages. JSPs are easier for creating UI because they focus on **presentation**, while servlets focus on **logic**.

In short: **Servlet = logic/controller**, **JSP = view/presentation**.

**Key differences:**

- Servlets: Java code generating HTML
- JSP: HTML with embedded Java
- Servlets: Better for logic
- JSP: Better for presentation
- JSP gets converted to servlets anyway

```java
// Servlet approach
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>User List</h1>");
        out.println("</body></html>");
    }
}
```

```jsp
<!-- JSP approach -->
<!DOCTYPE html>
<html>
<body>
    <h1>User List</h1>
    <% for(User user : users) { %>
        <p><%= user.getName() %></p>
    <% } %>
</body>
</html>
```


## 5. What is ORM?
**ORM(Object Relational Mapping)** is a technique that maps **Java Objects** to **Database Tables** and database records to Java objects.

**Key Features**

* Maps objects to database tables
* Reduces SQL boilerplate code
* Simplifies database operations
* Improves code readability and maintainability

**How It Works**

ORM automatically converts Java objects into database records and database records back into Java objects.

For example:

* **Class** → **Table**
* **Object** → **Row**
* **Field** → **Column**

**Code Example**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
}
```

```java
User user = new User();
user.setName("John");

userRepository.save(user);
```

ORM automatically generates and executes the SQL needed to save the object.

**Why to Use**

* Reduces manual SQL writing
* Improves developer productivity
* Makes code more object-oriented
* Simplifies CRUD operations

**When to Use**

Use **ORM** when working with relational databases and Java applications that need frequent database operations.

Examples:

* Spring Boot applications
* Enterprise applications
* Microservices with databases

## 6. What is JPA?


**JPA(Java Persistence API)** is a **Java Specification** for managing and persisting data between Java objects and relational databases. It provides a standard way to perform **ORM (Object Relational Mapping)**.

**Key Features**

* Standard API for database persistence
* Supports **ORM**
* Uses annotations such as **@Entity**, **@Id**, and **@Table**
* Reduces boilerplate JDBC code
* Database-independent

**How It Works**

JPA maps Java classes to database tables and Java objects to table rows.

* **Class** → **Table**
* **Object** → **Row**
* **Field** → **Column**

JPA defines the rules, while frameworks such as **Hibernate** implement those rules.

**Code Example**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
}
```

```java
userRepository.save(user);
```

JPA automatically maps the object and persists it to the database.

**Why to Use**

* Simplifies database operations
* Reduces SQL and JDBC code
* Improves productivity
* Provides a standard persistence approach

**When to Use**

Use **JPA** when building Java applications that interact with relational databases.

Examples:

* Spring Boot applications
* Enterprise applications
* Microservices with databases

## 6. What is Hibernate?

**Hibernate** is an **ORM (Object Relational Mapping) Framework** that simplifies database operations by mapping **Java Objects** to **Database Tables**. It is the most popular implementation of **JPA**.

**Key Features**

* Implements **JPA**
* Provides **ORM** functionality
* Automatically generates SQL queries
* Supports **Caching**
* Supports **Lazy Loading**
* Reduces JDBC boilerplate code

**How It Works**

Hibernate maps Java classes to database tables and Java objects to table rows.

* **Class** → **Table**
* **Object** → **Row**
* **Field** → **Column**

When you save an object, Hibernate automatically generates and executes the required SQL.

**Code Example**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
}
```

```java
User user = new User();
user.setName("John");

userRepository.save(user);
```

Hibernate automatically generates SQL similar to:

```sql
INSERT INTO user (name) VALUES ('John');
```

**Why to Use**

* Reduces manual SQL writing
* Simplifies database interaction
* Improves productivity
* Provides advanced ORM features
* Makes applications easier to maintain

**When to Use**

Use **Hibernate** when working with relational databases in Java applications.

Examples:

* Spring Boot applications
* Enterprise applications
* Microservices with databases


**Analogy:**
- **ORM** = "Driving a car" (concept) 🚗
- **JPA** = "Driver's license test" (standard rules) 📋
- **Hibernate** = "Toyota Camry" (actual car implementing the rules) 🚙

| Concept   | Analogy                 |
| --------- | ----------------------- |
| ORM       | Online shopping concept |
| Hibernate | Amazon application      |


| | ORM | JPA | Hibernate |
|---|---|---|---|
| **What it is** | Concept/technique | Java specification | Framework/library |
| **Who defines it** | General pattern | Oracle / Jakarta EE | Red Hat |
| **Analogous to** | "web standards" | JDBC interface | A JDBC driver |


## 7. Different between ORM, JPA and Hibernate?

| Aspect                 | ORM                                                           | JPA                                                                                                                                              | Hibernate                                                                                                                                                |
| ---------------------- | ------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| What it is             | Concept/technique for mapping objects to relational databases | Java specification (API) defining how ORM should work                                                                                            | Framework that implements JPA specification stackoverflow+1                                                                                              |
| Type                   | Approach/methodology (not a tool)                             | Specification/API (interface only)                                                                                                               | Full ORM framework/provider linkedin+1                                                                                                                   |
| Framework?             | ❌ No framework (it's the concept)                             | ❌ No framework (just API spec)                                                                                                                   | ✅ Yes - full ORM framework interviewbit+1                                                                                                                |
| Package                | N/A                                                           | javax.persistence or jakarta.persistence                                                                                                         | org.hibernate geeksforgeeks                                                                                                                              |
| Key Annotations        | N/A                                                           | @Entity, @Id, @Table, @Column, @OneToMany, @ManyToOne, @Transient, @GeneratedValue geeksforgeeks                                                 | All JPA annotations plus Hibernate-specific: @Lazy, @BatchSize, @Formula, @Where, @Cache geeksforgeeks                                                   |
| Main Interface         | N/A                                                           | EntityManager, EntityManagerFactory                                                                                                              | SessionFactory, Session (Hibernate-specific) + JPA EntityManager geeksforgeeks                                                                           |
| Query Language         | N/A                                                           | JPQL (Java Persistence Query Language)                                                                                                           | HQL (Hibernate Query Language) + supports JPQL geeksforgeeks                                                                                             |
| Setup/Maven Dependency | N/A                                                           | Need implementation (e.g., hibernate-core) + javax.persistence-api                                                                               | org.hibernate:hibernate-core or hibernate-entitymanager geeksforgeeks                                                                                    |
| Config File            | N/A                                                           | persistence.xml (defines persistence unit)                                                                                                       | hibernate.cfg.xml or persistence.xml (JPA mode) + application.properties in Spring Boot geeksforgeeks                                                    |
| Spring Boot Setup      | N/A                                                           | Auto-configured via spring-boot-starter-data-jpa                                                                                                 | Auto-configured as default JPA provider in Spring Boot youtube                                                                                           |
| Example Dependency     | N/A                                                           | xml<br><dependency><br>  <groupId>org.springframework.boot</groupId><br>  <artifactId>spring-boot-starter-data-jpa</artifactId><br></dependency> | xml<br><dependency><br>  <groupId>org.hibernate</groupId><br>  <artifactId>hibernate-core</artifactId><br>  <version>6.x.x</version><br></dependency>    |
| Entity Example         | N/A                                                           | java<br>@Entity<br>@Table(name="users")<br>public class User {<br>  @Id<br>  @GeneratedValue<br>  private Long id;<br>}                          | Same as JPA plus Hibernate extensions: java<br>@Entity<br>@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)<br>@Lazy(false)<br>public class User { ... } |
| Additional Features    | N/A                                                           | Standard ORM features only                                                                                                                       | Advanced caching, lazy loading strategies, batch processing, dirty checking, second-level cache, native SQL support stackoverflow+1                      |
| Switchability          | N/A                                                           | ✅ Easy to switch implementations (portable API)                                                                                                  | ❌ Tied to Hibernate if using Hibernate-specific features stackoverflow                                                                                   |


## 8. Difference between `save() and `persist()`


Both **`save()`** and **`persist()`** are used to store an entity in the database, but they belong to different APIs and have some behavioral differences.

**Key Features**

| Feature      | **save()**                  | **persist()**               |
| ------------ | --------------------------- | --------------------------- |
| API          | **Hibernate** method        | **JPA** standard method     |
| Return Type  | Returns generated **ID**    | Returns **void**            |
| Standard     | Hibernate-specific          | JPA-standard                |
| Entity State | Makes entity **Persistent** | Makes entity **Persistent** |
| Portability  | Less portable               | More portable               |

**How It Works**

**- save()**

* A method provided by **Hibernate Session**.
* Saves the entity and returns the generated **primary key**.
* Can trigger an immediate insert to generate the ID.

```java
Session session = sessionFactory.openSession();

Employee emp = new Employee();
emp.setName("John");

Long id = (Long) session.save(emp);

System.out.println(id);
```

**- persist()**

* A method provided by **JPA EntityManager**.
* Makes the entity managed by the persistence context.
* Does not return the generated ID.
* The actual SQL INSERT usually happens during **flush** or **transaction commit**.

```java
EntityManager em = entityManagerFactory.createEntityManager();

Employee emp = new Employee();
emp.setName("John");

em.persist(emp);
```

**Why Use It**

* Use **`persist()`** when working with **JPA** because it follows the standard specification.
* Use **`save()`** when directly using **Hibernate APIs** and you need the generated ID immediately.

**When to Use**

**Use `persist()`**

* In **Spring Boot + JPA** applications.
* When writing **portable code** that can work with different JPA providers.
* Recommended for modern enterprise applications.

```java
@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Employee emp) {
        em.persist(emp);
    }
}
```

**Use `save()`**

* When working directly with **Hibernate Session**.
* When you need the generated ID returned immediately.

```java
Session session = sessionFactory.openSession();

Long id = (Long) session.save(employee);
```



## 9. Difference between save() and saveAndFlush()?


Both **`save()`** and **`saveAndFlush()`** are methods provided by **Spring Data JPA** to persist entities, but they differ in **when changes are written to the database**.

**Key Features**

| Feature                   | **save()**                 | **saveAndFlush()**                     |
| ------------------------- | -------------------------- | -------------------------------------- |
| Save Entity               | Yes                        | Yes                                    |
| Flush Persistence Context | No                         | Yes                                    |
| SQL Execution             | At flush/commit time       | Immediately                            |
| Return Type               | Saved Entity               | Saved Entity                           |
| Performance               | Better for bulk operations | Slightly slower due to immediate flush |

**How It Works**

**save()**

* Saves the entity in the **Persistence Context**.
* SQL query is not necessarily executed immediately.
* Changes are usually sent to the database during **flush** or **transaction commit**.

```java
Employee emp = new Employee();
emp.setName("John");

employeeRepository.save(emp);
```

**saveAndFlush()**

* Saves the entity and immediately calls **flush()**.
* Forces Hibernate/JPA to execute the SQL statement right away.
* Changes become visible in the database immediately within the current transaction.

```java
Employee emp = new Employee();
emp.setName("John");

employeeRepository.saveAndFlush(emp);
```

**Why Use It**

**save()**

* Better performance.
* Lets Hibernate optimize SQL execution.
* Recommended for most CRUD operations.

**saveAndFlush()**

* Ensures data is immediately written to the database.
* Useful when subsequent code depends on the database state.

**When to Use**

**Use `save()`**

* Standard create/update operations.
* Batch inserts or updates.
* Most Spring Boot applications.

```java
employeeRepository.save(employee);
```

**Use `saveAndFlush()`**

* Need the data immediately available in the database.
* Calling a stored procedure after saving.
* Running a query in the same transaction that depends on the newly saved data.

```java
employeeRepository.saveAndFlush(employee);

Employee result =
        employeeRepository.findById(employee.getId()).get();
```

**Example**

```java
@Transactional
public void createEmployee() {

    Employee emp = new Employee();
    emp.setName("John");

    employeeRepository.saveAndFlush(emp);

    // SQL INSERT already executed here
    System.out.println("Employee saved in DB");
}
```

| Aspect               | save()                                               | saveAndFlush()                                                     |
| -------------------- | ---------------------------------------------------- | ------------------------------------------------------------------ |
| Repository Interface | CrudRepository                                       | JpaRepository                                                      |
| Flush Timing         | Delays until flush() or commit()                     | Flushes immediately during execution baeldung+1                    |
| Database Write       | Adds to transactional buffer (memory)                | Forces database to write changes to disk right away tutorialspoint |
| Data Visibility      | Changes not visible outside transaction until commit | Changes visible outside the transaction immediately tutorialspoint |
| Bulk Save            | Supports saveAll() for batch operations              | Doesn't support bulk operations tutorialspoint                     |
| Performance          | More efficient (allows batching)                     | Less efficient (immediate SQL execution) codemia                   |



## 10. What is the difference between DAO and DTO?

**DAO (Data Access Object)** is used to **interact with the database** and perform CRUD operations like save, update, delete, and fetch data.

**DTO (Data Transfer Object)** is used to **transfer data between layers** of an application (Controller, Service, etc.). Transfer only required fields instead of full entity objects. it usually contains only fields with getters and setters.

**Example:**

* **DAO:** `EmployeeRepository` that accesses the database.
* **DTO:** `EmployeeDTO` used to send employee data in API requests or responses.


## 10. What is the N+1 Query Problem and How Do You Fix It?

So the N+1 problem happens when you load a list of entities and then for each entity, JPA fires a separate query to load its related data.

For example, you load 10 orders — that's 1 query. Then for each order, it loads the customer — that's 10 more queries. Total = 11 queries. That's N+1.

**Fix it using JOIN FETCH:**

```java
@Query("SELECT o FROM Order o JOIN FETCH o.customer")
List<Order> findAllWithCustomer();
```

Or use `@EntityGraph`:

```java
@EntityGraph(attributePaths = {"customer"})
List<Order> findAll();
```

This loads everything in a single query instead of N+1 separate ones.


## 11. What is Optimistic vs Pessimistic Locking?

**Optimistic locking** assumes **conflicts are rare**, so users can read and modify data without locking it immediately. Before updating, the system checks whether another transaction has already changed the data.

Usually implemented using a **version column**.


```java
// Optimistic - uses @Version
@Entity
public class Product {
    @Id private Long id;
    @Version private int version; // auto-checked on update
    private int stock;
}

// If two threads update same Account simultaneously,
// second one gets: javax.persistence.OptimisticLockException
```

**Pessimistic Locking** — locking assumes **conflicts are common**, so data is locked immediately to prevent other transactions from modifying it until completion."

```java
// Pessimistic - locks row in DB
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdForUpdate(@Param("id") Long id);
```

**`@Version`** enables optimistic locking. Hibernate auto-increments the version on each update and throws `OptimisticLockException` if two transactions update the same record.

| | Optimistic | Pessimistic |
|---|---|---|
| Mechanism | Version check at commit | DB-level lock (SELECT FOR UPDATE) |
| Use case | Low contention | High contention |
| Performance | Better | Slower |

Use optimistic for read-heavy apps, pessimistic for write-heavy or financial systems.


## 12. What is JPQL vs Native Query?

**JPQL** (Java Persistence Query Language) works with entity class names and field names — not table names. It's database-independent.

```java
@Query("SELECT e FROM Employee e WHERE e.department = :dept")
List<Employee> findByDept(@Param("dept") String dept);
```

**Native Query** uses actual SQL with real table and column names. Use it when you need database-specific features or complex queries JPQL can't handle.

```java
@Query(value = "SELECT * FROM employee WHERE department = :dept", nativeQuery = true)
List<Employee> findByDeptNative(@Param("dept") String dept);
```

Prefer JPQL for portability. Use native query only when needed.


## 13. What are JPA Cascade Types?

Cascade means — *when you do an operation on a parent entity, automatically apply it to child entities too.*

| Type | Behavior |
|---|---|
| `PERSIST` | Save child when parent is saved |
| `MERGE` | Update child when parent is updated |
| `REMOVE` | Delete child when parent is deleted |
| `REFRESH` | Refresh child when parent is refreshed |
| `DETACH` | Detach child when parent is detached |
| `ALL` | Apply all of the above |

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> items;
```

Be careful with `REMOVE` — it can delete child records you didn't intend to delete.


## 14. What is Database Indexing and When to Use It?

**Database indexing** is is a data structure technique used to speed up data retrieval operations. it like a book's table of contents — it helps the database find rows faster without scanning the whole table.

**When to use:**
- Columns used in `WHERE`, `JOIN`, `ORDER BY`, or `GROUP BY`
- Foreign key columns
- Columns with high cardinality (many unique values)

**When NOT to use:**
- Small tables
- Columns that are updated very frequently
- Columns with very low cardinality (like boolean flags)

```sql
CREATE INDEX idx_employee_email ON employee(email);
```

In JPA:
```java
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class Employee { }
```

Indexes speed up reads but slow down writes — so use them wisely.


## 15. What is `FetchType.LAZY` vs `FetchType.EAGER` In Depth?

**EAGER** — loads related data immediately when the parent is loaded, even if you don't need it.

**LAZY** — loads related data only when you actually access it (on-demand).

```java
@OneToMany(fetch = FetchType.LAZY)   // default for collections
private List<Order> orders;

@ManyToOne(fetch = FetchType.EAGER)  // default for single associations
private Department department;
```

**The problem with LAZY** — if you access lazy data outside a transaction (after the session is closed), you get `LazyInitializationException`.

**Fix:** Use `JOIN FETCH` or `@Transactional` to keep the session open, or use DTOs.

**Best practice:** Always prefer LAZY. Load eagerly only when you always need the related data together.



## 16. What is LazyInitializationException?

**`LazyInitializationException`** occurs when Hibernate tries to load a lazily fetched entity or collection after the Hibernate session has already been closed.


```java
@Entity
class Employee {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> addresses;
}
```

```java
Employee emp = employeeRepository.findById(1L).get();
// Session closes here

emp.getAddresses().size(); // LazyInitializationException
```

Since `addresses` is loaded lazily, Hibernate tries to fetch it only when `getAddresses()` is called. But the session is already closed, so it throws `LazyInitializationException`.

**How to Fix It**

    1. Access the lazy data inside a transaction.
    2. Use `JOIN FETCH` in JPQL.
       ```java
       SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = 1
       ```
    3. Use DTO projections instead of entities for web layer.
    4. Use `FetchType.EAGER` only when necessary.


## 17. Entity lifecycle states?

| State | Description |
|---|---|
| **Transient** | New object, not tracked by JPA |
| **Managed** | Tracked by persistence context |
| **Detached** | Was managed, now outside context |
| **Removed** | Scheduled for deletion |

```java
User user = new User("John");          // Transient

entityManager.persist(user);           // Managed - changes auto-synced

entityManager.detach(user);            // Detached - changes NOT tracked
user.setName("Jane");                  // won't be saved

entityManager.merge(user);             // Managed again

entityManager.remove(user);            // Removed
```


## 18. What is dirty checking?


**Dirty Checking** is a feature of **Hibernate/JPA** that automatically detects changes made to a managed entity and updates the database without explicitly calling `save()` or `update()`.

**Key Features**

* Automatic change detection
* Works inside a **Transaction**
* Reduces boilerplate code
* Managed by the **Persistence Context**
* Automatically generates **UPDATE** queries

**How It Works**

1. Hibernate loads an entity from the database.
2. The entity becomes **Managed** by the **Persistence Context**.
3. If the entity's data is modified, Hibernate tracks the changes.
4. At **Transaction Commit**, Hibernate compares the current state with the original state.
5. If changes are found, Hibernate automatically executes an **UPDATE** statement.

**Code Example**

```java
@Transactional
public void updateUser(Long id) {

    User user = userRepository.findById(id).get();
    // Hibernate creates snapshot: {id: 1, name: "John", salary: 50000}

    user.setName("John Updated");

    // Hibernate marks entity as "dirty"
    
    // No need to call employeeRepository.save()!
    // At transaction commit, Hibernate automatically executes:
    // UPDATE employee SET name = 'John Updated' WHERE id = 1
}
```

No explicit `save()` call is needed.

Hibernate automatically generates:

```sql
UPDATE users
SET name = 'John Updated'
WHERE id = 1;
```

**Why to Use**

* Reduces manual database updates
* Simplifies code
* Improves developer productivity
* Ensures entity changes are persisted automatically

**When to Use**

Use **Dirty Checking** when working with **managed entities** inside a **@Transactional** method.


## 19. 0000

## 20. What is auditing and How it works in JPA?


In Java (especially enterprise applications like Spring Boot), **auditing** means **tracking and recording changes made to data**, such as:

* **Who** created the record
* **When** the record was created
* **Who** updated the record
* **When** it was last updated


**How it works**

Spring Data JPA provides `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy` via `@EnableJpaAuditing`.

```java
// 1. Enable auditing
@SpringBootApplication
@EnableJpaAuditing
public class App {}

// 2. Auditable base entity
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

// 3. Extend it
@Entity
public class User extends Auditable {
    @Id @GeneratedValue private Long id;
    private String name;
}
```


# ✅ 15. Java Lambda Expressions & Streams API 

## 2. What are lambda expressions?

**Lambda expressions** in Java are a short and clear way to represent **anonymous functions** (functions without a name).

They were introduced in **Java 8** to support **functional programming** and make code more readable and concise.

```java
(parameters) -> expression
```

```java
// Before lambda - anonymous class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// With lambda - concise
Runnable r2 = () -> System.out.println("Hello");

// Lambda with parameters
List<String> names = Arrays.asList("John", "Jane");
names.forEach(name -> System.out.println(name));
```

## 3. What are method references?

A **Method Reference** is a **shorthand syntax of a lambda expression** that refers to an existing method using `::` operator.

**Types of Method References:**
- Static method: `ClassName::methodName`
- Instance method: `object::methodName`
- Instance method of arbitrary object: `ClassName::methodName`
- Constructor: `ClassName::new`

```java
// Lambda expression
OptionalInt maxAge = studlist.stream()
        .mapToInt(student -> student.getAge())
        .max();

// Method reference - more concise
OptionalInt maxAge = studlist.stream()
        .mapToInt(Student::getAge)
        .max();

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## 4. What is the difference between lambda and anonymous class?

**Lambda expressions** in Java are a short and clear way to represent **anonymous functions** (functions without a name).

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

## 5. What is Stream API?

**Definition:**
The **Stream API** is introduced in Java 8 to process **collections of objects in a functional and declarative way**. It allows operations like **filtering, mapping, and reducing** without modifying the original data source.


**Key Features:**

* Supports **functional programming**
* Works with **Collections, Arrays, I/O**
* Uses **lambda expressions**
* Provides **lazy evaluation**
* Enables **parallel processing**
* Does not modify the **original data**


**How it works:**

* Data source (like **List, Set, Array**) is converted into a **Stream**
* A sequence of operations is applied:

  * **Intermediate operations** (filter, map)
  * **Terminal operations** (collect, forEach)
* Operations are executed only when a **terminal operation is called**


**Why to use:**

* To write **clean and readable code**
* To reduce **boilerplate loops**
* To improve **performance with parallel processing**
* To support **functional programming style**


**When to use:**

* When processing **collections of data**
* When applying **filtering, transformation, aggregation**
* When you want **declarative code instead of loops**


**Code Example:**

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)     // filter even numbers
                .map(n -> n * n)             // square them
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**Loop vs Stream API** 
Generally, a **for loop is faster** than the Stream API because it has **less overhead** and runs directly in the JVM.

**Stream API** provides **better readability, functional style, and parallel processing**, but it may have a **small performance cost**.


## 6. What is parallel streams? 

**Definition:**
A **Parallel Stream** is a type of **Stream API** that processes data **concurrently using multiple threads**, dividing the task into smaller parts and executing them in parallel to improve performance.


**Key Features:**

* Uses **multiple threads (ForkJoinPool framework)**
* Automatically splits data into **subtasks**
* Improves **performance for large datasets**
* Supports **parallel processing**
* Works with **collections via .parallelStream()**
* Maintains **functional programming style**


**How it works:**

* Source collection is divided into **multiple chunks**
* Each chunk is processed in **separate threads**
* Results are combined using **merge operation**
* Uses **ForkJoin framework internally**


**Why to use:**

* To improve **performance on large data sets**
* To utilize **multi-core CPU power**
* To reduce **processing time for heavy operations**


**When to use:**

* When working with **large collections**
* When operations are **CPU-intensive**
* When tasks are **independent and stateless**


**Code Example:**

```java id="k3p9qd"
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        numbers.parallelStream()
                .map(n -> {
                    System.out.println(Thread.currentThread().getName() + " processing " + n);
                    return n * n;
                })
                .forEach(System.out::println);
    }
}
```

## 7. What is the difference between Collection and Stream?

A **Collection** is a **data structure** that stores elements in memory, like `List`, `Set`, or `Map`. It holds data and allows operations such as add, remove, or iterate, and it can be traversed multiple times.

A **Stream** is **not a data structure**; it’s a **data-processing abstraction**. It doesn’t store data but processes elements from a collection or other sources. Streams are **one-time use**, support **functional operations** like `filter` and `map`, and enable easy **parallel processing**.

```java
List<String> collection = Arrays.asList("a", "b", "c");
collection.add("d"); // Modifies collection

Stream<String> stream = collection.stream();
stream.filter(s -> s.length() > 1); // Doesn't modify collection
// stream.filter(...); // Error - stream already used
```

## 8. What are intermediate and terminal operations?


**Definition:**
A **Collection** is a data structure that stores and manages **data in memory**, while a **Stream** is a sequence of elements used to **process data in a functional way without storing it**.


**Key Differences:**

| **Aspect**       | **Collection**                                | **Stream**                                           |
| ---------------- | --------------------------------------------- | ---------------------------------------------------- |
| **Nature**       | Stores **data**                               | Processes **data**                                   |
| **Storage**      | Holds elements in **memory**                  | Does not store data, works as a **data pipeline**    |
| **Modification** | Can be **modified (add/remove elements)**     | **Immutable**, does not modify source data           |
| **Iteration**    | Uses **external iteration (for/while loops)** | Uses **internal iteration (Stream API operations)**  |
| **Reusability**  | Can be **reused multiple times**              | Can be used **only once**                            |
| **Processing**   | Processes data **manually**                   | Uses **functional operations (filter, map, reduce)** |


**How it works:**

* **Collection:** Data is stored first, then processed using loops
* **Stream:** Data flows through a **pipeline of operations** (intermediate + terminal)


**Why to use:**

* **Collection:** When you need to **store and manage data**
* **Stream:** When you need to **process data efficiently and cleanly**


**When to use:**

* **Collection:** For CRUD operations and data storage
* **Stream:** For **data transformation, filtering, aggregation**


**Code Example:**

**Collection Example:**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
```

**Stream Example:**

```java
import java.util.*;
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

numbers.stream()
    .filter(n -> n > 2)     // Intermediate - lazy
    .map(n -> n * 2)        // Intermediate - lazy
    .sorted()               // Intermediate - lazy
    .forEach(System.out::println); // Terminal - triggers execution
```

## 9. What is the difference between map() and flatMap()?



`map()` is used to **transform each element** in a stream into another form. It returns **one output for each input**, so the structure of the stream stays the same.

`flatMap()` is used when each element produces **another stream or collection**. It **flattens** those nested streams into a **single stream**, so you don’t end up with a stream of streams.

**`map()`**

* Transforms each element → **1 to 1**
* Returns same number of elements

**`flatMap()`**

* Transforms each element → **1 to many**
* Flattens nested structure


```java

// map() - one-to-one
List<String> names = List.of("java", "spring");

List<String> result = names.stream()
    .map(String::toUpperCase)
    .toList();

System.out.println(result);
// Output: [JAVA, SPRING]

// flatMap() - one-to-many, then flatten
List<List<String>> list = List.of(
    List.of("A", "B"),
    List.of("C", "D")
);

List<String> result = list.stream()
    .flatMap(Collection::stream)
    .toList();

System.out.println(result);
// Output: [A, B, C, D]
```

## 10. What is Optional class?

**Optional class** is a container class that may or may not contain a value. It helps avoid **NullPointerException** and makes null handling more explicit and safer.

```java
import java.util.Optional;

public class Test {
    public static void main(String[] args) {

        String name = null;

        Optional<String> optional = Optional.ofNullable(name);

        // optional way one
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("Value is null");
        }

        // optional way two
        System.out.println(optional.orElse("Default Value"));
    }
}
```

## 11. What is diffence between Arrays.asList() vs List.of()?
`Arrays.asList()` creates a **fixed-size list** backed by the original array. You can modify the elements but cannot add or remove them. It allows `null` values.

`List.of()` creates an **immutable list** that does not allow `null` values. You cannot modify, add, or remove elements from this list.

```java
// Arrays.asList() - fixed-size, allows null   
List<String> list1 = Arrays.asList("a", "b", null);
list1.set(0, "x"); // OK - modifies element
// list1.add("c"); // Error - cannot add
// List.of() - immutable, does not allow null

List<String> list2 = List.of("a", "b", "c");
// list2.set(0, "x"); // Error - cannot modify
// list2.add("d"); // Error - cannot add
```


# ✅ 16. Java JDBC 

## 1. What is JDBC ?

JDBC (Java Database Connectivity) is a Java API that provides a standard way to connect and interact with relational databases. It acts as a bridge between Java applications and databases.

- Standard API for database connectivity
- Database-independent interface
- Supports SQL operations (CRUD)
- Works with any JDBC-compliant database
- Part of Java SE platform

JDBC allows Java applications to execute SQL statements, retrieve results, and manage database connections in a portable way across different database vendors.

## 2. What are the steps to connect to a database using JDBC?

There are 5 main steps to establish database connectivity using JDBC.

**Steps:**
1. **Load Driver:** Register database driver
2. **Create Connection:** Establish database connection
3. **Create Statement:** Prepare SQL statement
4. **Execute Query:** Run SQL and get results
5. **Close Resources:** Clean up connections

```java
// Step 1: Load driver (optional in modern JDBC)
Class.forName("com.mysql.cj.jdbc.Driver");

// Step 2: Create connection
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");

// Step 3: Create statement
Statement stmt = conn.createStatement();

// Step 4: Execute query
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// Step 5: Close resources
rs.close(); stmt.close(); conn.close();
```

## 3. What is the difference between Statement and PreparedStatement?

A **Statement** is used to execute **static SQL queries**. The SQL query is sent to the database every time it runs, so it’s less efficient and more vulnerable to **SQL injection**.

A **PreparedStatement** is used for **parameterized queries**. The SQL is **precompiled and cached** by the database, which improves performance for repeated execution and **prevents SQL injection** by safely handling input values.

```java
// Statement - vulnerable to SQL injection
Statement stmt = conn.createStatement();
String sql = "SELECT * FROM users WHERE id = " + userId; // Dangerous!
ResultSet rs = stmt.executeQuery(sql);

// PreparedStatement - safe and efficient
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```

## 4. What is connection pooling and how it works internally?

**Connection pooling is a technique used to reuse database connections instead of creating a new connection every time a request comes.**

**How It Works Internally (Simple Explanation)**

1. When the application starts, it **creates a fixed number of database connections** and stores them in a pool.
2. When a request needs database access, it **borrows a connection from the pool**.
3. After completing the work, the connection is **returned back to the pool**, not closed.
4. The same connection can then be reused by another request.
5. If all connections are busy:

   * The request either **waits** for a free connection
   * Or throws a timeout exception (based on configuration)

**Benefits:**
- **Reduced connection overhead:** Avoid expensive connection creation
- **Better resource utilization:** Limit concurrent connections
- **Improved response times:** Reuse existing connections
- **Database protection:** Prevent connection exhaustion
- **Scalability:** Handle more concurrent users

**Popular Connection Pools:**
- HikariCP (fastest)
- Apache DBCP2
- C3P0
- Tomcat JDBC Pool

```java
// HikariCP example
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);

HikariDataSource dataSource = new HikariDataSource(config);
Connection conn = dataSource.getConnection(); // From pool
```


## 5. What is caching and how it works inernally(Implementation)?

**Caching** in Java is a technique of **storing frequently used data in memory** so that we don’t have to fetch it again from a slow source like a database or external API.

* **`@Cacheable`** → Stores the method result in cache and returns cached data for the same request instead of executing the method again.
* **`@CachePut`** → Always executes the method and **updates the cache with the latest result**.
* **`@CacheEvict`** → Removes specific or all entries from the cache when data becomes invalid or is deleted.

In Java, caching can be broadly categorized into **three main types** based on where the data is stored.

- **Local Cache (In-Memory Cache):** Data is stored **inside the application memory** for fast access. Example using Caffeine


**1. Local Cache – Caffeine (Real-Time Example: Product Catalog)**

**Use case:** Frequently accessed product data → store in local cache to avoid DB calls.

**Service Class**

```java
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class ProductService {

    private static Cache<Long, String> productCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    public String getProduct(Long id) {

        String product = productCache.getIfPresent(id);

        if (product != null) {
            System.out.println("From Cache");
            return product;
        }

        // Simulate DB call
        System.out.println("From Database");
        product = "Laptop";

        productCache.put(id, product);

        return product;
    }

    public static void main(String[] args) {
        ProductService service = new ProductService();

        System.out.println(service.getProduct(1L)); // DB
        System.out.println(service.getProduct(1L)); // Cache
    }
}
```

**2. Distributed Cache – Redis (Real-Time Example: User Service)**

**Use case:** Multiple servers → shared cache → Redis

**application.properties**

```properties
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

**Enable Cache**

```java
@Configuration
@EnableCaching
public class CacheConfig {
}
```

**Service**

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB...");
        return userRepository.findById(id).orElse(new User(id, "Default User"));
    }
}
```

**Controller**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }
}
```

**First call → DB**
**Second call → Redis cache**


**3. Database Cache – Hibernate Second Level Cache (Real-Time Example: Product Table)**

**Entity**

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Cacheable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product {

    @Id
    private Long id;
    private String name;

    // getters and setters
}
```

**application.properties**

```properties
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
spring.cache.jcache.config=classpath:ehcache.xml
```

**ehcache.xml**

```xml
<config xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
        xmlns='http://www.ehcache.org/v3'>

    <cache alias="Product">
        <heap unit="entries">1000</heap>
        <expiry>
            <ttl unit="minutes">10</ttl>
        </expiry>
    </cache>
</config>
```

**First time → DB**
**Second time → Hibernate L2 Cache**


**Real-Time When to Use What**

| Scenario              | Cache Type   |
| --------------------- | ------------ |
| Single application    | Caffeine     |
| Multiple servers      | Redis        |
| ORM level caching     | Hibernate L2 |
| Frequently read data  | Cache        |
| Frequently write data | Avoid cache  |


**Caffeine vs Redis**

| Caffeine                   | Redis                        |
| -------------------------- | ---------------------------- |
| Local cache                | Distributed cache            |
| Stored inside app memory   | Separate server              |
| Ultra fast                 | Slight network latency       |
| Not shared across services | Shared across services       |
| Best for single instance   | Best for distributed systems |



## 6. Create File upload API to Handle Large Data Processing?

* **Use Streaming** (`Stream<T>`, file streaming) instead of loading all records with `findAll()`
* **Use Batch Processing** to process records in chunks and reduce memory consumption
* **Use Database Pagination** (`Page<T>`, `Slice<T>`, `Stream<T>`) for large datasets
* **Use Async / Parallel Processing** (`@Async`, `CompletableFuture`, ExecutorService) for concurrent workloads
* **Use Caching** (Redis, Caffeine, EhCache, Spring Cache) to reduce repeated database calls
* **Use JDBC Batch Operations** (`spring.jpa.properties.hibernate.jdbc.batch_size`) for bulk inserts/updates
* **Use Sharding** when data becomes too large for a single database server


**Step 1 — Client Uploads File**

Controller accepts file without loading everything into memory.

```java id="v50d5s"
@RestController
@RequestMapping("/files")
class FileUploadController {
    private final FileProcessingService service;
    public FileUploadController(FileProcessingService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        service.processFile(file);
        return "File Accepted";
    }
}
```


**Step 2 — Service Layer**

```java id="cx88hj"
@Service
class FileProcessingService {
    private final DataRepository repository;
    private static final int BATCH_SIZE = 1000;

    public FileProcessingService(DataRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<Void> processFile( MultipartFile file) throws Exception {
        List<DataEntity> batch = new ArrayList<>();

        try (
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    file.getInputStream()
                            )
                    )
        ) {

            String line;
            while ((line = reader.readLine()) != null) {
                DataEntity entity = mapToEntity(line);
                batch.add(entity);
                if (batch.size() == BATCH_SIZE) {
                    saveBatch(batch);
                    batch.clear();
                }
            }

            // remaining records
            if (!batch.isEmpty()) {
                saveBatch(batch);
            }
        }

        return CompletableFuture.completedFuture(null);
    }

    private DataEntity mapToEntity(String line) {
        DataEntity entity = new DataEntity();
        entity.setName(line);
        return entity;
    }

    private void saveBatch(List<DataEntity> batch) {
        repository.saveAll(batch);
        System.out.println(
                "Saved batch size: " + batch.size()
        );
    }
}
```


**Step 3 — Entity**

```java id="zwv22u"
@Entity
class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```


**Step 4 — Repository**

```java id="v74d5u"
@Repository
interface DataRepository extends JpaRepository<DataEntity, Long> {
}
```


**Step 5 — Enable Async**

```java id="9vkdpn"
@EnableAsync
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```

## 6. How to handle large data efficiently?

* **Use Streaming** (`Stream<T>`, file streaming) instead of loading all records with `findAll()`
* **Use Batch Processing** to process records in chunks and reduce memory consumption
* **Use Database Pagination** (`Page<T>`, `Slice<T>`, `Stream<T>`) for large datasets
* **Use Async / Parallel Processing** (`@Async`, `CompletableFuture`, ExecutorService) for concurrent workloads
* **Use Caching** (Redis, Caffeine, EhCache, Spring Cache) to reduce repeated database calls
* **Use JDBC Batch Operations** (`spring.jpa.properties.hibernate.jdbc.batch_size`) for bulk inserts/updates
* **Use Sharding** when data becomes too large for a single database server


**1. 🔄 Streaming (Already Have)**
```java
// WRONG - Loads everything into memory
List<User> users = userRepo.findAll(); // OOM risk with millions of records

// RIGHT - Stream row by row
@Query("SELECT u FROM User u")
Stream<User> streamAllUsers();

// Usage (must be in @Transactional)
@Transactional(readOnly = true)
public void processUsers() {
    try (Stream<User> stream = userRepo.streamAllUsers()) {
        stream.forEach(this::processUser);
    }
}
```


**2. 🧩 Batch Processing (Already Have)**
```java
// Spring Batch - Chunk-Oriented Processing
@Bean
public Step processUsersStep() {
    return stepBuilderFactory.get("processUsers")
        .<User, ProcessedUser>chunk(1000)   // Read 1000, Process, Write 1000
        .reader(userItemReader())
        .processor(userItemProcessor())
        .writer(userItemWriter())
        .build();
}
```
**Key Interview Point:** Spring Batch gives you **restart/retry**, **job monitoring**, and **skip logic** out of the box.


**3. 📄 Database Pagination (Already Have)**
```java
// Page<T> - gives totalCount (extra COUNT query)
Page<User> page = userRepo.findAll(PageRequest.of(0, 100, Sort.by("id")));

// Slice<T> - No COUNT query, better performance
Slice<User> slice = userRepo.findAll(PageRequest.of(0, 100));

// Keyset / Cursor Pagination - BEST for large datasets
@Query("SELECT u FROM User u WHERE u.id > :lastId ORDER BY u.id")
List<User> findNext(@Param("lastId") Long lastId, Pageable pageable);
```
**Key Difference:** Offset pagination degrades at high page numbers. **Keyset pagination stays O(log n)**.


**4. ⚡ Async / Parallel Processing (Already Have)**
```java
@Async("taskExecutor")
public CompletableFuture<Result> processChunk(List<User> chunk) {
    // processed in separate thread
    return CompletableFuture.completedFuture(result);
}

// Combine all futures
List<CompletableFuture<Result>> futures = chunks.stream()
    .map(this::processChunk)
    .collect(toList());

CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
```


**5. 🗄️ Caching (Already Have)**
```java
@Cacheable(value = "users", key = "#id")
public User getUser(Long id) { ... }

@CacheEvict(value = "users", key = "#id")
public void updateUser(Long id, User user) { ... }
```
**Interview Tip:** Discuss **cache eviction strategies** (LRU, TTL), **cache stampede** problem, and when **NOT to cache** (frequently changing data).


**6. 🗃️ JDBC Batch Operations (Already Have)**
```yaml
# application.yml
spring:
  jpa:
    properties:
      hibernate:
        jdbc.batch_size: 50
        order_inserts: true   # Critical - groups same entity inserts
        order_updates: true
```
```java
// Use saveAll() - Hibernate batches automatically
userRepo.saveAll(listOf1000Users); // 1000 inserts → ~20 batch calls
```

**7. 🔀 Sharding (Already Have)**
```
User Table Sharded by user_id:
  Shard 0 → user_id % 4 == 0  → DB Server 1
  Shard 1 → user_id % 4 == 1  → DB Server 2
  Shard 2 → user_id % 4 == 2  → DB Server 3
  Shard 3 → user_id % 4 == 3  → DB Server 4
```
**Mention:** Apache ShardingSphere or Vitess for Java-based sharding solutions.


**🆕 MUST-ADD Points for Interview**


**8. 📨 Message Queue / Event-Driven Processing**
```java
// Producer - Offload heavy processing
@Service
public class OrderService {
    public void placeOrder(Order order) {
        orderRepo.save(order);
        kafkaTemplate.send("order-processing", order); // Non-blocking
    }
}

// Consumer - Process asynchronously at own pace
@KafkaListener(topics = "order-processing", concurrency = "5")
public void processOrder(Order order) {
    heavyProcessingService.process(order);
}
```
**Why it matters:** Decouples producers from consumers, handles **back-pressure**, survives **service restarts**.


**9. 📊 Read Replicas / CQRS Pattern**
```java
// Route reads to replica, writes to primary
@Bean
public DataSource routingDataSource() {
    Map<Object, Object> sources = new HashMap<>();
    sources.put("WRITE", primaryDataSource());
    sources.put("READ",  replicaDataSource());

    AbstractRoutingDataSource routing = new AbstractRoutingDataSource() {
        protected Object determineCurrentLookupKey() {
            return TransactionSynchronizationManager
                       .isCurrentTransactionReadOnly() ? "READ" : "WRITE";
        }
    };
    routing.setTargetDataSources(sources);
    return routing;
}

@Transactional(readOnly = true) // → Goes to READ replica
public List<User> getUsers() { ... }
```


**10. 🏗️ Projection / DTO Fetching (Select Only What You Need)**
```java
// BAD - Fetches entire entity + all columns
List<User> users = userRepo.findAll();

// GOOD - Fetch only needed fields
@Query("SELECT new com.app.dto.UserSummary(u.id, u.name) FROM User u")
List<UserSummary> findUserSummaries();

// Interface Projection
public interface UserSummary {
    Long getId();
    String getName();
}
List<UserSummary> users = userRepo.findBy(); // Spring Data handles it
```
**Impact:** Can reduce data transfer by **60-80%** for wide tables.


**11. 🗜️ Data Archival / Partitioning**
```sql
-- Partition orders table by year (MySQL)
CREATE TABLE orders (
    id BIGINT, order_date DATE, ...
) PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025)
);
```
```java
// Archive old data to cold storage
@Scheduled(cron = "0 0 2 * * SUN") // Every Sunday 2AM
public void archiveOldOrders() {
    orderRepo.moveToArchive(LocalDate.now().minusYears(2));
}
```

**12. 🔍 Index Optimization (Often Overlooked)**
```java
// Composite index for common query patterns
@Table(indexes = {
    @Index(name = "idx_user_status_date",
           columnList = "status, created_date DESC"),
    @Index(name = "idx_user_email", columnList = "email", unique = true)
})

// Covering index - query served entirely from index
@Query(value = "SELECT id, name FROM users USE INDEX (idx_covering) 
                WHERE status = ?1", nativeQuery = true)
```
**Interview Tip:** Explain difference between **clustered vs non-clustered** index and **index selectivity**.


## 6. A bumper/Black Friday sale has 4 core challenges: high traffic, dynamic pricing, inventory race conditions, and coupon abuse. How to handle it?


During bumper offers or Black Friday sales, I would design the system to handle high traffic using load balancing, caching, auto-scaling, message queues, and database optimization. This prevents the application from crashing and ensures a smooth user experience.

**Key Strategies:**

1. **Rate Limiting & Throttling** — Prevent system overload by limiting requests per user
2. **Queue-Based Processing** — Use message queues (Kafka/RabbitMQ) to handle burst traffic asynchronously.
3. **Database Optimization** → Use indexing, read replicas, and connection pooling.
4. **Inventory Locking** — Use database transactions or Redis to prevent overselling
5. **Caching** — Reduce database calls for frequently accessed data.
6. **Load Balancing** — Distribute requests across multiple servers
7. **Auto Scaling** → Automatically add more application instances during peak traffic.

```java
// Rate Limiter using Redis
public class RateLimiter {
    private RedisTemplate<String, Integer> redis;
    
    public boolean allowRequest(String userId, int maxRequests, int windowSeconds) {
        String key = "rate_limit:" + userId;
        Integer count = redis.opsForValue().increment(key);
        
        if (count == 1) {
            redis.expire(key, windowSeconds, TimeUnit.SECONDS);
        }
        
        return count <= maxRequests; // Return true if under limit
    }
}

// Inventory Management with Redis (prevents overselling)
public class InventoryService {
    private RedisTemplate<String, Integer> redis;
    
    public boolean reserveInventory(String productId, int quantity) {
        String key = "inventory:" + productId;
        Integer available = redis.opsForValue().get(key);
        
        if (available != null && available >= quantity) {
            redis.opsForValue().decrement(key, quantity);
            return true; // Reserved successfully
        }
        return false; // Not enough inventory
    }
}

// Black Friday Sale Handler
public class SaleService {
    private RateLimiter rateLimiter;
    private InventoryService inventoryService;
    
    public PurchaseResult purchaseProduct(String userId, String productId, int quantity) {
        // 1. Rate limit check
        if (!rateLimiter.allowRequest(userId, 10, 60)) {
            return PurchaseResult.REJECTED("Too many requests");
        }
        
        // 2. Reserve inventory
        if (!inventoryService.reserveInventory(productId, quantity)) {
            return PurchaseResult.REJECTED("Item out of stock");
        }
        
        // 3. Process order (async via queue)
        orderQueue.send(new OrderEvent(userId, productId, quantity));
        
        return PurchaseResult.SUCCESS("Order placed");
    }
}
```



## 7. What is @Profile Annotation?

**Profiles** allow you to segregate parts of your application configuration for different environments like dev, test, and production.

```java
# application.properties
spring.profiles.active=dev

# application-dev.properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb

# application-prod.properties
server.port=80
spring.datasource.url=jdbc:mysql://prod-server:3306/mydb
```

```java
@Component
@Profile("dev")
public class DevDataLoader {
    // Only loaded in dev profile
}
```

## 9. What is application.properties file and how value read from there?

`application.properties` is a **configuration file in Spring Boot** used to store **application settings**, such as database URLs, server ports, or custom values.

**Example (`application.properties`):**

```properties id="0z5kqg"
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
app.name=MySpringApp
```

**Read Values from `application.properties`**

**1. Using `@Value`**

```java id="4y9qmr"
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Value("${app.name}")
    private String appName;

    public void printName() {
        System.out.println("Application Name: " + appName);
    }
}
```

**2. Using `@ConfigurationProperties`**

```java id="1sy8y7"
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;

    // getter and setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

## 10. How does Spring Boot decide which configuration file to load?

Spring Boot loads config in this priority order (higher overrides lower):

1. Command-line args (`--server.port=9090`)
2. `SPRING_APPLICATION_JSON` env variable
3. OS environment variables
4. `application-{profile}.properties` (profile-specific)
5. `application.properties` (default)
6. `@PropertySource` annotations
7. Default values in code

File locations searched (in order):
```
./config/
./
classpath:/config/
classpath:/          ← lowest priority
```


## 11. What is centralized configuration in microservices?

Centralized configuration means storing all microservice configurations in a **single external location** (like a Git repo) instead of bundling them inside each service's JAR/WAR.

- All services fetch their config from a **Config Server** at startup
- Changes can be applied **without redeploying** services
- Supports environment-specific configs (dev, staging, prod)

Solution: **Spring Cloud Config Server**

```
[Config Server] ←→ [Git Repo with all configs]
      ↑
[Service A] [Service B] [Service C]  ← all fetch config from Config Server
```

```yaml
# bootstrap.yml in each microservice
spring:
  config:
    import: "configserver:http://config-server:8888"
  application:
    name: order-service   # fetches order-service.yml from git
```


## 13. How can we create a centralized configuration for all microservices?

**In microservive If I do change in application.yml file, do I need to restart the the service everytime. will that chagne to apply** 

By default, Spring Boot loads the configuration only during application startup, so runtime changes in `application.yml` are not automatically picked up.

However, using tools like `@RefreshScope`, Spring Cloud Config, and Actuator refresh endpoints, some configuration changes can be applied without restarting the service.


**Step 1: Create Config Server**

**pom.xml dependency:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

**Main class:**
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
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/config-repo
          default-label: main
```


**Step 2: Create Git Config Repository**

Create a GitHub repo (e.g., `config-repo`) and add config files:

```
config-repo/
  order-service.yml
  order-service-dev.yml
  order-service-prod.yml
```

**order-service.yml:**
```yaml
app:
  message: "Hello from Config Server"
  timeout: 5000
```


**Step 3: Configure Client Microservice**

**pom.xml dependency:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

**application.yml (client):**
```yaml
spring:
  application:
    name: order-service        # must match filename in config-repo
  config:
    import: "configserver:http://localhost:8888"
  profiles:
    active: dev
```


**Step 4: Use Config Values in Code**

```java
@RestController
@RefreshScope   // enables runtime refresh without restart
public class OrderController {

    @Value("${app.message}")
    private String message;

    @GetMapping("/info")
    public String info() {
        return message;
    }
}
```


**Step 5: Dynamic Refresh (Without Restart)**

Add Actuator dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Expose refresh endpoint in client's `application.yml`:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: refresh
```

Trigger refresh after config change:
```bash
POST http://localhost:8080/actuator/refresh
```


**Config Resolution Order**

```
{app-name}-{profile}.yml  →  {app-name}.yml  →  application.yml
```

Example for `order-service` with `dev` profile:
1. `order-service-dev.yml`
2. `order-service.yml`
3. `application.yml`


**Interview Key Points**

| Point | Answer |
|---|---|
| Default Config Server port | 8888 |
| Annotation on server | `@EnableConfigServer` |
| Annotation for runtime refresh | `@RefreshScope` |
| Config file naming | `{spring.application.name}-{profile}.yml` |
| Refresh without restart | `POST /actuator/refresh` |
| Bus refresh (all instances) | Spring Cloud Bus + Kafka/RabbitMQ |

**Common Interview Questions**

**Q: What happens if Config Server is down at startup?**  
A: Service fails to start. Use `spring.cloud.config.fail-fast=true` to fail immediately, or `spring.cloud.config.retry.*` for retry logic.

**Q: How to secure Config Server?**  
A: Add Spring Security to Config Server, use Basic Auth or OAuth2. Encrypt sensitive values using `{cipher}` prefix with symmetric/asymmetric keys.

**Q: Difference between `@RefreshScope` and restart?**  
A: `@RefreshScope` re-initializes only the annotated bean at runtime via `/actuator/refresh`. Restart reloads the entire application context.

**Q: How to push config changes to all instances at once?**  
A: Use **Spring Cloud Bus** with a message broker (Kafka/RabbitMQ). One `POST /actuator/busrefresh` call propagates to all instances.


## 14. How do microservices load configuration from a central source?

On startup, each microservice:

```
Microservice starts
    ↓
Reads bootstrap.yml → finds Config Server URL
    ↓
HTTP GET http://config-server:8888/employee-service/prod
    ↓
Config Server fetches from Git repo
    ↓
Returns merged config (common + service-specific + profile-specific)
    ↓
Microservice loads properties into Spring Environment
    ↓
@Value, @ConfigurationProperties beans are populated
```

For **live refresh** without restart:
- Add `@RefreshScope` on beans that need to reload
- Call `POST /actuator/refresh` on the microservice
- Or use Spring Cloud Bus (broadcasts refresh to all instances via RabbitMQ/Kafka)

## 12. How do you use `@ConfigurationProperties` in Spring Boot?

Binds a group of related properties to a POJO — cleaner than injecting one-by-one with `@Value`.

```yaml
# application.yml
app:
  name: MyApp
  timeout: 5000
  retry: 3
```

```java
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {
    private String name;
    private int timeout;
    private int retry;
    // getters + setters
}
```

Inject and use:
```java
@Autowired
private AppConfig appConfig;

appConfig.getTimeout(); // 5000
```


## 13. What is the use of prefix in configuration properties?

The `prefix` acts as a namespace — it maps only the matching keys from the config file to the POJO, avoiding conflicts.

```yaml
mail:
  host: smtp.gmail.com
  port: 587

db:
  host: localhost
  port: 5432
```

```java
@ConfigurationProperties(prefix = "mail")  // only binds mail.* keys
public class MailConfig {
    private String host;  // smtp.gmail.com
    private int port;     // 587
}

@ConfigurationProperties(prefix = "db")    // only binds db.* keys
public class DbConfig {
    private String host;  // localhost
    private int port;     // 5432
}
```

This avoids collision when multiple configs share the same field names like `host` or `port`.

## 14. How to remove default server from springboot application?
By default, Spring Boot comes with an embedded server like Apache Tomcat. If we want to remove it, we exclude the default starter dependency and optionally add another server like Jetty or Undertow.

```xml
// Remove Inbuild Server
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>

//Add New Server
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```


## 16. How can we configure multiple databases in Spring Boot?

Yes — you can configure **multiple databases in Spring Boot without using `application.properties` or XML** by using **Java-based configuration (pure @Configuration classes)**.


You define **DataSource, EntityManager, and TransactionManager manually in Java classes**.


**First Database Config**
```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.app.repo.db1",
    entityManagerFactoryRef = "db1EntityManager",
    transactionManagerRef = "db1TransactionManager"
)
public class DB1Config {

    @Bean
    public DataSource db1DataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db1DataSource());
        em.setPackagesToScan("com.app.entity.db1");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public PlatformTransactionManager db1TransactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(db1EntityManager().getObject());
        return tm;
    }
}
```

**Second Database Config**
```java
@Configuration
@EnableJpaRepositories(
    basePackages = "com.app.repo.db2",
    entityManagerFactoryRef = "db2EntityManager",
    transactionManagerRef = "db2TransactionManager"
)
public class DB2Config {

    @Bean
    public DataSource db2DataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db2");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db2DataSource());
        em.setPackagesToScan("com.app.entity.db2");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(db2EntityManager().getObject());
        return tm;
    }
}
```

## 17. How to secure username and password?

To secure username and password, we should **never store passwords in plain text**.
We store **hashed passwords** using algorithms like **BCrypt**.
We also store secrets in **environment variables or secure vaults**, not in code.
We use **HTTPS** to encrypt data in transit and **Spring Security** for authentication and authorization.


** Simple Example (Spring Boot – BCrypt)**

```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "admin123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);

        // Check password
        boolean isMatch = encoder.matches("admin123", encodedPassword);
        System.out.println("Password Match: " + isMatch);
    }
}
```


* `encode()` → Hash password
* `matches()` → Verify password
* Even if DB is hacked, real password is not visible


** Where to Store Passwords Securely**

| Location | How                                   |
| -------- | ------------------------------------- |
| Local    | Environment Variables                 |
| Cloud    | AWS Secrets Manager / Azure Key Vault |
| Database | Store Hashed Password Only            |
| Network  | Use HTTPS                             |


## 18. What happens if DB goes down?

- Active requests fail with a `DataAccessException` — handle it gracefully with proper error responses.
- Connection pool (HikariCP) keeps retrying to get a connection up to `connectionTimeout`.
- If the pool is exhausted, new requests fail fast.
- Use circuit breaker (Resilience4j) to stop hammering a dead DB.
- Health checks (`/actuator/health`) will show DB as DOWN.

## 19. How do you deploy the same code to multiple environments?

Build once, deploy everywhere — the jar doesn't change between environments.

- Externalize all environment-specific config (URLs, credentials, feature flags).
- Use Spring profiles activated via `SPRING_PROFILES_ACTIVE` env variable.
- In CI/CD (Jenkins, GitHub Actions), pass the profile and secrets as environment variables at deploy time.
- Use Docker: same image, different env vars per environment.

```bash
# Dev
docker run -e SPRING_PROFILES_ACTIVE=dev app.jar

# Prod
docker run -e SPRING_PROFILES_ACTIVE=prod -e DB_PASS=secret app.jar
```

## 20. Connecting and Using Multiple Databases with a Single Spring Boot Service?


**Why Use Multiple Databases?**

Connecting to multiple databases can be beneficial for several reasons:

1. **Multitenancy:** Support multiple tenants within the same application.
2. **Dynamic Environment Switching:** Connect to different environments (e.g., development, QA) dynamically.
3. **Data Seeding and Testing:** Simulate various testing scenarios by seeding data across multiple databases.
4. **Organization Support:** Handle multiple organizations within the same app, dynamically routing data based on user login.
5. **Batch Operations:** Run scripts and batch jobs against multiple databases simultaneously.


**Step 1: Add Dependencies**
Add the necessary dependencies to your pom.xml file for MySQL, PostgreSQL, and Spring Data JPA.

```xml
<dependencies>

    <!-- Spring Boot and JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    
    <!-- PostgreSQL Connector -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>

</dependencies>
```

**Step 2: Configure Data Sources**

In your application.properties file, configure the connection details for both MySQL and PostgreSQL.

```sql
# MySQL DataSource Configuration
spring.datasource.mysql.jdbc-url=jdbc:mysql://localhost:3306/mysqldb
spring.datasource.mysql.username=root
spring.datasource.mysql.password=password
spring.datasource.mysql.driver-class-name=com.mysql.cj.jdbc.Driver

# PostgreSQL DataSource Configuration
spring.datasource.postgresql.jdbc-url=jdbc:postgresql://localhost:5432/postgresdb
spring.datasource.postgresql.username=postgres
spring.datasource.postgresql.password=password
spring.datasource.postgresql.driver-class-name=org.postgresql.Driver
```

**Step 3: Create Data Source Configuration Classes**

Create a configuration class for each datasource. 

MySQL Data Source Configuration

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.repository.mysql",
    entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager"
)

public class MysqlDataSourceConfig {
    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mysqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.model.mysql")
                .persistenceUnit("mysql")
                .build();
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
```

PostgreSQL Data Source Configuration

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.repository.postgresql",
    entityManagerFactoryRef = "postgresqlEntityManagerFactory",
    transactionManagerRef = "postgresqlTransactionManager"
)

public class PostgresqlDataSourceConfig {
    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("postgresqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.model.postgresql")
                .persistenceUnit("postgresql")
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
```

**Step 4: Define Entities and Repositories**

MySQL Entity and Repository

```java
// MySQL Entity
@Entity
@Table(name = "mysql_entity")
public class MysqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and setters

}

// MySQL Repository
@Repository
public interface MysqlRepository extends JpaRepository<MysqlEntity, Long> {

}
```

PostgreSQL Entity and Repository

```java
// PostgreSQL Entity
@Entity
@Table(name = "postgresql_entity")
public class PostgresqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and setters

}

// PostgreSQL Repository
@Repository
public interface PostgresqlRepository extends JpaRepository<PostgresqlEntity, Long> {

}
```

**Step 5: Using the Repositories in a Service**

Create a service class to use the repositories.

```java
@Service
public class MultiDatabaseService {
    private final MysqlRepository mysqlRepository;
    private final PostgresqlRepository postgresqlRepository;

    @Autowired
    public MultiDatabaseService(MysqlRepository mysqlRepository, PostgresqlRepository postgresqlRepository) {
        this.mysqlRepository = mysqlRepository;
        this.postgresqlRepository = postgresqlRepository;
    }

    public List<MysqlEntity> getMysqlEntities() {
        return mysqlRepository.findAll();
    }

    public List<PostgresqlEntity> getPostgresqlEntities() {
        return postgresqlRepository.findAll();
    }

}
```

**Step 6: Expose Endpoints to Test**

Create a controller to expose REST endpoints for testing.

```java
@RestController
@RequestMapping("/api")
public class MultiDatabaseController {
    private final MultiDatabaseService multiDatabaseService;

    @Autowired
    public MultiDatabaseController(MultiDatabaseService multiDatabaseService) {
        this.multiDatabaseService = multiDatabaseService;
    }

    @GetMapping("/mysql")
    public List<MysqlEntity> getMysqlEntities() {
        return multiDatabaseService.getMysqlEntities();
    }

    @GetMapping("/postgresql")
    public List<PostgresqlEntity> getPostgresqlEntities() {
        return multiDatabaseService.getPostgresqlEntities();
    }

}
```

# ✅ 17. Java Design Patterns 

## 0. What are SOLID principles?

**SOLID** is a set of five object-oriented design principles that help developers write clean, maintainable, scalable, and loosely coupled code.

Following SOLID principles makes applications easier to understand, test, extend, and maintain.

- **S**ingle Responsibility: A class should have one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable for their base types
- **I**nterface Segregation: Many specific interfaces are better than one general interface
- **D**ependency Inversion: Depend on abstractions, not concrete implementations



**Example:**
**S — Single Responsibility Principle (SRP)**

One class should have only one responsibility.

use case:  

❌ Wrong:

```java
class OrderService {
    public void createOrder() {
        // create order
    }

    public void sendEmail() {
        // send email
    }
}
```

✅ Correct:

```java
class OrderService {
    public void createOrder() {
        // create order
    }
}

class EmailService {
    public void sendEmail() {
        // send email
    }
}
```


**O — Open/Closed Principle (OCP)**

Open for extension, closed for modification.

```java
// OCP: Open for extension, closed for modification

// This is the abstraction (interface)
// We can add new payment types without changing existing code
interface Payment {
    void pay(); // common method for all payment types
}

// Concrete implementation 1
class CardPayment implements Payment {
    public void pay() {
        // specific logic for card payment
        System.out.println("Paid by Card");
    }
}

// Concrete implementation 2
class UpiPayment implements Payment {
    public void pay() {
        // specific logic for UPI payment
        System.out.println("Paid by UPI");
    }
}

// This class uses the abstraction (Payment interface)
// It does NOT depend on concrete classes like CardPayment or UpiPayment
class PaymentService {

    // This method is CLOSED for modification
    // We don't need to change this method when new payment types are added
    public void processPayment(Payment payment) {
        // This is OPEN for extension because any new Payment type can be passed here
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {

        // Create service object
        PaymentService service = new PaymentService();

        // Using Card payment
        Payment p1 = new CardPayment();
        service.processPayment(p1);

        // Using UPI payment
        Payment p2 = new UpiPayment();
        service.processPayment(p2);

        // If we add a new payment type (e.g., WalletPayment),
        // we DO NOT modify PaymentService
        // We only create a new class implementing Payment
    }
}

// Output:
// Paid by Card
// Paid by UPI
```

Now we can add **NetBankingPayment** without changing existing code.


**L — Liskov Substitution Principle (LSP)**

Child class should replace parent class without breaking code.

```java
// LSP: Liskov Substitution Principle
// Objects of a child class should be able to replace objects of the parent class
// WITHOUT breaking the expected behavior of the program.

class Bird {

    // Base class method
    // Defines general behavior that all birds SHOULD follow
    public void fly() {
        System.out.println("Bird can fly");
    }
}

// Child class extending Bird
class Sparrow extends Bird {

    // Overriding the parent method
    // Behavior is consistent with parent (still "fly")
    @Override
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

public class Main {
    public static void main(String[] args) {

        // Parent object
        Bird b = new Bird();
        b.fly(); 
        // Output: Bird can fly

        // Child object
        Sparrow s = new Sparrow();
        s.fly(); 
        // Output: Sparrow can fly

        // LSP in action (Runtime Polymorphism)
        // Parent reference holding child object
        Bird b2 = new Sparrow();

        // This should NOT break behavior
        // Even though reference is Bird, actual object is Sparrow
        b2.fly(); 
        // Output: Sparrow can fly

        // ✔ This works perfectly → LSP is satisfied
    }
}

/*
Key Interview Points:

1. LSP means:
   Child class must be substitutable for parent class.

2. In this example:
   - Sparrow IS-A Bird
   - Sparrow does not change expected behavior
   - So it follows LSP

3. Why this is correct:
   - No exception thrown
   - No unexpected behavior
   - Same logical meaning of "fly"

4. When LSP is violated:
   Example: If we create Penguin extends Bird but override fly() to throw exception
   → That breaks LSP because Penguin cannot truly replace Bird

5. Real-world idea:
   If parent says "can fly", child must honor that contract
*/


// Output:
Bird can fly
Sparrow can fly
Sparrow can fly
```

Bad example: Penguin cannot fly → violates LSP.


**I — Interface Segregation Principle (ISP)**

Create small interfaces.

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human working");
    }

    public void eat() {
        System.out.println("Human eating");
    }
}

class Robot implements Workable {
    public void work() {
        System.out.println("Robot working");
    }
}

public class Main {
    public static void main(String[] args) {
        Workable w1 = new Human();
        w1.work();

        Eatable e1 = new Human();
        e1.eat();

        Workable w2 = new Robot();
        w2.work();
    }
}

// Output:
Human working
Human eating
Robot working
```

Robot does not implement eat() → Correct.


**D — Dependency Inversion Principle (DIP)**

Depend on abstraction, not concrete class.

```java
// DIP: Depend on abstraction, not concrete implementation

// Abstraction
interface Payment {
    void pay();
}

// Concrete implementation
class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

// High-level module (business logic)
class OrderService {

    // Depends on abstraction, not CardPayment directly
    private Payment payment;

    // Dependency is injected via constructor
    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() {
        payment.pay(); // uses abstraction
    }
}

public class Main {
    public static void main(String[] args) {

        // Inject concrete implementation at runtime
        Payment payment = new CardPayment();

        // OrderService is not tightly coupled to CardPayment
        OrderService orderService = new OrderService(payment);

        orderService.placeOrder(); // Output: Card payment
    }
}

/*
Interview Points (Quick):

- High-level class (OrderService) should NOT depend on low-level class (CardPayment)
- Both depend on abstraction (Payment)
- Easy to switch implementation (UPI, Wallet, etc.) without changing OrderService
*/

//Output:
Card payment
```

## 1. What are design patterns?

Design patterns are proven reusable solutions to common software design problems, categorized into Creational, Structural, and Behavioral patterns.

1️⃣ **Creational Design Patterns**
* **Singleton** – Only one instance of a class is created.
* **Factory Method** – Creates objects without exposing creation logic.
* **Abstract Factory** – Creates families of related objects.
* **Builder** – Builds complex objects step by step.
* **Prototype** – Creates object by cloning existing object.

2️⃣ **Structural Design Patterns**
* **Adapter** – Converts one interface into another.
* **Bridge** – Separates abstraction from implementation.
* **Decorator** – Adds behavior dynamically.
* **Facade** – Provides simplified interface to complex system.
* **Proxy** – Controls access to an object.

3️⃣ **Behavioral Design Patterns**
* **Observer** – One-to-many dependency (used in event systems).
* **Strategy** – Select algorithm at runtime.
* **Command** – Encapsulates a request as an object.
* **State** – Changes behavior when state changes.
* **Template Method** – Defines skeleton of algorithm.
* **Iterator** – Sequential access to collection.

**Most Commonly Asked in Interviews**
* **Singleton** – Ensures that a class has only one instance and provides a global access point to it.
* **Factory** – Creates objects without exposing the object creation logic to the client.
* **Builder** – Constructs complex objects step by step, allowing flexible object creation.
* **Observer** – Defines a one-to-many relationship where multiple objects are notified automatically when one object changes state.
* **Strategy** – Allows selecting an algorithm’s behavior at runtime by encapsulating different algorithms in separate classes.
* **Decorator** – Adds new functionality to an object dynamically without modifying its existing code.
* **Prototype** –  Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.

## 2. What is Singleton pattern?

**Singleton Pattern** is a creational Design Patterns design pattern that ensures a **class has only one object (instance)** and provides a **global access point** to that instance.

**The default scope** for a Spring Bean in Spring Boot is the singleton scope

When to use:

* **Database Connection Manager** – one shared connection pool for the whole app
* **Logger Service** – single logging instance used everywhere
* **Configuration Manager** – load config once and reuse globally
* **Cache Manager** – one shared cache for fast data access

Singleton is used where only one shared instance is needed across the application.

```java
class Singleton {

    private static Singleton instance;

    private Singleton() { }   // private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
    }
}
```

## 3. How do you implement thread-safe Singleton?

**Thread-safe Singleton** can be implemented using synchronization, double-checked locking, or enum approach to prevent multiple instances in multithreaded environments.

**Methods:**
- Synchronized method (simple but slow)
- Double-checked locking (efficient)
- Enum singleton (best approach)
- Static inner class (lazy loading)

```java
// Double-checked locking
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;    
    private ThreadSafeSingleton() { }
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

// Enum singleton - best approach
public enum EnumSingleton {
    INSTANCE;    
    public void doSomething() { }
}
```

## 4. What is Factory pattern?

**Factory Pattern** is a creational design pattern used to: Create objects without exposing object creation logic to the client.

Instead of creating objects directly using **new**, the client asks the factory to create the required object.

**Advantages**
- Loose coupling
- Hides object creation logic
- Easy maintenance
- Easy to extend

👉 Real use:
- Payment systems
- Notification services
- Logger creation
- Database drivers
- Spring BeanFactory

```java
enum PaymentType {
    CARD, UPI
}

// Step 1: Interface
interface Payment {
    void pay();
}

// Step 2: Implementations
class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI payment");
    }
}

// Step 3: Factory Class
class PaymentFactory {
    public static Payment getPayment(PaymentType type) {
        switch (type) {
            case CARD:
                return new CardPayment();

            case UPI:
                return new UpiPayment();

            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}

// Step 4: Main Class
class FactoryPatternDemo {
    public static void main(String[] args) {
        Payment payment = PaymentFactory.getPayment(PaymentType.UPI);
        payment.pay();
    }
}
```

## 5. What is Observer pattern?

**Observer pattern** (Behavioral Design Patterns) is defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

👉 Real use:
- YouTube Notifications
- News Channel System, 
- Stock Market Apps
- Kafka / RabbitMQ Consumers


```java
// Step 1: Observer Interface
interface Observer {
    void update(String message);
}

// Step 2: Concrete Observer
class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    public void update(String news) {
        System.out.println(name + " received: " + news);
    }
}

// Step 3: Subject
class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    private void notifyObservers() {
        observers.forEach(observer -> observer.update(news));
    }
}

// Step 4: Main Class
class ObserverPatternExp {
    public static void main(String[] args) {

        NewsAgency agency = new NewsAgency();

        // Create observers
        Observer channel1 = new NewsChannel("CNN");
        Observer channel2 = new NewsChannel("BBC");

        // Register observers
        agency.addObserver(channel1);
        agency.addObserver(channel2);

        // Publish news
        agency.setNews("Java is awesome!");
        agency.setNews("Observer pattern in action!");
    }
}

// Output
CNN received: Java is awesome!
BBC received: Java is awesome!
CNN received: Observer pattern in action!
BBC received: Observer pattern in action!
```

## 6. What is Strategy pattern?

**Strategy pattern** (Behavioral Design Patterns) defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

Use Case: 

```java
// Strategy Interface
interface NotificationStrategy {
    void send(String message);
}

// Concrete Strategy 1
class EmailNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

// Concrete Strategy 2
class SMSNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Concrete Strategy 3
class PushNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH Notification: " + message);
    }
}

// Context Class
class NotificationService {
    private NotificationStrategy strategy;

    // Set strategy dynamically
    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    // Execute selected strategy
    public void notifyUser(String message) {
        strategy.send(message);
    }
}
```

## 7. What is Adapter pattern?

**Adapter pattern** allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.

```java
// Target interface (what client expects)
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee (existing incompatible interface)
class AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String audioType) {
        advancedPlayer = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);

        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);

        } else {
            System.out.println("Invalid media type");
        }
    }
}

// Main class
class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer vlcPlayer = new MediaAdapter("vlc");
        vlcPlayer.play("vlc", "movie.vlc");

        MediaPlayer mp4Player = new MediaAdapter("mp4");
        mp4Player.play("mp4", "video.mp4");
    }
}
// Output::
// Playing vlc file: movie.vlc
// Playing mp4 file: video.mp4
```

## 8. What is Decorator pattern?

**Decorator pattern** allows behavior to be added to objects dynamically without altering their structure. It provides a flexible alternative to subclassing for extending functionality.

When to use:
* Adding features to objects at runtime

```java
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple coffee"; }
    public double getCost() { return 2.0; }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    
    public String getDescription() {
        return coffee.getDescription() + ", milk";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    
    public String getDescription() {
        return coffee.getDescription() + ", sugar";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}

// Usage
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
// Result: "Simple coffee, milk, sugar" - $2.7
```

## 8. What is Builder pattern?

Builder Pattern(Creational Design Patterns) is  is used to create complex objects step by step, especially when an object has many optional parameters.

**Real time use case**

- API Request Objects, Complex DTO / Response Objects, Lombok @Builder, etc.

```java
// ❌ Problem Without Builder
// Employee e = new Employee(1, "Rahul", 25, "Bangalore", "Developer");

// 👉 Hard to read
// 👉 Constructor becomes huge

class Employee {

    private int id;
    private String name;

    // private constructor
    private Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String display() {
        return "Employee{id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // Builder class
    static class EmployeeBuilder {

        private int id;
        private String name;

        public EmployeeBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}

// Main Class
class BuilderPatternDemo {
    public static void main(String[] args) {
        Employee emp = new Employee.EmployeeBuilder()
                .setId(1)
                .setName("Rahul")
                .build();

        System.out.println(emp.display());
    }
}
```

## 8. What is Prototype pattern?

**Prototype Pattern** is a **Creational Design Pattern** used to create **new objects by copying (cloning) an existing object**, instead of creating a new object from scratch.

This is useful when **object creation is costly** (e.g., DB call, API call, complex object creation).


**Real-Time Example**

* Employee object from database
* Instead of loading from DB again → clone existing employee object


**Prototype Pattern Example in Java**
```java
class Student implements Cloneable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Student s1 = new Student(1, "John");
        Student s2 = (Student) s1.clone();

        System.out.println(s1.name);
        System.out.println(s2.name);
    }
}
```

**Where Used in Real Projects**

* Spring Framework (Prototype Bean scope)
* Hibernate (entity cloning)
* Game development (duplicate characters)
* Caching objects
* Creating multiple similar objects


**Difference between Shallow Copy vs Deep Copy**

| Shallow Copy        | Deep Copy             |
| ------------------- | --------------------- |
| Copies reference    | Copies actual object  |
| Changes affect both | Changes do not affect |
| `super.clone()`     | Manual clone          |



# ✅ 18. Java Spring Framework 

## 1. What is Spring Framework?

**Spring Framework** is a **comprehensive Java framework** for building enterprise applications.

It provides **infrastructure support**, uses **IoC and Dependency Injection**, has **modular architecture** (Core, MVC, Data, Security), and **simplifies Java EE development** with POJOs.

Spring makes Java development easier by handling common tasks and promoting best practices like loose coupling and testability.

```java
// pom.xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.1.5</version>
    </dependency>
</dependencies>
```

## 2. What are the features of Spring?

* **IoC Container**: Manages object lifecycle and dependencies
* **Dependency Injection**: Automatic wiring of dependencies
* **AOP Support**: Cross-cutting concerns like logging, security
* **MVC Framework**: Web application development
* **Transaction Management**: Declarative transaction support
* **Integration**: Easy integration with other frameworks and technologies

```java
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
}
```

## 3. What is Inversion of Control (IoC)?

**Inversion of Control (IoC)** means the **control of object creation and dependency management is given to the framework instead of the developer**.

Normally, we create objects using `new`.
But in **IoC (like in Spring)**, the **framework creates the objects and gives them to our class**.

```java
// Without IoC
Service service = new Service();

// With IoC (Spring)
@Autowired
Service service;
```

## 4. What is Dependency Injection?

**Dependency Injection (DI)** is a design pattern where an object’s dependencies are provided by another object/container instead of the object creating them itself.

👉 This makes code:  **Loosely coupled**,  **Easy to test**, **Easy to maintain**

**There are 3 main types of DI:**
1. Constructor Injection – dependencies injected through constructor (recommended)
2. Setter Injection – dependencies injected through setter method
3. Field Injection – dependencies injected directly into field using @Autowired

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

// Instead of :
Engine engine = new Engine(); // Spring does: tightly coupled
Car car = new Car(engine);    // Spring does: injected
```

**1. Constructor Injection (Recommended)**

```java
@Component
class Car {
    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
👉 Best because: Mandatory dependency, Immutable (using final), Easy to test
Reason To use: 
```
Reason to use: 


**2. Setter Injection**

```java
@Autowired
public void setEngine(Engine engine) {
    this.engine = engine;
}
👉 Used when dependency is optional

```


**3. Field Injection**

```java
@Autowired
private Engine engine;
👉 Avoid because: Hard to test, Not immutable, Hidden dependency
```

**If I inject the same bean multiple times using Dependency Injection, will it create multiple instances every time?** 

No, injecting the same bean multiple times using Dependency Injection does not create multiple instances every time because Spring beans are singleton by default. The same instance is reused from the IOC container. Multiple instances are created only if the bean scope is configured as prototype.



## 5. What is a Spring Container?

A **Spring Container** is the **core part of the Spring Framework that creates, manages, and controls the lifecycle of objects called Beans**.

It is responsible for:
* **Creating objects (Beans)**
* **Injecting dependencies**
* **Managing bean lifecycle**

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
UserService service = context.getBean(UserService.class);
```
Here the **Spring Container creates the `UserService` object and provides it when needed**.

**Types of Spring Container** 

1. **BeanFactory** – Basic container
2. **ApplicationContext** – Advanced container (most commonly used)


## 6. What is BeanFactory vs ApplicationContext?

**BeanFactory :** is the **basic IoC container in Spring** that creates and manages beans and performs **dependency injection**.
It uses **lazy initialization**, so beans are created **only when requested**.

**ApplicationContext :** is a **Spring container** that manages the lifecycle of Spring beans. It loads configuration, creates objects, injects dependencies, and provides advanced features like **event handling, internationalization, and AOP**. 

**Using BeanFactory (Lazy Loading)**
```java
Resource resource = new ClassPathResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

UserService service = (UserService) factory.getBean("userService");
service.print();
```

**Using ApplicationContext (Eager Loading)**
```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("beans.xml");

UserService service = context.getBean(UserService.class);
service.print();
```

## 9. What is AOP in Spring?


AOP is a programming paradigm that separates **cross-cutting concerns** from your core business logic. Cross-cutting concerns are things that affect multiple parts of your app but aren't part of the core logic — like logging, security, transactions, etc.

**The Problem AOP Solves:**
```java
// WITHOUT AOP — same boilerplate repeated everywhere
public class OrderService {
    public void placeOrder(Order order) {
        log.info("Method started");          // 😫 repeated
        checkSecurity();                     // 😫 repeated
        startTransaction();                  // 😫 repeated

        // actual business logic (3 lines)
        validateOrder(order);
        saveOrder(order);
        notifyUser(order);

        commitTransaction();                 // 😫 repeated
        log.info("Method ended");            // 😫 repeated
    }
}

// WITH AOP — clean business logic only
public class OrderService {
    public void placeOrder(Order order) {
        validateOrder(order);   // ✅ pure business logic
        saveOrder(order);
        notifyUser(order);
    }
}
// Logging, security, transactions handled automatically by aspects
```


**Core AOP Concepts**

**1. 🎯 Aspect**
A class that contains the cross-cutting logic (e.g., logging, security).
```java
@Aspect
@Component
public class LoggingAspect {
    // cross-cutting logic lives here
}
```

**2. 📍 Pointcut**
An expression that defines **where** the aspect should apply (which methods/classes).
```java
// Apply to all methods in service package
@Pointcut("execution(* com.app.service.*.*(..))")
public void serviceLayer() {}

// Apply to specific annotation
@Pointcut("@annotation(com.app.Loggable)")
public void loggableMethods() {}
```

**3. 💡 Advice**
**When** and **what** to execute — the actual logic to run at the join point.

| Advice Type | When it runs |
|---|---|
| `@Before` | Before the method executes |
| `@After` | After method (always, like finally) |
| `@AfterReturning` | After method returns successfully |
| `@AfterThrowing` | After method throws an exception |
| `@Around` | Before AND after (full control) |

**4. 🔗 Join Point**
The actual point of execution — in Spring AOP, always a **method execution**.
```java
@Before("serviceLayer()")
public void log(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName(); // the join point
}
```

**5. 🔀 Weaving**
The process of applying aspects to target objects. Spring does this at **runtime** using proxies.


**Types of Advice with Examples**

**@Before**
```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.app.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Calling: " + joinPoint.getSignature().getName());
        System.out.println("Args: " + Arrays.toString(joinPoint.getArgs()));
    }
}
```

**@AfterReturning**
```java
@AfterReturning(
    pointcut = "execution(* com.app.service.*.*(..))",
    returning = "result"
)
public void logAfterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("Method returned: " + result);
}
```

**@AfterThrowing**
```java
@AfterThrowing(
    pointcut = "execution(* com.app.service.*.*(..))",
    throwing = "ex"
)
public void logException(JoinPoint joinPoint, Exception ex) {
    System.out.println("Exception in: " + joinPoint.getSignature().getName());
    System.out.println("Exception: " + ex.getMessage());
}
```

**@Around (Most Powerful)**
```java
@Around("execution(* com.app.service.*.*(..))")
public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();

    Object result = joinPoint.proceed(); // 👈 actually calls the method

    long duration = System.currentTimeMillis() - start;
    System.out.println(joinPoint.getSignature().getName() + " took " + duration + "ms");

    return result;
}
```


**Real-World Use Cases**

**✅ 1. Logging**
```java
@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(Loggable)")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        log.info(">> {} started", pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("<< {} completed", pjp.getSignature().getName());
        return result;
    }
}
```

**✅ 2. Security Check**
```java
@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(secured)")
    public void checkRole(JoinPoint jp, Secured secured) {
        String requiredRole = secured.role();
        if (!currentUser.hasRole(requiredRole)) {
            throw new AccessDeniedException("Access denied!");
        }
    }
}
```

**✅ 3. Transaction Management (Spring does this internally)**
```java
// @Transactional is itself implemented as an AOP Aspect internally
@Transactional
public void transferMoney(Account from, Account to, double amount) {
    from.debit(amount);
    to.credit(amount);
}
```

**✅ 4. Performance Monitoring**
```java
@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.app.service.*.*(..))")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long elapsed = System.nanoTime() - start;

        if (elapsed > 1_000_000_000L) { // > 1 second
            log.warn("SLOW METHOD: {} took {}ms",
                pjp.getSignature(), elapsed / 1_000_000);
        }
        return result;
    }
}
```


**How Spring AOP Works Internally (Proxy Pattern)**

```
Your Code calls → Spring Proxy (AOP magic) → Real Bean

┌─────────────┐       ┌──────────────────────┐       ┌─────────────┐
│   Caller    │ ────▶ │   Spring Proxy       │ ────▶ │  Real Bean  │
│             │       │  1. Run @Before      │       │             │
│ orderSvc    │       │  2. Call real method │       │ placeOrder()│
│ .placeOrder │       │  3. Run @After       │       │             │
└─────────────┘       └──────────────────────┘       └─────────────┘
```

Spring creates a **proxy** around your bean and intercepts method calls to apply aspects.


**Setup in Spring Boot**

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

```java
// Enable AOP (auto-enabled in Spring Boot)
@SpringBootApplication
@EnableAspectJAutoProxy  // optional — Spring Boot enables this automatically
public class MyApp { }
```


**AOP vs OOP**

| | OOP | AOP |
|---|---|---|
| **Solves** | Core business logic | Cross-cutting concerns |
| **Unit** | Class / Object | Aspect |
| **Separation** | By objects/classes | By concerns |
| **Examples** | Order, User, Product | Logging, Security, Transactions |



## 10. What is Spring Data JPA?

**Spring Data JPA** is a Spring module that **simplifies JPA-based data access**.

It provides **repository abstraction**, **auto-implements methods from names**, supports **query methods, JPQL, and native SQL**, and **reduces boilerplate code**.

* **Auto-implementation**: Creates implementation from method names
* **Query Methods**: Derive queries from method names
* **Custom Queries**: Support for JPQL and native SQL

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
    List<User> findByAgeGreaterThan(int age);
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
```

## 11. What is Spring Security?

**Spring Security** is a **Java security framework** that handles **authentication** (user identity) and **authorization** (access control).

It provides **protection** against CSRF, session fixation, clickjacking, integrates with multiple authentication providers, and supports **annotation- and configuration-based security**.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin()
                .and()
                .build();
    }
}
```

## 12. What is Spring Cloud? 

Spring Cloud is a framework that helps developers build and manage microservices easily. It provides ready-made solutions for common problems like service discovery, load balancing, configuration management, and fault tolerance.

* **Service Discovery**: Eureka, Consul integration
* **Circuit Breaker**: Hystrix for fault tolerance
* **API Gateway**: Zuul, Spring Cloud Gateway
* **Configuration Management**: Centralized configuration
* **Load Balancing**: Client-side load balancing

```java
@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

## 13. What is Spring WebFlux?

**Spring WebFlux** is a **reactive, non-blocking web framework** for building high-performance applications.

It's an alternative to Spring MVC, uses **Reactive Streams** (Project Reactor), supports **functional routing**, and handles **more concurrent requests with fewer threads**.

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userService.findAll(); // Returns Flux<User>
    }

    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.findById(id); // Returns Mono<User>
    }
}
```

## 14. How Does Spring Handle Circular Dependency?

A circular dependency is when Bean A depends on Bean B, and Bean B depends on Bean A.

**With constructor injection** — Spring throws `BeanCurrentlyInCreationException` because it can't create either bean first.

**With field/setter injection** — Spring can handle it using a 3-level cache (early bean references). It creates a partially initialized bean and injects it.

```java
// Avoid this with constructor injection — causes error
@Component
public class A {
    @Autowired B b;
}

@Component
public class B {
    @Autowired A a;
}
```

**Best fix:** Refactor to remove the circular dependency. If you must keep it, use `@Lazy` on one of the injections:

```java
@Autowired
@Lazy
private B b;
```

`@Lazy` tells Spring to inject a proxy first and resolve the real bean later.

In Spring Boot 2.6+, circular dependencies are disabled by default. You need to explicitly enable them or fix the design.

## 15. Spring Boot 2.7 vs Spring Boot 3.0

| Feature              | Spring Boot 2.7     | Spring Boot 3.0            |
| -------------------- | ------------------- | -------------------------- |
| Java Version         | Java 8, 11, 17      | Java 17+ only              |
| Spring Framework     | Spring 5.3          | Spring 6.0                 |
| Namespace            | `javax.*`           | `jakarta.*`                |
| Native Image Support | Limited             | Built-in GraalVM support   |
| Observability        | Micrometer only     | Micrometer + OpenTelemetry |
| Security             | Spring Security 5   | Spring Security 6          |
| Hibernate            | Hibernate 5         | Hibernate 6                |
| Servlet API          | Servlet 4.0         | Servlet 6.0                |
| Validation API       | `javax.validation`  | `jakarta.validation`       |
| JPA API              | `javax.persistence` | `jakarta.persistence`      |



## 16. Real Industry Practice?

Here are strong **real industry practice tables** you can use in final-round interviews for deep project discussions.


**Microservices Architecture**

| Component         | Local Development    | Cloud / Production                 |
| ----------------- | -------------------- | ---------------------------------- |
| Service Discovery | Eureka               | Kubernetes Service Discovery       |
| API Gateway       | Spring Cloud Gateway | Kong / Nginx Ingress / API Gateway |
| Config Management | Spring Config Server | ConfigMap + Secrets                |
| Load Balancer     | Local Nginx          | Kubernetes Ingress / AWS ALB       |
| Communication     | REST                 | REST + gRPC + Event Driven         |
| Authentication    | Local JWT            | OAuth2 + Keycloak/Auth0            |
| Circuit Breaker   | Resilience4j         | Resilience4j + Service Mesh        |
| Deployment        | Docker Compose       | Kubernetes                         |
| Containerization  | Docker               | Docker + Kubernetes                |
| Orchestration     | Manual               | Kubernetes                         |
| Monitoring        | Prometheus + Grafana | Prometheus + Grafana + ELK         |
| Logging           | Console Logs         | ELK / Loki / Splunk                |
| Tracing           | Basic Logs           | Zipkin / Jaeger                    |
| CI/CD             | Jenkins Local        | Jenkins/GitHub Actions + ArgoCD    |


**Database Handling**

| Component         | Local Development     | Cloud / Production           |
| ----------------- | --------------------- | ---------------------------- |
| Database          | MySQL/Postgres Docker | RDS / Cloud SQL              |
| Connection Pool   | HikariCP              | Optimized HikariCP           |
| Migration         | Flyway Local          | Flyway CI/CD Pipeline        |
| Read Scaling      | Single DB             | Read Replicas                |
| Backup            | Manual Dump           | Automated Snapshots          |
| High Availability | Not Required          | Multi-AZ Deployment          |
| Sharding          | Rare                  | Used for large-scale systems |
| Transactions      | Local Transactions    | Distributed/Saga Pattern     |


**Cache**

| Component       | Local Development | Cloud / Production |
| --------------- | ----------------- | ------------------ |
| Cache           | Local Redis       | Redis Cluster      |
| Session Storage | In-Memory         | Distributed Redis  |
| Token Cache     | Local Memory      | Redis              |
| Eviction Policy | Default           | LRU/LFU Tuned      |
| Scaling         | Single Node       | Clustered Redis    |
| HA              | Not Needed        | Sentinel/Cluster   |


**Messaging / Event Driven**

| Component           | Local Development     | Cloud / Production        |
| ------------------- | --------------------- | ------------------------- |
| Queue               | RabbitMQ/Kafka Docker | Kafka Cluster             |
| Retry               | Basic Retry           | DLQ + Retry Topics        |
| Ordering            | Single Partition      | Multi-partition Strategy  |
| Event Storage       | Temporary             | Persistent                |
| Consumer Scaling    | Single Consumer       | Consumer Groups           |
| Async Communication | Basic                 | Event-Driven Architecture |
| Cloud Native Queue  | Rare                  | AWS SQS/SNS               |


**Security**

| Component       | Local Development | Cloud / Production          |
| --------------- | ----------------- | --------------------------- |
| Authentication  | Basic JWT         | OAuth2/OpenID Connect       |
| Authorization   | Role Based        | RBAC + IAM                  |
| Secrets         | application.yml   | Vault / AWS Secrets Manager |
| HTTPS           | Optional          | Mandatory                   |
| API Security    | Simple Filters    | WAF + Rate Limiting         |
| User Management | Local DB          | Keycloak/Auth0/Cognito      |


**Deployment**

| Component             | Local Development | Cloud / Production |
| --------------------- | ----------------- | ------------------ |
| Deployment            | Manual Run        | CI/CD Pipeline     |
| Build Tool            | Maven/Gradle      | Maven + Jenkins    |
| Container             | Docker            | Docker             |
| Registry              | Local Images      | Docker Hub/ECR     |
| Infra                 | Local Machine     | Kubernetes/AWS     |
| Auto Scaling          | No                | HPA                |
| Rollback              | Manual            | Automated Rollback |
| Blue Green Deployment | Rare              | Common             |
| Canary Deployment     | Rare              | Common             |


**Monitoring & Observability**

| Component    | Local Development | Cloud / Production          |
| ------------ | ----------------- | --------------------------- |
| Metrics      | Actuator          | Prometheus                  |
| Dashboard    | Grafana           | Grafana                     |
| Logging      | Console           | ELK/Loki                    |
| Alerting     | Minimal           | PagerDuty/Slack Alerts      |
| Tracing      | Basic Logs        | Jaeger/Zipkin               |
| Health Check | /actuator/health  | Liveness + Readiness Probes |


**High Availability & Scalability**

| Component         | Local Development | Cloud / Production    |
| ----------------- | ----------------- | --------------------- |
| Scaling           | Single Instance   | Auto Scaling          |
| Failover          | Manual Restart    | Automatic Recovery    |
| Load Balancing    | Simple Nginx      | Ingress + ALB         |
| Redundancy        | Minimal           | Multi-node            |
| Disaster Recovery | Rare              | Multi-region Strategy |
| Zero Downtime     | Not Needed        | Rolling Updates       |



In local development we use lightweight containerized setups mainly for development convenience and debugging.

In production, we move toward cloud-native and distributed architecture. Kubernetes handles orchestration and service discovery, managed Redis and Kafka are used for scalability and fault tolerance, ConfigMaps and Secrets handle configuration securely, and monitoring/logging are centralized using Prometheus, Grafana, and ELK stack.


**Strong Senior-Level Keywords**

* Cloud-native architecture
* Distributed systems
* Fault tolerance
* High availability
* Horizontal scaling
* Stateless services
* Event-driven architecture
* Container orchestration
* Observability
* Resilience
* Auto-scaling
* Centralized logging
* Service mesh
* Distributed tracing




# ✅ 19. Java Spring Boot 

## 1. What is annotations in Java?
**An annotation** is a special type of metadata in Java that provides additional information about classes, methods, or variables to the compiler or framework.

**Example Annotations:**
* `@Override`
* `@Autowired`
* `@Component`
* `@Service`

## 2. What is Spring Boot and How does it Works Internally(Lifecycle)?

**Spring Boot** is a framework built on top of Spring that simplifies application development. It provides **auto-configuration**, **embedded servers**, and **starter dependencies**, allowing developers to build production-ready applications quickly with minimal configuration.

**Design patterns:**
Spring Boot mainly uses **MVC, Dependency Injection, Singleton, Factory, and DAO** design patterns.

**Design Principle:**
Spring Boot follows principles like Convention over **Configuration, Dependency Injection, Auto-Configuration, and Standalone** Applications.


**How does it Works Internally:**

1. **Application Starts :** The `main()` method calls **`SpringApplication.run()`** to start the application.
2. **Auto Configuration** Spring Boot automatically configures beans based on dependencies using **`@EnableAutoConfiguration`**.
3. **Component Scanning :** It scans packages for classes annotated with **`@Component`**, **`@Service`**, **`@Repository`**, and **`@Controller`**.
4. **Bean Creation (IoC Container) :** Spring creates and manages objects (beans) inside the **Spring IoC container**.
5. **Embedded Server Starts :** Spring Boot starts an embedded server like **Apache Tomcat**, **Jetty**, or **Undertow**.
6. **Application Ready :** The application is ready to handle HTTP requests.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Combines three annotations
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// Equivalent to:
@Configuration
@EnableAutoConfiguration  
@ComponentScan
public class MyApplication { }
```


## 3. Spring Boot Flow Architecture works?

Spring Boot follows a **layered architecture** where a request flows through different layers:

**Flow:**

**Client → DispatcherServlet → Controller → Service → Repository → Database → Response**

**Short Explanation:**

1. **Client** – Sends HTTP request (browser/Postman).
2. **DispatcherServlet** (from Spring MVC) – Receives the request and routes it.
3. **Controller** – Handles the API request.
4. **Service** – Contains business logic.
5. **Repository/DAO** – Interacts with the database using Spring Data JPA.
6. **Database** – Stores and retrieves data.
7. **Response** – Data returns back to the client.


## 4. What is Java Bean, @Component and @Bean?

**@Component** Used directly on a class. It is a generic annotation and Spring automatically creates and manages the object using component scanning.

**@Bean** Used on a method inside a @Configuration class. It is an object that is created, managed, and stored by the Spring IoC container. Instead of creating objects using `new`, Spring creates and injects them automatically.


A **Java Bean** is a simple Java class with private fields, getters/setters, and a no-argument constructor, and it is created manually using the `new` keyword.

```java
// Example of Java Beans
public class User {
    private String name;
    public User() {}   // no-arg constructor
    public String getName() { return name;}
    public void setName(String name) {this.name = name;}
}

User user = new User();
user.setName("John");
```

**Example @Component and @Bean**
```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

// @Component Bean ------------
@Component
class UserService {

    public void createUser() {
        System.out.println("User Created");
    }
}

// @Bean ----------------------
// Service Interface
interface OrderService {
    void process(String item);
}

// First Implementation
class OnlineOrderServiceImpl implements OrderService {
    @Override
    public void process(String item) {
        System.out.println("Online Order Processing: " + item);
    }
}

// Second Implementation
class OfflineOrderServiceImpl implements OrderService {
    @Override
    public void process(String item) {
        System.out.println("Offline Order Processing: " + item);
    }
}


// Configuration Class
@Configuration
class AppConfig {
    // Bean name = buyItem
    @Bean
    public OrderService buyItem() {
        return new OnlineOrderServiceImpl();
    }

    // Bean name = shopItem
    @Bean
    public OrderService shopItem() {
        return new OfflineOrderServiceImpl();
    }
}


// Both Bean used in Controller -----
@RestController
@RequestMapping("/orders")
class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    // Constructor Injection
    public OrderController(
            @Qualifier("buyItem")
            OrderService orderService,
            UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/{item}")
    public String createOrder(@PathVariable String item) {
        userService.createUser();
        orderService.process(item);
        return "Order received for " + item;
    }
}
```

**`@Bean` vs `@Component` (Quick Difference)**

| Feature                      | `@Component` | `@Bean`  |
| ---------------------------- | ------------ | -------- |
| Applied On                   | Class        | Method   |
| Bean Creation                | Automatic    | Manual   |
| Scanning Needed              | Yes          | No       |
| Control Over Object Creation | Less         | More     |
| Third-party Classes          | Not possible | Possible |
| Configuration Logic          | Limited      | Flexible |



## 5. Explain Bean Lifecycle?

The **bean lifecycle** describes the steps a bean goes through from **creation to destruction** inside the Spring **IoC container**.

**Steps in Bean Lifecycle**

1. **Bean Instantiated:** Spring creates the bean object.
2. **Dependency Injection:** Required dependencies are injected using `@Autowired`.
3. **Bean Initialization:** Initialization methods run using `@PostConstruct`.
4. **Bean Ready for Use:** The bean is now fully initialized and used by the application.
5. **Bean Destruction:** When the application shuts down, cleanup happens using `@PreDestroy`.

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public MyBean() {
        System.out.println("Bean Created");
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean Initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean Destroyed");
    }
}
```


## 6. How can you disable specific auto-configurations in Spring Boot?

Use `@SpringBootApplication(exclude)` or `spring.autoconfigure.exclude` property to disable unwanted auto-configurations.

```java
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    SecurityAutoConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

```properties
# application.properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

## 7. What is @SpringBootApplication annotation?

@SpringBootApplication is a convenience annotation that combines three commonly used annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan.

```java
@SpringBootApplication // Combines three annotations
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// Equivalent to:
@Configuration
@EnableAutoConfiguration  
@ComponentScan
public class MyApplication { }
```

It's the standard annotation for Spring Boot main classes and enables all essential Spring Boot features.

## 8.  @Controller, @RestController, @Service, @Repository annotations?


**Simple Flow (Easy to Remember)**

```text
Controller / RestController → Service → Repository → Database
```

**@Controller (Returns View)r** is used to handle web requests and return views (like JSP/HTML).

```java
@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("user", service.getUser());
        return "home"; // returns HTML/JSP page
    }
}
```

**@RestController (Returns JSON)** is used to build REST APIs and returns JSON/XML data instead of views.
```java
@RestController
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping("/api/user")
    public String getUser() {
        return service.getUser(); // returns JSON/text
    }
}
`@RestController` = `@Controller` + `@ResponseBody`
```


**@Service** is used for business logic layer.

```java
@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public String getUser() {
        return repo.getUser();
    }
}
```


**@Repository** is used for database/DAO layer and provides exception handling.

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findAll(Pageable pageable);
}
```

## 9. What is @Autowired vs @Inject annotation?

**`@Autowired`** is an annotation in **Spring Framework** that enables **automatic dependency injection (DI)**.
It tells the Spring container to automatically inject a required bean into a class.

```java
// Using @Autowired (Spring)
@Service
public class OrderService {
    
    @Autowired
    private PaymentService paymentService; // Field injection
    
    private UserService userService;
    
    @Autowired
    public OrderService(UserService userService) { // Constructor injection
        this.userService = userService;
    }
}
```

**@Inject** also used used for dependency injection in Spring, it is provided by Spring Framework, while @Inject is provided by Java (JSR-330).

**Using @Inject (Java JSR-330)**

```java
import jakarta.inject.Inject;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public void printUser() {
        System.out.println(userRepository.getUser());
    }
}
```

## 10. What is @Profile Annotation?

**Profiles** allow you to segregate parts of your application configuration for different environments like dev, test, and production.

```java
# application.properties
spring.profiles.active=dev

# application-dev.properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb

# application-prod.properties
server.port=80
spring.datasource.url=jdbc:mysql://prod-server:3306/mydb
```

```java
@Component
@Profile("dev")
public class DevDataLoader {
    // Only loaded in dev profile
}
```


## 11. What is ApplicationContext?

**ApplicationContext** is the core container in Spring Framework that manages beans, dependency injection, bean lifecycle, and configuration of the application.

It provides advanced features like **event handling, internationalization, and AOP**. It’s an enhanced version of `BeanFactory` and is commonly used in Spring applications.

- Central interface for Spring applications
- Manages bean lifecycle and dependencies
- Provides additional enterprise features
- Event publishing and handling
- Resource loading and internationalization

```java
@Component
public class MyService {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    public void doSomething() {
        // Get bean programmatically
        UserService userService = applicationContext.getBean(UserService.class);
        
        // Publish event
        applicationContext.publishEvent(new CustomEvent("data"));
    }
}
```


## 12. What is @Component, @Configuration, @Primary, @Qualifier, @PatchMapping annotation?

**@Component** is used to tell Spring that this class is a bean and should be managed by the Spring container. Spring automatically detects it during component scanning.”

```java
@Component
public class EmailService {
    public void send() {
        System.out.println("Sending email");
    }
}
```

**@Configuration**  is used when we want to define beans explicitly using `@Bean` methods. It’s mainly used for Java-based configuration instead of XML.”

```java
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }
}
```

**@Primary** When multiple beans of the same type exist and Spring gets confused, `@Primary` tells Spring which bean should be chosen by default.

```java
@Component
@Primary
public class CreditCardPayment implements PaymentService {
}
```

```java
@Component
public class UpiPayment implements PaymentService {
}
```

**@Qualifier** is used when we want to explicitly specify which bean to inject when multiple beans of the same type are present.”

```java
@Autowired
@Qualifier("upiPayment")
private PaymentService paymentService;
```
**@PatchMapping** is used for partial updates of a resource in REST APIs, where only specific fields are modified instead of replacing the entire object.”

```java
@PatchMapping("/users/{id}")
public ResponseEntity<User> updateEmail(
        @PathVariable Long id,
        @RequestBody Map<String, Object> updates) {

    User updatedUser = userService.updateUser(id, updates);
    return ResponseEntity.ok(updatedUser);
}
```

## 13. Explain Spring Boot Actuator endpoints.

**Answer:**
Actuator provides production-ready features like health checks, metrics, and monitoring endpoints. Common endpoints: `/health`, `/metrics`, `/info`, `/env`.

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```java
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

## 14. How do you ensure security a Spring Boot Microservices application?

To secure a **Spring Boot application**, you can use **Spring Security** to handle authentication and authorization. Common practices include:

* **Role-based access control** using annotations like `@PreAuthorize` or `@Secured`
* **JWT or OAuth2 tokens** for stateless authentication
* **Encrypting passwords** with BCrypt or Argon2
* **HTTPS/SSL** to secure data in transit
* **Input validation and CSRF protection** to prevent attacks

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
            .build();
    }
}

@RestController
@PreAuthorize("hasRole('USER')")
public class SecureController {
    
    @GetMapping("/api/user/profile")
    public UserProfile getProfile(Authentication auth) {
        return userService.getProfile(auth.getName());
    }
}
```
## 15. What is Lombok in Java and and whe can we use?


**Lombok** is a **Java library** that helps reduce boilerplate code by generating common methods automatically at compile time using annotations.


**Example Without Lombok**

```java
public class User {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```

**Same Code Using Lombok**

```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private int age;
}
```

**Lombok automatically generates getter & setter at compile time.**


**Common Lombok Annotations**

| Annotation            | Purpose                                                 |
| --------------------- | ------------------------------------------------------- |
| `@Getter` / `@Setter` | Generates getters & setters                             |
| `@ToString`           | Generates `toString()`                                  |
| `@EqualsAndHashCode`  | Generates `equals()` & `hashCode()`                     |
| `@NoArgsConstructor`  | No-arg constructor                                      |
| `@AllArgsConstructor` | All-arg constructor                                     |
| `@Builder`            | Builder pattern                                         |
| `@Data`               | Combines multiple (`Getter + Setter + ToString + etc.`) |



**Use Lombok when:**

* Creating **DTOs / POJOs**
* Working with **Spring Boot projects**
* You want **clean and readable code**
* Reducing boilerplate

**Avoid Lombok when:**

* Debugging is critical (generated code not visible)
* Team is not familiar with Lombok
* You want explicit control over methods

## 17. How can you create a custom configuration and auto-configuration in Spring Boot?

Create **custom configuration** using `@Configuration` and `@Bean` annotations.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    @ConditionalOnProperty(name = "app.cache.enabled", havingValue = "true")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
```

```properties
# application.properties
app.cache.enabled=true
```

Create a **Auto configuration** class with conditional annotations and register it in `META-INF/spring.factories`.

```java
@Configuration
@ConditionalOnClass(MyService.class)
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService(MyProperties properties) {
        return new MyService(properties);
    }
}
```

```properties
# META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.MyAutoConfiguration
```

## 16. Why do we use Long in JpaRepository<Employee, Long>?
In **`JpaRepository<Employee, Long>`**, the **first type (`Employee`)** is the **entity class** the repository manages, and the **second type (`Long`)** is the **type of the entity’s primary key (`@Id`)**.

Using `Long` tells Spring Data JPA what type of value to expect when performing operations like `findById()`, `deleteById()`, or `save()`.


## 18. What is Spring Scheduler?

**Spring Scheduler** is a feature in **Spring Framework** used to **run tasks automatically at a scheduled time or at fixed intervals**.

It is commonly used for **background jobs** like sending emails, cleaning logs, or running periodic tasks using the `@Scheduled` annotation.

**Real-Time Example**

* Sending **daily reports**
* **Cleaning temporary data** every night
* **Sending scheduled emails**
* **Database backup every day**

**Enable Scheduling**
```java
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**Create Scheduled Task**
```java
@Component
public class MyScheduler {

    @Scheduled(fixedRate = 5000)
    public void runTask() {
        System.out.println("Task running every 5 seconds");
    }
}

//Output
Task running every 5 seconds
Task running every 5 seconds
Task running every 5 seconds
```

## 19. How would you set up a logging level for Spring Boot (e.g., debug, info, error)?

Configure logging levels in application.properties for different packages and classes.

```properties
# Root level (applies to all)
logging.level.root=INFO

# Package-specific levels
logging.level.com.mycompany.service=DEBUG
logging.level.com.mycompany.controller=INFO

# Framework levels
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG

# Specific class
logging.level.com.mycompany.UserService=TRACE
```

**Available levels (in order):**
- TRACE (most detailed)
- DEBUG
- INFO
- WARN
- ERROR (least detailed)

```java
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    public User findUser(Long id) {
        log.debug("Finding user with id: {}", id);
        log.info("User search completed");
        return user;
    }
}
```

 
## 20. What is `@Scope`, `@prototype`, `@PostConstruct` and `@PreDestroy` and  in Spring Boot?

**@Scope**

* Purpose: Defines the lifecycle and visibility context of a Spring bean.
* Default: singleton (one instance per container).
* Common Values: prototype, request, session, application.
* Example: Applied at class level alongside stereotype annotations.

```java
@Component
@Scope("prototype")public class ReportGenerator {}
```

**@prototype**

* Correction: There is no annotation named @prototype in Spring.
* Actual Use: It is a value passed into the @Scope annotation.
* Behavior: Instructs Spring to create a brand new instance every single time the bean is requested or injected.
* Example:
 
```java
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) or @Scope("prototype").
```

**@PostConstruct**

* Purpose: Marks a method to run exactly once, immediately after Spring finishes initializing the bean and injecting all dependencies.
* Common Use: Executing setup logic, populating caches, or validating that mandatory properties are not null.
* Rule: The method must be void and cannot accept any arguments.

```java
@PostConstructpublic void init() {
    this.connectionPool = initializePool();
}
```

**@PreDestroy**

* Purpose: Marks a method to run exactly once, right before the Spring container destroys the bean and removes it from memory.
* Common Use: Cleaning up resources, closing database connections, stopping background threads, or releasing file handles.
* Limitation: It does not trigger for prototype scoped beans because Spring loses track of them after creation.

```java
@PreDestroypublic void cleanup() {
    this.connectionPool.close();
}
```

**Singleton vs Prototype Scope**

| Feature              | Singleton                              | Prototype                                  |
|----------------------|----------------------------------------|--------------------------------------------|
| Instances created    | One per Spring container               | New instance every time it is requested    |
| Default scope        | Yes (default in Spring)                | No                                         |
| Memory               | Shared, memory efficient               | More memory usage                          |
| State                | Shared state — be careful with mutable fields | Each caller gets its own state        |
| Annotation           | `@Scope("singleton")` or default       | `@Scope("prototype")`                      |


**🔹 1. Singleton Scope (Default)**

👉 Only **one instance** is created and shared.

```java
import org.springframework.stereotype.Component;

@Component
class MyService {
    public void print() {
        System.out.println("Singleton instance: " + this);
    }
}
```

```java
@Autowired
MyService s1;

@Autowired
MyService s2;

s1.print();
s2.print();


// Output (same object)
Singleton instance: MyService@123
Singleton instance: MyService@123
```

**🔹 2. Prototype Scope**

👉 **New object every time** it is requested.

```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
class ReportGenerator {
    public void print() {
        System.out.println("Prototype instance: " + this);
    }
}
```

```java
import org.springframework.context.ApplicationContext;

@Autowired
ApplicationContext context;

ReportGenerator r1 = context.getBean(ReportGenerator.class);
ReportGenerator r2 = context.getBean(ReportGenerator.class);

r1.print();
r2.print();

//  Output (different objects)

Prototype instance: ReportGenerator@111
Prototype instance: ReportGenerator@222
```

## 21. How do you manage Spring Beans in Spring Boot?

Spring Boot automatically manages beans through component scanning and auto-configuration. You can also define custom beans explicitly.

```java
// Automatic bean management
@Service
public class UserService { }

@Repository
public class UserRepository { }

// Manual bean definition
@Configuration
public class BeanConfig {
    
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
    
    @Bean
    @ConditionalOnProperty("app.redis.enabled")
    public RedisTemplate redisTemplate() {
        return new RedisTemplate();
    }
}
```

Spring manages the entire lifecycle: creation, dependency injection, initialization, and destruction.

## 22. What is the difference between `@OneToMany` and `@ManyToOne` in Spring Boot?

These annotations define JPA relationships between entities with different cardinalities.

```java
// One User has Many Orders
@Entity
public class User {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    // cascade = CascadeType.ALL means all operations performed on the parent entity will also be applied to the child entities automatically.
}

// Many Orders belong to One User
@Entity
public class Order {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

**Key differences:**
- `@OneToMany`: One entity relates to multiple entities
- `@ManyToOne`: Multiple entities relate to one entity
- They're opposite sides of the same relationship

## 23. How can you configure pagination and sorting in Spring Boot with Spring Data JPA?

Use `PagingAndSortingRepository` or `Pageable` parameter in repository methods for automatic pagination.

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findAll(Pageable pageable);
}

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users")
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }
}
```

## 25. How Does `@EnableAutoConfiguration` Work Internally?

**DataSource:** 

@EnableAutoConfiguration is a Spring Boot annotation that automatically configures your Spring application based on the dependencies present in your classpath.

It saves time by removing the need to manually configure beans, like DataSource, DispatcherServlet, etc.

**Where is it used?**
It is included inside the @SpringBootApplication annotation by default.

```java
@SpringBootApplication
public class MyApp {
  public static void main(String[] args) {
SpringApplication.run(MyApp.class, args);
  }
}
```

👉 @SpringBootApplication =
@Configuration + @ComponentScan + @EnableAutoConfiguration

**How it works:**
It looks at the JARs in your classpath and tries to auto-configure beans accordingly.

**For example:**
- If Spring Boot sees spring-boot-starter-web, it will configure Tomcat, Spring MVC, and a default DispatcherServlet.
- If it sees spring-boot-starter-data-jpa, it will auto-configure Hibernate, DataSource, etc.

**In Interview, Say This:**
- @EnableAutoConfiguration helps in reducing boilerplate configuration.
- It allows me to start building features quickly without manual setup.
- If needed, I can still override its default settings using @Configuration classes 


## 26. What is `@Async` and How Does It Work?


The  annotation in Java is a Spring Framework feature that allows a method to be executed asynchronously—meaning the caller method proceeds immediately without waiting for the async method to finish. This boosts performance by executing independent tasks in parallel. 
How  Works Under the Hood 

**How @Async Works Under the Hood**

1. **Spring AOP (Aspect-Oriented Programming):** When you annotate a method with , Spring intercepts the call and wraps the target object inside a dynamic proxy. 
2. **Task Execution:** Instead of executing the method in the caller's main thread, the proxy submits the method's execution to a TaskExecutor (a Thread Pool). 

```java
@Bean
public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(10);
    executor.initialize();
    return executor;
}
```
3. **Immediate Return:** Control is immediately handed back to the calling thread so it can continue running other code, while the async method runs independently.  

**How to Use It**

To use  in a Spring/Spring Boot application, you must follow three steps: 

1. **Enable Async Processing** Add the  annotation to one of your Spring Configuration classes. 
```java
@Configuration
@EnableAsync
public class AppConfig {
}
```

2. **Annotate the Method** Add  to the method you want to run in the background. It must be a  method and can return either  or a . CompletableFuture.
```java
@Service
public class EmailService {

    @Async
    public void sendEmail(String recipient) {
        // Heavy or blocking email-sending logic
        System.out.println("Email sent to " + recipient);
    }
}
```

3. **Call the Method** When you call this method from another class, Spring intercepts the call and runs it in a background thread. 
Important Rules & Gotchas 


## 27. What is `@EventListener` in Spring Boot?

It's Spring's built-in **event-driven mechanism** — publish an event, and any listener picks it up.

**Create an event:**
```java
public class UserRegisteredEvent {
    private final String email;
    public UserRegisteredEvent(String email) { this.email = email; }
    public String getEmail() { return email; }
}
```

**Publish it:**
```java
@Autowired ApplicationEventPublisher publisher;

public void register(String email) {
    // save user...
    publisher.publishEvent(new UserRegisteredEvent(email));
}
```

**Listen to it:**
```java
@EventListener
public void onUserRegistered(UserRegisteredEvent event) {
    sendWelcomeEmail(event.getEmail());
}
```

By default it's **synchronous**. Combine with `@Async` to make it non-blocking.

Use it to decouple logic — like sending emails, notifications, or audit logging — without tight coupling between services.


## 28. What is `ResponseEntity` and When to Use It?

`ResponseEntity` gives you **full control** over the HTTP response — status code, headers, and body.

```java
// Simple return — no control
@GetMapping("/user/{id}")
public User getUser(@PathVariable Long id) {
    return userService.find(id);
}

// With ResponseEntity — full control
@GetMapping("/user/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.find(id);
    if (user == null) {
        return ResponseEntity.notFound().build();           // 404
    }
    return ResponseEntity.ok(user);                        // 200
}
```

You can also set custom headers:
```java
return ResponseEntity
    .status(HttpStatus.CREATED)
    .header("X-Custom-Header", "value")
    .body(savedUser);
```

Use it when you need to return different status codes, add headers, or return an empty body with a specific status.


## 29. What is `@ExceptionHandler` vs `@ControllerAdvice`?

Both handle exceptions — but at different scopes.

**`@ExceptionHandler`** — handles exceptions **only within the same controller**.

```java
@RestController
public class UserController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
```

**`@ControllerAdvice`** — handles exceptions **globally across all controllers**. One place to handle all errors.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(500).body("Something went wrong");
    }
}
```

## 30. DispatcherServlet, HttpServletRequest, PathVariable, RequestParam, Interceptors, Filter

**DispatcherServlet**

**DispatcherServlet** is the central **Front Controller** in **Spring MVC**. It receives all incoming HTTP requests and manages the request processing flow.

How it works:

1. Client sends an HTTP request.
2. **DispatcherServlet** receives the request.
3. It finds the appropriate **Controller**.
4. It calls the controller method.
5. The controller processes the request and returns a result.
6. **DispatcherServlet** sends the response back to the client.

Example:

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }
}
```

When `/users` is called, the request first reaches **DispatcherServlet**, which then forwards it to the `getUsers()` method.

**Key Point:**
**DispatcherServlet** is the heart of **Spring MVC**. It handles incoming requests, routes them to the correct controller, and returns the response to the client.


**HttpServletRequest**

**HttpServletRequest** is an object that represents an incoming **HTTP request**. It provides access to all request data sent by the client.

Using **HttpServletRequest**, we can access:

* **Request parameters**
* **HTTP headers**
* **Request URL**
* **HTTP method** (GET, POST, PUT, DELETE)
* **Session information**

Example:

```java
@GetMapping("/hello")
public String hello(HttpServletRequest request) {

    String name = request.getParameter("name");

    return "Hello " + name;
}
```

Request:

```text
/hello?name=John
```

Response:

```text
Hello John
```

**@PathVariable**

**@PathVariable** is used to get a value directly from the **URL path**.

Example URL:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable Long id) {
    return "User ID: " + id;
}
```

Request:

```text
/users/10
```

Result:

```text
User ID: 10
```

In this example, **10** is taken from the URL and assigned to the variable **id**.

**Key Point:**
Use **@PathVariable** when the value is part of the URL path and identifies a specific resource.


**@RequestParam**

**@RequestParam** is used to get a value from a **query parameter** in the URL.

Example:

```java
@GetMapping("/users")
public String getUser(@RequestParam String name) {
    return "User: " + name;
}
```

Request:

```text
/users?name=John
```

Result:

```text
User: John
```

In this example, **John** is taken from the query parameter **name**.

**Key Point:**
Use **@RequestParam** when the value is sent as a query parameter after the `?` in the URL.


**Difference Between @PathVariable and @RequestParam**

**@PathVariable**

* Value comes from the **URL path**
* Used to identify a specific resource
* Example: `/users/10`

**@RequestParam**

* Value comes from the **query string**
* Used for filtering, searching, sorting, or optional inputs
* Example: `/users?name=John`


**Filter**

A **Filter** is part of the **Servlet API** and executes **before the request reaches the DispatcherServlet**. It can intercept and process every HTTP request and response.

Common use cases:

* **Logging**
* **Authentication**
* **CORS handling**
* **Header modification**
* **Request/Response preprocessing**

Example:

```java
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Request received");

        chain.doFilter(request, response);
    }
}
```

**Key Point:**
A **Filter** works at the **Servlet Container** level and is executed before Spring MVC processes the request.


**Interceptor**

An **Interceptor** is a feature of **Spring MVC** that executes between the **DispatcherServlet** and the **Controller**.

It allows us to execute logic:

* Before the controller method (`preHandle`)
* After the controller method (`postHandle`)
* After the complete request is finished (`afterCompletion`)

Example:

```java
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        System.out.println("Before Controller");
        return true;
    }
}
```

**Key Point:**
An **Interceptor** is Spring-specific and is mainly used for controller-related processing.


**Difference Between Filter and Interceptor**

| **Filter**                        | **Interceptor**                                        |
| --------------------------------- | ------------------------------------------------------ |
| Part of **Servlet API**           | Part of **Spring MVC**                                 |
| Runs before **DispatcherServlet** | Runs after **DispatcherServlet** and before Controller |
| Works for all HTTP requests       | Works for Spring MVC requests                          |
| Can modify Request and Response   | Mainly used for application logic                      |
| Independent of Spring             | Spring-specific                                        |



## 31. Top 50 Spring Boot Annotations 

| #  | Annotation                 | Purpose                                                                                                                                     |
| -- | -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| 1  | `@SpringBootApplication`   | Marks the main class of a Spring Boot app; combines configuration, auto-configuration, and component scanning to bootstrap the application. |
| 2  | `@EnableAutoConfiguration` | Automatically configures Spring beans based on classpath dependencies, reducing manual setup.                                               |
| 3  | `@ComponentScan`           | Scans specified packages to detect and register Spring components (beans) automatically.                                                    |
| 4  | `@Configuration`           | Indicates a class contains bean definitions and acts as a source of Spring configuration.                                                   |
| 5  | `@Bean`                    | Declares a method that returns an object managed as a Spring bean in the application context.                                               |
| 6  | `@Component`               | Marks a class as a generic Spring-managed component for dependency injection.                                                               |
| 7  | `@Autowired`               | Automatically injects dependencies by type into constructors, fields, or setters.                                                           |
| 8  | `@Qualifier`               | Specifies which bean to inject when multiple beans of the same type exist.                                                                  |
| 9  | `@Primary`                 | Designates a bean as the default choice when multiple candidates are available.                                                             |
| 10 | `@Value`                   | Injects values from properties files, environment variables, or expressions into fields.                                                    |


**✅ Stereotype / Layer Annotations**

| #  | Annotation        | Purpose                                                                                      |
| -- | ----------------- | -------------------------------------------------------------------------------------------- |
| 11 | `@Controller`     | Handles incoming web requests and typically returns a view (like JSP/Thymeleaf).             |
| 12 | `@RestController` | Combines `@Controller` and `@ResponseBody` to return JSON/XML responses directly.            |
| 13 | `@Service`        | Marks a class as a service layer component containing business logic.                        |
| 14 | `@Repository`     | Indicates a data access component and enables exception translation for database operations. |


**✅ REST API Annotations**

| #  | Annotation        | Purpose                                                                            |
| -- | ----------------- | ---------------------------------------------------------------------------------- |
| 15 | `@RequestMapping` | Maps HTTP requests to handler methods with configurable URL, method, headers, etc. |
| 16 | `@GetMapping`     | Shortcut for handling HTTP GET requests for retrieving data.                       |
| 17 | `@PostMapping`    | Handles HTTP POST requests for creating resources.                                 |
| 18 | `@PutMapping`     | Handles HTTP PUT requests for updating entire resources.                           |
| 19 | `@DeleteMapping`  | Handles HTTP DELETE requests to remove resources.                                  |
| 20 | `@PatchMapping`   | Handles partial updates of a resource using HTTP PATCH.                            |
| 21 | `@PathVariable`   | Binds URI template variables from the URL to method parameters.                    |
| 22 | `@RequestParam`   | Extracts query parameters from the request URL into method parameters.             |
| 23 | `@RequestBody`    | Converts HTTP request body (JSON/XML) into a Java object.                          |
| 24 | `@ResponseBody`   | Converts Java object into HTTP response body (JSON/XML).                           |
| 25 | `@ResponseStatus` | Sets the HTTP response status code for a method or exception.                      |


**✅ Spring Data JPA**

| #  | Annotation        | Purpose                                                                       |
| -- | ----------------- | ----------------------------------------------------------------------------- |
| 26 | `@Entity`         | Marks a class as a JPA entity mapped to a database table.                     |
| 27 | `@Table`          | Specifies the table name and schema details for an entity.                    |
| 28 | `@Id`             | Defines the primary key of an entity.                                         |
| 29 | `@GeneratedValue` | Specifies strategy for auto-generating primary key values.                    |
| 30 | `@Column`         | Customizes column mapping such as name, length, nullable, unique constraints. |
| 31 | `@Transient`      | Excludes a field from being persisted to the database.                        |
| 32 | `@OneToOne`       | Defines a one-to-one relationship between two entities.                       |
| 33 | `@OneToMany`      | Defines a one-to-many relationship between entities.                          |
| 34 | `@ManyToOne`      | Defines a many-to-one relationship between entities.                          |
| 35 | `@ManyToMany`     | Defines a many-to-many relationship using a join table.                       |


**✅ Transaction & AOP**

| #  | Annotation                     | Purpose                                                                             |
| -- | ------------------------------ | ----------------------------------------------------------------------------------- |
| 36 | `@Transactional`               | Ensures methods execute within a database transaction with commit/rollback support. |
| 37 | `@EnableTransactionManagement` | Enables Spring’s annotation-driven transaction management.                          |
| 38 | `@Aspect`                      | Declares a class for cross-cutting concerns like logging, security, or auditing.    |
| 39 | `@Before`                      | Executes advice before the target method runs.                                      |
| 40 | `@After`                       | Executes advice after the target method completes (regardless of outcome).          |


**✅ Exception Handling**

| #  | Annotation              | Purpose                                                                 |
| -- | ----------------------- | ----------------------------------------------------------------------- |
| 41 | `@ControllerAdvice`     | Provides global exception handling and shared logic across controllers. |
| 42 | `@RestControllerAdvice` | Same as `@ControllerAdvice` but returns REST responses (JSON).          |
| 43 | `@ExceptionHandler`     | Handles specific exceptions and defines custom error responses.         |


**✅ Validation**

| #  | Annotation  | Purpose                                                                                |
| -- | ----------- | -------------------------------------------------------------------------------------- |
| 44 | `@Valid`    | Triggers validation of request body or method parameters using validation annotations. |
| 45 | `@NotNull`  | Ensures a field is not null during validation.                                         |
| 46 | `@NotBlank` | Ensures a string is not null, empty, or only whitespace.                               |
| 47 | `@Size`     | Validates size constraints for strings, collections, or arrays.                        |
| 48 | `@Email`    | Validates that a field contains a properly formatted email address.                    |


**✅ Async & Scheduling**

| #  | Annotation     | Purpose                                                   |
| -- | -------------- | --------------------------------------------------------- |
| 49 | `@EnableAsync` | Enables asynchronous method execution in the application. |
| 50 | `@Async`       | Executes a method in a separate thread asynchronously.    |



# ✅ 20. Java RESTful Services 

## 1. What is CORS, and how does it work?

**CORS (Cross-Origin Resource Sharing)** is a **browser security mechanism** that allows or blocks a web application from making requests to a **different domain, protocol, or port** than the one from which it was loaded.

For example:

* Frontend: `http://localhost:3000`
* Backend: `http://localhost:8080`

Since the **ports are different**, they are considered **different origins**, and the browser blocks the request unless **CORS** is enabled.

**Key Features**

* Provides **secure cross-origin communication**.
* Controlled by **HTTP headers**.
* Prevents unauthorized websites from accessing resources.
* Works only in **web browsers** (not between backend services).
* Supports **Simple Requests** and **Preflight Requests**.

**How it Works**

1. The browser sends a request from the frontend to the backend.
2. The backend checks whether the origin is allowed.
3. If allowed, it returns headers like:

   ```text
   Access-Control-Allow-Origin: http://localhost:3000
   ```
4. The browser reads the header and allows the request.
5. If the header is missing or invalid, the browser blocks the request.

For **POST**, **PUT**, **DELETE**, or custom headers, the browser first sends an **OPTIONS** request called a **Preflight Request** to check whether the actual request is permitted.

**Why to Use**

* Protects applications from **unauthorized cross-origin access**.
* Allows secure communication between **frontend and backend** hosted on different domains.
* Essential for **Single Page Applications (SPA)** and **Microservices**.

**When to Use**

* Frontend and backend are hosted on **different domains or ports**.
* Building applications with **React**, **Angular**, or **Vue**.
* When APIs are consumed by external web applications.

**Spring Boot CORS Example**

**Using `@CrossOrigin` Annotation:**

```java
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }
}
```

**Global CORS Configuration:**

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

**Real-World Example**

A **React** application running on `http://localhost:3000` calls a **Spring Boot API** running on `http://localhost:8080`. Without CORS configuration, the browser blocks the request. By enabling CORS and allowing `http://localhost:3000`, the frontend can securely access the backend API.


## 2. What is an API and what are different type of API?


**API (Application Programming Interface)** is a set of **rules and protocols** that allows two software applications to **communicate and exchange data** with each other without knowing their internal implementation.

For example, when a mobile app requests user details from a backend server, it uses an **API**.

**Key Features**

* Enables **communication** between different applications.
* Hides the **internal implementation** of a system.
* Promotes **reusability** and **modularity**.
* Supports **platform-independent** integration.
* Can exchange data in formats like **JSON** or **XML**.

**How it Works**

1. A **Client** sends an API request.
2. The **Server** processes the request.
3. The server accesses the database or business logic.
4. The server returns a **response** to the client, usually in **JSON** format.

Example:

```text
Client  --->  API Request  --->  Server
Client  <---  JSON Response <--- Server
```

**Why to Use**

* Connects different applications and services.
* Allows **frontend and backend** to communicate.
* Simplifies integration with **third-party systems**.
* Improves **code reusability** and **maintainability**.

**When to Use**

* Building **web**, **mobile**, or **microservices** applications.
* Integrating with external services like payment gateways or maps.
* Exposing business functionality to other systems.

**Different Types of API**

| **API Type**      | **Description**                                                                             | **Example**                           |
| ----------------- | ------------------------------------------------------------------------------------------- | ------------------------------------- |
| **REST API**      | Uses **HTTP methods** (`GET`, `POST`, `PUT`, `DELETE`) and usually exchanges **JSON** data. | Spring Boot REST API                  |
| **SOAP API**      | Uses **XML** messages with strict standards and security features.                          | Banking and enterprise systems        |
| **GraphQL API**   | Client requests only the required data using a single endpoint.                             | Facebook GraphQL                      |
| **gRPC API**      | High-performance API using **Protocol Buffers** and HTTP/2.                                 | Internal microservices communication  |
| **WebSocket API** | Provides **real-time, two-way communication** between client and server.                    | Chat applications, live notifications |

**Types of APIs Based on Access**

| **Type**          | **Description**                                    |
| ----------------- | -------------------------------------------------- |
| **Public API**    | Available for external developers.                 |
| **Private API**   | Used only within an organization.                  |
| **Partner API**   | Shared with authorized business partners.          |
| **Composite API** | Combines multiple API calls into a single request. |

**Simple Spring Boot REST API Example**

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }
}
```

Request:

```http
GET /api/users
```

Response:

```json
"User List"
```



## 3. What are RESTful web services?

**What are RESTful Web Services?**

**RESTful Web Services** are web services that follow the **REST (Representational State Transfer)** architectural style. They allow applications to communicate over **HTTP** using standard methods like **GET**, **POST**, **PUT**, and **DELETE** to perform operations on resources.

A **resource** can be any data, such as a **User**, **Product**, or **Order**, and it is identified by a **URL**.

**Key Features**

* Uses standard **HTTP protocol**.
* Follows a **Resource-Based Architecture**.
* Supports **CRUD operations** using HTTP methods.
* **Stateless** – the server does not store client session information.
* Usually exchanges data in **JSON** format (can also support XML).
* Easy to build, consume, and scale.

**How it Works**

1. The **Client** sends an HTTP request to a URL.
2. The **REST API** processes the request.
3. The server performs the required business logic or database operation.
4. The server returns an HTTP response, usually with **JSON** data.

**Common HTTP Methods**

| **HTTP Method** | **Operation**        | **Example URL** |
| --------------- | -------------------- | --------------- |
| **GET**         | Retrieve data        | `/users/1`      |
| **POST**        | Create new data      | `/users`        |
| **PUT**         | Update existing data | `/users/1`      |
| **DELETE**      | Delete data          | `/users/1`      |

**Why to Use**

* Simple and **lightweight**.
* Supports **loosely coupled** communication between systems.
* Easy to integrate with **web**, **mobile**, and **microservices** applications.
* Works well with **JSON**, making data exchange fast and efficient.

**When to Use**

* Building **Web APIs** and **Microservices**.
* Communication between **frontend and backend**.
* Mobile applications consuming backend services.
* Integration with third-party systems.

**Simple Spring Boot REST API Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

**Real-World Example**

In an **E-commerce Application**:

* `GET /products` → Get all products.
* `GET /products/101` → Get a specific product.
* `POST /orders` → Create a new order.
* `PUT /orders/101` → Update an order.
* `DELETE /orders/101` → Cancel an order.


## 4. What are the principles of REST?

REST (Representational State Transfer) is based on six key architectural principles that guide the design of web services.

**REST Principles:**
- **Stateless:** Each request contains all necessary information
- **Client-Server:** Separation of concerns between client and server
- **Cacheable:** Responses can be cached for better performance
- **Uniform Interface:** Consistent way to interact with resources
- **Layered System:** Architecture can have multiple layers
- **Code on Demand:** Optional - server can send executable code

These principles ensure scalability, reliability, and maintainability of web services.


## 5: What is XML how to return XML in response?

**XML (eXtensible Markup Language)** is a **markup language** for representing data using **tags**.

It is **verbose**, supports **attributes and namespaces**, is **self-documenting with schema validation**, and is used in **enterprise apps and SOAP services**.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```
```java
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "user")
public class User {
    private int id;
    private String name;

    // getters setters
}
```
```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @GetMapping(value = "/student", produces = "application/xml")
    public Student getStudent() {
        Student s = new Student();
        s.setId(1);
        s.setName("John");
        return s;
    }
}

// Result
// <student>
//     <id>1</id>
//     <name>John</name>
// </student>
```


## 6. What are HTTP methods and their usage?

**HTTP Methods** are standard operations used by a client to communicate with a server in a **RESTful Web Service**. They define what action should be performed on a resource, such as **creating**, **reading**, **updating**, or **deleting** data.

**Key Features**

* Define the **type of operation** to perform.
* Work over the **HTTP protocol**.
* Used in **REST APIs** for CRUD operations.
* Help create **standardized and predictable APIs**.
* Can be **safe** and **idempotent** depending on the method.

**How it Works**

1. The **Client** sends an HTTP request with a specific method.
2. The **Server** identifies the method and processes the request.
3. The server returns an appropriate **HTTP response** with data or status.

For example:

```text
Client ---- GET /users/1 ----> Server
Client <--- User Data -------- Server
```

**Main HTTP Methods and Their Usage**

| **Method**  | **Purpose**                          | **CRUD Operation** | **Example**                         |
| ----------- | ------------------------------------ | ------------------ | ----------------------------------- |
| **GET**     | Retrieve data                        | Read               | Get user details                    |
| **POST**    | Create new data                      | Create             | Create a new user                   |
| **PUT**     | Update or replace existing data      | Update             | Update user details                 |
| **PATCH**   | Partially update existing data       | Update             | Update only user's email            |
| **DELETE**  | Remove data                          | Delete             | Delete a user                       |
| **OPTIONS** | Returns supported HTTP methods       | N/A                | Used in **CORS Preflight** requests |
| **HEAD**    | Same as GET but returns only headers | N/A                | Check if a resource exists          |

**Why to Use**

* Provides a **standard way** to interact with APIs.
* Makes APIs **easy to understand and maintain**.
* Supports **CRUD operations** in a RESTful manner.
* Improves interoperability between different systems.

**When to Use**

* Building **RESTful APIs**.
* Communication between **frontend and backend**.
* **Microservices** and third-party API integrations.
* Web and mobile application development.

**Spring Boot Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "Get User";
    }

    @PostMapping
    public String createUser() {
        return "Create User";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id) {
        return "Update User";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return "Delete User";
    }
}
```


## 7. What is the difference between PUT and POST?

**PUT:**
- Updates or replaces entire resource
- Idempotent (same result when called multiple times)
- Requires complete resource data
- Used for updates when you know the resource ID

**POST:**
- Creates new resources
- Not idempotent (creates new resource each time)
- Can send partial data
- Server typically generates resource ID

```java
// POST - Create new user (server generates ID)
POST /users
{
    "name": "John",
    "email": "john@example.com"
}
// Response: 201 Created with generated ID

// PUT - Update/replace entire user resource
PUT /users/123
{
    "id": 123,
    "name": "John Updated",
    "email": "john.updated@example.com"
}
// Response: 200 OK or 204 No Content
```

## 8. What are HTTP status codes?

HTTP status codes indicate the result of an HTTP request. They're grouped into categories and provide standardized way to communicate request outcomes.

**Status Code Categories:**
- **1xx:** Informational responses
- **2xx:** Success responses
- **3xx:** Redirection responses
- **4xx:** Client error responses
- **5xx:** Server error responses

**Common Status Codes:**
- **200 OK:** Request successful
- **201 Created:** Resource created successfully
- **204 No Content:** Success with no response body
- **400 Bad Request:** Invalid request syntax
- **401 Unauthorized:** Authentication required
- **403 Forbidden:** Access denied
- **404 Not Found:** Resource not found
- **409 Conflict:** Request conflicts with current state
- **500 Internal Server Error:** Server error

```java
@RestController
public class UserController {
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);           // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)  // 201 Created
                           .body(created);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();    // 204 No Content
    }
}
```

# ✅ 21. Java Microservices 


## 1. What are microservices?

**Microservices** is an architectural style where a large application is divided into **small, independent, and loosely coupled services**. Each service is responsible for a **single business function** and can be developed, deployed, and scaled independently.

**Key Features**

* **Independent services** with a single responsibility.
* **Loosely coupled** and communicate through **REST APIs**, **gRPC**, or **message queues**.
* Each service can have its own **database**.
* Services can be **developed, deployed, and scaled independently**.
* Supports **continuous deployment** and **fault isolation**.

**How it Works**

* The application is split into multiple small services such as **User Service**, **Order Service**, and **Payment Service**.
* Each service runs as a separate application.
* Services communicate with each other over the network using APIs or messaging systems.
* If one service fails, the others can continue working.

**Example Architecture**

```text
Client
   |
API Gateway
   |
-------------------------------
|          |          |
User     Order     Payment
Service  Service   Service
|          |          |
DB         DB         DB
```

**Why to Use**

* Makes large applications easier to **develop and maintain**.
* Allows teams to work on different services independently.
* Improves **scalability** because only the required service needs to be scaled.
* Increases **fault tolerance** since failure of one service does not bring down the entire application.

**When to Use**

* Large and complex applications with multiple business modules.
* Applications requiring **high scalability** and **frequent deployments**.
* Projects where multiple teams work independently.
* Cloud-native and distributed systems.

**Monolithic vs Microservices**

| **Feature**        | **Monolithic**                     | **Microservices**                      |
| ------------------ | ---------------------------------- | -------------------------------------- |
| **Architecture**   | Single application                 | Multiple independent services          |
| **Deployment**     | Entire application                 | Individual service                     |
| **Scalability**    | Scale whole application            | Scale only required service            |
| **Database**       | Usually one shared database        | Each service can have its own database |
| **Failure Impact** | One issue can affect the whole app | Failure is isolated to one service     |

**Simple Spring Boot Example**

```java id="g5m8xq"
@RestController
public class UserController {

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable int id) {
        return "User ID: " + id;
    }
}
```

This can be a separate **User Microservice**. Similarly, **Order** and **Payment** services can be separate Spring Boot applications communicating through REST APIs.

**Common Technologies Used**

* **Spring Boot** for service development.
* **Spring Cloud** for distributed system features.
* **Eureka** for service discovery.
* **API Gateway** for request routing.
* **Kafka** or **RabbitMQ** for asynchronous communication.
* **Docker** and **Kubernetes** for containerization and orchestration.

## 2. What design patterns used in Microservices architecture?

Common design patterns in Microservices are:

* **API Gateway** – A single entry point that routes client requests to appropriate microservices.
* **Service Discovery** – A mechanism that automatically detects and locates available service instances.
* **Circuit Breaker** – A pattern that stops calls to a failing service to prevent system-wide failure.
* **Saga Pattern** – A way to manage distributed transactions using a sequence of local transactions.
* **CQRS (Command Query Responsibility Segregation)** – A pattern that separates read operations from write operations.
* **Database per Service** – Each microservice has its own dedicated database for data isolation.
* **Bulkhead Pattern** – A pattern that isolates resources to prevent one service failure from affecting others.

**Example:**
```java
// 1. API Gateway Pattern
@RestController
public class ApiGatewayController {
    @Autowired
    private UserClient userClient;
    
    @Autowired
    private OrderClient orderClient;
    
    @GetMapping("/api/user-orders/{userId}")
    public UserOrdersResponse getUserWithOrders(@PathVariable Long userId) {
        User user = userClient.getUser(userId);
        List<Order> orders = orderClient.getOrdersByUser(userId);
        return new UserOrdersResponse(user, orders);
    }
}

// 2. Service Discovery Pattern, Commonly implemented using Netflix Eureka.
@EnableEurekaClient
@SpringBootApplication
public class OrderServiceApplication {
}
restTemplate.getForObject("http://PAYMENT-SERVICE/pay", String.class);

// 3. Circuit Breaker Pattern (with Resilience4j)
@Service
public class OrderService {
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public Payment processPayment(PaymentRequest request) {
        return paymentClient.process(request);
    }
    
    public Payment paymentFallback(PaymentRequest request, Exception e) {
        return new Payment("PENDING", "Payment service unavailable");
    }
}

// 4. Saga Pattern (Choreography)
@Service
public class OrderSagaService {
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public void createOrder(Order order) {
        orderRepository.save(order);
        kafkaTemplate.send("order-created", new OrderEvent(order.getId()));
    }
    
    @KafkaListener(topics = "payment-failed")
    public void handlePaymentFailed(PaymentEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).get();
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}

// 5. CQRS (Command Query Responsibility Segregation)
// Command (Write)
@PostMapping("/orders")
public void createOrder(@RequestBody Order order) { }

// Query (Read)
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) { }

// 6. Database per Service
# order-service application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/orderdb

// 7. Bulkhead Pattern
// Example (Resilience4j)
@Bulkhead(name = "paymentService", type = Bulkhead.Type.THREADPOOL)
public String processPayment() {
    return "Processing payment";
}
```

## 3. Monolithic vs Microservices Architecture

**Monolithic** and **Microservices** are two different software architecture styles. In a **Monolithic architecture**, the entire application is built and deployed as a **single unit**, while in **Microservices**, the application is divided into **small, independent services**.

| **Feature**          | **Monolithic**                             | **Microservices**                                 |
| -------------------- | ------------------------------------------ | ------------------------------------------------- |
| **Architecture**     | Single, unified application                | Collection of small independent services          |
| **Deployment**       | Entire application is deployed together    | Each service is deployed independently            |
| **Scalability**      | Scale the whole application                | Scale only the required service                   |
| **Codebase**         | Single codebase                            | Multiple smaller codebases                        |
| **Database**         | Usually one shared database                | Each service can have its own database            |
| **Technology Stack** | Generally one technology stack             | Different services can use different technologies |
| **Failure Impact**   | Failure can affect the entire application  | Failure is isolated to one service                |
| **Development**      | Simpler for small projects                 | Better for large and complex projects             |
| **Maintenance**      | Becomes difficult as the application grows | Easier because services are independent           |

**Key Features**

* **Monolithic:** Simple architecture, easy to develop and deploy initially.
* **Microservices:** Loosely coupled, independently deployable, and highly scalable.
* **Microservices** communicate using **REST APIs**, **gRPC**, or **message queues**.

**How it Works**

* In a **Monolithic application**, modules like User, Order, and Payment are all part of one application.
* In **Microservices**, each module runs as a separate service with its own logic and possibly its own database.

**Architecture Example**

```text
Monolithic:
+--------------------------------------+
| User | Order | Payment | Inventory   |
+--------------------------------------+
           Single Application
```

```text
Microservices:
Client
   |
API Gateway
   |
---------------------------------
|        |         |            |
User   Order    Payment    Inventory
Service Service  Service     Service
|        |         |            |
DB       DB        DB           DB
```

**Why to Use**

* **Monolithic** is easier to build, test, and deploy for small applications.
* **Microservices** provide better scalability, flexibility, and fault isolation for large applications.

**When to Use**

* Choose **Monolithic** for:

  * Small or startup projects.
  * Simple business logic.
  * Small development teams.
* Choose **Microservices** for:

  * Large and complex applications.
  * Multiple independent teams.
  * High scalability and frequent deployments.
  * Cloud-native and distributed systems.

**Code Example**

**Monolithic (Single Application)**

```java
@RestController
public class AppController {

    @GetMapping("/user")
    public String getUser() {
        return "User Service";
    }

    @GetMapping("/order")
    public String getOrder() {
        return "Order Service";
    }
}
```

**Microservices (Separate Applications)**

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User Microservice";
    }
}
```

```java
@RestController
public class OrderController {

    @GetMapping("/orders")
    public String getOrders() {
        return "Order Microservice";
    }
}
```

Each controller can run as a separate **Spring Boot** application and communicate through **REST APIs**.


## 4. What are the advantages of microservices?

Microservices offer several benefits over monolithic architectures, particularly for large, complex applications and organizations.

**Key Advantages:**
- **Independent deployment:** Deploy services separately
- **Technology diversity:** Different tech stacks per service
- **Scalability:** Scale individual services based on demand
- **Fault isolation:** Failure in one service doesn't crash entire system
- **Team autonomy:** Small teams own complete services
- **Faster development:** Parallel development of services

These benefits enable organizations to move faster, scale better, and maintain more resilient systems.

## 5. What are the challenges of microservices?

While microservices offer many benefits, they also introduce complexity and challenges that must be carefully managed.

**Major Challenges:**
- **Distributed system complexity:** Network calls, latency, failures
- **Data consistency:** Managing transactions across services
- **Service communication:** Inter-service communication overhead
- **Monitoring and debugging:** Tracing requests across services
- **Deployment complexity:** Managing multiple services
- **Testing challenges:** Integration and end-to-end testing

Organizations need proper tooling, processes, and expertise to handle these challenges effectively.

## 6. What are CQRS principles?

**CQRS (Command Query Responsibility Segregation)** is an **architectural pattern** that separates **read operations (Queries)** from **write operations (Commands)**. Instead of using the same model for both reading and updating data, CQRS uses **different models and logic** for each responsibility.

**Key Features**

* Separates **Commands** (Write) and **Queries** (Read).
* Uses **different models** for updating and fetching data.
* Improves **scalability** and **performance**.
* Supports **Event-Driven Architecture** and **Microservices**.
* Often used with **Event Sourcing** (optional).

**How it Works**

1. A **Command** performs an action like **Create**, **Update**, or **Delete** data.
2. The command updates the **Write Database**.
3. An event may be published to synchronize the **Read Database**.
4. A **Query** retrieves data only from the **Read Database** without modifying it.

**Example Flow:**

```text
Client
   |
   |---- Command (Create Order) ----> Write Model ----> Write DB
   |
   |---- Query (Get Order) ---------> Read Model -----> Read DB
```

**Why to Use**

* Improves **read and write performance** independently.
* Allows separate optimization of **read** and **write** databases.
* Reduces complexity in applications with heavy business logic.
* Makes systems easier to **scale** and maintain.

**When to Use**

* In **Microservices Architecture**.
* In applications with **high read and write traffic**.
* When **read and write operations have different performance requirements**.
* In systems using **Event-Driven Architecture** or **Event Sourcing**.

**Command vs Query**

| **Command**                        | **Query**                     |
| ---------------------------------- | ----------------------------- |
| Changes data                       | Reads data                    |
| Uses **POST**, **PUT**, **DELETE** | Uses **GET**                  |
| Updates the **Write Model**        | Reads from the **Read Model** |
| Returns success/failure            | Returns requested data        |

**Simple Java Example**

**Command Service (Write):**

```java
@Service
public class UserCommandService {

    public void createUser(User user) {
        // Save user to database
        System.out.println("User Created");
    }
}
```

**Query Service (Read):**

```java
@Service
public class UserQueryService {

    public User getUserById(Long id) {
        // Fetch user from database
        return new User(id, "John");
    }
}
```

**Controller:**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserCommandService commandService;

    @Autowired
    private UserQueryService queryService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        commandService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return queryService.getUserById(id);
    }
}
```

**Real-World Example**

In an **E-commerce Application**:

* **Command Side** handles operations like **Place Order**, **Update Inventory**, and **Process Payment**.
* **Query Side** handles operations like **View Order History** and **Search Products**.

The read database can be optimized for fast searches, while the write database focuses on data consistency.


```text
src/main/java/com/example/user

├── command
│   ├── controller
│   │   └── UserCommandController.java
│   ├── service
│   │   └── UserCommandService.java
│   ├── handler
│   │   └── CreateUserCommandHandler.java
│   └── model
│       └── CreateUserCommand.java
│
├── query
│   ├── controller
│   │   └── UserQueryController.java
│   ├── service
│   │   └── UserQueryService.java
│   ├── handler
│   │   └── GetUserQueryHandler.java
│   └── model
│       └── GetUserQuery.java
│
├── entity
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
└── config
    └── ApplicationConfig.java
```


## 7. Blocking vs No blocking db call in Microservice?


A **blocking DB call** means the thread waits until the database response comes back.

A **non-blocking DB call** means the thread does not wait; it can handle other requests while waiting for the DB response.

Non-blocking is better for high-traffic microservices because it improves performance and scalability.

**Blocking Example (Spring Boot – JPA)**

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userRepository.findById(id).orElse(null); 
}
```

- Here thread **waits** until DB returns result → Blocking


**Non-Blocking Example (Spring WebFlux)**

```java
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable Long id) {
    return userRepository.findById(id);
}
```

- Returns **Mono** → Thread **does not wait** → Non-blocking


## 8. How microservices communicate with each other?


In our system, microservices mainly communicated using **REST APIs over HTTP**.
For synchronous communication, we used **Feign Client** with service discovery through **Eureka**.

For asynchronous communication, especially for event-based workflows, we used **Kafka**. This helped us reduce tight coupling and improve scalability.

Microservices communicate in two ways:

1. **Synchronous (REST, Feign, WebClient)** – Request/Response model
2. **Asynchronous (Kafka, RabbitMQ)** – Event-driven model

**Synchronous Communication**


**Using Feign Client**

```java
// `- Step 1: Configure Feign Client`
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

// `- Step 2: Enable Feign Client`
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

// `- Step 3: Create Feign Client Interface`
@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable int id);
}

// `- Step 4: use in controller`
@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    public Order getOrder(int userId) {
        User user = userClient.getUserById(userId);
        return new Order(user);
    }
}
```

**Using Spring RestTemplate (Spring Boot - Legacy, Sequential Calls (Blocking))**

```java
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

// Step 1: Configure as bean
@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

// Step 2: Usage in service
@Service
public class ExternalApiService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    // GET request
    public String getData() {
        String url = "https://api.example.com/data";
        return restTemplate.getForObject(url, String.class);
    }
    
    // GET with headers
    public String getDataWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
            "https://api.example.com/data",
            HttpMethod.GET,
            entity,
            String.class
        );
        return response.getBody();
    }
    
    // POST request
    public String postData(Map<String, Object> requestBody) {
        String url = "https://api.example.com/api";
        return restTemplate.postForObject(url, requestBody, String.class);
    }
    
    // POST with response entity
    public ResponseEntity<String> postWithResponse(Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity("https://api.example.com/api", entity, String.class);
    }
}
```

**Spring WebClient (Spring Boot 5+ - - Sequential Calls , Reactive, Recommended)**

```java
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

// Configure WebClient bean
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl("https://api.example.com")
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}

// Usage in service
@Service
public class ExternalApiService {
    
    @Autowired
    private WebClient webClient;
    
    // GET request
    public Mono<String> getData() {
        return webClient.get()
            .uri("/data")
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(String.class);
    }
    
    // GET with response body object
    public Mono<User> getUser(String id) {
        return webClient.get()
            .uri("/users/{id}", id)
            .retrieve()
            .bodyToMono(User.class);
    }
    
    // POST request
    public Mono<String> postData(User user) {
        return webClient.post()
            .uri("/users")
            .body(Mono.just(user), User.class)
            .retrieve()
            .bodyToMono(String.class);
    }
    
    // POST with headers
    public Mono<ResponseEntity<Void>> postDataWithHeaders(User user) {
        return webClient.post()
            .uri("/users")
            .header("Authorization", "Bearer " + token)
            .body(Mono.just(user), User.class)
            .retrieve()
            .toBodilessEntity();
    }
    
    // With error handling
    public Mono<String> getDataWithErrors() {
        return webClient.get()
            .uri("/data")
            .retrieve()
            .onStatus(HttpStatusCode::isError, 
                response -> response.bodyToMono(String.class)
                    .flatMap(error -> Mono.error(new RuntimeException(error))))
            .bodyToMono(String.class);
    }
}
```


**Asynchronous Communication**
```java
// Producer
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;
public void sendMessage() {
    kafkaTemplate.send("order-topic", "Order Created");
}
```

```java
// Consumer
@KafkaListener(topics = "order-topic", groupId = "group1")
public void consume(String message) {
    System.out.println("Received: " + message);
}
```

## 9. How do you Handle Failures in Microservices?

Failures in microservices are handled using **Circuit Breaker, Retry with backoff, Timeout, and Bulkhead patterns** to prevent cascading failures.

We also use **fallback methods, health checks, centralized logging, monitoring, and API Gateway** to improve resilience and quickly detect issues.

```java
// Steps 1: Add Dependencies (Maven)
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

// Step 2: Configure RestTemplate Bean
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
        // RestTemplate is now in maintenance mode. In modern Spring Boot, we prefer WebClient for non-blocking and reactive applications.
    }
}

// Inject RestTemplate in Your Client
@Component
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    @Retry(name = "user-service")
    public User getUser(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8081/users/" + id,
                User.class);
    }

    public User fallbackUser(Long id, Exception ex) {
        return new User(id, "Unknown User", "unknown@example.com");
    }
}

// application.yml Configuration
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        registerHealthIndicator: true
        slidingWindowSize: 5
        minimumNumberOfCalls: 3
        failureRateThreshold: 50
        waitDurationInOpenState: 10s

  retry:
    instances:
      user-service:
        maxAttempts: 3
        waitDuration: 2s

management:
  endpoints:
    web:
      exposure:
        include: health,metrics
```

Configuration includes failure rate thresholds, wait durations, and retry attempts to control when circuits open and close.

## 10. What is Event-Driven Architecture in Java?

**Event-Driven Architecture (EDA)** is an **architectural pattern** where different services communicate by **producing and consuming events**. Instead of calling each other directly, one service publishes an event, and other interested services react to it asynchronously.

**Key Features**

* **Asynchronous communication** between services.
* Uses **Events**, **Producers**, and **Consumers**.
* Services are **loosely coupled**.
* Supports **scalability** and **high availability**.
* Commonly implemented using **Kafka**, **RabbitMQ**, or other **message brokers**.

**How it Works**

1. A service performs an action (for example, an order is created).
2. It publishes an **event** like `OrderCreated`.
3. The **message broker** delivers the event.
4. Other services, such as **Payment Service**, **Inventory Service**, and **Notification Service**, consume the event and perform their tasks independently.

**Example Flow:**

```
Order Service
      |
      |  Publish: OrderCreated Event
      v
   Kafka / RabbitMQ
   /        |        \
  v         v         v
Payment  Inventory  Notification
Service   Service      Service
```

**Why to Use**

* Reduces **tight coupling** between services.
* Improves **performance** by processing tasks asynchronously.
* Makes the system easier to **scale** and **extend**.
* Supports **real-time processing** and **eventual consistency**.

**When to Use**

* In **Microservices Architecture**.
* For **real-time applications** like order processing, notifications, and analytics.
* When multiple services need to react to the same event.
* When high **scalability** and **fault tolerance** are required.

**Simple Java Example with Spring Event**

```java
// Event
public class OrderCreatedEvent {
    private String orderId;

    public OrderCreatedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
```

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

// Event Publisher
@Autowired
private ApplicationEventPublisher publisher;

public void createOrder() {
    System.out.println("Order Created");
    publisher.publishEvent(new OrderCreatedEvent("ORD-101"));
}
```

```java
// Event Listener
@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    System.out.println("Processing payment for: " + event.getOrderId());
}
```

**Real-World Example**

In an **E-commerce Application**:

* **Order Service** publishes an `OrderCreated` event.
* **Payment Service** processes the payment.
* **Inventory Service** updates stock.
* **Notification Service** sends an email or SMS.

All these services work **independently** without directly calling each other.


## 11. What is API Gateway and predicates?

An **API Gateway** is a **single entry point** for all client requests in a **Microservices** architecture. It receives the request, applies rules, and forwards it to the appropriate microservice.

**Predicates** are **conditions or matching rules** used by the API Gateway to decide **which request should be routed to which service**.

**Key Features**

* **API Gateway**

  * Single entry point for all APIs.
  * Routes requests to the correct microservice.
  * Can handle **authentication**, **authorization**, **load balancing**, **logging**, and **rate limiting**.
* **Predicates**

  * Define routing conditions.
  * Match requests based on **Path**, **Method**, **Header**, **Host**, **Query Parameter**, etc.
  * Used before forwarding the request.

**How it Works**

1. The client sends a request to the **API Gateway**.
2. The gateway checks the configured **predicates**.
3. If a predicate matches, the request is routed to the corresponding microservice.
4. The microservice processes the request and returns the response through the gateway.

**Example Flow**

```text id="u6knw2"
Client
   |
API Gateway
   |
   |-- /users/**  ----> User Service
   |
   |-- /orders/** ----> Order Service
   |
   |-- /payment/** ---> Payment Service
```

**Why to Use**

* Hides the internal microservice structure from clients.
* Reduces the number of client calls.
* Centralizes security, logging, and routing.
* Makes the system easier to manage and scale.

**When to Use**

* In **Microservices architectures** with multiple backend services.
* When a single entry point for APIs is needed.
* When implementing centralized **security**, **monitoring**, or **request routing**.

**Common Predicate Types**

| **Predicate** | **Purpose**                           |
| ------------- | ------------------------------------- |
| **Path**      | Matches the URL path                  |
| **Method**    | Matches HTTP methods like GET or POST |
| **Header**    | Matches request headers               |
| **Host**      | Matches the host name                 |
| **Query**     | Matches query parameters              |

**Spring Cloud Gateway Example**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

**`application.yml`**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: http://localhost:8081
          predicates:
            - Path=/users/**

        - id: order-service
          uri: http://localhost:8082
          predicates:
            - Path=/orders/**
```

```java
// API Gateway with Spring Cloud Gateway
@SpringBootApplication
public class ApiGatewayApplication {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/users/**")
                .uri("lb://user-service"))
            .route("order-service", r -> r.path("/orders/**")
                .uri("lb://order-service"))
            .build();
    }
}

```

For a typical Java/Spring Boot microservices application, commonly used API gateways are:

| Gateway              | Mostly Used With            |
| -------------------- | --------------------------- |
| Spring Cloud Gateway | Spring Boot microservices   |
| AWS API Gateway      | AWS cloud                   |
| Netflix Zuul         | Older Spring Cloud projects |
| Kong Gateway         | Cloud-native systems        |
| NGINX                | Reverse proxy/API gateway   |
| Azure API Management | Azure cloud                 |


**Advantages of API Gateway**

| Advantage                 | Explanation                         |
| ------------------------- | ----------------------------------- |
| Single Entry Point        | Easier client communication         |
| Better Security           | Centralized authentication          |
| Reduced Client Complexity | Client doesn't manage many services |
| Load Balancing            | Traffic distribution                |
| Centralized Logging       | Easier monitoring                   |
| Rate Limiting             | Prevent excessive requests          |
| API Aggregation           | Combine multiple service responses  |
| Version Management        | Easy API versioning                 |

**Disadvantages of API Gateway**

| Disadvantage            | Explanation                             |
| ----------------------- | --------------------------------------- |
| Single Point of Failure | If gateway fails, all services affected |
| Additional Latency      | One extra network hop                   |
| Complex Maintenance     | Gateway rules become complicated        |
| Bottleneck Risk         | Heavy traffic can overload gateway      |
| Deployment Complexity   | Requires scaling and monitoring         |


## 12. What is circuit breaker pattern?

A **Circuit Breaker** is a **design pattern** used in **Microservices** to prevent repeated calls to a failing service. It detects failures and temporarily stops requests to avoid overloading the unavailable service.

**Key Features**

* Prevents **cascade failures** in distributed systems.
* Improves **fault tolerance** and **system stability**.
* Supports **fallback methods** when a service is unavailable.
* Automatically recovers when the failed service becomes healthy.
* Commonly implemented using **Resilience4j** or **Hystrix** (older).

**How it Works**

A Circuit Breaker has **three states**:

| **State**     | **Description**                                                                                                          |
| ------------- | ------------------------------------------------------------------------------------------------------------------------ |
| **Closed**    | Normal state. Requests are sent to the target service.                                                                   |
| **Open**      | Too many failures occurred. Requests are blocked, and a fallback response is returned.                                   |
| **Half-Open** | After a waiting period, a few test requests are allowed. If they succeed, the circuit closes; otherwise, it opens again. |

**Flow**

```text id="j9p4qs"
Closed  -->  (Many Failures)  -->  Open
   ^                                |
   |                                |
   +---- (Success in Half-Open) <---+
                Half-Open
```

**Why to Use**

* Prevents one failed service from affecting the entire application.
* Reduces unnecessary network calls to unavailable services.
* Improves application availability by providing fallback responses.
* Helps maintain a better user experience during service outages.

**When to Use**

* In **Microservices** where services communicate over the network.
* When calling external APIs or third-party services.
* In systems where temporary failures or network issues are common.

**Spring Boot with Resilience4j Example**

```java id="cb9w2m"
@RestController
public class UserController {

    @CircuitBreaker(name = "userService", fallbackMethod = "fallback")
    @GetMapping("/users")
    public String getUsers() {
        // Call to another microservice
        throw new RuntimeException("Service Down");
    }

    public String fallback(Exception ex) {
        return "User Service is temporarily unavailable.";
    }
}
```

**How the Above Works**

* The `getUsers()` method calls another service.
* If failures exceed the configured threshold, the **Circuit Breaker** moves to the **Open** state.
* New requests do not call the failed service; instead, the **`fallback()`** method returns a default response.
* After a timeout, the breaker enters **Half-Open** state and checks whether the service has recovered.

**Common Configuration (application.yml)**

```yaml id="7khw6y"
resilience4j:
  circuitbreaker:
    instances:
      userService:
        failureRateThreshold: 50
        slidingWindowSize: 10
        waitDurationInOpenState: 10s
```

## 13: What is resilience4j pattern?



**Resilience4j** is a **lightweight fault-tolerance library** used in Java and **Spring Boot** applications. It helps make **Microservices** more reliable by handling failures gracefully using patterns like **Circuit Breaker**, **Retry**, **Rate Limiter**, **Bulkhead**, and **Time Limiter**.

**Key Features**

* Implements **Circuit Breaker** pattern.
* Supports **Retry** for failed requests.
* Provides **Rate Limiter** to control request traffic.
* Uses **Bulkhead** to isolate resources and prevent cascading failures.
* Supports **Time Limiter** for handling slow responses.
* Easy integration with **Spring Boot** and **Spring Cloud**.
* Lightweight and a modern replacement for **Hystrix**.

**How it Works**

* A request is wrapped with a **Resilience4j component** (for example, a Circuit Breaker).
* If the target service fails or becomes slow, Resilience4j applies the configured rule.
* It can **retry the request**, **return a fallback response**, or **block further calls** until the service recovers.

**Common Modules**

| **Module**          | **Purpose**                                        |
| ------------------- | -------------------------------------------------- |
| **Circuit Breaker** | Stops calls to a failing service                   |
| **Retry**           | Retries failed requests automatically              |
| **Rate Limiter**    | Limits the number of incoming requests             |
| **Bulkhead**        | Isolates resources to prevent system-wide failures |
| **Time Limiter**    | Sets a timeout for service calls                   |
| **Cache**           | Stores frequently used results                     |

**Why to Use**

* Improves **fault tolerance** in distributed systems.
* Prevents **cascade failures**.
* Provides **fallback responses** when services are unavailable.
* Increases system stability and user experience.

**When to Use**

* In **Microservices** architectures.
* When calling external APIs or third-party services.
* In applications that require high availability and resilience.
* With **Spring Boot** applications using REST APIs.

**Spring Boot Example**

**1. Maven Dependency**

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

**2. application.yml Configuration**

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3

  retry:
    instances:
      paymentService:
        maxAttempts: 3
        waitDuration: 2s

  ratelimiter:
    instances:
      paymentService:
        limitForPeriod: 5
        limitRefreshPeriod: 10s
        timeoutDuration: 1s

  timelimiter:
    instances:
      paymentService:
        timeoutDuration: 3s
```

**3. Service Class Example**

```java
@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
    @Retry(name = "paymentService")
    @RateLimiter(name = "paymentService")
    @TimeLimiter(name = "paymentService")
    public String callPaymentService() {
        return restTemplate.getForObject(
                "http://PAYMENT-SERVICE/pay", String.class);
    }

    public String fallback(Exception e) {
        return "Payment service is down. Try later.";
    }
}
```

## 14. What is service discovery?

**Service Discovery** is a mechanism in **microservices architecture** that allows services to **automatically find and communicate with each other** without hardcoding IP addresses or URLs.

**Key Features**

**• Automatic Service Registration** – services register themselves on startup

**• Dynamic Service Lookup** – services discover other services at runtime

**• Load Balancing Support** – requests can be distributed across instances

**• Scalability** – easily add or remove service instances

**• Fault Tolerance** – unhealthy instances can be excluded automatically

**How it works**

A **Service Registry** maintains information about all available service instances.

1. A service starts and **registers** itself with the registry.
2. Another service queries the registry to **discover** the target service.
3. The request is routed to an available service instance.

Common tools: **Netflix Eureka**, **Consul**, and **Apache ZooKeeper**.

**Why to use**

To eliminate **hardcoded service locations**, simplify **service communication**, and support **dynamic scaling** in distributed systems.

**When to use**

Use Service Discovery when:

* Building **Microservices**
* Deploying on **Cloud Platforms**
* Running **Multiple Service Instances**
* Using **Containerized Applications**
* Needing **Dynamic Scaling**

**Code Example (Spring Cloud Eureka Client)**

```java
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

**application.yml**

```yaml
spring:
  application:
    name: user-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

This registers **user-service** with the **Eureka Server**, allowing other services to discover it automatically.



## 15. What is Saga Pattern or How it handle payment failure?

The Saga Pattern is a design pattern used in microservices architecture to manage distributed transactions across multiple services without using a single global database transaction.

Instead of one big transaction, the process is divided into **multiple small local transactions**. Each service completes its own step.

If any step fails, the system performs **compensating actions** to undo the previous steps and keep data consistent.

**Example:**
- In an online order system:
- Order Created → Payment Done → Inventory Reserved.

If inventory fails, Saga will **refund payment and cancel the order**.

There are **two ways to implement Saga**:
- **Choreography**  – services communicate using events.
- **Orchestration** – a central service controls the workflow.

Order Created → Payment Done → Inventory Reserved
If **inventory fails → refund payment + cancel order**


**1. Choreography (Event-Based) – No Central Control**
```java
// Order → Payment → Inventory → (Failure → Compensation)

// Order Service
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void createOrder() {
        System.out.println("Order Created");
        kafkaTemplate.send("order-created", "order123");
    }
}

// Payment Service (Listens to Order Event)
@KafkaListener(topics = "order-created")
public void processPayment(String orderId) {
    System.out.println("Payment Done for " + orderId);

    kafkaTemplate.send("payment-success", orderId);
}

// Inventory Service (Failure Scenario)
@KafkaListener(topics = "payment-success")
public void reserveInventory(String orderId) {
    System.out.println("Inventory Failed for " + orderId);

    kafkaTemplate.send("inventory-failed", orderId);
}

// Compensation (Refund + Cancel)
@KafkaListener(topics = "inventory-failed")
public void handleFailure(String orderId) {
    System.out.println("Refund Payment for " + orderId);
    System.out.println("Cancel Order for " + orderId);
}

```

**2. Using Orchestrator Service**
```java
// Flow: Orchestrator → Order → Payment → Inventory
@Service
public class OrderOrchestrator {

    @Autowired
    private RestTemplate restTemplate;

    public void placeOrder() {

        try {
            // Step 1: Create Order
            restTemplate.postForObject("http://order-service/create", null, String.class);

            // Step 2: Payment
            restTemplate.postForObject("http://payment-service/pay", null, String.class);

            // Step 3: Inventory
            restTemplate.postForObject("http://inventory-service/reserve", null, String.class);

            System.out.println("Order Completed");

        } catch (Exception e) {

            // Compensation logic
            restTemplate.postForObject("http://payment-service/refund", null, String.class);
            restTemplate.postForObject("http://order-service/cancel", null, String.class);

            System.out.println("Transaction Failed → Rolled Back");
        }
    }
}
```


## 16. What is a @Transactional (ACID properties)? How do you handle rollback?


**@Transactional** is a Spring annotation used to manage **database transactions** automatically. It ensures that a group of database operations is executed as a single unit of work.

**How It Works**

When a method marked with **@Transactional** is called:

1. Spring starts a **Transaction**.
2. All database operations run inside that transaction.
3. If everything succeeds, Spring performs a **COMMIT**.
4. If an exception occurs, Spring performs a **ROLLBACK** and undoes all changes.


`@Transactional` is an annotation in **Spring Framework** that tells Spring:

👉 “Run this method inside a database transaction”

- All operations succeed → COMMIT
- Any operation fails → ROLLBACK


It follows **ACID properties:**

| Property    | Meaning                            |
| ----------- | ---------------------------------- |
| Atomicity   | All operations succeed or none (all-or-nothing). |
| Consistency | Data remains valid and follows all rules                 |
| Isolation   | Transactions do not interfere with each other       |
| Durability  | Once committed, data is permanently saved even after a crash          |


**Key Features**

* Automatic **Transaction Management**
* Supports **COMMIT** and **ROLLBACK**
* Ensures **ACID** properties
* Reduces boilerplate code
* Managed by Spring using **AOP (Proxy)**

**Why to Use**

* Maintains data consistency
* Prevents partial updates
* Simplifies transaction handling
* Ensures multiple operations succeed or fail together

**When to Use**

Use **@Transactional** when multiple database operations must be treated as one unit.

Examples:

* Money transfer between accounts
* Creating an order and updating inventory
* Saving data in multiple tables

**Code Example**

```java
@Service
public class BankService {

    @Transactional
    public void transferMoney() {

        withdrawMoney();
        depositMoney();
    }
}
```

If `depositMoney()` fails, Spring rolls back the transaction and `withdrawMoney()` is also undone.


**Example (Bank Transfer)** Transfer ₹100 from Account A → Account B:

1. Debit from A
2. Credit to B

If credit fails, debit must be undone → **Rollback**

**1. Using Spring `@Transactional` (Most Common)**
```java
@Service
public class BankService {

    @Transactional
    public void transferMoney() {
        debitFromAccountA();
        creditToAccountB();
    }
}
```


If any exception occurs → **Automatic Rollback**


**2. Force Rollback When Error Happens**

```java
@Transactional
public void transferMoney() {
    debit();

    if (true) {
        throw new RuntimeException("Error occurred");
    }

    credit();
}
```

→ Transaction will **rollback**


**3. Rollback for Checked Exception**

By default, Spring rolls back only for **RuntimeException**.

```java
@Transactional(rollbackFor = Exception.class)
public void transferMoney() throws Exception {
    debit();
    credit();
}
```


**4. Manual Transaction (JDBC)**

```java
Connection con = dataSource.getConnection();

try {
    con.setAutoCommit(false);

    debit(con);
    credit(con);

    con.commit();

} catch (Exception e) {
    con.rollback();
} finally {
    con.close();
}
```

## 17. How Does `@Transactional` Work Internally?

Spring uses **AOP (Aspect-Oriented Programming)** under the hood. When you annotate a method with `@Transactional`, Spring creates a **proxy** around the bean.

When the method is called:
1. The proxy intercepts the call
2. Opens a transaction (or joins an existing one based on propagation)
3. Executes your method
4. If no exception → commits the transaction
5. If a `RuntimeException` is thrown → rolls back


In Spring Boot, we use `@Transactional`:

```java
@Transactional
public void transferMoney(Long from, Long to, double amount) {
    debit(from, amount);
    credit(to, amount); // if this throws, debit also rolls back
}
```

If a `RuntimeException` occurs, Spring automatically rolls back the transaction.

For checked exceptions, we can configure rollback explicitly:

```java
@Transactional(rollbackFor = Exception.class)
```

**`@Transactional`** Propagation

**Propagation** defines how a transaction behaves when one transactional method calls another transactional method.

| Level | Behavior |
|---|---|
| `REQUIRED` (default) | Join existing transaction, or create new one |
| `REQUIRES_NEW` | Always create a new transaction, suspend the existing one |
| `NESTED` | Run inside a nested transaction (savepoint) |
| `SUPPORTS` | Use existing transaction if present, else run without |
| `NOT_SUPPORTED` | Suspend existing transaction, run without |
| `MANDATORY` | Must have an existing transaction, else throw exception |
| `NEVER` | Must NOT have a transaction, else throw exception |

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void saveAuditLog() {
    // always runs in its own transaction
}
```


## 17. What is a Transaction in SQL?

A transaction is a **group of SQL operations** that execute as a single unit. Either all succeed or all fail — no partial updates.

* **`@Transactional annotation`** = is **wrapper/helper** that uses SQL transactions automatically

Transactions follow **ACID** properties:
* **Atomicity** – All operations succeed or none (all-or-nothing).
* **Consistency** – Data remains valid and follows all rules.
* **Isolation** – Transactions do not interfere with each other.
* **Durability** – Once committed, data is permanently saved even after a crash.

**Transaction Control:**
- `setAutoCommit(false)` - Start transaction
- `commit()` - Save changes
- `rollback()` - Undo changes
- `setSavepoint()` - Create checkpoint

```sql
BEGIN TRANSACTION;

UPDATE account SET balance = balance - 500 WHERE id = 1;  -- debit
UPDATE account SET balance = balance + 500 WHERE id = 2;  -- credit

-- If both succeed
COMMIT;

-- If anything fails
ROLLBACK;
```

Without transactions, if the debit succeeds but the credit fails, you lose money. Transactions prevent that.


## 18. How do you Prevent duplicate payment(idempotency)?

We prevent duplicate payments using an **idempotency key (transaction ID)**. Even if user clicks multiple times, payment is processed only once.

**Idempotency means :** Doing the same operation multiple times gives the same result (no duplicate effect).


**Entity with Unique Constraint**

```java
@Entity
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "transactionId")
})
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private String transactionId;
    private double amount;
    private String status;
}
```


**Service Logic**

```java
@Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(String txnId, double amount) {

        // Step 1: Check existing transaction
        Optional<Payment> existing = paymentRepository.findByTransactionId(txnId);
        if (existing.isPresent()) {
            return existing.get();
        }

        // Step 2: Process and save payment
        try {
            Payment payment = new Payment(txnId, amount, "SUCCESS");
            return paymentRepository.save(payment);
        } catch (DataIntegrityViolationException e) {
            // Step 3: Handle duplicate transaction (race condition)
            return paymentRepository.findByTransactionId(txnId).get();
        }
    }
}
```


## 19. What happens if payment is successful but order update fails?

This is handled using **event-driven architecture and retry/compensation (Saga pattern)**. If order creation fails after payment, we retry or trigger refund.


**Event + Retry Example**

```java
@Service
public class PaymentHandler {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    public void handlePaymentSuccess(Payment payment) {
        try {
            orderService.createOrder(payment);
        } catch (Exception e) {

            // Retry logic (can be Kafka/RabbitMQ in real systems)
            retry(payment);

            // Or compensation (refund)
            paymentService.refund(payment.getTransactionId());
        }
    }

    private void retry(Payment payment) {
        // simple retry logic
        orderService.createOrder(payment);
    }
}
```

## 20. Java 11 HttpClient API, and communication between multiple microservices without event and messaing system?

In **Java 11**, the `HttpClient` API was introduced in the `java.net.http` package to simplify making HTTP requests. It supports **HTTP/1.1 and HTTP/2**, provides a **clean and fluent API**, and allows both **synchronous and asynchronous requests** using `CompletableFuture`.

For example, we create an `HttpClient`, build an `HttpRequest`, and then send it using the `send()` method.

```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

// Basic GET request
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .build();

HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

System.out.println(response.statusCode());
System.out.println(response.body());

// POST request with JSON body
HttpRequest postRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/api"))
    .header("Content-Type", "application/json")
    .header("Authorization", "Bearer " + token)
    .POST(HttpRequest.BodyPublishers.ofString("{\"key\":\"value\"}"))
    .build();

HttpResponse<String> postResponse = client.send(postRequest, 
    HttpResponse.BodyHandlers.ofString());
```

**Async version:**

```java
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);
```

## 20. How to call external API and how to handle parallel call in java?

**1. Call Multiple External APIs in Parallel** (Using CompletableFuture)

```java
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ParallelApiCaller {
    
    private final HttpClient client = HttpClient.newHttpClient();
    
    // Call multiple APIs in parallel
    public List<String> callMultipleApisParallel(List<String> urls) {
        
        // Create CompletableFuture for each API call
        List<CompletableFuture<String>> futures = urls.stream()
            .map(url -> CompletableFuture.supplyAsync(() -> {
                try {
                    return callApi(url);
                } catch (Exception e) {
                    System.err.println("API call failed for " + url + ": " + e.getMessage());
                    return null;
                }
            }))
            .collect(Collectors.toList());
        
        // Wait for all APIs to complete in parallel
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        
        // Collect all results
        return futures.stream()
            .map(CompletableFuture::join)
            .filter(result -> result != null)
            .collect(Collectors.toList());
    }
    
    // Single API call helper method
    private String callApi(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer YOUR_TOKEN")
            .GET()
            .build();
        
        HttpResponse<String> response = client.send(
            request, 
            HttpResponse.BodyHandlers.ofString()
        );
        
        return response.body();
    }
    
    // Example usage
    public static void main(String[] args) {
        ParallelApiCaller caller = new ParallelApiCaller();
        
        List<String> urls = List.of(
            "https://api.example.com/endpoint1",
            "https://api.example.com/endpoint2",
            "https://api.example.com/endpoint3"
        );
        
        long startTime = System.currentTimeMillis();
        
        List<String> results = caller.callMultipleApisParallel(urls);
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Total time: " + (endTime - startTime) + "ms");
        System.out.println("Results: " + results);
    }
}
```

**1. Spring Boot - RestTemplate + CompletableFuture (Parallel)**

```java
@Service
public class ParallelApiService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    // Parallel API calls using CompletableFuture
    public Map<String, Object> callApisParallel() {
        
        // Create futures for each API
        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(
                "https://api.example.com/user/1", User.class);
        });
        
        CompletableFuture<List<Order>> ordersFuture = CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(
                "https://api.example.com/user/1/orders", List.class);
        });
        
        CompletableFuture<Product> productFuture = CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(
                "https://api.example.com/product/1", Product.class);
        });
        
        // Wait for all to complete (parallel execution)
        CompletableFuture.allOf(userFuture, ordersFuture, productFuture).join();
        
        // Combine results
        Map<String, Object> result = new HashMap<>();
        result.put("user", userFuture.join());
        result.put("orders", ordersFuture.join());
        result.put("product", productFuture.join());
        
        return result;
    }
}
```

# ✅ 22. Java kafka and RabbitMQ


## 1. What is Kafka and How Does It Work?

Kafka is a **distributed event streaming platform** — used for high-throughput, real-time data pipelines.

**Core concepts:**
- **Producer** — sends messages to a topic
- **Topic** — a named channel (like a queue, but persistent)
- **Partition** — topics are split into partitions for parallelism
- **Consumer** — reads messages from a topic
- **Consumer Group** — multiple consumers share the load; each partition is read by one consumer in the group
- **Broker** — a Kafka server that stores messages

```
Producer → Topic (Partitions) → Consumer Group → Consumers
```

Messages are **persisted on disk** and retained for a configurable period — so consumers can replay events.

```java
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>3.7.0</version>
</dependency>

// Producer
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", 
                  "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", 
                  "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("my-topic", "key1", "Hello Kafka!");

        producer.send(record);

        producer.close();
    }
}

// Consumer
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my-group");
        props.put("key.deserializer", 
                  "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", 
                  "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("my-topic"));

        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received: " + record.value());
            }
        }
    }
}
```

Use Kafka for: event sourcing, log aggregation, real-time analytics, microservice communication at scale.


## 2. How does Kafka achieve high throughput and low latency?

Kafka achieves high performance using:

* Sequential disk writes
* Batching
* Compression
* Zero-copy transfer
* Partition-based parallelism

**Performance Config Example**

```java id="f4m8qa"
props.put("batch.size", 16384);

props.put("linger.ms", 5);

props.put("compression.type", "snappy");
```

**Explanation**

* `batch.size` → Sends messages in batches
* `linger.ms` → Waits briefly to create larger batch
* `compression.type` → Reduces network traffic


## 3. What is the difference between a topic and a partition?

| Topic                        | Partition                            |
| ---------------------------- | ------------------------------------ |
| Logical category of messages | Physical subdivision of topic        |
| Used to organize messages    | Used for scalability & parallelism   |
| No global ordering           | Ordering guaranteed within partition |

**Example**

```txt id="4v7kzp"
Topic: orders

Partition 0 → msg1, msg4, msg7
Partition 1 → msg2, msg5, msg8
Partition 2 → msg3, msg6, msg9
```

**Create Topic Example**

```java id="x9m2wr"
NewTopic topic =
        new NewTopic("orders", 3, (short) 2);
```

Meaning:

* 3 partitions
* Replication factor = 2


## 4. How does Kafka handle durability and fault tolerance?

Kafka handles durability using:

* Replication
* Leader/Follower architecture
* Persistent storage
* ISR (In-Sync Replicas)

**Flow**

```txt id="r8m4qp"
Leader Partition
      ↓
Follower Replicas
```

If leader broker fails:

```txt id="7n1xpv"
Follower becomes new leader automatically
```

**Durable Producer Config**

```java id="5k9wqm"
props.put("acks", "all");

props.put("min.insync.replicas", "2");
```

**Explanation**

* `acks=all` → Wait for all replicas acknowledgment
* `min.insync.replicas=2` → At least 2 replicas must sync


## 5. What is a consumer group and how does it work?

A consumer group is a group of consumers working together to consume messages from a topic.

**Rules**

* One partition is consumed by only one consumer within same group
* Multiple groups can consume same topic independently

**Example**

```txt id="2m8xqa"
Topic → 3 partitions

Consumer Group:
Consumer A → Partition 0
Consumer B → Partition 1
Consumer C → Partition 2
```

**Important**

```txt id="3q7vwp"
Consumers > Partitions
↓
Extra consumers remain idle
```

**Consumer Example**

```java id="9r4kxm"
props.put("group.id", "order-service");

KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(props);

consumer.subscribe(
        Collections.singletonList("orders"));
```


## 6. How does Kafka ensure message ordering?

Kafka guarantees ordering only within a partition.

**How?**

Messages are stored using offsets:

```txt id="8v1mzp"
Offset 0
Offset 1
Offset 2
```

Consumers read sequentially.

**Best Practice**

Use same key for related messages.

```java id="6m3xqw"
producer.send(
    new ProducerRecord<>(
        "orders",
        "user-123",
        "order-created"
    )
);
```

## 7. What Happens If a Consumer Crashes Before Committing the Offset?

If a Kafka consumer crashes after processing a message but before committing the offset, Kafka assumes the message was not successfully processed. When the consumer restarts, it will read the same message again from the last committed offset, which can lead to duplicate processing (at-least-once delivery).

```java
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

    for (ConsumerRecord<String, String> record : records) {
        process(record.value()); // Message processed
    }

    // Consumer crashes here before commit
    consumer.commitSync();
}
```

**What Happens?**

1. Message is processed.
2. Consumer crashes before `commitSync()`.
3. Offset is not saved.
4. After restart, Kafka reads the same message again.
5. Message may be processed twice.

**How to Handle It?**

* Use **idempotent processing** (safe to process the same message multiple times).
* Store processed message IDs to avoid duplicates.
* Use **Kafka transactions** for exactly-once processing when needed.


## 8. What is RabbitMQ and When to Use It Over Kafka?

RabbitMQ is a **traditional message broker** — it routes messages between producers and consumers using queues and exchanges.

**Core concepts:**
- **Producer** → sends to an **Exchange**
- **Exchange** → routes to one or more **Queues** (based on routing rules)
- **Consumer** → reads from a Queue
- Once a message is consumed and acknowledged, it's **deleted**

```java
// Send
rabbitTemplate.convertAndSend("order-exchange", "order.created", payload);

// Receive
@RabbitListener(queues = "order-queue")
public void receive(String message) { }
```

**RabbitMQ vs Kafka:**

| | RabbitMQ | Kafka |
|---|---|---|
| Message retention | Deleted after consumed | Retained (replayable) |
| Throughput | Moderate | Very high |
| Use case | Task queues, RPC, routing | Event streaming, audit logs |
| Ordering | Per-queue | Per-partition |
| Complexity | Simpler | More complex |

Use **RabbitMQ** for task queues, job processing, and complex routing.
Use **Kafka** for event streaming, high-volume data, and replay scenarios.


## 9. What is gRPC and How Does It Differ from REST?

gRPC is a **high-performance RPC framework** by Google. It uses **Protocol Buffers (protobuf)** for serialization and **HTTP/2** for transport.

You define your API in a `.proto` file:
```proto
service OrderService {
  rpc GetOrder (OrderRequest) returns (OrderResponse);
}
```

gRPC generates client and server code automatically from this contract.

**gRPC vs REST:**

| | REST | gRPC |
|---|---|---|
| Protocol | HTTP/1.1 | HTTP/2 |
| Format | JSON (text) | Protobuf (binary) |
| Speed | Slower | Much faster |
| Streaming | Limited | Built-in (bi-directional) |
| Browser support | Full | Limited |
| Contract | Optional (OpenAPI) | Strict (.proto file) |

Use gRPC for **internal microservice communication** where performance matters. Use REST for **public APIs** and browser clients.


## 10. What is a Service Mesh (Istio)?

A service mesh is an **infrastructure layer** that handles service-to-service communication in microservices — without changing your application code.

**Istio** is the most popular service mesh. It works by injecting a **sidecar proxy (Envoy)** alongside every service pod in Kubernetes.

All traffic goes through the sidecar, which handles:
- **Load balancing**
- **mTLS (mutual TLS)** — encrypted, authenticated service-to-service calls
- **Traffic management** — canary deployments, retries, timeouts, circuit breaking
- **Observability** — metrics, logs, distributed tracing automatically

```
Service A → [Envoy Sidecar] ──── [Envoy Sidecar] → Service B
                    ↕                       ↕
               Istio Control Plane (Istiod)
```

Without Istio, you'd have to implement retries, timeouts, and mTLS in every service manually.

Use it in large Kubernetes-based microservice architectures where you need security, observability, and traffic control at scale.


# ✅ 23. Java and Application Security


## 0. What are security vulnerability issues?
Common **Java security vulnerability issues :** -  occur when applications are not properly protected from attacks or sensitive data exposure.

1. **SQL Injection**
This happens when **user input is directly used in SQL queries :** - , allowing attackers to manipulate the query and access or modify database data.
2. **Cross-Site Scripting (XSS)**
Attackers inject **malicious scripts into web pages :** - , which execute in other users’ browsers.
3. **Cross-Site Request Forgery (CSRF) :** - 
An attacker tricks a user into performing **unwanted actions** on a web application where the user is already authenticated.
4. **Insecure Deserialization :** - 
If an application **deserializes untrusted data**, attackers may execute malicious code.
5. **Sensitive Data Exposure :** - 
Passwords, API keys, or personal data may be **stored or transmitted without proper encryption**
6. **Improper Authentication and Authorization :** - 
Weak authentication or incorrect access control may allow **unauthorized users to access secure resources**.
7. **Using Outdated Libraries :** - 
Old dependencies may contain **known security vulnerabilities**.

```java
// Prevent SQL injection
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, userId);
```


## 1: What is Java security model?

**Java Security Model** is a **built-in security framework** in the Java platform that protects applications from unauthorized access and malicious code execution.

* Comprehensive security framework built into Java platform
* **Bytecode Verification**: Ensures code follows Java language rules
* **Class Loading**: Secure loading and verification of classes
* **Security Manager**: Controls access to system resources
* **Access Control**: Permission-based security for operations
* **Cryptography**: Built-in encryption and digital signature support
* **Sandbox**: Restricted execution environment for untrusted code

```java
// Security Manager example
import java.lang.SecurityManager;

public class MySecurityManager extends SecurityManager {
    @Override
    public void checkRead(String file) {
        if (file.startsWith("/etc/")) {
            throw new SecurityException("Access denied to system files");
        }
        super.checkRead(file);
    }
}

// Enable security manager
System.setSecurityManager(new MySecurityManager());
```

## 2: What is sandbox in Java?

**Sandbox in Java** is a **restricted environment** for running untrusted code.

It **limits file, network, and system access**, uses **security policies**, provides **isolation** to protect the host, and was commonly used for **applets**.

```java
// Applet sandbox restrictions
public class MyApplet extends Applet {
    public void init() {
        // Cannot read local files
        // Cannot make network connections to other hosts
        // Cannot execute system commands
        // Limited to browser security policies
    }
}
```


## 3: What is bytecode verification?

**Bytecode Verification** is the process where the **JVM checks Java bytecode** for safety before execution.

It ensures **type safety, correct control flow, and stack usage**, preventing **illegal memory access or security issues**.

```java
// Bytecode verification checks:
// 1. Stack overflow/underflow prevention
// 2. Type consistency
// 3. Proper exception handling
// 4. Valid bytecode instructions

// Example: This would fail verification
// Attempting to call method on wrong type
// String s = new Integer(5);
// s.charAt(0); // Type mismatch caught by verifier
```


## 4: What is the security manager?

**Security Manager** is a Java component that **enforces security policies at runtime**.

It performs **permission checks** for file, network, and system access using **policy files**. It is **deprecated and removed in Java 17**, replaced by modern security mechanisms like the module system.


```java
// Security Manager usage (deprecated)
SecurityManager sm = System.getSecurityManager();
if (sm != null) {
    sm.checkRead("/etc/passwd"); // Throws SecurityException if not allowed
}

// Policy file example
grant {
    permission java.io.FilePermission "/tmp/*", "read,write";
    permission java.net.SocketPermission "localhost:8080", "connect";
};
```


## 5: What are digital signatures in Java?

**Digital Signatures in Java** are a **cryptographic mechanism** to verify **code authenticity and integrity**.

JAR files are **signed with a private key** and verified using a **public key certificate**, ensuring the code **has not been tampered with** and establishing **trust in the publisher**.

```java
// Creating digital signature
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(privateKey);
signature.update(data);
byte[] digitalSignature = signature.sign();

// Verifying signature
signature.initVerify(publicKey);
signature.update(data);
boolean isValid = signature.verify(digitalSignature);
```


## 6: What is encryption and decryption in Java?

**Encryption and Decryption in Java** is the process of converting data to and from a **secure unreadable format**.

It supports **symmetric (AES)** and **asymmetric (RSA)** encryption using **JCA APIs**, ensuring **secure data transmission, password protection, and file security**.

```java
// AES encryption example
Cipher cipher = Cipher.getInstance("AES");
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
SecretKey secretKey = keyGen.generateKey();

// Encrypt
cipher.init(Cipher.ENCRYPT_MODE, secretKey);
byte[] encrypted = cipher.doFinal("Hello World".getBytes());

// Decrypt
cipher.init(Cipher.DECRYPT_MODE, secretKey);
byte[] decrypted = cipher.doFinal(encrypted);
```


## 7: What is SSL/TLS in Java?

**SSL** stands for Secure Sockets Layer, and **TLS** stands for Transport Layer Security

**SSL/TLS in Java** are **secure communication protocols** for encrypted data transmission (e.g., **HTTPS**).

They use a **handshake process** and **certificates** to establish trust, supported by **JSSE**, with **KeyStore and TrustStore** for managing keys and certificates.

```java
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
</dependency>

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// SSL/TLS client example
SSLContext sslContext = SSLContext.getInstance("TLS");
sslContext.init(null, null, null);

SSLSocketFactory factory = sslContext.getSocketFactory();
SSLSocket socket = (SSLSocket) factory.createSocket("example.com", 443);

// HTTPS with RestTemplate
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.getForEntity(
    "https://api.example.com/data", String.class);
```

## 8: What is authentication vs authorization?

* **Authentication**: Verifies "who you are" - identity verification
* **Authorization**: Determines "what you can do" - access control
* **Authentication First**: Must authenticate before authorization
* **Examples**: Login (authentication), accessing admin panel (authorization)
* **Mechanisms**: Passwords, tokens, certificates for auth; roles, permissions for authz
* Both essential for complete security

```java
// Authentication - verify identity
@PostMapping("/login")
public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
    if (userService.validateCredentials(request.getUsername(), request.getPassword())) {
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }
    return ResponseEntity.status(401).body("Invalid credentials");
}

// Authorization - check permissions
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/users")
public List<User> getUsers() { return userService.getAllUsers(); }
```


## 9: What is OAuth?

**OAuth** is an **open standard for authorization** that allows third-party apps to access user resources **without sharing passwords**.

It uses **access tokens**, involves **Resource Owner, Client, Authorization Server, and Resource Server**, and supports flows like **authorization code and client credentials**.

```java
// OAuth 2.0 Spring Security configuration
@Configuration
@EnableOAuth2Client
public class OAuth2Config {
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
    
    @Bean
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId("my-client-id");
        details.setClientSecret("my-client-secret");
        details.setAccessTokenUri("https://auth-server.com/oauth/token");
        return details;
    }
}
```

**OAuth 1.0** uses signature-based authentication and is complex, while **OAuth 2.0** is token-based, simpler, faster, and widely used in modern applications.

## 11: What is OAuth 2.0?

**OAuth 2.0** is an **authorization framework** that allows secure access to resources using **access tokens**.

It supports flows like **Authorization Code (most secure), Client Credentials, PKCE**, and uses **scopes** to define permissions, avoiding password sharing.

```java
// OAuth 2.0 Authorization Server configuration
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("my-client")
            .secret(passwordEncoder.encode("my-secret"))
            .authorizedGrantTypes("authorization_code", "refresh_token")
            .scopes("read", "write")
            .redirectUris("http://localhost:8080/callback");
    }
}

// Resource server protection
@EnableResourceServer
@RestController
public class ApiController {
    @GetMapping("/api/data")
    @PreAuthorize("#oauth2.hasScope('read')")
    public String getData() { return "Protected data"; }
}
```

**OAuth 1.0** uses signature-based authentication and is complex, while **OAuth 2.0** is token-based, simpler, faster, and widely used in modern applications.


## 11:  How JWT(JSON Web Token) Authentication works in Spring Boot

JWT authentication is a stateless security mechanism where a token is issued after login and used to authorize subsequent requests without storing session data on the server.

**How JWT Works**

1. **Login**: Client sends credentials → Server validates → Returns JWT token
2. **Authorization**: Client includes JWT in request headers → Server validates token → Grants access


The backend server checks credentials using:
- Database (MySQL, PostgreSQL, etc.)
- or external provider (LDAP, OAuth, etc.)

**JWT Structure**

A JWT consists of three Base64-encoded parts separated by dots:
HEADER.PAYLOAD.SIGNATURE

**Sample JWT Token:**
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjE2MjM5MDIyLCJleHAiOjE2MTYzMjU0MjIsInJvbGVzIjpbIlVTRVIiLCJBRE1JTiJdfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
**Decoded Structure:**

- **Header**: Contains algorithm information (e.g., HS256)
  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```

- **Payload**: Contains claims (username, roles, expiration)
  ```json
  {
    "sub": "johndoe",
    "iat": 1616239022,
    "exp": 1616325422,
    "roles": ["USER", "ADMIN"]
  }
  ```

- **Signature**: Ensures token integrity and authenticity
  ```
  HMACSHA256(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    secret
  )
  ```

**Implementation Flow**

**1. User Authentication**
```Java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    
    String token = jwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(new AuthResponse(token));
}
```

**2. JWT Token Generation**

```Java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
}
```

**3. JWT Filter for Token Validation**

```Java
String authHeader = request.getHeader("Authorization");

if (authHeader != null && authHeader.startsWith("Bearer ")) {
    String token = authHeader.substring(7);

    String username = jwtUtil.extractUsername(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
```

**4. Security Configuration**
```Java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
```

**Key Components**

1. **JWT Utility Class**: Handles token generation, validation, and extraction
2. **JWT Filter**: Intercepts requests to validate tokens
3. **Security Configuration**: Configures Spring Security with JWT
4. **Authentication Controller**: Handles login and token generation

**Advantages**

- **Stateless**: No server-side session storage required
- **Scalable**: Easy to scale across multiple servers
- **Cross-domain**: Works across different domains
- **Self-contained**: All necessary information is in the token

**Security Considerations**

- Use strong secret keys and store them securely
- Implement token expiration
- Use HTTPS for all communications
- Don't store sensitive data in JWT payload (it's only Base64 encoded)
- Consider implementing refresh tokens for better security

**Common Interview Questions**

**Q: JWT vs Session-based authentication?**
A: JWT is stateless (no server storage), while sessions require server-side storage. JWT is better for microservices and scalability.

**Q: How do you handle token expiration?**
A: Implement refresh tokens or require re-authentication when tokens expire.

**Q: Can JWT be revoked?**
A: JWT cannot be revoked by default. Implement token blacklisting or use short expiration times with refresh tokens.


## 12. What is CSRF Protection?

**CSRF (Cross-Site Request Forgery)** — attacker tricks a logged-in user's browser into sending an unwanted request to your server.

**Example attack:**
- User is logged into `bank.com`
- Attacker sends a link: `<img src="http://bank.com/transfer?to=attacker&amount=1000">`
- Browser auto-sends the request with the user's cookies → money transferred

**How CSRF Token fixes it:**
- Server generates a unique token per session
- Every form/request must include this token
- Server validates it — attacker can't guess it


**Step 1: Add Spring Security dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**Step 2: CSRF is ENABLED by default in Spring Security**
```java
// Spring Security enables CSRF protection automatically
// No extra config needed for traditional form-based apps
```

**Step 3: For REST APIs — disable CSRF (stateless, uses JWT)**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())          // disable for stateless REST APIs
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
```

**Step 4: For form-based apps — include CSRF token in forms (Thymeleaf does it auto)**
```html
<!-- Thymeleaf auto-injects CSRF token -->
<form th:action="@{/submit}" method="post">
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
    <button type="submit">Submit</button>
</form>
```

**Interview Answer:**
"CSRF is enabled by default in Spring Security. For REST APIs with JWT, I disable it since requests are stateless and don't rely on cookies. For form-based apps, Spring Security + Thymeleaf handles CSRF tokens automatically."


## 13. What is XSS Protection?

**XSS (Cross-Site Scripting)** — attacker injects malicious JavaScript into your web page, which runs in other users' browsers.

**Example attack:**
```
User submits comment: <script>document.cookie</script>
Server stores it → next user loads page → script runs → cookie stolen
```

**Types:**
- **Stored XSS** — malicious script saved in DB, served to all users
- **Reflected XSS** — script in URL, reflected back in response
- **DOM-based XSS** — script manipulates DOM directly

**Step 1: Add security headers via Spring Security**
```java
@Bean
// filterChain 
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .headers(headers -> headers
            .xssProtection(xss -> xss.enable())                        // X-XSS-Protection header
            .contentSecurityPolicy(csp ->
                csp.policyDirectives("script-src 'self'"))             // CSP header
        );
    return http.build();
}
```

**Step 2: Sanitize user input using OWASP Java HTML Sanitizer**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.googlecode.owasp-java-html-sanitizer</groupId>
    <artifactId>owasp-java-html-sanitizer</artifactId>
    <version>20220608.1</version>
</dependency>
```

```java
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@Service
public class SanitizationService {

    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public String sanitize(String input) {
        return POLICY.sanitize(input);   // strips <script> tags etc.
    }
}
```

**Step 3: Encode output in Thymeleaf (auto-escapes by default)**
```html
<!-- Thymeleaf escapes HTML by default — safe -->
<p th:text="${userComment}"></p>

<!-- th:utext is UNSAFE — renders raw HTML, avoid it -->
<p th:utext="${userComment}"></p>
```

**Step 4: Use Content Security Policy (CSP) header**
```java
.contentSecurityPolicy(csp ->
    csp.policyDirectives("default-src 'self'; script-src 'self'; object-src 'none'"))
```

**Interview Answer:**
> "I prevent XSS by: 1) enabling X-XSS-Protection and CSP headers via Spring Security, 2) sanitizing user input with OWASP HTML Sanitizer before storing, 3) using Thymeleaf which auto-escapes output by default."


## 14. What is Input Validation?

**Input Validation** — ensuring data received from the user is correct, safe, and expected before processing it.

**Why it matters:**
- Prevents SQL Injection, XSS, buffer overflows
- Ensures business rules are enforced (e.g., age > 0)
- Fails fast before bad data reaches DB


**Step 1: Add validation dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

**Step 2: Annotate your DTO/model with constraints**
```java
public class UserRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be under 120")
    private int age;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    // getters + setters
}
```

**Step 3: Enable validation in controller with `@Valid`**
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request) {
        // if validation fails, MethodArgumentNotValidException is thrown automatically
        return ResponseEntity.ok("User created");
    }
}
```

**Step 4: Handle validation errors globally**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
```

**Step 5: Response when validation fails**
```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "age": "Age must be at least 18"
}
```


## 15: What is Filter Chain?

> A Filter Chain is a sequence of filters that process an HTTP request before it reaches the controller and process the response before it is sent back to the client.
>
> Each filter performs a specific task, such as authentication, authorization, logging, CSRF protection, or JWT validation.

**How It Works**

> When a request arrives, it passes through multiple filters one by one. If all checks are successful, the request reaches the controller. After the controller generates a response, the response can also pass through filters before being returned to the client.

```text
Client Request
      │
      ▼
JWT Filter
      ▼
Authentication Filter
      ▼
Authorization Filter
      ▼
Controller
      ▼
Response
```

**Example**

> For example, in a JWT-based application, the request first goes through a JWT filter to validate the token. Then Spring Security checks the user's authentication and permissions. If everything is valid, the request is allowed to reach the controller.

**Why It Is Important**

* Authentication
* Authorization
* JWT Validation
* CSRF Protection
* Request/Response Logging
* Security Checks

**Custom Filter Example**

```java
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Request URI: " + request.getRequestURI());

        filterChain.doFilter(request, response);
    }
}
```

**Add Filter to Spring Security**

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.addFilterBefore(
            loggingFilter,
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
```


## 16: What is SAML?

**SAML (Security Assertion Markup Language)** is an **XML-based standard** for exchanging authentication data.

It enables **Single Sign-On (SSO)** between an **Identity Provider (IdP)** and a **Service Provider (SP)** using **security assertions**, and is widely used in **enterprise environments**.

```java
// SAML configuration with Spring Security
@Configuration
@EnableWebSecurity
public class SamlConfig {
    
    @Bean
    public SAMLAuthenticationProvider samlAuthenticationProvider() {
        SAMLAuthenticationProvider provider = new SAMLAuthenticationProvider();
        provider.setUserDetails(samlUserDetailsService());
        return provider;
    }
    
    @Bean
    public MetadataManager metadata() throws Exception {
        List<MetadataProvider> providers = new ArrayList<>();
        providers.add(idpMetadata());
        return new CachingMetadataManager(providers);
    }
    
    @Bean
    public ExtendedMetadata extendedMetadata() {
        ExtendedMetadata metadata = new ExtendedMetadata();
        metadata.setIdpDiscoveryEnabled(true);
        metadata.setSignMetadata(false);
        return metadata;
    }
}

// SAML assertion processing
@Component
public class SamlUserDetailsService implements SAMLUserDetailsService {
    public Object loadUserBySAML(SAMLCredential credential) {
        String username = credential.getNameID().getValue();
        List<String> roles = credential.getAttributeAsStringArray("Role");
        return new SamlUser(username, roles);
    }
}
```

# ✅ 24. Java Performance and Optimization


## 0: How do you measure and Monitor application performance in Java?

**Application monitoring** is the continuous tracking of an application's **performance, health, and behavior in production**.

It includes monitoring metrics like response time, errors, CPU, and memory, along with logs and traces, using tools like **Prometheus**, **Grafana**, **New Relic**, **Datadog**, and **AppDynamics** to detect and resolve issues proactively.

**Production Monitoring Tools (Simple Detailed Table)**

| Tool                 | Type                | Why We Use It                                 | What It Monitors                                      | Example                     |
| -------------------- | ------------------- | --------------------------------------------- | ----------------------------------------------------- | --------------------------- |
| Prometheus           | Metrics             | Collect metrics from application              | CPU, Memory, Request count, Error rate, Response time | API response time = 200ms   |
| Grafana              | Dashboard           | Show metrics in graphs                        | Dashboards, Alerts, Charts                            | CPU usage graph             |
| Micrometer           | Metrics Library     | Send metrics from Spring Boot to Prometheus   | JVM, Custom metrics                                   | Heap memory                 |
| ELK Stack            | Logging             | Store and search logs                         | Error logs, Application logs                          | Exception logs              |
| Splunk               | Logging / Analytics | Advanced log analysis & monitoring            | Logs, Events, Security data                           | Payment failure logs        |
| Spring Boot Admin    | Monitoring          | Monitor Spring Boot apps                      | Health, Beans, Endpoints                              | App status UP/DOWN          |
| Spring Boot Actuator | Monitoring          | Expose application health & metrics endpoints | Health, Metrics, Info, Thread dump                    | `/actuator/health`          |
| Zipkin               | Tracing             | Track request flow between services           | Service call flow                                     | Order → Payment → Inventory |
| Jaeger               | Tracing             | Track microservice request                    | API calls                                             | Request time per service    |
| Datadog              | APM                 | Full system monitoring                        | Infra, Logs, APIs                                     | Server CPU                  |
| New Relic            | APM                 | Application performance monitoring            | Slow API, DB calls                                    | Slow query                  |
| Dynatrace            | APM                 | AI-based full stack monitoring                | Full stack                                            | Root cause detection        |
| AWS CloudWatch       | Cloud               | Monitor AWS services                          | EC2, RDS, Logs                                        | EC2 CPU                     |



**What We Monitor in Production (Simple Table)**

| Area          | What We Monitor           | Example Alert       |
| ------------- | ------------------------- | ------------------- |
| Server        | CPU, Memory, Disk         | CPU > 80%           |
| Application   | Request count, Error rate | Error rate > 5%     |
| API           | Response time             | API > 3 sec         |
| Database      | Query time                | Query > 2 sec       |
| JVM           | Heap memory, GC           | Memory > 80%        |
| Logs          | Errors                    | Too many exceptions |
| Microservices | Service response          | Service down        |
| Business      | Orders, Payments          | Payment failure     |


## 1. What are performance issues and How to Improve Performance(optimize)?

Common **Java performance issues :** 

* **OutOfMemoryError :** - This happens when the JVM heap memory is full and cannot allocate new objects.
* **Memory Leaks** – Objects stay in memory and are not removed by the Java Garbage Collector, increasing memory usage over time.
* **CPU Bottlenecks / Inefficient Algorithms** – Poor algorithms or unnecessary loops increase CPU usage and slow the application.
* **Database Issues** – Slow queries or poor connection pool management delay database responses.
* **Thread Contention** – Multiple threads compete for the same resource, causing delays and blocking.
* **Too Many Object Creations** – Creating many objects increases memory usage and garbage collection work.
* **Garbage Collection Overhead** – Frequent garbage collection pauses the application and affects performance.
* **Metaspace issues :** - In some applications (like servers), classes loaded by a ClassLoader are not released, causing Metaspace memory issues. Too many classes loaded
* **Improper Cache Management :** - If caching is implemented without limits, cached objects can keep growing and consume memory.
* **Blocking I/O Operations** – File, network, or API calls block threads and reduce application throughput.


```java
// Memory leak example
public class LeakExample {
    private static List<String> cache = new ArrayList<>();
    
    public void addToCache(String data) {
        cache.add(data); // Never cleared - memory leak
    }
}
```

**Steps to Improve Performance(optimize)**

Here are **key points with one-line explanations** for improving performance in a **Spring Boot application**:

1. **Optimize Database Queries** – Write efficient queries, use indexes, and avoid unnecessary joins to reduce database load.
2. **Use Caching** – Store frequently accessed data in cache (e.g., **Redis**) to reduce repeated database calls.
3. **Enable Connection Pooling** – Use connection pools like **HikariCP** to reuse database connections efficiently.
4. **Use Pagination** – Load data in smaller chunks instead of fetching large datasets at once.
5. **Enable Asynchronous Processing** – Use `@Async` to execute time-consuming tasks in background threads.
6. **Avoid N+1 Query Problem** – Use proper fetching strategies in **Hibernate** to prevent multiple unnecessary queries.
7. **Use DTOs Instead of Entities** – Transfer only required fields instead of full entity objects.
8. **Enable HTTP Compression** – Compress API responses to reduce network payload and improve response time.
9. **Reduce Logging in Production** – Use appropriate log levels to avoid performance overhead.
10. **Monitor Application Performance** – Use tools like **Spring Boot Actuator** to identify bottlenecks.
11. **Optimize Thread Pool Configuration** – Configure server thread pools to handle concurrent requests efficiently.
12. **Use Lazy Initialization** – Load objects only when needed to reduce memory usage and startup time.

**Tools Used:**

* VisualVM
* JConsole
* Eclipse Memory Analyzer

**I usually:**

1. Check heap usage.
2. Analyze GC logs.
3. Take heap dumps.
4. Find large object retainers and memory leaks.


## 3. What are Java Memory Leak Issues?


1. **Static Collections** Objects stored in static lists or maps are never released.
2. **Improper Cache Management*** Cache entries grow indefinitely without TTL or eviction policies.
3. **Unclosed Resources** Database connections, streams, or files are not properly closed.
4. **ThreadLocal Misuse** Values remain attached to pooled threads if not removed.
5. **Event Listeners** Registered listeners are not deregistered, keeping objects alive.

**Symptoms**

* Increasing heap memory usage
* Frequent Full GC
* Application slowdown
* High memory consumption
* `OutOfMemoryError`

**Detection**

I usually analyze:

* Heap dumps
* GC logs
* Object retention paths

**Tools:**

* VisualVM
* Eclipse Memory Analyzer
* JConsole

**Prevention**

* Remove unused references.
* Close resources using try-with-resources.
* Configure cache eviction policies.
* Clean up ThreadLocal variables.
* Monitor heap usage regularly.


## 3. What are common 10 Production Issues?

**1. 🔴 Memory Leaks**
**Symptom:** Heap memory keeps growing over time, leads to frequent Full GC and `OutOfMemoryError`

**Causes:**
- Unreleased object references
- Static collections holding objects
- `ThreadLocal` leaks


**2. 🗄️ Connection Pool Exhaustion**
**Symptom:** All DB connections are used up; requests start waiting and eventually fail

**Causes:**
- Too many concurrent DB requests
- Connections not released properly
- Pool size too small for the load


**3. ⏱️ High GC Pause Time**
**Symptom:** Application becomes slow or unresponsive

**Causes:**
- JVM spends too much time in Garbage Collection
- Large heap with too many short-lived objects
- Wrong GC algorithm for the workload


**4. 🔒 Deadlocks**
**Symptom:** No progress in the system; threads hang forever

**Causes:**
- Two or more threads waiting for each other's locks
- Inconsistent lock acquisition order
- `T1` holds lock A waiting for B, `T2` holds lock B waiting for A


**5. 🧵 Thread Pool Starvation**
**Symptom:** New tasks keep waiting in the queue indefinitely

**Causes:**
- All worker threads are busy or blocked
- Pool size too small
- Blocking I/O inside thread pool tasks


**6. 🐢 Slow SQL Queries**
**Symptom:** Increased response time, locked tables, degraded throughput

**Causes:**
- Unoptimized or inefficient queries
- Missing indexes
- Full table scans on large datasets


**7. 📨 Kafka Consumer Lag**
**Symptom:** Consumers can't keep up with incoming messages; data delays increase

**Causes:**
- Consumers too slow to process messages
- Insufficient consumer instances
- Heavy processing logic inside consumers


**8. 📈 CPU Spikes**
**Symptom:** Overall system performance degrades suddenly

**Causes:**
- Infinite loops in code
- Heavy or excessive logging
- Bad algorithms with high time complexity
- GC thrashing


**9. 🌊 Cascading Failures**
**Symptom:** System instability spreads across multiple services

**Causes:**
- One failing service impacts multiple downstream services
- No circuit breakers in place
- Retry storms amplifying the failure


**10. 🔕 Missing Monitoring & Alerts**
**Symptom:** Issues exist but nobody notices early; small problems become major outages

**Causes:**
- No alerting configured for key metrics
- Lack of observability (logs, metrics, traces)
- No dashboards tracking system health


## 3. What are Java concurrency issues?

Common **Java concurrency issues** occur when multiple threads work on shared resources without proper coordination. This can cause incorrect results, slow performance, or application crashes.

1. **race condition :** -  happens when multiple threads access and modify shared data at the same time, and the final result depends on the order of execution.
2. **Deadlock :** -   occurs when two or more threads are waiting for each other’s resources, and none of them can proceed.
3. **Thread Starvation :** -  happens when a thread does not get enough CPU time because other threads with higher priority keep running.
4. **Livelock :** -  threads keep responding to each other and changing states, but no thread makes progress.
5. **Thread Contention :** -  This happens when multiple threads try to access the same resource simultaneously, causing threads to wait and reducing performance.
6. **Visibility Issues :** -  Changes made by one thread may **not be visible to other threads** due to CPU caching. Solution often involves using `volatile` or synchronization.
7. **Improper Synchronization**
Using too many or incorrect `synchronized` blocks can lead to **performance issues or inconsistent data**.


```java
// Race condition fix
private volatile boolean flag = false;
private final Object lock = new Object();

public void safeMethod() {
    synchronized(lock) {
        // Thread-safe operation
        flag = !flag;
    }
}
```


## 4: What is JVM tuning and parameters for performance tuning?

**JVM tuning** is the process of **optimizing JVM settings** for better performance.

It includes configuring **heap size (-Xms, -Xmx)**, selecting the right **GC algorithm**, adjusting **thread stack and metaspace**, tuning **GC parameters**, and using **monitoring tools and GC logs**.

* **Memory**: -Xms, -Xmx for heap; -XX:NewRatio for young/old generation
* **Garbage Collection**: -XX:+UseG1GC, -XX:+UseZGC, -XX:+UseConcMarkSweepGC
* **GC Tuning**: -XX:MaxGCPauseMillis, -XX:GCTimeRatio
* **Compilation**: -XX:+TieredCompilation, -XX:CompileThreshold
* **Monitoring**: -XX:+PrintGC, -XX:+FlightRecorder
* **Debug**: -XX:+HeapDumpOnOutOfMemoryError

```bash
# Performance-focused JVM parameters
# For high-throughput applications
-Xms8g -Xmx8g
-XX:+UseParallelGC
-XX:ParallelGCThreads=8
-XX:+UseCompressedOops

# For low-latency applications
-Xms4g -Xmx4g
-XX:+UseZGC
-XX:+UnlockExperimentalVMOptions

# For microservices
-Xms512m -Xmx1g
-XX:+UseG1GC
-XX:MaxGCPauseMillis=50
-XX:+UseStringDeduplication
```

## 5. What is Distributed Tracing?

**Distributed Tracing** is a technique used to track a request as it travels through multiple **Microservices**. It helps identify where delays, errors, or performance issues occur in a distributed system.

When a request enters the system, a unique **Trace ID** is created. Every service involved in processing that request uses the same Trace ID, making it easy to follow the entire request flow.

**Why is Distributed Tracing Important?**

* Helps with **Troubleshooting**
* Identifies **Performance Bottlenecks**
* Tracks requests across multiple services
* Improves **Observability**
* Helps debug failures in **Microservices**

**Key Concepts**

* **Trace** – Complete journey of a request.
* **Trace ID** – Unique identifier for the entire request.
* **Span** – A single operation within a trace.
* **Span ID** – Unique identifier for a span.

Suppose a request goes through:

```text
API Gateway
   |
Order Service
   |
Payment Service
   |
Notification Service
```

All services share the same **Trace ID**:

```text
Trace ID: abc123

Order Service        Span ID: 1
Payment Service      Span ID: 2
Notification Service Span ID: 3
```

This allows us to see exactly where time is spent.

**Spring Boot Example with Micrometer Tracing**

**Maven Dependency**

```xml id="jzptg7"
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

**Service Class**

```java id="d36xf8"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    public void createOrder() {
        logger.info("Creating order");
        // Business Logic
        logger.info("Order created successfully");
    }
}
```

**application.properties**

```properties id="6fzpxt"
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans
```

**Sample Log Output**

```text
2026-06-09 10:00:00
[traceId=abc123 spanId=xyz789]
Creating order
```

The **Trace ID** and **Span ID** automatically appear in logs, making it easy to track requests across services.

**Common Tools**

* **OpenTelemetry**
* **Zipkin**
* **Jaeger**
* **Micrometer Tracing**
* **Spring Boot Actuator**


## 5. What is Zipkin and How Does Distributed Tracing Work?

> In a microservices architecture, a single user request often travels through multiple services. If the request is slow or fails, it can be difficult to identify which service caused the problem.
>
> **Zipkin** is a distributed tracing tool that helps track a request as it moves across different microservices. It provides end-to-end visibility into the request flow and helps identify performance bottlenecks and failures.

**How Distributed Tracing Works**

> When a request enters the system, a unique **Trace ID** is generated. As the request passes through multiple services, each service creates its own **Span**, which contains information such as start time, end time, duration, and status.
>
> All spans share the same Trace ID, allowing Zipkin to connect them and display the complete request journey.

```text
Client Request
      │
      ▼
Service A ──► Service B ──► Service C
  Span 1        Span 2        Span 3

      Same Trace ID: abc123
```

> Suppose a request takes 5 seconds to complete. Zipkin can show:
>
> * Service A took 200 ms
> * Service B took 4.5 seconds
> * Service C took 300 ms
>
> From this trace, we can immediately identify that Service B is the bottleneck.

**Spring Boot 3+**

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
```

```yaml
management:
  tracing:
    sampling:
      probability: 1.0
```


## 6: What is profiling in Java?

**Profiling in Java** is the process of **analyzing application performance** to find bottlenecks.

It includes **CPU, memory, and thread profiling**, using tools like **JProfiler, YourKit, VisualVM, and Java Flight Recorder**, with approaches like **sampling and instrumentation**.

* Process of analyzing application performance to identify bottlenecks
* **CPU Profiling**: Identifies methods consuming most CPU time
* **Memory Profiling**: Tracks memory allocation and garbage collection
* **Thread Profiling**: Analyzes thread behavior and synchronization
* **Tools**: JProfiler, YourKit, VisualVM, Java Flight Recorder
* **Sampling vs Instrumentation**: Different profiling approaches

```java
// Java Flight Recorder (JFR) profiling
// JVM flags for profiling
// -XX:+FlightRecorder
// -XX:StartFlightRecording=duration=60s,filename=profile.jfr

@Component
public class ProfiledService {
    
    // Custom JFR event
    @JfrEvent(name = "UserOperation")
    public void processUser(User user) {
        // Method will be tracked in JFR
        expensiveOperation(user);
    }
    
    // Method that might need profiling
    public List<String> processLargeDataset(List<String> data) {
        return data.stream()
            .filter(this::isValid)
            .map(this::transform)
            .collect(Collectors.toList());
    }
}
```


## 7: What is memory profiling?

**Memory profiling** is the analysis of an application's **memory usage and allocation patterns**.

It helps identify **heap usage, object retention, memory leaks**, and uses tools like **Eclipse MAT, JProfiler, VisualVM**, along with **heap dumps** for detailed analysis.

* Analysis of application memory usage patterns and allocation
* **Heap Analysis**: Object allocation, retention, and garbage collection
* **Memory Leaks**: Identify objects that aren't being garbage collected
* **Allocation Patterns**: Track where and how objects are created
* **Tools**: Eclipse MAT, JProfiler, VisualVM, JConsole
* **Heap Dumps**: Snapshots of memory for offline analysis


## 8: What is CPU profiling?

**CPU profiling** is the analysis of **CPU usage** to find performance hotspots.

It tracks **time spent in methods, call hierarchy**, uses **sampling or instrumentation**, and tools like **JProfiler, async-profiler, and Java Flight Recorder** to identify bottlenecks.

* Analysis of CPU usage to identify performance hotspots
* **Method Profiling**: Time spent in each method
* **Call Tree**: Method call hierarchy and execution paths
* **Sampling**: Periodic snapshots of thread stacks
* **Instrumentation**: Detailed method entry/exit tracking
* **Flame Graphs**: Visual representation of CPU usage
* **Tools**: JProfiler, async-profiler, Java Flight Recorder



## 9: What is application performance monitoring (APM)?

**Application Performance Monitoring (APM)** is the **real-time monitoring of application performance in production**.

It tracks **metrics, errors, distributed tracing, user experience, and infrastructure performance**, using tools like **New Relic, AppDynamics, Dynatrace, and Elastic APM**.


```java
// APM integration with Micrometer
@Configuration
public class ApmConfig {
    
    @Bean
    public MeterRegistry meterRegistry() {
        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }
    
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}

@RestController
public class MonitoredController {
    
    @Timed(name = "api.requests", description = "API request duration")
    @Counted(name = "api.calls", description = "API call count")
    @GetMapping("/api/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("data");
    }
}
```


## 11: What is database optimization?

**Database optimization** is the process of improving database performance and query speed.

It involves **proper indexing, writing efficient SQL queries, using connection pooling, caching, and good database design** to reduce load and improve response time.


```java
// Database optimization techniques
@Repository
public class OptimizedUserRepository {
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);
    
    // Batch operations
    @Modifying
    @Query("UPDATE User u SET u.lastLogin = :now WHERE u.id IN :ids")
    void updateLastLogin(@Param("ids") List<Long> ids, @Param("now") LocalDateTime now);
    
    // Pagination for large datasets
    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    Page<User> findAllUsers(Pageable pageable);
    
    // Fetch joins to avoid N+1 queries
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.orders WHERE u.id = :id")
    User findUserWithOrders(@Param("id") Long id);
}
```


## 12: What is query optimization?

**Query optimization** is the process of improving SQL query performance so that data is retrieved faster and database resources are used efficiently.

Common techniques include creating proper indexes, writing efficient JOIN and WHERE clauses, avoiding SELECT *, fetching only required data, using pagination for large datasets, and analyzing execution plans.

For example, if users are frequently searched by email, adding an index on the email column can significantly reduce query execution time.

```java
// Query optimization examples
@Repository
public class OptimizedQueryRepository {
    
    // Bad: N+1 query problem
    // List<Order> orders = orderRepository.findAll();
    // orders.forEach(order -> order.getCustomer().getName()); // N queries
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);

    // Good: Single query with join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer")
    List<Order> findAllOrdersWithCustomers();
    
    // Use specific columns instead of SELECT *
    @Query("SELECT new com.example.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDto> findUserSummaries();
    
    // Optimize with proper WHERE conditions
    @Query("SELECT u FROM User u WHERE u.active = true AND u.createdAt > :date")
    List<User> findActiveUsersAfter(@Param("date") LocalDateTime date);
    
    // Use native query for complex optimizations
    @Query(value = "SELECT * FROM users u WHERE u.score > (SELECT AVG(score) FROM users)", 
           nativeQuery = true)
    List<User> findAboveAverageUsers();
}
```


## 13: What is lazy loading?


**Lazy Loading** is a technique where data or objects are **loaded only when they are actually needed**, instead of loading them immediately. It helps improve **performance** and reduces unnecessary memory usage.

**Key Features**

* Loads data **on demand**.
* Improves **application performance**.
* Reduces **memory consumption**.
* Decreases initial loading time.
* Commonly used in **Hibernate/JPA** relationships.

**How It Works**

1. The main object is loaded first.
2. Related objects are not fetched immediately.
3. When the related data is accessed, Hibernate executes a query and loads it.
4. Data is fetched only when required.

**Why Use Lazy Loading?**

* Improves performance by avoiding unnecessary database calls.
* Reduces memory usage.
* Faster application startup and response time.
* Efficient for large object graphs.

**When to Use**

* Large datasets.
* Relationships that are not always needed.
* Performance-sensitive applications.
* One-to-Many and Many-to-Many associations.

**Code Example**

```java
// Lazy loading examples
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    
    // Lazy loading - orders loaded only when accessed
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;
    // fetch = FetchType.LAZY means the related child data is not loaded immediately. It is fetched only when we access it.
    
    // Eager loading - always loaded with user
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
}

@Service
public class UserService {
    
    // Lazy loading in action
    public void processUser(Long userId) {
        User user = userRepository.findById(userId);
        // Orders not loaded yet
        
        if (needsOrders(user)) {
            user.getOrders().size(); // Now orders are loaded
        }
    }
    
    // Avoid N+1 with explicit fetch
    public List<User> getUsersWithOrders() {
        return userRepository.findAllWithOrders(); // Single query with JOIN FETCH
    }
}
```

**Lazy vs Eager Loading**

| **Lazy Loading**                   | **Eager Loading**                       |
| ---------------------------------- | --------------------------------------- |
| Loads data when needed             | Loads data immediately                  |
| Better performance for unused data | Can load unnecessary data               |
| Lower memory usage                 | Higher memory usage                     |
| Default for collections in JPA     | Often used when data is always required |


## 14: What is eager loading?


**Definition**

**Eager Loading** is a technique where related data is **loaded immediately** along with the main entity in a **single query** or as soon as the entity is fetched.

**Key Features**

* **Loads related objects instantly**
* Reduces the **N+1 Query Problem**
* Improves performance when related data is definitely needed
* May load **extra data** that is not used

**How It Works**

When the main entity is fetched, its associated entities are fetched at the same time.

Example:

If you load an **Employee**, the related **Department** is also loaded immediately.

**Why Use It?**

* Reduces the number of database queries
* Improves performance for frequently accessed relationships
* Avoids additional database hits later

**When to Use**

* When related data is **always required**
* For reports, dashboards, and detailed views
* To avoid repeated database queries

**Example (JPA/Hibernate)**
```java
// Eager loading examples
@Entity
public class Order {
    @Id
    private Long id;
    
    // Eager loading - customer always loaded with order
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

     // fetch = FetchType.EAGER means the related entity is loaded immediately along with the parent entity
    
    // Lazy loading - items loaded on demand
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;

     // fetch = FetchType.LAZY means the related child data is not loaded immediately. It is fetched only when we access it.
}

@Repository
public class OrderRepository extends JpaRepository<Order, Long> {
    
    // Explicit eager loading with fetch join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer JOIN FETCH o.items")
    List<Order> findAllOrdersWithDetails();
    
    // Conditional eager loading
    @EntityGraph(attributePaths = {"customer", "items"})
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatusWithDetails(@Param("status") OrderStatus status);
}
```


## 15: What is pagination?

**Pagination** is a technique used to split large datasets into **smaller chunks (pages)** instead of loading all data at once.

It improves **performance, memory usage, and user experience**, and is usually implemented using **LIMIT/OFFSET or cursor-based pagination**.

```java
// Pagination implementation
@RestController
public class UserController {
    
    // Basic pagination
    @GetMapping("/users")
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.findAll(pageable);
    }
    
    // Cursor-based pagination for better performance
    @GetMapping("/users/cursor")
    public List<User> getUsersCursor(
        @RequestParam(required = false) Long lastId,
        @RequestParam(defaultValue = "20") int limit) {
        
        return userService.findUsersAfter(lastId, limit);
    }
}

@Repository
public class UserRepository extends JpaRepository<User, Long> {
    
    // Cursor pagination query
    @Query("SELECT u FROM User u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id")
    List<User> findUsersAfter(@Param("lastId") Long lastId, Pageable pageable);
}
```

## 16. What is JIT compilation?



**JIT (Just-In-Time) Compilation** is a feature of the **JVM** that **converts bytecode into native machine code at runtime** to make Java programs faster.

Java normally works like this:

```
.java → compiled → .class (bytecode) → JVM → Machine Code → Run
```

**How JIT Works**

1. Java code compiled → Bytecode
2. JVM runs bytecode
3. JIT finds frequently used code (hotspot)
4. JIT converts it to machine code
5. Execution becomes faster


**JIT vs Interpreter**

| Interpreter            | JIT                      |
| ---------------------- | ------------------------ |
| Line by line execution | Compiles to machine code |
| Slower                 | Faster                   |
| Starts fast            | Gets faster over time    |

```java
// JIT compilation example
public class JITExample {
    public static void main(String[] args) {
        // This loop will trigger JIT compilation
        for (int i = 0; i < 100000; i++) {
            calculateSum(i); // Method becomes "hot" and gets compiled
        }
    }
    
    private static int calculateSum(int n) {
        return n * (n + 1) / 2; // Simple calculation
    }
}

// JIT compilation flags
java -XX:+PrintCompilation \      # Print compilation events
     -XX:CompileThreshold=1000 \  # Compilation threshold
     JITExample
```

# ✅ 25. Java Features 

## 1. What are the features in Java 8?

Java 8 was a major release that introduced functional programming features and significantly changed how Java code is written. It's one of the most important Java releases.

**Major Features:**
* **Lambda Expressions:** Anonymous functions used to write shorter and cleaner code.
* **Stream API:** Allows processing collections using operations like filter, map, and reduce.
* **Optional Class:** A container object used to handle possible null values safely.
* **Default Methods:** Methods in interfaces that have a default implementation.
* **Method References:** A shorter way to call existing methods using `::` syntax.
* **Date/Time API:** The `java.time` package provides improved classes for handling date and time.
* **Nashorn JavaScript Engine:** A Java engine that allows executing JavaScript code inside the JVM.
  

```java
// Lambda expressions
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.forEach(name -> System.out.println(name));

// Stream API
List<String> filtered = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Optional
Optional<String> optional = Optional.ofNullable(getString());
optional.ifPresent(System.out::println);

// Default methods in interfaces
interface MyInterface {
    default void defaultMethod() {
        System.out.println("This is a default method");
    }
}

// Static method reference
List<String> numbers = Arrays.asList("1", "2", "3");
List<Integer> integers = numbers.stream()
    .map(Integer::parseInt)  // Static method reference
    .collect(Collectors.toList());


// Instance method reference
List<String> names = Arrays.asList("john", "jane", "bob");
List<String> upperNames = names.stream()
    .map(String::toUpperCase)  // Instance method reference
    .collect(Collectors.toList());


// Constructor reference
List<String> strings = Arrays.asList("Hello", "World");
List<StringBuilder> builders = strings.stream()
    .map(StringBuilder::new)  // Constructor reference
    .collect(Collectors.toList());


```

## 2. What are the features in Java 11?

Java 11 is an LTS (Long Term Support) release that introduced several useful features and improvements, particularly for modern development practices.

**Key Features:**
- **Local Variable Type Inference (var):** Enhanced for lambda parameters
- **HTTP Client API:** Built-in HTTP client
- **String Methods:** isBlank(), lines(), strip(), repeat()
- **Files Methods:** readString(), writeString()
- **Collection.toArray():** Enhanced method
- **Nest-Based Access Control:** Better inner class access
- **Java Flight Recorder (JFR):** is a built-in profiling and monitoring tool in Java.

```java
// var in lambda parameters
List<String> names = Arrays.asList("John", "Jane");
names.stream()
    .map((var name) -> name.toUpperCase())
    .forEach(System.out::println);

// HTTP Client API
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

// New String methods
String text = "  Hello World  ";
System.out.println(text.strip());        // "Hello World"
System.out.println(text.isBlank());      // false
System.out.println("Java ".repeat(3));   // "Java Java Java "

// Files utility methods
String content = Files.readString(Paths.get("file.txt"));
Files.writeString(Paths.get("output.txt"), "Hello World");

// Collection.toArray():
List<String> names = List.of("John", "David", "Mike");
String[] arr = names.toArray(String[]::new); // Java 11 style
System.out.println(Arrays.toString(arr));
```

## 3. What are the features in Java 17?

Java 17 is the latest LTS release with several language enhancements and performance improvements, making Java more modern and developer-friendly.

**Major Features:**
- **Sealed Classes:** Restrict class inheritance
- **Pattern Matching for instanceof:** Simplified type checking
- **Records:** Immutable data classes
- **Text Blocks:** Multi-line string literals
- **Switch Expressions:** Enhanced switch statements
- **Helpful NullPointerExceptions:** Better error messages
- **Strong Encapsulation:** JDK internals encapsulated

```java
// Sealed classes
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
}

// Records - immutable data classes
public record Person(String name, int age) {
    // Automatically generates constructor, getters, equals, hashCode, toString
}

// Pattern matching for instanceof
if (obj instanceof String str) {
    System.out.println(str.toUpperCase()); // str is automatically cast
}

// Text blocks
String json = """
    {
        "name": "John",
        "age": 30,
        "city": "New York"
    }
    """;

// Switch expressions
String dayType = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};

// Enhanced switch with pattern matching (Preview)
String result = switch (obj) {
    case Integer i -> "Integer: " + i;
    case String s -> "String: " + s;
    case null -> "null value";
    default -> "Unknown type";
};
```

## 4. What is the Java release cycle and LTS versions?

Java moved to a 6-month release cycle in 2017, providing regular updates with new features while maintaining Long Term Support versions for enterprise stability.

**Release Cycle:**
- **6-month releases:** March and September each year
- **Feature releases:** Java 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21...
- **LTS releases:** Every 3 years (Java 8, 11, 17, 21...)
- **Support timeline:** LTS versions supported for 8+ years

**LTS Versions:**
- **Java 8 (2014):** Lambda expressions, Stream API - supported until 2030+
- **Java 11 (2018):** HTTP Client, var enhancements - supported until 2026+
- **Java 17 (2021):** Sealed classes, Records, Pattern matching - supported until 2029+
- **Java 21 (2023):** Latest LTS with Virtual Threads, Pattern matching

**Benefits of 6-month cycle:**
- **Faster innovation:** New features delivered quickly
- **Predictable releases:** Regular schedule for planning
- **Reduced risk:** Smaller changes per release
- **LTS stability:** Enterprise applications can stick to LTS versions

**Choosing Java Version:**
- **Enterprise applications:** Use LTS versions (8, 11, 17, 21)
- **New projects:** Consider latest LTS (Java 17 or 21)
- **Experimentation:** Try latest feature releases for new capabilities
- **Migration strategy:** Plan upgrades around LTS releases


# ✅ 26. Java SQL

## 1. What is SQL?

**SQL (Structured Query Language)** is the standard language used to communicate with a **Relational Database**. It is used to **store**, **retrieve**, **update**, and **delete** data from database tables.

Databases such as MySQL, Oracle Database, PostgreSQL, and Microsoft SQL Server use SQL.


## 1. What are the Types of SQL JOINs?

JOINs combine rows from two or more tables based on a related column.

- **INNER JOIN** — returns only matching rows from both tables
- **LEFT JOIN** — returns all rows from the left table + matching rows from right (nulls if no match)
- **RIGHT JOIN** — returns all rows from the right table + matching from left
- **FULL OUTER JOIN** — returns all rows from both tables (nulls where no match)

```sql
-- INNER JOIN
SELECT e.name, d.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id;

-- LEFT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
LEFT JOIN department d ON e.dept_id = d.id;

-- RIGHT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
RIGHT JOIN department d ON e.dept_id = d.id;

-- FULL OUTER JOIN
SELECT e.name, d.name FROM employee e
FULL OUTER JOIN department d ON e.dept_id = d.id;
```

Think of it as a Venn diagram — INNER is the overlap, LEFT keeps the left circle, FULL keeps both circles.


## 2. What is the Difference Between WHERE and HAVING?

Both filter rows — but at different stages.

- **WHERE** — filters rows **before** grouping (works on raw rows)
- **HAVING** — filters groups **after** `GROUP BY` (works on aggregated results)

```sql
-- WHERE: filter before grouping
SELECT dept_id, COUNT(*) FROM employee
WHERE salary > 50000
GROUP BY dept_id;

-- HAVING: filter after grouping
SELECT dept_id, COUNT(*) as total FROM employee
GROUP BY dept_id
HAVING COUNT(*) > 5;
```

Simple rule: if you're filtering on an aggregate function like `COUNT`, `SUM`, `AVG` — use `HAVING`. Otherwise use `WHERE`.


## 3. What is GROUP BY and ORDER BY?

- **GROUP BY** — groups rows with the same value into summary rows. Used with aggregate functions like `COUNT`, `SUM`, `AVG`.
- **ORDER BY** — sorts the result set by one or more columns (ASC by default, or DESC).

```sql
-- GROUP BY: count employees per department
SELECT dept_id, COUNT(*) as total
FROM employee
GROUP BY dept_id;

-- ORDER BY: sort by salary descending
SELECT name, salary FROM employee
ORDER BY salary DESC;

-- Combined
SELECT dept_id, AVG(salary) as avg_sal
FROM employee
GROUP BY dept_id
ORDER BY avg_sal DESC;
```

`GROUP BY` collapses rows. `ORDER BY` just sorts them.


## 4. What is Database Indexing and When to Use It?

An index is like a book's table of contents — it lets the database find rows fast without scanning the whole table.

**When to use:**
- Columns in `WHERE`, `JOIN`, `ORDER BY`, `GROUP BY`
- Foreign key columns
- High-cardinality columns (many unique values like email, ID)

**When NOT to use:**
- Small tables
- Columns updated very frequently
- Low-cardinality columns (like a boolean `is_active`)

```sql
CREATE INDEX idx_employee_email ON employee(email);

-- Composite index
CREATE INDEX idx_dept_salary ON employee(dept_id, salary);
```

Indexes speed up reads but slow down writes (INSERT/UPDATE/DELETE). Use them wisely — don't over-index.


## 5. What is the Difference Between Stored Procedure and Aggregate Function?

**Stored Procedure** is a **precompiled SQL program** stored in the database. It can accept **parameters**, contain **business logic (IF, loops)**, and return results.

**Aggregate Functions** perform calculations on multiple rows and return a **single value**.
Common examples: `COUNT`, `SUM`, `AVG`, `MAX`, `MIN`.

| | Stored Procedure | Function |
|---|---|---|
| Returns | Optional (0 or more values via OUT params) | Must return a single value |
| Can use in SELECT | No | Yes |
| Can have DML (INSERT/UPDATE) | Yes | Limited (depends on DB) |
| Called with | `CALL` / `EXEC` | Used in expressions |
| Transaction control | Yes | No |

```sql
-- Stored Procedure
CREATE PROCEDURE get_employee(IN emp_id INT)
BEGIN
  SELECT * FROM employee WHERE id = emp_id;
END;

CALL get_employee(1);

-- Function
CREATE FUNCTION get_salary(emp_id INT) RETURNS DECIMAL
BEGIN
  DECLARE sal DECIMAL;
  SELECT salary INTO sal FROM employee WHERE id = emp_id;
  RETURN sal;
END;

SELECT get_salary(1);  -- used inline
```

Use a **function** when you need a return value in a query. Use a **procedure** for business logic, batch operations, or multiple operations.


## 6. What is Normalization? Types (1NF, 2NF, 3NF)?

Normalization is the process of organizing a database to **reduce redundancy** and **improve data integrity**.

**1NF (First Normal Form):**
- Each column has atomic (indivisible) values
- No repeating groups or arrays in a column

```
❌ Bad:  | id | name  | phones          |
         | 1  | Alice | 111, 222, 333   |

✅ Good: | id | name  | phone |
         | 1  | Alice | 111   |
         | 1  | Alice | 222   |
```

**2NF (Second Normal Form):**
- Must be in 1NF
- No partial dependency — every non-key column must depend on the **whole** primary key (applies to composite keys)

**3NF (Third Normal Form):**
- Must be in 2NF
- No transitive dependency — non-key columns must not depend on other non-key columns

```
❌ Bad:  | emp_id | dept_id | dept_name |
         dept_name depends on dept_id, not emp_id

✅ Fix: Split into employee(emp_id, dept_id) and department(dept_id, dept_name)
```

In practice, aim for 3NF. Sometimes you intentionally denormalize for performance.


## 7. What is the Difference Between DELETE, TRUNCATE, and DROP?

All three remove data — but very differently.

| | DELETE | TRUNCATE | DROP |
|---|---|---|---|
| Removes | Specific rows | All rows | Entire table + structure |
| WHERE clause | Yes | No | No |
| Rollback | Yes (logged) | No (or limited) | No |
| Triggers fired | Yes | No | No |
| Resets auto-increment | No | Yes | Yes |

```sql
DELETE FROM employee WHERE id = 5;     -- removes one row, can rollback

TRUNCATE TABLE employee;               -- removes all rows, fast, no rollback

DROP TABLE employee;                   -- removes table entirely
```

Use `DELETE` for selective removal. `TRUNCATE` to clear a table fast. `DROP` only when you want to remove the table completely.


## 8. What is a Subquery vs a JOIN?

Both retrieve related data — but differently.

**Subquery** — a query nested inside another query. Runs separately, result is used by the outer query.

```sql
-- Subquery: find employees earning more than average
SELECT name FROM employee
WHERE salary > (SELECT AVG(salary) FROM employee);
```

**JOIN** — combines rows from two tables in a single query execution.

```sql
-- JOIN: same result, often more efficient
SELECT e.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id
WHERE d.name = 'Engineering';
```

**When to use which:**
- Use **JOIN** when you need columns from multiple tables — it's generally faster
- Use **subquery** when the inner result is a single value or a filtered set that's hard to express as a JOIN
- Correlated subqueries (referencing outer query) can be slow — prefer JOIN or CTEs


## 9. What is a View in SQL?

A view is a **virtual table** — it's a saved SQL query that you can query like a table. It doesn't store data itself.

```sql
-- Create a view
CREATE VIEW high_salary_employees AS
SELECT name, salary, dept_id FROM employee
WHERE salary > 80000;

-- Query the view like a table
SELECT * FROM high_salary_employees;
```

**Benefits:**
- Simplifies complex queries — write once, reuse everywhere
- Security — expose only specific columns/rows to users
- Abstraction — hide underlying table structure

**Limitation:** A regular view doesn't store data. For performance, use a **Materialized View** (stores the result physically, needs refresh).


## 11. What are different types of **JOINs**?

**Answer:**

**JOIN** is used to combine rows from two or more tables based on a related column.

Main types:

* **INNER JOIN** – Returns only matching rows from both tables.
* **LEFT JOIN** – Returns all rows from left table and matching rows from right table.
* **RIGHT JOIN** – Returns all rows from right table and matching rows from left table.
* **FULL OUTER JOIN** – Returns all rows from both tables.
* **CROSS JOIN** – Returns Cartesian product (all possible combinations).


## 12. What is a **Primary Key** and **Foreign Key** ?

**PRIMARY KEY** – Ensures uniqueness and **cannot be NULL**. A table can have **only one** primary key.
**Foreign Key** is a column that **references the Primary Key of another table**.

## 12. What is the Difference Between UNION and UNION ALL?

Both combine results of two SELECT queries — but handle duplicates differently.

- **UNION** — combines results and **removes duplicates** (slower, does a distinct sort)
- **UNION ALL** — combines results and **keeps all rows including duplicates** (faster)

```sql
-- UNION: removes duplicates
SELECT name FROM employee_india
UNION
SELECT name FROM employee_us;

-- UNION ALL: keeps duplicates
SELECT name FROM employee_india
UNION ALL
SELECT name FROM employee_us;
```

**Rules for both:**
- Same number of columns in both SELECT statements
- Columns must have compatible data types

Use `UNION ALL` when you know there are no duplicates or you want all rows — it's faster because it skips the deduplication step.



## 12. What is SQL injection and how to prevent it?

**SQL Injection** is a **security vulnerability** where an attacker injects malicious SQL code into application queries, to **manipulate or access the database illegally**.

It can be prevented by using **Prepared Statements (Parameterized Queries)**, **input validation**, **ORM frameworks (like JPA/Hibernate)**, **stored procedures**, and **proper access control**.


```java
// Vulnerable code - SQL injection possible
String userId = request.getParameter("id");
String sql = "SELECT * FROM users WHERE id = " + userId;
// If userId = "1 OR 1=1", returns all users!

// Safe code - using PreparedStatement
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```


## 13. What is a Cursor in SQL and when should it be used ?

A **Cursor** is used to **process database rows one by one** instead of all at once.
It is useful when we need **row-by-row processing**, but it should be used carefully because it can be **slower than set-based queries**

**Why use it?**

* Prevents **OutOfMemoryError**
* Good for very large datasets
* Reduces heap usage


```java
DECLARE @name VARCHAR(50)

-- 1. Declare Cursor
DECLARE emp_cursor CURSOR FOR
SELECT name FROM employees

-- 2. Open Cursor
OPEN emp_cursor

-- 3. Fetch first row
FETCH NEXT FROM emp_cursor INTO @name

-- Loop through all rows
WHILE @@FETCH_STATUS = 0
BEGIN
    PRINT @name

    -- Fetch next row
    FETCH NEXT FROM emp_cursor INTO @name
END

-- 5. Close Cursor
CLOSE emp_cursor

-- 6. Deallocate Cursor
DEALLOCATE emp_cursor
```

```java
@Transactional
public void processProducts() {
    try (Stream<Product> stream = repo.findAllByStream()) {
        stream.forEach(product -> {
            // process record
        });
    }
}
```

## 14. What is Batch Processing?

Batch Processing is a technique where a large amount of data is processed in groups (batches) instead of processing one record at a time. It improves performance, reduces database calls, and is commonly used for data migration, report generation, payroll processing, and bulk updates.

Suppose you need to update **10,000 employee records**.

❌ One-by-one processing:

```java
for (Employee emp : employees) {
    employeeRepository.save(emp);
}
```

**✅ Batch processing:**

```java
List<Employee> employees = getEmployees();
employeeRepository.saveAll(employees);
```
The records are processed in batches, reducing database round trips.


**Spring Boot Batch Configuration**

```properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```
This allows Hibernate to send records to the database in batches of 50.

**Real-World Examples**

* Payroll processing
* Bank transaction settlement
* Data migration
* Bulk email sending
* Report generation



## 15. What is sharding in databases?

Sharding is a way to scale a database horizontally by dividing data into smaller pieces called shards. Each shard is stored in a separate database instance, which helps improve performance and handle large traffic.


Imagine a **users table** with millions of records:

Instead of storing everything in one DB:

* **Shard 1** → Users with ID 1–1,00,000
* **Shard 2** → Users with ID 1,00,001–2,00,000
* **Shard 3** → Users with ID 2,00,001–3,00,000

Each shard is stored on a different server.


**Why Sharding?**

* 🚀 Improves performance (queries run faster)
* 📈 Handles large-scale data
* ⚖️ Distributes load across servers


# ✅ 27. Java CI/CD and DevOp

## 1: What is continuous integration?

**Continuous Integration (CI)** is a development practice where developers **frequently merge code into a shared repository**, and each commit triggers an **automated build and test process**.

It helps detect bugs early, ensures code quality, and provides fast feedback using tools like **Jenkins, GitLab CI, or GitHub Actions**.

* Development practice of frequently integrating code changes into shared repository
* **Automated Builds**: Every commit triggers automated build and test
* **Early Detection**: Catch integration issues and bugs early
* **Fast Feedback**: Developers get quick feedback on code changes
* **Quality Gates**: Automated tests must pass before integration
* **Tools**: Jenkins, GitLab CI, GitHub Actions, Azure DevOps

```yaml
# GitHub Actions CI example
name: CI Pipeline
on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
    - name: Run tests
      run: ./mvnw test
    - name: Build application
      run: ./mvnw package
```


## 2: What is continuous deployment?

**Continuous Deployment (CD)** is a practice where code changes are **automatically deployed to production** after passing all tests.

It enables **fast and frequent releases**, reduces risk with small deployments, and requires strong automation, testing, monitoring, and rollback mechanisms.

* Automated deployment of code changes to production after passing all tests
* **Fully Automated**: No manual intervention in deployment process
* **Fast Delivery**: Features reach users quickly
* **Risk Mitigation**: Small, frequent deployments reduce risk
* **Rollback**: Quick rollback capabilities for issues
* **Prerequisites**: Requires robust testing, monitoring, and automation

```yaml
# CD Pipeline example
deploy:
  stage: deploy
  script:
    - docker build -t myapp:$CI_COMMIT_SHA .
    - docker push registry.com/myapp:$CI_COMMIT_SHA
    - kubectl set image deployment/myapp myapp=registry.com/myapp:$CI_COMMIT_SHA
  only:
    - main
  when: manual  # or 'on_success' for full automation
```


## 3: What is Jenkins?

**Jenkins** is an **open-source automation server** used to implement **CI/CD pipelines**.

It automates **build, test, and deployment processes**, supports pipeline as code using a *Jenkinsfile*, and integrates with tools like Git, Maven, Docker, and Kubernetes through plugins.

* Open-source automation server for CI/CD pipelines
* **Pipeline as Code**: Jenkinsfile defines build pipeline
* **Plugins**: Extensive plugin ecosystem for integrations
* **Distributed Builds**: Master-slave architecture for scalability
* **Web Interface**: User-friendly web-based configuration
* **Integration**: Integrates with Git, Maven, Docker, Kubernetes

```groovy
// Jenkinsfile example
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Package') {
            steps {
                sh './mvnw package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Deploy') {
            when { branch 'main' }
            steps {
                sh 'docker build -t myapp .'
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}
```


## 4: What is Git?

**Git** is a **distributed version control system** used to track and manage code changes.

It allows developers to **create branches, merge code, collaborate through remote repositories**, and maintains complete project history with high performance and data integrity.

* Distributed version control system for tracking code changes
* **Distributed**: Every developer has complete project history
* **Branching**: Lightweight branching and merging capabilities
* **Performance**: Fast operations for most commands
* **Integrity**: Cryptographic hashing ensures data integrity
* **Collaboration**: Enables team collaboration through remote repositories

```bash
# Basic Git commands
git init                          # Initialize repository
git add .                         # Stage changes
git commit -m "Add new feature"   # Commit changes
git branch feature-branch         # Create branch
git checkout feature-branch       # Switch branch
git merge feature-branch          # Merge branch
git push origin main              # Push to remote
git pull origin main              # Pull from remote
```


## 5: What is version control?

**Version control** is a system used to **track and manage changes to code or files over time**.

It allows multiple developers to collaborate, maintain version history, create branches, and revert to previous versions. It can be **centralized (like SVN) or distributed (like Git)**.

* System for tracking and managing changes to files over time
* **History**: Complete history of all changes and versions
* **Collaboration**: Multiple developers can work on same project
* **Branching**: Parallel development streams
* **Backup**: Distributed copies serve as backups
* **Rollback**: Ability to revert to previous versions
* **Types**: Centralized (SVN) vs Distributed (Git)

```bash
# Version control workflow
git status                    # Check current state
git log --oneline            # View commit history
git diff HEAD~1              # Compare with previous version
git checkout HEAD~2 -- file.java  # Restore file from 2 commits ago
git tag v1.0.0               # Tag release version
git revert abc123            # Revert specific commit
```


## 6: What is infrastructure as code?

**Infrastructure as Code (IaC)** is the practice of **managing and provisioning infrastructure using code instead of manual setup**.

It allows infrastructure to be **version-controlled, automated, and reproducible** across environments using tools like Terraform or CloudFormation.

* Managing and provisioning infrastructure through code rather than manual processes
* **Declarative**: Define desired state, tools ensure it's achieved
* **Version Control**: Infrastructure changes tracked like application code
* **Reproducible**: Consistent environments across dev, test, production
* **Automation**: Automated provisioning and configuration
* **Tools**: Terraform, CloudFormation, Ansible, Kubernetes manifests

```yaml
# Kubernetes deployment (IaC)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: java-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: java-app
  template:
    spec:
      containers:
      - name: app
        image: myapp:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: DATABASE_URL
          value: "jdbc:postgresql://db:5432/mydb"
```

```java
# Terraform example
resource "aws_instance" "web" {
  ami           = "ami-0c55b159cbfafe1d0"
  instance_type = "t2.micro"
  
  tags = {
    Name = "JavaApp"
  }
}
```


## 7: What is deployment strategies?

**Deployment strategies** are different approaches used to release applications to production safely and efficiently.

Common strategies include **Rolling, Blue-Green, Canary, Recreate, and Shadow deployments**, which help minimize downtime, reduce risk, and ensure smooth releases.

* Different approaches for releasing applications to production
* **Rolling Deployment**: Gradually replace old instances with new ones
* **Blue-Green**: Switch between two identical environments
* **Canary**: Deploy to small subset of users first
* **A/B Testing**: Compare different versions with user groups
* **Recreate**: Stop old version, start new version (downtime)
* **Shadow**: Route copy of traffic to new version for testing

```yaml
# Rolling deployment strategy
apiVersion: apps/v1
kind: Deployment
spec:
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 1
      maxSurge: 1
  replicas: 5
  template:
    spec:
      containers:
      - name: app
        image: myapp:v2.0.0
```

## 10: What is containerization?

**Containerization** is a technology that **packages applications with their dependencies** into isolated, portable containers.

Containers are **lightweight**, **consistent across environments**, **easy to scale**, and use platforms like **Docker, Podman, or containerd**.

* Technology that packages applications with their dependencies into containers
* **Isolation**: Applications run in isolated environments
* **Portability**: Containers run consistently across different environments
* **Lightweight**: Share OS kernel, more efficient than virtual machines
* **Scalability**: Easy to scale up/down container instances
* Popular platforms: Docker, Podman, containerd

```dockerfile
# Dockerfile for Java application
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```


## 11: What is Docker?

**Docker** is a **containerization platform** that allows developers to package an application along with its dependencies into a **container**. This container can run the same way across different environments like development, testing, and production.

Docker makes applications **lightweight, portable, fast to deploy**, and easier to scale compared to traditional virtual machines.

```bash
# Build and run Java application
docker build -t myapp:latest .
docker run -p 8080:8080 myapp:latest

# Docker Compose for multi-service setup
version: '3'
services:
  app:
    build: .
    ports: ["8080:8080"]
  db:
    image: mysql:8.0
```

## 12. What is Kubernetes?

**Kubernetes** is a **container orchestration platform** used to manage containerized applications at scale. It automates **deployment, scaling, load balancing, and self-healing** of containers across a cluster of machines.

It ensures applications remain **highly available, scalable, and resilient** in production environments.


- **Container orchestration:** Manages multiple containers
- **Automatic scaling:** Scale applications based on demand
- **Service discovery:** Containers can find and communicate
- **Load balancing:** Distributes traffic across containers
- **Self-healing:** Restarts failed containers automatically
- **Rolling updates:** Deploy new versions without downtime

**Key Components:**
- **Pods:** Smallest deployable units
- **Services:** Expose applications to network
- **Deployments:** Manage application replicas
- **ConfigMaps/Secrets:** Configuration management

```yaml
# Kubernetes deployment example
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
        image: myapp:latest
        ports:
        - containerPort: 8080
```

## 13. What is cloud computing?

**Cloud computing** is the delivery of computing resources like **servers, storage, databases, networking, and software** over the internet on a **pay-as-you-go** basis. Instead of owning physical infrastructure, you use resources provided by cloud platforms.

It allows easy **scaling, high availability, cost efficiency**, and faster application deployment.


**Service Models:**
- **IaaS (Infrastructure as a Service):** Virtual machines, storage, networks
- **PaaS (Platform as a Service):** Development platforms, databases
- **SaaS (Software as a Service):** Ready-to-use applications

**Deployment Models:**
- **Public Cloud:** AWS, Azure, Google Cloud
- **Private Cloud:** Organization's dedicated infrastructure
- **Hybrid Cloud:** Combination of public and private

**Benefits:**
- **Cost efficiency:** Pay for what you use
- **Scalability:** Scale resources up/down on demand
- **Accessibility:** Access from anywhere
- **Reliability:** High availability and disaster recovery

## 14. What is distributed system?

A **distributed system** is a system where multiple **independent computers or services work together** over a network and appear as a **single system** to the user.

These systems improve **scalability, fault tolerance, and availability**, since work is shared across multiple nodes instead of relying on a single machine.


**Characteristics:**
- **Multiple nodes:** Components run on different machines
- **Network communication:** Nodes communicate over network
- **Shared state:** Coordinate to maintain consistency
- **Fault tolerance:** Continue operating despite failures
- **Scalability:** Add more nodes to handle increased load

**Challenges:**
- **Network failures:** Partial failures and partitions
- **Consistency:** Keeping data synchronized across nodes
- **Latency:** Network communication delays
- **Complexity:** Debugging and monitoring difficulties

**Examples:**
- Microservices architectures
- Database clusters
- Content delivery networks (CDNs)
- Web applications with load balancers

## 15. What is load balancing?

**Load balancing** is the process of **distributing incoming requests** across multiple servers so that no single server becomes overloaded.

It improves **application performance, availability, and reliability** by ensuring traffic is handled efficiently, even when one server fails or traffic increases.


**Load Balancing Algorithms:**
- **Round Robin:** Requests distributed sequentially
- **Least Connections:** Route to server with fewest active connections
- **Weighted Round Robin:** Assign weights based on server capacity
- **IP Hash:** Route based on client IP hash
- **Health Check:** Only route to healthy servers

**Types:**
- **Layer 4 (Transport):** Routes based on IP and port
- **Layer 7 (Application):** Routes based on HTTP content
- **Hardware Load Balancers:** Dedicated physical devices
- **Software Load Balancers:** Nginx, HAProxy, AWS ALB

```java
# Nginx load balancer configuration
upstream backend {
    server backend1.example.com weight=3;
    server backend2.example.com weight=2;
    server backend3.example.com weight=1;
}

server {
    listen 80;
    location / {
        proxy_pass http://backend;
    }
}
```

## **16. How do you handle rollback strategies?**


In production deployments, rollback strategies are important in case a new release causes issues.
We usually use **CI/CD pipelines with versioned deployments**. If a deployment fails or causes errors, we quickly **roll back to the previous stable version**.

In Kubernetes or Docker environments, we use commands like **deployment rollback** to restore the last working version.
For database-related changes, we maintain **backup scripts and migration rollback scripts**.

This ensures the system is restored quickly with minimal downtime.


## **17. How do you manage database migrations?**


We manage database migrations using tools like **Flyway or Liquibase**.
These tools allow us to maintain **version-controlled SQL scripts** for schema changes.

During application startup or deployment, the migration tool automatically applies pending changes to the database.
This ensures **consistent schema across all environments like dev, QA, and production**.

```
**Step 1 → Add Dependency :: Maven**

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

**Step 2 → Configure Database :: application.yml**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: root

  flyway:
    enabled: true
```

**Step 3 → Create Migration Folder :: Create folder**

```txt
src/main/resources/db/migration
```

**Step 4 → Create SQL Migration File :: File name format**

```txt
V1__create_employee_table.sql
```

```txt
// Important:
V<version>__<description>.sql
```

**Step 5 → Add SQL :: V1__create_employee_table.sql**

```sql
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    salary DOUBLE
);
```

**Step 6 → Start Application :: When Spring Boot starts:**

```txt
Flyway automatically:
- checks migration history
- executes new scripts
- updates flyway_schema_history table
```

**Step 7 → Add New Migration :: Create new file:**

```txt
V2__add_email_column.sql
```

```sql
ALTER TABLE employee
ADD email VARCHAR(100);

Restart app → Flyway runs only V2.
```



**Internal Working**

```txt
Application Start
      ↓
Check flyway_schema_history
      ↓
Find pending migrations
      ↓
Execute SQL scripts
      ↓
Update migration history
```

**Generated Table :: Flyway creates:**

```txt
flyway_schema_history
```



## **18. How do you ensure zero downtime deployments?**


To ensure zero downtime deployments, we use **rolling deployments or blue-green deployments**.

In rolling deployment, new instances of the application are gradually started while old instances are terminated one by one.
This ensures that the system is always available.

In cloud environments like Kubernetes, we configure **readiness and liveness probes** so traffic is only sent to healthy pods.

**Key Techniques**

* Rolling Deployment
* Blue-Green Deployment
* Canary Deployment
* Load balancers
* Health checks


## **19. How do you implement auto-scaling, Horizontal and vertical scaling?**


Auto-scaling automatically increases or decreases the number of service instances based on traffic.

In Kubernetes, we use **Horizontal Pod Autoscaler (HPA)**, which scales pods based on metrics like **CPU usage or request count**.

In AWS, we configure **Auto Scaling Groups** to scale EC2 instances when traffic increases.

**Horizontal scaling** means adding more machines to handle load, Horizontal scaling is more scalable and fault-tolerant

**vertical scaling** means increasing the power of a single machine like CPU or RAM. vertical scaling is simpler but has hardware limits.

**Example**

```
min replicas = 2
max replicas = 10
scale when CPU 70%
```


## **20. What is Rate Limiting and how does it work? Where do you implement it?**


Rate limiting is used to **control how many requests a client can send to an API within a specific time period**.

It helps protect the system from **abuse, DDoS attacks, and excessive traffic**.

Rate limiting can be implemented at different levels:

* **API Gateway**
* **Load balancer**
* **Application layer**

For example, we can limit a user to **100 requests per minute**.
If the limit is exceeded, the API returns **HTTP 429 – Too Many Requests**.


# ✅ 28. Java Monitoring and Logging

## 1: What is logging framework?

A **logging framework** is a library that provides a structured way to record application events and errors.

It supports different **log levels (DEBUG, INFO, WARN, ERROR)**, configurable output destinations (console, file, etc.), and flexible formatting. Popular frameworks include **SLF4J**, **Apache Log4j**, **Logback**, and **java.util.logging**.

| Level | Purpose                         |
| ----- | ------------------------------- |
| DEBUG | Detailed debugging information  |
| INFO  | General application information |


```java
// Logging framework usage
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public User createUser(User user) {
        logger.info("Creating user with email: {}", user.getEmail());
        
        try {
            User created = userRepository.save(user);
            logger.info("User created successfully with ID: {}", created.getId());
            return created;
        } catch (Exception e) {
            logger.error("Failed to create user: {}", user.getEmail(), e);
            throw new UserCreationException("User creation failed", e);
        }
    }
}
```

## 2: What is Log4j?

**Apache Log4j** is a popular Java logging framework developed by the Apache Software Foundation.

It provides hierarchical loggers, multiple appenders (console, file, etc.), flexible configuration, and supports asynchronous logging for high-performance applications.

```xml
<!-- log4j2.xml configuration -->
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="FileAppender" fileName="logs/application.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"/>
        </File>
    </Appenders>
    
    <Loggers>
        <Logger name="com.example" level="DEBUG"/>
        <Root level="INFO">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="FileAppender"/>
        </Root>
    </Loggers>
</Configuration>
```


## 3: What is SLF4J?

**SLF4J** (Simple Logging Facade for Java) is a logging abstraction layer that provides a common API for different logging frameworks.

It allows you to switch the underlying implementation (like Log4j or Logback) without changing code and supports efficient, parameterized logging.

```java
// SLF4J usage
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Service
public class OrderService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    public Order processOrder(Order order) {
        // Add context to all log messages in this thread
        MDC.put("orderId", order.getId().toString());
        MDC.put("userId", order.getUserId().toString());
        
        try {
            logger.info("Processing order for user: {}", order.getUserId());
            
            // Parameterized logging - efficient
            logger.debug("Order details: amount={}, items={}", 
                order.getAmount(), order.getItems().size());
            
            Order processed = processOrderInternal(order);
            logger.info("Order processed successfully");
            return processed;
            
        } finally {
            MDC.clear(); // Clean up context
        }
    }
}
```


## 4: What is Logback?

**Logback** is a logging framework and the native implementation of SLF4J, designed as the successor to Log4j.
It provides **better performance, flexible configuration, and is the default logging framework in Spring Boot.**

**Simple Configuration :**
```xml
// logback-spring.xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss} %-5level %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
```


## 5: What is structured logging?

**Structured logging** is a logging approach where logs are written in a **machine-readable format** (like JSON) using **key-value pairs** instead of plain text.

It improves searchability, filtering, and tracing in monitoring tools like the **Elastic** (ELK Stack) and **Splunk**, and helps include contextual data like correlation IDs for better debugging.

```java
// Structured logging with Logstash encoder
import net.logstash.logback.argument.StructuredArguments;

@Service
public class PaymentService {
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    
    public PaymentResult processPayment(Payment payment) {
        // Structured logging with key-value pairs
        logger.info("Processing payment",
            StructuredArguments.kv("paymentId", payment.getId()),
            StructuredArguments.kv("amount", payment.getAmount()),
            StructuredArguments.kv("currency", payment.getCurrency()),
            StructuredArguments.kv("userId", payment.getUserId()));
        
        try {
            PaymentResult result = paymentGateway.process(payment);
            
            logger.info("Payment processed",
                StructuredArguments.kv("paymentId", payment.getId()),
                StructuredArguments.kv("status", result.getStatus()),
                StructuredArguments.kv("transactionId", result.getTransactionId()));
            
            return result;
        } catch (PaymentException e) {
            logger.error("Payment failed",
                StructuredArguments.kv("paymentId", payment.getId()),
                StructuredArguments.kv("errorCode", e.getErrorCode()),
                StructuredArguments.kv("errorMessage", e.getMessage()));
            throw e;
        }
    }
}
```

## 6: What is centralized logging?

**Centralized Logging** is a technique where logs from all **Microservices** or applications are collected and stored in a **single central location**. Instead of checking logs on individual servers, developers can view and analyze all logs from one place.

**Key Features**

* Collects logs from multiple services into one system.
* Provides **centralized monitoring** and troubleshooting.
* Supports **searching**, **filtering**, and **visualizing** logs.
* Helps track requests across multiple microservices.
* Integrates with monitoring and alerting tools.

**How it Works**

1. Each microservice generates logs.
2. A **log collector** (such as **Filebeat** or **Fluentd**) gathers the logs.
3. Logs are sent to a centralized storage system like **Elasticsearch**.
4. A visualization tool like **Kibana** displays and searches the logs.

**Architecture Example**

```text id="1a5r9w"
User Service   Order Service   Payment Service
      |               |               |
      ---------------------------------
                    |
             Log Collector
          (Filebeat/Fluentd)
                    |
              Elasticsearch
                    |
                 Kibana
```

**Why to Use**

* Makes debugging easier in **distributed systems**.
* Eliminates the need to log in to multiple servers.
* Helps identify errors and performance issues quickly.
* Improves system monitoring and incident analysis.

**When to Use**

* In **Microservices** architectures.
* In cloud-native or distributed applications.
* When multiple services generate large amounts of logs.
* For production systems requiring monitoring and auditing.

**Common Tools Used**

| **Tool**               | **Purpose**                       |
| ---------------------- | --------------------------------- |
| **Logback / Log4j2**   | Generate application logs         |
| **Filebeat / Fluentd** | Collect and forward logs          |
| **Elasticsearch**      | Store and index logs              |
| **Kibana**             | Search and visualize logs         |
| **ELK Stack**          | Elasticsearch + Logstash + Kibana |
| **EFK Stack**          | Elasticsearch + Fluentd + Kibana  |

**Spring Boot Logging Example**

```java id="2q8v7m"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public String getUsers() {
        logger.info("Fetching user details");
        return "Users List";
    }
}
```

**Spring Boot Logging Example**

**Service Class**

```java id="e8hzyt"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    public void createEmployee() {
        logger.info("Employee creation started");

        try {
            // Business logic
            logger.info("Employee created successfully");
        } catch (Exception e) {
            logger.error("Error while creating employee", e);
        }
    }
}
```

**Logback Configuration**

```xml id="jdr4c7"
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/application.log</file>

        <encoder>
            <pattern>
                %d %-5level %logger - %msg%n
            </pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="FILE"/>
    </root>

</configuration>
```

**How It Works in Production**

1. **Spring Boot** application generates logs.
2. Logs are written using **SLF4J** and **Logback**.
3. A log collector such as **Logstash** or **Fluentd** reads the log files.
4. Logs are sent to **Elasticsearch**.
5. **Kibana** provides dashboards and search capabilities.


```yaml
# Docker Compose with centralized logging
version: '3'
services:
  app1:
    image: myapp:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app1
        
  app2:
    image: myapp2:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app2
        
  fluentd:
    image: fluent/fluentd:latest
    ports:
      - "24224:24224"
    volumes:
      - ./fluentd.conf:/fluentd/etc/fluent.conf
      
  elasticsearch:
    image: elasticsearch:7.9.0
    
  kibana:
    image: kibana:7.9.0
    ports:
      - "5601:5601"
```

```java
// Application configuration for centralized logging
@Configuration
public class LoggingConfig {    
    @Bean
    public Logger structuredLogger() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        // Add correlation ID to all logs
        context.putProperty("service.name", "user-service");
        context.putProperty("service.version", "1.0.0");
        
        return context.getLogger("STRUCTURED");
    }
}
```

## 7: What is Config Server?

A **Config Server** is a **centralized configuration management service** used in **Microservices**. It stores and manages configuration files (such as database URLs, API keys, and application properties) in one place and provides them to all microservices at runtime.

**Key Features**

* Provides **centralized configuration management**.
* Stores configuration in a **Git repository** or other external storage.
* Allows **multiple microservices** to share common configurations.
* Supports **environment-specific** configurations (Dev, QA, Prod).
* Configuration can be refreshed without rebuilding the application.

**How it Works**

1. Configuration files are stored in a **Git repository**.
2. The **Spring Cloud Config Server** reads these files.
3. When a microservice starts, it contacts the Config Server.
4. The Config Server returns the required configuration based on the application name and environment.
5. The microservice loads and uses the configuration.

**Architecture Example**

```text id="8mtvhi"
              Git Repository
          (application.yml)
                    |
            Config Server
                    |
      ---------------------------
      |            |            |
 User Service  Order Service  Payment Service
```

**Why to Use**

* Eliminates duplicate configuration files across services.
* Makes configuration changes easier and more consistent.
* Improves security by storing sensitive configurations centrally.
* Simplifies environment management for Dev, Test, and Production.

**When to Use**

* In **Microservices** architectures with multiple services.
* When the same configuration is shared across applications.
* When configurations need to be updated without changing application code.
* For centralized management of environment-specific settings.

**Common Technologies Used**

| **Component**                  | **Purpose**                                  |
| ------------------------------ | -------------------------------------------- |
| **Spring Cloud Config Server** | Centralized configuration service            |
| **Git**                        | Stores configuration files                   |
| **Spring Cloud Config Client** | Fetches configuration from the Config Server |
| **Spring Boot Actuator**       | Refreshes configuration dynamically          |

**Config Server Example**

**Enable Config Server**

```java id="j0k5vq"
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**Config Server `application.yml`**

```yaml id="tk1l3m"
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/example/config-repo
```

**Client Configuration**

```yaml id="g8r4dw"
spring:
  application:
    name: user-service
  config:
    import: configserver:http://localhost:8888
```

## 8. What are Java deployment issues?

**Java deployment issues :** -  occur when an application runs correctly in development but fails or behaves differently in production.

1. **Dependency Conflicts :** - 
Different versions of libraries may cause **ClassNotFoundException** or **NoSuchMethodError** during deployment.
2. **Environment Configuration Issues :** - 
Application may fail if **environment variables, configuration files, or profiles** are not set correctly.
3. **Port Conflicts :** - 
If the application tries to start on a **port already used by another service**, it will fail to start.
4. **Database Connection Issues :** - 
Incorrect **database credentials, network restrictions, or connection pool configuration** can cause deployment failures.
5. **Missing Resources :** - 
Required files like **configuration files, certificates, or static resources** may not be included in the deployment package.
6. **JVM Configuration Problems :** - 
Improper **JVM memory settings** (`-Xms`, `-Xmx`) can cause performance issues or application crashes.
7. **Server Compatibility Issues :** - 
Sometimes the **Java version or application server version** in production is different from development.

```java
// Check classpath at runtime
String classpath = System.getProperty("java.class.path");
System.out.println("Classpath: " + classpath);
```


## 9. What are debugging strategies?

**Debugging strategies** are techniques used to **identify, analyze, and fix errors (bugs)** in a program.

**Common Debugging Strategies :**

1. **Log Analysis :**
   Check application logs to identify errors or unusual behavior.
2. **Using Debugger Tools :**
   Use IDE debuggers to add **breakpoints**, step through code, and inspect variables.
3. **Reproduce the Issue :**
   Try to recreate the bug in the same conditions to understand the problem.
4. **Divide and Conquer :**
   Break the code into smaller parts to isolate where the problem occurs.
5. **Check Recent Changes :**
   Review recently modified code because bugs often come from recent updates.
6. **Use Monitoring Tools :**
   Tools help analyze performance, memory usage, and thread activity.

```java
// Strategic logging
logger.debug("Processing user: {}, status: {}", userId, status);
```

# ✅ 29. Java Testing

##  1. What is unit testing in Java?
Unit testing is a software testing technique where individual components or modules of a software application are tested in isolation
It helps ensure that each unit of code performs as expected and catches bugs early in developmentjava

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

@Test
public void testAdd() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.add(2, 3));
}
```

## 2. What is JUnit?
JUnit is a popular open-source testing framework for Java that provides annotations and assertions for writing and running unit testsjava

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("hello"));
    }
}
```

## 3. What are the annotations used in JUnit?
Common JUnit annotations include @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @DisplayName, 

```java
@Disabledjava
public class LifecycleTest {
    @BeforeAll
    static void setupAll() { /* runs once before all tests */ }
    
    @BeforeEach
    void setup() { /* runs before each test */ }
    
    @Test
    @DisplayName("Test addition operation")
    void testAddition() { /* test method */ }
    
    @AfterEach
    void tearDown() { /* runs after each test */ }
    
    @AfterAll
    static void tearDownAll() { /* runs once after all tests */ }
}
```

## 4. What is TestNG?

TestNG is a testing framework inspired by JUnit but with more powerful features like data providers, parallel execution, and flexible test configurationjava

```java
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestNGExample {
    @DataProvider
    public Object[][] testData() {
        return new Object[][]{{1, 2, 3}, {4, 5, 9}};
    }
    
    @Test(dataProvider = "testData")
    public void testAdd(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }
}
```

## 5. What is the difference between JUnit and TestNG?
JUnit is simpler and widely adopted, while TestNG offers more advanced features like data providers, parallel execution, and better test configurationjava

```java
// JUnit 5
@ParameterizedTest
@ValueSource(strings = {"hello", "world"})
void testWithJUnit(String word) {
    assertNotNull(word);
}

// TestNG
@Test(dataProvider = "words")
void testWithTestNG(String word) {
    assertNotNull(word);
}
```

## 6. What is mocking in Java testing?
Mocking is creating fake objects that simulate the behavior of real objects to isolate the unit being tested from its dependenciesjava

```java
// Using Mockito
@Mock
private UserRepository userRepository;

@Test
void testGetUser() {
    User mockUser = new User("John", "john@email.com");
    when(userRepository.findById(1L)).thenReturn(mockUser);
    
    User result = userService.getUser(1L);
    assertEquals("John", result.getName());
}
```

## 7. What is Mockito?
Mockito is a popular Java mocking framework that allows you to create mock objects and define their behavior for testingjava
import static org.mockito.Mockito.*

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void testSendWelcomeEmail() {
        userService.registerUser("john@email.com");
        verify(emailService).sendEmail("john@email.com", "Welcome!");
    }
}
```

## 8. What is integration testing?
Integration testing verifies that different modules or services work correctly when integrated togetherjava

```java
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCreateUser() {
        User user = new User("John", "john@email.com");
        ResponseEntity<User> response = restTemplate.postForEntity("/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
```

## 9. What is test-driven development (TDD)?
TDD is a development approach where you write tests first, then write the minimum code to make tests pass, then refactorjava

```java
// Step 1: Write failing test
@Test
void testCalculateArea() {
    Circle circle = new Circle(5);
    assertEquals(78.54, circle.calculateArea(), 0.01);
}

// Step 2: Write minimum code to pass
public class Circle {
    private double radius;
    
    public Circle(double radius) { this.radius = radius; }
    
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

## 10. What is behavior-driven development (BDD)?
BDD extends TDD by writing tests in natural language that describes the behavior of the application from user's perspectivejava

```java
// Using Cucumber with Java
@Given("a user with email {string}")
public void aUserWithEmail(String email) {
    user = new User(email);
}

@When("the user logs in")
public void theUserLogsIn() {
    loginResult = authService.login(user);
}

@Then("the login should be successful")
public void theLoginShouldBeSuccessful() {
    assertTrue(loginResult.isSuccess());
}
```


## How to Start System Design From Scratch

System design should start with understanding requirements, estimating scale, designing high-level architecture, choosing databases/APIs, and then improving scalability, reliability, and maintainability.


**1. Understand Requirements**

First ask questions.

**Functional Requirements**

What system should do?

Example for Food Delivery:

* User login
* Search restaurants
* Place order
* Payment
* Track delivery

## Non-Functional Requirements

How system should behave?

* Scalability
* Security
* High availability
* Low latency
* Fault tolerance


**2. Estimate Scale**

Estimate:

* Daily active users
* Requests per second (RPS)
* Storage
* Traffic

Example:

```text id="7g1vgh"
1 million users
100k daily orders
500 requests/sec
```

This helps decide architecture.


**3. Identify Core Entities**

Find main objects.

Example for E-commerce:

```text id="o49gpk"
User
Product
Cart
Order
Payment
Inventory
```


**4. Design High-Level Architecture (HLD)**

Draw big components.

Example:

```text id="h83x71"
Client → Load Balancer → API Gateway
                        ↓
              Microservices
                        ↓
              Database / Cache / Queue
```

Components:

* Frontend
* Backend
* Database
* Cache
* Messaging queue
* CDN
* Storage


**5. Database Design**

Choose DB:

| Use Case           | Database         |
| ------------------ | ---------------- |
| Structured data    | MySQL/PostgreSQL |
| Huge scalable data | MongoDB          |
| Fast caching       | Redis            |
| Search             | Elasticsearch    |

Create tables/schema.

Example:

```text id="n5s2uw"
User(id, name, email)
Order(id, userId, total)
```


**6. API Design**

Design REST APIs.

Example:

```http id="9qkhtm"
POST /orders
GET /products
PUT /cart
```

Think about:

* Request
* Response
* Status codes
* Pagination


**7. Decide Architecture Style**

Choose:

| Type          | When Used              |
| ------------- | ---------------------- |
| Monolith      | Small projects         |
| Microservices | Large scalable systems |
| Event Driven  | Async processing       |


**8. Add Scalability**

Think:

## Horizontal Scaling

```text id="ggxyl4"
Multiple backend servers
```

Use:

* Load balancer
* Auto scaling


**9. Add Performance Optimization**

Use:

* Redis cache
* CDN
* DB indexing
* Lazy loading
* Compression


**10. Handle Reliability**

Add:

* Retry mechanism
* Circuit breaker
* Replication
* Backup
* Failover


**11. Security Design**

Think about:

* Authentication
* Authorization
* JWT/OAuth
* HTTPS
* Rate limiting


**12. Monitoring & Logging**

Use:

* ELK Stack
* Prometheus
* Grafana
* CloudWatch


**13. Deep Dive (LLD)**

Now design classes.

Example:

```java id="crd5dn"
interface PaymentStrategy {
    void pay();
}
```

Use:

* SOLID principles
* Design patterns
* UML
* OOP


**Example Interview Flow**

If interviewer asks:

> Design WhatsApp

You should answer in order:

1. Requirements
2. Scale estimation
3. HLD
4. DB design
5. Message flow
6. Real-time communication
7. Scaling
8. Reliability
9. Security


**Common Technologies**

| Component  | Technology     |
| ---------- | -------------- |
| API        | Spring Boot    |
| Database   | PostgreSQL     |
| Cache      | Redis          |
| Queue      | Kafka/RabbitMQ |
| Search     | Elasticsearch  |
| Storage    | S3             |
| Monitoring | Grafana        |


**Golden Rule**

Start with:

```text id="c0f6wj"
Requirements → Scale → HLD → DB → APIs → Scaling → Reliability → Security
```

