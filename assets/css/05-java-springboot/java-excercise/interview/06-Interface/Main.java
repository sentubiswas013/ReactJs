// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Notification email = new EmailNotification();
        Notification sms   = new SmsNotification();
        email.send("Order Confirmed");
        sms.send("Order Confirmed");

        // Real-world
        PaymentGateway stripe = new StripeGateway();
        PaymentGateway paypal = new PaypalGateway();
        stripe.charge(500);
        paypal.charge(300);
        stripe.refund(100);
    }
}

interface Notification { void send(String message); }

class EmailNotification implements Notification {
    @Override public void send(String message) { System.out.println("Email: " + message); }
}

class SmsNotification implements Notification {
    @Override public void send(String message) { System.out.println("SMS: " + message); }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Payment gateway — different providers, same contract
// ─────────────────────────────────────────────────────────────
interface PaymentGateway {
    void charge(double amount);
    default void refund(double amount) { System.out.println("Refund: $" + amount); }
}

class StripeGateway implements PaymentGateway {
    @Override public void charge(double amount) { System.out.println("Stripe charged: $" + amount); }
}

class PaypalGateway implements PaymentGateway {
    @Override public void charge(double amount) { System.out.println("PayPal charged: $" + amount); }
}

// Output:
// Email: Order Confirmed
// SMS: Order Confirmed
// Stripe charged: $500.0
// PayPal charged: $300.0
// Refund: $100.0
