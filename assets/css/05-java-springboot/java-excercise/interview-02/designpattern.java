// ============================================================
// 🚀 DESIGN PATTERNS - INTERVIEW MASTER FILE
// Covers: Singleton, Factory, Builder, Prototype
// ============================================================

class Main {

    public static void main(String[] args) {

        System.out.println("===== SINGLETON =====");
        singletonDemo();

        System.out.println("\n===== FACTORY =====");
        factoryDemo();

        System.out.println("\n===== BUILDER =====");
        builderDemo();

        System.out.println("\n===== PROTOTYPE =====");
        prototypeDemo();
    }

    // ============================================================
    // 1. Singleton Pattern
    // ============================================================
    static void singletonDemo() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println("Same instance: " + (s1 == s2));
    }

    // ============================================================
    // 2. Factory Pattern
    // ============================================================
    static void factoryDemo() {
        Shape shape1 = ShapeFactory.getShape(ShapeType.CIRCLE);
        shape1.draw();

        Shape shape2 = ShapeFactory.getShape(ShapeType.SQUARE);
        shape2.draw();
    }

    // ============================================================
    // 3. Builder Pattern
    // ============================================================
    static void builderDemo() {
        User user = new User.Builder("John")  // required field
                .age(25)                     // optional field
                .build();

        System.out.println(user);
    }

    // ============================================================
    // 4. Prototype Pattern
    // ============================================================
    static void prototypeDemo() {
        Prototype original = new Prototype("Data");
        Prototype copy = original.clone();

        System.out.println("Original: " + original.getValue());
        System.out.println("Copy: " + copy.getValue());
    }
}


// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking)
// ============================================================
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}


// ============================================================
// 2. Factory Pattern (Best Practice using Enum)
// Factory method to create objects based on type
// ============================================================

enum ShapeType {
    CIRCLE, SQUARE
}

interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class ShapeFactory {

    public static Shape getShape(ShapeType type) {

        switch (type) {
            case CIRCLE:
                return new Circle();
            case SQUARE:
                return new Square();
            default:
                throw new IllegalArgumentException("Invalid shape type");
        }
    }
}


// ============================================================
// 3. Builder Pattern (Immutable Object - BEST PRACTICE)
// ============================================================
final class User {
    private final String name;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder {
        private final String name; // required
        private int age;           // optional

        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}


// ============================================================
// 4. Prototype Pattern (Cloning)
// ============================================================
class Prototype implements Cloneable {

    private String value;

    public Prototype(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Prototype clone() {
        try {
            return (Prototype) super.clone(); // shallow copy
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}