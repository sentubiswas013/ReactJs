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
    }

    // ============================================================
    // 1. ArrayList
    // ArrayList uses a dynamic array, so it gives fast random access (O(1)), but slow insertions/deletions in the middle due to shifting.
    // ============================================================

    static void arrayListDemo() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Spring");
        list.add("Hibernate");

        System.out.println("ArrayList: " + list);
        System.out.println("Element at index 1: " + list.get(1));
    }

    // ============================================================
    // 2. LinkedList
    // LinkedList uses a doubly linked list, so it has slower access (O(n)), but faster insertions/deletions since no shifting is required.
    // ============================================================

    static void linkedListDemo() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.addFirst(5);

        System.out.println("LinkedList: " + list);
    }

    // ============================================================
    // 3. Set
    // ============================================================

    static void setDemo() {
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
    // 4. Map
    // map() is used to transform each element in a stream into another form. It returns one output for each input, so the structure of the stream stays the same.
    // ============================================================

    static void mapDemo() {
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
    // 5. Iterate Map
    // ============================================================

    static void iterateMapDemo() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 1);
        map.put("Spring", 2);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }

        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    // ============================================================
    // 6. Queue
    // Queue is a First-In-First-Out (FIFO) data structure where the first element added is the first one to be removed. It supports two main operations: offer (to add an element) and poll (to remove the front element).
    // ============================================================

    static void queueDemo() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println("Queue: " + queue);
        System.out.println("Removed: " + queue.poll());
    }

    // ============================================================
    // 7. Stack
    // Stack is a Last-In-First-Out (LIFO) data structure where the last element added is the first one to be removed. It supports two main operations: push (to add an element) and pop (to remove the top element).
    // ============================================================

    static void stackDemo() {
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
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));

        Collections.sort(list);

        System.out.println("Sorted List: " + list);
    }

    // ============================================================
    // 9. WeakHashMap
    // A map where keys are stored with weak references, so entries can be removed automatically by the Java Garbage Collector when keys are no longer used.
    // ============================================================

    static void weakHashMapDemo() throws Exception {
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
    // 10. IdentityHashMap
    // A map that compares keys using reference equality (==) instead of equals().
    // ============================================================

    static void identityHashMapDemo() {
        Map<String, Integer> identityMap = new IdentityHashMap<>();

        String s1 = new String("Java");
        String s2 = new String("Java");

        identityMap.put(s1, 1);
        identityMap.put(s2, 2);

        System.out.println(identityMap);
    }

    // ============================================================
    // 11. LinkedHashMap
    //  A map that maintains insertion order using a linked list along with a hash table.
    // ============================================================

    static void linkedHashMapDemo() {
        Map<Integer, String> linkedMap = new LinkedHashMap<>();

        linkedMap.put(3, "Java");
        linkedMap.put(1, "Spring");
        linkedMap.put(2, "Hibernate");

        System.out.println(linkedMap);
    }

    // ============================================================
    // 12. LRU Cache
    // LRU (Least Recently Used) cache is a data structure that evicts the least recently used items when it reaches its capacity. It can be implemented using LinkedHashMap in Java.
    // ============================================================

    static void lruCacheDemo() {
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

    // ============================================================
    // 13. PriorityQueue
    //  A queue that orders elements based on priority (natural order or comparator) instead of insertion order.
    // ============================================================

    static void priorityQueueDemo() {
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
    // 14. Max Heap
    // ============================================================

    static void maxHeapDemo() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}

