public class Main {
    public static void main(String[] args) {

        // Abstract class ref → child object (Polymorphism)
        BankAccount savings = new SavingsAccount(1000);
        BankAccount current = new CurrentAccount(1000);

        savings.showInterest();     // SavingsAccount specific interest
        current.showInterest();     // CurrentAccount specific interest

        savings.deposit(500);       // Common method from abstract class
        current.deposit(500);       // Common method from abstract class
    }
}

abstract class BankAccount {            // Abstract class — cannot be instantiated
    double balance;

    BankAccount(double balance) {
        this.balance = balance;
    }

    abstract void showInterest();       // Abstract — every account MUST define its own interest

    void deposit(double amount) {       // Concrete — common for all accounts
        balance += amount;
        System.out.println("Deposited: $" + amount + " | Balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount {      // Single Inheritance
    SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    void showInterest() {                       // Overriding abstract method
        System.out.println("Savings Interest: " + (balance * 0.04) + " (4%)");
    }
}

class CurrentAccount extends BankAccount {     // Hierarchical Inheritance
    CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    void showInterest() {                       // Overriding abstract method
        System.out.println("Current Interest: " + (balance * 0.01) + " (1%)");
    }
}

// Output:
// Savings Interest: 40.0 (4%)
// Current Interest: 10.0 (1%)
// Deposited: $500.0 | Balance: $1500.0
// Deposited: $500.0 | Balance: $1500.0
