import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Set<Integer> hs = new HashSet<>();
        hs.add(3); hs.add(1); hs.add(3);
        System.out.println("HashSet: " + hs);

        Set<Integer> ts = new TreeSet<>(hs);
        System.out.println("TreeSet: " + ts);

        // Real-world
        TagManager tags = new TagManager();
        tags.add("java"); tags.add("spring"); tags.add("java"); // duplicate ignored
        tags.showUnordered();
        tags.showSorted();
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Blog tag manager — no duplicate tags, sorted display
// ─────────────────────────────────────────────────────────────
class TagManager {
    private Set<String> tags = new HashSet<>();

    void add(String tag) { tags.add(tag); }

    void showUnordered() { System.out.println("Tags (unordered): " + tags); }

    void showSorted()    { System.out.println("Tags (sorted): " + new TreeSet<>(tags)); }
}

// Output:
// HashSet: [1, 3]
// TreeSet: [1, 3]
// Tags (unordered): [java, spring]
// Tags (sorted): [java, spring]
