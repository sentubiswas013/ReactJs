import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        int sum = nums.stream().filter(x -> x % 2 == 0).map(x -> x * x).reduce(0, Integer::sum);
        System.out.println("Sum of squares of evens: " + sum);

        // Real-world
        List<String> orders = Arrays.asList("Laptop:1200", "Phone:800", "Mouse:25", "Monitor:400");

        // Get items over $100, extract names, sort and collect
        List<String> expensive = orders.stream()
                .filter(o -> Integer.parseInt(o.split(":")[1]) > 100)
                .map(o -> o.split(":")[0])
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Expensive items: " + expensive);

        // Total revenue
        double total = orders.stream()
                .mapToDouble(o -> Double.parseDouble(o.split(":")[1]))
                .sum();

        System.out.println("Total revenue: $" + total);
    }
}

// Output:
// Sum of squares of evens: 20
// Expensive items: [Laptop, Monitor, Phone]
// Total revenue: $2425.0
