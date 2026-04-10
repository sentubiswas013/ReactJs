import java.util.LinkedHashMap;
import java.util.Map;

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

public class Main {
    public static void main(String[] args) {

        LRU<Integer, Integer> cache = new LRU<>(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);      // access 1
        cache.put(3, 3);   // evicts key 2

        System.out.println(cache.keySet()); // [1, 3]
    }
}