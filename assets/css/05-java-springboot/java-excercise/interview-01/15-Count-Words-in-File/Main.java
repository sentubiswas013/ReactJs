import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws Exception {
        int words = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 1 && parts[0].isEmpty()) continue;
                words += parts.length;
            }
        }
        System.out.println("Word count: " + words);

        // Real-world
        WordAnalyzer.analyze("logs.txt");
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Log analyzer — count total words and frequency of each word
// ─────────────────────────────────────────────────────────────
class WordAnalyzer {
    static void analyze(String filePath) {
        Map<String, Integer> freq = new HashMap<>();
        int total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.trim().split("\\s+")) {
                    if (word.isEmpty()) continue;
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                    total++;
                }
            }
        } catch (Exception e) {
            System.out.println("File not found: " + filePath);
            return;
        }

        System.out.println("Total words: " + total);
        System.out.println("Frequency: " + freq);
    }
}

// Output:
// Word count: <n>
// Total words: <n>
// Frequency: {word=count, ...}
