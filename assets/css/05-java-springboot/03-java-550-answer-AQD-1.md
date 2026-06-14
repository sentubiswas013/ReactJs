# **1. Core Java Fundamentals**

### **1. Java Features**

**Java** is a **high-level, object-oriented, platform-independent** programming language.

**Key Features:**

* **Platform Independent** – Write Once, Run Anywhere (**WORA**).
* **Object-Oriented** – Uses **classes** and **objects**.
* **Simple** – Easy syntax, similar to C/C++.
* **Secure** – No direct memory access and has built-in security features.
* **Robust** – Strong **exception handling** and **garbage collection**.
* **Multithreaded** – Can run multiple tasks at the same time.
* **Portable** – Java code runs on any system with a **JVM**.

**When to Use:**

* Building **web applications**, **enterprise software**, **Android apps**, and **microservices**.

**Example:**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

### **2. JVM, JRE, JDK**

These are the three main components of the Java environment.

| Component | Full Form                | Purpose                                                   |
| --------- | ------------------------ | --------------------------------------------------------- |
| **JVM**   | Java Virtual Machine     | Runs Java **bytecode**.                                   |
| **JRE**   | Java Runtime Environment | Contains **JVM + libraries** needed to run Java programs. |
| **JDK**   | Java Development Kit     | Contains **JRE + development tools** like `javac`.        |

**How it Works:**

* Write code using **JDK**.
* Compile it into **bytecode**.
* **JRE** provides the environment to run it.
* **JVM** executes the bytecode.

**When to Use:**

* **JDK** → To **develop** Java applications.
* **JRE** → To **run** Java applications.
* **JVM** → Automatically used to execute Java bytecode.

### **3. Java Compilation Process**

The Java compilation process converts source code into executable form.

**How it Works:**

1. Write code in a **`.java`** file.
2. The **`javac`** compiler converts it into **bytecode (`.class`)**.
3. The **JVM** loads the bytecode.
4. The **JIT (Just-In-Time) Compiler** converts bytecode into **machine code** for execution.

**Flow:**
`Source Code (.java) → javac → Bytecode (.class) → JVM → Machine Code → Output`

**Key Features:**

* **Platform Independent** because bytecode runs on any JVM.
* **JIT Compiler** improves execution speed.

**Example:**

```bash
javac Main.java
java Main
```

### **4. Class & Object**

A **Class** is a **blueprint** for creating objects, and an **Object** is a real instance of that class.

**Key Features:**

* **Class** defines **properties (variables)** and **behaviors (methods)**.
* **Object** stores actual data and can call methods.

**How it Works:**

* First, create a **class**.
* Then create an **object** using the `new` keyword.

**When to Use:**

* Use **classes and objects** to implement **Object-Oriented Programming (OOP)** and organize code.

**Example:**

```java
class Car {
    String brand = "Toyota";

    void show() {
        System.out.println(brand);
    }
}

public class Main {
    public static void main(String[] args) {
        Car c = new Car();   // Object creation
        c.show();
    }
}
```

### **5. Constructor**

A **Constructor** is a special method that is called **automatically when an object is created**. It is mainly used to **initialize object values**.

**Key Features:**

* Has the **same name as the class**.
* **No return type**, not even `void`.
* Called automatically when using the `new` keyword.
* Can be **default** or **parameterized**.

**How it Works:**

* When an object is created, the constructor initializes its data members.

**When to Use:**

* To set **default values** or initialize object data during object creation.

**Example:**

```java
class Student {
    String name;

    Student(String n) {   // Constructor
        name = n;
    }

    void display() {
        System.out.println(name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student("John");
        s.display();
    }
}
```

### **6. Method Overloading**

**Method Overloading** means having **multiple methods with the same name** but **different parameters** in the same class.

**Key Features:**

* Same **method name**.
* Different **number**, **type**, or **order** of parameters.
* Achieves **compile-time polymorphism**.

**How it Works:**

* The compiler chooses the correct method based on the arguments passed.

**When to Use:**

* When the same operation needs to work with different types or numbers of inputs.

**Example:**

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

### **7. Method Overriding**

**Method Overriding** means a **child class provides its own implementation** of a method already defined in the parent class.

**Key Features:**

* Same **method name**, **parameters**, and **return type**.
* Requires **inheritance**.
* Achieves **runtime polymorphism**.

**How it Works:**

* The JVM calls the overridden method based on the **actual object type**.

**When to Use:**

* When a child class needs to provide a **specific implementation** of a parent class method.

**Example:**

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

### **8. Static Keyword**

The **`static`** keyword makes a variable or method belong to the **class** instead of individual objects.

**Key Features:**

* Shared by **all objects**.
* Can access **static members** without creating an object.
* Static methods cannot directly access **non-static** members.

**How it Works:**

* Memory for static members is allocated only **once** when the class is loaded.

**When to Use:**

* For **common data** or **utility methods** shared across all objects.

**Example:**

```java
class Student {
    static String college = "ABC College";

    static void show() {
        System.out.println(college);
    }
}

public class Main {
    public static void main(String[] args) {
        Student.show();
    }
}
```

### **9. Final Keyword**

The **`final`** keyword is used to **restrict modification**.

**Key Features:**

* **Final variable** → Value cannot be changed.
* **Final method** → Cannot be overridden.
* **Final class** → Cannot be inherited.

**How it Works:**

* Once declared as `final`, Java prevents further modification or extension.

**When to Use:**

* For **constants**, **security**, and to prevent unwanted inheritance or overriding.

**Example:**

```java
final class Demo {
    final int x = 10;

    final void display() {
        System.out.println(x);
    }
}
```

### **10. this & super**

**`this`** and **`super`** are special keywords used to refer to the **current object** and the **parent class object**.

**Key Features:**

* **`this`** refers to the current class instance.
* **`super`** refers to the immediate parent class.
* Both can be used to call **constructors** and access **variables/methods**.

**How it Works:**

* `this()` calls another constructor in the same class.
* `super()` calls the parent class constructor.

**When to Use:**

* Use **`this`** to avoid variable name conflicts or call current class constructors.
* Use **`super`** to access parent class members or constructors.

**Example:**

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";

    void display() {
        System.out.println(this.name);   // Child
        System.out.println(super.name);  // Parent
    }
}
```

### **11. Access Modifiers**

**Access Modifiers** control the **visibility** and **access level** of classes, methods, and variables.

**Key Features:**

* **public** → Accessible from anywhere.
* **protected** → Accessible within the same package and by subclasses.
* **default** (no keyword) → Accessible only within the same package.
* **private** → Accessible only within the same class.

**How it Works:**

* Java checks the access modifier before allowing another class to use a member.

**When to Use:**

* To provide **data security** and **encapsulation**.

| Modifier      | Same Class | Same Package | Subclass | Anywhere |
| ------------- | :--------: | :----------: | :------: | :------: |
| **public**    |      ✔     |       ✔      |     ✔    |     ✔    |
| **protected** |      ✔     |       ✔      |     ✔    |     ✘    |
| **default**   |      ✔     |       ✔      |     ✘    |     ✘    |
| **private**   |      ✔     |       ✘      |     ✘    |     ✘    |

**Example:**

```java
class Demo {
    public int a = 10;
    private int b = 20;
}
```

### **12. Wrapper Classes**

**Wrapper Classes** convert **primitive data types** into **objects**.

**Key Features:**

* Each primitive type has a corresponding wrapper class.
* Required for working with **Collections Framework** and **Generics**.
* Provides useful utility methods.

| Primitive | Wrapper Class |
| --------- | ------------- |
| `int`     | `Integer`     |
| `double`  | `Double`      |
| `char`    | `Character`   |
| `boolean` | `Boolean`     |

**How it Works:**

* Java wraps primitive values inside objects.

**When to Use:**

* When an API or collection requires **objects instead of primitives**.

**Example:**

```java
Integer num = Integer.valueOf(100);
System.out.println(num);
```

### **13. Autoboxing & Unboxing**

**Autoboxing** is the automatic conversion of a **primitive** to a **wrapper object**, and **Unboxing** is the reverse process.

**Key Features:**

* Done automatically by the Java compiler.
* Reduces manual conversion code.
* Commonly used with **Collections**.

**How it Works:**

* `int` → `Integer` (**Autoboxing**).
* `Integer` → `int` (**Unboxing**).

**When to Use:**

* While working with **ArrayList**, **HashMap**, and other collection classes.

**Example:**

```java
int a = 10;
Integer obj = a;     // Autoboxing

int b = obj;         // Unboxing
```

### **14. String, StringBuilder, StringBuffer**

These classes are used to work with character data, but they differ in **mutability** and **thread safety**.

**Key Features:**

* **String** → **Immutable**, every modification creates a new object.
* **StringBuilder** → **Mutable** and **not thread-safe**, faster.
* **StringBuffer** → **Mutable** and **thread-safe**, slower due to synchronization.

**When to Use:**

* **String** → Read-only text.
* **StringBuilder** → Frequent string modifications in a single thread.
* **StringBuffer** → Frequent string modifications in a multi-threaded environment.

| Feature     | String | StringBuilder | StringBuffer |
| ----------- | ------ | ------------- | ------------ |
| Mutable     | ✘      | ✔             | ✔            |
| Thread Safe | ✔      | ✘             | ✔            |
| Performance | Slow   | Fast          | Moderate     |

**Example:**

```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" Programming");
System.out.println(sb);
```

### **15. Immutable Objects**

An **Immutable Object** is an object whose state **cannot be changed after creation**.

**Key Features:**

* Object data cannot be modified.
* Class is usually declared as `final`.
* Variables are `private` and initialized through a constructor.
* No setter methods.

**How it Works:**

* Any change creates a **new object** instead of modifying the existing one.

**When to Use:**

* For **security**, **thread safety**, and creating **constant objects** like `String`.

**Example:**

```java
final class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### **16. Object Class Methods**

The **`Object`** class is the **parent class of all Java classes**. Every class automatically inherits its methods.

**Key Features:**

* **`toString()`** → Returns object information as a string.
* **`equals()`** → Compares two objects.
* **`hashCode()`** → Returns a hash value for the object.
* **`getClass()`** → Returns the class information.
* **`clone()`** → Creates a copy of the object.
* **`wait()`, `notify()`, `notifyAll()`** → Used for thread communication.

**How it Works:**

* Every Java object can directly use or override these methods.

**When to Use:**

* To customize **object comparison**, **printing**, **hashing**, and **thread coordination**.

**Example:**

```java
class Student {
    String name = "John";

    public String toString() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        System.out.println(s.toString());
    }
}
```



# **2. OOPs Concepts**

### **1. Encapsulation**

**Encapsulation** is the process of **wrapping data (variables) and methods into a single unit (class)** and restricting direct access to the data.

**Key Features:**

* Achieves **data hiding**.
* Uses **private** variables and **public getter/setter methods**.
* Improves **security** and **maintainability**.

**How it Works:**

* Class variables are declared as `private`.
* Access and modification are done through `get()` and `set()` methods.

**When to Use:**

* When you want to **protect object data** and control how it is accessed or updated.

**Example:**

```java id="stg4eo"
class Student {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### **2. Inheritance**

**Inheritance** allows one class to **acquire the properties and methods of another class**.

**Key Features:**

* Promotes **code reusability**.
* Creates a **parent-child** relationship.
* Uses the **`extends`** keyword.
* Supports **method overriding**.

**How it Works:**

* A child class inherits members from the parent class and can also add its own features.

**When to Use:**

* When multiple classes share common functionality and you want to avoid code duplication.

**Example:**

```java id="r3x4tm"
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}
```

### **3. Polymorphism**

**Polymorphism** means **"many forms"**. It allows the same method or object to behave differently in different situations.

**Key Features:**

* Improves **flexibility** and **reusability**.
* Supports **Method Overloading** (**compile-time polymorphism**).
* Supports **Method Overriding** (**runtime polymorphism**).

**How it Works:**

* The same method name can perform different actions based on parameters or object type.

**When to Use:**

* When different classes need to provide their own implementation of the same behavior.

**Example:**

```java id="r2qgk7"
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

### **4. Abstraction**

**Abstraction** is the process of **hiding implementation details and showing only the essential functionality**.

**Key Features:**

* Focuses on **what an object does**, not how it does it.
* Achieved using **abstract classes** and **interfaces**.
* Improves **security** and **code maintainability**.

**How it Works:**

* An **abstract class** can have both abstract and normal methods.
* A child class provides the implementation for abstract methods.

**When to Use:**

* When you want to define a common structure but allow subclasses to implement their own behavior.

**Example:**

```java id="8nq8es"
abstract class Animal {
    abstract void sound();
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

### **5. Association**

**Association** is a relationship where **two independent classes are connected and can use each other**.

**Key Features:**

* Represents a **"uses-a"** relationship.
* Both objects have **independent life cycles**.
* Can be **one-to-one**, **one-to-many**, or **many-to-many**.

**How it Works:**

* One object holds a reference to another object and interacts with it.

**When to Use:**

* When two classes need to communicate but do not depend on each other's existence.

**Example:**

```java id="t1s9j4"
class Teacher {
    String name = "John";
}

class Student {
    Teacher teacher = new Teacher();
}
```

### **6. Aggregation**

**Aggregation** is a special type of **Association** where one class **contains another class**, but both can exist independently.

**Key Features:**

* Represents a **weak HAS-A** relationship.
* Child object can exist without the parent.
* Promotes **code reuse** and flexibility.

**How it Works:**

* The container class stores a reference to an external object.

**When to Use:**

* When objects are related but have **independent life cycles**.

**Example:**

```java id="d7v8qn"
class Department {
    String name = "IT";
}

class Employee {
    Department dept;

    Employee(Department dept) {
        this.dept = dept;
    }
}
```

### **7. Composition**

**Composition** is a strong form of **Aggregation** where one object **completely owns another object**.

**Key Features:**

* Represents a **strong HAS-A** relationship.
* Child object **cannot exist** without the parent.
* Provides strong ownership and dependency.

**How it Works:**

* The parent class creates and manages the child object internally.

**When to Use:**

* When the lifetime of one object depends entirely on another.

**Example:**

```java id="k9n5xe"
class Engine {
    Engine() {
        System.out.println("Engine Created");
    }
}

class Car {
    private Engine engine = new Engine();
}
```

### **8. IS-A vs HAS-A Relationship**

**IS-A** and **HAS-A** are two important relationships in **Object-Oriented Programming (OOP)**.

**Key Features:**

* **IS-A** represents **Inheritance** (`extends`).
* **HAS-A** represents **Association, Aggregation, or Composition**.
* **IS-A** means one object is a specialized form of another.
* **HAS-A** means one object contains or uses another object.

**How it Works:**

* **IS-A:** Child class inherits from the parent class.
* **HAS-A:** A class creates or stores a reference to another class object.

**When to Use:**

* Use **IS-A** when there is a natural inheritance relationship.
* Use **HAS-A** when one class needs the functionality of another through object reference.

| Relationship | Meaning                             | Example            |
| ------------ | ----------------------------------- | ------------------ |
| **IS-A**     | Inheritance                         | `Dog IS-A Animal`  |
| **HAS-A**    | Association/Aggregation/Composition | `Car HAS-A Engine` |

**Example:**

```java id="u8p3mf"
class Animal { }

class Dog extends Animal { }   // IS-A

class Engine { }

class Car {
    Engine engine = new Engine();   // HAS-A
}
```



# **3. Collections Framework**

### **1. List**

**List** is an interface in the **Java Collections Framework** that stores elements in an **ordered sequence**.

**Key Features:**

* Maintains **insertion order**.
* Allows **duplicate elements**.
* Supports **index-based access**.

**How it Works:**

* Elements are stored one after another and can be accessed using their index.

**When to Use:**

* When you need an **ordered collection** with duplicate values.

**Example:**

```java
import java.util.*;

List<String> list = new ArrayList<>();
list.add("Java");
list.add("Python");
list.add("Java");
System.out.println(list);
```

### **2. Set**

**Set** is an interface that stores **unique elements**.

**Key Features:**

* Does **not allow duplicates**.
* Does not support index-based access.
* Different implementations provide different ordering.

**How it Works:**

* Before adding an element, Java checks if it already exists in the set.

**When to Use:**

* When you need to store **unique values** only.

**Example:**

```java
import java.util.*;

Set<Integer> set = new HashSet<>();
set.add(10);
set.add(20);
set.add(10);
System.out.println(set);
```

### **3. Queue**

**Queue** is an interface that stores elements in **FIFO (First In, First Out)** order.

**Key Features:**

* Follows the **FIFO** principle.
* Supports insertion using `offer()` and removal using `poll()`.
* Common implementation is **LinkedList**.

**How it Works:**

* The first element added is the first element removed.

**When to Use:**

* For **task scheduling**, **message processing**, and **job queues**.

**Example:**

```java
import java.util.*;

Queue<Integer> queue = new LinkedList<>();
queue.offer(1);
queue.offer(2);
System.out.println(queue.poll());
```

### **4. Map**

**Map** is an interface that stores data as **key-value pairs**.

**Key Features:**

* Each **key must be unique**.
* Values can be duplicated.
* Provides fast data lookup using keys.

**How it Works:**

* Data is stored as `<key, value>`, and values are accessed using their keys.

**When to Use:**

* When data needs to be stored and retrieved using a **unique identifier**.

**Example:**

```java
import java.util.*;

Map<Integer, String> map = new HashMap<>();
map.put(1, "John");
map.put(2, "Alice");
System.out.println(map.get(1));
```

### **5. ArrayList**

**ArrayList** is a **resizable array** implementation of the **List** interface.

**Key Features:**

* Maintains **insertion order**.
* Allows **duplicate elements**.
* Provides **fast random access** using indexes.
* Automatically grows when capacity is exceeded.

**How it Works:**

* Internally uses a **dynamic array** to store elements.

**When to Use:**

* When frequent **reading and searching** operations are required.

**Example:**

```java
import java.util.*;

ArrayList<String> list = new ArrayList<>();
list.add("Java");
list.add("Spring");
System.out.println(list.get(0));
```

### **6. LinkedList**

**LinkedList** is a **doubly linked list** implementation of the **List** and **Queue** interfaces.

**Key Features:**

* Maintains **insertion order**.
* Allows **duplicate elements**.
* Fast **insertion and deletion**.
* Can be used as both a **List** and a **Queue**.

**How it Works:**

* Each element is stored as a **node** containing data and references to the previous and next nodes.

**When to Use:**

* When the application requires **frequent insertions or deletions**.

**Example:**

```java
import java.util.*;

LinkedList<String> list = new LinkedList<>();
list.add("Java");
list.add("Spring Boot");
list.addFirst("Programming");
System.out.println(list);
```

### **7. Vector**

**Vector** is a **legacy class** that implements the **List** interface using a dynamic array.

**Key Features:**

* Maintains **insertion order**.
* Allows **duplicate elements**.
* **Thread-safe** because its methods are synchronized.
* Automatically increases its size when needed.

**How it Works:**

* Internally uses a **resizable array** to store elements.

**When to Use:**

* When you need a **thread-safe List** in a multi-threaded environment. In modern applications, **ArrayList** is usually preferred for better performance.

**Example:**

```java id="a8k3pz"
import java.util.*;

Vector<String> vector = new Vector<>();
vector.add("Java");
vector.add("Spring");
System.out.println(vector);
```

### **8. HashSet**

**HashSet** is a class that implements the **Set** interface and stores **unique elements**.

**Key Features:**

* Does **not allow duplicates**.
* Does **not maintain insertion order**.
* Allows **one null value**.
* Provides **fast insertion and search**.

**How it Works:**

* Internally uses a **HashMap** to store elements based on their hash values.

**When to Use:**

* When you need a collection of **unique elements** and ordering is not important.

**Example:**

```java id="q9x4md"
import java.util.*;

HashSet<Integer> set = new HashSet<>();
set.add(10);
set.add(20);
set.add(10);
System.out.println(set);
```

### **9. LinkedHashSet**

**LinkedHashSet** is a class that combines the features of **HashSet** and **LinkedList**.

**Key Features:**

* Does **not allow duplicates**.
* **Maintains insertion order**.
* Allows **one null value**.
* Slightly slower than **HashSet** due to order maintenance.

**How it Works:**

* Uses a **hash table** with a **linked list** to preserve insertion order.

**When to Use:**

* When you need **unique elements** and also want to keep the order in which they were added.

**Example:**

```java id="w2m6jb"
import java.util.*;

LinkedHashSet<String> set = new LinkedHashSet<>();
set.add("Java");
set.add("Spring");
set.add("Java");
System.out.println(set);
```

### **10. TreeSet**

**TreeSet** is a class that implements the **Set** interface and stores elements in **sorted order**.

**Key Features:**

* Does **not allow duplicates**.
* Stores elements in **ascending order** by default.
* Does **not allow null values**.
* Provides operations like `first()`, `last()`, `higher()`, and `lower()`.

**How it Works:**

* Internally uses a **Red-Black Tree** for automatic sorting.

**When to Use:**

* When you need **unique elements** in **sorted order**.

**Example:**

```java id="m5j7ke"
import java.util.*;

TreeSet<Integer> set = new TreeSet<>();
set.add(30);
set.add(10);
set.add(20);
System.out.println(set);
```

### **11. HashMap**

**HashMap** is a class that implements the **Map** interface and stores data as **key-value pairs**.

**Key Features:**

* Stores **unique keys** and allows **duplicate values**.
* Does **not maintain insertion order**.
* Allows **one null key** and multiple null values.
* Provides **fast insertion, deletion, and lookup**.

**How it Works:**

* Internally uses a **hash table** to store and retrieve data using keys.

**When to Use:**

* When you need **fast access** to data using a unique key and ordering is not required.

**Example:**

```java id="z7p2qt"
import java.util.*;

HashMap<Integer, String> map = new HashMap<>();
map.put(1, "John");
map.put(2, "Alice");
System.out.println(map.get(1));
```

### **12. LinkedHashMap**

**LinkedHashMap** is a class that implements the **Map** interface and stores data as **key-value pairs** while maintaining **insertion order**.

**Key Features:**

* Stores **unique keys** and allows **duplicate values**.
* **Maintains insertion order**.
* Allows **one null key** and multiple null values.
* Slightly slower than **HashMap** because it maintains order.

**How it Works:**

* Internally uses a **hash table** and a **doubly linked list** to preserve insertion order.

**When to Use:**

* When you need **fast lookup** and want to preserve the order in which entries are added.

**Example:**

```java id="gvw6xr"
import java.util.*;

LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
map.put(1, "Java");
map.put(2, "Spring");
System.out.println(map);
```

### **13. TreeMap**

**TreeMap** is a class that implements the **Map** interface and stores entries in **sorted order of keys**.

**Key Features:**

* Stores **unique keys**.
* Keys are automatically sorted in **ascending order**.
* Does **not allow null keys**, but allows multiple null values.
* Provides methods like `firstKey()` and `lastKey()`.

**How it Works:**

* Internally uses a **Red-Black Tree** to keep keys sorted.

**When to Use:**

* When you need data to be stored and retrieved in **sorted key order**.

**Example:**

```java id="h6d2tp"
import java.util.*;

TreeMap<Integer, String> map = new TreeMap<>();
map.put(3, "C");
map.put(1, "A");
map.put(2, "B");
System.out.println(map);
```

### **14. PriorityQueue**

**PriorityQueue** is a class that implements the **Queue** interface and processes elements based on their **priority** instead of insertion order.

**Key Features:**

* Follows **priority-based ordering**.
* By default, elements are sorted in **ascending order**.
* Does **not allow null values**.
* Allows duplicate elements.

**How it Works:**

* Internally uses a **heap data structure** to maintain element priority.

**When to Use:**

* For **task scheduling**, **job processing**, and algorithms that require priority handling.

**Example:**

```java id="nb0k7c"
import java.util.*;

PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.poll());
```

### **15. Comparable vs Comparator**

**Comparable** and **Comparator** are interfaces used for **sorting objects**.

**Key Features:**

* **Comparable** provides the **default (natural) sorting**.
* **Comparator** provides **custom sorting logic**.
* **Comparable** has the `compareTo()` method.
* **Comparator** has the `compare()` method.

**How it Works:**

* Implement **Comparable** inside the class for a single sorting rule.
* Use **Comparator** when multiple sorting criteria are needed.

**When to Use:**

* **Comparable** → Default sorting (e.g., by ID).
* **Comparator** → Custom sorting (e.g., by Name or Salary).

| Feature      | Comparable    | Comparator  |
| ------------ | ------------- | ----------- |
| Package      | `java.lang`   | `java.util` |
| Method       | `compareTo()` | `compare()` |
| Sorting      | Default       | Custom      |
| Modify Class | Yes           | No          |

**Example:**

```java id="q7k4la"
import java.util.*;

List<Integer> list = Arrays.asList(30, 10, 20);
Collections.sort(list);   // Uses Comparable
System.out.println(list);
```

### **16. Collections Utility Class**

**Collections** is a utility class in `java.util` that provides **static methods** to perform operations on collections.

**Key Features:**

* Provides methods like **`sort()`**, **`reverse()`**, **`shuffle()`**, and **`max()`**.
* Works with **List**, **Set**, and other collection objects.
* Simplifies common collection operations.

**How it Works:**

* Call the required static method by using the **`Collections`** class name.

**When to Use:**

* When you need to perform common operations like **sorting**, **reversing**, or **searching** on collections.

**Example:**

```java id="x5m1br"
import java.util.*;

List<Integer> list = new ArrayList<>();
list.add(30);
list.add(10);
list.add(20);

Collections.sort(list);
System.out.println(list);
```


# **4. Internal Working of Collections**

### **1. How HashMap Works Internally**

**HashMap** stores data as **key-value pairs** and uses a **hash table** for fast access.

**Key Features:**

* Stores **unique keys** and duplicate values.
* Average **O(1)** time complexity for `put()` and `get()`.
* Uses **hashing** and **buckets** internally.
* Handles collisions using **Linked List** and **Red-Black Tree** (Java 8+).

**How it Works:**

1. `hashCode()` is called on the key.
2. Hash value is converted into a bucket index.
3. The key-value pair is stored in that bucket.
4. If multiple keys map to the same bucket (**collision**), they are stored using a **Linked List** or converted to a **Red-Black Tree** when the bucket becomes large.
5. During retrieval, `hashCode()` and `equals()` are used to find the correct value.

**When to Use:**

* When you need **fast insertion**, **search**, and **deletion** based on keys.

**Example:**

```java id="8m7rkp"
HashMap<Integer, String> map = new HashMap<>();
map.put(1, "Java");
map.put(2, "Spring");

System.out.println(map.get(1));
```

### **2. HashMap vs Hashtable**

**HashMap** and **Hashtable** both store data as **key-value pairs**, but they differ in synchronization and performance.

**Key Features:**

* **HashMap** is **not thread-safe**.
* **Hashtable** is **thread-safe** because methods are synchronized.
* **HashMap** allows **one null key** and multiple null values.
* **Hashtable** does **not allow null keys or values**.

**When to Use:**

* Use **HashMap** in single-threaded applications for better performance.
* Use **Hashtable** only when synchronization is required (though **ConcurrentHashMap** is preferred today).

| Feature     | HashMap   | Hashtable |
| ----------- | --------- | --------- |
| Thread Safe | ✘         | ✔         |
| Null Key    | 1 Allowed | ✘         |
| Null Values | Allowed   | ✘         |
| Performance | Faster    | Slower    |

**Example:**

```java id="5pqz7a"
Map<Integer, String> map = new HashMap<>();
map.put(1, "Java");

Hashtable<Integer, String> table = new Hashtable<>();
table.put(2, "Spring");
```

### **3. HashSet Internal Working**

**HashSet** internally uses a **HashMap** to store unique elements.

**Key Features:**

* Does **not allow duplicates**.
* Provides average **O(1)** insertion and search.
* Internally backed by a **HashMap**.

**How it Works:**

* When an element is added, it becomes the **key** in the internal `HashMap`.
* A constant dummy object is stored as the value.
* `hashCode()` and `equals()` are used to avoid duplicates.

**When to Use:**

* When you need a collection of **unique elements** with fast lookup.

**Example:**

```java id="9k2tmb"
HashSet<String> set = new HashSet<>();
set.add("Java");
set.add("Java");   // Duplicate ignored

System.out.println(set);
```

### **4. TreeMap Internal Working**

**TreeMap** stores key-value pairs in **sorted order of keys** using a **Red-Black Tree**.

**Key Features:**

* Stores **unique keys**.
* Automatically sorts keys in **ascending order**.
* Operations like `put()` and `get()` take **O(log n)** time.
* Does not allow **null keys**.

**How it Works:**

* Every new entry is inserted into a **Red-Black Tree**.
* The tree automatically balances itself after insertion or deletion.
* Data is retrieved based on the sorted key structure.

**When to Use:**

* When you need **sorted data** and efficient searching.

**Example:**

```java id="2lj8ws"
TreeMap<Integer, String> map = new TreeMap<>();
map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map);
```

### **5. ConcurrentHashMap Internal Working**

**ConcurrentHashMap** is a **thread-safe** implementation of the **Map** interface designed for high-performance concurrent access.

**Key Features:**

* Supports **multiple threads** without locking the entire map.
* Better performance than **Hashtable**.
* Does **not allow null keys or null values**.
* Uses **fine-grained locking** and **CAS (Compare-And-Swap)** operations.

**How it Works:**

* The map is internally divided so multiple threads can access different parts simultaneously.
* Read operations usually happen **without locking**.
* Write operations lock only the required portion instead of the whole map.

**When to Use:**

* In **multi-threaded applications** where many threads need to read and update shared data.

**Example:**

```java id="w4c8ny"
import java.util.concurrent.*;

ConcurrentHashMap<Integer, String> map =
        new ConcurrentHashMap<>();

map.put(1, "Java");
map.put(2, "Spring");

System.out.println(map.get(1));
```

### **6. Fail Fast vs Fail Safe Iterator**

**Fail Fast** and **Fail Safe** are two types of iterator behaviors in Java Collections.

**Key Features:**

* **Fail Fast** throws **`ConcurrentModificationException`** if the collection is modified while iterating.
* **Fail Safe** works on a **copy** of the collection, so it does not throw an exception.
* **ArrayList** and **HashMap** iterators are **Fail Fast**.
* **ConcurrentHashMap** and **CopyOnWriteArrayList** iterators are **Fail Safe**.

**How it Works:**

* **Fail Fast** checks for structural changes using an internal modification count.
* **Fail Safe** creates a separate copy and iterates over it.

**When to Use:**

* Use **Fail Fast** for normal collections.
* Use **Fail Safe** in **multi-threaded** environments where concurrent modification is expected.

| Feature                   | Fail Fast | Fail Safe |
| ------------------------- | --------- | --------- |
| Exception on Modification | ✔         | ✘         |
| Uses Original Collection  | ✔         | ✘         |
| Thread Safe               | ✘         | ✔         |

**Example:**

```java id="j4q9vx"
List<String> list = new ArrayList<>();
list.add("Java");

for (String s : list) {
    // list.add("Spring"); // ConcurrentModificationException
    System.out.println(s);
}
```

### **7. Load Factor**

**Load Factor** is the ratio that determines **when a HashMap should increase its capacity**.

**Key Features:**

* Default **Load Factor = 0.75**.
* Calculated as:
  `Load Factor = Number of Entries / Bucket Capacity`
* Helps balance **memory usage** and **performance**.

**How it Works:**

* When the number of elements exceeds `capacity × load factor`, **rehashing** is triggered.

**When to Use:**

* Use the default value (`0.75`) for most applications. Increase or decrease it only for specific performance needs.

**Example:**

```java id="y7w2fk"
HashMap<Integer, String> map =
        new HashMap<>(16, 0.75f);
```

### **8. Rehashing**

**Rehashing** is the process of **creating a larger hash table and redistributing existing entries**.

**Key Features:**

* Happens automatically when the **load factor limit** is reached.
* Increases the internal bucket capacity.
* Reduces the number of **hash collisions**.

**How it Works:**

1. HashMap becomes full beyond the load factor threshold.
2. A new larger bucket array is created (usually double the size).
3. Existing entries are recalculated and moved to new buckets.

**When to Use:**

* Rehashing is handled automatically by **HashMap**; developers do not need to call it manually.

**Example:**

```java id="6d8rpa"
HashMap<Integer, String> map = new HashMap<>();

for (int i = 1; i <= 20; i++) {
    map.put(i, "Value" + i);
}
```

### **9. Hashing & HashCode**

**Hashing** is a technique used to **convert data into a fixed numeric value**, and **`hashCode()`** is the method that generates that value for an object.

**Key Features:**

* **`hashCode()`** returns an integer hash value.
* Objects with the same hash code may still be different (**collision**).
* **`equals()`** is used along with `hashCode()` to identify the correct object.
* Widely used in **HashMap**, **HashSet**, and **Hashtable**.

**How it Works:**

1. `hashCode()` generates a hash value for the key.
2. The hash value determines the bucket location.
3. If multiple objects share the same hash value, `equals()` checks the actual equality.

**When to Use:**

* When creating custom objects to be stored in **HashMap** or **HashSet**, override both **`hashCode()`** and **`equals()`**.

**Example:**

```java id="v5n3qh"
class Student {
    int id;

    Student(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
```


# **5. Exception Handling**

### **1. Checked Exception**

**Checked Exceptions** are exceptions that are **checked at compile time**. The compiler forces the programmer to handle or declare them.

**Key Features:**

* Checked during **compilation**.
* Must be handled using **`try-catch`** or declared with **`throws`**.
* Usually occur due to **external resources** like files or databases.

**How it Works:**

* If a checked exception is not handled, the code will not compile.

**When to Use:**

* For recoverable situations such as **file handling**, **database access**, or **network operations**.

**Example:**

```java id="p7x2qm"
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("test.txt");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
```

### **2. Unchecked Exception**

**Unchecked Exceptions** are exceptions that occur at **runtime** and are not checked by the compiler.

**Key Features:**

* Checked during **runtime**.
* Handling is **optional**.
* Inherit from the **`RuntimeException`** class.

**How it Works:**

* If not handled, the program terminates and displays an error message.

**When to Use:**

* For programming errors such as invalid logic or incorrect data access.

**Common Examples:**

* **`NullPointerException`**
* **`ArithmeticException`**
* **`ArrayIndexOutOfBoundsException`**

**Example:**

```java id="n3k8wy"
public class Main {
    public static void main(String[] args) {
        int a = 10 / 0;   // ArithmeticException
    }
}
```

### **3. Error vs Exception**

**Error** and **Exception** both represent abnormal conditions, but they differ in severity and handling.

**Key Features:**

* **Error** indicates a serious problem that applications usually cannot recover from.
* **Exception** indicates a condition that can often be handled by the program.
* Errors are caused by the **JVM or system**, while exceptions are usually caused by application logic.

**When to Use:**

* Handle **Exceptions** in application code.
* **Errors** are generally not caught or handled.

| Feature            | Error              | Exception         |
| ------------------ | ------------------ | ----------------- |
| Recoverable        | ✘                  | ✔                 |
| Handled by Program | Rarely             | Yes               |
| Occurs             | JVM/System issue   | Application issue |
| Example            | `OutOfMemoryError` | `IOException`     |

**Example:**

```java id="f4m9zc"
try {
    int[] arr = new int[2];
    System.out.println(arr[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Exception handled");
}
```

### **4. try-catch-finally**

**`try-catch-finally`** is Java's mechanism for **handling exceptions** and ensuring cleanup code is executed.

**Key Features:**

* **`try`** contains code that may throw an exception.
* **`catch`** handles the exception.
* **`finally`** always executes, whether an exception occurs or not.

**How it Works:**

1. Code inside the **`try`** block executes.
2. If an exception occurs, control moves to the matching **`catch`** block.
3. The **`finally`** block runs at the end for cleanup activities.

**When to Use:**

* When you need to handle exceptions and ensure resources like **files**, **database connections**, or **streams** are properly closed.

**Example:**

```java id="k6v2ht"
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

### **5. throw vs throws**

**`throw`** is used to **explicitly throw an exception**, while **`throws`** is used to **declare exceptions in method signature**.

**Key Features:**

* **throw** → used inside method to throw an exception.
* **throws** → used in method declaration.
* **throw** throws a single exception instance.
* **throws** can declare multiple exceptions.

**How it Works:**

* **throw** creates and throws an exception object.
* **throws** informs the caller that the method may throw exceptions.

**When to Use:**

* Use **throw** for custom conditions.
* Use **throws** for propagating exceptions.

**Example:**

```java id="t1ex5a"
public class Main {
    static void check(int age) throws Exception {
        if (age < 18) {
            throw new Exception("Not eligible");
        }
    }
}
```

---

### **6. Custom Exceptions**

**Custom Exceptions** are user-defined exceptions created by extending **Exception** or **RuntimeException**.

**Key Features:**

* Helps define **business-specific errors**.
* Improves **readability and clarity**.
* Can be **checked or unchecked**.

**How it Works:**

* Create a class extending **Exception**.
* Throw it using **throw** keyword.

**When to Use:**

* When built-in exceptions are not enough for business rules.

**Example:**

```java id="c2ex6b"
class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}

public class Main {
    static void check(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age not allowed");
        }
    }
}
```

---

### **7. Exception Propagation**

**Exception Propagation** means an exception is **passed up the call stack** until it is handled.

**Key Features:**

* Happens automatically in Java.
* Moves from **method to method**.
* Stops when a **catch block** handles it.

**How it Works:**

* If a method does not handle an exception, it is passed to the **caller method**.

**When to Use:**

* When you want **centralized exception handling**.

**Example:**

```java id="p3ex7c"
public class Main {
    static void method1() {
        int a = 10 / 0;
    }

    static void method2() {
        method1();
    }

    public static void main(String[] args) {
        try {
            method2();
        } catch (Exception e) {
            System.out.println("Handled in main");
        }
    }
}
```

---

### **8. Multi-Catch**

**Multi-Catch** allows handling **multiple exceptions in a single catch block**.

**Key Features:**

* Reduces **duplicate catch blocks**.
* Uses `|` (pipe) symbol.
* Introduced in **Java 7**.

**How it Works:**

* If any exception matches, the same catch block executes.

**When to Use:**

* When multiple exceptions require the **same handling logic**.

**Example:**

```java id="m4ex8d"
public class Main {
    public static void main(String[] args) {
        try {
            int[] arr = new int[2];
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println("Exception handled");
        }
    }
}
```


# **6. Java 8 Features**

### **1. Lambda Expressions**

**Lambda Expressions** are a way to write **anonymous functions (no name methods)** in a short and readable form in Java.

**Key Features:**

* Introduced in **Java 8**.
* Reduces **boilerplate code**.
* Used with **functional interfaces**.
* Syntax: **(parameters) -> expression**

**How it Works:**

* Replaces anonymous inner classes with a **short function expression**.

**When to Use:**

* When you need **simple implementation of functional interfaces**.

**Example:**

```java id="l1"
public class Main {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello Lambda");
        r.run();
    }
}
```

---

### **2. Functional Interfaces**

A **Functional Interface** is an interface that contains **only one abstract method**.

**Key Features:**

* Has **single abstract method (SAM)**.
* Can have **default and static methods**.
* Used with **Lambda Expressions**.

**How it Works:**

* Lambda expression provides implementation for the **single method**.

**When to Use:**

* When using **Lambda Expressions or method references**.

**Example:**

```java id="f2"
@FunctionalInterface
interface MyFunc {
    void show();
}
```

---

### **3. Predicate**

**Predicate** is a functional interface that **returns a boolean result**.

**Key Features:**

* Takes **one input parameter**.
* Returns **true or false**.
* Method: **test()**.

**How it Works:**

* Evaluates a condition and returns a **boolean value**.

**When to Use:**

* For **filtering data**.

**Example:**

```java id="p3"
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> p = x -> x > 10;
        System.out.println(p.test(15));
    }
}
```

---

### **4. Function**

**Function** is a functional interface that takes an input and returns an output.

**Key Features:**

* Takes **one input**.
* Produces **one output**.
* Method: **apply()**.

**How it Works:**

* Transforms input into output.

**When to Use:**

* When you need **data transformation**.

**Example:**

```java id="f4"
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Function<Integer, String> f = x -> "Value: " + x;
        System.out.println(f.apply(10));
    }
}
```

---

### **5. Consumer**

**Consumer** is a functional interface that **accepts input but does not return anything**.

**Key Features:**

* Takes **one input**.
* Returns **void**.
* Method: **accept()**.

**How it Works:**

* Performs an action on input data.

**When to Use:**

* When you want to **process or print data**.

**Example:**

```java id="c5"
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Consumer<String> c = x -> System.out.println(x);
        c.accept("Hello Consumer");
    }
}
```

---

### **6. Supplier**

**Supplier** is a functional interface that **provides a result without taking any input**.

**Key Features:**

* Takes **no input**.
* Returns a **value**.
* Method: **get()**.

**How it Works:**

* Supplies data when needed.

**When to Use:**

* When you need to **generate or provide values dynamically**.

**Example:**

```java id="s6"
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Supplier<String> s = () -> "Hello Supplier";
        System.out.println(s.get());
    }
}
```

### **7. Method References**

**Method References** are a shorthand way to refer to a **method using `::` operator** instead of a lambda expression.

**Key Features:**

* Cleaner alternative to **lambda expressions**.
* Uses **`ClassName::methodName`** syntax.
* Works with **functional interfaces**.

**How it Works:**

* Java directly references an existing method instead of writing a lambda body.

**When to Use:**

* When a lambda is simply calling an existing method.

**Example:**

```java id="mr7"
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B");
        list.forEach(System.out::println);
    }
}
```

---

### **8. Stream API**

**Stream API** is used to process **collections in a functional and declarative way**.

**Key Features:**

* Supports **functional operations** like filter, map, reduce.
* Does **not modify original data**.
* Supports **parallel processing**.

**How it Works:**

* Converts collection into a **stream pipeline** and processes elements step-by-step.

**When to Use:**

* For **bulk data operations** like filtering, sorting, mapping.

**Example:**

```java id="st8"
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);

        list.stream()
            .filter(x -> x % 2 == 0)
            .forEach(System.out::println);
    }
}
```

---

### **9. Optional**

**Optional** is a container object used to **avoid NullPointerException**.

**Key Features:**

* Represents **value or no value (null-safe)**.
* Methods: **of(), empty(), orElse()**.
* Improves **code readability and safety**.

**How it Works:**

* Wraps a value and forces handling of **null cases explicitly**.

**When to Use:**

* When a method may return **null values**.

**Example:**

```java id="op9"
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.ofNullable(null);

        System.out.println(name.orElse("Default Name"));
    }
}
```

---

### **10. Default Methods**

**Default Methods** allow interfaces to have **method implementations**.

**Key Features:**

* Introduced in **Java 8**.
* Defined using **default keyword**.
* Helps in **backward compatibility**.

**How it Works:**

* Interface provides a method body that implementing classes can use or override.

**When to Use:**

* When you want to **add new methods to interfaces without breaking existing code**.

**Example:**

```java id="dm10"
interface A {
    default void show() {
        System.out.println("Default Method");
    }
}
```

---

### **11. Static Interface Methods**

**Static Interface Methods** are methods inside interfaces that belong to the **interface itself**.

**Key Features:**

* Defined using **static keyword**.
* Cannot be overridden.
* Called using **InterfaceName.method()**.

**How it Works:**

* Belongs to interface, not implementing classes.

**When to Use:**

* For **utility/helper methods** inside interfaces.

**Example:**

```java id="si11"
interface A {
    static void show() {
        System.out.println("Static Method");
    }
}

public class Main {
    public static void main(String[] args) {
        A.show();
    }
}
```

---

### **12. Date & Time API**

**Date & Time API** is a modern Java API introduced in **Java 8** for handling dates and times.

**Key Features:**

* Located in **`java.time` package**.
* Immutable and **thread-safe**.
* Classes: **LocalDate, LocalTime, LocalDateTime**.
* Better than legacy **Date and Calendar**.

**How it Works:**

* Uses separate classes for **date, time, and datetime operations**.

**When to Use:**

* For **modern date/time handling** in applications.

**Example:**

```java id="dt12"
import java.time.*;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
    }
}
```


# **7. Stream API**

### **1. filter()**

**`filter()`** is used to **select elements from a stream based on a condition**.

**Key Features:**

* Takes a **Predicate** (returns boolean).
* Keeps only **matching elements**.
* Does not modify original data.

**How it Works:**

* Each element is checked against a condition, and only **true results pass through**.

**When to Use:**

* When you need to **remove unwanted data**.

**Example:**

```java id="f1"
List<Integer> list = Arrays.asList(1,2,3,4);
list.stream()
    .filter(x -> x % 2 == 0)
    .forEach(System.out::println);
```

---

### **2. map()**

**`map()`** is used to **transform each element in a stream**.

**Key Features:**

* Takes a **Function**.
* Converts each element into another form.
* One-to-one mapping.

**How it Works:**

* Each element is replaced with a **transformed value**.

**When to Use:**

* When you need **data transformation**.

**Example:**

```java id="m2"
List<Integer> list = Arrays.asList(1,2,3);
list.stream()
    .map(x -> x * 2)
    .forEach(System.out::println);
```

---

### **3. flatMap()**

**`flatMap()`** is used to **flatten nested structures into a single stream**.

**Key Features:**

* Converts **Stream of Streams → single Stream**.
* Used for **complex data structures**.
* Handles **one-to-many mapping**.

**How it Works:**

* Breaks nested collections into a **single stream of elements**.

**When to Use:**

* When working with **List of Lists or nested data**.

**Example:**

```java id="fm3"
List<List<Integer>> list = Arrays.asList(
    Arrays.asList(1,2),
    Arrays.asList(3,4)
);

list.stream()
    .flatMap(x -> x.stream())
    .forEach(System.out::println);
```

---

### **4. reduce()**

**`reduce()`** is used to **combine all elements into a single result**.

**Key Features:**

* Performs **aggregation operations**.
* Returns a **single value**.
* Uses **BinaryOperator**.

**How it Works:**

* Repeatedly combines elements using a function.

**When to Use:**

* For **sum, product, concatenation, aggregation**.

**Example:**

```java id="r4"
List<Integer> list = Arrays.asList(1,2,3,4);

int sum = list.stream()
    .reduce(0, (a, b) -> a + b);

System.out.println(sum);
```

---

### **5. collect()**

**`collect()`** is used to **convert a stream into a collection or result container**.

**Key Features:**

* Uses **Collectors class**.
* Terminal operation.
* Converts stream to **List, Set, Map, etc.**

**How it Works:**

* Accumulates stream elements into a **final structure**.

**When to Use:**

* When you need to **store processed stream results**.

**Example:**

```java id="c5"
List<Integer> list = Arrays.asList(1,2,3);

List<Integer> result = list.stream()
    .collect(Collectors.toList());
```

---

### **6. sorted()**

**`sorted()`** is used to **sort elements in a stream**.

**Key Features:**

* Sorts in **natural order by default**.
* Supports **custom comparator**.
* Does not modify original data.

**How it Works:**

* Applies sorting algorithm on stream elements.

**When to Use:**

* When you need **ordered output**.

**Example:**

```java id="s6"
List<Integer> list = Arrays.asList(3,1,4,2);

list.stream()
    .sorted()
    .forEach(System.out::println);
```

### **7. distinct()**

**`distinct()`** is used to **remove duplicate elements from a stream**.

**Key Features:**

* Keeps only **unique elements**.
* Uses **equals() and hashCode()**.
* Maintains order in ordered streams.

**How it Works:**

* Compares elements and removes duplicates internally.

**When to Use:**

* When you need **unique values** from a collection.

**Example:**

```java id="d7"
List<Integer> list = Arrays.asList(1,2,2,3);
list.stream()
    .distinct()
    .forEach(System.out::println);
```

---

### **8. groupingBy()**

**`groupingBy()`** is a **Collector** used to **group elements based on a classifier function**.

**Key Features:**

* Groups data into a **Map**.
* Uses **Collectors class**.
* Supports multi-level grouping.

**How it Works:**

* Elements are grouped based on a **key function**.

**When to Use:**

* When you need **categorization of data**.

**Example:**

```java id="g8"
Map<Integer, List<String>> map =
    list.stream()
        .collect(Collectors.groupingBy(String::length));
```

---

### **9. partitioningBy()**

**`partitioningBy()`** is a **Collector** that divides data into **two groups (true/false)**.

**Key Features:**

* Returns a **Map<Boolean, List<T>>**.
* Always creates **two partitions**.
* Uses a **Predicate**.

**How it Works:**

* Elements are split based on a **condition**.

**When to Use:**

* When you need **binary classification of data**.

**Example:**

```java id="p9"
List<Integer> list = Arrays.asList(1,2,3,4);

Map<Boolean, List<Integer>> map =
    list.stream()
        .collect(Collectors.partitioningBy(x -> x % 2 == 0));
```

---

### **10. Parallel Streams**

**Parallel Streams** are streams that process data **concurrently using multiple threads**.

**Key Features:**

* Uses **ForkJoinPool framework**.
* Improves performance for large data sets.
* Not always faster for small data.

**How it Works:**

* Splits data into **multiple chunks** and processes them in parallel.

**When to Use:**

* For **large datasets** and CPU-intensive operations.

**Example:**

```java id="ps10"
List<Integer> list = Arrays.asList(1,2,3,4,5);

list.parallelStream()
    .forEach(System.out::println);
```

---

### **11. Stream vs Collection**

**Stream** and **Collection** are both used to handle data but work differently.

**Key Features:**

* **Collection** stores data in memory.
* **Stream** processes data in a pipeline.
* Streams are **lazy and do not store data**.
* Collections are **eager and mutable**.

**How it Works:**

* Collection holds data, Stream processes data step-by-step.

**When to Use:**

* Use **Collection** for storing data.
* Use **Stream** for processing data.

| Feature      | Collection | Stream            |
| ------------ | ---------- | ----------------- |
| Storage      | Yes        | No                |
| Processing   | External   | Internal pipeline |
| Reusability  | Yes        | No (single use)   |
| Modification | Yes        | No                |

**Example:**

```java id="sv11"
List<Integer> list = Arrays.asList(1,2,3);

list.stream()
    .map(x -> x * 2)
    .forEach(System.out::println);
```


# **8. Multithreading & Concurrency**

**1. Thread Lifecycle**

**Definition:**
The **Thread Lifecycle** describes the different **states** a thread goes through from creation to termination.

**Key Features**

* Defines the **execution flow** of a thread.
* Helps understand thread behavior and debugging.
* Managed by the **JVM Thread Scheduler**.

**Lifecycle States**

1. **NEW** – Thread object is created.
2. **RUNNABLE** – Thread is ready or running.
3. **BLOCKED** – Waiting to acquire a lock.
4. **WAITING/TIMED_WAITING** – Waiting for another thread or a specific time.
5. **TERMINATED** – Thread execution is completed.

**How It Works**

* Create thread using `new Thread()`.
* Call `start()` to move it to **RUNNABLE**.
* JVM schedules execution.
* Thread may enter **BLOCKED** or **WAITING** state.
* After execution, it moves to **TERMINATED**.

**When to Use**

* While working with **Multithreading**.
* For **Debugging** thread issues.
* To understand thread behavior and performance.

**Code Example**

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread Running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread(); // NEW
        t.start();                   // RUNNABLE
    }
}
```

---

**2. Runnable vs Callable**

**Definition:**
Both are used to execute tasks in a separate thread, but **Callable** can return a result and throw exceptions, while **Runnable** cannot.

**Key Features**

| Feature          | Runnable | Callable |
| ---------------- | -------- | -------- |
| Return Value     | No       | Yes      |
| Throws Exception | No       | Yes      |
| Method           | `run()`  | `call()` |
| Introduced In    | Java 1.0 | Java 5   |

**How It Works**

* **Runnable** executes a task without returning a result.
* **Callable** executes a task and returns a result using **Future**.

**When to Use**

* Use **Runnable** for simple background tasks.
* Use **Callable** when a result is required after execution.

**Runnable Example**

```java
Runnable task = () -> System.out.println("Runnable Task");

Thread t = new Thread(task);
t.start();
```

**Callable Example**

```java
ExecutorService executor = Executors.newSingleThreadExecutor();

Callable<Integer> task = () -> 100;

Future<Integer> future = executor.submit(task);

System.out.println(future.get());

executor.shutdown();
```

---

**3. Synchronization**

**Definition:**
**Synchronization** is a mechanism that allows only one thread at a time to access a shared resource, preventing data inconsistency.

**Key Features**

* Prevents **Race Conditions**.
* Ensures **Thread Safety**.
* Uses **Locking Mechanism**.
* Can be applied to methods or blocks.

**How It Works**

* A thread acquires a lock before entering synchronized code.
* Other threads must wait until the lock is released.
* Only one thread can execute the synchronized section at a time.

**When to Use**

* Multiple threads access shared data.
* Data consistency is important.
* Critical business operations need protection.

**Code Example**

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

---

**4. Race Condition**

**Definition:**
A **Race Condition** occurs when multiple threads access and modify shared data simultaneously, leading to unpredictable results.

**Key Features**

* Happens in **Multithreaded Applications**.
* Causes **Incorrect Data**.
* Depends on thread execution timing.
* Solved using **Synchronization**.

**How It Works**

* Two or more threads read the same value.
* Both update it simultaneously.
* One update overwrites the other.
* Final result becomes incorrect.

**Example Scenario**

```java
count = 0

Thread-1: count++
Thread-2: count++

Expected Result = 2
Actual Result = 1
```

**When to Use Synchronization**

* Shared variables are modified by multiple threads.
* Data accuracy is required.
* Concurrent updates must be controlled.

**Code Example**

```java
class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```
**5. Deadlock**

**Definition:**
A **Deadlock** occurs when two or more threads are waiting for each other’s resources indefinitely, causing all of them to stop execution.

**Key Features**

* Threads are permanently blocked.
* Happens due to circular dependency.
* Resources are never released.
* Application may appear frozen.

**How It Works**

* Thread A holds **Lock 1** and waits for **Lock 2**.
* Thread B holds **Lock 2** and waits for **Lock 1**.
* Neither thread can proceed.

**When to Use Knowledge**

* Designing concurrent applications.
* Avoiding locking issues.
* Debugging thread-related problems.

**Code Example**

```java
Object lock1 = new Object();
Object lock2 = new Object();

Thread t1 = new Thread(() -> {
    synchronized (lock1) {
        synchronized (lock2) {
            System.out.println("Thread 1");
        }
    }
});

Thread t2 = new Thread(() -> {
    synchronized (lock2) {
        synchronized (lock1) {
            System.out.println("Thread 2");
        }
    }
});
```

**Interview One-Liner**

A **Deadlock** occurs when threads wait for each other forever due to locked resources.

**6. Livelock**

**Definition:**
A **Livelock** occurs when threads are active and continuously responding to each other but never make actual progress.

**Key Features**

* Threads are not blocked.
* Threads keep changing states.
* CPU usage remains active.
* No useful work gets completed.

**How It Works**

* Thread A gives way to Thread B.
* Thread B gives way to Thread A.
* Both keep reacting endlessly.

**When to Use Knowledge**

* Designing retry mechanisms.
* Handling concurrent systems.
* Avoiding excessive thread coordination.

**Code Example**

```java
while(resourceBusy) {
    Thread.yield();
}
```

**Interview One-Liner**

A **Livelock** occurs when threads keep reacting to each other but never complete their work.

**7. Starvation**

**Definition:**
**Starvation** occurs when a thread never gets CPU time or resources because other higher-priority threads continuously consume them.

**Key Features**

* Thread waits indefinitely.
* Often caused by thread priorities.
* Low-priority threads suffer.
* Application performance degrades.

**How It Works**

* High-priority threads keep executing.
* Low-priority thread rarely gets scheduled.
* The waiting thread cannot complete its task.

**When to Use Knowledge**

* Working with thread priorities.
* Designing fair scheduling systems.
* Performance optimization.

**Code Example**

```java
Thread high = new Thread(task);
high.setPriority(Thread.MAX_PRIORITY);

Thread low = new Thread(task);
low.setPriority(Thread.MIN_PRIORITY);
```

**Interview One-Liner**

**Starvation** occurs when a thread is continuously denied CPU time or resources.

**8. Volatile Keyword**

**Definition:**
The **volatile** keyword ensures that a variable's latest value is always read from **main memory**, making updates visible to all threads.

**Key Features**

* Provides **Visibility** between threads.
* Prevents thread-local caching.
* Lightweight compared to synchronization.
* Does not provide atomicity.

**How It Works**

* One thread updates a volatile variable.
* Other threads immediately see the updated value.
* JVM reads and writes directly from main memory.

**When to Use**

* Status flags.
* Stop signals.
* Configuration values shared between threads.

**Code Example**

```java
class Task {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }
}
```

**Interview One-Liner**

**volatile** ensures that variable updates made by one thread are immediately visible to other threads.

**9. Atomic Classes**

**Definition:**
**Atomic Classes** provide thread-safe operations on variables without using explicit synchronization.

**Key Features**

* Lock-free thread safety.
* High performance.
* Supports atomic operations.
* Part of `java.util.concurrent.atomic`.

**Common Atomic Classes**

* `AtomicInteger`
* `AtomicLong`
* `AtomicBoolean`
* `AtomicReference`

**How It Works**

* Uses **CAS (Compare-And-Set)** internally.
* Updates happen atomically.
* Multiple threads can safely modify values.

**When to Use**

* Counters.
* Sequence generators.
* High-concurrency applications.

**Code Example**

```java
import java.util.concurrent.atomic.AtomicInteger;

AtomicInteger counter = new AtomicInteger(0);

counter.incrementAndGet();

System.out.println(counter.get());
```

**10. Executor Framework**

**Definition:**
The **Executor Framework** is a Java concurrency framework that simplifies thread management by separating **task submission** from **task execution**.

**Key Features**

* Manages threads automatically.
* Improves performance and scalability.
* Supports asynchronous task execution.
* Provides thread pools and scheduling.

**How It Works**

* Create an **ExecutorService**.
* Submit tasks using `execute()` or `submit()`.
* Framework assigns tasks to available threads.
* Reuses threads instead of creating new ones.

**When to Use**

* Multiple concurrent tasks.
* Background processing.
* High-performance applications.

**Code Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService executor = Executors.newFixedThreadPool(2);

executor.execute(() -> {
    System.out.println("Task Executed");
});

executor.shutdown();
```

**Interview One-Liner**

The **Executor Framework** simplifies thread management by handling thread creation, execution, and reuse automatically.

**11. Thread Pool**

**Definition:**
A **Thread Pool** is a collection of pre-created threads that are reused to execute multiple tasks.

**Key Features**

* Reuses existing threads.
* Reduces thread creation overhead.
* Improves application performance.
* Controls the number of concurrent threads.

**How It Works**

* Threads are created once.
* Tasks are submitted to the pool.
* Available threads pick up and execute tasks.
* After completion, threads return to the pool.

**When to Use**

* Large number of short-lived tasks.
* Web applications.
* Microservices and batch processing.

**Code Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService pool = Executors.newFixedThreadPool(3);

pool.submit(() -> System.out.println("Task 1"));
pool.submit(() -> System.out.println("Task 2"));

pool.shutdown();
```

**Interview One-Liner**

A **Thread Pool** reuses a fixed set of threads to execute multiple tasks efficiently.

**12. Future & CompletableFuture**

**Definition:**
Both are used for **Asynchronous Programming**, but **CompletableFuture** provides advanced features such as chaining, combining, and non-blocking execution.

**Key Features**

| Feature                | Future  | CompletableFuture |
| ---------------------- | ------- | ----------------- |
| Asynchronous Execution | Yes     | Yes               |
| Get Result             | Yes     | Yes               |
| Chaining Tasks         | No      | Yes               |
| Combine Tasks          | No      | Yes               |
| Non-Blocking Support   | Limited | Yes               |

**How It Works**

* Submit a task asynchronously.
* Task executes in another thread.
* Result becomes available later.
* Retrieve or process the result when completed.

**When to Use**

* API calls.
* Background processing.
* Parallel task execution.

**Future Example**

```java
import java.util.concurrent.*;

ExecutorService executor = Executors.newSingleThreadExecutor();

Future<String> future =
        executor.submit(() -> "Hello");

System.out.println(future.get());

executor.shutdown();
```

**CompletableFuture Example**

```java
import java.util.concurrent.CompletableFuture;

CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "Hello");

future.thenAccept(System.out::println);
```

**Interview One-Liner**

**Future** retrieves an asynchronous result, while **CompletableFuture** supports advanced asynchronous workflows and task chaining.

**13. Fork Join Framework**

**Definition:**
The **Fork Join Framework** is designed for **Parallel Processing** by splitting a large task into smaller subtasks and combining the results.

**Key Features**

* Uses **Divide and Conquer** approach.
* Supports parallel execution.
* Uses **Work Stealing Algorithm**.
* Maximizes CPU utilization.

**How It Works**

1. Split a large task (**Fork**).
2. Execute subtasks in parallel.
3. Combine results (**Join**).
4. Return the final result.

**When to Use**

* Large data processing.
* Recursive algorithms.
* CPU-intensive computations.

**Code Example**

```java
import java.util.concurrent.*;

class SumTask extends RecursiveTask<Integer> {

    protected Integer compute() {
        return 10 + 20;
    }
}

ForkJoinPool pool = new ForkJoinPool();

int result = pool.invoke(new SumTask());

System.out.println(result);
```

**Interview One-Liner**

The **Fork Join Framework** improves performance by splitting large tasks into smaller parallel tasks and combining their results.



# **9. Memory Management**

**1. Heap Memory**

**Definition:**
**Heap Memory** is the runtime memory area where **Objects** and **Instance Variables** are stored.

**Key Features**

* Shared among all threads.
* Stores objects created using `new`.
* Managed by **Garbage Collector (GC)**.
* Larger than stack memory.

**How It Works**

* Objects are created in the heap.
* References to those objects are stored in the stack.
* Unused objects are removed by GC.

**When to Use**

* Whenever objects need to exist beyond a method call.
* For storing application data and business objects.

**Code Example**

```java
class Employee {
    String name;
}

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee();
    }
}
```

**Interview One-Liner**

**Heap Memory** stores objects and is managed automatically by the **Garbage Collector**.

**2. Stack Memory**

**Definition:**
**Stack Memory** stores **Local Variables**, **Method Calls**, and **Object References** for each thread.

**Key Features**

* Each thread has its own stack.
* Faster access than heap.
* Memory is automatically released after method execution.
* Follows **LIFO (Last In First Out)**.

**How It Works**

* A stack frame is created when a method is called.
* Local variables are stored in that frame.
* Frame is removed when the method finishes.

**When to Use**

* Method execution.
* Local variable storage.
* Temporary data processing.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        int age = 25;
        display();
    }

    static void display() {
        int salary = 5000;
    }
}
```

**Interview One-Liner**

**Stack Memory** stores method-specific data and is automatically cleared after method execution.

**3. Metaspace**

**Definition:**
**Metaspace** is the memory area that stores **Class Metadata**, such as class definitions, methods, and runtime information.

**Key Features**

* Introduced in **Java 8**.
* Replaced **PermGen**.
* Uses native system memory.
* Grows dynamically when needed.

**How It Works**

* JVM loads class metadata into Metaspace.
* Metadata remains until the class is unloaded.
* Memory is released when classes are removed.

**When to Use**

* Class loading.
* Frameworks that dynamically create classes.
* Large enterprise applications.

**Code Example**

```java
class Employee {
    void display() {
        System.out.println("Employee");
    }
}
```

**Interview One-Liner**

**Metaspace** stores class metadata and replaced **PermGen** in Java 8.

**4. Garbage Collection**

**Definition:**
**Garbage Collection (GC)** is the automatic process of removing unused objects from heap memory.

**Key Features**

* Automatic memory management.
* Prevents memory leaks.
* Frees unused objects.
* Improves application stability.

**How It Works**

1. Objects are created in heap memory.
2. Objects that are no longer referenced become eligible for GC.
3. Garbage Collector identifies and removes them.
4. Memory is reclaimed for reuse.

**When to Use**

* Automatically handled by JVM.
* Important for memory optimization and performance tuning.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        String str = new String("Java");

        str = null;

        System.gc();
    }
}
```

**Interview One-Liner**

**Garbage Collection** automatically removes unused objects from heap memory.

**5. GC Types**

**Definition:**
Java provides different **Garbage Collectors** optimized for various performance and memory requirements.

**Key Features**

* Improve memory management.
* Reduce application pauses.
* Optimize throughput and latency.
* JVM selects the collector based on configuration.

**Common GC Types**

**1. Serial GC**

* Single-threaded GC.
* Suitable for small applications.
* Uses one thread for garbage collection.

**2. Parallel GC**

* Uses multiple threads.
* Focuses on high throughput.
* Default GC in many Java versions.

**3. G1 GC (Garbage First)**

* Divides heap into regions.
* Provides predictable pause times.
* Default GC in modern Java versions.

**4. ZGC**

* Extremely low pause times.
* Handles very large heaps.
* Suitable for high-performance applications.

**5. Shenandoah GC**

* Low-latency garbage collector.
* Performs GC concurrently with application execution.

**How It Works**

* GC identifies unused objects.
* Removes them from heap memory.
* Reclaims memory for future allocations.

**When to Use**

* **Serial GC** – Small applications.
* **Parallel GC** – High throughput applications.
* **G1 GC** – General enterprise applications.
* **ZGC** – Large heap, low latency systems.
* **Shenandoah GC** – Real-time and low-pause applications.

**Code Example**

```bash
# Use G1 GC
java -XX:+UseG1GC MyApp

# Use ZGC
java -XX:+UseZGC MyApp
```

**Interview One-Liner**

**GC Types** are different garbage collection algorithms designed to balance **Throughput**, **Memory Usage**, and **Pause Time**.

**6. Minor GC**

**Definition:**
**Minor GC** is the garbage collection process that cleans up unused objects from the **Young Generation** of the heap memory.

**Key Features**

* Works only on **Young Generation**.
* Runs frequently.
* Usually very fast.
* Surviving objects are moved to **Old Generation** after multiple GC cycles.

**How It Works**

1. New objects are created in **Eden Space**.
2. When Eden becomes full, **Minor GC** starts.
3. Unused objects are removed.
4. Live objects are moved to **Survivor Space** or **Old Generation**.

**When to Use Knowledge**

* JVM performance tuning.
* Memory optimization.
* GC log analysis.

**Code Example**

```java id="x62jmo"
for (int i = 0; i < 100000; i++) {
    String str = new String("Java");
}
```

**Interview One-Liner**

**Minor GC** cleans unused objects from the **Young Generation** and is usually fast.

**7. Major GC**

**Definition:**
**Major GC** is the garbage collection process that cleans up unused objects from the **Old Generation**.

**Key Features**

* Works on **Old Generation**.
* Slower than Minor GC.
* Occurs less frequently.
* Can cause application pauses.

**How It Works**

1. Objects survive multiple Minor GCs.
2. They move to **Old Generation**.
3. When Old Generation becomes full, **Major GC** runs.
4. Unused old objects are removed.

**When to Use Knowledge**

* Performance troubleshooting.
* Memory leak investigation.
* JVM tuning.

**Code Example**

```java id="6qzlf7"
List<byte[]> data = new ArrayList<>();

for (int i = 0; i < 1000; i++) {
    data.add(new byte[1024 * 1024]);
}
```

**Interview One-Liner**

**Major GC** cleans unused objects from the **Old Generation** and is slower than Minor GC.

**8. Full GC**

**Definition:**
**Full GC** is a complete garbage collection process that cleans the entire heap, including **Young Generation**, **Old Generation**, and sometimes **Metaspace**.

**Key Features**

* Cleans all memory regions.
* Most expensive GC operation.
* Causes longer application pauses.
* Should be minimized in production systems.

**How It Works**

1. JVM cannot reclaim enough memory.
2. Full GC is triggered.
3. Entire heap is scanned.
4. Unused objects are removed from all generations.

**When to Use Knowledge**

* JVM monitoring.
* Performance tuning.
* Identifying memory issues.

**Code Example**

```java id="v7mf8s"
System.gc();
```

**Interview One-Liner**

**Full GC** performs garbage collection on the entire heap and usually causes the longest pause time.

**9. Memory Leaks**

**Definition:**
A **Memory Leak** occurs when objects are no longer needed but are still referenced, preventing the Garbage Collector from removing them.

**Key Features**

* Causes increasing memory usage.
* Reduces application performance.
* Can eventually lead to **OutOfMemoryError**.
* Common in long-running applications.

**How It Works**

* Object is created.
* Application keeps an unnecessary reference.
* GC cannot remove the object.
* Memory keeps growing over time.

**When to Use Knowledge**

* Debugging memory problems.
* Optimizing enterprise applications.
* Performance monitoring.

**Code Example**

```java id="qq4tt7"
import java.util.*;

public class Demo {
    static List<Object> cache = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            cache.add(new Object());
        }
    }
}
```

**Interview One-Liner**

A **Memory Leak** happens when unused objects remain referenced and cannot be garbage collected.

**10. OutOfMemoryError**

**Definition:**
**OutOfMemoryError (OOM)** occurs when the JVM cannot allocate more memory because available memory has been exhausted.

**Key Features**

* JVM runs out of memory.
* Application may crash.
* Often caused by memory leaks.
* Indicates serious memory issues.

**How It Works**

1. Application continuously allocates memory.
2. Garbage Collector cannot free enough memory.
3. Heap becomes full.
4. JVM throws **OutOfMemoryError**.

**When to Use Knowledge**

* Production issue analysis.
* Memory optimization.
* JVM configuration and tuning.

**Code Example**

```java id="6ekj8s"
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        while (true) {
            list.add(new byte[1024 * 1024]);
        }
    }
}
```

# **10. JVM Internals**

**1. Class Loader**

**Definition:**
A **Class Loader** is a JVM component responsible for loading `.class` files into memory at runtime.

**Key Features**

* Loads classes dynamically.
* Avoids loading the same class multiple times.
* Follows **Parent Delegation Model**.
* Loads classes only when needed.

**Types of Class Loaders**

1. **Bootstrap Class Loader** – Loads core Java classes.
2. **Platform Class Loader** – Loads Java platform libraries.
3. **Application Class Loader** – Loads application classes from classpath.

**How It Works**

1. JVM requests a class.
2. Class Loader searches for the class.
3. Loads the `.class` file into memory.
4. JVM links and initializes the class.

**When to Use**

* Dynamic class loading.
* Framework development.
* Plugin-based applications.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(
            Main.class.getClassLoader()
        );
    }
}
```

**Interview One-Liner**

A **Class Loader** loads Java classes into JVM memory dynamically at runtime.

**2. JVM Architecture**

**Definition:**
**JVM Architecture** is the internal structure of the JVM that loads, manages, and executes Java programs.

**Key Features**

* Platform independent execution.
* Automatic memory management.
* Provides security and portability.
* Converts bytecode into machine code.

**Main Components**

1. **Class Loader**
2. **Runtime Data Areas**

   * Heap
   * Stack
   * Metaspace
   * PC Register
   * Native Method Stack
3. **Execution Engine**
4. **Garbage Collector**
5. **JNI (Java Native Interface)**

**How It Works**

1. Java source code is compiled into bytecode.
2. Class Loader loads classes.
3. Runtime memory areas are allocated.
4. Execution Engine executes bytecode.
5. Garbage Collector cleans unused objects.

**When to Use**

* JVM tuning.
* Performance optimization.
* Understanding Java internals.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("JVM Running");
    }
}
```

**Interview One-Liner**

**JVM Architecture** consists of components that load, manage, execute, and optimize Java applications.

**3. Execution Engine**

**Definition:**
The **Execution Engine** is the JVM component responsible for executing bytecode and converting it into machine code.

**Key Features**

* Executes bytecode.
* Uses **Interpreter** and **JIT Compiler**.
* Improves application performance.
* Works with Garbage Collector.

**Main Components**

1. **Interpreter**
2. **JIT (Just-In-Time) Compiler**
3. **Garbage Collector**

**How It Works**

* Interpreter executes bytecode line by line.
* Frequently used code is identified.
* JIT Compiler converts it into machine code.
* Future executions become faster.

**When to Use**

* Performance tuning.
* Understanding JVM execution process.
* Java internals discussions.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        int sum = 10 + 20;
        System.out.println(sum);
    }
}
```

**Interview One-Liner**

The **Execution Engine** converts bytecode into machine code and executes it efficiently.

**4. Bytecode**

**Definition:**
**Bytecode** is the intermediate code generated by the Java compiler and executed by the JVM.

**Key Features**

* Platform independent.
* Stored in `.class` files.
* Executed by JVM.
* Enables **Write Once, Run Anywhere (WORA)**.

**How It Works**

1. Java source code (`.java`) is compiled.
2. Compiler generates bytecode (`.class`).
3. JVM loads the bytecode.
4. Execution Engine converts it to machine code.

**When to Use**

* Cross-platform applications.
* JVM-based languages.
* Understanding Java compilation.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

**Compilation Command**

```bash
javac Main.java
```

**Generated File**

```bash
Main.class
```

**Interview One-Liner**

**Bytecode** is the platform-independent code generated by the Java compiler and executed by the JVM.

**5. JIT Compiler**

**Definition:**
The **JIT (Just-In-Time) Compiler** is a JVM component that converts frequently used **Bytecode** into **Native Machine Code** at runtime to improve performance.

**Key Features**

* Improves application speed.
* Reduces repeated interpretation of bytecode.
* Compiles frequently executed code.
* Part of the JVM **Execution Engine**.

**How It Works**

1. JVM starts by interpreting bytecode.
2. Frequently executed code is identified.
3. JIT compiles that code into machine code.
4. Future executions run faster.

**When to Use**

* Performance-critical applications.
* Long-running applications.
* High-throughput systems.

**Code Example**

```java id="dhyvgh"
public class Main {
    public static void main(String[] args) {
        for(int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }
    }
}
```

**Interview One-Liner**

The **JIT Compiler** improves performance by converting frequently executed bytecode into native machine code.

**6. Garbage Collectors**

**Definition:**
**Garbage Collectors (GCs)** are JVM components responsible for automatically removing unused objects from heap memory.

**Key Features**

* Automatic memory management.
* Prevents memory leaks.
* Reclaims unused memory.
* Improves application stability.

**Common Garbage Collectors**

1. **Serial GC**
2. **Parallel GC**
3. **G1 GC**
4. **ZGC**
5. **Shenandoah GC**

**How It Works**

1. GC identifies unreachable objects.
2. Unused objects are removed.
3. Memory is reclaimed.
4. Application continues execution.

**When to Use**

* All Java applications.
* JVM performance tuning.
* Memory optimization.

**Code Example**

```java id="uhrx3u"
String str = new String("Java");

str = null;

System.gc();
```

**Interview One-Liner**

**Garbage Collectors** automatically free heap memory by removing unused objects.

**7. JVM Memory Model**

**Definition:**
The **JVM Memory Model** defines how memory is organized and shared between threads inside the JVM.

**Key Features**

* Defines memory visibility rules.
* Supports multithreading.
* Controls data sharing between threads.
* Ensures consistency and synchronization.

**Main Memory Areas**

1. **Heap Memory**
2. **Stack Memory**
3. **Metaspace**
4. **PC Register**
5. **Native Method Stack**

**How It Works**

* Shared data is stored in **Heap Memory**.
* Each thread has its own **Stack Memory**.
* Threads read and write data according to JVM memory rules.
* Synchronization ensures data consistency.

**When to Use**

* Multithreading.
* Performance tuning.
* Understanding memory behavior.

**Code Example**

```java id="76n0qa"
class Counter {
    volatile int count = 0;
}
```

**Interview One-Liner**

The **JVM Memory Model** defines how threads interact with memory and share data safely.

**8. JVM Tuning Basics**

**Definition:**
**JVM Tuning** is the process of adjusting JVM settings to improve application **Performance**, **Memory Usage**, and **Garbage Collection Efficiency**.

**Key Features**

* Improves application responsiveness.
* Reduces GC pauses.
* Optimizes memory usage.
* Enhances throughput.

**Important JVM Parameters**

* **-Xms** : Initial Heap Size
* **-Xmx** : Maximum Heap Size
* **-XX:+UseG1GC** : Enable G1 GC
* **-XX:+UseZGC** : Enable ZGC
* **-Xss** : Thread Stack Size

**How It Works**

1. Analyze application behavior.
2. Monitor memory and GC logs.
3. Adjust heap size and GC settings.
4. Measure performance improvements.

**When to Use**

* High-memory applications.
* Performance issues.
* Frequent GC pauses.
* Production optimization.

**Code Example**

```bash id="13ft1y"
java -Xms512m -Xmx2g MyApp
```

**G1 GC Example**

```bash id="7j9g1k"
java -XX:+UseG1GC MyApp
```

**Interview One-Liner**

**JVM Tuning** optimizes memory settings and garbage collection to improve application performance.


# **11. Design Patterns**

**1. Singleton Pattern**

**Definition:**
The **Singleton Pattern** ensures that only **one instance** of a class is created and provides a global access point to that instance.

**Key Features**

* Only one object exists.
* Global access to the object.
* Saves memory.
* Commonly used for shared resources.

**How It Works**

1. Make the constructor **private**.
2. Create a static instance.
3. Provide a public method to access the instance.

**When to Use**

* Database connections.
* Configuration settings.
* Logging services.
* Caching systems.

**Code Example**

```java
public class Singleton {

    private static final Singleton instance =
            new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

**Interview One-Liner**

The **Singleton Pattern** ensures only one object of a class exists throughout the application.

**2. Factory Pattern**

**Definition:**
The **Factory Pattern** provides a way to create objects without exposing the object creation logic to the client.

**Key Features**

* Encapsulates object creation.
* Reduces tight coupling.
* Improves maintainability.
* Supports polymorphism.

**How It Works**

1. Client requests an object.
2. Factory decides which object to create.
3. Factory returns the appropriate object.

**When to Use**

* Multiple implementations of an interface.
* Dynamic object creation.
* Flexible application design.

**Code Example**

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Circle");
    }
}

class ShapeFactory {
    public Shape getShape() {
        return new Circle();
    }
}
```

**Interview One-Liner**

The **Factory Pattern** creates objects without exposing the creation logic to the client.

**3. Abstract Factory Pattern**

**Definition:**
The **Abstract Factory Pattern** provides an interface for creating **families of related objects** without specifying their concrete classes.

**Key Features**

* Creates related objects together.
* Promotes consistency.
* Hides object creation details.
* Easy to switch product families.

**How It Works**

1. Define an abstract factory.
2. Create concrete factories.
3. Each factory creates related objects.
4. Client uses the factory interface.

**When to Use**

* Cross-platform applications.
* UI component creation.
* Product families with related objects.

**Code Example**

```java
interface Button {
    void click();
}

class WindowsButton implements Button {
    public void click() {
        System.out.println("Windows Button");
    }
}

interface GUIFactory {
    Button createButton();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
}
```

**Interview One-Liner**

The **Abstract Factory Pattern** creates families of related objects through a common factory interface.

**4. Builder Pattern**

**Definition:**
The **Builder Pattern** is used to create complex objects step by step, especially when an object has many optional parameters.

**Key Features**

* Improves object creation readability.
* Handles optional parameters easily.
* Creates immutable objects.
* Avoids large constructors.

**How It Works**

1. Create a Builder class.
2. Set required and optional values.
3. Call `build()` to create the object.
4. Return the fully constructed object.

**When to Use**

* Objects with many fields.
* Immutable objects.
* Complex object creation.

**Code Example**

```java
class Employee {

    private String name;
    private int age;

    private Employee(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    static class Builder {
        private String name;
        private int age;

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setAge(int age) {
            this.age = age;
            return this;
        }

        Employee build() {
            return new Employee(this);
        }
    }
}
```

**Usage**

```java
Employee emp = new Employee.Builder()
        .setName("John")
        .setAge(30)
        .build();
```

**5. Prototype Pattern**

**Definition:**
The **Prototype Pattern** creates new objects by **cloning an existing object** instead of creating a new one from scratch.

**Key Features**

* Improves performance.
* Reduces object creation cost.
* Supports object cloning.
* Useful for complex objects.

**How It Works**

1. Create an object.
2. Implement `Cloneable`.
3. Clone the existing object.
4. Use the cloned object as a new instance.

**When to Use**

* Object creation is expensive.
* Similar objects are needed repeatedly.
* Complex object initialization.

**Code Example**

```java id="0akjlwm"
class Employee implements Cloneable {

    String name = "John";

    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }
}

Employee emp1 = new Employee();
Employee emp2 = emp1.clone();
```

**Interview One-Liner**

The **Prototype Pattern** creates new objects by cloning existing objects.

**6. Adapter Pattern**

**Definition:**
The **Adapter Pattern** allows two incompatible interfaces to work together by acting as a bridge between them.

**Key Features**

* Improves compatibility.
* Reuses existing code.
* Reduces code changes.
* Acts as a translator.

**How It Works**

1. Existing class has an incompatible interface.
2. Create an adapter class.
3. Adapter converts requests to the expected format.
4. Client uses the adapter.

**When to Use**

* Integrating third-party libraries.
* Legacy system integration.
* Interface mismatch situations.

**Code Example**

```java id="nfw7bp"
class OldPrinter {
    void printOld() {
        System.out.println("Old Printer");
    }
}

interface Printer {
    void print();
}

class PrinterAdapter implements Printer {
    private OldPrinter oldPrinter = new OldPrinter();

    public void print() {
        oldPrinter.printOld();
    }
}
```

**Interview One-Liner**

The **Adapter Pattern** allows incompatible interfaces to work together.

**7. Decorator Pattern**

**Definition:**
The **Decorator Pattern** adds new functionality to an object dynamically without modifying its existing code.

**Key Features**

* Adds behavior at runtime.
* Follows Open/Closed Principle.
* Flexible alternative to inheritance.
* Supports multiple decorators.

**How It Works**

1. Create a base interface.
2. Create a concrete implementation.
3. Create decorator classes.
4. Wrap the original object with decorators.

**When to Use**

* Adding features dynamically.
* Extending behavior without inheritance.
* UI and stream processing.

**Code Example**

```java id="wnl5k5"
interface Coffee {
    String getDescription();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Coffee";
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;

    MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }
}
```

**Usage**

```java id="pw4bvo"
Coffee coffee =
        new MilkDecorator(new SimpleCoffee());

System.out.println(coffee.getDescription());
```

**Interview One-Liner**

The **Decorator Pattern** adds new behavior to an object dynamically without changing its original code.

**8. Facade Pattern**

**Definition:**
The **Facade Pattern** provides a simplified interface to a complex subsystem, making it easier for clients to use.

**Key Features**

* Hides system complexity.
* Provides a single entry point.
* Improves code readability.
* Reduces coupling.

**How It Works**

1. Multiple subsystem classes exist.
2. Create a facade class.
3. Facade coordinates subsystem operations.
4. Client interacts only with the facade.

**When to Use**

* Complex systems.
* Third-party integrations.
* Simplifying APIs.

**Code Example**

```java id="z6w8m0"
class CPU {
    void start() {
        System.out.println("CPU Started");
    }
}

class Memory {
    void load() {
        System.out.println("Memory Loaded");
    }
}

class ComputerFacade {
    private CPU cpu = new CPU();
    private Memory memory = new Memory();

    void startComputer() {
        cpu.start();
        memory.load();
    }
}
```

**Usage**

```java id="of7cbj"
ComputerFacade computer =
        new ComputerFacade();

computer.startComputer();
```

**9. Strategy Pattern**

**Definition:**
The **Strategy Pattern** defines a family of algorithms, encapsulates each one, and allows them to be selected at runtime.

**Key Features**

* Encapsulates algorithms.
* Supports runtime behavior changes.
* Follows Open/Closed Principle.
* Reduces multiple if-else conditions.

**How It Works**

1. Create a strategy interface.
2. Implement multiple strategies.
3. Context class uses a strategy object.
4. Strategy can be changed at runtime.

**When to Use**

* Multiple algorithms for the same task.
* Dynamic business rules.
* Payment and sorting systems.

**Code Example**

```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid by Credit Card");
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void makePayment(int amount) {
        strategy.pay(amount);
    }
}
```

**Usage**

```java
PaymentContext payment =
    new PaymentContext(new CreditCardPayment());

payment.makePayment(1000);
```

**Interview One-Liner**

The **Strategy Pattern** allows switching between different algorithms at runtime.

**10. Observer Pattern**

**Definition:**
The **Observer Pattern** creates a **one-to-many relationship** where multiple objects are automatically notified when the state of another object changes.

**Key Features**

* Supports event-driven systems.
* Loose coupling between objects.
* Automatic notifications.
* Easy to add new observers.

**How It Works**

1. Subject maintains observers.
2. Observers register with the subject.
3. Subject state changes.
4. All observers are notified.

**When to Use**

* Event handling.
* Notification systems.
* Real-time updates.
* Messaging applications.

**Code Example**

```java
interface Observer {
    void update();
}

class User implements Observer {
    public void update() {
        System.out.println("Notification Received");
    }
}
```

**Interview One-Liner**

The **Observer Pattern** automatically notifies dependent objects when a state changes.

**11. Template Method Pattern**

**Definition:**
The **Template Method Pattern** defines the skeleton of an algorithm in a parent class while allowing subclasses to customize specific steps.

**Key Features**

* Reuses common logic.
* Promotes code consistency.
* Supports customization.
* Reduces code duplication.

**How It Works**

1. Parent class defines the workflow.
2. Some methods are implemented in subclasses.
3. Algorithm structure remains unchanged.
4. Subclasses customize specific steps.

**When to Use**

* Common processing workflows.
* Report generation.
* Data processing pipelines.

**Code Example**

```java
abstract class DataProcessor {

    final void process() {
        readData();
        processData();
    }

    abstract void readData();

    abstract void processData();
}

class CSVProcessor extends DataProcessor {

    void readData() {
        System.out.println("Reading CSV");
    }

    void processData() {
        System.out.println("Processing CSV");
    }
}
```

**Interview One-Liner**

The **Template Method Pattern** defines the overall algorithm while allowing subclasses to customize specific steps.

**12. Chain of Responsibility Pattern**

**Definition:**
The **Chain of Responsibility Pattern** passes a request through a chain of handlers until one of them processes it.

**Key Features**

* Decouples sender and receiver.
* Flexible request processing.
* Easy to add new handlers.
* Supports multiple processing levels.

**How It Works**

1. Request is sent to the first handler.
2. Handler processes it or forwards it.
3. Request moves through the chain.
4. One handler eventually handles it.

**When to Use**

* Approval workflows.
* Request validation.
* Logging frameworks.
* Authentication and authorization.

**Code Example**

```java
abstract class Handler {

    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract void handle(String request);
}

class Manager extends Handler {

    void handle(String request) {
        if ("Leave".equals(request))
            System.out.println("Approved by Manager");
        else if (next != null)
            next.handle(request);
    }
}
```

**Usage**

```java
Manager manager = new Manager();

manager.handle("Leave");
```



# **12. SOLID Principles**

**1. Single Responsibility Principle (SRP)**

**Definition:**
A class should have **only one responsibility** and **one reason to change**.

**Key Features**

* High cohesion.
* Easy to maintain.
* Easy to test.
* Reduces code complexity.

**How It Works**

* Each class handles only one specific task.
* Business logic and utility logic are separated.
* Changes in one responsibility do not affect others.

**When to Use**

* Every class design.
* Large applications.
* Maintainable codebases.

**Bad Example**

```java id="m8fd8w"
class Employee {

    void calculateSalary() {}

    void saveToDatabase() {}
}
```

**Good Example**

```java id="37u8v3"
class Employee {
    void calculateSalary() {}
}

class EmployeeRepository {
    void save(Employee emp) {}
}
```

**Interview One-Liner**

A class should have **one responsibility and one reason to change**.

**2. Open Closed Principle (OCP)**

**Definition:**
Software entities should be **open for extension** but **closed for modification**.

**Key Features**

* Easy to add new features.
* Existing code remains unchanged.
* Reduces risk of bugs.
* Promotes extensibility.

**How It Works**

* Use interfaces or abstract classes.
* Add new implementations instead of modifying existing code.
* Extend behavior through inheritance or composition.

**When to Use**

* Frequently changing business requirements.
* Plugin architectures.
* Scalable applications.

**Code Example**

```java id="w9v13l"
interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Credit Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI");
    }
}
```

**Interview One-Liner**

Classes should be **extendable without modifying existing code**.

**3. Liskov Substitution Principle (LSP)**

**Definition:**
A subclass should be able to **replace its parent class** without breaking application behavior.

**Key Features**

* Proper inheritance.
* Maintains expected behavior.
* Improves reusability.
* Prevents runtime issues.

**How It Works**

* Child classes must follow parent class behavior.
* Clients should not know whether they are using parent or child objects.

**When to Use**

* Inheritance hierarchies.
* Framework design.
* Polymorphic behavior.

**Bad Example**

```java id="nd1b7q"
class Bird {
    void fly() {}
}

class Ostrich extends Bird {
    void fly() {
        throw new UnsupportedOperationException();
    }
}
```

**Good Example**

```java id="az4bke"
interface Bird {}

interface FlyingBird extends Bird {
    void fly();
}
```

**Interview One-Liner**

A child class should be usable anywhere its parent class is expected.

**4. Interface Segregation Principle (ISP)**

**Definition:**
Clients should not be forced to depend on methods they do not use.

**Key Features**

* Small focused interfaces.
* Better maintainability.
* Reduces unnecessary implementation.
* Improves flexibility.

**How It Works**

* Split large interfaces into smaller ones.
* Classes implement only required interfaces.
* Avoids unused methods.

**When to Use**

* Large interfaces.
* Multiple implementations.
* API design.

**Bad Example**

```java id="7v1xzc"
interface Worker {
    void work();
    void eat();
}
```

**Good Example**

```java id="xslxvx"
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}
```

**Interview One-Liner**

Create **small, specific interfaces** instead of one large interface.

**5. Dependency Inversion Principle (DIP)**

**Definition:**
High-level modules should depend on **abstractions**, not on concrete implementations.

**Key Features**

* Loose coupling.
* Easy testing.
* Flexible design.
* Supports Dependency Injection.

**How It Works**

* Create interfaces.
* Depend on interfaces instead of concrete classes.
* Inject implementations at runtime.

**When to Use**

* Spring applications.
* Service-based architectures.
* Unit testing.

**Bad Example**

```java id="4w24u2"
class MySQLDatabase {}

class UserService {
    private MySQLDatabase db =
            new MySQLDatabase();
}
```

**Good Example**

```java id="m4tw3x"
interface Database {}

class MySQLDatabase implements Database {}

class UserService {

    private Database database;

    UserService(Database database) {
        this.database = database;
    }
}
```


| Principle | Main Idea                                   |
| --------- | ------------------------------------------- |
| **SRP**   | One class, one responsibility               |
| **OCP**   | Open for extension, closed for modification |
| **LSP**   | Child class must replace parent safely      |
| **ISP**   | Prefer small, focused interfaces            |
| **DIP**   | Depend on abstractions, not implementations |


# **13. Generics**

**1. Generic Classes**

**Definition:**
A **Generic Class** allows a class to work with different data types while maintaining **Type Safety**.

**Key Features**

* Reusable code.
* Type-safe.
* Reduces type casting.
* Improves code readability.

**How It Works**

* Use a type parameter such as `<T>`.
* Actual data type is provided when creating the object.
* Same class works with multiple data types.

**When to Use**

* Custom collections.
* Reusable utility classes.
* Data containers.

**Code Example**

```java
class Box<T> {

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

Box<String> box = new Box<>();
box.set("Java");
```

**Interview One-Liner**

A **Generic Class** allows one class to work with multiple data types safely.

**2. Generic Methods**

**Definition:**
A **Generic Method** is a method that can work with different data types independently of the class type.

**Key Features**

* Flexible.
* Reusable.
* Type-safe.
* Works with multiple data types.

**How It Works**

* Declare a type parameter before the return type.
* Method accepts different data types.
* Compiler ensures type safety.

**When to Use**

* Utility methods.
* Common operations for multiple data types.
* Generic processing logic.

**Code Example**

```java
class Utility {

    public static <T> void print(T value) {
        System.out.println(value);
    }
}

Utility.print("Java");
Utility.print(100);
```

**Interview One-Liner**

A **Generic Method** can process different data types using a single method implementation.

**3. Wildcards**

**Definition:**
A **Wildcard (`?`)** represents an unknown type and provides flexibility when working with generic collections.

**Key Features**

* Supports flexible type handling.
* Improves reusability.
* Useful with collections.
* Enables generic APIs.

**Types of Wildcards**

* `<?>` → Unbounded Wildcard
* `<? extends T>` → Upper Bounded Wildcard
* `<? super T>` → Lower Bounded Wildcard

**How It Works**

* Accepts multiple generic types.
* Compiler determines valid operations.
* Helps create flexible methods.

**When to Use**

* Generic collection processing.
* Framework development.
* Flexible APIs.

**Code Example**

```java
import java.util.List;

public void display(List<?> list) {
    for (Object obj : list) {
        System.out.println(obj);
    }
}
```

**Interview One-Liner**

A **Wildcard** represents an unknown type and provides flexibility in generic code.

**4. Bounded Types**

**Definition:**
**Bounded Types** restrict the types that can be used as generic parameters.

**Key Features**

* Adds type constraints.
* Improves type safety.
* Restricts invalid types.
* Enables access to specific methods.

**How It Works**

* Use `extends` keyword.
* Only specified type or its subclasses are allowed.

**When to Use**

* Mathematical operations.
* Type-restricted processing.
* Generic frameworks.

**Code Example**

```java
class Calculator<T extends Number> {

    public void display(T value) {
        System.out.println(value);
    }
}
```

**Interview One-Liner**

**Bounded Types** restrict generic parameters to specific classes or interfaces.

**5. Type Erasure**

**Definition:**
**Type Erasure** is the process where generic type information is removed by the compiler during compilation.

**Key Features**

* Generics exist only at compile time.
* No generic type information at runtime.
* Maintains backward compatibility.
* Implemented by the compiler.

**How It Works**

1. Compiler checks generic types.
2. Generic information is removed.
3. Type parameters are replaced with `Object` or bounded type.
4. Generated bytecode contains no generic information.

**When to Use Knowledge**

* Understanding generics internals.
* Reflection-related discussions.
* Advanced Java interviews.

**Code Example**

```java
List<String> names = new ArrayList<>();
```

**After Type Erasure**

```java
List names = new ArrayList();
```

**Interview One-Liner**

**Type Erasure** removes generic type information during compilation to maintain backward compatibility.

**6. Generic Collections**

**Definition:**
**Generic Collections** use generics to store only specific data types, providing type safety.

**Key Features**

* Prevents runtime type errors.
* Eliminates explicit casting.
* Improves readability.
* Type-safe collections.

**How It Works**

* Specify the data type while creating a collection.
* Compiler checks inserted elements.
* Invalid types are rejected.

**When to Use**

* Lists, Sets, Maps, Queues.
* Type-safe data storage.
* Collection-based applications.

**Code Example**

```java
import java.util.*;

List<String> names = new ArrayList<>();

names.add("Java");
names.add("Spring");

String name = names.get(0);
```


# **14. Annotations & Reflection**

**1. Built-in Annotations**

**Definition:**
**Built-in Annotations** are predefined annotations provided by Java to give metadata and instructions to the compiler or JVM.

**Key Features**

* Improves code readability.
* Helps compiler validation.
* Reduces coding errors.
* Built into Java.

**Common Built-in Annotations**

* `@Override`
* `@Deprecated`
* `@SuppressWarnings`
* `@FunctionalInterface`

**How It Works**

* Annotation is added to code.
* Compiler or JVM reads it.
* Specific behavior or validation is applied.

**When to Use**

* Method overriding.
* Marking deprecated code.
* Suppressing compiler warnings.
* Functional interfaces.

**Code Example**

```java id="h7x3ik"
class Parent {
    void display() {}
}

class Child extends Parent {

    @Override
    void display() {
        System.out.println("Overridden");
    }
}
```

**Interview One-Liner**

**Built-in Annotations** provide metadata and instructions to the compiler and JVM.

**2. Custom Annotations**

**Definition:**
A **Custom Annotation** is a user-defined annotation used to add custom metadata to classes, methods, or fields.

**Key Features**

* User-defined.
* Adds custom metadata.
* Can be processed at runtime.
* Commonly used in frameworks.

**How It Works**

1. Create annotation using `@interface`.
2. Apply it to classes or methods.
3. Read it using Reflection.

**When to Use**

* Custom validations.
* Framework development.
* Configuration metadata.
* Security rules.

**Code Example**

```java id="7k7gkg"
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
}

@Author(name = "John")
class Employee {}
```

**Interview One-Liner**

A **Custom Annotation** is a user-created annotation used to attach custom metadata to code.

**3. Reflection API**

**Definition:**
The **Reflection API** allows Java programs to inspect and manipulate classes, methods, fields, and constructors at runtime.

**Key Features**

* Runtime inspection.
* Dynamic object creation.
* Access private members.
* Used by many frameworks.

**How It Works**

1. Obtain `Class` object.
2. Read methods, fields, and constructors.
3. Invoke methods dynamically.
4. Access metadata at runtime.

**When to Use**

* Framework development.
* Dependency Injection.
* Testing frameworks.
* Annotation processing.

**Code Example**

```java id="txo8es"
Class<?> cls = String.class;

System.out.println(cls.getName());
```

**Interview One-Liner**

The **Reflection API** allows inspection and manipulation of classes and objects at runtime.

**4. Dynamic Class Loading**

**Definition:**
**Dynamic Class Loading** is the process of loading classes into memory during runtime instead of compile time.

**Key Features**

* Loads classes on demand.
* Reduces startup time.
* Supports plugins and modules.
* Uses Class Loader internally.

**How It Works**

1. Class name is provided at runtime.
2. JVM loads the class dynamically.
3. Object is created if required.
4. Class becomes available for use.

**When to Use**

* Plugin architectures.
* Frameworks.
* Modular applications.
* Dynamic configurations.

**Code Example**

```java id="38q8nq"
Class<?> cls =
        Class.forName("java.lang.String");

System.out.println(cls.getName());
```

**Interview One-Liner**

**Dynamic Class Loading** loads classes into JVM memory during runtime when needed.

**5. Runtime Processing**

**Definition:**
**Runtime Processing** refers to reading and processing metadata, annotations, or class information while the application is running.

**Key Features**

* Works during execution.
* Uses Reflection API.
* Supports dynamic behavior.
* Common in Spring and Hibernate.

**How It Works**

1. JVM loads classes.
2. Reflection reads annotations or metadata.
3. Framework processes information.
4. Behavior is applied dynamically.

**When to Use**

* Dependency Injection.
* Annotation-based configuration.
* Validation frameworks.
* ORM frameworks.

**Code Example**

```java id="gg7vcc"
if (Employee.class.isAnnotationPresent(Author.class)) {
    Author author =
        Employee.class.getAnnotation(Author.class);

    System.out.println(author.name());
}
```


# **15. File Handling**

**1. File Class**

**Definition:**
**File Class** (`java.io.File`) is used to **create, delete, rename, and get information** about files and directories. It does **not read or write data**.

**Key Features:**

* Represents **files and folders**
* Create and delete files
* Check file existence
* Get file path, name, and size

**How it Works:**

* Create a `File` object
* Perform file operations using built-in methods

**When to Use:**

* When you need to **manage files or directories**
* Before reading or writing files

**Code Example:**

```java
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("test.txt");

        if (file.createNewFile()) {
            System.out.println("File Created");
        }

        System.out.println(file.exists());
    }
}
```

---

**2. BufferedReader**

**Definition:**
**BufferedReader** is used to **read text efficiently** from a file by reading data in **chunks (buffer)** instead of character by character.

**Key Features:**

* Faster file reading
* Supports `readLine()`
* Reduces disk access operations

**How it Works:**

* Wraps around `FileReader`
* Stores data in a buffer
* Reads line by line

**When to Use:**

* Reading large text files
* When better performance is required

**Code Example:**

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new FileReader("test.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
}
```

---

**3. BufferedWriter**

**Definition:**
**BufferedWriter** is used to **write text efficiently** to a file using an internal **buffer**.

**Key Features:**

* Faster file writing
* Reduces disk I/O operations
* Supports `newLine()`

**How it Works:**

* Wraps around `FileWriter`
* Stores data temporarily in memory
* Writes data to file in batches

**When to Use:**

* Writing large amounts of text
* Improving file-writing performance

**Code Example:**

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw =
                new BufferedWriter(new FileWriter("test.txt"));

        bw.write("Hello Java");
        bw.newLine();
        bw.write("BufferedWriter Example");

        bw.close();
    }
}
```

---

**4. Serialization**

**Definition:**
**Serialization** is the process of converting an **object into a byte stream** so it can be stored in a file or transferred over a network.

**Key Features:**

* Saves object state
* Supports object transfer
* Uses `Serializable` interface

**How it Works:**

1. Class implements **Serializable**
2. Object is converted into bytes using **ObjectOutputStream**
3. Object can be restored using **ObjectInputStream** (**Deserialization**)

**When to Use:**

* Saving objects to files
* Sending objects across networks
* Caching object data

**Code Example:**

```java
import java.io.*;

class Student implements Serializable {
    String name = "John";
}

public class Main {
    public static void main(String[] args) throws Exception {
        Student s = new Student();

        ObjectOutputStream out =
                new ObjectOutputStream(
                        new FileOutputStream("student.ser"));

        out.writeObject(s);
        out.close();

        System.out.println("Object Serialized");
    }
}
```

**5. Deserialization**

**Definition:**
**Deserialization** is the process of converting a **byte stream back into an object**.

**Key Features:**

* Restores object state
* Opposite of **Serialization**
* Uses **ObjectInputStream**
* Requires the class to implement **Serializable**

**How it Works:**

1. Read bytes from a file or network
2. Use **ObjectInputStream**
3. Convert bytes back into an object

**When to Use:**

* Loading saved objects from files
* Receiving objects over a network
* Restoring application state

**Code Example:**

```java
import java.io.*;

class Student implements Serializable {
    String name = "John";
}

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectInputStream in =
                new ObjectInputStream(
                        new FileInputStream("student.ser"));

        Student s = (Student) in.readObject();

        System.out.println(s.name);

        in.close();
    }
}
```

**Interview One-Liner:**
**Deserialization** converts a **byte stream back into an object**.

**6. NIO (New I/O)**

**Definition:**
**NIO (New Input/Output)** is a modern Java API introduced to provide **faster and more scalable file and network operations** than traditional I/O.

**Key Features:**

* High-performance I/O
* Supports **Channels** and **Buffers**
* Non-blocking operations
* Better file handling
* Suitable for large applications

**How it Works:**

* Data is read into a **Buffer**
* A **Channel** transfers data between files, sockets, and buffers

**When to Use:**

* Large file processing
* High-performance applications
* Network programming
* Non-blocking operations

**Code Example:**

```java
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(
                Paths.get("test.txt"));

        System.out.println(new String(data));
    }
}
```

**Interview One-Liner:**
**NIO** provides **faster, scalable, and non-blocking I/O operations** using **Channels** and **Buffers**.

**7. Path & Files API**

**Definition:**
**Path** and **Files** API (introduced in Java 7) provide a modern and easy way to **work with files and directories**.

**Key Features:**

* Cleaner than `File` class
* Create, copy, move, and delete files
* Read and write files easily
* Better exception handling

**How it Works:**

* **Path** represents a file or directory path
* **Files** provides utility methods to perform operations on that path

**When to Use:**

* Modern file handling in Java
* Reading and writing files
* Managing directories

**Code Example:**

```java
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Path path = Paths.get("test.txt");

        Files.writeString(path, "Hello Java");

        String content = Files.readString(path);

        System.out.println(content);
    }
}
```


# **16. Java Interview Coding Topics**

**1. String Programs**

**Definition:**
**String Programs** are common coding problems that involve manipulating and processing **String** data.

**Key Features:**

* String reversal
* Palindrome check
* Count characters
* Remove duplicates
* Find anagrams

**How it Works:**

* Use loops, String methods, or character arrays
* Process each character based on the requirement

**When to Use:**

* Text processing
* Input validation
* Coding interviews

**Code Example (Reverse String):**

```java id="lhg6v4"
public class Main {
    public static void main(String[] args) {
        String str = "Java";
        String rev = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            rev += str.charAt(i);
        }

        System.out.println(rev);
    }
}
```

**Interview One-Liner:**
**String Programs** test your understanding of **loops, conditions, and String manipulation techniques**.

**2. Array Programs**

**Definition:**
**Array Programs** involve storing and processing multiple values of the same type using an **Array**.

**Key Features:**

* Searching
* Sorting
* Finding maximum/minimum
* Removing duplicates
* Array rotation

**How it Works:**

* Traverse array using loops
* Perform required operations on elements

**When to Use:**

* Storing fixed-size data
* Fast access using index
* Problem-solving and interviews

**Code Example (Find Maximum):**

```java id="dhs4g0"
public class Main {
    public static void main(String[] args) {

        int[] arr = {10, 20, 5, 40};

        int max = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        System.out.println(max);
    }
}
```

**Interview One-Liner:**
**Arrays** store multiple values in **contiguous memory locations** and provide **fast index-based access**.

**3. Linked List**

**Definition:**
A **Linked List** is a linear data structure where each element (**Node**) contains **data** and a reference to the **next node**.

**Key Features:**

* Dynamic size
* Easy insertion and deletion
* Sequential access
* No contiguous memory required

**How it Works:**

* Each node points to the next node
* Traversal starts from the **head node**

**When to Use:**

* Frequent insertions and deletions
* Dynamic data storage
* Implementing stacks and queues

**Code Example:**

```java id="cezk6x"
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class Main {
    public static void main(String[] args) {

        Node first = new Node(10);
        Node second = new Node(20);

        first.next = second;

        System.out.println(first.data);
        System.out.println(first.next.data);
    }
}
```

**Interview One-Liner:**
A **Linked List** stores data in **nodes connected through references**, allowing efficient insertions and deletions.

**4. Stack**

**Definition:**
A **Stack** is a linear data structure that follows the **LIFO (Last In, First Out)** principle.

**Key Features:**

* LIFO order
* `push()` for insertion
* `pop()` for deletion
* `peek()` for viewing top element

**How it Works:**

* New elements are added to the top
* Last inserted element is removed first

**When to Use:**

* Undo operations
* Function call management
* Expression evaluation

**Code Example:**

```java id="xwyv7o"
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);

        System.out.println(stack.pop());
    }
}
```

**Interview One-Liner:**
A **Stack** follows the **LIFO** principle where the **last inserted element is removed first**.

**5. Queue**

**Definition:**
A **Queue** is a linear data structure that follows the **FIFO (First In, First Out)** principle.

**Key Features:**

* FIFO order
* `offer()` for insertion
* `poll()` for deletion
* `peek()` for viewing front element

**How it Works:**

* Elements are added at the rear
* Elements are removed from the front

**When to Use:**

* Task scheduling
* Message processing
* Printer queue systems

**Code Example:**

```java id="4wt8zn"
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(10);
        queue.offer(20);

        System.out.println(queue.poll());
    }
}
```

**6. Binary Search**

**Definition:**
**Binary Search** is a searching algorithm that finds an element in a **sorted array** by repeatedly dividing the search space into two halves.

**Key Features:**

* Works only on **sorted data**
* Very fast search
* Time Complexity: **O(log n)**
* Divide and conquer approach

**How it Works:**

1. Find the middle element
2. Compare with target
3. Search left or right half
4. Repeat until found

**When to Use:**

* Large sorted datasets
* Fast searching requirements

**Code Example:**

```java id="7thw9y"
public class Main {
    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50};
        int target = 30;

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                System.out.println("Found");
                break;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
```

**Interview One-Liner:**
**Binary Search** finds an element in a **sorted array** with **O(log n)** time complexity.

**7. Sorting Algorithms**

**Definition:**
**Sorting Algorithms** arrange data in a specific order such as **ascending** or **descending**.

**Key Features:**

* Organizes data efficiently
* Improves searching performance
* Common algorithms: Bubble, Selection, Insertion, Merge, Quick Sort

**How it Works:**

* Compare elements
* Swap or reposition them until sorted

**When to Use:**

* Data organization
* Searching and reporting
* Performance optimization

**Code Example (Bubble Sort):**

```java id="g7r1xv"
public class Main {
    public static void main(String[] args) {

        int[] arr = {5, 3, 8, 1};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
```

**Interview One-Liner:**
**Sorting Algorithms** arrange data in a required order to improve processing and searching efficiency.

**8. Recursion**

**Definition:**
**Recursion** is a technique where a method **calls itself** until a **base condition** is reached.

**Key Features:**

* Self-calling method
* Requires a base case
* Simplifies complex problems
* Uses call stack internally

**How it Works:**

1. Method calls itself
2. Problem size reduces
3. Stops at base condition

**When to Use:**

* Tree traversal
* Factorial calculation
* Divide and conquer problems

**Code Example:**

```java id="0vgy3u"
public class Main {

    static int factorial(int n) {
        if (n == 1)
            return 1;

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
```

**Interview One-Liner:**
**Recursion** is a process where a method **calls itself repeatedly until a base condition is met**.

**9. Hashing**

**Definition:**
**Hashing** is a technique used to store and retrieve data quickly using a **hash function**.

**Key Features:**

* Fast lookup
* Key-value storage
* Average Time Complexity: **O(1)**
* Used in HashMap and HashSet

**How it Works:**

* Key is converted into a hash value
* Hash value determines storage location
* Data is retrieved using the same key

**When to Use:**

* Fast searching
* Caching
* Data indexing

**Code Example:**

```java id="xjlwm6"
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map.get(1));
    }
}
```

**Interview One-Liner:**
**Hashing** provides **near O(1)** insertion, deletion, and retrieval using a **hash function**.

**10. Tree Traversal**

**Definition:**
**Tree Traversal** is the process of visiting every node in a tree exactly once.

**Key Features:**

* Hierarchical data processing
* Traverses all nodes
* Types: **Preorder, Inorder, Postorder**

**How it Works:**

* Visit nodes in a specific order
* Usually implemented using recursion

**When to Use:**

* Binary Trees
* Search Trees
* Expression Trees

**Code Example (Inorder Traversal):**

```java id="m1vhj2"
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

public class Main {

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);

        inorder(root);
    }
}
```

**Interview One-Liner:**
**Tree Traversal** is the process of visiting all nodes of a tree in a specific order such as **Preorder, Inorder, or Postorder**.

**11. Dynamic Programming Basics**

**Definition:**
**Dynamic Programming (DP)** is an optimization technique used to solve problems by storing results of previously solved subproblems.

**Key Features:**

* Avoids repeated calculations
* Improves performance
* Uses **Memoization** or **Tabulation**
* Suitable for optimization problems

**How it Works:**

1. Break problem into smaller subproblems
2. Store computed results
3. Reuse stored values when needed

**When to Use:**

* Fibonacci series
* Knapsack problem
* Longest Common Subsequence
* Optimization problems

**Code Example (Fibonacci using DP):**

```java id="t1jlwm"
public class Main {
    public static void main(String[] args) {

        int n = 6;
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }
}
```


# **17. Functional Programming**

**1. Functional Interfaces**

**Definition:**
A **Functional Interface** is an interface that contains **exactly one abstract method**. It is the foundation of **Lambda Expressions**.

**Key Features:**

* Only one abstract method
* Can have default and static methods
* Annotated with **@FunctionalInterface**
* Enables functional programming

**How it Works:**

* Define a single abstract method
* Implement it using a lambda expression

**When to Use:**

* Lambda expressions
* Method references
* Stream operations

**Code Example:**

```java id="8p3qkx"
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {

        Greeting g = () -> System.out.println("Hello");

        g.sayHello();
    }
}
```

**Interview One-Liner:**
A **Functional Interface** contains **one abstract method** and is mainly used with **Lambda Expressions**.

**2. Lambda Expressions**

**Definition:**
A **Lambda Expression** is a concise way to implement a **Functional Interface** without creating a separate class.

**Key Features:**

* Less boilerplate code
* Improves readability
* Supports functional programming
* Introduced in Java 8

**How it Works:**

* Uses the syntax: `(parameters) -> expression`
* Provides implementation of a functional interface method

**When to Use:**

* Streams
* Collections processing
* Event handling

**Code Example:**

```java id="y6b9dw"
interface Calculator {
    int add(int a, int b);
}

public class Main {
    public static void main(String[] args) {

        Calculator c = (a, b) -> a + b;

        System.out.println(c.add(10, 20));
    }
}
```

**Interview One-Liner:**
A **Lambda Expression** provides a short and clean way to implement a **Functional Interface**.

**3. Streams**

**Definition:**
A **Stream** is a sequence of elements that supports **functional-style operations** such as filtering, mapping, and sorting.

**Key Features:**

* Process collections efficiently
* Supports functional programming
* Internal iteration
* Can be parallelized

**How it Works:**

* Create a stream from a collection
* Apply operations like `filter()`, `map()`, `collect()`
* Produce a result

**When to Use:**

* Data filtering
* Data transformation
* Collection processing

**Code Example:**

```java id="g4k7rt"
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> nums = List.of(10, 20, 30, 40);

        nums.stream()
            .filter(n -> n > 20)
            .forEach(System.out::println);
    }
}
```

**Interview One-Liner:**
A **Stream** processes collection data using **functional operations** without modifying the original collection.

**4. Optional**

**Definition:**
**Optional** is a container object used to represent the presence or absence of a value, helping avoid **NullPointerException**.

**Key Features:**

* Avoids null checks
* Improves code readability
* Provides utility methods
* Introduced in Java 8

**How it Works:**

* Wrap value inside `Optional`
* Use methods like `isPresent()`, `orElse()`, `ifPresent()`

**When to Use:**

* Returning nullable values
* Preventing NullPointerException

**Code Example:**

```java id="6jx2cp"
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Optional<String> name =
                Optional.ofNullable(null);

        System.out.println(
                name.orElse("Default Name"));
    }
}
```

**Interview One-Liner:**
**Optional** helps handle missing values safely and reduces the risk of **NullPointerException**.

**5. Immutability**

**Definition:**
An **Immutable Object** is an object whose state cannot be changed after it is created.

**Key Features:**

* Thread-safe
* More secure
* Easier to maintain
* Object state never changes

**How it Works:**

* Make class `final`
* Make fields `private final`
* Do not provide setters

**When to Use:**

* Shared objects
* Multithreaded applications
* Configuration objects

**Code Example:**

```java id="0h3rnp"
final class Employee {

    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

**Interview One-Liner:**
An **Immutable Object** cannot be modified after creation, making it **safe and thread-friendly**.

**6. Higher Order Functions**

**Definition:**
A **Higher Order Function** is a function that **accepts another function as a parameter** or **returns a function**.

**Key Features:**

* Supports functional programming
* Promotes code reusability
* Works with Lambda Expressions
* Uses functional interfaces

**How it Works:**

* Pass lambda expressions as arguments
* Execute behavior dynamically

**When to Use:**

* Stream operations
* Reusable business logic
* Functional programming

**Code Example:**

```java id="6uy9aw"
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Function<Integer, Integer> square =
                n -> n * n;

        System.out.println(square.apply(5));
    }
}
```

# **18. Java 11 / 17 / 21 Features**

1. var Keyword
2. New String Methods
3. Records
4. Sealed Classes
5. Pattern Matching
6. Switch Expressions
7. Virtual Threads
8. Structured Concurrency

# **19. Microservices-Oriented Java Concepts**

**1. DTO (Data Transfer Object)**

**Definition:**
A **DTO (Data Transfer Object)** is an object used to **transfer data between layers** of an application without containing business logic.

**Key Features:**

* Transfers data only
* No business logic
* Improves security
* Reduces unnecessary data exposure

**How it Works:**

* Receives data from client/API
* Transfers data between Controller, Service, and other layers
* Converts to/from Entity objects

**When to Use:**

* API request and response objects
* Layer-to-layer communication
* Hiding sensitive Entity fields

**Code Example:**

```java id="d2k9mw"
public class EmployeeDTO {

    private Long id;
    private String name;

    public EmployeeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

**Interview One-Liner:**
A **DTO** is used to **transfer data between application layers** without exposing internal database entities.

**2. Entity**

**Definition:**
An **Entity** is a class that represents a **database table** and is managed by JPA/Hibernate.

**Key Features:**

* Maps to database table
* Contains persistent data
* Managed by JPA/Hibernate
* Uses `@Entity`

**How it Works:**

* Each object represents a database row
* Fields map to table columns
* JPA performs CRUD operations

**When to Use:**

* Database interaction
* ORM mapping
* Persistent storage

**Code Example:**

```java id="r5z8xc"
import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    private Long id;

    private String name;
}
```

**Interview One-Liner:**
An **Entity** is a Java class that represents a **database table** and is managed by JPA/Hibernate.

**3. Bean**

**Definition:**
A **Bean** is an object whose lifecycle is managed by the **Spring IoC Container**.

**Key Features:**

* Managed by Spring
* Supports Dependency Injection
* Singleton by default
* Created automatically by Spring

**How it Works:**

* Spring scans configuration
* Creates Bean objects
* Injects dependencies where needed

**When to Use:**

* Service classes
* Repository classes
* Configuration classes

**Code Example:**

```java id="u7m4pd"
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public void display() {
        System.out.println("Service Bean");
    }
}
```

**Interview One-Liner:**
A **Bean** is an object that is **created, managed, and injected by the Spring Container**.

**4. POJO (Plain Old Java Object)**

**Definition:**
A **POJO** is a simple Java object that is **not dependent on any framework or special inheritance**.

**Key Features:**

* Simple Java class
* Private fields
* Getters and setters
* No framework dependency

**How it Works:**

* Stores data using fields
* Accessed through methods

**When to Use:**

* Data models
* Business objects
* General Java applications

**Code Example:**

```java id="x8f2jl"
public class Employee {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
```

**Interview One-Liner:**
A **POJO** is a simple Java object that does not require any special framework-specific rules.

**5. Dependency Injection (DI)**

**Definition:**
**Dependency Injection** is a design pattern where dependencies are **provided by the Spring Container** instead of being created manually.

**Key Features:**

* Loose coupling
* Better testability
* Easier maintenance
* Managed by Spring IoC

**How it Works:**

1. Spring creates required Beans
2. Injects dependencies automatically
3. Class uses injected objects

**When to Use:**

* Spring applications
* Service-to-service communication
* Large scalable applications

**Code Example:**

```java id="m4t7qw"
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;
}
```

**6. Serialization**

**Definition:**
**Serialization** is the process of converting a Java **object into a byte stream** so it can be stored in a file, database, or sent over a network.

**Key Features:**

* Saves object state
* Supports object transfer
* Uses `Serializable` interface
* Enables persistence

**How it Works:**

1. Class implements **Serializable**
2. Object is converted into bytes
3. Bytes are stored or transmitted

**When to Use:**

* Saving objects to files
* Caching data
* Sending objects across networks

**Code Example:**

```java id="k8v4qn"
import java.io.*;

class Employee implements Serializable {
    String name = "John";
}

public class Main {
    public static void main(String[] args) throws Exception {

        Employee emp = new Employee();

        ObjectOutputStream out =
                new ObjectOutputStream(
                        new FileOutputStream("emp.ser"));

        out.writeObject(emp);
        out.close();
    }
}
```

**Interview One-Liner:**
**Serialization** converts an **object into a byte stream** for storage or transmission.

**7. Deserialization**

**Definition:**
**Deserialization** is the process of converting a **byte stream back into an object**.

**Key Features:**

* Restores object state
* Opposite of Serialization
* Uses `ObjectInputStream`
* Retrieves stored objects

**How it Works:**

1. Read bytes from file or network
2. Use `ObjectInputStream`
3. Recreate the original object

**When to Use:**

* Loading saved objects
* Receiving objects from network
* Restoring application state

**Code Example:**

```java id="f3r8xm"
import java.io.*;

class Employee implements Serializable {
    String name = "John";
}

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectInputStream in =
                new ObjectInputStream(
                        new FileInputStream("emp.ser"));

        Employee emp =
                (Employee) in.readObject();

        System.out.println(emp.name);

        in.close();
    }
}
```

**Interview One-Liner:**
**Deserialization** converts a **byte stream back into an object**.

**8. API Design**

**Definition:**
**API Design** is the process of creating APIs that are **easy to understand, maintain, and use**.

**Key Features:**

* Consistent naming
* Clear request and response structure
* Proper HTTP methods
* Versioning support
* Error handling

**How it Works:**

* Define resources
* Create endpoints
* Use standard HTTP methods
* Return meaningful responses

**When to Use:**

* Building REST APIs
* Microservices
* Public or internal services

**Code Example:**

```java id="z6n2pw"
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public Employee getEmployee(
            @PathVariable Long id) {
        return new Employee();
    }
}
```

**Good API Design Example:**

```text
GET    /employees
GET    /employees/1
POST   /employees
PUT    /employees/1
DELETE /employees/1
```

**Interview One-Liner:**
**API Design** focuses on creating **simple, consistent, scalable, and user-friendly APIs**.

**9. REST Principles**

**Definition:**
**REST (Representational State Transfer)** is an architectural style for designing web services using standard HTTP protocols.

**Key Features:**

* Client-Server architecture
* Stateless communication
* Resource-based URLs
* Uses HTTP methods
* Supports multiple formats like JSON

**How it Works:**

* Resources are identified using URLs
* HTTP methods perform operations on resources
* Server returns representations such as JSON

**When to Use:**

* Web services
* Microservices
* Mobile and web applications

**Code Example:**

```java id="x9w5jl"
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    public String getEmployees() {
        return "Employee List";
    }
}
```

**Common HTTP Methods:**

| Method     | Operation              |
| ---------- | ---------------------- |
| **GET**    | Read Data              |
| **POST**   | Create Data            |
| **PUT**    | Update Entire Resource |
| **PATCH**  | Partial Update         |
| **DELETE** | Delete Data            |



# **20. Java Best Practices**

**1. Immutable Objects**

**Definition:**
An **Immutable Object** is an object whose state **cannot be changed after creation**.

**Key Features:**

* Thread-safe
* Secure and reliable
* No state modification
* Easier maintenance

**How it Works:**

* Make class `final`
* Make fields `private final`
* Initialize fields through constructor
* Do not provide setters

**When to Use:**

* Multithreaded applications
* Configuration objects
* Shared data

**Code Example:**

```java id="h2m7qp"
final class Employee {

    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

**Interview One-Liner:**
An **Immutable Object** cannot be modified after creation, making it **safe for concurrent use**.

**2. Defensive Copying**

**Definition:**
**Defensive Copying** creates a copy of mutable objects to prevent external code from modifying internal data.

**Key Features:**

* Protects object state
* Improves immutability
* Prevents unintended modifications
* Enhances security

**How it Works:**

* Return copies instead of original objects
* Store copies in constructors

**When to Use:**

* Immutable classes
* Shared mutable objects
* Sensitive data handling

**Code Example:**

```java id="v8n3tx"
import java.util.*;

class Employee {

    private final List<String> skills;

    public Employee(List<String> skills) {
        this.skills = new ArrayList<>(skills);
    }

    public List<String> getSkills() {
        return new ArrayList<>(skills);
    }
}
```

**Interview One-Liner:**
**Defensive Copying** protects internal data by **returning or storing copies instead of original mutable objects**.

**3. Effective Exception Handling**

**Definition:**
**Exception Handling** is the process of managing runtime errors gracefully without crashing the application.

**Key Features:**

* Improves reliability
* Handles unexpected situations
* Maintains application flow
* Provides meaningful error messages

**How it Works:**

* Use `try`, `catch`, `finally`
* Catch specific exceptions
* Log errors properly

**When to Use:**

* File operations
* Database access
* External service calls

**Code Example:**

```java id="r5k9jw"
public class Main {
    public static void main(String[] args) {

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }
}
```

**Interview One-Liner:**
**Effective Exception Handling** means handling errors gracefully, logging them, and providing meaningful messages.

**4. Proper Logging**

**Definition:**
**Logging** is the process of recording application events, errors, and execution details for monitoring and debugging.

**Key Features:**

* Easier debugging
* Production monitoring
* Error tracking
* Audit support

**How it Works:**

* Use logging frameworks such as SLF4J and Logback
* Log important events at different levels

**When to Use:**

* Error tracking
* Debugging applications
* Monitoring production systems

**Code Example:**

```java id="p4x7qc"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log =
            LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Application Started");
        log.error("Something went wrong");
    }
}
```

**Common Log Levels:**

* **TRACE**
* **DEBUG**
* **INFO**
* **WARN**
* **ERROR**

**Interview One-Liner:**
**Logging** helps track application behavior and diagnose issues without using `System.out.println()`.

**5. Thread Safety**

**Definition:**
**Thread Safety** ensures that multiple threads can access shared resources without causing data inconsistency.

**Key Features:**

* Prevents race conditions
* Ensures data consistency
* Safe concurrent execution
* Improves reliability

**How it Works:**

* Synchronize shared resources
* Use immutable objects
* Use concurrent collections

**When to Use:**

* Multi-threaded applications
* Web applications
* High-concurrency systems

**Code Example:**

```java id="q8y2nf"
class Counter {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

**Interview One-Liner:**
**Thread Safety** ensures that shared data remains **consistent and correct when accessed by multiple threads**.

**6. Clean Code Principles**

**Definition:**
**Clean Code** is code that is easy to read, understand, maintain, and modify.

**Key Features:**

* Readable code
* Meaningful names
* Small methods
* Reusable logic
* Easy maintenance

**How it Works:**

* Follow coding standards
* Keep methods focused on one responsibility
* Avoid duplicate code
* Write self-explanatory code

**When to Use:**

* Every software project
* Team development
* Long-term maintenance

**Code Example:**

```java id="n6w4jb"
public class EmployeeService {

    public boolean isEligibleForBonus(int yearsOfService) {
        return yearsOfService >= 5;
    }
}
```

**Clean Code Guidelines:**

* Use **meaningful names**
* Follow **Single Responsibility Principle**
* Keep methods **small and focused**
* Avoid **duplicate code**
* Write **readable code**


# **21. Scenario-Based Questions**

1. How HashMap Works Internally
2. How to Make Class Immutable
3. How to Prevent Deadlocks
4. Difference Between Heap and Stack
5. Difference Between Comparable and Comparator
6. Difference Between Runnable and Callable
7. Why String is Immutable
8. How ConcurrentHashMap Works
9. How Garbage Collection Works
10. How to Optimize Java Performance

# **Spring Boot Interview Categories**

## **1. Spring Core**

1. IOC Container
2. Dependency Injection
3. Bean Lifecycle
4. Bean Scopes

## **2. Spring Boot Fundamentals**

1. Auto Configuration
2. Starter Dependencies
3. Embedded Servers
4. Spring Boot Annotations

## **3. REST API Development**

1. REST Principles
2. Request Mapping
3. Path Variables
4. Request Parameters
5. ResponseEntity

## **4. Spring Data JPA**

1. Entity Mapping
2. Repository Pattern
3. JPQL
4. Native Queries
5. Pagination
6. Auditing

## **5. Transaction Management**

1. @Transactional
2. Propagation
3. Isolation Levels
4. Rollback

## **6. Spring Security**

1. Authentication
2. Authorization
3. JWT
4. OAuth2
5. Role-Based Access

## **7. Exception Handling**

1. Global Exception Handling
2. @ControllerAdvice
3. Custom Exceptions

## **8. Caching**

1. Cache Management
2. Redis Integration
3. Cache Annotations

## **9. Testing**

1. JUnit
2. Mockito
3. Integration Testing
4. MockMvc

## **10. Monitoring & Logging**

1. Spring Boot Actuator
2. Logging Frameworks
3. Health Checks

## **11. Performance Optimization**

1. Connection Pooling
2. Lazy vs Eager Loading
3. Query Optimization

## **12. Spring Boot Deployment**

1. Profiles
2. External Configuration
3. Docker
4. CI/CD

# **Microservices Interview Categories**

## **1. Microservices Fundamentals**

1. Monolith vs Microservices
2. Benefits and Challenges
3. Service Decomposition

## **2. Service Communication**

1. REST Communication
2. Synchronous vs Asynchronous
3. gRPC
4. Event-Driven Architecture

## **3. API Gateway**

1. Routing
2. Authentication
3. Rate Limiting
4. Load Balancing

## **4. Service Discovery**

1. Eureka
2. Consul
3. Registration and Discovery

## **5. Configuration Management**

1. Centralized Configuration
2. Spring Cloud Config

## **6. Resilience Patterns**

1. Circuit Breaker
2. Retry
3. Timeout
4. Bulkhead Pattern

## **7. Distributed Transactions**

1. Two-Phase Commit
2. Saga Pattern
3. Eventual Consistency

## **8. Messaging Systems**

1. Kafka
2. RabbitMQ
3. Message Queues
4. Event Streaming

## **9. Database Design**

1. Database per Service
2. CQRS
3. Event Sourcing

## **10. Security**

1. JWT
2. OAuth2
3. API Security
4. Service-to-Service Authentication

## **11. Observability**

1. Centralized Logging
2. Distributed Tracing
3. Monitoring
4. Metrics Collection

## **12. Containerization & Orchestration**

1. Docker
2. Kubernetes
3. Helm

## **13. Cloud & Deployment**

1. AWS Services
2. CI/CD Pipelines
3. Blue-Green Deployment
4. Rolling Deployment

## **14. Performance & Scalability**

1. Load Balancing
2. Horizontal Scaling
3. Caching
4. Rate Limiting

## **15. Microservice Design Patterns**

1. API Gateway Pattern
2. Saga Pattern
3. CQRS Pattern
4. Strangler Pattern
5. Database per Service Pattern

## **16. Real-World Troubleshooting**

1. API Latency Issues
2. Distributed Debugging
3. Kafka Consumer Lag
4. Database Bottlenecks
5. Production Incident Handling
6. Root Cause Analysis (RCA)
