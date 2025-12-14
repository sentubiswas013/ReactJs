# Top 1000 Java Interview Question & Answers

## Method Overloading and Overriding

### 40. **What Is the Other Name of Method Overloading?**

**Method Overloading** is also commonly referred to as **"compile-time polymorphism"** or **"static polymorphism."**

This is because the decision regarding which method to call is made during **compile-time** based on the method signature (the number and type of parameters). The method name remains the same, but different parameter lists allow for multiple versions of the method.

### 41. **How Will You Implement Method Overloading in Java?**

**Method overloading** in Java is implemented by defining multiple methods with the **same name** but **different method signatures** within the same class. The method signature includes the method name and the number, type, or order of parameters. Overloading is not based on the return type of the method.

Example of method overloading:

```java
class Calculator {

    // Method to add two integers
    int add(int a, int b) {
        return a + b;
    }

    // Method to add three integers
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method to add two doubles
    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println(calc.add(5, 3));            // Calls add(int, int)
        System.out.println(calc.add(5, 3, 2));         // Calls add(int, int, int)
        System.out.println(calc.add(5.5, 3.2));        // Calls add(double, double)
    }
}
```

In this example, the method `add()` is overloaded with different parameter lists. The correct version of the method is called based on the number and type of arguments passed.

### 42. **What Kinds of Argument Variations Are Allowed in Method Overloading?**

In **method overloading**, the variations allowed are based on the method's **parameter list**, which can differ in:

1. **Number of Parameters**: You can overload methods by changing the number of parameters.
   
   Example:
   ```java
   void display(int a) { }
   void display(int a, int b) { }
   ```

2. **Type of Parameters**: You can overload methods by changing the type of one or more parameters.

   Example:
   ```java
   void show(int a) { }
   void show(double a) { }
   ```

3. **Order of Parameters**: You can overload methods by changing the order of parameters, even if the types are the same.

   Example:
   ```java
   void print(int a, double b) { }
   void print(double b, int a) { }
   ```

4. **Varargs (Variable Number of Arguments)**: Java allows you to pass a variable number of arguments using varargs (`...`), which can also be a form of overloading.

   Example:
   ```java
   void print(int... numbers) { }
   void print(String... strings) { }
   ```

However, **method overloading is determined by the method signature**, not by the return type, which leads to the next question.

### 43. **Why It Is Not Possible to Do Method Overloading by Changing the Return Type of Method in Java?**

In Java, **method overloading cannot be achieved by changing only the return type** because the method signature must be unique and distinguishable. The method signature consists of the **method name** and the **parameter list**, but **not the return type**. Therefore, if two methods have the same name and parameter list but different return types, the Java compiler cannot distinguish between them when called, leading to ambiguity.

For example, this would **not compile**:

```java
class Example {
    int add(int a, int b) { return a + b; }
    double add(int a, int b) { return a + b; }  // Error: Return type alone cannot differentiate methods
}
```

In this case, the two methods have the same name and parameters, so Java cannot decide which one to call based solely on the return type.

### 44. **Is It Allowed to Overload `main()` Method in Java?**

Yes, it is allowed to **overload the `main()` method** in Java. The `main()` method is just like any other method in a class, so you can define multiple versions of it with different parameter lists. 

However, the **entry point for the Java program** is the **`public static void main(String[] args)`** method, which is called by the JVM when the program starts. If you create overloaded versions of the `main()` method, they will not be called by the JVM. You would need to call them explicitly from within your `main()` method or another method.

Example of overloading `main()`:

```java
class Main {

    public static void main(String[] args) {
        System.out.println("Standard main method.");
        main(5);  // Calling overloaded main
    }

    // Overloaded main method with an integer argument
    public static void main(int a) {
        System.out.println("Overloaded main method: " + a);
    }
}
```

Output:
```
Standard main method.
Overloaded main method: 5
```

In this case, the JVM invokes the `main(String[] args)` method, but the overloaded `main(int a)` method can be called explicitly inside the program.

### 45. **How Do We Implement Method Overriding in Java?**

**Method overriding** in Java is a concept where a subclass provides a **specific implementation** for a method that is already defined in its superclass. The method in the subclass must have the **same signature** (same name, same parameter list) as the method in the superclass. The `@Override` annotation is commonly used for clarity and to help the compiler catch errors.

To implement method overriding:
1. The method in the **superclass** should be **non-static** and typically **public or protected** (so it can be overridden).
2. The subclass should provide its own implementation of the method with the same method signature.

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {  // Overriding the sound method in the Dog subclass
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.sound();  // Calls Animal's sound()

        Dog dog = new Dog();
        dog.sound();  // Calls Dog's overridden sound()
    }
}
```

Output:
```
Animal makes a sound
Dog barks
```

In this example:
- The `sound()` method in the `Dog` class **overrides** the `sound()` method in the `Animal` class.
- When the method is called on an object of type `Dog`, the overridden version in the `Dog` class is executed.
- The `@Override` annotation is used to indicate that the method is intentionally overriding a superclass method.

### Key Points of Method Overriding:
- The method in the subclass must have the same method signature as the one in the superclass.
- Overriding allows the subclass to provide its own specific behavior.
- The method in the superclass should not be `private`, `final`, or `static` (because such methods cannot be overridden).
- The return type of the overridden method should be the same or a **subtype** (covariant return type) of the return type in the superclass method.

### 46. **Are We Allowed to Override a Static Method in Java?**

No, we **cannot override** a static method in Java. Static methods are associated with the class rather than with instances of the class. Therefore, static methods are resolved at compile time based on the class type, not the object type. This makes static methods **not subject to method overriding**.

However, we can **hide** a static method in a subclass by defining a static method with the same signature in the subclass. This is known as **method hiding**, not overriding.

### 47. **Why Java Does Not Allow Overriding a Static Method?**

Java does not allow overriding static methods because static methods are resolved **at compile-time** based on the class type, not the object type. Since static methods are tied to the class itself (not instances), they are not part of the object's dynamic dispatch mechanism that is needed for method overriding.

In other words:
- Static methods are called directly on the class (e.g., `ClassName.method()`).
- Method overriding involves dynamic polymorphism, where the method that gets called is determined at **runtime** based on the object’s type. Since static methods don’t participate in the runtime polymorphism, they cannot be overridden.

If a subclass defines a static method with the same name and signature as a static method in the parent class, it **hides** the parent class method, but this is not considered overriding.

### 48. **Is It Allowed to Override an Overloaded Method?**

Yes, **overloaded methods** can be overridden in Java. Overloading and overriding are two different concepts:
- **Overloading** occurs when methods have the same name but different parameter lists in the same class.
- **Overriding** occurs when a subclass provides a specific implementation for a method already defined in its superclass.

An **overloaded method** can be overridden if it is inherited by a subclass. The overriding method in the subclass must have the same method signature (name and parameters) as the method in the superclass.

Example:

```java
class Parent {
    void display(int a) {
        System.out.println("Parent display with int: " + a);
    }
}

class Child extends Parent {
    @Override
    void display(int a) {
        System.out.println("Child display with int: " + a);
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        p.display(10);  // Calls overridden method in Child
    }
}
```

In this example, the `display(int)` method is overridden by the `Child` class.

### 49. **What Is the Difference Between Method Overloading and Method Overriding in Java?**

| Feature                     | **Method Overloading**                                  | **Method Overriding**                                  |
|-----------------------------|---------------------------------------------------------|--------------------------------------------------------|
| **Definition**               | Defining multiple methods with the same name but different parameter lists within the same class. | Providing a specific implementation of a method in a subclass that already exists in the superclass. |
| **Binding Time**             | Compile-time (resolved at compile time).               | Runtime (resolved dynamically at runtime).              |
| **Return Type**              | The return type can be different, as long as the parameter list differs. | The return type must be the same or covariant (a subclass type of the return type in the superclass). |
| **Method Signature**         | The method name and parameter list must differ.         | The method name and parameter list must be the same.     |
| **Inheritance Requirement**  | No inheritance required; overloading happens within the same class. | Inheritance required (subclass inherits the method).     |
| **Polymorphism**             | Not related to polymorphism.                          | Associated with polymorphism (dynamic method dispatch). |

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    // Method Overriding
    @Override
    void sound() {
        System.out.println("Dog barks");
    }

    // Method Overloading
    void sound(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Dog barks");
        }
    }
}
```

### 50. **Does Java Allow Virtual Functions?**

Yes, **Java supports virtual functions**. In fact, **all non-static, non-private methods in Java are virtual** by default. When you call a method on an object, Java determines at **runtime** which method to invoke, based on the actual object type (not the reference type). This is known as **dynamic method dispatch**, and it allows method overriding to work.

For example, if you have a superclass and subclass with an overridden method, Java will call the subclass method even if the reference variable is of the superclass type. This dynamic resolution of the method is referred to as **virtual method invocation** or **runtime polymorphism**.

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();  // Animal reference, but Dog object
        animal.sound();  // Calls Dog's sound() method, not Animal's
    }
}
```

In this example, even though the reference type is `Animal`, the **virtual method** `sound()` is resolved at runtime to the `Dog` class version.

### 51. **What Is Meant by Covariant Return Type in Java?**

A **covariant return type** allows a method in a subclass to return a type that is a subclass of the return type declared in the superclass method. This concept is allowed in method overriding, meaning the return type in the overridden method can be more specific (or a subclass) than the return type in the parent class.

Example:

```java
class Animal {
    Animal getAnimal() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    Dog getAnimal() {  // Covariant return type (Dog is a subclass of Animal)
        return new Dog();
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Animal animal = dog.getAnimal();  // Returns a Dog, but the reference is of type Animal
    }
}
```