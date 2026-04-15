import java.util.HashMap;
import java.util.Map;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3};
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : arr) freq.put(v, freq.getOrDefault(v, 0) + 1);
        System.out.println(freq);

        // Real-world
        String[] clicks = {"home", "shop", "home", "cart", "shop", "home"};
        Map<String, Integer> pageViews = new HashMap<>();
        for (String page : clicks)
            pageViews.put(page, pageViews.getOrDefault(page, 0) + 1);

        System.out.println("Page views: " + pageViews);

        String mostVisited = pageViews.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        System.out.println("Most visited: " + mostVisited);
    }
}

// Output:
// {1=1, 2=2, 3=3}
// Page views: {home=3, shop=2, cart=1}
// Most visited: home
