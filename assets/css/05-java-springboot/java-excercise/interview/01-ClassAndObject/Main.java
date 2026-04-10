// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.name = "Alice";
        p.age  = 25;
        System.out.println(p.info());

        // Real-world
        Product product = new Product("Laptop", 999.99);
        System.out.println(product.info());
    }
}

class Person {
    String name;
    int age;

    String info() {
        return name + " | " + age;
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// E-commerce product with fields and behaviour
// ─────────────────────────────────────────────────────────────
class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name  = name;
        this.price = price;
    }

    String info() {
        return name + " costs $" + price;
    }
}

// Output:
// Alice | 25
// Laptop costs $999.99
