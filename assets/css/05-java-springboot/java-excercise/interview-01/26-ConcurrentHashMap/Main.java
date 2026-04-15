import java.util.concurrent.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();

        store.put("Hello1", 2000);
        store.put("Hello2", 4000);
        store.put("Hello3", 5000);

        // Keep main thread alive
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        store.shutdown();
    }
}

class Store {

    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    public void put(String key, int delay) {

        // Store delay
        map.put(key, delay);

        // Schedule execution
        scheduler.schedule(() -> {
            System.out.println("Executing: " + key + " after " + delay + " ms");
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}