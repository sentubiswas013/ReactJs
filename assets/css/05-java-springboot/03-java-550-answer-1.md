# ✅ **1. Core Java Fundamentals**

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



# ✅ **2. OOPs Concepts**

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



# ✅ **3. Collections Framework**

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


# ✅ **4. Internal Working of Collections**

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


# ✅ **5. Exception Handling**

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


# ✅ **6. Java 8 Features**

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


# ✅ **7. Stream API**

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


# ✅ **8. Multithreading & Concurrency**

### **1. Thread Lifecycle**

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

### **2. Runnable vs Callable**

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

### **3. Synchronization**

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

### **4. Race Condition**

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
### **5. Deadlock**

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

### **6. Livelock**

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

### **7. Starvation**

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

### **8. Volatile Keyword**

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

### **9. Atomic Classes**

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

### **10. Executor Framework**

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

### **11. Thread Pool**

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

### **12. Future & CompletableFuture**

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

### **13. Fork Join Framework**

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



# ✅ **9. Memory Management**

### **1. Heap Memory**

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

### **2. Stack Memory**

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

### **3. Metaspace**

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

### **4. Garbage Collection**

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

### **5. GC Types**

**Definition:**
Java provides different **Garbage Collectors** optimized for various performance and memory requirements.

**Key Features**

* Improve memory management.
* Reduce application pauses.
* Optimize throughput and latency.
* JVM selects the collector based on configuration.

**Common GC Types**

### **1. Serial GC**

* Single-threaded GC.
* Suitable for small applications.
* Uses one thread for garbage collection.

### **2. Parallel GC**

* Uses multiple threads.
* Focuses on high throughput.
* Default GC in many Java versions.

### **3. G1 GC (Garbage First)**

* Divides heap into regions.
* Provides predictable pause times.
* Default GC in modern Java versions.

### **4. ZGC**

* Extremely low pause times.
* Handles very large heaps.
* Suitable for high-performance applications.

### **5. Shenandoah GC**

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

### **6. Minor GC**

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

### **7. Major GC**

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

### **8. Full GC**

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

### **9. Memory Leaks**

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

### **10. OutOfMemoryError**

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

# ✅ **10. JVM Internals**

### **1. Class Loader**

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

### **2. JVM Architecture**

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

### **3. Execution Engine**

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

### **4. Bytecode**

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

### **5. JIT Compiler**

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

### **6. Garbage Collectors**

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

### **7. JVM Memory Model**

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

### **8. JVM Tuning Basics**

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


# ✅ **11. Design Patterns**

### **1. Singleton Pattern**

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

### **2. Factory Pattern**

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

### **3. Abstract Factory Pattern**

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

### **4. Builder Pattern**

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

### **5. Prototype Pattern**

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

### **6. Adapter Pattern**

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

### **7. Decorator Pattern**

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

### **8. Facade Pattern**

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

### **9. Strategy Pattern**

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

### **10. Observer Pattern**

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

### **11. Template Method Pattern**

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

### **12. Chain of Responsibility Pattern**

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



# ✅ **12. SOLID Principles**

### **1. Single Responsibility Principle (SRP)**

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

### **2. Open Closed Principle (OCP)**

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

### **3. Liskov Substitution Principle (LSP)**

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

### **4. Interface Segregation Principle (ISP)**

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

### **5. Dependency Inversion Principle (DIP)**

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


# ✅ **13. Generics**

### **1. Generic Classes**

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

### **2. Generic Methods**

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

### **3. Wildcards**

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

### **4. Bounded Types**

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

### **5. Type Erasure**

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

### **6. Generic Collections**

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


# ✅ **14. Annotations & Reflection**

### **1. Built-in Annotations**

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

### **2. Custom Annotations**

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

### **3. Reflection API**

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

### **4. Dynamic Class Loading**

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

### **5. Runtime Processing**

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


# ✅ **15. File Handling**

### **1. File Class**

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

### **2. BufferedReader**

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

### **3. BufferedWriter**

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

### **4. Serialization**

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

### **5. Deserialization**

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

### **6. NIO (New I/O)**

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

### **7. Path & Files API**

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


# ✅ **16. Java Interview Coding Topics**

### **1. String Programs**

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

### **2. Array Programs**

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

### **3. Linked List**

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

### **4. Stack**

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

### **5. Queue**

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

### **6. Binary Search**

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

### **7. Sorting Algorithms**

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

### **8. Recursion**

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

### **9. Hashing**

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

### **10. Tree Traversal**

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

### **11. Dynamic Programming Basics**

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

### **1. Functional Interfaces**

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

### **2. Lambda Expressions**

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

### **3. Streams**

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

### **4. Optional**

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

### **5. Immutability**

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

### **6. Higher Order Functions**

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

### **1. DTO (Data Transfer Object)**

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

### **2. Entity**

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

### **3. Bean**

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

### **4. POJO (Plain Old Java Object)**

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

### **5. Dependency Injection (DI)**

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

### **6. Serialization**

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

### **7. Deserialization**

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

### **8. API Design**

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

### **9. REST Principles**

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

### **1. Immutable Objects**

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

### **2. Defensive Copying**

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

### **3. Effective Exception Handling**

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

### **4. Proper Logging**

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

### **5. Thread Safety**

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

### **6. Clean Code Principles**

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

### **1. How HashMap Works Internally**

**Definition:**
**HashMap** stores data as **Key-Value pairs** and uses a **hashing mechanism** for fast retrieval.

**Key Features:**

* Stores unique keys
* Allows one null key
* Average Time Complexity: **O(1)**
* Uses **Array + Linked List/Tree**

**How it Works:**

1. Key's `hashCode()` is calculated
2. Hash value determines the bucket index
3. Data is stored in that bucket
4. If multiple keys map to the same bucket (**Collision**), Java uses:

   * **Linked List** (Java 7)
   * **Linked List + Red-Black Tree** (Java 8+)

**When to Use:**

* Fast data lookup
* Caching
* Key-value storage

**Code Example:**

```java id="aj2k7m"
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> map =
                new HashMap<>();

        map.put(1, "Java");

        System.out.println(map.get(1));
    }
}
```

**Interview One-Liner:**
**HashMap** uses **hashCode()**, bucket indexing, and collision handling to provide **O(1)** average lookup performance.

### **2. How to Make Class Immutable**

**Definition:**
An **Immutable Class** is a class whose objects cannot be modified after creation.

**Key Features:**

* Thread-safe
* Secure
* No state changes
* Easier maintenance

**How it Works:**

1. Make class `final`
2. Make fields `private final`
3. Initialize fields through constructor
4. Do not provide setters
5. Use defensive copying for mutable objects

**When to Use:**

* Configuration objects
* Shared objects
* Multi-threaded applications

**Code Example:**

```java id="u5r8tx"
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
To make a class **Immutable**, make it **final**, use **private final fields**, and avoid setter methods.

### **3. How to Prevent Deadlocks**

**Definition:**
A **Deadlock** occurs when two or more threads wait indefinitely for resources held by each other.

**Key Features:**

* Threads become blocked forever
* Causes application hangs
* Common in multi-threaded systems

**How it Works:**

* Thread A holds Resource 1 and waits for Resource 2
* Thread B holds Resource 2 and waits for Resource 1
* Neither thread can proceed

**How to Prevent:**

* Acquire locks in a fixed order
* Minimize nested locks
* Use timeout-based locking
* Use concurrent utilities

**When to Use:**

* Multi-threaded applications
* Shared resource management

**Code Example:**

```java id="q4w7np"
synchronized(lock1) {
    synchronized(lock2) {
        // Safe locking order
    }
}
```

**Interview One-Liner:**
**Deadlocks** can be prevented by acquiring locks in a **consistent order** and minimizing nested locking.

### **4. Difference Between Heap and Stack**

**Definition:**
Both are memory areas used by JVM, but they serve different purposes.

| Feature           | **Heap Memory**         | **Stack Memory**               |
| ----------------- | ----------------------- | ------------------------------ |
| Stores            | Objects                 | Local variables & method calls |
| Shared            | Yes                     | No                             |
| Access Speed      | Slower                  | Faster                         |
| Memory Management | Garbage Collector       | Automatic                      |
| Lifetime          | Until object is removed | Until method ends              |

**Key Features:**

* **Heap** stores objects
* **Stack** stores method execution data
* Every thread has its own stack
* Heap is shared across threads

**Code Example:**

```java id="e8m3zr"
public class Main {

    public static void main(String[] args) {

        int age = 25;          // Stack

        String name = "John";  // Object in Heap
    }
}
```

**When to Use:**

* **Heap:** Object storage
* **Stack:** Method execution and local variables

**Interview One-Liner:**
**Heap** stores objects, while **Stack** stores local variables and method call information.

### **5. Difference Between Comparable and Comparator**

**Definition:**
Both are used for **sorting objects**, but they differ in where the sorting logic is defined.

**Key Features:**

| Feature               | **Comparable** | **Comparator** |
| --------------------- | -------------- | -------------- |
| Package               | java.lang      | java.util      |
| Method                | compareTo()    | compare()      |
| Sorting Logic         | Inside Class   | Outside Class  |
| Number of Sort Orders | One            | Multiple       |
| Modification Needed   | Yes            | No             |

**How it Works:**

* **Comparable:** Class defines its own default sorting
* **Comparator:** External class defines custom sorting

**When to Use:**

* **Comparable:** Natural ordering
* **Comparator:** Multiple sorting criteria

**Code Example (Comparable):**

```java id="y6p4cw"
class Employee implements Comparable<Employee> {

    int id;

    @Override
    public int compareTo(Employee e) {
        return this.id - e.id;
    }
}
```

**Code Example (Comparator):**

```java id="x2n8jt"
import java.util.Comparator;

class EmployeeNameComparator
        implements Comparator<Employee> {

    public int compare(Employee e1,
                       Employee e2) {
        return e1.name.compareTo(e2.name);
    }
}
```

### **6. Difference Between Runnable and Callable**

**Definition:**
Both **Runnable** and **Callable** are used to execute tasks in separate threads.

**Key Features:**

| Feature            | **Runnable**                    | **Callable**                 |
| ------------------ | ------------------------------- | ---------------------------- |
| Return Value       | No                              | Yes                          |
| Exception Handling | Cannot throw checked exceptions | Can throw checked exceptions |
| Method             | `run()`                         | `call()`                     |
| Package            | java.lang                       | java.util.concurrent         |

**How it Works:**

* **Runnable** executes a task without returning a result.
* **Callable** executes a task and returns a result using `Future`.

**When to Use:**

* **Runnable:** Simple background tasks.
* **Callable:** When a result is required from a thread.

**Code Example:**

```java id="r8m2xp"
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Callable<String> task =
                () -> "Task Completed";

        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        Future<String> result =
                executor.submit(task);

        System.out.println(result.get());

        executor.shutdown();
    }
}
```

**Interview One-Liner:**
**Runnable** performs a task without returning a value, while **Callable** can **return a result and throw checked exceptions**.

### **7. Why String is Immutable**

**Definition:**
A **String** object cannot be changed once it is created.

**Key Features:**

* Thread-safe
* Secure
* Supports String Pool
* Improves performance

**Why Java Made String Immutable:**

* **Security:** Prevents modification of sensitive values like database URLs and file paths.
* **Thread Safety:** Multiple threads can safely share the same String.
* **String Pool:** Reuse of String objects saves memory.
* **Hashing:** Stable hashCode improves HashMap performance.

**How it Works:**

* `String` class is `final`
* Internal character data cannot be modified
* Any modification creates a new object

**Code Example:**

```java id="n4q7vy"
public class Main {
    public static void main(String[] args) {

        String s = "Java";

        s.concat(" Spring");

        System.out.println(s);
    }
}
```

**Output:**

```text id="5u2zmd"
Java
```

**Interview One-Liner:**
**String** is immutable to provide **security, thread safety, memory optimization, and better hashing performance**.

### **8. How ConcurrentHashMap Works**

**Definition:**
**ConcurrentHashMap** is a thread-safe version of **HashMap** designed for high-performance concurrent access.

**Key Features:**

* Thread-safe
* Better performance than Hashtable
* Supports concurrent reads and writes
* No full-map locking

**How it Works:**

* Multiple threads can read simultaneously.
* Java 8 uses **bucket-level locking** instead of locking the entire map.
* Only the affected bucket is locked during updates.

**When to Use:**

* Multi-threaded applications
* Shared cache
* High-concurrency systems

**Code Example:**

```java id="w9c4kn"
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map =
                new ConcurrentHashMap<>();

        map.put(1, "Java");

        System.out.println(map.get(1));
    }
}
```

**Interview One-Liner:**
**ConcurrentHashMap** provides thread-safe access using **fine-grained locking**, allowing high concurrency and better performance.

### **9. How Garbage Collection Works**

**Definition:**
**Garbage Collection (GC)** is the JVM process that automatically removes unused objects from memory.

**Key Features:**

* Automatic memory management
* Prevents memory leaks
* Frees unused objects
* Managed by JVM

**How it Works:**

1. Objects are created in **Heap Memory**.
2. JVM tracks object references.
3. Objects with no reachable references become **eligible for GC**.
4. Garbage Collector removes those objects and frees memory.

**When to Use:**

* Happens automatically in all Java applications.
* No manual memory deallocation required.

**Code Example:**

```java id="e6v8rt"
public class Main {
    public static void main(String[] args) {

        String str = new String("Java");

        str = null;

        System.gc();
    }
}
```

**Interview One-Liner:**
**Garbage Collection** automatically removes **unreachable objects from heap memory**, helping manage memory efficiently.

### **10. How to Optimize Java Performance**

**Definition:**
**Java Performance Optimization** involves improving application speed, memory usage, and scalability.

**Key Features:**

* Faster execution
* Lower memory consumption
* Better scalability
* Improved user experience

**How it Works:**

* Identify bottlenecks
* Optimize code, memory, and database operations
* Use efficient data structures

**Best Practices:**

* Use **StringBuilder** instead of String concatenation in loops.
* Choose the right **Collection**.
* Minimize object creation.
* Use **Connection Pooling**.
* Implement **Caching**.
* Use **Concurrent Collections**.
* Optimize database queries.
* Profile applications using monitoring tools.

**Code Example:**

```java id="y3k8pm"
StringBuilder sb = new StringBuilder();

for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
```


# **Spring Boot Interview Categories**

## ✅ **1. Spring Core**

### **1. IoC (Inversion of Control) Container**

**Definition**

The **IoC Container** is the core of Spring that **creates, configures, and manages objects (Beans)** instead of developers creating them manually using `new`.

**Key Features**

* Manages **Bean creation**
* Handles **Dependency Injection**
* Controls **Bean lifecycle**
* Improves **loose coupling**

**How It Works**

1. Spring starts the container.
2. Reads configuration (`@Component`, `@Bean`, XML).
3. Creates beans.
4. Injects required dependencies.
5. Makes beans available for use.

**When to Use**

* In almost every **Spring application**
* To avoid manual object creation
* To manage application components centrally

**Code Example**

```java
@Component
class Engine {
}

@Component
class Car {
    private Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Here, the **IoC Container** creates both `Engine` and `Car` objects and injects the dependency automatically.

---

### **2. Dependency Injection (DI)**

**Definition**

**Dependency Injection** is a design pattern where Spring provides required objects (**dependencies**) to a class instead of the class creating them itself.

**Key Features**

* Promotes **loose coupling**
* Improves **testability**
* Easier maintenance
* Managed by Spring IoC Container

**Types of DI**

1. **Constructor Injection** (Recommended)
2. **Setter Injection**
3. **Field Injection**

**How It Works**

1. Class declares required dependency.
2. Spring creates dependency object.
3. Spring injects it into the class.

**When to Use**

* Whenever one class depends on another
* For better unit testing and maintainability

**Code Example**

```java
@Component
class Engine {
}

@Component
class Car {

    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Spring automatically injects the `Engine` object into `Car`.

---

### **3. Bean Lifecycle**

**Definition**

The **Bean Lifecycle** describes the stages a Spring Bean goes through from creation to destruction.

**Lifecycle Steps**

1. **Bean Instantiation**
2. **Dependency Injection**
3. **@PostConstruct**
4. Bean Ready for Use
5. **@PreDestroy**
6. Bean Destroyed

**How It Works**

Spring creates the bean, injects dependencies, calls initialization methods, uses the bean, and finally destroys it when the container shuts down.

**When to Use**

* Resource initialization
* Database connections
* Cache loading
* Cleanup operations

**Code Example**

```java
@Component
class DatabaseService {

    @PostConstruct
    public void init() {
        System.out.println("Bean Initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean Destroyed");
    }
}
```

**Output**

```text
Bean Initialized
Bean Destroyed
```

---

### **4. Bean Scopes**

**Definition**

**Bean Scope** defines how many bean instances Spring creates and how long they live.

**Common Scopes**

| Scope           | Description                              |
| --------------- | ---------------------------------------- |
| **singleton**   | One bean instance for entire application |
| **prototype**   | New bean instance every request          |
| **request**     | One bean per HTTP request                |
| **session**     | One bean per HTTP session                |
| **application** | One bean per web application             |

**How It Works**

Spring checks the configured scope and creates bean instances accordingly.

**When to Use**

* **singleton** – Services, Repositories
* **prototype** – Stateful objects
* **request** – Request-specific data
* **session** – User session data

**Code Example**

```java
@Component
@Scope("prototype")
class Employee {
}
```

Each call creates a **new Employee object**.

```java
Employee e1 = context.getBean(Employee.class);
Employee e2 = context.getBean(Employee.class);

System.out.println(e1 == e2);
```

**Output**

```text
false
```

Because **prototype scope** creates a new bean instance every time.


## ✅ **2. Spring Boot Fundamentals**

### **1. Auto Configuration**

**Definition**

**Auto Configuration** is a Spring Boot feature that automatically configures beans based on the dependencies available in the project.

**Key Features**

* Reduces manual configuration
* Automatically creates required beans
* Uses sensible defaults
* Speeds up application development

**How It Works**

1. Spring Boot scans dependencies in the classpath.
2. Detects required configurations.
3. Creates and configures beans automatically.
4. Developers can override default settings if needed.

**When to Use**

* In all Spring Boot applications
* To minimize XML and Java configuration
* For faster project setup

**Code Example**

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

If `spring-boot-starter-web` is present, Spring Boot automatically configures **DispatcherServlet**, **Tomcat**, and web-related beans.

### **2. Starter Dependencies**

**Definition**

**Starter Dependencies** are pre-configured dependency packages that include all libraries needed for a specific feature.

**Key Features**

* Simplifies dependency management
* Reduces version conflicts
* Provides commonly used libraries
* Easy project setup

**How It Works**

1. Add a starter dependency.
2. Spring Boot downloads required libraries.
3. Auto Configuration configures them automatically.

**When to Use**

* To quickly add application features
* To avoid managing multiple dependencies manually

**Common Starters**

* **spring-boot-starter-web**
* **spring-boot-starter-data-jpa**
* **spring-boot-starter-security**
* **spring-boot-starter-test**

**Code Example**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

This single dependency includes Spring MVC, Jackson, Validation, and Embedded Tomcat.

### **3. Embedded Servers**

**Definition**

An **Embedded Server** is a web server packaged inside the Spring Boot application, eliminating the need for separate server installation.

**Key Features**

* No external server setup
* Easy deployment
* Self-contained application
* Faster development

**Supported Servers**

* **Tomcat** (Default)
* **Jetty**
* **Undertow**

**How It Works**

1. Spring Boot starts the embedded server.
2. Deploys application automatically.
3. Begins listening for HTTP requests.

**When to Use**

* Microservices
* REST APIs
* Standalone web applications

**Code Example**

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }
}
```

Run the application:

```bash
mvn spring-boot:run
```

Spring Boot automatically starts the embedded **Tomcat** server.

### **4. Spring Boot Annotations**

**Definition**

Spring Boot provides annotations to simplify configuration and development.

**Key Features**

* Reduces boilerplate code
* Enables automatic configuration
* Simplifies component scanning
* Improves readability

**Common Annotations**

| Annotation                 | Purpose                     |
| -------------------------- | --------------------------- |
| **@SpringBootApplication** | Main Spring Boot annotation |
| **@RestController**        | Creates REST APIs           |
| **@Controller**            | Creates MVC controller      |
| **@Service**               | Business logic layer        |
| **@Repository**            | Data access layer           |
| **@Component**             | Generic Spring bean         |
| **@Autowired**             | Dependency Injection        |
| **@Configuration**         | Configuration class         |
| **@Bean**                  | Creates and manages a bean  |

**How It Works**

Spring scans these annotations during startup and automatically registers the corresponding beans.

**When to Use**

* Building REST APIs
* Creating services and repositories
* Configuring Spring-managed components

**Code Example**

```java
@Service
public class UserService {

    public String getUser() {
        return "John";
    }
}

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user() {
        return userService.getUser();
    }
}
```


## ✅ **3. REST API Development**

### **1. REST Principles**

**Definition**

**REST (Representational State Transfer)** is an architectural style used to build **scalable web services** using standard HTTP methods.

**Key Features**

* **Client-Server Architecture**
* **Stateless Communication**
* **Resource-Based URLs**
* Uses **HTTP Methods** (GET, POST, PUT, DELETE)
* Supports multiple formats like **JSON** and **XML**

**How It Works**

1. Client sends an HTTP request.
2. Server processes the request.
3. Server returns a response with data and status code.

**When to Use**

* Building REST APIs
* Microservices communication
* Web and mobile applications

**Example URLs**

```http
GET    /users
GET    /users/1
POST   /users
PUT    /users/1
DELETE /users/1
```

**Code Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "All Users";
    }
}
```

---

### **2. Request Mapping**

**Definition**

**Request Mapping** is used to map an HTTP request URL to a controller method.

**Key Features**

* Maps URLs to methods
* Supports HTTP methods
* Improves API organization
* Can be applied at class and method level

**How It Works**

1. Client sends a request.
2. Spring matches URL and HTTP method.
3. Corresponding controller method executes.

**When to Use**

* Creating REST endpoints
* Routing requests to controller methods

**Code Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "All Users";
    }

    @PostMapping
    public String createUser() {
        return "User Created";
    }
}
```

**Common Mapping Annotations**

* **@RequestMapping**
* **@GetMapping**
* **@PostMapping**
* **@PutMapping**
* **@DeleteMapping**

---

### **3. Path Variables**

**Definition**

A **Path Variable** is used to extract values directly from the URL path.

**Key Features**

* Dynamic URL handling
* Easy resource identification
* Improves URL readability

**How It Works**

1. Value is passed in the URL.
2. Spring extracts the value.
3. Method parameter receives it.

**When to Use**

* Fetching specific resources
* Updating or deleting records

**Example URL**

```http
GET /users/101
```

**Code Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User Id: " + id;
    }
}
```

**Output**

```text
User Id: 101
```

---

### **4. Request Parameters**

**Definition**

**Request Parameters** are values passed in the URL after the `?` symbol.

**Key Features**

* Used for filtering and searching
* Optional or mandatory
* Supports default values

**How It Works**

1. Client sends parameters in URL.
2. Spring reads parameters using **@RequestParam**.
3. Values are passed to the controller method.

**When to Use**

* Search operations
* Pagination
* Sorting and filtering

**Example URL**

```http
GET /users?name=John
```

**Code Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUser(@RequestParam String name) {
        return "User Name: " + name;
    }
}
```

**Output**

```text
User Name: John
```

---

### **5. ResponseEntity**

**Definition**

**ResponseEntity** represents the complete HTTP response including **body, headers, and status code**.

**Key Features**

* Custom HTTP status codes
* Custom headers
* Flexible response handling
* Better API design

**How It Works**

1. Controller creates a ResponseEntity object.
2. Sets response body.
3. Sets status code and headers.
4. Returns complete HTTP response.

**When to Use**

* Returning custom status codes
* Error handling
* REST API responses

**Code Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(
            @PathVariable Long id) {

        return ResponseEntity
                .ok("User Found: " + id);
    }
}
```

**Custom Status Example**

```java
@PostMapping
public ResponseEntity<String> createUser() {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("User Created");
}
```

**Output**

```http
HTTP Status: 201 Created
Body: User Created
```


## ✅ **4. Spring Data JPA**

### **1. Entity Mapping**

**Definition**

**Entity Mapping** is the process of mapping a Java class to a database table using **JPA annotations**.

**Key Features**

* Object-Relational Mapping (**ORM**)
* Maps classes to tables
* Maps fields to columns
* Supports relationships between entities

**How It Works**

1. Create a Java class.
2. Annotate it with **@Entity**.
3. JPA maps the class to a database table.
4. Objects are stored as rows in the table.

**When to Use**

* Database-driven applications
* Spring Data JPA projects
* CRUD operations

**Code Example**

```java
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
```

**Common Mapping Annotations**

* **@Entity**
* **@Table**
* **@Id**
* **@Column**
* **@OneToOne**
* **@OneToMany**
* **@ManyToOne**
* **@ManyToMany**

---

### **2. Repository Pattern**

**Definition**

The **Repository Pattern** abstracts database operations and provides a clean way to access data.

**Key Features**

* Separates business logic from data access
* Reduces boilerplate code
* Provides built-in CRUD operations
* Easy database interaction

**How It Works**

1. Create a repository interface.
2. Extend **JpaRepository**.
3. Spring automatically generates implementation.

**When to Use**

* Performing database operations
* CRUD applications
* Data access layer implementation

**Code Example**

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}
```

**Built-in Methods**

```java
userRepository.save(user);
userRepository.findById(1L);
userRepository.findAll();
userRepository.deleteById(1L);
```

---

### **3. JPQL (Java Persistence Query Language)**

**Definition**

**JPQL** is an object-oriented query language used to query **Entity objects** instead of database tables.

**Key Features**

* Database independent
* Works with entities
* Similar to SQL
* Supports custom queries

**How It Works**

1. Write query using entity names.
2. JPA converts JPQL into SQL.
3. Database executes generated SQL.

**When to Use**

* Complex data retrieval
* Custom filtering
* Entity-based queries

**Code Example**

```java
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByName(String name);
}
```

**JPQL Example**

```sql
SELECT u FROM User u
```

Uses **User Entity**, not the database table name.

---

### **4. Native Queries**

**Definition**

A **Native Query** is a database-specific SQL query written directly in SQL.

**Key Features**

* Uses actual SQL syntax
* Supports database-specific features
* More control over queries
* Useful for complex operations

**How It Works**

1. Write SQL query.
2. Set `nativeQuery = true`.
3. Spring executes the SQL directly.

**When to Use**

* Complex joins
* Stored procedures
* Database-specific optimizations

**Code Example**

```java
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query(
      value = "SELECT * FROM users WHERE name = ?1",
      nativeQuery = true)
    List<User> findByName(String name);
}
```

**SQL Example**

```sql
SELECT * FROM users
```

Uses the actual **table name**.

---

### **5. Pagination**

**Definition**

**Pagination** is the process of retrieving data in smaller chunks (pages) instead of loading all records at once.

**Key Features**

* Improves performance
* Reduces memory usage
* Faster API responses
* Handles large datasets efficiently

**How It Works**

1. Specify page number and size.
2. Spring fetches only required records.
3. Returns paginated result.

**When to Use**

* Large datasets
* Search APIs
* User listings
* Reports

**Code Example**

```java
import org.springframework.data.domain.*;

Page<User> users =
    userRepository.findAll(
        PageRequest.of(0, 5));
```

**Repository**

```java
public interface UserRepository
        extends JpaRepository<User, Long> {
}
```

**Output**

```text
Page 0
5 Records Returned
```

---

### **6. Auditing**

**Definition**

**Auditing** automatically tracks who created or modified data and when those changes occurred.

**Key Features**

* Tracks creation time
* Tracks update time
* Reduces manual coding
* Improves data traceability

**How It Works**

1. Enable JPA auditing.
2. Add auditing annotations.
3. Spring automatically updates audit fields.

**When to Use**

* Enterprise applications
* Logging changes
* Compliance requirements
* Tracking user activity

**Code Example**

**Enable Auditing**

```java
@Configuration
@EnableJpaAuditing
public class AuditConfig {
}
```

**Entity**

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

**Audit Annotations**

* **@CreatedDate**
* **@LastModifiedDate**
* **@CreatedBy**
* **@LastModifiedBy**


## ✅ **5. Transaction Management**

### **1. @Transactional**

**Definition**

**@Transactional** is a Spring annotation used to manage **database transactions** automatically.

A transaction ensures that a group of operations either **all succeed (Commit)** or **all fail (Rollback)**.

**Key Features**

* Automatic transaction management
* Maintains data consistency
* Supports rollback on failure
* Supports propagation and isolation levels

**How It Works**

1. Transaction starts.
2. Database operations execute.
3. If successful, transaction commits.
4. If an exception occurs, transaction rolls back.

**When to Use**

* Money transfers
* Order processing
* Multiple database updates
* Any operation requiring data consistency

**Code Example**

```java
@Service
public class AccountService {

    @Transactional
    public void transferMoney() {

        debitAccount();
        creditAccount();
    }
}
```

Both methods execute in a **single transaction**.

---

### **2. Propagation**

**Definition**

**Propagation** defines how a transaction behaves when one transactional method calls another transactional method.

**Key Features**

* Controls transaction boundaries
* Supports nested operations
* Reuses or creates transactions
* Improves transaction management

**How It Works**

Spring checks whether a transaction already exists and applies the configured propagation behavior.

**Common Propagation Types**

| Propagation       | Description                                   |
| ----------------- | --------------------------------------------- |
| **REQUIRED**      | Join existing transaction or create a new one |
| **REQUIRES_NEW**  | Always create a new transaction               |
| **SUPPORTS**      | Use existing transaction if available         |
| **MANDATORY**     | Must run inside an existing transaction       |
| **NOT_SUPPORTED** | Execute without transaction                   |

**When to Use**

* Parent-child service calls
* Complex business operations
* Transaction separation

**Code Example**

```java
@Service
public class OrderService {

    @Transactional(
        propagation = Propagation.REQUIRED)
    public void placeOrder() {
        paymentService.pay();
    }
}
```

**Interview One-Line Answer**

**Propagation** defines how Spring handles transactions when one transactional method calls another.

---

### **3. Isolation Levels**

**Definition**

**Isolation Level** controls how transactions interact with each other when running concurrently.

**Key Features**

* Prevents data inconsistency
* Controls concurrent access
* Balances performance and safety
* Avoids transaction conflicts

**How It Works**

The database restricts how one transaction can view changes made by another transaction.

**Isolation Levels**

| Level                | Description                                    |
| -------------------- | ---------------------------------------------- |
| **READ_UNCOMMITTED** | Can read uncommitted data                      |
| **READ_COMMITTED**   | Reads only committed data                      |
| **REPEATABLE_READ**  | Same row returns same data during transaction  |
| **SERIALIZABLE**     | Highest isolation, fully isolated transactions |

**When to Use**

* Banking systems
* Financial applications
* High-concurrency systems

**Code Example**

```java
@Transactional(
    isolation = Isolation.READ_COMMITTED)
public void updateBalance() {

}
```

**Interview One-Line Answer**

**Isolation Level** controls the visibility of data changes between concurrent transactions.

---

### **4. Rollback**

**Definition**

**Rollback** cancels all changes made within a transaction when an error occurs.

**Key Features**

* Maintains data integrity
* Prevents partial updates
* Automatic failure recovery
* Supports custom exception handling

**How It Works**

1. Transaction starts.
2. Operations execute.
3. Exception occurs.
4. All changes are undone.

**When to Use**

* Financial transactions
* Order management
* Inventory updates
* Any critical business operation

**Code Example**

```java
@Service
public class PaymentService {

    @Transactional
    public void processPayment() {

        savePayment();

        if (true) {
            throw new RuntimeException("Error");
        }

        updateAccount();
    }
}
```

**Result**

```text
Transaction Rolled Back
No Data Saved
```

**Custom Rollback Example**

```java
@Transactional(
    rollbackFor = Exception.class)
public void processPayment() throws Exception {

    throw new Exception("Failure");
}
```


## ✅ **6. Spring Security**

### **1. Authentication**

**Authentication** is the process of **verifying a user's identity**.

**Key Features**

* Confirms **who the user is**
* Uses **username/password**, **OTP**, **biometrics**, or **tokens**
* First step before authorization

**How It Works**

1. User enters credentials.
2. System verifies credentials.
3. User is authenticated and allowed to access the application.

**When to Use**

* Login systems
* Banking applications
* Enterprise applications

**Example**

```java
if(username.equals("admin") && password.equals("123")) {
    System.out.println("Authenticated");
}
```

**Interview One-Liner**

Authentication means **verifying the identity of a user before granting access**.

---

### **2. Authorization**

**Authorization** is the process of **determining what an authenticated user is allowed to do**.

**Key Features**

* Controls **permissions and access**
* Happens **after authentication**
* Defines access to resources

**How It Works**

1. User is authenticated.
2. System checks permissions.
3. Access is granted or denied.

**When to Use**

* Admin/User access control
* API security
* Resource protection

**Example**

```java
if(user.getRole().equals("ADMIN")) {
    System.out.println("Access Granted");
} else {
    System.out.println("Access Denied");
}
```

**Interview One-Liner**

Authorization means **checking what actions or resources a user can access**.

---

### **3. JWT (JSON Web Token)**

**JWT** is a **compact, secure token** used for authentication and information exchange between client and server.

**Key Features**

* **Stateless**
* **Digitally signed**
* Easy to transfer in HTTP headers
* Contains user information (**claims**)

**JWT Structure**

```text
Header.Payload.Signature
```

**How It Works**

1. User logs in.
2. Server generates JWT.
3. Client stores token.
4. Client sends token with every request.
5. Server validates token and grants access.

**When to Use**

* REST APIs
* Microservices
* Stateless authentication

**Example**

```java
String token = Jwts.builder()
        .setSubject("user1")
        .signWith(secretKey)
        .compact();
```

**Interview One-Liner**

JWT is a **signed token used for stateless authentication and secure user information exchange**.

---

### **4. OAuth2**

**OAuth2** is an **authorization framework** that allows users to grant limited access to resources without sharing passwords.

**Key Features**

* Secure **third-party access**
* Uses **Access Tokens**
* Supports multiple grant types
* Widely used by Google, GitHub, Facebook

**How It Works**

1. User logs in through a provider.
2. Provider authenticates the user.
3. Access token is issued.
4. Application uses the token to access resources.

**When to Use**

* Social login
* Third-party integrations
* API authorization

**Example**

```java
http
    .oauth2Login(Customizer.withDefaults());
```

**Real Example**

* Login with Google
* Login with GitHub
* Login with Facebook

**Interview One-Liner**

OAuth2 allows **secure authorization by granting access through tokens instead of sharing passwords**.

---

### **5. Role-Based Access Control (RBAC)**

**RBAC** is a security model where permissions are assigned based on **roles**.

**Key Features**

* Centralized permission management
* Easy to maintain
* Improves security
* Supports multiple user roles

**How It Works**

1. Assign roles to users.
2. Assign permissions to roles.
3. System checks user role before granting access.

**Example Roles**

* **ADMIN** → Full access
* **MANAGER** → Limited management access
* **USER** → Basic access

**When to Use**

* Enterprise applications
* Banking systems
* E-commerce applications

**Spring Security Example**

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser() {
    System.out.println("User Deleted");
}
```


## ✅ **7. Exception Handling**

### **1. Global Exception Handling**

**Global Exception Handling** is a mechanism to **handle exceptions centrally** across the entire application instead of writing try-catch blocks in every controller.

**Key Features**

* Centralized error handling
* Reduces duplicate code
* Consistent error responses
* Improves application maintainability

**How It Works**

1. An exception occurs in a controller/service.
2. Global exception handler catches it.
3. A custom error response is returned to the client.

**When to Use**

* REST APIs
* Large applications
* Common error handling requirements

**Example**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "Something went wrong";
    }
}
```

**Interview One-Liner**

Global Exception Handling provides **centralized exception management for the entire application**.

---

### **2. @ControllerAdvice**

**@ControllerAdvice** is a Spring annotation used to **handle exceptions globally** and apply common logic across multiple controllers.

**Key Features**

* Global exception handling
* Shared model attributes
* Shared data binding configuration
* Works across all controllers

**How It Works**

1. Spring detects the class annotated with **@ControllerAdvice**.
2. When an exception occurs, matching **@ExceptionHandler** methods are executed.
3. A custom response is sent back to the client.

**When to Use**

* Common exception handling
* Shared controller logic
* Consistent API error responses

**Example**

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointer() {
        return "Null value found";
    }
}
```

**For REST APIs**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException() {
        return "Runtime Error";
    }
}
```

**Interview One-Liner**

**@ControllerAdvice** allows you to **apply exception handling and common controller logic globally**.

---

### **3. Custom Exceptions**

**Custom Exceptions** are user-defined exception classes created to represent specific business or application errors.

**Key Features**

* Meaningful error messages
* Business-specific error handling
* Better code readability
* Easier debugging

**How It Works**

1. Create a class extending **Exception** or **RuntimeException**.
2. Throw the custom exception when a business rule fails.
3. Handle it using **@ExceptionHandler**.

**When to Use**

* Resource not found
* Invalid business rules
* Custom validation failures

**Custom Exception Class**

```java
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
```

**Throwing Exception**

```java
if(user == null) {
    throw new UserNotFoundException("User not found");
}
```

**Handling Exception**

```java
@ExceptionHandler(UserNotFoundException.class)
public String handleUserNotFound(UserNotFoundException ex) {
    return ex.getMessage();
}
```

## ✅ **8. Caching**

### **1. Cache Management**

**Cache Management** is the process of **storing frequently accessed data in memory** to improve application performance and reduce database calls.

**Key Features**

* Faster data retrieval
* Reduces database load
* Improves application performance
* Supports automatic cache updates

**How It Works**

1. Application requests data.
2. Cache is checked first.
3. If data exists (**Cache Hit**), return it immediately.
4. If data does not exist (**Cache Miss**), fetch from database and store in cache.

**When to Use**

* Frequently accessed data
* Product catalogs
* User profiles
* Configuration data

**Example**

```java
@Service
public class ProductService {

    @Cacheable("products")
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
```

**Interview One-Liner**

Cache Management improves performance by **storing frequently used data in memory and reducing database access**.

---

### **2. Redis Integration**

**Redis** is an **in-memory key-value data store** commonly used as a cache in Spring Boot applications.

**Key Features**

* Extremely fast
* In-memory storage
* Supports expiration (TTL)
* Distributed caching support
* Reduces database load

**How It Works**

1. Application requests data.
2. Redis checks if data exists.
3. If found, data is returned from Redis.
4. Otherwise, data is fetched from database and stored in Redis.

**When to Use**

* High-performance applications
* Microservices
* Session storage
* Frequently accessed data

**Dependency**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

**Configuration**

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

**Enable Caching**

```java
@SpringBootApplication
@EnableCaching
public class Application {
}
```

**Interview One-Liner**

Redis is a **high-speed in-memory data store used for caching, session management, and performance optimization**.

---

### **3. Cache Annotations**

Spring Boot provides **cache annotations** to manage caching with minimal code.

**Key Features**

* Declarative caching
* Automatic cache management
* Improves performance
* Reduces boilerplate code

**Important Cache Annotations**

**@Cacheable**

* Stores method result in cache.
* Returns cached value for future requests.

```java
@Cacheable("users")
public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
}
```

**@CachePut**

* Updates cache every time the method executes.

```java
@CachePut(value = "users", key = "#user.id")
public User updateUser(User user) {
    return userRepository.save(user);
}
```

**@CacheEvict**

* Removes data from cache.

```java
@CacheEvict(value = "users", key = "#id")
public void deleteUser(Long id) {
    userRepository.deleteById(id);
}
```

**How It Works**

1. Method is called.
2. Spring checks cache annotations.
3. Cache is created, updated, or removed automatically.

**When to Use**

* Data caching
* Performance optimization
* Reducing database queries


## ✅ **9. Testing**

### **1. JUnit**

**JUnit** is the most popular **Java testing framework** used to write and run unit tests.

**Key Features**

* Automated testing
* Assertion support
* Test lifecycle annotations
* Easy integration with build tools

**How It Works**

1. Create a test class.
2. Write test methods using **@Test**.
3. Execute tests.
4. Verify expected results using assertions.

**When to Use**

* Unit testing
* Test-driven development (TDD)
* Validating business logic

**Example**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddition() {
        assertEquals(5, 2 + 3);
    }
}
```

**Common Annotations**

* **@Test**
* **@BeforeEach**
* **@AfterEach**
* **@BeforeAll**
* **@AfterAll**

**Interview One-Liner**

JUnit is a **Java testing framework used to write and execute automated unit tests**.

---

### **2. Mockito**

**Mockito** is a **mocking framework** used to create fake objects for testing.

**Key Features**

* Creates mock objects
* Isolates unit tests
* Verifies method calls
* Reduces dependency on external systems

**How It Works**

1. Create mock objects.
2. Define mock behavior.
3. Execute the test.
4. Verify interactions and results.

**When to Use**

* Unit testing services
* Testing without database access
* Testing external dependencies

**Example**

```java
@Mock
private UserRepository repository;

@Test
void testUser() {
    when(repository.findById(1L))
            .thenReturn(Optional.of(new User()));

    User user = repository.findById(1L).get();

    assertNotNull(user);
}
```

**Verification Example**

```java
verify(repository).findById(1L);
```

**Interview One-Liner**

Mockito is a **mocking framework that creates fake objects to test code independently from external dependencies**.

---

### **3. Integration Testing**

**Integration Testing** verifies that multiple components work correctly together.

**Key Features**

* Tests complete application flow
* Loads Spring context
* Validates database interactions
* Tests real component integration

**How It Works**

1. Start Spring application context.
2. Load actual beans.
3. Execute business operations.
4. Verify component interaction.

**When to Use**

* Testing service and repository layers
* Database testing
* End-to-end application flow validation

**Example**

```java
@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        User user = userService.createUser("John");
        assertNotNull(user);
    }
}
```

**Common Annotation**

```java
@SpringBootTest
```

**Interview One-Liner**

Integration Testing ensures that **multiple application components work together correctly in a real environment**.

---

### **4. MockMvc**

**MockMvc** is a Spring testing tool used to **test REST controllers without starting the actual server**.

**Key Features**

* Fast API testing
* No server startup required
* Tests request and response behavior
* Supports JSON validation

**How It Works**

1. Send a mock HTTP request.
2. Controller processes the request.
3. Verify response status and data.

**When to Use**

* Controller testing
* REST API testing
* Request/response validation

**Example**

```java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUsers() throws Exception {

        mockMvc.perform(get("/users"))
               .andExpect(status().isOk());
    }
}
```

**Common Methods**

* **perform()**
* **andExpect()**
* **status()**
* **content()**


## ✅ **10. Monitoring & Logging**

### **1. Spring Boot Actuator**

**Spring Boot Actuator** provides **production-ready monitoring and management endpoints** for a Spring Boot application.

**Key Features**

* Application health monitoring
* Metrics collection
* Environment information
* Application status monitoring
* Production diagnostics

**How It Works**

1. Add the Actuator dependency.
2. Spring exposes monitoring endpoints.
3. Admins or monitoring tools access these endpoints.
4. Application status and metrics are returned.

**When to Use**

* Production monitoring
* Performance tracking
* Application diagnostics
* DevOps and cloud environments

**Dependency**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

**Configuration**

```properties
management.endpoints.web.exposure.include=*
```

**Common Endpoints**

* **/actuator/health**
* **/actuator/info**
* **/actuator/metrics**
* **/actuator/env**
* **/actuator/beans**

**Example**

```text
GET /actuator/health
```

**Response**

```json
{
  "status": "UP"
}
```

**Interview One-Liner**

Spring Boot Actuator provides **built-in endpoints for monitoring, managing, and observing application health and performance**.

---

### **2. Logging Frameworks**

**Logging Frameworks** are used to **record application events, errors, and debugging information**.

**Key Features**

* Error tracking
* Debugging support
* Audit trails
* Performance monitoring
* Different log levels

**Common Logging Frameworks**

* **SLF4J** (Logging API)
* **Logback** (Default in Spring Boot)
* **Log4j2**

**Log Levels**

* **TRACE**
* **DEBUG**
* **INFO**
* **WARN**
* **ERROR**

**How It Works**

1. Application generates log messages.
2. Logging framework captures them.
3. Logs are written to console, file, or monitoring system.

**When to Use**

* Debugging applications
* Production monitoring
* Error analysis
* Audit logging

**Example**

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    public void createUser() {
        logger.info("User created successfully");
    }
}
```

**Configuration**

```properties
logging.level.com.example=DEBUG
```

**Interview One-Liner**

Logging Frameworks help **track application events, errors, and system behavior for debugging and monitoring**.

---

### **3. Health Checks**

**Health Checks** are mechanisms used to verify whether an application and its dependencies are working properly.

**Key Features**

* Application availability monitoring
* Dependency status validation
* Automatic failure detection
* Supports cloud deployments

**How It Works**

1. Health endpoint is called.
2. Application checks critical components.
3. Status is returned as **UP** or **DOWN**.
4. Monitoring systems use the result for alerts and recovery.

**When to Use**

* Microservices
* Kubernetes deployments
* Cloud-native applications
* Production monitoring

**Built-in Health Check**

```text
GET /actuator/health
```

**Response**

```json
{
  "status": "UP"
}
```

**Custom Health Check**

```java
@Component
public class DatabaseHealthIndicator
        implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up()
                .withDetail("Database", "Available")
                .build();
    }
}
```

## ✅ **11. Performance Optimization**

### **1. Connection Pooling**

**Connection Pooling** is a technique where a pool of **pre-created database connections** is maintained and reused instead of creating a new connection for every request.

**Key Features**

* Improves performance
* Reduces connection creation overhead
* Better resource utilization
* Supports high concurrency
* Faster database access

**How It Works**

1. Application starts and creates a pool of connections.
2. Request borrows a connection from the pool.
3. Database operation is performed.
4. Connection is returned to the pool for reuse.

**When to Use**

* High-traffic applications
* Enterprise applications
* Microservices
* Frequent database operations

**Spring Boot Default Pool**

* **HikariCP** (default connection pool)

**Configuration**

```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
```

**Example**

```java
@Autowired
private DataSource dataSource;

Connection connection = dataSource.getConnection();
```

**Interview One-Liner**

Connection Pooling improves performance by **reusing database connections instead of creating new ones for every request**.

---

### **2. Lazy vs Eager Loading**

**Lazy Loading** and **Eager Loading** define when related data should be loaded from the database.

**Key Features**

| **Lazy Loading**             | **Eager Loading**             |
| ---------------------------- | ----------------------------- |
| Loads data only when needed  | Loads data immediately        |
| Better performance initially | Faster access to related data |
| Reduces unnecessary queries  | May load unused data          |
| Default for collections      | Often used for required data  |

**How It Works**

**Lazy Loading**

1. Parent entity is loaded.
2. Related entity is not loaded initially.
3. Data is fetched only when accessed.

```java
@OneToMany(fetch = FetchType.LAZY)
private List<Order> orders;
```

**Eager Loading**

1. Parent entity is loaded.
2. Related entity is loaded immediately.

```java
@OneToMany(fetch = FetchType.EAGER)
private List<Order> orders;
```

**When to Use**

**Lazy Loading**

* Large relationships
* Better performance
* Data not always required

**Eager Loading**

* Data always required
* Small relationships
* Avoid additional queries

**Interview One-Liner**

**Lazy Loading** fetches data only when needed, while **Eager Loading** fetches related data immediately with the parent entity.

---

### **3. Query Optimization**

**Query Optimization** is the process of improving SQL query performance to reduce execution time and database load.

**Key Features**

* Faster query execution
* Reduced database load
* Better scalability
* Improved application performance

**How It Works**

1. Analyze slow queries.
2. Identify bottlenecks.
3. Optimize indexes, joins, and query structure.
4. Reduce unnecessary database operations.

**When to Use**

* Slow database performance
* Large datasets
* High-traffic applications
* Performance tuning

**Best Practices**

* Use **Indexes**
* Fetch only required columns
* Avoid unnecessary joins
* Use pagination
* Avoid N+1 query problems
* Use caching when possible

**Bad Example**

```sql
SELECT * FROM users;
```

**Optimized Example**

```sql
SELECT id, name
FROM users
WHERE id = 1;
```

**JPA Pagination Example**

```java
Page<User> users =
        userRepository.findAll(
                PageRequest.of(0, 10));
```


## ✅ **12. Spring Boot Deployment**

### **1. Profiles**

**Profiles** in Spring Boot allow you to **use different configurations for different environments** such as Development, Testing, and Production.

**Key Features**

* Environment-specific configuration
* Easy deployment management
* Reduces configuration conflicts
* Supports multiple environments

**How It Works**

1. Create separate configuration files.
2. Activate a specific profile.
3. Spring loads only that profile's configuration.

**When to Use**

* Development environment
* Testing environment
* Production environment

**Configuration Files**

```text
application.properties
application-dev.properties
application-test.properties
application-prod.properties
```

**Activate Profile**

```properties
spring.profiles.active=dev
```

**Profile-Specific Bean**

```java
@Profile("dev")
@Bean
public DataSource dataSource() {
    return new H2DataSource();
}
```

**Interview One-Liner**

Profiles allow Spring Boot to **load different configurations based on the active environment**.

---

### **2. External Configuration**

**External Configuration** allows application settings to be managed **outside the application code**.

**Key Features**

* No code changes for configuration updates
* Environment-specific settings
* Improved security
* Easier deployment

**How It Works**

1. Configuration values are stored externally.
2. Spring Boot reads them during startup.
3. Values are injected into the application.

**When to Use**

* Database credentials
* API keys
* Environment-specific properties
* Cloud deployments

**application.properties**

```properties
app.name=MyApplication
server.port=8080
```

**Using @Value**

```java
@Value("${app.name}")
private String appName;
```

**Using Environment Variable**

```bash
export SERVER_PORT=9090
```

**Interview One-Liner**

External Configuration allows application settings to be **managed outside the codebase for flexibility and security**.

---

### **3. Docker**

**Docker** is a containerization platform that packages an application and its dependencies into a **portable container**.

**Key Features**

* Consistent environments
* Easy deployment
* Lightweight containers
* Platform independence
* Faster application delivery

**How It Works**

1. Create a Docker image.
2. Package application and dependencies.
3. Run the image as a container anywhere Docker is installed.

**When to Use**

* Microservices
* Cloud deployments
* CI/CD pipelines
* Environment consistency

**Dockerfile**

```dockerfile
FROM eclipse-temurin:21-jdk

COPY target/app.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

**Build Image**

```bash
docker build -t springboot-app .
```

**Run Container**

```bash
docker run -p 8080:8080 springboot-app
```

**Interview One-Liner**

Docker packages an application and its dependencies into **portable containers for consistent deployment across environments**.

---

### **4. CI/CD**

**CI/CD** stands for **Continuous Integration** and **Continuous Deployment/Delivery**.

**Key Features**

* Automated builds
* Automated testing
* Faster releases
* Reduced manual effort
* Improved software quality

**How It Works**

**Continuous Integration (CI)**

1. Developer pushes code.
2. Build is triggered automatically.
3. Automated tests run.
4. Code is merged if tests pass.

**Continuous Deployment (CD)**

1. Application is packaged.
2. Deployment is automated.
3. Changes are released to production.

**When to Use**

* Agile development
* DevOps environments
* Frequent releases
* Large development teams

**Example CI/CD Pipeline**

```yaml
steps:
  - Build
  - Test
  - Package
  - Deploy
```

**Popular CI/CD Tools**

* **Jenkins**
* **GitHub Actions**
* **GitLab CI/CD**
* **Azure DevOps**


# **Microservices Interview Categories**

## ✅ **1. Microservices Fundamentals**

### **1. Monolith vs Microservices**

**Monolith**
A **Monolithic Architecture** is an application where all modules (UI, Business Logic, Database Access) are built and deployed as a **single unit**.

**Microservices**
A **Microservices Architecture** is an application split into multiple **independent services**, where each service handles a specific business function and can be developed, deployed, and scaled separately.

**Key Differences**

| Feature        | Monolith                       | Microservices                   |
| -------------- | ------------------------------ | ------------------------------- |
| Deployment     | Single deployment              | Independent deployment          |
| Scalability    | Scale entire application       | Scale individual services       |
| Development    | Easier initially               | Better for large teams          |
| Maintenance    | Harder as app grows            | Easier to maintain              |
| Technology     | Usually one technology stack   | Different technologies possible |
| Failure Impact | One issue may affect whole app | Failure isolated to one service |

**When to Use**

* **Monolith**: Small applications, startups, simple projects.
* **Microservices**: Large, complex, highly scalable applications.

**Example**

**Monolith**

```text
E-Commerce App
├── User Module
├── Product Module
├── Order Module
└── Payment Module

Single Deployment
```

**Microservices**

```text
User Service
Product Service
Order Service
Payment Service

Separate Deployments
```

---

### **2. Benefits and Challenges of Microservices**

**Benefits**

1. **Independent Deployment** – Deploy one service without affecting others.
2. **Scalability** – Scale only the required service.
3. **Fault Isolation** – Failure in one service doesn't bring down the entire system.
4. **Technology Flexibility** – Different services can use different technologies.
5. **Faster Development** – Multiple teams can work independently.

**Challenges**

1. **Complex Communication** – Services communicate over network calls.
2. **Distributed Transactions** – Managing data consistency is difficult.
3. **Monitoring & Logging** – Requires centralized monitoring.
4. **Deployment Complexity** – Many services to manage.
5. **Network Latency** – Remote calls are slower than in-process calls.

**How It Works**

```text
Client
   |
API Gateway
   |
-------------------------
|      |       |        |
User  Order  Product  Payment
Svc   Svc     Svc      Svc
```

**When to Use**

* Large enterprise applications.
* Systems requiring high scalability and availability.
* Applications managed by multiple teams.

---

### **3. Service Decomposition**

**Definition**

**Service Decomposition** is the process of breaking a large application into smaller, independent **microservices**, where each service focuses on a single business capability.

**How It Works**

1. Identify business domains.
2. Split application into services based on business functions.
3. Give each service its own database if possible.
4. Enable communication through APIs or messaging.

**Example**

**Before Decomposition**

```text
E-Commerce Application
├── User Management
├── Product Management
├── Orders
└── Payments
```

**After Decomposition**

```text
User Service
Product Service
Order Service
Payment Service
```

**Common Decomposition Strategies**

1. **Business Capability Based**

   * User Service
   * Order Service
   * Payment Service

2. **Domain-Driven Design (DDD)**

   * Split services by **Bounded Contexts**.

**Key Features**

* **Single Responsibility**
* **Loose Coupling**
* **Independent Deployment**
* **Independent Scaling**
* **Better Maintainability**

**When to Use**

* Migrating a large monolithic application.
* Building scalable enterprise systems.
* Supporting multiple development teams.

**Spring Boot Example**

```java
@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) {
        return "Order " + id;
    }
}
```

This service handles only **Order Management**, following the **Microservices** and **Service Decomposition** principles.


## ✅ **2. Service Communication**

### **1. REST Communication**

**Definition**

**REST (Representational State Transfer)** is an architectural style that allows applications and microservices to communicate using **HTTP protocols**.

**Key Features**

* **Stateless Communication**
* Uses **HTTP Methods** (GET, POST, PUT, DELETE)
* Data usually exchanged as **JSON**
* Simple and widely used
* Platform independent

**How It Works**

1. Client sends an HTTP request.
2. Server processes the request.
3. Server returns an HTTP response.

```text
Client → REST API → Server
       ← JSON Response
```

**Common HTTP Methods**

| Method | Purpose     |
| ------ | ----------- |
| GET    | Read Data   |
| POST   | Create Data |
| PUT    | Update Data |
| DELETE | Delete Data |

**When to Use**

* Web applications
* Mobile applications
* Communication between microservices
* Public APIs

**Spring Boot Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "User " + id;
    }
}
```

---

### **2. Synchronous vs Asynchronous Communication**

**Synchronous Communication**

The sender waits for the response before continuing.

**How It Works**

```text
Service A → Request → Service B
Service A ← Response ← Service B
```

**Key Features**

* Immediate response
* Easy to implement
* Strong dependency between services

**When to Use**

* Real-time data retrieval
* Payment processing
* User authentication

**Example**

```java
String response = restTemplate.getForObject(
        "http://user-service/users/1",
        String.class);
```

**Asynchronous Communication**

The sender does not wait for an immediate response.

**How It Works**

```text
Service A → Message Queue
                ↓
            Service B
```

**Key Features**

* Non-blocking
* Better scalability
* Loose coupling
* High availability

**When to Use**

* Notifications
* Email sending
* Order processing
* Background jobs

**Example**

```java
kafkaTemplate.send("orders", order);
```

**Comparison**

| Feature     | Synchronous | Asynchronous |
| ----------- | ----------- | ------------ |
| Response    | Immediate   | Later        |
| Coupling    | Tight       | Loose        |
| Scalability | Lower       | Higher       |
| Complexity  | Simple      | More Complex |

---

### **3. gRPC**

**Definition**

**gRPC (Google Remote Procedure Call)** is a high-performance communication framework that uses **Protocol Buffers (Protobuf)** instead of JSON.

**Key Features**

* High performance
* Uses **HTTP/2**
* Smaller payload size
* Faster than REST
* Supports streaming

**How It Works**

1. Define service in a `.proto` file.
2. Generate client and server code.
3. Client calls remote methods directly.

```text
Client → gRPC Call → Server
       ← Response
```

**When to Use**

* High-performance microservices
* Real-time systems
* Internal service-to-service communication

**Proto File Example**

```proto
service UserService {
  rpc GetUser(UserRequest)
      returns (UserResponse);
}
```

**Java Server Example**

```java
public class UserServiceImpl
        extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(UserRequest request,
                        StreamObserver<UserResponse> responseObserver) {

        UserResponse response =
            UserResponse.newBuilder()
                        .setName("John")
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
```

---

### **4. Event-Driven Architecture**

**Definition**

**Event-Driven Architecture (EDA)** is a design pattern where services communicate through **events** instead of direct API calls.

**Key Features**

* **Loose Coupling**
* **Asynchronous Communication**
* Highly scalable
* Fault tolerant
* Event-based processing

**How It Works**

1. A service generates an event.
2. Event is sent to a message broker.
3. Interested services consume the event.

```text
Order Service
      |
      | Order Created Event
      v
   Kafka/RabbitMQ
      |
--------------------
|                  |
Email Service   Inventory Service
```

**When to Use**

* Microservices architecture
* Real-time processing
* Notifications
* Audit logging
* Distributed systems

**Spring Boot Kafka Example**

**Producer**

```java
kafkaTemplate.send(
    "order-topic",
    "Order Created");
```

**Consumer**

```java
@KafkaListener(topics = "order-topic")
public void consume(String message) {
    System.out.println(message);
}
```


## ✅ **3. API Gateway**

### **1. Routing**

**Definition**

**Routing** is the process of directing incoming client requests to the appropriate microservice based on the request path, URL, or rules.

**Key Features**

* **Request Forwarding**
* **Path-Based Routing**
* **Centralized Entry Point**
* **Improved Security**
* **Simplified Client Communication**

**How It Works**

1. Client sends a request.
2. API Gateway receives the request.
3. Gateway identifies the target service.
4. Request is forwarded to the correct service.

```text
Client
   |
API Gateway
   |
-------------------
|                 |
User Service   Order Service
```

**When to Use**

* Microservices architecture
* Multiple backend services
* Centralized request handling

**Spring Cloud Gateway Example**

```java
@Bean
public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("user-service",
            r -> r.path("/users/**")
                  .uri("lb://USER-SERVICE"))
        .build();
}
```

---

### **2. Authentication**

**Definition**

**Authentication** is the process of verifying the identity of a user or system before granting access to resources.

**Key Features**

* **Identity Verification**
* **JWT Support**
* **OAuth2 Integration**
* **Secure Access**
* **Token-Based Security**

**How It Works**

1. User provides credentials.
2. Server validates credentials.
3. Server generates a token.
4. Client sends the token with future requests.

```text
User Login
    |
Authentication Service
    |
 JWT Token Generated
    |
Access Protected APIs
```

**When to Use**

* Secure APIs
* User login systems
* Microservices security

**JWT Example**

```java
String token = Jwts.builder()
        .setSubject("user1")
        .signWith(secretKey)
        .compact();
```

**Authorization vs Authentication**

* **Authentication** = Who are you?
* **Authorization** = What can you access?

---

### **3. Rate Limiting**

**Definition**

**Rate Limiting** restricts the number of requests a client can make within a specific time period.

**Key Features**

* **Prevents Abuse**
* **Protects APIs**
* **Improves Stability**
* **Controls Traffic**
* **Prevents DDoS-Like Overload**

**How It Works**

1. Client sends requests.
2. System counts requests.
3. If limit is exceeded, request is rejected.

```text
Limit = 100 Requests/Minute

Request 101
     |
  Rejected
```

**When to Use**

* Public APIs
* Payment APIs
* High-traffic applications
* Security-sensitive systems

**Spring Cloud Gateway Example**

```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: user-service
        uri: lb://USER-SERVICE
        predicates:
        - Path=/users/**
        filters:
        - RequestRateLimiter=10,20
```

---

### **4. Load Balancing**

**Definition**

**Load Balancing** distributes incoming requests across multiple service instances to improve performance and availability.

**Key Features**

* **Traffic Distribution**
* **High Availability**
* **Fault Tolerance**
* **Better Performance**
* **Horizontal Scaling**

**How It Works**

1. Multiple instances of a service are running.
2. Load balancer receives requests.
3. Requests are distributed among instances.

```text
            Load Balancer
                  |
       ---------------------
       |         |         |
   Instance1 Instance2 Instance3
```

**Common Algorithms**

1. **Round Robin**
2. **Least Connections**
3. **Weighted Round Robin**
4. **Random Selection**

**When to Use**

* High-traffic applications
* Cloud-native applications
* Scalable microservices

**Spring Cloud LoadBalancer Example**

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```


## ✅ **4. Service Discovery**

### **1. Eureka**

**Definition**

**Eureka** is a **Service Discovery Server** provided by **Spring Cloud Netflix** that allows microservices to register themselves and discover other services dynamically.

**Key Features**

* **Automatic Service Registration**
* **Service Discovery**
* **Load Balancing Support**
* **Health Monitoring**
* **Dynamic Scaling**

**How It Works**

1. Eureka Server starts.
2. Microservices register with Eureka.
3. Services query Eureka to find other services.
4. Requests are routed using service names instead of IP addresses.

```text
Eureka Server
      |
-----------------------
|                     |
User Service     Order Service
```

**When to Use**

* Spring Boot Microservices
* Dynamic cloud environments
* Service-to-service communication

**Eureka Server Example**

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(
            EurekaServerApplication.class, args);
    }
}
```

**Client Configuration**

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

### **2. Consul**

**Definition**

**Consul** is a **Service Discovery and Configuration Management Tool** developed by **HashiCorp** for managing distributed applications and microservices.

**Key Features**

* **Service Discovery**
* **Health Checks**
* **Key-Value Configuration Store**
* **Multi-Data Center Support**
* **DNS and HTTP API Support**

**How It Works**

1. Services register with Consul.
2. Consul continuously performs health checks.
3. Healthy services are available for discovery.
4. Other services query Consul to locate them.

```text
Consul Server
      |
-----------------------
|                     |
User Service     Payment Service
```

**When to Use**

* Cloud-native applications
* Kubernetes environments
* Multi-cloud deployments
* Large distributed systems

**Spring Boot Configuration**

```yaml
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        enabled: true
```

**Consul Advantages**

* Built-in health checks
* Configuration management
* Better multi-cloud support
* DNS-based discovery

---

### **3. Registration and Discovery**

**Definition**

**Service Registration and Discovery** is a mechanism where microservices register themselves with a registry and other services discover them dynamically.

**Key Features**

* **Dynamic Service Lookup**
* **No Hardcoded URLs**
* **Automatic Scaling Support**
* **Fault Tolerance**
* **Load Balancing Integration**

**How It Works**

1. Service starts.
2. Service registers with a registry (**Eureka**, **Consul**).
3. Registry stores service details.
4. Other services request service locations from the registry.
5. Communication happens using service names.

```text
1. User Service Registers
            |
            v
      Service Registry
            ^
            |
2. Order Service Discovers
```

**Why It Is Needed**

Without Service Discovery:

```text
Order Service
      |
http://192.168.1.10:8080
```

If the IP changes, communication fails.

With Service Discovery:

```text
Order Service
      |
USER-SERVICE
      |
Service Registry
```

The registry automatically provides the current service instance.

**When to Use**

* Any Microservices Architecture
* Cloud Deployments
* Containerized Applications
* Auto-Scaling Systems

**Spring Boot Discovery Example**

```java
@RestController
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> services() {
        return discoveryClient.getServices();
    }
}
```

**Eureka vs Consul**

| Feature             | Eureka  | Consul    |
| ------------------- | ------- | --------- |
| Developed By        | Netflix | HashiCorp |
| Service Discovery   | Yes     | Yes       |
| Health Checks       | Basic   | Advanced  |
| Configuration Store | No      | Yes       |
| DNS Support         | No      | Yes       |
| Multi-Data Center   | Limited | Excellent |



## ✅ **5. Configuration Management**

### **1. Centralized Configuration**

**Definition**

**Centralized Configuration** is a way of storing application configuration in a **single central location** instead of keeping separate configuration files in every microservice.

**Key Features**

* **Centralized Management**
* **Single Source of Truth**
* **Environment-Specific Configurations**
* **Easy Updates**
* **Consistent Configuration Across Services**

**How It Works**

1. Configuration is stored in a central repository.
2. Microservices fetch configuration during startup.
3. Services use the configuration without storing it locally.
4. Changes can be managed from one place.

```text
Configuration Repository
          |
-------------------------
|           |           |
User      Order      Payment
Service   Service    Service
```

**Benefits**

* Easier maintenance
* Reduced duplication
* Better consistency
* Faster configuration changes
* Simplified deployment

**When to Use**

* Microservices architecture
* Multiple environments (Dev, QA, Prod)
* Large distributed systems

**Example**

Instead of storing this in every service:

```yaml
server:
  port: 8081

database:
  url: jdbc:mysql://localhost:3306/appdb
```

Store it centrally and let services fetch it automatically.

---

### **2. Spring Cloud Config**

**Definition**

**Spring Cloud Config** provides **Centralized Configuration Management** for Spring Boot microservices using a dedicated **Config Server**.

**Key Features**

* **Centralized Configuration**
* **Git-Based Configuration Storage**
* **Environment Support**
* **Dynamic Configuration Updates**
* **Integration with Spring Boot**

**How It Works**

1. Configuration files are stored in a **Git Repository**.
2. **Config Server** reads configurations from Git.
3. Microservices request configuration from Config Server.
4. Configurations are loaded during startup.

```text
Git Repository
       |
Config Server
       |
-------------------------
|           |           |
User      Order      Payment
Service   Service    Service
```

**When to Use**

* Spring Boot Microservices
* Multiple deployment environments
* Centralized configuration management

**Config Server Setup**

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            ConfigServerApplication.class,
            args);
    }
}
```

**Config Server Configuration**

```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/config-repo
```

**Client Configuration**

```yaml
spring:
  application:
    name: user-service

  config:
    import: configserver:http://localhost:8888
```

**Accessing Configuration**

```java
@RestController
public class UserController {

    @Value("${app.message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }
}
```

## ✅ **6. Resilience Patterns**

### **1. Circuit Breaker**

**Definition**

**Circuit Breaker** is a resilience pattern that prevents repeated calls to a failing service, helping avoid cascading failures in a microservices system.

**Key Features**

* **Failure Detection**
* **Automatic Recovery**
* **Fallback Support**
* **Prevents Cascading Failures**
* **Improves System Stability**

**How It Works**

1. Service calls are monitored.
2. If failures exceed a threshold, the circuit opens.
3. Requests are blocked temporarily.
4. After a waiting period, a few requests are allowed for testing.
5. If successful, the circuit closes again.

**States**

* **Closed** – Normal operation.
* **Open** – Requests blocked.
* **Half-Open** – Testing recovery.

```text
Service A
    |
Circuit Breaker
    |
Service B
```

**When to Use**

* External API calls
* Microservice communication
* Unstable downstream services

**Resilience4j Example**

```java
@CircuitBreaker(
    name = "paymentService",
    fallbackMethod = "fallback")
public String getPayment() {
    return paymentClient.getPayment();
}

public String fallback(Exception ex) {
    return "Service Unavailable";
}
```

---

### **2. Retry**

**Definition**

**Retry** automatically attempts a failed operation again before reporting failure.

**Key Features**

* **Automatic Reattempts**
* **Handles Temporary Failures**
* **Improves Reliability**
* **Configurable Attempts**
* **Works with Circuit Breakers**

**How It Works**

1. Request fails.
2. System retries after a delay.
3. If retry succeeds, response is returned.
4. If all retries fail, an error is returned.

```text
Request
   |
Failure
   |
Retry 1
Retry 2
Retry 3
   |
Success / Failure
```

**When to Use**

* Network issues
* Temporary service outages
* External API communication

**Resilience4j Example**

```java
@Retry(name = "paymentService")
public String getPayment() {
    return paymentClient.getPayment();
}
```

**Important**

Use retries only for **temporary failures**, not permanent errors.

---

### **3. Timeout**

**Definition**

**Timeout** defines the maximum time an application waits for a response before terminating the request.

**Key Features**

* **Prevents Infinite Waiting**
* **Improves Performance**
* **Resource Protection**
* **Faster Failure Detection**
* **Works with Retry and Circuit Breaker**

**How It Works**

1. Request is sent.
2. Timer starts.
3. If response arrives within limit, process continues.
4. If limit is exceeded, request fails.

```text
Request Sent
      |
Wait 3 Seconds
      |
Response Received
      |
Success

OR

No Response
      |
Timeout Exception
```

**When to Use**

* Remote API calls
* Database calls
* Microservice communication

**Spring WebClient Example**

```java
WebClient.builder()
    .build()
    .get()
    .uri("/users")
    .retrieve()
    .bodyToMono(String.class)
    .timeout(Duration.ofSeconds(3));
```

**Benefits**

* Better user experience
* Prevents thread blocking
* Faster error handling

---

### **4. Bulkhead Pattern**

**Definition**

**Bulkhead Pattern** isolates resources so that failure in one part of the system does not affect other parts.

**Key Features**

* **Resource Isolation**
* **Fault Containment**
* **Improved Availability**
* **Independent Resource Pools**
* **Prevents System-Wide Failure**

**How It Works**

1. Separate thread pools or resources are allocated.
2. Each service uses its own pool.
3. If one service becomes overloaded, others continue working.

```text
Application
    |
-----------------------
|          |          |
Pool A   Pool B    Pool C
User     Order     Payment
```

**When to Use**

* High-traffic systems
* Multiple dependent services
* Critical business applications

**Resilience4j Example**

```java
@Bulkhead(name = "paymentService")
public String getPayment() {
    return paymentClient.getPayment();
}
```

**Benefits**

* Prevents resource exhaustion
* Increases fault tolerance
* Improves system stability
* Protects critical services


## ✅ **7. Distributed Transactions**

### **1. Two-Phase Commit (2PC)**

**Definition**

**Two-Phase Commit (2PC)** is a distributed transaction protocol that ensures all participating services either **commit** or **rollback** a transaction together.

**Key Features**

* **Strong Consistency**
* **Atomic Transactions**
* **Central Coordinator**
* **Commit or Rollback Together**
* **Data Integrity**

**How It Works**

**Phase 1: Prepare Phase**

1. Coordinator asks all services if they can commit.
2. Services prepare the transaction and respond.

**Phase 2: Commit Phase**

1. If all services agree, coordinator sends commit.
2. If any service fails, coordinator sends rollback.

```text id="1z1jso"
Coordinator
     |
--------------------
|                  |
Service A      Service B

Prepare
   |
Commit / Rollback
```

**When to Use**

* Banking systems
* Financial transactions
* Systems requiring strict consistency

**Example**

```java id="7w9cyw"
@Transactional
public void transferMoney() {
    debitAccount();
    creditAccount();
}
```

**Challenges**

* Performance overhead
* Coordinator becomes a single point of failure
* Not suitable for highly scalable microservices

---

### **2. Saga Pattern**

**Definition**

**Saga Pattern** manages distributed transactions using a sequence of **local transactions** and **compensating transactions** instead of a global transaction.

**Key Features**

* **Distributed Transactions**
* **No Global Locking**
* **Better Scalability**
* **Compensation Mechanism**
* **Event-Driven Support**

**How It Works**

1. First service completes its local transaction.
2. Event triggers the next service.
3. Process continues until all transactions succeed.
4. If a step fails, compensating transactions undo previous changes.

```text id="9vuz7w"
Create Order
      |
Reserve Inventory
      |
Process Payment
      |
Success
```

**Failure Scenario**

```text id="m9h0x2"
Create Order
      |
Reserve Inventory
      |
Payment Failed
      |
Cancel Inventory
      |
Cancel Order
```

**When to Use**

* Microservices architecture
* E-commerce applications
* Highly scalable systems

**Event Example**

```java id="yrzd51"
kafkaTemplate.send(
    "order-created",
    orderEvent);
```

**Benefits**

* Better scalability
* No distributed locks
* High availability

---

### **3. Eventual Consistency**

**Definition**

**Eventual Consistency** means all services will become consistent over time, but not necessarily immediately after an update.

**Key Features**

* **High Scalability**
* **Asynchronous Updates**
* **Better Performance**
* **Loose Coupling**
* **Common in Microservices**

**How It Works**

1. Service updates its own database.
2. An event is published.
3. Other services consume the event.
4. Databases synchronize gradually.

```text id="8z0xmw"
Order Service
      |
Order Created Event
      |
-----------------------
|                     |
Inventory         Payment
Service           Service
```

**Example**

1. Order Service creates an order.
2. Inventory Service updates stock.
3. Payment Service processes payment.
4. All services become consistent after event processing.

**When to Use**

* Microservices
* Event-Driven Architecture
* Distributed systems
* High-performance applications

**Kafka Consumer Example**

```java id="3ld7pq"
@KafkaListener(topics = "order-created")
public void processOrder(String event) {
    System.out.println(event);
}
```

**Benefits**

* Better scalability
* Improved availability
* Faster response times
* Reduced service dependency

**Comparison**

| Feature                   | Two-Phase Commit | Saga Pattern         | Eventual Consistency |
| ------------------------- | ---------------- | -------------------- | -------------------- |
| Consistency               | Strong           | Eventual             | Eventual             |
| Performance               | Lower            | Higher               | Higher               |
| Scalability               | Limited          | High                 | Very High            |
| Rollback                  | Global Rollback  | Compensating Actions | Event-Based Recovery |
| Microservices Suitability | Low              | High                 | High                 |


## ✅ **8. Messaging Systems**

### **1. Kafka**

**Definition:**
**Apache Kafka** is a **distributed event streaming platform** used to handle **high-volume real-time data**.

**Key Features:**

* **High Throughput**
* **Scalable**
* **Fault Tolerant**
* **Persistent Storage**
* **Real-Time Processing**

**How it Works:**

1. **Producer** sends messages to a **Topic**.
2. Kafka stores messages in **Partitions**.
3. **Consumer** reads messages from the Topic.
4. Messages remain stored for a configured retention period.

**When to Use:**

* Real-time analytics
* Log processing
* Event-driven microservices
* Streaming data pipelines

**Example:**

```java
// Producer
producer.send(new ProducerRecord<>("orders", "Order Created"));
```

---

### **2. RabbitMQ**

**Definition:**
**RabbitMQ** is a **message broker** that enables applications to communicate through **message queues**.

**Key Features:**

* **Reliable Message Delivery**
* **Message Routing**
* **Acknowledgments**
* **Queue-Based Communication**
* **Easy Integration**

**How it Works:**

1. Producer sends a message to an **Exchange**.
2. Exchange routes the message to a **Queue**.
3. Consumer reads the message from the Queue.
4. Consumer sends acknowledgment after processing.

**When to Use:**

* Task processing
* Order processing
* Notification systems
* Reliable asynchronous communication

**Example:**

```java
channel.basicPublish("", "orderQueue", null,
        "Order Created".getBytes());
```

---

### **3. Message Queues**

**Definition:**
A **Message Queue** is a communication mechanism where messages are stored in a queue until a consumer processes them.

**Key Features:**

* **Asynchronous Communication**
* **Decoupling Services**
* **Load Balancing**
* **Reliable Processing**
* **Improved Scalability**

**How it Works:**

1. Producer sends a message.
2. Message is stored in a queue.
3. Consumer retrieves and processes the message.
4. Message is removed after successful processing.

**When to Use:**

* Background jobs
* Email sending
* Payment processing
* Distributed systems

**Example:**

```text
Producer → Queue → Consumer
```

---

### **4. Event Streaming**

**Definition:**
**Event Streaming** is the continuous flow and processing of events in real time as they occur.

**Key Features:**

* **Real-Time Processing**
* **Continuous Data Flow**
* **Scalability**
* **Event Persistence**
* **Low Latency**

**How it Works:**

1. An event occurs (e.g., order placed).
2. Event is published to a streaming platform.
3. Multiple consumers process the event simultaneously.
4. Applications react immediately to the event.

**When to Use:**

* Live dashboards
* Fraud detection
* IoT systems
* Real-time monitoring

**Example:**

```text
Order Created Event
      ↓
Kafka Topic
      ↓
Inventory Service
Payment Service
Notification Service
```

**Kafka vs RabbitMQ**

| Feature         | Kafka                        | RabbitMQ                              |
| --------------- | ---------------------------- | ------------------------------------- |
| Type            | **Event Streaming Platform** | **Message Broker**                    |
| Message Storage | **Long-Term Retention**      | **Usually Removed After Consumption** |
| Throughput      | **Very High**                | **Moderate**                          |
| Use Case        | **Real-Time Streaming**      | **Task Queues & Messaging**           |
| Scalability     | **Excellent**                | **Good**                              |
| Consumer Model  | **Pull-Based**               | **Push-Based**                        |


## ✅ **9. Database Design**

### **1. Database per Service**

**Definition:**
**Database per Service** is a **microservices design pattern** where each service owns its own database and no other service can access it directly.

**Key Features:**

* **Loose Coupling**
* **Independent Deployment**
* **Data Isolation**
* **Better Scalability**
* **Technology Flexibility**

**How it Works:**

1. Each microservice has its own database.
2. Services manage their own data.
3. Services communicate through **APIs** or **events**.
4. No direct database sharing between services.

**When to Use:**

* Microservices architecture
* Independent service scaling
* Large distributed systems

**Example:**

```text
User Service     → UserDB
Order Service    → OrderDB
Payment Service  → PaymentDB
```

**Interview One-Liner:**
Each microservice owns its **own database**, ensuring **independence, scalability, and loose coupling**.

---

### **2. CQRS (Command Query Responsibility Segregation)**

**Definition:**
**CQRS** separates **write operations (Commands)** from **read operations (Queries)**.

**Key Features:**

* **Separate Read and Write Models**
* **Improved Performance**
* **Independent Scaling**
* **Better Flexibility**
* **Optimized Queries**

**How it Works:**

1. **Command** handles data modification.
2. **Query** handles data retrieval.
3. Read and write models can use different databases.
4. Data is synchronized through events.

**When to Use:**

* Complex business applications
* High read/write traffic systems
* Event-driven architectures

**Example:**

```java
// Command
orderService.createOrder(order);

// Query
Order order = orderQueryService.getOrder(id);
```

**CQRS Structure:**

```text
Commands → Write Database
Queries  → Read Database
```

**Interview One-Liner:**
CQRS separates **reads and writes** to improve **performance, scalability, and maintainability**.

---

### **3. Event Sourcing**

**Definition:**
**Event Sourcing** stores all changes as a sequence of **events** instead of storing only the current state.

**Key Features:**

* **Complete Audit Trail**
* **Event History**
* **Data Recovery**
* **Easy Debugging**
* **Supports CQRS**

**How it Works:**

1. User performs an action.
2. An event is generated and stored.
3. Current state is rebuilt by replaying events.
4. Events are never deleted or updated.

**When to Use:**

* Financial systems
* Audit logging
* Event-driven applications
* Systems requiring full history tracking

**Example:**

```text
Account Created
Money Deposited ₹1000
Money Withdrawn ₹200
```

**Current Balance Calculation:**

```text
0 + 1000 - 200 = 800
```

**Simple Event Class:**

```java
public class MoneyDepositedEvent {
    private String accountId;
    private double amount;
}
```


## ✅ **10. Security**

### **1. JWT (JSON Web Token)**

**Definition:**
**JWT** is a compact and secure token format used for **authentication** and **authorization** between client and server.

**Key Features:**

* **Stateless Authentication**
* **Digitally Signed**
* **Compact Format**
* **Secure Data Exchange**
* **Easy to Use with APIs**

**How it Works:**

1. User logs in with credentials.
2. Server generates a **JWT token**.
3. Client stores the token.
4. Client sends the token in each request.
5. Server validates the token before granting access.

**JWT Structure:**

```text
Header.Payload.Signature
```

**When to Use:**

* REST APIs
* Microservices
* Single Sign-On (SSO)
* Stateless authentication

**Example:**

```java
String token = Jwts.builder()
        .setSubject("user1")
        .signWith(secretKey)
        .compact();
```

**Interview One-Liner:**
JWT is a **stateless authentication token** that securely carries user information between client and server.

---

### **2. OAuth2**

**Definition:**
**OAuth2** is an **authorization framework** that allows applications to access user resources without sharing passwords.

**Key Features:**

* **Secure Authorization**
* **Token-Based Access**
* **Third-Party Login Support**
* **Delegated Access**
* **Industry Standard**

**How it Works:**

1. User logs in through an **Authorization Server**.
2. User grants permission.
3. Authorization Server issues an **Access Token**.
4. Client uses the token to access protected resources.

**When to Use:**

* Google Login
* GitHub Login
* Social Authentication
* Third-party API access

**Example:**

```text
User → Google Login
      → Access Token
      → Application
```

**Interview One-Liner:**
OAuth2 provides **secure authorization** by allowing applications to access resources using **tokens instead of passwords**.

---

### **3. API Security**

**Definition:**
**API Security** is the practice of protecting APIs from unauthorized access, attacks, and data breaches.

**Key Features:**

* **Authentication**
* **Authorization**
* **Encryption**
* **Rate Limiting**
* **Input Validation**

**How it Works:**

1. Client sends a request.
2. API validates credentials or token.
3. User permissions are checked.
4. Request is processed securely.
5. Response is returned.

**Common Security Techniques:**

* **JWT Authentication**
* **OAuth2**
* **HTTPS**
* **API Keys**
* **Rate Limiting**

**When to Use:**

* Public APIs
* Internal APIs
* Microservices
* Cloud applications

**Example:**

```java
@GetMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public List<User> getUsers() {
    return userService.findAll();
}
```

**Interview One-Liner:**
API Security protects APIs using **authentication, authorization, encryption, and access control mechanisms**.

---

### **4. Service-to-Service Authentication**

**Definition:**
**Service-to-Service Authentication** is a mechanism where one microservice securely verifies the identity of another microservice.

**Key Features:**

* **Secure Communication**
* **Mutual Trust**
* **Token-Based Access**
* **Microservices Security**
* **Automated Authentication**

**How it Works:**

1. Service A requests a token.
2. Authentication Server issues a token.
3. Service A calls Service B with the token.
4. Service B validates the token.
5. Secure communication is established.

**When to Use:**

* Microservices architecture
* Distributed systems
* Internal APIs
* Cloud-native applications

**Example:**

```java
HttpHeaders headers = new HttpHeaders();
headers.setBearerAuth(token);

restTemplate.exchange(
    serviceUrl,
    HttpMethod.GET,
    new HttpEntity<>(headers),
    String.class
);
```

**Common Approaches:**

* **JWT Tokens**
* **OAuth2 Client Credentials Flow**
* **mTLS (Mutual TLS)**
* **API Keys**


## ✅ **11. Observability**

### **1. Centralized Logging**

**Definition:**
**Centralized Logging** is the practice of collecting logs from multiple applications and services into a single location for analysis and troubleshooting.

**Key Features:**

* **Single Log Repository**
* **Easy Troubleshooting**
* **Real-Time Log Analysis**
* **Search and Filtering**
* **Improved Visibility**

**How it Works:**

1. Applications generate logs.
2. Logs are sent to a central logging system.
3. Logs are stored and indexed.
4. Developers search and analyze logs from one place.

**When to Use:**

* Microservices architecture
* Distributed systems
* Production monitoring
* Debugging issues

**Example:**

```java
log.info("Order created successfully");
log.error("Payment failed");
```

**Popular Tools:**

* **ELK Stack (Elasticsearch, Logstash, Kibana)**
* **Splunk**
* **Graylog**

**Interview One-Liner:**
Centralized Logging collects logs from all services into a **single platform** for easier monitoring and troubleshooting.

---

### **2. Distributed Tracing**

**Definition:**
**Distributed Tracing** tracks a request as it travels through multiple services in a distributed system.

**Key Features:**

* **Request Tracking**
* **Performance Analysis**
* **Root Cause Detection**
* **Service Dependency Visibility**
* **Latency Monitoring**

**How it Works:**

1. A request enters the system.
2. A unique **Trace ID** is assigned.
3. Each service records its processing details.
4. The complete request path can be viewed later.

**When to Use:**

* Microservices
* API troubleshooting
* Performance optimization
* Distributed applications

**Example:**

```text
Request
  Service A
  Service B
  Database
```

**Popular Tools:**

* **Zipkin**
* **Jaeger**
* **OpenTelemetry**

**Interview One-Liner:**
Distributed Tracing follows a request across multiple services using a **Trace ID** to identify bottlenecks and failures.

---

### **3. Monitoring**

**Definition:**
**Monitoring** is the continuous observation of application and infrastructure health to detect issues and ensure system reliability.

**Key Features:**

* **Health Checks**
* **Real-Time Alerts**
* **Performance Tracking**
* **Availability Monitoring**
* **Incident Detection**

**How it Works:**

1. Monitoring tools collect system data.
2. Metrics are analyzed continuously.
3. Alerts are triggered when thresholds are exceeded.
4. Teams investigate and resolve issues.

**When to Use:**

* Production systems
* Cloud environments
* High-availability applications
* Microservices

**Example:**

```java
@Readiness
public boolean isHealthy() {
    return database.isConnected();
}
```

**Popular Tools:**

* **Prometheus**
* **Grafana**
* **Datadog**
* **New Relic**

**Interview One-Liner:**
Monitoring helps track the **health, availability, and performance** of applications and infrastructure.

---

### **4. Metrics Collection**

**Definition:**
**Metrics Collection** is the process of gathering numerical data about application and system performance.

**Key Features:**

* **Performance Measurement**
* **Resource Tracking**
* **Trend Analysis**
* **Alert Generation**
* **Capacity Planning**

**How it Works:**

1. Applications expose metrics.
2. Monitoring tools collect the metrics.
3. Data is stored and visualized.
4. Teams analyze trends and system behavior.

**Common Metrics:**

* **CPU Usage**
* **Memory Usage**
* **Request Count**
* **Response Time**
* **Error Rate**

**When to Use:**

* Performance monitoring
* Capacity planning
* SLA tracking
* System optimization

**Example:**

```java
Counter counter = meterRegistry.counter("orders.created");
counter.increment();
```

**Interview One-Liner:**
Metrics Collection gathers **numerical performance data** to measure system health, performance, and reliability.

**Centralized Logging vs Distributed Tracing vs Monitoring vs Metrics Collection**

| Feature   | Centralized Logging | Distributed Tracing    | Monitoring         | Metrics Collection   |
| --------- | ------------------- | ---------------------- | ------------------ | -------------------- |
| Focus     | **Logs**            | **Request Flow**       | **System Health**  | **Numerical Data**   |
| Purpose   | Troubleshooting     | Request Tracking       | Detect Issues      | Measure Performance  |
| Data Type | Log Entries         | Trace Information      | Health Status      | Metrics              |
| Example   | Error Logs          | API Call Path          | Server Status      | CPU Usage            |
| Best For  | Debugging           | Microservices Analysis | System Reliability | Performance Analysis |


## ✅ **12. Containerization & Orchestration**

### **1. Docker**

**Definition:**
**Docker** is a **containerization platform** used to package an application along with its dependencies into a lightweight **container**.

**Key Features:**

* **Containerization**
* **Portable Across Environments**
* **Fast Deployment**
* **Lightweight**
* **Consistent Execution**

**How it Works:**

1. Create a **Dockerfile**.
2. Build a **Docker Image**.
3. Run the image as a **Container**.
4. The container runs the application in an isolated environment.

**When to Use:**

* Application packaging
* Microservices deployment
* CI/CD pipelines
* Consistent development environments

**Dockerfile Example:**

```dockerfile
FROM openjdk:17
COPY app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Commands:**

```bash
docker build -t myapp .
docker run -p 8080:8080 myapp
```

**Interview One-Liner:**
Docker packages an application and its dependencies into a **container**, ensuring it runs consistently across environments.

---

### **2. Kubernetes**

**Definition:**
**Kubernetes (K8s)** is a **container orchestration platform** used to automate the deployment, scaling, and management of containers.

**Key Features:**

* **Auto Scaling**
* **Self-Healing**
* **Load Balancing**
* **Rolling Updates**
* **High Availability**

**How it Works:**

1. Applications run inside **Pods**.
2. Pods are managed by Kubernetes.
3. Kubernetes monitors pod health.
4. Failed pods are automatically recreated.
5. Traffic is distributed through **Services**.

**When to Use:**

* Large-scale microservices
* Cloud-native applications
* High-availability systems
* Container orchestration

**Deployment Example:**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app
spec:
  replicas: 3
```

**Commands:**

```bash
kubectl apply -f deployment.yaml
kubectl get pods
```

**Interview One-Liner:**
Kubernetes automates the **deployment, scaling, and management of containers** in production environments.

---

### **3. Helm**

**Definition:**
**Helm** is the **package manager for Kubernetes** that simplifies deploying and managing Kubernetes applications.

**Key Features:**

* **Reusable Templates**
* **Version Management**
* **Easy Deployment**
* **Configuration Management**
* **Rollback Support**

**How it Works:**

1. Application configuration is packaged as a **Helm Chart**.
2. Helm installs the chart into Kubernetes.
3. Values can be customized using configuration files.
4. Helm manages upgrades and rollbacks.

**When to Use:**

* Kubernetes deployments
* Managing complex applications
* Reusable infrastructure templates
* Automated releases

**Install Command:**

```bash
helm install myapp mychart
```

**Values Example:**

```yaml
replicaCount: 3

image:
  repository: myapp
  tag: latest
```

**Interview One-Liner:**
Helm simplifies Kubernetes deployments by packaging applications into reusable **Helm Charts**.

**Docker vs Kubernetes vs Helm**

| Feature    | Docker               | Kubernetes                  | Helm                              |
| ---------- | -------------------- | --------------------------- | --------------------------------- |
| Purpose    | **Containerization** | **Container Orchestration** | **Kubernetes Package Management** |
| Main Unit  | **Container**        | **Pod**                     | **Chart**                         |
| Scaling    | Manual               | Automatic                   | Uses Kubernetes                   |
| Deployment | Single Container     | Multiple Containers         | Simplifies Kubernetes Deployment  |
| Best For   | Packaging Apps       | Managing Containers         | Managing Kubernetes Applications  |


## ✅ **13. Cloud & Deployment**

### **1. AWS Services**

**Definition:**
**AWS (Amazon Web Services)** is a cloud platform that provides services for computing, storage, databases, networking, security, and deployment.

**Key Services**

* **EC2** – Virtual Servers
* **S3** – Object Storage
* **RDS** – Managed Database
* **Lambda** – Serverless Computing
* **EKS** – Kubernetes Service
* **IAM** – Identity & Access Management
* **CloudWatch** – Monitoring & Logging
* **SNS/SQS** – Messaging Services

**How It Works**

1. Deploy application on **EC2** or **EKS**
2. Store files in **S3**
3. Store data in **RDS**
4. Monitor using **CloudWatch**
5. Secure access using **IAM**

**When to Use**

* Cloud hosting
* Scalable applications
* Microservices architecture
* Serverless applications

**Example**

```text
Frontend → EC2
Images → S3
Database → RDS
Monitoring → CloudWatch
```

---

### **2. CI/CD Pipelines**

**Definition:**
**CI/CD (Continuous Integration / Continuous Deployment)** is an automated process that builds, tests, and deploys code whenever changes are pushed.

**Key Features**

* **Automated Build**
* **Automated Testing**
* **Continuous Delivery**
* **Fast Releases**
* **Reduced Human Errors**

**How It Works**

1. Developer pushes code to Git
2. Pipeline triggers automatically
3. Application is built
4. Tests are executed
5. Artifact is created
6. Application is deployed

**When to Use**

* Agile development
* Frequent releases
* DevOps environments
* Microservices projects

**Pipeline Flow**

```text
Code Commit
    ↓
Build
    ↓
Test
    ↓
Package
    ↓
Deploy
```

**Jenkins Pipeline Example**

```groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                sh './deploy.sh'
            }
        }
    }
}
```

---

### **3. Blue-Green Deployment**

**Definition:**
**Blue-Green Deployment** is a release strategy where two identical environments are maintained.

* **Blue** → Current Production
* **Green** → New Version

**How It Works**

1. Production runs on **Blue**
2. Deploy new version to **Green**
3. Test Green environment
4. Switch traffic from Blue to Green
5. Keep Blue as backup

**Key Features**

* **Zero Downtime**
* **Easy Rollback**
* **Safer Releases**
* **Reduced Risk**

**When to Use**

* Critical applications
* High availability systems
* Production deployments

**Example**

```text
Users
   ↓
Load Balancer
   ↓
Blue (v1)

Deploy v2 to Green
Test Green
Switch Traffic

Users
   ↓
Load Balancer
   ↓
Green (v2)
```

**Rollback**

```text
Green Fails
    ↓
Switch Back
    ↓
Blue
```

---

### **4. Rolling Deployment**

**Definition:**
**Rolling Deployment** updates application instances gradually instead of replacing all servers at once.

**How It Works**

1. Update a few servers
2. Verify health
3. Update next batch
4. Continue until all servers are updated

**Key Features**

* **No Downtime**
* **Gradual Release**
* **Lower Resource Cost**
* **Continuous Availability**

**When to Use**

* Kubernetes deployments
* Large distributed systems
* Microservices applications

**Example**

```text
Before Deployment

Server1 v1
Server2 v1
Server3 v1
Server4 v1
```

```text
Step 1

Server1 v2
Server2 v1
Server3 v1
Server4 v1
```

```text
Step 2

Server1 v2
Server2 v2
Server3 v1
Server4 v1
```

```text
Final State

Server1 v2
Server2 v2
Server3 v2
Server4 v2
```

**Kubernetes Example**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app
spec:
  replicas: 4
  strategy:
    type: RollingUpdate
  template:
    spec:
      containers:
      - name: app
        image: app:v2
```

**Blue-Green vs Rolling Deployment**

| Feature             | Blue-Green       | Rolling         |
| ------------------- | ---------------- | --------------- |
| Deployment Style    | Two Environments | Gradual Updates |
| Downtime            | None             | None            |
| Rollback            | Very Fast        | Slower          |
| Infrastructure Cost | Higher           | Lower           |
| Risk                | Very Low         | Moderate        |


## ✅ **14. Performance & Scalability**

### **1. Load Balancing**

**Definition:**
**Load Balancing** is a technique that distributes incoming traffic across multiple servers to ensure no single server is overloaded.

**Key Features**

* **Traffic Distribution**
* **High Availability**
* **Fault Tolerance**
* **Scalability**

**How It Works**

1. Client sends request
2. **Load Balancer** receives request
3. It forwards request to healthy server
4. Response is returned to client

**When to Use**

* High traffic applications
* Microservices systems
* Scalable web apps

**Example**

```text id="lb1"
Client → Load Balancer → Server1 / Server2 / Server3
```

---

### **2. Horizontal Scaling**

**Definition:**
**Horizontal Scaling** means adding more machines (servers) to handle increased load instead of upgrading a single server.

**Key Features**

* **Adds more servers**
* **Better fault tolerance**
* **Improves performance**
* **Elastic scaling**

**How It Works**

1. Traffic increases
2. New servers are added
3. Load balancer distributes traffic
4. System handles more requests

**When to Use**

* Cloud applications
* High traffic systems
* Distributed systems

**Example**

```text id="hs1"
Before: 1 Server
After: 5 Servers handling same load
```

---

### **3. Caching**

**Definition:**
**Caching** stores frequently used data in fast storage (memory) to reduce database load and improve performance.

**Key Features**

* **Fast Data Access**
* **Reduces DB load**
* **Improves latency**
* **Temporary storage**

**How It Works**

1. User requests data
2. System checks **cache**
3. If found → return instantly
4. If not found → fetch from DB and store in cache

**When to Use**

* Frequently accessed data
* Read-heavy systems
* High-performance apps

**Example (Redis Cache Flow)**

```text id="cache1"
User → Cache (Hit) → Response
User → Cache (Miss) → DB → Cache → Response
```

**Spring Boot Example**

```java id="cache2"
@Cacheable("users")
public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
}
```

---

### **4. Rate Limiting**

**Definition:**
**Rate Limiting** controls the number of requests a user or client can make in a specific time period.

**Key Features**

* **Prevents abuse**
* **Protects APIs**
* **Controls traffic**
* **Improves stability**

**How It Works**

1. Client sends request
2. System checks request count
3. If limit exceeded → block request
4. Else → allow request

**When to Use**

* Public APIs
* Login systems
* Payment gateways
* Prevent DDoS attacks

**Example**

```text id="rl1"
Limit: 100 requests/minute

User requests:
1...100 → Allowed
101 → Blocked
```

**Spring Boot Bucket4j Example**

```java id="rl2"
Bucket bucket = Bucket4j.builder()
    .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
    .build();

if (bucket.tryConsume(1)) {
    return "Request Allowed";
} else {
    return "Too Many Requests";
}
```


## ✅ **15. Microservice Design Patterns**

### **1. API Gateway Pattern**

**Definition:**
**API Gateway Pattern** is a single entry point that routes all client requests to appropriate microservices.

**Key Features**

* **Single Entry Point**
* **Request Routing**
* **Authentication & Authorization**
* **Load Balancing**
* **Rate Limiting**

**How It Works**

1. Client sends request to **API Gateway**
2. Gateway authenticates request
3. Routes request to correct microservice
4. Aggregates response if needed

**When to Use**

* Microservices architecture
* Multiple backend services
* Security centralization

**Example**

```text id="ag1"
Client → API Gateway → User Service / Order Service / Payment Service
```

**Spring Cloud Gateway Example**

```yaml id="ag2"
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: http://localhost:8081
          predicates:
            - Path=/users/**
```

---

### **2. Saga Pattern**

**Definition:**
**Saga Pattern** manages distributed transactions by breaking them into multiple local transactions with compensation steps.

**Key Features**

* **Distributed Transaction Management**
* **Event-driven**
* **Compensation Rollback**
* **Loosely Coupled Services**

**How It Works**

1. Service executes local transaction
2. Publishes event
3. Next service executes its transaction
4. If failure occurs → compensation actions run

**When to Use**

* Microservices transactions
* No 2-phase commit required
* Long-running workflows

**Example**

```text id="sp1"
Order → Payment → Inventory

If Payment fails:
Order rollback (cancel order)
```

---

### **3. CQRS Pattern**

**Definition:**
**CQRS (Command Query Responsibility Segregation)** separates read and write operations into different models.

**Key Features**

* **Separate Read & Write Models**
* **Scalability**
* **Performance Optimization**
* **Event-driven possible**

**How It Works**

1. **Command model** handles write operations
2. **Query model** handles read operations
3. Both can use different databases

**When to Use**

* High-scale systems
* Complex read/write logic
* Event-driven architecture

**Example**

```text id="cqrs1"
Write → Order Service → Database A
Read  → Order Query Service → Database B
```

**Spring Example**

```java id="cqrs2"
// Command
@PostMapping("/orders")
public void createOrder(@RequestBody Order order) {
    orderService.save(order);
}

// Query
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {
    return queryService.findById(id);
}
```

---

### **4. Strangler Pattern**

**Definition:**
**Strangler Pattern** gradually replaces a legacy system with a new system without full downtime.

**Key Features**

* **Gradual Migration**
* **No Big Bang Replacement**
* **Low Risk**
* **Backward Compatibility**

**How It Works**

1. New system is built around legacy system
2. Requests are routed to new services step by step
3. Legacy system is slowly phased out

**When to Use**

* Legacy system modernization
* Large monolithic migration
* Risk reduction scenarios

**Example**

```text id="sp2"
Client → API Gateway
        → New Service (preferred)
        → Legacy System (fallback)
```

---

### **5. Database per Service Pattern**

**Definition:**
Each microservice owns its own **separate database**, ensuring loose coupling.

**Key Features**

* **Independent Databases**
* **Loose Coupling**
* **Better Scalability**
* **Technology Flexibility**

**How It Works**

1. Each service has its own DB
2. Services communicate via APIs/events
3. No direct DB sharing

**When to Use**

* Microservices architecture
* Independent service scaling
* Team autonomy

**Example**

```text id="db1"
User Service → User DB
Order Service → Order DB
Payment Service → Payment DB
```

**Spring Example**

```yaml id="db2"
user-service:
  datasource:
    url: jdbc:mysql://user-db:3306/users

order-service:
  datasource:
    url: jdbc:mysql://order-db:3306/orders
```


## ✅ **16. Real-World Troubleshooting**

### **1. API Latency Issues**

**Definition:**
**API Latency Issues** occur when API responses take too long due to network, server, or database delays.

**Key Features**

* **Slow Response Time**
* **High Server Load**
* **Network Delays**
* **DB Query Bottlenecks**

**How It Works**

1. Request hits API
2. Processing delay occurs (DB / network / CPU)
3. Response time increases

**When to Use / Detect**

* High traffic systems
* Performance degradation
* SLA violations

**Example Fix (Spring Boot Logging)**

```java id="lat1"
long start = System.currentTimeMillis();
ResponseEntity<?> response = service.getData();
long end = System.currentTimeMillis();
System.out.println("Latency: " + (end - start) + "ms");
```

---

### **2. Distributed Debugging**

**Definition:**
**Distributed Debugging** is identifying issues across multiple microservices in a distributed system.

**Key Features**

* **Multi-service tracing**
* **Correlation IDs**
* **Centralized logs**
* **End-to-end visibility**

**How It Works**

1. Request enters system
2. **Correlation ID** is attached
3. Logs are collected across services
4. Trace path is analyzed

**When to Use**

* Microservices architecture
* Complex system failures
* Production debugging

**Example**

```java id="dbg1"
log.info("Request ID: {}", requestId);
```

---

### **3. Kafka Consumer Lag**

**Definition:**
**Kafka Consumer Lag** is the delay between messages produced and messages consumed.

**Key Features**

* **Message backlog**
* **Performance indicator**
* **Consumer health metric**
* **Real-time monitoring**

**How It Works**

1. Producer sends messages
2. Consumer reads slower than producer
3. Lag increases in Kafka topic

**When to Use**

* Event-driven systems
* Streaming pipelines
* Monitoring Kafka health

**Example Check**

```text id="kafka1"
Lag = Latest Offset - Consumer Offset
```

---

### **4. Database Bottlenecks**

**Definition:**
**Database Bottlenecks** occur when the database cannot handle the load efficiently, slowing down the system.

**Key Features**

* **Slow Queries**
* **Locking Issues**
* **High CPU/Memory usage**
* **Connection saturation**

**How It Works**

1. Multiple queries hit DB
2. Index missing or poor design
3. Queries slow down system

**When to Use / Detect**

* High traffic systems
* Slow response times
* Deadlocks

**Example Fix**

```sql id="db1"
-- Add index to improve performance
CREATE INDEX idx_user_id ON users(id);
```

---

### **5. Production Incident Handling**

**Definition:**
**Production Incident Handling** is the process of detecting, resolving, and recovering from system failures in production.

**Key Features**

* **Incident Detection**
* **Alerting Systems**
* **Rollback Strategy**
* **Post-mortem analysis**

**How It Works**

1. Alert triggered (monitoring tool)
2. On-call engineer investigates
3. Fix or rollback applied
4. System restored

**When to Use**

* System outages
* Performance degradation
* Critical failures

**Example Flow**

```text id="pi1"
Alert → Investigation → Fix/Rollback → Recovery
```

---

### **6. Root Cause Analysis (RCA)**

**Definition:**
**RCA (Root Cause Analysis)** is the process of identifying the underlying cause of a production issue.

**Key Features**

* **Problem Identification**
* **Cause Analysis**
* **Evidence-based investigation**
* **Preventive actions**

**How It Works**

1. Incident occurs
2. Logs and metrics analyzed
3. Root cause identified
4. Fix + prevention implemented

**When to Use**

* After production incidents
* Recurring failures
* System optimization

**Example Method**

```text id="rca1"
5 Whys Technique:
Why → Why → Why → Why → Root Cause
```

