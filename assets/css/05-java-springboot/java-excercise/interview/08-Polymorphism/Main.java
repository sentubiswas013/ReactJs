public class Main {
    public static void main(String[] args) {

        // Parent ref → Child object (Runtime Polymorphism)
        Payment p = new CreditCard();
        p.pay(100);                 // CreditCard pay() called

        p = new PayPal();
        p.pay(200);                 // PayPal pay() called

        p = new Crypto();
        p.pay(300);                 // Crypto pay() called
    }
}

class Payment {                         // Parent class
    void pay(double amount) {
        System.out.println("Paying: $" + amount);
    }
}

class CreditCard extends Payment {      // Child 1
    @Override
    void pay(double amount) {           // Runtime — decided at runtime which pay() to call
        System.out.println("CreditCard: $" + amount + " + 2% fee");
    }
}

class PayPal extends Payment {          // Child 2
    @Override
    void pay(double amount) {           // Runtime — decided at runtime which pay() to call
        System.out.println("PayPal: $" + amount + " + 1% fee");
    }
}

class Crypto extends Payment {          // Child 3
    @Override
    void pay(double amount) {           // Runtime — decided at runtime which pay() to call
        System.out.println("Crypto: $" + amount + " no fee");
    }
}

// Output:
// CreditCard: $100.0 + 2% fee
// PayPal: $200.0 + 1% fee
// Crypto: $300.0 no fee
