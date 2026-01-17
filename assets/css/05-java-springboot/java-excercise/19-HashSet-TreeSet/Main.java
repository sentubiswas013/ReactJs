import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Set<Integer> hs = new HashSet<>();
        hs.add(3);
        hs.add(1);
        hs.add(3);

        System.out.println(hs);

        Set<Integer> ts = new TreeSet<>(hs);
        System.out.println(ts);
    }
}