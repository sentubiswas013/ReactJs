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

## 3. What are the main principles of Object-Oriented Programming?

Object-Oriented Programming is based on four fundamental principles that promote code reusability, maintainability, and modularity.

**Four OOP Principles:**

- **Encapsulation**: is the process of **wrapping data (variables) and methods (functions) together into a single unit called a class**, and **restricting direct access to the data** using access modifiers (`private`, `protected`, `public`).

- **Inheritance**: The ability of a class to inherit the properties and behaviors of another class. And  class (child/subclass) access the properties and behaviors of another class (parent/superclass)** using the `extends` keyword.

- **Polymorphism**: means **"many forms"**. It allows the same method or object to behave differently in different situations. (e.g., method overloading, method overriding).

- **Abstraction**: is the concept of **hiding internal implementation details and showing only essential features to the user**.


## 4. What is polymorphism? Explain with examples.

Polymorphism means "many forms" - the ability of objects to take multiple forms. The same method call can behave differently depending on the object type.

```java
// Runtime polymorphism - method overriding
class Animal {
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

// Usage
Animal animal1 = new Dog();
Animal animal2 = new Cat();
animal1.makeSound(); // "Dog barks"
animal2.makeSound(); // "Cat meows"
```

## 5. What is encapsulation and how is it implemented in Java?

Encapsulation is the bundling of data and methods that operate on that data within a single unit, while hiding the internal implementation details from outside access.

```java
public class BankAccount {
    private double balance; // Private field - encapsulated
    private String accountNumber;
    
    // Public methods provide controlled access
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
```

## 6. What is inheritance and what are its types?

Inheritance is a mechanism where a new class acquires properties and behaviors of an existing class. It promotes code reusability and establishes an "is-a" relationship.

```java
// Single inheritance
class Vehicle {
    protected String brand;
    
    public void start() {
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
    private int doors;
    
    public void honk() {
        System.out.println("Car honks");
    }
}

// Multilevel inheritance
class SportsCar extends Car {
    public void turboBoost() {
        System.out.println("Turbo activated");
    }
}
```

## 7. What is an abstract class?

An abstract class is a class that cannot be instantiated and may contain both abstract methods (without implementation) and concrete methods (with implementation). It's used to provide a common base for related classes.

```java
abstract class Shape {
    protected String color;
    
    // Constructor
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract double calculateArea();
    
    // Concrete method - inherited by subclasses
    public void displayColor() {
        System.out.println("Color: " + color);
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Usage
// Shape shape = new Shape("red"); // Error - cannot instantiate
Circle circle = new Circle("blue", 5.0); // OK
double area = circle.calculateArea();
```

## 8. What is a package in Java? 
In Java, a **package** is a **namespace that groups related classes, interfaces, and sub-packages together**. It helps organize code, avoid naming conflicts, and control access to classes.

```java
package com.example.utils;

public class Helper {
    public static void printHello() {
        System.out.println("Hello!");
    }
}
```

## 9. Normal, final, static, static final, volatile, abstract, transient?

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



## 10. What is Instance, Static, Abstract, and Final Methods?

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

## 11. How do you read user input from the console in Java?
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

## 12. How do you iterate through a collection in Java?
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

**Primitive types** in Java, like `int`, `double`, and `boolean`, store **actual values** in memory and are **stored on the stack**. They are fast and have a fixed size.

**Reference types**, like objects, arrays, and strings, store a **reference or memory address** pointing to the actual data in the **heap**. They can have methods, support polymorphism, and are generally more flexible but slightly slower.


**Primitive types:**
- Stored in stack memory
- Direct value storage
- Faster access
- Default values (0, false, etc.)

**Reference types:**
- Stored in heap memory
- Store memory addresses
- Include classes, arrays, interfaces
- Default value is null

```java
int x = 10;        // primitive - stores value 10
String name = "John"; // reference - stores address to "John" object
```

## 3. What is autoboxing and unboxing?

* **Autoboxing** is the automatic conversion of a **primitive type** into its corresponding **wrapper class**.
  ```java
  int num = 10;
  Integer numObj = num;  // Autoboxing: int → Integer
  ```

* **Unboxing** is the automatic conversion of a **wrapper class** back to its **primitive type**.
  ```java
  Integer numObj = 20;
  int num = numObj;  // Unboxing: Integer → int
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
String str = "Hello";
str += " World";  // Creates new String object

// StringBuilder - modifies existing buffer
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");  // Modifies same object
```

## 6. Why are strings immutable in Java?

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

## 7. What is string pooling?

String pooling is Java's memory optimization technique where identical string literals share the same memory location in the String Pool (part of heap memory).

```java
String a = "hello";    // stored in string pool
String b = "hello";    // reuses same memory location
String c = new String("hello"); // creates new object in heap

System.out.println(a == b);  // true - same reference
System.out.println(a == c);  // false - different references
```

## 8. What is the difference between final, finally, and finalize?

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

## 9. What is coercion in Java?

**Coercion in Java** is the process of converting a value from one data type to another, either automatically by the compiler or manually by the programmer, to make data types compatible in expressions or assignments.


```java
int num = 10;
double result = num; // Automatic coercion - int to double

double d = 10.5;
int i = (int) d; // Explicit casting required - double to int
```


# ✅ 3. Classes and Objects

## 1. What is a constructor in Java?

A constructor is a special method that initializes objects when they're created. It has the same name as the class and no return type.

- Called automatically when creating objects with 'new'
- Used to set initial values for object properties
- Can be overloaded with different parameters
- If not defined, Java provides a default constructor

```java
public class Person {
    String name;
    
    // Constructor
    public Person(String name) {
        this.name = name;
    }
}

Person p = new Person("John"); // Constructor called
```

## 2. What is constructor chaining?

Constructor chaining is calling one constructor from another constructor in the same class or parent class. It helps avoid code duplication.

- Use `this()` to call another constructor in same class
- Use `super()` to call parent class constructor
- Must be the first statement in constructor

```java
public class Student {
    String name;
    int age;
    
    public Student() {
        this("Unknown", 0); // Calls parameterized constructor
    }
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

## 3. What is the difference between this and super keywords?

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

## 4. What is method overloading?

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

## 5. What is method overriding?

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

## 6. Is it allowed to overload main() method in Java?

**Yes, the `main()` method can be overloaded** in Java.

The JVM only calls **`public static void main(String[] args)`**, while other overloaded versions behave as **regular methods**.

```java
public class Test {
    public static void main(String[] args) { } // JVM entry point
    public static void main(int x) { }         // Overloaded
    public static void main() { }              // Overloaded
}
```

## 6. Is it allowed to override main() method in Java?

❌ **No, we cannot override the `main()` method in Java** because it is **static**, and static methods cannot be overridden (they are hidden).

* The `main()` method is:

  ```java
  public static void main(String[] args)
  ```
* Since it is **static**, it belongs to the class, not the object.
* Static methods are resolved at **compile time**, so they cannot participate in runtime polymorphism.

## 7. Are we allowed to override a static method in Java?

**Static methods cannot be overridden** in Java.

They can be **hidden** in a subclass, and calls are resolved based on the **reference type**, not the object type.

* **No, static methods cannot be overridden**
* Static methods can be **hidden** (method hiding)
* Called based on reference type, not object type

```java
class Parent {
    static void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    static void show() { System.out.println("Child"); } // Hiding, not overriding
}
```

## 8. Is it possible to execute a program without defining a main() method?

It is **technically possible** to run code in a **static block** without a `main()` method, but **modern Java requires `main()`** as the entry point.

Using static blocks alone is **not recommended** and violates standard practices.

* **Yes, using static blocks** (before Java 7)
* **Modern Java requires main()** method for execution
* **Static blocks execute** but program exits with error
* **Not recommended** - violates standard practices

```java
class NoMain {
    static {
        System.out.println("Executing without main");
        System.exit(0); // Required to prevent error
    }
}
// Works in older Java versions, not recommended
```

# ✅ 4. Java Inheritance 

## 1. Why doesn't Java support multiple inheritance?

Java doesn't support multiple inheritance of classes to avoid complexity and ambiguity. If a class inherited from two classes with the same method, Java wouldn't know which one to use.

- Prevents diamond problem confusion
- Keeps language simple and clean
- Avoids method resolution conflicts
- Maintains single inheritance hierarchy

Java provides interfaces to achieve multiple inheritance of behavior without the problems of multiple class inheritance.

## 2. What is the diamond problem?

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

Java solves the diamond problem by not allowing multiple class inheritance but supporting multiple interface inheritance with default methods.

- Single class inheritance only
- Multiple interface inheritance allowed
- Default methods in interfaces (Java 8+)
- Explicit override required for conflicts

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

## 4. Can you override static methods?

No, you cannot override static methods in Java. Static methods belong to the class, not instances, so they're resolved at compile time based on the reference type.

- Static methods are class-level, not instance-level
- Method hiding occurs instead of overriding
- Resolved at compile time (static binding)
- No polymorphism with static methods

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

## 5. What is covariant return type?

Covariant return type allows an overriding method to return a subtype of the return type declared in the parent method. This provides more specific return types in child classes.

- Return type can be more specific in child class
- Must be subtype of parent's return type
- Introduced in Java 5
- Enables more precise API design

```java
class Animal { }
class Dog extends Animal { }

class AnimalFactory {
    Animal createAnimal() { return new Animal(); }
}

class DogFactory extends AnimalFactory {
    @Override
    Dog createAnimal() { return new Dog(); } // Covariant return
}
```

## 6. What is the difference between IS-A and HAS-A relationship?

**IS-A relationship** represents inheritance - a child class IS-A type of parent class.
**HAS-A relationship** represents composition - a class HAS-A reference to another class.

**IS-A (Inheritance):**
- "extends" keyword
- Child inherits parent properties
- Represents specialization

**HAS-A (Composition):**
- Object as instance variable
- Represents ownership/containment
- More flexible than inheritance

```java
// IS-A relationship
class Dog extends Animal { } // Dog IS-A Animal

// HAS-A relationship  
class Car {
    Engine engine; // Car HAS-A Engine
    
    public Car() {
        engine = new Engine();
    }
}
```

# ✅ 5. Java Interface & Abstract Class 

## 1. What is an interface in Java?

An **interface** in Java is a blueprint of a class that defines a contract of abstract methods which implementing classes must provide, used to achieve abstraction and multiple inheritance.

```java
interface Animal {
    void sound();   // abstract method
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
    int add(int a, int b);
}
```

**Marker Interface :**
A marker interface is an empty interface used to tag a class for special behavior.

```java
interface SerializableMarker {
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

## 2. What is an abstract class?

An abstract class is a class that cannot be instantiated and may contain both abstract and concrete methods. It's used when you want to share code among related classes.

- Cannot create objects directly
- Can have constructors and instance variables
- May contain abstract and concrete methods
- Extended using 'extends' keyword
- Supports single inheritance only

```java
abstract class Animal {
    String name; // instance variable
    
    public Animal(String name) { this.name = name; } // constructor
    
    abstract void sound(); // abstract method
    
    void sleep() { System.out.println("Sleeping"); } // concrete method
}
```

## 3. What is the difference between interface and abstract class?

**Interface:**
- Multiple inheritance supported
- Only public abstract methods (before Java 8)
- Variables are public, static, final
- No constructors allowed
- 100% abstraction (before default methods)

**Abstract Class:**
- Single inheritance only
- Can have any access modifier methods
- Can have instance variables
- Can have constructors
- 0-100% abstraction

```java
// Interface - contract
interface Flyable {
    void fly();
}

// Abstract class - partial implementation
abstract class Bird {
    String species;
    abstract void fly();
    void eat() { System.out.println("Eating"); }
}
```

## 4. What are default methods in interfaces?

Default methods are methods with implementation in interfaces, introduced in Java 8. They allow adding new methods to interfaces without breaking existing implementations.

- Provide default implementation in interface
- Use 'default' keyword
- Can be overridden in implementing classes
- Enable interface evolution without breaking compatibility

```java
interface Vehicle {
    void start(); // abstract method
    
    default void honk() { // default method
        System.out.println("Beep beep!");
    }
}

class Car implements Vehicle {
    public void start() { System.out.println("Car started"); }
    // honk() is inherited, can be overridden if needed
}
```

## 5. What is the static keyword?

The **`static` keyword** in Java is used to define **class-level members** that belong to the class rather than any specific instance.

For example, a **static variable** is shared across all objects, a **static method** can be called without creating an object, and a **static block** runs once when the class is loaded.

In short: **`static` means the member belongs to the class, not to individual objects**.


## 5. What are static methods in interfaces?

Static methods in interfaces belong to the interface itself, not to implementing classes. They're called using the interface name and cannot be overridden.

- Called using interface name
- Cannot be overridden in implementing classes
- Provide utility methods related to interface
- Introduced in Java 8

```java
interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
    
    void calculate(); // abstract method
}

// Usage
int result = MathUtils.add(5, 3); // Called on interface
```

## 6. What is marker interface?

A marker interface is an empty interface with no methods or fields. It's used to mark or tag classes to indicate they have special behavior or properties.

- Contains no methods or variables
- Used for metadata purposes
- JVM or frameworks treat marked classes specially
- Examples: Serializable, Cloneable, Remote

```java
// Marker interface
interface Serializable {
    // Empty - just marks the class
}

class Student implements Serializable {
    String name;
    // This class can now be serialized
}
```

## 7. What is functional interface?

A functional interface has exactly one abstract method and can be used with lambda expressions. It represents a single unit of functionality.

- Exactly one abstract method
- Can have default and static methods
- Used with lambda expressions
- @FunctionalInterface annotation for safety

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

## 8. Can an interface extend another interface?

Yes, an interface can extend one or more interfaces using the 'extends' keyword. The child interface inherits all methods from parent interfaces.

- Use 'extends' keyword (not implements)
- Can extend multiple interfaces
- Inherits all abstract, default, and static methods
- Implementing class must implement all inherited abstract methods

```java
interface Animal {
    void eat();
}

interface Mammal extends Animal {
    void breathe();
}

interface Flyable {
    void fly();
}

interface Bird extends Animal, Flyable { // Multiple inheritance
    void chirp();
}

class Eagle implements Bird {
    public void eat() { }
    public void fly() { }
    public void chirp() { }
}
```

# ✅ 6. Java Exception Handling 

## 1. What is an exception in Java?

An exception is an unexpected event that occurs during program execution and disrupts the normal flow of the program. It's Java's way of handling runtime errors gracefully.

- Represents abnormal conditions during execution
- Allows programs to handle errors without crashing
- Provides information about what went wrong
- Can be caught and handled using try-catch blocks

```java
int result = 10 / 0; // ArithmeticException occurs
String text = null;
int length = text.length(); // NullPointerException occurs
```

## 2. What is the exception hierarchy in Java?

Java's exception hierarchy starts with Throwable class, which has two main branches: Error and Exception. Exception further divides into checked and unchecked exceptions.

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

**Checked exceptions** must be handled at compile time, while **unchecked exceptions** occur at runtime and don't require mandatory handling.

**Checked Exceptions:**
- Must be caught or declared with throws
- Compile-time enforcement
- Examples: IOException, SQLException

**Unchecked Exceptions:**
- Runtime exceptions, optional handling
- Inherit from RuntimeException
- Examples: NullPointerException, ArithmeticException

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

Try-catch-finally is Java's exception handling mechanism where try contains risky code, catch handles exceptions, and finally executes cleanup code regardless of exceptions.

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

Multiple catch blocks can handle different exception types, and finally runs even if exceptions occur.

## 6. What is try-with-resources?

**Try-with-resources** is a feature in Java that automatically closes resources (like files or database connections) after use.

- Automatically closes resources
- Resources must implement AutoCloseable
- Cleaner code, no explicit close() calls
- Introduced in Java 7

```java
// Old way
FileReader file = null;
try {
    file = new FileReader("data.txt");
} finally {
    if (file != null) file.close();
}

// Try-with-resources
try (FileReader file = new FileReader("data.txt")) {
    // Use file
} // Automatically closed
```

## 7. How do you create custom exceptions?

Custom exceptions are created by extending Exception class for checked exceptions or RuntimeException for unchecked exceptions. They provide specific error information for your application.

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

## 8. What is exception chaining?

Exception chaining links exceptions together, preserving the original cause when wrapping exceptions. It helps maintain the complete error trail for better debugging.

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

# ✅ 7. Java Collections Framework

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

**ArrayList** uses a **dynamic array**, so it gives **fast random access (O(1))**, but **slow insertions/deletions in the middle** due to shifting.

**LinkedList** uses a **doubly linked list**, so it has **slower access (O(n))**, but **faster insertions/deletions** since no shifting is required.

**In simple words:** Use **ArrayList for reading/searching**, and **LinkedList for frequent insert/delete operations.** 🚀


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

**In simple words:** Use **HashMap in single-threaded applications**, and **Hashtable in multi-threaded scenarios** (though nowadays we prefer ConcurrentHashMap).

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

## 7. What is the difference between fail-fast and fail-safe iterators?

**Fail-fast iterator** detects changes in the collection during iteration and throws a **ConcurrentModificationException**. It works on the **original collection**.
(Examples: ArrayList, HashMap)

**Fail-safe iterator** allows modifications during iteration because it works on a **separate copy** of the collection. It does not throw an exception.
(Examples: ConcurrentHashMap, CopyOnWriteArrayList)

**In simple words:** Fail-fast throws exception on modification, fail-safe allows it safely.


```java
List<String> list = new ArrayList<>();
Iterator<String> failFast = list.iterator(); // Throws exception if modified

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
Iterator<String> failSafe = map.keySet().iterator(); // Safe for modifications
```

## 8. What is the difference between Comparable and Comparator?

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

## 9. What is WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?

A `WeakHashMap` stores keys as **weak references**, so entries are automatically removed when the key is no longer referenced elsewhere (used for caching).

An `IdentityHashMap` compares keys using **== (reference equality)** instead of `equals()` method.

A `LinkedHashMap` Maintains insertion order (or access order), allows null keys and values, slightly slower than HashMap.

A `PriorityQueue` stores elements in **priority order** (natural order or custom comparator), not in insertion order.

```java
Map<String, Integer> weakMap = new WeakHashMap<>(); // GC-friendly, Used for: caching
Map<String, Integer> identityMap = new IdentityHashMap<>(); // for key comparison
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // Ordered, LRU cache implementations
Queue<Integer> priorityQueue = new PriorityQueue<>(); // Heap-based, processed based on priority
```

# ✅ 8. Java Multithreading & Synchronization 

## 1. What is multithreading?

**Multithreading** allows a program to run **multiple threads concurrently** within the same memory space, improving **CPU utilization, performance, and responsiveness**. Threads can **share data** and the **JVM handles scheduling**.

**In simple words:** It lets a program do **many tasks at the same time** efficiently.

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
// Runnable allows extending other classes
class MyTask extends SomeClass implements Runnable {
    public void run() { }
}

// Thread extension limits inheritance
class MyThread extends Thread { // Cannot extend anything else
    public void run() { }
}
```

## 4. What are the states of a thread?

A thread goes through various states during its lifecycle: **NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED**.

- **NEW:** Thread created but not started
- **RUNNABLE:** Thread executing or ready to execute
- **BLOCKED:** Thread blocked waiting for monitor lock
- **WAITING:** Thread waiting indefinitely for another thread
- **TIMED_WAITING:** Thread waiting for specified time period
- **TERMINATED:** Thread completed execution

```java
Thread t = new Thread(() -> {}); // NEW state
t.start(); // RUNNABLE state
// Thread may go through BLOCKED, WAITING states
// Finally reaches TERMINATED state
```

## 5. What is synchronization in Java?

**Synchronization** in Java ensures that **only one thread at a time** can access a shared resource, preventing **race conditions**.

* Achieved using **`synchronized`** keyword or **locks**
* Can synchronize **methods** or **blocks**
* Ensures **thread safety** but may reduce performance

**In simple words:** It makes multithreaded access to shared data **safe and consistent**.

```java
// Synchronized method
public synchronized void increment() {
    count++;
}

// Synchronized block
public void decrement() {
    synchronized(this) {
        count--;
    }
}
```

## 6. What is deadlock and how do you prevent it?

**Deadlock** happens when **two or more threads wait forever** for resources held by each other, causing the program to **freeze**.

**Prevention strategies:**
- Avoid nested locks
- Use timeout for lock acquisition
- Order locks consistently
- Use concurrent collections
- Implement deadlock detection

```java
// Deadlock scenario
Thread1: lock(A) -> lock(B)
Thread2: lock(B) -> lock(A)

// Prevention - consistent ordering
Thread1: lock(A) -> lock(B)
Thread2: lock(A) -> lock(B)
```

## 7. What is volatile keyword?

**Volatile** keyword ensures that a variable's value is **always read from and written to main memory**, not from thread's local cache. It provides visibility guarantee across threads.

- Ensures visibility of changes across threads
- Prevents compiler optimizations
- No atomicity guarantee for compound operations
- Lighter alternative to synchronization for simple cases
- Used for flags and status variables

```java
class SharedData {
    private volatile boolean flag = false;
    
    public void setFlag() {
        flag = true; // Immediately visible to all threads
    }
    
    public boolean isFlag() {
        return flag; // Always reads from main memory
    }
}
```

## 8. What is the difference between synchronized and volatile?

**`synchronized`** is a keyword used to control **access to a block or method** by multiple threads, ensuring **mutual exclusion** and **thread safety**.

**`volatile`** is a keyword used with variables to ensure that **changes made by one thread are visible to all other threads immediately**, but it **does not provide mutual exclusion**.


**Synchronized:**
- Provides both visibility and atomicity
- Blocks other threads (mutual exclusion)
- Can be used with methods and blocks
- Heavier performance overhead
- Prevents race conditions completely

**Volatile:**
- Provides only visibility, not atomicity
- No blocking of threads
- Only for variables
- Lighter performance overhead
- Prevents visibility issues only

```java
// Synchronized - full protection
private int count = 0;
public synchronized void increment() {
    count++; // Atomic and visible
}

// Volatile - visibility only
private volatile boolean ready = false;
public void setReady() {
    ready = true; // Visible but not atomic for compound operations
}
```

## 9. What is race condition and atomic operation?

**Race condition** occurs when multiple threads access shared data simultaneously and the outcome depends on thread scheduling. **Atomic operation** is indivisible and completes without interruption.

**Race Condition:**
- Multiple threads modify shared data
- Unpredictable results due to timing
- Causes data corruption
- Prevented by synchronization

**Atomic Operation:**
- Indivisible operation
- Either completes fully or not at all
- Thread-safe by nature
- Examples: reading/writing primitive variables (except long/double)

```java
// Race condition example
private int counter = 0;
public void increment() {
    counter++; // Not atomic: read -> increment -> write
}

// Atomic solution
private AtomicInteger counter = new AtomicInteger(0);
public void increment() {
    counter.incrementAndGet(); // Atomic operation
}
```

# ✅ 9. Java Advanced Concurrency 

## 0. What is **Concurrency in Java**?

**Concurrency in Java** is the ability of a program to **execute multiple tasks at the same time** by using **multiple threads**. These tasks can run **in parallel on multiple CPU cores** or be **interleaved on a single core** to improve performance and responsiveness.

1. **Thread-based**
* Java provides the **`Thread` class** and **`Runnable` interface** to create and manage concurrent tasks.

```java
class MyTask extends Thread {
    public void run() {
        System.out.println("Task running in thread: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyTask t1 = new MyTask();
        t1.start(); // Runs concurrently
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


---

* **Concurrency vs Parallelism**

| Concurrency                | Parallelism                             |
| -------------------------- | --------------------------------------- |
| Multiple tasks in progress | Multiple tasks executing simultaneously |
| May run on one CPU         | Requires multiple cores                 |


## 1. What is ExecutorService?

**ExecutorService** is a **Java API to manage thread pools and execute tasks asynchronously**, handling **thread creation, reuse, and termination**, and allowing **task tracking with Future**.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

// Submit tasks
executor.submit(() -> System.out.println("Task executed"));
executor.execute(() -> System.out.println("Another task"));

executor.shutdown(); // Graceful shutdown
```

## 2. What are the types of thread pools?

Java provides several predefined thread pool types through Executors class, each optimized for different use cases.

**Types of thread pools in Java** (via `Executors`) are:

* **Fixed Thread Pool** – a **fixed number of threads** for executing tasks.
 ```java
   ExecutorService fixedPool = Executors.newFixedThreadPool(3);
```

* **Cached Thread Pool** – **creates threads as needed** and **reuses idle threads**.
```java
   ExecutorService cachedPool = Executors.newCachedThreadPool();
```

* **Single Thread Pool** – **only one thread** executes tasks sequentially.
```java
   ExecutorService singlePool = Executors.newSingleThreadExecutor();
```

* **Scheduled Thread Pool** – **executes tasks after a delay or periodically**.
```java
   ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
```

## 3. What is Future and CompletableFuture?

**Future** represents the result of an asynchronous computation, while **CompletableFuture** is an enhanced version that supports functional programming and chaining operations.

**Future:**
- Represents pending result
- Blocking get() method
- Limited functionality
- Cannot be completed manually

**CompletableFuture:**
- Non-blocking operations
- Supports chaining and composition
- Can be completed manually
- Functional programming support

```java
// Future - basic async result
Future<String> future = executor.submit(() -> "Hello");
String result = future.get(); // Blocking call

// CompletableFuture - enhanced async programming
CompletableFuture<String> cf = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenCompose(s -> CompletableFuture.completedFuture(s.toUpperCase()));
```

## 4. What is CountDownLatch?

**CountDownLatch** is a **synchronization utility** that **blocks threads until a set count reaches zero**, using **countDown() to decrement** and **await() to wait**, and is **one-time use**.

```java
CountDownLatch latch = new CountDownLatch(3);

// Worker threads
for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        // Do work
        System.out.println("Task completed");
        latch.countDown(); // Decrease count
    }).start();
}

latch.await(); // Wait for all tasks to complete
System.out.println("All tasks finished");
```

## 5. What is ReentrantLock?

**ReentrantLock** is a class in Java (`java.util.concurrent.locks`) that provides an explicit and more flexible locking mechanism than `synchronized`.

It allows:

* Manual `lock()` and `unlock()` control
* Fair or non-fair locking policy
* Interruptible lock acquisition
* `tryLock()` with timeout
* Reentrancy (same thread can acquire the lock multiple times)

```java
ReentrantLock lock = new ReentrantLock();

public void method1() {
    lock.lock();
    try {
        // Critical section
        method2(); // Same thread can acquire lock again
    } finally {
        lock.unlock();
    }
}

public void method2() {
    lock.lock();
    try {
        // Another critical section
    } finally {
        lock.unlock();
    }
}
```

## 6. What is the difference between ReentrantLock and synchronized?

Both **`synchronized`** and **`ReentrantLock`** are used for **thread synchronization** in Java, but there are differences:

* **`synchronized`** is a **built-in keyword**. It’s simple to use, automatically releases the lock, and blocks threads until the lock is available.
* **`ReentrantLock`** is a **class from `java.util.concurrent`**. It offers more flexibility, like **tryLock()**, **lockInterruptibly()**, and **fair locking**. You must **manually release the lock** using `unlock()`.

In short: **synchronized is simpler**, while **ReentrantLock provides advanced features and greater control**.

**ReentrantLock:**
- Explicit lock/unlock
- Supports fairness, timeout, interruption
- More flexible but requires manual management
- Can check if lock is held
- Better performance under high contention

**Synchronized:**
- Implicit lock/unlock
- Simpler syntax
- Automatic lock release
- JVM optimized
- Cannot be interrupted

```java
// ReentrantLock - explicit control
ReentrantLock lock = new ReentrantLock();
public void method() {
    if (lock.tryLock(1, TimeUnit.SECONDS)) {
        try {
            // Critical section
        } finally {
            lock.unlock();
        }
    }
}

// Synchronized - implicit control
public synchronized void method() {
    // Critical section - automatic lock management
}
```
7. **What is immutability in Java?**

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

**Generational Garbage Collection** is a memory management strategy in JVM where the heap is divided into multiple generations based on the assumption that **most objects die young**.

**Young Generation:**
- Eden space (new objects)
- Survivor spaces (S0, S1)
- Frequent, fast collection

**Old Generation:**
- Long-lived objects
- Less frequent collection
- More expensive cleanup

**Collection Process:**
- Objects start in Eden
- Survivors move to Old generation
- Different algorithms for each generation

This approach optimizes GC performance by focusing on areas where most garbage exists.

## 7. What is the difference between minor GC and major GC?

A **Minor GC** occurs in the **Young Generation** of the heap and cleans up short-lived objects like temporary variables. It happens frequently and is usually very fast, causing minimal pause.

A **Major GC** (also called **Full GC**) runs on the **Old Generation** and removes long-lived objects that are no longer needed. It happens less often but takes more time and can significantly impact application performance.

**Minor GC:**
- Cleans Young Generation only
- Fast and frequent
- Typically takes milliseconds
- Triggered when Eden space fills up
- Most objects are collected here

**Major GC:**
- Cleans Old Generation (and sometimes entire heap)
- Slower and less frequent
- Can cause application pauses
- Triggered when Old Generation fills up
- Also called Full GC when entire heap is cleaned

```java
// Objects that survive multiple minor GCs get promoted to Old Generation
List<String> longLived = new ArrayList<>(); // Eventually moves to Old Gen
String temp = "temporary"; // Likely collected in minor GC
```

## 8. What are GC roots?

GC roots are objects that are always reachable and serve as starting points for garbage collection reachability analysis. Objects reachable from GC roots are considered live.

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

**InputStream:**
- Handles raw bytes (binary data)
- Abstract class for byte streams
- Methods return int (0-255) or byte arrays
- Used for images, videos, any binary files
- Examples: FileInputStream, ByteArrayInputStream

**Reader:**
- Handles characters (text data)
- Abstract class for character streams
- Handles character encoding automatically
- Used for text files
- Examples: FileReader, StringReader

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


**BufferedReader:**
- Buffers input for efficient reading
- Provides readLine() method
- Reduces system calls
- Default buffer size 8192 characters

**BufferedWriter:**
- Buffers output for efficient writing
- Provides newLine() method
- Flushes buffer when full or explicitly called
- Improves write performance

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

Type erasure is the process where generic type information is removed during compilation. The compiler replaces generic types with their bounds or Object, maintaining backward compatibility.

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

PECS stands for "Producer Extends, Consumer Super" - a guideline for choosing between extends and super wildcards based on how you use the collection.

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

Generics have several limitations due to type erasure and backward compatibility requirements.

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

## 1.  What is `@Bean` in Java?

`@Bean` is a Spring annotation used to declare and manage an object inside the Spring container.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Student student() {
        return new Student();
    }
}
```



## 1. What are annotations in Java?

Annotations are metadata that provide information about code without affecting its execution. They're used by compilers, development tools, and frameworks to process code automatically.

- Metadata attached to code elements
- Start with @ symbol
- Don't change program behavior directly
- Used by tools and frameworks for processing
- Can be applied to classes, methods, fields, parameters

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

## 3. How do you create custom annotations?

Custom annotations are created using @interface keyword and can include elements with default values. They require retention and target policies.

```java
// Custom annotation definition
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value() default "default";
    int priority() default 1;
}

// Usage
@MyAnnotation(value = "important", priority = 5)
public void annotatedMethod() {
    // Method implementation
}

// Processing annotation
Method method = MyClass.class.getMethod("annotatedMethod");
MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
String value = annotation.value(); // "important"
```

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

**@Override:**
- Built-in annotation
- Indicates method overrides parent method
- Compile-time verification
- Prevents accidental method signature mistakes

**@Overload:**
- Not a standard Java annotation
- Method overloading happens automatically
- No special annotation needed
- Multiple methods with same name, different parameters

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

- Examine class structure at runtime
- Create objects dynamically
- Invoke methods dynamically
- Access private fields and methods
- Used by frameworks like Spring, Hibernate

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

Use reflection when you need dynamic behavior that cannot be achieved with normal Java code, typically in frameworks and libraries.

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

Reflection is significantly slower than direct method calls due to runtime type checking, security checks, and method resolution overhead.

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

Mockito is a testing framework that creates fake objects called mocks for unit testing. You can control what these mock objects return when their methods are called, which helps isolate the code you're testing from its dependencies. It's very popular for testing Spring applications.

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

A servlet is a Java class that handles HTTP requests and responses on a web server. It's like a controller that processes incoming requests, performs business logic, and sends back responses. Servlets run inside containers like Tomcat and are the foundation of Java web applications.

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

The servlet lifecycle has three main phases managed by the container. First is init() called once when the servlet loads to initialize resources. Then service() is called for every request and delegates to doGet, doPost methods. Finally destroy() is called once when the servlet unloads to clean up resources.

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

JSP allows you to create dynamic web pages by embedding Java code directly into HTML. It's much easier than servlets for creating user interfaces because you write mostly HTML with some Java mixed in. JSP pages get automatically compiled into servlets by the container behind the scenes.

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


Key differences:
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

## 5. What is Spring Data JPA?

**Spring Data JPA** is a **Spring framework module** that simplifies working with **JPA-based data access**. It provides **ready-made repository interfaces** like `CrudRepository` and `JpaRepository`, so you can perform common CRUD operations without writing boilerplate code.

It also supports **custom queries, pagination, and sorting**, and integrates seamlessly with Spring Boot, making **database access faster, cleaner, and easier to maintain**.

## 5. What is JPA and how it works?

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

# ✅ 15. Java Lambda Expressions & Streams API 

## 1. What are lambda expressions?

**Lambda expressions** in Java are a short and clear way to represent **anonymous functions** (functions without a name).

They were introduced in **Java 8** to support **functional programming** and make code more readable and concise.

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

## 2. What are functional interfaces?

**Functional interfaces** are interfaces with exactly **one abstract method**. They can be implemented using **lambda expressions** and serve as the foundation for functional programming in Java.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // Single abstract method
    
    default void print() { } // Default methods allowed
    static void info() { } // Static methods allowed
}

// Usage with lambda
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;
```

## 3. What are method references?

Method references are shorthand notation for lambda expressions that call a single method. They make code even more concise when lambda just calls an existing method.

**Types of Method References:**
- Static method: `ClassName::methodName`
- Instance method: `object::methodName`
- Instance method of arbitrary object: `ClassName::methodName`
- Constructor: `ClassName::new`

```java
List<String> names = Arrays.asList("john", "jane", "bob");

// Lambda expression
names.forEach(name -> System.out.println(name));

// Method reference - more concise
names.forEach(System.out::println);

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## 4. What is the difference between lambda and anonymous class?

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

## 6. What is parallel streams? - asked 

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

```java
List<String> words = Arrays.asList("Hello", "World");

// map() - one-to-one
List<Integer> lengths = words.stream()
    .map(String::length)        // ["Hello", "World"] → [5, 5]
    .collect(Collectors.toList());

// flatMap() - one-to-many, then flatten
List<String> letters = words.stream()
    .flatMap(word -> Arrays.stream(word.split(""))) // Flatten arrays
    .collect(Collectors.toList()); // [H, e, l, l, o, W, o, r, l, d]
```

## 10. What is Optional class?

**Optional class** is a container class that may or may not contain a value. It helps avoid **NullPointerException** and makes null handling more explicit and safer.

```java
// Creating Optional
Optional<String> optional = Optional.of("Hello");
Optional<String> empty = Optional.empty();
Optional<String> nullable = Optional.ofNullable(getString());

// Using Optional
optional.ifPresent(System.out::println);        // Execute if present
String result = optional.orElse("Default");     // Provide default
String result2 = optional.orElseGet(() -> "Default"); // Lazy default

// Chaining operations
optional.filter(s -> s.length() > 3)
        .map(String::toUpperCase)
        .ifPresent(System.out::println);
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

**Caching in Java is a technique of storing frequently used data in memory so that we don’t have to fetch it again from a slow source like a database or external API.**

**Types of cache in Java** are generally categorized as:

1. **In-Memory Cache** – stores data in **RAM** for fast access (e.g., `HashMap`, `ConcurrentHashMap`, **Guava Cache**).
2. **Distributed Cache** – stores data across **multiple nodes** for scalability (e.g., **Redis**, **Hazelcast**, **Ehcache Cluster**).
3. **Persistent Cache** – stores data on **disk** to survive restarts (e.g., **Ehcache with disk store**, **Caffeine with persistence**).

**Caching Levels:**
- **Application level:** In-memory caches (Caffeine, Guava)
- **Database level:** Query result caching
- **Distributed level:** Redis, Hazelcast
- **HTTP level:** Browser and CDN caching

**How cache works internally (Steps)**
* Application receives a request.
* It checks the cache for the requested data.

* **If cache hit:**
  * Data is found in cache.
  * Return data directly from cache.
  * No database call is made.

* **If cache miss:**
  * Data is fetched from the database.
  * Data is stored in cache as a **key-value pair**.
  * Return the response to the user.


**Using HashMap (Manual Cache)**
```java
import java.util.HashMap;
import java.util.Map;

class UserService {

    private Map<Integer, String> cache = new HashMap<>();
    // private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public String getUser(int userId) {
        // Check cache first
        if (cache.containsKey(userId)) {
            System.out.println("Cache Hit!");
            return cache.get(userId);
        }

        // Simulate database call
        System.out.println("Cache Miss! Fetching from DB...");
        String user = "User" + userId;

        // Store in cache
        cache.put(userId, user);

        return user;
    }
}

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        System.out.println(service.getUser(1)); // Miss
        System.out.println(service.getUser(1)); // Hit
    }
}
```

**Using Caffeine (Real-World Cache)**
```java
<dependency>
  <groupId>com.github.ben-manes.caffeine</groupId>
  <artifactId>caffeine</artifactId>
  <version>3.1.8</version>
</dependency>

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class UserService {

    private Cache<Integer, String> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public String getUser(int userId) {

        return cache.get(userId, id -> {
            System.out.println("Fetching from DB...");
            return "User" + id;
        });
    }

    public static void main(String[] args) {
        UserService service = new UserService();

        System.out.println(service.getUser(1)); // DB call
        System.out.println(service.getUser(1)); // Cached
    }
}
```

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
- **Atomicity:** All or nothing execution
- **Consistency:** Data remains valid
- **Isolation:** Concurrent transactions don't interfere
- **Durability:** Committed changes are permanent

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

**Streaming (Low Memory)**
I process large files or datasets **line by line** using Java Streams.
This avoids loading everything into memory.


```java
Files.lines(Path.of("large-file.txt"))
     .filter(l -> l.contains("ERROR"))
     .forEach(this::processError);
```


- **Batch Processing**
Instead of processing all data at once, I divide it into **smaller batches** (like 1000 records per batch).
This reduces memory usage and improves performance.
In Spring Boot, I use **Spring Batch** for this.

```java
int BATCH = 1000;

for (int i = 0; i < data.size(); i += BATCH) {
    processBatch(data.subList(i, Math.min(i + BATCH, data.size())));
}
```

- **Database Pagination (Spring Data JPA)**
When data comes from a database, I fetch and process it **page by page** using `PageRequest`.
This prevents memory overflow and keeps processing stable.

```java
int page = 0;
Page<DataItem> result;

do {
    result = repo.findByStatus("PENDING", PageRequest.of(page++, 1000));
    processBatch(result.getContent());
} while (result.hasNext());
```


- **Async / Parallel Processing**
I use **CompletableFuture and Parallel Streams** to process data in parallel across CPU cores.
This improves speed and handles large volumes efficiently.

```java
CompletableFuture.runAsync(() ->
    data.parallelStream()
        .filter(this::isValid)
        .forEach(this::save)
);
```


- **Memory-Efficient Caching**

```java
Map<String, WeakReference<Data>> cache = new ConcurrentHashMap<>();
```

## 9. What is Cursor?

A **cursor** fetches records **one by one (or in small chunks)** instead of loading the entire result into memory.

**Why use it?**

* Prevents **OutOfMemoryError**
* Good for very large datasets
* Reduces heap usage


```java
@Query("SELECT p FROM Product p")
Stream<Product> findAllByStream();
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

## 10. What is Batch Processing?

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

## 2. What is Singleton pattern and provide an example of a thread-safe implementation in Java.?

Singleton pattern ensures that a class has only one instance throughout the application lifecycle and provides global access to that instance.

- Only one instance of the class
- Global access point
- Lazy or eager initialization
- Used for logging, database connections, caching

```java
public class Singleton {
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

## 3. How do you implement thread-safe Singleton?

Thread-safe Singleton can be implemented using synchronization, double-checked locking, or enum approach to prevent multiple instances in multithreaded environments.

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

Factory pattern creates objects without specifying their exact classes. It provides an interface for creating objects but lets subclasses decide which class to instantiate.

- Creates objects without exposing creation logic
- Refers to newly created objects through common interface
- Promotes loose coupling
- Easy to extend with new types

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

Observer pattern defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

- Subject maintains list of observers
- Observers are notified of state changes
- Loose coupling between subject and observers
- Used in event handling, MVC architecture

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

Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

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

Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.

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

Decorator pattern allows behavior to be added to objects dynamically without altering their structure. It provides a flexible alternative to subclassing for extending functionality.

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

# ✅ 18. Java Spring Framework 

## 1. What is Spring Framework?

**Spring Framework** is a **comprehensive Java framework** for building enterprise applications.

It provides **infrastructure support**, uses **IoC and Dependency Injection**, has **modular architecture** (Core, MVC, Data, Security), and **simplifies Java EE development** with POJOs.

Spring makes Java development easier by handling common tasks and promoting best practices like loose coupling and testability.

## 282: What are the core features of Spring?

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

## 2. What is Inversion of Control (IoC)?

**Inversion of Control (IoC)** is a design principle where the control of object creation and dependency management is transferred from the program to a container or framework.

Instead of a class creating its own dependencies, they are injected from outside.

In simple terms: **IoC means the framework controls the flow and object creation, not your code.**

## 3. What is Dependency Injection?

**Dependency Injection (DI)** is a design pattern where an object’s **dependencies are provided externally** rather than the object creating them itself.

In Java and Spring, DI helps make code **loosely coupled, easier to test, and more maintainable**. It can be implemented via **constructor injection, setter injection, or field injection**.

**Types of DI:**
- Constructor injection (recommended)
- Setter injection
- Field injection

```java
// Without DI - tight coupling
class OrderService {
    private PaymentService paymentService = new PaymentService(); // Creates dependency
}

// With DI - loose coupling
class OrderService {
    private PaymentService paymentService;
    
    public OrderService(PaymentService paymentService) { // Injected
        this.paymentService = paymentService;
    }
}
```
## 287: What is Spring Data JPA?

**Spring Data JPA** is a Spring module that **simplifies JPA-based data access**.

It provides **repository abstraction**, **auto-implements methods from names**, supports **query methods, JPQL, and native SQL**, and **reduces boilerplate code**.

* Spring module that simplifies JPA-based data access
* Provides repository abstraction over JPA
* **Auto-implementation**: Creates implementation from method names
* **Query Methods**: Derive queries from method names
* **Custom Queries**: Support for JPQL and native SQL
* Reduces boilerplate code significantly

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
    List<User> findByAgeGreaterThan(int age);
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
```

---

## 288: What is Spring Cloud? - asked

**Spring Cloud** is a framework for **building distributed systems and microservices**.

It provides tools for **service discovery (Eureka/Consul)**, **circuit breakers (Hystrix)**, **API gateways (Zuul/Gateway)**, **centralized configuration**, and **client-side load balancing**.

* Framework for building distributed systems and microservices
* Provides tools for common patterns in distributed systems
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

---

## 289: What is Spring Security?

**Spring Security** is a **Java security framework** that handles **authentication** (user identity) and **authorization** (access control).

It provides **protection** against CSRF, session fixation, clickjacking, integrates with multiple authentication providers, and supports **annotation- and configuration-based security**.

* Comprehensive security framework for Java applications
* Handles authentication and authorization
* **Authentication**: Verify user identity (login)
* **Authorization**: Control access to resources
* **Protection**: CSRF, session fixation, clickjacking protection
* Integrates with various authentication providers
* Annotation-based and configuration-based security

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
            .formLogin().and()
            .build();
    }
}
```

---

## 290: What is Spring WebFlux?

**Spring WebFlux** is a **reactive, non-blocking web framework** for building high-performance applications.

It’s an alternative to Spring MVC, uses **Reactive Streams** (Project Reactor), supports **functional routing**, and handles **more concurrent requests with fewer threads**.

* Reactive web framework for building non-blocking applications
* Alternative to Spring MVC for reactive programming
* **Non-blocking**: Handles more concurrent requests with fewer threads
* **Reactive Streams**: Built on Project Reactor
* **Functional Programming**: Supports functional routing
* Better performance for I/O intensive applications

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

## 4. What is BeanFactory?

**BeanFactory** is the **basic IoC container in Spring** that creates and manages beans and performs **dependency injection**.
It uses **lazy initialization**, so beans are created **only when requested**.
It is lightweight, but **ApplicationContext** is preferred because it provides more features like event handling and annotation support.


```java
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
UserService userService = (UserService) factory.getBean("userService");
```

## 4. What is a Java Bean?

A **Java Bean** is a simple Java class that follows certain rules: it has a **no-argument constructor**, **private fields**, and **public getter and setter methods**. Java Beans are mainly used to **encapsulate data** and are reusable components.

```java
public class User implements Serializable {
    private String name;
    
    public User() {} // No-arg constructor
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

## 5. What are Spring beans?

**Spring beans** are objects that are **created, managed, and destroyed by the Spring container**. They are defined using annotations like `@Component`, `@Service`, or through configuration files. Spring beans support **dependency injection**, making applications loosely coupled.

- Objects managed by Spring container
- Defined through configuration (XML, annotations, Java config)
- Container handles lifecycle and dependencies
- Can be singleton or prototype scope
- Configured with metadata

```java
@Component
public class UserService { // This becomes a Spring bean
    // Bean managed by Spring container
}

// Or XML configuration
<bean id="userService" class="com.example.UserService"/>
```

## 5. What are Bean life cycle in sprintboot 

The **bean lifecycle** describes the steps a bean goes through from **creation to destruction** inside the Spring **IoC container**.

**Steps in Bean Lifecycle**

1. **Bean Instantiation :** Spring creates the bean object.
2. **Dependency Injection:** Required dependencies are injected using `@Autowired`.
3. **Bean Initialization:** Initialization methods run using `@PostConstruct` or `afterPropertiesSet()`.
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


# ✅ 19. Java Spring Boot 

## 6. What is annotations in Java?
**An annotation** is a special type of metadata in Java that provides additional information about classes, methods, or variables to the compiler or framework.

**Example Annotations:**
* `@Override`
* `@Autowired`
* `@Component`
* `@Service`

## 6. What is Spring Boot?

**Spring Boot** is a framework built on top of Spring that simplifies application development. It provides **auto-configuration**, **embedded servers**, and **starter dependencies**, allowing developers to build production-ready applications quickly with minimal configuration.

**Design patterns:**
Spring Boot mainly uses **MVC, Dependency Injection, Singleton, Factory, and DAO** design patterns.

**Design Principle:**
Spring Boot follows principles like Convention over **Configuration, Dependency Injection, Auto-Configuration, and Standalone** Applications.

- Rapid application development
- Auto-configuration based on classpath
- Embedded servers (Tomcat, Jetty)
- Production-ready features out of the box
- Minimal configuration required

Spring Boot eliminates most boilerplate configuration and allows developers to focus on business logic rather than setup.

## 7. How does Spring Boot Works Internally?

**Spring Boot** starts with `SpringApplication.run()`, which initializes the Spring context. It performs auto-configuration based on project dependencies, scans components to create beans in the IoC container, and starts an embedded server like Apache Tomcat. After that, the application becomes ready to handle requests.

**Internal Flow:**

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

## 8. What is auto-configuration in Spring Boot and to disable?

Auto-configuration automatically configures Spring applications based on the dependencies present in the classpath. It reduces manual configuration by making intelligent assumptions.

- Automatic bean configuration
- Based on classpath dependencies
- Can be customized or disabled
- Uses conditional annotations
- Follows convention over configuration

```java
// If H2 database is on classpath, Spring Boot automatically configures:
// - DataSource bean
// - JdbcTemplate bean  
// - Transaction manager
// No manual configuration needed
```

**Disable for Entire Application (Most Common)**

```java
@SpringBootApplication(
    exclude = DataSourceAutoConfiguration.class
)
public class Application {
}
```

**Disable for a Specific Configuration Class**

If you are not using `@SpringBootApplication` in that class:

```java
@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class CustomConfig {
}
```

**How to Disable Specific Auto-Configuration Class**


```java
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
    exclude = DataSourceAutoConfiguration.class
)
public class Application {
}
```

**How to Disable Multiple Auto Configurations**

```java
@SpringBootApplication(
    exclude = {
        DataSourceAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
    }
)
```

## 9. What is @SpringBootApplication annotation?

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

## 10. What is the difference between @Component, @Service, and @Repository?

These are stereotype annotations that mark classes as Spring beans, but they serve different purposes and provide semantic meaning.

**@Component** is a generic Spring annotation used to mark a class as a **Spring-managed bean**.
**@Component:**
- Generic stereotype for any Spring-managed component
- Base annotation for other stereotypes

**@Service** is a specialization of `@Component` used to mark a class as a **service layer component**, indicating business logic.
**@Service:**
- Marks service layer classes
- Contains business logic
- Semantic specialization of @Component

**@Repository** is a specialization of `@Component` used to mark a class as a **data access object (DAO)**, enabling **exception translation** for database operations.
**@Repository:**
- Marks data access layer classes
- Provides exception translation
- Semantic specialization of @Component

```java
@Component
public class UtilityClass { } // Generic component

@Service
public class UserService { } // Business logic

@Repository
public class UserRepository { } // Data access
```

## 11. What is @Autowired annotation?

`@Autowired` is an annotation in **Spring Framework** that enables **automatic dependency injection (DI)**.
It tells the Spring container to automatically inject a required bean into a class.

- Automatic dependency injection
- Injection by type
- Can be applied to fields, constructors, methods
- Required by default (can be made optional)

```java
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

## 12. What is @Qualifier annotation?

`@Qualifier` is a Spring annotation used **along with `@Autowired`** to resolve ambiguity when **multiple beans of the same type** exist in the Spring container.

```java
@Service
public class NotificationService {
    
    @Autowired
    @Qualifier("emailSender")
    private MessageSender messageSender; // Specifies which implementation
}

@Component("emailSender")
public class EmailSender implements MessageSender { }

@Component("smsSender") 
public class SmsSender implements MessageSender { }
```

Without @Qualifier, Spring would throw an exception due to multiple beans of type MessageSender.

## 13. What is ApplicationContext?

`ApplicationContext` is a **Spring container** that manages the lifecycle of Spring beans. It loads configuration, creates objects, injects dependencies, and provides advanced features like **event handling, internationalization, and AOP**. It’s an enhanced version of `BeanFactory` and is commonly used in Spring applications.

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

## 14. What is @Primary, @Qualifier, @Component, @Configuration, @PatchMapping annotation?

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

## 15. Explain Spring Boot Actuator endpoints.

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

## 16. How do you secure a Java Spring Boot application?

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
## 17. What is Lombok in Java and and whe can we use?

**Lombok** is a Java library that reduces boilerplate code by automatically generating getters, setters, constructors, and other methods using annotations.

If we don’t want to manually write **getters and setters** in Java, we can use **Lombok’s `@Getter` and `@Setter` annotations** on the class or fields.

Alternatively, `@Data` generates **getters, setters, `toString()`, `equals()`, and `hashCode()`** all at once.

## 17.How do you create custom auto-configuration?

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

## 18. Why do we use Long in JpaRepository<Employee, Long>?
In **`JpaRepository<Employee, Long>`**, the **first type (`Employee`)** is the **entity class** the repository manages, and the **second type (`Long`)** is the **type of the entity’s primary key (`@Id`)**.

Using `Long` tells Spring Data JPA what type of value to expect when performing operations like `findById()`, `deleteById()`, or `save()`.

## 19. What is Transactional  Why @Transactional Matters in Spring Boot?
**@Transactional** is an annotation in **Spring Boot** used to manage **database transactions automatically**.

A **transaction** means a group of database operations that must **all succeed or all fail together**.

---

### Why **@Transactional** Matters

1. **Data Consistency**
   Ensures all database operations are completed successfully. If one fails, everything is rolled back.

2. **Automatic Rollback**
   If an exception occurs, Spring automatically **rolls back the transaction**.

3. **Simplifies Code**
   Developers don't need to manually write **commit or rollback logic**.

4. **Maintains Data Integrity**
   Prevents partial updates in the database.

---

### Simple Example

```java
@Service
public class PaymentService {

    @Transactional
    public void transferMoney() {
        debitAccount();
        creditAccount();
    }
}
```

If **creditAccount() fails**, Spring will **rollback debitAccount()** automatically.

## 20. How to implement many to many, many to one and one to many in java?

**One-To-Many**

```java
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

}
```

```java
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
```

**Many-To-One**
Many **Employees** → One **Department**

```java
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
```

**Many-To-Many**
Many **Students** ↔ Many **Courses**

```java
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```

```java
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
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


## 1. What are RESTful web services?

**RESTful web services** are **web services based on REST architecture** they use **HTTP methods (GET, POST, PUT, DELETE)** to operate on **resources identified by URLs**.

They are **stateless, platform-independent**, and typically exchange data in **JSON or XML** format.


## 2. What are the principles of REST?

REST (Representational State Transfer) is based on six key architectural principles that guide the design of web services.

**REST Principles:**
- **Stateless:** Each request contains all necessary information
- **Client-Server:** Separation of concerns between client and server
- **Cacheable:** Responses can be cached for better performance
- **Uniform Interface:** Consistent way to interact with resources
- **Layered System:** Architecture can have multiple layers
- **Code on Demand:** Optional - server can send executable code

These principles ensure scalability, reliability, and maintainability of web services.


## 275: What is XML how to return XML in response?

**XML (eXtensible Markup Language)** is a **markup language** for representing data using **tags**.

It is **verbose**, supports **attributes and namespaces**, is **self-documenting with schema validation**, and is used in **enterprise apps and SOAP services**.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
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


## 3. What are HTTP methods and their usage?

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

## 4. What is the difference between PUT and POST?

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

## 5. What is idempotency in REST?

Idempotency means that making the same request multiple times produces the same result as making it once. It's a crucial property for reliable and predictable web services.

**Idempotent Methods:**
- **GET:** Always returns same data
- **PUT:** Same update result
- **DELETE:** Resource remains deleted
- **HEAD, OPTIONS:** Same metadata

**Non-Idempotent Methods:**
- **POST:** Creates new resource each time

```java
// Idempotent - GET always returns same user
GET /users/123  // Returns user data
GET /users/123  // Returns same user data

// Idempotent - PUT produces same result
PUT /users/123 {"name": "John"}  // Updates user
PUT /users/123 {"name": "John"}  // Same result

// Non-idempotent - POST creates new resource each time
POST /users {"name": "John"}  // Creates user with ID 1
POST /users {"name": "John"}  // Creates user with ID 2
```

## 6. What are HTTP status codes?

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

## 1. What are SOLID principles?

**Answer:**

SOLID is an acronym for five design principles that make software more maintainable and scalable:

- **S**ingle Responsibility: A class should have one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable for their base types
- **I**nterface Segregation: Many specific interfaces are better than one general interface
- **D**ependency Inversion: Depend on abstractions, not concrete implementations

**Example:**
```java
// Single Responsibility Principle
public class UserService {
    public void createUser(User user) { /* only user logic */ }
}

public class EmailService {
    public void sendEmail(String to, String message) { /* only email logic */ }
}

// Dependency Inversion Principle
public interface PaymentProcessor {
    void processPayment(double amount);
}

public class PayPalProcessor implements PaymentProcessor {
    public void processPayment(double amount) { /* PayPal logic */ }
}
```

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

**Spoken Answer:**

> In our system, microservices mainly communicated using **REST APIs over HTTP**.
> For synchronous communication, we used **Feign Client** with service discovery through **Eureka**.
>
> For asynchronous communication, especially for event-based workflows, we used **Kafka**. This helped us reduce tight coupling and improve scalability.

> Microservices communicate in two ways:

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
@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/{orderId}")
    PaymentResponse getPaymentDetails(@PathVariable("orderId") Long orderId);
}

// `- Step 4: use in controller`
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private PaymentClient paymentClient;

    @GetMapping("/{orderId}")
    public PaymentResponse getOrder(@PathVariable Long orderId) {
        return paymentClient.getPaymentDetails(orderId);
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

## 10. How do you Handle Exception Handling in Spring Boot?

**Answer:**

In Spring Boot, exception handling is done using **@ExceptionHandler** and **@ControllerAdvice** for global exception handling.

We create custom exceptions, return proper **HTTP status codes**, and provide meaningful error responses using **ResponseEntity**. This ensures clean, centralized, and consistent error handling across the application.


```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("USER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

## 11. What is Event-Driven Architecture in Java?

**Event-Driven Architecture (EDA)** in microservices is a design where services **communicate by producing and consuming events** instead of calling each other directly.

When something happens in a service, it **publishes an event** to a message broker (like Kafka or RabbitMQ). Other services **subscribe to these events** and react asynchronously.

This makes microservices **loosely coupled, scalable, and more resilient**, since services don’t depend on each other being available at the same time.


```java
@Service
public class OrderService {
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        eventPublisher.publishEvent(new OrderCreatedEvent(order.getId()));
        return order;
    }
}

@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    emailService.sendConfirmation(event.getOrderId());
}
```

## 12. What is API Gateway?

**Answer**

An **API Gateway** is a single entry point for all client requests in a microservices architecture.

It handles **routing, authentication, rate limiting, logging, and load balancing**, and forwards requests to appropriate backend services, improving security and simplifying client communication.


**Benefits:**
- Single entry point for clients
- Centralized cross-cutting concerns
- Simplified client code
- Better security and monitoring

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

## 14. How do you Improve Performance in Spring Boot Application?

**Answer**

To improve performance in Spring Boot, I use **caching (Redis), connection pooling (HikariCP), proper indexing in database, async processing, and pagination for large data**.

Enable **asynchronous processing** where applicable, I also optimize **JVM settings, reduce unnecessary logging, use efficient queries, and monitor the application using Actuator and profiling tools** to identify bottlenecks.

```java
@Service
public class UserService {
    
    @Cacheable("users")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Async
    public CompletableFuture<Void> sendEmailAsync(String email) {
        emailService.sendEmail(email);
        return CompletableFuture.completedFuture(null);
    }
}
```

## 15. Have you worked with the Java 11 HTTP Client? How does it differ from the HTTP clients used in earlier Java versions?

Yes, I’ve worked with the **Java 11 HTTP Client**. It’s a modern HTTP client introduced in Java 11 under the `java.net.http` package. It supports **HTTP/1.1 and HTTP/2**, has **built-in asynchronous and non-blocking calls** using `CompletableFuture`, and provides a clean, fluent API.

In earlier Java versions, developers typically used **`HttpURLConnection`**, which was **blocking, verbose, and hard to use**, or relied on **third-party libraries** like Apache HttpClient or OkHttp for advanced features.

So compared to older clients, the Java 11 HTTP Client is **simpler, more efficient, async-friendly, and officially supported by the JDK**.


```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://example.com"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
```

The old HttpURLConnection required much more boilerplate code and didn't support modern features like HTTP/2 or reactive programming patterns.

## 16. What is service discovery?

**Service Discovery** is a mechanism in microservices architecture where services automatically find and communicate with each other without hardcoding their IP addresses.

**How it works:**
- Services register themselves with discovery server
- Services query discovery server to find other services
- Handles dynamic IP addresses and scaling
- Provides health checking and load balancing

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

# ✅ 22. Java and Application Security

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

---

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

---

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

---

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

---

## 7: What is SSL/TLS in Java?

**SSL/TLS in Java** are **secure communication protocols** for encrypted data transmission (e.g., **HTTPS**).

They use a **handshake process** and **certificates** to establish trust, supported by **JSSE**, with **KeyStore and TrustStore** for managing keys and certificates.

```java
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

🔹 **Application Security**

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

---

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

---

## 10: What is JWT (JSON Web Token)?

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

---

## 11: What is CSRF protection?

**CSRF protection** prevents **unauthorized actions** caused by malicious websites.

It uses a **CSRF token** validated by the server, supports **SameSite cookies** and **double submit tokens**, and is **automatically handled in Spring Security**.


## 12: What is XSS protection?

**XSS protection** prevents **malicious script injection** in web applications.

It defends against **Reflected, Stored, and DOM XSS** using **input validation, output encoding, sanitization, and Content Security Policy (CSP)**.


## 13: What is input validation?

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

## 14: What is OAuth 2.0?

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


## 1: How do you measure Java application performance?

* **Response Time**: Time to complete requests
* **Throughput**: Requests processed per second
* **Resource Utilization**: CPU, memory, disk, network usage
* **JVM Metrics**: Heap usage, GC frequency, thread count
* **Tools**: JProfiler, VisualVM, JConsole, Micrometer
* **APM Solutions**: New Relic, AppDynamics, Dynatrace

```java
// Micrometer metrics example
@RestController
public class UserController {
    private final MeterRegistry meterRegistry;
    private final Timer requestTimer;
    
    public UserController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.requestTimer = Timer.builder("user.requests")
            .description("User API request duration")
            .register(meterRegistry);
    }
    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return requestTimer.recordCallable(() -> userService.findById(id));
    }
}
```

## 2: What are the common performance bottlenecks in Java?

* **Memory Issues**: Memory leaks, excessive GC, heap exhaustion
* **CPU Intensive**: Inefficient algorithms, excessive loops
* **I/O Bottlenecks**: Database queries, file operations, network calls
* **Threading Issues**: Synchronization overhead, thread contention
* **JVM Configuration**: Inappropriate heap size, GC settings
* **Database**: Slow queries, missing indexes, connection pooling
* **Caching**: Lack of caching or cache misses

```java
// Common bottleneck examples
public class PerformanceBottlenecks {
    
    // Memory leak - static collection grows indefinitely
    private static List<String> cache = new ArrayList<>();
}
```

---

## 3: How do you optimize Java code for performance?

* **Algorithm Optimization**: Use efficient data structures and algorithms
* **Memory Management**: Avoid object creation in loops, use object pools
* **Caching**: Cache expensive computations and database results
* **Lazy Loading**: Load data only when needed
* **Batch Operations**: Process data in batches instead of one-by-one
* **Asynchronous Processing**: Use CompletableFuture for non-blocking operations
* **Database Optimization**: Use proper indexes, optimize queries

```java
// Performance optimization examples
@Service
public class OptimizedUserService {
    
    // Cache expensive operations
    @Cacheable("users")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    // Batch processing instead of individual operations
    public void updateUsers(List<User> users) {
        userRepository.saveAll(users); // Batch instead of individual saves
    }
    
    // Asynchronous processing
    @Async
    public CompletableFuture<String> processAsync(String data) {
        // Long-running operation
        return CompletableFuture.completedFuture(processData(data));
    }
    
    // Efficient string concatenation
    public String buildMessage(List<String> parts) {
        return String.join(", ", parts); // Instead of += in loop
    }
}
```

---

## 4: What is profiling in Java?

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

---

## 5: What is JVM tuning?

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

---

## 6: What are the JVM parameters for performance tuning?

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

---

## 7: What is memory profiling?

**Memory profiling** is the analysis of an application's **memory usage and allocation patterns**.

It helps identify **heap usage, object retention, memory leaks**, and uses tools like **Eclipse MAT, JProfiler, VisualVM**, along with **heap dumps** for detailed analysis.

* Analysis of application memory usage patterns and allocation
* **Heap Analysis**: Object allocation, retention, and garbage collection
* **Memory Leaks**: Identify objects that aren't being garbage collected
* **Allocation Patterns**: Track where and how objects are created
* **Tools**: Eclipse MAT, JProfiler, VisualVM, JConsole
* **Heap Dumps**: Snapshots of memory for offline analysis

---

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


---

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

---

## 10: What is code profiling?

**Code profiling** is the process of analyzing how your code runs to find performance issues.

It measures **execution time, memory usage, and method hotspots**, using static or runtime (dynamic) analysis tools to identify slow or inefficient parts of the code.


```java
// Code profiling with annotations
@Component
public class ProfiledCodeService {
    
    // Profile specific methods
    @Profile("development")
    @EventListener
    public void onMethodExecution(MethodExecutionEvent event) {
        if (event.getDuration() > 100) {
            logger.info("Slow method: {} took {}ms", 
                event.getMethodName(), event.getDuration());
        }
    }
    
    // Benchmark critical code sections
    @Benchmark
    public String optimizedStringOperation(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append(",");
        }
        return sb.toString();
    }
}
```

---

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

---

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

---

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

---

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
    
    // Lazy loading - items loaded on demand
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;
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

---

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

JIT (Just-In-Time) compilation is a runtime optimization where the JVM compiles frequently executed bytecode into native machine code for better performance.

**How JIT works:**
- **Interpretation:** Initially executes bytecode in interpreter
- **Profiling:** Monitors method execution frequency and patterns
- **Compilation:** Compiles hot methods to native code
- **Optimization:** Applies advanced optimizations based on runtime data

**JIT Optimizations:**
- **Inlining:** Embed method calls directly
- **Dead code elimination:** Remove unused code
- **Loop optimization:** Optimize frequently executed loops
- **Escape analysis:** Optimize object allocations

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

## 1. What are the new features in Java 8?

Java 8 was a major release that introduced functional programming features and significantly changed how Java code is written. It's one of the most important Java releases.

**Major Features:**
- **Lambda Expressions:** Anonymous functions for functional programming
- **Stream API:** Functional-style operations on collections
- **Optional Class:** Better null handling
- **Default Methods:** Interface methods with implementation
- **Method References:** Shorthand for lambda expressions
- **Date/Time API:** New java.time package
- **Nashorn JavaScript Engine:** JavaScript execution in JVM

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

## 2. What are the new features in Java 11?

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

## 3. What are the new features in Java 17?

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

# ✅ 25. CI/CD and DevOp

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

---

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

---

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

---

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

---

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

---

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

---

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

---

## 8: What is blue-green deployment?

**Blue-Green deployment** is a strategy where two identical production environments are maintained — one live (**Blue**) and one with the new version (**Green**).

After testing, traffic is switched to Green, ensuring **zero downtime and quick rollback**, but it requires double infrastructure resources.

* Deployment strategy using two identical production environments
* **Blue**: Current live environment serving users
* **Green**: New environment with updated application
* **Switch**: Instant switch from blue to green after validation
* **Zero Downtime**: No service interruption during deployment
* **Quick Rollback**: Instant rollback by switching back to blue
* **Resource Cost**: Requires double the infrastructure resources

```yaml
# Blue-Green deployment with Kubernetes
# Blue environment (current)
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    app: myapp
    version: blue  # Currently pointing to blue
  ports:
  - port: 80
    targetPort: 8080

---
# Green deployment (new version)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-green
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
      version: green
  template:
    metadata:
      labels:
        app: myapp
        version: green
    spec:
      containers:
      - name: app
        image: myapp:v2.0.0
```

---

## 9: What is canary deployment?

**Canary deployment** is a strategy where a new version of an application is released to a **small percentage of users first**, and then gradually rolled out to everyone.

It helps **reduce risk, monitor performance, and quickly roll back** if issues are detected.

* Deployment strategy that releases new version to small subset of users first
* **Gradual Rollout**: Start with 5-10% of traffic, gradually increase
* **Risk Mitigation**: Limit blast radius of potential issues
* **Monitoring**: Monitor metrics and user feedback during rollout
* **Automated Rollback**: Automatic rollback if metrics degrade
* **A/B Testing**: Can be combined with A/B testing for feature validation
* **Traffic Splitting**: Use load balancers or service mesh for traffic control

```yaml
# Canary deployment with Istio
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: myapp
spec:
  http:
  - match:
    - headers:
        canary:
          exact: "true"
    route:
    - destination:
        host: myapp
        subset: v2
  - route:
    - destination:
        host: myapp
        subset: v1
      weight: 90  # 90% to stable version
    - destination:
        host: myapp
        subset: v2
      weight: 10  # 10% to canary version
```

```java
// Feature flag for canary deployment
@RestController
public class UserController {
    
    @Autowired
    private FeatureToggleService featureToggle;
    
    @GetMapping("/users")
    public List<User> getUsers() {
        if (featureToggle.isEnabled("new-user-api", getCurrentUser())) {
            return newUserService.getUsers(); // Canary version
        }
        return userService.getUsers(); // Stable version
    }
}
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

---

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

# ✅ 26. Monitoring and Logging

## 1: What is application monitoring?

**Application monitoring** is the continuous tracking of an application's **performance, health, and behavior in production**.

It includes monitoring metrics like response time, errors, CPU, and memory, along with logs and traces, using tools like **Prometheus**, **Grafana**, **New Relic**, **Datadog**, and **AppDynamics** to detect and resolve issues proactively.

```java
// Application monitoring with Micrometer
@RestController
public class UserController {
    
    private final MeterRegistry meterRegistry;
    private final Counter userCreationCounter;
    private final Timer responseTimer;
    
    public UserController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.userCreationCounter = Counter.builder("users.created")
            .description("Number of users created")
            .register(meterRegistry);
        this.responseTimer = Timer.builder("api.response.time")
            .register(meterRegistry);
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return responseTimer.recordCallable(() -> {
            User created = userService.create(user);
            userCreationCounter.increment();
            return created;
        });
    }
}
```

---

## 2: What is logging framework?

A **logging framework** is a library that provides a structured way to record application events and errors.

It supports different **log levels (DEBUG, INFO, WARN, ERROR)**, configurable output destinations (console, file, etc.), and flexible formatting. Popular frameworks include **SLF4J**, **Apache Log4j**, **Logback**, and **java.util.logging**.

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

---

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

---

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

---

## 5: What is Logback?

**Logback** is a logging framework and the native implementation of the SLF4J API, designed as the successor to Log4j 1.x.

It offers better performance, flexible configuration, automatic reload support, and is the default logging framework in Spring Boot.

```xml
<!-- logback-spring.xml configuration -->
<configuration>
    <springProfile name="dev">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="DEBUG">
            <appender-ref ref="CONSOLE"/>
        </root>
    </springProfile>
    
    <springProfile name="prod">
        <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>logs/application.log</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>logs/application.%d{yyyy-MM-dd}.%i.gz</fileNamePattern>
                <maxFileSize>100MB</maxFileSize>
                <maxHistory>30</maxHistory>
            </rollingPolicy>
            <encoder>
                <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="INFO">
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>
</configuration>
```

---

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

---

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

## 8: What is metrics collection?

**Metrics collection** is the process of gathering **quantitative data about system and application performance** over time.

It includes system metrics (CPU, memory), application metrics (response time, error rate), and business metrics, and is commonly done using tools like **Micrometer**, **Prometheus**, **InfluxDB**, and **Amazon CloudWatch**.

```java
// Metrics collection with Micrometer
@Component
public class MetricsCollector {
    
    private final MeterRegistry meterRegistry;
    private final Counter orderCounter;
    private final Timer orderProcessingTimer;
    private final Gauge activeUsers;
    
    public MetricsCollector(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        
        // Counter for total orders
        this.orderCounter = Counter.builder("orders.total")
            .description("Total number of orders")
            .tag("status", "created")
            .register(meterRegistry);
            
        // Timer for processing duration
        this.orderProcessingTimer = Timer.builder("orders.processing.time")
            .description("Order processing time")
            .register(meterRegistry);
            
        // Gauge for active users
        this.activeUsers = Gauge.builder("users.active")
            .description("Number of active users")
            .register(meterRegistry, this, MetricsCollector::getActiveUserCount);
    }
    
    public void recordOrderCreated() {
        orderCounter.increment();
    }
    
    public void recordOrderProcessingTime(Duration duration) {
        orderProcessingTimer.record(duration);
    }
    
    private double getActiveUserCount() {
        return userService.getActiveUserCount();
    }
}
```

---

## 9: What is JMX monitoring?

**JMX (Java Management Extensions) monitoring** is a standard way to **monitor and manage Java applications**.

It uses **MBeans** to expose metrics and operations, and tools like **JConsole** allow local or remote monitoring and management of running JVM applications.

```java
// Custom MBean for monitoring
@Component
public class ApplicationMonitorMBean implements ApplicationMonitorMXBean {
    
    private final UserService userService;
    private final OrderService orderService;
    
    @Override
    public long getTotalUsers() {
        return userService.getTotalUserCount();
    }
    
    @Override
    public long getActiveOrders() {
        return orderService.getActiveOrderCount();
    }
    
    @Override
    public double getAverageResponseTime() {
        return performanceService.getAverageResponseTime();
    }
    
    @Override
    public void clearCache() {
        cacheService.clearAll();
    }
    
    @Override
    public String getApplicationStatus() {
        return healthService.getOverallStatus();
    }
}

// MBean interface
public interface ApplicationMonitorMXBean {
    long getTotalUsers();
    long getActiveOrders();
    double getAverageResponseTime();
    void clearCache();
    String getApplicationStatus();
}
```

# ✅ 27. Miscellaneous