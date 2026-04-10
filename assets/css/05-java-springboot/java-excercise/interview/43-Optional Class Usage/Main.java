import java.util.Optional;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable(null);
        System.out.println(opt.orElse("default"));

        // Real-world
        UserRepo repo = new UserRepo();

        repo.findById(1)
            .map(u -> "Found: " + u)
            .ifPresent(System.out::println);

        String result = repo.findById(99)
                            .orElse("User not found");
        System.out.println(result);
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// User lookup — Optional avoids NullPointerException
// ─────────────────────────────────────────────────────────────
class UserRepo {
    Optional<String> findById(int id) {
        if (id == 1) return Optional.of("Alice");
        return Optional.empty();                    // no NPE — safe empty
    }
}

// Output:
// default
// Found: Alice
// User not found
