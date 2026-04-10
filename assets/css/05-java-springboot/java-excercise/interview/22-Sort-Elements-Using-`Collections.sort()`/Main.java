import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);

        Collections.sort(list);
        System.out.println("Ascending:  " + list); // [1, 2, 3, 4, 5]

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Descending: " + list); // [5, 4, 3, 2, 1]
    }
}
