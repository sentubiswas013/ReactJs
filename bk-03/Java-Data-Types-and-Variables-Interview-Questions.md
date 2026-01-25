# 2. Data Types and Variables

## 1. What are primitive data types in Java?

Java has 8 primitive data types that store simple values directly in memory. They're the building blocks of data manipulation.

- **byte** - 8-bit integer (-128 to 127)
- **short** - 16-bit integer (-32,768 to 32,767)
- **int** - 32-bit integer (most commonly used)
- **long** - 64-bit integer (add 'L' suffix)
- **float** - 32-bit decimal (add 'f' suffix)
- **double** - 64-bit decimal (default for decimals)
- **char** - 16-bit Unicode character
- **boolean** - true or false

```java
int age = 25;
double price = 99.99;
boolean isActive = true;
```

## 2. What is the difference between primitive and reference types?

Primitive types store actual values directly in memory, while reference types store memory addresses pointing to objects.

**Primitive types:**
- Stored in stack memory
- Direct value storage
- Faster access
- Default values (0, false, etc.)

**Reference types:**
- Stored in heap memory
- Store memory addresses
- Include classes, arrays, interfaces
- Default value is null

```java
int x = 10;        // primitive - stores value 10
String name = "John"; // reference - stores address to "John" object
```

## 3. What is autoboxing and unboxing?

Autoboxing automatically converts primitives to their wrapper objects, while unboxing does the reverse. Java handles this conversion automatically.

```java
// Autoboxing - primitive to wrapper
Integer num = 100;  // int 100 becomes Integer object

// Unboxing - wrapper to primitive  
int value = num;    // Integer object becomes int
```

This happens automatically in collections, method calls, and assignments.

## 4. What is the difference between == and equals() method?

The == operator compares memory addresses for objects, while equals() compares actual content.

**For primitives:** == compares values
**For objects:** == compares references, equals() compares content

```java
String a = new String("hello");
String b = new String("hello");

System.out.println(a == b);       // false - different objects
System.out.println(a.equals(b));  // true - same content
```

## 5. What is the difference between String, StringBuilder, and StringBuffer?

**String:** Immutable, creates new objects for modifications
**StringBuilder:** Mutable, not thread-safe, faster
**StringBuffer:** Mutable, thread-safe, slower due to synchronization

```java
// String - creates new objects
String str = "Hello";
str += " World";  // Creates new String object

// StringBuilder - modifies existing buffer
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");  // Modifies same object
```

Use StringBuilder for single-threaded operations, StringBuffer for multi-threaded.

## 6. Why are strings immutable in Java?

Strings are immutable for several important reasons:

- **Security:** Prevents malicious code from changing string values
- **Thread Safety:** Multiple threads can access without synchronization
- **String Pool:** Enables efficient memory usage through sharing
- **Hashcode Caching:** Hash values remain constant for HashMap keys
- **Performance:** JVM optimizations possible

Once created, String objects cannot be modified - operations create new objects instead.

## 7. What is string pooling?

String pooling is Java's memory optimization technique where identical string literals share the same memory location in the String Pool (part of heap memory).

```java
String a = "hello";    // stored in string pool
String b = "hello";    // reuses same memory location
String c = new String("hello"); // creates new object in heap

System.out.println(a == b);  // true - same reference
System.out.println(a == c);  // false - different references
```

This reduces memory usage and improves performance for string literals.

## 8. What is the difference between final, finally, and finalize?

**final:** Keyword for constants, preventing inheritance/override
**finally:** Block that always executes after try-catch
**finalize:** Method called by garbage collector before object destruction

```java
// final
final int MAX_SIZE = 100;
final class MyClass { }

// finally
try {
    // code
} catch (Exception e) {
    // handle exception
} finally {
    // always executes
}

// finalize
protected void finalize() {
    // cleanup before garbage collection
}
```

**final** is for immutability, **finally** for cleanup code, **finalize** for garbage collection (rarely used).