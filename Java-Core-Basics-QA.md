# Core Java Basics - Interview Q&A

## 1. What is the difference between JDK and JRE?

**JRE (Java Runtime Environment)** is what you need to run Java applications - it includes JVM and core libraries. **JDK (Java Development Kit)** is what you need to develop Java applications - it includes JRE plus development tools like compiler, debugger, and documentation.

```java
// To run this, you need JRE
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

// To compile this, you need JDK
// javac HelloWorld.java (requires JDK)
// java HelloWorld (requires JRE)
```

---

## 2. What is Java Virtual Machine (JVM)?

**JVM** is a runtime environment that executes Java bytecode. It's platform-specific but provides platform independence to Java programs. JVM converts bytecode into machine-specific code and manages memory, garbage collection, and security.

```java
// Java source code (.java)
public class Demo {
    public static void main(String[] args) {
        int x = 10;
        System.out.println(x);
    }
}

// Compiler converts to bytecode (.class)
// JVM converts bytecode to machine code
// Output: 10
```

---

## 3. What are the different types of memory areas allocated by JVM?

JVM allocates several memory areas: **Heap** (object storage), **Method Area** (class metadata), **Stack** (method calls and local variables), **PC Register** (current instruction pointer), and **Native Method Stack** (native method calls).

```java
public class MemoryDemo {
    static int classVar = 100;  // Method Area
    
    public static void main(String[] args) {
        int localVar = 50;      // Stack
        String obj = "Hello";   // Heap (object)
        method1(localVar);      // Stack frame created
    }
    
    static void method1(int param) {  // Stack
        int temp = param * 2;         // Stack
    }
}
```

---

## 4. What is JIT Compiler?

**JIT (Just-In-Time) Compiler** improves performance by compiling frequently used bytecode into native machine code at runtime. It identifies "hot spots" in code and optimizes them for faster execution.

```java
public class JITDemo {
    public static void main(String[] args) {
        // This loop will be optimized by JIT
        for (int i = 0; i < 1000000; i++) {
            calculate(i);  // JIT optimizes this after multiple calls
        }
    }
    
    static int calculate(int n) {
        return n * n + 10;
    }
}
```

---

## 5. How is Java Platform different from other platforms?

Java platform is **software-based** and runs on top of hardware platforms. Unlike hardware platforms (Windows, Linux), Java platform provides a consistent runtime environment through JVM, making applications platform-independent.

```java
// Same code runs on Windows, Linux, Mac
public class PlatformDemo {
    public static void main(String[] args) {
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Java Version: " + System.getProperty("java.version"));
        // Output varies by OS, but code remains same
    }
}
```

---

## 6. Why do people say that Java is 'Write Once and Run Anywhere' language?

Java source code compiles to **platform-neutral bytecode**, not machine-specific code. This bytecode runs on any system with JVM installed, eliminating the need to rewrite or recompile for different platforms.

```java
// Write once
public class WORA {
    public static void main(String[] args) {
        System.out.println("Running on: " + 
            System.getProperty("os.name"));
    }
}

// Compile once: javac WORA.java
// Run anywhere: java WORA
// Works on Windows, Linux, Mac without changes
```

---

## 7. How does ClassLoader work in Java?

**ClassLoader** loads classes into JVM memory on-demand. It follows delegation model: **Bootstrap** (core classes), **Extension** (extension classes), and **Application** (user classes) loaders work hierarchically.

```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // Get class loader information
        Class<?> clazz = ClassLoaderDemo.class;
        
        System.out.println("Class: " + clazz.getName());
        System.out.println("ClassLoader: " + clazz.getClassLoader());
        System.out.println("Parent: " + clazz.getClassLoader().getParent());
    }
}
```

---

## 8. Do you think 'main' used for main method is a keyword in Java?

**No**, 'main' is not a keyword in Java. It's just a method name that JVM looks for as the entry point. You can have other methods named 'main' with different signatures.

```java
public class MainDemo {
    // Entry point - JVM calls this
    public static void main(String[] args) {
        System.out.println("Entry point main");
        main(10);  // Call overloaded main
    }
    
    // Overloaded main - not a keyword
    public static void main(int x) {
        System.out.println("Overloaded main: " + x);
    }
}
```

---

## 9. Can we write main method as public void static instead of public static void?

**No**, the order of modifiers matters for the main method. JVM specifically looks for `public static void main(String[] args)`. Changing the order will cause runtime error.

```java
// Correct - JVM recognizes this
public static void main(String[] args) {
    System.out.println("Correct main method");
}

// Incorrect - JVM won't recognize this as entry point
// public void static main(String[] args) {
//     System.out.println("Wrong order");
// }
// Error: Could not find or load main class
```

---

## 10. What will be the default value of local variables if we do not specify any value?

**Local variables have no default values** and must be explicitly initialized before use. Attempting to use uninitialized local variables results in compilation error.

```java
public class LocalVarDemo {
    static int classVar;  // Default value: 0
    
    public static void main(String[] args) {
        int localVar;  // No default value
        
        System.out.println("Class var: " + classVar);  // Prints: 0
        // System.out.println(localVar);  // Compilation error
        
        localVar = 10;  // Must initialize
        System.out.println("Local var: " + localVar);  // Prints: 10
    }
}
```

---

## 11. What is the difference between byte and char data types in Java?

**byte** is 8-bit signed integer (-128 to 127) used for binary data. **char** is 16-bit unsigned integer (0 to 65535) representing Unicode characters. char can store letters, digits, and symbols.

```java
public class ByteCharDemo {
    public static void main(String[] args) {
        byte b = 65;        // Numeric value
        char c = 65;        // Unicode character 'A'
        char c2 = 'A';      // Character literal
        
        System.out.println("byte: " + b);      // 65
        System.out.println("char: " + c);      // A
        System.out.println("char2: " + c2);    // A
        System.out.println("char as int: " + (int)c);  // 65
    }
}
```

---

## Summary

These core Java concepts form the foundation for Java development. Understanding JVM architecture, memory management, and basic data types is crucial for writing efficient Java applications and succeeding in technical interviews.