// ─────────────────────────────────────────────────────────────
// SINGLETON RULES
// ─────────────────────────────────────────────────────────────
// 1. PRIVATE constructor         — prevents `new Singleton()` from outside
// 2. PRIVATE STATIC instance     — holds the one and only object
// 3. VOLATILE keyword            — ensures visibility across threads (no stale cache)
// 4. PUBLIC STATIC getInstance() — the only way to access the instance
// 5. DOUBLE-CHECKED LOCKING      — first null check avoids lock overhead after init
//                                  second null check inside sync block prevents
//                                  duplicate creation when two threads pass first check
// 6. SYNCHRONIZED block          — locks only on creation, not on every call (performance)
// ─────────────────────────────────────────────────────────────

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {

        // Practice
        System.out.println(Singleton.getInstance());

        // Real-world
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db1.query("SELECT * FROM users");
        System.out.println(db1 == db2); // true — same instance
    }
}

class Singleton {

    private static Singleton instance;          // Rule 2

    private Singleton() {}                      // Rule 1

    public static Singleton getInstance() {     // Rule 4
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE  (Thread-Safe with Double-Checked Locking)
// ─────────────────────────────────────────────────────────────
class DatabaseConnection {

    private static volatile DatabaseConnection instance;  // Rule 2 + 3

    private DatabaseConnection() {                        // Rule 1
        System.out.println("DB connected");
    }

    public static DatabaseConnection getInstance() {      // Rule 4
        if (instance == null) {                           // Rule 5 — first check
            synchronized (DatabaseConnection.class) {    // Rule 6
                if (instance == null) {                  // Rule 5 — second check
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
}

// Output:
// Main@<hashcode>                     ← Singleton instance
// DB connected                        ← created only ONCE
// Executing: SELECT * FROM users
// true                                ← db1 and db2 are same object
