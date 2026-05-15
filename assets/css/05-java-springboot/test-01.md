import java.util.ArrayList;
import java.util.List;

// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking) Ex: Database Connection Manager
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

class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    // volatile ensures visibility of changes across threads and prevents multiple threads from creating separate objects
    // during DatabaseConnection initialization.

    // volatile makes DatabaseConnection thread-safe
    // DatabaseConnection is the datatype/class type of the variable.
    // 'instance' stores the single object of DatabaseConnection class.

    private DatabaseConnection() {
        System.out.println("Database connection established: " + this.hashCode());
    }

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
    
    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }
}

class DatabaseDemo {
    public static void main(String[] args) {

        // Create multiple threads to test thread safety
        Runnable task = () -> {
            DatabaseConnection connection = DatabaseConnection.getInstance();
            connection.executeQuery("SELECT * FROM users");
            System.out.println(Thread.currentThread().getName() +
                    " got connection: " + connection.hashCode());
        };

        // Creating multiple threads
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}

// Output: 
// Database connection established: 1530262698
// Executing query: SELECT * FROM users
// Thread-2 got connection: 1530262698
// Executing query: SELECT * FROM users
// Thread-1 got connection: 1530262698


// ============================================================
// 2. Factory Pattern (Best Practice using Enum) : Ex: Vehicle Manufacturing System
// **Factory Pattern** is a creational design pattern used to: Create objects without exposing object creation logic to the client. Instead of creating objects directly using **new**, the client asks the factory to create the required object.

// Flow:: Client  -----> Factory (Creates CAR / BIKE object) ----> Required Object ("Give me CAR vehicle object")

// Rules to create :: Vehicle:: 
// step 0: Define an enum for the Vehicle types 
// Step 1: Interface or Abstract Class :: vehicle interface with a method start()
// Step 2: Concrete Implementations :: Car, Bike classes that implement the Vehicle interface
// Step 3: Factory Class with a static method to create objects based on input :: VehicleFactory with a static method getVehicle(String type)

// Advantages   : -----------
// Loose coupling
// Hides object creation logic
// Easy maintenance
// Easy to extend

// 👉 Real use : -----------
// - Vehicle manufacturing
// - Document creation services
// - UI component creation
// - Game character creation
// - Spring BeanFactory
// ============================================================
enum VehicleType {
    CAR, BIKE
}

// Step 1: Interface
interface Vehicle {
    void start();
}

// Step 2: Implementations
class Car implements Vehicle {
    public void start() {
        System.out.println("Car engine started with ignition key");
    }
}

class Bike implements Vehicle {
    public void start() {
        System.out.println("Bike engine started with kick start");
    }
}

// Step 3: Factory Class
class VehicleFactory {
    public static Vehicle getVehicle(VehicleType type) {
        switch (type) {
            case CAR:
                return new Car();

            case BIKE:
                return new Bike();

            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}

// Step 4: Main Class
class VehicleFactoryDemo {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle(VehicleType.CAR);
        vehicle.start();
    }
}


// Output:
// Car engine started with ignition key


// ============================================================
// 3. Observer Pattern (Best Practice using Enum) : Ex: Stock Market System
// **Observer pattern** (Behavioral Design Patterns) is defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

// Rules to create :: (example Stock Market):
// Stock Market - > Investors -> Observer Pattern

// 1. Create an Observer interface with an update() method.
// 2. Create a Subject class that maintains a list of observers and has methods to attach/detach observers and notify them of changes.
// 3. Create concrete Observer classes that implement the Observer interface and define the update() method to react to changes in the Subject.

// Flow:
// Stock Price Changed  ---> Market Notifies Everyone ---> Investors Receive Update  ---> When to use Observer Pattern:

// 👉 Real use:
// - Stock Market Apps
// - Social Media Notifications
// - Email Subscription Systems
// - Event Management Systems
// - Kafka / RabbitMQ Consumers


// ============================================================
// import java.util.*;

// Observer interface
// Step 1: Observer Interface
interface StockObserver {
    void update(String stockSymbol, double price);
}

// Step 2: Concrete Observer
class Investor implements StockObserver {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    public void update(String stockSymbol, double price) {
        System.out.println(name + " notified: " + stockSymbol + " price is now $" + price);
    }
}

// Step 3: Subject
class StockMarket {
    private List<StockObserver> investors = new ArrayList<>();
    private String stockSymbol;
    private double price;

    public void addInvestor(StockObserver investor) {
        investors.add(investor);
    }

    public void setStockPrice(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        investors.forEach(investor -> investor.update(stockSymbol, price));
    }
}

// Step 4: Main Class
class StockMarketDemo {
    public static void main(String[] args) {

        StockMarket market = new StockMarket();

        // Create observers
        StockObserver investor1 = new Investor("Alice");
        StockObserver investor2 = new Investor("Bob");

        // Register observers
        market.addInvestor(investor1);
        market.addInvestor(investor2);

        // Update stock prices
        market.setStockPrice("AAPL", 150.25);
        market.setStockPrice("GOOGL", 2800.50);
    }
}

// Output
// Alice notified: AAPL price is now $150.25
// Bob notified: AAPL price is now $150.25
// Alice notified: GOOGL price is now $2800.5
// Bob notified: GOOGL price is now $2800.5



// ============================================================
// 4. Strategy pattern:  Ex: Sorting System (Open/Closed Principle close implementsation, open for extension)
// Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.
// ============================================================
// Strategy interface
interface SortingStrategy {
    void sort(int[] array);
}

// Concrete strategies
class BubbleSort implements SortingStrategy {
    public void sort(int[] array) {
        System.out.println("Sorting using Bubble Sort algorithm");
        // Bubble sort implementation would go here
        System.out.println("Array sorted with Bubble Sort");
    }
}

class QuickSort implements SortingStrategy {
    public void sort(int[] array) {
        System.out.println("Sorting using Quick Sort algorithm");
        // Quick sort implementation would go here
        System.out.println("Array sorted with Quick Sort");
    }
}

// Context
class SortingContext {
    private SortingStrategy sortingStrategy;
    
    public void setSortingStrategy(SortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }
    
    public void performSort(int[] array) {
        sortingStrategy.sort(array);
    }
}

// Main class
class SortingStrategyDemo {
    public static void main(String[] args) {
        SortingContext context = new SortingContext();
        int[] data = {64, 34, 25, 12, 22, 11, 90};

        // Sort using Bubble Sort
        context.setSortingStrategy(new BubbleSort());
        context.performSort(data);

        // Sort using Quick Sort
        context.setSortingStrategy(new QuickSort());
        context.performSort(data);
    }
}

// Output:
// Sorting using Bubble Sort algorithm
// Array sorted with Bubble Sort
// Sorting using Quick Sort algorithm
// Array sorted with Quick Sort


// ============================================================
// 5. Adapter pattern: Ex: Temperature Converter
// Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.
// ============================================================
// Target interface (what client expects)
interface TemperatureReader {
    double getTemperature(String unit);
}

// Adaptee (existing incompatible interface)
class LegacyThermometer {
    public double readFahrenheit() {
        return 98.6; // Body temperature in Fahrenheit
    }

    public double readKelvin() {
        return 310.15; // Body temperature in Kelvin
    }
}

// Adapter
class TemperatureAdapter implements TemperatureReader {
    private LegacyThermometer thermometer;

    public TemperatureAdapter() {
        thermometer = new LegacyThermometer();
    }

    public double getTemperature(String unit) {
        if (unit.equalsIgnoreCase("fahrenheit")) {
            return thermometer.readFahrenheit();

        } else if (unit.equalsIgnoreCase("celsius")) {
            // Convert Fahrenheit to Celsius
            double fahrenheit = thermometer.readFahrenheit();
            return (fahrenheit - 32) * 5.0/9.0;

        } else if (unit.equalsIgnoreCase("kelvin")) {
            return thermometer.readKelvin();

        } else {
            System.out.println("Invalid temperature unit");
            return 0;
        }
    }
}

// Main class
class TemperatureAdapterDemo {
    public static void main(String[] args) {
        TemperatureReader reader = new TemperatureAdapter();
        
        System.out.println("Temperature in Celsius: " + reader.getTemperature("celsius"));
        System.out.println("Temperature in Fahrenheit: " + reader.getTemperature("fahrenheit"));
        System.out.println("Temperature in Kelvin: " + reader.getTemperature("kelvin"));
    }
}


// Output::
// Temperature in Celsius: 37.0
// Temperature in Fahrenheit: 98.6
// Temperature in Kelvin: 310.15

// ============================================================
// 6. Builder Pattern (Immutable Object - BEST PRACTICE) : Ex: Computer Object Creation
// Builder Pattern(Creational Design Patterns) is  is used to create complex objects step by step, especially when an object has many optional parameters.

// Rules to create Builder Pattern:
// 1. Create a static nested Builder class inside the main class.
// 2. The Builder class should have the same fields as the main class.
// 3. The Builder class should have setter-like methods that return the Builder instance (for chaining).
// 4. Create a build() method in the Builder class that returns the final object.

// When to use Builder Pattern:
// 1. When you have a class with many parameters (especially optional ones) and want to avoid constructor overloading.
// 2. When you want to create immutable objects with many parameters.

// Use case: Computer class with processor, ram, storage, graphics card, and operating system.

// Real use: Configuration Objects, HTTP Request Builders, SQL Query Builders, Lombok @Builder, etc.
// ============================================================
// ❌ Problem Without Builder
// Computer c = new Computer("Intel i7", "16GB", "1TB SSD", "RTX 3080", "Windows 11");

// 👉 Hard to read
// 👉 Constructor becomes huge

class Computer {

    private String processor;
    private String ram;
    private String storage;
    private String graphicsCard;

    // private constructor
    private Computer(ComputerBuilder builder) {
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }

    public String getSpecs() {
        return "Computer{processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                '}';
    }

    // Builder class
    static class ComputerBuilder {

        private String processor;
        private String ram;
        private String storage;
        private String graphicsCard;

        public ComputerBuilder setProcessor(String processor) {
            this.processor = processor;
            return this;
        }

        public ComputerBuilder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public ComputerBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// Main Class
class ComputerBuilderDemo {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder()
                .setProcessor("AMD Ryzen 9")
                .setRam("32GB DDR4")
                .setStorage("2TB NVMe SSD")
                .setGraphicsCard("RTX 4090")
                .build();

        System.out.println(computer.getSpecs());
    }
}


// Output
// Computer{processor='AMD Ryzen 9', ram='32GB DDR4', storage='2TB NVMe SSD', graphicsCard='RTX 4090'}


// ============================================================
// 7. Prototype Pattern (Cloning) :Ex Book Object Creation
// Prototype Pattern is a Creational Design Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.

// Rules to create Prototype Pattern:
// 1. Implement the Cloneable interface in the class you want to clone.
// 2. Override the clone() method to return a copy of the object.
// 3. Use the clone() method to create new objects by copying existing ones.    

// When to use Prototype Pattern:
// 1. When object creation is expensive and you want to create new objects by copying existing ones.
// 2. When you want to hide the creation logic from the client code.
// ============================================================
class PrototypeBookDemo {
    public static void main(String[] args) throws Exception {
        Book book1 = new Book("Design Patterns", "Gang of Four", 395);
        Book book2 = (Book) book1.clone();
        
        // Modify the cloned book
        book2
