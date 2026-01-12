# 🔵 16. Design Patterns
---
# 🔹 Common Design Patterns
### Question 252: What are design patterns?

**Answer (30 seconds):**
* Reusable solutions to common software design problems
* Best practices proven over time by experienced developers
* Provide template for writing maintainable, flexible code
* Three categories: Creational, Structural, and Behavioral patterns
* Help communicate design intent clearly among developers

```java
// Example: Factory pattern creates objects without specifying exact class
Animal animal = AnimalFactory.createAnimal("dog");
// Instead of: Animal animal = new Dog();
```

---

### Question 253: What is Singleton design pattern?

**Answer (25 seconds):**
* Ensures only one instance of a class exists in application
* Provides global access point to that instance
* Useful for database connections, logging, configuration settings
* Controls object creation and prevents multiple instances

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```

---

### Question 254: How do you implement Singleton pattern in Java?

**Answer (35 seconds):**
* **Lazy Initialization**: Create instance when first needed
* **Thread-Safe**: Use synchronized or double-checked locking
* **Eager Initialization**: Create instance at class loading
* **Enum Singleton**: Best approach, handles serialization automatically
* Private constructor prevents external instantiation

```java
// Thread-safe enum singleton (recommended)
public enum DatabaseConnection {
    INSTANCE;
    public void connect() { /* connection logic */ }
}

// Usage: DatabaseConnection.INSTANCE.connect();
```

---

### Question 255: What is Factory design pattern?

**Answer (30 seconds):**
* Creates objects without specifying their exact classes
* Encapsulates object creation logic in separate method/class
* Client code doesn't need to know concrete class names
* Easy to add new types without changing existing code
* Promotes loose coupling between classes

```java
public class ShapeFactory {
    public static Shape createShape(String type) {
        switch(type) {
            case "circle": return new Circle();
            case "square": return new Square();
            default: throw new IllegalArgumentException();
        }
    }
}
```

---

### Question 256: What is Abstract Factory design pattern?

**Answer (35 seconds):**
* Factory of factories - creates families of related objects
* Provides interface for creating groups of related products
* Useful when system needs to work with multiple product families
* Ensures products from same family are used together
* More complex than simple Factory pattern

```java
interface GUIFactory {
    Button createButton();
    TextField createTextField();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public TextField createTextField() { return new WindowsTextField(); }
}
```

---

### Question 257: What is Builder design pattern?

**Answer (30 seconds):**
* Constructs complex objects step by step
* Separates object construction from its representation
* Useful for objects with many optional parameters
* Provides fluent interface for object creation
* Avoids telescoping constructor problem

```java
public class User {
    private String name, email, phone;
    
    public static class Builder {
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public User build() { return new User(this); }
    }
}
// Usage: new User.Builder().setName("John").setEmail("john@email.com").build();
```

---

### Question 258: What is Observer design pattern?

**Answer (35 seconds):**
* Defines one-to-many dependency between objects
* When subject changes state, all observers are notified automatically
* Promotes loose coupling between subject and observers
* Commonly used in event handling systems, MVC architecture
* Java provides Observable class and Observer interface

```java
interface Observer {
    void update(String message);
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) { observers.add(observer); }
    public void notifyObservers(String news) {
        observers.forEach(observer -> observer.update(news));
    }
}
```

---

### Question 259: What is Strategy design pattern?

**Answer (30 seconds):**
* Defines family of algorithms and makes them interchangeable
* Algorithm varies independently from clients that use it
* Eliminates conditional statements for algorithm selection
* Follows Open/Closed principle - open for extension, closed for modification
* Runtime algorithm switching capability

```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) { /* credit card logic */ }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) { /* PayPal logic */ }
}
```

---

### Question 260: What is Command design pattern?

**Answer (35 seconds):**
* Encapsulates request as an object with all necessary information
* Allows parameterization of clients with different requests
* Supports undo operations and logging of requests
* Decouples sender and receiver of requests
* Useful for implementing macro commands, queuing operations

```java
interface Command {
    void execute();
    void undo();
}

class LightOnCommand implements Command {
    private Light light;
    public void execute() { light.turnOn(); }
    public void undo() { light.turnOff(); }
}

// Usage: RemoteControl remote = new RemoteControl();
// remote.setCommand(new LightOnCommand(light));
```

---

### Question 261: What is Decorator design pattern?

**Answer (35 seconds):**
* Adds new functionality to objects dynamically without altering structure
* Alternative to subclassing for extending functionality
* Wraps original object and provides additional behavior
* Multiple decorators can be stacked for combined effects
* Follows composition over inheritance principle

```java
interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    public double cost() { return 2.0; }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public double cost() { return coffee.cost() + 0.5; }
}
// Usage: Coffee coffee = new MilkDecorator(new SimpleCoffee());
```
