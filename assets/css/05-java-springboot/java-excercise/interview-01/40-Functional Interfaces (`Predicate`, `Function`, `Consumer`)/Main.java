import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven  = x -> x % 2 == 0;
        Function<Integer, Integer> square = x -> x * x;
        Consumer<Integer> print    = System.out::println;

        if (isEven.test(4)) print.accept(square.apply(4));

        // Real-world
        List<String> emails = Arrays.asList("alice@gmail.com", "invalid-email", "bob@yahoo.com");

        Predicate<String>  isValid  = e -> e.contains("@");         // validate
        Function<String, String> mask = e -> e.replaceAll("(?<=.{3}).(?=.*@)", "*"); // mask
        Consumer<String>   log      = e -> System.out.println("Processed: " + e);   // log

        emails.stream()
              .filter(isValid)
              .map(mask)
              .forEach(log);
    }
}

// Output:
// 16
// Processed: ali**@gmail.com
// Processed: bob@yahoo.com
