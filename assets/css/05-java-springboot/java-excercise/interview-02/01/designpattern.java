// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking)
// Singleton Pattern is a design pattern that ensures a class has only one object (instance) and provides a global access point to that instance.

// Rules to create Singleton Pattern:
// Make class final to prevent subclassing
// Private constructor to prevent instantiation from outside the class
// Private static variable to hold the single instance of the class
// Public static method that returns the instance of the class, creating it if it doesn't exist yet


// When to use Singleton Pattern in real life:
// 1. When you want to control access to a shared resource (like a database connection).
// 2. When you want to ensure that only one instance of a class is created and used throughout the application (like a configuration manager).
// 3. When you want to implement a global point of access to a resource (like a logging service).   
// ============================================================
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        System.out.println("Singleton instance created: " + this.hashCode());
    }

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

class SingletonDemo {
    public static void main(String[] args) {

        // Create multiple threads to test thread safety
        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() +
                    " got instance: " + instance.hashCode());
        };

        // Creating multiple threads
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}

// Output: 
// Singleton instance created: 1530262698
// Thread-2 got instance: 1530262698
// Thread-1 got instance: 1530262698


// ============================================================
// 2. Factory Pattern (Best Practice using Enum)
// Factory Design Pattern is used to create objects without using new keyword directly, by using a factory method.

// Rules to create Factory Pattern:
// 1. Create an interface or abstract class for the type of object you want to create.
// 2. Create concrete classes that implement the interface or extend the abstract class.
// 3. Create a Factory class with a static method that takes input (like an enum


// When to use Factory Pattern:
// 1. When you have a super class with multiple sub-classes and based on input, you need to return one of the sub-class.
// 2. When you want to decouple the client code from the actual implementation of the objects it needs to create.

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

class ShapeFactory {

    public static Shape getShape(ShapeType type) {

        switch (type) {
            case CIRCLE:
                return new Circle();
            default:
                throw new IllegalArgumentException("Invalid shape type");
        }
    }
}

class FactoryDesignDemo {
    public static void main(String[] args) {

        // Create Circle
        Shape shape1 = ShapeFactory.getShape(ShapeType.CIRCLE);
        shape1.draw();
    }
}

// Output:
// Drawing Circle
// Drawing Square


// ============================================================
// 3. Builder Pattern (Immutable Object - BEST PRACTICE)
// Builder Pattern is used to create complex objects step by step, especially when an object has many optional parameters.

// Rules to create Builder Pattern:
// 1. Create a static nested Builder class inside the main class.
// 2. The Builder class should have the same fields as the main class.
// 3. The Builder class should have setter-like methods that return the Builder instance (for chaining).
// 4. Create a build() method in the Builder class that returns the final object.

// When to use Builder Pattern:
// 1. When you have a class with many parameters (especially optional ones) and want to avoid constructor overloading.
// 2. When you want to create immutable objects with many parameters.

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

class BuilderPatternDemo {
    public static void main(String[] args) {

        // Create user with only required field
        User user1 = new User.Builder("Alice").build();

        // Create user with optional field
        User user2 = new User.Builder("Bob")
                            .age(25)
                            .build();

        System.out.println(user1);
        System.out.println(user2);
    }
}

// Output:
// User{name='Alice', age=0}
// User{name='Bob', age=25}

// ============================================================
// 4. Prototype Pattern (Cloning)
// Prototype Pattern is a Creational Design Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.

// Rules to create Prototype Pattern:
// 1. Implement the Cloneable interface in the class you want to clone.
// 2. Override the clone() method to return a copy of the object.
// 3. Use the clone() method to create new objects by copying existing ones.    

// When to use Prototype Pattern:
// 1. When object creation is expensive and you want to create new objects by copying existing ones.
// 2. When you want to hide the creation logic from the client code.
// ============================================================
class PrototypeDemo {
    public static void main(String[] args) throws Exception {
        Student s1 = new Student(1, "John");
        Student s2 = (Student) s1.clone();

        System.out.println(s1.name);
        System.out.println(s2.name);
    }
}

class Student implements Cloneable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}