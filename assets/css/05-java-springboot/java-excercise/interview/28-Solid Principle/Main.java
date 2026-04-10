import java.util.ArrayList;
import java.util.List;


// ─────────────────────────────────────────────
// MAIN - Run all principles
// ─────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {

        // S - Single Responsibility
        System.out.println("=== S: Single Responsibility ===");
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Book", 12.0));
        orders.add(new Order("Pen", 3.0));
        double total = new Invoice().calculateTotal(orders);
        new InvoicePrinter().print(total);

        // O - Open/Closed
        System.out.println("\n=== O: Open/Closed ===");
        Discount discount = new TenPercentDiscount(); // swap without changing Invoice
        System.out.println("After Discount: $" + discount.apply(total));

        // L - Liskov Substitution
        System.out.println("\n=== L: Liskov Substitution ===");
        List<Shape> shapes = List.of(new Circle(5), new Rectangle(4, 6));
        shapes.forEach(s -> System.out.println(s.getClass().getSimpleName() + " area: " + String.format("%.2f", s.area())));

        // I - Interface Segregation
        System.out.println("\n=== I: Interface Segregation ===");
        new AllInOnePrinter().print();
        new AllInOnePrinter().scan();
        new SimplePrinter().print();

        // D - Dependency Inversion
        System.out.println("\n=== D: Dependency Inversion ===");
        new OrderProcessor(new EmailNotification()).process("Laptop");
        new OrderProcessor(new SMSNotification()).process("Phone"); // swap easily ✅
    }
}

// ─────────────────────────────────────────────
// S - Single Responsibility: Each class = one job
// ─────────────────────────────────────────────
class Order {
    String item;
    double price;
    Order(String item, double price) { this.item = item; this.price = price; }
}

class Invoice {
    double calculateTotal(List<Order> orders) {
        return orders.stream().mapToDouble(o -> o.price).sum();
    }
}

class InvoicePrinter {
    void print(double total) { System.out.println("Invoice Total: $" + total); }
}

// ─────────────────────────────────────────────
// O - Open/Closed: Add new types without changing existing code
// ─────────────────────────────────────────────
interface Discount {
    double apply(double price);
}

class NoDiscount implements Discount {
    public double apply(double price) { return price; }
}

class TenPercentDiscount implements Discount {
    public double apply(double price) { return price * 0.9; }
}

// ─────────────────────────────────────────────
// L - Liskov Substitution: Subclass must honor parent contract
// ─────────────────────────────────────────────
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    public double area() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    public double area() { return w * h; }
}

// ─────────────────────────────────────────────
// I - Interface Segregation: Don't force unused methods
// ─────────────────────────────────────────────
interface Printable  { void print(); }
interface Scannable  { void scan(); }

class AllInOnePrinter implements Printable, Scannable {
    public void print() { System.out.println("Printing..."); }
    public void scan()  { System.out.println("Scanning..."); }
}

class SimplePrinter implements Printable {
    public void print() { System.out.println("Simple Print..."); } // no scan needed ✅
}

// ─────────────────────────────────────────────
// D - Dependency Inversion: Depend on abstraction, not concrete class
// ─────────────────────────────────────────────
interface NotificationService {
    void send(String message);
}

class EmailNotification implements NotificationService {
    public void send(String message) { System.out.println("Email: " + message); }
}

class SMSNotification implements NotificationService {
    public void send(String message) { System.out.println("SMS: " + message); }
}

class OrderProcessor {
    private NotificationService notifier; // depends on interface ✅
    OrderProcessor(NotificationService notifier) { this.notifier = notifier; }
    void process(String item) { notifier.send("Order placed for: " + item); }
}


