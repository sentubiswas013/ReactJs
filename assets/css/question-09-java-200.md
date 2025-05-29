# Java Interview Questions and Answers

## Java Platform
Here are the answers to your questions about Java:

### 1. Why is Java so popular?

Java has remained popular for several reasons:
- **Platform Independence**: Java's "Write Once, Run Anywhere" capability makes it highly portable, thanks to the JVM (Java Virtual Machine), which allows Java applications to run on any system that has a JVM installed.
- **Object-Oriented**: Java is object-oriented, which makes it easier to manage and scale large applications.
- **Rich API**: Java provides a rich set of libraries and APIs, making it easy to build a wide range of applications.
- **Robustness**: Java has built-in error-checking mechanisms (such as exception handling) and strong memory management (automatic garbage collection).
- **Security**: Java provides a secure environment through the use of bytecode verification and a security manager that restricts access to critical system resources.
- **Multithreading**: Java has built-in support for multithreading, allowing for the creation of highly responsive applications.
- **Large Developer Community**: The extensive resources, frameworks, libraries, and tools make Java a go-to language for both new and experienced developers.
- **Widespread Use in Enterprise and Web Applications**: Java is commonly used in large-scale enterprise systems, backend systems, and Android app development.

### 2. What is platform independence?

Platform independence refers to the ability of Java applications to run on any operating system without modification. Java achieves this through the use of the **Java Virtual Machine (JVM)**. When you compile a Java program, it generates **bytecode** (a platform-independent code), which can be executed on any platform that has a corresponding JVM. This ensures that Java applications can be run on Windows, Mac, Linux, or any other system with a JVM.

### 3. What is bytecode?

Bytecode is an intermediate representation of a Java program that is produced after the Java source code is compiled by the Java compiler. Unlike machine code, bytecode is not specific to any hardware and can be executed by the Java Virtual Machine (JVM) on any platform. This allows Java to achieve platform independence because the JVM translates the bytecode into machine-specific code at runtime.

### 4. Compare JDK vs JVM vs JRE

- **JDK (Java Development Kit)**: It is a software development kit used to develop Java applications. The JDK includes:
  - JRE (Java Runtime Environment) 
  - Development tools like the Java compiler (`javac`), debugger, and other utilities.
  - APIs and libraries for building Java programs.

- **JVM (Java Virtual Machine)**: It is a virtual machine that runs Java bytecode. It is responsible for converting the bytecode into machine code that is executed on a specific platform. The JVM provides features such as garbage collection, memory management, and security.

- **JRE (Java Runtime Environment)**: It is a part of the JDK but can also be installed separately. The JRE provides the libraries, JVM, and other components necessary to run Java applications. It does **not** include development tools like compilers and debuggers.

### 5. What are the important differences between C++ and Java?

Here are some key differences between **C++** and **Java**:

| **Aspect**           | **C++**                                | **Java**                           |
|----------------------|----------------------------------------|------------------------------------|
| **Memory Management** | Programmer-managed (manual memory allocation and deallocation) | Automatic (garbage collection)     |
| **Compilation**       | Compiled to machine code               | Compiled to bytecode (intermediate form) |
| **Pointers**          | Supports pointers for direct memory manipulation | Does not support explicit pointers (uses references) |
| **Inheritance**       | Supports multiple inheritance through classes | Supports single inheritance, but allows multiple inheritance through interfaces |
| **Language Paradigm** | Primarily object-oriented, but also procedural | Fully object-oriented (everything is part of a class) |
| **Platform Independence** | Platform-dependent (requires recompilation for different systems) | Platform-independent via the JVM (write once, run anywhere) |
| **Execution**         | Executed directly by the operating system | Executed by the JVM (indirect execution) |
| **Exception Handling**| Provides exception handling, but not as robust as Java | Has a more powerful and structured exception handling system |
| **Libraries**         | Standard libraries are less comprehensive | Extensive and rich set of libraries and APIs |
| **Use Cases**         | System software, game development, embedded systems | Web applications, enterprise applications, mobile (Android) applications |

### 6. What is the role of a classloader in Java?

A **ClassLoader** in Java is responsible for dynamically loading Java classes into the JVM at runtime. The process involves:
1. **Loading**: The ClassLoader reads the bytecode of the class (from a file, network, etc.) and loads it into memory.
2. **Linking**: Once loaded, the class is verified, prepared, and resolved by the ClassLoader. This step ensures that the class can be used correctly within the Java program.
3. **Initialization**: After the class is linked, the JVM initializes static variables and executes the static block (if present).

The ClassLoader helps Java achieve its **platform independence** by allowing the JVM to load classes from different sources, such as the file system, network, or even compressed archives (like JAR files). Additionally, there are different types of ClassLoaders in Java, such as the **Bootstrap ClassLoader**, **Extension ClassLoader**, and **System ClassLoader**, each serving different purposes in the class loading process.

## Wrapper Classes
Here are the answers to your new questions about **Wrapper classes** in Java:

### 7. What are Wrapper classes?

In Java, **Wrapper classes** are classes that provide object representations of the primitive data types. Every primitive type (like `int`, `char`, `double`, etc.) has a corresponding wrapper class that is used to wrap the primitive value into an object. The wrapper classes are part of the `java.lang` package. For example:

- `int` → `Integer`
- `char` → `Character`
- `double` → `Double`
- `boolean` → `Boolean`

These classes allow you to treat primitive data types as objects, which is useful in situations that require objects (such as working with collections like `ArrayList`).

### 8. Why do we need Wrapper classes in Java?

Wrapper classes are needed for several reasons:

- **Object-Oriented Nature of Java**: Java collections (like `ArrayList`, `HashMap`, etc.) only work with objects, not primitives. Wrapper classes allow you to use primitive types in these collections.
  
- **Conversion Between Primitives and Objects**: Wrapper classes provide methods to convert between primitive types and their corresponding object form. For example, the `Integer.parseInt()` method converts a string to an `int` type.

- **Utility Methods**: Each wrapper class provides useful methods for manipulating values (such as converting them to strings, comparing them, etc.), which primitive types do not offer.

- **Nullability**: Wrapper classes allow for null values, unlike primitive types, which cannot be `null`. This is important when dealing with data that may not have been initialized.

### 9. What are the different ways of creating Wrapper class instances?

There are two main ways to create instances of wrapper classes:

1. **Using Constructors** (deprecated in some cases):
   ```java
   Integer integerObj = new Integer(10);
   Double doubleObj = new Double(3.14);
   ```

2. **Using Static Methods (Preferred)**:
   Wrapper classes provide **static methods** like `valueOf()` to create instances. These methods are preferred over constructors, especially for performance reasons (they often use caching).
   ```java
   Integer integerObj = Integer.valueOf(10);
   Double doubleObj = Double.valueOf(3.14);
   ```

The `valueOf()` method is the recommended way because it can cache values, like small integers, to improve performance.

### 10. What are the differences in the two ways of creating Wrapper classes?

The differences between using **constructors** and **static methods** (e.g., `valueOf()`) are as follows:

| **Aspect**                   | **Using Constructors**                      | **Using Static Methods (`valueOf()`)**      |
|------------------------------|---------------------------------------------|--------------------------------------------|
| **Method**                    | Example: `new Integer(10)`                 | Example: `Integer.valueOf(10)`             |
| **Deprecation**               | In some cases (e.g., `Integer` constructor) | Not deprecated, recommended method         |
| **Performance**               | New instance created every time            | May cache values, improving performance (e.g., for small integers) |
| **Memory Efficiency**         | Always creates a new object                | Caching for small values reduces memory usage |
| **Nullability**               | Can be `null`                              | Can be `null` as well                     |
| **Usage**                     | Less common, legacy style                  | Modern and preferred practice             |

In general, **using static methods** (`valueOf()`) is preferred because it is more efficient and supports value caching, which can save memory, especially for frequently used values.

### 11. What is auto boxing?

**Auto-boxing** is a feature introduced in Java 5 that automatically converts between primitive types and their corresponding wrapper class objects. This means that you don't have to manually create a wrapper object when working with collections or other object-oriented frameworks.

For example, when you add a primitive `int` to a collection, Java will automatically box it into an `Integer` object:

```java
List<Integer> list = new ArrayList<>();
list.add(10);  // Auto-boxing: `10` is automatically converted to `Integer.valueOf(10)`
```

Similarly, **unboxing** refers to the automatic conversion from an object to its primitive form:

```java
Integer integerObj = Integer.valueOf(10);
int i = integerObj;  // Unboxing: `integerObj` is automatically converted to primitive `int`
```
Here are the answers to your new questions:

### 12. What are the advantages of auto boxing?

**Auto-boxing** has several advantages, making it easier and more efficient to work with primitive types and their corresponding wrapper classes:

1. **Simplified Code**: Auto-boxing eliminates the need for manually wrapping and unwrapping primitive values, making the code cleaner and more readable. You don't need to explicitly create wrapper class objects for primitive values.
   ```java
   Integer num = 10;  // Auto-boxing: primitive `10` is automatically converted to `Integer`
   ```

2. **Integration with Collections**: Auto-boxing allows primitive types to be used in collections (like `ArrayList`, `HashMap`, etc.) that expect objects. Without auto-boxing, you would need to manually wrap primitives in their corresponding wrapper classes.
   ```java
   List<Integer> list = new ArrayList<>();
   list.add(42);  // Auto-boxing occurs behind the scenes
   ```

3. **Code Efficiency**: Auto-boxing reduces the need for extra lines of code that would otherwise involve manual conversion between primitives and objects. It improves development speed and reduces errors.

4. **Type Safety**: Auto-boxing ensures type consistency by automatically converting between primitive types and wrapper objects, helping to avoid errors that could occur due to manual conversion.

5. **Less Boilerplate Code**: It eliminates the need to write additional code for wrapping/unwrapping (i.e., converting between primitive and object form).

### 13. What is casting?

**Casting** in Java is the process of converting one data type to another. There are two types of casting in Java: **implicit casting** (also called automatic casting) and **explicit casting** (also called manual casting).

Casting is often used when you want to convert data types, for example, converting an integer to a double or vice versa. 

### 14. What is implicit casting?

**Implicit casting**, also known as **automatic casting** or **widening casting**, is when Java automatically converts a smaller data type into a larger one. This type of casting happens **without any explicit action from the programmer**, as long as there is no risk of data loss or overflow. 

For example, converting an `int` to a `long`, or a `float` to a `double`, is safe and automatic because the target type can accommodate the value without loss of information:

```java
int num = 100;
long longNum = num;  // Implicit casting (int to long)
```

In this case, the `int` value is automatically promoted to a `long` without needing an explicit cast because a `long` can hold all values of an `int`.

### 15. What is explicit casting?

**Explicit casting**, also known as **manual casting** or **narrowing casting**, is when the programmer explicitly converts a larger data type into a smaller one. This type of casting is required when the conversion could potentially result in data loss or when the target type cannot automatically hold the value of the source type. Therefore, you must explicitly instruct Java to perform the conversion, often by adding a cast operator.

For example, converting a `double` to an `int` requires explicit casting because the `double` type has a larger range and fractional values that could be lost during conversion:

```java
double num = 9.99;
int intNum = (int) num;  // Explicit casting (double to int)
```

In this case, the value `9.99` will be truncated to `9`, and the fractional part is lost during the conversion.

**Key Differences between Implicit and Explicit Casting**:
- **Implicit casting** happens automatically and involves widening types (e.g., `int` to `long`).
- **Explicit casting** requires the programmer to intervene and is typically used for narrowing conversions (e.g., `double` to `int`), where data loss may occur.

## Strings
Here are the answers to your new set of questions related to **String** and its utilities in Java:

### 16. Are all Strings immutable?

Yes, **all Strings in Java are immutable**. Once a `String` object is created, its value cannot be changed. If you modify a `String`, a new `String` object is created with the updated value, and the original `String` remains unchanged. This immutability ensures that `String` objects are thread-safe and helps to avoid potential issues in multi-threaded environments.

For example:

```java
String str1 = "Hello";
String str2 = str1;
str1 = str1 + " World";  // A new String object is created
System.out.println(str1);  // Outputs: "Hello World"
System.out.println(str2);  // Outputs: "Hello"
```

### 17. Where are String values stored in memory?

- **String literals** are stored in a special area of memory called the **String Pool** (also known as the **String Constant Pool**), which is part of the heap memory. The String Pool is used to optimize memory usage by reusing the same `String` objects rather than creating new ones every time the same value is encountered.
  
  For example:
  ```java
  String str1 = "Hello";
  String str2 = "Hello";  // str1 and str2 refer to the same object in the String pool
  ```
- **New String objects** created using the `new` keyword (e.g., `new String("Hello")`) are stored on the heap outside the String Pool.

### 18. Why should you be careful about the String concatenation (`+`) operator in loops?

Concatenating strings with the `+` operator inside loops can lead to **performance problems** due to the way strings are handled in Java. Since strings are immutable, each concatenation creates a new `String` object, which results in the following inefficiencies:
- **Memory overhead**: Each string concatenation creates a new object, leading to more memory consumption.
- **Time complexity**: Every time you concatenate, the old strings must be copied to the new string, increasing the time required for the operation.

For example, the following code will perform inefficient string concatenation:

```java
String result = "";
for (int i = 0; i < 1000; i++) {
    result = result + i;  // Inefficient: Creates a new string each time
}
```

### 19. How do you solve the above problem?

To solve the performance issue with string concatenation in loops, you should use a **StringBuilder** or **StringBuffer**. These classes are mutable and allow for efficient string manipulation by appending characters without creating new objects each time.

Example using **StringBuilder**:

```java
StringBuilder result = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    result.append(i);  // Efficient concatenation
}
String finalResult = result.toString();
```

### 20. What are differences between String and StringBuffer?

Here are the key differences between **String** and **StringBuffer**:

| **Aspect**              | **String**                                   | **StringBuffer**                             |
|-------------------------|----------------------------------------------|----------------------------------------------|
| **Immutability**         | Immutable (cannot be changed once created)   | Mutable (can be modified after creation)     |
| **Performance**          | Less efficient for modifications (due to immutability) | More efficient for modifications (mutable)   |
| **Thread-Safety**        | Thread-safe (because of immutability)        | Thread-safe (synchronized methods)           |
| **Use Case**             | Preferred when the string is not modified frequently | Preferred when frequent modifications are needed (e.g., in loops) |
| **Memory Consumption**   | More memory is consumed due to frequent creation of new objects | More memory-efficient as it modifies the same object |

### 21. What are differences between StringBuilder and StringBuffer?

The main difference between **StringBuilder** and **StringBuffer** is related to **thread safety**:

| **Aspect**              | **StringBuilder**                          | **StringBuffer**                           |
|-------------------------|--------------------------------------------|--------------------------------------------|
| **Thread-Safety**        | Not thread-safe (not synchronized)         | Thread-safe (synchronized methods)         |
| **Performance**          | Faster than StringBuffer in single-threaded environments | Slower due to synchronization overhead     |
| **Use Case**             | Preferred in single-threaded environments  | Preferred in multi-threaded environments   |

In most cases, **StringBuilder** is recommended for performance in single-threaded scenarios, and **StringBuffer** should be used when thread safety is a concern.

### 22. Can you give examples of different utility methods in the String class?

The **String class** provides several useful methods for working with strings. Some common utility methods are:

1. **length()**: Returns the length of the string.
   ```java
   String str = "Hello";
   int len = str.length();  // Output: 5
   ```

2. **charAt()**: Returns the character at a specified index.
   ```java
   char c = str.charAt(1);  // Output: 'e'
   ```

3. **substring()**: Extracts a substring from a string.
   ```java
   String sub = str.substring(1, 4);  // Output: "ell"
   ```

4. **indexOf()**: Returns the index of the first occurrence of a specified character or substring.
   ```java
   int index = str.indexOf("e");  // Output: 1
   ```

5. **toLowerCase()** and **toUpperCase()**: Converts the string to lower or upper case.
   ```java
   String lower = str.toLowerCase();  // Output: "hello"
   String upper = str.toUpperCase();  // Output: "HELLO"
   ```

6. **equals()**: Compares two strings for equality.
   ```java
   boolean isEqual = str.equals("Hello");  // Output: true
   ```

7. **contains()**: Checks if a substring is present in the string.
   ```java
   boolean contains = str.contains("ll");  // Output: true
   ```

8. **replace()**: Replaces occurrences of a character or substring with another character or substring.
   ```java
   String replaced = str.replace("e", "a");  // Output: "Hallo"
   ```

9. **trim()**: Removes leading and trailing whitespace from the string.
   ```java
   String trimmed = "  Hello  ".trim();  // Output: "Hello"
   ```

10. **split()**: Splits the string into an array of substrings based on a delimiter.
    ```java
    String str = "apple,banana,orange";
    String[] fruits = str.split(",");  // Output: ["apple", "banana", "orange"]

## Object oriented programming basics
Here are the answers to your questions related to **classes**, **objects**, and some fundamental Java concepts:

### 23. What is a class?

A **class** in Java is a blueprint or template used to define objects. It specifies the structure (attributes or fields) and behavior (methods) that the objects created from the class will have. A class defines properties and functionalities that can be shared by all objects of that type.

For example:

```java
public class Car {
    // Attributes (fields)
    String make;
    String model;
    int year;

    // Method (behavior)
    public void startEngine() {
        System.out.println("Engine started.");
    }
}
```

In this example, `Car` is a class, and it defines three fields (`make`, `model`, and `year`) and one method (`startEngine`). Objects of the `Car` class can have these properties and behaviors.

### 24. What is an object?

An **object** in Java is an instance of a class. When a class is defined, no memory is allocated until an object of that class is created. An object contains data (attributes) and methods that operate on the data (behavior). Objects are the actual entities that exist in memory and perform the actions defined by the class.

For example:

```java
Car myCar = new Car();  // Creating an object of the class `Car`
myCar.make = "Toyota";  // Setting the `make` property
myCar.model = "Corolla";  // Setting the `model` property
myCar.startEngine();  // Calling the `startEngine` method
```

Here, `myCar` is an object of the `Car` class, with its own state and behavior.

### 25. What is the state of an object?

The **state** of an object refers to the values of its attributes or fields at any given point in time. It represents the characteristics or data that define the object. The state is stored in the object’s instance variables and can change over time based on method calls and interactions.

For example:

```java
Car myCar = new Car();
myCar.make = "Toyota";  // State of the object (make = Toyota)
myCar.model = "Corolla";  // State of the object (model = Corolla)
myCar.year = 2020;  // State of the object (year = 2020)
```

In this example, the state of `myCar` is determined by its `make`, `model`, and `year`.

### 26. What is behavior of an object?

The **behavior** of an object refers to the actions or methods that the object can perform. It is defined by the methods in the class. Behavior describes how an object responds to messages or method invocations and how it interacts with other objects.

For example:

```java
public class Car {
    String make;
    String model;
    int year;

    // Method defining the behavior
    public void startEngine() {
        System.out.println("The engine of " + make + " " + model + " is started.");
    }
}

Car myCar = new Car();
myCar.make = "Toyota";
myCar.model = "Corolla";
myCar.startEngine();  // This is the behavior (prints: "The engine of Toyota Corolla is started.")
```

In this case, the behavior of the `myCar` object is the ability to call the `startEngine()` method, which outputs a message to the console.

### 27. What is the super class of every class in Java?

The **superclass** of every class in Java, except for the `Object` class, is the **`Object`** class. In Java, all classes, whether explicitly declared or not, implicitly extend the `Object` class. This means that every class inherits the methods of the `Object` class, such as `toString()`, `equals()`, `hashCode()`, etc.

For example:

```java
public class Car {
    // Car class extends Object implicitly
}
```

In this case, `Car` is implicitly a subclass of the `Object` class, which provides fundamental methods that all objects can use.

### 28. Explain about the `toString` method?

The **`toString()`** method is a method defined in the `Object` class, which returns a string representation of the object. By default, the `toString()` method in `Object` returns a string consisting of the class name followed by the `@` character and the object's hash code in hexadecimal format. However, it is common to **override** the `toString()` method in custom classes to provide a more meaningful or readable string representation of the object.

For example:

```java
public class Car {
    String make;
    String model;
    int year;

    // Overriding the toString() method to return a meaningful string
    @Override
    public String toString() {
        return "Car [Make=" + make + ", Model=" + model + ", Year=" + year + "]";
    }
}

Car myCar = new Car();
myCar.make = "Toyota";
myCar.model = "Corolla";
myCar.year = 2020;

System.out.println(myCar);  // Outputs: Car [Make=Toyota, Model=Corolla, Year=2020]
```

In this example, the `toString()` method is overridden in the `Car` class to return a string that represents the car's `make`, `model`, and `year`. When `System.out.println(myCar)` is called, it prints the result of `myCar.toString()`, which is `"Car [Make=Toyota, Model=Corolla, Year=2020]"`.

Here are the answers to your questions related to **Java methods**, **inheritance**, and **object comparison**:

### 29. What is the use of the `equals` method in Java?

The **`equals()`** method in Java is used to compare two objects for **content equality**. By default, the `equals()` method in the `Object` class compares object references (i.e., checks if the two references point to the same object in memory). However, it is often **overridden** in custom classes to compare the actual data or fields of two objects.

For example, in a `Person` class, `equals()` can be overridden to check if two `Person` objects have the same name and age:

```java
public class Person {
    String name;
    int age;

    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Check if both objects are the same instance
        if (obj == null || getClass() != obj.getClass()) return false;  // Check if the object is of the same type
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);  // Compare the fields
    }
}

Person person1 = new Person();
person1.name = "John";
person1.age = 25;

Person person2 = new Person();
person2.name = "John";
person2.age = 25;

System.out.println(person1.equals(person2));  // Output: true
```

In this example, `equals()` is used to compare the contents of the two `Person` objects.

### 30. What are the important things to consider when implementing the `equals` method?

When implementing the `equals()` method in Java, it's important to follow the **contract** of `equals()` as specified in the `Object` class. The main points to consider are:

1. **Reflexive**: An object must be equal to itself.
   ```java
   obj.equals(obj) should return true.
   ```

2. **Symmetric**: If `a.equals(b)` is `true`, then `b.equals(a)` must also return `true`.

3. **Transitive**: If `a.equals(b)` is `true` and `b.equals(c)` is `true`, then `a.equals(c)` must also return `true`.

4. **Consistent**: Multiple invocations of `a.equals(b)` should return the same result, provided that the fields of `a` and `b` do not change.

5. **Null comparison**: `a.equals(null)` should return `false`.

6. **Use of `instanceof` for type comparison**: It's common to use `instanceof` (or `getClass()`) to ensure that the objects being compared are of the same type.

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;  // Check if the objects are the same
    if (obj == null || getClass() != obj.getClass()) return false;
    // Implement other equality checks
}
```

### 31. What is the `hashCode` method used for in Java?

The **`hashCode()`** method in Java is used to return a hash code value for the object. It is used by certain Java data structures (e.g., `HashMap`, `HashSet`) for efficient storage and retrieval of objects. The `hashCode()` method allows objects to be quickly located based on their hash code value. 

- If two objects are considered **equal** according to the `equals()` method, then they must have the **same hash code**.
- **Different objects** that are not equal can have **different hash codes**, but this is not a requirement.

For example:

```java
@Override
public int hashCode() {
    return Objects.hash(name, age);  // Combine fields for hash calculation
}
```

### 32. Explain inheritance with examples.

**Inheritance** in Java is a mechanism where one class (child or subclass) inherits the properties and behaviors (fields and methods) from another class (parent or superclass). It allows code reusability and establishes a relationship between the parent and child classes.

Example:

```java
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {  // Dog inherits from Animal
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();  // Inherited from Animal class
        dog.bark(); // Defined in Dog class
    }
}
```

In this example, the `Dog` class **inherits** the `eat()` method from the `Animal` class, and it also has its own method, `bark()`. The `Dog` object can call both methods.

### 33. What is method overloading?

**Method overloading** in Java is the ability to define multiple methods with the **same name** but different parameter lists (either different number or types of parameters). Overloading is resolved at compile-time (known as compile-time polymorphism).

Key points:
- The return type can be the same or different.
- The parameter list must be different in type, number, or both.

Example:

```java
class Calculator {
    // Method to add two integers
    int add(int a, int b) {
        return a + b;
    }

    // Method to add three integers (method overloading)
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method to add two doubles (method overloading)
    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));          // Calls add(int, int)
        System.out.println(calc.add(2, 3, 4));       // Calls add(int, int, int)
        System.out.println(calc.add(2.5, 3.5));      // Calls add(double, double)
    }
}
```

In this example, the `add()` method is overloaded with different parameter types and numbers.

### 34. What is method overriding?

**Method overriding** occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. Overriding allows the subclass to modify or replace the behavior of the method inherited from the superclass.

Key points:
- The method in the subclass must have the same name, return type, and parameters as in the superclass.
- Overriding is resolved at runtime (known as runtime polymorphism).

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

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.sound();  // Outputs: Animal makes a sound

        Dog myDog = new Dog();
        myDog.sound();  // Outputs: Dog barks

        Animal animal = new Dog();
        animal.sound();  // Outputs: Dog barks (runtime polymorphism)
    }
}
```

Here are the answers to your Java-related questions about **inheritance**, **interfaces**, and other key concepts:

### 35. Can a superclass reference variable hold an object of a subclass?

Yes, a **superclass reference variable can hold an object of a subclass**. This is one of the key features of **inheritance** in Java, where a subclass object can be assigned to a superclass reference. This allows for **polymorphism** because you can use the superclass reference to access methods of the subclass that are overridden or specific to the subclass.

Example:

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

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Superclass reference variable holding subclass object
        myAnimal.sound();  // Outputs: Dog barks
    }
}
```

In this example, the `Animal` reference variable `myAnimal` can hold a `Dog` object. Even though `myAnimal` is of type `Animal`, it will call the overridden `sound()` method from the `Dog` class.

### 36. Is multiple inheritance allowed in Java?

**Multiple inheritance** (inheriting from more than one class) is **not allowed in Java** for classes. Java does not support multiple inheritance through classes due to the **diamond problem**, which can lead to ambiguity in method resolution.

However, Java allows **multiple inheritance through interfaces**, where a class can implement more than one interface.

For example:

```java
interface Animal {
    void sound();
}

interface Mammal {
    void walk();
}

class Dog implements Animal, Mammal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }

    @Override
    public void walk() {
        System.out.println("Dog walks");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();  // Outputs: Dog barks
        dog.walk();   // Outputs: Dog walks
    }
}
```

In this example, the `Dog` class implements two interfaces (`Animal` and `Mammal`), effectively allowing **multiple inheritance** through interfaces.

### 37. What is an interface?

An **interface** in Java is a reference type, similar to a class, but it can only contain **abstract methods** (methods without a body) and **constant variables** (public, static, final). Interfaces define a contract that classes must adhere to if they implement the interface. They allow **multiple inheritance** and provide a way for classes to achieve **polymorphism** and **decoupling**.

- **Abstract methods**: The methods in an interface do not have a body, and classes implementing the interface must provide an implementation for these methods.
- **Constants**: Variables in an interface are implicitly `public`, `static`, and `final`.

Example:

```java
interface Animal {
    void sound();  // Abstract method
}
```

### 38. How do you define an interface?

An **interface** is defined using the `interface` keyword. You can define methods within the interface, but they do not have implementations. Classes that implement the interface must provide concrete implementations for these methods.

Example:

```java
interface Animal {
    void sound();  // Abstract method (no implementation)
    void eat();    // Another abstract method
}
```

Here, `Animal` is an interface with two abstract methods: `sound()` and `eat()`. Any class that implements `Animal` must provide implementations for these methods.

### 39. How do you implement an interface?

To **implement** an interface in Java, a class must use the `implements` keyword and provide concrete implementations for all the abstract methods declared in the interface.

Example:

```java
interface Animal {
    void sound();
    void eat();
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }

    @Override
    public void eat() {
        System.out.println("Dog eats food");
    }
}

class Cat implements Animal {
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }

    @Override
    public void eat() {
        System.out.println("Cat eats food");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.sound();  // Outputs: Dog barks
        dog.eat();    // Outputs: Dog eats food
        
        Animal cat = new Cat();
        cat.sound();  // Outputs: Cat meows
        cat.eat();    // Outputs: Cat eats food
    }
}
```

In this example:
- `Dog` and `Cat` implement the `Animal` interface.
- Both classes provide their own implementations of the `sound()` and `eat()` methods.

Here are the explanations for your Java-related questions about **interfaces**, **abstract classes**, and some nuances in Java:

### 40. Can you explain a few tricky things about interfaces?

Here are a few **tricky aspects** and nuances of **interfaces** in Java:

1. **Default Methods**: Starting from Java 8, interfaces can have **default methods**. These methods have a body and provide a default implementation. This was introduced to allow developers to add new methods to interfaces without breaking the existing implementations.

   Example:

   ```java
   interface Animal {
       void sound();  // Abstract method

       // Default method
       default void sleep() {
           System.out.println("Animal is sleeping");
       }
   }
   ```

   - Any class implementing `Animal` does not need to provide an implementation for the `sleep()` method unless it wants to override the default behavior.

2. **Static Methods**: Interfaces can have **static methods**, but these methods cannot be overridden by implementing classes. They are called on the interface itself.

   Example:

   ```java
   interface Animal {
       static void run() {
           System.out.println("Animal is running");
       }
   }
   
   Animal.run();  // Calling static method from interface
   ```

3. **Multiple Interface Inheritance**: Java supports **multiple inheritance** with interfaces, which means that a class can implement multiple interfaces, but it can only extend one class. If two interfaces have conflicting default methods, the implementing class must provide an override to resolve the conflict.

4. **Cannot Instantiate Interfaces**: Interfaces cannot be instantiated directly. You can only instantiate them indirectly through a class that implements the interface.

5. **Private Methods**: From Java 9 onwards, interfaces can have **private methods**. These methods are used to encapsulate code that is shared by other default or static methods in the interface. Private methods are not accessible to the implementing classes.

### 41. Can you extend an interface?

Yes, **an interface can extend another interface**. When an interface extends another interface, it inherits all the abstract methods from the parent interface. The child interface can add more abstract methods or override the inherited ones (via default methods).

- An interface extends another interface using the `extends` keyword (like class inheritance).

Example:

```java
interface Animal {
    void sound();
}

interface Mammal extends Animal {  // Mammal extends Animal interface
    void walk();
}
```

In this example, `Mammal` extends the `Animal` interface and inherits its `sound()` method, while also adding its own `walk()` method.

### 42. Can a class extend multiple interfaces?

Yes, a **class can implement multiple interfaces**. This is one of the key advantages of interfaces in Java, as it allows for **multiple inheritance** of behavior. However, a class can **extend only one class** (single inheritance).

Example:

```java
interface Animal {
    void sound();
}

interface Mammal {
    void walk();
}

class Dog implements Animal, Mammal {  // A class can implement multiple interfaces
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }

    @Override
    public void walk() {
        System.out.println("Dog walks");
    }
}
```

In this example, the `Dog` class implements both the `Animal` and `Mammal` interfaces, which allows it to inherit behavior from both interfaces.

### 43. What is an abstract class?

An **abstract class** is a class that cannot be instantiated on its own. It is intended to be subclassed by other classes. An abstract class can have both **abstract methods** (methods without a body) and **concrete methods** (methods with a body). The abstract methods must be implemented by the subclasses, but the concrete methods can either be used directly or overridden by the subclasses.

- **Abstract Methods**: Methods that do not have a body in the abstract class and must be implemented by concrete subclasses.
- **Concrete Methods**: Methods that have a body and can be directly used by subclasses.
- **Abstract Classes cannot be instantiated**: You cannot create an object of an abstract class, but it can have constructors, fields, and methods like any regular class.

Example:

```java
abstract class Animal {
    // Abstract method (no implementation)
    abstract void sound();
    
    // Concrete method (has implementation)
    void sleep() {
        System.out.println("Animal is sleeping");
    }
}

class Dog extends Animal {
    // Providing implementation of the abstract method
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

In this example, `Animal` is an abstract class with an abstract method `sound()` and a concrete method `sleep()`. The `Dog` class extends `Animal` and provides its own implementation of `sound()`.

### 44. When do you use an abstract class?

You should use an **abstract class** when:

1. **You have common functionality** that can be shared across all subclasses, but you also want to leave some methods to be implemented by the subclasses. In this case, you can provide a **default implementation** for common functionality and leave specific functionality to the subclass.

   For example, in a system where all animals share common behavior like eating or sleeping but have different ways of making sounds, you might use an abstract class to define common behavior and leave the sound method abstract.

2. **You want to define a common interface but still allow for some flexibility**. For instance, you might define an abstract class with some concrete methods and some abstract methods. This allows you to define behavior that is common to all subclasses while allowing subclasses to implement their own specific behavior.

3. **You want to provide a partial implementation**. In some cases, abstract classes may include some implemented methods, and subclasses only need to implement specific methods. This allows you to define a skeleton structure for future classes.

4. **You want to prevent direct instantiation**: You might want to prevent creating objects of the class directly. For example, an abstract class might represent a general category that should not be instantiated directly (like `Shape`), but concrete subclasses (like `Circle` or `Square`) can be instantiated.

### Summary:
- Use an **abstract class** when you want to provide a common base with both concrete and abstract methods, or when you want to prevent instantiating the base class.
- Use **interfaces** when you want to define a contract for classes that can implement multiple behaviors, especially when you need multiple inheritance of behavior.


Here are the answers to your Java-related questions about **abstract methods**, **constructors**, and **code compilation**:

### 45. How do you define an abstract method?

An **abstract method** is a method that is declared in an **abstract class** or an **interface** but does not have a body (i.e., it doesn't have implementation). The purpose of an abstract method is to force subclasses or implementing classes to provide an implementation for that method.

- **Abstract method syntax**:
  - In an **abstract class**: The method is declared without curly braces `{}` and marked with the `abstract` keyword.
  - In an **interface**: The method is implicitly abstract, and you don't need to use the `abstract` keyword (though you can).

### Example of defining an abstract method:

```java
abstract class Animal {
    // Abstract method (does not have a body)
    abstract void sound();
}

class Dog extends Animal {
    // Providing implementation for the abstract method
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.sound();  // Outputs: Dog barks
    }
}
```

In this example, the method `sound()` is abstract and is implemented in the `Dog` subclass.

### 46. Compare abstract class vs interface?

Here’s a comparison between **abstract classes** and **interfaces** in Java:

| Feature                      | **Abstract Class**                          | **Interface**                                    |
|------------------------------|---------------------------------------------|--------------------------------------------------|
| **Purpose**                   | To provide a base class with some common functionality and allow subclass-specific implementation. | To define a contract that implementing classes must follow. |
| **Method Implementation**     | Can have both abstract and concrete (implemented) methods. | Can have abstract methods (before Java 8) and default or static methods (from Java 8 onward). |
| **Constructor**               | Can have constructors.                      | Cannot have constructors.                        |
| **Multiple Inheritance**      | Supports single inheritance (only extends one class). | Supports multiple inheritance (can implement multiple interfaces). |
| **Fields**                    | Can have instance variables.                | Can only have `public`, `static`, and `final` variables (constants). |
| **Access Modifiers**         | Methods and fields can have access modifiers (`private`, `protected`, `public`). | Methods in an interface are implicitly `public`. |
| **Implementation of Methods** | Abstract methods must be implemented in subclasses, but concrete methods can be used as-is. | All methods must be implemented by classes implementing the interface unless the method is a default method. |
| **Keyword to define**         | `abstract class`                           | `interface`                                      |
| **Default Methods**           | Cannot have default methods (before Java 8). | Can have default methods (since Java 8).         |

### Example:

```java
abstract class Animal {
    abstract void sound();  // Abstract method

    void sleep() {  // Concrete method
        System.out.println("Animal is sleeping");
    }
}

interface Mammal {
    void walk();  // Abstract method
    default void breathe() {  // Default method
        System.out.println("Mammal breathes");
    }
}
```

### 47. What is a constructor?

A **constructor** in Java is a special method used to initialize objects when they are created. A constructor is automatically called when a new instance of a class is created. It has the same name as the class and does not have a return type (not even `void`).

Key points about constructors:
- It initializes the object.
- It cannot have a return type.
- It is invoked when a new object is created using the `new` keyword.

### Example:

```java
class Dog {
    String name;

    // Constructor
    Dog(String name) {
        this.name = name;  // Initialize the 'name' field with the passed argument
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");  // Constructor is called here
        System.out.println(dog.name);  // Outputs: Buddy
    }
}
```

In this example, the constructor `Dog(String name)` is used to initialize the `name` field of the `Dog` object.

### 48. What is a default constructor?

A **default constructor** is a constructor that is automatically provided by Java if no constructors are explicitly defined in a class. It is a no-argument constructor that initializes the object with default values for its fields (e.g., `null` for reference types, `0` for numeric types, and `false` for boolean types).

- If you explicitly define any constructor in your class, Java will **not** provide the default constructor. However, if no constructors are defined, the default constructor is automatically provided by Java.

### Example:

```java
class Dog {
    String name;

    // No constructor defined, so a default constructor is automatically provided
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();  // Default constructor is called here
        System.out.println(dog.name);  // Outputs: null (default value for reference types)
    }
}
```

In this example, since no constructor is defined in `Dog`, the default constructor is provided by Java, and the `name` field is initialized to `null`.

### 49. Will this code compile?

Without the actual code snippet, I cannot determine if it will compile. However, here are common scenarios where code may not compile:

1. **Missing semicolons** (`;`) after statements.
2. **Incorrect method signatures** or **missing return types**.
3. **Wrong class or method access modifiers** (e.g., a method trying to access private members of another class).
4. **Unresolved inheritance issues**, like an abstract class not having all its abstract methods implemented.
5. **Variable or method name conflicts**.

Here are the answers to your Java-related questions regarding **constructors**, **`this()`**, and **`super()`**:

### 50. How do you call a superclass constructor from a constructor?

In Java, you can call a **superclass constructor** from a subclass constructor using the `super()` keyword. This is usually done to initialize the state of the superclass before initializing the state of the subclass. You can either call the default constructor (without arguments) or a parameterized constructor of the superclass by passing the appropriate arguments.

- `super()` calls the default constructor of the superclass.
- `super(arguments)` calls a parameterized constructor of the superclass.

The `super()` call must be the first statement in the subclass constructor.

### Example:

```java
class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }
}

class Dog extends Animal {
    Dog() {
        super();  // Explicitly calling the superclass constructor
        System.out.println("Dog constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();  // Calls the Dog constructor, which calls Animal constructor
    }
}
```

**Output**:
```
Animal constructor
Dog constructor
```

In this example, the `super()` statement is used to call the `Animal` constructor from the `Dog` constructor.

### 51. Will this code compile?

To determine if the code will compile, you need to provide the actual code snippet. Without the code, I cannot directly assess if it will compile, but here are common reasons code may not compile:

1. **Missing semicolons** (`;`) after statements.
2. **Mismatched parentheses** or braces (`{}`).
3. **Access control violations** (trying to access a `private` field or method).
4. **Unresolved references** (e.g., referencing a non-existent class or variable).
5. **Incorrect constructor signatures**.
6. **Abstract methods not implemented** in a subclass.

If you provide the specific code, I can help determine if it will compile or not.

### 52. What is the use of `this()`?

`this()` is a special keyword in Java used to call another constructor in the **same class**. It is a way to **invoke one constructor from another** within the same class. It allows constructor overloading, where multiple constructors exist with different parameters, but you want to reuse the logic from another constructor.

- `this()` must be the first statement in a constructor.
- It is typically used to avoid code duplication when you have multiple constructors with overlapping logic.

### Example:

```java
class Dog {
    String name;
    int age;

    // Constructor with two parameters
    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Constructor with one parameter calling another constructor using 'this()'
    Dog(String name) {
        this(name, 2);  // Calls the constructor with two parameters
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Buddy", 5);  // Calls the two-parameter constructor
        Dog dog2 = new Dog("Max");       // Calls the one-parameter constructor, which calls the two-parameter constructor
        dog1.display();
        dog2.display();
    }
}
```

**Output**:
```
Name: Buddy, Age: 5
Name: Max, Age: 2
```

In this example, the constructor `Dog(String name)` uses `this(name, 2)` to call the other constructor `Dog(String name, int age)`.

### 53. Can a constructor be called directly from a method?

No, a constructor **cannot be called directly from a method**. Constructors are used to create and initialize objects, and they are invoked when you use the `new` keyword. A constructor cannot be called directly in the body of a method like a regular method.

However, you can call a constructor when you create an object using `new`, but you cannot invoke it directly in the body of a method like this:

```java
class Dog {
    String name;

    // Constructor
    Dog(String name) {
        this.name = name;
    }

    void display() {
        // Invalid: You cannot call the constructor like this from a method
        // Dog("Max"); // This will not compile
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy"); // Valid constructor call
        dog.display();
    }
}
```

The correct way is to use `new Dog()` to create an object, not to call the constructor directly within a method.

### 54. Is a superclass constructor called even when there is no explicit call from a subclass constructor?

Yes, **a superclass constructor is always called** when a subclass is instantiated, even if you do not explicitly call it using `super()`. If no explicit call is made, Java will automatically insert a **call to the default constructor** of the superclass (i.e., `super()` without arguments).

- If the superclass does not have a default constructor (i.e., no constructor without parameters), the subclass must explicitly call a parameterized constructor of the superclass using `super(arguments)`.

### Example 1: Implicit Call to Superclass Constructor

```java
class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }
}

class Dog extends Animal {
    Dog() {
        // Implicitly calls super() - default constructor of Animal
        System.out.println("Dog constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();  // Calls Dog constructor, which calls Animal constructor
    }
}
```

**Output**:
```
Animal constructor
Dog constructor
```

In this example, even though we didn't explicitly call `super()`, Java automatically calls `super()` (the default constructor of `Animal`).

### Example 2: Explicit Call Required When No Default Constructor

```java
class Animal {
    Animal(String name) {
        System.out.println("Animal name: " + name);
    }
}

class Dog extends Animal {
    Dog() {
        // Explicitly calling the superclass constructor with a parameter
        super("Buddy");
        System.out.println("Dog constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();  // Calls Dog constructor, which calls Animal constructor
    }
}
```

**Output**:
```
Animal name: Buddy
Dog constructor
```

In this case, since `Animal` doesn't have a default constructor, we need to explicitly call the `super("Buddy")` constructor from `Dog`.

### Summary:
- The **superclass constructor** is always called when a subclass is instantiated.
- If you don’t explicitly call the superclass constructor using `super()`, Java automatically calls the **default constructor** of the superclass.
- If the superclass does not have a default constructor, you must explicitly call a parameterized constructor from the subclass constructor.

## Advanced object oriented concepts
Here are the answers to your Java-related questions on **polymorphism**, **instanceof operator**, **coupling**, and **cohesion**:

### 55. What is polymorphism?

**Polymorphism** is one of the four fundamental principles of Object-Oriented Programming (OOP) and refers to the ability of a single entity (method or object) to take on multiple forms. In Java, polymorphism allows you to use a single interface to represent different underlying forms (classes). It enhances flexibility and maintainability in the code.

There are two types of polymorphism in Java:

1. **Compile-time polymorphism (Method Overloading)**:
   This type of polymorphism occurs when multiple methods have the same name but differ in the number or types of parameters. The appropriate method is determined at compile time.
   
   - **Example**:
     ```java
     class Calculator {
         // Overloaded methods
         int add(int a, int b) {
             return a + b;
         }

         double add(double a, double b) {
             return a + b;
         }
     }

     public class Main {
         public static void main(String[] args) {
             Calculator calc = new Calculator();
             System.out.println(calc.add(5, 3));        // Calls int version
             System.out.println(calc.add(5.5, 3.2));    // Calls double version
         }
     }
     ```

2. **Runtime polymorphism (Method Overriding)**:
   This occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. The appropriate method is determined at runtime based on the object type (not the reference type).
   
   - **Example**:
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

     public class Main {
         public static void main(String[] args) {
             Animal myAnimal = new Animal();
             Animal myDog = new Dog();
             
             myAnimal.sound();  // Outputs: Animal makes a sound
             myDog.sound();     // Outputs: Dog barks (determined at runtime)
         }
     }
     ```

### 56. What is the use of `instanceof` operator in Java?

The **`instanceof`** operator in Java is used to test whether an object is an instance of a specific class or subclass, or whether it implements an interface. It is used to check the type of an object during runtime, preventing **ClassCastException** when performing type casting.

- **Syntax**: 
  ```java
  object instanceof ClassName
  ```
  This returns `true` if the object is an instance of the class or its subclass; otherwise, it returns `false`.

- **Example**:
  ```java
  class Animal {}
  class Dog extends Animal {}

  public class Main {
      public static void main(String[] args) {
          Animal animal = new Dog();
          
          // Check if 'animal' is an instance of Dog
          System.out.println(animal instanceof Dog);  // Outputs: true
          
          // Check if 'animal' is an instance of Animal
          System.out.println(animal instanceof Animal);  // Outputs: true
      }
  }
  ```

In this example, `animal instanceof Dog` returns `true` because `animal` is actually an instance of `Dog`. Similarly, `animal instanceof Animal` is `true` because `Dog` is a subclass of `Animal`.

### 57. What is coupling?

**Coupling** refers to the degree of dependence between different components or classes in a software system. In object-oriented programming, coupling defines how closely related or connected the classes or modules are. The more dependent one class is on another, the higher the coupling.

There are two types of coupling:

1. **Loose Coupling**:
   In loose coupling, components or classes have minimal dependencies on each other. Loose coupling is desirable because it makes the system more flexible, maintainable, and scalable.
   
   - **Example**: Using interfaces to decouple classes.
   
2. **Tight Coupling**:
   In tight coupling, components or classes have strong dependencies, meaning that a change in one class may affect the others. Tight coupling is generally undesirable because it makes the system harder to maintain and scale.

- **Example of Loose Coupling**:
  ```java
  interface Animal {
      void sound();
  }

  class Dog implements Animal {
      public void sound() {
          System.out.println("Bark");
      }
  }

  class Main {
      public static void main(String[] args) {
          Animal dog = new Dog();  // Loose coupling (Dog class is decoupled from Main)
          dog.sound();
      }
  }
  ```

In this example, the `Main` class depends on the `Animal` interface, not the concrete `Dog` class. This makes the system loosely coupled and easy to extend.

### 58. What is cohesion?

**Cohesion** refers to the degree to which the elements of a class or module are related to one another. It measures how closely related and focused the responsibilities of a class or module are. Higher cohesion means that the class or module has a well-defined purpose, making it easier to maintain and understand.

- **High Cohesion**:
  A class with high cohesion has methods and fields that are closely related to the core purpose of the class. Such classes are easier to maintain, reuse, and extend.
  
  - **Example**:
    ```java
    class Book {
        private String title;
        private String author;
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return this.title;
        }

        public String getAuthor() {
            return this.author;
        }
    }
    ```

  The `Book` class is cohesive because all its methods and fields are directly related to the book's characteristics (title and author).

- **Low Cohesion**:
  A class with low cohesion has methods and fields that are unrelated, which leads to poor maintainability and difficulty in understanding the class's purpose.
  
  - **Example**:
    ```java
    class Employee {
        private String name;
        private double salary;

        // Method related to employee information
        public void setName(String name) {
            this.name = name;
        }

        // Method unrelated to employee info, e.g., performing some external service
        public void printSalarySlip() {
            // Unrelated to the employee class purpose
            System.out.println("Salary Slip");
        }
    }
    ```

In this example, `printSalarySlip()` has low cohesion with respect to the `Employee` class since it does not logically belong in the class focused on employee attributes.

### Summary:
- **Polymorphism**: Ability to use a single entity to represent different types; it includes method overloading (compile-time) and method overriding (runtime).
- **`instanceof`**: Used to check the type of an object at runtime.
- **Coupling**: Measures the dependency between classes; loose coupling is preferred over tight coupling.
- **Cohesion**: Measures how closely related the responsibilities of a class are; higher cohesion is better for maintainability.

Here are the answers to your Java-related questions on **encapsulation**, **inner classes**, **static inner classes**, and **anonymous classes**:

### 59. What is encapsulation?

**Encapsulation** is one of the fundamental principles of Object-Oriented Programming (OOP) and refers to the concept of **hiding** the internal state and behavior of an object and only exposing a controlled interface to the outside world. In Java, encapsulation is achieved by:

1. **Private fields**: Keeping class variables (fields) private so they cannot be accessed directly outside the class.
2. **Public getter and setter methods**: Providing public methods (getters and setters) to access and modify the values of private fields.

Encapsulation helps to protect an object’s state from unintended changes and allows you to change the internal implementation of a class without affecting the external code that uses the class.

- **Example**:
  ```java
  class Employee {
      private String name;  // private field
      private double salary;

      // Public getter for 'name'
      public String getName() {
          return name;
      }

      // Public setter for 'name'
      public void setName(String name) {
          this.name = name;
      }

      // Public getter for 'salary'
      public double getSalary() {
          return salary;
      }

      // Public setter for 'salary'
      public void setSalary(double salary) {
          if (salary > 0) {  // Validation before setting
              this.salary = salary;
          }
      }
  }

  public class Main {
      public static void main(String[] args) {
          Employee emp = new Employee();
          emp.setName("John Doe");
          emp.setSalary(5000);
          
          System.out.println("Employee Name: " + emp.getName());
          System.out.println("Employee Salary: " + emp.getSalary());
      }
  }
  ```

In this example, the `name` and `salary` fields are encapsulated within the `Employee` class, and their values can only be modified or accessed via getter and setter methods.

### 60. What is an inner class?

An **inner class** is a class that is defined **within** another class. In Java, inner classes can access the members (fields and methods) of their enclosing class, even if they are private. Inner classes are useful when you want to logically group classes that are only used in one place, and they can also provide access to the enclosing class’s members.

There are several types of inner classes in Java:

- **Non-static inner class (Member inner class)**: This is a class defined inside another class without the `static` modifier.

- **Example**:
  ```java
  class Outer {
      private String outerField = "Outer class field";

      // Non-static inner class
      class Inner {
          void display() {
              System.out.println(outerField);  // Accessing outer class field
          }
      }
  }

  public class Main {
      public static void main(String[] args) {
          Outer outer = new Outer();
          Outer.Inner inner = outer.new Inner();  // Creating an inner class object
          inner.display();  // Accesses outer class field
      }
  }
  ```

In this example, the `Inner` class is a **non-static inner class** that has access to the `outerField` of the `Outer` class.

### 61. What is a static inner class?

A **static inner class** is an inner class that is defined with the `static` modifier. Unlike a non-static inner class, a static inner class does not have access to the instance members (fields and methods) of the outer class. However, it can access static members of the outer class. A static inner class is often used when the inner class does not require access to the instance data of the enclosing class.

- **Example**:
  ```java
  class Outer {
      static String outerStaticField = "Outer static field";

      // Static inner class
      static class Inner {
          void display() {
              System.out.println(outerStaticField);  // Accessing outer class static field
          }
      }
  }

  public class Main {
      public static void main(String[] args) {
          Outer.Inner inner = new Outer.Inner();  // Creating static inner class object
          inner.display();  // Accesses outer class static field
      }
  }
  ```

In this example, the `Inner` class is a **static inner class** that can access the static field `outerStaticField` from the `Outer` class. You do not need an instance of `Outer` to create an instance of `Inner`.

### 62. Can you create an inner class inside a method?

Yes, you can create an **inner class inside a method**. These are called **local inner classes**. Local inner classes are defined within methods, and they can access final or effectively final local variables of the method. They cannot have access to the non-final local variables of the enclosing method.

- **Example**:
  ```java
  class Outer {
      void outerMethod() {
          final String localVar = "Local variable";  // Local variable must be final or effectively final

          // Local inner class inside a method
          class Inner {
              void display() {
                  System.out.println(localVar);  // Accessing local variable of the method
              }
          }

          Inner inner = new Inner();  // Creating an instance of the inner class
          inner.display();
      }
  }

  public class Main {
      public static void main(String[] args) {
          Outer outer = new Outer();
          outer.outerMethod();  // Calling outer method, which creates an inner class inside
      }
  }
  ```

In this example, the `Inner` class is defined inside the `outerMethod` of the `Outer` class and can access the `localVar` because it is `final`.

### 63. What is an anonymous class?

An **anonymous class** is a class without a name that is defined and instantiated in a single statement. Anonymous classes enable you to make your code more concise when you need to implement a class just once, usually for a simple implementation of an interface or an abstract class.

Anonymous classes are commonly used when implementing interfaces or extending classes without the need for creating a separate named class.

- **Syntax**:
  ```java
  ClassName obj = new ClassName() {
      // Implement methods or override methods
  };
  ```

- **Example** (using an anonymous class to implement an interface):
  ```java
  interface Animal {
      void sound();
  }

  public class Main {
      public static void main(String[] args) {
          // Anonymous class implementing Animal interface
          Animal myAnimal = new Animal() {
              public void sound() {
                  System.out.println("Roar");
              }
          };

          myAnimal.sound();  // Outputs: Roar
      }
  }
  ```

In this example, an anonymous class implements the `Animal` interface and provides an implementation for the `sound()` method in the same statement.

### Summary:
- **Encapsulation**: Hiding the internal state and providing controlled access to it via getter and setter methods.
- **Inner class**: A class defined within another class that can access the enclosing class's members.
- **Static inner class**: A class defined inside another class with the `static` keyword, which does not have access to instance members of the outer class.
- **Inner class inside a method**: A local inner class defined inside a method, which can access final or effectively final local variables.
- **Anonymous class**: A class defined and instantiated in a single statement, commonly used for one-time implementations of interfaces or abstract classes.

## Modifiers
Here are the answers to your Java questions related to **access modifiers**:

### 64. What is the default class modifier?

In Java, **the default class modifier** refers to a situation where no explicit access modifier (like `public`, `private`, `protected`) is specified for a class. When a class is declared without any access modifier, it is given **default (package-private)** access. This means the class is accessible only within the same package, and not from classes in other packages.

- **Example**:
  ```java
  class MyClass {  // Default access modifier (no modifier)
      void display() {
          System.out.println("Default access");
      }
  }
  ```

In the example, `MyClass` has no access modifier, so it has **package-private** access, meaning it can be accessed only by classes within the same package.

### 65. What is the private access modifier?

The **`private`** access modifier is the most restrictive access level in Java. It means that the member (variable or method) is accessible **only within the same class**. It cannot be accessed from outside the class, not even by subclasses or classes in the same package.

- **Example**:
  ```java
  class MyClass {
      private int secretValue;  // Private member

      private void display() {
          System.out.println("This is private");
      }

      public void show() {
          display();  // Can call private method within the same class
      }
  }

  public class Main {
      public static void main(String[] args) {
          MyClass obj = new MyClass();
          obj.secretValue = 10;  // Error: 'secretValue' has private access in MyClass
          obj.display();  // Error: 'display()' has private access in MyClass
      }
  }
  ```

In this example, `secretValue` and `display()` are **private**, meaning they cannot be accessed from outside `MyClass`.

### 66. What is the default or package access modifier?

The **default (package-private)** access modifier is used when **no access modifier** is specified for a class, method, or variable. Members with this access level are accessible only within classes in the **same package**. They are not accessible from classes in other packages, not even by subclasses.

- **Example**:
  ```java
  class MyClass {  // Default (package-private) access
      void display() {
          System.out.println("Default access");
      }
  }

  public class Main {
      public static void main(String[] args) {
          MyClass obj = new MyClass();
          obj.display();  // Accessible within the same package
      }
  }
  ```

In this example, since `display()` has **default access** (no modifier), it is accessible to other classes in the same package but not outside.

### 67. What is the protected access modifier?

The **`protected`** access modifier allows members to be accessible within the **same package** and by **subclasses** (even if they are in different packages). However, they are not accessible by other classes that are not subclasses, even if they are in the same package.

- **Example**:
  ```java
  class Animal {
      protected void sound() {
          System.out.println("Animal makes a sound");
      }
  }

  class Dog extends Animal {
      void sound() {
          System.out.println("Dog barks");
      }
  }

  public class Main {
      public static void main(String[] args) {
          Dog dog = new Dog();
          dog.sound();  // Outputs: Dog barks
      }
  }
  ```

In this example, `sound()` in `Animal` is marked as `protected`, so it is accessible in the `Dog` class, which extends `Animal`.

### 68. What is the public access modifier?

The **`public`** access modifier is the least restrictive access level in Java. A member (class, method, variable) declared as `public` is accessible from **anywhere**: from any class, whether it is in the same package or a different package.

- **Example**:
  ```java
  public class MyClass {
      public int value;  // Public variable

      public void display() {  // Public method
          System.out.println("This is public");
      }
  }

  public class Main {
      public static void main(String[] args) {
          MyClass obj = new MyClass();
          obj.value = 100;  // Accessible from outside
          obj.display();  // Accessible from outside
      }
  }
  ```

In this example, both the `value` variable and the `display()` method in `MyClass` are declared as `public`, meaning they are accessible from any class.

---

### Summary of Access Modifiers:
- **Default (Package-Private)**: No modifier. The class, method, or variable is accessible only within the same package.
- **Private**: The member is accessible only within the same class.
- **Protected**: The member is accessible within the same package and by subclasses (even in different packages).
- **Public**: The member is accessible from any class, whether in the same package or a different package.

Here are the answers to your Java questions on **access types** of variables and the use of the `final` modifier:

### 69. What access types of variables can be accessed from a class in the same package?

In **the same package**, the following access modifiers allow access to variables:

- **Public**: A variable marked as `public` is accessible from any class, including those in the same package.
- **Default (Package-Private)**: A variable with no modifier (i.e., default access) is accessible within all classes in the same package.
- **Protected**: A `protected` variable is accessible within the same package and in subclasses, even if they are in different packages.
- **Private**: A `private` variable is **not accessible** outside the class it is declared in, even within the same package.

- **Summary**: In the same package, **`public`, default (package-private), and `protected`** variables are accessible. **`private`** variables are **not accessible**.

### 70. What access types of variables can be accessed from a class in a different package?

In **a different package**, the following access modifiers determine the accessibility of variables:

- **Public**: A `public` variable is accessible from any class, including those in different packages.
- **Default (Package-Private)**: A variable with **no modifier** (default access) is **not accessible** outside its package.
- **Protected**: A `protected` variable is **not accessible** from classes in different packages unless the class is a subclass.
- **Private**: A `private` variable is **not accessible** from any class outside the class where it is declared.

- **Summary**: In a different package, **only `public`** variables are accessible. **`protected`** variables are accessible only by subclasses in different packages. **`default` (package-private)** and **`private`** variables are **not accessible**.

### 71. What access types of variables can be accessed from a subclass in the same package?

In **the same package**, a subclass has access to the following types of variables in its superclass:

- **Public**: A `public` variable is accessible to the subclass.
- **Protected**: A `protected` variable is accessible to the subclass in the same package.
- **Default (Package-Private)**: A variable with no modifier (default access) is accessible to the subclass, as they are in the same package.
- **Private**: A `private` variable is **not accessible** to the subclass.

- **Summary**: In the same package, a subclass can access **`public`, `protected`, and default (package-private)** variables from the superclass, but **not `private`** variables.

### 72. What access types of variables can be accessed from a subclass in a different package?

In **a different package**, a subclass has access to the following types of variables in its superclass:

- **Public**: A `public` variable is accessible to the subclass.
- **Protected**: A `protected` variable is accessible only to the subclass, but only because the subclass is inheriting from the superclass.
- **Default (Package-Private)**: A variable with **no modifier** is **not accessible** in a subclass in a different package.
- **Private**: A `private` variable is **not accessible** to the subclass.

- **Summary**: In a different package, a subclass can access **`public`** and **`protected`** variables. **`default` (package-private)** and **`private`** variables are **not accessible**.

### 73. What is the use of a final modifier on a class?

The **`final`** modifier can be applied to a class, method, or variable. When applied to a **class**, the `final` modifier has the following significance:

- **Prevents Inheritance**: A `final` class **cannot be subclassed**. This is useful when you want to create a class that cannot be extended or modified further. For example, you might use a `final` class for security reasons or to ensure that the class's behavior remains consistent.
  
- **Example**:
  ```java
  final class MyClass {
      void display() {
          System.out.println("This is a final class");
      }
  }

  // This will result in a compile-time error:
  // class AnotherClass extends MyClass { } // Error: cannot inherit from final class MyClass
  ```

In this example, the `MyClass` is marked as `final`, meaning that no other class can extend it.

---

### Summary:
- **Same Package**: 
  - A class in the same package can access `public`, default (package-private), and `protected` variables, but **not private** variables.
- **Different Package**: 
  - A class in a different package can only access `public` variables. `protected` variables are accessible only in subclasses, and **`default` and `private` variables are not accessible**.
- **Subclass in Same Package**: 
  - A subclass in the same package can access `public`, `protected`, and default (package-private) variables but **not private** variables.
- **Subclass in Different Package**: 
  - A subclass in a different package can access `public` and `protected` variables, but **not default (package-private)** and **private** variables.
- **`final` Class Modifier**: 
  - A `final` class cannot be subclassed. This ensures that the class cannot be extended or modified.

Here are the answers to your Java questions on the **`final` modifier**, **`volatile`**, and **`static` variable**:

### 74. What is the use of a final modifier on a method?

When the **`final`** modifier is applied to a method, it **prevents the method from being overridden** in any subclass. This ensures that the behavior of the method remains consistent and cannot be changed in derived classes.

- **Use cases**:
  - You want to make sure that the method's implementation cannot be modified in subclasses, providing a guarantee of behavior.
  - For example, methods in a class that perform critical functionality and need to remain unchanged can be marked as `final`.

- **Example**:
  ```java
  class Parent {
      final void show() {
          System.out.println("This method cannot be overridden");
      }
  }

  class Child extends Parent {
      // The following will result in a compile-time error
      // void show() {
      //     System.out.println("Trying to override");
      // }
  }
  ```

In this example, the `show()` method in the `Parent` class is `final`, meaning it cannot be overridden by the `Child` class.

### 75. What is a final variable?

A **`final`** variable in Java is a variable whose value cannot be changed once it is assigned. The value is **constant** after initialization, and attempting to modify it will result in a compile-time error.

- **For instance variables**, it must be initialized either during declaration or in the constructor.
- **For local variables**, it must be initialized before being used.

- **Example**:
  ```java
  class MyClass {
      final int MAX_VALUE = 100;

      void changeValue() {
          // MAX_VALUE = 200;  // Compile-time error: cannot assign a value to a final variable
      }
  }
  ```

In this example, the `MAX_VALUE` variable is marked as `final`, so its value cannot be changed after it is initially set.

### 76. What is a final argument?

A **`final`** argument is a method parameter that cannot be modified within the method body. It ensures that the argument passed to the method is not changed during the execution of the method.

- **Use case**: It is often used when you want to guarantee that the original value passed into the method remains unchanged.

- **Example**:
  ```java
  class MyClass {
      void display(final int value) {
          // value = 200; // Compile-time error: cannot assign a value to final parameter
          System.out.println(value);
      }
  }
  ```

In this example, the parameter `value` is marked as `final`, meaning it cannot be modified inside the `display()` method.

### 77. What happens when a variable is marked as volatile?

A **`volatile`** variable in Java ensures that the value of the variable is **always read from and written to the main memory**, rather than being cached locally in a thread's memory. This is useful in multithreaded programming where multiple threads might access and modify the same variable concurrently.

- **Key points**:
  - The `volatile` keyword tells the Java Virtual Machine (JVM) not to cache the variable locally, ensuring that every read and write operation is done directly from the main memory.
  - It is used to make sure that changes made by one thread to a variable are visible to other threads.

- **Example**:
  ```java
  class SharedResource {
      private volatile boolean flag = false;

      public void toggleFlag() {
          flag = !flag;  // Every thread will see the updated value of flag
      }

      public boolean getFlag() {
          return flag;
      }
  }
  ```

In this example, the `flag` variable is marked as `volatile`, ensuring that all threads accessing the `flag` see the most up-to-date value.

### 78. What is a static variable?

A **`static`** variable is a class-level variable that is shared among all instances of the class. Instead of each instance having its own copy of the variable, all instances refer to the same variable. A `static` variable is initialized only once, when the class is loaded into memory.

- **Key characteristics**:
  - It is common to all instances of the class.
  - It is initialized when the class is loaded, and it exists until the program terminates.
  - A static variable can be accessed directly using the class name or through instances of the class.

- **Example**:
  ```java
  class MyClass {
      static int counter = 0;

      MyClass() {
          counter++;  // Every new instance of MyClass will increment counter
      }

      void display() {
          System.out.println("Counter: " + counter);
      }
  }

  public class Main {
      public static void main(String[] args) {
          MyClass obj1 = new MyClass();
          MyClass obj2 = new MyClass();
          obj1.display();  // Output: Counter: 2
          obj2.display();  // Output: Counter: 2
      }
  }
  ```

In this example, the `counter` variable is `static`, meaning it is shared by all instances of `MyClass`. Both `obj1` and `obj2` will refer to the same `counter` variable.

---

### Summary:
- **`final` Method**: Prevents method from being overridden by subclasses.
- **`final` Variable**: The value of the variable cannot be changed once it is initialized.
- **`final` Argument**: The parameter passed to a method cannot be modified inside the method.
- **`volatile` Variable**: Ensures that the variable’s value is always read from and written to main memory, making it visible across multiple threads.
- **`static` Variable**: A variable that is shared among all instances of the class, and only one copy of it exists, irrespective of the number of instances created.

## conditions & loops
Here are the answers to your Java-related questions:

### 79. Why should you always use blocks around an `if` statement?

It is recommended to always use **blocks** (curly braces `{}`) around an `if` statement, even when it's followed by only a single statement, for the following reasons:

1. **Improves readability**: Using braces makes the code more consistent and readable. It's easier to understand the scope of the condition when curly braces are present.
   
2. **Prevents errors**: If you later add additional statements to the `if` block and forget to add the braces, it may lead to logic errors that are difficult to spot. Using braces ensures that the block is always treated as a group of statements.

3. **Prevents ambiguity**: Without braces, the `if` statement is followed by only the next single statement, which can sometimes lead to misunderstandings or unintentional errors when modifying code.

- **Example**:
  ```java
  if (condition) {
      // Block of code
      statement1;
      statement2;
  }
  ```

Without the braces, only the first statement after the `if` is considered part of the block, which could cause issues if someone later adds another statement but forgets to include braces.

### 80. Guess the output

This is a generic question and doesn't provide a specific code snippet. Please provide the code you're referring to, and I can help you guess the output.

### 81. Guess the output

Similarly to question 80, this question doesn't provide any code snippet. Please share the code, and I'll be happy to predict the output for you.

### 82. Guess the output of this switch block.

Without the code, I can't specifically predict the output. However, if you're asking about a **switch statement**, here is how it generally works:

- A **switch** statement compares a variable against different possible `case` values. When a match is found, the code inside that case executes, and it can "fall through" to subsequent cases unless a `break` is used.
  
- **Example**:
  ```java
  int x = 2;
  switch (x) {
      case 1:
          System.out.println("Case 1");
          break;
      case 2:
          System.out.println("Case 2");
          break;
      case 3:
          System.out.println("Case 3");
          break;
      default:
          System.out.println("Default case");
  }
  ```

In this case, since `x` equals `2`, the output will be:
```
Case 2
  ```

Please provide the specific switch block code, and I will provide a more accurate answer.

### 83. Guess the output of this switch block?

Again, I would need the specific code snippet you're asking about to predict the output. Please provide the code, and I will guide you through it.

### 84. Should `default` be the last case in a switch statement?

The **`default`** case in a **switch statement** is **not required** to be at the end, but it is generally placed at the end for **readability** and **convention**. The **`default`** case is executed when no `case` matches the value of the switch variable.

- **Position of `default`**: 
  - You can place the `default` case anywhere in the `switch` block. However, the conventional position is at the **end**, as it signifies that it catches all unmatched values.

- **Example**:
  ```java
  switch (day) {
      case 1:
          System.out.println("Monday");
          break;
      case 2:
          System.out.println("Tuesday");
          break;
      default:
          System.out.println("Invalid day");
          break;
  }
  ```

While it's not strictly necessary to place `default` last, it's a widely accepted practice for clarity and maintainability. Having it at the end allows anyone reading the code to see quickly that it's the fallback option for unmatched cases.

---

### Summary:
- Always use curly braces `{}` around an `if` statement to improve readability and prevent potential errors when adding more statements in the future.
- For the questions regarding guessing the output of switch blocks or code, please provide the code snippets so that I can help predict the output accurately.
- The `default` case in a `switch` statement is conventionally placed last for clarity, but it's not a requirement in Java.

Let's go through the questions one by one.

### 85. Can a switch statement be used around a String?

Yes, a **switch statement** **can** be used with a **String** in Java. This feature was introduced in **Java 7**. The `switch` statement can now compare a String variable against multiple possible string values.

- **Example**:
  ```java
  String day = "Monday";

  switch (day) {
      case "Monday":
          System.out.println("Start of the week");
          break;
      case "Friday":
          System.out.println("End of the week");
          break;
      default:
          System.out.println("Midweek day");
  }
  ```

In this example, the output will be:
```
Start of the week
```

The `switch` will compare the `day` variable with each case string and execute the corresponding block.

### 86. Guess the output of this for loop (P.S. there is an error as the output of given question should be 0-1-2-3-4-5-6-7-8-9. So please ignore that.)

Since the question states that there's an error but provides the desired output (0-1-2-3-4-5-6-7-8-9), I'll provide a corrected version of the loop that would produce this output.

- **Corrected `for` loop**:
  ```java
  public class Main {
      public static void main(String[] args) {
          for (int i = 0; i < 10; i++) {
              System.out.print(i + "-");
          }
      }
  }
  ```

**Expected output**:
```
0-1-2-3-4-5-6-7-8-9-
```

Note that the output has a trailing `-`, but if you don't want that, you can modify the loop slightly to avoid it.

### 87. What is an enhanced for loop?

The **enhanced `for` loop** (also known as the **for-each loop**) is a simplified version of the `for` loop used to iterate over arrays or collections without needing an index.

- **Syntax**:
  ```java
  for (datatype element : array_or_collection) {
      // code using element
  }
  ```

- **Example**:
  ```java
  int[] numbers = {1, 2, 3, 4, 5};
  
  for (int number : numbers) {
      System.out.println(number);
  }
  ```

In this example, the enhanced `for` loop iterates over each element in the `numbers` array and prints it.

**Output**:
```
1
2
3
4
5
```

### 88. What is the output of the for loop below?

This question lacks the code snippet, but I'll show an example of a typical `for` loop and explain its output.

**Example**:
```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

**Expected Output**:
```
0
1
2
3
4
```

This loop prints the numbers from `0` to `4` because it starts with `i = 0` and increments `i` by 1 each time, terminating when `i < 5` is no longer true.

If you provide the exact code, I can give a more accurate answer.

### 89. What is the output of the program below?

Since no specific code is provided, I'll provide a general example of output.

**Example Program**:
```java
public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        System.out.println(a + b);
    }
}
```

**Expected Output**:
```
15
```

This program adds the two variables `a` and `b`, then prints the result, which is `15`. If you provide the code, I can give the precise output.

### 90. What is the output of the program below?

As with the previous question, I'll provide a generic example.

**Example Program**:
```java
public class Main {
    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println(str.substring(7, 12));
    }
}
```

**Expected Output**:
```
World
```

In this example, the program calls `substring(7, 12)` on the string `str`, extracting characters from index 7 to 11 (inclusive), which results in the string `"World"`.

---

### Summary:
- **Switch with String**: Yes, Java allows you to use strings in `switch` statements starting from Java 7.
- **For Loop Output (0-1-2-3-4-5-6-7-8-9)**: The output is as expected when using a simple `for` loop iterating from 0 to 9.
- **Enhanced For Loop**: A loop that simplifies iteration over arrays or collections without needing indices.
- **Loop Output**: The output of a `for` loop depends on the loop's condition and how it's written (typically printing values iterated over).
- **Output of Code**: Provide the specific code snippets, and I can give the exact output you're looking for.

## Exception handling
Here are the answers to your questions regarding **exception handling** in Java:

### 91. Why is exception handling important?

Exception handling is important for the following reasons:

1. **Graceful Error Management**: It allows developers to handle errors gracefully without crashing the program. Instead of letting the program terminate unexpectedly, exceptions enable you to catch and manage errors.
   
2. **Improved Readability and Maintainability**: By separating error handling code from the regular business logic, it improves the structure and readability of the code. This makes it easier to maintain and debug the program.
   
3. **Resource Management**: Exception handling is often used to manage resources (like file handles, database connections, etc.). It ensures that resources are properly released even when an error occurs, which is critical to avoid resource leaks.
   
4. **Security**: Proper exception handling can prevent malicious users from exploiting errors or unexpected situations in the program.

5. **Control over Program Flow**: Exception handling allows developers to handle specific error conditions and guide the program flow, rather than relying on unpredictable behavior.

### 92. What design pattern is used to implement exception handling features in most languages?

The design pattern commonly used for implementing exception handling is the **"Try-Catch"** pattern, also referred to as **"Exception Handling Pattern"**.

- **Try-Catch Block**: 
  - The **`try`** block contains code that may throw an exception.
  - The **`catch`** block handles the exception if one occurs.
  
- **Example**:
  ```java
  try {
      // Code that may throw an exception
      int result = 10 / 0;
  } catch (ArithmeticException e) {
      // Exception handling code
      System.out.println("An error occurred: " + e.getMessage());
  }
  ```

### 93. What is the need for a `finally` block?

The `finally` block is used to ensure that certain clean-up code is always executed, regardless of whether an exception is thrown or not. It's useful for closing resources like file streams, database connections, or network sockets that need to be released after their use, regardless of the program's flow.

- **Key reasons**:
  1. **Resource Cleanup**: It guarantees that resources are cleaned up (e.g., closing files or releasing database connections).
  2. **Consistency**: It ensures that important actions (such as logging) or resource deallocation happen regardless of whether an exception occurred.
  3. **Ensuring Program Continuity**: Even if an exception is thrown, the program will continue to execute after the `finally` block.

### 94. In what scenarios is code in `finally` not executed?

The code in a **`finally`** block is generally always executed, but there are some scenarios where it may not be executed:

1. **If the JVM crashes**: If there is a fatal error or if the JVM terminates abruptly (e.g., due to a `System.exit()` call), the `finally` block might not be executed.

2. **If the thread is killed**: If the thread executing the `finally` block is interrupted or killed before it can finish executing the `finally` block, the block may not be executed.

3. **If the JVM is forcefully terminated**: If `System.exit()` is called, it will terminate the JVM, preventing the `finally` block from executing.

4. **In case of an infinite loop or blocking operation**: If the code in the `finally` block itself contains an infinite loop or is blocked (waiting for a condition that never occurs), it will not complete.

### 95. Will `finally` be executed in the program below?

Without seeing the actual code, I can provide general guidance:

The `finally` block **will always be executed** unless the program terminates abruptly (e.g., due to a call to `System.exit()` or if the JVM crashes). Here's a basic example:

**Example 1**:
```java
public class Test {
    public static void main(String[] args) {
        try {
            System.out.println("In try block");
            int result = 10 / 0;  // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Exception caught");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Output**:
```
In try block
Exception caught
Finally block executed
```

In this case, the **`finally`** block is executed after the exception is caught, regardless of whether the `try` block executes successfully or an exception is thrown.

**Example 2 (with abrupt termination)**:
```java
public class Test {
    public static void main(String[] args) {
        try {
            System.out.println("In try block");
            System.exit(0);  // JVM exits abruptly
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Output**:
```
In try block
```

In this case, because `System.exit(0)` is called, the JVM terminates immediately, and the **`finally`** block is **not executed**.

---

### Summary:
- **Exception Handling**: It helps to manage errors and ensures program stability by using `try-catch` blocks.
- **Design Pattern**: The "Try-Catch" pattern is used for exception handling in many languages, including Java.
- **`finally` Block**: Used to ensure that cleanup code (like resource release) is always executed, regardless of whether an exception occurs.
- **Scenarios where `finally` is not executed**: It is not executed if the JVM crashes, the thread is terminated, or the program terminates via `System.exit()`.
- **Code in `finally`**: The code in the `finally` block is generally executed unless the JVM is forcibly terminated.

Let's answer your questions about **exception handling** in Java.

### 96. Is `try` without a `catch` allowed?

Yes, a `try` block **without a `catch`** is allowed **only if there is a `finally` block**. This is because a `try` block must either have a `catch` block to handle exceptions or a `finally` block to ensure that some code is always executed.

- **Syntax**:
  ```java
  try {
      // Code that may throw an exception
  } finally {
      // Code that will always be executed
  }
  ```

- **Example**:
  ```java
  public class Test {
      public static void main(String[] args) {
          try {
              System.out.println("In try block");
              int result = 10 / 0; // ArithmeticException
          } finally {
              System.out.println("Finally block executed");
          }
      }
  }
  ```

**Output**:
```
In try block
Finally block executed
```

In this case, the exception is not caught, but the `finally` block is still executed.

### 97. Is `try` without `catch` and `finally` allowed?

No, a `try` block **must** have either a `catch` block or a `finally` block. You cannot have a `try` block without either of them. The purpose of the `try` block is to handle or guarantee the handling of exceptions, and at least one of these constructs is required.

- **Invalid**:
  ```java
  try {
      // Some code
  } // Missing catch or finally, this will result in a compilation error.
  ```

### 98. Can you explain the hierarchy of exception handling classes?

Yes, Java has a rich hierarchy for exceptions. It is based on the class `Throwable`, which is the superclass for all errors and exceptions. Here’s the hierarchy:

- **`Throwable`**: The superclass of all errors and exceptions in Java.
  - **`Error`**: Represents severe errors that a program cannot handle (e.g., `OutOfMemoryError`, `StackOverflowError`).
    - **`VirtualMachineError`**: Errors related to the JVM, such as `OutOfMemoryError`.
  - **`Exception`**: Represents conditions that a program can handle. This is further divided into:
    - **`RuntimeException`**: Exceptions that can occur during the execution of the program and are unchecked (i.e., you don't need to declare them).
      - Examples: `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`.
    - **Checked exceptions**: These exceptions must be explicitly caught or declared to be thrown using the `throws` keyword.
      - Examples: `IOException`, `SQLException`.
      
- **Example Hierarchy**:
  ```
  Throwable
    ├── Error
    │    └── VirtualMachineError
    └── Exception
         ├── RuntimeException
         └── (Checked exceptions)
  ```

### 99. What is the difference between error and exception?

- **Error**:
  - Errors represent serious issues that a program usually cannot recover from (e.g., `OutOfMemoryError`, `StackOverflowError`).
  - These are **not meant to be caught** by the program. Errors are typically related to the environment in which the application is running.
  - **Example**: `OutOfMemoryError`.

- **Exception**:
  - Exceptions represent conditions that a program can potentially recover from. Exceptions are events that disrupt the normal flow of execution.
  - Exceptions are **meant to be handled** (either by using `try-catch` blocks or by declaring them).
  - There are two types of exceptions:
    - **Checked exceptions**: Exceptions that must be either caught or declared (e.g., `IOException`).
    - **Unchecked exceptions** (Runtime exceptions): Exceptions that are not mandatory to catch (e.g., `NullPointerException`).

### 100. What is the difference between checked exceptions and unchecked exceptions?

- **Checked Exceptions**:
  - These exceptions are checked at **compile-time**, and you must either catch them with a `try-catch` block or declare them using the `throws` keyword in the method signature.
  - They are **subclasses of `Exception`** but not `RuntimeException`.
  - **Example**: `IOException`, `SQLException`.

- **Unchecked Exceptions**:
  - These exceptions are not checked at compile-time. They are also known as **runtime exceptions** and are **subclasses of `RuntimeException`**.
  - These exceptions typically represent programming bugs, such as accessing a null reference or dividing by zero.
  - **Example**: `NullPointerException`, `ArrayIndexOutOfBoundsException`.

**Key Differences**:
- **Checked exceptions** must be handled or declared, while **unchecked exceptions** do not need to be caught or declared.
- Checked exceptions are often caused by external factors, while unchecked exceptions are usually caused by programming logic errors.

### 101. How do you throw an exception from a method?

You can throw an exception from a method using the `throw` keyword. The `throw` keyword is used to explicitly throw an exception from within a method or a block of code.

- **Syntax**:
  ```java
  throw new ExceptionType("Error message");
  ```

- **Example**:
  ```java
  public class Test {
      public static void main(String[] args) {
          try {
              throwException(); // Calling method that throws an exception
          } catch (Exception e) {
              System.out.println("Caught exception: " + e.getMessage());
          }
      }

      public static void throwException() throws Exception {
          throw new Exception("This is an exception thrown from method");
      }
  }
  ```

**Output**:
```
Caught exception: This is an exception thrown from method
```

In the example above, the method `throwException` throws an exception, which is then caught in the `try-catch` block in the `main` method. You can also specify the exception type (like `IOException`, `SQLException`, etc.) and a message when throwing an exception.

---

### Summary:
- **`try` without `catch`** is allowed only if there is a `finally` block.
- **`try` without `catch` and `finally`** is not allowed.
- The **hierarchy of exception classes** starts with `Throwable`, then divides into `Error` and `Exception`. `Exception` has both **checked** and **unchecked** exceptions.
- **Errors** are typically beyond the program's control, while **exceptions** are conditions that can be handled.
- **Checked exceptions** must be declared or caught, while **unchecked exceptions** don't have to be.
- You can **throw exceptions** from methods using the `throw` keyword and declare them with `throws`.

Here are the answers to your questions about **exception handling** and related topics in Java:

### 102. What happens when you throw a checked exception from a method?

When you throw a checked exception from a method, you must **either catch it** within the method using a `try-catch` block, or **declare it** in the method signature using the `throws` keyword. If you do neither, the code will not compile. This is a key feature of **checked exceptions**—they are checked at compile-time to ensure they are either handled or declared.

- **Example**:
  ```java
  public class Test {
      public static void main(String[] args) {
          try {
              throwCheckedException();
          } catch (IOException e) {
              System.out.println("Caught IOException: " + e.getMessage());
          }
      }

      public static void throwCheckedException() throws IOException {
          throw new IOException("Checked exception thrown");
      }
  }
  ```

In this example, the method `throwCheckedException` throws a checked exception (`IOException`). The calling method (in this case, `main`) catches this exception to handle it.

### 103. What are the options you have to eliminate compilation errors when handling checked exceptions?

When handling checked exceptions, you have two main options to avoid compilation errors:

1. **Catch the exception**:
   - You can catch the exception using a `try-catch` block and handle it accordingly.
   
   - **Example**:
     ```java
     try {
         // Code that may throw a checked exception
     } catch (IOException e) {
         // Handle the exception
     }
     ```

2. **Declare the exception**:
   - You can declare that your method might throw the exception using the `throws` keyword. This passes the responsibility of handling the exception to the caller.
   
   - **Example**:
     ```java
     public void myMethod() throws IOException {
         // Code that may throw an IOException
     }
     ```

These are the two options that you must use for **checked exceptions**. If you don't handle or declare them, you'll get a compilation error.

### 104. How do you create a custom exception?

To create a custom exception in Java, you can extend the `Exception` class (for checked exceptions) or the `RuntimeException` class (for unchecked exceptions). You can also provide constructors to allow users to pass messages or other details.

- **Steps**:
  1. **Extend `Exception`** (or `RuntimeException` for unchecked exceptions).
  2. **Provide constructors**: Typically, you include a default constructor and one that takes a message or cause.

- **Example**:
  ```java
  public class CustomException extends Exception {
      // Default constructor
      public CustomException() {
          super("This is a custom exception");
      }

      // Constructor with custom message
      public CustomException(String message) {
          super(message);
      }
  }

  // Usage
  public class Test {
      public static void main(String[] args) {
          try {
              throw new CustomException("Something went wrong!");
          } catch (CustomException e) {
              System.out.println(e.getMessage());
          }
      }
  }
  ```

### 105. How do you handle multiple exception types with the same exception handling block?

In Java 7 and later, you can handle multiple exceptions in a **single `catch` block** by separating them with a pipe (`|`). This is called **multi-catch**.

- **Example**:
  ```java
  try {
      // Code that may throw multiple types of exceptions
  } catch (IOException | SQLException e) {
      System.out.println("Exception occurred: " + e.getMessage());
  }
  ```

In this example, both `IOException` and `SQLException` are handled by the same `catch` block. You don't need separate blocks for each exception type.

### 106. Can you explain about try-with-resources?

The **try-with-resources** statement, introduced in Java 7, allows you to automatically close resources (such as files, sockets, database connections, etc.) after use, ensuring that resources are freed without needing explicit `finally` blocks.

- **Resources** are objects that implement the `AutoCloseable` or `java.io.Closeable` interface.
- When the try block finishes, the resources are automatically closed, whether the block completes normally or due to an exception.

- **Syntax**:
  ```java
  try (ResourceType resource = new ResourceType()) {
      // Code using the resource
  } catch (Exception e) {
      // Handle exception
  } // The resource is automatically closed here
  ```

- **Example**:
  ```java
  public class Test {
      public static void main(String[] args) {
          try (FileReader reader = new FileReader("file.txt")) {
              // Read from file
          } catch (IOException e) {
              System.out.println("Error reading file: " + e.getMessage());
          }
      }
  }
  ```

In this example, the `FileReader` is automatically closed at the end of the `try` block, even if an exception occurs.

### 107. How does try-with-resources work?

The **try-with-resources** works by automatically closing all resources declared in the parentheses of the `try` block when the block is exited. This is done by calling the `close()` method of each resource. The resources are closed in the reverse order of their declaration.

- **Important Points**:
  1. Resources must implement `AutoCloseable` or `Closeable` interface.
  2. If any of the resources throw an exception during closing, it will be suppressed, and the original exception will still be thrown.

- **Example**:
  ```java
  try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
      // Code using BufferedReader
  } catch (IOException e) {
      System.out.println("Exception: " + e.getMessage());
  }
  ```

When the `try` block finishes, the `BufferedReader` resource is automatically closed.

### 108. Can you explain a few exception handling best practices?

Here are a few best practices for **exception handling** in Java:

1. **Use specific exceptions**: Catch the most specific exceptions first, followed by more general exceptions. This helps you handle errors in a targeted manner.
   - **Bad**:
     ```java
     try {
         // Code
     } catch (Exception e) { // Catching general exception first
         // Handle all exceptions
     }
     ```
   - **Good**:
     ```java
     try {
         // Code
     } catch (IOException e) {
         // Handle IOException
     } catch (SQLException e) {
         // Handle SQLException
     } catch (Exception e) {
         // Handle other exceptions
     }
     ```

2. **Don’t catch `Exception` unless necessary**: Catching `Exception` should be avoided unless you have a good reason, as it can hide specific errors that might be better handled individually.

3. **Always clean up resources**: Use **try-with-resources** for automatic resource management, such as closing streams, connections, or other resources.

4. **Log exceptions**: Don’t swallow exceptions silently; always log them using a logger. This allows easier troubleshooting later.
   - **Bad**:
     ```java
     catch (Exception e) {
         // Do nothing
     }
     ```
   - **Good**:
     ```java
     catch (IOException e) {
         Logger.getLogger(MyClass.class.getName()).log(Level.SEVERE, "Error reading file", e);
     }
     ```

5. **Don’t throw generic exceptions**: Always throw a specific exception, not a generic one like `Exception`. This helps in identifying and handling the issue appropriately.

6. **Avoid overuse of exceptions for flow control**: Exceptions should not be used for normal control flow. Use exceptions for exceptional conditions, not for regular program flow (like checking for null values or normal flow control).

---

### Summary:
- **Checked exception handling** requires either catching or declaring the exception using `throws`.
- **Custom exceptions** are created by extending `Exception` (for checked exceptions) or `RuntimeException` (for unchecked exceptions).
- **Multi-catch** allows handling multiple exceptions in a single `catch` block.
- **Try-with-resources** simplifies resource management by automatically closing resources that implement `AutoCloseable`.
- **Best practices** include catching specific exceptions, logging exceptions, cleaning up resources, and using exceptions appropriately for exceptional conditions.

## Miscellaneous topics
Here are the answers to your questions about **arrays** and **enums** in Java:

### 109. What are the default values in an array?

In Java, when an array is created, its elements are initialized to default values based on the type of the array. Here are the default values for different data types:

- **For numeric types** (like `int`, `long`, `float`, `double`): `0` or `0.0` depending on the type.
- **For `char` type**: `'\u0000'` (null character).
- **For `boolean` type**: `false`.
- **For reference types** (like `String`, objects): `null`.

- **Example**:
  ```java
  int[] intArray = new int[5];  // Default value for int array elements: 0
  String[] strArray = new String[3];  // Default value for String array elements: null
  boolean[] boolArray = new boolean[2];  // Default value for boolean array elements: false
  ```

### 110. How do you loop around an array using an enhanced for loop?

The **enhanced for loop** (also known as the "for-each" loop) is a simple way to iterate over arrays or collections. It eliminates the need for indexing.

- **Syntax**:
  ```java
  for (Type element : array) {
      // Code to operate on element
  }
  ```

- **Example**:
  ```java
  int[] numbers = {1, 2, 3, 4, 5};
  
  // Looping through the array
  for (int num : numbers) {
      System.out.println(num);  // Prints each element in the array
  }
  ```

### 111. How do you print the content of an array?

You can print the content of an array in a few ways. Some common methods are:

1. **Using `Arrays.toString()`**:
   The `Arrays.toString()` method converts the array into a string representation, and you can then print it.
   
   - **Example**:
     ```java
     import java.util.Arrays;

     int[] numbers = {1, 2, 3, 4, 5};
     System.out.println(Arrays.toString(numbers));  // Prints: [1, 2, 3, 4, 5]
     ```

2. **Using enhanced for loop**:
   You can also use a loop to print the individual elements of the array.
   
   - **Example**:
     ```java
     int[] numbers = {1, 2, 3, 4, 5};
     for (int num : numbers) {
         System.out.print(num + " ");  // Prints: 1 2 3 4 5
     }
     ```

3. **Using `for` loop**:
   - **Example**:
     ```java
     int[] numbers = {1, 2, 3, 4, 5};
     for (int i = 0; i < numbers.length; i++) {
         System.out.print(numbers[i] + " ");  // Prints: 1 2 3 4 5
     }
     ```

### 112. How do you compare two arrays?

In Java, you can compare arrays using `Arrays.equals()` method, which checks whether two arrays have the same elements in the same order.

- **Example**:
  ```java
  import java.util.Arrays;

  int[] array1 = {1, 2, 3};
  int[] array2 = {1, 2, 3};
  
  boolean areEqual = Arrays.equals(array1, array2);
  System.out.println(areEqual);  // Prints: true
  ```

If the arrays are reference types (like objects), you can use `Arrays.deepEquals()` for deep comparison.

### 113. What is an enum?

An **enum** (short for **enumeration**) in Java is a special type that represents a fixed set of constants. Enums are used to define a collection of related constants, such as days of the week, months of the year, or states in a finite state machine.

- **Syntax**:
  ```java
  enum EnumName {
      CONSTANT1, CONSTANT2, CONSTANT3
  }
  ```

- **Example**:
  ```java
  enum Day {
      SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
  }
  
  public class TestEnum {
      public static void main(String[] args) {
          Day today = Day.MONDAY;  // Using enum constant
          System.out.println("Today is " + today);
      }
  }
  ```

In this example, `Day` is an enum that defines the days of the week. Enums provide type safety and can be used in switch statements, comparisons, and more. You can also add fields, methods, and constructors to enums.

---

### Summary:
- **Default values in an array** are set depending on the type (e.g., `0` for numeric types, `null` for reference types).
- **Enhanced for loop** provides an easy way to iterate over arrays and collections.
- To **print the content of an array**, you can use `Arrays.toString()` or iterate using a loop.
- To **compare arrays**, use `Arrays.equals()` for basic comparison.
- **Enums** represent a fixed set of constants and provide a way to define meaningful constant values for your program.

Here are the answers to your questions related to **switch statements, varargs, assertions, and garbage collection** in Java:

### 114. Can you use a switch statement around an enum?

Yes, you can use a **switch statement** with an **enum** in Java. In fact, enums are often used with switch statements to control program flow based on predefined constants. Since enum constants are unique and of the same type, using them in a switch statement is very straightforward.

- **Example**:
  ```java
  enum Day {
      MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
  }

  public class TestSwitch {
      public static void main(String[] args) {
          Day today = Day.WEDNESDAY;

          switch (today) {
              case MONDAY:
                  System.out.println("Start of the work week.");
                  break;
              case WEDNESDAY:
                  System.out.println("Midweek day.");
                  break;
              case FRIDAY:
                  System.out.println("End of the work week.");
                  break;
              default:
                  System.out.println("Regular day.");
                  break;
          }
      }
  }
  ```

In this example, the `switch` statement checks the value of the `Day` enum and prints the corresponding message.

### 115. What are variable arguments or varargs?

**Variable arguments** (or **varargs**) in Java allow you to pass a variable number of arguments to a method. This feature is particularly useful when the exact number of parameters is not known in advance. The varargs parameter must be the last parameter in the method's parameter list.

- **Syntax**:
  ```java
  methodName(type... parameterName)
  ```

- **Example**:
  ```java
  public class TestVarargs {
      public static void main(String[] args) {
          printNumbers(1, 2, 3, 4, 5);  // Passing 5 numbers as varargs
      }

      public static void printNumbers(int... numbers) {
          for (int num : numbers) {
              System.out.println(num);
          }
      }
  }
  ```

In this example, the `printNumbers` method accepts any number of integer arguments (varargs). You can pass zero or more arguments, and Java will treat them as an array.

### 116. What are asserts used for?

**Assertions** in Java are used for debugging purposes. They are a way to test assumptions made in the code during development. If an assertion fails, it throws an `AssertionError`, which helps to identify logical errors or assumptions that were not met during execution. Assertions are typically used during testing or development phases and should be disabled in production code.

- **Syntax**:
  ```java
  assert condition : message;
  ```

- **Example**:
  ```java
  public class TestAssert {
      public static void main(String[] args) {
          int value = 10;
          assert value > 5 : "Value should be greater than 5";
      }
  }
  ```

If the condition in the assertion fails (i.e., `value > 5`), an `AssertionError` will be thrown with the message "Value should be greater than 5".

### 117. When should asserts be used?

Assertions should be used in the following scenarios:

1. **For validating internal logic**: Assertions help check conditions that should never be false, such as invariants or pre/postconditions in your code. 
   - For example, validating that a method's input parameters meet certain criteria.
   
2. **During development and testing**: Assertions are primarily a tool for developers to catch bugs early during development and testing. They should not be used for error handling in production code.

3. **Not for user input validation**: Assertions should **not** be used to check user input or recover from runtime errors. Use regular exception handling for such cases.

**Note**: Assertions can be disabled at runtime by passing the `-ea` option when running the Java program.

### 118. What is garbage collection?

**Garbage collection** in Java is the automatic process of reclaiming memory by deleting objects that are no longer reachable or needed by the program. Java has a built-in garbage collector that automatically manages memory, removing objects that are no longer in use, freeing up resources, and preventing memory leaks.

Key points about **garbage collection**:
- The garbage collector identifies and clears objects that are no longer referenced, meaning they are not reachable from any live thread.
- It works in the background without needing explicit action from the developer.
- The primary goal of garbage collection is to manage heap memory efficiently and avoid memory leaks.

- **Example**: 
  ```java
  public class TestGC {
      public static void main(String[] args) {
          TestGC obj1 = new TestGC();  // obj1 is now a live object
          obj1 = null;  // obj1 is no longer referenced, eligible for garbage collection
      }
  }
  ```

In the above example, after setting `obj1` to `null`, the object previously referenced by `obj1` becomes eligible for garbage collection. The garbage collector will eventually free the memory associated with that object.

### Summary:
- You can use a **switch statement** with **enum** values to control the flow based on predefined constants.
- **Varargs** (variable arguments) allow passing a variable number of arguments to a method, making the code more flexible.
- **Assertions** are used to test assumptions and internal logic during development and debugging. They should not be used for runtime error handling.
- **Garbage collection** is the automatic process of reclaiming memory by removing objects that are no longer in use, which helps to avoid memory leaks and manage resources efficiently.

Here are the answers to your questions regarding **garbage collection**, **initialization blocks**, and **static initializers** in Java:

### 119. Can you explain garbage collection with an example?

**Garbage collection** (GC) in Java is the process of automatically reclaiming memory by removing objects that are no longer reachable in the program. Objects that are no longer referenced by any active part of the program become eligible for garbage collection.

**Example**:

```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        // Creating object instances
        MyClass obj1 = new MyClass("Object 1");
        MyClass obj2 = new MyClass("Object 2");
        
        // Assigning null to obj1, obj1 is now eligible for garbage collection
        obj1 = null;
        
        // At this point, obj2 is still referenced, so it's not eligible for GC
        System.gc();  // Suggests to JVM to run garbage collection (not guaranteed)
    }
}

class MyClass {
    String name;
    
    MyClass(String name) {
        this.name = name;
    }
    
    @Override
    protected void finalize() {
        // Called just before object is destroyed by GC
        System.out.println("Cleaning up resources for " + name);
    }
}
```

**Explanation**:
- The `obj1` reference is set to `null`, which means the object previously referenced by `obj1` becomes eligible for garbage collection.
- The `System.gc()` call is a request to the JVM to perform garbage collection, but it's not guaranteed to execute immediately.
- The `finalize()` method is called before the object is reclaimed by the garbage collector to clean up resources.

**Note**: Garbage collection happens automatically, but you cannot directly control when it occurs. The JVM decides when to run it.

### 120. When is garbage collection run?

Garbage collection is typically run when the Java Virtual Machine (JVM) detects that memory is running low or when the heap space has been exhausted. However, it's not triggered at predictable times, and its execution is dependent on several factors such as:

1. **Heap memory usage**: When the JVM notices that a significant portion of heap memory is occupied by unreachable objects, it may decide to perform garbage collection to reclaim space.
2. **Low memory**: If the JVM runs out of available memory, it may trigger garbage collection to free up space.
3. **Explicit request**: The `System.gc()` method can request garbage collection, but this is a suggestion, not a command, and the JVM may or may not honor it.

The garbage collector works in the background, without any explicit calls from the developer.

### 121. What are best practices on garbage collection?

While the garbage collector is automatic, there are a few best practices to help improve memory management and performance:

1. **Avoid memory leaks**: Ensure that objects are properly dereferenced once they're no longer needed, allowing the garbage collector to clean them up.
   - Example: Set objects to `null` if they are not needed anymore, especially in long-running applications or loops.

2. **Use weak references when necessary**: If an object should be collected as soon as it's not needed (for example, in caches), use `WeakReference` to avoid keeping it alive unnecessarily.

3. **Optimize object creation**: Avoid excessive object creation inside loops. Reuse objects if possible or use object pooling techniques for frequently created objects.

4. **Monitor memory usage**: You can monitor memory usage through JVM flags or profiling tools. This helps you understand how the garbage collector is behaving and optimize memory management.

5. **Minimize finalizer usage**: The `finalize()` method can be expensive in terms of performance. Avoid relying on it for cleaning resources; instead, use explicit resource management (like `try-with-resources` for file or network resources).

6. **Tune garbage collection settings**: You can fine-tune garbage collection by adjusting JVM options related to the heap size and GC algorithms based on your application's needs.

### 122. What are initialization blocks?

**Initialization blocks** are blocks of code that are executed when an object is created, or a class is loaded. They can be either **instance initialization blocks** or **static initialization blocks**.

1. **Instance Initialization Blocks**: These blocks are executed when an instance of the class is created, just before the constructor is called.

   - **Example**:
     ```java
     public class MyClass {
         {
             // This is an instance initialization block
             System.out.println("Instance initialization block executed");
         }

         public MyClass() {
             System.out.println("Constructor executed");
         }

         public static void main(String[] args) {
             MyClass obj = new MyClass();
         }
     }
     ```
     Output:
     ```
     Instance initialization block executed
     Constructor executed
     ```

   In this example, the instance initialization block is executed first, followed by the constructor.

2. **Static Initialization Blocks**: These blocks are executed once when the class is loaded into memory, before any instances of the class are created. They are typically used to initialize static variables or perform one-time setup for the class.

   - **Example**:
     ```java
     public class MyClass {
         static {
             // This is a static initialization block
             System.out.println("Static initialization block executed");
         }

         public static void main(String[] args) {
             MyClass obj = new MyClass();
         }
     }
     ```
     Output:
     ```
     Static initialization block executed
     ```

   The static block runs only once when the class is loaded, even if multiple instances of the class are created.

### 123. What is a static initializer?

A **static initializer** is a block of code in a class that is executed when the class is first loaded into memory. It is used to initialize static variables or perform any class-level setup. Static initializers are executed only once when the class is loaded and before any instance of the class is created.

- **Example**:
  ```java
  public class MyClass {
      static int counter;

      static {
          // Static initializer block
          counter = 10;  // Initializing static variable
          System.out.println("Static initializer executed");
      }

      public static void main(String[] args) {
          System.out.println("Counter: " + counter);
      }
  }
  ```
  Output:
  ```
  Static initializer executed
  Counter: 10
  ```

In this example, the static initializer initializes the `counter` static variable before the `main()` method runs. The static initializer is executed only once, the first time the class is loaded.

---

### Summary:
- **Garbage collection** automatically reclaims memory by removing objects that are no longer reachable. You can suggest garbage collection with `System.gc()`, but the JVM decides when to run it.
- Garbage collection occurs when memory is low or heap space is full, and the JVM runs it in the background without direct control from the programmer.
- **Best practices for garbage collection** include avoiding memory leaks, minimizing object creation inside loops, and tuning garbage collection settings for performance.
- **Initialization blocks** are used to initialize objects and static variables. Instance blocks run when an object is created, while static blocks run when the class is loaded.
- **Static initializers** are a type of initialization block used for class-level setup and static variable initialization. They run once when the class is loaded into memory.

Here are the answers to your questions related to **instance initializer blocks**, **tokenizing**, **serialization**, and the **Serializable interface** in Java:

### 124. What is an instance initializer block?

An **instance initializer block** is a block of code in a Java class that is executed whenever an instance of that class is created, right before the constructor is called. Instance initializer blocks allow you to perform some initialization tasks that need to run every time an object is created, but outside of the constructor. 

It is used for setting up instance-level fields or executing some logic for every instance of the class. 

- **Syntax**:
  ```java
  {
      // Code here is executed whenever an instance of the class is created
  }
  ```

- **Example**:
  ```java
  public class InstanceInitializerExample {
      private String name;
      
      {
          // Instance initializer block
          name = "Default Name";
          System.out.println("Instance initializer block executed");
      }

      public InstanceInitializerExample() {
          System.out.println("Constructor executed");
      }

      public static void main(String[] args) {
          InstanceInitializerExample obj = new InstanceInitializerExample();
          System.out.println("Name: " + obj.name);
      }
  }
  ```
  **Output**:
  ```
  Instance initializer block executed
  Constructor executed
  Name: Default Name
  ```

In this example, the instance initializer block sets the `name` property before the constructor is executed.

### 125. What is tokenizing?

**Tokenizing** refers to the process of breaking down a stream of text into smaller, meaningful units called **tokens**. In Java, this is often done when working with strings or parsing text, such as separating words or components from a larger sentence or input string.

For example, tokenizing a sentence like "Java is great" would break it into tokens: `"Java"`, `"is"`, and `"great"`.

In Java, the `StringTokenizer` class or the `split()` method from the `String` class can be used for tokenizing strings.

### 126. Can you give an example of tokenizing?

Yes, here's an example using both the `StringTokenizer` class and the `split()` method:

- **Using `StringTokenizer`**:
  ```java
  import java.util.StringTokenizer;

  public class TokenizingExample {
      public static void main(String[] args) {
          String text = "Java is great";
          StringTokenizer tokenizer = new StringTokenizer(text);
          
          while (tokenizer.hasMoreTokens()) {
              System.out.println(tokenizer.nextToken());
          }
      }
  }
  ```
  **Output**:
  ```
  Java
  is
  great
  ```

  The `StringTokenizer` class splits the string into tokens by default using spaces as delimiters.

- **Using `split()` method**:
  ```java
  public class TokenizingExample {
      public static void main(String[] args) {
          String text = "Java is great";
          String[] tokens = text.split(" ");  // Split by space

          for (String token : tokens) {
              System.out.println(token);
          }
      }
  }
  ```
  **Output**:
  ```
  Java
  is
  great
  ```

  The `split()` method divides the string into an array of substrings based on the delimiter provided (in this case, a space).

### 127. What is serialization?

**Serialization** in Java is the process of converting an object into a byte stream so that it can be easily saved to a file or sent over a network. The opposite of serialization is **deserialization**, where the byte stream is converted back into an object.

Serialization is mainly used when you need to persist the state of an object or transfer it over a network. For an object to be serializable, the class must implement the `Serializable` interface.

**Key points**:
- The class must implement the `Serializable` interface.
- The object’s state (fields) is converted to a byte stream, but not the methods (methods are not serialized).
- `ObjectOutputStream` is used for serialization, and `ObjectInputStream` is used for deserialization.

### 128. How do you serialize an object using the `Serializable` interface?

To serialize an object in Java, follow these steps:

1. Make the class implement the `Serializable` interface (which is a marker interface, i.e., it doesn't have any methods).
2. Use `ObjectOutputStream` to write the object to a file or stream.

**Steps to serialize an object**:
1. **Implement `Serializable`**: Your class must implement `Serializable`.
2. **Use `ObjectOutputStream`**: Use this stream to write the object to a file.

**Example**:
```java
import java.io.*;

class Person implements Serializable {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", age=" + age + "]";
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        Person person = new Person("John", 30);

        // Serialization process
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            out.writeObject(person);
            System.out.println("Object serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization process
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Person deserializedPerson = (Person) in.readObject();
            System.out.println("Object deserialized: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

**Explanation**:
- The class `Person` implements the `Serializable` interface, making its instances eligible for serialization.
- The `ObjectOutputStream` is used to write the object to a file (`person.ser`).
- The `ObjectInputStream` is used to read the object back from the file.

**Output**:
```
Object serialized
Object deserialized: Person[name=John, age=30]
```

In this example, the `Person` object is serialized to a file and later deserialized back into a `Person` object.

---

### Summary:
- **Instance initializer blocks** are executed every time an object is created, right before the constructor. They are used to initialize instance variables.
- **Tokenizing** is the process of splitting a string into smaller parts (tokens), often using delimiters like spaces. It can be done using `StringTokenizer` or `split()` method.
- **Serialization** is the process of converting an object into a byte stream so it can be stored or transmitted. The object must implement the `Serializable` interface, and `ObjectOutputStream` is used to serialize objects.

Here are the answers to your Java questions regarding serialization and deserialization:

### 129. How do you de-serialize in Java?

**Deserialization** in Java is the process of converting a byte stream back into an object. This is the opposite of serialization. To deserialize an object, you use the `ObjectInputStream` class to read the byte stream and convert it into an actual Java object.

#### Steps to deserialize:
1. **Use `ObjectInputStream`**: This stream reads the object from a file or other stream.
2. **Call `readObject()`**: This method is used to read an object from the stream and convert it back to its original state.

#### Example:
```java
import java.io.*;

class Person implements Serializable {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", age=" + age + "]";
    }
}

public class DeserializationExample {
    public static void main(String[] args) {
        // Deserialization process
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Person deserializedPerson = (Person) in.readObject();
            System.out.println("Object deserialized: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```
In this example, we read the serialized object (`person.ser`) and convert it back to a `Person` object using `ObjectInputStream.readObject()`.

**Output**:
```
Object deserialized: Person[name=John, age=30]
```

### 130. What do you do if only parts of the object have to be serialized?

If only **parts** of an object need to be serialized, you can use the **`transient` keyword**. The `transient` keyword marks certain fields to be excluded from the serialization process. This is useful when you don't want some data (such as sensitive information or complex objects that can be reconstructed) to be serialized.

- **Example**:
  ```java
  class Person implements Serializable {
      private String name;
      private transient int age;  // This will not be serialized

      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }

      @Override
      public String toString() {
          return "Person[name=" + name + ", age=" + age + "]";
      }
  }
  ```
In this case, the `age` field will not be serialized when the `Person` object is serialized, but the `name` will be.

### 131. How do you serialize a hierarchy of objects?

To serialize an object hierarchy, every class in the hierarchy must implement the `Serializable` interface. When you serialize an object, any objects referenced by that object are also serialized recursively, provided they also implement `Serializable`.

- **Example**:
  ```java
  import java.io.*;

  class Animal implements Serializable {
      private String name;
      
      public Animal(String name) {
          this.name = name;
      }

      public String toString() {
          return "Animal[name=" + name + "]";
      }
  }

  class Dog extends Animal {
      private String breed;

      public Dog(String name, String breed) {
          super(name);
          this.breed = breed;
      }

      public String toString() {
          return "Dog[breed=" + breed + ", " + super.toString() + "]";
      }
  }

  public class SerializationExample {
      public static void main(String[] args) {
          Dog dog = new Dog("Max", "Bulldog");

          try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dog.ser"))) {
              out.writeObject(dog);
              System.out.println("Object serialized");
          } catch (IOException e) {
              e.printStackTrace();
          }

          // Deserialization process
          try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("dog.ser"))) {
              Dog deserializedDog = (Dog) in.readObject();
              System.out.println("Object deserialized: " + deserializedDog);
          } catch (IOException | ClassNotFoundException e) {
              e.printStackTrace();
          }
      }
  }
  ```

In this example, both the `Animal` and `Dog` classes implement `Serializable`, and the entire hierarchy will be serialized correctly.

**Output**:
```
Object serialized
Object deserialized: Dog[breed=Bulldog, Animal[name=Max]]
```

### 132. Are the constructors in an object invoked when it is de-serialized?

**No**, constructors are **not** invoked during deserialization. When you deserialize an object, Java simply reconstructs the object and assigns the fields to the values stored in the serialized byte stream. The constructor is not called. However, you can define the behavior you want after the object is deserialized by implementing the `readObject()` method in your class.

If you want to run specific code during deserialization, you can implement the `readObject()` method.

- **Example**:
  ```java
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();  // Reads the default serialization data
      // Custom logic here after deserialization
  }
  ```

### 133. Are the values of static variables stored when an object is serialized?

**No**, **static variables** are **not** stored during serialization. Static variables belong to the class itself rather than to instances of the class, so they are not serialized along with instance variables.

- When an object is serialized, only instance fields are stored. Static variables are initialized to their default values when the class is loaded, but their values are not saved or restored as part of the object.

- **Example**:
  ```java
  class MyClass implements Serializable {
      private int instanceVar = 10;
      private static int staticVar = 20;

      public String toString() {
          return "instanceVar: " + instanceVar + ", staticVar: " + staticVar;
      }
  }

  public class SerializationExample {
      public static void main(String[] args) throws IOException, ClassNotFoundException {
          MyClass obj = new MyClass();
          try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("obj.ser"))) {
              out.writeObject(obj);
          }

          // Change the static variable value
          MyClass.staticVar = 100;

          try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("obj.ser"))) {
              MyClass deserializedObj = (MyClass) in.readObject();
              System.out.println(deserializedObj); // staticVar will be its default value (not serialized)
          }
      }
  }
  ```

**Output** (with static variable reset to its default value):
```
instanceVar: 10, staticVar: 0
```

Here, `staticVar` was not serialized, and during deserialization, it was initialized to the default value (`0` in this case).

---

### Summary:
- **Deserialization** is done using `ObjectInputStream` and the `readObject()` method to convert byte streams back into Java objects.
- **Transient fields** are excluded from serialization if only parts of the object need to be serialized.
- To serialize a **hierarchy of objects**, ensure all classes in the hierarchy implement `Serializable`.
- **Constructors are not called** during deserialization. You can use `readObject()` for custom initialization if needed.
- **Static variables** are not serialized because they belong to the class, not to individual instances, and are not part of the object's state.

## Collections
Here are the answers to your Java questions regarding collections:

### 134. Why do we need collections in Java?

Collections in Java provide a way to group and manage objects in a more efficient and organized manner. They are essential for:

1. **Storing Groups of Objects**: Collections allow you to store and manipulate a group of objects as a single unit. They provide better ways to work with groups of objects compared to arrays, offering more functionality and flexibility.

2. **Dynamic Data Storage**: Collections can grow and shrink in size dynamically as opposed to arrays, which have a fixed size. This makes collections more flexible when dealing with unknown or changing amounts of data.

3. **Efficiency**: The Java Collections Framework includes algorithms for sorting, searching, and manipulating collections, providing optimized implementations for various data storage and retrieval needs.

4. **Readability and Maintainability**: Using collections can make code cleaner, more understandable, and easier to maintain. Collections help in abstracting the complex functionality of handling data structures like lists, maps, sets, etc.

### 135. What are the important interfaces in the collection hierarchy?

The **Collection Framework** in Java consists of several interfaces, which define the common behavior for different types of collections. Some important interfaces in the collection hierarchy include:

1. **Collection**: The root interface of the collection hierarchy, which represents a group of objects. It defines basic methods like `add()`, `remove()`, `clear()`, and `size()`.

2. **List**: Extends `Collection` and represents an ordered collection (also called a sequence). It allows duplicate elements and provides methods to access elements by index. Common implementations include `ArrayList`, `LinkedList`, and `Vector`.

3. **Set**: Extends `Collection` and represents a collection that does not allow duplicate elements. Common implementations include `HashSet`, `LinkedHashSet`, and `TreeSet`.

4. **Queue**: Extends `Collection` and represents a collection used for holding elements before processing. It provides methods like `offer()`, `poll()`, and `peek()`. Common implementations include `LinkedList` and `PriorityQueue`.

5. **Deque**: Extends `Queue` and represents a double-ended queue, which allows elements to be added or removed from both ends. Common implementations include `ArrayDeque` and `LinkedList`.

6. **Map**: While not a subclass of `Collection`, the `Map` interface represents a collection of key-value pairs, where each key is mapped to a specific value. Common implementations include `HashMap`, `TreeMap`, and `LinkedHashMap`.

### 136. What are the important methods that are declared in the collection interface?

The `Collection` interface provides several important methods for working with collections, including:

1. **add(E e)**: Adds the specified element to the collection.
2. **addAll(Collection<? extends E> c)**: Adds all elements from the specified collection to the current collection.
3. **clear()**: Removes all elements from the collection.
4. **contains(Object o)**: Returns `true` if the collection contains the specified element.
5. **containsAll(Collection<?> c)**: Returns `true` if the collection contains all elements from the specified collection.
6. **isEmpty()**: Returns `true` if the collection is empty.
7. **iterator()**: Returns an iterator over the elements in the collection.
8. **remove(Object o)**: Removes a single instance of the specified element from the collection, if present.
9. **removeAll(Collection<?> c)**: Removes all elements from the collection that are contained in the specified collection.
10. **retainAll(Collection<?> c)**: Retains only the elements that are contained in the specified collection.
11. **size()**: Returns the number of elements in the collection.
12. **toArray()**: Returns an array containing all elements in the collection.

These methods are designed to provide basic functionality for manipulating collections, such as adding, removing, checking, and iterating over elements.

### 137. Can you explain briefly about the List interface?

The **List** interface is a part of the Java Collections Framework and represents an ordered collection of elements. It allows duplicate elements and preserves the order of insertion. Lists allow random access to elements via an index and can be modified dynamically in size.

#### Key characteristics of the `List` interface:
1. **Ordered**: Elements in a list have a specific order, and the order is maintained even when elements are added or removed.
2. **Allows Duplicates**: Unlike sets, lists allow duplicate elements, meaning the same object can appear multiple times in a list.
3. **Access by Index**: Lists allow access to their elements using indices (like arrays). The first element is at index 0, the second at index 1, and so on.
4. **Implementations**: The `List` interface has several common implementations, including:
   - **`ArrayList`**: A dynamically resizable array. It provides fast random access to elements, but adding/removing elements at arbitrary positions can be slower.
   - **`LinkedList`**: A doubly-linked list that provides efficient insertions and deletions at both ends but slower random access compared to `ArrayList`.
   - **`Vector`**: Similar to `ArrayList`, but synchronized and less commonly used due to its thread-safety overhead.

#### Common methods in `List` interface:
1. **add(int index, E element)**: Inserts the specified element at the specified position in the list.
2. **get(int index)**: Returns the element at the specified position in the list.
3. **set(int index, E element)**: Replaces the element at the specified position in the list with the specified element.
4. **remove(int index)**: Removes the element at the specified position in the list.
5. **indexOf(Object o)**: Returns the index of the first occurrence of the specified element in the list.
6. **lastIndexOf(Object o)**: Returns the index of the last occurrence of the specified element in the list.
7. **subList(int fromIndex, int toIndex)**: Returns a view of the portion of the list between the specified `fromIndex`, inclusive, and `toIndex`, exclusive.

#### Example:
```java
import java.util.List;
import java.util.ArrayList;

public class ListExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        System.out.println("First fruit: " + fruits.get(0));  // Access by index
        System.out.println("Size of list: " + fruits.size());

        fruits.add(1, "Orange");  // Insert at index 1
        System.out.println("Fruits after insertion: " + fruits);

        fruits.remove("Banana");  // Remove an element by value
        System.out.println("Fruits after removal: " + fruits);
    }
}
```

**Output**:
```
First fruit: Apple
Size of list: 3
Fruits after insertion: [Apple, Orange, Banana, Cherry]
Fruits after removal: [Apple, Orange, Cherry]
```

In this example, the `List` interface is implemented by `ArrayList`, and various methods are demonstrated, such as adding, removing, and accessing elements by index.

### Summary:
- Collections are essential for storing and manipulating groups of objects dynamically, efficiently, and flexibly.
- The key interfaces in the collection hierarchy include `Collection`, `List`, `Set`, `Queue`, and `Map`.
- The `Collection` interface defines core methods for adding, removing, and checking elements in a collection.
- The `List` interface represents an ordered collection that allows duplicate elements and provides indexed access to elements.

Here are the answers to your questions about **ArrayList** in Java:

### 138. Explain about ArrayList with an example?

An `ArrayList` is a resizable array implementation of the `List` interface. It allows dynamic resizing, meaning the size of the list can grow as elements are added. It is part of the `java.util` package.

#### Key features of `ArrayList`:
- It can store elements of any type (objects).
- It maintains the insertion order.
- It allows duplicates.
- It allows null elements.
- It provides random access to elements using indexes, similar to an array.

#### Example:
```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();

        // Add elements to the ArrayList
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Access elements using index
        System.out.println("First fruit: " + fruits.get(0));

        // Add an element at a specific position
        fruits.add(1, "Orange");

        // Print all elements in the ArrayList
        System.out.println("Fruits: " + fruits);

        // Remove an element by index
        fruits.remove(2);

        // Print updated ArrayList
        System.out.println("Updated fruits: " + fruits);
    }
}
```

**Output**:
```
First fruit: Apple
Fruits: [Apple, Orange, Banana, Cherry]
Updated fruits: [Apple, Orange, Banana]
```

In this example, an `ArrayList` is created to store `String` elements, elements are added, accessed, and removed. It shows how dynamic resizing works with `ArrayList`.

---

### 139. Can an ArrayList have duplicate elements?

Yes, an `ArrayList` **can** have duplicate elements. It does not enforce any restrictions on the uniqueness of elements, unlike a `Set`. It allows storing multiple occurrences of the same object.

#### Example:
```java
import java.util.ArrayList;

public class ArrayListDuplicateExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");  // Duplicate element
        list.add("Orange");

        System.out.println("ArrayList with duplicates: " + list);
    }
}
```

**Output**:
```
ArrayList with duplicates: [Apple, Banana, Apple, Orange]
```

In the above example, the element `"Apple"` is added twice, showing that `ArrayList` allows duplicates.

---

### 140. How do you iterate around an ArrayList using iterator?

You can use an **Iterator** to iterate through the elements of an `ArrayList`. An iterator provides methods like `hasNext()` to check if there are more elements and `next()` to retrieve the next element.

#### Example:
```java
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListIteratorExample {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Create an Iterator
        Iterator<String> iterator = fruits.iterator();

        // Iterate through the ArrayList using the Iterator
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

**Output**:
```
Apple
Banana
Orange
```

In the example, the `Iterator` is used to iterate over each element in the `ArrayList` and print them out.

---

### 141. How do you sort an ArrayList?

You can sort an `ArrayList` in Java using the `Collections.sort()` method. This method sorts the list in ascending order (by default) if the list elements are comparable (e.g., `String`, `Integer`). If the list elements are custom objects, you can provide a comparator.

#### Example:
```java
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSortExample {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Orange");

        // Sort the ArrayList in ascending order
        Collections.sort(fruits);

        System.out.println("Sorted ArrayList: " + fruits);
    }
}
```

**Output**:
```
Sorted ArrayList: [Apple, Banana, Orange]
```

In the example, the `Collections.sort()` method sorts the `ArrayList` of strings in alphabetical order.

If you want to sort in descending order, you can use a custom comparator:
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListSortDescendingExample {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Orange");

        // Sort the ArrayList in descending order
        Collections.sort(fruits, Comparator.reverseOrder());

        System.out.println("Sorted ArrayList in descending order: " + fruits);
    }
}
```

**Output**:
```
Sorted ArrayList in descending order: [Orange, Banana, Apple]
```

In this case, the `Comparator.reverseOrder()` is used to reverse the sorting order.

---

### Summary:
- **ArrayList** is a resizable array implementation of the `List` interface that allows dynamic size adjustments and maintains insertion order.
- `ArrayList` allows **duplicate elements**.
- You can **iterate** over an `ArrayList` using an `Iterator` with `hasNext()` and `next()` methods.
- You can **sort** an `ArrayList` using `Collections.sort()`. To sort in reverse order, you can use a custom comparator.

Here are the answers to your Java questions:

### 142. How do you sort elements in an ArrayList using the `Comparable` interface?

The `Comparable` interface is used when you want to define the natural order of objects of a class. The class implementing the `Comparable` interface needs to override the `compareTo()` method to specify how two objects should be compared.

Once `Comparable` is implemented, you can use the `Collections.sort()` method to sort the `ArrayList`.

#### Example:
```java
import java.util.ArrayList;
import java.util.Collections;

class Person implements Comparable<Person> {
    String name;
    int age;

    // Constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Implement compareTo method
    @Override
    public int compareTo(Person other) {
        return this.age - other.age;  // Sort by age in ascending order
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class ArrayListComparableExample {
    public static void main(String[] args) {
        // Create an ArrayList of Person objects
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 22));
        people.add(new Person("Charlie", 30));

        // Sort the ArrayList using the Comparable interface
        Collections.sort(people);

        System.out.println("Sorted List: " + people);
    }
}
```

**Output**:
```
Sorted List: [Bob (22), Alice (25), Charlie (30)]
```

In this example, the `Person` class implements `Comparable` and sorts the list of `Person` objects based on the `age` field.

---

### 143. How do you sort elements in an ArrayList using the `Comparator` interface?

The `Comparator` interface is used when you want to sort objects in a custom order that is different from the natural order defined by the `Comparable` interface. The `Comparator` interface has the `compare()` method, which defines how two objects should be compared.

You can pass a `Comparator` to the `Collections.sort()` method to sort the `ArrayList`.

#### Example:
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Person {
    String name;
    int age;

    // Constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class ArrayListComparatorExample {
    public static void main(String[] args) {
        // Create an ArrayList of Person objects
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 22));
        people.add(new Person("Charlie", 30));

        // Sort the ArrayList using the Comparator interface (sort by name)
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);  // Sort by name alphabetically
            }
        });

        System.out.println("Sorted List by Name: " + people);
    }
}
```

**Output**:
```
Sorted List by Name: [Alice (25), Bob (22), Charlie (30)]
```

In this example, the `Comparator` interface is used to sort the `Person` objects by their `name` field.

Alternatively, you can use **lambda expressions** (in Java 8 and above) to simplify the `Comparator`:

```java
Collections.sort(people, (p1, p2) -> p1.name.compareTo(p2.name));
```

---

### 144. What is the `Vector` class? How is it different from an `ArrayList`?

The `Vector` class is a part of the Java Collection Framework and is very similar to `ArrayList`. It implements the `List` interface and can dynamically grow as elements are added. The key differences are:

- **Synchronization**: `Vector` is **synchronized**, meaning it is thread-safe and can be used safely in multi-threaded environments. However, this can lead to overhead in performance when synchronization is not needed.
- **Growth Policy**: By default, `Vector` grows its size by doubling the array when it runs out of space, while `ArrayList` typically grows by 50%.
- **Performance**: Due to synchronization and resizing behavior, `Vector` tends to be slower than `ArrayList` in single-threaded applications.

#### Example:
```java
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("Apple");
        vector.add("Banana");
        vector.add("Cherry");
        System.out.println("Vector: " + vector);
    }
}
```

In most cases, you should prefer `ArrayList` unless thread safety is a critical requirement. If thread safety is needed, it's often better to use other options, such as `CopyOnWriteArrayList`.

---

### 145. What is `LinkedList`? What interfaces does it implement? How is it different from an `ArrayList`?

The `LinkedList` class implements both the `List` and `Queue` interfaces. Unlike `ArrayList`, which is backed by a dynamically resizing array, `LinkedList` is backed by a **doubly linked list**. This makes `LinkedList` more efficient for certain operations.

#### Key Differences:
- **Access Time**: `ArrayList` provides **O(1)** time complexity for accessing elements by index, whereas `LinkedList` requires **O(n)** time for random access due to its traversal from the head.
- **Insertions and Deletions**: `LinkedList` excels in **insertions** and **deletions** in the middle of the list. In `ArrayList`, these operations involve shifting elements, which can be slower.
- **Memory Overhead**: `LinkedList` requires more memory per element due to the storage of references to the next and previous elements (doubly linked nodes).

#### Example:
```java
import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Cherry");

        System.out.println("LinkedList: " + linkedList);
    }
}
```

**Output**:
```
LinkedList: [Apple, Banana, Cherry]
```

`LinkedList` is typically used when you have frequent insertions and deletions, especially when you're working with a lot of data and need to modify the list.

---

### 146. Can you briefly explain the `Set` interface?

The `Set` interface is part of the Java Collection Framework and represents a **collection of unique elements**. It does not allow duplicate values and is similar to a mathematical set.

Key characteristics of `Set`:
- **No duplicates**: A `Set` cannot contain duplicate elements. If you attempt to add a duplicate, it will be ignored.
- **No ordering**: Some implementations of `Set` (like `HashSet`) do not guarantee the order of elements. Other implementations like `LinkedHashSet` maintain insertion order, and `TreeSet` sorts the elements.
- `Set` is implemented by classes like `HashSet`, `LinkedHashSet`, and `TreeSet`.

#### Example:
```java
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // Duplicate element, will be ignored
        set.add("Orange");

        System.out.println("Set: " + set);
    }
}
```

**Output**:
```
Set: [Apple, Banana, Orange]
```

In the above example, you can see that the duplicate `"Apple"` is not added to the `HashSet`, which demonstrates the uniqueness constraint of the `Set` interface.

---

### Summary:
- **Comparable** is used to define the natural order of objects, and it is used with `Collections.sort()`.
- **Comparator** is used for custom sorting and can be used with `Collections.sort()`.
- `Vector` is similar to `ArrayList` but is synchronized, while `ArrayList` is not.
- `LinkedList` is backed by a doubly linked list and is better for frequent insertions and deletions but slower for random access compared to `ArrayList`.
- The `Set` interface ensures uniqueness of elements and does not allow duplicates. Implementations include `HashSet`, `LinkedHashSet`, and `TreeSet`.

Here are the answers to your Java questions related to the `Set` interface:

### 147. What are the important interfaces related to the `Set` interface?

The `Set` interface is part of the Java Collections Framework and has the following important related interfaces:

1. **SortedSet**: This interface is a specialized subset of `Set` that maintains the elements in a sorted order. It extends the `Set` interface and adds methods that provide a total ordering for the elements. A `SortedSet` is useful when the order of elements matters, and you want them to be automatically sorted.

   - Important methods:
     - `first()`: Returns the first (lowest) element.
     - `last()`: Returns the last (highest) element.
     - `headSet(E toElement)`: Returns a subset of the elements that are less than the specified element.
     - `tailSet(E fromElement)`: Returns a subset of elements that are greater than or equal to the specified element.
   
   **Implementations**: `TreeSet`.

2. **NavigableSet**: This interface extends `SortedSet` and provides methods for navigation (e.g., getting the closest matches to a given element). It allows more advanced searching, such as floor, ceiling, higher, and lower element retrievals.

   - Important methods:
     - `lower(E e)`: Returns the greatest element less than `e`.
     - `floor(E e)`: Returns the greatest element less than or equal to `e`.
     - `ceiling(E e)`: Returns the least element greater than or equal to `e`.
     - `higher(E e)`: Returns the least element greater than `e`.

   **Implementations**: `TreeSet`.

---

### 148. What is the difference between `Set` and `SortedSet` interfaces?

The main difference between the `Set` and `SortedSet` interfaces lies in the order of elements:

- **Set**: 
  - Does not guarantee any specific order of elements.
  - It only ensures that there are no duplicates in the collection.
  - The order in which elements are stored or retrieved depends on the specific implementation, such as `HashSet` (no ordering) or `LinkedHashSet` (insertion order).

- **SortedSet**:
  - Extends `Set` and guarantees that the elements are stored in a **sorted order**.
  - It provides methods to access the elements based on their order, such as `first()`, `last()`, `headSet()`, and `tailSet()`.
  - The `SortedSet` interface is usually implemented by classes like `TreeSet`, which stores elements in natural order or according to a specified comparator.

---

### 149. Can you give examples of classes that implement the `Set` interface?

There are several classes that implement the `Set` interface in Java. Some of the commonly used ones include:

1. **HashSet**:
   - Implements `Set` using a hash table. 
   - Does not maintain any order of the elements.
   - It allows **null** elements and offers constant-time performance for basic operations (like `add`, `remove`, `contains`).
   - **Example**:
     ```java
     Set<String> set = new HashSet<>();
     set.add("Apple");
     set.add("Banana");
     set.add("Apple");  // Duplicate element, will be ignored
     ```

2. **LinkedHashSet**:
   - Implements `Set` and maintains the **insertion order** of elements.
   - It uses a linked list to maintain the order in which elements were added.
   - It has a slightly slower performance compared to `HashSet` due to the additional overhead of maintaining order.
   - **Example**:
     ```java
     Set<String> set = new LinkedHashSet<>();
     set.add("Apple");
     set.add("Banana");
     set.add("Cherry");
     ```

3. **TreeSet**:
   - Implements `SortedSet` (and hence `Set`), which means it stores elements in **sorted order**.
   - The elements are sorted according to their natural ordering, or by a comparator if provided.
   - **Example**:
     ```java
     Set<Integer> set = new TreeSet<>();
     set.add(5);
     set.add(2);
     set.add(8);
     System.out.println(set);  // Output will be sorted: [2, 5, 8]
     ```

---

### 150. What is a `HashSet`?

`HashSet` is a class that implements the `Set` interface and uses a **hash table** to store its elements. It is one of the most commonly used implementations of the `Set` interface. 

#### Key features of `HashSet`:
1. **No duplicates**: It does not allow duplicate elements. If you try to add a duplicate element, the `add()` method will return `false`, and the element will not be added.
2. **No ordering**: It does not guarantee any specific order of elements. The elements are stored based on their hash codes, which means the order can be different each time you iterate through the set.
3. **Performance**: It provides constant-time performance (O(1)) for basic operations like `add()`, `remove()`, and `contains()` if the hash function is well-designed.
4. **Null elements**: It allows a `null` element to be added.

#### Example:
```java
import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");  // Duplicate, will not be added

        System.out.println(set);  // Output: [Apple, Banana, Cherry]
    }
}
```

**Output**:
```
[Apple, Banana, Cherry]
```

In this example, even though we tried to add `"Apple"` twice, it is only stored once due to the properties of the `HashSet`.

---

### Summary:
- **Related interfaces**: `SortedSet` and `NavigableSet` are specialized interfaces extending `Set`, offering sorting and navigation capabilities.
- **Difference**: `SortedSet` guarantees a sorted order, while `Set` does not guarantee any particular ordering.
- **Implementing classes**: Common classes that implement `Set` include `HashSet`, `LinkedHashSet`, and `TreeSet`.
- **HashSet**: A class that implements `Set` using a hash table. It doesn't maintain order and doesn't allow duplicates. It offers fast lookups and is commonly used for efficient set operations.

### 151. What is a `LinkedHashSet`? How is it different from a `HashSet`?

`LinkedHashSet` is a class in Java that implements the `Set` interface and is backed by a **hash table** (similar to `HashSet`) but also maintains a **linked list** to preserve the **insertion order** of elements. This means that the elements in a `LinkedHashSet` will be iterated in the order in which they were added.

#### Key Differences Between `LinkedHashSet` and `HashSet`:
1. **Ordering**:
   - **HashSet**: Does not maintain any specific order. The order of elements may vary each time you iterate over the set.
   - **LinkedHashSet**: Maintains the insertion order of elements. The order of elements remains consistent with the order in which they were added.
   
2. **Performance**:
   - **HashSet**: Typically faster for large datasets because it does not have the overhead of maintaining the insertion order.
   - **LinkedHashSet**: Slightly slower than `HashSet` due to the additional overhead of maintaining a linked list to preserve order.

3. **Use Case**:
   - Use **`HashSet`** when you don't care about the order and just need fast lookups and unique elements.
   - Use **`LinkedHashSet`** when you want to maintain the order of insertion while still ensuring uniqueness of elements.

#### Example:
```java
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");  // Duplicate, will not be added
        
        System.out.println(set);  // Output: [Apple, Banana, Cherry] (Insertion order preserved)
    }
}
```

---

### 152. What is a `TreeSet`? How is it different from a `HashSet`?

`TreeSet` is a class that implements the `NavigableSet` interface and extends `SortedSet`. It is backed by a **red-black tree** and ensures that elements are stored in a **sorted order**.

#### Key Differences Between `TreeSet` and `HashSet`:
1. **Ordering**:
   - **HashSet**: Does not maintain any order; elements are stored based on their hash code.
   - **TreeSet**: Maintains the elements in **sorted order**, either in natural order (if the elements implement `Comparable`) or according to a provided `Comparator`.
   
2. **Performance**:
   - **HashSet**: Generally provides faster `O(1)` operations for add, remove, and contains.
   - **TreeSet**: Operations like add, remove, and contains take `O(log n)` time due to the underlying tree structure.

3. **Duplicates**:
   - Both **`TreeSet`** and **`HashSet`** do not allow duplicates.

4. **Use Case**:
   - Use **`TreeSet`** when you need elements to be sorted, either by their natural order or a custom comparator.
   - Use **`HashSet`** when you don't care about the order and just need fast operations for uniqueness.

#### Example:
```java
import java.util.TreeSet;
import java.util.Set;

public class TreeSetExample {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(2);
        set.add(8);
        set.add(1);
        
        System.out.println(set);  // Output will be sorted: [1, 2, 5, 8]
    }
}
```

---

### 153. Can you give examples of implementations of `NavigableSet`?

`NavigableSet` extends `SortedSet` and provides additional navigation methods for efficiently navigating through the set. Some of the classes that implement the `NavigableSet` interface include:

1. **TreeSet**: 
   - `TreeSet` implements `NavigableSet` and stores elements in a **sorted order** based on their natural ordering or a specified comparator.
   - Example usage:
     ```java
     NavigableSet<Integer> set = new TreeSet<>();
     set.add(10);
     set.add(20);
     set.add(30);
     set.add(5);
     System.out.println(set);  // Output: [5, 10, 20, 30]
     ```

2. **ConcurrentSkipListSet**:
   - `ConcurrentSkipListSet` is another implementation of `NavigableSet`, but it is thread-safe and provides a scalable, lock-free implementation for concurrent access.
   - It is part of the `java.util.concurrent` package and is useful in multithreading scenarios.
   - Example usage:
     ```java
     NavigableSet<Integer> concurrentSet = new ConcurrentSkipListSet<>();
     concurrentSet.add(15);
     concurrentSet.add(25);
     concurrentSet.add(35);
     System.out.println(concurrentSet);  // Output: [15, 25, 35]
     ```

---

### 154. Explain briefly about the `Queue` interface?

The `Queue` interface is part of the Java Collections Framework and represents a **first-in, first-out (FIFO)** data structure. It is designed to hold elements before they are processed. The `Queue` interface provides methods for inserting, removing, and inspecting elements. 

#### Key Methods of `Queue` Interface:
- **offer(E e)**: Adds an element to the queue if possible. It returns `true` if the element is added successfully.
- **poll()**: Retrieves and removes the head of the queue. It returns `null` if the queue is empty.
- **peek()**: Retrieves but does not remove the head of the queue. It returns `null` if the queue is empty.
- **remove()**: Retrieves and removes the head of the queue. Throws an exception if the queue is empty.

#### Example:
```java
import java.util.Queue;
import java.util.LinkedList;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Apple");
        queue.offer("Banana");
        queue.offer("Cherry");
        
        System.out.println(queue.poll());  // Output: Apple
        System.out.println(queue.peek());  // Output: Banana
    }
}
```

---

### 155. What are the important interfaces related to the `Queue` interface?

Several important interfaces extend the `Queue` interface to provide specialized behavior for different types of queues:

1. **Deque (Double-Ended Queue)**:
   - Extends the `Queue` interface and allows elements to be added or removed from both ends.
   - Common implementations: `ArrayDeque`, `LinkedList`.
   - Key Methods:
     - `addFirst(E e)`: Inserts an element at the front of the deque.
     - `addLast(E e)`: Inserts an element at the end of the deque.
     - `removeFirst()`: Removes and returns the first element.
     - `removeLast()`: Removes and returns the last element.

2. **BlockingQueue**:
   - Extends `Queue` and adds support for blocking operations, useful in multithreading environments.
   - Key Methods:
     - `put(E e)`: Inserts an element into the queue, waiting if necessary for space to become available.
     - `take()`: Retrieves and removes the head of the queue, waiting if necessary for an element to become available.

3. **TransferQueue**:
   - Extends `BlockingQueue` and provides a mechanism for transferring elements between threads.
   - Key Method:
     - `transfer(E e)`: Transfers the specified element to another thread.

#### Example of Deque:
```java
import java.util.Deque;
import java.util.ArrayDeque;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Apple");
        deque.addLast("Banana");
        
        System.out.println(deque);  // Output: [Apple, Banana]
    }
}
```

---

### Summary:
- **`LinkedHashSet`**: Maintains insertion order while providing the benefits of a `HashSet` (no duplicates).
- **`TreeSet`**: Stores elements in sorted order and implements `NavigableSet`.
- **`NavigableSet` Implementations**: `TreeSet` and `ConcurrentSkipListSet` are common implementations.
- **`Queue` Interface**: Represents a FIFO data structure with methods for adding, removing, and inspecting elements.
- **Important Queue Interfaces**: `Deque`, `BlockingQueue`, and `TransferQueue` provide specialized functionality for queue operations.

### 156. Explain about the `Deque` interface?

The `Deque` interface (short for **Double-Ended Queue**) is an extension of the `Queue` interface in Java. It allows elements to be added or removed from both ends of the queue, providing more flexible access compared to a regular `Queue`, which only supports access from one end (FIFO – First In, First Out).

#### Key Features:
- **Insertion and Removal at Both Ends**: You can add elements to both the front and the back of the queue.
- **Common Implementations**: `ArrayDeque`, `LinkedList`.
- **Order**: The order of elements depends on the specific methods used for insertion (`addFirst`, `addLast`, etc.).

#### Common Methods in `Deque`:
- **addFirst(E e)**: Adds the element at the front of the deque.
- **addLast(E e)**: Adds the element at the end of the deque.
- **removeFirst()**: Removes and returns the element from the front of the deque.
- **removeLast()**: Removes and returns the element from the end of the deque.
- **peekFirst()**: Retrieves, but does not remove, the element at the front.
- **peekLast()**: Retrieves, but does not remove, the element at the end.

#### Example:
```java
import java.util.Deque;
import java.util.ArrayDeque;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Apple");
        deque.addLast("Banana");
        deque.addFirst("Cherry");
        
        System.out.println(deque);  // Output: [Cherry, Apple, Banana]
        
        System.out.println(deque.removeFirst());  // Output: Cherry
        System.out.println(deque.removeLast());   // Output: Banana
    }
}
```

---

### 157. Explain the `BlockingQueue` interface?

The `BlockingQueue` interface in Java is a part of the `java.util.concurrent` package and represents a thread-safe queue where operations like adding, removing, and inspecting elements are "blocked" under certain conditions. It is designed for use in concurrent programming where threads need to wait for conditions to be met (such as when the queue is empty or full).

#### Key Features:
- **Blocking Operations**: The methods in this interface allow threads to be blocked until certain conditions are met.
- **Producer-Consumer Problem**: It is typically used to solve the producer-consumer problem, where one or more threads produce data and others consume it.
  
#### Common Methods:
- **put(E e)**: Inserts the specified element into the queue, waiting if necessary for space to become available.
- **take()**: Retrieves and removes the head of the queue, waiting if necessary for an element to become available.
- **offer(E e, long timeout, TimeUnit unit)**: Inserts the specified element into the queue, waiting up to the specified time if necessary for space to become available.
- **poll(long timeout, TimeUnit unit)**: Retrieves and removes the head of the queue, waiting up to the specified time if necessary for an element to become available.

#### Example:
```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                queue.put("Apple");
                System.out.println("Added Apple");
                queue.put("Banana");
                System.out.println("Added Banana");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(1000);  // Wait for the producer to add elements
                System.out.println("Consumed: " + queue.take());
                System.out.println("Consumed: " + queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
```

---

### 158. What is a `PriorityQueue`?

A `PriorityQueue` is a type of queue in Java that **orders its elements based on their priority** rather than their insertion order. It is part of the `java.util` package and implements the `Queue` interface. The elements are ordered according to their natural ordering or by a comparator provided at the time of queue creation.

#### Key Features:
- **Order**: The elements in a `PriorityQueue` are not ordered by their insertion time. Instead, the queue orders elements based on their priority (using either their natural ordering or a custom comparator).
- **Heap Structure**: Internally, a `PriorityQueue` is usually implemented using a heap data structure, which makes retrieving the highest (or lowest) priority element very efficient.

#### Example:
```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Create a priority queue where the smallest number has the highest priority
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(5);
        pq.add(20);
        
        System.out.println("Priority Queue: " + pq);  // Output: [5, 10, 20]
        
        System.out.println("Poll: " + pq.poll());  // Output: 5 (the smallest element)
        System.out.println("Priority Queue: " + pq);  // Output: [10, 20]
    }
}
```

---

### 159. Can you give example implementations of the `BlockingQueue` interface?

Some common implementations of the `BlockingQueue` interface are:

1. **ArrayBlockingQueue**:
   - A bounded blocking queue backed by an array. It supports a fixed-size queue with blocking operations.
   - Example:
     ```java
     import java.util.concurrent.ArrayBlockingQueue;

     public class ArrayBlockingQueueExample {
         public static void main(String[] args) throws InterruptedException {
             ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
             
             // Producer
             new Thread(() -> {
                 try {
                     queue.put("Apple");
                     System.out.println("Produced: Apple");
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             }).start();
             
             // Consumer
             new Thread(() -> {
                 try {
                     System.out.println("Consumed: " + queue.take());
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                 }
             }).start();
         }
     }
     ```

2. **LinkedBlockingQueue**:
   - A blocking queue that is backed by a linked node structure, and it can be bounded or unbounded (if no limit is set).
   
3. **PriorityBlockingQueue**:
   - A blocking queue that orders elements based on their priority.

---

### 160. Can you briefly explain about the `Map` interface?

The `Map` interface in Java represents a collection of key-value pairs, where each key is unique, and each key maps to exactly one value. It is not a part of the `Collection` hierarchy but is part of the `java.util` package. Maps provide efficient access to values based on keys.

#### Key Features:
- **Key-Value Mapping**: Each element in a `Map` consists of a key and its associated value. You can look up a value using its corresponding key.
- **No Duplicates for Keys**: A `Map` does not allow duplicate keys. However, it can have duplicate values.
- **Common Implementations**: `HashMap`, `TreeMap`, `LinkedHashMap`, `Hashtable`.

#### Key Methods:
- **put(K key, V value)**: Adds the specified key-value pair to the map.
- **get(Object key)**: Retrieves the value associated with the specified key.
- **remove(Object key)**: Removes the key-value pair for the specified key.
- **containsKey(Object key)**: Checks if the map contains the specified key.
- **containsValue(Object value)**: Checks if the map contains the specified value.
- **keySet()**: Returns a set of all the keys in the map.
- **values()**: Returns a collection of all the values in the map.
- **entrySet()**: Returns a set of key-value pairs (entries) in the map.

#### Example:
```java
import java.util.Map;
import java.util.HashMap;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        System.out.println("Value for Apple: " + map.get("Apple"));  // Output: 1
        System.out.println("Contains key 'Banana': " + map.containsKey("Banana"));  // Output: true
        System.out.println("Contains value 3: " + map.containsValue(3));  // Output: true
    }
}
```

---

### Summary:
- **`Deque`**: A double-ended queue allowing insertion and removal of elements from both ends.
- **`BlockingQueue`**: A thread-safe queue designed for concurrent programming with blocking operations.
- **`PriorityQueue`**: A queue that orders elements based on priority (natural or custom order).
- **Implementations of `BlockingQueue`**: Common implementations include `ArrayBlockingQueue`, `LinkedBlockingQueue`, and `PriorityBlockingQueue`.
- **`Map` Interface**: Represents key-value pairs where keys are unique and map to specific values. Common implementations are `HashMap`, `TreeMap`, and `LinkedHashMap`.

### 161. What is the difference between `Map` and `SortedMap`?

- **`Map`**: The `Map` interface in Java is used to represent key-value pairs. It does not impose any specific order on its keys or values. A `Map` can be implemented by classes like `HashMap`, `LinkedHashMap`, etc. The elements in a `Map` are not necessarily sorted in any order.

- **`SortedMap`**: The `SortedMap` interface is a subtype of `Map` that maintains the keys in a sorted order. It provides additional methods for handling sorted data, such as `firstKey()`, `lastKey()`, and `subMap()`. A `SortedMap` is generally implemented by classes like `TreeMap`. It guarantees that the keys will be sorted in their natural order or according to a provided comparator.

#### Key Differences:
- **Order**: 
  - `Map`: No specific order is maintained.
  - `SortedMap`: Keys are always sorted.
- **Additional Methods**: 
  - `SortedMap` has additional methods like `firstKey()`, `lastKey()`, `subMap()`, etc., which are not present in `Map`.

---

### 162. What is a `HashMap`?

`HashMap` is a class that implements the `Map` interface in Java and is used to store key-value pairs. It uses a hash table for storage, which provides fast access to elements via their keys. The `HashMap` allows **null** values and **null** keys, and it does not guarantee any specific order of its elements.

#### Key Features:
- **Unordered**: The elements in a `HashMap` are not ordered; the order of insertion is not preserved.
- **Allowing Null Keys/Values**: A `HashMap` allows one `null` key and multiple `null` values.
- **Thread-Safety**: `HashMap` is **not thread-safe**. To make it thread-safe, you can use `Collections.synchronizedMap()` or use `ConcurrentHashMap`.

#### Example:
```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        
        System.out.println(map);  // Output: {Apple=1, Banana=2, Cherry=3}
        System.out.println("Value for Apple: " + map.get("Apple"));  // Output: 1
    }
}
```

---

### 163. What are the different methods in a `HashMap`?

A `HashMap` provides several methods to interact with key-value pairs. Some of the commonly used methods are:

1. **put(K key, V value)**: Adds a key-value pair to the map.
2. **get(Object key)**: Retrieves the value associated with the specified key.
3. **remove(Object key)**: Removes the key-value pair for the given key.
4. **containsKey(Object key)**: Checks if the map contains the specified key.
5. **containsValue(Object value)**: Checks if the map contains the specified value.
6. **size()**: Returns the number of key-value pairs in the map.
7. **clear()**: Removes all entries from the map.
8. **keySet()**: Returns a set of all the keys in the map.
9. **values()**: Returns a collection of all the values in the map.
10. **entrySet()**: Returns a set of key-value pairs (entries).
11. **putIfAbsent(K key, V value)**: Adds the key-value pair only if the key is not already present.
12. **replace(K key, V oldValue, V newValue)**: Replaces the value associated with the key if the key exists with the specified value.

---

### 164. What is a `TreeMap`? How is it different from a `HashMap`?

A `TreeMap` is a `Map` implementation in Java that maintains the keys in **sorted order**. It implements the `NavigableMap` interface and uses a **Red-Black tree** (a type of balanced binary search tree) to store the data.

#### Key Differences Between `HashMap` and `TreeMap`:
1. **Ordering**:
   - `HashMap`: Does not maintain any order of its keys.
   - `TreeMap`: Maintains the keys in a sorted order (natural ordering or by a custom comparator).
   
2. **Performance**:
   - `HashMap`: Offers constant-time complexity (O(1)) for basic operations like `get()` and `put()` under ideal conditions.
   - `TreeMap`: Provides log(n) time complexity (O(log n)) for basic operations like `get()` and `put()` due to the tree structure.

3. **Null Keys/Values**:
   - `HashMap`: Allows one `null` key and multiple `null` values.
   - `TreeMap`: Does not allow `null` keys (but allows `null` values).

#### Example:
```java
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        
        System.out.println(map);  // Output: {Apple=1, Banana=2, Cherry=3}
    }
}
```

---

### 165. Can you give an example of implementation of `NavigableMap` interface?

The `NavigableMap` interface extends the `SortedMap` interface and provides methods for navigating a map in both directions (ascending and descending order). One of the most common implementations of the `NavigableMap` interface is `TreeMap`.

#### Example using `TreeMap` (which implements `NavigableMap`):
```java
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapExample {
    public static void main(String[] args) {
        NavigableMap<String, Integer> map = new TreeMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        
        // Navigating the map
        System.out.println("First Key: " + map.firstKey());  // Output: Apple
        System.out.println("Last Key: " + map.lastKey());    // Output: Cherry
        
        // Submap from "Banana" onwards
        System.out.println("Submap from Banana: " + map.tailMap("Banana"));
        
        // Descending map
        System.out.println("Descending Map: " + map.descendingMap());
    }
}
```

---

### 166. What are the static methods present in the `Collections` class?

The `Collections` class in Java provides several static utility methods that make working with collections easier. Some of the key static methods include:

1. **sort(List<T> list)**: Sorts the elements of the list in natural order.
2. **sort(List<T> list, Comparator<? super T> c)**: Sorts the elements of the list according to the specified comparator.
3. **reverse(List<?> list)**: Reverses the order of elements in the list.
4. **shuffle(List<?> list)**: Randomly permutes the elements of the list.
5. **min(Collection<? extends T> coll)**: Returns the minimum element in the collection according to its natural ordering.
6. **max(Collection<? extends T> coll)**: Returns the maximum element in the collection according to its natural ordering.
7. **frequency(Collection<?> c, Object o)**: Returns the frequency of the specified element in the collection.
8. **copy(List<? super T> dest, List<? extends T> src)**: Copies all elements from the source list to the destination list.
9. **addAll(Collection<? super T> c, T... elements)**: Adds all elements to the specified collection.
10. **unmodifiableList(List<? extends T> list)**: Returns an unmodifiable view of the specified list.
11. **synchronizedList(List<T> list)**: Returns a synchronized (thread-safe) list backed by the specified list.

#### Example:
```java
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class CollectionsExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Sort the list
        Collections.sort(list);
        System.out.println("Sorted List: " + list);  // Output: [Apple, Banana, Cherry]

        // Reverse the list
        Collections.reverse(list);
        System.out.println("Reversed List: " + list);  // Output: [Cherry, Banana, Apple]

        // Find the minimum
        System.out.println("Min Element: " + Collections.min(list));  // Output: Apple
    }
}
```

---

### Summary:
- **`Map` vs `SortedMap`**: `SortedMap` maintains keys in sorted order, while `Map` does not.
- **`HashMap`**: A hash table-based implementation of `Map`, does not maintain order.
- **`TreeMap`**: A `Map` implementation that maintains keys in sorted order.
- **`NavigableMap`**: A `SortedMap` extension that provides additional navigation methods, such as `firstKey()`, `lastKey()`, and `subMap()`.
- **Static methods in `Collections`**: Utility methods for sorting, reversing, copying, and performing other operations on collections.

## Advanced collections
### 167. What is the difference between **synchronized** and **concurrent collections** in Java?

- **Synchronized Collections**: 
  - Synchronized collections are traditional collections like `Vector`, `Hashtable`, and collections wrapped by `Collections.synchronizedList()`, `Collections.synchronizedMap()`, etc. These collections ensure that all operations on them are thread-safe. However, synchronization happens at the level of individual methods, which can result in performance overhead due to contention between threads when accessing these methods. Multiple threads can hold locks on different methods at the same time, leading to potential inefficiency when threads need to perform a sequence of operations atomically.
  
- **Concurrent Collections**: 
  - Concurrent collections (introduced in Java 5 with the `java.util.concurrent` package) are designed for high concurrency. They provide thread-safe operations but also aim to reduce contention and increase performance when multiple threads are involved. Examples include `ConcurrentHashMap`, `CopyOnWriteArrayList`, and `BlockingQueue`. These collections provide more fine-grained locking or lock-free mechanisms (e.g., using `CAS` - Compare And Swap) to optimize performance and ensure thread safety with minimal overhead.

#### Key Differences:
- **Synchronization Mechanism**: Synchronized collections lock the entire collection or method for thread safety, which may cause contention and reduce performance. Concurrent collections employ advanced techniques like segment-based locking, atomic operations, or lock-free algorithms to provide better concurrency.
- **Performance**: Concurrent collections generally offer better performance in multithreaded environments due to reduced contention.

---

### 168. Explain about the new **concurrent collections** in Java?

Java provides several concurrent collections in the `java.util.concurrent` package designed for high concurrency. Some key concurrent collections are:

1. **`ConcurrentHashMap`**: 
   - This class provides a thread-safe, highly concurrent map implementation. It uses fine-grained locking (locks on segments) to allow multiple threads to read and write concurrently without locking the entire map. It performs better than `Hashtable` or `Collections.synchronizedMap()` in multithreaded scenarios.
   - Example:
   ```java
   import java.util.concurrent.ConcurrentHashMap;
   public class ConcurrentHashMapExample {
       public static void main(String[] args) {
           ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
           map.put("Apple", 1);
           map.put("Banana", 2);
           System.out.println(map);
       }
   }
   ```

2. **`CopyOnWriteArrayList`**: 
   - This list implementation is thread-safe for iteration. It copies the entire array when an element is modified (e.g., `add()`, `remove()`), which makes it ideal for scenarios with frequent reads and rare writes.
   - Example:
   ```java
   import java.util.concurrent.CopyOnWriteArrayList;
   public class CopyOnWriteExample {
       public static void main(String[] args) {
           CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
           list.add("Apple");
           list.add("Banana");
           list.add("Cherry");
           System.out.println(list);
       }
   }
   ```

3. **`BlockingQueue`**: 
   - A `BlockingQueue` is a thread-safe queue that supports operations like `put()` and `take()` that block the thread if the queue is full or empty. It’s useful in producer-consumer scenarios.
   - Example:
   ```java
   import java.util.concurrent.ArrayBlockingQueue;
   public class BlockingQueueExample {
       public static void main(String[] args) throws InterruptedException {
           ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
           queue.put("Apple");
           String item = queue.take();
           System.out.println(item);
       }
   }
   ```

4. **`BlockingDeque`**: 
   - It extends `Deque` and adds blocking methods for queues where elements can be added and removed from both ends.

---

### 169. Explain about **CopyOnWrite** concurrent collections approach?

The **CopyOnWrite** approach is used in collections like `CopyOnWriteArrayList` and `CopyOnWriteArraySet`. In this approach, every time a write operation (such as `add()`, `remove()`, or `set()`) is performed, the entire underlying data structure (array or set) is copied to a new array. After that, the write operation is applied to the new array, and the reference to the underlying data structure is updated atomically. 

#### Key Features of CopyOnWrite Collections:
- **Thread-Safe Iteration**: Since the underlying data structure is copied on write, threads that are iterating over the collection see a consistent snapshot of the data. They do not experience the risk of seeing inconsistent or partially updated data during iteration.
- **Performance Trade-off**: It is optimal for use cases where reads are frequent, but writes (modifications) are rare. Write operations are expensive because they involve copying the entire data structure.
- **No Locking**: Copy-on-write collections do not require locks for reading, so they perform well in highly concurrent environments where reading operations significantly outnumber writes.

#### Example:
```java
import java.util.concurrent.CopyOnWriteArrayList;
public class CopyOnWriteExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Iterating while modifying the list
        for (String item : list) {
            System.out.println(item);
            if (item.equals("Banana")) {
                list.remove("Banana");  // Will not affect iteration
            }
        }
    }
}
```

---

### 170. What is the **CompareAndSwap (CAS)** approach?

**Compare-And-Swap (CAS)** is a low-level atomic operation used to achieve concurrency without locking. It is a hardware-level operation supported by modern CPUs. CAS compares the value of a variable to a given value and, if they are equal, updates the variable with a new value. This operation is atomic, meaning it is performed as a single, indivisible action, preventing race conditions.

#### How CAS works:
1. It compares the current value of a variable with an expected value.
2. If they match, it swaps the current value with a new value.
3. If they don't match, it does nothing and the thread can retry.

CAS is often used in concurrent programming to implement lock-free data structures and algorithms. It is a foundation for many high-performance concurrent collections in Java, such as `AtomicInteger`, `AtomicReference`, and `AtomicLong`.

---

### 171. What is a **lock**? How is it different from using **synchronized** approach?

- **Lock**: A `Lock` in Java (like `ReentrantLock`, `ReadWriteLock`, etc.) is a more advanced and flexible concurrency tool than the `synchronized` keyword. A `Lock` allows finer control over synchronization, such as trying to acquire a lock without blocking indefinitely (using `tryLock()`), interrupting the thread while it is waiting for a lock (`lockInterruptibly()`), and ensuring that multiple threads can acquire read and write locks concurrently (with `ReadWriteLock`).

#### Key Differences between `Lock` and `synchronized`:
1. **Flexibility**:
   - **`synchronized`**: Automatically acquires and releases a lock when entering and exiting a synchronized block or method. It cannot be interrupted or tried for non-blocking execution.
   - **`Lock`**: Provides more control, such as non-blocking attempts to acquire a lock (`tryLock()`) and the ability to interrupt threads waiting for a lock.

2. **Reentrant Capability**:
   - **`synchronized`**: Allows the thread that currently holds the lock to enter the synchronized block/method again.
   - **`Lock`**: Most `Lock` implementations (like `ReentrantLock`) are reentrant, meaning the same thread can acquire the lock again.

3. **Fairness**:
   - **`synchronized`**: Does not guarantee any specific order of acquiring the lock.
   - **`Lock`**: Some implementations, like `ReentrantLock`, can be configured to be "fair", meaning that threads acquire the lock in the order they requested it (FIFO).

#### Example:
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static final Lock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        lock.lock();
        try {
            // Critical section code
            System.out.println("Executing critical section");
        } finally {
            lock.unlock();
        }
    }
}
```

In contrast, with the `synchronized` approach, you would write:

```java
public class SynchronizedExample {
    public synchronized void method() {
        // Critical section code
    }
}
```

### Summary:
- **Synchronized vs Concurrent Collections**: Synchronized collections lock entire methods or collections, while concurrent collections (e.g., `ConcurrentHashMap`) use fine-grained locking for better performance in multithreaded environments.
- **CopyOnWrite**: A collection strategy where writes create a copy of the underlying structure to maintain thread safety during reads.
- **CAS**: A low-level atomic operation that helps in building lock-free data structures.
- **Lock vs Synchronized**: `Lock` provides more control and flexibility than `synchronized` blocks, such as support for non-blocking and interruptible lock attempts.

### 172. What is the **initial capacity** of a Java collection?

The **initial capacity** of a Java collection refers to the initial size or amount of memory allocated when the collection is created. This value is used to avoid resizing when the collection grows. For example, an `ArrayList` has an initial capacity, which can be set when the list is created. If elements are added beyond the initial capacity, the collection will automatically resize (typically doubling its size) to accommodate the new elements.

- Example for `ArrayList`:
  ```java
  ArrayList<Integer> list = new ArrayList<>(20);  // Initial capacity is 20
  ```

The initial capacity helps optimize performance by minimizing the number of times the collection needs to resize when adding elements.

---

### 173. What is **load factor**?

The **load factor** is a measure of how full a collection can get before it needs to resize. In the context of `HashMap` or `HashSet`, the load factor determines when the collection will grow. The default load factor is typically 0.75, meaning the collection will resize when it is 75% full.

- **Formula**: 
  - When the number of entries exceeds `capacity * loadFactor`, the collection is resized.
  
- Example:
  ```java
  HashMap<String, Integer> map = new HashMap<>(16, 0.75f);  // initial capacity of 16 and load factor of 0.75
  ```

A higher load factor reduces the number of resizes but may increase collisions and degrade lookup performance.

---

### 174. When does a Java collection throw **UnsupportedOperationException**?

A Java collection will throw an **`UnsupportedOperationException`** when you attempt to perform an operation that is not supported by that collection type. This commonly happens with immutable collections, or with collections that do not support certain modifications.

- **Common scenarios**:
  1. **Immutable collections**: For example, an unmodifiable list created using `Collections.unmodifiableList()`.
  2. **Unsupported operations in certain collection types**: For example, calling `add()`, `remove()`, or `clear()` on a fixed-size list created by `Arrays.asList()`.

- Example:
  ```java
  List<String> list = Arrays.asList("A", "B", "C");
  list.add("D");  // Throws UnsupportedOperationException
  ```

---

### 175. What is the difference between **fail-safe** and **fail-fast** iterators?

**Fail-fast** and **fail-safe** iterators refer to how iterators behave when the underlying collection is modified during iteration.

1. **Fail-fast iterator**:
   - A fail-fast iterator will throw a **`ConcurrentModificationException`** if the collection is modified (except through the iterator itself) during iteration. This is to prevent unpredictable behavior or incorrect results due to concurrent modification.
   - Example: `ArrayList`, `HashMap` have fail-fast iterators.
   - **Fail-fast** occurs when you modify the collection structurally (e.g., adding/removing elements) while iterating.

2. **Fail-safe iterator**:
   - A fail-safe iterator works with a copy of the collection and allows concurrent modifications. It does not throw exceptions if the collection is modified during iteration, but the changes made to the collection may not be reflected during the iteration.
   - Example: `CopyOnWriteArrayList`, `ConcurrentHashMap` have fail-safe iterators.
   - The fail-safe iterator is typically used with collections designed for high concurrency.

---

### 176. What are **atomic operations** in Java?

**Atomic operations** in Java are operations that are performed as a single, indivisible step. In other words, the operation is guaranteed to complete fully without interruption, ensuring that no other thread can interfere during execution. Atomic operations are crucial for thread safety when dealing with shared resources.

Examples of atomic operations:
1. **Atomic classes** in `java.util.concurrent.atomic` package (e.g., `AtomicInteger`, `AtomicLong`, `AtomicReference`) provide atomic methods like `get()`, `set()`, `compareAndSet()`, etc.
   - Example:
     ```java
     AtomicInteger counter = new AtomicInteger(0);
     counter.incrementAndGet();  // Atomic operation
     ```
2. **Compare-And-Swap (CAS)**: This is an atomic operation where the current value is compared with an expected value, and if they match, it is updated to a new value.
   
---

### 177. What is **BlockingQueue** in Java?

A **BlockingQueue** is an interface that represents a thread-safe queue that supports operations that block when the queue is full (during enqueue operations) or empty (during dequeue operations). It is part of the `java.util.concurrent` package, and it is typically used in producer-consumer scenarios where one thread produces data and another consumes it.

- **Key Operations**:
  1. **put(E e)**: Inserts the specified element into the queue, waiting if necessary for space to become available.
  2. **take()**: Retrieves and removes the head of the queue, waiting if necessary until an element becomes available.
  3. **offer(E e)**: Attempts to insert the element but does not block. Returns `true` if successful, `false` if the queue is full.
  4. **poll()**: Retrieves and removes the head of the queue, but does not block. Returns `null` if the queue is empty.
  
- **Example**:
  ```java
  import java.util.concurrent.ArrayBlockingQueue;
  public class BlockingQueueExample {
      public static void main(String[] args) throws InterruptedException {
          BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
          queue.put(1);  // Blocks if queue is full
          System.out.println(queue.take());  // Blocks if queue is empty
      }
  }
  ```

- Common implementations of `BlockingQueue`:
  1. `ArrayBlockingQueue`: A bounded blocking queue.
  2. `LinkedBlockingQueue`: An optionally bounded queue with a linked list structure.
  3. `PriorityBlockingQueue`: A blocking queue with a priority ordering.

---

### Summary:

- **Initial Capacity**: Refers to the initial size or allocated memory when a collection is created.
- **Load Factor**: Determines when a collection will resize. A higher load factor reduces resizing but may degrade performance.
- **UnsupportedOperationException**: Thrown when an unsupported operation is invoked on a collection.
- **Fail-fast vs Fail-safe**: Fail-fast iterators throw `ConcurrentModificationException` on structural modification, while fail-safe iterators do not throw such exceptions.
- **Atomic Operations**: Operations that are indivisible, ensuring thread safety during concurrent access.
- **BlockingQueue**: A thread-safe queue that supports blocking operations, used in scenarios like producer-consumer.

## Generics
### 178. What are **Generics**?

**Generics** in Java enable the creation of classes, interfaces, and methods with a placeholder for the type of data they operate on. Generics allow for type-safe code and help prevent `ClassCastException` by enforcing type constraints at compile time. This provides more flexibility and code reusability, as the same code can work with different data types.

- For example, a generic class `Box` can hold any type of object, not just one specific type.
  
  ```java
  public class Box<T> {
      private T value;
      
      public T getValue() {
          return value;
      }

      public void setValue(T value) {
          this.value = value;
      }
  }
  ```

Here, `T` is a generic type parameter that will be replaced with an actual type when the class is instantiated (e.g., `Box<Integer>`).

---

### 179. Why do we need **Generics**? Can you give an example of how Generics make a program more flexible?

Generics provide several benefits:

1. **Type safety**: Generics ensure that only objects of a specified type are used in collections, reducing runtime errors.
2. **Code reusability**: You can use the same class or method to operate on different types of objects, reducing redundancy.
3. **Eliminate casting**: Without generics, you would often need to cast objects when retrieving them from collections, leading to potential errors.

**Example of Generics improving flexibility**:
Before Generics, you would need to use `Object` for a collection and cast elements back to the desired type:

```java
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0);  // Requires casting, error-prone
```

With Generics, you can ensure type safety:

```java
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0);  // No casting required, type-safe
```

Generics eliminate the need for casting and ensure that the list only contains `String` objects.

---

### 180. How do you declare a **generic class**?

To declare a **generic class**, you place a type parameter in angle brackets (`<>`) after the class name. This allows the class to accept different types of objects when instantiated.

**Example** of a generic class declaration:

```java
public class Box<T> {  // T is the generic type parameter
    private T value;
    
    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

When creating an instance of this class, you specify the type:

```java
Box<Integer> intBox = new Box<>();
Box<String> strBox = new Box<>();
```

In this case, `T` can be any type (e.g., `Integer`, `String`).

---

### 181. What are the restrictions in using a generic type that is declared in a **class declaration**?

The main restrictions when using a generic type in a class declaration include:

1. **Cannot create instances of a generic type**: You cannot directly create an instance of a generic type parameter (e.g., `new T()` is not allowed).
   
   - **Example**:
     ```java
     public class Box<T> {
         T value;
         // Cannot do: T value = new T(); // Compilation error
     }
     ```

2. **No static references**: You cannot use a generic type parameter in static fields or static methods because static fields/methods are shared across all instances of the class, and the type parameter is specific to each instance.

   - **Example**:
     ```java
     public class Box<T> {
         private static T staticValue; // Compilation error
     }
     ```

---

### 182. How can we **restrict Generics** to a **subclass** of a particular class?

You can restrict a generic type to a subclass of a particular class by using the **extends** keyword. This creates an upper bound on the type.

**Example**:

```java
class Animal {}
class Dog extends Animal {}

public class Box<T extends Animal> {  // Restricts T to subclasses of Animal
    private T value;
    
    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

In this case, `T` can be `Animal` or any subclass of `Animal` (e.g., `Dog`).

---

### 183. How can we **restrict Generics** to a **superclass** of a particular class?

To restrict a generic type to a superclass of a particular class, you can use the `super` keyword in conjunction with wildcards (`?`).

**Example**:

```java
class Animal {}
class Dog extends Animal {}

public class Box<T> {
    public void addItemToBox(T item) {
        // Do something
    }
}

// Using super keyword
public class Test {
    public static void main(String[] args) {
        Box<? super Dog> box = new Box<>();
        box.addItemToBox(new Dog()); // Allowed: Dog is a subclass of Animal
        // box.addItemToBox(new Animal()); // Not allowed: Animal is not a supertype of Dog
    }
}
```

This restricts `T` to any class that is a superclass of `Dog` (e.g., `Animal` or `Object`).

---

### 184. Can you give an example of a **generic method**?

A **generic method** is a method that can operate on objects of any type. You can define a generic method by placing a type parameter before the return type.

**Example** of a generic method:

```java
public class Utility {

    // Generic method to print an array of any type
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        String[] strArray = {"Hello", "World"};
        
        printArray(intArray);  // Generic method used for Integer array
        printArray(strArray);  // Generic method used for String array
    }
}
```

In this example, the method `printArray` is generic and can accept arrays of any type (`T`).

---

### Summary:

- **Generics** allow creating flexible, reusable, and type-safe code.
- **Why use Generics?**: They help eliminate casting and improve type safety by allowing methods and classes to operate on different data types while ensuring correctness.
- **Declaring Generic Classes**: Use `<T>` to declare a type parameter in the class definition.
- **Restrictions**: Generics cannot be instantiated directly (e.g., `new T()`) and cannot be used in static fields/methods.
- **Restricting Generics**: Use `extends` for a subclass or `super` for a superclass.
- **Generic Methods**: Methods can also be generic, allowing you to perform operations on different types of data.

## Multi threading
### 185. What is the need for **threads** in Java?

Threads are needed in Java to execute tasks concurrently, which allows for better performance and responsiveness in applications. They help in:

1. **Parallel execution**: Threads allow multiple operations to be performed at the same time, which can improve performance, especially on multi-core processors.
2. **Improved responsiveness**: In GUI applications, threads help to keep the interface responsive by delegating long-running tasks (like file loading) to separate threads.
3. **Better resource utilization**: Threads can take advantage of idle CPU time or I/O operations.
4. **Concurrency**: Threads help to perform multiple tasks at the same time within a single program, such as downloading files, processing user input, and updating UI simultaneously.

---

### 186. How do you create a **thread**?

There are two primary ways to create a thread in Java:

1. **By extending the `Thread` class**
2. **By implementing the `Runnable` interface**

Both approaches allow you to define the task the thread should perform.

---

### 187. How do you create a thread by **extending the Thread class**?

To create a thread by extending the `Thread` class, follow these steps:

1. **Extend the `Thread` class**.
2. **Override the `run()` method** to define the task to be executed by the thread.
3. **Create an instance** of the class and call the `start()` method to begin the thread's execution.

**Example**:

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();  // Create a thread
        thread.start();  // Start the thread
    }
}
```

In this example, the `MyThread` class extends `Thread` and overrides the `run()` method. The `start()` method is called to execute the `run()` method in a new thread.

---

### 188. How do you create a thread by **implementing the Runnable interface**?

To create a thread by implementing the `Runnable` interface, follow these steps:

1. **Implement the `Runnable` interface**.
2. **Override the `run()` method** to define the task to be executed.
3. **Create a `Thread` object** and pass the `Runnable` object to the `Thread` constructor.
4. **Start the thread** using the `start()` method of the `Thread` class.

**Example**:

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread is running");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();  // Create a Runnable object
        Thread thread = new Thread(myRunnable);  // Create a Thread using Runnable
        thread.start();  // Start the thread
    }
}
```

Here, the `MyRunnable` class implements `Runnable` and overrides the `run()` method. A `Thread` object is created with the `Runnable` object and started.

---

### 189. How do you **run a thread** in Java?

To run a thread in Java, you need to follow these steps:

1. **Create a thread** either by extending the `Thread` class or by implementing the `Runnable` interface.
2. **Start the thread** by calling the `start()` method.

The `start()` method does not directly run the `run()` method; instead, it causes the JVM to invoke the `run()` method on a new thread.

**Example using `Thread` class**:
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();  // Create thread object
        thread.start();  // Start the thread
    }
}
```

**Example using `Runnable` interface**:
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread is running");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);  // Pass Runnable to Thread
        thread.start();  // Start the thread
    }
}
```

The `start()` method initiates a new thread of execution, invoking the `run()` method of either the `Thread` class or the `Runnable` object.

### 190. What are the different **states of a thread**?

A thread in Java can be in one of the following states during its lifecycle:

1. **New**: When a thread is created but not yet started. The thread is in the "new" state.
   - Example: `Thread t = new Thread();`
   
2. **Runnable**: After the `start()` method is invoked, the thread enters the runnable state. It is ready to run, but may not be executing immediately, depending on the thread scheduler.
   - Example: `t.start();`

3. **Blocked**: A thread enters this state when it is waiting for a monitor lock to enter a synchronized block of code. It remains blocked until it acquires the necessary lock.
   - Example: If two threads are trying to enter a synchronized method or block simultaneously.

4. **Waiting**: A thread enters this state when it is waiting indefinitely for another thread to perform a particular action. This happens when `wait()` is called.
   - Example: `Thread.sleep()` or `wait()` in a synchronized method.

5. **Timed Waiting**: A thread enters this state when it is waiting for a specified period. It can transition back to the runnable state once the specified time expires.
   - Example: `Thread.sleep(1000)` or `Thread.join(1000)`.

6. **Terminated (Dead)**: A thread is in the terminated state when it has finished executing its `run()` method or when it has been explicitly stopped. After this, it cannot be started again.
   - Example: The `run()` method has completed.

---

### 191. What is **priority of a thread**? How do you change the priority of a thread?

The **priority of a thread** is a number that indicates the relative importance of the thread in comparison to others. The thread scheduler uses this value to decide which thread to execute next when there are multiple threads in a runnable state. The priority can range from **Thread.MIN_PRIORITY (1)** to **Thread.MAX_PRIORITY (10)**, with **Thread.NORM_PRIORITY (5)** being the default.

#### How to change the priority of a thread?

You can change the priority of a thread by using the `setPriority(int priority)` method.

**Example**:
```java
Thread thread = new Thread();
thread.setPriority(Thread.MAX_PRIORITY);  // Set the thread's priority to maximum
thread.start();
```

---

### 192. What is **ExecutorService**?

`ExecutorService` is an interface in Java that provides a higher-level replacement for managing and controlling thread execution. It provides methods to manage the lifecycle of threads and submit tasks to be executed by a pool of worker threads, handling the low-level details of thread management.

Some important methods in the `ExecutorService` interface include:
- `submit()`: Submits a task for execution and returns a `Future` object.
- `invokeAll()`: Executes a collection of tasks and waits for all to complete.
- `shutdown()`: Initiates an orderly shutdown of the ExecutorService in which previously submitted tasks are executed, but no new tasks will be accepted.
- `shutdownNow()`: Attempts to stop all actively executing tasks and halts the processing of waiting tasks.

---

### 193. Can you give an example for **ExecutorService**?

Here is an example where `ExecutorService` is used to manage a pool of threads to execute tasks concurrently:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is executing the task.");
    }
}

public class ExecutorServiceExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit 5 tasks for execution
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyTask());
        }

        // Shut down the executor service
        executorService.shutdown();
    }
}
```

In this example, an `ExecutorService` is used to create a pool of 3 threads that execute 5 tasks concurrently.

---

### 194. Explain different ways of creating **Executor Services**.

Java provides different ways to create an `ExecutorService`, which can be done using factory methods provided by the `Executors` class:

1. **Fixed Thread Pool**: 
   A fixed number of threads are used to execute tasks. The thread pool size is set at the time of creation.
   
   ```java
   ExecutorService executor = Executors.newFixedThreadPool(5);
   ```
   This creates a thread pool with 5 threads.

2. **Cached Thread Pool**:
   A thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available. This is ideal for applications where many short-lived tasks are executed.

   ```java
   ExecutorService executor = Executors.newCachedThreadPool();
   ```

3. **Single Thread Executor**:
   It creates a pool with a single worker thread. It is useful when you want to ensure that only one thread executes tasks at any given time.

   ```java
   ExecutorService executor = Executors.newSingleThreadExecutor();
   ```

4. **Scheduled Thread Pool**:
   It creates a thread pool that can schedule tasks with fixed-rate or fixed-delay execution policies.

   ```java
   ExecutorService executor = Executors.newScheduledThreadPool(3);
   ```

In addition to these, you can also create a custom `ExecutorService` using `ThreadPoolExecutor`, which gives you more control over thread pool settings (such as core pool size, maximum pool size, etc.). Here's an example:

```java
ExecutorService executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
```

### 195. How do you check whether an **ExecutorService** task executed successfully?

To check whether a task executed successfully in an `ExecutorService`, you can use the `Future` object returned by the `submit()` method. The `Future` object provides methods like `get()`, `cancel()`, and `isDone()` to check the execution status of the task.

- **`get()`**: This method waits for the task to complete and returns the result. If the task completes normally, it returns the result; if the task threw an exception, it will throw a `ExecutionException`.

- **`isDone()`**: Returns `true` if the task has completed, regardless of whether it finished normally, was cancelled, or threw an exception.

- **`isCancelled()`**: Returns `true` if the task was cancelled before completion.

#### Example:
```java
import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            // Simulate task execution
            return 42;
        });
        
        try {
            // Check if task is done and get the result
            Integer result = future.get(); // blocks until result is available
            System.out.println("Task completed successfully with result: " + result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```

In this case, `future.get()` will return the result if the task completed successfully.

---

### 196. What is **Callable**? How do you execute a **Callable** from **ExecutorService**?

A **`Callable`** is similar to `Runnable`, but it can return a result and throw an exception. It is often used when you need to execute a task that returns a result, such as in a parallel computation.

- A `Callable` task returns a value of type `T` (e.g., `Integer`, `String`, etc.), unlike `Runnable` which does not return a value.
- You execute a `Callable` using the `submit()` method of `ExecutorService`, which returns a `Future<T>` object.

#### Example of executing a `Callable`:
```java
import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<Integer> task = () -> {
            // Simulate some work and return a result
            return 42;
        };

        Future<Integer> future = executor.submit(task); // Submitting the callable task

        try {
            // Retrieving the result of the callable task
            Integer result = future.get();
            System.out.println("Callable result: " + result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```

In this example, the `Callable` returns an integer result, which is accessed using `future.get()`.

---

### 197. What is **synchronization of threads**?

**Synchronization** in Java is a mechanism that ensures that only one thread can access a critical section of code (usually shared resources or variables) at a time. It is used to prevent data inconsistency caused by multiple threads accessing shared resources concurrently.

Java provides the `synchronized` keyword to ensure that only one thread at a time can execute a block of code or method. This prevents race conditions where multiple threads may modify shared data simultaneously.

There are two types of synchronization:
- **Method-level synchronization**: When you use `synchronized` on a method.
- **Block-level synchronization**: When you use `synchronized` on a specific block of code within a method.

---

### 198. Can you give an **example of a synchronized block**?

A **synchronized block** is a section of code that is synchronized, ensuring that only one thread can execute it at a time. This is used to protect a critical section of code that accesses shared resources.

#### Example:
```java
public class SynchronizedBlockExample {
    private int counter = 0;

    public void increment() {
        // Synchronizing only the critical section of code
        synchronized(this) {
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        SynchronizedBlockExample example = new SynchronizedBlockExample();
        // Simulating multiple threads accessing the increment method
        Thread t1 = new Thread(() -> example.increment());
        Thread t2 = new Thread(() -> example.increment());

        t1.start();
        t2.start();
    }
}
```

In this example, the `increment()` method synchronizes access to the `counter` variable, ensuring that only one thread can increment the counter at a time.

---

### 199. Can a **static method** be synchronized?

Yes, a **static method** can be synchronized. In the case of a static method, the synchronization applies to the class-level lock (i.e., the lock on the `Class` object), rather than an instance-level lock (which is used for instance methods).

#### Example:
```java
public class SynchronizedStaticExample {
    private static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(SynchronizedStaticExample::increment);
        Thread t2 = new Thread(SynchronizedStaticExample::increment);

        t1.start();
        t2.start();
    }
}
```


### 200. What is the use of **join()** method in threads?

The **`join()`** method in Java is used to pause the execution of the current thread until the thread on which it is called has finished executing. In other words, it allows one thread to wait for another thread to complete.

#### Example:
```java
class MyThread extends Thread {
    public void run() {
        try {
            Thread.sleep(1000);  // Simulate some task
            System.out.println("Thread finished execution.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JoinExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();

        try {
            // Main thread waits for t1 and t2 to finish
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finishes.");
    }
}
```

In this example, `t1.join()` and `t2.join()` ensure that the main thread will wait for both `t1` and `t2` to finish before continuing with the rest of the code.

---

### 201. Describe a few other important methods in threads?

Some important methods in the `Thread` class are:

1. **`start()`**: Starts the execution of the thread by calling its `run()` method.
   - Example: `myThread.start();`
   
2. **`run()`**: Contains the code that defines the thread's task. This method is executed when the thread is started.
   - Example: 
   ```java
   public void run() {
       // thread's task
   }
   ```

3. **`sleep(long millis)`**: Pauses the execution of the current thread for the specified time in milliseconds.
   - Example: `Thread.sleep(2000);` (Pauses for 2 seconds)

4. **`interrupt()`**: Interrupts the thread, causing it to stop its execution. If the thread is blocked on `sleep()`, `wait()`, or `join()`, it throws an `InterruptedException`.
   - Example: `myThread.interrupt();`

5. **`isAlive()`**: Returns `true` if the thread is still alive, meaning it has been started but not yet finished.
   - Example: `boolean isAlive = myThread.isAlive();`

6. **`setPriority(int priority)`**: Sets the priority of the thread. It can be an integer between `Thread.MIN_PRIORITY` (1) and `Thread.MAX_PRIORITY` (10).
   - Example: `myThread.setPriority(Thread.MAX_PRIORITY);`

---

### 202. What is a **deadlock**?

A **deadlock** occurs when two or more threads are blocked forever, waiting for each other to release resources that they need to continue. This situation typically arises when two threads have circular dependencies on locks, meaning each thread holds a lock and is waiting to acquire the lock held by the other.

#### Example of a deadlock:
```java
class A {
    synchronized void methodA(B b) {
        System.out.println("Thread A trying to call B's method.");
        b.last();
    }

    synchronized void last() {
        System.out.println("Inside A's last method.");
    }
}

class B {
    synchronized void methodB(A a) {
        System.out.println("Thread B trying to call A's method.");
        a.last();
    }

    synchronized void last() {
        System.out.println("Inside B's last method.");
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        Thread t1 = new Thread(() -> a.methodA(b));
        Thread t2 = new Thread(() -> b.methodB(a));

        t1.start();
        t2.start();
    }
}
```
In this example, thread `t1` holds a lock on `a` and tries to call `b.last()`, while thread `t2` holds a lock on `b` and tries to call `a.last()`. Both threads are waiting for each other, resulting in a deadlock.

---

### 203. What are the important methods in Java for **inter-thread communication**?

Java provides several methods for inter-thread communication to allow threads to communicate and synchronize their actions. These methods are mainly part of the `Object` class and are used for thread synchronization:

1. **`wait()`**: Causes the current thread to release the monitor (lock) and enter the waiting state until another thread calls `notify()` or `notifyAll()` on the same object.
   - Example: `synchronized (obj) { obj.wait(); }`

2. **`notify()`**: Wakes up a single thread that is waiting on the object's monitor. If multiple threads are waiting, one of them will be chosen arbitrarily.
   - Example: `synchronized (obj) { obj.notify(); }`

3. **`notifyAll()`**: Wakes up all threads that are waiting on the object's monitor.
   - Example: `synchronized (obj) { obj.notifyAll(); }`

These methods are often used in producer-consumer scenarios, where one thread is producing data and another is consuming it. The producer thread can `notify()` or `notifyAll()` the consumer thread to signal that data is available, while the consumer thread can use `wait()` to pause execution until data is available.

#### Example of inter-thread communication:
```java
class SharedData {
    private int data;
    private boolean isDataAvailable = false;

    // Producer thread
    public synchronized void produce(int newData) throws InterruptedException {
        while (isDataAvailable) {
            wait();  // Wait until consumer consumes data
        }
        data = newData;
        isDataAvailable = true;
        notify();  // Notify consumer that data is available
    }

    // Consumer thread
    public synchronized int consume() throws InterruptedException {
        while (!isDataAvailable) {
            wait();  // Wait until producer produces data
        }
        isDataAvailable = false;
        notify();  // Notify producer that data has been consumed
        return data;
    }
}

public class InterThreadCommunicationExample {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        
        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    sharedData.produce(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    int data = sharedData.consume();
                    System.out.println("Consumed: " + data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
```


### 204. What is the use of **`wait()`** method?

The **`wait()`** method in Java is used for inter-thread communication. It causes the current thread to release the monitor (lock) and enter the waiting state. The thread remains in the waiting state until another thread sends a notification using either the **`notify()`** or **`notifyAll()`** method on the same object. It is commonly used in synchronization scenarios where a thread needs to wait for another thread to complete an action before continuing its own execution.

- **Usage**: Typically used within a **synchronized block** to release the lock temporarily and allow other threads to execute.

#### Example:
```java
synchronized (obj) {
    while (condition) {
        obj.wait();  // Thread releases the lock and waits for notification
    }
}
```

---

### 205. What is the use of **`notify()`** method?

The **`notify()`** method is used to wake up one thread that is waiting on the monitor (lock) of the object. If multiple threads are waiting on the same object, only one thread is selected arbitrarily to be awakened. The thread that is notified can continue execution after being awakened.

- **Usage**: Typically called within a **synchronized block** to wake up a waiting thread.

#### Example:
```java
synchronized (obj) {
    obj.notify();  // Wakes up one thread that is waiting on the object
}
```

---

### 206. What is the use of **`notifyAll()`** method?

The **`notifyAll()`** method is used to wake up all threads that are waiting on the monitor (lock) of the object. It is useful when you have multiple threads waiting for a condition and want to notify all threads that they can check the condition again and proceed.

- **Usage**: Typically called within a **synchronized block** to wake up all threads waiting on the object.

#### Example:
```java
synchronized (obj) {
    obj.notifyAll();  // Wakes up all threads waiting on the object
}
```

---

### 207. Can you write a synchronized program with **`wait()`** and **`notify()`** methods?

Yes! Here's an example that demonstrates the use of `wait()` and `notify()` methods in a synchronized program. This is a simple producer-consumer scenario, where the producer produces data, and the consumer consumes it, with both threads communicating through the `wait()` and `notify()` methods.

#### Example:

```java
class SharedData {
    private int data;
    private boolean isDataAvailable = false;

    // Producer thread
    public synchronized void produce(int newData) throws InterruptedException {
        while (isDataAvailable) {
            wait();  // Wait until consumer consumes data
        }
        data = newData;
        isDataAvailable = true;
        System.out.println("Produced: " + data);
        notify();  // Notify consumer that data is available
    }

    // Consumer thread
    public synchronized int consume() throws InterruptedException {
        while (!isDataAvailable) {
            wait();  // Wait until producer produces data
        }
        isDataAvailable = false;
        System.out.println("Consumed: " + data);
        notify();  // Notify producer that data has been consumed
        return data;
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    sharedData.produce(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    sharedData.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
```

#### Explanation:

- **Producer**: The `produce()` method produces data and stores it in `SharedData`. If the data is already available (`isDataAvailable` is true), the producer calls `wait()`, causing it to release the lock and wait until the consumer consumes the data.
- **Consumer**: The `consume()` method consumes the data from `SharedData`. If no data is available (`isDataAvailable` is false), the consumer calls `wait()`, pausing execution until the producer produces data and notifies the consumer.
- **Synchronization**: Both the producer and consumer methods are synchronized to ensure that only one thread can access and modify the shared resource at a time.
- **Inter-thread Communication**: The `notify()` method wakes up the waiting thread (producer or consumer) after it has finished its task.

This code shows how `wait()` and `notify()` can be used for synchronizing and communicating between threads.

## Functional Programming - Lamdba expressions and Streams
### 208. What is **functional programming**?

Functional programming is a programming paradigm where computation is treated as the evaluation of mathematical functions and avoids changing state or mutable data. It focuses on the use of functions as first-class entities, where functions can be passed as arguments to other functions, returned from other functions, and assigned to variables. It also emphasizes immutability, higher-order functions, and declarative programming.

Some key features of functional programming are:
- **Immutability**: Once data is created, it cannot be changed.
- **Higher-order functions**: Functions that take other functions as parameters or return them as results.
- **First-class functions**: Functions can be assigned to variables, passed as arguments, or returned from other functions.
- **Pure functions**: Functions that always produce the same output for the same input and have no side effects.

In Java, functional programming is supported by lambdas and the Stream API.

---

### 209. Can you give an example of **functional programming**?

Here's an example of functional programming using Java's **Stream API** and **Lambda expressions**. The task is to filter a list of numbers, square each number, and then sum them up.

#### Example:

```java
import java.util.Arrays;
import java.util.List;

public class FunctionalProgrammingExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using stream, lambda, and functional style
        int sumOfSquares = numbers.stream()
                                  .filter(n -> n % 2 == 0)  // Filter even numbers
                                  .map(n -> n * n)           // Square the numbers
                                  .reduce(0, Integer::sum);  // Sum them up

        System.out.println("Sum of squares of even numbers: " + sumOfSquares);
    }
}
```

#### Explanation:
- **Stream API**: It allows functional-style operations on collections.
- **Lambda expression**: `(n -> n % 2 == 0)` is a function that filters out even numbers.
- **map()**: Transforms each number by squaring it.
- **reduce()**: Accumulates the result by summing the squared numbers.

This is an example of functional programming where the code is declarative and focuses on what should be done rather than how.

---

### 210. What is a **stream**?

In Java, a **Stream** is a sequence of elements that can be processed in parallel or sequentially. Streams allow you to process data in a functional style by chaining various operations such as filtering, mapping, and reducing without modifying the underlying data source. It enables operations to be more declarative and concise.

Streams do not store data. Instead, they operate on data provided by a source, such as a collection, an array, or I/O channels.

There are two types of streams:
- **Sequential stream**: Data is processed in a single-threaded manner.
- **Parallel stream**: Data is processed in a multi-threaded manner, providing performance improvements for large datasets.

### Key characteristics of streams:
- They support **functional-style operations** such as `filter()`, `map()`, `reduce()`, `collect()`, and more.
- Streams are **lazy**: Intermediate operations are not executed until a terminal operation (like `collect()`) is invoked.
- Streams are **non-interfering**: Operations do not modify the underlying data source.

---

### 211. Explain about **streams** with an example?

Streams are a core part of Java's functional programming approach, allowing you to process data declaratively. The **Stream API** in Java provides an abstraction for processing sequences of elements (e.g., collections, arrays) in a functional style.

#### Example: Stream Operations

Let's say we have a list of integers, and we want to find the sum of all even numbers after doubling them. We can achieve this using the **Stream API** in a concise and readable way.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Using Stream to process data
        int sumOfDoubledEvens = numbers.stream()
                                       .filter(n -> n % 2 == 0)   // Step 1: Filter even numbers
                                       .map(n -> n * 2)           // Step 2: Double each number
                                       .reduce(0, Integer::sum);  // Step 3: Sum them up

        System.out.println("Sum of doubled even numbers: " + sumOfDoubledEvens);
    }
}
```

#### Explanation:
1. **stream()**: Creates a stream from the list of integers.
2. **filter()**: Filters the even numbers (returns only numbers that satisfy `n % 2 == 0`).
3. **map()**: Doubles each even number (`n * 2`).
4. **reduce()**: Sums up the doubled numbers. The initial value is `0`, and `Integer::sum` is a method reference that sums the values.

#### Output:
```
Sum of doubled even numbers: 40
```

This example demonstrates how you can use streams to process collections in a declarative, functional style, chaining together operations like `filter()`, `map()`, and `reduce()`.

---

### Summary of Stream Features:
- **Laziness**: Operations like `filter()` and `map()` are not executed until a terminal operation (`collect()`, `reduce()`, etc.) is invoked.
- **Functional-style programming**: Operations can be chained and processed in a clean, functional way.
- **Parallelization**: Streams can be processed in parallel for performance gains in large datasets.

### 211. **What are intermediate operations in streams?**

Intermediate operations in streams are operations that transform a stream into another stream. These operations are **lazy** and are not executed until a terminal operation is invoked. Each intermediate operation returns a new stream, allowing operations to be chained together. Common intermediate operations include:

- **`filter()`**: Filters elements based on a condition.
- **`map()`**: Transforms elements using a given function.
- **`flatMap()`**: Flattens elements into a single stream after transforming them.
- **`distinct()`**: Removes duplicate elements from the stream.
- **`sorted()`**: Sorts elements based on a comparator or natural ordering.
- **`limit()`**: Limits the number of elements in the stream.
- **`skip()`**: Skips the first `n` elements of the stream.
- **`peek()`**: Allows you to perform an action on each element without modifying the stream.

Intermediate operations are **not executed immediately**; they are combined into a pipeline and are evaluated when a terminal operation is called.

### 212. **What are terminal operations in streams?**

Terminal operations in streams are operations that trigger the processing of the stream. These operations produce a result or a side-effect and **terminate the stream pipeline**. Once a terminal operation is invoked, the stream can no longer be used. Common terminal operations include:

- **`collect()`**: Collects the elements into a collection like `List`, `Set`, or `Map`.
- **`forEach()`**: Iterates over the elements of the stream and performs an action.
- **`reduce()`**: Reduces the elements of the stream into a single value (e.g., summing numbers).
- **`count()`**: Counts the number of elements in the stream.
- **`min()`** and **`max()`**: Returns the minimum or maximum element based on a comparator.
- **`anyMatch()`**, **`allMatch()`**, and **`noneMatch()`**: Tests whether any, all, or none of the elements match a given condition.
- **`findFirst()`** and **`findAny()`**: Retrieves the first or any element from the stream.

Once a terminal operation is invoked, the stream is consumed, and no further operations can be applied to it.

### 213. **What are method references?**

Method references in Java provide a shorthand notation to call a method using an object or class reference. They make the code more concise and readable, especially when using lambda expressions. A method reference refers directly to a method of an object or class and can be used to replace lambda expressions where the lambda only calls an existing method.

There are four types of method references:

1. **Reference to a static method**:  
   ```java
   ClassName::staticMethod
   ```

2. **Reference to an instance method of a particular object**:  
   ```java
   instance::instanceMethod
   ```

3. **Reference to an instance method of an arbitrary object of a particular type**:  
   ```java
   ClassName::instanceMethod
   ```

4. **Reference to a constructor**:  
   ```java
   ClassName::new
   ```

#### Example of Method Reference:
```java
import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using method reference to print each name
        names.forEach(System.out::println);  // Equivalent to names.forEach(name -> System.out.println(name));
    }
}
```

---

### 214. **What are lambda expressions?**

Lambda expressions are a feature introduced in Java 8 that allows you to treat functionality as a method argument or to create a function that can be passed around. They enable **functional programming** and are used primarily to define the behavior of methods in interfaces with a single abstract method (i.e., functional interfaces).

The general syntax of a lambda expression is:
```java
(parameters) -> expression
```

- **Parameters**: The input parameters of the lambda expression (can be empty or multiple).
- **Arrow (`->`)**: Separates parameters from the body of the lambda expression.
- **Expression**: The body of the lambda, which can be a single expression or a block of code.

#### Example:
```java
import java.util.Arrays;
import java.util.List;

public class LambdaExpressionExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using lambda expression to print each number
        numbers.forEach(n -> System.out.println(n));
    }
}
```

### Benefits of Lambda Expressions:
- **Concise**: Lambda expressions make code more readable and concise.
- **Functional Style**: They allow the implementation of functional programming in Java.
- **Can be passed as parameters**: Lambda expressions can be passed as arguments to methods or used as return values.

---

### 215. **Can you give an example of a lambda expression?**

Sure! Here's an example where we use a lambda expression to sort a list of strings in Java:

```java
import java.util.Arrays;
import java.util.List;

public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Using lambda expression to sort names in alphabetical order
        names.sort((a, b) -> a.compareTo(b));

        // Printing the sorted list
        names.forEach(name -> System.out.println(name));
    }
}
```

#### Explanation:
- **Lambda expression `(a, b) -> a.compareTo(b)`**: This is used to define the sorting logic. It compares two strings alphabetically.
- **`names.sort()`**: Sorts the list using the provided comparator, which is a lambda expression.
- **`names.forEach(name -> System.out.println(name))`**: Prints each name in the sorted list.

### 216. **Can you explain the relationship between lambda expression and functional interfaces?**

Lambda expressions in Java are directly tied to **functional interfaces**. A **functional interface** is an interface that contains exactly one abstract method. Lambda expressions provide a way to implement the abstract method of a functional interface in a more concise manner.

- **Functional interfaces** allow you to define the target type for lambda expressions.
- A lambda expression is a compact way of expressing an instance of a **functional interface**.

### Example:
```java
@FunctionalInterface
interface Greet {
    void greetMessage(String name);
}

public class LambdaExample {
    public static void main(String[] args) {
        Greet greet = (name) -> System.out.println("Hello, " + name);
        greet.greetMessage("Alice");
    }
}
```

In this example, the `Greet` interface is functional, as it has only one abstract method (`greetMessage`). The lambda expression `(name) -> System.out.println("Hello, " + name)` provides an implementation for the abstract method, creating a more concise and readable way of writing the code.

### 217. **What is a Predicate?**

A **Predicate** is a functional interface that represents a condition (or test) that returns a boolean value. It is used for evaluating a condition on a given input.

- **`Predicate<T>`** has a method `test(T t)` that returns a boolean value.

#### Example:
```java
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Integer> isEven = num -> num % 2 == 0;

        System.out.println(isEven.test(4));  // true
        System.out.println(isEven.test(5));  // false
    }
}
```

In this example, the `isEven` predicate tests whether a number is even.

### 218. **What is the functional interface - Function?**

The **`Function`** functional interface represents a function that accepts one argument and produces a result. It is commonly used for transformations or operations that take an input and return an output.

- **`Function<T, R>`** has a method `apply(T t)` that takes a parameter of type `T` and returns a result of type `R`.

#### Example:
```java
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String, Integer> stringLength = str -> str.length();

        System.out.println(stringLength.apply("Hello"));  // 5
    }
}
```

In this example, the `stringLength` function takes a `String` and returns its length.

### 219. **What is a Consumer?**

The **`Consumer<T>`** functional interface represents an operation that takes a single input and returns no result (i.e., it produces a side effect). It is often used for actions that perform operations on input data but do not return any value.

- **`Consumer<T>`** has a method `accept(T t)` that consumes the input of type `T`.

#### Example:
```java
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> printMessage = message -> System.out.println(message);

        printMessage.accept("Hello, world!");
    }
}
```

In this example, the `printMessage` consumer accepts a string and prints it.

### 220. **Can you give examples of functional interfaces with multiple arguments?**

Yes! There are functional interfaces that accept multiple arguments, such as `BiPredicate`, `BiFunction`, and `BiConsumer`. These interfaces represent operations that take two arguments.

#### Example with `BiPredicate`:
```java
import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;

        System.out.println(isGreater.test(10, 5));  // true
        System.out.println(isGreater.test(3, 5));   // false
    }
}
```

In this example, the `BiPredicate` interface is used to compare two integers.

#### Example with `BiFunction`:
```java
import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

        System.out.println(sum.apply(10, 5));  // 15
    }
}
```

In this example, the `BiFunction` interface takes two integers as input and returns their sum.

#### Example with `BiConsumer`:
```java
import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {
        BiConsumer<String, Integer> printMessage = (name, age) -> System.out.println(name + " is " + age + " years old.");

        printMessage.accept("Alice", 30);  // Alice is 30 years old.
    }
}
```

In this example, the `BiConsumer` interface takes two arguments (a `String` and an `Integer`) and performs an action on them (printing a message). 

##  New Features
- 221 . What are the new features in Java 5?
- 222 . What are the new features in Java 6?
- 223 . What are the new features in Java 7?
- 224 . What are the new features in Java 8?
Java 8 introduced several new features and enhancements to the Java programming language, with a focus on improving code readability, enabling parallelism, and introducing functional programming concepts. Some of the key features are:

### 1. **Lambda Expressions**
Lambda expressions allow you to pass behavior as parameters to methods or define code inline in a more concise and readable manner. They represent an anonymous function that can be treated as an instance of a functional interface.

#### Example:
```java
// Traditional way of implementing a Runnable
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello from Runnable");
    }
};

// Using Lambda Expression
Runnable r2 = () -> System.out.println("Hello from Runnable");
```

### 2. **Functional Interfaces**
A **functional interface** is an interface that has exactly one abstract method, and it may contain multiple default or static methods. The lambda expressions can be used to instantiate functional interfaces.

Examples of functional interfaces are:
- `Runnable`
- `Callable`
- `Comparator`
- `Function`
- `Predicate`
- `Consumer`

### 3. **Streams API**
The Streams API allows you to work with sequences of elements in a functional style. It supports operations like filtering, mapping, and reducing, and is particularly useful for processing large datasets in parallel.

#### Example:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

names.stream()
    .filter(name -> name.startsWith("A"))
    .forEach(System.out::println);  // Prints: Alice
```

### 4. **Default Methods in Interfaces**
Java 8 allows interfaces to have method implementations using the `default` keyword. This feature enables interfaces to evolve without breaking existing implementations.

#### Example:
```java
interface MyInterface {
    default void greet() {
        System.out.println("Hello from MyInterface");
    }
}
```

### 5. **Method References**
Method references are a shorthand notation of a lambda expression to call a method directly by referring to it using the `::` operator.

#### Example:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(System.out::println);  // Method reference for println
```

### 6. **Optional Class**
The `Optional` class is a container object which may or may not contain a value. It helps to handle `null` values gracefully and avoid `NullPointerException`.

#### Example:
```java
Optional<String> name = Optional.ofNullable(getName());
name.ifPresent(System.out::println);  // If name is not null, print it
```

### 7. **New Date and Time API (java.time package)**
The new `java.time` package introduces a comprehensive and immutable set of classes for date and time manipulation, making it easier to handle dates, times, and durations compared to the old `Date` and `Calendar` classes.

Examples:
- `LocalDate`, `LocalTime`, `LocalDateTime`
- `Instant`, `Duration`, `Period`

#### Example:
```java
LocalDate today = LocalDate.now();
System.out.println(today);  // Prints today's date in YYYY-MM-DD format
```

### 8. **Nashorn JavaScript Engine**
Java 8 introduced the Nashorn JavaScript engine, which is a replacement for the older Rhino engine. Nashorn is faster and more efficient, and it allows embedding JavaScript code within Java applications.

#### Example:
```java
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript')");
```

### 9. **CompletableFuture**
`CompletableFuture` is a class that represents a future result of an asynchronous computation. It provides methods to handle asynchronous tasks in a more flexible and readable way, and supports combining multiple asynchronous tasks.

#### Example:
```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10)
    .thenApplyAsync(result -> result * 2);
future.thenAccept(System.out::println);  // Prints 20
```

### 10. **Parallel Streams**
Parallel streams allow you to process data in parallel, taking advantage of multi-core processors. It simplifies parallel processing with minimal effort.

#### Example:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.parallelStream().forEach(System.out::println);  // Process in parallel
```

### 11. **New `@FunctionalInterface` Annotation**
The `@FunctionalInterface` annotation is used to indicate that an interface is intended to be a functional interface. This annotation provides compile-time checking that ensures the interface has exactly one abstract method.

#### Example:
```java
@FunctionalInterface
interface MyFunctionalInterface {
    void doSomething();
}
```

### 12. **Streams API Enhancements (e.g., `Collectors`)**
Java 8 introduced the `Collectors` utility class, which provides methods to collect elements from a stream into collections like lists, sets, maps, etc., in an easy and declarative way.

#### Example:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("A"))
    .collect(Collectors.toList());  // Collecting the filtered names into a list
```

### Summary of New Features in Java 8:
- **Lambda expressions**
- **Functional interfaces**
- **Streams API**
- **Default methods in interfaces**
- **Method references**
- **Optional class**
- **New Date and Time API (`java.time`)**
- **Nashorn JavaScript engine**
- **CompletableFuture**
- **Parallel streams**
- **`@FunctionalInterface` annotation**
- **Collectors utility class**


