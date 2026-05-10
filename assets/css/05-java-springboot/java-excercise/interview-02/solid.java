// ============================================================
// 1. SRP (Single Responsibility Principle)
// One class should have only one responsibility.
// ============================================================
// Bad Design: Invoice doing too many things ❌
class UserService1 {

    public void registerUser() {
        System.out.println("User registered");
    }

    public void sendEmail() {
        System.out.println("Email sent");
    }
}
// Problem:
// Handles user logic + email logic

// Good Design: Split responsibilities ✅
class UserService {
    public void registerUser() {
        System.out.println("User registered");
    }
}

class EmailService {
    public void sendEmail() {
        System.out.println("Email sent");
    }
}


// ============================================================
// 2. OCP (Open/Closed Principle)
// Open for extension, closed for modification
// ============================================================
// bad Impplementation: Adding new payment type requires modifying existing code ❌
class Payment1 {
    void pay(String type) {
        if(type.equals("UPI")) {}
        else if(type.equals("CARD")) {}
    }
}

// Good Implementation: Easy to extend without modifying existing code ✅
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Paid by Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paid by UPI");
    }
}

class PaymentService {
    public void processPayment(Payment payment) {
        payment.pay();
    }
}

class OpenClosedDemo {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        Payment p1 = new CardPayment();
        service.processPayment(p1);

        Payment p2 = new UpiPayment();
        service.processPayment(p2);
    }
}

// Output:
// Paid by Card
// Paid by UPI


// ============================================================
// 3. LSP (Liskov Substitution Principle)
// A child class should be able to replace its parent class without breaking the program behavior.
// ============================================================

// Bad implementation: Ostrich can't fly, violates LSP ❌
class Bird1 {
    void fly() {}
}

class Ostrich extends Bird1 {
    void fly() { throw new RuntimeException(); }
}

// Good implementation: Separate flying and non-flying birds ✅
class Bird {
    public void fly() {
        System.out.println("Bird can fly");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

class LiskovSubstitutionDemo {
    public static void main(String[] args) {
        Bird b = new Bird();
        b.fly();

        Sparrow s = new Sparrow();
        s.fly();

        Bird b2 = new Sparrow(); // Runtime Polymorphism
        b2.fly();
    }
}

// Output:
// Bird can fly
// Sparrow can fly
// Sparrow can fly


// ============================================================
// 4. ISP (Interface Segregation Principle)
// Many specific interfaces are better than one general interface
// ============================================================
// Bad Design: Worker interface has both work and eat methods ❌
interface Worker {
    void work();
    void eat();
}

// Good Design: Separate interfaces for different responsibilities ✅
class InterfaceSegregationDemo {
    public static void main(String[] args) {
        Workable w1 = new Human();
        w1.work();

        Eatable e1 = new Human();
        e1.eat();

        Workable w2 = new Robot();
        w2.work();
    }
}

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

// Output:
// Human working
// Human eating
// Robot working


// ============================================================
// 5. DIP (Dependency Inversion Principle)
// Depend on abstractions, not concrete implementations
// ============================================================
// Bad Design: Laptop directly depends on WiredMouse ❌
class Keyboard2 {
    public void type() {
        System.out.println("Typing with keyboard");
    }
}

class Computer2 {
    private Keyboard2 keyboard = new Keyboard2();
    public void start() {
        keyboard.type();
    }
}

class MainTest {
        public static void main(String[] args) {
        Computer2 computer = new Computer2();
        computer.start();
    }
}

// Good Design: Laptop depends on Mouse interface, not specific implementation ✅
// Step 1: Create Interface
interface InputDevice {
    void type();
}

// Step 2: Implement Interface
class Keyboard implements InputDevice {
    public void type() {
        System.out.println("Typing with keyboard");
    }
}
class WirelessKeyboard implements InputDevice {
    public void type() {
        System.out.println("Typing with wireless keyboard");
    }
}

// Step 3: High-Level Class Depends on Interface
class Computer {
    private InputDevice inputDevice;

    // Dependency Injection
    public Computer(InputDevice inputDevice) {
        this.inputDevice = inputDevice;
    }

    public void start() {
        inputDevice.type();
    }
}

// Step 4: Main Class
class DependencyInversionDemo {
    public static void main(String[] args) {
        InputDevice device = new WirelessKeyboard();
        Computer computer = new Computer(device);

        computer.start();
    }
}

//Output:
// Typing with wireless keyboard