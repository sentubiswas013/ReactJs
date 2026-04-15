// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {

        try {
            int x = 10 / 0;

            // simulate another exception
            String str = null;
            System.out.println(str.length());

        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");

        } catch (NullPointerException e) {
            System.out.println("Null value encountered");

        } catch (Exception e) {
            System.out.println("General exception occurred");

        } finally {
            System.out.println("Finally always runs");
        }

        // Real-world
        processPayment(-50);
        processPayment(200);
    }

    // ─────────────────────────────────────────────────────────
    // REAL-WORLD CODE
    // Using multiple catch blocks
    // ─────────────────────────────────────────────────────────
    static void processPayment(double amount) {
        try {
            if (amount <= 0)
                throw new IllegalArgumentException("Invalid amount: $" + amount);

            System.out.println("Payment successful: $" + amount);

        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed: " + e.getMessage());

        } catch (Exception e) { // fallback
            System.out.println("Unexpected error: " + e.getMessage());

        } finally {
            System.out.println("Payment attempt logged");
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
