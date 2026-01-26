import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        List<Integer, String> list = new ArrayList<>();
        list.add(3, "");
        list.add(4, "");
        list.add(3, "");
        list.add(2, "" );

        Map<Integer, Integer> frequencyMap = new HashMap<>(list);


        // List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);

        // Collections.sort(list);
        // System.out.println(list);

        // Collections.sort(list, Collections.reverseOrder());
        // System.out.println(list);
    }
}