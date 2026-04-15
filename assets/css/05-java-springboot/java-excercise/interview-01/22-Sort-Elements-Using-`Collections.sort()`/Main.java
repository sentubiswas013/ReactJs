import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);
        Collections.sort(list);
        System.out.println("Ascending:  " + list);
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Descending: " + list);

        // Real-world
        List<String> products = Arrays.asList("Laptop:999", "Mouse:25", "Keyboard:49", "Monitor:399");
        products.sort(Comparator.comparingInt(p -> Integer.parseInt(p.split(":")[1])));
        System.out.println("By price asc:  " + products);
        products.sort((a, b) -> Integer.parseInt(b.split(":")[1]) - Integer.parseInt(a.split(":")[1]));
        System.out.println("By price desc: " + products);
    }
}

// Output:
// Ascending:  [1, 2, 3, 4, 5]
// Descending: [5, 4, 3, 2, 1]
// By price asc:  [Mouse:25, Keyboard:49, Monitor:399, Laptop:999]
// By price desc: [Laptop:999, Monitor:399, Keyboard:49, Mouse:25]
