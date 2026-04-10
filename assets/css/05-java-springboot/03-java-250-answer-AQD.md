# ✅ 1. Java Basic Concepts 

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

**JVM (Java Virtual Machine)** is the runtime environment that executes Java bytecode on any platform. It’s responsible for **memory management, garbage collection, and execution**.

**JRE (Java Runtime Environment)** includes the **JVM plus core libraries and classes** needed to run Java applications. It doesn’t have tools for development.

**JDK (Java Development Kit)** includes **JRE plus development tools** like `javac` and `jar` for compiling and building Java programs.


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


## 3. What are the main principles of Object-Oriented Programming?

**Object-Oriented Programming** is based on four fundamental principles that promote code reusability, maintainability, and modularity.

**Four OOP Principles:**

* **Encapsulation:** Encapsulation means binding data and methods together and protecting data using access modifiers (`private`, `protected`, `public`) and access using getters and setters.
* **Inheritance:** Inheritance means a child class can use properties and methods of a parent class using `extends`.
* **Polymorphism:** Polymorphism means one method can perform different actions using overloading or overriding.
* **Abstraction:** Abstraction means hiding internal implementation and showing only necessary details using abstract class or interface.


- **1. Encapsulation:** Data is `private` and accessed using `getter and setter methods`.

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

## 5. Normal, final, static, static final, volatile, abstract, transient?

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

**volatile** Used in multithreading. always read from main memory, Prevents caching issues.
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
    private transient String password;
    // `transient` is a serialization keyword in Java. password will NOT be saved.
}
```

**Difference Table**

| Keyword      | Belongs To    | Can Change? | Purpose                  |
| ------------ | ------------- | ----------- | ------------------------ |
| Normal       | Object        | Yes         | Regular variable         |
| final        | Object        | No          | Constant value           |
| static       | Class         | Yes         | Shared among objects     |
| static final | Class         | No          | Class constant           |
| volatile     | Object        | Yes         | Thread visibility        |
| abstract     | ❌ Not allowed | ❌           | Only for methods/classes |
| transient    | Object        | Yes         | Skip serialization       |



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



# ✅ 2. Data Types and Variables

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

## 2. What is the difference between primitive and reference types?
Primitive types in Java, like int, double, and boolean, store actual values in memory and are stored on the stack. They are fast and have a fixed size.

Reference types, like objects, arrays, and strings, store a reference or memory address pointing to the actual data in the heap. They can have methods, support polymorphism, and are generally more flexible but slightly slower.

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
* **`String`** is **immutable**, meaning every modification creates a **new object**. It’s thread-safe but can be inefficient for repeated changes.

* **`StringBuilder`** is **mutable** and allows **fast modifications** of strings. It is **not thread-safe**, so it’s suitable for single-threaded operations.

* **`StringBuffer`** is also **mutable** but **thread-safe** because its methods are synchronized. It’s slightly slower than `StringBuilder` due to synchronization overhead.

```java
// String - creates new objects
String s = "Hello";
s.concat(" World");
System.out.println(s);

// StringBuilder - modifies existing buffer
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");
System.out.println(sb);

// StringBuffer (Mutable, Thread-safe)
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");
System.out.println(sb);
```

## 6. what is immutable and Why are strings immutable in Java?

Immutable means **once an object is created, its state (data) cannot be changed**. If you try to change it, a **new object is created instead of modifying the existing one**.

**Strings are immutable in Java because:**

1. **String Pool memory optimization**
2. **Thread safety**
3. **HashMap key reliability (hashcode caching)**
4. **Security (class loading, file paths, URLs)**


In Java, **strings are immutable**, meaning once a `String` object is created, its value **cannot be changed**.

- **Security:** Prevents malicious code from changing string values
- **Thread Safety:** Multiple threads can access without synchronization
- **String Pool:** Enables efficient memory usage through sharing
- **Hashcode Caching:** Hash values remain constant for HashMap keys
- **Performance:** JVM optimizations possible

**Example:**

```java
String s1 = "Hello";
String s2 = s1;      // Both point to same object

s1 = s1 + " World";  // Creates a new String object
System.out.println(s1); // Hello World
System.out.println(s2); // Hello
```

## 7. What is the difference between final, finally, and finalize?

- **final:** Keyword for constants, preventing inheritance/override
- **finally:** Block that always executes after try-catch
- **finalize:** Method called by garbage collector before object destruction

```java
// final
final int MAX_SIZE = 100;
final class MyClass { }

// finally
try {
    // code
} catch (Exception e) {
    // handle exception
} finally {
    // always executes
}

// finalize
protected void finalize() {
    // cleanup before garbage collection
}
```


**final** is for immutability, **finally** for cleanup code, **finalize** for garbage collection (rarely used).

## 8. What is string pooling?

String pooling is Java's memory optimization technique where identical string literals share the same memory location in the String Pool (part of heap memory).

```java
String a = "hello";    // stored in string pool
String b = "hello";    // reuses same memory location
String c = new String("hello"); // creates new object in heap

System.out.println(a == b);  // true - same reference
System.out.println(a == c);  // false - different references
```

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

## 11. What is Predicate in java

In Java, a **Predicate** is a functional interface used to **test a condition** and return a boolean result (**true or false**).

It is part of the java.util.function package (introduced in Java 8).


** Real-Time Example (Filtering)**

```java
List<String> names = Arrays.asList("John", "Sam", "Alex");

Predicate<String> startsWithA = name -> name.startsWith("A");

names.stream()
     .filter(startsWithA)
     .forEach(System.out::println);  // Output: Alex
```

## 12. What is `hashCode()` in Java?

**`hashCode()`** is used to generate a hash value for an object and is used internally by HashMap, HashSet for fast retrieval. If two objects are equal, their hashCode must be equal.


```java
class Test {
    public static void main(String[] args) {
        String a = new String("Hello");
        String b = new String("Hello");

        System.out.println(a.equals(b));   // true
        System.out.println(a.hashCode()); // same
        System.out.println(b.hashCode()); // same
    }
}
```

**Important Rule Table**

| Condition         | Rule                     |
| ----------------- | ------------------------ |
| equals() true     | hashCode must be same    |
| hashCode same     | equals may be true/false |
| equals overridden | hashCode must override   |


## 13. What is `var` keyword in Java(10)?

**var** in Java 10 is used for local variable type inference. It means the compiler automatically detects the type based on the value assigned, so we don’t need to explicitly declare the type.

```java
var name = "Kali";     // String
var age = 25;          // int
var list = List.of(1,2,3); // List<Integer>

// Compiler converts it internally:
String name = "Kali";
int age = 25;
List<Integer> list = List.of(1,2,3);
```

# ✅ 3. Classes and Objects


## 1. What is the difference between this and super keywords?

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

## 2. What is method overloading?

Method overloading is a feature in Java where multiple methods have the **same name** but **different parameter lists** (different number, type, or order of parameters) in the same class.

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

## 3. What is method overriding?

**Method overriding** is redefining a parent class method in the child class with the same signature. The child class version gets called instead of the parent's.


```java
class Animal {
    public void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
```


## 4. What is a class in Java?

A class is a blueprint or template used to create objects. It defines properties and behavior.

```java
class Car {
    String color;
    void drive() {
        System.out.println("Car is driving");
    }
}
```


## 5. What is class loader and how do they work?

A **ClassLoader** in Java is a part of the **JVM** that is responsible for **loading `.class` files into memory** at runtime.


**How ClassLoader Works**

Java follows a **Delegation Hierarchy Model**:

1. **Bootstrap ClassLoader**

   * Loads core Java classes (e.g., `java.lang.*`)
2. **Extension (Platform) ClassLoader**

   * Loads classes from `jre/lib/ext`
3. **Application (System) ClassLoader**

   * Loads classes from classpath



## 6. How do you create an immutable class in Java?

An immutable class in Java is a class whose objects cannot be changed after they are created. To create an immutable class:

1. Make the class `final` so it cannot be subclassed.
2. Make all fields `private` and `final`.
3. Do not provide setters.
4. Initialize fields through constructor.
5. Return copies of mutable objects (defensive copy).

```java
final class Student {
    private final int id;
    private final String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```


## 7. What is an object?

An object is an instance of a class. It represents real-world entities.

```java
Car c = new Car();  // object creation
```


## 8. Difference between class and object?

Class is a blueprint, object is the actual implementation of that blueprint.

* Class → logical
* Object → physical (exists in memory)



## 9. How to create a class and object?

We create a class using class keyword and object using new keyword.

```java
class Student {
    String name;
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student(); // object
        s.name = "John";
        System.out.println(s.name);
    }
}
```


## 10. What are instance variables and methods?

Instance variables belong to object and methods define behavior of object.

```java
class Employee {
    String name; // instance variable

    void work() { // instance method
        System.out.println("Working...");
    }
}
```


## 11. What is a constructor? Types?

Constructor is a special method used to initialize objects. It is called automatically when object is created.

**Types:**

* Default
* Parameterized

```java
class Student {
    String name;

    Student() { // default
        name = "Unknown";
    }

    Student(String n) { // parameterized
        name = n;
    }
}
```


## 12. What is default constructor?

A constructor with no parameters. If we don’t create one, Java provides it automatically.

```java
Student s = new Student(); // calls default constructor
```


## 5. What is the static keyword?

**`static`** is a keyword in **Java** used to declare variables, methods, or blocks that **belong to the class instead of an object**, so they can be accessed without creating an instance.

```java
class Example {
    static int count = 0;

    static void show() {
        System.out.println("Static method");
    }
}

public class Test {
    public static void main(String[] args) {
        Example.show();   // No object needed
    }
}
```

`static` members are **shared by all objects and can be accessed using the class name**.



## 14. Difference: static vs non-static?

Static belongs to class, non-static belongs to object.

| Static           | Non-Static               |
| ---------------- | ------------------------ |
| Shared           | Separate for each object |
| No object needed | Object required          |

```java
class Test {
    static void show1() {}
    void show2() {}
}
```


## 15. Can a class have multiple constructors?

Yes, this is called constructor overloading.

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

Yes, final class cannot be extended. It is used to prevent inheritance for security or immutability.

```java
final class A {}

// class B extends A {} ❌ ERROR
```


## 18. Can we make a class `abstract`?

Yes, abstract class cannot be instantiated and can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();
}
```


## 19. What is the difference between interface and abstract class?

An **interface** is used to define a **contract** that classes must implement. It mainly contains **abstract methods**, and variables are **public, static, and final by default**.
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

An **abstract class** is used when classes share **common behavior and state**. It can have **abstract methods and concrete methods**, and it can also have **instance variables and constructors**.

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


## 20. Can a class be both abstract and final? 

No, because abstract needs inheritance and final restricts inheritance.



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

Singleton class allows only one object creation.

```java
class Singleton {
    private static Singleton obj = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return obj;
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

Java provides a default constructor automatically.



## 29. Can a class be private or protected?

Top-level class cannot be private or protected, only public or default. Inner classes can be private/protected.

```java
class Outer {
    private class Inner {}
}
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



# ✅ 4. Java Inheritance 

## 1. What is Inheritance in Java?

Inheritance is a feature where one class **gets properties and methods of another class**.

👉 *In simple words:* child class uses parent class code.

```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}
```


## 2. Why doesn't Java support multiple inheritance (with classes)?

Java does not support multiple inheritance with **classes** to avoid the **Diamond Problem** and complexity.

But Java does support multiple inheritance with **interfaces**.

```java
class A {
    void show() {
        System.out.println("From class A");
    }
}

class B extends A {
}

class C extends A {
}

class D extends B, C {   // ❌ Not allowed in Java
    public static void main(String[] args) {
        D obj = new D();
        obj.show();  // Which show()? From B or C?
    }
}
```

## 3. What is the diamond problem?

The diamond problem occurs when a class inherits from two classes that both inherit from the same base class, creating ambiguity about which method to call.

```
    A
   / \
  B   C
   \ /
    D
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

We use inheritance to:

* **Reuse code**
* **Avoid duplication**
* **Make code cleaner and organized**

👉 *Example:* Dog already gets `eat()` from Animal, no need to rewrite it.


## 5. What is `extends` keyword?

`extends` is used to **inherit a class**.

```java
class Dog extends Animal {
}
```

👉 Dog is inheriting from Animal using `extends`.


## 6. What is IS-A Relationship?

IS-A means **one object is a type of another**.

👉 Example:

* Dog IS-A Animal ✅

```java
Dog d = new Dog(); // Dog is also an Animal
```


## 7. What is Method Overriding?

Method overriding means **child class provides its own version of parent method**.

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

👉 Same method name, but different behavior.


## 8. Types of Inheritance in Java

Java supports:

* **Single**
* **Multilevel**
* **Hierarchical**

👉 *Note:* Multiple inheritance is not supported with classes.


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

👉 Puppy → Dog → Animal


## 11. What is Hierarchical Inheritance?

Multiple child classes inherit from one parent class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {}
class Cat extends Animal {}
```

👉 Dog and Cat both inherit Animal



## 12. Can we override static methods?

❌ No

👉 Static methods are **not overridden**, they are **hidden**.


## 13. Can we override final methods?

❌ No

👉 Final methods cannot be changed in child class.

```java
class Animal {
    final void run() {}
}

class Dog extends Animal {
    // ❌ Error: cannot override final method
}
```


## 14. What is runtime polymorphism?

👉 *“Runtime polymorphism means method execution is decided at runtime based on object type using method overriding.”*

```java
class A {
    void show() { System.out.println("A"); }
}

class B extends A {
    void show() { System.out.println("B"); }
}

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.show(); // Output: B
    }
}
```


## 15. How does method overriding work internally?

👉 *“JVM uses dynamic method dispatch. It decides method call at runtime based on actual object, not reference.”*


## 16. What are rules for method overriding?

👉 *“Same name, same parameters, same or covariant return type, and cannot reduce access level.”*

* Cannot override `final`, `static`, `private`
* Access modifier can be same or more open


## 17. What is covariant return type?

👉 *“Child class can return a more specific type than parent method.”*

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

👉 *“No, constructors are not inherited because they belong to class initialization.”*


## 19. What is the order of constructor execution?

👉 *“Parent constructor executes first, then child constructor.”*

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() { System.out.println("Child"); }
}
```

**Output:**

```
Parent
Child
```


## 20. What happens if parent constructor is not called?

👉 *“Java automatically calls default parent constructor using super().”*

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

👉 *“No, Java does not support multiple inheritance for classes to avoid ambiguity.”*

```java id="7c8h3n"
// class C extends A, B {} ❌ NOT ALLOWED
```


## 22. Can we inherit private members?

👉 *“Yes, but we cannot access them directly outside the class.”*

```java 
class A {
    private int x = 10;

    int getX() { return x; } // access via method
}

class B extends A {
    void show() {
        // System.out.println(x); ❌ ERROR
        System.out.println(getX()); // ✅
    }
}
```


## 23. Can we override private methods?

👉 *“No, private methods are not visible, so they cannot be overridden.”*

```java 
class A {
    private void show() {}
}

class B extends A {
    // void show() {} ❌ not overriding, new method
}
```


## 24. Can we change access modifier while overriding?

👉 *“Yes, but only to increase visibility, not decrease.”*

```java id="c2h9rf"
class A {
    protected void show() {}
}

class B extends A {
    public void show() {} // ✅ allowed
}
```


## 25. What happens if parent and child have same variable name?

👉 *“Variable hiding happens. Access depends on reference type.”*

```java id="6y5kdn"
class A { int x = 10; }
class B extends A { int x = 20; }

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        System.out.println(obj.x); // 10
    }
}
```


## 26. Can abstract class have constructor?

👉 *“Yes, it is used to initialize variables when child object is created.”*

```java id="ux1l7p"
abstract class A {
    A() { System.out.println("Abstract constructor"); }
}

class B extends A {}
```


## 27. Can interface extend class?

👉 *“No, interface can only extend another interface.”*

```java id="1jxv8n"
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

👉 *“No, class cannot extend interface. It uses implements keyword.”*

```java id="8mz4tt"
interface A {}

class B implements A {}
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


# ✅ 5. Java Interface & Abstract Class 


## 1. What is an Interface?

An interface in Java is a blueprint that contains only abstract methods (by default) and constants. It is used to achieve 100% abstraction and multiple inheritance.


```java
interface Animal {
    void sound(); // abstract method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
}
```

## 2. What are the interface available in Java?

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

// Usage with lambda
Calculator add = (a, b) -> a + b;
int result = add.calculate(5, 3);
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

## 3. What are static methods in interfaces?

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


## 4. What is an Abstract Class?


An abstract class is a class that can have both abstract methods and concrete methods. It is used when we want partial abstraction.



```java
abstract class Animal {
    abstract void sound(); // abstract method

    void eat() { // concrete method
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}
```


## 5. When should you use an interface instead of an abstract class?

Use **interface** when:

* You want **multiple inheritance**
* You define **only method contracts (what to do, not how)**
* Different classes share **common behavior but not relationship**

Use **abstract class** when:

* Classes are **closely related**
* You want **common code + common fields**


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

👉 *Use interface when we need 100% abstraction and multiple inheritance. Use abstract class when we need partial abstraction with shared code.*


## 13. Can a class implement multiple interfaces?

👉 *Yes, a class can implement multiple interfaces to achieve multiple inheritance.*

```java id="l3p9sx"
interface A { void show(); }
interface B { void display(); }

class C implements A, B {
    public void show() {}
    public void display() {}
}
```


## 14. Can an abstract class implement an interface?

👉 *Yes, abstract class can implement interface and may or may not provide implementation.*

```java id="9v2tqg"
interface A {
    void show();
}

abstract class B implements A {
    // no implementation (allowed)
}
```


## 15. Can an interface extend another interface?

👉 *Yes, interface can extend one or more interfaces.*

```java id="y6m2an"
interface A {
    void show();
}

interface B extends A {
    void display();
}
```


## 16. Can an interface extend a class? (Tricky)

👉 *No, interface cannot extend a class, it can only extend interfaces.*


## 17. Can a class extend multiple abstract classes?

👉 *No, Java does not support multiple inheritance for classes, even abstract classes.*

```java id="g8x4qp"
// class C extends A, B {} ❌ NOT ALLOWED
```


## 18. Can interface have constructor?

👉 ❌ No (no object creation)


## 19. Can abstract class have constructor?

👉 ✅ Yes (used during object creation of subclass)


## 20. Can interface have private methods?

👉 ✅ Yes (Java 9+)


## 21. Can we override static methods in interface?

👉 ❌ No (only hide)


## 22. What if two interfaces have same default method?

👉 Must override:

```java
class Test implements A, B {
    public void show() {
        A.super.show();
    }
}
```


## 23. Can abstract class be final?

👉 ❌ No (conflict)


## 24. Can interface have main method?

👉 ✅ Yes (Java 8+)


## 25. Can we use both interface and abstract class together?

👉 ✅ Yes (very common)


## 26. What access modifiers are allowed in interface?

👉 Methods → public (default)
👉 Variables → public static final


## 27. Can we instantiate interface using lambda?

👉 ✅ Yes (functional interface)


## 9. What are SOLID principles?

**Answer:**

**SOLID** is a set of five object-oriented design principles that help write clean, maintainable, and scalable code.

- **S**ingle Responsibility: A class should have one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable for their base types
- **I**nterface Segregation: Many specific interfaces are better than one general interface
- **D**ependency Inversion: Depend on abstractions, not concrete implementations

**Example:**
**S — Single Responsibility Principle (SRP)**

One class should have only one responsibility.

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

---

**O — Open/Closed Principle (OCP)**

Open for extension, closed for modification.

```java
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Paid by Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paid by UPI");
    }
}

class PaymentService {
    public void processPayment(Payment payment) {
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        Payment p1 = new CardPayment();
        service.processPayment(p1);

        Payment p2 = new UpiPayment();
        service.processPayment(p2);
    }
}

// Output:
Paid by Card
Paid by UPI
```

Now we can add **NetBankingPayment** without changing existing code.

---

**L — Liskov Substitution Principle (LSP)**

Child class should replace parent class without breaking code.

```java
class Bird {
    public void fly() {
        System.out.println("Bird can fly");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

public class Main {
    public static void main(String[] args) {
        Bird b = new Bird();
        b.fly();

        Sparrow s = new Sparrow();
        s.fly();

        Bird b2 = new Sparrow(); // Runtime Polymorphism
        b2.fly();
    }
}


// Output:
Bird can fly
Sparrow can fly
Sparrow can fly
```

Bad example: Penguin cannot fly → violates LSP.

---

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

---

**D — Dependency Inversion Principle (DIP)

Depend on abstraction, not concrete class.

```java
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class OrderService {
    private Payment payment;

    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() {
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {
        Payment payment = new CardPayment();   // Inject dependency
        OrderService orderService = new OrderService(payment);

        orderService.placeOrder();
    }
}

//Output:
Card payment
```

## 10. What is `.class` and When do we use in Java?

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



# ✅ 6. Java Exception Handling 

## 1. What is an exception in Java?

An **exception** is an unexpected event that occurs during program execution and disrupts the normal flow of the program. It's Java's way of **handling runtime errors** gracefully.

```java
int result = 10 / 0; // ArithmeticException occurs
String text = null;
int length = text.length(); // NullPointerException occurs
```

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

The **`throw`** keyword is used to **actually throw an exception object** inside the method body.

The **`throws`** keyword is used in the **method signature** to declare that the method may throw certain exceptions.

```java
public void validateAge(int age) throws IllegalArgumentException {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
}
// 'throws' warns the caller that this method might fail with an IOException
public void readFile() throws IOException { 
    if (fileNotFound) {
        // 'throw' actually triggers the error right now
        throw new IOException("File is missing!"); 
    }
}
```

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
class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Global Exception Caught: " + e.getMessage());
    }
}

public class Test {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());

        int a = 10 / 0; // Exception
    }
}
```

**2. Global Exception Handling in Spring Boot (Most Important for Interview)**


```java
@ControllerAdvice
```

This handles exceptions for the **entire application**

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "Error occurred: " + ex.getMessage();
    }
}
```

**Handle Specific Exception**

```java
@ExceptionHandler(NullPointerException.class)
public String handleNullPointer(NullPointerException ex) {
    return "Null value found";
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


## 9. What is try-with-resources?

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

# ✅ 7. Java Collections Framework

## 1. What is Java Collections Framework?

**Java Collections** Framework is a unified architecture for storing and manipulating groups of objects. It provides interfaces, implementations, and algorithms to work with collections efficiently.

- Provides common interfaces **like List, Set, Map**
- Ready-to-use implementations like **ArrayList, HashMap**
- Algorithms for **sorting, searching, shuffling**
- Reduces programming effort and increases performance

```java
List<String> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();
```

Here’s a cleaner and corrected version of your table:

```
Interface    | Common Classes                         | Description
-------------|----------------------------------------|-----------------------------------------
List         | ArrayList, LinkedList                  | Ordered collection that allows duplicates
Set          | HashSet, TreeSet, LinkedHashSet        | Collection of unique elements (no duplicates)
Map          | HashMap, TreeMap, LinkedHashMap        | Stores key-value pairs with unique keys
```


## 2. What is the difference between ArrayList and LinkedList?

**ArrayList** uses a **dynamic array**, so it gives **fast random access (O(1))**, but **slow insertions/deletions in the middle** due to shifting.

**LinkedList** uses a **doubly linked list**, so it has **slower access (O(n))**, but **faster insertions/deletions** since no shifting is required.

* **O(1) – Constant Time:** Execution time **does not change with input size**.
* **O(n) – Linear Time:** Execution time **increases proportionally with input size**.


```java
List<String> arrayList = new ArrayList<>(); // Fast access
List<String> linkedList = new LinkedList<>(); // Fast insertion/deletion
```

## 3. What is the difference between HashMap and TreeMap?

**HashMap** Unordered, allows null keys and values, O(1) average time complexity for basic operations.

**TreeMap** Sorted by keys, doesn't allow null keys, O(log n) time complexity for basic operations.

**In simple words:** Use **HashMap for faster performance**, and **TreeMap when you need sorted data.** 


```java
Map<String, Integer> hashMap = new HashMap<>(); // Fast, unordered
Map<String, Integer> treeMap = new TreeMap<>(); // Slower, sorted
```

## 4. What is the difference between HashMap Hashtable?

**HashMap** is a Map implementation that is **not synchronized** and allows **one null key and multiple null values**, making it faster but not thread-safe.

**Hashtable** is a Map implementation that is **synchronized** and does **not allow any null key or null value**, making it thread-safe but slower.


```java
Map<String, Integer> hashMap = new HashMap<>(); // Modern, faster
Map<String, Integer> hashtable = new Hashtable<>(); // Legacy, thread-safe
```

## 5. How does HashMap work internally?

**HashMap** works using an **array of buckets (Node array)**.
When you insert a key-value pair, it calculates the **hash value** of the key to find the bucket index.

If multiple keys map to the same bucket (**collision**), it stores them using a **linked list or tree (after Java 8)**.

When the number of entries exceeds the **load factor limit**, it performs **rehashing** to increase capacity.

**In simple words:** HashMap uses hashing to store and retrieve data efficiently. 🚀

```java
// Simplified internal process:
// 1. hash(key) -> bucket index
// 2. Store/retrieve from that bucket
// 3. Handle collisions in same bucket

Map<String, Integer> map = new HashMap<>();
map.put("key", 100); // hash("key") -> bucket index -> store
```

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

# ✅ 8. Java Multithreading & Synchronization 

## 0. What is thread and what are life cycle?

A **thread** is the **smallest unit of execution in a program** that allows multiple tasks to run simultaneously. 

Every Java application starts with at least one thread (the main thread), and you can create additional threads to run tasks concurrently.

**Thread Life Cycle:**

1. **New** – Thread object is created.
2. **Runnable** – Thread is ready to run after calling `start()`.
3. **Running** – Thread is executing.
4. **Waiting / Blocked** – Thread waits for a resource or another thread.
5. **Terminated (Dead)** – Thread execution is completed.

## 1. What is multithreading?

**Multithreading** allows a program to run **multiple threads concurrently** within the same memory space, improving **CPU utilization, performance, and responsiveness**. Threads can **share data** and the **JVM handles scheduling**.

**In simple words:** It lets a program do **many tasks at the same time** efficiently.

```java
// Realtime example
class PrintTask extends Thread {
    public void run() {
        System.out.println("Printing document...");
    }
}

class SaveTask extends Thread {
    public void run() {
        System.out.println("Saving document...");
    }
}

public class Main {
    public static void main(String[] args) {

        PrintTask t1 = new PrintTask();
        SaveTask t2 = new SaveTask();

        t1.start();   // Thread for printing
        t2.start();   // Thread for saving
    }
}
```

**Where Multithreading is Usually Used in Java (Important for Interview)**

**1. Web Applications (Spring Boot)**

Very common use:

* Each HTTP request runs in a **separate thread**
* Multiple users can use the application at the same time

Example:

* 1000 users → 1000 threads → handled by thread pool

---

**2. Calling Multiple APIs**

```java
CompletableFuture.supplyAsync(() -> apiCall1());
CompletableFuture.supplyAsync(() -> apiCall2());
CompletableFuture.supplyAsync(() -> apiCall3());
```

Used to **reduce API response time**.

**3. Background Jobs**

Used for:

* Sending emails
* Generating PDF
* Report generation
* Data import (Excel/CSV)
* Image processing

In Spring Boot:

```java
@Async
public void sendEmail() {
    // runs in background thread
}
```

**4. Batch Processing**

* Processing large data
* Bank transactions
* Log processing
* Data migration

**5. Real-Time Systems**

* Chat applications
* Notification systems
* Live tracking apps

## 2. How do you create threads in Java?

There are two main ways to create **threads** in Java: **extending Thread** class or implementing **Runnable interfac**e.

**Method 1: Extending Thread**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
MyThread t = new MyThread();
t.start();
```

**Method 2: Implementing Runnable**
```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}
Thread t = new Thread(new MyTask());
t.start();
```

## 3. What is the difference between extending Thread and implementing Runnable?

**Extending `Thread`** means creating a new class that inherits from the `Thread` class to define the task in its `run()` method.

**Implementing `Runnable`** means creating a class that implements the `Runnable` interface and defines the task in its `run()` method, which can then be executed by a `Thread` object.

**In simple words:** Use **Runnable** for better design; **Thread** for quick/simple cases.


```java
// Thread extension limits inheritance
class MyThread extends Thread { // Cannot extend anything else
    public void run() { }
}


// Runnable allows extending other classes
class MyTask extends SomeClass implements Runnable {
    public void run() { }
}
```

## 4. What are `sleep()` vs `wait()` in Multithreading?

* **`sleep()`** pauses the current thread for a specified time but **does not release the lock**.
* **`wait()`** pauses the thread **and releases the lock**, allowing other threads to execute. It must be used inside a **synchronized block**.

```java
// sleep() Example (Lock NOT Released)
class SleepExample {
    public static void main(String[] args) {

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 acquired lock");
                try {
                    Thread.sleep(3000); // sleeping but STILL holding lock
                } catch (InterruptedException e) {}
                System.out.println("Thread 1 finished");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired lock");
            }
        });

        t1.start();
        t2.start();
    }
}

// wait() Example (Lock Released)
class WaitExample {
    public static void main(String[] args) {

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 waiting...");
                    lock.wait(); // releases lock
                    System.out.println("Thread 1 resumed");
                } catch (InterruptedException e) {}
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired lock");
                lock.notify(); // wakes up t1
                System.out.println("Thread 2 notified");
            }
        });

        t1.start();
        t2.start();
    }
}

```


## 5. What is deadlock and how do you prevent it?

**Deadlock** happens when **two or more threads wait forever** for resources held by each other, causing the program to **freeze**.

**Prevention strategies:**
* **Avoid nested locks** – Do not lock multiple resources unnecessarily.
* **Use consistent lock order** – Always acquire locks in the same order.
* **Use timeout locks** – Use `tryLock()` to avoid waiting forever.
* **Minimize synchronized blocks** – Keep lock scope as small as possible.

```java
// Deadlock scenario
Thread1: lock(A) -> lock(B)
Thread2: lock(B) -> lock(A)

// Prevention - consistent ordering
Thread1: lock(A) -> lock(B)
Thread2: lock(A) -> lock(B)
```


## 6. What is synchronization in Java?

**Synchronization** in Java ensures that **only one thread at a time** can access a shared resource, preventing **race conditions**.

* Achieved using **`synchronized`** keyword or **locks**
* Can synchronize **methods** or **blocks**
* Ensures **thread safety** but may reduce performance

**In simple words:** It makes multithreaded access to shared data **safe and consistent**.

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

## 7. What is volatile keyword?

**Volatile** keyword ensures that a variable's value is **always read from and written to main memory**, not from thread's local cache. It provides visibility guarantee across threads.


Real-Time Example: Stop Thread (Very Common Interview Example)

// Scenario:

One thread runs a task, another thread stops it.

**Without volatile (Problem)**

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

Another thread sets:

```java id="v3n2po"
task.running = false;
```
❌ Thread may **never stop** because it may use cached value.


**With volatile (Solution)**

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

## 8. What is the difference between synchronized and concurrent collections?

**Synchronized collections** use a **single lock** to control access, which can block all threads and reduce performance under high concurrency.

**Concurrent collections** use **fine-grained locking or non-blocking algorithms**, allowing better **parallelism and performance**.

Example: `Collections.synchronizedList()` vs `ConcurrentHashMap`.

* **Locking mechanism** - Synchronized uses single lock, concurrent uses fine-grained locking
* **Performance** - Concurrent collections offer better performance under high concurrency
* **Blocking behavior** - Synchronized blocks all threads, concurrent allows some parallelism
* **Examples** - Collections.synchronizedList() vs ConcurrentHashMap

```java
// Synchronized collection - single lock for entire collection
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
synchronized (syncList) {  // Manual synchronization needed for iteration
    for (String item : syncList) {
        System.out.println(item);
    }
}

// Concurrent collection - fine-grained locking
ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("key1", 1);  // Non-blocking for different segments
concurrentMap.put("key2", 2);  // Can happen concurrently

// No external synchronization needed for iteration
for (Map.Entry<String, Integer> entry : concurrentMap.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

## 9. What is ConcurrentHashMap and how is it different from HashMap?

**ConcurrentHashMap** is a **thread-safe map** designed for high concurrency.

Unlike **HashMap**, it **does not allow null keys or values** and uses **segment-based (fine-grained) locking** instead of full synchronization, providing **better performance** in multi-threaded environments.

* **Thread safety** - ConcurrentHashMap is thread-safe, HashMap is not
* **Locking strategy** - Uses segment-based locking instead of full synchronization
* **Null values** - ConcurrentHashMap doesn't allow null keys/values, HashMap does
* **Performance** - Better concurrent performance than synchronized HashMap

```java
// HashMap - not thread-safe
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("key1", 1);
hashMap.put(null, 2);     // Null key allowed
hashMap.put("key2", null); // Null value allowed

// ConcurrentHashMap - thread-safe with better performance
ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("key1", 1);
// concurrentMap.put(null, 2);     // NullPointerException
// concurrentMap.put("key2", null); // NullPointerException

// Atomic operations
concurrentMap.putIfAbsent("key3", 3);
concurrentMap.compute("key1", (k, v) -> v + 1);
concurrentMap.merge("key1", 5, Integer::sum);

// Thread-safe iteration without external synchronization
concurrentMap.forEach((k, v) -> System.out.println(k + ": " + v));
```

## 10. What is race condition and how to resolve it?

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

## 11. What are Important Java Multithreading Concepts

**Thread** is the smallest unit of a process that can run independently.

In Java, threads are used to perform multiple tasks concurrently to improve performance.

**Runnable** is an interface used to create a thread in Java. It contains only one method: `run()`.

**Callable** is an interface and similar to Runnable but **returns a result** and can **throw exceptions**.

**ExecutorService** is a framework that manages threads and thread pools.

**ThreadPool** is a group of pre-created threads used to execute tasks, which improves performance and avoids creating threads again and again.

**synchronized** is a keyword used to **prevent multiple threads from accessing the same resource at the same time** and mainly to achieve thread safety

**Lock** (ReentrantLock) is similar to synchronized but gives more control like lock, unlock, tryLock.

**Deadlock** is a situation where two or more threads are blocked forever waiting for each other’s resources.

**CompletableFuture** is used to run asynchronous tasks and combine multiple async operations.

**@Async**  is used in Spring Boot to execute a method asynchronously and used to run a method in **background thread** 



# ✅ 9. Java Advanced Concurrency 

## 1. What is **Concurrency in Java**?

**Concurrency in Java** is the ability of a program to **execute multiple tasks at the same time** by using **multiple threads**. These tasks can run **in parallel on multiple CPU cores** or be **interleaved on a single core** to improve performance and responsiveness.

1. **Thread-based**
* Java provides the **`Thread` class** and **`Runnable` interface** to create and manage concurrent tasks.

```java
// Method 1: extends Thread
class MyThreadTask extends Thread {
    public void run() {
        System.out.println("Task running in thread: " + Thread.currentThread().getName());
    }
}

// Method 2: Implementing Runnable
class MyRunnableTask implements Runnable {
    public void run() {
        System.out.println("Task running in thread: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        
        // Using Thread class
        MyThreadTask t1 = new MyThreadTask();
        t1.start();

        // Using Runnable interface
        Thread t2 = new Thread(new MyRunnableTask());
        t2.start();
    }
}

// Output:
Task running in thread: Thread-1
Task running in thread: Thread-0
```

* **How concurrency is achieved in Java**

* **Thread & Runnable** – basic units of execution.
* **ExecutorService** – manages thread pools and asynchronous tasks.
* **Synchronization** – ensures **thread-safe access** to shared resources.
* **Locks (ReentrantLock)** – flexible locking with reentrant and fairness options.
* **Atomic variables** – **lock-free thread-safe operations** on single variables.
* **Concurrent collections** – thread-safe collections like `ConcurrentHashMap`.
* **CompletableFuture (Java 8+)** – **asynchronous computation** with callbacks and chaining.


* **Concurrency vs Parallelism**

| Concurrency                | Parallelism                             |
| -------------------------- | --------------------------------------- |
| Multiple tasks in progress | Multiple tasks executing simultaneously |
| May run on one CPU         | Requires multiple cores                 |


## 2.  What is ConcurrentHashMap and when to use?


**“`ConcurrentHashMap` is a thread-safe implementation of `Map` that allows multiple threads to read and write data concurrently without locking the entire map.”**


**Why Not `HashMap`**

* ❌ Not thread-safe
* ❌ Data inconsistency in multi-threading
* ❌ Can cause unpredictable behavior


**Why `ConcurrentHashMap`?**

* ✅ Thread-safe
* ✅ Better performance
* ✅ Allows parallel reads & writes
* ❌ Does NOT allow `null` key/value


```java
import java.util.concurrent.ConcurrentHashMap;

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

map.put("A", 1);
map.put("B", 2);
```

**Real-Time Use Cases**

* Caching frequently accessed data (user/session data)
* Counting API hits or tracking events
* Multi-threaded data processing
* Real-time applications (chat apps, dashboards, trading systems)
* Spring Boot in-memory caching / shared state between threads


## 3.  What is a Thread Pool and What are the types of thread pools?

A **Thread Pool** is a collection of **pre-created threads** that are reused to execute multiple tasks.

Instead of creating a **new thread every time a task comes**, the application **takes an available thread from the pool, runs the task, and then returns the thread back to the pool** for reuse.

This **improves performance**, **reduces thread creation overhead**, and **controls the number of threads running in the system**.


* **Fixed Thread Pool** – A fixed number of threads handle tasks.
* **Cached Thread Pool** – Creates new threads when needed and reuses existing ones.
* **Single Thread Pool** – Uses only one thread to execute tasks sequentially.
* **Scheduled Thread Pool** – Executes tasks after a delay or periodically.

**Types of thread pools in Java** (via `Executors`) are:

 ```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        // Fixed Thread Pool
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);

        // Cached Thread Pool
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        // Single Thread Pool
        ExecutorService singlePool = Executors.newSingleThreadExecutor();

        // Scheduled Thread Pool
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);

        // Task
        Runnable task = () -> {
            System.out.println("Task executed by: " + Thread.currentThread().getName());
        };

        // Submit tasks
        fixedPool.submit(task);
        cachedPool.submit(task);
        singlePool.submit(task);

        // Scheduled task (runs after 3 seconds)
        scheduledPool.schedule(task, 3, TimeUnit.SECONDS);

        // Shutdown
        fixedPool.shutdown();
        cachedPool.shutdown();
        singlePool.shutdown();
        scheduledPool.shutdown();
    }
}

// Output:
Task executed by: pool-3-thread-1
Task executed by: pool-1-thread-1
Task executed by: pool-2-thread-1
```

## 4. What is ExecutorService?

**ExecutorService** is a **Java API to manage thread pools and execute tasks asynchronously**, in the background.

It handle **thread creation, reuse, and termination**, and allowing **task tracking with Future**.

**Why Use ExecutorService?**

* Reuses threads (Thread Pool)
* Improves performance
* Controls number of threads
* Used in real-time applications (APIs, DB calls, Microservices)


```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit multiple tasks
        for (int i = 1; i <= 5; i++) {
            int task = i;
            executor.submit(() -> {
                System.out.println("Task " + task + " executed by " 
                                   + Thread.currentThread().getName());
            });
        }

        // Additional tasks
        executor.submit(() -> System.out.println("Task executed"));
        executor.execute(() -> System.out.println("Another task"));

        // Shutdown executor
        executor.shutdown();
    }
}

/// Output: 
Task 2 executed by pool-1-thread-2
Task 1 executed by pool-1-thread-1
Task 3 executed by pool-1-thread-3
Task 4 executed by pool-1-thread-1
Task 5 executed by pool-1-thread-2
Task executed
Another task
```

**Realtime Example:** Food delivery app

* One thread → take order
* One thread → payment
* One thread → send notification
* One thread → update database


## 5. What is CountDownLatch and CyclicBarrier?

**CountDownLatch** is a **synchronization utility** that **blocks threads until a set count reaches zero**, using **countDown() to decrement** and **await() to wait**, and is **one-time use**.

```java
import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);

        Runnable task = () -> {
            try {
                Thread.sleep(1000); // Simulate some work
                System.out.println(Thread.currentThread().getName() + " finished work");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); // Reduce count
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        latch.await(); // Main thread waits

        System.out.println("All tasks completed. Main thread starts");
    }
}

// Output:
Thread-0 finished work
Thread-2 finished work
Thread-1 finished work
All tasks completed. Main thread starts
```

**CyclicBarrier** is a synchronization tool in Java that makes **a group of threads wait for each other** until all threads reach a common point (barrier), and then all threads continue execution.


```java
import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args) {

        // Barrier with 3 threads and barrier action
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("All threads reached barrier. Starting next phase...");
        });

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier");
                
                Thread.sleep(1000); // Simulate work
                
                barrier.await(); // Wait for other threads

                System.out.println(Thread.currentThread().getName() + " crossed barrier");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}

// Output:
Thread-2 reached barrier
Thread-1 reached barrier
Thread-0 reached barrier
All threads reached barrier. Starting next phase...
Thread-0 crossed barrier
Thread-1 crossed barrier
Thread-2 crossed barrier
```

**Difference: CountDownLatch vs CyclicBarrier**

| CountDownLatch          | CyclicBarrier           |
| ----------------------- | ----------------------- |
| One thread waits        | All threads wait        |
| Not reusable            | Reusable                |
| Used for one-time event | Used for repeated tasks |
| countDown()             | await()                 |


## 6. What is ReentrantLock?

**ReentrantLock** is a class in Java (`java.util.concurrent.locks`) that provides an explicit and more flexible locking mechanism than `synchronized`.

It allows:

* Manual `lock()` and `unlock()` control
* Fair or non-fair locking policy
* Interruptible lock acquisition
* `tryLock()` with timeout
* Reentrancy (same thread can acquire the lock multiple times)

```java
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    ReentrantLock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " inside method1");
            method2(); // Same thread can acquire lock again
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " inside method2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test obj = new Test();

        Runnable task = () -> {
            obj.method1();
        };

        new Thread(task).start();
        new Thread(task).start();
    }
}

//Output: 
Thread-0 inside method1
Thread-0 inside method2
Thread-1 inside method1
Thread-1 inside method2
```


## 7. How to call three APIs concurrently:

// Way One

“To call three APIs concurrently in Spring Boot, we can use `CompletableFuture` with `@Async` so that all services like User, Payment, and Inventory are called in parallel, improving performance.”

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


// Way two:: WebClient (Reactive - Non-Blocking)

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


// Way Three::  ExecutorService (Manual Thread Pool)

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

**Immutability** in **Java** means that **once an object is created, its state (data) cannot be changed**. If any modification is needed, a **new object is created instead**.

**Example:**
String is immutable in Java.

```java
String s = "Hello";
s.concat(" World"); // creates a new object, original string is unchanged
```

## 9. What is CompletableFuture and how does it work?

**CompletableFuture** is an advanced version of Future in Java that allows asynchronous, non-blocking programming. It lets you run tasks in the background and chain multiple operations without blocking the main thread.

👉 **It works by:**

* Running tasks asynchronously (`supplyAsync`, `runAsync`)
* Chaining results (`thenApply`, `thenAccept`)
* Combining multiple tasks (`thenCombine`, `allOf`)

```java
import java.util.concurrent.CompletableFuture;

public class Test {
    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Hello")
                                 .thenApply(result -> result + " World");

        System.out.println(future.join()); // Hello World
    }
}
```


## 10. Difference between Future and CompletableFuture

**Future** is used to get the result of an async task, but it is blocking and limited. CompletableFuture is non-blocking and supports chaining, combining, and better async flow control.


| Feature            | Future        | CompletableFuture        |
| ------------------ | ------------- | ------------------------ |
| Blocking           | Yes (`get()`) | No (`thenApply`)         |
| Chaining           | ❌ No          | ✅ Yes                    |
| Combine tasks      | ❌ No          | ✅ Yes                    |
| Manual completion  | ❌ No          | ✅ Yes                    |
| Exception handling | Limited       | Better (`exceptionally`) |

```java
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(() -> "Hello");

        System.out.println(future.get()); // Blocks
    }
}
```

# ✅ 10. Java JVM & Memory Management 

## 0. What is Java Memory Model (JMM)?

**Java Memory Model** defines how threads interact with shared memory in a multithreaded environment. It ensures visibility, atomicity, and ordering using mechanisms like volatile and synchronized.

* **Visibility** – Changes made by one thread are visible to others.
* **Atomicity** – Certain operations are executed completely or not at all.
* **Ordering** – Code execution follows defined rules (happens-before relationship).

## 1. What are the different memory areas in JVM?

JVM divides memory into several distinct areas, each serving specific purposes for program execution and memory management.

**JVM Memory Areas** manage program execution and memory:

* **Heap** – stores **objects and instance variables**, GC-managed.
* **Stack** – stores **local variables and method calls**, auto-managed.
* **Method Area (Metaspace)** – stores **class metadata, static variables, and bytecode**.
* **Program Counter (PC) Register** – tracks **current instruction per thread**.
* **Native Method Stack** – holds **native method execution info**.

## 2. What is the difference between heap and stack?

**Heap memory** is used for dynamic memory allocation.
It stores **objects and instance variables** created using `new`.
Memory in the heap is managed by the **Garbage Collector** in Java.
It is **larger in size**, but slightly slower than stack. Example: `new Student()` object is stored in Heap.

**Stack memory** is used for temporary memory allocation.
It stores **local variables, method calls, and function execution data**.
Memory in the stack is managed automatically — when a method finishes, its memory is removed immediately.
Method variables like `int x = 10` are stored in Stack.


```java
public void method() {
    String obj = new String("Hello"); // obj reference on stack, object on heap
     int x = 10;        // Stack - local variable
}
```

## 3. What is the difference between PermGen and Metaspace?

**PermGen** (Permanent Generation) is a fixed memory area in Java (up to Java 7) used to store class metadata, method information, and interned strings.

**Characteristics:**

* Fixed size (`-XX:PermSize`, `-XX:MaxPermSize`)
* Part of JVM memory
* Can cause `OutOfMemoryError: PermGen space` if full

**Metaspace** (Java 8 onwards) replaces PermGen, storing class metadata in native memory with dynamic sizing, improving memory management.
* Fixed size (`-XX:PermSize`, `-XX:MaxMetaspaceSize`)

**Characteristics:**

* Dynamically resizable (limited by system memory)
* Stored in **native memory** outside the JVM heap
* Reduces PermGen-related memory errors

**Key Differences**

| Feature          | PermGen                           | Metaspace              |
| ---------------- | --------------------------------- | ---------------------- |
| Java Version     | Java 7 and earlier                | Java 8 and later       |
| Stores           | Class metadata + interned strings | Class metadata only    |
| Memory Size      | Fixed                             | Dynamic (resizable)    |
| Location         | JVM memory                        | Native memory          |
| OutOfMemory Risk | High                              | Lower                  |
| Config Option    | `-XX:MaxPermSize`                 | `-XX:MaxMetaspaceSize` |


## 4. What is garbage collection?

**Garbage Collection** is an automatic memory management process in Java in which the JVM **identifies and removes objects that are no longer in use**, freeing up memory.

- Automatic memory cleanup
- Removes unreferenced objects
- Prevents memory leaks
- Runs periodically or when memory is low
- Frees developers from manual memory management

The GC identifies objects with no active references and deallocates their memory, making it available for new objects.

```java
Object obj = new Object();
obj = null; // object becomes eligible for garbage collection
```

## 5. What are the types of garbage collectors?

In Java, **Garbage Collectors (GC)** are responsible for **automatically reclaiming memory** used by objects that are no longer needed. Java provides several types of garbage collectors.

1. **Serial Garbage Collector**
   * **Single-threaded** collector, good for **small applications**.
   * Performs GC **stop-the-world** for minor and major collections.

   ```java
   JVM option: -XX:+UseSerialGC
   ```

2. **Parallel Garbage Collector (Throughput Collector)**
   * **Multi-threaded**, uses multiple threads for **minor GC**.
   * Focused on **high throughput** (less CPU idle time).

   ```text
   JVM option: -XX:+UseParallelGC
   ```

3. **Concurrent Mark-Sweep (CMS) Collector**
   * **Concurrent collector** for **low-latency applications**.
   * Performs **most GC work concurrently with application threads**.

   ```java
   JVM option: -XX:+UseConcMarkSweepGC
   ```

4. **G1 (Garbage-First) Collector**
   * Divides heap into **regions** and collects **garbage in parallel**.
   * Designed for **large heaps and low pause times**.

   ```java
   JVM option: -XX:+UseG1GC
   ```

5. **Z Garbage Collector (ZGC)**
   * **Low-latency, scalable** collector for **very large heaps**.
   * Pauses are typically **<10ms**, even with TB-sized heaps.

   ```java
   JVM option: -XX:+UseZGC
   ```

6. **Shenandoah GC**
   * Focuses on **low pause times** by doing **concurrent compaction**.
   * Available in **OpenJDK 12+**.

   ```java
   JVM option: -XX:+UseShenandoahGC
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

**Generational Garbage Collection** in **Java** is a technique where the **heap is divided into generations** to improve GC efficiency:

* **Young Generation** – Stores newly created objects. Collected frequently (minor GC). Most objects die here quickly.
* **Old/Tenured Generation** – Stores long-lived objects that survived multiple GCs. Collected less frequently (major GC).
* **Permanent/Metaspace** – Stores class metadata and static information.


## 7. What is the difference between minor GC and major GC?

A **Minor GC** occurs in the **Young Generation** of the heap and cleans up short-lived objects like temporary variables. It happens frequently and is usually very fast, causing minimal pause.

A **Major GC** (also called **Full GC**) runs on the **Old Generation** and removes long-lived objects that are no longer needed. It happens less often but takes more time and can significantly impact application performance.


```java
// Objects that survive multiple minor GCs get promoted to Old Generation
List<String> longLived = new ArrayList<>(); // Eventually moves to Old Gen
String temp = "temporary"; // Likely collected in minor GC
```

## 8. What are GC roots?

**GC roots** are objects that are always reachable and serve as starting points for garbage collection reachability analysis. Objects reachable from GC roots are considered live.

```java
public class Example {
    static String staticVar = "root";     // GC root - static variable
    
    public void method() {
        String localVar = "root";         // GC root - local variable
        Object obj = new Object();        // Reachable from localVar
    }
}
```

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

## 4. How do you handle large files efficiently?

For large files, use streaming approaches and buffering to avoid loading entire file into memory.

**Efficient Strategies:**
- Use BufferedReader/Writer for text files
- Process line by line, not entire file
- Use NIO for better performance
- Stream API for functional processing
- Memory-mapped files for very large files

```java
// Stream processing - memory efficient
try (Stream<String> lines = Files.lines(Paths.get("largefile.txt"))) {
    lines.filter(line -> line.contains("keyword"))
         .forEach(System.out::println);
}

// BufferedReader - traditional approach
try (BufferedReader br = Files.newBufferedReader(Paths.get("largefile.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        // Process line by line
    }
}
```

## 5. What is NIO in Java?

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

## 6. What is the difference between IO and NIO?

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

## 7. When would you use NIO over traditional I/O?

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

**Generics in Java** let you **write reusable, type-safe code** using **parameterized types (<>)**, ensuring **compile-time type checking** and **no need for explicit casting**.

```java
// Without generics - requires casting
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // Casting required

// With generics - type safe
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0); // No casting needed
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

**Annotations** are metadata that **provide information about code without affecting its execution**. They're used by compilers, development tools, and frameworks to process code automatically.

```java
@Override
public String toString() {
    return "Example";
}

@Deprecated
public void oldMethod() {
    // Legacy code
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

**@Target** defines **where we can use the annotation** (class, method, field, etc.).


```java
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@interface MyAnnotation {
}
```

**Use:**

```java
@MyAnnotation
class Test {
}
```

**@Documented** means the annotation **will appear in JavaDoc documentation**.

```java
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@interface MyAnnotation {
}
```


**@Inherited** means the annotation **can be inherited from parent class to child class**.

```java
import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Role {
    String value();
}

@Role("Admin")
class Parent {
}

class Child extends Parent {
}
```

Child class automatically gets `@Role`.


## 4. What is retention policy?

Retention policy determines how long annotations are retained - in source code, class files, or at runtime.

**Retention Policies:**
- **SOURCE:** Discarded by compiler (e.g., @Override)
- **CLASS:** Stored in class files but not available at runtime
- **RUNTIME:** Available at runtime via reflection

```java
@Retention(RetentionPolicy.SOURCE)   // Compile-time only
@interface CompileTime { }

@Retention(RetentionPolicy.RUNTIME)  // Available at runtime
@interface RuntimeAnnotation { }
```

## 5. What is the difference between @Override and @Overload?

**@Override** is a **Java annotation** used when a subclass provides a new implementation of a **parent class or interface method** with the same method signature. It helps the compiler catch mistakes if the method doesn’t actually override anything.

**Overloading** is **not an annotation**—it’s a **concept**. Method overloading happens when multiple methods have the **same name but different parameters** (different type, number, or order) within the same class.

```java
class Parent {
    public void method() { }
}

class Child extends Parent {
    @Override
    public void method() { } // Overriding - uses @Override
    
    public void method(int x) { } // Overloading - no annotation needed
    public void method(String s) { } // Another overload
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

## 8. What are the performance implications of reflection?

**Reflection** is significantly slower than direct method calls due to runtime type checking, security checks, and method resolution overhead.

**Performance Issues:**
- Method lookup is expensive
- Security permission checks
- Type validation at runtime
- No compiler optimizations
- Boxing/unboxing for primitives

**Optimization Strategies:**
- Cache Method/Field objects
- Use MethodHandle (Java 7+) for better performance
- Minimize reflection usage in hot paths

```java
// Slow - repeated reflection
for (int i = 0; i < 1000000; i++) {
    Method method = obj.getClass().getMethod("toString");
    method.invoke(obj);
}

// Better - cache method
Method method = obj.getClass().getMethod("toString");
for (int i = 0; i < 1000000; i++) {
    method.invoke(obj);
}
```

## 9. What are the security implications of reflection?

Reflection can bypass normal access controls and security restrictions, potentially exposing private implementation details and creating security vulnerabilities.

**Security Risks:**
- Access private fields and methods
- Bypass encapsulation
- Modify final fields
- Create instances of restricted classes
- Invoke restricted methods

**Security Measures:**
- SecurityManager can restrict reflection
- Use setAccessible() carefully
- Validate inputs when using reflection
- Consider security policies in production

```java
// Reflection can access private members
Field privateField = obj.getClass().getDeclaredField("privateField");
privateField.setAccessible(true); // Bypass access control
Object value = privateField.get(obj); // Access private field
```

## 10. How do you handle exceptions in reflection?

When using **Java Reflection**, several checked exceptions can occur. You must handle them using **try-catch blocks**. Common exceptions include:

**Common Reflection Exceptions:**
- **ClassNotFoundException:** Class not found
- **NoSuchMethodException:** Method doesn't exist
- **IllegalAccessException:** Access denied
- **InvocationTargetException:** Exception in invoked method
- **InstantiationException:** Cannot create instance

```java
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    Method method = clazz.getMethod("myMethod");
    Object result = method.invoke(obj);
} catch (ClassNotFoundException e) {
    // Handle class not found
} catch (NoSuchMethodException e) {
    // Handle method not found
} catch (IllegalAccessException e) {
    // Handle access denied
} catch (InvocationTargetException e) {
    // Handle exception from invoked method
    Throwable cause = e.getCause(); // Get actual exception
}
```

## 11. What is Mockito?

**Mockito** is a testing framework that **creates fake objects** called mocks for unit testing. You can control what these mock objects return when their methods are called, which helps isolate the code you're testing from its dependencies. It's very popular for testing Spring applications.

```java
@Mock
private UserRepository userRepository;

@Test
public void testGetUser() {
    when(userRepository.findById(1L)).thenReturn(new User("John"));
    
    User result = userService.getUser(1L);
    
    assertEquals("John", result.getName());
    verify(userRepository).findById(1L);
}
```

# 14. ✅ Java Web Development - Servlets and JSP

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

## 5. What is Hibernate?

**Hibernate** is an **ORM framework** that helps map Java objects to database tables and perform database operations without writing SQL.


```java
Student student = new Student();
student.setName("John");
session.save(student);
```

## 6. What is JPA?

**Spring JPA** is a **Spring framework module** that simplifies working with **JPA-based data access**. It provides **ready-made repository interfaces** like `CrudRepository` and `JpaRepository`

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

## 7. What is ORM?

**ORM (Object Relational Mapping)** is a technique to map **Java objects ↔ database tables**.

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    private int id;
    private String name;

    // getters and setters
}
```


## 8. Difference between `save()` and `persist()`

| Feature                   | save()                  | persist()          |
| ------------------------- | ----------------------- | ------------------ |
| Belongs to                | Hibernate               | JPA                |
| Return type               | Returns ID              | void               |
| Works outside transaction | Yes                     | No                 |
| Standard                  | No (Hibernate specific) | Yes (JPA standard) |


```java
// save()
int id = (int) session.save(student);

// persist()
entityManager.persist(student);

* **save() → returns generated ID**
* **persist() → does not return anything**
```

## 9. What is JPA and how it works?

**JPA (Java Persistence API)** is a **Java specification** for managing relational data in Java applications. It allows you to **map Java objects to database tables** using annotations like `@Entity`, `@Table`, and `@Id`.

JPA works by using an **EntityManager** to perform CRUD operations. You write **Java objects**, and JPA handles converting them to SQL queries and storing them in the database. Frameworks like **Hibernate** are common implementations of JPA.


```java
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;
    
    // constructors, getters, setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user); // JPA handles the SQL
    }
}
```

## 10. What is the difference between DAO and DTO?

**DAO (Data Access Object)** is used to **interact with the database** and perform CRUD operations like save, update, delete, and fetch data.

**DTO (Data Transfer Object)** is used to **transfer data between layers** of an application (Controller, Service, etc.) and usually contains only fields with getters and setters.

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

---

## 11. What is `@Transactional` Propagation Levels?

Propagation tells Spring — *what should happen to the transaction when one transactional method calls another?*

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

Most of the time you'll use `REQUIRED` or `REQUIRES_NEW`.

---

## 12. What is Optimistic vs Pessimistic Locking?

Both handle concurrent access to the same data — but differently.

**Optimistic Locking** — assumes conflicts are rare. It uses a `@Version` field. When you update, it checks if the version matches. If someone else already updated it, it throws `OptimisticLockException`.

```java
@Entity
public class Product {
    @Version
    private int version;
}
```

**Pessimistic Locking** — assumes conflicts are likely. It locks the row in the database immediately using `SELECT FOR UPDATE`.

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdWithLock(@Param("id") Long id);
```

Use optimistic for read-heavy apps, pessimistic for write-heavy or financial systems.

---

## 13. What is JPQL vs Native Query?

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

---

## 14. What are JPA Cascade Types?

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

---

## 15. What is Database Indexing and When to Use It?

An index is like a book's table of contents — it helps the database find rows faster without scanning the whole table.

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

---

## 16. What is `FetchType.LAZY` vs `FetchType.EAGER` In Depth?

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

The **Stream API**, introduced in **Java 8**, is used to **process collections of data in a functional way**. It allows operations like **filtering, mapping, and sorting** without modifying the original data source.

Streams work in a **pipeline** using **intermediate operations** such as `filter()` and `map()`, and a **terminal operation** like `collect()` or `forEach()` to produce a result. This makes code cleaner, more readable, and supports easy parallel processing.

```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// Stream pipeline
List<String> result = names.stream()
    .filter(name -> name.length() > 3)  // Intermediate
    .map(String::toUpperCase)           // Intermediate
    .sorted()                           // Intermediate
    .collect(Collectors.toList());      // Terminal
```

**Loop vs Stream API** 
Generally, a **for loop is faster** than the Stream API because it has **less overhead** and runs directly in the JVM.
Stream API provides **better readability, functional style, and parallel processing**, but it may have a **small performance cost**.

## 6. What is parallel streams? 

**Parallel streams** in Java are a **Stream API feature** that automatically executes operations **in parallel across multiple threads**.

They use the **ForkJoinPool.commonPool()** by default and are ideal for **CPU-intensive operations on large datasets**, making it easy to leverage **multi-core processors**.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Sequential
int sum = numbers.stream().mapToInt(i -> i * i).sum();

// Parallel - automatically uses multiple threads
int parallelSum = numbers.parallelStream()
    .mapToInt(i -> i * i)
    .sum();
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

In the **Stream API**, **intermediate operations** are operations like `filter()`, `map()`, and `sorted()` that **transform a stream**. They are **lazy**, meaning they don’t execute immediately and return another stream, allowing operations to be chained.

**Terminal operations** are operations like `forEach()`, `collect()`, `reduce()`, and `count()` that **trigger the execution** of the stream pipeline and produce a final result or side effect.

```java
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

# ✅ 16. Java JDBC 

## 1. What is JDBC?

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


## 6. What is SQL injection and how to prevent it?

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

## 7. What is transaction management in JDBC?

**Transaction management** ensures that a group of database operations either all succeed or all fail together, maintaining data consistency and integrity.

**ACID Properties:**
* **Atomicity** – All operations succeed or none (all-or-nothing).
* **Consistency** – Data remains valid and follows all rules.
* **Isolation** – Transactions do not interfere with each other.
* **Durability** – Once committed, data is permanently saved even after a crash.

**Transaction Control:**
- `setAutoCommit(false)` - Start transaction
- `commit()` - Save changes
- `rollback()` - Undo changes
- `setSavepoint()` - Create checkpoint

```java
Connection conn = null;
try {
    conn = DriverManager.getConnection(url, user, password);
    conn.setAutoCommit(false); // Start transaction
    
    // Multiple database operations
    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE id = ?");
    pstmt1.setDouble(1, 100.0);
    pstmt1.setInt(2, 1);
    pstmt1.executeUpdate();
    
    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?");
    pstmt2.setDouble(1, 100.0);
    pstmt2.setInt(2, 2);
    pstmt2.executeUpdate();
    
    conn.commit(); // All operations successful
    
} catch (SQLException e) {
    if (conn != null) {
        conn.rollback(); // Undo all changes
    }
} finally {
    if (conn != null) {
        conn.setAutoCommit(true); // Reset to default
        conn.close();
    }
}
```

## 8. How do you Handle Large Data Processing?

**1. Streaming Large File (Low Memory) – Runnable Code**

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LargeFileProcessor {

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("large-file.txt"))) {
            lines.filter(l -> l.contains("ERROR"))
                 .forEach(LargeFileProcessor::processError);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processError(String line) {
        System.out.println("Processing error: " + line);
    }
}
```

**Important Fix:** Use `try-with-resources` so the file stream is closed.


**2. Batch Processing – Runnable Code**

```java
import java.util.ArrayList;
import java.util.List;

public class BatchProcessingExample {

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 5000; i++) {
            data.add("Record " + i);
        }

        int BATCH = 1000;

        for (int i = 0; i < data.size(); i += BATCH) {
            List<String> batch = data.subList(i, Math.min(i + BATCH, data.size()));
            processBatch(batch);
        }
    }

    private static void processBatch(List<String> batch) {
        System.out.println("Processing batch of size: " + batch.size());
    }
}
```


**3. Database Pagination (Spring Data JPA) – Correct Logic**

```java
int page = 0;
Page<DataItem> result;

do {
    result = repo.findByStatus("PENDING", PageRequest.of(page, 1000));
    processBatch(result.getContent());
    page++;
} while (result.hasNext());
```

**Fix:** Increment `page` after fetching.


**4. Async / Parallel Processing – Correct Code**

```java
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncProcessing {

    public static void main(String[] args) {

        List<Integer> data = List.of(1,2,3,4,5,6,7,8,9,10);

        CompletableFuture.runAsync(() ->
            data.parallelStream()
                .filter(AsyncProcessing::isValid)
                .forEach(AsyncProcessing::save)
        ).join(); // wait for completion
    }

    private static boolean isValid(int num) {
        return num % 2 == 0;
    }

    private static void save(int num) {
        System.out.println("Saving: " + num);
    }
}
```

**Fix:** Add `.join()` so main thread waits.


**5. Memory Efficient Cache – Correct Code**

```java
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeakCacheExample {

    private static Map<String, WeakReference<Data>> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Data data = new Data("Sample");
        cache.put("key1", new WeakReference<>(data));

        Data cachedData = cache.get("key1").get();

        if (cachedData != null) {
            System.out.println("From cache: " + cachedData.value);
        } else {
            System.out.println("Object was garbage collected");
        }
    }

    static class Data {
        String value;
        Data(String value) {
            this.value = value;
        }
    }
}
```


If interviewer asks **“Which one do you use in real project?”**, answer:

| Scenario         | Method     |
| ---------------- | ---------- |
| Large file       | Streaming  |
| Large DB data    | Pagination |
| Large processing | Batch      |
| Performance      | Parallel   |
| Frequent reads   | Cache      |

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

## 10. How can we configure multiple databases in Spring Boot?

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


## 11. What is a Cursor in SQL and when should it be used ?

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

## 12. How to secure username and password?

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


## 13. What is Batch Processing?

Processing records in **small fixed-size chunks** (like 1000 records per batch)

**Why use it?**

* Handles large data safely
* Supports retry & restart
* Good for ETL jobs
* Production-ready

```java
.chunk(1000)
```


# ✅ 17. Java Design Patterns 

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

## 2. What is Singleton pattern?

**Singleton Pattern** is a design pattern that ensures a **class has only one object (instance)** and provides a **global access point** to that instance.

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

**Factory pattern** creates objects without specifying their exact classes. It provides an interface for creating objects but lets subclasses decide which class to instantiate.

```java
// Product interface
interface Animal {
    void makeSound();
}

// Concrete products
class Dog implements Animal {
    public void makeSound() { System.out.println("Woof"); }
}

class Cat implements Animal {
    public void makeSound() { System.out.println("Meow"); }
}

// Factory
class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type) {
            case "dog": return new Dog();
            case "cat": return new Cat();
            default: throw new IllegalArgumentException("Unknown animal");
        }
    }
}
```

## 5. What is Observer pattern?

**Observer pattern** defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

```java
// Observer interface
interface Observer {
    void update(String message);
}

// Subject
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

// Concrete observer
class NewsChannel implements Observer {
    public void update(String news) {
        System.out.println("Breaking news: " + news);
    }
}
```

## 6. What is Strategy pattern?

**Strategy pattern** defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
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
    
    public void play(String audioType, String fileName) {
        if (audioType.equals("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equals("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}
```

## 8. What is Decorator pattern?

**Decorator pattern** allows behavior to be added to objects dynamically without altering their structure. It provides a flexible alternative to subclassing for extending functionality.

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

The **Builder Pattern** is used to create complex objects step by step. It is useful when an object has many optional fields and we want readable object creation.

**Simple Real-Life Example**

Think of ordering a **Burger**:

* Bread
* Cheese
* Egg
* Chicken
* Sauce

You choose items step by step → then the burger is built.
This is Builder Pattern.


**Step 1: Product Class**

```java
class Burger {
    private String bread;
    private String cheese;
    private String sauce;

    private Burger(BurgerBuilder builder) {
        this.bread = builder.bread;
        this.cheese = builder.cheese;
        this.sauce = builder.sauce;
    }

    public void showBurger() {
        System.out.println(bread + " " + cheese + " " + sauce);
    }

    public static class BurgerBuilder {
        private String bread;
        private String cheese;
        private String sauce;

        public BurgerBuilder setBread(String bread) {
            this.bread = bread;
            return this;
        }

        public BurgerBuilder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public BurgerBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}
```

**Step 2: Use Builder**

```java
public class Main {
    public static void main(String[] args) {
        Burger burger = new Burger.BurgerBuilder()
                .setBread("Wheat Bread")
                .setCheese("Cheddar")
                .setSauce("Mayo")
                .build();

        burger.showBurger();
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

## 2. What are the core features of Spring?

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

**Dependency Injection (DI)** is a design pattern where an object's **dependencies are provided externally** rather than the object creating them itself.

In Java and Spring, DI helps make code **loosely coupled, easier to test, and more maintainable**. It can be implemented via **constructor injection, setter injection, or field injection**.

**There are 3 main types of DI:**
1. Constructor Injection – dependencies injected through constructor (recommended)
2. Setter Injection – dependencies injected through setter method
3. Field Injection – dependencies injected directly into field using @Autowired

**1. Constructor Injection (Recommended)**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

@Component
class Car {
    private Engine engine;

    @Autowired
    public Car(Engine engine) {   // Constructor Injection
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is running");
    }
}
```


**2. Setter Injection**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {   // Setter Injection
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is running");
    }
}
```


**3. Field Injection**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Car {

    @Autowired   // Field Injection
    private Engine engine;

    public void drive() {
        engine.start();
        System.out.println("Car is running");
    }
}
```

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

## 7. What is the difference between Java Bean and Spring Bean?

A **Java Bean** is a simple Java class with private fields, getters/setters, and a no-argument constructor, and it is created manually using the `new` keyword.

```java
public class User {
    private String name;

    public User() {}   // no-arg constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Usage:

```java
User user = new User();
user.setName("John");
```

A **Spring Bean** is an object that is created and managed by the **Spring IoC container**, and it supports features like dependency injection, lifecycle management, and configuration.

```java
@Component
public class UserService {

    public void printUser() {
        System.out.println("User Service Running");
    }
}
```

Usage (Dependency Injection):

```java
@Autowired
private UserService userService;
```

## 8. What is Bean Lifecycle?

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

## 9. What is AOP in Spring?

**AOP (Aspect Oriented Programming)** is a programming concept used to **separate cross-cutting concerns** like **logging, security, transactions, and exception handling** from the main business logic.

Instead of writing this code in every method, AOP lets us **apply it automatically across multiple methods**.

**Key Concepts in AOP**

* **Aspect** – Class that contains cross-cutting logic (e.g., logging).
* **Advice** – When the code should run (before, after, around).
* **Join Point** – A point in program execution (method call).
* **Pointcut** – Expression that selects join points.

```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }
}
```

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

## 14 How Does Spring Handle Circular Dependency?

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


## 4. What is @Configuration and @Bean?


**@Configuration** is used to declare a class as a **Spring configuration class**.
It tells the **Spring Framework** that this class contains bean definitions.

**Purpose:**

* Marks the class as a configuration class
* Replaces old XML-based configuration
* Tells Spring: “Look here for object creation logic”


**@Bean** is an object that is created, managed, and stored by the Spring IoC container. Instead of creating objects using `new`, Spring creates and injects them automatically.

**Step 1 — Create a Bean Class**

```java
public class GreetingService {

    public String greet() {
        return "Hello, Welcome to Spring!";
    }
}
```

**Step 2 — Configuration Class**

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GreetingService greetingService() {
        return new GreetingService();
    }
}
```

**Step 3 — Use the Bean**

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        GreetingService service = context.getBean(GreetingService.class);
        System.out.println(service.greet());
    }
}

// Output:
Hello, Welcome to Spring!
```

**`@Bean` vs `@Component` (Quick Difference)**

| Feature    | `@Bean`                        | `@Component`             |
| ---------- | ------------------------------ | ------------------------ |
| Defined in | Method inside config class     | Directly on class        |
| Control    | More manual control            | Auto-detected (scanning) |
| Use case   | Third-party or complex objects | Your own classes         |


## 5. How can you disable specific auto-configurations in Spring Boot?

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

## 6. What is @SpringBootApplication annotation?

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

## 7. @Component vs @Service vs @Repository vs @Controller vs @RestController annotations?


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

**@Component** is a generic annotation and is used to create a Spring-managed bean automatically using component scanning..
```java
@Component
public class EmailUtil {
    public void sendEmail() {
        System.out.println("Sending Email");
    }
}
```

## 8. What is @Autowired vs @Inject annotation?

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

## 9. What is @Profile Annotation?

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


## 10. What is ApplicationContext?

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


## 11. What is @Primary, @Qualifier, @Component, @Configuration, @PatchMapping annotation?

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

## 12. Explain Spring Boot Actuator endpoints.

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

## 13. How do you ensure security a Spring Boot Microservices application?

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
## 14. What is Lombok in Java and and whe can we use?


## 🚀 What is Lombok in Java?

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

---

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

## 15. Why do we use Long in JpaRepository<Employee, Long>?
In **`JpaRepository<Employee, Long>`**, the **first type (`Employee`)** is the **entity class** the repository manages, and the **second type (`Long`)** is the **type of the entity’s primary key (`@Id`)**.

Using `Long` tells Spring Data JPA what type of value to expect when performing operations like `findById()`, `deleteById()`, or `save()`.


## 16. What is Distributed Tracing?

**Distributed Tracing** is a technique used in **microservices architecture** to track and monitor a request as it travels across multiple services.

It helps developers **identify performance issues, delays, and failures** by showing the **complete flow of a request across different services in a system**.

**Real-Time Example**

1. **API Gateway** receives request
2. **Order Service** processes order
3. **Payment Service** processes payment
4. **Inventory Service** updates stock
5. **Notification Service** sends email/SMS

## 17. What is Spring Scheduler?

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

## 18. How would you set up a logging level for Spring Boot (e.g., debug, info, error)?

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

## 19. ---

 
## 20. What is `@PostConstruct`, `@PreDestroy` and `@Scope` in Spring Boot?

**@PostConstruct** annotations is used to run initialization logic after Spring creates the bean and injects dependencies.

**@PreDestroy** annotation is used to clean up resources before the bean is destroyed.

`@Scope` is a **Spring annotation** used to define **how many objects (beans) Spring should create** for a class.

**Lifecycle order:** Constructor → Dependency Injection → @PostConstruct → Bean Ready → @PreDestroy → Destruction

```java
@Component
@Scope("prototype")  // new object every time
public class MyService {

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean destroyed");
    }
}
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

## 24. How Does `@Transactional` Work Internally?

Spring uses **AOP (Aspect-Oriented Programming)** under the hood. When you annotate a method with `@Transactional`, Spring creates a **proxy** around your bean.

When the method is called:
1. The proxy intercepts the call
2. Opens a transaction (or joins an existing one based on propagation)
3. Executes your method
4. If no exception → commits the transaction
5. If a `RuntimeException` is thrown → rolls back

```java
@Transactional
public void transferMoney(Long from, Long to, double amount) {
    debit(from, amount);
    credit(to, amount);  // if this throws, debit also rolls back
}
```

**Important gotcha:** `@Transactional` only works on **public methods** and only when called from **outside the class** (because the proxy is bypassed on self-invocation).

---

## 25. How Does `@EnableAutoConfiguration` Work Internally?

It's the magic behind Spring Boot's "convention over configuration."

When your app starts:
1. Spring Boot scans `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` (or `spring.factories` in older versions)
2. That file lists hundreds of auto-configuration classes
3. Each class is annotated with `@ConditionalOn...` — so it only activates if certain conditions are met (e.g., a class is on the classpath, a bean is missing, a property is set)

```java
@ConditionalOnClass(DataSource.class)
@ConditionalOnMissingBean(DataSource.class)
public class DataSourceAutoConfiguration { ... }
```

So if you add `spring-boot-starter-data-jpa`, Spring Boot automatically configures a `DataSource`, `EntityManagerFactory`, and transaction manager — without you writing any config.

`@SpringBootApplication` = `@Configuration` + `@ComponentScan` + `@EnableAutoConfiguration` combined.

---

## 26. What is `@Async` and How Does It Work?

`@Async` runs a method in a **separate thread** — so the caller doesn't wait for it to finish.

**Setup:**
```java
@SpringBootApplication
@EnableAsync
public class MyApp { }
```

```java
@Async
public void sendEmail(String to) {
    // runs in a background thread
    emailService.send(to);
}
```

Internally, Spring wraps the method in a proxy and submits it to a `TaskExecutor` (thread pool). By default it uses `SimpleAsyncTaskExecutor` — but in production, always configure a proper thread pool:

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

**Gotcha:** Same as `@Transactional` — self-invocation won't work. Must be called from another bean.

If you need the result, return `CompletableFuture<T>` instead of `void`.

---

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

---

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

---

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


# ✅ 20. RESTful Services 

## 1. What is CORS, and how does it work?

**CORS (Cross-Origin Resource Sharing)** is a **browser security mechanism** that restricts cross-domain requests by default.

It works by allowing the **server to send special HTTP headers** (like `Access-Control-Allow-Origin`) to specify which domains are permitted to access its resources.


```java
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {
    @GetMapping("/api/data")
    public ResponseEntity<Data> getData() {
        return ResponseEntity.ok(data);
    }
}

// Global CORS configuration
@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        return source;
    }
}
```

## 2. What is an API and what are different type of API?

**API (Application Programming Interface)** is a **set of rules that allows different software applications to communicate with each other**.

**Types of APIs:**

* **REST API** – Uses HTTP methods (GET, POST, PUT, DELETE). Commonly uses JSON format.
* **SOAP API** – is a Protocol-based, uses XML messaging. used in enterprise and banking systems
* **GraphQL API** – It is query-based API technology, Allows clients to request specific data.
* **gRPC API** – It is High-performance API framework, it uses Protocol Buffers for fast communication.
* **WebSocket API** – Enables real-time, two-way communication.


## 3. What are RESTful web services?

**RESTful web services** are **web services based on REST architecture** they use **HTTP methods (GET, POST, PUT, DELETE)** to operate on **resources identified by URLs**.

They are **stateless, platform-independent**, and typically exchange data in **JSON or XML** format.


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

**HTTP** methods define the type of operation to be performed on a resource. Each method has a specific purpose and semantic meaning in RESTful services.

**Common HTTP Methods:**
- **GET:** Retrieve data (read-only)
- **POST:** Create new resources
- **PUT:** Update/replace entire resource
- **PATCH:** Partial update of resource
- **DELETE:** Remove resource
- **HEAD:** Get headers only (no body)
- **OPTIONS:** Get allowed methods

```java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/{id}")        // GET /users/1
    public User getUser(@PathVariable Long id) { }
    
    @PostMapping               // POST /users
    public User createUser(@RequestBody User user) { }
    
    @PutMapping("/{id}")       // PUT /users/1
    public User updateUser(@PathVariable Long id, @RequestBody User user) { }
    
    @DeleteMapping("/{id}")    // DELETE /users/1
    public void deleteUser(@PathVariable Long id) { }
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

## 1. What are CQRS principles?
**CQRS (Command Query Responsibility Segregation)** is an architectural pattern that **separates read operations (queries) from write operations (commands)** in an application.

* **Command** → Used to **create, update, or delete data**
* **Query** → Used to **read or retrieve data**

**Why Use CQRS?**

* Improves **performance**
* Allows **separate scaling of read and write operations**
* Makes the system **more maintainable**

```java
// Command (Write)
public void createUser(User user) {
    userRepository.save(user);
}

// Query (Read)
public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
}
```

## 2. Blocking vs No blocking db call in Microservice?


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


## 3. What are microservices?

**Microservices** are an architectural style where an application is built as **small, independent services**, each handling a **single business function**.

They **communicate via APIs**, are **independently deployable**, **technology-agnostic**, and can be **developed and scaled separately**.

## 4. What design patterns used in Microservices architecture?

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

## 5. Monolithic vs Microservices Architecture

**Monolithic Architecture:**
All components (UI, business logic, database) are built and deployed as a **single unit**. It is simple to develop and deploy initially, but becomes difficult to scale and maintain as the application grows.

**Microservices Architecture:**
The application is divided into **small, independent services** that communicate via APIs. Each service can be developed, deployed, and scaled independently, but it increases complexity in communication, monitoring, and deployment.

So, **monolithic is simple but less scalable**, while **microservices are scalable and flexible but more complex**.


Trade-offs:
- Monolithic: Simple deployment, shared database, single technology stack
- Microservices: Independent scaling, technology diversity, complex deployment
- Choose monolithic for small teams, microservices for large organizations

## 6. What are the advantages of microservices?

Microservices offer several benefits over monolithic architectures, particularly for large, complex applications and organizations.

**Key Advantages:**
- **Independent deployment:** Deploy services separately
- **Technology diversity:** Different tech stacks per service
- **Scalability:** Scale individual services based on demand
- **Fault isolation:** Failure in one service doesn't crash entire system
- **Team autonomy:** Small teams own complete services
- **Faster development:** Parallel development of services

These benefits enable organizations to move faster, scale better, and maintain more resilient systems.

## 7. What are the challenges of microservices?

While microservices offer many benefits, they also introduce complexity and challenges that must be carefully managed.

**Major Challenges:**
- **Distributed system complexity:** Network calls, latency, failures
- **Data consistency:** Managing transactions across services
- **Service communication:** Inter-service communication overhead
- **Monitoring and debugging:** Tracing requests across services
- **Deployment complexity:** Managing multiple services
- **Testing challenges:** Integration and end-to-end testing

Organizations need proper tooling, processes, and expertise to handle these challenges effectively.

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

**Using RestTemplate**

```java
// Step 1: Create RestTemplate Bean
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

```java
// Step 2: Call Payment Service
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{orderId}")
    public PaymentResponse getOrder(@PathVariable Long orderId) {

        String url = "http://localhost:8081/payments/" + orderId;

        return restTemplate.getForObject(url, PaymentResponse.class);
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

**Answer**

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
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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

## 10. ---


## 11. What is Event-Driven Architecture in Java?

**Event-Driven Architecture (EDA)** is a design pattern where **one service produces an event and another service consumes the event** and performs some action.

**Real-Time Example: E-commerce Application:**

* User places order → Event: *OrderCreated*
* Payment service listens → processes payment
* Email service listens → sends email
* Inventory service listens → updates stock

All services work **independently**.


**Flow Diagram**

```
Order Service → Event → Payment Service
                      → Email Service
                      → Inventory Service
```

Using Spring Events:

```java
// Event Class
import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    private String orderId;

    public OrderEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
```

```java
// Publisher
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void createOrder() {
        System.out.println("Order Created");
        publisher.publishEvent(new OrderEvent(this, "123"));
    }
}
```

```java
// Listener
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @EventListener
    public void handleOrderEvent(OrderEvent event) {
        System.out.println("Send Email for Order " + event.getOrderId());
    }
}
```

**Tools Used in Real Projects**

* Apache Kafka
* RabbitMQ
* ActiveMQ

Used with **Spring Boot** microservices.


| Advantage      | Description          |
| -------------- | -------------------- |
| Loose coupling | Services independent |
| Scalable       | Easy to scale        |
| Faster         | Async processing     |
| Reliable       | Events stored        |


## 12. What is API Gateway?

**Answer**

An **API Gateway** is a single entry point for all client requests in a microservices architecture.

It handles **routing, authentication, rate limiting, logging, and load balancing**, and forwards requests to appropriate backend services, improving security and simplifying client communication.

```
Client
   |
API Gateway
 |    |    |
MS1  MS2  MS3
```


**Basic Configuration (application.yml)**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

```yaml
server:
  port: 8080

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

## 13. What is circuit breaker pattern?

The **Circuit Breaker pattern** is a design pattern used in **microservices** to prevent cascading failures. When a service repeatedly fails or becomes slow, the circuit breaker **opens** and temporarily blocks calls to that service.

After a cooldown period, it moves to a **half-open** state to test if the service has recovered. If successful, the circuit closes and normal traffic resumes. This improves **system resilience and stability**.

**Circuit States:**
- **Closed:** Normal operation, requests pass through
- **Open:** Failures detected, requests fail immediately
- **Half-Open:** Testing if service has recovered

**Benefits:**
- Prevents cascading failures
- Faster failure detection
- Automatic recovery
- Improves system resilience

```java
// Circuit breaker with Resilience4j
@Component
public class UserServiceClient {
    
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    @TimeLimiter(name = "user-service")
    public CompletableFuture<User> getUser(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            // Call to user service
            return restTemplate.getForObject("/users/" + id, User.class);
        });
    }
    
    // Fallback method when circuit is open
    public CompletableFuture<User> fallbackUser(Long id, Exception ex) {
        return CompletableFuture.completedFuture(
            new User(id, "Default User", "default@example.com")
        );
    }
}

// Configuration
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        failure-rate-threshold: 50
        wait-duration-in-open-state: 30s
        sliding-window-size: 10
```

## 14: What is resilience4j pattern?

Resilience4j is a fault tolerance library used in microservices to make services resilient when dependent services fail.
It provides patterns like Circuit Breaker, Retry, Rate Limiter, Bulkhead, and Timeout to prevent system failure.


**Why we use Resilience4j in real-time**

* Order Service calls Payment Service
* Payment Service is slow or down
* Without protection → Order Service will also fail
* With Resilience4j → It will stop calling temporarily and return fallback response

This prevents **system crash / cascading failure**.

**Main Modules in Resilience4j**

| Module          | Purpose                      |
| --------------- | ---------------------------- |
| Circuit Breaker | Stops calling failed service |
| Retry           | Retry failed request         |
| Rate Limiter    | Limit number of requests     |
| Bulkhead        | Limit concurrent users       |
| Time Limiter    | Timeout for slow service     |


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


## 15. What is service discovery?

**Service Discovery** is a mechanism in microservices architecture where services automatically find and communicate with each other without hardcoding their IP addresses.

Service Discovery is used in microservices to automatically find the location (IP address and port) of services.
Because in microservices, service instances change dynamically, we cannot hardcode URLs.
So services register themselves in a Service Registry, and other services discover and call them.

**Example Flow:**

1. Payment Service registers in Service Registry
2. Order Service asks registry: “Where is Payment Service?”
3. Registry returns IP
4. Order Service calls Payment Service


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
    name: payment-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

```java
// Service registration with Eureka
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

// Service discovery usage
@RestController
public class OrderController {
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    public void callUserService() {
        List<ServiceInstance> instances = 
            discoveryClient.getInstances("user-service");
        String url = instances.get(0).getUri().toString();
        // Make HTTP call to user service
    }
}
```


## 16. What is Saga Pattern or How it handle payment failure?

Saga Pattern is used in **microservices architecture** to manage transactions across multiple services. Instead of one big transaction, the process is divided into **multiple small local transactions**. Each service completes its own step.

If any step fails, the system performs **compensating actions** to undo the previous steps and keep data consistent.

**Use when:**
- Microservices architecture
- Each service has its own database
- Distributed transaction

**Example:**
- In an online order system:
- Order Created → Payment Done → Inventory Reserved.

If inventory fails, Saga will **refund payment and cancel the order**.

There are **two ways to implement Saga**:
- **Choreography** – services communicate using events.
- **Orchestration** – a central service controls the workflow.

Order Created → Payment Done → Inventory Reserved
If **inventory fails → refund payment + cancel order**

```java
@Service
public class OrderSagaService {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderService orderService;

    public void placeOrder(Order order) {
        orderService.createOrder(order);
        try {
            paymentService.charge(order);        // Step 1
            inventoryService.reserve(order);     // Step 2
            orderService.updateStatus(order, "COMPLETED");

        } catch (Exception e) {
            // Compensation actions
            paymentService.refund(order);
            orderService.cancelOrder(order);
            System.out.println("Order failed, rollback completed");
        }
    }
}
```

**Payment Service**
```java
@Service
public class PaymentService {
    public void charge(Order order) {
        System.out.println("Payment charged for order " + order.getId());
    }

    public void refund(Order order) {
        System.out.println("Payment refunded for order " + order.getId());
    }
}
```

**Inventory Service**
```java
@Service
public class InventoryService {
    public void reserve(Order order) {
        System.out.println("Inventory reserved for order " + order.getId());
        
        // simulate failure
        throw new RuntimeException("Inventory not available");
    }
}
```

**Flow**
1. Create Order
2. Charge Payment
3. Reserve Inventory
4. If inventory fails → **Refund Payment + Cancel Order**


## 17. What is a transaction (ACID properties)? How do you handle rollback?

A **transaction** is a group of database operations that are executed as **one single unit of work**.

All operations succeed → COMMIT
Any operation fails → ROLLBACK


It follows **ACID properties:**

| Property    | Meaning                            |
| ----------- | ---------------------------------- |
| Atomicity   | All operations succeed or all fail |
| Consistency | Data remains valid                 |
| Isolation   | Transactions don’t interfere       |
| Durability  | Data is saved permanently          |

**Example (Bank Transfer)**

Transfer ₹100 from Account A → Account B:

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

---

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

---

**3. Rollback for Checked Exception**

By default, Spring rolls back only for **RuntimeException**.

```java
@Transactional(rollbackFor = Exception.class)
public void transferMoney() throws Exception {
    debit();
    credit();
}
```

---

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

## 20. Java 11 HttpClient API, and how does it differ from earlier Java versions?

In **Java 11**, the `HttpClient` API was introduced in the `java.net.http` package to simplify making HTTP requests. It supports **HTTP/1.1 and HTTP/2**, provides a **clean and fluent API**, and allows both **synchronous and asynchronous requests** using `CompletableFuture`.

For example, we create an `HttpClient`, build an `HttpRequest`, and then send it using the `send()` method.

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
```

## 21. What is Kafka and How Does It Work?

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
// Producer
kafkaTemplate.send("order-topic", "order-created");

// Consumer
@KafkaListener(topics = "order-topic", groupId = "order-group")
public void consume(String message) {
    System.out.println("Received: " + message);
}
```

Use Kafka for: event sourcing, log aggregation, real-time analytics, microservice communication at scale.

---

## 22. What is RabbitMQ and When to Use It Over Kafka?

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

---

## 23. What is gRPC and How Does It Differ from REST?

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

---

## 24. What is a Service Mesh (Istio)?

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

---

## 25. What is Zipkin and How Does Distributed Tracing Work?

In microservices, a single request can pass through 5–10 services. When something fails or is slow, how do you find where? That's what **distributed tracing** solves.

**Zipkin** is a distributed tracing system. It collects timing data from all services and lets you visualize the full request journey.

**How it works:**
1. Each request gets a unique **Trace ID**
2. Each service call within that request gets a **Span ID**
3. Services report their spans (start time, duration, status) to Zipkin
4. Zipkin assembles them into a full trace timeline

**Spring Boot setup:**
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
      probability: 1.0  # trace 100% of requests
```

```
Request → [Service A] → [Service B] → [Service C]
              span1          span2          span3
              └──────── Trace ID: abc123 ──────────┘
```

In Zipkin UI, you can see exactly which service was slow or failed. Often used with **Sleuth** (older) or **Micrometer Tracing** (Spring Boot 3+).


# ✅ 22. Java and Application Security


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


## 11: What is JWT (JSON Web Token)?

**JWT (JSON Web Token)** is a **compact, URL-safe token** used for secure data transmission.

It has three parts: **Header.Payload.Signature**, is **stateless and self-contained**, and is commonly used for **authentication and API authorization**, being **signed (optionally encrypted)** for security.

```java
// JWT creation and validation
@Service
public class JwtService {
    private String secretKey = "mySecretKey";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            .getBody().getSubject();
    }
}
```


## 12: What is CSRF protection?

**CSRF (Cross-Site Request Forgery)** is a security attack where a **malicious website tricks a logged-in user into performing an unwanted action** on another website.

* You log in to your bank
* You open a malicious website in another tab
* That site sends a request to transfer money from your bank
* Since you are already logged in → request is executed
* This is **CSRF attack**


```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
```

**JWT-based REST APIs usually disable CSRF** because they don’t use session cookies.

```java
http.csrf().disable();
```


## 13: What is XSS protection?

**XSS is an attack where malicious scripts are injected into web pages and executed in users' browsers, and it is prevented using input validation, output encoding, and security headers.**

**Simple Example (XSS Attack)**
```html
<script>alert('Hacked!')</script>
```

**How to Prevent XSS (XSS Protection)**

**1. Input Validation**

* Do not allow script tags
* Validate user input


**2. Output Encoding (Most Important)**

Convert special characters:

```html
< → &lt;
> → &gt;
```

**3. Use HTTP Security Headers**

* Content-Security-Policy (CSP)
* X-XSS-Protection


**4. Use Framework Security**

In **Spring Security**:

* Escapes output automatically in many cases
* Prevents common attacks


**5. Avoid Direct HTML Rendering**

Avoid:

```java
out.println(userInput);
```


## 14: What is input validation?

**Input validation** is the process of **checking user input for correctness and security**.

It should be done on the **server side** (never trust client), use a **whitelist approach**, apply **sanitization**, and can use **Bean Validation annotations** like `@Valid`, `@NotNull`, and `@Pattern`.


```java
// Input validation with Bean Validation
public class UserRegistration {
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Invalid username format")
    private String username;
    
    @Email(message = "Invalid email format")
    private String email;
    
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}

@PostMapping("/register")
public ResponseEntity<String> register(@Valid @RequestBody UserRegistration user) {
    // Validation automatically applied
    return ResponseEntity.ok("User registered successfully");
}
```


## 15: What is SAML?

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

# ✅ 23. Java Performance and Optimization


## 0: How do you measure and Monitor application performance in Java?

**Application monitoring** is the continuous tracking of an application's **performance, health, and behavior in production**.

It includes monitoring metrics like response time, errors, CPU, and memory, along with logs and traces, using tools like **Prometheus**, **Grafana**, **New Relic**, **Datadog**, and **AppDynamics** to detect and resolve issues proactively.

**Production Monitoring Tools (Simple Detailed Table)**

| Tool              | Type            | Why We Use It                               | What It Monitors                                      | Example                     |
| ----------------- | --------------- | ------------------------------------------- | ----------------------------------------------------- | --------------------------- |
| Prometheus        | Metrics         | Collect metrics from application            | CPU, Memory, Request count, Error rate, Response time | API response time = 200ms   |
| Grafana           | Dashboard       | Show metrics in graphs                      | Dashboard, Alerts, Charts                             | CPU usage graph             |
| Micrometer        | Metrics Library | Send metrics from Spring Boot to Prometheus | JVM, Custom metrics                                   | Heap memory                 |
| ELK Stack         | Logging         | Store and search logs                       | Error logs, Application logs                          | Exception logs              |
| Spring Boot Admin | Monitoring      | Monitor Spring Boot apps                    | Health, Beans, Endpoints                              | App status UP/DOWN          |
| Zipkin            | Tracing         | Track request flow between services         | Service call flow                                     | Order → Payment → Inventory |
| Jaeger            | Tracing         | Track microservice request                  | API calls                                             | Request time per service    |
| Datadog           | APM             | Full system monitoring                      | Infra, Logs, APIs                                     | Server CPU                  |
| New Relic         | APM             | Application performance monitoring          | Slow API, DB calls                                    | Slow query                  |
| Dynatrace         | APM             | AI monitoring                               | Full stack                                            | Root cause detection        |
| AWS CloudWatch    | Cloud           | Monitor AWS services                        | EC2, RDS, Logs                                        | EC2 CPU                     |


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


## 1. What are common Java performance issues?

Common **Java performance issues** include **memory leaks** (objects not garbage collected), **CPU bottlenecks** (inefficient code or blocking calls), **database problems** (slow queries or connection pool issues), and **thread contention** (threads competing for shared resources).
Here is a **simple one-line explanation for each point**:

* **Memory Leaks** – Objects stay in memory and are not removed by the Java Garbage Collector, increasing memory usage over time.
* **CPU Bottlenecks / Inefficient Algorithms** – Poor algorithms or unnecessary loops increase CPU usage and slow the application.
* **Database Issues** – Slow queries or poor connection pool management delay database responses.
* **Thread Contention** – Multiple threads compete for the same resource, causing delays and blocking.
* **Too Many Object Creations** – Creating many objects increases memory usage and garbage collection work.
* **Garbage Collection Overhead** – Frequent garbage collection pauses the application and affects performance.
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

## 2. How do you Improve Performance(optimize) in Spring Boot Application?
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


## 3. What are Java memory issues?
* **OutOfMemoryError :** - This happens when the JVM heap memory is full and cannot allocate new objects.
* **Memory leaks :** - A memory leak happens when objects are no longer needed but are still referenced, so the Garbage Collector cannot remove them.
* **Excessive Object Creation :** - Creating too many objects repeatedly increases memory usage and garbage collection activity, which slows down the application.
* **Metaspace issues :** - In some applications (like servers), classes loaded by a ClassLoader are not released, causing Metaspace memory issues. Too many classes loaded
* **Improper Cache Management :** - If caching is implemented without limits, cached objects can keep growing and consume memory.

```java
// Stack overflow example
public void recursiveMethod() {
    recursiveMethod(); // No base case - stack overflow
}

// Memory optimization
List<String> list = new ArrayList<>(1000); // Pre-size collections
```

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


## 4: What is JVM tuning?

**JVM tuning** is the process of **optimizing JVM settings** for better performance.

It includes configuring **heap size (-Xms, -Xmx)**, selecting the right **GC algorithm**, adjusting **thread stack and metaspace**, tuning **GC parameters**, and using **monitoring tools and GC logs**.

* Process of optimizing JVM parameters for better performance
* **Heap Size**: -Xms (initial) and -Xmx (maximum) heap size
* **Garbage Collection**: Choose appropriate GC algorithm
* **Thread Stack**: -Xss for thread stack size
* **Metaspace**: -XX:MetaspaceSize for class metadata
* **GC Tuning**: -XX:NewRatio, -XX:SurvivorRatio for generation sizes
* **Monitoring**: Enable GC logging and JFR

```bash
# Common JVM tuning parameters
java -Xms2g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+PrintGC \
     -XX:+PrintGCDetails \
     -XX:+PrintGCTimeStamps \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/tmp/heapdump.hprof \
     -jar myapp.jar

# G1GC tuning for low latency
-XX:+UseG1GC
-XX:MaxGCPauseMillis=100
-XX:G1HeapRegionSize=16m
```


## 5: What are the JVM parameters for performance tuning?

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

**Query optimization** is the process of improving the performance and execution time of SQL queries.

It involves **using proper indexes, writing efficient joins and WHERE clauses, avoiding unnecessary data fetch (like SELECT *), and analyzing execution plans** to ensure faster query execution.


```java
// Query optimization examples
@Repository
public class OptimizedQueryRepository {
    
    // Bad: N+1 query problem
    // List<Order> orders = orderRepository.findAll();
    // orders.forEach(order -> order.getCustomer().getName()); // N queries
    
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

**Lazy loading** is a design pattern where data is loaded **only when it is actually needed**, instead of loading everything at once.

It improves performance and reduces memory usage, but if not handled properly, it can cause issues like the **N+1 query problem**.

* **Proxy Objects**: Hibernate creates proxies for lazy-loaded entities

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


## 14: What is eager loading?

**Eager loading** is a strategy where related data is **loaded immediately along with the main entity**.

It reduces additional database queries later, but increases **initial load time and memory usage**, so it should be used only when the related data is definitely needed.

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

# ✅ 24. Modern Java Features 

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
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Static method reference
names.forEach(System.out::println);

// Instance method reference
names.sort(String::compareToIgnoreCase);

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
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
- **Flight Recorder:** Low-overhead profiling

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


# ✅ 25. SQL

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

-- FULL OUTER JOIN
SELECT e.name, d.name FROM employee e
FULL OUTER JOIN department d ON e.dept_id = d.id;
```

Think of it as a Venn diagram — INNER is the overlap, LEFT keeps the left circle, FULL keeps both circles.

---

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

---

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

---

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

---

## 5. What is the Difference Between Stored Procedure and Function?

Both are reusable SQL blocks — but with key differences.

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

---

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

---

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

---

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

---

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

---

## 10. What is a Transaction in SQL?

A transaction is a **group of SQL operations** that execute as a single unit. Either all succeed or all fail — no partial updates.

Transactions follow **ACID** properties:
- **Atomicity** — all or nothing
- **Consistency** — data stays valid before and after
- **Isolation** — transactions don't interfere with each other
- **Durability** — committed changes are permanent

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

---

## 11. What is the Difference Between UNION and UNION ALL?

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

---


# ✅ 26. System Design
## 1. What is CAP theorem?
## 2. What is eventual consistency?
## 3. What is consistent hashing?
## 4. How would you design a URL shortener?
## 5. How would you design a rate limiter?
## 6. How would you design a notification system?
## 7. How would you design a chat application?
## 8. What is horizontal vs vertical scaling?
## 9. What is sharding in databases?
## 10. What is a message queue and when to use it?
## 11. What is the difference between SQL and NoSQL databases?
## 12. How would you design a caching strategy?


# ✅ 27. CI/CD and DevOp

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

**Example**

```
V1__create_user_table.sql
V2__add_email_column.sql
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


## **19. How do you implement auto-scaling?**


Auto-scaling automatically increases or decreases the number of service instances based on traffic.

In Kubernetes, we use **Horizontal Pod Autoscaler (HPA)**, which scales pods based on metrics like **CPU usage or request count**.

In AWS, we configure **Auto Scaling Groups** to scale EC2 instances when traffic increases.

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


# ✅ 28. Monitoring and Logging

## 1: 

## 2: What is logging framework?

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

## 3: What is Log4j?

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


## 4: What is SLF4J?

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


## 5: What is Logback?

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


## 6: What is structured logging?

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


## 7: What is centralized logging?

**Centralized logging** is the practice of collecting logs from multiple applications and servers into a **single central system**.

It helps with unified search, request tracing across services, log retention, and better monitoring using tools like the **Elastic** (ELK Stack), **Fluentd**, and **Splunk**.

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



# ✅ 29.  Common Issues

## 1. What are Java deployment issues?

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


## 2. What are debugging strategies?

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

# ✅ 30. Real Production Scenario 

**Java**

---

## 1. Your service is slow but CPU and memory are normal. What do you check first?

I check for **I/O blocking** — the most common cause when CPU and memory look fine.

- Is the service waiting on a **slow database query**? I check slow query logs.
- Is it waiting on an **external API call** that's taking too long?
- Are threads **blocked on I/O** — file reads, network calls, or locks?
- I use tools like **thread dumps**, **APM tools (like Datadog or New Relic)**, or Spring Boot Actuator `/metrics` to find where time is being spent.

> "CPU and memory are fine means the machine isn't busy — it's *waiting*. I look for blocking I/O, slow DB queries, or external service timeouts."

---

## 2. Multiple threads update shared data and results become inconsistent. Why?

This is a **race condition** — multiple threads read and write shared data without proper synchronization.

- Thread A reads a value, Thread B reads the same value, both update it — one update gets lost.
- In Java, this happens when shared variables are accessed without `synchronized`, `volatile`, or atomic classes.

**Fix:**
```java
// Use AtomicInteger instead of plain int
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet(); // thread-safe

// Or use synchronized block
synchronized(this) {
    count++;
}
```

> "Without synchronization, threads interleave their reads and writes. The result depends on timing — that's a race condition."

---

## 3. Increasing thread pool size didn't improve performance. What could be the reason?

More threads don't help if the **bottleneck is not the CPU** — it's something else.

- **Database connection pool is the limit** — 100 threads but only 10 DB connections means 90 threads are waiting.
- **External API is slow** — more threads just means more threads waiting on the same slow endpoint.
- **Lock contention** — threads are blocking each other on synchronized code.
- **GC pressure** — too many threads create too many objects, causing frequent garbage collection.

> "Thread pool size only helps if threads are actually doing work. If they're all waiting on DB or I/O, adding more threads just adds more waiting threads."


**Spring Boot**

---

## 4. API works locally but is slow in production. What might be different?

Several things differ between local and production:

- **Database** — local uses H2 or a local DB; production has network latency to RDS or a remote DB.
- **No connection pooling configured** — HikariCP defaults may not be tuned for production load.
- **Missing indexes** — local has small data, production has millions of rows.
- **N+1 query problem** — Hibernate fires one query per record; not visible with small local data.
- **Environment variables / config** — logging level set to DEBUG in production slows things down.
- **Cold start** — first request after deployment is always slow.

> "I check DB query count, connection pool config, and whether indexes exist on production data. Local data is small so problems hide there."

---

## 5. @Transactional is present but rollback doesn't happen. Why?

Common reasons:

1. **Checked exception** — by default, `@Transactional` only rolls back on `RuntimeException`. A checked exception won't trigger rollback unless you add `rollbackFor`.
```java
@Transactional(rollbackFor = Exception.class)
```

2. **Self-invocation** — calling a `@Transactional` method from within the same class bypasses the Spring proxy, so the transaction never starts.

3. **Method is not public** — Spring AOP only intercepts `public` methods.

4. **Exception is caught inside the method** — if you catch the exception and don't rethrow it, Spring never sees it.

> "Most common cause I've seen: checked exception thrown but `rollbackFor` not set, or the method calls itself internally — self-invocation bypasses the proxy."

---

## 6. Connection pool gets exhausted under load. What causes this?

- **Connections not released** — a transaction is opened but never committed or rolled back (often due to an exception that's swallowed).
- **Long-running transactions** — holding a connection for too long while doing non-DB work inside a transaction.
- **Pool size too small** — default HikariCP pool is 10; under high load that's not enough.
- **N+1 queries** — each request fires many queries, holding connections longer.

**Fix:**
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
```

> "I check if connections are being returned properly, look for long-running transactions, and tune HikariCP pool size based on load."


**Microservices**

---

## 7. One slow service increases latency across the system. How do you handle it?

This is the **cascading latency** problem. I handle it with:

- **Timeouts** — set a max wait time on every service call. Don't wait forever.
- **Circuit Breaker (Resilience4j)** — if a service keeps failing or is slow, stop calling it and return a fallback.
- **Bulkhead** — isolate thread pools per service so one slow service doesn't consume all threads.
- **Async / non-blocking calls** — use reactive or async patterns so the caller isn't blocked.

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
public String callPayment() { ... }

public String fallback(Exception e) {
    return "Payment service unavailable, try later";
}
```

> "Timeout + Circuit Breaker is the standard answer. Timeout stops waiting, circuit breaker stops calling when the service is unhealthy."

---

## 8. Retries between services start causing system overload. Why?

This is called a **retry storm**.

- Service B is slow → Service A retries → now Service B gets 3x the traffic → gets even slower → more retries → system collapses.
- If multiple services all retry simultaneously, the load multiplies across the system.

**Fix:**
- **Exponential backoff** — wait longer between each retry (1s, 2s, 4s...).
- **Jitter** — add randomness to retry timing so all instances don't retry at the same moment.
- **Max retry limit** — don't retry forever.
- **Circuit breaker** — stop retrying when the service is clearly down.

> "Retries without backoff turn a slow service into a completely dead one. Exponential backoff with jitter is the fix."

---

## 9. Circuit breaker remains open even when service is healthy. Why?

The circuit breaker has a **half-open state** — it lets a few test requests through to check if the service recovered. If those test requests fail, it stays open.

Reasons it stays open:

- **Health check threshold not met** — the service needs to pass a minimum number of successful calls before closing.
- **Slow response still above threshold** — service is responding but too slowly; slow calls count as failures.
- **Wrong configuration** — `waitDurationInOpenState` is too long, or `permittedNumberOfCallsInHalfOpenState` is too high.
- **Downstream dependency still unhealthy** — the service itself is up but its DB or dependency is still failing.

> "I check the Resilience4j config — specifically `waitDurationInOpenState` and the success threshold in half-open state. Also check if slow responses are being counted as failures."


**System Thinking**

---

## 10. Adding more instances didn't improve performance. What might be the bottleneck?

Horizontal scaling only helps if the bottleneck is **compute**. If it's something else, more instances don't help.

- **Database is the bottleneck** — all instances share one DB; adding instances just adds more DB load.
- **Shared resource contention** — a single Redis, message queue, or external API that all instances hit.
- **Stateful sessions** — requests must go to a specific instance; load balancer can't distribute freely.
- **Network I/O limit** — bandwidth or DNS resolution is the ceiling.

> "I ask: what do all instances share? Usually it's the database. More app servers hitting the same DB just makes the DB slower."

---

## 11. Cache improved speed but caused inconsistent data. Why?

This is a **cache invalidation** problem — the cache has stale data that doesn't match the database.

- Data was updated in the DB but the cache still holds the old value.
- **TTL (Time To Live) is too long** — cache doesn't expire soon enough.
- **Cache not invalidated on write** — when data changes, the cache entry isn't removed or updated.
- **Multiple instances with local caches** — each instance has its own cache; one updates the DB but others still serve old data.

**Fix:**
- Use **write-through** or **cache-aside** pattern with proper invalidation on update.
- Use a **distributed cache** (Redis) instead of local in-memory cache.
- Set appropriate TTL based on how stale data is acceptable.

> "Cache consistency is hard. The fix is: on every write, either update or evict the cache entry. And use a shared cache like Redis, not local per-instance cache."

---

## 12. System works in testing but fails under real traffic. Why?

Testing environments don't replicate production conditions:

- **Volume** — test has 10 users, production has 10,000. Connection pools, thread pools, and queues get exhausted.
- **Data size** — test DB has 100 rows, production has 10 million. Queries that were fast now do full table scans.
- **Concurrency** — tests run sequentially; production has simultaneous requests causing race conditions and deadlocks.
- **Network latency** — test services are on the same machine; production services are across networks.
- **Resource limits** — memory leaks, file descriptor limits, or connection leaks only show up over time under load.
- **Config differences** — test uses H2, production uses MySQL; test has DEBUG logging, production should have INFO.

> "The gap is always load, data size, and concurrency. I recommend load testing with realistic data volumes before going to production — tools like JMeter or Gatling."

---


# ✅ 31. Miscellaneous

## **Q0. Tell me about yourself (Java Developer)**

I am a Java Full Stack Developer with around **10+ years of experience** in designing and developing scalable web applications. My primary expertise is in **Java, Spring Boot, and microservices architecture** for backend development, and **React or Angular** for frontend development.

In my current role, I work on building **RESTful APIs using Spring Boot**, implementing business logic, and integrating services with **databases like MySQL or PostgreSQL**. On the frontend side, I develop responsive user interfaces and integrate them with backend APIs.

I also have experience with **microservices architecture**, where I work with tools like **Spring Cloud, Feign Client, Kafka for event-driven communication, and Redis for caching**. For security, I have implemented **JWT-based authentication using Spring Security**.

From a DevOps perspective, I have worked with **Docker, Kubernetes, and CI/CD pipelines using Jenkins or GitHub Actions**, and deployed applications on **cloud platforms like AWS**.

I follow **clean coding practices, unit testing with JUnit and Mockito, and Agile development methodologies**. I enjoy solving performance issues, improving system scalability, and collaborating with cross-functional teams to deliver reliable software solutions.


## **Q1. Current role responsibilities & day-to-day activity (Java Developer)**

In my current role, I mainly develop **REST APIs using Java and Spring Boot**, implement business logic, and work with **microservices architecture**.

I handle **database operations with MySQL/PostgreSQL**, and on the frontend side I build **UI components using React or Angular**.

I also write **unit tests, participate in code reviews, and support CI/CD deployments using Docker and Jenkins**.

Additionally, I work closely with QA and product teams in **Agile ceremonies like daily stand-ups and sprint planning** to deliver features efficiently.


## **Q2. Explain agile stategy**

In **Agile**, teams usually have **4–5 regular meetings (ceremonies/calls)** in each **Scrum** sprint.

1. **Sprint Planning :**
**Purpose:** Decide **what work will be done in the sprint** and how the team will complete it.
**Participants:** Product Owner, Scrum Master, Development Team.

2. **Daily Stand-up (Daily Scrum) :**
**Purpose:** Quick **15-minute daily call** to discuss:

* What you did yesterday
* What you will do today
* Any blockers

3. **Sprint Review :**
**Purpose:** Demonstrate the **completed work to stakeholders** and get feedback.

4. **Sprint Retrospective :**
**Purpose:** Team discusses **what went well, what didn’t, and improvements for next sprint**.

5. **Backlog Refinement (Grooming)  *(optional but common)* : **
**Purpose:** Review and **clarify upcoming backlog items** so they are ready for future sprints.