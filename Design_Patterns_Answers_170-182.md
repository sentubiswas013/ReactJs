# ✅ 14) Design Patterns (Commonly Used)

## 170. Explain Singleton pattern and thread-safe implementation.

**Definition:** Singleton ensures only one instance of a class exists throughout the application lifecycle.

**Example:**
```java
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    
    private DatabaseConnection() {}
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}
```

---

## 171. What is double-checked locking problem?

**Definition:** Double-checked locking is a pattern to reduce synchronization overhead by checking the instance twice - once without locking and once with locking.

**Example:**
```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {  // First check (no lock)
            synchronized (Singleton.class) {
                if (instance == null) {  // Second check (with lock)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

---

## 172. Explain Factory pattern with example.

**Definition:** Factory pattern creates objects without exposing creation logic, returning objects through a common interface.

**Example:**
```java
interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    public void drive() { System.out.println("Driving car"); }
}

class Bike implements Vehicle {
    public void drive() { System.out.println("Riding bike"); }
}

class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equals("car")) return new Car();
        if (type.equals("bike")) return new Bike();
        return null;
    }
}
```

---

## 173. Explain Builder pattern and its use cases.

**Definition:** Builder pattern constructs complex objects step by step, separating construction from representation.

**Example:**
```java
public class User {
    private String name;
    private int age;
    private String email;
    
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }
    
    public static class Builder {
        private String name;
        private int age;
        private String email;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
}

// Usage: User user = new User.Builder().name("John").age(30).build();
```

---

## 174. Explain Strategy pattern with example.

**Definition:** Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

**Example:**
```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCard implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPal implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

---

## 175. Explain Observer pattern.

**Definition:** Observer pattern defines one-to-many dependency where when one object changes state, all dependents are notified automatically.

**Example:**
```java
interface Observer {
    void update(String message);
}

class User implements Observer {
    private String name;
    
    public User(String name) { this.name = name; }
    
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}
```

---

## 176. Explain Decorator pattern.

**Definition:** Decorator pattern adds new functionality to an object dynamically without altering its structure.

**Example:**
```java
interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double getCost() { return 2.0; }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

// Usage: Coffee coffee = new MilkDecorator(new SimpleCoffee());
```

---

## 177. Explain Adapter pattern.

**Definition:** Adapter pattern converts the interface of a class into another interface that clients expect, allowing incompatible interfaces to work together.

**Example:**
```java
interface MediaPlayer {
    void play(String filename);
}

class MP3Player {
    public void playMP3(String filename) {
        System.out.println("Playing MP3: " + filename);
    }
}

class MediaAdapter implements MediaPlayer {
    private MP3Player mp3Player;
    
    public MediaAdapter() {
        this.mp3Player = new MP3Player();
    }
    
    public void play(String filename) {
        mp3Player.playMP3(filename);
    }
}
```

---

## 178. Explain Facade pattern.

**Definition:** Facade pattern provides a simplified interface to a complex subsystem, hiding its complexity.

**Example:**
```java
class CPU {
    public void start() { System.out.println("CPU started"); }
}

class Memory {
    public void load() { System.out.println("Memory loaded"); }
}

class HardDrive {
    public void read() { System.out.println("HardDrive reading"); }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void startComputer() {
        cpu.start();
        memory.load();
        hardDrive.read();
    }
}
```

---

## 179. Explain Template Method pattern.

**Definition:** Template Method pattern defines the skeleton of an algorithm in a base class, letting subclasses override specific steps without changing the algorithm's structure.

**Example:**
```java
abstract class DataProcessor {
    public final void process() {
        readData();
        processData();
        saveData();
    }
    
    abstract void readData();
    abstract void processData();
    
    void saveData() {
        System.out.println("Saving data");
    }
}

class CSVProcessor extends DataProcessor {
    void readData() { System.out.println("Reading CSV"); }
    void processData() { System.out.println("Processing CSV"); }
}

class XMLProcessor extends DataProcessor {
    void readData() { System.out.println("Reading XML"); }
    void processData() { System.out.println("Processing XML"); }
}
```

---

## 180. Explain Proxy pattern.

**Definition:** Proxy pattern provides a surrogate or placeholder to control access to another object.

**Example:**
```java
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }
    
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
```

---

## 181. Strategy vs State pattern.

**Definition:** Strategy pattern selects algorithm at runtime based on client choice. State pattern changes object behavior when its internal state changes.

**Example:**
```java
// Strategy - Client chooses behavior
interface SortStrategy {
    void sort(int[] array);
}

class QuickSort implements SortStrategy {
    public void sort(int[] array) { /* quick sort */ }
}

// State - Object changes behavior based on state
interface State {
    void handle();
}

class OnState implements State {
    public void handle() { System.out.println("Turning OFF"); }
}

class OffState implements State {
    public void handle() { System.out.println("Turning ON"); }
}

class Switch {
    private State state;
    
    public void setState(State state) { this.state = state; }
    public void press() { state.handle(); }
}
```

---

## 182. When do you use Dependency Injection pattern?

**Definition:** Dependency Injection provides dependencies to a class from external sources rather than creating them internally, promoting loose coupling and testability.

**Example:**
```java
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class UserService {
    private Database database;
    
    // Constructor Injection
    public UserService(Database database) {
        this.database = database;
    }
    
    public void saveUser(String user) {
        database.save(user);
    }
}

// Usage: UserService service = new UserService(new MySQLDatabase());
```

