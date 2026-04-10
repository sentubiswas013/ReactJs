import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list =
                new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 4));

        List<Integer> unique =
                new ArrayList<>(new LinkedHashSet<>(list));

        System.out.println(unique);
    }
}