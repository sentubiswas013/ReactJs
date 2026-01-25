# 3. Classes and Objects

## 1. What is a constructor in Java?

A constructor is a special method that initializes objects when they're created. It has the same name as the class and no return type.

- Called automatically when creating objects with 'new'
- Used to set initial values for object properties
- Can be overloaded with different parameters
- If not defined, Java provides a default constructor

```java
public class Person {
    String name;
    
    // Constructor
    public Person(String name) {
        this.name = name;
    }
}

Person p = new Person("John"); // Constructor called
```

## 2. What is constructor chaining?

Constructor chaining is calling one constructor from another constructor in the same class or parent class. It helps avoid code duplication.

- Use `this()` to call another constructor in same class
- Use `super()` to call parent class constructor
- Must be the first statement in constructor

```java
public class Student {
    String name;
    int age;
    
    public Student() {
        this("Unknown", 0); // Calls parameterized constructor
    }
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

## 3. What is the difference between this and super keywords?

**this** refers to the current object instance, while **super** refers to the immediate parent class.

**this keyword:**
- References current object
- Calls current class constructor/methods
- Resolves naming conflicts

**super keyword:**
- References parent class
- Calls parent constructor/methods
- Accesses overridden methods

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

## 4. What is method overloading?

Method overloading means having multiple methods with the same name but different parameters in the same class. Java decides which method to call based on arguments.

- Same method name, different parameters
- Compile-time polymorphism
- Parameters can differ by number, type, or order

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

## 5. What is method overriding?

Method overriding is redefining a parent class method in the child class with the same signature. The child class version gets called instead of the parent's.

- Same method signature as parent
- Runtime polymorphism
- Use @Override annotation for safety
- Child method must be equally or more accessible

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

## 6. What is the difference between overloading and overriding?

**Method Overloading:**
- Same class, same method name, different parameters
- Compile-time decision (static binding)
- No inheritance required
- Can have different return types

**Method Overriding:**
- Parent-child classes, same method signature
- Runtime decision (dynamic binding)
- Requires inheritance
- Must have same return type

```java
// Overloading - same class
class Math {
    int multiply(int a, int b) { return a * b; }
    double multiply(double a, double b) { return a * b; }
}

// Overriding - inheritance
class Shape { void draw() { } }
class Circle extends Shape { 
    @Override void draw() { } // Same signature
}
```

## 7. What is dynamic method dispatch?

Dynamic method dispatch is Java's mechanism for runtime polymorphism where the actual method called is determined at runtime based on the object type, not reference type.

- JVM decides which overridden method to call
- Based on actual object type at runtime
- Enables true polymorphism in inheritance

```java
Animal animal1 = new Dog();
Animal animal2 = new Cat();

animal1.sound(); // Calls Dog's sound() method
animal2.sound(); // Calls Cat's sound() method

// Reference type is Animal, but actual method called
// depends on object type (Dog or Cat)
```

The JVM looks at the actual object type and calls the appropriate overridden method, making polymorphism work seamlessly.