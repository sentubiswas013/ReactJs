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
- **Encapsulation:** Bundling data and methods together, hiding internal details
- **Inheritance:** Creating new classes based on existing classes
- **Polymorphism:** Same interface, different implementations
- **Abstraction:** Hiding complex implementation details, showing only essential features

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

Abstract classes provide a way to share code among related classes while enforcing that certain methods must be implemented by subclasses.