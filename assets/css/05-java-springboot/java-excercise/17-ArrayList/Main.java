import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> Cars = new ArrayList<>();
        Cars.add("Tata");
        Cars.add("Mahindia");
        Cars.add("OLA");
        Cars.add("Bajaj");

        // for(int i = 0; i<Cars.size(); i++) {
        //     System.out.println("Hello " + Cars.get(i));
        // }

        for(String row: Cars) {
            System.out.println(row);
        }



        // List<String> list = new ArrayList<>();

        // list.add("a");
        // list.add("b");
        // list.remove("a");

        // for (String s : list) {
        //     System.out.println(s);
        // }
    }
}