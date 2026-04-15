// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    static int count = 0;

    public Main() { count++; }

    public static void main(String[] args) {
        new Main(); new Main();
        System.out.println("Instances: " + Main.count);

        // Real-world
        System.out.println(MathUtils.square(5));
        System.out.println(MathUtils.cube(3));
        System.out.println("Total orders: " + Order.totalOrders);
        new Order("Laptop");
        new Order("Phone");
        System.out.println("Total orders: " + Order.totalOrders);
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// 1. Static utility methods — no need to create object
// 2. Static counter — shared across all instances
// ─────────────────────────────────────────────────────────────
class MathUtils {
    static int square(int n) { return n * n; }  // utility — no state needed
    static int cube(int n)   { return n * n * n; }
}

class Order {
    static int totalOrders = 0;                 // shared across all Order objects
    String item;

    Order(String item) {
        this.item = item;
        totalOrders++;
        System.out.println("Order placed: " + item);
    }
}

// Output:
// Instances: 2
// 25
// 27
// Total orders: 0
// Order placed: Laptop
// Order placed: Phone
// Total orders: 2
