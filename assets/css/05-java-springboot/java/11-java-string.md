# Top 1000 Java Interview Question & Answers

## String

### 121. **What is the meaning of Immutable in the context of the String class in Java?**

In Java, the term **immutable** means that once an object is created, its state cannot be changed or modified. In the context of the `String` class, it implies that once a `String` object is created, its value cannot be altered. Any operation that seems to modify the `String` (such as concatenation or replacement) actually results in the creation of a new `String` object, leaving the original string unchanged.

### 122. **Why is a String object considered immutable in Java?**

A `String` object is considered **immutable** in Java for several reasons:

1. **Security**: Strings are frequently used for sensitive data such as passwords, URLs, and network connections. If they were mutable, a malicious entity might alter the string contents, leading to security vulnerabilities. By making `String` immutable, you ensure that the value remains constant and unaltered during its lifetime.

2. **Efficiency**: Since strings are immutable, they can be shared among multiple parts of a program. This is particularly useful with the **String Pool** (or **String Literal Pool**) in Java. Instead of creating new instances of the same string, Java reuses existing string objects from the pool, saving memory and improving performance.

3. **Thread-Safety**: Immutable objects are inherently **thread-safe**. Multiple threads can safely use and share the same `String` object without worrying about concurrency issues or data corruption, as its value cannot be changed once it's created.

4. **Hashing Consistency**: String immutability ensures that the hash code of a string (used in collections like `HashMap` or `HashSet`) remains consistent over time, which is vital for efficient data retrieval and comparison.

### 123. **How many objects does the following code create?**

Let's analyze this code:

```java
String str1 = "Hello";
String str2 = "Hello";
String str3 = new String("Hello");
```

- **Object 1**: The string literal `"Hello"` is added to the **String Pool**. This ensures that `"Hello"` is reused across the program wherever it appears as a literal.
- **Object 2**: The second reference `str2` also points to the same string literal `"Hello"` from the String Pool.
- **Object 3**: The `new String("Hello")` creates a **new `String` object** in the heap, which is not part of the String Pool.

So, the total number of objects created is:
- **1 object in the String Pool** for `"Hello"`.
- **1 object in the heap** created by `new String("Hello")`.

Thus, **2 objects** are created.

### 124. **How many ways are there in Java to create a String object?**

In Java, there are primarily two ways to create a `String` object:

1. **Using String Literals (String Pool)**:
   - When you use a string literal, Java checks if the string already exists in the **String Pool** (a special area in memory to store string literals). If the string already exists, it reuses the existing object; otherwise, it creates a new object and adds it to the pool.

   **Example**:
   ```java
   String str1 = "Hello";  // String literal
   ```

2. **Using the `new` Keyword**:
   - When you create a `String` using the `new` keyword, a new `String` object is created on the heap, even if the same string exists in the String Pool. This is independent of the String Pool.

   **Example**:
   ```java
   String str2 = new String("Hello");  // Using new keyword
   ```

### Summary:

- **Immutable String**: A `String` object cannot be modified after it is created. Any operation that seems to modify a string results in a new `String` object being created.
- A `String` object is immutable to ensure security, efficiency, thread-safety, and consistency of hash codes.
- In the provided code, **2 objects** are created — one in the String Pool and another in the heap.
- There are **two main ways** to create a `String` object:
  1. **Using string literals** (which may reference the String Pool).
  2. **Using the `new` keyword** (which creates a new object in the heap).

### 125. **How many objects does the following code create?**

Let's analyze the code:

```java
String str1 = "Hello";
String str2 = "Hello";
String str3 = new String("Hello");
```

1. **String str1 = "Hello";**
   - The string literal `"Hello"` is added to the **String Pool**. If `"Hello"` does not already exist in the pool, it is created. If it already exists, it is reused.

2. **String str2 = "Hello";**
   - This also refers to the string literal `"Hello"`. Since it was already placed in the String Pool (by `str1`), **no new object** is created in the heap.

3. **String str3 = new String("Hello");**
   - This creates a **new String object** on the heap. Even though the string `"Hello"` exists in the String Pool, the `new String()` explicitly creates a new object in the heap.

#### Objects created:
- **1 object in the String Pool** for `"Hello"`.
- **1 object in the heap** due to the `new String("Hello")`.

**Total number of objects created = 2**.

### 126. **What is String interning?**

**String interning** is a process in Java where a string is stored in a special pool called the **String Pool** (or **String Literal Pool**). The main idea behind string interning is to **reuse** immutable string objects so that the same string literal does not take up memory repeatedly in the heap. When a string is interned, only one instance of that string is stored in the pool, even if the string is referenced multiple times in the program.

- When you use a string literal (e.g., `"Hello"`), Java automatically interns the string.
- When you create a string using `new String("Hello")`, a new object is created in the heap, but the string `"Hello"` is not interned unless you call `str.intern()` explicitly.

#### Example:
```java
String s1 = "Hello";  // "Hello" is automatically added to the String Pool.
String s2 = new String("Hello");  // "Hello" is in the pool but a new object is created on the heap.
String s3 = s2.intern();  // Interns the string "Hello", pointing to the string from the String Pool.
```

### 127. **Why does Java use the String literal concept?**

Java uses the **String literal concept** primarily for the following reasons:

1. **Memory Efficiency**: By using the String literal pool, Java reuses string objects rather than creating duplicate objects every time a string is used. This saves memory.
   
2. **Performance**: Reusing string literals improves the performance of string operations. Since strings are immutable, using the same string object from the String Pool allows for faster comparisons (using reference equality rather than value comparison).

3. **Consistency**: The String Pool ensures that strings with the same value are stored in one location in memory, preventing the need to create multiple instances of the same string.

4. **Simpler Garbage Collection**: String literals in the pool are never garbage collected, which ensures that they are always available, making them a reliable mechanism for commonly used strings.

### 128. **What is the basic difference between a String and StringBuffer object?**

The main difference between a **`String`** and a **`StringBuffer`** in Java lies in **mutability**:

1. **Mutability**:
   - **String**: Strings are **immutable**. Once a `String` object is created, its value cannot be changed. Any operation on a string (like concatenation) creates a new `String` object.
   - **StringBuffer**: `StringBuffer` objects are **mutable**, meaning the content of the string can be changed without creating a new object. It is designed for **efficient string manipulation**.

2. **Performance**:
   - **String**: Since `String` objects are immutable, every modification results in the creation of a new object, which can be less efficient when performing many string modifications.
   - **StringBuffer**: `StringBuffer` is more efficient when performing multiple string manipulations (like appending or inserting characters) because it modifies the content in-place without creating new objects.

3. **Thread Safety**:
   - **String**: `String` is immutable, so it is inherently thread-safe.
   - **StringBuffer**: `StringBuffer` is **synchronized** and thread-safe for use in multi-threaded environments. However, if you do not need synchronization, **StringBuilder** (a non-synchronized version) can be used for better performance.

#### Example:
```java
// String Example (Immutable)
String str = "Hello";
str = str + " World"; // A new String object is created

// StringBuffer Example (Mutable)
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");  // The same StringBuffer object is modified
```

### 129. **How will you create an immutable class in Java?**

To create an **immutable class** in Java, you need to follow these steps:

1. **Declare the class as `final`**: This prevents subclassing and ensures that the class cannot be extended to alter its behavior.

2. **Make all fields `private` and `final`**: This ensures that the fields cannot be modified outside the class and that their values are set only once.

3. **Initialize the fields in the constructor**: Ensure that the fields are set only during object creation.

4. **Provide `getter` methods only**: Provide access to the fields via getter methods but **do not provide setter methods**.

5. **Ensure that mutable objects are properly handled**: If the class contains references to mutable objects (e.g., arrays, lists), ensure that these objects are **deep-copied** to prevent modification from outside the class.

#### Example of an Immutable Class:
```java
public final class ImmutablePerson {
    private final String name;
    private final int age;

    // Constructor initializes fields
    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods only (no setters)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

#### Key Points:
- The class is `final`, so it cannot be subclassed.
- The fields are `private` and `final`, so they cannot be modified after construction.
- No setter methods are provided.
- If the class contained mutable objects, we would ensure that they are copied when passed to the constructor and when accessed through getter methods to maintain immutability.

### Summary:

- **String internment**: Refers to storing strings in a common pool to avoid duplicate string objects.
- **String literal concept**: Saves memory and improves performance by reusing string values.
- **String vs StringBuffer**: `String` is immutable, while `StringBuffer` is mutable and optimized for string manipulations.
- **Creating an immutable class**: Ensure the class is `final`, fields are `private` and `final`, and no setter methods are provided. Properly handle mutable objects through defensive copying.

### 130. **What is the use of the `toString()` method in Java?**

The **`toString()`** method in Java is a method from the `Object` class that is used to return a string representation of an object. Every class in Java inherits from the `Object` class, so every class has a `toString()` method. By default, the `toString()` method returns a string that includes the class name and the hash code of the object. However, it is common to override this method to provide a more meaningful string representation of an object, which is useful for debugging or logging purposes.

#### Default `toString()` Implementation:
- The default implementation of `toString()` returns a string of the form:  
  `ClassName@HashCode` (e.g., `Person@15db9742`).

#### Example of Overriding `toString()` Method:
```java
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice", 25);
        System.out.println(person.toString());  // Output: Person{name='Alice', age=25}
    }
}
```

In this example, we override the `toString()` method to return a more human-readable string representation of the `Person` object, which can be useful when printing objects or logging information about them.

### 131. **Arrange the three classes String, StringBuffer, and StringBuilder in the order of efficiency for String processing operations?**

The three classes—**String**, **StringBuffer**, and **StringBuilder**—are used for handling strings in Java, but their performance and efficiency vary depending on how they are used:

1. **`String`** (least efficient for modification):
   - The `String` class is **immutable**, meaning that once a `String` object is created, its value cannot be changed. Any modification to a `String` results in the creation of a new `String` object.
   - While it is efficient for **small, fixed values**, **repeated modifications** (like concatenations) lead to high memory consumption and reduced performance because new `String` objects are created each time.

   **Efficiency Ranking**: Least efficient when performing many modifications.

2. **`StringBuffer`** (efficient in multi-threaded environments):
   - The `StringBuffer` class is **mutable** (can be modified without creating new objects). It is also **synchronized**, meaning it is thread-safe. 
   - It is designed for situations where multiple modifications to a string (like appending or inserting) are required.
   - However, the thread synchronization makes `StringBuffer` slightly slower than `StringBuilder` when thread-safety is not required.

   **Efficiency Ranking**: More efficient than `String` when modifying strings, but thread-safety comes at a cost.

3. **`StringBuilder`** (most efficient for single-threaded environments):
   - The `StringBuilder` class is also **mutable** (can be modified without creating new objects) but is **not synchronized**. This makes it faster than `StringBuffer` because there is no overhead from synchronization.
   - It is ideal for use in **single-threaded applications** where you need to modify strings repeatedly (e.g., during string concatenation, appending).

   **Efficiency Ranking**: Most efficient for string processing when thread-safety is not a concern.

### Summary of Efficiency Ranking:
1. **StringBuilder** (most efficient for string operations in single-threaded scenarios)
2. **StringBuffer** (more efficient than String, but slower due to synchronization)
3. **String** (least efficient due to immutability and the creation of new objects on each modification)

Thus, for **string processing operations** (like concatenation, appending, or modification), **`StringBuilder`** is the most efficient, followed by **`StringBuffer`**, and **`String`** is the least efficient when it comes to frequent modifications.
