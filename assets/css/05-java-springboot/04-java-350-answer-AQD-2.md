# 🔹 Core Java Fundamentals

## Basic Concepts

### 1. What is Java and what are its key features?

Java is a high-level, object-oriented programming language developed by Sun Microsystems (now Oracle). 

**Key Features:**
- Platform independence ("Write Once, Run Anywhere")
- Object-oriented programming
- Automatic memory management (Garbage Collection)
- Strong type checking
- Multithreading support
- Robust security features
- Rich API and libraries

### 2. Explain the difference between JDK, JRE, and JVM.

**JVM (Java Virtual Machine):**
- Executes Java bytecode
- Platform-specific runtime engine
- Provides runtime environment

**JRE (Java Runtime Environment):**
- Includes JVM + standard libraries
- Required to run Java applications
- No development tools included

**JDK (Java Development Kit):**
- Includes JRE + development tools
- Contains compiler (javac), debugger, documentation tools
- Required for Java development

### 3. What is platform independence in Java?

Platform independence means Java code can run on any operating system without modification. Java achieves this by:
- Compiling source code to platform-neutral bytecode
- JVM interprets bytecode on specific platforms
- Same compiled code works across different operating systems

### 4. How does Java achieve "write once, run anywhere"?

Java achieves WORA through:
1. **Compilation:** Source code (.java) compiles to bytecode (.class)
2. **Bytecode:** Platform-neutral intermediate code
3. **JVM:** Platform-specific virtual machine interprets bytecode
4. **Result:** Same bytecode runs on any platform with JVM

### 5. What are the main principles of Object-Oriented Programming?

The four main OOP principles are:

**1. Encapsulation:**
- Data hiding and bundling
- Private fields with public methods

**2. Inheritance:**
- Code reuse through parent-child relationships
- "IS-A" relationship

**3. Polymorphism:**
- Multiple forms of same method
- Method overloading and overriding

**4. Abstraction:**
- Hiding implementation details
- Focus on what object does, not how

### 6. Explain the difference between class and object.

**Class:**
- Blueprint or template
- Defines structure and behavior
- No memory allocation until instantiated
- Example: `class Car { ... }`

**Object:**
- Instance of a class
- Actual entity in memory
- Has specific values for class attributes
- Example: `Car myCar = new Car();`

### 7. What is encapsulation and how is it implemented in Java?

**Encapsulation** is the principle of hiding internal implementation details and exposing only necessary functionality.

**Implementation in Java:**
```java
public class Student {
    private String name;  // Private field
    private int age;      // Private field
    
    // Public getter method
    public String getName() {
        return name;
    }
    
    // Public setter method with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
```

**Benefits:**
- Data protection
- Controlled access
- Flexibility to change implementation

### 8. What is inheritance and what are its types?

**Inheritance** allows a class to acquire properties and methods from another class.

**Types in Java:**

**1. Single Inheritance:**
```java
class Animal { }
class Dog extends Animal { }
```

**2. Multilevel Inheritance:**
```java
class Animal { }
class Mammal extends Animal { }
class Dog extends Mammal { }
```

**3. Hierarchical Inheritance:**
```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
```

**Note:** Java doesn't support multiple inheritance of classes to avoid diamond problem.

### 9. What is polymorphism? Explain with examples.

**Polymorphism** means "many forms" - same method name behaving differently based on context.

**Types:**

**1. Compile-time Polymorphism (Method Overloading):**
```java
class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
}
```

**2. Runtime Polymorphism (Method Overriding):**
```java
class Animal {
    public void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    public void sound() { System.out.println("Bark"); }
}

Animal animal = new Dog();
animal.sound(); // Output: "Bark"
```

### 10. What is abstraction and how is it achieved in Java?

**Abstraction** hides complex implementation details while showing only essential features to the user.

**Achievement in Java:**

**1. Abstract Classes (Partial Abstraction):**
```java
abstract class Shape {
    abstract void draw();  // Abstract method
    void display() {       // Concrete method
        System.out.println("Displaying shape");
    }
}
```

**2. Interfaces (Complete Abstraction):**
```java
interface Drawable {
    void draw();  // All methods are abstract by default
}
```

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

## Control Structures

### 21. What are the different types of loops in Java?

Java provides **4 types of loops**:

#### 1. **Traditional for loop**
```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```
- **Use when:** You know the number of iterations
- **Components:** initialization, condition, increment/decrement

#### 2. **Enhanced for loop (for-each)**
```java
int[] array = {1, 2, 3, 4, 5};
for (int element : array) {
    System.out.println(element);
}
```
- **Use when:** Iterating over collections/arrays
- **Advantage:** Cleaner syntax, no index management

#### 3. **While loop (pre-test)**
```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}
```
- **Use when:** Number of iterations unknown
- **Condition:** Checked before each iteration

#### 4. **Do-while loop (post-test)**
```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 10);
```
- **Use when:** Loop must execute at least once
- **Condition:** Checked after each iteration

### 22. What is the difference between break and continue?

| Statement | Purpose | Effect | Usage |
|-----------|---------|--------|-------|
| `break` | Exit loop completely | Terminates entire loop | Exit when condition met |
| `continue` | Skip current iteration | Jumps to next iteration | Skip specific cases |

#### **break example:**
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // Exits loop completely
    }
    System.out.println(i);  // Prints: 0, 1, 2, 3, 4
}
```

#### **continue example:**
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        continue;  // Skips only i=5
    }
    System.out.println(i);  // Prints: 0, 1, 2, 3, 4, 6, 7, 8, 9
}
```

#### **Labeled break/continue:**
```java
outer: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (i == 1 && j == 1) {
            break outer;  // Breaks outer loop
        }
        System.out.println(i + "," + j);
    }
}
```

### 23. What is enhanced for loop (for-each)?

**Enhanced for loop** simplifies iteration over arrays and collections.

#### **Syntax:**
```java
for (dataType variable : collection/array) {
    // Use variable
}
```

#### **Examples:**

**With Arrays:**
```java
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    System.out.println(num);
}
```

**With Collections:**
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}
```

#### **Advantages:**
- **Cleaner syntax** - no index management
- **Safer** - eliminates ArrayIndexOutOfBoundsException
- **More readable** - intent is clearer
- **Less error-prone** - no off-by-one errors

#### **Limitations:**
- **No index access** - can't get current position
- **Read-only** - can't modify array/collection structure
- **Single direction** - only forward iteration
- **No partial iteration** - can't start from middle

#### **When to use traditional vs enhanced:**
```java
// Use enhanced for - simple iteration
for (String item : list) {
    System.out.println(item);
}

// Use traditional for - need index
for (int i = 0; i < list.size(); i++) {
    System.out.println(i + ": " + list.get(i));
}
```

### 24. When would you use switch vs if-else?

#### **Use Switch When:**
- **Single variable** with multiple discrete values
- **Equality comparisons** only
- **Better readability** for many conditions
- **Performance matters** (compiler optimization)

#### **Use If-Else When:**
- **Complex conditions** (ranges, multiple variables)
- **Boolean expressions**
- **Different comparison operators** (>, <, >=, etc.)
- **Object method calls** in conditions

#### **Switch Example (Good Use Case):**
```java
// Menu selection - perfect for switch
int choice = scanner.nextInt();
switch (choice) {
    case 1:
        addStudent();
        break;
    case 2:
        deleteStudent();
        break;
    case 3:
        viewStudents();
        break;
    default:
        System.out.println("Invalid choice");
}
```

#### **If-Else Example (Good Use Case):**
```java
// Complex conditions - better with if-else
int score = 85;
if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";
} else if (score >= 70) {
    grade = "C";
} else {
    grade = "F";
}
```

#### **Performance Comparison:**
- **Switch:** O(1) with jump table for many cases
- **If-Else:** O(n) linear search through conditions

#### **Readability Comparison:**
```java
// Switch - cleaner for discrete values
switch (dayOfWeek) {
    case MONDAY: return "Start of work week";
    case FRIDAY: return "TGIF!";
    case SATURDAY: case SUNDAY: return "Weekend!";
}

// If-else - better for ranges
if (age < 13) return "Child";
else if (age < 20) return "Teenager";
else if (age < 60) return "Adult";
else return "Senior";
```

### 25. What are the rules for switch statement in Java?

#### **Expression Type Rules:**
Switch expression must be one of:
- **Integral types:** `byte`, `short`, `int`, `char`
- **Wrapper classes:** `Byte`, `Short`, `Integer`, `Character`
- **String** (Java 7+)
- **Enum** types

```java
// Valid switch expressions
switch (intValue) { }      // int
switch (stringValue) { }   // String
switch (enumValue) { }     // Enum
switch (charValue) { }     // char

// Invalid switch expressions
// switch (floatValue) { }    // float - NOT allowed
// switch (booleanValue) { } // boolean - NOT allowed
```

#### **Case Label Rules:**

**1. Must be compile-time constants:**
```java
final int CONSTANT = 10;
int variable = 20;

switch (x) {
    case 5:         // OK - literal
    case CONSTANT:  // OK - final variable
    // case variable:  // ERROR - not constant
}
```

**2. Must be unique:**
```java
switch (x) {
    case 1:
        // code
        break;
    // case 1:  // ERROR - duplicate case
}
```

**3. Must be compatible with switch expression type:**
```java
byte b = 10;
switch (b) {
    case 100:   // OK - within byte range
    // case 200:   // ERROR - outside byte range (-128 to 127)
}
```

#### **Break Statement Rules:**
```java
switch (x) {
    case 1:
        System.out.println("One");
        break;  // Prevents fall-through
    case 2:
        System.out.println("Two");
        // No break - falls through to case 3
    case 3:
        System.out.println("Two or Three");
        break;
    default:
        System.out.println("Other");
}
```

#### **Default Case Rules:**
- **Optional** but recommended
- **Can appear anywhere** (usually at end)
- **Only one default** allowed per switch

```java
switch (x) {
    default:        // Can be first
        System.out.println("Default");
        break;
    case 1:
        System.out.println("One");
        break;
}
```

#### **Modern Switch Features (Java 12+):**

**Switch Expressions:**
```java
String result = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> "6";
    case TUESDAY -> "7";
    case THURSDAY, SATURDAY -> "8";
    case WEDNESDAY -> "9";
};
```

**Yield Statement:**
```java
int result = switch (x) {
    case 1 -> {
        System.out.println("Processing 1");
        yield 10;
    }
    case 2 -> 20;
    default -> 0;
};
```

#### **Best Practices:**
1. **Always include default** case
2. **Use break** to prevent fall-through (unless intentional)
3. **Group related cases** together
4. **Keep cases simple** - complex logic in separate methods
5. **Consider enums** for type safety

# 🔹 Object-Oriented Programming

## Classes and Objects

### 26. What is a constructor in Java?

A **constructor** is a special method that initializes objects when they are created.

#### **Key Characteristics:**
- **Same name** as the class
- **No return type** (not even void)
- **Automatically called** when object is created with `new`
- **Purpose:** Initialize object state and allocate resources

#### **Basic Example:**
```java
public class Student {
    private String name;
    private int age;
    
    // Constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Usage
Student student = new Student("Alice", 20); // Constructor called automatically
```

#### **Constructor vs Method:**
| Constructor | Regular Method |
|-------------|----------------|
| Same name as class | Any name |
| No return type | Has return type |
| Called automatically | Called explicitly |
| Initializes object | Performs operations |

### 27. What are the types of constructors?

#### **1. Default Constructor (No-argument)**
```java
public class Student {
    private String name = "Unknown";
    private int age = 0;
    
    // Default constructor
    public Student() {
        // Initialize with default values
    }
}
```
- **Provided automatically** if no constructor is defined
- **Disappears** when you define any constructor

#### **2. Parameterized Constructor**
```java
public class Student {
    private String name;
    private int age;
    
    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```
- **Takes arguments** to initialize with specific values
- **Most commonly used** type

#### **3. Copy Constructor (Manual Implementation)**
```java
public class Student {
    private String name;
    private int age;
    
    // Copy constructor
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
    }
}
```
- **Java doesn't provide** built-in copy constructors
- **Must implement manually** if needed

### 28. What is constructor chaining?

**Constructor chaining** is calling one constructor from another constructor.

#### **Same Class Chaining (this()):**
```java
public class Student {
    private String name;
    private int age;
    private String course;
    
    // Default constructor
    public Student() {
        this("Unknown", 0);  // Calls parameterized constructor
    }
    
    // Parameterized constructor
    public Student(String name, int age) {
        this(name, age, "Not Assigned");  // Calls 3-parameter constructor
    }
    
    // Full constructor
    public Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }
}
```

#### **Parent Class Chaining (super()):**
```java
class Person {
    protected String name;
    
    public Person(String name) {
        this.name = name;
    }
}

class Student extends Person {
    private int studentId;
    
    public Student(String name, int studentId) {
        super(name);  // Calls parent constructor
        this.studentId = studentId;
    }
}
```

#### **Rules:**
- **Must be first statement** in constructor
- **Cannot use both** `this()` and `super()` in same constructor
- **Prevents code duplication**

### 29. Can you call a constructor from another constructor?

**Yes**, but with specific rules:

#### **Using this() - Same Class:**
```java
public class Rectangle {
    private int width, height;
    
    public Rectangle() {
        this(1, 1);  // ✓ Valid - calls parameterized constructor
    }
    
    public Rectangle(int side) {
        this(side, side);  // ✓ Valid - calls 2-parameter constructor
    }
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
```

#### **Using super() - Parent Class:**
```java
class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
}

class Circle extends Shape {
    private int radius;
    
    public Circle(String color, int radius) {
        super(color);  // ✓ Valid - calls parent constructor
        this.radius = radius;
    }
}
```

#### **Invalid Examples:**
```java
public Rectangle(int width, int height) {
    this.width = width;
    // this(1, 1);  // ✗ Error - not first statement
}

public Rectangle() {
    this(1, 1);   // ✓ Valid
    super();      // ✗ Error - cannot use both this() and super()
}
```

### 30. What is the difference between this and super keywords?

| Aspect | `this` | `super` |
|--------|--------|---------|
| **Refers to** | Current object | Parent class |
| **Usage** | Current class members | Parent class members |
| **Constructor** | `this()` - same class | `super()` - parent class |
| **Scope** | Current class | Parent class |

#### **this keyword examples:**
```java
public class Student {
    private String name;
    
    public void setName(String name) {
        this.name = name;        // Distinguish parameter from field
    }
    
    public Student() {
        this("Default");         // Call another constructor
    }
    
    public Student(String name) {
        this.name = name;
    }
    
    public void display() {
        this.printInfo();        // Call current class method
    }
}
```

#### **super keyword examples:**
```java
class Person {
    protected String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public void display() {
        System.out.println("Person: " + name);
    }
}

class Student extends Person {
    private int studentId;
    
    public Student(String name, int studentId) {
        super(name);             // Call parent constructor
        this.studentId = studentId;
    }
    
    @Override
    public void display() {
        super.display();         // Call parent method
        System.out.println("Student ID: " + studentId);
    }
}
```

### 31. What is method overloading?

**Method overloading** is having multiple methods with the same name but different parameters.

#### **Overloading Rules:**
- **Same method name**
- **Different parameter list** (number, type, or order)
- **Return type** can be different but not sufficient alone
- **Compile-time polymorphism**

#### **Examples:**
```java
public class Calculator {
    // Different number of parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Different parameter types
    public double add(double a, double b) {
        return a + b;
    }
    
    // Different parameter order
    public void display(String name, int age) {
        System.out.println(name + " is " + age + " years old");
    }
    
    public void display(int age, String name) {
        System.out.println(name + " is " + age + " years old");
    }
}
```

#### **Invalid Overloading:**
```java
public class Invalid {
    public int method(int x) { return x; }
    // public double method(int x) { return x; }  // ✗ Error - only return type differs
}
```

### 32. What is method overriding?

**Method overriding** is redefining a parent class method in the child class with the same signature.

#### **Basic Example:**
```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {  // Overriding parent method
        System.out.println("Dog barks");
    }
}

// Usage
Animal animal = new Dog();
animal.makeSound();  // Output: "Dog barks" (runtime polymorphism)
```

#### **Key Points:**
- **Same method signature** (name, parameters, return type)
- **Runtime polymorphism** - actual method determined at runtime
- **@Override annotation** recommended for safety
- **Enables dynamic behavior**

### 33. What are the rules for method overriding?

#### **Method Signature Rules:**
1. **Exact same method name**
2. **Same parameter list** (number, type, order)
3. **Same or covariant return type**

#### **Access Modifier Rules:**
```java
class Parent {
    protected void method() { }  // protected access
}

class Child extends Parent {
    @Override
    public void method() { }     // ✓ Valid - more accessible
    
    // private void method() { }  // ✗ Error - more restrictive
}
```

#### **Exception Handling Rules:**
```java
class Parent {
    public void method() throws IOException { }
}

class Child extends Parent {
    @Override
    public void method() throws FileNotFoundException { }  // ✓ Valid - subclass exception
    
    // public void method() throws Exception { }  // ✗ Error - broader exception
}
```

#### **Cannot Override:**
- **static methods** (method hiding instead)
- **private methods** (not inherited)
- **final methods** (cannot be changed)
- **constructors** (not inherited)

#### **Covariant Return Type:**
```java
class Animal {
    public Animal reproduce() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog reproduce() {  // ✓ Valid - Dog is subclass of Animal
        return new Dog();
    }
}
```

### 34. What is the difference between overloading and overriding?

| Aspect | Method Overloading | Method Overriding |
|--------|-------------------|-------------------|
| **Definition** | Same name, different parameters | Same signature, different implementation |
| **Inheritance** | Same class | Parent-child classes |
| **Polymorphism** | Compile-time | Runtime |
| **Parameters** | Must be different | Must be same |
| **Return Type** | Can be different | Same or covariant |
| **Access Modifier** | Can be any | Cannot be more restrictive |
| **Purpose** | Add functionality | Change behavior |

#### **Overloading Example:**
```java
class Calculator {
    public int multiply(int a, int b) {           // 2 parameters
        return a * b;
    }
    
    public int multiply(int a, int b, int c) {    // 3 parameters
        return a * b * c;
    }
    
    public double multiply(double a, double b) {  // Different type
        return a * b;
    }
}
```

#### **Overriding Example:**
```java
class Shape {
    public double area() {
        return 0;
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {  // Same signature, different implementation
        return Math.PI * radius * radius;
    }
}
```

### 35. What is dynamic method dispatch?

**Dynamic method dispatch** is the mechanism where the correct overridden method is called at runtime based on the actual object type.

#### **How it Works:**
```java
class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

// Dynamic method dispatch
Animal animal1 = new Dog();  // Reference type: Animal, Object type: Dog
Animal animal2 = new Cat();  // Reference type: Animal, Object type: Cat

animal1.makeSound();  // Output: "Bark" - Dog's method called
animal2.makeSound();  // Output: "Meow" - Cat's method called
```

#### **Key Points:**
- **Runtime decision** - method determined by actual object type
- **Reference type** doesn't matter for method selection
- **Enables polymorphism** - same interface, different behaviors
- **Foundation of OOP** flexibility

#### **Practical Example:**
```java
public class ShapeProcessor {
    public void processShapes(Animal[] animals) {
        for (Animal animal : animals) {
            animal.makeSound();  // Correct method called for each object type
        }
    }
}

// Usage
Animal[] animals = {new Dog(), new Cat(), new Dog()};
processor.processShapes(animals);
// Output: Bark, Meow, Bark
```

#### **Benefits:**
- **Code flexibility** - add new types without changing existing code
- **Extensibility** - easy to add new implementations
- **Maintainability** - changes localized to specific classes

## Inheritance

### 36. What is single inheritance in Java?

**Single inheritance** means a class can extend only one parent class directly.

#### **Key Characteristics:**
- **One parent class** per child class
- **Clear hierarchy** - no ambiguity
- **All non-private members** inherited
- **Simplifies design** and maintenance

#### **Example:**
```java
// Parent class
class Animal {
    protected String name;
    
    public void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class - single inheritance
class Dog extends Animal {
    public void bark() {
        System.out.println("Dog is barking");
    }
}

// Usage
Dog dog = new Dog();
dog.eat();   // Inherited from Animal
dog.bark();  // Own method
```

#### **Inheritance Chain:**
```java
class Animal { }
class Mammal extends Animal { }      // Mammal IS-A Animal
class Dog extends Mammal { }         // Dog IS-A Mammal (and Animal)

// Dog inherits from Mammal, which inherits from Animal
```

#### **Benefits:**
- **Simplicity** - clear parent-child relationship
- **No ambiguity** - single source of inheritance
- **Easy maintenance** - changes propagate clearly

### 37. Why doesn't Java support multiple inheritance?

Java doesn't support **multiple inheritance of classes** to avoid complexity and ambiguity.

#### **Problems with Multiple Inheritance:**

**1. Diamond Problem:**
```java
// This is NOT allowed in Java
class A {
    public void method() { System.out.println("A"); }
}

class B extends A {
    public void method() { System.out.println("B"); }
}

class C extends A {
    public void method() { System.out.println("C"); }
}

// class D extends B, C { }  // ✗ NOT allowed - which method() to inherit?
```

**2. Complexity Issues:**
- **Constructor chaining** becomes complex
- **Method resolution** ambiguity
- **Memory layout** complications
- **Maintenance difficulties**

#### **Java's Solution:**
```java
// Single inheritance + Multiple interface implementation
class Animal {
    public void eat() { }
}

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck extends Animal implements Flyable, Swimmable {
    public void fly() { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
}
```

#### **Benefits of Java's Approach:**
- **Clear method resolution**
- **Simplified design**
- **Better maintainability**
- **Type safety**

### 38. What is the diamond problem?

The **diamond problem** occurs when a class inherits from two classes that both inherit from the same base class.

#### **Diamond Problem Illustration:**
```
    A
   / \
  B   C
   \ /
    D
```

#### **The Problem:**
```java
// Hypothetical scenario (NOT valid Java)
class A {
    public void method() { System.out.println("A's method"); }
}

class B extends A {
    public void method() { System.out.println("B's method"); }
}

class C extends A {
    public void method() { System.out.println("C's method"); }
}

// If this were allowed:
// class D extends B, C {
//     // Which method() should D inherit?
//     // B's method() or C's method()?
// }
```

#### **Ambiguity Questions:**
- Which `method()` should class D inherit?
- How many instances of class A should D contain?
- Which constructor chain should be followed?

#### **Real-world Example:**
```java
// This creates confusion
class Vehicle {
    protected int wheels;
}

class Car extends Vehicle {
    public Car() { wheels = 4; }
}

class Boat extends Vehicle {
    public Boat() { wheels = 0; }
}

// If multiple inheritance were allowed:
// class AmphibiousCar extends Car, Boat {
//     // How many wheels? 4 or 0?
// }
```

### 39. How does Java solve the diamond problem?

Java solves the diamond problem through **single inheritance + interfaces**.

#### **1. Single Class Inheritance:**
```java
// Only one class can be extended
class Animal {
    public void breathe() { System.out.println("Breathing"); }
}

class Mammal extends Animal {
    public void giveBirth() { System.out.println("Giving birth"); }
}

class Dog extends Mammal {  // Single inheritance chain
    public void bark() { System.out.println("Barking"); }
}
```

#### **2. Multiple Interface Implementation:**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck extends Animal implements Flyable, Swimmable {
    public void fly() { System.out.println("Flying"); }
    public void swim() { System.out.println("Swimming"); }
}
```

#### **3. Default Method Conflicts (Java 8+):**
```java
interface A {
    default void method() { System.out.println("A"); }
}

interface B {
    default void method() { System.out.println("B"); }
}

class C implements A, B {
    @Override
    public void method() {
        A.super.method();  // Explicitly choose A's method
        // or B.super.method();  // or choose B's method
        // or provide own implementation
    }
}
```

#### **Benefits:**
- **Clear resolution** - explicit choice required
- **Type safety** maintained
- **Flexibility** through interfaces
- **No ambiguity** in method calls

### 40. What is the use of super keyword?

The **super keyword** provides access to parent class members.

#### **1. Calling Parent Constructor:**
```java
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name);        // Call parent constructor
        this.breed = breed;
    }
}
```

#### **2. Accessing Parent Methods:**
```java
class Animal {
    public void display() {
        System.out.println("I am an animal");
    }
}

class Dog extends Animal {
    @Override
    public void display() {
        super.display();    // Call parent method
        System.out.println("I am a dog");
    }
}
```

#### **3. Accessing Parent Fields:**
```java
class Animal {
    protected String type = "Animal";
}

class Dog extends Animal {
    private String type = "Dog";
    
    public void showTypes() {
        System.out.println("Child type: " + this.type);  // "Dog"
        System.out.println("Parent type: " + super.type); // "Animal"
    }
}
```

#### **Rules:**
- **super()** must be first statement in constructor
- **Cannot use** in static context
- **Cannot chain** super.super.method()

### 41. What is method hiding?

**Method hiding** occurs when a child class defines a static method with the same signature as a parent class static method.

#### **Method Hiding Example:**
```java
class Parent {
    public static void staticMethod() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {
    public static void staticMethod() {  // Method hiding
        System.out.println("Child static method");
    }
}

// Usage
Parent.staticMethod();  // "Parent static method"
Child.staticMethod();   // "Child static method"

Parent p = new Child();
p.staticMethod();       // "Parent static method" - reference type matters!
```

#### **Method Hiding vs Overriding:**

| Aspect | Method Hiding | Method Overriding |
|--------|---------------|-------------------|
| **Method Type** | Static | Instance |
| **Binding** | Compile-time | Runtime |
| **Reference Type** | Matters | Doesn't matter |
| **Polymorphism** | No | Yes |

#### **Demonstration:**
```java
class Test {
    public static void main(String[] args) {
        Parent p1 = new Parent();
        Parent p2 = new Child();
        Child c = new Child();
        
        p1.staticMethod();  // "Parent static method"
        p2.staticMethod();  // "Parent static method" (reference type)
        c.staticMethod();   // "Child static method"
    }
}
```

### 42. Can you override static methods?

**No**, you cannot override static methods. Static methods belong to the class, not instances.

#### **Why Static Methods Cannot Be Overridden:**
- **Class-level binding** - belongs to class, not object
- **No polymorphism** - resolved at compile time
- **Memory allocation** - single copy per class
- **No dynamic dispatch** - reference type determines method

#### **What Happens Instead:**
```java
class Parent {
    public static void method() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    public static void method() {  // Method hiding, NOT overriding
        System.out.println("Child static");
    }
}

// Demonstration
Parent p = new Child();
p.method();  // Output: "Parent static" (not "Child static")
```

#### **Attempting to Override:**
```java
class Parent {
    public static void method() { }
}

class Child extends Parent {
    // @Override  // ✗ Compiler error if uncommented
    public static void method() { }  // This is hiding, not overriding
}
```

#### **Instance Method Overriding (for comparison):**
```java
class Parent {
    public void instanceMethod() {
        System.out.println("Parent instance");
    }
}

class Child extends Parent {
    @Override
    public void instanceMethod() {  // True overriding
        System.out.println("Child instance");
    }
}

Parent p = new Child();
p.instanceMethod();  // Output: "Child instance" (polymorphism works)
```

### 43. Can you override private methods?

**No**, you cannot override private methods because they are not inherited.

#### **Why Private Methods Cannot Be Overridden:**
- **Not inherited** - child class cannot see private methods
- **No access** - private methods are class-specific
- **No polymorphism** - not part of class interface
- **Encapsulation** - private methods are implementation details

#### **Example:**
```java
class Parent {
    private void privateMethod() {
        System.out.println("Parent private method");
    }
    
    public void callPrivate() {
        privateMethod();  // Calls Parent's private method
    }
}

class Child extends Parent {
    // This is NOT overriding - it's a new method
    private void privateMethod() {
        System.out.println("Child private method");
    }
    
    public void testPrivate() {
        privateMethod();  // Calls Child's own private method
        callPrivate();    // Calls Parent's private method via public method
    }
}
```

#### **Demonstration:**
```java
Child child = new Child();
child.testPrivate();
// Output:
// "Child private method"
// "Parent private method"
```

#### **What You Can Do:**
```java
class Parent {
    protected void protectedMethod() {  // Use protected instead
        System.out.println("Parent protected");
    }
}

class Child extends Parent {
    @Override
    protected void protectedMethod() {  // Now this is overriding
        System.out.println("Child protected");
    }
}
```

### 44. What is covariant return type?

**Covariant return type** allows an overriding method to return a subtype of the return type declared in the parent method.

#### **Basic Example:**
```java
class Animal {
    public Animal reproduce() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog reproduce() {  // Covariant return - Dog is subtype of Animal
        return new Dog();
    }
}
```

#### **Rules:**
- **Return type** must be subclass of parent's return type
- **Primitive types** cannot be covariant
- **Must maintain** IS-A relationship

#### **Practical Example:**
```java
abstract class Shape {
    public abstract Shape clone();
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public Circle clone() {  // Returns Circle, not Shape
        return new Circle(this.radius);
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public Rectangle clone() {  // Returns Rectangle, not Shape
        return new Rectangle(this.width, this.height);
    }
}
```

#### **Benefits:**
- **Type safety** - no casting required
- **Better API** - more specific return types
- **Cleaner code** - eliminates type casting

#### **Invalid Examples:**
```java
class Parent {
    public String method() { return "parent"; }
}

class Child extends Parent {
    // public Object method() { }  // ✗ Error - Object is supertype of String
    // public int method() { }     // ✗ Error - different type hierarchy
}
```

### 45. What is the difference between IS-A and HAS-A relationship?

| Aspect | IS-A Relationship | HAS-A Relationship |
|--------|-------------------|-------------------|
| **Type** | Inheritance | Composition |
| **Keyword** | `extends` | Instance variables |
| **Relationship** | Child is a type of Parent | Class contains another class |
| **Coupling** | Tight coupling | Loose coupling |
| **Flexibility** | Less flexible | More flexible |

#### **IS-A Relationship (Inheritance):**
```java
// Dog IS-A Animal
class Animal {
    protected String name;
    
    public void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {  // Dog IS-A Animal
    public void bark() {
        System.out.println("Dog is barking");
    }
}

// Usage
Dog dog = new Dog();
Animal animal = dog;  // Valid - Dog IS-A Animal
```

#### **HAS-A Relationship (Composition):**
```java
// Car HAS-A Engine
class Engine {
    private String type;
    
    public Engine(String type) {
        this.type = type;
    }
    
    public void start() {
        System.out.println(type + " engine started");
    }
}

class Car {
    private Engine engine;  // Car HAS-A Engine
    private String model;
    
    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }
    
    public void startCar() {
        engine.start();  // Delegating to Engine
    }
}

// Usage
Engine v8Engine = new Engine("V8");
Car car = new Car("Sports Car", v8Engine);
```

#### **When to Use Each:**

**Use IS-A (Inheritance) when:**
- **True specialization** exists
- **Shared behavior** is needed
- **Polymorphism** is required
- **Liskov Substitution Principle** applies

**Use HAS-A (Composition) when:**
- **Functionality reuse** is needed
- **Flexible relationships** are required
- **Runtime behavior change** is needed
- **Multiple "parts"** are involved

#### **Real-world Examples:**

**IS-A Examples:**
```java
class Vehicle { }
class Car extends Vehicle { }     // Car IS-A Vehicle
class Truck extends Vehicle { }   // Truck IS-A Vehicle

class Shape { }
class Circle extends Shape { }    // Circle IS-A Shape
class Rectangle extends Shape { } // Rectangle IS-A Shape
```

**HAS-A Examples:**
```java
class University {
    private List<Student> students;  // University HAS-A Students
    private List<Professor> faculty; // University HAS-A Professors
}

class Computer {
    private CPU processor;           // Computer HAS-A CPU
    private Memory ram;             // Computer HAS-A Memory
    private Storage hardDrive;      // Computer HAS-A Storage
}
```

#### **Benefits Comparison:**

**IS-A Benefits:**
- **Code reuse** through inheritance
- **Polymorphism** support
- **Natural hierarchy** modeling

**HAS-A Benefits:**
- **Flexibility** - can change components
- **Loose coupling** - easier testing
- **Multiple inheritance** simulation
- **Better encapsulation**

## Interfaces and Abstract Classes

### 46. What is an interface in Java?

An **interface** is a contract that defines what methods a class must implement without providing implementation details.

#### **Key Characteristics:**
- **Contract definition** - specifies what classes must do
- **Abstract methods** by default (before Java 8)
- **Multiple inheritance** support
- **No instantiation** - cannot create objects directly
- **Loose coupling** - classes depend on interface, not implementation

#### **Basic Interface:**
```java
interface Drawable {
    void draw();           // Abstract method (implicitly public abstract)
    void resize(int size); // Abstract method
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    @Override
    public void resize(int size) {
        System.out.println("Resizing circle to " + size);
    }
}
```

#### **Interface Features:**
- **Variables:** `public static final` by default
- **Methods:** `public abstract` by default (before Java 8)
- **Access:** All members are public
- **Inheritance:** Can extend multiple interfaces

#### **Example with Constants:**
```java
interface Constants {
    int MAX_SIZE = 100;        // public static final
    String DEFAULT_NAME = "Unknown"; // public static final
}
```

### 47. What is an abstract class?

An **abstract class** is a class that cannot be instantiated and may contain both abstract and concrete methods.

#### **Key Characteristics:**
- **Cannot be instantiated** directly
- **Mix of abstract and concrete methods**
- **Can have constructors** and instance variables
- **Single inheritance** only
- **Partial abstraction**

#### **Basic Abstract Class:**
```java
abstract class Animal {
    protected String name;
    
    // Constructor
    public Animal(String name) {
        this.name = name;
    }
    
    // Concrete method
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void makeSound();
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks");
    }
}
```

#### **When to Use Abstract Classes:**
- **Shared code** among related classes
- **Common state** (instance variables)
- **Partial implementation** with some abstract methods
- **Constructor logic** needed

### 48. What is the difference between interface and abstract class?

| Aspect | Interface | Abstract Class |
|--------|-----------|----------------|
| **Abstraction** | Complete (100%) | Partial |
| **Methods** | Abstract by default | Mix of abstract & concrete |
| **Variables** | public static final only | Any type of variables |
| **Constructors** | Not allowed | Allowed |
| **Inheritance** | Multiple inheritance | Single inheritance |
| **Access Modifiers** | public only | Any access modifier |
| **Instantiation** | Cannot instantiate | Cannot instantiate |
| **Keyword** | `implements` | `extends` |

#### **Interface Example:**
```java
interface Vehicle {
    int MAX_SPEED = 200;  // public static final
    
    void start();         // public abstract
    void stop();          // public abstract
}

class Car implements Vehicle {
    @Override
    public void start() { System.out.println("Car started"); }
    
    @Override
    public void stop() { System.out.println("Car stopped"); }
}
```

#### **Abstract Class Example:**
```java
abstract class Vehicle {
    protected String brand;     // Instance variable
    private static int count;   // Static variable
    
    public Vehicle(String brand) {  // Constructor
        this.brand = brand;
        count++;
    }
    
    public void displayInfo() {     // Concrete method
        System.out.println("Brand: " + brand);
    }
    
    public abstract void start();   // Abstract method
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }
    
    @Override
    public void start() {
        System.out.println(brand + " car started");
    }
}
```

#### **When to Choose:**

**Use Interface when:**
- **Multiple inheritance** needed
- **Complete abstraction** required
- **Unrelated classes** need same contract
- **Loose coupling** is priority

**Use Abstract Class when:**
- **Code sharing** among related classes
- **Partial implementation** needed
- **Common state** (instance variables) required
- **Constructor logic** needed

### 49. Can an interface have constructors?

**No**, interfaces cannot have constructors.

#### **Why Interfaces Cannot Have Constructors:**
- **No instantiation** - interfaces cannot be instantiated
- **No instance variables** - nothing to initialize
- **Contract definition** - interfaces define behavior, not state
- **Multiple inheritance** - would create constructor conflicts

#### **What Happens Instead:**
```java
interface Drawable {
    // No constructors allowed
    // public Drawable() { }  // ✗ Compilation error
    
    void draw();
}

// Cannot instantiate interface
// Drawable d = new Drawable();  // ✗ Compilation error

// Must implement through a class
class Circle implements Drawable {
    public Circle() {  // Class constructor is fine
        // initialization code
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
```

#### **Interface Initialization:**
```java
interface Constants {
    // Variables are initialized at declaration
    int MAX_VALUE = 100;           // Initialized here
    List<String> NAMES = Arrays.asList("A", "B", "C");
    
    // Static block for complex initialization (Java 8+)
    static void initializeResources() {
        // Initialization logic
    }
}
```

### 50. What are default methods in interfaces?

**Default methods** are methods with implementation in interfaces, introduced in Java 8.

#### **Purpose:**
- **Interface evolution** - add methods without breaking existing code
- **Backward compatibility** - existing implementations don't break
- **Code reuse** - share common implementation
- **Optional implementation** - implementing classes can override if needed

#### **Syntax:**
```java
interface Drawable {
    void draw();  // Abstract method
    
    // Default method with implementation
    default void display() {
        System.out.println("Displaying drawable object");
        draw();  // Can call abstract methods
    }
    
    default void highlight() {
        System.out.println("Highlighting object");
    }
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    // Can optionally override default method
    @Override
    public void highlight() {
        System.out.println("Highlighting circle with red border");
    }
}
```

#### **Default Method Conflicts:**
```java
interface A {
    default void method() { System.out.println("A"); }
}

interface B {
    default void method() { System.out.println("B"); }
}

class C implements A, B {
    @Override
    public void method() {
        A.super.method();  // Explicitly call A's method
        // or B.super.method();  // or call B's method
        // or provide own implementation
    }
}
```

#### **Benefits:**
- **Library evolution** without breaking changes
- **Code sharing** between implementations
- **Flexibility** - can override if needed
- **Maintains interface contract**

### 51. What are static methods in interfaces?

**Static methods** in interfaces belong to the interface itself and provide utility functionality.

#### **Characteristics:**
- **Belong to interface** - not to implementing classes
- **Cannot be overridden** - final implementation
- **Called using interface name**
- **Utility methods** - helper functionality

#### **Example:**
```java
interface MathUtils {
    // Static method in interface
    static int add(int a, int b) {
        return a + b;
    }
    
    static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // Abstract method
    void performCalculation();
}

class Calculator implements MathUtils {
    @Override
    public void performCalculation() {
        // Use static methods from interface
        int sum = MathUtils.add(5, 3);
        double area = MathUtils.calculateArea(2.5);
        System.out.println("Sum: " + sum + ", Area: " + area);
    }
}

// Usage
int result = MathUtils.add(10, 20);  // Called on interface
```

#### **Rules:**
- **Cannot be overridden** in implementing classes
- **Must have implementation** in interface
- **Called using interface name** only
- **Cannot access instance members**

#### **Practical Use Case:**
```java
interface FileProcessor {
    void processFile(String filename);
    
    // Static utility methods
    static boolean isValidFile(String filename) {
        return filename != null && filename.endsWith(".txt");
    }
    
    static String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot + 1) : "";
    }
}
```

### 52. Can you have private methods in interfaces?

**Yes**, since Java 9, interfaces can have private methods.

#### **Purpose:**
- **Code reuse** between default methods
- **Reduce duplication** in interface implementation
- **Encapsulation** - hide implementation details
- **Helper methods** for default methods

#### **Types of Private Methods:**

**1. Private Instance Methods:**
```java
interface Calculator {
    default int addAndMultiply(int a, int b, int multiplier) {
        int sum = add(a, b);
        return multiply(sum, multiplier);
    }
    
    default int subtractAndMultiply(int a, int b, int multiplier) {
        int diff = subtract(a, b);
        return multiply(diff, multiplier);
    }
    
    // Private method to avoid duplication
    private int multiply(int a, int b) {
        return a * b;
    }
    
    private int add(int a, int b) {
        return a + b;
    }
    
    private int subtract(int a, int b) {
        return a - b;
    }
}
```

**2. Private Static Methods:**
```java
interface Logger {
    default void logInfo(String message) {
        log("INFO", message);
    }
    
    default void logError(String message) {
        log("ERROR", message);
    }
    
    // Private static method
    private static void log(String level, String message) {
        System.out.println("[" + level + "] " + getCurrentTime() + ": " + message);
    }
    
    private static String getCurrentTime() {
        return java.time.LocalTime.now().toString();
    }
}
```

#### **Rules:**
- **Cannot be abstract** - must have implementation
- **Cannot be accessed** by implementing classes
- **Can be called** only from default/static methods in same interface
- **Help organize code** within interface

### 53. What is marker interface?

A **marker interface** is an empty interface with no methods that serves as a tag or marker.

#### **Purpose:**
- **Metadata** - indicates class has certain properties
- **Runtime checks** - JVM or frameworks can check for marker
- **Design pattern** - semantic meaning without methods
- **Type identification** - instanceof checks

#### **Common Marker Interfaces:**

**1. Serializable:**
```java
import java.io.Serializable;

class Student implements Serializable {
    private String name;
    private int age;
    
    // Class can be serialized because it implements Serializable
}
```

**2. Cloneable:**
```java
class Person implements Cloneable {
    private String name;
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();  // Only works if class implements Cloneable
    }
}
```

**3. Remote (RMI):**
```java
import java.rmi.Remote;

interface RemoteService extends Remote {
    void performRemoteOperation() throws java.rmi.RemoteException;
}
```

#### **Custom Marker Interface:**
```java
// Custom marker interface
interface Auditable {
    // Empty interface - just a marker
}

class BankAccount implements Auditable {
    private double balance;
    
    // This class is marked as auditable
}

// Usage in framework code
public void processObjects(Object[] objects) {
    for (Object obj : objects) {
        if (obj instanceof Auditable) {
            // Perform audit logging
            System.out.println("Auditing: " + obj.getClass().getSimpleName());
        }
    }
}
```

#### **Modern Alternative - Annotations:**
```java
// Modern approach using annotations
@Retention(RetentionPolicy.RUNTIME)
@interface Auditable {
}

@Auditable
class BankAccount {
    // Class marked with annotation instead of interface
}
```

### 54. What is functional interface?

A **functional interface** has exactly one abstract method and can be used with lambda expressions.

#### **Characteristics:**
- **Single Abstract Method (SAM)** - exactly one abstract method
- **Lambda compatible** - can use lambda expressions
- **Can have default/static methods** - but only one abstract
- **@FunctionalInterface annotation** - optional but recommended

#### **Built-in Functional Interfaces:**

**1. Runnable:**
```java
@FunctionalInterface
interface Runnable {
    void run();
}

// Lambda usage
Runnable task = () -> System.out.println("Task executed");
new Thread(task).start();
```

**2. Predicate:**
```java
@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}

// Lambda usage
Predicate<String> isEmpty = s -> s.isEmpty();
System.out.println(isEmpty.test(""));  // true
```

**3. Function:**
```java
@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
}

// Lambda usage
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello"));  // 5
```

#### **Custom Functional Interface:**
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);  // Single abstract method
    
    // Default methods allowed
    default void printResult(int a, int b) {
        System.out.println("Result: " + calculate(a, b));
    }
    
    // Static methods allowed
    static void info() {
        System.out.println("Calculator interface");
    }
}

// Lambda usage
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3));      // 8
System.out.println(multiply.calculate(5, 3)); // 15
```

#### **Method References:**
```java
// Method reference instead of lambda
Function<String, Integer> length1 = s -> s.length();     // Lambda
Function<String, Integer> length2 = String::length;      // Method reference

Predicate<String> isEmpty1 = s -> s.isEmpty();           // Lambda
Predicate<String> isEmpty2 = String::isEmpty;            // Method reference
```

### 55. Can an interface extend another interface?

**Yes**, an interface can extend one or more interfaces using the `extends` keyword.

#### **Single Interface Extension:**
```java
interface Animal {
    void eat();
    void sleep();
}

interface Mammal extends Animal {
    void giveBirth();
    void produceMilk();
}

class Dog implements Mammal {
    @Override
    public void eat() { System.out.println("Dog eats"); }
    
    @Override
    public void sleep() { System.out.println("Dog sleeps"); }
    
    @Override
    public void giveBirth() { System.out.println("Dog gives birth"); }
    
    @Override
    public void produceMilk() { System.out.println("Dog produces milk"); }
}
```

#### **Multiple Interface Extension:**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

interface Amphibious extends Flyable, Swimmable {
    void land();  // Additional method
}

class Duck implements Amphibious {
    @Override
    public void fly() { System.out.println("Duck flies"); }
    
    @Override
    public void swim() { System.out.println("Duck swims"); }
    
    @Override
    public void land() { System.out.println("Duck lands"); }
}
```

#### **Interface Hierarchy:**
```java
interface Shape {
    double area();
}

interface TwoDimensional extends Shape {
    double perimeter();
}

interface ThreeDimensional extends Shape {
    double volume();
}

interface Drawable {
    void draw();
}

// Multiple inheritance of interfaces
interface DrawableShape extends Shape, Drawable {
    void highlight();
}

class Circle implements DrawableShape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    @Override
    public void highlight() {
        System.out.println("Highlighting circle");
    }
}
```

#### **Default Method Inheritance:**
```java
interface A {
    default void method() {
        System.out.println("A's method");
    }
}

interface B extends A {
    // Inherits A's default method
    
    default void anotherMethod() {
        method();  // Can call inherited method
        System.out.println("B's method");
    }
}

class C implements B {
    // Inherits both default methods from A and B
}
```

#### **Benefits:**
- **Interface composition** - build complex contracts
- **Code reuse** - inherit methods from parent interfaces
- **Hierarchical design** - organize related interfaces
- **Multiple inheritance** - combine multiple interface contracts

# 🔹 Exception Handling

## Exception Handling - Exception Basics

### 56. What is an exception in Java?

An **exception** is an abnormal event that occurs during program execution and disrupts the normal flow of instructions.

#### **Key Characteristics:**
- **Runtime event** - occurs during program execution
- **Object representation** - exceptions are objects
- **Disrupts flow** - interrupts normal program execution
- **Recoverable** - can be caught and handled
- **Graceful handling** - prevents program crashes

#### **Basic Example:**
```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;  // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero: " + e.getMessage());
        }
    }
}
```

#### **Exception vs Normal Flow:**
```java
// Normal flow
public int divide(int a, int b) {
    return a / b;  // Works fine when b != 0
}

// Exception scenario
public int safeDivide(int a, int b) {
    if (b == 0) {
        throw new ArithmeticException("Division by zero");
    }
    return a / b;
}
```

#### **Benefits of Exception Handling:**
- **Program stability** - prevents crashes
- **Error information** - provides details about what went wrong
- **Recovery mechanism** - allows alternative actions
- **Clean code** - separates error handling from business logic

### 57. What is the difference between error and exception?

| Aspect | Error | Exception |
|--------|-------|-----------|
| **Severity** | Serious, unrecoverable | Recoverable conditions |
| **Handling** | Should not be caught | Should be caught and handled |
| **Cause** | System-level problems | Application-level problems |
| **Recovery** | Not possible | Possible with proper handling |
| **Examples** | OutOfMemoryError, StackOverflowError | IOException, SQLException |

#### **Error Examples:**
```java
// These should NOT be caught
public class ErrorExamples {
    public void causeOutOfMemoryError() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]);  // OutOfMemoryError
        }
    }
    
    public void causeStackOverflowError() {
        causeStackOverflowError();  // Infinite recursion -> StackOverflowError
    }
}
```

#### **Exception Examples:**
```java
// These should be caught and handled
public class ExceptionExamples {
    public void readFile(String filename) {
        try {
            FileReader file = new FileReader(filename);  // May throw FileNotFoundException
            // Process file
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
    
    public void parseNumber(String str) {
        try {
            int number = Integer.parseInt(str);  // May throw NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + str);
        }
    }
}
```

#### **When to Handle vs When to Let Fail:**
```java
// Handle exceptions - recoverable
try {
    processUserInput(input);
} catch (InvalidInputException e) {
    showErrorMessage("Please enter valid input");
}

// Don't catch errors - let them fail fast
// try {
//     someMethod();
// } catch (OutOfMemoryError e) {  // ✗ Don't do this
//     // Cannot recover from this
// }
```

### 58. What is the exception hierarchy in Java?

```
                    Throwable
                   /         \
               Error         Exception
              /     \       /         \
    OutOfMemory  StackOverflow    IOException  RuntimeException
    Error        Error            SQLException      /        \
                                                NullPointer  ArrayIndexOutOf
                                                Exception    BoundsException
```

#### **Throwable Class:**
```java
// Root of all exceptions and errors
public class Throwable {
    private String message;
    private Throwable cause;
    
    public String getMessage() { return message; }
    public Throwable getCause() { return cause; }
    public void printStackTrace() { /* prints stack trace */ }
}
```

#### **Error Branch:**
```java
// System-level problems - don't catch these
class SystemErrors {
    // OutOfMemoryError - JVM runs out of memory
    // StackOverflowError - call stack exceeds limit
    // NoClassDefFoundError - class not found at runtime
    // VirtualMachineError - JVM problems
}
```

#### **Exception Branch:**
```java
// Application-level problems - should be handled

// Checked Exceptions (compile-time checking)
class CheckedExceptions {
    public void readFile() throws IOException {
        // IOException, FileNotFoundException
        // SQLException, ClassNotFoundException
    }
}

// Unchecked Exceptions (runtime checking)
class UncheckedExceptions {
    public void accessArray() {
        // RuntimeException and its subclasses
        // NullPointerException, ArrayIndexOutOfBoundsException
        // IllegalArgumentException, NumberFormatException
    }
}
```

#### **Practical Hierarchy Usage:**
```java
public class ExceptionHandling {
    public void handleSpecificExceptions() {
        try {
            riskyOperation();
        } catch (FileNotFoundException e) {
            // Most specific first
            System.out.println("File not found");
        } catch (IOException e) {
            // More general
            System.out.println("IO problem");
        } catch (Exception e) {
            // Most general last
            System.out.println("General exception");
        }
    }
}
```

### 59. What are checked and unchecked exceptions?

| Aspect | Checked Exceptions | Unchecked Exceptions |
|--------|-------------------|---------------------|
| **Compile-time** | Must be handled | No handling required |
| **Inheritance** | Extend Exception (not RuntimeException) | Extend RuntimeException |
| **When occurs** | Predictable conditions | Programming errors |
| **Examples** | IOException, SQLException | NullPointerException, ArrayIndexOutOfBoundsException |

#### **Checked Exceptions:**
```java
// Must be handled at compile time
public class CheckedExceptionExample {
    // Method declares it throws checked exception
    public void readFile(String filename) throws IOException {
        FileReader file = new FileReader(filename);  // Checked exception
        // Compiler forces handling
    }
    
    // Caller must handle or declare
    public void caller() {
        try {
            readFile("data.txt");  // Must catch
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
    
    // Or declare in method signature
    public void anotherCaller() throws IOException {
        readFile("data.txt");  // Pass exception up
    }
}
```

#### **Unchecked Exceptions:**
```java
// No compile-time handling required
public class UncheckedExceptionExample {
    public void accessArray() {
        int[] arr = {1, 2, 3};
        // This may throw ArrayIndexOutOfBoundsException
        // But compiler doesn't force handling
        System.out.println(arr[5]);  // Runtime exception
    }
    
    public void processString(String str) {
        // May throw NullPointerException
        // No compile-time checking required
        System.out.println(str.length());
    }
    
    // Optional handling
    public void safeProcessing(String str) {
        try {
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("String is null");
        }
    }
}
```

#### **Common Checked Exceptions:**
```java
// File operations
try {
    FileInputStream fis = new FileInputStream("file.txt");
} catch (FileNotFoundException e) { }

// Database operations
try {
    Connection conn = DriverManager.getConnection(url);
} catch (SQLException e) { }

// Network operations
try {
    URL url = new URL("http://example.com");
} catch (MalformedURLException e) { }

// Class loading
try {
    Class.forName("com.example.MyClass");
} catch (ClassNotFoundException e) { }
```

#### **Common Unchecked Exceptions:**
```java
// Null pointer access
String str = null;
str.length();  // NullPointerException

// Array bounds
int[] arr = new int[3];
arr[5] = 10;   // ArrayIndexOutOfBoundsException

// Number parsing
Integer.parseInt("abc");  // NumberFormatException

// Illegal arguments
Thread.sleep(-1);  // IllegalArgumentException
```

### 60. What is the difference between throw and throws?

| Aspect | throw | throws |
|--------|-------|--------|
| **Purpose** | Explicitly throw exception | Declare possible exceptions |
| **Location** | Inside method body | Method signature |
| **Syntax** | `throw new Exception()` | `throws Exception` |
| **Usage** | Execute exception throwing | Declare exception possibility |
| **Object** | Followed by exception object | Followed by exception class |

#### **throw - Explicit Exception Throwing:**
```java
public class ThrowExample {
    public void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age cannot exceed 150");
        }
        System.out.println("Valid age: " + age);
    }
    
    public void withdraw(double amount, double balance) {
        if (amount > balance) {
            throw new RuntimeException("Insufficient funds");
        }
        // Process withdrawal
    }
}
```

#### **throws - Exception Declaration:**
```java
public class ThrowsExample {
    // Declares that method might throw IOException
    public void readFile(String filename) throws IOException {
        FileReader file = new FileReader(filename);  // May throw IOException
        // Method doesn't handle it, passes to caller
    }
    
    // Multiple exception declaration
    public void databaseOperation() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");  // ClassNotFoundException
        Connection conn = DriverManager.getConnection(url);  // SQLException
    }
    
    // Caller must handle declared exceptions
    public void caller() {
        try {
            readFile("data.txt");
            databaseOperation();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

#### **Combined Usage:**
```java
public class CombinedExample {
    // Method declares exception and also throws it
    public void processFile(String filename) throws IOException {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }
        
        FileReader file = new FileReader(filename);  // May throw IOException
        // IOException is declared, IllegalArgumentException is unchecked
    }
}
```

### 61. What is try-catch-finally block?

The **try-catch-finally** construct provides structured exception handling in Java.

#### **Basic Structure:**
```java
try {
    // Risky code that might throw exceptions
} catch (SpecificException e) {
    // Handle specific exception
} catch (GeneralException e) {
    // Handle more general exception
} finally {
    // Cleanup code - always executes
}
```

#### **Complete Example:**
```java
public class TryCatchFinallyExample {
    public void processFile(String filename) {
        FileReader file = null;
        BufferedReader reader = null;
        
        try {
            file = new FileReader(filename);
            reader = new BufferedReader(file);
            String line = reader.readLine();
            System.out.println("First line: " + line);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            // Cleanup resources
            try {
                if (reader != null) reader.close();
                if (file != null) file.close();
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
```

#### **Execution Flow:**
```java
public class ExecutionFlow {
    public void demonstrateFlow() {
        System.out.println("1. Before try");
        
        try {
            System.out.println("2. In try block");
            int result = 10 / 0;  // Exception here
            System.out.println("3. This won't execute");
        } catch (ArithmeticException e) {
            System.out.println("4. In catch block");
        } finally {
            System.out.println("5. In finally block");
        }
        
        System.out.println("6. After try-catch-finally");
    }
    
    // Output:
    // 1. Before try
    // 2. In try block
    // 4. In catch block
    // 5. In finally block
    // 6. After try-catch-finally
}
```

#### **Finally Block Guarantees:**
```java
public class FinallyGuarantees {
    public String testFinally() {
        try {
            return "try";
        } catch (Exception e) {
            return "catch";
        } finally {
            System.out.println("Finally always executes");
            // Even when return statements exist
        }
    }
    
    public void testFinallyWithException() {
        try {
            throw new RuntimeException("Exception");
        } finally {
            System.out.println("Finally executes even with unhandled exception");
        }
    }
}
```

### 62. Can you have multiple catch blocks?

**Yes**, you can have multiple catch blocks to handle different types of exceptions.

#### **Multiple Catch Blocks:**
```java
public class MultipleCatchExample {
    public void processData(String data) {
        try {
            // Multiple operations that can throw different exceptions
            int number = Integer.parseInt(data);  // NumberFormatException
            int[] array = new int[number];        // NegativeArraySizeException
            array[0] = 100 / number;              // ArithmeticException
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + data);
        } catch (NegativeArraySizeException e) {
            System.out.println("Array size cannot be negative");
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
```

#### **Order Matters - Specific to General:**
```java
public class CatchOrder {
    public void correctOrder() {
        try {
            riskyOperation();
        } catch (FileNotFoundException e) {      // Most specific
            System.out.println("File not found");
        } catch (IOException e) {               // More general
            System.out.println("IO error");
        } catch (Exception e) {                 // Most general
            System.out.println("General error");
        }
    }
    
    // This won't compile - unreachable catch
    public void incorrectOrder() {
        try {
            riskyOperation();
        } catch (Exception e) {                 // Too general first
            System.out.println("General error");
        // } catch (IOException e) {            // ✗ Unreachable code
        //     System.out.println("IO error");
        // }
    }
}
```

#### **Multi-catch (Java 7+):**
```java
public class MultiCatch {
    public void handleMultipleExceptions() {
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {
            // Handle both exceptions the same way
            System.out.println("Database or IO error: " + e.getMessage());
            // Note: 'e' is effectively final
        } catch (Exception e) {
            System.out.println("Other error: " + e.getMessage());
        }
    }
}
```

### 63. What is try-with-resources?

**Try-with-resources** automatically manages resources that implement the `AutoCloseable` interface.

#### **Syntax:**
```java
try (ResourceType resource = new ResourceType()) {
    // Use resource
} catch (Exception e) {
    // Handle exceptions
}
// Resource automatically closed here
```

#### **Basic Example:**
```java
public class TryWithResourcesExample {
    // Old way - manual resource management
    public void readFileOldWay(String filename) {
        FileReader file = null;
        BufferedReader reader = null;
        
        try {
            file = new FileReader(filename);
            reader = new BufferedReader(file);
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (file != null) file.close();
            } catch (IOException e) {
                System.out.println("Error closing resources");
            }
        }
    }
    
    // New way - automatic resource management
    public void readFileNewWay(String filename) {
        try (FileReader file = new FileReader(filename);
             BufferedReader reader = new BufferedReader(file)) {
            
            System.out.println(reader.readLine());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Resources automatically closed
    }
}
```

#### **Multiple Resources:**
```java
public class MultipleResources {
    public void copyFile(String source, String destination) {
        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(destination);
             BufferedInputStream bufferedInput = new BufferedInputStream(input);
             BufferedOutputStream bufferedOutput = new BufferedOutputStream(output)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                bufferedOutput.write(buffer, 0, bytesRead);
            }
            
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        // All resources automatically closed in reverse order
    }
}
```

#### **Custom AutoCloseable Resource:**
```java
public class CustomResource implements AutoCloseable {
    private String name;
    
    public CustomResource(String name) {
        this.name = name;
        System.out.println("Opening resource: " + name);
    }
    
    public void doSomething() {
        System.out.println("Using resource: " + name);
    }
    
    @Override
    public void close() {
        System.out.println("Closing resource: " + name);
    }
}

// Usage
public void useCustomResource() {
    try (CustomResource resource = new CustomResource("MyResource")) {
        resource.doSomething();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    // Output:
    // Opening resource: MyResource
    // Using resource: MyResource
    // Closing resource: MyResource
}
```

### 64. What happens if an exception occurs in finally block?

When an exception occurs in the finally block, it **suppresses** any exception from the try or catch block.

#### **Exception Suppression:**
```java
public class FinallyException {
    public void demonstrateSuppression() {
        try {
            throw new RuntimeException("Exception from try block");
        } finally {
            throw new RuntimeException("Exception from finally block");
        }
        // Only "Exception from finally block" is thrown
        // "Exception from try block" is suppressed
    }
    
    public void handleSuppressedExceptions() {
        try {
            demonstrateSuppression();
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            
            // Access suppressed exceptions
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable t : suppressed) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }
}
```

#### **Try-with-resources and Suppressed Exceptions:**
```java
public class SuppressedExceptionExample {
    static class ProblematicResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new Exception("Error closing resource");
        }
        
        public void doWork() throws Exception {
            throw new Exception("Error during work");
        }
    }
    
    public void demonstrateSuppressionInTryWithResources() {
        try (ProblematicResource resource = new ProblematicResource()) {
            resource.doWork();  // Throws exception
        } catch (Exception e) {
            System.out.println("Primary: " + e.getMessage());  // "Error during work"
            
            // The close() exception is suppressed
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());  // "Error closing resource"
            }
        }
    }
}
```

#### **Best Practices:**
```java
public class FinallyBestPractices {
    // ✗ Bad - finally exception masks original
    public void badPractice() {
        try {
            throw new RuntimeException("Important exception");
        } finally {
            throw new RuntimeException("Masks important exception");
        }
    }
    
    // ✓ Good - handle finally exceptions carefully
    public void goodPractice() {
        Exception originalException = null;
        
        try {
            throw new RuntimeException("Important exception");
        } catch (Exception e) {
            originalException = e;
            throw e;
        } finally {
            try {
                // Cleanup code that might throw
                riskyCleanup();
            } catch (Exception e) {
                if (originalException != null) {
                    e.addSuppressed(originalException);
                }
                // Log cleanup exception but don't throw
                System.err.println("Cleanup failed: " + e.getMessage());
            }
        }
    }
    
    private void riskyCleanup() throws Exception {
        // Cleanup code
    }
}
```

### 65. Can you throw an exception from finally block?

**Yes**, you can throw exceptions from finally block, but it's generally **not recommended**.

#### **Throwing from Finally:**
```java
public class ThrowFromFinally {
    public void throwFromFinally() {
        try {
            System.out.println("In try block");
        } finally {
            throw new RuntimeException("Exception from finally");
        }
        // This method will throw "Exception from finally"
    }
    
    public void suppressOriginalException() {
        try {
            throw new RuntimeException("Original exception");
        } finally {
            throw new RuntimeException("Finally exception");
        }
        // "Original exception" is suppressed
        // "Finally exception" is thrown
    }
}
```

#### **Problems with Throwing from Finally:**
```java
public class ProblemsWithFinallyThrow {
    public void maskingImportantException() {
        try {
            // Important business logic exception
            throw new IllegalStateException("Critical business rule violation");
        } finally {
            // Cleanup code throws exception
            throw new RuntimeException("Cleanup failed");
        }
        // The critical business exception is lost!
    }
    
    public void debuggingNightmare() {
        try {
            processImportantData();
        } catch (DataProcessingException e) {
            // Log and handle the data processing error
            logError(e);
            throw e;  // Re-throw for caller
        } finally {
            // This masks the real problem
            throw new RuntimeException("Logging system failed");
        }
        // Developer will see logging failure, not data processing failure
    }
}
```

#### **Better Alternatives:**
```java
public class BetterAlternatives {
    // ✓ Good - Log exceptions in finally, don't throw
    public void logDontThrow() {
        try {
            riskyOperation();
        } finally {
            try {
                cleanup();
            } catch (Exception e) {
                // Log the cleanup failure but don't throw
                logger.error("Cleanup failed", e);
            }
        }
    }
    
    // ✓ Good - Use try-with-resources for automatic cleanup
    public void useAutoCloseable() {
        try (MyResource resource = new MyResource()) {
            resource.doWork();
        } catch (Exception e) {
            // Handle business logic exceptions
            // Resource cleanup exceptions are suppressed, not lost
        }
    }
    
    // ✓ Good - Explicit exception handling in finally
    public void explicitHandling() {
        Exception primaryException = null;
        
        try {
            riskyOperation();
        } catch (Exception e) {
            primaryException = e;
        } finally {
            try {
                cleanup();
            } catch (Exception cleanupException) {
                if (primaryException != null) {
                    // Add cleanup exception as suppressed
                    primaryException.addSuppressed(cleanupException);
                } else {
                    // No primary exception, cleanup exception is the problem
                    throw cleanupException;
                }
            }
        }
        
        if (primaryException != null) {
            throw primaryException;
        }
    }
}
```

#### **When It Might Be Acceptable:**
```java
public class AcceptableFinallyThrow {
    // Validation in finally - might be acceptable
    public void validateState() {
        try {
            performOperation();
        } finally {
            // Validate system state after operation
            if (!isSystemStateValid()) {
                throw new IllegalStateException("System in invalid state");
            }
        }
    }
    
    // Resource cleanup with critical failure
    public void criticalCleanup() {
        try {
            performCriticalOperation();
        } finally {
            try {
                criticalCleanup();
            } catch (CriticalCleanupException e) {
                // If cleanup fails, system might be corrupted
                // Throwing might be appropriate to prevent further damage
                throw new SystemCorruptedException("Critical cleanup failed", e);
            }
        }
    }
}
```

## Custom Exceptions

### 66. How do you create custom exceptions?

Custom exceptions are created by **extending existing exception classes** to provide domain-specific error handling.

#### **Basic Custom Exception Structure:**
```java
// Checked custom exception
public class CustomCheckedException extends Exception {
    public CustomCheckedException() {
        super();
    }
    
    public CustomCheckedException(String message) {
        super(message);
    }
    
    public CustomCheckedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CustomCheckedException(Throwable cause) {
        super(cause);
    }
}

// Unchecked custom exception
public class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException() {
        super();
    }
    
    public CustomUncheckedException(String message) {
        super(message);
    }
    
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CustomUncheckedException(Throwable cause) {
        super(cause);
    }
}
```

#### **Domain-Specific Custom Exceptions:**
```java
// Banking domain exceptions
public class InsufficientFundsException extends Exception {
    private final double requestedAmount;
    private final double availableBalance;
    
    public InsufficientFundsException(double requestedAmount, double availableBalance) {
        super(String.format("Insufficient funds: requested %.2f, available %.2f", 
              requestedAmount, availableBalance));
        this.requestedAmount = requestedAmount;
        this.availableBalance = availableBalance;
    }
    
    public double getRequestedAmount() { return requestedAmount; }
    public double getAvailableBalance() { return availableBalance; }
}

// User management exceptions
public class UserNotFoundException extends RuntimeException {
    private final String userId;
    
    public UserNotFoundException(String userId) {
        super("User not found: " + userId);
        this.userId = userId;
    }
    
    public String getUserId() { return userId; }
}

// Validation exceptions
public class ValidationException extends Exception {
    private final List<String> errors;
    
    public ValidationException(List<String> errors) {
        super("Validation failed: " + String.join(", ", errors));
        this.errors = new ArrayList<>(errors);
    }
    
    public List<String> getErrors() { return new ArrayList<>(errors); }
}
```

#### **Usage Examples:**
```java
public class BankAccount {
    private double balance;
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }
}

public class UserService {
    public User findUser(String userId) {
        User user = database.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
    }
}
```

### 67. When should you create custom exceptions?

Create custom exceptions when **standard exceptions don't adequately describe your specific error conditions**.

#### **Good Reasons to Create Custom Exceptions:**

**1. Domain-Specific Error Conditions:**
```java
// E-commerce domain
public class ProductOutOfStockException extends Exception {
    private final String productId;
    private final int requestedQuantity;
    private final int availableQuantity;
    
    public ProductOutOfStockException(String productId, int requested, int available) {
        super(String.format("Product %s: requested %d, available %d", 
              productId, requested, available));
        this.productId = productId;
        this.requestedQuantity = requested;
        this.availableQuantity = available;
    }
    
    // Getters for recovery logic
    public String getProductId() { return productId; }
    public int getRequestedQuantity() { return requestedQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
}
```

**2. API Consistency:**
```java
// Payment processing API
public abstract class PaymentException extends Exception {
    protected PaymentException(String message) { super(message); }
    protected PaymentException(String message, Throwable cause) { super(message, cause); }
}

public class PaymentDeclinedException extends PaymentException {
    private final String declineReason;
    
    public PaymentDeclinedException(String reason) {
        super("Payment declined: " + reason);
        this.declineReason = reason;
    }
    
    public String getDeclineReason() { return declineReason; }
}

public class PaymentTimeoutException extends PaymentException {
    public PaymentTimeoutException() {
        super("Payment processing timed out");
    }
}
```

**3. Additional Context and Recovery Information:**
```java
public class DatabaseConnectionException extends Exception {
    private final String databaseUrl;
    private final int retryCount;
    private final long lastAttemptTime;
    
    public DatabaseConnectionException(String url, int retryCount) {
        super(String.format("Failed to connect to database %s after %d attempts", url, retryCount));
        this.databaseUrl = url;
        this.retryCount = retryCount;
        this.lastAttemptTime = System.currentTimeMillis();
    }
    
    // Recovery information
    public boolean shouldRetry() { return retryCount < 3; }
    public long getNextRetryTime() { return lastAttemptTime + (retryCount * 1000); }
}
```

#### **When NOT to Create Custom Exceptions:**

**❌ Don't create custom exceptions for:**
```java
// Bad - standard exceptions work fine
public class MyNullPointerException extends RuntimeException { }  // Use NullPointerException
public class MyIllegalArgumentException extends RuntimeException { }  // Use IllegalArgumentException
public class MyIOException extends IOException { }  // Use IOException or its subclasses

// Bad - too generic
public class ApplicationException extends Exception { }  // Too broad, not helpful

// Bad - just wrapping without adding value
public class ServiceException extends Exception {
    public ServiceException(Exception e) { super(e); }  // No additional value
}
```

**✅ Good custom exception criteria:**
- **Specific error condition** that needs special handling
- **Additional context** beyond standard exceptions
- **Recovery information** for callers
- **Domain-specific** error that makes code more readable
- **API consistency** across your application layers

### 68. What is exception chaining?

**Exception chaining** links exceptions together to preserve the original cause while throwing a new exception.

#### **Why Use Exception Chaining:**
- **Preserve root cause** - don't lose original error information
- **Add context** - provide higher-level meaning to low-level errors
- **Maintain stack trace** - complete error trail for debugging
- **Layer abstraction** - convert implementation exceptions to domain exceptions

#### **Basic Exception Chaining:**
```java
public class ServiceLayer {
    public void processData(String data) throws ServiceException {
        try {
            // Low-level database operation
            database.save(data);
        } catch (SQLException e) {
            // Chain the SQLException as cause of ServiceException
            throw new ServiceException("Failed to process data", e);
        }
    }
}

public class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

#### **Accessing Chained Exceptions:**
```java
public class ExceptionChainExample {
    public void demonstrateChaining() {
        try {
            serviceLayer.processData("test data");
        } catch (ServiceException e) {
            System.out.println("Service error: " + e.getMessage());
            
            // Access the root cause
            Throwable cause = e.getCause();
            if (cause instanceof SQLException) {
                SQLException sqlEx = (SQLException) cause;
                System.out.println("Database error code: " + sqlEx.getErrorCode());
            }
            
            // Print complete stack trace (shows chain)
            e.printStackTrace();
        }
    }
}
```

#### **Multi-Level Chaining:**
```java
public class MultiLevelChaining {
    // Layer 1: Database layer
    public void databaseOperation() throws SQLException {
        throw new SQLException("Connection timeout", "08001");
    }
    
    // Layer 2: DAO layer
    public void dataAccess() throws DataAccessException {
        try {
            databaseOperation();
        } catch (SQLException e) {
            throw new DataAccessException("Data access failed", e);
        }
    }
    
    // Layer 3: Service layer
    public void businessLogic() throws BusinessException {
        try {
            dataAccess();
        } catch (DataAccessException e) {
            throw new BusinessException("Business operation failed", e);
        }
    }
    
    // Usage
    public void handleChainedExceptions() {
        try {
            businessLogic();
        } catch (BusinessException e) {
            // Walk the exception chain
            Throwable current = e;
            while (current != null) {
                System.out.println("Exception: " + current.getClass().getSimpleName() + 
                                 " - " + current.getMessage());
                current = current.getCause();
            }
        }
    }
}
```

#### **Using initCause() Method:**
```java
public class InitCauseExample {
    public void alternativeChaining() throws CustomException {
        try {
            riskyOperation();
        } catch (IOException e) {
            CustomException customEx = new CustomException("Operation failed");
            customEx.initCause(e);  // Alternative to constructor chaining
            throw customEx;
        }
    }
}
```

### 69. What is suppressed exception?

**Suppressed exceptions** occur when multiple exceptions happen and one exception suppresses others to prevent them from being lost.

#### **When Suppressed Exceptions Occur:**

**1. Try-with-resources:**
```java
public class SuppressedExceptionExample {
    static class ProblematicResource implements AutoCloseable {
        private final String name;
        
        public ProblematicResource(String name) {
            this.name = name;
        }
        
        public void doWork() throws Exception {
            throw new RuntimeException("Error during work in " + name);
        }
        
        @Override
        public void close() throws Exception {
            throw new RuntimeException("Error closing " + name);
        }
    }
    
    public void demonstrateSuppression() {
        try (ProblematicResource resource = new ProblematicResource("MyResource")) {
            resource.doWork();  // Throws exception
        } catch (Exception e) {
            System.out.println("Primary exception: " + e.getMessage());
            // Output: "Error during work in MyResource"
            
            // Access suppressed exceptions
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable s : suppressed) {
                System.out.println("Suppressed: " + s.getMessage());
                // Output: "Error closing MyResource"
            }
        }
    }
}
```

**2. Multiple Resources:**
```java
public class MultipleSuppressedExceptions {
    public void multipleResources() {
        try (ProblematicResource r1 = new ProblematicResource("Resource1");
             ProblematicResource r2 = new ProblematicResource("Resource2")) {
            
            throw new RuntimeException("Main operation failed");
            
        } catch (Exception e) {
            System.out.println("Primary: " + e.getMessage());
            // Output: "Main operation failed"
            
            // Both close() exceptions are suppressed
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());
                // Output: "Error closing Resource2"
                // Output: "Error closing Resource1"
            }
        }
    }
}
```

#### **Manual Suppression:**
```java
public class ManualSuppression {
    public void manualSuppressionExample() throws Exception {
        Exception primaryException = null;
        
        try {
            throw new RuntimeException("Primary exception");
        } catch (Exception e) {
            primaryException = e;
        }
        
        try {
            // Cleanup code that might fail
            cleanup();
        } catch (Exception cleanupException) {
            if (primaryException != null) {
                // Manually add suppressed exception
                primaryException.addSuppressed(cleanupException);
                throw primaryException;
            } else {
                throw cleanupException;
            }
        }
        
        if (primaryException != null) {
            throw primaryException;
        }
    }
    
    private void cleanup() throws Exception {
        throw new RuntimeException("Cleanup failed");
    }
}
```

#### **Accessing Suppressed Exceptions:**
```java
public class AccessingSuppressed {
    public void analyzeSuppressedExceptions(Exception e) {
        // Check if there are suppressed exceptions
        Throwable[] suppressed = e.getSuppressed();
        
        if (suppressed.length > 0) {
            System.out.println("Found " + suppressed.length + " suppressed exceptions:");
            
            for (int i = 0; i < suppressed.length; i++) {
                System.out.println((i + 1) + ". " + suppressed[i].getClass().getSimpleName() + 
                                 ": " + suppressed[i].getMessage());
                
                // Suppressed exceptions can also have their own suppressed exceptions
                Throwable[] nestedSuppressed = suppressed[i].getSuppressed();
                if (nestedSuppressed.length > 0) {
                    System.out.println("   Nested suppressed exceptions: " + nestedSuppressed.length);
                }
            }
        }
    }
}
```

### 70. How do you handle multiple exceptions in a single catch?

Use **multi-catch syntax** (Java 7+) to handle multiple exception types in one catch block.

#### **Multi-catch Syntax:**
```java
public class MultiCatchExample {
    public void processData(String input) {
        try {
            // Operations that can throw different exceptions
            int number = Integer.parseInt(input);        // NumberFormatException
            int[] array = new int[number];               // NegativeArraySizeException
            array[0] = 100 / number;                     // ArithmeticException
            
        } catch (NumberFormatException | NegativeArraySizeException | ArithmeticException e) {
            // Handle all three exceptions the same way
            System.out.println("Input processing error: " + e.getMessage());
            logError(e);
        }
    }
}
```

#### **Rules for Multi-catch:**

**1. No Inheritance Relationship:**
```java
public class MultiCatchRules {
    public void correctMultiCatch() {
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {  // ✓ Valid - no inheritance relationship
            handleException(e);
        }
    }
    
    public void incorrectMultiCatch() {
        try {
            riskyOperation();
        // } catch (Exception | RuntimeException e) {  // ✗ Invalid - RuntimeException extends Exception
        //     handleException(e);
        // }
    }
}
```

**2. Exception Variable is Final:**
```java
public class FinalExceptionVariable {
    public void demonstrateFinalVariable() {
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {
            // e is implicitly final
            System.out.println("Exception type: " + e.getClass().getSimpleName());
            
            // e = new IOException();  // ✗ Compilation error - cannot reassign
            
            // But you can call methods on e
            e.printStackTrace();
        }
    }
}
```

#### **Combining Multi-catch with Regular Catch:**
```java
public class CombinedCatchBlocks {
    public void combinedHandling() {
        try {
            complexOperation();
        } catch (FileNotFoundException e) {
            // Specific handling for file not found
            System.out.println("File not found: " + e.getMessage());
            createDefaultFile();
        } catch (IOException | SQLException e) {
            // Common handling for IO and SQL exceptions
            System.out.println("Data access error: " + e.getMessage());
            notifyAdministrator(e);
        } catch (Exception e) {
            // Catch-all for any other exceptions
            System.out.println("Unexpected error: " + e.getMessage());
            logUnexpectedError(e);
        }
    }
}
```

#### **Practical Examples:**

**Network Operations:**
```java
public class NetworkOperations {
    public void downloadFile(String url) {
        try {
            URL fileUrl = new URL(url);
            URLConnection connection = fileUrl.openConnection();
            // Download logic
        } catch (MalformedURLException | UnknownHostException | SocketTimeoutException e) {
            // All network-related exceptions handled similarly
            System.out.println("Network error: " + e.getMessage());
            scheduleRetry();
        } catch (IOException e) {
            // Other IO exceptions
            System.out.println("IO error: " + e.getMessage());
        }
    }
}
```

**Data Processing:**
```java
public class DataProcessor {
    public void processUserInput(String input) {
        try {
            validateAndProcess(input);
        } catch (NumberFormatException | IllegalArgumentException | DateTimeParseException e) {
            // All input validation errors
            showUserFriendlyError("Invalid input format: " + e.getMessage());
        } catch (SecurityException | AccessDeniedException e) {
            // Security-related exceptions
            logSecurityViolation(e);
            showAccessDeniedMessage();
        }
    }
}
```

#### **Benefits of Multi-catch:**
- **Reduced code duplication** - same handling logic for multiple exceptions
- **Cleaner code** - fewer catch blocks
- **Easier maintenance** - single place to update common exception handling
- **Better readability** - groups related exceptions together

#### **When to Use Multi-catch:**
```java
// ✓ Good use cases
catch (IOException | SQLException e) {
    // Both are data access related, handle similarly
    logDataAccessError(e);
    notifyUser("Data temporarily unavailable");
}

catch (NumberFormatException | IllegalArgumentException e) {
    // Both are input validation errors
    showInputValidationError(e.getMessage());
}

// ✗ Avoid when exceptions need different handling
catch (FileNotFoundException | OutOfMemoryError e) {
    // These need very different handling approaches
    // Better to handle separately
}
```

# 🔹 Collections Framework

## Collection Basics

### 71. What is Java Collections Framework?

The **Java Collections Framework** is a unified architecture for representing and manipulating collections of objects.

#### **Key Components:**
- **Interfaces** - Abstract data types (List, Set, Map)
- **Implementations** - Concrete classes (ArrayList, HashMap)
- **Algorithms** - Static methods for operations (sort, search)
- **Utilities** - Helper classes and methods

#### **Framework Structure:**
```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList (Class)
│   ├── LinkedList (Class)
│   └── Vector (Class)
├── Set (Interface)
│   ├── HashSet (Class)
│   ├── TreeSet (Class)
│   └── LinkedHashSet (Class)
└── Queue (Interface)
    ├── PriorityQueue (Class)
    └── ArrayDeque (Class)

Map (Interface) - separate hierarchy
├── HashMap (Class)
├── TreeMap (Class)
├── LinkedHashMap (Class)
└── Hashtable (Class)
```

#### **Basic Usage:**
```java
// List - ordered, allows duplicates
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Alice");  // Duplicate allowed

// Set - no duplicates
Set<String> uniqueNames = new HashSet<>();
uniqueNames.add("Alice");
uniqueNames.add("Bob");
uniqueNames.add("Alice");  // Duplicate ignored

// Map - key-value pairs
Map<String, Integer> ages = new HashMap<>();
ages.put("Alice", 25);
ages.put("Bob", 30);
```

#### **Benefits:**
- **Consistent API** - same methods across implementations
- **Reduced programming effort** - no need to write custom data structures
- **Increased performance** - optimized implementations
- **Interoperability** - collections work together seamlessly

### 72. What is the difference between Collection and Collections?

| Aspect | Collection | Collections |
|--------|------------|-------------|
| **Type** | Interface | Utility Class |
| **Purpose** | Defines contract for collections | Provides utility methods |
| **Usage** | Implemented by collection classes | Static methods for operations |
| **Package** | java.util.Collection | java.util.Collections |

#### **Collection Interface:**
```java
// Collection is the root interface
public interface Collection<E> extends Iterable<E> {
    boolean add(E e);
    boolean remove(Object o);
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Iterator<E> iterator();
    // ... other methods
}

// Usage - implement or use implementations
List<String> list = new ArrayList<>();  // ArrayList implements Collection
Set<String> set = new HashSet<>();      // HashSet implements Collection
```

#### **Collections Utility Class:**
```java
// Collections provides static utility methods
public class Collections {
    // Sorting
    public static <T extends Comparable<? super T>> void sort(List<T> list);
    
    // Searching
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key);
    
    // Synchronization
    public static <T> Collection<T> synchronizedCollection(Collection<T> c);
    
    // Immutable collections
    public static <T> List<T> unmodifiableList(List<? extends T> list);
}

// Usage examples
List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5);
Collections.sort(numbers);                    // [1, 1, 3, 4, 5]
Collections.reverse(numbers);                 // [5, 4, 3, 1, 1]
int index = Collections.binarySearch(numbers, 3);  // Find index of 3

// Create synchronized version
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Create unmodifiable version
List<String> readOnlyList = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
```

### 73. What are the main interfaces in Collections Framework?

#### **Core Interfaces Hierarchy:**

**1. Collection Interface (Root):**
```java
Collection<E>
├── List<E>     // Ordered, allows duplicates
├── Set<E>      // No duplicates
└── Queue<E>    // FIFO operations
    └── Deque<E> // Double-ended queue
```

**2. Map Interface (Separate):**
```java
Map<K,V>        // Key-value pairs
```

#### **Interface Characteristics:**

| Interface | Duplicates | Ordered | Indexed | Key-Value |
|-----------|------------|---------|---------|-----------|
| **List** | Yes | Yes | Yes | No |
| **Set** | No | Depends | No | No |
| **Queue** | Yes | Yes | No | No |
| **Deque** | Yes | Yes | No | No |
| **Map** | Values: Yes, Keys: No | Depends | No | Yes |

#### **Interface Examples:**

**List Interface:**
```java
List<String> list = new ArrayList<>();
list.add("first");
list.add("second");
list.add("first");     // Duplicate allowed
list.get(0);           // Access by index
list.set(1, "new");    // Modify by index
```

**Set Interface:**
```java
Set<String> set = new HashSet<>();
set.add("unique");
set.add("values");
set.add("unique");     // Duplicate ignored
// No index-based access
```

**Queue Interface:**
```java
Queue<String> queue = new LinkedList<>();
queue.offer("first");   // Add to rear
queue.offer("second");
String head = queue.poll();  // Remove from front
```

**Map Interface:**
```java
Map<String, Integer> map = new HashMap<>();
map.put("key1", 100);
map.put("key2", 200);
Integer value = map.get("key1");  // Access by key
```

### 74. What is the difference between List, Set, and Map?

| Feature | List | Set | Map |
|---------|------|-----|-----|
| **Duplicates** | Allows duplicates | No duplicates | Keys: No, Values: Yes |
| **Ordering** | Maintains insertion order | Depends on implementation | Depends on implementation |
| **Indexing** | Index-based access | No indexing | Key-based access |
| **Null Values** | Multiple nulls allowed | At most one null | Depends on implementation |

#### **List - Ordered Collection with Duplicates:**
```java
List<String> fruits = new ArrayList<>();
fruits.add("apple");
fruits.add("banana");
fruits.add("apple");      // Duplicate allowed
fruits.add(null);         // Null allowed
fruits.add(null);         // Multiple nulls allowed

// Index-based operations
fruits.get(0);            // "apple"
fruits.set(1, "orange");  // Replace at index 1
fruits.remove(2);         // Remove at index 2

// Common implementations: ArrayList, LinkedList, Vector
```

#### **Set - Unique Elements:**
```java
Set<String> uniqueFruits = new HashSet<>();
uniqueFruits.add("apple");
uniqueFruits.add("banana");
uniqueFruits.add("apple");    // Ignored - duplicate
uniqueFruits.add(null);       // One null allowed (in HashSet)

// No index-based access
// uniqueFruits.get(0);      // ✗ Not available

// Check membership
boolean hasApple = uniqueFruits.contains("apple");

// Common implementations: HashSet, TreeSet, LinkedHashSet
```

#### **Map - Key-Value Pairs:**
```java
Map<String, Integer> inventory = new HashMap<>();
inventory.put("apples", 50);
inventory.put("bananas", 30);
inventory.put("apples", 60);     // Overwrites previous value

// Key-based access
Integer appleCount = inventory.get("apples");  // 60
inventory.remove("bananas");

// Keys are unique, values can duplicate
inventory.put("oranges", 50);    // Same value as apples, but different key

// Common implementations: HashMap, TreeMap, LinkedHashMap
```

### 75. What is the difference between ArrayList and LinkedList?

| Aspect | ArrayList | LinkedList |
|--------|-----------|------------|
| **Internal Structure** | Dynamic array | Doubly-linked list |
| **Random Access** | O(1) - fast | O(n) - slow |
| **Insertion/Deletion** | O(n) in middle | O(1) at known position |
| **Memory Overhead** | Lower | Higher (node objects) |
| **Cache Performance** | Better | Worse |

#### **ArrayList - Dynamic Array:**
```java
List<String> arrayList = new ArrayList<>();

// Fast random access
arrayList.add("A");
arrayList.add("B");
arrayList.add("C");
String element = arrayList.get(1);  // O(1) - direct array access

// Slow insertion in middle
arrayList.add(1, "X");  // O(n) - shift elements right

// Memory efficient - contiguous storage
// Good cache locality
```

#### **LinkedList - Doubly-Linked List:**
```java
List<String> linkedList = new LinkedList<>();

// Slow random access
linkedList.add("A");
linkedList.add("B");
linkedList.add("C");
String element = linkedList.get(1);  // O(n) - traverse from head/tail

// Fast insertion anywhere if you have reference
LinkedList<String> ll = (LinkedList<String>) linkedList;
ll.addFirst("X");   // O(1)
ll.addLast("Y");    // O(1)

// Higher memory overhead - node objects with pointers
// Poor cache locality
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    public void demonstratePerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // Adding elements - both O(1) at end
        for (int i = 0; i < 1000; i++) {
            arrayList.add(i);      // Fast
            linkedList.add(i);     // Fast
        }
        
        // Random access - ArrayList wins
        int sum1 = 0;
        for (int i = 0; i < 1000; i++) {
            sum1 += arrayList.get(i);   // O(1) each - total O(n)
        }
        
        int sum2 = 0;
        for (int i = 0; i < 1000; i++) {
            sum2 += linkedList.get(i);  // O(n) each - total O(n²)
        }
        
        // Insertion in middle - LinkedList can be better
        arrayList.add(500, -1);     // O(n) - shift 500 elements
        linkedList.add(500, -1);    // O(n) - traverse to position, then O(1) insert
    }
}
```

### 76. When would you use ArrayList vs LinkedList?

#### **Use ArrayList When:**

**1. Frequent Random Access:**
```java
// Reading data by index frequently
List<String> records = new ArrayList<>();
// ... populate records
for (int i = 0; i < records.size(); i++) {
    processRecord(records.get(i));  // O(1) access
}
```

**2. Memory is a Concern:**
```java
// ArrayList uses less memory per element
List<Integer> numbers = new ArrayList<>(1000000);  // More memory efficient
```

**3. Iteration Performance:**
```java
// ArrayList has better cache locality
for (String item : arrayList) {  // Faster iteration
    process(item);
}
```

**4. Mostly Appending Data:**
```java
// Adding to end is O(1) amortized
List<LogEntry> logs = new ArrayList<>();
logs.add(new LogEntry());  // Fast append
```

#### **Use LinkedList When:**

**1. Frequent Insertions/Deletions in Middle:**
```java
// Implementing a queue or deque
Queue<Task> taskQueue = new LinkedList<>();
taskQueue.offer(task);     // O(1) at end
Task next = taskQueue.poll();  // O(1) at beginning

// Frequent insertions at specific positions
LinkedList<String> editableText = new LinkedList<>();
editableText.add(position, newText);  // Better than ArrayList for middle insertions
```

**2. Unknown Size with Frequent Modifications:**
```java
// When you don't know final size and modify frequently
List<String> dynamicList = new LinkedList<>();
// No need to worry about array resizing overhead
```

**3. Implementing Stack/Queue Operations:**
```java
// LinkedList implements Deque interface
Deque<String> stack = new LinkedList<>();
stack.push("item");     // O(1)
String top = stack.pop();   // O(1)

Queue<String> queue = new LinkedList<>();
queue.offer("item");    // O(1)
String first = queue.poll();  // O(1)
```

#### **Decision Matrix:**
```java
public class CollectionChoice {
    // ✓ ArrayList - frequent random access
    public void processDataByIndex(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            if (shouldProcess(i)) {
                process(data.get(i));  // O(1) with ArrayList
            }
        }
    }
    
    // ✓ LinkedList - frequent insertions at beginning
    public void maintainRecentItems(LinkedList<String> recent, String newItem) {
        recent.addFirst(newItem);  // O(1)
        if (recent.size() > 100) {
            recent.removeLast();   // O(1)
        }
    }
    
    // ✓ ArrayList - memory-sensitive application
    public List<Integer> createLargeDataset() {
        return new ArrayList<>(1000000);  // Less memory per element
    }
}
```

### 77. What is the difference between HashSet and TreeSet?

| Aspect | HashSet | TreeSet |
|--------|---------|---------|
| **Internal Structure** | Hash table | Red-black tree (balanced BST) |
| **Time Complexity** | O(1) average | O(log n) |
| **Ordering** | No ordering | Sorted order |
| **Null Values** | One null allowed | No null allowed |
| **Comparison** | equals() and hashCode() | Comparable or Comparator |

#### **HashSet - Hash Table Based:**
```java
Set<String> hashSet = new HashSet<>();
hashSet.add("banana");
hashSet.add("apple");
hashSet.add("cherry");
hashSet.add(null);        // Null allowed

// No guaranteed order
System.out.println(hashSet);  // Could be: [null, cherry, apple, banana]

// Fast operations - O(1) average
boolean hasApple = hashSet.contains("apple");  // O(1)
hashSet.remove("banana");                      // O(1)
```

#### **TreeSet - Balanced Tree Based:**
```java
Set<String> treeSet = new TreeSet<>();
treeSet.add("banana");
treeSet.add("apple");
treeSet.add("cherry");
// treeSet.add(null);     // ✗ NullPointerException

// Maintains sorted order
System.out.println(treeSet);  // [apple, banana, cherry]

// Slower operations - O(log n)
boolean hasApple = treeSet.contains("apple");  // O(log n)
treeSet.remove("banana");                      // O(log n)
```

#### **TreeSet Additional Operations:**
```java
TreeSet<Integer> numbers = new TreeSet<>();
numbers.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));

// Sorted: [1, 2, 3, 5, 8, 9]
Integer first = numbers.first();        // 1
Integer last = numbers.last();          // 9
Integer lower = numbers.lower(5);       // 3 (largest < 5)
Integer higher = numbers.higher(5);     // 8 (smallest > 5)
Integer floor = numbers.floor(4);       // 3 (largest <= 4)
Integer ceiling = numbers.ceiling(4);   // 5 (smallest >= 4)

// Range operations
Set<Integer> subset = numbers.subSet(3, 8);  // [3, 5] (3 inclusive, 8 exclusive)
```

#### **Custom Sorting with TreeSet:**
```java
// Custom comparator for reverse order
TreeSet<String> reverseSet = new TreeSet<>(Collections.reverseOrder());
reverseSet.addAll(Arrays.asList("apple", "banana", "cherry"));
System.out.println(reverseSet);  // [cherry, banana, apple]

// Custom object with Comparable
class Person implements Comparable<Person> {
    String name;
    int age;
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);  // Sort by age
    }
}

TreeSet<Person> people = new TreeSet<>();
// Automatically sorted by age
```

### 78. What is the difference between HashMap and TreeMap?

| Aspect | HashMap | TreeMap |
|--------|---------|---------|
| **Internal Structure** | Hash table | Red-black tree |
| **Time Complexity** | O(1) average | O(log n) |
| **Ordering** | No ordering | Sorted by keys |
| **Null Keys** | One null key allowed | No null keys |
| **Null Values** | Multiple null values | Multiple null values |

#### **HashMap - Hash Table Based:**
```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("banana", 2);
hashMap.put("apple", 5);
hashMap.put("cherry", 3);
hashMap.put(null, 0);     // Null key allowed

// No guaranteed order
System.out.println(hashMap);  // Could be: {null=0, cherry=3, apple=5, banana=2}

// Fast operations - O(1) average
Integer apples = hashMap.get("apple");  // O(1)
hashMap.remove("banana");               // O(1)
```

#### **TreeMap - Balanced Tree Based:**
```java
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("banana", 2);
treeMap.put("apple", 5);
treeMap.put("cherry", 3);
// treeMap.put(null, 0);  // ✗ NullPointerException

// Maintains sorted order by keys
System.out.println(treeMap);  // {apple=5, banana=2, cherry=3}

// Slower operations - O(log n)
Integer apples = treeMap.get("apple");  // O(log n)
treeMap.remove("banana");               // O(log n)
```

#### **TreeMap Additional Operations:**
```java
TreeMap<String, Integer> inventory = new TreeMap<>();
inventory.put("apples", 50);
inventory.put("bananas", 30);
inventory.put("cherries", 20);
inventory.put("dates", 10);

// Navigation methods
String firstKey = inventory.firstKey();        // "apples"
String lastKey = inventory.lastKey();          // "dates"
String lowerKey = inventory.lowerKey("cherries");  // "bananas"
String higherKey = inventory.higherKey("bananas"); // "cherries"

// Range operations
Map<String, Integer> subMap = inventory.subMap("bananas", "dates");
// {bananas=30, cherries=20} (inclusive start, exclusive end)

// Reverse navigation
NavigableMap<String, Integer> descendingMap = inventory.descendingMap();
System.out.println(descendingMap);  // {dates=10, cherries=20, bananas=30, apples=50}
```

### 79. What is the difference between HashMap and Hashtable?

| Aspect | HashMap | Hashtable |
|--------|---------|-----------|
| **Synchronization** | Not synchronized | Synchronized |
| **Thread Safety** | Not thread-safe | Thread-safe |
| **Null Values** | Allows null key/values | No null key/values |
| **Performance** | Faster | Slower (due to synchronization) |
| **Inheritance** | Extends AbstractMap | Extends Dictionary (legacy) |
| **Introduction** | Java 1.2 | Java 1.0 |

#### **HashMap - Modern, Fast, Not Thread-Safe:**
```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("key1", 100);
hashMap.put("key2", 200);
hashMap.put(null, 300);      // Null key allowed
hashMap.put("key3", null);   // Null value allowed

// Fast but not thread-safe
// Multiple threads accessing simultaneously can cause issues
```

#### **Hashtable - Legacy, Synchronized, Thread-Safe:**
```java
Map<String, Integer> hashtable = new Hashtable<>();
hashtable.put("key1", 100);
hashtable.put("key2", 200);
// hashtable.put(null, 300);    // ✗ NullPointerException
// hashtable.put("key3", null); // ✗ NullPointerException

// Thread-safe but slower
// All methods are synchronized
```

#### **Thread Safety Demonstration:**
```java
public class ThreadSafetyDemo {
    public void demonstrateHashMap() {
        Map<String, Integer> map = new HashMap<>();
        
        // Unsafe in multi-threaded environment
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);  // Can cause data corruption
            }
        };
        
        new Thread(task).start();
        new Thread(task).start();
        // Possible issues: infinite loops, data corruption
    }
    
    public void demonstrateHashtable() {
        Map<String, Integer> map = new Hashtable<>();
        
        // Safe in multi-threaded environment
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);  // Thread-safe operations
            }
        };
        
        new Thread(task).start();
        new Thread(task).start();
        // No data corruption, but slower performance
    }
}
```

#### **Modern Alternatives:**
```java
// Instead of Hashtable, use:

// 1. ConcurrentHashMap - better performance
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// 2. Synchronized wrapper
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

// 3. External synchronization
Map<String, Integer> map = new HashMap<>();
synchronized(map) {
    map.put("key", value);
}
```

### 80. What is the difference between HashMap and ConcurrentHashMap?

| Aspect | HashMap | ConcurrentHashMap |
|--------|---------|-------------------|
| **Thread Safety** | Not thread-safe | Thread-safe |
| **Synchronization** | No synchronization | Segment-based locking |
| **Performance** | Fast (single-thread) | Good (multi-thread) |
| **Null Values** | Allows null key/values | No null key/values |
| **Fail-Fast** | Yes | No (fail-safe) |
| **Concurrent Reads** | Not safe | Safe |

#### **HashMap - Single-Threaded Performance:**
```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("key1", 100);
hashMap.put(null, 200);      // Null allowed
hashMap.put("key2", null);   // Null value allowed

// Fast operations but not thread-safe
// Can cause infinite loops or data corruption in multi-threaded environment
```

#### **ConcurrentHashMap - Multi-Threaded Safety:**
```java
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("key1", 100);
// concurrentMap.put(null, 200);    // ✗ NullPointerException
// concurrentMap.put("key2", null); // ✗ NullPointerException

// Thread-safe with better performance than Hashtable
// Uses segment-based locking for concurrent access
```

#### **Concurrency Features:**
```java
public class ConcurrencyDemo {
    private final Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
    
    public void demonstrateConcurrentOperations() {
        // Multiple threads can safely access
        Runnable reader = () -> {
            for (int i = 0; i < 1000; i++) {
                Integer value = concurrentMap.get("key" + (i % 100));
                // Safe concurrent reads
            }
        };
        
        Runnable writer = () -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("key" + i, i);
                // Safe concurrent writes (with some locking)
            }
        };
        
        // Multiple readers and writers can work simultaneously
        new Thread(reader).start();
        new Thread(reader).start();
        new Thread(writer).start();
    }
}
```

#### **Atomic Operations in ConcurrentHashMap:**
```java
ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();

// Atomic increment
counter.compute("visits", (key, val) -> (val == null) ? 1 : val + 1);

// Atomic put-if-absent
Integer previous = counter.putIfAbsent("users", 0);

// Atomic replace
counter.replace("sessions", 10, 11);  // Replace only if current value is 10

// Atomic compute operations
counter.computeIfAbsent("newKey", key -> calculateInitialValue(key));
counter.computeIfPresent("existingKey", (key, val) -> val * 2);
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    public void comparePerformance() {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> hashtable = new Hashtable<>();
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        
        // Single-threaded: HashMap > ConcurrentHashMap > SynchronizedMap > Hashtable
        // Multi-threaded: ConcurrentHashMap > SynchronizedMap ≈ Hashtable >> HashMap (unsafe)
    }
}
```

#### **When to Use Each:**
```java
// ✓ HashMap - single-threaded applications
Map<String, String> config = new HashMap<>();

// ✓ ConcurrentHashMap - multi-threaded applications
Map<String, UserSession> sessions = new ConcurrentHashMap<>();

// ✗ Hashtable - legacy, avoid in new code
// Map<String, Object> legacy = new Hashtable<>();

// ✓ Synchronized wrapper - when you need null values in multi-threaded environment
Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
```

## Advanced Collections

### 81. How does HashMap work internally?

HashMap uses a **hash table** with **separate chaining** for collision resolution.

#### **Internal Structure:**
```java
// Simplified HashMap structure
class HashMap<K,V> {
    Node<K,V>[] table;        // Array of buckets
    int size;                 // Number of entries
    int threshold;            // Resize threshold
    float loadFactor;         // Load factor (default 0.75)
    
    static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;       // For chaining
    }
}
```

#### **How put() Works:**
```java
public V put(K key, V value) {
    // 1. Calculate hash
    int hash = hash(key);
    
    // 2. Find bucket index
    int index = hash & (table.length - 1);
    
    // 3. Handle collision or insert
    Node<K,V> node = table[index];
    if (node == null) {
        // Empty bucket - direct insertion
        table[index] = new Node<>(hash, key, value, null);
    } else {
        // Collision - traverse chain
        while (node != null) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                // Key exists - update value
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            if (node.next == null) break;
            node = node.next;
        }
        // Add new node to end of chain
        node.next = new Node<>(hash, key, value, null);
    }
    
    // 4. Check if resize needed
    if (++size > threshold) {
        resize();
    }
    return null;
}
```

#### **Hash Function:**
```java
static final int hash(Object key) {
    int h;
    // XOR higher bits with lower bits for better distribution
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

#### **Tree Conversion (Java 8+):**
```java
// When chain length > TREEIFY_THRESHOLD (8), convert to balanced tree
// When tree size < UNTREEIFY_THRESHOLD (6), convert back to list
// Improves worst-case performance from O(n) to O(log n)
```

### 82. What is hash collision and how is it handled?

**Hash collision** occurs when different keys produce the same hash code and map to the same bucket.

#### **Types of Collisions:**

**1. Hash Code Collision:**
```java
// Different objects, same hash code
String s1 = "Aa";
String s2 = "BB";
System.out.println(s1.hashCode());  // 2112
System.out.println(s2.hashCode());  // 2112 - same hash code!
```

**2. Bucket Index Collision:**
```java
// Different hash codes, same bucket index
int hash1 = 100;
int hash2 = 116;
int tableSize = 16;
int index1 = hash1 & (tableSize - 1);  // 4
int index2 = hash2 & (tableSize - 1);  // 4 - same bucket!
```

#### **Collision Resolution - Separate Chaining:**
```java
// HashMap uses separate chaining
// Each bucket contains a linked list (or tree) of entries

Bucket 0: null
Bucket 1: [key1,val1] -> [key5,val5] -> null
Bucket 2: [key2,val2] -> null
Bucket 3: null
Bucket 4: [key3,val3] -> [key7,val7] -> [key11,val11] -> null
```

#### **Tree Conversion for Performance:**
```java
public class CollisionHandling {
    // When chain length exceeds TREEIFY_THRESHOLD (8)
    // Convert linked list to balanced red-black tree
    
    public void demonstrateTreeConversion() {
        Map<BadHashKey, String> map = new HashMap<>();
        
        // Add many keys that hash to same bucket
        for (int i = 0; i < 10; i++) {
            map.put(new BadHashKey(i), "value" + i);
        }
        // After 8 entries in same bucket, converts to tree
        // Search time improves from O(n) to O(log n)
    }
    
    static class BadHashKey {
        int value;
        BadHashKey(int value) { this.value = value; }
        
        @Override
        public int hashCode() { return 1; }  // All keys hash to same bucket
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof BadHashKey && ((BadHashKey) obj).value == this.value;
        }
    }
}
```

### 83. What is the load factor in HashMap?

**Load factor** is the ratio of number of entries to the number of buckets.

#### **Load Factor Formula:**
```java
Load Factor = Number of Entries / Number of Buckets
Default Load Factor = 0.75
Threshold = Capacity × Load Factor
```

#### **Load Factor Impact:**
```java
public class LoadFactorDemo {
    public void demonstrateLoadFactor() {
        // Default: capacity=16, loadFactor=0.75, threshold=12
        Map<String, Integer> map = new HashMap<>();
        
        // Add 12 entries - no resize
        for (int i = 0; i < 12; i++) {
            map.put("key" + i, i);
        }
        
        // Add 13th entry - triggers resize
        map.put("key12", 12);  // Capacity doubles to 32, threshold becomes 24
    }
    
    // Custom load factor
    public void customLoadFactor() {
        // Lower load factor - more memory, fewer collisions
        Map<String, Integer> lowLoad = new HashMap<>(16, 0.5f);
        
        // Higher load factor - less memory, more collisions
        Map<String, Integer> highLoad = new HashMap<>(16, 0.9f);
    }
}
```

#### **Load Factor Trade-offs:**

| Load Factor | Memory Usage | Collision Probability | Performance |
|-------------|--------------|----------------------|-------------|
| **Low (0.5)** | High | Low | Fast access |
| **Medium (0.75)** | Balanced | Balanced | Good balance |
| **High (0.9)** | Low | High | Slower access |

#### **Choosing Load Factor:**
```java
// ✓ Use default 0.75 for most cases
Map<String, Integer> defaultMap = new HashMap<>();

// ✓ Use lower load factor when performance is critical
Map<String, Integer> fastMap = new HashMap<>(1000, 0.5f);

// ✓ Use higher load factor when memory is limited
Map<String, Integer> compactMap = new HashMap<>(100, 0.9f);
```

### 84. What happens when HashMap reaches its capacity?

When HashMap reaches its **threshold** (capacity × load factor), it triggers **rehashing**.

#### **Resize Process:**
```java
public class HashMapResize {
    public void demonstrateResize() {
        Map<String, Integer> map = new HashMap<>(4, 0.75f);
        // Initial: capacity=4, threshold=3
        
        map.put("A", 1);  // size=1, no resize
        map.put("B", 2);  // size=2, no resize
        map.put("C", 3);  // size=3, no resize
        map.put("D", 4);  // size=4 > threshold=3, RESIZE!
        
        // After resize: capacity=8, threshold=6
    }
}
```

#### **Rehashing Steps:**

**1. Create New Array:**
```java
// Double the capacity
Node<K,V>[] oldTable = table;
int oldCapacity = oldTable.length;
int newCapacity = oldCapacity << 1;  // Double the size
Node<K,V>[] newTable = new Node[newCapacity];
```

**2. Rehash All Entries:**
```java
// Recalculate bucket index for each entry
for (int i = 0; i < oldCapacity; i++) {
    Node<K,V> node = oldTable[i];
    while (node != null) {
        Node<K,V> next = node.next;
        
        // Recalculate index in new table
        int newIndex = node.hash & (newCapacity - 1);
        
        // Move to new table
        node.next = newTable[newIndex];
        newTable[newIndex] = node;
        
        node = next;
    }
}
```

#### **Performance Impact:**
```java
public class ResizePerformance {
    public void measureResizeImpact() {
        Map<Integer, String> map = new HashMap<>(2);  // Small initial capacity
        
        long start = System.nanoTime();
        
        // This will trigger multiple resizes
        for (int i = 0; i < 1000; i++) {
            map.put(i, "value" + i);
            // Resizes at: 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024
        }
        
        long end = System.nanoTime();
        System.out.println("Time with resizes: " + (end - start) + " ns");
        
        // Better: pre-size the map
        Map<Integer, String> presized = new HashMap<>(1024);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            presized.put(i, "value" + i);  // No resizes needed
        }
        end = System.nanoTime();
        System.out.println("Time without resizes: " + (end - start) + " ns");
    }
}
```

### 85. What is the difference between fail-fast and fail-safe iterators?

| Aspect | Fail-Fast | Fail-Safe |
|--------|-----------|-----------|
| **Behavior** | Throws ConcurrentModificationException | No exception thrown |
| **Detection** | Detects concurrent modification | Allows concurrent modification |
| **Memory** | Works on original collection | Works on copy of collection |
| **Performance** | Faster | Slower (due to copying) |
| **Examples** | ArrayList, HashMap | ConcurrentHashMap, CopyOnWriteArrayList |

#### **Fail-Fast Iterator:**
```java
public class FailFastExample {
    public void demonstrateFailFast() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        
        try {
            for (String item : list) {
                System.out.println(item);
                if ("B".equals(item)) {
                    list.remove(item);  // Modifies collection during iteration
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Fail-fast: " + e.getMessage());
        }
    }
    
    public void safeModification() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        
        // ✓ Safe way - use iterator's remove method
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("B".equals(item)) {
                iterator.remove();  // Safe removal
            }
        }
    }
}
```

#### **Fail-Safe Iterator:**
```java
public class FailSafeExample {
    public void demonstrateFailSafe() {
        // ConcurrentHashMap uses fail-safe iterator
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        // Safe to modify during iteration
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
            if ("B".equals(entry.getKey())) {
                map.put("D", 4);  // Safe modification
            }
        }
        // No exception thrown
    }
    
    public void copyOnWriteExample() {
        // CopyOnWriteArrayList creates copy for each modification
        List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
        
        for (String item : list) {
            System.out.println(item);
            if ("B".equals(item)) {
                list.add("D");  // Safe - works on copy
            }
        }
        // Iterator sees original snapshot, modifications don't affect it
    }
}
```

### 86. What is WeakHashMap?

**WeakHashMap** uses **weak references** for keys, allowing garbage collection when no strong references exist.

#### **Weak Reference Behavior:**
```java
public class WeakHashMapExample {
    public void demonstrateWeakReferences() {
        Map<String, String> weakMap = new WeakHashMap<>();
        Map<String, String> normalMap = new HashMap<>();
        
        // Create keys
        String key1 = new String("key1");  // Strong reference
        String key2 = new String("key2");
        
        // Add to both maps
        weakMap.put(key1, "value1");
        weakMap.put(key2, "value2");
        normalMap.put(key1, "value1");
        normalMap.put(key2, "value2");
        
        System.out.println("Before GC - WeakMap size: " + weakMap.size());     // 2
        System.out.println("Before GC - NormalMap size: " + normalMap.size()); // 2
        
        // Remove strong references
        key1 = null;
        key2 = null;
        
        // Force garbage collection
        System.gc();
        Thread.sleep(1000);  // Give GC time to run
        
        System.out.println("After GC - WeakMap size: " + weakMap.size());      // 0 (keys collected)
        System.out.println("After GC - NormalMap size: " + normalMap.size());  // 2 (keys retained)
    }
}
```

#### **Use Cases for WeakHashMap:**

**1. Cache Implementation:**
```java
public class CacheExample {
    private final Map<Object, ExpensiveObject> cache = new WeakHashMap<>();
    
    public ExpensiveObject getExpensiveObject(Object key) {
        ExpensiveObject cached = cache.get(key);
        if (cached == null) {
            cached = createExpensiveObject(key);
            cache.put(key, cached);
        }
        return cached;
    }
    
    // When 'key' is no longer referenced elsewhere,
    // it will be garbage collected along with its cache entry
}
```

**2. Observer Pattern:**
```java
public class ObserverRegistry {
    private final Map<Observer, String> observers = new WeakHashMap<>();
    
    public void addObserver(Observer observer) {
        observers.put(observer, "registered");
    }
    
    public void notifyObservers() {
        for (Observer observer : observers.keySet()) {
            observer.update();
        }
    }
    
    // Observers are automatically removed when they're no longer referenced
}
```

### 87. What is IdentityHashMap?

**IdentityHashMap** uses **reference equality** (==) instead of **object equality** (equals()) for key comparison.

#### **Reference vs Object Equality:**
```java
public class IdentityHashMapExample {
    public void demonstrateDifference() {
        Map<String, Integer> normalMap = new HashMap<>();
        Map<String, Integer> identityMap = new IdentityHashMap<>();
        
        String key1 = new String("hello");
        String key2 = new String("hello");  // Same content, different object
        String key3 = "hello";              // String literal (interned)
        
        // HashMap uses equals() - treats key1 and key2 as same
        normalMap.put(key1, 1);
        normalMap.put(key2, 2);  // Overwrites previous value
        System.out.println("HashMap size: " + normalMap.size());  // 1
        
        // IdentityHashMap uses == - treats key1 and key2 as different
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);  // Different objects, both stored
        identityMap.put(key3, 3);  // key3 might be same reference as key1 (interning)
        System.out.println("IdentityHashMap size: " + identityMap.size());  // 2 or 3
    }
}
```

#### **Use Cases:**

**1. Object Tracking:**
```java
public class ObjectTracker {
    private final Map<Object, String> objectStates = new IdentityHashMap<>();
    
    public void trackObject(Object obj, String state) {
        objectStates.put(obj, state);  // Track specific object instance
    }
    
    public String getObjectState(Object obj) {
        return objectStates.get(obj);  // Only matches exact same object
    }
}
```

**2. Serialization/Deserialization:**
```java
public class SerializationHelper {
    private final Map<Object, Integer> objectIds = new IdentityHashMap<>();
    private int nextId = 1;
    
    public int getObjectId(Object obj) {
        return objectIds.computeIfAbsent(obj, k -> nextId++);
    }
    
    // Ensures each object instance gets unique ID, even if equals() returns true
}
```

### 88. What is LinkedHashMap?

**LinkedHashMap** extends HashMap and maintains **insertion order** or **access order** using a doubly-linked list.

#### **Insertion Order (Default):**
```java
public class LinkedHashMapExample {
    public void demonstrateInsertionOrder() {
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        Map<String, Integer> hashMap = new HashMap<>();
        
        // Add in specific order
        linkedMap.put("third", 3);
        linkedMap.put("first", 1);
        linkedMap.put("second", 2);
        
        hashMap.put("third", 3);
        hashMap.put("first", 1);
        hashMap.put("second", 2);
        
        System.out.println("LinkedHashMap: " + linkedMap);  // {third=3, first=1, second=2}
        System.out.println("HashMap: " + hashMap);          // Random order
    }
}
```

#### **Access Order Mode:**
```java
public class AccessOrderExample {
    public void demonstrateAccessOrder() {
        // accessOrder = true enables LRU behavior
        Map<String, Integer> lruMap = new LinkedHashMap<>(16, 0.75f, true);
        
        lruMap.put("A", 1);
        lruMap.put("B", 2);
        lruMap.put("C", 3);
        
        System.out.println("Initial: " + lruMap);  // {A=1, B=2, C=3}
        
        // Access A - moves to end
        lruMap.get("A");
        System.out.println("After accessing A: " + lruMap);  // {B=2, C=3, A=1}
        
        // Access B - moves to end
        lruMap.get("B");
        System.out.println("After accessing B: " + lruMap);  // {C=3, A=1, B=2}
    }
}
```

#### **LRU Cache Implementation:**
```java
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;
    
    public LRUCache(int maxSize) {
        super(16, 0.75f, true);  // Enable access order
        this.maxSize = maxSize;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;  // Remove oldest when size exceeds limit
    }
}

// Usage
LRUCache<String, String> cache = new LRUCache<>(3);
cache.put("1", "one");
cache.put("2", "two");
cache.put("3", "three");
cache.put("4", "four");    // "1" is automatically removed
System.out.println(cache); // {2=two, 3=three, 4=four}
```

### 89. What is PriorityQueue?

**PriorityQueue** is a **heap-based** priority queue that orders elements by priority, not insertion order.

#### **Natural Ordering:**
```java
public class PriorityQueueExample {
    public void demonstrateNaturalOrdering() {
        Queue<Integer> pq = new PriorityQueue<>();
        
        // Add elements in random order
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        pq.offer(5);
        
        // Elements come out in priority order (min-heap by default)
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());  // 5, 10, 20, 30
        }
    }
}
```

#### **Custom Comparator:**
```java
public class CustomPriorityExample {
    public void demonstrateCustomPriority() {
        // Max-heap using reverse order
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(Arrays.asList(30, 10, 20, 5));
        
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());  // 30, 20, 10, 5
        }
        
        // Custom object priority
        Queue<Task> taskQueue = new PriorityQueue<>(
            Comparator.comparing(Task::getPriority).reversed()
        );
        
        taskQueue.offer(new Task("Low priority", 1));
        taskQueue.offer(new Task("High priority", 10));
        taskQueue.offer(new Task("Medium priority", 5));
        
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());  // High, Medium, Low
        }
    }
    
    static class Task {
        String name;
        int priority;
        
        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        int getPriority() { return priority; }
        
        @Override
        public String toString() { return name + " (" + priority + ")"; }
    }
}
```

#### **Heap Properties:**
```java
public class HeapProperties {
    public void demonstrateHeapBehavior() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(4, 2, 8, 1, 7, 3));
        
        // peek() returns minimum without removing
        System.out.println("Min element: " + pq.peek());  // 1
        
        // poll() removes and returns minimum
        System.out.println("Removed: " + pq.poll());      // 1
        System.out.println("New min: " + pq.peek());      // 2
        
        // Note: iteration order is NOT sorted
        System.out.println("Iteration order: " + pq);     // Not necessarily sorted
    }
}
```

### 90. What is the difference between Comparable and Comparator?

| Aspect | Comparable | Comparator |
|--------|------------|------------|
| **Location** | Inside the class | External class/lambda |
| **Method** | compareTo(T other) | compare(T o1, T o2) |
| **Sorting** | Natural ordering | Custom ordering |
| **Flexibility** | Single sorting logic | Multiple sorting strategies |
| **Modification** | Requires class modification | No class modification needed |

#### **Comparable - Natural Ordering:**
```java
public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double gpa;
    
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    @Override
    public int compareTo(Student other) {
        // Natural ordering by GPA (descending)
        return Double.compare(other.gpa, this.gpa);
    }
    
    // Usage
    public static void demonstrateComparable() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8),
            new Student("Bob", 22, 3.5),
            new Student("Charlie", 21, 3.9)
        );
        
        Collections.sort(students);  // Uses compareTo()
        // Sorted by GPA: Charlie (3.9), Alice (3.8), Bob (3.5)
    }
}
```

#### **Comparator - Custom Ordering:**
```java
public class ComparatorExample {
    public void demonstrateComparator() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8),
            new Student("Bob", 22, 3.5),
            new Student("Charlie", 21, 3.9)
        );
        
        // Sort by name
        Collections.sort(students, Comparator.comparing(Student::getName));
        
        // Sort by age (descending)
        Collections.sort(students, Comparator.comparing(Student::getAge).reversed());
        
        // Multiple criteria - age first, then GPA
        Collections.sort(students, 
            Comparator.comparing(Student::getAge)
                     .thenComparing(Student::getGpa));
        
        // Lambda comparator
        Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));
    }
}
```

#### **Advanced Comparator Usage:**
```java
public class AdvancedComparator {
    public void demonstrateAdvancedUsage() {
        List<Student> students = getStudents();
        
        // Null-safe comparison
        Comparator<Student> nameComparator = 
            Comparator.comparing(Student::getName, Comparator.nullsLast(String::compareTo));
        
        // Custom comparison logic
        Comparator<Student> gradeComparator = (s1, s2) -> {
            if (s1.getGpa() >= 3.5 && s2.getGpa() >= 3.5) return 0;  // Both honor students
            return Double.compare(s2.getGpa(), s1.getGpa());
        };
        
        // Chaining comparators
        Comparator<Student> complexComparator = 
            Comparator.comparing(Student::getGpa).reversed()           // GPA descending
                     .thenComparing(Student::getAge)                   // Then age ascending
                     .thenComparing(Student::getName);                 // Then name ascending
        
        students.sort(complexComparator);
    }
    
    // TreeSet with custom comparator
    public void treeSetWithComparator() {
        // Sort students by name length, then alphabetically
        Set<Student> studentSet = new TreeSet<>(
            Comparator.comparing((Student s) -> s.getName().length())
                     .thenComparing(Student::getName)
        );
        
        studentSet.addAll(getStudents());
    }
}
```

#### **When to Use Each:**
```java
// ✓ Use Comparable when:
// - There's a single, natural way to order objects
// - You control the class definition
// - The ordering is fundamental to the class

public class Price implements Comparable<Price> {
    private BigDecimal amount;
    
    @Override
    public int compareTo(Price other) {
        return this.amount.compareTo(other.amount);  // Natural ordering by amount
    }
}

// ✓ Use Comparator when:
// - You need multiple sorting strategies
// - You don't control the class definition
// - The ordering is context-dependent

public class ProductSorter {
    public static final Comparator<Product> BY_PRICE = 
        Comparator.comparing(Product::getPrice);
    
    public static final Comparator<Product> BY_NAME = 
        Comparator.comparing(Product::getName);
    
    public static final Comparator<Product> BY_POPULARITY = 
        Comparator.comparing(Product::getPopularityScore).reversed();
}
```

## Collection Performance

### 91. What is the time complexity of ArrayList operations?

ArrayList is backed by a **dynamic array**, which determines its performance characteristics.

#### **Time Complexity Summary:**

| Operation | Best Case | Average Case | Worst Case | Notes |
|-----------|-----------|--------------|------------|-------|
| **get(index)** | O(1) | O(1) | O(1) | Direct array access |
| **set(index, element)** | O(1) | O(1) | O(1) | Direct array access |
| **add(element)** | O(1) | O(1) | O(n) | Amortized O(1), O(n) when resize |
| **add(index, element)** | O(1) | O(n) | O(n) | O(1) at end, O(n) in middle |
| **remove(index)** | O(1) | O(n) | O(n) | O(1) at end, O(n) in middle |
| **contains(element)** | O(1) | O(n) | O(n) | Linear search |
| **indexOf(element)** | O(1) | O(n) | O(n) | Linear search |

#### **Detailed Analysis:**

**Fast Operations - O(1):**
```java
List<String> list = new ArrayList<>();
list.add("A");  // O(1) - add at end
list.add("B");
list.add("C");

// Random access - O(1)
String element = list.get(1);     // O(1) - direct array access
list.set(2, "D");                 // O(1) - direct array assignment

// Size operations - O(1)
int size = list.size();           // O(1)
boolean empty = list.isEmpty();   // O(1)
```

**Slow Operations - O(n):**
```java
// Insertion in middle - O(n)
list.add(1, "X");  // Shifts elements: [A, X, B, D]
// Internally: System.arraycopy() shifts elements right

// Removal from middle - O(n)
list.remove(1);    // Shifts elements: [A, B, D]
// Internally: System.arraycopy() shifts elements left

// Search operations - O(n)
boolean found = list.contains("B");  // Linear search through array
int index = list.indexOf("D");       // Linear search until found
```

#### **Resize Operation - Amortized O(1):**
```java
public class ArrayListResize {
    public void demonstrateResize() {
        List<Integer> list = new ArrayList<>(2);  // Initial capacity = 2
        
        list.add(1);  // O(1) - space available
        list.add(2);  // O(1) - space available
        list.add(3);  // O(n) - triggers resize, copies all elements
        
        // Resize process:
        // 1. Create new array with 1.5x capacity (capacity = 3)
        // 2. Copy all existing elements: O(n)
        // 3. Add new element: O(1)
        
        // Subsequent adds are O(1) until next resize
        list.add(4);  // O(1)
    }
}
```

#### **Performance Optimization:**
```java
// ✓ Good - pre-size when you know approximate size
List<String> optimized = new ArrayList<>(1000);  // Avoids multiple resizes

// ✗ Bad - frequent insertions in middle
for (int i = 0; i < 1000; i++) {
    list.add(0, "item" + i);  // O(n) each time - total O(n²)
}

// ✓ Better - add at end, then reverse if needed
for (int i = 0; i < 1000; i++) {
    list.add("item" + i);     // O(1) each time - total O(n)
}
Collections.reverse(list);    // O(n) - much better than O(n²)
```

### 92. What is the time complexity of HashMap operations?

HashMap uses a **hash table** with **separate chaining**, providing excellent average-case performance.

#### **Time Complexity Summary:**

| Operation | Average Case | Worst Case (Java 7) | Worst Case (Java 8+) | Notes |
|-----------|--------------|---------------------|----------------------|-------|
| **get(key)** | O(1) | O(n) | O(log n) | Tree conversion in Java 8+ |
| **put(key, value)** | O(1) | O(n) | O(log n) | Tree conversion in Java 8+ |
| **remove(key)** | O(1) | O(n) | O(log n) | Tree conversion in Java 8+ |
| **containsKey(key)** | O(1) | O(n) | O(log n) | Same as get() |
| **size()** | O(1) | O(1) | O(1) | Cached value |

#### **Average Case - O(1):**
```java
Map<String, Integer> map = new HashMap<>();

// All O(1) average case
map.put("apple", 5);           // O(1) - good hash distribution
map.put("banana", 3);          // O(1)
map.put("cherry", 8);          // O(1)

Integer apples = map.get("apple");      // O(1) - direct bucket access
boolean hasKey = map.containsKey("banana"); // O(1)
Integer removed = map.remove("cherry");     // O(1)
```

#### **Worst Case Scenario:**
```java
public class HashMapWorstCase {
    // Bad hash function - all keys map to same bucket
    static class BadHashKey {
        String value;
        
        BadHashKey(String value) { this.value = value; }
        
        @Override
        public int hashCode() { return 1; }  // All keys hash to bucket 1
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof BadHashKey && 
                   Objects.equals(this.value, ((BadHashKey) obj).value);
        }
    }
    
    public void demonstrateWorstCase() {
        Map<BadHashKey, String> map = new HashMap<>();
        
        // All keys go to same bucket - creates long chain
        for (int i = 0; i < 1000; i++) {
            map.put(new BadHashKey("key" + i), "value" + i);
        }
        
        // Java 7: O(n) - linear search through chain
        // Java 8+: O(log n) - chain converts to balanced tree after 8 elements
        String value = map.get(new BadHashKey("key500"));
    }
}
```

#### **Java 8+ Tree Conversion:**
```java
// When bucket chain length > TREEIFY_THRESHOLD (8):
// Linked list converts to balanced red-black tree
// Improves worst-case from O(n) to O(log n)

// Tree conversion conditions:
// 1. Chain length > 8
// 2. Total map size > 64 (MIN_TREEIFY_CAPACITY)
// 3. Keys implement Comparable or use System.identityHashCode()
```

#### **Load Factor Impact:**
```java
public class LoadFactorImpact {
    public void demonstrateLoadFactor() {
        // Default load factor = 0.75
        Map<Integer, String> defaultMap = new HashMap<>();
        
        // Lower load factor - fewer collisions, more memory
        Map<Integer, String> lowLoadMap = new HashMap<>(16, 0.5f);
        
        // Higher load factor - more collisions, less memory
        Map<Integer, String> highLoadMap = new HashMap<>(16, 0.9f);
        
        // Performance comparison:
        // Low load factor: Faster access, more memory usage
        // High load factor: Slower access, less memory usage
    }
}
```

### 93. What is the time complexity of TreeMap operations?

TreeMap uses a **Red-Black Tree** (self-balancing binary search tree), providing guaranteed logarithmic performance.

#### **Time Complexity Summary:**

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| **get(key)** | O(log n) | Tree traversal |
| **put(key, value)** | O(log n) | Insert + rebalance |
| **remove(key)** | O(log n) | Delete + rebalance |
| **containsKey(key)** | O(log n) | Same as get() |
| **firstKey() / lastKey()** | O(log n) | Navigate to min/max |
| **higherKey() / lowerKey()** | O(log n) | Tree navigation |
| **subMap()** | O(log n) | Create view |

#### **Basic Operations:**
```java
TreeMap<String, Integer> treeMap = new TreeMap<>();

// All operations are O(log n)
treeMap.put("banana", 2);     // O(log n) - insert and rebalance
treeMap.put("apple", 5);      // O(log n)
treeMap.put("cherry", 3);     // O(log n)

Integer apples = treeMap.get("apple");        // O(log n) - tree traversal
boolean hasKey = treeMap.containsKey("banana"); // O(log n)
Integer removed = treeMap.remove("cherry");     // O(log n) - delete and rebalance
```

#### **Navigation Operations:**
```java
TreeMap<Integer, String> numbers = new TreeMap<>();
numbers.put(5, "five");
numbers.put(2, "two");
numbers.put(8, "eight");
numbers.put(1, "one");
numbers.put(7, "seven");

// All navigation operations are O(log n)
Integer first = numbers.firstKey();        // O(log n) - leftmost node
Integer last = numbers.lastKey();          // O(log n) - rightmost node
Integer lower = numbers.lowerKey(5);       // O(log n) - largest < 5
Integer higher = numbers.higherKey(5);     // O(log n) - smallest > 5
Integer floor = numbers.floorKey(4);       // O(log n) - largest <= 4
Integer ceiling = numbers.ceilingKey(4);   // O(log n) - smallest >= 4
```

#### **Range Operations:**
```java
// Range views are O(log n) to create, then depend on range size
NavigableMap<Integer, String> subMap = numbers.subMap(2, true, 7, true);
// Creates view from 2 to 7 (inclusive) - O(log n)

NavigableMap<Integer, String> headMap = numbers.headMap(5, false);
// Creates view of elements < 5 - O(log n)

NavigableMap<Integer, String> tailMap = numbers.tailMap(5, true);
// Creates view of elements >= 5 - O(log n)
```

#### **Performance Comparison:**
```java
public class TreeMapPerformance {
    public void compareWithHashMap() {
        Map<Integer, String> hashMap = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        
        // Insertion performance
        for (int i = 0; i < 100000; i++) {
            hashMap.put(i, "value" + i);   // O(1) average - faster
            treeMap.put(i, "value" + i);   // O(log n) - slower but sorted
        }
        
        // Access performance
        String value1 = hashMap.get(50000);   // O(1) average - faster
        String value2 = treeMap.get(50000);   // O(log n) - slower
        
        // TreeMap advantages: sorted order, range operations
        // HashMap advantages: faster access, lower memory overhead
    }
}
```

### 94. How do you choose the right collection for your use case?

Collection choice depends on **access patterns**, **performance requirements**, and **functional needs**.

#### **Decision Matrix:**

| Use Case | Recommended Collection | Reason |
|----------|----------------------|---------|
| **Indexed access** | ArrayList | O(1) random access |
| **Frequent insertions** | LinkedList | O(1) at known positions |
| **Key-value lookup** | HashMap | O(1) average access |
| **Sorted key-value** | TreeMap | O(log n) with ordering |
| **Unique elements** | HashSet | O(1) uniqueness check |
| **Sorted unique elements** | TreeSet | O(log n) with ordering |
| **FIFO queue** | LinkedList, ArrayDeque | O(1) queue operations |
| **Priority queue** | PriorityQueue | O(log n) priority ordering |
| **Thread-safe** | ConcurrentHashMap, Vector | Built-in synchronization |

#### **Access Pattern Analysis:**

**1. Random Access vs Sequential Access:**
```java
// ✓ ArrayList - frequent random access
public void processDataByIndex(List<String> data) {
    for (int i = 0; i < data.size(); i++) {
        if (shouldProcess(i)) {
            String item = data.get(i);  // O(1) with ArrayList
            process(item);
        }
    }
}

// ✓ LinkedList - sequential access with modifications
public void processAndModify(LinkedList<String> data) {
    Iterator<String> it = data.iterator();
    while (it.hasNext()) {
        String item = it.next();
        if (shouldRemove(item)) {
            it.remove();  // O(1) with iterator
        }
    }
}
```

**2. Lookup Requirements:**
```java
// ✓ HashMap - fast key-based lookup
Map<String, User> userCache = new HashMap<>();
User user = userCache.get(userId);  // O(1) average

// ✓ TreeMap - sorted keys with range queries
TreeMap<Date, Event> eventLog = new TreeMap<>();
Map<Date, Event> todayEvents = eventLog.subMap(startOfDay, endOfDay);

// ✓ HashSet - fast membership testing
Set<String> validCodes = new HashSet<>(Arrays.asList("A", "B", "C"));
boolean isValid = validCodes.contains(inputCode);  // O(1) average
```

**3. Ordering Requirements:**
```java
// ✓ LinkedHashMap - insertion order preservation
Map<String, String> orderedConfig = new LinkedHashMap<>();
// Maintains order for iteration

// ✓ TreeSet - natural ordering
Set<String> sortedNames = new TreeSet<>();
// Always sorted for iteration

// ✓ PriorityQueue - priority-based ordering
Queue<Task> taskQueue = new PriorityQueue<>(Comparator.comparing(Task::getPriority));
```

#### **Performance vs Features Trade-off:**
```java
public class CollectionTradeoffs {
    // Fast access, no ordering
    Map<String, Object> fastMap = new HashMap<>();
    
    // Slower access, maintains order
    Map<String, Object> orderedMap = new TreeMap<>();
    
    // Fast access, insertion order
    Map<String, Object> insertionOrderMap = new LinkedHashMap<>();
    
    // Thread-safe, good performance
    Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
    
    // Thread-safe, poor performance
    Map<String, Object> syncMap = Collections.synchronizedMap(new HashMap<>());
}
```

#### **Size and Memory Considerations:**
```java
// ✓ ArrayList - known size, frequent access
List<String> knownSizeList = new ArrayList<>(1000);  // Pre-size to avoid resizing

// ✓ LinkedList - unknown size, frequent modifications
List<String> dynamicList = new LinkedList<>();  // No wasted space

// ✓ HashMap - load factor tuning
Map<String, Object> memoryOptimized = new HashMap<>(100, 0.9f);  // Higher load factor
Map<String, Object> speedOptimized = new HashMap<>(100, 0.5f);   // Lower load factor
```

### 95. What are the best practices for using collections?

#### **1. Use Generics for Type Safety:**
```java
// ✓ Good - type safe
List<String> names = new ArrayList<>();
names.add("Alice");
String name = names.get(0);  // No casting needed

// ✗ Bad - not type safe
List rawList = new ArrayList();
rawList.add("Alice");
rawList.add(123);  // Runtime error waiting to happen
String name = (String) rawList.get(0);  // Explicit casting required
```

#### **2. Program to Interfaces:**
```java
// ✓ Good - flexible, can change implementation
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
Set<String> set = new HashSet<>();

// ✗ Bad - tied to specific implementation
ArrayList<String> arrayList = new ArrayList<>();
HashMap<String, Integer> hashMap = new HashMap<>();
```

#### **3. Initialize with Appropriate Capacity:**
```java
// ✓ Good - avoid unnecessary resizing
List<String> list = new ArrayList<>(1000);  // Known size
Map<String, Object> map = new HashMap<>(100, 0.75f);  // Expected size

// ✗ Bad - multiple resizes
List<String> smallList = new ArrayList<>();  // Default size 10
for (int i = 0; i < 1000; i++) {
    smallList.add("item" + i);  // Multiple resizes
}
```

#### **4. Use Enhanced For Loops:**
```java
List<String> items = Arrays.asList("a", "b", "c");

// ✓ Good - clean and efficient
for (String item : items) {
    process(item);
}

// ✗ Bad - verbose and error-prone
for (int i = 0; i < items.size(); i++) {
    String item = items.get(i);
    process(item);
}
```

#### **5. Safe Iteration and Modification:**
```java
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));

// ✓ Good - use iterator for safe removal
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (shouldRemove(item)) {
        it.remove();  // Safe removal
    }
}

// ✓ Good - collect items to remove, then remove
List<String> toRemove = new ArrayList<>();
for (String item : list) {
    if (shouldRemove(item)) {
        toRemove.add(item);
    }
}
list.removeAll(toRemove);

// ✗ Bad - ConcurrentModificationException
for (String item : list) {
    if (shouldRemove(item)) {
        list.remove(item);  // Throws exception
    }
}
```

#### **6. Choose Thread-Safe Collections Appropriately:**
```java
// ✓ Good - modern concurrent collections
Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
List<String> copyOnWriteList = new CopyOnWriteArrayList<>();

// ✓ Good - synchronized wrappers when needed
Map<String, Object> syncMap = Collections.synchronizedMap(new HashMap<>());
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// ✗ Bad - using legacy thread-safe collections
Hashtable<String, Object> hashtable = new Hashtable<>();  // Use ConcurrentHashMap instead
Vector<String> vector = new Vector<>();  // Use ArrayList with synchronization if needed
```

#### **7. Use Immutable Collections When Possible:**
```java
// ✓ Good - immutable collections (Java 9+)
List<String> immutableList = List.of("a", "b", "c");
Set<String> immutableSet = Set.of("x", "y", "z");
Map<String, Integer> immutableMap = Map.of("key1", 1, "key2", 2);

// ✓ Good - defensive copying
public List<String> getItems() {
    return new ArrayList<>(items);  // Return copy, not original
}

// ✓ Good - unmodifiable views
List<String> readOnlyView = Collections.unmodifiableList(originalList);
```

#### **8. Null Handling:**
```java
// ✓ Good - null-safe operations
List<String> list = new ArrayList<>();
if (list != null && !list.isEmpty()) {
    process(list);
}

// ✓ Good - use Optional for single values
Optional<String> optionalValue = Optional.ofNullable(getValue());
optionalValue.ifPresent(this::process);

// ✗ Bad - not handling nulls
list.add(null);  // Some collections don't allow nulls (TreeSet, ConcurrentHashMap)
```

#### **9. Performance Monitoring:**
```java
public class CollectionPerformanceMonitoring {
    public void monitorPerformance() {
        // Monitor collection sizes
        if (cache.size() > MAX_CACHE_SIZE) {
            cache.clear();  // Prevent memory leaks
        }
        
        // Use appropriate collection for use case
        if (frequentRandomAccess) {
            useArrayList();
        } else if (frequentInsertions) {
            useLinkedList();
        }
        
        // Profile and measure actual performance
        long start = System.nanoTime();
        performOperation();
        long duration = System.nanoTime() - start;
        logPerformance(duration);
    }
}
```

#### **10. Documentation and Contracts:**
```java
/**
 * Returns an unmodifiable view of user preferences.
 * Changes to the underlying map will be reflected in the returned map.
 * 
 * @return unmodifiable map of preferences, never null
 */
public Map<String, String> getPreferences() {
    return Collections.unmodifiableMap(preferences);
}

/**
 * Adds items to the processing queue.
 * This method is thread-safe.
 * 
 * @param items items to add, must not be null
 * @throws IllegalArgumentException if items is null or contains null elements
 */
public void addItems(List<Item> items) {
    Objects.requireNonNull(items, "Items cannot be null");
    // Implementation
}
```

# 🔹 Multithreading and Concurrency

## Multithreading and Concurrency - Thread Basics

### 96. What is multithreading?

**Multithreading** is the ability to execute multiple threads concurrently within a single process.

#### **Key Concepts:**
- **Concurrent execution** - multiple threads run simultaneously
- **Shared resources** - threads share memory and process resources
- **Independent execution** - each thread has its own execution path
- **Improved performance** - better CPU utilization and responsiveness

#### **Basic Multithreading Example:**
```java
public class MultithreadingExample {
    public static void main(String[] args) {
        // Create multiple threads
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2: " + i);
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
            }
        });
        
        // Start threads - they run concurrently
        thread1.start();
        thread2.start();
        
        System.out.println("Main thread continues...");
    }
}
```

#### **Benefits of Multithreading:**
- **Improved responsiveness** - UI remains responsive during background tasks
- **Better resource utilization** - CPU cores work in parallel
- **Concurrent processing** - multiple tasks execute simultaneously
- **Asynchronous operations** - non-blocking I/O and network operations

#### **Use Cases:**
```java
// Web server handling multiple requests
public class WebServer {
    public void handleRequest(Request request) {
        new Thread(() -> processRequest(request)).start();
    }
}

// Background data processing
public class DataProcessor {
    public void processLargeDataset() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (DataChunk chunk : dataset) {
            executor.submit(() -> processChunk(chunk));
        }
    }
}
```

### 97. What is the difference between process and thread?

| Aspect | Process | Thread |
|--------|---------|--------|
| **Definition** | Independent program execution | Lightweight unit within process |
| **Memory** | Separate memory space | Shared memory space |
| **Communication** | IPC (Inter-Process Communication) | Direct memory sharing |
| **Creation Cost** | Expensive | Lightweight |
| **Context Switching** | Slower | Faster |
| **Isolation** | Fully isolated | Share process resources |
| **Failure Impact** | Isolated failure | Can affect entire process |

#### **Process Characteristics:**
```java
// Each JVM instance is a separate process
// Process 1: java App1
public class App1 {
    public static void main(String[] args) {
        System.out.println("Process 1 - PID: " + ProcessHandle.current().pid());
        // Separate memory space, cannot directly access App2's variables
    }
}

// Process 2: java App2  
public class App2 {
    public static void main(String[] args) {
        System.out.println("Process 2 - PID: " + ProcessHandle.current().pid());
        // Separate memory space, isolated from App1
    }
}
```

#### **Thread Characteristics:**
```java
public class ThreadExample {
    private static int sharedCounter = 0;  // Shared among all threads
    
    public static void main(String[] args) {
        // Multiple threads within same process
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter++;  // Direct access to shared memory
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedCounter++;  // Same shared variable
            }
        });
        
        t1.start();
        t2.start();
    }
}
```

#### **Communication Comparison:**
```java
// Process communication - requires IPC
ProcessBuilder pb = new ProcessBuilder("java", "OtherProcess");
Process process = pb.start();
// Communicate via streams, files, sockets, etc.

// Thread communication - direct memory sharing
class ThreadCommunication {
    private volatile boolean flag = false;
    
    public void thread1() {
        flag = true;  // Direct memory access
    }
    
    public void thread2() {
        while (!flag) {  // Direct memory access
            // Wait for flag
        }
    }
}
```

### 98. How do you create threads in Java?

There are **several ways** to create threads in Java:

#### **1. Extending Thread Class:**
```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread: " + i);
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }
}

// Usage
MyThread thread = new MyThread();
thread.start();  // Creates new thread and calls run()
```

#### **2. Implementing Runnable Interface:**
```java
class MyTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Task: " + i);
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }
}

// Usage
Thread thread = new Thread(new MyTask());
thread.start();
```

#### **3. Lambda Expression (Java 8+):**
```java
// Most concise approach
Thread thread = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("Lambda: " + i);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
});
thread.start();
```

#### **4. Using ExecutorService (Recommended):**
```java
ExecutorService executor = Executors.newFixedThreadPool(2);

// Submit Runnable
executor.submit(() -> {
    System.out.println("Task executed by thread pool");
});

// Submit Callable (returns result)
Future<String> future = executor.submit(() -> {
    return "Task completed";
});

executor.shutdown();
```

#### **5. Thread with Name and Priority:**
```java
Thread thread = new Thread(() -> {
    System.out.println("Running in: " + Thread.currentThread().getName());
}, "MyCustomThread");

thread.setPriority(Thread.MAX_PRIORITY);
thread.start();
```

### 99. What is the difference between extending Thread and implementing Runnable?

| Aspect | Extending Thread | Implementing Runnable |
|--------|------------------|----------------------|
| **Inheritance** | Uses single inheritance slot | Allows multiple inheritance |
| **Design** | IS-A relationship | HAS-A relationship |
| **Reusability** | Less flexible | More reusable |
| **Thread Pool** | Cannot use with executors easily | Works well with thread pools |
| **Separation** | Task and thread coupled | Task and thread separated |

#### **Extending Thread - Less Flexible:**
```java
class PrinterThread extends Thread {
    private String message;
    
    public PrinterThread(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        System.out.println(message);
    }
}

// Problems:
// 1. Cannot extend another class
// 2. Tightly couples task with thread
// 3. Hard to reuse with different thread management strategies

// Usage
PrinterThread thread = new PrinterThread("Hello");
thread.start();
```

#### **Implementing Runnable - More Flexible:**
```java
class PrinterTask implements Runnable {
    private String message;
    
    public PrinterTask(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        System.out.println(message);
    }
}

// Advantages:
// 1. Can extend other classes
// 2. Separates task from thread management
// 3. Reusable with different execution strategies

// Multiple usage options
PrinterTask task = new PrinterTask("Hello");

// Option 1: Traditional thread
new Thread(task).start();

// Option 2: Thread pool
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(task);

// Option 3: Scheduled execution
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.schedule(task, 5, TimeUnit.SECONDS);
```

#### **Inheritance Flexibility:**
```java
// With Runnable - can extend other classes
class DatabaseTask extends DatabaseConnection implements Runnable {
    @Override
    public void run() {
        // Can use inherited database methods
        connect();
        executeQuery();
        disconnect();
    }
}

// With Thread - cannot extend other classes
// class DatabaseThread extends Thread, DatabaseConnection { // ✗ Not allowed
```

### 100. What are the states of a thread?

Java threads have **6 states** defined in `Thread.State` enum:

#### **Thread State Diagram:**
```
NEW → RUNNABLE ⇄ BLOCKED
         ↕         ↕
    TIMED_WAITING ← WAITING
         ↓         ↓
      TERMINATED ←──┘
```

#### **State Descriptions:**

| State | Description | How to Enter | How to Exit |
|-------|-------------|--------------|-------------|
| **NEW** | Created but not started | `new Thread()` | `thread.start()` |
| **RUNNABLE** | Executing or ready to execute | `start()` called | Various transitions |
| **BLOCKED** | Waiting for monitor lock | Trying to enter synchronized block | Acquiring the lock |
| **WAITING** | Waiting indefinitely | `wait()`, `join()` | `notify()`, `notifyAll()` |
| **TIMED_WAITING** | Waiting for specified time | `sleep()`, `wait(timeout)` | Timeout or notification |
| **TERMINATED** | Execution completed | `run()` method ends | Final state |

#### **State Transition Examples:**
```java
public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread running");
                Thread.sleep(2000);  // TIMED_WAITING
                System.out.println("Thread finishing");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        System.out.println("State: " + thread.getState());  // NEW
        
        thread.start();
        System.out.println("State: " + thread.getState());  // RUNNABLE
        
        Thread.sleep(100);
        System.out.println("State: " + thread.getState());  // TIMED_WAITING
        
        thread.join();  // Wait for completion
        System.out.println("State: " + thread.getState());  // TERMINATED
    }
}
```

#### **Monitoring Thread States:**
```java
public class ThreadStateMonitor {
    public void monitorThreadStates() {
        Thread worker = new Thread(() -> {
            synchronized (this) {
                try {
                    wait(1000);  // TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Monitor state changes
        new Thread(() -> {
            while (worker.getState() != Thread.State.TERMINATED) {
                System.out.println("Worker state: " + worker.getState());
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        }).start();
        
        worker.start();
    }
}
```

### 101. What is the difference between start() and run() methods?

| Method | Behavior | Thread Creation | Use Case |
|--------|----------|----------------|----------|
| **start()** | Creates new thread, then calls run() | Yes | Multithreading |
| **run()** | Executes in current thread | No | Sequential execution |

#### **start() Method - Creates New Thread:**
```java
public class StartVsRun {
    public static void demonstrateStart() {
        Thread thread = new Thread(() -> {
            System.out.println("Running in: " + Thread.currentThread().getName());
            for (int i = 0; i < 3; i++) {
                System.out.println("Count: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        System.out.println("Main thread: " + Thread.currentThread().getName());
        thread.start();  // Creates new thread
        System.out.println("Main continues immediately");
        
        // Output:
        // Main thread: main
        // Main continues immediately
        // Running in: Thread-0
        // Count: 0
        // Count: 1
        // Count: 2
    }
}
```

#### **run() Method - Executes in Current Thread:**
```java
public class RunMethodExample {
    public static void demonstrateRun() {
        Thread thread = new Thread(() -> {
            System.out.println("Running in: " + Thread.currentThread().getName());
            for (int i = 0; i < 3; i++) {
                System.out.println("Count: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        System.out.println("Main thread: " + Thread.currentThread().getName());
        thread.run();  // Executes in main thread
        System.out.println("Main continues after run() completes");
        
        // Output:
        // Main thread: main
        // Running in: main
        // Count: 0
        // Count: 1
        // Count: 2
        // Main continues after run() completes
    }
}
```

#### **Key Differences Demonstration:**
```java
public class StartRunComparison {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        };
        
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        
        System.out.println("=== Using start() ===");
        t1.start();  // Runs concurrently
        t2.start();  // Runs concurrently
        
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        System.out.println("\n=== Using run() ===");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");
        
        t3.run();  // Runs sequentially in main thread
        t4.run();  // Runs sequentially in main thread
    }
}
```

### 102. What is thread priority?

**Thread priority** is a hint to the thread scheduler about the relative importance of threads.

#### **Priority Levels:**
```java
public class ThreadPriority {
    public static void demonstratePriority() {
        Thread lowPriority = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Low priority: " + i);
            }
        });
        
        Thread highPriority = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("High priority: " + i);
            }
        });
        
        // Set priorities
        lowPriority.setPriority(Thread.MIN_PRIORITY);    // 1
        highPriority.setPriority(Thread.MAX_PRIORITY);   // 10
        
        // Default priority is NORM_PRIORITY (5)
        System.out.println("Default priority: " + Thread.NORM_PRIORITY);
        
        lowPriority.start();
        highPriority.start();
    }
}
```

#### **Priority Constants:**
```java
// Thread priority constants
Thread.MIN_PRIORITY  = 1
Thread.NORM_PRIORITY = 5  (default)
Thread.MAX_PRIORITY  = 10

// Setting priority
thread.setPriority(8);  // Custom priority
int priority = thread.getPriority();  // Get current priority
```

#### **Important Notes:**
```java
public class PriorityLimitations {
    public void demonstrateLimitations() {
        Thread thread = new Thread(() -> {
            System.out.println("Thread priority: " + Thread.currentThread().getPriority());
        });
        
        // Priority is just a hint, not a guarantee
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        
        // Platform-dependent behavior
        // Some operating systems ignore Java thread priorities
        // Modern JVMs may not strictly follow priority ordering
    }
    
    // Better approach: Use ExecutorService with proper task management
    public void betterApproach() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit high-priority tasks first
        executor.submit(highPriorityTask);
        executor.submit(lowPriorityTask);
        
        executor.shutdown();
    }
}
```

### 103. What is daemon thread?

**Daemon threads** are background service threads that don't prevent JVM from exiting.

#### **Characteristics:**
- **Background service** - provide services to user threads
- **JVM exit** - don't prevent JVM shutdown
- **Automatic termination** - killed when all user threads finish
- **Examples** - Garbage Collector, JIT Compiler

#### **Creating Daemon Threads:**
```java
public class DaemonThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("User thread: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
            System.out.println("User thread finished");
        });
        
        Thread daemonThread = new Thread(() -> {
            while (true) {  // Infinite loop
                System.out.println("Daemon thread running...");
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });
        
        // Set as daemon BEFORE starting
        daemonThread.setDaemon(true);
        
        userThread.start();
        daemonThread.start();
        
        // JVM will exit when userThread finishes
        // daemonThread will be terminated automatically
    }
}
```

#### **Daemon Thread Rules:**
```java
public class DaemonRules {
    public void demonstrateRules() {
        Thread thread = new Thread(() -> {
            System.out.println("Is daemon: " + Thread.currentThread().isDaemon());
        });
        
        // Must set daemon status before starting
        thread.setDaemon(true);  // ✓ Valid
        thread.start();
        
        // thread.setDaemon(true);  // ✗ IllegalThreadStateException
    }
    
    public void daemonInheritance() {
        Thread parentThread = new Thread(() -> {
            // Child threads inherit daemon status from parent
            Thread childThread = new Thread(() -> {
                System.out.println("Child is daemon: " + Thread.currentThread().isDaemon());
            });
            childThread.start();
        });
        
        parentThread.setDaemon(true);
        parentThread.start();
        // Child thread will also be daemon
    }
}
```

#### **Common Use Cases:**
```java
// Background monitoring
Thread monitorThread = new Thread(() -> {
    while (true) {
        checkSystemHealth();
        try { Thread.sleep(5000); } catch (InterruptedException e) { break; }
    }
});
monitorThread.setDaemon(true);
monitorThread.start();

// Periodic cleanup
Thread cleanupThread = new Thread(() -> {
    while (true) {
        cleanupTempFiles();
        try { Thread.sleep(60000); } catch (InterruptedException e) { break; }
    }
});
cleanupThread.setDaemon(true);
cleanupThread.start();
```

### 104. What is the difference between user thread and daemon thread?

| Aspect | User Thread | Daemon Thread |
|--------|-------------|---------------|
| **JVM Exit** | Prevents JVM exit | Doesn't prevent JVM exit |
| **Priority** | High priority for JVM | Low priority for JVM |
| **Purpose** | Main application logic | Background services |
| **Lifecycle** | Must complete before JVM exit | Terminated when JVM exits |
| **Default** | All threads are user threads by default | Must be explicitly set |

#### **JVM Exit Behavior:**
```java
public class UserVsDaemonThreads {
    public static void demonstrateJVMExit() {
        // User thread - keeps JVM alive
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("User thread: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        
        // Daemon thread - doesn't keep JVM alive
        Thread daemonThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {  // Long running
                System.out.println("Daemon thread: " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });
        
        daemonThread.setDaemon(true);
        
        userThread.start();
        daemonThread.start();
        
        // JVM will exit after userThread completes
        // daemonThread will be forcibly terminated
    }
}
```

#### **Thread Type Checking:**
```java
public class ThreadTypeChecker {
    public void checkThreadTypes() {
        Thread currentThread = Thread.currentThread();
        System.out.println("Main thread is daemon: " + currentThread.isDaemon());  // false
        
        Thread userThread = new Thread(() -> {});
        System.out.println("User thread is daemon: " + userThread.isDaemon());  // false
        
        Thread daemonThread = new Thread(() -> {});
        daemonThread.setDaemon(true);
        System.out.println("Daemon thread is daemon: " + daemonThread.isDaemon());  // true
    }
}
```

#### **Practical Example:**
```java
public class WebServer {
    public static void main(String[] args) {
        // Main server thread (user thread)
        Thread serverThread = new Thread(() -> {
            System.out.println("Server starting...");
            // Server logic here
            try { Thread.sleep(10000); } catch (InterruptedException e) {}
            System.out.println("Server shutting down...");
        });
        
        // Background monitoring (daemon thread)
        Thread monitorThread = new Thread(() -> {
            while (true) {
                System.out.println("Monitoring system...");
                try { Thread.sleep(2000); } catch (InterruptedException e) { break; }
            }
        });
        monitorThread.setDaemon(true);
        
        serverThread.start();
        monitorThread.start();
        
        // When serverThread finishes, JVM exits
        // monitorThread is automatically terminated
    }
}
```

### 105. How do you stop a thread?

**Never use deprecated `stop()` method**. Use **cooperative cancellation** with interruption mechanism.

#### **❌ Deprecated Approach (Don't Use):**
```java
// DON'T DO THIS - deprecated and unsafe
Thread thread = new Thread(() -> {
    while (true) {
        // Some work
    }
});
thread.start();
// thread.stop();  // ✗ Deprecated - can cause data corruption
```

#### **✅ Correct Approach 1 - Interruption:**
```java
public class ThreadInterruption {
    public static void demonstrateInterruption() throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // Simulate work
                    System.out.println("Working...");
                    Thread.sleep(1000);  // InterruptedException if interrupted
                } catch (InterruptedException e) {
                    // Restore interrupt status and exit
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted, exiting...");
                    break;
                }
            }
        });
        
        worker.start();
        Thread.sleep(3000);  // Let it work for 3 seconds
        worker.interrupt();  // Request interruption
        worker.join();       // Wait for completion
    }
}
```

#### **✅ Correct Approach 2 - Volatile Flag:**
```java
public class VolatileFlagCancellation {
    private volatile boolean running = true;
    
    public void startWorker() {
        Thread worker = new Thread(() -> {
            while (running) {
                // Do work
                System.out.println("Working...");
                try { Thread.sleep(1000); } catch (InterruptedException e) {
                    // Handle interruption but continue checking flag
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Worker stopped");
        });
        
        worker.start();
        
        // Stop after 5 seconds
        new Thread(() -> {
            try { Thread.sleep(5000); } catch (InterruptedException e) {}
            running = false;  // Signal to stop
        }).start();
    }
}
```

#### **✅ Correct Approach 3 - ExecutorService:**
```java
public class ExecutorServiceCancellation {
    public void demonstrateExecutorCancellation() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<?> future = executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("Task running...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        Thread.sleep(3000);
        future.cancel(true);  // Interrupt the task
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
```

#### **Handling InterruptedException Properly:**
```java
public class ProperInterruptionHandling {
    public void handleInterruption() {
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    doWork();
                    Thread.sleep(1000);  // Blocking operation
                }
            } catch (InterruptedException e) {
                // Option 1: Restore interrupt status and exit
                Thread.currentThread().interrupt();
                return;
                
                // Option 2: Handle and continue (if appropriate)
                // handleInterruption();
            }
        });
        
        worker.start();
    }
    
    private void doWork() {
        // Non-blocking work
        System.out.println("Doing work...");
    }
}
```

#### **Best Practices for Thread Stopping:**
```java
public class ThreadStoppingBestPractices {
    // 1. Design threads to be interruptible
    public void interruptibleTask() {
        while (!Thread.currentThread().isInterrupted()) {
            // Check interruption status regularly
            if (Thread.interrupted()) {
                break;  // Exit gracefully
            }
            
            // Do work in small chunks
            doSmallAmountOfWork();
        }
    }
    
    // 2. Use timeout for blocking operations
    public void timeoutExample() {
        try {
            // Use timeout instead of indefinite blocking
            boolean acquired = semaphore.tryAcquire(5, TimeUnit.SECONDS);
            if (!acquired) {
                return;  // Timeout, exit gracefully
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
    
    // 3. Cleanup resources properly
    public void resourceCleanup() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("file.txt");
            while (!Thread.currentThread().isInterrupted()) {
                // Process file
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (fis != null) {
                try { fis.close(); } catch (IOException e) {}
            }
        }
    }
}
```

## Synchronization

### 106. What is synchronization in Java?

**Synchronization** is a mechanism to control access to shared resources by multiple threads to prevent data corruption and race conditions.

#### **Purpose of Synchronization:**
- **Prevent race conditions** - avoid unpredictable behavior
- **Ensure data consistency** - maintain data integrity
- **Coordinate thread access** - control shared resource usage
- **Provide thread safety** - guarantee correct concurrent execution

#### **Problem Without Synchronization:**
```java
public class UnsafeCounter {
    private int count = 0;
    
    public void increment() {
        count++;  // Not atomic: read, increment, write
    }
    
    public int getCount() {
        return count;
    }
    
    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();
        
        // Create 1000 threads, each incrementing 1000 times
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();  // Race condition!
                }
            });
            threads[i].start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("Expected: 1000000");
        System.out.println("Actual: " + counter.getCount());  // Usually less than 1000000
    }
}
```

#### **Solution With Synchronization:**
```java
public class SafeCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;  // Now thread-safe
    }
    
    public synchronized int getCount() {
        return count;
    }
    
    // Now the result will always be 1000000
}
```

#### **Types of Synchronization:**
- **Method-level synchronization** - entire method is synchronized
- **Block-level synchronization** - specific code blocks are synchronized
- **Static synchronization** - class-level locking
- **Object-level synchronization** - instance-level locking

### 107. What is the synchronized keyword?

The **synchronized keyword** provides mutual exclusion by allowing only one thread to execute synchronized code at a time.

#### **Synchronized Method:**
```java
public class SynchronizedMethods {
    private int balance = 1000;
    
    // Synchronized instance method - locks on 'this'
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println("Withdrawing: " + amount);
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        }
    }
    
    // Synchronized static method - locks on Class object
    public static synchronized void staticMethod() {
        System.out.println("Static synchronized method");
    }
}
```

#### **Synchronized Block:**
```java
public class SynchronizedBlocks {
    private int balance = 1000;
    private final Object lock = new Object();
    
    public void withdraw(int amount) {
        // Synchronized block with custom lock
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }
    
    public void deposit(int amount) {
        // Synchronized block with 'this' as lock
        synchronized (this) {
            balance += amount;
        }
    }
    
    // Multiple synchronized blocks can use different locks
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            // Critical section 1
        }
    }
    
    public void method2() {
        synchronized (lock2) {
            // Critical section 2 - can run concurrently with method1
        }
    }
}
```

#### **How Synchronized Works:**
```java
public class SynchronizedMechanism {
    // Every object has an intrinsic lock (monitor)
    private final Object monitor = new Object();
    
    public void demonstrateLocking() {
        synchronized (monitor) {
            // 1. Thread acquires monitor lock
            // 2. Executes critical section
            // 3. Releases lock when exiting block
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
        // Lock is automatically released here
    }
}
```

### 108. What is the difference between synchronized method and synchronized block?

| Aspect | Synchronized Method | Synchronized Block |
|--------|-------------------|-------------------|
| **Granularity** | Entire method | Specific code section |
| **Lock Object** | `this` (instance) or `Class` (static) | Any object |
| **Performance** | Lower (locks entire method) | Higher (locks only needed section) |
| **Flexibility** | Less flexible | More flexible |
| **Syntax** | Method modifier | Block with lock object |

#### **Synchronized Method Examples:**
```java
public class SynchronizedMethodExample {
    private int value = 0;
    
    // Locks entire method on 'this'
    public synchronized void incrementAndPrint() {
        value++;                           // Critical section
        System.out.println(value);         // Critical section
        doSomeOtherWork();                 // Also locked (may not need to be)
    }
    
    // Static synchronized method - locks on Class object
    public static synchronized void staticMethod() {
        System.out.println("Static method");
    }
    
    private void doSomeOtherWork() {
        // Some work that doesn't need synchronization
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
}
```

#### **Synchronized Block Examples:**
```java
public class SynchronizedBlockExample {
    private int value = 0;
    private final Object lock = new Object();
    
    public void incrementAndPrint() {
        // Only critical section is synchronized
        synchronized (lock) {
            value++;                       // Critical section
            System.out.println(value);     // Critical section
        }
        doSomeOtherWork();                 // Not synchronized - better performance
    }
    
    // Multiple locks for different resources
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private int resource1 = 0;
    private int resource2 = 0;
    
    public void updateResource1() {
        synchronized (lock1) {
            resource1++;
        }
    }
    
    public void updateResource2() {
        synchronized (lock2) {  // Can run concurrently with updateResource1
            resource2++;
        }
    }
}
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    private int counter = 0;
    
    // Synchronized method - locks entire method
    public synchronized void synchronizedMethod() {
        counter++;                    // Needs synchronization
        expensiveOperation();         // Doesn't need synchronization but is locked
    }
    
    // Synchronized block - locks only what's needed
    public void synchronizedBlock() {
        synchronized (this) {
            counter++;                // Only this needs synchronization
        }
        expensiveOperation();         // Not synchronized - better concurrency
    }
    
    private void expensiveOperation() {
        // Expensive operation that doesn't need synchronization
        try { Thread.sleep(10); } catch (InterruptedException e) {}
    }
}
```

### 109. What is deadlock?

**Deadlock** occurs when two or more threads are blocked forever, each waiting for the other to release a resource.

#### **Deadlock Conditions (All must be true):**
1. **Mutual Exclusion** - resources cannot be shared
2. **Hold and Wait** - threads hold resources while waiting for others
3. **No Preemption** - resources cannot be forcibly taken
4. **Circular Wait** - circular chain of threads waiting for each other

#### **Classic Deadlock Example:**
```java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock1...");
            
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            
            System.out.println("Thread 1: Waiting for lock2...");
            synchronized (lock2) {
                System.out.println("Thread 1: Acquired lock2!");
            }
        }
    }
    
    public void method2() {
        synchronized (lock2) {
            System.out.println("Thread 2: Holding lock2...");
            
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            
            System.out.println("Thread 2: Waiting for lock1...");
            synchronized (lock1) {
                System.out.println("Thread 2: Acquired lock1!");
            }
        }
    }
    
    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();
        
        Thread t1 = new Thread(example::method1);
        Thread t2 = new Thread(example::method2);
        
        t1.start();
        t2.start();
        
        // Deadlock occurs:
        // Thread 1 holds lock1, waits for lock2
        // Thread 2 holds lock2, waits for lock1
        // Both threads wait forever
    }
}
```

#### **Deadlock Detection:**
```java
public class DeadlockDetection {
    public static void detectDeadlock() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadBean.findDeadlockedThreads();
        
        if (deadlockedThreads != null) {
            ThreadInfo[] threadInfos = threadBean.getThreadInfo(deadlockedThreads);
            System.out.println("Deadlock detected!");
            for (ThreadInfo threadInfo : threadInfos) {
                System.out.println("Thread: " + threadInfo.getThreadName());
                System.out.println("Blocked on: " + threadInfo.getLockName());
            }
        }
    }
}
```

### 110. How do you prevent deadlock?

#### **Prevention Strategies:**

**1. Lock Ordering - Most Effective:**
```java
public class DeadlockPrevention {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // Always acquire locks in same order
    public void method1() {
        synchronized (lock1) {      // Always acquire lock1 first
            synchronized (lock2) {  // Then lock2
                // Critical section
            }
        }
    }
    
    public void method2() {
        synchronized (lock1) {      // Same order: lock1 first
            synchronized (lock2) {  // Then lock2
                // Critical section
            }
        }
    }
}
```

**2. Timeout with tryLock:**
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class TimeoutPrevention {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();
    
    public void safeMethod() {
        boolean acquired1 = false;
        boolean acquired2 = false;
        
        try {
            acquired1 = lock1.tryLock(1, TimeUnit.SECONDS);
            if (acquired1) {
                acquired2 = lock2.tryLock(1, TimeUnit.SECONDS);
                if (acquired2) {
                    // Both locks acquired - safe to proceed
                    performOperation();
                } else {
                    System.out.println("Could not acquire lock2, avoiding deadlock");
                }
            } else {
                System.out.println("Could not acquire lock1, avoiding deadlock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (acquired2) lock2.unlock();
            if (acquired1) lock1.unlock();
        }
    }
}
```

**3. Avoid Nested Locks:**
```java
public class AvoidNestedLocks {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // Instead of nested locks
    public void badApproach() {
        synchronized (lock1) {
            synchronized (lock2) {  // Nested - potential deadlock
                // Critical section
            }
        }
    }
    
    // Use separate synchronized blocks
    public void goodApproach() {
        synchronized (lock1) {
            // Work with resource 1
        }
        
        synchronized (lock2) {
            // Work with resource 2
        }
    }
}
```

**4. Use Higher-Level Concurrency Utilities:**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HigherLevelUtilities {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void avoidDeadlockWithExecutor() {
        // Use thread pools instead of manual synchronization
        executor.submit(() -> {
            // Task 1
        });
        
        executor.submit(() -> {
            // Task 2
        });
    }
}
```

### 111. What is race condition?

**Race condition** occurs when multiple threads access shared data concurrently and the final result depends on the timing of thread execution.

#### **Race Condition Example:**
```java
public class RaceConditionExample {
    private int counter = 0;
    
    public void increment() {
        // This is NOT atomic - race condition!
        // Step 1: Read current value
        // Step 2: Increment value
        // Step 3: Write back value
        counter++;
    }
    
    public static void main(String[] args) throws InterruptedException {
        RaceConditionExample example = new RaceConditionExample();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + example.counter);  // Often less than 2000
    }
}
```

#### **Why Race Conditions Occur:**
```java
// Thread interleaving example
// Thread 1: counter = 5
// Thread 1: reads counter (5)
// Thread 2: reads counter (5)  ← Both read same value
// Thread 1: increments (5 + 1 = 6)
// Thread 2: increments (5 + 1 = 6)  ← Both calculate same result
// Thread 1: writes counter (6)
// Thread 2: writes counter (6)  ← Lost increment!
// Result: counter = 6 instead of 7
```

#### **Solutions to Race Conditions:**

**1. Synchronization:**
```java
public class SynchronizedSolution {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;  // Now atomic
    }
}
```

**2. Atomic Variables:**
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSolution {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet();  // Atomic operation
    }
}
```

**3. Locks:**
```java
import java.util.concurrent.locks.ReentrantLock;

public class LockSolution {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
```

### 112. What is thread safety?

**Thread safety** means code behaves correctly when accessed by multiple threads simultaneously.

#### **Levels of Thread Safety:**

**1. Immutable - Highest Level:**
```java
public final class ImmutableClass {
    private final int value;
    private final String name;
    
    public ImmutableClass(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public int getValue() { return value; }
    public String getName() { return name; }
    
    // No setters - object cannot be modified
    // Thread-safe by design
}
```

**2. Thread-Safe Classes:**
```java
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;

public class ThreadSafeCollections {
    // Thread-safe collections
    private final Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
    private final List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    
    public void safeOperations() {
        // These operations are thread-safe
        concurrentMap.put("key", 1);
        synchronizedList.add("item");
    }
}
```

**3. Conditionally Thread-Safe:**
```java
public class ConditionallyThreadSafe {
    private final List<String> list = Collections.synchronizedList(new ArrayList<>());
    
    public void safeOperation() {
        list.add("item");  // Thread-safe
    }
    
    public void unsafeOperation() {
        // Iteration requires external synchronization
        synchronized (list) {
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
```

**4. Not Thread-Safe:**
```java
public class NotThreadSafe {
    private List<String> list = new ArrayList<>();  // Not thread-safe
    private int counter = 0;                        // Not thread-safe
    
    public void unsafeOperations() {
        list.add("item");  // Race condition possible
        counter++;         // Race condition possible
    }
}
```

#### **Achieving Thread Safety:**

**1. Synchronization:**
```java
public class SynchronizedThreadSafety {
    private int value = 0;
    
    public synchronized void setValue(int value) {
        this.value = value;
    }
    
    public synchronized int getValue() {
        return value;
    }
}
```

**2. Volatile for Simple Cases:**
```java
public class VolatileThreadSafety {
    private volatile boolean flag = false;
    
    public void setFlag(boolean flag) {
        this.flag = flag;  // Thread-safe for simple assignment
    }
    
    public boolean getFlag() {
        return flag;       // Thread-safe for simple read
    }
}
```

**3. Atomic Classes:**
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThreadSafety {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet();  // Thread-safe
    }
    
    public int getValue() {
        return counter.get();       // Thread-safe
    }
}
```

### 113. What is volatile keyword?

The **volatile keyword** ensures variable visibility across threads and prevents certain compiler optimizations.

#### **Volatile Guarantees:**
- **Visibility** - changes are immediately visible to all threads
- **Ordering** - prevents reordering of volatile reads/writes
- **No caching** - reads/writes go directly to main memory

#### **Volatile Example:**
```java
public class VolatileExample {
    private volatile boolean running = true;  // Volatile ensures visibility
    
    public void startWorker() {
        Thread worker = new Thread(() -> {
            while (running) {  // Always reads fresh value from main memory
                doWork();
            }
            System.out.println("Worker stopped");
        });
        worker.start();
    }
    
    public void stopWorker() {
        running = false;  // Change immediately visible to worker thread
    }
    
    private void doWork() {
        // Some work
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
}
```

#### **Without Volatile - Problem:**
```java
public class WithoutVolatile {
    private boolean running = true;  // Not volatile
    
    public void startWorker() {
        Thread worker = new Thread(() -> {
            // Compiler might optimize this to:
            // if (running) { while(true) { doWork(); } }
            // Worker thread might never see running = false
            while (running) {
                doWork();
            }
        });
        worker.start();
    }
    
    public void stopWorker() {
        running = false;  // Change might not be visible to worker thread
    }
}
```

#### **Volatile Use Cases:**

**1. Status Flags:**
```java
public class StatusFlag {
    private volatile boolean shutdown = false;
    
    public void shutdown() {
        shutdown = true;
    }
    
    public void doWork() {
        while (!shutdown) {
            // Process work
        }
    }
}
```

**2. Double-Checked Locking:**
```java
public class Singleton {
    private static volatile Singleton instance;  // Volatile prevents reordering
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();  // Volatile ensures proper initialization
                }
            }
        }
        return instance;
    }
}
```

#### **Volatile Limitations:**
```java
public class VolatileLimitations {
    private volatile int counter = 0;
    
    public void increment() {
        counter++;  // NOT atomic! Still has race condition
        // This is actually: counter = counter + 1
        // Read, increment, write - not atomic even with volatile
    }
    
    // Solution: Use AtomicInteger or synchronization
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    public void safeIncrement() {
        atomicCounter.incrementAndGet();  // Atomic operation
    }
}
```

### 114. What is the difference between synchronized and volatile?

| Aspect | synchronized | volatile |
|--------|-------------|----------|
| **Purpose** | Mutual exclusion + visibility | Visibility only |
| **Atomicity** | Provides atomicity | No atomicity |
| **Blocking** | Can block threads | Non-blocking |
| **Performance** | Higher overhead | Lower overhead |
| **Scope** | Methods/blocks | Variables only |
| **Compound Operations** | Safe | Not safe |

#### **Synchronized - Mutual Exclusion:**
```java
public class SynchronizedExample {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;  // Atomic: only one thread can execute this
    }
    
    public synchronized int getCounter() {
        return counter;  // Consistent read
    }
    
    // Provides both atomicity and visibility
}
```

#### **Volatile - Visibility Only:**
```java
public class VolatileExample {
    private volatile int counter = 0;
    
    public void increment() {
        counter++;  // NOT atomic! Race condition still exists
        // Multiple threads can interfere with each other
    }
    
    public int getCounter() {
        return counter;  // Always reads fresh value
    }
    
    // Provides visibility but not atomicity
}
```

#### **When to Use Each:**

**Use synchronized for:**
```java
public class UseSynchronized {
    private int balance = 1000;
    
    // Compound operation - needs atomicity
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {  // Read
            balance -= amount;    // Write
        }
    }
    
    // Multiple operations that must be atomic together
    public synchronized void transfer(Account other, int amount) {
        this.balance -= amount;
        other.balance += amount;
    }
}
```

**Use volatile for:**
```java
public class UseVolatile {
    private volatile boolean shutdown = false;
    private volatile int status = 0;
    
    // Simple flag - only needs visibility
    public void shutdown() {
        shutdown = true;  // Simple write
    }
    
    public boolean isShutdown() {
        return shutdown;  // Simple read
    }
    
    // Status updates - simple assignments
    public void updateStatus(int newStatus) {
        status = newStatus;  // Simple write, visibility needed
    }
}
```

#### **Performance Comparison:**
```java
public class PerformanceComparison {
    private volatile int volatileCounter = 0;
    private int synchronizedCounter = 0;
    
    // Faster - no blocking
    public void incrementVolatile() {
        volatileCounter++;  // Still not thread-safe for increment!
    }
    
    // Slower - thread blocking
    public synchronized void incrementSynchronized() {
        synchronizedCounter++;  // Thread-safe but slower
    }
    
    // Best of both worlds for simple operations
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    public void incrementAtomic() {
        atomicCounter.incrementAndGet();  // Fast and thread-safe
    }
}
```

### 115. What is atomic operation?

An **atomic operation** completes entirely or not at all, without interruption from other threads.

#### **Characteristics of Atomic Operations:**
- **Indivisible** - cannot be interrupted midway
- **Thread-safe** - safe for concurrent access
- **No partial states** - either complete success or complete failure
- **Lock-free** - no explicit synchronization needed

#### **Java's Atomic Classes:**
```java
import java.util.concurrent.atomic.*;

public class AtomicOperations {
    // Atomic primitives
    private final AtomicInteger atomicInt = new AtomicInteger(0);
    private final AtomicLong atomicLong = new AtomicLong(0L);
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final AtomicReference<String> atomicRef = new AtomicReference<>("initial");
    
    public void demonstrateAtomicOperations() {
        // All these operations are atomic
        atomicInt.incrementAndGet();        // Atomic increment
        atomicInt.decrementAndGet();        // Atomic decrement
        atomicInt.addAndGet(5);             // Atomic add
        atomicInt.compareAndSet(5, 10);     // Atomic compare-and-swap
        
        atomicBoolean.compareAndSet(false, true);  // Atomic flag toggle
        atomicRef.compareAndSet("old", "new");     // Atomic reference update
    }
}
```

#### **Compare-and-Swap (CAS) Operations:**
```java
public class CompareAndSwap {
    private final AtomicInteger value = new AtomicInteger(0);
    
    public void atomicIncrement() {
        int current;
        int updated;
        do {
            current = value.get();           // Read current value
            updated = current + 1;           // Calculate new value
        } while (!value.compareAndSet(current, updated));  // Atomic update
        
        // CAS ensures: if value is still 'current', set it to 'updated'
        // If another thread changed it, retry the operation
    }
    
    public void safeUpdate(int newValue) {
        // Only update if current value is less than new value
        int current;
        do {
            current = value.get();
            if (current >= newValue) {
                break;  // Don't update
            }
        } while (!value.compareAndSet(current, newValue));
    }
}
```

#### **Atomic vs Non-Atomic Operations:**
```java
public class AtomicVsNonAtomic {
    private volatile int volatileCounter = 0;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    // NOT atomic - race condition possible
    public void nonAtomicIncrement() {
        volatileCounter++;  // Read, increment, write - 3 separate operations
    }
    
    // Atomic - thread-safe
    public void atomicIncrement() {
        atomicCounter.incrementAndGet();  // Single atomic operation
    }
    
    // Demonstration of the difference
    public static void main(String[] args) throws InterruptedException {
        AtomicVsNonAtomic example = new AtomicVsNonAtomic();
        
        // Create 1000 threads, each incrementing 1000 times
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.nonAtomicIncrement();  // Race conditions
                    example.atomicIncrement();     // Thread-safe
                }
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("Non-atomic result: " + example.volatileCounter);  // Usually < 1000000
        System.out.println("Atomic result: " + example.atomicCounter.get()); // Always 1000000
    }
}
```

#### **Custom Atomic Operations:**
```java
public class CustomAtomicOperations {
    private final AtomicReference<Node> head = new AtomicReference<>();
    
    static class Node {
        final int value;
        final Node next;
        
        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    
    // Lock-free stack push
    public void push(int value) {
        Node newNode;
        Node currentHead;
        do {
            currentHead = head.get();
            newNode = new Node(value, currentHead);
        } while (!head.compareAndSet(currentHead, newNode));
    }
    
    // Lock-free stack pop
    public Integer pop() {
        Node currentHead;
        Node newHead;
        do {
            currentHead = head.get();
            if (currentHead == null) {
                return null;  // Stack is empty
            }
            newHead = currentHead.next;
        } while (!head.compareAndSet(currentHead, newHead));
        
        return currentHead.value;
    }
}
```

#### **Performance Benefits:**
```java
public class AtomicPerformance {
    private int synchronizedCounter = 0;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    
    // Slower - uses locks
    public synchronized void synchronizedIncrement() {
        synchronizedCounter++;
    }
    
    // Faster - lock-free
    public void atomicIncrement() {
        atomicCounter.incrementAndGet();
    }
    
    // Atomic operations are generally faster than synchronized
    // because they don't involve thread blocking/unblocking
}
```

## Advanced Concurrency

### 116. What is java.util.concurrent package?

The **java.util.concurrent package** provides high-level concurrency utilities that are more powerful and easier to use than low-level synchronization primitives.

#### **Key Components:**

| Category | Classes/Interfaces | Purpose |
|----------|-------------------|---------|
| **Executors** | ExecutorService, ThreadPoolExecutor | Thread pool management |
| **Locks** | ReentrantLock, ReadWriteLock | Advanced locking mechanisms |
| **Atomic Variables** | AtomicInteger, AtomicReference | Lock-free thread-safe operations |
| **Synchronization Aids** | CountDownLatch, CyclicBarrier, Semaphore | Thread coordination |
| **Concurrent Collections** | ConcurrentHashMap, BlockingQueue | Thread-safe data structures |
| **Asynchronous Programming** | Future, CompletableFuture | Async task management |

#### **Benefits Over Traditional Synchronization:**
```java
// Traditional approach - manual thread management
public class TraditionalApproach {
    public void processData() {
        Thread t1 = new Thread(() -> processChunk1());
        Thread t2 = new Thread(() -> processChunk2());
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Modern approach - using java.util.concurrent
import java.util.concurrent.*;

public class ModernApproach {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void processData() {
        Future<?> future1 = executor.submit(() -> processChunk1());
        Future<?> future2 = executor.submit(() -> processChunk2());
        
        try {
            future1.get();  // Wait for completion
            future2.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

#### **Package Structure:**
```java
// Core interfaces and classes
java.util.concurrent.ExecutorService
java.util.concurrent.Future
java.util.concurrent.CompletableFuture

// Locks package
java.util.concurrent.locks.ReentrantLock
java.util.concurrent.locks.ReadWriteLock

// Atomic package
java.util.concurrent.atomic.AtomicInteger
java.util.concurrent.atomic.AtomicReference
```

### 117. What is ExecutorService?

**ExecutorService** is a high-level interface for managing and controlling thread execution, providing thread pool management and task lifecycle control.

#### **Key Features:**
- **Thread pool management** - automatic thread creation/destruction
- **Task submission** - submit Runnable and Callable tasks
- **Lifecycle control** - shutdown and termination management
- **Result handling** - Future objects for async results

#### **Basic Usage:**
```java
import java.util.concurrent.*;

public class ExecutorServiceExample {
    public void demonstrateBasicUsage() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit Runnable tasks
        executor.submit(() -> {
            System.out.println("Task 1 executed by: " + Thread.currentThread().getName());
        });
        
        // Submit Callable tasks (return results)
        Future<String> future = executor.submit(() -> {
            Thread.sleep(1000);
            return "Task completed";
        });
        
        try {
            String result = future.get();  // Blocking call
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        // Proper shutdown
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
```

#### **ExecutorService Methods:**
```java
public class ExecutorServiceMethods {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void demonstrateMethods() {
        // Submit tasks
        Future<?> future1 = executor.submit(runnableTask);
        Future<String> future2 = executor.submit(callableTask);
        
        // Submit multiple tasks
        List<Callable<String>> tasks = Arrays.asList(
            () -> "Task 1",
            () -> "Task 2",
            () -> "Task 3"
        );
        
        try {
            // Execute all tasks and wait for completion
            List<Future<String>> futures = executor.invokeAll(tasks);
            
            // Execute tasks and return first completed result
            String result = executor.invokeAny(tasks);
            
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    Runnable runnableTask = () -> System.out.println("Runnable task");
    Callable<String> callableTask = () -> "Callable result";
}
```

#### **Lifecycle Management:**
```java
public class ExecutorLifecycle {
    public void demonstrateLifecycle() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit tasks
        executor.submit(() -> doWork());
        
        // Graceful shutdown
        executor.shutdown();  // No new tasks accepted
        
        try {
            // Wait for existing tasks to complete
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                // Force shutdown if tasks don't complete in time
                executor.shutdownNow();
                
                // Wait for forced shutdown
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    private void doWork() {
        // Simulate work
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
}
```

### 118. What is ThreadPoolExecutor?

**ThreadPoolExecutor** is the main implementation of ExecutorService that manages a pool of worker threads with configurable parameters.

#### **Key Parameters:**
```java
public ThreadPoolExecutor(
    int corePoolSize,           // Minimum number of threads
    int maximumPoolSize,        // Maximum number of threads
    long keepAliveTime,         // Thread idle timeout
    TimeUnit unit,              // Time unit for keepAliveTime
    BlockingQueue<Runnable> workQueue,  // Task queue
    ThreadFactory threadFactory,         // Thread creation factory
    RejectedExecutionHandler handler     // Rejection policy
)
```

#### **Configuration Example:**
```java
import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public void createCustomThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,                              // corePoolSize
            4,                              // maximumPoolSize
            60L,                            // keepAliveTime
            TimeUnit.SECONDS,               // unit
            new LinkedBlockingQueue<>(100), // workQueue
            new ThreadFactory() {           // custom thread factory
                private int counter = 0;
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "CustomThread-" + counter++);
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()  // rejection policy
        );
        
        // Submit tasks
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by: " + 
                                 Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

#### **Thread Pool Behavior:**
```java
public class ThreadPoolBehavior {
    public void demonstrateBehavior() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2)
        );
        
        // Task submission behavior:
        // 1. If threads < corePoolSize: create new thread
        // 2. If threads = corePoolSize: add to queue
        // 3. If queue full and threads < maxPoolSize: create new thread
        // 4. If threads = maxPoolSize and queue full: reject task
        
        // Monitor pool status
        System.out.println("Core pool size: " + executor.getCorePoolSize());
        System.out.println("Max pool size: " + executor.getMaximumPoolSize());
        System.out.println("Active threads: " + executor.getActiveCount());
        System.out.println("Queue size: " + executor.getQueue().size());
    }
}
```

#### **Rejection Policies:**
```java
public class RejectionPolicies {
    public void demonstratePolicies() {
        // 1. AbortPolicy (default) - throws RejectedExecutionException
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(1, 1, 0L, 
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.AbortPolicy());
        
        // 2. CallerRunsPolicy - runs task in caller thread
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.CallerRunsPolicy());
        
        // 3. DiscardPolicy - silently discards task
        ThreadPoolExecutor executor3 = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.DiscardPolicy());
        
        // 4. DiscardOldestPolicy - discards oldest task in queue
        ThreadPoolExecutor executor4 = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
```

### 119. What are the types of thread pools?

Java provides several **pre-configured thread pool types** through the Executors factory class.

#### **1. FixedThreadPool:**
```java
public class FixedThreadPoolExample {
    public void demonstrateFixedThreadPool() {
        // Creates pool with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Equivalent to:
        // new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
        //                       new LinkedBlockingQueue<Runnable>())
        
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

**Use Case:** Known workload with predictable number of concurrent tasks.

#### **2. CachedThreadPool:**
```java
public class CachedThreadPoolExample {
    public void demonstrateCachedThreadPool() {
        // Creates threads as needed, reuses idle threads
        ExecutorService executor = Executors.newCachedThreadPool();
        
        // Equivalent to:
        // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
        //                       new SynchronousQueue<Runnable>())
        
        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

**Use Case:** Many short-lived asynchronous tasks.

#### **3. SingleThreadExecutor:**
```java
public class SingleThreadExecutorExample {
    public void demonstrateSingleThreadExecutor() {
        // Single worker thread, tasks executed sequentially
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
    }
}
```

**Use Case:** Tasks must be executed sequentially, thread-safe execution.

#### **4. ScheduledThreadPool:**
```java
public class ScheduledThreadPoolExample {
    public void demonstrateScheduledThreadPool() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        
        // Schedule one-time task with delay
        scheduler.schedule(() -> {
            System.out.println("Delayed task executed");
        }, 5, TimeUnit.SECONDS);
        
        // Schedule recurring task with fixed rate
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task: " + System.currentTimeMillis());
        }, 0, 2, TimeUnit.SECONDS);
        
        // Schedule recurring task with fixed delay
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Fixed delay task");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }, 0, 3, TimeUnit.SECONDS);
        
        // Shutdown after 20 seconds
        scheduler.schedule(() -> scheduler.shutdown(), 20, TimeUnit.SECONDS);
    }
}
```

**Use Case:** Delayed execution, periodic tasks, scheduling.

#### **Comparison Table:**

| Pool Type | Core Threads | Max Threads | Queue | Use Case |
|-----------|--------------|-------------|-------|----------|
| **Fixed** | N | N | Unbounded | Predictable load |
| **Cached** | 0 | Unlimited | Direct handoff | Variable load |
| **Single** | 1 | 1 | Unbounded | Sequential execution |
| **Scheduled** | N | N | Delayed queue | Timed tasks |

### 120. What is Future and CompletableFuture?

#### **Future - Basic Asynchronous Result:**
```java
import java.util.concurrent.*;

public class FutureExample {
    public void demonstrateFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit Callable task
        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);  // Simulate long-running task
            return "Task completed";
        });
        
        try {
            // Check if task is done (non-blocking)
            System.out.println("Is done: " + future.isDone());
            
            // Get result (blocking)
            String result = future.get();  // Waits until completion
            System.out.println("Result: " + result);
            
            // Get result with timeout
            String result2 = future.get(3, TimeUnit.SECONDS);
            
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
    }
}
```

#### **CompletableFuture - Advanced Asynchronous Programming:**
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public void demonstrateCompletableFuture() {
        // Create CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            return "Hello";
        });
        
        // Chain operations
        CompletableFuture<String> result = future
            .thenApply(s -> s + " World")           // Transform result
            .thenApply(String::toUpperCase)         // Another transformation
            .thenCompose(s -> CompletableFuture     // Compose with another async operation
                .supplyAsync(() -> s + "!"));
        
        // Handle completion
        result.thenAccept(System.out::println);     // Consume result
        
        // Handle exceptions
        result.exceptionally(throwable -> {
            System.err.println("Error: " + throwable.getMessage());
            return "Default value";
        });
        
        // Wait for completion
        result.join();  // Similar to get() but unchecked exceptions
    }
}
```

#### **CompletableFuture Combining Operations:**
```java
public class CompletableFutureCombining {
    public void demonstrateCombining() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        
        // Combine two futures
        CompletableFuture<String> combined = future1.thenCombine(future2, 
            (s1, s2) -> s1 + " " + s2);
        
        // Wait for either future to complete
        CompletableFuture<Object> either = CompletableFuture.anyOf(future1, future2);
        
        // Wait for all futures to complete
        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2);
        
        // Execute after both complete
        all.thenRun(() -> System.out.println("Both completed"));
        
        System.out.println(combined.join());  // "Hello World"
    }
}
```

#### **Key Differences:**

| Feature | Future | CompletableFuture |
|---------|--------|-------------------|
| **Chaining** | No | Yes |
| **Combining** | No | Yes |
| **Exception Handling** | Basic | Advanced |
| **Completion Callbacks** | No | Yes |
| **Manual Completion** | No | Yes |

### 121. What is CountDownLatch?

**CountDownLatch** is a synchronization aid that allows threads to wait until a set of operations complete.

#### **Basic Usage:**
```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public void demonstrateCountDownLatch() throws InterruptedException {
        int numberOfTasks = 3;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);
        
        // Start worker threads
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            new Thread(() -> {
                try {
                    System.out.println("Task " + taskId + " starting");
                    Thread.sleep(2000);  // Simulate work
                    System.out.println("Task " + taskId + " completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();  // Decrement counter
                }
            }).start();
        }
        
        // Wait for all tasks to complete
        System.out.println("Waiting for all tasks to complete...");
        latch.await();  // Blocks until counter reaches 0
        System.out.println("All tasks completed!");
    }
}
```

#### **Real-world Example - Service Startup:**
```java
public class ServiceStartup {
    private final CountDownLatch startupLatch = new CountDownLatch(3);
    
    public void startServices() {
        // Start database service
        new Thread(() -> {
            initializeDatabase();
            startupLatch.countDown();
        }).start();
        
        // Start cache service
        new Thread(() -> {
            initializeCache();
            startupLatch.countDown();
        }).start();
        
        // Start web service
        new Thread(() -> {
            initializeWebServer();
            startupLatch.countDown();
        }).start();
        
        try {
            // Wait for all services to start
            startupLatch.await(30, TimeUnit.SECONDS);
            System.out.println("All services started successfully");
        } catch (InterruptedException e) {
            System.err.println("Service startup interrupted");
            Thread.currentThread().interrupt();
        }
    }
    
    private void initializeDatabase() { /* Database init */ }
    private void initializeCache() { /* Cache init */ }
    private void initializeWebServer() { /* Web server init */ }
}
```

#### **Key Characteristics:**
- **One-time use** - cannot be reset
- **Thread-safe** - multiple threads can call countDown()
- **Blocking** - await() blocks until counter reaches zero
- **Timeout support** - await() with timeout

### 122. What is CyclicBarrier?

**CyclicBarrier** synchronizes threads at a common barrier point where all threads must arrive before any can proceed.

#### **Basic Usage:**
```java
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public void demonstrateCyclicBarrier() {
        int numberOfThreads = 3;
        
        // Create barrier with optional barrier action
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> {
            System.out.println("All threads reached barrier - proceeding to next phase");
        });
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    // Phase 1
                    System.out.println("Thread " + threadId + " - Phase 1");
                    Thread.sleep(1000 + threadId * 500);  // Different work times
                    
                    barrier.await();  // Wait for all threads
                    
                    // Phase 2
                    System.out.println("Thread " + threadId + " - Phase 2");
                    Thread.sleep(1000);
                    
                    barrier.await();  // Wait again (barrier is reusable)
                    
                    // Phase 3
                    System.out.println("Thread " + threadId + " - Phase 3");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

#### **Parallel Algorithm Example:**
```java
public class ParallelMatrixMultiplication {
    private final CyclicBarrier barrier;
    private final int[][] matrixA, matrixB, result;
    private final int numberOfThreads;
    
    public ParallelMatrixMultiplication(int[][] a, int[][] b, int threads) {
        this.matrixA = a;
        this.matrixB = b;
        this.result = new int[a.length][b[0].length];
        this.numberOfThreads = threads;
        this.barrier = new CyclicBarrier(threads, () -> {
            System.out.println("Phase completed by all threads");
        });
    }
    
    public void multiply() {
        int rowsPerThread = matrixA.length / numberOfThreads;
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int startRow = i * rowsPerThread;
            final int endRow = (i == numberOfThreads - 1) ? 
                matrixA.length : (i + 1) * rowsPerThread;
            
            new Thread(() -> {
                try {
                    // Each thread processes its assigned rows
                    for (int row = startRow; row < endRow; row++) {
                        for (int col = 0; col < matrixB[0].length; col++) {
                            for (int k = 0; k < matrixB.length; k++) {
                                result[row][col] += matrixA[row][k] * matrixB[k][col];
                            }
                        }
                    }
                    
                    // Wait for all threads to complete their portion
                    barrier.await();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

#### **CyclicBarrier vs CountDownLatch:**

| Feature | CyclicBarrier | CountDownLatch |
|---------|---------------|----------------|
| **Reusability** | Reusable | One-time use |
| **Waiting Threads** | All parties wait | Only some threads wait |
| **Reset** | Automatic after barrier | Cannot reset |
| **Barrier Action** | Supported | Not supported |

### 123. What is Semaphore?

**Semaphore** controls access to shared resources by maintaining a set of permits.

#### **Basic Usage:**
```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private final Semaphore semaphore = new Semaphore(3);  // 3 permits
    
    public void accessResource() {
        try {
            semaphore.acquire();  // Acquire permit
            System.out.println(Thread.currentThread().getName() + " acquired permit");
            
            // Use resource
            Thread.sleep(2000);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released permit");
            semaphore.release();  // Release permit
        }
    }
    
    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();
        
        // Create 10 threads trying to access resource
        for (int i = 0; i < 10; i++) {
            new Thread(example::accessResource, "Thread-" + i).start();
        }
    }
}
```

#### **Connection Pool Example:**
```java
public class ConnectionPool {
    private final Semaphore semaphore;
    private final Queue<Connection> connections;
    
    public ConnectionPool(int maxConnections) {
        this.semaphore = new Semaphore(maxConnections);
        this.connections = new ConcurrentLinkedQueue<>();
        
        // Initialize connections
        for (int i = 0; i < maxConnections; i++) {
            connections.offer(new Connection("Connection-" + i));
        }
    }
    
    public Connection getConnection() throws InterruptedException {
        semaphore.acquire();  // Wait for available permit
        return connections.poll();
    }
    
    public void releaseConnection(Connection connection) {
        connections.offer(connection);
        semaphore.release();  // Return permit
    }
    
    static class Connection {
        private final String name;
        Connection(String name) { this.name = name; }
        @Override
        public String toString() { return name; }
    }
}
```

#### **Semaphore Features:**
```java
public class SemaphoreFeatures {
    private final Semaphore semaphore = new Semaphore(2, true);  // Fair semaphore
    
    public void demonstrateFeatures() {
        try {
            // Try to acquire without blocking
            if (semaphore.tryAcquire()) {
                System.out.println("Acquired permit immediately");
                semaphore.release();
            }
            
            // Try to acquire with timeout
            if (semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
                System.out.println("Acquired permit within timeout");
                semaphore.release();
            }
            
            // Acquire multiple permits
            semaphore.acquire(2);
            System.out.println("Acquired 2 permits");
            semaphore.release(2);
            
            // Check available permits
            System.out.println("Available permits: " + semaphore.availablePermits());
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

### 124. What is ReentrantLock?

**ReentrantLock** is an explicit lock that provides the same functionality as synchronized with additional features.

#### **Basic Usage:**
```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println("Counter: " + counter);
        } finally {
            lock.unlock();  // Always unlock in finally block
        }
    }
    
    public void demonstrateReentrant() {
        lock.lock();
        try {
            System.out.println("First lock acquired");
            
            // Same thread can acquire lock again (reentrant)
            lock.lock();
            try {
                System.out.println("Second lock acquired");
            } finally {
                lock.unlock();  // Must unlock same number of times
            }
            
        } finally {
            lock.unlock();
        }
    }
}
```

#### **Advanced Features:**
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockAdvanced {
    private final ReentrantLock lock = new ReentrantLock(true);  // Fair lock
    
    public void tryLockExample() {
        // Non-blocking lock attempt
        if (lock.tryLock()) {
            try {
                System.out.println("Lock acquired immediately");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock");
        }
    }
    
    public void tryLockWithTimeout() {
        try {
            // Try to acquire lock with timeout
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println("Lock acquired within timeout");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Timeout waiting for lock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void interruptibleLock() {
        try {
            // Lock that can be interrupted
            lock.lockInterruptibly();
            try {
                // Critical section
                Thread.sleep(5000);
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("Lock acquisition interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
```

#### **Condition Variables:**
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private final Object[] buffer = new Object[10];
    private int count = 0, putIndex = 0, takeIndex = 0;
    
    public void put(Object item) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                notFull.await();  // Wait until buffer not full
            }
            
            buffer[putIndex] = item;
            putIndex = (putIndex + 1) % buffer.length;
            count++;
            
            notEmpty.signal();  // Signal that buffer is not empty
        } finally {
            lock.unlock();
        }
    }
    
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();  // Wait until buffer not empty
            }
            
            Object item = buffer[takeIndex];
            buffer[takeIndex] = null;
            takeIndex = (takeIndex + 1) % buffer.length;
            count--;
            
            notFull.signal();  // Signal that buffer is not full
            return item;
        } finally {
            lock.unlock();
        }
    }
}
```

### 125. What is the difference between ReentrantLock and synchronized?

| Feature | ReentrantLock | synchronized |
|---------|---------------|--------------|
| **Lock Acquisition** | Explicit lock()/unlock() | Automatic |
| **Try Lock** | tryLock() with timeout | Not available |
| **Interruptible** | lockInterruptibly() | Not interruptible |
| **Fairness** | Fair/unfair options | Unfair only |
| **Condition Variables** | Multiple conditions | Single wait/notify |
| **Performance** | Slightly better in high contention | Good for low contention |
| **Error Handling** | Manual unlock in finally | Automatic unlock |

#### **Synchronized Example:**
```java
public class SynchronizedExample {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;  // Automatic lock acquisition/release
    }
    
    public void synchronizedBlock() {
        synchronized (this) {
            counter++;  // Automatic lock management
        }
    }
    
    // Wait/notify with synchronized
    private boolean condition = false;
    
    public synchronized void waitForCondition() throws InterruptedException {
        while (!condition) {
            wait();  // Single condition variable
        }
    }
    
    public synchronized void signalCondition() {
        condition = true;
        notifyAll();  // Wake all waiting threads
    }
}
```

#### **ReentrantLock Example:**
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock(true);  // Fair lock
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            counter++;  // Manual lock management
        } finally {
            lock.unlock();  // Must unlock manually
        }
    }
    
    public void tryIncrement() {
        if (lock.tryLock()) {  // Non-blocking attempt
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock");
        }
    }
    
    // Multiple condition variables
    public void waitForCondition1() throws InterruptedException {
        lock.lock();
        try {
            condition1.await();  // Wait on specific condition
        } finally {
            lock.unlock();
        }
    }
    
    public void signalCondition1() {
        lock.lock();
        try {
            condition1.signal();  // Signal specific condition
        } finally {
            lock.unlock();
        }
    }
}
```

#### **When to Use Each:**

**Use synchronized when:**
- Simple locking requirements
- Automatic lock management preferred
- Lower complexity needed
- Built-in wait/notify is sufficient

**Use ReentrantLock when:**
- Need tryLock() functionality
- Require interruptible locking
- Need fair locking
- Multiple condition variables required
- Advanced lock features needed

# 🔹 Memory Management and Garbage Collection

## Memory Areas in JVM

### 126. What are the different memory areas in JVM?

The JVM divides memory into several distinct areas:

- **Heap Memory**: Stores all Java objects and instance variables
- **Stack Memory**: Contains method call frames and local variables (per thread)
- **Method Area**: Stores class metadata, constant pool, and static variables
- **PC (Program Counter) Register**: Holds the address of currently executing instruction
- **Native Method Stack**: Used for native method calls

### 127. What is heap memory?

Heap memory is the runtime data area where Java objects are allocated. Key characteristics:

- Shared among all threads
- Divided into Young Generation and Old Generation
- Managed by Garbage Collector
- Objects created with `new` keyword are stored here
- Instance variables of objects reside here

### 128. What is stack memory?

Stack memory stores method-specific data:

- Each thread has its own stack
- Contains method call frames
- Stores local variables and partial results
- Follows LIFO (Last In, First Out) principle
- Automatically managed (no garbage collection needed)
- Faster access compared to heap

### 129. What is method area?

Method area is a shared memory region that contains:

- Class-level metadata and structure
- Runtime constant pool
- Static variables and methods
- Method bytecode
- Shared among all threads
- Part of non-heap memory

### 130. What is the difference between heap and stack?

| Aspect | Heap | Stack |
|--------|------|-------|
| **Storage** | Objects and instance variables | Method calls and local variables |
| **Sharing** | Shared among all threads | Per thread |
| **Management** | Garbage collected | Automatically managed |
| **Speed** | Slower access | Faster access |
| **Size** | Larger | Smaller |
| **Memory Issues** | OutOfMemoryError | StackOverflowError |

### 131. What is permgen space?

PermGen (Permanent Generation) was used in Java versions before Java 8:

- Part of heap memory with fixed size
- Stored class metadata and interned strings
- Static variables and method bytecode
- Could cause `OutOfMemoryError: PermGen space`
- Replaced by Metaspace in Java 8

### 132. What is metaspace?

Metaspace is the replacement for PermGen introduced in Java 8:

- Stores class metadata in native memory (not heap)
- Dynamic sizing based on application needs
- Limited by available system memory
- Better memory management
- Eliminates PermGen space issues

### 133. What is the difference between permgen and metaspace?

| Feature | PermGen | Metaspace |
|---------|---------|-----------|
| **Location** | Heap memory | Native memory |
| **Size** | Fixed size | Dynamic sizing |
| **Memory Limit** | JVM heap limit | System memory limit |
| **Common Issues** | OutOfMemoryError: PermGen | Rare memory issues |
| **Java Version** | Before Java 8 | Java 8 and later |

### 134. What is direct memory?

Direct memory is off-heap memory allocated directly from system RAM:

- Bypasses JVM heap allocation
- Used by NIO operations (ByteBuffer.allocateDirect())
- Not subject to garbage collection
- Faster I/O operations
- Limited by available system memory
- Must be explicitly freed

### 135. What is off-heap memory?

Off-heap memory refers to memory outside the JVM heap:

- Stored directly in system RAM
- Not managed by garbage collector
- Provides faster access for large datasets
- Used for caching and NIO operations
- Reduces GC pressure
- Examples: Direct ByteBuffers, memory-mapped files

## Key Takeaways

1. **Heap vs Stack**: Heap for objects (shared), Stack for method calls (per thread)
2. **PermGen → Metaspace**: Java 8 moved class metadata from heap to native memory
3. **Direct Memory**: Off-heap allocation for performance-critical operations
4. **Memory Management**: Understanding these areas helps in performance tuning and troubleshooting

## Best Practices

- Monitor heap usage to prevent OutOfMemoryError
- Tune stack size for deep recursion scenarios
- Use direct memory judiciously for I/O operations
- Configure metaspace size for applications with many classes
- Profile memory usage to identify bottlenecks

