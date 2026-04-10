// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        System.out.println(div(10, 2));

        // Real-world
        try {
            withdraw(500, 200);
            withdraw(500, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static int div(int a, int b) throws ArithmeticException {
        if (b == 0) throw new ArithmeticException("b cannot be zero");
        return a / b;
    }

    // ─────────────────────────────────────────────────────────
    // REAL-WORLD CODE
    // Bank withdrawal — throw if insufficient balance
    // ─────────────────────────────────────────────────────────
    static double withdraw(double balance, double amount) throws IllegalArgumentException {
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance: $" + balance);
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + " | Remaining: $" + balance);
        return balance;
    }
}

// Output:
// 5
// Withdrawn: $200.0 | Remaining: $300.0
// Error: Insufficient balance: $500.0
