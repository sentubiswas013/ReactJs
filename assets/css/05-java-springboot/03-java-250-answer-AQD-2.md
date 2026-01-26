# 1. Java Basic Concepts 

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

**JVM (Java Virtual Machine):**
- Runtime environment that executes Java bytecode
- Platform-specific implementation
- Handles memory management and garbage collection

**JRE (Java Runtime Environment):**
- Includes JVM plus core libraries
- Needed to run Java applications
- Contains runtime classes and resources

**JDK (Java Development Kit):**
- Complete development environment
- Includes JRE plus development tools (compiler, debugger)
- Needed to develop Java applications

```
JDK = JRE + Development Tools (javac, javadoc, jar, etc.)
JRE = JVM + Core Libraries (java.lang, java.util, etc.)
JVM = Runtime execution environment
```

## 3. What are the main principles of Object-Oriented Programming?

Object-Oriented Programming is based on four fundamental principles that promote code reusability, maintainability, and modularity.

**Four OOP Principles:**
The four main principles are:
- **Encapsulation**: Wrapping data and methods into a single unit (class) and restricting access to some components using access modifiers.
- **Inheritance**: The ability of a class to inherit the properties and behaviors of another class.
- **Polymorphism**: Allowing one entity to take multiple forms (e.g., method overloading, method overriding).
- **Abstraction**: Hiding the implementation details and showing only the necessary functionality to the user.

These principles help create modular, maintainable, and scalable software by organizing code around objects that represent real-world entities.

## 4. What is polymorphism? Explain with examples.

Polymorphism means "many forms" - the ability of objects to take multiple forms. The same method call can behave differently depending on the object type.

**Types of Polymorphism:**
- **Runtime Polymorphism:** Method overriding (dynamic binding)
- **Compile-time Polymorphism:** Method overloading (static binding)

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

**Implementation in Java:**
- **Private fields:** Hide data from direct access
- **Public methods:** Provide controlled access (getters/setters)
- **Access modifiers:** Control visibility (private, protected, public)

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

**Types of Inheritance:**
- **Single Inheritance:** One class extends another class
- **Multilevel Inheritance:** Chain of inheritance (A→B→C)
- **Hierarchical Inheritance:** Multiple classes inherit from one parent
- **Multiple Inheritance:** Not supported in Java (use interfaces)
- **Hybrid Inheritance:** Combination of above types

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

**Characteristics:**
- Cannot create objects directly
- Can have constructors and instance variables
- May contain abstract and concrete methods
- Extended using 'extends' keyword
- Supports single inheritance only

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
A package in Java is a collection of related classes and interfaces grouped to organize code and prevent naming conflicts. 
- Built-in packages: java.lang, java.util, etc.
- User-defined packages: Created by developers for organizing custom classes

# 2. Data Types and Variables

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

Autoboxing automatically converts primitives to their wrapper objects, while unboxing does the reverse. Java handles this conversion automatically.

```java
// Autoboxing - primitive to wrapper
Integer num = 100;  // int 100 becomes Integer object

// Unboxing - wrapper to primitive  
int value = num;    // Integer object becomes int
```

This happens automatically in collections, method calls, and assignments.

## 4. What is the difference between == and equals() method?

The == operator compares memory addresses for objects, while equals() compares actual content.

**For primitives:** == compares values
**For objects:** == compares references, equals() compares content

```java
String a = new String("hello");
String b = new String("hello");

System.out.println(a == b);       // false - different objects
System.out.println(a.equals(b));  // true - same content
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

Use StringBuilder for single-threaded operations, StringBuffer for multi-threaded.

## 6. Why are strings immutable in Java?

Strings are immutable for several important reasons:

- **Security:** Prevents malicious code from changing string values
- **Thread Safety:** Multiple threads can access without synchronization
- **String Pool:** Enables efficient memory usage through sharing
- **Hashcode Caching:** Hash values remain constant for HashMap keys
- **Performance:** JVM optimizations possible

Once created, String objects cannot be modified - operations create new objects instead.

## 7. What is string pooling?

String pooling is Java's memory optimization technique where identical string literals share the same memory location in the String Pool (part of heap memory).

```java
String a = "hello";    // stored in string pool
String b = "hello";    // reuses same memory location
String c = new String("hello"); // creates new object in heap

System.out.println(a == b);  // true - same reference
System.out.println(a == c);  // false - different references
```

This reduces memory usage and improves performance for string literals.

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

Coercion is automatic type conversion that Java performs when you assign a smaller data type to a larger one. For example, when you put an int into a double variable, Java automatically converts it. This only works for widening conversions - for narrowing like double to int, you need explicit casting to prevent data loss.

```java
int num = 10;
double result = num; // Automatic coercion - int to double

double d = 10.5;
int i = (int) d; // Explicit casting required - double to int
```


# 3. Classes and Objects

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

**this** refers to the current object instance, while **super** refers to the immediate parent class.

**this keyword:**
- References current object
- Calls current class constructor/methods
- Resolves naming conflicts

**super keyword:**
- References parent class
- Calls parent constructor/methods
- Accesses overridden methods

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

Method overloading means having multiple methods with the same name but different parameters in the same class. Java decides which method to call based on arguments.

- Same method name, different parameters
- Compile-time polymorphism
- Parameters can differ by number, type, or order

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

Method overriding is redefining a parent class method in the child class with the same signature. The child class version gets called instead of the parent's.

- Same method signature as parent
- Runtime polymorphism
- Use @Override annotation for safety
- Child method must be equally or more accessible

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

## 6. What is the difference between overloading and overriding?

**Method Overloading:**
- Same class, same method name, different parameters
- Compile-time decision (static binding)
- No inheritance required
- Can have different return types

**Method Overriding:**
- Parent-child classes, same method signature
- Runtime decision (dynamic binding)
- Requires inheritance
- Must have same return type

```java
// Overloading - same class
class Math {
    int multiply(int a, int b) { return a * b; }
    double multiply(double a, double b) { return a * b; }
}

// Overriding - inheritance
class Shape { void draw() { } }
class Circle extends Shape { 
    @Override void draw() { } // Same signature
}
```

## 7. What is dynamic method dispatch?

Dynamic method dispatch is Java's mechanism for runtime polymorphism where the actual method called is determined at runtime based on the object type, not reference type.

- JVM decides which overridden method to call
- Based on actual object type at runtime
- Enables true polymorphism in inheritance

```java
Animal animal1 = new Dog();
Animal animal2 = new Cat();

animal1.sound(); // Calls Dog's sound() method
animal2.sound(); // Calls Cat's sound() method

// Reference type is Animal, but actual method called
// depends on object type (Dog or Cat)
```

# 4. Java Inheritance 

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

# 5. Java Interface & Abstract Class 

## 1. What is an interface in Java?

An interface is a contract that defines what methods a class must implement without providing the implementation. It's a blueprint for classes that ensures consistent behavior.d

An **interface** in Java is a **reference type** that defines a **contract of methods** that a class must implement. It contains **abstract methods**, and since Java 8, it can also have **default and static methods**.

Interfaces are used to achieve **abstraction and multiple inheritance of behavior**. A class can implement **multiple interfaces**, allowing loose coupling and flexible design.

In short: an interface **defines “what to do” without specifying “how to do it”**.


- Contains abstract methods by default
- All methods are public by default
- Variables are public, static, and final
- Supports multiple inheritance
- Implemented using 'implements' keyword

```java
interface Drawable {
    void draw(); // abstract method
    int MAX_SIZE = 100; // public static final
}

class Circle implements Drawable {
    public void draw() { System.out.println("Drawing circle"); }
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

# 6. Java Exception Handling 

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

**throw** is used to explicitly throw an exception, while **throws** is used to declare that a method might throw exceptions.

**throw:**
- Throws actual exception object
- Used inside method body
- Followed by exception instance

**throws:**
- Declares possible exceptions
- Used in method signature
- Followed by exception class names

```java
public void validateAge(int age) throws IllegalArgumentException {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
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

Try-with-resources automatically closes resources that implement AutoCloseable interface. It ensures proper resource management without explicit finally blocks.

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

# 7. Java Collections Framework

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

ArrayList uses dynamic arrays for storage, while LinkedList uses doubly-linked nodes. This affects their performance characteristics for different operations.

**ArrayList:**
- Random access O(1)
- Insertion/deletion at middle O(n)
- Better for frequent access
- Contiguous memory storage

**LinkedList:**
- Sequential access O(n)
- Insertion/deletion O(1) if node known
- Better for frequent modifications
- Non-contiguous memory storage

```java
List<String> arrayList = new ArrayList<>(); // Fast access
List<String> linkedList = new LinkedList<>(); // Fast insertion/deletion
```

## 3. What is the difference between HashMap and TreeMap?

HashMap provides O(1) average access time with no ordering, while TreeMap maintains sorted order with O(log n) access time.

**HashMap:**
- Hash table implementation
- O(1) average time complexity
- No ordering of keys
- Allows one null key

**TreeMap:**
- Red-black tree implementation
- O(log n) time complexity
- Sorted order of keys
- No null keys allowed

```java
Map<String, Integer> hashMap = new HashMap<>(); // Fast, unordered
Map<String, Integer> treeMap = new TreeMap<>(); // Slower, sorted
```

## 4. What is the difference between HashMap and Hashtable?

HashMap is not synchronized and allows null values, while Hashtable is synchronized and doesn't allow nulls. HashMap is preferred for single-threaded applications.

**HashMap:**
- Not synchronized (not thread-safe)
- Allows one null key and multiple null values
- Introduced in Java 1.2
- Better performance

**Hashtable:**
- Synchronized (thread-safe)
- No null keys or values allowed
- Legacy class from Java 1.0
- Slower due to synchronization

```java
Map<String, Integer> hashMap = new HashMap<>(); // Modern, faster
Map<String, Integer> hashtable = new Hashtable<>(); // Legacy, thread-safe
```

## 5. How does HashMap work internally?

HashMap uses an array of buckets where each bucket can hold multiple key-value pairs. It uses hashing to determine which bucket to use for storing entries.

- Uses array of Node objects (buckets)
- Hash function determines bucket index
- Handles collisions with chaining (linked list/tree)
- Rehashing occurs when load factor exceeds threshold

```java
// Simplified internal process:
// 1. hash(key) -> bucket index
// 2. Store/retrieve from that bucket
// 3. Handle collisions in same bucket

Map<String, Integer> map = new HashMap<>();
map.put("key", 100); // hash("key") -> bucket index -> store
```

## 6. What is hash collision and how is it handled?

Hash collision occurs when two different keys produce the same hash code, mapping to the same bucket. HashMap handles this using chaining and tree conversion.

- **Chaining:** Multiple entries in same bucket form linked list
- **Tree conversion:** When chain length > 8, converts to balanced tree
- **Load factor:** Rehashing when buckets become too full
- **Open addressing:** Alternative approach (not used in HashMap)

```java
// Collision example:
// hash("Aa") and hash("BB") might produce same value
map.put("Aa", 1);
map.put("BB", 2); // Collision - stored in same bucket as linked list
```

## 7. What is the difference between fail-fast and fail-safe iterators?

Fail-fast iterators throw ConcurrentModificationException when collection is modified during iteration, while fail-safe iterators work on a copy and don't throw exceptions.

**Fail-fast:**
- Detects concurrent modifications
- Throws ConcurrentModificationException
- Examples: ArrayList, HashMap iterators
- Works on original collection

**Fail-safe:**
- Allows concurrent modifications
- Works on cloned copy
- Examples: ConcurrentHashMap, CopyOnWriteArrayList
- May not reflect latest changes

```java
List<String> list = new ArrayList<>();
Iterator<String> failFast = list.iterator(); // Throws exception if modified

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
Iterator<String> failSafe = map.keySet().iterator(); // Safe for modifications
```

## 8. What is the difference between Comparable and Comparator?

Comparable provides natural ordering by implementing compareTo() method in the class itself, while Comparator provides custom ordering through external comparison logic.

**Comparable:**
- Single sorting sequence
- compareTo() method in class
- Natural ordering
- Part of java.lang package

**Comparator:**
- Multiple sorting sequences
- External compare() method
- Custom ordering
- Part of java.util package

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

These are specialized collection implementations with unique characteristics for specific use cases.

**WeakHashMap:**
- Keys are weak references
- Entries removed when key is garbage collected
- Useful for caches and memory-sensitive applications

**IdentityHashMap:**
- Uses == instead of equals() for key comparison
- Allows duplicate "equal" keys
- Useful when object identity matters

**LinkedHashMap:**
- Maintains insertion or access order
- Combines HashMap performance with predictable iteration
- Useful for LRU caches

**PriorityQueue:**
- Heap-based priority queue
- Elements ordered by natural ordering or Comparator
- Useful for scheduling and algorithms

```java
Map<String, Integer> weakMap = new WeakHashMap<>(); // GC-friendly
Map<String, Integer> identityMap = new IdentityHashMap<>(); // Identity-based
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // Ordered
Queue<Integer> priorityQueue = new PriorityQueue<>(); // Heap-based
```

# 8. Java Multithreading & Synchronization 

## 1. What is multithreading?

Multithreading is the ability to execute multiple threads concurrently within a single program. It allows better resource utilization and improved performance by running tasks simultaneously.

- Multiple threads share same memory space
- Enables concurrent execution of tasks
- Improves CPU utilization and responsiveness
- Threads can communicate through shared memory
- JVM manages thread scheduling

Benefits include faster execution, better resource usage, and responsive user interfaces.

## 2. How do you create threads in Java?

There are two main ways to create threads in Java: extending Thread class or implementing Runnable interface.

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

**Extending Thread:**
- Direct inheritance from Thread class
- Cannot extend other classes (single inheritance)
- Tightly coupled with Thread class
- Less flexible approach

**Implementing Runnable:**
- Can extend other classes
- Better separation of concerns
- More flexible and reusable
- Preferred approach for thread creation

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

A thread goes through various states during its lifecycle: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED.

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

Synchronization is a mechanism that ensures only one thread can access a shared resource at a time. It prevents data corruption and maintains thread safety.

- Controls access to shared resources
- Prevents race conditions
- Uses synchronized keyword or locks
- Can synchronize methods or code blocks
- Ensures thread safety but may reduce performance

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

Deadlock occurs when two or more threads are blocked forever, each waiting for the other to release a resource. It's a circular dependency situation.

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

Volatile keyword ensures that a variable's value is always read from and written to main memory, not from thread's local cache. It provides visibility guarantee across threads.

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

# 9. Java Advanced Concurrency 

## 0. What is **Concurrency in Java**?

**Concurrency in Java** is the ability of a program to **execute multiple tasks at the same time** by using **multiple threads**. These tasks can run **in parallel on multiple CPU cores** or be **interleaved on a single core** to improve performance and responsiveness.

* **Simple example**

```java
new Thread(() -> System.out.println("Task running")).start();
```

This creates a new thread that runs concurrently with the main thread.

---

* **How concurrency is achieved in Java**

* **Thread & Runnable**
* **ExecutorService**
* **Synchronization**
* **Locks (`ReentrantLock`)**
* **Atomic variables**
* **Concurrent collections**
* **CompletableFuture (Java 8+)**

---

* **Concurrency vs Parallelism**

| Concurrency                | Parallelism                             |
| -------------------------- | --------------------------------------- |
| Multiple tasks in progress | Multiple tasks executing simultaneously |
| May run on one CPU         | Requires multiple cores                 |


## 1. What is ExecutorService?

ExecutorService is a high-level framework for managing and controlling thread execution. It provides a way to submit tasks and manage their lifecycle without directly creating threads.

- Manages thread pools automatically
- Provides methods to submit tasks (submit, execute)
- Handles thread creation, reuse, and termination
- Returns Future objects for tracking task progress
- Better alternative to manual thread management

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

// Submit tasks
executor.submit(() -> System.out.println("Task executed"));
executor.execute(() -> System.out.println("Another task"));

executor.shutdown(); // Graceful shutdown
```

## 2. What are the types of thread pools?

Java provides several predefined thread pool types through Executors class, each optimized for different use cases.

**Fixed Thread Pool:**
- Fixed number of threads
- Good for known workload

**Cached Thread Pool:**
- Creates threads as needed
- Reuses idle threads

**Single Thread Executor:**
- Single worker thread
- Sequential task execution

**Scheduled Thread Pool:**
- Supports delayed and periodic tasks

```java
ExecutorService fixed = Executors.newFixedThreadPool(10);
ExecutorService cached = Executors.newCachedThreadPool();
ExecutorService single = Executors.newSingleThreadExecutor();
ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(5);
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

CountDownLatch is a synchronization utility that allows one or more threads to wait until a set of operations being performed by other threads completes.

- Initialized with a count
- Threads wait until count reaches zero
- countDown() decreases the count
- await() blocks until count is zero
- One-time use only (cannot be reset)

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

ReentrantLock is an explicit lock implementation that provides more flexibility than synchronized blocks. It allows the same thread to acquire the lock multiple times.

- Explicit lock/unlock operations
- Supports fairness policy
- Interruptible lock acquisition
- Try-lock with timeout
- Same thread can acquire multiple times (reentrant)

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

# 10. Java JVM & Memory Management 

## 1. What are the different memory areas in JVM?

JVM divides memory into several distinct areas, each serving specific purposes for program execution and memory management.

**Main Memory Areas:**
- **Heap:** Object storage (Young + Old generation)
- **Stack:** Method calls and local variables
- **Method Area/Metaspace:** Class metadata and constants
- **PC Register:** Current instruction pointer
- **Native Method Stack:** Native method calls
- **Direct Memory:** Off-heap memory (NIO operations)

Each area has different characteristics for garbage collection and memory allocation strategies.

## 2. What is the difference between heap and stack?

The **heap** is used for dynamic memory allocation and stores objects and class instances. Memory is shared across threads and managed by the **garbage collector**, which makes it slower than stack but much larger in size.

The **stack** is used for method execution and stores local variables, method calls, and references. It works in a **LIFO** manner and memory is allocated and released automatically, so it’s very fast but limited in size. Each thread has its own stack.

**Heap:**
- Stores objects and instance variables
- Shared among all threads
- Garbage collected
- Slower access
- Dynamic memory allocation

**Stack:**
- Stores method calls and local variables
- Thread-specific (each thread has own stack)
- Automatic cleanup when method exits
- Faster access (LIFO structure)
- Fixed size per thread

```java
public void method() {
    int x = 10;        // Stack - local variable
    String obj = new String("Hello"); // obj reference on stack, object on heap
}
```

## 3. What is the difference between PermGen and Metaspace?

**PermGen (Java 7 and earlier):**
- Fixed size heap area
- Stored class metadata
- Caused OutOfMemoryError when full
- Part of heap memory

**Metaspace (Java 8+):**
- Native memory (outside heap)
- Dynamic size expansion
- Automatically managed by OS
- Better memory utilization
- Eliminates PermGen OutOfMemoryError

Metaspace replaced PermGen to solve memory limitations and provide better class metadata management.

## 4. What is garbage collection?

Garbage collection is JVM's automatic memory management process that reclaims memory occupied by objects that are no longer reachable or referenced by the application.

- Automatic memory cleanup
- Removes unreferenced objects
- Prevents memory leaks
- Runs periodically or when memory is low
- Frees developers from manual memory management

The GC identifies objects with no active references and deallocates their memory, making it available for new objects.

## 5. What are the types of garbage collectors?

Java provides several garbage collectors, each optimized for different application requirements and performance characteristics.

**Serial GC:**
- Single-threaded
- Good for small applications

**Parallel GC:**
- Multi-threaded
- Default for server applications

**G1 GC:**
- Low-latency collector
- Good for large heaps

**ZGC/Shenandoah:**
- Ultra-low latency
- Concurrent collection

**CMS (deprecated):**
- Concurrent mark-sweep
- Low pause times

Choose based on application needs: throughput vs latency requirements.

## 6. What is generational garbage collection?

Generational garbage collection is based on the observation that most objects die young. It divides heap into generations and applies different collection strategies.

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

## 8. What is metaspace?

Metaspace is the native memory area where JVM stores class metadata, replacing PermGen from Java 8 onwards. It's allocated in native memory outside the heap.

**Characteristics:**
- Native memory allocation
- Dynamic size expansion
- No fixed size limit (limited by available system memory)
- Garbage collected when classes are unloaded
- Better memory utilization than PermGen

**Contents:**
- Class definitions and metadata
- Method bytecode
- Constant pool information
- Runtime constant pool

Metaspace eliminates PermGen OutOfMemoryError issues and provides more flexible memory management.

## 9. What are GC roots?

GC roots are objects that are always reachable and serve as starting points for garbage collection reachability analysis. Objects reachable from GC roots are considered live.

**Types of GC Roots:**
- Local variables in stack frames
- Static variables in classes
- JNI global references
- Objects in native method stacks
- Thread objects
- System class loader references

**Reachability Analysis:**
- GC starts from roots
- Marks all reachable objects
- Unreachable objects are garbage collected
- Ensures no live objects are accidentally collected

```java
public class Example {
    static String staticVar = "root";     // GC root - static variable
    
    public void method() {
        String localVar = "root";         // GC root - local variable
        Object obj = new Object();        // Reachable from localVar
    }
}
```

# 11. Java Input/Output (I/O) 

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

BufferedReader and BufferedWriter are wrapper classes that add buffering capability to improve I/O performance by reducing the number of system calls.

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

NIO (New I/O) is a collection of APIs introduced in Java 1.4 that provides non-blocking I/O operations and better performance for handling multiple connections.

**Key Components:**
- **Channels:** Bidirectional data connections
- **Buffers:** Containers for data
- **Selectors:** Multiplexing for non-blocking I/O
- **Non-blocking operations:** Don't wait for I/O completion

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

**Traditional I/O:**
- Stream-oriented (one byte/character at a time)
- Blocking operations
- Simpler API
- Good for fewer connections
- Thread per connection model

**NIO:**
- Buffer-oriented (chunks of data)
- Non-blocking operations
- More complex API
- Better for many connections
- Single thread can handle multiple connections

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

Use NIO when you need better performance and scalability, especially for server applications handling many concurrent connections.

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

**NIO is ideal for:**
- Network servers (web servers, chat servers)
- File servers handling multiple clients
- Applications requiring high throughput
- Systems with limited threads but many connections


# 12. Java Generics 

## 1. What are generics in Java?

Generics allow you to write type-safe code by parameterizing types. They enable classes, interfaces, and methods to work with different types while providing compile-time type checking.

- Parameterized types using angle brackets <>
- Provide compile-time type safety
- Eliminate need for explicit casting
- Enable writing reusable code
- Introduced in Java 5

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

# 13. Java Annotations & Reflection 

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

Reflection is the ability to inspect and manipulate classes, methods, fields, and other code elements at runtime. It allows programs to examine their own structure dynamically.

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

Reflection operations throw various checked exceptions that must be handled properly to ensure robust code.

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

# 14. Java Web Development - Servlets and JSP

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

# 15. Java Lambda Expressions & Streams API 

## 1. What are lambda expressions?

Lambda expressions are anonymous functions that provide a concise way to represent functional interfaces. They enable functional programming in Java and make code more readable and expressive.

- Anonymous functions without name
- Concise syntax for functional interfaces
- Enable functional programming style
- Reduce boilerplate code
- Introduced in Java 8

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

Functional interfaces are interfaces with exactly one abstract method. They can be implemented using lambda expressions and serve as the foundation for functional programming in Java.

- Exactly one abstract method (SAM - Single Abstract Method)
- Can have default and static methods
- @FunctionalInterface annotation for safety
- Target type for lambda expressions
- Examples: Runnable, Callable, Comparator

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

**Lambda Expression:**
- Only for functional interfaces
- More concise syntax
- No new class file generated
- Better performance (invokedynamic)
- 'this' refers to enclosing class

**Anonymous Class:**
- Can implement any interface or extend class
- More verbose syntax
- Creates new class file
- Slower performance
- 'this' refers to anonymous class instance

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

The **Stream API**, introduced in **Java 8**, is used to **process collections of data in a functional way**. It allows operations like filtering, mapping, and sorting without modifying the original data source.

Streams work in a **pipeline** using **intermediate operations** such as `filter()` and `map()`, and a **terminal operation** like `collect()` or `forEach()` to produce a result. This makes code cleaner, more readable, and supports easy parallel processing.


- Functional-style operations on collections
- Lazy evaluation for better performance
- Supports parallel processing
- Immutable - doesn't modify original data
- Pipeline of operations: source → intermediate → terminal

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

**Collection:**
- Data structure that stores elements
- Eagerly computed (all elements present)
- Can be modified (add/remove elements)
- External iteration (for loops)
- Reusable multiple times

**Stream:**
- Abstraction for processing data
- Lazily computed (computed on demand)
- Immutable (doesn't modify source)
- Internal iteration (handled by Stream API)
- Single-use only

```java
List<String> collection = Arrays.asList("a", "b", "c");
collection.add("d"); // Modifies collection

Stream<String> stream = collection.stream();
stream.filter(s -> s.length() > 1); // Doesn't modify collection
// stream.filter(...); // Error - stream already used
```

## 7. What are intermediate and terminal operations?

In the **Stream API**, **intermediate operations** are operations like `filter()`, `map()`, and `sorted()` that **transform a stream**. They are **lazy**, meaning they don’t execute immediately and return another stream, allowing operations to be chained.

**Terminal operations** are operations like `forEach()`, `collect()`, `reduce()`, and `count()` that **trigger the execution** of the stream pipeline and produce a final result or side effect.

**Intermediate Operations:**
- Transform stream into another stream
- Lazy evaluation (not executed until terminal operation)
- Can be chained together
- Examples: filter(), map(), sorted(), distinct()

**Terminal Operations:**
- Produce final result or side effect
- Trigger execution of entire pipeline
- Cannot be chained
- Examples: collect(), forEach(), reduce(), count()

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

numbers.stream()
    .filter(n -> n > 2)     // Intermediate - lazy
    .map(n -> n * 2)        // Intermediate - lazy
    .sorted()               // Intermediate - lazy
    .forEach(System.out::println); // Terminal - triggers execution
```

## 8. What is the difference between map() and flatMap()?

`map()` is used to **transform each element** in a stream into another form. It returns **one output for each input**, so the structure of the stream stays the same.

`flatMap()` is used when each element produces **another stream or collection**. It **flattens** those nested streams into a **single stream**, so you don’t end up with a stream of streams.


**map():**
- One-to-one transformation
- Transforms each element to another element
- Stream<T> → Stream<R>

**flatMap():**
- One-to-many transformation
- Flattens nested structures
- Stream<T> → Stream<R> (where T contains multiple R)

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

## 9. What is Optional class?

Optional is a container class that may or may not contain a value. It helps avoid NullPointerException and makes null handling more explicit and safer.

- Container for potentially null values
- Prevents NullPointerException
- Encourages explicit null handling
- Provides functional-style methods
- Should not be used for fields or parameters

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

# 16. Java JDBC 

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

**Statement:**
- Executes static SQL queries
- SQL compiled every time
- Vulnerable to SQL injection
- Less efficient for repeated queries

**PreparedStatement:**
- Executes parameterized SQL queries
- Pre-compiled SQL (better performance)
- Prevents SQL injection
- Efficient for repeated queries with different parameters

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

## 4. What is connection pooling?

Connection pooling is a technique that maintains a cache of database connections that can be reused across multiple requests, improving application performance and resource utilization.

**Benefits:**
- Reduces connection creation overhead
- Limits number of concurrent connections
- Improves application performance
- Better resource management
- Handles connection lifecycle automatically

**Popular Connection Pools:**
- HikariCP (fastest)
- Apache DBCP
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

## 5. What is SQL injection and how to prevent it?

SQL injection is a security vulnerability where malicious SQL code is inserted into application queries, potentially allowing unauthorized database access or data manipulation.

**How it happens:**
- User input directly concatenated into SQL
- Malicious input modifies query logic
- Can lead to data theft, deletion, or unauthorized access

**Prevention methods:**
- Use PreparedStatement with parameters
- Input validation and sanitization
- Stored procedures
- Least privilege database access

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

## 6. What is transaction management in JDBC?

Transaction management ensures that a group of database operations either all succeed or all fail together, maintaining data consistency and integrity.

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

### 7. How do you Handle Large Data Processing?

#### Streaming (Low Memory)
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

### 8. What is the difference between Direct Servlet and JSP?

**Spoken Answer (30 seconds):**
* Servlets are Java classes that handle HTTP requests programmatically
* JSP (JavaServer Pages) mixes HTML with Java code for dynamic web pages
* Servlets are better for business logic, JSP for presentation layer
* JSP gets compiled to servlets behind the scenes
* Modern apps use REST APIs instead of JSP for frontend separation

**Example:**
```java
// Direct Servlet
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from Servlet!</h1>");
        out.println("<p>User: " + request.getParameter("name") + "</p>");
    }
}

// JSP (hello.jsp)
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Hello JSP</title></head>
<body>
    <h1>Hello from JSP!</h1>
    <p>User: <%= request.getParameter("name") %></p>
    <p>Current time: <%= new java.util.Date() %></p>
</body>
</html>
```

# 16. Java Design Patterns 

## 1. What are design patterns?

Design patterns are reusable solutions to commonly occurring problems in software design. They represent best practices and proven solutions that developers can apply to solve design problems.

- Reusable solutions to common design problems
- Best practices in object-oriented design
- Improve code maintainability and readability
- Facilitate communication between developers
- Three categories: Creational, Structural, Behavioral

Design patterns provide a common vocabulary and proven approaches to solving recurring design challenges in software development.

## 2. What is Singleton pattern?

Singleton pattern ensures that a class has only one instance throughout the application lifecycle and provides global access to that instance.

- Only one instance of the class
- Global access point
- Lazy or eager initialization
- Used for logging, database connections, caching

```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() { } // Private constructor
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
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

- Encapsulates algorithms in separate classes
- Makes algorithms interchangeable at runtime
- Eliminates conditional statements
- Follows Open/Closed principle

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

- Converts interface of a class into another interface
- Allows incompatible classes to work together
- Wraps existing functionality
- Used for integrating third-party libraries

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

- Adds new functionality to objects dynamically
- Alternative to inheritance for extending behavior
- Maintains same interface as original object
- Can stack multiple decorators

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

# 18. Java Spring Framework 

## 1. What is Spring Framework?

Spring Framework is a comprehensive Java framework that provides infrastructure support for developing enterprise applications. It simplifies Java development through dependency injection and aspect-oriented programming.

- Lightweight and modular framework
- Provides dependency injection and IoC container
- Supports aspect-oriented programming (AOP)
- Integrates with various technologies
- Reduces boilerplate code and complexity

Spring makes Java development easier by handling common tasks and promoting best practices like loose coupling and testability.

## 2. What is Inversion of Control (IoC)?

Inversion of Control is a design principle where the control of object creation and dependency management is transferred from the application code to an external container or framework.

- Container manages object lifecycle
- Objects don't create their dependencies
- Dependencies are provided by external source
- Promotes loose coupling
- Makes code more testable and maintainable

Instead of objects creating their dependencies, the IoC container creates and injects them, inverting the traditional control flow.

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

## 3. What are the best ways to implement Dependency Injection in Java?

The best way to implement **Dependency Injection in Java** is by **constructor injection**, where dependencies are provided through the class constructor. It makes the code easier to test, ensures required dependencies are available, and supports immutability.

Another common approach is **setter injection**, where dependencies are injected using setter methods. It’s useful for optional dependencies but less safe because objects can be used without full initialization.

In real-world applications, **framework-based DI** like **Spring** is the most popular. Spring supports **constructor, setter, and field injection**, with constructor injection being the recommended best practice.



**Spoken Answer (35 seconds):**
* Three main types: Constructor injection, Setter injection, and Field injection
* Constructor injection is preferred - it ensures required dependencies are provided
* Use frameworks like Spring, Guice, or CDI for automatic injection
* Annotations like @Autowired, @Inject make it simple

**Example:**
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    // Constructor injection - best practice
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Setter injection
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
```

## 4. What is BeanFactory?

BeanFactory is Spring's basic IoC container that manages object creation and dependency injection. It uses lazy initialization, meaning beans are created only when you request them. It's the foundation of Spring's dependency injection but ApplicationContext is more commonly used because it has additional features.

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

## 6. What is Spring Boot?

**Spring Boot** is a framework built on top of Spring that simplifies application development. It provides **auto-configuration**, **embedded servers**, and **starter dependencies**, allowing developers to build production-ready applications quickly with minimal configuration.

- Rapid application development
- Auto-configuration based on classpath
- Embedded servers (Tomcat, Jetty)
- Production-ready features out of the box
- Minimal configuration required

Spring Boot eliminates most boilerplate configuration and allows developers to focus on business logic rather than setup.

## 7. What is auto-configuration in Spring Boot?

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

## 8. What is @SpringBootApplication annotation?

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

## 9. What is the difference between @Component, @Service, and @Repository?

These are stereotype annotations that mark classes as Spring beans, but they serve different purposes and provide semantic meaning.

**@Component:**
- Generic stereotype for any Spring-managed component
- Base annotation for other stereotypes

**@Service:**
- Marks service layer classes
- Contains business logic
- Semantic specialization of @Component

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

## 10. What is @Autowired annotation?

@Autowired enables automatic dependency injection by type. Spring automatically injects the required dependencies into the annotated fields, constructors, or methods.

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

## 11. What is @Qualifier annotation?

@Qualifier is used with @Autowired to specify which bean to inject when multiple beans of the same type exist. It resolves ambiguity in dependency injection.

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

## 12. What is ApplicationContext?

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

### 13. What is @Primary, @Qualifier, @Component, @Configuration, @PatchMapping annotation?

Great question — this is a **very common Spring interview topic**.
I’ll answer it in a **real-time spoken style**, explaining *why and when* we use each annotation, with **short, clear code examples**.

**@Component**

**Spoken Answer:**
“`@Component` is used to tell Spring that this class is a bean and should be managed by the Spring container. Spring automatically detects it during component scanning.”

**When to use:**

* For general-purpose beans
* When you want Spring to auto-create the object

**Example:**

```java
@Component
public class EmailService {
    public void send() {
        System.out.println("Sending email");
    }
}
```

**@Configuration**

**Spoken Answer:**
“`@Configuration` is used when we want to define beans explicitly using `@Bean` methods. It’s mainly used for Java-based configuration instead of XML.”

**When to use:**

* To create beans manually
* For third-party or complex bean creation

**Example:**

```java
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }
}
```

**@Primary**

**Spoken Answer:**
“When multiple beans of the same type exist and Spring gets confused, `@Primary` tells Spring which bean should be chosen by default.”

**Example Scenario:**
Two implementations of the same interface.

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

**@Qualifier**

**Spoken Answer:**
“`@Qualifier` is used when we want to explicitly specify which bean to inject when multiple beans of the same type are present.”

**Example:**

```java
@Autowired
@Qualifier("upiPayment")
private PaymentService paymentService;
```
**@PatchMapping**
“`@PatchMapping` is used for partial updates of a resource in REST APIs, where only specific fields are modified instead of replacing the entire object.”

```java
@PatchMapping("/users/{id}")
public ResponseEntity<User> updateEmail(
        @PathVariable Long id,
        @RequestBody Map<String, Object> updates) {

    User updatedUser = userService.updateUser(id, updates);
    return ResponseEntity.ok(updatedUser);
}
```

## 14. How do you integrate a Java application with a cloud environment?

Integrate by containerizing your application with Docker, using cloud databases and services instead of local ones, implementing health checks for monitoring, and configuring environment variables for different cloud environments. Use cloud-specific features like auto-scaling and load balancing.

```java
@RestController
public class HealthController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
```

```dockerfile
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
HEALTHCHECK CMD curl -f http://localhost:8080/health || exit 1
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Additional steps:
- Use cloud databases (RDS, Cloud SQL)
- Configure environment-specific properties
- Implement distributed logging
- Use cloud storage services
- Set up monitoring and alerting

## 15. How do you secure a Java Spring Boot application?

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
## 16. If we don’t write getters & setters, which annotation can we use?

If we don’t want to manually write **getters and setters** in Java, we can use **Lombok’s `@Getter` and `@Setter` annotations** on the class or fields.

Alternatively, `@Data` generates **getters, setters, `toString()`, `equals()`, and `hashCode()`** all at once.


## 17. Why do we use Long in JpaRepository<Employee, Long>?
In **`JpaRepository<Employee, Long>`**, the **first type (`Employee`)** is the **entity class** the repository manages, and the **second type (`Long`)** is the **type of the entity’s primary key (`@Id`)**.

Using `Long` tells Spring Data JPA what type of value to expect when performing operations like `findById()`, `deleteById()`, or `save()`.


## 18. If a table has 100+ fields and performance is slow, how do you fetch only required 3–4 fields?

If a table has many fields but you only need a few, fetching all columns can **slow down performance**. To optimize, you can:

1. **Use JPQL or native queries** to select only the required fields:

   ```java
   @Query("SELECT e.name, e.salary FROM Employee e WHERE e.id = :id")
   Object[] findNameAndSalary(@Param("id") Long id);
   ```

2. **Use projections** with interfaces or DTOs:

   ```java
   public interface EmployeeView {
       String getName();
       Double getSalary();
   }

   List<EmployeeView> findByDepartment(String dept);
   ```

3. **Avoid `findAll()`** and fetch only what you need using `select` or DTO mapping.



# 19. RESTful Services 

## 1. What is CORS, and how does it work?

CORS stands for Cross-Origin Resource Sharing. It's a browser security feature that blocks requests from one domain to another by default. CORS allows servers to specify which domains can access their resources by sending special HTTP headers. This is common when your frontend runs on localhost:3000 and backend on localhost:8080.

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

## 1. What are RESTful web services?

RESTful web services are web services that follow REST architectural principles. They use HTTP methods to perform operations on resources identified by URLs, providing a stateless and scalable approach to web communication.

- Based on REST architectural style
- Use HTTP methods for operations
- Resources identified by URLs
- Stateless communication
- Platform and language independent
- JSON/XML data exchange

RESTful services provide a simple, standardized way for different systems to communicate over the web using standard HTTP protocols.

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

## 3. What are HTTP methods and their usage?

HTTP methods define the type of operation to be performed on a resource. Each method has a specific purpose and semantic meaning in RESTful services.

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

# 20. Java Microservices 

## 1. What is CORS, and how does it work?

CORS stands for Cross-Origin Resource Sharing. It's a browser security feature that blocks requests from one domain to another by default. CORS allows servers to specify which domains can access their resources by sending special HTTP headers. This is common when your frontend runs on localhost:3000 and backend on localhost:8080.

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

## 1. What are microservices?

Microservices is an architectural approach where applications are built as a collection of small, independent services that communicate over well-defined APIs. Each service is responsible for a specific business function.

- Small, independent services
- Single business responsibility
- Communicate via APIs (usually HTTP/REST)
- Independently deployable
- Technology agnostic
- Owned by small teams

Microservices break down monolithic applications into smaller, manageable pieces that can be developed, deployed, and scaled independently.

## 2. Monolithic vs Microservices Architecture

**Monolithic architecture** is where the entire application is built as a **single unit**. All components like UI, business logic, and database access are tightly coupled and deployed together. It’s simple to develop and test initially, but as the application grows, scaling and maintenance become difficult.

**Microservices architecture** breaks the application into **small, independent services**, each responsible for a specific business function. These services can be developed, deployed, and scaled independently, which improves flexibility and scalability but adds complexity in communication, monitoring, and deployment.

So, **monolithic is simple but less scalable**, while **microservices are scalable and flexible but more complex**.


Trade-offs:
- Monolithic: Simple deployment, shared database, single technology stack
- Microservices: Independent scaling, technology diversity, complex deployment
- Choose monolithic for small teams, microservices for large organizations

## 2. What are the advantages of microservices?

Microservices offer several benefits over monolithic architectures, particularly for large, complex applications and organizations.

**Key Advantages:**
- **Independent deployment:** Deploy services separately
- **Technology diversity:** Different tech stacks per service
- **Scalability:** Scale individual services based on demand
- **Fault isolation:** Failure in one service doesn't crash entire system
- **Team autonomy:** Small teams own complete services
- **Faster development:** Parallel development of services

These benefits enable organizations to move faster, scale better, and maintain more resilient systems.

## 3. What are the challenges of microservices?

While microservices offer many benefits, they also introduce complexity and challenges that must be carefully managed.

**Major Challenges:**
- **Distributed system complexity:** Network calls, latency, failures
- **Data consistency:** Managing transactions across services
- **Service communication:** Inter-service communication overhead
- **Monitoring and debugging:** Tracing requests across services
- **Deployment complexity:** Managing multiple services
- **Testing challenges:** Integration and end-to-end testing

Organizations need proper tooling, processes, and expertise to handle these challenges effectively.

## 3. How did microservices communicate with each other?**

**Spoken Answer:**

> In our system, microservices mainly communicated using **REST APIs over HTTP**.
> For synchronous communication, we used **Feign Client** with service discovery through **Eureka**.
>
> For asynchronous communication, especially for event-based workflows, we used **Kafka**. This helped us reduce tight coupling and improve scalability.

**Example Code (Feign Client):**

```java
@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/{orderId}")
    PaymentResponse getPayment(@PathVariable Long orderId);
}
```

## 4. How do you Handle Failures in Microservices?

Handle failures using circuit breakers to prevent cascading failures, retry mechanisms with exponential backoff, and always have fallback responses. Set timeouts so requests don't hang forever, and implement health checks for monitoring. The key is failing fast and gracefully.

```java
@Component
public class UserServiceClient {
    
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    @Retry(name = "user-service")
    public User getUser(Long id) {
        return restTemplate.getForObject("/users/" + id, User.class);
    }
    
    public User fallbackUser(Long id, Exception ex) {
        return new User(id, "Unknown User", "unknown@example.com");
    }
}
```

Configuration includes failure rate thresholds, wait durations, and retry attempts to control when circuits open and close.

## 5. How do you Handle Exception Handling in Spring Boot?

Use @ControllerAdvice for global exception handling across all controllers. Create custom exception classes for different error scenarios and return proper HTTP status codes with meaningful error messages. Always log exceptions for debugging but don't expose internal details to users.

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

## 6. What is Event-Driven Architecture in Java?

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

## 5. What is API Gateway?

The **Circuit Breaker pattern** is a design pattern used in **microservices** to prevent cascading failures. When a service repeatedly fails or becomes slow, the circuit breaker **opens** and temporarily blocks calls to that service.

After a cooldown period, it moves to a **half-open** state to test if the service has recovered. If successful, the circuit closes and normal traffic resumes. This improves **system resilience and stability**.

**Key Functions:**
- **Request routing:** Direct requests to correct services
- **Authentication and authorization:** Centralized security
- **Rate limiting:** Control request rates
- **Load balancing:** Distribute requests across instances
- **Request/response transformation:** Modify data formats
- **Monitoring and analytics:** Track API usage

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

## 6. What is circuit breaker pattern?

Circuit breaker pattern prevents cascading failures in microservices by monitoring service calls and "opening the circuit" when failures exceed a threshold, allowing the system to fail fast and recover gracefully.

**Circuit States:**
- **Closed:** Normal operation, requests pass through
- **Open:** Failures detected, requests fail immediately
- **Half-Open:** Testing if service has recovered

**Benefits:**
- Prevents cascading failures
- Faster failure detection
- Automatic recovery
- Improves system resilience

**Popular Implementations:**
- Netflix Hystrix (deprecated)
- Resilience4j
- Spring Cloud Circuit Breaker

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

## 6. How do you Improve Performance in Spring Boot Application?

To improve performance in a **Spring Boot application**, start by **optimizing database access** using proper indexing, pagination, and connection pooling like **HikariCP**. Reduce unnecessary queries and use **caching** with tools like Redis or Spring Cache.

Enable **asynchronous processing** where applicable, and tune **JVM settings** such as heap size and garbage collection. Also, use **lazy loading**, compress responses, and monitor the application using **Actuator and profiling tools** to identify bottlenecks.

Overall, focus on **efficient DB usage, caching, async processing, and monitoring** for best performance.


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

Additional optimizations:
- Use connection pooling (HikariCP)
- Enable HTTP compression
- Optimize database queries
- Use CDN for static content
- Monitor with APM tools

## 7. Have you worked with the Java 11 HTTP Client? How does it differ from the HTTP clients used in earlier Java versions?

Yes, I’ve worked with the **Java 11 HTTP Client**. It’s a modern HTTP client introduced in Java 11 under the `java.net.http` package. It supports **HTTP/1.1 and HTTP/2**, has **built-in asynchronous and non-blocking calls** using `CompletableFuture`, and provides a clean, fluent API.

In earlier Java versions, developers typically used **`HttpURLConnection`**, which was **blocking, verbose, and hard to use**, or relied on **third-party libraries** like Apache HttpClient or OkHttp for advanced features.

So compared to older clients, the Java 11 HTTP Client is **simpler, more efficient, async-friendly, and officially supported by the JDK**.


```java
// Java 11 HTTP Client - modern approach
HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .connectTimeout(Duration.ofSeconds(10))
    .build();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .GET()
    .build();

// Synchronous
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

// Asynchronous
CompletableFuture<HttpResponse<String>> futureResponse = 
    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
```

The old HttpURLConnection required much more boilerplate code and didn't support modern features like HTTP/2 or reactive programming patterns.

## 4. What is service discovery?

Service discovery is a mechanism that allows services to find and communicate with each other dynamically without hardcoding network locations. It's essential in microservices architectures.

**How it works:**
- Services register themselves with discovery server
- Services query discovery server to find other services
- Handles dynamic IP addresses and scaling
- Provides health checking and load balancing

**Popular Tools:**
- Netflix Eureka
- Consul
- Kubernetes DNS
- AWS Service Discovery

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

# 21. Java Performance Tuning 

## 1. How do you identify performance bottlenecks?

To identify **performance bottlenecks**, I start by **monitoring application metrics** like response time, CPU, memory, and thread usage using tools such as **Spring Boot Actuator, logs, and APM tools**.

Then I **profile the application** to find slow methods, analyze **database queries** for delays, and check for issues like **high GC time, thread blocking, or connection pool exhaustion**. Based on the data, I focus on the component causing the maximum delay and optimize it.

In short, I rely on **metrics, profiling, and logs** to pinpoint bottlenecks accurately.


**Identification Methods:**
- **Application Performance Monitoring (APM):** Tools like New Relic, AppDynamics
- **Profiling tools:** JProfiler, VisualVM, YourKit
- **JVM monitoring:** JConsole, JVisualVM
- **Database monitoring:** Query execution times
- **Log analysis:** Response times and error patterns
- **Load testing:** Identify limits under stress

Start with high-level metrics, then drill down to specific components causing delays.

## 2. What are common performance issues in Java applications?

Java applications face several typical performance problems that can significantly impact user experience and system efficiency.

**Common Issues:**
- **Memory leaks:** Objects not garbage collected
- **Inefficient database queries:** N+1 queries, missing indexes
- **Poor caching strategy:** Repeated expensive operations
- **Blocking I/O operations:** Synchronous file/network calls
- **Inefficient algorithms:** O(n²) instead of O(n log n)
- **Excessive object creation:** Unnecessary garbage collection pressure
- **Thread contention:** Synchronized blocks causing bottlenecks

```java
// Performance anti-patterns
// 1. String concatenation in loops
String result = "";
for (int i = 0; i < 1000; i++) {
    result += "text"; // Creates new String objects
}

// Better approach
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("text");
}
```

## 3. What is connection pooling and how it works?

**Connection pooling** is a technique where a set of **pre-created database connections** is maintained and reused instead of opening a new connection for every request. Creating a database connection is expensive, so reusing them improves performance.

When the application needs a connection, it **borrows one from the pool**. After the operation is complete, the connection is **returned to the pool** instead of being closed. The pool also manages limits, idle connections, and timeouts.

So, connection pooling **reduces latency, improves scalability, and efficiently manages database resources**.


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
// HikariCP configuration
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);        // Max connections
config.setMinimumIdle(5);             // Min idle connections
config.setConnectionTimeout(30000);   // 30 seconds timeout

HikariDataSource dataSource = new HikariDataSource(config);
```

## 4. What is caching and when should you use it?

Caching stores frequently accessed data in fast storage to reduce expensive operations like database queries, API calls, or complex calculations.

**When to use caching:**
- **Expensive operations:** Database queries, API calls
- **Frequently accessed data:** User profiles, configuration
- **Rarely changing data:** Reference data, lookup tables
- **Computed results:** Complex calculations, reports

**Caching Levels:**
- **Application level:** In-memory caches (Caffeine, Guava)
- **Database level:** Query result caching
- **Distributed level:** Redis, Hazelcast
- **HTTP level:** Browser and CDN caching

```java
// Spring Boot caching example
@Service
public class UserService {
    
    @Cacheable("users")
    public User getUserById(Long id) {
        // Expensive database operation
        return userRepository.findById(id);
    }
    
    @CacheEvict("users")
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
```

## 5. What are important JVM parameters?

JVM parameters control memory allocation, garbage collection, and runtime behavior. Proper tuning can significantly improve application performance.

**Memory Parameters:**
- **-Xms:** Initial heap size
- **-Xmx:** Maximum heap size
- **-XX:NewRatio:** Ratio of old/young generation
- **-XX:MaxMetaspaceSize:** Metaspace limit

**Garbage Collection:**
- **-XX:+UseG1GC:** Use G1 garbage collector
- **-XX:MaxGCPauseMillis:** Target pause time
- **-XX:+PrintGCDetails:** GC logging

**Performance:**
- **-server:** Server mode JIT compilation
- **-XX:+TieredCompilation:** Multi-level compilation

```bash
# Example JVM parameters for production
java -Xms2g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+PrintGCDetails \
     -XX:+PrintGCTimeStamps \
     -jar myapp.jar
```

## 6. How do you tune heap size?

Heap size tuning involves setting appropriate initial and maximum heap sizes based on application memory requirements and available system resources.

**Tuning Guidelines:**
- **Start conservative:** Begin with smaller heap, monitor usage
- **Monitor GC frequency:** Too small = frequent GC, too large = long pauses
- **Leave system memory:** Don't allocate all available RAM
- **Consider GC overhead:** Aim for <5% time in GC
- **Use monitoring tools:** Track heap utilization patterns

**Best Practices:**
- Set -Xms and -Xmx to same value in production
- Allocate 25-50% of system memory to heap
- Monitor actual usage before increasing

```bash
# Heap size examples
# Small application
java -Xms512m -Xmx1g MyApp

# Large application  
java -Xms4g -Xmx8g MyApp

# Monitor heap usage
jstat -gc <pid> 5s  # GC stats every 5 seconds
```

## 7. What is the difference between -Xms and -Xmx?

**-Xms (Initial Heap Size):**
- Sets starting heap size when JVM starts
- Minimum heap allocation
- JVM allocates this memory immediately

**-Xmx (Maximum Heap Size):**
- Sets maximum heap size JVM can use
- Upper limit for heap growth
- JVM can expand heap up to this limit

```bash
# Different initial and max heap
java -Xms1g -Xmx4g MyApp  # Start with 1GB, can grow to 4GB

# Same initial and max heap (recommended for production)
java -Xms2g -Xmx2g MyApp   # Fixed 2GB heap, no expansion overhead
```

Setting them equal in production eliminates heap expansion overhead and provides predictable memory usage.

## 8. How do you analyze heap dumps?

Heap dumps are snapshots of JVM memory that help identify memory leaks, analyze object usage, and understand memory allocation patterns.

**Analysis Tools:**
- **Eclipse MAT (Memory Analyzer Tool):** Most popular
- **VisualVM:** Built-in heap dump analyzer
- **JProfiler:** Commercial profiler
- **jhat:** Command-line heap analyzer (deprecated)

**Analysis Steps:**
1. **Generate heap dump:** jcmd, jmap, or automatic on OutOfMemoryError
2. **Load in analyzer:** Open dump file in MAT or VisualVM
3. **Find memory leaks:** Look for objects with unexpected retention
4. **Analyze object references:** Trace why objects aren't garbage collected

```bash
# Generate heap dump
jcmd <pid> GC.run_finalization
jcmd <pid> VM.gc
jmap -dump:format=b,file=heapdump.hprof <pid>

# Automatic heap dump on OOM
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/tmp/heapdump.hprof \
     MyApp
```

## 9. What is JIT compilation?

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

# 22. Modern Java Features 

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
interface Drawable {
    void draw();
    default void print() { System.out.println("Printing..."); }
}
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

# 23. Cloud and Containerization 

## 1. What is containerization?

**Containerization** is a technique where an application and all its dependencies—like libraries and configuration—are packaged together into a **lightweight container**. This ensures the application runs **consistently across different environments**, such as development, testing, and production.

Containers are fast to start, use fewer resources than virtual machines, and make applications easier to **deploy, scale, and manage**.


- **Lightweight virtualization:** Shares OS kernel, unlike VMs
- **Application packaging:** Bundles code, runtime, libraries, dependencies
- **Environment consistency:** Same behavior across dev, test, production
- **Resource efficiency:** Lower overhead than virtual machines
- **Portability:** Run anywhere containers are supported

Containers solve the "it works on my machine" problem by ensuring consistent runtime environments.

## 2. What is Docker?

**Docker** is a **containerization platform** that allows developers to package an application along with its dependencies into a **container**. This container can run the same way across different environments like development, testing, and production.

Docker makes applications **lightweight, portable, fast to deploy**, and easier to scale compared to traditional virtual machines.


- **Container platform:** Create, deploy, and manage containers
- **Docker images:** Read-only templates for creating containers
- **Docker containers:** Running instances of images
- **Dockerfile:** Text file with instructions to build images
- **Docker Hub:** Cloud-based registry for sharing images

```dockerfile
# Dockerfile example
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

```bash
# Docker commands
docker build -t myapp .              # Build image
docker run -p 8080:8080 myapp        # Run container
docker ps                            # List running containers
docker images                        # List images
```

## 3. What is Kubernetes?

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

## 4. What is cloud computing?

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

## 5. What is distributed system?

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

## 6. What is load balancing?

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

```nginx
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

## 7. What are caching strategies?

**Caching strategies in Java** define how data is temporarily stored to reduce repeated computation or database calls and improve performance.

The most common strategies are **Cache-Aside**, where the application checks the cache first and loads data from the database if missing; **Read-Through**, where the cache automatically loads data on a miss; **Write-Through**, where data is written to both cache and database at the same time; and **Write-Behind**, where cache writes to the database asynchronously.

In Java, these strategies are commonly implemented using tools like **Spring Cache, Ehcache, Caffeine, or Redis**, depending on performance and scalability needs.


**Common Caching Strategies:**

**Cache-Aside (Lazy Loading):**
**Write-Through:**
**Write-Behind (Write-Back):**
**Refresh-Ahead:**

```java
// Cache-aside pattern example
@Service
public class UserService {
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    public User getUser(Long id) {
        String key = "user:" + id;
        
        // Try cache first
        User user = redisTemplate.opsForValue().get(key);
        if (user != null) {
            return user; // Cache hit
        }
        
        // Cache miss - load from database
        user = userRepository.findById(id);
        if (user != null) {
            // Store in cache for future requests
            redisTemplate.opsForValue().set(key, user, Duration.ofMinutes(30));
        }
        
        return user;
    }
}
```

**Cache Levels:**
- **Browser Cache:** Client-side caching
- **CDN Cache:** Geographic distribution
- **Application Cache:** In-memory caching (Redis, Memcached)
- **Database Cache:** Query result caching

### 24. Miscellaneous

## 2. What are the main features of an eCommerce application?

An eCommerce application needs core features like product catalog for browsing items, shopping cart for collecting purchases, user management for accounts and authentication, order processing for checkout and payment, and inventory management for stock tracking.

Additional features include:
- Search and filtering capabilities
- Reviews and ratings system
- Admin panel for management
- Payment gateway integration
- Order tracking and notifications

## 3. Explain the flowchart of an eCommerce application (frontend and backend).

The flow starts with users browsing products on the frontend, adding items to cart, then proceeding to checkout. The frontend sends requests to an API gateway which routes them to appropriate backend services. These services handle business logic, interact with databases, process payments, and send responses back to the frontend.

```
User → Frontend → API Gateway → Backend Services → Database
                                ├── Product Service
                                ├── Cart Service  
                                ├── Order Service
                                └── Payment Service
```

## 4. What are the components and tools used in the backend of an eCommerce application?

Backend components include Spring Boot for REST APIs, PostgreSQL or MySQL for transactional data, Redis for caching, message queues like RabbitMQ for async processing, and Elasticsearch for product search.

Infrastructure tools:
- Docker for containerization
- Kubernetes for orchestration
- AWS S3 for file storage
- Monitoring tools like Prometheus

```java
@RestController
public class ProductController {
    @GetMapping("/api/products")
    public Page<Product> getProducts(@RequestParam String category) {
        return productService.getProducts(category);
    }
}
```

## 5. Explain the Git workflow used in an eCommerce application.

We use GitFlow with main branch for production, develop branch for integration, and feature branches for new functionality. Developers create feature branches from develop, work on features, then create pull requests for code review before merging back to develop.

```bash
git checkout develop
git checkout -b feature/shopping-cart
# Make changes
git commit -m "Add shopping cart"
git push origin feature/shopping-cart
# Create pull request
```

Release branches prepare for deployment, and hotfix branches handle urgent production fixes.

## 7. Can you write the business logic for a CRUD service in Java?

A CRUD service handles Create, Read, Update, Delete operations with proper validation and error handling. I'll use JPA repository for database operations and add business logic for validation.

```java
@Service
public class ProductService {
    
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }
    
    public Product getProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
    
    public Product updateProduct(Long id, Product details) {
        Product product = getProduct(id);
        product.setName(details.getName());
        product.setPrice(details.getPrice());
        validateProduct(product);
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    private void validateProduct(Product product) {
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
```

## 8. How do you migrate a Java application from a lower version to a higher version?

Migration involves analyzing current code for compatibility issues, updating build configuration like Maven or Gradle, replacing deprecated APIs with newer alternatives, updating third-party dependencies, and thorough testing before production deployment.

Key steps:
- Update Java runtime and build tools
- Replace deprecated APIs
- Update dependency versions
- Fix compilation errors
- Test thoroughly in staging environment

```java
// Before (Java 8)
List<String> names = Arrays.asList("John", "Jane");
List<String> result = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// After (Java 17)
var names = List.of("John", "Jane");
var result = names.stream()
    .map(String::toUpperCase)
    .toList();
```