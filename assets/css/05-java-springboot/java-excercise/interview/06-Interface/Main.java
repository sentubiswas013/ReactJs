public class Main {
    public static void main(String[] args) {

        // Interface ref → different implementations (Polymorphism)
        Notification email = new EmailNotification();
        Notification sms   = new SmsNotification();
        Notification push  = new PushNotification();

        email.send("Order Confirmed");      // Email specific
        sms.send("Order Confirmed");        // SMS specific
        push.send("Order Confirmed");       // Push specific
    }
}

interface Notification {                        // Interface — only abstract methods (contract)
    void send(String message);                  // No body — every class MUST implement
}

class EmailNotification implements Notification {   // Implements interface
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsNotification implements Notification {     // Implements interface
    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

class PushNotification implements Notification {    // Implements interface
    @Override
    public void send(String message) {
        System.out.println("Push: " + message);
    }
}

// Output:
// Email: Order Confirmed
// SMS: Order Confirmed
// Push: Order Confirmed
