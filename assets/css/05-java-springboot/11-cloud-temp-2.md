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

**How ClassLoader Works:**

Java follows a **Delegation Hierarchy Model**:

1. **Bootstrap ClassLoader** — Loads core Java classes (e.g., `java.lang.*`)
2. **Extension (Platform) ClassLoader** — Loads classes from `jre/lib/ext`
3. **Application (System) ClassLoader** — Loads classes from classpath


## 6. How do you create an immutable class in Java?

An immutable class in Java is a class whose objects cannot be changed after they are created. To create an immutable class:

1. Make the class `final` so it cannot be subclassed.
2. Make all fields `private` and `final`.
3. Do not provide setters.
4. Initialize fields through constructor.
5. Return copies of mutable objects (defensive copy).

```java
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


## 9. What are instance variables and methods?

Instance variables belong to object and methods define behavior of object.

```java
class Employee {
    String name; // instance variable

    void work() { // instance method
        System.out.println("Working...");
    }
}
```


## 10. What is a constructor? Types?

Constructor is a special method used to initialize objects. It is called automatically when object is created.

**Types:**

* Default — no parameters; Java provides it automatically if none is defined.
* Parameterized — accepts arguments to initialize fields.

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


## 11. What is the static keyword?

**`static`** is a keyword in Java used to declare variables, methods, or blocks that **belong to the class instead of an object**, so they can be accessed without creating an instance.

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


## 12. Difference: static vs non-static?

Static belongs to class, non-static belongs to object.

| Static           | Non-Static               |
| ---------------- | ------------------------ |
| Shared           | Separate for each object |
| No object needed | Object required          |


## 13. Can a class have multiple constructors?

Yes, this is called constructor overloading.

```java
class Student {
    Student() {}
    Student(String name) {}
    Student(int age) {}
}
```


## 14. Can a constructor be final, static, or abstract in Java?

No, a constructor cannot be **final, static, or abstract**.

**Why:**

* **final** → Constructor is not inherited, so it cannot be overridden.
* **static** → Static belongs to class, but constructor is used to create object.
* **abstract** → Abstract methods have no body, but constructor must have a body.


## 15. Can we make a class `final`? Why?

Yes, final class cannot be extended. It is used to prevent inheritance for security or immutability.

```java
final class A {}

// class B extends A {} ❌ ERROR
```


## 16. Can we make a class `abstract`?

Yes, abstract class cannot be instantiated and can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();
}
```


## 17. What is the difference between interface and abstract class?

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


## 18. Can a class be both abstract and final?

No, because abstract needs inheritance and final restricts inheritance.


## 19. What is inner class? Types?

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


## 20. What is singleton class?

Singleton class allows only one object creation.

```java
class Singleton {
    private static Singleton obj = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return obj;
    }
}

public class Test {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
    }
}
```

## 21. Is it allowed to overload main() method in Java?

**Yes, the `main()` method can be overloaded** in Java.

The JVM only calls **`public static void main(String[] args)`**, while other overloaded versions behave as **regular methods**.

```java
public class Test {
    public static void main(String[] args) { } // JVM entry point
    public static void main(int x) { }         // Overloaded
    public static void main() { }              // Overloaded
}
```


## 22. Is it allowed to override main() method in Java?

**No**, because `main()` is **static**, and static methods cannot be overridden — they are hidden.

Static methods are resolved at **compile time**, so they cannot participate in runtime polymorphism.


## 23. Can you override static methods?

No. **Static methods belong to the class, not instances,** so they're resolved at compile time based on the reference type (method hiding, not overriding).

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


## 24. Is it possible to execute a program without defining a main() method?

It is **technically possible** to run code in a **static block** without a `main()` method, but **modern Java requires `main()`** as the entry point.

```java
class NoMain {
    static {
        System.out.println("Executing without main");
        System.exit(0); // Required to prevent error
    }
}
// Works in older Java versions, not recommended
```


## 25. Can we create object without `new` keyword?

Yes, using methods like clone(), factory methods, reflection, or deserialization.

```java
class A implements Cloneable {
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```


## 26. What happens if a class has no constructor?

Java provides a default constructor automatically.


## 27. Can a class be private or protected?

Top-level class cannot be private or protected, only public or default. Inner classes can be private/protected.

```java
class Outer {
    private class Inner {}
}
```


## 28. What happens if a static block throws an unchecked exception?

* Class will **fail to load**
* JVM throws **ExceptionInInitializerError**
* Program stops and class cannot be used

```java
class Test {
    static {
        System.out.println("Static block running");
        int x = 10 / 0; // ArithmeticException
    }
}
// Exception in thread "main" java.lang.ExceptionInInitializerError
```

## 29. Summary Classes and Objects

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

Java does not support multiple inheritance with **classes** to avoid the **Diamond Problem** and complexity. But Java does support multiple inheritance with **interfaces**.

```java
// class D extends B, C {}  // ❌ Not allowed in Java
```

## 3. What is the diamond problem and how does Java solve it?

The diamond problem occurs when a class inherits from two classes that both inherit from the same base class, creating ambiguity about which method to call.

```
    A
   / \
  B   C
   \ /
    D
```

Java solves it by not allowing multiple class inheritance but supporting multiple interface inheritance with **default methods**, requiring explicit override to resolve conflicts.

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

* **Reuse code**
* **Avoid duplication**
* **Make code cleaner and organized**


## 5. What is `extends` keyword?

`extends` is used to **inherit a class**.

```java
class Dog extends Animal {}
```


## 6. What is IS-A Relationship?

IS-A means **one object is a type of another**.

```java
Dog d = new Dog(); // Dog is also an Animal
```


## 7. Types of Inheritance in Java

Java supports:

* **Single**
* **Multilevel**
* **Hierarchical**

Multiple inheritance is not supported with classes.


## 8. What is Multilevel Inheritance?

When a class inherits from a class, which already inherits another class.

```java
class Animal { void eat() {} }
class Dog extends Animal { void bark() {} }
class Puppy extends Dog { void weep() {} }
// Puppy → Dog → Animal
```


## 9. What is Hierarchical Inheritance?

Multiple child classes inherit from one parent class.

```java
class Animal { void eat() {} }
class Dog extends Animal {}
class Cat extends Animal {}
```


## 10. Can we override final methods?

❌ No. Final methods cannot be changed in child class.

```java
class Animal {
    final void run() {}
}

class Dog extends Animal {
    // ❌ Error: cannot override final method
}
```


## 11. What is runtime polymorphism?

Method execution is decided at runtime based on object type using method overriding.

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


## 12. How does method overriding work internally?

JVM uses **dynamic method dispatch** — it decides method call at runtime based on actual object, not reference.


## 13. What are rules for method overriding?

* Same name, same parameters, same or covariant return type
* Cannot reduce access level
* Cannot override `final`, `static`, `private`


## 14. What is covariant return type?

Child class can return a more specific type than parent method.

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


## 15. Can constructors be inherited?

No, constructors are not inherited because they belong to class initialization.


## 16. What is the order of constructor execution?

Parent constructor executes first, then child constructor.

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() { System.out.println("Child"); }
}
// Output: Parent → Child
```


## 17. What happens if parent constructor is not called?

Java automatically calls default parent constructor using `super()`.


## 18. Can we inherit private members?

Yes, but we cannot access them directly — only via public/protected methods.

```java
class A {
    private int x = 10;
    int getX() { return x; }
}

class B extends A {
    void show() {
        System.out.println(getX()); // ✅
    }
}
```


## 19. Can we override private methods?

No, private methods are not visible, so they cannot be overridden.


## 20. Can we change access modifier while overriding?

Yes, but only to increase visibility, not decrease.

```java
class A {
    protected void show() {}
}

class B extends A {
    public void show() {} // ✅ allowed
}
```


## 21. What happens if parent and child have same variable name?

Variable hiding happens. Access depends on reference type.

```java
class A { int x = 10; }
class B extends A { int x = 20; }

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        System.out.println(obj.x); // 10
    }
}
```


## 22. Can abstract class have constructor?

Yes, it is used to initialize variables when child object is created.

```java
abstract class A {
    A() { System.out.println("Abstract constructor"); }
}

class B extends A {}
```


## 23. Can an interface extend another interface?

Yes, using the `extends` keyword. A child interface inherits all methods from the parent interface.

```java
interface A {
    void methodA();
}

interface B extends A {
    void methodB();
}
```


## 24. Can class extend interface?

No, class cannot extend interface. It uses `implements` keyword.

```java
interface A {}
class B implements A {}
```

## 25. Which type of polymorphism does method overloading represent?

**Method overloading** represents **compile-time polymorphism** (static polymorphism / early binding).

```java
class Calculator {
    int add(int a, int b)          { return a + b; }
    double add(double a, double b) { return a + b; }
}
```


## 26. If a class implements two interfaces with the same default method, what happens and how do you resolve it?

Java gives a **compile-time error** — the class must **override** the conflicting default method.

```java
interface A {
    default void greet() { System.out.println("Hello from A"); }
}

interface B {
    default void greet() { System.out.println("Hello from B"); }
}

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet(); // explicitly choose
    }
}
```

## 27. Can an interface have a constructor?

**No.** Interfaces cannot have constructors because they cannot be instantiated and have no instance state to initialize.

## 28. Can an interface have instance variables?

**No.** All variables in an interface are implicitly **`public static final`** (constants).

```java
interface MathConstants {
    double PI = 3.14159;   // public static final
    int MAX_VALUE = 100;
}
```

## 29. Summary Java Inheritance

**🔹 Basics**

* **Inheritance:** Mechanism where one class acquires properties and behavior of another class.
* **extends keyword:** Used to inherit a class.
* **super keyword:** Used to access parent class methods/constructors.

**🔹 Key Concepts**

* **Code Reusability:** Avoids duplication by reusing existing code.
* **Method Overriding:** Child class provides its own implementation of parent method.
* **IS-A Relationship:** Represents inheritance relationship (e.g., Dog is an Animal).

**🔹 Types of Inheritance**

* **Single:** One parent → one child.
* **Multilevel:** Chain of inheritance (A → B → C).
* **Hierarchical:** One parent → multiple children.
* **Multiple:** Not supported with classes (supported via interfaces).

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

## 2. What are the types of interfaces available in Java?

**Normal Interface:** Defines a contract with multiple abstract methods.

```java
interface Payment {
    void pay();
}
```

**Functional Interface:** Contains exactly one abstract method; used for lambda expressions.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

Calculator add = (a, b) -> a + b;
```

**Marker Interface:** An empty interface used to tag a class for special behavior (e.g., `Serializable`).

```java
class Student implements Serializable {
    String name;
}
```

**Nested Interface:** Declared inside a class or another interface for logical grouping.

```java
class Bank {
    interface RBI {
        void rule();
    }
}
```

## 3. What is an Abstract Class?

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


## 4. When should you use an interface instead of an abstract class?

Use **interface** when:
* You want **multiple inheritance**
* You define **only method contracts**
* Different classes share **common behavior but not relationship**

Use **abstract class** when:
* Classes are **closely related**
* You want **common code + common fields**


## 5. Can we create object of interface or abstract class?

No, we cannot create objects directly. But we can create objects using their implementing or child classes.

```java
Animal a = new Dog(); // valid
```


## 6. What are default and static methods in interface? (Java 8+)

**Default methods** are instance methods with implementation that can be overridden.  
**Static methods** belong to the interface and cannot be overridden.

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


## 7. Can abstract class have constructor? Why?

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


## 8. Can abstract class have both abstract and concrete methods?

Yes, which is why it provides partial abstraction.

```java
abstract class Animal {
    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}
```


## 9. Can interface have variables? What type?

Yes, but they are by default **public, static, and final** constants.

```java
interface Animal {
    int AGE = 5; // public static final by default
}
```


## 10. Can a class implement multiple interfaces?

Yes, to achieve multiple inheritance.

```java
interface A { void show(); }
interface B { void display(); }

class C implements A, B {
    public void show() {}
    public void display() {}
}
```


## 11. Can an abstract class implement an interface?

Yes, abstract class can implement interface and may or may not provide implementation.

```java
interface A {
    void show();
}

abstract class B implements A {
    // no implementation (allowed)
}
```


## 12. Can an interface extend a class?

No, interface can only extend interfaces.


## 13. Can a class extend multiple abstract classes?

No, Java does not support multiple inheritance for classes, even abstract classes.


## 14. Can interface have private methods?

✅ Yes (Java 9+)


## 15. Can we override static methods in interface?

❌ No (only hide)


## 16. What if two interfaces have same default method?

Must override:

```java
class Test implements A, B {
    public void show() {
        A.super.show();
    }
}
```


## 17. Can abstract class be final?

❌ No (conflict — abstract requires subclassing, final prevents it)


## 18. Can interface have main method?

✅ Yes (Java 8+)


## 19. Can we use both interface and abstract class together?

✅ Yes (very common pattern)


## 20. What access modifiers are allowed in interface?

* Methods → `public` (default)
* Variables → `public static final`


## 21. Can we instantiate interface using lambda?

✅ Yes (functional interface)


## 22. What are SOLID principles?

**SOLID** is a set of five object-oriented design principles that help write clean, maintainable, and scalable code.

- **S**ingle Responsibility: A class should have one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable for their base types
- **I**nterface Segregation: Many specific interfaces are better than one general interface
- **D**ependency Inversion: Depend on abstractions, not concrete implementations

**S — Single Responsibility Principle (SRP)**

```java
class OrderService {
    public void createOrder() { }
}

class EmailService {
    public void sendEmail() { }
}
```

---

**O — Open/Closed Principle (OCP)**

```java
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() { System.out.println("Paid by Card"); }
}

class UpiPayment implements Payment {
    public void pay() { System.out.println("Paid by UPI"); }
}

class PaymentService {
    public void processPayment(Payment payment) {
        payment.pay();
    }
}
```

---

**L — Liskov Substitution Principle (LSP)**

```java
class Bird {
    public void fly() { System.out.println("Bird can fly"); }
}

class Sparrow extends Bird {
    @Override
    public void fly() { System.out.println("Sparrow can fly"); }
}
// Bad example: Penguin cannot fly → violates LSP
```

---

**I — Interface Segregation Principle (ISP)**

```java
interface Workable { void work(); }
interface Eatable  { void eat();  }

class Human implements Workable, Eatable {
    public void work() { System.out.println("Human working"); }
    public void eat()  { System.out.println("Human eating");  }
}

class Robot implements Workable {
    public void work() { System.out.println("Robot working"); }
}
```

---

**D — Dependency Inversion Principle (DIP)**

```java
interface Payment { void pay(); }

class CardPayment implements Payment {
    public void pay() { System.out.println("Card payment"); }
}

class OrderService {
    private Payment payment;

    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() { payment.pay(); }
}
```

## 23. What is `.class` and when do we use it in Java?

We use `.class` when we need to pass class metadata — for example in Spring configuration, exception handling, reflection, and getting beans from the Spring container.

```java
// Spring Configuration
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

// Exception Handling
@ExceptionHandler(Exception.class)

// Getting Bean from Spring
Student s = context.getBean(Student.class);

// Reflection
Class<?> c = Student.class;
```

| `.class`          | `new`              |
| ----------------- | ------------------ |
| Class metadata    | Creates object     |
| No object created | Object in memory   |
| Used by frameworks| Used by developers |

## 24. Summary Java Interface & Abstract Class

**🔹 Interface**

* A contract that defines methods a class must implement.
* Methods: by default abstract (Java 8+ supports default & static methods).
* `implements` keyword used to implement an interface.
* Multiple Inheritance: supported via interfaces.
* No Constructors allowed.
* Variables: by default `public static final` (constants).

**🔹 Abstract Class**

* Can have both abstract and concrete methods.
* `abstract` keyword used to declare abstract class/method.
* Constructor: allowed and used for initialization.
* Single Inheritance: a class can extend only one abstract class.
* Supports both implemented and unimplemented methods.
