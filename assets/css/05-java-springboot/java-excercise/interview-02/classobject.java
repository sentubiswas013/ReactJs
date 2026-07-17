class Main {
    public static void main(String[] args) {
        ConstructStaticDemo();
        FinalVariableMethodDemo();
        FinalClassDemo();
        InterfaceDemo();
        SuperThisDemo();
        ImmutableDemo();
    }

// ============================================================
// Class, Constructor + Static + Final Variable
// ============================================================
public static void ConstructStaticDemo() {
    System.out.println("=========================== InterfaceDemo");
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


static class Student {
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
// Final Variable Once assigned, value cannot be changed.
// Final method cannot be overridden
// ============================================================
public static void FinalVariableMethodDemo() {
    System.out.println("=========================== FinalVariableMethodDemo");
}
static class FinalVariableMethodDemo {

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
static class FinalChild extends FinalVariableMethodDemo {

    // ❌ This will give compile error if uncommented
    /*
    void displayMessage() {
        System.out.println("Overriding not allowed");
    }
    */
}


// ============================================================
// Final Class: cannot be extended to(subclassed). it is Used to Prevent inheritance for security (e.g., String, Integer) and Ensure immutability
// ============================================================
public static void FinalClassDemo() {
    System.out.println("=========================== FinalClassDemo");
}

final class FinalClassDemo {
    void show() {
        System.out.println("Final class method");
    }
}

// ❌ Not allowed
// static class Test extends FinalClassDemo {
// }



// ============================================================
// Interface: An Interface in Java is a blueprint of a class that defines a set of abstract methods which implementing classes must provide. It is mainly used to achieve abstraction and multiple inheritance.
// ============================================================
public static void InterfaceDemo() {
    System.out.println("=========================== InterfaceDemo");
    Car car = new Car();
    car.start();
}

interface Vehicle {
    void start();
}

static class Car implements Vehicle {
    public void start() {
        System.out.println("Car starts");
    }
}


// ============================================================
// this refers to the current object instance,
// super refers to the immediate parent class object.
// ============================================================
public static void SuperThisDemo() {
    System.out.println("=========================== SuperThisDemo");
    Child c = new Child();
    c.show();
}


static class Parent {
    String msg = "Parent";

    Parent() {
        System.out.println("Parent Constructor");
    }
}

static class Child extends Parent {
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
public static void ImmutableDemo() {
    System.out.println("=========================== ImmutableDemo");
    Person p = new Person("Pintu");

    System.out.println(p.getName()); // Pintu

    // p.name = "Raju"; ❌ Not allowed
    // No setter method ❌
}

static final class Person {

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


// ============================================================
//Shallow Copy creates a new object, but the nested objects are shared between the original and copied object.
// Deep Copy creates a new object along with completely new copies of all nested objects, so both objects are fully independent.
//============================================================


public static void () {
    Address address = new Address("Bangalore");
    Employee emp1 = new Employee("John", address);

    // Shallow Copy
    Employee emp2 = new Employee(emp1);
    emp2.address.city = "Mumbai";

    System.out.println(emp1.address.city); // Mumbai

    // Deep Copy
    Employee emp3 = new Employee(emp1, true);
    emp3.address.city = "Delhi";

    System.out.println(emp1.address.city); // Mumbai
    System.out.println(emp3.address.city); // Delhi
}

static class Address {
    String city;

    Address(String city) {
        this.city = city;
    }

    Address(Address other) {      // Deep copy constructor
        this.city = other.city;
    }
}

static class Employee {
    String name;
    Address address;

    Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow Copy
    Employee(Employee other) {
        this.name = other.name;
        this.address = other.address;
    }

    // Deep Copy
    Employee(Employee other, boolean deep) {
        this.name = other.name;
        this.address = new Address(other.address);
    }
}


}
