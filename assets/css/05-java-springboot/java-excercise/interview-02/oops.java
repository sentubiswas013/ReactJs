class Oops {
    public static void main(String[] args) {
        EncapsulationDemo();
        InheritanceDemo();
        PolymorphismDemo();
        AbstractClassDemo();
    }

// ============================================================
// Encapsulation:: Data is private and accessed using getter and setter methods. 👉 Hide data and control access. Keep data safe and access it through methods
// ============================================================
static class Person {
    private String name;   // private variable

    public void setName(String name) {   // setter
        this.name = name;
    }

    public String getName() {   // getter
        return name;
    }
}

public static void EncapsulationDemo() {
    System.out.println("=========================== EncapsulationDemo");
    Person p = new Person();
    p.setName("John");
    System.out.println(p.getName());
}



// ============================================================
// Inheritance :: Child class inherits properties from parent class using extends. 👉 Reuse parent properties in child. Child gets features from parent
// ============================================================
static class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

static class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

public static void InheritanceDemo() {
    System.out.println("=========================== InheritanceDemo");
    Dog d = new Dog();
    d.eat();   // inherited method
    d.bark();
}


// ============================================================
// Polymorphism :: Method Overloading 👉 Same method, different behavior. One thing, many forms
// ============================================================
static class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public static void PolymorphismDemo() {
    System.out.println("=========================== PolymorphismDemo");
    Calculator c = new Calculator();
    System.out.println(c.add(5, 10));
    System.out.println(c.add(5, 10, 15));
}



// ============================================================
// Abstraction :: abstract class or interface 👉 Hide complexity, show only needed parts. Show what is needed, hide the rest.
// ============================================================
static abstract class Vehicle {
    abstract void start();   // abstract method
}

static class Car extends Vehicle {
    void start() {
        System.out.println("Car starts with key");
    }
}


public static void AbstractClassDemo() {
    System.out.println("=========================== AbstractClassDemo");
    Vehicle v = new Car();
    v.start();
}



interface VehicleB {
    void sound();
}

static class CarV implements VehicleB {
    public void sound() {
        System.out.println("Car makes engine sound");
    }
}

}
