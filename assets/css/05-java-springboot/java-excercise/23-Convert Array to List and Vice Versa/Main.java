import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] arr = {"a", "b", "c"};

        List<String> list = new ArrayList<>(Arrays.asList(arr));
        String[] back = list.toArray(new String[0]);

        System.out.println(list + " | " + Arrays.toString(back));
    }
}