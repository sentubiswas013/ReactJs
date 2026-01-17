import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3};

        Map<Integer, Integer> freq = new HashMap<>();

        for (int v : arr) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }

        System.out.println(freq);
    }
}