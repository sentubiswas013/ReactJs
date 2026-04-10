import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws Exception {
        try (
            BufferedInputStream  in  = new BufferedInputStream(new FileInputStream("in.txt"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("out.txt"))
        ) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = in.read(buffer)) != -1) out.write(buffer, 0, len);
        }
        System.out.println("File copied");

        // Real-world
        BackupService.backup("config.properties", "config.backup");
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Backup service — copies config file to a backup before update
// ─────────────────────────────────────────────────────────────
class BackupService {
    static void backup(String source, String dest) {
        try (
            BufferedReader br = new BufferedReader(new FileReader(source));
            BufferedWriter bw = new BufferedWriter(new FileWriter(dest))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Backup created: " + dest);
        } catch (Exception e) {
            System.out.println("Backup failed: " + e.getMessage());
        }
    }
}

// Output:
// File copied
// Backup created: config.backup
