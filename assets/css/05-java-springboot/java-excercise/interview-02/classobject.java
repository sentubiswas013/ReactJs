// ============================================================
// Class, Constructor + Static + Final Variable
// ============================================================
class Main {
    public static void main(String[] args) {

        // Creating objects
        Student s1 = new Student("Pintu", 25);
        Student s2 = new Student("Raju", 22);
        Student s3 = new Student(); // default constructor

        // Display student info
        s1.display();
        s2.display();
        s3.display();

        // Show total count
        Student.showCount();

        // Access static nested class (no need to create outer object)
        Student.StaticHelper helper = new Student.StaticHelper();
        helper.showMessage();
    }
}

class Student {
    private String name;
    private int age;

    private static int count = 0; // encapsulated

    // Static block
    static {
        System.out.println("Static block executed");
    }

    // Default constructor
    Student() {
        this("Default", 0);
    }

    // Parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }

    // Display method
    void display() {
        System.out.println("Student: " + name + ", " + age);
    }

    // Static method
    static void showCount() {
        System.out.println("Total Students: " + count);
    }

    // Static Nested Class
    static class StaticHelper {
        void showMessage() {
            System.out.println("Inside Static Nested Class");
        }
    }
}

// Output: 
// Static block executed
// Student: Pintu, 25
// Student: Raju, 22
// Student: Default, 0
// Total Students: 3
// Inside Static Nested Class



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

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
    }
}


// ============================================================
// this & super
// ============================================================

class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.show();
    }
}

class Parent {
    String msg = "Parent";

    Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {
    String msg = "Child";

    Child() {
        super(); // calls Parent constructor
    }

    void show() {
        System.out.println("this.msg: " + this.msg);
        System.out.println("super.msg: " + super.msg);
    }
}


// Output
// Parent Constructor
// this.msg: Child
// super.msg: Parent


// ============================================================
// Immutable class is a class whose object state cannot be changed after it is created.
// ============================================================
// **Immutable Class Rules**

// * ✔ **Make the class `final`**
//   → Prevents subclassing (so no one can override behavior)

// * ✔ **Make all fields `private`**
//   → Data cannot be accessed directly from outside

// * ✔ **Make all fields `final`**
//   → Values can be assigned only once (during object creation)

// * ✔ **Initialize fields through constructor only**
//   → No other way to set values

// * ✔ **Do NOT provide setter methods**
//   → Prevents modification after object creation

// * ✔ **Provide only getter methods**
//   → To read data safely

// * ✔ **For mutable objects, return a copy (defensive copy)**
//   → Avoid exposing internal state
//   Example: return new List instead of original

class Main {
    public static void main(String[] args) {
        Person p = new Person("Pintu");

        System.out.println(p.getName()); // Pintu

        // p.name = "Raju"; ❌ Not allowed
        // No setter method ❌
    }
}
final class Person {

    private final String name;

    // Constructor
    public Person(String name) {
        this.name = name;
    }

    // Getter only (no setter)
    public String getName() {
        return name;
    }
}

