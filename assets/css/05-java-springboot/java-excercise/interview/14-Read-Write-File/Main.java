import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        fnReadFile();
        fnWriteFile();
    }

    public static void fnReadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void fnWriteFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write("Hello file");
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
