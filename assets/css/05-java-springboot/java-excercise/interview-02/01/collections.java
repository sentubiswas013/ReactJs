import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// ============================================================
// Java Collections + Important Interview Examples
// Compact Single File
// ============================================================

class CollectionsDemo {

    public static void main(String[] args) throws Exception {

        arrayListDemo();
        linkedListDemo();
        setDemo();
        mapDemo();
        iterateMapDemo();
        queueDemo();
        stackDemo();
        sortingDemo();
        weakHashMapDemo();
        identityHashMapDemo();
        linkedHashMapDemo();
        lruCacheDemo();
        priorityQueueDemo();
        maxHeapDemo();
        LRUCacheDemo();
        LFUCacheDemo();
        TTLCacheDemo();
    }

    // ============================================================
    // 1. ArrayList
    // ============================================================
    public static void arrayListDemo() {

        System.out.println("=========================== arrayListDemo");

    }

    // ============================================================
    // 2. LinkedList
    // ============================================================
    public static void linkedListDemo() {

        System.out.println("=========================== linkedListDemo");

    }

    // ============================================================
    // 3. Set
    // ============================================================
    public static void setDemo() {

        System.out.println("=========================== setDemo");

    }

    // ============================================================
    // 4. Map
    // ============================================================
    public static void mapDemo() {

        System.out.println("=========================== mapDemo");

    }

    // ============================================================
    // 5. Iterate Map
    // ============================================================
    public static void iterateMapDemo() {

        System.out.println("=========================== iterateMapDemo");

    }

    // ============================================================
    // 6. Queue
    // ============================================================
    public static void queueDemo() {

        System.out.println("=========================== queueDemo");

    }

    // ============================================================
    // 7. Stack
    // ============================================================
    public static void stackDemo() {

        System.out.println("=========================== stackDemo");

    }

    // ============================================================
    // 8. Sorting
    // ============================================================
    public static void sortingDemo() {

        System.out.println("=========================== sortingDemo");

    }

    // ============================================================
    // 9. WeakHashMap
    // ============================================================
    public static void weakHashMapDemo() {

        System.out.println("=========================== weakHashMapDemo");

    }

    // ============================================================
    // 10. IdentityHashMap
    // ============================================================
    public static void identityHashMapDemo() {

        System.out.println("=========================== identityHashMapDemo");

    }

    // ============================================================
    // 11. LinkedHashMap
    // ============================================================
    public static void linkedHashMapDemo() {

        System.out.println("=========================== linkedHashMapDemo");
        Map<Integer, String> linkedList = new LinkedHashMap<>();
        linkedList.put(1, "Sentu");
        linkedList.put(2, "Pintu");
        linkedList.put(3, "Ranku");
        linkedList.put(4, "Data");

        // for (Integer key : linkedList.keySet()) {
        for (Integer key : linkedList.keySet()) {
            System.out.println(key + " -> " + linkedList.get(key));
        }

        // System.out.println("Result " + linkedList);
    }

    // ============================================================
    // 12. LRU Cache
    // ============================================================
    public static void lruCacheDemo() {

        System.out.println("=========================== lruCacheDemo");

    }

    // ============================================================
    // 13. PriorityQueue
    // ============================================================
    public static void priorityQueueDemo() {

        System.out.println("=========================== priorityQueueDemo");

    }

    // ============================================================
    // 14. Max Heap
    // ============================================================
    public static void maxHeapDemo() {

        System.out.println("=========================== maxHeapDemo");

    }

     // ============================================================
    // 15. LRU Cache
    // LRU (Least Recently Used) cache is a data structure that evicts the least recently used items when it reaches its capacity. It can be implemented using LinkedHashMap in Java.
    // ============================================================
    // **# 2. LRU Cache way 1
    public static void LRUCacheDemo() {

        System.out.println("=========================== LRUCacheDemo");

        // LinkedHashMap<Integer, String> cache = new LinkedHashMap<Integer, String>(3, 0.75f, true) {
        LinkedHashMap<Integer, String> cache = new LinkedHashMap<Integer, String>(3, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest){
                return size() > 3;
            }
        };

        cache.put(1, "Sentu");
        cache.put(2, "Pintu");
        cache.put(3, "Ranku");       

        cache.get(1);
        cache.put(4, "Dada");
        System.out.println("Hello 1" + cache);
    }

    // **# 2. LRU Cache way 2

    public static void LFUCacheDemo() {

        System.out.println("=========================== LFUCacheDemo");
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);

        cache.put(4, "D");
        System.out.println(cache);

        
    }

    static class LRUCache<k, v> extends LinkedHashMap<k, v> {
        private final int capacity;
        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<k, v> eldest){
            return size() > capacity;
        }


    }


    // ============================================================
    // **16. LFU Cache (Least Frequently Used):** Removes least frequently accessed item.
    // ============================================================



    // ============================================================
    // 17. TTL Cache (Time To Live) :** Expires data after fixed duration.
    // ============================================================
    public static void TTLCacheDemo() throws Exception {

        System.out.println("=========================== TTLCacheDemo");
        TTLCache cache = new TTLCache();
        cache.put(1, "java", 3000);

        System.out.println(cache.get(1));
        Thread.sleep(4000);
        System.out.println(cache.get(1));
       

    }

    class TTLCache {
        static class CacheObject {
            String value;
            long expiryTime;

            CacheObject (String value, long ttMillis) {
                this.value = value;
                this.expiryTime = System.currentTimeMillis() + ttMillis;
            }

            private Map<Integer, CacheObject> cache = new HashMap<>();
            public void put(int key, String value, long ttMillis) {
                cache.put(key, new CacheObject(value, ttMillis));
            }

            public String get(int key) {
                CacheObject obj = cache.get(key);

                if(obj == null) {
                    return null;
                }

                if(System.currentTimeMillis() > obj.expiryTime) {
                    cache.remove(key);
                    return null;
                }
                
                return obj.value;
            }
        }
    }
}