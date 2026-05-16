import java.util.ArrayList;
import java.util.List;

class DesignPattern {
	public static void main (String[] args) {
		
		// 1. Singleton Pattern ==========================================



		// 2. Factory Pattern ============================================



		// 3. Observer Pattern ===========================================



		// 4. Strategy pattern ===========================================
		ShoppingCart cart = new ShoppingCart();

        // Pay using Credit Card
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(500);

        // Pay using PayPal
        cart.setPaymentStrategy(new paypal());
        cart.checkout(1200);


		// 5. Adapter pattern ============================================
    

		// 6. Builder Pattern ============================================



		// 7. Prototype Pattern ==========================================

		
    }
}

// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking) Ex: Logger, Configuration Manager
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







// Output: 
// Singleton instance created: 1530262698
// Thread-2 got instance: 1530262698
// Thread-1 got instance: 1530262698


// ============================================================
// 2. Factory Pattern (Best Practice using Enum) : Ex: Payment System
// **Factory Pattern** is a creational design pattern used to: Create objects without exposing object creation logic to the client. Instead of creating objects directly using **new**, the client asks the factory to create the required object.

// Flow:: Client  -----> Factory (Creates CARD / UPI object) ----> Required Object ("Give me CARD payment object")

// Rules to create :: Payment:: 
// step 0: Define an enum for the Payment types 
// Step 1: Interface or Abstract Class :: payment interface with a method pay()
// Step 2: Concrete Implementations :: CardPayment, UpiPayment classes that implement the Payment interface
// Step 3: Factory Class with a static method to create objects based on input :: PaymentFactory with a static method getPayment(String type)

// Advantages   : -----------
// Loose coupling
// Hides object creation logic
// Easy maintenance
// Easy to extend

// 👉 Real use : -----------
// - Payment systems
// - Notification services
// - Logger creation
// - Database drivers
// - Spring BeanFactory
// ============================================================






// Output:
// UPI payment


// ============================================================
// 3. Observer Pattern (Best Practice using Enum) : Ex: News Agency
// **Observer pattern** (Behavioral Design Patterns) is defines a one-to-many dependency between objects. When one object changes state, all dependent objects are notified and updated automatically.

// Rules to create :: (exmaple News Agency):
// News agency - > News Channel -> Observer Pattern

// 1. Create an Observer interface with an update() method.
// 2. Create a Subject class that maintains a list of observers and has methods to attach/detach observers and notify them of changes.
// 3. Create concrete Observer classes that implement the Observer interface and define the update() method to react to changes in the Subject.

// Flow:
// News Changed  ---> Publisher Notifies Everyone ---> Subscribers Receive Update  ---> When to use Observer Pattern:

// 👉 Real use:
// - YouTube Notifications
// - News Channel System, 
// - Stock Market Apps
// - Kafka / RabbitMQ Consumers
// ============================================================






// Output
// CNN received: Java is awesome!
// BBC received: Java is awesome!
// CNN received: Observer pattern in action!
// BBC received: Observer pattern in action!


// ============================================================
// 4. Strategy Pattern Example: Notification System
// Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

// Real-time Example: Different ways to send notifications (Email, SMS, Push)
// We can add new notification types without modifying existing code.
// Open for extension, closed for modification.
// ============================================================
class StrategyPatternDemo {
	public static void main (String[] args) {
		ShoppingCart cart = new ShoppingCart();

        // Pay using Credit Card
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(500);

        // Pay using PayPal
        cart.setPaymentStrategy(new paypal());
        cart.checkout(1200);
    }
}

interface PaymentStrategy {
	void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
	public void pay(double amount) {
		System.out.println("Credit paid " + amount);
	}
}
class paypal implements PaymentStrategy {
	public void pay(double amount) {
		System.out.println("Paypal paid " + amount);
	}
}
class ShoppingCart {
	private PaymentStrategy paymentStrategy;

	public void setPaymentStrategy(PaymentStrategy strategy) {
		this.paymentStrategy = strategy;
	}

	public void checkout(double amount) {
		paymentStrategy.pay(amount);
	}
}





// Output:
// Paid $500.0 using Credit Card
// Paid $1200.0 using PayPal


// ============================================================
// 5. Adapter pattern: Ex: Media Player
// Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.
// ============================================================
interface MediaPlayer {
	void play(String audioType, String fileName);
}

class AdvancedMediaPlayer {
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file " + fileName);
	}
	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file " + fileName);
	}
}

class MediaAdapter implements MediaPlayer {
	private AdvancedMediaPlayer advacedPlayer;

	public MediaAdapter(String fileName) {
		advacedPlayer= new AdvancedMediaPlayer();
	} 

	public void play(String audioType, String fileName) {
		if(audioType.equalsIgnoreOnce("vlc")) {
			advacedPlayer.playVlc();
		} else if(audioType.equalsIgnoreOnce("mp4")) {
			advacedPlayer.playMp4()
		} else {
			System.out.println("Invalid media type.")
		}
	}
}



// Output::
// Playing vlc file: movie.vlc
// Playing mp4 file: video.mp4


// ============================================================
// 6. Builder Pattern (Immutable Object - BEST PRACTICE) : Ex: Employee Object Creation
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







// Output
// Employee{id=1, name='Rahul'}


// ============================================================
// 7. Prototype Pattern (Cloning) :Ex Student Object Creation
// Prototype Pattern is a Creational Design Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.

// Rules to create Prototype Pattern:
// 1. Implement the Cloneable interface in the class you want to clone.
// 2. Override the clone() method to return a copy of the object.
// 3. Use the clone() method to create new objects by copying existing ones.    

// When to use Prototype Pattern:
// 1. When object creation is expensive and you want to create new objects by copying existing ones.
// 2. When you want to hide the creation logic from the client code.
// ============================================================







// Output:
// John
// John