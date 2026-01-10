# Core Java Basics 

## 1. What is the difference between JDK and JRE?

**Answer:** JRE is the runtime environment that runs Java applications, while JDK is the development kit that includes JRE plus development tools.

- **JRE**: Contains JVM, core libraries, and runtime components
- **JDK**: Contains JRE + compiler (javac), debugger, and other development tools

```java
// You need JDK to compile this
javac HelloWorld.java

// You need JRE to run this
java HelloWorld
```

## 2. What is Java Virtual Machine (JVM)?

**Answer:** JVM is a runtime environment that executes Java bytecode. It provides platform independence by converting bytecode to machine-specific code.

- Acts as an intermediary between Java code and operating system
- Handles memory management and garbage collection
- Provides security through bytecode verification

```java
// Java source code (.java) → Bytecode (.class) → Machine code (JVM)
public class Demo {
    public static void main(String[] args) {
        System.out.println("JVM executes this bytecode");
    }
}
```

## 3. What are the different types of memory areas allocated by JVM?

**Answer:** JVM allocates several memory areas for different purposes during program execution.

- **Heap Memory**: Stores objects and instance variables
- **Stack Memory**: Stores method calls and local variables
- **Method Area**: Stores class-level data and static variables
- **PC Register**: Tracks currently executing instruction
- **Native Method Stack**: For native method calls

```java
public class MemoryDemo {
    static int staticVar = 100;        // Method Area
    
    public void method() {
        int localVar = 50;             // Stack Memory
        String obj = new String("Hi"); // Object in Heap Memory
    }
}
```

## 4. What is JIT Compiler?

**Answer:** JIT (Just-In-Time) Compiler improves performance by compiling frequently used bytecode to native machine code at runtime.

- Identifies "hot spots" - frequently executed code blocks
- Compiles bytecode to optimized machine code
- Caches compiled code for future use

```java
public class JITDemo {
    public static void main(String[] args) {
        // This loop will be optimized by JIT compiler
        for (int i = 0; i < 1000000; i++) {
            calculate(i);
        }
    }
    
    static int calculate(int n) {
        return n * n + 10;
    }
}
```

## 5. How is Java Platform different from other platforms?

**Answer:** Java platform is software-based, while others are typically hardware-based. It provides platform independence through JVM.

- **Hardware Platform**: CPU + Operating System
- **Java Platform**: JVM + Java API
- Write once, run anywhere capability
- Automatic memory management

```java
// Same code runs on Windows, Linux, Mac
public class PlatformIndependent {
    public static void main(String[] args) {
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Java Version: " + System.getProperty("java.version"));
    }
}
```

## 6. Why do people say that Java is 'Write Once and Run Anywhere' language?

**Answer:** Java source code compiles to platform-neutral bytecode that runs on any system with JVM installed.

- Source code → Bytecode (platform independent)
- JVM translates bytecode to machine-specific code
- Same .class file runs on different operating systems

```java
// Compile once: javac Calculator.java
public class Calculator {
    public static void main(String[] args) {
        int result = add(5, 3);
        System.out.println("Result: " + result);
    }
    
    static int add(int a, int b) {
        return a + b;
    }
}
// Run anywhere: java Calculator (on Windows/Linux/Mac)
```

## 7. How does ClassLoader work in Java?

**Answer:** ClassLoader dynamically loads Java classes into JVM memory when needed. It follows delegation hierarchy.

- **Bootstrap ClassLoader**: Loads core Java classes
- **Extension ClassLoader**: Loads extension classes
- **Application ClassLoader**: Loads application classes
- **Custom ClassLoader**: User-defined loaders

```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Get class loader information
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("Class Loader: " + loader);
        
        // Load class dynamically
        try {
            Class<?> clazz = Class.forName("java.util.ArrayList");
            System.out.println("Loaded: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## 8. Do you think 'main' used for main method is a keyword in Java?

**Answer:** No, 'main' is not a keyword in Java. It's just a method name that JVM looks for as the entry point.

- Keywords are reserved words like `class`, `public`, `static`
- 'main' is an identifier that can be used elsewhere
- JVM specifically searches for this method signature

```java
public class MainDemo {
    // 'main' as method name - entry point
    public static void main(String[] args) {
        System.out.println("Entry point");
        main(); // Calling another method named 'main'
    }
    
    // 'main' as regular method name - allowed
    public static void main() {
        System.out.println("Another main method");
    }
}
```

## 9. Can we write main method as public void static instead of public static void?

**Answer:** No, the order of modifiers matters in Java. The correct order is `public static void`, not `public void static`.

- Java follows specific modifier ordering rules
- `static` must come before return type `void`
- Compilation error occurs with wrong order

```java
// ✅ Correct - Compiles and runs
public class CorrectMain {
    public static void main(String[] args) {
        System.out.println("Correct order");
    }
}

// ❌ Incorrect - Compilation error
public class WrongMain {
    public void static main(String[] args) { // Error!
        System.out.println("Wrong order");
    }
}
```

## 10. What will be the default value of local variables if we do not specify any value?

**Answer:** Local variables do NOT have default values in Java. You must initialize them before use, or you'll get a compilation error.

- Instance variables have default values
- Local variables must be explicitly initialized
- Compiler prevents use of uninitialized local variables

```java
public class VariableDemo {
    int instanceVar; // Default value: 0
    
    public void method() {
        int localVar; // No default value
        
        System.out.println(instanceVar); // ✅ Prints: 0
        // System.out.println(localVar);  // ❌ Compilation error
        
        localVar = 10; // Must initialize
        System.out.println(localVar); // ✅ Prints: 10
    }
}
```

## 11. What is the difference between byte and char data types in Java?

**Answer:** `byte` stores signed 8-bit integers (-128 to 127), while `char` stores unsigned 16-bit Unicode characters (0 to 65535).

- **byte**: 1 byte, signed, numeric values
- **char**: 2 bytes, unsigned, Unicode characters
- Different purposes and value ranges

```java
public class DataTypeDemo {
    public static void main(String[] args) {
        byte b = 100;           // 8-bit signed integer
        char c = 'A';           // 16-bit Unicode character
        char c2 = 65;           // Same as 'A' (ASCII value)
        
        System.out.println("Byte: " + b);        // 100
        System.out.println("Char: " + c);        // A
        System.out.println("Char as int: " + (int)c); // 65
        
        // Range demonstration
        byte maxByte = 127;     // Max byte value
        char maxChar = 65535;   // Max char value
    }
}
```

### Object-Oriented Programming (OOP)
## 12. What are the main principles of Object-Oriented Programming (OOP)?

**Answer:** OOP has four main principles: Encapsulation, Inheritance, Polymorphism, and Abstraction. These principles help create modular, reusable, and maintainable code.

- **Encapsulation**: Data hiding using private fields and public methods
- **Inheritance**: Code reuse through parent-child relationships
- **Polymorphism**: Same interface, different implementations
- **Abstraction**: Hiding implementation details

```java
// Encapsulation
class Student {
    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
}

// Inheritance
class Person { protected String name; }
class Employee extends Person { private int id; }

// Polymorphism
interface Shape { void draw(); }
class Circle implements Shape { public void draw() { System.out.println("Circle"); } }
```

## 13. What is the difference between Object-Oriented Programming language and Object-Based Programming language?

**Answer:** Object-Oriented languages support all OOP principles including inheritance, while Object-Based languages support objects but may lack inheritance.

- **Object-Oriented**: Java, C++, C# (supports inheritance)
- **Object-Based**: JavaScript (ES5), VB.NET (limited inheritance)
- Key difference is inheritance support

```java
// Object-Oriented (Java) - Full inheritance support
class Animal {
    void eat() { System.out.println("Eating"); }
}

class Dog extends Animal {  // Inheritance supported
    void bark() { System.out.println("Barking"); }
}

// Object-Based languages may not support this inheritance
```

## 14. What is the default value of an object reference defined as an instance variable?

**Answer:** The default value of an object reference as an instance variable is `null`. This applies to all reference types including String, arrays, and custom objects.

- Primitive types have specific default values (0, false, etc.)
- All reference types default to `null`
- Local variables have no default values

```java
public class DefaultValues {
    String name;        // Default: null
    int[] numbers;      // Default: null
    Student student;    // Default: null
    int age;           // Default: 0 (primitive)
    
    public void display() {
        System.out.println("Name: " + name);           // null
        System.out.println("Numbers: " + numbers);     // null
        System.out.println("Student: " + student);     // null
        System.out.println("Age: " + age);             // 0
    }
}
```

## 15. Why do we need a constructor in Java?

**Answer:** Constructors initialize objects when they're created. They set up initial state, allocate resources, and ensure objects are ready to use immediately after creation.

- Initialize instance variables with meaningful values
- Perform setup operations like opening files or connections
- Ensure object is in valid state from creation

```java
class BankAccount {
    private String accountNumber;
    private double balance;
    
    // Constructor ensures proper initialization
    public BankAccount(String accNum, double initialBalance) {
        this.accountNumber = accNum;
        this.balance = initialBalance;
        System.out.println("Account created: " + accNum);
    }
    
    public double getBalance() { return balance; }
}

// Usage
BankAccount account = new BankAccount("12345", 1000.0);
```

## 16. Why do we need a default constructor in Java classes?

**Answer:** Default constructor provides a no-argument way to create objects. Java automatically provides one if you don't define any constructor, but you lose it when you add parameterized constructors.

- Allows object creation without parameters
- Required for frameworks like Spring, Hibernate
- Needed for array initialization of objects

```java
class Product {
    private String name;
    private double price;
    
    // Default constructor
    public Product() {
        this.name = "Unknown";
        this.price = 0.0;
    }
    
    // Parameterized constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Both ways work
Product p1 = new Product();                    // Uses default
Product p2 = new Product("Laptop", 50000);     // Uses parameterized
```

## 17. What is the value returned by constructor in Java?

**Answer:** Constructors don't return any value, not even void. They implicitly return the reference to the newly created object, but you cannot use return statements with values.

- No return type specified in constructor declaration
- Cannot use `return value;` statements
- JVM handles object reference return automatically

```java
class Car {
    private String model;
    
    // Constructor - no return type
    public Car(String model) {
        this.model = model;
        // return model;  // ❌ Compilation error
        // return;        // ✅ Allowed but not needed
    }
    
    // Regular method - has return type
    public String getModel() {
        return model;  // ✅ Returns value
    }
}

Car car = new Car("Toyota"); // Constructor implicitly returns Car reference
```

## 18. Can we inherit a constructor?

**Answer:** No, constructors are not inherited in Java. Child classes must define their own constructors, but they can call parent constructors using `super()`.

- Constructors belong to specific class only
- Child class can call parent constructor with `super()`
- If no constructor defined, Java provides default constructor

```java
class Vehicle {
    protected String brand;
    
    public Vehicle(String brand) {
        this.brand = brand;
    }
}

class Bike extends Vehicle {
    private int wheels;
    
    // Must define own constructor
    public Bike(String brand, int wheels) {
        super(brand);        // Call parent constructor
        this.wheels = wheels;
    }
    
    // Cannot inherit Vehicle(String) constructor directly
}

Bike bike = new Bike("Honda", 2);
```

## 19. Why constructors cannot be final, static, or abstract in Java?

**Answer:** Constructors have special purposes that conflict with these modifiers. They're called automatically during object creation and cannot be overridden or called independently.

- **final**: Constructors aren't inherited, so no overriding to prevent
- **static**: Constructors need object context, static belongs to class
- **abstract**: Constructors must have implementation for object creation

```java
class Example {
    // ❌ All these are compilation errors
    // public final Example() { }     // Error: final not allowed
    // public static Example() { }    // Error: static not allowed  
    // public abstract Example();     // Error: abstract not allowed
    
    // ✅ Valid constructor
    public Example() {
        System.out.println("Valid constructor");
    }
}

// Constructors are automatically called during object creation
Example obj = new Example(); // Constructor called implicitly
```

---

*This document covers the fundamental Java concepts essential for technical interviews. Each answer is designed to be concise yet comprehensive, with practical code examples.*