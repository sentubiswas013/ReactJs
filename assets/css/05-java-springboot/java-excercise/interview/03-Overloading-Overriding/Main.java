// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Payment p = new Payment();
        p.pay(100);               // Overloading
        p.pay(100, "Card");       // Overloading

        UpiPayment upi = new UpiPayment();
        upi.pay(100);             // Overriding

        // Real-world
        Logger log = new Logger();
        log.log("Server started");
        log.log("Server started", "ERROR");

        FileLogger flog = new FileLogger();
        flog.log("Writing to file");
    }
}

class Payment {
    void pay(int amount)                  { System.out.println("Paid: $" + amount); }
    void pay(int amount, String method)   { System.out.println("Paid: $" + amount + " via " + method); }
}

class UpiPayment extends Payment {
    @Override
    void pay(int amount) { System.out.println("UPI Payment: $" + amount); }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Logger — overloading for log levels, overriding for file logging
// ─────────────────────────────────────────────────────────────
class Logger {
    void log(String msg)               { System.out.println("[INFO] " + msg); }   // Overloading
    void log(String msg, String level) { System.out.println("[" + level + "] " + msg); } // Overloading
}

class FileLogger extends Logger {
    @Override
    void log(String msg) { System.out.println("[FILE] " + msg); }  // Overriding
}

// Output:
// Paid: $100
// Paid: $100 via Card
// UPI Payment: $100
// [INFO] Server started
// [ERROR] Server started
// [FILE] Writing to file
