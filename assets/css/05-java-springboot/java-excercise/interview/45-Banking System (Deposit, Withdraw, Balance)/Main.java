// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Account account = new Account(1000);
        account.deposit(500);
        account.withdraw(200);
        System.out.println("Balance: " + account.getBalance()); // 1300

        // Real-world: multi-user concurrent banking
        Account savings = new Account(5000);

        Thread salary   = new Thread(() -> savings.deposit(3000),  "SalaryCredit");
        Thread rent     = new Thread(() -> savings.withdraw(1500), "RentDebit");
        Thread shopping = new Thread(() -> savings.withdraw(800),  "ShoppingDebit");

        salary.start(); rent.start(); shopping.start();

        try { salary.join(); rent.join(); shopping.join(); } catch (InterruptedException ignored) {}

        System.out.println("Final balance: " + savings.getBalance()); // 5000+3000-1500-800 = 5700
        System.out.println("Transaction history:");
        savings.printHistory();
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Thread-safe account with transaction history
// ─────────────────────────────────────────────────────────────
class Account {
    private int balance;
    private final java.util.List<String> history = new java.util.ArrayList<>();

    Account(int balance) { this.balance = balance; }

    synchronized void deposit(int amount) {
        balance += amount;
        history.add("+" + amount + " | Balance: " + balance);
    }

    synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("-" + amount + " | Balance: " + balance);
            return true;
        }
        history.add("FAILED withdraw " + amount + " | Balance: " + balance);
        return false;
    }

    synchronized int getBalance() { return balance; }

    synchronized void printHistory() { history.forEach(System.out::println); }
}

// Output:
// Balance: 1300
// Final balance: 5700
// Transaction history:
// +3000 | Balance: 8000
// -1500 | Balance: 6500
// -800  | Balance: 5700
