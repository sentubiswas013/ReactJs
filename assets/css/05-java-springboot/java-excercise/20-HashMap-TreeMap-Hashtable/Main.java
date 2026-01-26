import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {

        Map<Integer, String> user = new HashMap<>();
        user.put(23, "Sentu");
        user.put(12, "Pintu");
        user.put(45, "Ranku");
        user.put(23, "Sentu");

        System.out.println(user);
        // {23=Sentu, 12=Pintu, 45=Ranku}

        Map<Integer, String> userTemp1 = new TreeMap<>(user);
        System.out.println(userTemp1);
        // {12=Pintu, 23=Sentu, 45=Ranku} - sorted by key

        Map<Integer, String> userTemp2 = new Hashtable<>(user);
        System.out.println(userTemp2);
        // {45=Ranku, 23=Sentu, 12=Pintu} - no particular order

        // Map<String, Integer> map = new HashMap<>();
        // map.put("a", 1);
        // map.put("b", 2);

        // System.out.println(map);

        // Map<String, Integer> tmap = new TreeMap<>(map);
        // System.out.println(tmap);
    }
}