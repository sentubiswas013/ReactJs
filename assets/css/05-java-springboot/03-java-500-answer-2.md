

# ✅ 22. Java Design Patterns 

## 0. What are SOLID principles?

**SOLID** is a set of **five object-oriented design principles** that help developers write **clean, maintainable, scalable, and loosely coupled code**.

| **Principle**                                 | **Meaning**                                                                                                      |
| --------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| **S - Single Responsibility Principle (SRP)** | A class should have **only one reason to change**, meaning it should have **one responsibility**.                |
| **O - Open/Closed Principle (OCP)**           | Software entities should be **open for extension but closed for modification**.                                  |
| **L - Liskov Substitution Principle (LSP)**   | A subclass should be able to **replace its parent class** without changing the program's behavior.               |
| **I - Interface Segregation Principle (ISP)** | Clients should not be forced to depend on **interfaces they do not use**. Create **small, specific interfaces**. |
| **D - Dependency Inversion Principle (DIP)**  | High-level modules should depend on **abstractions (interfaces)**, not concrete implementations.                 |

**Key Features:**

* Promotes **loose coupling**.
* Improves **code reusability** and **maintainability**.
* Makes applications easier to **test** and **extend**.
* Reduces the impact of future changes.

**How it Works:**

* Break responsibilities into smaller classes (**SRP**).
* Add new features by extending code instead of modifying existing code (**OCP**).
* Ensure subclasses behave correctly when replacing parent classes (**LSP**).
* Create focused interfaces instead of large, general ones (**ISP**).
* Depend on interfaces and use **Dependency Injection** (**DIP**).

**When to Use:**

* Designing **object-oriented applications**.
* Building **Spring Boot** or **enterprise Java** applications.
* When writing code that needs to be **scalable, testable, and maintainable**.

**Code Example (Dependency Inversion Principle):**

```java id="k3m8pq"
interface MessageService {
    void send(String message);
}

class EmailService implements MessageService {
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class Notification {
    private MessageService service;

    public Notification(MessageService service) {
        this.service = service;
    }

    public void notifyUser() {
        service.send("Hello User");
    }
}
```

**Easy Memory Trick:**
**S → One Responsibility**
**O → Open for Extension, Closed for Modification**
**L → Subclass should replace Parent**
**I → Small Specific Interfaces**
**D → Depend on Interfaces, Not Implementations**


**Example:**
**S — Single Responsibility Principle (SRP)**

One class should have only one responsibility.

use case:  

❌ Wrong:

```java
class OrderService {
    public void createOrder() {
        // create order
    }

    public void sendEmail() {
        // send email
    }
}
```

✅ Correct:

```java
class OrderService {
    public void createOrder() {
        // create order
    }
}

class EmailService {
    public void sendEmail() {
        // send email
    }
}
```


**O — Open/Closed Principle (OCP)**

Open for extension, closed for modification.

```java
// OCP: Open for extension, closed for modification

// This is the abstraction (interface)
// We can add new payment types without changing existing code
interface Payment {
    void pay(); // common method for all payment types
}

// Concrete implementation 1
class CardPayment implements Payment {
    public void pay() {
        // specific logic for card payment
        System.out.println("Paid by Card");
    }
}

// Concrete implementation 2
class UpiPayment implements Payment {
    public void pay() {
        // specific logic for UPI payment
        System.out.println("Paid by UPI");
    }
}

// This class uses the abstraction (Payment interface)
// It does NOT depend on concrete classes like CardPayment or UpiPayment
class PaymentService {

    // This method is CLOSED for modification
    // We don't need to change this method when new payment types are added
    public void processPayment(Payment payment) {
        // This is OPEN for extension because any new Payment type can be passed here
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {

        // Create service object
        PaymentService service = new PaymentService();

        // Using Card payment
        Payment p1 = new CardPayment();
        service.processPayment(p1);

        // Using UPI payment
        Payment p2 = new UpiPayment();
        service.processPayment(p2);

        // If we add a new payment type (e.g., WalletPayment),
        // we DO NOT modify PaymentService
        // We only create a new class implementing Payment
    }
}

// Output:
// Paid by Card
// Paid by UPI
```

Now we can add **NetBankingPayment** without changing existing code.


**L — Liskov Substitution Principle (LSP)**

Child class should replace parent class without breaking code.

```java
// LSP: Liskov Substitution Principle
// Objects of a child class should be able to replace objects of the parent class
// WITHOUT breaking the expected behavior of the program.

class Bird {

    // Base class method
    // Defines general behavior that all birds SHOULD follow
    public void fly() {
        System.out.println("Bird can fly");
    }
}

// Child class extending Bird
class Sparrow extends Bird {

    // Overriding the parent method
    // Behavior is consistent with parent (still "fly")
    @Override
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

public class Main {
    public static void main(String[] args) {

        // Parent object
        Bird b = new Bird();
        b.fly(); 
        // Output: Bird can fly

        // Child object
        Sparrow s = new Sparrow();
        s.fly(); 
        // Output: Sparrow can fly

        // LSP in action (Runtime Polymorphism)
        // Parent reference holding child object
        Bird b2 = new Sparrow();

        // This should NOT break behavior
        // Even though reference is Bird, actual object is Sparrow
        b2.fly(); 
        // Output: Sparrow can fly

        // ✔ This works perfectly → LSP is satisfied
    }
}

/*
Key Interview Points:

1. LSP means:
   Child class must be substitutable for parent class.

2. In this example:
   - Sparrow IS-A Bird
   - Sparrow does not change expected behavior
   - So it follows LSP

3. Why this is correct:
   - No exception thrown
   - No unexpected behavior
   - Same logical meaning of "fly"

4. When LSP is violated:
   Example: If we create Penguin extends Bird but override fly() to throw exception
   → That breaks LSP because Penguin cannot truly replace Bird

5. Real-world idea:
   If parent says "can fly", child must honor that contract
*/


// Output:
Bird can fly
Sparrow can fly
Sparrow can fly
```

Bad example: Penguin cannot fly → violates LSP.


**I — Interface Segregation Principle (ISP)**

Create small interfaces.

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human working");
    }

    public void eat() {
        System.out.println("Human eating");
    }
}

class Robot implements Workable {
    public void work() {
        System.out.println("Robot working");
    }
}

public class Main {
    public static void main(String[] args) {
        Workable w1 = new Human();
        w1.work();

        Eatable e1 = new Human();
        e1.eat();

        Workable w2 = new Robot();
        w2.work();
    }
}

// Output:
Human working
Human eating
Robot working
```

Robot does not implement eat() → Correct.


**D — Dependency Inversion Principle (DIP)**

Depend on abstraction, not concrete class.

```java
// DIP: Depend on abstraction, not concrete implementation

// Abstraction
interface Payment {
    void pay();
}

// Concrete implementation
class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

// High-level module (business logic)
class OrderService {

    // Depends on abstraction, not CardPayment directly
    private Payment payment;

    // Dependency is injected via constructor
    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() {
        payment.pay(); // uses abstraction
    }
}

public class Main {
    public static void main(String[] args) {

        // Inject concrete implementation at runtime
        Payment payment = new CardPayment();

        // OrderService is not tightly coupled to CardPayment
        OrderService orderService = new OrderService(payment);

        orderService.placeOrder(); // Output: Card payment
    }
}

/*
Interview Points (Quick):

- High-level class (OrderService) should NOT depend on low-level class (CardPayment)
- Both depend on abstraction (Payment)
- Easy to switch implementation (UPI, Wallet, etc.) without changing OrderService
*/

//Output:
Card payment
```

## 1. What are design patterns?

**Design Patterns** are **proven, reusable solutions** to common software design problems. They are **templates or best practices**, not complete code, that help developers write **clean, maintainable, and scalable** applications.

**Key Features:**

* Provide **standard solutions** to recurring problems.
* Promote **code reusability** and **loose coupling**.
* Improve **maintainability** and **readability**.
* Follow **object-oriented design principles** like **SOLID**.

**Types of Design Patterns:**

| **Category**   | **Purpose**                                   | **Examples**                            |
| -------------- | --------------------------------------------- | --------------------------------------- |
| **Creational** | Deals with **object creation**.               | **Singleton**, **Factory**, **Builder** |
| **Structural** | Deals with **class and object composition**.  | **Adapter**, **Decorator**, **Facade**  |
| **Behavioral** | Deals with **communication between objects**. | **Strategy**, **Observer**, **Command** |

**How it Works:**

* Identify a common design problem.
* Apply a suitable design pattern instead of creating a custom solution from scratch.
* The pattern provides a flexible and maintainable structure for the code.

**When to Use:**

* When building **large or scalable applications**.
* When the same design problem occurs repeatedly.
* To improve **code organization**, **extensibility**, and **maintainability**.
* Commonly used in **Spring Framework**, **Hibernate**, and enterprise Java applications.

**Code Example (Singleton Pattern):**

```java id="m8t2qx"
public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

**Easy Memory Trick:**

* **Creational** → How objects are **created**.
* **Structural** → How objects are **connected**.
* **Behavioral** → How objects **communicate**.


1️⃣ **Creational Design Patterns**
* **Singleton** – Only one instance of a class is created.
* **Factory Method** – Creates objects without exposing creation logic.
* **Abstract Factory** – Creates families of related objects.
* **Builder** – Builds complex objects step by step.
* **Prototype** – Creates object by cloning existing object.

2️⃣ **Structural Design Patterns**
* **Adapter** – Converts one interface into another.
* **Bridge** – Separates abstraction from implementation.
* **Decorator** – Adds behavior dynamically.
* **Facade** – Provides simplified interface to complex system.
* **Proxy** – Controls access to an object.

3️⃣ **Behavioral Design Patterns**
* **Observer** – One-to-many dependency (used in event systems).
* **Strategy** – Select algorithm at runtime.
* **Command** – Encapsulates a request as an object.
* **State** – Changes behavior when state changes.
* **Template Method** – Defines skeleton of algorithm.
* **Iterator** – Sequential access to collection.


## 2. What is Singleton pattern?

The **Singleton Pattern** is a **Creational Design Pattern** that ensures a class has **only one instance** throughout the application and provides a **global access point** to that instance.

**Key Features:**

* Creates **only one object** of a class.
* Provides a **single global access point**.
* Saves **memory and resources**.
* Can be made **thread-safe** for multithreaded applications.

**How it Works:**

1. Make the **constructor `private`** so no other class can create an object.
2. Create a **static instance** of the class.
3. Provide a **public static method** (usually `getInstance()`) to return the single instance.

**When to Use:**

* For **logging** services.
* For **configuration** or **properties** management.
* For **cache** managers.
* When only **one shared instance** is required across the application.
* Commonly used in **Spring**, where beans are **singleton-scoped by default**.

**Code Example (Eager Initialization):**

```java id="f6w9kp"
public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

**Thread-Safe Lazy Initialization:**

```java id="p4m8xt"
public class Singleton {
    private static Singleton instance;
    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
    }
}
```

**Advantages:**

* **Controlled access** to a single instance.
* **Reduces object creation overhead**.
* Easy to share common resources across the application.



## 3. How do you implement thread-safe Singleton?

**Thread-safe Singleton** can be implemented using synchronization, double-checked locking, or enum approach to prevent multiple instances in multithreaded environments.

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

The **Factory Pattern** is a **Creational Design Pattern** that provides a **centralized way to create objects** without exposing the object creation logic to the client. Instead of using `new` directly, the client asks the **factory** to create the required object.

**Key Features:**

* Encapsulates **object creation logic**.
* Promotes **loose coupling** between client and implementation classes.
* Makes code **easier to extend and maintain**.
* Follows the **Open/Closed Principle (OCP)**.

**How it Works:**

1. Create a **common interface** or abstract class.
2. Create multiple classes that implement the interface.
3. Create a **Factory class** that decides which object to create based on input.
4. The client requests an object from the factory instead of creating it directly.

**When to Use:**

* When the exact type of object is determined **at runtime**.
* When object creation logic is **complex**.
* When you want to reduce **tight coupling** between classes.
* Commonly used in **Spring**, **Hibernate**, and **JDBC DriverManager**.

**Code Example:**

```java
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
```

**Advantages:**

* Hides **object creation details**.
* Improves **code flexibility** and **reusability**.
* Makes it easier to add new implementations without changing client code.


## 5. What is Observer pattern?

The **Observer Pattern** is a **Behavioral Design Pattern** in which **one object (Subject)** automatically **notifies multiple dependent objects (Observers)** whenever its state changes. It establishes a **one-to-many relationship** between objects.

**Key Features:**

* Supports **one-to-many communication**.
* Promotes **loose coupling** between the subject and observers.
* Observers are **automatically updated** when the subject changes.
* Easy to **add or remove observers** without changing the subject.

**How it Works:**

1. A **Subject** maintains a list of registered **Observers**.
2. Observers **subscribe** to the subject.
3. When the subject's state changes, it calls the **`update()`** method on all observers.
4. Each observer performs its own action based on the notification.

**When to Use:**

* **Event-driven systems**.
* **Notification services** (Email, SMS, Push Notifications).
* **Stock price** or **weather update** applications.
* Commonly used in **Spring Event Listeners** and **GUI frameworks**.

**Code Example:**

```java id="n4v8kp"
interface Observer {
    void update(String message);
}

class EmailObserver implements Observer {
    public void update(String message) {
        System.out.println("Email received: " + message);
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

**Using the Observer Pattern:**

```java id="m7q2tx"
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new EmailObserver());

        subject.notifyObservers("Order Placed Successfully!");
    }
}
```

**Advantages:**

* Reduces **tight coupling** between objects.
* Makes the system **flexible** and **extensible**.
* Supports **event-based communication**.


## 6. What is Strategy pattern?

The **Strategy Pattern** is a **Behavioral Design Pattern** that defines a **family of algorithms**, encapsulates each one in a separate class, and allows them to be **interchanged at runtime** without changing the client code.

**Key Features:**

* Encapsulates **different algorithms** in separate classes.
* Allows changing the **behavior at runtime**.
* Promotes **loose coupling**.
* Follows the **Open/Closed Principle (OCP)** by allowing new strategies without modifying existing code.

**How it Works:**

1. Define a common **Strategy interface**.
2. Create multiple classes implementing the interface, each with a different algorithm.
3. The **Context** class holds a reference to a strategy.
4. At runtime, the client selects the required strategy, and the context delegates the work to it.

**When to Use:**

* When there are **multiple ways to perform the same task**.
* To avoid large **`if-else`** or **`switch`** statements.
* When the algorithm needs to be **changed dynamically**.
* Commonly used for **payment methods**, **sorting algorithms**, and **discount calculations**.

**Code Example:**

```java id="g8n4tx"
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(int amount) {
        strategy.pay(amount);
    }
}
```

**Using the Strategy Pattern:**

```java id="d5k9mp"
public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService(new UpiPayment());
        service.processPayment(1000);
    }
}
```

**Advantages:**

* Eliminates complex **conditional logic**.
* Makes code **easy to extend and maintain**.
* Allows **runtime selection** of algorithms.



## 7. What is Adapter pattern?

The **Adapter Pattern** is a **Structural Design Pattern** that allows **two incompatible interfaces** to work together by acting as a **bridge** between them. It converts the interface of one class into another interface that the client expects.

**Key Features:**

* Connects **incompatible classes** without modifying their code.
* Promotes **code reusability**.
* Provides **loose coupling** between client and implementation.
* Also known as a **Wrapper Pattern**.

**How it Works:**

1. The client expects a specific interface.
2. An existing class has a different, incompatible interface.
3. The **Adapter** implements the expected interface and internally calls the existing class.
4. The client interacts with the adapter without knowing about the incompatible class.

**When to Use:**

* When integrating **third-party libraries** or **legacy systems**.
* When existing classes cannot be modified.
* To make incompatible interfaces work together.
* Commonly used in **Spring**, where adapters convert one API format into another.

**Code Example:**

```java id="w8n4kp"
interface Charger {
    void charge();
}

class MicroUsbCharger {
    public void microUsbCharge() {
        System.out.println("Charging with Micro USB");
    }
}

class ChargerAdapter implements Charger {
    private MicroUsbCharger charger = new MicroUsbCharger();

    @Override
    public void charge() {
        charger.microUsbCharge();
    }
}
```

**Using the Adapter:**

```java id="t3m7qx"
public class Main {
    public static void main(String[] args) {
        Charger charger = new ChargerAdapter();
        charger.charge();
    }
}
```

**Advantages:**

* Reuses existing code without modification.
* Improves **flexibility** and **maintainability**.
* Simplifies integration with **legacy or external systems**.


## 8. What is Decorator pattern?

The **Decorator Pattern** is a **Structural Design Pattern** that allows you to **add new functionality to an object dynamically** without changing its existing code. It works by **wrapping** the original object inside a decorator object.

**Key Features:**

* Adds **new behavior** without modifying the original class.
* Uses **composition over inheritance**.
* Promotes **flexibility** and **code reusability**.
* Follows the **Open/Closed Principle (OCP)**.

**How it Works:**

1. Define a common **interface**.
2. Create a **base implementation** of that interface.
3. Create a **Decorator** class that also implements the interface and holds a reference to the original object.
4. The decorator adds extra behavior before or after delegating the call to the wrapped object.

**When to Use:**

* When you need to **add features dynamically** at runtime.
* When using inheritance would create too many subclasses.
* For adding **logging**, **security**, **compression**, or **caching** functionality.
* Commonly used in Java I/O classes like **`BufferedReader`** and **`BufferedInputStream`**.

**Code Example:**

```java id="x7m4kp"
interface Coffee {
    String getDescription();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }
}
```

**Using the Decorator:**

```java id="n5q8tx"
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription());
    }
}
```

**Advantages:**

* Adds functionality **without changing existing code**.
* Avoids creating many subclasses.
* Makes the system **flexible** and **easy to extend**.


## 8. What is Builder pattern?

The **Builder Pattern** is a **Creational Design Pattern** used to **construct complex objects step by step**. It is especially useful when an object has **many optional parameters** and you want to avoid multiple constructors.

**Key Features:**

* Builds objects **step by step**.
* Handles **many optional fields** cleanly.
* Creates **immutable objects** easily.
* Improves **code readability** and **maintainability**.
* Avoids the **Telescoping Constructor Problem** (too many constructor parameters).

**How it Works:**

1. Create a **Builder** class inside or outside the target class.
2. The builder contains methods to set each field.
3. Each method returns the **Builder object** to allow **method chaining**.
4. Call the **`build()`** method to create the final object.

**When to Use:**

* When a class has **many fields**, especially optional ones.
* When constructors become too large or confusing.
* When creating **immutable objects**.
* Commonly used in **Spring**, **Lombok (`@Builder`)**, and Java libraries.

**Code Example:**

```java id="r8m4kp"
public class User {
    private String name;
    private int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

**Using the Builder:**

```java id="w5q9tx"
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder()
                        .name("Alice")
                        .age(25)
                        .build();
    }
}
```

**Advantages:**

* Makes object creation **clear and readable**.
* Eliminates constructors with too many parameters.
* Supports **immutable object creation**.
* Easy to extend with new optional fields.


## 8. What is Prototype pattern?

The **Prototype Pattern** is a **Creational Design Pattern** that creates new objects by **copying (cloning) an existing object** instead of creating a new one from scratch. It is useful when object creation is **expensive or complex**.

**Key Features:**

* Creates objects by **cloning existing instances**.
* Reduces the cost of **expensive object creation**.
* Promotes **code reusability**.
* Supports **shallow copy** and **deep copy**.

**How it Works:**

1. A class implements the **`Cloneable`** interface.
2. It overrides the **`clone()`** method.
3. Instead of using `new`, the client calls `clone()` on an existing object.
4. A new object is created as a copy of the original.

**When to Use:**

* When object creation is **time-consuming** or resource-intensive.
* When you need **multiple similar objects** with slight modifications.
* When creating objects from scratch is costly.
* Commonly used in **caching**, **game development**, and **object templates**.

**Code Example:**

```java id="k4m8xp"
class Employee implements Cloneable {
    String name;

    Employee(String name) {
        this.name = name;
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }
}
```

**Using the Prototype Pattern:**

```java id="v9q3tn"
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Employee emp1 = new Employee("Alice");
        Employee emp2 = emp1.clone();

        System.out.println(emp2.name);
    }
}
```

**Advantages:**

* Improves performance by avoiding repeated object creation.
* Simplifies creating **similar objects**.
* Reduces the need for complex constructors.





# ✅ 23. Java Features of Version wise.

## 1. What are the features in Java 8?

Java 8 was a major release that introduced functional programming features and significantly changed how Java code is written. It's one of the most important Java releases.

**Major Features:**
* **Lambda Expressions:** Anonymous functions used to write shorter and cleaner code.
* **Stream API:** Allows processing collections using operations like filter, map, and reduce.
* **Optional Class:** A container object used to handle possible null values safely.
* **Default Methods:** Methods in interfaces that have a default implementation.
* **Method References:** A shorter way to call existing methods using `::` syntax.
* **Date/Time API:** The `java.time` package provides improved classes for handling date and time.
* **Nashorn JavaScript Engine:** A Java engine that allows executing JavaScript code inside the JVM.
  
**Features in Java 8**

**Java 8** introduced several powerful features that make code more **concise**, **readable**, and **efficient**, especially for **functional programming** and **parallel data processing**.

**Key Features:**

1. **Lambda Expressions (`->`)**

   * Write anonymous functions with less code.
   * Reduces boilerplate code.

   ```java id="j8l1a2"
   List<String> names = Arrays.asList("A", "B", "C");
   names.forEach(name -> System.out.println(name));
   ```

2. **Functional Interfaces**

   * An interface with **only one abstract method**.
   * Annotated with **`@FunctionalInterface`**.
   * Examples: `Runnable`, `Callable`, `Comparator`.

   ```java id="j8f3b4"
   @FunctionalInterface
   interface MyInterface {
       void display();
   }
   ```

3. **Stream API**

   * Processes collections using **functional operations** like `filter()`, `map()`, and `collect()`.
   * Supports **parallel processing**.

   ```java id="j8s5c6"
   List<String> names = Arrays.asList("John", "Alex", "Bob");

   names.stream()
        .filter(name -> name.startsWith("J"))
        .forEach(System.out::println);
   ```

4. **Method References (`::`)**

   * A shorter way to refer to existing methods.

   ```java id="j8m7d8"
   List<String> names = Arrays.asList("A", "B", "C");
   names.forEach(System.out::println);
   ```

5. **Default and Static Methods in Interfaces**

   * Interfaces can now have method implementations.

   ```java id="j8i9e1"
   interface Vehicle {
       default void start() {
           System.out.println("Vehicle Started");
       }
   }
   ```

6. **Optional Class**

   * Helps avoid **`NullPointerException`** by representing optional values.

   ```java id="j8o2f3"
   Optional<String> name = Optional.ofNullable(null);
   System.out.println(name.orElse("Default Name"));
   ```

7. **New Date and Time API (`java.time`)**

   * Introduces immutable classes like `LocalDate`, `LocalTime`, and `LocalDateTime`.

   ```java id="j8d4g5"
   LocalDate today = LocalDate.now();
   System.out.println(today);
   ```

8. **Parallel Streams**

   * Enables parallel processing of collections using multiple CPU cores.

   ```java id="j8p6h7"
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

   numbers.parallelStream()
          .forEach(System.out::println);
   ```

9. **CompletableFuture**

   * Supports **asynchronous** and **non-blocking programming**.

   ```java id="j8c8i9"
   CompletableFuture.runAsync(() ->
       System.out.println("Async Task"));
   ```

**How It Works:**

* Java 8 adds **functional programming capabilities** using **lambda expressions** and **functional interfaces**.
* The **Stream API** processes collections efficiently.
* **Optional** handles null values safely.
* **Parallel Streams** and **CompletableFuture** improve concurrency and performance.

**When to Use:**

* Simplifying collection processing.
* Writing **clean and readable code**.
* Implementing **functional programming**.
* Performing **parallel and asynchronous operations**.
* Reducing **NullPointerException** using `Optional`.


## 2. What are the features in Java 11?

**Java 11** is a **Long-Term Support (LTS)** release that introduced several features to improve **developer productivity**, **performance**, and **HTTP communication**.

**Key Features:**
- **Local Variable Type Inference (var):** Enhanced for lambda parameters
- **HTTP Client API:** Built-in HTTP client
- **String Methods:** isBlank(), lines(), strip(), repeat()
- **Files Methods:** readString(), writeString()
- **Collection.toArray():** Enhanced method
- **Nest-Based Access Control:** Better inner class access
- **Java Flight Recorder (JFR):** is a built-in profiling and monitoring tool in Java.

1. **New HTTP Client API**

   * Introduced a modern **`HttpClient`** for sending **HTTP/2** and **WebSocket** requests.
   * Replaces the older `HttpURLConnection`.

   ```java id="j11h1"
   HttpClient client = HttpClient.newHttpClient();

   HttpRequest request = HttpRequest.newBuilder()
           .uri(URI.create("https://example.com"))
           .build();

   HttpResponse<String> response =
           client.send(request, HttpResponse.BodyHandlers.ofString());

   System.out.println(response.body());
   ```

2. **`var` in Lambda Parameters**

   * Allows using **`var`** for lambda expression parameters to improve readability and support annotations.

   ```java id="j11v2"
   List<String> names = Arrays.asList("Java", "Spring");
   names.forEach((var name) -> System.out.println(name));
   ```

3. **String Utility Methods**

   * Added useful methods like **`isBlank()`**, **`lines()`**, **`strip()`**, **`stripLeading()`**, and **`stripTrailing()`**.

   ```java id="j11s3"
   String text = "  Java  ";
   System.out.println(text.strip());
   System.out.println(text.isBlank());
   ```

4. **Files Utility Methods**

   * New methods **`readString()`** and **`writeString()`** simplify file handling.

   ```java id="j11f4"
   Path path = Path.of("data.txt");
   Files.writeString(path, "Hello Java 11");
   String content = Files.readString(path);
   ```

5. **Collection to Array**

   * Added a simpler way to convert collections to arrays using **`toArray()`**.

   ```java id="j11c5"
   List<String> list = List.of("A", "B", "C");
   String[] arr = list.toArray(String[]::new);
   ```

6. **Single-File Source Code Execution**

   * Java programs can be executed without explicit compilation.

   ```text id="j11t6"
   java HelloWorld.java
   ```

7. **Nested-Based Access Control**

   * Improves access between nested classes, reducing the need for compiler-generated bridge methods and improving performance.

**How It Works:**

* Java 11 extends Java 8 features with a **modern HTTP client**, **better String and File APIs**, and **simplified coding syntax**.
* The JVM and standard libraries provide these enhancements without changing existing application logic.

**When to Use:**

* Building **REST clients** using the new `HttpClient`.
* Simplifying **String** and **file operations**.
* Running small Java programs quickly with **single-file execution**.
* Developing enterprise applications on a stable **LTS version**.


## 3. What are the features in Java 17?

**Java 17** is a **Long-Term Support (LTS)** release that brings improvements in **code readability**, **performance**, **security**, and **developer productivity**. It is widely used for modern **Spring Boot** and **enterprise applications**.

**Major Features:**
- **Sealed Classes:** Restrict class inheritance
- **Pattern Matching for instanceof:** Simplified type checking
- **Records:** Immutable data classes
- **Text Blocks:** Multi-line string literals
- **Switch Expressions:** Enhanced switch statements
- **Helpful NullPointerExceptions:** Better error messages
- **Strong Encapsulation:** JDK internals encapsulated


1. **Sealed Classes**

   * Restrict which classes can extend or implement a class or interface.
   * Improves **inheritance control**.

   ```java id="j17s1"
   public sealed class Shape
       permits Circle, Rectangle {
   }

   final class Circle extends Shape { }
   final class Rectangle extends Shape { }
   ```

2. **Pattern Matching for `switch` (Preview)**

   * Simplifies complex `if-else` and `switch` statements by matching object types.

   ```java id="j17p2"
   Object obj = "Java";

   switch (obj) {
       case String s -> System.out.println(s.toUpperCase());
       default -> System.out.println("Unknown");
   }
   ```

3. **Enhanced `switch` Expressions**

   * Allows `switch` to return values with a cleaner syntax.

   ```java id="j17e3"
   int day = 1;

   String result = switch (day) {
       case 1 -> "Monday";
       default -> "Other Day";
   };
   ```

4. **Text Blocks**

   * Makes writing **multi-line strings** easier and more readable.

   ```java id="j17t4"
   String json = """
       {
         "name": "Java",
         "version": 17
       }
       """;
   ```

5. **Records**

   * A compact way to create **immutable data classes** without writing boilerplate code like getters, constructors, and `toString()`.

   ```java id="j17r5"
   public record Employee(
       int id,
       String name
   ) {}
   ```

6. **New Random Number Generator API**

   * Introduces improved and flexible random number generators.

   ```java id="j17n6"
   Random random = new Random();
   System.out.println(random.nextInt(100));
   ```

7. **Strong Encapsulation of JDK Internals**

   * Improves **security** by preventing direct access to internal JDK APIs.

**How It Works:**

* Java 17 extends previous Java versions with features that reduce **boilerplate code**, improve **type safety**, and provide **cleaner syntax**.
* The JVM and compiler support these enhancements while maintaining backward compatibility.

**When to Use:**

* Building modern **Spring Boot 3.x** applications.
* Creating **immutable data models** using Records.
* Controlling inheritance with **Sealed Classes**.
* Writing cleaner code with **Text Blocks** and **enhanced switch expressions**.
* Enterprise applications requiring a stable **LTS version**.


## 4. What are the features in Java 21?

**Java 21** is a **Long-Term Support (LTS)** release that focuses on **simpler concurrency**, **better performance**, and **improved developer productivity**. It introduces modern features that make Java applications easier to write and scale.

**Key Features:**

1. **Virtual Threads**

   * Lightweight threads managed by the JVM.
   * Allow applications to handle **millions of concurrent tasks** with lower memory usage.

   ```java id="j21v1"
   Thread.startVirtualThread(() -> {
       System.out.println("Running in a Virtual Thread");
   });
   ```

2. **Record Patterns**

   * Simplifies extracting data from **record objects**.

   ```java id="j21r2"
   record Person(String name, int age) {}

   Object obj = new Person("John", 25);

   if (obj instanceof Person(String name, int age)) {
       System.out.println(name + " " + age);
   }
   ```

3. **Pattern Matching for `switch`**

   * Allows `switch` statements to match object types directly, reducing complex `if-else` logic.

   ```java id="j21p3"
   Object obj = "Java";

   switch (obj) {
       case String s -> System.out.println(s.toUpperCase());
       default -> System.out.println("Unknown");
   }
   ```

4. **Sequenced Collections**

   * Introduces a common API for collections that have a defined encounter order, with methods like `getFirst()` and `getLast()`.

   ```java id="j21s4"
   List<String> list = List.of("A", "B", "C");
   System.out.println(list.getFirst());
   ```

5. **String Templates (Preview)**

   * Provides a cleaner and safer way to create dynamic strings.

   ```java id="j21t5"
   String name = "Java";
   String message = STR."Hello \{name}";
   ```

6. **Unnamed Patterns and Variables (Preview)**

   * Uses `_` to ignore unused variables, making code cleaner.

   ```java id="j21u6"
   if (obj instanceof Person(_, int age)) {
       System.out.println(age);
   }
   ```

**How It Works:**

* **Virtual Threads** improve concurrency by allowing the JVM to efficiently manage many lightweight threads.
* **Pattern Matching** and **Record Patterns** reduce boilerplate code.
* **Sequenced Collections** and **String Templates** make APIs easier to use and code more readable.

**When to Use:**

* Building **high-concurrency** applications and **microservices**.
* Developing **Spring Boot 3.x** applications on Java 21.
* Simplifying object processing with **Pattern Matching**.
* Writing cleaner and more maintainable Java code.
* Enterprise applications requiring a stable **LTS version**.


## 5. What is the Java release cycle and LTS versions?

Java moved to a 6-month release cycle in 2017, providing regular updates with new features while maintaining Long Term Support versions for enterprise stability.

**Release Cycle:**
- **6-month releases:** March and September each year
- **Feature releases:** Java 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21...
- **LTS releases:** Every 3 years (Java 8, 11, 17, 21...)
- **Support timeline:** LTS versions supported for 8+ years

**LTS Versions:**
- **Java 8 (2014):** Lambda expressions, Stream API - supported until 2030+
- **Java 11 (2018):** HTTP Client, var enhancements - supported until 2026+
- **Java 17 (2021):** Sealed classes, Records, Pattern matching - supported until 2029+
- **Java 21 (2023):** Latest LTS with Virtual Threads, Pattern matching

**Benefits of 6-month cycle:**
- **Faster innovation:** New features delivered quickly
- **Predictable releases:** Regular schedule for planning
- **Reduced risk:** Smaller changes per release
- **LTS stability:** Enterprise applications can stick to LTS versions

**Choosing Java Version:**
- **Enterprise applications:** Use LTS versions (8, 11, 17, 21)
- **New projects:** Consider latest LTS (Java 17 or 21)
- **Experimentation:** Try latest feature releases for new capabilities
- **Migration strategy:** Plan upgrades around LTS releases




# ✅ 24. Java and Application Security

## 0. What are vulnerability issues?

A **Security Vulnerability** is a **weakness or flaw** in an application, system, or code that attackers can exploit to **steal data, gain unauthorized access, or disrupt services**.

**Key Features**

* **Weakness** in code or configuration.
* Can lead to **data breaches** or **system compromise**.
* Should be identified using **security scans**, **code reviews**, and **penetration testing**.
* Fixed by following **secure coding practices**.

**Common Security Vulnerabilities**

| **Vulnerability**                     | **Description**                                                  | **Solution**                                       |
| ------------------------------------- | ---------------------------------------------------------------- | -------------------------------------------------- |
| **SQL Injection**                     | Attacker injects SQL queries to access database data.            | Use **Prepared Statements** / **JPA**              |
| **Cross-Site Scripting (XSS)**        | Malicious JavaScript is injected into web pages.                 | Validate input and **escape output**               |
| **Cross-Site Request Forgery (CSRF)** | Tricks users into performing unwanted actions.                   | Enable **CSRF protection**                         |
| **Broken Authentication**             | Weak login or password handling.                                 | Use **JWT**, **OAuth2**, **MFA**, strong passwords |
| **Broken Access Control**             | Users access resources without permission.                       | Use **Role-Based Access Control (RBAC)**           |
| **Sensitive Data Exposure**           | Passwords or personal data are exposed.                          | Use **HTTPS**, **encryption**, password hashing    |
| **Insecure Deserialization**          | Malicious serialized objects execute harmful code.               | Validate input and avoid untrusted deserialization |
| **Security Misconfiguration**         | Default settings or unnecessary services expose the application. | Secure configurations and regular updates          |
| **Using Vulnerable Dependencies**     | Old libraries contain known security flaws.                      | Regularly update dependencies and scan them        |

**How it works**

Example of **SQL Injection (Vulnerable Code)**

```java
String query = "SELECT * FROM users WHERE username='" + username + "'";
```

If the user enters:

```sql
' OR '1'='1
```

The attacker may retrieve all user records.

**Secure Code**

```java
String query = "SELECT * FROM users WHERE username = ?";
PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, username);
```

Or with **Spring Data JPA**:

```java
User user = userRepository.findByUsername(username);
```

**When to use Security Best Practices**

* **REST APIs**
* **Spring Boot Applications**
* **Microservices**
* **Banking Applications**
* **Healthcare Systems**
* Any application handling **user data** or **payments**

**How We Handle Security Vulnerabilities in Spring Boot**

* Use **Spring Security** for authentication and authorization.
* Use **JWT** or **OAuth2** for secure authentication.
* Validate all **user inputs**.
* Use **Prepared Statements** or **JPA** to prevent SQL Injection.
* Store passwords using **BCrypt**.
* Enable **HTTPS**.
* Keep dependencies updated using **Maven** or **Gradle**.
* Perform regular **SAST**, **DAST**, and dependency vulnerability scans.

**Common Interview Follow-up Questions**

**Q: Which security vulnerability is most common in web applications?**

**Answer:** **SQL Injection**, **XSS**, **Broken Authentication**, and **Broken Access Control** are among the most common vulnerabilities.

**Q: How do you identify security vulnerabilities?**

**Answer:** By using **code reviews**, **SAST** (Static Application Security Testing), **DAST** (Dynamic Application Security Testing), **dependency scanners**, and **penetration testing**.

**Q: How do you prevent SQL Injection?**

**Answer:** Use **Prepared Statements**, **Spring Data JPA**, parameterized queries, and never concatenate user input into SQL queries.



## 1. How to find Security Vulnerabilities in Production? Which tools do you use?


In **Production**, we identify **Security Vulnerabilities** using **security monitoring**, **dependency scanning**, **code analysis**, **application security testing**, and **log analysis**. These tools help detect vulnerabilities early so they can be fixed before they are exploited.

**Key Features**

* Monitor **Security Logs** and **Alerts**.
* Scan **Dependencies** for known **CVEs**.
* Perform **Static (SAST)** and **Dynamic (DAST)** security testing.
* Monitor **Authentication** failures and suspicious activities.
* Continuously scan **Containers** and **Cloud Infrastructure**.
* Integrate security scans into the **CI/CD Pipeline**.


**How it works**

1. **Monitor logs** for failed logins, unauthorized access, and suspicious requests.
2. **Scan dependencies** to identify vulnerable libraries.
3. Run **SAST** tools to detect security issues in source code.
4. Run **DAST** tools to test the running application.
5. Monitor **SIEM dashboards** for security alerts.
6. Patch vulnerable libraries or fix the code.
7. Test the fix and deploy it to production.

**Common Tools Used in Production**

| **Category**                        | **Tools**                                                                 | **Purpose**                                      |
| ----------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------ |
| **Dependency Scanning**             | **OWASP Dependency-Check**, **Snyk**, **Mend (WhiteSource)**              | Detect vulnerable libraries and **CVEs**         |
| **Static Code Analysis (SAST)**     | **SonarQube**, **Checkmarx**, **Fortify**                                 | Find security issues in source code              |
| **Dynamic Security Testing (DAST)** | **OWASP ZAP**, **Burp Suite**                                             | Find vulnerabilities in a running application    |
| **Container Scanning**              | **Trivy**, **Clair**                                                      | Scan Docker images for vulnerabilities           |
| **Cloud Security**                  | **AWS Inspector**, **AWS Security Hub**, **Microsoft Defender for Cloud** | Detect cloud security issues                     |
| **Monitoring / SIEM**               | **Splunk**, **ELK Stack**, **IBM QRadar**                                 | Detect suspicious activities and security alerts |

**Example: Scan Dependencies**

**Maven Plugin**

```xml
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>12.1.8</version>
</plugin>
```

**Run Scan**

```bash
mvn dependency-check:check
```

The scan generates a report showing **vulnerable dependencies**, **CVE IDs**, and recommended fixes.

**Real-Time Example**

**Issue:** A new **Log4j** vulnerability (**CVE**) is announced.

**Steps:**

1. Run **OWASP Dependency-Check** or **Snyk**.
2. Verify whether the application uses the vulnerable **Log4j** version.
3. Check **Splunk/ELK** for any exploit attempts.
4. Upgrade to the patched version.
5. Test the application.
6. Deploy the fix and continue monitoring.

**When to use**

* Before every **Production Release**.
* During the **CI/CD Pipeline**.
* After a new **CVE** is published.
* During **Security Audits**.
* When suspicious activity is detected in production.


**Common Interview Follow-up Questions**

**1. Which tools have you used for security scanning?**

* **OWASP Dependency-Check**
* **Snyk**
* **SonarQube**
* **OWASP ZAP**
* **Burp Suite**
* **Splunk**
* **ELK Stack**

**2. What is a CVE?**

A **CVE (Common Vulnerabilities and Exposures)** is a publicly disclosed security vulnerability with a unique identifier.

**3. What is the difference between SAST and DAST?**

* **SAST** scans the **source code** without running the application.
* **DAST** tests the **running application** by simulating real attacks.

**4. Which tool is most commonly used in Spring Boot projects?**

**SonarQube** for code quality and security, **OWASP Dependency-Check** or **Snyk** for dependency scanning, and **OWASP ZAP** for API security testing.

**5. What do you do if a vulnerability is found in production?**

Analyze the impact, identify the affected component, apply the security patch or upgrade the vulnerable dependency, validate the fix in QA, deploy it to production, and continue monitoring to ensure the issue is resolved.



## 1: What is Java security model?

The **Java Security Model** is a set of **security mechanisms** that protect Java applications from **unauthorized access**, **malicious code**, and **security vulnerabilities**. It ensures that code runs in a **safe and controlled environment**.

**Key Features**

* **Authentication** – Verifies the identity of users.
* **Authorization** – Controls what users are allowed to access.
* **Access Control** – Restricts access to files, network, and system resources.
* **Class Loader** – Loads classes safely and prevents unauthorized classes from replacing trusted ones.
* **Bytecode Verifier** – Checks bytecode before execution to ensure it is valid and safe.
* **Cryptography API** – Supports **encryption**, **decryption**, **digital signatures**, and **hashing**.
* **Secure Communication** – Supports **SSL/TLS** for secure network communication.

**How it works**

1. **Class Loader** loads Java classes securely.
2. **Bytecode Verifier** checks that the bytecode is valid and follows Java rules.
3. **JVM** executes only verified bytecode.
4. **Access Control** checks whether the code has permission to access resources.
5. **Authentication** verifies the user, and **Authorization** determines what the user can do.

**Code Example**

Using **Spring Security** to restrict access based on roles:

```java
@RestController
public class AdminController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Welcome Admin";
    }
}
```

```java
// Security Manager example
import java.lang.SecurityManager;

public class MySecurityManager extends SecurityManager {
    @Override
    public void checkRead(String file) {
        if (file.startsWith("/etc/")) {
            throw new SecurityException("Access denied to system files");
        }
        super.checkRead(file);
    }
}

// Enable security manager
System.setSecurityManager(new MySecurityManager());
```

Only users with the **ADMIN** role can access the `/admin` endpoint.

**When to use**

* **Spring Boot REST APIs**
* **Microservices**
* **Banking Applications**
* **Healthcare Systems**
* Any application handling **sensitive data** or **user authentication**


**Common Interview Follow-up Questions**

**Q: What is the role of the Class Loader?**

**Answer:** The **Class Loader** securely loads Java classes into memory and prevents untrusted classes from replacing trusted ones.

**Q: What does the Bytecode Verifier do?**

**Answer:** It checks that the **bytecode** is valid, type-safe, and follows Java security rules before execution.

**Q: What is the difference between Authentication and Authorization?**

**Answer:**

* **Authentication** verifies **who the user is**.
* **Authorization** determines **what the user is allowed to access**.

**Q: How is the Java Security Model implemented in Spring Boot?**

**Answer:** By using **Spring Security**, **JWT/OAuth2**, **HTTPS**, **BCrypt** for password hashing, and **role-based access control (RBAC)** to secure APIs and resources.



## 2: What is sandbox in Java?

A **Sandbox** in Java is a **security mechanism** that runs code in a **restricted environment**, preventing it from accessing sensitive resources like the **file system**, **network**, or **operating system** without permission.

**Key Features:**

* **Restricts unauthorized access** to system resources.
* Provides **secure execution** of untrusted code.
* Uses **permissions and security policies** to control actions.
* Helps protect against **malicious or harmful code**.

**How It Works:**
Java executes code inside the **JVM (Java Virtual Machine)**. The **Security Manager** (used in older Java versions) and **class loaders** enforce rules that allow or deny operations such as reading files, opening network connections, or executing system commands.

**When to Use:**

* Running **third-party or untrusted code**.
* **Plugin-based** or **script execution** systems.
* **Online code execution** platforms.
* Applications that require an **extra security layer**.

**Simple Example:**

```java
try {
    System.getSecurityManager().checkRead("secret.txt");
    System.out.println("File access allowed.");
} catch (SecurityException e) {
    System.out.println("File access denied by the Java Sandbox.");
}
```

In this example, the **Java Sandbox** checks whether the application has permission to read the file. If not, a **SecurityException** is thrown.



## 3: What is bytecode verification?


**Bytecode Verification** is the process where the **JVM (Java Virtual Machine)** checks the compiled **`.class` (bytecode)** before execution to ensure it is **valid**, **safe**, and **does not violate Java security rules**.

**Simple Interview Definition**

**Bytecode Verification** is a **security mechanism** of the **JVM** that validates bytecode before execution to prevent **illegal**, **corrupted**, or **malicious code** from running.

**Key Features**

* **Ensures bytecode is safe** before execution.
* Prevents **illegal memory access**.
* Checks **type safety**.
* Protects against **malicious or corrupted class files**.
* Improves **JVM security** and **stability**.

**How It Works**

1. Java source code (`.java`) is compiled into **bytecode (`.class`)**.
2. The **Class Loader** loads the class.
3. The **Bytecode Verifier** checks whether the bytecode follows JVM rules.
4. If verification succeeds, the JVM executes the code.
5. If verification fails, the JVM throws a **`VerifyError`**.

**JVM Execution Flow**

```text
Java Source (.java)
        ↓
Compiler (javac)
        ↓
Bytecode (.class)
        ↓
Class Loader
        ↓
Bytecode Verifier
        ↓
Execution by JVM
```

**What Does the Bytecode Verifier Check?**

* **Type Safety** (correct data types are used).
* **Stack Integrity** (operand stack is used correctly).
* **Valid Method Calls**.
* **No Invalid Memory Access**.
* **Valid Branch Instructions**.
* **No Stack Overflow/Underflow** during bytecode execution.

**When to Use**

You **do not manually use** bytecode verification. It is **automatically performed** by the JVM whenever a class is loaded.

It is especially important in:

* **Web applications**
* **Spring Boot** applications
* **Microservices**
* **Downloaded libraries (JARs)**
* **Third-party code**

**Example**

```java
public class Demo {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println(a + b);
    }
}
```

**Explanation**

* The compiler generates **bytecode**.
* Before execution, the **Bytecode Verifier** validates the bytecode.
* If it is valid, the JVM executes the program safely.

**If Verification Fails**

The JVM throws:

```java
java.lang.VerifyError
```

This indicates the **bytecode is invalid or corrupted**, and execution is stopped to protect the JVM.

**Advantages**

* **Improves Security**
* Prevents **malicious code execution**
* Ensures **type safety**
* Prevents **memory corruption**
* Makes Java **platform-independent** and **secure**



**Common Interview Follow-up Questions**

**1. When does Bytecode Verification happen?**

It happens **during class loading**, before the JVM executes the bytecode.

**2. Why is Bytecode Verification needed?**

To ensure the bytecode is **safe**, **valid**, and cannot perform **illegal operations** that could compromise the JVM.

**3. Which JVM component performs Bytecode Verification?**

The **Bytecode Verifier**, which is part of the **Class Loading** process.

**4. What happens if verification fails?**

The JVM throws a **`VerifyError`** and prevents the class from being executed.

**5. What is the difference between Compilation and Bytecode Verification?**

| **Compilation**                         | **Bytecode Verification**                      |
| --------------------------------------- | ---------------------------------------------- |
| Converts **`.java`** to **`.class`**    | Validates **`.class`** before execution        |
| Done by the **Java Compiler (`javac`)** | Done by the **JVM Bytecode Verifier**          |
| Generates bytecode                      | Ensures the bytecode is **safe** and **valid** |



## 4: What is the security manager?


**Security Manager** is a **JVM component** that defines and enforces a **security policy** to control what operations a Java program is allowed to perform, such as **file access**, **network access**, or **system resources usage**.

**Simple Interview Definition**

A **Security Manager** is a **security control mechanism in Java** that restricts or allows application actions based on a defined **security policy**.

**Key Features**

* Controls **access to system resources** (files, network, threads).
* Enforces **security policies at runtime**.
* Prevents **unauthorized operations**.
* Works with **Permission-based model**.
* Helps in running **untrusted code safely**.

**How It Works**

1. Java application starts under the **JVM**.
2. A **Security Policy** is defined.
3. The **Security Manager** checks every sensitive operation.
4. If permission is allowed → operation executes.
5. If not allowed → **SecurityException** is thrown.

**Security Flow**

```text id="sec1"
Application Request
        ↓
Security Manager
        ↓
Policy Check
   ↓        ↓
Allow     Deny (SecurityException)
```

**When to Use**

* Running **untrusted code (plugins, applets - legacy)**.
* **Sandbox environments**.
* Applications requiring **strict access control**.
* Legacy **Java Web Start / Applet-based systems**.
* Controlled enterprise environments (historically).

**Important Note (Modern Java)**

* **Security Manager is deprecated (Java 17+)**.
* Replaced by **container security**, **OS-level security**, and **framework-based security (Spring Security, IAM, OAuth2)**.

**Example Code**

```java id="sec2"
public class SecurityExample {

    public static void main(String[] args) {

        System.setSecurityManager(new SecurityManager());

        try {
            System.out.println("Trying file access...");

            java.io.File file = new java.io.File("test.txt");
            file.createNewFile();

        } catch (SecurityException e) {
            System.out.println("Access Denied: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**Explanation**

* The **Security Manager** is enabled using `System.setSecurityManager()`.
* When file creation is attempted, JVM checks permissions.
* If not allowed → **SecurityException** is thrown.

**Types of Permissions Controlled**

* **File System Access**
* **Network Access**
* **System Properties**
* **Thread Operations**
* **Class Loading**

**Advantages**

* Provides **runtime security control**
* Protects against **malicious code**
* Enables **sandboxing**
* Fine-grained **permission management**

**Disadvantages**

* **Performance overhead**
* Complex **policy configuration**
* Now mostly **deprecated in modern Java**


**Common Interview Follow-up Questions**

**1. Is Security Manager still used in Java?**

No, it is **deprecated in Java 17+** and removed in newer versions.

**2. What happens if a permission is denied?**

The JVM throws a **SecurityException** and blocks the operation.

**3. What is the difference between Security Manager and Spring Security?**

| **Security Manager**      | **Spring Security**                     |
| ------------------------- | --------------------------------------- |
| JVM-level security        | Application-level security              |
| Controls system resources | Controls authentication & authorization |
| Now deprecated            | Widely used in modern apps              |

**4. What replaced Security Manager?**

* **Spring Security**
* **OAuth2 / JWT**
* **Container security (Docker/Kubernetes)**
* **Cloud IAM systems**

**5. Why was Security Manager deprecated?**

Because it was:

* **Complex to configure**
* **Hard to maintain**
* Added **performance overhead**
* Replaced by better modern security frameworks



## 5: What are digital signatures in Java?

**Digital Signatures in Java** are a **cryptographic mechanism** to verify **code authenticity and integrity**.

JAR files are **signed with a private key** and verified using a **public key certificate**, ensuring the code **has not been tampered with** and establishing **trust in the publisher**.

```java
// Creating digital signature
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(privateKey);
signature.update(data);
byte[] digitalSignature = signature.sign();

// Verifying signature
signature.initVerify(publicKey);
signature.update(data);
boolean isValid = signature.verify(digitalSignature);
```


## 6: What is encryption and decryption?


**Encryption** is the process of converting **plain text (readable data)** into **cipher text (unreadable format)** to protect sensitive information.
**Decryption** is the reverse process of converting **cipher text back to plain text** using a **key**.

In Java, encryption and decryption are used to secure **data in transit** and **data at rest**.

**Simple Interview Definition**

**Encryption** = converting **readable data into secure format**
**Decryption** = converting **secure data back to readable format**

**Key Features**

* Ensures **data confidentiality**
* Protects **sensitive information**
* Uses **cryptographic algorithms**
* Requires **keys (symmetric or asymmetric)**
* Prevents **unauthorized access**

**How It Works**

1. User provides **plain text data**.
2. Java uses an **encryption algorithm + key**.
3. Data is converted into **cipher text**.
4. Encrypted data is stored or transmitted.
5. Receiver uses a **decryption key** to convert it back to **plain text**.

**Flow**

```text id="enc1"
Plain Text → Encryption (Key + Algorithm) → Cipher Text
Cipher Text → Decryption (Key + Algorithm) → Plain Text
```

**Types of Encryption**

**1. Symmetric Encryption**

* Same **key** used for **encryption and decryption**
* Faster
* Example: **AES**

**2. Asymmetric Encryption**

* Uses **public key (encryption)** and **private key (decryption)**
* More secure
* Example: **RSA**

**When to Use**

* **Password storage (hashed/encrypted form)**
* **Secure API communication**
* **Banking and payment systems**
* **HTTPS (TLS encryption)**
* **Data protection in databases**
* **File encryption**

**Java Example (AES Symmetric Encryption)**

```java id="enc2"
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptionExample {

    public static void main(String[] args) throws Exception {

        // Generate Secret Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        String plainText = "Hello Java Security";

        // Encrypt
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Encrypted: " + encryptedText);

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        System.out.println("Decrypted: " + decryptedText);
    }
}
```

**Explanation**

* **AES algorithm** is used for encryption.
* A **secret key** is generated.
* Data is converted to **cipher text** using encryption mode.
* The same key is used to **decrypt** back to original text.

**Important Algorithms in Java**

| **Algorithm** | **Type**        | **Use Case**                |
| ------------- | --------------- | --------------------------- |
| **AES**       | Symmetric       | Fast secure encryption      |
| **RSA**       | Asymmetric      | Secure key exchange         |
| **DES**       | Symmetric (old) | Legacy systems              |
| **SHA**       | Hashing         | Password hashing, integrity |

**Advantages**

* Protects **confidential data**
* Prevents **data breaches**
* Ensures **secure communication**
* Widely used in **web and enterprise systems**

**Disadvantages**

* Adds **performance overhead**
* Requires **secure key management**
* Complex **implementation for strong security**



**Common Interview Follow-up Questions**

**1. What is the difference between Encryption and Hashing?**

| **Encryption**           | **Hashing**                    |
| ------------------------ | ------------------------------ |
| Reversible               | Irreversible                   |
| Uses keys                | No key used                    |
| Used for data protection | Used for integrity & passwords |

**2. What is AES and RSA?**

* **AES** → Symmetric encryption (fast, same key)
* **RSA** → Asymmetric encryption (public/private key)

**3. Is encryption enough for password storage?**

No. Passwords should be stored using **hashing algorithms (like BCrypt)**, not encryption.

**4. Where is encryption used in Java applications?**

* **HTTPS (TLS)**
* **Spring Boot APIs**
* **Database encryption**
* **File security**
* **JWT token security**

**5. What happens if encryption key is lost?**

Encrypted data becomes **unrecoverable**, as decryption is not possible without the key.



## 7: What is SSL/TLS in Java?


**SSL (Secure Sockets Layer)** and **TLS (Transport Layer Security)** are security protocols used to **encrypt communication** between a **client** and a **server** over the internet.

**TLS** is the **newer and more secure** version of **SSL**. Today, almost all applications use **TLS**, although people still commonly say **SSL**.

**Key Features**

* **Encrypts** data during transmission.
* Protects against **data theft** and **man-in-the-middle attacks**.
* Ensures **Confidentiality**, **Integrity**, and **Authentication**.
* Used by **HTTPS**, secure APIs, online banking, and e-commerce websites.

**How It Works**

1. Client sends a request to the server using **HTTPS**.
2. Server sends its **SSL/TLS Certificate**.
3. Client verifies the certificate.
4. Client and server perform a **TLS Handshake** and generate a **shared session key**.
5. All communication is **encrypted** using the session key.

```text
Client → HTTPS Request
Server → SSL/TLS Certificate
Client → Verify Certificate
TLS Handshake
Shared Session Key Created
Encrypted Communication
```

**When to Use**

Use **SSL/TLS** when:

* Building **REST APIs**.
* Sending **login credentials**.
* Transferring **payment** or **personal data**.
* Communication between **Microservices**.
* Any application accessible over the **internet**.

**Spring Boot HTTPS Configuration**

**application.properties**

```properties
server.port=8443
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=password
server.ssl.key-store-type=PKCS12
```

**Common Interview Follow-up Questions**

**1. What is the difference between SSL and TLS?**

* **SSL** is the **older** protocol and is no longer considered secure.
* **TLS** is the **newer**, **faster**, and **more secure** protocol.
* Today, almost all systems use **TLS**.

**2. What is an SSL/TLS Certificate?**

It is a **digital certificate** that verifies the **server's identity** and contains the server's **public key**.

**3. What is HTTPS?**

**HTTPS = HTTP + TLS**. It encrypts communication between the client and server.

**4. Does JWT replace SSL/TLS?**

**No.** They solve different problems:

* **TLS** protects data **during transmission**.
* **JWT** is used for **authentication** and **authorization**.

**5. Can we use JWT without TLS?**

**Technically yes, but it is not recommended.** Without **TLS**, a JWT can be intercepted during transmission, creating a security risk. In production, **JWT should always be used over HTTPS (TLS)**.


## 8: What is authentication vs authorization?


* **Authentication** is the process of **verifying the identity** of a user.
* **Authorization** is the process of **determining what an authenticated user is allowed to access or perform**.

**Simple Interview Definition**

* **Authentication** = **Who are you?**
* **Authorization** = **What are you allowed to do?**

**Key Differences**

| **Authentication**                                             | **Authorization**                            |
| -------------------------------------------------------------- | -------------------------------------------- |
| Verifies **user identity**                                     | Verifies **user permissions**                |
| Happens **first**                                              | Happens **after authentication**             |
| Uses **username, password, OTP, biometrics, JWT, OAuth login** | Uses **roles, permissions, privileges**      |
| Returns **authenticated user**                                 | Grants or denies **access to resources**     |
| Example: User logs in                                          | Example: User can access **Admin Dashboard** |

**How It Works**

1. User enters **username and password**.
2. System verifies the credentials (**Authentication**).
3. If valid, the user is logged in.
4. System checks the user's **role or permissions** (**Authorization**).
5. Access is either **granted** or **denied**.

**Real-World Example**

Imagine entering an office building:

* Showing your **ID card** at the entrance is **Authentication**.
* Accessing only the **HR floor** or **Server Room** based on your role is **Authorization**.

**When to Use**

**Authentication**

* **Login pages**
* **Mobile apps**
* **Web applications**
* **Banking systems**
* **Online shopping**

**Authorization**

* **Role-Based Access Control (RBAC)**
* **Admin/User permissions**
* **API security**
* **Microservices**
* **Enterprise applications**

**Spring Boot Example**

```java
@RestController
public class UserController {

    @GetMapping("/profile")
    public String profile() {
        return "Authenticated User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User Deleted";
    }
}
```

**Explanation**

* Accessing **`/profile`** requires the user to be **authenticated**.
* Accessing **`/users/{id}`** requires the user to have the **ADMIN** role (**authorization**).

**How Authentication is Implemented**

* **Username & Password**
* **OTP**
* **JWT (JSON Web Token)**
* **OAuth 2.0**
* **Biometric Authentication**

**How Authorization is Implemented**

* **Roles** (ADMIN, USER)
* **Permissions** (READ, WRITE, DELETE)
* **Role-Based Access Control (RBAC)**
* **Attribute-Based Access Control (ABAC)**


**Common Interview Follow-up Questions**

**1. Which comes first: Authentication or Authorization?**

**Authentication** always happens **before** **Authorization**.

**2. Can Authorization happen without Authentication?**

**No.** The system must first know **who the user is** before deciding **what they can access**.

**3. What is RBAC?**

**Role-Based Access Control (RBAC)** assigns permissions based on **user roles** such as **ADMIN**, **USER**, or **MANAGER**.

**4. What is JWT used for?**

**JWT (JSON Web Token)** is commonly used for **Authentication**. After successful login, the server issues a token, which the client sends with subsequent requests. The server validates the token and then performs **Authorization** based on the user's roles or permissions.

**5. What HTTP status codes are returned?**

* **401 Unauthorized** → **Authentication failed** (invalid or missing credentials).
* **403 Forbidden** → **Authentication succeeded**, but the user **does not have permission** to access the resource.


## 9: What is OAuth?

**OAuth (Open Authorization)** is an **authorization framework** that allows a user to give a third-party application **limited access** to their resources **without sharing their password**.

**Example:** When you click **"Login with Google"** or **"Login with GitHub"**, OAuth is used to authorize the application.

**Key Features:**

* **Password is never shared** with the third-party application.
* Uses **access tokens** instead of credentials.
* Provides **limited and controlled access** through **scopes**.
* Supports **secure delegated authorization**.
* Widely used in **REST APIs** and **microservices**.

**How It Works:**

1. User requests to log in using an OAuth provider (e.g., Google).
2. The application redirects the user to the provider's login page.
3. User authenticates and grants permission.
4. The provider returns an **authorization code**.
5. The application exchanges the code for an **access token**.
6. The application uses the access token to access the user's allowed resources.

**Main OAuth Components:**

* **Resource Owner** – The user.
* **Client** – The application requesting access.
* **Authorization Server** – Issues access tokens.
* **Resource Server** – Hosts the protected resources.
* **Access Token** – Temporary token used to access resources.

**When to Use:**

* **Social login** (Google, GitHub, Facebook).
* Securing **REST APIs**.
* **Microservices** communication.
* Allowing third-party apps to access user data securely.

**Simple Spring Boot Example:**

**application.yml**

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
```

**Security Configuration**

```java id="2lsn4f"
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
            .oauth2Login();

        return http.build();
    }
}
```

With this configuration, users can securely **log in using their Google account** without sharing their Google password with your application.


**OAuth 1.0** uses signature-based authentication and is complex, while **OAuth 2.0** is token-based, simpler, faster, and widely used in modern applications.

## 11: What is OAuth 2.0?

**OAuth 2.0** is the **latest version of the OAuth authorization framework** that allows a user to grant a third-party application **limited access** to their resources **without sharing their password**. It uses **access tokens** to securely authorize requests.

**Example:** **"Sign in with Google"** or **"Login with GitHub"** uses OAuth 2.0.

**Key Features:**

* Uses **access tokens** instead of usernames and passwords.
* Provides **secure delegated authorization**.
* Supports **scopes** to limit permissions.
* Supports **refresh tokens** to obtain new access tokens without logging in again.
* Widely used for **REST APIs**, **microservices**, and **social login**.

**How It Works:**

1. The user requests login through an OAuth 2.0 provider (e.g., Google).
2. The application redirects the user to the provider's login page.
3. The user authenticates and grants permission.
4. The provider returns an **authorization code**.
5. The application exchanges the code for an **access token** (and optionally a **refresh token**).
6. The application sends the access token to the **resource server** to access protected resources.

**Main Components:**

* **Resource Owner** – The user.
* **Client Application** – The app requesting access.
* **Authorization Server** – Authenticates the user and issues tokens.
* **Resource Server** – Stores protected resources.
* **Access Token** – Used to access resources.
* **Refresh Token** – Used to generate a new access token.

**Common OAuth 2.0 Grant Types:**

* **Authorization Code Grant** (most common and recommended).
* **Client Credentials Grant** (machine-to-machine communication).
* **Refresh Token Grant** (renew expired access tokens).

**When to Use:**

* **Social login** (Google, GitHub, Facebook).
* Securing **REST APIs**.
* **Microservices** authentication and authorization.
* Allowing third-party applications to access user data securely.

**Simple Spring Boot Example:**

**application.yml**

```yaml id="x9kl1a"
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
```

**Security Configuration**

```java id="sh8w2m"
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
            .oauth2Login();

        return http.build();
    }
}
```

This configuration enables users to **log in with Google** using the **OAuth 2.0 Authorization Code Flow**.

**OAuth 1.0** uses signature-based authentication and is complex, while **OAuth 2.0** is token-based, simpler, faster, and widely used in modern applications.


## 11:  How JWT(JSON Web Token) Authentication works in Spring Boot?

**JWT (JSON Web Token)** authentication is a **stateless authentication mechanism** where, after successful login, the server generates a **signed token** and sends it to the client. The client includes this token in every request, and Spring Boot validates it before granting access.

**Key Features:**

* **Stateless** – No session is stored on the server.
* Uses **signed tokens** for security.
* Easy to use with **REST APIs** and **microservices**.
* Supports **user roles and permissions** through token claims.
* Improves **scalability** because the server does not maintain session data.

**How It Works:**

1. The user sends **username** and **password** to the login API.
2. **Spring Security** authenticates the user.
3. If authentication is successful, the server generates a **JWT token** containing user details and roles.
4. The client stores the token (usually in local storage or a secure cookie).
5. For every API request, the client sends the token in the **Authorization** header:

   ```
   Authorization: Bearer <JWT_TOKEN>
   ```
6. A **JWT Filter** intercepts the request, validates the token, and extracts user information.
7. If the token is valid, Spring Security sets the authentication in the **SecurityContext**, and the request is allowed. Otherwise, access is denied.

**JWT Structure:**
A JWT consists of **three parts** separated by dots (`.`):

* **Header** – Contains token type and signing algorithm.
* **Payload** – Contains user information (**claims**).
* **Signature** – Verifies that the token has not been modified.

Example:

```text
xxxxx.yyyyy.zzzzz
```

**When to Use:**

* **REST APIs**
* **Microservices architecture**
* **Single Page Applications (SPA)** like React or Angular
* **Mobile applications**
* Systems requiring **stateless authentication**


**How JWT Works**

1. **Login**: Client sends credentials → Server validates → Returns JWT token
2. **Authorization**: Client includes JWT in request headers → Server validates token → Grants access


The backend server checks credentials using:
- Database (MySQL, PostgreSQL, etc.)
- or external provider (LDAP, OAuth, etc.)

**JWT Structure**

A JWT consists of three Base64-encoded parts separated by dots:
HEADER.PAYLOAD.SIGNATURE

**Sample JWT Token:**
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjE2MjM5MDIyLCJleHAiOjE2MTYzMjU0MjIsInJvbGVzIjpbIlVTRVIiLCJBRE1JTiJdfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
**Decoded Structure:**

- **Header**: Contains algorithm information (e.g., HS256)
  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```

- **Payload**: Contains claims (username, roles, expiration)
  ```json
  {
    "sub": "johndoe",
    "iat": 1616239022,
    "exp": 1616325422,
    "roles": ["USER", "ADMIN"]
  }
  ```

- **Signature**: Ensures token integrity and authenticity
  ```
  HMACSHA256(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    secret
  )
  ```

**Implementation Flow**

**1. User Authentication**
```Java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    
    String token = jwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(new AuthResponse(token));
}
```

**2. JWT Token Generation**

```Java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
}
```

**3. JWT Filter for Token Validation**

```Java
String authHeader = request.getHeader("Authorization");

if (authHeader != null && authHeader.startsWith("Bearer ")) {
    String token = authHeader.substring(7);

    String username = jwtUtil.extractUsername(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
```

**4. Security Configuration**
```Java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
```

## 12. JWT follow up common Questions?

**1. Why is JWT called Stateless?**

Because the **server does not store user sessions**. Every request contains the JWT, and the server validates it independently.

**2. Where is JWT stored?**

Usually in the **Authorization Header** (`Bearer Token`). It can also be stored in an **HttpOnly Cookie** for better security.

**3. What happens if the token expires?**

The server returns **401 Unauthorized**, and the client must authenticate again or use a **Refresh Token** to obtain a new JWT.

**4. What is the difference between Authentication and Authorization?**

* **Authentication** → Verifies **who the user is**.
* **Authorization** → Determines **what the user is allowed to access**.

**5. Why use `SessionCreationPolicy.STATELESS`?**

It tells Spring Security **not to create or use HTTP sessions**, making JWT-based authentication fully **stateless**.


**6. Where is JWT Stored in the Backend?**

**JWT is generally *not stored* in the backend.** This is what makes **JWT authentication stateless**.

**How it Works**

1. User logs in with **username** and **password**.
2. Server generates a **JWT** and sends it to the client.
3. The client stores the token (usually in the **Authorization Header** or an **HttpOnly Cookie**).
4. For every request, the client sends the JWT.
5. The backend **validates the token's signature and expiration** using the **secret key** (or **public/private key**) without looking it up in a database.

**7. What Does the Backend Store?**

Normally, the backend stores only:

* **Secret Key** (for HS256) or **Public/Private Keys** (for RS256)
* **User details** in the database
* **No JWT tokens** are stored

**8. When Can JWT Be Stored in the Backend?**

Although JWT is designed to be **stateless**, some applications store tokens for additional security:

* **Refresh Tokens** stored in the database
* **Token Blacklist** for logout or revoked tokens
* **Redis Cache** for token revocation or session tracking

**9. If JWT is not stored, how does the server verify it?**

The server verifies the **digital signature**, **expiration time**, and **claims** using the configured **secret key** or **public key**.

**10. Why is JWT called Stateless?**

Because the **server does not store session or access token information**. Every request contains all the information needed for authentication.

**11. JWT vs Session-based authentication?**

A: JWT is stateless (no server storage), while sessions require server-side storage. JWT is better for microservices and scalability.

**12. How do you handle token expiration?**

A: Implement refresh tokens or require re-authentication when tokens expire.

**13. Can JWT be revoked?**

A: JWT cannot be revoked by default. Implement token blacklisting or use short expiration times with refresh tokens.



## 13. Implement Roles Base Authentication(RBAC) (ADMIN, USER, MANAGER) in Spring Boot Using Spring Security?

In **Spring Boot**, **application roles** (such as **ADMIN**, **USER**, **MANAGER**) are typically stored in the **database**. When a user logs in, **Spring Security** loads the user's roles and checks them before allowing access to APIs.

**How it Works**

1. User logs in with **username** and **password**.
2. **Spring Security** authenticates the user.
3. It loads the user's **roles** from the **database**.
4. The roles are converted into **GrantedAuthority** objects.
5. Before accessing an API, Spring Security checks whether the user has the required role.
6. If the role matches, access is granted; otherwise, **403 Forbidden** is returned.

**Flow**

```text
User Login
     │
     ▼
Spring Security Authentication
     │
     ▼
Load User & Roles from Database
     │
     ▼
Convert Roles to GrantedAuthority
     │
     ▼
Check @PreAuthorize / hasRole()
     │
 ┌───┴────┐
 │        │
Allowed  Forbidden (403)
```

**Key Features**

* **Role-Based Access Control (RBAC)**.
* Roles stored in the **database**.
* Uses **Spring Security** for authorization.
* Supports **method-level** and **URL-level** security.
* Easy to add new roles without changing business logic.

**When to Use**

* Applications with multiple user types.
* **Admin Dashboard**.
* **HR**, **Banking**, **E-commerce**, **Healthcare** applications.
* Any application requiring different access levels.

**Database Tables**

**users**

| id | username | password          |
| -- | -------- | ----------------- |
| 1  | john     | encryptedPassword |

**roles**

| id | name       |
| -- | ---------- |
| 1  | ROLE_ADMIN |
| 2  | ROLE_USER  |

**user_roles**

| user_id | role_id |
| ------- | ------- |
| 1       | 1       |

**Example**

**Role Entity**

```java
@Entity
public class Role {

    @Id
    private Long id;

    private String name;   // ROLE_ADMIN, ROLE_USER
}
```

**User Entity**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
```

**UserDetailsService**

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = repository.findByUsername(username);

        List<GrantedAuthority> authorities =
                user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
```

**Secure API**

```java
@RestController
public class UserController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        return "Only ADMIN can access";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/reports")
    public String getReports() {
        return "ADMIN and MANAGER can access";
    }
}
```



**Common Interview Follow-up Questions**

**1. Where are roles stored?**

Usually in a **roles** table with a **user_roles** mapping table.

**2. Why use `ROLE_ADMIN` instead of `ADMIN`?**

Spring Security convention is to store roles with the **`ROLE_`** prefix. Then `hasRole("ADMIN")` automatically matches **`ROLE_ADMIN`**.

**3. What is `GrantedAuthority`?**

It is the Spring Security object that represents the **permissions or roles** assigned to an authenticated user.

**4. What is the difference between `hasRole()` and `hasAuthority()`?**

* **`hasRole("ADMIN")`** checks for **`ROLE_ADMIN`** automatically.
* **`hasAuthority("ROLE_ADMIN")`** checks the exact authority string.

**5. Can one user have multiple roles?**

**Yes.** A user can have multiple roles (for example, **ROLE_USER** and **ROLE_MANAGER**) using a **`@ManyToMany`** relationship.


## 14. Implement AWS IAM Rolebase Control Access to AWS Resources?

**AWS IAM (Identity and Access Management)** controls **who** can access **AWS resources** and **what actions** they can perform.

The recommended approach is to **attach an IAM Role** to your **EC2**, **ECS**, **EKS**, or **Lambda** instead of storing **Access Keys** in your application.

**How it Works**

1. Create an **IAM Policy** with the required permissions.
2. Create an **IAM Role**.
3. Attach the **Policy** to the **Role**.
4. Attach the **Role** to the **EC2**, **ECS**, **EKS**, or **Lambda**.
5. The **AWS SDK** automatically retrieves **temporary credentials** from the IAM Role.
6. Your application securely accesses **S3**, **SQS**, **DynamoDB**, or **Secrets Manager**.

**Key Features**

* **Role-Based Access Control (RBAC)**.
* Uses **IAM Policies** to define permissions.
* Provides **temporary credentials**.
* No hardcoded **Access Key** or **Secret Key**.
* Follows the **Least Privilege Principle**.

**When to Use**

* Access files in **S3**.
* Send or receive messages from **SQS**.
* Read or write data in **DynamoDB**.
* Retrieve secrets from **Secrets Manager**.
* Any application running on **EC2**, **ECS**, **EKS**, or **Lambda**.

**Example**

**Step 1: Create an IAM Policy**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:GetObject",
        "s3:PutObject"
      ],
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

**Step 2: Attach the Policy to an IAM Role**

```text
Role Name : SpringBootS3Role

Permissions:
- s3:GetObject
- s3:PutObject
```

**Step 3: Attach the IAM Role to the EC2 Instance**

```text
EC2
 └── IAM Role: SpringBootS3Role
```

**Step 4: Spring Boot Code**

```java
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

public class S3Service {

    private final S3Client s3Client = S3Client.create();

    public void uploadFile() {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("my-bucket")
                .key("report.pdf")
                .build();

        s3Client.putObject(request, Paths.get("report.pdf"));
    }
}
```

**How Authentication Happens**

* The application **does not store AWS credentials**.
* The **AWS SDK** checks for credentials.
* It automatically retrieves **temporary credentials** from the attached **IAM Role**.
* AWS verifies the **IAM Policy**.
* If allowed, access to the resource is granted.


**Common Interview Follow-up Questions**

**1. Why use an IAM Role instead of Access Keys?**

**IAM Roles** provide **temporary credentials**, improve security, eliminate hardcoded secrets, and support the **Least Privilege Principle**.

**2. Does the AWS SDK automatically use the IAM Role?**

**Yes.** When running on **EC2**, **ECS**, **EKS**, or **Lambda**, the **AWS SDK** automatically retrieves the temporary credentials from the attached **IAM Role**.

**3. Can one IAM Role access multiple AWS services?**

**Yes.** A single IAM Role can have multiple permissions, such as access to **S3**, **SQS**, **DynamoDB**, and **Secrets Manager**, based on its attached **IAM Policies**.

**4. What happens if the IAM Role does not have permission?**

AWS returns an **AccessDenied** error, and the requested operation is rejected.




## 15. Difference between JWT and Session?

Both are used to **authenticate users**, but they store and manage user authentication differently.

| **Feature**        | **JWT Authentication**                                                       | **Session Authentication**                                    |
| ------------------ | ---------------------------------------------------------------------------- | ------------------------------------------------------------- |
| **Storage**        | Token stored on **client** (Browser LocalStorage, SessionStorage, or Cookie) | Session data stored on **server**                             |
| **Server State**   | **Stateless**                                                                | **Stateful**                                                  |
| **Authentication** | Client sends **JWT token** with every request                                | Client sends **Session ID** with every request                |
| **Scalability**    | Better for **Microservices** and distributed systems                         | Better for **Traditional Web Applications**                   |
| **Performance**    | Faster because server doesn't store session                                  | Slightly slower because server checks session store           |
| **Logout**         | Harder (token remains valid until expiry unless blacklisted)                 | Easy (server simply destroys the session)                     |
| **Security**       | Secure if token is protected and sent over **HTTPS**                         | More secure by default because sensitive data stays on server |

**How JWT Authentication Works**

1. User logs in with **username/password**.
2. Server validates credentials.
3. Server generates a **JWT Token**.
4. Client stores the token.
5. Client sends the token in the **Authorization** header for every request.
6. Server validates the token and allows access.

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

**Spring Boot Example (JWT)**

```java
@GetMapping("/profile")
public String profile() {
    return "Authenticated User";
}
```

The request contains:

```http
Authorization: Bearer <JWT_TOKEN>
```

Spring Security validates the token before accessing the API.

**How Session Authentication Works**

1. User logs in.
2. Server creates a **Session** and stores user data.
3. Server returns a **Session ID** in a cookie.
4. Browser automatically sends the Session ID with every request.
5. Server looks up the session and authenticates the user.

**Spring Boot Example (Session)**

```java
@PostMapping("/login")
public String login(HttpSession session) {
    session.setAttribute("user", "John");
    return "Login Successful";
}
```

Later:

```java
String user = (String) session.getAttribute("user");
```

**When to Use**

**Use JWT when:**

* Building **REST APIs**
* Using **Microservices**
* Supporting **Mobile Applications**
* Need **Stateless Authentication**
* Multiple services need to validate the same token

**Use Session when:**

* Building **Traditional Web Applications**
* Need easy **Logout**
* Want authentication managed completely by the server
* Small or medium-sized applications

**Key Features**

**JWT**

* **Stateless**
* **Token-based**
* No server session storage
* Best for **REST APIs** and **Microservices**
* Better scalability

**Session**

* **Stateful**
* **Server stores session**
* Easy logout and session invalidation
* Best for **Server-rendered Web Applications**


**Session Authentication** is **stateful**, where the server stores user session data and the client sends a **Session ID**. The server checks the session on every request, making it ideal for **traditional web applications**.

**Common Interview Follow-up Questions**

**Q: Which is more scalable?**
**JWT**, because the server does not store session data.

**Q: Which provides easier logout?**
**Session Authentication**, because the server can immediately destroy the session.

**Q: Why is JWT called stateless?**
Because the server **does not store user session information**. Each request contains all the authentication information in the **JWT token**.

**Q: Can we use JWT and Session together?**
Yes. A common approach is to use **Session Authentication** for a web application's UI and **JWT Authentication** for REST APIs or communication between **Microservices**.



## 16. What is CSRF Protection?

**CSRF (Cross-Site Request Forgery) Protection** is a **security mechanism** that prevents attackers from tricking an authenticated user into performing unwanted actions on a web application without their consent.

**Example:** A user is logged into a banking website. If they visit a malicious website, it could secretly send a money transfer request using the user's active session. **CSRF protection blocks this attack.**

**Key Features:**

* Prevents **unauthorized requests** from malicious websites.
* Uses a unique **CSRF token** to validate requests.
* Protects **state-changing operations** like **POST**, **PUT**, **PATCH**, and **DELETE**.
* Enabled **by default** in **Spring Security** for session-based applications.

**How It Works:**

1. The server generates a unique **CSRF token** for the user's session.
2. The token is sent to the client (usually in a hidden form field or HTTP header).
3. The client includes the token in every state-changing request.
4. Spring Security compares the received token with the stored token.
5. If the tokens match, the request is allowed; otherwise, it is rejected.

**When to Use:**

* **Session-based authentication** using cookies.
* Traditional **Spring MVC** or server-rendered web applications.
* Any application where the browser automatically sends **session cookies**.

**When CSRF Protection is Usually Disabled:**

* **Stateless REST APIs** using **JWT** or **OAuth 2.0 Bearer Tokens**.
* APIs where authentication is sent explicitly in the **Authorization** header instead of cookies.

**Simple Spring Security Example:**


**Step 1: Add Spring Security dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**CSRF Enabled (Default):**

```java id="r4t9mk"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated())
        .formLogin();

    return http.build();
}
```

**Disable CSRF for JWT-Based REST API:**

```java id="v8n2qp"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated());

    return http.build();
}
```

## 17. What is XSS Protection?

**XSS (Cross-Site Scripting) Protection** is a **security mechanism** that prevents attackers from injecting and executing **malicious JavaScript code** in a web application. It protects users from attacks such as **cookie theft**, **session hijacking**, and **data manipulation**.

**Example:** If a comment field allows users to submit `<script>alert('Hacked')</script>`, an attacker could execute JavaScript in other users' browsers. **XSS protection blocks or sanitizes such input.**

**Key Features:**

* Prevents **malicious script injection**.
* Protects against **session hijacking** and **cookie theft**.
* Uses **input validation** and **output encoding**.
* Can be enhanced with **Content Security Policy (CSP)** headers.
* Important for any application that displays **user-generated content**.

**How It Works:**

1. The application receives input from the user.
2. Before storing or displaying it, the input is **validated** or **sanitized**.
3. When rendering the data in HTML, special characters are **encoded** (e.g., `<` becomes `&lt;`).
4. The browser displays the text as plain content instead of executing it as JavaScript.

**Common Types of XSS:**

* **Stored XSS** – Malicious script is stored in the database and executed when users view the page.
* **Reflected XSS** – Malicious script comes from the request and is immediately reflected in the response.
* **DOM-based XSS** – The vulnerability exists in client-side JavaScript code.

**When to Use:**

* Applications with **forms**, **comments**, or **search fields**.
* Websites displaying **user-generated content**.
* Any **web application** that accepts and renders user input.

**Simple Example:**

**Step 1: Add security headers via Spring Security**
```java
@Bean
// filterChain 
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .headers(headers -> headers
            .xssProtection(xss -> xss.enable())                        // X-XSS-Protection header
            .contentSecurityPolicy(csp ->
                csp.policyDirectives("script-src 'self'"))             // CSP header
        );
    return http.build();
}
```

**Step 2: Sanitize user input using OWASP Java HTML Sanitizer**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.googlecode.owasp-java-html-sanitizer</groupId>
    <artifactId>owasp-java-html-sanitizer</artifactId>
    <version>20220608.1</version>
</dependency>
```

```java
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@Service
public class SanitizationService {
    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public String sanitize(String input) {
        return POLICY.sanitize(input);   // strips <script> tags etc.
    }
}
```

**Step 3: Encode output in Thymeleaf (auto-escapes by default)**
```html
<!-- Thymeleaf escapes HTML by default — safe -->
<p th:text="${userComment}"></p>

<!-- th:utext is UNSAFE — renders raw HTML, avoid it -->
<p th:utext="${userComment}"></p>
```

**Step 4: Use Content Security Policy (CSP) header**
```java
.contentSecurityPolicy(csp ->
    csp.policyDirectives("default-src 'self'; script-src 'self'; object-src 'none'"))
```

Now, `<script>` is displayed as plain text instead of being executed.

**How to Prevent XSS:**

* **Validate and sanitize user input**.
* **Encode output** before rendering HTML.
* Use **Content Security Policy (CSP)**.
* Avoid directly inserting untrusted data into the **DOM**.
* Keep frameworks and libraries **updated**.


## 18. What is Input Validation?

**Input Validation** — ensuring data received from the user is correct, safe, and expected before processing it.

**Why it matters:**
- Prevents SQL Injection, XSS, buffer overflows
- Ensures business rules are enforced (e.g., age > 0)
- Fails fast before bad data reaches DB


**Step 1: Add validation dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

**Step 2: Annotate your DTO/model with constraints**
```java
public class UserRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be under 120")
    private int age;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    // getters + setters
}
```

**Step 3: Enable validation in controller with `@Valid`**
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request) {
        // if validation fails, MethodArgumentNotValidException is thrown automatically
        return ResponseEntity.ok("User created");
    }
}
```

**Step 4: Handle validation errors globally**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
```

**Step 5: Response when validation fails**
```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "age": "Age must be at least 18"
}
```


## 19: What is Filter Chain?

A **Filter Chain** is a sequence of **filters** that process an HTTP request and response **before** it reaches the controller and **after** the response is returned. In **Spring Security**, the filter chain is used to perform tasks like **authentication**, **authorization**, **JWT validation**, and **CSRF protection**.

**Key Features:**

* Processes requests in a **defined order**.
* Each filter performs a **specific task**.
* Can **modify**, **allow**, or **block** a request.
* Central part of **Spring Security**.
* Supports custom filters like **JWT authentication filters**.

**How It Works:**

1. A client sends an HTTP request.
2. The request passes through the **Filter Chain**.
3. Each filter checks or processes the request (e.g., validate JWT, check CSRF token).
4. If all filters pass, the request reaches the **Spring MVC Controller**.
5. The response travels back through the filter chain before being sent to the client.

**Typical Spring Security Filter Flow:**

```text id="j4v9qm"
Client Request
      │
      ▼
Security Filter Chain
      │
      ├── Authentication Filter
      ├── JWT Filter
      ├── CSRF Filter
      ├── Authorization Filter
      │
      ▼
Spring MVC Controller
      │
      ▼
Client Response
```

**When to Use:**

* **Authentication** and **authorization**.
* **JWT token validation**.
* **Logging** and **request tracking**.
* **CSRF**, **CORS**, and other security checks.
* Any requirement to process requests before they reach the controller.

**Simple Custom Filter Example:**

```java id="fc8p2x"
@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Request URI: " + request.getRequestURI());

        filterChain.doFilter(request, response); // Pass request to next filter
    }
}
```

**Adding a JWT Filter to the Security Filter Chain:**

```java id="k7m3zn"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated());

    return http.build();
}
```

## 20: What is SAML?

**SAML (Security Assertion Markup Language)** is an **XML-based authentication and authorization standard** that enables **Single Sign-On (SSO)**. It allows users to log in once and access multiple applications without entering their credentials again.

**Example:** A company employee logs into the corporate portal once and can then access applications like HR, email, and internal dashboards without separate logins.

**Key Features:**

* Provides **Single Sign-On (SSO)**.
* Uses **XML-based security assertions**.
* Eliminates the need to share passwords between applications.
* Supports **centralized authentication**.
* Widely used in **enterprise applications**.

**How It Works:**

1. The user tries to access an application (**Service Provider - SP**).
2. The application redirects the user to the **Identity Provider (IdP)**.
3. The user authenticates with the IdP.
4. The IdP generates a **SAML Assertion** (an XML document containing user identity and permissions).
5. The assertion is digitally signed and sent back to the Service Provider.
6. The Service Provider verifies the signature and grants access to the user.

**Main Components:**

* **Identity Provider (IdP)** – Authenticates the user (e.g., corporate login server).
* **Service Provider (SP)** – The application the user wants to access.
* **SAML Assertion** – XML document containing authentication and authorization information.

**When to Use:**

* **Enterprise Single Sign-On (SSO)**.
* Corporate applications with **centralized identity management**.
* Integration with identity providers like **Active Directory Federation Services (ADFS)** or **Okta**.
* Large organizations where users need access to multiple applications.

**Simple Spring Security SAML Configuration:**

```java id="saml9q"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated())
        .saml2Login();

    return http.build();
}
```

The `.saml2Login()` configuration enables **SAML 2.0-based Single Sign-On** in a Spring Boot application.

**SAML Authentication Flow:**

```text id="sml4r8"
User
  │
  ▼
Service Provider (Application)
  │
  ▼
Identity Provider (Login Server)
  │
  ▼
SAML Assertion (XML)
  │
  ▼
Service Provider Validates Assertion
  │
  ▼
Access Granted
```


# ✅ 25. Java Performance and Optimization

## 0: Monitor application performance in Java?

**Application monitoring** is the continuous tracking of an application's **performance, health, and behavior in production**.

It includes monitoring metrics like response time, errors, CPU, and memory, along with logs and traces, using tools like **Prometheus**, **Grafana**, **New Relic**, **Datadog**, and **AppDynamics** to detect and resolve issues proactively.

**Production Monitoring Tools (Simple Detailed Table)**

| Tool                 | Type                | Why We Use It                                 | What It Monitors                                      | Example                     |
| -------------------- | ------------------- | --------------------------------------------- | ----------------------------------------------------- | --------------------------- |
| Prometheus           | Metrics             | Collect metrics from application              | CPU, Memory, Request count, Error rate, Response time | API response time = 200ms   |
| Grafana              | Dashboard           | Show metrics in graphs                        | Dashboards, Alerts, Charts                            | CPU usage graph             |
| Micrometer           | Metrics Library     | Send metrics from Spring Boot to Prometheus   | JVM, Custom metrics                                   | Heap memory                 |
| ELK Stack            | Logging             | Store and search logs                         | Error logs, Application logs                          | Exception logs              |
| Splunk               | Logging / Analytics | Advanced log analysis & monitoring            | Logs, Events, Security data                           | Payment failure logs        |
| Spring Boot Admin    | Monitoring          | Monitor Spring Boot apps                      | Health, Beans, Endpoints                              | App status UP/DOWN          |
| Spring Boot Actuator | Monitoring          | Expose application health & metrics endpoints | Health, Metrics, Info, Thread dump                    | `/actuator/health`          |
| Zipkin               | Tracing             | Track request flow between services           | Service call flow                                     | Order → Payment → Inventory |
| Jaeger               | Tracing             | Track microservice request                    | API calls                                             | Request time per service    |
| Datadog              | APM                 | Full system monitoring                        | Infra, Logs, APIs                                     | Server CPU                  |
| New Relic            | APM                 | Application performance monitoring            | Slow API, DB calls                                    | Slow query                  |
| Dynatrace            | APM                 | AI-based full stack monitoring                | Full stack                                            | Root cause detection        |
| AWS CloudWatch       | Cloud               | Monitor AWS services                          | EC2, RDS, Logs                                        | EC2 CPU                     |



**What We Monitor in Production (Simple Table)**

| Area          | What We Monitor           | Example Alert       |
| ------------- | ------------------------- | ------------------- |
| Server        | CPU, Memory, Disk         | CPU > 80%           |
| Application   | Request count, Error rate | Error rate > 5%     |
| API           | Response time             | API > 3 sec         |
| Database      | Query time                | Query > 2 sec       |
| JVM           | Heap memory, GC           | Memory > 80%        |
| Logs          | Errors                    | Too many exceptions |
| Microservices | Service response          | Service down        |
| Business      | Orders, Payments          | Payment failure     |


## 1. What are performance issues and solutions?


* **OutOfMemoryError :** - This happens when the JVM heap memory is full and cannot allocate new objects.
* **Memory Leaks** – Objects stay in memory and are not removed by the Java Garbage Collector, increasing memory usage over time.
* **CPU Bottlenecks / Inefficient Algorithms** – Poor algorithms or unnecessary loops increase CPU usage and slow the application.
* **Database Issues** – Slow queries or poor connection pool management delay database responses.
* **Thread Contention** – Multiple threads compete for the same resource, causing delays and blocking.
* **Too Many Object Creations** – Creating many objects increases memory usage and garbage collection work.
* **Garbage Collection Overhead** – Frequent garbage collection pauses the application and affects performance.
* **Metaspace issues :** - In some applications (like servers), classes loaded by a ClassLoader are not released, causing Metaspace memory issues. Too many classes loaded
* **Improper Cache Management :** - If caching is implemented without limits, cached objects can keep growing and consume memory.
* **Blocking I/O Operations** – File, network, or API calls block threads and reduce application throughput.


```java
// Memory leak example
public class LeakExample {
    private static List<String> cache = new ArrayList<>();
    
    public void addToCache(String data) {
        cache.add(data); // Never cleared - memory leak
    }
}
```

**Steps to Improve Performance(optimize)**

Here are **key points with one-line explanations** for improving performance in a **Spring Boot application**:

1. **Optimize Database Queries** – Write efficient queries, use indexes, and avoid unnecessary joins to reduce database load.
2. **Use Caching** – Store frequently accessed data in cache (e.g., **Redis**) to reduce repeated database calls.
3. **Enable Connection Pooling** – Use connection pools like **HikariCP** to reuse database connections efficiently.
4. **Use Pagination** – Load data in smaller chunks instead of fetching large datasets at once.
5. **Enable Asynchronous Processing** – Use `@Async` to execute time-consuming tasks in background threads.
6. **Avoid N+1 Query Problem** – Use proper fetching strategies in **Hibernate** to prevent multiple unnecessary queries.
7. **Use DTOs Instead of Entities** – Transfer only required fields instead of full entity objects.
8. **Enable HTTP Compression** – Compress API responses to reduce network payload and improve response time.
9. **Reduce Logging in Production** – Use appropriate log levels to avoid performance overhead.
10. **Monitor Application Performance** – Use tools like **Spring Boot Actuator** to identify bottlenecks.
11. **Optimize Thread Pool Configuration** – Configure server thread pools to handle concurrent requests efficiently.
12. **Use Lazy Initialization** – Load objects only when needed to reduce memory usage and startup time.

**Tools Used:**

* VisualVM
* JConsole
* Eclipse Memory Analyzer

**I usually:**

1. Check heap usage.
2. Analyze GC logs.
3. Take heap dumps.
4. Find large object retainers and memory leaks.


## 2. What are Java Memory Leak Issues?

A **Memory Leak** in Java occurs when **objects are no longer needed but are still referenced**, so the **Garbage Collector (GC)** cannot remove them. Over time, unused objects accumulate, increasing memory usage and may eventually cause an **OutOfMemoryError**.

**Symptoms**

* Increasing heap memory usage
* Frequent Full GC
* Application slowdown
* High memory consumption
* `OutOfMemoryError`

**Key Features**

* Caused by **unused objects** that are still referenced.
* **Garbage Collector** cannot reclaim their memory.
* Increases **heap memory** usage over time.
* Can lead to **performance issues** and **OutOfMemoryError**.
* Common in **long-running applications**.

**How It Works**

1. An object is created in the **Heap**.
2. The application no longer needs the object.
3. A reference to the object still exists.
4. **Garbage Collector** considers the object reachable.
5. The object remains in memory, causing a **Memory Leak**.

**Example**

**Memory Leak**

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {
    private static final List<String> cache = new ArrayList<>();
    public static void main(String[] args) {
        while (true) {
            cache.add("Java"); // Objects are never removed
        }
    }
}
```

**Why it leaks?**

* The **static List** always holds references to the objects.
* Since the references exist, the **Garbage Collector** cannot free the memory.
* Eventually, the application throws **OutOfMemoryError**.

**Fixed Example**

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakFixed {
    public static void main(String[] args) {
        List<String> cache = new ArrayList<>();

        cache.add("Java");
        cache.add("Spring");

        cache.clear(); // Remove references
    }
}
```

**Common Causes of Memory Leaks**

1. **Static Collections** – Objects stored in **static** `List`, `Map`, or `Set` remain referenced for the lifetime of the application, preventing **Garbage Collection (GC)**.

2. **Improper Cache Management** – Caches grow indefinitely when **TTL (Time-to-Live)**, **size limits**, or **eviction policies** are not configured, leading to excessive memory usage.

3. **Unclosed Resources** – Database connections, file streams, sockets, or other resources are not properly closed, causing memory and resource leaks. Use **try-with-resources** whenever possible.

4. **ThreadLocal Misuse** – `ThreadLocal` values are not removed after use. In **thread pools**, the values remain attached to reused threads, causing memory leaks.

5. **Event Listener Leaks** – Event listeners, callbacks, or observers are registered but never deregistered, keeping objects referenced and preventing **Garbage Collection (GC)**.


**How to Prevent Memory Leaks**

* Remove unused objects from **collections**.
* Close resources using **try-with-resources**.
* Deregister **listeners** when no longer needed.
* Call **ThreadLocal.remove()** after use.
* Avoid unnecessary **static** references.
* Use **WeakReference** when appropriate.
* Monitor memory using profiling tools.

**Common Interview Follow-up Questions**

**1. Does Java have memory leaks even with Garbage Collection?**

**Yes.** **Garbage Collection** removes only **unreachable objects**. If an unused object is still referenced, it cannot be collected, resulting in a **Memory Leak**.

**2. What is the difference between Memory Leak and OutOfMemoryError?**

| **Memory Leak**                           | **OutOfMemoryError**                                              |
| ----------------------------------------- | ----------------------------------------------------------------- |
| Unused objects remain referenced          | JVM cannot allocate more memory                                   |
| Memory usage gradually increases          | Application fails due to insufficient memory                      |
| Can eventually cause **OutOfMemoryError** | Often the result of severe memory leaks or insufficient heap size |

**3. How do you detect Memory Leaks?**

Common tools include:

* **JVisualVM**
* **JConsole**
* **Eclipse Memory Analyzer (MAT)**
* **Java Flight Recorder (JFR)**
* **Java Mission Control (JMC)**

## 3. What are Latency isuue in java?

A **Latency Issue** is a **delay in processing or responding to a request**. It occurs when an application takes **longer than expected** to complete an operation, resulting in **slow response times**.

**Key Features**

* Increases **response time**.
* Reduces application **performance**.
* Can affect **user experience**.
* May occur due to **CPU**, **Memory**, **Database**, **Network**, or **External APIs**.
* Common in **high-traffic** applications.

**How It Works**

1. A client sends a request.
2. The application processes the request.
3. One or more operations become slow (Database, API, Disk I/O, GC, etc.).
4. The response is delayed, increasing **latency**.

**Common Causes**

* **Slow Database Queries**
* **Long Garbage Collection (GC) pauses**
* **Blocking I/O** operations
* **Slow External API** calls
* **Network latency**
* **Thread contention** or **deadlocks**
* **Insufficient Thread Pool** size
* **Large file processing**
* **CPU** or **Memory** bottlenecks

**Example**

**Latency Due to Synchronous API Calls**

```java
public String getUserDetails() {
    User user = userService.getUser();
    // Slow external API call
    Address address = addressService.getAddress();

    return user.getName() + " - " + address.getCity();
}
```

If `getAddress()` takes **5 seconds**, the entire request waits **5 seconds** before responding.

**Better Approach (Asynchronous Processing)**

```java
CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> userService.getUser());

CompletableFuture<Address> addressFuture = CompletableFuture.supplyAsync(() -> addressService.getAddress());

CompletableFuture.allOf(userFuture, addressFuture).join();

System.out.println(userFuture.join().getName() + " - " +
                   addressFuture.join().getCity());
```

Both API calls execute **in parallel**, reducing overall response time.

**How to Reduce Latency**

* Optimize **SQL queries** and add **Indexes**.
* Use **Caching** (Redis, Caffeine, etc.).
* Execute independent tasks using **CompletableFuture**.
* Use **Connection Pooling** for databases.
* Tune the **JVM** and **Garbage Collector**.
* Increase or tune the **Thread Pool** size.
* Avoid unnecessary **blocking** operations.
* Compress large responses and use **pagination**.
* Monitor application performance using **APM tools**.

**Common Interview Follow-up Questions**

**1. What is the difference between Latency and Throughput?**

| **Latency**                            | **Throughput**                                                         |
| -------------------------------------- | ---------------------------------------------------------------------- |
| Time taken to complete **one request** | Number of requests processed **per second**                            |
| Measured in **milliseconds (ms)**      | Measured in **Requests/Second (RPS)** or **Transactions/Second (TPS)** |
| Lower is better                        | Higher is better                                                       |

**2. How do you identify latency issues?**

Use monitoring and profiling tools such as:

* **JVisualVM**
* **Java Flight Recorder (JFR)**
* **Java Mission Control (JMC)**
* **Prometheus + Grafana**
* **New Relic**, **Dynatrace**, or **AppDynamics**

Check:

* **GC logs**
* **Thread dumps**
* **Heap dumps**
* **Slow SQL queries**
* **API response times**
* **CPU** and **Memory** usage

**3. How does Caching reduce latency?**

**Caching** stores frequently accessed data in memory, reducing repeated **database** or **API** calls and providing much faster responses.


## 4: What is database optimization?

**Database optimization** is the process of improving database performance and query speed.

It involves **proper indexing, writing efficient SQL queries, using connection pooling, caching, and good database design** to reduce load and improve response time.


```java
// Database optimization techniques
@Repository
public class OptimizedUserRepository {
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);
    
    // Batch operations
    @Modifying
    @Query("UPDATE User u SET u.lastLogin = :now WHERE u.id IN :ids")
    void updateLastLogin(@Param("ids") List<Long> ids, @Param("now") LocalDateTime now);
    
    // Pagination for large datasets
    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    Page<User> findAllUsers(Pageable pageable);
    
    // Fetch joins to avoid N+1 queries
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.orders WHERE u.id = :id")
    User findUserWithOrders(@Param("id") Long id);
}
```


## 5: What is query optimization?

**Query optimization** is the process of improving SQL query performance so that data is retrieved faster and database resources are used efficiently.

Common techniques include creating proper indexes, writing efficient JOIN and WHERE clauses, avoiding SELECT *, fetching only required data, using pagination for large datasets, and analyzing execution plans.

For example, if users are frequently searched by email, adding an index on the email column can significantly reduce query execution time.

```java
// Query optimization examples
@Repository
public class OptimizedQueryRepository {
    
    // Bad: N+1 query problem
    // List<Order> orders = orderRepository.findAll();
    // orders.forEach(order -> order.getCustomer().getName()); // N queries
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);

    // Good: Single query with join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer")
    List<Order> findAllOrdersWithCustomers();
    
    // Use specific columns instead of SELECT *
    @Query("SELECT new com.example.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDto> findUserSummaries();
    
    // Optimize with proper WHERE conditions
    @Query("SELECT u FROM User u WHERE u.active = true AND u.createdAt > :date")
    List<User> findActiveUsersAfter(@Param("date") LocalDateTime date);
    
    // Use native query for complex optimizations
    @Query(value = "SELECT * FROM users u WHERE u.score > (SELECT AVG(score) FROM users)", 
           nativeQuery = true)
    List<User> findAboveAverageUsers();
}
```


## 6: What is pagination?

**Pagination** is a technique used to split large datasets into **smaller chunks (pages)** instead of loading all data at once.

It improves **performance, memory usage, and user experience**, and is usually implemented using **LIMIT/OFFSET or cursor-based pagination**.

```java
// Pagination implementation
@RestController
public class UserController {
    
    // Basic pagination
    @GetMapping("/users")
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.findAll(pageable);
    }
    
    // Cursor-based pagination for better performance
    @GetMapping("/users/cursor")
    public List<User> getUsersCursor(
        @RequestParam(required = false) Long lastId,
        @RequestParam(defaultValue = "20") int limit) {
        
        return userService.findUsersAfter(lastId, limit);
    }
}

@Repository
public class UserRepository extends JpaRepository<User, Long> {
    
    // Cursor pagination query
    @Query("SELECT u FROM User u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id")
    List<User> findUsersAfter(@Param("lastId") Long lastId, Pageable pageable);
}
```


## 7. What are common 10 Production Issues?

**1. 🔴 Memory Leaks**
**Symptom:** Heap memory keeps growing over time, leads to frequent Full GC and `OutOfMemoryError`

**Causes:**
- Unreleased object references
- Static collections holding objects
- `ThreadLocal` leaks


**2. 🗄️ Connection Pool Exhaustion**
**Symptom:** All DB connections are used up; requests start waiting and eventually fail

**Causes:**
- Too many concurrent DB requests
- Connections not released properly
- Pool size too small for the load


**3. ⏱️ High GC Pause Time**
**Symptom:** Application becomes slow or unresponsive

**Causes:**
- JVM spends too much time in Garbage Collection
- Large heap with too many short-lived objects
- Wrong GC algorithm for the workload


**4. 🔒 Deadlocks**
**Symptom:** No progress in the system; threads hang forever

**Causes:**
- Two or more threads waiting for each other's locks
- Inconsistent lock acquisition order
- `T1` holds lock A waiting for B, `T2` holds lock B waiting for A


**5. 🧵 Thread Pool Starvation**
**Symptom:** New tasks keep waiting in the queue indefinitely

**Causes:**
- All worker threads are busy or blocked
- Pool size too small
- Blocking I/O inside thread pool tasks


**6. 🐢 Slow SQL Queries**
**Symptom:** Increased response time, locked tables, degraded throughput

**Causes:**
- Unoptimized or inefficient queries
- Missing indexes
- Full table scans on large datasets


**7. 📨 Kafka Consumer Lag**
**Symptom:** Consumers can't keep up with incoming messages; data delays increase

**Causes:**
- Consumers too slow to process messages
- Insufficient consumer instances
- Heavy processing logic inside consumers


**8. 📈 CPU Spikes**
**Symptom:** Overall system performance degrades suddenly

**Causes:**
- Infinite loops in code
- Heavy or excessive logging
- Bad algorithms with high time complexity
- GC thrashing


**9. 🌊 Cascading Failures**
**Symptom:** System instability spreads across multiple services

**Causes:**
- One failing service impacts multiple downstream services
- No circuit breakers in place
- Retry storms amplifying the failure


**10. 🔕 Missing Monitoring & Alerts**
**Symptom:** Issues exist but nobody notices early; small problems become major outages

**Causes:**
- No alerting configured for key metrics
- Lack of observability (logs, metrics, traces)
- No dashboards tracking system health


## 8. What are Java concurrency issues?

Common **Java concurrency issues** occur when multiple threads work on shared resources without proper coordination. This can cause incorrect results, slow performance, or application crashes.

1. **race condition :** -  happens when multiple threads access and modify shared data at the same time, and the final result depends on the order of execution.
2. **Deadlock :** -   occurs when two or more threads are waiting for each other’s resources, and none of them can proceed.
3. **Thread Starvation :** -  happens when a thread does not get enough CPU time because other threads with higher priority keep running.
4. **Livelock :** -  threads keep responding to each other and changing states, but no thread makes progress.
5. **Thread Contention :** -  This happens when multiple threads try to access the same resource simultaneously, causing threads to wait and reducing performance.
6. **Visibility Issues :** -  Changes made by one thread may **not be visible to other threads** due to CPU caching. Solution often involves using `volatile` or synchronization.
7. **Improper Synchronization**
Using too many or incorrect `synchronized` blocks can lead to **performance issues or inconsistent data**.


```java
// Race condition fix
private volatile boolean flag = false;
private final Object lock = new Object();

public void safeMethod() {
    synchronized(lock) {
        // Thread-safe operation
        flag = !flag;
    }
}
```


## 9: What is JVM tuning and parameters for performance tuning?

**JVM Tuning** is the process of **configuring JVM parameters** to improve **application performance**, **reduce Garbage Collection (GC) pauses**, **optimize memory usage**, and **increase throughput**.

**Key Features**

* **Optimizes Heap Memory** allocation.
* **Reduces GC pause time**.
* **Improves application response time**.
* **Prevents OutOfMemoryError**.
* **Increases CPU and memory efficiency**.

**How It Works**

1. JVM starts with **default settings**.
2. We configure **JVM startup parameters** based on application requirements.
3. JVM uses these settings for **memory allocation**, **GC**, and **thread management**.
4. Proper tuning results in **better performance** and **stable memory usage**.

**Common JVM Performance Tuning Parameters**

| **Parameter**                       | **Purpose**                  | **Example**                       |
| ----------------------------------- | ---------------------------- | --------------------------------- |
| **-Xms**                            | Initial Heap Size            | `-Xms512m`                        |
| **-Xmx**                            | Maximum Heap Size            | `-Xmx2g`                          |
| **-Xss**                            | Thread Stack Size            | `-Xss1m`                          |
| **-XX:+UseG1GC**                    | Enable G1 Garbage Collector  | `-XX:+UseG1GC`                    |
| **-XX:MaxGCPauseMillis**            | Target Maximum GC Pause Time | `-XX:MaxGCPauseMillis=200`        |
| **-XX:ParallelGCThreads**           | Number of GC Threads         | `-XX:ParallelGCThreads=8`         |
| **-XX:+HeapDumpOnOutOfMemoryError** | Generate Heap Dump on OOM    | `-XX:+HeapDumpOnOutOfMemoryError` |
| **-XX:HeapDumpPath**                | Heap Dump Location           | `-XX:HeapDumpPath=/logs`          |
| **-XX:+PrintGCDetails** *(Java 8)*  | Print GC Logs                | `-XX:+PrintGCDetails`             |
| **-Xlog:gc** *(Java 9+)*            | Enable GC Logging            | `-Xlog:gc`                        |

**Most Common JVM Startup Example**

```bash
java -Xms512m -Xmx2g -Xss1m \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -jar application.jar
```

**Explanation**

* **-Xms512m** → JVM starts with **512 MB Heap**.
* **-Xmx2g** → Maximum Heap is **2 GB**.
* **-Xss1m** → Each thread gets **1 MB Stack**.
* **UseG1GC** → Uses the **G1 Garbage Collector**.
* **MaxGCPauseMillis=200** → Tries to keep GC pauses around **200 ms**.

**When to Use JVM Tuning**

* **Spring Boot** applications.
* **Microservices**.
* **High-traffic web applications**.
* **Large enterprise applications**.
* Applications with **high memory usage** or **frequent GC**.
* Systems requiring **low response time**.

**Common Performance Tuning Areas**

**1. Heap Memory Tuning**

* Configure **-Xms** and **-Xmx** properly.
* Keep **Xms = Xmx** in production to avoid heap resizing.

**2. Garbage Collector Tuning**

* Choose the right **GC algorithm**.
* **G1GC** is the default and recommended for most applications.
* Tune **MaxGCPauseMillis** if low latency is important.

**3. Thread Tuning**

* Configure **-Xss** based on thread count.
* Too many threads with large stacks can consume excessive memory.

**4. GC Logging**

Enable GC logs to analyze memory behavior.

**Java 17 Example**

```bash
-Xlog:gc
```

**Code Example (Setting JVM Options in Spring Boot)**

```bash
java -Xms1g -Xmx2g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -jar springboot-app.jar
```

**Popular Garbage Collectors**

| **Garbage Collector** | **Best For**                     |
| --------------------- | -------------------------------- |
| **Serial GC**         | Small applications               |
| **Parallel GC**       | High throughput                  |
| **G1 GC**             | Most enterprise applications     |
| **ZGC**               | Very low latency and large heaps |
| **Shenandoah GC**     | Low pause-time applications      |

**Best Practices**

* Set **-Xms** and **-Xmx** appropriately.
* Use **G1GC** for most production workloads.
* Enable **GC logging** for analysis.
* Monitor **Heap**, **GC**, and **CPU** usage regularly.
* Avoid allocating unnecessary objects.
* Analyze **Heap Dumps** if memory usage keeps increasing.


**Common Interview Follow-up Questions**

**1. What is the difference between `-Xms` and `-Xmx`?**

* **-Xms** → **Initial Heap Size**.
* **-Xmx** → **Maximum Heap Size**.

**2. Why is G1GC commonly used?**

Because it provides **predictable GC pause times**, handles **large heaps efficiently**, and offers a good balance between **throughput** and **low latency**.

**3. Why keep `-Xms` and `-Xmx` the same in production?**

It avoids **heap resizing**, reducing GC overhead and improving application stability.

**4. How do you identify JVM performance issues?**

By monitoring:

* **Heap usage**
* **GC logs**
* **CPU utilization**
* **Thread dumps**
* **Heap dumps**

using tools like **VisualVM**, **Java Flight Recorder (JFR)**, **Java Mission Control (JMC)**, and **Eclipse Memory Analyzer (MAT)**.

**5. Does increasing `-Xmx` always improve performance?**

**No.** A larger heap can reduce GC frequency but may increase **GC pause times**. The heap size should be tuned based on the application's memory usage and workload.



## 10. What is Distributed Tracing?

**Distributed Tracing** is a **monitoring technique** used in **microservices architecture** to track a request as it travels across multiple services. It helps developers understand the complete path of a request and quickly identify **performance bottlenecks** or **failures**.

**Example:** A request to place an order may go through **API Gateway → Order Service → Payment Service → Inventory Service → Notification Service**. Distributed tracing shows the complete flow of that request.

**Key Features:**

* Tracks a request across **multiple microservices**.
* Uses a unique **Trace ID** and **Span ID**.
* Helps identify **slow services** and **errors**.
* Improves **debugging** and **performance monitoring**.
* Integrates with tools like **Zipkin**, **Jaeger**, and **OpenTelemetry**.

**How It Works:**

1. A client sends a request to the application.
2. The first service generates a unique **Trace ID**.
3. As the request moves between services, the same Trace ID is propagated.
4. Each service creates a **Span** representing its individual operation.
5. All spans are collected and sent to a tracing system (e.g., Zipkin or Jaeger).
6. Developers can view the complete request flow and timing information.

**Important Terms:**

* **Trace ID** – A unique identifier for the entire request.
* **Span** – A single operation or unit of work within the trace.
* **Span ID** – A unique identifier for an individual span.

**When to Use:**

* **Microservices architecture**.
* Debugging **service-to-service communication**.
* Finding **latency issues** and **bottlenecks**.
* Monitoring **distributed systems** and **cloud-native applications**.


**Spring Boot Example with Micrometer Tracing**

**Maven Dependency**

```xml id="jzptg7"
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

**application.yml**

```yaml id="dt1k8p"
management:
  tracing:
    sampling:
      probability: 1.0
```

**Service Class**

```java id="d36xf8"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    public void createOrder() {
        logger.info("Creating order");
        // Business Logic
        logger.info("Order created successfully");
    }
}
```

**application.properties**

```properties id="6fzpxt"
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans
```

**Sample Log Output**

```text
2026-06-09 10:00:00
[traceId=abc123 spanId=xyz789]
Creating order
```

The **Trace ID** and **Span ID** automatically appear in logs, making it easy to track requests across services.

With **Spring Boot 3+** and **Micrometer Tracing/OpenTelemetry**, each request automatically gets a **Trace ID** that is propagated across services.

**Distributed Tracing Flow:**

```text id="dtr8w2"
Client Request
      │
      ▼
API Gateway
      │
      ▼
Order Service
      │
      ▼
Payment Service
      │
      ▼
Inventory Service
      │
      ▼
Notification Service
      │
      ▼
Response to Client
      │
      ▼
All Services Share the Same Trace ID
```


**Common Tools**

* **OpenTelemetry**
* **Zipkin**
* **Jaeger**
* **Micrometer Tracing**
* **Spring Boot Actuator**


## 11. What is Zipkin and how it Works?

**Zipkin** is a **distributed tracing tool** used in **microservices architecture** to track and monitor requests as they travel across multiple services.

It helps developers identify **latency issues**, **performance bottlenecks**, and **service failures**.

**Key Features**

* **Distributed Tracing**
* Tracks requests across multiple microservices
* Shows complete request flow
* Measures response time of each service
* Helps in debugging and performance monitoring
* Provides a visual trace through a web UI

**How It Works**

1. A request enters a microservice.
2. A unique **Trace ID** is generated.
3. Each service creates a **Span** (a unit of work).
4. Trace and span information are propagated to downstream services.
5. Services send tracing data to **Zipkin Server**.
6. Zipkin collects and displays the complete request journey.

**Example Flow**

```text
Client Request
      │
      ▼
Service A ──► Service B ──► Service C
  Span 1        Span 2        Span 3

      Same Trace ID: abc123
```

All services share the same **Trace ID**, while each service has its own **Span ID**.

**Important Terms**

* **Trace**: Complete journey of a request.
* **Span**: Single operation within a trace.
* **Trace ID**: Unique identifier for the entire request.
* **Span ID**: Unique identifier for a specific operation.

**When to Use**

* Microservices applications
* Debugging service-to-service communication
* Finding slow APIs
* Monitoring distributed systems
* Performance analysis and troubleshooting

**Spring Boot Example**

**Dependency**

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

**application.properties**

```properties
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans
```

After starting the application, trace data is automatically sent to Zipkin.



## 12: What is profiling in Java?

**Profiling in Java** is the process of **analyzing the runtime behavior** of a Java application to identify **performance bottlenecks**, **high CPU usage**, **memory leaks**, and **slow methods**. It helps developers optimize application performance.

**Key Features:**

* Monitors **CPU usage**.
* Tracks **memory allocation** and **garbage collection (GC)**.
* Identifies **slow or frequently called methods**.
* Detects **memory leaks** and **thread issues**.
* Helps improve **application performance** and **resource utilization**.

**How It Works:**

1. A **Java Profiler** is attached to the running application.
2. The profiler collects runtime metrics such as CPU time, memory usage, thread activity, and method execution.
3. The collected data is analyzed to find bottlenecks.
4. Developers optimize the code based on the profiling results.

**Common Metrics Collected:**

* **CPU Usage** – Which methods consume the most CPU time.
* **Memory Usage** – How much memory objects occupy.
* **Heap Analysis** – Detects unnecessary object retention.
* **Thread Activity** – Finds blocked or deadlocked threads.
* **Garbage Collection (GC)** – Measures GC frequency and pause times.

**Popular Java Profiling Tools:**

* **JVisualVM**
* **JConsole**
* **Java Flight Recorder (JFR)**
* **YourKit Java Profiler**
* **JProfiler**

**When to Use:**

* Application is running **slowly**.
* Investigating **memory leaks**.
* High **CPU** or **heap memory** usage.
* Performance tuning before **production deployment**.
* Analyzing **thread contention** or **deadlocks**.

**Simple Example:**

```java id="jp7x2m"
public class Demo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }

        long end = System.currentTimeMillis();
        System.out.println("Execution Time: " + (end - start) + " ms");
    }
}
```

A **Java Profiler** can analyze this program and show how much **CPU time** is spent inside the loop and whether there are any performance issues.


## 13: What is memory profiling?

**Memory Profiling** is the process of **analyzing how a Java application uses memory** during execution. It helps identify **memory leaks**, **excessive object creation**, and **high heap usage** to improve application performance and stability.

**Key Features:**

* Monitors **heap memory usage**.
* Tracks **object creation and allocation**.
* Detects **memory leaks**.
* Analyzes **Garbage Collection (GC)** behavior.
* Identifies objects that occupy the most memory.

**How It Works:**

1. A **memory profiler** is attached to the running Java application.
2. It collects information about **heap usage**, **object allocation**, and **GC activity**.
3. The profiler shows which objects are consuming memory and whether they are being released correctly.
4. Developers analyze the data to optimize memory usage and fix leaks.

**Common Metrics Monitored:**

* **Heap Memory Usage**
* **Object Allocation Rate**
* **Live Objects Count**
* **Garbage Collection Frequency**
* **Memory Leak Detection**

**Popular Memory Profiling Tools:**

* **JVisualVM**
* **Java Flight Recorder (JFR)**
* **JProfiler**
* **YourKit Java Profiler**
* **Eclipse Memory Analyzer (MAT)**

**When to Use:**

* Application is consuming **too much memory**.
* Investigating **OutOfMemoryError**.
* Finding **memory leaks**.
* Optimizing **heap usage** and **GC performance**.
* Performance tuning before **production deployment**.

**Simple Example:**

```java id="mp6r2k"
import java.util.ArrayList;
import java.util.List;

public class MemoryDemo {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new byte[1024 * 1024]); // Allocate 1 MB
        }

        System.out.println("Objects created: " + list.size());
    }
}
```

A **memory profiler** can analyze this program and show how the `byte[]` objects are allocated in the **heap** and whether they are properly released by the **Garbage Collector**.


## 14: What is CPU profiling?

**CPU Profiling** is the process of **analyzing how a Java application uses CPU resources** during execution. It helps identify **slow methods**, **performance bottlenecks**, and **high CPU-consuming code** so that the application can be optimized.

**Key Features:**

* Monitors **CPU usage** of the application.
* Identifies **slow or frequently executed methods**.
* Detects **performance bottlenecks**.
* Tracks **method execution time** and **call frequency**.
* Helps improve **application speed** and **efficiency**.

**How It Works:**

1. A **CPU profiler** is attached to the running Java application.
2. The profiler records **method calls**, **execution time**, and **CPU consumption**.
3. It generates a report showing which methods consume the most CPU time.
4. Developers analyze the report and optimize the expensive code paths.

**Common Metrics Monitored:**

* **CPU Usage Percentage**
* **Method Execution Time**
* **Method Call Count**
* **Hotspots** (methods using the most CPU)
* **Thread CPU Utilization**

**Popular CPU Profiling Tools:**

* **JVisualVM**
* **Java Flight Recorder (JFR)**
* **JProfiler**
* **YourKit Java Profiler**
* **Async Profiler**

**When to Use:**

* Application is running **slowly**.
* Investigating **high CPU utilization**.
* Finding **performance bottlenecks**.
* Optimizing **algorithms** and **business logic**.
* Performance tuning before **production deployment**.

**Simple Example:**

```java id="cpu4x8"
public class CpuDemo {
    public static void main(String[] args) {
        long sum = 0;

        for (int i = 0; i < 10000000; i++) {
            sum += Math.sqrt(i);
        }

        System.out.println("Result: " + sum);
    }
}
```

A **CPU profiler** can analyze this program and show that the loop and the `Math.sqrt()` method consume most of the CPU time, helping developers optimize the code.



## 16: How do you find Security Vulnerabilities in Production? Which tools do you use?


In **Production**, we identify **Security Vulnerabilities** using **security monitoring**, **dependency scanning**, **code analysis**, **application security testing**, and **log analysis**. These tools help detect vulnerabilities early so they can be fixed before they are exploited.

**Key Features**

* Monitor **Security Logs** and **Alerts**.
* Scan **Dependencies** for known **CVEs**.
* Perform **Static (SAST)** and **Dynamic (DAST)** security testing.
* Monitor **Authentication** failures and suspicious activities.
* Continuously scan **Containers** and **Cloud Infrastructure**.
* Integrate security scans into the **CI/CD Pipeline**.

**How it works**

1. **Monitor logs** for failed logins, unauthorized access, and suspicious requests.
2. **Scan dependencies** to identify vulnerable libraries.
3. Run **SAST** tools to detect security issues in source code.
4. Run **DAST** tools to test the running application.
5. Monitor **SIEM dashboards** for security alerts.
6. Patch vulnerable libraries or fix the code.
7. Test the fix and deploy it to production.

**Common Tools Used in Production**

| **Category**                        | **Tools**                                                                 | **Purpose**                                      |
| ----------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------ |
| **Dependency Scanning**             | **OWASP Dependency-Check**, **Snyk**, **Mend (WhiteSource)**              | Detect vulnerable libraries and **CVEs**         |
| **Static Code Analysis (SAST)**     | **SonarQube**, **Checkmarx**, **Fortify**                                 | Find security issues in source code              |
| **Dynamic Security Testing (DAST)** | **OWASP ZAP**, **Burp Suite**                                             | Find vulnerabilities in a running application    |
| **Container Scanning**              | **Trivy**, **Clair**                                                      | Scan Docker images for vulnerabilities           |
| **Cloud Security**                  | **AWS Inspector**, **AWS Security Hub**, **Microsoft Defender for Cloud** | Detect cloud security issues                     |
| **Monitoring / SIEM**               | **Splunk**, **ELK Stack**, **IBM QRadar**                                 | Detect suspicious activities and security alerts |

**Example: Scan Dependencies**

**Maven Plugin**

```xml
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>12.1.8</version>
</plugin>
```

**Run Scan**

```bash
mvn dependency-check:check
```

The scan generates a report showing **vulnerable dependencies**, **CVE IDs**, and recommended fixes.

**Real-Time Example**

**Issue:** A new **Log4j** vulnerability (**CVE**) is announced.

**Steps:**

1. Run **OWASP Dependency-Check** or **Snyk**.
2. Verify whether the application uses the vulnerable **Log4j** version.
3. Check **Splunk/ELK** for any exploit attempts.
4. Upgrade to the patched version.
5. Test the application.
6. Deploy the fix and continue monitoring.

**When to use**

* Before every **Production Release**.
* During the **CI/CD Pipeline**.
* After a new **CVE** is published.
* During **Security Audits**.
* When suspicious activity is detected in production.



**Common Interview Follow-up Questions**

**1. Which tools have you used for security scanning?**

* **OWASP Dependency-Check**
* **Snyk**
* **SonarQube**
* **OWASP ZAP**
* **Burp Suite**
* **Splunk**
* **ELK Stack**

**2. What is a CVE?**

A **CVE (Common Vulnerabilities and Exposures)** is a publicly disclosed security vulnerability with a unique identifier.

**3. What is the difference between SAST and DAST?**

* **SAST** scans the **source code** without running the application.
* **DAST** tests the **running application** by simulating real attacks.

**4. Which tool is most commonly used in Spring Boot projects?**

**SonarQube** for code quality and security, **OWASP Dependency-Check** or **Snyk** for dependency scanning, and **OWASP ZAP** for API security testing.

**5. What do you do if a vulnerability is found in production?**

Analyze the impact, identify the affected component, apply the security patch or upgrade the vulnerable dependency, validate the fix in QA, deploy it to production, and continue monitoring to ensure the issue is resolved.



## 17. What is JIT compilation?


**JIT (Just-In-Time) Compilation** is a feature of the **JVM** that **converts bytecode into native machine code at runtime** to make Java programs faster.

Java normally works like this:

```
.java → compiled → .class (bytecode) → JVM → Machine Code → Run
```

**How JIT Works**

1. Java code compiled → Bytecode
2. JVM runs bytecode
3. JIT finds frequently used code (hotspot)
4. JIT converts it to machine code
5. Execution becomes faster


**JIT vs Interpreter**

| Interpreter            | JIT                      |
| ---------------------- | ------------------------ |
| Line by line execution | Compiles to machine code |
| Slower                 | Faster                   |
| Starts fast            | Gets faster over time    |

```java
// JIT compilation example
public class JITExample {
    public static void main(String[] args) {
        // This loop will trigger JIT compilation
        for (int i = 0; i < 100000; i++) {
            calculateSum(i); // Method becomes "hot" and gets compiled
        }
    }
    
    private static int calculateSum(int n) {
        return n * (n + 1) / 2; // Simple calculation
    }
}

// JIT compilation flags
java -XX:+PrintCompilation \      # Print compilation events
     -XX:CompileThreshold=1000 \  # Compilation threshold
     JITExample
```


# ✅ 26. Java Monitoring and Logging

## 1: What is logging framework?

A **Logging Framework** is a library used to **record application events, errors, and execution details** into log files or monitoring systems. It helps developers **debug, monitor, and troubleshoot** applications.

**Key Features:**

* Records **application events** and **errors**.
* Supports different **log levels** such as **TRACE, DEBUG, INFO, WARN, and ERROR**.
* Can write logs to **console, files, or external systems**.
* Supports **log formatting** and **log rotation**.
* Integrates with monitoring tools like **ELK Stack** and **Splunk**.

**How it Works:**

1. The application generates log messages.
2. The **Logging Framework** captures the messages.
3. Based on the configured **log level**, it decides whether to record the message.
4. The logs are written to the configured destination (console, file, or centralized logging system).
5. Developers or monitoring tools analyze the logs for debugging and monitoring.

**Common Logging Frameworks in Java:**

* **SLF4J** – Logging API (facade).
* **Logback** – Default logging implementation in Spring Boot.
* **Log4j2** – High-performance logging framework.
* **java.util.logging (JUL)** – Built-in Java logging framework.

**When to Use:**

* To **debug application issues**.
* To monitor **application health and performance**.
* To track **errors, warnings, and user activities**.
* In **microservices** and **distributed systems** for centralized logging.

**Simple Spring Boot Logging Example:**

```java id="v3k8wp"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger logger =
        LoggerFactory.getLogger(UserService.class);

    public void createUser() {
        logger.info("User created successfully");
        logger.error("Error while creating user");
    }
}
```


## 2: What is Log4j?

**Log4j** is an **open-source Java Logging Framework** developed by the **Apache Software Foundation**. It is used to **generate, manage, and store application logs** for debugging, monitoring, and troubleshooting purposes.

**Key Features:**

* Supports different **Log Levels**: **TRACE, DEBUG, INFO, WARN, ERROR, FATAL**.
* Can write logs to **Console, File, Database, or Remote Servers**.
* Supports **Asynchronous Logging** for better performance.
* Provides flexible **log formatting** and **log rotation**.
* Integrates with **SLF4J** and many Java frameworks.

**How it Works:**

1. The application generates a log message.
2. **Log4j Logger** receives the message.
3. Based on the configured **log level**, it decides whether to log the message.
4. The message is passed to an **Appender** (Console, File, etc.).
5. A **Layout** formats the log before it is written to the destination.

**Core Components:**

* **Logger** – Creates log messages.
* **Appender** – Defines where logs are written.
* **Layout** – Defines how logs are formatted.

**When to Use:**

* To **debug and troubleshoot** Java applications.
* To monitor **application behavior and errors**.
* In **enterprise**, **microservices**, and **distributed systems**.
* When centralized logging and log management are required.

**Simple Log4j2 Example:**

```java id="l5q2vm"
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
    private static final Logger logger =
        LogManager.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.error("An error occurred");
    }
}
```

**Simple `log4j2.xml` Configuration:**

```xml id="y8r4pk"
<Configuration>
  <Appenders>
    <Console name="Console">
      <PatternLayout pattern="%d %p %c - %m%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="info">
      <AppenderRef ref="Console"/>
    </Root>
  </Loggers>
</Configuration>
```


## 3: What is SLF4J?

**SLF4J (Simple Logging Facade for Java)** is a **logging abstraction (facade)** that provides a **common API** for Java logging. Instead of directly depending on a specific logging framework like **Log4j** or **Logback**, applications use SLF4J, which can be connected to any logging implementation.

**Key Features:**

* Provides a **single logging API** for different logging frameworks.
* Allows easy switching between **Logback**, **Log4j2**, or **java.util.logging (JUL)** without changing application code.
* Supports **parameterized logging**, improving performance.
* Reduces dependency on a specific logging implementation.
* Used as the **default logging API** in Spring Boot.

**How it Works:**

1. The application writes log statements using the **SLF4J API**.
2. SLF4J forwards the log messages to a configured **logging implementation** (e.g., Logback or Log4j2).
3. The logging framework processes and writes the logs to the configured destination, such as the **console** or a **file**.

**SLF4J Architecture:**

```text id="f2m8rx"
Application
     |
   SLF4J API
     |
-----------------------
|          |          |
Logback   Log4j2    JUL
```

**When to Use:**

* In **Java** and **Spring Boot** applications.
* When you want the flexibility to change the logging framework later.
* In **enterprise** and **microservices** applications for standardized logging.
* To improve maintainability and reduce framework dependency.

**Simple SLF4J Example:**

```java id="k7w4ny"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger =
        LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.error("An error occurred");
    }
}
```


## 4: What is Logback?

**Logback** is an **open-source Java Logging Framework** and the **default logging implementation in Spring Boot**. It works together with **SLF4J** to provide fast, flexible, and efficient application logging.

**Key Features:**

* **Default Logging Framework** in Spring Boot.
* Works seamlessly with **SLF4J**.
* Supports different **Log Levels**: **TRACE, DEBUG, INFO, WARN, and ERROR**.
* Can write logs to **Console, Files, or External Systems**.
* Supports **Asynchronous Logging**, **Log Rotation**, and **Custom Log Patterns**.

**How it Works:**

1. The application writes log messages using the **SLF4J API**.
2. **SLF4J** forwards the messages to **Logback**.
3. Logback checks the configured **log level**.
4. It formats the message and sends it to the configured **Appender** (console, file, etc.).
5. The logs are stored or displayed for monitoring and debugging.

**Core Components:**

* **Logger** – Creates log messages.
* **Appender** – Defines where logs are written.
* **Encoder/Layout** – Defines how logs are formatted.

**When to Use:**

* In **Spring Boot** applications.
* For **application monitoring** and **debugging**.
* In **microservices** and **distributed systems** requiring centralized logging.
* When you need a **high-performance** and flexible logging solution.

**Simple Logback Example:**

```java id="q4n7vx"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger =
        LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.error("An error occurred");
    }
}
```

**Simple `logback-spring.xml` Configuration:**

```xml id="w8m2kp"
<configuration>
    <appender name="CONSOLE"
        class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d %-5level %logger - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
```


## 5: What is structured logging?

**Structured Logging** is a logging approach where log data is stored in a **structured format (such as JSON)** instead of plain text. Each log entry contains **key-value pairs**, making it easier for machines and monitoring tools to **search, filter, and analyze** logs.

**Key Features:**

* Stores logs in a **structured format** (e.g., JSON).
* Uses **key-value pairs** instead of free-form text.
* Makes logs easier to **search, filter, and analyze**.
* Integrates well with centralized logging tools like **ELK Stack**, **Splunk**, and **Grafana Loki**.
* Improves debugging and monitoring in **microservices** and **distributed systems**.

**How it Works:**

1. The application generates a log event.
2. The logging framework (e.g., **Logback** or **Log4j2**) formats the log as a structured object.
3. The log is stored or sent to a centralized logging system.
4. Monitoring tools index the fields (e.g., `timestamp`, `userId`, `requestId`) for fast searching and analysis.

**Plain Log vs Structured Log:**

**Plain Log:**

```text
User 101 logged in successfully
```

**Structured Log (JSON):**

```json id="v6p3kj"
{
  "timestamp": "2026-06-13T10:30:00Z",
  "level": "INFO",
  "userId": 101,
  "action": "LOGIN",
  "message": "User logged in successfully"
}
```

**When to Use:**

* In **microservices** and **distributed systems**.
* For **centralized logging** and monitoring.
* When using tools like **ELK Stack**, **Splunk**, or **Grafana**.
* To simplify **debugging**, **troubleshooting**, and **log analytics**.

**Simple SLF4J Example:**

```java id="n4x8qw"
logger.info("UserId={} Action={} Status={}",
            101, "LOGIN", "SUCCESS");
```

## 6: What is centralized logging?

**Centralized Logging** is a technique where logs from all **Microservices** or applications are collected and stored in a **single central location**. Instead of checking logs on individual servers, developers can view and analyze all logs from one place.

**Key Features**

* Collects logs from multiple services into one system.
* Provides **centralized monitoring** and troubleshooting.
* Supports **searching**, **filtering**, and **visualizing** logs.
* Helps track requests across multiple microservices.
* Integrates with monitoring and alerting tools.

**How it Works**

1. Each microservice generates logs.
2. A **log collector** (such as **Filebeat** or **Fluentd**) gathers the logs.
3. Logs are sent to a centralized storage system like **Elasticsearch**.
4. A visualization tool like **Kibana** displays and searches the logs.

**Architecture Example**

```text id="1a5r9w"
User Service   Order Service   Payment Service
      |               |               |
      ---------------------------------
                    |
             Log Collector
          (Filebeat/Fluentd)
                    |
              Elasticsearch
                    |
                 Kibana
```

**When to Use**

* In **Microservices** architectures.
* In cloud-native or distributed applications.
* When multiple services generate large amounts of logs.
* For production systems requiring monitoring and auditing.

**Common Tools Used**

| **Tool**               | **Purpose**                       |
| ---------------------- | --------------------------------- |
| **Logback / Log4j2**   | Generate application logs         |
| **Filebeat / Fluentd** | Collect and forward logs          |
| **Elasticsearch**      | Store and index logs              |
| **Kibana**             | Search and visualize logs         |
| **ELK Stack**          | Elasticsearch + Logstash + Kibana |
| **EFK Stack**          | Elasticsearch + Fluentd + Kibana  |

**Spring Boot Logging Example**

```java id="2q8v7m"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public String getUsers() {
        logger.info("Fetching user details");
        return "Users List";
    }
}
```

**Spring Boot Logging Example**

**Service Class**

```java id="e8hzyt"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    public void createEmployee() {
        logger.info("Employee creation started");

        try {
            // Business logic
            logger.info("Employee created successfully");
        } catch (Exception e) {
            logger.error("Error while creating employee", e);
        }
    }
}
```

**Logback Configuration**

```xml id="jdr4c7"
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/application.log</file>

        <encoder>
            <pattern>
                %d %-5level %logger - %msg%n
            </pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="FILE"/>
    </root>

</configuration>
```

**How It Works in Production**

1. **Spring Boot** application generates logs.
2. Logs are written using **SLF4J** and **Logback**.
3. A log collector such as **Logstash** or **Fluentd** reads the log files.
4. Logs are sent to **Elasticsearch**.
5. **Kibana** provides dashboards and search capabilities.


```yaml
# Docker Compose with centralized logging
version: '3'
services:
  app1:
    image: myapp:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app1
        
  app2:
    image: myapp2:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app2
        
  fluentd:
    image: fluent/fluentd:latest
    ports:
      - "24224:24224"
    volumes:
      - ./fluentd.conf:/fluentd/etc/fluent.conf
      
  elasticsearch:
    image: elasticsearch:7.9.0
    
  kibana:
    image: kibana:7.9.0
    ports:
      - "5601:5601"
```

```java
// Application configuration for centralized logging
@Configuration
public class LoggingConfig {    
    @Bean
    public Logger structuredLogger() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        // Add correlation ID to all logs
        context.putProperty("service.name", "user-service");
        context.putProperty("service.version", "1.0.0");
        
        return context.getLogger("STRUCTURED");
    }
}
```

## 7: What is Config Server?

A **Config Server** is a **centralized configuration management service** used in **Microservices**. It stores and manages configuration files (such as database URLs, API keys, and application properties) in one place and provides them to all microservices at runtime.

**Key Features**

* Provides **centralized configuration management**.
* Stores configuration in a **Git repository** or other external storage.
* Allows **multiple microservices** to share common configurations.
* Supports **environment-specific** configurations (Dev, QA, Prod).
* Configuration can be refreshed without rebuilding the application.

**How it Works**

1. Configuration files are stored in a **Git repository**.
2. The **Spring Cloud Config Server** reads these files.
3. When a microservice starts, it contacts the Config Server.
4. The Config Server returns the required configuration based on the application name and environment.
5. The microservice loads and uses the configuration.

**Architecture Example**

```text id="8mtvhi"
              Git Repository
          (application.yml)
                    |
            Config Server
                    |
      ---------------------------
      |            |            |
 User Service  Order Service  Payment Service
```

**When to Use**

* In **Microservices** architectures with multiple services.
* When the same configuration is shared across applications.
* When configurations need to be updated without changing application code.
* For centralized management of environment-specific settings.

**Common Technologies Used**

| **Component**                  | **Purpose**                                  |
| ------------------------------ | -------------------------------------------- |
| **Spring Cloud Config Server** | Centralized configuration service            |
| **Git**                        | Stores configuration files                   |
| **Spring Cloud Config Client** | Fetches configuration from the Config Server |
| **Spring Boot Actuator**       | Refreshes configuration dynamically          |

**Config Server Example**

**Enable Config Server**

```java id="j0k5vq"
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**Config Server `application.yml`**

```yaml id="tk1l3m"
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/example/config-repo
```

**Client Configuration**

```yaml id="g8r4dw"
spring:
  application:
    name: user-service
  config:
    import: configserver:http://localhost:8888
```

## 8. What are Java deployment issues?

**Java Deployment Issues** are common problems that occur when deploying a Java application from development to testing or production environments. These issues can affect the application's **startup, performance, availability, or compatibility**.

**Key Features:**

* Usually related to **configuration**, **dependencies**, or **environment differences**.
* Can cause **application startup failures** or **runtime errors**.
* Often detected during **CI/CD deployments**.
* Can be minimized using **Docker**, **Kubernetes**, and automated deployment pipelines.

**Common Java Deployment Issues:**

| **Issue**                        | **Description**                                                       |
| -------------------------------- | --------------------------------------------------------------------- |
| **Dependency Conflicts**         | Different library versions cause runtime errors.                      |
| **Configuration Errors**         | Incorrect `application.properties` or environment variables.          |
| **Java Version Mismatch**        | Application is built with one JDK version but deployed on another.    |
| **Database Connection Issues**   | Wrong database URL, credentials, or unavailable database.             |
| **Port Conflicts**               | Another application is already using the required port.               |
| **Memory Issues**                | Insufficient JVM heap size causing `OutOfMemoryError`.                |
| **Deployment Failures**          | Incomplete builds, failed artifact uploads, or CI/CD pipeline issues. |
| **Logging or Permission Issues** | Application cannot write logs or access required files.               |

**How to Handle Them:**

1. Use **CI/CD pipelines** to automate build and deployment.
2. Use **Docker** to ensure consistent environments.
3. Manage database changes with **Flyway** or **Liquibase**.
4. Monitor logs using **SLF4J/Logback** and centralized logging tools.
5. Implement **health checks**, **rollback strategies**, and **zero downtime deployments**.

**When to Consider These Issues:**

* During **production deployments**.
* While migrating applications between environments.
* In **microservices** and **cloud-native** applications.
* When upgrading **Java versions** or dependencies.

**Simple Spring Boot Configuration Example:**

```properties id="d8m4rp"
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```


## 9. What are debugging strategies?

**Debugging Strategies** are systematic approaches used to **identify, analyze, and fix bugs or issues** in an application. The goal is to quickly find the **root cause** and resolve the problem with minimal impact.

**Key Features:**

* Helps identify the **root cause** of issues.
* Uses **logs, breakpoints, and monitoring tools**.
* Reduces application downtime and troubleshooting time.
* Improves application **stability** and **reliability**.

**How it Works:**

1. **Reproduce the issue** consistently.
2. Check **application logs** and error messages.
3. Use a **debugger** and set breakpoints to inspect code execution.
4. Analyze **stack traces**, request flow, and variable values.
5. Verify external dependencies such as **database, APIs, and network connections**.
6. Fix the issue, test the solution, and deploy the update.

**Common Debugging Strategies:**

* **Log Analysis:** Check logs using **SLF4J/Logback** or centralized logging tools.
* **Breakpoint Debugging:** Pause execution and inspect variables using an IDE.
* **Stack Trace Analysis:** Identify the exact location of exceptions.
* **Monitoring and Metrics:** Use tools like **Prometheus** and **Grafana** to analyze application health.
* **Binary Search Debugging:** Disable or isolate parts of the code to narrow down the problem.
* **Root Cause Analysis (RCA):** Fix the underlying issue instead of only the symptom.

**When to Use:**

* When an application throws **exceptions or errors**.
* To investigate **performance issues** or memory leaks.
* During **production incident analysis**.
* When troubleshooting **deployment** or **integration** problems.

**Simple Java Debugging Example:**

```java id="d5q9vk"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger =
        LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        int value = 10;
        logger.debug("Value = {}", value);
    }
}
```




# ✅ 27. Java SQL

## 1. What is SQL?

**SQL (Structured Query Language)** is the standard language used to communicate with a **Relational Database**. It is used to **store**, **retrieve**, **update**, and **delete** data from database tables.

Databases such as MySQL, Oracle Database, PostgreSQL, and Microsoft SQL Server use SQL.


## 2. What is the Difference Between WHERE and HAVING?

Both filter rows — but at different stages.

- **WHERE** — filters rows **before** grouping (works on raw rows)
- **HAVING** — filters groups **after** `GROUP BY` (works on aggregated results)

```sql
-- WHERE: filter before grouping
SELECT dept_id, COUNT(*) FROM employee
WHERE salary > 50000
GROUP BY dept_id;

-- HAVING: filter after grouping
SELECT dept_id, COUNT(*) as total FROM employee
GROUP BY dept_id
HAVING COUNT(*) > 5;
```

Simple rule: if you're filtering on an aggregate function like `COUNT`, `SUM`, `AVG` — use `HAVING`. Otherwise use `WHERE`.


## 3. What is GROUP BY and ORDER BY?

- **GROUP BY** — groups rows with the same value into summary rows. Used with aggregate functions like `COUNT`, `SUM`, `AVG`.
- **ORDER BY** — sorts the result set by one or more columns (ASC by default, or DESC).

```sql
-- GROUP BY: count employees per department
SELECT dept_id, COUNT(*) as total
FROM employee
GROUP BY dept_id;

-- ORDER BY: sort by salary descending
SELECT name, salary FROM employee
ORDER BY salary DESC;

-- Combined
SELECT dept_id, AVG(salary) as avg_sal
FROM employee
GROUP BY dept_id
ORDER BY avg_sal DESC;
```

`GROUP BY` collapses rows. `ORDER BY` just sorts them.


## 4. What is Database Indexing and When to Use It?


A **Database Index** is a **data structure** that improves the **speed of data retrieval** from a table. It works like the **index of a book**, allowing the database to find rows quickly without scanning the entire table.

**Key Features**

* Improves **SELECT** query performance.
* Reduces the need for a **Full Table Scan**.
* Created on one or more **columns**.
* Uses additional **storage space**.
* Slightly slows down **INSERT, UPDATE, and DELETE** operations because the index must also be updated.

**How It Works**

1. Create an **index** on one or more columns.
2. The database stores the indexed column values in a **sorted data structure** (commonly a **B-Tree**).
3. When a query searches on the indexed column, the database uses the index to locate matching rows quickly.
4. The database retrieves only the required rows instead of scanning the entire table.

**Syntax**

**Create an Index**

```sql
CREATE INDEX idx_employee_name
ON Employee(Name);
```

**Create a Unique Index**

```sql
CREATE UNIQUE INDEX idx_employee_email
ON Employee(Email);
```

**Example**

Without an index:

```sql
SELECT *
FROM Employee
WHERE Email = 'john@example.com';
```

* The database may perform a **Full Table Scan**.

After creating an index:

```sql
CREATE INDEX idx_email
ON Employee(Email);
```

Now the same query:

```sql
SELECT *
FROM Employee
WHERE Email = 'john@example.com';
```

* The database uses the **index** to find the row much faster.

**When to Use**

* Columns frequently used in the **WHERE** clause.
* Columns used in **JOIN** conditions.
* Columns used in **ORDER BY**.
* Columns used in **GROUP BY**.
* Columns frequently searched for specific values.
* **Primary Key** and **Unique** columns (most databases create indexes automatically).

**When Not to Use**

* Small tables where a **Full Table Scan** is faster.
* Columns with frequent **INSERT, UPDATE, DELETE** operations.
* Columns with very few unique values (for example, **Gender**, **Status**, **Yes/No**).
* Columns that are rarely used in queries.

**Advantages**

* Faster **SELECT** queries.
* Improves **JOIN**, **ORDER BY**, and **GROUP BY** performance.
* Reduces query execution time.
* Helps optimize large tables.

**Disadvantages**

* Uses additional **disk space**.
* Slows down **INSERT**, **UPDATE**, and **DELETE** operations.
* Too many indexes can reduce overall database performance.

**Common Interview Follow-up Questions**

**1. Why does an index improve performance?**

Because the database searches the **index** instead of scanning every row in the table, reducing the amount of data it needs to examine.

**2. Why do indexes slow down INSERT, UPDATE, and DELETE?**

Whenever data changes, the database must also **update the index**, which adds extra work.

**3. Can a table have multiple indexes?**

**Yes.** A table can have **multiple indexes** on different columns or combinations of columns.

**4. What is the difference between a Primary Key and an Index?**

| **Primary Key**                         | **Index**                           |
| --------------------------------------- | ----------------------------------- |
| Ensures **uniqueness** and **NOT NULL** | Improves **query performance**      |
| Only **one** per table                  | Multiple indexes allowed            |
| Automatically creates an index          | Can be **unique** or **non-unique** |

**5. What are the common types of indexes?**

* **Clustered Index** – Stores the table data in the same order as the index (usually only one per table).
* **Non-Clustered Index** – Stores the index separately and points to the actual rows (multiple allowed).
* **Unique Index** – Ensures all indexed values are unique.
* **Composite Index** – Created on **multiple columns**.


## 5. What is the Difference Between Stored Procedure and Aggregate Function?

**Stored Procedure** is a **precompiled SQL program** stored in the database. It can accept **parameters**, contain **business logic (IF, loops)**, and return results.

**Aggregate Functions** perform calculations on multiple rows and return a **single value**.
Common examples: `COUNT`, `SUM`, `AVG`, `MAX`, `MIN`.

| | Stored Procedure | Function |
|---|---|---|
| Returns | Optional (0 or more values via OUT params) | Must return a single value |
| Can use in SELECT | No | Yes |
| Can have DML (INSERT/UPDATE) | Yes | Limited (depends on DB) |
| Called with | `CALL` / `EXEC` | Used in expressions |
| Transaction control | Yes | No |

```sql
-- Stored Procedure
CREATE PROCEDURE get_employee(IN emp_id INT)
BEGIN
  SELECT * FROM employee WHERE id = emp_id;
END;

CALL get_employee(1);

-- Function
CREATE FUNCTION get_salary(emp_id INT) RETURNS DECIMAL
BEGIN
  DECLARE sal DECIMAL;
  SELECT salary INTO sal FROM employee WHERE id = emp_id;
  RETURN sal;
END;

SELECT get_salary(1);  -- used inline
```

Use a **function** when you need a return value in a query. Use a **procedure** for business logic, batch operations, or multiple operations.


## 6. What is Normalization? Types (1NF, 2NF, 3NF)?

**Normalization** is the process of organizing data in a database to **reduce redundancy (duplicate data)** and **improve data integrity** by dividing data into related tables.

**Benefits**

* **Eliminates duplicate data**
* **Maintains data consistency**
* **Reduces update, insert, and delete anomalies**
* **Improves data integrity**

**1NF (First Normal Form)**

**Rule**

* Each column should contain **atomic (single) values**.
* No **repeating groups** or multiple values in a single column.

**Example (Not in 1NF)**

| StudentID | Name | Subjects  |
| --------- | ---- | --------- |
| 101       | John | Java, SQL |

**After 1NF**

| StudentID | Name | Subject |
| --------- | ---- | ------- |
| 101       | John | Java    |
| 101       | John | SQL     |

**2NF (Second Normal Form)**

**Rule**

* Must satisfy **1NF**.
* Remove **partial dependency**, where a non-key column depends on only part of a composite primary key.

**Example**

**Before 2NF**

| StudentID | CourseID | StudentName | CourseName |
| --------- | -------- | ----------- | ---------- |

Primary Key: **(StudentID, CourseID)**

Here:

* **StudentName** depends only on **StudentID**.
* **CourseName** depends only on **CourseID**.

**After 2NF**

**Student Table**

| StudentID | StudentName |
| --------- | ----------- |

**Course Table**

| CourseID | CourseName |
| -------- | ---------- |

**Enrollment Table**

| StudentID | CourseID |
| --------- | -------- |

**3NF (Third Normal Form)**

**Rule**

* Must satisfy **2NF**.
* Remove **transitive dependency**, where a non-key column depends on another non-key column.

**Example**

**Before 3NF**

| EmployeeID | EmployeeName | DepartmentID | DepartmentName |
| ---------- | ------------ | ------------ | -------------- |

Here:

* **DepartmentName** depends on **DepartmentID**, not directly on **EmployeeID**.

**After 3NF**

**Employee Table**

| EmployeeID | EmployeeName | DepartmentID |
| ---------- | ------------ | ------------ |

**Department Table**

| DepartmentID | DepartmentName |
| ------------ | -------------- |



## 7. What is the Between DELETE, TRUNCATE, and DROP?


These are SQL commands used to remove data or database objects, but they work differently.

| **Feature**      | **DELETE**                                | **TRUNCATE**                       | **DROP**                           |
| ---------------- | ----------------------------------------- | ---------------------------------- | ---------------------------------- |
| Removes          | **Selected rows**                         | **All rows**                       | **Entire table/object**            |
| **WHERE** clause | **Yes**                                   | **No**                             | **No**                             |
| Table structure  | **Remains**                               | **Remains**                        | **Deleted**                        |
| Rollback         | **Yes** (if transaction is not committed) | **Depends on the database**        | **Depends on the database**        |
| Speed            | **Slower**                                | **Faster**                         | **Fastest**                        |
| Type             | **DML (Data Manipulation Language)**      | **DDL (Data Definition Language)** | **DDL (Data Definition Language)** |

**1. DELETE**

**DELETE** removes **specific rows** or **all rows** from a table while keeping the **table structure** intact.

**How It Works**

* Deletes rows **one by one**.
* Can use a **WHERE** clause to delete selected records.
* Can be **rolled back** before the transaction is committed (in transactional databases).

**Syntax**

```sql
DELETE FROM Employee
WHERE Id = 101;
```

**Delete All Rows**

```sql
DELETE FROM Employee;
```

**When to Use**

* Delete **specific records**.
* When you need **rollback support**.
* When deleting based on a **condition**.

**2. TRUNCATE**

**TRUNCATE** removes **all rows** from a table but keeps the **table structure**.

**How It Works**

* Removes **all records at once**.
* Cannot use a **WHERE** clause.
* Resets the **identity/auto-increment** value in many databases.
* Much **faster** than **DELETE** because it deallocates data pages instead of deleting rows one by one.

**Syntax**

```sql
TRUNCATE TABLE Employee;
```

**When to Use**

* Remove **all records** quickly.
* Reset a table before loading fresh data.
* When individual row deletion is not required.

**3. DROP**

**DROP** permanently removes the **entire database object**, including its **data**, **structure**, indexes, constraints, and permissions.

**How It Works**

* Deletes the **table definition** from the database.
* The table no longer exists after execution.

**Syntax**

```sql
DROP TABLE Employee;
```

**When to Use**

* Remove a table that is **no longer needed**.
* Delete database objects permanently.

**Example**

Suppose the **Employee** table contains:

```text
Id   Name
101  John
102  Alice
103  Bob
```

**DELETE**

```sql
DELETE FROM Employee
WHERE Id = 101;
```

**Result**

```text
102  Alice
103  Bob
```

**TRUNCATE**

```sql
TRUNCATE TABLE Employee;
```

**Result**

```text
Table exists, but contains 0 rows.
```

**DROP**

```sql
DROP TABLE Employee;
```

**Result**

```text
Employee table no longer exists.
```

**When to Use Which?**

* Use **DELETE** when removing **specific rows**.
* Use **TRUNCATE** when removing **all rows** but keeping the **table**.
* Use **DROP** when removing the **entire table** permanently.

**Common Interview Follow-up Questions**

**1. Which command is the fastest?**

**DROP** is generally the **fastest**, followed by **TRUNCATE**, then **DELETE**.

**2. Which command supports the WHERE clause?**

Only **DELETE** supports the **WHERE** clause.

**3. Which command resets the identity (auto-increment) value?**

**TRUNCATE** resets the **identity/auto-increment** value in many databases.

**4. Which command removes the table structure?**

Only **DROP** removes the **table structure**.


```sql
DELETE FROM employee WHERE id = 5;     -- removes one row, can rollback

TRUNCATE TABLE employee;               -- removes all rows, fast, no rollback

DROP TABLE employee;                   -- removes table entirely
```

Use `DELETE` for selective removal. `TRUNCATE` to clear a table fast. `DROP` only when you want to remove the table completely.


## 8. What is a Subquery vs a JOIN?

Both retrieve related data — but differently.

**Subquery** — a query nested inside another query. Runs separately, result is used by the outer query.

```sql
-- Subquery: find employees earning more than average
SELECT name FROM employee
WHERE salary > (SELECT AVG(salary) FROM employee);
```

**JOIN** — combines rows from two tables in a single query execution.

```sql
-- JOIN: same result, often more efficient
SELECT e.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id
WHERE d.name = 'Engineering';
```

**When to use which:**
- Use **JOIN** when you need columns from multiple tables — it's generally faster
- Use **subquery** when the inner result is a single value or a filtered set that's hard to express as a JOIN
- Correlated subqueries (referencing outer query) can be slow — prefer JOIN or CTEs


## 9. What is a View in SQL?

A **View** is a **virtual table** created from the result of a **SQL query**. It **does not store data** itself; instead, it displays data from one or more underlying tables whenever it is queried.

**Key Features**

* A **virtual table** based on a **SELECT** query.
* **Does not store data** (except **Materialized Views** in some databases).
* Can combine data from **multiple tables**.
* Improves **security** by exposing only required columns or rows.
* Simplifies **complex SQL queries**.

**How It Works**

1. Create a **View** using a **SELECT** statement.
2. The database stores only the **query definition**.
3. When the view is queried, the database executes the stored query.
4. The latest data from the underlying table(s) is returned.

**Syntax**

```sql
CREATE VIEW view_name AS
SELECT column1, column2
FROM table_name
WHERE condition;
```

**Example**

**Create a View**

```sql
CREATE VIEW EmployeeView AS
SELECT EmployeeId, EmployeeName, Department
FROM Employee
WHERE Department = 'IT';
```

**Query the View**

```sql
SELECT *
FROM EmployeeView;
```

**Output**

```text
EmployeeId  EmployeeName  Department
101         John          IT
102         Alice         IT
```

**When to Use**

* Simplify **complex JOIN** queries.
* Restrict access to sensitive columns (for example, **Salary**).
* Reuse frequently executed queries.
* Present customized data to different users.

**Advantages**

* Improves **security** by hiding sensitive data.
* Simplifies complex queries.
* Promotes **code reusability**.
* Always shows the **latest data** from the underlying tables.
* Makes SQL easier to maintain.

**Limitations**

* A standard **View** does **not store data**.
* Complex views may have slower performance.
* Some views are **not updatable**, especially those using **JOIN**, **GROUP BY**, or aggregate functions.



**Common Interview Follow-up Questions**

**1. Does a View store data?**

**No.** A standard **View** stores only the **SQL query**, not the actual data. Every time you query the view, it retrieves the latest data from the base table(s).

**2. Can we perform INSERT, UPDATE, or DELETE on a View?**

**Yes**, but only if the view is **updatable**. Views with **JOIN**, **GROUP BY**, **DISTINCT**, or aggregate functions are generally **not updatable**.

**3. What is the difference between a View and a Table?**

| **View**                        | **Table**                 |
| ------------------------------- | ------------------------- |
| **Virtual table**               | **Physical table**        |
| Usually **does not store data** | Stores actual data        |
| Created from a **SELECT** query | Stores records directly   |
| Always shows the latest data    | Data is physically stored |

**4. What is a Materialized View?**

A **Materialized View** **stores the query result physically**. It provides **faster read performance** but must be **refreshed** to reflect changes in the underlying tables.


## 11. What are different types of **JOINs**?

JOINs combine rows from two or more tables based on a related column.

- **INNER JOIN** — returns only matching rows from both tables
- **LEFT JOIN** — returns all rows from the left table + matching rows from right (nulls if no match)
- **RIGHT JOIN** — returns all rows from the right table + matching from left
- **FULL OUTER JOIN** — returns all rows from both tables (nulls where no match)

```sql
-- INNER JOIN
SELECT e.name, d.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id;

-- LEFT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
LEFT JOIN department d ON e.dept_id = d.id;

-- RIGHT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
RIGHT JOIN department d ON e.dept_id = d.id;

-- FULL OUTER JOIN
SELECT e.name, d.name FROM employee e
FULL OUTER JOIN department d ON e.dept_id = d.id;
```

Think of it as a Venn diagram — INNER is the overlap, LEFT keeps the left circle, FULL keeps both circles.


## 12. What is a **Primary Key** and **Foreign Key** ?

A **Primary Key** is a column (or combination of columns) that **uniquely identifies** each record in a table.

A **Foreign Key** is a column that **references the Primary Key** of another table to establish a relationship.

| **Feature**      | **Primary Key**                             | **Foreign Key**                                      |
| ---------------- | ------------------------------------------- | ---------------------------------------------------- |
| Purpose          | **Uniquely identifies** each row            | **Creates a relationship** between tables            |
| Duplicate Values | **Not Allowed**                             | **Allowed**                                          |
| NULL Values      | **Not Allowed**                             | **Allowed** (unless restricted)                      |
| Number per Table | Only **one Primary Key** (can be composite) | Multiple **Foreign Keys** allowed                    |
| References       | Own table                                   | **Primary Key** (or **Unique Key**) of another table |


## 12. What is the Difference Between UNION and UNION ALL?

Both combine results of two SELECT queries — but handle duplicates differently.

- **UNION** — combines results and **removes duplicates** (slower, does a distinct sort)
- **UNION ALL** — combines results and **keeps all rows including duplicates** (faster)

```sql
-- UNION: removes duplicates
SELECT name FROM employee_india
UNION
SELECT name FROM employee_us;

-- UNION ALL: keeps duplicates
SELECT name FROM employee_india
UNION ALL
SELECT name FROM employee_us;
```

**Rules for both:**
- Same number of columns in both SELECT statements
- Columns must have compatible data types

Use `UNION ALL` when you know there are no duplicates or you want all rows — it's faster because it skips the deduplication step.



## 12. What is SQL injection and how to prevent it?

**SQL Injection** is a **security vulnerability** where an attacker injects malicious SQL code into application queries, to **manipulate or access the database illegally**.

It can be prevented by using **Prepared Statements (Parameterized Queries)**, **input validation**, **ORM frameworks (like JPA/Hibernate)**, **stored procedures**, and **proper access control**.


```java
// Vulnerable code - SQL injection possible
String userId = request.getParameter("id");
String sql = "SELECT * FROM users WHERE id = " + userId;
// If userId = "1 OR 1=1", returns all users!

// Safe code - using PreparedStatement
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```


## 13. What is a Cursor in SQL and when should it be used ?

A **Cursor** is used to **process database rows one by one** instead of all at once.
It is useful when we need **row-by-row processing**, but it should be used carefully because it can be **slower than set-based queries**

**Why use it?**

* Prevents **OutOfMemoryError**
* Good for very large datasets
* Reduces heap usage


```java
DECLARE @name VARCHAR(50)

-- 1. Declare Cursor
DECLARE emp_cursor CURSOR FOR
SELECT name FROM employees

-- 2. Open Cursor
OPEN emp_cursor

-- 3. Fetch first row
FETCH NEXT FROM emp_cursor INTO @name

-- Loop through all rows
WHILE @@FETCH_STATUS = 0
BEGIN
    PRINT @name

    -- Fetch next row
    FETCH NEXT FROM emp_cursor INTO @name
END

-- 5. Close Cursor
CLOSE emp_cursor

-- 6. Deallocate Cursor
DEALLOCATE emp_cursor
```

```java
@Transactional
public void processProducts() {
    try (Stream<Product> stream = repo.findAllByStream()) {
        stream.forEach(product -> {
            // process record
        });
    }
}
```

## 14. What is Batch Processing?

**Batch Processing** is a technique where a **large number of records** are **processed together as a single batch** instead of processing each record individually. It is commonly used for **scheduled**, **repetitive**, and **high-volume** data processing.

**Key Features**

* **Processes Multiple Records** together
* **Scheduled Execution**
* **High Performance**
* **Automatic Retry** and **Restart**
* **Transaction Management**
* **Scalable** for large datasets

**How It Works**

1. A **Batch Job** is triggered manually or by a scheduler.
2. Data is **read** from a source (Database, CSV, File, API).
3. Data is **processed** (validate, transform, calculate).
4. Processed data is **written** to the destination.
5. The job completes and generates a **status/report**.

**Architecture Flow**

```text
Scheduler
    │
    ▼
Batch Job
    │
    ▼
Item Reader
    │
    ▼
Item Processor
    │
    ▼
Item Writer
    │
    ▼
Database / File
```

**Main Components (Spring Batch)**

| **Component**     | **Purpose**                           |
| ----------------- | ------------------------------------- |
| **Job**           | Represents the complete batch process |
| **Step**          | A single phase within the job         |
| **ItemReader**    | Reads data from the source            |
| **ItemProcessor** | Processes or transforms the data      |
| **ItemWriter**    | Writes data to the destination        |

**When to Use**

* **Payroll Processing**
* **Bank Transactions**
* **Invoice Generation**
* **Report Generation**
* **Data Migration**
* **ETL (Extract, Transform, Load)**

**Code Example**

```java
@Component
public class EmployeeProcessor
        implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) {
        employee.setSalary(employee.getSalary() * 1.10);
        return employee;
    }
}
```

**Advantages**

* **Efficient** for large datasets
* **Improves Performance**
* **Reduces Resource Usage**
* **Supports Restart** after failure
* **Easy to Automate**

**Disadvantages**

* **Not Suitable** for real-time processing
* Large jobs may take **more time** to complete
* Error handling can be **complex**


**Common Interview Follow-up**

**Q: What is the difference between Batch Processing and Stream Processing?**

| **Batch Processing**                                   | **Stream Processing**                                                    |
| ------------------------------------------------------ | ------------------------------------------------------------------------ |
| Processes **data in batches**.                         | Processes **data continuously** as it arrives.                           |
| Used for **scheduled jobs**.                           | Used for **real-time applications**.                                     |
| Higher **throughput**.                                 | Lower **latency**.                                                       |
| Example: **Payroll**, **ETL**, **Invoice Generation**. | Example: **Fraud Detection**, **Live Notifications**, **Stock Trading**. |


## 15. What is sharding in databases?

**Sharding** is a **database scaling technique** where a large database is **split into smaller, independent databases (called shards)**. Each shard stores a **portion of the data**, allowing the system to handle more users and data efficiently.

**Key Features**

* **Horizontal Scaling**
* **Distributes Data** across multiple databases
* **Improves Performance**
* **Reduces Database Load**
* **Supports High Availability**
* Each **Shard** is an independent database

**How It Works**

1. The application receives a request.
2. A **Sharding Key** (such as **User ID** or **Customer ID**) is used.
3. The application determines which **Shard** contains the data.
4. The request is sent only to that specific **Shard**.
5. The shard processes the request and returns the result.

**Architecture Flow**

```text
           Client
              │
              ▼
        Application
              │
      Sharding Logic
              │
    ┌─────────┼─────────┐
    ▼         ▼         ▼
 Shard 1   Shard 2   Shard 3
(User 1-1000) (1001-2000) (2001-3000)
```

**Types of Sharding**

| **Type**               | **Description**                                           |
| ---------------------- | --------------------------------------------------------- |
| **Range Sharding**     | Data is divided by a range (e.g., IDs 1–1000, 1001–2000). |
| **Hash Sharding**      | A **hash function** decides which shard stores the data.  |
| **Directory Sharding** | A lookup table maps data to the correct shard.            |
| **Geo Sharding**       | Data is split based on **region** or **location**.        |

**When to Use**

* **Large Databases**
* **High Traffic Applications**
* **Microservices**
* **E-commerce Platforms**
* **Banking Systems**
* **Social Media Applications**

**Code Example**

```java
int shardId = userId % 3;

switch (shardId) {
    case 0:
        // Query Shard 1
        break;
    case 1:
        // Query Shard 2
        break;
    case 2:
        // Query Shard 3
        break;
}
```

**Advantages**

* **Better Performance**
* **Horizontal Scalability**
* **Lower Load** on each database
* **Faster Queries**
* **Supports Large Datasets**

**Disadvantages**

* **More Complex Architecture**
* **Cross-Shard Queries** are difficult
* **Data Rebalancing** is required when adding new shards
* **Joins Across Shards** are expensive



**Common Interview Follow-up**

**Q: What is the difference between Sharding and Partitioning?**

| **Sharding**                                              | **Partitioning**                                                |
| --------------------------------------------------------- | --------------------------------------------------------------- |
| **Data is distributed across multiple database servers.** | **Data is divided within the same database server.**            |
| Supports **Horizontal Scaling**.                          | Supports **Logical Organization** and performance optimization. |
| Each shard has its own **database instance**.             | All partitions belong to the **same database**.                 |
| Used for **very large, distributed systems**.             | Used for **managing large tables** within one database.         |


## 15. What is stored procedure in sql?


A **Stored Procedure** is a **precompiled collection of SQL statements** stored in the database that performs a **specific task**. It can accept **input parameters**, execute **multiple SQL operations**, and optionally return **output values**.

**Key Features**

* **Precompiled** for better performance.
* Stored and managed inside the **database**.
* Supports **input** and **output parameters**.
* Can contain **SELECT, INSERT, UPDATE, DELETE**, loops, conditions, and transactions.
* Improves **code reusability**, **security**, and **maintainability**.

**How It Works**

1. Create the stored procedure once in the database.
2. Pass required **input parameters** while calling it.
3. The database executes the stored SQL statements.
4. Returns **result sets**, **output parameters**, or a **status**.

**Syntax**

```sql
CREATE PROCEDURE procedure_name
    @parameter datatype
AS
BEGIN
    -- SQL Statements
END;
```

**Example**

**Create Procedure**

```sql
CREATE PROCEDURE GetEmployeeById
    @EmpId INT
AS
BEGIN
    SELECT *
    FROM Employee
    WHERE Id = @EmpId;
END;
```

**Execute Procedure**

```sql
EXEC GetEmployeeById @EmpId = 101;
```

**Output**

```text
Id   Name     Department
101  John     IT
```

**When to Use**

* When the **same SQL logic** is executed repeatedly.
* To improve **performance** by using precompiled execution plans.
* To **encapsulate business logic** inside the database.
* To restrict direct table access and improve **security**.
* To perform **complex database operations** involving multiple SQL statements.

**Advantages**

* **Faster execution** due to precompilation.
* **Reusable** and reduces duplicate SQL code.
* Better **security** by granting execute permission instead of table access.
* Easier **maintenance** since logic is stored in one place.
* Reduces **network traffic** because a single procedure call can execute multiple statements.

**Limitations**

* Can become difficult to maintain if business logic grows too large.
* Database-specific syntax may reduce portability between different databases.
* Debugging complex stored procedures can be challenging.

**Common Interview Follow-up Questions**

**1. What is the difference between a Stored Procedure and a Function?**

| **Stored Procedure**                   | **Function**                                      |
| -------------------------------------- | ------------------------------------------------- |
| May or may not return a value          | Must return a value                               |
| Can perform **INSERT, UPDATE, DELETE** | Mainly used to compute and return a value         |
| Called using **EXEC**                  | Can be used inside **SELECT** statements          |
| Can return multiple result sets        | Returns a single value or table (depending on DB) |

**2. Why are Stored Procedures faster?**

Because they are **precompiled**, the database can **reuse the execution plan**, reducing query parsing and optimization overhead.

**3. Can a Stored Procedure return multiple values?**

Yes. It can return:

* **Result sets**
* **Output parameters**
* **Return status code**



# ✅ 28. Java CI/CD and DevOp

## 1: What is CI/CD (Continuous Integration and Continuous Deployment)?

**CI/CD** is a **software development practice** that automates the process of **building, testing, and deploying** applications. It helps teams deliver code changes **quickly, reliably, and with fewer errors**.

* **CI (Continuous Integration):** Developers frequently **merge code changes** into a shared repository, and every commit automatically triggers **builds and tests**.
* **CD (Continuous Deployment/Delivery):** After successful testing, the application is **automatically delivered or deployed** to the target environment.

**Key Features:**

* **Automatic Build and Testing** after every code commit.
* **Fast Feedback** to detect bugs early.
* **Automated Deployment** to test, staging, or production environments.
* **Consistent and Reliable Releases** with minimal manual work.
* Integrates with tools like **Git, Jenkins, GitHub Actions, Docker, and Kubernetes**.

**How it Works:**

1. Developer **pushes code** to a Git repository.
2. **CI tool** (e.g., Jenkins) detects the change.
3. The application is **built and tested** automatically.
4. If all tests pass, the **CD pipeline** deploys the application.
5. The application becomes available in **staging or production**.

**When to Use:**

* In **Agile** and **DevOps** environments.
* When multiple developers work on the same project.
* For applications requiring **frequent releases**.
* To reduce **manual deployment errors** and improve release speed.

**Simple CI/CD Pipeline Example (`Jenkinsfile`):**

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building application...'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
            }
        }
    }
}
```


## 3: What is Jenkins?

**Jenkins** is an **open-source Automation Server** used to automate the **Build, Test, and Deployment** process of applications. It is widely used for implementing **CI/CD (Continuous Integration and Continuous Deployment)** pipelines.

**Key Features:**

* **Automates** build, test, and deployment tasks.
* Supports **CI/CD Pipelines**.
* Large collection of **Plugins** for Git, Maven, Docker, Kubernetes, etc.
* Can run jobs on **multiple agents (distributed builds)**.
* Supports **Pipeline as Code** using a `Jenkinsfile`.

**How it Works:**

1. Developer pushes code to **Git**.
2. **Jenkins** detects the change (via webhook or polling).
3. Jenkins pulls the latest code.
4. It runs the **build**, **unit tests**, and **quality checks**.
5. If everything passes, Jenkins **deploys** the application automatically.

**When to Use:**

* When you want to **automate software delivery**.
* For **continuous integration** after every code commit.
* For **continuous deployment** to test or production environments.
* In projects requiring frequent and reliable releases.

**Simple Pipeline Example (`Jenkinsfile`):**
```groovy
// Jenkinsfile example
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Package') {
            steps {
                sh './mvnw package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Deploy') {
            when { branch 'main' }
            steps {
                sh 'docker build -t myapp .'
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}
```


## 4: What is Git?

**Git** is a **Distributed Version Control System (DVCS)** used to **track changes in source code** and help multiple developers work on the same project without overwriting each other's work.

**Key Features:**

* **Version Control** to track code changes.
* **Distributed System** where every developer has a complete copy of the repository.
* Supports **Branching and Merging** for parallel development.
* Enables **Collaboration** among multiple developers.
* Provides **History and Rollback** to restore previous versions.

**How it Works:**

1. Create or clone a **Git repository**.
2. Make changes to the code.
3. Add changes to the staging area using `git add`.
4. Save the changes with `git commit`.
5. Push the commits to a remote repository (e.g., GitHub) using `git push`.
6. Other developers can pull the latest changes using `git pull`.

**When to Use:**

* For **source code management** in software projects.
* When multiple developers collaborate on the same codebase.
* To maintain **code history** and easily revert changes.
* For implementing **CI/CD pipelines** and automated deployments.

**Common Git Commands:**

```bash
# Basic Git commands
git init                          # Initialize repository
git add .                         # Stage changes
git commit -m "Add new feature"   # Commit changes
git branch feature-branch         # Create branch
git checkout feature-branch       # Switch branch
git merge feature-branch          # Merge branch
git push origin main              # Push to remote
git pull origin main              # Pull from remote
```


## 5: What is version control?

**Version Control** is a system that **tracks and manages changes** made to source code or files over time. It allows developers to **save different versions**, **collaborate safely**, and **restore previous versions** if needed.

**Key Features:**

* **Tracks Changes** made to files and code.
* Maintains a complete **history of versions**.
* Supports **multiple developers** working on the same project.
* Allows **rollback** to a previous stable version.
* Supports **branching and merging** for parallel development.

**How it Works:**

1. Developers make changes to the code.
2. The **Version Control System (VCS)** records each change as a new version.
3. Changes are saved with a **commit**.
4. Team members can **merge** their work into a shared codebase.
5. If an issue occurs, the project can be **reverted** to an earlier version.

**When to Use:**

* In **software development** projects.
* When multiple developers collaborate on the same codebase.
* To maintain a **history of code changes**.
* To safely experiment with new features using **branches**.


* System for tracking and managing changes to files over time
* **History**: Complete history of all changes and versions
* **Collaboration**: Multiple developers can work on same project
* **Branching**: Parallel development streams
* **Backup**: Distributed copies serve as backups
* **Rollback**: Ability to revert to previous versions
* **Types**: Centralized (SVN) vs Distributed (Git)

```bash
# Version control workflow
git status                    # Check current state
git log --oneline            # View commit history
git diff HEAD~1              # Compare with previous version
git checkout HEAD~2 -- file.java  # Restore file from 2 commits ago
git tag v1.0.0               # Tag release version
git revert abc123            # Revert specific commit
```


## 6: What is infrastructure as code?

**Infrastructure as Code (IaC)** is the practice of **managing and provisioning infrastructure using code instead of manual setup**.

It allows infrastructure to be **version-controlled, automated, and reproducible** across environments using tools like Terraform or CloudFormation.

* Managing and provisioning infrastructure through code rather than manual processes
* **Declarative**: Define desired state, tools ensure it's achieved
* **Version Control**: Infrastructure changes tracked like application code
* **Reproducible**: Consistent environments across dev, test, production
* **Automation**: Automated provisioning and configuration
* **Tools**: Terraform, CloudFormation, Ansible, Kubernetes manifests

```yaml
# Kubernetes deployment (IaC)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: java-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: java-app
  template:
    spec:
      containers:
      - name: app
        image: myapp:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: DATABASE_URL
          value: "jdbc:postgresql://db:5432/mydb"
```

```java
# Terraform example
resource "aws_instance" "web" {
  ami           = "ami-0c55b159cbfafe1d0"
  instance_type = "t2.micro"
  
  tags = {
    Name = "JavaApp"
  }
}
```


## 7: What is deployment strategies?

**Deployment Strategies** are different methods of **releasing a new version of an application** to users while minimizing **downtime, risk, and failures**.

**Key Features:**

* Reduces **deployment risk**.
* Minimizes **application downtime**.
* Enables **easy rollback** if issues occur.
* Improves **availability and user experience**.

**Common Deployment Strategies:**

| **Strategy**              | **How it Works**                                                                                                                   | **When to Use**                                              |
| ------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| **Recreate**              | Stops the old version and deploys the new version.                                                                                 | Small applications where short downtime is acceptable.       |
| **Rolling Deployment**    | Gradually replaces old instances with new ones.                                                                                    | Most common choice for microservices and cloud applications. |
| **Blue-Green Deployment** | Maintains two identical environments (**Blue** = current, **Green** = new). Traffic switches to the new environment after testing. | When near-zero downtime and quick rollback are required.     |
| **Canary Deployment**     | Releases the new version to a **small percentage of users** first. If successful, it is rolled out to everyone.                    | High-risk or large-scale production deployments.             |
| **A/B Testing**           | Different user groups receive different versions to compare behavior or performance.                                               | Feature testing and product experimentation.                 |

**How it Works:**

1. Build and test the new application version.
2. Choose a suitable **deployment strategy**.
3. Deploy the new version based on the selected approach.
4. Monitor application health and user traffic.
5. If issues occur, **rollback** to the previous stable version.

**When to Use:**

* During **CI/CD pipelines** for automated releases.
* For **microservices** and **cloud-native** applications.
* When high availability and minimal downtime are required.
* To safely release new features in production.

* Different approaches for releasing applications to production

* **Rolling Deployment**: Gradually replace old instances with new ones
* **Blue-Green**: Switch between two identical environments
* **Canary**: Deploy to small subset of users first
* **A/B Testing**: Compare different versions with user groups
* **Recreate**: Stop old version, start new version (downtime)
* **Shadow**: Route copy of traffic to new version for testing

```yaml
# Rolling deployment strategy
apiVersion: apps/v1
kind: Deployment
spec:
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 1
      maxSurge: 1
  replicas: 5
  template:
    spec:
      containers:
      - name: app
        image: myapp:v2.0.0
```

## 10: What is containerization?

**Containerization** is a technology that packages an application along with its **code, libraries, dependencies, and configuration files** into a lightweight, isolated unit called a **Container**. This ensures the application runs **consistently across different environments**.

**Key Features:**

* **Portable** – Runs the same on development, testing, and production environments.
* **Lightweight** – Shares the host operating system kernel, so it uses fewer resources than virtual machines.
* **Isolated** – Each container has its own dependencies and does not interfere with others.
* **Fast Startup** – Containers start in seconds.
* Works well with **Docker** and orchestration tools like **Kubernetes**.

**How it Works:**

1. Create an application and define its dependencies.
2. Write a **Dockerfile** to describe how to build the container.
3. Build a **Container Image** from the Dockerfile.
4. Run the image as a **Container**.
5. The same container can be deployed on any system with a container runtime (e.g., Docker).

**When to Use:**

* For **Microservices Architecture**.
* To ensure **environment consistency** across development and production.
* In **CI/CD pipelines** for automated deployment.
* For **cloud-native** and scalable applications.

**Simple Dockerfile Example:**

```dockerfile id="n7k2xq"
FROM openjdk:21-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

**Build and Run Commands:**

```bash id="h4v9mp"
# Build the image
docker build -t my-app .

# Run the container
docker run -p 8080:8080 my-app
```


## 11: What is Docker?

**Docker** is an **open-source Containerization Platform** that allows developers to **build, package, ship, and run applications** inside lightweight, isolated units called **Containers**. It ensures the application runs the same way across all environments.

**Key Features:**

* **Containerization** of applications and dependencies.
* **Portable** – Runs consistently on any system with Docker installed.
* **Lightweight** – Shares the host OS kernel, using fewer resources than virtual machines.
* **Fast Deployment** and startup time.
* Integrates easily with **CI/CD**, **Kubernetes**, and cloud platforms.

**How it Works:**

1. Create a **Dockerfile** that defines the application environment.
2. Build a **Docker Image** from the Dockerfile.
3. Run the image to create a **Docker Container**.
4. The container executes the application in an isolated environment.
5. The same image can be deployed anywhere without changing the code.

**When to Use:**

* For **Microservices Architecture**.
* To ensure **consistent environments** across development, testing, and production.
* In **CI/CD pipelines** for automated builds and deployments.
* For **cloud-native** and scalable applications.

**Simple Dockerfile Example:**

```dockerfile id="t5m8kq"
FROM openjdk:21-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

**Common Docker Commands:**

```bash
# Build and run Java application
docker build -t myapp:latest .
docker run -p 8080:8080 myapp:latest

# Docker Compose for multi-service setup
version: '3'
services:
  app:
    build: .
    ports: ["8080:8080"]
  db:
    image: mysql:8.0
```

## 12. What is Kubernetes?

**Kubernetes (K8s)** is an **open-source Container Orchestration Platform** used to **automate the deployment, scaling, management, and monitoring of containerized applications**. It helps manage large numbers of Docker containers across multiple servers.

**Key Features:**

* **Automatic Deployment** and management of containers.
* **Auto Scaling** based on application load.
* **Self-Healing** by restarting or replacing failed containers.
* **Load Balancing** to distribute traffic across containers.
* Supports **Rolling Updates** and **Rollback** without downtime.

**How it Works:**

1. Developers package the application into **Docker Containers**.
2. Kubernetes groups containers into **Pods**.
3. The **Master (Control Plane)** schedules and manages Pods across **Worker Nodes**.
4. Kubernetes continuously monitors the application and automatically recovers from failures.
5. It can scale the number of Pods up or down based on demand.

**When to Use:**

* For **Microservices Architecture**.
* When managing **multiple containers** across servers.
* For **high availability** and **auto-scaling** requirements.
* In **cloud-native** applications and **CI/CD pipelines**.

**Simple Kubernetes Deployment Example:**

```yaml id="k8f3wd"
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-app
  template:
    metadata:
      labels:
        app: my-app
    spec:
      containers:
      - name: my-app
        image: my-app:latest
```


## 13. What is cloud computing?

**Cloud Computing** is the delivery of **computing services** such as **servers, storage, databases, networking, and software** over the **Internet (Cloud)** instead of using local machines or on-premise infrastructure.

**Key Features:**

* **On-Demand Access** to computing resources.
* **Scalability** to increase or decrease resources as needed.
* **Pay-as-You-Go** pricing model.
* **High Availability** and reliability.
* Easy integration with **CI/CD, Docker, and Kubernetes**.

**How it Works:**

1. A **Cloud Provider** (e.g., AWS, Azure, GCP) offers infrastructure and services.
2. Users request resources such as virtual machines, databases, or storage.
3. The cloud platform automatically provisions and manages these resources.
4. Applications are deployed and accessed over the Internet.
5. Resources can be scaled up or down based on demand.

**When to Use:**

* For **web and enterprise applications**.
* When you need **high scalability** and **high availability**.
* To reduce the cost of maintaining physical servers.
* For **microservices**, **containerized applications**, and **CI/CD pipelines**.

**Simple Example (Deploying a Docker Container on the Cloud):**

```bash id="c7m4kx"
# Build Docker image
docker build -t my-app .

# Push image to a cloud container registry
docker push my-app:latest

# Deploy the image to a cloud platform
kubectl apply -f deployment.yaml
```

**Common Cloud Service Models:**

| **Model**                              | **Description**                                       | **Example**                          |
| -------------------------------------- | ----------------------------------------------------- | ------------------------------------ |
| **IaaS (Infrastructure as a Service)** | Provides virtual servers, storage, and networking.    | AWS EC2, Azure VM                    |
| **PaaS (Platform as a Service)**       | Provides a platform to build and deploy applications. | Google App Engine, Azure App Service |
| **SaaS (Software as a Service)**       | Provides ready-to-use software over the Internet.     | Gmail, Microsoft 365                 |


## 14. What is distributed system?

A **Distributed System** is a collection of **multiple independent computers (nodes)** that work together and communicate over a network to function as a **single system**. The workload is shared across multiple machines, improving **scalability, availability, and fault tolerance**.

**Key Features:**

* **Multiple Nodes** work together as one system.
* **Scalability** by adding more servers when demand increases.
* **Fault Tolerance** because failure of one node does not stop the entire system.
* **High Availability** through redundancy and replication.
* **Resource Sharing** across different machines.

**How it Works:**

1. A client sends a request to the system.
2. A **Load Balancer** distributes the request to one of the available servers.
3. Multiple servers (nodes) process requests and may communicate with each other.
4. Data can be stored across multiple databases or replicated for reliability.
5. If one server fails, another server continues handling requests.

**When to Use:**

* For **large-scale web applications** and **microservices**.
* When high **scalability** and **availability** are required.
* For **cloud-native** applications running on multiple servers.
* In systems handling **high traffic** and **large amounts of data**.

**Simple Example:**

```text
Client
   |
Load Balancer
   |
-------------------------
|           |           |
Server 1   Server 2   Server 3
```

In this example, the **Load Balancer** distributes incoming requests among multiple servers, allowing the system to handle more users and continue running even if one server fails.


## 15. What is load balancing?


**Load Balancing** is the process of **distributing incoming client requests across multiple servers** so that no single server becomes overloaded. It improves **performance, scalability, and high availability** of an application.

**Key Features:**

* **Distributes Traffic** evenly across multiple servers.
* Improves **High Availability** by preventing a single point of failure.
* Supports **Scalability** by adding or removing servers easily.
* Increases **Performance** and reduces response time.
* Provides **Fault Tolerance** by redirecting traffic if a server fails.

**How it Works:**

1. A client sends a request to the application.
2. The **Load Balancer** receives the request.
3. It selects a healthy server using an algorithm such as **Round Robin**, **Least Connections**, or **IP Hash**.
4. The selected server processes the request and returns the response.
5. If a server becomes unavailable, the load balancer automatically redirects traffic to other healthy servers.

**When to Use:**

* For **high-traffic web applications**.
* In **Distributed Systems** and **Microservices Architectures**.
* When **high availability** and **fault tolerance** are required.
* For applications running on multiple servers or containers.

**Simple Example:**

```text id="l4d8wn"
          Client Requests
                 |
          Load Balancer
          /     |      \
    Server1  Server2  Server3
```

In this setup, the **Load Balancer** distributes requests among **Server1**, **Server2**, and **Server3**, ensuring that the workload is shared evenly.


**Load Balancing Algorithms:**
- **Round Robin:** Requests distributed sequentially
- **Least Connections:** Route to server with fewest active connections
- **Weighted Round Robin:** Assign weights based on server capacity
- **IP Hash:** Route based on client IP hash
- **Health Check:** Only route to healthy servers

**Types:**
- **Layer 4 (Transport):** Routes based on IP and port
- **Layer 7 (Application):** Routes based on HTTP content
- **Hardware Load Balancers:** Dedicated physical devices
- **Software Load Balancers:** Nginx, HAProxy, AWS ALB

```java
# Nginx load balancer configuration
upstream backend {
    server backend1.example.com weight=3;
    server backend2.example.com weight=2;
    server backend3.example.com weight=1;
}

server {
    listen 80;
    location / {
        proxy_pass http://backend;
    }
}
```

## 15. What is AWS load balancing and how it works?


**Application Load Balancer (ALB)** is an **AWS Layer 7 Load Balancer** that intelligently distributes **HTTP/HTTPS** requests across multiple backend targets such as **EC2 instances**, **ECS/EKS containers**, **IP addresses**, or **Lambda functions**. It improves **availability**, **scalability**, and **performance**.

**How It Works**

1. A **client** sends an **HTTP/HTTPS** request.
2. The request reaches the **ALB**.
3. The **Listener** receives the request on **port 80 (HTTP)** or **443 (HTTPS)**.
4. ALB checks the **Listener Rules** (based on **URL path**, **host name**, **headers**, etc.).
5. It selects the appropriate **Target Group**.
6. ALB performs **Health Checks** and forwards the request only to a **healthy target**.
7. The backend processes the request and sends the response back through the **ALB** to the client.

**Flow**

```text
                Client
                   |
                   v
        Application Load Balancer (ALB)
                   |
        -----------------------------
        |             |             |
   Target Group   Target Group   Target Group
   User Service   Order Service  Payment Service
        |             |             |
      EC2/ECS       EC2/ECS       EC2/ECS
```

**Key Features**

* **Layer 7 (Application Layer)** Load Balancer.
* Supports **HTTP** and **HTTPS** traffic.
* **Path-based Routing** (e.g., `/users`, `/orders`).
* **Host-based Routing** (e.g., `api.company.com`, `admin.company.com`).
* Performs **Health Checks** to send traffic only to healthy targets.
* Supports **SSL/TLS Termination**.
* Integrates with **Auto Scaling**, **ECS**, **EKS**, and **Lambda**.
* Supports **WebSocket** and **HTTP/2**.

**When to Use**

* **Spring Boot REST APIs**
* **Microservices Architecture**
* **Web Applications**
* Applications requiring **Path-based** or **Host-based Routing**
* Applications using **Auto Scaling**

**Real-Time Example**

Suppose an e-commerce application has three microservices:

* **`/users`** → **User Service**
* **`/orders`** → **Order Service**
* **`/payments`** → **Payment Service**

When a client requests:

```http
GET /orders/101
```

The **ALB** checks the URL path, identifies the **Order Service Target Group**, and forwards the request only to a **healthy Order Service instance**.



**Common Interview Follow-up Questions**

**1. Why is ALB called a Layer 7 Load Balancer?**
Because it works at the **Application Layer (Layer 7)** and routes requests based on **HTTP/HTTPS** content like **URL paths**, **host names**, and **headers**.

**2. What is the difference between ALB and NLB?**

| **ALB**                                            | **NLB**                                                  |
| -------------------------------------------------- | -------------------------------------------------------- |
| **Layer 7**                                        | **Layer 4**                                              |
| Supports **HTTP/HTTPS**                            | Supports **TCP/UDP**                                     |
| Supports **Path-based** and **Host-based Routing** | No application-level routing                             |
| Best for **Web Applications** and **REST APIs**    | Best for **High-performance**, **low-latency** workloads |

**3. What is a Listener in ALB?**
A **Listener** listens on a specific **port (80/443)**, receives incoming requests, and applies **Listener Rules** to route them.

**4. What is a Target Group?**
A **Target Group** is a group of backend resources (**EC2 instances**, **containers**, **IP addresses**, or **Lambda functions**) that receive traffic from the **ALB**.

**5. Can one ALB route traffic to multiple microservices?**
**Yes.** One **ALB** can route requests to multiple microservices using **Path-based** or **Host-based Routing**, with each service mapped to a different **Target Group**.



## **16. How do you handle rollback strategies?**

A **Rollback Strategy** is a process of **reverting an application to the last stable version** if a new deployment causes failures or unexpected issues. The goal is to **minimize downtime and restore service quickly**.

**Key Features:**

* **Quick Recovery** from failed deployments.
* **Minimal Downtime** for users.
* **Risk Reduction** during releases.
* Works well with **CI/CD pipelines** and **automated deployments**.
* Supports **versioned artifacts** and **deployment history**.

**How it Works:**

1. Deploy the new application version.
2. Continuously monitor **health checks**, logs, and metrics.
3. If errors or failures are detected, stop routing traffic to the new version.
4. **Rollback** to the previously stable version.
5. Investigate and fix the issue before redeploying.

**Common Rollback Approaches:**

* **Blue-Green Deployment:** Switch traffic back from the **Green** environment to the stable **Blue** environment.
* **Canary Deployment:** Roll back if the small group of users experiences issues.
* **Rolling Deployment:** Gradually replace the new instances with the old stable version.
* **Versioned Artifacts:** Redeploy the previous application version stored in the artifact repository.

**When to Use:**

* During **production deployments**.
* In **CI/CD pipelines** with automated releases.
* For **microservices** and **cloud-native** applications.
* Whenever high availability and business continuity are critical.

**Simple Kubernetes Rollback Example:**

```bash id="r6m9tx"
# Check deployment history
kubectl rollout history deployment/my-app

# Roll back to the previous version
kubectl rollout undo deployment/my-app
```


## 17. How do you manage database migrations?

**Database Migration** is the process of **applying version-controlled changes** to the database schema, such as creating or modifying tables, columns, indexes, or constraints, without losing existing data.

**Key Features:**

* **Version Control** for database changes.
* **Automated Execution** during application deployment.
* Ensures **schema consistency** across all environments.
* Supports **rollback** of failed changes.
* Commonly managed using tools like **Flyway** or **Liquibase**.

**How it Works:**

1. Create a migration script with the required database changes.
2. Store the script in the project repository.
3. During application startup or CI/CD deployment, the migration tool checks the database version.
4. Only **new migration scripts** are executed in sequence.
5. The tool records executed migrations to avoid running them again.

**When to Use:**

* When modifying the **database schema**.
* During **application deployments** in CI/CD pipelines.
* To keep **development, testing, and production databases synchronized**.
* In projects where multiple developers work on the same database.

**Simple Flyway Migration Example:**

```sql id="f8k2wp"
-- File: V1__create_employee_table.sql
CREATE TABLE employee (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50)
);
```

**Spring Boot Configuration Example:**

```properties id="m3x7qa"
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

```
**Step 1 → Add Dependency :: Maven**

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

**Step 2 → Configure Database :: application.yml**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: root

  flyway:
    enabled: true
```

**Step 3 → Create Migration Folder :: Create folder**

```txt
src/main/resources/db/migration
```

**Step 4 → Create SQL Migration File :: File name format**

```txt
V1__create_employee_table.sql
```

```txt
// Important:
V<version>__<description>.sql
```

**Step 5 → Add SQL :: V1__create_employee_table.sql**

```sql
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    salary DOUBLE
);
```

**Step 6 → Start Application :: When Spring Boot starts:**

```txt
Flyway automatically:
- checks migration history
- executes new scripts
- updates flyway_schema_history table
```

**Step 7 → Add New Migration :: Create new file:**

```txt
V2__add_email_column.sql
```

```sql
ALTER TABLE employee
ADD email VARCHAR(100);

Restart app → Flyway runs only V2.
```

**Internal Working**

```txt
Application Start
      ↓
Check flyway_schema_history
      ↓
Find pending migrations
      ↓
Execute SQL scripts
      ↓
Update migration history
```

**Generated Table :: Flyway creates:**

```txt
flyway_schema_history
```


## **18. How do you ensure zero downtime deployments?**

**Zero Downtime Deployment** is a deployment approach where a new application version is released **without interrupting service** for users. The old version continues serving requests until the new version is fully ready.

**Key Features:**

* **No Service Interruption** during deployment.
* **High Availability** for end users.
* Supports **Automatic Rollback** if issues are detected.
* Uses **Load Balancing** to redirect traffic.
* Commonly implemented with **Blue-Green**, **Canary**, or **Rolling Deployments**.

**How it Works:**

1. Deploy the new application version alongside the current one.
2. Run **health checks** and validate the new version.
3. Gradually or instantly switch traffic using a **Load Balancer**.
4. Monitor logs and application metrics.
5. If any problem occurs, automatically **rollback** to the previous stable version.

**Common Techniques:**

* **Blue-Green Deployment:** Keep two environments and switch traffic to the new one after validation.
* **Canary Deployment:** Release the new version to a small percentage of users before a full rollout.
* **Rolling Deployment:** Replace old instances with new ones gradually, one by one.

**When to Use:**

* For **production deployments** with high user traffic.
* In **microservices** and **cloud-native** applications.
* When **high availability** and **business continuity** are critical.
* In **CI/CD pipelines** with automated deployments.

**Simple Kubernetes Rolling Update Example:**

```yaml id="z4n8qy"
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  strategy:
    type: RollingUpdate
```

## 9. What is Autoscaling and How is it Implemented??

**Autoscaling** is a cloud and microservices feature that automatically **increases** or **decreases** the number of application instances based on traffic, CPU usage, memory usage, or custom metrics.

**Key Features**

* **Automatic Scaling** based on workload.
* Improves **Performance** and **Availability**.
* Reduces **Infrastructure Cost** by scaling down when traffic is low.
* Supports **Horizontal Scaling** (adding/removing instances).
* Ensures better **Resource Utilization**.

**How It Works**

1. **Monitor Metrics** such as CPU, Memory, Request Count, or Response Time.
2. Define **Scaling Rules** and thresholds.
3. When the threshold is exceeded, new instances are automatically created (**Scale Out**).
4. When load decreases, unnecessary instances are removed (**Scale In**).

**When to Use**

* Applications with **variable traffic**.
* **Microservices** and **Cloud-native** applications.
* High-availability systems.
* E-commerce, banking, and streaming platforms.

**Kubernetes Autoscaling Example**

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: user-service-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: user-service
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
```

**How the Above Example Works**

* Minimum **2 Pods** always run.
* Maximum **10 Pods** can be created.
* If **CPU Usage** exceeds **70%**, Kubernetes automatically adds more pods.
* When CPU usage drops, extra pods are removed.


**Common Interview Follow-up Questions**

**1. What is Horizontal Scaling vs Vertical Scaling?**

| **Horizontal Scaling**  | **Vertical Scaling**                |
| ----------------------- | ----------------------------------- |
| Add more instances/pods | Increase CPU/RAM of existing server |
| Better fault tolerance  | Limited by hardware                 |
| Used in Microservices   | Used in Monolithic applications     |

**2. What is HPA in Kubernetes?**

**Horizontal Pod Autoscaler (HPA)** automatically scales the number of pods based on metrics such as **CPU**, **Memory**, or **Custom Metrics**.

**3. What metrics can be used for Autoscaling?**

* **CPU Usage**
* **Memory Usage**
* **Request Count**
* **Response Time**
* **Custom Business Metrics**

**4. What are the benefits of Autoscaling?**

* **High Availability**
* **Better Performance**
* **Cost Optimization**
* **Efficient Resource Utilization**


## 10. What is Horizontal scaling?

**Horizontal Scaling (Scale Out)** means **adding more servers/instances** to distribute the application load instead of increasing the resources of a single server.

**Key Features**

* **Adds multiple application instances**
* **Uses a Load Balancer** to distribute requests
* **High Availability**
* **Fault Tolerance**
* **Better Scalability**
* **Supports Cloud Environments** (AWS, Azure, Kubernetes)

**How it Works**

1. Deploy multiple instances of the application.
2. Place a **Load Balancer** in front of them.
3. The Load Balancer distributes incoming requests across all instances.
4. If traffic increases, add more instances.
5. If one instance fails, traffic is routed to the remaining healthy instances.

**When to Use**

* **High-Traffic Applications**
* **Microservices**
* **Cloud-Native Applications**
* **E-commerce Websites**
* **Banking and Payment Systems**

**Architecture**

```text
              Client Requests
                     |
              Load Balancer
           /       |        \
      App-1     App-2     App-3
           \       |        /
              Shared Database
```

**Spring Boot Example**

Run multiple instances of the same application on different ports.

**Instance 1**

```properties
server.port=8081
```

**Instance 2**

```properties
server.port=8082
```

**Instance 3**

```properties
server.port=8083
```

Configure a **Load Balancer** (such as **Nginx**) to distribute requests.

```nginx
upstream springboot-app {
    server localhost:8081;
    server localhost:8082;
    server localhost:8083;
}

server {
    listen 80;

    location / {
        proxy_pass http://springboot-app;
    }
}
```

Now, requests sent to **port 80** are automatically distributed across all three Spring Boot instances.

**Advantages**

* **Handles more concurrent users**
* **Easy to add or remove servers**
* **No single point of failure**
* **Improves application availability**
* **Ideal for cloud deployments**



## 10. How do you implement auto-scaling, Horizontal and vertical scaling?

**Auto-Scaling** is the process of **automatically increasing or decreasing application resources** based on workload. It is commonly implemented using cloud platforms or **Kubernetes Horizontal Pod Autoscaler (HPA)**.

**Key Features:**

* **Automatic Resource Adjustment** based on CPU, memory, or custom metrics.
* Improves **Performance** during traffic spikes.
* Optimizes **Cost** by reducing unused resources.
* Provides **High Availability** and better user experience.

**How it Works:**

1. The system continuously monitors metrics such as **CPU** or **Memory Usage**.
2. If usage exceeds a defined threshold, additional instances or containers are created.
3. When the load decreases, extra instances are automatically removed.
4. A **Load Balancer** distributes traffic across the available instances.

**Horizontal Scaling (Scale Out / Scale In):**

*  Add or remove **multiple servers or containers**.
* **Example:** Increase Pods from **3 to 6** in Kubernetes.
* **Best For:** **Microservices**, cloud applications, and distributed systems.
* **Advantage:** Better fault tolerance and almost unlimited scalability.

**Vertical Scaling (Scale Up / Scale Down):**

*  Increase or decrease the **CPU, RAM, or storage** of an existing server.
* **Example:** Upgrade a server from **4 GB RAM to 16 GB RAM**.
* **Best For:** Traditional applications or databases that cannot be easily distributed.
* **Limitation:** Has a hardware limit and may require downtime.

| **Scaling Type**       | **How it Works**                       | **Example**          |
| ---------------------- | -------------------------------------- | -------------------- |
| **Horizontal Scaling** | Add or remove servers/containers.      | 3 Pods → 6 Pods      |
| **Vertical Scaling**   | Increase or decrease server resources. | 4 GB RAM → 16 GB RAM |

**When to Use:**

* **Auto-Scaling:** For applications with changing traffic patterns.
* **Horizontal Scaling:** For cloud-native, microservices, and highly available systems.
* **Vertical Scaling:** For monolithic applications or databases requiring more resources.

**Simple Kubernetes Auto-Scaling Example:**

```bash id="a7k5mq"
# Create Horizontal Pod Autoscaler
kubectl autoscale deployment my-app \
  --cpu-percent=70 \
  --min=2 \
  --max=10
```

This configuration automatically keeps CPU usage around **70%** by scaling the number of Pods between **2 and 10**.


## 11. Blue-Green deployment strategy?

**Blue-Green Deployment** is a deployment technique where two identical production environments are maintained:

* **Blue Environment** = Current live version serving users.
* **Green Environment** = New version deployed and tested.

Once the new version is verified, traffic is switched from **Blue** to **Green** with minimal or no downtime.

**Key Features**

* **Zero or Near-Zero Downtime**
* **Quick Rollback**
* **Reduced Deployment Risk**
* **High Availability**
* **Production Testing Before Release**

**How It Works**

1. **Blue** environment is serving live traffic.
2. Deploy the new application version to **Green** environment.
3. Perform testing and validation on Green.
4. Switch traffic from Blue to Green using a **Load Balancer** or **Ingress Controller**.
5. If issues occur, route traffic back to Blue immediately.

**When to Use**

* **Production deployments** requiring high availability.
* Applications where **downtime is unacceptable**.
* **Microservices** and **Cloud-Native** applications.
* Systems requiring **fast rollback** capabilities.

**Example Flow**

```text
Before Deployment

Users
   |
 Blue (v1 - Live)

After Deployment

Users
   |
 Green (v2 - Live)

Blue remains available for rollback.
```

**Kubernetes Example**

**Blue Deployment**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-blue
spec:
  replicas: 3
```

**Green Deployment**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-green
spec:
  replicas: 3
```

**Service Initially Points to Blue**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    version: blue
```

**Switch Traffic to Green**

```yaml
spec:
  selector:
    version: green
```

**Advantages**

* **No Downtime Deployments**
* **Instant Rollback**
* Safer releases with pre-production validation.
* Better user experience during deployments.

**Disadvantages**

* Requires **double infrastructure** during deployment.
* Higher infrastructure cost.
* Database changes must be handled carefully.


**Common Interview Follow-up Questions**

**1. What is the biggest advantage of Blue-Green Deployment?**

**Zero Downtime** and **Instant Rollback**.

**2. How is traffic switched between Blue and Green?**

Using a **Load Balancer**, **Reverse Proxy**, **DNS Switch**, or **Kubernetes Service/Ingress**.

**3. What is the difference between Blue-Green and Canary Deployment?**

| **Blue-Green**               | **Canary**                                    |
| ---------------------------- | --------------------------------------------- |
| Switches all traffic at once | Releases to a small percentage of users first |
| Simple rollback              | Gradual rollout                               |
| Requires two environments    | Requires traffic splitting                    |

**4. What challenge exists with databases in Blue-Green Deployment?**

**Database schema changes** must be backward compatible because both Blue and Green environments may need to work with the same database during the transition.



## 12. What is Rate Limiting and how does it work? Where do you implement it?

**Rate Limiting** is a technique used to **control the number of requests** a client can make to a service within a specific time period. It helps protect the system from **overload, abuse, and DDoS attacks**.

**Key Features:**

* Prevents **API abuse** and excessive traffic.
* Protects against **DDoS and brute-force attacks**.
* Improves **system stability** and **resource utilization**.
* Ensures **fair usage** among all users.
* Commonly implemented using **API Gateways**, **Load Balancers**, or **Redis**.

**How it Works:**

1. A client sends a request to the application.
2. The **Rate Limiter** checks how many requests the client has already made within the configured time window.
3. If the request count is below the limit, the request is processed.
4. If the limit is exceeded, the server rejects the request and returns **HTTP 429 (Too Many Requests)**.

**Common Rate Limiting Algorithms:**

* **Fixed Window Counter:** Allows a fixed number of requests per time window.
* **Sliding Window:** Tracks requests over a moving time window for smoother limiting.
* **Token Bucket:** Tokens are added at a fixed rate, and each request consumes a token.
* **Leaky Bucket:** Processes requests at a constant rate, smoothing traffic spikes.

**Where Do You Implement It?**

* **API Gateway** (Preferred) – e.g., Spring Cloud Gateway, Kong, NGINX.
* **Load Balancer** – To filter excessive requests before they reach the application.
* **Application Layer** – Using libraries such as **Bucket4j** or **Resilience4j**.
* **Distributed Cache (Redis)** – To maintain request counts across multiple application instances.

**When to Use:**

* For **public APIs** and microservices.
* To prevent **brute-force login attempts**.
* To protect systems from **traffic spikes** and malicious users.
* In **high-traffic distributed systems** and cloud applications.

**Simple Spring Boot + Bucket4j Example:**

```java id="t8p4xk"
Bucket bucket = Bucket.builder()
    .addLimit(limit -> limit.capacity(100)
    .refillGreedy(100, Duration.ofMinutes(1)))
    .build();

if (bucket.tryConsume(1)) {
    return "Request Allowed";
} else {
    return "HTTP 429 - Too Many Requests";
}
```


# ✅ 29. Java Testing

##  1. What is unit testing in Java?
Unit testing is a software testing technique where individual components or modules of a software application are tested in isolation
It helps ensure that each unit of code performs as expected and catches bugs early in developmentjava

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

@Test
public void testAdd() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.add(2, 3));
}
```

## 2. What is JUnit?
JUnit is a popular open-source testing framework for Java that provides annotations and assertions for writing and running unit testsjava

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("hello"));
    }
}
```

## 3. What are the annotations used in JUnit?
Common JUnit annotations include @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @DisplayName, 

```java
@Disabledjava
public class LifecycleTest {
    @BeforeAll
    static void setupAll() { /* runs once before all tests */ }
    
    @BeforeEach
    void setup() { /* runs before each test */ }
    
    @Test
    @DisplayName("Test addition operation")
    void testAddition() { /* test method */ }
    
    @AfterEach
    void tearDown() { /* runs after each test */ }
    
    @AfterAll
    static void tearDownAll() { /* runs once after all tests */ }
}
```

## 4. What is TestNG?

TestNG is a testing framework inspired by JUnit but with more powerful features like data providers, parallel execution, and flexible test configurationjava

```java
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestNGExample {
    @DataProvider
    public Object[][] testData() {
        return new Object[][]{{1, 2, 3}, {4, 5, 9}};
    }
    
    @Test(dataProvider = "testData")
    public void testAdd(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }
}
```

## 5. What is the difference between JUnit and TestNG?
JUnit is simpler and widely adopted, while TestNG offers more advanced features like data providers, parallel execution, and better test configurationjava

```java
// JUnit 5
@ParameterizedTest
@ValueSource(strings = {"hello", "world"})
void testWithJUnit(String word) {
    assertNotNull(word);
}

// TestNG
@Test(dataProvider = "words")
void testWithTestNG(String word) {
    assertNotNull(word);
}
```

## 6. What is mocking in Java testing?
Mocking is creating fake objects that simulate the behavior of real objects to isolate the unit being tested from its dependenciesjava

```java
// Using Mockito
@Mock
private UserRepository userRepository;

@Test
void testGetUser() {
    User mockUser = new User("John", "john@email.com");
    when(userRepository.findById(1L)).thenReturn(mockUser);
    
    User result = userService.getUser(1L);
    assertEquals("John", result.getName());
}
```

## 7. What is Mockito?
Mockito is a popular Java mocking framework that allows you to create mock objects and define their behavior for testingjava
import static org.mockito.Mockito.*

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void testSendWelcomeEmail() {
        userService.registerUser("john@email.com");
        verify(emailService).sendEmail("john@email.com", "Welcome!");
    }
}
```

## 8. What is integration testing?
Integration testing verifies that different modules or services work correctly when integrated togetherjava

```java
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCreateUser() {
        User user = new User("John", "john@email.com");
        ResponseEntity<User> response = restTemplate.postForEntity("/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
```

## 9. What is test-driven development (TDD)?
TDD is a development approach where you write tests first, then write the minimum code to make tests pass, then refactorjava

```java
// Step 1: Write failing test
@Test
void testCalculateArea() {
    Circle circle = new Circle(5);
    assertEquals(78.54, circle.calculateArea(), 0.01);
}

// Step 2: Write minimum code to pass
public class Circle {
    private double radius;
    
    public Circle(double radius) { this.radius = radius; }
    
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

## 10. What is behavior-driven development (BDD)?
BDD extends TDD by writing tests in natural language that describes the behavior of the application from user's perspectivejava

```java
// Using Cucumber with Java
@Given("a user with email {string}")
public void aUserWithEmail(String email) {
    user = new User(email);
}

@When("the user logs in")
public void theUserLogsIn() {
    loginResult = authService.login(user);
}

@Then("the login should be successful")
public void theLoginShouldBeSuccessful() {
    assertTrue(loginResult.isSuccess());
}
```