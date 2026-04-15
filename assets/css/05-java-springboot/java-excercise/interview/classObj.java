// ============================================================
// 01–10: OOP Concepts in One File (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {

        // 01. Class & Object
        Student s1 = new Student("John", 20);
        s1.display();

        // 02. Constructor
        Student s2 = new Student(); // default constructor
        s2.display();

        // 03. Overloading
        MathUtils mu = new MathUtils();
        System.out.println("Add: " + mu.add(10, 20));
        System.out.println("Add (double): " + mu.add(10.5, 20.5));

        // 03. Overriding + 04. Inheritance + 07. Polymorphism
        Animal a = new Dog(); // upcasting
        a.sound(); // runtime polymorphism

        // 05. Abstract
        Shape shape = new Circle();
        shape.draw();

        // 06. Encapsulation
        Person p = new Person();
        p.setName("Alice");
        System.out.println("Encapsulation: " + p.getName());

        // 08. Interface
        Vehicle v = new Car();
        v.start();

        // 09. Static Keyword
        System.out.println("Static count: " + Student.count);

        // 10. this & super
        Child c = new Child();
        c.show();
    }
}


// ============================================================
// 01 & 02: Class, Object, Constructor
// ============================================================
class Student {
    String name;
    int age;
    static int count = 0;

    // Default constructor
    Student() {
        this("Default", 0);
    }

    // Parameterized constructor
    Student(String name, int age) {
        this.name = name; // this keyword
        this.age = age;
        count++;
    }

    void display() {
        System.out.println("Student: " + name + ", " + age);
    }
}


// ============================================================
// 03: Method Overloading
// ============================================================
class MathUtils {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) { // overloaded
        return a + b;
    }
}


// ============================================================
// 04: Inheritance + 07: Polymorphism + 03: Overriding
// ============================================================
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}


// ============================================================
// 05: Abstract Class
// ============================================================
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}


// ============================================================
// 06: Encapsulation
// ============================================================
class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// ============================================================
// 08: Interface
// ============================================================
interface Vehicle {
    void start();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car starts");
    }
}


// ============================================================
// 10: this & super
// ============================================================
class Parent {
    String msg = "Parent";

    Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {
    String msg = "Child";

    Child() {
        super(); // calls parent constructor
    }

    void show() {
        System.out.println("this.msg: " + this.msg);
        System.out.println("super.msg: " + super.msg);
    }
}