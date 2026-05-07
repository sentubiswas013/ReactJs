import java.util.ArrayList;
import java.util.List;

// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking)
// Singleton Pattern is a design pattern that ensures a class has only one object (instance) and provides a global access point to that instance.

// Rules to create Singleton Pattern:
// Make class final to prevent subclassing
// Private constructor to prevent instantiation from outside the class
// Private static variable to hold the single instance of the class
// Public static method that returns the instance of the class, creating it if it doesn't exist yet


// When to use Singleton Pattern in real life:
// 1. When you want to control access to a shared resource (like a database connection).
// 2. When you want to ensure that only one instance of a class is created and used throughout the application (like a configuration manager).
// 3. When you want to implement a global point of access to a resource (like a logging service).   
// ============================================================

class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        System.out.println("Singleton instance created: " + this.hashCode());
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

class SingletonDemo {
    public static void main(String[] args) {

        // Create multiple threads to test thread safety
        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() +
                    " got instance: " + instance.hashCode());
        };

        // Creating multiple threads
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}

// Output: 
// Singleton instance created: 1530262698
// Thread-2 got instance: 1530262698
// Thread-1 got instance: 1530262698


// ============================================================
// 2. Factory Pattern (Best Practice using Enum)
// Factory Design Pattern is used to create objects without using new keyword directly, by using a factory method.

// Rules to create Factory Pattern:
// 1. Create an interface or abstract class for the type of object you want to create.
// 2. Create concrete classes that implement the interface or extend the abstract class.
// 3. Create a Factory class with a static method that takes input (like an enum


// When to use Factory Pattern:
// 1. When you have a super class with multiple sub-classes and based on input, you need to return one of the sub-class.
// 2. When you want to decouple the client code from the actual implementation of the objects it needs to create.

// ============================================================
enum ShapeType {
    CARD, UPI
}

// Step 1: Interface
interface Payment {
    void pay();
}

// Step 2: Implementations
class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI payment");
    }
}

// Step 3: Factory Class
class PaymentFactory {

    public static Payment getPayment(String type) {

        switch (type.toUpperCase()) {

            case "CARD":
                return new CardPayment();

            case "UPI":
                return new UpiPayment();

            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}

// Step 4: Main Class
class FactoryPatternDemo {
    public static void main(String[] args) {

        Payment payment = PaymentFactory.getPayment("UPI");
        payment.pay();
    }
}


// Output:
// UPI payment


// ============================================================
// 2. Observer Pattern (Best Practice using Enum)
// Observer pattern defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

// Rules to create Observer Pattern:
// 1. Create an Observer interface with an update() method.
// 2. Create a Subject class that maintains a list of observers and has methods to attach/detach observers and notify them of changes.
// 3. Create concrete Observer classes that implement the Observer interface and define the update() method to react to changes in the Subject.

// When to use Observer Pattern:
//  Real use: Email service, Logging, Notifications

// ============================================================
// import java.util.*;

// Observer interface
// Step 1: Observer Interface
interface Observer {
    void update(String message);
}

// Step 2: Concrete Observer
class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    public void update(String news) {
        System.out.println(name + " received: " + news);
    }
}

// Step 3: Subject
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

// Step 4: Main Class
class ObserverPatternExp {
    public static void main(String[] args) {

        NewsAgency agency = new NewsAgency();

        // Create observers
        Observer channel1 = new NewsChannel("CNN");
        Observer channel2 = new NewsChannel("BBC");

        // Register observers
        agency.addObserver(channel1);
        agency.addObserver(channel2);

        // Publish news
        agency.setNews("Java is awesome!");
        agency.setNews("Observer pattern in action!");
    }
}

// Output
// CNN received: Java is awesome!
// BBC received: Java is awesome!
// CNN received: Observer pattern in action!
// BBC received: Observer pattern in action!


// ============================================================
// 3. Builder Pattern (Immutable Object - BEST PRACTICE)
// Builder Pattern is used to create complex objects step by step, especially when an object has many optional parameters.

// Rules to create Builder Pattern:
// 1. Create a static nested Builder class inside the main class.
// 2. The Builder class should have the same fields as the main class.
// 3. The Builder class should have setter-like methods that return the Builder instance (for chaining).
// 4. Create a build() method in the Builder class that returns the final object.

// When to use Builder Pattern:
// 1. When you have a class with many parameters (especially optional ones) and want to avoid constructor overloading.
// 2. When you want to create immutable objects with many parameters.

// Real use: StringBuilder, Lombok @Builder, etc.
// ============================================================
// ❌ Problem Without Builder
// Employee e = new Employee(1, "Rahul", 25, "Bangalore", "Developer");

// 👉 Hard to read
// 👉 Constructor becomes huge

class Employee {

    private int id;
    private String name;

    // private constructor
    private Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String display() {
        return "Employee{id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // Builder class
    static class EmployeeBuilder {

        private int id;
        private String name;

        public EmployeeBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}

// Main Class
class BuilderPatternDemo {
    public static void main(String[] args) {
        Employee emp = new Employee.EmployeeBuilder()
                .setId(1)
                .setName("Rahul")
                .build();

        System.out.println(emp.display());
    }
}


// Output
// Employee{id=1, name='Rahul'}

// ============================================================
// 4. Prototype Pattern (Cloning)
// Prototype Pattern is a Creational Design Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.

// Rules to create Prototype Pattern:
// 1. Implement the Cloneable interface in the class you want to clone.
// 2. Override the clone() method to return a copy of the object.
// 3. Use the clone() method to create new objects by copying existing ones.    

// When to use Prototype Pattern:
// 1. When object creation is expensive and you want to create new objects by copying existing ones.
// 2. When you want to hide the creation logic from the client code.
// ============================================================
class PrototypeDemo {
    public static void main(String[] args) throws Exception {
        Student s1 = new Student(1, "John");
        Student s2 = (Student) s1.clone();

        System.out.println(s1.name);
        System.out.println(s2.name);
    }
}

class Student implements Cloneable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

// Output:
// John
// John