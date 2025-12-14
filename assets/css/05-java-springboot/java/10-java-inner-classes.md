# Top 1000 Java Interview Question & Answers

## Inner Classes

### 112. **What is a Nested Class?**

A **nested class** in Java is a class that is defined within another class. Nested classes can be categorized into different types based on how they are structured and how they interact with the outer class. Nested classes can be useful for logically grouping classes that are only used in one place, enhancing code readability, and encapsulating helper classes that don't need to be exposed outside of their containing class.

### 113. **How Many Types of Nested Classes Are in Java?**

In Java, there are **four types** of nested classes:

1. **Static Nested Class**:
   - A static nested class is defined as a static member within an outer class. It can access the static members of the outer class, but it cannot access the instance variables or methods directly (without an explicit instance of the outer class).
   - It is independent of the instance of the outer class, which is why it is called a "static" nested class.
   
   Example:
   ```java
   class Outer {
       static int outerStaticVar = 10;
       
       static class StaticNested {
           void display() {
               System.out.println("Outer static variable: " + outerStaticVar);
           }
       }
   }
   ```

2. **Non-static (Inner) Class**:
   - A non-static nested class is known as an **inner class**. It is associated with an instance of the outer class and can access both instance variables and static variables/methods of the outer class.
   - It can be used when the nested class needs access to the instance variables and methods of the outer class.
   
   Example:
   ```java
   class Outer {
       int outerVar = 10;
       
       class Inner {
           void display() {
               System.out.println("Outer instance variable: " + outerVar);
           }
       }
   }
   ```

3. **Local Inner Class**:
   - A local inner class is defined within a method, constructor, or a block of code. It is local to that method or block and can access the local variables and parameters of the method or block in which it is defined (but only if those variables are declared as **final** or effectively final).
   
   Example:
   ```java
   class Outer {
       void method() {
           class LocalInner {
               void display() {
                   System.out.println("Inside local inner class");
               }
           }
           LocalInner li = new LocalInner();
           li.display();
       }
   }
   ```

4. **Anonymous Inner Class**:
   - An anonymous inner class is a class without a name. It is often used for implementing interfaces or extending classes in a concise and anonymous manner. The class is defined and instantiated in a single statement.
   
   Example:
   ```java
   interface Greeting {
       void greet();
   }

   class Outer {
       void createGreeting() {
           Greeting greeting = new Greeting() {
               public void greet() {
                   System.out.println("Hello from anonymous inner class!");
               }
           };
           greeting.greet();
       }
   }
   ```

### 114. **Why Do We Use Nested Classes?**

Nested classes are used in Java for various reasons:

1. **Logical Grouping**:
   - Nested classes allow you to logically group classes that are only used in one place. This makes the code more organized and easier to maintain.

2. **Encapsulation**:
   - They allow better encapsulation by keeping inner classes private and restricting access to them, reducing the chances of accidental misuse.

3. **Improved Code Readability**:
   - Using nested classes can make your code more readable and concise, especially when the nested class is closely tied to its outer class and does not need to be exposed outside.

4. **Reduction in Namespace Pollution**:
   - By using nested classes, you can avoid polluting the global namespace with classes that are only needed within the outer class.

5. **Access to Outer Class Members**:
   - Inner classes (non-static) have access to both instance and static members of the outer class, which is useful when the nested class needs to operate on the state of the outer class.

### 115. **What is the Difference Between a Nested Class and an Inner Class in Java?**

In Java, the terms **nested class** and **inner class** are often used interchangeably, but they refer to different concepts:

1. **Nested Class**:
   - A **nested class** is any class that is defined inside another class. It can be either **static** or **non-static**.
   - **Static nested classes** are not associated with an instance of the outer class.

2. **Inner Class**:
   - An **inner class** is a **non-static nested class** that is associated with an instance of the outer class. It has access to all instance variables and methods of the outer class.
   - Inner classes require an instance of the outer class to be instantiated.

**Summary of differences**:
- **Nested class**: A broader term that includes both static and non-static nested classes.
- **Inner class**: A specific type of non-static nested class that has access to instance members of the outer class.

### 116. **What is a Nested Interface?**

A **nested interface** is an **interface** defined inside another class or interface. It can be **static** (most common), and it can be accessed by the outer class or any other class in the same package. A nested interface is often used to represent some behavior that is only relevant to the outer class or interface.

#### Example:
```java
class Outer {
    interface NestedInterface {
        void display();
    }

    static class InnerClass implements NestedInterface {
        public void display() {
            System.out.println("Inside nested interface implementation");
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer.InnerClass obj = new Outer.InnerClass();
        obj.display();
    }
}
```

### Summary:

- **Nested class**: A class defined inside another class. Can be static or non-static.
- **Types of nested classes**: Static nested class, Inner class (non-static), Local inner class, and Anonymous inner class.
- **Nested class usage**: For logical grouping, better encapsulation, improving readability, and avoiding namespace pollution.
- **Nested interface**: An interface defined inside a class or another interface, typically static, and used for grouping related behaviors.

### 117. **How Can We Access the Non-Final Local Variable Inside a Local Inner Class?**

In Java, **local inner classes** (i.e., classes defined inside methods or blocks) can access local variables from their enclosing method or block. However, the local variables must be **final** or **effectively final**. This is because Java needs to ensure that the local variable's value does not change during the inner class's lifetime, which could lead to unpredictable behavior when the inner class is used.

#### **How to access non-final local variables?**
In Java 8 and later, **non-final local variables** can still be accessed by local inner classes if they are **effectively final**. A variable is considered effectively final if it is not modified after its initialization.

- **Effectively final** means that even though the variable is not explicitly declared as `final`, it is not reassigned after its initial assignment, and therefore behaves like a final variable.

### Example of **Effectively Final**:
```java
class Outer {
    void method() {
        int num = 10;  // Effectively final (not changed after initialization)
        
        class Inner {
            void display() {
                System.out.println("Value: " + num);  // Accessing effectively final variable
            }
        }
        
        Inner inner = new Inner();
        inner.display();
    }
}
```

However, if you attempt to modify the variable after the inner class is instantiated, it will result in a compile-time error:
```java
class Outer {
    void method() {
        int num = 10;
        
        class Inner {
            void display() {
                System.out.println("Value: " + num);
            }
        }
        
        num = 20;  // Error: Local variable num defined in an enclosing scope must be final or effectively final.
        
        Inner inner = new Inner();
        inner.display();
    }
}
```

### 118. **Can an Interface Be Defined in a Class?**

Yes, an **interface** can be defined inside a **class** in Java. When an interface is defined inside a class, it is considered a **nested interface**. A nested interface behaves like any other interface but is confined to the context of the class or interface in which it is defined.

#### Example of an Interface Defined Inside a Class:
```java
class OuterClass {
    interface InnerInterface {
        void display();
    }
    
    class InnerClass implements InnerInterface {
        public void display() {
            System.out.println("Inside nested interface implementation");
        }
    }
}

public class Test {
    public static void main(String[] args) {
        OuterClass.InnerClass obj = new OuterClass().new InnerClass();
        obj.display();
    }
}
```
In this example, the `InnerInterface` is defined inside the `OuterClass`. The `InnerClass` implements this nested interface.

### 119. **Do We Have to Explicitly Mark a Nested Interface Public Static?**

No, a nested interface **does not need to be explicitly marked as `public` and `static`**. However, when defining a nested interface inside a class, it is implicitly **static**, as interfaces in Java cannot be instance members of a class. They are always treated as static by default. 

- **Public**: You only need to mark a nested interface as `public` if you want it to be accessible from outside the class or package in which it is defined. If no access modifier is provided, it will have package-private visibility (i.e., accessible only within the same package).
- **Static**: Since an interface is always implicitly static when it is nested, you do not need to explicitly mark it as `static`.

#### Example:
```java
class OuterClass {
    interface InnerInterface {
        void display();
    }
}
```
In this case, `InnerInterface` is implicitly `static`, and there is no need to explicitly declare it as `static`.

### 120. **Why Do We Use Static Nested Interface in Java?**

A **static nested interface** is used for the following reasons:

1. **Logical Grouping**: When you have an interface that is only relevant to the outer class, defining it as a static nested interface makes the code more readable and organized. It keeps the interface close to the class that uses it but doesn't require an instance of the outer class to be accessed.

2. **Decoupling from Outer Class**: Since a static nested interface does not depend on an instance of the outer class, it can be used independently. You can access it without creating an instance of the outer class, which is particularly useful when the interface defines constants or methods that do not depend on the state of the outer class.

3. **Separation of Concerns**: If the interface is logically related to the outer class but does not require any instance-specific behavior, making it static emphasizes that the interface is independent of the instance state and can be used as a utility.

#### Example:
```java
class OuterClass {
    static interface NestedInterface {
        void show();
    }
}

public class Test {
    public static void main(String[] args) {
        // No need for OuterClass instance
        OuterClass.NestedInterface obj = new OuterClass.NestedInterface() {
            public void show() {
                System.out.println("Inside nested static interface");
            }
        };
        obj.show();
    }
}
```
In this case, `NestedInterface` is a static interface, which allows you to instantiate it without needing an instance of the `OuterClass`.

### Summary:

- **Local inner class** can access **effectively final** or **final** local variables from the enclosing method or block.
- **Yes**, an interface can be defined **inside a class** (nested interface).
- A nested interface does not need to be explicitly marked as **`public` or `static`**, but it is always static by default.
- A **static nested interface** is useful because it logically groups the interface with its outer class and does not require an instance of the outer class for access.