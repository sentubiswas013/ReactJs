# Top 1000 Java Interview Question & Answers

## OOPS

### 00. **OOP Concepts with Example: Abstraction, Polymorphism, Inheritance, and Encapsulation**

**Object-Oriented Programming (OOP)** is a programming paradigm based on the concept of "objects," which are instances of classes. The four major principles of OOP are:

1. **Abstraction**: Hiding the implementation details and showing only the essential features of an object.
   - Example: You use a **smartphone** without needing to understand its internal workings. The complex functionalities (e.g., networking, hardware operations) are abstracted from the user.

   ```java
   abstract class Animal {
       abstract void sound();  // Abstract method - no implementation
   }

   class Dog extends Animal {
       void sound() {
           System.out.println("Dog barks");
       }
   }

   class Main {
       public static void main(String[] args) {
           Animal animal = new Dog();  // Polymorphism
           animal.sound();  // Dog barks
       }
   }
   ```

2. **Polymorphism**: The ability of a single function or method to behave differently based on the object it is acting upon. This can be achieved through **method overriding** (runtime polymorphism) and **method overloading** (compile-time polymorphism).
   
   ```java
   class Animal {
       void sound() {
           System.out.println("Animal makes a sound");
       }
   }

   class Dog extends Animal {
       @Override
       void sound() {
           System.out.println("Dog barks");
       }
   }

   class Main {
       public static void main(String[] args) {
           Animal animal = new Dog();  // Runtime Polymorphism
           animal.sound();  // Dog barks
       }
   }
   ```

3. **Inheritance**: A mechanism where one class acquires the properties and behaviors (methods) of another class. Inheritance promotes code reusability.
   
   ```java
   class Animal {
       void sound() {
           System.out.println("Animal makes a sound");
       }
   }

   class Dog extends Animal {
       void sound() {
           System.out.println("Dog barks");
       }
   }
   ```

4. **Encapsulation**: The concept of wrapping data (variables) and methods (functions) together as a single unit and restricting access to some of the object's components. It is achieved using **private** variables and **public** getter and setter methods.
   
   ```java
   class Person {
       private String name;  // private variable

       // Getter and Setter methods
       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   class Main {
       public static void main(String[] args) {
           Person person = new Person();
           person.setName("John");
           System.out.println(person.getName());
       }
   }
   ```

### 52. **What Is Runtime Polymorphism?**

**Runtime Polymorphism**, also known as **dynamic method dispatch**, refers to the ability of Java to resolve method calls at runtime, based on the object type rather than the reference type. This allows one method to be used in different contexts, depending on the object that invokes it.

In Java, runtime polymorphism is typically achieved through **method overriding** (when a subclass overrides a method of its superclass).

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();  // Reference of Animal, object of Dog
        animal.sound();  // Dog barks (resolved at runtime)
    }
}
```

In the above example, the method `sound()` is invoked on an `Animal` reference, but at runtime, it calls the `sound()` method of the `Dog` class because the actual object is of type `Dog`.

### 53. **Is It Possible to Achieve Runtime Polymorphism by Data Members?**

No, **runtime polymorphism** in Java cannot be achieved by **data members**. Polymorphism in Java is **only applicable to methods**, not fields or variables. Method overriding is the mechanism through which runtime polymorphism is achieved.

Data members (fields) are resolved at compile time based on the reference type. If two classes have the same field name, the field in the class being referenced is used, not the one in the actual object.

Example:

```java
class Animal {
    String type = "Animal";
}

class Dog extends Animal {
    String type = "Dog";
}

class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println(animal.type);  // Outputs: Animal (resolved at compile time, not runtime)
    }
}
```

In this case, the field `type` in the `Animal` class is accessed, not the one in the `Dog` class, because field resolution is done at compile time, not at runtime.

### 54. **Explain the Difference Between Static and Dynamic Binding?**

**Binding** refers to the linking of a method call to the method body. There are two types of binding in Java: **static binding** (early binding) and **dynamic binding** (late binding).

- **Static Binding** (Early Binding):
  - It occurs at **compile time**.
  - It is used for method calls to **static**, **private**, and **final** methods, as well as **variables**.
  - In static binding, the method to be invoked or the variable to be accessed is determined at compile time based on the reference type.

  Example of static binding:

  ```java
  class Animal {
      static void sound() {
          System.out.println("Animal makes a sound");
      }
  }

  class Dog extends Animal {
      static void sound() {
          System.out.println("Dog barks");
      }
  }

  class Main {
      public static void main(String[] args) {
          Animal animal = new Dog();
          animal.sound();  // Static binding, calls Animal's sound() method
      }
  }
  ```

- **Dynamic Binding** (Late Binding):
  - It occurs at **runtime**.
  - It is used for **method overriding**, where the method to be invoked is determined based on the actual object type at runtime.
  - It allows Java to choose the correct method when a method is called on a reference variable, based on the object type assigned to that reference.

  Example of dynamic binding:

  ```java
  class Animal {
      void sound() {
          System.out.println("Animal makes a sound");
      }
  }

  class Dog extends Animal {
      @Override
      void sound() {
          System.out.println("Dog barks");
      }
  }

  class Main {
      public static void main(String[] args) {
          Animal animal = new Dog();
          animal.sound();  // Dynamic binding, calls Dog's sound() method at runtime
      }
  }
  ```

### Key Differences:
- **Static Binding**:
  - Occurs at **compile time**.
  - Used for static, private, and final methods and fields.
  - Resolved based on the reference type.
- **Dynamic Binding**:
  - Occurs at **runtime**.
  - Used for method overriding (polymorphism).
  - Resolved based on the actual object type at runtime.

### 55. **What is Abstraction in Object-Oriented Programming?**

**Abstraction** is one of the fundamental principles of Object-Oriented Programming (OOP). It refers to the concept of **hiding the implementation details** and showing only the essential features of an object or system. In other words, abstraction allows us to define "what" an object does but not "how" it does it. This helps in reducing complexity and focusing on high-level functionalities.

In Java, abstraction can be achieved using:
1. **Abstract Classes**: Classes that cannot be instantiated directly and can have abstract methods (methods without body).
2. **Interfaces**: A contract that defines a set of methods that must be implemented by the class that implements the interface.

Example of Abstraction:

```java
abstract class Animal {
    abstract void sound();  // Abstract method (no implementation)
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();  // Animal reference, Dog object
        animal.sound();  // Dog barks
    }
}
```

In this example, the `sound()` method is abstract in the `Animal` class, so subclasses like `Dog` provide the implementation. The user of the class doesn't need to know how `sound()` is implemented but only that it will make a sound.

### 56. **How is Abstraction Different from Encapsulation?**

While both **abstraction** and **encapsulation** deal with hiding details, they focus on different aspects:

- **Abstraction** focuses on **hiding the complexity** and showing only the relevant functionality. It defines **what** an object should do but not **how** it should do it. 
  - Example: A **remote control** abstracts away the complexities of the television's internal working and provides only the essential functionality like power on/off, volume control, etc.

- **Encapsulation** focuses on **hiding the internal state** of an object and restricting access to it. It allows control over data by using **getter** and **setter** methods. It defines **how** an object’s data can be accessed or modified.
  - Example: A **bank account** class encapsulates the balance and allows access through methods like `deposit()` or `withdraw()`, hiding the internal balance state.

**Key Difference**:
- Abstraction hides complexity by exposing only necessary information (what an object does).
- Encapsulation hides data and allows controlled access to it (how an object stores and manipulates data).

Example:
```java
class BankAccount {
    private double balance;  // Encapsulation: balance is hidden

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;  // Deposit money into account
        }
    }

    public double getBalance() {
        return balance;  // Access the balance safely
    }
}

abstract class Shape {
    abstract void draw();  // Abstraction: defining a common method, but the implementation is hidden
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a Circle");
    }
}
```

### 57. **What is an Abstract Class in Java?**

An **abstract class** in Java is a class that **cannot be instantiated directly**. It is used as a base for other classes to inherit from. An abstract class can contain both abstract methods (methods without a body) and concrete methods (methods with implementation). 

**Key Points about Abstract Class**:
- It may contain abstract methods (methods without implementation).
- It may contain concrete methods (methods with implementation).
- It can have constructors, fields, and other members.
- A subclass that inherits an abstract class **must implement all of its abstract methods** unless the subclass is also abstract.

Example of Abstract Class:

```java
abstract class Animal {
    abstract void sound();  // Abstract method (no implementation)

    void eat() {  // Concrete method
        System.out.println("Animal is eating");
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
        Animal dog = new Dog();
        dog.sound();  // Dog barks
        dog.eat();    // Animal is eating
    }
}
```

### 58. **Is It Allowed to Mark a Method Abstract Without Marking the Class Abstract?**

No, **you cannot mark a method as abstract** without marking the class as abstract. In Java, an abstract method must be defined in an abstract class. This is because abstract methods do not have an implementation, and the responsibility of providing an implementation for that method lies with subclasses. If a class contains an abstract method, it must be declared as abstract.

Example of Invalid Code:

```java
class Animal {
    abstract void sound();  // Error: Cannot have an abstract method in a non-abstract class
}
```

To correct this, you must mark the class as abstract:

```java
abstract class Animal {
    abstract void sound();  // This is correct now
}
```

### 59. **Is It Allowed to Mark a Method Abstract as Well as Final?**

No, you **cannot mark a method as both `abstract` and `final`**. The `abstract` modifier indicates that the method must be overridden by subclasses, while the `final` modifier indicates that the method cannot be overridden. These two behaviors are contradictory.

- **`abstract`** methods must be overridden in a subclass.
- **`final`** methods cannot be overridden in any subclass.

Therefore, it is not possible to have a method that is both abstract and final.

Example of Invalid Code:

```java
abstract class Animal {
    final abstract void sound();  // Error: Cannot be both final and abstract
}
```

To resolve this, you should choose one of the modifiers based on your intention:
- If the method should be overridden, use `abstract`.
- If the method should not be overridden, use `final`.

### 60. **Can We Instantiate an Abstract Class in Java?**

No, you **cannot instantiate an abstract class** in Java directly. An **abstract class** is designed to be a base class and can have abstract methods (methods without implementation), so it cannot be instantiated. However, you can create an instance of a subclass that extends the abstract class and provides implementations for the abstract methods.

Example:

```java
abstract class Animal {
    abstract void sound();
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        // Animal animal = new Animal();  // Error: Cannot instantiate an abstract class
        Animal animal = new Dog();  // Valid, creates an instance of Dog
        animal.sound();  // Dog barks
    }
}
```

### 61. **What is an Interface in Java?**

An **interface** in Java is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types. Interfaces cannot contain instance fields or constructors. A class implements an interface by providing implementations for all of its methods. It defines a **contract** or a set of rules that the implementing class must follow.

Key points about interfaces:
- An interface defines **what a class should do**, but not **how it should do it**.
- A class that implements an interface **must provide an implementation** for all the methods declared by the interface, unless the class is abstract.
- An interface can be used to achieve **multiple inheritance** in Java.

Example:

```java
interface Animal {
    void sound();  // Method signature (no body)
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.sound();  // Dog barks
    }
}
```

### 62. **Is It Allowed to Mark an Interface Method as Static?**

Yes, **it is allowed** to mark a method as **static** in an interface, starting from Java 8. A **static method** in an interface can have a body, and it can be invoked directly using the interface name. However, **static methods cannot be overridden** by implementing classes. They are not part of the contract that implementing classes have to follow.

Example of a static method in an interface:

```java
interface Animal {
    static void greet() {
        System.out.println("Hello from Animal");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal.greet();  // Directly call the static method on the interface
    }
}
```

### 63. **Why Can an Interface Not Be Marked as Final in Java?**

An interface **cannot be marked as `final`** because the purpose of an interface is to provide a **contract** that other classes can **implement**. If an interface were marked as `final`, no class would be able to implement it, making it pointless. A final class cannot be subclassed, and similarly, marking an interface as final would prevent any class from implementing it.

Example:

```java
// Invalid: Interfaces cannot be final
// final interface Animal { 
//     void sound();
// }
```

### 64. **What is a Marker Interface?**

A **marker interface** is an interface that has no methods or fields. It is used to signal a special behavior or property for the classes that implement it. A class implements a marker interface to indicate that it possesses certain characteristics, and the behavior is typically checked at runtime by other components.

The marker interface itself does not enforce any behavior, but its presence tells the program that the class should be treated in a special way.

**Common examples** of marker interfaces in Java include:
- `Serializable`: Marks a class whose objects can be serialized.
- `Cloneable`: Marks a class whose objects can be cloned.

Example of a Marker Interface:

```java
interface Serializable {
    // No methods or fields
}

class Person implements Serializable {
    String name;
    int age;

    // Person class can now be serialized
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        // If we try to serialize a Person object, it must implement Serializable
    }
}
```

### 65. **What Can We Use Instead of Marker Interface?**

Instead of using a marker interface, we can use the following alternatives in Java:

1. **Annotations**:
   - Annotations can provide metadata about the class or methods, similar to marker interfaces, but with more flexibility. Annotations can be processed at compile time or runtime to perform specific actions based on the presence of the annotation.
   
2. **Custom Logic or Flags**:
   - We can use custom logic within the class, such as setting a flag (boolean or other values) to indicate a special behavior. This can provide more control compared to marker interfaces, as it allows more information to be conveyed.

3. **Reflection**:
   - Using Java reflection, we can inspect class types and determine whether a class has a specific behavior or characteristic, without needing a marker interface.

**Example with Annotations**:
```java
@interface Serializable {
}

@Serializable
class Person {
    String name;
    int age;
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        // Check if the class has the Serializable annotation
        if (person.getClass().isAnnotationPresent(Serializable.class)) {
            System.out.println("Person class is serializable");
        }
    }
}
```

### 66. **How Are Annotations Better Than Marker Interfaces?**

Annotations are considered better than marker interfaces in many situations due to the following reasons:

1. **Flexibility**:
   - Annotations can carry **additional metadata**. While a marker interface only indicates that a class has a specific property, an annotation can carry more detailed information (e.g., a version number, author, or description).

2. **More Powerful and Extensible**:
   - Annotations are more **powerful and flexible** as they can be processed at compile-time (using annotation processors) or runtime (using reflection). You can also apply them to methods, fields, parameters, etc.
   
3. **Avoids Inheritance Issues**:
   - Marker interfaces force a class to inherit from an interface, which may not always be desirable. Using annotations, we avoid unwanted inheritance relationships. Classes don't have to implement an interface just for marking purposes.
   
4. **Cleaner Code**:
   - Using annotations eliminates the need for unnecessary interface implementation, which may lead to cleaner and more maintainable code.

**Example**:
```java
@interface Marker {
    String value() default "Default Marker";
}

@Marker(value = "Special Class")
class MyClass {
    // Additional logic
}

public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        if (obj.getClass().isAnnotationPresent(Marker.class)) {
            System.out.println("This class has Marker annotation.");
        }
    }
}
```

### 67. **What is the Difference Between Abstract Class and Interface in Java?**

**Abstract Class** and **Interface** in Java are both used to represent abstract concepts, but they have several key differences:

| Feature                 | **Abstract Class**                                    | **Interface**                                      |
|-------------------------|--------------------------------------------------------|----------------------------------------------------|
| **Methods**             | Can have both abstract (without implementation) and concrete (with implementation) methods. | Can only have abstract methods (until Java 8) or default/static methods (from Java 8). |
| **Multiple Inheritance**| Supports single inheritance (a class can extend only one abstract class). | Supports multiple inheritance (a class can implement multiple interfaces). |
| **Constructors**        | Can have constructors.                                | Cannot have constructors.                         |
| **Fields**              | Can have instance variables (fields), both static and non-static. | Can only have static final variables (constants). |
| **Access Modifiers**    | Can have any access modifiers for methods and fields (e.g., public, private). | Methods in an interface are implicitly `public`, and fields are `public static final`. |
| **Purpose**             | Used when classes share common behavior and attributes, but they may have different implementations for some methods. | Used to define a contract that can be implemented by any class. |
| **Inheritance**         | A class can extend only one abstract class. | A class can implement multiple interfaces. |
  
**Example**:

```java
abstract class Animal {
    abstract void sound();  // Abstract method
    void eat() {            // Concrete method
        System.out.println("Animal is eating");
    }
}

interface Mammal {
    void giveBirth();  // Abstract method in interface
}
```

### 68. **Does Java Allow Us to Use Private and Protected Modifiers for Variables in Interfaces?**

In Java, the following rules apply to variables in interfaces:

- **Variables in interfaces** are implicitly **`public static final`**.
  - **`public`**: The variable is accessible by any class.
  - **`static`**: The variable belongs to the interface itself, not instances of the interface.
  - **`final`**: The variable cannot be modified once it is assigned.

As a result, **private** or **protected** modifiers are not allowed for variables in an interface because the primary goal of an interface is to provide a public contract that all implementing classes can access.

**Example of Valid Interface Variables**:

```java
interface Animal {
    // Implicitly public, static, and final
    int age = 10;  // Default access modifier is public static final

    void sound();  // Abstract method
}
```

You cannot use the `private` or `protected` modifiers for variables in an interface, and they will result in a compile-time error.

### 69. **How Can We Cast an Object Reference to an Interface Reference?**

In Java, you can cast an object reference to an interface reference if the object's class implements the interface. The cast is typically done when you want to treat the object as an instance of the interface, allowing you to call the methods defined by the interface.

Example:

```java
interface Animal {
    void sound();
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();  // Implicit casting to Animal reference
        animal.sound();  // Dog barks

        // Explicit casting to the Animal interface reference
        Dog dog = (Dog) animal;  // Cast to the Dog class
        dog.sound();  // Dog barks
    }
}
```

In this example:
- The `Dog` object is cast to the `Animal` interface reference. Since `Dog` implements `Animal`, this cast is valid.
- You can also cast the object back to the original type (`Dog`), allowing you to access methods specific to `Dog`.