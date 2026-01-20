## Structural Patterns 

### 256. What is Adapter pattern?

**Adapter pattern** allows **incompatible interfaces** to work together by creating a wrapper that converts one interface to another:

#### Problem Scenario:
```java
// Existing class with incompatible interface
class LegacyPrinter {
    public void printOldFormat(String text) {
        System.out.println("Legacy: " + text);
    }
}

// New interface expected by client
interface ModernPrinter {
    void print(String text);
}
```

#### Adapter Solution:
```java
// Adapter class
class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter;
    
    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }
    
    @Override
    public void print(String text) {
        // Adapt the interface
        legacyPrinter.printOldFormat(text);
    }
}
```

#### Usage:
```java
public class AdapterExample {
    public static void main(String[] args) {
        // Legacy printer
        LegacyPrinter legacyPrinter = new LegacyPrinter();
        
        // Adapt to modern interface
        ModernPrinter adapter = new PrinterAdapter(legacyPrinter);
        
        // Client uses modern interface
        adapter.print("Hello World"); // Output: Legacy: Hello World
    }
}
```

#### Real-world Example - Database Adapters:
```java
// Different database interfaces
interface DatabaseConnection {
    void connect();
    void executeQuery(String sql);
}

class MySQLConnection {
    public void mysqlConnect() { System.out.println("MySQL connected"); }
    public void mysqlQuery(String sql) { System.out.println("MySQL: " + sql); }
}

class PostgreSQLConnection {
    public void pgConnect() { System.out.println("PostgreSQL connected"); }
    public void pgExecute(String sql) { System.out.println("PostgreSQL: " + sql); }
}

// Adapters
class MySQLAdapter implements DatabaseConnection {
    private MySQLConnection mysql;
    
    public MySQLAdapter(MySQLConnection mysql) {
        this.mysql = mysql;
    }
    
    public void connect() { mysql.mysqlConnect(); }
    public void executeQuery(String sql) { mysql.mysqlQuery(sql); }
}

class PostgreSQLAdapter implements DatabaseConnection {
    private PostgreSQLConnection postgres;
    
    public PostgreSQLAdapter(PostgreSQLConnection postgres) {
        this.postgres = postgres;
    }
    
    public void connect() { postgres.pgConnect(); }
    public void executeQuery(String sql) { postgres.pgExecute(sql); }
}
```

### 257. What is Decorator pattern?

**Decorator pattern** adds **new functionality** to objects dynamically without altering their structure:

#### Basic Implementation:
```java
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
    
    public double getCost() {
        return 2.0;
    }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    public String getDescription() {
        return coffee.getDescription();
    }
    
    public double getCost() {
        return coffee.getCost();
    }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}

class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }
    
    public String getDescription() {
        return coffee.getDescription() + ", Whip";
    }
    
    public double getCost() {
        return coffee.getCost() + 0.7;
    }
}
```

#### Usage:
```java
public class DecoratorExample {
    public static void main(String[] args) {
        // Start with simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());
        
        // Add milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());
        
        // Add sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());
        
        // Add whip
        coffee = new WhipDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());
        
        // Output:
        // Simple Coffee - $2.0
        // Simple Coffee, Milk - $2.5
        // Simple Coffee, Milk, Sugar - $2.7
        // Simple Coffee, Milk, Sugar, Whip - $3.4
    }
}
```

#### Real-world Example - I/O Streams:
```java
// Java I/O uses Decorator pattern extensively
FileInputStream fileStream = new FileInputStream("file.txt");
BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
DataInputStream dataStream = new DataInputStream(bufferedStream);

// Each decorator adds functionality:
// FileInputStream - basic file reading
// BufferedInputStream - adds buffering
// DataInputStream - adds data type reading methods
```

### 258. What is Facade pattern?

**Facade pattern** provides a **simplified interface** to a complex subsystem:

#### Complex Subsystem:
```java
// Complex subsystem classes
class CPU {
    public void freeze() { System.out.println("CPU: Freezing processor"); }
    public void jump(long position) { System.out.println("CPU: Jumping to " + position); }
    public void execute() { System.out.println("CPU: Executing instructions"); }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: Loading data at " + position);
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: Reading " + size + " bytes from " + lba);
        return new byte[size];
    }
}
```

#### Facade Implementation:
```java
// Facade class
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void start() {
        System.out.println("Starting computer...");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("Computer started successfully!");
    }
}
```

#### Usage:
```java
public class FacadeExample {
    public static void main(String[] args) {
        // Client uses simple interface
        ComputerFacade computer = new ComputerFacade();
        computer.start();
        
        // Instead of complex interactions:
        // CPU cpu = new CPU();
        // Memory memory = new Memory();
        // HardDrive hardDrive = new HardDrive();
        // cpu.freeze();
        // memory.load(0, hardDrive.read(0, 1024));
        // cpu.jump(0);
        // cpu.execute();
    }
}
```

#### Real-world Example - Banking System:
```java
// Complex banking subsystem
class AccountService {
    public boolean hasEnoughBalance(String account, double amount) {
        return true; // Simplified
    }
}

class SecurityService {
    public boolean authenticate(String pin) {
        return true; // Simplified
    }
}

class NotificationService {
    public void sendSMS(String message) {
        System.out.println("SMS: " + message);
    }
}

class LedgerService {
    public void recordTransaction(String account, double amount) {
        System.out.println("Transaction recorded: " + account + " - $" + amount);
    }
}

// Banking facade
class BankingFacade {
    private AccountService accountService;
    private SecurityService securityService;
    private NotificationService notificationService;
    private LedgerService ledgerService;
    
    public BankingFacade() {
        this.accountService = new AccountService();
        this.securityService = new SecurityService();
        this.notificationService = new NotificationService();
        this.ledgerService = new LedgerService();
    }
    
    public boolean withdrawMoney(String account, String pin, double amount) {
        System.out.println("Processing withdrawal...");
        
        if (!securityService.authenticate(pin)) {
            System.out.println("Authentication failed");
            return false;
        }
        
        if (!accountService.hasEnoughBalance(account, amount)) {
            System.out.println("Insufficient balance");
            return false;
        }
        
        ledgerService.recordTransaction(account, -amount);
        notificationService.sendSMS("Withdrawal successful: $" + amount);
        return true;
    }
}
```

### 259. What is Proxy pattern?

**Proxy pattern** provides a **placeholder or surrogate** for another object to control access to it:

#### Types of Proxies:

##### 1. Virtual Proxy (Lazy Loading):
```java
// Expensive object
class HighResolutionImage {
    private String filename;
    
    public HighResolutionImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading high resolution image: " + filename);
        // Simulate expensive loading operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Image interface
interface Image {
    void display();
}

// Concrete image
class RealImage implements Image {
    private HighResolutionImage image;
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
    }
    
    public void display() {
        if (image == null) {
            image = new HighResolutionImage(filename); // Lazy loading
        }
        image.display();
    }
}

// Proxy
class ImageProxy implements Image {
    private RealImage realImage;
    private String filename;
    
    public ImageProxy(String filename) {
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

##### 2. Protection Proxy (Access Control):
```java
interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

class RealBankAccount implements BankAccount {
    private double balance;
    
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }
    
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

class BankAccountProxy implements BankAccount {
    private RealBankAccount realAccount;
    private String userRole;
    
    public BankAccountProxy(String userRole) {
        this.userRole = userRole;
        this.realAccount = new RealBankAccount();
    }
    
    public void deposit(double amount) {
        realAccount.deposit(amount);
    }
    
    public void withdraw(double amount) {
        if ("ADMIN".equals(userRole) || "OWNER".equals(userRole)) {
            realAccount.withdraw(amount);
        } else {
            System.out.println("Access denied: Insufficient privileges");
        }
    }
    
    public double getBalance() {
        if ("ADMIN".equals(userRole) || "OWNER".equals(userRole)) {
            return realAccount.getBalance();
        } else {
            System.out.println("Access denied: Cannot view balance");
            return 0;
        }
    }
}
```

##### 3. Caching Proxy:
```java
interface WebService {
    String getData(String request);
}

class RealWebService implements WebService {
    public String getData(String request) {
        System.out.println("Fetching data from server for: " + request);
        // Simulate network delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Data for " + request;
    }
}

class CachingWebServiceProxy implements WebService {
    private RealWebService realService;
    private Map<String, String> cache = new HashMap<>();
    
    public CachingWebServiceProxy() {
        this.realService = new RealWebService();
    }
    
    public String getData(String request) {
        if (cache.containsKey(request)) {
            System.out.println("Returning cached data for: " + request);
            return cache.get(request);
        }
        
        String data = realService.getData(request);
        cache.put(request, data);
        return data;
    }
}
```

### 260. What is Composite pattern?

**Composite pattern** composes objects into **tree structures** to represent part-whole hierarchies:

#### Implementation:
```java
// Component interface
interface FileSystemComponent {
    void showDetails();
    int getSize();
}

// Leaf - File
class File implements FileSystemComponent {
    private String name;
    private int size;
    
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
    
    public void showDetails() {
        System.out.println("File: " + name + " (" + size + " KB)");
    }
    
    public int getSize() {
        return size;
    }
}

// Composite - Directory
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();
    
    public Directory(String name) {
        this.name = name;
    }
    
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }
    
    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }
    
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            System.out.print("  ");
            component.showDetails();
        }
    }
    
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}
```

#### Usage:
```java
public class CompositeExample {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("document.txt", 10);
        File file2 = new File("image.jpg", 500);
        File file3 = new File("video.mp4", 2000);
        
        // Create directories
        Directory documents = new Directory("Documents");
        Directory media = new Directory("Media");
        Directory root = new Directory("Root");
        
        // Build tree structure
        documents.addComponent(file1);
        media.addComponent(file2);
        media.addComponent(file3);
        
        root.addComponent(documents);
        root.addComponent(media);
        
        // Display structure
        root.showDetails();
        System.out.println("Total size: " + root.getSize() + " KB");
    }
}
```

#### Real-world Example - UI Components:
```java
interface UIComponent {
    void render();
}

class Button implements UIComponent {
    private String text;
    
    public Button(String text) {
        this.text = text;
    }
    
    public void render() {
        System.out.println("Rendering button: " + text);
    }
}

class Panel implements UIComponent {
    private List<UIComponent> components = new ArrayList<>();
    
    public void addComponent(UIComponent component) {
        components.add(component);
    }
    
    public void render() {
        System.out.println("Rendering panel:");
        for (UIComponent component : components) {
            component.render();
        }
    }
}
```

### 261. What is Bridge pattern?

**Bridge pattern** separates **abstraction from implementation**, allowing both to vary independently:

#### Implementation:
```java
// Implementation interface
interface DrawingAPI {
    void drawCircle(double x, double y, double radius);
    void drawRectangle(double x, double y, double width, double height);
}

// Concrete implementations
class DrawingAPI1 implements DrawingAPI {
    public void drawCircle(double x, double y, double radius) {
        System.out.println("API1: Drawing circle at (" + x + "," + y + ") with radius " + radius);
    }
    
    public void drawRectangle(double x, double y, double width, double height) {
        System.out.println("API1: Drawing rectangle at (" + x + "," + y + ") " + width + "x" + height);
    }
}

class DrawingAPI2 implements DrawingAPI {
    public void drawCircle(double x, double y, double radius) {
        System.out.println("API2: Circle[" + x + "," + y + "," + radius + "]");
    }
    
    public void drawRectangle(double x, double y, double width, double height) {
        System.out.println("API2: Rectangle[" + x + "," + y + "," + width + "," + height + "]");
    }
}

// Abstraction
abstract class Shape {
    protected DrawingAPI drawingAPI;
    
    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }
    
    public abstract void draw();
}

// Refined abstractions
class Circle extends Shape {
    private double x, y, radius;
    
    public Circle(double x, double y, double radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }
}

class Rectangle extends Shape {
    private double x, y, width, height;
    
    public Rectangle(double x, double y, double width, double height, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void draw() {
        drawingAPI.drawRectangle(x, y, width, height);
    }
}
```

#### Usage:
```java
public class BridgeExample {
    public static void main(String[] args) {
        Shape circle1 = new Circle(1, 2, 3, new DrawingAPI1());
        Shape circle2 = new Circle(5, 7, 11, new DrawingAPI2());
        
        circle1.draw(); // API1: Drawing circle at (1.0,2.0) with radius 3.0
        circle2.draw(); // API2: Circle[5.0,7.0,11.0]
        
        Shape rectangle1 = new Rectangle(0, 0, 4, 6, new DrawingAPI1());
        Shape rectangle2 = new Rectangle(2, 3, 8, 10, new DrawingAPI2());
        
        rectangle1.draw(); // API1: Drawing rectangle at (0.0,0.0) 4.0x6.0
        rectangle2.draw(); // API2: Rectangle[2.0,3.0,8.0,10.0]
    }
}
```

### 262. What is Flyweight pattern?

**Flyweight pattern** minimizes memory usage by **sharing efficiently** among multiple objects:

#### Implementation:
```java
// Flyweight interface
interface Character {
    void display(int row, int column);
}

// Concrete flyweight
class ConcreteCharacter implements Character {
    private char symbol; // Intrinsic state (shared)
    
    public ConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }
    
    public void display(int row, int column) { // Extrinsic state (context)
        System.out.println("Character '" + symbol + "' at position (" + row + "," + column + ")");
    }
}

// Flyweight factory
class CharacterFactory {
    private static Map<java.lang.Character, Character> characters = new HashMap<>();
    
    public static Character getCharacter(char symbol) {
        Character character = characters.get(symbol);
        if (character == null) {
            character = new ConcreteCharacter(symbol);
            characters.put(symbol, character);
            System.out.println("Created new character: " + symbol);
        }
        return character;
    }
    
    public static int getCreatedCharactersCount() {
        return characters.size();
    }
}

// Context class
class Document {
    private List<CharacterContext> characters = new ArrayList<>();
    
    public void addCharacter(char symbol, int row, int column) {
        Character character = CharacterFactory.getCharacter(symbol);
        characters.add(new CharacterContext(character, row, column));
    }
    
    public void display() {
        for (CharacterContext context : characters) {
            context.display();
        }
    }
    
    private static class CharacterContext {
        private Character character;
        private int row, column;
        
        public CharacterContext(Character character, int row, int column) {
            this.character = character;
            this.row = row;
            this.column = column;
        }
        
        public void display() {
            character.display(row, column);
        }
    }
}
```

#### Usage:
```java
public class FlyweightExample {
    public static void main(String[] args) {
        Document document = new Document();
        
        // Add characters to document
        String text = "HELLO WORLD";
        for (int i = 0; i < text.length(); i++) {
            document.addCharacter(text.charAt(i), 0, i);
        }
        
        // Display document
        document.display();
        
        System.out.println("Total unique characters created: " + 
                          CharacterFactory.getCreatedCharactersCount());
        // Only unique characters are created, duplicates are shared
    }
}
```

### 263. When would you use structural patterns?

#### Pattern Selection Guide:

##### Adapter Pattern:
**Use When:**
- Integrating incompatible interfaces
- Working with legacy code
- Third-party library integration

```java
// Legacy system integration
LegacyPaymentSystem legacy = new LegacyPaymentSystem();
ModernPaymentInterface adapter = new PaymentAdapter(legacy);
```

##### Decorator Pattern:
**Use When:**
- Adding functionality dynamically
- Avoiding class explosion
- Flexible feature combinations

```java
// Adding features to objects
InputStream stream = new BufferedInputStream(
    new GZIPInputStream(
        new FileInputStream("file.gz")
    )
);
```

##### Facade Pattern:
**Use When:**
- Simplifying complex subsystems
- Providing unified interface
- Reducing dependencies

```java
// Simplifying complex operations
EmailFacade emailService = new EmailFacade();
emailService.sendEmail("user@example.com", "Subject", "Body");
```

##### Proxy Pattern:
**Use When:**
- Controlling access to objects
- Lazy loading expensive resources
- Adding security or caching

```java
// Lazy loading expensive resources
Image image = new ImageProxy("large_image.jpg");
image.display(); // Loads only when needed
```

##### Composite Pattern:
**Use When:**
- Tree-like object structures
- Treating individual and composite objects uniformly
- Hierarchical data representation

```java
// File system, UI components, organizational structures
Directory root = new Directory("root");
root.addComponent(new File("file.txt"));
root.addComponent(new Directory("subfolder"));
```

#### Decision Matrix:

| Scenario | Recommended Pattern | Reason |
|----------|-------------------|---------|
| **Interface Incompatibility** | Adapter | Convert interfaces |
| **Adding Features Dynamically** | Decorator | Flexible enhancement |
| **Complex System Simplification** | Facade | Unified interface |
| **Access Control** | Proxy | Controlled access |
| **Tree Structures** | Composite | Uniform treatment |
| **Platform Independence** | Bridge | Separate abstraction |
| **Memory Optimization** | Flyweight | Share common data |

### 264. How do you implement Decorator pattern?

#### Step-by-Step Implementation:

##### 1. Define Component Interface:
```java
interface TextProcessor {
    String process(String text);
}
```

##### 2. Create Concrete Component:
```java
class PlainText implements TextProcessor {
    public String process(String text) {
        return text;
    }
}
```

##### 3. Create Base Decorator:
```java
abstract class TextDecorator implements TextProcessor {
    protected TextProcessor processor;
    
    public TextDecorator(TextProcessor processor) {
        this.processor = processor;
    }
    
    public String process(String text) {
        return processor.process(text);
    }
}
```

##### 4. Create Concrete Decorators:
```java
class UpperCaseDecorator extends TextDecorator {
    public UpperCaseDecorator(TextProcessor processor) {
        super(processor);
    }
    
    public String process(String text) {
        return super.process(text).toUpperCase();
    }
}

class BoldDecorator extends TextDecorator {
    public BoldDecorator(TextProcessor processor) {
        super(processor);
    }
    
    public String process(String text) {
        return "<b>" + super.process(text) + "</b>";
    }
}

class ItalicDecorator extends TextDecorator {
    public ItalicDecorator(TextProcessor processor) {
        super(processor);
    }
    
    public String process(String text) {
        return "<i>" + super.process(text) + "</i>";
    }
}
```

##### 5. Usage:
```java
public class DecoratorImplementation {
    public static void main(String[] args) {
        TextProcessor processor = new PlainText();
        
        // Add decorators
        processor = new UpperCaseDecorator(processor);
        processor = new BoldDecorator(processor);
        processor = new ItalicDecorator(processor);
        
        String result = processor.process("hello world");
        System.out.println(result); // <i><b>HELLO WORLD</b></i>
    }
}
```

#### Advanced Example - HTTP Request Decorators:
```java
interface HttpRequest {
    String execute();
}

class BasicHttpRequest implements HttpRequest {
    private String url;
    
    public BasicHttpRequest(String url) {
        this.url = url;
    }
    
    public String execute() {
        return "GET " + url;
    }
}

abstract class HttpRequestDecorator implements HttpRequest {
    protected HttpRequest request;
    
    public HttpRequestDecorator(HttpRequest request) {
        this.request = request;
    }
    
    public String execute() {
        return request.execute();
    }
}

class AuthenticationDecorator extends HttpRequestDecorator {
    private String token;
    
    public AuthenticationDecorator(HttpRequest request, String token) {
        super(request);
        this.token = token;
    }
    
    public String execute() {
        return super.execute() + " [Auth: " + token + "]";
    }
}

class LoggingDecorator extends HttpRequestDecorator {
    public LoggingDecorator(HttpRequest request) {
        super(request);
    }
    
    public String execute() {
        String result = super.execute();
        System.out.println("LOG: Executing request - " + result);
        return result;
    }
}
```

### 265. What is the difference between Adapter and Facade?

#### Key Differences:

| Aspect | Adapter Pattern | Facade Pattern |
|--------|-----------------|----------------|
| **Purpose** | Interface conversion | Interface simplification |
| **Scope** | Single class adaptation | Multiple class coordination |
| **Intent** | Make incompatible interfaces work | Hide complexity |
| **Structure** | Wraps one object | Coordinates multiple objects |
| **Client Knowledge** | Knows about adaptation | Unaware of subsystem complexity |

#### Adapter Pattern Example:
```java
// Existing incompatible interface
class EuropeanSocket {
    public void plugIn() {
        System.out.println("Plugged into European socket");
    }
}

// Target interface
interface AmericanSocket {
    void connect();
}

// Adapter - converts interface
class SocketAdapter implements AmericanSocket {
    private EuropeanSocket europeanSocket;
    
    public SocketAdapter(EuropeanSocket europeanSocket) {
        this.europeanSocket = europeanSocket;
    }
    
    public void connect() {
        europeanSocket.plugIn(); // Interface conversion
    }
}

// Usage
EuropeanSocket europeanSocket = new EuropeanSocket();
AmericanSocket adapter = new SocketAdapter(europeanSocket);
adapter.connect(); // Converts American interface to European
```

#### Facade Pattern Example:
```java
// Complex subsystem
class AudioSystem {
    public void turnOn() { System.out.println("Audio system on"); }
    public void setVolume(int level) { System.out.println("Volume: " + level); }
}

class VideoSystem {
    public void turnOn() { System.out.println("Video system on"); }
    public void setResolution(String res) { System.out.println("Resolution: " + res); }
}

class LightingSystem {
    public void dimLights() { System.out.println("Lights dimmed"); }
}

// Facade - simplifies complex operations
class HomeTheaterFacade {
    private AudioSystem audio;
    private VideoSystem video;
    private LightingSystem lighting;
    
    public HomeTheaterFacade() {
        this.audio = new AudioSystem();
        this.video = new VideoSystem();
        this.lighting = new LightingSystem();
    }
    
    public void watchMovie() {
        System.out.println("Setting up movie mode...");
        lighting.dimLights();
        audio.turnOn();
        audio.setVolume(8);
        video.turnOn();
        video.setResolution("1080p");
        System.out.println("Movie mode ready!");
    }
}

// Usage
HomeTheaterFacade homeTheater = new HomeTheaterFacade();
homeTheater.watchMovie(); // Simple interface hides complexity
```

#### Comparison in Context:

##### Adapter - Interface Conversion:
```java
// Problem: Incompatible interfaces
interface MediaPlayer {
    void play(String audioType, String fileName);
}

class Mp3Player {
    public void playMp3(String fileName) {
        System.out.println("Playing mp3: " + fileName);
    }
}

// Adapter converts Mp3Player interface to MediaPlayer
class Mp3Adapter implements MediaPlayer {
    private Mp3Player mp3Player;
    
    public Mp3Adapter() {
        this.mp3Player = new Mp3Player();
    }
    
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            mp3Player.playMp3(fileName); // Interface conversion
        }
    }
}
```

##### Facade - Complexity Hiding:
```java
// Problem: Complex subsystem
class DatabaseConnection { /* complex implementation */ }
class QueryBuilder { /* complex implementation */ }
class ResultProcessor { /* complex implementation */ }

// Facade hides complexity
class DatabaseFacade {
    private DatabaseConnection connection;
    private QueryBuilder queryBuilder;
    private ResultProcessor processor;
    
    public List<User> getUsers() {
        // Hides complex operations
        connection.connect();
        String query = queryBuilder.buildSelectQuery("users");
        ResultSet results = connection.execute(query);
        return processor.processResults(results);
    }
}
```

#### When to Use Each:

##### Use Adapter When:
- **Existing code** has incompatible interface
- **Third-party libraries** don't match your interface
- **Legacy systems** need integration
- **Interface mismatch** between components

##### Use Facade When:
- **Complex subsystem** needs simplification
- **Multiple classes** need coordination
- **Client code** should be decoupled from subsystem
- **Common operations** need unified interface

## Best Practices

### 1. Choose Based on Intent:
```java
// Adapter - for compatibility
PaymentAdapter adapter = new PaymentAdapter(legacyPaymentSystem);

// Facade - for simplification
EmailFacade emailService = new EmailFacade();
```

### 2. Keep Interfaces Simple:
```java
// Good - simple, focused interface
interface Shape {
    void draw();
    double getArea();
}

// Avoid - complex, multi-purpose interface
interface ComplexShape {
    void draw();
    void rotate();
    void scale();
    void translate();
    void applyTexture();
    // ... many more methods
}
```

### 3. Use Composition Over Inheritance:
```java
// Prefer composition in structural patterns
class Decorator {
    private Component component; // Composition
    
    public Decorator(Component component) {
        this.component = component;
    }
}
```

### 4. Consider Performance Implications:
```java
// Flyweight - optimize memory usage
class CharacterFactory {
    private static Map<Character, CharacterFlyweight> flyweights = new HashMap<>();
    
    public static CharacterFlyweight getFlyweight(char c) {
        return flyweights.computeIfAbsent(c, CharacterFlyweight::new);
    }
}
```

## Key Takeaways

1. **Structural patterns organize object composition** and relationships
2. **Adapter converts interfaces** for compatibility
3. **Decorator adds behavior dynamically** without inheritance
4. **Facade simplifies complex subsystems** with unified interface
5. **Proxy controls access** and adds functionality transparently
6. **Composite handles tree structures** uniformly
7. **Bridge separates abstraction from implementation**
8. **Flyweight optimizes memory** by sharing common data
9. **Choose patterns based on specific structural needs**
10. **Consider modern alternatives** like dependency injection frameworks