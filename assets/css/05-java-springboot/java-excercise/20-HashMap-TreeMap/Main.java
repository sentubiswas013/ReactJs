import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        System.out.println(map);

        Map<String, Integer> tmap = new TreeMap<>(map);
        System.out.println(tmap);
    }
}