import java.util.ArrayList;
import java.util.List;

// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking)
// Singleton Pattern Creational Design Patterns that ensures a class has only one object (instance) and provides a global access point to that instance.

// Rules to create Singleton Pattern:
// 1. Make Constructor private
// 2. Create Static Instance Variable
// 3. Provide Public Static Method getInstance()
// 4. null check -> synchronized
// 5. Return Same Object Every Time

// When to use Singleton Pattern in real life:
// 1. When you want to control access to a shared resource (like a database connection).
// 2. When you want to ensure that only one instance of a class is created and used throughout the application (like a configuration manager).
// 3. When you want to implement a global point of access to a resource (like a logging service).   
// ============================================================

class Singleton {
    private static volatile Singleton instance;
    // volatile ensures visibility of changes across threads and prevents multiple threads from creating separate objects
    // during Singleton initialization.

    // volatile makes Singleton thread-safe
    // Singleton is the datatype/class type of the variable.
    // 'instance' stores the single object of Singleton class.

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
// Factory Design Pattern is creational design pattern that provides an interface for creating objects and lets the factory decide which object to create.

// Rules to create Factory Pattern exmaple Payment:: 
// step 0: Define an enum for the Payment types 
// Step 1: Interface or Abstract Class :: payment interface with a method pay()
// Step 2: Concrete Implementations :: CardPayment, UpiPayment classes that implement the Payment interface
// Step 3: Factory Class with a static method to create objects based on input :: PaymentFactory with a static method getPayment(String type)


// 👉 Real use:
// - Payment systems
// - Notification services
// - Logger creation
// - Database drivers
// - Spring BeanFactory
// ============================================================
enum PaymentType {
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
    public static Payment getPayment(PaymentType type) {
        switch (type) {
            case CARD:
                return new CardPayment();

            case UPI:
                return new UpiPayment();

            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}

// Step 4: Main Class
class FactoryPatternDemo {
    public static void main(String[] args) {
        Payment payment = PaymentFactory.getPayment(PaymentType.UPI);
        payment.pay();
    }
}


// Output:
// UPI payment


// ============================================================
// 3. Observer Pattern (Best Practice using Enum)
// Observer pattern is Behavioral Design Patterns defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

// Rules to create Observer Pattern (exmaple News Agency):
// News agency - > News Channel -> Observer Pattern
// 1. Create an Observer interface with an update() method.
// 2. Create a Subject class that maintains a list of observers and has methods to attach/detach observers and notify them of changes.
// 3. Create concrete Observer classes that implement the Observer interface and define the update() method to react to changes in the Subject.

// Flow:
// News Changed
// ↓
// Publisher Notifies Everyone
// ↓
// Subscribers Receive Update

// When to use Observer Pattern:

// 👉 Real use:
// - YouTube Notifications
// - News Channel System, 
// - Stock Market Apps
// - Kafka / RabbitMQ Consumers


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
// 4. Builder Pattern (Immutable Object - BEST PRACTICE)
// Builder Pattern(Creational Design Patterns) is  is used to create complex objects step by step, especially when an object has many optional parameters.

// Rules to create Builder Pattern:
// 1. Create a static nested Builder class inside the main class.
// 2. The Builder class should have the same fields as the main class.
// 3. The Builder class should have setter-like methods that return the Builder instance (for chaining).
// 4. Create a build() method in the Builder class that returns the final object.

// When to use Builder Pattern:
// 1. When you have a class with many parameters (especially optional ones) and want to avoid constructor overloading.
// 2. When you want to create immutable objects with many parameters.

// Use case: Employee class with id, name, age, department, and salary.

// Real use: API Request Objects, Complex DTO / Response Objects, Lombok @Builder, etc.
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
// 5. Prototype Pattern (Cloning)
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