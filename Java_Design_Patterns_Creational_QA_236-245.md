# 🔹 Design Patterns

## Design Patterns Fundamentals

### 236. What are design patterns?

**Design patterns** are **reusable solutions** to commonly occurring problems in software design:

#### Key Characteristics:
- **Proven solutions**: Battle-tested approaches to common problems
- **Reusable templates**: Can be applied across different projects
- **Communication tool**: Common vocabulary for developers
- **Best practices**: Encapsulate design expertise and experience

#### Categories of Design Patterns:

##### 1. Creational Patterns:
- **Purpose**: Object creation mechanisms
- **Examples**: Singleton, Factory, Builder, Prototype, Abstract Factory

##### 2. Structural Patterns:
- **Purpose**: Object composition and relationships
- **Examples**: Adapter, Decorator, Facade, Proxy, Composite

##### 3. Behavioral Patterns:
- **Purpose**: Communication between objects and assignment of responsibilities
- **Examples**: Observer, Strategy, Command, Template Method, State

#### Benefits:
```java
// Without pattern - tightly coupled code
public class OrderProcessor {
    public void processOrder(Order order) {
        EmailService emailService = new EmailService(); // Tight coupling
        emailService.sendConfirmation(order);
    }
}

// With pattern - loose coupling using Factory
public class OrderProcessor {
    private NotificationServiceFactory factory;
    
    public void processOrder(Order order) {
        NotificationService service = factory.createService("email");
        service.sendConfirmation(order);
    }
}
```

#### When to Use Design Patterns:
- **Recurring problems**: When you encounter the same design challenges
- **Code flexibility**: When you need extensible and maintainable code
- **Team communication**: When working with multiple developers
- **Framework development**: When building reusable libraries

### 237. What is Singleton pattern?

**Singleton pattern** ensures a class has **only one instance** and provides **global access** to it:

#### Basic Implementation:
```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    // Private constructor prevents instantiation
    private DatabaseConnection() {
        // Initialize database connection
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void executeQuery(String sql) {
        // Database operations
    }
}
```

#### Usage:
```java
// Always returns the same instance
DatabaseConnection db1 = DatabaseConnection.getInstance();
DatabaseConnection db2 = DatabaseConnection.getInstance();

System.out.println(db1 == db2); // true - same instance
```

#### Real-world Examples:
```java
// Logger singleton
public class Logger {
    private static Logger instance;
    
    private Logger() {}
    
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Configuration singleton
public class AppConfig {
    private static AppConfig instance;
    private Properties properties;
    
    private AppConfig() {
        properties = new Properties();
        // Load configuration
    }
    
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
```

### 238. How do you implement thread-safe Singleton?

#### 1. Synchronized Method (Simple but Inefficient):
```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```

#### 2. Double-Checked Locking (Efficient):
```java
public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;
    
    private DoubleCheckedSingleton() {}
    
    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```

#### 3. Bill Pugh Solution (Lazy Initialization):
```java
public class BillPughSingleton {
    
    private BillPughSingleton() {}
    
    // Static inner class - loaded only when getInstance() is called
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

#### 4. Enum Singleton (Recommended):
```java
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Doing something...");
    }
}

// Usage
EnumSingleton.INSTANCE.doSomething();
```

#### 5. Eager Initialization:
```java
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    
    private EagerSingleton() {}
    
    public static EagerSingleton getInstance() {
        return instance;
    }
}
```

#### Comparison:
| Approach | Thread Safe | Lazy Loading | Performance | Complexity |
|----------|-------------|--------------|-------------|------------|
| **Synchronized Method** | ✅ | ✅ | Poor | Low |
| **Double-Checked Locking** | ✅ | ✅ | Good | Medium |
| **Bill Pugh** | ✅ | ✅ | Excellent | Low |
| **Enum** | ✅ | ❌ | Excellent | Very Low |
| **Eager Initialization** | ✅ | ❌ | Excellent | Very Low |

### 239. What is Factory pattern?

**Factory pattern** creates objects **without specifying their exact classes**:

#### Simple Factory:
```java
// Product interface
interface Vehicle {
    void start();
}

// Concrete products
class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }
}

class Motorcycle implements Vehicle {
    public void start() {
        System.out.println("Motorcycle started");
    }
}

// Factory class
class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "motorcycle":
                return new Motorcycle();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
```

#### Usage:
```java
public class FactoryExample {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        Vehicle motorcycle = VehicleFactory.createVehicle("motorcycle");
        
        car.start();        // Car started
        motorcycle.start(); // Motorcycle started
    }
}
```

#### Factory Method Pattern:
```java
// Abstract creator
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
    
    public void deliverVehicle() {
        Vehicle vehicle = createVehicle();
        vehicle.start();
        System.out.println("Vehicle delivered!");
    }
}

// Concrete creators
class CarFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Car();
    }
}

class MotorcycleFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Motorcycle();
    }
}
```

#### Real-world Example:
```java
// Database connection factory
interface DatabaseConnection {
    void connect();
}

class MySQLConnection implements DatabaseConnection {
    public void connect() {
        System.out.println("Connected to MySQL");
    }
}

class PostgreSQLConnection implements DatabaseConnection {
    public void connect() {
        System.out.println("Connected to PostgreSQL");
    }
}

class DatabaseFactory {
    public static DatabaseConnection createConnection(String dbType) {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MySQLConnection();
            case "postgresql":
                return new PostgreSQLConnection();
            default:
                throw new IllegalArgumentException("Unsupported database: " + dbType);
        }
    }
}
```

### 240. What is Abstract Factory pattern?

**Abstract Factory** provides an interface for creating **families of related objects**:

#### Implementation:
```java
// Abstract products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Windows family
class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering Windows button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Windows checkbox");
    }
}

// Mac family
class MacButton implements Button {
    public void render() {
        System.out.println("Rendering Mac button");
    }
}

class MacCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Mac checkbox");
    }
}

// Abstract factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete factories
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

#### Usage:
```java
public class Application {
    private Button button;
    private Checkbox checkbox;
    
    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }
    
    public void render() {
        button.render();
        checkbox.render();
    }
    
    public static void main(String[] args) {
        String osName = System.getProperty("os.name").toLowerCase();
        GUIFactory factory;
        
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
        } else if (osName.contains("mac")) {
            factory = new MacFactory();
        } else {
            throw new RuntimeException("Unsupported OS");
        }
        
        Application app = new Application(factory);
        app.render();
    }
}
```

#### Real-world Example - Database Abstraction:
```java
// Abstract factory for database components
interface DatabaseFactory {
    Connection createConnection();
    Query createQuery();
    Transaction createTransaction();
}

class MySQLFactory implements DatabaseFactory {
    public Connection createConnection() {
        return new MySQLConnection();
    }
    
    public Query createQuery() {
        return new MySQLQuery();
    }
    
    public Transaction createTransaction() {
        return new MySQLTransaction();
    }
}

class PostgreSQLFactory implements DatabaseFactory {
    public Connection createConnection() {
        return new PostgreSQLConnection();
    }
    
    public Query createQuery() {
        return new PostgreSQLQuery();
    }
    
    public Transaction createTransaction() {
        return new PostgreSQLTransaction();
    }
}
```

### 241. What is Builder pattern?

**Builder pattern** constructs complex objects **step by step** using a fluent interface:

#### Basic Implementation:
```java
public class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private boolean hasWiFi;
    private boolean hasBluetooth;
    
    // Private constructor
    private Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
    }
    
    // Static nested Builder class
    public static class ComputerBuilder {
        // Required parameters
        private String CPU;
        private String RAM;
        
        // Optional parameters
        private String storage = "256GB SSD";
        private String GPU = "Integrated";
        private boolean hasWiFi = false;
        private boolean hasBluetooth = false;
        
        public ComputerBuilder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public ComputerBuilder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        
        public ComputerBuilder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }
        
        public ComputerBuilder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
    
    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", storage='" + storage + '\'' +
                ", GPU='" + GPU + '\'' +
                ", hasWiFi=" + hasWiFi +
                ", hasBluetooth=" + hasBluetooth +
                '}';
    }
}
```

#### Usage:
```java
public class BuilderExample {
    public static void main(String[] args) {
        // Build a gaming computer
        Computer gamingPC = new Computer.ComputerBuilder("Intel i9", "32GB")
                .setStorage("1TB NVMe SSD")
                .setGPU("RTX 4080")
                .setWiFi(true)
                .setBluetooth(true)
                .build();
        
        // Build a basic office computer
        Computer officePC = new Computer.ComputerBuilder("Intel i5", "16GB")
                .setWiFi(true)
                .build();
        
        System.out.println(gamingPC);
        System.out.println(officePC);
    }
}
```

#### Real-world Example - HTTP Request Builder:
```java
public class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
    private int timeout;
    
    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.body = builder.body;
        this.timeout = builder.timeout;
    }
    
    public static class Builder {
        private String url;
        private String method = "GET";
        private Map<String, String> headers = new HashMap<>();
        private String body;
        private int timeout = 30000;
        
        public Builder(String url) {
            this.url = url;
        }
        
        public Builder method(String method) {
            this.method = method;
            return this;
        }
        
        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }
        
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        
        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }
        
        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}

// Usage
HttpRequest request = new HttpRequest.Builder("https://api.example.com/users")
        .method("POST")
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer token123")
        .body("{\"name\":\"John\",\"email\":\"john@example.com\"}")
        .timeout(5000)
        .build();
```

### 242. What is Prototype pattern?

**Prototype pattern** creates new objects by **cloning existing instances**:

#### Basic Implementation:
```java
// Prototype interface
interface Prototype {
    Prototype clone();
}

// Concrete prototype
public class Document implements Prototype {
    private String title;
    private String content;
    private List<String> images;
    
    public Document(String title, String content) {
        this.title = title;
        this.content = content;
        this.images = new ArrayList<>();
    }
    
    // Copy constructor
    private Document(Document other) {
        this.title = other.title;
        this.content = other.content;
        this.images = new ArrayList<>(other.images); // Deep copy
    }
    
    @Override
    public Document clone() {
        return new Document(this);
    }
    
    // Getters and setters
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void addImage(String image) { this.images.add(image); }
    
    @Override
    public String toString() {
        return "Document{title='" + title + "', content='" + content + 
               "', images=" + images + "}";
    }
}
```

#### Usage:
```java
public class PrototypeExample {
    public static void main(String[] args) {
        // Create original document
        Document original = new Document("Original Title", "Original Content");
        original.addImage("image1.jpg");
        original.addImage("image2.jpg");
        
        // Clone the document
        Document copy = original.clone();
        copy.setTitle("Copy Title");
        copy.addImage("image3.jpg");
        
        System.out.println("Original: " + original);
        System.out.println("Copy: " + copy);
    }
}
```

#### Using Java's Cloneable Interface:
```java
public class Employee implements Cloneable {
    private String name;
    private int age;
    private Address address;
    
    public Employee(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    @Override
    protected Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        // Deep copy for reference types
        cloned.address = this.address.clone();
        return cloned;
    }
    
    // Getters and setters...
}

class Address implements Cloneable {
    private String street;
    private String city;
    
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    
    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
    
    // Getters and setters...
}
```

#### Prototype Registry:
```java
public class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();
    
    public void registerPrototype(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }
    
    public Prototype getPrototype(String key) {
        Prototype prototype = prototypes.get(key);
        return prototype != null ? prototype.clone() : null;
    }
}

// Usage
PrototypeRegistry registry = new PrototypeRegistry();
registry.registerPrototype("basic-doc", new Document("Template", "Basic content"));

Document newDoc = (Document) registry.getPrototype("basic-doc");
newDoc.setTitle("My Document");
```

### 243. When would you use each creational pattern?

#### Pattern Selection Guide:

##### Singleton Pattern:
**Use When:**
- Need exactly one instance (database connection, logger, cache)
- Global access point required
- Resource sharing across application

```java
// Example: Application configuration
public class AppConfig {
    private static AppConfig instance;
    private Properties config;
    
    private AppConfig() {
        config = loadConfiguration();
    }
    
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
}
```

##### Factory Pattern:
**Use When:**
- Object creation logic is complex
- Need to decouple object creation from usage
- Runtime decision on which class to instantiate

```java
// Example: Payment processing
PaymentProcessor processor = PaymentFactory.createProcessor(paymentType);
processor.processPayment(amount);
```

##### Abstract Factory Pattern:
**Use When:**
- Need families of related objects
- Want to ensure objects work together
- Platform-specific implementations

```java
// Example: Cross-platform UI
GUIFactory factory = getFactoryForPlatform();
Button button = factory.createButton();
Menu menu = factory.createMenu();
```

##### Builder Pattern:
**Use When:**
- Objects have many optional parameters
- Construction process is complex
- Want immutable objects
- Telescoping constructor problem

```java
// Example: SQL query building
Query query = new QueryBuilder()
    .select("name", "email")
    .from("users")
    .where("age > 18")
    .orderBy("name")
    .build();
```

##### Prototype Pattern:
**Use When:**
- Object creation is expensive
- Need objects similar to existing ones
- Runtime object composition
- Avoiding subclasses of object creator

```java
// Example: Game objects
Enemy basicEnemy = enemyRegistry.getPrototype("basic");
Enemy bossEnemy = basicEnemy.clone();
bossEnemy.setHealth(1000);
bossEnemy.setDamage(50);
```

#### Decision Matrix:

| Scenario | Recommended Pattern | Reason |
|----------|-------------------|---------|
| **Database Connection** | Singleton | Single shared resource |
| **UI Components** | Abstract Factory | Platform-specific families |
| **HTTP Requests** | Builder | Many optional parameters |
| **Document Templates** | Prototype | Clone and customize |
| **Payment Methods** | Factory | Runtime type selection |

### 244. What are the pros and cons of Singleton pattern?

#### Pros:

##### 1. Controlled Access:
```java
public class DatabasePool {
    private static DatabasePool instance;
    private List<Connection> connections;
    
    private DatabasePool() {
        connections = new ArrayList<>();
        // Initialize connection pool
    }
    
    public static DatabasePool getInstance() {
        if (instance == null) {
            instance = new DatabasePool();
        }
        return instance;
    }
    
    public Connection getConnection() {
        // Controlled access to connections
        return connections.isEmpty() ? createConnection() : connections.remove(0);
    }
}
```

##### 2. Memory Efficiency:
```java
// Only one instance exists in memory
Logger logger1 = Logger.getInstance();
Logger logger2 = Logger.getInstance();
// logger1 == logger2 (same memory location)
```

##### 3. Global Access Point:
```java
// Accessible from anywhere in the application
public class OrderService {
    public void processOrder(Order order) {
        Logger.getInstance().log("Processing order: " + order.getId());
    }
}
```

##### 4. Lazy Initialization:
```java
public class ExpensiveResource {
    private static ExpensiveResource instance;
    
    public static ExpensiveResource getInstance() {
        if (instance == null) {
            // Created only when first accessed
            instance = new ExpensiveResource();
        }
        return instance;
    }
}
```

#### Cons:

##### 1. Violates Single Responsibility Principle:
```java
// Singleton manages both its creation AND business logic
public class Logger {
    private static Logger instance;
    
    // Creation responsibility
    public static Logger getInstance() { ... }
    
    // Business logic responsibility
    public void log(String message) { ... }
    public void setLogLevel(Level level) { ... }
}
```

##### 2. Difficult to Test:
```java
// Hard to mock or replace in tests
public class OrderService {
    public void processOrder(Order order) {
        // Tightly coupled to concrete Logger
        Logger.getInstance().log("Processing: " + order.getId());
    }
}

// Better approach - dependency injection
public class OrderService {
    private Logger logger;
    
    public OrderService(Logger logger) {
        this.logger = logger;
    }
    
    public void processOrder(Order order) {
        logger.log("Processing: " + order.getId());
    }
}
```

##### 3. Thread Safety Issues:
```java
// Not thread-safe
public static Singleton getInstance() {
    if (instance == null) {  // Race condition here
        instance = new Singleton();
    }
    return instance;
}
```

##### 4. Hidden Dependencies:
```java
// Dependencies not visible in constructor/method signatures
public class UserService {
    public User createUser(String name) {
        // Hidden dependency on Logger
        Logger.getInstance().log("Creating user: " + name);
        return new User(name);
    }
}
```

##### 5. Global State Problems:
```java
// Changes affect entire application
ConfigManager.getInstance().setDebugMode(true);
// Now ALL parts of application are in debug mode
```

#### Comparison Table:

| Aspect | Pros | Cons |
|--------|------|------|
| **Memory** | Single instance saves memory | Global state can cause issues |
| **Access** | Global access point | Hidden dependencies |
| **Control** | Controlled instantiation | Violates SRP |
| **Testing** | - | Difficult to mock/test |
| **Threading** | - | Synchronization complexity |

### 245. How do you break Singleton pattern?

#### Ways to Break Singleton:

##### 1. Reflection Attack:
```java
public class SingletonBreaker {
    public static void main(String[] args) throws Exception {
        // Get normal instance
        Singleton instance1 = Singleton.getInstance();
        
        // Break using reflection
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton instance2 = constructor.newInstance();
        
        System.out.println(instance1 == instance2); // false - different instances!
    }
}
```

**Prevention:**
```java
public class ReflectionProofSingleton {
    private static ReflectionProofSingleton instance;
    
    private ReflectionProofSingleton() {
        // Prevent reflection attack
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create instance");
        }
    }
    
    public static ReflectionProofSingleton getInstance() {
        if (instance == null) {
            instance = new ReflectionProofSingleton();
        }
        return instance;
    }
}
```

##### 2. Serialization Attack:
```java
public class SerializableSingleton implements Serializable {
    private static SerializableSingleton instance;
    
    private SerializableSingleton() {}
    
    public static SerializableSingleton getInstance() {
        if (instance == null) {
            instance = new SerializableSingleton();
        }
        return instance;
    }
}

// Breaking code
public class SerializationBreaker {
    public static void main(String[] args) throws Exception {
        SerializableSingleton instance1 = SerializableSingleton.getInstance();
        
        // Serialize
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        out.writeObject(instance1);
        out.close();
        
        // Deserialize - creates new instance!
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
        SerializableSingleton instance2 = (SerializableSingleton) in.readObject();
        in.close();
        
        System.out.println(instance1 == instance2); // false
    }
}
```

**Prevention:**
```java
public class SerializationProofSingleton implements Serializable {
    private static SerializationProofSingleton instance;
    
    private SerializationProofSingleton() {}
    
    public static SerializationProofSingleton getInstance() {
        if (instance == null) {
            instance = new SerializationProofSingleton();
        }
        return instance;
    }
    
    // Prevent creating new instance during deserialization
    protected Object readResolve() {
        return getInstance();
    }
}
```

##### 3. Cloning Attack:
```java
public class CloneableSingleton implements Cloneable {
    private static CloneableSingleton instance;
    
    private CloneableSingleton() {}
    
    public static CloneableSingleton getInstance() {
        if (instance == null) {
            instance = new CloneableSingleton();
        }
        return instance;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Creates new instance!
    }
}
```

**Prevention:**
```java
public class CloneProofSingleton implements Cloneable {
    private static CloneProofSingleton instance;
    
    private CloneProofSingleton() {}
    
    public static CloneProofSingleton getInstance() {
        if (instance == null) {
            instance = new CloneProofSingleton();
        }
        return instance;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed for singleton");
    }
}
```

##### 4. Multiple ClassLoaders:
```java
// Different class loaders can create different instances
ClassLoader loader1 = new URLClassLoader(urls);
ClassLoader loader2 = new URLClassLoader(urls);

Class<?> class1 = loader1.loadClass("Singleton");
Class<?> class2 = loader2.loadClass("Singleton");

// Different static variables, different instances
```

#### Ultimate Solution - Enum Singleton:
```java
public enum UltimateProofSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

**Why Enum is Best:**
- **Reflection-proof**: Cannot instantiate enum with reflection
- **Serialization-proof**: JVM handles serialization correctly
- **Thread-safe**: JVM guarantees thread safety
- **Clone-proof**: Enums cannot be cloned

#### Comparison of Solutions:

| Attack Vector | Vulnerable Patterns | Safe Patterns |
|---------------|-------------------|---------------|
| **Reflection** | Basic Singleton | Enum, Exception in constructor |
| **Serialization** | Basic Singleton | readResolve(), Enum |
| **Cloning** | Cloneable Singleton | Override clone(), Enum |
| **ClassLoaders** | All except Enum | Enum |

## Best Practices

### 1. Choose the Right Pattern:
```java
// Use Factory for flexibility
PaymentProcessor processor = PaymentFactory.create(type);

// Use Builder for complex objects
HttpRequest request = new HttpRequest.Builder(url)
    .method("POST")
    .header("Content-Type", "application/json")
    .build();
```

### 2. Prefer Composition over Inheritance:
```java
// Instead of extending classes, use composition
public class EnhancedLogger {
    private Logger logger;
    private Formatter formatter;
    
    public EnhancedLogger(Logger logger, Formatter formatter) {
        this.logger = logger;
        this.formatter = formatter;
    }
}
```

### 3. Use Dependency Injection:
```java
// Instead of Singleton, use DI
@Service
public class OrderService {
    private final Logger logger;
    
    public OrderService(Logger logger) {
        this.logger = logger;
    }
}
```

### 4. Consider Modern Alternatives:
```java
// Spring's @Scope("singleton") instead of Singleton pattern
@Component
@Scope("singleton")
public class ConfigService {
    // Spring manages the singleton lifecycle
}
```

## Key Takeaways

1. **Design patterns solve recurring problems** with proven solutions
2. **Singleton ensures single instance** but has testing and coupling issues
3. **Factory patterns provide flexibility** in object creation
4. **Builder pattern handles complex construction** with many parameters
5. **Prototype pattern enables object cloning** for expensive creations
6. **Choose patterns based on specific needs** - no one-size-fits-all
7. **Consider modern frameworks** that provide pattern implementations
8. **Enum is the best Singleton implementation** - handles all attack vectors
9. **Patterns should improve code quality** - don't force them unnecessarily
10. **Understand trade-offs** - every pattern has pros and cons