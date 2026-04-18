// ============================================================
// 1. SRP (Single Responsibility Principle)
// One class should have only one responsibility.
// ============================================================
// Bad Design: Invoice doing too many things ❌
class UserService {

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

public class Main {
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
// Child class should replace parent class without breaking code.
// ============================================================
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

public class Main {
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
// Child class should replace parent class without breaking code.
// ============================================================
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
// Human working
// Human eating
// Robot working


// ============================================================
// 5. DIP (Dependency Inversion Principle)
// Depend on abstractions, not concrete implementations
// ============================================================

interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class OrderService {
    private Payment payment;

    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() {
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {
        Payment payment = new CardPayment();   // Inject dependency
        OrderService orderService = new OrderService(payment);

        orderService.placeOrder();
    }
}

//Output:
// Card payment