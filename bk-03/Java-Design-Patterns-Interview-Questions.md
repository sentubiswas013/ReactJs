# 16. Java Design Patterns 

## 1. What are design patterns?

Design patterns are reusable solutions to commonly occurring problems in software design. They represent best practices and proven solutions that developers can apply to solve design problems.

- Reusable solutions to common design problems
- Best practices in object-oriented design
- Improve code maintainability and readability
- Facilitate communication between developers
- Three categories: Creational, Structural, Behavioral

Design patterns provide a common vocabulary and proven approaches to solving recurring design challenges in software development.

## 2. What is Singleton pattern?

Singleton pattern ensures that a class has only one instance throughout the application lifecycle and provides global access to that instance.

- Only one instance of the class
- Global access point
- Lazy or eager initialization
- Used for logging, database connections, caching

```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() { } // Private constructor
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

## 3. How do you implement thread-safe Singleton?

Thread-safe Singleton can be implemented using synchronization, double-checked locking, or enum approach to prevent multiple instances in multithreaded environments.

**Methods:**
- Synchronized method (simple but slow)
- Double-checked locking (efficient)
- Enum singleton (best approach)
- Static inner class (lazy loading)

```java
// Double-checked locking
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() { }
    
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

// Enum singleton - best approach
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() { }
}
```

## 4. What is Factory pattern?

Factory pattern creates objects without specifying their exact classes. It provides an interface for creating objects but lets subclasses decide which class to instantiate.

- Creates objects without exposing creation logic
- Refers to newly created objects through common interface
- Promotes loose coupling
- Easy to extend with new types

```java
// Product interface
interface Animal {
    void makeSound();
}

// Concrete products
class Dog implements Animal {
    public void makeSound() { System.out.println("Woof"); }
}

class Cat implements Animal {
    public void makeSound() { System.out.println("Meow"); }
}

// Factory
class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type) {
            case "dog": return new Dog();
            case "cat": return new Cat();
            default: throw new IllegalArgumentException("Unknown animal");
        }
    }
}
```

## 5. What is Observer pattern?

Observer pattern defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

- Subject maintains list of observers
- Observers are notified of state changes
- Loose coupling between subject and observers
- Used in event handling, MVC architecture

```java
// Observer interface
interface Observer {
    void update(String message);
}

// Subject
class NewsAgency {
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

// Concrete observer
class NewsChannel implements Observer {
    public void update(String news) {
        System.out.println("Breaking news: " + news);
    }
}
```

## 6. What is Strategy pattern?

Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

- Encapsulates algorithms in separate classes
- Makes algorithms interchangeable at runtime
- Eliminates conditional statements
- Follows Open/Closed principle

```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

## 7. What is Adapter pattern?

Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.

- Converts interface of a class into another interface
- Allows incompatible classes to work together
- Wraps existing functionality
- Used for integrating third-party libraries

```java
// Target interface (what client expects)
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee (existing incompatible interface)
class AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
    
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        advancedPlayer = new AdvancedMediaPlayer();
    }
    
    public void play(String audioType, String fileName) {
        if (audioType.equals("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equals("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}
```

## 8. What is Decorator pattern?

Decorator pattern allows behavior to be added to objects dynamically without altering their structure. It provides a flexible alternative to subclassing for extending functionality.

- Adds new functionality to objects dynamically
- Alternative to inheritance for extending behavior
- Maintains same interface as original object
- Can stack multiple decorators

```java
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple coffee"; }
    public double getCost() { return 2.0; }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    
    public String getDescription() {
        return coffee.getDescription() + ", milk";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    
    public String getDescription() {
        return coffee.getDescription() + ", sugar";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}

// Usage
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
// Result: "Simple coffee, milk, sugar" - $2.7
```