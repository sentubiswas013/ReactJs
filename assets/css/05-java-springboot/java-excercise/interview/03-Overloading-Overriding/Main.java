public class Main {
    public static void main(String[] args) {
        Payment p = new Payment();
        p.pay(100);                               // Overloading
        p.pay(100, "Card");                // Overloading
        
        UpiPayment upi = new UpiPayment();
        upi.pay(100);                             // Overriding
    }
}
// Output:
// Paid: $100
// Paid: $100 via Card
// UPI Payment: $100

class Payment {
    void pay(int amount) {                        // Overloading - pay by amount
        System.out.println("Paid: $" + amount);
    }
    void pay(int amount, String method) {         // Overloading - pay by amount + method
        System.out.println("Paid: $" + amount + " via " + method);
    }
}

class UpiPayment extends Payment {
    @Override
    void pay(int amount) {                        // Overriding - UPI specific payment
        System.out.println("UPI Payment: $" + amount);
    }
}
