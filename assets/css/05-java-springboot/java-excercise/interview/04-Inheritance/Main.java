// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Manager m = new Manager();
        m.breathe(); m.work(); m.manage();

        Developer d = new Developer();
        d.breathe(); d.work(); d.code();

        // Real-world
        AdminUser admin = new AdminUser("Alice", "admin@app.com");
        admin.login();
        admin.deleteUser("Bob");

        GuestUser guest = new GuestUser("Guest123");
        guest.login();
        guest.browse();
    }
}

class Person { void breathe() { System.out.println("Person: breathe"); } }
class Employee extends Person { void work() { System.out.println("Employee: work"); } }
class Manager extends Employee { void manage() { System.out.println("Manager: manage team"); } }
class Developer extends Employee { void code() { System.out.println("Developer: write code"); } }

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// User roles — Admin and Guest both extend BaseUser
// ─────────────────────────────────────────────────────────────
class BaseUser {
    String name, email;

    BaseUser(String name, String email) { this.name = name; this.email = email; }

    void login() { System.out.println(name + " logged in"); }
}

class AdminUser extends BaseUser {
    AdminUser(String name, String email) { super(name, email); }

    void deleteUser(String target) { System.out.println(name + " deleted user: " + target); }
}

class GuestUser extends BaseUser {
    GuestUser(String name) { super(name, "guest@app.com"); }

    void browse() { System.out.println(name + " is browsing (read-only)"); }
}

// Output:
// Person: breathe  Employee: work  Manager: manage team
// Person: breathe  Employee: work  Developer: write code
// Alice logged in
// Alice deleted user: Bob
// Guest123 logged in
// Guest123 is browsing (read-only)
