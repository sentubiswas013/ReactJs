// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        } finally {
            System.out.println("Finally always runs");
        }

        // Real-world
        processPayment(-50);
        processPayment(200);
    }

    // ─────────────────────────────────────────────────────────
    // REAL-WORLD CODE
    // Payment processing — catch invalid amount, always log
    // ─────────────────────────────────────────────────────────
    static void processPayment(double amount) {
        try {
            if (amount <= 0) throw new IllegalArgumentException("Invalid amount: $" + amount);
            System.out.println("Payment successful: $" + amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed: " + e.getMessage());
        } finally {
            System.out.println("Payment attempt logged");  // always logs, success or failure
        }
    }
}

// Output:
// Cannot divide by zero
// Finally always runs
// Payment failed: Invalid amount: $-50.0
// Payment attempt logged
// Payment successful: $200.0
// Payment attempt logged
