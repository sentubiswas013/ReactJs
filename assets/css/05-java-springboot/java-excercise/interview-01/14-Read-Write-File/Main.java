import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws Exception {
        fnWriteFile();
        fnReadFile();

        // Real-world
        AuditLogger logger = new AuditLogger("audit.log");
        logger.log("User Alice logged in");
        logger.log("User Alice placed order #101");
        logger.readAll();
    }

    static void fnReadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) System.out.println(line);
        } catch (Exception e) { System.out.println("Error reading: " + e.getMessage()); }
    }

    static void fnWriteFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write("Hello file");
        } catch (Exception e) { System.out.println("Error writing: " + e.getMessage()); }
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Audit logger — writes user actions to a log file, reads them back
// ─────────────────────────────────────────────────────────────
class AuditLogger {
    private String filePath;

    AuditLogger(String filePath) { this.filePath = filePath; }

    void log(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(message);
            bw.newLine();
            System.out.println("Logged: " + message);
        } catch (Exception e) { System.out.println("Log error: " + e.getMessage()); }
    }

    void readAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("--- Audit Log ---");
            while ((line = br.readLine()) != null) System.out.println(line);
        } catch (Exception e) { System.out.println("Read error: " + e.getMessage()); }
    }
}

// Output:
// Logged: User Alice logged in
// Logged: User Alice placed order #101
// --- Audit Log ---
// User Alice logged in
// User Alice placed order #101
