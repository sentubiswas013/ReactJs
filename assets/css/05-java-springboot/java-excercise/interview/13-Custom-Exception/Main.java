// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        try {
            vote(16);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }

        // Real-world
        try {
            placeOrder("", 3);
            placeOrder("Laptop", 0);
            placeOrder("Phone", 2);
        } catch (InvalidOrderException e) {
            System.out.println("Order Error: " + e.getMessage());
        }
    }

    static void vote(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Underage: " + age);
    }

    // ─────────────────────────────────────────────────────────
    // REAL-WORLD CODE
    // Order validation — custom exception for bad order input
    // ─────────────────────────────────────────────────────────
    static void placeOrder(String item, int qty) throws InvalidOrderException {
        if (item == null || item.isEmpty()) throw new InvalidOrderException("Item name is empty");
        if (qty <= 0)                       throw new InvalidOrderException("Quantity must be > 0");
        System.out.println("Order placed: " + qty + "x " + item);
    }
}

class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) { super(msg); }
}

class InvalidOrderException extends Exception {
    InvalidOrderException(String msg) { super(msg); }
}

// Output:
// Underage: 16
// Order Error: Item name is empty
