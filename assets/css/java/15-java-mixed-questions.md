# Top 1000 Java Interview Question & Answers

## Mixed Questions

### 169. **What are Wrapper classes in Java?**

In Java, **Wrapper classes** are used to provide a way to use primitive data types (such as `int`, `char`, `boolean`, etc.) as objects. Each primitive type has a corresponding wrapper class in Java:

- **Primitive Type** → **Wrapper Class**
  - `int` → `Integer`
  - `char` → `Character`
  - `boolean` → `Boolean`
  - `byte` → `Byte`
  - `short` → `Short`
  - `long` → `Long`
  - `float` → `Float`
  - `double` → `Double`
  
These wrapper classes provide utility methods to convert between primitive types and their respective object types, and they also provide methods to parse strings into primitive types, etc.

**Example**:
```java
int primitiveInt = 10;
Integer wrapperInt = Integer.valueOf(primitiveInt);  // Boxing

int unboxedInt = wrapperInt.intValue();  // Unboxing
```

**Purpose**:
- **Autoboxing**: Automatic conversion between primitives and their wrapper objects.
- **Utilities**: Wrapper classes provide methods for manipulating primitive data in a way that regular primitive types cannot (e.g., converting to/from strings, parsing, etc.).
- **Generics**: Collections (like `List` or `Set`) can only store objects, so wrapper classes are used to store primitive values in collections.

---

### 170. **What is the purpose of a native method in Java?**

A **native method** in Java is a method that is implemented in a language other than Java, typically in **C or C++**, using the Java Native Interface (JNI). Native methods allow Java to interact with platform-specific code or perform operations that cannot be directly implemented in Java.

**Purpose**:
- **Performance**: Native methods can be used to write performance-critical sections of code that require direct access to hardware or operating system resources.
- **Access to non-Java libraries**: You can use native methods to call existing non-Java libraries or system-level resources (such as writing directly to the file system or interacting with hardware).
- **Platform-specific functionality**: Native methods are used when Java's standard libraries do not support the required platform-specific operations.

**Example**:
```java
public class Example {
    public native void exampleMethod();  // Declaring a native method

    static {
        System.loadLibrary("exampleLibrary");  // Loading the native library
    }
}
```
Here, `exampleMethod()` is implemented in a native language like C or C++.

---

### 171. **What is the System class?**

The **`System` class** in Java is a utility class from the `java.lang` package that provides several useful methods and fields for interacting with the environment in which the Java program is running. It cannot be instantiated because all methods in the `System` class are static.

**Key features of the `System` class**:
- **Standard input/output**: `System.in`, `System.out`, and `System.err` are commonly used for reading input, output, and error messages.
- **System properties**: Methods like `System.getProperty()` are used to retrieve system properties (such as OS name, version, etc.).
- **Environment variables**: The `System.getenv()` method allows access to environment variables.
- **Exiting the program**: The `System.exit()` method is used to terminate the Java Virtual Machine (JVM).
- **Timing**: Methods like `System.nanoTime()` and `System.currentTimeMillis()` provide ways to measure elapsed time.
- **Garbage collection**: `System.gc()` suggests the JVM to run garbage collection.

**Example**:
```java
System.out.println("Hello, World!");
long startTime = System.nanoTime();
System.out.println("Time taken: " + (System.nanoTime() - startTime) + " nanoseconds");
```

---

### 172. **What is System, out and println in the `System.out.println()` method call?**

In Java, **`System.out.println()`** is a common way to print output to the console. Here's a breakdown of each component:

1. **`System`**: `System` is a class in the `java.lang` package. It provides utility methods and fields for interacting with the operating system and JVM, like `System.in`, `System.out`, and `System.err`.

2. **`out`**: `out` is a static field of the `System` class that represents the **standard output stream**. It is an instance of the `PrintStream` class, which is used to send output to the console or terminal.

3. **`println`**: `println` is a method of the `PrintStream` class (which is the type of `System.out`). It is used to print data to the console, followed by a new line. The `println()` method can print different types of data (strings, numbers, objects, etc.).

**Example**:
```java
System.out.println("Hello, World!");
```

In this example:
- `System` refers to the `System` class.
- `out` is the **standard output stream** (an instance of `PrintStream`).
- `println()` is the method that outputs the text `"Hello, World!"` followed by a newline to the console.

The **`println()`** method automatically converts the argument to a string representation if necessary and prints it to the console.

### 173. **What is the other name of Shallow Copy in Java?**

The other name for **Shallow Copy** in Java is **"shallow clone"** or simply **"clone"**. In Java, the `clone()` method (from the `Object` class) is used to create a shallow copy of an object.

### 174. **What is the difference between Shallow Copy and Deep Copy in Java?**

**Shallow Copy** and **Deep Copy** are two types of copying mechanisms used to duplicate an object. Here's the difference:

#### Shallow Copy:
- A shallow copy of an object creates a new object, but it does not clone the objects referenced by the original object's fields. It only copies the **references** to the objects, not the objects themselves.
- In other words, for any field that is a reference type, a shallow copy will still point to the same object in memory as the original object.
- It is faster but does not provide a complete independent copy.

**Example of Shallow Copy:**
```java
class Person {
    String name;
    int age;
    
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class ShallowCopyExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("John", 25);
        Person p2 = (Person) p1.clone();  // Shallow copy
        
        System.out.println(p1 == p2);  // false: different memory locations
    }
}
```

In the above example, `p1` and `p2` are different objects, but they point to the same internal fields if they are reference types.

#### Deep Copy:
- A deep copy creates a new object as well as copies of the objects referenced by the original object. Essentially, it creates an entirely new independent object, including nested objects.
- It ensures that no shared references are maintained, meaning each object is fully copied and independent of the other.

**Example of Deep Copy:**
```java
class Address {
    String city;
    
    Address(String city) {
        this.city = city;
    }
}

class Person {
    String name;
    Address address;
    
    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    // Deep copy method
    public Person deepCopy() {
        return new Person(this.name, new Address(this.address.city));
    }
}

class DeepCopyExample {
    public static void main(String[] args) {
        Address addr = new Address("New York");
        Person p1 = new Person("John", addr);
        
        Person p2 = p1.deepCopy();  // Deep copy
        
        System.out.println(p1 == p2);  // false: different objects
        System.out.println(p1.address == p2.address);  // false: different Address objects
    }
}
```

In this deep copy example, both `p1` and `p2` are independent, and even their `Address` objects are separate.

### 175. **What is a Singleton class?**

A **Singleton class** in Java is a class that allows only one instance of itself to be created during the runtime of an application. The primary purpose of a Singleton is to control access to resources, such as database connections, thread pools, or configurations, ensuring that only one instance exists and is shared.

**Characteristics of Singleton class**:
- It has a private static instance of itself.
- The constructor is made private to prevent instantiation from outside the class.
- It provides a public static method to return the unique instance.

**Example of Singleton class**:
```java
public class Singleton {
    // Private static instance of the Singleton class
    private static Singleton instance;
    
    // Private constructor to prevent instantiation from outside
    private Singleton() {}
    
    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();  // Create the instance if it does not exist
        }
        return instance;
    }
}
```

### 176. **What is the difference between Singleton class and Static class?**

A **Singleton class** and a **Static class** have some key differences:

#### Singleton Class:
- **Instance-based**: A Singleton class allows only one instance of the class to be created. You can use the `getInstance()` method to access the unique instance.
- **Lazy Initialization**: The instance of the Singleton class is typically created lazily, i.e., it is only created when needed (usually the first time `getInstance()` is called).
- **Stateful**: A Singleton can maintain state through instance variables.

**Example**: As shown above.

#### Static Class:
- **No instance**: A static class cannot have instances. All methods and members of a static class are static, and they are accessed directly via the class name.
- **Utility-based**: Static classes are often used for utility methods that do not require any instance state.
- **Eager Initialization**: A static class is typically loaded into memory when the class is loaded, and it does not have lazy initialization.

**Example of Static Class**:
```java
public class UtilityClass {
    // Static method that doesn't require an instance of the class
    public static void printMessage() {
        System.out.println("This is a static method.");
    }
}

class Test {
    public static void main(String[] args) {
        UtilityClass.printMessage();  // Accessing static method directly
    }
}
```

**Key Differences**:
1. **Instance**: A Singleton class has a single instance that is used globally, while a static class has no instance and its methods are accessed directly via the class.
2. **State**: A Singleton can maintain state, whereas a static class typically doesn't maintain state (except through static variables).
3. **Purpose**: Singleton is used when you need a single point of access to a resource, while static classes are used to group utility methods that don’t require any object state.