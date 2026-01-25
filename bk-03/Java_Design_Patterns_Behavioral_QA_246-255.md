## Behavioral Patterns 

### 246. What is Observer pattern?

**Observer pattern** defines a **one-to-many dependency** between objects where state changes in one object trigger notifications to all dependents:

#### Key Components:
- **Subject (Observable)**: Maintains list of observers and notifies them of changes
- **Observer**: Interface that defines update method for receiving notifications
- **ConcreteSubject**: Implements subject and maintains state
- **ConcreteObserver**: Implements observer and reacts to notifications

#### Basic Implementation:
```java
// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
    
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}

// Concrete Observer
class NewsChannel implements Observer {
    private String name;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}
```

#### Usage:
```java
public class ObserverExample {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        
        NewsChannel cnn = new NewsChannel("CNN");
        NewsChannel bbc = new NewsChannel("BBC");
        
        agency.attach(cnn);
        agency.attach(bbc);
        
        agency.setNews("Breaking: New Java version released!");
        // Output:
        // CNN received news: Breaking: New Java version released!
        // BBC received news: Breaking: New Java version released!
    }
}
```

#### Real-world Example - Stock Price Monitor:
```java
class Stock implements Subject {
    private List<Observer> investors = new ArrayList<>();
    private String symbol;
    private double price;
    
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }
    
    public void notifyObservers() {
        for (Observer investor : investors) {
            investor.update(symbol + " price changed to $" + price);
        }
    }
    
    // attach/detach methods...
}

class Investor implements Observer {
    private String name;
    
    public Investor(String name) {
        this.name = name;
    }
    
    public void update(String message) {
        System.out.println(name + " notified: " + message);
    }
}
```

### 247. What is Strategy pattern?

**Strategy pattern** defines a **family of algorithms**, encapsulates each one, and makes them **interchangeable**:

#### Implementation:
```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal: " + email);
    }
}

class BankTransferPayment implements PaymentStrategy {
    private String accountNumber;
    
    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Bank Transfer: " + accountNumber);
    }
}

// Context class
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

#### Usage:
```java
public class StrategyExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Pay with credit card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout(100.0);
        
        // Switch to PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(50.0);
        
        // Switch to bank transfer
        cart.setPaymentStrategy(new BankTransferPayment("ACC-123456"));
        cart.checkout(75.0);
    }
}
```

#### Real-world Example - Sorting Strategies:
```java
interface SortStrategy {
    void sort(int[] array);
}

class BubbleSort implements SortStrategy {
    public void sort(int[] array) {
        System.out.println("Sorting using Bubble Sort");
        // Bubble sort implementation
    }
}

class QuickSort implements SortStrategy {
    public void sort(int[] array) {
        System.out.println("Sorting using Quick Sort");
        // Quick sort implementation
    }
}

class SortContext {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void performSort(int[] array) {
        strategy.sort(array);
    }
}
```

### 248. What is Command pattern?

**Command pattern** encapsulates a **request as an object**, allowing parameterization and queuing of requests:

#### Implementation:
```java
// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class Light {
    private boolean isOn = false;
    
    public void turnOn() {
        isOn = true;
        System.out.println("Light is ON");
    }
    
    public void turnOff() {
        isOn = false;
        System.out.println("Light is OFF");
    }
}

// Concrete Commands
class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    public void execute() {
        light.turnOn();
    }
    
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    public void execute() {
        light.turnOff();
    }
    
    public void undo() {
        light.turnOn();
    }
}

// Invoker
class RemoteControl {
    private Command command;
    private Command lastCommand;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
        lastCommand = command;
    }
    
    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
        }
    }
}
```

#### Usage:
```java
public class CommandExample {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        
        RemoteControl remote = new RemoteControl();
        
        remote.setCommand(lightOn);
        remote.pressButton(); // Light is ON
        
        remote.setCommand(lightOff);
        remote.pressButton(); // Light is OFF
        
        remote.pressUndo(); // Light is ON (undo last command)
    }
}
```

#### Advanced Example - Macro Commands:
```java
class MacroCommand implements Command {
    private List<Command> commands;
    
    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }
    
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
    
    public void undo() {
        // Undo in reverse order
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}

// Usage
List<Command> partyCommands = Arrays.asList(
    new LightOnCommand(livingRoomLight),
    new StereoOnCommand(stereo),
    new TVOnCommand(tv)
);

Command partyMode = new MacroCommand(partyCommands);
remote.setCommand(partyMode);
remote.pressButton(); // Executes all commands
```

### 249. What is Template Method pattern?

**Template Method pattern** defines the **skeleton of an algorithm** in a base class, letting subclasses override specific steps:

#### Implementation:
```java
// Abstract class with template method
abstract class DataProcessor {
    
    // Template method - defines algorithm skeleton
    public final void processData() {
        readData();
        processDataImpl();
        writeData();
    }
    
    // Common implementation
    private void readData() {
        System.out.println("Reading data from source");
    }
    
    // Abstract method - subclasses must implement
    protected abstract void processDataImpl();
    
    // Hook method - subclasses can override
    protected void writeData() {
        System.out.println("Writing data to default output");
    }
}

// Concrete implementations
class CSVProcessor extends DataProcessor {
    protected void processDataImpl() {
        System.out.println("Processing CSV data");
    }
    
    protected void writeData() {
        System.out.println("Writing CSV data to file");
    }
}

class XMLProcessor extends DataProcessor {
    protected void processDataImpl() {
        System.out.println("Processing XML data");
    }
    
    protected void writeData() {
        System.out.println("Writing XML data to database");
    }
}

class JSONProcessor extends DataProcessor {
    protected void processDataImpl() {
        System.out.println("Processing JSON data");
    }
    // Uses default writeData() implementation
}
```

#### Usage:
```java
public class TemplateMethodExample {
    public static void main(String[] args) {
        DataProcessor csvProcessor = new CSVProcessor();
        csvProcessor.processData();
        // Output:
        // Reading data from source
        // Processing CSV data
        // Writing CSV data to file
        
        DataProcessor xmlProcessor = new XMLProcessor();
        xmlProcessor.processData();
        
        DataProcessor jsonProcessor = new JSONProcessor();
        jsonProcessor.processData();
    }
}
```

#### Real-world Example - Game Framework:
```java
abstract class Game {
    
    // Template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
    
    protected abstract void initialize();
    protected abstract void startPlay();
    protected abstract void endPlay();
}

class Cricket extends Game {
    protected void initialize() {
        System.out.println("Cricket Game Initialized");
    }
    
    protected void startPlay() {
        System.out.println("Cricket Game Started");
    }
    
    protected void endPlay() {
        System.out.println("Cricket Game Finished");
    }
}

class Football extends Game {
    protected void initialize() {
        System.out.println("Football Game Initialized");
    }
    
    protected void startPlay() {
        System.out.println("Football Game Started");
    }
    
    protected void endPlay() {
        System.out.println("Football Game Finished");
    }
}
```

### 250. What is State pattern?

**State pattern** allows an object to **alter its behavior** when its internal state changes:

#### Implementation:
```java
// State interface
interface State {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispenseProduct(VendingMachine machine);
}

// Context
class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State soldState;
    private State currentState;
    
    public VendingMachine() {
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        soldState = new SoldState();
        currentState = noCoinState;
    }
    
    public void setState(State state) {
        this.currentState = state;
    }
    
    public void insertCoin() {
        currentState.insertCoin(this);
    }
    
    public void selectProduct() {
        currentState.selectProduct(this);
    }
    
    public void dispenseProduct() {
        currentState.dispenseProduct(this);
    }
    
    // Getters for states
    public State getNoCoinState() { return noCoinState; }
    public State getHasCoinState() { return hasCoinState; }
    public State getSoldState() { return soldState; }
}

// Concrete States
class NoCoinState implements State {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted");
        machine.setState(machine.getHasCoinState());
    }
    
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please insert coin first");
    }
    
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please insert coin first");
    }
}

class HasCoinState implements State {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin already inserted");
    }
    
    public void selectProduct(VendingMachine machine) {
        System.out.println("Product selected");
        machine.setState(machine.getSoldState());
    }
    
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please select product first");
    }
}

class SoldState implements State {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Please wait, dispensing product");
    }
    
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please wait, dispensing product");
    }
    
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Product dispensed");
        machine.setState(machine.getNoCoinState());
    }
}
```

#### Usage:
```java
public class StateExample {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        
        machine.selectProduct(); // Please insert coin first
        machine.insertCoin();    // Coin inserted
        machine.selectProduct(); // Product selected
        machine.dispenseProduct(); // Product dispensed
    }
}
```

### 251. What is Chain of Responsibility pattern?

**Chain of Responsibility** passes requests along a **chain of handlers** until one handles it:

#### Implementation:
```java
// Handler interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;
    
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract void handleRequest(SupportRequest request);
}

// Request class
class SupportRequest {
    private String type;
    private String description;
    
    public SupportRequest(String type, String description) {
        this.type = type;
        this.description = description;
    }
    
    public String getType() { return type; }
    public String getDescription() { return description; }
}

// Concrete handlers
class Level1Support extends SupportHandler {
    public void handleRequest(SupportRequest request) {
        if (request.getType().equals("BASIC")) {
            System.out.println("Level 1 Support handled: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class Level2Support extends SupportHandler {
    public void handleRequest(SupportRequest request) {
        if (request.getType().equals("TECHNICAL")) {
            System.out.println("Level 2 Support handled: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class Level3Support extends SupportHandler {
    public void handleRequest(SupportRequest request) {
        if (request.getType().equals("CRITICAL")) {
            System.out.println("Level 3 Support handled: " + request.getDescription());
        } else {
            System.out.println("Request cannot be handled: " + request.getDescription());
        }
    }
}
```

#### Usage:
```java
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        // Create chain
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();
        
        level1.setNextHandler(level2);
        level2.setNextHandler(level3);
        
        // Send requests
        level1.handleRequest(new SupportRequest("BASIC", "Password reset"));
        level1.handleRequest(new SupportRequest("TECHNICAL", "Server down"));
        level1.handleRequest(new SupportRequest("CRITICAL", "Security breach"));
    }
}
```

### 252. What is Iterator pattern?

**Iterator pattern** provides a way to **access elements sequentially** without exposing underlying representation:

#### Implementation:
```java
// Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Aggregate interface
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// Concrete collection
class BookCollection implements Aggregate<String> {
    private List<String> books = new ArrayList<>();
    
    public void addBook(String book) {
        books.add(book);
    }
    
    public Iterator<String> createIterator() {
        return new BookIterator();
    }
    
    // Inner iterator class
    private class BookIterator implements Iterator<String> {
        private int index = 0;
        
        public boolean hasNext() {
            return index < books.size();
        }
        
        public String next() {
            if (hasNext()) {
                return books.get(index++);
            }
            throw new NoSuchElementException();
        }
    }
}
```

#### Usage:
```java
public class IteratorExample {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        collection.addBook("Design Patterns");
        collection.addBook("Clean Code");
        collection.addBook("Effective Java");
        
        Iterator<String> iterator = collection.createIterator();
        
        while (iterator.hasNext()) {
            System.out.println("Book: " + iterator.next());
        }
    }
}
```

#### Java's Built-in Iterator:
```java
// Java collections already implement Iterator pattern
List<String> list = Arrays.asList("A", "B", "C");

// Using iterator
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// Enhanced for loop uses iterator internally
for (String item : list) {
    System.out.println(item);
}
```

### 253. What is Visitor pattern?

**Visitor pattern** separates **algorithms from objects** they operate on:

#### Implementation:
```java
// Visitor interface
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
    void visit(Triangle triangle);
}

// Element interface
interface Shape {
    void accept(ShapeVisitor visitor);
}

// Concrete elements
class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public double getRadius() { return radius; }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle implements Shape {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

class Triangle implements Shape {
    private double base, height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    public double getBase() { return base; }
    public double getHeight() { return height; }
    
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete visitors
class AreaCalculator implements ShapeVisitor {
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.println("Circle area: " + area);
    }
    
    public void visit(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        System.out.println("Rectangle area: " + area);
    }
    
    public void visit(Triangle triangle) {
        double area = 0.5 * triangle.getBase() * triangle.getHeight();
        System.out.println("Triangle area: " + area);
    }
}

class PerimeterCalculator implements ShapeVisitor {
    public void visit(Circle circle) {
        double perimeter = 2 * Math.PI * circle.getRadius();
        System.out.println("Circle perimeter: " + perimeter);
    }
    
    public void visit(Rectangle rectangle) {
        double perimeter = 2 * (rectangle.getWidth() + rectangle.getHeight());
        System.out.println("Rectangle perimeter: " + perimeter);
    }
    
    public void visit(Triangle triangle) {
        // Assuming equilateral triangle for simplicity
        double perimeter = 3 * triangle.getBase();
        System.out.println("Triangle perimeter: " + perimeter);
    }
}
```

#### Usage:
```java
public class VisitorExample {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 4)
        );
        
        AreaCalculator areaCalculator = new AreaCalculator();
        PerimeterCalculator perimeterCalculator = new PerimeterCalculator();
        
        System.out.println("Calculating areas:");
        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }
        
        System.out.println("\nCalculating perimeters:");
        for (Shape shape : shapes) {
            shape.accept(perimeterCalculator);
        }
    }
}
```

### 254. When would you use behavioral patterns?

#### Pattern Selection Guide:

##### Observer Pattern:
**Use When:**
- Need to notify multiple objects about state changes
- Implementing event handling systems
- Model-View architectures

```java
// GUI event handling
button.addActionListener(event -> handleButtonClick());

// Model-View updates
model.addObserver(view);
```

##### Strategy Pattern:
**Use When:**
- Multiple ways to perform a task
- Algorithm selection at runtime
- Avoiding conditional statements

```java
// Payment processing
paymentProcessor.setStrategy(new CreditCardStrategy());

// Sorting algorithms
sorter.setStrategy(new QuickSortStrategy());
```

##### Command Pattern:
**Use When:**
- Need to queue, log, or undo operations
- Decoupling invoker from receiver
- Macro operations

```java
// GUI actions with undo
textEditor.execute(new BoldCommand());
textEditor.undo();

// Request queuing
requestQueue.add(new DatabaseCommand());
```

##### Template Method Pattern:
**Use When:**
- Common algorithm structure with varying steps
- Code reuse across similar classes
- Framework development

```java
// Data processing pipeline
abstract class DataProcessor {
    public final void process() {
        read(); transform(); write();
    }
}
```

##### State Pattern:
**Use When:**
- Object behavior depends on state
- Complex conditional logic based on state
- State machines

```java
// Vending machine states
machine.insertCoin(); // Changes state
machine.selectProduct(); // Behavior depends on state
```

#### Decision Matrix:

| Scenario | Recommended Pattern | Reason |
|----------|-------------------|---------|
| **Event Notifications** | Observer | One-to-many updates |
| **Algorithm Selection** | Strategy | Runtime flexibility |
| **Request Processing** | Chain of Responsibility | Flexible handling |
| **Undo/Redo Operations** | Command | Request encapsulation |
| **State-dependent Behavior** | State | Clean state management |
| **Collection Traversal** | Iterator | Uniform access |
| **Adding Operations** | Visitor | Open/closed principle |

### 255. How do you implement Observer pattern in Java?

#### 1. Custom Implementation:
```java
interface Observer {
    void update(Object data);
}

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Object data);
}

class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(Object data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}
```

#### 2. Using Java's Built-in Observable (Deprecated):
```java
import java.util.Observable;
import java.util.Observer;

class WeatherStation extends Observable {
    private float temperature;
    
    public void setTemperature(float temperature) {
        this.temperature = temperature;
        setChanged();
        notifyObservers(temperature);
    }
}

class Display implements Observer {
    public void update(Observable o, Object arg) {
        System.out.println("Temperature updated: " + arg);
    }
}
```

#### 3. Modern Approach - PropertyChangeListener:
```java
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

class Model {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String value;
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    public void setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        support.firePropertyChange("value", oldValue, value);
    }
}

// Usage
Model model = new Model();
model.addPropertyChangeListener(evt -> 
    System.out.println("Property changed: " + evt.getNewValue()));
```

#### 4. Reactive Streams (Modern Java):
```java
import java.util.concurrent.Flow.*;

class ReactivePublisher implements Publisher<String> {
    private List<Subscriber<? super String>> subscribers = new ArrayList<>();
    
    public void subscribe(Subscriber<? super String> subscriber) {
        subscribers.add(subscriber);
        subscriber.onSubscribe(new Subscription() {
            public void request(long n) {}
            public void cancel() {}
        });
    }
    
    public void publish(String data) {
        for (Subscriber<? super String> subscriber : subscribers) {
            subscriber.onNext(data);
        }
    }
}
```

#### 5. Event-Driven Architecture:
```java
// Using Spring's ApplicationEvent
@Component
public class OrderService {
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    public void createOrder(Order order) {
        // Create order logic
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
    }
}

@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    // Handle the event
    System.out.println("Order created: " + event.getOrder().getId());
}
```

#### Real-world Example - Stock Market:
```java
class StockPrice {
    private String symbol;
    private double price;
    private List<StockObserver> observers = new ArrayList<>();
    
    public StockPrice(String symbol) {
        this.symbol = symbol;
    }
    
    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }
    
    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.priceChanged(symbol, price);
        }
    }
}

interface StockObserver {
    void priceChanged(String symbol, double price);
}

class Portfolio implements StockObserver {
    private String name;
    
    public Portfolio(String name) {
        this.name = name;
    }
    
    public void priceChanged(String symbol, double price) {
        System.out.println(name + " portfolio: " + symbol + " price changed to $" + price);
    }
}

// Usage
StockPrice appleStock = new StockPrice("AAPL");
Portfolio portfolio1 = new Portfolio("John's");
Portfolio portfolio2 = new Portfolio("Jane's");

appleStock.addObserver(portfolio1);
appleStock.addObserver(portfolio2);

appleStock.setPrice(150.25); // Both portfolios get notified
```

## Best Practices

### 1. Choose the Right Pattern:
```java
// Use Observer for notifications
eventBus.register(listener);

// Use Strategy for algorithms
processor.setAlgorithm(new FastAlgorithm());

// Use Command for operations
commandQueue.execute(new SaveCommand());
```

### 2. Keep Interfaces Simple:
```java
// Simple, focused interfaces
interface Command {
    void execute();
}

interface Observer {
    void update(Object data);
}
```

### 3. Consider Modern Alternatives:
```java
// Instead of custom Observer, use reactive streams
Observable.just("data")
    .subscribe(System.out::println);

// Instead of Strategy, use lambdas
processor.process(data, x -> x.toUpperCase());
```

### 4. Handle Edge Cases:
```java
// Null checks and error handling
public void notifyObservers(Object data) {
    for (Observer observer : observers) {
        try {
            observer.update(data);
        } catch (Exception e) {
            logger.error("Observer notification failed", e);
        }
    }
}
```

## Key Takeaways

1. **Behavioral patterns manage object interactions** and responsibilities
2. **Observer enables loose coupling** between subjects and observers
3. **Strategy provides algorithm flexibility** at runtime
4. **Command encapsulates requests** for queuing and undo operations
5. **Template Method promotes code reuse** with algorithm skeletons
6. **State pattern manages state-dependent behavior** cleanly
7. **Chain of Responsibility provides flexible request handling**
8. **Iterator enables uniform collection traversal**
9. **Visitor separates algorithms from data structures**
10. **Modern Java provides reactive alternatives** to traditional patterns