class Main {
    public static void main(String[] args) {

        // 1. Exception Handling
        processPayment(-50);
        processPayment(200);

        // 2. throw vs throws
        try {
            withdraw(500, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 3. Custom Exception
        try {
            vote(16);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }

    // ─────────────────────────────
    // 1. try-catch-finally
    // ─────────────────────────────
    static void processPayment(double amount) {
        try {
            if (amount <= 0)
                throw new IllegalArgumentException("Invalid amount");

            System.out.println("Payment successful");

        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed");

        } finally {
            System.out.println("Logged");
        }
    }

    // ─────────────────────────────
    // 2. throw vs throws
    // ─────────────────────────────
    static void withdraw(double balance, double amount) throws IllegalArgumentException {
        if (amount > balance)
            throw new IllegalArgumentException("Insufficient balance");

        System.out.println("Withdraw successful");
    }

    // ─────────────────────────────
    // 3. Custom Exception
    // ─────────────────────────────
    static void vote(int age) throws InvalidAgeException {
        if (age < 18)
            throw new InvalidAgeException("Underage");
    }
}

// Custom Exception
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}