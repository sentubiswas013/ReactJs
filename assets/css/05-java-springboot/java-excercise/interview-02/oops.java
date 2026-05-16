// ============================================================
// Encapsulation:: Data is private and accessed using getter and setter methods. 👉 Hide data and control access. Keep data safe and access it through methods

// Rules for Encapsulation:
// 1. Make all fields private
// 2. Provide public getter and setter methods
// 3. Use read-only or write-only access when needed
// 4. Optionally, make the class final to prevent subclassing
// ============================================================
class Person {
    private String name;   // private variable

    public void setName(String name) {   // setter
        this.name = name;
    }

    public String getName() {   // getter
        return name;
    }
}

class EncapsulationDemo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("John");
        System.out.println(p.getName());
    }
}


// ============================================================
// Inheritance :: Child class inherits properties from parent class using extends. 👉 Reuse parent properties in child. Child gets features from parent

// Rules for Inheritance:
// 1. Use `extends` keyword to create a child class
// 2. Child class inherits all non-private members of parent class
// 3. Child class can override parent methods to provide specific implementation
// ============================================================

class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

class InheritanceDemo {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // inherited method
        d.bark();
    }
}


// ============================================================
// Polymorphism :: Method Overloading 👉 Same method, different behavior. One thing, many forms

// Rules for Polymorphism:
// 1. Method Overloading: Same method name with different parameters in the same class.
// 2. Method Overriding: Same method name and parameters in parent and child class, but different implementation in child class.
// 3. Runtime Polymorphism: Parent reference can point to child object, and overridden method will be called at runtime.
// ============================================================
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class PolymorphismDemo {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.add(5, 10));
        System.out.println(c.add(5, 10, 15));
    }
}


// ============================================================
// Abstraction :: abstract class or interface 👉 Hide complexity, show only needed parts. Show what is needed, hide the rest.

// Rules for Abstraction:
// 1. Use `abstract` keyword to create an abstract class or method.
// 2. Abstract class can have both abstract and non-abstract methods, while interface can only have

// Why Use Abstraction?
// Hides complexity
// Improves security
// Makes code flexible & maintainable
// ============================================================
class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();
    }
}

// 1. Abstract Class (0–100% abstraction)
// Use cases:
// You want some methods implemented, some not
// You want to reuse code
// You need instance variables (state)
abstract class AnimalB {
    abstract void sound(); // no implementation

    void eat() { // concrete method
        System.out.println("Animal is eating");
    }
}

class DogB extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

// 2. Interface (100% abstraction – mostly)
// Use cases:
// Multiple classes need to follow same behavior
// No shared state needed
// You want multiple inheritance

interface AnimalC {
    void sound();
}

class DogC implements AnimalC {
    public void sound() {
        System.out.println("Dog barks");
    }
}


