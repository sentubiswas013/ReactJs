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

# Java Exception Handling 

## Exception Concepts

### 92. What is exception handling in Java?

**Answer:**
* **Error management** - Mechanism to handle runtime errors gracefully
* **Program continuity** - Prevents program from crashing unexpectedly
* **Structured approach** - Uses try-catch-finally blocks for control
* **Clean separation** - Separates error handling code from business logic

```java
try {
    int result = 10 / 0;  // May throw ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Cleanup code here");
}
```

### 93. What are the differences between checked and unchecked exceptions?

**Answer:**
* **Compile-time vs Runtime** - Checked at compile time vs runtime
* **Handling requirement** - Checked must be handled, unchecked optional
* **Inheritance** - Checked extends Exception, unchecked extends RuntimeException
* **Examples** - IOException vs NullPointerException

```java
// Checked exception - must handle
try {
    FileReader file = new FileReader("file.txt");
} catch (FileNotFoundException e) {
    // Must catch or declare throws
}

// Unchecked exception - optional handling
String str = null;
int length = str.length();  // RuntimeException, no forced handling
```

### 94. What is the base class for Error and Exception classes in Java?

**Answer:**
* **Throwable class** - Root class for all errors and exceptions
* **Two main branches** - Error and Exception both extend Throwable
* **Catchable objects** - Only Throwable objects can be thrown/caught
* **Common methods** - getMessage(), printStackTrace(), getCause()

```java
// Hierarchy
Throwable
├── Error (OutOfMemoryError, StackOverflowError)
└── Exception
    ├── RuntimeException (unchecked)
    └── Other exceptions (checked)

try {
    // some code
} catch (Throwable t) {  // Catches both Error and Exception
    t.printStackTrace();
}
```

### 95. What is a finally block in Java?

**Answer:**
* **Always executes** - Runs regardless of exception occurrence
* **Cleanup code** - Used for resource cleanup and finalization
* **Exception independent** - Executes even if catch block throws exception
* **Optional block** - Can exist without catch block

```java
FileInputStream file = null;
try {
    file = new FileInputStream("data.txt");
    // Process file
} catch (IOException e) {
    System.out.println("File error");
} finally {
    if (file != null) {
        file.close();  // Always cleanup resources
    }
}
```

### 96. What is the use of the finally block in Java?

**Answer:**
* **Resource cleanup** - Close files, database connections, streams
* **Guaranteed execution** - Ensures critical code always runs
* **Memory management** - Release allocated resources
* **Logging/monitoring** - Record completion status

```java
Connection conn = null;
try {
    conn = DriverManager.getConnection(url);
    // Database operations
} catch (SQLException e) {
    // Handle database errors
} finally {
    if (conn != null) {
        conn.close();  // Always close connection
    }
}
```

### 97. Can we create a finally block without creating a catch block?

**Answer:**
* **Yes, it's allowed** - try-finally is valid syntax
* **No exception handling** - Exceptions will propagate up
* **Resource cleanup** - Still useful for cleanup operations
* **Common pattern** - Used when you don't want to handle exceptions locally

```java
public void readFile() throws IOException {
    FileReader reader = null;
    try {
        reader = new FileReader("file.txt");
        // Read operations
    } finally {
        if (reader != null) {
            reader.close();  // Cleanup without catching
        }
    }
    // IOException propagates to caller
}
```

### 98. Do we have to always put a catch block after a try block?

**Answer:**
* **Not mandatory** - Either catch or finally is required
* **Three valid combinations** - try-catch, try-finally, try-catch-finally
* **Cannot be alone** - try block cannot exist by itself
* **Compiler enforced** - Will get compilation error without catch or finally

```java
// Valid combinations
try { } catch (Exception e) { }           // try-catch
try { } finally { }                       // try-finally  
try { } catch (Exception e) { } finally { } // try-catch-finally

// Invalid - compilation error
try { }  // Error: try without catch or finally
```

### 99. In what scenarios will a finally block not be executed?

**Answer:**
* **System.exit()** - JVM termination prevents finally execution
* **Fatal JVM errors** - OutOfMemoryError, system crashes
* **Infinite loops** - Code never reaches finally block
* **Thread interruption** - Daemon thread termination

```java
try {
    System.out.println("In try block");
    System.exit(0);  // JVM exits, finally won't execute
} finally {
    System.out.println("This won't print");
}

try {
    while (true) { }  // Infinite loop, finally never reached
} finally {
    System.out.println("Never executes");
}
```

### 100. Can we re-throw an exception in Java?

**Answer:**
* **Yes, using throw** - Can re-throw caught exceptions
* **Same or different** - Can throw same exception or wrap in new one
* **Preserve stack trace** - Original stack trace can be maintained
* **Common pattern** - Used for logging then propagating

```java
try {
    riskyOperation();
} catch (IOException e) {
    // Log the exception
    logger.error("Operation failed", e);
    
    // Re-throw same exception
    throw e;
    
    // Or wrap in different exception
    // throw new RuntimeException("Wrapped exception", e);
}
```

### 101. What is the difference between throw and throws in Java?

**Answer:**
* **throw** - Actually throws an exception object
* **throws** - Declares that method may throw exceptions
* **Usage location** - throw inside method body, throws in method signature
* **Purpose** - throw creates exception, throws declares possibility

```java
// throws - declares exceptions in method signature
public void readFile() throws IOException, FileNotFoundException {
    
    if (fileName == null) {
        throw new IllegalArgumentException("File name cannot be null");  // throw - actually throws
    }
    
    // Method may throw IOException (declared by throws)
    FileReader reader = new FileReader(fileName);
}
```

### 102. What is the concept of exception propagation?

**Answer:**
* **Upward movement** - Exceptions move up the call stack
* **Automatic process** - Happens when exception is not caught
* **Method chain** - Each method can catch or let it propagate
* **Program termination** - Uncaught exceptions terminate program

```java
public void method1() {
    try {
        method2();
    } catch (RuntimeException e) {
        System.out.println("Caught in method1");
    }
}

public void method2() {
    method3();  // Exception propagates from method3 to method2
}

public void method3() {
    throw new RuntimeException("Error in method3");  // Exception starts here
}
```

### 103. When we override a method in a child class, can we throw an additional exception that is not thrown by the parent class method?

**Answer:**
* **Checked exceptions: No** - Cannot throw new checked exceptions
* **Unchecked exceptions: Yes** - Can throw any RuntimeException
* **Same or subclass** - Can throw same or subclass of parent's exceptions
* **Liskov substitution** - Child must be substitutable for parent

```java
class Parent {
    public void method() throws IOException {
        // Parent throws IOException
    }
}

class Child extends Parent {
    @Override
    public void method() throws FileNotFoundException {  // OK - subclass of IOException
        // throw new SQLException();  // ERROR - not declared in parent
        throw new RuntimeException();  // OK - unchecked exception
    }
}
```

## Exception Hierarchy Summary

```
Throwable
├── Error (System-level errors)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── RuntimeException (Unchecked)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   └── IllegalArgumentException
    └── Checked Exceptions
        ├── IOException
        ├── SQLException
        └── ClassNotFoundException
```

## Iterators and Advanced Collections

### 113. What is the difference between Iterator and ListIterator?

**Answer:**
* **Direction** - Iterator is unidirectional, ListIterator is bidirectional
* **Collection support** - Iterator works with all collections, ListIterator only with List
* **Modification** - Iterator only removes, ListIterator can add, remove, and set
* **Index access** - ListIterator provides index information, Iterator doesn't

```java
List<String> list = Arrays.asList("A", "B", "C");

// Iterator - forward only, remove only
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String item = iterator.next();
    // iterator.remove();  // Only removal allowed
}

// ListIterator - bidirectional, full modification
ListIterator<String> listIterator = list.listIterator();
while (listIterator.hasNext()) {
    String item = listIterator.next();
    listIterator.set("Modified");     // Can modify
    listIterator.add("New");          // Can add
}
// Can go backward
while (listIterator.hasPrevious()) {
    String item = listIterator.previous();
}
```

### 114. What is the difference between Enumeration and Iterator?

**Answer:**
* **Legacy vs Modern** - Enumeration is legacy, Iterator is modern approach
* **Modification** - Enumeration read-only, Iterator supports removal
* **Fail-fast** - Iterator is fail-fast, Enumeration is not
* **Method names** - Enumeration uses longer method names

```java
Vector<String> vector = new Vector<>();
vector.add("A");
vector.add("B");

// Enumeration - legacy, read-only
Enumeration<String> enumeration = vector.elements();
while (enumeration.hasMoreElements()) {
    String item = enumeration.nextElement();
    // No modification methods available
}

// Iterator - modern, supports modification
Iterator<String> iterator = vector.iterator();
while (iterator.hasNext()) {
    String item = iterator.next();
    iterator.remove();  // Can remove elements
}
```

### 115. What is the fail-fast property of iterators?

**Answer:**
* **Concurrent modification detection** - Throws ConcurrentModificationException if collection modified during iteration
* **Safety mechanism** - Prevents unpredictable behavior from concurrent changes
* **ModCount tracking** - Uses modification count to detect changes
* **Immediate failure** - Fails as soon as modification is detected

```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
Iterator<String> iterator = list.iterator();

while (iterator.hasNext()) {
    String item = iterator.next();
    
    // This will cause ConcurrentModificationException
    list.add("D");  // Modifying collection during iteration
    
    // Correct way - use iterator's remove method
    // iterator.remove();  // Safe modification
}

// Safe concurrent modification using CopyOnWriteArrayList
List<String> safeList = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
for (String item : safeList) {
    safeList.add("D");  // No exception thrown
}
```

### 116. What is the difference between synchronized and concurrent collections?

**Answer:**
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

### 117. What is ConcurrentHashMap and how is it different from HashMap?

**Answer:**
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

## Iterator Comparison Summary

| Feature | Enumeration | Iterator | ListIterator |
|---------|-------------|----------|--------------|
| Direction | Forward | Forward | Bidirectional |
| Modification | No | Remove only | Add, Remove, Set |
| Collections | Vector, Hashtable | All Collections | List only |
| Fail-fast | No | Yes | Yes |
| Index access | No | No | Yes |
| Legacy | Yes | No | No |

## Collection Thread Safety Comparison

| Collection Type | Thread Safety | Performance | Null Support | Locking |
|----------------|---------------|-------------|--------------|---------|
| HashMap | No | High | Yes | None |
| Hashtable | Yes | Low | No | Full synchronization |
| Collections.synchronizedMap() | Yes | Medium | Yes | Full synchronization |
| ConcurrentHashMap | Yes | High | No | Segment-based |

## Concurrent Collections Benefits

```java
// Traditional synchronized approach - poor performance
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

// Modern concurrent approach - better performance
ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// Other concurrent collections
ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
```

## Best Practices

* **Use Iterator.remove()** instead of Collection.remove() during iteration
* **Choose ConcurrentHashMap** over synchronized HashMap for better performance
* **Use CopyOnWriteArrayList** for read-heavy scenarios with occasional writes
* **Avoid Enumeration** - use Iterator or enhanced for-loop instead
* **Consider BlockingQueue** for producer-consumer scenarios



# Java Multi-threading and Concurrency 

### Basic Threading
## 118. How does multi-threading work in Java?

**Answer:**
* Java creates multiple threads that run concurrently within a single process
* Each thread has its own stack but shares heap memory
* JVM schedules threads using time-slicing on available CPU cores
* Threads can be created by extending Thread class or implementing Runnable interface

```java
// Creating thread with Runnable
Thread t = new Thread(() -> System.out.println("Hello from thread"));
t.start();
```

## 119. What are the advantages of multithreading?

**Answer:**
* **Better performance** - Utilizes multiple CPU cores effectively
* **Improved responsiveness** - UI remains responsive while background tasks run
* **Resource sharing** - Threads share memory space efficiently
* **Concurrent execution** - Multiple tasks can run simultaneously

```java
// Parallel processing example
CompletableFuture.runAsync(() -> processData1());
CompletableFuture.runAsync(() -> processData2());
```

## 120. What are the disadvantages of multithreading?

**Answer:**
* **Complexity** - Harder to debug and maintain code
* **Synchronization overhead** - Performance cost of thread coordination
* **Race conditions** - Data corruption when threads access shared resources
* **Deadlocks** - Threads can block each other indefinitely

```java
// Potential race condition
private int counter = 0;
public void increment() { counter++; } // Not thread-safe
```

## 121. What is thread safety?

**Answer:**
* Code that works correctly when accessed by multiple threads simultaneously
* Ensures data consistency and prevents race conditions
* Achieved through synchronization, immutability, or thread-local storage
* Critical for shared mutable state

```java
// Thread-safe using AtomicInteger
private AtomicInteger counter = new AtomicInteger(0);
public void increment() { counter.incrementAndGet(); }
```

## 122. What is synchronization in Java?

**Answer:**
* Mechanism to control access to shared resources by multiple threads
* Ensures only one thread can access critical section at a time
* Prevents data corruption and maintains consistency
* Implemented using synchronized keyword, locks, or atomic classes

```java
private final Object lock = new Object();
public void criticalSection() {
    synchronized(lock) {
        // Only one thread can execute this
    }
}
```

## 123. What is the synchronized keyword?

**Answer:**
* Java keyword that provides mutual exclusion for methods or blocks
* Uses intrinsic lock (monitor) associated with every object
* Ensures thread-safe access to shared resources
* Can be applied to methods or code blocks

```java
// Synchronized method
public synchronized void updateBalance(int amount) {
    balance += amount;
}

// Synchronized block
synchronized(this) {
    balance += amount;
}
```

## 124. What are synchronized methods and blocks?

**Answer:**
* **Synchronized methods** - Entire method is thread-safe, uses object's intrinsic lock
* **Synchronized blocks** - Only specific code section is synchronized, more granular control
* Blocks allow custom lock objects for better performance
* Methods are simpler but less flexible

```java
// Synchronized method
public synchronized void method1() { /* thread-safe */ }

// Synchronized block with custom lock
private final Object lock = new Object();
public void method2() {
    synchronized(lock) { /* only this block is synchronized */ }
}
```

## 125. What is deadlock?

**Answer:**
* Situation where two or more threads are blocked forever, waiting for each other
* Occurs when threads acquire locks in different orders
* All threads are stuck and cannot proceed
* Must be prevented through careful lock ordering

```java
// Deadlock scenario
Thread1: synchronized(lockA) { synchronized(lockB) {...} }
Thread2: synchronized(lockB) { synchronized(lockA) {...} }
// Both threads wait for each other's lock
```

## 126. How do you prevent deadlock?

**Answer:**
* **Lock ordering** - Always acquire locks in the same order
* **Timeout** - Use tryLock with timeout instead of blocking
* **Avoid nested locks** - Minimize holding multiple locks
* **Use concurrent collections** - Avoid manual synchronization

```java
// Prevent deadlock with consistent ordering
private void transfer(Account from, Account to, int amount) {
    Account firstLock = from.getId() < to.getId() ? from : to;
    Account secondLock = from.getId() < to.getId() ? to : from;
    
    synchronized(firstLock) {
        synchronized(secondLock) {
            from.withdraw(amount);
            to.deposit(amount);
        }
    }
}
```

## 127. What is race condition?

**Answer:**
* When multiple threads access shared data simultaneously without proper synchronization
* Final result depends on timing and order of thread execution
* Leads to unpredictable and incorrect results
* Solved by making operations atomic or synchronized

```java
// Race condition example
private int count = 0;
public void increment() {
    count++; // Not atomic: read, increment, write
}

// Solution
private AtomicInteger count = new AtomicInteger(0);
public void increment() {
    count.incrementAndGet(); // Atomic operation
}
```

## 128. What is volatile keyword?

**Answer:**
* Ensures variable changes are immediately visible to all threads
* Prevents compiler optimizations that cache variable values
* Guarantees happens-before relationship for memory visibility
* Doesn't provide atomicity for compound operations

```java
private volatile boolean running = true;

public void stop() {
    running = false; // Immediately visible to all threads
}

public void run() {
    while(running) { /* loop */ }
}
```

## 129. What is atomic operations?

**Answer:**
* Operations that complete entirely or not at all, no intermediate state
* Cannot be interrupted by other threads
* Java provides atomic classes like AtomicInteger, AtomicBoolean
* Essential for thread-safe operations without synchronization overhead

```java
private AtomicInteger counter = new AtomicInteger(0);

public int incrementAndGet() {
    return counter.incrementAndGet(); // Atomic operation
}

public boolean compareAndSet(int expected, int update) {
    return counter.compareAndSet(expected, update);
}
```

## 130. What is java.util.concurrent package?

**Answer:**
* High-level concurrency utilities introduced in Java 5
* Provides thread pools, concurrent collections, synchronizers
* Includes ExecutorService, CountDownLatch, Semaphore, BlockingQueue
* Simplifies concurrent programming with proven patterns

```java
// ExecutorService example
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> System.out.println("Task executed"));

// Concurrent collection
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1); // Thread-safe

// CountDownLatch
CountDownLatch latch = new CountDownLatch(3);
latch.countDown(); // Decrements count
latch.await(); // Waits until count reaches zero
```

## Advanced Concurrency

## 131. What is ExecutorService?

**Answer:**
* High-level interface for managing and controlling thread execution
* Provides thread pool management and task scheduling capabilities
* Handles thread lifecycle automatically - creation, reuse, and termination
* Supports both Runnable and Callable tasks with Future results

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
Future<String> future = executor.submit(() -> "Task result");
String result = future.get();
executor.shutdown();
```

## 132. What is ThreadPoolExecutor?

**Answer:**
* Concrete implementation of ExecutorService with customizable thread pool
* Controls core pool size, maximum pool size, and keep-alive time
* Uses BlockingQueue to hold pending tasks
* Provides fine-grained control over thread pool behavior

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    2, 4, 60L, TimeUnit.SECONDS,
    new LinkedBlockingQueue<>(100)
);
executor.execute(() -> System.out.println("Task executed"));
```

## 133. What is Future and CompletableFuture?

**Answer:**
* **Future** - Represents result of asynchronous computation, blocking get() method
* **CompletableFuture** - Enhanced Future with non-blocking operations and chaining
* CompletableFuture supports callbacks, composition, and exception handling
* Enables reactive programming patterns in Java

```java
// Future - blocking
Future<String> future = executor.submit(() -> "Hello");
String result = future.get(); // Blocks until complete

// CompletableFuture - non-blocking
CompletableFuture<String> cf = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World");
```

## 134. What is CountDownLatch?

**Answer:**
* Synchronization primitive that allows threads to wait for multiple operations to complete
* Initialized with a count, decremented by countDown(), threads wait with await()
* One-time use only - cannot be reset once count reaches zero
* Perfect for coordinating startup or shutdown sequences

```java
CountDownLatch latch = new CountDownLatch(3);

// Worker threads
new Thread(() -> { doWork(); latch.countDown(); }).start();

// Main thread waits
latch.await(); // Blocks until count reaches 0
System.out.println("All tasks completed");
```

## 135. What is CyclicBarrier?

**Answer:**
* Synchronization point where threads wait for each other to reach barrier
* Reusable - automatically resets after all threads pass through
* Supports optional barrier action executed when all threads arrive
* Useful for parallel algorithms requiring synchronization points

```java
CyclicBarrier barrier = new CyclicBarrier(3, () -> 
    System.out.println("All threads reached barrier"));

// Each thread calls
barrier.await(); // Waits for other threads
// Continues after all 3 threads reach barrier
```

## 136. What is Semaphore?

**Answer:**
* Controls access to shared resource by limiting number of concurrent threads
* Maintains count of available permits - acquire() decreases, release() increases
* Useful for rate limiting and resource pool management
* Can be fair or unfair in permit distribution

```java
Semaphore semaphore = new Semaphore(2); // Allow 2 concurrent access

public void accessResource() throws InterruptedException {
    semaphore.acquire(); // Get permit
    try {
        // Access shared resource
    } finally {
        semaphore.release(); // Return permit
    }
}
```

## 137. What is ReentrantLock?

**Answer:**
* Advanced lock implementation providing more flexibility than synchronized
* Supports fairness, try-lock with timeout, and interruptible locking
* Same thread can acquire lock multiple times (reentrant)
* Must manually unlock in finally block to prevent deadlocks

```java
ReentrantLock lock = new ReentrantLock();

public void method() {
    lock.lock();
    try {
        // Critical section
    } finally {
        lock.unlock(); // Always unlock in finally
    }
}
```

## 138. What is ReadWriteLock?

**Answer:**
* Allows multiple concurrent readers but exclusive writer access
* Improves performance when reads are more frequent than writes
* ReentrantReadWriteLock is the standard implementation
* Read lock can be acquired by multiple threads, write lock is exclusive

```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();
Lock readLock = rwLock.readLock();
Lock writeLock = rwLock.writeLock();

// Multiple readers allowed
readLock.lock();
try { /* read data */ } finally { readLock.unlock(); }

// Exclusive writer
writeLock.lock();
try { /* write data */ } finally { writeLock.unlock(); }
```

## 139. What is BlockingQueue?

**Answer:**
* Thread-safe queue that blocks when empty (take) or full (put)
* Provides producer-consumer pattern implementation
* Common implementations: ArrayBlockingQueue, LinkedBlockingQueue
* Eliminates need for manual synchronization in producer-consumer scenarios

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

// Producer
queue.put("item"); // Blocks if queue is full

// Consumer
String item = queue.take(); // Blocks if queue is empty
```

## 140. What is fork-join framework?

**Answer:**
* Designed for parallel processing of recursive tasks that can be split
* Uses work-stealing algorithm for load balancing across threads
* ForkJoinPool manages worker threads efficiently
* Perfect for divide-and-conquer algorithms

```java
class SumTask extends RecursiveTask<Long> {
    private int[] array;
    private int start, end;
    
    protected Long compute() {
        if (end - start <= 1000) {
            return Arrays.stream(array, start, end).sum();
        }
        int mid = (start + end) / 2;
        SumTask left = new SumTask(array, start, mid);
        SumTask right = new SumTask(array, mid, end);
        left.fork();
        return right.compute() + left.join();
    }
}
```

## 141. What is parallel streams?

**Answer:**
* Stream API feature that automatically parallelizes operations across multiple threads
* Uses ForkJoinPool.commonPool() by default for parallel execution
* Simple way to leverage multi-core processors for data processing
* Best for CPU-intensive operations on large datasets

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Sequential
int sum = numbers.stream().mapToInt(i -> i * i).sum();

// Parallel - automatically uses multiple threads
int parallelSum = numbers.parallelStream()
    .mapToInt(i -> i * i)
    .sum();
```

## 142. What is reactive streams?

**Answer:**
* Specification for asynchronous stream processing with non-blocking backpressure
* Handles data streams that may arrive faster than they can be processed
* Key interfaces: Publisher, Subscriber, Subscription, Processor
* Implemented by libraries like RxJava, Project Reactor, Akka Streams

```java
// Using Flow API (Java 9+)
Flow.Publisher<String> publisher = subscriber -> {
    subscriber.onSubscribe(new Flow.Subscription() {
        public void request(long n) {
            subscriber.onNext("Hello");
            subscriber.onComplete();
        }
        public void cancel() {}
    });
};

publisher.subscribe(new Flow.Subscriber<String>() {
    public void onNext(String item) { System.out.println(item); }
    public void onComplete() { System.out.println("Done"); }
    // ... other methods
});
```

# Memory Management and Garbage Collection

## Memory Concepts
### 143. What are the different memory areas in JVM?

**Answer:**
* **Heap Memory** - Stores objects and instance variables, shared among threads
* **Stack Memory** - Stores method calls, local variables, and partial results
* **Method Area** - Stores class-level data, static variables, and method bytecode
* **PC Register** - Tracks currently executing instruction for each thread
* **Native Method Stack** - For native method calls (JNI)

```java
public class MemoryExample {
    static int classVar = 10;        // Method Area
    
    public void method() {
        int localVar = 20;           // Stack Memory
        String obj = new String();   // Object in Heap, reference in Stack
    }
}
```

### 144. What is heap memory in Java?

**Answer:**
* Runtime memory area where all objects and instance variables are stored
* Divided into Young Generation (Eden, S0, S1) and Old Generation
* Shared among all threads in the application
* Managed by garbage collector for automatic memory cleanup
* Size controlled by -Xms and -Xmx JVM parameters

```java
public class HeapExample {
    private String name;             // Stored in heap
    private List<String> items;      // Object and list elements in heap
    
    public void createObjects() {
        String str = new String("Hello");  // Goes to heap memory
        int[] array = new int[1000];       // Array object in heap
    }
}
```

### 145. What is stack memory in Java?

**Answer:**
* Thread-specific memory area storing method call frames
* Contains local variables, method parameters, and return addresses
* Follows LIFO (Last In First Out) principle
* Automatically managed - memory freed when method completes
* Each thread has its own stack memory

```java
public class StackExample {
    public void methodA() {
        int x = 10;          // Local variable in stack
        methodB(x);          // Method call frame pushed to stack
    }                        // Frame popped when method ends
    
    public void methodB(int param) {
        String local = "test";  // Reference in stack, object in heap
    }
}
```

### 146. What is method area in Java?

**Answer:**
* Stores class-level information shared among all instances
* Contains class metadata, static variables, method bytecode, and constant pool
* Also called Metaspace (Java 8+) or Permanent Generation (Java 7-)
* Shared among all threads of the application
* Garbage collected when classes are unloaded

```java
public class MethodAreaExample {
    static final String CONSTANT = "Hello";    // Stored in method area
    static int staticVar = 100;                // Static variable in method area
    
    public void instanceMethod() {             // Method bytecode in method area
        // Method implementation
    }
}
```

### 147. What is the difference between heap and stack memory?

**Answer:**
* **Purpose** - Heap stores objects, Stack stores method calls and local variables
* **Sharing** - Heap is shared among threads, Stack is thread-specific
* **Speed** - Stack access is faster, Heap access is slower
* **Management** - Stack is automatic, Heap needs garbage collection
* **Size** - Heap is larger and configurable, Stack is smaller and fixed

```java
public class MemoryComparison {
    private String heapData = "Stored in heap";  // Heap memory
    
    public void demonstrate() {
        int stackVar = 42;                       // Stack memory
        String obj = new String("heap object");  // obj reference in stack, object in heap
        
        // stackVar automatically freed when method ends
        // obj reference freed, but object needs GC
    }
}
```

### 148. What is memory leak in Java?

**Answer:**
* Objects that are no longer needed but still referenced, preventing garbage collection
* Causes gradual memory consumption leading to OutOfMemoryError
* Common causes: static collections, listeners not removed, unclosed resources
* Despite garbage collection, memory leaks can still occur in Java

```java
public class MemoryLeakExample {
    private static List<String> cache = new ArrayList<>();  // Static collection
    
    public void addToCache(String data) {
        cache.add(data);  // Memory leak: objects never removed from static list
    }
    
    // Better approach
    private static Map<String, String> betterCache = new WeakHashMap<>();
}
```

### 149. How can we prevent memory leaks in Java?

**Answer:**
* **Close resources** - Use try-with-resources for automatic cleanup
* **Remove listeners** - Unregister event listeners when no longer needed
* **Avoid static collections** - Use WeakHashMap or clear collections periodically
* **Profile memory usage** - Use tools like JProfiler or VisualVM

```java
public class PreventMemoryLeak {
    // Use try-with-resources
    public void readFile() throws IOException {
        try (FileReader reader = new FileReader("file.txt")) {
            // Resource automatically closed
        }
    }
    
    // Remove listeners
    public void cleanup() {
        button.removeActionListener(listener);
        timer.stop();
    }
    
    // Use WeakHashMap for caches
    private Map<String, Object> cache = new WeakHashMap<>();
}
```

### 150. What is the difference between shallow copy and deep copy?

**Answer:**
* **Shallow Copy** - Copies object references, not the actual objects
* **Deep Copy** - Creates new objects for all referenced objects recursively
* Shallow copy shares mutable objects between original and copy
* Deep copy creates completely independent objects

```java
public class CopyExample implements Cloneable {
    private List<String> items = new ArrayList<>();
    
    // Shallow copy - default clone()
    public Object clone() throws CloneNotSupportedException {
        return super.clone();  // Shares the same 'items' list
    }
    
    // Deep copy
    public CopyExample deepCopy() {
        CopyExample copy = new CopyExample();
        copy.items = new ArrayList<>(this.items);  // New list with same elements
        return copy;
    }
}
```

### 151. What is the purpose of clone() method?

**Answer:**
* Creates a copy of an object without calling constructor
* Defined in Object class, requires Cloneable interface implementation
* Provides shallow copy by default - can be overridden for deep copy
* Alternative to copy constructors for object duplication
* Throws CloneNotSupportedException if Cloneable not implemented

```java
public class Person implements Cloneable {
    private String name;
    private int age;
    
    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();  // Shallow copy
    }
    
    // Usage
    Person original = new Person("John", 25);
    Person copy = original.clone();
}
```

### 152. What is the difference between == and equals() method?

**Answer:**
* **== operator** - Compares memory addresses (reference equality)
* **equals() method** - Compares object content (logical equality)
* == is faster but only checks if two references point to same object
* equals() can be overridden to define custom equality logic
* For primitives, == compares actual values

```java
public class EqualityExample {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        String s3 = "Hello";
        String s4 = "Hello";
        
        System.out.println(s1 == s2);        // false - different objects
        System.out.println(s1.equals(s2));   // true - same content
        System.out.println(s3 == s4);        // true - string pool
        
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2);        // true - Integer cache
    }
}
```

## Java Garbage Collection 

### 153. What is garbage collection in Java?

**Answer:**
* Automatic memory management process that reclaims unused object memory
* Runs in background to free heap memory occupied by unreferenced objects
* Prevents memory leaks and OutOfMemoryError in most cases
* Eliminates need for manual memory deallocation like in C/C++
* Managed by JVM with various garbage collection algorithms

```java
public class GCExample {
    public void createObjects() {
        String temp = new String("Hello");  // Object created
        // When method ends, 'temp' becomes unreferenced
        // Object becomes eligible for garbage collection
    }
}
```

### 154. Why Java provides garbage collector?

**Answer:**
* **Automatic memory management** - Developers don't need to manually free memory
* **Prevents memory leaks** - Automatically cleans up unreferenced objects
* **Reduces programming errors** - No dangling pointers or double-free issues
* **Improves productivity** - Focus on business logic instead of memory management
* **Runtime optimization** - GC can optimize memory layout and allocation

```java
public class WhyGC {
    public void demonstrateAutoCleanup() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("Item " + i);  // Objects created automatically
        }
        // When method ends, list and all strings become eligible for GC
        // No manual cleanup needed
    }
}
```

### 155. What is the purpose of gc() in Java?

**Answer:**
* System.gc() suggests JVM to run garbage collection
* It's only a hint - JVM may ignore the request
* Cannot force immediate garbage collection
* Generally not recommended in production code
* Useful for testing or specific performance scenarios

```java
public class GCMethod {
    public void demonstrateGC() {
        // Create many objects
        for (int i = 0; i < 10000; i++) {
            new String("Object " + i);
        }
        
        System.gc();  // Suggest garbage collection
        // No guarantee GC will run immediately
        
        Runtime.getRuntime().gc();  // Alternative way
    }
}
```

### 156. How does garbage collection work in Java?

**Answer:**
* **Mark phase** - Identifies which objects are still reachable/referenced
* **Sweep phase** - Removes unreferenced objects from memory
* **Compact phase** - Defragments memory to reduce fragmentation
* Uses generational hypothesis - most objects die young
* Different algorithms: Serial, Parallel, G1, ZGC for different needs

```java
public class GCProcess {
    private static List<String> keepAlive = new ArrayList<>();
    
    public void demonstrateGCPhases() {
        String reachable = "I'm referenced";
        keepAlive.add(reachable);           // Mark: reachable
        
        String unreachable = new String("I'll be collected");
        unreachable = null;                 // Sweep: eligible for GC
        
        // Compact: Memory defragmentation happens automatically
    }
}
```

### 157. When does an object become eligible for garbage collection?

**Answer:**
* When no active references point to the object
* All references go out of scope or are set to null
* Object is only referenced by other unreferenced objects (island of isolation)
* Parent object containing the reference is itself unreferenced
* Circular references with no external references

```java
public class GCEligibility {
    public void demonstrateEligibility() {
        String obj1 = new String("Hello");
        String obj2 = obj1;                 // Two references to same object
        
        obj1 = null;                        // Still referenced by obj2
        obj2 = null;                        // Now eligible for GC
        
        List<String> list = new ArrayList<>();
        list.add("Item");                   // Referenced by list
    }   // list goes out of scope - both list and "Item" eligible for GC
}
```

### 158. Why do we use finalize() method in Java?

**Answer:**
* Called by garbage collector before object destruction
* Provides last chance to perform cleanup operations
* Generally not recommended - use try-with-resources instead
* No guarantee when or if it will be called
* Can prevent garbage collection if not implemented properly

```java
public class FinalizeExample {
    private FileInputStream file;
    
    @Override
    protected void finalize() throws Throwable {
        try {
            if (file != null) {
                file.close();  // Cleanup resource
            }
        } finally {
            super.finalize();
        }
    }
    
    // Better approach - use try-with-resources
    public void betterApproach() throws IOException {
        try (FileInputStream fis = new FileInputStream("file.txt")) {
            // Automatic cleanup
        }
    }
}
```

### 159. What are the different types of references in Java?

**Answer:**
* **Strong Reference** - Normal references, prevents garbage collection
* **Weak Reference** - Allows GC even if weakly referenced
* **Soft Reference** - GC only when memory is low
* **Phantom Reference** - Used for cleanup actions after GC
* Helps control object lifecycle and memory management

```java
import java.lang.ref.*;

public class ReferenceTypes {
    public void demonstrateReferences() {
        String obj = new String("Strong");          // Strong reference
        
        WeakReference<String> weak = new WeakReference<>(obj);
        SoftReference<String> soft = new SoftReference<>(obj);
        PhantomReference<String> phantom = new PhantomReference<>(obj, new ReferenceQueue<>());
        
        obj = null;  // Remove strong reference
        // weak.get() may return null after GC
        // soft.get() returns null only when memory is low
    }
}
```

### 160. How can we reference an unreferenced object again?

**Answer:**
* Once object has no strong references, it cannot be directly referenced again
* Can use weak/soft references to check if object still exists
* finalize() method could theoretically resurrect object (not recommended)
* Better to maintain proper object lifecycle management
* Use object pools or caching for reusable objects

```java
public class ObjectResurrection {
    private static ObjectResurrection saved;
    
    @Override
    protected void finalize() throws Throwable {
        saved = this;  // Resurrect object (bad practice)
        super.finalize();
    }
    
    // Better approach - use WeakReference
    private static WeakReference<String> cache;
    
    public static String getCachedValue() {
        String value = cache != null ? cache.get() : null;
        if (value == null) {
            value = "New Value";
            cache = new WeakReference<>(value);
        }
        return value;
    }
}
```

### 161. What kind of process is the garbage collector thread?

**Answer:**
* **Daemon thread** - Runs in background, doesn't prevent JVM shutdown
* **Low priority thread** - Runs when CPU is available
* **System thread** - Managed by JVM, not user application
* Can be concurrent or stop-the-world depending on GC algorithm
* Multiple GC threads may run in parallel for better performance

```java
public class GCThreadDemo {
    public static void main(String[] args) {
        // GC runs as daemon thread
        Thread gcThread = new Thread(() -> {
            while (true) {
                // Simulate GC work
                System.gc();
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        gcThread.setDaemon(true);  // Similar to GC thread behavior
        gcThread.start();
        
        // Main thread can exit, daemon threads won't prevent shutdown
    }
}
```

### 162. What are the different types of garbage collectors?

**Answer:**
* **Serial GC** - Single-threaded, suitable for small applications
* **Parallel GC** - Multi-threaded, good for throughput-focused applications
* **G1 GC** - Low-latency collector for large heaps
* **ZGC/Shenandoah** - Ultra-low latency collectors for real-time applications
* **CMS** - Concurrent collector (deprecated in Java 14)

```java
// JVM flags to select garbage collectors
public class GCTypes {
    /*
    -XX:+UseSerialGC          // Serial Garbage Collector
    -XX:+UseParallelGC        // Parallel Garbage Collector  
    -XX:+UseG1GC              // G1 Garbage Collector
    -XX:+UseZGC               // ZGC (Java 11+)
    -XX:+UseShenandoahGC      // Shenandoah GC
    */
    
    public void monitorGC() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        
        System.out.println("Used: " + heapUsage.getUsed());
        System.out.println("Max: " + heapUsage.getMax());
    }
}
```

### 163. How do you tune garbage collection?

**Answer:**
* **Monitor GC logs** - Enable GC logging to analyze performance
* **Adjust heap size** - Set appropriate -Xms and -Xmx values
* **Choose right GC algorithm** - Based on application requirements
* **Tune GC parameters** - NewRatio, MaxGCPauseMillis, etc.
* **Profile application** - Use tools like JProfiler, VisualVM

```java
public class GCTuning {
    /*
    JVM Tuning Parameters:
    -Xms2g -Xmx4g                    // Heap size
    -XX:NewRatio=3                   // Old/Young generation ratio
    -XX:MaxGCPauseMillis=200         // Target pause time for G1
    -XX:+PrintGC                     // Enable GC logging
    -XX:+PrintGCDetails              // Detailed GC logs
    */
    
    public void measureGCImpact() {
        long startTime = System.currentTimeMillis();
        
        // Create objects to trigger GC
        List<byte[]> memory = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            memory.add(new byte[1024 * 1024]); // 1MB objects
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Time with GC: " + (endTime - startTime) + "ms");
    }
}
```

# Java I/O Operations 

## Basic I/O
### 164. What is Java I/O?

**Answer:**
* Input/Output API for reading and writing data from various sources
* Handles files, network connections, memory buffers, and other data streams
* Provides classes for both byte-oriented and character-oriented operations
* Foundation for data persistence and communication in Java applications
* Includes traditional I/O and modern NIO (New I/O) packages

```java
import java.io.*;

public class BasicIO {
    public void readFile() throws IOException {
        FileInputStream fis = new FileInputStream("data.txt");
        int data = fis.read();  // Read single byte
        fis.close();
        
        FileReader fr = new FileReader("text.txt");
        int character = fr.read();  // Read single character
        fr.close();
    }
}
```

### 165. What are the different types of streams in Java?

**Answer:**
* **Byte Streams** - Handle raw binary data (InputStream/OutputStream)
* **Character Streams** - Handle text data with encoding (Reader/Writer)
* **Input Streams** - Read data from source
* **Output Streams** - Write data to destination
* **Buffered Streams** - Improve performance with internal buffering

```java
public class StreamTypes {
    public void demonstrateStreams() throws IOException {
        // Byte streams
        FileInputStream byteInput = new FileInputStream("binary.dat");
        FileOutputStream byteOutput = new FileOutputStream("output.dat");
        
        // Character streams
        FileReader charInput = new FileReader("text.txt");
        FileWriter charOutput = new FileWriter("output.txt");
        
        // Buffered streams
        BufferedReader bufferedReader = new BufferedReader(charInput);
        BufferedWriter bufferedWriter = new BufferedWriter(charOutput);
    }
}
```

### 166. What is the difference between byte stream and character stream?

**Answer:**
* **Byte Stream** - Handles 8-bit bytes, suitable for binary data like images, videos
* **Character Stream** - Handles 16-bit Unicode characters, suitable for text data
* **Encoding** - Character streams handle character encoding automatically
* **Performance** - Byte streams are faster, character streams are more convenient for text
* **Base Classes** - InputStream/OutputStream vs Reader/Writer

```java
public class StreamComparison {
    public void byteStreamExample() throws IOException {
        FileInputStream fis = new FileInputStream("image.jpg");
        byte[] buffer = new byte[1024];
        int bytesRead = fis.read(buffer);  // Read raw bytes
        fis.close();
    }
    
    public void characterStreamExample() throws IOException {
        FileReader fr = new FileReader("document.txt");
        char[] buffer = new char[1024];
        int charsRead = fr.read(buffer);  // Read characters with encoding
        fr.close();
    }
}
```

### 167. What is the difference between InputStream and OutputStream?

**Answer:**
* **InputStream** - Abstract class for reading byte data from source
* **OutputStream** - Abstract class for writing byte data to destination
* **Direction** - InputStream is for input, OutputStream is for output
* **Methods** - InputStream has read() methods, OutputStream has write() methods
* **Common Implementations** - FileInputStream/FileOutputStream, ByteArrayInputStream/ByteArrayOutputStream

```java
public class StreamDirection {
    public void inputStreamExample() throws IOException {
        InputStream is = new FileInputStream("input.txt");
        int data = is.read();           // Read single byte
        byte[] buffer = new byte[100];
        is.read(buffer);                // Read into buffer
        is.close();
    }
    
    public void outputStreamExample() throws IOException {
        OutputStream os = new FileOutputStream("output.txt");
        os.write(65);                   // Write single byte (ASCII 'A')
        os.write("Hello".getBytes());   // Write byte array
        os.close();
    }
}
```

### 168. What is the difference between Reader and Writer?

**Answer:**
* **Reader** - Abstract class for reading character data with encoding support
* **Writer** - Abstract class for writing character data with encoding support
* **Character-based** - Handle Unicode characters instead of raw bytes
* **Encoding** - Automatically handle character encoding/decoding
* **Text Processing** - Designed specifically for text data manipulation

```java
public class ReaderWriter {
    public void readerExample() throws IOException {
        Reader reader = new FileReader("text.txt");
        int character = reader.read();      // Read single character
        char[] buffer = new char[100];
        reader.read(buffer);                // Read into character buffer
        reader.close();
    }
    
    public void writerExample() throws IOException {
        Writer writer = new FileWriter("output.txt");
        writer.write('A');                  // Write single character
        writer.write("Hello World");        // Write string
        writer.close();
    }
}
```

### 169. What is BufferedReader and BufferedWriter?

**Answer:**
* **BufferedReader** - Wraps Reader with internal buffer for efficient reading
* **BufferedWriter** - Wraps Writer with internal buffer for efficient writing
* **Performance** - Reduces system calls by batching read/write operations
* **Methods** - BufferedReader provides readLine() for line-by-line reading
* **Buffering** - Default buffer size is 8192 characters

```java
public class BufferedStreams {
    public void bufferedReaderExample() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("large.txt"));
        String line;
        while ((line = br.readLine()) != null) {  // Read line by line
            System.out.println(line);
        }
        br.close();
    }
    
    public void bufferedWriterExample() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        bw.write("Line 1");
        bw.newLine();                    // Platform-independent line separator
        bw.write("Line 2");
        bw.flush();                      // Force buffer to write
        bw.close();
    }
}
```

### 170. What is the purpose of File class in Java?

**Answer:**
* Represents file and directory pathnames in abstract way
* Provides methods for file operations like create, delete, rename
* Checks file properties like existence, permissions, size
* Platform-independent file system operations
* Does not actually read/write file content - only metadata operations

```java
import java.io.File;

public class FileOperations {
    public void fileExample() {
        File file = new File("example.txt");
        
        if (file.exists()) {
            System.out.println("Size: " + file.length());
            System.out.println("Can read: " + file.canRead());
            System.out.println("Can write: " + file.canWrite());
        }
        
        File dir = new File("myDirectory");
        dir.mkdir();                     // Create directory
        
        String[] files = dir.list();     // List files in directory
        file.delete();                   // Delete file
    }
}
```
## NIO (New I/O)
### 171. What is NIO in Java?

**Answer:**
* **New I/O** - Modern I/O API introduced in Java 1.4
* **Non-blocking** - Supports non-blocking I/O operations
* **Channel-based** - Uses channels instead of streams for better performance
* **Buffer-oriented** - Data read into buffers for efficient processing
* **Selector** - Single thread can monitor multiple channels

```java
import java.nio.*;
import java.nio.channels.*;

public class NIOExample {
    public void basicNIO() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("file.txt"));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        int bytesRead = channel.read(buffer);  // Read into buffer
        buffer.flip();                         // Prepare for reading
        
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        channel.close();
    }
}
```

### 172. What is the difference between IO and NIO?

**Answer:**
* **IO** - Stream-oriented, blocking, single-threaded
* **NIO** - Buffer-oriented, non-blocking, supports multiplexing
* **Performance** - NIO is faster for large files and network operations
* **Complexity** - IO is simpler, NIO requires more complex programming
* **Use Cases** - IO for simple operations, NIO for high-performance applications

```java
public class IOvsNIO {
    // Traditional IO - blocking
    public void traditionalIO() throws IOException {
        FileInputStream fis = new FileInputStream("file.txt");
        int data = fis.read();  // Blocks until data available
        fis.close();
    }
    
    // NIO - non-blocking
    public void nioApproach() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("file.txt"));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = channel.read(buffer);  // Can be non-blocking
        channel.close();
    }
}
```

### 173. What are channels in Java NIO?

**Answer:**
* **Bidirectional** - Can read and write data unlike streams
* **Buffer-based** - Work with ByteBuffer objects for data transfer
* **Types** - FileChannel, SocketChannel, ServerSocketChannel, DatagramChannel
* **Performance** - More efficient than streams for large data operations
* **Non-blocking** - Support non-blocking I/O operations

```java
public class ChannelExample {
    public void fileChannelExample() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("data.txt"), 
                                             StandardOpenOption.READ, 
                                             StandardOpenOption.WRITE);
        
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = channel.read(buffer);    // Read from channel
        
        buffer.flip();
        channel.write(buffer);                   // Write to channel
        channel.close();
    }
}
```

### 174. What is NIO.2 features?

**Answer:**
* **Path API** - Modern file system interface replacing File class
* **Files utility class** - Convenient methods for file operations
* **Watch Service** - Monitor directory changes in real-time
* **Asynchronous I/O** - Non-blocking file operations with callbacks
* **File attributes** - Better support for file metadata and permissions

```java
import java.nio.file.*;

public class NIO2Features {
    public void pathAPI() throws IOException {
        Path path = Paths.get("example.txt");
        
        // Files utility methods
        Files.createFile(path);
        Files.write(path, "Hello NIO.2".getBytes());
        List<String> lines = Files.readAllLines(path);
        
        // File attributes
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        System.out.println("Size: " + attrs.size());
    }
}
```

### 175. What is asynchronous file I/O?

**Answer:**
* **Non-blocking** - Operations return immediately without waiting for completion
* **Callback-based** - Uses CompletionHandler for result notification
* **AsynchronousFileChannel** - Main class for async file operations
* **Future-based** - Can also return Future objects for result handling
* **Performance** - Better for I/O intensive applications

```java
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;

public class AsyncIO {
    public void asyncRead() throws IOException {
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("file.txt"));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("Read completed: " + result + " bytes");
            }
            
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Read failed: " + exc.getMessage());
            }
        });
    }
}
```

### 176. What is memory-mapped I/O?

**Answer:**
* **Virtual memory** - Maps file content directly into memory address space
* **Performance** - Extremely fast for large file operations
* **MappedByteBuffer** - Special buffer type for memory-mapped files
* **OS-level** - Leverages operating system's virtual memory system
* **Use cases** - Large file processing, database implementations

```java
public class MemoryMappedIO {
    public void memoryMappedFile() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("largefile.dat"));
        
        MappedByteBuffer buffer = channel.map(
            FileChannel.MapMode.READ_WRITE, 0, channel.size());
        
        // Direct memory access - very fast
        buffer.put(0, (byte) 'A');      // Write at position 0
        byte data = buffer.get(100);     // Read from position 100
        
        channel.close();
    }
}
```

### 177. What is zero-copy I/O?

**Answer:**
* **Direct transfer** - Data moves directly between channels without copying to application buffers
* **transferTo/transferFrom** - Methods that enable zero-copy operations
* **Performance** - Eliminates unnecessary data copying, improves speed
* **Kernel space** - Operations happen in kernel space, not user space
* **Use cases** - File copying, network data transfer, streaming applications

```java
public class ZeroCopyIO {
    public void zeroCopyTransfer() throws IOException {
        FileChannel source = FileChannel.open(Paths.get("source.txt"));
        FileChannel destination = FileChannel.open(Paths.get("dest.txt"), 
                                                  StandardOpenOption.CREATE, 
                                                  StandardOpenOption.WRITE);
        
        // Zero-copy transfer - no intermediate buffers
        source.transferTo(0, source.size(), destination);
        
        source.close();
        destination.close();
    }
}
```

# Serialization and Reflection
## Serialization
### 178. What is serialization?

**Answer:**
* Process of converting Java object into byte stream for storage or transmission
* Allows objects to be saved to files, databases, or sent over network
* Object state is preserved including all instance variables
* Requires class to implement Serializable interface
* Automatic process handled by ObjectOutputStream

```java
import java.io.*;

public class SerializationExample implements Serializable {
    private String name;
    private int age;
    
    public void serializeObject() throws IOException {
        SerializationExample obj = new SerializationExample();
        obj.name = "John";
        obj.age = 25;
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.ser"));
        oos.writeObject(obj);  // Serialize object to file
        oos.close();
    }
}
```

### 179. What is the purpose of serialization?

**Answer:**
* **Persistence** - Save object state to disk for later retrieval
* **Network communication** - Send objects between different JVMs
* **Caching** - Store objects in memory or distributed cache systems
* **Deep copying** - Create exact copies of complex objects
* **RMI/Web services** - Enable remote method invocation and data exchange

```java
public class SerializationPurpose implements Serializable {
    private String data;
    
    // Network transmission example
    public void sendOverNetwork() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        
        byte[] serializedData = baos.toByteArray();  // Ready for network
        // Send serializedData over network
    }
}
```

### 180. What is deserialization?

**Answer:**
* Reverse process of serialization - converting byte stream back to Java object
* Reconstructs object from serialized data with same state
* Uses ObjectInputStream to read serialized data
* Object class must be available in classpath during deserialization
* Can throw ClassNotFoundException or InvalidClassException

```java
public class DeserializationExample {
    public void deserializeObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.ser"));
        
        SerializationExample obj = (SerializationExample) ois.readObject();
        // Object restored with original state
        
        ois.close();
        System.out.println("Object deserialized successfully");
    }
}
```

### 181. Why do we mark a data member transient?

**Answer:**
* **Exclude from serialization** - Transient fields are not serialized
* **Security** - Prevent sensitive data like passwords from being serialized
* **Non-serializable references** - Skip fields that can't be serialized
* **Calculated values** - Exclude derived fields that can be recalculated
* **Default values** - Transient fields get default values during deserialization

```java
public class TransientExample implements Serializable {
    private String username;
    private transient String password;      // Not serialized
    private transient Thread workerThread;  // Non-serializable
    private transient int calculatedValue;  // Can be recalculated
    
    public void demonstrateTransient() {
        // password and workerThread won't be serialized
        // calculatedValue will be 0 after deserialization
    }
}
```

### 182. Is it allowed to mark a method as transient?

**Answer:**
* **No** - transient keyword can only be applied to instance variables
* **Methods are not serialized** - Only object state (fields) is serialized
* **Compile error** - Using transient on methods causes compilation failure
* **Method behavior** - Methods are part of class definition, not object state
* **Alternative** - Use static or private methods if needed

```java
public class TransientMethod implements Serializable {
    private transient String data;           // Valid - field can be transient
    
    // public transient void method() {}     // Compile error - invalid
    
    public void normalMethod() {             // Valid - methods don't need transient
        // Method implementation
    }
    
    private static void utilityMethod() {    // Static methods not serialized anyway
        // Utility implementation
    }
}
```

### 183. What is the Externalizable interface in Java?

**Answer:**
* **Custom serialization control** - Provides complete control over serialization process
* **Manual implementation** - Must implement writeExternal() and readExternal() methods
* **Performance** - Can be faster than default serialization
* **Selective serialization** - Choose exactly which fields to serialize
* **Extends Serializable** - Subinterface of Serializable with additional methods

```java
import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ExternalizableExample implements Externalizable {
    private String name;
    private int age;
    private String password;  // Won't serialize this
    
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);   // Manually serialize only name and age
        out.writeInt(age);
        // password is not serialized
    }
    
    public void readExternal(ObjectInput in) throws IOException {
        name = in.readUTF();  // Manually deserialize
        age = in.readInt();
        // password remains null
    }
}
```

### 184. What is the difference between Serializable and Externalizable interface?

**Answer:**
* **Control** - Serializable is automatic, Externalizable gives manual control
* **Performance** - Externalizable can be faster with custom implementation
* **Methods** - Serializable has no methods, Externalizable requires writeExternal/readExternal
* **Default constructor** - Externalizable requires public no-arg constructor
* **Flexibility** - Externalizable allows selective field serialization

```java
// Serializable - automatic
public class SerializableClass implements Serializable {
    private String data;
    // All non-transient fields automatically serialized
}

// Externalizable - manual control
public class ExternalizableClass implements Externalizable {
    private String data;
    
    public ExternalizableClass() {}  // Required public no-arg constructor
    
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(data);  // Manual serialization
    }
    
    public void readExternal(ObjectInput in) throws IOException {
        data = in.readUTF();  // Manual deserialization
    }
}
```

### 185. What is custom serialization?

**Answer:**
* **Override default behavior** - Implement writeObject() and readObject() methods
* **Special handling** - Custom logic for complex objects or security requirements
* **Validation** - Add validation during serialization/deserialization
* **Compatibility** - Handle version changes and backward compatibility
* **Performance optimization** - Optimize serialization for specific use cases

```java
public class CustomSerializationExample implements Serializable {
    private String data;
    private transient String cachedValue;
    
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();        // Serialize non-transient fields
        oos.writeUTF(data.toUpperCase()); // Custom serialization logic
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();         // Deserialize non-transient fields
        String customData = ois.readUTF(); // Read custom data
        cachedValue = customData.toLowerCase(); // Initialize transient field
    }
}
```

### 186. What is serialization security?

**Answer:**
* **Data exposure** - Serialized data can be inspected and modified
* **Deserialization attacks** - Malicious data can exploit deserialization process
* **Sensitive information** - Use transient for passwords and sensitive data
* **Validation** - Implement readObject() with proper validation
* **SerialVersionUID** - Control class version compatibility

```java
public class SecureSerializationExample implements Serializable {
    private static final long serialVersionUID = 1L;  // Version control
    
    private String username;
    private transient String password;  // Never serialize passwords
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        
        // Validation during deserialization
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidObjectException("Username cannot be null or empty");
        }
        
        // Re-initialize sensitive data
        password = null;  // Ensure password is not restored
    }
    
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // Clear sensitive data before serialization
        String tempPassword = password;
        password = null;
        
        oos.defaultWriteObject();
        
        // Restore after serialization
        password = tempPassword;
    }
}
```

## Reflection
### 187. What is reflection in Java?

**Answer:**
* **Runtime introspection** - Examine and modify classes, methods, fields at runtime
* **Dynamic access** - Access private members and invoke methods dynamically
* **Metadata inspection** - Get information about class structure without source code
* **Framework foundation** - Used by Spring, Hibernate, JUnit for dependency injection and testing
* **java.lang.reflect package** - Contains classes like Class, Method, Field, Constructor

```java
import java.lang.reflect.*;

public class ReflectionExample {
    public void demonstrateReflection() throws Exception {
        Class<?> clazz = String.class;
        
        Method[] methods = clazz.getMethods();        // Get all public methods
        Field[] fields = clazz.getDeclaredFields();   // Get all fields
        Constructor<?>[] constructors = clazz.getConstructors(); // Get constructors
        
        System.out.println("Class name: " + clazz.getName());
    }
}
```

### 188. What are the uses of reflection in Java?

**Answer:**
* **Frameworks** - Spring uses reflection for dependency injection and bean creation
* **Testing** - JUnit uses reflection to find and execute test methods
* **Serialization** - JSON/XML libraries use reflection to map objects
* **IDE tools** - Code completion and debugging features
* **Dynamic loading** - Load and instantiate classes at runtime

```java
public class ReflectionUses {
    public void frameworkExample() throws Exception {
        // Simulate framework behavior
        Class<?> clazz = Class.forName("com.example.UserService");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        
        Method method = clazz.getMethod("processUser", String.class);
        method.invoke(instance, "John");  // Dynamic method invocation
    }
}
```

### 189. How can we access a private method of a class from outside the class?

**Answer:**
* **getDeclaredMethod()** - Get private method using reflection
* **setAccessible(true)** - Bypass access control to make private method accessible
* **invoke()** - Call the private method with required parameters
* **Security risk** - Breaks encapsulation, use carefully
* **Testing purpose** - Mainly used for unit testing private methods

```java
public class PrivateMethodAccess {
    private void privateMethod(String message) {
        System.out.println("Private: " + message);
    }
    
    public static void accessPrivateMethod() throws Exception {
        PrivateMethodAccess obj = new PrivateMethodAccess();
        
        Method method = obj.getClass().getDeclaredMethod("privateMethod", String.class);
        method.setAccessible(true);  // Bypass access control
        method.invoke(obj, "Hello"); // Call private method
    }
}
```

### 190. How can we create an object dynamically at runtime in Java?

**Answer:**
* **Class.forName()** - Load class dynamically by name
* **newInstance()** - Create object using default constructor
* **Constructor.newInstance()** - Create object with parameterized constructor
* **Dynamic instantiation** - Useful for plugin architectures and frameworks
* **Exception handling** - Handle ClassNotFoundException and InstantiationException

```java
public class DynamicObjectCreation {
    public void createObjectDynamically() throws Exception {
        // Method 1: Using Class.forName()
        Class<?> clazz = Class.forName("java.util.ArrayList");
        Object obj1 = clazz.getDeclaredConstructor().newInstance();
        
        // Method 2: Using Constructor with parameters
        Constructor<?> constructor = clazz.getConstructor(int.class);
        Object obj2 = constructor.newInstance(10);  // ArrayList with capacity 10
        
        System.out.println("Objects created: " + obj1.getClass().getName());
    }
}
```

### 191. What is reflection performance optimization?

**Answer:**
* **Caching** - Cache Method, Field, Constructor objects to avoid repeated lookups
* **Method handles** - Use MethodHandle API for better performance than reflection
* **Avoid setAccessible()** - Use public APIs when possible
* **Compile-time alternatives** - Use code generation or annotations processors
* **Minimal usage** - Use reflection only when necessary, prefer direct calls

```java
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class ReflectionOptimization {
    private static final Map<String, Method> methodCache = new HashMap<>();
    
    // Cache methods for better performance
    public Method getCachedMethod(Class<?> clazz, String methodName) throws Exception {
        String key = clazz.getName() + "." + methodName;
        return methodCache.computeIfAbsent(key, k -> {
            try {
                return clazz.getMethod(methodName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    // Use MethodHandle for better performance
    public void useMethodHandle() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(String.class, "length", MethodType.methodType(int.class));
        int length = (int) mh.invoke("Hello");  // Faster than reflection
    }
}
```

### 192. What is method handles?

**Answer:**
* **Modern alternative** - Java 7+ feature for dynamic method invocation
* **Better performance** - Faster than traditional reflection
* **Type safety** - Compile-time type checking with MethodType
* **JVM optimization** - Better optimized by JVM compared to reflection
* **MethodHandles.Lookup** - Factory for creating method handles

```java
import java.lang.invoke.*;

public class MethodHandleExample {
    public void demonstrateMethodHandle() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        
        // Get method handle for String.substring(int, int)
        MethodHandle mh = lookup.findVirtual(
            String.class, 
            "substring", 
            MethodType.methodType(String.class, int.class, int.class)
        );
        
        String result = (String) mh.invoke("Hello World", 0, 5);  // "Hello"
        System.out.println(result);
    }
}
```

### 193. What is dynamic proxies?

**Answer:**
* **Runtime proxy creation** - Create proxy objects that implement interfaces dynamically
* **Proxy.newProxyInstance()** - Factory method to create proxy objects
* **InvocationHandler** - Interface to handle method calls on proxy
* **AOP implementation** - Foundation for aspect-oriented programming
* **Framework usage** - Used by Spring AOP, Hibernate, and other frameworks

```java
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

interface UserService {
    void createUser(String name);
}

public class DynamicProxyExample {
    public void createProxy() {
        UserService proxy = (UserService) Proxy.newProxyInstance(
            UserService.class.getClassLoader(),
            new Class[]{UserService.class},
            (proxy1, method, args) -> {
                System.out.println("Before: " + method.getName());
                // Simulate actual method call
                System.out.println("Creating user: " + args[0]);
                System.out.println("After: " + method.getName());
                return null;
            }
        );
        
        proxy.createUser("John");  // Calls InvocationHandler
    }
}
```
# Inner Classes and Nested Classes

## Nested Classes
### 194. What is a nested class?

**Answer:**
* **Class inside class** - Class defined within another class
* **Logical grouping** - Groups related classes together
* **Access privileges** - Can access outer class private members
* **Types** - Static nested, inner, local inner, and anonymous classes
* **Encapsulation** - Provides better encapsulation and code organization

```java
public class OuterClass {
    private String outerField = "Outer";
    
    // Static nested class
    static class StaticNested {
        void display() {
            System.out.println("Static nested class");
        }
    }
    
    // Inner class
    class Inner {
        void display() {
            System.out.println("Inner: " + outerField);  // Access outer field
        }
    }
}
```

### 195. How many types of nested classes are in Java?

**Answer:**
* **Static nested class** - Static class inside another class
* **Inner class (non-static)** - Non-static class with access to outer instance
* **Local inner class** - Class defined inside method or block
* **Anonymous class** - Class without name, defined and instantiated together
* **Each type** - Has different access rules and use cases

```java
public class NestedClassTypes {
    static class StaticNested {}           // 1. Static nested
    
    class Inner {}                         // 2. Inner class
    
    public void method() {
        class LocalInner {}                // 3. Local inner class
        
        Runnable anonymous = new Runnable() {  // 4. Anonymous class
            public void run() {}
        };
    }
}
```

### 196. Why do we use nested classes?

**Answer:**
* **Logical grouping** - Group classes that are only used in one place
* **Encapsulation** - Access outer class private members
* **Code organization** - Keep related classes together
* **Namespace management** - Avoid naming conflicts
* **Helper classes** - Create utility classes specific to outer class

```java
public class LinkedList<T> {
    private Node head;
    
    // Nested class for better encapsulation
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public void add(T data) {
        Node newNode = new Node(data);  // Access to nested class
        newNode.next = head;
        head = newNode;
    }
}
```

### 197. What is the difference between a nested class and an inner class?

**Answer:**
* **Nested class** - General term for any class inside another class
* **Inner class** - Specifically refers to non-static nested classes
* **Static nested** - Cannot access outer instance variables directly
* **Inner (non-static)** - Has implicit reference to outer class instance
* **Terminology** - All inner classes are nested, but not all nested are inner

```java
public class NestedVsInner {
    private String outerField = "Outer";
    
    // Static nested class (not inner)
    static class StaticNested {
        void method() {
            // System.out.println(outerField);  // Compile error - no outer instance
        }
    }
    
    // Inner class (non-static nested)
    class Inner {
        void method() {
            System.out.println(outerField);  // OK - has outer instance reference
        }
    }
}
```

### 198. What is a nested interface?

**Answer:**
* **Interface inside class** - Interface defined within a class or another interface
* **Implicitly static** - Nested interfaces are always static by default
* **Access modifiers** - Can be public, protected, or private
* **Logical grouping** - Groups related interfaces with their implementation
* **Common pattern** - Used in collections framework and event handling

```java
public class OuterClass {
    // Nested interface (implicitly static)
    public interface NestedInterface {
        void method();
    }
    
    // Implementation of nested interface
    class Implementation implements NestedInterface {
        public void method() {
            System.out.println("Nested interface implementation");
        }
    }
}

// Usage
OuterClass.NestedInterface obj = new OuterClass().new Implementation();
```

### 199. How can we access the non-final local variable inside a local inner class?

**Answer:**
* **Cannot access** - Local inner classes cannot access non-final local variables
* **Effectively final** - Variable must be effectively final (not modified after initialization)
* **Java 8+** - No need to explicitly declare final if variable is effectively final
* **Reason** - Local variables are stored on stack, inner class objects on heap
* **Workaround** - Use final variables or instance variables

```java
public class LocalInnerAccess {
    public void method() {
        int finalVar = 10;              // Effectively final
        final int explicitFinal = 20;   // Explicitly final
        int nonFinal = 30;
        nonFinal = 40;                  // Modified - not effectively final
        
        class LocalInner {
            void display() {
                System.out.println(finalVar);      // OK - effectively final
                System.out.println(explicitFinal); // OK - explicitly final
                // System.out.println(nonFinal);   // Compile error - not final
            }
        }
    }
}
```

### 200. Can an interface be defined in a class?

**Answer:**
* **Yes** - Interfaces can be defined inside classes
* **Implicitly static** - Nested interfaces are always static
* **Access modifiers** - Can have public, protected, private, or package access
* **Logical organization** - Groups related interface with its implementation
* **Common usage** - Event listeners, callback interfaces, strategy patterns

```java
public class EventSource {
    // Nested interface inside class
    public interface EventListener {
        void onEvent(String event);
    }
    
    private EventListener listener;
    
    public void setListener(EventListener listener) {
        this.listener = listener;
    }
    
    public void fireEvent(String event) {
        if (listener != null) {
            listener.onEvent(event);
        }
    }
}
```

### 201. Do we have to explicitly mark a nested interface public static?

**Answer:**
* **Static by default** - Nested interfaces are implicitly static
* **Access modifier required** - Must specify public, protected, or private explicitly
* **Not implicitly public** - Unlike top-level interfaces, nested interfaces need explicit access modifier
* **Best practice** - Always specify access modifier for clarity
* **Compilation** - Will compile without static keyword but good to be explicit

```java
public class InterfaceModifiers {
    // Implicitly static, but access modifier needed
    public interface PublicNested {}        // OK
    protected interface ProtectedNested {}  // OK
    private interface PrivateNested {}      // OK
    
    // interface DefaultNested {}           // Package-private, OK but specify explicitly
    
    // Explicitly static (redundant but clear)
    public static interface ExplicitStatic {}
}
```

### 202. Why do we use static nested interface in Java?

**Answer:**
* **Logical grouping** - Group related interfaces with their implementation classes
* **Namespace organization** - Avoid naming conflicts in large applications
* **Encapsulation** - Control access to interface through outer class
* **Design patterns** - Common in strategy, observer, and factory patterns
* **Framework design** - Used extensively in Java collections and event handling

```java
public class DatabaseConnection {
    // Static nested interface for connection strategies
    public static interface ConnectionStrategy {
        Connection getConnection();
    }
    
    // Implementations of the strategy
    public static class MySQLStrategy implements ConnectionStrategy {
        public Connection getConnection() {
            return DriverManager.getConnection("jdbc:mysql://...");
        }
    }
    
    public static class PostgreSQLStrategy implements ConnectionStrategy {
        public Connection getConnection() {
            return DriverManager.getConnection("jdbc:postgresql://...");
        }
    }
    
    // Usage: DatabaseConnection.ConnectionStrategy strategy = new DatabaseConnection.MySQLStrategy();
}
```

# Java 8+ Features
---
## Java 8 Features

## 203. What are the new features introduced in Java 8?

**Answer:**
* **Lambda expressions** - Functional programming with concise syntax
* **Stream API** - Functional-style operations on collections
* **Default methods** - Interface methods with implementation
* **Optional class** - Better null handling and avoiding NullPointerException
* **Method references** - Shorthand for lambda expressions
* **Date/Time API** - New java.time package replacing Date/Calendar

```java
import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;

public class Java8Features {
    public void demonstrateFeatures() {
        List<String> names = Arrays.asList("John", "Jane", "Bob");
        
        // Lambda expression
        names.forEach(name -> System.out.println(name));
        
        // Stream API
        List<String> filtered = names.stream()
            .filter(name -> name.startsWith("J"))
            .collect(Collectors.toList());
        
        // Optional
        Optional<String> first = names.stream().findFirst();
        
        // New Date API
        LocalDate today = LocalDate.now();
    }
}
```

## 204. What are lambda expressions in Java 8?

**Answer:**
* **Anonymous functions** - Functions without name, class, or modifier
* **Functional programming** - Enables functional programming paradigm in Java
* **Concise syntax** - Reduces boilerplate code for simple operations
* **Syntax** - (parameters) -> expression or (parameters) -> { statements }
* **Functional interfaces** - Can only be used with interfaces having single abstract method

```java
import java.util.function.*;

public class LambdaExpressions {
    public void demonstrateLambdas() {
        // Traditional anonymous class
        Runnable oldWay = new Runnable() {
            public void run() { System.out.println("Old way"); }
        };
        
        // Lambda expression
        Runnable newWay = () -> System.out.println("Lambda way");
        
        // With parameters
        BinaryOperator<Integer> add = (a, b) -> a + b;
        Predicate<String> isEmpty = str -> str.isEmpty();
        Function<String, Integer> length = str -> str.length();
        
        System.out.println(add.apply(5, 3));  // 8
    }
}
```

## 205. What are functional interfaces in Java 8?

**Answer:**
* **Single Abstract Method (SAM)** - Interface with exactly one abstract method
* **@FunctionalInterface** - Annotation to ensure functional interface contract
* **Lambda target** - Can be used as target for lambda expressions
* **Built-in interfaces** - Predicate, Function, Consumer, Supplier in java.util.function
* **Method references** - Can be used with method references

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);  // Single abstract method
    
    default void display() {      // Default methods allowed
        System.out.println("Calculator");
    }
}

public class FunctionalInterfaces {
    public void demonstrateFunctional() {
        // Custom functional interface
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        
        // Built-in functional interfaces
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> stringLength = String::length;
        Consumer<String> printer = System.out::println;
        
        System.out.println(add.calculate(5, 3));  // 8
        System.out.println(isEven.test(4));       // true
    }
}
```

## 206. What is the Stream API in Java 8?

**Answer:**
* **Functional operations** - Process collections in functional programming style
* **Pipeline operations** - Chain multiple operations together
* **Lazy evaluation** - Operations are not executed until terminal operation
* **Parallel processing** - Easy parallel processing with parallelStream()
* **Immutable** - Original collection remains unchanged

```java
import java.util.stream.*;

public class StreamAPI {
    public void demonstrateStreams() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter, map, and collect
        List<Integer> evenSquares = numbers.stream()
            .filter(n -> n % 2 == 0)           // Intermediate operation
            .map(n -> n * n)                   // Intermediate operation
            .collect(Collectors.toList());     // Terminal operation
        
        // Find operations
        Optional<Integer> first = numbers.stream().findFirst();
        boolean anyMatch = numbers.stream().anyMatch(n -> n > 5);
        
        // Parallel processing
        int sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
        
        System.out.println(evenSquares);  // [4, 16, 36, 64, 100]
    }
}
```

## 207. What are default methods in interfaces?

**Answer:**
* **Interface evolution** - Add new methods to interfaces without breaking existing implementations
* **Implementation in interface** - Interfaces can now have method implementations
* **Multiple inheritance** - Classes can inherit behavior from multiple interfaces
* **Backward compatibility** - Existing classes don't need to implement new methods
* **Diamond problem** - Resolved using explicit override or super calls

```java
interface Drawable {
    void draw();  // Abstract method
    
    default void print() {  // Default method with implementation
        System.out.println("Printing...");
    }
    
    default void display() {
        System.out.println("Default display from Drawable");
    }
}

interface Printable {
    default void display() {
        System.out.println("Default display from Printable");
    }
}

public class Shape implements Drawable, Printable {
    public void draw() {
        System.out.println("Drawing shape");
    }
    
    // Must override to resolve diamond problem
    public void display() {
        Drawable.super.display();  // Call specific interface method
    }
}
```

## 208. What are static methods in interfaces?

**Answer:**
* **Utility methods** - Interfaces can have static utility methods
* **No inheritance** - Static methods are not inherited by implementing classes
* **Interface namespace** - Called using interface name, not instance
* **Helper methods** - Provide common functionality related to interface
* **No override** - Cannot be overridden in implementing classes

```java
interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
    
    static int multiply(int a, int b) {
        return a * b;
    }
    
    static boolean isEven(int number) {
        return number % 2 == 0;
    }
}

public class StaticInterfaceMethods {
    public void demonstrateStatic() {
        // Call static methods using interface name
        int sum = MathUtils.add(5, 3);
        int product = MathUtils.multiply(4, 6);
        boolean even = MathUtils.isEven(8);
        
        System.out.println("Sum: " + sum);        // 8
        System.out.println("Product: " + product); // 24
        System.out.println("Is even: " + even);   // true
    }
}
```

## 209. What is the Optional class in Java 8?

**Answer:**
* **Null safety** - Container object to avoid NullPointerException
* **Explicit null handling** - Makes null handling explicit and safer
* **Functional methods** - Provides functional methods like map, filter, orElse
* **No more null checks** - Eliminates need for explicit null checks
* **Immutable** - Optional objects are immutable

```java
import java.util.Optional;

public class OptionalExample {
    public void demonstrateOptional() {
        // Creating Optional
        Optional<String> optional = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);
        
        // Checking presence
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        
        // Functional approach
        optional.ifPresent(System.out::println);
        
        // Default values
        String value = empty.orElse("Default");
        String computed = empty.orElseGet(() -> "Computed Default");
        
        // Chaining operations
        Optional<Integer> length = optional
            .filter(s -> s.length() > 3)
            .map(String::length);
    }
}
```

## 210. What are method references in Java 8?

**Answer:**
* **Shorthand for lambdas** - Concise way to refer to methods using :: operator
* **Four types** - Static, instance, constructor, and arbitrary object method references
* **Readability** - Makes code more readable when lambda just calls existing method
* **Functional interface target** - Can be used wherever lambda expressions are used
* **No parameters** - Method signature must match functional interface

```java
import java.util.function.*;

public class MethodReferences {
    public void demonstrateMethodReferences() {
        List<String> names = Arrays.asList("John", "Jane", "Bob");
        
        // Static method reference
        Function<String, Integer> parseInt = Integer::parseInt;
        
        // Instance method reference
        Consumer<String> printer = System.out::println;
        
        // Constructor reference
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;
        Function<Integer, ArrayList<String>> listWithCapacity = ArrayList::new;
        
        // Arbitrary object method reference
        names.stream()
            .map(String::toUpperCase)  // Same as s -> s.toUpperCase()
            .forEach(System.out::println);
        
        // Usage
        int number = parseInt.apply("123");
        ArrayList<String> newList = listSupplier.get();
    }
}
```

## 211. What is the forEach method in Java 8?

**Answer:**
* **Internal iteration** - Iterates over collection elements internally
* **Functional approach** - Takes Consumer functional interface as parameter
* **Default method** - Added as default method in Iterable interface
* **Parallel support** - Can be easily parallelized with parallel streams
* **Cleaner syntax** - More readable than traditional for loops

```java
public class ForEachExample {
    public void demonstrateForEach() {
        List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");
        Map<String, Integer> ages = Map.of("John", 25, "Jane", 30, "Bob", 35);
        
        // Traditional for loop
        for (String name : names) {
            System.out.println(name);
        }
        
        // forEach with lambda
        names.forEach(name -> System.out.println(name));
        
        // forEach with method reference
        names.forEach(System.out::println);
        
        // forEach on Map
        ages.forEach((name, age) -> System.out.println(name + ": " + age));
        
        // forEach on Stream
        names.stream()
            .filter(name -> name.startsWith("J"))
            .forEach(System.out::println);
    }
}
```

## 212. What are the differences between Collection API and Stream API?

**Answer:**
* **Purpose** - Collection stores data, Stream processes data
* **Mutability** - Collections are mutable, Streams are immutable
* **Iteration** - Collections use external iteration, Streams use internal iteration
* **Reusability** - Collections can be reused, Streams are consumed once
* **Lazy evaluation** - Collections are eager, Streams are lazy until terminal operation

```java
public class CollectionVsStream {
    public void demonstrateDifferences() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Collection API - External iteration, mutable
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num * num);
            }
        }
        
        // Stream API - Internal iteration, immutable
        List<Integer> evenSquares = numbers.stream()
            .filter(n -> n % 2 == 0)           // Lazy - not executed yet
            .map(n -> n * n)                   // Lazy - not executed yet
            .collect(Collectors.toList());     // Terminal - triggers execution
        
        // Collection - can be reused
        System.out.println("Size: " + numbers.size());
        numbers.forEach(System.out::println);
        
        // Stream - consumed after terminal operation
        Stream<Integer> stream = numbers.stream();
        stream.forEach(System.out::println);
        // stream.count();  // IllegalStateException - stream already consumed
    }
}
```

## Modern Java Features (9+)

### 213. What are the new features in Java 9?

**Answer:**
* **Module System (Jigsaw)** - Modularize JDK and applications for better encapsulation
* **JShell (REPL)** - Interactive Java shell for testing code snippets
* **Private methods in interfaces** - Interfaces can have private helper methods
* **Collection factory methods** - List.of(), Set.of(), Map.of() for immutable collections
* **Stream API enhancements** - takeWhile(), dropWhile(), iterate() methods
* **Process API improvements** - Better process management and control

```java
import java.util.*;

public class Java9Features {
    public void demonstrateFeatures() {
        // Collection factory methods
        List<String> list = List.of("Java", "Python", "JavaScript");
        Set<Integer> set = Set.of(1, 2, 3, 4, 5);
        Map<String, Integer> map = Map.of("Java", 9, "Python", 3);
        
        // Stream enhancements
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.stream()
            .takeWhile(n -> n < 6)        // [1, 2, 3, 4, 5]
            .forEach(System.out::println);
        
        numbers.stream()
            .dropWhile(n -> n < 6)        // [6, 7, 8, 9, 10]
            .forEach(System.out::println);
    }
}
```

### 214. What is the module system in Java 9?

**Answer:**
* **Project Jigsaw** - Modularize JDK and applications into discrete modules
* **module-info.java** - Module descriptor file defining dependencies and exports
* **Encapsulation** - Strong encapsulation of internal APIs
* **Reliable configuration** - Eliminates classpath hell and missing dependencies
* **Scalable platform** - Smaller runtime images with only required modules

```java
// module-info.java
module com.example.myapp {
    requires java.base;          // Dependency on java.base module
    requires java.logging;       // Dependency on logging module
    
    exports com.example.api;     // Export public API package
    
    provides com.example.spi.Service 
        with com.example.impl.ServiceImpl;  // Service provider
}

// Usage in modular application
public class ModuleExample {
    public void demonstrateModule() {
        // Only exported packages are accessible
        // Internal implementation packages are encapsulated
        System.out.println("Running in modular application");
    }
}
```

### 215. What are the new features in Java 10?

**Answer:**
* **Local variable type inference (var)** - Compiler infers type from initializer
* **Application Class-Data Sharing** - Improve startup time and memory footprint
* **Garbage collector improvements** - Parallel full GC for G1 collector
* **Container awareness** - Better Docker container support
* **Root certificates** - Default set of root CA certificates

```java
import java.util.*;

public class Java10Features {
    public void demonstrateVar() {
        // Local variable type inference with var
        var message = "Hello Java 10";           // String
        var number = 42;                         // int
        var list = new ArrayList<String>();      // ArrayList<String>
        var map = Map.of("key", "value");        // Map<String, String>
        
        // var in loops
        var numbers = List.of(1, 2, 3, 4, 5);
        for (var num : numbers) {                // int
            System.out.println(num);
        }
        
        // var with streams
        var result = numbers.stream()
            .filter(n -> n > 2)
            .collect(Collectors.toList());       // List<Integer>
    }
}
```

### 216. What is var keyword in Java 10?

**Answer:**
* **Type inference** - Compiler automatically infers variable type from initializer
* **Local variables only** - Can only be used for local variables, not fields or parameters
* **Readability** - Reduces verbosity while maintaining type safety
* **Compile-time feature** - No runtime overhead, purely compile-time sugar
* **Limitations** - Cannot be used with null, lambda expressions without explicit types

```java
public class VarKeyword {
    // private var field = "test";  // Compile error - not allowed for fields
    
    public void demonstrateVar() {
        // Valid uses of var
        var name = "John";                    // String
        var age = 25;                         // int
        var salary = 50000.0;                 // double
        var isActive = true;                  // boolean
        var list = new ArrayList<String>();   // ArrayList<String>
        
        // Invalid uses
        // var x;                             // Compile error - no initializer
        // var y = null;                      // Compile error - cannot infer from null
        // var lambda = () -> "test";         // Compile error - ambiguous type
        
        // Correct lambda with var
        var lambda = (String s) -> s.toUpperCase();  // Function<String, String>
    }
}
```

### 217. What are the new features in Java 11?

**Answer:**
* **HTTP Client API** - Standard HTTP client replacing legacy HttpURLConnection
* **String methods** - isBlank(), lines(), strip(), repeat() methods
* **File methods** - Files.readString(), Files.writeString() for easy file operations
* **var in lambda** - Use var in lambda parameter declarations
* **Flight Recorder** - Low-overhead profiling and monitoring tool
* **ZGC** - Experimental low-latency garbage collector

```java
import java.net.http.*;
import java.nio.file.*;

public class Java11Features {
    public void demonstrateFeatures() throws Exception {
        // String enhancements
        String text = "  Hello World  ";
        System.out.println(text.isBlank());           // false
        System.out.println(text.strip());             // "Hello World"
        System.out.println("Java".repeat(3));         // "JavaJavaJava"
        
        // File operations
        String content = "Hello Java 11";
        Files.writeString(Path.of("test.txt"), content);
        String read = Files.readString(Path.of("test.txt"));
        
        // HTTP Client
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.example.com"))
            .build();
        HttpResponse<String> response = client.send(request, 
            HttpResponse.BodyHandlers.ofString());
        
        // var in lambda
        var list = List.of("a", "b", "c");
        list.stream()
            .map((var s) -> s.toUpperCase())  // var in lambda parameter
            .forEach(System.out::println);
    }
}
```

### 218. What are the new features in Java 14?

**Answer:**
* **Switch expressions** - Enhanced switch with expression syntax and yield keyword
* **Text blocks (Preview)** - Multi-line string literals with triple quotes
* **Pattern matching for instanceof (Preview)** - Simplified instanceof checks
* **Records (Preview)** - Compact syntax for data carrier classes
* **Helpful NullPointerExceptions** - Better NPE messages with precise locations

```java
public class Java14Features {
    public void demonstrateSwitchExpressions() {
        String day = "MONDAY";
        
        // Traditional switch statement
        String result1;
        switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY":
                result1 = "Weekday";
                break;
            case "SATURDAY", "SUNDAY":
                result1 = "Weekend";
                break;
            default:
                result1 = "Unknown";
        }
        
        // Switch expression
        String result2 = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        
        // Switch expression with yield
        String result3 = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> {
                System.out.println("It's a weekday");
                yield "Weekday";
            }
            case "SATURDAY", "SUNDAY" -> {
                System.out.println("It's weekend");
                yield "Weekend";
            }
            default -> "Unknown";
        };
    }
    
    public void demonstrateTextBlocks() {
        // Text blocks (Preview in Java 14)
        String json = """
            {
                "name": "John",
                "age": 30,
                "city": "New York"
            }
            """;
        
        String html = """
            <html>
                <body>
                    <h1>Hello World</h1>
                </body>
            </html>
            """;
    }
}
```

### 219. What are the new features in Java 17?

**Answer:**
* **Sealed classes** - Restrict which classes can extend or implement
* **Pattern matching for switch (Preview)** - Enhanced switch with pattern matching
* **Text blocks** - Finalized multi-line string literals feature
* **Records** - Finalized compact data carrier classes
* **Strong encapsulation** - JDK internals strongly encapsulated by default
* **New macOS rendering pipeline** - Metal-based rendering for better performance

```java
// Sealed classes
public sealed class Shape permits Circle, Rectangle, Triangle {
    // Only Circle, Rectangle, Triangle can extend Shape
}

final class Circle extends Shape {
    private final double radius;
    public Circle(double radius) { this.radius = radius; }
}

final class Rectangle extends Shape {
    private final double width, height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

final class Triangle extends Shape {
    private final double base, height;
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
}

// Records (finalized)
public record Person(String name, int age, String email) {
    // Automatically generates constructor, getters, equals, hashCode, toString
    
    // Custom validation in compact constructor
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}

public class Java17Features {
    public void demonstrateFeatures() {
        // Using records
        Person person = new Person("John", 30, "john@example.com");
        System.out.println(person.name());  // Getter method
        System.out.println(person);         // toString() automatically generated
        
        // Pattern matching for switch (Preview)
        Shape shape = new Circle(5.0);
        String result = switch (shape) {
            case Circle c -> "Circle with radius " + c.radius;
            case Rectangle r -> "Rectangle " + r.width + "x" + r.height;
            case Triangle t -> "Triangle with base " + t.base;
        };
        
        System.out.println(result);
    }
}
```

