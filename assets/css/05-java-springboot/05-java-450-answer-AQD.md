# Java Interview Questions - Comprehensive Guide

# Java Fundamentals 

## Core Java Basics

### 1. What is the difference between JDK and JRE?

**Answer:**
* JDK is the development kit - includes compiler, debugger, and development tools
* JRE is the runtime environment - just runs Java applications
* JDK = JRE + Development Tools
* Think of JRE as a player, JDK as a player + recorder

```java
// You need JDK to compile this
javac HelloWorld.java

// You need JRE to run this
java HelloWorld
```

### 2. What is Java Virtual Machine (JVM)?

**Answer:**
* JVM is the runtime engine that executes Java bytecode
* It's platform-specific but makes Java platform-independent
* Converts bytecode to machine code
* Handles memory management and garbage collection

```java
// Java code → Bytecode → JVM → Machine code
public class Demo {
    public static void main(String[] args) {
        System.out.println("JVM executes this");
    }
}
```

### 3. What are the different types of memory areas allocated by JVM?

**Answer:**
* **Heap Memory**: Objects and instance variables
* **Stack Memory**: Method calls and local variables
* **Method Area**: Class-level data, static variables
* **PC Register**: Current executing instruction
* **Native Method Stack**: Native method calls

```java
public class MemoryDemo {
    static int classVar = 10;        // Method Area
    
    public void method() {
        int localVar = 20;           // Stack
        String obj = new String();   // Heap
    }
}
```

### 4. What is JIT Compiler?

**Answer:**
* Just-In-Time compiler optimizes bytecode to machine code at runtime
* Improves performance by compiling frequently used code
* Works behind the scenes automatically
* Makes Java faster than interpreted languages

```java
// This loop will be optimized by JIT
for (int i = 0; i < 1000000; i++) {
    // JIT optimizes this after multiple executions
    result += i * 2;
}
```

### 5. How is Java Platform different from other platforms?

**Answer:**
* Java platform is software-based, not hardware-based
* Includes JVM + Java API libraries
* Platform-independent - same code runs everywhere
* Other platforms are typically OS + hardware specific

```java
// Same code works on Windows, Linux, Mac
public class CrossPlatform {
    public static void main(String[] args) {
        System.out.println("Runs everywhere!");
    }
}
```

### 6. Why do people say that Java is 'Write Once and Run Anywhere' language?

**Answer:**
* Java compiles to bytecode, not machine code
* Bytecode is platform-neutral
* JVM on each platform interprets bytecode
* Same .class file runs on any system with JVM

```java
// Compile once: javac Demo.java → Demo.class
// Run anywhere: java Demo (on any OS)
public class Demo {
    public static void main(String[] args) {
        System.out.println("WORA in action!");
    }
}
```

### 7. How does ClassLoader work in Java?

**Answer:**
* ClassLoader loads .class files into JVM memory
* Three types: Bootstrap, Extension, Application
* Uses delegation model - parent first approach
* Loads classes on-demand (lazy loading)

```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Get current class loader
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("ClassLoader: " + loader);
        
        // Load class dynamically
        Class.forName("java.util.ArrayList");
    }
}
```

### 8. Do you think 'main' used for main method is a keyword in Java?

**Answer:**
* No, 'main' is NOT a keyword in Java
* It's just a method name with special significance
* JVM looks for this specific method signature to start execution
* You can use 'main' as variable name elsewhere

```java
public class MainDemo {
    int main = 5; // 'main' used as variable - perfectly valid
    
    public static void main(String[] args) {
        // This 'main' is special method name, not keyword
        System.out.println("Started");
    }
}
```

### 9. Can we write main method as public void static instead of public static void?

**Answer:**
* No, the order matters in Java
* Access modifier must come first: public
* Then static keyword
* Then return type: void
* Wrong order causes compilation error

```java
// ✅ Correct
public static void main(String[] args) { }

// ❌ Wrong - Compilation error
public void static main(String[] args) { }
```

### 10. What will be the default value of local variables if we do not specify any value?

**Answer:**
* Local variables have NO default values
* Must be initialized before use
* Compilation error if used without initialization
* Only instance and static variables get default values

```java
public class DefaultValues {
    int instanceVar; // Default: 0
    
    public void method() {
        int localVar; // No default value
        // System.out.println(localVar); // Compilation error
        
        localVar = 10; // Must initialize
        System.out.println(localVar); // Now OK
    }
}
```

### 11. What is the difference between byte and char data types in Java?

**Answer:**
* **byte**: 8-bit signed integer (-128 to 127)
* **char**: 16-bit unsigned Unicode character (0 to 65535)
* byte stores numbers, char stores characters
* char can represent any Unicode character

```java
public class ByteCharDemo {
    public static void main(String[] args) {
        byte b = 65;        // Number 65
        char c = 65;        // Character 'A' (ASCII 65)
        char c2 = 'A';      // Same as above
        
        System.out.println(b);  // Prints: 65
        System.out.println(c);  // Prints: A
        System.out.println(c2); // Prints: A
    }
}
```

---

## Object-Oriented Programming (OOP)

### 12. What are the main principles of Object-Oriented Programming (OOP)?

**Answer:**
* **Encapsulation**: Hide internal details, expose through methods
* **Inheritance**: Child class inherits parent properties
* **Polymorphism**: Same method, different behaviors
* **Abstraction**: Hide complexity, show essential features

```java
// Encapsulation
class Account {
    private double balance;
    public void deposit(double amount) { balance += amount; }
}

// Inheritance
class SavingsAccount extends Account { }

// Polymorphism
Account acc = new SavingsAccount();
```

### 13. What is the difference between Object-Oriented Programming language and Object-Based Programming language?

**Answer:**
* **Object-Oriented**: Supports all OOP features (Java, C++)
* **Object-Based**: Has objects but limited OOP features (JavaScript)
* OOP has inheritance, Object-based may not
* OOP follows class-based approach

```java
// Object-Oriented (Java)
class Animal { }
class Dog extends Animal { } // Inheritance supported

// Object-Based (JavaScript-like)
// var obj = { name: "test" }; // Objects without classes
```

### 14. What is the default value of an object reference defined as an instance variable?

**Answer:**
* Default value is **null**
* All object references are initialized to null
* Primitive types have different defaults (0, false, etc.)
* Must check for null before using

```java
class Demo {
    String name;        // Default: null
    int age;           // Default: 0
    boolean active;    // Default: false
    
    public void test() {
        System.out.println(name);    // Prints: null
        System.out.println(age);     // Prints: 0
    }
}
```

### 15. Why do we need a constructor in Java?

**Answer:**
* Initialize object state when created
* Set initial values for instance variables
* Perform setup operations
* Ensure object is in valid state from creation

```java
class Person {
    private String name;
    private int age;
    
    // Constructor initializes object
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

Person p = new Person("John", 25); // Object ready to use
```

### 16. Why do we need a default constructor in Java classes?

**Answer:**
* Provides no-argument way to create objects
* Java creates one automatically if no constructor exists
* Frameworks often need default constructor (Spring, Hibernate)
* Allows flexible object creation

```java
class Student {
    String name = "Unknown";
    
    // Default constructor (implicit if not written)
    public Student() { }
    
    public Student(String name) {
        this.name = name;
    }
}

Student s1 = new Student();      // Uses default
Student s2 = new Student("Tom"); // Uses parameterized
```

### 17. What is the value returned by constructor in Java?

**Answer:**
* Constructors don't return any value
* Not even void - they have no return type
* They implicitly return the created object reference
* Cannot use return statement with value

```java
class Car {
    // ✅ Correct - No return type
    public Car() {
        System.out.println("Car created");
        // return; // OK - but not needed
    }
    
    // ❌ Wrong - Cannot have return type
    // public void Car() { } // This is a method, not constructor
}
```

### 18. Can we inherit a constructor?

**Answer:**
* No, constructors are NOT inherited
* Each class must define its own constructors
* Child class can call parent constructor using super()
* Constructor chaining happens automatically

```java
class Parent {
    public Parent(String name) {
        System.out.println("Parent: " + name);
    }
}

class Child extends Parent {
    public Child() {
        super("Default"); // Must call parent constructor
    }
}
```

### 19. Why constructors cannot be final, static, or abstract in Java?

**Answer:**
* **final**: Constructors can't be overridden (inheritance doesn't apply)
* **static**: Constructors belong to instance creation, not class
* **abstract**: Constructors must have implementation to create objects
* These modifiers contradict constructor purpose

```java
class Demo {
    // ❌ All these are compilation errors
    // public final Demo() { }     // Cannot be final
    // public static Demo() { }    // Cannot be static  
    // public abstract Demo() { }  // Cannot be abstract
    
    // ✅ Correct
    public Demo() { }
}
```

---

## Inheritance and Polymorphism

### 20. What is the purpose of the 'this' keyword in Java?

**Answer:**
* Refers to current object instance
* Differentiates between instance variables and parameters
* Calls other constructors in same class
* Passes current object as parameter

```java
class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;  // Distinguish parameter from field
    }
    
    public Person() {
        this("Unknown");   // Call other constructor
    }
}
```

### 21. Explain the concept of inheritance?

**Answer:**
* Child class inherits properties and methods from parent
* Promotes code reusability and hierarchy
* Uses 'extends' keyword in Java
* Child can override parent methods

```java
class Animal {
    public void eat() { System.out.println("Eating"); }
}

class Dog extends Animal {
    public void bark() { System.out.println("Barking"); }
}

Dog d = new Dog();
d.eat();  // Inherited method
d.bark(); // Own method
```

### 22. Which class in Java is the superclass of every other class?

**Answer:**
* **Object class** is the root of all classes
* Every class implicitly extends Object
* Provides basic methods like toString(), equals(), hashCode()
* Located in java.lang package

```java
class MyClass { } // Implicitly extends Object

// Same as:
class MyClass extends Object {
    // Inherits toString(), equals(), hashCode(), etc.
}

MyClass obj = new MyClass();
System.out.println(obj.toString()); // From Object class
```

### 23. Why does Java not support multiple inheritance?

**Answer:**
* Avoids **Diamond Problem** - ambiguity in method resolution
* Simplifies language design and reduces complexity
* Uses interfaces for multiple inheritance of type
* Single inheritance keeps hierarchy clear

```java
// Diamond Problem (not allowed in Java)
// class C extends A, B { } // Compilation error

// Solution: Use interfaces
interface A { void method(); }
interface B { void method(); }

class C implements A, B {
    public void method() { } // Single implementation
}
```

### 24. What is meant by composition in OOPS?

**Answer:**
* "Has-a" relationship between classes
* One class contains objects of another class
* Stronger relationship than inheritance
* Promotes code reuse without inheritance hierarchy

```java
class Engine {
    public void start() { System.out.println("Engine started"); }
}

class Car {
    private Engine engine = new Engine(); // Composition
    
    public void startCar() {
        engine.start(); // Using composed object
    }
}
```

### 25. How are aggregation and composition different concepts?

**Answer:**
* **Composition**: Strong "has-a", child can't exist without parent
* **Aggregation**: Weak "has-a", child can exist independently
* Composition: Car-Engine (engine dies with car)
* Aggregation: Department-Employee (employee can change departments)

```java
// Composition - Engine can't exist without Car
class Car {
    private Engine engine = new Engine(); // Dies with car
}

// Aggregation - Student exists independently
class Department {
    private List<Student> students; // Students can transfer
}
```

### 26. What is the purpose of the 'super' keyword in Java?

**Answer:**
* Refers to immediate parent class object
* Calls parent class methods and constructors
* Accesses parent class variables
* Resolves method name conflicts

```java
class Parent {
    String name = "Parent";
    public void display() { System.out.println("Parent"); }
}

class Child extends Parent {
    String name = "Child";
    
    public void show() {
        System.out.println(super.name);  // Parent
        super.display();                 // Parent method
    }
}
```

### 27. Is it possible to use this() and super() both in the same constructor?

**Answer:**
* **No**, cannot use both in same constructor
* Both must be first statement in constructor
* Only one can be first statement
* Choose either constructor chaining or parent call

```java
class Parent {
    public Parent(String name) { }
}

class Child extends Parent {
    public Child() {
        super("Default"); // Must be first
        // this();        // Error - cannot have both
    }
    
    public Child(String name) {
        this(); // Calls other constructor
    }
}
```

### 28. What is the meaning of object cloning in Java?

**Answer:**
* Creating exact copy of an object
* Uses clone() method from Object class
* Implement Cloneable interface
* Shallow vs Deep cloning concepts

```java
class Person implements Cloneable {
    String name;
    int age;
    
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow clone
    }
}

Person p1 = new Person();
Person p2 = (Person) p1.clone(); // Creates copy
```

### 29. What is runtime polymorphism?

**Answer:**
* Method resolution happens at runtime, not compile time
* Achieved through method overriding and inheritance
* Uses dynamic method dispatch
* Same method call, different implementations

```java
class Animal {
    public void sound() { System.out.println("Animal sound"); }
}

class Dog extends Animal {
    public void sound() { System.out.println("Bark"); }
}

Animal a = new Dog(); // Runtime decides which sound()
a.sound(); // Prints: Bark
```

### 30. Is it possible to achieve runtime polymorphism by data members?

**Answer:**
* **No**, runtime polymorphism only works with methods
* Data members are resolved at compile time
* Variable binding is static, not dynamic
* Only method calls use dynamic dispatch

```java
class Parent {
    int x = 10;
}

class Child extends Parent {
    int x = 20;
}

Parent p = new Child();
System.out.println(p.x); // Prints: 10 (Parent's x)
// No polymorphism for variables
```

### 31. What is the difference between static and dynamic binding?

**Answer:**
* **Static Binding**: Method call resolved at compile time
* **Dynamic Binding**: Method call resolved at runtime
* Static: private, final, static methods
* Dynamic: overridden methods in inheritance

```java
class Demo {
    public static void staticMethod() { } // Static binding
    public void instanceMethod() { }      // Dynamic binding
}

class Child extends Demo {
    public void instanceMethod() { }      // Overridden - dynamic
}

Demo d = new Child();
d.instanceMethod(); // Runtime decides which method
```

# Java Method Concepts - Overloading and Overriding

## Method Overloading

### 32. What is the other name of method overloading?
* **Compile-time polymorphism** or **Static polymorphism**
* Also called **Early binding**
* Method resolution happens at compile time

### 33. How will you implement method overloading in Java?
* Define multiple methods with **same name** but **different parameters**
* Parameters must differ in number, type, or order

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
    double add(double a, double b) { return a + b; }
}
```

### 34. What kinds of argument variations are allowed in method overloading?
* **Number of parameters**: `method(int a)` vs `method(int a, int b)`
* **Type of parameters**: `method(int a)` vs `method(double a)`
* **Order of parameters**: `method(int a, String b)` vs `method(String a, int b)`

```java
void print(int x) { }
void print(String s) { }
void print(int x, String s) { }
void print(String s, int x) { }
```

### 35. Why is it not possible to do method overloading by changing the return type of method?
* **Return type is not part of method signature**
* Compiler cannot distinguish methods based on return type alone
* Method calls don't always use return values

```java
// This won't compile - ambiguous
int getValue() { return 1; }
String getValue() { return "hello"; } // Error!
```

### 36. Is it allowed to overload main() method in Java?
* **Yes, you can overload main() method**
* JVM only calls `public static void main(String[] args)`
* Other overloaded versions are regular methods

```java
public class Test {
    public static void main(String[] args) { } // JVM entry point
    public static void main(int x) { }         // Overloaded
    public static void main() { }              // Overloaded
}
```

## Method Overriding

### 37. How do we implement method overriding in Java?
* **Inherit from parent class**
* **Redefine method** with same signature in child class
* Use `@Override` annotation for safety

```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    @Override
    void sound() { System.out.println("Bark"); }
}
```

### 38. Are we allowed to override a static method in Java?
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

### 39. Why Java does not allow overriding a static method?
* **Static methods belong to class, not instance**
* **No dynamic binding** for static methods
* **Resolved at compile time** based on reference type
* Overriding requires runtime polymorphism

### 40. Is it allowed to override an overloaded method?
* **Yes, you can override any overloaded method**
* Each overloaded method can be overridden independently
* Must match exact signature to override

```java
class Parent {
    void method(int x) { }
    void method(String s) { }
}
class Child extends Parent {
    @Override
    void method(int x) { }      // Overriding first method
    @Override
    void method(String s) { }   // Overriding second method
}
```

### 41. What is the difference between method overloading and method overriding?

| **Overloading** | **Overriding** |
|-----------------|----------------|
| Same class | Parent-child classes |
| Different parameters | Same signature |
| Compile-time | Runtime |
| Static polymorphism | Dynamic polymorphism |

```java
// Overloading
class Math {
    int add(int a, int b) { }
    double add(double a, double b) { }
}

// Overriding
class Animal { void move() { } }
class Bird extends Animal { 
    @Override void move() { } 
}
```

### 42. Does Java allow virtual functions?
* **All non-static, non-final, non-private methods are virtual by default**
* No explicit `virtual` keyword like C++
* **Dynamic method dispatch** happens automatically

```java
Animal animal = new Dog();
animal.sound(); // Calls Dog's sound() method - virtual behavior
```

### 43. What is meant by covariant return type in Java?
* **Subclass can return more specific type** than parent method
* Return type must be **subclass of original return type**
* Available since Java 5

```java
class Animal { }
class Dog extends Animal { }

class AnimalFactory {
    Animal getAnimal() { return new Animal(); }
}
class DogFactory extends AnimalFactory {
    @Override
    Dog getAnimal() { return new Dog(); } // Covariant return
}
```

---

## Key Takeaways
* **Overloading**: Same name, different parameters, compile-time
* **Overriding**: Same signature, parent-child, runtime
* **Static methods**: Cannot be overridden, only hidden
* **Virtual methods**: All instance methods are virtual in Java
* **Covariant returns**: Subclass can return more specific type

# Java Static and Final Keywords

## Static Concepts

### 44. Why do we use static variables in Java?
* **Shared among all instances** of a class
* **Memory efficient** - only one copy exists
* **Class-level data** that doesn't change per object
* **Common configuration** or constants

```java
class Student {
    static String schoolName = "ABC School"; // Shared by all students
    String name; // Individual to each student
}
```

### 45. Why is it not a good practice to create static variables in Java?
* **Memory leaks** - static variables live until program ends
* **Thread safety issues** - shared state causes concurrency problems
* **Testing difficulties** - state persists between test cases
* **Tight coupling** - reduces flexibility and modularity

```java
class Counter {
    static int count = 0; // Problem: shared state, hard to test
    
    void increment() {
        count++; // Not thread-safe
    }
}
```

### 46. What is the purpose of static method in Java?
* **Called without creating object instance**
* **Utility functions** that don't need object state
* **Class-level operations** independent of instance data
* **Cannot access instance variables** directly

```java
class MathUtils {
    static int add(int a, int b) {
        return a + b; // No object needed
    }
}
// Usage: MathUtils.add(5, 3);
```

### 47. Why do we mark the main method as static in Java?
* **JVM needs to call main() without creating object**
* **Entry point** must be accessible before any object creation
* **No instance required** to start program execution
* **Standard convention** for program initialization

```java
public class App {
    public static void main(String[] args) {
        // JVM calls this without creating App object
        System.out.println("Program starts");
    }
}
```

### 48. In what scenario do we use a static block?
* **Initialize static variables** with complex logic
* **One-time setup** when class is first loaded
* **Load configuration** or resources at class loading
* **Executes before main()** and constructor

```java
class DatabaseConfig {
    static String url;
    
    static {
        // Complex initialization logic
        url = loadConfigFromFile();
        System.out.println("DB Config loaded");
    }
    
    static String loadConfigFromFile() {
        return "jdbc:mysql://localhost:3306/db";
    }
}
```

### 49. Is it possible to execute a program without defining a main() method?
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

### 50. What is the difference between static method and instance method in Java?

| **Static Method** | **Instance Method** |
|-------------------|---------------------|
| Called on class | Called on object |
| No `this` reference | Has `this` reference |
| Cannot access instance variables | Can access all variables |
| Memory allocated at class loading | Memory allocated per object |

```java
class Example {
    int instanceVar = 10;
    static int staticVar = 20;
    
    // Static method
    static void staticMethod() {
        System.out.println(staticVar); // OK
        // System.out.println(instanceVar); // Error!
    }
    
    // Instance method
    void instanceMethod() {
        System.out.println(instanceVar); // OK
        System.out.println(staticVar);   // OK
    }
}

// Usage:
Example.staticMethod();        // Called on class
Example obj = new Example();
obj.instanceMethod();          // Called on object
```

## Final Keyword

### 51. How can you change the value of a final variable in Java?
* **You cannot change final primitive values** once assigned
* **Final object references cannot be changed** but object content can be modified
* **Use reflection** (not recommended) to forcefully change
* **Final arrays/collections** - reference fixed, elements can change

```java
final int x = 10;
// x = 20; // Error! Cannot change

final List<String> list = new ArrayList<>();
list.add("Hello"); // OK - modifying content
// list = new ArrayList<>(); // Error! Cannot change reference
```

### 52. Can a class be marked final in Java?
* **Yes, classes can be marked final**
* **Final classes cannot be extended**
* **Examples**: String, Integer, all wrapper classes
* **Used for security and immutability**

```java
final class MyClass {
    void method() { }
}

// class Child extends MyClass { } // Error! Cannot extend final class
```

### 53. How can we create a final method in Java?
* **Add final keyword before method declaration**
* **Final methods cannot be overridden** in subclasses
* **Can be inherited** but not modified
* **Used to preserve critical functionality**

```java
class Parent {
    final void criticalMethod() {
        System.out.println("Cannot be overridden");
    }
}

class Child extends Parent {
    // void criticalMethod() { } // Error! Cannot override final method
}
```

### 54. How can we prohibit inheritance in Java?
* **Mark class as final** - most common approach
* **Make constructor private** - prevents direct inheritance
* **Use composition instead of inheritance**
* **Static factory methods** for object creation

```java
// Method 1: Final class
final class NoInheritance { }

// Method 2: Private constructor
class Singleton {
    private Singleton() { }
    static Singleton getInstance() { return new Singleton(); }
}
```

### 55. Why is the Integer class final in Java?
* **Security reasons** - prevents malicious subclassing
* **Immutability guarantee** - ensures Integer objects don't change
* **Performance optimization** - JVM can make assumptions
* **API consistency** - prevents breaking wrapper class behavior

```java
final class Integer { // Cannot extend
    private final int value; // Immutable
    
    public Integer(int value) {
        this.value = value;
    }
}
```

### 56. What is a blank final variable in Java?
* **Final variable declared without initialization**
* **Must be initialized before use**
* **Can be initialized in constructor or instance block**
* **Each instance can have different values**

```java
class Student {
    final String name; // Blank final variable
    
    Student(String name) {
        this.name = name; // Must initialize in constructor
    }
}
```

### 57. How can we initialize a blank final variable?
* **In constructor** - most common approach
* **In instance initialization block**
* **At declaration time** (then it's not blank)
* **Must be initialized before object creation completes**

```java
class Example {
    final int value1; // Blank final
    final int value2; // Blank final
    
    // Method 1: Constructor initialization
    Example(int val) {
        this.value1 = val;
        this.value2 = val * 2;
    }
    
    // Method 2: Instance block initialization
    {
        // value2 = 100; // Alternative initialization
    }
}
```

### 58. Is it allowed to declare the main method as final?
* **Yes, main method can be declared final**
* **No impact on program execution**
* **Cannot be overridden** in subclasses
* **JVM still calls it normally**

```java
class MainExample {
    public static final void main(String[] args) {
        System.out.println("Final main method works fine");
    }
}

class Child extends MainExample {
    // public static void main(String[] args) { } // Error if trying to override
}
```

# Java Abstract Classes and Interfaces

## Abstraction Concepts

### 59. What is abstraction in Object-Oriented Programming?
* **Hiding implementation details** and showing only essential features
* **Focus on what an object does** rather than how it does it
* **Achieved through abstract classes and interfaces**
* **Reduces complexity** by providing simplified interface

```java
// Abstract concept - we know what a car does, not how engine works
abstract class Car {
    abstract void start(); // What it does
    abstract void stop();  // Implementation hidden
}

class Honda extends Car {
    void start() { /* Complex engine logic hidden */ }
    void stop() { /* Brake system details hidden */ }
}
```

### 60. How is abstraction different from encapsulation?
* **Abstraction**: Hides complexity, shows only necessary features
* **Encapsulation**: Bundles data and methods, controls access
* **Abstraction**: Design level concept (what to show)
* **Encapsulation**: Implementation level concept (how to hide)

```java
// Encapsulation - data hiding with access control
class BankAccount {
    private double balance; // Hidden data
    
    public void deposit(double amount) { // Controlled access
        balance += amount;
    }
}

// Abstraction - hiding implementation complexity
abstract class PaymentProcessor {
    abstract void processPayment(); // What, not how
}
```

### 61. What is an abstract class in Java?
* **Cannot be instantiated directly**
* **Can have abstract and concrete methods**
* **Used as base class** for other classes
* **Provides partial implementation** and common functionality

```java
abstract class Animal {
    String name; // Concrete field
    
    void sleep() { // Concrete method
        System.out.println("Sleeping...");
    }
    
    abstract void makeSound(); // Abstract method - must implement
}

class Dog extends Animal {
    void makeSound() { // Must implement
        System.out.println("Bark");
    }
}
```

### 62. Is it allowed to mark a method abstract without marking the class abstract?
* **No, it's not allowed**
* **Class must be abstract** if it contains abstract methods
* **Compilation error** if class is not marked abstract
* **Abstract methods need abstract class** to exist

```java
// This will cause compilation error
class RegularClass {
    abstract void method(); // Error! Class must be abstract
}

// Correct way
abstract class AbstractClass {
    abstract void method(); // OK - class is abstract
}
```

### 63. Is it allowed to mark a method abstract as well as final?
* **No, it's contradictory and not allowed**
* **Abstract methods must be overridden** in subclasses
* **Final methods cannot be overridden**
* **Compilation error** if both keywords used together

```java
abstract class Example {
    // abstract final void method(); // Error! Cannot be both
    
    abstract void abstractMethod();   // OK - must override
    final void finalMethod() { }      // OK - cannot override
}
```

### 64. Can we instantiate an abstract class in Java?
* **No, cannot create objects** of abstract class directly
* **Can create reference variables** of abstract class type
* **Must instantiate concrete subclass**
* **Anonymous inner classes** are possible exception

```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() { System.out.println("Drawing circle"); }
}

// Usage:
// Shape shape = new Shape(); // Error! Cannot instantiate

Shape shape = new Circle(); // OK - concrete subclass
shape.draw();

// Anonymous class (special case)
Shape anonymous = new Shape() {
    void draw() { System.out.println("Anonymous shape"); }
};
```

## Interface Fundamentals

### 65. What is an interface in Java?
* **Contract that defines what a class must do**
* **All methods are public and abstract by default** (before Java 8)
* **Variables are public, static, and final** (constants)
* **Supports multiple inheritance** unlike classes

```java
interface Drawable {
    int MAX_SIZE = 100; // public static final by default
    
    void draw(); // public abstract by default
    void resize(int size);
}

class Circle implements Drawable {
    public void draw() { System.out.println("Drawing circle"); }
    public void resize(int size) { System.out.println("Resizing to " + size); }
}
```

### 66. Is it allowed to mark an interface method as static?
* **Yes, since Java 8** static methods are allowed in interfaces
* **Static methods must have implementation** in interface
* **Cannot be overridden** by implementing classes
* **Called using interface name**, not object reference

```java
interface MathUtils {
    static int add(int a, int b) { // Static method with body
        return a + b;
    }
    
    void calculate(); // Abstract method
}

class Calculator implements MathUtils {
    public void calculate() { }
}

// Usage:
int result = MathUtils.add(5, 3); // Called on interface
```

### 67. Why can an interface not be marked as final in Java?
* **Final means cannot be extended**
* **Interfaces are meant to be implemented** by classes
* **Contradictory purpose** - interfaces exist to be implemented
* **Would make interface useless** if marked final

```java
// This would be meaningless and cause error
// final interface MyInterface { } // Error! Cannot be final

interface Usable {
    void use();
}

class Tool implements Usable { // This is the purpose of interfaces
    public void use() { }
}
```

### 68. What is a marker interface?
* **Interface with no methods or fields**
* **Used to mark or tag classes** with special behavior
* **JVM or frameworks** treat marked classes differently
* **Examples**: Serializable, Cloneable, Remote

```java
// Marker interface - no methods
interface Serializable { }

class Student implements Serializable { // Marks class as serializable
    String name;
    int age;
}

// JVM knows Student objects can be serialized
```

### 69. What can we use instead of marker interface?
* **Annotations** - modern and preferred approach
* **More flexible** and can carry additional information
* **Better readability** and metadata support
* **Type-safe** and compile-time checking

```java
// Instead of marker interface
interface Auditable { }

// Use annotation
@Retention(RetentionPolicy.RUNTIME)
@interface Auditable {
    String value() default ""; // Can carry data
}

@Auditable("financial")
class BankAccount { }
```

### 70. How are annotations better than marker interfaces?
* **Can carry parameters** and additional metadata
* **Multiple annotations** can be applied to same class
* **Better tool support** and IDE integration
* **Compile-time validation** and processing

```java
// Marker interface - limited
interface Cacheable { }

// Annotation - flexible
@Retention(RetentionPolicy.RUNTIME)
@interface Cacheable {
    int timeout() default 300;
    String region() default "default";
}

@Cacheable(timeout = 600, region = "user-data")
class UserService { }
```

### 71. What is the difference between abstract class and interface in Java?

| **Abstract Class** | **Interface** |
|-------------------|---------------|
| Can have concrete methods | All methods abstract (pre-Java 8) |
| Can have instance variables | Only constants (public static final) |
| Single inheritance | Multiple inheritance |
| Can have constructors | No constructors |
| Any access modifier | Public methods only |

```java
// Abstract class
abstract class Animal {
    String name; // Instance variable
    
    Animal(String name) { this.name = name; } // Constructor
    
    void sleep() { } // Concrete method
    abstract void makeSound(); // Abstract method
}

// Interface
interface Flyable {
    int MAX_SPEED = 200; // Constant only
    
    void fly(); // Abstract method
    
    default void land() { } // Default method (Java 8+)
}
```

### 72. Does Java allow us to use private and protected modifiers for variables in interfaces?
* **No, interface variables are always public static final**
* **Cannot use private or protected** modifiers
* **Compilation error** if you try to use other modifiers
* **All variables are constants** by default

```java
interface Example {
    int PUBLIC_VAR = 10; // public static final by default
    
    // private int x = 5; // Error! Cannot be private
    // protected int y = 6; // Error! Cannot be protected
    
    public static final int EXPLICIT = 20; // Explicit but redundant
}
```

### 73. How can we cast an object reference to an interface reference?
* **Direct assignment** if class implements interface
* **Explicit casting** when needed for type safety
* **instanceof check** recommended before casting
* **ClassCastException** if object doesn't implement interface

```java
interface Drawable {
    void draw();
}

class Circle implements Drawable {
    public void draw() { }
}

// Direct assignment
Circle circle = new Circle();
Drawable drawable = circle; // Implicit casting

// Explicit casting
Object obj = new Circle();
if (obj instanceof Drawable) {
    Drawable d = (Drawable) obj; // Safe explicit cast
    d.draw();
}
```

# Java Package and Import 

## Package Management

### 74. What is the purpose of package in Java?

**Answer:**
* **Namespace management** - Avoids naming conflicts between classes
* **Access control** - Provides package-level access modifiers
* **Code organization** - Groups related classes and interfaces logically
* **Security** - Controls access to classes and members

```java
package com.company.project.utils;
public class StringHelper {
    // Package helps organize and protect this class
}
```

### 75. What is the java.lang package?

**Answer:**
* **Core package** - Contains fundamental classes every Java program needs
* **Auto-imported** - Automatically available without explicit import
* **Essential classes** - String, Object, System, Thread, Exception classes
* **Wrapper classes** - Integer, Double, Boolean, etc.

```java
// No import needed - java.lang is automatic
String name = "Java";
System.out.println(name);
Object obj = new Object();
```

### 76. Which is the most important class in Java?

**Answer:**
* **Object class** - Root of all Java class hierarchy
* **Universal parent** - Every class extends Object directly or indirectly
* **Core methods** - Provides toString(), equals(), hashCode(), clone()
* **Foundation** - Enables polymorphism and collections framework

```java
public class MyClass {
    // Implicitly extends Object
    @Override
    public String toString() {
        return "MyClass instance";
    }
}
```

### 77. Is it mandatory to import the java.lang package every time?

**Answer:**
* **No import required** - java.lang is automatically imported
* **Built-in access** - All classes available by default
* **Compiler handles** - JVM automatically includes java.lang
* **Exception** - Only explicit import needed for subpackages like java.lang.reflect

```java
// No import statements needed
public class Example {
    String text = "Hello";        // java.lang.String
    System.out.println(text);     // java.lang.System
}
```

### 78. Can you import the same package or class twice in your class?

**Answer:**
* **Redundant but allowed** - Multiple imports of same class don't cause errors
* **Compiler ignores** - Duplicate imports are simply ignored
* **No performance impact** - Doesn't affect runtime or compilation
* **Best practice** - Avoid duplicates for clean code

```java
import java.util.List;
import java.util.List;  // Redundant but valid
import java.util.*;     // Also imports List again

public class Example {
    List<String> items;  // Works fine
}
```

### 79. What is a static import in Java?

**Answer:**
* **Direct access** - Import static members without class name qualification
* **Cleaner code** - Reduces verbosity for frequently used static methods
* **Syntax** - Uses `import static` keyword
* **Careful usage** - Can reduce code readability if overused

```java
import static java.lang.Math.*;
import static java.lang.System.out;

public class Example {
    public void calculate() {
        double result = sqrt(25);  // Instead of Math.sqrt(25)
        out.println(result);       // Instead of System.out.println
    }
}
```

### 80. What is the difference between import static com.test.FooClass and import com.test.FooClass?

**Answer:**
* **Regular import** - `import com.test.FooClass` imports the class itself
* **Static import** - `import static com.test.FooClass` imports static members
* **Usage difference** - Regular needs class name, static doesn't
* **Scope** - Static import affects only static methods and fields

```java
// Regular import
import com.test.FooClass;
FooClass obj = new FooClass();
FooClass.staticMethod();

// Static import  
import static com.test.FooClass.*;
FooClass obj = new FooClass();  // Still need class name for constructor
staticMethod();                 // Direct access to static method
```

## Internationalization

### 81. What is Locale in Java?

**Answer:**
* **Geographic identifier** - Represents specific geographical, political, or cultural region
* **Language and country** - Combines language code with country/region code
* **Formatting control** - Determines how dates, numbers, currencies display
* **i18n support** - Essential for internationalization

```java
Locale usLocale = new Locale("en", "US");
Locale frenchLocale = Locale.FRANCE;
Locale defaultLocale = Locale.getDefault();

System.out.println(usLocale.getDisplayName()); // English (United States)
```

### 82. How will you use a specific Locale in Java?

**Answer:**
* **Create Locale object** - Use constructor or predefined constants
* **Apply to formatters** - Use with DateFormat, NumberFormat, etc.
* **Resource bundles** - Load locale-specific text and messages
* **Set as default** - Change JVM default locale if needed

```java
// Create and use specific locale
Locale locale = new Locale("de", "DE");  // German, Germany

// Format numbers for German locale
NumberFormat nf = NumberFormat.getInstance(locale);
String formatted = nf.format(1234.56);  // "1.234,56"

// Format dates
DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locale);
String date = df.format(new Date());

// Resource bundle for German
ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
String greeting = bundle.getString("hello");
```

# Java String Handling 

## String Concepts

### 83. What is the meaning of immutable in the context of the String class?

**Answer:**
* **Cannot be changed** - Once created, String content cannot be modified
* **New object creation** - Any modification creates a new String object
* **Original unchanged** - Original String remains unaffected
* **Memory efficiency** - Enables string pooling and caching

```java
String str = "Hello";
str.concat(" World");  // Creates new String, original unchanged
System.out.println(str);  // Still prints "Hello"

String newStr = str.concat(" World");  // Must assign to new variable
```

### 84. Why is a String object considered immutable in Java?

**Answer:**
* **Security** - Prevents malicious code from changing string values
* **Thread safety** - Multiple threads can safely access same String
* **Caching** - Hashcode can be cached since content never changes
* **String pool** - Enables memory optimization through interning

```java
// Thread-safe sharing
String password = "secret123";
// Multiple threads can read password safely

// Hashcode caching
String key = "myKey";
int hash = key.hashCode();  // Cached after first call
```

### 85. How many ways are there in Java to create a String object?

**Answer:**
* **String literal** - Using double quotes (goes to string pool)
* **New keyword** - Creates object in heap memory
* **Constructor methods** - Various constructors available
* **StringBuilder/StringBuffer** - Using toString() method

```java
// 1. String literal
String str1 = "Hello";

// 2. New keyword
String str2 = new String("Hello");

// 3. Constructor with char array
char[] chars = {'H', 'e', 'l', 'l', 'o'};
String str3 = new String(chars);

// 4. From StringBuilder
String str4 = new StringBuilder("Hello").toString();
```

### 86. What is String interning?

**Answer:**
* **String pool storage** - JVM stores unique string literals in special memory area
* **Memory optimization** - Identical strings share same memory location
* **Reference sharing** - Multiple variables can point to same string object
* **Automatic process** - Happens automatically for string literals

```java
String str1 = "Hello";        // Goes to string pool
String str2 = "Hello";        // Points to same object in pool
String str3 = new String("Hello");  // Creates new object in heap

System.out.println(str1 == str2);     // true (same reference)
System.out.println(str1 == str3);     // false (different objects)
System.out.println(str1 == str3.intern());  // true (intern returns pool reference)
```

### 87. Why does Java use the String literal concept?

**Answer:**
* **Memory efficiency** - Reduces memory usage by sharing identical strings
* **Performance boost** - Faster string comparison using reference equality
* **Reduced garbage** - Less object creation means less garbage collection
* **Common usage** - Strings are heavily used, optimization is crucial

```java
// Memory efficient - both point to same object
String name1 = "John";
String name2 = "John";

// Fast comparison
if (name1 == name2) {  // Reference comparison, very fast
    System.out.println("Same object in pool");
}
```

### 88. What is the basic difference between a String and StringBuffer object?

**Answer:**
* **Mutability** - String is immutable, StringBuffer is mutable
* **Thread safety** - StringBuffer is synchronized, String is not applicable
* **Performance** - StringBuffer better for multiple modifications
* **Memory** - StringBuffer uses internal buffer, String creates new objects

```java
// String - creates new objects
String str = "Hello";
str += " World";  // Creates new String object

// StringBuffer - modifies existing buffer
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");  // Modifies same object
String result = sb.toString();
```

### 89. How will you create an immutable class in Java?

**Answer:**
* **Final class** - Declare class as final to prevent inheritance
* **Private fields** - Make all fields private and final
* **No setters** - Don't provide setter methods
* **Defensive copying** - Return copies of mutable objects

```java
public final class ImmutablePerson {
    private final String name;
    private final int age;
    
    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    // No setter methods provided
}
```

### 90. What is the use of the toString() method in Java?

**Answer:**
* **String representation** - Converts object to readable string format
* **Debugging aid** - Helps in debugging and logging
* **Default behavior** - Returns classname@hashcode by default
* **Override recommended** - Should be overridden for meaningful output

```java
public class Person {
    private String name;
    private int age;
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

Person p = new Person();
System.out.println(p);  // Calls toString() automatically
```

### 91. Arrange String, StringBuffer, and StringBuilder in order of efficiency for String processing operations?

**Answer:**
* **Most efficient: StringBuilder** - Fastest, not synchronized
* **Medium efficient: StringBuffer** - Fast but synchronized overhead
* **Least efficient: String** - Creates new objects for each operation
* **Use case matters** - Choose based on thread safety requirements

```java
// Performance comparison for multiple concatenations
StringBuilder sb = new StringBuilder();  // Fastest
for (int i = 0; i < 1000; i++) {
    sb.append("text");
}

StringBuffer sbf = new StringBuffer();   // Medium (thread-safe)
for (int i = 0; i < 1000; i++) {
    sbf.append("text");
}

String str = "";                         // Slowest (creates 1000 objects)
for (int i = 0; i < 1000; i++) {
    str += "text";  // Very inefficient
}
```

## Performance Summary

| Class | Thread Safe | Performance | Use Case |
|-------|-------------|-------------|----------|
| StringBuilder | No | Fastest | Single-threaded string building |
| StringBuffer | Yes | Medium | Multi-threaded string building |
| String | N/A | Slowest for modifications | Immutable string operations |