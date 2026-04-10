// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Car a = new Car();
        Car b = new Car("Toyota", "Camry", 2023);
        Car c = new Car(b);

        System.out.println(a.getInfo());
        System.out.println(b.getInfo());
        System.out.println(c.getInfo());

        // Real-world
        User u1 = new User("Alice", "alice@email.com");
        User u2 = new User("Bob");
        System.out.println(u1.info());
        System.out.println(u2.info());
    }
}

class Car {
    String brand, model;
    int year;

    Car() { this.brand = "Unknown"; this.model = "Unknown"; this.year = 0; }

    Car(String brand, String model, int year) {
        this.brand = brand; this.model = model; this.year = year;
    }

    Car(Car c) { this.brand = c.brand; this.model = c.model; this.year = c.year; }

    String getInfo() { return brand + " " + model + " (" + year + ")"; }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// User registration — default email if not provided
// ─────────────────────────────────────────────────────────────
class User {
    String name, email;

    User(String name) {                         // no email provided
        this.name  = name;
        this.email = "no-email@default.com";    // default value
    }

    User(String name, String email) {           // full constructor
        this.name  = name;
        this.email = email;
    }

    String info() { return name + " | " + email; }
}

// Output:
// Unknown Unknown (0)
// Toyota Camry (2023)
// Toyota Camry (2023)
// Alice | alice@email.com
// Bob | no-email@default.com
