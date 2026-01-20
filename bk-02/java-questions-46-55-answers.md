## Interfaces and Abstract Classes

### 46. What is an interface in Java?

An **interface** is a contract that defines what methods a class must implement without providing implementation details.

#### **Key Characteristics:**
- **Contract definition** - specifies what classes must do
- **Abstract methods** by default (before Java 8)
- **Multiple inheritance** support
- **No instantiation** - cannot create objects directly
- **Loose coupling** - classes depend on interface, not implementation

#### **Basic Interface:**
```java
interface Drawable {
    void draw();           // Abstract method (implicitly public abstract)
    void resize(int size); // Abstract method
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    @Override
    public void resize(int size) {
        System.out.println("Resizing circle to " + size);
    }
}
```

#### **Interface Features:**
- **Variables:** `public static final` by default
- **Methods:** `public abstract` by default (before Java 8)
- **Access:** All members are public
- **Inheritance:** Can extend multiple interfaces

#### **Example with Constants:**
```java
interface Constants {
    int MAX_SIZE = 100;        // public static final
    String DEFAULT_NAME = "Unknown"; // public static final
}
```

### 47. What is an abstract class?

An **abstract class** is a class that cannot be instantiated and may contain both abstract and concrete methods.

#### **Key Characteristics:**
- **Cannot be instantiated** directly
- **Mix of abstract and concrete methods**
- **Can have constructors** and instance variables
- **Single inheritance** only
- **Partial abstraction**

#### **Basic Abstract Class:**
```java
abstract class Animal {
    protected String name;
    
    // Constructor
    public Animal(String name) {
        this.name = name;
    }
    
    // Concrete method
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void makeSound();
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks");
    }
}
```

#### **When to Use Abstract Classes:**
- **Shared code** among related classes
- **Common state** (instance variables)
- **Partial implementation** with some abstract methods
- **Constructor logic** needed

### 48. What is the difference between interface and abstract class?

| Aspect | Interface | Abstract Class |
|--------|-----------|----------------|
| **Abstraction** | Complete (100%) | Partial |
| **Methods** | Abstract by default | Mix of abstract & concrete |
| **Variables** | public static final only | Any type of variables |
| **Constructors** | Not allowed | Allowed |
| **Inheritance** | Multiple inheritance | Single inheritance |
| **Access Modifiers** | public only | Any access modifier |
| **Instantiation** | Cannot instantiate | Cannot instantiate |
| **Keyword** | `implements` | `extends` |

#### **Interface Example:**
```java
interface Vehicle {
    int MAX_SPEED = 200;  // public static final
    
    void start();         // public abstract
    void stop();          // public abstract
}

class Car implements Vehicle {
    @Override
    public void start() { System.out.println("Car started"); }
    
    @Override
    public void stop() { System.out.println("Car stopped"); }
}
```

#### **Abstract Class Example:**
```java
abstract class Vehicle {
    protected String brand;     // Instance variable
    private static int count;   // Static variable
    
    public Vehicle(String brand) {  // Constructor
        this.brand = brand;
        count++;
    }
    
    public void displayInfo() {     // Concrete method
        System.out.println("Brand: " + brand);
    }
    
    public abstract void start();   // Abstract method
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }
    
    @Override
    public void start() {
        System.out.println(brand + " car started");
    }
}
```

#### **When to Choose:**

**Use Interface when:**
- **Multiple inheritance** needed
- **Complete abstraction** required
- **Unrelated classes** need same contract
- **Loose coupling** is priority

**Use Abstract Class when:**
- **Code sharing** among related classes
- **Partial implementation** needed
- **Common state** (instance variables) required
- **Constructor logic** needed

### 49. Can an interface have constructors?

**No**, interfaces cannot have constructors.

#### **Why Interfaces Cannot Have Constructors:**
- **No instantiation** - interfaces cannot be instantiated
- **No instance variables** - nothing to initialize
- **Contract definition** - interfaces define behavior, not state
- **Multiple inheritance** - would create constructor conflicts

#### **What Happens Instead:**
```java
interface Drawable {
    // No constructors allowed
    // public Drawable() { }  // ✗ Compilation error
    
    void draw();
}

// Cannot instantiate interface
// Drawable d = new Drawable();  // ✗ Compilation error

// Must implement through a class
class Circle implements Drawable {
    public Circle() {  // Class constructor is fine
        // initialization code
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
```

#### **Interface Initialization:**
```java
interface Constants {
    // Variables are initialized at declaration
    int MAX_VALUE = 100;           // Initialized here
    List<String> NAMES = Arrays.asList("A", "B", "C");
    
    // Static block for complex initialization (Java 8+)
    static void initializeResources() {
        // Initialization logic
    }
}
```

### 50. What are default methods in interfaces?

**Default methods** are methods with implementation in interfaces, introduced in Java 8.

#### **Purpose:**
- **Interface evolution** - add methods without breaking existing code
- **Backward compatibility** - existing implementations don't break
- **Code reuse** - share common implementation
- **Optional implementation** - implementing classes can override if needed

#### **Syntax:**
```java
interface Drawable {
    void draw();  // Abstract method
    
    // Default method with implementation
    default void display() {
        System.out.println("Displaying drawable object");
        draw();  // Can call abstract methods
    }
    
    default void highlight() {
        System.out.println("Highlighting object");
    }
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    // Can optionally override default method
    @Override
    public void highlight() {
        System.out.println("Highlighting circle with red border");
    }
}
```

#### **Default Method Conflicts:**
```java
interface A {
    default void method() { System.out.println("A"); }
}

interface B {
    default void method() { System.out.println("B"); }
}

class C implements A, B {
    @Override
    public void method() {
        A.super.method();  // Explicitly call A's method
        // or B.super.method();  // or call B's method
        // or provide own implementation
    }
}
```

#### **Benefits:**
- **Library evolution** without breaking changes
- **Code sharing** between implementations
- **Flexibility** - can override if needed
- **Maintains interface contract**

### 51. What are static methods in interfaces?

**Static methods** in interfaces belong to the interface itself and provide utility functionality.

#### **Characteristics:**
- **Belong to interface** - not to implementing classes
- **Cannot be overridden** - final implementation
- **Called using interface name**
- **Utility methods** - helper functionality

#### **Example:**
```java
interface MathUtils {
    // Static method in interface
    static int add(int a, int b) {
        return a + b;
    }
    
    static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // Abstract method
    void performCalculation();
}

class Calculator implements MathUtils {
    @Override
    public void performCalculation() {
        // Use static methods from interface
        int sum = MathUtils.add(5, 3);
        double area = MathUtils.calculateArea(2.5);
        System.out.println("Sum: " + sum + ", Area: " + area);
    }
}

// Usage
int result = MathUtils.add(10, 20);  // Called on interface
```

#### **Rules:**
- **Cannot be overridden** in implementing classes
- **Must have implementation** in interface
- **Called using interface name** only
- **Cannot access instance members**

#### **Practical Use Case:**
```java
interface FileProcessor {
    void processFile(String filename);
    
    // Static utility methods
    static boolean isValidFile(String filename) {
        return filename != null && filename.endsWith(".txt");
    }
    
    static String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot + 1) : "";
    }
}
```

### 52. Can you have private methods in interfaces?

**Yes**, since Java 9, interfaces can have private methods.

#### **Purpose:**
- **Code reuse** between default methods
- **Reduce duplication** in interface implementation
- **Encapsulation** - hide implementation details
- **Helper methods** for default methods

#### **Types of Private Methods:**

**1. Private Instance Methods:**
```java
interface Calculator {
    default int addAndMultiply(int a, int b, int multiplier) {
        int sum = add(a, b);
        return multiply(sum, multiplier);
    }
    
    default int subtractAndMultiply(int a, int b, int multiplier) {
        int diff = subtract(a, b);
        return multiply(diff, multiplier);
    }
    
    // Private method to avoid duplication
    private int multiply(int a, int b) {
        return a * b;
    }
    
    private int add(int a, int b) {
        return a + b;
    }
    
    private int subtract(int a, int b) {
        return a - b;
    }
}
```

**2. Private Static Methods:**
```java
interface Logger {
    default void logInfo(String message) {
        log("INFO", message);
    }
    
    default void logError(String message) {
        log("ERROR", message);
    }
    
    // Private static method
    private static void log(String level, String message) {
        System.out.println("[" + level + "] " + getCurrentTime() + ": " + message);
    }
    
    private static String getCurrentTime() {
        return java.time.LocalTime.now().toString();
    }
}
```

#### **Rules:**
- **Cannot be abstract** - must have implementation
- **Cannot be accessed** by implementing classes
- **Can be called** only from default/static methods in same interface
- **Help organize code** within interface

### 53. What is marker interface?

A **marker interface** is an empty interface with no methods that serves as a tag or marker.

#### **Purpose:**
- **Metadata** - indicates class has certain properties
- **Runtime checks** - JVM or frameworks can check for marker
- **Design pattern** - semantic meaning without methods
- **Type identification** - instanceof checks

#### **Common Marker Interfaces:**

**1. Serializable:**
```java
import java.io.Serializable;

class Student implements Serializable {
    private String name;
    private int age;
    
    // Class can be serialized because it implements Serializable
}
```

**2. Cloneable:**
```java
class Person implements Cloneable {
    private String name;
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();  // Only works if class implements Cloneable
    }
}
```

**3. Remote (RMI):**
```java
import java.rmi.Remote;

interface RemoteService extends Remote {
    void performRemoteOperation() throws java.rmi.RemoteException;
}
```

#### **Custom Marker Interface:**
```java
// Custom marker interface
interface Auditable {
    // Empty interface - just a marker
}

class BankAccount implements Auditable {
    private double balance;
    
    // This class is marked as auditable
}

// Usage in framework code
public void processObjects(Object[] objects) {
    for (Object obj : objects) {
        if (obj instanceof Auditable) {
            // Perform audit logging
            System.out.println("Auditing: " + obj.getClass().getSimpleName());
        }
    }
}
```

#### **Modern Alternative - Annotations:**
```java
// Modern approach using annotations
@Retention(RetentionPolicy.RUNTIME)
@interface Auditable {
}

@Auditable
class BankAccount {
    // Class marked with annotation instead of interface
}
```

### 54. What is functional interface?

A **functional interface** has exactly one abstract method and can be used with lambda expressions.

#### **Characteristics:**
- **Single Abstract Method (SAM)** - exactly one abstract method
- **Lambda compatible** - can use lambda expressions
- **Can have default/static methods** - but only one abstract
- **@FunctionalInterface annotation** - optional but recommended

#### **Built-in Functional Interfaces:**

**1. Runnable:**
```java
@FunctionalInterface
interface Runnable {
    void run();
}

// Lambda usage
Runnable task = () -> System.out.println("Task executed");
new Thread(task).start();
```

**2. Predicate:**
```java
@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}

// Lambda usage
Predicate<String> isEmpty = s -> s.isEmpty();
System.out.println(isEmpty.test(""));  // true
```

**3. Function:**
```java
@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
}

// Lambda usage
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello"));  // 5
```

#### **Custom Functional Interface:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);  // Single abstract method
    
    // Default methods allowed
    default void printResult(int a, int b) {
        System.out.println("Result: " + calculate(a, b));
    }
    
    // Static methods allowed
    static void info() {
        System.out.println("Calculator interface");
    }
}

// Lambda usage
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3));      // 8
System.out.println(multiply.calculate(5, 3)); // 15
```

#### **Method References:**
```java
// Method reference instead of lambda
Function<String, Integer> length1 = s -> s.length();     // Lambda
Function<String, Integer> length2 = String::length;      // Method reference

Predicate<String> isEmpty1 = s -> s.isEmpty();           // Lambda
Predicate<String> isEmpty2 = String::isEmpty;            // Method reference
```

### 55. Can an interface extend another interface?

**Yes**, an interface can extend one or more interfaces using the `extends` keyword.

#### **Single Interface Extension:**
```java
interface Animal {
    void eat();
    void sleep();
}

interface Mammal extends Animal {
    void giveBirth();
    void produceMilk();
}

class Dog implements Mammal {
    @Override
    public void eat() { System.out.println("Dog eats"); }
    
    @Override
    public void sleep() { System.out.println("Dog sleeps"); }
    
    @Override
    public void giveBirth() { System.out.println("Dog gives birth"); }
    
    @Override
    public void produceMilk() { System.out.println("Dog produces milk"); }
}
```

#### **Multiple Interface Extension:**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

interface Amphibious extends Flyable, Swimmable {
    void land();  // Additional method
}

class Duck implements Amphibious {
    @Override
    public void fly() { System.out.println("Duck flies"); }
    
    @Override
    public void swim() { System.out.println("Duck swims"); }
    
    @Override
    public void land() { System.out.println("Duck lands"); }
}
```

#### **Interface Hierarchy:**
```java
interface Shape {
    double area();
}

interface TwoDimensional extends Shape {
    double perimeter();
}

interface ThreeDimensional extends Shape {
    double volume();
}

interface Drawable {
    void draw();
}

// Multiple inheritance of interfaces
interface DrawableShape extends Shape, Drawable {
    void highlight();
}

class Circle implements DrawableShape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    @Override
    public void highlight() {
        System.out.println("Highlighting circle");
    }
}
```

#### **Default Method Inheritance:**
```java
interface A {
    default void method() {
        System.out.println("A's method");
    }
}

interface B extends A {
    // Inherits A's default method
    
    default void anotherMethod() {
        method();  // Can call inherited method
        System.out.println("B's method");
    }
}

class C implements B {
    // Inherits both default methods from A and B
}
```

#### **Benefits:**
- **Interface composition** - build complex contracts
- **Code reuse** - inherit methods from parent interfaces
- **Hierarchical design** - organize related interfaces
- **Multiple inheritance** - combine multiple interface contracts
