
# ✅ 3. Java Classes and Objects

---

## 1. What is the difference between `this` and `super` keywords?

- `this` refers to the **current class instance** — used to access current class fields, methods, or constructors.
- `super` refers to the **immediate parent class** — used to access parent class fields, methods, or constructors.

```java
class Animal {
    String name = "Animal";
    void sound() { System.out.println("Some sound"); }
}

class Dog extends Animal {
    String name = "Dog";

    void display() {
        System.out.println(this.name);   // Dog  (current class)
        System.out.println(super.name);  // Animal (parent class)
        super.sound();                   // calls parent method
    }
}
```

---

## 2. What is method overloading?

Method overloading means **multiple methods with the same name but different parameters** (type, number, or order) in the same class. Resolved at **compile time** (static polymorphism).

```java
class Calculator {
    int add(int a, int b)          { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c)   { return a + b + c; }
}
```

---

## 3. What is method overriding?

Method overriding means a **child class provides its own implementation** of a method already defined in the parent class, with the **same name and parameters**. Resolved at **runtime** (dynamic polymorphism).

```java
class Animal {
    void sound() { System.out.println("Some sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Dog barks"); }
}

Animal a = new Dog();
a.sound(); // Dog barks
```

---

## 4. What is a class in Java?

A class is a **blueprint or template** for creating objects. It defines the state (fields) and behavior (methods) that objects of that type will have.

```java
class Car {
    String color;
    int speed;

    void drive() {
        System.out.println("Car is driving at " + speed + " km/h");
    }
}
```

---

## 5. What is class loader and how do they work?

A **ClassLoader** is part of the JVM that **loads `.class` files into memory** at runtime.

**Delegation Hierarchy (Parent-First model):**

1. **Bootstrap ClassLoader** — loads core Java classes (`java.lang.*`, `java.util.*`)
2. **Extension (Platform) ClassLoader** — loads classes from `jre/lib/ext`
3. **Application ClassLoader** — loads classes from the application classpath

When a class is requested, the JVM delegates upward first. If the parent cannot find it, the child loads it.

```
Request to load "MyClass"
  → Application ClassLoader asks Extension ClassLoader
    → Extension asks Bootstrap ClassLoader
      → Bootstrap: not found
    → Extension: not found
  → Application ClassLoader loads it ✅
```

---

## 6. How do you create an immutable class in Java?

An immutable class is one whose **state cannot be changed after creation**.

**Rules:**
1. Declare class as `final`
2. Make all fields `private final`
3. No setters
4. Initialize via constructor
5. Return deep copies of mutable fields

```java
final class Employee {
    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge()     { return age; }
}
```

> `String`, `Integer`, `LocalDate` are built-in immutable classes in Java.

---

## 7. What is an object?

An object is an **instance of a class**. It has:
- **State** — represented by fields/variables
- **Behavior** — represented by methods
- **Identity** — unique memory address

```java
Car myCar = new Car();   // myCar is an object of class Car
myCar.color = "Red";
myCar.drive();
```

---

## 8. Difference between class and object?

| Class | Object |
|-------|--------|
| Blueprint / template | Instance of a class |
| Logical entity | Physical entity (exists in memory) |
| Declared once | Can be created many times |
| No memory at declaration | Memory allocated on `new` |

```java
class Dog { }           // class — blueprint
Dog d = new Dog();      // object — actual instance
```

---

## 9. How to create a class and object?

Use `class` keyword to define a class and `new` keyword to create an object.

```java
class Student {
    String name;
    int age;

    void display() {
        System.out.println(name + " - " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();   // object created
        s.name = "Alice";
        s.age = 20;
        s.display();                 // Alice - 20
    }
}
```

---

## 10. What are instance variables and methods?

- **Instance variables** — fields declared inside a class but outside methods. Each object gets its own copy.
- **Instance methods** — methods that operate on instance variables. Require an object to be called.

```java
class Employee {
    String name;    // instance variable
    int salary;     // instance variable

    void work() {   // instance method
        System.out.println(name + " is working");
    }
}

Employee e = new Employee();
e.name = "Bob";
e.work();   // Bob is working
```

---

## 11. What is a constructor? Types?

A constructor is a **special method** with the same name as the class, used to **initialize objects**. It has no return type and is called automatically when an object is created.

**Types:**

| Type | Description |
|------|-------------|
| Default | No parameters, provided by Java if none defined |
| Parameterized | Accepts arguments to initialize fields |
| Copy | Takes another object of same class as parameter |

```java
class Student {
    String name;

    Student() { name = "Unknown"; }              // default

    Student(String n) { name = n; }              // parameterized

    Student(Student s) { name = s.name; }        // copy
}
```

---

## 12. What is default constructor?

A default constructor is a **no-argument constructor**. If you don't define any constructor, Java automatically provides one that initializes fields to default values (`0`, `null`, `false`).

```java
class Box { int width; }

Box b = new Box();              // default constructor called
System.out.println(b.width);   // 0
```

> Once you define any constructor, Java no longer provides the default one automatically.

---

## 13. What is the static keyword?

`static` means the member **belongs to the class**, not to any specific object. It is shared across all instances and can be accessed without creating an object.

**Used for:**
- Static variables (shared state)
- Static methods (utility methods)
- Static blocks (one-time initialization)
- Static nested classes

```java
class Counter {
    static int count = 0;   // shared across all objects

    Counter() { count++; }

    static void showCount() {
        System.out.println("Count: " + count);
    }
}

new Counter(); new Counter(); new Counter();
Counter.showCount();   // Count: 3
```

---

## 14. Difference: static vs non-static?

| Static | Non-Static |
|--------|-----------|
| Belongs to the class | Belongs to the object |
| Shared across all instances | Separate copy per object |
| Accessed via class name | Accessed via object reference |
| Cannot access `this` | Can access `this` |
| Loaded at class loading time | Created when object is created |

```java
class Demo {
    static int x = 10;   // static
    int y = 20;          // non-static

    static void show() { System.out.println(x); }   // static method
    void display()     { System.out.println(y); }   // non-static method
}

Demo.show();              // no object needed
new Demo().display();     // object needed
```

---

## 15. Can a class have multiple constructors?

Yes. This is called **constructor overloading** — multiple constructors with different parameter lists.

```java
class Person {
    String name;
    int age;

    Person() { name = "Unknown"; age = 0; }

    Person(String name) { this.name = name; }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

## 16. Can a constructor be final, static, or abstract in Java?

**No.** Constructors cannot be `final`, `static`, or `abstract`.

| Modifier | Why Not Allowed |
|----------|----------------|
| `final` | Constructors are not inherited, so there's nothing to prevent overriding |
| `static` | Static belongs to the class; constructors are tied to object creation |
| `abstract` | Abstract means no body; constructors must have a body |

---

## 17. Can we make a class `final`? Why?

Yes. A `final` class **cannot be extended (subclassed)**. Used to:
- Prevent inheritance for security (e.g., `String`, `Integer`)
- Ensure immutability
- Prevent unintended behavior changes

```java
final class Constants {
    static final double PI = 3.14159;
}

// class MyConstants extends Constants { }  // ❌ Compile error
```

---

## 18. Can we make a class `abstract`?

Yes. An `abstract` class **cannot be instantiated** directly. It can have both abstract methods (no body) and concrete methods (with body). Used as a base class.

```java
abstract class Shape {
    abstract double area();          // must be implemented by subclass

    void display() {                 // concrete method
        System.out.println("Area: " + area());
    }
}

class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }

    @Override
    double area() { return Math.PI * radius * radius; }
}
```

---

## 19. What is the difference between interface and abstract class?

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Instantiation | Cannot instantiate | Cannot instantiate |
| Methods | Abstract + concrete | Abstract + default + static |
| Variables | Instance variables allowed | Only `public static final` constants |
| Constructor | Yes | No |
| Inheritance | `extends` (single) | `implements` (multiple) |
| Use case | Shared base with common state | Define a contract / capability |

```java
abstract class Vehicle {
    int speed;
    abstract void move();
    void fuel() { System.out.println("Needs fuel"); }
}

interface Electric {
    void charge();
}

class Tesla extends Vehicle implements Electric {
    void move()   { System.out.println("Tesla moves"); }
    public void charge() { System.out.println("Charging..."); }
}
```

---

## 20. Can a class be both abstract and final?

**No.** `abstract` requires the class to be subclassed (to implement abstract methods), while `final` prevents subclassing. They directly contradict each other — Java gives a compile error.

---

## 21. What is inner class? Types?

An inner class is a **class defined inside another class**. Used for logical grouping and encapsulation.

**Types:**

| Type | Description |
|------|-------------|
| Member inner class | Non-static class inside outer class |
| Static nested class | Static class inside outer class |
| Local inner class | Class inside a method |
| Anonymous class | Nameless class defined and instantiated inline |

```java
class Outer {
    class Inner { void show() { System.out.println("Member inner"); } }

    static class StaticNested { void show() { System.out.println("Static nested"); } }

    void method() {
        class Local { void show() { System.out.println("Local inner"); } }
        new Local().show();
    }
}

// Anonymous class
Runnable r = new Runnable() {
    public void run() { System.out.println("Anonymous class"); }
};
```

---

## 22. What is singleton class?

A singleton class allows **only one instance** to be created throughout the application. Achieved by making the constructor private and providing a static method to return the single instance.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() { }   // private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
System.out.println(s1 == s2);   // true — same object
```

> Used in: database connections, logging, configuration managers.

---

## 23. Is it allowed to overload main() method in Java?

**Yes.** You can overload `main()` with different parameters. However, the JVM only calls `public static void main(String[] args)` as the entry point. Other overloaded versions are treated as regular methods.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("JVM entry point");
        main(42);           // calling overloaded version manually
    }

    public static void main(int x) {
        System.out.println("Overloaded main: " + x);
    }
}
```

---

## 24. Is it allowed to override main() method in Java?

**No.** `main()` is `static`, and static methods **cannot be overridden** — they can only be hidden. The JVM always calls the `main()` of the class specified at startup, regardless of inheritance.

```java
class Parent {
    public static void main(String[] args) {
        System.out.println("Parent main");
    }
}

class Child extends Parent {
    public static void main(String[] args) {
        System.out.println("Child main");   // method hiding, not overriding
    }
}
```

---

## 25. Can you override static methods?

**No.** Static methods belong to the class, not the object. They are resolved at **compile time** based on the reference type, not the actual object. This is called **method hiding**, not overriding.

```java
class Parent {
    static void show() { System.out.println("Parent"); }
}

class Child extends Parent {
    static void show() { System.out.println("Child"); }  // hiding
}

Parent p = new Child();
p.show();   // Parent — resolved by reference type, not object type
```

---

## 26. Is it possible to execute a program without defining a main() method?

In **older Java versions (before Java 7)**, a static block could run code before the JVM looked for `main()`. But from **Java 7 onwards**, the JVM explicitly requires `main()` and throws an error if it's missing.

```java
class NoMain {
    static {
        System.out.println("Static block runs");
        System.exit(0);   // needed to prevent "main not found" error in older JVM
    }
}
// Works only in Java 6 and below — NOT recommended
```

---

## 27. Can we create an object without `new` keyword?

Yes. There are several ways:

| Method | Example |
|--------|---------|
| `clone()` | `obj2 = (MyClass) obj1.clone();` |
| Reflection | `Class.forName("MyClass").newInstance()` |
| Deserialization | `ObjectInputStream.readObject()` |
| Factory methods | `Integer.valueOf(5)` |

```java
// Using reflection
class Car { }
Car c = (Car) Class.forName("Car").getDeclaredConstructor().newInstance();
```

---

## 28. What happens if a class has no constructor?

Java automatically provides a **default no-argument constructor** that calls `super()` implicitly. All fields are initialized to their default values (`0`, `null`, `false`).

> Once you define **any** constructor, Java no longer provides the default one.

```java
class Box { int width; }       // Java adds: Box() { super(); }

Box b = new Box();             // works fine
System.out.println(b.width);  // 0
```

---

## 29. Can a class be private or protected?

- **Top-level class** — can only be `public` or package-private (default). Cannot be `private` or `protected`.
- **Inner class** — can be `private`, `protected`, `public`, or default.

```java
class Outer {
    private class Inner { }      // ✅ allowed
    protected class Helper { }   // ✅ allowed
}

// private class TopLevel { }   // ❌ compile error
```

---

## 30. What happens if a static block throws an unchecked exception?

If a static block throws an unchecked exception, the class **fails to load** and the JVM wraps it in `ExceptionInInitializerError`. Any attempt to use the class after that throws `NoClassDefFoundError`.

```java
class Config {
    static {
        System.out.println("Loading config...");
        int x = 10 / 0;   // ArithmeticException
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            new Config();
        } catch (ExceptionInInitializerError e) {
            System.out.println("Class failed to load: " + e.getCause());
        }
    }
}
```

**Output:**
```
Loading config...
Class failed to load: java.lang.ArithmeticException: / by zero
```

# ✅ 4. Java Inheritance

---

## 1. What is Inheritance in Java?

Inheritance is an OOP feature where a **child class acquires the properties and methods of a parent class** using the `extends` keyword. It promotes **code reuse** and establishes an IS-A relationship.

```java
class Animal {
    void eat() { System.out.println("Animal eats"); }
}

class Dog extends Animal {
    void bark() { System.out.println("Dog barks"); }
}

Dog d = new Dog();
d.eat();    // inherited from Animal
d.bark();   // own method
```

---

## 2. Why doesn't Java support multiple inheritance (with classes)?

Java does not support multiple inheritance with classes to avoid the **Diamond Problem** — ambiguity when two parent classes have the same method and the child doesn't know which one to call.

```java
class A { void show() { System.out.println("A"); } }
class B { void show() { System.out.println("B"); } }

// class C extends A, B { }  // ❌ Compile error — which show() to call?
```

> Java supports multiple inheritance through **interfaces** instead.

---

## 3. What is the diamond problem?

The diamond problem occurs when a class inherits from two classes that both inherit from the same base class, creating **ambiguity** about which version of a method to use.

```
      A
     / \
    B   C
     \ /
      D
```

If `B` and `C` both override a method from `A`, and `D` extends both `B` and `C`, the compiler cannot decide which version `D` should use — this is the diamond problem.

---

## 4. How does Java solve the diamond problem?

Java solves it by:
- **Not allowing multiple class inheritance**
- Allowing multiple **interface** inheritance, where conflicts must be **explicitly resolved** by overriding the method

```java
interface A { default void greet() { System.out.println("Hello from A"); } }
interface B { default void greet() { System.out.println("Hello from B"); } }

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet();   // explicitly choose which default to call
    }
}
```

---

## 5. Why do we use Inheritance?

- **Code reuse** — avoid writing the same code in multiple classes
- **Method overriding** — customize parent behavior in child
- **Polymorphism** — treat child objects as parent type
- **Maintainability** — change in parent reflects in all children

```java
class Vehicle {
    void start() { System.out.println("Vehicle started"); }
}

class Car extends Vehicle {
    void drive() { System.out.println("Car driving"); }
}

// Car reuses start() without rewriting it
```

---

## 6. What is `extends` keyword?

`extends` is used to **inherit a class** in Java. The child class gets all non-private members of the parent class.

```java
class Parent {
    void display() { System.out.println("Parent"); }
}

class Child extends Parent {
    void show() { System.out.println("Child"); }
}

Child c = new Child();
c.display();   // inherited
c.show();      // own
```

---

## 7. What is IS-A Relationship?

IS-A is an inheritance relationship — it means **one object is a type of another**. Implemented using `extends` or `implements`. Used to check with `instanceof`.

```java
class Animal { }
class Dog extends Animal { }

Dog d = new Dog();
System.out.println(d instanceof Dog);     // true
System.out.println(d instanceof Animal);  // true — Dog IS-A Animal
```

---

## 8. What is Method Overriding?

Method overriding is when a **child class provides its own implementation** of a method already defined in the parent class with the **same name, return type, and parameters**.

```java
class Animal {
    void sound() { System.out.println("Some sound"); }
}

class Cat extends Animal {
    @Override
    void sound() { System.out.println("Cat meows"); }
}

Animal a = new Cat();
a.sound();   // Cat meows — runtime decision
```

---

## 9. Types of Inheritance in Java?

| Type | Description |
|------|-------------|
| Single | One parent → one child |
| Multilevel | A → B → C (chain) |
| Hierarchical | One parent → multiple children |
| Multiple | Not supported with classes; supported via interfaces |
| Hybrid | Combination — only via interfaces |

```java
// Single
class A { }  class B extends A { }

// Multilevel
class C extends B { }

// Hierarchical
class D extends A { }  class E extends A { }
```

---

## 10. Does Java support Multiple Inheritance? Why?

**Not with classes** — to avoid the Diamond Problem and ambiguity.
**Yes with interfaces** — since Java 8, interfaces can have `default` methods, and conflicts are resolved by explicit override.

```java
interface Flyable  { default void move() { System.out.println("Flying"); } }
interface Swimmable { default void move() { System.out.println("Swimming"); } }

class Duck implements Flyable, Swimmable {
    @Override
    public void move() { Flyable.super.move(); }   // resolved explicitly
}
```

---

## 11. What is Multilevel Inheritance?

A chain of inheritance where a class inherits from a class that itself inherits from another class.

```java
class Animal {
    void eat() { System.out.println("Eating"); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking"); }
}

class Puppy extends Dog {
    void weep() { System.out.println("Weeping"); }
}

Puppy p = new Puppy();
p.eat();    // from Animal
p.bark();   // from Dog
p.weep();   // own
```

---

## 12. What is Hierarchical Inheritance?

Multiple child classes inherit from a **single parent class**.

```java
class Animal {
    void breathe() { System.out.println("Breathing"); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking"); }
}

class Cat extends Animal {
    void meow() { System.out.println("Meowing"); }
}
```

Both `Dog` and `Cat` inherit `breathe()` from `Animal`.

---

## 13. Can we override static methods?

**No.** Static methods are resolved at **compile time** based on the reference type, not the object. This is called **method hiding**, not overriding. No runtime polymorphism applies.

```java
class Parent {
    static void show() { 
        System.out.println("Parent"); 
    }
}

class Child extends Parent {
    static void show() { 
        System.out.println("Child"); 
    }  // hiding
}

Parent p = new Child();
p.show();   // Parent — reference type decides, not object type
```

---

## 14. Can we override final methods?

**No.** A `final` method cannot be overridden in any subclass. It is a compile-time error.

```java
class Animal {
    final void breathe() { System.out.println("Breathing"); }
}

class Dog extends Animal {
    // void breathe() { }  // ❌ Compile error: cannot override final method
}
```

---

## 15. What is runtime polymorphism?

Runtime polymorphism (dynamic method dispatch) is when the **method to call is decided at runtime** based on the actual object type, not the reference type. Achieved through method overriding.

```java
class Shape {
    void draw() { System.out.println("Drawing shape"); }
}

class Circle extends Shape {
    @Override
    void draw() { System.out.println("Drawing circle"); }
}

class Square extends Shape {
    @Override
    void draw() { System.out.println("Drawing square"); }
}

Shape s1 = new Circle();
Shape s2 = new Square();
s1.draw();   // Drawing circle
s2.draw();   // Drawing square
```

---

## 16. How does method overriding work internally?

JVM uses **dynamic method dispatch** — it maintains a **vtable (virtual method table)** for each class. When an overridden method is called on a parent reference, the JVM looks up the actual object's vtable at runtime and calls the correct method.

```
Parent ref → points to Child object
JVM checks Child's vtable → finds overridden method → calls Child's version
```

This is why `Animal a = new Dog(); a.sound();` calls `Dog`'s `sound()`, not `Animal`'s.

---

## 17. What are rules for method overriding?

| Rule | Detail |
|------|--------|
| Same method name | Must match exactly |
| Same parameters | Same type, number, order |
| Return type | Same or covariant (subtype) |
| Access modifier | Same or more accessible (not more restrictive) |
| Exceptions | Can throw fewer/narrower checked exceptions |
| Cannot override | `static`, `final`, `private` methods |

```java
class Parent {
    protected Number getValue() throws Exception { return 1; }
}

class Child extends Parent {
    @Override
    public Integer getValue() { return 42; }  // ✅ covariant return, wider access, fewer exceptions
}
```

---

## 18. What is covariant return type?

Covariant return type means the **overriding method can return a subtype** of the return type declared in the parent method. Introduced in Java 5.

```java
class Animal {
    Animal create() { return new Animal(); }
}

class Dog extends Animal {
    @Override
    Dog create() { return new Dog(); }   // ✅ Dog is subtype of Animal
}
```

---

## 19. Can constructors be inherited?

**No.** Constructors are not inherited because they are tied to the class they belong to. However, a child class can **call the parent constructor** using `super()`.

```java
class Animal {
    Animal(String name) { System.out.println("Animal: " + name); }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);   // explicitly calling parent constructor
        System.out.println("Dog: " + name);
    }
}
```

---

## 20. What is the order of constructor execution?

Parent constructor always executes **before** the child constructor. Java automatically inserts `super()` as the first line if not explicitly written.

```java
class A {
    A() { System.out.println("A constructor"); }
}

class B extends A {
    B() { System.out.println("B constructor"); }
}

class C extends B {
    C() { System.out.println("C constructor"); }
}

new C();
// Output:
// A constructor
// B constructor
// C constructor
```

---

## 21. What happens if parent constructor is not called?

Java **automatically inserts `super()`** as the first statement in the child constructor if not explicitly written. If the parent has no default constructor, you **must** explicitly call `super(paramters)` or it's a compile error.

```java
class Animal {
    Animal(String name) { System.out.println("Animal: " + name); }
}

class Dog extends Animal {
    Dog() {
        // super();  // ❌ compile error — no default constructor in Animal
        super("Dog");  // ✅ must explicitly call
    }
}
```

---

## 22. Can we extend multiple classes in Java?

**No.** Java does not support extending multiple classes. A class can only extend **one** class. For multiple inheritance of behavior, use **interfaces**.

```java
// class C extends A, B { }  // ❌ Not allowed

interface A { void methodA(); }
interface B { void methodB(); }

class C implements A, B {    // ✅ multiple interfaces allowed
    public void methodA() { }
    public void methodB() { }
}
```

---

## 23. Can we inherit private members?

**Yes, they are inherited** but **not directly accessible** in the child class. They can be accessed indirectly through `public` or `protected` getter/setter methods.

```java
class Parent {
    private int secret = 42;
    int getSecret() { return secret; }   // public accessor
}

class Child extends Parent {
    void show() {
        // System.out.println(secret);      // ❌ not accessible
        System.out.println(getSecret());    // ✅ accessed via method
    }
}
```

---

## 24. Can we override private methods?

**No.** Private methods are not visible to child classes, so they cannot be overridden. If a child defines a method with the same name, it is a **new independent method**, not an override.

```java
class Parent {
    private void display() { System.out.println("Parent"); }
}

class Child extends Parent {
    void display() { System.out.println("Child"); }  // new method, not override
}
```

---

## 25. Can we change access modifier while overriding?

**Yes, but only to increase visibility** (make it more accessible). You cannot reduce the access level.

| Parent Modifier | Allowed in Child |
|----------------|-----------------|
| `private` | Cannot override |
| `default` | `default`, `protected`, `public` |
| `protected` | `protected`, `public` |
| `public` | `public` only |

```java
class Parent {
    protected void show() { }
}

class Child extends Parent {
    @Override
    public void show() { }    // ✅ widened from protected to public

    // private void show() { }  // ❌ narrowed — compile error
}
```

---

## 26. What happens if parent and child have same variable name?

This is called **variable hiding**. The variable accessed depends on the **reference type**, not the object type. Unlike methods, variables do not follow runtime polymorphism.

```java
class Parent { int x = 10; }
class Child extends Parent  { int x = 20; }

Parent p = new Child();
System.out.println(p.x);   // 10 — reference type (Parent) decides

Child c = new Child();
System.out.println(c.x);   // 20 — reference type (Child) decides
```

---

## 27. Can abstract class have constructor?

**Yes.** An abstract class can have a constructor. It is called when a child class object is created via `super()`. It is used to initialize common fields.

```java
abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
        System.out.println("Shape created: " + color);
    }

    abstract double area();
}

class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);   // calls abstract class constructor
        this.radius = radius;
    }

    double area() { return Math.PI * radius * radius; }
}
```

---

## 28. Can interface extend class?

**No.** An interface cannot extend a class. An interface can only **extend another interface**.

```java
class MyClass { }
// interface MyInterface extends MyClass { }  // ❌ Not allowed

interface A { void methodA(); }
interface B extends A { void methodB(); }     // ✅ interface extends interface
```

---

## 29. Can an interface extend another interface?

**Yes.** An interface can extend one or more interfaces using the `extends` keyword. The implementing class must implement all methods from the entire hierarchy.

```java
interface Flyable {
    void fly();
}

interface SuperHero extends Flyable {
    void saveWorld();
}

class IronMan implements SuperHero {
    public void fly()       { System.out.println("Flying with suit"); }
    public void saveWorld() { System.out.println("Saving the world"); }
}
```

---

## 30. Can class extend interface?

**No.** A class cannot `extend` an interface. A class must use `implements` to use an interface.

```java
interface Printable { void print(); }

// class Doc extends Printable { }     // ❌ Not allowed

class Doc implements Printable {       // ✅ correct
    public void print() { System.out.println("Printing..."); }
}
```

---

## 31. Which type of polymorphism does method overloading represent?

Method overloading represents **compile-time polymorphism** (also called static polymorphism or early binding). The compiler decides which overloaded method to call at **compile time** based on the method signature.

```java
class Printer {
    void print(int x)    { System.out.println("int: " + x); }
    void print(String s) { System.out.println("String: " + s); }
}

Printer p = new Printer();
p.print(10);       // resolved at compile time → int version
p.print("Hello");  // resolved at compile time → String version
```

---

## 32. If a class implements two interfaces with the same default method, what happens and how do you resolve it?

Java gives a **compile-time error**. The class **must override** the conflicting default method to resolve the ambiguity. You can then choose which interface's default to call using `InterfaceName.super.method()`.

```java
interface A { default void greet() { System.out.println("Hello from A"); } }
interface B { default void greet() { System.out.println("Hello from B"); } }

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet();   // ✅ explicitly choose A's version
        // B.super.greet(); // or choose B's version
        // or write your own logic
    }
}
```

---

## 33. Can an interface have a constructor in Java, and why or why not?

**No.** Interfaces cannot have constructors because:
- Interfaces **cannot be instantiated** — you never write `new MyInterface()`
- There is **no instance state** to initialize (no instance variables)
- Only the **implementing class** is instantiated, and its constructor handles initialization

```java
interface Vehicle {
    // Vehicle() { }  // ❌ Compile error: interfaces cannot have constructors
    void start();
}

class Car implements Vehicle {
    String model;
    Car(String model) { this.model = model; }   // ✅ constructor in implementing class
    public void start() { System.out.println(model + " started"); }
}
```

---

## 34. Can an interface have instance variables in Java, and if not, what type of variables can it have?

**No.** Interfaces cannot have instance variables. All variables in an interface are implicitly **`public static final`** (constants). They belong to the interface itself, not to any object.

```java
interface Config {
    int MAX_RETRY = 3;           // implicitly public static final
    String DEFAULT_HOST = "localhost";

    // int counter;              // ❌ not allowed — would be instance variable
}

class App implements Config {
    void connect() {
        System.out.println("Host: " + DEFAULT_HOST);     // ✅ access directly
        System.out.println("Max retry: " + MAX_RETRY);
    }
}

// Access without object
System.out.println(Config.MAX_RETRY);   // 3
```



# ✅ 5. Java Interface & Abstract Class

---

## 1. What is an Interface?

An interface is a **contract** that defines what a class must do, without specifying how. It contains abstract methods (by default) that implementing classes must provide. Used to achieve **100% abstraction** and **multiple inheritance**.

```java
interface Payment {
    void pay(double amount);   // abstract by default
}

class CardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by Card");
    }
}
```

---

## 2. What are the types of interfaces available in Java?

| Type | Description |
|------|-------------|
| Normal Interface | Has multiple abstract methods |
| Functional Interface | Has exactly one abstract method — used with lambdas |
| Marker Interface | Empty interface — tags a class for special behavior |
| Nested Interface | Declared inside a class or another interface |

```java
// Normal
interface Animal { void eat(); void sleep(); }

// Functional
@FunctionalInterface
interface Calculator { int calculate(int a, int b); }
Calculator add = (a, b) -> a + b;

// Marker
interface Serializable { }   // empty — just marks the class

// Nested
class Bank {
    interface Transaction { void process(); }
}
```

---

## 3. What are static methods in interfaces?

Static methods in interfaces belong to the **interface itself**, not to implementing classes. They are called using the **interface name**, cannot be overridden, and are used for utility/helper logic related to the interface.

```java
interface MathUtils {
    static int square(int x) { return x * x; }
    static int cube(int x)   { return x * x * x; }
}

// Called using interface name — no object needed
System.out.println(MathUtils.square(4));   // 16
System.out.println(MathUtils.cube(3));     // 27
```

---

## 4. What is an Abstract Class?

An abstract class is a class declared with the `abstract` keyword that **cannot be instantiated**. It can have both abstract methods (no body) and concrete methods (with body). Used for **partial abstraction** and sharing common code among related classes.

```java
abstract class Vehicle {
    String brand;

    Vehicle(String brand) { this.brand = brand; }

    abstract void move();           // must be implemented by subclass

    void fuel() {                   // concrete — shared by all
        System.out.println(brand + " needs fuel");
    }
}

class Car extends Vehicle {
    Car(String brand) { super(brand); }

    @Override
    void move() { System.out.println(brand + " drives on road"); }
}
```

---

## 5. When should you use an interface instead of an abstract class?

| Use Interface | Use Abstract Class |
|--------------|-------------------|
| Multiple inheritance needed | Single inheritance is fine |
| Unrelated classes share behavior | Closely related classes |
| Define a contract only (what, not how) | Share common code + state |
| No shared fields needed | Common fields/constructors needed |

```java
// Interface — unrelated classes share Printable behavior
interface Printable { void print(); }
class Invoice implements Printable { public void print() { } }
class Report  implements Printable { public void print() { } }

// Abstract class — related classes share common logic
abstract class Animal {
    String name;
    abstract void sound();
    void breathe() { System.out.println("Breathing"); }
}
```

---

## 6. Can we create object of interface or abstract class?

**No.** Neither an interface nor an abstract class can be instantiated directly. Objects are created through **concrete implementing/extending classes** or **anonymous classes**.

```java
interface Shape { void draw(); }
abstract class Color { abstract void fill(); }

// Shape s = new Shape();   // ❌ compile error
// Color c = new Color();   // ❌ compile error

Shape s = new Shape() {                  // ✅ anonymous class
    public void draw() { System.out.println("Drawing"); }
};

// Or via implementing class
class Circle implements Shape {
    public void draw() { System.out.println("Circle"); }
}
Shape c = new Circle();                  // ✅
```

---

## 7. Can an interface have methods with implementation? (Java 8+)

**Yes.** From Java 8 onwards, interfaces can have:
- `default` methods — instance methods with a body, can be overridden
- `static` methods — utility methods, cannot be overridden

From Java 9:
- `private` methods — helper methods used internally by default/static methods

```java
interface Greeter {
    void greet(String name);                          // abstract

    default void greetAll() {                         // default (Java 8+)
        System.out.println("Hello Everyone!");
    }

    static void info() {                              // static (Java 8+)
        System.out.println("Greeter Interface");
    }

    private void log(String msg) {                    // private (Java 9+)
        System.out.println("Log: " + msg);
    }
}
```

---

## 8. What are default and static methods in interface?

**Default method** — has a body, belongs to the instance, can be overridden by implementing class. Introduced to add new methods to interfaces without breaking existing implementations.

**Static method** — has a body, belongs to the interface, called via interface name, cannot be overridden.

```java
interface Logger {
    default void log(String msg) {                    // default
        System.out.println("LOG: " + msg);
    }

    static Logger getConsoleLogger() {                // static factory
        return msg -> System.out.println("CONSOLE: " + msg);
    }
}

class AppLogger implements Logger {
    @Override
    public void log(String msg) {                     // overriding default
        System.out.println("APP LOG: " + msg);
    }
}

Logger l = new AppLogger();
l.log("Started");                                     // APP LOG: Started
Logger.getConsoleLogger().log("Hello");               // CONSOLE: Hello
```

---

## 9. Can abstract class have constructor? Why?

**Yes.** An abstract class can have a constructor. It is called when a **subclass object is created** via `super()`. Used to initialize common fields shared by all subclasses.

```java
abstract class Animal {
    String name;

    Animal(String name) {
        this.name = name;
        System.out.println("Animal created: " + name);
    }

    abstract void sound();
}

class Dog extends Animal {
    Dog(String name) {
        super(name);   // calls abstract class constructor
    }

    void sound() { System.out.println(name + " barks"); }
}

new Dog("Rex");
// Animal created: Rex
// Rex barks
```

---

## 10. Can abstract class have both abstract and concrete methods?

**Yes.** This is the key feature of abstract classes — **partial abstraction**. Abstract methods define the contract; concrete methods provide shared reusable logic.

```java
abstract class Report {
    String title;

    Report(String title) { this.title = title; }

    abstract void generate();           // subclass must implement

    void printHeader() {                // shared concrete method
        System.out.println("=== " + title + " ===");
    }

    void printFooter() {                // shared concrete method
        System.out.println("=== End of Report ===");
    }
}

class SalesReport extends Report {
    SalesReport() { super("Sales Report"); }

    @Override
    void generate() {
        printHeader();
        System.out.println("Sales data here...");
        printFooter();
    }
}
```

---

## 11. Can interface have variables? What type?

**Yes**, but all variables in an interface are implicitly **`public static final`** (constants). They cannot be instance variables and must be initialized at declaration.

```java
interface AppConfig {
    int MAX_CONNECTIONS = 100;          // public static final int
    String DEFAULT_HOST = "localhost";  // public static final String

    // int count;                       // ❌ not allowed — no instance variables
}

class Database implements AppConfig {
    void connect() {
        System.out.println("Host: " + DEFAULT_HOST);
        System.out.println("Max: " + MAX_CONNECTIONS);
    }
}

System.out.println(AppConfig.MAX_CONNECTIONS);   // 100
```

---

## 12. When should we use interface vs abstract class?

| Scenario | Use |
|----------|-----|
| Multiple inheritance | Interface |
| Unrelated classes share behavior | Interface |
| Define a pure contract | Interface |
| Related classes with shared state/code | Abstract class |
| Need constructors or instance fields | Abstract class |
| Partial implementation + contract | Abstract class |

> **Rule of thumb:** If it's a capability (Flyable, Printable, Serializable) → Interface. If it's a base type with shared logic (Animal, Vehicle, Shape) → Abstract class.

---

## 13. Can a class implement multiple interfaces?

**Yes.** A class can implement any number of interfaces. This is how Java achieves **multiple inheritance of behavior**.

```java
interface Flyable  { void fly(); }
interface Swimmable { void swim(); }
interface Runnable  { void run(); }

class Duck implements Flyable, Swimmable, Runnable {
    public void fly()  { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
    public void run()  { System.out.println("Duck runs"); }
}
```

---

## 14. Can an abstract class implement an interface?

**Yes.** An abstract class can implement an interface and is **not required to implement all its methods**. The unimplemented methods remain abstract and must be implemented by the concrete subclass.

```java
interface Drawable {
    void draw();
    void resize();
}

abstract class Shape implements Drawable {
    @Override
    public void resize() {                        // implements one method
        System.out.println("Resizing shape");
    }
    // draw() left abstract — subclass must implement
}

class Circle extends Shape {
    @Override
    public void draw() { System.out.println("Drawing circle"); }
}
```

---

## 15. Can an interface extend another interface?

**Yes.** An interface can extend one or more interfaces using `extends`. The implementing class must implement all methods from the entire hierarchy.

```java
interface Animal {
    void breathe();
}

interface Pet extends Animal {
    void play();
}

interface ServiceAnimal extends Pet {
    void assist();
}

class GuideDog implements ServiceAnimal {
    public void breathe() { System.out.println("Breathing"); }
    public void play()    { System.out.println("Playing"); }
    public void assist()  { System.out.println("Guiding owner"); }
}
```

---

## 16. Can an interface extend a class?

**No.** An interface cannot extend a class. An interface can only extend another **interface**. A class uses `implements` for interfaces and `extends` for classes.

```java
class MyClass { }
// interface MyInterface extends MyClass { }   // ❌ compile error

interface A { void methodA(); }
interface B extends A { void methodB(); }       // ✅ interface extends interface
```

---

## 17. Can a class extend multiple abstract classes?

**No.** Java does not support multiple class inheritance — a class can only `extend` **one** class (abstract or concrete). Use interfaces for multiple inheritance of behavior.

```java
abstract class A { abstract void methodA(); }
abstract class B { abstract void methodB(); }

// class C extends A, B { }   // ❌ Not allowed

interface IA { void methodA(); }
interface IB { void methodB(); }
class C implements IA, IB {   // ✅ multiple interfaces allowed
    public void methodA() { }
    public void methodB() { }
}
```

---

## 18. Can interface have constructor?

**No.** Interfaces cannot have constructors because they cannot be instantiated. There is no object state to initialize. Only the implementing class is instantiated.

```java
interface Vehicle {
    // Vehicle() { }   // ❌ compile error
    void start();
}
```

---

## 19. Can abstract class have constructor?

**Yes.** Abstract classes can have constructors. They are invoked when a subclass object is created via `super()`. Used to initialize common fields.

```java
abstract class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    abstract void work();
}

class Developer extends Employee {
    Developer(String name, double salary) {
        super(name, salary);
    }
    void work() { System.out.println(name + " writes code"); }
}
```

---

## 20. Can interface have private methods?

**Yes**, from **Java 9** onwards. Private methods in interfaces are used as **helper methods** for `default` or `static` methods to avoid code duplication. They cannot be accessed by implementing classes.

```java
interface Logger {
    default void logInfo(String msg)  { log("INFO", msg); }
    default void logError(String msg) { log("ERROR", msg); }

    private void log(String level, String msg) {   // Java 9+
        System.out.println("[" + level + "] " + msg);
    }
}
```

---

## 21. Can we override static methods in interface?

**No.** Static methods in interfaces belong to the interface itself and **cannot be overridden** by implementing classes. If a class defines a method with the same name, it is a completely separate method — not an override.

```java
interface Printer {
    static void print() { System.out.println("Interface print"); }
}

class MyPrinter implements Printer {
    static void print() { System.out.println("Class print"); }  // new method, not override
}

Printer.print();        // Interface print
MyPrinter.print();      // Class print
```

---

## 22. What if two interfaces have same default method?

Java gives a **compile-time error**. The implementing class **must override** the conflicting method. It can then choose which interface's default to call using `InterfaceName.super.method()`.

```java
interface A { default void greet() { System.out.println("Hello from A"); } }
interface B { default void greet() { System.out.println("Hello from B"); } }

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet();   // explicitly choose A's version
    }
}

new C().greet();   // Hello from A
```

---

## 23. Can abstract class be final?

**No.** `abstract` requires the class to be subclassed (to implement abstract methods), while `final` prevents subclassing. They directly contradict each other — Java gives a compile error.

```java
// abstract final class Shape { }   // ❌ compile error: illegal combination
```

---

## 24. Can interface have main method?

**Yes**, from **Java 8** onwards. Since interfaces can have `static` methods, they can have a `main` method. The JVM can execute it directly.

```java
interface App {
    static void main(String[] args) {
        System.out.println("Main method inside interface");
    }
}
// Run: java App  → Main method inside interface
```

---

## 25. Can we use both interface and abstract class together?

**Yes.** This is a very common pattern. An abstract class implements an interface partially, and concrete subclasses complete the implementation.

```java
interface Reportable {
    void generate();
    void export();
}

abstract class BaseReport implements Reportable {
    @Override
    public void export() {                          // common implementation
        System.out.println("Exporting to PDF...");
    }
    // generate() left for subclasses
}

class SalesReport extends BaseReport {
    @Override
    public void generate() {
        System.out.println("Generating Sales Report");
    }
}

SalesReport r = new SalesReport();
r.generate();   // Generating Sales Report
r.export();     // Exporting to PDF...
```

---

## 26. What access modifiers are allowed in interface?

| Member | Default Modifier | Allowed Modifiers |
|--------|-----------------|-------------------|
| Abstract methods | `public abstract` | `public` only |
| Default methods | `public` | `public` only |
| Static methods | `public` | `public` only |
| Private methods | `private` | `private` only (Java 9+) |
| Variables | `public static final` | `public static final` only |

```java
interface Demo {
    int VALUE = 10;                        // public static final
    void abstractMethod();                 // public abstract
    default void defaultMethod() { }       // public
    static void staticMethod() { }         // public
    private void helperMethod() { }        // private (Java 9+)
}
```

---

## 27. Can we instantiate interface using lambda?

**Yes**, but only for **functional interfaces** (interfaces with exactly one abstract method). A lambda expression provides the implementation of that single abstract method inline.

```java
@FunctionalInterface
interface Greeting {
    void greet(String name);
}

// Lambda instantiates the functional interface
Greeting g = name -> System.out.println("Hello, " + name + "!");
g.greet("Alice");   // Hello, Alice!

// Another example
Runnable r = () -> System.out.println("Running...");
r.run();
```

---

## 28. What are SOLID principles?

SOLID is a set of five OOP design principles for writing clean, maintainable, and scalable code.

| Letter | Principle | Meaning |
|--------|-----------|---------|
| S | Single Responsibility | A class should have only one reason to change |
| O | Open/Closed | Open for extension, closed for modification |
| L | Liskov Substitution | Child class must be substitutable for parent |
| I | Interface Segregation | Many specific interfaces > one general interface |
| D | Dependency Inversion | Depend on abstractions, not concrete classes |

```java
// S — Single Responsibility
class OrderService  { void placeOrder() { } }
class EmailService  { void sendEmail()  { } }   // separate responsibilities

// O — Open/Closed
interface Payment { void pay(); }
class CardPayment implements Payment { public void pay() { } }
class UpiPayment  implements Payment { public void pay() { } }
// Add new payment type without changing existing code

// L — Liskov Substitution
class Bird    { void fly() { System.out.println("Flying"); } }
class Sparrow extends Bird { void fly() { System.out.println("Sparrow flies"); } }
Bird b = new Sparrow();
b.fly();   // works correctly — Sparrow substitutes Bird

// I — Interface Segregation
interface Workable { void work(); }
interface Eatable  { void eat(); }
class Human implements Workable, Eatable { public void work(){} public void eat(){} }
class Robot implements Workable          { public void work(){} }  // no forced eat()

// D — Dependency Inversion
interface NotificationService { void send(String msg); }
class EmailNotification implements NotificationService {
    public void send(String msg) { System.out.println("Email: " + msg); }
}
class OrderProcessor {
    private NotificationService service;
    OrderProcessor(NotificationService service) { this.service = service; }
    void process() { service.send("Order placed"); }
}
```

---

## 29. What is `.class` and when do we use it in Java?

`.class` is a **class literal** — it returns the `Class<T>` object representing the type at compile time. Used when you need to pass **class metadata** to frameworks, reflection, or APIs.

**Common use cases:**

```java
// 1. Spring — load configuration
ApplicationContext ctx =
    new AnnotationConfigApplicationContext(AppConfig.class);

// 2. Spring — get bean by type
UserService us = ctx.getBean(UserService.class);

// 3. Exception handler
@ExceptionHandler(NullPointerException.class)
public void handle(Exception e) { }

// 4. Reflection — inspect class at runtime
Class<?> clazz = String.class;
System.out.println(clazz.getName());        // java.lang.String
System.out.println(clazz.getMethods().length);

// 5. JUnit — parameterized tests
@Test
void test() {
    assertThrows(IllegalArgumentException.class, () -> new User(null));
}
```

| `.class` | `new` |
|----------|-------|
| Returns class metadata | Creates an object instance |
| No memory allocated for object | Memory allocated on heap |
| Used by frameworks/reflection | Used in application logic |
