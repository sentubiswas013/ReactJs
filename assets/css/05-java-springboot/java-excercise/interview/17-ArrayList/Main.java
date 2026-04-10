import java.util.ArrayList;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.remove("a");
        for (String s : list) System.out.println(s);

        // Real-world
        Cart cart = new Cart();
        cart.addItem("Laptop");
        cart.addItem("Mouse");
        cart.addItem("Keyboard");
        cart.removeItem("Mouse");
        cart.showItems();
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Shopping cart — add/remove items using ArrayList
// ─────────────────────────────────────────────────────────────
class Cart {
    private List<String> items = new ArrayList<>();

    void addItem(String item)    { items.add(item); System.out.println("Added: " + item); }
    void removeItem(String item) { items.remove(item); System.out.println("Removed: " + item); }
    void showItems()             { System.out.println("Cart: " + items); }
}

// Output:
// b
// Added: Laptop  Added: Mouse  Added: Keyboard
// Removed: Mouse
// Cart: [Laptop, Keyboard]
