# Java Interview Questions - Basic Concepts

## 1. What is Java and what are its key features?

**Answer:**
Java is a high-level, object-oriented programming language developed by Sun Microsystems (now Oracle).

**Key Features:**
- **Platform Independent** - Write once, run anywhere (WORA)
- **Object-Oriented** - Everything is an object
- **Simple and Secure** - No pointers, automatic memory management
- **Multithreaded** - Can handle multiple tasks simultaneously
- **Robust** - Strong memory management and exception handling

**Example:**
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

---

## 2. Explain the difference between JDK, JRE, and JVM.

**Answer:**
- **JVM (Java Virtual Machine)** - Runtime environment that executes Java bytecode
- **JRE (Java Runtime Environment)** - JVM + libraries needed to run Java applications
- **JDK (Java Development Kit)** - JRE + development tools (compiler, debugger)

**Simple way to remember:**
- JDK = Development tools + JRE
- JRE = JVM + Runtime libraries
- JVM = Executes bytecode

---

## 3. What are the main principles of Object-Oriented Programming?

**Answer:**
The four main principles are:
- **Encapsulation** - Bundling data and methods together
- **Inheritance** - Creating new classes based on existing ones
- **Polymorphism** - One interface, multiple implementations
- **Abstraction** - Hiding complex implementation details

**Example:**
```java
// Encapsulation
class Car {
    private String engine; // Hidden data
    
    public void start() { // Public method
        engine = "running";
    }
}
```

---

## 4. What is polymorphism? Explain with examples.

**Answer:**
Polymorphism means "many forms" - same method name behaving differently based on the object.

**Two types:**
- **Compile-time** - Method overloading
- **Runtime** - Method overriding

**Examples:**
```java
// Method Overloading (Compile-time)
class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
}

// Method Overriding (Runtime)
class Animal {
    public void sound() { System.out.println("Animal sound"); }
}

class Dog extends Animal {
    public void sound() { System.out.println("Bark"); }
}

class Cat extends Animal {
    public void sound() { System.out.println("Meow"); }
}
```

---

## 5. What is encapsulation and how is it implemented in Java?

**Answer:**
Encapsulation is wrapping data and methods together and controlling access to them.

**Implementation:**
- Make variables **private**
- Provide **public getter/setter** methods
- Control access through methods

**Example:**
```java
class Student {
    private String name;
    private int age;
    
    // Getter
    public String getName() {
        return name;
    }
    
    // Setter with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
```

---

## 6. What is inheritance and what are its types?

**Answer:**
Inheritance allows a class to acquire properties and methods from another class.

**Types in Java:**
- **Single** - One child, one parent
- **Multilevel** - Chain of inheritance
- **Hierarchical** - Multiple children, one parent

**Note:** Java doesn't support multiple inheritance with classes (but supports with interfaces)

**Example:**
```java
// Single Inheritance
class Vehicle {
    protected String brand = "Ford";
}

class Car extends Vehicle {
    private String model = "Mustang";
    
    public void display() {
        System.out.println(brand + " " + model);
    }
}
```

---

## 7. What is an abstract class?

**Answer:**
An abstract class is a class that cannot be instantiated and may contain abstract methods (methods without implementation).

**Key Points:**
- Use **abstract** keyword
- Can have both abstract and concrete methods
- Cannot create objects directly
- Must be extended by subclasses

**Example:**
```java
abstract class Shape {
    // Abstract method
    public abstract void draw();
    
    // Concrete method
    public void display() {
        System.out.println("This is a shape");
    }
}

class Circle extends Shape {
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

// Usage
Circle c = new Circle(); // Valid
// Shape s = new Shape(); // Error - cannot instantiate
```

# Java Interview Questions - Data Types and Variables

## 1. What are primitive data types in Java?

**Answer:**
Primitive data types are the basic building blocks that store simple values directly in memory.

**8 Primitive Types:**
- **byte** - 8-bit integer (-128 to 127)
- **short** - 16-bit integer (-32,768 to 32,767)
- **int** - 32-bit integer (most commonly used)
- **long** - 64-bit integer (add 'L' suffix)
- **float** - 32-bit decimal (add 'f' suffix)
- **double** - 64-bit decimal (default for decimals)
- **char** - 16-bit Unicode character
- **boolean** - true or false

**Example:**
```java
int age = 25;
double salary = 50000.50;
char grade = 'A';
boolean isActive = true;
long population = 1000000L;
```

---

## 2. What is the difference between primitive and reference types?

**Answer:**
The main difference is how they store data and where they're stored in memory.

**Primitive Types:**
- Store actual values directly
- Stored in stack memory
- Fixed size
- Default values (0, false, null)

**Reference Types:**
- Store memory addresses (references)
- Objects stored in heap memory
- Variable size
- Default value is null

**Example:**
```java
// Primitive
int x = 10; // Stores value 10 directly

// Reference
String name = "John"; // Stores reference to "John" object
Integer num = 20; // Wrapper class - reference type
```

---

## 3. What is autoboxing and unboxing?

**Answer:**
Automatic conversion between primitive types and their wrapper classes.

**Autoboxing:**
- Primitive → Wrapper class automatically

**Unboxing:**
- Wrapper class → Primitive automatically

**Example:**
```java
// Autoboxing - int to Integer
Integer num = 10; // Compiler converts int to Integer

// Unboxing - Integer to int
int value = num; // Compiler converts Integer to int

// In collections
List<Integer> list = new ArrayList<>();
list.add(5); // Autoboxing: int 5 becomes Integer
int first = list.get(0); // Unboxing: Integer becomes int
```

---

## 4. What is the difference between == and equals() method?

**Answer:**
They compare different things depending on the data type.

**== Operator:**
- Compares references for objects
- Compares values for primitives

**equals() Method:**
- Compares actual content/values
- Can be overridden in classes

**Example:**
```java
// Primitives
int a = 5, b = 5;
System.out.println(a == b); // true (same values)

// Objects
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2); // false (different references)
System.out.println(s1.equals(s2)); // true (same content)

// String literals
String s3 = "Hello";
String s4 = "Hello";
System.out.println(s3 == s4); // true (same reference in pool)
```

---

## 5. What is the difference between String, StringBuilder, and StringBuffer?

**Answer:**
They differ in mutability and thread safety.

**String:**
- Immutable (cannot be changed)
- Thread-safe (because immutable)
- Creates new object for each operation

**StringBuilder:**
- Mutable (can be modified)
- Not thread-safe
- Faster for single-threaded operations

**StringBuffer:**
- Mutable (can be modified)
- Thread-safe (synchronized methods)
- Slower due to synchronization

**Example:**
```java
// String - creates new objects
String str = "Hello";
str = str + " World"; // Creates new String object

// StringBuilder - modifies existing
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World"); // Modifies same object

// StringBuffer - thread-safe version
StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World"); // Thread-safe modification
```

---

## 6. Why are strings immutable in Java?

**Answer:**
Strings are immutable for security, performance, and design reasons.

**Reasons:**
- **Security** - Prevents malicious code from changing strings
- **String Pool** - Enables memory optimization through sharing
- **Thread Safety** - No synchronization needed
- **Hashcode Caching** - Hash value calculated once and reused
- **Class Loading** - Class names are strings, must be secure

**Example:**
```java
String original = "Hello";
String modified = original.concat(" World");

System.out.println(original); // Still "Hello"
System.out.println(modified); // "Hello World"
// Original string unchanged - new object created
```

---

## 7. What is string pooling?

**Answer:**
String pooling is Java's memory optimization technique where identical string literals share the same memory location.

**How it works:**
- JVM maintains a pool of unique strings
- String literals are stored in this pool
- Duplicate literals reference the same object
- Saves memory and improves performance

**Example:**
```java
// String literals - stored in pool
String s1 = "Hello";
String s2 = "Hello";
System.out.println(s1 == s2); // true (same reference)

// New String objects - not in pool initially
String s3 = new String("Hello");
System.out.println(s1 == s3); // false (different references)

// intern() method adds to pool
String s4 = s3.intern();
System.out.println(s1 == s4); // true (now same reference)
```

---

## 8. What is the difference between final, finally, and finalize?

**Answer:**
Three different keywords with completely different purposes.

**final:**
- Keyword for constants, preventing inheritance/override
- Variables: cannot be reassigned
- Methods: cannot be overridden
- Classes: cannot be extended

**finally:**
- Block that always executes after try-catch
- Used for cleanup code
- Executes even if exception occurs

**finalize:**
- Method called by garbage collector
- Used for cleanup before object destruction
- Deprecated since Java 9

**Example:**
```java
// final
final int MAX_SIZE = 100; // Cannot be changed
final class FinalClass {} // Cannot be extended

// finally
try {
    int result = 10/0;
} catch (Exception e) {
    System.out.println("Error occurred");
} finally {
    System.out.println("Always executes"); // Cleanup code
}

// finalize (deprecated)
class MyClass {
    protected void finalize() throws Throwable {
        // Cleanup before garbage collection
        super.finalize();
    }
}
```

# Java Classes and Objects - Interview Q&A

## 1. What is a constructor in Java?

A constructor is a special method that initializes objects when they're created.

**Key Points:**
- Same name as the class
- No return type (not even void)
- Called automatically when object is created
- Can be overloaded

**Example:**
```java
public class Student {
    String name;
    int age;
    
    // Default constructor
    public Student() {
        name = "Unknown";
        age = 0;
    }
    
    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

## 2. What is constructor chaining?

Constructor chaining is calling one constructor from another constructor in the same class or parent class.

**Key Points:**
- Use `this()` to call another constructor in same class
- Use `super()` to call parent class constructor
- Must be the first statement in constructor

**Example:**
```java
public class Employee {
    String name;
    int id;
    double salary;
    
    public Employee() {
        this("Unknown", 0); // Calls parameterized constructor
    }
    
    public Employee(String name, int id) {
        this(name, id, 0.0); // Calls three-parameter constructor
    }
    
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
}
```

## 3. What is the difference between this and super keywords?

**this keyword:**
- Refers to current object
- Access current class members
- Call current class constructors

**super keyword:**
- Refers to parent class object
- Access parent class members
- Call parent class constructors

**Example:**
```java
class Animal {
    String name = "Animal";
    
    public void eat() {
        System.out.println("Animal eating");
    }
}

class Dog extends Animal {
    String name = "Dog";
    
    public void display() {
        System.out.println(this.name);  // Prints "Dog"
        System.out.println(super.name); // Prints "Animal"
    }
    
    public void eat() {
        super.eat(); // Calls parent method
        System.out.println("Dog eating");
    }
}
```

## 4. What is method overloading?

Method overloading means having multiple methods with the same name but different parameters in the same class.

**Key Points:**
- Same method name, different parameters
- Compile-time polymorphism
- Parameters can differ by number, type, or order

**Example:**
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

Method overriding means redefining a parent class method in the child class with the same signature.

**Key Points:**
- Same method signature as parent
- Runtime polymorphism
- Use @Override annotation
- Child method is called at runtime

**Example:**
```java
class Vehicle {
    public void start() {
        System.out.println("Vehicle starting");
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car starting with key");
    }
}

class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starting with kick");
    }
}
```

## 6. What is the difference between overloading and overriding?

| **Overloading** | **Overriding** |
|-----------------|----------------|
| Same class | Different classes (inheritance) |
| Different parameters | Same method signature |
| Compile-time | Runtime |
| Static polymorphism | Dynamic polymorphism |
| Can change return type | Must have same return type |

**Example:**
```java
// Overloading - Same class, different parameters
class MathUtils {
    public int multiply(int a, int b) { return a * b; }
    public double multiply(double a, double b) { return a * b; }
}

// Overriding - Different classes, same signature
class Shape {
    public void draw() { System.out.println("Drawing shape"); }
}

class Circle extends Shape {
    @Override
    public void draw() { System.out.println("Drawing circle"); }
}
```

## 7. What is dynamic method dispatch?

Dynamic method dispatch is the mechanism where the correct overridden method is called at runtime based on the actual object type, not the reference type.

**Key Points:**
- Runtime polymorphism
- Method call resolved at runtime
- Depends on actual object, not reference type
- Enables polymorphic behavior

**Example:**
```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal animal1 = new Cat(); // Reference type: Animal, Object type: Cat
        Animal animal2 = new Dog(); // Reference type: Animal, Object type: Dog
        
        animal1.makeSound(); // Prints "Cat meows" - Dynamic dispatch
        animal2.makeSound(); // Prints "Dog barks" - Dynamic dispatch
    }
}
```

# Java Inheritance Interview Questions & Answers

## 1. Why doesn't Java support multiple inheritance?

**Answer:**
• Java doesn't support multiple inheritance of classes to avoid complexity and ambiguity
• Main reasons:
  - **Diamond Problem**: Confusion when multiple parent classes have same method
  - **Complexity**: Makes code harder to understand and maintain
  - **Ambiguity**: Compiler can't decide which parent method to inherit

**Example:**
```java
// This is NOT allowed in Java
class A { void show() { } }
class B { void show() { } }
class C extends A, B { } // Compilation Error!
```

---

## 2. What is the diamond problem?

**Answer:**
• Diamond problem occurs when a class inherits from multiple classes that have a common parent
• Creates ambiguity about which method implementation to use
• Named "diamond" because the inheritance structure looks like a diamond shape

**Example Structure:**
```
    A
   / \
  B   C
   \ /
    D
```

**Code Example:**
```java
// If this was allowed (it's not in Java)
class Animal { void sound() { } }
class Dog extends Animal { void sound() { System.out.println("Bark"); } }
class Cat extends Animal { void sound() { System.out.println("Meow"); } }
class Hybrid extends Dog, Cat { } // Which sound() method to inherit?
```

---

## 3. How does Java solve the diamond problem?

**Answer:**
• Java solves it by **not allowing multiple inheritance of classes**
• Instead provides alternatives:
  - **Interfaces**: Can implement multiple interfaces
  - **Default methods**: Interface methods with implementation (Java 8+)
  - **Composition**: Use HAS-A relationship instead of IS-A

**Example:**
```java
interface Flyable { 
    default void fly() { System.out.println("Flying"); }
}
interface Swimmable { 
    default void swim() { System.out.println("Swimming"); }
}

class Duck implements Flyable, Swimmable {
    // Can implement both interfaces
    // If conflict, must override the method
}
```

---

## 4. Can you override static methods?

**Answer:**
• **No, you cannot override static methods**
• Static methods belong to the class, not to instances
• You can **hide** static methods (method hiding), but it's not overriding
• Static methods are resolved at compile time, not runtime

**Example:**
```java
class Parent {
    static void display() { System.out.println("Parent static"); }
}

class Child extends Parent {
    static void display() { System.out.println("Child static"); } // Method hiding, not overriding
}

// Usage:
Parent.display(); // Output: Parent static
Child.display();  // Output: Child static
Parent p = new Child();
p.display();      // Output: Parent static (not Child!)
```

---

## 5. What is covariant return type?

**Answer:**
• Covariant return type allows overriding method to return a **subtype** of the original return type
• Introduced in Java 5
• The return type can be more specific (narrower) than the parent's return type
• Must maintain IS-A relationship

**Example:**
```java
class Animal { }
class Dog extends Animal { }

class AnimalFactory {
    Animal createAnimal() {
        return new Animal();
    }
}

class DogFactory extends AnimalFactory {
    @Override
    Dog createAnimal() {  // Covariant return type - Dog is subtype of Animal
        return new Dog();
    }
}
```

**Another Example:**
```java
class Shape {
    Shape getShape() { return new Shape(); }
}

class Circle extends Shape {
    @Override
    Circle getShape() { return new Circle(); } // Valid covariant return
}
```

---

## 6. What is the difference between IS-A and HAS-A relationship?

**Answer:**

### IS-A Relationship (Inheritance)
• Represents **inheritance** relationship
• Uses `extends` keyword
• Child class IS-A type of parent class
• **"Kind of"** relationship

**Example:**
```java
class Vehicle { }
class Car extends Vehicle { } // Car IS-A Vehicle

class Animal { }
class Dog extends Animal { }  // Dog IS-A Animal
```

### HAS-A Relationship (Composition/Aggregation)
• Represents **composition** or **aggregation**
• One class contains reference to another class
• **"Part of"** or **"Has"** relationship
• More flexible than inheritance

**Example:**
```java
class Engine {
    void start() { System.out.println("Engine started"); }
}

class Car {
    private Engine engine; // Car HAS-A Engine
    
    public Car() {
        engine = new Engine();
    }
    
    void startCar() {
        engine.start();
    }
}

class Student {
    private Address address; // Student HAS-A Address
    private List<Course> courses; // Student HAS-A list of Courses
}
```

### Key Differences:
| IS-A | HAS-A |
|------|-------|
| Inheritance | Composition |
| Tight coupling | Loose coupling |
| "Kind of" | "Part of" |
| Less flexible | More flexible |
| Single inheritance limit | Multiple composition allowed |

**When to use:**
• **IS-A**: When there's a clear parent-child relationship
• **HAS-A**: When you need flexibility and want to avoid inheritance limitations

# Interfaces and Abstract Classes - Interview Questions & Answers

## 1. What is an interface in Java?

An interface is a contract that defines what methods a class must implement, without providing the implementation details.

**Key Points:**
• Contains abstract methods (by default)
• All variables are public, static, and final
• Classes implement interfaces using `implements` keyword
• Supports multiple inheritance
• Cannot be instantiated directly

**Example:**
```java
interface Animal {
    void makeSound();
    void move();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }
    
    public void move() {
        System.out.println("Running");
    }
}
```

---

## 2. What is an abstract class?

An abstract class is a class that cannot be instantiated and may contain both abstract and concrete methods.

**Key Points:**
• Uses `abstract` keyword
• Can have constructors, instance variables
• Can contain both abstract and concrete methods
• Classes extend abstract classes using `extends` keyword
• Supports single inheritance only

**Example:**
```java
abstract class Vehicle {
    String brand;
    
    public Vehicle(String brand) {
        this.brand = brand;
    }
    
    abstract void start();
    
    public void stop() {
        System.out.println("Vehicle stopped");
    }
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }
    
    void start() {
        System.out.println("Car started");
    }
}
```

---

## 3. What is the difference between interface and abstract class?

**Interface:**
• Multiple inheritance supported
• Only public abstract methods (before Java 8)
• Variables are public, static, final
• No constructors
• 100% abstraction (before Java 8)

**Abstract Class:**
• Single inheritance only
• Can have concrete and abstract methods
• Can have any type of variables
• Can have constructors
• 0-100% abstraction

**Example:**
```java
// Interface - multiple inheritance
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Flying"); }
    public void swim() { System.out.println("Swimming"); }
}

// Abstract class - single inheritance
abstract class Bird {
    abstract void makeSound();
    public void sleep() { System.out.println("Sleeping"); }
}
```

---

## 4. What are default methods in interfaces?

Default methods allow interfaces to have method implementations without breaking existing code.

**Key Points:**
• Introduced in Java 8
• Use `default` keyword
• Provide backward compatibility
• Can be overridden in implementing classes

**Example:**
```java
interface Calculator {
    int add(int a, int b);
    
    default int multiply(int a, int b) {
        return a * b;
    }
}

class SimpleCalculator implements Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    // multiply method is inherited from interface
}
```

---

## 5. What are static methods in interfaces?

Static methods in interfaces belong to the interface itself and cannot be overridden.

**Key Points:**
• Introduced in Java 8
• Called using interface name
• Cannot be overridden in implementing classes
• Provide utility methods

**Example:**
```java
interface MathUtils {
    static int square(int number) {
        return number * number;
    }
    
    static boolean isEven(int number) {
        return number % 2 == 0;
    }
}

// Usage
int result = MathUtils.square(5); // 25
boolean even = MathUtils.isEven(4); // true
```

---

## 6. What is marker interface?

A marker interface is an empty interface with no methods, used to mark or tag classes for special treatment.

**Key Points:**
• Contains no methods or constants
• Provides metadata about the class
• Used by JVM or frameworks for special processing
• Examples: Serializable, Cloneable, Remote

**Example:**
```java
// Marker interface
interface Printable {
    // No methods - just marks the class
}

class Document implements Printable {
    String content;
    
    public Document(String content) {
        this.content = content;
    }
}

// Usage in framework code
public void printDocument(Object obj) {
    if (obj instanceof Printable) {
        System.out.println("Printing: " + obj);
    }
}
```

---

## 7. What is functional interface?

A functional interface has exactly one abstract method and can be used with lambda expressions.

**Key Points:**
• Contains exactly one abstract method
• Can have default and static methods
• Annotated with `@FunctionalInterface` (optional)
• Used with lambda expressions and method references
• Examples: Runnable, Callable, Comparator

**Example:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
    
    // Can have default methods
    default void printResult(int result) {
        System.out.println("Result: " + result);
    }
}

// Usage with lambda
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

int sum = add.calculate(5, 3); // 8
int product = multiply.calculate(5, 3); // 15
```

---

## 8. Can an interface extend another interface?

Yes, an interface can extend one or more interfaces using the `extends` keyword.

**Key Points:**
• Interface can extend multiple interfaces
• Inherits all abstract methods from parent interfaces
• Implementing class must provide implementation for all methods
• Creates interface hierarchy

**Example:**
```java
interface Animal {
    void eat();
}

interface Mammal extends Animal {
    void breathe();
}

interface Pet extends Animal {
    void play();
}

// Multiple interface inheritance
interface Dog extends Mammal, Pet {
    void bark();
}

class GoldenRetriever implements Dog {
    public void eat() { System.out.println("Eating"); }
    public void breathe() { System.out.println("Breathing"); }
    public void play() { System.out.println("Playing"); }
    public void bark() { System.out.println("Barking"); }
}
```

---

## Quick Summary

**Remember:**
• Interface = Contract (what to do)
• Abstract Class = Partial implementation (what and how)
• Default methods = Backward compatibility
• Static methods = Utility functions
• Marker interface = Tagging mechanism
• Functional interface = Lambda expressions
• Interface inheritance = Multiple extends allowed

# Java Exception Handling - Interview Questions & Answers

## 1. What is an exception in Java?

An exception is an event that disrupts the normal flow of program execution. It's an object that represents an error or unexpected condition.

**Key Points:**
• Exceptions are runtime errors that can be handled
• They help maintain program stability
• Java uses exception objects to provide error information

**Example:**
```java
int result = 10 / 0; // ArithmeticException
String str = null;
int length = str.length(); // NullPointerException
```

---

## 2. What is the exception hierarchy in Java?

Java exceptions follow a class hierarchy with Throwable at the top.

**Hierarchy Structure:**
• `Throwable` (root class)
  • `Error` (system-level errors, not recoverable)
  • `Exception` (recoverable errors)
    • `RuntimeException` (unchecked exceptions)
    • Other exceptions (checked exceptions)

**Example:**
```java
// Error example (not handled by application)
// OutOfMemoryError, StackOverflowError

// Exception examples
IOException io = new IOException(); // Checked
RuntimeException re = new RuntimeException(); // Unchecked
```

---

## 3. What are checked and unchecked exceptions?

**Checked Exceptions:**
• Must be handled at compile time
• Compiler forces you to handle them
• Examples: IOException, SQLException, ClassNotFoundException

**Unchecked Exceptions:**
• Runtime exceptions, not checked at compile time
• Extend RuntimeException
• Examples: NullPointerException, ArrayIndexOutOfBoundsException

**Example:**
```java
// Checked exception - must handle
try {
    FileReader file = new FileReader("file.txt");
} catch (FileNotFoundException e) {
    System.out.println("File not found");
}

// Unchecked exception - optional handling
String str = null;
str.length(); // NullPointerException at runtime
```

---

## 4. What is the difference between throw and throws?

**throw:**
• Used to explicitly throw an exception
• Used inside method body
• Throws one exception at a time

**throws:**
• Used in method signature to declare exceptions
• Indicates method might throw exceptions
• Can declare multiple exceptions

**Example:**
```java
// throws - method declaration
public void readFile() throws IOException, FileNotFoundException {
    // throw - explicit exception throwing
    if (someCondition) {
        throw new IOException("File error");
    }
}
```

---

## 5. What is try-catch-finally block?

A mechanism to handle exceptions gracefully.

**Components:**
• `try` - contains code that might throw exception
• `catch` - handles specific exceptions
• `finally` - always executes (cleanup code)

**Example:**
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("This always executes");
}
```

---

## 6. What is try-with-resources?

Automatic resource management introduced in Java 7. Resources are automatically closed.

**Benefits:**
• Automatic resource cleanup
• Cleaner code
• Prevents resource leaks

**Example:**
```java
// Traditional way
FileReader file = null;
try {
    file = new FileReader("data.txt");
    // use file
} finally {
    if (file != null) file.close();
}

// Try-with-resources
try (FileReader file = new FileReader("data.txt")) {
    // use file - automatically closed
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 7. How do you create custom exceptions?

Create custom exceptions by extending Exception or RuntimeException classes.

**Steps:**
• Extend Exception (checked) or RuntimeException (unchecked)
• Add constructors
• Optionally add custom methods

**Example:**
```java
// Custom checked exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Custom unchecked exception
class InsufficientBalanceException extends RuntimeException {
    private double balance;
    
    public InsufficientBalanceException(String message, double balance) {
        super(message);
        this.balance = balance;
    }
    
    public double getBalance() {
        return balance;
    }
}

// Usage
public void validateAge(int age) throws InvalidAgeException {
    if (age < 18) {
        throw new InvalidAgeException("Age must be 18 or above");
    }
}
```

---

## 8. What is exception chaining?

Exception chaining allows you to associate one exception with another, preserving the original cause.

**Purpose:**
• Preserve original exception information
• Provide better debugging
• Maintain exception context

**Example:**
```java
public void processData() throws DataProcessingException {
    try {
        // Some database operation
        connection.executeQuery("SELECT * FROM users");
    } catch (SQLException e) {
        // Chain the original exception
        throw new DataProcessingException("Failed to process user data", e);
    }
}

// Custom exception with chaining
class DataProcessingException extends Exception {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Getting the original cause
try {
    processData();
} catch (DataProcessingException e) {
    Throwable originalCause = e.getCause(); // Gets SQLException
    e.printStackTrace(); // Shows full chain
}
```

---

## Quick Summary

**Remember these key points:**
• Always handle checked exceptions
• Use specific exception types in catch blocks
• Finally block always executes
• Try-with-resources for automatic cleanup
• Custom exceptions for business logic
• Exception chaining preserves error context

# Java Collections Framework - Interview Questions & Answers

## 1. What is Java Collections Framework?

**Answer:**
- It's a unified architecture for storing and manipulating groups of objects
- Provides interfaces, implementations, and algorithms for common data structures
- Key benefits:
  - Reduces programming effort
  - Increases performance
  - Provides interoperability between unrelated APIs

**Example:**
```java
List<String> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();
```

## 2. What is the difference between ArrayList and LinkedList?

**Answer:**
- **ArrayList**: Uses dynamic array internally
  - Fast random access (O(1))
  - Slow insertion/deletion in middle (O(n))
  - Better for frequent reads

- **LinkedList**: Uses doubly linked list
  - Slow random access (O(n))
  - Fast insertion/deletion anywhere (O(1))
  - Better for frequent modifications

**Example:**
```java
// ArrayList - good for accessing elements
List<String> arrayList = new ArrayList<>();
String element = arrayList.get(5); // Fast O(1)

// LinkedList - good for adding/removing
List<String> linkedList = new LinkedList<>();
linkedList.add(2, "new element"); // Fast O(1)
```

## 3. What is the difference between HashMap and TreeMap?

**Answer:**
- **HashMap**:
  - Hash table implementation
  - No ordering of keys
  - O(1) average time complexity
  - Allows null key and values

- **TreeMap**:
  - Red-Black tree implementation
  - Sorted order of keys
  - O(log n) time complexity
  - No null keys allowed

**Example:**
```java
// HashMap - unordered, faster
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("apple", 5); // O(1)

// TreeMap - sorted, slower
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("apple", 5); // O(log n), keys will be sorted
```

## 4. What is the difference between HashMap and Hashtable?

**Answer:**
- **HashMap**:
  - Not synchronized (not thread-safe)
  - Allows null key and values
  - Introduced in Java 1.2
  - Better performance in single-threaded apps

- **Hashtable**:
  - Synchronized (thread-safe)
  - No null keys or values allowed
  - Legacy class from Java 1.0
  - Slower due to synchronization overhead

**Example:**
```java
// HashMap - not thread-safe, allows nulls
Map<String, String> hashMap = new HashMap<>();
hashMap.put(null, "value"); // OK
hashMap.put("key", null);   // OK

// Hashtable - thread-safe, no nulls
Map<String, String> hashtable = new Hashtable<>();
// hashtable.put(null, "value"); // NullPointerException
```

## 5. How does HashMap work internally?

**Answer:**
- Uses array of buckets (Node array)
- Hash function determines bucket index
- Process:
  - Calculate hash code of key
  - Find bucket using hash % array_length
  - Store key-value pair in that bucket
  - Handle collisions using linked list or red-black tree (Java 8+)

**Example:**
```java
// Internal working concept
Map<String, Integer> map = new HashMap<>();
map.put("key1", 100); 
// 1. "key1".hashCode() calculated
// 2. Index = hash % bucket_array_length
// 3. Store at that index
```

## 6. What is hash collision and how is it handled?

**Answer:**
- **Hash Collision**: When two different keys produce the same hash code
- **Handling methods**:
  - **Chaining**: Store multiple entries in same bucket using linked list
  - **Tree Structure**: Convert to red-black tree when chain length > 8 (Java 8+)
  - **Open Addressing**: Find next available slot

**Example:**
```java
// Collision scenario
Map<String, String> map = new HashMap<>();
map.put("Aa", "value1");  // hashCode might be same
map.put("BB", "value2");  // as these strings
// Both stored in same bucket using chaining
```

## 7. What is the difference between fail-fast and fail-safe iterators?

**Answer:**
- **Fail-Fast**:
  - Throws ConcurrentModificationException if collection modified during iteration
  - Used by ArrayList, HashMap, HashSet
  - Works on original collection

- **Fail-Safe**:
  - Creates copy of collection, iterates over copy
  - No exception thrown if original modified
  - Used by ConcurrentHashMap, CopyOnWriteArrayList

**Example:**
```java
// Fail-fast - throws exception
List<String> list = new ArrayList<>();
list.add("a"); list.add("b");
for(String s : list) {
    list.remove(s); // ConcurrentModificationException
}

// Fail-safe - no exception
ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
for(String key : map.keySet()) {
    map.remove(key); // No exception
}
```

## 8. What is the difference between Comparable and Comparator?

**Answer:**
- **Comparable**:
  - Interface implemented by class itself
  - Provides natural ordering
  - compareTo() method
  - Single sorting logic

- **Comparator**:
  - External interface for custom sorting
  - Multiple sorting strategies possible
  - compare() method
  - Can sort classes you don't own

**Example:**
```java
// Comparable - natural ordering
class Student implements Comparable<Student> {
    String name;
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}

// Comparator - custom ordering
Comparator<Student> byAge = (s1, s2) -> s1.age - s2.age;
Collections.sort(students, byAge);
```

## 9. What is WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?

**Answer:**

### WeakHashMap
- Keys are weak references
- Entries automatically removed when key is garbage collected
- Good for caches

```java
WeakHashMap<String, String> weakMap = new WeakHashMap<>();
```

### IdentityHashMap
- Uses == instead of equals() for key comparison
- Violates Map contract intentionally
- Used for special cases

```java
IdentityHashMap<String, String> identityMap = new IdentityHashMap<>();
```

### LinkedHashMap
- Maintains insertion order or access order
- Extends HashMap with doubly-linked list
- Good for LRU caches

```java
LinkedHashMap<String, String> linkedMap = new LinkedHashMap<>();
```

### PriorityQueue
- Heap-based priority queue
- Elements ordered by natural ordering or Comparator
- Not thread-safe

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(5); pq.offer(1); pq.offer(3);
System.out.println(pq.poll()); // Output: 1 (smallest first)
```

# Multithreading and Synchronization Interview Questions & Answers

## 1. What is multithreading?

Multithreading is the ability of a program to execute multiple threads concurrently within a single process.

• **Key Points:**
  - Multiple threads share the same memory space
  - Improves performance by utilizing CPU cores efficiently
  - Allows concurrent execution of tasks

• **Example:**
```java
// Main thread creates and starts worker threads
public class MultithreadingExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Task 1"));
        Thread t2 = new Thread(() -> System.out.println("Task 2"));
        t1.start();
        t2.start();
    }
}
```

---

## 2. How do you create threads in Java?

There are two main ways to create threads in Java.

• **Method 1: Extending Thread class**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + getName());
    }
}

MyThread t = new MyThread();
t.start();
```

• **Method 2: Implementing Runnable interface**
```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}

Thread t = new Thread(new MyTask());
t.start();
```

• **Method 3: Lambda expression (Java 8+)**
```java
Thread t = new Thread(() -> System.out.println("Lambda thread"));
t.start();
```

---

## 3. What is the difference between extending Thread and implementing Runnable?

The main differences are inheritance flexibility and design principles.

• **Extending Thread:**
  - Single inheritance limitation (can't extend other classes)
  - Direct access to Thread methods
  - Tightly couples task with thread management

• **Implementing Runnable:**
  - Can extend other classes (multiple inheritance support)
  - Better separation of concerns
  - Preferred approach for most cases

• **Example:**
```java
// Runnable approach - more flexible
class DatabaseTask implements Runnable {
    public void run() {
        // Database operations
    }
}

// Can be used with different thread pools
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(new DatabaseTask());
```

---

## 4. What are the states of a thread?

A thread goes through several states during its lifecycle.

• **Thread States:**
  - **NEW**: Thread created but not started
  - **RUNNABLE**: Thread executing or ready to execute
  - **BLOCKED**: Thread blocked waiting for monitor lock
  - **WAITING**: Thread waiting indefinitely for another thread
  - **TIMED_WAITING**: Thread waiting for specified time
  - **TERMINATED**: Thread completed execution

• **Example:**
```java
Thread t = new Thread(() -> {
    try {
        Thread.sleep(1000); // TIMED_WAITING
    } catch (InterruptedException e) {}
});

System.out.println(t.getState()); // NEW
t.start();
System.out.println(t.getState()); // RUNNABLE
```

---

## 5. What is synchronization in Java?

Synchronization controls access to shared resources to prevent data corruption in multithreaded environments.

• **Key Benefits:**
  - Prevents race conditions
  - Ensures thread safety
  - Maintains data consistency

• **Types:**
  - Method synchronization
  - Block synchronization
  - Static synchronization

• **Example:**
```java
class Counter {
    private int count = 0;
    
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
}
```

---

## 6. What is deadlock and how do you prevent it?

Deadlock occurs when two or more threads are blocked forever, waiting for each other to release resources.

• **Deadlock Conditions:**
  - Mutual exclusion
  - Hold and wait
  - No preemption
  - Circular wait

• **Prevention Strategies:**
  - Always acquire locks in same order
  - Use timeout for lock acquisition
  - Avoid nested locks when possible

• **Example:**
```java
// Deadlock scenario
class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // Prevention: Always acquire locks in same order
    public void method1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // Work here
            }
        }
    }
    
    public void method2() {
        synchronized(lock1) { // Same order as method1
            synchronized(lock2) {
                // Work here
            }
        }
    }
}
```

---

## 7. What is volatile keyword?

Volatile ensures that changes to a variable are immediately visible to all threads.

• **Key Features:**
  - Prevents caching of variable values
  - Ensures visibility across threads
  - Provides happens-before guarantee

• **When to use:**
  - Simple flags or status variables
  - When only one thread writes, others read

• **Example:**
```java
class VolatileExample {
    private volatile boolean running = true;
    
    public void stop() {
        running = false; // Immediately visible to all threads
    }
    
    public void doWork() {
        while (running) {
            // Work continues until running becomes false
        }
    }
}
```

---

## 8. What is the difference between synchronized and volatile?

Both provide thread safety but work differently.

• **Synchronized:**
  - Provides mutual exclusion (locking)
  - Ensures atomicity of operations
  - Can cause thread blocking
  - Heavier performance overhead

• **Volatile:**
  - Provides visibility guarantee only
  - No locking mechanism
  - Cannot ensure atomicity of compound operations
  - Lighter performance overhead

• **Example:**
```java
class Comparison {
    private volatile int volatileCounter = 0;
    private int syncCounter = 0;
    
    // Volatile - not thread-safe for increment
    public void incrementVolatile() {
        volatileCounter++; // Not atomic!
    }
    
    // Synchronized - thread-safe
    public synchronized void incrementSync() {
        syncCounter++; // Atomic operation
    }
}
```

---

## 9. What is race condition and atomic operation?

Race condition occurs when multiple threads access shared data simultaneously, leading to unpredictable results.

• **Race Condition:**
  - Outcome depends on thread scheduling
  - Results in data corruption
  - Occurs with non-atomic operations

• **Atomic Operation:**
  - Indivisible operation
  - Completes entirely or not at all
  - Thread-safe by nature

• **Example:**
```java
import java.util.concurrent.atomic.AtomicInteger;

class RaceConditionExample {
    private int counter = 0; // Race condition prone
    private AtomicInteger atomicCounter = new AtomicInteger(0); // Thread-safe
    
    // Race condition
    public void increment() {
        counter++; // Read-modify-write (not atomic)
    }
    
    // Atomic operation
    public void atomicIncrement() {
        atomicCounter.incrementAndGet(); // Atomic
    }
}
```

• **Common Atomic Classes:**
  - AtomicInteger, AtomicLong
  - AtomicBoolean, AtomicReference
  - Provide lock-free thread-safe operations


  # Advanced Concurrency Interview Questions & Answers

## 1. What is ExecutorService?

**ExecutorService is a framework that manages thread pools and executes tasks asynchronously.**

• **Purpose**: Simplifies thread management and task execution
• **Benefits**: Reuses threads, controls concurrency, handles lifecycle
• **Key Methods**: submit(), execute(), shutdown()

**Example:**
```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Task executed"));
executor.shutdown();
```

---

## 2. What are the types of thread pools?

**Java provides several built-in thread pool types through Executors class.**

• **FixedThreadPool**: Fixed number of threads
  ```java
  ExecutorService fixed = Executors.newFixedThreadPool(5);
  ```

• **CachedThreadPool**: Creates threads as needed, reuses idle ones
  ```java
  ExecutorService cached = Executors.newCachedThreadPool();
  ```

• **SingleThreadExecutor**: Single worker thread
  ```java
  ExecutorService single = Executors.newSingleThreadExecutor();
  ```

• **ScheduledThreadPool**: For delayed/periodic tasks
  ```java
  ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
  ```

---

## 3. What is Future and CompletableFuture?

**Future represents the result of an asynchronous computation.**

### Future:
• **Basic async result holder**
• **Blocking operations**: get() blocks until result is ready
• **Limited functionality**: Can't chain operations

```java
Future<String> future = executor.submit(() -> "Hello");
String result = future.get(); // Blocks until complete
```

### CompletableFuture:
• **Enhanced Future with non-blocking operations**
• **Chainable**: Can compose multiple async operations
• **Callback support**: Handle completion without blocking

```java
CompletableFuture<String> cf = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenAccept(System.out::println);
```

---

## 4. What is CountDownLatch?

**CountDownLatch is a synchronization aid that allows threads to wait until a set of operations complete.**

• **Use Case**: Wait for multiple threads to finish before proceeding
• **One-time use**: Cannot be reset once count reaches zero
• **Methods**: countDown(), await()

**Example:**
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

latch.await(); // Wait for all 3 tasks to complete
System.out.println("All tasks finished");
```

---

## 5. What is ReentrantLock?

**ReentrantLock is an explicit lock that provides more flexibility than synchronized blocks.**

• **Reentrant**: Same thread can acquire the lock multiple times
• **Explicit control**: Manual lock/unlock operations
• **Advanced features**: Trylock, timed locking, interruptible locking

**Example:**
```java
ReentrantLock lock = new ReentrantLock();

public void method() {
    lock.lock();
    try {
        // Critical section
        System.out.println("Protected code");
    } finally {
        lock.unlock(); // Always unlock in finally
    }
}
```

---

## 6. What is the difference between ReentrantLock and synchronized?

**Both provide thread synchronization but with different capabilities.**

### ReentrantLock:
• **Explicit locking**: Manual lock/unlock
• **Advanced features**: tryLock(), lockInterruptibly()
• **Fairness option**: Can ensure FIFO thread access
• **Condition variables**: Multiple wait/notify conditions

```java
ReentrantLock lock = new ReentrantLock(true); // Fair lock
if (lock.tryLock(5, TimeUnit.SECONDS)) {
    try {
        // Work
    } finally {
        lock.unlock();
    }
}
```

### Synchronized:
• **Implicit locking**: Automatic lock/unlock
• **Simpler syntax**: Built into language
• **JVM optimized**: Better performance for simple cases
• **Single condition**: Only one wait/notify per object

```java
synchronized (this) {
    // Critical section - automatic unlock
}
```

### Key Differences:
| Feature | ReentrantLock | Synchronized |
|---------|---------------|--------------|
| **Syntax** | Explicit | Implicit |
| **Trylock** | Yes | No |
| **Timeout** | Yes | No |
| **Fairness** | Configurable | No |
| **Conditions** | Multiple | Single |
| **Performance** | Good for complex scenarios | Better for simple cases |

**When to use:**
• **ReentrantLock**: Complex locking scenarios, need timeout/trylock
• **Synchronized**: Simple mutual exclusion, better readability


# JVM and Memory Management Interview Questions & Answers

## 1. What are the different memory areas in JVM?

**Answer:**
The JVM has several distinct memory areas:

• **Heap Memory** - Where objects are stored
  - Young Generation (Eden, S0, S1)
  - Old Generation (Tenured)

• **Stack Memory** - Method calls and local variables
  - Each thread has its own stack

• **Method Area/Metaspace** - Class metadata and constants
  - Replaced PermGen in Java 8+

• **PC Register** - Program counter for each thread

• **Native Method Stack** - For native method calls

**Example:**
```java
String name = "John"; // Stored in heap
int age = 25;         // Stored in stack (local variable)
```

---

## 2. What is the difference between heap and stack?

**Answer:**
Key differences between heap and stack:

• **Stack Memory:**
  - Stores method calls and local variables
  - Thread-specific (each thread has own stack)
  - Faster access
  - Automatically managed
  - Limited size

• **Heap Memory:**
  - Stores objects and instance variables
  - Shared among all threads
  - Slower access than stack
  - Managed by garbage collector
  - Larger size

**Example:**
```java
public void method() {
    int x = 10;           // Stack - local variable
    String str = new String("Hello"); // str reference in stack, object in heap
}
```

---

## 3. What is the difference between PermGen and Metaspace?

**Answer:**
PermGen was replaced by Metaspace in Java 8:

• **PermGen (Before Java 8):**
  - Fixed size memory area
  - Stored class metadata
  - Could cause OutOfMemoryError easily
  - Part of heap memory

• **Metaspace (Java 8+):**
  - Dynamic size (uses native memory)
  - Stores class metadata
  - Auto-expands as needed
  - Not part of heap memory
  - Better memory management

**Example:**
```java
// Before Java 8: -XX:PermSize=64m -XX:MaxPermSize=128m
// Java 8+: -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m
```

---

## 4. What is garbage collection?

**Answer:**
Garbage Collection (GC) is automatic memory management:

• **Purpose:**
  - Automatically frees unused memory
  - Removes objects with no references
  - Prevents memory leaks

• **How it works:**
  - Identifies unreachable objects
  - Marks them for deletion
  - Reclaims memory space

• **Benefits:**
  - Automatic memory management
  - Prevents OutOfMemoryError
  - Developer doesn't need to manually free memory

**Example:**
```java
String str = new String("Hello"); // Object created
str = null; // Object becomes eligible for GC
System.gc(); // Suggests GC (not guaranteed)
```

---

## 5. What are the types of garbage collectors?

**Answer:**
Different GC algorithms for different needs:

• **Serial GC:**
  - Single-threaded
  - Good for small applications
  - `-XX:+UseSerialGC`

• **Parallel GC:**
  - Multi-threaded
  - Default for server applications
  - `-XX:+UseParallelGC`

• **G1 GC:**
  - Low-latency collector
  - Good for large heaps
  - `-XX:+UseG1GC`

• **ZGC/Shenandoah:**
  - Ultra-low latency
  - For very large heaps
  - `-XX:+UseZGC`

**Example:**
```bash
java -XX:+UseG1GC -Xmx4g MyApplication
```

---

## 6. What is generational garbage collection?

**Answer:**
GC strategy based on object age:

• **Young Generation:**
  - New objects created here
  - Most objects die young
  - Frequent, fast GC

• **Old Generation:**
  - Long-lived objects promoted here
  - Less frequent GC
  - More expensive to collect

• **Survival Process:**
  - Objects start in Eden space
  - Survivors move to S0/S1
  - After several cycles, promoted to Old Gen

**Example:**
```java
// Young objects (likely to be GC'd soon)
for(int i = 0; i < 1000; i++) {
    String temp = "temp" + i; // Dies after loop
}

// Old objects (long-lived)
static List<String> cache = new ArrayList<>(); // Lives long
```

---

## 7. What is the difference between minor GC and major GC?

**Answer:**
Different types of garbage collection cycles:

• **Minor GC:**
  - Cleans Young Generation only
  - Happens frequently (seconds/minutes)
  - Fast execution
  - Low impact on application

• **Major GC:**
  - Cleans Old Generation
  - Happens less frequently
  - Slower execution
  - Can cause "stop-the-world" pauses

• **Full GC:**
  - Cleans entire heap
  - Most expensive operation
  - Should be minimized

**Example:**
```java
// Triggers minor GC frequently
for(int i = 0; i < 10000; i++) {
    new Object(); // Creates many short-lived objects
}

// May trigger major GC
static List<Object> longLived = new ArrayList<>(); // Accumulates objects
```

---

## 8. What is metaspace?

**Answer:**
Metaspace is the memory area for class metadata in Java 8+:

• **Purpose:**
  - Stores class definitions and metadata
  - Replaces PermGen space
  - Holds constant pool information

• **Characteristics:**
  - Uses native memory (not heap)
  - Dynamically resizable
  - Garbage collected when classes unloaded

• **Configuration:**
  - `-XX:MetaspaceSize` - Initial size
  - `-XX:MaxMetaspaceSize` - Maximum size

**Example:**
```java
// Class metadata stored in Metaspace
public class Employee {
    private String name;    // Field metadata in Metaspace
    public void work() {}   // Method metadata in Metaspace
}
```

---

## 9. What are GC roots?

**Answer:**
GC roots are starting points for garbage collection reachability analysis:

• **Types of GC Roots:**
  - Local variables in stack
  - Static variables
  - JNI references
  - Thread objects
  - System class loader references

• **How they work:**
  - GC starts from roots
  - Traces all reachable objects
  - Unreachable objects are garbage collected

• **Importance:**
  - Determines what objects to keep
  - Prevents accidental deletion of active objects

**Example:**
```java
public class GCRootExample {
    static List<String> staticList = new ArrayList<>(); // GC Root (static)
    
    public void method() {
        String local = "test"; // GC Root (local variable)
        staticList.add(local); // Object reachable from GC root
    }
}
```

---

## Summary

Understanding JVM memory management is crucial for:
- Writing efficient Java applications
- Debugging memory issues
- Optimizing application performance
- Choosing appropriate GC strategies

**Key Takeaway:** Modern JVM handles most memory management automatically, but understanding these concepts helps in optimization and troubleshooting.

# Java I/O Interview Questions & Answers

## 1. What are the different ways to read a file in Java?

There are several ways to read files in Java:

• **FileInputStream** - For reading raw bytes
• **FileReader** - For reading character data
• **BufferedReader** - For efficient line-by-line reading
• **Scanner** - For parsing different data types
• **Files.readAllLines()** - For reading entire file at once (Java 7+)
• **Files.lines()** - For streaming large files (Java 8+)

**Examples:**
```java
// Using BufferedReader
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
}

// Using Files (Java 8+)
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// Using Scanner
Scanner scanner = new Scanner(new File("file.txt"));
while (scanner.hasNextLine()) {
    String line = scanner.nextLine();
}
```

---

## 2. What is the difference between InputStream and Reader?

**InputStream:**
• Handles **raw bytes** (binary data)
• Base class for all byte input streams
• Methods return int (0-255) or byte arrays
• Used for images, videos, executables

**Reader:**
• Handles **character data** (text)
• Base class for all character input streams
• Automatically handles character encoding
• Used for text files

**Examples:**
```java
// InputStream - for binary data
FileInputStream fis = new FileInputStream("image.jpg");
int byteData = fis.read(); // reads raw bytes

// Reader - for text data
FileReader fr = new FileReader("text.txt");
int charData = fr.read(); // reads characters
```

---

## 3. What is BufferedReader and BufferedWriter?

**BufferedReader/BufferedWriter** are wrapper classes that add buffering capability:

• **Reduces system calls** by reading/writing data in chunks
• **Improves performance** significantly for frequent I/O operations
• **Default buffer size** is 8192 characters
• **readLine()** method for convenient line reading

**Examples:**
```java
// BufferedReader
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// BufferedWriter
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello World");
    bw.newLine();
    bw.flush(); // force write to disk
}
```

---

## 4. How do you handle large files efficiently?

For large files, use these strategies:

• **Streaming approach** - Process line by line instead of loading entire file
• **BufferedReader/Writer** - Use buffering to reduce I/O calls
• **Files.lines()** - Java 8 Stream API for memory-efficient processing
• **Memory mapping** - Use NIO for very large files
• **Chunked processing** - Read in fixed-size chunks

**Examples:**
```java
// Streaming with Java 8
try (Stream<String> lines = Files.lines(Paths.get("largefile.txt"))) {
    lines.filter(line -> line.contains("keyword"))
         .forEach(System.out::println);
}

// Chunked reading
try (FileInputStream fis = new FileInputStream("largefile.dat")) {
    byte[] buffer = new byte[8192];
    int bytesRead;
    while ((bytesRead = fis.read(buffer)) != -1) {
        // Process chunk
    }
}
```

---

## 5. What is NIO in Java?

**NIO (New I/O)** introduced in Java 1.4:

• **Non-blocking I/O** - Threads don't wait for I/O operations
• **Channel-based** - Uses channels instead of streams
• **Buffer-oriented** - Data read into buffers
• **Selectors** - Single thread can monitor multiple channels
• **Memory mapping** - Direct access to file system

**Key Components:**
• **Channels** - FileChannel, SocketChannel
• **Buffers** - ByteBuffer, CharBuffer
• **Selectors** - For multiplexed I/O

**Example:**
```java
// NIO file reading
try (FileChannel channel = FileChannel.open(Paths.get("file.txt"))) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int bytesRead = channel.read(buffer);
    buffer.flip();
    // Process buffer data
}
```

---

## 6. What is the difference between IO and NIO?

| **Traditional I/O** | **NIO** |
|-------------------|---------|
| **Blocking** - Thread waits | **Non-blocking** - Thread continues |
| **Stream-oriented** - Sequential | **Buffer-oriented** - Random access |
| **One thread per connection** | **One thread handles multiple connections** |
| **Simpler API** | **More complex but powerful** |
| **Good for few connections** | **Good for many connections** |

**Examples:**
```java
// Traditional I/O (blocking)
ServerSocket server = new ServerSocket(8080);
Socket client = server.accept(); // blocks until connection

// NIO (non-blocking)
ServerSocketChannel server = ServerSocketChannel.open();
server.configureBlocking(false);
SocketChannel client = server.accept(); // returns immediately
```

---

## 7. When would you use NIO over traditional I/O?

**Use NIO when:**
• **High concurrency** - Many simultaneous connections
• **Large files** - Memory mapping for better performance
• **Network servers** - Chat servers, web servers
• **Real-time applications** - Gaming, trading systems
• **Resource constraints** - Limited threads available

**Use Traditional I/O when:**
• **Simple applications** - Basic file operations
• **Few connections** - Desktop applications
• **Sequential processing** - Log file processing
• **Rapid development** - Simpler API and debugging

**Example Use Cases:**
```java
// NIO for high-performance server
Selector selector = Selector.open();
ServerSocketChannel server = ServerSocketChannel.open();
server.configureBlocking(false);
server.register(selector, SelectionKey.OP_ACCEPT);

// Traditional I/O for simple file copy
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
```

---

## Quick Reference

**File Reading Performance (Best to Worst):**
1. `Files.readAllBytes()` - Small files
2. `BufferedReader` - Line-by-line processing
3. `FileChannel` - Large files with NIO
4. `FileReader` - Simple character reading
5. `FileInputStream` - Raw byte reading

**Memory Usage:**
• **Lowest**: Streaming with `Files.lines()`
• **Moderate**: `BufferedReader`
• **Highest**: `Files.readAllLines()` (loads entire file)


# Java Generics Interview Questions & Answers

## 1. What are generics in Java?

**Answer:**
• Generics allow you to write type-safe code by parameterizing types
• They enable classes, interfaces, and methods to work with different data types while maintaining compile-time type safety
• Introduced in Java 5 to eliminate ClassCastException at runtime

**Example:**
```java
// Without generics (old way)
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // Manual casting required

// With generics
List<String> list = new ArrayList<String>();
list.add("Hello");
String str = list.get(0); // No casting needed
```

---

## 2. Why were generics introduced?

**Answer:**
• **Type Safety**: Catch type errors at compile time instead of runtime
• **Eliminate Casting**: No need for explicit type casting
• **Code Clarity**: Makes code more readable and self-documenting
• **Performance**: Reduces overhead of boxing/unboxing and casting

**Example:**
```java
// Before generics - Runtime error possible
List list = new ArrayList();
list.add("String");
list.add(123);
String str = (String) list.get(1); // ClassCastException at runtime

// With generics - Compile time error
List<String> list = new ArrayList<String>();
list.add("String");
list.add(123); // Compile error - cannot add Integer to String list
```

---

## 3. What is type erasure?

**Answer:**
• Type erasure removes generic type information at runtime
• Generic types are replaced with their raw types or Object during compilation
• This ensures backward compatibility with pre-Java 5 code
• At runtime, `List<String>` and `List<Integer>` are just `List`

**Example:**
```java
List<String> stringList = new ArrayList<String>();
List<Integer> intList = new ArrayList<Integer>();

// At runtime, both are just ArrayList
System.out.println(stringList.getClass() == intList.getClass()); // true

// Cannot do this due to type erasure
// if (list instanceof List<String>) // Compile error
if (list instanceof List) // This works
```

---

## 4. What is the difference between <? extends T> and <? super T>?

**Answer:**
• **`<? extends T>`** (Upper Bounded Wildcard):
  - Accepts T and all its subtypes
  - Used for reading data (Producer)
  - Cannot add elements (except null)

• **`<? super T>`** (Lower Bounded Wildcard):
  - Accepts T and all its supertypes
  - Used for writing data (Consumer)
  - Can add T and its subtypes

**Example:**
```java
// Upper bounded - can read, cannot write
List<? extends Number> numbers = new ArrayList<Integer>();
Number num = numbers.get(0); // OK - reading
// numbers.add(10); // Compile error - cannot write

// Lower bounded - can write, limited reading
List<? super Integer> integers = new ArrayList<Number>();
integers.add(10); // OK - writing Integer
integers.add(20); // OK - writing Integer
Object obj = integers.get(0); // Can only read as Object
```

---

## 5. What is PECS principle?

**Answer:**
• **PECS**: Producer Extends, Consumer Super
• **Producer Extends**: Use `<? extends T>` when you only read from the collection
• **Consumer Super**: Use `<? super T>` when you only write to the collection
• Helps decide which wildcard to use in method parameters

**Example:**
```java
// Producer - only reading from source
public static <T> void copy(List<? super T> dest, List<? extends T> src) {
    for (T item : src) {
        dest.add(item); // src produces, dest consumes
    }
}

// Usage
List<Number> numbers = new ArrayList<>();
List<Integer> integers = Arrays.asList(1, 2, 3);
copy(numbers, integers); // integers produces, numbers consumes
```

---

## 6. What are the limitations of generics?

**Answer:**
• **Cannot instantiate generic types**: `new T()` is not allowed
• **Cannot create arrays of generic types**: `T[] array = new T[10]` is invalid
• **Cannot use primitives**: Must use wrapper classes (`List<int>` is invalid)
• **Cannot use static fields**: Static context cannot access type parameters
• **Type erasure limitations**: Cannot check instance of parameterized types at runtime

**Example:**
```java
public class GenericClass<T> {
    // These are NOT allowed:
    // private T instance = new T(); // Cannot instantiate
    // private T[] array = new T[10]; // Cannot create array
    // private static T staticField; // Cannot use in static context
    
    // These ARE allowed:
    private List<T> list = new ArrayList<>(); // OK
    private T field; // OK
    
    public void method(T param) {
        // if (param instanceof T) // Cannot check instanceof
        if (param instanceof Object) // This works
    }
}

// Must use wrapper classes
List<Integer> intList = new ArrayList<>(); // OK
// List<int> primitiveList; // Compile error
```

# Java Annotations and Reflection - Interview Questions & Answers

## 1. What are annotations in Java?

Annotations are metadata that provide information about code without affecting its execution.

• **Purpose**: Add metadata to classes, methods, fields, parameters
• **Compile-time**: Used by compiler and development tools
• **Runtime**: Can be processed during execution
• **Syntax**: Start with @ symbol

**Example:**
```java
@Override
public String toString() {
    return "MyClass";
}

@Deprecated
public void oldMethod() {
    // legacy code
}
```

## 2. What are built-in annotations?

Java provides several predefined annotations for common use cases.

• **@Override**: Ensures method overrides parent method
• **@Deprecated**: Marks code as outdated
• **@SuppressWarnings**: Suppresses compiler warnings
• **@FunctionalInterface**: Marks interface as functional
• **@SafeVarargs**: Suppresses varargs warnings

**Example:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

@SuppressWarnings("unchecked")
List rawList = new ArrayList();
```

## 3. How do you create custom annotations?

Custom annotations are created using @interface keyword with meta-annotations.

• **@interface**: Defines annotation type
• **Elements**: Can have methods (elements)
• **Default values**: Optional default values
• **Meta-annotations**: Control annotation behavior

**Example:**
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {
    String value() default "";
    boolean enabled() default true;
}

// Usage
@LogExecutionTime(value = "database operation", enabled = true)
public void saveData() {
    // method implementation
}
```

## 4. What is retention policy?

Retention policy determines how long annotations are retained in the program lifecycle.

• **SOURCE**: Discarded by compiler (e.g., @Override)
• **CLASS**: Retained in bytecode, not at runtime
• **RUNTIME**: Available at runtime via reflection

**Example:**
```java
@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeAnnotation {
    String value();
}

@Retention(RetentionPolicy.SOURCE)
public @interface CompileTimeAnnotation {
    String message();
}
```

## 5. What is the difference between @Override and @Overload?

There's no @Overload annotation in Java - only @Override exists.

• **@Override**: Ensures method overrides superclass method
• **Overloading**: Multiple methods with same name, different parameters
• **Overriding**: Subclass provides specific implementation of superclass method
• **No annotation needed**: For method overloading

**Example:**
```java
class Parent {
    public void display() { }
}

class Child extends Parent {
    @Override
    public void display() { } // Overriding
    
    public void display(String msg) { } // Overloading (no annotation)
    public void display(int num) { }    // Overloading (no annotation)
}
```

## 6. What is reflection in Java?

Reflection allows examining and manipulating classes, methods, and fields at runtime.

• **Runtime inspection**: Analyze class structure dynamically
• **Dynamic invocation**: Call methods without compile-time knowledge
• **Access modifiers**: Can access private members
• **Class loading**: Work with dynamically loaded classes

**Example:**
```java
Class<?> clazz = String.class;
Method[] methods = clazz.getMethods();

// Get specific method
Method lengthMethod = clazz.getMethod("length");
int result = (int) lengthMethod.invoke("Hello");
```

## 7. When should you use reflection?

Reflection is useful for frameworks, libraries, and dynamic programming scenarios.

• **Frameworks**: Spring, Hibernate use reflection extensively
• **Serialization**: JSON/XML libraries
• **Testing**: JUnit, Mockito frameworks
• **Plugin systems**: Dynamic class loading
• **Avoid in regular code**: Performance and complexity concerns

**Example:**
```java
// Framework scenario - dependency injection
@Autowired
private UserService userService;

// Framework uses reflection to inject dependencies
Field field = clazz.getDeclaredField("userService");
field.setAccessible(true);
field.set(instance, serviceInstance);
```

## 8. What are the performance implications of reflection?

Reflection is significantly slower than direct method calls.

• **Method lookup**: Finding methods by name is expensive
• **Security checks**: Additional permission checks
• **Optimization barriers**: JVM can't optimize reflective calls
• **Memory overhead**: Additional metadata storage

**Performance tips:**
```java
// Cache Method objects
private static final Method TO_STRING_METHOD;
static {
    try {
        TO_STRING_METHOD = Object.class.getMethod("toString");
    } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
    }
}

// Use MethodHandle for better performance (Java 7+)
MethodHandle handle = MethodHandles.lookup()
    .findVirtual(String.class, "length", MethodType.methodType(int.class));
```

## 9. What are the security implications of reflection?

Reflection can bypass access controls and expose sensitive information.

• **Access private members**: Can access private fields/methods
• **Security manager**: May be restricted by security policies
• **Sensitive data exposure**: Can access internal implementation
• **Code injection**: Potential for malicious code execution

**Security considerations:**
```java
// Accessing private field
Field privateField = clazz.getDeclaredField("password");
privateField.setAccessible(true); // Bypasses private access
String password = (String) privateField.get(instance);

// Security manager check
SecurityManager sm = System.getSecurityManager();
if (sm != null) {
    sm.checkPermission(new ReflectPermission("suppressAccessChecks"));
}
```

## 10. How do you handle exceptions in reflection?

Reflection operations throw checked exceptions that must be handled.

• **NoSuchMethodException**: Method not found
• **IllegalAccessException**: Access denied
• **InvocationTargetException**: Exception in invoked method
• **ClassNotFoundException**: Class not found

**Exception handling:**
```java
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    Method method = clazz.getMethod("process", String.class);
    Object result = method.invoke(instance, "parameter");
    
} catch (ClassNotFoundException e) {
    // Handle class not found
    log.error("Class not found", e);
} catch (NoSuchMethodException e) {
    // Handle method not found
    log.error("Method not found", e);
} catch (IllegalAccessException e) {
    // Handle access denied
    log.error("Access denied", e);
} catch (InvocationTargetException e) {
    // Handle exception from invoked method
    Throwable cause = e.getCause();
    log.error("Method execution failed", cause);
}
```

## Key Takeaways

• **Annotations**: Metadata for code documentation and processing
• **Retention policies**: Control annotation lifecycle
• **Reflection**: Powerful but expensive runtime introspection
• **Use cases**: Frameworks, testing, serialization
• **Performance**: Cache reflective operations when possible
• **Security**: Be cautious with access control bypass
• **Exception handling**: Always handle reflection exceptions properly

# Lambda Expressions and Streams API - Interview Questions & Answers

## 1. What are lambda expressions?

**Answer:**
- Lambda expressions are anonymous functions introduced in Java 8
- They provide a concise way to write functional-style code
- Syntax: `(parameters) -> expression` or `(parameters) -> { statements }`

**Key Points:**
- Reduce boilerplate code
- Enable functional programming
- Work with functional interfaces

**Examples:**
```java
// Traditional way
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// Lambda way
Runnable r2 = () -> System.out.println("Hello");

// With parameters
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.forEach(name -> System.out.println(name));
```

## 2. What are functional interfaces?

**Answer:**
- Interfaces with exactly one abstract method
- Can have multiple default and static methods
- Annotated with `@FunctionalInterface` (optional but recommended)

**Key Points:**
- Target for lambda expressions
- Built-in functional interfaces: Predicate, Function, Consumer, Supplier
- Can create custom functional interfaces

**Examples:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Usage
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

// Built-in examples
Predicate<String> isEmpty = s -> s.isEmpty();
Function<String, Integer> length = s -> s.length();
Consumer<String> print = s -> System.out.println(s);
```

## 3. What are method references?

**Answer:**
- Shorthand notation for lambda expressions that call existing methods
- Four types: static, instance, constructor, arbitrary object method
- Syntax uses `::` operator

**Key Points:**
- More readable than lambda expressions
- Reuse existing method implementations
- Compile to same bytecode as lambdas

**Examples:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob");

// Lambda vs Method Reference
names.forEach(name -> System.out.println(name));
names.forEach(System.out::println);  // Method reference

// Static method reference
Function<String, Integer> parser = Integer::parseInt;

// Instance method reference
String str = "Hello";
Supplier<String> upperCase = str::toUpperCase;

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## 4. What is the difference between lambda and anonymous class?

**Answer:**

| Lambda Expression | Anonymous Class |
|------------------|-----------------|
| Only for functional interfaces | Can implement any interface/extend class |
| No new class file generated | Creates .class file |
| `this` refers to enclosing class | `this` refers to anonymous class |
| Cannot have instance variables | Can have instance variables |
| More memory efficient | Less memory efficient |

**Examples:**
```java
// Anonymous class
Runnable r1 = new Runnable() {
    private String message = "Hello";  // Instance variable allowed
    
    public void run() {
        System.out.println(this.message);  // 'this' refers to anonymous class
    }
};

// Lambda expression
Runnable r2 = () -> {
    // No instance variables allowed
    System.out.println("Hello");  // 'this' would refer to enclosing class
};
```

## 5. What is Stream API?

**Answer:**
- Functional-style operations on collections of objects
- Introduced in Java 8 for processing data declaratively
- Supports parallel processing and lazy evaluation

**Key Points:**
- Not a data structure, but a view of data
- Immutable - doesn't modify original collection
- Supports method chaining (fluent interface)
- Can be sequential or parallel

**Examples:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Filter even numbers and square them
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .collect(Collectors.toList());

// Parallel processing
int sum = numbers.parallelStream()
    .filter(n -> n > 5)
    .mapToInt(Integer::intValue)
    .sum();
```

## 6. What is the difference between Collection and Stream?

**Answer:**

| Collection | Stream |
|------------|--------|
| Data structure that stores elements | Abstraction for processing data |
| Eagerly constructed | Lazily constructed |
| Can be iterated multiple times | Can be consumed only once |
| External iteration (for loops) | Internal iteration (forEach) |
| Stores data in memory | Processes data on-demand |

**Examples:**
```java
List<String> collection = Arrays.asList("a", "b", "c");

// Collection - can iterate multiple times
for(String s : collection) { /* process */ }
for(String s : collection) { /* process again */ }

// Stream - can only be used once
Stream<String> stream = collection.stream();
stream.forEach(System.out::println);
// stream.forEach(System.out::println); // IllegalStateException!

// Need new stream for another operation
collection.stream().map(String::toUpperCase).forEach(System.out::println);
```

## 7. What are intermediate and terminal operations?

**Answer:**

**Intermediate Operations:**
- Return a new Stream
- Lazy evaluation - not executed until terminal operation
- Examples: filter(), map(), sorted(), distinct()

**Terminal Operations:**
- Produce a result or side effect
- Trigger execution of intermediate operations
- Examples: collect(), forEach(), reduce(), count()

**Examples:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// Intermediate operations (lazy)
Stream<String> filtered = names.stream()
    .filter(name -> name.length() > 3)  // Intermediate
    .map(String::toUpperCase);          // Intermediate

// Terminal operation (triggers execution)
List<String> result = filtered.collect(Collectors.toList());  // Terminal

// Chained example
long count = names.stream()
    .filter(name -> name.startsWith("J"))  // Intermediate
    .map(String::toLowerCase)              // Intermediate
    .count();                              // Terminal
```

## 8. What is the difference between map() and flatMap()?

**Answer:**

**map():**
- One-to-one transformation
- Transforms each element to another element
- Stream<T> → Stream<R>

**flatMap():**
- One-to-many transformation
- Flattens nested structures
- Stream<T> → Stream<R> (where T contains multiple R)

**Examples:**
```java
// map() example
List<String> words = Arrays.asList("hello", "world");
List<Integer> lengths = words.stream()
    .map(String::length)  // "hello" -> 5, "world" -> 5
    .collect(Collectors.toList());

// flatMap() example
List<String> sentences = Arrays.asList("hello world", "java stream");
List<String> allWords = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .collect(Collectors.toList());
// Result: ["hello", "world", "java", "stream"]

// Nested collections
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5, 6)
);

// map() would give Stream<List<Integer>>
// flatMap() gives Stream<Integer>
List<Integer> flattened = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4, 5, 6]
```

## 9. What is Optional class?

**Answer:**
- Container class introduced in Java 8 to handle null values
- Helps avoid NullPointerException
- Encourages explicit handling of absent values

**Key Points:**
- Immutable container
- Can contain either a value or be empty
- Provides methods for safe value extraction
- Should not be used for fields or parameters

**Examples:**
```java
// Creating Optional
Optional<String> optional1 = Optional.of("Hello");          // Non-null value
Optional<String> optional2 = Optional.ofNullable(null);     // Possibly null
Optional<String> optional3 = Optional.empty();              // Empty optional

// Checking and extracting values
if (optional1.isPresent()) {
    System.out.println(optional1.get());
}

// Better approach - functional style
optional1.ifPresent(System.out::println);

// Providing defaults
String value = optional2.orElse("Default Value");
String value2 = optional2.orElseGet(() -> "Computed Default");

// Chaining operations
Optional<String> result = Optional.of("hello")
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase);

// Method returning Optional
public Optional<User> findUserById(Long id) {
    User user = database.findUser(id);
    return Optional.ofNullable(user);
}

// Usage
findUserById(123L)
    .map(User::getName)
    .ifPresent(System.out::println);
```

# JDBC Interview Questions & Answers

## 1. What is JDBC?

**Answer:**
• JDBC stands for Java Database Connectivity
• It's an API that allows Java applications to interact with databases
• Acts as a bridge between Java and relational databases
• Provides a standard interface for database operations

**Key Points:**
• Platform-independent database connectivity
• Supports multiple database vendors (MySQL, Oracle, PostgreSQL, etc.)
• Part of Java SE since JDK 1.1

**Example:**
```java
// Basic JDBC usage
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

---

## 2. What are the steps to connect to a database using JDBC?

**Answer:**
The standard steps are:

• **Step 1:** Load the JDBC driver
• **Step 2:** Establish connection using DriverManager
• **Step 3:** Create Statement or PreparedStatement
• **Step 4:** Execute SQL queries
• **Step 5:** Process ResultSet (for SELECT queries)
• **Step 6:** Close connections and resources

**Example:**
```java
// Step 1: Load driver (optional in modern JDBC)
Class.forName("com.mysql.cj.jdbc.Driver");

// Step 2: Create connection
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb", "username", "password");

// Step 3: Create statement
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");

// Step 4: Execute query
pstmt.setInt(1, 123);
ResultSet rs = pstmt.executeQuery();

// Step 5: Process results
while(rs.next()) {
    System.out.println(rs.getString("name"));
}

// Step 6: Close resources
rs.close();
pstmt.close();
conn.close();
```

---

## 3. What is the difference between Statement and PreparedStatement?

**Answer:**

### Statement:
• Used for static SQL queries
• SQL is compiled every time it's executed
• Vulnerable to SQL injection
• No parameter binding

### PreparedStatement:
• Used for dynamic SQL queries with parameters
• Pre-compiled SQL - better performance
• Prevents SQL injection attacks
• Supports parameter binding with placeholders (?)

**Comparison Example:**
```java
// Statement - Not recommended for user input
Statement stmt = conn.createStatement();
String sql = "SELECT * FROM users WHERE name = '" + userName + "'"; // Dangerous!
ResultSet rs = stmt.executeQuery(sql);

// PreparedStatement - Recommended approach
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
pstmt.setString(1, userName); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```

**Key Benefits of PreparedStatement:**
• Better performance for repeated queries
• Automatic SQL injection prevention
• Type-safe parameter setting
• Cleaner, more readable code

---

## 4. What is connection pooling?

**Answer:**
• Connection pooling is a technique to reuse database connections
• Instead of creating new connections for each request, connections are pooled and reused
• Improves application performance and reduces database load
• Manages connection lifecycle automatically

**Benefits:**
• Faster response times
• Reduced database server load
• Better resource management
• Controlled concurrent connections

**Example with HikariCP:**
```java
// Configuration
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);
config.setMinimumIdle(5);

// Create pool
HikariDataSource dataSource = new HikariDataSource(config);

// Get connection from pool
Connection conn = dataSource.getConnection();
// Use connection...
conn.close(); // Returns to pool, doesn't actually close
```

**Popular Connection Pool Libraries:**
• HikariCP (fastest)
• Apache DBCP
• C3P0
• Tomcat JDBC Pool

---

## 5. What is SQL injection and how to prevent it?

**Answer:**
• SQL injection is a security vulnerability where malicious SQL code is inserted into application queries
• Attackers can manipulate SQL queries to access unauthorized data
• Can lead to data theft, data corruption, or complete database compromise

**How it happens:**
```java
// Vulnerable code
String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
// If userInput = "admin'; DROP TABLE users; --"
// Final query: SELECT * FROM users WHERE username = 'admin'; DROP TABLE users; --'
```

**Prevention Methods:**

### 1. Use PreparedStatement (Primary defense)
```java
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
pstmt.setString(1, userInput); // Automatically escaped
```

### 2. Input Validation
```java
// Validate input format
if (!userInput.matches("^[a-zA-Z0-9_]+$")) {
    throw new IllegalArgumentException("Invalid username format");
}
```

### 3. Stored Procedures
```java
CallableStatement cstmt = conn.prepareCall("{call getUserByName(?)}");
cstmt.setString(1, username);
```

### 4. Escape Special Characters
```java
// Use database-specific escaping functions
String escaped = StringEscapeUtils.escapeSql(userInput);
```

---

## 6. What is transaction management in JDBC?

**Answer:**
• Transaction management ensures data consistency and integrity
• A transaction is a group of SQL operations that either all succeed or all fail
• Follows ACID properties (Atomicity, Consistency, Isolation, Durability)

**Key Concepts:**
• **Commit:** Save all changes permanently
• **Rollback:** Undo all changes in the transaction
• **Auto-commit:** Each SQL statement is automatically committed (default behavior)

**Transaction Control Methods:**
```java
Connection conn = DriverManager.getConnection(url, user, password);

try {
    // Disable auto-commit to start transaction
    conn.setAutoCommit(false);
    
    // Execute multiple operations
    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
    pstmt1.setDouble(1, 1000.0);
    pstmt1.setInt(2, 1);
    pstmt1.executeUpdate();
    
    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
    pstmt2.setDouble(1, 1000.0);
    pstmt2.setInt(2, 2);
    pstmt2.executeUpdate();
    
    // If all operations successful, commit
    conn.commit();
    System.out.println("Transaction completed successfully");
    
} catch (SQLException e) {
    // If any operation fails, rollback
    conn.rollback();
    System.out.println("Transaction rolled back: " + e.getMessage());
} finally {
    // Restore auto-commit
    conn.setAutoCommit(true);
    conn.close();
}
```

**Transaction Isolation Levels:**
• **READ_UNCOMMITTED:** Lowest isolation, allows dirty reads
• **READ_COMMITTED:** Prevents dirty reads
• **REPEATABLE_READ:** Prevents dirty and non-repeatable reads
• **SERIALIZABLE:** Highest isolation, prevents all phenomena

**Setting Isolation Level:**
```java
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
```

**Savepoints (Advanced):**
```java
conn.setAutoCommit(false);
Savepoint sp1 = conn.setSavepoint("SavePoint1");
// Some operations...
conn.rollback(sp1); // Rollback to specific savepoint
conn.commit();
```

# Design Patterns Interview Questions & Answers

## 1. What are design patterns?

Design patterns are proven solutions to common programming problems that occur repeatedly in software development.

**Key Points:**
• Reusable templates for solving design problems
• Best practices documented by experienced developers
• Not code, but concepts and approaches
• Help make code more maintainable and flexible

**Example:**
Instead of creating objects directly everywhere, use Factory pattern to centralize object creation logic.

---

## 2. What is Singleton pattern?

Singleton ensures only one instance of a class exists throughout the application lifecycle.

**Key Points:**
• Only one object instance allowed
• Global access point to that instance
• Lazy or eager initialization options
• Common use: Database connections, loggers, configuration

**Example:**
```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {}
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

---

## 3. How do you implement thread-safe Singleton?

Thread-safe Singleton prevents multiple threads from creating multiple instances simultaneously.

**Key Points:**
• Use synchronized methods or blocks
• Double-checked locking for performance
• Enum approach is naturally thread-safe
• Static inner class approach

**Example:**
```java
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
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
```

---

## 4. What is Factory pattern?

Factory pattern creates objects without specifying their exact classes, delegating creation to factory methods.

**Key Points:**
• Encapsulates object creation logic
• Returns objects based on input parameters
• Promotes loose coupling
• Easy to extend with new types

**Example:**
```java
public class ShapeFactory {
    public Shape createShape(String type) {
        switch (type.toLowerCase()) {
            case "circle": return new Circle();
            case "square": return new Square();
            case "triangle": return new Triangle();
            default: throw new IllegalArgumentException("Unknown shape");
        }
    }
}
```

---

## 5. What is Observer pattern?

Observer pattern defines one-to-many dependency where multiple objects get notified when one object changes state.

**Key Points:**
• Subject maintains list of observers
• Automatic notification on state changes
• Loose coupling between subject and observers
• Common in event handling, MVC architecture

**Example:**
```java
public class NewsAgency {
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
```

---

## 6. What is Strategy pattern?

Strategy pattern defines family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

**Key Points:**
• Multiple ways to perform same task
• Algorithm selection at runtime
• Eliminates conditional statements
• Easy to add new strategies

**Example:**
```java
public class PaymentProcessor {
    private PaymentStrategy strategy;
    
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void processPayment(double amount) {
        strategy.pay(amount);
    }
}

// Strategies: CreditCardPayment, PayPalPayment, BankTransfer
```

---

## 7. What is Adapter pattern?

Adapter pattern allows incompatible interfaces to work together by wrapping existing class with new interface.

**Key Points:**
• Converts one interface to another
• Makes incompatible classes compatible
• Wrapper around existing functionality
• Common when integrating third-party libraries

**Example:**
```java
// Third-party library with different interface
class LegacyPrinter {
    public void oldPrint(String text) {
        System.out.println("Legacy: " + text);
    }
}

// Adapter to make it compatible
class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter;
    
    public PrinterAdapter(LegacyPrinter printer) {
        this.legacyPrinter = printer;
    }
    
    public void print(String text) {
        legacyPrinter.oldPrint(text);
    }
}
```

---

## 8. What is Decorator pattern?

Decorator pattern adds new functionality to objects dynamically without altering their structure.

**Key Points:**
• Extends object behavior at runtime
• Alternative to subclassing
• Wraps original object with new features
• Can stack multiple decorators

**Example:**
```java
// Base coffee
class SimpleCoffee implements Coffee {
    public double cost() { return 2.0; }
    public String description() { return "Simple coffee"; }
}

// Decorators
class MilkDecorator implements Coffee {
    private Coffee coffee;
    
    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    public double cost() { return coffee.cost() + 0.5; }
    public String description() { return coffee.description() + ", milk"; }
}

// Usage: new MilkDecorator(new SimpleCoffee())
```

---

## Quick Summary

**Most Common Patterns in Interviews:**
1. **Singleton** - One instance only
2. **Factory** - Object creation abstraction  
3. **Observer** - Event notification system
4. **Strategy** - Interchangeable algorithms

**Remember:** Focus on the problem each pattern solves, not just the implementation details!


# Spring Framework Interview Questions & Answers

## 1. What is Spring Framework?

Spring is a comprehensive Java framework that simplifies enterprise application development.

**Key Points:**
- Open-source framework for building Java applications
- Provides dependency injection and aspect-oriented programming
- Makes Java development easier and more testable
- Supports various modules like MVC, Security, Data, etc.

**Example:**
```java
@Component
public class UserService {
    // Spring manages this class lifecycle
}
```

---

## 2. What is Inversion of Control (IoC)?

IoC is a design principle where object creation and dependency management is handled by the framework, not the class itself.

**Key Points:**
- Objects don't create their dependencies directly
- Framework controls object lifecycle and dependencies
- Reduces coupling between classes
- Makes code more testable and maintainable

**Example:**
```java
// Without IoC - tight coupling
public class OrderService {
    private PaymentService paymentService = new PaymentService(); // Bad
}

// With IoC - loose coupling
@Service
public class OrderService {
    @Autowired
    private PaymentService paymentService; // Good - Spring injects it
}
```

---

## 3. What is Dependency Injection?

Dependency Injection is a technique where dependencies are provided to an object rather than the object creating them.

**Key Points:**
- Three types: Constructor, Setter, and Field injection
- Constructor injection is recommended
- Makes testing easier with mock objects
- Reduces boilerplate code

**Example:**
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    // Constructor injection (recommended)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

---

## 4. What is the difference between BeanFactory and ApplicationContext?

Both are IoC containers, but ApplicationContext is more feature-rich.

**BeanFactory:**
- Basic container with lazy initialization
- Minimal memory footprint
- Manual bean lifecycle management

**ApplicationContext:**
- Advanced container with eager initialization
- More features like event handling, internationalization
- Automatic bean lifecycle management
- Extends BeanFactory

**Example:**
```java
// BeanFactory
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));

// ApplicationContext (preferred)
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
```

---

## 5. What are Spring beans?

Spring beans are objects managed by the Spring IoC container.

**Key Points:**
- Objects whose lifecycle is managed by Spring
- Configured via annotations or XML
- Singleton by default (can be changed)
- Automatically injected where needed

**Example:**
```java
@Component
public class EmailService {
    // This is a Spring bean
}

@Configuration
public class AppConfig {
    @Bean
    public DatabaseService databaseService() {
        return new DatabaseService(); // This creates a bean
    }
}
```

---

## 6. What is Spring Boot?

Spring Boot is a framework that simplifies Spring application development with auto-configuration and embedded servers.

**Key Points:**
- Built on top of Spring Framework
- Provides auto-configuration
- Embedded servers (Tomcat, Jetty)
- Starter dependencies for quick setup
- Production-ready features out of the box

**Example:**
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        // That's it! Web server starts automatically
    }
}
```

---

## 7. What is auto-configuration in Spring Boot?

Auto-configuration automatically configures Spring beans based on classpath dependencies and properties.

**Key Points:**
- Reduces manual configuration
- Works based on conditions (classpath, properties, beans)
- Can be customized or disabled
- Uses @Conditional annotations internally

**Example:**
```java
// If H2 database is on classpath, Spring Boot automatically configures:
// - DataSource
// - JdbcTemplate  
// - Transaction manager

// You just need to add dependency in pom.xml:
// <dependency>
//     <groupId>com.h2database</groupId>
//     <artifactId>h2</artifactId>
// </dependency>
```

---

## 8. What is @SpringBootApplication annotation?

@SpringBootApplication is a convenience annotation that combines three important annotations.

**Key Points:**
- Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
- Marks the main class of Spring Boot application
- Enables auto-configuration and component scanning
- Should be placed on the main class

**Example:**
```java
@SpringBootApplication
// Equivalent to:
// @Configuration
// @EnableAutoConfiguration  
// @ComponentScan
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

---

## 9. What is the difference between @Component, @Service, and @Repository?

All are stereotype annotations for creating Spring beans, but they indicate different layers.

**Key Points:**
- @Component: Generic stereotype for any Spring-managed component
- @Service: Business logic layer
- @Repository: Data access layer (adds exception translation)
- All create beans, but provide semantic meaning

**Example:**
```java
@Component
public class UtilityHelper {
    // Generic component
}

@Service
public class UserService {
    // Business logic
}

@Repository
public class UserRepository {
    // Data access - Spring adds exception translation
}
```

---

## 10. What is @Autowired annotation?

@Autowired enables automatic dependency injection by Spring.

**Key Points:**
- Injects dependencies automatically
- Works on constructors, setters, and fields
- By default, injection is required (can be made optional)
- Uses type-based matching

**Example:**
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

---

## 11. What is @Qualifier annotation?

@Qualifier is used when multiple beans of the same type exist and you need to specify which one to inject.

**Key Points:**
- Resolves ambiguity when multiple beans of same type exist
- Used with @Autowired
- Can specify bean name or custom qualifier
- Helps in precise dependency injection

**Example:**
```java
@Service
public class NotificationService {
    
    @Autowired
    @Qualifier("emailSender")
    private MessageSender emailSender;
    
    @Autowired
    @Qualifier("smsSender") 
    private MessageSender smsSender;
}

@Component("emailSender")
public class EmailSender implements MessageSender { }

@Component("smsSender")
public class SmsSender implements MessageSender { }
```

---

## 12. What is ApplicationContext?

ApplicationContext is Spring's advanced IoC container that manages beans and provides enterprise features.

**Key Points:**
- Central interface for Spring application
- Manages bean lifecycle and dependencies
- Provides event handling, internationalization
- Different implementations: ClassPathXmlApplicationContext, AnnotationConfigApplicationContext
- Eagerly initializes singleton beans

**Example:**
```java
@Service
public class MyService {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    public void doSomething() {
        // Get bean programmatically
        UserService userService = applicationContext.getBean(UserService.class);
        
        // Publish events
        applicationContext.publishEvent(new CustomEvent("Hello"));
    }
}
```

---

## Quick Reference Summary

| Annotation | Purpose |
|------------|---------|
| @Component | Generic Spring bean |
| @Service | Business logic layer |
| @Repository | Data access layer |
| @Autowired | Dependency injection |
| @Qualifier | Specify which bean to inject |
| @SpringBootApplication | Main Spring Boot class |

**Best Practices:**
- Use constructor injection over field injection
- Prefer @Service and @Repository over @Component for clarity
- Use @Qualifier when multiple beans of same type exist
- Keep ApplicationContext usage minimal in business logic

# RESTful Services Interview Questions & Answers

## 1. What are RESTful web services?

RESTful web services are web APIs that follow REST architectural principles for building distributed systems.

**Key Points:**
• **Stateless communication** - Each request contains all needed information
• **Resource-based** - Everything is treated as a resource with unique URLs
• **Standard HTTP methods** - Uses GET, POST, PUT, DELETE
• **JSON/XML data exchange** - Lightweight data formats
• **Platform independent** - Works across different technologies

**Example:**
```
GET /api/users/123        // Get user with ID 123
POST /api/users           // Create new user
PUT /api/users/123        // Update user 123
DELETE /api/users/123     // Delete user 123
```

---

## 2. What are the principles of REST?

REST has six core architectural principles that guide API design.

**The 6 REST Principles:**
• **Client-Server** - Separation of concerns between client and server
• **Stateless** - No client state stored on server between requests
• **Cacheable** - Responses should be cacheable when possible
• **Uniform Interface** - Consistent way to interact with resources
• **Layered System** - Architecture can have multiple layers
• **Code on Demand** (optional) - Server can send executable code

**Real Example:**
```
// Stateless - each request is independent
GET /api/products/456
Authorization: Bearer token123
Accept: application/json
```

---

## 3. What are HTTP methods and their usage?

HTTP methods define what action to perform on a resource.

**Common HTTP Methods:**
• **GET** - Retrieve data (read-only, safe)
• **POST** - Create new resources
• **PUT** - Update/replace entire resource
• **PATCH** - Partial update of resource
• **DELETE** - Remove resource
• **HEAD** - Get headers only (no body)
• **OPTIONS** - Get allowed methods

**Practical Examples:**
```
GET /api/books           // Get all books
GET /api/books/5         // Get book with ID 5
POST /api/books          // Create new book
PUT /api/books/5         // Replace entire book 5
PATCH /api/books/5       // Update some fields of book 5
DELETE /api/books/5      // Delete book 5
```

---

## 4. What is the difference between PUT and POST?

PUT and POST serve different purposes in resource management.

**Key Differences:**

| Aspect | POST | PUT |
|--------|------|-----|
| **Purpose** | Create new resource | Update/replace resource |
| **Idempotent** | No | Yes |
| **Resource ID** | Server generates | Client provides |
| **Multiple calls** | Creates multiple resources | Same result |

**Examples:**
```
// POST - Create new user (server assigns ID)
POST /api/users
{
  "name": "John Doe",
  "email": "john@example.com"
}
Response: 201 Created, Location: /api/users/789

// PUT - Update specific user (client knows ID)
PUT /api/users/789
{
  "name": "John Smith",
  "email": "johnsmith@example.com"
}
Response: 200 OK
```

---

## 5. What is idempotency in REST?

Idempotency means making the same request multiple times produces the same result.

**Idempotent Methods:**
• **GET** - Always returns same data
• **PUT** - Same update result every time
• **DELETE** - Resource stays deleted
• **HEAD, OPTIONS** - Safe operations

**Non-Idempotent:**
• **POST** - Creates new resource each time

**Real Example:**
```
// Idempotent - calling multiple times has same effect
PUT /api/users/123
{ "status": "active" }

// First call: Updates user status
// Second call: User status already active (no change)
// Result: Same final state

// Non-idempotent
POST /api/orders
{ "product": "laptop", "quantity": 1 }

// First call: Creates order #001
// Second call: Creates order #002
// Result: Different each time
```

---

## 6. What are HTTP status codes?

HTTP status codes indicate the result of an HTTP request.

**Status Code Categories:**
• **1xx** - Informational (100 Continue)
• **2xx** - Success (200 OK, 201 Created)
• **3xx** - Redirection (301 Moved, 304 Not Modified)
• **4xx** - Client Error (400 Bad Request, 404 Not Found)
• **5xx** - Server Error (500 Internal Error, 503 Unavailable)

**Common REST API Status Codes:**
```
// Success responses
200 OK              // GET, PUT successful
201 Created         // POST successful
204 No Content      // DELETE successful

// Client errors
400 Bad Request     // Invalid request data
401 Unauthorized    // Authentication required
403 Forbidden       // Access denied
404 Not Found       // Resource doesn't exist
409 Conflict        // Resource conflict

// Server errors
500 Internal Error  // Server-side problem
503 Service Unavailable // Server overloaded
```

**Practical Usage:**
```javascript
// API Response Examples
GET /api/users/999
Response: 404 Not Found
{ "error": "User not found" }

POST /api/users
Response: 201 Created
Location: /api/users/1001
{ "id": 1001, "name": "Alice" }

DELETE /api/users/123
Response: 204 No Content
```

---

## Quick Interview Tips

**Remember:**
• REST is about **resources**, not actions
• Use **nouns** in URLs, not verbs (`/users` not `/getUsers`)
• HTTP methods define the **action**
• Status codes communicate **results**
• Keep APIs **stateless** and **predictable**

**Common Mistakes to Avoid:**
• Using GET for data modification
• Ignoring proper status codes
• Making APIs stateful
• Poor resource naming conventions


# Microservices Interview Questions & Answers

## 1. What are microservices?

Microservices are a software architecture pattern where applications are built as a collection of small, independent services that communicate over well-defined APIs.

**Key Points:**
• Each service handles a specific business function
• Services run in their own processes
• They communicate via HTTP/REST, messaging, or gRPC
• Each service can be developed, deployed, and scaled independently

**Example:**
Instead of one large e-commerce application, you'd have:
• User Service (handles authentication)
• Product Service (manages catalog)
• Order Service (processes orders)
• Payment Service (handles transactions)

---

## 2. What are the advantages of microservices?

**Key Benefits:**
• **Independent Development** - Teams can work on different services simultaneously
• **Technology Flexibility** - Each service can use different programming languages/databases
• **Scalability** - Scale only the services that need it
• **Fault Isolation** - If one service fails, others continue working
• **Faster Deployment** - Deploy individual services without affecting the entire system

**Real Example:**
Netflix can update their recommendation service without touching their video streaming service. If recommendations go down, users can still watch movies.

---

## 3. What are the challenges of microservices?

**Main Challenges:**
• **Complexity** - Managing multiple services is harder than one application
• **Network Latency** - Services communicate over network, adding delays
• **Data Consistency** - Maintaining consistency across distributed databases
• **Testing** - Integration testing becomes more complex
• **Monitoring** - Need to track multiple services and their interactions

**Example:**
A simple user registration might involve:
• User Service → Email Service → Notification Service
If any service fails, you need proper error handling and rollback mechanisms.

---

## 4. What is service discovery?

Service discovery is the mechanism that allows services to find and communicate with each other without hardcoding network locations.

**How it Works:**
• Services register themselves with a discovery server
• Other services query the discovery server to find available services
• Handles dynamic IP addresses and port changes

**Popular Tools:**
• Consul
• Eureka (Netflix)
• etcd
• Kubernetes built-in service discovery

**Example:**
```
Order Service needs Payment Service
→ Queries Service Registry: "Where is Payment Service?"
→ Registry responds: "Payment Service is at 192.168.1.100:8080"
→ Order Service connects to that address
```

---

## 5. What is API Gateway?

An API Gateway is a single entry point that sits between clients and microservices, routing requests to appropriate services.

**Key Functions:**
• **Request Routing** - Directs requests to correct microservice
• **Authentication** - Handles user authentication centrally
• **Rate Limiting** - Controls request frequency
• **Load Balancing** - Distributes traffic across service instances
• **Response Aggregation** - Combines responses from multiple services

**Popular Solutions:**
• AWS API Gateway
• Kong
• Zuul (Netflix)
• Nginx Plus

**Example:**
```
Mobile App → API Gateway → User Service (for profile)
                      → Order Service (for orders)
                      → Product Service (for catalog)
```

---

## 6. What is circuit breaker pattern?

Circuit breaker is a design pattern that prevents cascading failures by monitoring service calls and "opening the circuit" when failures exceed a threshold.

**Three States:**
• **Closed** - Normal operation, requests pass through
• **Open** - Service is failing, requests are blocked immediately
• **Half-Open** - Testing if service has recovered

**Benefits:**
• Prevents system overload during failures
• Provides fast failure responses
• Allows failing services time to recover
• Improves overall system resilience

**Example Implementation:**
```
if (failureCount > threshold) {
    // Circuit is OPEN - return cached response or error
    return fallbackResponse();
} else {
    try {
        // Circuit is CLOSED - make actual call
        response = callExternalService();
        resetFailureCount();
        return response;
    } catch (Exception e) {
        incrementFailureCount();
        throw e;
    }
}
```

**Real-World Example:**
If Payment Service is down, instead of waiting 30 seconds for timeout, circuit breaker immediately returns "Payment temporarily unavailable" after detecting the pattern of failures.

# Performance Tuning Interview Questions & Answers

## 1. How do you identify performance bottlenecks?

**Answer:**
• Use profiling tools like JProfiler, VisualVM, or Java Flight Recorder
• Monitor application metrics - CPU usage, memory consumption, response times
• Analyze logs for slow queries or operations
• Use APM tools like New Relic or AppDynamics
• Check database query performance with EXPLAIN plans
• Monitor thread dumps for blocked threads

**Example:**
```bash
# Using JVisualVM to profile application
jvisualvm --jdkhome $JAVA_HOME

# Enable JFR for production monitoring
java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=app.jfr MyApp
```

## 2. What are common performance issues in Java applications?

**Answer:**
• Memory leaks - objects not being garbage collected
• Inefficient database queries - N+1 problems, missing indexes
• Synchronization bottlenecks - excessive locking
• Poor caching strategies
• Large object creation in loops
• Inefficient collections usage
• Blocking I/O operations

**Example:**
```java
// Bad - creates many objects
for (int i = 0; i < 1000; i++) {
    String result = "Item " + i; // Creates new String objects
}

// Good - use StringBuilder
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("Item ").append(i);
}
```

## 3. What is connection pooling and why is it important?

**Answer:**
• Reuses database connections instead of creating new ones
• Reduces connection overhead and improves performance
• Limits concurrent connections to database
• Prevents connection exhaustion
• Common pools: HikariCP, Apache DBCP, C3P0

**Example:**
```java
// HikariCP configuration
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setMaximumPoolSize(20);
config.setMinimumIdle(5);
config.setConnectionTimeout(30000);
HikariDataSource dataSource = new HikariDataSource(config);
```

## 4. What is caching and when should you use it?

**Answer:**
• Stores frequently accessed data in memory for faster retrieval
• Use when data is read frequently but changes infrequently
• Types: In-memory (Redis, Memcached), Application-level (@Cacheable)
• Consider cache invalidation strategies
• Monitor cache hit ratios

**When to use:**
• Database query results
• API responses
• Computed values
• Static content

**Example:**
```java
@Cacheable("users")
public User getUserById(Long id) {
    return userRepository.findById(id);
}

@CacheEvict("users")
public void updateUser(User user) {
    userRepository.save(user);
}
```

## 5. What are important JVM parameters?

**Answer:**
• **Heap Size:** -Xms (initial), -Xmx (maximum)
• **Garbage Collection:** -XX:+UseG1GC, -XX:+UseZGC
• **Memory:** -XX:NewRatio, -XX:MaxMetaspaceSize
• **Monitoring:** -XX:+PrintGCDetails, -XX:+HeapDumpOnOutOfMemoryError
• **Performance:** -server, -XX:+TieredCompilation

**Example:**
```bash
java -Xms2g -Xmx4g -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -jar myapp.jar
```

## 6. How do you tune heap size?

**Answer:**
• Start with -Xms = -Xmx for consistent performance
• Monitor heap usage with profiling tools
• Set heap to 70-80% of available RAM
• Consider young/old generation ratios
• Adjust based on GC logs and application behavior

**Guidelines:**
• Small apps: 512MB - 2GB
• Medium apps: 2GB - 8GB  
• Large apps: 8GB+

**Example:**
```bash
# For 8GB RAM server
java -Xms4g -Xmx6g -XX:NewRatio=3 MyApp

# Monitor heap usage
jstat -gc -t <pid> 5s
```

## 7. What is the difference between -Xms and -Xmx?

**Answer:**
• **-Xms:** Initial heap size allocated at JVM startup
• **-Xmx:** Maximum heap size JVM can grow to
• Setting them equal avoids dynamic heap resizing overhead
• JVM will expand heap from Xms to Xmx as needed

**Example:**
```bash
# Different sizes - heap can grow
java -Xms512m -Xmx2g MyApp

# Same sizes - fixed heap, better performance
java -Xms2g -Xmx2g MyApp
```

## 8. How do you analyze heap dumps?

**Answer:**
• Use tools like Eclipse MAT, VisualVM, or JProfiler
• Look for memory leaks - objects with high retained size
• Analyze object references and GC roots
• Check for duplicate strings and large arrays
• Identify classes consuming most memory

**Steps:**
1. Generate heap dump: `jcmd <pid> GC.run_finalization`
2. Open in MAT or VisualVM
3. Run leak suspects report
4. Analyze dominator tree
5. Check histogram for object counts

**Example:**
```bash
# Generate heap dump
jcmd 12345 GC.run_finalization
jcmd 12345 VM.gc
jmap -dump:format=b,file=heap.hprof 12345

# Analyze with MAT
./mat heap.hprof
```

## 9. What is JIT compilation?

**Answer:**
• Just-In-Time compilation converts bytecode to native machine code
• Happens at runtime for frequently executed code (hot spots)
• Improves performance through optimizations
• C1 (client) compiler for fast startup
• C2 (server) compiler for maximum performance
• Tiered compilation uses both

**Benefits:**
• Method inlining
• Dead code elimination  
• Loop optimization
• Branch prediction

**Example:**
```bash
# Enable JIT compilation logging
java -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining MyApp

# Tiered compilation (default in Java 8+)
java -XX:+TieredCompilation MyApp
```

---

## Key Performance Tips:
• Profile before optimizing
• Use appropriate data structures
• Minimize object creation
• Implement proper caching
• Optimize database queries
• Monitor GC behavior
• Use connection pooling
• Enable JIT optimizations

# Modern Java Features - Interview Questions & Answers

## 1. What are the new features in Java 8?

Java 8 was a game-changer with several revolutionary features:

• **Lambda Expressions** - Functional programming support
  ```java
  List<String> names = Arrays.asList("John", "Jane", "Bob");
  names.forEach(name -> System.out.println(name));
  ```

• **Stream API** - Powerful data processing
  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
  int sum = numbers.stream().filter(n -> n > 2).mapToInt(Integer::intValue).sum();
  ```

• **Method References** - Cleaner syntax
  ```java
  names.forEach(System.out::println);
  ```

• **Optional Class** - Null pointer safety
  ```java
  Optional<String> optional = Optional.ofNullable(getName());
  optional.ifPresent(System.out::println);
  ```

• **Default Methods in Interfaces** - Interface evolution
  ```java
  interface Vehicle {
      default void start() { System.out.println("Starting..."); }
  }
  ```

• **New Date/Time API** - Better date handling
  ```java
  LocalDateTime now = LocalDateTime.now();
  LocalDate date = LocalDate.of(2024, 1, 15);
  ```

---

## 2. What are the new features in Java 11?

Java 11 is an LTS version with practical improvements:

• **Local Variable Type Inference (var)** - Enhanced from Java 10
  ```java
  var list = List.of("apple", "banana");
  var map = Map.of("key1", "value1");
  ```

• **HTTP Client API** - Built-in HTTP support
  ```java
  HttpClient client = HttpClient.newHttpClient();
  HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("https://api.example.com"))
      .build();
  ```

• **String Methods** - New utility methods
  ```java
  String text = "  Hello World  ";
  text.isBlank();     // false
  text.strip();       // "Hello World"
  "A".repeat(3);      // "AAA"
  ```

• **Files Methods** - Easy file operations
  ```java
  String content = Files.readString(Path.of("file.txt"));
  Files.writeString(Path.of("output.txt"), "Hello");
  ```

• **Collection.toArray()** - Enhanced method
  ```java
  List<String> list = List.of("a", "b", "c");
  String[] array = list.toArray(String[]::new);
  ```

• **Nest-Based Access Control** - Better inner class access
• **Dynamic Class-File Constants** - Performance improvements

---

## 3. What are the new features in Java 17?

Java 17 is the latest LTS with modern language features:

• **Sealed Classes** - Controlled inheritance
  ```java
  public sealed class Shape permits Circle, Rectangle, Triangle {
      // Base class
  }
  
  public final class Circle extends Shape {
      private double radius;
  }
  ```

• **Pattern Matching for instanceof** - Cleaner type checking
  ```java
  if (obj instanceof String str) {
      System.out.println(str.toUpperCase());
  }
  ```

• **Records** - Immutable data classes
  ```java
  public record Person(String name, int age) {}
  
  Person person = new Person("John", 30);
  System.out.println(person.name()); // John
  ```

• **Text Blocks** - Multi-line strings
  ```java
  String json = """
      {
          "name": "John",
          "age": 30
      }
      """;
  ```

• **Switch Expressions** - Enhanced switch
  ```java
  String result = switch (day) {
      case MONDAY, FRIDAY -> "Work day";
      case SATURDAY, SUNDAY -> "Weekend";
      default -> "Midweek";
  };
  ```

• **Helpful NullPointerExceptions** - Better error messages
• **Strong Encapsulation of JDK Internals** - Security improvements

---

## 4. What is the Java release cycle and LTS versions?

Java follows a predictable 6-month release cycle since Java 9:

• **Release Schedule**
  - New version every 6 months (March & September)
  - Predictable and time-based releases
  - Feature-driven to time-driven approach

• **LTS (Long Term Support) Versions**
  - Every 3 years (every 6th release)
  - Extended support and updates
  - Recommended for production use

• **LTS Timeline**
  - **Java 8** (March 2014) - First modern LTS
  - **Java 11** (September 2018) - Current popular LTS
  - **Java 17** (September 2021) - Latest LTS
  - **Java 21** (September 2023) - Next LTS

• **Non-LTS Versions**
  - Java 9, 10, 12, 13, 14, 15, 16, 18, 19, 20
  - 6 months of support only
  - Good for experimenting with new features

• **Support Duration**
  - **LTS versions**: 8+ years of support
  - **Non-LTS versions**: 6 months only
  - Oracle provides commercial support
  - OpenJDK provides free updates

• **Migration Strategy**
  - Most enterprises stick to LTS versions
  - Upgrade path: Java 8 → Java 11 → Java 17 → Java 21
  - Test new features in non-LTS versions

  # Cloud and Containerization Interview Questions & Answers

## 1. What is containerization?

Containerization is packaging applications with all their dependencies into lightweight, portable containers.

**Key Points:**
- Bundles code, runtime, libraries, and settings together
- Runs consistently across different environments
- Isolated from the host system
- More efficient than virtual machines

**Example:** 
- Your Node.js app + dependencies → Docker container
- Runs same way on dev laptop, staging, and production

---

## 2. What is Docker?

Docker is a platform that creates, manages, and runs containers using containerization technology.

**Key Points:**
- Uses Docker images as blueprints
- Docker Engine runs containers
- Dockerfile defines how to build images
- Cross-platform compatibility

**Example:**
```dockerfile
FROM node:16
COPY . /app
WORKDIR /app
RUN npm install
CMD ["npm", "start"]
```

---

## 3. What is Kubernetes?

Kubernetes is an orchestration platform that manages containerized applications at scale across clusters.

**Key Points:**
- Automates deployment, scaling, and management
- Self-healing (restarts failed containers)
- Load balancing and service discovery
- Rolling updates with zero downtime

**Example:**
- Deploy 100 web app containers
- Auto-scale based on traffic
- Replace failed containers automatically

---

## 4. What is cloud computing?

Cloud computing delivers computing services over the internet instead of using local servers.

**Key Points:**
- On-demand resource access
- Pay-as-you-use pricing
- Scalable and flexible
- Three main types: IaaS, PaaS, SaaS

**Examples:**
- **IaaS:** AWS EC2, Azure VMs
- **PaaS:** Heroku, Google App Engine
- **SaaS:** Gmail, Salesforce

---

## 5. What is distributed system?

A distributed system is multiple computers working together as a single system to achieve common goals.

**Key Points:**
- Components communicate over network
- No shared memory between nodes
- Fault tolerance and redundancy
- Horizontal scaling capability

**Examples:**
- Netflix streaming (multiple data centers)
- Google Search (thousands of servers)
- Banking systems (ATMs, branches, online)

---

## 6. What is load balancing?

Load balancing distributes incoming requests across multiple servers to prevent overload and ensure availability.

**Key Points:**
- Prevents single point of failure
- Improves response times
- Enables horizontal scaling
- Health checks monitor server status

**Examples:**
- **Round Robin:** Request 1→Server A, Request 2→Server B
- **Least Connections:** Route to server with fewest active connections
- **Geographic:** Route users to nearest data center

---

## 7. What is caching strategies?

Caching strategies store frequently accessed data in fast storage to reduce response times and database load.

**Key Points:**
- Temporary storage for quick access
- Reduces backend load
- Improves user experience
- Various levels and types

**Examples:**
- **Browser Cache:** Store CSS/JS files locally
- **CDN Cache:** Images served from nearest location
- **Database Cache:** Redis stores frequent queries
- **Application Cache:** In-memory data storage

**Common Strategies:**
- **Cache-Aside:** App manages cache manually
- **Write-Through:** Write to cache and database simultaneously
- **Write-Behind:** Write to cache first, database later