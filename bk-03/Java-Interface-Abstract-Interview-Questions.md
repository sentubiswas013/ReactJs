# 5. Java Interface & Abstract Class 

## 1. What is an interface in Java?

An interface is a contract that defines what methods a class must implement without providing the implementation. It's a blueprint for classes that ensures consistent behavior.

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