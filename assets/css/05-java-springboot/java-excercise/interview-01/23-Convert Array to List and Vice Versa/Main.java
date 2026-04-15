import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        String[] arr  = {"a", "b", "c"};
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        String[] back = list.toArray(new String[0]);
        System.out.println(list + " | " + Arrays.toString(back));

        // Real-world
        String[] roles   = {"ADMIN", "USER", "GUEST"};
        List<String> roleList = new ArrayList<>(Arrays.asList(roles));
        roleList.add("MODERATOR");
        roleList.remove("GUEST");

        String[] updatedRoles = roleList.toArray(new String[0]);
        System.out.println("Roles list:  " + roleList);
        System.out.println("Roles array: " + Arrays.toString(updatedRoles));
    }
}

// Output:
// [a, b, c] | [a, b, c]
// Roles list:  [ADMIN, USER, MODERATOR]
// Roles array: [ADMIN, USER, MODERATOR]
