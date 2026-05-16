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
        // weakHashMapDemo();
        // identityHashMapDemo();
        // linkedHashMapDemo();
        // lruCacheDemo();
        // priorityQueueDemo();
        // maxHeapDemo();
        simpleCacheDemo();
    }

    // ============================================================
    // 1. ArrayList
    // ArrayList uses a dynamic array, so it gives fast random access (O(1)), but slow insertions/deletions in the middle due to shifting.
    // ============================================================

    

    // ============================================================
    // 2. LinkedList
    // LinkedList uses a doubly linked list, so it has slower access (O(n)), but faster insertions/deletions since no shifting is required.
    // ============================================================

    

    // ============================================================
    // 3. Set
    // ============================================================

    

    // ============================================================
    // 4. Map
    // map() is used to transform each element in a stream into another form. It returns one output for each input, so the structure of the stream stays the same.
    // ============================================================

    

    // ============================================================
    // 5. Iterate Map
    // ============================================================

    

    // ============================================================
    // 6. Queue
    // ============================================================

    

    // ============================================================
    // 7. Stack
    // ============================================================

    

    // ============================================================
    // 8. Sorting
    // ============================================================

    

    // ============================================================
    // 9. WeakHashMap
    // A map where keys are stored with weak references, so entries can be removed automatically by the Java Garbage Collector when keys are no longer used.
    // ============================================================

    

    // ============================================================
    // 10. IdentityHashMap
    // A map that compares keys using reference equality (==) instead of equals().
    // ============================================================

    

    // ============================================================
    // 11. LinkedHashMap
    // A map that maintains insertion order using a linked list along with a hash table.
    // ============================================================

    

    // ============================================================
    // 12. LRU Cache
    // LRU (Least Recently Used) cache is a data structure that evicts the least recently used items when it reaches its capacity. It can be implemented using LinkedHashMap in Java.
    // ============================================================
    public static void lruCacheDemo () {
        LinkedHashMap<Integer, String> cache = new LinkedHashMap<Integer, String>(3, 0.75f){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 3;
            }
        };

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.get(1);
        cache.put(4, "D");

        System.out.println(cache);
    }
    

    // ============================================================
    // 13. PriorityQueue
    // A queue that orders elements based on priority (natural order or comparator) instead of insertion order.
    // ============================================================

    

    // ============================================================
    // 14. Max Heap
    // ============================================================

    
}
