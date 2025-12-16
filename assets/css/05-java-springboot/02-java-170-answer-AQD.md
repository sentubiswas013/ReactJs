## Java Platform
### 1. Why is Java so popular?

Java is popular because it's platform-independent - you write once and run anywhere. It has automatic memory management through garbage collection, strong security features, and a huge ecosystem of libraries and frameworks. Plus, it's object-oriented, which makes code more maintainable and reusable.

```java
// Example: Same code runs on Windows, Linux, Mac
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

### 2. What is platform independence?

Platform independence means Java code can run on any operating system without modification. You compile Java source code into bytecode, and the JVM on each platform interprets this bytecode. So the same .class file works on Windows, Linux, or Mac.

```java
// This .java file compiles to .class bytecode
// The bytecode runs on any JVM
javac MyApp.java  // Creates MyApp.class
java MyApp        // Runs on any platform with JVM
```

### 3. What is bytecode?

Bytecode is the intermediate code that Java source code gets compiled into. It's platform-neutral and stored in .class files. The JVM reads this bytecode and converts it to machine-specific instructions. Think of it as a universal language that all JVMs understand.

```java
// Source code (.java) → Bytecode (.class) → Machine code
public class Demo {
    int x = 10;  // This becomes bytecode instructions
}
// javac creates bytecode that JVM interprets
```

### 4. Compare JDK vs JVM vs JRE

JDK is the development kit with compiler and tools. JRE is the runtime environment needed to run Java programs. JVM is the virtual machine that actually executes bytecode. Think of it as: JDK contains JRE, JRE contains JVM. Developers need JDK, end users need JRE.

```java
// JDK: Development tools (javac, jar, etc.)
// JRE: Runtime libraries + JVM
// JVM: Executes bytecode
```

### 5. What are the important differences between C++ and Java?

Java is simpler and safer than C++. Java has automatic memory management while C++ requires manual memory management. Java doesn't have pointers, multiple inheritance, or operator overloading. Java is platform-independent, C++ is platform-dependent. Java has built-in garbage collection.

```java
// Java - automatic memory management
String str = new String("Hello");  // No need to delete

// C++ - manual memory management
// char* str = new char[10];  // Must delete manually
// delete[] str;
```

### 6. What is the role of a classloader in Java?

The classloader loads .class files into memory when needed. It finds classes from classpath, loads them into JVM, and links them. There are different types: Bootstrap loads core classes, Extension loads extension classes, and Application loads your application classes. It enables dynamic loading.

```java
// ClassLoader example
Class<?> clazz = Class.forName("com.example.MyClass");
Object obj = clazz.newInstance();  // Dynamically loaded
```

## Wrapper Classes
### 7. What are Wrapper classes?

Wrapper classes are object versions of primitive types. Like Integer for int, Boolean for boolean, Double for double. They're needed because collections can only store objects, not primitives. They also provide utility methods for conversion and manipulation.

```java
// Primitive vs Wrapper
int num = 5;           // primitive
Integer numObj = 5;    // wrapper (autoboxing)
String str = numObj.toString();  // utility method
```

### 8. Why do we need Wrapper classes in Java?

We need wrapper classes because collections like ArrayList can only store objects, not primitives. They also provide useful methods like parsing strings to numbers, comparing values, and converting between types. Plus, they allow null values which primitives can't have.

```java
// Collections need objects
ArrayList<Integer> list = new ArrayList<>();
list.add(10);  // autoboxing: int → Integer

// Utility methods
int value = Integer.parseInt("123");
```

### 9. What are the different ways of creating Wrapper class instances?

You can create wrapper instances using constructors, valueOf methods, or autoboxing. Constructor creates new objects each time. valueOf reuses cached objects for small values. Autoboxing automatically converts primitives to wrappers.

```java
// Three ways to create Integer
Integer a = new Integer(10);     // Constructor (deprecated)
Integer b = Integer.valueOf(10); // valueOf method
Integer c = 10;                  // Autoboxing
```

### 10. What are the differences in the two ways of creating Wrapper classes?

Using constructors always creates new objects, even for same values. Using valueOf or autoboxing reuses cached objects for values like -128 to 127 for Integer. This saves memory and improves performance. That's why valueOf is preferred over constructors.

```java
Integer a = new Integer(100);
Integer b = new Integer(100);
System.out.println(a == b);  // false - different objects

Integer c = Integer.valueOf(100);
Integer d = Integer.valueOf(100);
System.out.println(c == d);  // true - same cached object
```

### 11. What is auto boxing?

Auto boxing is Java's automatic conversion between primitives and their wrapper classes. When you assign a primitive to a wrapper object, Java automatically boxes it. When you assign a wrapper to a primitive, it unboxes automatically. This makes code cleaner and easier to write.

```java
// Auto boxing: primitive → wrapper
Integer num = 5;  // int automatically becomes Integer

// Auto unboxing: wrapper → primitive  
int value = num;  // Integer automatically becomes int
```

### 12. What are the advantages of auto boxing?

Auto boxing simplifies code by eliminating manual conversions. You can directly use primitives with collections, pass them to methods expecting objects, and mix primitives with wrappers seamlessly. It reduces boilerplate code and makes the API more intuitive to use.

```java
// Before auto boxing - verbose
List<Integer> list = new ArrayList<>();
list.add(Integer.valueOf(10));

// With auto boxing - clean
List<Integer> list = new ArrayList<>();
list.add(10);  // Much simpler!
```

### 13. What is casting?

Casting is converting one data type to another. In Java, you can cast between compatible types like numbers, or between classes in an inheritance hierarchy. It's either implicit when safe, or explicit when you need to force the conversion and potentially lose data.

```java
// Numeric casting
double d = 10.5;
int i = (int) d;  // Explicit cast, loses decimal

// Object casting
Object obj = "Hello";
String str = (String) obj;  // Cast back to String
```

### 14. What is implicit casting?

Implicit casting happens automatically when converting from smaller to larger data types, or from subclass to superclass. Java does this safely without data loss. Like converting int to long, or assigning a subclass object to a superclass reference.

```java
// Implicit numeric casting - safe
int i = 10;
long l = i;  // int automatically becomes long

// Implicit object casting - upcasting
String str = "Hello";
Object obj = str;  // String automatically becomes Object
```

### 15. What is explicit casting?

Explicit casting is when you manually convert types using parentheses. You need this when converting from larger to smaller types, or from superclass to subclass. It can cause data loss or ClassCastException, so you must be careful.

```java
// Explicit numeric casting - potential data loss
double d = 10.9;
int i = (int) d;  // Result: 10 (loses .9)

// Explicit object casting - downcasting
Object obj = "Hello";
String str = (String) obj;  // Must cast explicitly
```

## Strings
### 16. Are all Strings immutable?

Yes, all String objects in Java are immutable. Once created, you cannot change their content. Any operation that seems to modify a String actually creates a new String object. This provides thread safety and allows String pooling for memory efficiency.

```java
String str = "Hello";
str.concat(" World");  // Creates new String, doesn't modify original
System.out.println(str);  // Still prints "Hello"

String newStr = str.concat(" World");  // Must assign to new variable
```

### 17. Where are String values stored in memory?

String literals are stored in the String Pool, which is part of the heap memory. When you create a String literal, Java checks if it already exists in the pool. If yes, it reuses the same object. Strings created with 'new' keyword go directly to heap memory.

```java
String s1 = "Hello";        // Stored in String Pool
String s2 = "Hello";        // Reuses same object from pool
String s3 = new String("Hello");  // Creates new object in heap

System.out.println(s1 == s2);  // true - same reference
System.out.println(s1 == s3);  // false - different objects
```

### 18. Why should you be careful about the String concatenation (+) operator in loops?

String concatenation with + operator in loops creates many temporary String objects because Strings are immutable. Each concatenation creates a new String, leading to poor performance and memory waste. In a loop with 1000 iterations, you'd create 1000 unnecessary objects.

```java
// Bad - creates many temporary objects
String result = "";
for (int i = 0; i < 1000; i++) {
    result += "a";  // Creates new String each time
}

// Each += operation: creates new String, copies old content, adds new
```

### 19. How do you solve the above problem?

Use StringBuilder or StringBuffer instead of String concatenation in loops. These classes use a resizable buffer, so they modify the content in place without creating new objects. StringBuilder is faster for single-threaded code, StringBuffer is thread-safe.

```java
// Good - uses mutable buffer
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("a");  // Modifies existing buffer
}
String result = sb.toString();  // Convert to String once
```

### 20. What are differences between String and StringBuffer?

String is immutable and thread-safe by nature, but creates new objects for modifications. StringBuffer is mutable with a resizable buffer and is thread-safe with synchronized methods. StringBuffer is better for multiple modifications, especially in multi-threaded environments.

```java
// String - immutable
String str = "Hello";
str.concat(" World");  // Creates new object

// StringBuffer - mutable and thread-safe
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");  // Modifies existing buffer
```

### 21. What are differences between StringBuilder and StringBuffer?

StringBuilder is faster but not thread-safe, while StringBuffer is thread-safe but slower due to synchronized methods. Use StringBuilder for single-threaded applications and StringBuffer when multiple threads access the same buffer. Both are mutable and better than String for multiple concatenations.

```java
// StringBuilder - faster, not thread-safe
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");

// StringBuffer - slower, thread-safe
StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World");  // Synchronized method
```

### 22. Can you give examples of different utility methods in the String class?

String class has many useful methods like length(), charAt(), substring(), indexOf(), contains(), startsWith(), endsWith(), toUpperCase(), toLowerCase(), trim(), split(), and replace(). These help with string manipulation, searching, and formatting.

```java
String str = "  Hello World  ";
int len = str.length();           // 15
char ch = str.charAt(2);          // 'H'
String sub = str.substring(2, 7); // "Hello"
boolean has = str.contains("World"); // true
String clean = str.trim();        // "Hello World"
```

## Object Oriented Programming Basics
### 23. What is a class?

A class is a blueprint or template for creating objects. It defines the structure and behavior that objects will have - their attributes (fields) and methods. Think of it like a cookie cutter that shapes cookies. The class defines what properties and actions all objects of that type will have.

```java
public class Car {
    String brand;     // Attribute
    int speed;        // Attribute
    
    void accelerate() {  // Method/Behavior
        speed += 10;
    }
}
```

### 24. What is an object?

An object is an instance of a class - it's the actual thing created from the class blueprint. Each object has its own copy of the class attributes with specific values. If class is the blueprint, object is the actual house built from that blueprint.

```java
Car myCar = new Car();    // Creating object
myCar.brand = "Toyota";   // Setting object's attributes
myCar.speed = 60;
myCar.accelerate();       // Calling object's method
```

### 25. What is the state of an object?

The state of an object is the current values of all its attributes or fields at any given time. It represents what the object "knows" or "remembers." The state can change when you modify the object's fields through methods or direct access.

```java
Car car = new Car();
car.brand = "Honda";  // State: brand="Honda", speed=0
car.speed = 50;       // State: brand="Honda", speed=50
car.accelerate();     // State: brand="Honda", speed=60
```

### 26. What is behavior of an object?

Behavior refers to what an object can do - the methods or functions it can perform. These are the actions the object can take, often changing its state or interacting with other objects. Behavior defines the object's capabilities and operations.

```java
public class Dog {
    String name;
    
    // Behaviors (methods)
    void bark() { System.out.println("Woof!"); }
    void eat() { System.out.println("Eating..."); }
    void sleep() { System.out.println("Sleeping..."); }
}
```

### 27. What is the super class of every class in Java?

Object class is the superclass of every class in Java. Even if you don't explicitly extend anything, your class automatically extends Object. This means every class inherits methods like toString(), equals(), hashCode(), and getClass() from the Object class.

```java
public class MyClass {  // Implicitly extends Object
    // Inherits: toString(), equals(), hashCode(), etc.
}

// Same as writing:
public class MyClass extends Object {
}
```

### 28. Explain about the toString method?

The toString() method returns a string representation of an object. By default, it returns the class name and hash code, but you should override it to provide meaningful information. It's automatically called when you print an object or concatenate it with strings.

```java
public class Person {
    String name;
    int age;
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

Person p = new Person();
System.out.println(p);  // Calls toString() automatically
```

### 29. What is the use of the equals method in Java?

The equals() method compares two objects for logical equality. By default, it compares references (same as ==), but you should override it to compare actual content. It's used by collections like HashSet and HashMap to check for duplicate objects.

```java
public class Person {
    String name;
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person other = (Person) obj;
            return this.name.equals(other.name);
        }
        return false;
    }
}
```

### 30. What are the important things to consider when implementing the equals method?

When overriding equals(), follow these rules: check for null and same reference first, use instanceof for type checking, cast safely, compare all significant fields, and always override hashCode() too. The method should be reflexive, symmetric, transitive, and consistent.

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;           // Same reference
    if (obj == null) return false;          // Null check
    if (!(obj instanceof Person)) return false;  // Type check
    
    Person other = (Person) obj;
    return Objects.equals(name, other.name);     // Field comparison
}
```

### 31. What is the hashCode method used for in Java?

The hashCode() method returns an integer hash value for an object, used by hash-based collections like HashMap and HashSet. Objects that are equal must have the same hash code. It's crucial for efficient storage and retrieval in hash tables. Always override it when you override equals().

```java
public class Person {
    String name;
    
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
    
    // If two Person objects are equal, they must have same hashCode
}
```

### 32. Explain inheritance with examples.

Inheritance allows a class to inherit properties and methods from another class. The child class (subclass) gets all features of the parent class (superclass) and can add its own or override existing ones. It promotes code reuse and establishes "is-a" relationships.

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

Dog dog = new Dog();
dog.eat();  // Inherited from Animal
dog.bark(); // Own method
```

### 33. What is method overloading?

Method overloading means having multiple methods with the same name but different parameters in the same class. The compiler decides which method to call based on the number, type, or order of arguments. It's compile-time polymorphism and makes code more readable.

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}

Calculator calc = new Calculator();
calc.add(5, 3);      // Calls first method
calc.add(5.5, 3.2);  // Calls second method
```

### 34. What is method overriding?

Method overriding occurs when a subclass provides a specific implementation of a method that's already defined in its parent class. The subclass method must have the same signature as the parent method. It's runtime polymorphism and allows customizing inherited behavior.

```java
class Animal {
    void makeSound() { System.out.println("Some sound"); }
}

class Dog extends Animal {
    @Override
    void makeSound() { System.out.println("Bark"); }
}

Animal animal = new Dog();
animal.makeSound();  // Prints "Bark" - overridden method
```

### 35. Can a superclass reference variable hold an object of a subclass?

Yes, a superclass reference can hold a subclass object. This is called upcasting and happens automatically. You can access superclass methods and overridden methods, but not subclass-specific methods unless you downcast. This enables polymorphism.

```java
Animal animal = new Dog();  // Upcasting - automatic
animal.eat();               // Superclass method
animal.makeSound();         // Overridden method in Dog

// animal.bark();           // Error - bark() not in Animal
Dog dog = (Dog) animal;     // Downcasting - explicit
dog.bark();                 // Now accessible
```

### 36. Is multiple inheritance allowed in Java?

No, Java doesn't support multiple inheritance of classes to avoid the diamond problem and complexity. A class can only extend one superclass. However, Java supports multiple inheritance through interfaces - a class can implement multiple interfaces, providing similar benefits safely.

```java
// Not allowed - multiple class inheritance
// class Child extends Parent1, Parent2 { }  // Compilation error

// Allowed - multiple interface implementation
interface Flyable { void fly(); }
interface Swimmable { void swim(); }

class Duck implements Flyable, Swimmable {
    public void fly() { }
    public void swim() { }
}
```

### 37. What is an interface?

An interface is a contract that defines what methods a class must implement, but not how. It contains abstract methods (and can have default/static methods since Java 8). Interfaces achieve abstraction and multiple inheritance. Classes implement interfaces using the "implements" keyword.

```java
interface Drawable {
    void draw();           // Abstract method
    default void print() { // Default method (Java 8+)
        System.out.println("Printing...");
    }
}

class Circle implements Drawable {
    public void draw() { System.out.println("Drawing circle"); }
}
```

### 38. How do you define an interface?

You define an interface using the "interface" keyword instead of "class". Methods are implicitly public and abstract (unless default/static). Variables are implicitly public, static, and final. Use meaningful names and focus on what the implementing class should do.

```java
public interface Vehicle {
    int MAX_SPEED = 200;        // public static final
    
    void start();               // public abstract
    void stop();                // public abstract
    
    default void honk() {       // Default implementation
        System.out.println("Beep!");
    }
}
```

### 39. How do you implement an interface?

Use the "implements" keyword after the class name, then provide concrete implementations for all abstract methods. A class can implement multiple interfaces separated by commas. You must implement all abstract methods or declare the class as abstract.

```java
class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }
    
    public void stop() {
        System.out.println("Car stopped");
    }
}

Car car = new Car();
car.start();  // Implemented method
car.honk();   // Inherited default method
```

### 40. Can you explain a few tricky things about interfaces?

Interfaces can have default methods (Java 8+), static methods, and private methods (Java 9+). If a class implements multiple interfaces with same default method, it must override to resolve conflict. Interface variables are constants. Functional interfaces have exactly one abstract method.

```java
interface A { default void test() { System.out.println("A"); } }
interface B { default void test() { System.out.println("B"); } }

class C implements A, B {
    public void test() {        // Must override to resolve conflict
        A.super.test();         // Call specific interface method
    }
}
```

### 41. Can you extend an interface?

Yes, interfaces can extend other interfaces using the "extends" keyword. An interface can extend multiple interfaces, inheriting all their abstract methods. The implementing class must provide implementations for all methods from the entire inheritance chain. This creates interface hierarchies.

```java
interface Animal { void eat(); }
interface Mammal extends Animal { void breathe(); }
interface Dog extends Mammal { void bark(); }

class Labrador implements Dog {
    public void eat() { }      // From Animal
    public void breathe() { }  // From Mammal  
    public void bark() { }     // From Dog
}
```

### 42. Can a class extend multiple interfaces?

You mean implement multiple interfaces - yes, a class can implement multiple interfaces separated by commas. However, a class can only extend one superclass. This gives you the benefits of multiple inheritance without the complexity. The class must implement all abstract methods from all interfaces.

```java
interface Flyable { void fly(); }
interface Swimmable { void swim(); }

class Duck extends Bird implements Flyable, Swimmable {
    public void fly() { System.out.println("Flying"); }
    public void swim() { System.out.println("Swimming"); }
}
```

### 43. What is an abstract class?

An abstract class is a class that cannot be instantiated directly. It's declared with the "abstract" keyword and typically contains abstract methods that subclasses must implement. It can also have concrete methods, constructors, and instance variables. Use it when classes share common code but need different implementations.

```java
abstract class Shape {
    String color;  // Concrete field
    
    abstract void draw();  // Abstract method
    
    void setColor(String color) {  // Concrete method
        this.color = color;
    }
}

class Circle extends Shape {
    void draw() { System.out.println("Drawing circle"); }
}
```

### 44. When do you use an abstract class?

Use abstract classes when you have common code to share among related classes, but some methods need different implementations. Perfect for template method pattern, when you need constructors, or when you want to provide default implementations. Choose over interfaces when you need state or non-public methods.

```java
abstract class DatabaseConnection {
    String url;  // Common state
    
    void connect() {  // Common implementation
        System.out.println("Connecting to " + url);
    }
    
    abstract void executeQuery(String sql);  // Different for each DB
}
```

### 45. How do you define an abstract method?

Use the "abstract" keyword before the method signature, with no method body - just a semicolon. Abstract methods can only exist in abstract classes or interfaces. Subclasses must provide concrete implementations unless they're also abstract. Abstract methods define what subclasses must do, not how.

```java
abstract class Vehicle {
    abstract void start();     // No body, ends with semicolon
    abstract void stop();      // Must be implemented by subclasses
    
    void honk() {              // Concrete method allowed
        System.out.println("Beep!");
    }
}
```

### 46. Compare abstract class vs interface?

Abstract classes can have constructors, instance variables, and concrete methods, while interfaces mainly have abstract methods (plus default/static since Java 8). A class extends one abstract class but implements multiple interfaces. Use abstract classes for "is-a" relationships with shared code, interfaces for "can-do" capabilities.

```java
// Abstract class - shared implementation
abstract class Animal { 
    String name;  // State allowed
    Animal(String name) { this.name = name; }  // Constructor allowed
}

// Interface - contract only
interface Flyable { 
    void fly();  // No state, no constructors
}
```

## Constructors
### 47. What is a constructor?

A constructor is a special method that initializes objects when they're created. It has the same name as the class, no return type, and is called automatically with the "new" keyword. Constructors set up initial state, allocate resources, and ensure objects start in a valid condition.

```java
public class Person {
    String name;
    int age;
    
    public Person(String name, int age) {  // Constructor
        this.name = name;
        this.age = age;
    }
}

Person person = new Person("John", 25);  // Constructor called
```

### 48. What is a default constructor?

A default constructor is a no-argument constructor that Java provides automatically if you don't define any constructors. It initializes fields to default values (null for objects, 0 for numbers, false for booleans). If you create any constructor, Java won't provide the default one.

```java
class Student {
    String name;  // Will be null
    int age;      // Will be 0
    // Java provides: public Student() { }
}

class Teacher {
    String name;
    public Teacher(String name) { this.name = name; }
    // No default constructor - must use Teacher(String)
}
```

### 49. How do you call a superclass constructor from a constructor?

Use "super()" as the first statement in your constructor to call the superclass constructor. You can pass arguments to call specific superclass constructors. If you don't call super() explicitly, Java automatically calls the no-argument superclass constructor.

```java
class Animal {
    String species;
    Animal(String species) { this.species = species; }
}

class Dog extends Animal {
    String breed;
    
    Dog(String species, String breed) {
        super(species);  // Must be first statement
        this.breed = breed;
    }
}
```

### 50. What is the use of this()?

The "this()" calls another constructor in the same class, enabling constructor chaining. It must be the first statement and helps avoid code duplication by having one constructor call another with different parameters. Useful for providing multiple ways to create objects.

```java
class Rectangle {
    int width, height;
    
    Rectangle() {
        this(1, 1);  // Calls Rectangle(int, int)
    }
    
    Rectangle(int size) {
        this(size, size);  // Calls Rectangle(int, int)
    }
    
    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
```

### 51. Can a constructor be called directly from a method?

No, you cannot call a constructor directly from a regular method. Constructors are only called during object creation with the "new" keyword, or from other constructors using "this()" or "super()". If you need constructor-like functionality in a method, create a separate initialization method instead.

```java
public class Person {
    String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public void someMethod() {
        // Person("John");  // Error - cannot call constructor directly
        // this("John");    // Error - this() only in constructors
        initialize("John"); // Use regular method instead
    }
    
    private void initialize(String name) { this.name = name; }
}
```

### 52. Is a superclass constructor called even when there is no explicit call from a subclass constructor?

Yes, Java automatically calls the no-argument superclass constructor if you don't explicitly call super(). This happens as the first statement of every constructor. If the superclass doesn't have a no-argument constructor, you must explicitly call super() with appropriate arguments or you'll get a compilation error.

```java
class Animal {
    Animal() { System.out.println("Animal created"); }
}

class Dog extends Animal {
    Dog() {
        // super() is automatically called here
        System.out.println("Dog created");
    }
}

// Output: "Animal created" then "Dog created"
```

## Advanced Object Oriented Concepts
### 53. What is polymorphism?

Polymorphism means "many forms" - the ability of objects to take multiple forms. In Java, it allows a single interface to represent different underlying data types. You can use a superclass reference to call overridden methods in subclasses, and the correct method is chosen at runtime.

```java
Animal animal1 = new Dog();
Animal animal2 = new Cat();

animal1.makeSound();  // Calls Dog's makeSound()
animal2.makeSound();  // Calls Cat's makeSound()

// Same method call, different behavior - that's polymorphism
```

### 54. What is the use of instanceof operator in Java?

The instanceof operator checks if an object is an instance of a specific class or interface. It returns true if the object is of that type or any of its subtypes. It's useful for safe casting and type checking before performing type-specific operations.

```java
Object obj = "Hello";

if (obj instanceof String) {
    String str = (String) obj;  // Safe to cast
    System.out.println(str.toUpperCase());
}

if (obj instanceof Integer) {  // false
    // This block won't execute
}
```

### 55. What is coupling?

Coupling refers to how much one class depends on another class. Low coupling means classes are independent and changes in one class don't affect others much. High coupling means classes are tightly connected, making the code harder to maintain and modify. Always aim for loose coupling.

```java
// High coupling - bad
class OrderProcessor {
    MySQLDatabase db = new MySQLDatabase();  // Tightly coupled to MySQL
}

// Low coupling - good  
class OrderProcessor {
    Database db;  // Depends on interface, not concrete class
    OrderProcessor(Database db) { this.db = db; }
}
```

### 56. What is cohesion?

Cohesion measures how closely related the responsibilities of a single class are. High cohesion means a class has a single, well-defined purpose with all methods working toward that goal. Low cohesion means a class does too many unrelated things. Always aim for high cohesion.

```java
// High cohesion - good
class Calculator {
    int add(int a, int b) { return a + b; }
    int subtract(int a, int b) { return a - b; }
    // All methods related to calculation
}

// Low cohesion - bad
class UtilityClass {
    void calculateTax() { }
    void sendEmail() { }     // Unrelated responsibilities
    void validateInput() { }
}
```

### 57. What is encapsulation?

Encapsulation is bundling data and methods that operate on that data within a single class, and controlling access through access modifiers. It hides internal implementation details and provides a clean interface. This protects data integrity and makes code more maintainable and secure.

```java
public class BankAccount {
    private double balance;  // Hidden from outside
    
    public void deposit(double amount) {  // Controlled access
        if (amount > 0) balance += amount;
    }
    
    public double getBalance() {  // Read-only access
        return balance;
    }
    // Cannot directly access balance from outside
}
```

### 58. What is an inner class?

An inner class is a class defined inside another class. It has access to all members of the outer class, including private ones. Inner classes are useful for helper classes that are only used by the outer class, and they can access outer class instance variables directly.

```java
public class OuterClass {
    private String message = "Hello";
    
    class InnerClass {
        void display() {
            System.out.println(message);  // Can access outer class members
        }
    }
    
    void createInner() {
        InnerClass inner = new InnerClass();
        inner.display();
    }
}
```

### 59. What is a static inner class?

A static inner class is an inner class declared with the static keyword. It doesn't need an instance of the outer class to be created and cannot access non-static members of the outer class. It's like a regular class but nested for packaging convenience.

```java
public class OuterClass {
    private static String staticMessage = "Static Hello";
    private String instanceMessage = "Instance Hello";
    
    static class StaticInnerClass {
        void display() {
            System.out.println(staticMessage);     // OK - static member
            // System.out.println(instanceMessage); // Error - non-static
        }
    }
}

// Usage: OuterClass.StaticInnerClass inner = new OuterClass.StaticInnerClass();
```

### 60. Can you create an inner class inside a method?

Yes, you can create a local inner class inside a method. These classes are only visible within that method and can access final or effectively final local variables. They're useful for implementing interfaces or extending classes for specific method needs without creating separate files.

```java
public class OuterClass {
    void someMethod() {
        final String localVar = "Local";
        
        class LocalInnerClass {
            void display() {
                System.out.println(localVar);  // Can access final local variables
            }
        }
        
        LocalInnerClass local = new LocalInnerClass();
        local.display();
    }
}
```

### 61. What is an anonymous class?

An anonymous class is a class without a name that's defined and instantiated at the same time. It's typically used to implement interfaces or extend classes for one-time use, like event handlers or callbacks. You create it inline where you need it, making code more concise for simple implementations.

```java
// Anonymous class implementing Runnable
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Task running");
    }
};

// Anonymous class extending Thread
Thread thread = new Thread() {
    @Override
    public void run() {
        System.out.println("Thread running");
    }
};
```

## Modifiers
### 62. What is the default class modifier?

The default class modifier is package-private, meaning no explicit access modifier is specified. Classes with default access can only be accessed by other classes in the same package. This is more restrictive than public but less restrictive than private, providing package-level encapsulation.

```java
// Default access - no modifier specified
class DefaultClass {
    // Only accessible within same package
}

// Same as above - package-private
package com.example;
class AnotherClass {
    DefaultClass obj = new DefaultClass();  // OK - same package
}
```

### 63. What is the private access modifier?

Private access modifier restricts access to within the same class only. Private members cannot be accessed from outside the class, not even by subclasses. It's the most restrictive access level and is essential for encapsulation, hiding internal implementation details from other classes.

```java
public class Person {
    private String ssn;        // Only accessible within Person class
    private void validate() {  // Only callable within Person class
        // Internal validation logic
    }
    
    public void setSSN(String ssn) {
        validate();           // OK - same class
        this.ssn = ssn;       // OK - same class
    }
}
```

### 64. What is the default or package access modifier?

Default or package access means no access modifier is specified. Members with default access are accessible to all classes within the same package but not from classes in different packages. It's useful for creating package-level APIs where classes need to collaborate closely.

```java
package com.example;

class PackageClass {
    String name;              // Package access
    void doSomething() { }    // Package access
}

class AnotherClass {
    void test() {
        PackageClass obj = new PackageClass();
        obj.name = "Test";    // OK - same package
        obj.doSomething();    // OK - same package
    }
}
```

### 65. What is the protected access modifier?

Protected access allows access within the same package and by subclasses in any package. It's more permissive than default access because subclasses can access protected members even from different packages. It's commonly used for methods that subclasses need to override or access.

```java
package com.base;
public class Animal {
    protected void breathe() {  // Accessible to subclasses
        System.out.println("Breathing");
    }
}

package com.derived;
class Dog extends Animal {
    void bark() {
        breathe();  // OK - subclass can access protected method
    }
}
```

### 66. What is the public access modifier?

Public access modifier provides the widest accessibility - public members can be accessed from anywhere in the application, regardless of package or inheritance relationship. Use it for APIs that need to be available to all classes, but be careful not to expose internal implementation details.

```java
public class Calculator {
    public int add(int a, int b) {  // Accessible from anywhere
        return a + b;
    }
    
    public static final double PI = 3.14159;  // Global constant
}

// From any package
Calculator calc = new Calculator();
int result = calc.add(5, 3);  // OK - public method
```

### 67. What access types of variables can be accessed from a class in the same package?

From a class in the same package, you can access public, protected, and default (package-private) variables, but not private variables. Private variables are only accessible within the same class. This allows package-level collaboration while maintaining class-level encapsulation.

```java
package com.example;

class ClassA {
    public int publicVar = 1;     // Accessible
    protected int protectedVar = 2; // Accessible  
    int defaultVar = 3;           // Accessible
    private int privateVar = 4;   // NOT accessible
}

class ClassB {
    void test() {
        ClassA a = new ClassA();
        a.publicVar = 10;    // OK
        a.protectedVar = 20; // OK
        a.defaultVar = 30;   // OK
        // a.privateVar = 40; // Error - private
    }
}
```

### 68. What access types of variables can be accessed from a class in a different package?

From a class in a different package, you can only access public variables directly. Protected variables are accessible only if there's an inheritance relationship. Default and private variables are not accessible from different packages, providing package-level and class-level encapsulation respectively.

```java
package com.other;
import com.example.ClassA;

class ClassC {
    void test() {
        ClassA a = new ClassA();
        a.publicVar = 10;        // OK - public
        // a.protectedVar = 20;  // Error - different package, no inheritance
        // a.defaultVar = 30;    // Error - different package
        // a.privateVar = 40;    // Error - private
    }
}
```

### 69. What access types of variables can be accessed from a subclass in the same package?

From a subclass in the same package, you can access public, protected, and default variables, but not private variables. Being in the same package gives you default access, and being a subclass gives you protected access. Private variables remain inaccessible even to subclasses.

```java
package com.example;

class Parent {
    public int publicVar = 1;
    protected int protectedVar = 2;
    int defaultVar = 3;
    private int privateVar = 4;
}

class Child extends Parent {
    void test() {
        publicVar = 10;      // OK - public
        protectedVar = 20;   // OK - protected + subclass
        defaultVar = 30;     // OK - same package
        // privateVar = 40;  // Error - private not inherited
    }
}
```

### 70. What access types of variables can be accessed from a subclass in a different package?

From a subclass in a different package, you can access public and protected variables, but not default or private variables. The inheritance relationship allows access to protected members across packages, but default access is limited to the same package only.

```java
package com.other;
import com.example.Parent;

class RemoteChild extends Parent {
    void test() {
        publicVar = 10;      // OK - public
        protectedVar = 20;   // OK - protected + subclass
        // defaultVar = 30;  // Error - different package
        // privateVar = 40;  // Error - private
    }
}
```

### 71. What is the use of a final modifier on a class?

A final class cannot be extended or subclassed. Once you mark a class as final, no other class can inherit from it. This is useful for creating immutable classes or when you want to prevent inheritance for security or design reasons. String class is a famous example of a final class.

```java
final class ImmutableClass {
    private final String value;
    
    public ImmutableClass(String value) {
        this.value = value;
    }
    
    public String getValue() { return value; }
}

// class Child extends ImmutableClass { }  // Compilation error
```

### 72. What is the use of a final modifier on a method?

A final method cannot be overridden by subclasses. The method implementation is locked and subclasses must use it as-is. This ensures critical functionality remains unchanged and prevents accidental modification of important behavior in inheritance hierarchies.

```java
class Parent {
    final void criticalMethod() {
        System.out.println("This cannot be changed");
    }
    
    void normalMethod() {
        System.out.println("This can be overridden");
    }
}

class Child extends Parent {
    // void criticalMethod() { }  // Error - cannot override final method
    
    @Override
    void normalMethod() {  // OK - not final
        System.out.println("Overridden");
    }
}
```

### 73. What is a final variable?

A final variable is a constant that cannot be reassigned once initialized. For primitives, the value is constant. For objects, the reference is constant but the object's state can still change. Final variables must be initialized either at declaration or in the constructor.

```java
class Example {
    final int CONSTANT = 10;           // Initialized at declaration
    final List<String> list;           // Must initialize in constructor
    
    Example() {
        list = new ArrayList<>();      // Reference is final
        list.add("item");              // Object state can change
        // list = new ArrayList<>();   // Error - cannot reassign
    }
}
```

### 74. What is a final argument?

A final argument is a method parameter that cannot be reassigned within the method. The parameter value remains constant throughout the method execution. This prevents accidental modification of parameters and makes the code more predictable, especially useful in lambda expressions and inner classes.

```java
public void processData(final String input, final List<String> items) {
    // input = "new value";  // Error - cannot reassign final parameter
    // items = new ArrayList<>();  // Error - cannot reassign final parameter
    
    items.add("new item");  // OK - can modify object state
    System.out.println(input.toUpperCase());  // OK - can use the value
}
```

### 75. What happens when a variable is marked as volatile?

A volatile variable ensures visibility across threads and prevents compiler optimizations that might cache the variable. Every read gets the latest value from main memory, and every write immediately updates main memory. It's useful for flags and simple shared state but doesn't provide atomicity for compound operations.

```java
class SharedData {
    private volatile boolean flag = false;  // Visible to all threads
    private volatile int counter = 0;       // Always reads from memory
    
    public void setFlag() {
        flag = true;  // Immediately visible to other threads
    }
    
    public boolean isFlag() {
        return flag;  // Always gets latest value
    }
}
```

### 76. What is a static variable?

A static variable belongs to the class rather than any instance. There's only one copy shared by all objects of the class. It's initialized when the class is first loaded and exists for the entire program lifetime. Static variables are useful for constants, counters, or shared data.

```java
class Student {
    private static int totalStudents = 0;  // Shared by all instances
    private String name;                   // Instance variable
    
    public Student(String name) {
        this.name = name;
        totalStudents++;  // Increment shared counter
    }
    
    public static int getTotalStudents() {
        return totalStudents;  // Access without instance
    }
}
```

## Conditions & Loops
### 77. Why should you always use blocks around an if statement?

Using blocks with curly braces prevents bugs when adding more statements later. Without blocks, only the first statement is part of the if condition. Adding another statement without blocks can lead to logic errors that are hard to spot, especially with nested conditions.

```java
// Dangerous - without blocks
if (condition)
    doSomething();
    doSomethingElse();  // Always executes, not part of if!

// Safe - with blocks
if (condition) {
    doSomething();
    doSomethingElse();  // Both statements in if block
}

// Even for single statements, blocks prevent future bugs
if (condition) {
    doSomething();  // Easy to add more statements later
}
```

### 78. Should default be the last case in a switch statement?

Yes, default should typically be the last case in a switch statement for better readability and convention. However, it's not mandatory - default can appear anywhere and will execute if no other case matches. Placing it last makes the code more intuitive and follows standard practice.

```java
// Good practice - default at the end
switch (day) {
    case "Monday":
        System.out.println("Start of week");
        break;
    case "Friday":
        System.out.println("TGIF!");
        break;
    default:
        System.out.println("Regular day");
        break;
}
```

### 79. Can a switch statement be used around a String?

Yes, since Java 7, you can use String in switch statements. The comparison is done using equals() method, so it's case-sensitive. This makes string-based logic much cleaner than multiple if-else statements. Null strings will throw NullPointerException, so handle them separately.

```java
String action = "save";

switch (action) {
    case "save":
        saveDocument();
        break;
    case "load":
        loadDocument();
        break;
    case "delete":
        deleteDocument();
        break;
    default:
        System.out.println("Unknown action");
}
```

### 80. What is an enhanced for loop?

An enhanced for loop, also called for-each loop, simplifies iteration over collections and arrays. It automatically handles the iteration logic and eliminates index-based errors. You get each element directly without worrying about indices or iterators. It's cleaner and less error-prone than traditional loops.

```java
// Traditional for loop
int[] numbers = {1, 2, 3, 4, 5};
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// Enhanced for loop - much cleaner
for (int number : numbers) {
    System.out.println(number);
}

// Works with collections too
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}
```

## Exception Handling
### 81. Why is exception handling important?

Exception handling prevents programs from crashing when unexpected errors occur. It allows you to gracefully handle runtime problems like file not found, network issues, or invalid input. Without proper exception handling, a single error could terminate your entire application, making it unreliable and user-unfriendly.

```java
// Without exception handling - program crashes
int result = 10 / 0;  // ArithmeticException crashes program

// With exception handling - program continues
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
    // Program continues running
}
```

### 82. What design pattern is used to implement exception handling features in most languages?

The Chain of Responsibility pattern is used in exception handling. When an exception occurs, it's passed up the call stack until a suitable handler is found. Each method in the call chain has the opportunity to handle the exception or pass it to the next level, creating a chain of potential handlers.

```java
public void method1() {
    try {
        method2();
    } catch (IOException e) {
        // Handle or re-throw
    }
}

public void method2() throws IOException {
    method3();  // Exception bubbles up if not caught
}

public void method3() throws IOException {
    // Exception starts here and travels up the chain
}
```

### 83. What is the need for a finally block?

The finally block executes regardless of whether an exception occurs or not. It's essential for cleanup operations like closing files, database connections, or releasing resources. Even if an exception is thrown or a return statement is executed, finally ensures critical cleanup code always runs.

```java
FileInputStream file = null;
try {
    file = new FileInputStream("data.txt");
    // Process file
} catch (IOException e) {
    System.out.println("File error");
} finally {
    if (file != null) {
        file.close();  // Always executes - cleanup guaranteed
    }
}
```

### 84. In what scenarios is code in finally not executed?

Finally block doesn't execute in extreme cases: when System.exit() is called, JVM crashes, infinite loops in try/catch blocks, or when the thread is killed externally. Also, if the JVM runs out of memory or encounters a fatal error, finally might not execute. These are rare edge cases.

```java
try {
    System.exit(0);  // JVM terminates - finally won't run
} finally {
    System.out.println("This won't print");
}

try {
    while(true) { }  // Infinite loop - finally never reached
} finally {
    System.out.println("This won't execute");
}
```

### 85. Will finally be executed in the program below?

This question typically refers to a specific code example. Generally, finally executes even when there's a return statement in try or catch blocks. The finally block runs before the method actually returns, allowing for last-minute cleanup or modifications to return values in some cases.

```java
public int testFinally() {
    try {
        return 1;  // Finally still executes before return
    } finally {
        System.out.println("Finally executes");  // This prints
        // return 2;  // Would override the try return
    }
}
// Output: "Finally executes", then returns 1
```

### 86. Is try without a catch allowed?

Yes, you can have try without catch if you include a finally block. This is useful when you want to ensure cleanup happens but don't need to handle specific exceptions at that level. The exception will still propagate up the call stack to be handled elsewhere.

```java
public void processFile() throws IOException {
    FileInputStream file = null;
    try {
        file = new FileInputStream("data.txt");
        // Process file
    } finally {
        if (file != null) file.close();  // Cleanup guaranteed
    }
    // IOException propagates to caller if it occurs
}
```

### 87. Is try without catch and finally allowed?

No, you cannot have a try block without either a catch or finally block. Java requires at least one of them. A try block by itself serves no purpose since there's no exception handling or cleanup code. You need either catch to handle exceptions or finally for cleanup.

```java
// Not allowed - compilation error
try {
    riskyOperation();
}

// Must have at least one of these:
try {
    riskyOperation();
} catch (Exception e) { }

// Or:
try {
    riskyOperation();
} finally { }
```

### 88. Can you explain the hierarchy of exception handling classes?

Throwable is the root class for all exceptions and errors. It has two main subclasses: Exception for recoverable conditions and Error for serious problems. Exception further divides into checked exceptions (must be handled) and RuntimeException (unchecked). This hierarchy helps categorize different types of problems.

```java
// Hierarchy:
// Throwable
//   ├── Error (OutOfMemoryError, StackOverflowError)
//   └── Exception
//       ├── IOException, SQLException (checked)
//       └── RuntimeException
//           └── NullPointerException, IllegalArgumentException (unchecked)

try {
    // Code that might throw different exception types
} catch (RuntimeException e) {
    // Handles unchecked exceptions
} catch (Exception e) {
    // Handles all other exceptions
}
```

### 89. What is the difference between error and exception?

Errors represent serious problems that applications shouldn't try to handle, like OutOfMemoryError or StackOverflowError. Exceptions represent conditions that applications can reasonably handle, like FileNotFoundException or IllegalArgumentException. Errors indicate JVM or system-level problems, while exceptions indicate application-level problems.

```java
// Error - serious system problem, don't catch
public void causeError() {
    causeError();  // StackOverflowError - infinite recursion
}

// Exception - application problem, should handle
try {
    File file = new File("missing.txt");
    FileInputStream fis = new FileInputStream(file);  // FileNotFoundException
} catch (FileNotFoundException e) {
    System.out.println("File not found - handle gracefully");
}
```

### 90. What is the difference between checked exceptions and unchecked exceptions?

Checked exceptions must be declared in method signatures or handled with try-catch blocks - the compiler enforces this. Unchecked exceptions (RuntimeExceptions) don't require explicit handling. Checked exceptions represent recoverable conditions you should plan for, while unchecked exceptions usually indicate programming errors.

```java
// Checked exception - must handle or declare
public void readFile() throws IOException {  // Must declare
    FileInputStream file = new FileInputStream("data.txt");
}

// Or handle:
public void readFile() {
    try {
        FileInputStream file = new FileInputStream("data.txt");
    } catch (IOException e) {  // Must catch
        e.printStackTrace();
    }
}

// Unchecked exception - optional handling
public void divide(int a, int b) {
    int result = a / b;  // ArithmeticException possible, no requirement to handle
}
```

### 91. How do you throw an exception from a method?

Use the "throw" keyword followed by an exception object to throw an exception from a method. You must also declare it in the method signature using "throws" for checked exceptions. This allows you to signal error conditions and pass them up to the calling code for handling.

```java
public void validateAge(int age) throws IllegalArgumentException {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
    if (age > 150) {
        throw new IllegalArgumentException("Age seems unrealistic");
    }
}

// Usage
try {
    validateAge(-5);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
}
```

### 92. What happens when you throw a checked exception from a method?

When you throw a checked exception, you must declare it in the method signature using "throws". The calling method must either handle it with try-catch or declare it in its own throws clause. This creates a compile-time contract that forces proper exception handling throughout the call chain.

```java
public void readFile(String filename) throws IOException {
    FileInputStream file = new FileInputStream(filename);  // Checked exception
    // If IOException occurs, it propagates to caller
}

// Caller must handle or declare
public void processFile() {
    try {
        readFile("data.txt");
    } catch (IOException e) {  // Must handle
        System.out.println("File error: " + e.getMessage());
    }
}
```

### 93. What are the options you have to eliminate compilation errors when handling checked exceptions?

You have two options: either handle the exception with try-catch blocks, or declare it in your method signature using throws. You can also wrap it in an unchecked exception if appropriate. The choice depends on whether you can meaningfully handle the exception at that level or should pass it up.

```java
// Option 1: Handle with try-catch
public void option1() {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

// Option 2: Declare with throws
public void option2() throws InterruptedException {
    Thread.sleep(1000);
}

// Option 3: Wrap in unchecked exception
public void option3() {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
```

### 94. How do you create a custom exception?

Create a custom exception by extending Exception for checked exceptions or RuntimeException for unchecked exceptions. Provide constructors that accept messages and causes. Follow naming conventions by ending with "Exception". This allows you to create domain-specific error types for your application.

```java
// Custom checked exception
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Usage
public void withdraw(double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Insufficient funds: " + balance);
    }
}
```

### 95. How do you handle multiple exception types with the same exception handling block?

Since Java 7, you can use multi-catch syntax with the pipe (|) operator to handle multiple exception types in a single catch block. This reduces code duplication when you want to handle different exceptions the same way. The exceptions must not have inheritance relationships.

```java
try {
    // Code that might throw different exceptions
    parseNumber(input);
    accessArray(index);
} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
    System.out.println("Input error: " + e.getMessage());
    // Same handling for both exception types
}

// Before Java 7, you needed separate catch blocks:
// catch (NumberFormatException e) { ... }
// catch (ArrayIndexOutOfBoundsException e) { ... }
```

### 96. Can you explain about try-with-resources?

Try-with-resources automatically closes resources that implement AutoCloseable interface. You declare resources in parentheses after try, and Java automatically calls close() method when the block exits, even if an exception occurs. This eliminates the need for explicit finally blocks for resource cleanup.

```java
// Try-with-resources - automatic cleanup
try (FileInputStream file = new FileInputStream("data.txt");
     BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
    
    return reader.readLine();
    // file and reader automatically closed here
} catch (IOException e) {
    System.out.println("File error: " + e.getMessage());
}
// No need for finally block to close resources
```

### 97. How does try-with-resources work?

Try-with-resources works by calling the close() method on all declared resources when the try block exits, regardless of whether it completes normally or with an exception. Resources are closed in reverse order of declaration. If exceptions occur during closing, they're suppressed and attached to the main exception.

```java
// Multiple resources closed in reverse order
try (FileOutputStream out = new FileOutputStream("output.txt");  // Closed second
     FileInputStream in = new FileInputStream("input.txt")) {    // Closed first
    
    // Process files
    out.write(in.read());
    
} // Both resources automatically closed here
// If close() throws exceptions, they're suppressed
```

### 98. Can you explain a few exception handling best practices?

Key best practices: catch specific exceptions rather than generic Exception, don't ignore exceptions, log meaningful error messages, clean up resources properly, don't use exceptions for control flow, and fail fast with clear error messages. Always provide context about what went wrong and why.

```java
// Good practices
try {
    processFile(filename);
} catch (FileNotFoundException e) {
    logger.error("Configuration file not found: " + filename, e);
    throw new ConfigurationException("Cannot load config", e);
} catch (IOException e) {
    logger.error("Error reading file: " + filename, e);
    throw new ConfigurationException("Cannot read config", e);
}

// Avoid: catch (Exception e) { } // Too generic and ignores error
```

## Miscellaneous Topics
### 99. What are the default values in an array?

Array elements are automatically initialized to default values: 0 for numeric types (int, double, etc.), false for boolean, null for object references, and '\u0000' for char. This ensures arrays never contain garbage values and provides predictable initial state for all elements.

```java
int[] numbers = new int[5];        // All elements are 0
boolean[] flags = new boolean[3];  // All elements are false
String[] names = new String[4];    // All elements are null
char[] chars = new char[2];        // All elements are '\u0000'

System.out.println(numbers[0]);    // Prints: 0
System.out.println(flags[0]);      // Prints: false
System.out.println(names[0]);      // Prints: null
```

### 100. How do you loop around an array using an enhanced for loop?

Use the enhanced for loop syntax: "for (type element : array)". This automatically iterates through all elements without needing indices or length checks. It's cleaner, less error-prone, and more readable than traditional for loops. You get direct access to each element value.

```java
int[] numbers = {1, 2, 3, 4, 5};

// Enhanced for loop - clean and simple
for (int number : numbers) {
    System.out.println(number);
}

// Works with object arrays too
String[] names = {"Alice", "Bob", "Charlie"};
for (String name : names) {
    System.out.println("Hello, " + name);
}

// Compare to traditional loop:
// for (int i = 0; i < numbers.length; i++) { ... }
```

### 101. How do you print the content of an array?

Use Arrays.toString() for one-dimensional arrays or Arrays.deepToString() for multi-dimensional arrays. Don't use System.out.println() directly on arrays as it prints the memory reference, not the content. These utility methods format arrays nicely with square brackets and comma separation.

```java
int[] numbers = {1, 2, 3, 4, 5};
System.out.println(Arrays.toString(numbers));  // [1, 2, 3, 4, 5]

int[][] matrix = {{1, 2}, {3, 4}};
System.out.println(Arrays.deepToString(matrix));  // [[1, 2], [3, 4]]

// Don't do this:
System.out.println(numbers);  // Prints: [I@15db9742 (memory reference)
```

### 102. How do you compare two arrays?

Use Arrays.equals() for one-dimensional arrays or Arrays.deepEquals() for multi-dimensional arrays. Don't use == operator as it compares references, not content. These methods compare element by element and return true only if all corresponding elements are equal and arrays have the same length.

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
int[] arr3 = {1, 2, 4};

System.out.println(arr1 == arr2);              // false - different references
System.out.println(Arrays.equals(arr1, arr2)); // true - same content
System.out.println(Arrays.equals(arr1, arr3)); // false - different content

// For multi-dimensional arrays
int[][] matrix1 = {{1, 2}, {3, 4}};
int[][] matrix2 = {{1, 2}, {3, 4}};
System.out.println(Arrays.deepEquals(matrix1, matrix2)); // true
```

### 103. What is an enum?

An enum is a special class that represents a group of constants. It's type-safe and provides a fixed set of predefined values. Enums are more powerful than simple constants because they can have methods, constructors, and fields. They're perfect for representing things like days of the week, colors, or status values.

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public enum Status {
    PENDING("Waiting for approval"),
    APPROVED("Request approved"),
    REJECTED("Request rejected");
    
    private String description;
    
    Status(String description) {
        this.description = description;
    }
    
    public String getDescription() { return description; }
}
```

### 104. Can you use a switch statement around an enum?

Yes, enums work perfectly with switch statements and it's actually the preferred way to handle enum values. The switch is type-safe and the compiler can warn you if you miss any enum constants. You don't need to use the enum class name in case labels, just the constant names.

```java
public void processDay(Day day) {
    switch (day) {
        case MONDAY:
            System.out.println("Start of work week");
            break;
        case FRIDAY:
            System.out.println("TGIF!");
            break;
        case SATURDAY:
        case SUNDAY:
            System.out.println("Weekend!");
            break;
        default:
            System.out.println("Regular day");
    }
}
```

### 105. What are variable arguments or varargs?

Varargs allow methods to accept a variable number of arguments of the same type. Use three dots (...) after the type in the parameter list. Inside the method, varargs behave like an array. There can be only one varargs parameter and it must be the last parameter.

```java
public void printNumbers(int... numbers) {
    for (int num : numbers) {
        System.out.println(num);
    }
}

// Can be called with any number of arguments
printNumbers(1);           // One argument
printNumbers(1, 2, 3);     // Three arguments
printNumbers();            // No arguments

// Equivalent to: public void printNumbers(int[] numbers)
```

### 106. What are asserts used for?

Asserts are used to test assumptions in your code during development. They check conditions that should always be true and throw AssertionError if false. Asserts are disabled by default in production and must be enabled with -ea flag. Use them for debugging and testing, not for handling user input or recoverable errors.

```java
public void withdraw(double amount) {
    assert amount > 0 : "Amount must be positive";
    assert balance >= amount : "Insufficient funds";
    
    balance -= amount;
}

// Enable asserts: java -ea MyProgram
// If assertion fails: AssertionError with optional message
```

### 107. When should asserts be used?

Use asserts for internal consistency checks, preconditions, postconditions, and invariants that should never fail in correct code. Don't use them for argument validation in public methods, user input validation, or any condition that might legitimately occur. Asserts are for catching programming errors during development, not runtime errors.

```java
// Good uses:
assert index >= 0 && index < array.length : "Index out of bounds";
assert result != null : "Method should never return null";

// Bad uses - use regular exceptions instead:
// assert userInput != null;  // Use IllegalArgumentException
// assert fileExists;         // Use FileNotFoundException
```

### 108. What is garbage collection?

Garbage collection is Java's automatic memory management system that reclaims memory used by objects that are no longer reachable or referenced. It runs in the background, freeing developers from manual memory management. This prevents memory leaks and reduces programming errors, making Java applications more reliable and easier to develop.

```java
public void createObjects() {
    String temp = new String("temporary");  // Object created
    List<String> list = new ArrayList<>();
    // When method ends, 'temp' becomes unreachable
    // Garbage collector will eventually reclaim its memory
}

// Objects become eligible for GC when:
// - No references point to them
// - References go out of scope
// - References are set to null
```

### 109. Can you explain garbage collection with an example?

When objects have no more references pointing to them, they become eligible for garbage collection. The GC automatically identifies these unreachable objects and frees their memory. For example, when a local variable goes out of scope or you set a reference to null, the object may be collected.

```java
public void demonstrateGC() {
    String str1 = new String("Hello");     // Object 1 created
    String str2 = new String("World");     // Object 2 created
    
    str1 = null;  // Object 1 becomes eligible for GC
    str2 = str1;  // Object 2 also becomes eligible for GC
    
    // Both objects can now be garbage collected
    System.gc();  // Suggests GC to run (not guaranteed)
}
```

### 110. When is garbage collection run?

Garbage collection runs automatically when the JVM determines it's needed, typically when heap memory is getting full. You can't control exactly when it runs, but you can suggest it with System.gc() (though it's not guaranteed to run immediately). The JVM uses various algorithms and strategies to optimize GC timing for performance.

```java
// GC typically runs when:
// - Heap memory is low
// - JVM decides it's optimal
// - During application idle time

// You can suggest GC (but don't rely on it):
System.gc();  // Just a suggestion to JVM

// Better approach: let JVM handle it automatically
// Focus on creating fewer objects and nullifying references when done
String largeData = processLargeFile();
// ... use largeData
largeData = null;  // Help GC by removing reference
```

### 111. What are best practices on garbage collection?

Don't call System.gc() explicitly, let the JVM handle it automatically. Minimize object creation in loops, reuse objects when possible, and set large object references to null when done. Use appropriate data structures and avoid memory leaks by removing listeners and closing resources. Profile your application to understand memory usage patterns.

```java
// Good practices:
StringBuilder sb = new StringBuilder();  // Reuse instead of creating many Strings
for (int i = 0; i < 1000; i++) {
    sb.append(i).append(" ");  // Better than: result += i + " ";
}

// Help GC by nullifying large objects
byte[] largeArray = new byte[1000000];
// ... use array
largeArray = null;  // Help GC reclaim memory

// Avoid: System.gc();  // Let JVM decide when to run GC
```

### 112. What are initialization blocks?

Initialization blocks are code blocks that run when an object is created, before the constructor executes. Instance initialization blocks run for every object creation, while static initialization blocks run once when the class is first loaded. They're useful for complex initialization logic that's common across multiple constructors.

```java
public class Example {
    static int staticVar;
    int instanceVar;
    
    // Static initialization block - runs once when class loads
    static {
        staticVar = 100;
        System.out.println("Static block executed");
    }
    
    // Instance initialization block - runs before every constructor
    {
        instanceVar = 50;
        System.out.println("Instance block executed");
    }
    
    public Example() {
        System.out.println("Constructor executed");
    }
}
```

### 113. What is a static initializer?

A static initializer is a static block that runs once when the class is first loaded into memory. It's used to initialize static variables or perform one-time setup operations. Static initializers execute in the order they appear in the class, before any static methods are called or static variables are accessed.

```java
public class DatabaseConfig {
    private static Properties config;
    private static String dbUrl;
    
    // Static initializer - runs once when class loads
    static {
        config = new Properties();
        try {
            config.load(new FileInputStream("db.properties"));
            dbUrl = config.getProperty("database.url");
        } catch (IOException e) {
            dbUrl = "jdbc:mysql://localhost:3306/default";
        }
    }
}
```

### 114. What is an instance initializer block?

An instance initializer block is a block of code that runs every time an object is created, before any constructor executes. It's useful for initialization code that's common to all constructors. Multiple instance blocks execute in the order they appear in the class, followed by the constructor.

```java
public class Student {
    private String name;
    private int id;
    private static int nextId = 1;
    
    // Instance initializer - runs before every constructor
    {
        id = nextId++;
        System.out.println("Assigning ID: " + id);
    }
    
    public Student(String name) {
        this.name = name;  // Constructor runs after initializer
    }
    
    public Student() {
        this("Unknown");   // Both constructors benefit from initializer
    }
}
```

### 115. What is tokenizing?

Tokenizing is breaking down a string into smaller parts called tokens based on delimiters. Java provides StringTokenizer class and String.split() method for this. It's commonly used for parsing CSV files, processing user input, or analyzing text data by separating words, numbers, or other meaningful units.

```java
// Using StringTokenizer
String data = "apple,banana,orange";
StringTokenizer tokenizer = new StringTokenizer(data, ",");
while (tokenizer.hasMoreTokens()) {
    System.out.println(tokenizer.nextToken());
}

// Using String.split() - more modern approach
String[] tokens = data.split(",");
for (String token : tokens) {
    System.out.println(token.trim());
}
```

### 116. Can you give an example of tokenizing?

Here's a practical example of tokenizing a sentence into words and processing CSV data. You can use different delimiters like spaces, commas, or custom characters. StringTokenizer and split() method are the most common approaches for breaking strings into meaningful pieces.

```java
// Tokenizing a sentence
String sentence = "Java is a powerful programming language";
String[] words = sentence.split(" ");
for (String word : words) {
    System.out.println("Word: " + word);
}

// Tokenizing CSV data
String csvLine = "John,25,Engineer,New York";
String[] fields = csvLine.split(",");
System.out.println("Name: " + fields[0]);
System.out.println("Age: " + fields[1]);
System.out.println("Job: " + fields[2]);
System.out.println("City: " + fields[3]);
```

### 117. What is serialization?

Serialization is converting an object into a byte stream so it can be saved to a file, sent over a network, or stored in a database. The object's state is preserved and can be reconstructed later through deserialization. The class must implement Serializable interface for this to work.

```java
// Serializable class
public class Person implements Serializable {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Serialization converts object to bytes for storage/transmission
// Deserialization recreates object from bytes
```

### 118. How do you serialize an object using the Serializable interface?

Implement the Serializable interface in your class, then use ObjectOutputStream to write the object to a stream. The serialization process automatically handles converting the object's state into bytes. All instance variables are serialized unless marked as transient.

```java
public class Person implements Serializable {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Serialize object to file
Person person = new Person("John", 30);
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
    oos.writeObject(person);
}
```

### 119. How do you de-serialize in Java?

Use ObjectInputStream to read the serialized object from a stream and cast it back to the original type. The class definition must be available in the classpath, and the serialVersionUID should match. Deserialization recreates the object with its original state.

```java
// Deserialize object from file
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
    Person person = (Person) ois.readObject();
    System.out.println("Name: " + person.getName());
    System.out.println("Age: " + person.getAge());
} catch (ClassNotFoundException e) {
    System.out.println("Person class not found");
}
```

### 120. What do you do if only parts of the object have to be serialized?

Use the transient keyword to exclude fields from serialization. Transient fields are not saved during serialization and are set to default values during deserialization. You can also implement custom serialization methods writeObject() and readObject() for more control over the process.

```java
public class User implements Serializable {
    private String username;
    private transient String password;  // Not serialized for security
    private transient int sessionId;    // Not serialized - temporary data
    
    // Custom serialization method
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();  // Serialize non-transient fields
        // Add custom logic here if needed
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();   // Deserialize non-transient fields
        // Initialize transient fields if needed
        sessionId = generateNewSessionId();
    }
}
```

### 121. How do you serialize a hierarchy of objects?

When serializing object hierarchies, all classes in the inheritance chain must implement Serializable. If a parent class doesn't implement Serializable, it must have a no-argument constructor. The serialization process automatically handles the entire object graph, including inherited fields from parent classes.

```java
// Parent class must be Serializable or have no-arg constructor
class Animal implements Serializable {
    protected String species;
    
    public Animal(String species) {
        this.species = species;
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog(String species, String breed) {
        super(species);
        this.breed = breed;
    }
}

// Serializing Dog will include Animal fields too
Dog dog = new Dog("Canine", "Labrador");
// Both species and breed are serialized
```

### 122. Are the constructors in an object invoked when it is de-serialized?

No, constructors are not called during deserialization. Java uses a special mechanism to create objects without calling constructors. However, if a parent class doesn't implement Serializable, its no-argument constructor will be called. This is why non-serializable parent classes need a default constructor.

```java
public class Person implements Serializable {
    private String name;
    
    public Person() {
        System.out.println("Constructor called");  // Not printed during deserialization
    }
    
    public Person(String name) {
        this.name = name;
    }
}

// During deserialization:
// - No constructor is called
// - Object is created directly from serialized data
// - Fields are restored to their serialized values
```

### 123. Are the values of static variables stored when an object is serialized?

No, static variables are not serialized because they belong to the class, not to individual instances. Static variables maintain their current values in the JVM when objects are deserialized. If you need to preserve static state, you must handle it separately from object serialization.

```java
public class Counter implements Serializable {
    private static int totalCount = 0;  // Not serialized
    private int instanceCount;          // Serialized
    
    public Counter() {
        totalCount++;
        instanceCount = totalCount;
    }
}

// After deserialization:
// - instanceCount is restored from serialized data
// - totalCount keeps its current JVM value (not restored)
```

## Collections
### 124. Why do we need collections in Java?

Collections provide dynamic data structures that can grow and shrink at runtime, unlike fixed-size arrays. They offer powerful operations like searching, sorting, and filtering. Collections also provide different data structures optimized for specific use cases - lists for ordered data, sets for unique elements, maps for key-value pairs.

```java
// Arrays are fixed size
int[] array = new int[5];  // Cannot change size

// Collections are dynamic
List<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.remove(0);  // Can add/remove elements

// Rich operations
Collections.sort(list);
boolean contains = list.contains(2);
```

### 125. What are the important interfaces in the collection hierarchy?

The main interfaces are Collection (root interface), List (ordered, allows duplicates), Set (no duplicates), Queue (FIFO operations), and Map (key-value pairs). List includes ArrayList and LinkedList. Set includes HashSet and TreeSet. Map includes HashMap and TreeMap. Each serves different data organization needs.

```java
// Collection hierarchy
Collection<String> col = new ArrayList<>();  // Root interface
List<String> list = new ArrayList<>();       // Ordered, duplicates allowed
Set<String> set = new HashSet<>();           // No duplicates
Queue<String> queue = new LinkedList<>();    // FIFO operations
Map<String, Integer> map = new HashMap<>();  // Key-value pairs
```

### 126. What are the important methods that are declared in the collection interface?

Key methods include add() to insert elements, remove() to delete elements, contains() to check existence, size() to get count, isEmpty() to check if empty, clear() to remove all elements, and iterator() to traverse elements. These methods provide basic operations common to all collection types.

```java
Collection<String> collection = new ArrayList<>();

collection.add("apple");           // Add element
collection.remove("apple");        // Remove element
boolean has = collection.contains("apple");  // Check existence
int count = collection.size();     // Get size
boolean empty = collection.isEmpty();        // Check if empty
collection.clear();                // Remove all elements

Iterator<String> iter = collection.iterator();  // Get iterator
```

### 127. Can you explain briefly about the List interface?

List is an ordered collection that allows duplicate elements and provides indexed access. Elements maintain their insertion order and can be accessed by position. List extends Collection and adds methods like get(), set(), add(index, element), and remove(index) for positional operations.

```java
List<String> list = new ArrayList<>();
list.add("first");      // Index 0
list.add("second");     // Index 1
list.add("first");      // Index 2 - duplicates allowed

String item = list.get(1);           // Access by index: "second"
list.set(1, "modified");             // Replace at index
list.add(1, "inserted");             // Insert at specific position
String removed = list.remove(0);     // Remove by index
```

### 128. Explain about ArrayList with an example?

ArrayList is a resizable array implementation of the List interface. It provides fast random access by index but slower insertion and deletion in the middle. It's the most commonly used List implementation, perfect when you need frequent access by index and don't do many insertions or deletions.

```java
ArrayList<String> fruits = new ArrayList<>();

// Adding elements
fruits.add("apple");
fruits.add("banana");
fruits.add("orange");

// Accessing elements
String first = fruits.get(0);        // "apple"
fruits.set(1, "grape");              // Replace "banana" with "grape"

// Size and operations
int size = fruits.size();            // 3
boolean hasApple = fruits.contains("apple");  // true
fruits.remove("orange");             // Remove by value
```

### 129. Can an ArrayList have duplicate elements?

Yes, ArrayList allows duplicate elements because it implements the List interface, which permits duplicates. You can add the same object or value multiple times, and each will occupy a separate position in the list. This is different from Set collections, which don't allow duplicates.

```java
ArrayList<String> list = new ArrayList<>();
list.add("apple");
list.add("banana");
list.add("apple");    // Duplicate allowed
list.add("apple");    // Another duplicate allowed

System.out.println(list.size());  // 4 - all elements counted
// Output: [apple, banana, apple, apple]

// Compare with Set:
Set<String> set = new HashSet<>(list);
System.out.println(set.size());   // 2 - duplicates removed
```

### 130. How do you iterate around an ArrayList using iterator?

Use the iterator() method to get an Iterator object, then use hasNext() to check for more elements and next() to get the next element. Iterator provides a safe way to traverse and optionally remove elements during iteration without causing ConcurrentModificationException.

```java
ArrayList<String> list = new ArrayList<>();
list.add("apple");
list.add("banana");
list.add("orange");

// Using Iterator
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String fruit = iterator.next();
    System.out.println(fruit);
    
    if (fruit.equals("banana")) {
        iterator.remove();  // Safe removal during iteration
    }
}

// Enhanced for loop is simpler for read-only iteration
for (String fruit : list) {
    System.out.println(fruit);
}
```

### 131. How do you sort an ArrayList?

Use Collections.sort() method to sort an ArrayList. For objects, they must implement Comparable interface or you can provide a custom Comparator. Collections.sort() uses an efficient merge sort algorithm and modifies the original list. For primitive wrapper types like Integer or String, sorting works automatically.

```java
// Sorting strings (natural order)
ArrayList<String> names = new ArrayList<>();
names.add("Charlie");
names.add("Alice");
names.add("Bob");
Collections.sort(names);  // [Alice, Bob, Charlie]

// Sorting integers
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(3);
numbers.add(1);
numbers.add(2);
Collections.sort(numbers);  // [1, 2, 3]
```

### 132. How do you sort elements in an ArrayList using the Comparable interface?

Implement the Comparable interface in your class and override the compareTo() method. This defines the natural ordering for your objects. Then use Collections.sort() which will automatically use your compareTo() implementation to determine the sort order.

```java
class Person implements Comparable<Person> {
    String name;
    int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);  // Sort by name
    }
}

ArrayList<Person> people = new ArrayList<>();
people.add(new Person("Bob", 25));
people.add(new Person("Alice", 30));
Collections.sort(people);  // Sorted by name using compareTo()
```

### 133. How do you sort elements in an ArrayList using the Comparator interface?

Create a Comparator object that defines custom sorting logic and pass it to Collections.sort(). This allows multiple sorting criteria without modifying the original class. You can use lambda expressions or method references for cleaner code in Java 8+.

```java
ArrayList<Person> people = new ArrayList<>();
people.add(new Person("Bob", 25));
people.add(new Person("Alice", 30));

// Sort by age using Comparator
Collections.sort(people, new Comparator<Person>() {
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.age, p2.age);
    }
});

// Java 8+ lambda expression - much cleaner
Collections.sort(people, (p1, p2) -> Integer.compare(p1.age, p2.age));
// Or even simpler:
people.sort(Comparator.comparing(p -> p.age));
```

### 134. What is the Vector class? How is it different from an ArrayList?

Vector is a legacy synchronized version of ArrayList. All Vector methods are synchronized, making it thread-safe but slower than ArrayList. Vector also grows by 100% when it needs more space, while ArrayList grows by 50%. Use ArrayList for single-threaded applications and Vector only when you need thread safety.

```java
// Vector - thread-safe, slower
Vector<String> vector = new Vector<>();
vector.add("item1");  // Synchronized method

// ArrayList - not thread-safe, faster
ArrayList<String> list = new ArrayList<>();
list.add("item1");    // Not synchronized

// For thread safety with ArrayList, use:
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

### 135. What is LinkedList? What interfaces does it implement? How is it different from an ArrayList?

LinkedList implements both List and Deque interfaces, providing doubly-linked list functionality. It's efficient for insertions and deletions at any position but slower for random access by index. ArrayList is better for frequent access by index, while LinkedList is better for frequent insertions and deletions.

```java
LinkedList<String> linkedList = new LinkedList<>();

// Efficient operations in LinkedList
linkedList.addFirst("first");    // O(1) - fast
linkedList.addLast("last");      // O(1) - fast
linkedList.add(1, "middle");     // O(n) - but faster than ArrayList for middle insertions

// ArrayList is faster for:
ArrayList<String> arrayList = new ArrayList<>();
String item = arrayList.get(100);  // O(1) - direct index access

// LinkedList is slower for:
String item2 = linkedList.get(100);  // O(n) - must traverse nodes
```

### 136. Can you briefly explain the Set interface?

Set is a collection that doesn't allow duplicate elements. It models the mathematical set abstraction and extends the Collection interface. Sets are perfect when you need to ensure uniqueness of elements. Common implementations include HashSet for fast operations, LinkedHashSet for insertion order, and TreeSet for sorted order.

```java
Set<String> set = new HashSet<>();
set.add("apple");
set.add("banana");
set.add("apple");    // Duplicate - ignored

System.out.println(set.size());  // 2, not 3
System.out.println(set);         // [apple, banana] - no duplicates

// Useful for removing duplicates from lists
List<String> listWithDuplicates = Arrays.asList("a", "b", "a", "c");
Set<String> uniqueItems = new HashSet<>(listWithDuplicates);
```

### 137. What are the important interfaces related to the Set interface?

The main Set-related interfaces are Set (basic set operations), SortedSet (maintains sorted order), and NavigableSet (adds navigation methods). SortedSet extends Set and provides ordering. NavigableSet extends SortedSet and adds methods like lower(), higher(), ceiling(), and floor() for navigation.

```java
// Set - basic operations
Set<Integer> basicSet = new HashSet<>();

// SortedSet - maintains order
SortedSet<Integer> sortedSet = new TreeSet<>();
sortedSet.add(3);
sortedSet.add(1);
sortedSet.add(2);
System.out.println(sortedSet);  // [1, 2, 3] - automatically sorted

// NavigableSet - navigation methods
NavigableSet<Integer> navSet = new TreeSet<>();
navSet.addAll(Arrays.asList(1, 3, 5, 7, 9));
Integer lower = navSet.lower(5);    // 3 - largest element < 5
Integer higher = navSet.higher(5);  // 7 - smallest element > 5
```

### 138. What is the difference between Set and SortedSet interfaces?

Set provides basic set operations without any ordering guarantee. SortedSet extends Set and maintains elements in sorted order according to their natural ordering or a provided Comparator. SortedSet adds methods like first(), last(), headSet(), and tailSet() for working with sorted elements.

```java
// Regular Set - no ordering guarantee
Set<String> set = new HashSet<>();
set.add("zebra");
set.add("apple");
set.add("banana");
// Order is unpredictable: might be [banana, apple, zebra]

// SortedSet - maintains sorted order
SortedSet<String> sortedSet = new TreeSet<>();
sortedSet.add("zebra");
sortedSet.add("apple");
sortedSet.add("banana");
System.out.println(sortedSet);  // [apple, banana, zebra] - always sorted

String first = sortedSet.first();  // "apple"
String last = sortedSet.last();    // "zebra"
```

### 139. Can you give examples of classes that implement the Set interface?

Main Set implementations are HashSet (fastest, no ordering), LinkedHashSet (maintains insertion order), and TreeSet (sorted order). HashSet uses hash table for O(1) operations, LinkedHashSet adds linked list for order, and TreeSet uses red-black tree for O(log n) operations with sorting.

```java
// HashSet - fastest, no order guarantee
Set<String> hashSet = new HashSet<>();
hashSet.add("c");
hashSet.add("a");
hashSet.add("b");
// Output order: unpredictable

// LinkedHashSet - maintains insertion order
Set<String> linkedSet = new LinkedHashSet<>();
linkedSet.add("c");
linkedSet.add("a");
linkedSet.add("b");
// Output order: [c, a, b] - insertion order preserved

// TreeSet - sorted order
Set<String> treeSet = new TreeSet<>();
treeSet.add("c");
treeSet.add("a");
treeSet.add("b");
// Output order: [a, b, c] - natural sorted order
```

### 140. What is a HashSet?

HashSet is a hash table-based implementation of the Set interface. It offers constant-time O(1) performance for basic operations like add, remove, and contains. HashSet doesn't maintain any order of elements and doesn't allow duplicates. It's the fastest Set implementation for most operations.

```java
HashSet<String> hashSet = new HashSet<>();

// Fast operations - O(1) average case
hashSet.add("apple");      // O(1)
hashSet.add("banana");     // O(1)
hashSet.add("apple");      // Duplicate ignored

boolean contains = hashSet.contains("apple");  // O(1) - very fast
boolean removed = hashSet.remove("banana");    // O(1) - very fast

System.out.println(hashSet.size());  // 1 - only unique elements
// No guaranteed order: elements may appear in any sequence
```

### 141. What is a LinkedHashSet? How is it different from a HashSet?

LinkedHashSet maintains insertion order while HashSet doesn't guarantee any order. LinkedHashSet uses a hash table with a linked list to preserve the order elements were added. It's slightly slower than HashSet due to the extra overhead of maintaining links, but still provides O(1) performance for basic operations.

```java
// HashSet - no order guarantee
Set<String> hashSet = new HashSet<>();
hashSet.add("third");
hashSet.add("first");
hashSet.add("second");
System.out.println(hashSet);  // Unpredictable order: [first, second, third]

// LinkedHashSet - maintains insertion order
Set<String> linkedHashSet = new LinkedHashSet<>();
linkedHashSet.add("third");
linkedHashSet.add("first");
linkedHashSet.add("second");
System.out.println(linkedHashSet);  // [third, first, second] - insertion order
```

### 142. What is a TreeSet? How is it different from a HashSet?

TreeSet maintains elements in sorted order using a red-black tree structure, while HashSet uses a hash table with no ordering. TreeSet operations are O(log n) compared to HashSet's O(1), but TreeSet automatically keeps elements sorted. Elements must be Comparable or you must provide a Comparator.

```java
// HashSet - fast but no order
Set<Integer> hashSet = new HashSet<>();
hashSet.add(3);
hashSet.add(1);
hashSet.add(2);
System.out.println(hashSet);  // Unpredictable: [1, 2, 3] or any order

// TreeSet - slower but always sorted
Set<Integer> treeSet = new TreeSet<>();
treeSet.add(3);
treeSet.add(1);
treeSet.add(2);
System.out.println(treeSet);  // Always: [1, 2, 3] - natural order

// TreeSet provides navigation methods
Integer first = treeSet.first();   // 1
Integer last = treeSet.last();     // 3
```

### 143. Can you give examples of implementations of NavigableSet?

TreeSet is the main implementation of NavigableSet. NavigableSet extends SortedSet and provides navigation methods like lower(), higher(), ceiling(), floor(), and methods to get subsets. These methods help you find elements relative to a given value in the sorted set.

```java
NavigableSet<Integer> navSet = new TreeSet<>();
navSet.addAll(Arrays.asList(1, 3, 5, 7, 9));

// Navigation methods
Integer lower = navSet.lower(5);     // 3 - largest element < 5
Integer higher = navSet.higher(5);   // 7 - smallest element > 5
Integer ceiling = navSet.ceiling(4); // 5 - smallest element >= 4
Integer floor = navSet.floor(6);     // 5 - largest element <= 6

// Subset operations
NavigableSet<Integer> headSet = navSet.headSet(5, false);  // [1, 3]
NavigableSet<Integer> tailSet = navSet.tailSet(5, true);   // [5, 7, 9]
```

### 144. Explain briefly about the Queue interface?

Queue represents a collection designed for holding elements before processing, typically in FIFO (First-In-First-Out) order. It extends Collection and adds methods like offer() to insert, poll() to remove and retrieve, and peek() to examine without removing. It's perfect for task scheduling and buffering.

```java
Queue<String> queue = new LinkedList<>();

// Adding elements (FIFO)
queue.offer("first");   // Add to rear
queue.offer("second");
queue.offer("third");

// Removing elements (FIFO order)
String first = queue.poll();   // "first" - removes and returns head
String peek = queue.peek();    // "second" - examines without removing

System.out.println(queue);     // [second, third]
```

### 145. What are the important interfaces related to the Queue interface?

Key Queue-related interfaces include Queue (basic FIFO operations), Deque (double-ended queue), BlockingQueue (thread-safe with blocking operations), and PriorityQueue (heap-based priority ordering). Each serves different use cases from simple queues to concurrent programming and priority-based processing.

```java
// Basic Queue - FIFO
Queue<String> queue = new LinkedList<>();

// Deque - both ends operations
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("front");
deque.addLast("back");

// BlockingQueue - thread-safe
BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

// PriorityQueue - priority-based ordering
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
priorityQueue.offer(3);
priorityQueue.offer(1);
priorityQueue.offer(2);
System.out.println(priorityQueue.poll());  // 1 - smallest first
```

### 146. Explain about the Deque interface?

Deque (Double Ended Queue) allows insertion and removal at both ends. It extends Queue and provides methods like addFirst(), addLast(), removeFirst(), removeLast(). Deque can function as both a queue (FIFO) and a stack (LIFO), making it very versatile for different data structure needs.

```java
Deque<String> deque = new ArrayDeque<>();

// Add to both ends
deque.addFirst("front1");    // Add to front
deque.addLast("back1");      // Add to back
deque.addFirst("front2");    // Add to front again

// Current state: [front2, front1, back1]

// Remove from both ends
String fromFront = deque.removeFirst();  // "front2"
String fromBack = deque.removeLast();    // "back1"

// Use as stack (LIFO)
deque.push("item");          // Add to front
String popped = deque.pop(); // Remove from front
```

### 147. Explain the BlockingQueue interface?

BlockingQueue extends Queue and provides thread-safe operations with blocking behavior. Methods like put() block when the queue is full, and take() blocks when the queue is empty. It's essential for producer-consumer scenarios where threads need to wait for space or elements to become available.

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);  // Capacity 2

// Producer thread
new Thread(() -> {
    try {
        queue.put("item1");  // Succeeds
        queue.put("item2");  // Succeeds
        queue.put("item3");  // Blocks until space available
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}).start();

// Consumer thread
new Thread(() -> {
    try {
        String item = queue.take();  // Blocks if queue is empty
        System.out.println("Consumed: " + item);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}).start();
```

### 148. What is a PriorityQueue?

PriorityQueue is a heap-based priority queue where elements are ordered by their natural ordering or a provided Comparator. The head is always the smallest element according to the ordering. It's not thread-safe and doesn't allow null elements. Perfect for scheduling tasks by priority.

```java
// Natural ordering (smallest first)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(5);
pq.offer(2);
pq.offer(8);
pq.offer(1);

System.out.println(pq.poll());  // 1 - smallest
System.out.println(pq.poll());  // 2 - next smallest

// Custom ordering (largest first)
PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
maxPQ.offer(5);
maxPQ.offer(2);
maxPQ.offer(8);
System.out.println(maxPQ.poll());  // 8 - largest first
```

### 149. Can you give example implementations of the BlockingQueue interface?

Main BlockingQueue implementations include ArrayBlockingQueue (fixed-size array), LinkedBlockingQueue (optionally bounded linked list), PriorityBlockingQueue (unbounded priority queue), and SynchronousQueue (no storage, direct handoff). Each has different characteristics for capacity and performance.

```java
// ArrayBlockingQueue - fixed capacity
BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(10);

// LinkedBlockingQueue - optionally bounded
BlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>();  // Unbounded
BlockingQueue<String> boundedLinked = new LinkedBlockingQueue<>(100);  // Bounded

// PriorityBlockingQueue - unbounded, priority-ordered
BlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();

// SynchronousQueue - no storage, direct handoff
BlockingQueue<String> syncQueue = new SynchronousQueue<>();

// Usage example
arrayQueue.put("item");     // Blocks if full
String item = arrayQueue.take();  // Blocks if empty
```

### 150. Can you briefly explain about the Map interface?

Map represents key-value pairs where each key maps to exactly one value. It's not part of the Collection hierarchy but is a fundamental part of the Collections framework. Maps don't allow duplicate keys but can have duplicate values. Common implementations include HashMap, TreeMap, and LinkedHashMap.

```java
Map<String, Integer> map = new HashMap<>();

// Adding key-value pairs
map.put("apple", 5);
map.put("banana", 3);
map.put("orange", 8);

// Accessing values
Integer apples = map.get("apple");        // 5
boolean hasKey = map.containsKey("banana"); // true
boolean hasValue = map.containsValue(8);    // true

// Iterating over map
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Size and removal
int size = map.size();           // 3
Integer removed = map.remove("banana");  // 3
```

### 151. What is the difference between Map and SortedMap?

Map provides basic key-value operations without any ordering guarantee. SortedMap extends Map and maintains keys in sorted order according to their natural ordering or a provided Comparator. SortedMap adds methods like firstKey(), lastKey(), headMap(), and tailMap() for working with sorted keys.

```java
// Regular Map - no ordering guarantee
Map<String, Integer> map = new HashMap<>();
map.put("zebra", 1);
map.put("apple", 2);
map.put("banana", 3);
// Key order is unpredictable

// SortedMap - maintains sorted key order
SortedMap<String, Integer> sortedMap = new TreeMap<>();
sortedMap.put("zebra", 1);
sortedMap.put("apple", 2);
sortedMap.put("banana", 3);
System.out.println(sortedMap);  // {apple=2, banana=3, zebra=1} - keys sorted

String firstKey = sortedMap.firstKey();  // "apple"
String lastKey = sortedMap.lastKey();    // "zebra"
```

### 152. What is a HashMap?

HashMap is a hash table-based implementation of the Map interface that provides O(1) average-time performance for basic operations like get and put. It allows one null key and multiple null values. HashMap doesn't maintain any order of keys and is not thread-safe. It's the most commonly used Map implementation.

```java
HashMap<String, Integer> map = new HashMap<>();

// Basic operations - O(1) average case
map.put("apple", 5);       // Add key-value pair
map.put("banana", 3);
map.put(null, 0);          // Null key allowed

Integer value = map.get("apple");     // 5 - fast retrieval
boolean hasKey = map.containsKey("banana");  // true
Integer removed = map.remove("apple");       // 5

// Size and checking
int size = map.size();           // 2
boolean isEmpty = map.isEmpty(); // false
```

### 153. What are the different methods in a HashMap?

Key HashMap methods include put() to add entries, get() to retrieve values, remove() to delete entries, containsKey() and containsValue() to check existence, keySet() to get all keys, values() to get all values, and entrySet() to get key-value pairs for iteration.

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("apple", 5);
map.put("banana", 3);

// Access methods
Integer value = map.get("apple");              // 5
Integer defaultValue = map.getOrDefault("orange", 0);  // 0

// Check methods
boolean hasApple = map.containsKey("apple");   // true
boolean hasValue3 = map.containsValue(3);      // true

// Collection views
Set<String> keys = map.keySet();               // [apple, banana]
Collection<Integer> values = map.values();     // [5, 3]
Set<Map.Entry<String, Integer>> entries = map.entrySet();  // For iteration
```

### 154. What is a TreeMap? How is it different from a HashMap?

TreeMap is a red-black tree implementation that maintains keys in sorted order, while HashMap uses a hash table with no ordering. TreeMap operations are O(log n) compared to HashMap's O(1), but TreeMap keeps keys automatically sorted. TreeMap doesn't allow null keys but allows null values.

```java
// HashMap - fast but no order
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("zebra", 1);
hashMap.put("apple", 2);
hashMap.put("banana", 3);
// Order: unpredictable

// TreeMap - slower but sorted
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("zebra", 1);
treeMap.put("apple", 2);
treeMap.put("banana", 3);
System.out.println(treeMap);  // {apple=2, banana=3, zebra=1} - always sorted

// TreeMap provides navigation
TreeMap<String, Integer> navMap = new TreeMap<>();
navMap.putAll(treeMap);
String lowerKey = navMap.lowerKey("banana");  // "apple"
```

### 155. Can you give an example of implementation of NavigableMap interface?

TreeMap is the main implementation of NavigableMap. NavigableMap extends SortedMap and provides navigation methods like lowerKey(), higherKey(), ceilingKey(), floorKey(), and methods to get submaps. These help you navigate through the sorted keys efficiently.

```java
NavigableMap<Integer, String> navMap = new TreeMap<>();
navMap.put(1, "one");
navMap.put(3, "three");
navMap.put(5, "five");
navMap.put(7, "seven");

// Navigation methods
Integer lowerKey = navMap.lowerKey(5);     // 3 - largest key < 5
Integer higherKey = navMap.higherKey(5);   // 7 - smallest key > 5
Integer ceilingKey = navMap.ceilingKey(4); // 5 - smallest key >= 4
Integer floorKey = navMap.floorKey(6);     // 5 - largest key <= 6

// Submap operations
NavigableMap<Integer, String> headMap = navMap.headMap(5, false);  // {1=one, 3=three}
NavigableMap<Integer, String> tailMap = navMap.tailMap(5, true);   // {5=five, 7=seven}
```

## Functional Programming (Java 8+)
### 156. What is the functional interface - Predicate?

Predicate is a functional interface that represents a boolean-valued function of one argument. It has a single abstract method test() that takes an argument and returns true or false. It's commonly used for filtering operations in streams and provides default methods like and(), or(), and negate().

```java
// Using Predicate to test conditions
Predicate<Integer> isEven = num -> num % 2 == 0;
Predicate<String> isLong = str -> str.length() > 5;

System.out.println(isEven.test(4));    // true
System.out.println(isEven.test(3));    // false
System.out.println(isLong.test("hello world"));  // true

// Combining predicates
Predicate<Integer> isPositiveEven = isEven.and(num -> num > 0);
System.out.println(isPositiveEven.test(4));   // true
System.out.println(isPositiveEven.test(-4));  // false

// Using with streams
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evenNumbers = numbers.stream()
    .filter(isEven)
    .collect(Collectors.toList());  // [2, 4, 6]
```

### 157. What is the functional interface - Function?

Function is a functional interface that represents a function accepting one argument and producing a result. It has a single abstract method apply() and provides default methods like compose() and andThen() for function composition. It's widely used for transforming data in streams.

```java
// Function to transform data
Function<String, Integer> stringLength = str -> str.length();
Function<Integer, Integer> square = num -> num * num;

System.out.println(stringLength.apply("hello"));  // 5
System.out.println(square.apply(4));               // 16

// Function composition
Function<String, Integer> lengthSquared = stringLength.andThen(square);
System.out.println(lengthSquared.apply("hello"));  // 25 (5 squared)

// Using with streams
List<String> words = Arrays.asList("java", "python", "javascript");
List<Integer> lengths = words.stream()
    .map(stringLength)
    .collect(Collectors.toList());  // [4, 6, 10]
```

### 158. What is a Consumer?

Consumer is a functional interface that represents an operation accepting a single input argument and returning no result. It has a single abstract method accept() and a default method andThen() for chaining consumers. It's perfect for operations that consume data without returning anything, like printing or logging.

```java
// Consumer for side effects
Consumer<String> printer = str -> System.out.println(str);
Consumer<String> upperPrinter = str -> System.out.println(str.toUpperCase());

printer.accept("hello");        // Prints: hello
upperPrinter.accept("world");   // Prints: WORLD

// Chaining consumers
Consumer<String> combined = printer.andThen(upperPrinter);
combined.accept("java");        // Prints: java, then JAVA

// Using with streams
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream().forEach(printer);  // Prints each name

// Common use case
names.forEach(name -> System.out.println("Hello, " + name));
```

### 159. Can you give examples of functional interfaces with multiple arguments?

Java provides BiPredicate (two arguments, returns boolean), BiFunction (two arguments, returns result), BiConsumer (two arguments, no return), and you can create custom functional interfaces. These handle operations that need multiple inputs while maintaining the functional programming style.

```java
// BiPredicate - two arguments, returns boolean
BiPredicate<String, Integer> lengthCheck = (str, len) -> str.length() == len;
System.out.println(lengthCheck.test("hello", 5));  // true

// BiFunction - two arguments, returns result
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
BiFunction<String, String, String> concat = (s1, s2) -> s1 + s2;
System.out.println(add.apply(3, 4));           // 7
System.out.println(concat.apply("Hello", " World"));  // "Hello World"

// BiConsumer - two arguments, no return
BiConsumer<String, Integer> printWithCount = (str, count) -> 
    System.out.println(str + " appears " + count + " times");
printWithCount.accept("Java", 3);  // Prints: Java appears 3 times

// Custom functional interface
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
TriFunction<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
```

## New Features
### 160. What are the new features in Java 5?

Java 5 introduced major features: Generics for type safety, Enhanced for loops (for-each), Autoboxing/Unboxing between primitives and wrappers, Enums for type-safe constants, Varargs for variable arguments, Static imports, and Annotations for metadata. These features significantly improved code safety and readability.

```java
// Generics - type safety
List<String> list = new ArrayList<String>();  // Before: List list = new ArrayList();

// Enhanced for loop
for (String item : list) {
    System.out.println(item);
}

// Autoboxing/Unboxing
Integer num = 5;        // Autoboxing: int to Integer
int value = num;        // Unboxing: Integer to int

// Enums
enum Day { MONDAY, TUESDAY, WEDNESDAY }

// Varargs
public void print(String... messages) {
    for (String msg : messages) {
        System.out.println(msg);
    }
}

// Static imports
import static java.lang.Math.PI;
double area = PI * radius * radius;

// Annotations
@Override
public String toString() { return "example"; }
```

### 161. What are the new features in Java 6?

Java 6 introduced scripting support with JSR 223, allowing you to run JavaScript and other scripting languages from Java. It added compiler API for programmatic compilation, improved web services with JAX-WS, better JDBC 4.0 support, and significant performance improvements. Desktop integration was enhanced with system tray and splash screen support.

```java
// Scripting support - run JavaScript from Java
ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("JavaScript");
engine.eval("print('Hello from JavaScript')");

// Compiler API - compile Java code programmatically
JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
int result = compiler.run(null, null, null, "MyClass.java");

// Desktop integration
if (Desktop.isDesktopSupported()) {
    Desktop desktop = Desktop.getDesktop();
    desktop.browse(new URI("http://example.com"));
}

// System tray support
SystemTray tray = SystemTray.getSystemTray();
```

### 162. What are the new features in Java 7?

Java 7 brought diamond operator for generics, strings in switch statements, try-with-resources for automatic resource management, multi-catch exception handling, binary literals, and underscores in numeric literals. The fork/join framework was added for parallel processing, and NIO.2 provided better file system operations.

```java
// Diamond operator - type inference
List<String> list = new ArrayList<>();  // No need to repeat <String>

// Strings in switch
String day = "Monday";
switch (day) {
    case "Monday": System.out.println("Start of week"); break;
    case "Friday": System.out.println("TGIF!"); break;
}

// Try-with-resources
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // File automatically closed
}

// Multi-catch
try {
    // risky code
} catch (IOException | SQLException e) {
    System.out.println("Error: " + e.getMessage());
}

// Binary literals and underscores
int binary = 0b1010;           // Binary literal
long million = 1_000_000L;     // Underscores for readability
```

### 163. What are the new features in Java 8?

Java 8 was a major release introducing lambda expressions, functional interfaces, streams API for data processing, method references, default methods in interfaces, Optional class to handle null values, and the new Date/Time API. These features enabled functional programming style and made code more concise and readable.

```java
// Lambda expressions
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println(name));

// Streams API
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Method references
names.stream().forEach(System.out::println);

// Default methods in interfaces
interface Drawable {
    void draw();
    default void print() {
        System.out.println("Printing...");
    }
}

// Optional to handle null
Optional<String> optional = Optional.ofNullable(getName());
optional.ifPresent(System.out::println);

// New Date/Time API
LocalDate today = LocalDate.now();
LocalDateTime now = LocalDateTime.now();
```