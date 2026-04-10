import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        // HashSet — no duplicates, unordered
        Set<Integer> hs = new HashSet<>();
        hs.add(3);
        hs.add(1);
        hs.add(3); // duplicate, ignored
        System.out.println("HashSet: " + hs); // [1, 3] (order not guaranteed)

        // TreeSet — no duplicates, sorted
        Set<Integer> ts = new TreeSet<>(hs);
        System.out.println("TreeSet: " + ts); // [1, 3]
    }
}
