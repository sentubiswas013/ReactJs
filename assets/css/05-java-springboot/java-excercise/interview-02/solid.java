// ============================================================
// SOLID Principles - Single File (Interview Ready)
// ============================================================

class Main {

    public static void main(String[] args) {

        // ================= SRP =================
        Invoice invoice = new Invoice(1000);
        InvoicePrinter printer = new InvoicePrinter();
        InvoiceRepository repository = new InvoiceRepository();

        printer.print(invoice);
        repository.save(invoice);

        // ================= OCP =================
        Discount discount = new PercentageDiscount();
        System.out.println("Discount: " + discount.calculate(1000));

        Discount festival = new FestivalDiscount();
        System.out.println("Festival Discount: " + festival.calculate(1000));

        // ================= LSP =================
        Bird sparrow = new Sparrow();
        sparrow.move();

        Bird penguin = new Penguin();
        penguin.move();

        // ================= ISP =================
        Workable developer = new Developer();
        developer.work();

        Eatable robot = new Robot();
        robot.eat();

        // ================= DIP =================
        NotificationService emailService = new NotificationService(new EmailSender());
        emailService.send("Email Notification");

        NotificationService smsService = new NotificationService(new SmsSender());
        smsService.send("SMS Notification");
    }
}

// ============================================================
// 1. SRP (Single Responsibility Principle)
// ============================================================
// Bad Design: Invoice doing too many things ❌
// Good Design: Split responsibilities ✅

class Invoice {
    private int amount;

    public Invoice(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Printing invoice: " + invoice.getAmount());
    }
}

class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Saving invoice to DB: " + invoice.getAmount());
    }
}


// ============================================================
// 2. OCP (Open/Closed Principle)
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

class FestivalDiscount implements Discount {
    public double calculate(double amount) {
        return amount * 0.2;
    }
}


// ============================================================
// 3. LSP (Liskov Substitution Principle)
// ============================================================
// Bad Design ❌
// Penguin cannot fly → violates LSP

// Good Design ✅

abstract class Bird {
    abstract void move();
}

class Sparrow extends Bird {
    public void move() {
        System.out.println("Sparrow flying");
    }
}

class Penguin extends Bird {
    public void move() {
        System.out.println("Penguin swimming");
    }
}


// ============================================================
// 4. ISP (Interface Segregation Principle)
// ============================================================
// Bad ❌
// interface Worker { work(), eat() }

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Developer implements Workable {
    public void work() {
        System.out.println("Writing code");
    }
}

class Robot implements Eatable {
    public void eat() {
        System.out.println("Charging battery");
    }
}


// ============================================================
// 5. DIP (Dependency Inversion Principle)
// ============================================================

interface MessageSender {
    void sendMessage(String msg);
}

class EmailSender implements MessageSender {
    public void sendMessage(String msg) {
        System.out.println("Email sent: " + msg);
    }
}

class SmsSender implements MessageSender {
    public void sendMessage(String msg) {
        System.out.println("SMS sent: " + msg);
    }
}

class NotificationService {
    private MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void send(String msg) {
        sender.sendMessage(msg);
    }
}