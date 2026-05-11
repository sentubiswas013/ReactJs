// ============================================================
// 1. SRP (Single Responsibility Principle) : Ex User Registration
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
class RegisterService {
    public void registerUser() {
        System.out.println("User registered");
    }
}

class SendEmailService {
    public void sendEmail() {
        System.out.println("Email sent");
    }
}


// ============================================================
// 2. OCP (Open/Closed Principle) : Ex Payment System
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
// 3. LSP (Liskov Substitution Principle) : Ex Bird and Ostrich Example
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
// 4. ISP (Interface Segregation Principle) Ex: Worker Interface 
// Many specific interfaces are better than one general interface
// ============================================================
// Bad Design: Worker interface has both work and eat methods ❌
interface Worker {
    void work();
    void eat();
}

// Good Design: Separate interfaces for different responsibilities ✅
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

// Output:
// Human working
// Human eating
// Robot working


// ============================================================
// 5. DIP (Dependency Inversion Principle) - Ex: Notification Service
// Depend on abstractions, not concrete implementations
// ============================================================
// Bad Design: Laptop directly depends on WiredMouse ❌
class EmailService1 {
    void send() {
        System.out.println("Email Sent");
    }
}

class NotificationService1 {
    private EmailService1 emailService = new EmailService1();
    void notifyUser() {
        emailService.send();
    }
}

// Good Design: Laptop depends on Mouse interface, not specific implementation ✅
// Step 1: Create Interface
interface MessageService {
    void send();
}

// Step 2: Implement Interface
class EmailService implements MessageService {
    public void send() {
        System.out.println("Email Sent");
    }
}

class SmsService implements MessageService {
    public void send() {
        System.out.println("SMS Sent");
    }
}

// Step 3: High-Level Class Depends on Interface
class NotificationService {
    private MessageService messageService;
    NotificationService(MessageService messageService) {
        this.messageService = messageService;
    }

    void notifyUser() {
        messageService.send();
    }
}

// Step 4: Main Class
class Main {
    public static void main(String[] args) {
        MessageService service = new EmailService();
        NotificationService notification = new NotificationService(service);

        notification.notifyUser();
    }
}

//Output:
// Typing with wireless keyboard