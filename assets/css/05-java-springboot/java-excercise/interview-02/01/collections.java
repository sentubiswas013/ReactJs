import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// ============================================================
// Java Collections + Important Interview Examples
// Compact Single File
// ============================================================

class CollectionsDemo {

    public static void main(String[] args) throws Exception {

        // arrayListDemo();
        // linkedListDemo();
        // setDemo();
        // mapDemo();
        // iterateMapDemo();
        // queueDemo();
        // stackDemo();
        // sortingDemo();
         weakHashMapDemo();
        // identityHashMapDemo();
        // linkedHashMapDemo();
        // lruCacheDemo();
        // priorityQueueDemo();
        // maxHeapDemo();
        // simpleCacheDemo();
    }

    // ============================================================
    // 1. ArrayList
    // ArrayList uses a dynamic array, so it gives fast random
    // access (O(1)), but slow insertions/deletions in the middle
    // due to shifting.
    // ============================================================

    public static void arrayListDemo() {

    }

    // ============================================================
    // 2. LinkedList
    // LinkedList uses a doubly linked list, so it has slower
    // access (O(n)), but faster insertions/deletions since no
    // shifting is required.
    // ============================================================

    public static void linkedListDemo() {

    }

    // ============================================================
    // 3. Set
    // ============================================================

    public static void setDemo() {

    }

    // ============================================================
    // 4. Map
    // map() is used to transform each element in a stream into
    // another form. It returns one output for each input,
    // so the structure of the stream stays the same.
    // ============================================================

    public static void mapDemo() {

    }

    // ============================================================
    // 5. Iterate Map
    // ============================================================

    public static void iterateMapDemo() {

    }

    // ============================================================
    // 6. Queue
    // ============================================================

    public static void queueDemo() {

    }

    // ============================================================
    // 7. Stack
    // ============================================================

    public static void stackDemo() {

    }

    // ============================================================
    // 8. Sorting
    // ============================================================

    public static void sortingDemo() {

    }

    // ============================================================
    // 9. WeakHashMap
    // A map where keys are stored with weak references,
    // so entries can be removed automatically by the
    // Java Garbage Collector when keys are no longer used.
    // ============================================================

    public static void weakHashMapDemo() {
        Map<String, Integer> weakMap = new WeakHashMap<>();
        String key = new String("Java");

        weakMap.put(key, 100);
        System.out.println("Before weakmap " + weakMap);

        key = null;
        System.gc();

        System.out.println("After weakmap " + weakMap);

    }

    // ============================================================
    // 10. IdentityHashMap
    // A map that compares keys using reference equality (==)
    // instead of equals().
    // ============================================================

    public static void identityHashMapDemo() {
        IdentityHashMap<String, Integer> identityMap = new IdentityHashMap<>();

        String s1 = new String("Java");
        String s2 = new String("Java");

        identityMap.put(s1, 1);
        identityMap.put(s2, 2);


        System.out.println("identityMap ---- " + identityMap);
    }

    // ============================================================
    // 11. LinkedHashMap
    // A map that maintains insertion order using a linked list
    // along with a hash table.
    // ============================================================

    public static void linkedHashMapDemo() {

        Map<Integer, String> linkedMap = new LinkedHashMap<>();

        linkedMap.put(1, "Java");
        linkedMap.put(2, "Python");
        linkedMap.put(3, "Node");

        System.out.println(linkedMap);
    }

    // ============================================================
    // 12. LRU Cache
    // LRU (Least Recently Used) cache is a data structure that
    // evicts the least recently used items when it reaches its
    // capacity. It can be implemented using LinkedHashMap in Java.
    // ============================================================

    public static void lruCacheDemo() {
        LinkedHashMap<Integer, String> cache = new LinkedHashMap<>(2, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 2;
            } 
        };

        cache.put(1, "A");
        cache.put(2, "B");

        cache.get(1);
        cache.put(3, "C");


        System.out.println(cache);
    }

    // ============================================================
    // 13. PriorityQueue
    // A queue that orders elements based on priority
    // (natural order or comparator) instead of insertion order.
    // ============================================================

    public static void priorityQueueDemo() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(40);
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        while(!queue.isEmpty()) {
            System.out.println("queue " + queue.poll());
        }
    }

    // ============================================================
    // 14. Max Heap
    // ============================================================

    public static void maxHeapDemo() {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxQueue.offer(10);
        maxQueue.offer(40);
        maxQueue.offer(20);
        maxQueue.offer(60);


        while(!maxQueue.isEmpty()) {
            System.out.println(maxQueue.poll());
        }

    }

    // ============================================================
    // 15. Simple Cache
    // ============================================================

    public static void simpleCacheDemo() {

    }
}