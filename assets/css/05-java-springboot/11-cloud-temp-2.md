# ✅ 3. Classes and Objects


## 1. What is the difference between this and super keywords?

**this** refers to the current object instance,  
**super** refers to the immediate parent class object.

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";
    
    void display() {
        System.out.println(this.name);  // Child
        System.out.println(super.name); // Parent
    }
}
```

## 2. What is method overloading?

Method overloading is a feature in Java where multiple methods have the **same name** but **different parameter lists** (different number, type, or order of parameters) in the same class.

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

## 3. What is method overriding?

**Method overriding** is redefining a parent class method in the child class with the same signature. The child class version gets called instead of the parent's.


```java
class Animal {
    public void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
```


## 4. What is a class in Java?

A class is a blueprint or template used to create objects. It defines properties and behavior.

```java
class Car {
    String color;
    void drive() {
        System.out.println("Car is driving");
    }
}
```


## 5. What is class loader and how do they work?

A **ClassLoader** in Java is a part of the **JVM** that is responsible for **loading `.class` files into memory** at runtime.


**How ClassLoader Works :**

Java follows a **Delegation Hierarchy Model**:

1. **Bootstrap ClassLoader** Loads core Java classes (e.g., `java.lang.*`)
2. **Extension (Platform) ClassLoader** Loads classes from `jre/lib/ext`
3. **Application (System) ClassLoader** Loads classes from classpath


## 6. How do you create an immutable class in Java?

An immutable class in Java is a class whose objects cannot be changed after they are created. To create an immutable class:

1. Make the class `final` so it cannot be subclassed.
2. Make all fields `private` and `final`.
3. Do not provide setters.
4. Initialize fields through constructor.
5. Return copies of mutable objects (defensive copy).

```java
// Immutable class
final class Student {
    private final String name;
    
    
    public Student(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
```


## 7. What is an object?

An object is an instance of a class. It represents real-world entities.

```java
Car c = new Car();  // object creation
```


## 8. Difference between class and object?

Class is a blueprint, object is the actual implementation of that blueprint.

* Class → logical
* Object → physical (exists in memory)



## 9. How to create a class and object?

We create a class using class keyword and object using new keyword.

```java
class Student {
    String name;
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student(); // object
        s.name = "John";
        System.out.println(s.name);
    }
}
```


## 10. What are instance variables and methods?

Instance variables belong to object and methods define behavior of object.

```java
class Employee {
    String name; // instance variable

    void work() { // instance method
        System.out.println("Working...");
    }
}
```


## 11. What is a constructor? Types?

Constructor is a special method used to initialize objects. It is called automatically when object is created.

**Types:**

* Default
* Parameterized

```java
class Student {
    String name;

    Student() { // default
        name = "Unknown";
    }

    Student(String n) { // parameterized
        name = n;
    }
}
```


## 12. What is default constructor?

A constructor with no parameters. If we don’t create one, Java provides it automatically.

```java
Student s = new Student(); // calls default constructor
```


## 5. What is the static keyword?

**`static`** is a keyword in **Java** used to declare variables, methods, or blocks that **belong to the class instead of an object**, so they can be accessed without creating an instance.

```java
class Example {
    static int count = 0;

    static void show() {
        System.out.println("Static method");
    }
}

public class Test {
    public static void main(String[] args) {
        Example.show();   // No object needed
    }
}
```

`static` members are **shared by all objects and can be accessed using the class name**.



## 14. Difference: static vs non-static?

Static belongs to class, non-static belongs to object.

| Static           | Non-Static               |
| ---------------- | ------------------------ |
| Shared           | Separate for each object |
| No object needed | Object required          |

```java
class Test {
    static void show1() {}
    void show2() {}
}
```


## 15. Can a class have multiple constructors?

Yes, this is called constructor overloading.

```java
class Student {
    Student() {}
    Student(String name) {}
    Student(int age) {}
}
```


## 16. Can a constructor be final, static, or abstract in Java?

No, a constructor cannot be **final, static, or abstract**.

**Why:**

* **final** → Constructor is not inherited, so it cannot be overridden.
* **static** → Static belongs to class, but constructor is used to create object.
* **abstract** → Abstract methods have no body, but constructor must have a body.



## 17. Can we make a class `final`? Why?

Yes, final class cannot be extended. It is used to prevent inheritance for security or immutability.

```java
final class A {}

// class B extends A {} ❌ ERROR
```


## 18. Can we make a class `abstract`?

Yes, abstract class cannot be instantiated and can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();
}
```


## 19. What is the difference between interface and abstract class?

An **interface** is used to define a **contract** that classes must implement. It mainly contains **abstract methods**, and variables are **public, static, and final by default**.
```java
interface Animal {
    void sound();   // abstract method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Dog barks");
    }
}
```

An **abstract class** is used when classes share **common behavior and state**. It can have **abstract methods and concrete methods**, and it can also have **instance variables and constructors**.

```java
abstract class Animal {
    abstract void sound();   // abstract method

    void eat() {             // concrete method
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```


## 20. Can a class be both abstract and final? 

No, because abstract needs inheritance and final restricts inheritance.



## 21. What is inner class? Types?

Inner class is a class inside another class, used for better grouping.

**Types:**

* Member inner class
* Static nested class
* Local inner class
* Anonymous class

```java
class Outer {
    class Inner {
        void show() {
            System.out.println("Inner class");
        }
    }
}
```


## 22. What is singleton class?

Singleton class allows only one object creation.

```java
class Singleton {
    private static Singleton obj = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return obj;
    }
}

// How t use
public class Test {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
    }
}
```

## 23. Is it allowed to overload main() method in Java?

**Yes, the `main()` method can be overloaded** in Java.

The JVM only calls **`public static void main(String[] args)`**, while other overloaded versions behave as **regular methods**.

```java
public class Test {
    public static void main(String[] args) { } // JVM entry point
    public static void main(int x) { }         // Overloaded
    public static void main() { }              // Overloaded
}
```


## 24. Is it allowed to override main() method in Java?

**No, we cannot override the `main()` method in Java** because it is **static**, and static methods cannot be overridden (they are hidden).

* The `main()` method is:

  ```java
  public static void main(String[] args)
  ```
* Since it is **static**, it belongs to the class, not the object.
* Static methods are resolved at **compile time**, so they cannot participate in runtime polymorphism.



## 25. Can you override static methods?

No, you cannot override static methods in Java. **Static methods belong to the class, not instances,** so they're resolved at compile time based on the reference type.

```java
class Parent {
    static void display() { System.out.println("Parent"); }
}

class Child extends Parent {
    static void display() { System.out.println("Child"); } // Hiding, not overriding
}

Parent p = new Child();
p.display(); // Prints "Parent" - based on reference type
```


## 26. Is it possible to execute a program without defining a main() method?

It is **technically possible** to run code in a **static block** without a `main()` method, but **modern Java requires `main()`** as the entry point.

Using static blocks alone is **not recommended** and violates standard practices.

```java
class NoMain {
    static {
        System.out.println("Executing without main");
        System.exit(0); // Required to prevent error
    }
}
// Works in older Java versions, not recommended
```


## 27. Can we create object without `new` keyword?

Yes, using methods like clone(), factory methods, reflection, or deserialization.

```java
// Example using clone
class A implements Cloneable {
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```


## 28. What happens if a class has no constructor?

Java provides a default constructor automatically.



## 29. Can a class be private or protected?

Top-level class cannot be private or protected, only public or default. Inner classes can be private/protected.

```java
class Outer {
    private class Inner {}
}
```


## 30. What happens if a static block throws an unchecked exception?

If a static block throws an unchecked exception:

* Class will **fails to load**
* JVM will throws **ExceptionInInitializerError**
* Program stops and class cannot be used


```java
public class Main {
    public static void main(String[] args) {
        Test t = new Test();
    }
}

class Test {
    static {
        System.out.println("Static block running");
        int x = 10 / 0; // ArithmeticException
    }
}

// Output:
Exception in thread "main" java.lang.ExceptionInInitializerError
```

## 31. Summary Classes and Objects

**🔹 Class**

* **Class:** A blueprint/template used to create objects.
* **Fields (Variables):** Represent the state of the object.
* **Methods:** Define the behavior of the object.
* **Constructor:** Special method used to initialize object values.


**🔹 Object**

* **Object:** Instance of a class with state and behavior.
* **State:** Represented by variables (data).
* **Behavior:** Represented by methods (functions).
* **Memory:** Objects are created in heap memory.


**🔹 Key Concepts**

* **Encapsulation:** Wrapping data and methods together and restricting access using private/public.
* **this keyword:** Refers to the current object.
* **new keyword:** Used to create an object.



# ✅ 4. Java Inheritance 

## 1. What is Inheritance in Java?

Inheritance is a feature where one class **gets properties and methods of another class**.

👉 *In simple words:* child class uses parent class code.

```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}
```


## 2. Why doesn't Java support multiple inheritance (with classes)?

Java does not support multiple inheritance with **classes** to avoid the **Diamond Problem** and complexity.

But Java does support multiple inheritance with **interfaces**.

```java
class A {
    void show() {
        System.out.println("From class A");
    }
}

class B extends A {
}

class C extends A {
}

class D extends B, C {   // ❌ Not allowed in Java
    public static void main(String[] args) {
        D obj = new D();
        obj.show();  // Which show()? From B or C?
    }
}
```

## 3. What is the diamond problem?

The diamond problem occurs when a class inherits from two classes that both inherit from the same base class, creating ambiguity about which method to call.

```
    A
   / \
  B   C
   \ /
    D
```

If classes B and C both override a method from A, and D inherits from both B and C, which version should D use? This creates confusion and compilation errors.

## 3. How does Java solve the diamond problem?

Java solves the **diamond problem** by not allowing multiple class inheritance but supporting multiple interface inheritance with **default methods**.


```java
interface A { default void method() { } }
interface B { default void method() { } }

class C implements A, B {
    @Override
    public void method() {
        A.super.method(); // Explicitly choose which to call
    }
}
```


## 4. Why do we use Inheritance?

We use inheritance to:

* **Reuse code**
* **Avoid duplication**
* **Make code cleaner and organized**

👉 *Example:* Dog already gets `eat()` from Animal, no need to rewrite it.


## 5. What is `extends` keyword?

`extends` is used to **inherit a class**.

```java
class Dog extends Animal {
}
```

👉 Dog is inheriting from Animal using `extends`.


## 6. What is IS-A Relationship?

IS-A means **one object is a type of another**.

👉 Example:

* Dog IS-A Animal ✅

```java
Dog d = new Dog(); // Dog is also an Animal
```


## 7. What is Method Overriding?

Method overriding means **child class provides its own version of parent method**.

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

👉 Same method name, but different behavior.


## 8. Types of Inheritance in Java

Java supports:

* **Single**
* **Multilevel**
* **Hierarchical**

👉 *Note:* Multiple inheritance is not supported with classes.


## 9. Does Java support Multiple Inheritance? Why?

❌ No, Java does not support multiple inheritance with classes.

👉 *Reason:* To avoid **ambiguity problem (Diamond problem)**.

✅ But it supports it using **interfaces**.


## 10. What is Multilevel Inheritance?

When a class inherits from a class, which already inherits another class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {
    void bark() {}
}

class Puppy extends Dog {
    void weep() {}
}
```

👉 Puppy → Dog → Animal


## 11. What is Hierarchical Inheritance?

Multiple child classes inherit from one parent class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {}
class Cat extends Animal {}
```

👉 Dog and Cat both inherit Animal



## 12. Can we override static methods?

❌ No

👉 Static methods are **not overridden**, they are **hidden**.


## 13. Can we override final methods?

❌ No

👉 Final methods cannot be changed in child class.

```java
class Animal {
    final void run() {}
}

class Dog extends Animal {
    // ❌ Error: cannot override final method
}
```


## 14. What is runtime polymorphism?

👉 *“Runtime polymorphism means method execution is decided at runtime based on object type using method overriding.”*

```java
class A {
    void show() { System.out.println("A"); }
}

class B extends A {
    void show() { System.out.println("B"); }
}

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.show(); // Output: B
    }
}
```


## 15. How does method overriding work internally?

👉 *“JVM uses dynamic method dispatch. It decides method call at runtime based on actual object, not reference.”*


## 16. What are rules for method overriding?

👉 *“Same name, same parameters, same or covariant return type, and cannot reduce access level.”*

* Cannot override `final`, `static`, `private`
* Access modifier can be same or more open


## 17. What is covariant return type?

👉 *“Child class can return a more specific type than parent method.”*

```java
class A {}
class B extends A {}

class Parent {
    A get() { return new A(); }
}

class Child extends Parent {
    B get() { return new B(); } // allowed
}
```


## 18. Can constructors be inherited?

👉 *“No, constructors are not inherited because they belong to class initialization.”*


## 19. What is the order of constructor execution?

👉 *“Parent constructor executes first, then child constructor.”*

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() { System.out.println("Child"); }
}
```

**Output:**

```
Parent
Child
```


## 20. What happens if parent constructor is not called?

👉 *“Java automatically calls default parent constructor using super().”*

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() {
        // super() is added automatically
        System.out.println("Child");
    }
}
```


## 21. Can we extend multiple classes in Java?

👉 *“No, Java does not support multiple inheritance for classes to avoid ambiguity.”*

```java id="7c8h3n"
// class C extends A, B {} ❌ NOT ALLOWED
```


## 22. Can we inherit private members?

👉 *“Yes, but we cannot access them directly outside the class.”*

```java 
class A {
    private int x = 10;

    int getX() { return x; } // access via method
}

class B extends A {
    void show() {
        // System.out.println(x); ❌ ERROR
        System.out.println(getX()); // ✅
    }
}
```


## 23. Can we override private methods?

👉 *“No, private methods are not visible, so they cannot be overridden.”*

```java 
class A {
    private void show() {}
}

class B extends A {
    // void show() {} ❌ not overriding, new method
}
```


## 24. Can we change access modifier while overriding?

👉 *“Yes, but only to increase visibility, not decrease.”*

```java id="c2h9rf"
class A {
    protected void show() {}
}

class B extends A {
    public void show() {} // ✅ allowed
}
```


## 25. What happens if parent and child have same variable name?

👉 *“Variable hiding happens. Access depends on reference type.”*

```java id="6y5kdn"
class A { int x = 10; }
class B extends A { int x = 20; }

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        System.out.println(obj.x); // 10
    }
}
```


## 26. Can abstract class have constructor?

👉 *“Yes, it is used to initialize variables when child object is created.”*

```java id="ux1l7p"
abstract class A {
    A() { System.out.println("Abstract constructor"); }
}

class B extends A {}
```


## 27. Can interface extend class?

👉 *“No, interface can only extend another interface.”*

```java id="1jxv8n"
// interface A extends B {} (only if B is interface)
```


## 27. Can an interface extend another interface?

Yes, in **Java**, an **interface can extend another interface** using the `extends` keyword.
A child interface inherits all methods from the parent interface.

```java
interface A {
    void methodA();
}

interface B extends A {
    void methodB();
}
```


## 28. Can class extend interface?

👉 *“No, class cannot extend interface. It uses implements keyword.”*

```java id="8mz4tt"
interface A {}

class B implements A {}
```

## 29. Which type of polymorphism does method overloading represent?

**Method overloading** represents **compile-time polymorphism** (also called static polymorphism or early binding).

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


## 30. If a class implements two interfaces with the same default method, what happens and how do you resolve it?

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

## 31. Can an interface have a constructor in Java, and why or why not?

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


## 31. Can an interface have instance variables in Java, and if not, what type of variables can it have?

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

## 32. Summary Java Inheritance

**🔹 Basics**

* **Inheritance:** Mechanism where one class acquires properties and behavior of another class.
* **Parent Class (Super Class):** Class whose properties are inherited.
* **Child Class (Sub Class):** Class that inherits from parent class.
* **extends keyword:** Used to inherit a class.


**🔹 Key Concepts**

* **Code Reusability:** Avoids duplication by reusing existing code.
* **Method Overriding:** Child class provides its own implementation of parent method.
* **super keyword:** Used to access parent class methods/constructors.
* **IS-A Relationship:** Represents inheritance relationship (e.g., Dog is an Animal).


**🔹 Types of Inheritance**

* **Single Inheritance:** One parent → one child.
* **Multilevel Inheritance:** Chain of inheritance (A → B → C).
* **Hierarchical Inheritance:** One parent → multiple children.
* **Multiple Inheritance:** Not supported with classes (supported via interfaces).


**⚠️ Important Points**

* Constructors are **not inherited**, but can be called using `super()`.
* Private members are **not directly accessible** in child class.



# ✅ 5. Java Interface & Abstract Class 


## 1. What is an Interface?

An interface in Java is a blueprint that contains only abstract methods (by default) and constants. It is used to achieve 100% abstraction and multiple inheritance.


```java
interface Animal {
    void sound(); // abstract method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
}
```

## 2. What are the interface available in Java?

In Java, interfaces are mainly of four types:

**Normal Interface :**
A normal interface defines a contract with multiple abstract methods that a class must implement.

```java
interface Payment {
    void pay();
}
```

**Functional Interface :**
A functional interface contains exactly one abstract method and is used for lambda expressions.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // single abstract method
    
    default void print() { } // default methods allowed
}

// Usage with lambda
Calculator add = (a, b) -> a + b;
int result = add.calculate(5, 3);
```

**Marker Interface :**
A marker interface is an empty interface used to tag a class for special behavior.

```java
import java.io.Serializable;

// Marker interface
interface Serializable {
    // Empty - just marks the class
}

class Student implements Serializable {
    String name;
    // This class can now be serialized
}
```

**Nested Interface :**
A nested interface is declared inside a class or another interface for logical grouping.

```java
class Bank {
    interface RBI {
        void rule();
    }
}
```

## 3. What are static methods in interfaces?

**Static methods in interfaces** are methods declared with the **`static`** keyword inside an interface in **Java**.
They **belong to the interface itself and are called using the interface name**, not by implementing classes.

```java
interface MyInterface {

    static void show() {
        System.out.println("Static method in interface");
    }
}

public class Test {
    public static void main(String[] args) {
        MyInterface.show();  // called using interface name
    }
}
```


## 4. What is an Abstract Class?


An abstract class is a class that can have both abstract methods and concrete methods. It is used when we want partial abstraction.



```java
abstract class Animal {
    abstract void sound(); // abstract method

    void eat() { // concrete method
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}
```


## 5. When should you use an interface instead of an abstract class?

Use **interface** when:

* You want **multiple inheritance**
* You define **only method contracts (what to do, not how)**
* Different classes share **common behavior but not relationship**

Use **abstract class** when:

* Classes are **closely related**
* You want **common code + common fields**


## 6. Can we create object of interface or abstract class?


No, we cannot create objects of interface or abstract class directly. But we can create objects using their implementing or child classes.



```java
Animal a = new Dog(); // valid
```


## 7. Can an interface have methods with implementation? (Java 8+)


Yes, from Java 8 onwards, interfaces can have methods with implementation using default and static keywords.



```java
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }
}
```


## 8. What are default and static methods in interface?


Default methods are instance methods with implementation that can be overridden. Static methods belong to the interface and cannot be overridden.



```java
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }

    static void info() {
        System.out.println("Animal Info");
    }
}

class Dog implements Animal {}

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();           // default method
        Animal.info();     // static method
    }
}
```


## 9. Can abstract class have constructor? Why?


Yes, abstract class can have a constructor because it is used to initialize common properties when a subclass object is created.



```java
abstract class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }
}

class Dog extends Animal {
    Dog() {
        System.out.println("Dog constructor");
    }
}
```


## 10. Can abstract class have both abstract and concrete methods?


Yes, abstract class can have both abstract methods and concrete methods, which is why it provides partial abstraction.



```java
abstract class Animal {
    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}
```


## 11. Can interface have variables? What type?


Yes, interface can have variables, but they are by default public, static, and final constants.



```java
interface Animal {
    int AGE = 5; // public static final by default
}
```


## 12. When should we use interface vs abstract class?

👉 *Use interface when we need 100% abstraction and multiple inheritance. Use abstract class when we need partial abstraction with shared code.*


## 13. Can a class implement multiple interfaces?

👉 *Yes, a class can implement multiple interfaces to achieve multiple inheritance.*

```java id="l3p9sx"
interface A { void show(); }
interface B { void display(); }

class C implements A, B {
    public void show() {}
    public void display() {}
}
```


## 14. Can an abstract class implement an interface?

👉 *Yes, abstract class can implement interface and may or may not provide implementation.*

```java id="9v2tqg"
interface A {
    void show();
}

abstract class B implements A {
    // no implementation (allowed)
}
```


## 15. Can an interface extend another interface?

👉 *Yes, interface can extend one or more interfaces.*

```java id="y6m2an"
interface A {
    void show();
}

interface B extends A {
    void display();
}
```


## 16. Can an interface extend a class? (Tricky)

👉 *No, interface cannot extend a class, it can only extend interfaces.*


## 17. Can a class extend multiple abstract classes?

👉 *No, Java does not support multiple inheritance for classes, even abstract classes.*

```java id="g8x4qp"
// class C extends A, B {} ❌ NOT ALLOWED
```


## 18. Can interface have constructor?

👉 ❌ No (no object creation)


## 19. Can abstract class have constructor?

👉 ✅ Yes (used during object creation of subclass)


## 20. Can interface have private methods?

👉 ✅ Yes (Java 9+)


## 21. Can we override static methods in interface?

👉 ❌ No (only hide)


## 22. What if two interfaces have same default method?

👉 Must override:

```java
class Test implements A, B {
    public void show() {
        A.super.show();
    }
}
```


## 23. Can abstract class be final?

👉 ❌ No (conflict)


## 24. Can interface have main method?

👉 ✅ Yes (Java 8+)


## 25. Can we use both interface and abstract class together?

👉 ✅ Yes (very common)


## 26. What access modifiers are allowed in interface?

👉 Methods → public (default)
👉 Variables → public static final


## 27. Can we instantiate interface using lambda?

👉 ✅ Yes (functional interface)


## 9. What are SOLID principles?

**Answer:**

**SOLID** is a set of five object-oriented design principles that help write clean, maintainable, and scalable code.

- **S**ingle Responsibility: A class should have one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable for their base types
- **I**nterface Segregation: Many specific interfaces are better than one general interface
- **D**ependency Inversion: Depend on abstractions, not concrete implementations

**Example:**
**S — Single Responsibility Principle (SRP)**

One class should have only one responsibility.

❌ Wrong:

```java
class OrderService {
    public void createOrder() {
        // create order
    }

    public void sendEmail() {
        // send email
    }
}
```

✅ Correct:

```java
class OrderService {
    public void createOrder() {
        // create order
    }
}

class EmailService {
    public void sendEmail() {
        // send email
    }
}
```

---

**O — Open/Closed Principle (OCP)**

Open for extension, closed for modification.

```java
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Paid by Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paid by UPI");
    }
}

class PaymentService {
    public void processPayment(Payment payment) {
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        Payment p1 = new CardPayment();
        service.processPayment(p1);

        Payment p2 = new UpiPayment();
        service.processPayment(p2);
    }
}

// Output:
Paid by Card
Paid by UPI
```

Now we can add **NetBankingPayment** without changing existing code.

---

**L — Liskov Substitution Principle (LSP)**

Child class should replace parent class without breaking code.

```java
class Bird {
    public void fly() {
        System.out.println("Bird can fly");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

public class Main {
    public static void main(String[] args) {
        Bird b = new Bird();
        b.fly();

        Sparrow s = new Sparrow();
        s.fly();

        Bird b2 = new Sparrow(); // Runtime Polymorphism
        b2.fly();
    }
}


// Output:
Bird can fly
Sparrow can fly
Sparrow can fly
```

Bad example: Penguin cannot fly → violates LSP.

---

**I — Interface Segregation Principle (ISP)**

Create small interfaces.

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human working");
    }

    public void eat() {
        System.out.println("Human eating");
    }
}

class Robot implements Workable {
    public void work() {
        System.out.println("Robot working");
    }
}

public class Main {
    public static void main(String[] args) {
        Workable w1 = new Human();
        w1.work();

        Eatable e1 = new Human();
        e1.eat();

        Workable w2 = new Robot();
        w2.work();
    }
}

// Output:
Human working
Human eating
Robot working
```

Robot does not implement eat() → Correct.

---

**D — Dependency Inversion Principle (DIP)

Depend on abstraction, not concrete class.

```java
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class OrderService {
    private Payment payment;

    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() {
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {
        Payment payment = new CardPayment();   // Inject dependency
        OrderService orderService = new OrderService(payment);

        orderService.placeOrder();
    }
}

//Output:
Card payment
```

## 10. What is `.class` and When do we use in Java?

We use .class when we need to pass class metadata, for example in Spring configuration, exception handling, reflection, and getting beans from the Spring container.


**Common Places Where We Use `.class`**

**1. Spring Configuration**
```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);
```
Spring uses it to read configuration from the class.


**2. Exception Handling**
```java
@ExceptionHandler(Exception.class)
```
Tells Spring: handle this type of exception.


**3. Getting Bean from Spring**
```java
Student s = context.getBean(Student.class);
```
Spring will find bean of type `Student`.


**4. Reflection**
```java
Class<?> c = Student.class;
```
Used in reflection to get class information.

**5. Annotations**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
```
These also use `.class` internally to store metadata.


**`.class` vs `new`**

| `.class` | `new` |
|----------|------|
| Class metadata | Creates object |
| No memory for object | Object created |
| Used by frameworks | Used by developers |

Example:
```java
Student.class   // Class info
new Student()   // Object
```

## 11.  Summary Java Interface & Abstract Class

**🔹 Interface**

* **Interface:** A contract that defines methods a class must implement.
* **Methods:** By default abstract (Java 8+ supports default & static methods).
* **implements keyword:** Used to implement an interface.
* **Multiple Inheritance:** Supported via interfaces.
* **No Constructors:** Interfaces cannot have constructors.
* **Variables:** By default `public static final` (constants).

**🔹 Abstract Class**

* **Abstract Class:** A class that can have both abstract and concrete methods.
* **abstract keyword:** Used to declare abstract class/method.
* **Constructor:** Allowed and used for initialization.
* **Single Inheritance:** A class can extend only one abstract class.
* **Partial Abstraction:** Supports both implemented and unimplemented methods.
