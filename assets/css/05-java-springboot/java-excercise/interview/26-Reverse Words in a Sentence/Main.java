// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        String s = "Java is fun";
        String[] parts = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = parts.length - 1; i >= 0; i--) {
            sb.append(parts[i]);
            if (i != 0) sb.append(" ");
        }
        System.out.println(sb);

        // Real-world: reverse display name for RTL language support
        String fullName = "John Michael Smith";
        String reversed = reverseWords(fullName);
        System.out.println("Display name (RTL): " + reversed);

        // Real-world: reverse log message tokens for audit trail
        String logEntry = "ERROR database connection failed";
        System.out.println("Reversed log: " + reverseWords(logEntry));
    }

    static String reverseWords(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" ");
        }
        return sb.toString();
    }
}

// Output:
// fun is Java
// Display name (RTL): Smith Michael John
// Reversed log: failed connection database ERROR
