import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {

        try (
            BufferedInputStream in =
                    new BufferedInputStream(new FileInputStream("in.txt"));
            BufferedOutputStream out =
                    new BufferedOutputStream(new FileOutputStream("out.txt"))
        ) {
            byte[] buffer = new byte[4096];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
    }
}