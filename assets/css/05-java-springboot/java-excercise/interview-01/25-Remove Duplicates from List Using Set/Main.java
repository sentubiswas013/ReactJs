import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 4));
        List<Integer> unique = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println(unique);

        // Real-world: remove duplicate user emails from signup list
        List<String> emails = new ArrayList<>(Arrays.asList(
            "alice@gmail.com", "bob@gmail.com", "alice@gmail.com", "carol@gmail.com", "bob@gmail.com"
        ));
        List<String> uniqueEmails = new ArrayList<>(new LinkedHashSet<>(emails));
        System.out.println("Unique emails: " + uniqueEmails);
        System.out.println("Duplicates removed: " + (emails.size() - uniqueEmails.size()));
    }
}

// Output:
// [1, 2, 3, 4]
// Unique emails: [alice@gmail.com, bob@gmail.com, carol@gmail.com]
// Duplicates removed: 2
