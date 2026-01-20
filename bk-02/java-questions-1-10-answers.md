# 🔹 Core Java Fundamentals

## Basic Concepts

### 1. What is Java and what are its key features?

Java is a high-level, object-oriented programming language developed by Sun Microsystems (now Oracle). 

**Key Features:**
- Platform independence ("Write Once, Run Anywhere")
- Object-oriented programming
- Automatic memory management (Garbage Collection)
- Strong type checking
- Multithreading support
- Robust security features
- Rich API and libraries

### 2. Explain the difference between JDK, JRE, and JVM.

**JVM (Java Virtual Machine):**
- Executes Java bytecode
- Platform-specific runtime engine
- Provides runtime environment

**JRE (Java Runtime Environment):**
- Includes JVM + standard libraries
- Required to run Java applications
- No development tools included

**JDK (Java Development Kit):**
- Includes JRE + development tools
- Contains compiler (javac), debugger, documentation tools
- Required for Java development

### 3. What is platform independence in Java?

Platform independence means Java code can run on any operating system without modification. Java achieves this by:
- Compiling source code to platform-neutral bytecode
- JVM interprets bytecode on specific platforms
- Same compiled code works across different operating systems

### 4. How does Java achieve "write once, run anywhere"?

Java achieves WORA through:
1. **Compilation:** Source code (.java) compiles to bytecode (.class)
2. **Bytecode:** Platform-neutral intermediate code
3. **JVM:** Platform-specific virtual machine interprets bytecode
4. **Result:** Same bytecode runs on any platform with JVM

### 5. What are the main principles of Object-Oriented Programming?

The four main OOP principles are:

**1. Encapsulation:**
- Data hiding and bundling
- Private fields with public methods

**2. Inheritance:**
- Code reuse through parent-child relationships
- "IS-A" relationship

**3. Polymorphism:**
- Multiple forms of same method
- Method overloading and overriding

**4. Abstraction:**
- Hiding implementation details
- Focus on what object does, not how

### 6. Explain the difference between class and object.

**Class:**
- Blueprint or template
- Defines structure and behavior
- No memory allocation until instantiated
- Example: `class Car { ... }`

**Object:**
- Instance of a class
- Actual entity in memory
- Has specific values for class attributes
- Example: `Car myCar = new Car();`

### 7. What is encapsulation and how is it implemented in Java?

**Encapsulation** is the principle of hiding internal implementation details and exposing only necessary functionality.

**Implementation in Java:**
```java
public class Student {
    private String name;  // Private field
    private int age;      // Private field
    
    // Public getter method
    public String getName() {
        return name;
    }
    
    // Public setter method with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
```

**Benefits:**
- Data protection
- Controlled access
- Flexibility to change implementation

### 8. What is inheritance and what are its types?

**Inheritance** allows a class to acquire properties and methods from another class.

**Types in Java:**

**1. Single Inheritance:**
```java
class Animal { }
class Dog extends Animal { }
```

**2. Multilevel Inheritance:**
```java
class Animal { }
class Mammal extends Animal { }
class Dog extends Mammal { }
```

**3. Hierarchical Inheritance:**
```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
```

**Note:** Java doesn't support multiple inheritance of classes to avoid diamond problem.

### 9. What is polymorphism? Explain with examples.

**Polymorphism** means "many forms" - same method name behaving differently based on context.

**Types:**

**1. Compile-time Polymorphism (Method Overloading):**
```java
class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
}
```

**2. Runtime Polymorphism (Method Overriding):**
```java
class Animal {
    public void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    public void sound() { System.out.println("Bark"); }
}

Animal animal = new Dog();
animal.sound(); // Output: "Bark"
```

### 10. What is abstraction and how is it achieved in Java?

**Abstraction** hides complex implementation details while showing only essential features to the user.

**Achievement in Java:**

**1. Abstract Classes (Partial Abstraction):**
```java
abstract class Shape {
    abstract void draw();  // Abstract method
    void display() {       // Concrete method
        System.out.println("Displaying shape");
    }
}
```

**2. Interfaces (Complete Abstraction):**
```java
interface Drawable {
    void draw();  // All methods are abstract by default
}
```

