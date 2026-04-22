import java.util.*;

// ============================================================
// 17–21 + Extra: Java Collections (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {

        arrayListDemo();
        linkedListDemo();
        setDemo();
        mapDemo();
        iterateMapDemo();

        // 🔥 Extra Important Topics
        queueDemo();
        stackDemo();
        sortingDemo();
    }

    // ─────────────────────────────────────────────
    // 17. ArrayList
    // Dynamic array (fast read)
    // ─────────────────────────────────────────────
    static void arrayListDemo() {
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("ArrayList: " + list);
        System.out.println("Element at index 1: " + list.get(1));
    }

    // ─────────────────────────────────────────────
    // 18. LinkedList
    // Doubly linked list (fast insert/delete)
    // ─────────────────────────────────────────────
    static void linkedListDemo() {
        List<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.addFirst(5); // special method

        System.out.println("LinkedList: " + list);
    }

    // ─────────────────────────────────────────────
    // 19. Set (HashSet, TreeSet, LinkedHashSet)
    // HashSet (unique and no order), 
    // TreeSet (unique and sorted), 
    // LinkedHashSet (unique and insertion order)
    // ─────────────────────────────────────────────
    static void setDemo() {

        // HashSet (no order)
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(2);

        // TreeSet (sorted)
        Set<Integer> treeSet = new TreeSet<>(hashSet);

        // LinkedHashSet (insertion order)
        Set<Integer> linkedHashSet = new LinkedHashSet<>(hashSet);

        System.out.println("HashSet: " + hashSet);
        System.out.println("TreeSet: " + treeSet);
        System.out.println("LinkedHashSet: " + linkedHashSet);
    }

    // ─────────────────────────────────────────────
    // 20. Map (HashMap, TreeMap, LinkedHashMap, Hashtable)
    // Key-value pairs
    // ─────────────────────────────────────────────
    static void mapDemo() {

        // HashMap (no order, not thread-safe)
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);

        // TreeMap (sorted by key)
        Map<String, Integer> treeMap = new TreeMap<>(hashMap);

        // LinkedHashMap (insertion order)
        Map<String, Integer> linkedMap = new LinkedHashMap<>(hashMap);

        // Hashtable (thread-safe, legacy)
        Map<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("X", 100);

        System.out.println("HashMap: " + hashMap);
        System.out.println("TreeMap: " + treeMap);
        System.out.println("LinkedHashMap: " + linkedMap);
        System.out.println("Hashtable: " + hashtable);
    }

    // ─────────────────────────────────────────────
    // 21. Iterate HashMap
    // ─────────────────────────────────────────────
    static void iterateMapDemo() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);

        // Method 1: entrySet (best)
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Method 2: keySet
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }

        // Method 3: forEach (Java 8)
        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    // ============================================================
    // 🔥 EXTRA IMPORTANT TOPICS
    // ============================================================

    // Queue (FIFO)
    static void queueDemo() {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Queue poll: " + queue.poll());
    }

    // Stack (LIFO)
    static void stackDemo() {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);

        System.out.println("Stack pop: " + stack.pop());
    }

    // Sorting
    static void sortingDemo() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));

        Collections.sort(list);
        System.out.println("Sorted: " + list);
    }
}