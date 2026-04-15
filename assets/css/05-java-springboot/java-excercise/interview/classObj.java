// ============================================================
// 01–10 + Static + Final Concepts in One File (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {

        // 01. Class & Object
        Student s1 = new Student("John", 20);
        s1.display();

        // 02. Constructor
        Student s2 = new Student();
        s2.display();

        // 03. Overloading
        MathUtils mu = new MathUtils();
        System.out.println("Add: " + mu.add(10, 20));
        System.out.println("Add (double): " + mu.add(10.5, 20.5));

        // 04 + 07: Inheritance + Runtime Polymorphism
        Animal a = new Dog();
        a.sound();

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

        // 09. Static Variable
        System.out.println("Static count: " + Student.count);

        // Static Method
        Student.showCount();

        // Static Nested Class
        Student.StaticHelper helper = new Student.StaticHelper();
        helper.showMessage();

        // Final variable usage
        FinalExample fe = new FinalExample();
        fe.show();

        // 10. this & super
        Child c = new Child();
        c.show();
    }
}


// ============================================================
// Class, Constructor + Static + Final Variable
// ============================================================
class Student {
    String name;
    int age;

    static int count = 0; // static variable

    static {
        System.out.println("Static block executed");
    }

    Student() {
        this("Default", 0);
    }

    Student(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }

    void display() {
        System.out.println("Student: " + name + ", " + age);
    }

    static void showCount() {
        System.out.println("Total Students: " + count);
    }

    static class StaticHelper {
        void showMessage() {
            System.out.println("Inside Static Nested Class");
        }
    }
}


// ============================================================
// Final Variable + Final Method
// ============================================================
class FinalExample {

    // final variable (constant)
    final int MAX_VALUE = 100;

    void show() {
        System.out.println("Final variable: " + MAX_VALUE);
    }

    // final method (cannot be overridden)
    final void displayMessage() {
        System.out.println("This is a final method");
    }
}


// Trying to override final method → NOT allowed
class FinalChild extends FinalExample {

    // ❌ This will give compile error if uncommented
    /*
    void displayMessage() {
        System.out.println("Overriding not allowed");
    }
    */
}


// ============================================================
// Final Class (cannot be extended)
// ============================================================
final class FinalClass {
    void show() {
        System.out.println("Final class method");
    }
}

// ❌ Not allowed
/*
class Test extends FinalClass {
}
*/


// ============================================================
// Method Overloading
// ============================================================
class MathUtils {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}


// ============================================================
// Inheritance + Polymorphism + Overriding
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
// Abstract Class
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
// Encapsulation
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
// Interface
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
// this & super
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
        super();
    }

    void show() {
        System.out.println("this.msg: " + this.msg);
        System.out.println("super.msg: " + super.msg);
    }
}