import java.util.Arrays;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {

    static void print(Integer x) { System.out.println(x); }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.forEach(Main::print);                          // static method reference

        // Real-world
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        names.stream()
             .map(String::toUpperCase)                      // instance method reference
             .forEach(System.out::println);                 // static method reference

        List<String> emails = Arrays.asList("alice@gmail.com", "bob@yahoo.com");
        emails.stream()
              .map(EmailService::format)                    // static method reference
              .forEach(System.out::println);
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Email formatter using method reference
// ─────────────────────────────────────────────────────────────
class EmailService {
    static String format(String email) {
        return "To: " + email.trim().toLowerCase();
    }
}

// Output:
// 1  2  3
// ALICE  BOB  CHARLIE
// To: alice@gmail.com
// To: bob@yahoo.com
