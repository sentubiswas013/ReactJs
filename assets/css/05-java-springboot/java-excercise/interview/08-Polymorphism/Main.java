// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Payment p = new CreditCard();
        p.pay(100);
        p = new PayPal();
        p.pay(200);
        p = new Crypto();
        p.pay(300);

        // Real-world
        Discount d = new SeasonalDiscount();
        System.out.println("After discount: $" + d.apply(1000));
        d = new LoyaltyDiscount();
        System.out.println("After discount: $" + d.apply(1000));
    }
}

class Payment { void pay(double amount) { System.out.println("Paying: $" + amount); } }
class CreditCard extends Payment { @Override void pay(double amount) { System.out.println("CreditCard: $" + amount + " + 2% fee"); } }
class PayPal     extends Payment { @Override void pay(double amount) { System.out.println("PayPal: $" + amount + " + 1% fee"); } }
class Crypto     extends Payment { @Override void pay(double amount) { System.out.println("Crypto: $" + amount + " no fee"); } }

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Discount strategy — swap discount type without changing caller
// ─────────────────────────────────────────────────────────────
class Discount {
    double apply(double price) { return price; }
}

class SeasonalDiscount extends Discount {
    @Override
    double apply(double price) { return price * 0.80; }  // 20% off
}

class LoyaltyDiscount extends Discount {
    @Override
    double apply(double price) { return price * 0.90; }  // 10% off
}

// Output:
// CreditCard: $100.0 + 2% fee
// PayPal: $200.0 + 1% fee
// Crypto: $300.0 no fee
// After discount: $800.0
// After discount: $900.0
