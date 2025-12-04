# Java Interview Questions - Concise Answers (Questions 1-10)

## 1. Why is Java so popular?

Java is popular because it's "write once, run anywhere" - platform independent. It has automatic memory management, strong security, and a huge ecosystem. Major companies like Google, Netflix, and banks use Java for enterprise applications because it's reliable and scalable.

```java
public class Demo {
    public static void main(String[] args) {
        System.out.println("Runs on any OS with JVM");
    }
}
```

## 2. What is platform independence?

Platform independence means Java code runs on any operating system without changes. You compile once to bytecode, then the JVM on each platform converts it to machine code. Same .class file works on Windows, Linux, Mac - that's the power of JVM.

```java
// Same code works everywhere
File file = new File("data.txt");
```

## 3. What is bytecode?

Bytecode is intermediate code between source code and machine code. When you compile Java, it creates .class files containing bytecode. The JVM reads this bytecode and converts it to platform-specific machine instructions. It's like a universal language for JVMs.

```bash
javac Hello.java  # Creates Hello.class (bytecode)
java Hello        # JVM executes bytecode
```

## 4. Compare JDK vs JVM vs JRE

JVM executes Java programs. JRE includes JVM plus runtime libraries to run Java apps. JDK includes JRE plus development tools like compiler and debugger. Think of it as: JDK for developers, JRE for users, JVM for execution.

```
JDK = JRE + Tools (javac, javadoc)
JRE = JVM + Libraries  
JVM = Execution Engine
```

## 5. What are the important differences between C++ and Java?

Java is simpler and safer. No pointers, no manual memory management, no multiple inheritance. Java has garbage collection, built-in security, and platform independence. C++ gives more control but requires manual memory management and compiles to machine code.

```java
// Java - automatic cleanup
String name = new String("John");  // GC handles memory

// C++ needs: delete[] name;
```

## 6. What is the role of a classloader in Java?

Classloader loads .class files into memory when needed. It follows delegation - checks parent first, then loads if not found. Three types: Bootstrap loads core classes, Extension loads extensions, Application loads your classes from classpath.

```java
Class<?> clazz = Class.forName("MyClass");
Object obj = clazz.newInstance();
```

## 7. What are Wrapper classes?

Wrapper classes convert primitives to objects. int becomes Integer, double becomes Double, etc. They're needed because collections only store objects, not primitives. They also provide utility methods for parsing and conversion.

```java
int x = 5;
Integer wrapper = Integer.valueOf(x);
List<Integer> list = new ArrayList<>();
```

## 8. Why do we need Wrapper classes in Java?

Collections can't store primitives, only objects. Wrapper classes solve this. They also provide utility methods like parseInt(), allow null values, and are required for generics. They bridge the gap between primitive and object worlds.

```java
List<Integer> numbers = new ArrayList<>();  // Can't use int
int parsed = Integer.parseInt("123");
```

## 9. What are the different ways of creating Wrapper class instances?

Three ways: constructor, valueOf(), and auto-boxing. Constructor always creates new objects. valueOf() reuses cached objects for small values. Auto-boxing automatically converts primitives to wrappers - it's the most convenient.

```java
Integer a = new Integer(10);     // Constructor
Integer b = Integer.valueOf(10); // valueOf
Integer c = 10;                  // Auto-boxing
```

## 10. What are the differences in the two ways of creating Wrapper classes?

Constructor always creates new objects in memory. valueOf() reuses cached instances for values -128 to 127, saving memory. Auto-boxing uses valueOf() internally. For performance, prefer valueOf() or auto-boxing over constructors.

```java
Integer a = new Integer(100);
Integer b = new Integer(100);
System.out.println(a == b);  // false

Integer c = 100, d = 100;
System.out.println(c == d);  // true (cached)
```

---

*Concise, interview-ready answers designed for 15-35 second spoken responses with minimal essential code examples.*