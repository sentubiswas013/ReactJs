// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount(1000);
        BankAccount current = new CurrentAccount(1000);

        savings.showInterest();
        current.showInterest();
        savings.deposit(500);

        // Real-world
        Delivery bike  = new BikeDelivery();
        Delivery drone = new DroneDelivery();
        bike.deliver("123 Street");
        drone.deliver("456 Avenue");
        bike.track();
    }
}

abstract class BankAccount {
    double balance;
    BankAccount(double balance) { this.balance = balance; }
    abstract void showInterest();
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount + " | Balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount {
    SavingsAccount(double b) { super(b); }
    @Override void showInterest() { System.out.println("Savings: " + (balance * 0.04) + " (4%)"); }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(double b) { super(b); }
    @Override void showInterest() { System.out.println("Current: " + (balance * 0.01) + " (1%)"); }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Delivery system — each type delivers differently
// ─────────────────────────────────────────────────────────────
abstract class Delivery {
    abstract void deliver(String address);      // each type must implement

    void track() { System.out.println("Tracking order..."); }  // common for all
}

class BikeDelivery extends Delivery {
    @Override
    void deliver(String address) { System.out.println("Bike delivering to: " + address); }
}

class DroneDelivery extends Delivery {
    @Override
    void deliver(String address) { System.out.println("Drone flying to: " + address); }
}

// Output:
// Savings: 40.0 (4%)
// Current: 10.0 (1%)
// Deposited: $500.0 | Balance: $1500.0
// Bike delivering to: 123 Street
// Drone flying to: 456 Avenue
// Tracking order...
