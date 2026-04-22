import java.util.*;

// ============================================================
// 17–21 + Extra: Java Collections (Interview Ready)
// ============================================================

class Collections {
    public static void main(String[] args) {
        // System.out.println("setDemo");
        // arrayListDemo();
        // linkedListDemo();
        // setDemo();
        mapDemo();
        iterateMapDemo();

        // 🔥 Extra Important Topics
        // queueDemo();
        // stackDemo();
        // sortingDemo();
    }

    // ─────────────────────────────────────────────
    // 17. ArrayList
    // Dynamic array (fast read)
    // ─────────────────────────────────────────────
    public static void arrayListDemo() {
        List<String> list = new ArrayList<>();
        list.add("d");
        list.add("A");
        list.add("C");

        System.out.println("ArrayList " + list);
        System.out.println("ArrayList " + list.get(1));
    }

    // ─────────────────────────────────────────────
    // 18. LinkedList
    // Doubly linked list (fast insert/delete)
    // ─────────────────────────────────────────────
    public static void linkedListDemo() {
        LinkedList<String> list = new LinkedList<>();
        list.add("B");
        list.add("G");
        list.add("C");
        list.addFirst("Hello");
        System.out.println("setDemo " + list);
    }

    // ─────────────────────────────────────────────
    // 19. Set (HashSet, TreeSet, LinkedHashSet)
    // No duplicates
    // ─────────────────────────────────────────────
    public static void setDemo() {
        Set<Integer> listHash = new HashSet<>();
        listHash.add(8);
        listHash.add(2);
        listHash.add(9);
        listHash.add(9);

        // Set<Integer> listTree = new TreeSet<>(listHash);
        // Set<Integer> listLink= new LinkedHashSet<>(listHash);

        System.out.println("listHash" + listHash);
        // System.out.println("listTree" + listTree);
        // System.out.println("listLink" + listLink);
    }

    // ─────────────────────────────────────────────
    // 20. Map (HashMap, TreeMap, LinkedHashMap, Hashtable)
    // Key-value pairs
    // ─────────────────────────────────────────────
    public static void mapDemo() {
        Map<String, Integer> list = new HashMap<>();
        list.put("A", 1);
        list.put("C", 3);
        list.put("B", 2);
        list.put("B", 2);

        Map<String, Integer> treeList = new TreeMap<>(list);
        Map<String, Integer>  linkList = new LinkedHashMap<>(list);
        Map<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("X", 100);

        // System.out.println("HashMap " + list);
        // System.out.println("TreeMap " + treeList);
        // System.out.println("LinkedHashMap " + linkList);
        // System.out.println("Hashtable " + hashtable);
    }

    // ─────────────────────────────────────────────
    // 21. Iterate HashMap
    // ─────────────────────────────────────────────
    public static void iterateMapDemo() {
        Map<String, Integer> list = new HashMap<>();
        list.put("A", 1);
        list.put("C", 3);
        list.put("B", 2);
        list.put("B", 2);

        for(String key:list.keySet()) {
            System.out.println("iterateMapDemo "+ key + list.get(key));
        }
        
    }

    // ============================================================
    // 🔥 EXTRA IMPORTANT TOPICS
    // ============================================================

    // Queue (FIFO)
    public static void queueDemo() {
        System.out.println("queueDemo");
    }

    // Stack (LIFO)
    public static void stackDemo() {
        System.out.println("stackDemo");
    }

    // Sorting
    public static void sortingDemo() {
        System.out.println("sortingDemo");
    }
}