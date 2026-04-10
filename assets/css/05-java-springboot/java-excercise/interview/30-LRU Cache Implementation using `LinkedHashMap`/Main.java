import java.util.LinkedHashMap;
import java.util.Map;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        LRU<Integer, Integer> cache = new LRU<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // access 1 → moves to recent
        cache.put(3, 3);    // evicts key 2 (least recently used)
        System.out.println(cache.keySet()); // [1, 3]

        // Real-world: API response cache — evict least-used endpoints
        LRU<String, String> apiCache = new LRU<>(3);
        apiCache.put("/users", "[{id:1,name:Alice}]");
        apiCache.put("/products", "[{id:1,name:Laptop}]");
        apiCache.put("/orders", "[{id:1,status:shipped}]");
        apiCache.get("/users");              // access /users → stays recent
        apiCache.put("/payments", "[]");     // evicts /products (LRU)

        System.out.println("Cached endpoints: " + apiCache.keySet());
        System.out.println("Cache hit /users: " + (apiCache.get("/users") != null));
        System.out.println("Cache hit /products: " + (apiCache.get("/products") != null));
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// LRU Cache using LinkedHashMap with accessOrder=true
// ─────────────────────────────────────────────────────────────
class LRU<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRU(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// Output:
// [1, 3]
// Cached endpoints: [/orders, /users, /payments]
// Cache hit /users: true
// Cache hit /products: false
