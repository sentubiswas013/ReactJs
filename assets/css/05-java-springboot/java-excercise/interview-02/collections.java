import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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
        flatMapDemo();
        CopyOnWriteArrayList();
        iterateMapDemo();

        queueDemo();
        stackDemo();
        sortingDemo();
        weakHashMapDemo();
        identityHashMapDemo();
        linkedHashMapDemo();
        priorityQueueDemo();
        maxHeapDemo();

        lruCacheDemoOne();
        LRUCacheDemoTwo();
        LFUCacheDemo();
        TTLCacheDemo();
    }

    // ============================================================
    // An ArrayList is a class in the java.util package that implements the List interface. It is a dynamic array, meaning it can grow and shrink automatically as elements are added or removed.
    // ============================================================

    static void arrayListDemo() {
        System.out.println("=========================== arrayListDemo");

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Spring");
        list.add("Hibernate");

        System.out.println("ArrayList: " + list);
        System.out.println("Element at index 1: " + list.get(1));
    }

    // ============================================================
    // 2. LinkedList is a class in the java.util package that implements the List and Deque interfaces. It stores elements as nodes, where each node contains:
    // ============================================================

    static void linkedListDemo() {
        System.out.println("=========================== linkedListDemo");

        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.addFirst(5);

        System.out.println("LinkedList: " + list);
    }

    // ============================================================
    // 3. Set: Stores unique elements and does not allow duplicates.
    // ============================================================

    static void setDemo() {
        System.out.println("=========================== setDemo");

        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(2);

        Set<Integer> treeSet = new TreeSet<>(hashSet);
        Set<Integer> linkedHashSet = new LinkedHashSet<>(hashSet);

        System.out.println("HashSet: " + hashSet);
        System.out.println("TreeSet: " + treeSet);
        System.out.println("LinkedHashSet: " + linkedHashSet);
    }

    // ============================================================
    // Map() is used to transform each element in a stream into another form. It returns one output for each input, so the structure of the stream stays the same.
    // ============================================================

    static void mapDemo() {
        System.out.println("=========================== mapDemo");

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);

        Map<String, Integer> treeMap = new TreeMap<>(hashMap);
        Map<String, Integer> linkedMap = new LinkedHashMap<>(hashMap);

        Map<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("X", 100);

        System.out.println("HashMap: " + hashMap);
        System.out.println("TreeMap: " + treeMap);
        System.out.println("LinkedHashMap: " + linkedMap);
        System.out.println("Hashtable: " + hashtable);
    }
    
 // ============================================================
    // 4. FlatMap: is used when each element produces another stream or collection. It flattens those nested streams into a single stream, so you don’t end up with a stream of streams.
    // ============================================================

    static void flatMapDemo() {
        System.out.println("=========================== flatMapDemo");

    	List<List<String>> nested = List.of(
		    List.of("A", "B"),
		    List.of("C", "D")
		);

		List<String> flatList = nested.stream()
		                              .flatMap(List::stream)
		                              .toList();

		System.out.println(flatList);   // [A, B, C, D]
    }

    // ============================================================
    // CopyOnWriteArrayList() is a thread-safe implementation of the List interface. Whenever an element is added, updated, or removed, it creates a new copy of the underlying array instead of modifying the existing one.
    // ============================================================
    static void CopyOnWriteArrayList() {
        System.out.println("=========================== CopyOnWriteArrayList");

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("Java");
        list.add("Spring");

        for (String item : list) {
            System.out.println(item);

            // Safe modification during iteration
            list.add("Docker");
        }

        System.out.println("Result: " + list);
    }

    // ============================================================
    // 5. Iterate Map: Stores data as key-value pairs. Keys are unique, while values can be duplicated.
    // ============================================================

    static void iterateMapDemo() {
        System.out.println("=========================== iterateMapDemo");

        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 1);
        map.put("Spring", 2);
        
        map.forEach((key, value) -> {
            System.out.println("key " + value);
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }

        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    // ============================================================
    // Queue is a First-In-First-Out (FIFO) data structure where the first element added is the first one to be removed. It supports two main operations: offer (to add an element) and poll (to remove the front element).
    // ============================================================

    static void queueDemo() {
        System.out.println("=========================== queueDemo");

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println("Queue: " + queue);
        System.out.println("Removed: " + queue.poll());
    }

    // ============================================================
    // Stack is a Last-In-First-Out (LIFO) data structure where the last element added is the first one to be removed. It supports two main operations: push (to add an element) and pop (to remove the top element).
    // ============================================================

    static void stackDemo() {
        System.out.println("=========================== stackDemo");

        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);

        System.out.println("Stack: " + stack);
        System.out.println("Popped: " + stack.pop());
    }

    // ============================================================
    // 8. Sorting
    // ============================================================

    static void sortingDemo() {
        System.out.println("=========================== sortingDemo");
        
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
        Collections.sort(list);
        System.out.println("Sorted List: " + list);
    }

    // ============================================================
    // 9. WeakHashMap: is a map where keys are stored with weak references, so entries can be removed automatically by the Java Garbage Collector when keys are no longer used.
    // ============================================================

    static void weakHashMapDemo() throws Exception {
        System.out.println("=========================== weakHashMapDemo");

        Map<String, Integer> weakMap = new WeakHashMap<>();
        String key = new String("Java");
        weakMap.put(key, 100);
        System.out.println("Before GC: " + weakMap);
        key = null;
        System.gc();
        Thread.sleep(2000);
        System.out.println("After GC: " + weakMap);
    }

    // ============================================================
    // 10. IdentityHashMap: is a map that compares keys using reference equality (==) instead of equals().
    // ============================================================

    static void identityHashMapDemo() {
        System.out.println("=========================== identityHashMapDemo");

        Map<String, Integer> identityMap = new IdentityHashMap<>();

        String s1 = new String("Java");
        String s2 = new String("Java");

        identityMap.put(s1, 1);
        identityMap.put(s2, 2);

        System.out.println(identityMap);
    }

    // ============================================================
    // 11. LinkedHashMap: is a map that maintains insertion order using a linked list along with a hash table.
    // ============================================================

    static void linkedHashMapDemo() {
        System.out.println("=========================== linkedHashMapDemo");

        Map<Integer, String> linkedMap = new LinkedHashMap<>();

        linkedMap.put(3, "Java");
        linkedMap.put(1, "Spring");
        linkedMap.put(2, "Hibernate");

        System.out.println(linkedMap);
    }

    // ============================================================
    // 12. PriorityQueue is a queue that orders elements based on priority (natural order or comparator) instead of insertion order.
    // ============================================================

    static void priorityQueueDemo() {
        System.out.println("=========================== priorityQueueDemo");

        Queue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(50);
        priorityQueue.offer(20);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }

    // ============================================================
    // 13. Max Heap is a complete binary tree where the value of every parent node is greater than or equal to its children. Therefore, the largest element is always at the root.
    // ============================================================

    static void maxHeapDemo() {
        System.out.println("=========================== maxHeapDemo");

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }

    // ============================================================
    // LRU (Least Recently Used) cache is a data structure that evicts the least recently used items when it reaches its capacity. It can be implemented using LinkedHashMap in Java.
    // ============================================================
    // **# 2. LRU Cache way one
    static void lruCacheDemoOne() {
        System.out.println("=========================== lruCacheDemoOne");

        LinkedHashMap<Integer, String> cache =  new LinkedHashMap<Integer, String>(3, 0.75f, true) {
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

    // **# 2. LRU Cache way two
     static void LRUCacheDemoTwo() {
        System.out.println("=========================== LRUCacheDemoTwo");

        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);

        cache.put(4, "D");
        System.out.println(cache);
     }
    class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;
        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
    // Output: {3=C, 1=A, 4=D}
    // `2=B` removed because it was least recently used.

    // ============================================================
    // **16. LFU Cache (Least Frequently Used):** Removes least frequently accessed item.
    // ============================================================
    // import java.util.HashMap;
    // import java.util.Map;
    static void LFUCacheDemo() {
        System.out.println("=========================== LFUCacheDemo");

        LFUCache cache = new LFUCache();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);
        cache.get(1);
        cache.get(2);

        cache.removeLFU();
        cache.print();
    }
    class LFUCache {
        private Map<Integer, String> cache = new HashMap<>();
        private Map<Integer, Integer> frequency = new HashMap<>();

        public void put(int key, String value) {
            cache.put(key, value);
            frequency.put(key, 1);
        }

        public String get(int key) {
            if (!cache.containsKey(key)) {
                return null;
            }

            frequency.put(key, frequency.get(key) + 1);
            return cache.get(key);
        }

        public void removeLFU() {
            int minFreq = Integer.MAX_VALUE;
            int lfuKey = -1;

            for (int key : frequency.keySet()) {
                if (frequency.get(key) < minFreq) {
                    minFreq = frequency.get(key);
                    lfuKey = key;
                }
            }

            cache.remove(lfuKey);
            frequency.remove(lfuKey);
        }

        public void print() {
            System.out.println(cache);
        }
    }
    // Output: {1=A, 2=B}
    // 3=C removed because it was least frequently used.

    // ============================================================
    // **17. TTL Cache (Time To Live) :** Expires data after fixed duration.
    // ============================================================
    // import java.util.HashMap;
    // import java.util.Map;
    static void TTLCacheDemo() {
        System.out.println("=========================== TTLCacheDemo");
        
        TTLCache cache = new TTLCache();
        cache.put(1, "Java", 3000);
        System.out.println(cache.get(1));
        Thread.sleep(4000);
        System.out.println(cache.get(1));
    }

    static class TTLCache {
        static class CacheObject {
            String value;
            long expiryTime;

            CacheObject(String value, long ttlMillis) {
                this.value = value;
                this.expiryTime = System.currentTimeMillis() + ttlMillis;
            }
        }

        private Map<Integer, CacheObject> cache = new HashMap<>();
        public void put(int key, String value, long ttlMillis) {
            cache.put(key, new CacheObject(value, ttlMillis));
        }

        public String get(int key) {
            CacheObject obj = cache.get(key);

            if (obj == null) {
                return null;
            }

            if (System.currentTimeMillis() > obj.expiryTime) {
                cache.remove(key);
                return null;
            }

            return obj.value;
        }
    }
    // Output: 
    // Java
    // null
    // After 3 seconds, value expires.
}

