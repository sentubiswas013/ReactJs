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
