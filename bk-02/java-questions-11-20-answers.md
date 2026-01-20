## Data Types and Variables

### 11. What are primitive data types in Java?

Java has **8 primitive data types**:

| Type | Size | Range | Default |
|------|------|-------|---------|
| `byte` | 8-bit | -128 to 127 | 0 |
| `short` | 16-bit | -32,768 to 32,767 | 0 |
| `int` | 32-bit | -2³¹ to 2³¹-1 | 0 |
| `long` | 64-bit | -2⁶³ to 2⁶³-1 | 0L |
| `float` | 32-bit | IEEE 754 | 0.0f |
| `double` | 64-bit | IEEE 754 | 0.0d |
| `char` | 16-bit | 0 to 65,535 (Unicode) | '\u0000' |
| `boolean` | 1-bit | true/false | false |

**Key Points:**
- Store actual values directly in memory
- Stored in stack memory
- No methods or null values

### 12. What is the difference between primitive and reference types?

| Aspect | Primitive Types | Reference Types |
|--------|----------------|-----------------|
| **Storage** | Stack memory | Heap memory (object), Stack (reference) |
| **Content** | Actual values | Memory addresses |
| **Size** | Fixed size | Variable size |
| **Default** | Specific values (0, false, etc.) | null |
| **Methods** | No methods | Have methods |
| **Null** | Cannot be null | Can be null |

```java
int x = 10;        // Primitive - stores value 10
Integer y = 10;    // Reference - stores address to Integer object
```

### 13. What is autoboxing and unboxing?

**Autoboxing:** Automatic conversion from primitive to wrapper object
**Unboxing:** Automatic conversion from wrapper object to primitive

```java
// Autoboxing
int primitive = 5;
Integer wrapper = primitive;  // int → Integer

// Unboxing  
Integer wrapper = 10;
int primitive = wrapper;      // Integer → int

// In collections
List<Integer> list = new ArrayList<>();
list.add(5);  // Autoboxing: int → Integer
```

**When it happens:**
- Assignment operations
- Method parameters
- Collection operations
- Arithmetic operations

### 14. Explain the concept of wrapper classes.

**Wrapper classes** are object representations of primitive types:

| Primitive | Wrapper Class |
|-----------|---------------|
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

**Benefits:**
```java
// Utility methods
Integer.parseInt("123");
Double.valueOf("3.14");

// Null values
Integer count = null;  // Possible
// int count = null;   // Compilation error

// Collections (require objects)
List<Integer> numbers = new ArrayList<>();
```

### 15. What is the difference between == and equals() method?

| Operator/Method | Primitives | Objects |
|----------------|------------|---------|
| `==` | Compares values | Compares memory addresses |
| `equals()` | Not applicable | Compares content/values |

```java
// Primitives
int a = 5, b = 5;
System.out.println(a == b);  // true (same values)

// Objects
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2);      // false (different objects)
System.out.println(s1.equals(s2)); // true (same content)

// String literals (pooled)
String s3 = "Hello";
String s4 = "Hello";
System.out.println(s3 == s4);      // true (same object from pool)
```

### 16. What is the difference between String, StringBuilder, and StringBuffer?

| Feature | String | StringBuilder | StringBuffer |
|---------|--------|---------------|--------------|
| **Mutability** | Immutable | Mutable | Mutable |
| **Thread Safety** | Thread-safe | Not thread-safe | Thread-safe |
| **Performance** | Slow for modifications | Fast | Moderate |
| **Memory** | Creates new objects | Modifies existing | Modifies existing |

```java
// String - creates new objects
String str = "Hello";
str += " World";  // Creates new String object

// StringBuilder - modifies existing
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");  // Modifies same object

// StringBuffer - thread-safe version
StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World");  // Thread-safe modification
```

**Use Cases:**
- **String:** Few modifications, thread safety needed
- **StringBuilder:** Many modifications, single-threaded
- **StringBuffer:** Many modifications, multi-threaded

### 17. Why are strings immutable in Java?

**Reasons for String immutability:**

1. **Security:** Strings used in network connections, file paths can't be modified
2. **Thread Safety:** Multiple threads can access without synchronization
3. **String Pool:** Enables efficient memory usage through pooling
4. **Hash Keys:** Reliable hash codes for HashMap keys
5. **Performance:** Caching hash code improves performance

```java
String password = "secret123";
// No method can modify the original string
// Any "modification" creates a new string
```

### 18. What is string pooling?

**String Pool** is a special memory area in heap where string literals are stored.

```java
String s1 = "Hello";        // Stored in string pool
String s2 = "Hello";        // Reuses same object from pool
String s3 = new String("Hello"); // Creates new object in heap

System.out.println(s1 == s2);  // true (same reference)
System.out.println(s1 == s3);  // false (different objects)
```

**Benefits:**
- Memory optimization
- Faster string comparison
- Reduced object creation

**intern() method:**
```java
String s4 = new String("Hello").intern(); // Forces pool storage
System.out.println(s1 == s4);  // true
```

### 19. How do you compare strings in Java?

**Methods for string comparison:**

```java
String s1 = "Hello";
String s2 = "hello";
String s3 = "Hello";

// Content comparison (case-sensitive)
s1.equals(s3);           // true
s1.equals(s2);           // false

// Content comparison (case-insensitive)  
s1.equalsIgnoreCase(s2); // true

// Lexicographic comparison
s1.compareTo(s3);        // 0 (equal)
s1.compareTo(s2);        // negative (s1 < s2)

// Null-safe comparison
Objects.equals(s1, s3);  // true (handles null)
```

**Best Practices:**
- Use `equals()` for content comparison
- Use `equalsIgnoreCase()` for case-insensitive comparison
- Use `Objects.equals()` for null-safe comparison
- Never use `==` for content comparison

### 20. What is the difference between final, finally, and finalize?

| Keyword | Type | Purpose | Usage |
|---------|------|---------|-------|
| `final` | Keyword | Immutability/Inheritance control | Variables, methods, classes |
| `finally` | Block | Guaranteed execution | Exception handling |
| `finalize` | Method | Cleanup before GC | Object destruction (deprecated) |

**final:**
```java
final int x = 10;           // Constant variable
final List<String> list = new ArrayList<>(); // Immutable reference

final class FinalClass { }  // Cannot be extended
class Parent {
    final void method() { } // Cannot be overridden
}
```

**finally:**
```java
try {
    // risky code
} catch (Exception e) {
    // handle exception
} finally {
    // Always executes (cleanup code)
    // Executes even if return statement in try/catch
}
```

**finalize (deprecated in Java 9):**
```java
@Override
protected void finalize() throws Throwable {
    // Cleanup before garbage collection
    // Not recommended - use try-with-resources instead
}
```

**Key Points:**
- `final` prevents modification/inheritance
- `finally` ensures cleanup code execution
- `finalize` is deprecated - use AutoCloseable instead
