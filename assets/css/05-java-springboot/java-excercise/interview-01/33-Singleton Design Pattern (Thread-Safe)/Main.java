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

    // 1️⃣ Static variable to hold only ONE instance of the class
    private static Singleton instance;

    // 2️⃣ Private constructor prevents object creation from outside the class
    private Singleton() {}

    // synchronized ensures only ONE thread executes at a time
    // == public static synchronized Singleton getInstance() {

    // 3️⃣ Public method to provide global access to the instance
    public static Singleton getInstance() {

        // 4️⃣ Lazy initialization:
        // Object is created ONLY when it is needed (first call)
        if(instance == null) {

            // 5️⃣ Only ONE object is created and stored in static variable
            instance = new Singleton();
        }

        // 6️⃣ Always return the SAME instance (no new object creation)
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
