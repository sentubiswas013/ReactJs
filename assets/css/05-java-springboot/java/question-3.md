## 5. Which type of polymorphism does method overloading represent?

Method overloading represents **compile-time polymorphism** (also called static polymorphism or early binding).

The compiler resolves which overloaded method to call **at compile time** based on the method signature (number and types of parameters).

```java
class Calculator {
    int add(int a, int b)          { return a + b; }
    double add(double a, double b) { return a + b; }
}

// Resolved at compile time — no runtime decision needed
Calculator c = new Calculator();
c.add(1, 2);       // calls int version
c.add(1.0, 2.0);   // calls double version
```

> **Contrast:** Method **overriding** = runtime polymorphism (resolved at runtime based on actual object type).

---

## 6. What is the difference between an abstract class and an interface in Java?

| Feature | Abstract Class | Interface |
|---|---|---|
| Methods | Abstract + concrete | Abstract + default + static (Java 8+) |
| Variables | Any type (instance, static) | `public static final` only |
| Constructors | ✅ Yes | ❌ No |
| Multiple inheritance | ❌ One class only | ✅ Multiple interfaces |
| Access modifiers | Any | Methods are `public` by default |

```java
abstract class Animal {
    String name;                        // instance variable ✅
    abstract void sound();              // abstract method
    void breathe() { System.out.println("breathing"); } // concrete method
}

interface Flyable {
    int MAX_HEIGHT = 1000;              // public static final ✅
    void fly();                         // abstract method
    default void land() { System.out.println("landing"); } // default method (Java 8+)
}
```

---

## 7. When should you use an interface instead of an abstract class?

**Use an interface when:**
- You want to define a **contract** (capability) that unrelated classes can share
- You need **multiple inheritance** — a class can implement many interfaces
- You have no shared state or common implementation to provide

**Use an abstract class when:**
- Classes share **common state** (instance variables) or **common behavior**
- You want to provide a **partial implementation** that subclasses build on
- You need a **constructor** to initialize shared fields

```java
// Interface — unrelated classes share the same capability
interface Printable {
    void print();
}
class Invoice implements Printable { public void print() { /*...*/ } }
class Report  implements Printable { public void print() { /*...*/ } }

// Abstract class — related classes share common state + behavior
abstract class Shape {
    String color;                       // shared state
    Shape(String color) { this.color = color; }
    abstract double area();             // subclass must implement
}
class Circle extends Shape {
    double radius;
    Circle(String color, double radius) { super(color); this.radius = radius; }
    public double area() { return Math.PI * radius * radius; }
}
```

---

## 8. How are variables defined differently in interfaces compared to abstract classes?

| | Abstract Class | Interface |
|---|---|---|
| Variable types | Instance, static, final, non-final | `public static final` **only** |
| Mutability | Can be mutable | Always constants (immutable) |
| Access modifiers | Any (`private`, `protected`, `public`) | Always `public` |

```java
abstract class Vehicle {
    int speed = 0;          // mutable instance variable ✅
    static int count = 0;   // mutable static variable ✅
    final int MAX = 200;    // constant ✅
}

interface Config {
    int TIMEOUT = 30;       // compiler treats as: public static final int TIMEOUT = 30
    // int count = 0;       // ❌ cannot be reassigned — it's final
}
```

> Any variable declared in an interface is **implicitly** `public static final`, even if you don't write those keywords.

---

## 9. If a class implements two interfaces with the same default method, what happens and how do you resolve it?

Java gives a **compile-time error** — the class must **override** the conflicting default method to resolve the ambiguity.

```java
interface A {
    default void greet() { System.out.println("Hello from A"); }
}

interface B {
    default void greet() { System.out.println("Hello from B"); }
}

// ❌ Compile error without override
class C implements A, B {

    // ✅ Must override to resolve conflict
    @Override
    public void greet() {
        A.super.greet();    // explicitly call A's version, OR
        // B.super.greet(); // explicitly call B's version, OR
        // write your own implementation
        System.out.println("Hello from C");
    }
}
```

> Use `InterfaceName.super.methodName()` to explicitly delegate to a specific interface's default method.

---

## 10. Can an interface have a constructor in Java, and why or why not?

**No.** Interfaces cannot have constructors.

**Why:**
- A constructor's purpose is to **initialize an object's instance state**
- Interfaces cannot be instantiated directly — you never write `new Flyable()`
- Interfaces have **no instance variables** to initialize
- Only the implementing class is instantiated, and its constructor handles initialization

```java
interface Animal {
    // Animal() { }  // ❌ Compile error: interfaces cannot have constructors
    void sound();
}

class Dog implements Animal {
    String name;
    Dog(String name) { this.name = name; }  // ✅ constructor in the implementing class
    public void sound() { System.out.println("Woof"); }
}
```

---

## 11. Can an interface have instance variables in Java, and if not, what type of variables can it have?

**No.** Interfaces cannot have instance variables.

All variables in an interface are implicitly **`public static final`** (constants). They belong to the interface itself, not to any instance.

```java
interface MathConstants {
    double PI = 3.14159;        // public static final double PI = 3.14159
    int MAX_VALUE = 100;        // public static final int MAX_VALUE = 100

    // int counter;             // ❌ not allowed — would be an instance variable
    // private int x = 5;       // ❌ private instance variable not allowed
}

class Circle implements MathConstants {
    double area(double r) {
        return PI * r * r;      // access constant directly
    }
}

// Access without an instance
System.out.println(MathConstants.PI);       // 3.14159
System.out.println(MathConstants.MAX_VALUE); // 100
```

---

## 12. In an inheritance hierarchy, how is a method resolved when called using a parent reference?

Java uses **dynamic dispatch (runtime polymorphism)**:
- The **reference type** determines which methods are *accessible* (compile-time check)
- The **actual object type** determines which method *runs* (runtime decision)

```java
class Animal {
    void sound() { System.out.println("Some sound"); }
    static void type() { System.out.println("Animal"); }  // static — NOT overridden
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Woof"); }          // overrides parent
    static void type() { System.out.println("Dog"); }     // hides parent (not override)
}

Animal a = new Dog();   // parent reference, child object

a.sound();  // "Woof"   — runtime: actual object is Dog → Dog's sound() runs
a.type();   // "Animal" — static methods use reference type (compile-time binding)
```

**Resolution rules:**
1. **Instance methods** → resolved at **runtime** based on actual object type (dynamic dispatch)
2. **Static methods** → resolved at **compile time** based on reference type (no polymorphism)
3. **Instance variables** → resolved at **compile time** based on reference type (no polymorphism)

```java
class Parent { String name = "Parent"; }
class Child extends Parent { String name = "Child"; }

Parent p = new Child();
System.out.println(p.name);  // "Parent" — variables use reference type, not object type
```
