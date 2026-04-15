import java.util.HashMap;
import java.util.Map;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("x", 10); map.put("y", 20);

        for (Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + "=" + e.getValue());

        for (String key   : map.keySet())  System.out.println("Key: "   + key);
        for (Integer val  : map.values())  System.out.println("Value: " + val);

        // Real-world
        OrderSummary summary = new OrderSummary();
        summary.add("Laptop",   2);
        summary.add("Mouse",    5);
        summary.add("Keyboard", 3);
        summary.printAll();
        summary.printTotal();
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Order summary — iterate items and quantities, compute total count
// ─────────────────────────────────────────────────────────────
class OrderSummary {
    private Map<String, Integer> items = new HashMap<>();

    void add(String item, int qty) { items.put(item, qty); }

    void printAll() {
        System.out.println("--- Order Summary ---");
        for (Map.Entry<String, Integer> e : items.entrySet())
            System.out.println(e.getKey() + " x" + e.getValue());
    }

    void printTotal() {
        int total = 0;
        for (int qty : items.values()) total += qty;
        System.out.println("Total items: " + total);
    }
}

// Output:
// x=10  y=20
// Key: x  Key: y
// Value: 10  Value: 20
// --- Order Summary ---
// Laptop x2  Mouse x5  Keyboard x3
// Total items: 10
