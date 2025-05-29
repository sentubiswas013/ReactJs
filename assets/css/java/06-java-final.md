# Top 1000 Java Interview Question & Answers

## Final

### 70. **How Can You Change the Value of a Final Variable in Java?**

In Java, a `final` variable **cannot be reassigned** once it has been initialized, meaning you cannot change its value after it has been assigned. However, there are some exceptions where the value of a `final` variable can appear to change:

1. **For Final Instance Variables**: 
   - If the `final` variable is an object reference, you cannot change the reference to point to another object, but you can modify the state of the object the reference points to (if the object's class allows that).
   
2. **For Final Local Variables**: 
   - Once a local `final` variable is assigned a value, it cannot be reassigned within the method.

3. **For Final Static Variables**:
   - Static `final` variables must be assigned exactly once, and this assignment typically happens during class initialization. You can assign them in a static block, but they cannot be reassigned after that.

**Examples:**

```java
class MyClass {
    final int x = 10;  // This final variable cannot be changed

    final int[] arr = {1, 2, 3}; // You can't reassign arr, but you can modify its elements

    public void changeValue() {
        // x = 20;  // Error: Cannot assign a value to final variable 'x'

        arr[0] = 100;  // Valid: Modifying an element of the final array
    }
}
```

### 71. **Can a Class Be Marked Final in Java?**

Yes, a **class can be marked as `final`** in Java. When a class is marked as `final`, it **cannot be subclassed**. This is useful when you want to ensure that the class’s behavior is not altered through inheritance, providing security or stability.

For example:
```java
final class MyClass {
    void display() {
        System.out.println("This is a final class.");
    }
}

// The following code will result in an error:
// class SubClass extends MyClass { }  // Error: Cannot subclass a final class
```

### 72. **How Can We Create a Final Method in Java?**

A **final method** in Java is a method that **cannot be overridden** by subclasses. It is marked with the `final` keyword to prevent any subclass from providing its own implementation of the method.

To declare a method as `final`:

```java
class MyClass {
    public final void display() {
        System.out.println("This method cannot be overridden.");
    }
}

class SubClass extends MyClass {
    // The following will result in an error:
    // public void display() { }  // Error: Cannot override the final method from MyClass
}
```

In this example, the `display()` method in `MyClass` is marked as `final`, so any subclass attempting to override it will result in a compile-time error.

### 73. **How Can We Prohibit Inheritance in Java?**

To **prohibit inheritance** in Java, you can **mark the class as `final`**. This prevents any subclass from inheriting from that class. When a class is marked as `final`, it cannot be extended.

```java
final class MyClass {
    void display() {
        System.out.println("This class cannot be inherited.");
    }
}

// The following will result in an error:
// class SubClass extends MyClass { }  // Error: Cannot subclass a final class
```

Alternatively, you can prevent inheritance of **specific methods** by marking them as `final`, as discussed above. This would allow you to prevent method overriding but still allow subclassing.

**To summarize**:
- To prohibit inheritance of a class, mark the class as `final`.
- To prevent method overriding, mark the method as `final`.

### 74. **Why is the Integer Class Final in Java?**

The **Integer class** in Java is marked as **final** to prevent subclassing. There are several reasons for this:

1. **Immutable Design**: 
   - The `Integer` class is immutable, meaning its state (the value it holds) cannot be changed after it is created. Allowing subclassing could compromise the immutability of the class, as a subclass could introduce methods that modify the state of the `Integer` object.

2. **Performance**: 
   - The `Integer` class is widely used in Java, and subclassing it might introduce performance overhead or unexpected behaviors that could interfere with its optimized implementation.

3. **Security**: 
   - Marking the `Integer` class as final prevents it from being extended, ensuring its behavior remains consistent and predictable across different uses.

By making it final, Java guarantees that the class’s behavior remains unaltered, improving reliability and reducing potential bugs.

### 75. **What is a Blank Final Variable in Java?**

A **blank final variable** is a `final` variable that is declared but **not initialized** at the time of declaration. It is **required to be initialized later**, either in the constructor or through an initializer block.

A blank final variable can be:
- **Instance variable**: A `final` variable that is not initialized in the constructor but needs to be assigned a value within the constructor.
- **Static final variable**: A static `final` variable that is not initialized at the point of declaration but needs to be initialized in a static block.

**Example:**

```java
class MyClass {
    final int x;  // Blank final instance variable

    MyClass(int value) {
        x = value;  // Blank final variable is initialized in constructor
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass(100);
        System.out.println(obj.x);  // Output: 100
    }
}
```

### 76. **How Can We Initialize a Blank Final Variable?**

A **blank final variable** must be initialized in the constructor or a static block, depending on whether it is an instance or static variable. 

1. **Instance Blank Final Variable**:
   - It must be assigned a value in the constructor of the class before it is accessed.

```java
class MyClass {
    final int x;  // Blank final variable

    MyClass(int value) {
        x = value;  // Initialization of blank final variable
    }
}
```

2. **Static Blank Final Variable**:
   - A static blank final variable must be initialized in a static block, and it can only be assigned once.

```java
class MyClass {
    static final int x;  // Blank final static variable

    static {
        x = 100;  // Initialization of static final variable
    }
}
```

If you fail to initialize a blank final variable, the Java compiler will throw an error.

### 77. **Is it Allowed to Declare the Main Method as Final?**

Yes, it is **allowed to declare the `main` method as `final`** in Java. The `main` method is typically declared as `public static void main(String[] args)` because it is the entry point for any Java application, but you can also mark it as `final` if you wish.

**Example:**

```java
class MyClass {
    public final static void main(String[] args) {
        System.out.println("This is the main method.");
    }
}
```

In this case, declaring the `main` method as `final` would prevent any subclass from overriding it. However, in most cases, overriding the `main` method is not necessary or recommended, so declaring it as `final` simply provides the benefit of ensuring that it cannot be modified in subclasses.

- **Note**: The `main` method is typically **not overridden** in Java applications, as it is a static method designed to start the execution of the program. However, marking it as `final` doesn't have much practical effect other than ensuring no subclass can override it.