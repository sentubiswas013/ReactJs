## Inheritance

### 36. What is single inheritance in Java?

**Single inheritance** means a class can extend only one parent class directly.

#### **Key Characteristics:**
- **One parent class** per child class
- **Clear hierarchy** - no ambiguity
- **All non-private members** inherited
- **Simplifies design** and maintenance

#### **Example:**
```java
// Parent class
class Animal {
    protected String name;
    
    public void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class - single inheritance
class Dog extends Animal {
    public void bark() {
        System.out.println("Dog is barking");
    }
}

// Usage
Dog dog = new Dog();
dog.eat();   // Inherited from Animal
dog.bark();  // Own method
```

#### **Inheritance Chain:**
```java
class Animal { }
class Mammal extends Animal { }      // Mammal IS-A Animal
class Dog extends Mammal { }         // Dog IS-A Mammal (and Animal)

// Dog inherits from Mammal, which inherits from Animal
```

#### **Benefits:**
- **Simplicity** - clear parent-child relationship
- **No ambiguity** - single source of inheritance
- **Easy maintenance** - changes propagate clearly

### 37. Why doesn't Java support multiple inheritance?

Java doesn't support **multiple inheritance of classes** to avoid complexity and ambiguity.

#### **Problems with Multiple Inheritance:**

**1. Diamond Problem:**
```java
// This is NOT allowed in Java
class A {
    public void method() { System.out.println("A"); }
}

class B extends A {
    public void method() { System.out.println("B"); }
}

class C extends A {
    public void method() { System.out.println("C"); }
}

// class D extends B, C { }  // ✗ NOT allowed - which method() to inherit?
```

**2. Complexity Issues:**
- **Constructor chaining** becomes complex
- **Method resolution** ambiguity
- **Memory layout** complications
- **Maintenance difficulties**

#### **Java's Solution:**
```java
// Single inheritance + Multiple interface implementation
class Animal {
    public void eat() { }
}

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck extends Animal implements Flyable, Swimmable {
    public void fly() { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
}
```

#### **Benefits of Java's Approach:**
- **Clear method resolution**
- **Simplified design**
- **Better maintainability**
- **Type safety**

### 38. What is the diamond problem?

The **diamond problem** occurs when a class inherits from two classes that both inherit from the same base class.

#### **Diamond Problem Illustration:**
```
    A
   / \
  B   C
   \ /
    D
```

#### **The Problem:**
```java
// Hypothetical scenario (NOT valid Java)
class A {
    public void method() { System.out.println("A's method"); }
}

class B extends A {
    public void method() { System.out.println("B's method"); }
}

class C extends A {
    public void method() { System.out.println("C's method"); }
}

// If this were allowed:
// class D extends B, C {
//     // Which method() should D inherit?
//     // B's method() or C's method()?
// }
```

#### **Ambiguity Questions:**
- Which `method()` should class D inherit?
- How many instances of class A should D contain?
- Which constructor chain should be followed?

#### **Real-world Example:**
```java
// This creates confusion
class Vehicle {
    protected int wheels;
}

class Car extends Vehicle {
    public Car() { wheels = 4; }
}

class Boat extends Vehicle {
    public Boat() { wheels = 0; }
}

// If multiple inheritance were allowed:
// class AmphibiousCar extends Car, Boat {
//     // How many wheels? 4 or 0?
// }
```

### 39. How does Java solve the diamond problem?

Java solves the diamond problem through **single inheritance + interfaces**.

#### **1. Single Class Inheritance:**
```java
// Only one class can be extended
class Animal {
    public void breathe() { System.out.println("Breathing"); }
}

class Mammal extends Animal {
    public void giveBirth() { System.out.println("Giving birth"); }
}

class Dog extends Mammal {  // Single inheritance chain
    public void bark() { System.out.println("Barking"); }
}
```

#### **2. Multiple Interface Implementation:**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck extends Animal implements Flyable, Swimmable {
    public void fly() { System.out.println("Flying"); }
    public void swim() { System.out.println("Swimming"); }
}
```

#### **3. Default Method Conflicts (Java 8+):**
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
        A.super.method();  // Explicitly choose A's method
        // or B.super.method();  // or choose B's method
        // or provide own implementation
    }
}
```

#### **Benefits:**
- **Clear resolution** - explicit choice required
- **Type safety** maintained
- **Flexibility** through interfaces
- **No ambiguity** in method calls

### 40. What is the use of super keyword?

The **super keyword** provides access to parent class members.

#### **1. Calling Parent Constructor:**
```java
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name);        // Call parent constructor
        this.breed = breed;
    }
}
```

#### **2. Accessing Parent Methods:**
```java
class Animal {
    public void display() {
        System.out.println("I am an animal");
    }
}

class Dog extends Animal {
    @Override
    public void display() {
        super.display();    // Call parent method
        System.out.println("I am a dog");
    }
}
```

#### **3. Accessing Parent Fields:**
```java
class Animal {
    protected String type = "Animal";
}

class Dog extends Animal {
    private String type = "Dog";
    
    public void showTypes() {
        System.out.println("Child type: " + this.type);  // "Dog"
        System.out.println("Parent type: " + super.type); // "Animal"
    }
}
```

#### **Rules:**
- **super()** must be first statement in constructor
- **Cannot use** in static context
- **Cannot chain** super.super.method()

### 41. What is method hiding?

**Method hiding** occurs when a child class defines a static method with the same signature as a parent class static method.

#### **Method Hiding Example:**
```java
class Parent {
    public static void staticMethod() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {
    public static void staticMethod() {  // Method hiding
        System.out.println("Child static method");
    }
}

// Usage
Parent.staticMethod();  // "Parent static method"
Child.staticMethod();   // "Child static method"

Parent p = new Child();
p.staticMethod();       // "Parent static method" - reference type matters!
```

#### **Method Hiding vs Overriding:**

| Aspect | Method Hiding | Method Overriding |
|--------|---------------|-------------------|
| **Method Type** | Static | Instance |
| **Binding** | Compile-time | Runtime |
| **Reference Type** | Matters | Doesn't matter |
| **Polymorphism** | No | Yes |

#### **Demonstration:**
```java
class Test {
    public static void main(String[] args) {
        Parent p1 = new Parent();
        Parent p2 = new Child();
        Child c = new Child();
        
        p1.staticMethod();  // "Parent static method"
        p2.staticMethod();  // "Parent static method" (reference type)
        c.staticMethod();   // "Child static method"
    }
}
```

### 42. Can you override static methods?

**No**, you cannot override static methods. Static methods belong to the class, not instances.

#### **Why Static Methods Cannot Be Overridden:**
- **Class-level binding** - belongs to class, not object
- **No polymorphism** - resolved at compile time
- **Memory allocation** - single copy per class
- **No dynamic dispatch** - reference type determines method

#### **What Happens Instead:**
```java
class Parent {
    public static void method() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    public static void method() {  // Method hiding, NOT overriding
        System.out.println("Child static");
    }
}

// Demonstration
Parent p = new Child();
p.method();  // Output: "Parent static" (not "Child static")
```

#### **Attempting to Override:**
```java
class Parent {
    public static void method() { }
}

class Child extends Parent {
    // @Override  // ✗ Compiler error if uncommented
    public static void method() { }  // This is hiding, not overriding
}
```

#### **Instance Method Overriding (for comparison):**
```java
class Parent {
    public void instanceMethod() {
        System.out.println("Parent instance");
    }
}

class Child extends Parent {
    @Override
    public void instanceMethod() {  // True overriding
        System.out.println("Child instance");
    }
}

Parent p = new Child();
p.instanceMethod();  // Output: "Child instance" (polymorphism works)
```

### 43. Can you override private methods?

**No**, you cannot override private methods because they are not inherited.

#### **Why Private Methods Cannot Be Overridden:**
- **Not inherited** - child class cannot see private methods
- **No access** - private methods are class-specific
- **No polymorphism** - not part of class interface
- **Encapsulation** - private methods are implementation details

#### **Example:**
```java
class Parent {
    private void privateMethod() {
        System.out.println("Parent private method");
    }
    
    public void callPrivate() {
        privateMethod();  // Calls Parent's private method
    }
}

class Child extends Parent {
    // This is NOT overriding - it's a new method
    private void privateMethod() {
        System.out.println("Child private method");
    }
    
    public void testPrivate() {
        privateMethod();  // Calls Child's own private method
        callPrivate();    // Calls Parent's private method via public method
    }
}
```

#### **Demonstration:**
```java
Child child = new Child();
child.testPrivate();
// Output:
// "Child private method"
// "Parent private method"
```

#### **What You Can Do:**
```java
class Parent {
    protected void protectedMethod() {  // Use protected instead
        System.out.println("Parent protected");
    }
}

class Child extends Parent {
    @Override
    protected void protectedMethod() {  // Now this is overriding
        System.out.println("Child protected");
    }
}
```

### 44. What is covariant return type?

**Covariant return type** allows an overriding method to return a subtype of the return type declared in the parent method.

#### **Basic Example:**
```java
class Animal {
    public Animal reproduce() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog reproduce() {  // Covariant return - Dog is subtype of Animal
        return new Dog();
    }
}
```

#### **Rules:**
- **Return type** must be subclass of parent's return type
- **Primitive types** cannot be covariant
- **Must maintain** IS-A relationship

#### **Practical Example:**
```java
abstract class Shape {
    public abstract Shape clone();
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public Circle clone() {  // Returns Circle, not Shape
        return new Circle(this.radius);
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public Rectangle clone() {  // Returns Rectangle, not Shape
        return new Rectangle(this.width, this.height);
    }
}
```

#### **Benefits:**
- **Type safety** - no casting required
- **Better API** - more specific return types
- **Cleaner code** - eliminates type casting

#### **Invalid Examples:**
```java
class Parent {
    public String method() { return "parent"; }
}

class Child extends Parent {
    // public Object method() { }  // ✗ Error - Object is supertype of String
    // public int method() { }     // ✗ Error - different type hierarchy
}
```

### 45. What is the difference between IS-A and HAS-A relationship?

| Aspect | IS-A Relationship | HAS-A Relationship |
|--------|-------------------|-------------------|
| **Type** | Inheritance | Composition |
| **Keyword** | `extends` | Instance variables |
| **Relationship** | Child is a type of Parent | Class contains another class |
| **Coupling** | Tight coupling | Loose coupling |
| **Flexibility** | Less flexible | More flexible |

#### **IS-A Relationship (Inheritance):**
```java
// Dog IS-A Animal
class Animal {
    protected String name;
    
    public void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {  // Dog IS-A Animal
    public void bark() {
        System.out.println("Dog is barking");
    }
}

// Usage
Dog dog = new Dog();
Animal animal = dog;  // Valid - Dog IS-A Animal
```

#### **HAS-A Relationship (Composition):**
```java
// Car HAS-A Engine
class Engine {
    private String type;
    
    public Engine(String type) {
        this.type = type;
    }
    
    public void start() {
        System.out.println(type + " engine started");
    }
}

class Car {
    private Engine engine;  // Car HAS-A Engine
    private String model;
    
    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }
    
    public void startCar() {
        engine.start();  // Delegating to Engine
    }
}

// Usage
Engine v8Engine = new Engine("V8");
Car car = new Car("Sports Car", v8Engine);
```

#### **When to Use Each:**

**Use IS-A (Inheritance) when:**
- **True specialization** exists
- **Shared behavior** is needed
- **Polymorphism** is required
- **Liskov Substitution Principle** applies

**Use HAS-A (Composition) when:**
- **Functionality reuse** is needed
- **Flexible relationships** are required
- **Runtime behavior change** is needed
- **Multiple "parts"** are involved

#### **Real-world Examples:**

**IS-A Examples:**
```java
class Vehicle { }
class Car extends Vehicle { }     // Car IS-A Vehicle
class Truck extends Vehicle { }   // Truck IS-A Vehicle

class Shape { }
class Circle extends Shape { }    // Circle IS-A Shape
class Rectangle extends Shape { } // Rectangle IS-A Shape
```

**HAS-A Examples:**
```java
class University {
    private List<Student> students;  // University HAS-A Students
    private List<Professor> faculty; // University HAS-A Professors
}

class Computer {
    private CPU processor;           // Computer HAS-A CPU
    private Memory ram;             // Computer HAS-A Memory
    private Storage hardDrive;      // Computer HAS-A Storage
}
```

#### **Benefits Comparison:**

**IS-A Benefits:**
- **Code reuse** through inheritance
- **Polymorphism** support
- **Natural hierarchy** modeling

**HAS-A Benefits:**
- **Flexibility** - can change components
- **Loose coupling** - easier testing
- **Multiple inheritance** simulation
- **Better encapsulation**
