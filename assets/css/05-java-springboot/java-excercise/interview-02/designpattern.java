import java.util.ArrayList;
import java.util.List;

// ============================================================
// The Singleton Pattern is a Creational Design Pattern that ensures a class has only one instance throughout the application and provides a global access point to that instance.

// Make the constructor private so no other class can create an object.
// Create a static instance of the class.
// Provide a public static method (usually getInstance()) to return the single instance.
// ============================================================

class Singleton {
    private static volatile Singleton instance;
    private Singleton() {
        // System.out.println("Singleton instance created: " + this.hashCode());
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
// The Factory Pattern is a Creational Design Pattern that provides a centralized way to create objects without exposing the object creation logic to the client. Instead of using new directly, the client asks the factory to create the required object.

// Flow:: Client  -----> Factory (Creates CARD / UPI object) ----> Required Object ("Give me CARD payment object")
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
// The Observer Pattern is a Behavioral Design Pattern in which one object (Subject) automatically notifies multiple dependent objects (Observers) whenever its state changes. It establishes a one-to-many relationship between objects.

// Rules to create :: (exmaple News Agency):
// News agency - > News Channel -> Observer Pattern
// ============================================================
// import java.util.*;

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
// The Strategy Pattern is a Behavioral Design Pattern that defines a family of algorithms, encapsulates each one in a separate class, and allows them to be interchanged at runtime without changing the client code.

// Real-time Example: to send email notifications
// ============================================================

// Strategy Interface
interface NotificationStrategy {
    void send(String message);
}

// Concrete Strategy 1
class EmailNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

// Concrete Strategy 2
class SMSNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Concrete Strategy 3
class PushNotification implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH Notification: " + message);
    }
}

// Context Class
class NotificationService {
    private NotificationStrategy strategy;

    // Set strategy dynamically
    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    // Execute selected strategy
    public void notifyUser(String message) {
        strategy.send(message);
    }
}

// Main Class
class StrategyPatternDemo {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        // Send Email
        service.setStrategy(new EmailNotification());
        service.notifyUser("Your order has been placed!");

        // Send SMS
        service.setStrategy(new SMSNotification());
        service.notifyUser("OTP is 4589");

        // Send Push Notification
        service.setStrategy(new PushNotification());
        service.notifyUser("Flash sale starts in 10 minutes!");
    }
}
// Output:
// Sending EMAIL: Your order has been placed!
// Sending SMS: OTP is 4589
// Sending PUSH Notification: Flash sale starts in 10 minutes!

// ============================================================
// 5. Adapter pattern: Ex: Media Player
// The Adapter Pattern is a Structural Design Pattern that allows two incompatible interfaces to work together by acting as a bridge between them. It converts the interface of one class into another interface that the client expects.
// ============================================================
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
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);

        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);

        } else {
            System.out.println("Invalid media type");
        }
    }
}

// Main class
class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer vlcPlayer = new MediaAdapter("vlc");
        vlcPlayer.play("vlc", "movie.vlc");

        MediaPlayer mp4Player = new MediaAdapter("mp4");
        mp4Player.play("mp4", "video.mp4");
    }
}

// Output::
// Playing vlc file: movie.vlc
// Playing mp4 file: video.mp4

// ============================================================
// The Builder Pattern is a Creational Design Pattern used to construct complex objects step by step. It is especially useful when an object has many optional parameters and you want to avoid multiple constructors.
// Real Example: Employee Object Creation
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
// The Prototype Pattern is a Creational Design Pattern that creates new objects by copying (cloning) an existing object instead of creating a new one from scratch. It is useful when object creation is expensive or complex.

// Real-time Example: Student Object Creation
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