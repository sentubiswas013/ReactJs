// ============================================================
// Design Patterns: Singleton, Factory, Builder, Prototype
// ============================================================

class Main {
    public static void main(String[] args) {

        singletonDemo();
        factoryDemo();
        builderDemo();
        prototypeDemo();
    }

    // ─────────────────────────────────────────────
    // 1. Singleton Pattern
    // Only one instance
    // ─────────────────────────────────────────────
    static void singletonDemo() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println("Singleton same instance: " + (s1 == s2));
    }

    // ─────────────────────────────────────────────
    // 2. Factory Pattern
    // Create objects without exposing creation logic
    // ─────────────────────────────────────────────
    static void factoryDemo() {
        Shape shape1 = ShapeFactory.getShape("CIRCLE");
        shape1.draw();

        Shape shape2 = ShapeFactory.getShape("SQUARE");
        shape2.draw();
    }

    // ─────────────────────────────────────────────
    // 3. Builder Pattern
    // Step-by-step object creation
    // ─────────────────────────────────────────────
    static void builderDemo() {
        User user = new User.Builder()
                .setName("John")
                .setAge(25)
                .build();

        System.out.println(user);
    }

    // ─────────────────────────────────────────────
    // 4. Prototype Pattern
    // Clone existing object
    // ─────────────────────────────────────────────
    static void prototypeDemo() {
        Prototype original = new Prototype("Data");
        Prototype copy = original.clone();

        System.out.println("Original: " + original.value);
        System.out.println("Copy: " + copy.value);
    }
}


// ============================================================
// 1. Singleton Pattern - Thread Safe with Double-Checked Locking 
// ============================================================
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}


// ============================================================
// 2. Factory Pattern:  Create objects without exposing creation logic
// ============================================================
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
    public static Shape getShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE"))
            return new Circle();
        else if (type.equalsIgnoreCase("SQUARE"))
            return new Square();
        return null;
    }
}


// ============================================================
// 3. Builder Pattern - Step-by-step object creation
// ============================================================
class User {
    private String name;
    private int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    static class Builder {
        private String name;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
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
// 4. Prototype Pattern - Clone existing object 
// ============================================================
class Prototype implements Cloneable {
    String value;

    Prototype(String value) {
        this.value = value;
    }

    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}