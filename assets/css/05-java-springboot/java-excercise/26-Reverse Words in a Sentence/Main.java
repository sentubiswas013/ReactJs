public class Main {
    public static void main(String[] args) {

        String s = "Java is fun";
        String[] parts = s.split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = parts.length - 1; i >= 0; i--) {
            sb.append(parts[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}