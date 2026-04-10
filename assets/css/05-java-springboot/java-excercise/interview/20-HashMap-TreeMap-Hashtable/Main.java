import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Map<Integer, String> user = new HashMap<>();
        user.put(23, "Sentu"); user.put(12, "Pintu"); user.put(45, "Ranku");
        System.out.println("HashMap:   " + user);
        System.out.println("TreeMap:   " + new TreeMap<>(user));
        System.out.println("Hashtable: " + new Hashtable<>(user));

        // Real-world
        ProductCatalog catalog = new ProductCatalog();
        catalog.add("P003", "Keyboard", 49.99);
        catalog.add("P001", "Laptop",   999.99);
        catalog.add("P002", "Mouse",    25.99);
        catalog.showAll();
        catalog.showSortedById();
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Product catalog — HashMap for fast lookup, TreeMap for sorted display
// ─────────────────────────────────────────────────────────────
class ProductCatalog {
    private Map<String, String> products = new HashMap<>();  // id → "name:price"

    void add(String id, String name, double price) {
        products.put(id, name + ":$" + price);
    }

    void showAll()          { System.out.println("All products: " + products); }

    void showSortedById()   { System.out.println("Sorted by ID: " + new TreeMap<>(products)); }
}

// Output:
// HashMap:   {23=Sentu, 12=Pintu, 45=Ranku}
// TreeMap:   {12=Pintu, 23=Sentu, 45=Ranku}
// Hashtable: {45=Ranku, 23=Sentu, 12=Pintu}
// All products: {P003=Keyboard:$49.99, P001=Laptop:$999.99, P002=Mouse:$25.99}
// Sorted by ID: {P001=Laptop:$999.99, P002=Mouse:$25.99, P003=Keyboard:$49.99}
