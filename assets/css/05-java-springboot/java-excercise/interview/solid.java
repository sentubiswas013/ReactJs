// ============================================================
// SOLID Principles (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {

        // SRP
        Invoice invoice = new Invoice();
        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        // OCP
        Discount discount = new PercentageDiscount();
        System.out.println("Discount: " + discount.calculate(1000));

        // LSP
        Bird bird = new Sparrow();
        bird.fly();

        // ISP
        Worker worker = new Developer();
        worker.work();

        // DIP
        NotificationService service = new NotificationService(new EmailSender());
        service.send("Hello SOLID");
    }
}


// ============================================================
// 1. SRP (Single Responsibility Principle)
// One class = one responsibility
// ============================================================
class Invoice {
    int amount = 1000;
}

class InvoicePrinter {
    void print(Invoice invoice) {
        System.out.println("Printing invoice: " + invoice.amount);
    }
}


// ============================================================
// 2. OCP (Open/Closed Principle)
// Open for extension, closed for modification
// ============================================================
interface Discount {
    double calculate(double amount);
}

class PercentageDiscount implements Discount {
    public double calculate(double amount) {
        return amount * 0.1;
    }
}

class FlatDiscount implements Discount {
    public double calculate(double amount) {
        return amount - 100;
    }
}


// ============================================================
// 3. LSP (Liskov Substitution Principle)
// Child should replace parent without breaking behavior
// ============================================================
class Bird {
    void fly() {
        System.out.println("Flying");
    }
}

class Sparrow extends Bird {
    void fly() {
        System.out.println("Sparrow flying");
    }
}


// ============================================================
// 4. ISP (Interface Segregation Principle)
// Don't force classes to implement unused methods
// ============================================================
interface Worker {
    void work();
}

class Developer implements Worker {
    public void work() {
        System.out.println("Writing code");
    }
}


// ============================================================
// 5. DIP (Dependency Inversion Principle)
// Depend on abstraction, not concrete class
// ============================================================
interface MessageSender {
    void sendMessage(String msg);
}

class EmailSender implements MessageSender {
    public void sendMessage(String msg) {
        System.out.println("Email sent: " + msg);
    }
}

class NotificationService {
    private MessageSender sender;

    NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    void send(String msg) {
        sender.sendMessage(msg);
    }
}