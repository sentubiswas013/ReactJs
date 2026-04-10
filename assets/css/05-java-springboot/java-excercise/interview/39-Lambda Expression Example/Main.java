import java.util.Arrays;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Operation add = (a, b) -> a + b;
        System.out.println(add.apply(2, 3));

        // Real-world
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));

        List<Integer> prices = Arrays.asList(100, 200, 50, 300);
        prices.stream()
              .filter(p -> p > 100)
              .forEach(p -> System.out.println("Expensive: $" + p));
    }
}

interface Operation { int apply(int a, int b); }

// Output:
// 5
// Hello, Alice  Hello, Bob  Hello, Charlie
// Expensive: $200
// Expensive: $300
