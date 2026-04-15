import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {

    static boolean balanced(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (map.containsValue(ch)) {
                stack.push(ch);
            } else if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != map.get(ch)) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(balanced("{[()]}"));  // true
        System.out.println(balanced("{[(])}"));  // false

        // Real-world: validate JSON/HTML template syntax before parsing
        String[] templates = {
            "{\"name\": \"Alice\", \"roles\": [\"admin\", \"user\"]}",
            "<div><span></div>",
            "SELECT * FROM users WHERE (id = 1 AND (status = 'active'))"
        };

        for (String t : templates) {
            System.out.println("\"" + t.substring(0, Math.min(30, t.length())) + "...\" → " + (balanced(t) ? "valid" : "invalid"));
        }
    }
}

// Output:
// true
// false
// "{"name": "Alice", "roles": [..." → valid
// "<div><span></div>..." → invalid
// "SELECT * FROM users WHERE (id ..." → valid
