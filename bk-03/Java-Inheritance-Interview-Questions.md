# 4. Java Inheritance 

## 1. Why doesn't Java support multiple inheritance?

Java doesn't support multiple inheritance of classes to avoid complexity and ambiguity. If a class inherited from two classes with the same method, Java wouldn't know which one to use.

- Prevents diamond problem confusion
- Keeps language simple and clean
- Avoids method resolution conflicts
- Maintains single inheritance hierarchy

Java provides interfaces to achieve multiple inheritance of behavior without the problems of multiple class inheritance.

## 2. What is the diamond problem?

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

Java solves the diamond problem by not allowing multiple class inheritance but supporting multiple interface inheritance with default methods.

- Single class inheritance only
- Multiple interface inheritance allowed
- Default methods in interfaces (Java 8+)
- Explicit override required for conflicts

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

## 4. Can you override static methods?

No, you cannot override static methods in Java. Static methods belong to the class, not instances, so they're resolved at compile time based on the reference type.

- Static methods are class-level, not instance-level
- Method hiding occurs instead of overriding
- Resolved at compile time (static binding)
- No polymorphism with static methods

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

## 5. What is covariant return type?

Covariant return type allows an overriding method to return a subtype of the return type declared in the parent method. This provides more specific return types in child classes.

- Return type can be more specific in child class
- Must be subtype of parent's return type
- Introduced in Java 5
- Enables more precise API design

```java
class Animal { }
class Dog extends Animal { }

class AnimalFactory {
    Animal createAnimal() { return new Animal(); }
}

class DogFactory extends AnimalFactory {
    @Override
    Dog createAnimal() { return new Dog(); } // Covariant return
}
```

## 6. What is the difference between IS-A and HAS-A relationship?

**IS-A relationship** represents inheritance - a child class IS-A type of parent class.
**HAS-A relationship** represents composition - a class HAS-A reference to another class.

**IS-A (Inheritance):**
- "extends" keyword
- Child inherits parent properties
- Represents specialization

**HAS-A (Composition):**
- Object as instance variable
- Represents ownership/containment
- More flexible than inheritance

```java
// IS-A relationship
class Dog extends Animal { } // Dog IS-A Animal

// HAS-A relationship  
class Car {
    Engine engine; // Car HAS-A Engine
    
    public Car() {
        engine = new Engine();
    }
}
```

IS-A creates tight coupling through inheritance, while HAS-A creates loose coupling through composition. Composition is often preferred for flexibility.